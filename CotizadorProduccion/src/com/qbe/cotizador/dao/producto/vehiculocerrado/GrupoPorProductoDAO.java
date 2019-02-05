package com.qbe.cotizador.dao.producto.vehiculocerrado;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.GrupoProducto;
import com.qbe.cotizador.model.TipoGrupo;

public class GrupoPorProductoDAO extends EntityManagerFactoryDAO<GrupoPorProducto>{

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

	public GrupoPorProductoDAO() {
	    super(GrupoPorProducto.class);
	}

	public List<GrupoPorProducto> buscarTodos(){
		return getEntityManager().createNamedQuery("GrupoPorProducto.buscarTodos").getResultList();
	}
	
	public List<GrupoPorProducto> buscarTodosPorGrupo(GrupoProducto grupoProducto){
		return getEntityManager().createNamedQuery("GrupoPorProducto.buscarTodosPorGrupo").setParameter("grupoProducto", grupoProducto).getResultList();
	}
	
	public List<GrupoPorProducto> buscarTodosPorGrupoTipoGrupo(GrupoProducto grupoProducto,TipoGrupo tipoGrupo){
		return getEntityManager().createNamedQuery("GrupoPorProducto.buscarTodosPorGrupoTipoGrupo").setParameter("grupoProducto", grupoProducto).setParameter("tipoGrupo", tipoGrupo).getResultList();
	}
	
	public List<GrupoPorProducto> buscarTodosPorTipoGrupo(TipoGrupo tipoGrupo){
		return getEntityManager().createNamedQuery("GrupoPorProducto.buscarTodosPorTipoGrupo").setParameter("tipoGrupo", tipoGrupo).getResultList();
	}
	
	public List<GrupoPorProducto> buscarActivosPorTipoGrupo(TipoGrupo tipoGrupo){
        return getEntityManager().createNamedQuery("GrupoPorProducto.buscarActivosPorTipoGrupo").setParameter("tipoGrupo", tipoGrupo).getResultList();
    }
	
	public GrupoPorProducto buscarPorId(String id){
		GrupoPorProducto grupo = new GrupoPorProducto();
		List<GrupoPorProducto> query = getEntityManager().createNamedQuery("GrupoPorProducto.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			grupo =  query.get(0);			
		return grupo;
	}
	
	public GrupoPorProducto buscarPorNombre(String nombre){
		GrupoPorProducto grupo = new GrupoPorProducto();
		List<GrupoPorProducto> query = getEntityManager().createNamedQuery("GrupoPorProducto.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			grupo =  query.get(0);
		return grupo;
	}

	public String buscarIdEnPoliza (String idGrupoPorProducto){
		String grupo = "";		
		BigInteger id = new BigInteger(idGrupoPorProducto);
		List<GrupoPorProducto> query = getEntityManager().createNamedQuery("Cotizacion.buscarPorGrupoPorProducto").setParameter("grupoPorProductoId", id).getResultList();
		if(!query.isEmpty()){
			grupo = "SI";
		}else{
			grupo = "NO";					
		}    	
      	return grupo;
	}
}
