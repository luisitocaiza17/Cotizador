package com.qbe.cotizador.dao.producto.agricola;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriCotizacionMax;
import com.qbe.cotizador.model.AgriObjetoCotizacion;

public class AgriCotizacionMaxDAO extends EntityManagerFactoryDAO<AgriCotizacionMax>{

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
	
	public AgriCotizacionMaxDAO() {
		super(AgriCotizacionMax.class);
	}
	
	
	public AgriCotizacionMax buscarTodos(){
		AgriCotizacionMax agriCotizacionMax = new AgriCotizacionMax();
		List <AgriCotizacionMax>results = getEntityManager().createNamedQuery("AgriCotizacionMax.buscar", AgriCotizacionMax.class).getResultList();
		if(results.size()>0)
			agriCotizacionMax = results.get(0);
		return agriCotizacionMax;
	}
}
