package com.qbe.cotizador.dao.cotizacion;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Descuento;
import com.qbe.cotizador.model.GrupoAutorizacion;

public class DescuentoDAO extends EntityManagerFactoryDAO<Descuento>{	

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
	
	public DescuentoDAO() {
        super(Descuento.class);
    }
	
	public List<Descuento> buscarTodos(){
		return getEntityManager().createNamedQuery("Descuento.buscarTodos").getResultList();
	}
	
	public Descuento buscarPorId(String id){
		Descuento descuento = new Descuento();
		List<Descuento> query = getEntityManager().createNamedQuery("Descuento.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			descuento =  query.get(0);
		return descuento;
	}

	public List<Descuento> buscarPorGrupoAutorizacion(GrupoAutorizacion grupoAutorizacion){
		return getEntityManager().createNamedQuery("Descuento.buscarPorGrupoAutorizacion").setParameter("grupoAutorizacion", grupoAutorizacion).getResultList();		
	}

	public List<Descuento> buscarActivos(){
		return getEntityManager().createNamedQuery("Descuento.buscarActivos").setParameter("valorActivo", true).getResultList();		
	}
}
