package com.qbe.cotizador.dao.cotizacion;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.TipoObjeto;


public class CotizacionDetalleDAO extends EntityManagerFactoryDAO<CotizacionDetalle>{

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
	
	public CotizacionDetalleDAO() {
        super(CotizacionDetalle.class);
    }
	
	public List<CotizacionDetalle> buscarTodos(){
		return getEntityManager().createNamedQuery("CotizacionDetalle.buscarTodos").getResultList();
	}
	
	public CotizacionDetalle buscarPorId(String id){
		CotizacionDetalle cotizacion = new CotizacionDetalle();
		List<CotizacionDetalle> query = getEntityManager().createNamedQuery("CotizacionDetalle.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			cotizacion =  query.get(0);
			
		return cotizacion;		
	}
	
	
//	public List<CotizacionDetalle> buscarActivos(){
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
//		List<CotizacionDetalle> results = null;
//		try{	
//			TypedQuery<CotizacionDetalle> query = em.createQuery("SELECT c FROM CotizacionDetalle c WHERE c.activo =:valorActivo", CotizacionDetalle.class).setParameter("valorActivo", true);
//			results = query.getResultList(); 
//		}catch(Exception e) { 
//			 System.out.println(e.getMessage());
//			em.getTransaction().rollback();           
//		}finally{         
//			em.close();			
//		}
//		return results;
//	}
	
	public List<CotizacionDetalle> buscarCotizacionDetallePorCotizacion(Cotizacion cotizacion){
		return getEntityManager().createNamedQuery("CotizacionDetalle.buscarCotizacionDetallePorCotizacion").setParameter("cotizacion", cotizacion).getResultList();
	}
	
	public String buscarCotizacionDetallePorObjetoId(String objetoId){
		String cotizacionDetalleId = "";
		List<CotizacionDetalle> query = getEntityManager().createNamedQuery("CotizacionDetalle.buscarCotizacionesDetallePorObjetoId").setParameter("objetoId", objetoId).getResultList();
		if(!query.isEmpty())
			cotizacionDetalleId = query.get(0).getId();
		return cotizacionDetalleId;
	}
	

	public List<CotizacionDetalle> buscarCotizacionesDetallePorObjetoId(String objetoId){
		return getEntityManager().createNamedQuery("CotizacionDetalle.buscarCotizacionesDetallePorObjetoId").setParameter("objetoId", objetoId).getResultList();
	}
	
	public List<CotizacionDetalle> buscarCotizacionesDetallePorObjetoIdTipoObjeto(String objetoId,List<String> tipoObjetoId){
		return getEntityManager().createNamedQuery("CotizacionDetalle.buscarCotizacionesDetallePorObjetoIdTipoObjeto").setParameter("objetoId", objetoId).setParameter("tipoObjetoId", tipoObjetoId).getResultList();
	}
	
	public CotizacionDetalle buscarCotizacionDetalleIdYObjetoId(String objetoId, Cotizacion cotizacion){
		CotizacionDetalle cotizacionDetalle = new CotizacionDetalle();
		List<CotizacionDetalle> query = getEntityManager().createNamedQuery("CotizacionDetalle.buscarCotizacionDetalleIdYObjetoId").setParameter("objetoId", objetoId).setParameter("cotizacion", cotizacion).getResultList();
		if(!query.isEmpty())
			cotizacionDetalle =  query.get(0);
		return cotizacionDetalle;
	}
	
}
