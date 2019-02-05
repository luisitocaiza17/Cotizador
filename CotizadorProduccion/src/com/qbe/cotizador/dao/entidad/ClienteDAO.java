package com.qbe.cotizador.dao.entidad;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Entidad;

public class ClienteDAO extends EntityManagerFactoryDAO<Cliente>{
	
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
	
	public ClienteDAO() {
        super(Cliente.class);
    }
	
	public List<Cliente> buscarTodos(){
		return getEntityManager().createNamedQuery("Cliente.buscarTodos").getResultList();		
	}
		
	public Cliente buscarPorId(String id){
		Cliente cliente = new Cliente();
		List<Cliente> query = getEntityManager().createNamedQuery("Cliente.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			cliente =  query.get(0);
		return cliente;		
	}
	public Cliente buscarPorEntidadId(Entidad entidad){
		Cliente cliente = new Cliente();
		List<Cliente> query = getEntityManager().createNamedQuery("Cliente.buscarPorEntidadId").setParameter("entidad", entidad).getResultList();
		if(!query.isEmpty())
			cliente =  query.get(0);
		return cliente;		
	}
	
	public List<Cliente> buscarActivos(){
		return getEntityManager().createNamedQuery("Cliente.buscarActivos").setParameter("valorActivo", true).getResultList();		
	}		
	
	public List<Cliente> buscarPorFiltros(String tipoIdentificacion, String identificacion, String nombre, String apellido, String actividadEconomica, boolean activo)
	{
		StringBuilder sentencia = new StringBuilder().append("Select c From Cliente c where c.activo = :activo and ");
		if(tipoIdentificacion != null && !tipoIdentificacion.equals(""))
		{
			sentencia.append(" c.entidad.tipoIdentificacion.id = :tipoIdentificacion ");
		}
		
		if(((tipoIdentificacion != null && !tipoIdentificacion.equals("")) && (identificacion != null && !identificacion.equals("")) )
				|| ((tipoIdentificacion != null && !tipoIdentificacion.equals("")) && (nombre != null && !nombre.equals(""))) 
				|| ( (tipoIdentificacion != null && !tipoIdentificacion.equals("")) && (apellido != null && !apellido.equals("")) )
				|| ( (tipoIdentificacion != null && !tipoIdentificacion.equals("")) && (actividadEconomica != null && !actividadEconomica.equals("")) ) )
		{
			sentencia.append(" and ");
		}
		
		if(identificacion != null && !identificacion.equals(""))
		{
			sentencia.append(" c.entidad.identificacion = :identificacion ");
		}
		
		if(((identificacion != null && !identificacion.equals("")) && (nombre != null && !nombre.equals(""))) 
				|| ( (identificacion != null && !identificacion.equals("")) && (apellido != null && !apellido.equals("")) )
				|| ( (identificacion != null && !identificacion.equals("")) && (actividadEconomica != null && !actividadEconomica.equals("")) ))
		{
			sentencia.append(" and ");
		}
		if(nombre != null && !nombre.equals(""))
		{
			sentencia.append(" c.entidad.nombres like :nombre ");
		}
		if(( (nombre != null && !nombre.equals("")) && (apellido != null && !apellido.equals("")) )
				|| ( (nombre != null && !nombre.equals("")) && (actividadEconomica != null && !actividadEconomica.equals("")) ))
		{
			sentencia.append(" and ");
		}
		if(apellido != null && !apellido.equals(""))
		{
			sentencia.append(" c.entidad.apellidos like :apellido ");
		}
		if(( (apellido != null && !apellido.equals("")) && (actividadEconomica != null && !actividadEconomica.equals("")) ))
		{
			sentencia.append(" and ");
		}
		if(actividadEconomica != null && !actividadEconomica.equals(""))
		{
			sentencia.append(" c.actividadEconomica.id = :actividadEconomica");
		}
		
		
		Query q = getEntityManager().createQuery(sentencia.toString());
		q.setParameter("activo", activo);
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
		if(apellido != null && !apellido.equals(""))
		{
			q.setParameter("apellido", "%"+apellido+"%");
		}
		if(actividadEconomica != null && !actividadEconomica.equals(""))
		{
			q.setParameter("actividadEconomica", actividadEconomica);
		}
		return q.getResultList();
		
	}
}
