package com.qbe.cotizador.dao.producto.vehiculocerrado;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.TasaProducto;

public class TasaProductoDAO extends EntityManagerFactoryDAO<TasaProducto>{

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

	public TasaProductoDAO() {
	    super(TasaProducto.class);
	}
	
	public List<TasaProducto> buscarTodos(){
		return getEntityManager().createNamedQuery("TasaProducto.buscarTodos").getResultList();
	}
	
	public List<TasaProducto> buscarTodosPorGrupoPorProducto(GrupoPorProducto grupoPorProducto){
		return getEntityManager().createNamedQuery("TasaProducto.buscarTodosPorGrupoPorProducto").setParameter("grupoPorProducto", grupoPorProducto).getResultList();
	}	

	public TasaProducto buscarPorId(String id){
		TasaProducto tasa = new TasaProducto();
		List<TasaProducto> query = getEntityManager().createNamedQuery("TasaProducto.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tasa =  query.get(0);
		return tasa;
	}

	public void eliminarCascada(String id){
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();			
			getEntityManager().createQuery("DELETE FROM TasaProducto where grupoPorProducto.id = :id", TasaProducto.class).setParameter("id", id);
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
