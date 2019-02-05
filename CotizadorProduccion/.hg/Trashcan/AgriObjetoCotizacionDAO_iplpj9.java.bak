package com.qbe.cotizador.dao.producto.agricola;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriObjetoCotizacion;
import com.qbe.cotizador.model.PymeObjetoCotizacion;

public class AgriObjetoCotizacionDAO extends EntityManagerFactoryDAO<AgriObjetoCotizacion>{

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
	
	public AgriObjetoCotizacionDAO() {
		super(AgriObjetoCotizacion.class);
	}
	
	public List<AgriObjetoCotizacion> buscarTodos(){   
		return getEntityManager().createNamedQuery("AgriObjetoCotizacion.buscarTodos", AgriObjetoCotizacion.class).getResultList();
	}


	public AgriObjetoCotizacion buscarPorId(BigInteger id){
		AgriObjetoCotizacion objetoCotizacion = new AgriObjetoCotizacion();
		List <AgriObjetoCotizacion>results = getEntityManager().createNamedQuery("AgriObjetoCotizacion.buscarPorId", AgriObjetoCotizacion.class).setParameter("id", id).getResultList();
		if(results.size()>0)
			objetoCotizacion = results.get(0);
		return objetoCotizacion;
	}
	
	public List<AgriObjetoCotizacion> buscarPorObjetoOfflineId(String id){
		return getEntityManager().createNamedQuery("AgriObjetoCotizacion.buscarPorObjetoOfflineId", AgriObjetoCotizacion.class).setParameter("objetoOfflineId", id).getResultList();
	}
}
