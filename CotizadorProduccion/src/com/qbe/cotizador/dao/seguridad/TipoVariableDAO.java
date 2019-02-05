package com.qbe.cotizador.dao.seguridad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.TipoVariable;

public class TipoVariableDAO extends EntityManagerFactoryDAO<TipoVariable>{
	
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

	public TipoVariableDAO() {
	    super(TipoVariable.class);
	}
		
	public List<TipoVariable> buscarTodos(){
		return getEntityManager().createNamedQuery("TipoVariable.buscarTodos").getResultList();
	}
	
	public TipoVariable buscarPorId(String id){
		TipoVariable tipo = new TipoVariable();
		List<TipoVariable> query = getEntityManager().createNamedQuery("TipoVariable.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);
		return tipo;
	}		
}
