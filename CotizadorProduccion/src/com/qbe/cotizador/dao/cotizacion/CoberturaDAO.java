package com.qbe.cotizador.dao.cotizacion;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Cobertura;
import com.qbe.cotizador.model.GrupoCobertura;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.PaqueteCobertura;
import com.qbe.cotizador.model.ProductoCobertura;

public class CoberturaDAO extends EntityManagerFactoryDAO<Cobertura>{

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

	public CoberturaDAO() {
		super(Cobertura.class);
	}	

	public List<Cobertura> buscarTodos(){
		return getEntityManager().createNamedQuery("Cobertura.buscarTodos").getResultList();
	}
	
	public List<Cobertura> buscarMostrarCotizador(){
		return getEntityManager().createNamedQuery("Cobertura.buscarMostrarCotizador").getResultList();
	}
	
	public Cobertura buscarPorId(String id){
		Cobertura cobertura = new Cobertura();
		List<Cobertura> query = getEntityManager().createNamedQuery("Cobertura.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			cobertura =  query.get(0);
		return cobertura;
	}
	
	public Cobertura buscarPorNombre(String nombre){
		Cobertura cobertura = new Cobertura();
		List<Cobertura> query = getEntityManager().createNamedQuery("Cobertura.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			cobertura =  query.get(0);
		return cobertura;
	}
	
	public Cobertura buscarPorNemotecnico(String nemotecnico){
		Cobertura cobertura = new Cobertura();
		List<Cobertura> query = getEntityManager().createNamedQuery("Cobertura.buscarPorNemotecnico").setParameter("nemotecnico", nemotecnico).getResultList();
		if(!query.isEmpty())
			cobertura =  query.get(0);
		return cobertura;
	}
//	public List<Cobertura> buscarActivos(){		
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
//		List<Cobertura> results = null;
//		try{	
//			TypedQuery<Cobertura> query = em.createQuery("SELECT c FROM Cobertura c WHERE c.activo =:valorActivo", Cobertura.class).setParameter("valorActivo", true);
//			results = query.getResultList(); 
//		}catch(Exception e) { 
//			em.getTransaction().rollback();           
//		}finally{         
//			em.close(); 			
//		}
//		return results;
//	}
	
	public List<Cobertura> buscarPorGrupoCobertura(GrupoCobertura grupoCobertura){
		return getEntityManager().createNamedQuery("Cobertura.buscarPorGrupoCobertura").setParameter("grupoCobertura", grupoCobertura).getResultList();
	}
	
	public List<Cobertura> buscarPorIds(List<String> ids){
		return getEntityManager().createNamedQuery("Cobertura.buscarPorIds").setParameter("ids", ids).getResultList();		
	}
	
	public List<Cobertura> buscarCoberturasPorGrupoProducto(GrupoPorProducto grupoPorProducto){
		List<ProductoCobertura> results = new ArrayList<ProductoCobertura>();
    	List<Cobertura> coberturas = new ArrayList<Cobertura>();
		List<ProductoCobertura> productos_cobertura = getEntityManager().createNamedQuery("ProductoCobertura.buscarCoberturasPorGrupoProducto").setParameter("grupoPorProductoObjeto", grupoPorProducto).getResultList();
	    for(int i=0; i<productos_cobertura.size();i++){
	    	coberturas.add(productos_cobertura.get(i).getCobertura());
	    }
      	return coberturas;
    }	
	
	public List<Cobertura> buscarCoberturasNemotecnicoGrupoPorProducto(String nombre,GrupoPorProducto grupoPorProducto){
		List<ProductoCobertura> results = new ArrayList<ProductoCobertura>();
    	List<Cobertura> coberturas = new ArrayList<Cobertura>();
		List<ProductoCobertura> productos_cobertura = getEntityManager().createNamedQuery("ProductoCobertura.buscarCoberturasNemotecnicoGrupoPorProducto").setParameter("grupoPorProducto", grupoPorProducto).setParameter("nombre", nombre).getResultList();		
	    for(int i=0; i<productos_cobertura.size();i++){
	    	coberturas.add(productos_cobertura.get(i).getCobertura());
	    }
      	return coberturas;
    }	
	
	public List<Cobertura> buscarCoberturasGrupoCobertura(GrupoCobertura grupoCobertura){
		return getEntityManager().createNamedQuery("Cobertura.buscarCoberturasGrupoCobertura").setParameter("grupoCobertura", grupoCobertura).getResultList();
    }	

	public List<PaqueteCobertura> buscarPaqueteCoberturasPorGrupoPorProductoId(GrupoPorProducto grupoPorProducto){		
		return getEntityManager().createNamedQuery("PaqueteCobertura.buscarPaqueteCoberturasPorGrupoPorProductoId").setParameter("grupoPorProducto", grupoPorProducto).getResultList();
    }
	
	public List<PaqueteCobertura> buscarCoberturasPorPaquete(){
		return getEntityManager().createNamedQuery("PaqueteCobertura.buscarTodos").getResultList();      	
    }
	
	@SuppressWarnings("unchecked")
	public List<Cobertura> buscarCoberturaPorRamoNemotecnico(String ramoNemotecnico)
	{
		Query q = getEntityManager().createNamedQuery("Cobertura.buscarPorRamoNemotecnico");
		q.setParameter("ramoNemotecnico", ramoNemotecnico);
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public boolean verificarCoberturaOtros(String coberturaId)
	{
		Query q = getEntityManager().createNativeQuery("select * from vh_coberturaotros where coberturaId = ?1");
		q.setParameter(1, coberturaId);
		if(!q.getResultList().isEmpty())
		{
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public boolean verificarCoberturaOtrosConIva(String coberturaId)
	{
		Query q = getEntityManager().createNativeQuery("select * from vh_coberturaotros where iva = 1 and coberturaId = ?1");
		q.setParameter(1, coberturaId);
		if(!q.getResultList().isEmpty())
		{
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public boolean verificarCoberturaOtrosSinIva(String coberturaId)
	{
		Query q = getEntityManager().createNativeQuery("select * from vh_coberturaotros where iva = 0 and coberturaId = ?1");
		q.setParameter(1, coberturaId);
		if(!q.getResultList().isEmpty())
		{
			return true;
		}
		return false;
	}
	
	
}
