package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeFechaCierre;

public class PymeFechaCierreDAO extends EntityManagerFactoryDAO<PymeFechaCierre>{
	
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
	
	public PymeFechaCierreDAO(){
		super(PymeFechaCierre.class);
	}
	
	public PymeFechaCierre buscarPorId(BigInteger fechaCierreId) {
		PymeFechaCierre pymeExtRamo=new PymeFechaCierre();
		List<PymeFechaCierre> results = getEntityManager().createNamedQuery("PymeFechaCierre.buscarPorId").setParameter("fechaCierreId", fechaCierreId).getResultList();			
		if(results.size()>0)
			pymeExtRamo = results.get(0);
		return pymeExtRamo;
	}
	
	public List<PymeFechaCierre> buscarTodos() {		
		return getEntityManager().createNamedQuery("PymeFechaCierre.buscarTodos").getResultList();	
	}
}
