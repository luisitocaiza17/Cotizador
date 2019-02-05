package com.qbe.cotizador.dao.producto.agricola;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriPronacaQbeCultivos;

public class AgriPronacaQbeCultivoDAO extends EntityManagerFactoryDAO<AgriPronacaQbeCultivos>{

	@PersistenceContext(name="CotizadorWebPC", unitName = "CotizadorWebPU" )	
	private EntityManager em;
	
	public AgriPronacaQbeCultivoDAO(Class<AgriPronacaQbeCultivos> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}
	
	public AgriPronacaQbeCultivoDAO(){
		super(AgriPronacaQbeCultivos.class);
	}

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
	
	public  List<AgriPronacaQbeCultivos>BuscarCultivos(String nombrePronaca)
	{
		return getEntityManager().createNamedQuery("AgriPronacaQbeCultivos.BuscarCultivos").setParameter("nombrePronaca", nombrePronaca).getResultList();
	}

}