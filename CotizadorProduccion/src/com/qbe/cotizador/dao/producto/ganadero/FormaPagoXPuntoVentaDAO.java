package com.qbe.cotizador.dao.producto.ganadero;

import java.math.BigInteger;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.FormaPago;
import com.qbe.cotizador.model.FormaPagoXPuntoVenta;
import com.qbe.cotizador.model.FormaPagoXPuntoVentaVta;

public class FormaPagoXPuntoVentaDAO extends EntityManagerFactoryDAO<FormaPagoXPuntoVenta>{
	
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
	
	public FormaPagoXPuntoVentaDAO() {
		super(FormaPagoXPuntoVenta.class);
	}
	
	public List<FormaPagoXPuntoVenta> buscarTodosPV(BigInteger puntoVentaId){
		return getEntityManager().createNamedQuery("FormaPagoXPuntoVenta.buscarTodosPV").setParameter("puntoVentaId", puntoVentaId).getResultList();
	}
	
	public List<FormaPago> buscarTodos(){
		return getEntityManager().createNamedQuery("FormaPago.buscarTodos").getResultList();
	}
	
	public FormaPago buscarPorId(String id){
		FormaPago formaPago = new FormaPago();
		List<FormaPago> query = getEntityManager().createNamedQuery("FormaPago.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			formaPago =  query.get(0);
		return formaPago;
	}	
	
	public FormaPagoXPuntoVenta buscarFormaPagoXPuntoVentaPorId(String id){
		FormaPagoXPuntoVenta formaPago = new FormaPagoXPuntoVenta();
		List<FormaPagoXPuntoVenta> query = getEntityManager().createNamedQuery("FormaPagoXPuntoVenta.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			formaPago =  query.get(0);
		return formaPago;
	}	
	
	public List<FormaPagoXPuntoVentaVta> obtenerPorPuntoVentaId(BigInteger puntoVentaId){
		return getEntityManager().createNamedQuery("FormaPagoXPuntoVenta.obtenerPorPuntoVentaId").setParameter("id", puntoVentaId).getResultList();
	}
	
	public FormaPagoXPuntoVenta buscarFormaPago(BigInteger formaPagoId, BigInteger puntoVentaId){
		FormaPagoXPuntoVenta formaPago = new FormaPagoXPuntoVenta();
		List<FormaPagoXPuntoVenta> query = getEntityManager().createNamedQuery("FormaPagoXPuntoVenta.buscarFormaPago").setParameter("formaPagoId", formaPagoId).setParameter("puntoVentaId", puntoVentaId).getResultList();
		if(!query.isEmpty())
			formaPago =  query.get(0);
		return formaPago;
	}	

}
