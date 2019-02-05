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
import com.qbe.cotizador.model.AgriCultivoCanal;

public class AgriCultivoCanalDAO extends EntityManagerFactoryDAO<AgriCultivoCanal>{
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
	public AgriCultivoCanalDAO() {
		// TODO Auto-generated constructor stub
		super(AgriCultivoCanal.class);
	}
	public List<AgriCultivoCanal> buscarTodos(){   
		return getEntityManager().createNamedQuery("AgriCultivoCanal.findAll", AgriCultivoCanal.class).getResultList();
	}

	public List<AgriCultivoCanal> buscarTipoCalculo(BigInteger tipoCalculoId){  
		return getEntityManager().createNamedQuery("AgriCultivoCanal.buscarTipoCalculo", AgriCultivoCanal.class).setParameter("tipoCalculoId", tipoCalculoId).getResultList();
		
	}
}
