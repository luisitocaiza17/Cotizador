package com.qbe.cotizador.dao.producto.ganadero;

import java.math.BigInteger;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.ParametroXPuntoVenta;

public class ParametroXPuntoVentaDAO extends EntityManagerFactoryDAO <ParametroXPuntoVenta>{
	
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
	
	public ParametroXPuntoVentaDAO() {
		super(ParametroXPuntoVenta.class);
	}
	
	public List<ParametroXPuntoVenta> buscarTodos(){
		return getEntityManager().createNamedQuery("ParametroXPuntoVenta.buscarTodos").getResultList();
	}
	
	public ParametroXPuntoVenta obtenerPorPuntoVentaId(BigInteger puntoVentaId){
		ParametroXPuntoVenta parametro = new ParametroXPuntoVenta();
		List<ParametroXPuntoVenta> query = getEntityManager().createNamedQuery("ParametroXPuntoVenta.obtenerPorPuntoVentaId").setParameter("puntoVentaId", puntoVentaId).getResultList();
		if(!query.isEmpty())
			parametro =  query.get(0);			
		return parametro;
	}
}
