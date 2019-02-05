package com.qbe.cotizador.servlets.producto.vehiculo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.jws.WebParam;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import net.sf.json.JSONObject;
import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoXPuntoVentaDAO;
import com.qbe.cotizador.dao.entidad.AgenteDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.DocumentoVisadoDAO;
import com.qbe.cotizador.dao.entidad.RamoDAO;
import com.qbe.cotizador.dao.entidad.TipoDocumentoDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.Agente;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.Direccion;
import com.qbe.cotizador.model.DocumentoVisado;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.ProductoXPuntoVenta;
import com.qbe.cotizador.model.Ramo;
import com.qbe.cotizador.model.TipoDocumento;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.servicios.QBE.clienteServiciosCotizador.WebServiceCotizadorImplService;
import com.qbe.cotizador.servicios.smartwork.workflow.ArrayOfDocumentList;
import com.qbe.cotizador.servicios.smartwork.workflow.ArrayOfFileList;
import com.qbe.cotizador.servicios.smartwork.workflow.ArrayOfQBEControlDocumentos;
import com.qbe.cotizador.servicios.smartwork.workflow.ClassificationType;
import com.qbe.cotizador.servicios.smartwork.workflow.DocumentList;
import com.qbe.cotizador.servicios.smartwork.workflow.ExternalService;
import com.qbe.cotizador.servicios.smartwork.workflow.ExternalServiceSoap;
import com.qbe.cotizador.servicios.smartwork.workflow.FileList;
import com.qbe.cotizador.servicios.smartwork.workflow.QBEControlDocumentos;
import com.qbe.cotizador.servicios.smartwork.workflow.QBEDestinatario;
import com.qbe.cotizador.servicios.smartwork.workflow.QBEDeuda;
import com.qbe.cotizador.servicios.smartwork.workflow.QBEEntidad;
import com.qbe.cotizador.servicios.smartwork.workflow.QBEFianza;
import com.qbe.cotizador.servicios.smartwork.workflow.QBESolicitudEmision;
import com.qbe.cotizador.servicios.smartwork.workflow.WorkFlowResult;
import com.qbe.cotizador.servicios.smartwork.workflow.WorkFlowType;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;

/**
 * Servlet implementation class WorkFlowSmartworkController
 */@WebServlet("/WorkFlowSmartworkController")
public class WorkFlowSmartworkController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WorkFlowSmartworkController() {
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
		try {
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");


			CotizacionDAO cDAO = new CotizacionDAO();

			Cotizacion cotizacion = cDAO.buscarPorId(cotizacionId);

			JSONObject resultado=new JSONObject();
			
			if (tipoConsulta.equals("crearTramiteWF")) 
			  {
			  if(cotizacion.getNumeroTramite()!=null)
			    if(cotizacion.getNumeroTramite().equals(""))
			      throw new Exception("La cotizacion ya tiene asignada el tramite #"+cotizacion.getNumeroTramite());
			  
			    resultado=crearTramiteWorkFlow(cotizacion);
			    result.put("resultado", resultado);
			    String numeroTramite= resultado.getString("InstanceNumber");
			    WebServiceCotizadorImplService webService = new WebServiceCotizadorImplService();
                if(!webService.getWebServiceCotizadorImplPort().guardarNumeroTramiteEndoso(cotizacion.getNumeroFactura(),numeroTramite)){
                  String mensaje="Se creo el tramite numero "+numeroTramite+" pero no se puedo actualizar este numero en ensurance.";
                  throw new Exception(mensaje);
                }
			  }

			

			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1");
			result.write(response.getWriter());
		} catch (Exception e) {
			result.put("success", Boolean.FALSE);
			result.put("error", e.getLocalizedMessage());
			response.setContentType("application/json; charset=ISO-8859-1");
			result.write(response.getWriter());
			e.printStackTrace();

		}

	}

	public JSONObject crearTramiteWorkFlow(Cotizacion cotizacion) throws Exception {
		JSONObject retorno = new JSONObject();
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente clienteObj = clienteDAO.buscarPorId(cotizacion.getClienteId().toString());
		Entidad cliente = clienteObj.getEntidad();

		CotizacionTransaction cotizacionTransaction= new CotizacionTransaction();
		
		Direccion dirCliente = cliente.getDireccions().get(0);

		Entidad asegurado = cotizacion.getAsegurado();

		Direccion dirAseguardo = asegurado.getDireccions().get(0);

		ProductoXPuntoVentaDAO pxpvDAO = new ProductoXPuntoVentaDAO();
		ProductoXPuntoVenta pxpv = pxpvDAO.buscarPorId(cotizacion.getProductoXPuntoVentaId().toString());
		RamoDAO ramoDAO = new RamoDAO();
		Ramo ramo = ramoDAO.buscarPorId(pxpv.getGrupoPorProducto().getProducto().getRamoId().toString());

		AgenteDAO agenteDAO = new AgenteDAO();
		Agente agente = agenteDAO.buscarPorId(cotizacion.getAgenteId().toString());

		List<String> documentosRequeridos=new ArrayList();
		documentosRequeridos.add("POLIZA COPIA DEVOLVER FIRMADO");
		documentosRequeridos.add("FORMULARIO CONOCE A TU CLIENTE");
		documentosRequeridos.add("GUIA REMISION");
		//documentosRequeridos.add("KIT VEHICULOS");
		//documentosRequeridos.add("POLIZA ASEGURADO");
		//documentosRequeridos.add("POLIZA COPIA AGENTE");
		
		//mapeo forma de pago
		String formaPagoWF = "CONTADO";
		if (cotizacion.getPago().getFormaPago().getNombre().trim().equals("DEBITO BANCARIO")
				&& cotizacion.getPago().getCuotaInicial() == 0) {
			formaPagoWF = "DEBITO BANCARIO";
			documentosRequeridos.add("AUTORIZACION DEBITO");
			
		}
		if (cotizacion.getPago().getFormaPago().getNombre().trim().equals("DEBITO BANCARIO")
				&& cotizacion.getPago().getCuotaInicial() > 0) {
			formaPagoWF = "Cuota Inicial + Débito Bancario";
			documentosRequeridos.add("AUTORIZACION DEBITO");
			
		}
		if (cotizacion.getPago().getFormaPago().getNombre().trim().equals("DEBITO TARJETA")) {
			formaPagoWF = "Cuota Inicial + Tarjeta de Crédito";
			documentosRequeridos.add("AUTORIZACION DEBITO");
			
		}
		if (cotizacion.getPago().getFormaPago().getNombre().trim().equals("CREDITO CUOTAS")) {
			formaPagoWF = "PAGOS IGUALES EN CUOTAS";
			documentosRequeridos.add("AUTORIZACION DEBITO");
			
		}


		// TODO Auto-generated method stub	
		ExternalService servicioWorkFlowExternalService = new ExternalService();
		ExternalServiceSoap servicioSmartWorkSoap = servicioWorkFlowExternalService.getExternalServiceSoap();

		VariableSistemaDAO vsDAO=new VariableSistemaDAO();
        
		String token = vsDAO.buscarPorNombre("TOKEN_WORKFLOW").getValor();//"EBB48E36-9D94-4AA0-A711-673272A8B499";
		String username = vsDAO.buscarPorNombre("USUARIO_WORKFLOW").getValor(); //"1714282777"; //CEDULA A QUIEN LE VA A LLEGAR	revisar

		List < DocumentList > listaDocumentos = servicioSmartWorkSoap.loadDocumentsByClassificator(token, ClassificationType.DOCUMENTOSVISADO).getDocumentList();
		List < DocumentList > listaRamos = servicioSmartWorkSoap.loadDocumentsByClassificator(token, ClassificationType.RAMOS).getDocumentList();
		List < DocumentList > listaFormaPago = servicioSmartWorkSoap.loadDocumentsByClassificator(token, ClassificationType.FORMADEPAGO).getDocumentList();
		List < DocumentList > listaUnidadComercial = servicioSmartWorkSoap.loadDocumentsByClassificator(token, ClassificationType.UNIDADCOMERCIAL).getDocumentList();
		List < DocumentList > listaMatrizSucursal = servicioSmartWorkSoap.loadDocumentsByClassificator(token, ClassificationType.SUCURSALES).getDocumentList();
		List < DocumentList > listaBroker = servicioSmartWorkSoap.loadDocumentsByClassificator(token, ClassificationType.BROKER).getDocumentList();
		List < DocumentList > listaTipoEndoso = servicioSmartWorkSoap.loadDocumentsByClassificator(token, ClassificationType.TIPODEENDOSO).getDocumentList();
		List < DocumentList > listaTipoPoliza = servicioSmartWorkSoap.loadDocumentsByClassificator(token, ClassificationType.TIPODEPOLIZA).getDocumentList();

		// Obtenemos los datos del usuario con el que nos conectamos al workflow		
		// Creamos el Objeto Solicitud de Emision para enviar a Smartwork
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(new Date());
		XMLGregorianCalendar fechaActual = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

		QBESolicitudEmision solicitud = new QBESolicitudEmision();

		String ramoGUID = buscarGUIDPorNombre(ramo.getNombre(), listaRamos);
		String formaPagoGUID = buscarGUIDPorNombre(formaPagoWF, listaFormaPago);
		String unidadComercialGUID = buscarGUIDPorNombre(pxpv.getUnidadNegocio().getNombre(), listaUnidadComercial);
		String matrizSucursalGUID = buscarGUIDPorNombre(pxpv.getPuntoVenta().getSucursal().getNombre(), listaMatrizSucursal);
		String brokerGUID = "";
		//if(agente.getEntidad().getTipoIdentificacion().getId().equalsIgnoreCase("4") ){
		//	System.out.println(agente.getEntidad().getNombreCompleto().toString());
			brokerGUID = buscarGUIDPorNombre(agente.getEntidad().getNombreCompleto().toString(), listaBroker);
		//}
		//else
//			brokerGUID = buscarGUIDPorNombre(agente.getEntidad().getApellidos() + " " + agente.getEntidad().getNombres(), listaBroker);
		
		String tipoEndosoGUID = buscarGUIDPorNombre("Emisión Póliza Individual", listaTipoEndoso);
		String tipoPolizaGUID = buscarGUIDPorNombre("100% QBE", listaTipoPoliza);

		if (cliente.getEntEnsurance() == null || cliente.getEntEnsurance().equals("")) solicitud.setEsClienteNuevo(true);
		else solicitud.setEsClienteNuevo(false);

		solicitud.setNombreAsegurado(cliente.getNombres());
		solicitud.setApellidoAsegurado(cliente.getApellidos());
		solicitud.setCIRUC(cliente.getIdentificacion());
		solicitud.setDireccion(dirCliente.getCallePrincipal() + " " + dirAseguardo.getNumero() + " " + dirAseguardo.getCalleSecundaria());
		solicitud.setTelefonoCelular(cliente.getCelular());
		solicitud.setTipoPoliza(tipoPolizaGUID);
		solicitud.setTipoEndoso(tipoEndosoGUID);
		solicitud.setFechaNacimiento(fechaActual);
		solicitud.setCorreoElectronico(cliente.getMail());
		solicitud.setBroker(brokerGUID);
		solicitud.setFormaPago(formaPagoGUID);
		solicitud.setUnidadComercial(unidadComercialGUID);
		solicitud.setMatrizSucursal(matrizSucursalGUID);
		solicitud.setRamo(ramoGUID);
		solicitud.setRemisionDocumentos(vsDAO.buscarPorNombre("REMISION_DOCUMENTOS_WORKFLOW").getValor()); //"22542278-9437-45D2-9480-C72DAA501CD9");

		solicitud.setVerificacionDatosCliente(true);
		solicitud.setTieneTasaEspecial(false);
		solicitud.setTieneComisionEspecial(false);
		solicitud.setProgramaVariosemisores(false);
		solicitud.setUrgente(false);
		solicitud.setCumplimientoPresupuestario(false);
		solicitud.setRenovacionIgualesCondiciones(false);
		solicitud.setComisionEspecialAlBroker(false);
		solicitud.setTieneInspeccion(false);
		solicitud.setDatosActualizados(false);
		solicitud.setCOR(false);
		solicitud.setDetalleExtrasVehiculo(false);
		solicitud.setFacturarMesSiguiente(false);
		solicitud.setAutoXAuto(false);
		solicitud.setEndosoBeneficiario(false);
		solicitud.setSegurosOriente(false);
		solicitud.setEsSumaAsegurada(false);

		//EN LA ENTIDAD VAN DATOS DEL CLIENTE
		QBEEntidad entidad = new QBEEntidad();
		entidad.setNombre(cliente.getNombres());
		entidad.setApellido(cliente.getApellidos());
		entidad.setCIRUC(cliente.getIdentificacion());
		entidad.setDireccion(dirCliente.getCallePrincipal() + " " + dirCliente.getNumero() + " " + dirCliente.getCalleSecundaria());
		entidad.setTelefonoCelular(cliente.getCelular());
		entidad.setTipoEntidad((short) 0);
		String tipoIdentificacion = "1";
		if (cliente.getTipoIdentificacion().getId().equals("3") || cliente.getTipoIdentificacion().getId().equals("4")) tipoIdentificacion = "2";
		entidad.setTipoPersona(tipoIdentificacion);

		if (cliente.getEntEnsurance() == null || cliente.getEntEnsurance().equals("")) entidad.setEsNuevo(true);
		else entidad.setEsNuevo(false);
		entidad.setEsDatosActualizados(false);
		entidad.setTelefonoDomicilio("");
		entidad.setTelefonoOficina("");
		entidad.setCorreoElectronico(cliente.getMail());
		entidad.setCorreoElectronicoOpcional("");
		entidad.setEmpresa("");

		QBEDeuda deuda = null;

		QBEDestinatario destinatario = new QBEDestinatario();
		destinatario.setEmpresa("");
		destinatario.setAtencionA("");
		destinatario.setDireccion("");
		destinatario.setCiudad("");
		destinatario.setTelefonos("");
		destinatario.setObservaciones("Enviado de documentación");

		//Datos que deben ser quemados:.
		destinatario.setTipo((short) 50); //Destinatario1

		QBEFianza fianza = new QBEFianza();
		fianza.setSector("");
		fianza.setMontoGarantia(new BigDecimal(0));
		fianza.setTiempoVigencia(0);
		fianza.setTasa(new BigDecimal(0));
		fianza.setIncondicionalrrevocable(false);
		fianza.setObjetoAsegurado("");
		fianza.setNumeroOficio("");

		QBEControlDocumentos doumentoVacio = new QBEControlDocumentos();
		List < QBEControlDocumentos > listaDocumentosVacios = new ArrayList < QBEControlDocumentos > ();
		listaDocumentosVacios.add(doumentoVacio);
		ArrayOfQBEControlDocumentos documentos = new ArrayOfQBEControlDocumentos();
		//documentos.setQBEControlDocumentos(listaDocumentosVacios);

		ArrayOfDocumentList aodl = new ArrayOfDocumentList();
		List < DocumentList > listaEnviar = new ArrayList < DocumentList > ();

		ArrayOfFileList arrayArchivos = new ArrayOfFileList();
		List < FileList > listaFileList = new ArrayList < FileList > ();
		DocumentoVisadoDAO dvDAO = new DocumentoVisadoDAO();
		TipoDocumentoDAO tdDAO = new TipoDocumentoDAO();

		for (DocumentList item: listaDocumentos) {
			FileList fl = new FileList();
			DocumentList aux = new DocumentList();
			aux.setDocumentID(item.getDocumentID());
			aux.setDocumentName(item.getDocumentName());
			TipoDocumento td = tdDAO.buscarPorNombre(item.getDocumentName());
			DocumentoVisado dv = new DocumentoVisado();
			if (!(td == null || td.getId() == null)) {
				if (dvDAO.buscarPorCotizacionTipoDocumento(cotizacion, td).size() > 0) 
					dv = dvDAO.buscarPorCotizacionTipoDocumento(cotizacion, td).get(0);
				else 
					if(dvDAO.buscarPorEntidadTipoDocumento(cliente, td).size() > 0)
						dv = dvDAO.buscarPorEntidadTipoDocumento(cliente, td).get(0);
				if (dv != null && dv.getId() != null) {
					fl.setFileBytes(dv.getContenido());
					fl.setFileName(td.getNombre()+"_"+dv.getNombre());
					aux.setCheckValue(true);
					listaFileList.add(fl);
				} else{
					if(documentosRequeridos.contains(item.getDocumentName()))
						throw new Exception("El documento: "+item.getDocumentName()+" es requerido para crear el tramite");
					aux.setCheckValue(false);
					}
			} else aux.setCheckValue(false);
			listaEnviar.add(aux);
		}
		
		

		arrayArchivos.setFileList(listaFileList);
		aodl.setDocumentList(listaEnviar);


		WorkFlowResult resultado = servicioSmartWorkSoap.createInstanceWF(token, username, WorkFlowType.VISADO, solicitud, entidad, documentos, deuda, destinatario, fianza, arrayArchivos, aodl);
		//WorkFlowResult resultado = servicioSmartWorkSoap.createInstanceWF(token, username,WorkFlowType.VISADO, solicitud, entidad, documentos, deuda, destinatario, fianza,documentos,arrayArchivos);
		//System.out.println("Error Mensaje: " + resultado.getErrorMessage());
		//System.out.println("Instance Number: " + resultado.getInstanceNumber());
		//System.out.println("WorkFlowId: " + resultado.getWorkFlowID());

		cotizacion.setNumeroTramite(resultado.getInstanceNumber());
		cotizacion=cotizacionTransaction.editar(cotizacion);

		retorno.put("ErrorMensaje", resultado.getErrorMessage());
		retorno.put("InstanceNumber", resultado.getInstanceNumber());
		retorno.put("WorkFlowId", resultado.getWorkFlowID());

		return retorno;
	}

	public String buscarGUIDPorNombre(String valor, List < DocumentList > arr) {
		String guid = "";
		valor = valor.toUpperCase().trim();
		for (DocumentList item: arr) {
			if (valor.contains(item.getDocumentName().toUpperCase().trim())) {
				guid = item.getDocumentID();
				break;
			}
		}

		return guid;
	}
}