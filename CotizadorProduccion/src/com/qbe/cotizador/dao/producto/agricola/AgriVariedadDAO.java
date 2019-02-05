package com.qbe.cotizador.dao.producto.agricola;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriVariedad;

public class AgriVariedadDAO extends EntityManagerFactoryDAO<AgriVariedad> {

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
	public AgriVariedadDAO() {
		// TODO Auto-generated constructor stub
		super(AgriVariedad.class);
	}
	public AgriVariedad BuscarPorId(BigInteger variedadId)
	{
		AgriVariedad agriVariedad = new AgriVariedad();
		List<AgriVariedad> result = getEntityManager().createNamedQuery("AgriVariedad.buscarPorId").setParameter("variedadId", variedadId).getResultList();
		if (result.size()>0)
			agriVariedad=result.get(0);
		return agriVariedad;
	}
	public List<AgriVariedad> BuscarPorTipoCultivoId(BigInteger tipoCultivoId)
	{
		return getEntityManager().createNamedQuery("AgriVariedad.buscarPorTipoCultivoId").setParameter("tipoCultivoId", tipoCultivoId).getResultList();
	}
	public List<AgriVariedad> BuscarTodos()
	{
		return getEntityManager().createNamedQuery("AgriVariedad.findAll").getResultList();
	}

}
