package com.qbe.cotizador.servlets.seguridad;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.qbe.cotizador.dao.seguridad.OpcionMenuDAO;
import com.qbe.cotizador.dao.seguridad.OpcionMenuRolDAO;
import com.qbe.cotizador.dao.seguridad.RolDAO;
import com.qbe.cotizador.dao.seguridad.TipoRolModuloDAO;
import com.qbe.cotizador.model.OpcionMenu;
import com.qbe.cotizador.model.OpcionMenuRol;
import com.qbe.cotizador.model.Rol;
import com.qbe.cotizador.model.TipoRolModulo;
import com.qbe.cotizador.transaction.seguridad.OpcionMenuRolTransaction;

@WebServlet("/AdministracionMenuRolController")
public class AdministracionMenuRolController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONObject result = new JSONObject();
		try{
			
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String rol = request.getParameter("rol") == null ? "" : request.getParameter("rol");
			String padre = request.getParameter("padre") == null ? "" :request.getParameter("padre");
			String [] listaCheck = request.getParameterValues("listaCheck[]");
			String [] listaNoCheck = request.getParameterValues("listaNoCheck[]");
			JSONObject RolJSONObject = new JSONObject();
			JSONArray RolJSONArray = new JSONArray();
			JSONObject OpcionMenuJSONObject = new JSONObject();
			JSONArray OpcionMenuJSONArray = new JSONArray();
			JSONObject OpcionMenuHijosJSONObject = new JSONObject();
			JSONArray OpcionMenuHijosJSONArray = new JSONArray();
			JSONObject OpcionMenuHijosTodosJSONObject = new JSONObject();
			JSONArray OpcionMenuHijosTodosJSONArray = new JSONArray();
			
			
			RolDAO rolDAO = new RolDAO();
			OpcionMenuDAO opcionMenuDAO = new OpcionMenuDAO();
			OpcionMenuRolDAO opcionMenuRolDAO = new OpcionMenuRolDAO();
			OpcionMenuRolTransaction opcionMenuTransaction = new OpcionMenuRolTransaction();

			if(tipoConsulta.equals("encontrarRol")){ 
				List<Rol> listaRol = rolDAO.buscarTodosActivos();
				for(Rol r : listaRol){
					RolJSONObject.put("codigo", r.getId());
					RolJSONObject.put("nombre", r.getNombre());

					RolJSONArray.add(RolJSONObject);
				}
				result.put("numRegistros",listaRol.size());
				result.put("listadoRol", RolJSONArray);
			}
			
			if(tipoConsulta.equals("encontrarOpcionMenu"))
			{
				List<OpcionMenu> listaOpcion = opcionMenuDAO.buscarPadres();
				for(OpcionMenu om : listaOpcion)
				{
					OpcionMenuRol omr = opcionMenuRolDAO.buscarXRolXOpcion(rol, om.getId());
					OpcionMenuJSONObject.put("codigo", om.getId());
					OpcionMenuJSONObject.put("nombre", om.getNombre());
					OpcionMenuJSONObject.put("check", omr == null ? false : true);
					OpcionMenuJSONArray.add(OpcionMenuJSONObject);
				}
				result.put("listadoOpcion", OpcionMenuJSONArray);
				
				List<OpcionMenu> listaOpcionHijosTodos = opcionMenuDAO.buscarHijos();
				for(OpcionMenu om : listaOpcionHijosTodos)
				{
					OpcionMenuRol omr = opcionMenuRolDAO.buscarXRolXOpcion(rol, om.getId());
					OpcionMenuHijosTodosJSONObject.put("codigo", om.getId());
					OpcionMenuHijosTodosJSONObject.put("nombre", om.getNombre());
					OpcionMenuHijosTodosJSONObject.put("check", omr == null ? false : true);
					OpcionMenuHijosTodosJSONArray.add(OpcionMenuHijosTodosJSONObject);
				}
				result.put("listadoTodosHijos", OpcionMenuHijosTodosJSONArray);
			}
			
			if(tipoConsulta.equals("encontrarHijos"))
			{
				List<OpcionMenu> listaHijos = opcionMenuDAO.buscarHijos(padre);
				for(OpcionMenu om : listaHijos)
				{
					OpcionMenuRol omr = opcionMenuRolDAO.buscarXRolXOpcion(rol, om.getId());
					OpcionMenuHijosJSONObject.put("codigo", om.getId());
					OpcionMenuHijosJSONObject.put("nombre", om.getNombre());
					OpcionMenuHijosJSONObject.put("check", omr == null ? false : true);
					OpcionMenuHijosJSONArray.add(OpcionMenuHijosJSONObject);
				}
				result.put("listadoHijos", OpcionMenuHijosJSONArray);
			}
			
			if(tipoConsulta.equals("guardar"))
			{
				Rol r = rolDAO.buscarPorId(rol);
				TipoRolModuloDAO moduloDAO = new TipoRolModuloDAO();
				TipoRolModulo modulo = moduloDAO.buscarPorId("1");
				if(listaCheck != null)
					for(String id : listaCheck)
					{
						OpcionMenu om = opcionMenuDAO.buscarXId(id);
						OpcionMenuRol opcionMenuRol = new OpcionMenuRol();
						opcionMenuRol = opcionMenuRolDAO.buscarXRolXOpcion(rol, id);
						if(opcionMenuRol == null)
						{
							opcionMenuRol = new OpcionMenuRol();
							opcionMenuRol.setOpcionMenu(om);
							opcionMenuRol.setRol(r);
							opcionMenuRol.setTipoRolModulo(modulo);
							opcionMenuTransaction.crear(opcionMenuRol);
						}
						
					}
				if(listaNoCheck != null)
					for(String id : listaNoCheck)
					{
						OpcionMenuRol opcionMenuRol = new OpcionMenuRol();
						opcionMenuRol = opcionMenuRolDAO.buscarXRolXOpcion(rol, id);
						if(opcionMenuRol != null)
						{
							opcionMenuTransaction.eliminar(opcionMenuRol);
						}
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

		}		
	}

}
