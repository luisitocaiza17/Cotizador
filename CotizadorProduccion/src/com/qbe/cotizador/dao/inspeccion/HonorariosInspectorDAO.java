package com.qbe.cotizador.dao.inspeccion;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.HonorariosInspector;
import com.qbe.cotizador.model.Inspector;
import com.qbe.cotizador.model.Zona;

public class HonorariosInspectorDAO extends EntityManagerFactoryDAO<HonorariosInspector>{
	
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
	
	public HonorariosInspectorDAO() {
        super(HonorariosInspector.class);
    }
	
	public List<HonorariosInspector> buscarTodos(){ 
		return getEntityManager().createNamedQuery("HonorariosInspector.buscarTodos").getResultList();
	}
	
	public HonorariosInspector buscarPorId(String id){
		HonorariosInspector inspector = new HonorariosInspector();
		List<HonorariosInspector> query = getEntityManager().createNamedQuery("HonorariosInspector.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			inspector =  query.get(0);		
		return inspector;
	}
	
//	public HonorariosInspector buscarPorEntidadId(Entidad entidad){
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
//		HonorariosInspector honorariosInspector = new HonorariosInspector();
//		try{
//			TypedQuery<HonorariosInspector> query = em.createQuery("SELECT c FROM HonorariosInspector c where c.entidad = :id ", HonorariosInspector.class).setParameter("id", entidad);
//			List <HonorariosInspector>results = query.getResultList();
//			honorariosInspector = results.get(0);
//			return honorariosInspector;
//		}catch(Exception e) { 
//	        em.getTransaction().rollback();
//	        System.out.println(e.getMessage());
//	        return honorariosInspector;
//	    }finally{         
//	        em.close();
//        }
//	}
	
//	public List<HonorariosInspector> buscarActivos(){
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
//		
//		List<HonorariosInspector> results = null;
//		try{	
//			TypedQuery<HonorariosInspector> query = em.createQuery("SELECT c FROM HonorariosInspector c WHERE c.activo =:valorActivo", HonorariosInspector.class).setParameter("valorActivo", true);
//			results = query.getResultList(); 
//		}catch(Exception e) { 
//			em.getTransaction().rollback();           
//		}finally{         
//			em.close();
//		}
//		return results;
//	}
	
	public HonorariosInspector buscarPorInspectorZonaTipoObjeto(Inspector inspector,Zona zona,String tipoObjeto){
		HonorariosInspector honorario = new HonorariosInspector();
		List<HonorariosInspector> query = getEntityManager().createNamedQuery("HonorariosInspector.buscarPorInspectorZonaTipoObjeto").setParameter("inspector", inspector).setParameter("zona", zona).setParameter("tipoObjeto", tipoObjeto).getResultList();
		if(!query.isEmpty())
			honorario =  query.get(0);
		return honorario;
	}
}
