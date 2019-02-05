package com.qbe.cotizador.dao.cotizacion;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Cobertura;
import com.qbe.cotizador.model.CotizacionCobertura;
import com.qbe.cotizador.model.CotizacionDetalle;

public class CotizacionCoberturaDAO extends EntityManagerFactoryDAO<CotizacionCobertura>{
	
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
	
	public CotizacionCoberturaDAO() {
        super(CotizacionCobertura.class);
    }
		
	public List<CotizacionCobertura> buscarTodos(){
		return getEntityManager().createNamedQuery("CotizacionCobertura.buscarTodos").getResultList();
	}
	
	public CotizacionCobertura buscarPorId(String id){
		CotizacionCobertura cotizacion = new CotizacionCobertura();
		List<CotizacionCobertura> query = getEntityManager().createNamedQuery("CotizacionCobertura.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			cotizacion =  query.get(0);
		return cotizacion;
	}
	
	
//	public List<CotizacionCobertura> buscarActivos(){
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
//		List<CotizacionCobertura> results = null;
//		try{	
//			TypedQuery<CotizacionCobertura> query = em.createQuery("SELECT c FROM CotizacionCobertura c WHERE c.activo =:valorActivo", CotizacionCobertura.class).setParameter("valorActivo", true);
//			results = query.getResultList(); 
//		}catch(Exception e) { 
//			em.getTransaction().rollback();           
//		}finally{         
//			em.close(); 			
//		}
//		return results;
//	}
	
	public List<CotizacionCobertura> buscarCotizacionCoberturaPorCotizacionDetalle(CotizacionDetalle cotizacionDetalle,String tipoObjetoId){
		return getEntityManager().createNamedQuery("CotizacionCobertura.buscarCotizacionCoberturaPorCotizacionDetalle").setParameter("cotizacionDetalle", cotizacionDetalle).getResultList();
	}
	
	public List<CotizacionCobertura> buscarPorCotizacionDetalle(CotizacionDetalle cotizacionDetalle){
		return getEntityManager().createNamedQuery("CotizacionCobertura.buscarPorCotizacionDetalle").setParameter("cotizacionDetalle", cotizacionDetalle).getResultList();
	}
	
	public List<CotizacionCobertura> buscarPorCotizacionDetalleIdCoberturas(CotizacionDetalle cotizacionDetalle, List<Cobertura> ids){//'id','id2'
		return getEntityManager().createNamedQuery("CotizacionCobertura.buscarPorCotizacionDetalleIdCoberturas").setParameter("cotizacionDetalle", cotizacionDetalle).setParameter("ids", ids).getResultList();		
	}
	
	public void eliminarPorCotizacionDetalle(CotizacionDetalle cotizacionDetalle){
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			getEntityManager().createQuery("DELETE FROM CotizacionCobertura c WHERE c.cotizacionDetalle =:cotizacionDetalle",CotizacionCobertura.class).setParameter("cotizacionDetalle", cotizacionDetalle).executeUpdate();
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

}
