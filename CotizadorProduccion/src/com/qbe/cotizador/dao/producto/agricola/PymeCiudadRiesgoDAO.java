package com.qbe.cotizador.dao.producto.agricola;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.AgriCotizacionEndoso;
import com.qbe.cotizador.model.Canton;
import com.qbe.cotizador.model.Ciudad;
import com.qbe.cotizador.model.Provincia;
import com.qbe.cotizador.model.PymeCiudadRiesgo;
import com.qbe.cotizador.model.UsuariosOffline;

public class PymeCiudadRiesgoDAO extends EntityManagerFactoryDAO<PymeCiudadRiesgo> {
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
	public PymeCiudadRiesgoDAO (){
		super(PymeCiudadRiesgo.class);
	}
	
	public PymeCiudadRiesgo BuscarPorId(BigInteger id)
	{
		PymeCiudadRiesgo pymeCiudad = new PymeCiudadRiesgo();
		@SuppressWarnings("unchecked")
		List<PymeCiudadRiesgo> result = getEntityManager().createNamedQuery("PymeCiudadRiesgo.obtenerPorId").setParameter("id", id).getResultList();
		if (result.size()>0)
			pymeCiudad=result.get(0);
		return pymeCiudad;
	}
	
	public List<PymeCiudadRiesgo> BuscarPorCiudad(Canton canton)
	{
		PymeCiudadRiesgo pymeCiudad = new PymeCiudadRiesgo();
		@SuppressWarnings("unchecked")
		List<PymeCiudadRiesgo> result = getEntityManager().createNamedQuery("PymeCiudadRiesgo.obtenerPorCiudad").setParameter("canton", canton).getResultList();
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public  List<PymeCiudadRiesgo>BuscarTodos()
	{
		return getEntityManager().createNamedQuery("PymeCiudadRiesgo.findAll").getResultList();
	}	
	
	public List<PymeCiudadRiesgo> cargarTodosKendo(int Skip, int Take, Provincia provincia,Canton ciudad){
		
		TypedQuery<PymeCiudadRiesgo> query = null;
		
		String stringQuery= "SELECT p FROM PymeCiudadRiesgo p WHERE p.id IS NOT NULL";	
		String valoresWhereQuery = "";
		if(provincia.getId()!=null)
			valoresWhereQuery=valoresWhereQuery+" AND p.provincia =:provincia ";
		if(ciudad.getId()!=null)
			valoresWhereQuery=valoresWhereQuery+" AND p.canton =:ciudad ";
		stringQuery = stringQuery+valoresWhereQuery;
		query = getEntityManager().createQuery(stringQuery, PymeCiudadRiesgo.class);
		
		if(provincia.getId()!=null)
			query.setParameter("provincia", provincia);			
		if(ciudad.getId()!=null)
			query.setParameter("ciudad", ciudad);	
		
		return query.setMaxResults(Take).setFirstResult(Skip).getResultList();			
		
	}
    
    public long cargarTodosKendoPorNumero(int Skip, int Take, Provincia provincia,Canton ciudad){
		
    	Query query = null;	
		
		String stringQuery= "SELECT count(p.id) FROM PymeCiudadRiesgo p WHERE p.id IS NOT NULL";	
		String valoresWhereQuery = "";
		if(provincia.getId()!=null)
			valoresWhereQuery=valoresWhereQuery+" AND p.provincia =:provincia ";
		if(ciudad.getId()!=null)
			valoresWhereQuery=valoresWhereQuery+" AND p.canton =:ciudad ";
		stringQuery = stringQuery+valoresWhereQuery;
		query = getEntityManager().createQuery(stringQuery, PymeCiudadRiesgo.class);
		
		if(provincia.getId()!=null)
			query.setParameter("provincia", provincia);			
		if(ciudad.getId()!=null)
			query.setParameter("ciudad", ciudad);	
		long total = (long) query.getSingleResult();
		return total;
	}
	
}
