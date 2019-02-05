package com.qbe.cotizador.dao.entidad;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgenteAcuerdo;

public class AgenteAcuerdoDAO extends EntityManagerFactoryDAO<AgenteAcuerdo>{	

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
	
	public AgenteAcuerdoDAO() {
        super(AgenteAcuerdo.class);
    }
	
	public List<AgenteAcuerdo> buscarTodos(){
		return getEntityManager().createNamedQuery("AgenteAcuerdo.buscarTodos").getResultList();		
	}
	
	public AgenteAcuerdo buscarPorId(String id){
		AgenteAcuerdo agenteAcuerdo = null;
		List<AgenteAcuerdo> query = getEntityManager().createNamedQuery("AgenteAcuerdo.buscarPorId").setParameter("id", id).getResultList();
		if(query.isEmpty())
			agenteAcuerdo = null;
		else
			agenteAcuerdo =  query.get(0);
		return agenteAcuerdo;
	}
}
