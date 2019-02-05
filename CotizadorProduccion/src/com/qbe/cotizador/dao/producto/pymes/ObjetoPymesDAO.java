package com.qbe.cotizador.dao.producto.pymes;

import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.ObjetoPyme;

public class ObjetoPymesDAO extends EntityManagerFactoryDAO<ObjetoPyme>{
	
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
	
	public ObjetoPymesDAO() {
        super(ObjetoPyme.class);
    }
	
	public ObjetoPyme buscarPorId(String id){
		ObjetoPyme objeto = new ObjetoPyme();
		List<ObjetoPyme> query = getEntityManager().createNamedQuery("ObjetoPyme.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			objeto =  query.get(0);	
		return objeto;
	}
	
	public List<ObjetoPyme> buscarTodos(){  
		return getEntityManager().createNamedQuery("ObjetoPyme.buscarTodos").getResultList();
	}

	public List<ObjetoPyme> buscarObjetoPymePorCotizacionDetalle(List<CotizacionDetalle> listadoCotizacionDetalle){
		List<ObjetoPyme> results = new ArrayList<ObjetoPyme>();
			for(int i=0; i<listadoCotizacionDetalle.size();i++){	
				
				Query query = getEntityManager().createNamedQuery("ObjetoPyme.buscarPorId").setParameter("id", listadoCotizacionDetalle.get(i).getObjetoId());
				results.add((ObjetoPyme) query.getResultList().get(0));
			}						 			
		return results;
	}
}
