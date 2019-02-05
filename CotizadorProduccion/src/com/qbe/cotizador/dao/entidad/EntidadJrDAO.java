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
import com.qbe.cotizador.model.EntidadJr;

public class EntidadJrDAO extends EntityManagerFactoryDAO<EntidadJr>{
	
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
	
	public EntidadJrDAO() {
        super(EntidadJr.class);
    }
	
	public List<EntidadJr> buscarTodos(){
		return getEntityManager().createNamedQuery("EntidadJr.buscarTodos").getResultList();		
	}
		
	public EntidadJr buscarPorIdentificacion(String identificacion){
		EntidadJr entidad = new EntidadJr();
		List<EntidadJr> query = getEntityManager().createNamedQuery("EntidadJr.buscarPorIdentificacion").setParameter("identificacion", identificacion).getResultList();
		if(!query.isEmpty())
			entidad =  query.get(0);
		return entidad;
	}
		
	public EntidadJr buscarPorId(String id){
		EntidadJr entidad = new EntidadJr();
		List<EntidadJr> query = getEntityManager().createNamedQuery("EntidadJr.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			entidad =  query.get(0);
		return entidad;
	}
	
	
	public int eliminarTodos(){		
		int resultado=0;
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			resultado = getEntityManager().createQuery("DELETE FROM EntidadJr ",EntidadJr.class).executeUpdate();
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
