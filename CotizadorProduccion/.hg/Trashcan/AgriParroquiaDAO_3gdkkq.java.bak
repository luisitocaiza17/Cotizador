package com.qbe.cotizador.dao.producto.agricola;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriParroquia;
import com.qbe.cotizador.model.AgriRegla;
import com.qbe.cotizador.model.AgriTipoCalculo;
import com.qbe.cotizador.model.Parroquia;

public class AgriParroquiaDAO extends EntityManagerFactoryDAO<AgriParroquia> {
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

	public AgriParroquiaDAO() {
		// TODO Auto-generated constructor stub
		super(AgriParroquia.class);
	}
	public AgriParroquia BuscarPorSBS (String CodigoSBS){
		AgriParroquia agriParroquia = new AgriParroquia();
		List<AgriParroquia> result = getEntityManager().createNamedQuery("AgriParroquia.obtenerPorSBS").setParameter("CodigoSBS", CodigoSBS).getResultList();
		if (result.size()>0)
			agriParroquia = result.get(0);
		return agriParroquia;
	}
	
	public List<AgriParroquia> buscarTodos(){
		return getEntityManager().createNamedQuery("AgriParroquia.buscarTodos").getResultList();
	}
	
	public AgriParroquia BuscarPorNombre (String nombre){
		AgriParroquia agriParroquia = new AgriParroquia();
		List<AgriParroquia> result = getEntityManager().createNamedQuery("AgriParroquia.obtenerPorNombre").setParameter("nombre", nombre).getResultList();
		if (result.size()>0)
			agriParroquia = result.get(0);
		return agriParroquia;
	}
	
	public AgriParroquia BuscarPorId (int id){
		AgriParroquia agriParroquia = new AgriParroquia();
		List<AgriParroquia> result = getEntityManager().createNamedQuery("AgriParroquia.obtenerPorId").setParameter("id", id).getResultList();
		if (result.size()>0)
			agriParroquia = result.get(0);
		return agriParroquia;
	}
	
	public List<AgriParroquia> BuscarPorCanton (String cantonId){
		AgriParroquia agriParroquia = new AgriParroquia();
		List<AgriParroquia> result = getEntityManager().createNamedQuery("AgriParroquia.obtenerPorCanton").setParameter("canton", cantonId).getResultList();
		
		return result;
	}
	
	public AgriParroquia BuscarPorNombreYCanton (String nombre, String cantonId){
		AgriParroquia agriParroquia = new AgriParroquia();
		List<AgriParroquia> result = getEntityManager().createNamedQuery("AgriParroquia.obtenerPorNombreYCanton").setParameter("nombre", "%" +nombre+ "%").setParameter("canton", cantonId).getResultList();
		if (result.size()>0)
			agriParroquia = result.get(0);
		return agriParroquia;
	}
	
	
}