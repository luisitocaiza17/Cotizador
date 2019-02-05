package com.qbe.cotizador.servlets.producto.pymes;

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
import com.google.gson.JsonObject;
import com.qbe.cotizador.dao.entidad.CantonDAO;
import com.qbe.cotizador.dao.entidad.CiudadDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.producto.agricola.PymeCiudadRiesgoDAO;
import com.qbe.cotizador.dao.producto.agricola.UsuariosOfflineDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeCiudadRiesgoVista;
import com.qbe.cotizador.dao.producto.pymes.PymeTipoRiesgoDAO;
import com.qbe.cotizador.model.Canton;
import com.qbe.cotizador.model.Ciudad;
import com.qbe.cotizador.model.Provincia;
import com.qbe.cotizador.model.PymeCiudadRiesgo;
import com.qbe.cotizador.model.PymeTipoRiesgo;
import com.qbe.cotizador.model.UsuariosOffline;
import com.qbe.cotizador.servlets.producto.agricola.DataSourceResult;
import com.qbe.cotizador.transaction.entidad.CiudadTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeCiudadRiesgoTransaction;

/**
 * Servlet implementation class PymeCiudadRiesgoController
 */
@WebServlet("/PymeCiudadRiesgoController")
public class PymeCiudadRiesgoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PymeCiudadRiesgoController() {
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
		JSONObject result = new JSONObject();
		try{
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String provinciaId = request.getParameter("provinciaId") == null ? "" : request.getParameter("provinciaId");
			String ciudadId = request.getParameter("ciudadId") == null ? "" : request.getParameter("ciudadId");
			String tipodId = request.getParameter("tipodId") == null ? "" : request.getParameter("tipodId");
			String id = request.getParameter("id") == null ? "" : request.getParameter("id");
			
			PymeCiudadRiesgo pymeCiudadRiesgoObjeto = new PymeCiudadRiesgo();
			Provincia provinciaObjeto= new Provincia();
			Canton ciudadObjeto = new Canton();
			PymeTipoRiesgo pymeTipoRiesgoObjeto= new PymeTipoRiesgo();
			if(!id.equals(""))
				pymeCiudadRiesgoObjeto.setId(new BigInteger(id));
			if(!provinciaId.equals("")){
				ProvinciaDAO provinciaDAO = new ProvinciaDAO();
				provinciaObjeto=provinciaDAO.buscarPorId(provinciaId);
				pymeCiudadRiesgoObjeto.setProvincia(provinciaObjeto);
			}
			if(!ciudadId.equals("")){
				CantonDAO ciudadDAO = new CantonDAO();
				ciudadObjeto=ciudadDAO.buscarPorId(ciudadId);
				pymeCiudadRiesgoObjeto.setCanton(ciudadObjeto);
			}
			if(!tipodId.equals("")){
				PymeTipoRiesgoDAO pymeTipoRiesgoDAO= new PymeTipoRiesgoDAO();
				pymeTipoRiesgoObjeto=pymeTipoRiesgoDAO.BuscarPorId(new BigInteger(tipodId));
				pymeCiudadRiesgoObjeto.setPymeTipoRiesgo(pymeTipoRiesgoObjeto);
			}
			
			if(tipoConsulta.equals("crear")){
				PymeCiudadRiesgoDAO pymeCiudadRiesgoDAO2 = new PymeCiudadRiesgoDAO();
				int contador=pymeCiudadRiesgoDAO2.BuscarPorCiudad(ciudadObjeto).size();
				if(contador==0){
					PymeCiudadRiesgoTransaction ciudadRiesgoTransaction= new PymeCiudadRiesgoTransaction(); 
					ciudadRiesgoTransaction.crear(pymeCiudadRiesgoObjeto);
				}
				else{
					throw new Exception("La ciudad no puede contener mas de un tipo de riesgo");
				}
			}
			
			if(tipoConsulta.equals("editar")){
				PymeCiudadRiesgoDAO pymeCiudadRiesgoDAO2 = new PymeCiudadRiesgoDAO();
				int contador=pymeCiudadRiesgoDAO2.BuscarPorCiudad(ciudadObjeto).size();
				if(contador==0){
					PymeCiudadRiesgoTransaction ciudadRiesgoTransaction= new PymeCiudadRiesgoTransaction(); 
					ciudadRiesgoTransaction.editar(pymeCiudadRiesgoObjeto);
				}
				else{
					throw new Exception("La ciudad no puede contener mas de un tipo de riesgo");
				}
			}
			
			if(tipoConsulta.equals("eliminar")){
				PymeCiudadRiesgoTransaction ciudadRiesgoTransaction= new PymeCiudadRiesgoTransaction(); 
				ciudadRiesgoTransaction.eliminar(pymeCiudadRiesgoObjeto);
			}
			
			if(tipoConsulta.equals("obtenerPorId")){
				PymeCiudadRiesgo ciudadRiesgo= new PymeCiudadRiesgo();
				PymeCiudadRiesgoDAO  pymeCiudadRiesgoDAO= new PymeCiudadRiesgoDAO();
				ciudadRiesgo=pymeCiudadRiesgoDAO.BuscarPorId(new BigInteger(id));
				result.put("id", ciudadRiesgo.getId());
				result.put("provinciaId", ciudadRiesgo.getProvincia().getId());
				result.put("ciudadId", ciudadRiesgo.getCanton().getId());
				result.put("tiposId", ciudadRiesgo.getPymeTipoRiesgo().getId());
			}
			
			
			//por el momento quemado no se vio la necesidad de agregar mas
			if(tipoConsulta.equals("encontrarTipos")){ 
				JSONArray TipoJSONArray = new JSONArray();
				JSONObject tipo = new JSONObject();
				PymeTipoRiesgoDAO pymeTipoRiesgoDAO = new PymeTipoRiesgoDAO();
				List<PymeTipoRiesgo> pymeTipoRiesgo=pymeTipoRiesgoDAO.buscarTodos();
				for(PymeTipoRiesgo rs:pymeTipoRiesgo){
					tipo.put("nombre", rs.getNombre());
					tipo.put("id", rs.getId());
					TipoJSONArray.add(tipo);
				}
				result.put("listTipos", TipoJSONArray);
			}
			
			if(tipoConsulta.equals("buscarTodos")){ 
				/*cargamos la tabla*/
				int skip = request.getParameter("skip") == null ? 0 : Integer.parseInt(request.getParameter("skip"));
				int take = request.getParameter("take") == null ? 20 : Integer.parseInt(request.getParameter("take"));

				String provincia = request.getParameter("Provincia") == null ? "" : request.getParameter("Provincia");
				String ciudad = request.getParameter("Ciudad") == null ? "" : request.getParameter("Ciudad");
				
				Provincia provinciaO=new Provincia();
				if(!provincia.equals("")){
					ProvinciaDAO provinciaDAO= new ProvinciaDAO();
					provinciaO=provinciaDAO.buscarPorId(provincia);					
				}
				Canton ciudadO= new Canton();
				if(!ciudad.equals("")){
					CantonDAO ciudadDAO= new CantonDAO();
					ciudadO=ciudadDAO.buscarPorId(ciudad);
				}
				List<PymeCiudadRiesgo> data = new ArrayList<PymeCiudadRiesgo>();
				PymeCiudadRiesgoDAO pymeCiudadRiesgoDAO= new PymeCiudadRiesgoDAO();
				data=pymeCiudadRiesgoDAO.cargarTodosKendo(skip, take,provinciaO,ciudadO);
				//lleno Lista para mostrar
				List<PymeCiudadRiesgoVista> lista= new ArrayList<PymeCiudadRiesgoVista>();
				for(PymeCiudadRiesgo rs :data){
					PymeCiudadRiesgoVista pymeCiudadRiesgoVista= new PymeCiudadRiesgoVista();
					pymeCiudadRiesgoVista.setId(rs.getId());
					pymeCiudadRiesgoVista.setProvincia((rs.getProvincia().getNombre()));
					pymeCiudadRiesgoVista.setCiudad((rs.getCanton().getNombre()));
					pymeCiudadRiesgoVista.setTipo(rs.getPymeTipoRiesgo().getNombre());
					lista.add(pymeCiudadRiesgoVista);
				}
				
				long total=pymeCiudadRiesgoDAO.cargarTodosKendoPorNumero(skip, take,provinciaO,ciudadO);
						
				DataSourceResult pg = new DataSourceResult();
				pg.setTotal((int)total);
				pg.setData(lista);
				
				Gson gson = new Gson(); 
				// convert the DataSourceReslt to JSON and write it to the response
				response.setContentType("application/json; charset=ISO-8859-1");
			    response.getWriter().print(gson.toJson(pg));
			    return;		
			}
			

			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
		}catch(Exception e){
			result.put("success", Boolean.FALSE);
			result.put("mensaje", e.getLocalizedMessage());
			result.put("error", e.getLocalizedMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			e.printStackTrace();

		}
	}
}