package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeDerechoEmision;

public class PymeDerechoEmisionDAO extends EntityManagerFactoryDAO<PymeDerechoEmision>{
	
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
	
	public PymeDerechoEmisionDAO(){
		super(PymeDerechoEmision.class);
	}
	
	public List<PymeDerechoEmision> buscarTodos() {
		return getEntityManager().createNamedQuery("PymeDerechoEmision.buscarTodos").getResultList();
	}
	
	public PymeDerechoEmision buscarPorId(BigInteger derechoEmisonId) {
		PymeDerechoEmision pymeAsistencia=new PymeDerechoEmision();
		List<PymeDerechoEmision> results = getEntityManager().createNamedQuery("PymeDerechoEmision.buscarPorId").setParameter("Id", derechoEmisonId).getResultList();			
		if(results.size()>0)
			pymeAsistencia = results.get(0);
		return pymeAsistencia;
	}
	
	public List<PymeDerechoEmision> buscarIntervalo(double valorPrima) {
		return getEntityManager().createNamedQuery("PymeDerechoEmision.buscarIntervalo").setParameter("valorPrima", valorPrima).getResultList();
	}
}
