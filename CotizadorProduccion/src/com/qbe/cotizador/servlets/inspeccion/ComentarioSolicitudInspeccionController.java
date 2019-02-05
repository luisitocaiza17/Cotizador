package com.qbe.cotizador.servlets.inspeccion;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.entidad.UsuarioDAO;
import com.qbe.cotizador.dao.inspeccion.ComentarioSolicitudInspeccionDAO;
import com.qbe.cotizador.dao.inspeccion.SolicitudInspeccionDAO;
import com.qbe.cotizador.model.ComentarioSolicitudInspeccion;
import com.qbe.cotizador.model.SolicitudInspeccion;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.transaction.inspeccion.ComentarioSolicitudInspeccionTransaction;


@WebServlet("/ComentarioSolicitudInspeccionController")
public class ComentarioSolicitudInspeccionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComentarioSolicitudInspeccionController() {
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
		ComentarioSolicitudInspeccionTransaction comentarioSolicitudInspeccionTransaction = new ComentarioSolicitudInspeccionTransaction();
		try{
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String solicitudInspecccionId = request.getParameter("codigoSolicitudInspecccion") == null ? "" : request.getParameter("codigoSolicitudInspecccion");
			String descripcion = request.getParameter("descripcion") == null ? "" : request.getParameter("descripcion");
			Usuario usuario =((Usuario) request.getSession().getAttribute("usuario"))!=null?((Usuario) request.getSession().getAttribute("usuario")):new Usuario();
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			
			if(usuario==null||usuario.getId()==null)
				throw new Exception("Por favor Inicie Sesión!");
			
			JSONObject comentarioSolicitudInspeccionJSONObject = new JSONObject();
			JSONArray comentarioSolicitudInspeccionJSONArray = new JSONArray();

			ComentarioSolicitudInspeccion comentarioSolicitudInspeccion = new ComentarioSolicitudInspeccion();
			ComentarioSolicitudInspeccionDAO comentarioSolicitudInspeccionDAO = new ComentarioSolicitudInspeccionDAO();
			
			if (!codigo.equals(""))
				comentarioSolicitudInspeccion=comentarioSolicitudInspeccionDAO.buscarPorId(codigo);

			SolicitudInspeccion solicitudInspeccion = new SolicitudInspeccion();
			SolicitudInspeccionDAO solicitudInspeccionDAO = new SolicitudInspeccionDAO();
			
			if (!solicitudInspecccionId.equals("")){
				solicitudInspeccion = solicitudInspeccionDAO.buscarPorId(solicitudInspecccionId);
				comentarioSolicitudInspeccion.setSolicitudInspeccion(solicitudInspeccion);
			}
			
			if (!descripcion.equals("")){
				
				comentarioSolicitudInspeccion.setDescripcion(descripcion);
			}
			
					
			if (usuario.getId()!=null&&!usuario.getId().equals("")){
				comentarioSolicitudInspeccion.setUsuarioId(BigInteger.valueOf(Long.parseLong(usuario.getId())));	
			}
			
			Calendar calendar = Calendar.getInstance();
			comentarioSolicitudInspeccion.setFecha(new java.sql.Timestamp(calendar.getTime().getTime()));
			
			
			if(tipoConsulta.equals("encontrarTodos")){ 
				List<ComentarioSolicitudInspeccion> results = comentarioSolicitudInspeccionDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					comentarioSolicitudInspeccion = results.get(i);
					comentarioSolicitudInspeccionJSONObject.put("codigo", comentarioSolicitudInspeccion.getId());
					comentarioSolicitudInspeccionJSONObject.put("solicitudInspeccionId", comentarioSolicitudInspeccion.getSolicitudInspeccion().getId());
					comentarioSolicitudInspeccionJSONObject.put("descripcion", comentarioSolicitudInspeccion.getDescripcion());
					comentarioSolicitudInspeccionJSONObject.put("usuarioId", comentarioSolicitudInspeccion.getUsuarioId());
					comentarioSolicitudInspeccionJSONObject.put("fecha", comentarioSolicitudInspeccion.getFecha());

					comentarioSolicitudInspeccionJSONArray.add(comentarioSolicitudInspeccionJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoComentarioSolicitudInspeccion", comentarioSolicitudInspeccionJSONArray);
			}
			
			if(tipoConsulta.equals("encontrarPorSolicitudInspeccion")){ 
				UsuarioDAO usuarioDAO=new UsuarioDAO();
				List<ComentarioSolicitudInspeccion> results = comentarioSolicitudInspeccionDAO.buscarPorSolicitudInspeccion(solicitudInspeccion);
				int i=0;
				for(i=0; i<results.size(); i++){
					comentarioSolicitudInspeccion = results.get(i);
					comentarioSolicitudInspeccionJSONObject.put("codigo", comentarioSolicitudInspeccion.getId());
					comentarioSolicitudInspeccionJSONObject.put("solicitudInspeccionId", comentarioSolicitudInspeccion.getSolicitudInspeccion().getId());
					comentarioSolicitudInspeccionJSONObject.put("descripcion", comentarioSolicitudInspeccion.getDescripcion());
					comentarioSolicitudInspeccionJSONObject.put("usuario", usuarioDAO.buscarPorId(comentarioSolicitudInspeccion.getUsuarioId().toString()).getEntidad().getNombreCompleto());
					comentarioSolicitudInspeccionJSONObject.put("fecha", comentarioSolicitudInspeccion.getFecha().toString());

					comentarioSolicitudInspeccionJSONArray.add(comentarioSolicitudInspeccionJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoComentarioSolicitudInspeccion", comentarioSolicitudInspeccionJSONArray);
			}
			
			if(tipoConsulta.equals("crear")){
				
				comentarioSolicitudInspeccionTransaction.crear(comentarioSolicitudInspeccion);
			}
			if(tipoConsulta.equals("actualizar"))
				comentarioSolicitudInspeccionTransaction.editar(comentarioSolicitudInspeccion);

			if(tipoConsulta.equals("eliminar"))
				comentarioSolicitudInspeccionTransaction.eliminar(comentarioSolicitudInspeccion);


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
