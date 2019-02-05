package com.qbe.cotizador.dao.producto.agricola;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriCiclo;
import com.qbe.cotizador.model.AgriRegla;
import com.qbe.cotizador.model.AgriReglaAuditoria;
import com.qbe.cotizador.model.AgriReglaNueva;
import com.qbe.cotizador.model.AgriTipoCalculo;
import com.qbe.cotizador.model.AgriTipoCultivo;

public class AgriReglaAuditoriaNuevaDAO extends EntityManagerFactoryDAO<AgriReglaNueva> {
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

	public AgriReglaAuditoriaNuevaDAO() {
		// TODO Auto-generated constructor stub
		super(AgriReglaNueva.class);
	}	
	
	public List<AgriReglaNueva>BuscarTodos(){
		return getEntityManager().createNamedQuery("AgriReglaNueva.buscarTodos").getResultList();
	}	
	

}
