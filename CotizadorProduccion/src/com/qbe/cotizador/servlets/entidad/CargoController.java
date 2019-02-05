package com.qbe.cotizador.servlets.entidad;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.entidad.CargoDAO;
import com.qbe.cotizador.model.Cargo;
import com.qbe.cotizador.transaction.entidad.CargoTransaction;

/**
 * Servlet implementation class Cargo
 */
@WebServlet("/CargoController")
public class CargoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargoController() {
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
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		try{
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
			String activo = request.getParameter("activo") == null ? "" : request.getParameter("activo");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			JSONObject cargoJSONObject = new JSONObject();
			JSONArray cargoJSONArray = new JSONArray();

			Cargo cargo = new Cargo();
			CargoDAO cargoDAO = new CargoDAO();
			
			CargoTransaction cargoTransaction = new CargoTransaction(); 

			if (!codigo.equals(""))
				cargo.setId(codigo);

			if (!nombre.equals(""))
				cargo.setNombre(nombre);

			if (activo.equals("1"))
				cargo.setActivo(true);
			else if(!tipoConsulta.equals("eliminar"))
				cargo.setActivo(false);

			if(tipoConsulta.equals("encontrarTodos")){ 
				List<Cargo> results = cargoDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					cargo = results.get(i);
					cargoJSONObject.put("codigo", cargo.getId());
					cargoJSONObject.put("nombre", cargo.getNombre());
//					cargoJSONObject.put("descripcion", cargo.getDescripcion());
//					cargoJSONObject.put("rangoInicial", cargo.getRangoInicial());
//					cargoJSONObject.put("rangoFinal", cargo.getRangoFinal());
					
					if(cargo.getActivo())
						cargoJSONObject.put("activo", "Si");
					else
						cargoJSONObject.put("activo", "No");

					cargoJSONArray.add(cargoJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoCargo", cargoJSONArray);
			}
			
			// Encontramos las cargoes activas ayanez
			if(tipoConsulta.equals("encontrarCargoesActivas")){
				List<Cargo> listado = cargoDAO.buscarActivos();
				if(listado.size() > 0) {
					JSONObject cargoesJSON = new JSONObject();
					for(int i=0; i<listado.size(); i++) {
						cargo = (Cargo) listado.get(i);					
						cargoesJSON.put("id", cargo.getId());
						cargoesJSON.put("nombre", cargo.getNombre());
						cargoJSONArray.add(cargoesJSON);
					}
				}
				result.put("cargoes", cargoJSONArray);
			}

			if(tipoConsulta.equals("crear"))
				cargoTransaction.crear(cargo);

			if(tipoConsulta.equals("actualizar"))
				cargoTransaction.editar(cargo);

			if(tipoConsulta.equals("eliminar"))
				cargoTransaction.eliminar(cargo);


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
