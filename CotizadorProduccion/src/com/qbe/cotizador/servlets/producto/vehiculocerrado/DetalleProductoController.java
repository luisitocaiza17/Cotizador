package com.qbe.cotizador.servlets.producto.vehiculocerrado;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.qbe.cotizador.dao.cotizacion.CoberturasConjuntoDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoDAO;
import com.qbe.cotizador.dao.entidad.ConfiguracionProductoDAO;
import com.qbe.cotizador.dao.entidad.DetalleProductoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.PlanDAO;
import com.qbe.cotizador.model.CoberturasConjunto;
import com.qbe.cotizador.model.ConfiguracionProducto;
import com.qbe.cotizador.model.DetalleProducto;
import com.qbe.cotizador.model.Plan;
import com.qbe.cotizador.model.Producto;
import com.qbe.cotizador.transaction.producto.DetalleProductoTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class DetalleProductoController
 */
@WebServlet("/DetalleProductoController")
public class DetalleProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetalleProductoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject result = new JSONObject();
		try{
		
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String nombreProducto = request.getParameter("nombreProducto") == null ? "" : request.getParameter("nombreProducto");
			String productoBusqueda = request.getParameter("productoBusqueda") == null ? "" : request.getParameter("productoBusqueda");
			String plan = request.getParameter("plan") == null ? "" : request.getParameter("plan");
			String coberturaConjunto = request.getParameter("coberturaConjunto") == null ? "" : request.getParameter("coberturaConjunto");
			String texto = request.getParameter("texto") == null ? "" : request.getParameter("texto");
			
			JSONObject detalleProductoJSONObject = new JSONObject();
			JSONArray detalleProductoJSONArray = new JSONArray();
			
			DetalleProductoDAO detalleProductoDAO = new DetalleProductoDAO();
			DetalleProducto detalleProducto = new DetalleProducto();
			
			DetalleProductoTransaction detalleProductoTransaction = new DetalleProductoTransaction();
			
			if (!codigo.equals(""))
				detalleProducto.setId(codigo);
			
			if (!nombreProducto.equals("")){
				ConfiguracionProductoDAO configuracionProductoDAO= new ConfiguracionProductoDAO();
				ConfiguracionProducto configuracionProducto= new ConfiguracionProducto();
				ProductoDAO productoDAO = new ProductoDAO();
				Producto producto = new Producto();
				producto = productoDAO.buscarPorNombre(nombreProducto);				
				configuracionProducto = configuracionProductoDAO.buscarPorProducto(producto);
				detalleProducto.setConfiguracionProducto(configuracionProducto);
			}
			
			if (!plan.equals("")){
				PlanDAO planDAO= new PlanDAO();
				Plan planf = new Plan();
				planf = planDAO.buscarPorNombre(plan);				
				detalleProducto.setPlan(planf);
			}
			
			if (!coberturaConjunto.equals("")){
				CoberturasConjunto coberturasConjunto = new CoberturasConjunto(); 				
				CoberturasConjuntoDAO coberturasConjuntoDAO = new CoberturasConjuntoDAO();
				coberturasConjunto = coberturasConjuntoDAO.buscarPorId(coberturaConjunto);
				detalleProducto.setCoberturasConjunto(coberturasConjunto);
			}			
			
			if (!texto.equals("")){
				byte[] b = texto.getBytes();				
				detalleProducto.setTexto(b);
			}
			
			if(tipoConsulta.equals("cargarCombos")){
				ProductoDAO productoDAO = new ProductoDAO();
				Producto producto= new Producto ();

				JSONObject productoJSONObject = new JSONObject();
				JSONArray productoJSONArray = new JSONArray();

				List<Producto> listaProducto= productoDAO
						.buscarTodos();

				for (int i = 0; i < listaProducto.size(); i++) {
					producto = listaProducto.get(i);
					productoJSONObject.put("codigo",
							producto.getId());
					productoJSONObject.put("nombre",
							producto.getNombre());

					productoJSONArray
							.add(productoJSONObject);
				}

				result.put("listadoProducto",
						productoJSONArray);

				
			}
			
		    if(tipoConsulta.equals("encontrarPorProducto")){ 
		    	ConfiguracionProducto configuracionProducto = new ConfiguracionProducto();
		    	ConfiguracionProductoDAO configuracionProductoDAO = new ConfiguracionProductoDAO();
		    	Producto producto = new Producto();
		    	ProductoDAO productoDAO = new ProductoDAO();
		    	producto = productoDAO.buscarPorNombre(productoBusqueda);		    	
		    	configuracionProducto= configuracionProductoDAO.buscarPorProducto(producto);
		    	DetalleProducto results = detalleProductoDAO.buscarPorConfiguracionProducto(configuracionProducto);
					detalleProductoJSONObject.put("codigo", results.getId());
					detalleProductoJSONObject.put("nombreProducto", results.getConfiguracionProducto().getProducto().getNombre());
					detalleProductoJSONObject.put("plan", results.getPlan().getNombre());
					detalleProductoJSONObject.put("coberturaConjunto", results.getCoberturasConjunto().getId());
					String str = IOUtils.toString(results.getTexto(), "ISO-8859-1");
					detalleProductoJSONObject.put("texto", str );
					detalleProductoJSONArray.add(detalleProductoJSONObject);				
				result.put("listadoDetalleProducto", detalleProductoJSONArray);
			}
		    
		    if(tipoConsulta.equals("actualizar")){
		    	detalleProductoTransaction.editar(detalleProducto);
		    }
		    
		    result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
		}catch(Exception e){
			result.put("success", Boolean.FALSE);
			result.put("error", e.getLocalizedMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			e.printStackTrace();
		}	
		
	}

}