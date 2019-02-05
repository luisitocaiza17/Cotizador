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
import com.qbe.cotizador.model.AgriObjetoCotizacion;

public class AgriAgenciaDAO extends EntityManagerFactoryDAO<AgriAgencia>{
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
	public AgriAgenciaDAO() {
		// TODO Auto-generated constructor stub
		super(AgriAgencia.class);
	}
	public List<AgriAgencia> buscarTodos(){   
		return getEntityManager().createNamedQuery("AgriAgencia.buscarTodos", AgriAgencia.class).getResultList();
	}

	public AgriAgencia buscarPorId(BigInteger id){
		AgriAgencia objetoAgencia = new AgriAgencia();
		List <AgriAgencia>results = getEntityManager().createNamedQuery("AgriAgencia.buscarPorId", AgriAgencia.class).setParameter("Agencia_id", id).getResultList();
		if(results.size()>0)
			objetoAgencia = results.get(0);
		return objetoAgencia;
	}
}
