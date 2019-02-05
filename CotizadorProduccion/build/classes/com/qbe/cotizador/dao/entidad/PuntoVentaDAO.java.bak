package com.qbe.cotizador.dao.entidad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PuntoVenta;

public class PuntoVentaDAO extends EntityManagerFactoryDAO<PuntoVenta>{

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
	
	public PuntoVentaDAO() {
        super(PuntoVenta.class);
    }
	
	public List<PuntoVenta> buscarTodos(){
		return getEntityManager().createNamedQuery("PuntoVenta.buscarTodos").getResultList();		
	}
	
	public PuntoVenta buscarPorId(String id){
		PuntoVenta ptoVenta = null;
		List<PuntoVenta> query = getEntityManager().createNamedQuery("PuntoVenta.buscarPorId").setParameter("id", id).getResultList();
		if(query.isEmpty())
			ptoVenta = null;
		else
			ptoVenta =  query.get(0);
		return ptoVenta;
	}
	
	public PuntoVenta buscarPorNombre(String nombre){
		PuntoVenta ptoVenta = new PuntoVenta();
		List<PuntoVenta> query = getEntityManager().createNamedQuery("PuntoVenta.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			ptoVenta =  query.get(0);			
		return ptoVenta;
	}
		
	public List<PuntoVenta> buscarActivos(){
		return getEntityManager().createNamedQuery("PuntoVenta.buscarActivos").setParameter("valorActivo", true).getResultList();		
	}
	
	public List<PuntoVenta> buscarPtosEnsurance() {
		return getEntityManager().createNamedQuery("PuntoVenta.buscarPtosEnsurance").getResultList();
	}
	 
}