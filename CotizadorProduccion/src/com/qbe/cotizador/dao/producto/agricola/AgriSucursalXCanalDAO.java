package com.qbe.cotizador.dao.producto.agricola;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriSucursalXCanal;

public class AgriSucursalXCanalDAO extends EntityManagerFactoryDAO<AgriSucursalXCanal>  {

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

	public AgriSucursalXCanalDAO()	{
		super(AgriSucursalXCanal.class);
	}

	public AgriSucursalXCanal BuscarPorId(BigInteger sucursalCanalId)
	{
		AgriSucursalXCanal agriSucursalXCanal = new AgriSucursalXCanal();
		List<AgriSucursalXCanal> result = getEntityManager().createNamedQuery("AgriSucursalXCanal.buscarPorId").setParameter("sucursalCanalId", sucursalCanalId).getResultList();
		if (result.size()>0)
			agriSucursalXCanal=result.get(0);
		return agriSucursalXCanal;
	}
	
	public  List<AgriSucursalXCanal>BuscarTodos()
	{
		return getEntityManager().createNamedQuery("AgriSucursalXCanal.findAll").getResultList();
	}
	
	public List<AgriSucursalXCanal> BuscarPorCanalId(BigInteger canalId)
	{
		AgriSucursalXCanal agriSucursalXCanal = new AgriSucursalXCanal();
		List<AgriSucursalXCanal> result = getEntityManager().createNamedQuery("AgriSucursalXCanal.buscarPorCanalId").setParameter("canalId", canalId).getResultList();
		return result;
	}
	
	public AgriSucursalXCanal buscarCanalIntegracion(String sucursalIntegracion,BigInteger canalId)
	{
		AgriSucursalXCanal agriSucursalXCanal = new AgriSucursalXCanal();
		List<AgriSucursalXCanal> result = getEntityManager().createNamedQuery("AgriSucursalXCanal.buscarCanalIntegracion").setParameter("sucursalIntegracion", sucursalIntegracion).setParameter("canalId", canalId).getResultList();
		if (result.size()>0)
			agriSucursalXCanal=result.get(0);
		return agriSucursalXCanal;
	}
}
