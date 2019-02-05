package com.qbe.cotizador.dao.producto.agricola;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriTipoCalculo;

public class AgriTipoCalculoDAO extends EntityManagerFactoryDAO<AgriTipoCalculo> {
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
	public AgriTipoCalculoDAO(){
		super(AgriTipoCalculo.class);
	} 
	
	public AgriTipoCalculo BuscarPorId(BigInteger TipoCalculoId)
	{
		AgriTipoCalculo agriCiclo = new AgriTipoCalculo();
		List<AgriTipoCalculo> result = getEntityManager().createNamedQuery("AgriTipoCalculo.buscarPorId").setParameter("tipoCalculoId", TipoCalculoId).getResultList();
		if (result.size()>0)
			agriCiclo=result.get(0);
		return agriCiclo;
	}
	public  List<AgriTipoCalculo>BuscarTodos()
	{
		return getEntityManager().createNamedQuery("AgriTipoCalculo.findAll").getResultList();
	}
	public AgriTipoCalculo BuscarPorNombre(String nombre)
	{
		AgriTipoCalculo agriCiclo = new AgriTipoCalculo();
		List<AgriTipoCalculo> result = getEntityManager().createNamedQuery("AgriTipoCalculo.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if (result.size()>0)
			agriCiclo=result.get(0);
		return agriCiclo;
	}
}
