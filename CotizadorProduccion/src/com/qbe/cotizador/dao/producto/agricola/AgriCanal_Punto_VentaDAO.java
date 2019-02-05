package com.qbe.cotizador.dao.producto.agricola;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriCanal_Punto_Venta;

public class AgriCanal_Punto_VentaDAO extends EntityManagerFactoryDAO<AgriCanal_Punto_Venta>{
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
	
	public List<AgriCanal_Punto_Venta> BuscarPorCanal(String CanalId){
		List<AgriCanal_Punto_Venta> results = new ArrayList<AgriCanal_Punto_Venta>();
		TypedQuery<AgriCanal_Punto_Venta> query = null;
		String stringQuery= "SELECT c FROM AgriCanal_Punto_Venta c where (c.CanalId =:CanalId)";					
		String valoresWhereQuery = "";
		stringQuery = stringQuery+valoresWhereQuery+" ORDER BY c.NombrePuntoVenta ASC";
		
		query = getEntityManager().createQuery(stringQuery, AgriCanal_Punto_Venta.class);
		query.setParameter("CanalId",new BigInteger(CanalId) );
		results = query.getResultList();
		return results;
	}

	public AgriCanal_Punto_VentaDAO() {
		// TODO Auto-generated constructor stub
		super(AgriCanal_Punto_Venta.class);
	}
	
}
