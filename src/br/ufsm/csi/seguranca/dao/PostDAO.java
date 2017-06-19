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

@Repository
public class PostDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void inserirPost(Post post)throws Exception{		
		sessionFactory.getCurrentSession().save(post);
	}
	
	@Transactional
	public void alterarPost(Post post) throws Exception{
		sessionFactory.getCurrentSession().update(post);
	}
	
	@Transactional
	public void excluirPost(Post post) throws Exception{
		sessionFactory.getCurrentSession().delete(post);
	}
	
	@Transactional
	public Collection<Post> getPosts() throws SQLException{
		return sessionFactory.getCurrentSession().createCriteria(Post.class).list();
	}

	@Transactional
	public Collection<Post> getPosts10() throws SQLException{
		return sessionFactory.getCurrentSession().createCriteria(Post.class).addOrder(Order.desc("idPost")).setMaxResults(10).list();
	}
	
	@Transactional
	public Collection<Post> getPostsUsuario(Usuario usuario) throws SQLException{
		return sessionFactory.getCurrentSession().createCriteria(Post.class).add(Restrictions.eq("usuario", usuario)).list();
	}
	
	@Transactional
	public Post getPostId(Long id) throws Exception{
		return (Post) sessionFactory.getCurrentSession().get(Post.class, id);
	}

}
