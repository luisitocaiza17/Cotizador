package com.qbe.cotizador.servlets.pagos;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.pagos.InstitucionFinancieraDAO;
import com.qbe.cotizador.model.InstitucionFinanciera;
import com.qbe.cotizador.transaction.pagos.InstitucionFinancieraTransaction;

/**
 * Servlet implementation class InstitucionFinancieraController
 */
@WebServlet("/InstitucionFinancieraController")
public class InstitucionFinancieraController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InstitucionFinancieraController() {
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
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			JSONObject InstitucionFinancieraJSONObject = new JSONObject();
			JSONArray InstitucionFinancieraJSONArray = new JSONArray();

			InstitucionFinanciera InstitucionFinanciera = new InstitucionFinanciera();
			InstitucionFinancieraDAO InstitucionFinancieraDAO = new InstitucionFinancieraDAO();
			
			InstitucionFinancieraTransaction institucionFinancieraTransaction = new InstitucionFinancieraTransaction();
/*
			if (!codigo.equals(""))
				InstitucionFinanciera.setId(codigo);

			if (!codigoEnsurance.equals(""))
				InstitucionFinanciera.setSucEnsurance(codigoEnsurance);        

			if (!nombre.equals(""))
				InstitucionFinanciera.setNombre(nombre);

			if (matriz.equals("1"))
				InstitucionFinanciera.setEsMatriz(true);
			else if(!tipoConsulta.equals("eliminar"))
				InstitucionFinanciera.setEsMatriz(false);

			if (activa.equals("1"))
				InstitucionFinanciera.setActivo(true);
			else if(!tipoConsulta.equals("eliminar"))
				InstitucionFinanciera.setActivo(false);
*/
			if(tipoConsulta.equals("encontrarTodos")){ 
				List<InstitucionFinanciera> results = InstitucionFinancieraDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					InstitucionFinanciera = results.get(i);
					
					InstitucionFinancieraJSONObject.put("codigo", InstitucionFinanciera.getId());
					InstitucionFinancieraJSONObject.put("codigoEnsurance", InstitucionFinanciera.getCodigoEnsurance());
					InstitucionFinancieraJSONObject.put("nombre", InstitucionFinanciera.getNombre());
					InstitucionFinancieraJSONObject.put("debito", InstitucionFinanciera.getEsDebito());
					InstitucionFinancieraJSONObject.put("tarjeta", InstitucionFinanciera.getEsTarjetaCredito());
					InstitucionFinancieraJSONObject.put("nemotecnico", InstitucionFinanciera.getNombreNemotecnico());

					InstitucionFinancieraJSONArray.add(InstitucionFinancieraJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoInstitucionFinanciera", InstitucionFinancieraJSONArray);
			}
			
			// Encontramos los InstitucionFinancieras activos ayanez
			if(tipoConsulta.equals("encontrarInstitucionFinancierasDebito")){
				List<InstitucionFinanciera> listado = InstitucionFinancieraDAO.buscarInstitucionFinancierasDebito();
				if(listado.size() > 0) {
					JSONObject InstitucionFinancierasJSON = new JSONObject();
					for(int i=0; i<listado.size(); i++) {
						InstitucionFinanciera = (InstitucionFinanciera) listado.get(i);					
						InstitucionFinancierasJSON.put("codigo", InstitucionFinanciera.getId());
						InstitucionFinancierasJSON.put("nombre", InstitucionFinanciera.getNombre());
						InstitucionFinancieraJSONArray.add(InstitucionFinancierasJSON);
					}
				}
				result.put("listadoInstitucionFinanciera", InstitucionFinancieraJSONArray);
			}
			
			if(tipoConsulta.equals("encontrarActivos")){
				List<InstitucionFinanciera> listado = InstitucionFinancieraDAO.buscarActivos();
				if(listado.size() > 0) {
					for(int i=0; i<listado.size(); i++) {
						InstitucionFinanciera = listado.get(i);
						
						InstitucionFinancieraJSONObject.put("codigo", InstitucionFinanciera.getId());
						InstitucionFinancieraJSONObject.put("codigoEnsurance", InstitucionFinanciera.getCodigoEnsurance());
						InstitucionFinancieraJSONObject.put("nombre", InstitucionFinanciera.getNombre());
						InstitucionFinancieraJSONObject.put("debito", InstitucionFinanciera.getEsDebito());
						InstitucionFinancieraJSONObject.put("tarjeta", InstitucionFinanciera.getEsTarjetaCredito());
						InstitucionFinancieraJSONObject.put("nemotecnico", InstitucionFinanciera.getNombreNemotecnico());

						InstitucionFinancieraJSONArray.add(InstitucionFinancieraJSONObject);
					}
				}
				result.put("listadoInstitucionFinanciera", InstitucionFinancieraJSONArray);
			}
					
			if(tipoConsulta.equals("crear"))
				institucionFinancieraTransaction.crear(InstitucionFinanciera);

			if(tipoConsulta.equals("actualizar"))
				institucionFinancieraTransaction.editar(InstitucionFinanciera);

			if(tipoConsulta.equals("eliminar"))
				institucionFinancieraTransaction.eliminar(InstitucionFinanciera);


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
