package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeObjetoCotizacion;

public class PymeObjetoCotizacionDAO extends EntityManagerFactoryDAO<PymeObjetoCotizacion>{

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
	
	public PymeObjetoCotizacionDAO() {
		super(PymeObjetoCotizacion.class);
	}
	
	public List<PymeObjetoCotizacion> buscarTodos(){   
		return getEntityManager().createQuery("SELECT p FROM PymeObjetoCotizacion p", PymeObjetoCotizacion.class).getResultList();
	}


	public PymeObjetoCotizacion buscarPorId(BigInteger id){
		PymeObjetoCotizacion objetoCotizacion = new PymeObjetoCotizacion();
		List <PymeObjetoCotizacion>results = getEntityManager().createQuery("SELECT c FROM PymeObjetoCotizacion c where c.objetoPymesId=:id", PymeObjetoCotizacion.class).setParameter("id", id).getResultList();
		if(results.size()>0)
			objetoCotizacion = results.get(0);
		return objetoCotizacion;
	}
}
