package com.qbe.cotizador.dao.cotizacion;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Contenedor;

public class ContenedorDAO extends EntityManagerFactoryDAO <Contenedor>{
	
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
	
	public ContenedorDAO() {
        super(Contenedor.class);
    }
	
	public List<Contenedor> buscarTodos(){ 
		return getEntityManager().createNamedQuery("Contenedor.buscarTodos").getResultList();
	}
	
	public Contenedor buscarPorId(String id){   
		Contenedor contenedor = new Contenedor();
		List<Contenedor> query = getEntityManager().createNamedQuery("Contenedor.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			contenedor =  query.get(0);
		return contenedor;
	}
	
	public Contenedor buscarPorNumero(String numero){   
		Contenedor contenedor = new Contenedor();
		List<Contenedor> query = getEntityManager().createNamedQuery("Contenedor.buscarPorNumero").setParameter("numero", numero).getResultList();
		if(!query.isEmpty())
			contenedor =  query.get(0);
		return contenedor;
	}
	
	public Contenedor buscarPorEnsuranceId(String ensuranceId){   
		Contenedor contenedor = new Contenedor();
		List<Contenedor> query = getEntityManager().createNamedQuery("Contenedor.buscarPorEnsuranceId").setParameter("ensuranceId", ensuranceId).getResultList();
		if(!query.isEmpty())
			contenedor =  query.get(0);
		return contenedor;
	}
	
	public Contenedor buscarPorNumeroActivo(String numero, BigInteger ensurance)
	{
		try {
			Query q = getEntityManager().createNamedQuery("Contenedor.buscarPorNumeroActivo");
			q.setParameter("numero", numero);
			q.setParameter("ensurance", ensurance);
			return (Contenedor) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Contenedor> buscarContenedorPorFiltros(String numero, Timestamp fechaInicio, Timestamp fechaFin, BigInteger ensurance)
	{
		StringBuilder sentencia = new StringBuilder().append("Select c From Contenedor c where ");
		if(numero != null && !numero.equals(""))
		{
			sentencia.append("c.numero = :numero ");
		}
		if(((numero != null && !numero.equals("")) && ((fechaInicio!= null ) && (fechaFin != null ))) || ((numero != null && !numero.equals("")) && ensurance != null))
		{
			sentencia.append(" and ");
		}
		if((fechaInicio!= null ) && (fechaFin != null ))
		{
			sentencia.append("c.vigenciaDesde = :fechaInicio and c.vigenciaHasta = :fechaFin ");
		}
		
		if(((fechaInicio!= null ) && (fechaFin != null )) && ensurance != null)
		{
			sentencia.append(" and ");
		}
		
		if(ensurance != null)
		{
			sentencia.append(" c.idEnsurance = :ensurance");
		}
		
		Query q = getEntityManager().createQuery(sentencia.toString());
		if(numero != null && !numero.equals(""))
		{
			q.setParameter("numero", numero);
		}
		if((fechaInicio!= null ) && (fechaFin != null ))
		{
			q.setParameter("fechaInicio", fechaInicio);
			q.setParameter("fechaFin", fechaFin);
		}
		if(ensurance != null)
		{
			q.setParameter("ensurance", ensurance);
		}
		return q.getResultList();
	}
	
}
