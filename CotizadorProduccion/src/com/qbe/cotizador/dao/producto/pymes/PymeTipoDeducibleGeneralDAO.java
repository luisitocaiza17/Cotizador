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
import com.qbe.cotizador.model.PymeTipoDeducibleGeneral;

public class PymeTipoDeducibleGeneralDAO extends EntityManagerFactoryDAO<PymeTipoDeducibleGeneral>{
	
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

	public PymeTipoDeducibleGeneralDAO() {		
		super(PymeTipoDeducibleGeneral.class);	
	}
	
	public List<PymeTipoDeducibleGeneral> buscarTodos() {
		return getEntityManager().createQuery("SELECT p FROM PymeTipoDeducibleCobertura p", PymeTipoDeducibleGeneral.class).getResultList();		
	}
	
	public PymeTipoDeducibleGeneral buscarPorId(BigInteger tipoDeducibleCoberturaId) {
		PymeTipoDeducibleGeneral tipoDeducibleCobertura = new PymeTipoDeducibleGeneral();
		List<PymeTipoDeducibleGeneral> results = getEntityManager().createQuery("SELECT c FROM PymeTipoDeducibleGeneral c where c.tipoDeducibleGeneralId=:tipoDeducibleGeneralId", PymeTipoDeducibleGeneral.class).setParameter("tipoDeducibleGeneralId",tipoDeducibleCoberturaId).getResultList();
		if(results.size() > 0)
			tipoDeducibleCobertura = results.get(0);
		return tipoDeducibleCobertura;		
	}
	
	public List<PymeTipoDeducibleGeneral> buscarPorRamoGrupoProductoId(BigInteger ramoId, BigInteger grupoPorProductoId) {		
		return getEntityManager().createQuery("SELECT c FROM PymeTipoDeducibleGeneral c where c.ramoId=:ramoId and c.grupoPorProductoId=:grupoPorProductoId ORDER BY c.tipoDeducibleGeneralId", PymeTipoDeducibleGeneral.class).setParameter("ramoId",ramoId).setParameter("grupoPorProductoId",grupoPorProductoId).getResultList();		
		 		
	}	

}
