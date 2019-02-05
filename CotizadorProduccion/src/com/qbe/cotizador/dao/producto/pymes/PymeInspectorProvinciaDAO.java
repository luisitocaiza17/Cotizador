package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeInspectorProvincia;

public class PymeInspectorProvinciaDAO extends EntityManagerFactoryDAO<PymeInspectorProvincia>{
	
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
	
	public PymeInspectorProvinciaDAO(){
		super(PymeInspectorProvincia.class);
	}
	
	public List<PymeInspectorProvincia> buscarUsuarioId(BigInteger usuarioId, BigInteger provinciaId, BigInteger ciudadId) {
		return getEntityManager().createNamedQuery("PymeInspectorProvincia.buscarPorUsuarioId").setParameter("usuarioId", usuarioId)
				.setParameter("provinciaId", provinciaId)
				.setParameter("ciudadId", ciudadId).getResultList();
	}
	
	/*public PymeInspectorProvincia buscarUsuarioId2(BigInteger usuarioId) {
		PymeInspectorProvincia pymeInspectorProvincia=new PymeInspectorProvincia();
		List<PymeInspectorProvincia> results = getEntityManager().createNamedQuery("PymeInspectorProvincia.buscarPorUsuarioId2").setParameter("usuarioId", usuarioId).getResultList();			
		if(results.size()>0)
			pymeInspectorProvincia = results.get(0);
		return pymeInspectorProvincia;
	}*/
	
	
	public List<PymeInspectorProvincia> buscarUsuarioId(BigInteger usuarioId) {
		return getEntityManager().createNamedQuery("PymeInspectorProvincia.buscarPorUsuarioId2").setParameter("usuarioId", usuarioId).getResultList();
	}
	
	public PymeInspectorProvincia buscarPorId(BigInteger inspectorProvinciaId) {
		PymeInspectorProvincia pymeInspectorProvincia=new PymeInspectorProvincia();
		List<PymeInspectorProvincia> results = getEntityManager().createNamedQuery("PymeInspectorProvincia.buscarPorId").setParameter("inspectorProvinciaId", inspectorProvinciaId).getResultList();			
		if(results.size()>0)
			pymeInspectorProvincia = results.get(0);
		return pymeInspectorProvincia;
	}
	
	public List<PymeInspectorProvincia> buscarTodos() {
		return getEntityManager().createNamedQuery("PymeInspectorProvincia.buscarTodos").getResultList();
	}
	
}
