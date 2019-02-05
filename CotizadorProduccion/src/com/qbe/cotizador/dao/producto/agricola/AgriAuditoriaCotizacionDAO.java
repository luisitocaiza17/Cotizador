package com.qbe.cotizador.dao.producto.agricola;

import java.math.BigInteger;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriAuditoriaCotizacion;

public class AgriAuditoriaCotizacionDAO extends EntityManagerFactoryDAO<AgriAuditoriaCotizacion> {
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
	public AgriAuditoriaCotizacionDAO() {
		// TODO Auto-generated constructor stub
		super(AgriAuditoriaCotizacion.class);
	}
	public AgriAuditoriaCotizacion BuscarPorId(BigInteger Id)
	{
		AgriAuditoriaCotizacion agriAuditoria = new AgriAuditoriaCotizacion();
		List<AgriAuditoriaCotizacion> result = getEntityManager().createNamedQuery("AgriAuditoriaCotizacion.buscarPorId").setParameter("Id", Id).getResultList();
		if (result.size()>0)
			agriAuditoria=result.get(0);
		return agriAuditoria;
	}
	
	public AgriAuditoriaCotizacion BuscarPorCotizacinId(BigInteger CotizacionId)
	{
		AgriAuditoriaCotizacion agriAuditoria = new AgriAuditoriaCotizacion();
		List<AgriAuditoriaCotizacion> result = getEntityManager().createNamedQuery("AgriAuditoriaCotizacion.buscarPorCotizacionId").setParameter("CotizacionId", CotizacionId).getResultList();
		if (result.size()>0)
			agriAuditoria=result.get(0);
		return agriAuditoria;
	}
	
	public  List<AgriAuditoriaCotizacion>BuscarTodos()
	{
		return getEntityManager().createNamedQuery("AgriAuditoriaCotizacion.findAll").getResultList();
	}
}
