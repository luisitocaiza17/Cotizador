package com.qbe.cotizador.dao.producto.agricola;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriCanal;

public class AgriCanalDAO extends EntityManagerFactoryDAO<AgriCanal> {
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
	public AgriCanalDAO (){
		super(AgriCanal.class);
	}
	
	public AgriCanal BuscarPorId(BigInteger canalId)
	{
		AgriCanal agriCanal = new AgriCanal();
		List<AgriCanal> result = getEntityManager().createNamedQuery("AgriCanal.obtenerPorId").setParameter("canalId", canalId).getResultList();
		if (result.size()>0)
			agriCanal=result.get(0);
		return agriCanal;
	}
	
	public  List<AgriCanal>BuscarTodos()
	{
		return getEntityManager().createNamedQuery("AgriCanal.findAll").getResultList();
	}
	
	public AgriCanal buscarPorNombre(String nombre)
	{
		AgriCanal agriCanal = new AgriCanal();
		List<AgriCanal> result = getEntityManager().createNamedQuery("AgriCanal.obtenerPorNombre").setParameter("nombre", nombre).getResultList();
		if (result.size()>0)
			agriCanal=result.get(0);
		return agriCanal;
	}
}
