package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeParametroPuntoVenta;
import com.qbe.cotizador.model.UsuariosOffline;

public class PymeParametroPuntoVentaDAO extends EntityManagerFactoryDAO<PymeParametroPuntoVenta>{
	
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
	
	public PymeParametroPuntoVentaDAO() {
		super(PymeParametroPuntoVenta.class);
	}
	
	public List<PymeParametroPuntoVenta> buscarTodos(){
		return getEntityManager().createNamedQuery("PymeParametroPuntoVenta.buscarTodos").getResultList();
	}
	
	public List<PymeParametroPuntoVenta> cargarTodosKendo(int Skip, int Take){
		
		Query query = null;
		
		String stringQuery= "SELECT c FROM PymeParametroPuntoVenta c";	
				
		query = getEntityManager().createQuery(stringQuery);
		
		List<PymeParametroPuntoVenta> results = new ArrayList<PymeParametroPuntoVenta>();
		results = query.setMaxResults(Take).setFirstResult(Skip).getResultList();
		
		return results;
	}
	
	public long cargarTodosKendoPorNumero(){
		
		Query query = null;
		
		String stringQuery= "SELECT count(c.parametroPuntoventaId) FROM PymeParametroPuntoVenta c ";	
		
		query = getEntityManager().createQuery(stringQuery);
		
		long results = (long)query.getSingleResult();
		
		return results;
	}
	
	public PymeParametroPuntoVenta buscarPorId(BigInteger Id){
		PymeParametroPuntoVenta parametroPyme = new PymeParametroPuntoVenta();
		List<PymeParametroPuntoVenta> results = getEntityManager().createNamedQuery("PymeParametroPuntoVenta.buscarPorId").setParameter("parametroPuntoventaId", Id).getResultList();	
		if(results.size()>0)
			parametroPyme = results.get(0);
		return  parametroPyme;
	}
	
	public PymeParametroPuntoVenta buscarPorPuntoVentaId(BigInteger puntoVentaId){
		PymeParametroPuntoVenta parametroPyme = new PymeParametroPuntoVenta();
		List<PymeParametroPuntoVenta> results = getEntityManager().createNamedQuery("PymeParametroPuntoVenta.obtenerPorPuntoVentaId").setParameter("puntoVentaId", puntoVentaId).getResultList();	
		if(results.size()>0)
			parametroPyme = results.get(0);
		return  parametroPyme;
	}		
}
