package com.qbe.cotizador.servlets.entidad;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.entidad.CargoDAO;
import com.qbe.cotizador.dao.entidad.EmpleadoDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.SucursalDAO;
import com.qbe.cotizador.dao.entidad.TipoEntidadDAO;
import com.qbe.cotizador.dao.entidad.TipoIdentificacionDAO;
import com.qbe.cotizador.model.Cargo;
import com.qbe.cotizador.model.Empleado;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Sucursal;
import com.qbe.cotizador.model.TipoIdentificacion;
import com.qbe.cotizador.transaction.entidad.EmpleadoTransaction;
import com.qbe.cotizador.transaction.entidad.EntidadTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Empleado
 */
@WebServlet("/EmpleadoController")
public class EmpleadoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmpleadoController() {
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
			String apellido = request.getParameter("apellido") == null ? "" : request.getParameter("apellido");
			String tipoIdentificacionId = request.getParameter("tipoIdentificacion") == null ? "" : request.getParameter("tipoIdentificacion");
			String identificacion = request.getParameter("identificacion") == null ? "" : request.getParameter("identificacion");
			String cargoId = request.getParameter("cargo") == null ? "" : request.getParameter("cargo");
			//String firma = request.getParameter("firma") == null ? "" : request.getParameter("firma");
			String mail = request.getParameter("mail") == null ? "" : request.getParameter("mail");
			String cargoGenerico = request.getParameter("cargoGenerico") == null ? "" : request.getParameter("cargoGenerico");
			//String codigoLider = request.getParameter("codigoLider") == null ? "" : request.getParameter("codigoLider");
			String activo = request.getParameter("activo") == null ? "" : request.getParameter("activo");
			String sucursalId = request.getParameter("sucursalId") == null ? "" : request.getParameter("sucursalId");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			
			String nombreFiltro = request.getParameter("nombreFiltro") == null ? "" : request.getParameter("nombreFiltro");
			String identificacionFiltro = request.getParameter("identificacionFiltro") == null ? "" : request.getParameter("identificacionFiltro");
			String tipoIdentificacionFiltro = request.getParameter("tipoIdentificacionFiltro") == null ? "" : request.getParameter("tipoIdentificacionFiltro");
			String apellidoFiltro = request.getParameter("apellidoFiltro") == null ? "" : request.getParameter("apellidoFiltro");
			String ensuranceFiltro = request.getParameter("ensuranceFiltro") == null ? "" : request.getParameter("ensuranceFiltro");
			String cargoFiltro = request.getParameter("cargoFiltro") == null ? "" : request.getParameter("cargoFiltro");
			String sucursalFiltro = request.getParameter("sucursalFiltro") == null ? "" : request.getParameter("sucursalFiltro");
			String activoFiltro = request.getParameter("activoFiltro") == null ? "" : request.getParameter("activoFiltro");
			
			
			EntidadTransaction entidadTransaction = new EntidadTransaction();
			EmpleadoTransaction empleadoTransaction = new EmpleadoTransaction();
			
			
			JSONObject empleadoJSONObject = new JSONObject();
			JSONArray empleadoJSONArray = new JSONArray();

			Empleado empleado = new Empleado();
			EmpleadoDAO empleadoDAO = new EmpleadoDAO();
			
			Entidad entidad = new Entidad();
			EntidadDAO entidadDAO = new EntidadDAO();

			if (!identificacion.equals(""))
				entidad=entidadDAO.buscarEntidadPorIdentificacion(identificacion);
			
			if (!identificacion.equals(""))
				entidad.setIdentificacion(identificacion);
			
			if (!codigoEnsurance.equals(""))
				entidad.setEntEnsurance(codigoEnsurance);
			
			if (!tipoIdentificacionId.equals("")){
				TipoIdentificacionDAO tiDAO = new TipoIdentificacionDAO(); 
				entidad.setTipoIdentificacion(tiDAO.buscarPorId(tipoIdentificacionId) ); 
				TipoEntidadDAO tipoEntidadDAO = new TipoEntidadDAO();				
				if(tipoIdentificacionId.equalsIgnoreCase("4"))
					entidad.setTipoEntidad(tipoEntidadDAO.buscarPorId("2"));
				else					
					entidad.setTipoEntidad(tipoEntidadDAO.buscarPorId("1"));
				}

			if (!nombre.equals(""))
				entidad.setNombres(nombre);
			
			if (!apellido.equals(""))
				entidad.setApellidos(apellido);
			
			if (!nombre.equals("")&&!apellido.equals(""))
				entidad.setNombreCompleto(apellido+" "+nombre);
			
			if (!mail.equals(""))
				entidad.setMail(mail);
			
			if (!cargoId.equals("")){
				CargoDAO cargoDAO = new CargoDAO(); 
				empleado.setCargo(cargoDAO.buscarPorId(cargoId) ); 
				}
			
			if (!sucursalId.equals("")){
				SucursalDAO sucursalDAO = new SucursalDAO(); 
				empleado.setSucursal(sucursalDAO.buscarPorId(sucursalId) ); 
				}
			
			if (!codigo.equals(""))
				empleado.setId(codigo);
			
//			if (!firma.equals(""))
//				empleado.setFirma(firma);
			
			if (activo.equals("1"))
				empleado.setActivo(true);
			else 
				empleado.setActivo(false);
			
			//if(tipoConsulta.equals("eliminar"))
			//	empleado.setActivo(false);

			if (tipoConsulta.equals("encontrarTodos")) {
				boolean activoF =  activoFiltro.equals("1") ? true : false;
				List<Empleado> results = empleadoDAO.buscarPorFiltros(tipoIdentificacionFiltro, identificacionFiltro, nombreFiltro, apellidoFiltro, ensuranceFiltro, cargoFiltro, sucursalFiltro, activoF);
				int i = 0;
				for (i = 0; i < results.size(); i++) {
					empleado = results.get(i);
					empleadoJSONObject.put("codigo", empleado.getId());
					empleadoJSONObject.put("codigoEnsurance", empleado.getEntidad().getEntEnsurance()==null?"":empleado.getEntidad().getEntEnsurance());
					empleadoJSONObject.put("identificacion", empleado.getEntidad().getIdentificacion());
					empleadoJSONObject.put("tipoIdentificacion", empleado.getEntidad().getTipoIdentificacion().getNombre());
					empleadoJSONObject.put("nombre", empleado.getEntidad().getNombres());
					empleadoJSONObject.put("apellido", empleado.getEntidad().getApellidos());
					empleadoJSONObject.put("cargo", empleado.getCargo().getNombre());
					empleadoJSONObject.put("firma", empleado.getFirma());
					empleadoJSONObject.put("mail", empleado.getEntidad().getMail());
					empleadoJSONObject.put("sucursal", empleado.getSucursal()==null?"":empleado.getSucursal().getNombre());
					if (empleado.getActivo())
						empleadoJSONObject.put("activo", "Si");
					else
						empleadoJSONObject.put("activo", "No");

					empleadoJSONArray.add(empleadoJSONObject);
				}
				result.put("numRegistros", i);
				result.put("listadoEmpleado", empleadoJSONArray);

				

			}
			
			if (tipoConsulta.equals("buscarFiltro")) {
				int i = 0;
				TipoIdentificacionDAO tipoIndentificacionDAO = new TipoIdentificacionDAO();
				TipoIdentificacion tipoIdentificacion = new TipoIdentificacion();

				JSONObject tipoIdentificacionJSONObject = new JSONObject();
				JSONArray tipoIdentificacionJSONArray = new JSONArray();

				List<TipoIdentificacion> listadoTipoIdentificacion = tipoIndentificacionDAO
						.buscarTodos();

				for (i = 0; i < listadoTipoIdentificacion.size(); i++) {
					tipoIdentificacion = listadoTipoIdentificacion.get(i);
					tipoIdentificacionJSONObject.put("codigo",
							tipoIdentificacion.getId());
					tipoIdentificacionJSONObject.put("nombre",
							tipoIdentificacion.getNombre());

					tipoIdentificacionJSONArray
							.add(tipoIdentificacionJSONObject);
				}

				result.put("listadoTipoIdentificacion",
						tipoIdentificacionJSONArray);
				
				
				CargoDAO cargoDAO = new CargoDAO();
				Cargo cargo = new Cargo();

				JSONObject cargoJSONObject = new JSONObject();
				JSONArray cargoJSONArray = new JSONArray();

				List<Cargo> listadoCargo = cargoDAO
						.buscarTodos();

				for (i = 0; i < listadoCargo.size(); i++) {
					cargo = listadoCargo.get(i);
					cargoJSONObject.put("codigo",cargo.getId());
					cargoJSONObject.put("nombre",cargo.getNombre());

					cargoJSONArray.add(cargoJSONObject);
				}

				result.put("listadoCargo",cargoJSONArray);

				SucursalDAO sucursalDAO = new SucursalDAO();
				Sucursal sucursal = new Sucursal();

				JSONObject sucursalJSONObject = new JSONObject();
				JSONArray sucursalJSONArray = new JSONArray();

				List<Sucursal> listadoSucursal = sucursalDAO.buscarActivos();

				for (i = 0; i < listadoSucursal.size(); i++) {
					sucursal = listadoSucursal.get(i);
					sucursalJSONObject.put("codigo",sucursal.getId());
					sucursalJSONObject.put("nombre",sucursal.getNombre());

					sucursalJSONArray.add(sucursalJSONObject);
				}

				result.put("listadoSucursal",sucursalJSONArray);
			}
			
			
			if(tipoConsulta.equals("cambioEstado")){
				empleado = empleadoDAO.buscarPorId(codigo);
				if (activo.equals("1")){
					empleado.setActivo(false);
				}else if (activo.equals("0")){
					empleado.setActivo(true);						
				}
				empleadoTransaction.editar(empleado);
				
			}

			
			// Encontramos los empleados por cargo en base al nombre generico
			if(tipoConsulta.equals("empleadoPorCargo")){
				Cargo cargo = new Cargo();
				CargoDAO cargoDAO = new CargoDAO();
				List<Cargo> listado = cargoDAO.buscarPorCargoGenerico(cargoGenerico);
				if(listado.size() > 0) {
					for(int i=0; i<listado.size(); i++) {
						cargo = (Cargo) listado.get(i);
						List<Empleado> listaEmpleado = empleadoDAO.buscarPorCargo(cargo);
						if (listaEmpleado.size() > 0){
							for(int j=0; j<listaEmpleado.size(); j++){
								empleado = listaEmpleado.get(j);
								empleadoJSONObject.put("codigoEmpleado", empleado.getId());
								empleadoJSONObject.put("codigoEntidad", empleado.getEntidad().getId());
								empleadoJSONObject.put("nombre", empleado.getEntidad().getNombreCompleto());
								empleadoJSONObject.put("mail", empleado.getEntidad().getMail());
								
								empleadoJSONArray.add(empleadoJSONObject);
							}							
						}
					}
				}
				result.put("listadoEmpleadosCargo",empleadoJSONArray);
			}
			
			
			
			if (tipoConsulta.equals("crear")) {
				if(entidad.getId()==null)
					entidad=entidadTransaction.crear(entidad);
				
				empleado.setEntidad(entidad);
				
				empleadoTransaction.crear(empleado);

			}

			if (tipoConsulta.equals("eliminar")) {
				empleado=empleadoDAO.buscarPorId(codigo);
				empleadoTransaction.eliminar(empleado);
			}

			if (tipoConsulta.equals("actualizar")) {
				entidad = entidadTransaction.editar(entidad);
				empleado.setEntidad(entidad);
				
				if(empleado.getId()!=null)
					empleadoTransaction.editar(empleado);
				else
					empleadoTransaction.crear(empleado);
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
