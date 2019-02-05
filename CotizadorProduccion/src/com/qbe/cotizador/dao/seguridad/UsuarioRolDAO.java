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
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.model.UsuarioRol;

public class UsuarioRolDAO extends EntityManagerFactoryDAO<UsuarioRol>{
	
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

	public UsuarioRolDAO() {
	    super(UsuarioRol.class);
	}
		
	public UsuarioRol buscarPorUsuario(Usuario usuario){
		UsuarioRol usuarioRol = new UsuarioRol();
		List<UsuarioRol> query = getEntityManager().createNamedQuery("UsuarioRol.buscarPorUsuario").setParameter("usuario", usuario).getResultList();
		if(!query.isEmpty())
			usuarioRol =  query.get(0);
		return usuarioRol;
	}
	
	public void eliminarPorUsuario(Usuario usuario){
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			getEntityManager().createQuery("DELETE FROM UsuarioRol c where c.usuario = :usuario ", UsuarioRol.class).setParameter("usuario", usuario).executeUpdate();
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

	public UsuarioRol buscarPorId(String id){
		UsuarioRol usuarioRol = new UsuarioRol();
		List<UsuarioRol> query = getEntityManager().createNamedQuery("UsuarioRol.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			usuarioRol =  query.get(0);
		return usuarioRol;
	}
}
