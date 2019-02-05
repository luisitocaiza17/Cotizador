package com.qbe.cotizador.dao.entidad;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.model.UsuarioXPuntoVenta;

public class UsuarioXPuntoVentaDAO extends EntityManagerFactoryDAO<UsuarioXPuntoVenta>{	
	
	@PersistenceContext(name="CotizadorWebPC", unitName = "CotizadorWebPU" )	
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		if(em == null){
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
	
	public UsuarioXPuntoVentaDAO() {
        super(UsuarioXPuntoVenta.class);
    }
	
	public void eliminarPorUsuario(Usuario usuario){
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			getEntityManager().createQuery("DELETE FROM UsuarioXPuntoVenta c where c.usuario = :usuario ", UsuarioXPuntoVenta.class).setParameter("usuario", usuario).executeUpdate();
			  ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			    
		}			
	} 
	
	public UsuarioXPuntoVenta buscarPorId(String id){
		UsuarioXPuntoVenta usuario = new UsuarioXPuntoVenta();
		List<UsuarioXPuntoVenta> query = getEntityManager().createNamedQuery("UsuarioXPuntoVenta.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			usuario =  query.get(0);
		return usuario;
	}
	
	public UsuarioXPuntoVenta buscarPorUsuario(Usuario usuario){
		UsuarioXPuntoVenta usuarioPtoVenta = new UsuarioXPuntoVenta();
		List<UsuarioXPuntoVenta> query = getEntityManager().createNamedQuery("UsuarioXPuntoVenta.buscarPorUsuario").setParameter("usuario", usuario).getResultList();
		if(!query.isEmpty())
			usuarioPtoVenta =  query.get(0);
		return usuarioPtoVenta;
	}
	
	public List<UsuarioXPuntoVenta> buscarTodos(){   
		return getEntityManager().createNamedQuery("UsuarioXPuntoVenta.buscarTodos").getResultList();
	}
}
