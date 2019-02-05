package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeCobertura;
import com.qbe.cotizador.model.PymeCoberturasConfiguracion;

public class PymeCoberturasConfiguracionDAO extends EntityManagerFactoryDAO<PymeCoberturasConfiguracion>{

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
	
	public PymeCoberturasConfiguracionDAO() {
		super(PymeCoberturasConfiguracion.class);
	}
	
	public List<PymeCoberturasConfiguracion> buscarTodos() {
		return getEntityManager().createNamedQuery("PymeCoberturasConfiguracion.buscarTodos").getResultList();
	}
	
	public List<PymeCoberturasConfiguracion> buscarRamoGrupoCobertura(BigInteger configProductoId ) {
		return getEntityManager().createNamedQuery("PymeCoberturasConfiguracion.buscarPorGrupoCobertura").setParameter("configProductoId", configProductoId).getResultList();
	}
	public List<PymeCoberturasConfiguracion> buscarPorGrupoCoberturaIN(List<BigInteger> configProductoId ) {
		return getEntityManager().createNamedQuery("PymeCoberturasConfiguracion.buscarPorGrupoCoberturaIN").setParameter("listConfigProductoId", configProductoId).getResultList();
	}
	
}

