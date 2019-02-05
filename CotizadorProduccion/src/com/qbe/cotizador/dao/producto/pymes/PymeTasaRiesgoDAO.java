package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Ciudad;
import com.qbe.cotizador.model.Provincia;
import com.qbe.cotizador.model.PymeCiudadRiesgo;
import com.qbe.cotizador.model.PymeTasaRiesgo;


public class PymeTasaRiesgoDAO extends EntityManagerFactoryDAO<PymeTasaRiesgo> {
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
	public PymeTasaRiesgoDAO (){
		super(PymeTasaRiesgo.class);
	}
	
	public PymeTasaRiesgo BuscarPorId(BigInteger id)
	{
		PymeTasaRiesgo pymeTasaRiesgo = new PymeTasaRiesgo();
		@SuppressWarnings("unchecked")
		List<PymeTasaRiesgo> result = getEntityManager().createNamedQuery("PymeTasaRiesgo.obtenerPorId").setParameter("id", id).getResultList();
		if (result.size()>0)
			pymeTasaRiesgo=result.get(0);
		return pymeTasaRiesgo;
	}
	
	@SuppressWarnings("unchecked")
	public  List<PymeTasaRiesgo>BuscarTodos()
	{
		return getEntityManager().createNamedQuery("PymeTasaRiesgo.findAll").getResultList();
	}	
	
	public  List<PymeTasaRiesgo>BuscarPorCotizacion(BigInteger coberturaConfiguracionId)
	{
		return getEntityManager().createNamedQuery("PymeTasaRiesgo.buscarPorConfiguracion").setParameter("id", coberturaConfiguracionId).getResultList();
	}	
	
	public List<PymeCiudadRiesgo> cargarTodosKendo(int Skip, int Take, Provincia provincia,Ciudad ciudad){
		
		TypedQuery<PymeCiudadRiesgo> query = null;
		
		String stringQuery= "SELECT p FROM PymeCiudadRiesgo p WHERE p.id IS NOT NULL";	
		String valoresWhereQuery = "";
		if(provincia.getId()!=null)
			valoresWhereQuery=valoresWhereQuery+" AND p.provincia =:provincia ";
		if(ciudad.getId()!=null)
			valoresWhereQuery=valoresWhereQuery+" AND p.ciudad =:ciudad ";
		stringQuery = stringQuery+valoresWhereQuery;
		query = getEntityManager().createQuery(stringQuery, PymeCiudadRiesgo.class);
		
		if(provincia.getId()!=null)
			query.setParameter("provincia", provincia);			
		if(ciudad.getId()!=null)
			query.setParameter("ciudad", ciudad);	
		
		return query.setMaxResults(Take).setFirstResult(Skip).getResultList();			
		
	}
    
    public long cargarTodosKendoPorNumero(int Skip, int Take, Provincia provincia,Ciudad ciudad){
		
    	Query query = null;	
		
		String stringQuery= "SELECT count(p.id) FROM PymeCiudadRiesgo p WHERE p.id IS NOT NULL";	
		String valoresWhereQuery = "";
		if(provincia.getId()!=null)
			valoresWhereQuery=valoresWhereQuery+" AND p.provincia =:provincia ";
		if(ciudad.getId()!=null)
			valoresWhereQuery=valoresWhereQuery+" AND p.ciudad =:ciudad ";
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

