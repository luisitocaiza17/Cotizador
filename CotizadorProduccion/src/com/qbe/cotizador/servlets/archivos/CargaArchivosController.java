package com.qbe.cotizador.servlets.archivos;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.DocumentoVisadoDAO;
import com.qbe.cotizador.dao.entidad.TipoDocumentoDAO;
import com.qbe.cotizador.dao.inspeccion.SolicitudInspeccionDAO;
import com.qbe.cotizador.model.ArchivoSolicitudInspeccion;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.DocumentoVisado;
import com.qbe.cotizador.model.TipoDocumento;
import com.qbe.cotizador.transaction.archivos.ArchivosSolicitudInspeccionTransaction;
import com.qbe.cotizador.transaction.archivos.DocumentoVisadoTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;

public class CargaArchivosController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
	HttpServletResponse response)
	throws ServletException, IOException {

		String ajaxUpdateResult = "";

		try {
			List < FileItem > items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			InputStream inputStream = null;
			//para archivos de solicitud de inspeccion
			for (int i = 0; i < items.size(); i++) {
				if (items.get(i) != null && items.get(i).getSize() != -1) {
					// debug messages

					if (items.get(i).getFieldName().contains("archivoSolicitudInspeccion")) {
						System.out.println(items.get(i).getName());
						System.out.println(items.get(i).getSize());
						System.out.println(items.get(i).getContentType());

						// obtains input stream of the upload file
						inputStream = items.get(i).getInputStream();

						SolicitudInspeccionDAO solicitudInspeccionDAO = new SolicitudInspeccionDAO();
						ArchivoSolicitudInspeccion archivoSolicitudInspeccion = new ArchivoSolicitudInspeccion();
						ArchivosSolicitudInspeccionTransaction archivoSolicitudInspeccionTransaction = new ArchivosSolicitudInspeccionTransaction();
						archivoSolicitudInspeccion.setContenidoArchivo(IOUtils.toByteArray(inputStream));
						archivoSolicitudInspeccion.setNombreArchivo(items.get(i).getName());
						archivoSolicitudInspeccion.setSolicitudInspeccion(solicitudInspeccionDAO.buscarPorId(items.get(i).getFieldName().replace("archivoSolicitudInspeccion", "")));
						archivoSolicitudInspeccion = archivoSolicitudInspeccionTransaction.crear(archivoSolicitudInspeccion);
						if (archivoSolicitudInspeccion.getId() != null) ajaxUpdateResult = "Se guardo el archivo con exito";
						else {
							if (items.get(i).getSize() > 8388608) ajaxUpdateResult = "Archivo muy grande! el tamaño máximo es de 8MB";
							else ajaxUpdateResult = "No se guardo el archivo con exito";
						}
					}

					if (items.get(i).getFieldName().contains("archivoPolizaFirmada")) {
						System.out.println(items.get(i).getName());
						System.out.println(items.get(i).getSize());
						System.out.println(items.get(i).getContentType());

						// obtains input stream of the upload file
						inputStream = items.get(i).getInputStream();

						CotizacionDAO cotizacionDAO = new CotizacionDAO();
						CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
						Cotizacion cotizacion = cotizacionDAO.buscarPorId(items.get(i).getFieldName().replace("archivoPolizaFirmada", ""));
						cotizacion.setEtapaWizard(6);
						cotizacion = cotizacionTransaction.editar(cotizacion);
						TipoDocumentoDAO tipoDocumentoDAO = new TipoDocumentoDAO();
						TipoDocumento tipoDocumento = tipoDocumentoDAO.buscarPorNombre("PolizaFirmada");
						DocumentoVisadoDAO dvDAO = new DocumentoVisadoDAO();
						DocumentoVisadoTransaction dvTransaction = new DocumentoVisadoTransaction();
						List < DocumentoVisado > dvLista = dvDAO.buscarPorCotizacionTipoDocumento(cotizacion, tipoDocumento);
						DocumentoVisado dv = new DocumentoVisado();
						if (dvLista.size() > 0) dv = dvLista.get(0);
						String mensaje = "Se guardo el archivo con exito";
						if (items.get(i).getSize() > 8388608) throw new Exception("El archivo puede ser máximo de 8MB");

						dv.setNombre(items.get(i).getName());
						dv.setContenido(IOUtils.toByteArray(inputStream));
						dv.setCotizacion(cotizacion);
						dv.setTipoDocumento(tipoDocumento);
						if (dv.getId() == null) dvTransaction.crear(dv);
						else dvTransaction.editar(dv);
						ajaxUpdateResult = "{'success':true,'mensaje':'" + mensaje + "'}";

					}

					if (items.get(i).getFieldName().contains("archivoAutorizacionDebito")) {
						System.out.println(items.get(i).getName());
						System.out.println(items.get(i).getSize());
						System.out.println(items.get(i).getContentType());

						// obtains input stream of the upload file
						inputStream = items.get(i).getInputStream();

						CotizacionDAO cotizacionDAO = new CotizacionDAO();
						CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
						Cotizacion cotizacion = cotizacionDAO.buscarPorId(items.get(i).getFieldName().replace("archivoAutorizacionDebito", ""));
						cotizacion.setEtapaWizard(6);
						cotizacion = cotizacionTransaction.editar(cotizacion);
						TipoDocumentoDAO tipoDocumentoDAO = new TipoDocumentoDAO();
						TipoDocumento tipoDocumento = tipoDocumentoDAO.buscarPorNombre("AutorizacionDebito");
						DocumentoVisadoDAO dvDAO = new DocumentoVisadoDAO();
						List < DocumentoVisado > dvLista = dvDAO.buscarPorCotizacionTipoDocumento(cotizacion, tipoDocumento);
						DocumentoVisado dv = new DocumentoVisado();
						DocumentoVisadoTransaction dvTransaction = new DocumentoVisadoTransaction();
						if (dvLista.size() > 0) dv = dvLista.get(0);
						String mensaje = "Se guardo el archivo con exito";
						if (items.get(i).getSize() > 8388608) throw new Exception("El archivo puede ser máximo de 8MB");

						dv.setNombre(items.get(i).getName());
						dv.setContenido(IOUtils.toByteArray(inputStream));
						dv.setCotizacion(cotizacion);
						dv.setTipoDocumento(tipoDocumento);
						if (dv.getId() == null) dvTransaction.crear(dv);
						else dvTransaction.editar(dv);
						ajaxUpdateResult = "{'success':true,'mensaje':'" + mensaje + "'}";

					}


					if (items.get(i).getFieldName().contains("archivoFormularioUPLA")) {
						System.out.println(items.get(i).getName());
						System.out.println(items.get(i).getSize());
						System.out.println(items.get(i).getContentType());

						// obtains input stream of the upload file
						inputStream = items.get(i).getInputStream();

						CotizacionDAO cotizacionDAO = new CotizacionDAO();
						Cotizacion cotizacion = cotizacionDAO.buscarPorId(items.get(i).getFieldName().replace("archivoFormularioUPLA", ""));
						cotizacion.setEtapaWizard(6);
						cotizacion = cotizacionDAO.editar(cotizacion);
						TipoDocumentoDAO tipoDocumentoDAO = new TipoDocumentoDAO();
						TipoDocumento tipoDocumento = tipoDocumentoDAO.buscarPorNombre("FormularioUPLA");
						DocumentoVisadoDAO dvDAO = new DocumentoVisadoDAO();
						List < DocumentoVisado > dvLista = dvDAO.buscarPorCotizacionTipoDocumento(cotizacion, tipoDocumento);
						DocumentoVisado dv = new DocumentoVisado();
						DocumentoVisadoTransaction dvTransaction = new DocumentoVisadoTransaction();
						if (dvLista.size() > 0) dv = dvLista.get(0);
						String mensaje = "Se guardo el archivo con exito";
						if (items.get(i).getSize() > 8388608) throw new Exception("El archivo puede ser máximo de 8MB");

						ClienteDAO clienteDAO = new ClienteDAO();
						Cliente cliente = clienteDAO.buscarPorId(cotizacion.getClienteId().toString());

						dv.setNombre(items.get(i).getName());
						dv.setContenido(IOUtils.toByteArray(inputStream));
						dv.setEntidad(cliente.getEntidad());
						dv.setTipoDocumento(tipoDocumento);
						if (dv.getId() == null) dvTransaction.crear(dv);
						else dvTransaction.editar(dv);
						ajaxUpdateResult = "{'success':true,'mensaje':'" + mensaje + "'}";

					}
				}
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
			ajaxUpdateResult = "{'success':false,'mensaje':'No se pudo guardar el archivo'}";
			throw new ServletException("Parsing file upload failed.", e);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxUpdateResult = "{'success':false,'mensaje':'" + e.getMessage() + "'}";

		}

		response.getWriter().print(ajaxUpdateResult);
	}

}