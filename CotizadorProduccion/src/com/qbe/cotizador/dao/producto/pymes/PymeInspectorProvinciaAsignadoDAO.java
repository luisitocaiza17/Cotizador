package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeInspectorProvinciaAsignado;

public class PymeInspectorProvinciaAsignadoDAO extends EntityManagerFactoryDAO<PymeInspectorProvinciaAsignado>{
	
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
	
	public PymeInspectorProvinciaAsignadoDAO(){
		super(PymeInspectorProvinciaAsignado.class);
	}
	
	public List<PymeInspectorProvinciaAsignado> buscarPorProvinciaCiudad(BigInteger provinciaId, BigInteger ciudadId) {
		return getEntityManager().createQuery("SELECT p FROM PymeInspectorProvinciaAsignado p where p.provinciaId=:provinciaId and p.ciudadId=:ciudadId", PymeInspectorProvinciaAsignado.class).setParameter("provinciaId", provinciaId).setParameter("ciudadId", ciudadId).getResultList();
		
	}
	
}
