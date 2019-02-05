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
import com.qbe.cotizador.dao.cotizacion.PaqueteDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.PaqueteCoberturaDAO;
import com.qbe.cotizador.model.Cobertura;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.Paquete;
import com.qbe.cotizador.model.PaqueteCobertura;
import com.qbe.cotizador.transaction.producto.vehiculo.PaqueteCoberturaTransaction;

/**
 * Servlet implementation class PaqueteCoberturaController
 */
@WebServlet("/PaqueteCoberturaController")
public class PaqueteCoberturaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaqueteCoberturaController() {
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
			String paquete = request.getParameter("paquete") == null ? "" : request.getParameter("paquete");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			JSONObject paqueteCoberturaJSONObject = new JSONObject();
			JSONArray paqueteCoberturaJSONArray = new JSONArray();

			PaqueteCobertura paqueteCobertura = new PaqueteCobertura();
			PaqueteCoberturaDAO paqueteCoberturaDAO = new PaqueteCoberturaDAO();
			
			PaqueteCoberturaTransaction paqueteCoberturaTransaction = new PaqueteCoberturaTransaction();

			if (!codigo.equals(""))
				paqueteCobertura.setId(codigo);
			
			if (!grupoPorProducto.equals("")){
				GrupoPorProducto grupoPorProductoAux = new GrupoPorProducto();
				GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();  
				grupoPorProductoAux = grupoPorProductoDAO.buscarPorNombre(grupoPorProducto);
				paqueteCobertura.setGrupoPorProducto(grupoPorProductoAux);
			}
			
			if (!cobertura.equals("")){
				Cobertura coberturaAux = new Cobertura();
				CoberturaDAO coberturaDAO = new CoberturaDAO ();
				coberturaAux = coberturaDAO.buscarPorNombre(cobertura);
				paqueteCobertura.setCobertura(coberturaAux);
			}
			
			if (!paquete.equals("")){
				Paquete paqueteAux = new Paquete();
				PaqueteDAO paqueteDAO = new PaqueteDAO ();
				paqueteAux = paqueteDAO.buscarPorNombre(paquete);
				paqueteCobertura.setPaquete(paqueteAux);
			}

			if(tipoConsulta.equals("encontrarTodos")){ 
				List<PaqueteCobertura> results = paqueteCoberturaDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					paqueteCobertura = results.get(i);
					paqueteCoberturaJSONObject.put("codigo", paqueteCobertura.getId());
					paqueteCoberturaJSONObject.put("cobertura", paqueteCobertura.getCobertura().getNombre());
					paqueteCoberturaJSONObject.put("grupoPorProducto", paqueteCobertura.getGrupoPorProducto().getNombreComercialProducto());
					paqueteCoberturaJSONObject.put("paquete", paqueteCobertura.getPaquete().getNombre());
					
					paqueteCoberturaJSONArray.add(paqueteCoberturaJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoPaqueteCobertura", paqueteCoberturaJSONArray);
			}
			
			JSONObject paqueteJSONObject = new JSONObject();
			JSONArray paqueteJSONArray = new JSONArray();
			
			if(tipoConsulta.equals("encontrarPaquetes") ){
				Paquete paquetef = new Paquete();
				PaqueteDAO paqueteDAO = new PaqueteDAO (); 
				List<Paquete> results = paqueteDAO.buscarTodos();				
				int i=0;
				for(i=0; i<results.size(); i++){
					paquetef = results.get(i);
					paqueteJSONObject.put("codigo", paquetef.getId());
					paqueteJSONObject.put("nombre", paquetef.getNombre());
					paqueteJSONArray.add(paqueteJSONObject);
				}
				result.put("listadoPaquete", paqueteJSONArray);
			}
			
			
			if(tipoConsulta.equals("crear"))
				paqueteCoberturaTransaction.crear(paqueteCobertura);

			if(tipoConsulta.equals("actualizar"))
				paqueteCoberturaTransaction.editar(paqueteCobertura);

			if(tipoConsulta.equals("eliminar"))
				paqueteCoberturaTransaction.eliminar(paqueteCobertura);


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
