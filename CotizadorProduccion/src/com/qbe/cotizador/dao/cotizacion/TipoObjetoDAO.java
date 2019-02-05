package com.qbe.cotizador.dao.cotizacion;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.TipoObjeto;

public class TipoObjetoDAO extends EntityManagerFactoryDAO<TipoObjeto>{

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
	
	public TipoObjetoDAO() {
        super(TipoObjeto.class);
    }
		
	public List<TipoObjeto> buscarTodos(){
		return getEntityManager().createNamedQuery("TipoObjeto.buscarTodos").getResultList();		
	}
		
	public TipoObjeto buscarPorId(String id){
		TipoObjeto tipo = new TipoObjeto();
		List<TipoObjeto> query = getEntityManager().createNamedQuery("TipoObjeto.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);
		return tipo;
	}
	
//	public List<TipoObjeto> buscarActivos(){				
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
//		List<TipoObjeto> results = null;
//		try{	
//			TypedQuery<TipoObjeto> query = em.createQuery("SELECT c FROM TipoObjeto c WHERE c.activo =:valorActivo", TipoObjeto.class).setParameter("valorActivo", true);
//			results = query.getResultList(); 
//		}catch(Exception e) { 
//			em.getTransaction().rollback();           
//		}finally{         
//			em.close();			
//		}
//		return results;
//	}
	    
    public TipoObjeto buscarPorNombre(String nombre){
    	TipoObjeto tipo = new TipoObjeto();
		List<TipoObjeto> query = getEntityManager().createNamedQuery("TipoObjeto.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);
		return tipo;    	
      }
}
