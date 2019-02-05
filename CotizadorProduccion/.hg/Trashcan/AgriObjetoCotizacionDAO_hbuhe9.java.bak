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
	
	public List<BigInteger> buscaPolizasRepetidas(){
		return getEntityManager().createNamedQuery("AgriObjetoCotizacion.buscarRepetidosPoliza").getResultList();
	}
	
	public List<AgriObjetoCotizacion> buscarPorIdPoliza( BigInteger id){
		return getEntityManager().createNamedQuery("AgriObjetoCotizacion.buscarPorIdPoliza", AgriObjetoCotizacion.class).setParameter("idCotizacion2", id).getResultList();
	}
	
	public BigInteger buscarMaxIdTramites(BigInteger idCotizacion2){		
		BigInteger id= new BigInteger("0");
		List<BigInteger> query = getEntityManager().createNamedQuery("AgriObjetoCotizacion.buscarMaxId").setParameter("idCotizacion2", idCotizacion2).getResultList();
		if(!query.isEmpty())
			id =  query.get(0);
		return id;
	}
}
