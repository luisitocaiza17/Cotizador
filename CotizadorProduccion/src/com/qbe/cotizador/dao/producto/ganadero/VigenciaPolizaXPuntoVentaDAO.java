package com.qbe.cotizador.dao.producto.ganadero;

import java.math.BigInteger;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.VigenciaPolizaXPuntoVenta;
import com.qbe.cotizador.model.VigenciaPolizaXPuntoVentaVta;

public class VigenciaPolizaXPuntoVentaDAO extends EntityManagerFactoryDAO<VigenciaPolizaXPuntoVenta> {
	
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
	
	public VigenciaPolizaXPuntoVentaDAO() {
		super(VigenciaPolizaXPuntoVenta.class);
	}
	
	public List<VigenciaPolizaXPuntoVenta> buscarTodos(){
		return getEntityManager().createNamedQuery("VigenciaPolizaXPuntoVenta.buscarTodos").getResultList();
	}
	
	public VigenciaPolizaXPuntoVenta buscarPorId(String id){
		VigenciaPolizaXPuntoVenta vigencia = new VigenciaPolizaXPuntoVenta();
		List<VigenciaPolizaXPuntoVenta> query = getEntityManager().createNamedQuery("VigenciaPolizaXPuntoVenta.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			vigencia =  query.get(0);	
		return vigencia;
	}
	
	public List<VigenciaPolizaXPuntoVenta> buscarTodosPV(BigInteger puntoVentaId){
		return getEntityManager().createNamedQuery("VigenciaPolizaXPuntoVenta.buscarTodosPV").setParameter("puntoVentaId", puntoVentaId).getResultList();
	}
	

	public List<VigenciaPolizaXPuntoVentaVta> obtenerPorPuntoVentaId(BigInteger puntoVentaId){
		return getEntityManager().createNamedQuery("VigenciaPolizaXPuntoVentaVta.obtenerPorPuntoVentaId").setParameter("id", puntoVentaId).getResultList();
	}
	
	public VigenciaPolizaXPuntoVenta buscarVigenciaPoliza(BigInteger vigenciaPolizaId,BigInteger puntoVentaId){
		VigenciaPolizaXPuntoVenta vigencia = new VigenciaPolizaXPuntoVenta();
		List<VigenciaPolizaXPuntoVenta> query = getEntityManager().createNamedQuery("VigenciaPolizaXPuntoVenta.buscarVigenciaPoliza").setParameter("vigenciaPolizaId", vigenciaPolizaId).setParameter("puntoVentaId", puntoVentaId).getResultList();
		if(!query.isEmpty())
			vigencia =  query.get(0);
		return vigencia;
	}
	
}
