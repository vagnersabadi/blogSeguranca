package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.model.Comentario;
import br.ufsm.csi.seguranca.model.Post;
import br.ufsm.csi.seguranca.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Map;

/**
 * Created by vagner on 03/07/17.
 */

@Controller
public class RefreshGet {

    @RequestMapping(value = "cadastro-usuario.html", method = RequestMethod.GET)
    public ModelAndView carrega_Usuario(Map<String, Object> model) throws Exception {
        ModelAndView mv = new ModelAndView("redirect: Buscalogin.html");
        //model.put("usuarios", new Usuario());
        return mv;
    }

    @RequestMapping(value = "alterarPost.html", method = RequestMethod.GET)
    public ModelAndView alterar_Post(HttpServletRequest request) {

        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView mv = new ModelAndView("login");
        if (u != null) {
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

    @RequestMapping(value = "deletaPost.html", method = RequestMethod.GET)
    public ModelAndView deleta_Post(HttpServletRequest request) {

        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView mv = new ModelAndView("login");
        if (u != null) {

            if (u.getTipoUsuario() == 1 ) {
                mv = new ModelAndView("paginaAdm");
            } else if (u.getTipoUsuario() == 2) {
                mv = new ModelAndView("paginaUser");
            }
        } else {
            mv = new ModelAndView("redirect: Buscalogin.html");
        }
        return mv;
    }

    @RequestMapping(value = "verPost.html", method = RequestMethod.GET)
    public ModelAndView verPost(HttpServletRequest request) {

        Usuario u = (Usuario) request.getSession().getAttribute("usuario");
        ModelAndView mv = new ModelAndView("login");
        if (u != null) {

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

    @RequestMapping(value = "cadastro-post.html", method = RequestMethod.GET)
    public String carregaUsuario(Long id, Map<String, Object> model) throws Exception {

        if (id == null) {
            model.put("posts", new Usuario());
        } else {
            Post postPersiste = new Post();
            model.put("posts", postPersiste);
        }
        return "cadastro-post";
    }

    @RequestMapping(value = "cadastro-comentario.html", method = RequestMethod.GET)
    public String carregaUsuario(Map<String, Object> model) throws Exception {
        model.put("comentarios", new Comentario());
        return "cadastro-comentario";
    }
}
