package com.qbe.cotizador.dao.tarifador;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.SumaAsegurada;

public class SumaAseguradaDAO extends EntityManagerFactoryDAO<SumaAsegurada>{
	
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

	public SumaAseguradaDAO() {
	    super(SumaAsegurada.class);
	}
	
	public List<SumaAsegurada>buscarTodos(){
		return getEntityManager().createNamedQuery("SumaAsegurada.buscarTodos").getResultList();
	}
}
