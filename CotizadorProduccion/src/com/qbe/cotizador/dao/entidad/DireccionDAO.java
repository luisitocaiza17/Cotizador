package com.qbe.cotizador.dao.entidad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Direccion;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.TipoDireccion;

public class DireccionDAO extends EntityManagerFactoryDAO<Direccion>{
	
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
	
	public DireccionDAO() {
        super(Direccion.class);
    }
	
	public List<Direccion> buscarTodos(){
		return getEntityManager().createNamedQuery("Direccion.buscarTodos").getResultList();		
	}
	
	public Direccion buscarPorId(String id){
		Direccion direccion = new Direccion();
		List<Direccion> query = getEntityManager().createNamedQuery("Direccion.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			direccion =  query.get(0);
		return direccion;
	}
		
	public List<Direccion> buscarPorEntidadId(Entidad entidad){
		return getEntityManager().createNamedQuery("Direccion.buscarPorEntidadId").setParameter("entidad", entidad).getResultList();		
	}
	
	public List<Direccion> buscarCobroPorEntidadId(Entidad entidad){
		return getEntityManager().createNamedQuery("Direccion.buscarCobroPorEntidadId").setParameter("entidad", entidad).getResultList();		
	}
	
	public List<Direccion> buscarPorTipoDireccionEntidad(Entidad entidad, TipoDireccion tipoDireccion){
		return getEntityManager().createNamedQuery("Direccion.buscarPorTipoDireccionEntidad").setParameter("entidad", entidad).setParameter("tipoDireccion", tipoDireccion).getResultList();		
	}
}