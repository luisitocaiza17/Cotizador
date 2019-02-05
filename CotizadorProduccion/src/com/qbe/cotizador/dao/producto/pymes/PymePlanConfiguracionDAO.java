package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymePlanConfiguracion;

public class PymePlanConfiguracionDAO extends EntityManagerFactoryDAO<PymePlanConfiguracion>{
	
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
	
	public PymePlanConfiguracionDAO(){
		super(PymePlanConfiguracion.class);
	}
	
	public List<PymePlanConfiguracion> buscarPorConfiguracionId(BigInteger configuracionCoberturaId) {
		return  getEntityManager().createNamedQuery("PymePlanConfiguracion.buscarPorConfiguracionId", PymePlanConfiguracion.class).setParameter("configuracionCoberturaId", configuracionCoberturaId).getResultList();			
	}
	
	public PymePlanConfiguracion buscarPorId(BigInteger planConfiguracionId) {
		PymePlanConfiguracion pymeExtPlanConfig=new PymePlanConfiguracion();
		List<PymePlanConfiguracion> results = getEntityManager().createNamedQuery("PymePlanConfiguracion.buscarPorId", PymePlanConfiguracion.class).setParameter("planConfiguracionId", planConfiguracionId).getResultList();			
		if(results.size()>0)
			pymeExtPlanConfig = results.get(0);
		return pymeExtPlanConfig;
	}
	
	public List<PymePlanConfiguracion> buscarTodos() {		
		return getEntityManager().createNamedQuery("PymePlanConfiguracion.PymePlanConfiguracion", PymePlanConfiguracion.class).getResultList();	
	}
}
