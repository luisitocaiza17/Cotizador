package com.qbe.cotizador.servlets.inspeccion;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.inspeccion.ArchivoSolicitudInspeccionDAO;
import com.qbe.cotizador.dao.inspeccion.SolicitudInspeccionDAO;
import com.qbe.cotizador.model.ArchivoSolicitudInspeccion;
import com.qbe.cotizador.model.SolicitudInspeccion;
import com.qbe.cotizador.transaction.inspeccion.ArchivoSolicitudInspeccionTransaction;

@WebServlet("/ArchivoSolicitudInspeccionController")
public class ArchivoSolicitudInspeccionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArchivoSolicitudInspeccionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo= request.getParameter("codigo");
		int BUFFER_SIZE = 4096;   
	    
		ArchivoSolicitudInspeccion archivoSolicitudInspeccion = new ArchivoSolicitudInspeccion();
		ArchivoSolicitudInspeccionDAO archivoSolicitudInspeccionDAO = new ArchivoSolicitudInspeccionDAO();
		
		if (!codigo.equals(""))
			archivoSolicitudInspeccion=archivoSolicitudInspeccionDAO.buscarPorId(codigo);
		if(archivoSolicitudInspeccion.getId()!=null){
			
			String fileName = archivoSolicitudInspeccion.getNombreArchivo();
			 byte[] contenido = archivoSolicitudInspeccion.getContenidoArchivo();
            InputStream inputStream =new ByteArrayInputStream(contenido);
            int fileLength = inputStream.available();
             
            System.out.println("fileLength = " + fileLength);
            
            ServletContext context = getServletContext();

            // sets MIME type for the file download
            String mimeType = context.getMimeType(fileName);
            if (mimeType == null) {        
                mimeType = "application/octet-stream";
            }              
             
            // set content properties and header attributes for the response
            response.setContentType(mimeType);
            response.setContentLength(fileLength);
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", fileName);
            response.setHeader(headerKey, headerValue);

            // writes the file to the client
            OutputStream outStream = response.getOutputStream();
             
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
             
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
             
            inputStream.close();
            outStream.close();             
        } else {
            // no file found
            response.getWriter().print("File not found for the id: " + codigo);  
        }
    } 
              
    


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		
		ArchivoSolicitudInspeccionTransaction archivoSolicitudInspeccionTransaction = new ArchivoSolicitudInspeccionTransaction();
		try{
			String codigo = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
			String solicitudInspecccionId = request.getParameter("codigoSolicitudInspecccion") == null ? "" : request.getParameter("codigoSolicitudInspecccion");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			
			JSONObject archivoSolicitudInspeccionJSONObject = new JSONObject();
			JSONArray archivoSolicitudInspeccionJSONArray = new JSONArray();

			ArchivoSolicitudInspeccion archivoSolicitudInspeccion = new ArchivoSolicitudInspeccion();
			ArchivoSolicitudInspeccionDAO archivoSolicitudInspeccionDAO = new ArchivoSolicitudInspeccionDAO();
			
			if (!codigo.equals(""))
				archivoSolicitudInspeccion=archivoSolicitudInspeccionDAO.buscarPorId(codigo);

			SolicitudInspeccion solicitudInspeccion = new SolicitudInspeccion();
			SolicitudInspeccionDAO solicitudInspeccionDAO = new SolicitudInspeccionDAO();
			
			if(solicitudInspecccionId!=null&&!solicitudInspecccionId.equals(""))
				solicitudInspeccion=solicitudInspeccionDAO.buscarPorId(solicitudInspecccionId);
			
						
			if(tipoConsulta.equals("encontrarTodos")){ 
				List<ArchivoSolicitudInspeccion> results = archivoSolicitudInspeccionDAO.buscarTodos();
				int i=0;
				for(i=0; i<results.size(); i++){
					archivoSolicitudInspeccion = results.get(i);
					archivoSolicitudInspeccionJSONObject.put("codigo", archivoSolicitudInspeccion.getId());
					archivoSolicitudInspeccionJSONObject.put("solicitudInspeccionId", archivoSolicitudInspeccion.getSolicitudInspeccion().getId());
					archivoSolicitudInspeccionJSONObject.put("nombre", archivoSolicitudInspeccion.getNombreArchivo());

					archivoSolicitudInspeccionJSONArray.add(archivoSolicitudInspeccionJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoArchivosSolicitudInspeccion", archivoSolicitudInspeccionJSONArray);
			}
			
			if(tipoConsulta.equals("encontrarPorSolicitudInspeccion")){ 
				List<ArchivoSolicitudInspeccion> results = archivoSolicitudInspeccionDAO.buscarPorSolicitudInspeccion(solicitudInspeccion);
				int i=0;
				for(i=0; i<results.size(); i++){
					archivoSolicitudInspeccion = results.get(i);
					archivoSolicitudInspeccionJSONObject.put("codigo", archivoSolicitudInspeccion.getId());
					archivoSolicitudInspeccionJSONObject.put("solicitudInspeccionId", archivoSolicitudInspeccion.getSolicitudInspeccion().getId());
					archivoSolicitudInspeccionJSONObject.put("nombre", archivoSolicitudInspeccion.getNombreArchivo());

					archivoSolicitudInspeccionJSONArray.add(archivoSolicitudInspeccionJSONObject);
				}
				result.put("numRegistros",i);
				result.put("listadoArchivosSolicitudInspeccion", archivoSolicitudInspeccionJSONArray);
			}
			
			if(tipoConsulta.equals("eliminar"))
				archivoSolicitudInspeccionTransaction.eliminar(archivoSolicitudInspeccion);


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
