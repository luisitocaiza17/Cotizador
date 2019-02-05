package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeFechaCierre;
import com.qbe.cotizador.model.PymeInspectorProvinciaAsignado;
import com.qbe.cotizador.model.PymeParametroXGrupoPorProducto;
import com.qbe.cotizador.model.PymePlanesPorCoberturas;

public class PymePlanesPorCoberturasDAO extends EntityManagerFactoryDAO<PymePlanesPorCoberturas>{

	
	
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
	
	public PymePlanesPorCoberturasDAO() {
		super(PymePlanesPorCoberturas.class);
	}
	
	public List<PymePlanesPorCoberturas> buscarTodos() {
		return getEntityManager().createNamedQuery("PymePlanesPorCoberturas.buscarTodos").getResultList();
	}
	
	public List<PymePlanesPorCoberturas> buscarPorPlan(String grupoPorProductoId, int tipoDeclaracion) {
		PymePlanesPorCoberturas pymePlanesPorCoberturas=new PymePlanesPorCoberturas();
		List<PymePlanesPorCoberturas> results = getEntityManager().createNamedQuery("PymePlanesPorCoberturas.buscarPorPlan").setParameter("grupoPorProductoId", grupoPorProductoId).setParameter("tipoDeclaracion", tipoDeclaracion).getResultList();			
		return results;
	}
	
	public List<PymePlanesPorCoberturas> buscarPorGeneral(String grupoPorProductoId, int tipoDeclaracion, String configuracionCoberturaId) {
		PymePlanesPorCoberturas pymePlanesPorCoberturas=new PymePlanesPorCoberturas();
		List<PymePlanesPorCoberturas> results = getEntityManager().createNamedQuery("PymePlanesPorCoberturas.buscarPorGeneral").setParameter("grupoPorProductoId", grupoPorProductoId).setParameter("tipoDeclaracion", tipoDeclaracion).setParameter("configuracionCoberturaId", configuracionCoberturaId).getResultList();			
		return results;
	}
	
	public List<PymePlanesPorCoberturas> buscarPorProductoId(String grupoPorProductoId) {
		PymePlanesPorCoberturas pymePlanesPorCoberturas=new PymePlanesPorCoberturas();
		List<PymePlanesPorCoberturas> results = getEntityManager().createNamedQuery("PymePlanesPorCoberturas.buscarPorProductoId").setParameter("grupoPorProductoId", grupoPorProductoId).getResultList();			
		return results;
	}
	
	
}
