package com.qbe.cotizador.dao.producto.agricola;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriParametroXCanal;

public class AgriParametroXCanalDAO extends EntityManagerFactoryDAO<AgriParametroXCanal> {
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
	public AgriParametroXCanalDAO (){
		super(AgriParametroXCanal.class);
	}
	
	public AgriParametroXCanal BuscarPorId(BigInteger parametroCanalId)
	{
		AgriParametroXCanal agriParametroXCanal = new AgriParametroXCanal();
		List<AgriParametroXCanal> result = getEntityManager().createNamedQuery("AgriParametroXCanal.buscarPorId").setParameter("parametroCanalId", parametroCanalId).getResultList();
		if (result.size()>0)
			agriParametroXCanal=result.get(0);
		return agriParametroXCanal;
	}
	
	public AgriParametroXCanal obtenerPorCanalTipoCultivo(BigInteger canalId, BigInteger tipoCultivoId)
	{
		AgriParametroXCanal agriParametroXCanal = new AgriParametroXCanal();
		List<AgriParametroXCanal> result = getEntityManager().createNamedQuery("AgriParametroXCanal.obtenerPorCanalTipoCultivo").setParameter("canalId", canalId).setParameter("tipoCultivoId", tipoCultivoId).getResultList();
		if (result.size()>0)
			agriParametroXCanal=result.get(0);
		return agriParametroXCanal;
	}
	
	public List<AgriParametroXCanal> ObtenerPorPuntoVenta(BigInteger puntoVentaId)
	{
		AgriParametroXCanal agriParametroXCanal = new AgriParametroXCanal();
		List<AgriParametroXCanal> result = getEntityManager().createNamedQuery("AgriParametroXCanal.buscarPorPuntoVenta").setParameter("puntoVentaId", puntoVentaId).getResultList();
		return result;
	}
	
	public  List<AgriParametroXCanal>BuscarTodos()
	{
		return getEntityManager().createNamedQuery("AgriParametroXCanal.findAll").getResultList();
	}
}