package com.qbe.cotizador.dao.producto.vehiculo;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Marca;

public class MarcaDAO extends EntityManagerFactoryDAO<Marca>{
	
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
	
	public MarcaDAO() {
        super(Marca.class);
    }

	public List<Marca> buscarTodos(){   
		return getEntityManager().createNamedQuery("Marca.buscarTodos").getResultList();
	}
	
	public Marca buscarPorId(String id){   
		Marca marca = new Marca();
		List<Marca> query = getEntityManager().createNamedQuery("Marca.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			marca =  query.get(0);
		return marca;
	}
	
	public Marca buscarPorCodigoEnsurance(String marEnsurance){   
		Marca marca = new Marca();
		List<Marca> query = getEntityManager().createNamedQuery("Marca.buscarPorCodigoEnsurance").setParameter("marEnsurance", marEnsurance).getResultList();
		if(!query.isEmpty())
			marca =  query.get(0);
		return marca;
	}

	
	public List<Marca> buscarActivos(){
		return getEntityManager().createNamedQuery("Marca.buscarActivos").setParameter("valorActivo", true).getResultList();
	}
	
	public Marca buscarPorNombre(String nombre){
		Marca marca = new Marca();
		List<Marca> query = getEntityManager().createNamedQuery("Marca.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			marca =  query.get(0);	
		return marca;
	}
}
