package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.model.PymeUsuarioInspector;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeFechaCierre;

public class PymeUsuarioInspectorDAO extends EntityManagerFactoryDAO<PymeUsuarioInspector>{
	
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
	
	public PymeUsuarioInspectorDAO(){
		super(PymeUsuarioInspector.class);
	}
	
	public PymeUsuarioInspector buscarPorUsuarioId(BigInteger id) {
		PymeUsuarioInspector pymeObjeto=new PymeUsuarioInspector();
		List<PymeUsuarioInspector> results = getEntityManager().createNamedQuery("PymeUsuarioInspector.buscarPorUsuarioId").setParameter("id", id).getResultList();			
		if(results.size()>0)
			pymeObjeto = results.get(0);
		return pymeObjeto;
	}
	
	public PymeUsuarioInspector buscarPorId(BigInteger id) {
		PymeUsuarioInspector pymeObjeto=new PymeUsuarioInspector();
		List<PymeUsuarioInspector> results = getEntityManager().createNamedQuery("PymeUsuarioInspector.buscarPorId").setParameter("id", id).getResultList();			
		if(results.size()>0)
			pymeObjeto = results.get(0);
		return pymeObjeto;
	}
	
	public List<PymeUsuarioInspector> buscarPorRolId(BigInteger rolId) {
		return getEntityManager().createNamedQuery("PymeUsuarioInspector.buscarPorRolId").setParameter("rolId", rolId).getResultList();			
	}
	
	public List<PymeUsuarioInspector> buscarTodos() {		
		return getEntityManager().createNamedQuery("PymeUsuarioInspector.buscarTodos").getResultList();	
	}
}
