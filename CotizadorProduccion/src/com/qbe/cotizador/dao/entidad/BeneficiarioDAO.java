package com.qbe.cotizador.dao.entidad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Beneficiario;

public class BeneficiarioDAO extends EntityManagerFactoryDAO<Beneficiario>{
	
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
	
	public BeneficiarioDAO() {
        super(Beneficiario.class);
    }
	
	public List<Beneficiario> buscarTodos(){
		return getEntityManager().createNamedQuery("Beneficiario.buscarTodos").getResultList();		
	}
		
	public Beneficiario buscarPorId(String id){
		Beneficiario beneficiario = new Beneficiario();
		List<Beneficiario> query = getEntityManager().createNamedQuery("Beneficiario.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			beneficiario =  query.get(0);
		return beneficiario;		
	}
	
//	public Beneficiario buscarPorEntidadId(Entidad entidad){
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
//		Beneficiario Beneficiario = new Beneficiario();
//		try{
//			em.getTransaction().begin();
//			TypedQuery<Beneficiario> query = em.createQuery("SELECT c FROM Beneficiario c where c.entidad = :id ", Beneficiario.class).setParameter("id", entidad);
//			List <Beneficiario>results = query.getResultList();
//			if(results.size()>0)
//				Beneficiario= results.get(0);
//		
//			return Beneficiario;
//		}catch(Exception e) { 
//	        em.getTransaction().rollback();
//	        System.out.println(e.getMessage());
//	        return Beneficiario;
//	    }finally{         
//	        em.close(); 	        
//        }
//	}
	
	public List<Beneficiario> buscarActivos(){
		return getEntityManager().createNamedQuery("Beneficiario.buscarActivos").setParameter("valorActivo", true).getResultList();		
	}	
	
}
