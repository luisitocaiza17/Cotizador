package com.qbe.cotizador.dao.producto.vehiculo.tarifador;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Agente;
import com.qbe.cotizador.model.VhTari1Zona;

public class VhTari1ZonaDAO extends EntityManagerFactoryDAO<VhTari1Zona>{
	
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
	
	public VhTari1ZonaDAO() {
		super(VhTari1Zona.class);
	}
	
	public List<VhTari1Zona> buscarTodos (){				
		List<VhTari1Zona> listadoZonas = getEntityManager().createNamedQuery("VhTari1Zona.buscarTodos").getResultList();		
		return listadoZonas;				
	}
	
	public VhTari1Zona buscarPorId(String id){
		VhTari1Zona vhTari1Zona = new VhTari1Zona();
		List<VhTari1Zona> query = getEntityManager().createNamedQuery("VhTari1Zona.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			vhTari1Zona =  query.get(0);
		return vhTari1Zona;		
	}
	
}
