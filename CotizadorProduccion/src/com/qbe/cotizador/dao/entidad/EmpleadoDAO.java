package com.qbe.cotizador.dao.entidad;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Cargo;
import com.qbe.cotizador.model.Empleado;
import com.qbe.cotizador.model.Entidad;

public class EmpleadoDAO extends EntityManagerFactoryDAO<Empleado>{
	
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
	
	public EmpleadoDAO() {
        super(Empleado.class);
	}
	
	public List<Empleado> buscarTodos(){
		return getEntityManager().createNamedQuery("Empleado.buscarTodos").getResultList();		
	}
		
	public Empleado buscarPorId(String id){
		Empleado empleado = new Empleado();
		List<Empleado> query = getEntityManager().createNamedQuery("Empleado.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			empleado =  query.get(0);
		return empleado;
	}
	public Empleado buscarPorEntidadId(Entidad entidad){
		Empleado empleado = new Empleado();
		List<Empleado> query = getEntityManager().createNamedQuery("Empleado.buscarPorEntidadId").setParameter("entidad", entidad).getResultList();
		if(!query.isEmpty())
			empleado =  query.get(0);
		return empleado;
	}
	
	public List<Empleado> buscarActivos(){
		return getEntityManager().createNamedQuery("Empleado.buscarActivos").setParameter("valorActivo", true).getResultList();
	}
	
	public List<Empleado> buscarPorCargo(Cargo cargo){
		return getEntityManager().createNamedQuery("Empleado.buscarPorCargo").setParameter("cargo", cargo).getResultList();
	}
	
	public List<Empleado> buscarPorFiltros(String tipoIdentificacion, String identificacion, String nombre, String apellido, String ensurance, String cargo, String sucursal, boolean activo)
	{
		StringBuilder sentencia = new StringBuilder().append("Select e From Empleado e Where e.activo = :activo and ");
		if(tipoIdentificacion != null && !tipoIdentificacion.equals(""))
		{
			sentencia.append(" e.entidad.tipoIdentificacion.id = :tipoIdentificacion ");
		}
		if(((tipoIdentificacion != null && !tipoIdentificacion.equals("")) && (identificacion != null && !identificacion.equals("")) )
				|| ((tipoIdentificacion != null && !tipoIdentificacion.equals("")) && (nombre != null && !nombre.equals(""))) 
				|| ( (tipoIdentificacion != null && !tipoIdentificacion.equals("")) && (apellido != null && !apellido.equals("")) )
				|| ( (tipoIdentificacion != null && !tipoIdentificacion.equals("")) && (ensurance != null && !ensurance.equals("")) ) 
				|| ( (tipoIdentificacion != null && !tipoIdentificacion.equals("")) && (cargo != null && !cargo.equals("")) )
				|| ( (tipoIdentificacion != null && !tipoIdentificacion.equals("")) && (sucursal != null && !sucursal.equals("")) ))
		{
			sentencia.append(" and ");
		}
		if(identificacion != null && !identificacion.equals(""))
		{
			sentencia.append(" e.entidad.identificacion = :identificacion ");
		}
		if(((identificacion != null && !identificacion.equals("")) && (nombre != null && !nombre.equals(""))) 
				|| ( (identificacion != null && !identificacion.equals("")) && (apellido != null && !apellido.equals("")) )
				|| ( (identificacion != null && !identificacion.equals("")) && (ensurance != null && !ensurance.equals("")) ) 
				|| ( (identificacion != null && !identificacion.equals("")) && (cargo != null && !cargo.equals("")) )
				|| ( (identificacion != null && !identificacion.equals("")) && (sucursal != null && !sucursal.equals("")) ))
		{
			sentencia.append(" and ");
		}
		if(nombre != null && !nombre.equals(""))
		{
			sentencia.append(" e.entidad.nombres like :nombre ");
		}
		if(( (nombre != null && !nombre.equals("")) && (apellido != null && !apellido.equals("")) )
				|| ( (nombre != null && !nombre.equals("")) && (ensurance != null && !ensurance.equals("")) ) 
				|| ( (nombre != null && !nombre.equals("")) && (cargo != null && !cargo.equals("")) )
				|| ( (nombre != null && !nombre.equals("")) && (sucursal != null && !sucursal.equals("")) ))
		{
			sentencia.append(" and ");
		}
		if(apellido != null && !apellido.equals(""))
		{
			sentencia.append(" e.entidad.apellidos like :apellido ");
		}
		if(( (apellido != null && !apellido.equals("")) && (ensurance != null && !ensurance.equals("")) ) 
				|| ( (apellido != null && !apellido.equals("")) && (cargo != null && !cargo.equals("")) )
				|| ( (apellido != null && !apellido.equals("")) && (sucursal != null && !sucursal.equals("")) ))
		{
			sentencia.append(" and ");
		}
		if(ensurance != null && !ensurance.equals(""))
		{
			sentencia.append(" e.entidad.entEnsurance = :ensurance ");
		}
		if(( (ensurance != null && !ensurance.equals("")) && (cargo != null && !cargo.equals("")) )
				|| ( (ensurance != null && !ensurance.equals("")) && (sucursal != null && !sucursal.equals("")) ))
		{
			sentencia.append(" and ");
		}
		if(cargo != null && !cargo.equals(""))
		{
			sentencia.append(" e.cargo.id = :cargo ");
		}
		if(( (cargo != null && !cargo.equals("")) && (sucursal != null && !sucursal.equals("")) ))
		{
			sentencia.append(" and ");
		}
		if(sucursal != null && !sucursal.equals(""))
		{
			sentencia.append(" e.sucursal.id = :sucursal ");
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
		if(ensurance != null && !ensurance.equals(""))
		{
			q.setParameter("ensurance", ensurance);
		}
		if(cargo != null && !cargo.equals(""))
		{
			q.setParameter("cargo", cargo);
		}
		if(sucursal != null && !sucursal.equals(""))
		{
			q.setParameter("sucursal", sucursal);
		}
		return q.getResultList();
	}
}
