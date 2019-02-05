package com.qbe.cotizador.dao.pagos;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.FormaPago;

public class FormaPagoDAO extends EntityManagerFactoryDAO <FormaPago>{
	
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
	
	public FormaPagoDAO() {
        super(FormaPago.class);
    }
	
	public List<FormaPago> buscarTodos(){
		return getEntityManager().createNamedQuery("FormaPago.buscarTodos").getResultList();
	}
	
	
	public FormaPago buscarPorId(String id){
		FormaPago forma = new FormaPago();
		List<FormaPago> query = getEntityManager().createNamedQuery("FormaPago.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			forma =  query.get(0);	
		return forma;
	}
	
	
//	public List<FormaPago> buscarFormaPagosDebito(){
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
//		List<FormaPago> results = null;
//		try{	
//			TypedQuery<FormaPago> query = em.createQuery("SELECT c FROM FormaPago c WHERE c.debito = '1'", FormaPago.class);
//			results = query.getResultList(); 
//		}catch(Exception e) { 
//			em.getTransaction().rollback();           
//		}finally{         
//			em.close();
//		}
//		return results;
//	}
}
