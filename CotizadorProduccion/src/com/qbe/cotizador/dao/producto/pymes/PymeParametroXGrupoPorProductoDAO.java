package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeParametroXGrupoPorProducto;

public class PymeParametroXGrupoPorProductoDAO extends EntityManagerFactoryDAO<PymeParametroXGrupoPorProducto>{

	/*public PymeParametroXGrupoPorProducto crear(PymeParametroXGrupoPorProducto objeto) {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(objeto);
			em.flush();
			em.getTransaction().commit();
			return objeto;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
			return objeto;
		}finally{
			em.close();
		}
		
	}
	
	public PymeParametroXGrupoPorProducto editar(PymeParametroXGrupoPorProducto objeto) {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(objeto);
			em.getTransaction().commit();
			return objeto;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
			return objeto;
		}finally{
			em.close();
		}
		
	}
	
	public PymeParametroXGrupoPorProducto eliminar(PymeParametroXGrupoPorProducto objeto) {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			PymeParametroXGrupoPorProducto parametroXGrupoPorProductoToBeRemoved = em.getReference(PymeParametroXGrupoPorProducto.class, objeto.getParametroGrupoProductoId());
			em.remove(parametroXGrupoPorProductoToBeRemoved);
			em.getTransaction().commit();
			return objeto;
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
			return objeto;
		}finally{
			em.close();
		}
	}*/
	
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
	
	public PymeParametroXGrupoPorProductoDAO() {
		super(PymeParametroXGrupoPorProducto.class);
	}
	
	public List<PymeParametroXGrupoPorProducto> buscarTodos() {
		return getEntityManager().createNamedQuery("PymeParametroXGrupoPorProducto.buscarTodos").getResultList();
	}
	
	public PymeParametroXGrupoPorProducto buscarPorId(BigInteger id) {
		PymeParametroXGrupoPorProducto entidad = new PymeParametroXGrupoPorProducto();
		List<PymeParametroXGrupoPorProducto> results = getEntityManager().createNamedQuery("PymeParametroXGrupoPorProducto.buscarPorId").setParameter("id", id).getResultList();
		if(results.size()>0)
			entidad = results.get(0);
		return entidad;
	}
	
	
	
	public PymeParametroXGrupoPorProducto buscarPorGrupoPorProductoId(BigInteger idGrupo) {
		PymeParametroXGrupoPorProducto entidad = new PymeParametroXGrupoPorProducto();
		List<PymeParametroXGrupoPorProducto> results = getEntityManager().createNamedQuery("PymeParametroXGrupoPorProducto.buscarPorGrupoPorProductoId").setParameter("idGrupo", idGrupo).getResultList();
		if(results.size()>0)
			entidad = results.get(0);
		return entidad;
	}
}
