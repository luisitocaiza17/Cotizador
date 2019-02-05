package com.qbe.cotizador.servlets.producto.pymes;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qbe.cotizador.dao.entidad.CantonDAO;
import com.qbe.cotizador.dao.entidad.CiudadDAO;
import com.qbe.cotizador.dao.entidad.EmpleadoDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.entidad.UsuarioDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeInspectorProvinciaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeInspectorProvinciaUsuarioDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeUsuarioInspectorDAO;
import com.qbe.cotizador.dao.seguridad.RolDAO;
import com.qbe.cotizador.model.Canton;
import com.qbe.cotizador.model.Ciudad;
import com.qbe.cotizador.model.Empleado;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Provincia;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.PymeInspectorProvincia;
import com.qbe.cotizador.model.PymeInspectorProvinciaUsuario;
import com.qbe.cotizador.model.PymeUsuarioInspector;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeInspectorProvinciaTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * Servlet implementation class PymeInspectorProvinciaController
 */
@WebServlet("/PymeInspectorProvinciaController")
public class PymeInspectorProvinciaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PymeInspectorProvinciaController() {
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
		JSONObject result =  new JSONObject();
		try {
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "": request.getParameter("tipoConsulta");
			String usuarioId = request.getParameter("usuarioId") == null ? "": request.getParameter("usuarioId");
			String entidadId = request.getParameter("entidadId") == null ? "": request.getParameter("entidadId");
			String provinciaId = request.getParameter("provinciaId") == null ? "": request.getParameter("provinciaId");
			String ciudadId = request.getParameter("ciudadId") == null ? "": request.getParameter("ciudadId");
			String configuracionArray = request.getParameter("configuracionArray") == null ? "": request.getParameter("configuracionArray");
			/*Objetos bdd*/			
			Entidad entidad = new Entidad();
			EntidadDAO entidadDAO = new EntidadDAO();			
			
			Usuario usuario = new Usuario();
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			
			PymeInspectorProvincia pymeInspectorProvincia = new PymeInspectorProvincia();
			PymeInspectorProvinciaDAO pymeInspectorProvinciaDAO = new PymeInspectorProvinciaDAO();
			PymeInspectorProvinciaTransaction pymeTransaction = new PymeInspectorProvinciaTransaction();
			
			Provincia provincia = new Provincia();
			ProvinciaDAO provinciaDAO = new ProvinciaDAO();
			
			Canton canton = new Canton();
			CantonDAO cantonDAO = new CantonDAO();
			
			Empleado empleado = new Empleado();
			EmpleadoDAO empleadoDAO = new EmpleadoDAO();
			
			/*Objetos bdd*/
			
			/*Objetos Json*/
			JSONObject datosEntidadJsonObject = new JSONObject();
			JSONArray datosEntidadJsonArray = new JSONArray();
			
			JSONObject entidadJsonObject = new JSONObject();
			JSONArray entidadJsonArray = new JSONArray();
			
			JSONObject pInspectorProvinciaJsonObject = new JSONObject();
			JSONArray pInspectorProvinciaJsonArray = new JSONArray();
			
			JSONObject provinciaJsonObject = new JSONObject();
			JSONArray provinciaJsonArray = new JSONArray();
			
			JSONObject ciudadJsonObject = new JSONObject();
			JSONArray ciudadJsonArray = new JSONArray();
			/*Objetos Json*/
			
			
			if(tipoConsulta.equals("buscarTodos")){
				
				/*List<Empleado> empleadoList = empleadoDAO.buscarActivos();
				for(Empleado empleadoList2 : empleadoList){
					entidadJsonObject.put("entidadId",empleadoList2.getEntidad().getId());
					entidadJsonObject.put("nombre",empleadoList2.getEntidad().getNombreCompleto());					
					entidadJsonArray.add(entidadJsonObject);
				}				
				result.put("listaEntidad", entidadJsonArray);*/
				PymeUsuarioInspectorDAO rolDAO=new PymeUsuarioInspectorDAO();
				List<PymeUsuarioInspector> roles=rolDAO.buscarPorRolId(new BigInteger("5"));
				
				for(PymeUsuarioInspector empleadoList2 : roles){
					entidadJsonObject.put("usuarioId",empleadoList2.getUsuarioid());
					entidadJsonObject.put("nombre",empleadoList2.getNombreCompleto());					
					entidadJsonArray.add(entidadJsonObject);
				}				
				result.put("listaEntidad", entidadJsonArray);
				
				List<Provincia> resultProvincia = provinciaDAO.buscarTodos();
				for(Provincia listProvincia: resultProvincia){
					provinciaJsonObject.put("provinciaId", listProvincia.getId());
					provinciaJsonObject.put("provinciaNombre", listProvincia.getNombre());
					provinciaJsonArray.add(provinciaJsonObject);
				}
				result.put("listaProvincia", provinciaJsonArray);
				
				
				PymeInspectorProvinciaUsuarioDAO inspectorProvUsuarioDAO=new PymeInspectorProvinciaUsuarioDAO();
				List<PymeInspectorProvinciaUsuario> usuarioList = inspectorProvUsuarioDAO.buscarTodos();
				for(PymeInspectorProvinciaUsuario list : usuarioList){
					datosEntidadJsonObject.put("usuarioId",list.getUsuarioId());
					datosEntidadJsonObject.put("entidadId",list.getEntidadId());
					datosEntidadJsonObject.put("nombreCompleto",list.getNombreCompleto());
					datosEntidadJsonObject.put("login",list.getLogin());

					datosEntidadJsonArray.add(datosEntidadJsonObject);
					
					/*List<PymeInspectorProvincia> pymeInspectorProvinciaList = pymeInspectorProvinciaDAO.buscarUsuarioId(new BigInteger(list.getId()));		
					if(pymeInspectorProvinciaList.size() >= 1){
						entidad = entidadDAO.buscarPorId(list.getEntidad().getId());
						datosEntidadJsonObject.put("entidadId",entidad.getId());
						datosEntidadJsonObject.put("ensuranceId",entidad.getEntEnsurance());
						datosEntidadJsonObject.put("nombreCompleto",entidad.getNombreCompleto());
						datosEntidadJsonObject.put("login",list.getLogin());
						datosEntidadJsonObject.put("usuarioId",list.getId());
						
					}				*/	
				}
				result.put("listaInspector", datosEntidadJsonArray);	
				
			}
			
			if(tipoConsulta.equals("buscarPorId")){
				if(!entidadId.equals("")){
					
					//conocer el Usuario
					// /TODO: obtener usuario
					HttpSession session = request.getSession(true);
					Object login=session.getAttribute("login");
					
					String loginUsuario=login.toString();
					String nombreUsuario=loginUsuario;
					
					
					//lcaiza Proceso de Auditoria 
					PymeAuditoriaGeneral pymeAuditoriaGeneral = new PymeAuditoriaGeneral();
					pymeAuditoriaGeneral.setProceso("Consulta Inspector");
					
					EntidadDAO entidadDAO2 = new EntidadDAO();
					Entidad entidad2= entidadDAO2.buscarPorId(entidadId);
					
					
					String Objeto=" Usuario Consulta: " +nombreUsuario; 
					
					PymeUsuarioInspectorDAO  pymeUsuarioInspectorDAO = new PymeUsuarioInspectorDAO();
					PymeUsuarioInspector  pymeUsuarioInspector = pymeUsuarioInspectorDAO.buscarPorUsuarioId(new BigInteger(entidadId));
					
					
					Objeto=Objeto+ " Inspector: "+pymeUsuarioInspector.getNombreCompleto();
					
					
					result.put("entidadId",entidadId);
					
					List<PymeInspectorProvincia> pymeInspectorProvinciaList = pymeInspectorProvinciaDAO.buscarUsuarioId(new BigInteger(entidadId));
					if(pymeInspectorProvinciaList.size() >=1 ){					
						for(PymeInspectorProvincia pIPList : pymeInspectorProvinciaList){
							provincia = provinciaDAO.buscarPorId(pIPList.getProvinciaId().toString());
							pInspectorProvinciaJsonObject.put("provinciaId", provincia.getId());
							pInspectorProvinciaJsonObject.put("proviniciaNombre", provincia.getNombre());
							canton = cantonDAO.buscarPorId(pIPList.getCiudadId().toString());
							pInspectorProvinciaJsonObject.put("ciudadId", canton.getId());
							pInspectorProvinciaJsonObject.put("ciudadNombre", canton.getNombre());
							pInspectorProvinciaJsonArray.add(pInspectorProvinciaJsonObject);	
							
							//Auditoria
							Objeto=Objeto+ "||provinicia:"+ provincia.getNombre();
							Objeto=Objeto+ " ciudad:"+canton.getNombre();
							
						}
												
					}else{
						Objeto="no existe configuracion";
					}
					
					pymeAuditoriaGeneral.setObjeto(Objeto);
					pymeAuditoriaGeneral.setEstado("Correcto");
					
					PymeAuditoriaGeneralTransaction pymeAuditoriaGeneralTransaction = new PymeAuditoriaGeneralTransaction();
					pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
					
				}
				result.put("detalleInspector", pInspectorProvinciaJsonArray);
			}
			
			if(tipoConsulta.equals("buscarPorProvincia")){
				provincia = provinciaDAO.buscarPorId(provinciaId);
				List<Canton> ciudadList = cantonDAO.buscarPorProvincia(provincia);
				if(ciudadList.size() >=1 ){					
					for(Canton ciudadArg : ciudadList){
						ciudadJsonObject.put("ciudadId", ciudadArg.getId());
						ciudadJsonObject.put("nombre", ciudadArg.getNombre());
						ciudadJsonArray.add(ciudadJsonObject);
					}
				}
				result.put("listaCiudad", ciudadJsonArray);
			}
			
			
			if(tipoConsulta.equals("crear")){
				//entidad = entidadDAO.buscarPorId(entidadId);
				List<PymeInspectorProvincia> pymeInspectorProvinciaList = pymeInspectorProvinciaDAO.buscarUsuarioId(new BigInteger(entidadId));
				if(pymeInspectorProvinciaList.size() >=1 ){					
					for(PymeInspectorProvincia pIPList : pymeInspectorProvinciaList){
						pymeTransaction.eliminar(pIPList);
					}					
				}
				JSONArray array = (JSONArray)JSONSerializer.toJSON(configuracionArray);
				
				//Auditoria de creacion
				//conocer el Usuario
				// /TODO: obtener usuario
				HttpSession session = request.getSession(true);
				Object login=session.getAttribute("login");
				
				String loginUsuario=login.toString();
				String nombreUsuario=loginUsuario;
				
				
				//lcaiza Proceso de Auditoria 
				PymeAuditoriaGeneral pymeAuditoriaGeneral = new PymeAuditoriaGeneral();
				pymeAuditoriaGeneral.setProceso("Creacion InspectorProvincia");
				
				String Objeto=" Usuario Consulta: " +nombreUsuario; 
				
				PymeUsuarioInspectorDAO  pymeUsuarioInspectorDAO = new PymeUsuarioInspectorDAO();
				PymeUsuarioInspector  pymeUsuarioInspector = pymeUsuarioInspectorDAO.buscarPorUsuarioId(new BigInteger(entidadId));
				
				Objeto=Objeto+ " Inspector: "+pymeUsuarioInspector.getNombreCompleto();
				
				//por cada objeto JSon CREADO ..
                for (Object js:array){
                	JSONObject jsonStr=(JSONObject)JSONSerializer.toJSON(js);
                	pymeInspectorProvincia.setUsuarioId(new BigInteger(entidadId));
    				pymeInspectorProvincia.setProvinciaId(new BigInteger(jsonStr.getString("provinciaId")));
    				pymeInspectorProvincia.setCiudadId(new BigInteger(jsonStr.getString("ciudadId")));
    				
    				pymeTransaction.crear(pymeInspectorProvincia);
    				ProvinciaDAO provinciaDAO2= new ProvinciaDAO();
    				Provincia provincia2=provinciaDAO2.buscarPorId(jsonStr.getString("provinciaId"));
    				
    				CiudadDAO ciudadDAO = new CiudadDAO();
    				Ciudad ciudad= ciudadDAO.buscarPorId(jsonStr.getString("ciudadId"));
    				
    				
    				Objeto= Objeto+" provincia:"+provincia2.getNombre()+ " ciudad:"+ciudad.getNombre();
				}
                pymeAuditoriaGeneral.setObjeto(Objeto);
				pymeAuditoriaGeneral.setEstado("Correcto");
				
				PymeAuditoriaGeneralTransaction pymeAuditoriaGeneralTransaction = new PymeAuditoriaGeneralTransaction();
				pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
			}
			
			if(tipoConsulta.equals("eliminar")){
				List<PymeInspectorProvincia> pymeInspectorProvinciaList = pymeInspectorProvinciaDAO.buscarUsuarioId(new BigInteger(entidadId));
				
				//Auditoria de creacion
				//conocer el Usuario
				// /TODO: obtener usuario
				HttpSession session = request.getSession(true);
				Object login=session.getAttribute("login");
				
				String loginUsuario=login.toString();
				String nombreUsuario=loginUsuario;
				//lcaiza Proceso de Auditoria 
				PymeAuditoriaGeneral pymeAuditoriaGeneral = new PymeAuditoriaGeneral();
				pymeAuditoriaGeneral.setProceso("Eliminacion InspectorProvincia");
				
				String Objeto=" Usuario elimina: " +nombreUsuario;
				
				PymeUsuarioInspectorDAO  pymeUsuarioInspectorDAO = new PymeUsuarioInspectorDAO();
				PymeUsuarioInspector  pymeUsuarioInspector = pymeUsuarioInspectorDAO.buscarPorUsuarioId(new BigInteger(entidadId));
				
				Objeto=Objeto+ " Inspector: "+pymeUsuarioInspector.getNombreCompleto();		
				
				if(pymeInspectorProvinciaList.size() >=1 ){					
					for(PymeInspectorProvincia pIPList : pymeInspectorProvinciaList){
						pymeTransaction.eliminar(pIPList);
						
						ProvinciaDAO provinciaDAO2= new ProvinciaDAO();
	    				Provincia provincia2=provinciaDAO2.buscarPorId(""+pIPList.getProvinciaId());
	    				
	    				CiudadDAO ciudadDAO = new CiudadDAO();
	    				Ciudad ciudad= ciudadDAO.buscarPorId(""+pIPList.getCiudadId());
						
						Objeto=Objeto+" Provincia: "+provincia2.getNombre()+" CiudadId:"+ciudad.getNombre()+" InspectorProvinciaId:"+pIPList.getInspectorProvinciaId()+" UsuarioId:"+pIPList.getUsuarioId();
					}					
				}
				pymeAuditoriaGeneral.setObjeto(Objeto);
				pymeAuditoriaGeneral.setEstado("Correcto");
				
				PymeAuditoriaGeneralTransaction pymeAuditoriaGeneralTransaction = new PymeAuditoriaGeneralTransaction();
				pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
				
			}
				
			
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			
		}catch(Exception e){
			/***TRATAMIENTO DE ERROR***/
			Date codigo = new Date();//EL codigo de error se conforma por los valores de la fecha
			String CodError=codigo.getYear()+""+codigo.getMonth()+""+codigo.getDay()+""+codigo.getHours()+""+codigo.getMinutes()+"-"+codigo.getSeconds();
			/***AUDITORIA auditamos el error para el seguimiento***/
			PymeAuditoriaGeneralTransaction pymeAuditoriaGeneralTransaction = new PymeAuditoriaGeneralTransaction();
			PymeAuditoriaGeneral pymeAuditoriaGeneral = new PymeAuditoriaGeneral();
			pymeAuditoriaGeneral.setTramite(CodError);
			pymeAuditoriaGeneral.setEstado("ERROR_PYMES");
			pymeAuditoriaGeneral.setProceso("PYMES");
			pymeAuditoriaGeneral.setObjeto(e.getMessage()+"||"+e.getCause());
			try {
				pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
			} catch (Exception e1) {				
				e1.printStackTrace();
			}
			/***RESPUESTA A INTERFAZ***/
			result.put("success", Boolean.FALSE);
			result.put("error", "Error en Servidor, refiérase para soporte con el siguiente código: "+CodError);
			result.put("ex", e.getMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			e.printStackTrace();	
		}
	}
}