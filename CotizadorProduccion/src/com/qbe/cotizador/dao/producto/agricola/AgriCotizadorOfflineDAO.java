package com.qbe.cotizador.dao.producto.agricola;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriCiclo;
import com.qbe.cotizador.model.AgriCotizadorOffline;

public class AgriCotizadorOfflineDAO extends EntityManagerFactoryDAO<AgriCotizadorOffline> {
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
	public AgriCotizadorOfflineDAO (){
		super(AgriCotizadorOffline.class);
	}
	
	public AgriCotizadorOffline BuscarPorId(BigInteger id)
	{
		AgriCotizadorOffline agriCotizadorOffline = new AgriCotizadorOffline();
		List<AgriCotizadorOffline> result = getEntityManager().createNamedQuery("AgriCotizadorOffline.buscarId").setParameter("id", id).getResultList();
		if (result.size()>0)
			agriCotizadorOffline=result.get(0);
		return agriCotizadorOffline;
	}
	
	public AgriCotizadorOffline BuscarPorFecha()
	{
		AgriCotizadorOffline agriCotizadorOffline = new AgriCotizadorOffline();
		List<AgriCotizadorOffline> result = getEntityManager().createNamedQuery("AgriCotizadorOffline.buscarFecha").getResultList();
		if (result.size()>0)
			agriCotizadorOffline=result.get(0);
		return agriCotizadorOffline;
	}
	
}

