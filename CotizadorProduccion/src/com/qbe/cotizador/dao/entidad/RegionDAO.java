package com.qbe.cotizador.dao.entidad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Region;

public class RegionDAO extends EntityManagerFactoryDAO<Region>{
	
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
	
	public RegionDAO() {
        super(Region.class);
    }
	
	public List<Region> buscarTodos(){
		return getEntityManager().createNamedQuery("Region.buscarTodos").getResultList();		
	}
		
	public Region buscarPorId(String id){
		Region region = new Region();
		List<Region> query = getEntityManager().createNamedQuery("Region.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			region =  query.get(0);
		return region;
	}
	
	public Region buscarPorNombre(String nombre){
		Region region = new Region();
		List<Region> query = getEntityManager().createNamedQuery("Region.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			region =  query.get(0);
		return region;
	}
	
//	public List<Region> buscarActivos(){		
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
//		List<Region> results = null;
//		try{	
//			TypedQuery<Region> query = em.createQuery("SELECT c FROM Region c WHERE c.activo =:valorActivo", Region.class).setParameter("valorActivo", true);
//			results = query.getResultList(); 
//		}catch(Exception e) { 
//			em.getTransaction().rollback();           
//		}finally{         
//			em.close();
//		}
//		return results;
//	}
}
