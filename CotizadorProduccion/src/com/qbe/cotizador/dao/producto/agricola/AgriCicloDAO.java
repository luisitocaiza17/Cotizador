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

public class AgriCicloDAO extends EntityManagerFactoryDAO<AgriCiclo> {
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
	public AgriCicloDAO (){
		super(AgriCiclo.class);
	}
	
	public AgriCiclo BuscarPorId(BigInteger CicloId)
	{
		AgriCiclo agriCiclo = new AgriCiclo();
		List<AgriCiclo> result = getEntityManager().createNamedQuery("AgriCiclo.buscarPorId").setParameter("clicloId", CicloId).getResultList();
		if (result.size()>0)
			agriCiclo=result.get(0);
		return agriCiclo;
	}
	
	public  List<AgriCiclo>BuscarTodos()
	{
		return getEntityManager().createNamedQuery("AgriCiclo.findAll").getResultList();
	}
}
