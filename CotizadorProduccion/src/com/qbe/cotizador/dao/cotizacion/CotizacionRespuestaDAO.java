package com.qbe.cotizador.dao.cotizacion;

import java.math.BigInteger;
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
	
	public CotizacionRespuesta buscarPorId(BigInteger id){
		CotizacionRespuesta cotizacionRespuesta = new CotizacionRespuesta();
		List<CotizacionRespuesta> query = getEntityManager().createQuery("SELECT p FROM CotizacionRespuesta p where p.id=:Id", CotizacionRespuesta.class).setParameter("Id", id).getResultList();
		if(!query.isEmpty())
			cotizacionRespuesta = query.get(0);
		return cotizacionRespuesta;
	}
	
	public CotizacionRespuesta buscarPorCotizacionId(BigInteger cotizacionId){
		CotizacionRespuesta cotizacionRespuesta = new CotizacionRespuesta();
		List<CotizacionRespuesta> query = getEntityManager().createQuery("SELECT p FROM CotizacionRespuesta p where p.cotizacionId=:Id", CotizacionRespuesta.class).setParameter("Id", cotizacionId).getResultList();
		if(!query.isEmpty())
			cotizacionRespuesta = query.get(0);
		return cotizacionRespuesta;
	}
}
