package com.qbe.cotizador.dao.entidad;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.CoberturasConjunto;
import com.qbe.cotizador.model.ConfiguracionProducto;
import com.qbe.cotizador.model.DetalleProducto;
import com.qbe.cotizador.model.Plan;
import com.qbe.cotizador.model.Ramo;

public class DetalleProductoDAO extends EntityManagerFactoryDAO<DetalleProducto>{

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
	
	public DetalleProductoDAO() {
        super(DetalleProducto.class);
    }
	
	public DetalleProducto buscarPorId(String id){
		DetalleProducto detalle = new DetalleProducto();
		List<DetalleProducto> query = getEntityManager().createNamedQuery("DetalleProducto.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			detalle =  query.get(0);
		return detalle;
	}
	
	public List<DetalleProducto> buscarTodos(){
		return getEntityManager().createNamedQuery("DetalleProducto.buscarTodos").getResultList();		
	}
	
//	public List<DetalleProducto> buscarPorConfiguracionProducto(){		
//		EntityManager em = obtenerEntityManagerFactory().createEntityManager();		
//		List<DetalleProducto> results = null;
//		try{
//			TypedQuery<DetalleProducto> query = em.createQuery("SELECT c FROM DetalleProducto c", DetalleProducto.class);
//			results = query.getResultList(); 	 
//		}catch(Exception e) { 
//			em.getTransaction().rollback();  	
//		}finally{         
//			em.close();		
//		}
//		return results;
//	}

	public DetalleProducto buscarPorCobertura(CoberturasConjunto coberturasConjunto, Plan plan){
		DetalleProducto detalle = new DetalleProducto();
		List<DetalleProducto> query = getEntityManager().createNamedQuery("DetalleProducto.buscarPorCobertura").setParameter("coberturasConjunto", coberturasConjunto).getResultList();
		if(!query.isEmpty())
			detalle =  query.get(0);
		return detalle;		
	}
	
	
	public DetalleProducto buscarPorCoberturaPlanNull(CoberturasConjunto coberturasConjunto){
		DetalleProducto detalle = new DetalleProducto();
		List<DetalleProducto> query = getEntityManager().createNamedQuery("DetalleProducto.buscarPorCoberturaPlanNull").setParameter("coberturasConjunto", coberturasConjunto).getResultList();
		if(!query.isEmpty())
			detalle =  query.get(0);
		return detalle;		
	}
	
	public DetalleProducto buscarPorConfiguracionProducto(ConfiguracionProducto configuracionProducto){
		DetalleProducto detalle = new DetalleProducto();
		List<DetalleProducto> query = getEntityManager().createNamedQuery("DetalleProducto.buscarPorConfiguracionProducto").setParameter("configuracionProducto", configuracionProducto).getResultList();
		if(!query.isEmpty())
			detalle =  query.get(0);
		return detalle;
	}
	
	public DetalleProducto buscarPorConfiguracionConjuntoPlan(ConfiguracionProducto configuracionProducto,CoberturasConjunto coberturasConjunto, Plan plan){		DetalleProducto detalle = new DetalleProducto();
		List<DetalleProducto> query = getEntityManager().createNamedQuery("DetalleProducto.buscarPorConfiguracionConjuntoPlan").setParameter("configuracionProducto", configuracionProducto).setParameter("coberturasConjunto", coberturasConjunto).setParameter("plan", plan).getResultList();
		if(!query.isEmpty())
			detalle =  query.get(0);
		return detalle;		
	}
	
//	public List<Object> buscarPorProductoTasaPymes(String productoId, String coberturaId){		
//		List<Object> result=  new ArrayList<Object>();		         
//			//"Consulta las coberturas en base al DETALLE_PRODUCTO, GRUPO_COBERTURA para asignar las tasas por producto en PYMES
//            String query = " select prod.id as PRODUCTO_ID, prod.nombre as PRODUCTO, cob.id as COBERTURA_ID, cob.nombre as COBERTURA, grupcob.id as GRUPO_ID, grupcob.nombre as GRUPO_COBERTURA, detp.tasa, detp.id as DETALLE_PRODUCTO_ID, detp.coberturas_conj_id "+
//							" from PRODUCTO prod, CONFIGURACION_PRODUCTO confp, DETALLE_PRODUCTO detp, COBERTURAS_CONJUNTO cobc, COBERTURA cob, GRUPO_COBERTURA grupcob "+
//							" where prod.id = '" + productoId + "' "+
//							" and cob.id = '" + coberturaId + "' "+
//							" and prod.ramo_id = '1516276756602' "+
//							" and prod.id = confp.producto_id  "+
//							" and confp.id = detp.config_producto_id  "+
//							" and detp.coberturas_conj_id = cobc.id  "+
//							" and cobc.cobertura_id = cob.id  "+
//							" and cob.mostrar_cotizador = '1' "+
//							" and cob.grupo_cobertura_id = grupcob.id "+
//							" and grupcob.id in ('1528073304335','1528073306055','1528073306132','1528073306138','1528073306270');";
//			
//            Query q = getEntityManager().createNativeQuery(query);
//            result = q.getResultList();                    
//		return result;
//	}	
	
	public int eliminarTodos(){		
		int resultado=0;
		UserTransaction ut = null;
		try{
			ut = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			ut.begin();
			resultado = getEntityManager().createQuery("DELETE FROM DetalleProducto ",DetalleProducto.class).executeUpdate();
			ut.commit();
		}catch(Exception e) {
			try {
				ut.rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			    
		}		
		return resultado;		
	}
	/**
	 * Busqueda de los detalles del producto por ramo
	 * @param ramo
	 * @return
	 */	 
	public List<DetalleProducto> buscarPorRamoDetalleProducto(String ramoNemotecnico){		
		List<DetalleProducto> detalles = null;
		detalles = getEntityManager().createNamedQuery("DetalleProducto.buscarPorRamoDetalleProducto").setParameter("ramoNemotecnico",ramoNemotecnico).getResultList();		
		return detalles;		
	}
	
}
