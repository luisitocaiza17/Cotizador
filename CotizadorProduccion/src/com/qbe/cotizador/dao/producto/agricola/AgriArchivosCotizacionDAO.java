package com.qbe.cotizador.dao.producto.agricola;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriArchivosCotizacion;

public class AgriArchivosCotizacionDAO extends EntityManagerFactoryDAO<AgriArchivosCotizacion>{
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
	public AgriArchivosCotizacionDAO() {
		// TODO Auto-generated constructor stub
		super(AgriArchivosCotizacion.class);
	}
	public AgriArchivosCotizacion BuscarPorId(BigInteger Id)
	{
		AgriArchivosCotizacion agriAuditoria = new AgriArchivosCotizacion();
		List<AgriArchivosCotizacion> result = getEntityManager().createNamedQuery("AgriArchivosCotizacion.buscarPorId").setParameter("Id", Id).getResultList();
		if (result.size()>0)
			agriAuditoria=result.get(0);
		return agriAuditoria;
	}
	
	public  List<AgriArchivosCotizacion>BuscarTodos()
	{
		return getEntityManager().createNamedQuery("AgriArchivosCotizacion.findAll").getResultList();
	}
}
