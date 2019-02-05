package com.qbe.cotizador.servlets.producto.agricola;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.UsuarioDAO;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.transaction.entidad.EntidadTransaction;

/**
 * Servlet implementation class AgriClienteController
 */
@WebServlet("/AgriClienteController")
public class AgriClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriClienteController() {
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
		try {
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "":request.getParameter("tipoConsulta");
			
			
			
			if(tipoConsulta.equals("actualizar")){
				String identificacion = request.getParameter("identificacion") == null ? "":request.getParameter("identificacion");
				String nombre = request.getParameter("nombre") == null ? "":request.getParameter("nombre");
				String apellido = request.getParameter("apellido") == null ? "":request.getParameter("apellido");
				String nombreCompleto = request.getParameter("nombreCompleto") == null ? "":request.getParameter("nombreCompleto");
				
				EntidadDAO entidadDAO = new EntidadDAO();
				Entidad entidad = entidadDAO.buscarEntidadPorIdentificacion(identificacion);
				
				if(!nombre.equals(""))
					entidad.setNombres(nombre);
				if(!apellido.equals(""))
					entidad.setApellidos(apellido);
				if(!nombreCompleto.equals(""))
					entidad.setNombreCompleto(nombreCompleto);
				
				EntidadTransaction entidadTransaction = new EntidadTransaction();
				entidadTransaction.editar(entidad);
				
				result.put("success", Boolean.TRUE);
				response.setContentType("application/json; charset=ISO-8859-1");
				result.write(response.getWriter());
				
			}
			
			if(tipoConsulta.equals("encontrar")){
				String identificacion = request.getParameter("identificaion") == null ? "":request.getParameter("identificaion");
				String cliente = request.getParameter("id") == null ? "":request.getParameter("id");
				if(!identificacion.equals("")||!cliente.equals("")){
					List<AgriCliente> listaCliente = new ArrayList<AgriCliente>();
					
					AgriCliente agriCliente = new AgriCliente();
					if(!identificacion.equals("")){
						
						EntidadDAO entidadDAO = new EntidadDAO();
						Entidad entidad = entidadDAO.buscarEntidadPorIdentificacion(identificacion);
						ClienteDAO clienteDAO = new ClienteDAO();
						Cliente cliente2 = clienteDAO.buscarPorEntidadId(entidad);
						
						agriCliente.setEntidadId(entidad.getId());
						agriCliente.setIdentificacion(entidad.getIdentificacion());
						agriCliente.setNombre(entidad.getNombres());
						agriCliente.setApellido(entidad.getApellidos());
						agriCliente.setNombresCompletos(entidad.getNombreCompleto());
						agriCliente.setClienteId(cliente2.getId());
						
						
					}else{
						ClienteDAO clienteDAO = new ClienteDAO();
						Cliente cliente2 = clienteDAO.buscarPorId(cliente);
						
						agriCliente.setEntidadId(cliente2.getEntidad().getId());
						agriCliente.setIdentificacion(cliente2.getEntidad().getIdentificacion());
						agriCliente.setNombre(cliente2.getEntidad().getNombres());
						agriCliente.setApellido(cliente2.getEntidad().getApellidos());
						agriCliente.setNombresCompletos(cliente2.getEntidad().getNombreCompleto());
						agriCliente.setClienteId(cliente2.getId());
						
					}
					
					listaCliente.add(agriCliente);
					
					DataSourceResult pg = new DataSourceResult();
					pg.setTotal(1);
					pg.setData(listaCliente);
					
					
					//Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
					Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy HH:mm:ss").create();
					// convert the DataSourceReslt to JSON and write it to the response
					response.setContentType("application/json; charset=ISO-8859-1");
				    response.getWriter().print(gson.toJson(pg));
				    return;		
				}
			}
			
			
			
			
		}catch (Exception e) {
			result.put("success", Boolean.FALSE);
			result.put("mensaje", e.getLocalizedMessage());
			response.setContentType("application/json; charset=ISO-8859-1");
			result.write(response.getWriter());
			e.printStackTrace();
		}  
		
	}
}
