package com.qbe.cotizador.dao.cotizacion;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Plan;

public class PlanDAO extends EntityManagerFactoryDAO<Plan>{

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
	
	public PlanDAO() {
        super(Plan.class);
    }
	
	public List<Plan> buscarTodos(){
		return getEntityManager().createNamedQuery("Plan.buscarTodos").getResultList();		
	}
		
	public Plan buscarPorId(String id){
		Plan plan = new Plan();
		List<Plan> query = getEntityManager().createNamedQuery("Plan.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			plan =  query.get(0);
			
		return plan;
	}
}
