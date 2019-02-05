package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeTipoDeducibleCobertura;

public class PymeTipoDeducibleCoberturaDAO extends EntityManagerFactoryDAO<PymeTipoDeducibleCobertura>{
	
	@PersistenceContext(name = "CotizadorWebPC", unitName = "CotizadorWebPU")	
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		if(em == null) {
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

	public PymeTipoDeducibleCoberturaDAO() {		
		super(PymeTipoDeducibleCobertura.class);	
	}
	
	public List<PymeTipoDeducibleCobertura> buscarTodos() {
		return getEntityManager().createQuery("SELECT p FROM PymeTipoDeducibleCobertura p", PymeTipoDeducibleCobertura.class).getResultList();		
	}
	
	public PymeTipoDeducibleCobertura buscarPorId(String tipoDeducibleCoberturaId) {
		PymeTipoDeducibleCobertura tipoDeducibleCobertura = new PymeTipoDeducibleCobertura();
		List<PymeTipoDeducibleCobertura> results = getEntityManager().createQuery("SELECT p FROM PymeTipoDeducibleCobertura p where p.tipoDeducibleCoberturaId=:tipoDeducibleCoberturaId", PymeTipoDeducibleCobertura.class).setParameter("tipoDeducibleCoberturaId",tipoDeducibleCoberturaId).getResultList();
		if(results.size() > 0)
			tipoDeducibleCobertura = results.get(0);
		return tipoDeducibleCobertura;
	}
	
	public List<PymeTipoDeducibleCobertura> buscarPorConfiguracionCoberturaId(BigInteger configuracionCoberturaId) {		
		return getEntityManager().createQuery("SELECT c FROM PymeTipoDeducibleCobertura c where c.configuracionCoberturaId=:configuracionCoberturaId ORDER BY c.tipoDeducibleCoberturaId", PymeTipoDeducibleCobertura.class).setParameter("configuracionCoberturaId",configuracionCoberturaId).getResultList();		
		 		
	}	

	public List<PymeTipoDeducibleCobertura> buscarPorConfiguracionCoberturaTipoVariableId(BigInteger configuracionCoberturaId, boolean seguridadAdecuada) {		
		return getEntityManager().createQuery("SELECT c FROM PymeTipoDeducibleCobertura c where c.configuracionCoberturaId=:configuracionCoberturaId and c.seguridadAdecuada=:seguridadAdecuada ORDER BY c.tipoDeducibleCoberturaId", PymeTipoDeducibleCobertura.class).setParameter("configuracionCoberturaId",configuracionCoberturaId).setParameter("seguridadAdecuada", seguridadAdecuada).getResultList();		
		 		
	}
	public List<PymeTipoDeducibleCobertura> buscarPorConfiguracionCoberturaTipoVariableIdNegativa(BigInteger configuracionCoberturaId, boolean seguridadAdecuada) {		
		return getEntityManager().createQuery("SELECT c FROM PymeTipoDeducibleCobertura c where c.configuracionCoberturaId=:configuracionCoberturaId and (c.seguridadAdecuada=:seguridadAdecuada or c.seguridadAdecuada IS NULL) ORDER BY c.tipoDeducibleCoberturaId", PymeTipoDeducibleCobertura.class).setParameter("configuracionCoberturaId",configuracionCoberturaId).setParameter("seguridadAdecuada", seguridadAdecuada).getResultList();		
		 		
	}
}
