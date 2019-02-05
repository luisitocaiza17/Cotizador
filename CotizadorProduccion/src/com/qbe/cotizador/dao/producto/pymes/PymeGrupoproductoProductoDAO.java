package com.qbe.cotizador.dao.producto.pymes;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.PymeGrupoproductoProducto;

public class PymeGrupoproductoProductoDAO extends EntityManagerFactoryDAO<PymeGrupoproductoProducto>{
	
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
	
	public PymeGrupoproductoProductoDAO() {
        super(PymeGrupoproductoProducto.class);
    }
	
	public List<PymeGrupoproductoProducto> buscarTodos(){
		return getEntityManager().createNamedQuery("PymeGrupoproductoProducto.findAll").getResultList();
	}
		
	public List<PymeGrupoproductoProducto> buscarPorGrupoProducto(BigInteger grupoProductoId){
		PymeGrupoproductoProducto PymeGrupoproductoProducto = new PymeGrupoproductoProducto();
		List<PymeGrupoproductoProducto> query = getEntityManager().createNamedQuery("PymeGrupoproductoProducto.buscarPorGrupoProducto").setParameter("grupoProductoId", grupoProductoId).getResultList();
		return query;		
	}
	
	public PymeGrupoproductoProducto buscarPorId(BigInteger id){
		PymeGrupoproductoProducto PymeGrupoproductoProducto = new PymeGrupoproductoProducto();
		List<PymeGrupoproductoProducto> query = getEntityManager().createNamedQuery("PymeGrupoproductoProducto.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			PymeGrupoproductoProducto =  query.get(0);
		return PymeGrupoproductoProducto;		
	}	
}