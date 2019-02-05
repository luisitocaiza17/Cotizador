package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeInspectorProvinciaUsuario;

public class PymeInspectorProvinciaUsuarioDAO extends EntityManagerFactoryDAO<PymeInspectorProvinciaUsuario>{
	
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
	
	public PymeInspectorProvinciaUsuarioDAO(){
		super(PymeInspectorProvinciaUsuario.class);
	}
	
	public List<PymeInspectorProvinciaUsuario> buscarTodos() {
		return getEntityManager().createNamedQuery("PymeInspectorProvinciaUsuario.buscarTodos").getResultList();
	}
	
}
