package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeCoberturaTasa;
import com.qbe.cotizador.model.PymeParametroXPuntoVenta;

public class PymeCoberturaTasaDAO extends EntityManagerFactoryDAO<PymeCoberturaTasa>{

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
	
	public PymeCoberturaTasaDAO() {
		super(PymeCoberturaTasa.class);
	}
	
	public List<PymeCoberturaTasa> buscarTodos(BigInteger id){
		return getEntityManager().createQuery("SELECT p FROM PymeCoberturaTasa p", PymeCoberturaTasa.class).getResultList();
	}
	
	public PymeCoberturaTasa buscarPorId(BigInteger Id){
		PymeCoberturaTasa parametroPyme = new PymeCoberturaTasa();
		List<PymeCoberturaTasa> results = getEntityManager().createQuery("SELECT c FROM PymeCoberturaTasa c where c.coberturaTasaId = :id", PymeCoberturaTasa.class).setParameter("id", Id).getResultList();	
		if(results.size()>0)
			parametroPyme = results.get(0);
		return  parametroPyme;
	}
	
	public List<PymeCoberturaTasa> buscarPorConfiguracionCoberturaId(BigInteger id){
		return getEntityManager().createQuery("SELECT c FROM PymeCoberturaTasa c where c.configuracionCoberturaId = :id", PymeCoberturaTasa.class).setParameter("id", id).getResultList();
	}
}
