package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeDeducibleCotizacionValor;;

public class PymeDeducibleCotizacionValorDAO extends EntityManagerFactoryDAO<PymeDeducibleCotizacionValor>{
	
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
	
	public PymeDeducibleCotizacionValorDAO(){
		super(PymeDeducibleCotizacionValor.class);
	}

	public List<PymeDeducibleCotizacionValor> buscarPorCotizacionIdRamoId(BigInteger cotizacionId, BigInteger ramoId) {
		return getEntityManager().createQuery("SELECT c FROM PymeDeducibleCotizacionValor c where c.cotizacionId=:cotizacionId and c.ramoId=:ramoId").setParameter("cotizacionId", cotizacionId).setParameter("ramoId", ramoId).getResultList();
	}
	
	public List<PymeDeducibleCotizacionValor> buscarPorCotizacionId(BigInteger cotizacionId) {
		return getEntityManager().createQuery("SELECT c FROM PymeDeducibleCotizacionValor c where c.cotizacionId=:cotizacionId").setParameter("cotizacionId", cotizacionId).getResultList();
	}
	
	public List<PymeDeducibleCotizacionValor> buscarPorCotizacionIdCoberturaId(BigInteger cotizacionId, BigInteger coberturaId) {
		return getEntityManager().createQuery("SELECT c FROM PymeDeducibleCotizacionValor c where c.cotizacionId=:cotizacionId and c.coberturaPymesId=:coberturaId").setParameter("cotizacionId", cotizacionId).setParameter("coberturaId", coberturaId).getResultList();
	}
}
