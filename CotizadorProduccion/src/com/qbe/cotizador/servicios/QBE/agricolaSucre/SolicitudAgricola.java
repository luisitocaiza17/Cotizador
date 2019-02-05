package com.qbe.cotizador.servicios.QBE.agricolaSucre;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoXPuntoVentaDAO;
import com.qbe.cotizador.dao.cotizacion.TipoObjetoDAO;
import com.qbe.cotizador.dao.cotizacion.UplaDAO;
import com.qbe.cotizador.dao.cotizacion.VigenciaPolizaDAO;
import com.qbe.cotizador.dao.entidad.ActividadEconomicaDAO;
import com.qbe.cotizador.dao.entidad.CantonDAO;
import com.qbe.cotizador.dao.entidad.CiudadDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.DireccionDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.entidad.TipoDireccionDAO;
import com.qbe.cotizador.dao.entidad.TipoEntidadDAO;
import com.qbe.cotizador.dao.entidad.TipoIdentificacionDAO;
import com.qbe.cotizador.dao.entidad.UsuarioDAO;
import com.qbe.cotizador.dao.entidad.ZonaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriAuditoriaCotizacionDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCicloDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCotizacionEndosoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCotizacionMaxDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriObjetoCotizacionDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParametroXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParroquiaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriReglaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriSubcanalDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriSucreDetalleDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCalculoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCultivoDAO;
import com.qbe.cotizador.dao.producto.agricola.CotizacionAprobacionDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.dao.seguridad.TipoVariableDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.ActividadEconomica;
import com.qbe.cotizador.model.AgriAuditoriaCotizacion;
import com.qbe.cotizador.model.AgriCiclo;
import com.qbe.cotizador.model.AgriCotizacionAprobacion;
import com.qbe.cotizador.model.AgriCotizacionEndoso;
import com.qbe.cotizador.model.AgriCotizacionMax;
import com.qbe.cotizador.model.AgriIndemnizacion;
import com.qbe.cotizador.model.AgriObjetoCotizacion;
import com.qbe.cotizador.model.AgriParametroXPuntoVenta;
import com.qbe.cotizador.model.AgriParroquia;
import com.qbe.cotizador.model.AgriRegla;
import com.qbe.cotizador.model.AgriSubcanal;
import com.qbe.cotizador.model.AgriSucreAuditoria;
import com.qbe.cotizador.model.AgriSucreDetalle;
import com.qbe.cotizador.model.AgriTipoCalculo;
import com.qbe.cotizador.model.AgriTipoCultivo;
import com.qbe.cotizador.model.Canton;
import com.qbe.cotizador.model.Ciudad;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.Direccion;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.ProductoXPuntoVenta;
import com.qbe.cotizador.model.Provincia;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.TipoIdentificacion;
import com.qbe.cotizador.model.TipoVariable;
import com.qbe.cotizador.model.Upla;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.servlets.producto.agricola.AgriCotizacionDetalleProcesos;
import com.qbe.cotizador.servlets.producto.agricola.AgriSucreNotificacionErrores;
import com.qbe.cotizador.transaction.cotizacion.CotizacionDetalleTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.cotizacion.UplaTransaction;
import com.qbe.cotizador.transaction.entidad.ClienteTransaction;
import com.qbe.cotizador.transaction.entidad.DireccionTransaction;
import com.qbe.cotizador.transaction.entidad.EntidadTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriAuditoriaCotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriCotizacionEndosoTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriIndemnizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriObjetoCotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriSucreAuditoriaTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriSucreDetalleTransaction;
import com.qbe.cotizador.util.Utilitarios;

public class SolicitudAgricola {

	public RespuestaUN01DTO registrarSolicitud(SolicitudUN01DTO in0) {
		RespuestaUN01DTO respuesta = new RespuestaUN01DTO();
		AgriSucreAuditoria auditoria = new AgriSucreAuditoria();
		AgriSucreAuditoriaTransaction procesoAuditoria = new AgriSucreAuditoriaTransaction();
		auditoria.setTramite("" + in0.getNumeroResolucion());
		String DatosRecibidos = " SERVICIO WEB-AGRICOLA "
				+ " NumeroResolucion "
				+ in0.getNumeroResolucion()
				+ " Inversion "
				+ in0.getInversion()
				+ " CostoProduccion "
				+ in0.getCostoProduccion()
				+ " CostoMantenimientoCultivo "
				+ in0.getCostoMantenimientoCultivo()
				+ " Canton "
				+ in0.getCanton()				
				+ " Provincia "
				+ in0.getProvincia()
				+ "ActividadEconomica "
				+ in0.getActividadEconomica()
				+ " Apellido1 "
				+ in0.getApellido1()
				+ " Apellido2 "
				+ in0.getApellido2()
				+ " Cedula "
				+ in0.getCedula()
				+ " CodigoAgenciaOSucursal "
				+ in0.getCodigoAgenciaOSucursal()
				+ " CodigoFacilitador "
				+ in0.getCodigoFacilitador()
				+ " CodigoVariedadCultivo "
				+ in0.getCodigoVariedadCultivo()
				+ " CondicionPredio "
				+ in0.getCondicionPredio()
				+ " CondicionPredioOtra "
				+ in0.getCondicionPredioOtra()
				+ " CostoEstablecimientoCultivo "
				+ in0.getCostoEstablecimientoCultivo()
				+ " DireccionNacimiento "
				+ in0.getDireccionNacimiento()
				+ " DisponeRiego "
				+ in0.getDisponeRiego()
				+ " EstadoCivil "
				+ in0.getEstadoCivil()
				+ " EmailContacto "
				+ in0.getEmailContacto()
				+ " EstadoSolicitud "
				+ in0.getEstadoSolicitud()
				+ " FechaAprobacion "
				+ in0.getFechaAprobacion()
				+ " FechaSiembra "
				+ in0.getFechaSiembra()
				+ " FechaVencimientoCredito "
				+ in0.getFechaVencimientoCredito()
				+ " FuenteIngreso "
				+ in0.getFuenteIngreso()
				+ " Genero "
				+ in0.getGenero()
				+ " IngresoAnual "
				+ in0.getIngresoAnual()	
				+ " Latitud "
				+ in0.getLatitud()
				+ " Longitud "
				+ in0.getLongitud()
				+ " Lote "
				+ in0.getLote()
				+ " LugarNacimiento "
				+ in0.getLugarNacimiento()
				+ " MontoRecomendado "
				+ in0.getMontoRecomendado()
				+ " Nacionalidad "
				+ in0.getNacionalidad()
				+ " NombreContacto "
				+ in0.getNombreContacto()
				+ " Nombres "
				+ in0.getNombres()
				+ " NumHectAseguradas "
				+ in0.getNumHectAseguradas()
				+ " NumHectFinanciadas "
				+ in0.getNumHectFinanciadas()
				+ " OtroRiego "
				+ in0.getOtroRiego()
				+ " PaqueteTecnologico "
				+ in0.getPaqueteTecnologico()
				+ " Parroquia "
				+ in0.getParroquia()
				+ " Pep "
				+ in0.getPep()
				+ " Recinto "
				+ in0.getRecinto()
				+ " Referencia "
				+ in0.getReferencia()
				+ " Telfreferencia "
				+ in0.getTelfreferencia()
				+ " TerrenoTecnificado "
				+ in0.getTerrenoTecnificado()
				+ " TipoCanal "
				+ in0.getTipoCanal()
				+ " TipoRiego "
				+ in0.getTipoRiego()
				+ " TipoSemilla "
				+ in0.getTipoSemilla()
				+ " ValorSubsidio "
				+ in0.getValorSubsidio()
				+ " Variedad "
				+ in0.getVariedad()
				+ " SubCanal "
				+ in0.getCodigoSubCanal();
		
		auditoria.setObjeto(DatosRecibidos);
		java.util.Date date2 = new java.util.Date();
		java.sql.Timestamp sq2 = new java.sql.Timestamp(date2.getTime());
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		auditoria.setFecha(sq2);
		auditoria.setCanal("SUCRE");
		auditoria.setEstado("Ingreso");
		try {
			/* proceso de auditoria almacenamiento de inf. en tabla Agri_Sucre_Auditoria */
			try {
				procesoAuditoria.crear(auditoria);
			} catch (Exception e) {
				e.printStackTrace();
			}
			/* Fin proceso de auditoria */
			if (in0.getNumeroResolucion() == null)
				throw new Exception(
						"Campo Numero de resolucion vacio, el proceso no se puede continuar");
			
			Cotizacion ExisteCotizacion = new Cotizacion();
			CotizacionDAO BusquedaCotizacion = new CotizacionDAO();
			
			//variables globales
			String Observacion = "";
			String NumeroTramite = in0.getNumeroResolucion().trim();
			java.util.Date date = new java.util.Date();
			java.sql.Timestamp sq = new java.sql.Timestamp(date.getTime());
			
			ExisteCotizacion = BusquedaCotizacion
					.buscarPorNumeroTramite(NumeroTramite);
			
			if (ExisteCotizacion.getId() == null) {
				/* PROCESO DE INSTANCIAS CREADO */
				EntidadDAO entidadDAO = new EntidadDAO();
				ClienteDAO clienteDAO = new ClienteDAO();
				TipoEntidadDAO tipoEntidadDAO = new TipoEntidadDAO();
				PuntoVentaDAO pvDAO = new PuntoVentaDAO();
				VigenciaPolizaDAO vpDAO = new VigenciaPolizaDAO();
				GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
				ProductoXPuntoVentaDAO productoPorPuntoVentaDAO = new ProductoXPuntoVentaDAO();
				TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				EstadoDAO estadoDAO = new EstadoDAO();
				UplaDAO uplaCliente = new UplaDAO();
				Upla registroExiste = new Upla();
				UplaTransaction uplaProceso = new UplaTransaction();
				TipoIdentificacionDAO tipoIDentificacion = new TipoIdentificacionDAO();
				CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
				CotizacionDetalleTransaction cotizacionDetalleTransaction = new CotizacionDetalleTransaction();
				EntidadTransaction entidadTransaction = new EntidadTransaction();
				ClienteTransaction clienteTransaction = new ClienteTransaction();
				AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction = new AgriObjetoCotizacionTransaction();
				Entidad entidad = new Entidad();
				Cliente cliente = new Cliente();
				Upla registro = new Upla();
				AgriTipoCultivoDAO cultivo = new AgriTipoCultivoDAO();
				AgriTipoCultivo elCutlitvo = new AgriTipoCultivo();
				Cotizacion cotizacion = new Cotizacion();

				/* Proceso de validacion de campos obligatorios */
				if (in0.getTipoCanal() == null)
					throw new Exception(
							"Error Campo Obligatorio TipoCanal vacio");
				if (in0.getCodigoFacilitador() == null)
					throw new Exception(
							"Error Campo Obligatorio CodigoFacilitador vacio");
				if (in0.getCodigoAgenciaOSucursal() == null)
					throw new Exception(
							"Error Campo Obligatorio CodigoAgenciaOSucursal vacio");
				if (in0.getNumeroResolucion() == null)
					throw new Exception(
							"Error Campo Obligatorio NumeroResolucion vacio");
				if (in0.getEstadoSolicitud() == null)
					throw new Exception(
							"Error Campo Obligatorio EstadoSolicitud vacio");
				if (in0.getCedula() == null)
					throw new Exception("Error Campo Obligatorio Cedula vacio");
				if (in0.getApellido1() == null)
					throw new Exception(
							"Error Campo Obligatorio Apellido vacio");
				if (in0.getApellido2() == null)
					throw new Exception(
							"Error Campo Obligatorio Apellido2 vacio");
				if (in0.getNombres() == null)
					throw new Exception(
							"Error Campo Obligatorio Nombres vacio");
				if (in0.getGenero() == null)
					throw new Exception("Error Campo Obligatorio Genero vacio");
				if (in0.getFechaNacimiento() == null)
					throw new Exception(
							"Error Campo Obligatorio FechaNacimiento vacio");
				if (in0.getFechaAprobacion() == null)
					throw new Exception(
							"Error Campo Obligatorio FechaAprobacion vacio");
				if (in0.getFuenteIngreso() == null)
					throw new Exception(
							"Error Campo Obligatorio FuenteIngreso vacio");
				if (in0.getNombreContacto() == null)
					throw new Exception(
							"Error Campo Obligatorio NombreContacto vacio");
				if (in0.getEmailContacto() == null)
					throw new Exception(
							"Error Campo Obligatorio EmailContacto vacio");
				if (in0.getFechaVencimientoCredito() == null)
					throw new Exception(
							"Error Campo Obligatorio FechaVencimientoCredito vacio");
				if (in0.getProvincia() == null)
					throw new Exception(
							"Error Campo Obligatorio Provincia vacio");
				if (in0.getCanton() == null)
					throw new Exception("Error Campo Obligatorio Canton vacio");
				if (in0.getParroquia() == null)
					throw new Exception(
							"Error Campo Obligatorio Parroquia vacio");
				if (in0.getRecinto() == null)
					throw new Exception(
							"Error Campo Obligatorio Recinto vacio");
				if (in0.getReferencia() == null)
					throw new Exception(
							"Error Campo Obligatorio Referencia vacio");
				if (in0.getLote() == null)
					throw new Exception("Error Campos Obligatorio Lote vacio");
				if (in0.getCondicionPredio() == null)
					throw new Exception(
							"Error Campo Obligatorio CondicionPredio vacio");
				if (in0.getLongitud() == null)
					throw new Exception(
							"Error Campo Obligatorio Longitud vacio");
				if (in0.getDisponeRiego() == null)
					throw new Exception(
							"Error Campo Obligatorio DisponeRiego vacio");
				if (in0.getLatitud() == null)
					throw new Exception(
							"Error Campo Obligatorio Latitud vacio");
				if (in0.getTerrenoTecnificado() == null)
					throw new Exception(
							"Error Campo Obligatorio TerrenoTecnificado vacio");
				if (in0.getCostoProduccion() == null)
					throw new Exception(
							"Error Campo Obligatorio CostoProduccion vacio");
				if (in0.getFechaSiembra() == null)
					throw new Exception(
							"Error Campo Obligatorio FechaSiembra vacio");
				if (in0.getMontoRecomendado() == null)
					throw new Exception(
							"Error Campo Obligatorio MontoRecomendado vacio");
				if (in0.getTipoSemilla() == null)
					throw new Exception(
							"Error Campo Obligatorio TipoSemilla vacio");
				if (in0.getValorSubsidio() == null)
					throw new Exception(
							"Error Campo Obligatorio ValorSubsidio vacio");
				if (in0.getEstadoCivil() == null)
					throw new Exception(
							"Error Campo Obligatorio EstadoCivil vacio");
				if (in0.getNacionalidad() == null)
					throw new Exception(
							"Error Campo Obligatorio Nacionalidad vacio");
				if (in0.getLugarNacimiento() == null)
					throw new Exception(
							"Error Campo Obligatorio LugarNacimiento vacio");
				if (in0.getDireccionNacimiento() == null)
					throw new Exception(
							"Error Campo Obligatorio DireccionNacimiento vacio");
				if (in0.getActividadEconomica() == null)
					throw new Exception(
							"Error Campo Obligatorio ActividadEconomica vacio");
				if (in0.getIngresoAnual() == null)
					throw new Exception(
							"Error Campo Obligatorio IngresoAnual vacio");
				if (in0.getPep() == null)
					throw new Exception("Error Campo Obligatorio Pep vacio");
				if (("" + in0.getNumHectFinanciadas()).equals("null"))
					throw new Exception(
							"Error Campo Obligatorio NumHectFinanciadas vacio");
				if (("" + in0.getNumHectAseguradas()).equals("null"))
					throw new Exception(
							"Error Campo Obligatorio NumHectAseguradas vacio");
				else
					if (("" + in0.getNumHectAseguradas()).equals("0")||("" + in0.getNumHectAseguradas()).equals("0.0")
							||("" + in0.getNumHectAseguradas()).equals("0.00")
							||in0.getNumHectAseguradas()==0||in0.getNumHectAseguradas()==0.0)
						throw new Exception(
								"Error el numero de hectareas aseguradas no pueden ser 0 imposible continuar");
					
				if (("" + in0.getInversion()).equals("null"))
					throw new Exception(
							"Error Campos Obligatorio Inversion(Codigo tipo Cultivo) vacio");
				
				if(in0.getNumHectAseguradas()==0||("" + in0.getNumHectAseguradas()).equals("0"))
					throw new Exception(
							"Error el numero de hectareas aseguradas no pueden ser 0 imposible continuar");
				
				if (in0.getTipoCanal() == null)
					throw new Exception(
							"Error Campo Obligatorio TipoCanal vacio");
				if(in0.getTelfreferencia().length()>12){
					throw new Exception(
							"Telefono no valido"+in0.getTelfreferencia());
				}
				
				//enviaban las fechas solo con 2 numeros, se puso el filtro para que envien en el formato correcto
				String AnioSiembra=in0.getFechaSiembra();
				String[] elementosFecha = AnioSiembra.split("/");
				int comprobacionAnio=elementosFecha[2].length();
							
				if(comprobacionAnio !=4)
					throw new Exception("Fecha de siembra no valida "+in0.getFechaSiembra()+" no valida, debe estar en el formato dd/MM/yyyy");
				
				
				String EntidadId = "";
				String NumeroCedula = in0.getCedula( ).trim();
				//verificamos si la entidad existe para actualizarla � crearla
				try {
					entidad = entidadDAO
							.buscarEntidadPorIdentificacion(NumeroCedula);
					EntidadId = entidad.getId();
				} catch (Exception e) {
					EntidadId = "";
				}
				/* PROCESO DE CREACION DE LA ENTIDAD */
				String nombre = "";
				String Apellidos = "";
				entidad.setIdentificacion(NumeroCedula);
				
				String identificacionCliente=NumeroCedula;
				TipoIdentificacion identificacion = new TipoIdentificacion();
				
				if(identificacionCliente.length()>13)
					 identificacion = tipoIDentificacion.buscarPorId("2");
				else{
					if(identificacionCliente.length()==13){
						identificacion = tipoIDentificacion.buscarPorId("3");
						String caracterNumerico=""+identificacionCliente.charAt(2);
						if(caracterNumerico.equals("9")){
							identificacion = tipoIDentificacion.buscarPorId("4");
						}
					}
					else{
						if(identificacionCliente.length()==10){
							identificacion = tipoIDentificacion.buscarPorId("1");
						}else{
							throw new Exception("Identificacion Invalida");
						}
					}
				}
				
				entidad.setTipoIdentificacion(identificacion);
				entidad.setTipoEntidad(tipoEntidadDAO.buscarPorId("1"));
				nombre = in0.getNombres().trim();
				Apellidos = " " + in0.getApellido1().trim();
				Apellidos = Apellidos + " " + in0.getApellido2();
				entidad.setApellidos(Apellidos);
				entidad.setNombres(nombre);
				entidad.setNombreCompleto(nombre + " " + Apellidos);
				entidad.setMail("" + in0.getEmailContacto().trim());
				entidad.setCelular("" + in0.getCelreferencia().trim());
				try{
					entidad.setTelefono("" + in0.getTelfreferencia().trim());					
				}
				catch (Exception e) {
					//numero vacio
				}
				if (EntidadId == null || EntidadId.equals("")) {
					entidad = entidadTransaction.crear(entidad);
				}else{
					entidad.setId(EntidadId);
					entidad = entidadTransaction.editar(entidad);
				}
				
				if(entidad.getId()==null)
					throw new Exception(
							"No se pudo crear la entidad");
				
				/*PROCESO DE CREACION DEL CLIENTE */
				cliente = clienteDAO.buscarPorEntidadId(entidad);
				
				ActividadEconomica actividad = new ActividadEconomica();
				ActividadEconomicaDAO actividadDAO = new ActividadEconomicaDAO();
				actividad = actividadDAO.buscarPorNombre("Ninguno");
				Cliente clienteNuevo = new Cliente();
				clienteNuevo.setEntidad(entidad);
				clienteNuevo.setActividadEconomica(actividad);
				clienteNuevo.setActivo(true);
				
				if (cliente.getEntidad() == null) {
					cliente = clienteTransaction.crear(clienteNuevo);
				}else{
					clienteNuevo.setId(cliente.getId());
					cliente = clienteTransaction.editar(clienteNuevo);
				}
				if(cliente.getId()==null)
					throw new Exception(
							"No se pudo crear el cliente");
				
				/*CREACION DE LA DIRECCION*/
				
				Direccion direccion=new Direccion();
				DireccionDAO direccionDAO=new DireccionDAO();
				DireccionTransaction direccionTransaction = new DireccionTransaction();
				ProvinciaDAO laProvincia = new ProvinciaDAO();
				Provincia provincia = new Provincia();
				CantonDAO elCanton = new CantonDAO();
				Canton canton = new Canton();
				TipoDireccionDAO tipoDireccionDAO = new TipoDireccionDAO();
				ZonaDAO zonaDAO = new ZonaDAO();
				AgriParroquiaDAO laParroquia = new AgriParroquiaDAO();
				AgriParroquia parroquia = new AgriParroquia();
				
				
								
				if(entidad.getId()!=null){
					int numeroDirecciones=direccionDAO.buscarCobroPorEntidadId(entidad).size();
					if(numeroDirecciones>0)
						direccion=direccionDAO.buscarCobroPorEntidadId(entidad).get(numeroDirecciones-1);
				}
				//hallamos la provincia en base al codigo que llega
				String CodigoSBSProvincia = in0.getProvincia().trim();
				
				if (CodigoSBSProvincia.length() == 1)
					CodigoSBSProvincia = "0" + CodigoSBSProvincia;
				
				provincia = laProvincia.buscarPorCodigoSBS(CodigoSBSProvincia);
				
				if (provincia.getId() == null)
					throw new Exception("Error Codigo de Provincia "
							+ CodigoSBSProvincia + "  no encontrado");
				
				//hallamos el canton en base al codigo que llega
				String CodigoSBSCanton = in0.getCanton().trim();
				
				if (CodigoSBSCanton.length() == 1 || CodigoSBSCanton.length() == 3)
					CodigoSBSCanton = "0" + CodigoSBSCanton;
				
				if (CodigoSBSCanton.length() >= 3)
					CodigoSBSCanton = CodigoSBSCanton.substring(2, 4);
				
				canton = elCanton.buscarPorCodigoSBS(CodigoSBSCanton, provincia);
				
				if (canton.getId() == null)
					throw new Exception("Error Codigo de Canton "
							+ CodigoSBSCanton + " no encontrado en provincia "
							+ CodigoSBSProvincia);
				
				//Proceso de busqueda de parroquias
				String CodigoSBSParroquia=in0.getParroquia().trim();
				if(CodigoSBSParroquia.length()<6)
					CodigoSBSParroquia="0"+CodigoSBSParroquia;
				if(CodigoSBSParroquia.length()>6)
					CodigoSBSParroquia=CodigoSBSParroquia.substring(0, 6);
				
				///hallamos la direccion del cliente
				CiudadDAO ciudadDAO = new CiudadDAO();
				Ciudad ciu = ciudadDAO.buscarPorId(provincia.getCapitalId());
				direccion.setCiudad(ciu); 
				
				//hallamos la parroquien en base al SBS
				parroquia= laParroquia.BuscarPorSBS(CodigoSBSParroquia);
								
				if (parroquia.getParroquiaNombre() == null)
					throw new Exception("Error Codigo de Parroquia "
							+ CodigoSBSParroquia + " no encontrado ");

								
				if(in0.getReferencia()!=null&&!in0.getReferencia().equals(""))
					direccion.setCallePrincipal(""+in0.getReferencia());
				
				if(in0.getLote()!=null&&!in0.getLote().equals("")){
					direccion.setCalleSecundaria("Lote "+in0.getLote());
					direccion.setNumero(""+in0.getLote());
				}
				
				if(in0.getLatitud()!=null&&!in0.getLatitud().equals("")||in0.getLongitud()!=null&&!in0.getLongitud().equals(""))
					direccion.setDatosDeReferencia("Latitud:"+in0.getLatitud()+" Longitud:"+in0.getLongitud());
				
				direccion.setEsCobro(true);
				direccion.setTipoDireccion(tipoDireccionDAO.buscarPorId("3"));
				direccion.setZona(zonaDAO.buscarPorNombre("Rural"));
				
				if(entidad!=null&&!entidad.getId().equals(""))
					direccion.setEntidad(entidad);
				//se almacena direccion pero si existe algun problema permite continuar
				try{
					if(direccion.getId()==null)
						direccion=direccionTransaction.crear(direccion);
					else
						direccion=direccionTransaction.editar(direccion);
				}catch(Exception e){
					System.out.println("Problemas al almacenar direccion");
				}
				
				/*PROCESO DE ALMACENAMIENTO DETALLADO DE INF. DEL CLIENTE */
				
				registro.setCliente(cliente);
				registro.setGeneroNatural("" + in0.getGenero().trim());
				registro.setTipoActividadNatural(""+ in0.getActividadEconomica().trim());
				Date fechaNacimiento = null;
				try {
					fechaNacimiento = formatoDelTexto.parse(""
							+ in0.getFechaNacimiento().trim());
				} catch (Exception e) {
					fechaNacimiento = null;
				}
				
				registro.setFechaNacimientoNatural(fechaNacimiento);
				
				if (in0.getTelfreferencia() != null) {
					registro.setTelefonoNatural(""
							+ in0.getTelfreferencia().trim());
				}
				
				if (in0.getCelreferencia() != null) {
					registro.setCelularNatural(""
							+ in0.getCelreferencia().trim());
				}
				
				try {
					double valorIngreso = Double.parseDouble(in0.getIngresoAnual().trim());
					registro.setSalarioMensual(valorIngreso);
				} catch (Exception e) {
					//NO SE PUEDE CONVERTIR EL SALARIO
				}
				//almacenamos datos especificos,caso de inconveniente, no detiene el proceso.
				try {
					registroExiste = uplaCliente.buscarPorCliente(cliente);
					if (registroExiste.getId() == null)
						uplaProceso.crear(registro);
					else {
						registro.setId(registroExiste.getId());
						uplaProceso.editar(registro);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				/*****PROCESO DE COTIZACION*****/
				
				AgriParametroXPuntoVenta PPuntoVenta = new AgriParametroXPuntoVenta();
				AgriParametroXPuntoVentaDAO PPuntoVentaProceso = new AgriParametroXPuntoVentaDAO();
				
				//buscamos el punto de venta en base al canal que llega
				PPuntoVenta = PPuntoVentaProceso.buscarCodigoIntegracion(in0.getCodigoFacilitador().trim());
				PuntoVenta puntoVenta = new PuntoVenta();
				puntoVenta = pvDAO.buscarPorId(""
						+ PPuntoVenta.getPuntoVentaId());
				GrupoPorProducto grupo = new GrupoPorProducto();
				grupo = grupoPorProductoDAO.buscarPorNombre("Agricola");
				GrupoPorProducto grupoPorProducto = grupoPorProductoDAO.buscarPorId(grupo.getId());// AGRICOLA
				
				if (puntoVenta != null)
					cotizacion.setPuntoVenta(puntoVenta);
				else {
					throw new Exception("El canal "+in0.getCodigoFacilitador().trim()+ " no existe");
				}
				//verifico si existe subCanal para poder tomar la configuracion especifica del mismo
				ProductoXPuntoVenta pxpv = new ProductoXPuntoVenta();
				
				if(in0.getCodigoSubCanal() == null || in0.getCodigoSubCanal().trim().equals("")|| in0.getCodigoSubCanal().trim().equals("null")){
					pxpv = productoPorPuntoVentaDAO.buscarPorGrupoPuntoVenta(grupoPorProducto, puntoVenta);
				}
				else{
					String codigoSubCanal=in0.getCodigoSubCanal().trim();
					AgriSubcanalDAO agriSubcanalDAO = new AgriSubcanalDAO();
					AgriSubcanal subcanales = agriSubcanalDAO.BuscarTodosCanal(PPuntoVenta.getCanalId().toString(),PPuntoVenta.getPuntoVentaId().toString(),codigoSubCanal); 
					if(subcanales.getPuntoVentaSubCanal()==null)
						throw new Exception("El Subcanal "+codigoSubCanal+" no existe");
					else{
						PPuntoVenta = PPuntoVentaProceso.buscarPorPuntoVentaId(new BigInteger(subcanales.getPuntoVentaSubCanal()));
						puntoVenta = pvDAO.buscarPorId(""
								+ PPuntoVenta.getPuntoVentaId());
						 pxpv = productoPorPuntoVentaDAO.buscarPorGrupoPuntoVenta(grupoPorProducto, puntoVenta);
						 cotizacion.setPuntoVenta(puntoVenta);
					}
					 
				}
				if (pxpv.getId() == null)
					throw new Exception(
							"Error Producto por Punto de Venta no configurado");
				try {
					cotizacion.setProductoXPuntoVentaId(new BigInteger(pxpv
							.getId()));
				} catch (Exception e) {
					// si no existe Producto por punto de venta lo direccionamos a uno por defecto.
					cotizacion.setProductoXPuntoVentaId(new BigInteger("6537"));
					Observacion=Observacion+", punto de venta sucre "+ pxpv
							.getId()+ " no configurado, entra por Id: 6537.";
				}
				
				cotizacion.setVigenciaPoliza(vpDAO.buscarPorId("1"));
				cotizacion.setAgenteId(new BigInteger("1"));// VERIFICAR
				cotizacion.setGrupoProductoId(BigInteger.valueOf(Long.valueOf(grupoPorProducto.getGrupoProducto().getId())));
				cotizacion.setGrupoPorProductoId(BigInteger.valueOf(Long.valueOf(grupoPorProducto.getId())));
				cotizacion.setProducto(grupoPorProducto.getProducto());
				cotizacion.setTipoObjeto(tipoObjetoDAO.buscarPorNombre("Agricola"));
								
				cotizacion.setUsuario(usuarioDAO.buscarPorLogin("e63_1708971229"));
				cotizacion.setFechaElaboracion(sq);
				
				String codigoTipoCultivo = "" + in0.getInversion();
				
				if (codigoTipoCultivo.length() < 2)
					codigoTipoCultivo = "0" + codigoTipoCultivo;

				double tasa = 0;
				double costoProduccion = Double.parseDouble(in0.getCostoProduccion().trim());
				/*double costoMantenimiento=0.0;
				try{
					costoMantenimiento=Double.parseDouble(in0.getCostoMantenimientoCultivo().trim());
				}catch(Exception e){
					costoMantenimiento=0.0;
				}
				
				//si nos llega el costo de mantenimiento entonces realizamos la busqueda de tipo de cultivo en base a este costo
				
				if(costoMantenimiento!=0||costoMantenimiento!=0.0)
					costoProduccion=costoMantenimiento;
				*/
				 String CodigoTipoCultivoProcesado = "";//para tomar el cod.Cultivo una ves verf. costo produccion.
				
				//Obtengo el id del tipo de canal
				AgriTipoCalculo agriTipoCalculo= new AgriTipoCalculo();
				AgriTipoCalculoDAO agriTipoCalculoDAO = new AgriTipoCalculoDAO();
				agriTipoCalculo=agriTipoCalculoDAO.BuscarPorId(PPuntoVenta.getTipoCalculoId());
				
				/*PROCESO DE BUSQUEDA Y VERIFICACION SI ES PERENNE O NO EN BASE al costo de produccion que nos llegue o no*/
				//proceso de busqueda de tipo de cultivo en base al costo de produccion
				AgriTipoCultivoDAO tipoCultivoSucreProceso = new AgriTipoCultivoDAO();
				List<AgriTipoCultivo> agriSucreQbeCultivo = tipoCultivoSucreProceso.BuscarTodosIntegracionSucre(codigoTipoCultivo);
				Date dateSiembra=null;
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");//formato de fecha de siembra
				try{
					dateSiembra=formatter.parse(in0.getFechaSiembra().trim());
				}catch(Exception e){
					throw new Exception(
							"Error la fecha de siembra "+in0.getFechaSiembra()+ " no esta en el formato dd/MM/yyyy");
				}
				
				String TipoCultivoSinCoincidencias="";//Se crea para que en caso de no referenciar una regla, este sirva de referencia para el costo de produccion.
				
				AgriCicloDAO cicloDAO=new AgriCicloDAO();
				//Se halla el tipo de cultivo en base al costo de produccion, en base a la fecha de siembra, caso contrario se toma el primero que tenga coincidencia
				if (!agriSucreQbeCultivo.isEmpty()) {
					bucle1: for (AgriTipoCultivo rs : agriSucreQbeCultivo) {
						AgriReglaDAO regla = new AgriReglaDAO();
						List<AgriRegla> reglas = regla.BuscarPorParametros(
								new BigInteger(provincia.getId()),
								new BigInteger(canton.getId()),
										rs.getTipoCultivoId(),agriTipoCalculo.getTipoCalculoId());
						for (AgriRegla rs2 : reglas) {
							try {
								
								AgriCiclo ciclo = cicloDAO.BuscarPorId(rs2.getClicloId());//tomamos el ciclo para verificar a cual pertenece
								Date fechafin=ciclo.getFechaFin();//fecha inicio ciclo
								Date fechaInicio=ciclo.getFechaInicio();//fecha fin ciclo
								
								String costoP = ""+rs2.getCostoProduccion();
								String costoA = ""+costoProduccion;
								if(costoA.equals(""+rs2.getCostoProduccion())||costoA.equals(rs2.getCostoMantenimiento())){//verificamos que los costos de produccion sean iguales
									TipoCultivoSinCoincidencias=""+rs2.getTipoCultivoId();//si son iguales le ponemos como temporal, asi si no calza por fechas calsa al menos el cultivo
									if (costoP.equals(costoA)) {									
										if(dateSiembra.after(fechaInicio) && dateSiembra.before(fechafin)){
											CodigoTipoCultivoProcesado = ""
													+ rs2.getTipoCultivoId();
											break bucle1;//si se encuentra la busqueda termina										
										}
									}else{
										costoP=""+rs2.getCostoMantenimiento();
										if(costoP .equals(costoA)){
											if(dateSiembra.after(fechaInicio) && dateSiembra.before(fechafin)){
												CodigoTipoCultivoProcesado = ""
														+ rs2.getTipoCultivoId();
												break bucle1;//si se encuentra la busqueda termina										
											}
										}
									}
								}else{
									TipoCultivoSinCoincidencias=""+rs2.getTipoCultivoId();//si no son iguales los costos de produccion al menos buscame un similar.
								}
								
							} catch (Exception e) {
								continue;
							}
						}
					}
				} else {
					throw new Exception("Error Tipo de cultivo "+ in0.getInversion() + " no existe");
				}
				
				if (CodigoTipoCultivoProcesado.equals("")) {//Si no encontraste tipo de cultivo traeme el que al menos coincida
					Observacion=" No se encontro ninguna regla de siembra para este tipo de cultivo.";
					CodigoTipoCultivoProcesado =TipoCultivoSinCoincidencias;
					if(CodigoTipoCultivoProcesado.equals("")){//si aun asi no encuentra tomamos el primero que coincidir con el codigo de integracion
						elCutlitvo = cultivo.buscarPorCodIntegracionSucre(codigoTipoCultivo);
						if (elCutlitvo.getTipoCultivoId() == null)
							throw new Exception("Error Tipo de cultivo "+ in0.getInversion() + " no existe");
						CodigoTipoCultivoProcesado = ""+ elCutlitvo.getTipoCultivoId();
					}					
				}

				AgriReglaDAO regla = new AgriReglaDAO();
				List<AgriRegla> tasas = regla.BuscarPorParametros(new BigInteger(provincia.getId()), new BigInteger(
								canton.getId()), new BigInteger(CodigoTipoCultivoProcesado),agriTipoCalculo.getTipoCalculoId());
				
				String nuestroCosto = "0.0";//para verificar si existen diferencias entre costo que llega y que tenemos
				String idTipoCalculo = "";//para saber en base a que regla se calcularon los datos
				
				//ya se tiene el cultivo, la provincia, el canton y el tipo de calculo se busca los costos de produccion
				for (AgriRegla rs : tasas) {
					if(rs.getTasa()!=0 || rs.getTasa()!=0.0 ){//si tiene tasa debe tener o costo de produccion o costo de mantenimiento
						tasa = Double.parseDouble(""+rs.getTasa());
						if(rs.getCostoProduccion()!=0){
							nuestroCosto = (""+rs.getCostoProduccion());
						}else{
							nuestroCosto = (""+rs.getCostoMantenimiento());
						}
						Observacion = Observacion+" " + rs.getObservaciones();
						idTipoCalculo = "" + rs.getReglaId();
					}
				}
				
				/* Costos De Produccion llegue o no */
				double costoProduccionL = 0.0;
				try {
					costoProduccionL = costoProduccion;
				} catch (Exception e) {
					costoProduccionL =Double.parseDouble(nuestroCosto);
				}
				
				if(!(""+costoProduccion).equals(nuestroCosto)){
					Observacion=Observacion+" Costos de Produccion Diferentes Costo Sucre: "+costoProduccion+" / Costo QB: E"+ nuestroCosto;
				}
				
				//si no se tuviese la tasa, hallamos en base a la primera regla del cultivo
				if (tasa==0|| tasa==0.0){
					AgriTipoCultivoDAO agriTipoCultivoDAO= new AgriTipoCultivoDAO();
					AgriTipoCultivo agriTipoCultivo=agriTipoCultivoDAO.BuscarPorId(new BigInteger( CodigoTipoCultivoProcesado));
					tasa = agriTipoCultivo.getTasa();
				}
					
				double NumeroHectareasL = 0.0;
				
				try {
					NumeroHectareasL =in0.getNumHectAseguradas();
					if(NumeroHectareasL==0.0||NumeroHectareasL==0)
						throw new Exception("Numero de hectareas 0 imposible continuar");
				} catch (Exception e) {
					throw new Exception("Error numero de hectareas"
							+ in0.getNumHectAseguradas()
							+ " no se puede procesar");
				}

				//Verificamos si es perenne o no en base al costo de mantenimiento que llega
				double valorTotaL = 0.0;
				try {
					valorTotaL = costoProduccionL * NumeroHectareasL;
					BigDecimal T = new BigDecimal(""+valorTotaL);
					BigDecimal roundOffT = T.setScale(2, BigDecimal.ROUND_HALF_UP);
					valorTotaL=Double.parseDouble(""+roundOffT);
				} catch (Exception e) {
					throw new Exception("Error se puede calcular valor Total");
				}
				
				//Calculo de la Prima = Costo Produccion * tasa * numero Hectareas / 1000
				
				String variableControl=auditoria.getObjeto();
				
				double valorPrima=valorTotaL * tasa /100;
				
				variableControl=variableControl+"|| CALCULOS:  Tasa: "+tasa;	
				variableControl=variableControl+" NHect: "+NumeroHectareasL;
				variableControl=variableControl+" CostoP: "+costoProduccionL;
				
				BigDecimal a = new BigDecimal(""+valorPrima);
				BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
				variableControl=variableControl+" Prima: "+roundOff;				
				cotizacion.setPrimaNetaTotal(""+roundOff);
				
				a=new BigDecimal(""+valorTotaL);
				roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
				variableControl=variableControl+" SumaAsegurada: "+roundOff;
				
				cotizacion.setSumaAseguradaTotal(Double.parseDouble(""+roundOff));
				
				a=new BigDecimal(""+valorPrima);
				roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
								
				cotizacion.setPrimaOrigen(Double.parseDouble(""+roundOff));
				
				/* PROCESO DE CALCULO DE COMPONENTES */
				
				double valorPrimaCalculada=(Double.parseDouble(""+roundOff));
				TipoVariableDAO tipoVariableDao = new TipoVariableDAO();
				TipoVariable tipoVariable = tipoVariableDao.buscarPorId("3");
				VariableSistemaDAO variableDAO = new VariableSistemaDAO();
				List<VariableSistema> variablesistema = variableDAO
						.buscarTipoVariable(tipoVariable);
				double valorBase = 0;
				double valorDerechosEmision = 0;
				double valorSeguroCampesino = 0;
				double valorSuperBancos = 0;
				double valorIva = 0;
				double valorSubTotal = 0;
				if (variablesistema.size() > 0) {
					for (VariableSistema variable : variablesistema) {
						if(tasa==0|| tasa==0.0){
							if (variable.getNombre().equals("TASA_ESTANDAR")) {
								tasa=Double.parseDouble(variable.getValor());
								Observacion=Observacion+" Tasa no encontrada, por defecto se envia "+tasa;
							}
						}
						
						if (variable.getNombre().equals("DERECHOS_EMISION_AGRICOLA")) {
							valorBase = valorDerechosEmision+valorBase;
									
							// valorDerechosEmision =
							// Double.parseDouble(variable.getValor());
							cotizacion
									.setImpDerechoEmision(valorDerechosEmision);
						} else if (variable.getNombre().equals(
								"SEGURO_CAMPESINO")) {
							valorSeguroCampesino = Math.rint(Double
									.parseDouble(variable.getValor())
									* valorPrima / 100 * 100) / 100;
							valorBase = valorBase + valorSeguroCampesino;
							cotizacion
									.setImpSeguroCampesino(valorSeguroCampesino);
						} else if (variable.getNombre().equals(
								"SUPER_DE_BANCOS")) {
							valorSuperBancos = Math.rint(Double
									.parseDouble(variable.getValor())
									* valorPrima / 100 * 100) / 100;
							cotizacion.setImpSuperBancos(valorSuperBancos);
							valorBase = valorBase + valorSuperBancos;
						}
						
					}
									
					VariableSistema variableIva= variableDAO.buscarPorNombre("IVA");					
					valorSubTotal=valorBase+valorPrimaCalculada;
					//calculamos el Iva
					String PorcentajeIva=variableIva.getValor();
					valorIva = Math.rint(Double.parseDouble(PorcentajeIva) * valorSubTotal / 100 * 100) / 100;
					a = new BigDecimal(""+valorIva);
					roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
					cotizacion.setImpIva(Double.parseDouble(""+roundOff));
					//calculamos el valor total
					double valorFactura=valorSubTotal+valorIva;
					a = new BigDecimal(""+valorFactura);
					roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
					cotizacion.setTotalFactura(Double.parseDouble(""+roundOff));
					
				}
				cotizacion.setTasaProductoValor(tasa);
				cotizacion.setEtapaWizard(3);
				cotizacion.setClienteId(new BigInteger(cliente.getId()));
				
				//Busqueda de emision directa por canal y cultivo
				//nuevo
				if(PPuntoVenta.getEmisionDirecta()){
					if(PPuntoVenta.getExcepcionesDirectasCultivos()!=null){
						String cultivosAEmitir = PPuntoVenta.getExcepcionesDirectasCultivos();
				        String[] cultivosAEmitirArray = cultivosAEmitir.split(";");
				        for(String cultivos :cultivosAEmitirArray){
				        	if(cultivos.equals(codigoTipoCultivo)){
				        		cotizacion.setEstado(estadoDAO.buscarPorNombre(
										"Pendiente de Emitir", "Cotizacion"));
								cotizacion.setFechaEmision(sq);
								cotizacion.setVigenciaDesde(sq);
								respuesta.setMontoAprobadoQBE((float)(cotizacion.getSumaAseguradaTotal()));
								break;
				        	}else{
				        		cotizacion.setEstado(estadoDAO.buscarPorNombre(
										"Pendiente Aprobar", "Cotizacion"));
				        	}
				   
				        }
					}
					else{
						cotizacion.setEstado(estadoDAO.buscarPorNombre(
								"Pendiente de Emitir", "Cotizacion"));
						cotizacion.setFechaEmision(sq);
						cotizacion.setVigenciaDesde(sq);
						respuesta.setMontoAprobadoQBE((float)(cotizacion.getSumaAseguradaTotal()));
					}
				}else{
					cotizacion.setEstado(estadoDAO.buscarPorNombre(
							"Pendiente Aprobar", "Cotizacion"));
				}
				
				
				
				cotizacion.setAsegurado(entidad);
				
				if (cotizacion.getId() != null)
					cotizacion = cotizacionTransaction.editar(cotizacion);
				else
					cotizacion = cotizacionTransaction.crear(cotizacion);
				
				/****PROCESO DE CREACION DE AGRI_OBJETO_COTIZACION*****/
				
				// Inserta el detalle de la cotizaci�n
				
				AgriObjetoCotizacion agriObjetoCotizacion = new AgriObjetoCotizacion();
				agriObjetoCotizacion.setConfirEmiCanal(false);
				agriObjetoCotizacion.setProvinciaId(new BigInteger(provincia.getId()));
				agriObjetoCotizacion.setCantonId(new BigInteger(canton.getId()));
				agriObjetoCotizacion.setCostoProduccionQBE(Float.parseFloat(nuestroCosto));
				try{
					agriObjetoCotizacion.setAgriParroquiaId(""+parroquia.getId());
				}catch(Exception e){
					//La parroquia no se encuentra en el catalogo.
				}
				
				agriObjetoCotizacion.setTipoCultivoId(new BigInteger(CodigoTipoCultivoProcesado));
				
				if (in0.getCodigoVariedadCultivo() != null)
					agriObjetoCotizacion.setVariedad(in0.getVariedad().trim());
				
				String tieneRiego = in0.getDisponeRiego().trim();
				
				if (tieneRiego.equals("0") || tieneRiego.equals("")
						|| tieneRiego.equals("no") || tieneRiego.equals("n"))
					agriObjetoCotizacion.setDisponeRiego(false);
				else
					agriObjetoCotizacion.setDisponeRiego(true);
				
				String terrenoTecnificado = in0.getTerrenoTecnificado().trim();
				
				if (terrenoTecnificado.equals("0")
						|| terrenoTecnificado.equals("no")
						|| terrenoTecnificado.equals("n") 
						|| terrenoTecnificado.equals("NO")
						|| terrenoTecnificado.equals("N"))
					agriObjetoCotizacion.setAgricultorTecnificado(false);
				else
					agriObjetoCotizacion.setAgricultorTecnificado(true);
				
				agriObjetoCotizacion.setDireccionSiembra("Recinto:"+in0.getRecinto()+"/"+in0.getReferencia().trim());
				agriObjetoCotizacion.setTipoSeguro(0);
				agriObjetoCotizacion.setHectareasLote(Float.parseFloat(""+in0.getNumHectFinanciadas()));
				agriObjetoCotizacion.setLatitud(Float.parseFloat(""+in0.getLatitud()));
				agriObjetoCotizacion.setLongitud(Float.parseFloat(in0.getLongitud()));
				agriObjetoCotizacion.setHectareasAsegurables(Float.parseFloat(""+in0.getNumHectAseguradas()));
				try{
					agriObjetoCotizacion.setMontoCredito(Float.parseFloat(in0.getMontoRecomendado()));
				}catch(Exception e){
					//error monto asegurado
				}
				
				Date fechaAprobacion = null;
				try {
					fechaAprobacion = formatoDelTexto.parse(in0.getFechaAprobacion().trim());
				} catch (ParseException e) {
					fechaAprobacion = null;
				}
				
				agriObjetoCotizacion.setFechaCredito(fechaAprobacion);
				agriObjetoCotizacion.setFechaSiembra(dateSiembra);
				
				agriObjetoCotizacion.setTipoOrigen("SUCRE");
				if (!Observacion.equals("")||!Observacion.equals(" ")) {
					agriObjetoCotizacion.setObservacion(Observacion);
				}
				agriObjetoCotizacion.setTipoCalculo(idTipoCalculo);
				agriObjetoCotizacion.setCostoProduccion(Float.parseFloat(""+costoProduccionL));
				
				/***Proceso de creacion de las IdCotizaciones para facturacion***/
				
				AgriCotizacionMaxDAO busquedaMax = new AgriCotizacionMaxDAO();
				AgriCotizacionMax numMaximo=busquedaMax.buscarTodos();
				int numeroActual=numMaximo.getMaximo().intValue();
				
				agriObjetoCotizacion.setIdCotizacion2(new BigInteger(""+(numeroActual+1)));
				agriObjetoCotizacion = agriObjetoCotizacionTransaction.crear(agriObjetoCotizacion);
				
				/*** PROCESO DE CREACION DE LAS COTIZACIONES DETALLE ***/
				
				CotizacionDetalle nuevoCotizacionDetalle = new CotizacionDetalle();
				nuevoCotizacionDetalle.setCotizacion(cotizacion);
				nuevoCotizacionDetalle.setNecesitaInspeccion(false);
				nuevoCotizacionDetalle.setTipoObjetoId(tipoObjetoDAO
						.buscarPorNombre("Agricola").getId());
				nuevoCotizacionDetalle.setObjetoId(agriObjetoCotizacion
						.getObjetoCotizacionId().toString());
				nuevoCotizacionDetalle.setSumaAseguradaItem(cotizacion
						.getSumaAseguradaTotal());
				nuevoCotizacionDetalle.setPrimaNetaItem(Double
						.parseDouble(cotizacion.getPrimaNetaTotal()));
				nuevoCotizacionDetalle.setTasa(tasa);
				
				cotizacionDetalleTransaction.crear(nuevoCotizacionDetalle);
				
				/* Proceso de almacenamiento y verificacion de la informacion (Informacion adicional que recibimos)*/
				AgriSucreDetalle detalleSucre = new AgriSucreDetalle();
				AgriSucreDetalleDAO detalle = new AgriSucreDetalleDAO();
				detalleSucre.setAgriObjetoCotizacion(""+ agriObjetoCotizacion.getObjetoCotizacionId());
				detalleSucre.setEntidadId(entidad.getId());
				detalleSucre.setNumeroResolucion("" + in0.getNumeroResolucion());
				detalleSucre.setEstadoSolicitud(in0.getEstadoSolicitud());
				detalleSucre.setCondicionPredio(in0.getCondicionPredio());
				if (in0.getCondicionPredioOtra() != null)
					detalleSucre.setCondicionPrediootra(in0
							.getCondicionPredioOtra().trim());
				detalleSucre.setValorSubsidio(in0.getValorSubsidio().trim());
				if (in0.getPaqueteTecnologico() != null)
					detalleSucre.setPaquetetecnologico(in0
							.getPaqueteTecnologico().trim());
				detalleSucre.setTipoCanal(in0.getTipoCanal().trim());
				detalleSucre.setLote(in0.getLote().trim());
				if (in0.getTipoRiego() != null)
					detalleSucre.setTipoRiesgo(in0.getTipoRiego().trim());
				if (in0.getOtroRiego() != null)
					detalleSucre.setOtroRiesgo(in0.getOtroRiego().trim());
				if (in0.getCostoEstablecimientoCultivo() != null)
					detalleSucre.setCostoEstablecimientoCultivo(in0
							.getCostoEstablecimientoCultivo().trim());
				if (in0.getCostoMantenimientoCultivo() != null)
					detalleSucre.setCostoMantenimientoCultivo(in0
							.getCostoMantenimientoCultivo().trim());

				AgriSucreDetalle detalleSucre2 = new AgriSucreDetalle();
				detalleSucre2 = detalle.buscarPorObjetoId(""+ agriObjetoCotizacion.getObjetoCotizacionId());
				AgriSucreDetalleTransaction agriSucreProceso = new AgriSucreDetalleTransaction();
				
				try{
					if (detalleSucre2 == null)
						agriSucreProceso.crear(detalleSucre);
					else {
						detalleSucre.setId(detalleSucre.getId());
						agriSucreProceso.editar(detalleSucre);
					}
				}catch(Exception e){
					//permitimos que continue en caso de suceder algun problema
				}
				
				/* PROCESO DE ADAPTACION AL OBJETO DE RESPUESTA*/
				
				respuesta.setPrima(Float.parseFloat(cotizacion.getPrimaNetaTotal()));
				String Estado = "" + cotizacion.getEstado().getNombre();
				if (Estado.equals("Pendiente de Emitir")){
					Estado = "Aprobado";
					AgriAuditoriaCotizacion agriAuditoriaCotizacion = new AgriAuditoriaCotizacion();
					AgriAuditoriaCotizacionDAO agriAuditoriaCotizacionDAO = new AgriAuditoriaCotizacionDAO();
					agriAuditoriaCotizacion=agriAuditoriaCotizacionDAO.BuscarPorCotizacinId(new BigInteger(cotizacion.getId()));
					respuesta.setFechaAprobacion(sq);
				}					
				else {
					if (Estado.equals("Anulado"))
						Estado = "Rechazado";
					else
						Estado = "Pendiente";
				}
				respuesta.setEstado(Estado);
				respuesta.setAutorizacion(Integer.parseInt(""+ cotizacion.getId()));
				respuesta.setObservacion("");
				respuesta.setNumeroCotizacion("" + cotizacion.getId());
				respuesta.setAutorizacion(Integer.parseInt(""+ cotizacion.getId()));
				respuesta.setMontoRecomendadoQBE((float)(cotizacion.getSumaAseguradaTotal()));
				
				cotizacion.setNumeroTramite(NumeroTramite);
				cotizacion = cotizacionTransaction.editar(cotizacion);
				auditoria.setObjeto(variableControl);
				/*Almacenamiento en auditoria*/
				String EstadoAuditoria = "" + cotizacion.getEstado().getNombre();
				AgriAuditoriaCotizacion agriAuditoriaCotizacion = new AgriAuditoriaCotizacion();
				if (EstadoAuditoria.equals("Pendiente de Emitir")){					
					Date fechaAprovacion = new Date();
					AgriAuditoriaCotizacionTransaction agriAuditoriaCotizacionTransaction = new AgriAuditoriaCotizacionTransaction();
					agriAuditoriaCotizacion.setCotizacionId(new BigInteger(cotizacion.getId()));
					agriAuditoriaCotizacion.setFecha(fechaAprovacion);
					agriAuditoriaCotizacion.setTipoActividad("APROBADO REVISION");
					agriAuditoriaCotizacion.setUsuarioId(new BigInteger("0"));
					agriAuditoriaCotizacionTransaction.crear(agriAuditoriaCotizacion);
				}
				
				try{
					auditoria.setEstado(Estado);
					procesoAuditoria.editar(auditoria);
				}catch(Exception e){
					e.printStackTrace();
				}
				
				/**Filtro agregado en base a solicitud de d.Garzon y L.Regalado, las cotizaciones no deberian ingresar con fechas mayores a los dias definidos, se deja pasar pero se muestra advertencia**/
				Date fechaSiembraIn=agriObjetoCotizacion.getFechaSiembra();
				VariableSistema diasCotizacion=variableDAO.buscarPorNombre("DIAS_COTIZACION");
				
				Date fechaActual= new Date();
				
				Calendar calFechaAntesApr = Calendar.getInstance();
				calFechaAntesApr.setTime(fechaActual);
				calFechaAntesApr.add(Calendar.DAY_OF_MONTH,-(Integer.parseInt(diasCotizacion.getValor())+1));
	            Date fechaAntesApr=calFechaAntesApr.getTime();
	            
	            Calendar calFechaDespuesApr = Calendar.getInstance();
	            calFechaDespuesApr.setTime(fechaActual);
	            calFechaDespuesApr.add(Calendar.DAY_OF_MONTH,Integer.parseInt(diasCotizacion.getValor())+1);
	            Date fechaDespuesApr=calFechaDespuesApr.getTime();
	            
	            String detalleCambio="Advertencia!! Fecha siembra "+formatoDelTexto.format(fechaSiembraIn)+" pasa los "+diasCotizacion.getValor()+" dias tolerados +/- de fecha ingreso cotizacion "+formatoDelTexto.format(fechaActual);
	            
	            if(fechaSiembraIn.after(fechaAntesApr) && fechaSiembraIn.before(fechaDespuesApr))
	            	System.out.println("fechas correctas");
	            else{
	            	agriObjetoCotizacion.setDetallesModificacion(detalleCambio);
	            	agriObjetoCotizacion.setTieneModificacion(true);
	            	agriObjetoCotizacion = agriObjetoCotizacionTransaction.editar(agriObjetoCotizacion);//Actualizo
	            }
	            
			} else {
				String IdCotizacion = ExisteCotizacion.getId();
				Cotizacion ExisteCotizacion2 = new Cotizacion();
				ExisteCotizacion2 = BusquedaCotizacion
						.buscarPorId(IdCotizacion);
				String Estado = "" + ExisteCotizacion2.getEstado().getNombre();
				if (Estado.equals("Pendiente de Emitir")){
					Estado = "Aprobado";
				    respuesta.setMontoAprobadoQBE((float)(ExisteCotizacion2.getSumaAseguradaTotal()));
				    AgriAuditoriaCotizacion agriAuditoriaCotizacion = new AgriAuditoriaCotizacion();
					AgriAuditoriaCotizacionDAO agriAuditoriaCotizacionDAO = new AgriAuditoriaCotizacionDAO();
					agriAuditoriaCotizacion=agriAuditoriaCotizacionDAO.BuscarPorCotizacinId(new BigInteger(IdCotizacion));
					if(agriAuditoriaCotizacion.getFecha()==null){						
						respuesta.setFechaAprobacion(ExisteCotizacion2.getFechaElaboracion());
					}else
						respuesta.setFechaAprobacion(agriAuditoriaCotizacion.getFecha());
				}
				else {
					if (Estado.equals("Anulado"))
						Estado = "Rechazado";
					else{
						if (Estado.equals("Revocado Sucre"))
							Estado = "Revocado";
						else
							Estado = "Pendiente";
					}
				}
				try{
					auditoria.setEstado(Estado);
					procesoAuditoria.editar(auditoria);
				}catch(Exception e){
					e.printStackTrace();
				}				
				respuesta.setEstado(Estado);
				respuesta.setNumeroCotizacion(ExisteCotizacion.getId());
				respuesta.setAutorizacion(Integer.parseInt(ExisteCotizacion.getId()));
				respuesta.setPrima(Float.parseFloat(ExisteCotizacion2.getPrimaNetaTotal()));
				respuesta.setMontoRecomendadoQBE(Float.parseFloat(""+ExisteCotizacion2.getSumaAseguradaTotal()));
				respuesta.setObservacion("");
			}

		} catch (Exception e) {	
			List<String> usuario = new ArrayList<>();
			usuario.add("luis.caiza@smartwork.com.ec");
			String asunto="Error en Proceso de Cotizacion Sucre";
			/*String cuerpo="<p>Estimado Usuario:  El n&uacute;mero de tr&aacute;mite Sucre:  "+in0.getNumeroResolucion()
					+ " No a podido ser procesado por el cotizador Agricola, por la siguiente raz&oacute;n:</p>"+e.getMessage();
			*/
			AgriSucreNotificacionErrores notificacionErrores= new AgriSucreNotificacionErrores();
			String Html=notificacionErrores.GeneradorHtml(in0.getNumeroResolucion(), e.getMessage(), "");
			
			for(String receptor:usuario){
				//Utilitarios.envioMail(receptor, asunto, cuerpo);
				Utilitarios.envioMail(receptor, asunto, Html);
			}
			respuesta.setEstado("Error");
			respuesta.setObservacion("Error: " + e.getMessage());
			
			try{
				auditoria.setEstado("Error" + e.getMessage());
				procesoAuditoria.editar(auditoria);
				}catch(Exception e1){
					e1.printStackTrace();
				}
			e.printStackTrace();
		}
		return respuesta;
	}

	
	/*
	 * Metodo que permite registrar la emisi�n de un tr�mite que ha pasado por
	 * la evaluaci�n de autorizaci�n registrada
	 */

	public RespuestaEMIDTO registrarEmision(SolicitudEMIDTO in0) {
		RespuestaEMIDTO respuesta = new RespuestaEMIDTO();
		/* Proceso de validacion de campos obligatorios */
		
		/*PROCESO DE AUDITORIA */
		AgriSucreAuditoria auditoria = new AgriSucreAuditoria();
		AgriSucreAuditoriaTransaction procesoAuditoria = new AgriSucreAuditoriaTransaction();
		auditoria.setTramite(in0.getNumeroResolucion());
		auditoria.setObjeto("TIPO DE ENDOSO: "+in0.getTipoEndoso()+" Tramite : "+in0.getNumeroResolucion()+" Anexo : "+in0.getAnexo()+" Apellido1 : "+in0.getApellido1()+" Apellido2 : "+in0.getApellido2()
				+" Canton : "+in0.getCanton()+" Cedula : "+in0.getCedula()+ " CodigoFacilitador : "+in0.getCodigoFacilitador()+" CodigoFacilitado : "+in0.getCodigoFacilitador()
				+" FechaEmision : "+in0.getFechaEmision()+" FechaPago : "+in0.getFechaPago()+" FechaSiembra "+in0.getFechaSiembra()+" Inversion : "+in0.getInversion()+
				" Lote : "+in0.getLote()+ " MontoAsegurado : "+in0.getMontoAsegurado()+ " Nombres : "+in0.getNombres()+" NumeroFactura : "+in0.getNumeroFactura()
				+" NumHectAseguradas "+in0.getNumHectAseguradas()+" NumHectFnanciadas  "+in0.getNumHectFnanciadas()+" OrdenPago : " +in0.getOrdenPago()
				+" Parroquia : "+in0.getParroquia()+" Poliza : "+in0.getPoliza()+" Provincia : "+in0.getProvincia()+" Recinto : "+in0.getRecinto()+" Referencia : "+in0.getReferencia()
				+" TerrenoTecnificado : "+in0.getTerrenoTecnificado()+ " Variedad : "+in0.getVariedad()+" MontoPago : "+in0.getMontoPago()+" PrimaCalculada : "+in0.getPrimaCalculada()+
				" TiposSubEndoso : "+in0.getTipoSubEndoso()+" hectareas cambiadas: "+in0.getNumeroHectareasMov()+" Causa : "+in0.getCausa());
		
		java.util.Date date2 = new java.util.Date();
		java.sql.Timestamp sq2 = new java.sql.Timestamp(date2.getTime());
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		auditoria.setFecha(sq2);
		auditoria.setCanal("SUCRE EMISION "+in0.getTipoEndoso());
		try{
			auditoria=procesoAuditoria.crear(auditoria);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try {
			
			  /*PROCESO DE VALIDACION DE CAMPOS ENTREGADOS AL SERVICIO*/
			  if(in0.getTipoEndoso()==null||in0.getTipoEndoso().equals("")) throw new Exception
			  ("Error Tipo de endoso null o vacio");
			  if(in0.getAnexo()==null||in0.getAnexo().equals("")) throw new Exception
			  ("Error Tipo de Anexo null o vacio");
			  if(in0.getNumeroResolucion()==null) throw new Exception
			  ("Error valor nulo campo NumeroResolucion");
			  if(in0.getNumeroFactura()==null) throw new Exception
			  ("Error valor nulo campo NumeroFactura");
			  if(in0.getFechaEmision()==null) throw new Exception
			  ("Error valor nulo campo FechaEmision");
			  if(in0.getPoliza()==null) throw new Exception
			  ("Error valor nulo campo Poliza"); if(in0.getAnexo()==null) throw
			  new Exception ("Error valor nulo campo Anexo");
			  if(in0.getOrdenPago()==null) throw new Exception
			  ("Error valor nulo campo OrdenPago");
			  if(in0.getFechaSiembra()==null) throw new Exception
			  ("Error valor nulo campo FechaSiembra");
			  if(in0.getCodigoFacilitador()==null) throw new Exception
			  ("Error valor nulo campo CodigoFacilitador");
			  if(in0.getProvincia()==null) throw new Exception
			  ("Error valor nulo campo Provincia"); if(in0.getCanton()==null)
			  throw new Exception ("Error valor nulo campo Canton");
			  if(in0.getInversion()==null) throw new Exception
			  ("Error valor nulo campo Inversion"); if(in0.getCedula()==null)
			  throw new Exception ("Error valor nulo campo Cedula");
			  if(in0.getNombres()==null) throw new Exception
			  ("Error valor nulo campo Nombres"); if(in0.getApellido1()==null)
			  throw new Exception ("Error valor nulo campo Apellido1");
			  if(in0.getApellido2()==null) throw new Exception
			  ("Error valor nulo campo Apellido2"); if(in0.getRecinto()==null)
			  throw new Exception ("Error valor nulo campo Recinto");
			  if(in0.getReferencia()==null) throw new Exception
			  ("Error valor nulo campo Referencia"); if(in0.getLote()==null)
			  throw new Exception ("Error valor nulo campo Lote");
			  if(in0.getTerrenoTecnificado()==null) throw new Exception
			  ("Error valor nulo campo TerrenoTecnificado");
			  if(in0.getMontoAsegurado()==null) throw new Exception
			  ("Error valor nulo campo MontoAsegurado");
			  if((""+in0.getMontoPago()).equals("null")) throw new Exception
			  ("Error valor nulo campo MontoPago");
			  if((""+in0.getNumHectFnanciadas()).equals("null")) throw new
			  Exception ("Error valor nulo campo NumHectFinanciadas");
			  if((""+in0.getNumHectAseguradas()).equals("null")) throw new
			  Exception ("Error valor nulo campo NumHectAseguradas");
			  if((""+in0.getTipoEndoso().toUpperCase().trim()).equals("POLI")||(""+in0.getTipoEndoso().toUpperCase().trim()).equals("APR")
					  ||(""+in0.getTipoEndoso().toUpperCase().trim()).equals("APA")||(""+in0.getTipoEndoso().toUpperCase().trim()).equals("ANU")) 
				  System.out.println("Entra Proceso :"+in0.getTipoEndoso());
			  else	  
				  throw new Exception ("Error tipo de endoso: "+in0.getTipoEndoso()+ " desconocido");
			 
			  //verificamos el a�o de las cotizaciones
			  //enviaban las fechas solo con 2 numeros, se puso el filtro para que envien en el formato correcto
				String AnioSiembra=in0.getFechaSiembra();
				String[] elementosFecha = AnioSiembra.split("/");
				int comprobacionAnio=elementosFecha[2].length();
							
				if(comprobacionAnio !=4)
					throw new Exception("Fecha de siembra no valida "+in0.getFechaSiembra()+" no valida, debe estar en el formato dd/MM/yyyy");
			  
			  	String NumeroTramite = in0.getNumeroResolucion().trim();
			  	Cotizacion ExisteCotizacion = new Cotizacion();
				CotizacionDAO BusquedaCotizacion = new CotizacionDAO();
				ExisteCotizacion = BusquedaCotizacion.buscarPorNumeroTramite(NumeroTramite.trim());
				CotizacionDetalleDAO cotizacionDetalleDAO= new CotizacionDetalleDAO();
				//SI NO EXISTE LA COTIZACION SE LES ENVIA UN ERROR	
				if(ExisteCotizacion.getId()==null){
					throw new Exception ("La cotizacion con el tramite: "+NumeroTramite+ " no esta registrada en el sistema");					  
				}
				/**BUSQUEDA DEL DETALLE DE LA COTIZACION**/
				CotizacionDetalle cotizacionDetalle = cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(ExisteCotizacion).get(0);
				AgriObjetoCotizacionDAO agriObjetoCotizacionDAO= new AgriObjetoCotizacionDAO();
				AgriObjetoCotizacion agriObjetoCotizacion= agriObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizacionDetalle.getObjetoId()));
				//variables globales
				java.util.Date date = new java.util.Date();
				String ObservacionSucre="";
				/**COMIENZA EL PROCESO CON LA COTIZACION**/
				if (ExisteCotizacion.getId() != null) {	
					if(ExisteCotizacion.getEstado().getNombre().equals("Pendiente de Emitir")||ExisteCotizacion.getEstado().getNombre().equals("Cancelada")){
						System.out.println("estados previos correctos");
					}else{
						ObservacionSucre="Error la cotizacion no esta Aprobada por QBE tramite: "+NumeroTramite.trim()+" ";
						throw new  Exception ("Error la cotizacion no esta Aprobada por QBE tramite: "+NumeroTramite.trim()+" ");
					}
					if(!ExisteCotizacion.getAsegurado().getIdentificacion().equals(in0.getCedula().trim()))
					{
						ObservacionSucre="Endoso TIPO POLI invalida: clientes distintos QBE: "+ExisteCotizacion.getAsegurado().getIdentificacion()+" cliente Sucre: "+in0.getCedula().trim();
						throw new  Exception ("Endoso TIPO POLI invalida: clientes distintos QBE: "+ExisteCotizacion.getAsegurado().getIdentificacion()+" cliente Sucre: "+in0.getCedula().trim());
					}
					String codigoParroquiaQBE=agriObjetoCotizacion.getAgriParroquiaId();
					AgriParroquiaDAO agriParroquiaDAO = new AgriParroquiaDAO();
					AgriParroquia agriParroquia= agriParroquiaDAO.BuscarPorId(Integer.parseInt(codigoParroquiaQBE));
					String parroquiaQBE=agriParroquia.getParroquiaSbs();
					if(parroquiaQBE.length()<6){
						parroquiaQBE="0"+parroquiaQBE;
					}
						
					if(!parroquiaQBE.equals(in0.getParroquia().trim())){
						ObservacionSucre="Endoso TIPO POLI invalida: parroquias Distintas QBE: "+agriParroquia.getParroquiaSbs()+" parroquia Sucre: "+in0.getParroquia().trim();
						throw new  Exception ("Endoso TIPO POLI invalida: parroquias Distintas QBE: "+agriParroquia.getParroquiaSbs()+" parroquia Sucre: "+in0.getParroquia().trim());
					}
					String fechaSiembra=formatoDelTexto.format(agriObjetoCotizacion.getFechaSiembra());
					String fechaSiembraSucre=in0.getFechaSiembra().trim();
					if(!fechaSiembra.equals(fechaSiembraSucre)){
						ObservacionSucre="Endoso TIPO POLI invalida: fechas Siembra distintas QBE: "+fechaSiembra+" fecha Sucre: "+fechaSiembraSucre;
						throw new  Exception ("Endoso TIPO POLI invalida: fechas Siembra distintas QBE: "+fechaSiembra+" fecha Sucre: "+fechaSiembraSucre);
					}
					String tipoEndoso=in0.getTipoEndoso().trim().toUpperCase();
					int tipoSubendoso=0;
					/***PROCESOS DEPENDIENTES DEL TIPO DE ENDOSO***/
					switch (tipoEndoso) {
					case "POLI":
						//verificacion de notificacion de Poli anterior
						if(agriObjetoCotizacion.getConfirEmiCanal()){
							respuesta.setEstado("OK");
							respuesta.setObservacion("NOTIFICACION POLI REALIZADA ANTERIORMENTE PROCESO CORRECTO");
							try{
								auditoria.setObjeto("NOTIFICACION POLI REALIZADA ANTERIORMENTE PROCESO CORRECTO");
								auditoria=procesoAuditoria.crear(auditoria);
							}catch(Exception e){
								e.printStackTrace();
							}							
							
							return respuesta;
						}
						//si esta revocado no se puede emitir de nuevo
						if(ExisteCotizacion.getEstado().getNombre().equals("Cancelada")||ExisteCotizacion.getEstado().getNombre().equals("RevocadoCanal")||ExisteCotizacion.getEstado().getNombre().equals("Revocado")){
							throw new Exception("No se puede emitir una cotizacion anulada anteriormente");
						}					
						
						//COMPARO VALORES DE COTIZACION
						Double valorCancelar=ExisteCotizacion.getPrimaOrigen();
						BigDecimal a = new BigDecimal(""+in0.getMontoPago());
						BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
						Double ValorPagado=Double.parseDouble(""+roundOff);
						int comparacion=valorCancelar.compareTo(ValorPagado);	
						if(comparacion!=0){
							ObservacionSucre="Endoso TIPO POLI invalida: valores distintos, valor QBE: "+valorCancelar+ " valor Sucre: "+ValorPagado;
							throw new  Exception ("Endoso TIPO POLI invalida: valores distintos, valor QBE: "+valorCancelar+ " valor Sucre: "+ValorPagado);
						}
						
						Double numeroHectareas=in0.getNumHectAseguradas();
						Double numeroHectareasSucre=Double.parseDouble(""+agriObjetoCotizacion.getHectareasAsegurables());
						int comparacionHectareas=numeroHectareas.compareTo(numeroHectareasSucre);
						if(comparacionHectareas!=0){
							ObservacionSucre="Endoso TIPO POLI invalida: hectareas aseguradas QBE: "+numeroHectareas+" hectareas Sucre: "+numeroHectareasSucre;
							throw new  Exception ("Endoso TIPO POLI invalida: hectareas aseguradas QBE: "+numeroHectareas+" hectareas Sucre: "+numeroHectareasSucre);
						}
						
						//SUMA ASEGURADA
						a = new BigDecimal(""+in0.getMontoAsegurado());//valor de la suma asegurada
						roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);//redondeamos a 2 decimales
						Double valorSumaAsegurada= Double.parseDouble(""+roundOff);
						
						/**Guardamos el endoso**/
						//se llena la cotizacion con valores para registrar el endoso
						AgriCotizacionEndosoTransaction agriCotizacionEndosoTransaction = new AgriCotizacionEndosoTransaction();
						AgriCotizacionEndoso agriCotizacionEndoso = new AgriCotizacionEndoso();
						
						//llenamos el endoso.
						
						//fechas de notificacion del canal
						try{
							Date fechaEmisionCanal=formatoDelTexto.parse(in0.getFechaEmision().trim());
							agriCotizacionEndoso.setFechaCanal(fechaEmisionCanal);
						}catch(Exception e){
							ObservacionSucre="Error fecha de Emision "+in0.getFechaPago().trim()+" con formato erroneo, formato esperado: dd/MM/yyyy";
							throw new Exception ("Error fecha de Emision "+in0.getFechaPago().trim()+" con formato erroneo, formato esperado: dd/MM/yyyy");
						}						
						
						agriCotizacionEndoso.setCotizacionId(new BigInteger(ExisteCotizacion.getId()));
						agriCotizacionEndoso.setTipo("CONFIRMACION");
						agriCotizacionEndoso.setValor(ValorPagado);
						agriCotizacionEndoso.setPrimaNeta(ValorPagado);
						agriCotizacionEndoso.setSumaAsegurada(valorSumaAsegurada);
						agriCotizacionEndoso.setSumaAnteriorMov(ExisteCotizacion.getSumaAseguradaTotal());
						agriCotizacionEndoso.setPrimaAnteriorMov(ExisteCotizacion.getPrimaOrigen());
						agriCotizacionEndoso.setTipoSubEndoso(3);
						agriCotizacionEndoso.setNumHectareas(0);
						agriCotizacionEndoso.setAnexo(in0.getAnexo());
						agriCotizacionEndoso.setSuperBancos(ExisteCotizacion.getImpSuperBancos());
						agriCotizacionEndoso.setSeguroCanpesino(ExisteCotizacion.getImpSeguroCampesino());
						agriCotizacionEndoso.setDerechoEmision(ExisteCotizacion.getImpDerechoEmision());
						agriCotizacionEndoso.setIva(ExisteCotizacion.getImpIva());
						agriCotizacionEndoso.setPrimaTotal(ExisteCotizacion.getTotalFactura());
						agriCotizacionEndoso.setTipoSubEndoso(2);//subEndosoNormal
						//almacenamos la prima y la suma
						agriCotizacionEndoso.setPrimaCotizacion(ExisteCotizacion.getPrimaOrigen());
						agriCotizacionEndoso.setSumaCotizacion(ExisteCotizacion.getSumaAseguradaTotal());
						agriCotizacionEndoso.setPrima_total_cotizacion(ExisteCotizacion.getTotalFactura());
						//datos para busqueda.
						agriCotizacionEndoso.setTramite(ExisteCotizacion.getNumeroTramite());
						agriCotizacionEndoso.setCanal("1");
						agriCotizacionEndoso.setPuntoVenta(ExisteCotizacion.getPuntoVenta().getId());
						agriCotizacionEndoso.setCliente(ExisteCotizacion.getAsegurado().getIdentificacion());
						agriCotizacionEndoso.setHectareasAntMov(Double.parseDouble(""+agriObjetoCotizacion.getHectareasAsegurables()));
						
						/***CONTINUAMOS CON EL PROCESO DE REGISTRO DE LA NOTIFICACION****/
						agriObjetoCotizacion.setNumeroOperacion(in0.getOrdenPago());
						agriObjetoCotizacion.setConfirEmiCanal(true);
						//almacenamos los tipos de movimientos
						agriObjetoCotizacion.setTiposMovimientos("CONF");
						agriObjetoCotizacion.setFechaConfirEmiCanal(date);
						//verificamos el formato de la fecha
						try{
							Date fechaPagoI=formatoDelTexto.parse(in0.getFechaPago().trim());
							agriObjetoCotizacion.setFechaDesembolso(fechaPagoI);
						}catch(Exception e){
							ObservacionSucre="Error fecha "+in0.getFechaPago().trim()+" con formato erroneo, formato esperado: dd/MM/yyyy";
							throw new
							  Exception ("Error fecha "+in0.getFechaPago().trim()+" con formato erroneo, formato esperado: dd/MM/yyyy");
						}
						agriObjetoCotizacion.setValorDesembolso(in0.getMontoPago());
						agriObjetoCotizacion.setAnexo(in0.getNumeroFactura());
						AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction = new AgriObjetoCotizacionTransaction();
						agriObjetoCotizacionTransaction.editar(agriObjetoCotizacion);
						//ENDOSO
						agriCotizacionEndosoTransaction.crear(agriCotizacionEndoso);
						
						try{
							/**PROCESO DE CREACION DEL DETALLE DE LA COTIZACION ENDOSO***/
							AgriCotizacionDetalleProcesos agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
							agriCotizacionDetalleProcesos.creardetalleCotizacion(ExisteCotizacion, "CONFIRMACION EMISION");
							/**FIN DE REGISTRO DE ENDOSO**/
						}catch(Exception e){
							e.printStackTrace();
						}
						respuesta.setEstado("OK");
						respuesta.setObservacion(ObservacionSucre);
						//auditoria proceso correctos
						try{
							auditoria.setObjeto("OK");
							auditoria=procesoAuditoria.crear(auditoria);							
						}catch(Exception e){
							e.printStackTrace();
						}
						break;
						
					case "APA"://AUMENTO DE VALORES
						
						/**CONPRUEBO QUE LOS MOVIMIENTOS SE REALIZEN SOBRE UN TRAMITE CONFIRMADO COMO EMITIDO**/
						if(agriObjetoCotizacion.getConfirEmiCanal()==false)
							throw new Exception(" La cotizacion aun no esta confirmada como emitida : POLI, por favor confirmela y luego intentelo. ");
						
						//si esta revocado no se puede emitir de nuevo
						if(ExisteCotizacion.getEstado().getNombre().equals("Cancelada")||ExisteCotizacion.getEstado().getNombre().equals("RevocadoCanal")||ExisteCotizacion.getEstado().getNombre().equals("Revocado")){
							throw new Exception("No se puede emitir una cotizacion anulada anteriormente");
						}
						
						/**verifico que el movimiento no se haya realizado con anterioridad en base al anexo**/
						AgriCotizacionEndosoDAO consultaEndoso= new AgriCotizacionEndosoDAO();
						List<AgriCotizacionEndoso>listadosEndosos=consultaEndoso.buscarPorCotizacionIdAnexo(new BigInteger(ExisteCotizacion.getId()), in0.getAnexo().trim());
						
						if(listadosEndosos.size()>0){
							respuesta.setEstado("OK");
							respuesta.setObservacion("El movimiento APA de anexo "+in0.getAnexo()+" ya fue registrado con anterioridas");
							auditoria.setObjeto("El movimiento APA de anexo "+in0.getAnexo()+" ya fue registrado con anterioridas");
							try{
								auditoria=procesoAuditoria.crear(auditoria);
								
							}catch(Exception e){
								e.printStackTrace();
							}
							return respuesta;
						}
						
						/**Verifico el tipo de aumento
						 * 0) normal, es decir cambia el valor de la suma asegurada por cambio de kit pero mantiene el numero de hectareas
						 * 1) cambio de hectareas, son casos en los que cambia las suma por aumento de hectareas aseguradas
						 * **/
												
						double numeroHectareasMov=0;
						try{
							tipoSubendoso=0+in0.getTipoSubEndoso();//para verificar que no sea null
							if(tipoSubendoso==1){
								numeroHectareasMov=in0.getNumeroHectareasMov();	
								if(numeroHectareasMov==0||numeroHectareasMov==0.0)
									throw new Exception("para aumento de hecatereas las hectaereas deben ser diferentes de 0");
							}
						}catch (Exception e) {
							throw new Exception("No se pudo procesar el movimiento, por favor en enviar en TipoSubEndoso : 0) si es normal, 1) si es cambio de hectareas, y el numero de hectareas a cambiar ");
						}						
						
						/***CREO EL ENDOSO DEL AUMENTO**/
						//registro el estado anterior para auditoria
						try{
							/**PROCESO DE CREACION DEL DETALLE DE LA COTIZACION ENDOSO***/
							AgriCotizacionDetalleProcesos agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
							agriCotizacionDetalleProcesos.creardetalleCotizacion(ExisteCotizacion, "NORMAL");
							/**FIN DE REGISTRO DE ENDOSO**/
						}catch(Exception e){
							e.printStackTrace();
						}
						//VERIFICAMOS LOS VALORES DE LA COTIZACION. CONCORDANCIA.
						
						//SUMA ASEGURADA
						a = new BigDecimal(""+in0.getMontoAsegurado());//valor de la suma asegurada
						roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);//redondeamos a 2 decimales
						valorSumaAsegurada= Double.parseDouble(""+roundOff);
						
						//PRIMA
						a = new BigDecimal(""+in0.getPrimaCalculada());//valor del aumento
						roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);//redondeamos a 2 decimales
						Double valorPrima= Double.parseDouble(""+roundOff);
						
						//verificacion de Prima recibida
						Double primaComprobacion=(valorSumaAsegurada*ExisteCotizacion.getTasaProductoValor())/100;
						
						//comprobacion que el valor de las primas sean iguales
						comparacion=primaComprobacion.compareTo(valorPrima);	
						if(comparacion!=0){
							ObservacionSucre=agriObjetoCotizacion.getObservacionCotizacion();
							ObservacionSucre=ObservacionSucre+"||  valores de primas distintas EN APA, Suma Sucre:"+valorSumaAsegurada+" tasa qbe: "+ExisteCotizacion.getTasaProductoValor()+", valor calculado QBE : "+primaComprobacion+ " valor Sucre: "+valorPrima;
							agriObjetoCotizacion.setObservacionCotizacion(ObservacionSucre);
						}
						
						/**Guardamos el endoso**/
						//se llena la cotizacion con valores para registrar el endoso
						agriCotizacionEndosoTransaction = new AgriCotizacionEndosoTransaction();
						agriCotizacionEndoso = new AgriCotizacionEndoso();
						
						//llenamos el endoso.
						//fechas de notificacion del canal
						try{
							Date fechaEmisionCanal=formatoDelTexto.parse(in0.getFechaEmision().trim());
							agriCotizacionEndoso.setFechaCanal(fechaEmisionCanal);
						}catch(Exception e){
							ObservacionSucre="Error fecha de Emision "+in0.getFechaPago().trim()+" con formato erroneo, formato esperado: dd/MM/yyyy";
							throw new
							  Exception ("Error fecha de Emision "+in0.getFechaPago().trim()+" con formato erroneo, formato esperado: dd/MM/yyyy");
						}
						agriCotizacionEndoso.setCotizacionId(new BigInteger(ExisteCotizacion.getId()));
						agriCotizacionEndoso.setTipo("AUMENTO");
						agriCotizacionEndoso.setValor(valorPrima);
						agriCotizacionEndoso.setPrimaNeta(valorPrima);
						agriCotizacionEndoso.setSumaAsegurada(valorSumaAsegurada);
						agriCotizacionEndoso.setSumaAnteriorMov(ExisteCotizacion.getSumaAseguradaTotal());
						agriCotizacionEndoso.setPrimaAnteriorMov(ExisteCotizacion.getPrimaOrigen());
						agriCotizacionEndoso.setAnexo(in0.getAnexo());
						
						if(tipoSubendoso==0){
							agriCotizacionEndoso.setTipoSubEndoso(0);
							agriCotizacionEndoso.setNumHectareas(0);
						}else{//caso de ser recalculo por numero de hectareas..
							agriCotizacionEndoso.setTipoSubEndoso(1);
							agriCotizacionEndoso.setNumHectareas(numeroHectareasMov);
						}
						//RECALCULAMOS LOS IMPUESTOS
						AgriCotizacionDetalleProcesos agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
						Cotizacion detallesImpuestos = new Cotizacion();
						detallesImpuestos=agriCotizacionDetalleProcesos.DetallesCalculo(ExisteCotizacion, valorPrima);
						//asignoImpuestos
						agriCotizacionEndoso.setSeguroCanpesino(detallesImpuestos.getImpSeguroCampesino());
						agriCotizacionEndoso.setSuperBancos(detallesImpuestos.getImpSuperBancos());
						agriCotizacionEndoso.setDerechoEmision(detallesImpuestos.getImpDerechoEmision());
						agriCotizacionEndoso.setIva(detallesImpuestos.getImpIva());
						agriCotizacionEndoso.setPrimaTotal(detallesImpuestos.getTotalFactura());
						//datos para busqueda.
						agriCotizacionEndoso.setTramite(ExisteCotizacion.getNumeroTramite());
						agriCotizacionEndoso.setCanal("1");
						agriCotizacionEndoso.setPuntoVenta(ExisteCotizacion.getPuntoVenta().getId());
						agriCotizacionEndoso.setCliente(ExisteCotizacion.getAsegurado().getIdentificacion());
						
						/**RECALCULO DE LOS VALORES DE LA COTIZACION
						 * 1) Se suma el valor recibido a la prima y suma asegurada
						 * 2) Se recalcula los costros de produccion y se mantiene el numero de hectareas
						 * 3) Recalculo los impuestos
						 * 4) guardo nuevo estado para auditoria
						 * **/
						
						//tomamos la prima y suma asegura, sumamos y luego dividimos para el numero de hectareas para calcularo el costo de produccion. :)
						Double primaActual = ExisteCotizacion.getPrimaOrigen();
						Double sumaAseguradaActual=ExisteCotizacion.getSumaAseguradaTotal();
						
						double PrimaTotal= primaActual+valorPrima;
						a = new BigDecimal(""+PrimaTotal);//prima total
						roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);//redondeamos a 2 decimales
						PrimaTotal= Double.parseDouble(""+roundOff);
						
						
						double SumaAseguradaTotal=sumaAseguradaActual+valorSumaAsegurada;
						a = new BigDecimal(""+SumaAseguradaTotal);//prima total
						roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);//redondeamos a 2 decimales
						SumaAseguradaTotal= Double.parseDouble(""+roundOff);
						
						//hallamos el costo de produccion;
						double hectareasAseguradas=Double.parseDouble(""+agriObjetoCotizacion.getHectareasAsegurables());//hectareas aseguradas.
						double costoProduccionTotal=SumaAseguradaTotal/hectareasAseguradas;
						
						/* PROCESO DE CALCULO DE COMPONENTES */
						
						ExisteCotizacion=agriCotizacionDetalleProcesos.DetallesCalculo(ExisteCotizacion, PrimaTotal);
						
						
						ExisteCotizacion.setPrimaNetaTotal(""+PrimaTotal);
						ExisteCotizacion.setPrimaOrigen(PrimaTotal);
						ExisteCotizacion.setSumaAseguradaTotal(SumaAseguradaTotal);
						//almacenamos la prima y la suma
						agriCotizacionEndoso.setPrimaCotizacion(ExisteCotizacion.getPrimaOrigen());
						agriCotizacionEndoso.setSumaCotizacion(ExisteCotizacion.getSumaAseguradaTotal());
						agriCotizacionEndoso.setPrima_total_cotizacion(ExisteCotizacion.getTotalFactura());
						
						CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
						cotizacionTransaction.editar(ExisteCotizacion);//actulizamo la cotizacion
						
						//Cotizacion Detalle
						cotizacionDetalle.setSumaAseguradaItem(SumaAseguradaTotal);
						cotizacionDetalle.setPrimaNetaItem(PrimaTotal);
						CotizacionDetalleTransaction cotizacionDetalleTransaction = new CotizacionDetalleTransaction();
						cotizacionDetalleTransaction.editar(cotizacionDetalle);
						
						//agriObjetoCotizacion
						if(tipoSubendoso==1){
							/**SUMAMOS LAS HECTAREAS POR LAS QUE SE SUMO LA PRIMA**/
							double numeroHectareasOriginales=Double.parseDouble(""+agriObjetoCotizacion.getHectareasAsegurables());
							double numeroHectareasTotal=numeroHectareasOriginales+numeroHectareasMov;
							a = new BigDecimal(""+numeroHectareasTotal);//prima total
							roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);//redondeamos a 2 decimales
							numeroHectareasTotal= Double.parseDouble(""+roundOff);
							agriObjetoCotizacion.setHectareasAsegurables(Float.parseFloat(""+numeroHectareasTotal));
						}else
							agriObjetoCotizacion.setCostoProduccion(Float.parseFloat(""+costoProduccionTotal));//si es normal se recalcula el costo de produccion
						agriObjetoCotizacion.setMontoCredito(Float.parseFloat(""+SumaAseguradaTotal));
						agriObjetoCotizacion.setCostoProduccion(Float.parseFloat(""+costoProduccionTotal));
						agriObjetoCotizacion.setTieneMov(true);
						String tiposMovimientos=agriObjetoCotizacion.getTiposMovimientos();
						agriObjetoCotizacion.setTiposMovimientos(tiposMovimientos+"-AUM");
						agriObjetoCotizacion.setFechaConfirEmiCanal(date);
						AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction2= new AgriObjetoCotizacionTransaction();
						agriObjetoCotizacionTransaction2.editar(agriObjetoCotizacion);
						
						//ENDOSO
						agriCotizacionEndoso.setHectareasAntMov(agriObjetoCotizacion.getHectareasAsegurables());
						agriCotizacionEndosoTransaction.crear(agriCotizacionEndoso);
												
						try{
							/**PROCESO DE CREACION DEL DETALLE DE LA COTIZACION ENDOSO***/
							agriCotizacionDetalleProcesos.creardetalleCotizacion(ExisteCotizacion, "AUMENTO");
							/**FIN DE REGISTRO DE ENDOSO**/
						}catch(Exception e){
							e.printStackTrace();
						}						
						respuesta.setEstado("OK");
						respuesta.setObservacion("PROCESO CORRECTO");
						
						//Auditoria Proceso
						try{
							auditoria.setObjeto("OK");
							auditoria=procesoAuditoria.crear(auditoria);							
						}catch(Exception e){
							e.printStackTrace();
						}
						/**NOTIFICAMOS A QBE POR CORREOS ELECTRONICOS**/
						List<String> usuario = new ArrayList<>();
						usuario.add("luis.caiza@smartwork.com.ec");
						usuario.add("Leonardo.Regalado@qbe.com.ec");
						usuario.add("Sandy.Jaramillo@qbe.com.ec");
						String asunto="AUMENTO SUMA ASEGURADA";
						AgriSucreNotificacionErrores notificacionErrores= new AgriSucreNotificacionErrores();
						String Html=notificacionErrores.GeneradorHtml(in0.getNumeroResolucion()
								, "EL TRAMITE: "+in0.getNumeroResolucion()+" tuvo un aumento de suma asegurada y prima (Monto/"+valorSumaAsegurada+" || Prima/"+valorPrima+")", "");
						
						for(String receptor:usuario){
							//Utilitarios.envioMail(receptor, asunto, cuerpo);
							Utilitarios.envioMail(receptor, asunto, Html);
						}
						
						break;
						
					case "APR"://REBAJA DE VALORES
						
						/**CONPRUEBO QUE LOS MOVIMIENTOS SE REALIZEN SOBRE UN TRAMITE CONFIRMADO COMO EMITIDO**/
						if(agriObjetoCotizacion.getConfirEmiCanal()==false)
							throw new Exception(" La cotizacion aun no esta confirmada como emitida : POLI, por favor confirmela y luego intentelo. ");
						
						//si esta revocado no se puede emitir de nuevo
						if(ExisteCotizacion.getEstado().getNombre().equals("Cancelada")||ExisteCotizacion.getEstado().getNombre().equals("RevocadoCanal")||ExisteCotizacion.getEstado().getNombre().equals("Revocado")){
							throw new Exception("No se puede emitir una cotizacion anulada anteriormente");
						}
						
						/**verifico que el movimiento no se haya realizado con anterioridad en base al anexo**/
						consultaEndoso= new AgriCotizacionEndosoDAO();
						listadosEndosos=consultaEndoso.buscarPorCotizacionIdAnexo(new BigInteger(ExisteCotizacion.getId()), in0.getAnexo().trim());
						
						if(listadosEndosos.size()>0){
							respuesta.setEstado("OK");
							respuesta.setObservacion("El movimiento APR de anexo "+in0.getAnexo()+" ya fue registrado con anterioridas");
							auditoria.setObjeto("El movimiento APR de anexo "+in0.getAnexo()+" ya fue registrado con anterioridas");
							try{
								auditoria=procesoAuditoria.crear(auditoria);
								
							}catch(Exception e){
								e.printStackTrace();
							}
							return respuesta;
						}				
												
						/**Verifico el tipo de aumento
						 * 0) normal, es decir cambia el valor de la suma asegurada por cambio de kit pero mantiene el numero de hectareas
						 * 1) cambio de hectareas, son casos en los que cambia las suma por aumento de hectareas aseguradas
						 * **/
						
						tipoSubendoso=0;
						numeroHectareasMov=0;
						try{
							tipoSubendoso=0+in0.getTipoSubEndoso();//para verificar que no sea null
							if(tipoSubendoso==1){
								numeroHectareasMov=Math.abs(in0.getNumeroHectareasMov());
								if(numeroHectareasMov==0||numeroHectareasMov==0.0)
									throw new Exception("para rebaja de hecatereas las hectaereas deben ser diferentes de 0");
							}
						}catch (Exception e) {
							throw new Exception("No se pudo procesar el movimiento, por favor en enviar en TipoSubEndoso : 0) si es normal, 1) si es cambio de hectareas, y el numero de hectareas a cambiar ");
						}
						
						/***CREO EL ENDOSO DEL AUMENTO**/
						//registro el estado anterior para auditoria
						try{
							/**PROCESO DE CREACION DEL DETALLE DE LA COTIZACION ENDOSO***/
							agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
							agriCotizacionDetalleProcesos.creardetalleCotizacion(ExisteCotizacion, "NORMAL");
							/**FIN DE REGISTRO DE ENDOSO**/
						}catch(Exception e){
							e.printStackTrace();
						}
						//VERIFICAMOS LOS VALORES DE LA COTIZACION. CONCORDANCIA.
						
						//SUMA ASEGURADA
						a = new BigDecimal(""+in0.getMontoAsegurado());//valor de la suma asegurada
						roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);//redondeamos a 2 decimales
						valorSumaAsegurada= Double.parseDouble(""+roundOff);
						
						//PRIMA
						a = new BigDecimal(""+in0.getPrimaCalculada());//valor del aumento
						roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);//redondeamos a 2 decimales
						valorPrima= Double.parseDouble(""+roundOff);
						
						//Prima y Sumas Actuales
						primaActual = ExisteCotizacion.getPrimaOrigen();
						sumaAseguradaActual=ExisteCotizacion.getSumaAseguradaTotal();
						
						//verificacion de Prima recibida
						primaComprobacion=(valorSumaAsegurada*ExisteCotizacion.getTasaProductoValor())/100;
						
						comparacion=primaComprobacion.compareTo(valorPrima);	
						if(comparacion!=0){
							ObservacionSucre=agriObjetoCotizacion.getObservacionCotizacion();
							ObservacionSucre=ObservacionSucre+"||  valores de primas distintas EN APR, Suma Sucre:"+valorSumaAsegurada+" tasa qbe: "+ExisteCotizacion.getTasaProductoValor()+", valor calculado QBE : "+primaComprobacion+ " valor Sucre: "+valorPrima;
							agriObjetoCotizacion.setObservacionCotizacion(ObservacionSucre);
							
						}
												
						/**Guardamos el endoso**/
						//se llena la cotizacion con valores para registrar el endoso
						agriCotizacionEndoso = new AgriCotizacionEndoso();
						agriCotizacionEndosoTransaction = new AgriCotizacionEndosoTransaction();
						
						//llenamos el endoso.
						try{
							Date fechaEmisionCanal=formatoDelTexto.parse(in0.getFechaEmision().trim());
							agriCotizacionEndoso.setFechaCanal(fechaEmisionCanal);
						}catch(Exception e){
							ObservacionSucre="Error fecha de Emision "+in0.getFechaPago().trim()+" con formato erroneo, formato esperado: dd/MM/yyyy";
							throw new
							  Exception ("Error fecha de Emision "+in0.getFechaPago().trim()+" con formato erroneo, formato esperado: dd/MM/yyyy");
						}
						agriCotizacionEndoso.setCotizacionId(new BigInteger(ExisteCotizacion.getId()));
						agriCotizacionEndoso.setTipo("REBAJA");
						agriCotizacionEndoso.setValor(Math.abs(valorPrima)*(-1));
						agriCotizacionEndoso.setPrimaNeta(Math.abs(valorPrima)*(-1));
						agriCotizacionEndoso.setSumaAsegurada(Math.abs(valorSumaAsegurada)*(-1));	
						agriCotizacionEndoso.setSumaAnteriorMov(ExisteCotizacion.getSumaAseguradaTotal());
						agriCotizacionEndoso.setPrimaAnteriorMov(ExisteCotizacion.getPrimaOrigen());
						agriCotizacionEndoso.setAnexo(in0.getAnexo());
						if(tipoSubendoso==0){
							agriCotizacionEndoso.setTipoSubEndoso(0);
							agriCotizacionEndoso.setNumHectareas(0);
						}else{//caso de ser recalculo por numero de hectareas..
							agriCotizacionEndoso.setTipoSubEndoso(1);
							agriCotizacionEndoso.setNumHectareas(numeroHectareasMov*(-1));
						}
						
						detallesImpuestos = new Cotizacion();
						agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
						
						detallesImpuestos=agriCotizacionDetalleProcesos.DetallesCalculo(ExisteCotizacion, Math.abs(valorPrima));
						//asignoImpuestos
						agriCotizacionEndoso.setSeguroCanpesino(Math.abs(detallesImpuestos.getImpSeguroCampesino())*(-1));
						agriCotizacionEndoso.setSuperBancos(Math.abs(detallesImpuestos.getImpSuperBancos())*(-1));
						agriCotizacionEndoso.setDerechoEmision(Math.abs(detallesImpuestos.getImpDerechoEmision())*(-1));
						agriCotizacionEndoso.setIva(Math.abs(detallesImpuestos.getImpIva())*(-1));
						agriCotizacionEndoso.setPrimaTotal(Math.abs(detallesImpuestos.getTotalFactura())*(-1));
						//datos para busqueda.
						agriCotizacionEndoso.setTramite(ExisteCotizacion.getNumeroTramite());
						agriCotizacionEndoso.setCanal("1");
						agriCotizacionEndoso.setPuntoVenta(ExisteCotizacion.getPuntoVenta().getId());
						agriCotizacionEndoso.setCliente(ExisteCotizacion.getAsegurado().getIdentificacion());
						
						/**RECALCULO DE LOS VALORES DE LA COTIZACION
						 * 1) Se resta el valor recibido a la prima y suma asegurada
						 * 2) Se recalcula los costros de produccion y se mantiene el numero de hectareas
						 * 3) Recalculo los impuestos
						 * 4) guardo nuevo estado para auditoria
						 * **/
						
						//verificamos que el valor no sea menos al que se tiene de prima
						
						
						//tomamos la prima y suma asegura, sumamos y luego dividimos para el numero de hectareas para calcularo el costo de produccion. :)
						PrimaTotal= primaActual-Math.abs(valorPrima);
						a = new BigDecimal(""+PrimaTotal);//prima total
						roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);//redondeamos a 2 decimales
						PrimaTotal= Double.parseDouble(""+roundOff);
						
						//si la prima ingresada es mayor a la prima calculada
						if(PrimaTotal<0){
							ObservacionSucre="Endoso TIPO APR invalido: valores de prima de rebaja mayor a prima cotizada, valor QBE: "+primaActual+ " valor Sucre: "+valorPrima;
							throw new  Exception ("Endoso TIPO APR invalida: valores de prima de rebaja mayor a prima cotizada, valor QBE: "+primaActual+ " valor Sucre: "+valorPrima);
						}
						
						SumaAseguradaTotal=sumaAseguradaActual-Math.abs(valorSumaAsegurada);
						a = new BigDecimal(""+SumaAseguradaTotal);//prima total
						roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);//redondeamos a 2 decimales
						SumaAseguradaTotal= Double.parseDouble(""+roundOff);
						
						// si la suma ingresada es mayor a la suma cotizada
						if(SumaAseguradaTotal<0){
							ObservacionSucre="Endoso TIPO APR invalido: valores de Sumas Aseguradas de rebaja mayor a Suma cotizada, valor QBE: "+primaActual+ " valor Sucre: "+valorPrima;
							throw new  Exception ("Endoso TIPO APR invalido: valores de Sumas Aseguradas de rebaja mayor a Suma cotizada, valor QBE: "+primaActual+ " valor Sucre: "+valorPrima);
						}
												
						//hallamos el costo de produccion;
						hectareasAseguradas=Double.parseDouble(""+agriObjetoCotizacion.getHectareasAsegurables());//hectareas aseguradas.
						costoProduccionTotal=SumaAseguradaTotal/hectareasAseguradas;
						
						/* PROCESO DE CALCULO DE COMPONENTES */
						agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
						ExisteCotizacion=agriCotizacionDetalleProcesos.DetallesCalculo(ExisteCotizacion, PrimaTotal);
												
						ExisteCotizacion.setPrimaNetaTotal(""+PrimaTotal);
						ExisteCotizacion.setPrimaOrigen(PrimaTotal);
						ExisteCotizacion.setSumaAseguradaTotal(SumaAseguradaTotal);
						//almacenamos la prima y la suma
						agriCotizacionEndoso.setPrimaCotizacion(ExisteCotizacion.getPrimaOrigen());
						agriCotizacionEndoso.setSumaCotizacion(ExisteCotizacion.getSumaAseguradaTotal());
						agriCotizacionEndoso.setPrima_total_cotizacion(ExisteCotizacion.getTotalFactura());
						
						
						cotizacionTransaction = new CotizacionTransaction();
						cotizacionTransaction.editar(ExisteCotizacion);//actulizamo la cotizacion
						
						//Cotizacion Detalle
						cotizacionDetalle.setSumaAseguradaItem(SumaAseguradaTotal);
						cotizacionDetalle.setPrimaNetaItem(PrimaTotal);
						cotizacionDetalleTransaction = new CotizacionDetalleTransaction();
						cotizacionDetalleTransaction.editar(cotizacionDetalle);
						
						//agriObjetoCotizacion
						if(tipoSubendoso==1){
							/**SUMAMOS LAS HECTAREAS POR LAS QUE SE SUMO LA PRIMA**/
							double numeroHectareasOriginales=Double.parseDouble(""+agriObjetoCotizacion.getHectareasAsegurables());
							if(numeroHectareasOriginales<numeroHectareasMov)
								throw new  Exception ("Numero de hectareas de rebaja mayor a numero de hectareas aseguradas: QBE: "+numeroHectareasOriginales+" Sucre : "+numeroHectareasMov);
							double numeroHectareasTotal=numeroHectareasOriginales-numeroHectareasMov;
							a = new BigDecimal(""+numeroHectareasTotal);//prima total
							roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);//redondeamos a 2 decimales
							numeroHectareasTotal= Double.parseDouble(""+roundOff);
							agriObjetoCotizacion.setHectareasAsegurables(Float.parseFloat(""+numeroHectareasTotal));
						}else
							agriObjetoCotizacion.setCostoProduccion(Float.parseFloat(""+costoProduccionTotal));//si es normal se recalcula el costo de produccion
						
						agriObjetoCotizacion.setMontoCredito(Float.parseFloat(""+SumaAseguradaTotal));
						agriObjetoCotizacion.setTieneMov(true);
						tiposMovimientos=agriObjetoCotizacion.getTiposMovimientos();
						agriObjetoCotizacion.setTiposMovimientos(tiposMovimientos+"-REB");
						agriObjetoCotizacion.setFechaConfirEmiCanal(date);
						agriObjetoCotizacionTransaction2= new AgriObjetoCotizacionTransaction();
						agriObjetoCotizacionTransaction2.editar(agriObjetoCotizacion);
						
						//ENDOSO
						agriCotizacionEndoso.setHectareasAntMov(agriObjetoCotizacion.getHectareasAsegurables());
						agriCotizacionEndosoTransaction.crear(agriCotizacionEndoso);
												
						try{
							/**PROCESO DE CREACION DEL DETALLE DE LA COTIZACION ENDOSO***/
							agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
							agriCotizacionDetalleProcesos.creardetalleCotizacion(ExisteCotizacion, "REBAJA");
							/**FIN DE REGISTRO DE ENDOSO**/
						}catch(Exception e){
							e.printStackTrace();
						}						
						respuesta.setEstado("OK");
						respuesta.setObservacion("PROCESO CORRECTO");
						//Auditoria Proceso
						try{
							auditoria.setObjeto("OK");
							auditoria=procesoAuditoria.crear(auditoria);							
						}catch(Exception e){
							e.printStackTrace();
						}
						/**NOTIFICAMOS A QBE POR CORREOS ELECTRONICOS**/
						usuario = new ArrayList<>();
						usuario.add("luis.caiza@smartwork.com.ec");
						usuario.add("Leonardo.Regalado@qbe.com.ec");
						usuario.add("Sandy.Jaramillo@qbe.com.ec");
						asunto="DISMINUCION DE SUMA ASEGURADA";
						notificacionErrores= new AgriSucreNotificacionErrores();
						Html=notificacionErrores.GeneradorHtml(in0.getNumeroResolucion()
								, "EL TRAMITE: "+in0.getNumeroResolucion()+" tuvo una disminucion de suma asegurada y prima (Monto/ -"+valorSumaAsegurada+" || Prima/-"+valorPrima+")", "");
						
						for(String receptor:usuario){
							//Utilitarios.envioMail(receptor, asunto, cuerpo);
							Utilitarios.envioMail(receptor, asunto, Html);
						}
						break;
					case "ANU"://ANULACION DE COTIZACION
						//si ya esta anulada solo respondo OK no realizo el proceso de nuevo, para mantener la integridad de los movientos
						if(ExisteCotizacion.getEstado().getNombre().equals("Anulado")||ExisteCotizacion.getEstado().getNombre().equals("RevocadoCanal")||ExisteCotizacion.getEstado().getNombre().equals("Cancelada")){
							ObservacionSucre="Error la cotizacion con el numero de tramite "+NumeroTramite.trim()+" fue revocada no se puede continuar con el proceso";
							respuesta.setEstado("OK");
							respuesta.setObservacion("ANULACION YA NOTIFICADA CON ANTERIORIDAD");							
							auditoria.setObjeto("ANULACION YA NOTIFICADA CON ANTERIORIDAD");
							try{
								auditoria=procesoAuditoria.crear(auditoria);
								
							}catch(Exception e){
								e.printStackTrace();
							}							
							return respuesta;
						}
						
						/**verifico que el movimiento no se haya realizado con anterioridad en base al anexo**/
						consultaEndoso= new AgriCotizacionEndosoDAO();
						listadosEndosos=consultaEndoso.buscarPorCotizacionIdAnexo(new BigInteger(ExisteCotizacion.getId()), in0.getAnexo().trim());
						
						if(listadosEndosos.size()>0){
							respuesta.setEstado("OK");
							respuesta.setObservacion("El movimiento ANU de anexo "+in0.getAnexo()+" ya fue registrado con anterioridas");
							auditoria.setObjeto("El movimiento ANU de anexo "+in0.getAnexo()+" ya fue registrado con anterioridas");
							try{
								auditoria=procesoAuditoria.crear(auditoria);
								
							}catch(Exception e){
								e.printStackTrace();
							}
							return respuesta;
						}
						
						//EN CASO DE UNA ANULACION LA COTIZACION CAMBIA DE ESTADO 
						
						//COMPARO VALORES DE COTIZACION
						valorCancelar=ExisteCotizacion.getPrimaOrigen();
						a = new BigDecimal(""+in0.getMontoPago());
						roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
						ValorPagado=Double.parseDouble(""+roundOff);
						comparacion=valorCancelar.compareTo(Math.abs(ValorPagado));	
						if(comparacion!=0){
							ObservacionSucre="Endoso TIPO ANU invalida: valores distintos, valor QBE: "+valorCancelar+ " valor Sucre: "+ValorPagado;
							throw new  Exception ("Endoso TIPO ANU invalida: valores distintos, valor QBE: "+valorCancelar+ " valor Sucre: "+ValorPagado);
						}
						
						/**Guardamos el endoso**/
						//se llena la cotizacion con valores para registrar el endoso
						agriCotizacionEndoso = new AgriCotizacionEndoso();
						agriCotizacionEndosoTransaction = new AgriCotizacionEndosoTransaction();
						
						//llenamos el endoso.
						try{
							Date fechaEmisionCanal=formatoDelTexto.parse(in0.getFechaEmision().trim());
							agriCotizacionEndoso.setFechaCanal(fechaEmisionCanal);
						}catch(Exception e){
							ObservacionSucre="Error fecha de Emision "+in0.getFechaPago().trim()+" con formato erroneo, formato esperado: dd/MM/yyyy";
							throw new
							  Exception ("Error fecha de Emision "+in0.getFechaPago().trim()+" con formato erroneo, formato esperado: dd/MM/yyyy");
						}
						agriCotizacionEndoso.setCotizacionId(new BigInteger(ExisteCotizacion.getId()));
						agriCotizacionEndoso.setTipo("CANCELACION");
						agriCotizacionEndoso.setValor(Math.abs(ExisteCotizacion.getPrimaOrigen())*(-1));
						agriCotizacionEndoso.setPrimaNeta(Math.abs(ExisteCotizacion.getPrimaOrigen())*(-1));
						agriCotizacionEndoso.setSumaAsegurada(Math.abs(ExisteCotizacion.getSumaAseguradaTotal())*(-1));	
						agriCotizacionEndoso.setSumaAnteriorMov(ExisteCotizacion.getSumaAseguradaTotal());
						agriCotizacionEndoso.setPrimaAnteriorMov(ExisteCotizacion.getPrimaOrigen());
						agriCotizacionEndoso.setTipoSubEndoso(2);
						agriCotizacionEndoso.setNumHectareas(0);
						agriCotizacionEndoso.setAnexo(in0.getAnexo());
						agriCotizacionEndoso.setHectareasAntMov(Double.parseDouble(""+agriObjetoCotizacion.getHectareasAsegurables()));
						detallesImpuestos = new Cotizacion();
						agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
						
						detallesImpuestos=agriCotizacionDetalleProcesos.DetallesCalculo(ExisteCotizacion, Math.abs(ExisteCotizacion.getPrimaOrigen()));
						//asignoImpuestos
						agriCotizacionEndoso.setSeguroCanpesino(Math.abs(detallesImpuestos.getImpSeguroCampesino())*(-1));
						agriCotizacionEndoso.setSuperBancos(Math.abs(detallesImpuestos.getImpSuperBancos())*(-1));
						agriCotizacionEndoso.setDerechoEmision(Math.abs(detallesImpuestos.getImpDerechoEmision())*(-1));
						agriCotizacionEndoso.setIva(Math.abs(detallesImpuestos.getImpIva())*(-1));
						agriCotizacionEndoso.setPrimaTotal(Math.abs(detallesImpuestos.getTotalFactura())*(-1));
						
						//datos para busqueda.
						agriCotizacionEndoso.setTramite(ExisteCotizacion.getNumeroTramite());
						agriCotizacionEndoso.setCanal("1");
						agriCotizacionEndoso.setPuntoVenta(ExisteCotizacion.getPuntoVenta().getId());
						agriCotizacionEndoso.setCliente(ExisteCotizacion.getAsegurado().getIdentificacion());
						
						/**RECALCULO DE LOS VALORES DE LA COTIZACION
						 * 1) Se resta el valor recibido a la prima y suma asegurada
						 * 2) Se recalcula los costros de produccion y se mantiene el numero de hectareas
						 * 3) Recalculo los impuestos
						 * 4) guardo nuevo estado para auditoria
						 * **/
						//actualizo los valores de la cotizacion
						ExisteCotizacion.setPrimaNetaTotal("-"+ExisteCotizacion.getPrimaNetaTotal());
						ExisteCotizacion.setPrimaOrigen(ExisteCotizacion.getPrimaOrigen()*(-1));
						ExisteCotizacion.setSumaAseguradaTotal(ExisteCotizacion.getSumaAseguradaTotal()*(-1));
						ExisteCotizacion.setImpDerechoEmision(ExisteCotizacion.getImpDerechoEmision()*(-1));
						ExisteCotizacion.setImpSeguroCampesino(ExisteCotizacion.getImpSeguroCampesino()*(-1));
						ExisteCotizacion.setImpSuperBancos(ExisteCotizacion.getImpSuperBancos()*(-1));
						ExisteCotizacion.setImpIva(ExisteCotizacion.getImpIva()*(-1));
						ExisteCotizacion.setTotalFactura(ExisteCotizacion.getTotalFactura()*(-1));
						
						EstadoDAO estadoDAO = new EstadoDAO();
						Estado estado=estadoDAO.buscarPorNombre("Cancelada", "Cotizacion");
						ExisteCotizacion.setEstado(estado);
						//almacenamos la prima y la suma
						agriCotizacionEndoso.setPrimaCotizacion(ExisteCotizacion.getPrimaOrigen());//es negativa 
						agriCotizacionEndoso.setSumaCotizacion(ExisteCotizacion.getSumaAseguradaTotal());
						agriCotizacionEndoso.setPrima_total_cotizacion(ExisteCotizacion.getTotalFactura());
						
						//actualizo el estado de la cotizacion
						cotizacionTransaction= new CotizacionTransaction();
						cotizacionTransaction.editar(ExisteCotizacion);	
						
						//Actualizo la cotizacion detalle
						//Cotizacion Detalle
						cotizacionDetalle.setSumaAseguradaItem(cotizacionDetalle.getSumaAseguradaItem()*(-1));
						cotizacionDetalle.setPrimaNetaItem(cotizacionDetalle.getPrimaNetaItem()*(-1));
						cotizacionDetalleTransaction = new CotizacionDetalleTransaction();
						cotizacionDetalleTransaction.editar(cotizacionDetalle);
						
						
						//ENDOSO
						agriCotizacionEndosoTransaction.crear(agriCotizacionEndoso);
						
						//actulizo la AGRIOBJETOCOTIZACION
						
						//actualizo la agriObjeto cotizacion con las causa del problema
						agriObjetoCotizacion.setObservacionCotizacion(in0.getCausa());
						tiposMovimientos=agriObjetoCotizacion.getTiposMovimientos();
						agriObjetoCotizacion.setMontoCredito(agriObjetoCotizacion.getMontoCredito()*(-1));
						agriObjetoCotizacion.setTiposMovimientos(tiposMovimientos+"-CAN");
						agriObjetoCotizacion.setFechaConfirEmiCanal(date);
						agriObjetoCotizacionTransaction= new AgriObjetoCotizacionTransaction();
						agriObjetoCotizacionTransaction.editar(agriObjetoCotizacion);
						try{
							/**PROCESO DE CREACION DEL DETALLE DE LA COTIZACION ENDOSO***/
							agriCotizacionDetalleProcesos = new AgriCotizacionDetalleProcesos();
							agriCotizacionDetalleProcesos.creardetalleCotizacion(ExisteCotizacion, "Cancelada");
							/**FIN DE REGISTRO DE ENDOSO**/
						}catch(Exception e){
							e.printStackTrace();
						}						
						respuesta.setEstado("OK");
						respuesta.setObservacion("PROCESO CORRECTO");
						//Auditoria Proceso
						try{
							auditoria.setObjeto("OK");
							auditoria=procesoAuditoria.crear(auditoria);							
						}catch(Exception e){
							e.printStackTrace();
						}						
						break;			
					}					
				}else{
					/***TEMPORALMENTE ALMACENAMOS LA INFORMACION. ***/					
					auditoria.setEstado("Error");
					auditoria.setObjeto("El n�mero de tramite "+in0.getNumeroResolucion()+" para proceso de emision no existe");
					procesoAuditoria.crear(auditoria);
					throw new
					  Exception ("El n�mero de tramite "+in0.getNumeroResolucion()+" para proceso de emision no existe");
				}
			  
		} catch (Exception e) {
			//nueva auditoria
			AgriSucreAuditoria auditoria2 = new AgriSucreAuditoria();
			AgriSucreAuditoriaTransaction procesoAuditoria2 = new AgriSucreAuditoriaTransaction();
			auditoria2.setTramite(in0.getNumeroResolucion());
			auditoria2.setObjeto("ERROR: "+e.getMessage());
			auditoria2.setFecha(sq2);
			auditoria2.setCanal("SUCRE EMISION");
			try{
				auditoria=procesoAuditoria.crear(auditoria);
				
			}catch(Exception e1){
				e.printStackTrace();
			}
			if(e.getMessage().equals("Existe")){//si ya fue registrado anterioremente solo notificamos no como error
				respuesta.setEstado("OK");
				respuesta.setObservacion("Confirmacion ya realizada con anterioridad");
				try{
					auditoria2.setEstado("OK");
					procesoAuditoria.editar(auditoria2);
					}catch(Exception e1){
						e1.printStackTrace();
					}
				e.printStackTrace();
			}else{
				List<String> usuario = new ArrayList<>();
				usuario.add("luis.caiza@smartwork.com.ec");
				String asunto="Error en Proceso de Cotizacion Sucre";
				/*String cuerpo="<p>Estimado Usuario:  El n&uacute;mero de tr&aacute;mite Sucre:  "+in0.getNumeroResolucion()
						+ " No a podido ser procesado por el cotizador Agricola, por la siguiente raz&oacute;n:</p>"+e.getMessage();
				*/
				AgriSucreNotificacionErrores notificacionErrores= new AgriSucreNotificacionErrores();
				String Html=notificacionErrores.GeneradorHtml(in0.getNumeroResolucion(), e.getMessage(), "");
				
				for(String receptor:usuario){
					//Utilitarios.envioMail(receptor, asunto, cuerpo);
					Utilitarios.envioMail(receptor, asunto, Html);
				}
				respuesta.setEstado("Error");
				respuesta.setObservacion("Error: " + e.getMessage());
				
				try{
					auditoria2.setEstado("Error");
					
					procesoAuditoria2.editar(auditoria2);
					}catch(Exception e1){
						e1.printStackTrace();
					}
				e.printStackTrace();
			}			
		}
		return respuesta;
	}
	
	/*metodo para actualizar las fechas de siembra en base a la fecha de Siembra f*/
	
	public RespuestaCambio solicitudCambioFecha(String tramite, String fechaSiembra){
		RespuestaCambio respuesta = new RespuestaCambio();
		
		/*PROCESO DE AUDITORIA */
		AgriSucreAuditoria auditoria = new AgriSucreAuditoria();
		AgriSucreAuditoriaTransaction procesoAuditoria = new AgriSucreAuditoriaTransaction();
		auditoria.setTramite(tramite);
		auditoria.setObjeto("tramite: "+tramite+" fecha Siembra: "+fechaSiembra );
		java.util.Date date2 = new java.util.Date();
		java.sql.Timestamp sq2 = new java.sql.Timestamp(date2.getTime());
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		auditoria.setFecha(sq2);
		auditoria.setCanal("SUCRE CAMBIO FECHA");
		try{
			auditoria=procesoAuditoria.crear(auditoria);
		}catch(Exception e){
			e.printStackTrace();
		}
		/*FIN DE PROCESO*/
		
		/*PROCESO DE ACTUALIZACION DE FECHA DE SIEMBRA*/
		try{
			
			//enviaban las fechas solo con 2 numeros, se puso el filtro para que envien en el formato correcto
			String AnioSiembra=fechaSiembra;
			String[] elementosFecha = AnioSiembra.split("/");
			int comprobacionAnio=elementosFecha[2].length();
						
			if(comprobacionAnio !=4)
				throw new Exception("Fecha de siembra no valida "+fechaSiembra+" no valida, debe estar en el formato dd/MM/yyyy");
			
			
			/*Proceso de validacion de campos*/
			if(tramite.equals("")||tramite==null){
				throw new Exception("Error el numero de tramite no puede ser null o vacio");
			}
			
			
			if(fechaSiembra.equals("")||fechaSiembra==null){
				throw new Exception("Error la fecha de siembra no puede ser null o vacio");
			}
			Date FechaNuevaSiembra= new Date();
			try{
				FechaNuevaSiembra=formatoDelTexto.parse(fechaSiembra.trim());
			}catch(Exception e){
				throw new Exception("Error fecha: "+fechaSiembra+" formato de fecha erroneo, formato sugerido dd/MM/yyyy");
			}
			
			
			CotizacionDAO cotizacionDAO = new CotizacionDAO();
			Cotizacion cotizacion=cotizacionDAO.buscarPorNumeroTramite(tramite.trim());
			
			if(cotizacion.getId()==null){
				throw new Exception("El numero de tramite : "+tramite+" no existe.");
			}
			
			CotizacionDetalleDAO cotizacionDetalleDAO= new CotizacionDetalleDAO();
			CotizacionDetalle cotizacionDetalle = cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).get(0);
			AgriObjetoCotizacionDAO agriObjetoCotizacionDAO= new AgriObjetoCotizacionDAO();
			AgriObjetoCotizacion agriObjetoCotizacion= agriObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizacionDetalle.getObjetoId()));
			
			if(!cotizacion.getEstado().getId().equals("21")){//si la cotizacion no esta pendiente de aprobacion
					/**Filtro soicitado d.Garzon. l.Regalado las cotizaciones deben estar dentro de un rango de fechas para poder ser cambiados o actualizados**/
				CotizacionAprobacionDAO AgriCotizacionAprobacionDAO = new CotizacionAprobacionDAO();
				AgriCotizacionAprobacion agriCotizacionAprobacion =AgriCotizacionAprobacionDAO.buscarPorTramite(tramite.trim());
				VariableSistemaDAO variableDAO= new VariableSistemaDAO();
				VariableSistema diasCotizacion=variableDAO.buscarPorNombre("DIAS_COTIZACION");				
				
				String fechaApr=agriCotizacionAprobacion.getFechaAprobacion();//fecha de aprobacion de la cotizacion
				SimpleDateFormat formatofechaApr = new SimpleDateFormat("yyyy-MM-dd");
				Date fechaAprobacion= formatofechaApr.parse(fechaApr);
				
				
				Calendar calFechaAntesApr = Calendar.getInstance();
				calFechaAntesApr.setTime(fechaAprobacion);
				calFechaAntesApr.add(Calendar.DAY_OF_MONTH,-(Integer.parseInt(diasCotizacion.getValor())+1));
	            Date fechaAntesApr=calFechaAntesApr.getTime();
	            
	            Calendar calFechaDespuesApr = Calendar.getInstance();
	            calFechaDespuesApr.setTime(fechaAprobacion);
	            calFechaDespuesApr.add(Calendar.DAY_OF_MONTH,Integer.parseInt(diasCotizacion.getValor())+1);
	            Date fechaDespuesApr=calFechaDespuesApr.getTime();
	            
	            Date fechaActual= new Date();
	            String detalleCambio=(agriObjetoCotizacion.getDetallesModificacion()==null)?"Advertencia!! Fecha Aprobacion QBE: "+formatoDelTexto.format(fechaAprobacion)+" fecha comunicacion de cambio: "+formatoDelTexto.format(fechaActual)+"|": 
					agriObjetoCotizacion.getDetallesModificacion()+"||Advertencia!! Fecha Aprobacion QBE: "+formatoDelTexto.format(fechaAprobacion)+" fecha comunicacion de cambio: "+formatoDelTexto.format(fechaActual)+"|";
				
	            System.out.println("Fecha aprobacion: "+formatoDelTexto.format(fechaAprobacion) +" fecha de cambio: "+ formatoDelTexto.format(fechaActual) );
	            if(fechaActual.after(fechaAntesApr) && fechaActual.before(fechaDespuesApr))
	            	System.out.println("fechas de Cambio correctas");
	            else
	            	agriObjetoCotizacion.setDetallesModificacion(detalleCambio);
	            	            
	            //Verificamos que la fecha de siembra no sobre pase la fecha permitida limite antes y despues
	            VariableSistema diasAntes=variableDAO.buscarPorNombre("DIAS_ANTES_SIEMBRA");
				VariableSistema diasDespues=variableDAO.buscarPorNombre("DIAS_DESPUES_SIEMBRA");
				
				Date fechaSiembraActual= agriObjetoCotizacion.getFechaSiembra();  // formatofechaApr.parse(fechaApr);
							
				Calendar calFechaAntes = Calendar.getInstance();
				calFechaAntes.setTime(fechaSiembraActual);
				calFechaAntes.add(Calendar.DAY_OF_MONTH,-(Integer.parseInt(diasAntes.getValor())+1));
	            Date fechaAntes=calFechaAntes.getTime();
	            
	            Calendar calFechaDespues = Calendar.getInstance();
	            calFechaDespues.setTime(fechaSiembraActual);
	            calFechaDespues.add(Calendar.DAY_OF_MONTH,Integer.parseInt(diasAntes.getValor())+1);
	            Date fechaDespues=calFechaDespues.getTime();
	            
	            System.out.println("Tramite "+tramite+": fecha nueva de siembra  "+formatoDelTexto.format(FechaNuevaSiembra)+" dias Antes "+diasAntes.getValor()+" fechaAntes"+formatoDelTexto.format(fechaAntes)
	            		+ " y dias despues "+diasDespues.getValor()+ " fechaDespues " + formatoDelTexto.format(fechaDespues) +": fecha de siembra original "+formatoDelTexto.format(fechaSiembraActual));
	            if(FechaNuevaSiembra.after(fechaAntes) && FechaNuevaSiembra.before(fechaDespues))
	            	System.out.println("fechas correctas");
	            else
	            	throw new Exception("No se puede actualizar la fecha nueva de siembra ya que  "+formatoDelTexto.format(FechaNuevaSiembra)+" no esta entre los "+diasAntes.getValor()+ " dias Antes y "
	            			+diasDespues.getValor()+ " dias Despues permitidos a partir de la fecha de siembra original "+formatoDelTexto.format(fechaSiembraActual));
	            
			}
			
			/**PROCESO DE ACTUALIZACION EN CASO DE ESTAR PENDIENTE, APROBADO O RECHAZADO**/
			/**
			 * 1) EN CASO DE ESTAR PENDIENTE, SE REGISTRA EL CAMBIO DE SITIO O FECHA Y SE LO ACTUALIZA SIN RESTRICCION
			 * 2) EN CASO DE ESTAR APROBADA, SE DEJA COMO APROBADA, SE ENVIA UN CORREO ELECTRONICO, Y SE REGISTRA EL CAMBIO DE SITIO O FECHA PARA VERLO POR PANTALLA
			 * 3) EN CASO DE ESTAR RECHAZADA, SE LA PONE COMO PENDIETE, SE ENVIA UN CORREO Y SE REGISTRA EL CAMVIO DE SITIO O FECHA PARA VERLO POR PANTALLA
			 */
			
			
			/*Proceso de auditoria de estados anteriores*/
			String fechaAnterior=""+auditoria.getObjeto();
			String fechaAnteriorSiembra="";
			try{
				fechaAnteriorSiembra=formatoDelTexto.format(agriObjetoCotizacion.getFechaSiembra());
			}catch(Exception e){
				fechaAnteriorSiembra="Sin fecha";
			}
			fechaAnterior =fechaAnterior+" fecha anterior: "+fechaAnteriorSiembra;
			
			
			//Verificamos en que estado esta
			if(cotizacion.getEstado().getId().equals("30")){//si esta RECHAZADA se la pone en aprobacion nuevamente para que sea revisada
				EstadoDAO estadoDAO= new EstadoDAO();
				Estado estado=estadoDAO.buscarPorId("21");
				cotizacion.setEstado(estado);
			}
			String detalleCambio=(agriObjetoCotizacion.getDetallesModificacion()==null)?" Fecha Anterior de Siembra: "+formatoDelTexto.format(agriObjetoCotizacion.getFechaSiembra())+ " Fecha actual de Siembra: "+fechaSiembra+" |": 
				agriObjetoCotizacion.getDetallesModificacion()+" Fecha Anterior de Siembra: "+formatoDelTexto.format(agriObjetoCotizacion.getFechaSiembra())+ " Fecha actual de Siembra: "+fechaSiembra+" |";
			agriObjetoCotizacion.setTieneModificacion(true);	
			agriObjetoCotizacion.setDetallesModificacion(detalleCambio);
			agriObjetoCotizacion.setFechaSiembra(FechaNuevaSiembra);
			AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction = new AgriObjetoCotizacionTransaction();
			agriObjetoCotizacionTransaction.editar(agriObjetoCotizacion);
			cotizacion.setFechaElaboracion(sq2);
			CotizacionTransaction cotizacionTransaction= new CotizacionTransaction();
			cotizacionTransaction.editar(cotizacion);
			
			respuesta.setEstado("OK");
			respuesta.setObservacion("PROCESO CORRECTO");
			respuesta.setTramite(tramite);
			String fechaCambio= formatoDelTexto.format(date2);
			respuesta.setFechaCambio(""+fechaCambio);
			
			auditoria.setObjeto(fechaAnterior);
			auditoria.setEstado("ok");
			try{
				auditoria=procesoAuditoria.editar(auditoria);
			}catch(Exception e){
				e.printStackTrace();
			}
						
		}catch(Exception e){
			//SI EXISTE ERROR ENVIAR UN MENSAJE DE ERROR
			try{
				auditoria.setEstado("Error");
				procesoAuditoria.editar(auditoria);
			}catch(Exception e1){
				e1.printStackTrace();
			}
			
			respuesta.setEstado("Error");
			respuesta.setObservacion("Error: " + e.getMessage());
			respuesta.setTramite(tramite);
			
			List<String> usuario = new ArrayList<>();
			usuario.add("luis.caiza@smartwork.com.ec");
			String asunto="Error en Proceso de Cotizacion Sucre";
			AgriSucreNotificacionErrores notificacionErrores= new AgriSucreNotificacionErrores();
			String Html=notificacionErrores.GeneradorHtml(tramite, e.getMessage(), "");
			
			for(String receptor:usuario){
				//Utilitarios.envioMail(receptor, asunto, cuerpo);
				Utilitarios.envioMail(receptor, asunto, Html);
			}			
			e.printStackTrace();
		}
		
		return respuesta;
	}
	
	/*metodo para actualizar las lugares de siembra*/
	
	public RespuestaCambio solicitudCambiolugarSiembra(String tramite, String codProvincia,String codCanton,String codParroquia,String direccionSiembra,String latitud,String longitud){
		RespuestaCambio respuesta = new RespuestaCambio();
		/*PROCESO DE AUDITORIA */
		AgriSucreAuditoria auditoria = new AgriSucreAuditoria();
		AgriSucreAuditoriaTransaction procesoAuditoria = new AgriSucreAuditoriaTransaction();
		auditoria.setTramite(tramite);
		auditoria.setObjeto("tramite : "+ tramite+ " Provincia : "+codProvincia+" Canton : "+codCanton+ " Parroquia : "+codParroquia+ " direccionSiembra : "+ direccionSiembra+" latitud : "+latitud+" longitud : "+longitud);
		java.util.Date date2 = new java.util.Date();
		java.sql.Timestamp sq2 = new java.sql.Timestamp(date2.getTime());
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		auditoria.setFecha(sq2);
		auditoria.setCanal("SUCRE CAMBIO LUGAR");
		try{
			auditoria=procesoAuditoria.crear(auditoria);
		}catch(Exception e){
			e.printStackTrace();
		}
		/*FIN DE PROCESO*/
		
		/*PROCESO DE ACTUALIZACION DE FECHA DE SIEMBRA*/
		try{
			/*Proceso de validacion de campos*/
			if(tramite.equals("")||tramite==null){
				throw new Exception("Error el numero de tramite no puede ser null o vacio");
			}
			
			
			if(codProvincia.equals("")||codProvincia==null){
				throw new Exception("Error la provincia no puede ser null o vacio");
			}
			
			if(codCanton.equals("")||codCanton==null){
				throw new Exception("Error la Canton  no puede ser null o vacio");
			}
			
			if(codParroquia.equals("")||codParroquia==null){
				throw new Exception("Error la Parroquias no puede ser null o vacio");
			}
			
			if(direccionSiembra.equals("")||direccionSiembra==null){
				throw new Exception("Error el sitioDeCultivo no puede ser null o vacio");
			}
			
			/*Proceso de verificacion de lugares de Cultivo*/
			
			/*CREACION DE LA DIRECCION*/
			
			ProvinciaDAO laProvincia = new ProvinciaDAO();
			Provincia provincia = new Provincia();
			CantonDAO elCanton = new CantonDAO();
			Canton canton = new Canton();
			AgriParroquiaDAO laParroquia = new AgriParroquiaDAO();
			AgriParroquia parroquia = new AgriParroquia();
			
			//hallamos la provincia en base al codigo que llega
			String CodigoSBSProvincia = codProvincia.trim();
			
			if (CodigoSBSProvincia.length() == 1)
				CodigoSBSProvincia = "0" + CodigoSBSProvincia;
			
			provincia = laProvincia.buscarPorCodigoSBS(CodigoSBSProvincia);
			
			if (provincia.getId() == null)
				throw new Exception("Error Codigo de Provincia "
						+ CodigoSBSProvincia + "  no encontrado");
			
			//hallamos el canton en base al codigo que llega
			String CodigoSBSCanton = codCanton.trim();
			
			if (CodigoSBSCanton.length() == 1 || CodigoSBSCanton.length() == 3)
				CodigoSBSCanton = "0" + CodigoSBSCanton;
			
			if (CodigoSBSCanton.length() >= 3)
				CodigoSBSCanton = CodigoSBSCanton.substring(2, 4);
			
			canton = elCanton.buscarPorCodigoSBS(CodigoSBSCanton, provincia);
			
			if (canton.getId() == null)
				throw new Exception("Error Codigo de Canton "
						+ CodigoSBSCanton + " no encontrado en provincia "
						+ CodigoSBSProvincia);
			
			//Proceso de busqueda de parroquias
			String CodigoSBSParroquia=codParroquia.trim();
			if(CodigoSBSParroquia.length()<6)
				CodigoSBSParroquia="0"+CodigoSBSParroquia;
			if(CodigoSBSParroquia.length()>6)
				CodigoSBSParroquia=CodigoSBSParroquia.substring(0, 6);
			
			//hallamos la parroquien en base al SBS
			parroquia= laParroquia.BuscarPorSBS(CodigoSBSParroquia);
			if (parroquia.getParroquiaNombre() == null)
				throw new Exception("Error Codigo de Parroquia "
						+ CodigoSBSParroquia + " no encontrado ");
			
			
			
			CotizacionDAO cotizacionDAO = new CotizacionDAO();
			Cotizacion cotizacion=cotizacionDAO.buscarPorNumeroTramite(tramite.trim());
			CotizacionDetalleDAO cotizacionDetalleDAO= new CotizacionDetalleDAO();
			CotizacionDetalle cotizacionDetalle = cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).get(0);
			AgriObjetoCotizacionDAO agriObjetoCotizacionDAO= new AgriObjetoCotizacionDAO();
			AgriObjetoCotizacion agriObjetoCotizacion= agriObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizacionDetalle.getObjetoId()));
			
			if(cotizacion.getId()==null){
				throw new Exception("El numero de tramite : "+tramite+" no existe.");
			}
			
			if(!cotizacion.getEstado().getId().equals("21")){//si la cotizacion no esta pendiente de aprobacion
				/**Filtro soicitado d.Garzon. l.Regalado las cotizaciones deben estar dentro de un rango de fechas para poder ser cambiados o actualizados**/
				CotizacionAprobacionDAO AgriCotizacionAprobacionDAO = new CotizacionAprobacionDAO();
				AgriCotizacionAprobacion agriCotizacionAprobacion =AgriCotizacionAprobacionDAO.buscarPorTramite(tramite.trim());
				VariableSistemaDAO variableDAO= new VariableSistemaDAO();
				VariableSistema diasCotizacion=variableDAO.buscarPorNombre("DIAS_COTIZACION");
				
				
				String fechaApr=agriCotizacionAprobacion.getFechaAprobacion();//fecha de aprobacion de la cotizacion
				SimpleDateFormat formatofechaApr = new SimpleDateFormat("yyyy-MM-dd");
				Date fechaAprobacion= formatofechaApr.parse(fechaApr);
				
				
				Calendar calFechaAntesApr = Calendar.getInstance();
				calFechaAntesApr.setTime(fechaAprobacion);
				calFechaAntesApr.add(Calendar.DAY_OF_MONTH,-(Integer.parseInt(diasCotizacion.getValor())+1));
	            Date fechaAntesApr=calFechaAntesApr.getTime();
	            
	            Calendar calFechaDespuesApr = Calendar.getInstance();
	            calFechaDespuesApr.setTime(fechaAprobacion);
	            calFechaDespuesApr.add(Calendar.DAY_OF_MONTH,Integer.parseInt(diasCotizacion.getValor())+1);
	            Date fechaDespuesApr=calFechaDespuesApr.getTime();
	            
	            Date fechaActual= new Date();
	            String detalleCambio=(agriObjetoCotizacion.getDetallesModificacion()==null)?"Advertencia!! Fecha Aprobacion QBE: "+formatoDelTexto.format(fechaAprobacion)+" fecha comunicacion de cambio: "+formatoDelTexto.format(fechaActual)+"|": 
					agriObjetoCotizacion.getDetallesModificacion()+"||Advertencia!! Fecha Aprobacion QBE: "+formatoDelTexto.format(fechaAprobacion)+" fecha comunicacion de cambio: "+formatoDelTexto.format(fechaActual)+"|";
				
	            System.out.println("Fecha aprobacion: "+formatoDelTexto.format(fechaAprobacion) +" fecha de cambio: "+ formatoDelTexto.format(fechaActual) );
	            if(fechaActual.after(fechaAntesApr) && fechaActual.before(fechaDespuesApr))
	            	System.out.println("fechas de Cambio correctas");
	            else
	            	agriObjetoCotizacion.setDetallesModificacion(detalleCambio);
			}
			
			
			/**PROCESO DE ACTUALIZACION EN CASO DE ESTAR PENDIENTE, APROBADO O RECHAZADO**/
			/**
			 * 1) EN CASO DE ESTAR PENDIENTE, SE REGISTRA EL CAMBIO DE SITIO O FECHA Y SE LO ACTUALIZA SIN RESTRICCION
			 * 2) EN CASO DE ESTAR APROBADA, SE DEJA COMO APROBADA, SE ENVIA UN CORREO ELECTRONICO, Y SE REGISTRA EL CAMBIO DE SITIO O FECHA PARA VERLO POR PANTALLA
			 * 3) EN CASO DE ESTAR RECHAZADA, SE LA PONE COMO PENDIETE, SE ENVIA UN CORREO Y SE REGISTRA EL CAMVIO DE SITIO O FECHA PARA VERLO POR PANTALLA
			 */					
			
			String temporal= auditoria.getObjeto()+ "|||| ProvinciaAnterior : "+agriObjetoCotizacion.getProvinciaId()+ " CantonIdAnterior : "+agriObjetoCotizacion.getCantonId()+" ParroquiaIdAnterior : "+agriObjetoCotizacion.getParroquiaId()
					+" DireccionSiembraAnterior : "+agriObjetoCotizacion.getDireccionSiembra()+" latitudAnterior : "+agriObjetoCotizacion.getLatitud()+" longitudAnterior : "+agriObjetoCotizacion.getLongitud();
			
			//sitio de siembra actual
			Provincia provinciaActual= laProvincia.buscarPorId(agriObjetoCotizacion.getProvinciaId().toString());
			Canton cantonActual=elCanton.buscarPorId(agriObjetoCotizacion.getCantonId().toString());
			AgriParroquia parroquiaActual=laParroquia.BuscarPorId(Integer.parseInt(agriObjetoCotizacion.getAgriParroquiaId()));
			
			String mensajePantalla= agriObjetoCotizacion.getDetallesModificacion()==null? " Sitio Anterior: "+ provinciaActual.getNombre()+"/"+cantonActual.getNombre()+"/"+parroquiaActual.getParroquiaNombre()+"/"+agriObjetoCotizacion.getDireccionSiembra()
					+" Sitio Actual: "+ provincia.getNombre()+"/"+canton.getNombre()+"/"+parroquia.getParroquiaNombre()+"/"+direccionSiembra +" | ":
						agriObjetoCotizacion.getDetallesModificacion()+ " Sitio Anterior: "+provinciaActual.getNombre()+"/"+cantonActual.getNombre()+"/"+parroquiaActual.getParroquiaNombre()+"/"+agriObjetoCotizacion.getDireccionSiembra()
						+" Sitio Actual: "+ provincia.getNombre()+"/"+canton.getNombre()+"/"+parroquia.getParroquiaNombre()+"/"+direccionSiembra +" | ";
			
			/*Proceso de auditoria de estados anteriores*/
			agriObjetoCotizacion.setProvinciaId(new BigInteger(provincia.getId()));
			agriObjetoCotizacion.setCantonId(new BigInteger(canton.getId()));
			agriObjetoCotizacion.setAgriParroquiaId(""+parroquia.getId());
			agriObjetoCotizacion.setDireccionSiembra(direccionSiembra);
			if(!latitud.trim().equals("")){
				try{
					float milatitud=Float.parseFloat(latitud);
					agriObjetoCotizacion.setLatitud(milatitud);
				}catch(Exception e){
					throw new Exception("la Longitud "+latitud+" debe ser un numero");
				}				
			}
							
			//Verificamos en que estado esta
			if(cotizacion.getEstado().getId().equals("30")){//si esta CANCELADA se la pone en aprobacion nuevamente para que sea revisada
				EstadoDAO estadoDAO= new EstadoDAO();
				Estado estado=estadoDAO.buscarPorId("21");
				cotizacion.setEstado(estado);
			}
			
			agriObjetoCotizacion.setTieneModificacion(true);	
			agriObjetoCotizacion.setDetallesModificacion(mensajePantalla);
			
			AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction = new AgriObjetoCotizacionTransaction();
			agriObjetoCotizacionTransaction.editar(agriObjetoCotizacion);
			cotizacion.setFechaElaboracion(sq2);
			CotizacionTransaction cotizacionTransaction= new CotizacionTransaction();
			cotizacionTransaction.editar(cotizacion);
			
			respuesta.setEstado("OK");
			respuesta.setObservacion("PROCESO CORRECTO");
			respuesta.setTramite(tramite);
			String fechaCambio= formatoDelTexto.format(date2);
			respuesta.setFechaCambio(""+fechaCambio);
			
			auditoria.setObjeto(temporal);
			auditoria.setEstado("ok");
			
			try{
				procesoAuditoria.editar(auditoria);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}catch(Exception e){
			respuesta.setEstado("Error");
			respuesta.setObservacion("Error: " + e.getMessage());
			respuesta.setTramite(tramite);
			try{
				auditoria.setEstado("Error");
				procesoAuditoria.editar(auditoria);
			}catch(Exception e1){
				e1.printStackTrace();
			}
			//SI EXISTE ERROR ENVIAR UN MENSAJE DE ERROR
			List<String> usuario = new ArrayList<>();
			usuario.add("luis.caiza@smartwork.com.ec");
			String asunto="Error en Proceso de Cotizacion Sucre";
			AgriSucreNotificacionErrores notificacionErrores= new AgriSucreNotificacionErrores();
			String Html=notificacionErrores.GeneradorHtml(tramite, e.getMessage(), "");
			
			for(String receptor:usuario){
				//Utilitarios.envioMail(receptor, asunto, cuerpo);
				Utilitarios.envioMail(receptor, asunto, Html);
			}			
			e.printStackTrace();
		}
		
		return respuesta;
	}

	public RespuestaCambio registroSiniestros(String Tramite, String identificacion, String NombreCliente,String ApellidoCliente, double valorIndemnizacion,
			String fechaPago, String cultivo, double hectareas, double sumaAseguradaIndemnizacion, String fechaNotificacionCliente, String tipoIndemnizacion ){
		RespuestaCambio respuesta = new RespuestaCambio();
		/* Proceso de validacion de campos obligatorios */
		
		/*PROCESO DE AUDITORIA */
		AgriSucreAuditoria auditoria = new AgriSucreAuditoria();
		AgriSucreAuditoriaTransaction procesoAuditoria = new AgriSucreAuditoriaTransaction();
		auditoria.setTramite(Tramite);
		auditoria.setObjeto("Tramite: "+Tramite+" identificacion : "+identificacion+" NombreCliente : "+NombreCliente+
				" ApellidoCliente : "+ApellidoCliente+" valorIndemnizacion : "+valorIndemnizacion
				+" fechaPago : "+fechaPago+" cultivo : "+cultivo+ " hectareas : "+hectareas+" sumaAseguradaHectareas : "+sumaAseguradaIndemnizacion
				+" fechaNotificacionCliente : "+fechaNotificacionCliente+" tipoIndemnizacion : "+tipoIndemnizacion);
		
		java.util.Date date2 = new java.util.Date();
		java.sql.Timestamp sq2 = new java.sql.Timestamp(date2.getTime());
		auditoria.setFecha(sq2);
		auditoria.setCanal("SUCRE INDEMNIZACION");
		try{
			auditoria=procesoAuditoria.crear(auditoria);			
		}catch(Exception e){
			e.printStackTrace();
		}
	
		/***TODO: PROCESO SOLICITADO POR AGRICOLA ref: Leonardo regalado, el proceso toma la informacion
		 * de seguros sucre para que notifique el pago al cliente que previamente ya deposito qbe en sucre***/
		try{
			/**Verificamos que la cotizacion exista con el numero de tramite**/
			CotizacionDAO cotizacionDAO = new CotizacionDAO();
			Cotizacion cotizacion = cotizacionDAO.buscarPorNumeroTramite(Tramite.trim());
			if(cotizacion.getId()==null)
				throw new Exception("El numero de tr�mite no existe en la base de datos QBE");	
			
			//verificamos que la cotizacion no haya sido notificada el pago anterioremente
			CotizacionDetalleDAO cotizacionDetalleDAO= new CotizacionDetalleDAO();
			CotizacionDetalle cotizacionDetalle= cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).get(0);
			AgriObjetoCotizacionDAO agriObjetoCotizacionDAO= new AgriObjetoCotizacionDAO();
			AgriObjetoCotizacion agriObjetoCotizacion=agriObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizacionDetalle.getObjetoId()));
		
			if(agriObjetoCotizacion.isTieneIndemnizacion()){
				respuesta.setEstado("OK");
				respuesta.setObservacion("La notificaci�n de la indemnizacion ya fue realizada anteriormente");
				return respuesta;
			}
			
			/**verificamos el formato de la fecha de pago**/
			/**validacion de fechas**/
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaPagoV= null;
			Date fechaNotificacionV=null;
			try{
				fechaPagoV=formatoDelTexto.parse(fechaPago.trim());
			}catch (Exception e) {
					throw new Exception("la fecha de pago al cliente no se encuentra en el formato: dd/MM/yyyy "+fechaPago);
			}
			try{
				fechaNotificacionV=formatoDelTexto.parse(fechaNotificacionCliente.trim());
			}catch (Exception e) {
					throw new Exception("la fecha de pago al cliente no se encuentra en el formato: dd/MM/yyyy "+fechaPago);
			}
			
			//enviaban las fechas solo con 2 numeros, se puso el filtro para que envien en el formato correcto
			String AnioSiembra=fechaNotificacionCliente;
			String[] elementosFecha = AnioSiembra.split("/");
			int comprobacionAnio=elementosFecha[2].length();
						
			if(comprobacionAnio !=4)
				throw new Exception("Fecha de siembra no valida "+fechaNotificacionCliente+" no valida, debe estar en el formato dd/MM/yyyy");
			
			AnioSiembra=fechaPago;
			elementosFecha = AnioSiembra.split("/");
			comprobacionAnio=elementosFecha[2].length();
			if(comprobacionAnio !=4)
				throw new Exception("Fecha de siembra no valida "+fechaPago+" no valida, debe estar en el formato dd/MM/yyyy");
			
						
			/**Verificamos que el usuario exista en la base del cotizador**/
			EntidadDAO entidadDAO = new EntidadDAO();
			Entidad entidad = entidadDAO.buscarEntidadPorIdentificacion(identificacion.trim());
			if(entidad.getId()==null)
				throw new Exception("El cliente no existe");
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = clienteDAO.buscarPorEntidadId(entidad);
			if(cliente.getId()==null)
				throw new Exception("El cliente no existe");
			
			Cliente clienteCotizacion = clienteDAO.buscarPorId(cotizacion.getClienteId().toString());
			
			if(!clienteCotizacion.getEntidad().getIdentificacion().equals(cliente.getEntidad().getIdentificacion()))
				throw new Exception("El cliente asegurado es diferente al cliente de cotizacion");
			
			/**Verificamos el tipo de cultivo**/
				
			BigInteger idCultivo=agriObjetoCotizacion.getTipoCultivoId();
			AgriTipoCultivoDAO agriTipoCultivoDAO = new AgriTipoCultivoDAO(); 
			AgriTipoCultivo agriTipoCultivo = agriTipoCultivoDAO.BuscarPorId(idCultivo);
			
			if(!agriTipoCultivo.getCodIntSucre().equals(cultivo.trim()))
				throw new Exception("El tipo de cultivo no corresponde al de la cotizacion: N.C.C= "+agriTipoCultivo.getNombre());
			/**validacion que esta aprobada y confirmada**/
			if(!cotizacion.getEstado().getNombre().equals("Pendiente de Emitir"))
				throw new Exception("La cotizacion no se encuentra confirmada como emitida, no se puede aplicar siniestros");
			if(agriObjetoCotizacion.getConfirEmiCanal()==false)
				throw new Exception("La cotizacion no esta confirmada como emitida, no se puede aplicar siniestros");
			
			/**Guardamos la informacion de la indemnizacion**/
			AgriIndemnizacion agriIndemnizacion= new AgriIndemnizacion();
			agriIndemnizacion.setClienteId(new BigInteger(cliente.getId()));
			agriIndemnizacion.setCultivoId(agriTipoCultivo.getTipoCultivoId());
			agriIndemnizacion.setFechaNotificacionCliente(fechaNotificacionV);
			agriIndemnizacion.setFechaPago(fechaPagoV);
			agriIndemnizacion.setHectareas(hectareas);
			agriIndemnizacion.setSumaAseguradaIndemnizacion(sumaAseguradaIndemnizacion);
			agriIndemnizacion.setValorIndemnizacion(valorIndemnizacion);
			agriIndemnizacion.setTramite(Tramite);
			agriIndemnizacion.setTramite(Tramite);
			if(tipoIndemnizacion.toUpperCase().trim().equals("P"))
				agriIndemnizacion.setTipoIndemnizacion("PARCIAL");
			else
				agriIndemnizacion.setTipoIndemnizacion("TOTAL");
				
			AgriIndemnizacionTransaction agriIndemnizacionTransaction = new AgriIndemnizacionTransaction();
			agriIndemnizacionTransaction.crear(agriIndemnizacion);
			
			//Identificamos como cotizacion ya notificada.
			agriObjetoCotizacion.setTieneIndemnizacion(true);
			AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction = new AgriObjetoCotizacionTransaction();
			agriObjetoCotizacionTransaction.editar(agriObjetoCotizacion);
			respuesta.setEstado("OK");
			respuesta.setObservacion("Correcto");
			
		}catch(Exception e){
			e.printStackTrace();
			auditoria= new AgriSucreAuditoria();
			auditoria.setTramite(Tramite);
			auditoria.setObjeto("ERROR: "+e.getMessage());
			
			auditoria.setFecha(sq2);
			auditoria.setCanal("SUCRE INDEMNIZACION");
			auditoria.setEstado("ERROR");
			
			auditoria=procesoAuditoria.crear(auditoria);
			//SI EXISTE ERROR ENVIAR UN MENSAJE DE ERROR
			/*List<String> usuario = new ArrayList<>();
			usuario.add("luis.caiza@smartwork.com.ec");
			String asunto="Error en Proceso Indemnizacion Sucre";
			AgriSucreNotificacionErrores notificacionErrores= new AgriSucreNotificacionErrores();
			String Html=notificacionErrores.GeneradorHtml(Tramite, e.getMessage(), "");
			
			for(String receptor:usuario){
				//Utilitarios.envioMail(receptor, asunto, cuerpo);
				Utilitarios.envioMail(receptor, asunto, Html);
			}
			*/
			respuesta.setEstado("Error");
			respuesta.setObservacion("Error: "+e.getMessage());
			
		}
		
		return respuesta;
	}
	


}