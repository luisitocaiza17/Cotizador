package com.qbe.cotizador.dao.producto.ganadero;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.ObjetoGanadero; 

public class ObjetoGanaderoDAO extends EntityManagerFactoryDAO<ObjetoGanadero>{
	
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
	
	public ObjetoGanaderoDAO() {
		super(ObjetoGanadero.class);
	}
	
	public ObjetoGanadero buscarPorId(String id){
		ObjetoGanadero objeto = new ObjetoGanadero();
		List<ObjetoGanadero> query = getEntityManager().createNamedQuery("ObjetoGanadero.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			objeto =  query.get(0);
		return objeto;
	}
	
	public ObjetoGanadero buscarObjetoGanaderoPorCotizacionDetalle(String CotizacionDetalleId){
		ObjetoGanadero objeto = new ObjetoGanadero();
		List<ObjetoGanadero> query = getEntityManager().createNamedQuery("ObjetoGanadero.buscarObjetoGanaderoPorCotizacionDetalle").setParameter("id", CotizacionDetalleId).getResultList();
		if(!query.isEmpty())
			objeto =  query.get(0);
		return objeto;
	}

}
