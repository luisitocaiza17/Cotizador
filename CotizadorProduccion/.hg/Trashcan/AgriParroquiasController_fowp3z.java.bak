package com.qbe.cotizador.servlets.producto.agricola;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.qbe.cotizador.dao.entidad.CantonDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriAgenciaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCargaPreviaArchivoPlanoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParametroXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParroquiaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParroquiasDetalleDAO;
import com.qbe.cotizador.dao.producto.agricola.UsuariosOfflineDAO;
import com.qbe.cotizador.model.AgriAgencia;
import com.qbe.cotizador.model.AgriCargaPreviaArchivoPlano;
import com.qbe.cotizador.model.AgriParametroXPuntoVenta;
import com.qbe.cotizador.model.AgriParroquia;
import com.qbe.cotizador.model.AgriParroquiasDetalle;
import com.qbe.cotizador.model.Canton;
import com.qbe.cotizador.model.Parroquia;
import com.qbe.cotizador.model.UsuariosOffline;
import com.qbe.cotizador.transaction.producto.agricola.AgriParroquiaTransaction;
import com.qbe.cotizador.transaction.producto.agricola.usuarioOfflineTransaction;

/**
 * Servlet implementation class AgriParroquiasController
 */
@WebServlet("/AgriParroquiasController")
public class AgriParroquiasController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriParroquiasController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		JSONObject result = new JSONObject();
		String tipoConsulta = request.getParameter("tipoConsulta") == null ? "":request.getParameter("tipoConsulta");
		
		
			/*Buscamos los combos para cargarlos a la ves*/
			String id = request.getParameter("id") == null ? "":request.getParameter("id");
			String cantonId = request.getParameter("cantonId") == null ? "":request.getParameter("cantonId");
			String parroquiaNombre = request.getParameter("parroquiaNombre") == null ? "":request.getParameter("parroquiaNombre");
			String parroquiaSBS = request.getParameter("parroquiaSBS") == null ? "":request.getParameter("parroquiaSBS");
			String parroquiaEnsurance = request.getParameter("parroquiaEnsurance") == null ? "":request.getParameter("parroquiaEnsurance");
			
			/*Cargamos las agencias*/
			AgriParroquiaDAO agriParroquiaDAO= new AgriParroquiaDAO();
			AgriParroquia  agriParroquia = new AgriParroquia();
			
			if(!id.equals(""))
				agriParroquia=agriParroquiaDAO.BuscarPorId(Integer.parseInt(id));
			if(!parroquiaSBS.equals(""))
				agriParroquia.setParroquiaSbs(parroquiaSBS);
			if(!parroquiaEnsurance.equals(""))
				agriParroquia.setCodigoEnsurance(parroquiaEnsurance);
			if(!parroquiaNombre.equals(""))
				agriParroquia.setParroquiaNombre(parroquiaNombre);
			if(!cantonId.equals(""))
				agriParroquia.setCantonId(cantonId);
			
		if(tipoConsulta.equals("editar")){
			AgriParroquiaTransaction agriParroquiaTransaction = new AgriParroquiaTransaction();
			agriParroquiaTransaction.editar(agriParroquia);

			/*Enviamos los combos*/
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());			
		}
		
		if(tipoConsulta.equals("encontrarPorCanton")){
			String canton = request.getParameter("canton") == null ? "" : request.getParameter("canton");
			CantonDAO cantonDAO= new CantonDAO();
			JSONArray parroquiaJSONArray = new JSONArray();
			
			List<AgriParroquia> listado =  agriParroquiaDAO.BuscarPorCanton(canton);
			if(listado.size() > 0) {
				JSONObject parroquiasJSON = new JSONObject();
				for(AgriParroquia rs:listado) {
					parroquiasJSON.put("id", rs.getId());
					parroquiasJSON.put("nombre", rs.getParroquiaNombre());
					parroquiaJSONArray.add(parroquiasJSON);
				}
			}
			result.put("listadoParroquia", parroquiaJSONArray);
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());	
		}
		
		if(tipoConsulta.equals("encontrarTodas")){
			
			String IdPrevio = request.getParameter("IdPrevio") == null ? "" : request.getParameter("IdPrevio");
			AgriCargaPreviaArchivoPlanoDAO agriCargaPreviaArchivoPlanoDAO=new AgriCargaPreviaArchivoPlanoDAO();
			AgriCargaPreviaArchivoPlano agriCargaPreviaArchivoPlano=agriCargaPreviaArchivoPlanoDAO.buscarPorId(new BigInteger(IdPrevio));
			
			JSONArray parroquiaJSONArray = new JSONArray();			
			List<AgriParroquia> listado =  agriParroquiaDAO.BuscarPorCanton(""+agriCargaPreviaArchivoPlano.getCantonId());
			if(listado.size() > 0) {
				JSONObject parroquiasJSON = new JSONObject();
				for(AgriParroquia rs:listado) {
					parroquiasJSON.put("id", rs.getId());
					parroquiasJSON.put("nombre", rs.getParroquiaNombre());
					parroquiaJSONArray.add(parroquiasJSON);
				}
			}
			result.put("listadoParroquia", parroquiaJSONArray);
			/*Enviamos los combos*/
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
		}
		
		if(tipoConsulta.equals("crear")){
			AgriParroquiaTransaction agriParroquiaTransaction = new AgriParroquiaTransaction();
			agriParroquiaTransaction.crear(agriParroquia);

			/*Enviamos los combos*/
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());			
		}
		
		if(tipoConsulta.equals("eliminar")){
			AgriParroquiaTransaction agriParroquiaTransaction = new AgriParroquiaTransaction();
			agriParroquiaTransaction.eliminar(agriParroquia);

			/*Enviamos los combos*/
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());			
		}
		
		if(tipoConsulta.equals("CargarParroquias")){
			/*Buscamos los combos para cargarlos a la ves*/
			String provinciaID = request.getParameter("provincia") == null ? "":request.getParameter("provincia");
			
			/*Cargamos las agencias*/
			JSONObject cantonesObjects = new JSONObject();
			JSONArray  cantonesArrays = new JSONArray();
			ProvinciaDAO provinciaDAO = new ProvinciaDAO();
			com.qbe.cotizador.model.Provincia provincia = provinciaDAO.buscarPorId(provinciaID); 
			CantonDAO cantonDAO = new CantonDAO();
			List<Canton> cantones= cantonDAO.buscarPorProvincia(provincia);
			for(Canton canton: cantones){
				cantonesObjects.put("id", canton.getId());
				cantonesObjects.put("nombre", canton.getNombre());
				cantonesArrays.add(cantonesObjects);
			}
			result.put("CantonesArray", cantonesArrays);
			
			/*Enviamos los combos*/
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			
		}
		
		if(tipoConsulta.equals("CargarParroquiasTodas")){
			/*Cargamos las agencias*/
			JSONObject cantonesObjects = new JSONObject();
			JSONArray  cantonesArrays = new JSONArray();
			CantonDAO cantonDAO = new CantonDAO();
			List<Canton> cantones= cantonDAO.buscarTodos();
			for(Canton canton: cantones){
				cantonesObjects.put("id", canton.getId());
				cantonesObjects.put("nombre", canton.getNombre());
				cantonesArrays.add(cantonesObjects);
			}
			result.put("CantonesArray", cantonesArrays);
			
			/*Enviamos los combos*/
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			
		}
		
		if(tipoConsulta.equals("encontrarPorParametros")){
						
			/*cargamos la tabla*/
			int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
			int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));
			String provinciaId = request.getParameter("provinciaId") == null ? "":request.getParameter("provinciaId");
			String cantonId2 = request.getParameter("cantonId") == null ? "":request.getParameter("cantonId");
			
			List<AgriParroquiasDetalle> data = new ArrayList<AgriParroquiasDetalle>();
			AgriParroquiasDetalleDAO agriParroquiasDetalleDAO = new AgriParroquiasDetalleDAO();
			
			data=agriParroquiasDetalleDAO.cargarTodosKendo(skip, take, provinciaId, cantonId2);
				
			long total=agriParroquiasDetalleDAO.cargarTodosKendoPorNumero(skip, take, provinciaId, cantonId2);
					
			DataSourceResult pg = new DataSourceResult();
			pg.setTotal((int)total);
			pg.setData(data);
			
			Gson gson = new Gson(); 
			// convert the DataSourceReslt to JSON and write it to the response
			response.setContentType("application/json; charset=ISO-8859-1");
		    response.getWriter().print(gson.toJson(pg));
		    return;		
			
		}
	}

}
