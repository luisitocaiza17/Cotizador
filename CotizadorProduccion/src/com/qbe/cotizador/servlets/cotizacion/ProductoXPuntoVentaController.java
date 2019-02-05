package com.qbe.cotizador.servlets.cotizacion;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.entidad.UnidadNegocioDAO;
import com.qbe.cotizador.dao.entidad.UnidadProduccionDAO;
import com.qbe.cotizador.dao.cotizacion.ContenedorDAO;
import com.qbe.cotizador.dao.cotizacion.PlanDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoXPuntoVentaDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoProductoDAO;
import com.qbe.cotizador.model.Contenedor;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.GrupoProducto;
import com.qbe.cotizador.model.Plan;
import com.qbe.cotizador.model.Producto;
import com.qbe.cotizador.model.ProductoXPuntoVenta;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.UnidadNegocio;
import com.qbe.cotizador.model.UnidadProduccion;
import com.qbe.cotizador.transaction.cotizacion.ProductoXPuntoVentaTransaction;

/**
 * Servlet implementation class ProductoXPuntoVentaController
 */
@WebServlet("/ProductoXPuntoVentaController")
public class ProductoXPuntoVentaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductoXPuntoVentaController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();

		try {
			String codigo = request.getParameter("codigo") == null ? "": request.getParameter("codigo");
			String grupoPorProductoId = request.getParameter("grupoPorProductoId") == null ? "" : request.getParameter("grupoPorProductoId");
		    String puntoVentaId = request.getParameter("puntoVenta") == null ? "": request.getParameter("puntoVenta");
			String unidadNegocioId = request.getParameter("unidadNegocio") == null ? ""	: request.getParameter("unidadNegocio");
			String unidadProduccionId = request.getParameter("unidadProduccion") == null ? "" : request.getParameter("unidadProduccion");
			String contenedorId = request.getParameter("contenedorEnsurance") == null ? "": request.getParameter("contenedorEnsurance");
			String planId = request.getParameter("planId") == null ? "": request.getParameter("planId");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "": request.getParameter("tipoConsulta");

			// Variables Parametros de Busqueda

			// Fin variables Busqueda

			JSONObject productoPuntoVentaJSONObject = new JSONObject();
			JSONArray productoPuntoVentaJSONArray = new JSONArray();

			ProductoXPuntoVenta productoPuntoVenta = new ProductoXPuntoVenta();
			ProductoXPuntoVentaDAO productoPuntoVentaDAO = new ProductoXPuntoVentaDAO();

			Producto producto = new Producto();
			ProductoDAO productoDAO = new ProductoDAO();

			GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
			GrupoPorProducto grupoPorProducto = new GrupoPorProducto();

			GrupoProductoDAO grupoProductoDAO = new GrupoProductoDAO();
			GrupoProducto grupoProducto = new GrupoProducto();

			PuntoVenta puntoVenta = new PuntoVenta();
			PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO();

			UnidadNegocio unidadNegocio = new UnidadNegocio();
			UnidadNegocioDAO unidadNegocioDAO = new UnidadNegocioDAO();

			UnidadProduccion unidadProduccion = new UnidadProduccion();
			UnidadProduccionDAO unidadProduccionDAO = new UnidadProduccionDAO();

			Contenedor contenedor = new Contenedor();
			ContenedorDAO contenedorDAO = new ContenedorDAO();

			Plan plan = new Plan();
			PlanDAO planDAO = new PlanDAO();
			
			ProductoXPuntoVentaTransaction productoXPuntoVentaTransaction = new ProductoXPuntoVentaTransaction();

			if (!codigo.equals(""))
				productoPuntoVenta.setId(codigo);

			if (!grupoPorProductoId.equals("")) 
			{
				grupoPorProducto = grupoPorProductoDAO.buscarPorId(grupoPorProductoId);
				productoPuntoVenta.setGrupoPorProducto(grupoPorProducto);
			}

			if (!puntoVentaId.equals("")) 
			{
				puntoVenta = puntoVentaDAO.buscarPorId(puntoVentaId);
				productoPuntoVenta.setPuntoVenta(puntoVenta);
			}

			if (!unidadNegocioId.equals("")) 
			{
				unidadNegocio = unidadNegocioDAO.buscarPorId(unidadNegocioId);
				productoPuntoVenta.setUnidadNegocio(unidadNegocio);
			}

			if (!unidadProduccionId.equals("")) {
				unidadProduccion = unidadProduccionDAO
						.buscarPorId(unidadProduccionId);
				productoPuntoVenta.setUnidadProduccion(unidadProduccion);
			}

			if (!planId.equals("")) {
				plan = planDAO.buscarPorId(planId);
				productoPuntoVenta.setPlan(plan);
			}

			if (!contenedorId.equals(""))
				productoPuntoVenta.setContenedorId(contenedorId);

			if (tipoConsulta.equals("encontrarTodos")) {

				List<ProductoXPuntoVenta> results = productoPuntoVentaDAO
						.buscarTodos();

				int i = 0;
				for (i = 0; i < results.size(); i++) {
					productoPuntoVenta = results.get(i);

					productoPuntoVentaJSONObject.put("codigo",productoPuntoVenta.getId());
					if (productoPuntoVenta.getGrupoPorProducto() == null) 
					{
						productoPuntoVentaJSONObject.put("grupo_por_producto","");
						productoPuntoVentaJSONObject.put("producto", "");
					} 
					else 
					{
						productoPuntoVentaJSONObject.put("grupo_por_producto",productoPuntoVenta.getGrupoPorProducto().getNombreComercialProducto());
						productoPuntoVentaJSONObject.put("producto",productoPuntoVenta.getGrupoPorProducto().getProducto().getNombre());
					}
					productoPuntoVentaJSONObject.put("puntoVenta",productoPuntoVenta.getPuntoVenta().getNombre());
					productoPuntoVentaJSONObject.put("unidadNegocio",productoPuntoVenta.getUnidadNegocio().getNombre());
					productoPuntoVentaJSONObject.put("unidadProduccion",productoPuntoVenta.getUnidadProduccion().getNombre());					
					// Validaciones de valores nulos
					String planValor = "";
					if(productoPuntoVenta.getPlan() != null)
						planValor = productoPuntoVenta.getPlan().getNombre();
					
					productoPuntoVentaJSONObject.put("contenedor",productoPuntoVenta.getContenedorId());
					productoPuntoVentaJSONObject.put("plan",planValor );

					productoPuntoVentaJSONArray.add(productoPuntoVentaJSONObject);
				}
				result.put("numRegistros", i);
				result.put("listaProductoPuntoVenta",productoPuntoVentaJSONArray);

				// Se consultan todos los grupos por productos para mostrar en
				// los combos de la pantalla de parametrizacion

				JSONObject grupoProductoJSONObject = new JSONObject();
				JSONArray grupoProductoJSONArray = new JSONArray();

				List<GrupoProducto> listaGrupoProducto = grupoProductoDAO
						.buscarTodos();
				for (i = 0; i < listaGrupoProducto.size(); i++) {
					grupoProducto = listaGrupoProducto.get(i);
					grupoProductoJSONObject
							.put("codigo", grupoProducto.getId());
					grupoProductoJSONObject.put("nombre",
							grupoProducto.getNombre());
					grupoProductoJSONArray.add(grupoProductoJSONObject);
				}
				result.put("listaGrupoProducto", grupoProductoJSONArray);

				JSONObject gruposPorProductoJSONObject = new JSONObject();
				JSONArray gruposPorProductoJSONArray = new JSONArray();
				List<GrupoPorProducto> listaGrupoPorProducto = grupoPorProductoDAO
						.buscarTodos();
				for (i = 0; i < listaGrupoPorProducto.size(); i++) {
					grupoPorProducto = listaGrupoPorProducto.get(i);
					gruposPorProductoJSONObject.put("codigo",
							grupoPorProducto.getId());
					gruposPorProductoJSONObject.put("nombre",
							grupoPorProducto.getNombreComercialProducto());
					gruposPorProductoJSONArray.add(gruposPorProductoJSONObject);
				}
				result.put("listaGrupoPorProducto", gruposPorProductoJSONArray);

				// Se consultan todos los productos para mostrar en los combos
				// de la pantalla de parametrizacion
				JSONObject productoJSONObject = new JSONObject();
				JSONArray productoJSONArray = new JSONArray();
				List<Producto> listaProducto = productoDAO.buscarActivos();
				for (i = 0; i < listaProducto.size(); i++) {
					producto = listaProducto.get(i);
					productoJSONObject.put("codigo", producto.getId());
					productoJSONObject.put("nombre", producto.getNombre());
					productoJSONArray.add(productoJSONObject);
				}
				result.put("listaProducto", productoJSONArray);

				// Se consultan todos los puntos de venta activos para mostrar
				// en los combos de la pantalla de parametrizacion
				JSONObject puntoVentaJSONObject = new JSONObject();
				JSONArray puntoVentaJSONArray = new JSONArray();
				List<PuntoVenta> listaPuntoVenta = puntoVentaDAO
						.buscarActivos();
				for (i = 0; i < listaPuntoVenta.size(); i++) {
					puntoVenta = listaPuntoVenta.get(i);
					puntoVentaJSONObject.put("codigo", puntoVenta.getId());
					puntoVentaJSONObject.put("nombre", puntoVenta.getNombre());
					puntoVentaJSONArray.add(puntoVentaJSONObject);
				}
				result.put("listaPuntoVenta", puntoVentaJSONArray);

				// Se consultan todas las unidades de negocio activas para
				// mostrar en los combos de la pantalla de parametrizacion
				JSONObject unidadNegocioJSONObject = new JSONObject();
				JSONArray unidadNegocioJSONArray = new JSONArray();
				List<UnidadNegocio> listaUnidadNegocio = unidadNegocioDAO
						.buscarActivos();
				for (i = 0; i < listaUnidadNegocio.size(); i++) {
					unidadNegocio = listaUnidadNegocio.get(i);
					unidadNegocioJSONObject
							.put("codigo", unidadNegocio.getId());
					unidadNegocioJSONObject.put("nombre",
							unidadNegocio.getNombre());
					unidadNegocioJSONArray.add(unidadNegocioJSONObject);
				}
				result.put("listaUnidadNegocio", unidadNegocioJSONArray);

				// Se consultan todas las unidades de produccion activas para
				// mostrar en los combos de la pantalla de parametrizacion
				JSONObject unidadProduccionJSONObject = new JSONObject();
				JSONArray unidadProduccionJSONArray = new JSONArray();
				List<UnidadProduccion> listaUnidadProduccion = unidadProduccionDAO
						.buscarActivos();
				for (i = 0; i < listaUnidadProduccion.size(); i++) {
					unidadProduccion = listaUnidadProduccion.get(i);
					unidadProduccionJSONObject.put("codigo",
							unidadProduccion.getId());
					unidadProduccionJSONObject.put("nombre",
							unidadProduccion.getNombre());
					unidadProduccionJSONArray.add(unidadProduccionJSONObject);
				}
				result.put("listaUnidadProduccion", unidadProduccionJSONArray);

				JSONObject contenedorJSONObject = new JSONObject();
				JSONArray contenedorJSONArray = new JSONArray();
				List<Contenedor> listaContenedor = contenedorDAO.buscarTodos();
				for (i = 0; i < listaContenedor.size(); i++) {
					contenedor = listaContenedor.get(i);
					contenedorJSONObject.put("codigo", contenedor.getId());
					contenedorJSONObject.put("nombre", contenedor.getNumero());
					contenedorJSONObject.put("descripcion", contenedor.getDescripcion());
					contenedorJSONArray.add(contenedorJSONObject);
				}
				result.put("listaContenedor", contenedorJSONArray);

				// Se consultan todas los planes para mostrar en los combos de
				// la pantalla de parametrizacion
				JSONObject planJSONObject = new JSONObject();
				JSONArray planJSONArray = new JSONArray();
				List<Plan> listaPlan = planDAO.buscarTodos();
				for (i = 0; i < listaPlan.size(); i++) {
					plan = listaPlan.get(i);
					planJSONObject.put("codigo", plan.getId());
					planJSONObject.put("nombre", plan.getNombre());
					planJSONArray.add(planJSONObject);
				}
				result.put("listaPlan", planJSONArray);

			}

			if (tipoConsulta.equals("buscador"))

			{

				String contenedor_id_busq = request.getParameter("contenedor_id_busq") == null ? "": request.getParameter("contenedor_id_busq");
				String grupo_por_producto_busq = request.getParameter("grupo_por_producto_busq") == null ? "": request.getParameter("grupo_por_producto_busq");
				String unidadProduccion_busq = request.getParameter("unidadProduccion_busq") == null ? "": request.getParameter("unidadProduccion_busq");
				String unidadNegocio_busq = request.getParameter("unidadNegocio_busq") == null ? "": request.getParameter("unidadNegocio_busq");
				String puntoVenta_busq = request.getParameter("puntoVenta_busq") == null ? "" : request.getParameter("puntoVenta_busq");
				String plan_busq = request.getParameter("plan_busq") == null ? "": request.getParameter("plan_busq");
				/*
				 * String auxgrupoPorproducto = "";
				 * if(!grupo_por_producto_busq.equals("")){
				 * grupoPorProducto=grupoPorProductoDAO
				 * .buscarPorNombre(grupo_por_producto_busq);
				 * auxgrupoPorproducto=grupoPorProducto.getId(); }
				 * 
				 * String auxproducto = ""; if(!producto_busq.equals("")){
				 * producto =productoDAO.buscarPorNombre(producto_busq);
				 * auxproducto = producto.getId(); }
				 * 
				 * String auxunidadProduccion = "";
				 * if(!producto_busq.equals("")){ unidadProduccion
				 * =unidadProduccionDAO.buscarPorNombre(unidadProduccion_busq);
				 * auxunidadProduccion =unidadProduccion.getId(); }
				 * 
				 * String auxunidadunidadNegocio = "";
				 * if(!unidadNegocio_busq.equals("")){ unidadNegocio
				 * =unidadNegocioDAO.buscarPorNombre(unidadNegocio_busq);
				 * auxunidadunidadNegocio =unidadNegocio.getId(); }
				 * 
				 * String auxunidadpuntoVenta = "";
				 * if(!puntoVenta_busq.equals("")){ puntoVenta
				 * =puntoVentaDAO.buscarPorNombre(puntoVenta_busq);
				 * auxunidadpuntoVenta =puntoVenta.getId(); }
				 * 
				 * 
				 */
				
				   String auxcontenedor = "";
				 if(!contenedor_id_busq.equals(""))
				 {
					 contenedor	 =contenedorDAO.buscarPorNumero(contenedor_id_busq);
					 auxcontenedor =contenedor.getId(); 
					 if (auxcontenedor==null)
					 {
					 auxcontenedor="-1";
					 }
				 }

				
				List<ProductoXPuntoVenta> results = productoPuntoVentaDAO
						.buscador(auxcontenedor, grupo_por_producto_busq,
								unidadProduccion_busq, unidadNegocio_busq,
								puntoVenta_busq, plan_busq);

				int i = 0;
				for (i = 0; i < results.size(); i++) {
					productoPuntoVenta = results.get(i);
					productoPuntoVentaJSONObject.put("codigo",
							productoPuntoVenta.getId());
					productoPuntoVentaJSONObject.put("grupo_por_producto",
							productoPuntoVenta.getGrupoPorProducto()
									.getNombreComercialProducto());
					productoPuntoVentaJSONObject.put("grupo_por_producto_id",
							productoPuntoVenta.getGrupoPorProducto()
									.getId());

					productoPuntoVentaJSONObject.put("grupo_producto",productoPuntoVenta.getGrupoPorProducto().getGrupoProducto().getId());
					productoPuntoVentaJSONObject.put("grupo_productoId",productoPuntoVenta.getGrupoPorProducto().getGrupoProducto().getNombre());
					productoPuntoVentaJSONObject.put("puntoVenta",productoPuntoVenta.getPuntoVenta().getNombre());
					productoPuntoVentaJSONObject.put("unidadNegocio",productoPuntoVenta.getUnidadNegocio().getNombre());
					productoPuntoVentaJSONObject.put("unidadProduccion",productoPuntoVenta.getUnidadProduccion().getNombre());					
					contenedor=contenedorDAO.buscarPorId(productoPuntoVenta.getContenedorId());
					productoPuntoVentaJSONObject.put("contenedor",contenedor.getNumero());
					productoPuntoVentaJSONObject.put("contenedorId",contenedor.getDescripcion()+" - "+contenedor.getNumero());
					if(productoPuntoVenta.getPlan() != null)
					productoPuntoVentaJSONObject.put("plan", productoPuntoVenta
							.getPlan().getNombre());

					productoPuntoVentaJSONArray
							.add(productoPuntoVentaJSONObject);
				}
				result.put("numRegistros", i);
				result.put("listaProductoPuntoVenta",
						productoPuntoVentaJSONArray);
			}
			if (tipoConsulta.equals("crear"))
				productoXPuntoVentaTransaction.crear(productoPuntoVenta);

			if (tipoConsulta.equals("actualizar"))
				productoXPuntoVentaTransaction.editar(productoPuntoVenta);

			if (tipoConsulta.equals("eliminar"))
			{
				ProductoXPuntoVenta pxv = productoPuntoVentaDAO.buscarPorId(productoPuntoVenta.getId());
				productoXPuntoVentaTransaction.eliminar(pxv);
			}
				

			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1");
			result.write(response.getWriter());
		} catch (Exception e) {
			result.put("success", Boolean.FALSE);
			result.put("error", e.getLocalizedMessage());
			response.setContentType("application/json; charset=ISO-8859-1");
			result.write(response.getWriter());
			e.printStackTrace();

		}
	}

}
