package com.qbe.cotizador.dao.entidad;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Agente;
import com.qbe.cotizador.model.AgenteAcuerdo;
import com.qbe.cotizador.model.Entidad;

public class AgenteDAO extends EntityManagerFactoryDAO<Agente>{
	
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
	
	public AgenteDAO() {
        super(Agente.class);
    }
	
	public List<Agente> buscarTodos(){
		return getEntityManager().createNamedQuery("Agente.buscarTodos").getResultList();		
	}
		
	public Agente buscarPorId(String id){
		Agente agente = new Agente();
		List<Agente> query = getEntityManager().createNamedQuery("Agente.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			agente =  query.get(0);
		return agente;		
	}
	
	public Agente buscarPorEntidadId(Entidad entidad){
		Agente agente = new Agente();
		List<Agente> query = getEntityManager().createNamedQuery("Agente.buscarPorEntidadId").setParameter("entidad", entidad).getResultList();
		if(!query.isEmpty())
			agente =  query.get(0);
		return agente;		
	}
	
	public List<Agente> buscarActivos(){
		return getEntityManager().createNamedQuery("Agente.buscarActivos").setParameter("valorActivo", true).getResultList();
	}
	
	public List<AgenteAcuerdo> buscarAgentesAcuerdo(String agenteId){
		return getEntityManager().createNamedQuery("AgenteAcuerdo.buscarAgentesAcuerdo").setParameter("valorActivo", true).setParameter("agenteId", agenteId).getResultList();
	}
	
	public List<Agente> buscarPorFiltros(String tipoIdentificacion, String identificacion, String nombre, String ensurance, String agente, boolean activo, boolean comision)
	{
		StringBuilder sentencia = new StringBuilder().append("Select a From Agente a where a.activo = :activo and a.comisionVariable = :comision and ");
		if(tipoIdentificacion != null && !tipoIdentificacion.equals(""))
		{
			sentencia.append(" a.entidad.tipoIdentificacion.id = :tipoIdentificacion ");
		}
		
		if(   ((tipoIdentificacion != null && !tipoIdentificacion.equals("")) && (identificacion != null && !identificacion.equals("")) )
				|| ((tipoIdentificacion != null && !tipoIdentificacion.equals("")) && (nombre != null && !nombre.equals(""))) 
				|| ( (tipoIdentificacion != null && !tipoIdentificacion.equals("")) && (ensurance != null && !ensurance.equals("")) )
				|| ( (tipoIdentificacion != null && !tipoIdentificacion.equals("")) && (agente != null && !agente.equals("")) ) )
		{
			sentencia.append(" and ");
		}
		
		if(identificacion != null && !identificacion.equals(""))
		{
			sentencia.append(" a.entidad.identificacion = :identificacion ");
		}
		
		if(((identificacion != null && !identificacion.equals("")) && (nombre != null && !nombre.equals(""))) 
				|| ( (identificacion != null && !identificacion.equals("")) && (ensurance != null && !ensurance.equals("")) )
				|| ( (identificacion != null && !identificacion.equals("")) && (agente != null && !agente.equals("")) ))
		{
			sentencia.append(" and ");
		}
		
		if(nombre != null && !nombre.equals(""))
		{
			sentencia.append(" a.entidad.nombreCompleto like :nombre ");
		}
		if(( (nombre != null && !nombre.equals("")) && (ensurance != null && !ensurance.equals("")) )
				|| ( (nombre != null && !nombre.equals("")) && (agente != null && !agente.equals("")) ))
		{
			sentencia.append(" and ");
		}
		if(ensurance != null && !ensurance.equals(""))
		{
			sentencia.append(" a.entidad.entEnsurance = :ensurance");
		}
		if(( (ensurance != null && !ensurance.equals("")) && (agente != null && !agente.equals("")) ))
		{
			sentencia.append(" and ");
		}
		if(agente != null && !agente.equals(""))
		{
			sentencia.append(" a.ageEnsurance = :agente");
		}
		
		Query q = getEntityManager().createQuery(sentencia.toString());
		q.setParameter("activo", activo);
		q.setParameter("comision", comision);
		if(tipoIdentificacion != null && !tipoIdentificacion.equals(""))
		{
			q.setParameter("tipoIdentificacion", tipoIdentificacion);
		}
		if(identificacion != null && !identificacion.equals(""))
		{
			q.setParameter("identificacion", identificacion);
		}
		if(nombre != null && !nombre.equals(""))
		{
			q.setParameter("nombre", "%"+nombre+"%");
		}
		if(ensurance != null && !ensurance.equals(""))
		{
			q.setParameter("ensurance", ensurance);
		}
		if(agente != null && !agente.equals(""))
		{
			q.setParameter("agente", agente);
		}
		
		return q.getResultList();
		
	}

}
