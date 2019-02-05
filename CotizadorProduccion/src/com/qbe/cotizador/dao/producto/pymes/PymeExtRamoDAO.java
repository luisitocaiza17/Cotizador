package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeExtRamo;

public class PymeExtRamoDAO extends EntityManagerFactoryDAO<PymeExtRamo>{
	
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
	
	public PymeExtRamoDAO(){
		super(PymeExtRamo.class);
	}
	
	public PymeExtRamo buscarPorId(BigInteger ramoId) {
		PymeExtRamo pymeExtRamo=new PymeExtRamo();
		List<PymeExtRamo> results = getEntityManager().createNamedQuery("PymeExtRamo.buscarPorId").setParameter("ramoId", ramoId).getResultList();			
		if(results.size()>0)
			pymeExtRamo = results.get(0);
		return pymeExtRamo;
	}
	
	public List<PymeExtRamo> buscarTodos() {		
		return getEntityManager().createNamedQuery("PymeExtRamo.buscarTodos").getResultList();	
	}
}
