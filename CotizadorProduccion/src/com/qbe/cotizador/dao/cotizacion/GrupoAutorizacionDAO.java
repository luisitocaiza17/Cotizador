package com.qbe.cotizador.dao.cotizacion;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Empleado;
import com.qbe.cotizador.model.GrupoAutorizacion;
import com.qbe.cotizador.model.UnidadNegocio;

public class GrupoAutorizacionDAO extends EntityManagerFactoryDAO<GrupoAutorizacion>{

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
	
	public GrupoAutorizacionDAO() {
        super(GrupoAutorizacion.class);
    }
	
	public List<GrupoAutorizacion> buscarTodos(){
		return getEntityManager().createNamedQuery("GrupoAutorizacion.buscarTodos").getResultList();		
	}
		
	public GrupoAutorizacion buscarPorId(String id){
		GrupoAutorizacion autorizacion = new GrupoAutorizacion();
		List<GrupoAutorizacion> query = getEntityManager().createNamedQuery("GrupoAutorizacion.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			autorizacion =  query.get(0);			
		return autorizacion;
	}
	
	public GrupoAutorizacion buscarPorLider(Empleado empleado){
		GrupoAutorizacion autorizacion = new GrupoAutorizacion();
		List<GrupoAutorizacion> query = getEntityManager().createNamedQuery("GrupoAutorizacion.buscarPorLider").setParameter("empleado", empleado).getResultList();
		if(!query.isEmpty())
			autorizacion =  query.get(0);
		return autorizacion;			
	}
	
	public List<GrupoAutorizacion> buscarActivos(){
		return getEntityManager().createNamedQuery("GrupoAutorizacion.buscarActivos").setParameter("valorActivo", true).getResultList();
	}
	
//	public GrupoAutorizacion buscarPorDescuento(Descuento descuento){
//		GrupoAutorizacion grupoAutorizacion = null;
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
//
//		try{	
//			TypedQuery<GrupoAutorizacion> query = em.createQuery("SELECT c FROM GrupoAutorizacion c WHERE c.descuento =:descuento", GrupoAutorizacion.class).setParameter("descuento", descuento);
//			grupoAutorizacion = query.getResultList().get(0);
//			return grupoAutorizacion;
//		}catch(Exception e) { 
//			em.getTransaction().rollback();           
//		}finally{         
//			em.close(); 			
//		}
//		return grupoAutorizacion;
//	}
	
	public List<GrupoAutorizacion> buscarPorUnidadNegocio(UnidadNegocio unidadNegocio){
		return getEntityManager().createNamedQuery("GrupoAutorizacion.buscarPorUnidadNegocio").setParameter("unidadNegocio", unidadNegocio).getResultList();
	}	
	
}
