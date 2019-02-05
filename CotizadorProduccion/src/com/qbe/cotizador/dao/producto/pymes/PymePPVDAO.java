package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriCanal;
import com.qbe.cotizador.model.PYMEPPV;
import com.qbe.cotizador.model.PymeParametroPuntoVenta;

public class PymePPVDAO extends EntityManagerFactoryDAO<PYMEPPV> {
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
	public PymePPVDAO (){
		super(PYMEPPV.class);
	}
	
	public List<PYMEPPV> cargarTodosKendo(int Skip, int Take){
		
		Query query = null;
		
		String stringQuery= "SELECT c FROM PYMEPPV c";	
				
		query = getEntityManager().createQuery(stringQuery);
		
		List<PYMEPPV> results = new ArrayList<PYMEPPV>();
		results = query.setMaxResults(Take).setFirstResult(Skip).getResultList();
		
		return results;
	}
	
	public long cargarTodosKendoPorNumero(){
		
		Query query = null;
		
		String stringQuery= "SELECT count(c.parametroPuntoVentaId) FROM PYMEPPV c ";	
		
		query = getEntityManager().createQuery(stringQuery);
		
		long results = (long)query.getSingleResult();
		
		return results;
	}
	
	public PYMEPPV buscarPorParametroId(BigInteger parametroPuntoVentaId)
	{
		PYMEPPV pymePPV = new PYMEPPV();
		List<PYMEPPV> result = getEntityManager().createNamedQuery("PYMEPPV.obtenerPorId").setParameter("parametroPuntoVentaId", parametroPuntoVentaId).getResultList();
		if (result.size()>0)
			pymePPV=result.get(0);
		return pymePPV;
	}
	
}
