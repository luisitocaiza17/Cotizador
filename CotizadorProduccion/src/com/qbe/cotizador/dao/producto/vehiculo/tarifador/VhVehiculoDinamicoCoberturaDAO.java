package com.qbe.cotizador.dao.producto.vehiculo.tarifador;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Cobertura;
import com.qbe.cotizador.model.VhVehiculoDinamicoCobertura;

public class VhVehiculoDinamicoCoberturaDAO extends EntityManagerFactoryDAO<VhVehiculoDinamicoCobertura>{
	
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
	
	public VhVehiculoDinamicoCoberturaDAO() {
		super(VhVehiculoDinamicoCobertura.class);
	}
	/* Metodo para obtener todas las coberturas amparos adicionales y textos por Cobertura Principal */
	public List <VhVehiculoDinamicoCobertura> buscarPorCoberturaPrincipalSecundarias (Cobertura coberturaPrincipal){		
		return getEntityManager().createNamedQuery("VhVehiculoDinamicoCobertura.buscarPorCoberturaPrincipalId").setParameter("cobertura", coberturaPrincipal).getResultList();								
	}
		
}
