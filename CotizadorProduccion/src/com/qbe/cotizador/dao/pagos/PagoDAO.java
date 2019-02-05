package com.qbe.cotizador.dao.pagos;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Pago;

public class PagoDAO extends EntityManagerFactoryDAO<Pago>{
	
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
	
	public PagoDAO() {
        super(Pago.class);
    }
	
	public List<Pago> buscarTodos(){
		return getEntityManager().createNamedQuery("Pago.buscarTodos").getResultList();
	}
	
	
	public Pago buscarPorId(String id){
		Pago pago = new Pago();
		List<Pago> query = getEntityManager().createNamedQuery("Pago.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			pago =  query.get(0);
		return pago;
	}
	
	
//	public List<Pago> buscarPagosDebito(){
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
//		List<Pago> results = null;
//		try{	
//			TypedQuery<Pago> query = em.createQuery("SELECT c FROM Pago c WHERE c.debito = '1'", Pago.class);
//			results = query.getResultList(); 
//		}catch(Exception e) { 
//			em.getTransaction().rollback();           
//		}finally{         
//			em.close();
//		}
//		return results;
//	}
}
