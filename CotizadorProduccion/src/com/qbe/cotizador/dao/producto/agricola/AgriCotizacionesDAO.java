package com.qbe.cotizador.dao.producto.agricola;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriCotizaciones;

public class AgriCotizacionesDAO extends EntityManagerFactoryDAO<AgriCotizaciones> {
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

	public AgriCotizacionesDAO() {
		// TODO Auto-generated constructor stub
		super(AgriCotizaciones.class);
	}
	
	public List<AgriCotizaciones>BuscarTodos(){
		return getEntityManager().createNamedQuery("AgriCotizaciones.buscarTodos").getResultList();
	}
	
	public AgriCotizaciones BuscarPorCotizacionId(BigInteger Id){
		AgriCotizaciones agriCotizaciones = new AgriCotizaciones();
		List<AgriCotizaciones> result = getEntityManager().createNamedQuery("AgriCotizaciones.buscarCotizacionId").setParameter("id", Id).getResultList();
		if (result.size()>0)
			agriCotizaciones = result.get(0);
		return agriCotizaciones;
	}
	
	public List<AgriCotizaciones> BuscarPorClienteIdentificacion(String identificacion){
		return getEntityManager().createNamedQuery("AgriCotizaciones.buscarPorIdentificacion")
				.setParameter("identificacion", identificacion).getResultList();
	}

}
