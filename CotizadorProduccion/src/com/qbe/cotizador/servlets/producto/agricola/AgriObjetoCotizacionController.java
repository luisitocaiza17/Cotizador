package com.qbe.cotizador.servlets.producto.agricola;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.cotizacion.TipoObjetoDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.DerechoEmisonDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCicloDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCotizacionMaxDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriObjetoCotizacionDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParametroXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriReglaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCalculoDAO;
import com.qbe.cotizador.dao.seguridad.TipoVariableDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.AgriCiclo;
import com.qbe.cotizador.model.AgriCotizacionMax;
import com.qbe.cotizador.model.AgriObjetoCotizacion;
import com.qbe.cotizador.model.AgriParametroXPuntoVenta;
import com.qbe.cotizador.model.AgriRegla;
import com.qbe.cotizador.model.AgriTipoCalculo;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.DerechoEmision;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.Provincia;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.TipoVariable;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.transaction.cotizacion.CotizacionDetalleTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriObjetoCotizacionTransaction;
//Import del servicio

/**
 * Servlet implementation class ObjetoGanaderoController
 */
@WebServlet("/AgriObjetoCotizacionController")
public class AgriObjetoCotizacionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AgriObjetoCotizacionController() {
		super();
		//TODO Auto-generated constructor stub
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
		try{
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String previaId = request.getParameter("previaId") == null ? "" : request.getParameter("previaId");
			CotizacionDAO cotizacionDAO=new CotizacionDAO();
			CotizacionDetalleDAO cotizacionDetalleDAO=new CotizacionDetalleDAO();
			ClienteDAO clienteDAO=new ClienteDAO();
			TipoObjetoDAO tipoObjetoDAO=new TipoObjetoDAO();
			AgriObjetoCotizacionDAO agriObjetoDAO=new AgriObjetoCotizacionDAO();
			AgriReglaDAO agriReglaDAO=new AgriReglaDAO();

			CotizacionTransaction cotizacionTransaction= new CotizacionTransaction();
			AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction = new AgriObjetoCotizacionTransaction();
			CotizacionDetalleTransaction cotizacionDetalleTransaction= new CotizacionDetalleTransaction();			

			if(tipoConsulta.equalsIgnoreCase("crear"))
			{
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();
				String provinciaId = request.getParameter("provinciaId") == null ? "" : request.getParameter("provinciaId");
				String cantonId = request.getParameter("cantonId") == null ? "" : request.getParameter("cantonId");
				String parroquiaId = request.getParameter("parroquiaId") == null ? "" : request.getParameter("parroquiaId");
				String direccionSiembra = request.getParameter("direccionSiembra") == null ? "" : request.getParameter("direccionSiembra").trim();
				String altitudNivelMar = request.getParameter("altitudNivelMar") == null ? "" : request.getParameter("altitudNivelMar").trim();
				String fechaCredito = request.getParameter("fechaCredito") == null ? "" : request.getParameter("fechaCredito").trim();
				String montoCredito = request.getParameter("montoCredito") == null ? "" : request.getParameter("montoCredito").trim();
				String tipoCultivoId = request.getParameter("tipoCultivoId") == null ? "" : request.getParameter("tipoCultivoId").trim();
				String variedad = request.getParameter("variedad") == null ? "" : request.getParameter("variedad").trim();
				String fechaSiembra = request.getParameter("fechaSiembra") == null ? "" : request.getParameter("fechaSiembra").trim();
				String hectareasLote = request.getParameter("hectareasLote") == null ? "" : request.getParameter("hectareasLote").trim();
				String hectareasAsegurables = request.getParameter("hectareasAsegurables") == null ? "" : request.getParameter("hectareasAsegurables").trim();
				String agricultorTecnificado = request.getParameter("agricultorTecnificado") == null ? "" : request.getParameter("agricultorTecnificado").trim();
				String latitud = request.getParameter("latitud") == null ? "" : request.getParameter("latitud").trim();
				String longitud = request.getParameter("longitud") == null ? "" : request.getParameter("longitud").trim();
				String disponeRiego = request.getParameter("disponeRiego") == null ? "" : request.getParameter("disponeRiego").trim();
				String disponeAsistencia = request.getParameter("disponeAsistencia") == null ? "" : request.getParameter("disponeAsistencia").trim();
				String tipoSeguro = request.getParameter("tipoSeguro") == null ? "" : request.getParameter("tipoSeguro").trim();
				String sucursalCanalId = request.getParameter("sucursalCanalId") == null ? "" : request.getParameter("sucursalCanalId").trim();
				String aniosCultivo = request.getParameter("aniosCultivo") == null ? "" : request.getParameter("aniosCultivo").trim();
				
				AgriObjetoCotizacion agriObjetoCotizacion=new AgriObjetoCotizacion();
				
				/*campos para el id y detalle de la regla*/
				String reglaId = "";
				String reglaDetalle = "";
				/*campos para el id y detalle de la regla*/
				
				Date dateCredito=null;
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				if(fechaCredito!="")
				{
					try{
						dateCredito=formatter.parse(fechaCredito);
					}catch(Exception e){
						System.out.print("fecha credito vacia");
					}
				}

				Date dateSiembra=null;
				if(fechaSiembra!=""){
					dateSiembra=formatter.parse(fechaSiembra);
				}

				/*if(Integer.parseInt(tipoSeguro)==0){
					Calendar c = Calendar.getInstance();    
					c.setTime(new Date());
					c.add(Calendar.DATE, -15);
					Date dateMenor=c.getTime();
					
					Calendar cM = Calendar.getInstance();    
					cM.setTime(new Date());
					cM.add(Calendar.DATE, 15);
					Date dateMayor=cM.getTime();
	
					if(!(dateSiembra.after(dateMenor) && dateSiembra.before(dateMayor)))
						throw new Exception("No se puede realizar la cotizaci�n. Porque la fecha de siembra debe estar entre 15 d�as m�s y 15 d�as menos de la fecha actual.");
				}*/

				//Valido si existe la cotizacion
				Cotizacion objetoCotizacion=cotizacionDAO.buscarPorId(cotizacionId);
				if(objetoCotizacion!=null)
				{
					//
					Double sumaAseguradaTotal=0.0;
					Double primaNetaTotal=0.0;
					//Obtengo el id del tipo de canal
					AgriTipoCalculo agriTipoCalculo= new AgriTipoCalculo();
					AgriTipoCalculoDAO agriTipoCalculoDAO = new AgriTipoCalculoDAO();
					agriTipoCalculo=agriTipoCalculoDAO.BuscarPorNombre("GENERAL");
					
					//Obtengo el valor de la tabla de reglas
					List<AgriRegla> reglas= agriReglaDAO.BuscarPorParametros(new BigInteger(provinciaId), new BigInteger(cantonId), new BigInteger(tipoCultivoId),agriTipoCalculo.getTipoCalculoId());
					if(reglas.size()!=0)
					{	
						if(reglas.get(0).getCostoProduccion()!=0 || reglas.get(0).getTasa()!=0){
							Boolean valido=false;
							for(AgriRegla regla:reglas){
								if(regla.getClicloId()!=null){
									AgriCicloDAO cicloDAO=new AgriCicloDAO();
									AgriCiclo ciclo = cicloDAO.BuscarPorId(regla.getClicloId());
									Date fechafin=ciclo.getFechaFin();
									Date fechaInicio=ciclo.getFechaInicio();
									if(dateSiembra.after(fechaInicio) && dateSiembra.before(fechafin))
										valido=true;
								}
								else {
									if(regla.getAceptabilidadDesde()!=null && regla.getAceptabilidadHasta()!=null){
										if(dateSiembra.after(regla.getAceptabilidadDesde()) && dateSiembra.before(regla.getAceptabilidadHasta()))
											valido=true;
									}
									else
										valido=true;
								}
							}
							if(valido){							
								
								if(Integer.parseInt(tipoSeguro)==0){
									sumaAseguradaTotal=reglas.get(0).getCostoProduccion()*Double.parseDouble(hectareasAsegurables);
									primaNetaTotal=sumaAseguradaTotal*reglas.get(0).getTasa()/100;
								}
								else if(Integer.parseInt(tipoSeguro)==1){
									//long diffMSec = (new Date()).getTime()-dateSiembra.getTime();
									//long diff = (long)60 * (long)60 * (long)1000 * (long)24 * (long)365;
									//long diffYears = (long) (diffMSec / diff);
									Double costoMantenimiento = reglas.get(0).getCostoMantenimiento() * new Double(aniosCultivo) * Double.parseDouble(hectareasAsegurables);
									sumaAseguradaTotal=reglas.get(0).getCostoProduccion() * Double.parseDouble(hectareasAsegurables) + costoMantenimiento;
									primaNetaTotal=sumaAseguradaTotal*reglas.get(0).getTasa()/100;
								}
								else{
									Double costoMantenimiento = reglas.get(0).getCostoMantenimiento() * new Double(1) * Double.parseDouble(hectareasAsegurables);
									sumaAseguradaTotal=costoMantenimiento;
									primaNetaTotal=sumaAseguradaTotal*reglas.get(0).getTasa()/100;
								}
								
								reglaId = reglas.get(0).getReglaId().toString();
								reglaDetalle = reglas.get(0).getObservaciones();						
	
							}
							else{
								throw new Exception("No se puede realizar la cotizaci�n. Porque la fecha de siembra no esta permitida en ningun ciclo.");
							}
	
						}
						else{
							throw new Exception("No se puede realizar la cotizaci�n. Porque la provincia y ciudad seleccionadas no permite ese tipo de cultivo.");
						}
					}
					else{
						throw new Exception("No se puede realizar la cotizaci�n. Porque la provincia y ciudad seleccionadas no permite ese tipo de cultivo.");
					}


					//Cuento si ya hay al menos un detalle para la cotizacion
					List<CotizacionDetalle> listadoDetalles=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(objetoCotizacion);
					if(listadoDetalles.size()==0)
					{
						//Inserta el detalle de la cotizaci�n
						
						agriObjetoCotizacion.setProvinciaId(new BigInteger(provinciaId));
						agriObjetoCotizacion.setCantonId(new BigInteger(cantonId));
						agriObjetoCotizacion.setAgriParroquiaId(parroquiaId);
						agriObjetoCotizacion.setTipoCultivoId(new BigInteger(tipoCultivoId));
						agriObjetoCotizacion.setVariedad(variedad);
						agriObjetoCotizacion.setAgricultorTecnificado(Boolean.parseBoolean(agricultorTecnificado));
						agriObjetoCotizacion.setDisponeRiego(Boolean.parseBoolean(disponeRiego));
						agriObjetoCotizacion.setDisponeAsistencia(Boolean.parseBoolean(disponeAsistencia));
						agriObjetoCotizacion.setAgricultorTecnificado(Boolean.parseBoolean(agricultorTecnificado));
						agriObjetoCotizacion.setDireccionSiembra(direccionSiembra);
						if(!aniosCultivo.equals(""))
							agriObjetoCotizacion.setAniosCultivo(Integer.parseInt(aniosCultivo));
						if(!tipoSeguro.equals(""))
							agriObjetoCotizacion.setTipoSeguro(Integer.parseInt(tipoSeguro));
						else
							agriObjetoCotizacion.setTipoSeguro(0);
						if(!hectareasLote.equals(""))
							agriObjetoCotizacion.setHectareasLote(Float.parseFloat(hectareasLote));
						if(!latitud.equals("")){
							
							try{
								agriObjetoCotizacion.setLatitud(Float.parseFloat(latitud));
							}catch(Exception e){
								System.out.println("Latitud vacia");
							}
						}
						if(!longitud.equals(""))
							try{
								agriObjetoCotizacion.setLongitud(Float.parseFloat(longitud));
							}catch(Exception e){
								System.out.println("Longitud vacia");
							}
						if(!hectareasAsegurables.equals(""))
							agriObjetoCotizacion.setHectareasAsegurables(Float.parseFloat(hectareasAsegurables));
						if(!montoCredito.equals(""))
							try{
								agriObjetoCotizacion.setMontoCredito(Float.parseFloat(montoCredito));
							}catch(Exception e){
								System.out.println("Monto vacio");
							}
						if(!sucursalCanalId.equals(""))
							agriObjetoCotizacion.setSucursalCanalId(new BigInteger(sucursalCanalId));

						if(dateCredito!=null)
							agriObjetoCotizacion.setFechaCredito(dateCredito);
						if(dateSiembra!=null)
							agriObjetoCotizacion.setFechaSiembra(dateSiembra);
						if(!altitudNivelMar.equals(""))
							agriObjetoCotizacion.setAltitudNivelMar(Float.parseFloat(altitudNivelMar));

						agriObjetoCotizacion.setTipoOrigen("COTIZADOR_ONLINE");
						
						agriObjetoCotizacion.setTipoCalculo(reglaId);
						agriObjetoCotizacion.setObservacion(reglaDetalle);
						
						agriObjetoCotizacion=agriObjetoCotizacionTransaction.crear(agriObjetoCotizacion);

						//Creo la cotizaci�n detalle
						CotizacionDetalle nuevoCotizacionDetalle=new CotizacionDetalle();
						nuevoCotizacionDetalle.setCotizacion(objetoCotizacion);
						nuevoCotizacionDetalle.setNecesitaInspeccion(false);
						nuevoCotizacionDetalle.setTipoObjetoId(tipoObjetoDAO.buscarPorNombre("Agricola").getId());
						nuevoCotizacionDetalle.setObjetoId(agriObjetoCotizacion.getObjetoCotizacionId().toString());
						nuevoCotizacionDetalle.setSumaAseguradaItem(sumaAseguradaTotal);
						nuevoCotizacionDetalle.setPrimaNetaItem(primaNetaTotal);
						cotizacionDetalleTransaction.crear(nuevoCotizacionDetalle);

						result.put("cotizacionId",objetoCotizacion.getId());
						result.put("objetoGanaderoId",agriObjetoCotizacion.getObjetoCotizacionId());
					}
					else
					{
						//Recupero el objeto detalle para actualizar
						CotizacionDetalle nuevoCotizacionDetalle=listadoDetalles.get(0);

						//AgriObjetoCotizacion agriObjetoCotizacion;

						//Valido si existe el objeto agricol para ese datalle, si existe lo recupero 
						//caso contrario me toca crearlo

						if(nuevoCotizacionDetalle.getObjetoId().equals(""))
							agriObjetoCotizacion=new AgriObjetoCotizacion();
						else
							agriObjetoCotizacion=agriObjetoDAO.buscarPorId(new BigInteger(nuevoCotizacionDetalle.getObjetoId()));

						//Inserta el detalle de la cotizaci�n

						agriObjetoCotizacion.setProvinciaId(new BigInteger(provinciaId));
						agriObjetoCotizacion.setCantonId(new BigInteger(cantonId));
						agriObjetoCotizacion.setAgriParroquiaId(parroquiaId);
						agriObjetoCotizacion.setTipoCultivoId(new BigInteger(tipoCultivoId));
						agriObjetoCotizacion.setVariedad(variedad);
						agriObjetoCotizacion.setAgricultorTecnificado(Boolean.parseBoolean(agricultorTecnificado));
						agriObjetoCotizacion.setDisponeRiego(Boolean.parseBoolean(disponeRiego));
						agriObjetoCotizacion.setDisponeAsistencia(Boolean.parseBoolean(disponeAsistencia));
						agriObjetoCotizacion.setDireccionSiembra(direccionSiembra);
						if(!aniosCultivo.equals(""))
							agriObjetoCotizacion.setAniosCultivo(Integer.parseInt(aniosCultivo));
						if(!tipoSeguro.equals(""))
							agriObjetoCotizacion.setTipoSeguro(Integer.parseInt(tipoSeguro));
						else
							agriObjetoCotizacion.setTipoSeguro(0);
						if(!hectareasLote.equals(""))
							agriObjetoCotizacion.setHectareasLote(Float.parseFloat(hectareasLote));
						if(!hectareasAsegurables.equals(""))
							agriObjetoCotizacion.setHectareasAsegurables(Float.parseFloat(hectareasAsegurables));
						if(!montoCredito.equals("")){
							try{
								agriObjetoCotizacion.setMontoCredito(Float.parseFloat(montoCredito));
							}catch (Exception e) {
								System.out.print("No hay monto de credito");
							}
						}
						if(!latitud.equals("")){
							try{
								agriObjetoCotizacion.setLatitud(Float.parseFloat(latitud));
							}catch (Exception e) {
								System.out.print("No hay latitud");
							}
						}
						if(!longitud.equals("")){
							try{
								agriObjetoCotizacion.setLongitud(Float.parseFloat(longitud));
							}catch (Exception e) {
								System.out.print("No hay longitud");
							}
						}
						if(!sucursalCanalId.equals(""))
							agriObjetoCotizacion.setSucursalCanalId(new BigInteger(sucursalCanalId));
						if(dateCredito!=null)
						{
							try{
								agriObjetoCotizacion.setFechaCredito(dateCredito);
							}catch (Exception e) {
								System.out.print("No hay fechaCredito");
							}
						}
						if(fechaSiembra!=null)
						{
							agriObjetoCotizacion.setFechaSiembra(dateSiembra);
						}
						agriObjetoCotizacion.setAltitudNivelMar(Float.parseFloat(altitudNivelMar));
						
						agriObjetoCotizacion.setTipoOrigen("COTIZADOR_ONLINE");
						
						agriObjetoCotizacion.setTipoCalculo(reglaId);
						agriObjetoCotizacion.setObservacion(reglaDetalle);

						agriObjetoCotizacion=agriObjetoCotizacionTransaction.editar(agriObjetoCotizacion);

						//Cambio los valores de la poliza
						nuevoCotizacionDetalle.setSumaAseguradaItem(sumaAseguradaTotal);
						nuevoCotizacionDetalle.setPrimaNetaItem(primaNetaTotal);
						cotizacionDetalleTransaction.editar(nuevoCotizacionDetalle);


						result.put("cotizacionId",objetoCotizacion.getId());
						result.put("objetoGanaderoId",agriObjetoCotizacion.getObjetoCotizacionId());
					}
					objetoCotizacion.setSumaAseguradaTotal(sumaAseguradaTotal);
					objetoCotizacion.setPrimaNetaTotal(primaNetaTotal.toString());
					objetoCotizacion.setEtapaWizard(2);
					cotizacionTransaction.editar(objetoCotizacion);
				}
			}
			
			
			/*COTIZACIONES ARCHIVO PLANO CARGA PREVIA*/
			if(tipoConsulta.equalsIgnoreCase("crearArchivoPlano")){
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();
				String provinciaId = request.getParameter("provinciaId") == null ? "" : request.getParameter("provinciaId");
				String cantonId = request.getParameter("cantonId") == null ? "" : request.getParameter("cantonId");
				String parroquiaId = request.getParameter("parroquiaId") == null ? "" : request.getParameter("parroquiaId");
				String direccionSiembra = request.getParameter("direccionSiembra") == null ? "" : request.getParameter("direccionSiembra").trim();
				String altitudNivelMar = request.getParameter("altitudNivelMar") == null ? "" : request.getParameter("altitudNivelMar").trim();
				String fechaCredito = request.getParameter("fechaCredito") == null ? "" : request.getParameter("fechaCredito").trim();
				String montoCredito = request.getParameter("montoCredito") == null ? "" : request.getParameter("montoCredito").trim();
				String tipoCultivoId = request.getParameter("tipoCultivoId") == null ? "" : request.getParameter("tipoCultivoId").trim();
				String variedad = request.getParameter("variedad") == null ? "" : request.getParameter("variedad").trim();
				String fechaSiembra = request.getParameter("fechaSiembra") == null ? "" : request.getParameter("fechaSiembra").trim();
				String hectareasLote = request.getParameter("hectareasLote") == null ? "" : request.getParameter("hectareasLote").trim();
				String hectareasAsegurables = request.getParameter("hectareasAsegurables") == null ? "" : request.getParameter("hectareasAsegurables").trim();
				String agricultorTecnificado = request.getParameter("agricultorTecnificado") == null ? "" : request.getParameter("agricultorTecnificado").trim();
				String latitud = request.getParameter("latitud") == null ? "" : request.getParameter("latitud").trim();
				String longitud = request.getParameter("longitud") == null ? "" : request.getParameter("longitud").trim();
				String disponeRiego = request.getParameter("disponeRiego") == null ? "" : request.getParameter("disponeRiego").trim();
				String disponeAsistencia = request.getParameter("disponeAsistencia") == null ? "" : request.getParameter("disponeAsistencia").trim();
				String tipoSeguro = request.getParameter("tipoSeguro") == null ? "" : request.getParameter("tipoSeguro").trim();
				String sucursalCanalId = request.getParameter("sucursalCanalId") == null ? "" : request.getParameter("sucursalCanalId").trim();
				String aniosCultivo = request.getParameter("aniosCultivo") == null ? "" : request.getParameter("aniosCultivo").trim();
				String previaId1 = request.getParameter("previaId") == null ? "" : request.getParameter("previaId").trim();
				AgriObjetoCotizacion agriObjetoCotizacion=new AgriObjetoCotizacion();
				
				/*campos para el id y detalle de la regla*/
				String reglaId = "";
				String reglaDetalle = "";
				String observaciones="";
				Boolean esEmitido=true;
				double costoProduccion=0;
				double tasa=0;
				/*campos para el id y detalle de la regla*/
				
				
				if(provinciaId.length() <=0 )
					provinciaId = "0";
				
				if(cantonId.length() <=0 )
					cantonId = "0";
				
				if(provinciaId.length() <=0 )
					provinciaId = "0";				
				
				if(parroquiaId.length() <=0 )
					parroquiaId = "0";
				
				if(altitudNivelMar.length() <=0 )
					altitudNivelMar = "0";
				
				if(montoCredito.length() <=0 )
					montoCredito = "0";
				
				if(tipoCultivoId.length() <=0 )
					tipoCultivoId = "0";
				
				if(hectareasLote.length() <=0 )
					hectareasLote = "0";
				
				if(hectareasAsegurables.length() <=0 )
					hectareasAsegurables = "0";
				
				if(latitud.length() <=0 )
					latitud = "0";
				
				if(longitud.length() <=0 )
					longitud = "0";
				
				if(aniosCultivo.length() <=0 )
					aniosCultivo = "0";

				Date dateCredito=null;
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				if(fechaCredito!="")
				{
					try{
						dateCredito=formatter.parse(fechaCredito);
					}catch(Exception e){
						System.out.print("fecha credito vacia");
					}
				}

				Date dateSiembra=null;
				if(fechaSiembra!=""){
					dateSiembra=formatter.parse(fechaSiembra);
				}else
					esEmitido=false;

				/*if(Integer.parseInt(tipoSeguro)==0){
					Calendar c = Calendar.getInstance();    
					c.setTime(new Date());
					c.add(Calendar.DATE, -15);
					Date dateMenor=c.getTime();
					
					Calendar cM = Calendar.getInstance();    
					cM.setTime(new Date());
					cM.add(Calendar.DATE, 15);
					Date dateMayor=cM.getTime();
	
					if(!(dateSiembra.after(dateMenor) && dateSiembra.before(dateMayor)))
						throw new Exception("No se puede realizar la cotizaci�n. Porque la fecha de siembra debe estar entre 15 d�as m�s y 15 d�as menos de la fecha actual.");
				}*/

				//Valido si existe la cotizacion
				Cotizacion cotizacion=cotizacionDAO.buscarPorId(cotizacionId);
				if(cotizacion!=null)
				{
					//
					Double sumaAseguradaTotal=0.0;
					Double primaNetaTotal=0.0;
				
					//Obtengo el id del tipo de canal
					AgriTipoCalculo agriTipoCalculo= new AgriTipoCalculo();
					AgriTipoCalculoDAO agriTipoCalculoDAO = new AgriTipoCalculoDAO();
					agriTipoCalculo=agriTipoCalculoDAO.BuscarPorNombre("PRONACA");
					//Obtengo el valor de la tabla de reglas
					String nuestroCosto = "0.0";//para verificar si existen diferencias entre costo que llega y que tenemos
					String idTipoCalculo = "";//para saber en base a que regla se calcularon los datos
					List<AgriRegla> reglas= agriReglaDAO.BuscarPorParametros(new BigInteger(provinciaId), new BigInteger(cantonId), new BigInteger(tipoCultivoId), agriTipoCalculo.getTipoCalculoId());

					if(reglas.size()!=0){
						for (AgriRegla rs : reglas) {
							if(rs.getTasa()!=0 || rs.getTasa()!=0.0 ){//si tiene tasa debe tener o costo de produccion o costo de mantenimiento
								tasa = Double.parseDouble(""+rs.getTasa());
								if(rs.getCostoProduccion()!=0){
									nuestroCosto = (""+rs.getCostoProduccion());
								}else{
									nuestroCosto = (""+rs.getCostoMantenimiento());
								}
								observaciones = observaciones+" " + rs.getObservaciones();
								idTipoCalculo = "" + rs.getReglaId();
								sumaAseguradaTotal=Double.parseDouble(nuestroCosto)*Double.parseDouble(hectareasAsegurables);
								primaNetaTotal=sumaAseguradaTotal*reglas.get(0).getTasa()/100;	
							}
						}
					}else{
						throw new Exception("No existe reglas de configuracion para este cultivo");
					}
					
					if(tasa==0 || tasa==0.0)
						throw new Exception("No existe reglas de configuracion para este cultivo");

					//Cuento si ya hay al menos un detalle para la cotizacion
					List<CotizacionDetalle> listadoDetalles=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);
					if(listadoDetalles.size()==0)
					{
						//Inserta el detalle de la cotizaci�n
						agriObjetoCotizacion.setCostoProduccion((float)costoProduccion);
						agriObjetoCotizacion.setCostoProduccionQBE((float)costoProduccion);
						agriObjetoCotizacion.setProvinciaId(new BigInteger(provinciaId));
						agriObjetoCotizacion.setCantonId(new BigInteger(cantonId));
						agriObjetoCotizacion.setAgriParroquiaId(parroquiaId);
						agriObjetoCotizacion.setTipoCultivoId(new BigInteger(tipoCultivoId));
						agriObjetoCotizacion.setVariedad(variedad);
						agriObjetoCotizacion.setAgricultorTecnificado(Boolean.parseBoolean(agricultorTecnificado));
						agriObjetoCotizacion.setDisponeRiego(Boolean.parseBoolean(disponeRiego));
						agriObjetoCotizacion.setDisponeAsistencia(Boolean.parseBoolean(disponeAsistencia));
						agriObjetoCotizacion.setAgricultorTecnificado(Boolean.parseBoolean(agricultorTecnificado));
						agriObjetoCotizacion.setDireccionSiembra(direccionSiembra);
						agriObjetoCotizacion.setConfirEmiCanal(true);
						agriObjetoCotizacion.setObjetoOfflineId(""+previaId1);
						if(!aniosCultivo.equals(""))
							agriObjetoCotizacion.setAniosCultivo(Integer.parseInt(aniosCultivo));
						if(!tipoSeguro.equals(""))
							agriObjetoCotizacion.setTipoSeguro(Integer.parseInt(tipoSeguro));
						else
							agriObjetoCotizacion.setTipoSeguro(0);
						if(!hectareasLote.equals(""))
							agriObjetoCotizacion.setHectareasLote(Float.parseFloat(hectareasLote));
						if(!latitud.equals("")){
							
							try{								
								agriObjetoCotizacion.setLatitud(Float.parseFloat(latitud));
								
							}catch(Exception e){
								System.out.println("Latitud vacia");
							}
						}
						if(!longitud.equals(""))
							try{								
								agriObjetoCotizacion.setLongitud(Float.parseFloat(longitud));
								
							}catch(Exception e){
								System.out.println("Longitud vacia");
							}
						if(!hectareasAsegurables.equals(""))
							agriObjetoCotizacion.setHectareasAsegurables(Float.parseFloat(hectareasAsegurables));
						if(!montoCredito.equals(""))
							try{
								agriObjetoCotizacion.setMontoCredito(Float.parseFloat(montoCredito));
							}catch(Exception e){
								System.out.println("Monto vacio");
							}
						//if(sucursalCanalId.length() > 0)
							//agriObjetoCotizacion.setSucursalCanalId(new BigInteger(sucursalCanalId));
						//else
							//throw new Exception("Seleccione una sucursal.");

						if(dateCredito!=null)
							agriObjetoCotizacion.setFechaCredito(dateCredito);
						if(dateSiembra!=null)
							agriObjetoCotizacion.setFechaSiembra(dateSiembra);
						else{
							observaciones=observaciones+" fecha de siembra invalida";
						}
							
						if(altitudNivelMar.length() > 0)
							agriObjetoCotizacion.setAltitudNivelMar(Float.parseFloat(altitudNivelMar));

						agriObjetoCotizacion.setTipoOrigen("PRONACA_CP");
						agriObjetoCotizacion.setCostoProduccion(Float.parseFloat(nuestroCosto));
						agriObjetoCotizacion.setCostoProduccionQBE(Float.parseFloat(nuestroCosto));
						agriObjetoCotizacion.setTipoCalculo(reglaId);
						agriObjetoCotizacion.setObservacion(observaciones+""+reglaDetalle);
						/***Proceso de creacion de las IdCotizaciones para facturacion***/
						
						AgriCotizacionMaxDAO busquedaMax = new AgriCotizacionMaxDAO();
						AgriCotizacionMax numMaximo=busquedaMax.buscarTodos();
						int numeroActual=numMaximo.getMaximo().intValue();						
						agriObjetoCotizacion.setIdCotizacion2(new BigInteger(""+(numeroActual+1)));
						agriObjetoCotizacion.setObservacionCotizacion(previaId);
						
						agriObjetoCotizacion=agriObjetoCotizacionTransaction.crear(agriObjetoCotizacion);

						//Creo la cotizaci�n detalle
						CotizacionDetalle nuevoCotizacionDetalle=new CotizacionDetalle();
						nuevoCotizacionDetalle.setCotizacion(cotizacion);
						nuevoCotizacionDetalle.setNecesitaInspeccion(false);
						nuevoCotizacionDetalle.setTipoObjetoId(tipoObjetoDAO.buscarPorNombre("Agricola").getId());
						nuevoCotizacionDetalle.setObjetoId(agriObjetoCotizacion.getObjetoCotizacionId().toString());
						nuevoCotizacionDetalle.setSumaAseguradaItem(sumaAseguradaTotal);
						nuevoCotizacionDetalle.setTasa(tasa);
						nuevoCotizacionDetalle.setPrimaNetaItem(primaNetaTotal);
						cotizacionDetalleTransaction.crear(nuevoCotizacionDetalle);
						
						result.put("cotizacionId",cotizacion.getId());
						result.put("objetoGanaderoId",agriObjetoCotizacion.getObjetoCotizacionId());
					}
					else
					{
						//Recupero el objeto detalle para actualizar
						CotizacionDetalle nuevoCotizacionDetalle=listadoDetalles.get(0);

						//AgriObjetoCotizacion agriObjetoCotizacion;

						//Valido si existe el objeto agricol para ese datalle, si existe lo recupero 
						//caso contrario me toca crearlo

						if(nuevoCotizacionDetalle.getObjetoId().equals(""))
							agriObjetoCotizacion=new AgriObjetoCotizacion();
						else
							agriObjetoCotizacion=agriObjetoDAO.buscarPorId(new BigInteger(nuevoCotizacionDetalle.getObjetoId()));

						//Inserta el detalle de la cotizaci�n
						agriObjetoCotizacion.setCostoProduccion((float)costoProduccion);
						agriObjetoCotizacion.setCostoProduccionQBE((float)costoProduccion);
						agriObjetoCotizacion.setProvinciaId(new BigInteger(provinciaId));
						agriObjetoCotizacion.setCantonId(new BigInteger(cantonId));
						agriObjetoCotizacion.setParroquiaId(new BigInteger(parroquiaId));
						agriObjetoCotizacion.setTipoCultivoId(new BigInteger(tipoCultivoId));
						agriObjetoCotizacion.setVariedad(variedad);
						agriObjetoCotizacion.setAgricultorTecnificado(Boolean.parseBoolean(agricultorTecnificado));
						agriObjetoCotizacion.setDisponeRiego(Boolean.parseBoolean(disponeRiego));
						agriObjetoCotizacion.setDisponeAsistencia(Boolean.parseBoolean(disponeAsistencia));
						agriObjetoCotizacion.setDireccionSiembra(direccionSiembra);
						agriObjetoCotizacion.setConfirEmiCanal(true);
						if(!aniosCultivo.equals(""))
							agriObjetoCotizacion.setAniosCultivo(Integer.parseInt(aniosCultivo));
						if(!tipoSeguro.equals(""))
							agriObjetoCotizacion.setTipoSeguro(Integer.parseInt(tipoSeguro));
						else
							agriObjetoCotizacion.setTipoSeguro(0);
						if(!hectareasLote.equals(""))
							agriObjetoCotizacion.setHectareasLote(Float.parseFloat(hectareasLote));
						if(!hectareasAsegurables.equals(""))
							agriObjetoCotizacion.setHectareasAsegurables(Float.parseFloat(hectareasAsegurables));
						if(!montoCredito.equals("")){
							try{
								agriObjetoCotizacion.setMontoCredito(Float.parseFloat(montoCredito));
							}catch (Exception e) {
								System.out.print("No hay monto de credito");
							}
						}
						if(!latitud.equals("")){
							try{
								agriObjetoCotizacion.setLatitud(Float.parseFloat(latitud));
							}catch (Exception e) {
								System.out.print("No hay latitud");
							}
						}
						if(!longitud.equals("")){
							try{
								agriObjetoCotizacion.setLongitud(Float.parseFloat(longitud));
							}catch (Exception e) {
								System.out.print("No hay longitud");
							}
						}
						if(dateCredito!=null)
						{
							try{
								agriObjetoCotizacion.setFechaCredito(dateCredito);
							}catch (Exception e) {
								System.out.print("No hay fechaCredito");
							}
						}
						if(fechaSiembra!=null)
						{
							agriObjetoCotizacion.setFechaSiembra(dateSiembra);
						}
						else{
							observaciones="No existe fecha de siembra";
						}
							
						agriObjetoCotizacion.setAltitudNivelMar(Float.parseFloat(altitudNivelMar));
						
						agriObjetoCotizacion.setTipoOrigen("PRONACA_CP");
						
						agriObjetoCotizacion.setTipoCalculo(reglaId);
						agriObjetoCotizacion.setObservacion(observaciones+reglaDetalle);
						
						//Cambio los valores de la poliza
						nuevoCotizacionDetalle.setSumaAseguradaItem(sumaAseguradaTotal);
						nuevoCotizacionDetalle.setPrimaNetaItem(primaNetaTotal);
						nuevoCotizacionDetalle.setTasa(tasa);
						cotizacionDetalleTransaction.editar(nuevoCotizacionDetalle);


						result.put("cotizacionId",cotizacion.getId());
						result.put("objetoGanaderoId",agriObjetoCotizacion.getObjetoCotizacionId());
					}
					Estado estado = new Estado();
					EstadoDAO estadoDAO = new EstadoDAO();
					
					if(sumaAseguradaTotal <= 0){
						observaciones= observaciones+" La suma asegurado es 0.";
					}
					
					/*if(esEmitido==false)
						estado = estadoDAO.buscarPorNombreClase("Pendiente Aprobar", "Cotizacion");
					else*/
					estado = estadoDAO.buscarPorNombreClase("Borrador", "Cotizacion");
					//Las cotizaciones del canal pronaca no pasan por la etapa de carga de veneficiario o asegurado
					//en este caso el cliente es el asegurado					
					Cliente cliente = new Cliente();
					cliente = clienteDAO.buscarPorId(cotizacion.getClienteId().toString());
					cotizacion.setAsegurado(cliente.getEntidad());
					//objetoCotizacion = cotizacionDAO.editar(objetoCotizacion);
					
					cotizacion.setEstado(estado);
					cotizacion.setNumeroTramite(cotizacion.getId());
					cotizacion.setSumaAseguradaTotal(sumaAseguradaTotal);
					cotizacion.setPrimaNetaTotal(primaNetaTotal.toString());
					cotizacion.setPrimaOrigen(primaNetaTotal);
					cotizacion.setTasaProductoValor(Double.parseDouble(""+tasa));
					cotizacion.setTasaMinima(tasa);
					cotizacion.setEtapaWizard(2);
					agriObjetoCotizacion=agriObjetoCotizacionTransaction.editar(agriObjetoCotizacion);
					cotizacionTransaction.editar(cotizacion);
				}
			}
			
			/*COTIZACIONES ARCHIVO PLANO CARGA PREVIA*/

			if(tipoConsulta.equalsIgnoreCase("obtenerValoresCotizacion"))
			{
				VariableSistemaDAO variableDAO = new VariableSistemaDAO();

				Cotizacion cotizacion = cotizacionDAO.buscarPorId(request.getParameter("cotizacionId"));
				CotizacionDetalle cotizacionesDetalle = cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).get(0);								
				AgriObjetoCotizacionDAO agriObjetoCotizacionDAO = new AgriObjetoCotizacionDAO();
				AgriObjetoCotizacion agriObjetoCotizacion = agriObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizacionesDetalle.getObjetoId()));
				
				Provincia provincia = new Provincia();
				ProvinciaDAO  provinciaDAO= new ProvinciaDAO();
				provincia=provinciaDAO.buscarPorId(""+agriObjetoCotizacion.getProvinciaId());
				
				
				double valorFinalPrima = cotizacion.getPrimaOrigen();
				
				
				result.put("valorAsegurado", cotizacion.getSumaAseguradaTotal());
				result.put("porcentajeDescuento", 0.0); 
				/*PROCESO DE CALCULO DE COMPONENTES*/
				TipoVariableDAO tipoVariableDao = new TipoVariableDAO();
		        TipoVariable tipoVariable = tipoVariableDao.buscarPorId("3");
		        variableDAO= new VariableSistemaDAO();
		        List<VariableSistema> variablesistema = variableDAO.buscarTipoVariable(tipoVariable);
		        double valorBase = 0;
		        double valorDerechosEmision = 0;
		        double valorSeguroCampesino = 0;
		        double valorSuperBancos = 0;
		        double valorIva= 0;
		        double valorSubTotal = 0;
		        double valorTotal=0;
		        if(variablesistema.size() > 0) {
		        	for(VariableSistema variable : variablesistema) {
		        		if(variable.getNombre().equals("DERECHOS_EMISION_AGRICOLA")){
		        			 valorDerechosEmision = Double.parseDouble(variable.getValor());
		                     cotizacion.setImpDerechoEmision(valorDerechosEmision);
		                     valorBase = Double.parseDouble(variable.getValor())+valorBase;
		        		}
		        		else if(variable.getNombre().equals("SEGURO_CAMPESINO")){
		        			valorSeguroCampesino = Math.rint(Double.parseDouble(variable.getValor())*valorFinalPrima/100*100)/100;
		                    valorBase = valorBase + valorSeguroCampesino;
		                    cotizacion.setImpSeguroCampesino(valorSeguroCampesino);
		        		}
		        		else if(variable.getNombre().equals("SUPER_DE_BANCOS")){
		        			valorSuperBancos = Math.rint(Double.parseDouble(variable.getValor())*valorFinalPrima/100*100)/100;
		                    cotizacion.setImpSuperBancos(valorSuperBancos);
		                    valorBase = valorBase + valorSuperBancos;
		        		}
		        		else if(variable.getNombre().equals("SUBTOTAL")){
		                    valorSubTotal = Math.rint(valorBase*100)/100;
		                }		               
		        	}
		        	
		        	VariableSistema variableIva= variableDAO.buscarPorNombre("IVA");					
					valorSubTotal=valorBase+valorFinalPrima;
					//calculamos el Iva
					String PorcentajeIva="";
					//exceocion temporal Manabi y esmeraldas
					if(provincia.getNombre().trim().toUpperCase().equals("MANABI")||provincia.getNombre().trim().toUpperCase().equals("ESMERALDAS")){
						 PorcentajeIva="12";
                	}else{
                		 PorcentajeIva=variableIva.getValor();
                	}	
					
					valorIva = Math.rint(Double.parseDouble(PorcentajeIva) * valorSubTotal / 100 * 100) / 100;
					BigDecimal a = new BigDecimal(""+valorIva);
					BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
					cotizacion.setImpIva(Double.parseDouble(""+roundOff));
					//calculamos el valor total
					double valorFactura=valorSubTotal+valorIva;
					a = new BigDecimal(""+valorFactura);
					roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
					cotizacion.setTotalFactura(Double.parseDouble(""+roundOff));					
		        }
		        AgriParametroXPuntoVenta agriParametroXPuntoVenta= new AgriParametroXPuntoVenta();
		        AgriParametroXPuntoVentaDAO agriParametroXPuntoVentaDAO = new AgriParametroXPuntoVentaDAO();
		        agriParametroXPuntoVenta=agriParametroXPuntoVentaDAO.buscarPorPuntoVentaId(new BigInteger(cotizacion.getPuntoVenta().getId()));
		        
		        cotizacion.setAgenteId(new BigInteger(agriParametroXPuntoVenta.getAgenteId()));
	        	cotizacion.setPorcentajeComision(Double.parseDouble(agriParametroXPuntoVenta.getPorcentajeComision()));
	        	
	        	Estado estado = new Estado();
	        	EstadoDAO estadoDAO= new EstadoDAO();
	        	estado=estadoDAO.buscarPorNombre("Borrador", "Cotizacion");
	        	
	        	cotizacion.setEstado(estado);
	        	cotizacionTransaction.editar(cotizacion);
	        	result.put("valorTotal", cotizacion.getTotalFactura());
	        	result.put("valorSuperBancos", cotizacion.getImpSuperBancos());
	        	result.put("valorSeguroCampesino", cotizacion.getImpSeguroCampesino());
	        	result.put("valorDerechosEmision", cotizacion.getImpDerechoEmision());
	        	BigDecimal a = new BigDecimal(""+(cotizacion.getImpDerechoEmision()+cotizacion.getImpSeguroCampesino()+cotizacion.getImpSuperBancos()+(Double.parseDouble(cotizacion.getPrimaNetaTotal()))));
	        	BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
	        	result.put("valorSubTotal", ""+roundOff);
	        	a = new BigDecimal(""+valorFinalPrima);
	        	roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
    			result.put("valorPrima", roundOff);
	        	result.put("valorIva", cotizacion.getImpIva());
				
			}
			
			if(tipoConsulta.equalsIgnoreCase("registrarInspeccion"))
			{
				EstadoDAO estadoDAO=new EstadoDAO();
				Cotizacion cotizacion = cotizacionDAO.buscarPorId(request.getParameter("cotizacionId"));
				//verifico si todas las direcciones estan aprobadas
				List<CotizacionDetalle> listadoDetalles=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);
				cotizacion.setEstado(estadoDAO.buscarPorId(request.getParameter("estadoInspeccion")));
				cotizacion.setEtapaWizard(3);
				Date fechaActual=new Date();
				cotizacion.setVigenciaDesde(fechaActual);
				cotizacionTransaction.editar(cotizacion);
			}
	
			if(tipoConsulta.equalsIgnoreCase("emitirPoliza"))
			{
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();
				
				Cotizacion cotizacionC = new Cotizacion();
				CotizacionDAO cotizacionCDAO = new CotizacionDAO();
				cotizacionC = cotizacionCDAO.buscarPorId(cotizacionId);
				cotizacionC=cotizacionTransaction.editar(cotizacionC);
						
				
				AgriResultadoEmision resultado=AgriEmisionPoliza.emitirPoliza(cotizacionId);
				if(resultado.isEmitidoCorrectamente()){
					cotizacionC = cotizacionCDAO.buscarPorId(cotizacionC.getId());
					result.put("NroFactura", resultado.getFactura());
					result.put("success", Boolean.TRUE);
					result.put("cotizacionId",cotizacionId);
				}
				else{
					result.put("success", Boolean.FALSE);
					result.put("mensajeEmision", resultado.getMensaje());
					response.setContentType("application/json; charset=ISO-8859-1");
					result.put("cotizacionId",cotizacionId);
					return;
				}
			}
			
			if(tipoConsulta.equalsIgnoreCase("contarImpresion"))
			{
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();
				
				Cotizacion cotizacion = cotizacionDAO.buscarPorId(request.getParameter("cotizacionId"));
				
				List<CotizacionDetalle> listadoDetalles=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);				
				AgriObjetoCotizacion agriObjetoCotizacion = agriObjetoDAO.buscarPorId(new BigInteger(listadoDetalles.get(0).getObjetoId()));
				agriObjetoCotizacion.setNumeroImpresion(agriObjetoCotizacion.getNumeroImpresion()+1);
				agriObjetoCotizacionTransaction.editar(agriObjetoCotizacion);
			}
			
			if(tipoConsulta.equals("crearPronaca")){
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();
				String provinciaId = request.getParameter("provinciaId") == null ? "" : request.getParameter("provinciaId").trim();
				String cantonId = request.getParameter("cantonId") == null ? "" : request.getParameter("cantonId").trim();
				String parroquiaId = request.getParameter("parroquiaId") == null ? "" : request.getParameter("parroquiaId").trim();
				String direccionSiembra = request.getParameter("direccionSiembra") == null ? "" : request.getParameter("direccionSiembra").trim();
				String fechaCredito = request.getParameter("fechaCredito") == null ? "" : request.getParameter("fechaCredito").trim();
				String montoCredito = request.getParameter("montoCredito") == null ? "" : request.getParameter("montoCredito").trim();
				String tipoCultivoId = request.getParameter("tipoCultivoId") == null ? "" : request.getParameter("tipoCultivoId").trim();
				String fechaSiembra = request.getParameter("fechaSiembra") == null ? "" : request.getParameter("fechaSiembra").trim();
				String hectareasLote = request.getParameter("hectareasLote") == null ? "" : request.getParameter("hectareasLote").trim();
				String hectareasAsegurables = request.getParameter("hectareasAsegurables") == null ? "" : request.getParameter("hectareasAsegurables").trim();
				String agricultorTecnificado = request.getParameter("agricultorTecnificado") == null ? "" : request.getParameter("agricultorTecnificado").trim();
				String latitud = request.getParameter("latitud") == null ? "" : request.getParameter("latitud").trim();
				String longitud = request.getParameter("longitud") == null ? "" : request.getParameter("longitud").trim();
				String disponeRiego = request.getParameter("disponeRiego") == null ? "" : request.getParameter("disponeRiego").trim();
				String disponeAsistencia = request.getParameter("disponeAsistencia") == null ? "" : request.getParameter("disponeAsistencia").trim();
				
				//primero hallo la cotizacion
				Cotizacion cotizacion =cotizacionDAO.buscarPorId(cotizacionId);
				CotizacionDetalle cotizacionDetalle = new CotizacionDetalle();
						
				
				
			}
			
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());

		}catch(Exception e){
			String error = "Se ha producido un error: "+e.getLocalizedMessage();
			result.put("success", Boolean.FALSE);
			result.put("error", error);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			e.printStackTrace();
		}
	}

	
}
