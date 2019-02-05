package com.qbe.cotizador.servlets.producto.vehiculocerrado;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.cotizacion.CoberturaDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.ProductoCoberturaDAO;
import com.qbe.cotizador.model.Cobertura;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.ProductoCobertura;
import com.qbe.cotizador.transaction.producto.ProductoCoberturaTransaction;

/**
 * Servlet implementation class ProductoCoberturaController
 */
@WebServlet("/ProductoCoberturaController")
public class ProductoCoberturaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductoCoberturaController() {
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
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String grupoPorProducto = request.getParameter("grupoPorProducto") == null ? "" : request.getParameter("grupoPorProducto");
			String cobertura = request.getParameter("cobertura") == null ? "" : request.getParameter("cobertura");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String grupoPorProductoFiltro = request.getParameter("grupoPorProductoFiltro") == null ? "" : request.getParameter("grupoPorProductoFiltro");
			JSONObject productoCoberturaJSONObject = new JSONObject();
			JSONArray productoCoberturaJSONArray = new JSONArray();

			ProductoCobertura productoCobertura = new ProductoCobertura();
			ProductoCoberturaDAO productoCoberturaDAO = new ProductoCoberturaDAO();

			ProductoCoberturaTransaction productoCoberturaTransaction = new ProductoCoberturaTransaction();
			
			if (!codigo.equals(""))
				productoCobertura.setId(codigo);
			
			if (!grupoPorProducto.equals("")){
				GrupoPorProducto grupoPorProductoAux = new GrupoPorProducto();
				GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();  
				//grupoPorProductoAux = grupoPorProductoDAO.buscarPorNombre(grupoPorProducto);
				grupoPorProductoAux = grupoPorProductoDAO.buscarPorId(grupoPorProducto);
				productoCobertura.setGrupoPorProducto(grupoPorProductoAux);
			}
			
			if (!cobertura.equals("")){
				Cobertura coberturaAux = new Cobertura();
				CoberturaDAO coberturaDAO = new CoberturaDAO ();
				//coberturaAux = coberturaDAO.buscarPorNombre(cobertura);
				coberturaAux = coberturaDAO.buscarPorId(cobertura);
				productoCobertura.setCobertura(coberturaAux);
			}

			if(tipoConsulta.equals("encontrarTodos")){ 
				List<ProductoCobertura> results = productoCoberturaDAO.buscarXGrupoProducto(grupoPorProductoFiltro, "V%");
				int i=0;
				for(i=0; i<results.size(); i++){
					productoCobertura = results.get(i);
					productoCoberturaJSONObject.put("codigo", productoCobertura.getId());
					productoCoberturaJSONObject.put("cobertura", productoCobertura.getCobertura().getNombre());
					productoCoberturaJSONObject.put("grupoPorProducto", productoCobertura.getGrupoPorProducto().getNombreComercialProducto());
					
					productoCoberturaJSONArray.add(productoCoberturaJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoProductoCobertura", productoCoberturaJSONArray);
			}
			
			if(tipoConsulta.equals("crear"))
			{
				if(productoCoberturaDAO.verificarExistenciaProductoCobertura(productoCobertura.getCobertura(), productoCobertura.getGrupoPorProducto()))
				{
					productoCoberturaTransaction.crear(productoCobertura);
					result.put("exitoCrear", Boolean.TRUE);
				}
				else
				{
					result.put("exitoCrear", Boolean.FALSE);
				}
				
			}
				

			if(tipoConsulta.equals("actualizar"))
			{
				if(productoCoberturaDAO.verificarExistenciaProductoCobertura(productoCobertura.getCobertura(), productoCobertura.getGrupoPorProducto()))
				{
					productoCoberturaTransaction.editar(productoCobertura);
					result.put("exitoCrear", Boolean.TRUE);
				}
				else
				{
					result.put("exitoCrear", Boolean.FALSE);
				}
				
			}
				
			if(tipoConsulta.equals("eliminar"))
			{
				result.put("exitoCrear", Boolean.TRUE);
				productoCoberturaTransaction.eliminar(productoCobertura);
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
