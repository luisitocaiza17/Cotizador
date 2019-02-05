package com.qbe.cotizador.dao.producto.agricola;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriArchivosCotizacion;
import com.qbe.cotizador.model.AgriSucreDetalle;
import com.qbe.cotizador.model.AgriSucreQbeCultivo;

public class AgriSucreQbeCultivoDAO extends EntityManagerFactoryDAO<AgriSucreQbeCultivo>{

	@PersistenceContext(name="CotizadorWebPC", unitName = "CotizadorWebPU" )	
	private EntityManager em;
	
	public AgriSucreQbeCultivoDAO(Class<AgriSucreQbeCultivo> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}
	
	public AgriSucreQbeCultivoDAO(){
		super(AgriSucreQbeCultivo.class);
	}

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
	
	public  List<AgriSucreQbeCultivo>BuscarCultivos(String codSucre)
	{
		return getEntityManager().createNamedQuery("AgriSucreQbeCultivo.BuscarCultivos").setParameter("codSucre", codSucre).getResultList();
	}

}