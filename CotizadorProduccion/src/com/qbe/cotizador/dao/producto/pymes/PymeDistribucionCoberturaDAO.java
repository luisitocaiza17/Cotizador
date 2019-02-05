package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeDistribucionCobertura;

public class PymeDistribucionCoberturaDAO extends EntityManagerFactoryDAO<PymeDistribucionCobertura>{
	
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
	
	public PymeDistribucionCoberturaDAO(){
		super(PymeDistribucionCobertura.class);
	}
	
	public PymeDistribucionCobertura buscarPorId(BigInteger distribucionCoberturaId) {
		PymeDistribucionCobertura pymeDistribucionCobertura=new PymeDistribucionCobertura();
		List<PymeDistribucionCobertura> results = getEntityManager().createNamedQuery("PymeDistribucionCobertura.buscarPorId").setParameter("distribucionCoberturaId", distribucionCoberturaId).getResultList();			
		if(results.size()>0)
			pymeDistribucionCobertura = results.get(0);
		return pymeDistribucionCobertura;
	}
	
	public PymeDistribucionCobertura buscarPorCoberturaId(BigInteger coberturaPymesId) {
		PymeDistribucionCobertura pymeDistribucionCobertura=new PymeDistribucionCobertura();
		List<PymeDistribucionCobertura> results = getEntityManager().createNamedQuery("PymeDistribucionCobertura.buscarPorCoberturaId").setParameter("coberturaPymesId", coberturaPymesId).getResultList();			
		if(results.size()>0)
			pymeDistribucionCobertura = results.get(0);
		return pymeDistribucionCobertura;
	}
	
	public List<PymeDistribucionCobertura> buscarTodos() {
		return getEntityManager().createNativeQuery("PymeDistribucionCobertura.buscarTodos").getResultList();
	}
}
