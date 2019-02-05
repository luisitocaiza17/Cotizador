package com.qbe.cotizador.dao.producto.pymes;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeTipoDeducibleGeneralRamo;

public class PymeTipoDeducibleGeneralRamoDAO extends EntityManagerFactoryDAO<PymeTipoDeducibleGeneralRamo>{
	
	@PersistenceContext(name = "CotizadorWebPC", unitName = "CotizadorWebPU")	
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		if(em == null) {
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

	public PymeTipoDeducibleGeneralRamoDAO() {		
		super(PymeTipoDeducibleGeneralRamo.class);	
	}
	
	public List<PymeTipoDeducibleGeneralRamo> buscarTodos() {
		return getEntityManager().createQuery("SELECT p FROM PymeTipoDeducibleGeneralRamo p", PymeTipoDeducibleGeneralRamo.class).getResultList();		
	}

}
