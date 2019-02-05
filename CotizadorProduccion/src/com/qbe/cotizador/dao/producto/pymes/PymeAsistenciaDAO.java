package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeAsistencia;

public class PymeAsistenciaDAO extends EntityManagerFactoryDAO<PymeAsistencia>{
	
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
	
	public PymeAsistenciaDAO(){
		super(PymeAsistencia.class);
	}
	
	/*public PymeAsistencia crear(PymeAsistencia objeto){
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		try{
			em.getTransaction().begin();
			em.persist(objeto);
			em.flush();
			em.getTransaction();
			return objeto;
		}catch(Exception e){
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
			return objeto;
		}finally{
			em.close();
		}
	}
	
	public PymeAsistencia editar(PymeAsistencia objeto) {
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
	
	public PymeAsistencia eliminar(PymeAsistencia objeto) {
		EntityManager em = obtenerEntityManagerFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			PymeAsistencia pymeAsistenciaToBeRemoved = em.getReference(PymeAsistencia.class, objeto.getAsistenciaId());
			em.remove(pymeAsistenciaToBeRemoved);
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
	
	public List<PymeAsistencia> buscarGrupoPorProductoId(BigInteger grupoPorProductoId) {
		return getEntityManager().createNamedQuery("PymeAsistencia.buscarGrupoPorProductoId").setParameter("grupoPorProductoId", grupoPorProductoId).getResultList();
	}
	
	public PymeAsistencia buscarPorId(BigInteger asistenciaId) {
		PymeAsistencia pymeAsistencia=new PymeAsistencia();
		List<PymeAsistencia> results = getEntityManager().createNamedQuery("PymeAsistencia.buscarPorId").setParameter("asistenciaId", asistenciaId).getResultList();			
		if(results.size()>0)
			pymeAsistencia = results.get(0);
		return pymeAsistencia;
	}
	
	public List<PymeAsistencia> buscarTodos() {
		return getEntityManager().createNamedQuery("PymeAsistencia.buscarTodos").getResultList();
	}
}
