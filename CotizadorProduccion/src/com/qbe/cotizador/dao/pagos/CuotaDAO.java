package com.qbe.cotizador.dao.pagos;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Cuota;
import com.qbe.cotizador.model.Pago;

public class CuotaDAO extends EntityManagerFactoryDAO<Cuota>{
	
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
	
	public CuotaDAO() {
        super(Cuota.class);
    }
	
	public List<Cuota> buscarTodos(){
		return getEntityManager().createNamedQuery("Cuota.buscarTodos").getResultList();
	}

	public Cuota buscarPorId(String id){
		Cuota cuota = new Cuota();
		List<Cuota> query = getEntityManager().createNamedQuery("Cuota.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			cuota =  query.get(0);
		return cuota;
	}
	
	//public List<Cuota> buscarCuotasDebito(){
	//	return getEntityManager().createNamedQuery("Cuota.buscarCuotasDebito").getResultList();
	//}
	
	public List<Cuota> buscarPorPago(Pago pago){
		return getEntityManager().createNamedQuery("Cuota.buscarPorPago").setParameter("pago", pago).getResultList();
	}
	
	public void eliminarPorPago(Pago pago){
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();		
			getEntityManager().createQuery("DELETE FROM Cuota c where c.pago = :pago ", Cuota.class).setParameter("pago", pago).executeUpdate();
		  	ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
				e.printStackTrace();			    
			}	
	}
}
