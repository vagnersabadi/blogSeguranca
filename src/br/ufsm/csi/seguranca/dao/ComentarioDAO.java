package br.ufsm.csi.seguranca.dao;

import java.sql.SQLException;
import java.util.Collection;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.ufsm.csi.seguranca.model.Post;
import br.ufsm.csi.seguranca.model.Usuario;
import br.ufsm.csi.seguranca.model.Comentario;;

@Repository
public class ComentarioDAO {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public Collection<Comentario> getComentariosPost(Post post) throws SQLException{
		return sessionFactory.getCurrentSession().createCriteria(Comentario.class).add(Restrictions.eq("post", post)).list();
	}
	
	@Transactional
	public void inserirComentario(Comentario comentario)throws Exception{		
		sessionFactory.getCurrentSession().save(comentario);
	}
	
}
