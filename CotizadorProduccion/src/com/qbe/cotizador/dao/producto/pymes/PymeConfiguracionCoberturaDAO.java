package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeConfiguracionCobertura;

public class PymeConfiguracionCoberturaDAO extends EntityManagerFactoryDAO<PymeConfiguracionCobertura>{
	
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
	
	public PymeConfiguracionCoberturaDAO() {
		super(PymeConfiguracionCobertura.class);
	}
	
	public List<PymeConfiguracionCobertura> buscarTodos() {
		return getEntityManager().createQuery("SELECT p FROM PymeConfiguracionCobertura p", PymeConfiguracionCobertura.class).getResultList();
	}
	
	public List<PymeConfiguracionCobertura> buscarPorGrupoPorProductoID(BigInteger grupoPorProductoId) {
		return getEntityManager().createQuery("SELECT p FROM PymeConfiguracionCobertura p where p.grupoPorProductoId=:grupoPorProductoId", PymeConfiguracionCobertura.class).setParameter("grupoPorProductoId", grupoPorProductoId).getResultList();
	}
	
	public PymeConfiguracionCobertura buscarPorId(BigInteger configuracionCoberturaId){
		PymeConfiguracionCobertura configuracionCobertura = new PymeConfiguracionCobertura();
		List<PymeConfiguracionCobertura> query = getEntityManager().createQuery("SELECT p FROM PymeConfiguracionCobertura p where p.configuracionCoberturaId=:Id", PymeConfiguracionCobertura.class).setParameter("Id", configuracionCoberturaId).getResultList();
		if(!query.isEmpty())
			configuracionCobertura = query.get(0);
		return configuracionCobertura;
	}

}
