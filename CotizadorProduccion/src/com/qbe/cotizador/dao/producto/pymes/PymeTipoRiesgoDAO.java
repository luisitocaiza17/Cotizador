package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.model.AgriParametroXCanal;
import com.qbe.cotizador.model.PymeTipoRiesgo;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriParroquia;

public class PymeTipoRiesgoDAO extends EntityManagerFactoryDAO<PymeTipoRiesgo> {
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

	public PymeTipoRiesgoDAO() {
		// TODO Auto-generated constructor stub
		super(PymeTipoRiesgo.class);
	}
	
	
	public List<PymeTipoRiesgo> buscarTodos(){
		return getEntityManager().createNamedQuery("PymeTipoRiesgo.findAll").getResultList();
	}
	
	public PymeTipoRiesgo BuscarPorId(BigInteger id)
	{
		PymeTipoRiesgo pymeTipoRiesgo = new PymeTipoRiesgo();
		List<PymeTipoRiesgo> result = getEntityManager().createNamedQuery("PymeTipoRiesgo.buscarPorId").setParameter("id", id).getResultList();
		if (result.size()>0)
			pymeTipoRiesgo=result.get(0);
		return pymeTipoRiesgo;
	}
		
}