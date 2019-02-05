package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeCobertura;

public class PymeCoberturaDAO extends EntityManagerFactoryDAO<PymeCobertura>{

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
	
	public PymeCoberturaDAO() {
		super(PymeCobertura.class);
	}
	
	public List<PymeCobertura> buscarTodos() {
		return getEntityManager().createNamedQuery("PymeCobertura.buscarTodos").getResultList();
	}
	
	public PymeCobertura buscarPorId(BigInteger id){
		PymeCobertura pymeCobertura = new PymeCobertura();
		List<PymeCobertura> query = getEntityManager().createNamedQuery("PymeCobertura.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			pymeCobertura = query.get(0);
		return pymeCobertura;
	}	
	
}
