package com.qbe.cotizador.dao.producto.agricola;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriAgencia;
import com.qbe.cotizador.model.AgriIndemnizacion;

public class AgriIndemnizacionDAO extends EntityManagerFactoryDAO<AgriIndemnizacion>{
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
	public AgriIndemnizacionDAO() {
		// TODO Auto-generated constructor stub
		super(AgriIndemnizacion.class);
	}
	public List<AgriIndemnizacion> buscarTodos(){   
		return getEntityManager().createNamedQuery("AgriIndemnizacion.findAll", AgriIndemnizacion.class).getResultList();
	}	
	
	public AgriIndemnizacion buscarPorId(BigInteger id){
		AgriIndemnizacion objetoAgencia = new AgriIndemnizacion();
		List <AgriIndemnizacion>results = getEntityManager().createNamedQuery("AgriIndemnizacion.buscarPorId", AgriIndemnizacion.class).setParameter("id", id).getResultList();
		if(results.size()>0)
			objetoAgencia = results.get(0);
		return objetoAgencia;
	}
}
