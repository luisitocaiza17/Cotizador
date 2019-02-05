package com.qbe.cotizador.dao.producto.ganadero;

import java.math.BigInteger;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.ObjetoGanaderoDetalle;

public class ObjetoGanaderoDetalleDAO extends EntityManagerFactoryDAO<ObjetoGanaderoDetalle>{
	
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
	
	public ObjetoGanaderoDetalleDAO() {
		super(ObjetoGanaderoDetalle.class);
	}
	
	public List<ObjetoGanaderoDetalle> buscarPorObjetoGanadero(BigInteger objetoGanaderoId)
	{
		return getEntityManager().createNamedQuery("ObjetoGanaderoDetalle.buscarPorObjetoGanadero").setParameter("id", objetoGanaderoId).getResultList();
	}

}
