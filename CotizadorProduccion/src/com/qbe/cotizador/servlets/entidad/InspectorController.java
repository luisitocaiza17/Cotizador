package com.qbe.cotizador.servlets.entidad;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.qbe.cotizador.dao.inspeccion.InspectorDAO;
import com.qbe.cotizador.dao.entidad.SucursalDAO;
import com.qbe.cotizador.dao.entidad.TipoInspectorDAO;
import com.qbe.cotizador.dao.entidad.UsuarioDAO;
import com.qbe.cotizador.model.Inspector;
import com.qbe.cotizador.model.Sucursal;
import com.qbe.cotizador.model.TipoInspector;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.transaction.entidad.InspectorTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class Inspector
 */
@WebServlet("/InspectorController")
public class InspectorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InspectorController() {
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
			String tipoInspectorId = request.getParameter("tipoInspector") == null ? "" : request.getParameter("tipoInspector");
			String nombre = request.getParameter("nombre") == null ? "" : request.getParameter("nombre");
			String valorKM = request.getParameter("valorKM") == null ? "" : request.getParameter("valorKM");			
		    String mail1 = request.getParameter("mail1") == null ? "" : request.getParameter("mail1");
		    String mail2 = request.getParameter("mail2") == null ? "" : request.getParameter("mail2");
		    String mail3 = request.getParameter("mail3") == null ? "" : request.getParameter("mail3");	    
		    String sucursalId = request.getParameter("sucursal") == null ? "" : request.getParameter("sucursal");
			String usuarioId = request.getParameter("usuario") == null ? "" : request.getParameter("usuario");
			
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
						
			InspectorTransaction inspectorTransaction = new InspectorTransaction(); 
			
			JSONObject inspectorJSONObject = new JSONObject();
			JSONArray inspectorJSONArray = new JSONArray();

			Inspector inspector = new Inspector();
			InspectorDAO inspectorDAO = new InspectorDAO();			
			
			if (!codigo.equals(""))
				inspector.setId(codigo);
			
			if (!tipoInspectorId.equals("")){
				TipoInspectorDAO tiDAO = new TipoInspectorDAO(); 
				inspector.setTipoInspector(tiDAO.buscarPorId(tipoInspectorId)); 
				}

			if (!nombre.equals(""))
				inspector.setNombre(nombre);
			
			if (!valorKM.equals(""))
				inspector.setValorKm(Double.parseDouble(valorKM));			
			
			if (!mail1.equals("")){
				inspector.setMail1(mail1);
			}
			if (!mail2.equals("")){
				inspector.setMail2(mail2);
			}
			if (!mail3.equals("")){
				inspector.setMail3(mail3);
			}
	
			if (!sucursalId.equals("")){
				SucursalDAO sucursalDAO = new SucursalDAO(); 
				inspector.setSucursal(sucursalDAO.buscarPorId(sucursalId)); 
				}
		
			if (!usuarioId.equals("")){
				UsuarioDAO usuarioDAO = new UsuarioDAO(); 
				inspector.setUsuario((usuarioDAO.buscarPorId(usuarioId))); 
				}			

			if (tipoConsulta.equals("encontrarTodos")) {
				List<Inspector> results = inspectorDAO.buscarTodos();
				int i = 0;
				for (i = 0; i < results.size(); i++) {
					inspector = results.get(i);
					inspectorJSONObject.put("codigo", inspector.getId());
					inspectorJSONObject.put("tipoInspector", inspector.getTipoInspector()==null?"":inspector.getTipoInspector().getNombre());
					inspectorJSONObject.put("nombre", inspector.getNombre());
					inspectorJSONObject.put("valorKM", inspector.getValorKm());
					inspectorJSONObject.put("mail1", inspector.getMail1());
					inspectorJSONObject.put("mail2", inspector.getMail2() == null ? "" : inspector.getMail2());
					inspectorJSONObject.put("mail3", inspector.getMail3() == null ? "" : inspector.getMail3());
					inspectorJSONObject.put("sucursal", inspector.getSucursal()==null?"":inspector.getSucursal().getNombre());
					inspectorJSONObject.put("usuario", inspector.getUsuario()==null?"":inspector.getUsuario().getLogin());

					inspectorJSONArray.add(inspectorJSONObject);
				}
				result.put("numRegistros", i);
				result.put("listadoInspector", inspectorJSONArray);

				TipoInspectorDAO tipoInspectorDAO = new TipoInspectorDAO();
				TipoInspector tipoInspector = new TipoInspector();

				JSONObject tipoInspectorJSONObject = new JSONObject();
				JSONArray tipoInspectorJSONArray = new JSONArray();

				List<TipoInspector> listadoTipoInspector = tipoInspectorDAO
						.buscarTodos();

				for (i = 0; i < listadoTipoInspector.size(); i++) {
					tipoInspector = listadoTipoInspector.get(i);
					tipoInspectorJSONObject.put("codigo",
							tipoInspector.getId());
					tipoInspectorJSONObject.put("nombre",
							tipoInspector.getNombre());

					tipoInspectorJSONArray
							.add(tipoInspectorJSONObject);
				}

				result.put("listadoTipoInspector",
						tipoInspectorJSONArray);
				
				
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				Usuario usuario = new Usuario();

				JSONObject usuarioJSONObject = new JSONObject();
				JSONArray usuarioJSONArray = new JSONArray();

				List<Usuario> listadoUsuario = usuarioDAO
						.buscarTodos();

				for (i = 0; i < listadoUsuario.size(); i++) {
					usuario = listadoUsuario.get(i);
					usuarioJSONObject.put("codigo",usuario.getId());
					usuarioJSONObject.put("nombre",usuario.getLogin());

					usuarioJSONArray.add(usuarioJSONObject);
				}

				result.put("listadoUsuario",usuarioJSONArray);

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
			
			if (tipoConsulta.equals("crear")) {
				inspectorTransaction.crear(inspector);
			}

			if (tipoConsulta.equals("eliminar")) {
				inspector=inspectorDAO.buscarPorId(codigo);
				inspectorTransaction.eliminar(inspector);
			}

			if (tipoConsulta.equals("actualizar")) {				
				if(inspector.getId()!=null)
					inspectorTransaction.editar(inspector);
				else
					inspectorTransaction.crear(inspector);
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
