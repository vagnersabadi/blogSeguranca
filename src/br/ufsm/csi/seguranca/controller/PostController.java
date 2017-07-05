package br.ufsm.csi.seguranca.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import br.ufsm.csi.seguranca.dao.LogDAO;
import br.ufsm.csi.seguranca.model.Log;
import org.omg.CORBA.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.ufsm.csi.seguranca.dao.ComentarioDAO;
import br.ufsm.csi.seguranca.dao.PostDAO;
import br.ufsm.csi.seguranca.dao.UsuarioDAO;
import br.ufsm.csi.seguranca.model.Comentario;
import br.ufsm.csi.seguranca.model.Post;
import br.ufsm.csi.seguranca.model.Usuario;
import org.springframework.web.util.HtmlUtils;

@Controller
public class PostController {

    @Autowired
    private PostDAO daoPost;

    @Autowired
    private ComentarioDAO daoComentario;

    @Autowired
    private LogDAO daoLog;

    //redireciona pagina
    @RequestMapping("GerenciaPosts.html")
    public ModelAndView gerenciaPost(HttpServletRequest request) {

        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView mv = new ModelAndView();
        if (u != null) {// se user estiver logado

            if (u.getTipoUsuario() == 1) {
                mv = new ModelAndView("paginaAdm");
            } else if (u.getTipoUsuario() == 2) {
                mv = new ModelAndView("paginaUser");
            }
        } else {
            mv = new ModelAndView("redirect: Buscalogin.html");
        }
        return mv;
    }

    //criar posts
    @RequestMapping("criarPosts.html")
    public ModelAndView criarPosts(HttpServletRequest request, Map<String, Object> model) throws SQLException {
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView mv = new ModelAndView("login.html");
        if (u != null) {// se user estiver logado
            if (u.getTipoUsuario() == 1) {//busca post do adm
                Collection<Post> posts = daoPost.getPostsUsuario(u);
                model.put("postsUsuario", posts);
                mv = new ModelAndView("criarpostsAdm");
                model.put("posts", new Post());
            } else {//busca post dos users
                Collection<Post> posts = daoPost.getPostsUsuario(u);
                model.put("postsUsuario", posts);
                mv = new ModelAndView("criarpostsUser");
                model.put("posts", new Post());
            }
        } else {
            mv.addObject("msg", "Erro: SEM ACESSO!");
            mv = new ModelAndView("redirect: Buscalogin.html");
        }
        return mv;
    }

    //listar meus posts
    @RequestMapping("meusPosts.html")
    public ModelAndView paginaPosts(HttpServletRequest request, Map<String, Object> model) throws SQLException {
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView mv = new ModelAndView("login.html");
        if (u != null) {// se user estiver logado
            if (u.getTipoUsuario() == 1) {
                Collection<Post> posts = daoPost.getPostsUsuario(u);
                model.put("postsUsuario", posts);
                mv = new ModelAndView("postsAdm");
                model.put("posts", new Post());
            } else if (u.getTipoUsuario() == 2) {
                Collection<Post> posts = daoPost.getPostsUsuario(u);
                model.put("postsUsuario", posts);
                mv = new ModelAndView("posts");
                model.put("posts", new Post());

            }
        } else {
            mv.addObject("msg", "Erro: SEM ACESSO!");
            mv = new ModelAndView("redirect: Buscalogin.html");
        }
        return mv;
    }

    //cadastrar novo post
    @RequestMapping(value = "cadastro-post.html", method = RequestMethod.POST)
    @Transactional
    public ModelAndView cadastro(Post post, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("redirect: meusPosts.html");
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        if (u.isAtivo()) {//user ativo/valido
            if (post.getIdPost() == null) {
                //se for null primera vez é para cadstro
                post.setUsuario(u);
                //pega data do cadastro
                Date d = new Date(new java.util.Date().getTime());
                post.setDataPost(d);
                daoPost.inserirPost(post);//insere
                mv.addObject("msg", "Post cadastrado com sucesso!");
            } else {
                // senão e para alterar
                Post postPersiste = daoPost.getPostId(post.getIdPost());
                if (u.getTipoUsuario() == 1 || u.getIdUsuario().equals(postPersiste.getUsuario().getIdUsuario())) {

                    //escapndo caracteres
                    postPersiste.setTituloPost((HtmlUtils.htmlEscape(post.getTituloPost())));
                    postPersiste.setTextoPost((HtmlUtils.htmlEscape(post.getTextoPost())));
                    daoPost.alterarPost(postPersiste);
                    mv.addObject("msg", "Post alterado com sucesso!");
                } else {
                    mv = new ModelAndView("erro");
                    mv.addObject("msg", "Sem Acesso!");
                }
            }
        } else {
            mv.addObject("msg", "Erro: Você Foi desativado!");
            mv = new ModelAndView("redirect: Buscalogin.html");
        }
        return mv;
    }

    //alterar post
    @RequestMapping(value = "alterarPost.html", method = RequestMethod.POST)
    public ModelAndView alteraUsuario(Long id, Map<String, Object> model, HttpServletRequest request) throws Exception {
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView mv = new ModelAndView("alteraPostAdm");
        Post post = new Post();
        post = daoPost.getPostId(id);
        //System.out.println(post.getUsuario().getIdUsuario());
        if (u != null) {// se user estiver logado
            //se user for o adm ou criado do post pode alterar
            if (u.getTipoUsuario() == 1 || u.getIdUsuario().equals(post.getUsuario().getIdUsuario())) {
                model.put("posts", post);
            } else {
                mv = new ModelAndView("erro");
                mv.addObject("msg", "Sem Acesso!");
            }
        } else {
            mv.addObject("msg", "Erro: SEM ACESSO!");
            mv = new ModelAndView("redirect: Buscalogin.html");
        }
        return mv;
    }

    //ver post escolhido
    @RequestMapping(value = "verPost.html", method = RequestMethod.POST)
    public ModelAndView verPost(Long id, HttpServletRequest request, Map<String, Object> model) throws Exception {
        ModelAndView mv;
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        Post post = new Post();
        post = daoPost.getPostId(id);
        model.put("post", post);
        Collection<Comentario> comentarios = daoComentario.getComentariosPost(post);
        model.put("comentariosPost", comentarios);
        model.put("comentarios", new Comentario());

        if (u != null) {
            if (u.getTipoUsuario() == 1) {
                mv = new ModelAndView("verPostAdm");
            } else {
                mv = new ModelAndView("verPost");
            }
        } else {
            mv = new ModelAndView("verPostPublic");
            //mv = new ModelAndView("erro");
            //mv.addObject("msg", "Você deve ta logado");
        }
        return mv;
    }

    //listar todos os posts
    @RequestMapping(value = "todosPosts.html")
    public ModelAndView todosPost(HttpServletRequest request, Map<String, Object> model) throws Exception {
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView mv = new ModelAndView();
        if (u == null) {
            mv = new ModelAndView("todosPosts");
        } else {
            if (u.getTipoUsuario() == 1) {
                mv = new ModelAndView("todosPostsAdm");
            } else {
                mv = new ModelAndView("todosPostsUser");
            }
        }
        Collection<Post> posts = daoPost.getPosts();
        model.put("posts", posts);

        return mv;
    }

    //deletar post escolhido
    @RequestMapping(value = "deletaPost.html", method = RequestMethod.POST)
    public ModelAndView excluirPost(Long id, HttpServletRequest request, Map<String, Object> model) throws Exception {
        ModelAndView mv = new ModelAndView("redirect: GerenciaPosts.html");
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        Post post = new Post();
        post = daoPost.getPostId(id);

        if (u != null) {
            //se for o adm ou o criador do post pode deletar
            if (u.getTipoUsuario() == 1 || u.getIdUsuario().equals(post.getUsuario().getIdUsuario())) {
                daoPost.excluirPost(post);
            }
        } else {
            mv.addObject("msg", "Erro: SEM ACESSO!");
            mv = new ModelAndView("redirect: Buscalogin.html");
        }
        return mv;
    }

    //cadastrar comentario
    @RequestMapping(value = "cadastro-comentario.html", method = RequestMethod.POST)
    @Transactional
    public ModelAndView cadastro(HttpServletRequest request, Long idPost, Comentario comentario) throws Exception {
        ModelAndView mv = new ModelAndView("redirect: todosPosts.html");
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        Post post = new Post();
        post = daoPost.getPostId(idPost);

        comentario.setPost(post);
        daoComentario.inserirComentario(comentario);
        if (u != null) {
            //implementado para gravar log
            Log log = new Log();
            //log.setClasse(Class.forName(String));
            //log.setIdObjeto(idObjeto);
            log.setDataHora(new Date(new java.util.Date().getTime()));
            log.setUsuario(u);
            daoLog.criaObjeto(log);

            mv.addObject("msg", "Comentario cadastrado com sucesso!");
        } else {
            mv.addObject("msg", "Erro: SEM ACESSO!");
            mv = new ModelAndView("redirect: Buscalogin.html");
        }
        return mv;
    }


}
