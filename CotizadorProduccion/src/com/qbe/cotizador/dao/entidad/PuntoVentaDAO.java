package com.qbe.cotizador.dao.entidad;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	
	public List<String> buscarPtosEnsurance() {
		return getEntityManager().createNamedQuery("PuntoVenta.buscarPtosEnsurance").getResultList();
	}
	
	// Buscamos todos los puntos de ventas que pertenecen a la matriz asignada
	public List<PuntoVenta> buscarPuntosVentaPorMatriz(String matrizPuntoVentaId){
		return getEntityManager().createNamedQuery("PuntoVenta.buscarPuntosVentaPorMatriz").setParameter("valorActivo", true).setParameter("matrizPuntoVentaId", matrizPuntoVentaId).getResultList();		
	}
	
	@SuppressWarnings("unchecked")
	public List<PuntoVenta> buscarMatrices()
	{
		Query q = getEntityManager().createNamedQuery("PuntoVenta.buscarMatrices");
		q.setParameter("activo", true);
		q.setParameter("matriz", true);
		return q.getResultList();
	}
	
	public List<PuntoVenta> buscarPorFiltros(String sucursal, String puntoVenta, String agente, String nombre, boolean activo)
	{
		StringBuilder sentencia = new StringBuilder().append("Select pv from PuntoVenta pv where pv.activo = :activo");
		/*if(!matriz)
		{
			sentencia.append(" (pv.esMatriz = :matriz or pv.esMatriz is null) and");
		}
		else
		{
			sentencia.append(" pv.esMatriz = :matriz and ");
		}*/
		if(sucursal != null && !sucursal.equals(""))
		{
			sentencia.append(" and pv.sucursal.id = :sucursal ");
		}
		/*if( ( (sucursal != null && !sucursal.equals("")) && (puntoVenta != null && !puntoVenta.equals("")) ) 
				|| ( (sucursal != null && !sucursal.equals("")) && (agente != null && !agente.equals("")) ) 
				|| ( (sucursal != null && !sucursal.equals("")) && (nombre != null && !nombre.equals("")) ) )
		{
			sentencia.append(" and ");
		}*/
		if(puntoVenta != null && !puntoVenta.equals(""))
		{
			sentencia.append(" and pv.ptoEnsurance = :puntoVenta ");
		}
		/*if((puntoVenta != null && !puntoVenta.equals("") && (agente != null && !agente.equals("")))
				|| ( (puntoVenta != null && !puntoVenta.equals("")) && (nombre != null && !nombre.equals("")) ))
		{
			sentencia.append(" and ");
		}*/
		if(agente != null && !agente.equals(""))
		{
			sentencia.append(" and pv.agenteId = :agente ");
		}
		/*if(( (agente != null && !agente.equals("")) && (nombre != null && !nombre.equals("")) ))
		{
			sentencia.append(" and ");
		}*/
		if(nombre != null && !nombre.equals(""))
		{
			sentencia.append(" and pv.nombre like :nombre");
		}
		
		Query q = getEntityManager().createQuery(sentencia.toString());
		//q.setParameter("matriz", matriz);
		q.setParameter("activo", activo);
		if(sucursal != null && !sucursal.equals(""))
		{
			q.setParameter("sucursal", sucursal);
		}
		if(puntoVenta != null && !puntoVenta.equals(""))
		{
			q.setParameter("puntoVenta", puntoVenta);
		}
		if(agente != null && !agente.equals(""))
		{
			q.setParameter("agente", agente);
		}
		if(nombre != null && !nombre.equals(""))
		{
			q.setParameter("nombre", "%"+nombre+"%");
		}
		return q.getResultList();
	}
	 
}