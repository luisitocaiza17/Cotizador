package com.qbe.cotizador.dao.seguridad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.DetalleProducto;
import com.qbe.cotizador.model.Session;
import com.qbe.cotizador.model.Usuario;

public class SessionDAO extends EntityManagerFactoryDAO<Session> {

	@PersistenceContext(name = "CotizadorWebPC", unitName = "CotizadorWebPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		if (em == null) {
			Context initCtx = null;
			try {
				initCtx = new InitialContext();
				em = (javax.persistence.EntityManager) initCtx.lookup("java:comp/env/CotizadorWebPC");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return em;
	}

	public SessionDAO() {
		super(Session.class);
	}

	public List<Session> buscarTodos() {
		return getEntityManager().createNamedQuery("Session.buscarTodos").getResultList();
	}

	public Session buscarPorId(String id) {
		Session Session = new Session();
		List<Session> query = getEntityManager().createNamedQuery("Session.buscarPorId").setParameter("id", id)
				.getResultList();
		if (!query.isEmpty())
			Session = query.get(0);
		return Session;
	}

	public Session buscarPorToken(String token) {
		Session Session = new Session();
		List<Session> query = getEntityManager().createNamedQuery("Session.buscarPorToken").setParameter("token", token)
				.getResultList();
		if (!query.isEmpty())
			Session = query.get(0);
		return Session;
	}

	public Session buscarPorUsuario(Usuario usuario) {
		Session Session = new Session();
		List<Session> query = getEntityManager().createNamedQuery("Session.buscarPorUsuario")
				.setParameter("usuario", usuario).getResultList();
		if (!query.isEmpty())
			Session = query.get(0);
		return Session;
	}
	
	public int eliminarPorUsuario(Usuario usuario){		
		int resultado=0;
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			resultado = getEntityManager().createQuery("DELETE FROM Session c where c.usuario= :usuario",DetalleProducto.class).setParameter("usuario", usuario).executeUpdate();
			ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			    
		}		
		return resultado;		
	}
}
