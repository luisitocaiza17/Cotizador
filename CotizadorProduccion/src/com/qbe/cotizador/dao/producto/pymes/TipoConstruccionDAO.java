package com.qbe.cotizador.dao.producto.pymes;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.TipoConstruccion;

public class TipoConstruccionDAO extends EntityManagerFactoryDAO<TipoConstruccion>{
	
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

	public TipoConstruccionDAO() {
	    super(TipoConstruccion.class);
	}

	public List<TipoConstruccion> buscarTodos(){   
		return getEntityManager().createNamedQuery("TipoConstruccion.buscarTodos").getResultList();
	}
	
	public TipoConstruccion buscarPorId(String id){   
		TipoConstruccion tipo = new TipoConstruccion();
		List<TipoConstruccion> query = getEntityManager().createNamedQuery("TipoConstruccion.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);
		return tipo;
	}
	
//	public TipoConstruccion buscarPorCodigoEnsurance(String marEnsurance){   
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
//		try{
//			TypedQuery<TipoConstruccion> query = em.createQuery("SELECT c FROM TipoConstruccion c WHERE c.marEnsurance =:marEnsurance", TipoConstruccion.class).setParameter("marEnsurance", marEnsurance);
//			List <TipoConstruccion>results = query.getResultList();
//			TipoConstruccion tipoConstruccion = new TipoConstruccion();
//			if (results.size()>0 )
//			tipoConstruccion = results.get(0);
//			return tipoConstruccion;
//        }finally{         
//	        em.close();
//        }
//	}

	
//	public List<TipoConstruccion> buscarActivos(){
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
//
//		List<TipoConstruccion> results = null;
//		try{	
//			TypedQuery<TipoConstruccion> query = em.createQuery("SELECT c FROM TipoConstruccion c WHERE c.activo =:valorActivo", TipoConstruccion.class).setParameter("valorActivo", true);
//			results = query.getResultList(); 
//		}catch(Exception e) { 
//			em.getTransaction().rollback();           
//		}finally{         
//			em.close();
//		}
//		return results;
//	}
	
	public TipoConstruccion buscarPorNombre(String nombre){
		TipoConstruccion tipo = new TipoConstruccion();
		List<TipoConstruccion> query = getEntityManager().createNamedQuery("TipoConstruccion.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);			
		return tipo;
	}
}
