package com.qbe.cotizador.dao.entidad;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.transaction.entidad.UsuarioTransaction;

public class UsuarioDAO extends EntityManagerFactoryDAO<Usuario>{

	@PersistenceContext(name="CotizadorWebPC", unitName = "CotizadorWebPU" )	
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {	
		if(em == null){
			Context initCtx = null;
			try {
				initCtx = new InitialContext();
				em = (javax.persistence.EntityManager) initCtx.lookup("java:comp/env/CotizadorWebPC");
				em.getEntityManagerFactory().getCache().evictAll();
			} catch (NamingException e) { 
				e.printStackTrace();
			}		
		}		                
		return em;
	}
	
	public UsuarioDAO() {
        super(Usuario.class);
    }
	
	public List<Usuario> buscarTodos(){   
		return getEntityManager().createNamedQuery("Usuario.buscarTodos").getResultList();
	}
	
	
	public Usuario buscarPorId(String id){
		Usuario usuario = new Usuario();
		List<Usuario> query = getEntityManager().createNamedQuery("Usuario.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			usuario =  query.get(0);
		return usuario;
	}
	
	public Usuario buscarPorEntidadId(Entidad entidad){
		Usuario usuario = new Usuario();
		List<Usuario> query = getEntityManager().createNamedQuery("Usuario.buscarPorEntidadId").setParameter("entidad", entidad).getResultList();
		if(!query.isEmpty())
			usuario =  query.get(0);
		return usuario;
	}

	public List<Usuario> buscarActivos(){
		return getEntityManager().createNamedQuery("Usuario.buscarActivos").setParameter("valorActivo", true).getResultList();
	}
	
	public Boolean buscarPorConfirmacionMail(String confirmacionMail){
		Usuario usuario = new Usuario();
		UsuarioTransaction usuarioTransaction = new UsuarioTransaction();
		Boolean retorno = false;
		List<Usuario> query = getEntityManager().createNamedQuery("Usuario.buscarPorConfirmacionMail").setParameter("confirmacion", confirmacionMail).getResultList();
		int existe = query.size();
			
		if(existe != 0){			
			UserTransaction ut = null;
			try{
				ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
				ut.begin();
			
				usuario = query.get(0);	
				usuario.setValidacionMail("");
				usuarioTransaction.editar(usuario);
				ut.commit();
			}catch(Exception e) {
				try {
					ut.rollback();
				} catch (IllegalStateException | SecurityException | SystemException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();			    
			}		
			retorno = true;
			
		}
		return retorno;			
		}
	
	public Usuario buscarPorLogin(String login){
		Usuario usuario = new Usuario();
		List<Usuario> query = getEntityManager().createNamedQuery("Usuario.buscarPorLogin").setParameter("login", login).getResultList();
		if(!query.isEmpty())
			usuario =  query.get(0);
		return usuario;
	}
	
	public Usuario buscarPorEntidad(Entidad entidad){
		Usuario usuario = new Usuario();
		List<Usuario> query = getEntityManager().createNamedQuery("Usuario.buscarPorEntidad").setParameter("entidad", entidad).getResultList();
		if(!query.isEmpty())
			usuario =  query.get(0);	
		return usuario;
	}
	
	public List<Usuario> obtenerUsuariosPorFiltros(String nombre, String identificacion, boolean activo, String login, String rol, String puntoVenta)
	{
		StringBuilder sentencia = new StringBuilder().append("Select u From Usuario u, UsuarioRol ur Where ur.usuario.id = u.id and "
				+ "u.activo = :activo and ");
		
		if(puntoVenta != null && !puntoVenta.equals(""))
		{
			sentencia = new StringBuilder().append("Select u From Usuario u, UsuarioRol ur, UsuarioXPuntoVenta up Where ur.usuario.id = u.id and up.usuario.id = u.id and "
					+ "u.activo = :activo and ");
		}
		
		if(nombre != null && !nombre.equals(""))
		{
			sentencia.append("u.entidad.nombreCompleto like :nombre");
		}
		if(((nombre != null && !nombre.equals("")) && (identificacion != null && !identificacion.equals("")))  
				|| ((nombre != null && !nombre.equals("")) && (login != null && !login.equals("")))
				|| ((nombre != null && !nombre.equals("")) && (rol != null && !rol.equals("")))
				|| ((nombre != null && !nombre.equals("")) && (puntoVenta != null && !puntoVenta.equals(""))))
		{
			sentencia.append(" and ");
		}
		if(identificacion != null && !identificacion.equals(""))
		{
			sentencia.append(" u.entidad.identificacion = :identificacion");
		}
		
		if(((identificacion != null && !identificacion.equals("")) && (login != null && !login.equals("")))
				|| ((identificacion != null && !identificacion.equals("")) && (rol != null && !rol.equals("")))
				|| ((identificacion != null && !identificacion.equals("")) && (puntoVenta != null && !puntoVenta.equals(""))))
		{
			sentencia.append(" and ");
		}
		
		if(login != null && !login.equals(""))
		{
			sentencia.append(" u.login = :login");
		}
		
		if(((login != null && !login.equals("")) && (rol != null && !rol.equals(""))) 
				|| ((login != null && !login.equals("")) && (puntoVenta != null && !puntoVenta.equals(""))))
		{
			sentencia.append(" and ");
		}
		
		if(rol != null && !rol.equals(""))
		{
			sentencia.append("ur.rol.id = :rol");
		}
		
		if((rol != null && !rol.equals("")) && (puntoVenta != null && !puntoVenta.equals("")))
		{
			sentencia.append(" and ");
		}
		if(puntoVenta != null && !puntoVenta.equals(""))
		{
			sentencia.append("up.puntoVenta.id = :puntoVenta");
		}
		
		Query q = getEntityManager().createQuery(sentencia.toString());
		q.setParameter("activo", activo);
		if(nombre != null && !nombre.equals(""))
		{
			q.setParameter("nombre", "%"+nombre+"%");
		}
		if(identificacion != null && !identificacion.equals(""))
		{
			q.setParameter("identificacion", identificacion);
		}
		if(login != null && !login.equals(""))
		{
			q.setParameter("login", login);
		}
		if(rol != null && !rol.equals(""))
		{
			q.setParameter("rol", rol);
		}
		if(puntoVenta != null && !puntoVenta.equals(""))
		{
			q.setParameter("puntoVenta", puntoVenta);
		}
		return q.getResultList();
	}
	
	
}
