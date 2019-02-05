package com.qbe.cotizador.dao.producto.pymes;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.CotizacionRespuesta;

public class CotizacionRespuestaDAO extends EntityManagerFactoryDAO<CotizacionRespuesta>{
	
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

	public CotizacionRespuestaDAO() {
	    super(CotizacionRespuesta.class);
	}
	
	public CotizacionRespuesta buscarPorCotizacionId(String cotizacionId){   
		CotizacionRespuesta tipo = new CotizacionRespuesta();
		List<CotizacionRespuesta> query = getEntityManager().createNamedQuery("CotizacionRespuesta.buscarPorCotizacionId").setParameter("cotizacionId", cotizacionId).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);
		return tipo;
	}
}
