package com.qbe.cotizador.servlets.producto.agricola;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import _174._13._39._181.ensurance.services.WSActualizarCrearEntidadesONBASE.WSActualizarCrearEntidadesONBASEProxy;

import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.cotizacion.EndosoBeneficiarioDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoXPuntoVentaDAO;
import com.qbe.cotizador.dao.entidad.AgenteDAO;
import com.qbe.cotizador.dao.entidad.BeneficiarioDAO;
import com.qbe.cotizador.dao.entidad.CantonDAO;
import com.qbe.cotizador.dao.entidad.CiudadDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.DireccionDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.ParroquiaDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.entidad.TipoDireccionDAO;
import com.qbe.cotizador.dao.entidad.UnidadNegocioDAO;
import com.qbe.cotizador.dao.entidad.ZonaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriAuditoriaCotizacionDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCanalDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriObjetoCotizacionDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParametroXCanalDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParametroXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParroquiaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCultivoDAO;
import com.qbe.cotizador.dao.producto.agricola.UsuariosOfflineDAO;
import com.qbe.cotizador.dao.seguridad.TipoVariableDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.Agente;
import com.qbe.cotizador.model.AgriAuditoriaCotizacion;
import com.qbe.cotizador.model.AgriCanal;
import com.qbe.cotizador.model.AgriObjetoCotizacion;
import com.qbe.cotizador.model.AgriParametroXCanal;
import com.qbe.cotizador.model.AgriParametroXPuntoVenta;
import com.qbe.cotizador.model.AgriParroquia;
import com.qbe.cotizador.model.AgriSucreAuditoria;
import com.qbe.cotizador.model.AgriTipoCultivo;
import com.qbe.cotizador.model.Beneficiario;
import com.qbe.cotizador.model.Provincia;
import com.qbe.cotizador.model.Canton;
import com.qbe.cotizador.model.Ciudad;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.CotizacionRespuesta;
import com.qbe.cotizador.model.Direccion;
import com.qbe.cotizador.model.EndosoBeneficiario;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.ProductoXPuntoVenta;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.SolicitudEmision;
import com.qbe.cotizador.model.TipoDireccion;
import com.qbe.cotizador.model.TipoVariable;
import com.qbe.cotizador.model.UnidadNegocio;
import com.qbe.cotizador.model.UsuariosOffline;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.model.Zona;
import com.qbe.cotizador.servicios.QBE.emisionAgricolaWS.WSEmisionAgricolaProxy;
import com.qbe.cotizador.servicios.QBE.ganadero.AseguradoOBJ;
import com.qbe.cotizador.transaction.cotizacion.CotizacionRespuestaTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.cotizacion.EndosoBeneficiarioTransaction;
import com.qbe.cotizador.transaction.cotizacion.SolicitudEmisionTransaction;
import com.qbe.cotizador.transaction.entidad.DireccionTransaction;
import com.qbe.cotizador.transaction.entidad.EntidadTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriAuditoriaCotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriObjetoCotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriSucreAuditoriaTransaction;
import com.qbe.cotizador.util.Utilitarios;
import com.tandi.entidad.dto.EntidadWSONBASE;

public class AgriEmisionPoliza {
	public static AgriResultadoEmision emitirPoliza(String cotizacionId) throws Exception{
		//Obtengo la cotizacion
		CotizacionDAO cotizacionDAO=new CotizacionDAO(); 
		ClienteDAO clienteDAO = new ClienteDAO();
		AgriObjetoCotizacionDAO objCotDAO = new AgriObjetoCotizacionDAO();
		AgriObjetoCotizacion objetoCot = new AgriObjetoCotizacion();
		CantonDAO cantDAO = new CantonDAO();
		CiudadDAO ciuDAO = new CiudadDAO();
		TipoDireccionDAO tipoDirecDAO = new TipoDireccionDAO();
		ZonaDAO zonaDAO = new ZonaDAO();
		DireccionTransaction direcTransaccion = new DireccionTransaction();
		Direccion nuevaDireccion = new Direccion();
		Date fechaActual = new Date();
		CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();
		CotizacionDetalle cotizacionDetalle= new CotizacionDetalle();
		AgriObjetoCotizacionDAO agriObjetoCotizacionDAO= new AgriObjetoCotizacionDAO();
		
		Cotizacion cotizacion=cotizacionDAO.buscarPorId(cotizacionId);
		
		/**CREACION O ACTUALIZACION DE LA DIRECCION DEL CLIENTE**/
		Cliente cliente = clienteDAO.buscarPorId(cotizacion.getClienteId().toString());
		List<CotizacionDetalle> detalle = cotizacion.getCotizacionDetalles();
		objetoCot = objCotDAO.buscarPorId(new BigInteger(detalle.get(0).getObjetoId()));
		
		
		ProvinciaDAO provinciaDAO = new ProvinciaDAO();
		Provincia provincia=provinciaDAO.buscarPorId(objetoCot.getProvinciaId().toString());
		Ciudad ciu = ciuDAO.buscarPorId(provincia.getCapitalId());
		
		TipoDireccion tipoDirec = tipoDirecDAO.buscarPorId("3");// Por defecto Tipo de direccion (cobro)
		Zona zona = zonaDAO.buscarPorId("2");// Por defecto zona rural ya que es agricola
		nuevaDireccion.setEntidad(cliente.getEntidad());
		nuevaDireccion.setCiudad(ciu);
		nuevaDireccion.setEntidad(cliente.getEntidad());
		nuevaDireccion.setTipoDireccion(tipoDirec);
		nuevaDireccion.setZona(zona);
		nuevaDireccion.setCallePrincipal(objetoCot.getDireccionSiembra());
		nuevaDireccion.setNumero("S/N");
		nuevaDireccion.setEsCobro(true);
		nuevaDireccion.setCalleSecundaria(objetoCot.getDireccionSiembra());
		
		int ContadordireccionesCliente=cliente.getEntidad().getDireccions().size();//a veces no se guardan bien las direcciones se verifica		
		if (ContadordireccionesCliente==0 )//Si no tiene Direccion //Tomamos la direccion del cliente como la direccion del cultivo, los canales no envian esa informacion
			nuevaDireccion = direcTransaccion.crear(nuevaDireccion);//creo la direccion		
		else
		{
			try{
				nuevaDireccion.setId(cliente.getEntidad().getDireccions().get(ContadordireccionesCliente-1).getId());
			}catch(Exception e)
			{
				System.out.println("no se pudo actualizar la direccion, se la creo");
			}
			nuevaDireccion = direcTransaccion.editar(nuevaDireccion);//actualizo la Cotizacion		
		}
		/***FIN DE PROCESO DE DIRECCION DEL CLIENTE***/
		
		/**CREACION O ACTUALIZACION DE LA DIRECCION DEL ASEGURADO**/
		EntidadDAO entidadAseguradoDAO=new EntidadDAO();
		Entidad entidadAsegurado= entidadAseguradoDAO.buscarPorId(cotizacion.getAsegurado().getId());
		nuevaDireccion.setEntidad(entidadAsegurado);
		int Contadordirecciones=cotizacion.getAsegurado().getDireccions().size();//a veces no se guardan bien las direcciones se verifica		
		if (Contadordirecciones==0 )//Si no tiene Direccion //Tomamos la direccion del cliente como la direccion del cultivo, los canales no envian esa informacion
			nuevaDireccion = direcTransaccion.crear(nuevaDireccion);//creo la direccion		
		else
		{
			try{
				nuevaDireccion.setId(cliente.getEntidad().getDireccions().get(ContadordireccionesCliente-1).getId());
			}catch(Exception e)
			{
				System.out.println("no se pudo actualizar la direccion, se la creo");
			}
			nuevaDireccion = direcTransaccion.editar(nuevaDireccion);//actualizo la Cotizacion		
		}	
		/**FIN DEL ASEGURADO**/
		
		/***PROCESO DE CREACION DE LA ENTIDAD EN ENSURANCE***/
		String resultadoCrearEntidad="";//crearActualizarEntidad(cotizacion.getAsegurado());
		if(!resultadoCrearEntidad.equals("")){
			Entidad entidad=cotizacion.getAsegurado();
			entidad.setEntEnsurance(resultadoCrearEntidad);
			EntidadTransaction entidadTrans=new EntidadTransaction();
			entidadTrans.editar(entidad);//Creo la entidad en ensurance y actualizo el id en la entidad del cotizador
		}
		/**FIN DE CREACION ENSURANCE**/
		
		/****TODO: PROCESO DE EMISION INTEGRACION ENSURANCE***/
		AgriResultadoEmision nuevoResultado=new AgriResultadoEmision();//Objeto que se llenara de lo que venga de ensurance
		if(cotizacion.getId()!=null){
			AgriSucreAuditoria agriSucreAuditoria = new AgriSucreAuditoria();
			agriSucreAuditoria.setCanal("EMISION");
			agriSucreAuditoria.setTramite(cotizacion.getNumeroTramite());
			agriSucreAuditoria.setFecha(fechaActual);
			agriSucreAuditoria.setEstado("Correcto");
			//Ensamblo el xml para el envi� a generrar la p�liza
			nuevoResultado=generarXML(cotizacion);
			if(nuevoResultado.isGeneradoXMLCorrectamente())
			{
				try{
					/***LLAMADA AL WS DE EMISION***/
					WSEmisionAgricolaProxy emisionAgricola=new WSEmisionAgricolaProxy();
					String resultado=emisionAgricola.emisionPoliza(nuevoResultado.getXmlEmision(), "f2rtiUdv2kjOgaCx");
					agriSucreAuditoria.setObjeto(resultado);
					AgriSucreAuditoriaTransaction agriSucreAuditoriaTransaction= new AgriSucreAuditoriaTransaction();
					agriSucreAuditoriaTransaction.crear(agriSucreAuditoria);//AUDITORIA INTERNA
					
					// EVALDEZ agregado para guardar logs de los XML que se
					// envian desde agricola
					try {
						SolicitudEmisionTransaction solicitudEmisionTransaction = new SolicitudEmisionTransaction();
						SolicitudEmision se = new SolicitudEmision();
						se.setCotizacionId(cotizacion.getId());
						se.setXml(nuevoResultado.getXmlEmision());
						se.setRespuesta(resultado);
						// System.out.println(emisionValor);
						solicitudEmisionTransaction.crear(se);
					} catch (Exception e) {

					}
					
					if(!resultado.equals("")){
						if (!resultado.contains("ERROR")&& !resultado.contains("Exception")&& !resultado.contains("TOKEN") && !resultado.contains("Fallo")&& !resultado.contains("null")&& resultado.contains("::")){						
							try{
								
								CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
								//formato esperado 11014757286001|001-003-003708513::2016-07-28 16:33:52.0
								String[] ids=resultado.split(" ");//separamos las fechas
								if(ids.length!=0){
									String[] ids2=ids[0].split("::");
									if(ids2.length!=0){
										String [] factura = ids2[0].replace("|"," ").split(" ");
										CotizacionRespuesta cotizacionRes = new CotizacionRespuesta();
										CotizacionRespuestaTransaction cotiResTransaction = new CotizacionRespuestaTransaction();
										EstadoDAO estadoDAO = new EstadoDAO();
										//si ensurance me devuelve el caracter ya esta emitida
										//formato correcto
										//11009488060525|001-003-003698285::2016-07-21 09:43:02.0
										//formato de ya emitida
										//1 |001-003-003698285::2016-07-21 09:43:02.0
										if(factura[0].trim().equals("EXISTE"))
											throw new Exception("EXISTE Poliza  emitida Anteriormente");
										Estado estado = estadoDAO.buscarPorNombre("Emitido", "Cotizacion");
										if (!cotizacion.getEstado().getNombre().equals(estado.getId())){
											System.out.println("factura[0] :"+factura[0].trim()+ " factura[1] :"+factura[1].trim());
											cotizacion.setNumeroFactura(factura[1].trim());
											cotizacion.setEstado(estado);
											cotizacion = cotizacionTransaction.editar(cotizacion);///TODO:tualiza estado tabla cotizacion
											
											//guardamos los otros datos de la factura
											cotizacionDetalle=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).get(0);
											AgriObjetoCotizacion agriObjetoCotizacion = agriObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizacionDetalle.getObjetoId()));
											agriObjetoCotizacion.setPolizaNumero("ID:"+factura[0].trim()+ " factura N.-:"+factura[1].trim());
											try{
												String fechaEnsurance=ids2[1].trim();
												SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
												Date date = formatter.parse(fechaEnsurance);
												agriObjetoCotizacion.setPolizaFechaVencimiento(date);
											}catch(Exception e){
												System.out.println("no se pudo guardar la fecha de ensurance");
											}											
											AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction = new AgriObjetoCotizacionTransaction();
											agriObjetoCotizacionTransaction.editar(agriObjetoCotizacion);
											try{											
												/**PROCESO DE CREACION DEL DETALLE DE LA COTIZACION ENDOSO***/
												AgriCotizacionDetalleProcesos agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
												agriCotizacionDetalleProcesos.creardetalleCotizacion(cotizacion, "Emision");
												/**FIN DE REGISTRO DE ENDOSO**/
											}catch(Exception e){
												e.printStackTrace();
											}
											//TODO: Se graba en cotizacion respuesta 
											cotizacionRes.setCotizacionId(new BigInteger(cotizacion.getId()));
											cotizacionRes.setPolizaId(factura[0]);
											cotizacionRes.setFacturaNumero(cotizacion.getNumeroFactura());
											cotizacionRes.setFechaEmision(ids2[1].trim());
											cotizacionRes = cotiResTransaction.crear(cotizacionRes);
											nuevoResultado.setFactura(factura[1].trim());											
											nuevoResultado.setEmitidoCorrectamente(true);
											return nuevoResultado;
										}
										else {
											agriSucreAuditoria.setObjeto("La cotizaci�n fue emitida anteriormente");
											agriSucreAuditoriaTransaction= new AgriSucreAuditoriaTransaction();
											agriSucreAuditoriaTransaction.crear(agriSucreAuditoria);//AUDITORIA INTERNA
											
											nuevoResultado.setEmitidoCorrectamente(false);
											nuevoResultado.setMensaje("La cotizaci�n fue emitida anteriormente");
											return nuevoResultado;
										}
									}
								}
							}catch (Exception e) {
								agriSucreAuditoria.setObjeto(e.getMessage()+"///"+e.getCause());
								agriSucreAuditoriaTransaction= new AgriSucreAuditoriaTransaction();
								agriSucreAuditoriaTransaction.crear(agriSucreAuditoria);//AUDITORIA INTERNA
								e.printStackTrace();
								nuevoResultado.setEmitidoCorrectamente(false);
								nuevoResultado.setMensaje(" Error al leer respuesta de ensurance "+e.getMessage());
								return nuevoResultado;
							}
						}
						else {//SI HAY UN MENSAJE DE ENSURANCE
							if (resultado.contains("EXISTE")){
								
								if (resultado.contains("TOKEN")){
									nuevoResultado.setEmitidoCorrectamente(false);
									nuevoResultado.setMensaje("Cotizacion Emitida Anteriormente"+resultado);
									
									agriSucreAuditoria.setObjeto("Cotizacion Emitida Anteriormente"+resultado);
									agriSucreAuditoriaTransaction= new AgriSucreAuditoriaTransaction();
									agriSucreAuditoriaTransaction.crear(agriSucreAuditoria);//AUDITORIA INTERNA
									
									return nuevoResultado;									
								}else{
									nuevoResultado.setEmitidoCorrectamente(false);
									nuevoResultado.setMensaje("Error de autenticacion de Token :"+resultado);
									
									agriSucreAuditoria.setObjeto("Error de autenticacion de Token :"+resultado);
									agriSucreAuditoriaTransaction= new AgriSucreAuditoriaTransaction();
									agriSucreAuditoriaTransaction.crear(agriSucreAuditoria);//AUDITORIA INTERNA
									
									return nuevoResultado;
								}
							}
							else{
								if (resultado.contains("TOKEN")){
									nuevoResultado.setEmitidoCorrectamente(false);
									nuevoResultado.setMensaje("Cotizacion Emitida Anteriormente"+resultado);
									agriSucreAuditoria.setObjeto("Cotizacion Emitida Anteriormente"+resultado);
									agriSucreAuditoriaTransaction= new AgriSucreAuditoriaTransaction();
									agriSucreAuditoriaTransaction.crear(agriSucreAuditoria);//AUDITORIA INTERNA
									
									return nuevoResultado;									
								}else{
									nuevoResultado.setEmitidoCorrectamente(false);
									nuevoResultado.setMensaje("Error en el proceso de emisi�n de Ensurance. "+resultado);
									
									agriSucreAuditoria.setObjeto("Error en el proceso de emisi�n de Ensurance. "+resultado);
									agriSucreAuditoriaTransaction= new AgriSucreAuditoriaTransaction();
									agriSucreAuditoriaTransaction.crear(agriSucreAuditoria);//AUDITORIA INTERNA
									
									return nuevoResultado;
								}
							}
						}
					}
					else{//SI ENSURANCE NO DEVUELVE NADA						
							nuevoResultado.setEmitidoCorrectamente(false);
							nuevoResultado.setMensaje("Error en el proceso de emisi�n de Ensurance");
							agriSucreAuditoria.setObjeto("Error en el proceso de emisi�n de Ensurance");
							agriSucreAuditoriaTransaction= new AgriSucreAuditoriaTransaction();
							agriSucreAuditoriaTransaction.crear(agriSucreAuditoria);//AUDITORIA INTERNA
							
							return nuevoResultado;						
					}						
				}
				catch(Exception ex){//SI HAY UN ERROR GENERAL	
					agriSucreAuditoria.setObjeto(ex.getMessage()+"///"+ex.getCause());
					AgriSucreAuditoriaTransaction agriSucreAuditoriaTransaction= new AgriSucreAuditoriaTransaction();
					agriSucreAuditoriaTransaction.crear(agriSucreAuditoria);//AUDITORIA INTERNA
					
					if(ex.getMessage().contains("EXISTE")){
						nuevoResultado.setEmitidoCorrectamente(false);
						nuevoResultado.setMensaje("Cotizacion Emitida Anteriormente");
						ex.printStackTrace();
						return nuevoResultado;
					}else{
						ex.printStackTrace();
						nuevoResultado.setEmitidoCorrectamente(false);
						nuevoResultado.setMensaje(" Error en el proceso de emisi�n de Ensurance"+ ex.getMessage());
						return nuevoResultado;
					}
					
				}
			}
			else
				return nuevoResultado;
		}
		else
		{
			nuevoResultado.setEmitidoCorrectamente(false);
			nuevoResultado.setMensaje("No existe una cotizaci�n con ese numero");
			return nuevoResultado;
		}
		return nuevoResultado;
	}

	private static String crearActualizarEntidad(Entidad entidad) throws Exception{
		//Objetos
		AgriSucreAuditoriaTransaction procesoAuditoria= new AgriSucreAuditoriaTransaction();
		EntidadWSONBASE entidadEnsurance=new EntidadWSONBASE();
		Date fecha=new Date();
		VariableSistemaDAO vsDAO=new VariableSistemaDAO();
		
		String variableControl="";//variable de auditoria
		String ciudad="";
		String direccion="";
		String provincia="";		
		AgriSucreAuditoria auditoria = new AgriSucreAuditoria();		
		auditoria.setTramite(""+entidad.getIdentificacion());
		java.util.Date date2= new java.util.Date();
		java.sql.Timestamp sq2 = new java.sql.Timestamp(date2.getTime());
		auditoria.setFecha(sq2);
		auditoria.setCanal("EMISION");
		int numeroDirecciones=entidad.getDireccions().size();
		if(numeroDirecciones!=0){
			if (entidad.getDireccions().get(numeroDirecciones-1).getCiudad()!=null){
				ciudad=entidad.getDireccions().get(numeroDirecciones-1).getCiudad().getId();
				provincia=entidad.getDireccions().get(numeroDirecciones-1).getCiudad().getProvincia().getId();
				direccion= entidad.getDireccions().get(numeroDirecciones-1).getCiudad().getProvincia().getNombre()+"/"+ entidad.getDireccions().get(numeroDirecciones-1).getCiudad().getNombre();
			}
		}else{//si no tiene direccion se envia como si fuese creada en Matriz
			direccion="Pichincha/Quito";
			provincia="17";
			ciudad="1201001";
		}
		/***Servicio web de creacion de usuario en Ensurance***/
		WSActualizarCrearEntidadesONBASEProxy wsEntidadEnsurance=new WSActualizarCrearEntidadesONBASEProxy();
		try{
			//Armado de Objeto Envio Ensurance y log de nuestro Envio
			if(entidad.getApellidos()==null){
				entidadEnsurance.setApellido(" ");
				entidadEnsurance.setApellidoRepresentanteLegal("");
				variableControl=variableControl+"Apellido: ";
			}else{
				entidadEnsurance.setApellido(entidad.getApellidos());
				entidadEnsurance.setApellidoRepresentanteLegal(entidad.getApellidos());
				variableControl=variableControl+"Apellido: "+entidad.getApellidos();
			}
			try{
				entidadEnsurance.setEmailPrincipal(entidad.getMail());
				variableControl=variableControl+" setEmailPrincipal: "+entidad.getMail();
			}catch(Exception e){
				System.out.println("No se encontro el correo electronico");
				variableControl=variableControl+" setEmailPrincipal: "+"No se encontro el correo electronico";
			}
			entidadEnsurance.setActividadEconomica("-1");
			
			entidadEnsurance.setCiudad(ciudad);
			entidadEnsurance.setConoceTuCliente("");
			entidadEnsurance.setEntidadId("no");
			entidadEnsurance.setEstadoCivilId("29");//quemado ya que no tenemos esa informacion desde los clientes
			entidadEnsurance.setFechaActualiza(fecha.toLocaleString());
			entidadEnsurance.setFechaConstitucion("01/01/1990");
			entidadEnsurance.setFechaNacimiento("01/01/1990");//mandamos porque no tenemos la fecha desde los clientes
			entidadEnsurance.setGenero("M");
			entidadEnsurance.setIdentificacion(entidad.getIdentificacion().trim());
			entidadEnsurance.setNombre(entidad.getNombres());
			entidadEnsurance.setNombrecompleto(entidad.getNombreCompleto());
			entidadEnsurance.setNombreDireccion(direccion.trim());
			entidadEnsurance.setNombreRepresentanteLegal(entidad.getNombres());
			entidadEnsurance.setPais("6815744");
			entidadEnsurance.setProvincia(provincia);
			entidadEnsurance.setTelefonoCelular1(entidad.getCelular());
			if(entidad.getTelefono()==null)
				entidadEnsurance.setTelefonoConvencional(entidad.getTelefono());
			else
				entidadEnsurance.setTelefonoConvencional("022316189");
			entidadEnsurance.setTelefonoOtro("");
			entidadEnsurance.setTipoEmpleado("INDEPENDIENTE");
			entidadEnsurance.setTitulo("");
			entidadEnsurance.setTipoCliente("PERSONA NATURAL");
			entidadEnsurance.setPuerto("8084");
			
			variableControl=variableControl+" setActividadEconomica: -1 ";		
			variableControl=variableControl+" setApellidoRepresentanteLegal: "+entidad.getApellidos();		
			variableControl=variableControl+" setCiudad: "+ciudad;		
			variableControl=variableControl+" setConoceTuCliente: ";	
			variableControl=variableControl+" setEntidadId: ";		
			variableControl=variableControl+" setEstadoCivilId: 29";		
			variableControl=variableControl+" setFechaActualiza: "+fecha.toLocaleString();		
			variableControl=variableControl+" setFechaConstitucion: null";		
			variableControl=variableControl+" setFechaNacimiento: 01/01/1990";		
			variableControl=variableControl+" setGenero: M";		
			variableControl=variableControl+" setIdentificacion: "+entidad.getIdentificacion().trim();		
			variableControl=variableControl+" setNombre: "+entidad.getNombres();
			variableControl=variableControl+" setNombrecompleto: "+entidad.getNombreCompleto();		
			variableControl=variableControl+" setNombreDireccion: "+direccion.trim();
			variableControl=variableControl+" setNombreRepresentanteLegal: "+entidad.getNombres();		
			variableControl=variableControl+" setPais: 6815744 ";
			variableControl=variableControl+" setProvincia: "+provincia;
			variableControl=variableControl+" setTelefonoCelular1: "+entidad.getCelular();		
			variableControl=variableControl+" setTelefonoConvencional: "+entidad.getTelefono();		
			variableControl=variableControl+" setTelefonoOtro: null";		
			variableControl=variableControl+" setTipoEmpleado: INDEPENDIENTE ";		
			variableControl=variableControl+" setTitulo: ";		
			variableControl=variableControl+" setTipoCliente: PERSONA NATURAL ";		
			variableControl=variableControl+" setPuerto: 8084 ";
		
			//tomamos los valores desde variables del sistema VARIABLES MIXTAS
			VariableSistema  vs=vsDAO.buscarPorNombre("USUARIO_EMI_AGRICOLA");
			entidadEnsurance.setUsuario(vs.getValor().trim());   
			variableControl=variableControl+" setUsuario:" +vs.getValor();
			vs=vsDAO.buscarPorNombre("CONTRASENIA_EMI_AGRICOLA");
			entidadEnsurance.setPassword(vs.getValor().trim());
			variableControl=variableControl+" setPassword: "+vs.getValor();
		
			//creamos Auditoria de la informacion que enviamos
			auditoria.setEstado("OK");
			variableControl=variableControl+" ||||| OBJETO ";
			auditoria.setObjeto(variableControl);
			procesoAuditoria.crear(auditoria);		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception("Uno de los parametros del cliente no es correcto "+ex.getMessage());
		}
		
		/***COMUNICACION CON EL WS DE ENSURANCE***/
		String result="";
		try
		{
			result=wsEntidadEnsurance.crearActualizarEntidad(entidadEnsurance);
			auditoria.setEstado("OK");
			variableControl=variableControl+" ||||| RESPUESTA ENSURANCE: result "+result;
			auditoria.setObjeto(variableControl);
			procesoAuditoria.crear(auditoria);
		}
		catch(Exception ex){
			auditoria.setEstado("ERROR");
			variableControl=variableControl+"||Error: "+ex.getMessage()+"||"+ex.getCause();
			ex.printStackTrace();
			variableControl=variableControl+" ||||| RESPUESTA ENSURANCE: result "+result;
			auditoria.setObjeto(variableControl);
			procesoAuditoria.crear(auditoria);
			throw new Exception("Error al crear la entidad ensurance :"+ex);
			
		}		
		return result;
	}
	
	private static AgriResultadoEmision generarXML(Cotizacion cotizacion){
		//ARMAMOS LOS OBJETOS
		AgriResultadoEmision resultado=new AgriResultadoEmision();
		AgriSucreAuditoria agriSucreAuditoria = new AgriSucreAuditoria();
		ClienteDAO clienteDAO=new ClienteDAO();
		AgenteDAO agenteDAO=new AgenteDAO();
		AgriParametroXPuntoVentaDAO parametroDAO=new AgriParametroXPuntoVentaDAO();
		PuntoVentaDAO puntoVentaDAO=new PuntoVentaDAO();
		ProductoXPuntoVentaDAO productoXPVDAO=new ProductoXPuntoVentaDAO();
		AgriObjetoCotizacionDAO objetoCotizacionDAO=new AgriObjetoCotizacionDAO();
		AgriTipoCultivoDAO tipoCultivoDAO=new AgriTipoCultivoDAO();
		EntidadDAO entidadDAO=new EntidadDAO();
		EndosoBeneficiarioDAO endosoBeneficiarioDAO=new EndosoBeneficiarioDAO();
		CotizacionDetalle cotizacionDetalle = new CotizacionDetalle();
		Provincia  provincia = new Provincia();
		ProvinciaDAO provinciaDAO= new ProvinciaDAO();
		AgriCanalDAO agriCanalDAO=new AgriCanalDAO();
		AgriParametroXCanalDAO apcDAO=new AgriParametroXCanalDAO();
		AgriAuditoriaCotizacion  agriAuditoriaCotizacion = new AgriAuditoriaCotizacion();
	    AgriAuditoriaCotizacionDAO agriAuditoriaCotizacionDAO = new AgriAuditoriaCotizacionDAO();
	    CotizacionTransaction  cotizacionTransaction = new CotizacionTransaction();
	    TipoVariableDAO tipoVariableDao = new TipoVariableDAO();
	    VariableSistemaDAO variableDAO= new VariableSistemaDAO();
		
		//COMENSAMOS A ARMAR EL XML :)
		try{
			if(cotizacion.getAsegurado()==null)
			{
				resultado.setGeneradoXMLCorrectamente(false);
				resultado.setMensaje("La cotizaci�n no tiene un asegurado");
				return resultado;
			}
			Agente agente=agenteDAO.buscarPorId(cotizacion.getAgenteId().toString());
			AgriParametroXPuntoVenta parametroXPV=parametroDAO.buscarPorPuntoVentaId(new BigInteger(cotizacion.getPuntoVenta().getId()));
			PuntoVenta puntoVenta=puntoVentaDAO.buscarPorId(cotizacion.getPuntoVenta().getId());
			ProductoXPuntoVenta productoXPV=productoXPVDAO.buscarPorId(cotizacion.getProductoXPuntoVentaId().toString());
			CotizacionDetalleDAO cotizacionDetalleDAO= new CotizacionDetalleDAO();
			cotizacionDetalle=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).get(0);
			AgriObjetoCotizacion objetoCotizacion=objetoCotizacionDAO.buscarPorId(new BigInteger(cotizacionDetalle.getObjetoId()));
			AgriTipoCultivo tipoCultivo=tipoCultivoDAO.BuscarPorId(objetoCotizacion.getTipoCultivoId());
			Cliente cliente=clienteDAO.buscarPorId(cotizacion.getClienteId().toString());
		
			//CAMBIO EMISION 12 % 
			
			/**FIN DE VIGENCIA DE LEY DE SOLIDARIDAD**/
			/* 1) Verificamos si se emite antes de 1 de junio del 2015.
			 * 2) Si la cotizaci�n se emite despues del 1 de junio del 2015 se verifica cual fue su fecha de creacion,
			 *  si la fecha de creacion es menor al 1 de junio se realiza un recalculo del iva, caso contrario no se hace nada ya que estara calculado con iva 12%
			 * 3) Si la fecha de emision es durante o despues del 1 de junio se envia bandera de zona afectada como falsa.
			 */
			
			Date fechaEmision= new Date();
			SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date fechaAplicaIva = dt.parse("31/05/2017 23:59:00");
			
			/*TODO: VERIFICAMOS SI SE ENCUENTRA EN ZONA DE TERREMOTO*/
			String zonaAfectada="0";
						
			if(fechaEmision.after(fechaAplicaIva))	{
				zonaAfectada="0";
				//verifico la fecha de creacion de la cotizacion
				Date fechaCreacionCotizacion=cotizacion.getFechaElaboracion();
				if(!fechaCreacionCotizacion.after(fechaAplicaIva)){
					//recalculamos el iva a 12%
					recalculoIVa(cotizacion,objetoCotizacion);
				}
				
			}else{
				provincia= provinciaDAO.buscarPorId(objetoCotizacion.getProvinciaId().toString());
				if(provincia.getNombre().trim().equals("MANABI")||provincia.getNombre().trim().equals("ESMERALDAS")){
					int canal=Integer.parseInt(parametroXPV.getCanalId().toString());
					if(canal==1)
						zonaAfectada="0";
					else
						zonaAfectada="1";	
				}
			}
			
			
			
			//Valido que existe un tipo de cultivo
			if(tipoCultivo.getTipoCultivoId()==null){
				resultado.setGeneradoXMLCorrectamente(false);
				resultado.setMensaje("No existe el tipo de cultivo indicado en la cotizaci�n");
				return resultado;
			}
			else{
				if(tipoCultivo.getCodEnsurance().equals("") || tipoCultivo.getCodEnsurance()==null){
					resultado.setGeneradoXMLCorrectamente(false);
					resultado.setMensaje("El tipo de cultivo no tiene un c�digo v�lido en Ensurance");
					return resultado;
				}
			}
			//TODO: VERIFICAMOS LA CONFIGURACION DE LA PLANTILLA Y EL CONTENEDOR
			//Determino la plantilla del tipo de cultivo para la emision, Agricola cada canal y cultivo tienen ids diferentes
			String plantillaId="";
			String contenedorId="";
			if(parametroXPV.getCanalId()!=null){
				AgriParametroXCanal parametroXCanal = apcDAO.obtenerPorCanalTipoCultivo(parametroXPV.getPuntoVentaId(), tipoCultivo.getTipoCultivoId());
				if(parametroXCanal.getParametroCanalId()!=null){
					if(parametroXCanal.getPlantillaId()!=null){
						plantillaId=parametroXCanal.getPlantillaId();
						contenedorId=parametroXCanal.getContenedorEnsuranceId();						
					}
					else{
						resultado.setGeneradoXMLCorrectamente(false);
						resultado.setMensaje("No existe configurado una plantilla para este canal.");
						return resultado;
					}						
				}
				else{
					resultado.setGeneradoXMLCorrectamente(false);
					resultado.setMensaje("No existe la configuraci�n de paramateros para este canal.");
					return resultado;
				}
					
			}
			else{
				resultado.setGeneradoXMLCorrectamente(false);
				resultado.setMensaje("No existe la configuraci�n de paramateros para este canal.");
				return resultado;
			}
			//VERIFICAMOS EL ENDOSOBENEFICIARIO		
			EndosoBeneficiario endosoBeneficiario = endosoBeneficiarioDAO.buscarPorCotizacion(cotizacion);	//si no tiene endoso beneficiario lo creamos	
			if (endosoBeneficiario == null){
				endosoBeneficiario = new EndosoBeneficiario();
				EndosoBeneficiarioTransaction endosoBeneficiarioTransaction = new EndosoBeneficiarioTransaction();
				BeneficiarioDAO beneficiarioDAO = new BeneficiarioDAO();
				Beneficiario beneficiario = beneficiarioDAO.buscarPorId(parametroXPV.getBeneficiarioId());
				endosoBeneficiario.setBeneficiario(beneficiario);
				endosoBeneficiario.setCotizacion(cotizacion);
				endosoBeneficiario.setMonto(cotizacion.getSumaAseguradaTotal());
				endosoBeneficiario=endosoBeneficiarioTransaction.crear(endosoBeneficiario);
			}
			//PROCESO DE ARMADO DE FECHAS	
			agriAuditoriaCotizacion = agriAuditoriaCotizacionDAO.BuscarPorCotizacinId(new BigInteger(cotizacion.getId()));
			Calendar c = Calendar.getInstance(); ;
			c.setTime(objetoCotizacion.getFechaSiembra()); 
			c.add(Calendar.DATE, tipoCultivo.getVigenciaDias());
			Date vigenciaHasta = c.getTime();// vigencia hasta para la emision
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");		
			String fechaInicioCultivo = df.format(objetoCotizacion.getFechaSiembra());		
			String fechaFinalCultivo = df.format(vigenciaHasta);		
			long fechaInicioPoliza = objetoCotizacion.getFechaSiembra().getTime()/1000;		
			long fechaFinalPoliza = vigenciaHasta.getTime()/1000;
		
			/****TODO: CONFIGURACIONES GLOBALES TOMADOS POR MEDIO DE VARIABLES DEL SISTEMA****/
			/*PROCESO DE CALCULO DE COMPONENTES*/			
	        TipoVariable tipoVariable = tipoVariableDao.buscarPorId("3");
	        List<VariableSistema> variablesistema = variableDAO.buscarTipoVariable(tipoVariable);
	        String CorreoElectronico="";//LO TOMAMOS DEL PARAMETRO POR PUNTO DE VENTA
	       if(parametroXPV.getEmailPuntoVenta()==null ||parametroXPV.getEmailPuntoVenta().equals(""))
	        	CorreoElectronico="";
	        else
	        	CorreoElectronico=parametroXPV.getEmailPuntoVenta();
	        String login="";
	        String password="";
	        String tipoRiesgo="";
	        String claseRiesgo="";
	        String tipoItem="";
	        
	        //RECORRO LAS VARIABLES DEL SISTEMA TRAYENDO SOLO LAS QUE NECESITO       
	        if(variablesistema.size() > 0) {
	        	for(VariableSistema variable : variablesistema) {
	        		if(variable.getNombre().equals("USUARIO_EMI_AGRICOLA"))
	        			login=variable.getValor();//usuario con el que se logue para emision
	        		if(variable.getNombre().equals("CONTRASENIA_EMI_AGRICOLA"))
	        			password=variable.getValor();//contrasenia con la que se loguea para la emision
	        		if(variable.getNombre().equals("TIPO_RIESGO_AGRICOLA"))
	        			tipoRiesgo=variable.getValor();//TIPO_RIESGO_AGRICOLA para la emision
	        		if(variable.getNombre().equals("CLASE_RIESGO_AGRICOLA"))
	        			claseRiesgo=variable.getValor();//CLASE_RIESGO_AGRICOLA para la emision
	        		if(variable.getNombre().equals("TIPO_ITEM_AGRICOLA"))
	        			tipoItem=variable.getValor();//TIPO_ITEM_AGRICOLA para la emision
	        	}
	        }
	        /**VARIABLES EN PREPARACION DE LA EMISION**/	        
	        String agricultorTecnificado="";        
	        if(objetoCotizacion.getAgricultorTecnificado())
	        	agricultorTecnificado="Si";
	        else
	        	agricultorTecnificado="No";
	        String EndosoBeneficiarioId="";
	        if(endosoBeneficiario!=null){
				Entidad entidadBeneficiario=entidadDAO.buscarEntidadPorIdEnsurance(endosoBeneficiario.getBeneficiario().getCodigoEnsurance().toString());
				EndosoBeneficiarioId=entidadBeneficiario.getEntEnsurance();
			}else
				EndosoBeneficiarioId="0";
	        
	        String cantonSBS="";
	        String parroquiaSBS="";
	        String ProvinciaSBS=provincia.getCodigoSbs();
	        String ParroquiaNombre="";
	        
	        /**PARAMETRIZACION DE LOS LUGARES DE SIEMBRA***/
	        try{
		        if(objetoCotizacion.getAgriParroquiaId()!=null){
		        	AgriParroquiaDAO agriParroquiaDAO = new AgriParroquiaDAO();
			        AgriParroquia agriParroquia=agriParroquiaDAO.BuscarPorId(Integer.parseInt(objetoCotizacion.getAgriParroquiaId()));
			        if(agriParroquia.getParroquiaNombre()==null){
			        	ParroquiaDAO parroquiaDAO = new ParroquiaDAO();
			        	com.qbe.cotizador.model.Parroquia parroquia=parroquiaDAO.buscarPorId(objetoCotizacion.getAgriParroquiaId());
			        	parroquiaSBS=parroquia.getId();
			        	ParroquiaNombre=parroquia.getNombre();
			        }else{
			        	parroquiaSBS=agriParroquia.getCodigoEnsurance();
			        	ParroquiaNombre=agriParroquia.getParroquiaNombre();
			        }
		        }else{
		        	ParroquiaDAO parroquiaDAO = new ParroquiaDAO();
		        	com.qbe.cotizador.model.Parroquia parroquia=parroquiaDAO.buscarPorId(""+objetoCotizacion.getParroquiaId());
		        	parroquiaSBS=parroquia.getId();
		        	ParroquiaNombre=parroquia.getNombre();
		        }
	        }catch(Exception e){
	        	throw new Exception("No se puede emitir la cotizacion no tiene parroquia");
	        }
	        
	        
	        
	        CantonDAO cantonDAO = new CantonDAO();
	        Canton canton=cantonDAO.buscarPorId(objetoCotizacion.getCantonId().toString());
	        cantonSBS=canton.getId();
	        
	        if(cantonSBS.length()<=3){
	        	cantonSBS="0"+cantonSBS;
	        }
	        /**FIN DE LUGARES DE SIEMBRA**/
	        
	        
	         /**CASO ESPECIAL**/
	        String unidadNegocio="";
	        if(puntoVenta.getNombre().equals("COOPROGRESO")){
	        	//TODO: OJO SOLO COOPROGRESO Peticion david garzon para distingir entre masivos y agricola ganadero
	        	//encontramos el usuario que relizo la cotizacion.
	        	String nombreEjecutivo=objetoCotizacion.getUsuarioOffline();
	        	UsuariosOffline usuariosOffline = new UsuariosOffline();
	    		UsuariosOfflineDAO usuariosOfflineDAO = new UsuariosOfflineDAO();
	    		usuariosOffline=usuariosOfflineDAO.BuscarNombre(nombreEjecutivo);	    		
	    		//Tomamos la unidad de negocio
	    		UnidadNegocioDAO unidadNegocioDAO = new UnidadNegocioDAO();
	    		UnidadNegocio negocio=unidadNegocioDAO.buscarPorId(usuariosOffline.getUnidadNegocio());
	    		unidadNegocio=negocio.getUnEnsurance();
	    		
	        }else{
	        	//TODO: Para canales sucre, banca comunal, Pronaca todo se emite como agricola ganadero
	        	unidadNegocio=productoXPV.getUnidadNegocio().getUnEnsurance();
	        }
            /***DESPUES DE TODITO ESO VIENE EL ARMADO DEL XML***/    
        
	        StringBuilder xml=new StringBuilder("<superObjetoXML>")
	        		.append("<detallesPoliza>")
	        			.append("<identificador>").append(objetoCotizacion.getIdCotizacion2()).append("</identificador>")
		        		.append("<login>").append(login).append("</login>")
		        		.append("<pass>").append(password).append("</pass>")
		        		.append("<agenteId>").append(agente.getAgeEnsurance()).append("</agenteId>")
		        		.append("<porcentajeComision>").append(cotizacion.getPorcentajeComision()).append("</porcentajeComision>")
		        		.append("<valorprima>").append(cotizacion.getPrimaNetaTotal()).append("</valorprima>")
		        		.append("<canalId>").append(parametroXPV.getCanalId()).append("</canalId>")
		        		.append("<contenedorId>").append(contenedorId).append("</contenedorId>")
		        		.append("<loteImpresion>").append((puntoVenta.getNombre())).append("</loteImpresion>")
		        		.append("<puntoVentaId>").append(puntoVenta.getPtoEnsurance()).append("</puntoVentaId>")
		        		.append("<tipoRiesgoId>").append(tipoRiesgo).append("</tipoRiesgoId>")
		        		.append("<claseRiesgoId>").append(claseRiesgo).append("</claseRiesgoId>")
		        		.append("<tipoItemId>").append(tipoItem).append("</tipoItemId>")
		        		.append("<monedaId>11141120</monedaId>")
		        		.append("<plantillaId>").append(plantillaId).append("</plantillaId>")
		        		.append("<firmaDigitalId>1</firmaDigitalId>")
		        		.append("<usuarioActualiza>10822106152960</usuarioActualiza>")
		        		.append("<ramoId>7274513</ramoId>")
		        		.append("<mnemotecnicoRamo>SA</mnemotecnicoRamo>")
		        		.append("<unidadNegocioId>").append(unidadNegocio).append("</unidadNegocioId>")//agricola-ganadero o como masivos
		        		.append("<unidadProduccionId>").append(productoXPV.getUnidadProduccion().getUpEnsurance()).append("</unidadProduccionId>")//agricola
		        		.append("<numeroPoliza>").append(objetoCotizacion.getIdCotizacion2()).append("</numeroPoliza>")//NUMERO DE COTIZACION 2
		        		.append("<codigoIntegracion>998000008</codigoIntegracion>")
		        		.append("<fechaAprobacion>").append(Utilitarios.actualDate()).append("</fechaAprobacion>")
						.append("<codigoEntidadFinanciera>").append(EndosoBeneficiarioId).append("</codigoEntidadFinanciera>")						
						.append("<vigenciaDesde>").append(fechaInicioPoliza).append("</vigenciaDesde>")
						.append("<valorAsegurado>").append(cotizacion.getSumaAseguradaTotal()).append("</valorAsegurado>")
						.append("<vigenciaHasta>").append(fechaFinalPoliza).append("</vigenciaHasta>")
						.append("<sucursalId>").append(cotizacion.getPuntoVenta().getSucursal().getSucEnsurance()).append("</sucursalId>")
						.append("<esZonaAfectada>").append(zonaAfectada).append("</esZonaAfectada>")
					.append("</detallesPoliza>")
					.append("<loteCultivo>")
						.append("<loteCultivoId>-1</loteCultivoId>")
						.append("<nombre>").append(StringEscapeUtils.escapeXml(""+ParroquiaNombre+" - "+objetoCotizacion.getDireccionSiembra())).append("</nombre>")
						.append("<valorAsegurado>").append(cotizacion.getSumaAseguradaTotal()).append("</valorAsegurado>")
						.append("<numeroHectareas>").append(objetoCotizacion.getHectareasLote()).append("</numeroHectareas>")
						.append("<valorPorHectarea>").append(objetoCotizacion.getCostoProduccion()).append("</valorPorHectarea>")
						.append("<gpsLoteX>").append(objetoCotizacion.getLatitud()).append("</gpsLoteX>")
						.append("<gpsLoteY>").append(objetoCotizacion.getLongitud()).append("</gpsLoteY>")
						.append("<tasa>").append(cotizacion.getTasaProductoValor()).append("</tasa>")
						.append("<tipoCultivoId>").append(tipoCultivo.getCodEnsurance()).append("</tipoCultivoId>")
						.append("<diasVigencia>").append(tipoCultivo.getVigenciaDias()).append("</diasVigencia>")
						.append("<inicioVigenciaCultivo>").append(fechaInicioCultivo).append("</inicioVigenciaCultivo>")
						.append("<finVigenciaCultivo>").append(fechaFinalCultivo).append("</finVigenciaCultivo>")
						.append("<variedad>").append(objetoCotizacion.getVariedad()).append("</variedad>")
						.append("<numeroHectareasAsegurables>").append(objetoCotizacion.getHectareasAsegurables()).append("</numeroHectareasAsegurables>")
						.append("<esTecnificado>").append(agricultorTecnificado).append("</esTecnificado>")
						.append("<fechaTentativaSiembra>").append(objetoCotizacion.getFechaSiembra()).append("</fechaTentativaSiembra>")
						.append("<propiedadId>-1</propiedadId>")
					.append("</loteCultivo>")
					.append("<cliente>")
						.append("<id>no</id>")
						.append("<entidadId>")
						.append(cliente.getEntidad().getEntEnsurance()==null||cliente.getEntidad().getEntEnsurance().trim().equals("0")?"no":cliente.getEntidad().getEntEnsurance()).append("</entidadId>")
						.append("<nombres>").append(StringEscapeUtils.escapeXml(cliente.getEntidad().getNombres())).append("</nombres>")
						.append("<apellidos>").append(StringEscapeUtils.escapeXml(cliente.getEntidad().getApellidos()==null?" ":cliente.getEntidad().getApellidos())).append("</apellidos>")
						.append("<tipoIdentificacion>")
						.append(cliente.getEntidad().getTipoIdentificacion().getId().equals("1") ? "c":cliente.getEntidad().getTipoIdentificacion().equals("2") ? "p":"r")
						.append("</tipoIdentificacion>")
						.append("<tipoEntidadId>").append(cliente.getEntidad().getTipoEntidad().getId()).append("</tipoEntidadId>")
						.append("<identificacion>").append(cliente.getEntidad().getIdentificacion()).append("</identificacion>")
	        			.append("<email>").append(CorreoElectronico).append("</email>")		
	        			.append("<genero>f</genero>")
	        			.append("<esEmpresa>")
	        			.append(cliente.getEntidad().getTipoIdentificacion().getId().equals("1") ? "false":cliente.getEntidad().getTipoIdentificacion().getId().equals("2") ? "false":cliente.getEntidad().getTipoIdentificacion().getId().equals("3")?"false":"true")
	        			.append("</esEmpresa>")
	        			.append("<DireccionDTO><direccion>")
		        			.append("<paisId>6815744</paisId>")		
		        			.append("<provinciaId>").append(ProvinciaSBS).append("</provinciaId>")
		        			.append("<ciudadId>").append(cliente.getEntidad().getDireccions().get(0).getCiudad().getId()).append("</ciudadId>")
							.append("<cantonId>").append(cantonSBS).append("</cantonId>")
							.append("<parroquiaId>").append(parroquiaSBS).append("</parroquiaId>")
							.append("<direccion>")		
								.append( StringEscapeUtils.escapeXml(objetoCotizacion.getDireccionSiembra()))
							.append("</direccion>")
							.append("<telefono>").append((cliente.getEntidad().getTelefono()!=null && !cliente.getEntidad().getTelefono().equals("")) ? cliente.getEntidad().getTelefono():"-").append("</telefono>")
						.append("</direccion></DireccionDTO>")
					.append("</cliente>")
					.append("<asegurado>")
						.append("<id>no</id>")
						.append("<entidadId>").append(cotizacion.getAsegurado().getEntEnsurance()).append("</entidadId>")
						.append("<nombres>").append(StringEscapeUtils.escapeXml(cotizacion.getAsegurado().getNombres())).append("</nombres>")
						.append("<apellidos>").append(StringEscapeUtils.escapeXml(cotizacion.getAsegurado().getApellidos())).append("</apellidos>")
						.append("<tipoIdentificacion>")
						.append(cotizacion.getAsegurado().getTipoIdentificacion().getId()=="1" ? "c":cliente.getEntidad().getTipoIdentificacion().getId()=="2" ? "p":"r")
						.append("</tipoIdentificacion>")
						.append("<tipoEntidadId>").append(cotizacion.getAsegurado().getTipoEntidad().getId()).append("</tipoEntidadId>")
						.append("<identificacion>").append(cotizacion.getAsegurado().getIdentificacion()).append("</identificacion>")
						.append("<genero>f</genero>")
						.append("<esEmpresa>")
						.append(cotizacion.getAsegurado().getTipoIdentificacion().getId()=="1" ? "false":cotizacion.getAsegurado().getTipoIdentificacion().getId()=="2" ? "false":cotizacion.getAsegurado().getTipoIdentificacion().getId()=="3"?"false":"true")
						.append("</esEmpresa>")
						.append("<DireccionDTO><direccion>")
							.append("<paisId>6815744</paisId>")
							.append("<provinciaId>").append(ProvinciaSBS).append("</provinciaId>")
							.append("<ciudadId>").append(cliente.getEntidad().getDireccions().get(0).getCiudad().getId()).append("</ciudadId>")
					        .append("<cantonId>").append(cantonSBS).append("</cantonId>")
							.append("<parroquiaId>").append(parroquiaSBS).append("</parroquiaId>")
							.append("<direccion>")		
								.append(StringEscapeUtils.escapeXml( objetoCotizacion.getDireccionSiembra()))
							.append("</direccion>")
							.append("<telefono>").append((cotizacion.getAsegurado().getTelefono()!=null && !cotizacion.getAsegurado().getTelefono().equals("")) ? cotizacion.getAsegurado().getTelefono():"-").append("</telefono>")
						.append("</direccion></DireccionDTO>")
					.append("</asegurado>");
		if(endosoBeneficiario!=null){
			Entidad entidadBeneficiario=entidadDAO.buscarEntidadPorIdEnsurance(endosoBeneficiario.getBeneficiario().getCodigoEnsurance().toString());
			xml.append("<beneficiarios>")
					.append("<id>no</id>")
					.append("<entidadId>").append(endosoBeneficiario.getBeneficiario().getCodigoEnsurance()).append("</entidadId>")
					.append("<nombres>").append(StringEscapeUtils.escapeXml(endosoBeneficiario.getBeneficiario().getNombre())).append("</nombres>")
					.append("<apellidos>").append(StringEscapeUtils.escapeXml(endosoBeneficiario.getBeneficiario().getNombre())).append("</apellidos>")
					.append("<tipoIdentificacion>")
					.append(entidadBeneficiario.getTipoIdentificacion().getId()=="1" ? "c":entidadBeneficiario.getTipoIdentificacion().getId()=="2" ? "p":"r")
					.append("</tipoIdentificacion>")
					.append("<tipoEntidadId>").append(entidadBeneficiario.getTipoEntidad().getId()).append("</tipoEntidadId>")
					.append("<identificacion>").append(entidadBeneficiario.getIdentificacion()).append("</identificacion>")
					.append("<esEmpresa>")
					.append(entidadBeneficiario.getTipoIdentificacion().getId()=="1" ? "false":entidadBeneficiario.getTipoIdentificacion().getId()=="2" ? "false":entidadBeneficiario.getTipoIdentificacion().getId()=="3"?"false":"true")
					.append("</esEmpresa>")
					.append("<DireccionDTO>")
					.append("<direccion>")
					.append("<paisId>-1</paisId>")
					.append("<provinciaId>-1</provinciaId>")
					.append("<cantonId>-1</cantonId>")
					.append("<parroquiaId>-1</parroquiaId>")
					.append("<ciudadId>-1</ciudadId>")
					.append("<direccion>-1</direccion>")
					.append("<telefono>-1</telefono>")
					.append("</direccion>")
					.append("</DireccionDTO>")
			.append("</beneficiarios>");
		}
		else{
			xml.append("<beneficiarios>")
				.append("<id>no</id>")
				.append("<entidadId>-1</entidadId>")
				.append("<nombres>-1</nombres>")
				.append("<apellidos>-1</apellidos>")
				.append("<tipoIdentificacion>-1</tipoIdentificacion>")
				.append("<tipoEntidadId>-1</tipoEntidadId>")
				.append("<identificacion>-1</identificacion>")
				.append("<esEmpresa>-1</esEmpresa>")
				.append("<DireccionDTO>")
				.append("<direccion>")
				.append("<paisId>-1</paisId>")
				.append("<provinciaId>-1</provinciaId>")
				.append("<cantonId>-1</cantonId>")
				.append("<parroquiaId>-1</parroquiaId>")
				.append("<ciudadId>-1</ciudadId>")
				.append("<direccion>-1</direccion>")
				.append("<telefono>-1</telefono>")
				.append("</direccion>")
				.append("</DireccionDTO>")
				.append("</beneficiarios>");
		}
		xml.append("</superObjetoXML>");
		
		agriSucreAuditoria.setCanal("EMISION");
		agriSucreAuditoria.setTramite(cotizacion.getNumeroTramite());
		agriSucreAuditoria.setObjeto(xml.toString());
		Date fechaActual = new Date();
		agriSucreAuditoria.setFecha(fechaActual);
		agriSucreAuditoria.setEstado("Correcto");
		AgriSucreAuditoriaTransaction agriSucreAuditoriaTransaction= new AgriSucreAuditoriaTransaction();
		agriSucreAuditoriaTransaction.crear(agriSucreAuditoria);
		
		resultado.setGeneradoXMLCorrectamente(true);
		resultado.setXmlEmision(xml.toString());
		return resultado;
		}
		catch(Exception e){	
			e.printStackTrace();
			resultado.setMensaje("Error: "+e.getMessage());
			agriSucreAuditoria.setCanal("EMISION");
			agriSucreAuditoria.setTramite(cotizacion.getNumeroTramite());
			agriSucreAuditoria.setObjeto("ERROR:"+e.getMessage());
			Date fechaActual = new Date();
			agriSucreAuditoria.setFecha(fechaActual);
			agriSucreAuditoria.setEstado("Correcto");
			AgriSucreAuditoriaTransaction agriSucreAuditoriaTransaction= new AgriSucreAuditoriaTransaction();
			agriSucreAuditoriaTransaction.crear(agriSucreAuditoria);
			return resultado;
		}
	}
	
	//proceso de recalculo de iva 12% de cotizaciones anteriores al 1 de junio 2017
	private static void recalculoIVa(Cotizacion cotizacion,AgriObjetoCotizacion agriObjetoCotizacion){
		
		VariableSistemaDAO variableSistemaDAO= new VariableSistemaDAO();
		VariableSistema variableSistema=variableSistemaDAO.buscarPorNombre("IVA");
		
		CotizacionTransaction cotizacionTransaction= new CotizacionTransaction();
		AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction= new AgriObjetoCotizacionTransaction();
		
		String observacion=agriObjetoCotizacion.getObservacion()+"|| Recalculo Iva Emision: Iva Anterior "+cotizacion.getImpIva()+" Prima Total Anterior "+cotizacion.getTotalFactura();
		
		double primaNeta=Double.parseDouble(cotizacion.getPrimaNetaTotal());
		double segBancos=cotizacion.getImpSuperBancos();
		double segCampesino=cotizacion.getImpSeguroCampesino();
		double segDerechoEmision=cotizacion.getImpDerechoEmision();
		double segSumaPrimaImp=primaNeta+segBancos+segCampesino+segDerechoEmision;
		double segIva=segSumaPrimaImp*(Double.parseDouble(variableSistema.getValor()))/100;
		double primaTotal=primaNeta+segBancos+segCampesino+segDerechoEmision+segIva;
		
		//Redondeos
		BigDecimal a = new BigDecimal(""+segIva);
		BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
		double valor = Double.parseDouble(roundOff.toString());
		cotizacion.setImpIva(valor);
		
		a = new BigDecimal(""+primaTotal);
		roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
		valor = Double.parseDouble(roundOff.toString());		
		cotizacion.setTotalFactura(valor);
		
		agriObjetoCotizacion.setObservacion(observacion);
		
		cotizacionTransaction.editar(cotizacion);
		agriObjetoCotizacionTransaction.editar(agriObjetoCotizacion);
		
	}
}
