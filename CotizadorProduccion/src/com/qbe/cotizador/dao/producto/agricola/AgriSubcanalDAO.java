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
import com.qbe.cotizador.model.AgriSubcanal;

public class AgriSubcanalDAO extends EntityManagerFactoryDAO<AgriSubcanal> {
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
	public AgriSubcanalDAO (){
		super(AgriSubcanal.class);
	}
	
	public AgriSubcanal BuscarPorId(BigInteger SubCanalID)
	{
		AgriSubcanal agriSubcanal = new AgriSubcanal();
		List<AgriSubcanal> result = getEntityManager().createNamedQuery("AgriSubcanal.buscarPorId").setParameter("Id", SubCanalID).getResultList();
		if (result.size()>0)
			agriSubcanal=result.get(0);
		return agriSubcanal;
	}
	
	public  List<AgriSubcanal>BuscarTodos()
	{
		return getEntityManager().createNamedQuery("AgriSubcanal.findAll").getResultList();
	}
	
	public  AgriSubcanal BuscarTodosCanal(String Canal,String AgriPXPuntoVenta, String CodIntegracion)
	{
		AgriSubcanal agriSubcanal = new AgriSubcanal();
		List<AgriSubcanal> result = getEntityManager().createNamedQuery("AgriSubcanal.findAllCanal").setParameter("CanalId", Canal).setParameter("agriParametroXPuntoVenta", AgriPXPuntoVenta).setParameter("CodIntegracion", CodIntegracion).getResultList();
		if (result.size()>0)
			agriSubcanal=result.get(0);
		return agriSubcanal;
	}
}