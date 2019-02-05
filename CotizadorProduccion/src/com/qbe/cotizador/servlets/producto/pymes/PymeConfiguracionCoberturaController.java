package com.qbe.cotizador.servlets.producto.pymes;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.qbe.cotizador.dao.cotizacion.GrupoCoberturaDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoDAO;
import com.qbe.cotizador.dao.cotizacion.TipoDeducibleDAO;
import com.qbe.cotizador.dao.entidad.ConfiguracionProductoDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeCoberturaConfiguradaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeCoberturaCotizacionValorDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeCoberturaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeCoberturaTasaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeCoberturasConfiguracionDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeConfiguracionCoberturaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeGrupoproductoProductoDAO;
import com.qbe.cotizador.dao.producto.pymes.PymePlanConfiguracionDAO;
import com.qbe.cotizador.dao.producto.pymes.PymePlanDAO;
import com.qbe.cotizador.dao.producto.pymes.PymePlanesPorCoberturasDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeTasaRiesgoDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeTipoDeducibleCoberturaDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.model.ConfiguracionProducto;
import com.qbe.cotizador.model.GrupoCobertura;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.Producto;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.PymeCobertura;
import com.qbe.cotizador.model.PymeCoberturaConfigurada;
import com.qbe.cotizador.model.PymeCoberturaCotizacionValor;
import com.qbe.cotizador.model.PymeCoberturaTasa;
import com.qbe.cotizador.model.PymeCoberturasConfiguracion;
import com.qbe.cotizador.model.PymeConfiguracionCobertura;
import com.qbe.cotizador.model.PymeConfiguracionLog;
import com.qbe.cotizador.model.PymeGrupoproductoProducto;
import com.qbe.cotizador.model.PymePlan;
import com.qbe.cotizador.model.PymePlanConfiguracion;
import com.qbe.cotizador.model.PymePlanesPorCoberturas;
import com.qbe.cotizador.model.PymeTasaRiesgo;
import com.qbe.cotizador.model.PymeTipoDeducibleCobertura;
import com.qbe.cotizador.model.TipoDeducible;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeCoberturaTasaTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeConfiguracionCoberturaTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymePlanConfiguracionTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeConfiguracionLogTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeTasaRiesgoTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeTipoDeducibleCoberturaTransaction;


/**
 * Servlet implementation class PymeConfiguracionCoberturaController
 */
@WebServlet("/PymeConfiguracionCoberturaController")
public class PymeConfiguracionCoberturaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PymeConfiguracionCoberturaController() {
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
		String observacion="";
		try{
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String configuracionCoberturaId = request.getParameter("configuracionCoberturaId") == null ? "" : request.getParameter("configuracionCoberturaId");
			String coberturaPymesId = request.getParameter("coberturaPymesId") == null ? "" : request.getParameter("coberturaPymesId");
			String grupoPorProductoId = request.getParameter("grupoPorProductoId") == null ? "" : request.getParameter("grupoPorProductoId");
			String tipoDeclaracion = request.getParameter("tipoDeclaracion") == null ? "" : request.getParameter("tipoDeclaracion");
			String tipoVariable = request.getParameter("tipoVariable") == null ? "" : request.getParameter("tipoVariable");
			String origenValorLimiteAsegurado = request.getParameter("origenValorLimiteAsegurado") == null ? "" : request.getParameter("origenValorLimiteAsegurado");
			String porcentajeLimiteAsegurado = request.getParameter("porcentajeLimiteAsegurado") == null ? "" : request.getParameter("porcentajeLimiteAsegurado");
			String valorMaximoLimiteAsegurado = request.getParameter("valorMaximoLimiteAsegurado") == null ? "" : request.getParameter("valorMaximoLimiteAsegurado");
			String origenValorLimiteCobertura = request.getParameter("origenValorLimiteCobertura") == null ? "" : request.getParameter("origenValorLimiteCobertura");
			String porcentajeLimiteCobertura = request.getParameter("porcentajeLimiteCobertura") == null ? "" : request.getParameter("porcentajeLimiteCobertura");
			String valorMaximoLimiteCobertura = request.getParameter("valorMaximoLimiteCobertura") == null ? "" : request.getParameter("valorMaximoLimiteCobertura");
			String coberturaEnsMultiId = request.getParameter("coberturaEnsMultiId") == null ? "" : request.getParameter("coberturaEnsMultiId");
			String coberturaEnsProgrId = request.getParameter("coberturaEnsProgrId") == null ? "" : request.getParameter("coberturaEnsProgrId");
			String usaValorAdicional = request.getParameter("usaValorAdicional") == null ? "" : request.getParameter("usaValorAdicional");
			String valorMaximo2 = request.getParameter("valorMaximo2") == null ? "" : request.getParameter("valorMaximo2");
			String tipoDeclaracionId = request.getParameter("tipoDeclaracionId") == null ? "" : request.getParameter("tipoDeclaracionId");

			//String textoDeducible = request.getParameter("textoDeducible") == null ? "" : request.getParameter("textoDeducible");
			String tipoTasa = request.getParameter("tipoTasa") == null ? "" : request.getParameter("tipoTasa");
			String tasa = request.getParameter("tasa") == null ? "" : request.getParameter("tasa");			
			String coberturaCopiaId = request.getParameter("coberturaCopiaId") == null ? "" : request.getParameter("coberturaCopiaId");
			String incluyeEnProducto = request.getParameter("incluyeEnProducto") == null ? "" : request.getParameter("incluyeEnProducto");
			String ordenPresentacion = request.getParameter("ordenPresentacion") == null ? "" : request.getParameter("ordenPresentacion");
			String dependeValor = request.getParameter("dependeValor") == null ? "" : request.getParameter("dependeValor");
			String listaValorMinimo = request.getParameter("listaValorMinimo") == null ? "" : request.getParameter("listaValorMinimo");
			String listaValorMaximo = request.getParameter("listaValorMaximo") == null ? "" : request.getParameter("listaValorMaximo");
			String listaTasa = request.getParameter("listaTasa") == null ? "" : request.getParameter("listaTasa");
			String tipoCofiguracion = request.getParameter("tipoCofiguracion") == null ? "" : request.getParameter("tipoCofiguracion");
			String tasaEstructuras = request.getParameter("tasaEstructuras") == null ? "" : request.getParameter("tasaEstructuras");
			String listaTasasRiesgosDesde=request.getParameter("listaTasasRiesgosDesde") == null ? "" : request.getParameter("listaTasasRiesgosDesde");
			String listaTasasRiesgosHasta=request.getParameter("listaTasasRiesgosHasta") == null ? "" : request.getParameter("listaTasasRiesgosHasta");
			String listaTasasRiesgosTasas=request.getParameter("listaTasasRiesgosTasas") == null ? "" : request.getParameter("listaTasasRiesgosTasas");
			String listaTasasReclamos=request.getParameter("listaTasasReclamos") == null ? "" : request.getParameter("listaTasasReclamos");
			String listaTasasReclamosTasas=request.getParameter("listaTasasReclamosTasas") == null ? "" : request.getParameter("listaTasasReclamosTasas");
			
			String arrTextoDeducible = request.getParameter("arrTextoDeducible") == null ? "" : request.getParameter("arrTextoDeducible");
			String arrPlanes = request.getParameter("arrPlanes") == null ? "" : request.getParameter("arrPlanes");

			String grupoPorProductoDestinoId = request.getParameter("grupoPorProductoDestinoId") == null ? "" : request.getParameter("grupoPorProductoDestinoId");

			JSONObject configuracionJSONObject = new JSONObject();
			JSONArray configuracionJSONArray = new JSONArray();

			JSONObject planeEstructurasJSONObject = new JSONObject();
			JSONArray planeEstructurasJSONArray = new JSONArray();
			
			JSONObject planeContenidosJSONObject = new JSONObject();
			JSONArray planeContenidosJSONArray = new JSONArray();
			
			JSONObject planeContenedidosJSONObject = new JSONObject();
			JSONArray planeContenedidosJSONArray = new JSONArray();
			
			JSONObject planeGeneralesJSONObject = new JSONObject();
			JSONArray planeGeneralesJSONArray = new JSONArray();
			
			JSONObject tipoDeducibleJSONObject = new JSONObject();
			JSONArray tipoDeducibleJSONArray = new JSONArray();
			
			JSONObject planJSONObject = new JSONObject();
			JSONArray planJSONArray = new JSONArray();
			
			TipoDeducibleDAO tipoDeducibleDAO = new TipoDeducibleDAO();
			PymeTipoDeducibleCoberturaDAO pymeTipoDeducibleDAO = new PymeTipoDeducibleCoberturaDAO(); 
			PymePlanDAO pymePlanDAO=new PymePlanDAO(); 
			PymePlanesPorCoberturas pymePlanesPorCoberturas = new PymePlanesPorCoberturas();
			PymePlanesPorCoberturasDAO pymePlanesPorCoberturasDAO = new PymePlanesPorCoberturasDAO();
			PymePlanConfiguracionDAO pymePlanConfiguracionDAO=new PymePlanConfiguracionDAO();
			
			PymeConfiguracionCoberturaDAO configuracionCoberturaDAO = new PymeConfiguracionCoberturaDAO();
			PymeCoberturaConfiguradaDAO coberturaConfiguradaDAO = new PymeCoberturaConfiguradaDAO();
			PymeCoberturaTasaDAO coberturaTasaDAO=new PymeCoberturaTasaDAO();
			
			PymeConfiguracionCoberturaTransaction pymeConfiguracionCoberturaTransaction=new PymeConfiguracionCoberturaTransaction();
			PymeCoberturaTasaTransaction pymeCoberturaTasaTransaction=new PymeCoberturaTasaTransaction(); 
			PymeTasaRiesgoTransaction pymeTasaRiesgoTransaction= new PymeTasaRiesgoTransaction();
			
			PymeTipoDeducibleCoberturaTransaction pymeTipoDeducibleCoberturaTransaction = new PymeTipoDeducibleCoberturaTransaction();
			
			PymePlanConfiguracionTransaction pymePlanConfiguracionTransaction=new PymePlanConfiguracionTransaction();

			PymeConfiguracionCobertura configuracionCobertura = new PymeConfiguracionCobertura();
 			if(!configuracionCoberturaId.equals(""))
 				configuracionCobertura.setConfiguracionCoberturaId(new BigInteger(configuracionCoberturaId)); 	
 			
 			if(coberturaPymesId != null && !coberturaPymesId.equals(""))
 				configuracionCobertura.setCoberturaPymesId(new BigInteger(coberturaPymesId)); 			
 			
 			if(grupoPorProductoId != null && !grupoPorProductoId.equals(""))
 				configuracionCobertura.setGrupoPorProductoId(new BigInteger(grupoPorProductoId));
 			
 			if(tipoDeclaracion != null && !tipoDeclaracion.equals(""))
 				configuracionCobertura.setTipoDeclaracion(Integer.parseInt(tipoDeclaracion));
 			
 			if(tipoCofiguracion.equals("1")){
 				if(origenValorLimiteAsegurado != null && !origenValorLimiteAsegurado.equals(""))
 					configuracionCobertura.setOrigenValorLimiteAsegurado(Integer.parseInt(origenValorLimiteAsegurado));
 			}
 			else{
 				configuracionCobertura.setOrigenValorLimiteAsegurado(9);
 			}
 				
 			if(tipoCofiguracion.equals("1")){
	 			if(porcentajeLimiteAsegurado != null && !porcentajeLimiteAsegurado.equals(""))
	 				configuracionCobertura.setPorcentajeLimiteAsegurado(Double.parseDouble(porcentajeLimiteAsegurado));
 			}
 			else
 				configuracionCobertura.setPorcentajeLimiteAsegurado(0);
 			
 			if(tipoCofiguracion.equals("1")){
 				if(valorMaximoLimiteAsegurado != null && !valorMaximoLimiteAsegurado.equals(""))
 					configuracionCobertura.setValorMaximoLimiteAsegurado(Double.parseDouble(valorMaximoLimiteAsegurado));
 			}
 			else
 				configuracionCobertura.setValorMaximoLimiteAsegurado(0);
 			
 			if(origenValorLimiteCobertura != null && !origenValorLimiteCobertura.equals(""))
 				configuracionCobertura.setOrigenValorLimiteCobertura(Integer.parseInt(origenValorLimiteCobertura));
 			
 			if(porcentajeLimiteCobertura != null && !porcentajeLimiteCobertura.equals(""))
 				configuracionCobertura.setPorcentajeLimiteCobertura(Double.parseDouble(porcentajeLimiteCobertura));
 			
 			if(valorMaximoLimiteCobertura != null && !valorMaximoLimiteCobertura.equals(""))
 				configuracionCobertura.setValorMaximoLimiteCobertura(Double.parseDouble(valorMaximoLimiteCobertura));
 			
 			
 			if(coberturaEnsMultiId != null && !coberturaEnsMultiId.equals(""))
 				configuracionCobertura.setCoberturaEnsMultiId(coberturaEnsMultiId);
 			
 			if(coberturaEnsProgrId != null && !coberturaEnsProgrId.equals(""))
 				configuracionCobertura.setCoberturaEnsProgrId(coberturaEnsProgrId);
 			
 			if(tipoTasa != null && !tipoTasa.equals(""))
 				configuracionCobertura.setTipoTasa(Integer.parseInt(tipoTasa));
 			
 			if(tipoCofiguracion != null && !tipoCofiguracion.equals(""))
 				configuracionCobertura.setProductoCerrado(Integer.parseInt(tipoCofiguracion));
 		
 			if(coberturaCopiaId != null && !coberturaCopiaId.equals(""))
 				configuracionCobertura.setCoberturaCopiaId(new BigInteger(coberturaCopiaId));
 			
 			if(tasa != null && !tasa.equals(""))
 				configuracionCobertura.setTasa(Double.parseDouble(tasa));
 			
 			if(tasaEstructuras != null && !tasaEstructuras.equals(""))
 				configuracionCobertura.setTasaEstructuras(Double.parseDouble(tasaEstructuras));
 			
 			if(incluyeEnProducto != null && !incluyeEnProducto.equals(""))
 				configuracionCobertura.setIncluyeEnProducto(Integer.parseInt(incluyeEnProducto));
 			
 			if(ordenPresentacion != null && !ordenPresentacion.equals(""))
 				configuracionCobertura.setOrdenPresentacion(Integer.parseInt(ordenPresentacion));	
 			
 			if(dependeValor != null && !dependeValor.equals(""))
 				configuracionCobertura.setDependeValor(Integer.parseInt(dependeValor));	
 			
 			if(usaValorAdicional != null && !usaValorAdicional.equals(""))
 				configuracionCobertura.setUsaValorAdicional(Integer.parseInt(usaValorAdicional));
 			
 			if(valorMaximo2 != null && !valorMaximo2.equals(""))
 				configuracionCobertura.setValorMaximo2(Double.parseDouble(valorMaximo2));
 			
			if(tipoConsulta.equals("encontrarTodosVista")){ 
				List<PymeCoberturaConfigurada> results = coberturaConfiguradaDAO.buscarTodos();

				for(PymeCoberturaConfigurada configuracion:results){
					configuracionJSONObject.put("configuracionCoberturaId", configuracion.getId());
					configuracionJSONObject.put("coberturaNombre", configuracion.getNombre());
					configuracionJSONObject.put("grupoNombre", configuracion.getNombreComercialProducto());
					if(configuracion.getTipoDeclaracion()==1)
						configuracionJSONObject.put("tipoDeclaracionNombre", "GENERAL");
					else if(configuracion.getTipoDeclaracion()==2)
						configuracionJSONObject.put("tipoDeclaracionNombre", "POR DIRECCIÓN");
					else if(configuracion.getTipoDeclaracion()==3)
						configuracionJSONObject.put("tipoDeclaracionNombre", "PRINCIPAL DE ESTRUCTURAS");
					else if(configuracion.getTipoDeclaracion()==4)
						configuracionJSONObject.put("tipoDeclaracionNombre", "PRINCIPAL DE CONTENIDOS");
					configuracionJSONObject.put("ramo", configuracion.getRamoNombre());
					configuracionJSONObject.put("idMulti", configuracion.getIdMulti());
					configuracionJSONObject.put("idPrograma", configuracion.getIdPrograma());
					configuracionJSONArray.add(configuracionJSONObject);					
				}
				result.put("listadoConfiguraciones", configuracionJSONArray);
				
				
				List<TipoDeducible> tipoDeducibles = tipoDeducibleDAO.buscarTodos();
				for(TipoDeducible tDeducible : tipoDeducibles){
					tipoDeducibleJSONObject.put("tDeducibleId", tDeducible.getId());
					tipoDeducibleJSONObject.put("tDeducibleNombre", tDeducible.getNombre());
					tipoDeducibleJSONObject.put("tDeducibleTexto", tDeducible.getTextoDescripcion());
					tipoDeducibleJSONArray.add(tipoDeducibleJSONObject);
				};
				result.put("tipoDeducibleArr", tipoDeducibleJSONArray);
				
				List<PymePlan> planes = pymePlanDAO.buscarTodos();
				for(PymePlan tPlan : planes){
					planJSONObject.put("tPlanId", tPlan.getPlanId());
					planJSONObject.put("tPlanNombre", tPlan.getNombre());
					planJSONArray.add(planJSONObject);
				};
				result.put("planArr", planJSONArray);
			}
			
			if(tipoConsulta.equals("encontrarTasasPorConfiguracionID")){ 
				List<PymeCoberturaTasa> results = coberturaTasaDAO.buscarPorConfiguracionCoberturaId(new BigInteger(configuracionCoberturaId));
				
				for(PymeCoberturaTasa configuracion:results){
					configuracionJSONObject.put("configuracionCoberturaId", configuracion.getConfiguracionCoberturaId());
					configuracionJSONObject.put("valorCoberturaInicial", configuracion.getValorCoberturaInicial());
					configuracionJSONObject.put("valorCoberturaFinal", configuracion.getValorCoberturaFinal());
					configuracionJSONObject.put("tasa", configuracion.getTasa());
					configuracionJSONArray.add(configuracionJSONObject);
				}
				result.put("listadoTasas", configuracionJSONArray);
			}
			
			if(tipoConsulta.equals("encontrarTasasPorCotizacionCoberturaID")){
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");
				
				//Obtengo la configuración de la cobertura seleccionada
				configuracionCobertura= configuracionCoberturaDAO.buscarPorId(new BigInteger(configuracionCoberturaId));
				
				PymeCoberturaCotizacionValorDAO coberturaCotizacionDAO=new PymeCoberturaCotizacionValorDAO();
				List<PymeCoberturaCotizacionValor> results = coberturaCotizacionDAO.buscarPorCotizacionCoberturaId(new BigInteger(cotizacionId), configuracionCobertura.getCoberturaCopiaId());
				for(PymeCoberturaCotizacionValor configuracion:results){
					result.put("tasa", configuracion.getTasaIngresada());
					break;
				}
			}
			
			
			if(tipoConsulta.equals("encontrarConfiguracionesPorProductoId")){ 
				List<PymeCoberturaConfigurada> listadoCoberturasConfig = coberturaConfiguradaDAO.buscarPorGrupoPorProductoId(new BigInteger(grupoPorProductoId));				
				for(PymeCoberturaConfigurada configuracion:listadoCoberturasConfig){
					configuracionJSONObject.put("configuracionCoberturaId", configuracion.getId());
					configuracionJSONObject.put("coberturaNombre", configuracion.getNombre());
					configuracionJSONObject.put("coberturaId", configuracion.getCoberturaPymesId());
					configuracionJSONObject.put("grupoNombre", configuracion.getNombreComercialProducto());
					if(configuracion.getTipoDeclaracion()==1)
						configuracionJSONObject.put("tipoDeclaracionNombre", "GENERAL");
					else if(configuracion.getTipoDeclaracion()==2)
						configuracionJSONObject.put("tipoDeclaracionNombre", "POR DIRECCIÓN");
					else if(configuracion.getTipoDeclaracion()==3)
						configuracionJSONObject.put("tipoDeclaracionNombre", "PRINCIPAL DE ESTRUCTURAS");
					else if(configuracion.getTipoDeclaracion()==4)
						configuracionJSONObject.put("tipoDeclaracionNombre", "PRINCIPAL DE CONTENIDOS");
					configuracionJSONObject.put("tipoDeclaracion", configuracion.getTipoDeclaracion());
					configuracionJSONObject.put("origenValorLimiteAsegurado", configuracion.getOrigenValorLimiteAsegurado());
					configuracionJSONObject.put("porcentajeLimiteAsegurado", configuracion.getPorcentajeLimiteAsegurado());
					configuracionJSONObject.put("valorMaximoLimiteAsegurado", configuracion.getValorMaximoLimiteAsegurado());
					configuracionJSONObject.put("origenValorLimiteCobertura", configuracion.getOrigenValorLimiteCobertura());
					configuracionJSONObject.put("porcentajeLimiteCobertura", configuracion.getPorcentajeLimiteCobertura());
					configuracionJSONObject.put("valorMaximoLimiteCobertura", configuracion.getValorMaximoLimiteCobertura());
					configuracionJSONObject.put("textoDeducible", configuracion.getTextoDeducible());					
					configuracionJSONObject.put("idsDeducible", configuracion.getIdsDeducible());
					configuracionJSONObject.put("valoresDeducible", configuracion.getValoresDeducible());
					configuracionJSONObject.put("textosDeducible", configuracion.getTextosDeducible());
					configuracionJSONObject.put("tipoTasa", configuracion.getTipoTasa());
					configuracionJSONObject.put("tasa", configuracion.getTasa());
					configuracionJSONObject.put("tasaEstructuras", configuracion.getTasaEstructuras());
					configuracionJSONObject.put("coberturaCopiaId", configuracion.getCoberturaCopiaId());
					configuracionJSONObject.put("incluyeEnProducto", configuracion.getIncluyeEnProducto());
					configuracionJSONObject.put("ordenPresentacion", configuracion.getOrdenPresentacion());					
					configuracionJSONObject.put("dependeValor", configuracion.getDependeValor());
					configuracionJSONObject.put("tipoCoberturaId", configuracion.getTipoCoberturaId());
					configuracionJSONObject.put("usaValorAdicional", configuracion.getUsaValorAdicional());
					configuracionJSONObject.put("valorMaximo2", configuracion.getValorMaximo2());
					configuracionJSONObject.put("ramo", configuracion.getRamo().toString());
					configuracionJSONObject.put("ramoNombre", configuracion.getRamoNombre());
					configuracionJSONArray.add(configuracionJSONObject);
				}
				result.put("listadoConfiguraciones", configuracionJSONArray);
			}

			if(tipoConsulta.equals("encontrarPorId")){ 
				PymeConfiguracionCobertura results = configuracionCoberturaDAO.buscarPorId(new BigInteger(configuracionCoberturaId));				
				JSONObject CoberturaProgramaJSONObject = new JSONObject();
				JSONArray CoberturaProgramaJSONArray = new JSONArray();
				JSONObject CoberturaMultiJSONObject = new JSONObject();
				JSONArray CoberturaMultiJSONArray = new JSONArray();
				/**TODO: REALIZAMOS LOS SIGUIENTES PASOS:
				 * 1) HALLAMAOS EL GRUPO POR PRODUCTO
				 * 2) HALLAMOS EL RAMO
				 * 3) HALLAMOS EL PRODUCTO POR RAMO
				 * 4) HALLAMOS LA CONFIGURACION DEL PRODUCTO
				 * 5) HALLAMOS LOS ID DE LAS COBERTURAS
				 * 6) DEVOLVEMOS EL LISTADO DE COBERTURAS
				 * **/
				
				/**PROGRAMA DE SEGURO**/
				try{
					// 1) hallamos el grupo por producto
					GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
					GrupoPorProducto grupoPorProducto= grupoPorProductoDAO.buscarPorId(results.getGrupoPorProductoId().toString());
					// 2) hallamos el ramo
					/*PymeCoberturaDAO pymeCoberturaDAO= new PymeCoberturaDAO();
					PymeCobertura pymeCobertura= pymeCoberturaDAO.buscarPorId(results.getCoberturaPymesId());
					GrupoCoberturaDAO grupoCoberturaDAO= new GrupoCoberturaDAO();
					GrupoCobertura grupoCobertura=grupoCoberturaDAO.buscarPorId(pymeCobertura.getGrupoCoberturaId().toString());*/
					
					// 3) hallamos el producto a la configuracion de la tabla PymeGrupoProductoProducto
					PymeGrupoproductoProductoDAO pymeGrupoproductoProductoDAO= new PymeGrupoproductoProductoDAO();
					ProductoDAO productoDAO = new ProductoDAO();
					List<PymeGrupoproductoProducto> pymeGrupoproductoProducto =pymeGrupoproductoProductoDAO.buscarPorGrupoProducto(new BigInteger(grupoPorProducto.getId()));
					if(pymeGrupoproductoProducto.size()==0){
						observacion="El grupo por producto "+grupoPorProducto.getNombreComercialProducto()+" no tiene configuracion por ramo";
						throw new Exception("El grupo por producto "+grupoPorProducto.getNombreComercialProducto()+" no tiene configuracion por ramo");
					}
					
					//Buscamos los productos por ramo
					/*Producto producto= new Producto();
					for(PymeGrupoproductoProducto rs:pymeGrupoproductoProducto){
						Producto productoBuscado=productoDAO.buscarPorId(""+rs.getProductoId());
						if(productoBuscado.getNombre()!=null){
							String ramoBuscado=grupoCobertura.getRamo().getId();
							String ramodelProducto=""+productoBuscado.getRamoId();
							if(ramodelProducto.equals(ramoBuscado)){
								producto=productoBuscado;
								break;
							}
						}
					}*/
					
					List<Producto> producto= new ArrayList<Producto>();
					for(PymeGrupoproductoProducto rs:pymeGrupoproductoProducto){
						Producto productoBuscado=productoDAO.buscarPorId(""+rs.getProductoId());
						producto.add(productoBuscado);
					}
					
					/*
					if(producto.getNombre()==null){
						observacion="IdPrograma, NO existe configuracion para el producto "+grupoPorProducto.getProducto().getNombre()+" y el ramo "+grupoCobertura.getRamo().getNombre();				
						throw new Exception("IdPrograma, NO existe configuracion para el producto "+grupoPorProducto.getProducto().getNombre()+" y el ramo "+grupoCobertura.getRamo().getNombre());
					}*/
					
					
					// 4) hallamos la configuracion del producto
					/*ConfiguracionProductoDAO configuracionProductoDAO = new ConfiguracionProductoDAO();
					ConfiguracionProducto configuracionProducto = new ConfiguracionProducto();
					configuracionProducto=configuracionProductoDAO.buscarPorProducto(producto);
					if(configuracionProducto.getId()==null)
						observacion="IdPrograma, No existe ConfiguracionProducto para el producto "+producto.getNombre();*/
					
					ConfiguracionProductoDAO configuracionProductoDAO = new ConfiguracionProductoDAO();
					List<ConfiguracionProducto> configuracionProducto = new ArrayList<ConfiguracionProducto>();
					configuracionProducto=configuracionProductoDAO.buscarPorProductosIN(producto);
					if(configuracionProducto.size()==0)
						observacion="IdPrograma, No existe ConfiguracionProducto para el producto "+producto.get(0).getNombre();
					
					// 5) hallamos los ids de las coberturas
					/*PymeCoberturasConfiguracionDAO pymeCoberturasConfiguracionDAO= new PymeCoberturasConfiguracionDAO();
					List<PymeCoberturasConfiguracion> pymeCoberturasConfiguracion=pymeCoberturasConfiguracionDAO.buscarRamoGrupoCobertura(new BigInteger(configuracionProducto.getId()));
					*/
					List<BigInteger> listId = new ArrayList<BigInteger>();
					for(ConfiguracionProducto rs:configuracionProducto){
						listId.add(new BigInteger(rs.getId()));
					}
										
					PymeCoberturasConfiguracionDAO pymeCoberturasConfiguracionDAO= new PymeCoberturasConfiguracionDAO();
					List<PymeCoberturasConfiguracion> pymeCoberturasConfiguracion=pymeCoberturasConfiguracionDAO.buscarPorGrupoCoberturaIN(listId);
					
					
					//asigno al objeto de respuesta					
									
					for(PymeCoberturasConfiguracion rs:pymeCoberturasConfiguracion){
						CoberturaProgramaJSONObject.put("id",rs.getCoberturaId().toString());
						CoberturaProgramaJSONObject.put("nombre",rs.getCobertura()+"(EnsId:"+rs.getCoberturaId().toString()+") Ramo:"+rs.getRamotc());
						CoberturaProgramaJSONArray.add(CoberturaProgramaJSONObject);
					}
				}catch(Exception e){
					System.out.println("ID de programa no pudieron ser cargados");
					e.printStackTrace();
				}
				
				try{
					/**MULTIRIESGO**/
					GrupoPorProductoDAO grupoPorProductoDAO = new GrupoPorProductoDAO();
					GrupoPorProducto grupoPorProducto= grupoPorProductoDAO.buscarPorId(results.getGrupoPorProductoId().toString());
					//Se envia por defecto ramo multiriesgo
					
					// hallamos el producto a la configuracion de la tabla PymeGrupoProductoProducto
					
					PymeGrupoproductoProductoDAO pymeGrupoproductoProductoDAO= new PymeGrupoproductoProductoDAO();
					ProductoDAO productoDAO = new ProductoDAO();
					List<PymeGrupoproductoProducto> pymeGrupoproductoProducto =pymeGrupoproductoProductoDAO.buscarPorGrupoProducto(new BigInteger(grupoPorProducto.getId()));
					if(pymeGrupoproductoProducto.size()==0){
						observacion="El grupo por producto "+grupoPorProducto.getNombreComercialProducto()+" no tiene configuracion por ramo";
						throw new Exception("El grupo por producto "+grupoPorProducto.getNombreComercialProducto()+" no tiene configuracion por ramo");
					}
					
					/*
					Producto producto= new Producto();
					for(PymeGrupoproductoProducto rs:pymeGrupoproductoProducto){
						Producto productoBuscado=productoDAO.buscarPorId(""+rs.getProductoId());
						if(productoBuscado.getNombre()!=null){
							String ramoBuscado="1516276756602";
							String ramodelProducto=""+productoBuscado.getRamoId();
							if(ramodelProducto.equals(ramoBuscado)){
								producto=productoBuscado;
								break;
							}
						}
					}*/
					
					List<Producto> producto= new ArrayList<Producto>();
					for(PymeGrupoproductoProducto rs:pymeGrupoproductoProducto){
						Producto productoBuscado=productoDAO.buscarPorId(""+rs.getProductoId());
						producto.add(productoBuscado);
					}
					
					/*
					if(producto.getNombre()==null){
						observacion="IdPrograma, NO existe configuracion para el producto "+grupoPorProducto.getProducto().getNombre()+" y el ramo Multiriesgo";	
						throw new Exception("IdPrograma, NO existe configuracion para el producto "+grupoPorProducto.getProducto().getNombre()+" y el ramo Multiriesgo");
					}*/
										
					//hallamos la configuracion del producto
					/*ConfiguracionProductoDAO configuracionProductoDAO = new ConfiguracionProductoDAO();
					ConfiguracionProducto configuracionProducto = new ConfiguracionProducto();
					configuracionProducto=configuracionProductoDAO.buscarPorProducto(producto);
					if(configuracionProducto.getId()==null)
						observacion="IdMulti, No existe ConfiguracionProducto para el producto "+producto.getNombre();*/
					
					//tenemos el listado de coberturas
					/*PymeCoberturasConfiguracionDAO pymeCoberturasConfiguracionDAO= new PymeCoberturasConfiguracionDAO();
					List<PymeCoberturasConfiguracion>pymeCoberturasConfiguracion=pymeCoberturasConfiguracionDAO.buscarRamoGrupoCobertura(new BigInteger(configuracionProducto.getId()));
					if(pymeCoberturasConfiguracion.size()==0)
						observacion="IdMulti, No existe Coberturas para la ConfiguracionProducto "+configuracionProducto.getId();*/
					
					ConfiguracionProductoDAO configuracionProductoDAO = new ConfiguracionProductoDAO();
					List<ConfiguracionProducto> configuracionProducto = new ArrayList<ConfiguracionProducto>();
					configuracionProducto=configuracionProductoDAO.buscarPorProductosIN(producto);
					if(configuracionProducto.size()==0)
						observacion="IdPrograma, No existe ConfiguracionProducto para el producto "+producto.get(0).getNombre();
					
					List<BigInteger> listId = new ArrayList<BigInteger>();
					for(ConfiguracionProducto rs:configuracionProducto){
						listId.add(new BigInteger(rs.getId()));
					}
										
					PymeCoberturasConfiguracionDAO pymeCoberturasConfiguracionDAO= new PymeCoberturasConfiguracionDAO();
					List<PymeCoberturasConfiguracion> pymeCoberturasConfiguracion=pymeCoberturasConfiguracionDAO.buscarPorGrupoCoberturaIN(listId);
					
					for(PymeCoberturasConfiguracion rs:pymeCoberturasConfiguracion){
						CoberturaMultiJSONObject.put("id",rs.getCoberturaId().toString());
						CoberturaMultiJSONObject.put("nombre",rs.getCobertura()+"(EnsId:"+rs.getCoberturaId().toString()+") Ramo:"+rs.getRamotc());
						CoberturaMultiJSONArray.add(CoberturaMultiJSONObject);
					}
				}catch(Exception e){
					System.out.println("ID de programa no pudieron ser cargados");
					e.printStackTrace();
				}
				result.put("observacion",observacion);	
				result.put("ListaCoberturasPrograma", CoberturaProgramaJSONArray);
				result.put("ListaCoberturasMulti", CoberturaMultiJSONArray);								
				result.put("configuracionCoberturaId", results.getConfiguracionCoberturaId());
				result.put("coberturaPymesId", results.getCoberturaPymesId());
				result.put("grupoPorProductoId", results.getGrupoPorProductoId());
				result.put("tipoDeclaracion", results.getTipoDeclaracion());
				result.put("origenValorLimiteAsegurado", results.getOrigenValorLimiteAsegurado());
				result.put("porcentajeLimiteAsegurado", results.getPorcentajeLimiteAsegurado());
				result.put("valorMaximoLimiteAsegurado", results.getValorMaximoLimiteAsegurado());
				result.put("origenValorLimiteCobertura", results.getOrigenValorLimiteCobertura());
				result.put("porcentajeLimiteCobertura", results.getPorcentajeLimiteCobertura());
				result.put("valorMaximoLimiteCobertura", results.getValorMaximoLimiteCobertura());
				//result.put("textoDeducible", results.getTextoDeducible());					
				result.put("tipoTasa", results.getTipoTasa());
				result.put("tasa", results.getTasa());
				result.put("tasaEstructuras", results.getTasaEstructuras());
				result.put("coberturaCopiaId", results.getCoberturaCopiaId());
				result.put("incluyeEnProducto", results.getIncluyeEnProducto());
				result.put("ordenPresentacion", results.getOrdenPresentacion());					
				result.put("dependeValor", results.getDependeValor());
				result.put("coberturaEnsMultiId", results.getCoberturaEnsMultiId());
				result.put("coberturaEnsProgrId", results.getCoberturaEnsProgrId());
				result.put("usaValorAdicional", results.getUsaValorAdicional());
				result.put("valorMaximo2", results.getValorMaximo2());
				result.put("tipoConfiguracion", results.getProductoCerrado());
				JSONArray tasasJSONArray= new JSONArray(); 

				List<PymeCoberturaTasa> listadoCoberturasTasas=coberturaTasaDAO.buscarPorConfiguracionCoberturaId(results.getConfiguracionCoberturaId());
				for(PymeCoberturaTasa detActual:listadoCoberturasTasas){
					JSONObject tasaJSON = new JSONObject();
					tasaJSON.put("coberturaTasaId", detActual.getConfiguracionCoberturaId());
					tasaJSON.put("configuracionCoberturaId", detActual.getConfiguracionCoberturaId());
					tasaJSON.put("valorCoberturaInicial", detActual.getValorCoberturaInicial());
					tasaJSON.put("valorCoberturaFinal", detActual.getValorCoberturaFinal());
					tasaJSON.put("tasa", detActual.getTasa());
					tasasJSONArray.add(tasaJSON);
				}
				result.put("tasas", tasasJSONArray);
				
				/**tasas por riesgos terremoto**/
				PymeTasaRiesgoDAO pymeTasaRiesgoDAO= new PymeTasaRiesgoDAO();
				List<PymeTasaRiesgo> pymeTasaRiesgos= pymeTasaRiesgoDAO.BuscarPorCotizacion(results.getConfiguracionCoberturaId());
				JSONArray tasasRiesgoVolcanJSONArray= new JSONArray(); 
				JSONArray tasasRiesgoCostaJSONArray= new JSONArray(); 
				for(PymeTasaRiesgo rs:pymeTasaRiesgos){
					JSONObject tasaRiesgoVolcanJSON = new JSONObject();
					JSONObject tasaRiesgoCostaJSON = new JSONObject();
					if((rs.getPymeTipoRiesgoId().compareTo(new BigInteger("1")))==0){
						tasaRiesgoVolcanJSON.put("valorInicial",rs.getDesde());
						tasaRiesgoVolcanJSON.put("valorFinal", rs.getHasta());
						tasaRiesgoVolcanJSON.put("tasa", rs.getTasa());
						tasasRiesgoVolcanJSONArray.add(tasaRiesgoVolcanJSON);
					}else{
						if(rs.getReclamo()==1)
							tasaRiesgoCostaJSON.put("reclamo", "SI");
						else
							if(rs.getReclamo()==2)
								tasaRiesgoCostaJSON.put("reclamo", "NO");
						tasaRiesgoCostaJSON.put("tasa", rs.getTasa());
						tasasRiesgoCostaJSONArray.add(tasaRiesgoCostaJSON);
					}	
				}
				result.put("tasasRiesgoVolcanJSONArray", tasasRiesgoVolcanJSONArray);
				result.put("tasasRiesgoCostaJSONArray", tasasRiesgoCostaJSONArray);
				
				List<PymeTipoDeducibleCobertura> pymeTipoDeducibleList = pymeTipoDeducibleDAO.buscarPorConfiguracionCoberturaId(results.getConfiguracionCoberturaId());
				for(PymeTipoDeducibleCobertura pymeTipoDeducible:pymeTipoDeducibleList){					
					configuracionJSONObject.put("pymeTipoDeducibleid", pymeTipoDeducible.getTipoDeducibleCoberturaId());
					configuracionJSONObject.put("tipoDeducibleId", pymeTipoDeducible.getTipoDeducibleId());
					configuracionJSONObject.put("configuracionCoberturaId", pymeTipoDeducible.getConfiguracionCoberturaId());
					TipoDeducible tipoDeducible = tipoDeducibleDAO.buscarPorId(pymeTipoDeducible.getTipoDeducibleId().toString());
					configuracionJSONObject.put("texto", pymeTipoDeducible.getTexto());
					configuracionJSONObject.put("valorDeducible", pymeTipoDeducible.getValor());
					if(pymeTipoDeducible.getSeguridadAdecuada()){
						configuracionJSONObject.put("auxSeguridadAdecuadaText", "SI");
						configuracionJSONObject.put("seguridadAdecuada", "1");
					}	
					else{
						configuracionJSONObject.put("auxSeguridadAdecuadaText", "NO");
					    configuracionJSONObject.put("seguridadAdecuada", "0");
					}    
					configuracionJSONArray.add(configuracionJSONObject);
				}
				result.put("pymeTipoDeducibleArr", configuracionJSONArray);
				
				List<PymePlanConfiguracion> pymePlanesList = pymePlanConfiguracionDAO.buscarPorConfiguracionId(results.getConfiguracionCoberturaId());
				for(PymePlanConfiguracion pymePlanConfig:pymePlanesList){										
					planJSONObject.put("planId", pymePlanConfig.getPlanId());
					planJSONObject.put("valorMaximo", pymePlanConfig.getValorMaximo());
					PymePlan pymePlan = pymePlanDAO.buscarPorId(pymePlanConfig.getPlanId());
					planJSONObject.put("texto", pymePlan.getNombre());					
					planJSONArray.add(planJSONObject);
				}
				result.put("pymePlanArr", planJSONArray);
			}
			
			if(tipoConsulta.equals("crear")){
				Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");

				PymeConfiguracionCobertura configuracion = pymeConfiguracionCoberturaTransaction.crear(configuracionCobertura);

				/**Tasas de Riesgos**/
				String[] arrTasasRiesgosDesde = listaTasasRiesgosDesde.split(",");
				String[] arrTasasRiesgosHasta = listaTasasRiesgosHasta.split(",");
				String[] arrTasasRiesgosTasas = listaTasasRiesgosTasas.split(",");
				
				for(int i=1; i< arrTasasRiesgosDesde.length; i++){
					PymeTasaRiesgo pymeTasaRiesgo = new PymeTasaRiesgo();
					pymeTasaRiesgo.setDesde(Double.parseDouble(arrTasasRiesgosDesde[i]));
					pymeTasaRiesgo.setHasta(Double.parseDouble(arrTasasRiesgosHasta[i]));
					pymeTasaRiesgo.setTasa(Double.parseDouble(arrTasasRiesgosTasas[i]));
					pymeTasaRiesgo.setPymeConfiguracionCoberturaId(configuracion.getConfiguracionCoberturaId());
					pymeTasaRiesgo.setPymeTipoRiesgoId(new BigInteger("1"));	
					pymeTasaRiesgoTransaction.crear(pymeTasaRiesgo);
				}
				
				/**Tasa de Reclamos**/
				String[] arrTasasReclamos = listaTasasReclamos.split(",");
				String[] arrTasasReclamosTasas = listaTasasReclamosTasas.split(",");
				for(int i=1; i< arrTasasReclamos.length; i++){
					PymeTasaRiesgo pymeTasaRiesgo = new PymeTasaRiesgo();
					pymeTasaRiesgo.setTasa(Double.parseDouble(arrTasasReclamosTasas[i]));
					pymeTasaRiesgo.setPymeConfiguracionCoberturaId(configuracion.getConfiguracionCoberturaId());
					pymeTasaRiesgo.setPymeTipoRiesgoId(new BigInteger("2"));
					if(arrTasasReclamos[i].equals("SI"))
						pymeTasaRiesgo.setReclamo(1);
					else
						pymeTasaRiesgo.setReclamo(2);
					pymeTasaRiesgoTransaction.crear(pymeTasaRiesgo);
				}
				
				
				String[] arrlistaValorMinimo = listaValorMinimo.split(",");
				String[] arrlistaValorMaximo = listaValorMaximo.split(",");
				String[] arrlistaTasa = listaTasa.split(",");
				PymeCoberturaTasa nuevaCoberturaTasa;
				List<PymeCoberturaTasa> listadoTasas=new ArrayList<PymeCoberturaTasa>(); 
				for(int i=1; i< arrlistaValorMinimo.length; i++){
					nuevaCoberturaTasa=new PymeCoberturaTasa();
					nuevaCoberturaTasa.setConfiguracionCoberturaId(configuracion.getConfiguracionCoberturaId());
					nuevaCoberturaTasa.setValorCoberturaInicial(Float.valueOf(arrlistaValorMinimo[i]));
					nuevaCoberturaTasa.setValorCoberturaFinal(Float.valueOf(arrlistaValorMaximo[i]));
					nuevaCoberturaTasa.setTasa(Float.valueOf(arrlistaTasa[i]));
					pymeCoberturaTasaTransaction.crear(nuevaCoberturaTasa);
					listadoTasas.add(nuevaCoberturaTasa);
				}

				JSONArray array = (JSONArray)JSONSerializer.toJSON(arrTextoDeducible);
				List<PymeTipoDeducibleCobertura> listadoDeducibles=new ArrayList<PymeTipoDeducibleCobertura>();
				for (Object js:array){
					JSONObject jsonStr=(JSONObject)JSONSerializer.toJSON(js);
					PymeTipoDeducibleCobertura pymeTipoDeducibleCobertura = new PymeTipoDeducibleCobertura();
					pymeTipoDeducibleCobertura.setTipoDeducibleCoberturaId(jsonStr.getString("tipoDeducibleCoberturaId"));
					pymeTipoDeducibleCobertura.setConfiguracionCoberturaId(configuracion.getConfiguracionCoberturaId());
					pymeTipoDeducibleCobertura.setTipoDeducibleId(new BigInteger(jsonStr.getString("tipoDeducibleId")));
					pymeTipoDeducibleCobertura.setValor(Double.parseDouble(jsonStr.getString("valor")));                    
					pymeTipoDeducibleCobertura.setTexto(jsonStr.getString(jsonStr.getString("texto")));
					if(jsonStr.getString("seguridadAdecuada").equals("1"))
                        pymeTipoDeducibleCobertura.setSeguridadAdecuada(true);
                    else
                    	pymeTipoDeducibleCobertura.setSeguridadAdecuada(false);	
					pymeTipoDeducibleCoberturaTransaction.crear(pymeTipoDeducibleCobertura);
					listadoDeducibles.add(pymeTipoDeducibleCobertura);
				}
				
				JSONArray arrayPlan = (JSONArray)JSONSerializer.toJSON(arrPlanes);
				for (Object js:arrayPlan){
                    JSONObject jsonStr=(JSONObject)JSONSerializer.toJSON(js);
                    PymePlanConfiguracion pymePlanConfiguracion = new PymePlanConfiguracion();
                    pymePlanConfiguracion.setPlanId(new BigInteger(jsonStr.getString("planId")));
                    pymePlanConfiguracion.setValorMaximo(Double.parseDouble(jsonStr.getString("valor")));                    
                	pymePlanConfiguracion.setConfiguracionCoberturaId(configuracion.getConfiguracionCoberturaId());
                	pymePlanConfiguracionTransaction.crear(pymePlanConfiguracion);
                }
				
				//Creo el log de cambios
				PymeConfiguracionLog nuevoLog=new PymeConfiguracionLog();
				nuevoLog.setConfiguracionOriginalId(configuracionCobertura.getConfiguracionCoberturaId());
				nuevoLog.setGrupoPorProductoId(configuracionCobertura.getGrupoPorProductoId());
				nuevoLog.setCoberturaPyesID(configuracionCobertura.getCoberturaPymesId());
				nuevoLog.setCampo("Cobertura creada.");
				nuevoLog.setUsuario(usuario.getLogin());
				PymeConfiguracionLogTransaction pymeConfiguracionLogTransaction=new PymeConfiguracionLogTransaction();
				pymeConfiguracionLogTransaction.crear(nuevoLog);
			}

			if(tipoConsulta.equals("copiarProducto")){
				//Recupero el listado de configuraciones de acuerdo al grupo por producto
				List<PymeConfiguracionCobertura> listadoConfiguracionCoberturas=configuracionCoberturaDAO.buscarPorGrupoPorProductoID(new BigInteger(grupoPorProductoId));
				for(PymeConfiguracionCobertura configuracionActual:listadoConfiguracionCoberturas){
					PymeConfiguracionCobertura nuevaConfiguracionCobertura = new PymeConfiguracionCobertura();
					nuevaConfiguracionCobertura.setGrupoPorProductoId(new BigInteger(grupoPorProductoDestinoId));
					nuevaConfiguracionCobertura.setCoberturaPymesId(configuracionActual.getCoberturaPymesId()); 			
					nuevaConfiguracionCobertura.setTipoDeclaracion(configuracionActual.getTipoDeclaracion());
					nuevaConfiguracionCobertura.setOrigenValorLimiteAsegurado(configuracionActual.getOrigenValorLimiteAsegurado());
					nuevaConfiguracionCobertura.setPorcentajeLimiteAsegurado(configuracionActual.getOrigenValorLimiteAsegurado());
					nuevaConfiguracionCobertura.setValorMaximoLimiteAsegurado(configuracionActual.getValorMaximoLimiteAsegurado());
					nuevaConfiguracionCobertura.setOrigenValorLimiteCobertura(configuracionActual.getOrigenValorLimiteCobertura());
					nuevaConfiguracionCobertura.setPorcentajeLimiteCobertura(configuracionActual.getPorcentajeLimiteCobertura());
					nuevaConfiguracionCobertura.setValorMaximoLimiteCobertura(configuracionActual.getValorMaximoLimiteCobertura());
					nuevaConfiguracionCobertura.setCoberturaEnsMultiId(configuracionActual.getCoberturaEnsMultiId());
					nuevaConfiguracionCobertura.setCoberturaEnsProgrId(configuracionActual.getCoberturaEnsProgrId());
					nuevaConfiguracionCobertura.setTipoTasa(configuracionActual.getTipoTasa());
					nuevaConfiguracionCobertura.setProductoCerrado(configuracionActual.getProductoCerrado());
					nuevaConfiguracionCobertura.setCoberturaCopiaId(configuracionActual.getCoberturaCopiaId());
					nuevaConfiguracionCobertura.setTasa(configuracionActual.getTasa());
					nuevaConfiguracionCobertura.setTasaEstructuras(configuracionActual.getTasaEstructuras());
					nuevaConfiguracionCobertura.setIncluyeEnProducto(configuracionActual.getIncluyeEnProducto());
					nuevaConfiguracionCobertura.setOrdenPresentacion(configuracionActual.getOrdenPresentacion());	
					nuevaConfiguracionCobertura.setDependeValor(configuracionActual.getDependeValor());	
					nuevaConfiguracionCobertura.setUsaValorAdicional(configuracionActual.getUsaValorAdicional());
					nuevaConfiguracionCobertura.setValorMaximo2(configuracionActual.getValorMaximo2());
					//graba la nueva configuracion
					nuevaConfiguracionCobertura = pymeConfiguracionCoberturaTransaction.crear(nuevaConfiguracionCobertura);

					//Recupero todas las tasas para copiarle
					PymeCoberturaTasa nuevaCoberturaTasa;
					List<PymeCoberturaTasa> listadoCoberturasTasas=coberturaTasaDAO.buscarPorConfiguracionCoberturaId(configuracionActual.getConfiguracionCoberturaId());
					for(PymeCoberturaTasa coberturaTasaActual:listadoCoberturasTasas){
						nuevaCoberturaTasa=new PymeCoberturaTasa();
						nuevaCoberturaTasa.setConfiguracionCoberturaId(nuevaConfiguracionCobertura.getConfiguracionCoberturaId());
						nuevaCoberturaTasa.setValorCoberturaInicial(coberturaTasaActual.getValorCoberturaInicial());
						nuevaCoberturaTasa.setValorCoberturaFinal(coberturaTasaActual.getValorCoberturaFinal());
						nuevaCoberturaTasa.setTasa(coberturaTasaActual.getTasa());
						pymeCoberturaTasaTransaction.crear(nuevaCoberturaTasa);
					}
					//Recupero todos los deducibles
					List<PymeTipoDeducibleCobertura> pymeTipoDeducibleList = pymeTipoDeducibleDAO.buscarPorConfiguracionCoberturaId(configuracionActual.getConfiguracionCoberturaId());
					for(PymeTipoDeducibleCobertura pymeTipoDeducibleActual:pymeTipoDeducibleList){					
						PymeTipoDeducibleCobertura pymeTipoDeducibleCobertura = new PymeTipoDeducibleCobertura();
	                    pymeTipoDeducibleCobertura.setTipoDeducibleCoberturaId(pymeTipoDeducibleActual.getTipoDeducibleCoberturaId());
	                    pymeTipoDeducibleCobertura.setConfiguracionCoberturaId(nuevaConfiguracionCobertura.getConfiguracionCoberturaId());
	                    pymeTipoDeducibleCobertura.setTipoDeducibleId(pymeTipoDeducibleActual.getTipoDeducibleId());
	                    pymeTipoDeducibleCobertura.setValor(pymeTipoDeducibleActual.getValor());
	                    pymeTipoDeducibleCobertura.setSeguridadAdecuada(pymeTipoDeducibleActual.getSeguridadAdecuada());	
	                    pymeTipoDeducibleCobertura.setTexto(pymeTipoDeducibleActual.getTexto());
	                    
	                	pymeTipoDeducibleCoberturaTransaction.crear(pymeTipoDeducibleCobertura);
					}
					//Recupero todos los planes
					List<PymePlanConfiguracion> pymePlanesList = pymePlanConfiguracionDAO.buscarPorConfiguracionId(configuracionActual.getConfiguracionCoberturaId());
					for(PymePlanConfiguracion pymePlanConfigActual:pymePlanesList){					
						PymePlanConfiguracion pymePlanConfiguracion = new PymePlanConfiguracion();
	                    pymePlanConfiguracion.setPlanId(pymePlanConfigActual.getPlanId());
	                    pymePlanConfiguracion.setValorMaximo(pymePlanConfigActual.getValorMaximo());                    
	                	pymePlanConfiguracion.setConfiguracionCoberturaId(nuevaConfiguracionCobertura.getConfiguracionCoberturaId());
	                	pymePlanConfiguracionTransaction.crear(pymePlanConfiguracion);
					}
				}
			}
			
			if(tipoConsulta.equals("editar")){
				Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
				
				PymeConfiguracionCobertura configuracionCoberturaActual=configuracionCoberturaDAO.buscarPorId(configuracionCobertura.getConfiguracionCoberturaId());
				PymeConfiguracionLogTransaction pymeConfiguracionLogTransaction=new PymeConfiguracionLogTransaction();
				
				PymeConfiguracionCobertura configuracion = pymeConfiguracionCoberturaTransaction.editar(configuracionCobertura);
				
				if(configuracionCoberturaActual.getTipoDeclaracion()!=configuracionCobertura.getTipoDeclaracion()){
					PymeConfiguracionLog nuevoLog=new PymeConfiguracionLog();
					nuevoLog.setConfiguracionOriginalId(configuracionCobertura.getConfiguracionCoberturaId());
					nuevoLog.setGrupoPorProductoId(configuracionCoberturaActual.getGrupoPorProductoId());
					nuevoLog.setCoberturaPyesID(configuracionCoberturaActual.getCoberturaPymesId());
					nuevoLog.setCampo("Tipo Declaración");
					nuevoLog.setValorAnterior(""+configuracionCoberturaActual.getTipoDeclaracion());
					nuevoLog.setValorNuevo(""+configuracionCobertura.getTipoDeclaracion());
					nuevoLog.setUsuario(usuario.getLogin());
					pymeConfiguracionLogTransaction.crear(nuevoLog);
				}
				if(configuracionCoberturaActual.getOrigenValorLimiteAsegurado()!=configuracionCobertura.getOrigenValorLimiteAsegurado()){
					PymeConfiguracionLog nuevoLog=new PymeConfiguracionLog();
					nuevoLog.setConfiguracionOriginalId(configuracionCobertura.getConfiguracionCoberturaId());
					nuevoLog.setGrupoPorProductoId(configuracionCoberturaActual.getGrupoPorProductoId());
					nuevoLog.setCoberturaPyesID(configuracionCoberturaActual.getCoberturaPymesId());
					nuevoLog.setCampo("OrigenValorLimiteAsegurado");
					nuevoLog.setValorAnterior(""+configuracionCoberturaActual.getOrigenValorLimiteAsegurado());
					nuevoLog.setValorNuevo(""+configuracionCobertura.getOrigenValorLimiteAsegurado());
					nuevoLog.setUsuario(usuario.getLogin());
					pymeConfiguracionLogTransaction.crear(nuevoLog);
				}
				if(configuracionCoberturaActual.getPorcentajeLimiteAsegurado()!=configuracionCobertura.getPorcentajeLimiteAsegurado()){
					PymeConfiguracionLog nuevoLog=new PymeConfiguracionLog();
					nuevoLog.setConfiguracionOriginalId(configuracionCobertura.getConfiguracionCoberturaId());
					nuevoLog.setGrupoPorProductoId(configuracionCoberturaActual.getGrupoPorProductoId());
					nuevoLog.setCoberturaPyesID(configuracionCoberturaActual.getCoberturaPymesId());
					nuevoLog.setCampo("PorcentajeLimiteAsegurado");
					nuevoLog.setValorAnterior(""+configuracionCoberturaActual.getPorcentajeLimiteAsegurado());
					nuevoLog.setValorNuevo(""+configuracionCobertura.getPorcentajeLimiteAsegurado());
					nuevoLog.setUsuario(usuario.getLogin());
					pymeConfiguracionLogTransaction.crear(nuevoLog);
				}
				if(configuracionCoberturaActual.getValorMaximoLimiteAsegurado()!=configuracionCobertura.getValorMaximoLimiteAsegurado()){
					PymeConfiguracionLog nuevoLog=new PymeConfiguracionLog();
					nuevoLog.setConfiguracionOriginalId(configuracionCobertura.getConfiguracionCoberturaId());
					nuevoLog.setGrupoPorProductoId(configuracionCoberturaActual.getGrupoPorProductoId());
					nuevoLog.setCoberturaPyesID(configuracionCoberturaActual.getCoberturaPymesId());
					nuevoLog.setCampo("ValorMaximoLimiteAsegurado");
					nuevoLog.setValorAnterior(""+configuracionCoberturaActual.getValorMaximoLimiteAsegurado());
					nuevoLog.setValorNuevo(""+configuracionCobertura.getValorMaximoLimiteAsegurado());
					nuevoLog.setUsuario(usuario.getLogin());
					pymeConfiguracionLogTransaction.crear(nuevoLog);
				}
				if(configuracionCoberturaActual.getOrigenValorLimiteCobertura()!=configuracionCobertura.getOrigenValorLimiteCobertura()){
					PymeConfiguracionLog nuevoLog=new PymeConfiguracionLog();
					nuevoLog.setConfiguracionOriginalId(configuracionCobertura.getConfiguracionCoberturaId());
					nuevoLog.setGrupoPorProductoId(configuracionCoberturaActual.getGrupoPorProductoId());
					nuevoLog.setCoberturaPyesID(configuracionCoberturaActual.getCoberturaPymesId());
					nuevoLog.setCampo("OrigenValorLimiteCobertura");
					nuevoLog.setValorAnterior(""+configuracionCoberturaActual.getOrigenValorLimiteCobertura());
					nuevoLog.setValorNuevo(""+configuracionCobertura.getOrigenValorLimiteCobertura());
					nuevoLog.setUsuario(usuario.getLogin());
					pymeConfiguracionLogTransaction.crear(nuevoLog);
				}
				if(configuracionCoberturaActual.getPorcentajeLimiteCobertura()!=configuracionCobertura.getPorcentajeLimiteCobertura()){
					PymeConfiguracionLog nuevoLog=new PymeConfiguracionLog();
					nuevoLog.setConfiguracionOriginalId(configuracionCobertura.getConfiguracionCoberturaId());
					nuevoLog.setGrupoPorProductoId(configuracionCoberturaActual.getGrupoPorProductoId());
					nuevoLog.setCoberturaPyesID(configuracionCoberturaActual.getCoberturaPymesId());
					nuevoLog.setCampo("PorcentajeLimiteCobertura");
					nuevoLog.setValorAnterior(""+configuracionCoberturaActual.getPorcentajeLimiteCobertura());
					nuevoLog.setValorNuevo(""+configuracionCobertura.getPorcentajeLimiteCobertura());
					nuevoLog.setUsuario(usuario.getLogin());
					pymeConfiguracionLogTransaction.crear(nuevoLog);
				}
				if(configuracionCoberturaActual.getValorMaximoLimiteCobertura()!=configuracionCobertura.getValorMaximoLimiteCobertura()){
					PymeConfiguracionLog nuevoLog=new PymeConfiguracionLog();
					nuevoLog.setConfiguracionOriginalId(configuracionCobertura.getConfiguracionCoberturaId());
					nuevoLog.setGrupoPorProductoId(configuracionCoberturaActual.getGrupoPorProductoId());
					nuevoLog.setCoberturaPyesID(configuracionCoberturaActual.getCoberturaPymesId());
					nuevoLog.setCampo("ValorMaximoLimiteCobertura");
					nuevoLog.setValorAnterior(""+configuracionCoberturaActual.getValorMaximoLimiteCobertura());
					nuevoLog.setValorNuevo(""+configuracionCobertura.getValorMaximoLimiteCobertura());
					nuevoLog.setUsuario(usuario.getLogin());
					pymeConfiguracionLogTransaction.crear(nuevoLog);
				}
				if(configuracionCoberturaActual.getTipoTasa()!=configuracionCobertura.getTipoTasa()){
					PymeConfiguracionLog nuevoLog=new PymeConfiguracionLog();
					nuevoLog.setConfiguracionOriginalId(configuracionCobertura.getConfiguracionCoberturaId());
					nuevoLog.setGrupoPorProductoId(configuracionCoberturaActual.getGrupoPorProductoId());
					nuevoLog.setCoberturaPyesID(configuracionCoberturaActual.getCoberturaPymesId());
					nuevoLog.setCampo("TipoTasa");
					nuevoLog.setValorAnterior(""+configuracionCoberturaActual.getTipoTasa());
					nuevoLog.setValorNuevo(""+configuracionCobertura.getTipoTasa());
					nuevoLog.setUsuario(usuario.getLogin());
					pymeConfiguracionLogTransaction.crear(nuevoLog);
				}
				if(configuracionCoberturaActual.getTasa()!=configuracionCobertura.getTasa()){
					PymeConfiguracionLog nuevoLog=new PymeConfiguracionLog();
					nuevoLog.setConfiguracionOriginalId(configuracionCobertura.getConfiguracionCoberturaId());
					nuevoLog.setGrupoPorProductoId(configuracionCoberturaActual.getGrupoPorProductoId());
					nuevoLog.setCoberturaPyesID(configuracionCoberturaActual.getCoberturaPymesId());
					nuevoLog.setCampo("Tasa");
					nuevoLog.setValorAnterior(""+configuracionCoberturaActual.getTasa());
					nuevoLog.setValorNuevo(""+configuracionCobertura.getTasa());
					nuevoLog.setUsuario(usuario.getLogin());
					pymeConfiguracionLogTransaction.crear(nuevoLog);
				}
				if(configuracionCoberturaActual.getCoberturaCopiaId()!=configuracionCobertura.getCoberturaCopiaId()){
					PymeConfiguracionLog nuevoLog=new PymeConfiguracionLog();
					nuevoLog.setConfiguracionOriginalId(configuracionCobertura.getConfiguracionCoberturaId());
					nuevoLog.setGrupoPorProductoId(configuracionCoberturaActual.getGrupoPorProductoId());
					nuevoLog.setCoberturaPyesID(configuracionCoberturaActual.getCoberturaPymesId());
					nuevoLog.setCampo("CoberturaCopiaId");
					nuevoLog.setValorAnterior(""+configuracionCoberturaActual.getCoberturaCopiaId());
					nuevoLog.setValorNuevo(""+configuracionCobertura.getCoberturaCopiaId());
					nuevoLog.setUsuario(usuario.getLogin());
					pymeConfiguracionLogTransaction.crear(nuevoLog);
				}
				if(configuracionCoberturaActual.getIncluyeEnProducto()!=configuracionCobertura.getIncluyeEnProducto()){
					PymeConfiguracionLog nuevoLog=new PymeConfiguracionLog();
					nuevoLog.setConfiguracionOriginalId(configuracionCobertura.getConfiguracionCoberturaId());
					nuevoLog.setGrupoPorProductoId(configuracionCoberturaActual.getGrupoPorProductoId());
					nuevoLog.setCoberturaPyesID(configuracionCoberturaActual.getCoberturaPymesId());
					nuevoLog.setCampo("IncluyeEnProducto");
					nuevoLog.setValorAnterior(""+configuracionCoberturaActual.getIncluyeEnProducto());
					nuevoLog.setValorNuevo(""+configuracionCobertura.getIncluyeEnProducto());
					nuevoLog.setUsuario(usuario.getLogin());
					pymeConfiguracionLogTransaction.crear(nuevoLog);
				}
				if(configuracionCoberturaActual.getOrdenPresentacion()!=configuracionCobertura.getOrdenPresentacion()){
					PymeConfiguracionLog nuevoLog=new PymeConfiguracionLog();
					nuevoLog.setConfiguracionOriginalId(configuracionCobertura.getConfiguracionCoberturaId());
					nuevoLog.setGrupoPorProductoId(configuracionCoberturaActual.getGrupoPorProductoId());
					nuevoLog.setCoberturaPyesID(configuracionCoberturaActual.getCoberturaPymesId());
					nuevoLog.setCampo("OrdenPresentacion");
					nuevoLog.setValorAnterior(""+configuracionCoberturaActual.getOrdenPresentacion());
					nuevoLog.setValorNuevo(""+configuracionCobertura.getOrdenPresentacion());
					nuevoLog.setUsuario(usuario.getLogin());
					pymeConfiguracionLogTransaction.crear(nuevoLog);
				}
				if(configuracionCoberturaActual.getDependeValor()!=configuracionCobertura.getDependeValor()){
					PymeConfiguracionLog nuevoLog=new PymeConfiguracionLog();
					nuevoLog.setConfiguracionOriginalId(configuracionCobertura.getConfiguracionCoberturaId());
					nuevoLog.setGrupoPorProductoId(configuracionCoberturaActual.getGrupoPorProductoId());
					nuevoLog.setCoberturaPyesID(configuracionCoberturaActual.getCoberturaPymesId());
					nuevoLog.setCampo("DependeValor");
					nuevoLog.setValorAnterior(""+configuracionCoberturaActual.getDependeValor());
					nuevoLog.setValorNuevo(""+configuracionCobertura.getDependeValor());
					nuevoLog.setUsuario(usuario.getLogin());
					pymeConfiguracionLogTransaction.crear(nuevoLog);
				}
				if(configuracionCoberturaActual.getCoberturaEnsMultiId()!=null){
					if(!configuracionCoberturaActual.getCoberturaEnsMultiId().equals(configuracionCobertura.getCoberturaEnsMultiId())){
						PymeConfiguracionLog nuevoLog=new PymeConfiguracionLog();
						nuevoLog.setConfiguracionOriginalId(configuracionCobertura.getConfiguracionCoberturaId());
						nuevoLog.setGrupoPorProductoId(configuracionCoberturaActual.getGrupoPorProductoId());
						nuevoLog.setCoberturaPyesID(configuracionCoberturaActual.getCoberturaPymesId());
						nuevoLog.setCampo("CoberturaEnsMultiId");
						nuevoLog.setValorAnterior(""+configuracionCoberturaActual.getCoberturaEnsMultiId());
						nuevoLog.setValorNuevo(""+configuracionCobertura.getCoberturaEnsMultiId());
						nuevoLog.setUsuario(usuario.getLogin());
						pymeConfiguracionLogTransaction.crear(nuevoLog);
					}
				}
				if(configuracionCoberturaActual.getCoberturaEnsProgrId()!=null){
					if(!configuracionCoberturaActual.getCoberturaEnsProgrId().equals(configuracionCobertura.getCoberturaEnsProgrId())){
						PymeConfiguracionLog nuevoLog=new PymeConfiguracionLog();
						nuevoLog.setConfiguracionOriginalId(configuracionCobertura.getConfiguracionCoberturaId());
						nuevoLog.setGrupoPorProductoId(configuracionCoberturaActual.getGrupoPorProductoId());
						nuevoLog.setCoberturaPyesID(configuracionCoberturaActual.getCoberturaPymesId());
						nuevoLog.setCampo("CoberturaEnsProgrId");
						nuevoLog.setValorAnterior(""+configuracionCoberturaActual.getCoberturaEnsProgrId());
						nuevoLog.setValorNuevo(""+configuracionCobertura.getCoberturaEnsProgrId());
						nuevoLog.setUsuario(usuario.getLogin());
						pymeConfiguracionLogTransaction.crear(nuevoLog);
					}
				}
				if(configuracionCoberturaActual.getUsaValorAdicional()!=configuracionCobertura.getUsaValorAdicional()){
					PymeConfiguracionLog nuevoLog=new PymeConfiguracionLog();
					nuevoLog.setConfiguracionOriginalId(configuracionCobertura.getConfiguracionCoberturaId());
					nuevoLog.setGrupoPorProductoId(configuracionCoberturaActual.getGrupoPorProductoId());
					nuevoLog.setCoberturaPyesID(configuracionCoberturaActual.getCoberturaPymesId());
					nuevoLog.setCampo("UsaValorAdicional");
					nuevoLog.setValorAnterior(""+configuracionCoberturaActual.getUsaValorAdicional());
					nuevoLog.setValorNuevo(""+configuracionCobertura.getUsaValorAdicional());
					nuevoLog.setUsuario(usuario.getLogin());
					pymeConfiguracionLogTransaction.crear(nuevoLog);
				}
				if(configuracionCoberturaActual.getValorMaximo2()!=configuracionCobertura.getValorMaximo2()){
					PymeConfiguracionLog nuevoLog=new PymeConfiguracionLog();
					nuevoLog.setConfiguracionOriginalId(configuracionCobertura.getConfiguracionCoberturaId());
					nuevoLog.setGrupoPorProductoId(configuracionCoberturaActual.getGrupoPorProductoId());
					nuevoLog.setCoberturaPyesID(configuracionCoberturaActual.getCoberturaPymesId());
					nuevoLog.setCampo("ValorMaximo2");
					nuevoLog.setValorAnterior(""+configuracionCoberturaActual.getValorMaximo2());
					nuevoLog.setValorNuevo(""+configuracionCobertura.getValorMaximo2());
					nuevoLog.setUsuario(usuario.getLogin());
					pymeConfiguracionLogTransaction.crear(nuevoLog);
				}
				if(configuracionCoberturaActual.getProductoCerrado()!=configuracionCobertura.getProductoCerrado()){
					PymeConfiguracionLog nuevoLog=new PymeConfiguracionLog();
					nuevoLog.setConfiguracionOriginalId(configuracionCobertura.getConfiguracionCoberturaId());
					nuevoLog.setGrupoPorProductoId(configuracionCoberturaActual.getGrupoPorProductoId());
					nuevoLog.setCoberturaPyesID(configuracionCoberturaActual.getCoberturaPymesId());
					nuevoLog.setCampo("ProductoCerrado");
					nuevoLog.setValorAnterior(""+configuracionCoberturaActual.getProductoCerrado());
					nuevoLog.setValorNuevo(""+configuracionCobertura.getProductoCerrado());
					nuevoLog.setUsuario(usuario.getLogin());
					pymeConfiguracionLogTransaction.crear(nuevoLog);
				}
				if(configuracionCoberturaActual.getTasaEstructuras()!=configuracionCobertura.getTasaEstructuras()){
					PymeConfiguracionLog nuevoLog=new PymeConfiguracionLog();
					nuevoLog.setConfiguracionOriginalId(configuracionCobertura.getConfiguracionCoberturaId());
					nuevoLog.setGrupoPorProductoId(configuracionCoberturaActual.getGrupoPorProductoId());
					nuevoLog.setCoberturaPyesID(configuracionCoberturaActual.getCoberturaPymesId());
					nuevoLog.setCampo("TasaEstructuras");
					nuevoLog.setValorAnterior(""+configuracionCoberturaActual.getTasaEstructuras());
					nuevoLog.setValorNuevo(""+configuracionCobertura.getTasaEstructuras());
					nuevoLog.setUsuario(usuario.getLogin());
					pymeConfiguracionLogTransaction.crear(nuevoLog);
				}
				
				
				//eliminamos las tasas por riesgo de esa configuracion
				PymeTasaRiesgoDAO pymeTasaRiesgoDAO = new PymeTasaRiesgoDAO();
				List<PymeTasaRiesgo> listadoCoberturasTasasRiesgo=pymeTasaRiesgoDAO.BuscarPorCotizacion(configuracion.getConfiguracionCoberturaId());
				for(PymeTasaRiesgo ct:listadoCoberturasTasasRiesgo){
					pymeTasaRiesgoTransaction.eliminar(ct);
				}
				
				/**Tasas de Riesgos**/
				String[] arrTasasRiesgosDesde = listaTasasRiesgosDesde.split(",");
				String[] arrTasasRiesgosHasta = listaTasasRiesgosHasta.split(",");
				String[] arrTasasRiesgosTasas = listaTasasRiesgosTasas.split(",");
				
				for(int i=1; i< arrTasasRiesgosDesde.length; i++){
					PymeTasaRiesgo pymeTasaRiesgo = new PymeTasaRiesgo();
					pymeTasaRiesgo.setDesde(Double.parseDouble(arrTasasRiesgosDesde[i]));
					pymeTasaRiesgo.setHasta(Double.parseDouble(arrTasasRiesgosHasta[i]));
					pymeTasaRiesgo.setTasa(Double.parseDouble(arrTasasRiesgosTasas[i]));
					pymeTasaRiesgo.setPymeConfiguracionCoberturaId(configuracion.getConfiguracionCoberturaId());
					pymeTasaRiesgo.setPymeTipoRiesgoId(new BigInteger("1"));	
					pymeTasaRiesgoTransaction.crear(pymeTasaRiesgo);
				}
				
				/**Tasa de Reclamos**/
				String[] arrTasasReclamos = listaTasasReclamos.split(",");
				String[] arrTasasReclamosTasas = listaTasasReclamosTasas.split(",");
				for(int i=1; i< arrTasasReclamos.length; i++){
					PymeTasaRiesgo pymeTasaRiesgo = new PymeTasaRiesgo();
					pymeTasaRiesgo.setTasa(Double.parseDouble(arrTasasReclamosTasas[i]));
					pymeTasaRiesgo.setPymeConfiguracionCoberturaId(configuracion.getConfiguracionCoberturaId());
					pymeTasaRiesgo.setPymeTipoRiesgoId(new BigInteger("2"));
					if(arrTasasReclamos[i].equals("SI"))
						pymeTasaRiesgo.setReclamo(1);
					else
						pymeTasaRiesgo.setReclamo(2);
					pymeTasaRiesgoTransaction.crear(pymeTasaRiesgo);
				}
				
				List<PymeCoberturaTasa> listadoCoberturasTasas=coberturaTasaDAO.buscarPorConfiguracionCoberturaId(configuracion.getConfiguracionCoberturaId());
				for(PymeCoberturaTasa ct:listadoCoberturasTasas){
					pymeCoberturaTasaTransaction.eliminar(ct);
				}
				String[] arrlistaValorMinimo = listaValorMinimo.split(",");
				String[] arrlistaValorMaximo = listaValorMaximo.split(",");
				String[] arrlistaTasa = listaTasa.split(",");
				PymeCoberturaTasa nuevaCoberturaTasa;
				List<PymeCoberturaTasa> listadoTasas=new ArrayList<PymeCoberturaTasa>();
				for(int i=1; i< arrlistaValorMinimo.length; i++){
					nuevaCoberturaTasa=new PymeCoberturaTasa();
					nuevaCoberturaTasa.setConfiguracionCoberturaId(configuracion.getConfiguracionCoberturaId());
					nuevaCoberturaTasa.setValorCoberturaInicial(Float.valueOf(arrlistaValorMinimo[i]));
					nuevaCoberturaTasa.setValorCoberturaFinal(Float.valueOf(arrlistaValorMaximo[i]));
					nuevaCoberturaTasa.setTasa(Float.valueOf(arrlistaTasa[i]));
					pymeCoberturaTasaTransaction.crear(nuevaCoberturaTasa);
					listadoTasas.add(nuevaCoberturaTasa);
				}
				

				JSONArray array = (JSONArray)JSONSerializer.toJSON(arrTextoDeducible);	

				List<PymeTipoDeducibleCobertura> pymeTipoDeducibleList = pymeTipoDeducibleDAO.buscarPorConfiguracionCoberturaId(configuracion.getConfiguracionCoberturaId());
				for(PymeTipoDeducibleCobertura pymeTipoDeducible:pymeTipoDeducibleList){					
					pymeTipoDeducibleCoberturaTransaction.eliminar(pymeTipoDeducible);
				}            	
				boolean bandera = true;
				List<PymeTipoDeducibleCobertura> listadoDeducibles=new ArrayList<PymeTipoDeducibleCobertura>();
				for (Object js:array){	
					JSONObject jsonStr=(JSONObject)JSONSerializer.toJSON(js);
					PymeTipoDeducibleCobertura pymeTipoDeducibleCobertura = new PymeTipoDeducibleCobertura();
					pymeTipoDeducibleCobertura.setTipoDeducibleCoberturaId(jsonStr.getString("tipoDeducibleCoberturaId"));
					pymeTipoDeducibleCobertura.setConfiguracionCoberturaId(configuracion.getConfiguracionCoberturaId());
					pymeTipoDeducibleCobertura.setTipoDeducibleId(new BigInteger(jsonStr.getString("tipoDeducibleId")));
					pymeTipoDeducibleCobertura.setValor(Double.parseDouble(jsonStr.getString("valor"))); 
					if(jsonStr.getString("seguridadAdecuada").equals("1"))
                        pymeTipoDeducibleCobertura.setSeguridadAdecuada(true);
                    else
                    	pymeTipoDeducibleCobertura.setSeguridadAdecuada(false);	
					pymeTipoDeducibleCobertura.setTexto(jsonStr.getString("texto"));

					pymeTipoDeducibleCoberturaTransaction.crear(pymeTipoDeducibleCobertura);
					listadoDeducibles.add(pymeTipoDeducibleCobertura);             	
				}

				List<PymePlanConfiguracion> pymePlanesList = pymePlanConfiguracionDAO.buscarPorConfiguracionId(configuracion.getConfiguracionCoberturaId());
				for(PymePlanConfiguracion pymePlanConfig:pymePlanesList){					
					pymePlanConfiguracionTransaction.eliminar(pymePlanConfig);
				}

				JSONArray arrayPlan = (JSONArray)JSONSerializer.toJSON(arrPlanes);
				for (Object js:arrayPlan){
					JSONObject jsonStr=(JSONObject)JSONSerializer.toJSON(js);
					PymePlanConfiguracion pymePlanConfiguracion = new PymePlanConfiguracion();
					pymePlanConfiguracion.setPlanId(new BigInteger(jsonStr.getString("planId")));
					pymePlanConfiguracion.setValorMaximo(Double.parseDouble(jsonStr.getString("valor")));                    
					pymePlanConfiguracion.setConfiguracionCoberturaId(configuracion.getConfiguracionCoberturaId());
					pymePlanConfiguracionTransaction.crear(pymePlanConfiguracion);
				}
			}

			if(tipoConsulta.equals("eliminar")){
				Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
				
				/*Elimino el texto del deducible*/
				List<PymeTipoDeducibleCobertura> pymeTipoDeducibleList = pymeTipoDeducibleDAO.buscarPorConfiguracionCoberturaId(configuracionCobertura.getConfiguracionCoberturaId());
				for(PymeTipoDeducibleCobertura pymeTipoDeducible:pymeTipoDeducibleList){					
					pymeTipoDeducibleCoberturaTransaction.eliminar(pymeTipoDeducible);
				}

				/*Elimino los planes*/
				List<PymePlanConfiguracion> pymePlanesConfig = pymePlanConfiguracionDAO.buscarPorConfiguracionId(configuracionCobertura.getConfiguracionCoberturaId());
				for(PymePlanConfiguracion pymePlanConfig:pymePlanesConfig){					
					pymePlanConfiguracionTransaction.eliminar(pymePlanConfig);
				}
				
				/*Elimino los rangos de las tasas*/
				List<PymeCoberturaTasa> pymeCoberturaTasaList= coberturaTasaDAO.buscarPorConfiguracionCoberturaId(configuracionCobertura.getConfiguracionCoberturaId());
				for(PymeCoberturaTasa pymeCoberturaTasa:pymeCoberturaTasaList){
					pymeCoberturaTasaTransaction.eliminar(pymeCoberturaTasa);
				}
				PymeConfiguracionCoberturaDAO configDAO=new PymeConfiguracionCoberturaDAO();
				PymeConfiguracionCobertura configuracionActual=configDAO.buscarPorId(configuracionCobertura.getConfiguracionCoberturaId());

				//Creo el log de cambios
				PymeConfiguracionLog nuevoLog=new PymeConfiguracionLog();
				nuevoLog.setConfiguracionOriginalId(configuracionCobertura.getConfiguracionCoberturaId());
				nuevoLog.setGrupoPorProductoId(configuracionActual.getGrupoPorProductoId());
				nuevoLog.setCoberturaPyesID(configuracionActual.getCoberturaPymesId());
				nuevoLog.setCampo("Cobertura eliminada.");
				nuevoLog.setUsuario(usuario.getLogin());
				PymeConfiguracionLogTransaction pymeConfiguracionLogTransaction=new PymeConfiguracionLogTransaction();
				pymeConfiguracionLogTransaction.crear(nuevoLog);
				
				/**Audito el proceso de borrado de cotizaciones**/

				/***TRATAMIENTO DE ERROR***/
				Date codigo = new Date();//EL codigo de error se conforma por los valores de la fecha
				String CodError=codigo.getYear()+""+codigo.getMonth()+""+codigo.getDay()+""+codigo.getHours()+""+codigo.getMinutes()+"-"+codigo.getSeconds();
				/***AUDITORIA auditamos el error para el seguimiento***/
				PymeAuditoriaGeneralTransaction pymeAuditoriaGeneralTransaction = new PymeAuditoriaGeneralTransaction();
				PymeAuditoriaGeneral pymeAuditoriaGeneral = new PymeAuditoriaGeneral();
				pymeAuditoriaGeneral.setTramite(CodError);
				pymeAuditoriaGeneral.setEstado("ELIMINACION COBERTURA");
				pymeAuditoriaGeneral.setProceso("PYMES");
				pymeAuditoriaGeneral.setObjeto("Eliminacion de Cobertura: ConfiguracionCoberturaId="+configuracionCobertura.getConfiguracionCoberturaId()+" GrupoPorProductoId="+configuracionCobertura.getGrupoPorProductoId()
						+" CoberturaPymesId="+configuracionCobertura.getCoberturaPymesId()+" Realizado por usuario: "+usuario.getLogin()+" Nombre: "+usuario.getEntidad().getNombreCompleto());
				try {
					pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
				} catch (Exception e1) {				
					e1.printStackTrace();
				}
				
				/*Elimino el configuracion*/
				pymeConfiguracionCoberturaTransaction.eliminar(configuracionCobertura);
			}

			if(tipoConsulta.equals("encontrarPorPlanesEstructuras")){

				List<PymePlanesPorCoberturas> pymePlanesPorCoberturasList = pymePlanesPorCoberturasDAO.buscarPorPlan(grupoPorProductoId, Integer.parseInt(tipoDeclaracionId));
				for(PymePlanesPorCoberturas tPymePlanesPorCoberturas : pymePlanesPorCoberturasList){
					planeEstructurasJSONObject.put("codigo", tPymePlanesPorCoberturas.getPlanId());
					planeEstructurasJSONObject.put("nombrePlan", tPymePlanesPorCoberturas.getNombre());
					planeEstructurasJSONObject.put("valormaximo", tPymePlanesPorCoberturas.getValormaximo());
					planeEstructurasJSONArray.add(planeEstructurasJSONObject);
				};
				result.put("listaPlanesEstructuras", planeEstructurasJSONArray);
			}

			if(tipoConsulta.equals("traeDeducible")){
				String variableTipoDeducibleId ="";
				String variableTextoDeducible="";
				String variableTextosDeducibles="";
				String variableValoresDeducible ="";
				boolean bandera = true;
				boolean encontro = false;
				if(tipoVariable.equals("true"))
					bandera = true;
				else
					bandera = false;
				
				List<PymeTipoDeducibleCobertura> pymeTipoDeducibleList= new ArrayList<PymeTipoDeducibleCobertura>();
				if(bandera==true){
					pymeTipoDeducibleList = pymeTipoDeducibleDAO.buscarPorConfiguracionCoberturaTipoVariableId(new BigInteger(configuracionCoberturaId),bandera);
				}else{
					pymeTipoDeducibleList = pymeTipoDeducibleDAO.buscarPorConfiguracionCoberturaTipoVariableIdNegativa(new BigInteger(configuracionCoberturaId),bandera);
				}
					
					
					
					for(PymeTipoDeducibleCobertura pymeTipoDeducible:pymeTipoDeducibleList){
						if(pymeTipoDeducible.getSeguridadAdecuada()==bandera){
							variableTipoDeducibleId = variableTipoDeducibleId+pymeTipoDeducible.getTipoDeducibleId()+";";
							variableTextoDeducible = variableTextoDeducible+pymeTipoDeducible.getTexto()+" ";
							variableTextosDeducibles = variableTextosDeducibles+pymeTipoDeducible.getTexto()+";";
							variableValoresDeducible = variableValoresDeducible+pymeTipoDeducible.getValor()+";";
							encontro = true;
						}
				}
				if(encontro){
					configuracionJSONObject.put("idsDeducible", variableTipoDeducibleId);
					configuracionJSONObject.put("texto", variableTextoDeducible);
					configuracionJSONObject.put("valoresDeducible", variableValoresDeducible);
					configuracionJSONObject.put("textosDeducible",variableTextosDeducibles);
					configuracionJSONArray.add(configuracionJSONObject);
				}
				result.put("pymeTipoDeducibleArr", configuracionJSONArray);
			}

			if(tipoConsulta.equals("traeDeducibleGeneral")){
				String variableTipoDeducibleId ="";
				String variableTextoDeducible="";
				String variableTextosDeducibles="";
				String variableValoresDeducible ="";
				boolean bandera = true;
				boolean encontro = false;
				if(tipoVariable.equals("true"))
					bandera = true;
				else
					bandera = false;
				List<PymeTipoDeducibleCobertura> pymeTipoDeducibleList = pymeTipoDeducibleDAO.buscarPorConfiguracionCoberturaId(new BigInteger(configuracionCoberturaId));
				for(PymeTipoDeducibleCobertura pymeTipoDeducible:pymeTipoDeducibleList){
					if(pymeTipoDeducible.getSeguridadAdecuada()==bandera){
						variableTipoDeducibleId = variableTipoDeducibleId+pymeTipoDeducible.getTipoDeducibleId()+";";
						variableTextoDeducible = variableTextoDeducible+pymeTipoDeducible.getTexto()+" ";
						variableTextosDeducibles = variableTextosDeducibles+pymeTipoDeducible.getTexto()+";";
						variableValoresDeducible = variableValoresDeducible+pymeTipoDeducible.getValor()+";";
						encontro = true;
					}
				}
				if(encontro){
					configuracionJSONObject.put("idsDeducible", variableTipoDeducibleId);
					configuracionJSONObject.put("texto", variableTextoDeducible);
					configuracionJSONObject.put("valoresDeducible", variableValoresDeducible);
					configuracionJSONObject.put("textosDeducible",variableTextosDeducibles);
					configuracionJSONArray.add(configuracionJSONObject);
					
				}
				result.put("pymeTipoDeducibleArr", configuracionJSONArray);
			}
			
			if(tipoConsulta.equals("traeDeducibleGeneralEspecifico")){
				String variableTipoDeducibleId ="";
				String variableTextoDeducible="";
				String variableTextosDeducibles="";
				String variableValoresDeducible ="";
				String nombreDeducible="";
				boolean bandera = true;
				boolean encontro = false;
				if(tipoVariable.equals("true"))
					bandera = true;
				else
					bandera = false;
				List<PymeTipoDeducibleCobertura> pymeTipoDeducibleList = pymeTipoDeducibleDAO.buscarPorConfiguracionCoberturaId(new BigInteger(configuracionCoberturaId));
				for(PymeTipoDeducibleCobertura pymeTipoDeducible:pymeTipoDeducibleList){
					if(pymeTipoDeducible.getSeguridadAdecuada()==bandera){
						variableTipoDeducibleId = variableTipoDeducibleId+pymeTipoDeducible.getTipoDeducibleId()+";";
						variableTextoDeducible = variableTextoDeducible+pymeTipoDeducible.getTexto()+" ";
						variableTextosDeducibles = variableTextosDeducibles+pymeTipoDeducible.getTexto()+";";
						variableValoresDeducible = variableValoresDeducible+pymeTipoDeducible.getValor()+";";
						encontro = true;
					}
				}
				
				try{
					PymeCoberturaConfiguradaDAO Configurada= new PymeCoberturaConfiguradaDAO();
					PymeCoberturaConfigurada pymeConfigurada= Configurada.buscarPorId(new BigInteger(configuracionCoberturaId)).get(0);
					nombreDeducible=pymeConfigurada.getNombre();
				}catch(Exception e){
					e.printStackTrace();
				}
				if(encontro){
					configuracionJSONObject.put("idsDeducible", variableTipoDeducibleId);
					configuracionJSONObject.put("texto", variableTextoDeducible);
					configuracionJSONObject.put("valoresDeducible", variableValoresDeducible);
					configuracionJSONObject.put("textosDeducible",variableTextosDeducibles);
					configuracionJSONObject.put("nombreDeducible",nombreDeducible);
					configuracionJSONArray.add(configuracionJSONObject);
				}
				result.put("pymeTipoDeducibleArr", configuracionJSONArray);
			}

			if(tipoConsulta.equals("encontrarPorPlanesContenidos")){

				List<PymePlanesPorCoberturas> pymePlanesPorCoberturasList = pymePlanesPorCoberturasDAO.buscarPorPlan(grupoPorProductoId, Integer.parseInt(tipoDeclaracionId));
				for(PymePlanesPorCoberturas tPymePlanesPorCoberturas : pymePlanesPorCoberturasList){
					planeContenidosJSONObject.put("codigo", tPymePlanesPorCoberturas.getPlanId());
					planeContenidosJSONObject.put("nombrePlan", tPymePlanesPorCoberturas.getNombre());
					planeContenidosJSONObject.put("valormaximo", tPymePlanesPorCoberturas.getValormaximo());
					planeContenidosJSONArray.add(planeContenidosJSONObject);
				};
				result.put("listaPlanesContenidos", planeContenidosJSONArray);
			}

			if(tipoConsulta.equals("encontrarPorPlanesGenerales")){
				List<PymePlanesPorCoberturas> pymePlanesPorCoberturasList = pymePlanesPorCoberturasDAO.buscarPorGeneral(grupoPorProductoId, Integer.parseInt(tipoDeclaracionId),configuracionCoberturaId);
				for(PymePlanesPorCoberturas tPymePlanesPorCoberturas : pymePlanesPorCoberturasList){
					planeGeneralesJSONObject.put("codigo", tPymePlanesPorCoberturas.getPlanId());
					planeGeneralesJSONObject.put("nombrePlan", tPymePlanesPorCoberturas.getNombre());
					planeGeneralesJSONObject.put("valormaximo", tPymePlanesPorCoberturas.getValormaximo());
					planeGeneralesJSONArray.add(planeGeneralesJSONObject);
				};
				result.put("listaPlanesGenerales", planeGeneralesJSONArray);
			}

			if(tipoConsulta.equals("encontrarConfiguracionesPorProductoPlanId")){ 
				List<PymePlanesPorCoberturas> pymePlanesPorCoberturasList = pymePlanesPorCoberturasDAO.buscarPorProductoId(grupoPorProductoId);				
				for(PymePlanesPorCoberturas configuracion:pymePlanesPorCoberturasList){
					configuracionJSONObject.put("configuracionCoberturaId", configuracion.getConfiguracionCoberturaId());
					configuracionJSONObject.put("coberturaNombre", configuracion.getNombrePlan());
					configuracionJSONObject.put("nombrePlan", configuracion.getNombre());
					configuracionJSONObject.put("planId", configuracion.getPlanId());
					configuracionJSONObject.put("coberturaId", configuracion.getCobeturaPymesId());
					configuracionJSONObject.put("grupoNombre", configuracion.getNombreComercial());
					if(configuracion.getTipoDeclaracion()==1)
						configuracionJSONObject.put("tipoDeclaracionNombre", "GENERAL");
					else if(configuracion.getTipoDeclaracion()==2)
						configuracionJSONObject.put("tipoDeclaracionNombre", "POR DIRECCIÓN");
					else if(configuracion.getTipoDeclaracion()==3)
						configuracionJSONObject.put("tipoDeclaracionNombre", "PRINCIPAL DE ESTRUCTURAS");
					else if(configuracion.getTipoDeclaracion()==4)
						configuracionJSONObject.put("tipoDeclaracionNombre", "PRINCIPAL DE CONTENIDOS");
					configuracionJSONObject.put("tipoDeclaracion", configuracion.getTipoDeclaracion());
					configuracionJSONObject.put("tipoTasa", configuracion.getTipoTasa());
					configuracionJSONObject.put("textoDeducible", configuracion.getTextoDeducible());					
					configuracionJSONObject.put("idsDeducible", configuracion.getIdsDeducible());
					configuracionJSONObject.put("valoresDeducible", configuracion.getValoresDeducible());
					configuracionJSONObject.put("textosDeducible", configuracion.getTextosDeducible());
					configuracionJSONObject.put("tasa", configuracion.getTasa());
					configuracionJSONObject.put("tasaEstructuras", configuracion.getTasaEstructuras());
					configuracionJSONObject.put("valorAsegurado", configuracion.getValormaximo());					
					configuracionJSONObject.put("dependeValor", configuracion.getDepende());
					configuracionJSONObject.put("tipoCoberturaId", configuracion.getTipoCoberturaId());
					configuracionJSONObject.put("ramo", configuracion.getRamo().toString());
					configuracionJSONObject.put("ramoNombre", configuracion.getRamoNombre());
					configuracionJSONArray.add(configuracionJSONObject);
				}
				result.put("listadoConfiguraciones", configuracionJSONArray);
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
