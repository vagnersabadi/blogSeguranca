package br.ufsm.csi.seguranca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;

import br.ufsm.csi.seguranca.model.Post;
import br.ufsm.csi.seguranca.model.Usuario;

@Repository
public class UsuarioDAO {

    @Autowired
    private SessionFactory sessionFactory;

    //inserir novo usuario
    @Transactional
    public void inserirUsuario(Usuario usuario) throws Exception {
        sessionFactory.getCurrentSession().save(usuario);
    }

    //faz login recebendo nome
    @Transactional
    public Usuario login(String login) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Usuario.class);
        crit.add(Restrictions.eq("loginUsuario", login));
        return (Usuario) crit.uniqueResult();
    }

    //busca todos usuarios
    @Transactional
    public Collection<Usuario> getUsuarios() throws SQLException {
        return sessionFactory.getCurrentSession().createCriteria(Usuario.class).list();
    }

    //busca usuario pelo id
    @Transactional
    public Usuario buscarUsuarioPeloId(Long id) throws Exception {
        return (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, id);
    }


}