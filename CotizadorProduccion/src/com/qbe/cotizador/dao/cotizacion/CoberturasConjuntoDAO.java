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
import com.qbe.cotizador.model.CoberturasConjunto;
import com.qbe.cotizador.model.Ramo;

public class CoberturasConjuntoDAO extends EntityManagerFactoryDAO<CoberturasConjunto>{
	
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
	
	public CoberturasConjuntoDAO() {
        super(CoberturasConjunto.class);
    }
	
	
	public List<CoberturasConjunto> buscarTodos(){
		return getEntityManager().createNamedQuery("CoberturasConjunto.buscarTodos").getResultList();
	}
	
	
	public CoberturasConjunto buscarPorId(String id){
		CoberturasConjunto cobertura = new CoberturasConjunto();
		List<CoberturasConjunto> query = getEntityManager().createNamedQuery("CoberturasConjunto.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			cobertura =  query.get(0);			
		return cobertura;
	}
	
	public CoberturasConjunto buscarPorCobertura(Cobertura cobertura){
		CoberturasConjunto coberturaConjunto = new CoberturasConjunto();
		List<CoberturasConjunto> query = getEntityManager().createNamedQuery("CoberturasConjunto.buscarPorCobertura").setParameter("cobertura", cobertura).getResultList();
		if(!query.isEmpty())
			coberturaConjunto =  query.get(0);
		return coberturaConjunto;
	}
	
    public int eliminarTodos(){     
      int resultado=0;
      UserTransaction ut = null;
      try{
          ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
          ut.begin();
          resultado = getEntityManager().createQuery("DELETE FROM CoberturasConjunto ",CoberturasConjunto.class).executeUpdate();
          ut.commit();
      }catch(Exception e) {
          try {
              ut.rollback();
          } catch (IllegalStateException | SecurityException | SystemException e1) {
              e1.printStackTrace();
          }
          e.printStackTrace();                
      }       
      return resultado;       
  }
    
	/**
	 * Busqueda de las coberturas conjunto por ramo
	 * @param ramo
	 * @return
	 */	 
	public List<CoberturasConjunto> buscarPorRamoCoberturasConjunto(String ramoNemotecnico){		
		List<CoberturasConjunto> coberturas = null;
		coberturas = getEntityManager().createNamedQuery("CoberturasConjunto.buscarPorRamoCoberturasConjunto").setParameter("ramoNemotecnico",ramoNemotecnico).getResultList();		
		return coberturas;		
	}
	
	
//	public List<CoberturasConjunto> buscarActivos(){
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
//		List<CoberturasConjunto> results = null;
//		try{	
//			TypedQuery<CoberturasConjunto> query = em.createQuery("SELECT c FROM CoberturasConjunto c WHERE c.activo =:valorActivo", CoberturasConjunto.class).setParameter("valorActivo", true);
//			results = query.getResultList(); 
//		}catch(Exception e) { 
//			em.getTransaction().rollback();           
//		}finally{         
//			em.close();			
//		}
//		return results;
//	}
}
