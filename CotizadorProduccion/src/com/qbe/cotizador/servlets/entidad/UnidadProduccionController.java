package com.qbe.cotizador.servlets.entidad;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.entidad.UnidadNegocioDAO;
import com.qbe.cotizador.dao.entidad.UnidadProduccionDAO;
import com.qbe.cotizador.model.UnidadNegocio;
import com.qbe.cotizador.model.UnidadProduccion;
import com.qbe.cotizador.transaction.entidad.UnidadProduccionTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class UnidadProduccionController
 */
@WebServlet("/UnidadProduccionController")
public class UnidadProduccionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnidadProduccionController() {
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
		
		UnidadProduccionTransaction unidadProduccionTransaction = new UnidadProduccionTransaction(); 

		JSONObject result = new JSONObject();
		try{
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String unidad_negocio = request.getParameter("unidad_negocio") == null ? "" : request.getParameter("unidad_negocio");
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
			String up_ensurance = request.getParameter("up_ensurance") == null ? "" : request.getParameter("up_ensurance");
			String activo = request.getParameter("activo") == null ? "" : request.getParameter("activo");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			UnidadProduccion up 	= new UnidadProduccion();
			UnidadProduccionDAO upDAO 	= new UnidadProduccionDAO();
			JSONObject upJSONObject = new JSONObject();
			JSONArray upJSONArray = new JSONArray();


			if (!codigo.equals(""))
				up.setId(codigo);
			
			if (!unidad_negocio.equals("")){
				UnidadNegocioDAO un = new UnidadNegocioDAO();
				up.setUnidadNegocio(un.buscarPorId(unidad_negocio));}

			if (!nombre.equals(""))
				up.setNombre(nombre);
			
			if (!up_ensurance.equals(""))
				up.setUpEnsurance(up_ensurance);

			if (activo.equals("1"))
				up.setActivo(true);
			else if(!tipoConsulta.equals("eliminar"))
				up.setActivo(false);

			if(tipoConsulta.equals("encontrarTodos")){ 
				List<UnidadProduccion> results = upDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					up = results.get(i);
					upJSONObject.put("codigo", up.getId());
					upJSONObject.put("nombre", up.getNombre());
					upJSONObject.put("unidad_negocio", up.getUnidadNegocio().getNombre() );
					upJSONObject.put("up_ensurance", up.getUpEnsurance() );
					
					if(up.getActivo())
						upJSONObject.put("activo", "Si");
					else
						upJSONObject.put("activo", "No");

					upJSONArray.add(upJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoUP", upJSONArray);
				
				//unidades de negocio
				
				JSONObject unJSONObject = new JSONObject();
				JSONArray unJSONArray = new JSONArray();
				
				UnidadNegocio un 	= new UnidadNegocio();
				UnidadNegocioDAO unDAO 	= new UnidadNegocioDAO();
				
				List<UnidadNegocio> resultsUN = unDAO.buscarTodos();
				int j=0;
				for(j=0; j<resultsUN.size(); j++){
					un = resultsUN.get(j);
					unJSONObject.put("codigo", un.getId());
					unJSONObject.put("nombre", un.getNombre());
					unJSONObject.put("un_ensurance", un.getUnEnsurance() );
					
					if(un.getActivo())
						unJSONObject.put("activo", "Si");
					else
						unJSONObject.put("activo", "No");

					unJSONArray.add(unJSONObject);
				}
				
				result.put("listadoUN", unJSONArray);
			}
			
			
			if(tipoConsulta.equals("encontrarUnidadesProduccionPorUnidadNegocio")){ 
				String unidadNegocioId = request.getParameter("unidadNegocioId") == null ? "" : request.getParameter("unidadNegocioId");
				UnidadNegocioDAO unidadNegocioDAO = new UnidadNegocioDAO();
				UnidadNegocio unidadNegocio = unidadNegocioDAO.buscarPorId(unidadNegocioId);
				List<UnidadProduccion> results = upDAO.buscarPorUnidadNegocio(unidadNegocio);
				int i=0;
				for(i=0; i<results.size(); i++){
					up = results.get(i);
					upJSONObject.put("codigo", up.getId());
					upJSONObject.put("nombre", up.getNombre());															
					upJSONArray.add(upJSONObject);
				}				
				result.put("listadoUnidadProduccion", upJSONArray);															
			}
			
			if(tipoConsulta.equals("crear"))
				unidadProduccionTransaction.crear(up);

			if(tipoConsulta.equals("actualizar"))
				unidadProduccionTransaction.editar(up);

			if(tipoConsulta.equals("eliminar"))
			{
				up = upDAO.buscarPorId(up.getId());
				unidadProduccionTransaction.eliminar(up);
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
