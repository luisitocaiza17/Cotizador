package com.qbe.cotizador.dao.entidad;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.TipoDocumento;

public class TipoDocumentoDAO extends EntityManagerFactoryDAO<TipoDocumento>{
	
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
	
	public TipoDocumentoDAO() {
        super(TipoDocumento.class);
    } 
	
	public List<TipoDocumento> buscarTodos(){
		return getEntityManager().createNamedQuery("TipoDocumento.buscarTodos").getResultList();		
	}
	
	public TipoDocumento buscarPorId(String id){
		TipoDocumento tipo = new TipoDocumento();
		List<TipoDocumento> query = getEntityManager().createNamedQuery("TipoDocumento.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);
		return tipo;		
	}	
	
	public TipoDocumento buscarPorNombre(String nombre){
		TipoDocumento tipo = new TipoDocumento();
		List<TipoDocumento> query = getEntityManager().createNamedQuery("TipoDocumento.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			tipo =  query.get(0);
		return tipo;		
	}	
	
	public List<TipoDocumento> buscarActivos(){
		return getEntityManager().createNamedQuery("TipoDocumento.buscarActivos").setParameter("valorActivo", true).getResultList();
	}
}
	