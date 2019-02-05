package com.qbe.cotizador.servlets.entidad;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.entidad.RegionDAO;
import com.qbe.cotizador.dao.entidad.SucursalDAO;
import com.qbe.cotizador.model.Region;
import com.qbe.cotizador.model.Sucursal;
import com.qbe.cotizador.transaction.entidad.SucursalTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Sucursal
 */
@WebServlet("/SucursalController")
public class SucursalController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SucursalController() {
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
			String codigoEnsurance = request.getParameter("codigoEnsurance") == null ? "" : request.getParameter("codigoEnsurance");
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
			String matriz = request.getParameter("matriz") == null ? "" : request.getParameter("matriz");
			String activa = request.getParameter("activo") == null ? "" : request.getParameter("activo");
			String indicechoquetotal = request.getParameter("indicechoquetotal") == null ? "" : request.getParameter("indicechoquetotal");
			String indicedanoparcialfrecuencia= request.getParameter("indicedanoparcialfrecuencia") == null ? "" : request.getParameter("indicedanoparcialfrecuencia");
			String indicedanoparcialseveridad = request.getParameter("indicedanoparcialseveridad") == null ? "" : request.getParameter("indicedanoparcialseveridad");
			String indiceresponsabilidadcivilfrecuencia = request.getParameter("indiceresponsabilidadcivilfrecuencia") == null ? "" : request.getParameter("indiceresponsabilidadcivilfrecuencia");
			String indiceresponsabilidadcivilseveridad = request.getParameter("indiceresponsabilidadcivilseveridad") == null ? "" : request.getParameter("indiceresponsabilidadcivilseveridad");
			String indicerobototal = request.getParameter("indicerobototal") == null ? "" : request.getParameter("indicerobototal");
			String region = request.getParameter("region") == null ? "" : request.getParameter("region");			
			
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			JSONObject sucursalJSONObject = new JSONObject();
			JSONArray sucursalJSONArray = new JSONArray();

			Sucursal sucursal = new Sucursal();
			SucursalDAO sucursalDAO = new SucursalDAO();
			
			SucursalTransaction sucursalTransaction = new SucursalTransaction();

			if (!codigo.equals(""))
				sucursal.setId(codigo);

			if (!codigoEnsurance.equals(""))
				sucursal.setSucEnsurance(codigoEnsurance);        

			if (!nombre.equals(""))
				sucursal.setNombre(nombre);
			
			if (!indicechoquetotal.equals(""))
				sucursal.setIndiceChoqueTotal(indicechoquetotal);
			
			if (!indicedanoparcialfrecuencia.equals(""))
				sucursal.setIndiceDanoParcialFrecuencia(indicedanoparcialfrecuencia);
			
			if (!indicedanoparcialseveridad.equals(""))
				sucursal.setIndiceDanoParcialSeveridad(indicedanoparcialseveridad);
			
			if (!indiceresponsabilidadcivilfrecuencia.equals(""))
				sucursal.setIndiceResponsabilidadCivilFrecuencia(indiceresponsabilidadcivilfrecuencia);
			
			if (!indiceresponsabilidadcivilseveridad.equals(""))
				sucursal.setIndiceResponsabilidadCivilSeveridad(indiceresponsabilidadcivilseveridad);
			
			if (!indicerobototal.equals(""))
				sucursal.setIndiceRoboTotal(indicerobototal);
			
			if (!region.equals("")){
				Region regionf = new Region();
				RegionDAO regionDAO = new RegionDAO();
				regionf = regionDAO.buscarPorNombre(region);
				sucursal.setRegion(regionf);
			}

			if (matriz.equals("1"))
				sucursal.setEsMatriz(true);
			else if(!tipoConsulta.equals("eliminar"))
				sucursal.setEsMatriz(false);

			if (activa.equals("1"))
				sucursal.setActivo(true);
			else if(!tipoConsulta.equals("eliminar"))
				sucursal.setActivo(false);

			if(tipoConsulta.equals("encontrarTodos")){ 
				List<Sucursal> results = sucursalDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					sucursal = results.get(i);
					sucursalJSONObject.put("codigo", sucursal.getId());
					sucursalJSONObject.put("codigoEnsurance", sucursal.getSucEnsurance());
					sucursalJSONObject.put("nombre", sucursal.getNombre());
					sucursalJSONObject.put("indicechoquetotal", sucursal.getIndiceChoqueTotal());
					sucursalJSONObject.put("indicedanoparcialfrecuencia", sucursal.getIndiceDanoParcialFrecuencia());
					sucursalJSONObject.put("indicedanoparcialseveridad", sucursal.getIndiceDanoParcialSeveridad());
					sucursalJSONObject.put("indiceresponsabilidadcivilfrecuencia", sucursal.getIndiceResponsabilidadCivilFrecuencia());					
					sucursalJSONObject.put("indiceresponsabilidadcivilseveridad", sucursal.getIndiceResponsabilidadCivilSeveridad());
					sucursalJSONObject.put("indicerobototal", sucursal.getIndiceRoboTotal());
					sucursalJSONObject.put("region", sucursal.getRegion().getNombre());
					if(sucursal.getEsMatriz())
						sucursalJSONObject.put("matriz", "Si");
					else
						sucursalJSONObject.put("matriz", "No");

					if(sucursal.getActivo())
						sucursalJSONObject.put("activo", "Si");
					else
						sucursalJSONObject.put("activo", "No");

					sucursalJSONArray.add(sucursalJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoSucursal", sucursalJSONArray);
		
				 RegionDAO regionDAO = new RegionDAO();
	             Region regions = new Region();
	             JSONObject regionJSONObject = new JSONObject();
	             JSONArray regionJSONArray = new JSONArray();
	             
	             List<Region> listadoRegion = regionDAO.buscarTodos();

	                for (i = 0; i < listadoRegion.size(); i++) {
	                    regions = listadoRegion.get(i);
	                    regionJSONObject.put("codigo", regions.getId());
	                    regionJSONObject.put("nombre", regions.getNombre());
	                    regionJSONArray.add(regionJSONObject);
	                }
	                result.put("listadoRegion", regionJSONArray);
	                
	              }
			
			// Encontramos las sucursales activas ayanez
			if(tipoConsulta.equals("encontrarSucursalesActivas")){
				List<Sucursal> listado = sucursalDAO.buscarActivos();
				if(listado.size() > 0) {
					JSONObject sucursalesJSON = new JSONObject();
					for(int i=0; i<listado.size(); i++) {
						sucursal = (Sucursal) listado.get(i);					
						sucursalesJSON.put("id", sucursal.getId());
						sucursalesJSON.put("nombre", sucursal.getNombre());
						sucursalJSONArray.add(sucursalesJSON);
					}
				}
				result.put("sucursales", sucursalJSONArray);
			}

			if(tipoConsulta.equals("crear"))
				sucursalTransaction.crear(sucursal);

			if(tipoConsulta.equals("actualizar"))
				sucursalTransaction.editar(sucursal);

			if(tipoConsulta.equals("eliminar"))
				sucursalTransaction.eliminar(sucursal);


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
