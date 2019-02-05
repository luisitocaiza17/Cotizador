package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymePlan;

public class PymePlanDAO extends EntityManagerFactoryDAO<PymePlan>{
	
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
	
	public PymePlanDAO(){
		super(PymePlan.class);
	}
	
	public PymePlan buscarPorId(BigInteger planId) {
		PymePlan pymeExtPlan=new PymePlan();
		List<PymePlan> results = getEntityManager().createNamedQuery("PymePlan.buscarPorId", PymePlan.class).setParameter("planId", planId).getResultList();			
		if(results.size()>0)
			pymeExtPlan = results.get(0);
		return pymeExtPlan;
	}
	
	public List<PymePlan> buscarTodos() {		
		return getEntityManager().createNamedQuery("PymePlan.buscarTodos", PymePlan.class).getResultList();	
	}
}
