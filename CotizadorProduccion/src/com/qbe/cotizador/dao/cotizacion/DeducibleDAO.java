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
import com.qbe.cotizador.model.Deducible;

public class DeducibleDAO extends EntityManagerFactoryDAO<Deducible>{

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
	
	public DeducibleDAO() {
        super(Deducible.class);
    }
	
	public List<Deducible> buscarTodos(){
		return getEntityManager().createNamedQuery("Deducible.buscarTodos").getResultList();
	}
	
	public Deducible buscarPorId(String id){
		Deducible deducible = new Deducible();
		List<Deducible> query = getEntityManager().createNamedQuery("Deducible.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			deducible =  query.get(0);
		return deducible;
	}
	
	public Deducible buscarPorProductoDeducible(String deducibleId, String productoId){
		Deducible deducible = new Deducible();
		List<Deducible> query = getEntityManager().createNamedQuery("Deducible.buscarPorProductoDeducible").setParameter("deducibleId", deducibleId).setParameter("productoId", productoId).getResultList();
		if(!query.isEmpty())
			deducible =  query.get(0);
		return deducible;
	}
	
	public List<Deducible> buscarPorProductoDeducible(String productoId){
		return getEntityManager().createNamedQuery("Deducible.buscarPorProductoDeducibleLista").setParameter("producto", productoId).getResultList();
	}
	
	public Deducible buscarPorCoberturaPlanDeducible(String deducibleId, String coberturaId,String planId,String productoId){
		Deducible deducible = new Deducible();
		List<Deducible> query = getEntityManager().createNamedQuery("Deducible.buscarPorCoberturaPlanDeducible").setParameter("deducibleId", deducibleId).setParameter("coberturaId", coberturaId).setParameter("planId", planId).setParameter("productoId", productoId).getResultList();
		if(!query.isEmpty())
			deducible =  query.get(0);
		return deducible;
	}
	
	public int eliminarTodos(){
		int resultado=0;
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			resultado = getEntityManager().createQuery("DELETE FROM Deducible ",Deducible.class).executeUpdate();
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
}
