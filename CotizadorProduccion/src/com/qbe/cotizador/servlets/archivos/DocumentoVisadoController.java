package com.qbe.cotizador.servlets.archivos;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.DocumentoVisadoDAO;
import com.qbe.cotizador.dao.entidad.TipoDocumentoDAO;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.DocumentoVisado;
import com.qbe.cotizador.model.TipoDocumento;
import com.qbe.cotizador.transaction.archivos.DocumentoVisadoTransaction;

public class DocumentoVisadoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	int BUFFER_SIZE = 4096; 
	int tamanhoArchivo=8388608;
	int tamMegas=(tamanhoArchivo/1024)/1024;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
		String id = request.getParameter("id") == null ? "" : request.getParameter("id").trim();
		
		if(tipoConsulta.equals("descargaPolizaFirmada")){
			 
			Cotizacion cotizacion = new Cotizacion();
			CotizacionDAO cotizacionDAO = new CotizacionDAO();
			
			if (!id.equals(""))
				cotizacion=cotizacionDAO.buscarPorId(id);
			if(cotizacion.getId()!=null){
				
				TipoDocumentoDAO tipoDocumentoDAO=new TipoDocumentoDAO();
				TipoDocumento tipoDocumento = tipoDocumentoDAO.buscarPorNombre("POLIZA COPIA DEVOLVER FIRMADO"); 
				DocumentoVisadoDAO dvDAO=new DocumentoVisadoDAO();				
				List<DocumentoVisado> dvLista=dvDAO.buscarPorCotizacionTipoDocumento(cotizacion, tipoDocumento);
				DocumentoVisado dv=new DocumentoVisado();
				if(dvLista.size()>0)
					dv=dvLista.get(0);
				String fileName = dv.getNombre();
				byte[] contenido = dv.getContenido();
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
	            response.getWriter().print("No se encontro la cotizacion: " + id);  
	        }
		}
			
		if(tipoConsulta.equals("descargaAutorizacionDebito")){
			
			Cotizacion cotizacion = new Cotizacion();
			CotizacionDAO cotizacionDAO = new CotizacionDAO();
			
			if (!id.equals(""))
				cotizacion=cotizacionDAO.buscarPorId(id);
			if(cotizacion.getId()!=null){
				
					TipoDocumentoDAO tipoDocumentoDAO=new TipoDocumentoDAO();
					TipoDocumento tipoDocumento = tipoDocumentoDAO.buscarPorNombre("AUTORIZACION DEBITO"); 
					DocumentoVisadoDAO dvDAO=new DocumentoVisadoDAO();
					List<DocumentoVisado> dvLista=dvDAO.buscarPorCotizacionTipoDocumento(cotizacion, tipoDocumento);
					DocumentoVisado dv=new DocumentoVisado();
					if(dvLista.size()>0)
						dv=dvLista.get(0);
					String fileName = dv.getNombre();
					byte[] contenido = dv.getContenido();
					 
					
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
	            response.getWriter().print("No se encontro la cotizacion: " + id);  
	        }
		}
		
		if(tipoConsulta.equals("descargaFormularioUPLA")){
			 
			Cotizacion cotizacion = new Cotizacion();
			CotizacionDAO cotizacionDAO = new CotizacionDAO();
			
			if (!id.equals(""))
				cotizacion=cotizacionDAO.buscarPorId(id);
			if(cotizacion.getId()!=null){
					ClienteDAO clienteDAO=new ClienteDAO();
					Cliente cliente=clienteDAO.buscarPorId(cotizacion.getClienteId().toString());
				
					TipoDocumentoDAO tipoDocumentoDAO=new TipoDocumentoDAO();
					TipoDocumento tipoDocumento = tipoDocumentoDAO.buscarPorNombre("FORMULARIO CONOCE A TU CLIENTE"); 
					DocumentoVisadoDAO dvDAO=new DocumentoVisadoDAO();
					List<DocumentoVisado> dvLista=dvDAO.buscarPorEntidadTipoDocumento(cliente.getEntidad(), tipoDocumento);
					DocumentoVisado dv=new DocumentoVisado();
					if(dvLista.size()>0)
						dv=dvLista.get(0);
					String fileName = dv.getNombre();
					byte[] contenido = dv.getContenido();
					 
					
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
	            response.getWriter().print("No se encontro la cotizacion: " + id);  
	        }
		}
		
		if(tipoConsulta.equals("descargaCaratulaCotizacion")){
			 
			Cotizacion cotizacion = new Cotizacion();
			CotizacionDAO cotizacionDAO = new CotizacionDAO();
			
			if (!id.equals(""))
				cotizacion=cotizacionDAO.buscarPorId(id);
			if(cotizacion.getId()!=null){
					TipoDocumentoDAO tipoDocumentoDAO=new TipoDocumentoDAO();
					TipoDocumento tipoDocumento = tipoDocumentoDAO.buscarPorNombre("CaratulaCotizacion"); 
					DocumentoVisadoDAO dvDAO=new DocumentoVisadoDAO();
					List<DocumentoVisado> dvLista=dvDAO.buscarPorCotizacionTipoDocumento(cotizacion, tipoDocumento);
					DocumentoVisado dv=new DocumentoVisado();
					if(dvLista.size()>0)
						dv=dvLista.get(0);
					String fileName = dv.getNombre();
					byte[] contenido = dv.getContenido();
					 
					
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
	            response.getWriter().print("No se encontro la cotizacion: " + id);  
	        }
		}
		
		if(tipoConsulta.equals("descargaFactura")){
			int BUFFER_SIZE = 4096;   
		    
			Cotizacion cotizacion = new Cotizacion();
			CotizacionDAO cotizacionDAO = new CotizacionDAO();
			
			if (!id.equals(""))
				cotizacion=cotizacionDAO.buscarPorId(id);
			if(cotizacion.getId()!=null){
					TipoDocumentoDAO tipoDocumentoDAO=new TipoDocumentoDAO();
					TipoDocumento tipoDocumento = tipoDocumentoDAO.buscarPorNombre("Factura"); 
					DocumentoVisadoDAO dvDAO=new DocumentoVisadoDAO();
					List<DocumentoVisado> dvLista=dvDAO.buscarPorCotizacionTipoDocumento(cotizacion, tipoDocumento);
					DocumentoVisado dv=new DocumentoVisado();
					if(dvLista.size()>0)
						dv=dvLista.get(0);
					String fileName = dv.getNombre();
					byte[] contenido = dv.getContenido();
					 
					
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
	            response.getWriter().print("No se encontro la cotizacion: " + id);  
	        }
		}
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		JSONObject result = new JSONObject();
		try{
			String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			
			if(tipoConsulta.equalsIgnoreCase("")){
				guardarArchivos(request, response);
			}
			
			if(tipoConsulta.equalsIgnoreCase("eliminarArchivoPolizaFirmada"))
			{
				eliminarArchivoPolizaFirmada(cotizacionId);
			}
			
			if(tipoConsulta.equalsIgnoreCase("eliminarArchivoAutorizacionDebito"))
			{
				eliminarArchivoAutorizacionDebito(cotizacionId);
			}
			
			if(tipoConsulta.equalsIgnoreCase("eliminarArchivoFormularioUPLA"))
			{
				eliminarArchivoFormularioUPLA(cotizacionId);
			}
			
			if(tipoConsulta.equalsIgnoreCase("eliminarArchivoCaratulaCotizacion"))
			{
				eliminarArchivoCaratulaCotizacion(cotizacionId);
			}
			
			if(tipoConsulta.equalsIgnoreCase("eliminarArchivoFactura"))
			{
				eliminarArchivoFactura(cotizacionId);
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
		
	public void guardarArchivos(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			List<FileItem> items = new ServletFileUpload(
					new DiskFileItemFactory()).parseRequest(request);
			InputStream inputStream = null;
			// para archivos de solicitud de inspeccion
			for (int i = 0; i < items.size(); i++) {
				if (items.get(i) != null && items.get(i).getSize() != -1) {
					// debug messages

					if (items.get(i).getFieldName().contains("archivoPolizaFirmada")) {
						System.out.println(items.get(i).getName());
						System.out.println(items.get(i).getSize());
						System.out.println(items.get(i).getContentType());

						// obtains input stream of the upload file
						inputStream = items.get(i).getInputStream();

						CotizacionDAO cotizacionDAO = new CotizacionDAO();
						Cotizacion cotizacion = cotizacionDAO.buscarPorId(items.get(i).getFieldName().replace("archivoPolizaFirmada", ""));
						TipoDocumentoDAO tipoDocumentoDAO = new TipoDocumentoDAO();
						TipoDocumento tipoDocumento = tipoDocumentoDAO.buscarPorNombre("POLIZA COPIA DEVOLVER FIRMADO");
						DocumentoVisadoDAO dvDAO = new DocumentoVisadoDAO();
						List<DocumentoVisado> dvLista = dvDAO.buscarPorCotizacionTipoDocumento(cotizacion,tipoDocumento);
						DocumentoVisado dv = new DocumentoVisado();
						DocumentoVisadoTransaction dvTransaction = new DocumentoVisadoTransaction();
						if (dvLista.size() > 0)
							dv = dvLista.get(0);
						if (items.get(i).getSize() > tamanhoArchivo)
							throw new Exception("El archivo debe ser menor a "+tamMegas+"MB");

						dv.setNombre(items.get(i).getName());
						dv.setContenido(IOUtils.toByteArray(inputStream));
						dv.setCotizacion(cotizacion);
						dv.setTipoDocumento(tipoDocumento);
						if (dv.getId() == null)
							dvTransaction.crear(dv);
						else
							dvTransaction.editar(dv);
					

					}

					if (items.get(i).getFieldName().contains("archivoAutorizacionDebito")) {
						System.out.println(items.get(i).getName());
						System.out.println(items.get(i).getSize());
						System.out.println(items.get(i).getContentType());

						// obtains input stream of the upload file
						inputStream = items.get(i).getInputStream();

						CotizacionDAO cotizacionDAO = new CotizacionDAO();
						Cotizacion cotizacion = cotizacionDAO.buscarPorId(items.get(i).getFieldName().replace("archivoAutorizacionDebito", ""));
						TipoDocumentoDAO tipoDocumentoDAO = new TipoDocumentoDAO();
						TipoDocumento tipoDocumento = tipoDocumentoDAO.buscarPorNombre("AUTORIZACION DEBITO");
						DocumentoVisadoDAO dvDAO = new DocumentoVisadoDAO();
						List<DocumentoVisado> dvLista = dvDAO.buscarPorCotizacionTipoDocumento(cotizacion,tipoDocumento);
						DocumentoVisado dv = new DocumentoVisado();
						DocumentoVisadoTransaction dvTransaction = new DocumentoVisadoTransaction();
						
						if (dvLista.size() > 0)
							dv = dvLista.get(0);
						if (items.get(i).getSize() > tamanhoArchivo)
							throw new Exception("El archivo debe ser menor a "+tamMegas+"MB");

						dv.setNombre(items.get(i).getName());
						dv.setContenido(IOUtils.toByteArray(inputStream));
						dv.setCotizacion(cotizacion);
						dv.setTipoDocumento(tipoDocumento);
						if (dv.getId() == null)
							dvTransaction.crear(dv);
						else
							dvTransaction.editar(dv);
					

					}

					if (items.get(i).getFieldName().contains("archivoFormularioUPLA")) {
						System.out.println(items.get(i).getName());
						System.out.println(items.get(i).getSize());
						System.out.println(items.get(i).getContentType());

						// obtains input stream of the upload file
						inputStream = items.get(i).getInputStream();

						CotizacionDAO cotizacionDAO = new CotizacionDAO();
						Cotizacion cotizacion = cotizacionDAO.buscarPorId(items.get(i).getFieldName().replace("archivoFormularioUPLA", ""));
						TipoDocumentoDAO tipoDocumentoDAO = new TipoDocumentoDAO();
						TipoDocumento tipoDocumento = tipoDocumentoDAO.buscarPorNombre("FORMULARIO CONOCE A TU CLIENTE");
						DocumentoVisadoDAO dvDAO = new DocumentoVisadoDAO();
						List<DocumentoVisado> dvLista = dvDAO.buscarPorCotizacionTipoDocumento(cotizacion,tipoDocumento);
						DocumentoVisado dv = new DocumentoVisado();
						DocumentoVisadoTransaction dvTransaction = new DocumentoVisadoTransaction();
						
						if (dvLista.size() > 0)
							dv = dvLista.get(0);
						if (items.get(i).getSize() > tamanhoArchivo)
							throw new Exception("El archivo debe ser menor a "+tamMegas+"MB");

						ClienteDAO clienteDAO = new ClienteDAO();
						Cliente cliente = clienteDAO.buscarPorId(cotizacion.getClienteId().toString());

						dv.setNombre(items.get(i).getName());
						dv.setContenido(IOUtils.toByteArray(inputStream));
						dv.setEntidad(cliente.getEntidad());
						dv.setTipoDocumento(tipoDocumento);
						if (dv.getId() == null)
							dvTransaction.crear(dv);
						else
							dvTransaction.editar(dv);
						

					}

					if (items.get(i).getFieldName().contains("archivoCaratulaCotizacion")) {
						System.out.println(items.get(i).getName());
						System.out.println(items.get(i).getSize());
						System.out.println(items.get(i).getContentType());

						// obtains input stream of the upload file
						inputStream = items.get(i).getInputStream();

						CotizacionDAO cotizacionDAO = new CotizacionDAO();
						Cotizacion cotizacion = cotizacionDAO.buscarPorId(items.get(i).getFieldName().replace("archivoCaratulaCotizacion", ""));
						TipoDocumentoDAO tipoDocumentoDAO = new TipoDocumentoDAO();
						TipoDocumento tipoDocumento = tipoDocumentoDAO.buscarPorNombre("CaratulaCotizacion");
						DocumentoVisadoDAO dvDAO = new DocumentoVisadoDAO();
						List<DocumentoVisado> dvLista = dvDAO.buscarPorCotizacionTipoDocumento(cotizacion,tipoDocumento);
						DocumentoVisado dv = new DocumentoVisado();
						DocumentoVisadoTransaction dvTransaction = new DocumentoVisadoTransaction();
						if (dvLista.size() > 0)
							dv = dvLista.get(0);
						if (items.get(i).getSize() > tamanhoArchivo)
							throw new Exception("El archivo debe ser menor a "+tamMegas+"MB");

						dv.setNombre(items.get(i).getName());
						dv.setContenido(IOUtils.toByteArray(inputStream));
						dv.setCotizacion(cotizacion);
						dv.setTipoDocumento(tipoDocumento);
						if (dv.getId() == null)
							dvTransaction.crear(dv);
						else
							dvTransaction.editar(dv);
						

					}
					
					if (items.get(i).getFieldName().contains("archivoFactura")) {
						System.out.println(items.get(i).getName());
						System.out.println(items.get(i).getSize());
						System.out.println(items.get(i).getContentType());

						// obtains input stream of the upload file
						inputStream = items.get(i).getInputStream();

						CotizacionDAO cotizacionDAO = new CotizacionDAO();
						Cotizacion cotizacion = cotizacionDAO.buscarPorId(items.get(i).getFieldName().replace("archivoFactura", ""));
						TipoDocumentoDAO tipoDocumentoDAO = new TipoDocumentoDAO();
						TipoDocumento tipoDocumento = tipoDocumentoDAO.buscarPorNombre("Factura");
						DocumentoVisadoDAO dvDAO = new DocumentoVisadoDAO();
						List<DocumentoVisado> dvLista = dvDAO.buscarPorCotizacionTipoDocumento(cotizacion,tipoDocumento);
						DocumentoVisado dv = new DocumentoVisado();
						DocumentoVisadoTransaction dvTransaction = new DocumentoVisadoTransaction();
						if (dvLista.size() > 0)
							dv = dvLista.get(0);
						if (items.get(i).getSize() > tamanhoArchivo)
							throw new Exception("El archivo debe ser menor a "+tamMegas+"MB");

						dv.setNombre(items.get(i).getName());
						dv.setContenido(IOUtils.toByteArray(inputStream));
						dv.setCotizacion(cotizacion);
						dv.setTipoDocumento(tipoDocumento);
						if (dv.getId() == null)
							dvTransaction.crear(dv);
						else
							dvTransaction.editar(dv);
						

					}
				}
			}

		}
		catch (FileUploadException e) {
			e.printStackTrace();
			throw new ServletException("Parsing file upload failed.", e);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new Exception("El archivo debe ser menor a "+tamMegas+"MB");
		}
	}
	
	
	public void eliminarArchivoPolizaFirmada(String id){
		Cotizacion cotizacion = new Cotizacion();
		CotizacionDAO cotizacionDAO = new CotizacionDAO();
		
		if (!id.equals(""))
			cotizacion=cotizacionDAO.buscarPorId(id);
		if(cotizacion.getId()!=null){
			TipoDocumentoDAO tipoDocumentoDAO=new TipoDocumentoDAO();
			TipoDocumento tipoDocumento = tipoDocumentoDAO.buscarPorNombre("POLIZA COPIA DEVOLVER FIRMADO"); 
			DocumentoVisadoDAO dvDAO=new DocumentoVisadoDAO();
			List<DocumentoVisado> dvLista=dvDAO.buscarPorCotizacionTipoDocumento(cotizacion, tipoDocumento);
			DocumentoVisado dv=new DocumentoVisado();
			DocumentoVisadoTransaction dvTransaction = new DocumentoVisadoTransaction();
			if(dvLista.size()>0)
				dv=dvLista.get(0);
			dvTransaction.eliminar(dv);
		}
	}
	
	public void eliminarArchivoAutorizacionDebito(String id){
		Cotizacion cotizacion = new Cotizacion();
		CotizacionDAO cotizacionDAO = new CotizacionDAO();
		
		if (!id.equals(""))
			cotizacion=cotizacionDAO.buscarPorId(id);
		if(cotizacion.getId()!=null){
			
			TipoDocumentoDAO tipoDocumentoDAO=new TipoDocumentoDAO();
			TipoDocumento tipoDocumento = tipoDocumentoDAO.buscarPorNombre("AUTORIZACION DEBITO"); 
			DocumentoVisadoDAO dvDAO=new DocumentoVisadoDAO();
			List<DocumentoVisado> dvLista=dvDAO.buscarPorCotizacionTipoDocumento(cotizacion, tipoDocumento);
			DocumentoVisado dv=new DocumentoVisado();
			DocumentoVisadoTransaction dvTransaction = new DocumentoVisadoTransaction();
			if(dvLista.size()>0)
				dv=dvLista.get(0);
			dvTransaction.eliminar(dv);
		}
	}
	
	public void eliminarArchivoFormularioUPLA(String id){
		Cotizacion cotizacion = new Cotizacion();
		CotizacionDAO cotizacionDAO = new CotizacionDAO();
		
		if (!id.equals(""))
			cotizacion=cotizacionDAO.buscarPorId(id);
		if(cotizacion.getId()!=null){
			ClienteDAO clienteDAO=new ClienteDAO();
			Cliente cliente=clienteDAO.buscarPorId(cotizacion.getClienteId().toString());
			
			TipoDocumentoDAO tipoDocumentoDAO=new TipoDocumentoDAO();
			TipoDocumento tipoDocumento = tipoDocumentoDAO.buscarPorNombre("FORMULARIO CONOCE A TU CLIENTE"); 
			DocumentoVisadoDAO dvDAO=new DocumentoVisadoDAO();
			List<DocumentoVisado> dvLista=dvDAO.buscarPorEntidadTipoDocumento(cliente.getEntidad(), tipoDocumento);
			DocumentoVisado dv=new DocumentoVisado();
			DocumentoVisadoTransaction dvTransaction = new DocumentoVisadoTransaction();
			if(dvLista.size()>0)
				dv=dvLista.get(0);
			dvTransaction.eliminar(dv);
		}
	}
	
	public void eliminarArchivoCaratulaCotizacion(String id){
		Cotizacion cotizacion = new Cotizacion();
		CotizacionDAO cotizacionDAO = new CotizacionDAO();
		
		if (!id.equals(""))
			cotizacion=cotizacionDAO.buscarPorId(id);
		if(cotizacion.getId()!=null){
			
			TipoDocumentoDAO tipoDocumentoDAO=new TipoDocumentoDAO();
			TipoDocumento tipoDocumento = tipoDocumentoDAO.buscarPorNombre("CaratulaCotizacion"); 
			DocumentoVisadoDAO dvDAO=new DocumentoVisadoDAO();
			List<DocumentoVisado> dvLista=dvDAO.buscarPorCotizacionTipoDocumento(cotizacion, tipoDocumento);
			DocumentoVisado dv=new DocumentoVisado();
			DocumentoVisadoTransaction dvTransaction = new DocumentoVisadoTransaction();
			if(dvLista.size()>0)
				dv=dvLista.get(0);
			dvTransaction.eliminar(dv);
		}
	}
	
	public void eliminarArchivoFactura(String id){
		Cotizacion cotizacion = new Cotizacion();
		CotizacionDAO cotizacionDAO = new CotizacionDAO();
		
		if (!id.equals(""))
			cotizacion=cotizacionDAO.buscarPorId(id);
		if(cotizacion.getId()!=null){
			
			TipoDocumentoDAO tipoDocumentoDAO=new TipoDocumentoDAO();
			TipoDocumento tipoDocumento = tipoDocumentoDAO.buscarPorNombre("Factura"); 
			DocumentoVisadoDAO dvDAO=new DocumentoVisadoDAO();
			List<DocumentoVisado> dvLista=dvDAO.buscarPorCotizacionTipoDocumento(cotizacion, tipoDocumento);
			DocumentoVisado dv=new DocumentoVisado();
			DocumentoVisadoTransaction dvTransaction = new DocumentoVisadoTransaction();
			if(dvLista.size()>0)
				dv=dvLista.get(0);
			dvTransaction.eliminar(dv);
		}
	}
}
