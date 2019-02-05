package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeAsistencia;
import com.qbe.cotizador.model.PymeConfiguracionLog;

public class PymeConfiguracionLogDAO extends EntityManagerFactoryDAO<PymeConfiguracionLog>{
	
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
	
	public PymeConfiguracionLogDAO(){
		super(PymeConfiguracionLog.class);
	}
	
	public PymeConfiguracionLog buscarPorId(BigInteger id) {
		PymeConfiguracionLog pymeConfiguracionLog=new PymeConfiguracionLog();
		List<PymeConfiguracionLog> results = getEntityManager().createNamedQuery("PymeConfiguracionLog.buscarPorId").setParameter("configuracionLogId", id).getResultList();			
		if(results.size()>0)
			pymeConfiguracionLog = results.get(0);
		return pymeConfiguracionLog;
	}
}
