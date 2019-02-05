package com.qbe.cotizador.dao.producto.vehiculo;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Color;

public class ColorDAO extends EntityManagerFactoryDAO<Color>{
	
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
	
	public ColorDAO() {
        super(Color.class);
    }
	
	public List<Color> buscarTodos(){
		return getEntityManager().createNamedQuery("Color.buscarTodos").getResultList();	
	}
	
	public Color buscarPorId(String id){
		Color color = new Color();
		List<Color> query = getEntityManager().createNamedQuery("Color.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			color =  query.get(0);
		return color;	
	}
	
	public Color buscarPorNombre(String nombre){
		Color color = new Color();
		List<Color> query = getEntityManager().createNamedQuery("Color.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			color =  query.get(0);			
		return color;
	}
	
	public List<Color> buscarActivos(){
		return getEntityManager().createNamedQuery("Color.buscarActivos").setParameter("valorActivo", true).getResultList();
	}
	
	public Color buscarPorCodigoEnsurance(String colEnsurance){
		Color color = new Color();
		List<Color> query = getEntityManager().createNamedQuery("Color.buscarPorCodigoEnsurance").setParameter("colEnsurance", colEnsurance).getResultList();
		if(!query.isEmpty())
			color =  query.get(0);					
		return color;
	}

}
