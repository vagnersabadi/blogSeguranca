package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.model.Log;
import br.ufsm.csi.seguranca.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.Date;
import br.ufsm.csi.seguranca.dao.LogDAO;

/**
 * Created by vagner on 03/07/17.
 */
@Controller
public class LogController {

    @Autowired
    private LogDAO daoLog;

    @Transactional
    @RequestMapping("cria-log.priv")
    public String criaLog(Long idUsuario,Long idObjeto,String classe,@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") Date dataHora) throws ClassNotFoundException {
        Usuario usuario = (Usuario) daoLog.carregaObjeto(Usuario.class, idUsuario);
        Log log = new Log();
        log.setClasse(Class.forName(classe));
        log.setIdObjeto(idObjeto);
        log.setDataHora(dataHora);
        log.setUsuario(usuario);
        daoLog.criaObjeto(log);
        return "log";
    }
}
