package com.qbe.cotizador.dao.inspeccion;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Inspector;
import com.qbe.cotizador.model.TipoInspector;
import com.qbe.cotizador.model.Usuario;

public class InspectorDAO extends EntityManagerFactoryDAO<Inspector>{
	
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
	
	public InspectorDAO() {
        super(Inspector.class);
    }
	
	public List<Inspector> buscarTodos(){ 
		return getEntityManager().createNamedQuery("Inspector.buscarTodos").getResultList();
	}

	public Inspector buscarPorId(String id){
		Inspector inspector = new Inspector();
		List<Inspector> query = getEntityManager().createNamedQuery("Inspector.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			inspector =  query.get(0);			
		return inspector;
	}
	
//	public Inspector buscarPorEntidadId(Entidad entidad){
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
//		Inspector inspector = new Inspector();
//		try{
//			TypedQuery<Inspector> query = em.createQuery("SELECT c FROM Inspector c where c.entidad = :id ", Inspector.class).setParameter("id", entidad);
//			List <Inspector>results = query.getResultList();
//			inspector = results.get(0);
//			return inspector;
//		}catch(Exception e) { 
//	        em.getTransaction().rollback();
//	        System.out.println(e.getMessage());
//	        return inspector;
//	    }finally{         
//	        em.close();
//        }
//	}
	
//	public List<Inspector> buscarActivos(){
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
//		
//		List<Inspector> results = null;
//		try{	
//			TypedQuery<Inspector> query = em.createQuery("SELECT c FROM Inspector c WHERE c.activo =:valorActivo", Inspector.class).setParameter("valorActivo", true);
//			results = query.getResultList(); 
//		}catch(Exception e) { 
//			em.getTransaction().rollback();           
//		}finally{         
//			em.close();
//		}
//		return results;
//	}
	
	public List<Inspector> buscarPorTipo(TipoInspector tipoInspector){
    	return getEntityManager().createNamedQuery("Inspector.buscarPorTipo").setParameter("tipoInspector", tipoInspector).getResultList();
	}
	
	public Inspector buscarPorUsuario(Usuario usuario){
		Inspector inspector = new Inspector();
		List<Inspector> query = getEntityManager().createNamedQuery("Inspector.buscarPorUsuario").setParameter("usuario", usuario).getResultList();
		if(!query.isEmpty())
			inspector =  query.get(0);
		return inspector;
	}

}
