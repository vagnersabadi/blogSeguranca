package br.ufsm.csi.seguranca.dao;

import br.ufsm.csi.seguranca.model.Comentario;
import br.ufsm.csi.seguranca.model.Log;
import br.ufsm.csi.seguranca.model.Post;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;


@Repository
public class LogDAO {
//Hibernate bloqueia injeções usando PreparedStatement do Criteria

	@Autowired
	private SessionFactory sessionFactory;

	public Object carregaObjeto(Class classe, Serializable id) {
		return sessionFactory.getCurrentSession().get(classe, id);
	}

	public void criaObjeto(Object o) {
		sessionFactory.getCurrentSession().save(o);
	}

	public void removeObjeto(Object o) {
		sessionFactory.getCurrentSession().remove(o);
	}
}
