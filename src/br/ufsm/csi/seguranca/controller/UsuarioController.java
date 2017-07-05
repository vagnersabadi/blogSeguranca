package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.PostDAO;
import br.ufsm.csi.seguranca.dao.UsuarioDAO;
import br.ufsm.csi.seguranca.model.Post;
import br.ufsm.csi.seguranca.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;


/**
 * Created by cpol on 29/05/2017.
 */
@Controller
public class UsuarioController {

    @Autowired
    private UsuarioDAO dao;

    @Autowired
    private PostDAO daoPost;

    @RequestMapping("Buscalogin.html")
    public ModelAndView Busca_login(HttpServletRequest request, Map<String, Object> model) throws SQLException {
        //redireciona para pagina login
        ModelAndView mv = new ModelAndView("login");
        Collection<Post> posts = daoPost.getPosts10();
        model.put("posts", posts);
        return mv;
    }

    //LOGIN
    @RequestMapping("login.html")
    @Transactional
    //recebe do formulario nome, senha
    public ModelAndView acesso_Sitema(String login, String senha, HttpServletRequest request, Map<String, Object> model)
            throws SQLException {

        request.getSession().invalidate();

        // view LOGIN
        ModelAndView mv = new ModelAndView("login");
        //coleção de posts
        Collection<Post> posts = daoPost.getPosts10();
        model.put("posts", posts);

        BASE64Decoder decoder = new BASE64Decoder();
        byte[] senha_crip = null;
        byte[] crip = null;


        try {
            //usando espape para tratar entradas
            Usuario u = dao.login((HtmlUtils.htmlEscape(login)));
            if (u != null) {

                //criptgrafa senha
                senha_crip = decoder.decodeBuffer(HtmlUtils.htmlEscape(senha));
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                crip = md.digest(senha_crip);
                senha = new sun.misc.BASE64Encoder().encode(crip);

                //compara as senhas do banco com a do form
                if (senha.equals(u.getSenhaUsuario())) {
                    //seta testativa 0
                    u.setTentativas(0);
                    //verifica se é um usuario ativo
                    if (u.isAtivo()) {
                        request.getSession().setAttribute("usuario", u);
                        //se for tipo 1 é adm
                        if (u.getTipoUsuario() == 1) {
                            System.out.println("Entro em: paginaAdm ");
                            mv = new ModelAndView("paginaAdm");
                        }
                        //se for tipo 2 e user normal
                        else if (u.getTipoUsuario() == 2) {
                            System.out.println("Entro em: paginaUser");
                            mv = new ModelAndView("paginaUser");
                        }
                        //senão apresenta erro que nao existe este tipo e user
                        else {
                            System.out.println("ERRO: tipo user");
                            mv = new ModelAndView("erro");
                        }
                    }
                    //erro realizar login não é ativo
                    else {
                        System.out.println("Erro ao realizar login: Usuario Desativado");
                        mv.addObject("msg", "Erro ao realizar login: Usuario Desativado");
                    }
                }
                //erro realizar login senhas não são iguais
                else {
                    System.out.println("Erro login senha !=");
                    //ferifica se a contagem nao e mais que 5 tentativas para bloquear user
                    if (u.getTentativas() > 2) {
                        u.setAtivo(false);
                        mv.addObject("msg", "Erro ao realizar login!");
                    }
                    //incrementa tentativas
                    else {
                        u.setTentativas(u.getTentativas() + 1);
                        mv.addObject("msg", "Erro ao realizar login!");
                    }
                }
            }
            //erro realizar login user nulo
            else {
                mv.addObject("msg", "Erro ao realizar login!");
            }

        } catch (Exception e) {
        }

        //retornou pois campos são nulos
        System.out.println("Retornou");
        return mv;
    }

    //botão logout
    @RequestMapping("logout.html")
    public ModelAndView sair(HttpServletRequest request, Map<String, Object> model) throws SQLException {
        //redireciona para pagina login e invalida a sessão
        ModelAndView mv = new ModelAndView("login");
        request.getSession().invalidate();
        //carregar 10 ultmos posts
        Collection<Post> posts = daoPost.getPosts10();
        model.put("posts", posts);
        return mv;
    }

    //somente user admin pode buscar
    @RequestMapping("usuarios.html")
    public ModelAndView busca_Pacientes(HttpServletRequest request) {
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView mv;
        if (u != null) {
            if (u.getTipoUsuario() == 1) {  //administrador
                mv = new ModelAndView("usuarios");
            } else {  // comum ou outro
                mv = new ModelAndView("erro");
            }
        } else {
            mv = new ModelAndView("redirect: Buscalogin.html");
        }
        return mv;
    }

    //somente user admin pode gerenciar
    @RequestMapping("GerenciaUsuariosVer.html")
    public ModelAndView pagina_Usuarios_Ver(HttpServletRequest request, Map<String, Object> model) throws SQLException {
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        //vai para pagina ususuarios
        ModelAndView mv = new ModelAndView("usuarios");
        Collection<Usuario> usuarios = dao.getUsuarios();
        model.put("usuariosCadastrados", usuarios);
        if (u != null) {
            if (u.getTipoUsuario() == 1) {//verifica se é adm contiua
                mv = new ModelAndView("usuarios");
            } else if (u.getTipoUsuario() == 2) {//se for user normal apresenta msgm erro
                mv = new ModelAndView("paginaUser");
                mv.addObject("msg", "ERRO: Esta Função somente pode ser executada pelo Adm");
            }
        } else {
            mv = new ModelAndView("redirect: Buscalogin.html");
        }
        model.put("usuarios", new Usuario());
        return mv;
    }

    //somente user admin pode gerenciar
    @RequestMapping("GerenciaUsuariosCriar.html")
    public ModelAndView pagina_Usuarios(HttpServletRequest request, Map<String, Object> model) throws SQLException {
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        //vai para pagina ususuarios
        ModelAndView mv = new ModelAndView("usuariosCriar");
        Collection<Usuario> usuarios = dao.getUsuarios();
        model.put("usuariosCadastrados", usuarios);
        if (u != null) {
            if (u.getTipoUsuario() == 1) {//verifica se é adm contiua
                mv = new ModelAndView("usuariosCriar");
            } else if (u.getTipoUsuario() == 2) {//se for user normal apresenta msgm erro
                mv = new ModelAndView("paginaUser");
                mv.addObject("msg", "ERRO: TAREFA DO ADMINISTRADOR!");
            }
        } else {
            mv = new ModelAndView("redirect: Buscalogin.html");
        }
        model.put("usuarios", new Usuario());
        return mv;
    }

    //cadastro novo user
    @RequestMapping(value = "cadastro-usuario.html", method = RequestMethod.POST)
    @Transactional
    public ModelAndView cadastro_usuario(Usuario usuario, HttpServletRequest request) throws Exception {
        //ModelAndView mv = new ModelAndView("redirect: GerenciaUsuarios.html");
        ModelAndView mv = new ModelAndView("paginaAdm");
        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        if (u != null) {
            if (u.getTipoUsuario() == 1) {//se for adm cadastra

                BASE64Decoder decoder = new BASE64Decoder();
                byte[] senha2 = null;
                byte[] crip = null;
                MessageDigest md;


                senha2 = decoder.decodeBuffer(usuario.getSenhaUsuario());
                md = MessageDigest.getInstance("SHA-256");
                crip = md.digest(senha2);
                usuario.setSenhaUsuario(new sun.misc.BASE64Encoder().encode(crip));

                usuario.setAtivo(true);
                usuario.setTentativas(0);
                usuario.setTipoUsuario(2);
                dao.inserirUsuario(usuario);

                //mv.addObject("msg", "Usuario cadastrado com sucesso!");
            } else {//se for user normal erro
                mv = new ModelAndView("erro");
                mv.addObject("msg", "Erro: SEM ACESSO!");
            }
        } else {
            mv = new ModelAndView("redirect: Buscalogin.html");
        }
        return mv;
    }

    //ativar e desativar usuario cadastrado
    @RequestMapping(value = "ativaUsuario.html", method = RequestMethod.POST)
    @Transactional
    public ModelAndView ativa_desativa_Usuario(String id, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("redirect: GerenciaUsuariosVer.html");
        Usuario us = (Usuario) request.getSession().getAttribute("usuario");
        Usuario u = dao.buscarUsuarioPeloId(Long.valueOf(HtmlUtils.htmlEscape(id)));//faz busca do usuario pelo id e escape
        if (u != null) {
            if (us.getTipoUsuario() == 1) {
                if (u.isAtivo()) {
                    u.setAtivo(false);//troca para desativado
                } else {
                    u.setAtivo(true);//troca apra ativo
                }
            } else {
                mv = new ModelAndView("erro");
                mv.addObject("msg", "Erro: SEM ACESSO!");
            }
        } else {
            mv = new ModelAndView("redirect: Buscalogin.html");
        }
        return mv;
    }

    //redirecionar paginas
    @RequestMapping("paginaAdm.html")
    public ModelAndView pagina_Adm(HttpServletRequest request) {
        Usuario us = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView mv = new ModelAndView();
        if (us != null) {

            if (us.getTipoUsuario() == 1) {
                mv = new ModelAndView("paginaAdm");
            } else {
                mv = new ModelAndView("paginaUser");
                //mv = new ModelAndView("erro");
                //mv.addObject("msg", "Erro: SEM ACESSO!");
            }
        } else {
            mv = new ModelAndView("redirect: Buscalogin.html");
        }
        return mv;
    }

    //redirecionar paginas
    @RequestMapping("voltar.html")
    public ModelAndView voltar(HttpServletRequest request) {
        Usuario us = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView mv = new ModelAndView("erro");
        if (us != null) {

            if (us.getTipoUsuario() == 1) {
                mv = new ModelAndView("paginaAdm");
                return mv;
            } else if (us.getTipoUsuario() == 2) {
                mv = new ModelAndView("paginaUser");
                return mv;
            }
        } else {
            mv = new ModelAndView("redirect: Buscalogin.html");
        }
        return mv;
    }

}
