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

import com.qbe.cotizador.dao.entidad.CargoDAO;
import com.qbe.cotizador.dao.entidad.EmpleadoDAO;
import com.qbe.cotizador.dao.entidad.UnidadNegocioDAO;
import com.qbe.cotizador.dao.cotizacion.GrupoAutorizacionDAO;
import com.qbe.cotizador.dao.cotizacion.GrupoUsuarioAutorizacionDAO;
import com.qbe.cotizador.model.Cargo;
import com.qbe.cotizador.model.Empleado;
import com.qbe.cotizador.model.GrupoAutorizacion;
import com.qbe.cotizador.model.GrupoUsuarioAutorizacion;
import com.qbe.cotizador.transaction.seguridad.GrupoAutorizacionTransaction;
import com.qbe.cotizador.transaction.seguridad.GrupoUsuarioAutorizacionTransaction;

/**
 * Servlet implementation class GrupoAutorizacionController
 */
@WebServlet("/GrupoAutorizacionController")
public class GrupoAutorizacionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GrupoAutorizacionController() {
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
			JSONObject result = new JSONObject();
			try{
				String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
				String unidadNegocioId = request.getParameter("unidadNegocioId") == null ? "" : request.getParameter("unidadNegocioId");
				String empleadoId = request.getParameter("lider") == null ? "" : request.getParameter("lider");
				String codigoLider = request.getParameter("codigoLider") == null ? "" : request.getParameter("codigoLider");
				String activa = request.getParameter("activo") == null ? "" : request.getParameter("activo");
				String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
				String listaGerentesSeleccionados = request.getParameter("listaGerentesSeleccionados") == null ? "" : request.getParameter("listaGerentesSeleccionados");
				String listaDirectoresSeleccionados = request.getParameter("listaDirectoresSeleccionados") == null ? "" : request.getParameter("listaDirectoresSeleccionados");
				String listadoEmpleadosSeleccionados = listaGerentesSeleccionados + listaDirectoresSeleccionados;
				
				JSONObject grupoAutorizacionJSONObject = new JSONObject();
				JSONArray grupoAutorizacionJSONArray = new JSONArray();
	
				GrupoAutorizacion grupoAutorizacion = new GrupoAutorizacion();
				GrupoAutorizacionDAO grupoAutorizacionDAO = new GrupoAutorizacionDAO();
				
				GrupoUsuarioAutorizacion grupoUsuarioAutorizacion = new GrupoUsuarioAutorizacion();
				GrupoUsuarioAutorizacionDAO grupoUsuarioAutorizacionDAO = new GrupoUsuarioAutorizacionDAO();
	
				GrupoAutorizacionTransaction grupoAutorizacionTransaction= new GrupoAutorizacionTransaction();
				GrupoUsuarioAutorizacionTransaction grupoUsuarioAutorizacionTransaction= new GrupoUsuarioAutorizacionTransaction();
				
				
				if (!codigo.equals(""))
					grupoAutorizacion.setId(codigo);   
	
				if (!unidadNegocioId.equals("")){
					UnidadNegocioDAO unidadNegocioDAO = new UnidadNegocioDAO();
					grupoAutorizacion.setUnidadNegocio(unidadNegocioDAO.buscarPorId(unidadNegocioId));
				}
					
	
				if (!empleadoId.equals("")){
					Empleado empleado = new Empleado();
					EmpleadoDAO empleadoDAO = new EmpleadoDAO();
					empleado = empleadoDAO.buscarPorId(empleadoId);
					grupoAutorizacion.setEmpleado(empleado);					
				}
			
				if (activa.equals("1"))
					grupoAutorizacion.setActivo(true);
				else if(!tipoConsulta.equals("eliminar"))
					grupoAutorizacion.setActivo(false);
	
				if(tipoConsulta.equals("encontrarTodos")){ 
					List<GrupoAutorizacion> results = grupoAutorizacionDAO.buscarTodos();
					int i=0;
					for(i=0; i<results.size(); i++){
						grupoAutorizacion = results.get(i);
						grupoAutorizacionJSONObject.put("codigo", grupoAutorizacion.getId());
						grupoAutorizacionJSONObject.put("lider", grupoAutorizacion.getEmpleado().getEntidad().getNombreCompleto());
						grupoAutorizacionJSONObject.put("unidadNegocioNombre", grupoAutorizacion.getUnidadNegocio().getNombre());
						grupoAutorizacionJSONObject.put("unidadNegocioId", grupoAutorizacion.getUnidadNegocio().getId());

						if(grupoAutorizacion.getActivo())
							grupoAutorizacionJSONObject.put("activo", "Si");
						else
							grupoAutorizacionJSONObject.put("activo", "No");
	
						grupoAutorizacionJSONArray.add(grupoAutorizacionJSONObject);
					}
					result.put("numRegistros",i);
					result.put("listadoGrupoAutorizacion", grupoAutorizacionJSONArray);
					
					Cargo cargo = new Cargo();
					CargoDAO cargoDAO = new CargoDAO();
					
					EmpleadoDAO empleadoDAO = new EmpleadoDAO();
					Empleado empleado = new Empleado();
					
					JSONObject empleadoVJSONObject = new JSONObject();
					JSONArray empleadoVJSONArray = new JSONArray();
					
					
					List<Cargo> listado = cargoDAO.buscarPorCargoGenerico("VICEPRESIDENTE");
					if(listado.size() > 0) {
						for(i=0; i<listado.size(); i++) {
							cargo = (Cargo) listado.get(i);
							List<Empleado> listaEmpleado = empleadoDAO.buscarPorCargo(cargo);
							if (listaEmpleado.size() > 0){
								for(int j=0; j<listaEmpleado.size(); j++){
									empleado = listaEmpleado.get(j);
									empleadoVJSONObject.put("codigoEmpleado", empleado.getId());
									empleadoVJSONObject.put("codigoEntidad", empleado.getEntidad().getId());
									empleadoVJSONObject.put("nombre", empleado.getEntidad().getNombreCompleto());
									empleadoVJSONObject.put("mail", empleado.getEntidad().getMail());
									
									empleadoVJSONArray.add(empleadoVJSONObject);
								}							
							}
						}
					}
					result.put("numRegistros",i);
					result.put("listadoEmpleadosV",empleadoVJSONArray);				
	
					listado = cargoDAO.buscarPorCargoGenerico("GERENTE");
					JSONObject empleadoGJSONObject = new JSONObject();
					JSONArray empleadoGJSONArray = new JSONArray();
					if(listado.size() > 0) {
						for(i=0; i<listado.size(); i++) {
							cargo = (Cargo) listado.get(i);
							List<Empleado> listaEmpleado = empleadoDAO.buscarPorCargo(cargo);
							if (listaEmpleado.size() > 0){
								for(int j=0; j<listaEmpleado.size(); j++){
									empleado = listaEmpleado.get(j);
									empleadoGJSONObject.put("codigoEmpleado", empleado.getId());
									empleadoGJSONObject.put("codigoEntidad", empleado.getEntidad().getId());
									empleadoGJSONObject.put("nombre", empleado.getEntidad().getNombreCompleto());
									empleadoGJSONObject.put("mail", empleado.getEntidad().getMail());
									
									empleadoGJSONArray.add(empleadoGJSONObject);
								}							
							}
						}
					}
					result.put("numRegistros",i);
					result.put("listadoEmpleadosG",empleadoGJSONArray);				
				
					listado = cargoDAO.buscarPorCargoGenerico("DIRECTOR");
					JSONObject empleadoDJSONObject = new JSONObject();
					JSONArray empleadoDJSONArray = new JSONArray();
					if(listado.size() > 0) {
						for(i=0; i<listado.size(); i++) {
							cargo = (Cargo) listado.get(i);
							List<Empleado> listaEmpleado = empleadoDAO.buscarPorCargo(cargo);
							if (listaEmpleado.size() > 0){
								for(int j=0; j<listaEmpleado.size(); j++){
									empleado = listaEmpleado.get(j);
									empleadoDJSONObject.put("codigoEmpleado", empleado.getId());
									empleadoDJSONObject.put("codigoEntidad", empleado.getEntidad().getId());
									empleadoDJSONObject.put("nombre", empleado.getEntidad().getNombreCompleto());
									empleadoDJSONObject.put("mail", empleado.getEntidad().getMail());
								
									empleadoDJSONArray.add(empleadoDJSONObject);
								}							
							}
						}
					}
					result.put("numRegistros",i);
					result.put("listadoEmpleadosD",empleadoDJSONArray);				
				
				}
				
				// Encontramos las grupoAutorizaciones activas ayanez
				if(tipoConsulta.equals("encontrarGrupoAutorizacionesActivas")){
					List<GrupoAutorizacion> listado = grupoAutorizacionDAO.buscarActivos();
					if(listado.size() > 0) {
						JSONObject grupoAutorizacionesJSON = new JSONObject();
						for(int i=0; i<listado.size(); i++) {
							grupoAutorizacion = (GrupoAutorizacion) listado.get(i);					
							grupoAutorizacionesJSON.put("id", grupoAutorizacion.getId());
							grupoAutorizacionesJSON.put("unidadNegocioNombre", grupoAutorizacion.getUnidadNegocio().getNombre());
							grupoAutorizacionesJSON.put("unidadNegocioId", grupoAutorizacion.getUnidadNegocio().getId());							
							grupoAutorizacionJSONArray.add(grupoAutorizacionesJSON);
						}
					}
					result.put("grupoAutorizaciones", grupoAutorizacionJSONArray);
				}
	
				
				if(tipoConsulta.equals("crear")){
					grupoAutorizacion = grupoAutorizacionTransaction.crear(grupoAutorizacion);
					String[] arrlistadoEmpleadosSeleccionados = listadoEmpleadosSeleccionados.split(",");
					Empleado empleadoNuevo = new Empleado();
					EmpleadoDAO empleadoDAO = new EmpleadoDAO();
					if(!arrlistadoEmpleadosSeleccionados[0].equals(""))
						for (int i=0; i<arrlistadoEmpleadosSeleccionados.length; i++){
							GrupoUsuarioAutorizacion grupoUsuarioAutorizacionNuevo = new GrupoUsuarioAutorizacion();
							empleadoNuevo = empleadoDAO.buscarPorId(arrlistadoEmpleadosSeleccionados[i]);
							grupoUsuarioAutorizacionNuevo.setEmpleado(empleadoNuevo);
							grupoUsuarioAutorizacionNuevo.setGrupoAutorizacion(grupoAutorizacion);
							grupoUsuarioAutorizacionTransaction.crear(grupoUsuarioAutorizacionNuevo);
						}
				}
				
				if(tipoConsulta.equals("actualizar")){
					grupoAutorizacion = grupoAutorizacionTransaction.editar(grupoAutorizacion);
					
					EmpleadoDAO empleadoDAO = new EmpleadoDAO();
					
					String listaRegistrosBase = "";
					List <GrupoUsuarioAutorizacion> listaGrupoUsuarioAutorizacion = grupoUsuarioAutorizacionDAO.buscarPorGrupoAutorizacion(grupoAutorizacion);
					if(listaGrupoUsuarioAutorizacion.size() > 0){
						for(int i=0; i<listaGrupoUsuarioAutorizacion.size(); i++){
							listaRegistrosBase = listaRegistrosBase + listaGrupoUsuarioAutorizacion.get(i).getEmpleado().getId()+",";
						}
					}
					
					//String listadoEmpleadosSeleccionadosOriginal = listadoEmpleadosSeleccionados;
					//String ListaRegistrosBaseOriginal = listaRegistrosBase;
					String listadoUsuariosNuevos = "";
					
					String[] arrlistadoEmpleadosSeleccionados = listadoEmpleadosSeleccionados.split(",");
					
					for(int i=0; i<arrlistadoEmpleadosSeleccionados.length; i++){
						String comprobarCodigo = arrlistadoEmpleadosSeleccionados[i] + ",";
						if(listaRegistrosBase.contains(comprobarCodigo)){
							listaRegistrosBase = listaRegistrosBase.replace(comprobarCodigo, " ");
						}
						else
							listadoUsuariosNuevos = listadoUsuariosNuevos + arrlistadoEmpleadosSeleccionados[i] + ",";
					}
					
					String[] arrListaRegistrosBase = listaRegistrosBase.trim().split(",");
					Empleado empleadoNuevo = new Empleado();
					if(!arrListaRegistrosBase[0].equals(""))					
						for(int i=0; i<arrListaRegistrosBase.length; i++){
							GrupoUsuarioAutorizacion grupoUsuarioAutorizacionNuevo = new GrupoUsuarioAutorizacion();
							empleadoNuevo = empleadoDAO.buscarPorId(arrListaRegistrosBase[i]);
							grupoUsuarioAutorizacionNuevo.setEmpleado(empleadoNuevo);
							listaGrupoUsuarioAutorizacion = grupoUsuarioAutorizacionDAO.buscarPorEmpleadoYGrupo(empleadoNuevo, grupoAutorizacion);
							for(int j=0; j<listaGrupoUsuarioAutorizacion.size(); j++){
								grupoUsuarioAutorizacionNuevo = listaGrupoUsuarioAutorizacion.get(j);
								grupoUsuarioAutorizacionTransaction.eliminar(grupoUsuarioAutorizacionNuevo);
							}
						}
					
					String[] arrlistadoUsuariosNuevos = listadoUsuariosNuevos.trim().split(",");
					
					if(!arrlistadoUsuariosNuevos[0].equals(""))
						for(int i=0; i<arrlistadoUsuariosNuevos.length; i++){
							GrupoUsuarioAutorizacion grupoUsuarioAutorizacionNuevo = new GrupoUsuarioAutorizacion();
							empleadoNuevo = empleadoDAO.buscarPorId(arrlistadoUsuariosNuevos[i]);
							grupoUsuarioAutorizacionNuevo.setEmpleado(empleadoNuevo);
							grupoUsuarioAutorizacionNuevo.setGrupoAutorizacion(grupoAutorizacion);
							grupoUsuarioAutorizacionNuevo.setEmpleado(empleadoNuevo);
							grupoUsuarioAutorizacionTransaction.crear(grupoUsuarioAutorizacionNuevo);
						}					
				}
					
	
				if(tipoConsulta.equals("eliminar")){
					List<GrupoUsuarioAutorizacion> listaGrupoUsuarioAutorizacion = grupoUsuarioAutorizacionDAO.buscarPorGrupoAutorizacion(grupoAutorizacion);
					for (int i=0; i<listaGrupoUsuarioAutorizacion.size(); i++){
						grupoUsuarioAutorizacion = listaGrupoUsuarioAutorizacion.get(i);
						grupoUsuarioAutorizacionTransaction.eliminar(grupoUsuarioAutorizacion);
					}
					grupoAutorizacionTransaction.eliminar(grupoAutorizacion);
				}
					
	
	
				if(tipoConsulta.equals("usuariosActivosGrupo")){
					grupoAutorizacion = grupoAutorizacionDAO.buscarPorId(codigo);
					
					List <GrupoUsuarioAutorizacion> listadoGrupoUsuarioAutorizacion= grupoUsuarioAutorizacionDAO.buscarPorGrupoAutorizacion(grupoAutorizacion);
					
					if (listadoGrupoUsuarioAutorizacion.size() > 0){
						JSONObject grupoUsuarioAutorizacionJSONObject = new JSONObject();
						JSONArray grupoUsuarioAutorizacionJSONArray = new JSONArray();
						for (int i=0; i<listadoGrupoUsuarioAutorizacion.size(); i++){
							grupoUsuarioAutorizacion = listadoGrupoUsuarioAutorizacion.get(i);
							grupoUsuarioAutorizacionJSONObject.put("codigoEmpleado", grupoUsuarioAutorizacion.getEmpleado().getId());
							if(grupoUsuarioAutorizacion.getEmpleado().getCargo().getNombreGenerico() != null)
								grupoUsuarioAutorizacionJSONObject.put("nombreGenerico", grupoUsuarioAutorizacion.getEmpleado().getCargo().getNombreGenerico().toLowerCase());
							else
								grupoUsuarioAutorizacionJSONObject.put("nombreGenerico", grupoUsuarioAutorizacion.getEmpleado().getCargo().getNombre().toLowerCase());
							grupoUsuarioAutorizacionJSONArray.add(grupoUsuarioAutorizacionJSONObject);
						}
						result.put("listadoGrupoUsuarioAutorizacion", grupoUsuarioAutorizacionJSONArray);
					}

				}
				
				if(tipoConsulta.equals("encontrarPorLider") && !codigoLider.equals("")){
					EmpleadoDAO empleadoDAO = new EmpleadoDAO();
					Empleado empleado = empleadoDAO.buscarPorId(codigoLider);
					
					grupoAutorizacion = grupoAutorizacionDAO.buscarPorLider(empleado);
					
					List <GrupoUsuarioAutorizacion> listadoGrupoUsuarioAutorizacion= grupoUsuarioAutorizacionDAO.buscarPorGrupoAutorizacion(grupoAutorizacion);
					
					if (listadoGrupoUsuarioAutorizacion.size() > 0){
						JSONObject grupoUsuarioAutorizacionJSONObject = new JSONObject();
						JSONArray grupoUsuarioAutorizacionJSONArray = new JSONArray();
						for (int i=0; i<listadoGrupoUsuarioAutorizacion.size(); i++){
							grupoUsuarioAutorizacion = listadoGrupoUsuarioAutorizacion.get(i);
							grupoUsuarioAutorizacionJSONObject.put("codigoEmpleado", grupoUsuarioAutorizacion.getEmpleado().getId());
							grupoUsuarioAutorizacionJSONObject.put("nombreGenerico", grupoUsuarioAutorizacion.getEmpleado().getCargo().getNombreGenerico().toLowerCase());
							grupoUsuarioAutorizacionJSONObject.put("nombre", grupoUsuarioAutorizacion.getEmpleado().getEntidad().getNombreCompleto());
							grupoUsuarioAutorizacionJSONObject.put("mail", grupoUsuarioAutorizacion.getEmpleado().getEntidad().getMail());
							grupoUsuarioAutorizacionJSONArray.add(grupoUsuarioAutorizacionJSONObject);
						}
						result.put("listadoGrupoUsuarioAutorizacion", grupoUsuarioAutorizacionJSONArray);
					}

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
			System.out.println(e.getMessage());

		}		
	}

}
