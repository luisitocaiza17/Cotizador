package com.qbe.cotizador.servlets.producto.agricola;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoXPuntoVentaDAO;
import com.qbe.cotizador.dao.cotizacion.TipoObjetoDAO;
import com.qbe.cotizador.dao.cotizacion.VigenciaPolizaDAO;
import com.qbe.cotizador.dao.entidad.ActividadEconomicaDAO;
import com.qbe.cotizador.dao.entidad.CantonDAO;
import com.qbe.cotizador.dao.entidad.CiudadDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.DerechoEmisonDAO;
import com.qbe.cotizador.dao.entidad.DireccionDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.ParroquiaDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.entidad.SucursalDAO;
import com.qbe.cotizador.dao.entidad.TipoDireccionDAO;
import com.qbe.cotizador.dao.entidad.TipoEntidadDAO;
import com.qbe.cotizador.dao.entidad.TipoIdentificacionDAO;
import com.qbe.cotizador.dao.entidad.UsuarioDAO;
import com.qbe.cotizador.dao.entidad.ZonaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCicloDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriObjetoCotizacionDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParametroXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParroquiaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriPronacaQbeCultivoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriReglaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCalculoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCultivoDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.dao.seguridad.TipoVariableDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.ActividadEconomica;
import com.qbe.cotizador.model.AgriCargaPreviaArchivoPlano;
import com.qbe.cotizador.model.AgriCiclo;
import com.qbe.cotizador.model.AgriObjetoCotizacion;
import com.qbe.cotizador.model.AgriParametroXPuntoVenta;
import com.qbe.cotizador.model.AgriParroquia;
import com.qbe.cotizador.model.AgriPronacaQbeCultivos;
import com.qbe.cotizador.model.AgriRegla;
import com.qbe.cotizador.model.AgriTipoCalculo;
import com.qbe.cotizador.model.AgriTipoCultivo;
import com.qbe.cotizador.model.Canton;
import com.qbe.cotizador.model.Ciudad;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.DerechoEmision;
import com.qbe.cotizador.model.Direccion;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.Parroquia;
import com.qbe.cotizador.model.ProductoXPuntoVenta;
import com.qbe.cotizador.model.Provincia;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.Sucursal;
import com.qbe.cotizador.model.TipoVariable;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.transaction.cotizacion.CotizacionDetalleTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.entidad.ClienteTransaction;
import com.qbe.cotizador.transaction.entidad.DireccionTransaction;
import com.qbe.cotizador.transaction.entidad.EntidadTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriCargaPreviaArchivoPlanoTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriObjetoCotizacionTransaction;

public class AgriProcesarCotizacionAP {

	@SuppressWarnings("finally")
	public static JSONArray procesarCotizaciones(
			List<CotizacionArchivoPlano> listado) {

		JSONObject error = new JSONObject();
		JSONArray errorList = new JSONArray();

		ProvinciaDAO provinciaDAO = new ProvinciaDAO();
		CantonDAO cantonDAO = new CantonDAO();
		ParroquiaDAO parroquiaDAO = new ParroquiaDAO();
		AgriTipoCultivoDAO tipoCultivoDAO = new AgriTipoCultivoDAO();
		CotizacionArchivoPlano nuevaCotizacionCopia = new CotizacionArchivoPlano();
		try {

			for (CotizacionArchivoPlano nuevaCotizacion : listado) {
				try {
					nuevaCotizacionCopia = nuevaCotizacion;

					/* Verifico que los campos no tengan valores null */
					if (nuevaCotizacion.getCanal() == null
							|| nuevaCotizacion.getCanal().equals("")) {
						throw new Exception("El Canal no puede ser null.");
					}
					if (nuevaCotizacion.getClienteNombre() == null
							|| nuevaCotizacion.getClienteNombre().equals("")) {
						throw new Exception("El Nombre no puede ser null.");
					}
					
					if (nuevaCotizacion.getClienteApellido() == null
							|| nuevaCotizacion.getClienteApellido().equals("")) {
						throw new Exception("El Nombre no puede ser null.");
					}					
					if (nuevaCotizacion.getClienteIdentificacion() == null
							|| nuevaCotizacion.getClienteIdentificacion()
									.equals("")) {
						throw new Exception(
								"El N�mero de identificaci�n no puede ser null.");
					}
					if (nuevaCotizacion.getMontoAsegurado() == null
							|| nuevaCotizacion.getMontoAsegurado().equals("")) {
						throw new Exception(
								"El monto asegurado no puede ser null.");
					}
					if (nuevaCotizacion.getFechaSolicitud() == null
							|| nuevaCotizacion.getFechaSolicitud().equals("")) {
						nuevaCotizacion.setFechaSolicitud(null);
						throw new Exception(
								"La Fecha de Solicitud no puede ser null. Ingrese la Fecha en el formato correcto dd-mm-aaaa.");
					}
					if (nuevaCotizacion.getFechaSiembra() == null
							|| nuevaCotizacion.getFechaSiembra().equals("")) {
						nuevaCotizacion.setFechaSiembra(null);
						throw new Exception(
								"La Fecha de Siembra no puede ser null. Ingrese la Fecha en el formato correcto dd-mm-aaaa.");
					}
					if (nuevaCotizacion.getTipoCultivoNombre() == null
							|| nuevaCotizacion.getTipoCultivoNombre()
									.equals("")) {
						throw new Exception(
								"El Tipo de cultivo no puede ser null.");
					}
					if (nuevaCotizacion.getNumeroHectareasAseguradas() == null
							|| nuevaCotizacion.getNumeroHectareasAseguradas()
									.equals("")) {
						throw new Exception(
								"El N�mero de Has no puede ser null.");
					}
					if (nuevaCotizacion.getNumeroHectareasLote() == null
							|| nuevaCotizacion.getNumeroHectareasLote().equals(
									"")) {
						throw new Exception(
								"El N�mero Has Lote no puede ser null.");
					}
					if (nuevaCotizacion.getProvinciaNombre() == null
							|| nuevaCotizacion.getProvinciaNombre().equals("")) {
						throw new Exception("La Provincia no puede ser null.");
					}
					if (nuevaCotizacion.getCantonNombre() == null
							|| nuevaCotizacion.getCantonNombre().equals("")) {
						throw new Exception("El Canton no puede ser null.");
					}

					if (nuevaCotizacion.getUbicacionCultivo() == null
							|| nuevaCotizacion.getUbicacionCultivo().equals("")) {
						throw new Exception("La Referencia no puede ser null.");
					}
					if (nuevaCotizacion.getTelefono() == null
							|| nuevaCotizacion.getTelefono().equals("")) {
						throw new Exception("El Tel�fono no puede ser null.");
					}
					if (nuevaCotizacion.getGpsX() == null
							|| nuevaCotizacion.getGpsX().equals("")
							|| nuevaCotizacion.getGpsY() == null
							|| nuevaCotizacion.getGpsY().equals("")) {
						throw new Exception(
								"Las Coordenadas no pueden ser null.");
					}

					// crearCotizacion(String canal, String clienteNombre,
					// String identificacion, Double montoAsegurado, Timestamp
					// fechaSolicitud, String Canton, String Parroquia, String
					// Ubicacion, String Telefono)
					

					Provincia provincia = provinciaDAO
							.buscarPorNombre(nuevaCotizacion
									.getProvinciaNombre());
					if (provincia.getId() == null) {
						throw new Exception(
								"No se encontro la provincia en la lista.");
					}
					Canton canton = cantonDAO.buscarPorNombre(nuevaCotizacion
							.getCantonNombre());
					if (canton.getId() == null) {
						throw new Exception(
								"No se encontro el cant�n en la lista.");
					}

					String ParroquiaId = "";
					Parroquia parroquia = parroquiaDAO
							.buscarPorNombre(nuevaCotizacion
									.getParroquiaNombre());
					if (parroquia.getId() == null)
						ParroquiaId = "";
					else
						ParroquiaId = parroquia.getId();

					// tratamiento del tipo de cultivo en base al nombre y las
					// coincidencias
					// Hallamos el costo de produccion
					Double montoAsegurado = nuevaCotizacion.getMontoAsegurado();
					Double hectareas = nuevaCotizacion
							.getNumeroHectareasAseguradas();
					double CostoProduccionPronaca = montoAsegurado / hectareas;

					// Obtengo el id del tipo de canal
					AgriTipoCalculo agriTipoCalculo = new AgriTipoCalculo();
					AgriTipoCalculoDAO agriTipoCalculoDAO = new AgriTipoCalculoDAO();
					agriTipoCalculo = agriTipoCalculoDAO
							.BuscarPorNombre("PRONACA");
					String CodigoTipoCultivoProcesado = "";

					AgriPronacaQbeCultivoDAO agriPronacaQbeCultivoDAO = new AgriPronacaQbeCultivoDAO();
					List<AgriPronacaQbeCultivos> agriPronacaQbeCultivos = agriPronacaQbeCultivoDAO
							.BuscarCultivos(nuevaCotizacion
									.getTipoCultivoNombre());
					if (!agriPronacaQbeCultivos.isEmpty()) {
						bucle1: for (AgriPronacaQbeCultivos rs : agriPronacaQbeCultivos) {
							AgriReglaDAO regla = new AgriReglaDAO();
							List<AgriRegla> reglas = regla.BuscarPorParametros(
									new BigInteger(provincia.getId()),
									new BigInteger(canton.getId()),
									new BigInteger(rs.getAgriTipoCultivoId()),
									agriTipoCalculo.getTipoCalculoId());
							for (AgriRegla rs2 : reglas) {
								try {
									double costoP = rs2.getCostoProduccion();

									if (costoP == CostoProduccionPronaca) {
										CodigoTipoCultivoProcesado = ""
												+ rs2.getTipoCultivoId();
										break bucle1;
									}
								} catch (Exception e) {
									continue;
								}
							}
						}
					} else {
						AgriTipoCultivo agriTipoCultivo = tipoCultivoDAO
								.BuscarPorNombre("MA�Z DURO VERANO");
						CodigoTipoCultivoProcesado = agriTipoCultivo
								.getTipoCultivoId().toString();
					}

					if (CodigoTipoCultivoProcesado.equals("")
							|| CodigoTipoCultivoProcesado.equals(" ")) {
						AgriTipoCultivo agriTipoCultivo = tipoCultivoDAO
								.BuscarPorNombre("MA�Z DURO VERANO");
						CodigoTipoCultivoProcesado = agriTipoCultivo
								.getTipoCultivoId().toString();
					}
					// Fin
										Sucursal sucursal = new Sucursal();
					SucursalDAO sucursalDAO = new SucursalDAO();

					sucursal = sucursalDAO.buscarPorNombre("QUITO");
					
					String cot = crearCotizacion(nuevaCotizacion.getCanal(),
							nuevaCotizacion.getClienteNombre(),
							nuevaCotizacion.getClienteIdentificacion(),
							nuevaCotizacion.getMontoAsegurado(),
							nuevaCotizacion.getFechaSolicitud(),
							nuevaCotizacion.getCantonNombre(),
							nuevaCotizacion.getParroquiaNombre(),
							nuevaCotizacion.getUbicacionCultivo(),
							nuevaCotizacion.getTelefono());

					String cotId = crearObjetoCotizacion(cot,
							provincia.getId(), canton.getId(), ParroquiaId,
							nuevaCotizacion.getUbicacionCultivo(), "",
							nuevaCotizacion.getFechaSolicitud(),
							nuevaCotizacion.getMontoAsegurado(),
							new BigInteger(CodigoTipoCultivoProcesado), "",
							nuevaCotizacion.getFechaSiembra(),
							nuevaCotizacion.getNumeroHectareasLote(),
							nuevaCotizacion.getNumeroHectareasAseguradas(),
							nuevaCotizacion.getEsTecnificado(),
							nuevaCotizacion.getGpsX(),
							nuevaCotizacion.getGpsY(), "", "", "0",
							sucursal.getId(), "","PRONACA");

					obtenerValores(cotId);

				} catch (Exception e) {
					e.printStackTrace();
					error.put("canal", nuevaCotizacionCopia.getCanal());
					error.put("cliente", nuevaCotizacionCopia.getClienteNombre());
					error.put("identificacion",
							nuevaCotizacionCopia.getClienteIdentificacion());
					error.put("montoAsegurado",
							nuevaCotizacionCopia.getMontoAsegurado());
					if (nuevaCotizacionCopia.getFechaSolicitud() == null) {
						error.put("fechaSolicitud", "null");
					} else {
						error.put("fechaSolicitud", nuevaCotizacionCopia
								.getFechaSolicitud().toString());
					}
					if (nuevaCotizacionCopia.getFechaSiembra() == null) {
						error.put("fechaSiembra", "null");
					} else {
						error.put("fechaSiembra", nuevaCotizacionCopia
								.getFechaSiembra().toString());
					}
					error.put("tipoCultivo",
							nuevaCotizacionCopia.getTipoCultivoNombre());
					error.put("hasAseguradas",
							nuevaCotizacionCopia.getNumeroHectareasAseguradas());
					error.put("hasLote",
							nuevaCotizacionCopia.getNumeroHectareasLote());
					if (nuevaCotizacionCopia.getEsTecnificado()) {
						error.put("esTecnificado", "SI");
					} else {
						error.put("esTecnificado", "NO");
					}
					error.put("provincia",
							nuevaCotizacionCopia.getProvinciaNombre());
					error.put("canton", nuevaCotizacionCopia.getCantonNombre());
					error.put("parroquia",
							nuevaCotizacionCopia.getParroquiaNombre());
					error.put("ubicacion",
							nuevaCotizacionCopia.getUbicacionCultivo());
					error.put("telefono", nuevaCotizacionCopia.getTelefono());
					error.put("gpsX", nuevaCotizacionCopia.getGpsX());
					error.put("gpsY", nuevaCotizacionCopia.getGpsY());
					error.put("detalle", e.getMessage());
					errorList.add(error);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			return errorList;
		}

	}

	@SuppressWarnings("finally")
	public static JSONArray procesarCotizacionesCargaPrevia(
			List<CotizacionArchivoPlano> listado, HttpSession session) {

		ProvinciaDAO provinciaDAO = new ProvinciaDAO();
		CantonDAO cantonDAO = new CantonDAO();
		CotizacionArchivoPlano nuevaCotizacionCopia = new CotizacionArchivoPlano();
			
		JSONObject error = new JSONObject();
		JSONArray errorList = new JSONArray();
		System.out.println("_________Listado Actual_________________");
		for (CotizacionArchivoPlano nuevaCotizacion : listado) {
			 System.out.println(nuevaCotizacion.getClienteIdentificacion());
		}
		
		List<CotizacionArchivoPlano> listadoLimpio = new ArrayList<>();
		Map<String, CotizacionArchivoPlano> MapListado= new HashMap<String,CotizacionArchivoPlano>(listado.size());
		for (CotizacionArchivoPlano nuevaCotizacion : listado) {
			MapListado.put(nuevaCotizacion.getClienteIdentificacion(), nuevaCotizacion);
		}
		System.out.println("_________Listado Nuevo_________________");
		for(Entry<String, CotizacionArchivoPlano> p : MapListado.entrySet()) {
			listadoLimpio.add(p.getValue());
			 System.out.println(p.getValue().getClienteIdentificacion());
		 }
		
		
		try {

			for (CotizacionArchivoPlano nuevaCotizacion : listadoLimpio) {
				try {
					nuevaCotizacionCopia = nuevaCotizacion;

					/* Verifico que los campos no tengan valores null */
					if (nuevaCotizacion.getCanal() == null
							|| nuevaCotizacion.getCanal().equals("")) {
						throw new Exception("El Canal no puede ser null.");
					}
					if (nuevaCotizacion.getClienteNombre() == null
							|| nuevaCotizacion.getClienteNombre().equals("")) {
						throw new Exception("El Nombre no puede ser null.");
					}
					if (nuevaCotizacion.getClienteIdentificacion() == null
							|| nuevaCotizacion.getClienteIdentificacion()
									.equals("")
							|| nuevaCotizacion.getClienteIdentificacion()
									.length() < 10) {
						throw new Exception(
								"El N�mero de identificaci�n no puede ser null.");
					}
					if (nuevaCotizacion.getMontoAsegurado() == null
							|| nuevaCotizacion.getMontoAsegurado().equals("")) {
						throw new Exception(
								"El monto asegurado no puede ser null.");
					}
					if (nuevaCotizacion.getFechaSolicitud() == null
							|| nuevaCotizacion.getFechaSolicitud().equals("")) {
						nuevaCotizacion.setFechaSolicitud(null);
						throw new Exception(
								"La Fecha de Solicitud no puede ser null. Ingrese la Fecha en el formato correcto dd-mm-aaaa.");
					}
					if (nuevaCotizacion.getFechaSiembra() == null
							|| nuevaCotizacion.getFechaSiembra().equals("")) {
						nuevaCotizacion.setFechaSiembra(null);
						throw new Exception(
								"La Fecha de Siembra no puede ser null. Ingrese la Fecha en el formato correcto dd-mm-aaaa.");
					}
					if (nuevaCotizacion.getTipoCultivoNombre() == null
							|| nuevaCotizacion.getTipoCultivoNombre()
									.equals("")) {
						throw new Exception(
								"El Tipo de cultivo no puede ser null.");
					}
					if (nuevaCotizacion.getNumeroHectareasAseguradas() == null
							|| nuevaCotizacion.getNumeroHectareasAseguradas()
									.equals("")) {
						throw new Exception(
								"El N�mero de Has no puede ser null.");
					}
					if (nuevaCotizacion.getNumeroHectareasLote() == null
							|| nuevaCotizacion.getNumeroHectareasLote().equals(
									"")) {
						throw new Exception(
								"El N�mero Has Lote no puede ser null.");
					}
					if (nuevaCotizacion.getProvinciaNombre() == null
							|| nuevaCotizacion.getProvinciaNombre().equals("")) {
						throw new Exception("La Provincia no puede ser null.");
					}
					if (nuevaCotizacion.getCantonNombre() == null
							|| nuevaCotizacion.getCantonNombre().equals("")) {
						throw new Exception("El Canton no puede ser null.");
					}

					if (nuevaCotizacion.getUbicacionCultivo() == null
							|| nuevaCotizacion.getUbicacionCultivo().equals("")) {
						throw new Exception("La Referencia no puede ser null.");
					}
					if (nuevaCotizacion.getTelefono() == null
							|| nuevaCotizacion.getTelefono().equals("")
							|| nuevaCotizacion.getTelefono().length() != 10) {
						throw new Exception("El Tel�fono debe contener diez digitos.");
					}
					if (nuevaCotizacion.getGpsX() == null
							|| nuevaCotizacion.getGpsX().equals("")
							|| nuevaCotizacion.getGpsY() == null
							|| nuevaCotizacion.getGpsY().equals("")) {
						throw new Exception(
								"Las Coordenadas no pueden ser null.");
					}

					AgriCargaPreviaArchivoPlano cargaPreviaArchivo = new AgriCargaPreviaArchivoPlano();
					AgriCargaPreviaArchivoPlanoTransaction cargaPreviaArchivoTransaction = new AgriCargaPreviaArchivoPlanoTransaction();

					/* Creo la entidad y el cliente si no existe en la bdd */

					/* Busco la provincia, canton, parroquia y tipo de cultivo */
					Provincia provincia = provinciaDAO
							.buscarPorNombre(nuevaCotizacion
									.getProvinciaNombre().replace("_", " ").trim());
					if (provincia.getId() == null) {
						throw new Exception(
								"No se encontro la provincia en la lista.");
					}
					Canton canton = cantonDAO.buscarPorNombre(nuevaCotizacion
							.getCantonNombre().replace("_", " ").trim());
					if (canton.getId() == null) {
						throw new Exception(
								"No se encontro el cant�n en la lista.");
					}
					AgriParroquiaDAO agriParroquiaDAO = new AgriParroquiaDAO();
					AgriParroquia parroquia = agriParroquiaDAO.BuscarPorNombreYCanton(nuevaCotizacion
									.getParroquiaNombre().replace("_", " ").trim(), canton.getId());
							
					if(parroquia.getParroquiaNombre()==null){
						throw new Exception(
								"No se encontro la Parroquia a la que hace referencia.");
					}
					// Hallamos el costo de produccion
					Double montoAsegurado = nuevaCotizacion.getMontoAsegurado();
					Double hectareas = nuevaCotizacion
							.getNumeroHectareasAseguradas();
					
					if(montoAsegurado==0||montoAsegurado==0.0)
						throw new Exception("El monto asegurado no puede ser 0.");
					
					if(hectareas==0||hectareas==0.0)
						throw new Exception("El n�mero de hectareas no puede ser 0.");
					
					// Proceso de busqueda del tipo de cultivo
					// Obtengo el id del tipo de canal
					AgriParametroXPuntoVenta agriParametroXPuntoVenta= new AgriParametroXPuntoVenta();
					AgriParametroXPuntoVentaDAO agriParametroXPuntoVentaDAO= new AgriParametroXPuntoVentaDAO();
					agriParametroXPuntoVenta=agriParametroXPuntoVentaDAO.buscarPorPuntoVentaId(new BigInteger("4271"));					
					
					AgriTipoCalculo agriTipoCalculo = new AgriTipoCalculo();
					AgriTipoCalculoDAO agriTipoCalculoDAO = new AgriTipoCalculoDAO();
					agriTipoCalculo = agriTipoCalculoDAO.BuscarPorId(agriParametroXPuntoVenta.getTipoCalculoId());
					String CodigoTipoCultivoProcesado = "";
					
					AgriCicloDAO cicloDAO=new AgriCicloDAO();
					
					//Buscamos el tipo de cultivo en base al nombre ingresado en la plantilla excel
					AgriTipoCultivoDAO agriTipoCultivoDAO = new AgriTipoCultivoDAO();
					AgriTipoCultivo agriTipoCultivoP=agriTipoCultivoDAO.BuscarPorNombre(nuevaCotizacion
									.getTipoCultivoNombre());
					
					
					String TipoCultivoSinCoincidencias="";
					//tomo fecha siembra para verificar a que ciclo pertenece
					Date dateSiembra=  nuevaCotizacion.getFechaSiembra();
					
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");//formato de fecha de siembra
					
					Date fechaControlInicio=null;
					Date fechaControlFin=null;
					fechaControlInicio=formatter.parse("01/01/2015");
					fechaControlFin=formatter.parse("01/01/2019");
					if(dateSiembra.after(fechaControlInicio) && dateSiembra.before(fechaControlFin)){
						System.out.print("fechas correctas");
					}
					else{
						throw new Exception(
								"Error la fecha de siembra "+nuevaCotizacion.getFechaSiembra()+ " no esta dentro del rango de fechas permitidas");
					}
					
					
					if (agriTipoCultivoP.getNombre()!=null) {
							AgriReglaDAO regla = new AgriReglaDAO();
							List<AgriRegla> reglas = regla.BuscarPorParametros(new BigInteger(provincia.getId()),new BigInteger(canton.getId()),
									agriTipoCultivoP.getTipoCultivoId(),agriTipoCalculo.getTipoCalculoId());
							
							bucle1: for (AgriRegla rs2 : reglas) {
								try {
									
									AgriCiclo ciclo = cicloDAO.BuscarPorId(rs2.getClicloId());//tomamos el ciclo para verificar a cual pertenece
									Date fechafin=ciclo.getFechaFin();//fecha inicio ciclo
									Date fechaInicio=ciclo.getFechaInicio();//fecha fin ciclo
									
									String costoP = ""+rs2.getCostoProduccion();
									String costoM= ""+rs2.getCostoMantenimiento();
									String costoA = ""+(nuevaCotizacion.getMontoAsegurado()/nuevaCotizacion.getNumeroHectareasAseguradas());
									double valorP =Double.parseDouble(costoP);
									double valorM= Double.parseDouble(costoM);
									//SUMA ASEGURADA
									BigDecimal a = new BigDecimal(costoA);//valor de la suma asegurada
									BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);//redondeamos a 2 decimales
									double valorA=Double.parseDouble(""+roundOff);
									
									if(valorP==valorA || valorM==valorA){//verificamos que los costos de produccion sean iguales
										TipoCultivoSinCoincidencias=""+rs2.getTipoCultivoId();//si son iguales le ponemos como temporal, asi si no calza por fechas calsa al menos el cultivo
										if (valorP==valorA) {									
											if(dateSiembra.after(fechaInicio) && dateSiembra.before(fechafin)){
												CodigoTipoCultivoProcesado = ""	+ rs2.getTipoCultivoId();
												break bucle1;//si se encuentra la busqueda termina										
											}
											else{
												TipoCultivoSinCoincidencias=""+ rs2.getTipoCultivoId();
											}
										}else{
											if(valorM==valorA){
												if(dateSiembra.after(fechaInicio) && dateSiembra.before(fechafin)){
													CodigoTipoCultivoProcesado = ""	+ rs2.getTipoCultivoId();
													break bucle1;//si se encuentra la busqueda termina										
												}
												else{
													TipoCultivoSinCoincidencias=""+ rs2.getTipoCultivoId();
												}
											}
										}
									}
								} catch (Exception e) {
									continue;
								}
							}
						
					} else {
						throw new Exception("Error Tipo de cultivo "+ nuevaCotizacion
								.getTipoCultivoNombre()+ " no existe");
					} 

					if (CodigoTipoCultivoProcesado.equals("")) {//Si no encontraste tipo de cultivo traeme el que al menos coincida
						CodigoTipoCultivoProcesado =TipoCultivoSinCoincidencias;
						if(CodigoTipoCultivoProcesado.equals("")){//si aun asi no encuentra tomamos el primero que coincidir con el codigo de integracion
							throw new Exception("No existe una regla de configuracion para este cultivo en este lugar de siembra Provincia:"+nuevaCotizacion.getProvinciaNombre()+ " Canton: "+nuevaCotizacion.getCantonNombre()+"con el costo de produccion:" +(nuevaCotizacion.getMontoAsegurado()/nuevaCotizacion.getNumeroHectareasAseguradas()));
						}					
					}
			
					// Fin

					cargaPreviaArchivo.setNombre_Completo(nuevaCotizacion.getClienteNombre()+" "+nuevaCotizacion.getClienteApellido());
					
					cargaPreviaArchivo.setCanalNombre(nuevaCotizacion
							.getCanal());
					cargaPreviaArchivo.setClienteNombre(nuevaCotizacion
							.getClienteNombre());
					cargaPreviaArchivo.setClienteApellido(nuevaCotizacion
							.getClienteApellido());
					cargaPreviaArchivo.setClienteId(null);
					cargaPreviaArchivo.setClienteIdentificacion(nuevaCotizacion
							.getClienteIdentificacion());
					cargaPreviaArchivo.setMontoAsegurado(nuevaCotizacion
							.getMontoAsegurado());
					cargaPreviaArchivo.setSolicitudFecha(nuevaCotizacion
							.getFechaSolicitud());
					cargaPreviaArchivo.setSiembraFecha(nuevaCotizacion
							.getFechaSiembra());
					cargaPreviaArchivo.setTipoCultivoId(new BigInteger(CodigoTipoCultivoProcesado));
					cargaPreviaArchivo.setTipoCultivoNombre(nuevaCotizacion
							.getTipoCultivoNombre());
					cargaPreviaArchivo.setNumerHasAseguradas(nuevaCotizacion
							.getNumeroHectareasAseguradas());
					cargaPreviaArchivo.setNumeroHasLote(nuevaCotizacion
							.getNumeroHectareasLote());
					cargaPreviaArchivo.setEsTecnificado(nuevaCotizacion
							.getEsTecnificado());
					cargaPreviaArchivo.setProvinciaId(new BigInteger(provincia
							.getId()));
					cargaPreviaArchivo.setProvinciaNombre(nuevaCotizacion
							.getProvinciaNombre());
					cargaPreviaArchivo.setCantonId(new BigInteger(canton
							.getId()));
					cargaPreviaArchivo.setCantonNombre(nuevaCotizacion
							.getCantonNombre());
					try {
						cargaPreviaArchivo.setParroquiaId(new BigInteger(
								""+parroquia.getId()));
						cargaPreviaArchivo.setParroquiaNombre(nuevaCotizacion
								.getParroquiaNombre());
					} catch (Exception e) {

					}
					cargaPreviaArchivo.setUbicacionCultivo(nuevaCotizacion
							.getUbicacionCultivo());
					cargaPreviaArchivo.setTelefono(nuevaCotizacion
							.getTelefono());
					
					cargaPreviaArchivo.setGpsX(nuevaCotizacion.getGpsX());
					cargaPreviaArchivo.setGpsY(nuevaCotizacion.getGpsY());
					// Estado activo
					cargaPreviaArchivo.setEstado(1);

					Usuario usuario = (Usuario) session.getAttribute("usuario");

					cargaPreviaArchivo.setUsuarioId(new BigInteger(usuario
							.getId()));

					Timestamp fechaActual = new Timestamp(
							System.currentTimeMillis());

					cargaPreviaArchivo.setCargaFecha(fechaActual);
					cargaPreviaArchivo.setActivo(true);
					cargaPreviaArchivoTransaction.crear(cargaPreviaArchivo);

				} catch (Exception e) {
					SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy"); 
					
					error.put("canal", nuevaCotizacionCopia.getCanal());
					error.put("nombres", nuevaCotizacionCopia.getClienteNombre());
					error.put("apellidos", nuevaCotizacionCopia.getClienteApellido());
					error.put("cliente", nuevaCotizacionCopia.getClienteNombre()+ " "+ nuevaCotizacionCopia.getClienteApellido() );
					error.put("identificacion",
							nuevaCotizacionCopia.getClienteIdentificacion());
					error.put("montoAsegurado",
							nuevaCotizacionCopia.getMontoAsegurado());
					if (nuevaCotizacionCopia.getFechaSolicitud() == null) {
						error.put("fechaSolicitud", "fecha no valida");
					} else {
						try{
							Date fecha= new Date (nuevaCotizacionCopia
									.getFechaSolicitud().getTime());
							String fecha2= dt.format(fecha);
							
						error.put("fechaSolicitud", fecha2);
						}
						catch(Exception ex){
							error.put("fechaSolicitud", "fecha no valida");
						}
					}
					if (nuevaCotizacionCopia.getFechaSiembra() == null) {
						error.put("fechaSiembra", "fecha no valida");
					} else {
						try{
							
							Date fecha= new Date (nuevaCotizacionCopia
									.getFechaSiembra().getTime());
							String fecha2= dt.format(fecha);
							
						error.put("fechaSiembra", fecha2);
						}
						catch(Exception ex){
							error.put("fechaSiembra", "fecha no valida");
						}
					}
					error.put("tipoCultivo",
							nuevaCotizacionCopia.getTipoCultivoNombre());
					error.put("hasAseguradas",
							nuevaCotizacionCopia.getNumeroHectareasAseguradas());
					error.put("hasLote",
							nuevaCotizacionCopia.getNumeroHectareasLote());
					if (nuevaCotizacionCopia.getEsTecnificado()) {
						error.put("esTecnificado", "SI");
					} else {
						error.put("esTecnificado", "NO");
					}
					error.put("provincia",
							nuevaCotizacionCopia.getProvinciaNombre());
					error.put("canton", nuevaCotizacionCopia.getCantonNombre());
					error.put("parroquia",
							nuevaCotizacionCopia.getParroquiaNombre());
					error.put("ubicacion",
							nuevaCotizacionCopia.getUbicacionCultivo());
					error.put("telefono", nuevaCotizacionCopia.getTelefono());
					error.put("gpsX", nuevaCotizacionCopia.getGpsX());
					error.put("gpsY", nuevaCotizacionCopia.getGpsY());
					error.put("detalle", e.getMessage());
					errorList.add(error);
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			return errorList;
		}

	}

	private static String crearCotizacion(String canal, String clienteNombre,
			String identificacion, Double montoAsegurado,
			Timestamp fechaSolicitud, String Canton, String Parroquia,
			String Ubicacion, String Telefono) {

		EntidadDAO entidadDAO = new EntidadDAO();
		ClienteDAO clienteDAO = new ClienteDAO();
		TipoIdentificacionDAO tipoDAO = new TipoIdentificacionDAO();
		PuntoVentaDAO pvDAO = new PuntoVentaDAO();
		VigenciaPolizaDAO vpDAO = new VigenciaPolizaDAO();
		GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
		ProductoXPuntoVentaDAO productoPorPuntoVentaDAO = new ProductoXPuntoVentaDAO();
		TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		EstadoDAO estadoDAO = new EstadoDAO();
		ParroquiaDAO parroquiaDAO = new ParroquiaDAO();
		DireccionDAO direccionDAO = new DireccionDAO();
		ZonaDAO zonaDAO = new ZonaDAO();
		TipoDireccionDAO tipoDireccionDAO = new TipoDireccionDAO();
		CiudadDAO ciudadDAO = new CiudadDAO();
		TipoEntidadDAO tipoEntidadDAO = new TipoEntidadDAO();
		
		Entidad entidad = new Entidad();
		Cliente cliente = new Cliente();
		Direccion direccion = new Direccion();

		CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
		EntidadTransaction entidadTransaction = new EntidadTransaction();
		ClienteTransaction clienteTransaction = new ClienteTransaction();
		DireccionTransaction direccionTransaction = new DireccionTransaction();

		Cotizacion cotizacion = new Cotizacion();

		entidad = entidadDAO.buscarEntidadPorIdentificacion(identificacion);
		
		if (identificacion.length() == 10){
			entidad.setTipoIdentificacion(tipoDAO.buscarPorId("1"));
			entidad.setTipoEntidad(tipoEntidadDAO.buscarPorId("1"));
		}if (identificacion.length() == 13){
			entidad.setTipoIdentificacion(tipoDAO.buscarPorId("3"));
			entidad.setTipoEntidad(tipoEntidadDAO.buscarPorId("1"));
		}else{							
			entidad.setTipoIdentificacion(tipoDAO.buscarPorId("4"));
			entidad.setTipoEntidad(tipoEntidadDAO.buscarPorId("2"));
		}
		entidad.setIdentificacion(identificacion);
		entidad.setNombres(clienteNombre);
		entidad.setNombreCompleto(clienteNombre);
		
		if (!Telefono.equals(""))
			entidad.setCelular(Telefono);

		if (entidad.getId() == null)
			entidad = entidadTransaction.crear(entidad);
		else
			entidad = entidadTransaction.editar(entidad);

		cliente = clienteDAO.buscarPorEntidadId(entidad);

		if (cliente.getId() == null) {
			ActividadEconomica actividad = new ActividadEconomica();
			ActividadEconomicaDAO actividadDAO = new ActividadEconomicaDAO();
			actividad = actividadDAO.buscarPorNombre("Ninguno");

			Cliente clienteNuevo = new Cliente();

			clienteNuevo.setEntidad(entidad);
			clienteNuevo.setActividadEconomica(actividad);
			clienteNuevo.setActivo(true);
			// ClienteTransaction clienteTransaction = new ClienteTransaction();
			cliente = clienteTransaction.crear(clienteNuevo);
			// falta en codigo del ensurance
		}
		
		//Tipo de canal
		AgriParametroXPuntoVenta PPuntoVenta = new AgriParametroXPuntoVenta();
		AgriParametroXPuntoVentaDAO PPuntoVentaProceso = new AgriParametroXPuntoVentaDAO();
		PPuntoVenta = PPuntoVentaProceso.buscarCodigoIntegracion(canal.toUpperCase().trim());
		PuntoVenta puntoVenta = new PuntoVenta();
		puntoVenta = pvDAO.buscarPorId(""
				+ PPuntoVenta.getPuntoVentaId());
		if (puntoVenta != null)
			cotizacion.setPuntoVenta(puntoVenta);
		else {
			puntoVenta = pvDAO.buscarPorId("109");
			cotizacion.setPuntoVenta(puntoVenta);
			PPuntoVenta = PPuntoVentaProceso
					.buscarCodigoIntegracion("109");
		}

		GrupoPorProducto grupoPorProducto = grupoPorProductoDAO
				.buscarPorNombre("AGRICOLA");

		ProductoXPuntoVenta pxpv = productoPorPuntoVentaDAO
				.buscarPorGrupoPuntoVenta(grupoPorProducto, puntoVenta);

		// if(!pxpv.equals(""))
		cotizacion.setProductoXPuntoVentaId(new BigInteger(pxpv.getId()));

		cotizacion.setVigenciaPoliza(vpDAO.buscarPorId("1"));

		cotizacion.setAgenteId(new BigInteger(puntoVenta.getAgenteId()));

		cotizacion.setClienteId(new BigInteger(cliente.getId()));

		grupoPorProducto = grupoPorProductoDAO.buscarPorNombre("AGRICOLA");
		cotizacion.setGrupoProductoId(BigInteger.valueOf(Long
				.valueOf(grupoPorProducto.getGrupoProducto().getId())));
		cotizacion.setGrupoPorProductoId(BigInteger.valueOf(Long
				.valueOf(grupoPorProducto.getId())));
		cotizacion.setProducto(grupoPorProducto.getProducto());

		// Agregamos el tipo de objeto a la cotizaci�n
		cotizacion.setTipoObjeto(tipoObjetoDAO.buscarPorNombre("Agricola"));
		cotizacion.setEstado(estadoDAO
				.buscarPorNombre("Borrador", "Cotizacion"));

		cotizacion.setUsuario(usuarioDAO.buscarPorLogin("dgarzon"));
		Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

		if (!fechaActual.equals(""))
			cotizacion.setFechaElaboracion(fechaActual);

		if (cotizacion.getEtapaWizard() < 1) {
			cotizacion.setEtapaWizard(1);
		}
		
		cotizacion = cotizacionTransaction.crear(cotizacion);
		cotizacion.setNumeroTramite(cotizacion.getId());
		cotizacion = cotizacionTransaction.editar(cotizacion);
		grupoPorProducto = new GrupoPorProducto();
		grupoPorProductoDAO = new GrupoPorProductoDAO();
		grupoPorProducto = grupoPorProductoDAO.buscarPorId(cotizacion
				.getGrupoPorProductoId().toString());

		ProductoXPuntoVenta productoXPuntoVenta = new ProductoXPuntoVenta();
		ProductoXPuntoVentaDAO productoXPuntoVentaDAO = new ProductoXPuntoVentaDAO();
		productoXPuntoVenta = (ProductoXPuntoVenta) productoXPuntoVentaDAO
				.buscarPorGrupoPuntoVenta(grupoPorProducto,
						cotizacion.getPuntoVenta());

		List<Direccion> dir = direccionDAO.buscarPorEntidadId(entidad);
		if (dir.size() <= 0) {
			Ciudad ciudad = ciudadDAO.buscarPorNombre(Canton);
			direccion.setCiudad(ciudad);
			direccion.setTipoDireccion(tipoDireccionDAO.buscarPorId("3"));
			direccion.setZona(zonaDAO.buscarPorNombre("Rural"));
			direccion.setEntidad(entidad);
			Parroquia parroquia = parroquiaDAO.buscarPorNombre(Parroquia);
			direccion.setParroquia(parroquia);
			direccion.setCallePrincipal(Ubicacion);
			direccion.setCalleSecundaria(".");
			direccion.setNumero("S/N");
			direccion.setDatosDeReferencia(Ubicacion);
			direccion.setEsCobro(true);
			direccionTransaction.crear(direccion);
		}
		return cotizacion.getId().toString();
	}

	private static String crearObjetoCotizacion(String cotizacionId,
			String provincia, String canton, String parroquia,
			String ubicacion, String altitud, Timestamp fechaSolicitud,
			Double montoAsegurado, BigInteger tipoCultivo, String variedad,
			Timestamp fechaSiembra, Double hasLote, Double hasAsegurables,
			Boolean esTecnificaco, String x, String y, String disponeRiego,
			String disponeAsistencia, String tipoSeguro,
			String sucursalCanalId, String aniosCultivo, String Origen) {

		ClienteDAO clienteDAO = new ClienteDAO();
		TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();
		EstadoDAO estadoDAO = new EstadoDAO();
		CotizacionDAO cotizacionDAO = new CotizacionDAO();
		CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();
		AgriObjetoCotizacionDAO agriObjetoDAO = new AgriObjetoCotizacionDAO();
		AgriReglaDAO agriReglaDAO = new AgriReglaDAO();

		Cliente cliente = new Cliente();

		CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
		CotizacionDetalleTransaction cotizacionDetalleTransaction = new CotizacionDetalleTransaction();
		AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction = new AgriObjetoCotizacionTransaction();

		AgriObjetoCotizacion agriObjetoCotizacion = new AgriObjetoCotizacion();

		//CAMPOS QUE PERMITIRAN SABER EL ESTADO EN EL QUE PASARA LA COTIZACION
		
		boolean esEmitir= true;
		
		/* campos para el id y detalle de la regla */
		String reglaId = "";
		String reglaDetalle = "";
		/* campos para el id y detalle de la regla */

		if (provincia.length() <= 0)
			provincia = "0";

		if (canton.length() <= 0)
			canton = "0";

		if (provincia.length() <= 0)
			provincia = "0";

		if (parroquia.length() <= 0)
			parroquia = "0";

		if (altitud.length() <= 0)
			altitud = "0";

		if (montoAsegurado.equals(""))
			montoAsegurado = 0.0;

		if (tipoCultivo.equals(""))
			tipoCultivo = new BigInteger("0");

		if (hasLote.equals(""))
			hasLote = 0.0;

		if (hasAsegurables.equals(""))
			hasAsegurables = 0.0;

		if (x.length() <= 0)
			x = "0";

		if (y.length() <= 0)
			y = "0";

		if (aniosCultivo.length() <= 0)
			aniosCultivo = "0";

		if (Integer.parseInt(tipoSeguro) == 0) {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.DATE, -15);
			Calendar cM = Calendar.getInstance();
			cM.setTime(new Date());
			cM.add(Calendar.DATE, 15);
		}

		// Valido si existe la cotizacion
		Cotizacion objetoCotizacion = cotizacionDAO.buscarPorId(cotizacionId);
		double tasaTraida=0;
		if (objetoCotizacion != null) {
			//
			Double sumaAseguradaTotal = 0.0;
			Double primaNetaTotal = 0.0;
			// Obtengo el id del tipo de canal
			AgriTipoCalculo agriTipoCalculo = new AgriTipoCalculo();
			AgriTipoCalculoDAO agriTipoCalculoDAO = new AgriTipoCalculoDAO();
			agriTipoCalculo = agriTipoCalculoDAO.BuscarPorNombre("PRONACA");

			// Obtengo el valor de la tabla de reglas
			List<AgriRegla> reglas = agriReglaDAO.BuscarPorParametros(
					new BigInteger(provincia), new BigInteger(canton),
					tipoCultivo, agriTipoCalculo.getTipoCalculoId());
			if (reglas.size() != 0) {
				Boolean valido = false;
				for (AgriRegla regla : reglas) {
					if (regla.getClicloId() != null) {
						
							valido = true;
					}
					if (Double.parseDouble("" + regla.getCostoMantenimiento()) != 0
							|| Double.parseDouble("" + regla.getTasa()) != 0) {
						valido = true;
					}

				}
				if (valido) {
					tasaTraida=reglas.get(0).getTasa();
					if (Integer.parseInt(tipoSeguro) == 0) {
						sumaAseguradaTotal = montoAsegurado;//reglas.get(0).getCostoProduccion()
								//* Double.parseDouble(hasAsegurables.toString());
						primaNetaTotal = (sumaAseguradaTotal
								* reglas.get(0).getTasa() )/ 100;
					} else if (Integer.parseInt(tipoSeguro) == 1) {
						// long diffMSec = (new
						// Date()).getTime()-fechaSiembra.getTime();
						// long diff = (long)60 * (long)60 * (long)1000 *
						// (long)24 * (long)365;
						// long diffYears = (long) (diffMSec / diff);
						Double costoMantenimiento = reglas.get(0)
								.getCostoMantenimiento()
								* new Double(aniosCultivo)
								* Double.parseDouble(hasAsegurables.toString());
						sumaAseguradaTotal = montoAsegurado//reglas.get(0).getCostoProduccion()
								//* Double.parseDouble(hasAsegurables.toString())
								+ costoMantenimiento;
						primaNetaTotal = sumaAseguradaTotal
								* reglas.get(0).getTasa() / 100;
					} else {
						Double costoMantenimiento = reglas.get(0)
								.getCostoMantenimiento()
								* new Double(1)
								* Double.parseDouble(hasAsegurables.toString());
						sumaAseguradaTotal = costoMantenimiento;
						primaNetaTotal = sumaAseguradaTotal
								* reglas.get(0).getTasa() / 100;
					}

					reglaId = reglas.get(0).getReglaId().toString();
					reglaDetalle = reglas.get(0).getObservaciones();

				} else {
					esEmitir= false;
					if (Integer.parseInt(tipoSeguro) == 0) {
						sumaAseguradaTotal = 0 * Double
								.parseDouble(hasAsegurables.toString());
						primaNetaTotal = sumaAseguradaTotal * 0 / 100;
					} else if (Integer.parseInt(tipoSeguro) == 1) {
						// long diffMSec = (new
						// Date()).getTime()-fechaSiembra.getTime();
						// long diff = (long)60 * (long)60 * (long)1000 *
						// (long)24 * (long)365;
						// long diffYears = (long) (diffMSec / diff);
						Double costoMantenimiento = 0
								* new Double(aniosCultivo)
								* Double.parseDouble(hasAsegurables.toString());
						sumaAseguradaTotal = 0
								* Double.parseDouble(hasAsegurables.toString())
								+ costoMantenimiento;
						primaNetaTotal = sumaAseguradaTotal * 0 / 100;
					} else {
						Double costoMantenimiento = 0 * new Double(1)
								* Double.parseDouble(hasAsegurables.toString());
						sumaAseguradaTotal = costoMantenimiento;
						primaNetaTotal = sumaAseguradaTotal * 0 / 100;
					}

					// throw new
					// Exception("No se puede realizar la cotizaci�n. Porque la fecha de siembra no esta permitida en ningun ciclo.");
				}

			} else {
				esEmitir= false;
				if (Integer.parseInt(tipoSeguro) == 0) {
					sumaAseguradaTotal = 0 * Double.parseDouble(hasAsegurables
							.toString());
					primaNetaTotal = sumaAseguradaTotal * 0 / 100;
				} else if (Integer.parseInt(tipoSeguro) == 1) {
					// long diffMSec = (new
					// Date()).getTime()-fechaSiembra.getTime();
					// long diff = (long)60 * (long)60 * (long)1000 * (long)24 *
					// (long)365;
					// long diffYears = (long) (diffMSec / diff);
					Double costoMantenimiento = 0 * new Double(aniosCultivo)
							* Double.parseDouble(hasAsegurables.toString());
					sumaAseguradaTotal = 0
							* Double.parseDouble(hasAsegurables.toString())
							+ costoMantenimiento;
					primaNetaTotal = sumaAseguradaTotal * 0 / 100;
				} else {
					Double costoMantenimiento = 0 * new Double(1)
							* Double.parseDouble(hasAsegurables.toString());
					sumaAseguradaTotal = costoMantenimiento;
					primaNetaTotal = sumaAseguradaTotal * 0 / 100;
				}

				// throw new
				// Exception("No se puede realizar la cotizaci�n. Porque la provincia y ciudad seleccionadas no permite ese tipo de cultivo.");
			}

			// Cuento si ya hay al menos un detalle para la cotizacion
			List<CotizacionDetalle> listadoDetalles = cotizacionDetalleDAO
					.buscarCotizacionDetallePorCotizacion(objetoCotizacion);
			if (listadoDetalles.size() == 0) {
				// Inserta el detalle de la cotizaci�n

				agriObjetoCotizacion.setProvinciaId(new BigInteger(provincia));
				agriObjetoCotizacion.setCantonId(new BigInteger(canton));
				try {
					agriObjetoCotizacion.setParroquiaId(new BigInteger(
							parroquia));
				} catch (Exception e) {
					e.printStackTrace();
				}
				agriObjetoCotizacion.setTipoCultivoId(tipoCultivo);
				agriObjetoCotizacion.setVariedad(variedad);
				// agriObjetoCotizacion.setAgricultorTecnificado(Boolean.parseBoolean(esTecnificaco));
				agriObjetoCotizacion.setDisponeRiego(Boolean
						.parseBoolean(disponeRiego));
				agriObjetoCotizacion.setDisponeAsistencia(Boolean
						.parseBoolean(disponeAsistencia));
				agriObjetoCotizacion.setAgricultorTecnificado(esTecnificaco);
				agriObjetoCotizacion.setDireccionSiembra(ubicacion);
				if (!aniosCultivo.equals(""))
					agriObjetoCotizacion.setAniosCultivo(Integer
							.parseInt(aniosCultivo));
				if (!tipoSeguro.equals(""))
					agriObjetoCotizacion.setTipoSeguro(Integer
							.parseInt(tipoSeguro));
				else
					agriObjetoCotizacion.setTipoSeguro(0);
				if (!hasLote.equals(""))
					agriObjetoCotizacion.setHectareasLote(Float
							.parseFloat(hasLote.toString()));
				if (!x.equals("")) {

					try {
						agriObjetoCotizacion.setLatitud(Float.parseFloat(x));

					} catch (Exception e) {
						System.out.println("Latitud vacia");
					}
				}
				if (!y.equals(""))
					try {
						agriObjetoCotizacion.setLongitud(Float.parseFloat(y));

					} catch (Exception e) {
						System.out.println("Longitud vacia");
					}
				if (!hasAsegurables.equals(""))
					agriObjetoCotizacion.setHectareasAsegurables(Float
							.parseFloat(hasAsegurables.toString()));
				if (!montoAsegurado.equals(""))
					try {
						agriObjetoCotizacion.setMontoCredito(Float
								.parseFloat(montoAsegurado.toString()));
					} catch (Exception e) {
						System.out.println("Monto vacio");
					}
				if (sucursalCanalId.length() > 0)
					agriObjetoCotizacion.setSucursalCanalId(new BigInteger(
							sucursalCanalId));

				if (fechaSolicitud != null)
					agriObjetoCotizacion.setFechaCredito(fechaSolicitud);
				if (fechaSiembra != null)
					agriObjetoCotizacion.setFechaSiembra(fechaSiembra);
				if (altitud.length() > 0)
					agriObjetoCotizacion.setAltitudNivelMar(Float
							.parseFloat(altitud));

				agriObjetoCotizacion.setTipoOrigen("PRONACA");

				agriObjetoCotizacion.setTipoCalculo(reglaId);
				agriObjetoCotizacion.setObservacion(reglaDetalle);

				agriObjetoCotizacion = agriObjetoCotizacionTransaction
						.crear(agriObjetoCotizacion);

				// Creo la cotizaci�n detalle
				CotizacionDetalle nuevoCotizacionDetalle = new CotizacionDetalle();
				nuevoCotizacionDetalle.setCotizacion(objetoCotizacion);
				nuevoCotizacionDetalle.setNecesitaInspeccion(false);
				nuevoCotizacionDetalle.setTipoObjetoId(tipoObjetoDAO
						.buscarPorNombre("Agricola").getId());
				nuevoCotizacionDetalle.setObjetoId(agriObjetoCotizacion
						.getObjetoCotizacionId().toString());
				nuevoCotizacionDetalle.setSumaAseguradaItem(sumaAseguradaTotal);
				nuevoCotizacionDetalle.setPrimaNetaItem(primaNetaTotal);
				nuevoCotizacionDetalle.setTasa(tasaTraida);
				cotizacionDetalleTransaction.crear(nuevoCotizacionDetalle);
			} else {
				// Recupero el objeto detalle para actualizar
				CotizacionDetalle nuevoCotizacionDetalle = listadoDetalles
						.get(0);

				if (nuevoCotizacionDetalle.getObjetoId().equals(""))
					agriObjetoCotizacion = new AgriObjetoCotizacion();
				else
					agriObjetoCotizacion = agriObjetoDAO
							.buscarPorId(new BigInteger(nuevoCotizacionDetalle
									.getObjetoId()));

				// Inserta el detalle de la cotizaci�n

				agriObjetoCotizacion.setProvinciaId(new BigInteger(provincia));
				agriObjetoCotizacion.setCantonId(new BigInteger(canton));
				agriObjetoCotizacion.setParroquiaId(new BigInteger(parroquia));
				agriObjetoCotizacion.setTipoCultivoId(tipoCultivo);
				agriObjetoCotizacion.setVariedad(variedad);
				agriObjetoCotizacion.setAgricultorTecnificado(esTecnificaco);
				agriObjetoCotizacion.setDisponeRiego(Boolean
						.parseBoolean(disponeRiego));
				agriObjetoCotizacion.setDisponeAsistencia(Boolean
						.parseBoolean(disponeAsistencia));
				agriObjetoCotizacion.setDireccionSiembra(ubicacion);
				if (!aniosCultivo.equals(""))
					agriObjetoCotizacion.setAniosCultivo(Integer
							.parseInt(aniosCultivo));
				if (!tipoSeguro.equals(""))
					agriObjetoCotizacion.setTipoSeguro(Integer
							.parseInt(tipoSeguro));
				else
					agriObjetoCotizacion.setTipoSeguro(0);
				if (!hasLote.equals(""))
					agriObjetoCotizacion.setHectareasLote(Float
							.parseFloat(hasLote.toString()));
				if (!hasAsegurables.equals(""))
					agriObjetoCotizacion.setHectareasAsegurables(Float
							.parseFloat(hasAsegurables.toString()));
				if (!montoAsegurado.equals("")) {
					try {
						agriObjetoCotizacion.setMontoCredito(Float
								.parseFloat(montoAsegurado.toString()));
					} catch (Exception e) {
						System.out.print("No hay monto de credito");
					}
				}
				if (!x.equals("")) {
					try {
						agriObjetoCotizacion.setLatitud(Float.parseFloat(x));
					} catch (Exception e) {
						System.out.print("No hay latitud");
					}
				}
				if (!y.equals("")) {
					try {
						agriObjetoCotizacion.setLongitud(Float.parseFloat(y));
					} catch (Exception e) {
						System.out.print("No hay longitud");
					}
				}
				if (sucursalCanalId.length() > 0)
					agriObjetoCotizacion.setSucursalCanalId(new BigInteger(
							sucursalCanalId));

				if (fechaSolicitud != null) {
					try {
						agriObjetoCotizacion.setFechaCredito(fechaSolicitud);
					} catch (Exception e) {
						System.out.print("No hay fechaCredito");
					}
				}
				if (fechaSiembra != null) {
					agriObjetoCotizacion.setFechaSiembra(fechaSiembra);
				}
				agriObjetoCotizacion.setAltitudNivelMar(Float
						.parseFloat(altitud));

				agriObjetoCotizacion.setTipoOrigen("PRONANCA");

				agriObjetoCotizacion.setTipoCalculo(reglaId);
				agriObjetoCotizacion.setObservacion(reglaDetalle);

				agriObjetoCotizacion = agriObjetoCotizacionTransaction
						.editar(agriObjetoCotizacion);

				// Cambio los valores de la poliza
				nuevoCotizacionDetalle.setSumaAseguradaItem(sumaAseguradaTotal);
				nuevoCotizacionDetalle.setPrimaNetaItem(primaNetaTotal);
				nuevoCotizacionDetalle.setTasa(tasaTraida);
				cotizacionDetalleTransaction.editar(nuevoCotizacionDetalle);

			}
			// Verificar si es emision directa o no.
			
			
			Estado estado = new Estado();
			// EstadoDAO estadoDAO = new EstadoDAO();
			AgriParametroXPuntoVenta PPuntoVenta = new AgriParametroXPuntoVenta();
			AgriParametroXPuntoVentaDAO PPuntoVentaProceso = new AgriParametroXPuntoVentaDAO();
			PPuntoVenta = PPuntoVentaProceso.buscarPorId(new BigInteger(objetoCotizacion.getPuntoVenta().getId()));
			if(!PPuntoVenta.getEmisionDirecta())
				esEmitir=false;
			
			if (esEmitir)
				estado = estadoDAO.buscarPorNombreClase("Pendiente de Emitir",
						"Cotizacion");
			else
				estado = estadoDAO.buscarPorNombreClase("Pendiente Aprobar",
						"Cotizacion");

			// Las cotizaciones del canal pronaca no pasan por la etapa de carga
			// de veneficiario o asegurado
			// en este caso el cliente es el asegurado
			cliente = clienteDAO.buscarPorId(objetoCotizacion.getClienteId()
					.toString());
			objetoCotizacion.setAsegurado(cliente.getEntidad());

			objetoCotizacion.setEstado(estado);
			objetoCotizacion.setSumaAseguradaTotal(sumaAseguradaTotal);
			objetoCotizacion.setPrimaNetaTotal(primaNetaTotal.toString());
			objetoCotizacion.setEtapaWizard(2);
			cotizacionTransaction.editar(objetoCotizacion);
		}

		/* COTIZACIONES ARCHIVO PLANO CARGA PREVIA */
		return cotizacionId;
	}

	private static void obtenerValores(String cotizacionId) {
		CotizacionDAO cotizacionDAO = new CotizacionDAO();
		CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();
		CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
		VariableSistemaDAO variableDAO = new VariableSistemaDAO();

		Cotizacion cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
		List<CotizacionDetalle> cotizacionesDetalle = cotizacionDetalleDAO
				.buscarCotizacionDetallePorCotizacion(cotizacion);

		double valorPrima = 0;
		double valorAsegurado = 0;
		double valorFinalPrima = 0;
		if (cotizacionesDetalle.size() != 0) {
			// Calculo los valores
			for (CotizacionDetalle cotizacionDetalle : cotizacionesDetalle) {
				valorPrima = valorPrima + cotizacionDetalle.getPrimaNetaItem();
				valorAsegurado = valorAsegurado
						+ cotizacionDetalle.getSumaAseguradaItem();
				valorPrima = Math.rint(valorPrima * 100) / 100;
				valorAsegurado = Math.rint(valorAsegurado * 100) / 100;
			}
			valorFinalPrima = valorPrima;
			TipoVariableDAO tipoVariableDao = new TipoVariableDAO();
			TipoVariable tipoVariable = tipoVariableDao.buscarPorId("3");
			List<VariableSistema> variablesistema = variableDAO
					.buscarTipoVariable(tipoVariable);
			double valorBase = 0;
			double valorDerechosEmision = 0;
			double valorSeguroCampesino = 0;
			double valorSuperBancos = 0;
			double valorIva = 0;
			double valorSubTotal = 0;
			double valorTotal = 0;
			if (variablesistema.size() > 0) {
				for (VariableSistema variable : variablesistema) {
					if (variable.getNombre().equals("DERECHOS_EMISION")) {
						/*
						 * valorBase = Double.parseDouble(variable.getValor())+
						 * valorFinalPrima; valorDerechosEmision =
						 * Double.parseDouble(variable.getValor());
						 * cotizacion.setImpDerechoEmision
						 * (valorDerechosEmision);
						 * result.put("valorDerechosEmision",
						 * valorDerechosEmision);
						 */
						// Proceso de Calculo de Derechos de emision dependiendo
						// del valor de la prima
						DerechoEmisonDAO derechoEmisonDAO = new DerechoEmisonDAO();
						List<DerechoEmision> derechoEmision = derechoEmisonDAO
								.buscarTodos();
						for (DerechoEmision rs : derechoEmision) {
							Double valorDesde = Double.parseDouble(rs
									.getPrimaDesde());
							Double valorHasta = Double.parseDouble(rs
									.getPrimaHasta());
							if (valorPrima >= valorDesde
									&& valorPrima <= valorHasta) {
								valorBase = Double.parseDouble(rs.getValor())
										+ valorFinalPrima;
								valorDerechosEmision = Double.parseDouble(rs
										.getValor());
								cotizacion
										.setImpDerechoEmision(valorDerechosEmision);

								break;
							}
						}

					} else if (variable.getNombre().equals("SEGURO_CAMPESINO")) {
						valorSeguroCampesino = Math.rint(Double
								.parseDouble(variable.getValor())
								* valorFinalPrima / 100 * 100) / 100;
						valorBase = valorBase + valorSeguroCampesino;
						cotizacion.setImpSeguroCampesino(valorSeguroCampesino);
					} else if (variable.getNombre().equals("SUPER_DE_BANCOS")) {
						valorSuperBancos = Math.rint(Double
								.parseDouble(variable.getValor())
								* valorFinalPrima / 100 * 100) / 100;
						cotizacion.setImpSuperBancos(valorSuperBancos);
						valorBase = valorBase + valorSuperBancos;

					}

					else if (variable.getNombre().equals("SUBTOTAL")) {
						valorSubTotal = Math.rint(valorBase * 100) / 100;
					} else if (variable.getNombre().equals("IVA")) {
						valorIva = Math.rint(Double.parseDouble(variable
								.getValor()) * valorSubTotal / 100 * 100) / 100;
						cotizacion.setImpIva(valorIva);
					}
				}
				valorTotal = Math.rint((valorBase + valorIva) * 100) / 100;
				cotizacion.setValorDescuento(0);
				cotizacion.setTotalFactura(valorTotal);
				cotizacionTransaction.editar(cotizacion);
			}
		}
		//
	}

}