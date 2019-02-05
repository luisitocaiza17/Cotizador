package com.qbe.cotizador.dao.cotizacion;

import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.GrupoCobertura;
import com.qbe.cotizador.model.Ramo;

public class GrupoCoberturaDAO extends EntityManagerFactoryDAO<GrupoCobertura>{

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
	
	public GrupoCoberturaDAO() {
        super(GrupoCobertura.class);
    }
	
	public List<GrupoCobertura> buscarTodos(){
		return getEntityManager().createNamedQuery("GrupoCobertura.buscarTodos").getResultList();		
	}
		
	public GrupoCobertura buscarPorId(String id){
		GrupoCobertura grupo = new GrupoCobertura();
		List<GrupoCobertura> query = getEntityManager().createNamedQuery("GrupoCobertura.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			grupo =  query.get(0);
		return grupo;
	}

//	public GrupoCobertura buscarPorNemotecnico(String nemotecnico){
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
//		GrupoCobertura cobertura = new GrupoCobertura();
//		try{
//			TypedQuery<GrupoCobertura> query = em.createQuery("SELECT c FROM GrupoCobertura c where c.nemotecnico = :nemotecnico", GrupoCobertura.class).setParameter("nemotecnico", nemotecnico);
//			List <GrupoCobertura>results = query.getResultList();
//			
//			cobertura = results.get(0);
//			return cobertura;
//		}catch(Exception e) { 
//	        em.getTransaction().rollback();
//	        System.out.println(e.getMessage());
//	        return cobertura;
//		}    finally{         
//	        em.close(); 	        
//        }
//	}
//	public List<GrupoCobertura> buscarActivos(){
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
//		List<GrupoCobertura> results = null;
//		try{	
//			TypedQuery<GrupoCobertura> query = em.createQuery("SELECT c FROM GrupoCobertura c WHERE c.activo =:valorActivo", GrupoCobertura.class).setParameter("valorActivo", true);
//			results = query.getResultList(); 
//		}catch(Exception e) { 
//			em.getTransaction().rollback();           
//		}finally{         
//			em.close();			
//		}
//		return results;
//	}
	
	public List<GrupoCobertura> buscarGrupoCoberturasPorProductoNemotecnico(String productoNemotecnico){			
    	List<GrupoCobertura> results = new ArrayList<GrupoCobertura>();    
    	TypedQuery<GrupoCobertura> query = getEntityManager().createQuery("SELECT c FROM GrupoCobertura c join c.productos p WHERE p.nemotecnico = :nemotecnico", GrupoCobertura.class).setParameter("nemotecnico", productoNemotecnico);    	
    	results = query.getResultList(); 	    
      	return results;
    }
	
	public List<GrupoCobertura> buscarPorRamoId(Ramo ramo){			
    	List<GrupoCobertura> results = new ArrayList<GrupoCobertura>();    
    	TypedQuery<GrupoCobertura> query = getEntityManager().createQuery("SELECT c FROM GrupoCobertura c where c.ramo = :ramo", GrupoCobertura.class).setParameter("ramo", ramo);    	
    	results = query.getResultList(); 	    
      	return results;
    }
	
//	public List<Object[]> buscarGrupoCoberturasPorPaquete(){		
//		List<Object[]> result=  new ArrayList<Object[]>();    	
//    		result = getEntityManager().createNativeQuery("select * from PAQUETE_COBERTURA").getResultList();			    	
//      	return result;
//    }
}
