package com.qbe.cotizador.servlets.producto.pymes;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.cotizacion.SolicitudDescuentoDAO;
import com.qbe.cotizador.dao.cotizacion.TipoObjetoDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.SucursalDAO;
import com.qbe.cotizador.dao.producto.vehiculo.ObjetoVehiculoDAO;
import com.qbe.cotizador.dao.seguridad.TipoVariableDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cobertura;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionCobertura;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.ObjetoVehiculo;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.SolicitudDescuento;
import com.qbe.cotizador.model.Sucursal;
import com.qbe.cotizador.model.TipoObjeto;
import com.qbe.cotizador.model.TipoVariable;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.servlets.cotizacion.CoberturaComparator;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;

/**
 * Servlet implementation class ObjetoPymesController
 */
@WebServlet("/ObjetoPymesController")
public class ObjetoPymesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObjetoPymesController() {
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

		CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
		
		JSONObject result = new JSONObject();
		try{
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String producto = request.getParameter("producto") == null ? "" : request.getParameter("producto");		
			JSONObject vehiculoJSONObject = new JSONObject();
			JSONArray vehiculoJSONArray = new JSONArray();
			JSONArray coberturasTextosJSONArray = new JSONArray();
			JSONObject coberturaTextosJSONObject = new JSONObject();
			Cotizacion cotizacion = new Cotizacion();
			CotizacionDAO cotizacionDAO = new CotizacionDAO();
			
			// Metodo obtener todos vehiculos de una cotizacion ayanez
			if(tipoConsulta.equalsIgnoreCase("obtenerObjetos"))
			{			
				cotizacion = cotizacionDAO.buscarPorId(request.getParameter("cotizacionId"));
				ClienteDAO clienteDAO = new ClienteDAO();
				Cliente cliente = new Cliente();
				TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();				
				TipoObjeto tipoObjeto = tipoObjetoDAO.buscarPorNombre("Vehiculos");
				
				if(cotizacion.getClienteId() != null)
					cliente = clienteDAO.buscarPorId(cotizacion.getClienteId().toString());
								
				CotizacionDetalleDAO cotizacionDetalleDAO= new CotizacionDetalleDAO();		
				List<CotizacionDetalle> cotizacionesDetalle = cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);
								
				ObjetoVehiculoDAO objetoVehiculoDAO = new ObjetoVehiculoDAO();
				List<ObjetoVehiculo> listadoVehiculos = objetoVehiculoDAO.buscarObjetoVehiculoPorCotizacionDetalle(cotizacionesDetalle);
				ObjetoVehiculo objetoVehiculo = new ObjetoVehiculo();
				SolicitudDescuentoDAO solicitudDescuentoDAO = new SolicitudDescuentoDAO();
				EstadoDAO estadoDAO = new EstadoDAO();
				Estado estado = estadoDAO.buscarPorId("7");
				SolicitudDescuento solicitudDescuento = solicitudDescuentoDAO.buscarPorCotizacion(cotizacion,estado);
				int i = 0;
				if(listadoVehiculos.size() > 0) {
					Double sumaAseguradaTotal = 0.0;
					Double sumaPrimaNetaTotal = 0.0;
					for(i=0; i<listadoVehiculos.size(); i++) {
						objetoVehiculo = (ObjetoVehiculo) listadoVehiculos.get(i);		
						vehiculoJSONObject.put("codigo_vehiculo", objetoVehiculo.getId());
						vehiculoJSONObject.put("marca_modelo", objetoVehiculo.getModelo().getMarca().getNombre() +" :: "+ objetoVehiculo.getModelo().getNombre());
						vehiculoJSONObject.put("ano_fabricacion", objetoVehiculo.getAnioFabricacion());
						
						SucursalDAO sucursalDAO = new SucursalDAO ();
						Sucursal sucursal =sucursalDAO.buscarPorId(objetoVehiculo.getSucursalId());
						vehiculoJSONObject.put("sucursal", sucursal.getNombre());
						
						CotizacionDetalle cotizacionDetalle = cotizacionDetalleDAO.buscarPorId(cotizacionesDetalle.get(i).getId());
						
						sumaAseguradaTotal += cotizacionDetalle.getSumaAseguradaItem();
						sumaPrimaNetaTotal += cotizacionDetalle.getPrimaNetaItem();
						
						vehiculoJSONObject.put("suma_asegurada", cotizacionDetalle.getSumaAseguradaItem());
						vehiculoJSONObject.put("prima_vehiculo", cotizacionDetalle.getPrimaNetaItem());
						String valorRastreo = "No";
						
						if (objetoVehiculo.getDispositivoRastreo())
							valorRastreo = "Si";							
						vehiculoJSONObject.put("dispositivo_rastreo", valorRastreo);
						
						
						vehiculoJSONArray.add(vehiculoJSONObject);
					}
					cotizacion.setSumaAseguradaTotal(sumaAseguradaTotal);
					cotizacion.setPrimaNetaTotal(sumaPrimaNetaTotal.toString());
					cotizacionTransaction.editar(cotizacion);
				}
				
				result.put("vehiculosCotizacion", vehiculoJSONArray);
				result.put("numeroVehiculos", i);
				CotizacionDetalle cotizacionDetalle = new CotizacionDetalle();
				VariableSistema variable = new VariableSistema();  
				VariableSistemaDAO variableDAO = new VariableSistemaDAO(); 
				double valorPrima = 0;
				double valorAsegurado = 0;
				double valorPrimaDescuento = 0;
				double valorFinalPrima = 0;
				if(cotizacionesDetalle.size() > 0) {
					for(i=0; i<cotizacionesDetalle.size(); i++) {
						cotizacionDetalle = (CotizacionDetalle) cotizacionesDetalle.get(i);					
						valorPrima = valorPrima + cotizacionDetalle.getPrimaNetaItem();
						valorAsegurado = valorAsegurado+ cotizacionDetalle.getSumaAseguradaItem();
						valorPrima = Math.rint(valorPrima*100)/100;
						valorAsegurado = Math.rint(valorAsegurado*100)/100;
					}
					valorFinalPrima = valorPrima;
					if(solicitudDescuento != null){
						if(solicitudDescuento.getEstado().getId().equals("7")){	
							valorPrimaDescuento = Math.rint((Double.parseDouble(solicitudDescuento.getPorcentaje())*valorFinalPrima)/100*100)/100;
						    valorPrimaDescuento = valorFinalPrima - valorPrimaDescuento;
							valorFinalPrima = valorPrimaDescuento;
							result.put("porcentajeDescuento", solicitudDescuento.getPorcentaje());
						}	
					}else{
						result.put("porcentajeDescuento", 0.0);  
					}
					TipoVariableDAO tipoVariableDao = new TipoVariableDAO();
					TipoVariable tipoVariable = tipoVariableDao.buscarPorId("3");
					List<VariableSistema> variablesistema = variableDAO.buscarTipoVariable(tipoVariable);
					double valorBase = 0;
					double valorDerechosEmision = 0;
					double valorSeguroCampesino = 0;
					double valorSuperBancos = 0;
					double valorIva= 0;
					double valorSubTotal = 0;
					double valorTotal = 0;
					result.put("valorPrima", valorFinalPrima);
					result.put("valorAsegurado", valorAsegurado);
					if(variablesistema.size() > 0) {
						for(i=0; i<variablesistema.size(); i++) {
							variable = (VariableSistema) variablesistema.get(i);
							if(variable.getNombre().equals("DERECHOS_EMISION")){
							   valorBase = Double.parseDouble(variable.getValor())+ valorFinalPrima;
							   valorDerechosEmision = Double.parseDouble(variable.getValor());
							   result.put("valorDerechosEmision", valorDerechosEmision);
							   
							}
							else if(variable.getNombre().equals("SEGURO_CAMPESINO")){
								valorSeguroCampesino = Math.rint(Double.parseDouble(variable.getValor())*valorFinalPrima/100*100)/100;
								valorBase = valorBase + valorSeguroCampesino;
								result.put("valorSeguroCampesino", valorSeguroCampesino);
							}
							else if(variable.getNombre().equals("SUPER_DE_BANCOS")){
								valorSuperBancos = Math.rint(Double.parseDouble(variable.getValor())*valorFinalPrima/100*100)/100;
								result.put("valorSuperBancos", valorSuperBancos);
								valorBase = valorBase + valorSuperBancos;
								
							}
							
							else if(variable.getNombre().equals("SUBTOTAL")){
								valorSubTotal = Math.rint(valorBase*100)/100;
								result.put("valorSubTotal", valorSubTotal);
								
								
							}
							else if(variable.getNombre().equals("IVA")){
								double respuesta [] = {0,0};					
								double compensacion =2.0;
								//double valorCompensacion =0.0;
								double valorIVATotal = 0.0;
								valorIva = Math.rint((Double.parseDouble(variable.getValor())*valorSubTotal/100*100))/100;
								respuesta = cotizacionDAO.buscarIvaSegunPuntoVenta(cotizacion);
								DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
							    Date fechaNuevoIVA = (Date) formatoFecha.parse("01/06/2016");				   					
								if(cotizacion.getFechaElaboracion().before(new Timestamp(fechaNuevoIVA.getTime()))){
									valorIVATotal = cotizacion.getImpIva();
								}else{
									if(respuesta[0] == 12.00){						
										//valorCompensacion = (valorIva*compensacion)/14;
										valorIVATotal = (valorIva*12)/14; 
									}else{
										valorIVATotal = cotizacion.getImpIva();
									}	
								}
								valorIva=valorIVATotal;
								result.put("valorIva", valorIva);
							}
							
						}
						valorTotal = Math.rint((valorBase+valorIva)*100)/100;
						result.put("valorTotal", valorTotal);
						
					}
					
				}
				
				// Obtenemos los textos de las coberturas y presentar en orden 
				for(CotizacionDetalle cotizacionDetalleItem:cotizacionesDetalle){
					List<CotizacionCobertura> cotizacionCoberturas = cotizacionDetalleItem.getCotizacionCoberturas();
					List<Cobertura> coberturas = new ArrayList<Cobertura>();
					
					// Obtenemos las coberturas de las cotizaciones coberturas
					for(CotizacionCobertura cotizacionCobertura:cotizacionCoberturas){
						Cobertura cobertura = cotizacionCobertura.getCobertura();
						coberturas.add(cobertura);
					}
					// Ordenamiento de los las coberturas por medio de las secciones y el orden
					Collections.sort(coberturas, new CoberturaComparator());
					
					// Recorre las coberturas y se les agrega dentro de un objeto json y se graba en un jsonarray
					for(Cobertura cobertura:coberturas){						
						coberturaTextosJSONObject.put("tipo_cobertura_nombre", cobertura.getTipoCobertura().getNombre());						
						coberturaTextosJSONObject.put("texto", cobertura.getNombre());
						coberturasTextosJSONArray.add(coberturaTextosJSONObject);
					}
				}
				// Enviamos los textos ordenamos de las coberturas
				result.put("textosCoberturas", coberturasTextosJSONArray);
			}
			
			
		
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			
		}catch(Exception e){
			/***TRATAMIENTO DE ERROR***/
			Date codigo = new Date();//EL codigo de error se conforma por los valores de la fecha
			String CodError=codigo.getYear()+""+codigo.getMonth()+""+codigo.getDay()+""+codigo.getHours()+""+codigo.getMinutes()+"-"+codigo.getSeconds();
			/***AUDITORIA auditamos el error para el seguimiento***/
			PymeAuditoriaGeneralTransaction pymeAuditoriaGeneralTransaction = new PymeAuditoriaGeneralTransaction();
			PymeAuditoriaGeneral pymeAuditoriaGeneral = new PymeAuditoriaGeneral();
			pymeAuditoriaGeneral.setTramite(CodError);
			pymeAuditoriaGeneral.setEstado("ERROR_PYMES");
			pymeAuditoriaGeneral.setProceso("PYMES");
			pymeAuditoriaGeneral.setObjeto(e.getMessage()+"||"+e.getCause());
			try {
				pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
			} catch (Exception e1) {				
				e1.printStackTrace();
			}
			/***RESPUESTA A INTERFAZ***/
			result.put("success", Boolean.FALSE);
			result.put("error", "Error en Servidor, refiérase para soporte con el siguiente código: "+CodError);
			result.put("ex", e.getMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			e.printStackTrace();
		}			
	}

	
}
