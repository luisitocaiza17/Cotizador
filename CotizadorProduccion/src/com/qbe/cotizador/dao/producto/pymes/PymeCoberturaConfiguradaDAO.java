package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeCoberturaConfigurada;
import com.qbe.cotizador.model.PymeCoberturaTasa;

public class PymeCoberturaConfiguradaDAO extends EntityManagerFactoryDAO<PymeCoberturaTasa>{
	
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
	
	public PymeCoberturaConfiguradaDAO() {
		super(PymeCoberturaTasa.class);
	}
	
	public List<PymeCoberturaConfigurada> buscarTodos() {
		return getEntityManager().createQuery("SELECT p FROM PymeCoberturaConfigurada p", PymeCoberturaConfigurada.class).getResultList();
	}
	
	public List<PymeCoberturaConfigurada> buscarPorGrupoPorProductoId(BigInteger grupoPorProductoId) {
		return getEntityManager().createQuery("SELECT p FROM PymeCoberturaConfigurada p where p.grupoPorProductoId=:grupoPorProductoId", PymeCoberturaConfigurada.class).setParameter("grupoPorProductoId", grupoPorProductoId).getResultList();
	}
	
	public List<PymeCoberturaConfigurada> buscarPorId(BigInteger id) {
		return getEntityManager().createQuery("SELECT p FROM PymeCoberturaConfigurada p where p.id=:id", PymeCoberturaConfigurada.class).setParameter("id", id).getResultList();
	}
}
