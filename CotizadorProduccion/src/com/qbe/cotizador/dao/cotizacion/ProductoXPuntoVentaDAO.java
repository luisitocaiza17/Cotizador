package com.qbe.cotizador.dao.cotizacion;

import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.entidad.UnidadNegocioDAO;
import com.qbe.cotizador.dao.entidad.UnidadProduccionDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.Plan;
import com.qbe.cotizador.model.ProductoXPuntoVenta;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.TipoGrupo;
import com.qbe.cotizador.model.UnidadNegocio;
import com.qbe.cotizador.model.UnidadProduccion;

public class ProductoXPuntoVentaDAO extends EntityManagerFactoryDAO <ProductoXPuntoVenta>{

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
	
	public ProductoXPuntoVentaDAO() {
        super(ProductoXPuntoVenta.class);
    }

	public ProductoXPuntoVenta buscarPorId(String id) {
		ProductoXPuntoVenta producto = new ProductoXPuntoVenta();
		List<ProductoXPuntoVenta> query = getEntityManager().createNamedQuery("ProductoXPuntoVenta.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			producto =  query.get(0);
		return producto;		
	}

	public List<ProductoXPuntoVenta> buscarTodos() {
		return getEntityManager().createNamedQuery("ProductoXPuntoVenta.buscarTodos").getResultList();		
	}

	public List<ProductoXPuntoVenta> buscador(String auxcontenedor,String grupo_por_producto_busq, 
		String unidadProduccion_busq, String unidadNegocio_busq,
		String puntoVenta_busq,String plan_busq) {

		List<ProductoXPuntoVenta> results = new ArrayList<ProductoXPuntoVenta>();

		String stringQuery = "SELECT c FROM ProductoXPuntoVenta c where";
		String valoresWhereQuery = "";

		if (auxcontenedor.length() > 0)
			valoresWhereQuery += " c.contenedorId = :auxcontenedor AND ";
		if (grupo_por_producto_busq.length() > 0)
			valoresWhereQuery += " c.grupoPorProducto = :grupo_por_producto_busq AND ";
			// if(producto_busq.length()>0)
			// valoresWhereQuery += " c.auxproducto = :producto_busq AND ";
		if (unidadProduccion_busq.length() > 0)
			valoresWhereQuery += " c.unidadProduccion = :unidadProduccion_busq AND ";
		if (unidadNegocio_busq.length() > 0)
			valoresWhereQuery += " c.unidadNegocio = :unidadNegocio_busq AND ";
		if (puntoVenta_busq.length() > 0)
			valoresWhereQuery += " c.puntoVenta = :puntoVenta_busq AND ";
		if (plan_busq.length() > 0)
			valoresWhereQuery += " c.plan = :plan_busq AND ";

			stringQuery = stringQuery + valoresWhereQuery;

		int tamanoCadena = stringQuery.length() - 5;
		stringQuery = stringQuery.substring(0, tamanoCadena);
		TypedQuery<ProductoXPuntoVenta> query = getEntityManager().createQuery(stringQuery,ProductoXPuntoVenta.class);

		if (auxcontenedor.length() > 0)
			query.setParameter("auxcontenedor", auxcontenedor);
		if (grupo_por_producto_busq.length() > 0) {
			GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
			GrupoPorProducto objeto = grupoPorProductoDAO.buscarPorId(grupo_por_producto_busq);
				query.setParameter("grupo_por_producto_busq", objeto);
			}

		if (unidadProduccion_busq.length() > 0)
			{
				UnidadProduccionDAO UnidadProduccionDAO = new UnidadProduccionDAO();
				UnidadProduccion unidad_produccion_obj = UnidadProduccionDAO.buscarPorId(unidadProduccion_busq);
				query.setParameter("unidadProduccion_busq",unidad_produccion_obj);
			}
		if (unidadNegocio_busq.length() > 0) {
				UnidadNegocioDAO UnidadNegocioDAO = new UnidadNegocioDAO();
				UnidadNegocio unidad_negocio_obj = UnidadNegocioDAO.buscarPorId(unidadNegocio_busq);
				query.setParameter("unidadNegocio_busq", unidad_negocio_obj);
			}
		if (puntoVenta_busq.length() > 0) {
				PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO();
				PuntoVenta punto_venta_obj = puntoVentaDAO.buscarPorId(puntoVenta_busq);
				query.setParameter("puntoVenta_busq", punto_venta_obj);
			}
		if (plan_busq.length() > 0) {
				PlanDAO planDAO = new PlanDAO();
				Plan plan_obj = planDAO.buscarPorId(plan_busq);
				query.setParameter("plan_busq", plan_obj);
			}
			results = query.getResultList();
			
		return results;		
	}

	public ProductoXPuntoVenta buscarPorProductoPuntoVenta(GrupoPorProducto grupoPorProducto) {
		ProductoXPuntoVenta producto = new ProductoXPuntoVenta();
		List<ProductoXPuntoVenta> query = getEntityManager().createNamedQuery("ProductoXPuntoVenta.buscarPorProductoPuntoVenta").setParameter("grupoPorProducto", grupoPorProducto).getResultList();
		if(!query.isEmpty())
			producto =  query.get(0);
		return producto;		
	}

	public List<ProductoXPuntoVenta> buscarPorProductoPuntoVentaListado(GrupoPorProducto grupoPorProducto) {
		return getEntityManager().createNamedQuery("ProductoXPuntoVenta.buscarPorProductoPuntoVentaListado").setParameter("grupoPorProducto", grupoPorProducto).getResultList();		
	}

    public List<ProductoXPuntoVenta> buscarPorGrupoPunto(PuntoVenta puntoVenta,TipoGrupo tipoGrupo) {
        return getEntityManager().createNamedQuery("ProductoXPuntoVenta.buscarPorGrupoPunto").setParameter("tipoGrupo", tipoGrupo).setParameter("puntoVenta", puntoVenta).getResultList();      
    }
    
    public List<ProductoXPuntoVenta> buscarActivosPorGrupoPunto(PuntoVenta puntoVenta,TipoGrupo tipoGrupo) {
        return getEntityManager().createNamedQuery("ProductoXPuntoVenta.buscarActivosPorGrupoPunto").setParameter("tipoGrupo", tipoGrupo).setParameter("puntoVenta", puntoVenta).getResultList();      
    }

	
	
	public List<ProductoXPuntoVenta> buscarPorPuntoVenta(PuntoVenta puntoVenta) {
		return getEntityManager().createNamedQuery("ProductoXPuntoVenta.buscarPorPuntoVenta").setParameter("puntoVenta", puntoVenta).getResultList();				
	}

	public ProductoXPuntoVenta buscarPorGrupoPuntoVenta(GrupoPorProducto grupoPorProducto, PuntoVenta puntoVenta) {
		ProductoXPuntoVenta producto = new ProductoXPuntoVenta();
		List<ProductoXPuntoVenta> query = getEntityManager().createNamedQuery("ProductoXPuntoVenta.buscarPorGrupoPuntoVenta").setParameter("grupoPorProducto", grupoPorProducto).setParameter("puntoVenta", puntoVenta).getResultList();
		if(!query.isEmpty())
			producto =  query.get(0);
		return producto;		
	}

	// Obtiene los productos por punto de venta de los productos cerrados para
	// usarlos mediante la session
	public List<ProductoXPuntoVenta> buscarPorProductoPuntoVentaSession(TipoGrupo tipoGrupo) {				
		List<ProductoXPuntoVenta> query = getEntityManager().createNamedQuery("ProductoXPuntoVenta.buscarPorProductoPuntoVentaSession").setParameter("tipoGrupo", tipoGrupo).getResultList();						
		return query;		
	}
	
	// Obtiene los productos por punto de venta de los productos cerrados para		
	public List<ProductoXPuntoVenta> buscarPorProductoPuntoVentaActivos() {				
		List<ProductoXPuntoVenta> query = getEntityManager().createNamedQuery("ProductoXPuntoVenta.buscarTodosActivos").setParameter("valorActivo", true).getResultList();						
		return query;		
	}
}
