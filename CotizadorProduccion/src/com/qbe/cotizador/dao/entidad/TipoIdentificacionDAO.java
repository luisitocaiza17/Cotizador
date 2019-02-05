package com.qbe.cotizador.dao.entidad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.TipoIdentificacion;


public class TipoIdentificacionDAO extends EntityManagerFactoryDAO<TipoIdentificacion>{
	
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
	
	public TipoIdentificacionDAO() {
        super(TipoIdentificacion.class);
    } 
	
	public List<TipoIdentificacion> buscarTodos(){
		return getEntityManager().createNamedQuery("TipoIdentificacion.buscarTodos").getResultList();	
	}
		
	public TipoIdentificacion buscarPorId(String id){
		TipoIdentificacion tipo = new TipoIdentificacion();
		List<TipoIdentificacion> query = getEntityManager().createNamedQuery("TipoIdentificacion.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);
		return tipo;			
	}
		
//	public List<TipoIdentificacion> buscarActivos(){		
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
//		List<TipoIdentificacion> results = null;
//		try{	
//			TypedQuery<TipoIdentificacion> query = em.createQuery("SELECT c FROM TipoIdentificacion c WHERE c.activo =:valorActivo", TipoIdentificacion.class).setParameter("valorActivo", true);
//			results = query.getResultList(); 
//		}catch(Exception e) { 
//			em.getTransaction().rollback();           
//		}finally{         
//			em.close();
//		}
//		return results;
//	}		
}
	