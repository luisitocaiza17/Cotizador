package com.qbe.cotizador.dao.producto.vehiculo;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.TipoExtra;

public class TipoExtraDAO extends EntityManagerFactoryDAO<TipoExtra>{
	
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

	public TipoExtraDAO() {
	    super(TipoExtra.class);
	}
	
	public List<TipoExtra> buscarTodos(){
		return getEntityManager().createNamedQuery("TipoExtra.buscarTodos").getResultList();
	}
	
	public TipoExtra buscarPorId(String id){
		TipoExtra tipo = new TipoExtra();
		List<TipoExtra> query = getEntityManager().createNamedQuery("TipoExtra.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);
		return tipo;
	}
	
	public TipoExtra buscarPorIdEnsurance(String id){
		TipoExtra tipo = new TipoExtra();
		List<TipoExtra> query = getEntityManager().createNamedQuery("TipoExtra.buscarPorIdEnsurance").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);
		return tipo;				
	}
	
	public List<TipoExtra> buscarActivos(){
		return getEntityManager().createNamedQuery("TipoExtra.buscarActivos").setParameter("valorActivo", true).getResultList();
	}
}
