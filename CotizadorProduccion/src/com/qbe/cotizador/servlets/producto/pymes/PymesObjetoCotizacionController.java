package com.qbe.cotizador.servlets.producto.pymes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URLEncoder;
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
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.cotizacion.TipoObjetoDAO;
import com.qbe.cotizador.dao.entidad.CantonDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.entidad.UsuarioDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeAsistenciaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeCoberturaCotizacionValorDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeConfiguracionCoberturaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeDerechoEmisionDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeInspectorProvinciaDAO;
import com.qbe.cotizador.dao.producto.pymes.NotificacionDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeObjetoCotizacionCoberturaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeObjetoCotizacionDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeParametroPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeParametroXGrupoPorProductoDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeParametroXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeRamoCotizacionDAO;
import com.qbe.cotizador.dao.seguridad.TipoVariableDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.Canton;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Provincia;
import com.qbe.cotizador.model.PymeAsistencia;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.PymeCoberturaCotizacionValor;
import com.qbe.cotizador.model.PymeConfiguracionCobertura;
import com.qbe.cotizador.model.PymeDerechoEmision;
import com.qbe.cotizador.model.PymeInspectorProvincia;
import com.qbe.cotizador.model.Notificacion;
import com.qbe.cotizador.model.PymeObjetoCotizacion;
import com.qbe.cotizador.model.PymeObjetoCotizacionCobertura;
import com.qbe.cotizador.model.PymeParametroPuntoVenta;
import com.qbe.cotizador.model.PymeParametroXGrupoPorProducto;
import com.qbe.cotizador.model.PymeParametroXPuntoVenta;
import com.qbe.cotizador.model.PymeRamoCotizacion;
import com.qbe.cotizador.model.Rol;
import com.qbe.cotizador.model.TipoVariable;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.model.UsuarioRol;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.servlets.producto.agricola.AgriSucreNotificacionErrores;
import com.qbe.cotizador.transaction.cotizacion.CotizacionDetalleTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeObjetoCotizacionCoberturaTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeObjetoCotizacionTransaction;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Servlet implementation class ObjetoPymesController
 */

@WebServlet("/PymesObjetoCotizacionController")
public class PymesObjetoCotizacionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PymesObjetoCotizacionController() {
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
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
		String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");
		String cotizacionDetalleId = request.getParameter("cotizacionDetalleId") == null ? "" : request.getParameter("cotizacionDetalleId");
		String provinciaId = request.getParameter("provinciaId") == null ? "" : request.getParameter("provinciaId");
		String cantonId = request.getParameter("cantonId") == null ? "" : request.getParameter("cantonId");
		String callePrincipal = request.getParameter("callePrincipal") == null ? "" : request.getParameter("callePrincipal");
		String numeroDireccion = request.getParameter("numeroDireccion") == null ? "" : request.getParameter("numeroDireccion");
		String calleSecundaria = request.getParameter("calleSecundaria") == null ? "" : request.getParameter("calleSecundaria");
		String actividadEconomicaId = request.getParameter("actividadEconomicaId") == null ? "" : request.getParameter("actividadEconomicaId");
		String tipoPredioId = request.getParameter("tipoPredioId") == null ? "" : request.getParameter("tipoPredioId");
		String tieneMasDosAnios = request.getParameter("tieneMasDosAnios") == null ? "" : request.getParameter("tieneMasDosAnios");
		String seguridadAdecuada = request.getParameter("seguridadAdecuada") == null ? "" : request.getParameter("seguridadAdecuada");
		String contabilidadFormal = request.getParameter("contabilidadFormal") == null ? "" : request.getParameter("contabilidadFormal");
		String requiereInspeccion = request.getParameter("requiereInspeccion") == null ? "" : request.getParameter("requiereInspeccion");
		String sector = request.getParameter("sector") == null ? "" : request.getParameter("sector");
		String nombreEdificio = request.getParameter("nombreEdificio") == null ? "" : request.getParameter("nombreEdificio");
		String numeroOficina = request.getParameter("numeroOficina") == null ? "" : request.getParameter("numeroOficina");
		String valorEstructuras = request.getParameter("valorEstructuras") == null ? "" : request.getParameter("valorEstructuras");
		String valorMueblesEnseres = request.getParameter("valorMueblesEnseres") == null ? "" : request.getParameter("valorMueblesEnseres");
		String valorMaquinaria = request.getParameter("valorMaquinaria") == null ? "" : request.getParameter("valorMaquinaria");
		String valorMercaderia = request.getParameter("valorMercaderia") == null ? "" : request.getParameter("valorMercaderia");
		String valorInsumosNoelectronicos = request.getParameter("valorInsumosNoelectronicos") == null ? "" : request.getParameter("valorInsumosNoelectronicos");
		String valorEquipoHerramienta = request.getParameter("valorEquipoHerramienta") == null ? "" : request.getParameter("valorEquipoHerramienta");
		String primaNeta = request.getParameter("primaNeta") == null ? "" : request.getParameter("primaNeta");
		String coberturas = request.getParameter("coberturas") == null ? "" : request.getParameter("coberturas");
		//Parametros usados en la inspeccion
		String tipoConstruccionId = request.getParameter("tipoConstruccionId") == null ? "" : request.getParameter("tipoConstruccionId");
		String tipoOcupacionId = request.getParameter("tipoOcupacionId") == null ? "" : request.getParameter("tipoOcupacionId");
		String numeroTotalPisos = request.getParameter("numeroTotalPisos") == null ? "" : request.getParameter("numeroTotalPisos");
		String anioConstruccion = request.getParameter("anioConstruccion") == null ? "" : request.getParameter("anioConstruccion");
		String extintores = request.getParameter("extintores") == null ? "" : request.getParameter("extintores");
		String detectorHumo = request.getParameter("detectorHumo") == null ? "" : request.getParameter("detectorHumo");
		String sprinklers = request.getParameter("sprinklers") == null ? "" : request.getParameter("sprinklers");
		String alarmaMonitoreada = request.getParameter("alarmaMonitoreada") == null ? "" : request.getParameter("alarmaMonitoreada");
		String guardiania = request.getParameter("guardiania") == null ? "" : request.getParameter("guardiania");
		String otros = request.getParameter("otros") == null ? "" : request.getParameter("otros");
		String latitud = request.getParameter("latitud") == null ? "" : request.getParameter("latitud");
		String longuitud = request.getParameter("longuitud") == null ? "" : request.getParameter("longuitud");
		String registro = request.getParameter("registro") == null ? "" : request.getParameter("registro");
		String estadoInspeccion = request.getParameter("estadoInspeccion") == null ? "" : request.getParameter("estadoInspeccion");
		String planesEstructuras = request.getParameter("planesEstructuras") == null ? "" : request.getParameter("planesEstructuras");
		String valorContenidos = request.getParameter("valorContenidos") == null ? "" : request.getParameter("valorContenidos");
		String valorEstructura = request.getParameter("valorEstructura") == null ? "" : request.getParameter("valorEstructura");
		String planesContenidos = request.getParameter("planesContenidos") == null ? "" : request.getParameter("planesContenidos");
		
		String fechaInspeccion = request.getParameter("fechaInspeccion") == null ? "" : request.getParameter("fechaInspeccion");
		
		String inspectorId = request.getParameter("inspectorId") == null ? "" : request.getParameter("inspectorId");

		String FileName = request.getParameter("FileName")== null ? "" : request.getParameter("FileName");
		
		
		JSONObject result = new JSONObject();
		try{
			JSONArray coberturasJSONArray = new JSONArray();
			JSONObject coberturaJSONObject = new JSONObject();
			JSONArray coberturasDeduciblesJSONArray = new JSONArray();
			JSONObject coberturaDeduciblesJSONObject = new JSONObject();

			CotizacionDAO cotizacionDAO = new CotizacionDAO();
			CotizacionDetalleDAO cotizacionDetalleDAO=new CotizacionDetalleDAO();
			PymeObjetoCotizacionDAO objetoCotizacionDAO=new PymeObjetoCotizacionDAO();
			PymeObjetoCotizacionCoberturaDAO objetoCCDAO=new PymeObjetoCotizacionCoberturaDAO();
			PymeAsistenciaDAO asistenciaDAO=new PymeAsistenciaDAO();
			EstadoDAO estadoDAO=new EstadoDAO();
			PymeConfiguracionCoberturaDAO configuracionCoberturaDAO=new PymeConfiguracionCoberturaDAO();

			PymeObjetoCotizacionTransaction pymeObjetoCotizacionTransaction=new PymeObjetoCotizacionTransaction();
			PymeObjetoCotizacionCoberturaTransaction pymeObjetoCCTransaction=new PymeObjetoCotizacionCoberturaTransaction();
			CotizacionTransaction cotizacionTransaction= new CotizacionTransaction();
			CotizacionDetalleTransaction cotizacionDetalleTransaction=new CotizacionDetalleTransaction();


			Cotizacion cotizacion = new Cotizacion();
			//Permite crear la cotización pyme en el paso 2
			if(tipoConsulta.equalsIgnoreCase("crear"))
			{
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);

				double valorAsegurado=0;				
				//Valido si tiene almenos una dirección con requiere inspección
				int contador=0;
				List<CotizacionDetalle> listadoDetalles=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);
				for(CotizacionDetalle detalleActual:listadoDetalles){
					PymeObjetoCotizacion objetoCotizacion=objetoCotizacionDAO.buscarPorId(new BigInteger(detalleActual.getObjetoId()));
					if(objetoCotizacion!=null){
						if(objetoCotizacion.getRequiereInspeccion()){
							contador++;
						}
					}
					valorAsegurado=valorAsegurado+(objetoCotizacion.getValorContenidos()+objetoCotizacion.getValorEquipoHerramienta()+objetoCotizacion.getValorEstructuras()+objetoCotizacion.getValorInsumosNoelectronicos()+objetoCotizacion.getValorMaquinaria()+objetoCotizacion.getValorMercaderia()+objetoCotizacion.getValorMueblesEnseres());
				}
				if(contador==0)
					throw new Exception("No es posible seguir con la cotización, porque no ha indicado al menos una dirección como dirección de inspección.");
				
				//Valido que la suma de los valores asegurados no pase del valor del producto
				PymeParametroXGrupoPorProductoDAO parametroGPDAO=new PymeParametroXGrupoPorProductoDAO();
				PymeParametroXGrupoPorProducto parametroGP=parametroGPDAO.buscarPorGrupoPorProductoId(cotizacion.getGrupoPorProductoId());
				if(valorAsegurado>parametroGP.getLimiteAsegurado())
						throw new Exception("No es posible seguir con la cotización, porque la suma asegurada de las direcciones es mayor a límite de cobertura del producto.");
				
				//Elimino las cooberturas generales y demás.
				List<PymeObjetoCotizacionCobertura> listado=objetoCCDAO.buscarPorObjetoPymeId(new BigInteger(cotizacionId));
				for(PymeObjetoCotizacionCobertura coberturaActual:listado){
					pymeObjetoCCTransaction.eliminar(coberturaActual);
				}

				//Barro el objeto de coberturas, asistencias y deducibles general.
				PymeObjetoCotizacionCobertura nuevoObjetoCC;
				if(coberturas != null && !coberturas.equals(""))
				{
					JSONArray array=(JSONArray)JSONSerializer.toJSON(coberturas);
					for(Object js:array){
						JSONObject jsonStr = (JSONObject)JSONSerializer.toJSON(js);
						nuevoObjetoCC=new PymeObjetoCotizacionCobertura();
						nuevoObjetoCC.setObjetoPymesId(new BigInteger(cotizacion.getId()));
						nuevoObjetoCC.setObjetoOrigenId(new BigInteger(jsonStr.getString("configuracionCoberturaId")));
						nuevoObjetoCC.setTasaSugerida(Double.parseDouble(jsonStr.getString("tasaSugerida")));
						nuevoObjetoCC.setTasaIngresada(Double.parseDouble(jsonStr.getString("tasaIngresada")));
						nuevoObjetoCC.setValorIngresado(Double.parseDouble(jsonStr.getString("valorIngresado")));
						nuevoObjetoCC.setPrimaCalculada(Double.parseDouble(jsonStr.getString("primaCalculada")));
						nuevoObjetoCC.setTipoOrigen(jsonStr.getString("tipoOrigen"));
						nuevoObjetoCC.setPlanId(jsonStr.getString("planGeneral"));
						nuevoObjetoCC.setTextoCompletoDeducible(jsonStr.getString("textoDeducible"));
						nuevoObjetoCC.setIdsDeducible(jsonStr.getString("idsDeducible"));
						nuevoObjetoCC.setValoresDeducible(jsonStr.getString("valoresDeducible"));
						nuevoObjetoCC.setTextosDeducible(jsonStr.getString("textosDeducible"));
						pymeObjetoCCTransaction.crear(nuevoObjetoCC);
					}
				}
				cotizacion.setEtapaWizard(2);
				cotizacionTransaction.editar(cotizacion);
			}

			
			if(tipoConsulta.equalsIgnoreCase("obtenerMaximoAseguradoIncendio")){
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				List<CotizacionDetalle> cotizacionesDetalle = cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);								
				ArrayList<Double> valoresAsegurados=new ArrayList<Double>();
				for(CotizacionDetalle cotizacionDetalle : cotizacionesDetalle)
				{			
					PymeObjetoCotizacion pymeObjetoCotizacion =  objetoCotizacionDAO.buscarPorId(new BigInteger(cotizacionDetalle.getObjetoId()));
					valoresAsegurados.add(pymeObjetoCotizacion.getValorEstructuras()+pymeObjetoCotizacion.getValorMueblesEnseres()+pymeObjetoCotizacion.getValorMaquinaria()+pymeObjetoCotizacion.getValorMercaderia()+pymeObjetoCotizacion.getValorInsumosNoelectronicos()+pymeObjetoCotizacion.getValorEquipoHerramienta()+pymeObjetoCotizacion.getValorContenidos());	
				}
				Double valorMaximo=Collections.max(valoresAsegurados);
				result.put("valorAsegurado", valorMaximo);
			}

			if(tipoConsulta.equalsIgnoreCase("obtenerResumenValores"))
			{
				VariableSistemaDAO variableDAO = new VariableSistemaDAO();
				cotizacion = cotizacionDAO.buscarPorId(request.getParameter("cotizacionId"));
				List<CotizacionDetalle> cotizacionesDetalle = cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);								

				double valorPrima = 0;
				double valorAsistencia = 0;
				double valorAsegurado = 0;
				double valorFinalPrima = 0;

				List<PymeObjetoCotizacionCobertura> cotizacionCoberturasGenerales =  objetoCCDAO.buscarPorObjetoPymeId(new BigInteger(cotizacion.getId()));
				for(PymeObjetoCotizacionCobertura coberturaActual: cotizacionCoberturasGenerales){
					if(coberturaActual.getTipoOrigen().equals("ADICIONALES") || coberturaActual.getTipoOrigen().equals("GENERAL")){
						valorPrima = valorPrima + coberturaActual.getPrimaCalculada();
						valorPrima = Math.rint(valorPrima*100)/100;
					}
					else if(coberturaActual.getTipoOrigen().equals("ASISTENCIA")){
						PymeAsistencia asistencia=asistenciaDAO.buscarPorId(coberturaActual.getObjetoOrigenId());
						if(asistencia.getEsPredeterminada()){
							valorAsistencia = valorAsistencia + coberturaActual.getPrimaCalculada();
							valorAsistencia = Math.rint(valorAsistencia*100)/100;
						}
						else{
							valorPrima = valorPrima + coberturaActual.getPrimaCalculada();
							valorPrima = Math.rint(valorPrima*100)/100;
						}
					}
				}


				Boolean primeraVez=false;  
				//Calculo los valores solo del detalle, el calculo e la prime depende si es multiriesgo o no
				PymeParametroXPuntoVentaDAO parametroPVDAO=new PymeParametroXPuntoVentaDAO();
				PymeParametroXPuntoVenta parametroPV=parametroPVDAO.obtenerPorAgenteId(cotizacion.getAgenteId());
				for(CotizacionDetalle cotizacionDetalle : cotizacionesDetalle)
				{			
					double primaActual=cotizacionDetalle.getPrimaNetaItem();

					//Sumo el valor de las asistencias a la cobertura de incendio
					if(!primeraVez){
						List<PymeObjetoCotizacionCobertura> cotizacionCoberturas =  objetoCCDAO.buscarPorObjetoPymeId(new BigInteger(cotizacionDetalle.getObjetoId()));

						for(PymeObjetoCotizacionCobertura coberturaActual : cotizacionCoberturas)
						{
							PymeConfiguracionCobertura configuracionCobertura=configuracionCoberturaDAO.buscarPorId(coberturaActual.getObjetoOrigenId());
							if(configuracionCobertura.getCoberturaPymesId().equals(new BigInteger("21"))){
								//Si deseo cambiar el valor de la cobertura
								if(!primeraVez){
									double primaCalculada= coberturaActual.getPrimaCalculada();
									coberturaActual.setPrimaCalculada(primaCalculada+valorAsistencia);
									primaActual=primaActual+valorAsistencia;
									primeraVez=true;
								}
							}
						}
					}
					valorPrima = valorPrima + primaActual;
					valorAsegurado = valorAsegurado+ cotizacionDetalle.getSumaAseguradaItem();
					valorPrima = Math.rint(valorPrima*100)/100;
					valorAsegurado = Math.rint(valorAsegurado*100)/100;
				}

				valorFinalPrima = valorPrima;
				//Deterino si el valor de la prima es menor al mínimo del producto
				PymeParametroXGrupoPorProductoDAO pymeParametroXGrupoPorProductoDAO=new PymeParametroXGrupoPorProductoDAO();
				PymeParametroXGrupoPorProducto PXGP=pymeParametroXGrupoPorProductoDAO.buscarPorGrupoPorProductoId(cotizacion.getGrupoPorProductoId());
				if(PXGP.getPrimaMinima()!=0){
					if(valorFinalPrima<PXGP.getPrimaMinima()){
						valorFinalPrima=PXGP.getPrimaMinima();
						valorPrima=PXGP.getPrimaMinima();
					}
				}
				result.put("porcentajeDescuento", 0.0);  
				TipoVariableDAO tipoVariableDao = new TipoVariableDAO();
				TipoVariable tipoVariable = tipoVariableDao.buscarPorId("3");
				List<VariableSistema> variablesistema = variableDAO.buscarTipoVariable(tipoVariable);
				double valorDerechosEmision = 0;
				double valorSeguroCampesino = 0;
				double valorSuperBancos = 0;
				double valorIva= 0;
				double valorSubTotal = 0;
				double valorTotal = 0;
				double valorBase = 0;
				result.put("valorPrima", valorFinalPrima);
				result.put("valorAsegurado", valorAsegurado);

				cotizacion.setPrimaNetaTotal(String.valueOf(valorFinalPrima));
				cotizacion.setSumaAseguradaTotal(valorAsegurado);

				for(VariableSistema variable : variablesistema) {
					if(variable.getNombre().equals("DERECHOS_EMISION")){
						//Determino si el el punto de venta es multirisgo o no
						if(parametroPV.getParametroPuntoVentaId()!=null){
							if(parametroPV.getTieneMultiriesgo()){
								//Obtengo el valor de la variable en base a la tabla.
								PymeDerechoEmisionDAO derechoEmisionDAO=new PymeDerechoEmisionDAO();
								List<PymeDerechoEmision> listadoDerechos=derechoEmisionDAO.buscarIntervalo(valorFinalPrima);
								double valorDerechoCalculado=0;
								if(listadoDerechos.size()!=0){
									valorDerechoCalculado = listadoDerechos.get(0).getValorDerechoEmision();
								}
								else{
									valorDerechoCalculado = Double.parseDouble(variable.getValor());
								}
								valorDerechosEmision = valorDerechoCalculado;
								valorBase=valorPrima+valorDerechosEmision;
								cotizacion.setImpDerechoEmision(valorDerechosEmision);
								result.put("valorDerechosEmision", valorDerechosEmision);
							}
							else{
								PymeRamoCotizacionDAO cotizacionRamoDAO=new PymeRamoCotizacionDAO();
								PymeCoberturaCotizacionValorDAO coberturaCotizacionValorDAO=new PymeCoberturaCotizacionValorDAO();
								List<PymeRamoCotizacion> ramosCotizados=cotizacionRamoDAO.buscarPorCotizacionId(new BigInteger(cotizacion.getId()));
								valorDerechosEmision = 0;
								for(PymeRamoCotizacion ramoActual:ramosCotizados){
									double valorPrimaCalculada=0;
									List<PymeCoberturaCotizacionValor> coberturasCotizadas=coberturaCotizacionValorDAO.buscarPorCotizacionRamoId(new BigInteger(cotizacion.getId()), ramoActual.getRamoId());
									for(PymeCoberturaCotizacionValor cobertuaCVActual:coberturasCotizadas){
										valorPrimaCalculada=valorPrimaCalculada+cobertuaCVActual.getPrimaCalculada();
									}
									if(ramoActual.getRamoId().toString().equals("7208961")){
										List<PymeAsistencia> asistencias=asistenciaDAO.buscarGrupoPorProductoId(cotizacion.getGrupoPorProductoId());
										for(PymeAsistencia asientenciActual:asistencias){
											if(asientenciActual.getEsPredeterminada())
												valorPrimaCalculada=valorPrimaCalculada+asientenciActual.getValor();
										}
									}
									PymeDerechoEmisionDAO derechoEmisionDAO=new PymeDerechoEmisionDAO();
									List<PymeDerechoEmision> listadoDerechos=derechoEmisionDAO.buscarIntervalo(valorPrimaCalculada);
									double valorDerechoCalculado=0;
									if(listadoDerechos.size()!=0){
										valorDerechoCalculado = listadoDerechos.get(0).getValorDerechoEmision();
									}
									else{
										valorDerechoCalculado = Double.parseDouble(variable.getValor());
									}
									valorDerechosEmision = valorDerechosEmision + valorDerechoCalculado;
								}
								valorBase=valorPrima+valorDerechosEmision;
								cotizacion.setImpDerechoEmision(valorDerechosEmision);
								result.put("valorDerechosEmision", valorDerechosEmision);
							}
						}
						else{
							PymeRamoCotizacionDAO cotizacionRamoDAO=new PymeRamoCotizacionDAO();
							PymeCoberturaCotizacionValorDAO coberturaCotizacionValorDAO=new PymeCoberturaCotizacionValorDAO();
							List<PymeRamoCotizacion> ramosCotizados=cotizacionRamoDAO.buscarPorCotizacionId(new BigInteger(cotizacion.getId()));
							valorDerechosEmision = 0;
							for(PymeRamoCotizacion ramoActual:ramosCotizados){
								double valorPrimaCalculada=0;
								List<PymeCoberturaCotizacionValor> coberturasCotizadas=coberturaCotizacionValorDAO.buscarPorCotizacionRamoId(new BigInteger(cotizacion.getId()), ramoActual.getRamoId());
								for(PymeCoberturaCotizacionValor cobertuaCVActual:coberturasCotizadas){
									valorPrimaCalculada=valorPrimaCalculada+cobertuaCVActual.getPrimaCalculada();
								}
								if(ramoActual.getRamoId().toString().equals("7208961")){
									List<PymeAsistencia> asistencias=asistenciaDAO.buscarGrupoPorProductoId(cotizacion.getGrupoPorProductoId());
									for(PymeAsistencia asientenciActual:asistencias){
										if(asientenciActual.getEsPredeterminada())
											valorPrimaCalculada=valorPrimaCalculada+asientenciActual.getValor();
									}
								}
								PymeDerechoEmisionDAO derechoEmisionDAO=new PymeDerechoEmisionDAO();
								List<PymeDerechoEmision> listadoDerechos=derechoEmisionDAO.buscarIntervalo(valorPrimaCalculada);
								double valorDerechoCalculado=0;
								if(listadoDerechos.size()!=0){
									valorDerechoCalculado = listadoDerechos.get(0).getValorDerechoEmision();
								}
								else{
									valorDerechoCalculado = Double.parseDouble(variable.getValor());
								}
								valorDerechosEmision = valorDerechosEmision + valorDerechoCalculado;
							}
							valorBase=valorPrima+valorDerechosEmision;
							cotizacion.setImpDerechoEmision(valorDerechosEmision);
							result.put("valorDerechosEmision", valorDerechosEmision);
						}

					}
					else if(variable.getNombre().equals("SEGURO_CAMPESINO")){
						valorSeguroCampesino = Math.rint(Double.parseDouble(variable.getValor())*valorFinalPrima/100*100)/100;
						valorBase = valorBase + valorSeguroCampesino;
						cotizacion.setImpSeguroCampesino(valorSeguroCampesino);
						result.put("valorSeguroCampesino", valorSeguroCampesino);
					}
					else if(variable.getNombre().equals("SUPER_DE_BANCOS")){
						valorSuperBancos = Math.rint(Double.parseDouble(variable.getValor())*valorFinalPrima/100*100)/100;
						result.put("valorSuperBancos", valorSuperBancos);
						cotizacion.setImpSuperBancos(valorSuperBancos);
						valorBase = valorBase + valorSuperBancos;

					}

					else if(variable.getNombre().equals("SUBTOTAL")){
						valorSubTotal = Math.rint(valorBase*100)/100;
						result.put("valorSubTotal", valorSubTotal);
					}
					else if(variable.getNombre().equals("IVA")){
						double respuesta [] = {0,0};
						double valorIVA = 0.0;
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
								valorIVATotal = valorIva;
							}	
						}
						valorIva=valorIVATotal;
						cotizacion.setImpIva(valorIva);
						result.put("valorIva", valorIva);
					}

				}
				valorTotal = Math.rint((valorBase+valorIva)*100)/100;
				cotizacion.setValorDescuento(0);
				cotizacion.setTotalFactura(valorTotal);
				result.put("valorTotal", valorTotal);
				cotizacionTransaction.editar(cotizacion);
			}


			if(tipoConsulta.equalsIgnoreCase("crearDireccion"))
			{			
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();				


				CotizacionDetalle nuevoCotizacionDetalle=new CotizacionDetalle(); 
				PymeObjetoCotizacion nuevoPymeObjetoCotizacion=new PymeObjetoCotizacion();

				//Si existe una cotización detalle id elimino las coberturas configurada.
				if(cotizacionDetalleId != null && !cotizacionDetalleId.equals(""))
				{
					nuevoCotizacionDetalle = cotizacionDetalleDAO.buscarPorId(cotizacionDetalleId);
					nuevoPymeObjetoCotizacion=objetoCotizacionDAO.buscarPorId(new BigInteger(nuevoCotizacionDetalle.getObjetoId()));


					List<PymeObjetoCotizacionCobertura> listado=objetoCCDAO.buscarPorObjetoPymeId(nuevoPymeObjetoCotizacion.getObjetoPymesId());

					pymeObjetoCotizacionTransaction.eliminar(nuevoPymeObjetoCotizacion);
					for(PymeObjetoCotizacionCobertura coberturaActual:listado){
						pymeObjetoCCTransaction.eliminar(coberturaActual);
					}
				}

				//Creo el objeto pyme
				if(callePrincipal != null && !callePrincipal.equals(""))
					nuevoPymeObjetoCotizacion.setCallePrincipal(callePrincipal);

				if(numeroDireccion != null && !numeroDireccion.equals(""))
					nuevoPymeObjetoCotizacion.setNumeroDireccion(numeroDireccion);

				if(calleSecundaria != null && !calleSecundaria.equals(""))
					nuevoPymeObjetoCotizacion.setCalleSecundaria(calleSecundaria);

				if(nombreEdificio != null && !nombreEdificio.equals(""))
					nuevoPymeObjetoCotizacion.setNombreEdificio(nombreEdificio);

				if(numeroOficina != null && !numeroOficina.equals(""))
					nuevoPymeObjetoCotizacion.setNumeroOficina(numeroOficina);

				if(sector != null && !sector.equals(""))
					nuevoPymeObjetoCotizacion.setSector(sector);

				if(provinciaId != null && !provinciaId.equals(""))
					nuevoPymeObjetoCotizacion.setProvinciaId(Integer.parseInt(provinciaId));

				if(tipoConstruccionId  != null && !tipoConstruccionId .equals(""))
					nuevoPymeObjetoCotizacion.setTipoConstruccionId(tipoConstruccionId);

				if(numeroTotalPisos  != null && !numeroTotalPisos .equals(""))
					nuevoPymeObjetoCotizacion.setNumeroTotalPisos(Integer.parseInt(numeroTotalPisos));

				if(anioConstruccion  != null && !anioConstruccion .equals(""))
					nuevoPymeObjetoCotizacion.setAnioConstruccion(Integer.parseInt(anioConstruccion));
				
				//ciudad cambio de tipo de dato
				if(cantonId != null && !cantonId.equals(""))
					nuevoPymeObjetoCotizacion.setCiudadId(new BigInteger(cantonId));

				if(actividadEconomicaId != null && !actividadEconomicaId.equals(""))
					nuevoPymeObjetoCotizacion.setActividadEconomicaId(new BigInteger(actividadEconomicaId));
				
				if(tipoPredioId != null && !tipoPredioId.equals(""))
					nuevoPymeObjetoCotizacion.setTipoPredioId(tipoPredioId);
				if(planesEstructuras != null && !planesEstructuras.equals(""))
					nuevoPymeObjetoCotizacion.setPlanEstructuraId(planesEstructuras);
				if(planesContenidos != null && !planesContenidos.equals(""))
					nuevoPymeObjetoCotizacion.setPlanContenidosId(planesContenidos);

				if(contabilidadFormal != null && !contabilidadFormal.equals(""))
					nuevoPymeObjetoCotizacion.setContabilidadFormal(Boolean.parseBoolean(contabilidadFormal));
				
				if(seguridadAdecuada != null && !seguridadAdecuada.equals(""))
					nuevoPymeObjetoCotizacion.setSeguridadAdecuada(Boolean.parseBoolean(seguridadAdecuada));

				if(requiereInspeccion != null && !requiereInspeccion.equals(""))
					nuevoPymeObjetoCotizacion.setRequiereInspeccion(Boolean.parseBoolean(requiereInspeccion));

				if(tieneMasDosAnios != null && !tieneMasDosAnios.equals(""))
					nuevoPymeObjetoCotizacion.setTieneMasDosAnio(Boolean.parseBoolean(tieneMasDosAnios));

				if(valorEstructuras != null && !valorEstructuras.equals(""))
					nuevoPymeObjetoCotizacion.setValorEstructuras(Double.parseDouble(valorEstructuras));

				if(valorMueblesEnseres != null && !valorMueblesEnseres.equals(""))
					nuevoPymeObjetoCotizacion.setValorMueblesEnseres(Double.parseDouble(valorMueblesEnseres));

				if(valorMaquinaria != null && !valorMaquinaria.equals(""))
					nuevoPymeObjetoCotizacion.setValorMaquinaria(Double.parseDouble(valorMaquinaria));

				if(valorMercaderia != null && !valorMercaderia.equals(""))
					nuevoPymeObjetoCotizacion.setValorMercaderia(Double.parseDouble(valorMercaderia));

				if(valorInsumosNoelectronicos != null && !valorInsumosNoelectronicos.equals(""))
					nuevoPymeObjetoCotizacion.setValorInsumosNoelectronicos(Double.parseDouble(valorInsumosNoelectronicos));

				if(valorEquipoHerramienta != null && !valorEquipoHerramienta.equals(""))
					nuevoPymeObjetoCotizacion.setValorEquipoHerramienta(Double.parseDouble(valorEquipoHerramienta));
				if(valorContenidos != null && !valorContenidos.equals(""))
					nuevoPymeObjetoCotizacion.setValorContenidos(Double.parseDouble(valorContenidos));

				double sumaAsegurada=nuevoPymeObjetoCotizacion.getValorContenidos()+nuevoPymeObjetoCotizacion.getValorEstructuras()+nuevoPymeObjetoCotizacion.getValorMueblesEnseres()+nuevoPymeObjetoCotizacion.getValorMaquinaria()+nuevoPymeObjetoCotizacion.getValorMercaderia()+nuevoPymeObjetoCotizacion.getValorInsumosNoelectronicos()+nuevoPymeObjetoCotizacion.getValorEquipoHerramienta();
				try
				{
					nuevoPymeObjetoCotizacion=pymeObjetoCotizacionTransaction.crear(nuevoPymeObjetoCotizacion);
				}
				catch(Exception ex){
					/***TRATAMIENTO DE ERROR***/
					Date codigo = new Date();//EL codigo de error se conforma por los valores de la fecha
					String CodError=codigo.getYear()+""+codigo.getMonth()+""+codigo.getDay()+""+codigo.getHours()+""+codigo.getMinutes()+"-"+codigo.getSeconds();
					/***AUDITORIA auditamos el error para el seguimiento***/
					PymeAuditoriaGeneralTransaction pymeAuditoriaGeneralTransaction = new PymeAuditoriaGeneralTransaction();
					PymeAuditoriaGeneral pymeAuditoriaGeneral = new PymeAuditoriaGeneral();
					pymeAuditoriaGeneral.setTramite(CodError);
					pymeAuditoriaGeneral.setEstado("ERROR_PYMES");
					pymeAuditoriaGeneral.setProceso("PYMES");
					pymeAuditoriaGeneral.setObjeto(ex.getMessage());
					try {
						pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
					} catch (Exception e1) {				
						e1.printStackTrace();
					}
					/***RESPUESTA A INTERFAZ***/
					result.put("success", Boolean.FALSE);
					result.put("error", " "+ex.getMessage()+" || Error código: "+CodError);
					result.put("ex", ex.getMessage());
					response.setContentType("application/json; charset=ISO-8859-1"); 
					result.write(response.getWriter());
					ex.printStackTrace();
				}

				if(nuevoPymeObjetoCotizacion==null)
				{
					result.put("success", Boolean.FALSE);
					result.put("error", "No se grabó correctamente la dirección");
					response.setContentType("application/json; charset=ISO-8859-1"); 
					result.write(response.getWriter());
					return;
				}
				if(nuevoPymeObjetoCotizacion.getObjetoPymesId()==null)
				{
					result.put("success", Boolean.FALSE);
					result.put("error", "No se grabó correctamente la dirección");
					response.setContentType("application/json; charset=ISO-8859-1"); 
					result.write(response.getWriter());
					return;
				}
				//Creo las coberturas
				PymeObjetoCotizacionCobertura nuevoObjetoCC;
				
				//prima neta
				double valorPrima=0;
				if(coberturas != null && !coberturas.equals(""))
				{
					JSONArray array=(JSONArray)JSONSerializer.toJSON(coberturas);
					for(Object js:array){
						JSONObject jsonStr = (JSONObject)JSONSerializer.toJSON(js);
						nuevoObjetoCC=new PymeObjetoCotizacionCobertura();
						nuevoObjetoCC.setObjetoPymesId(nuevoPymeObjetoCotizacion.getObjetoPymesId());
						nuevoObjetoCC.setObjetoOrigenId(new BigInteger(jsonStr.getString("configuracionCoberturaId")));
						nuevoObjetoCC.setTasaSugerida(Double.parseDouble(jsonStr.getString("tasaSugerida")));
						nuevoObjetoCC.setTasaIngresada(Double.parseDouble(jsonStr.getString("tasaIngresada")));
						nuevoObjetoCC.setValorIngresado(Double.parseDouble(jsonStr.getString("valorIngresado")));
						nuevoObjetoCC.setPrimaCalculada(Double.parseDouble(jsonStr.getString("primaCalculada")));
						nuevoObjetoCC.setTipoOrigen(jsonStr.getString("tipoOrigen"));
						nuevoObjetoCC.setPlanId(jsonStr.getString("planGeneral"));
						nuevoObjetoCC.setTextoCompletoDeducible(jsonStr.getString("textoDeducible"));
						nuevoObjetoCC.setIdsDeducible(jsonStr.getString("idsDeducible"));
						nuevoObjetoCC.setValoresDeducible(jsonStr.getString("valoresDeducible"));
						nuevoObjetoCC.setTextosDeducible(jsonStr.getString("textosDeducible"));
						nuevoObjetoCC.setValorIngresado2(Double.parseDouble(jsonStr.getString("valorAdicional")));
						valorPrima=valorPrima+nuevoObjetoCC.getPrimaCalculada();
						pymeObjetoCCTransaction.crear(nuevoObjetoCC);
					}
				}

				//Creo la cotización detalle
				if(cotizacionDetalleId == null || cotizacionDetalleId.equals(""))
				{
					nuevoCotizacionDetalle.setCotizacion(cotizacion);
					nuevoCotizacionDetalle.setNecesitaInspeccion(false);
					nuevoCotizacionDetalle.setTipoObjetoId(tipoObjetoDAO.buscarPorNombre("PYMES").getId());
					nuevoCotizacionDetalle.setObjetoId(String.valueOf(nuevoPymeObjetoCotizacion.getObjetoPymesId()));
					nuevoCotizacionDetalle.setSumaAseguradaItem(sumaAsegurada);
					nuevoCotizacionDetalle.setPrimaNetaItem(valorPrima);
					nuevoCotizacionDetalle=cotizacionDetalleTransaction.crear(nuevoCotizacionDetalle);
				}
				else
				{
					nuevoCotizacionDetalle.setObjetoId(String.valueOf(nuevoPymeObjetoCotizacion.getObjetoPymesId()));
					nuevoCotizacionDetalle.setSumaAseguradaItem(sumaAsegurada);
					nuevoCotizacionDetalle.setPrimaNetaItem(valorPrima);
					cotizacionDetalleTransaction.editar(nuevoCotizacionDetalle);
				}
				
				//Cambio de etapa al wizard
				cotizacion.setEtapaWizard(2);
				cotizacion=cotizacionTransaction.editar(cotizacion);
				
				result.put("cotizacionDetalleId", nuevoCotizacionDetalle.getId());
				
			}

			if(tipoConsulta.equalsIgnoreCase("registrarAsignacionInspector"))
			{			
				//Si existe una cotización detalle id elimino las coberturas configurada.
				if(cotizacionDetalleId != null && !cotizacionDetalleId.equals(""))
				{
					CotizacionDetalle nuevoCotizacionDetalle = cotizacionDetalleDAO.buscarPorId(cotizacionDetalleId);
					PymeObjetoCotizacion nuevoPymeObjetoCotizacion=objetoCotizacionDAO.buscarPorId(new BigInteger(nuevoCotizacionDetalle.getObjetoId()));

					if(inspectorId  != null && !inspectorId .equals("")){
						nuevoPymeObjetoCotizacion.setInspectorId(new BigInteger(inspectorId));
						Timestamp fechaActual1 = new Timestamp(System.currentTimeMillis());
						nuevoPymeObjetoCotizacion.setFechaSolicitud(fechaActual1);
					}
					pymeObjetoCotizacionTransaction.editar(nuevoPymeObjetoCotizacion);
					
					result.put("cotizacionDetalleId", nuevoCotizacionDetalle.getId());
				}
			}
			
			if(tipoConsulta.equalsIgnoreCase("finalizarAsignacionInspector"))
			{	
				//verifico si todas las direcciones estan aprobadas
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				List<CotizacionDetalle> listadoDetalles=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);
				int contadorAsignados=0;
				int contadorRequiereInspeccion=0;
				for(CotizacionDetalle detalle:listadoDetalles){
					PymeObjetoCotizacion pymeObjetoCotizacion=objetoCotizacionDAO.buscarPorId(new BigInteger(detalle.getObjetoId()));
					if(pymeObjetoCotizacion.getInspectorId()!=null)
						contadorAsignados++;
					if(pymeObjetoCotizacion.getRequiereInspeccion())
						contadorRequiereInspeccion++;
				}
				
				//Obtengo los datos del usuario para enviar el correo
				//UsuarioDAO usuarioDAO=new UsuarioDAO(); 
				//usuarioDAO.buscarPorId(cotizacion.getUsuario().getEntidad().getMail());
				if(contadorRequiereInspeccion == contadorAsignados){
					//Si existe la cotización le cambio de estado
					cotizacion.setEstado(estadoDAO.buscarPorNombre("Pendiente de Inspeccion","Cotizacion"));
					cotizacion = cotizacionTransaction.editar(cotizacion);
					NotificacionDAO notificacionDAO=new NotificacionDAO();
					UsuarioDAO usuarioDAO=new UsuarioDAO();
					ClienteDAO clienteProcesos = new ClienteDAO();
					
					int contador=0;
					for(CotizacionDetalle detalle:listadoDetalles){
						PymeObjetoCotizacion pymeObjetoCotizacion=objetoCotizacionDAO.buscarPorId(new BigInteger(detalle.getObjetoId()));
						if(pymeObjetoCotizacion.getInspectorId()!=null){
							//Proceso de envio email si necesita insepeccion.
							Cliente cliente = clienteProcesos.buscarPorId(""+cotizacion.getClienteId());

							Entidad usuario=cotizacion.getUsuario().getEntidad();
							
							VariableSistemaDAO variableSistemaDAO=new VariableSistemaDAO();
							VariableSistema variableSistema=variableSistemaDAO.buscarPorNombre("RUTA_IMAGENES_EMAILS");
							
							java.util.Hashtable<String, String> parametersHeader = new java.util.Hashtable<String, String>();
							parametersHeader.put("urlImagenes", variableSistema.getValor());
							parametersHeader.put("CotizacionID", cotizacion.getId());
							parametersHeader.put("ClienteIdentificacion", cliente.getEntidad().getIdentificacion());
							parametersHeader.put("ClienteNombre", cliente.getEntidad().getNombreCompleto());
							if(cliente.getEntidad().getTelefono()!=null)
								parametersHeader.put("ClienteTelefono", cliente.getEntidad().getTelefono());
							else
								parametersHeader.put("ClienteTelefono", "");
							parametersHeader.put("ClienteEmail", cliente.getEntidad().getMail());
							
							parametersHeader.put("UsuarioNombre", usuario.getNombreCompleto());
							parametersHeader.put("UsuarioEmail", usuario.getMail());
							
							ProvinciaDAO provinciaDAO=new ProvinciaDAO();
							String datosBien="";
							
							Provincia provincia=provinciaDAO.buscarPorId(""+pymeObjetoCotizacion.getProvinciaId());
							datosBien+="<tr><td>"+contador+"</td><td>"+provincia.getNombre()+"</td><td>"+pymeObjetoCotizacion.getCallePrincipal()+" y " +pymeObjetoCotizacion.getCalleSecundaria()+"</td></tr>";
							contador++;
							parametersHeader.put("DatosBienes", datosBien);
							
							//Obtengo las configuraciones de las notificaciones
							
							Notificacion notificacion=notificacionDAO.buscarPorProceso("NOTIFICACION_SOLICITUD_INSPECCION");
							if(notificacion.getNotificacionId()!=null){
								StringBuilder mailReceptor = new StringBuilder();
								String mailEjecutivo="";
								if(notificacion.isNotificacionCliente())
									if(!cliente.getEntidad().getMail().equals(""))
									{
										mailReceptor.append(cliente.getEntidad().getMail());
										mailReceptor.append(",");
									}
								if(notificacion.isNotificacionInspector())
								{
									if(pymeObjetoCotizacion.getInspectorId()!=null){
										Usuario usuarioInspector=usuarioDAO.buscarPorId(pymeObjetoCotizacion.getInspectorId().toString());
										if(!usuarioInspector.getEntidad().getMail().equals(""))
										{
											mailReceptor.append(usuarioInspector.getEntidad().getMail());
											mailReceptor.append(",");
										}
									}
								}
								if(notificacion.isNotificacionUsuario())
									if(!cotizacion.getUsuario().getEntidad().getMail().equals(""))
									{
										mailReceptor.append(cotizacion.getUsuario().getEntidad().getMail());
										mailReceptor.append(",");
									}
								if(notificacion.isNotificacionEjecutivo())
								{
									//proceso para obtener de la nueva tabla
									PymeParametroPuntoVentaDAO pppvDAO=new PymeParametroPuntoVentaDAO();
									EntidadDAO entidadDA=new EntidadDAO();
									PymeParametroPuntoVenta pppv= pppvDAO.buscarPorPuntoVentaId(new BigInteger(cotizacion.getPuntoVenta().getId()));
									if(pppv.getParametroPuntoventaId()!=null){
										Entidad entidad=entidadDA.buscarPorId(pppv.getEntidadId().toString());
										if(!entidad.getMail().equals(""))
										{
											mailReceptor.append(cotizacion.getUsuario().getEntidad().getMail());
											mailReceptor.append(",");
										}
									}
								}
								if(!notificacion.getEmailInspector().equals("")){
									mailEjecutivo = notificacion.getEmailInspector();
								}
								
								if(!mailReceptor.toString().equals("")){
									MailGenericoPlantillas.EnvioPlantillaGenerica(mailReceptor.substring(0,mailReceptor.length()-1), parametersHeader, 
																"/static/plantillas/pymes/"+notificacion.getPlantillaNombre(), request, mailEjecutivo);
								}
							}
						}
					}
					
					
				}
			}
			
			if(tipoConsulta.equalsIgnoreCase("finalizarAsignacionSinInspector"))
			{	
				//verifico si todas las direcciones estan aprobadas
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				List<CotizacionDetalle> listadoDetalles=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);
				int contadorAsignados=0;
				int contadorRequiereInspeccion=0;
				for(CotizacionDetalle detalle:listadoDetalles){
					PymeObjetoCotizacion pymeObjetoCotizacion=objetoCotizacionDAO.buscarPorId(new BigInteger(detalle.getObjetoId()));
					if(pymeObjetoCotizacion.getInspectorId()!=null)
						contadorAsignados++;
					if(pymeObjetoCotizacion.getRequiereInspeccion())
						contadorRequiereInspeccion++;
				}
				
				//Obtengo los datos del usuario para enviar el correo
				//UsuarioDAO usuarioDAO=new UsuarioDAO(); 
				//usuarioDAO.buscarPorId(cotizacion.getUsuario().getEntidad().getMail());
				
				//Si existe la cotización le cambio de estado
				cotizacion.setEstado(estadoDAO.buscarPorNombre("Pendiente de Inspeccion","Cotizacion"));
				cotizacion = cotizacionTransaction.editar(cotizacion);
				int contador=0;
				NotificacionDAO notificacionDAO=new NotificacionDAO();
				ClienteDAO clienteProcesos = new ClienteDAO();
				EntidadDAO entidadDAO=new EntidadDAO(); 
				ProvinciaDAO provinciaDAO=new ProvinciaDAO();
				UsuarioDAO usuarioDAO=new UsuarioDAO();
				for(CotizacionDetalle detalle:listadoDetalles){
					PymeObjetoCotizacion pymeObjetoCotizacion=objetoCotizacionDAO.buscarPorId(new BigInteger(detalle.getObjetoId()));
					
					//Proceso de envio email si necesita insepeccion.
					Cliente cliente = clienteProcesos.buscarPorId(""+cotizacion.getClienteId());
					
					Entidad usuario=cotizacion.getUsuario().getEntidad();
					
					VariableSistemaDAO variableSistemaDAO=new VariableSistemaDAO();
					VariableSistema variableSistema=variableSistemaDAO.buscarPorNombre("RUTA_IMAGENES_EMAILS");
					
					java.util.Hashtable<String, String> parametersHeader = new java.util.Hashtable<String, String>();
					parametersHeader.put("urlImagenes", variableSistema.getValor());
					parametersHeader.put("CotizacionID", cotizacion.getId());
					parametersHeader.put("ClienteIdentificacion", cliente.getEntidad().getIdentificacion());
					parametersHeader.put("ClienteNombre", cliente.getEntidad().getNombreCompleto());
					if(cliente.getEntidad().getTelefono()!=null)
						parametersHeader.put("ClienteTelefono", cliente.getEntidad().getTelefono());
					else
						parametersHeader.put("ClienteTelefono", "");
					parametersHeader.put("ClienteEmail", cliente.getEntidad().getMail());
					
					parametersHeader.put("UsuarioNombre", usuario.getNombreCompleto());
					parametersHeader.put("UsuarioEmail", usuario.getMail());
					
					String datosBien="";
					
					Provincia provincia=provinciaDAO.buscarPorId(""+pymeObjetoCotizacion.getProvinciaId());
					datosBien+="<tr><td>"+contador+"</td><td>"+provincia.getNombre()+"</td><td>"+pymeObjetoCotizacion.getCallePrincipal()+" y " +pymeObjetoCotizacion.getCalleSecundaria()+"</td></tr>";
					contador++;
					parametersHeader.put("DatosBienes", datosBien);
					
					//Obtengo las configuraciones de las notificaciones
					
					Notificacion notificacion=notificacionDAO.buscarPorProceso("NOTIFICACION_SOLICITUD_INSPECCION");
					if(notificacion.getNotificacionId()!=null){
						StringBuilder mailReceptor = new StringBuilder();
						String mailEjecutivo="";
						if(notificacion.isNotificacionCliente())
							if(!cliente.getEntidad().getMail().equals(""))
							{
								mailReceptor.append(cliente.getEntidad().getMail());
								mailReceptor.append(",");
							}
						if(notificacion.isNotificacionInspector())
						{
							if(pymeObjetoCotizacion.getInspectorId()!=null){
								Usuario usuarioInspector=usuarioDAO.buscarPorId(pymeObjetoCotizacion.getInspectorId().toString());
								if(!usuarioInspector.getEntidad().getMail().equals(""))
								{
									mailReceptor.append(usuarioInspector.getEntidad().getMail());
									mailReceptor.append(",");
								}
							}
						}
						if(notificacion.isNotificacionUsuario())
							if(!cotizacion.getUsuario().getEntidad().getMail().equals(""))
							{
								mailReceptor.append(cotizacion.getUsuario().getEntidad().getMail());
								mailReceptor.append(",");
							}
						if(notificacion.isNotificacionEjecutivo())
						{
							//proceso para obtener de la nueva tabla
							PymeParametroPuntoVentaDAO pppvDAO=new PymeParametroPuntoVentaDAO();
							EntidadDAO entidadDA=new EntidadDAO();
							PymeParametroPuntoVenta pppv= pppvDAO.buscarPorPuntoVentaId(new BigInteger(cotizacion.getPuntoVenta().getId()));
							Entidad entidad=entidadDA.buscarPorId(pppv.getEntidadId().toString());
							if(!entidad.getMail().equals(""))
							{
								mailReceptor.append(cotizacion.getUsuario().getEntidad().getMail());
								mailReceptor.append(",");
							}
						}
						if(!notificacion.getEmailInspector().equals("")){
							mailEjecutivo = notificacion.getEmailInspector();
						}
						
						if(!mailReceptor.toString().equals("")){
							MailGenericoPlantillas.EnvioPlantillaGenerica(mailReceptor.substring(0,mailReceptor.length()-1), parametersHeader, "/static/plantillas/pymes/"+notificacion.getPlantillaNombre(), 
													request, mailEjecutivo);
						}
					}
				}
			}
			// 
			if(tipoConsulta.equalsIgnoreCase("registrarInspeccion"))
			{			
				//Si existe una cotización detalle id elimino las coberturas configurada.
				if(cotizacionDetalleId != null && !cotizacionDetalleId.equals(""))
				{
					CotizacionDetalle nuevoCotizacionDetalle = cotizacionDetalleDAO.buscarPorId(cotizacionDetalleId);
					PymeObjetoCotizacion nuevoPymeObjetoCotizacion=objetoCotizacionDAO.buscarPorId(new BigInteger(nuevoCotizacionDetalle.getObjetoId()));

					if(tipoConstruccionId  != null && !tipoConstruccionId .equals(""))
						nuevoPymeObjetoCotizacion.setTipoConstruccionId(tipoConstruccionId);

					if(tipoOcupacionId  != null && !tipoOcupacionId .equals(""))
						nuevoPymeObjetoCotizacion.setTipoOcupacionId(new BigInteger(tipoOcupacionId));

					if(numeroTotalPisos  != null && !numeroTotalPisos .equals(""))
						nuevoPymeObjetoCotizacion.setNumeroTotalPisos(Integer.parseInt(numeroTotalPisos));

					if(anioConstruccion  != null && !anioConstruccion .equals(""))
						nuevoPymeObjetoCotizacion.setAnioConstruccion(Integer.parseInt(anioConstruccion));
					
					if(fechaInspeccion  != null && !fechaInspeccion .equals(""))
					{
						//Transforma la fecha de vigencia dede
						Date fechaInspeccionValor = new Date();
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						fechaInspeccionValor=formatter.parse(fechaInspeccion);
						nuevoPymeObjetoCotizacion.setFechaInspeccion(fechaInspeccionValor);
					}

					if(extintores  != null && !extintores.equals(""))
						nuevoPymeObjetoCotizacion.setExtintores(extintores.equals("1")?true:false);

					if(detectorHumo  != null && !detectorHumo .equals(""))
						nuevoPymeObjetoCotizacion.setDetectorHumo(detectorHumo.equals("1")?true:false);

					if(sprinklers  != null && !sprinklers .equals(""))
						nuevoPymeObjetoCotizacion.setSprinklers(sprinklers.equals("1")?true:false);

					if(alarmaMonitoreada  != null && !alarmaMonitoreada .equals(""))
						nuevoPymeObjetoCotizacion.setAlarmaMonitoreada(alarmaMonitoreada.equals("1")?true:false);

					if(guardiania  != null && !guardiania .equals(""))
						nuevoPymeObjetoCotizacion.setGuardiania(guardiania.equals("1")?true:false);

					if(otros != null && !otros.equals(""))
						nuevoPymeObjetoCotizacion.setOtros(otros);

					if(latitud != null && !latitud.equals(""))
						nuevoPymeObjetoCotizacion.setLatitud(Double.parseDouble(latitud));

					if(longuitud != null && !longuitud.equals(""))
						nuevoPymeObjetoCotizacion.setLonguitud(Double.parseDouble(longuitud));

					byte[] archivoAdjuntoInforme=null;
										
					if(!FileName.equals("")){
						 String[] extencion = FileName.split("\\.");
						 String extencionArchivo=extencion[extencion.length-1];
						 System.out.println("Entra el html "+FileName);
						 String rutaPlantilla = this.getServletContext().getRealPath("")+ "/static/plantillas/pymes/Informes/" + cotizacionId+"."+extencionArchivo;
						 File f1=new File(rutaPlantilla);
						 FileInputStream f_in=new FileInputStream(f1);
						 archivoAdjuntoInforme=IOUtils.toByteArray(f_in);
						 nuevoPymeObjetoCotizacion.setInforme(archivoAdjuntoInforme);
					}

					if(estadoInspeccion != null && !estadoInspeccion.equals(""))
						nuevoPymeObjetoCotizacion.setEstadoInspeccion(Integer.parseInt(estadoInspeccion));

					pymeObjetoCotizacionTransaction.editar(nuevoPymeObjetoCotizacion);

					//verifico si todas las direcciones estan aprobadas
					cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
					List<CotizacionDetalle> listadoDetalles=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);
					int contadorAprobados=0;
					int contadorNoAprobados=0;
					int contadorRequiereInspeccion=0;
					for(CotizacionDetalle detalle:listadoDetalles){
						PymeObjetoCotizacion pymeObjetoCotizacion=objetoCotizacionDAO.buscarPorId(new BigInteger(detalle.getObjetoId()));
						if(pymeObjetoCotizacion.getEstadoInspeccion()==1)
							contadorAprobados++;
						if(pymeObjetoCotizacion.getEstadoInspeccion()==2)
							contadorNoAprobados++;
						if(pymeObjetoCotizacion.getRequiereInspeccion())
							contadorRequiereInspeccion++;
					}
					//Obtengo los datos del usuario para enviar el correo
					UsuarioDAO usuarioDAO=new UsuarioDAO(); 
					usuarioDAO.buscarPorId(cotizacion.getUsuario().getEntidad().getMail());
					if(contadorRequiereInspeccion == contadorAprobados){
						cotizacion.setEstado(estadoDAO.buscarPorNombre("Pendiente","Cotizacion"));
						cotizacion.setEtapaWizard(3);
						Date fechaActual=new Date();
						cotizacion.setVigenciaDesde(fechaActual);
						cotizacionTransaction.editar(cotizacion);
						//Proceso de envio email si necesita insepeccion.
						ClienteDAO clienteProcesos = new ClienteDAO();
						Cliente cliente = clienteProcesos.buscarPorId(""+cotizacion.getClienteId());
						
						Entidad usuario=cotizacion.getUsuario().getEntidad();
						
						int pymes=Integer.parseInt(nuevoCotizacionDetalle.getObjetoId());
						BigInteger pymesId = new BigInteger(Integer.toString(pymes));
						PymeObjetoCotizacionDAO pymesProcesos= new PymeObjetoCotizacionDAO();
						PymeObjetoCotizacion pymesCotizacion = pymesProcesos.buscarPorId(pymesId);
						
						VariableSistemaDAO variableSistemaDAO=new VariableSistemaDAO();
						VariableSistema variableSistema=variableSistemaDAO.buscarPorNombre("RUTA_IMAGENES_EMAILS");
						
						java.util.Hashtable<String, String> parametersHeader = new java.util.Hashtable<String, String>();
						parametersHeader.put("urlImagenes", variableSistema.getValor());
						parametersHeader.put("CotizacionId", cotizacion.getId());
						parametersHeader.put("IdentificacionCliente", cliente.getEntidad().getIdentificacion());
						parametersHeader.put("NombreCliente", cliente.getEntidad().getNombreCompleto());
						if(cliente.getEntidad().getTelefono()!=null)
							parametersHeader.put("TelefonoCliente", cliente.getEntidad().getTelefono());
						else
							parametersHeader.put("TelefonoCliente", "");
						parametersHeader.put("EmailAgente", cliente.getEntidad().getMail());
						
						parametersHeader.put("UsuarioNombre", usuario.getNombreCompleto());
						parametersHeader.put("UsuarioEmail", usuario.getMail());
						
						PymeObjetoCotizacionDAO objetoPymesDAO=new PymeObjetoCotizacionDAO();
						ProvinciaDAO provinciaDAO=new ProvinciaDAO();
						String datosBien="";
						String emailsInspectores="";
						int contador=1;
						for(CotizacionDetalle detalle:cotizacion.getCotizacionDetalles()){
							PymeObjetoCotizacion detalleObjeto=objetoPymesDAO.buscarPorId(new BigInteger(detalle.getObjetoId()));
							if(detalleObjeto!=null){
								Provincia provincia=provinciaDAO.buscarPorId(""+detalleObjeto.getProvinciaId());
								datosBien+="<tr><td>"+contador+"</td><td>"+provincia.getNombre()+"</td><td>"+detalleObjeto.getCallePrincipal()+" y " +detalleObjeto.getCalleSecundaria()+"</td></tr>";
								contador++;
								if(detalleObjeto.getInspectorId()!=null)
								{
									Usuario usuarioInspector=usuarioDAO.buscarPorId(detalleObjeto.getInspectorId().toString());
									if(!usuarioInspector.getEntidad().getMail().equals("")){
										emailsInspectores=usuarioInspector.getEntidad().getMail() + ",";
									}
								}
							}
						}
						parametersHeader.put("DatosBienes", datosBien);
						
						//Obtengo las configuraciones de las notificaciones
						NotificacionDAO notificacionDAO=new NotificacionDAO();
						Notificacion notificacion=notificacionDAO.buscarPorProceso("NOTIFICACION_INSPECCION_REALIZADA");
						if(notificacion.getNotificacionId()!=null){
							StringBuilder mailReceptor = new StringBuilder();
							String mailEjecutivo="";
							if(notificacion.isNotificacionCliente())
								if(!cliente.getEntidad().getMail().equals(""))
								{
									mailReceptor.append(cliente.getEntidad().getMail());
									mailReceptor.append(",");
								}
							if(notificacion.isNotificacionInspector())
								if(!emailsInspectores.equals(""))
								{
									mailReceptor.append(emailsInspectores);
									
								}
							if(notificacion.isNotificacionUsuario())
								if(!cotizacion.getUsuario().getEntidad().getMail().equals(""))
								{
									mailReceptor.append(cotizacion.getUsuario().getEntidad().getMail());
									mailReceptor.append(",");
								}
							if(notificacion.isNotificacionEjecutivo())
							{
								//proceso para obtener de la nueva tabla
								PymeParametroPuntoVentaDAO pppvDAO=new PymeParametroPuntoVentaDAO();
								EntidadDAO entidadDA=new EntidadDAO();
								PymeParametroPuntoVenta pppv= pppvDAO.buscarPorPuntoVentaId(new BigInteger(cotizacion.getPuntoVenta().getId()));
								if(pppv.getParametroPuntoventaId()!=null)
								{
									Entidad entidad=entidadDA.buscarPorId(pppv.getEntidadId().toString());
									if(!entidad.getMail().equals(""))
									{
										mailReceptor.append(cotizacion.getUsuario().getEntidad().getMail());
										mailReceptor.append(",");
									}
								}
							}
							if(!notificacion.getEmailInspector().equals("")){
								mailEjecutivo = notificacion.getEmailInspector();
							}
							
							if(!mailReceptor.toString().equals("")){
								MailGenericoPlantillas.EnvioPlantillaGenerica(mailReceptor.substring(0,mailReceptor.length()-1), parametersHeader, 
																			  "/static/plantillas/pymes/"+notificacion.getPlantillaNombre(), request, 
																			  archivoAdjuntoInforme, mailEjecutivo);
							}
						}
						result.put("bandera", "Aprobada");
					}
					else if(contadorNoAprobados > 0){
						cotizacion.setEstado(estadoDAO.buscarPorNombre("Anulado","Cotizacion"));
						cotizacion.setEtapaWizard(3);
						cotizacionTransaction.editar(cotizacion);
						
						//Proceso de envio email si necesita insepeccion.
						ClienteDAO clienteProcesos = new ClienteDAO();
						Cliente cliente = clienteProcesos.buscarPorId(""+cotizacion.getClienteId());
						int pymes=Integer.parseInt(nuevoCotizacionDetalle.getObjetoId());
						BigInteger pymesId = new BigInteger(Integer.toString(pymes));
						PymeObjetoCotizacionDAO pymesProcesos= new PymeObjetoCotizacionDAO();
						PymeObjetoCotizacion pymesCotizacion = pymesProcesos.buscarPorId(pymesId);
						
						CantonDAO cantonDAO= new CantonDAO();
						String cantonID=""+pymesCotizacion.getCiudadId();
						Canton canton=cantonDAO.buscarPorId(cantonID);
						String CPrincipal= pymesCotizacion.getCallePrincipal();
						String CSecundaria= pymesCotizacion.getCalleSecundaria();
						String Ubicacion="En la ciudad de: "+ canton.getNombre() + ", Dirección: " + CPrincipal + " y " + CSecundaria;
						
						VariableSistemaDAO variableSistemaDAO=new VariableSistemaDAO();
						VariableSistema variableSistema=variableSistemaDAO.buscarPorNombre("RUTA_IMAGENES_EMAILS");
						
						java.util.Hashtable<String, String> parametersHeader = new java.util.Hashtable<String, String>();
						parametersHeader.put("urlImagenes", variableSistema.getValor());
						parametersHeader.put("cotizacionID", cotizacion.getId());
						//parametersHeader.put("ubicacionCotizacion", Ubicacion);
						
						//Obtengo las configuraciones de las notificaciones
						NotificacionDAO notificacionDAO=new NotificacionDAO();
						Notificacion notificacion=notificacionDAO.buscarPorProceso("NOTIFICACION_INSPECCION_REALIZADA");
						if(notificacion.getNotificacionId()!=null){
							StringBuilder mailReceptor = new StringBuilder();
							String mailEjecutivo="";
							if(notificacion.isNotificacionCliente())
								if(!cliente.getEntidad().getMail().equals(""))
								{
									mailReceptor.append(cliente.getEntidad().getMail());
									mailReceptor.append(",");
								}
							if(notificacion.isNotificacionInspector())
							{
								if(pymesCotizacion.getInspectorId()!=null){
									Usuario usuario=usuarioDAO.buscarPorId(pymesCotizacion.getInspectorId().toString());
									if(!usuario.getEntidad().getMail().equals(""))
									{
										mailReceptor.append(usuario.getEntidad().getMail());
										mailReceptor.append(",");
									}
								}
							}
							if(notificacion.isNotificacionUsuario())
								if(!cotizacion.getUsuario().getEntidad().getMail().equals(""))
								{
									mailReceptor.append(cotizacion.getUsuario().getEntidad().getMail());
									mailReceptor.append(",");
								}
							if(notificacion.isNotificacionEjecutivo())
							{
								//proceso para obtener de la nueva tabla
								PymeParametroPuntoVentaDAO pppvDAO=new PymeParametroPuntoVentaDAO();
								EntidadDAO entidadDA=new EntidadDAO();
								PymeParametroPuntoVenta pppv= pppvDAO.buscarPorPuntoVentaId(new BigInteger(cotizacion.getPuntoVenta().getId()));
								if(pppv.getParametroPuntoventaId()!=null){
									Entidad entidad=entidadDA.buscarPorId(pppv.getEntidadId().toString());
									if(!entidad.getMail().equals(""))
									{
										mailReceptor.append(cotizacion.getUsuario().getEntidad().getMail());
										mailReceptor.append(",");
									}
								}
							}
							if(!notificacion.getEmailInspector().equals("")){
								mailEjecutivo = notificacion.getEmailInspector();
							}
							
							if(!mailReceptor.equals("")){
								MailGenericoPlantillas.EnvioPlantillaGenerica(mailReceptor.substring(0,mailReceptor.length()-1), parametersHeader, 
																			  "/static/plantillas/pymes/InspeccionNoSugerida.html", request, 
																			  archivoAdjuntoInforme, mailEjecutivo);
							}
						}
						result.put("bandera", "Negada");
					}else{
						result.put("bandera", "Pendiente");
					}
						
					result.put("cotizacionDetalleId", nuevoCotizacionDetalle.getId());
				}
			}

			if (tipoConsulta.equals("")){
				// /Hacer una copia del archivo cargado para el archivo de emision
				// masiva
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				String valor = request.getParameter("valor") == null ? "" : request.getParameter("valor");
				try {
					List<FileItem> items = upload.parseRequest(request);
					for (FileItem item : items) {
						if (!item.isFormField()) {
							// Process form file field (input type="file").
							System.out.println("Field name: " + item.getFieldName());
							System.out.println("File name: " + item.getName());
							String fileName = ""+item.getName();
							String[] extencion = fileName.split("\\.");
							String extencionArchivo=extencion[extencion.length-1];
							System.out.println("File size: " + item.getSize());
							System.out.println("File type: " + item.getContentType());
							
							//tomamos la ruta relativa de la clase para referenciar la plantilla
							String rutaPlantilla = AgriSucreNotificacionErrores.class.getProtectionDomain().getCodeSource()
									.getLocation().getPath();
							rutaPlantilla=rutaPlantilla.replace("AgriSucreNotificacionErrores.class", "");
							String rutaRelativaReporte="../../../../../../../../static/plantillas/pymes/Informes/";
							rutaPlantilla=rutaPlantilla+rutaRelativaReporte;														
							try {
								File saveFile = new File(
										rutaPlantilla,
										valor+"."+extencionArchivo);
								saveFile.createNewFile();
								item.write(saveFile);
								System.out.println("Current folder: "
										+ saveFile.getCanonicalPath());
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				} catch (FileUploadException e) {
					try {
						throw new ServletException("Cannot parse multipart request.", e);
					} catch (ServletException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
			
			if(tipoConsulta.equalsIgnoreCase("obtenerInspecciones"))
			{
				HttpSession session = request.getSession(true);

				JSONObject objectDetalle= new JSONObject();
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				List<CotizacionDetalle> detalles= cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);		
				JSONArray jsonDetallesDirecciones= new JSONArray(); 
				PymeInspectorProvinciaDAO inspeccionProvinciaDAO=new PymeInspectorProvinciaDAO();
				for(CotizacionDetalle detalleActual: detalles){
					PymeObjetoCotizacion objetoCotizacion= objetoCotizacionDAO.buscarPorId(new BigInteger(detalleActual.getObjetoId()));
					if(objetoCotizacion.getRequiereInspeccion()){
						objectDetalle.put("cotizacionDetalleId", detalleActual.getId());
						objectDetalle.put("sumaAsegurada", detalleActual.getSumaAseguradaItem());
						objectDetalle.put("primaNeta", detalleActual.getPrimaNetaItem());

						objectDetalle.put("provinciaId", objetoCotizacion.getProvinciaId());
						objectDetalle.put("cantonId", objetoCotizacion.getCiudadId());
						objectDetalle.put("callePrincipal", objetoCotizacion.getCallePrincipal());
						objectDetalle.put("numeroDireccion", objetoCotizacion.getNumeroDireccion());
						objectDetalle.put("calleSecundaria", objetoCotizacion.getCalleSecundaria());
						jsonDetallesDirecciones.add(objectDetalle);
					}
				}
				result.put("direcciones", jsonDetallesDirecciones);
			}
			
			if(tipoConsulta.equalsIgnoreCase("obtenerDireccionesInspeccion"))
			{
				HttpSession session = request.getSession(true);
				Usuario usuario = (Usuario) session.getAttribute("usuario");

				JSONObject objectDetalle= new JSONObject();
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				List<CotizacionDetalle> detalles= cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);		
				JSONArray jsonDetallesDirecciones= new JSONArray(); 
				PymeInspectorProvinciaDAO inspeccionProvinciaDAO=new PymeInspectorProvinciaDAO();
				for(CotizacionDetalle detalleActual: detalles){
					PymeObjetoCotizacion objetoCotizacion= objetoCotizacionDAO.buscarPorId(new BigInteger(detalleActual.getObjetoId()));
					if(objetoCotizacion.getRequiereInspeccion()){
						List<PymeInspectorProvincia> listadoProvincias=inspeccionProvinciaDAO.buscarUsuarioId(new BigInteger(usuario.getId()), BigInteger.valueOf(objetoCotizacion.getProvinciaId()), objetoCotizacion.getCiudadId());
						if(listadoProvincias.size()!=0){
							objectDetalle.put("cotizacionDetalleId", detalleActual.getId());
							objectDetalle.put("sumaAsegurada", detalleActual.getSumaAseguradaItem());
							objectDetalle.put("primaNeta", detalleActual.getPrimaNetaItem());
	
							objectDetalle.put("provinciaId", objetoCotizacion.getProvinciaId());
							objectDetalle.put("cantonId", objetoCotizacion.getCiudadId());
							objectDetalle.put("callePrincipal", objetoCotizacion.getCallePrincipal());
							objectDetalle.put("numeroDireccion", objetoCotizacion.getNumeroDireccion());
							objectDetalle.put("calleSecundaria", objetoCotizacion.getCalleSecundaria());
							jsonDetallesDirecciones.add(objectDetalle);
						}
					}
				}
				result.put("direcciones", jsonDetallesDirecciones);
			}
			if(tipoConsulta.equalsIgnoreCase("obtenerDireccionesSinInspeccion"))
			{
				HttpSession session = request.getSession(true);
				Usuario usuario = (Usuario) session.getAttribute("usuario");

				JSONObject objectDetalle= new JSONObject();
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				List<CotizacionDetalle> detalles= cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);		
				JSONArray jsonDetallesDirecciones= new JSONArray(); 
				PymeInspectorProvinciaDAO inspeccionProvinciaDAO=new PymeInspectorProvinciaDAO();
				for(CotizacionDetalle detalleActual: detalles){
					PymeObjetoCotizacion objetoCotizacion= objetoCotizacionDAO.buscarPorId(new BigInteger(detalleActual.getObjetoId()));
					if(objetoCotizacion.getRequiereInspeccion()){
							objectDetalle.put("cotizacionDetalleId", detalleActual.getId());
							objectDetalle.put("sumaAsegurada", detalleActual.getSumaAseguradaItem());
							objectDetalle.put("primaNeta", detalleActual.getPrimaNetaItem());
	
							objectDetalle.put("provinciaId", objetoCotizacion.getProvinciaId());
							objectDetalle.put("cantonId", objetoCotizacion.getCiudadId());
							objectDetalle.put("callePrincipal", objetoCotizacion.getCallePrincipal());
							objectDetalle.put("numeroDireccion", objetoCotizacion.getNumeroDireccion());
							objectDetalle.put("calleSecundaria", objetoCotizacion.getCalleSecundaria());
							jsonDetallesDirecciones.add(objectDetalle);
						
					}
				}
				result.put("direcciones", jsonDetallesDirecciones);
			}
			if(tipoConsulta.equalsIgnoreCase("obtenerDireccionesSinInspector"))
			{
				//HttpSession session = request.getSession(true);
				//Usuario usuario = (Usuario) session.getAttribute("usuario");

				JSONObject objectDetalle= new JSONObject();
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				List<CotizacionDetalle> detalles= cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);		
				JSONArray jsonDetallesDirecciones= new JSONArray(); 
				//PymeInspectorProvinciaDAO inspeccionProvinciaDAO=new PymeInspectorProvinciaDAO();
				for(CotizacionDetalle detalleActual: detalles){
					PymeObjetoCotizacion objetoCotizacion= objetoCotizacionDAO.buscarPorId(new BigInteger(detalleActual.getObjetoId()));
					if(objetoCotizacion.getRequiereInspeccion()){
						//List<PymeInspectorProvincia> listadoProvincias=inspeccionProvinciaDAO.buscarUsuarioId(new BigInteger(usuario.getId()), BigInteger.valueOf(objetoCotizacion.getProvinciaId()), objetoCotizacion.getCiudadId());
						//if(listadoProvincias.size()!=0){
							objectDetalle.put("cotizacionDetalleId", detalleActual.getId());
							objectDetalle.put("sumaAsegurada", detalleActual.getSumaAseguradaItem());
							objectDetalle.put("primaNeta", detalleActual.getPrimaNetaItem());
							objectDetalle.put("provinciaId", objetoCotizacion.getProvinciaId());
							objectDetalle.put("cantonId", objetoCotizacion.getCiudadId());
							objectDetalle.put("callePrincipal", objetoCotizacion.getCallePrincipal());
							objectDetalle.put("numeroDireccion", objetoCotizacion.getNumeroDireccion());
							objectDetalle.put("calleSecundaria", objetoCotizacion.getCalleSecundaria());
							jsonDetallesDirecciones.add(objectDetalle);
						//}
					}
				}
				result.put("direcciones", jsonDetallesDirecciones);
			}
			
			if(tipoConsulta.equalsIgnoreCase("obtenerConfiguracionPorDetalleId"))
			{			
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				CotizacionDetalle nuevoCotizacionDetalle=new CotizacionDetalle(); 

				PymeObjetoCotizacion nuevoObjetoPyme=new PymeObjetoCotizacion();

				if(cotizacionDetalleId != null && !cotizacionDetalleId.equals(""))
				{
					nuevoCotizacionDetalle = cotizacionDetalleDAO.buscarPorId(cotizacionDetalleId);
					if(nuevoCotizacionDetalle.getObjetoId()!=null)
					{
						nuevoObjetoPyme=objetoCotizacionDAO.buscarPorId(new BigInteger(nuevoCotizacionDetalle.getObjetoId()));
						if(nuevoObjetoPyme.getObjetoPymesId()!=null)
						{
							result.put("provinciaId", nuevoObjetoPyme.getProvinciaId());
							result.put("cantonId", nuevoObjetoPyme.getCiudadId());
							result.put("callePrincipal", nuevoObjetoPyme.getCallePrincipal());
							result.put("numeroDireccion", nuevoObjetoPyme.getNumeroDireccion());
							result.put("calleSecundaria", nuevoObjetoPyme.getCalleSecundaria());
							result.put("actividadEconomicaId", nuevoObjetoPyme.getActividadEconomicaId());
							result.put("tipoPredioId", nuevoObjetoPyme.getTipoPredioId());
							result.put("tieneMasDosAnios", nuevoObjetoPyme.getTieneMasDosAnio());
							result.put("seguridadAdecuada", nuevoObjetoPyme.getSeguridadAdecuada());
							result.put("anioConstruccion", nuevoObjetoPyme.getAnioConstruccion());
							result.put("contabilidadFormal", nuevoObjetoPyme.getContabilidadFormal());
							result.put("requiereInspeccion", nuevoObjetoPyme.getRequiereInspeccion());
							result.put("sector", nuevoObjetoPyme.getSector());
							result.put("nombreEdificio", nuevoObjetoPyme.getNombreEdificio());
							result.put("numeroOficina", nuevoObjetoPyme.getNumeroOficina());
							result.put("valorEstructuras", nuevoObjetoPyme.getValorEstructuras());
							result.put("valorMueblesEnseres", nuevoObjetoPyme.getValorMueblesEnseres());
							result.put("valorMaquinaria", nuevoObjetoPyme.getValorMaquinaria());
							result.put("valorMercaderia", nuevoObjetoPyme.getValorMercaderia());
							result.put("valorInsumosNoelectronicos", nuevoObjetoPyme.getValorInsumosNoelectronicos());
							result.put("valorEquipoHerramienta", nuevoObjetoPyme.getValorEquipoHerramienta());
							result.put("valorContenidos", nuevoObjetoPyme.getValorContenidos());
							//Datos de la inspección
							result.put("tipoConstruccionId", nuevoObjetoPyme.getTipoConstruccionId());
							result.put("tipoOcupacionId", nuevoObjetoPyme.getTipoOcupacionId());
							result.put("numeroTotalPisos", nuevoObjetoPyme.getNumeroTotalPisos());
							result.put("extintores", nuevoObjetoPyme.getExtintores());
							result.put("detectorHumo", nuevoObjetoPyme.getDetectorHumo());
							result.put("sprinklers", nuevoObjetoPyme.getSprinklers());
							result.put("alarmaMonitoreada", nuevoObjetoPyme.getAlarmaMonitoreada());
							result.put("guardiania", nuevoObjetoPyme.getGuardiania());
							result.put("otros", nuevoObjetoPyme.getOtros());
							result.put("latitud", nuevoObjetoPyme.getLatitud());
							result.put("longuitud", nuevoObjetoPyme.getLonguitud());
							result.put("planEstructuraId", nuevoObjetoPyme.getPlanEstructuraId());
							result.put("planContenidosId", nuevoObjetoPyme.getPlanContenidosId());
							result.put("registro", nuevoObjetoPyme.getInforme());
							result.put("EstadoInspeccion", nuevoObjetoPyme.getEstadoInspeccion());
							result.put("inspectorID", nuevoObjetoPyme.getInspectorId());
							if(nuevoObjetoPyme.getFechaInspeccion()!=null){
								DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
								String reportDate = df.format(nuevoObjetoPyme.getFechaInspeccion());
								result.put("fechaInspecion", reportDate);
							}
							else{
								result.put("fechaInspecion", "");
							}

							//Obtengo las coberturas del objeto pymes
							List<PymeObjetoCotizacionCobertura> listado=objetoCCDAO.buscarPorObjetoPymeId(nuevoObjetoPyme.getObjetoPymesId());
							for(PymeObjetoCotizacionCobertura coberturaActual:listado){
								coberturaJSONObject.put("configuracionCoberturaId", coberturaActual.getObjetoOrigenId());
								coberturaJSONObject.put("tasaSugerida",coberturaActual.getTasaSugerida());
								coberturaJSONObject.put("tasaIngresada",coberturaActual.getTasaIngresada());
								coberturaJSONObject.put("valorIngresado",coberturaActual.getValorIngresado());
								coberturaJSONObject.put("primaCalculada",coberturaActual.getPrimaCalculada());
								coberturaJSONObject.put("tipoOrigen",coberturaActual.getTipoOrigen());
								coberturaJSONObject.put("textoDeducible",coberturaActual.getTextoCompletoDeducible());
								coberturaJSONObject.put("idsDeducible",coberturaActual.getIdsDeducible());
								coberturaJSONObject.put("valoresDeducible",coberturaActual.getValoresDeducible());
								coberturaJSONObject.put("textosDeducible",coberturaActual.getTextosDeducible());
								coberturaJSONObject.put("valorAdicional",coberturaActual.getValorIngresado2());
								coberturasJSONArray.add(coberturaJSONObject);
							}
							result.put("coberturas", coberturasJSONArray);
							
							//se envian los deducibles
							List<PymeObjetoCotizacionCobertura> listado2=objetoCCDAO.buscarPorCotizacionId(nuevoObjetoPyme.getObjetoPymesId(),"DEDUCIBLE DIRECCION");
							for(PymeObjetoCotizacionCobertura coberturaActual:listado2){
								coberturaDeduciblesJSONObject.put("configuracionCoberturaId", coberturaActual.getObjetoOrigenId());
								coberturaDeduciblesJSONObject.put("direccion","direccionDeducibles");
								coberturaDeduciblesJSONObject.put("tasaSugerida",coberturaActual.getTasaSugerida());
								coberturaDeduciblesJSONObject.put("tasaIngresada",coberturaActual.getTasaIngresada());
								coberturaDeduciblesJSONObject.put("valorIngresado",coberturaActual.getValorIngresado());
								coberturaDeduciblesJSONObject.put("primaCalculada",coberturaActual.getPrimaCalculada());
								coberturaDeduciblesJSONObject.put("tipoOrigen",coberturaActual.getTipoOrigen());
								coberturaDeduciblesJSONObject.put("textoDeducible",coberturaActual.getTextoCompletoDeducible());
								coberturaDeduciblesJSONObject.put("idsDeducible",coberturaActual.getIdsDeducible());
								coberturaDeduciblesJSONObject.put("valoresDeducible",coberturaActual.getValoresDeducible());
								coberturaDeduciblesJSONObject.put("textosDeducible",coberturaActual.getTextosDeducible());
								coberturaDeduciblesJSONObject.put("valorAdicional",coberturaActual.getValorIngresado2());
								coberturasDeduciblesJSONArray.add(coberturaDeduciblesJSONObject);
							}
							result.put("coberturasDeducibles", coberturasDeduciblesJSONArray);
						}
					}
				}
			}
			if(tipoConsulta.equalsIgnoreCase("traeRol")){
				HttpSession session = request.getSession(true);
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				CotizacionDetalle nuevoCotizacionDetalle=new CotizacionDetalle(); 
				com.qbe.cotizador.model.Usuario usuario = (com.qbe.cotizador.model.Usuario) session.getAttribute("usuario");						
				List<UsuarioRol> listadoUsuarioRol = usuario.getUsuarioRols();			
				UsuarioRol usuarioRol = listadoUsuarioRol.get(0);
				Rol rol = usuarioRol.getRol();
				result.put("rol", rol.getNombre());
			}
			if(tipoConsulta.equalsIgnoreCase("obtenerConfiguracionPorDetallePlanId"))
				
			{			
				HttpSession session = request.getSession(true);
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				CotizacionDetalle nuevoCotizacionDetalle=new CotizacionDetalle(); 
				com.qbe.cotizador.model.Usuario usuario = (com.qbe.cotizador.model.Usuario) session.getAttribute("usuario");						
				List<UsuarioRol> listadoUsuarioRol = usuario.getUsuarioRols();			
				UsuarioRol usuarioRol = listadoUsuarioRol.get(0);
				Rol rol = usuarioRol.getRol();
				PymeObjetoCotizacion nuevoObjetoPyme=new PymeObjetoCotizacion();

				if(cotizacionDetalleId != null && !cotizacionDetalleId.equals(""))
				{
					nuevoCotizacionDetalle = cotizacionDetalleDAO.buscarPorId(cotizacionDetalleId);
					if(nuevoCotizacionDetalle.getObjetoId()!=null)
					{
						nuevoObjetoPyme=objetoCotizacionDAO.buscarPorId(new BigInteger(nuevoCotizacionDetalle.getObjetoId()));
						if(nuevoObjetoPyme.getObjetoPymesId()!=null)
						{
							result.put("provinciaId", nuevoObjetoPyme.getProvinciaId());
							result.put("cantonId", nuevoObjetoPyme.getCiudadId());
							result.put("callePrincipal", nuevoObjetoPyme.getCallePrincipal());
							result.put("numeroDireccion", nuevoObjetoPyme.getNumeroDireccion());
							result.put("calleSecundaria", nuevoObjetoPyme.getCalleSecundaria());
							result.put("actividadEconomicaId", nuevoObjetoPyme.getActividadEconomicaId());
							result.put("tipoPredioId", nuevoObjetoPyme.getTipoPredioId());
							result.put("tieneMasDosAnios", nuevoObjetoPyme.getTieneMasDosAnio());
							result.put("seguridadAdecuada", nuevoObjetoPyme.getSeguridadAdecuada());
							result.put("rol", rol.getNombre());
							result.put("anioConstruccion", nuevoObjetoPyme.getAnioConstruccion());
							result.put("contabilidadFormal", nuevoObjetoPyme.getContabilidadFormal());
							result.put("requiereInspeccion", nuevoObjetoPyme.getRequiereInspeccion());
							result.put("sector", nuevoObjetoPyme.getSector());
							result.put("nombreEdificio", nuevoObjetoPyme.getNombreEdificio());
							result.put("numeroOficina", nuevoObjetoPyme.getNumeroOficina());
							result.put("valorEstructuras", nuevoObjetoPyme.getValorEstructuras());
							result.put("valorMueblesEnseres", nuevoObjetoPyme.getValorMueblesEnseres());
							result.put("valorMaquinaria", nuevoObjetoPyme.getValorMaquinaria());
							result.put("valorMercaderia", nuevoObjetoPyme.getValorMercaderia());
							result.put("valorInsumosNoelectronicos", nuevoObjetoPyme.getValorInsumosNoelectronicos());
							result.put("valorEquipoHerramienta", nuevoObjetoPyme.getValorEquipoHerramienta());
							//Datos de la inspección
							result.put("tipoConstruccionId", nuevoObjetoPyme.getTipoConstruccionId());
							result.put("tipoOcupacionId", nuevoObjetoPyme.getTipoOcupacionId());
							result.put("numeroTotalPisos", nuevoObjetoPyme.getNumeroTotalPisos());
							result.put("extintores", nuevoObjetoPyme.getExtintores());
							result.put("detectorHumo", nuevoObjetoPyme.getDetectorHumo());
							result.put("sprinklers", nuevoObjetoPyme.getSprinklers());
							result.put("alarmaMonitoreada", nuevoObjetoPyme.getAlarmaMonitoreada());
							result.put("guardiania", nuevoObjetoPyme.getGuardiania());
							result.put("otros", nuevoObjetoPyme.getOtros());
							result.put("latitud", nuevoObjetoPyme.getLatitud());
							result.put("longuitud", nuevoObjetoPyme.getLonguitud());
							result.put("planEstructuraId", nuevoObjetoPyme.getPlanEstructuraId());
							result.put("planContenidosId", nuevoObjetoPyme.getPlanContenidosId());
							result.put("registro", nuevoObjetoPyme.getInforme());
							result.put("EstadoInspeccion", nuevoObjetoPyme.getEstadoInspeccion());
							result.put("inspectorID", nuevoObjetoPyme.getInspectorId());

							//Obtengo las coberturas del objeto pymes
							List<PymeObjetoCotizacionCobertura> listado=objetoCCDAO.buscarPorCotizacionId(nuevoObjetoPyme.getObjetoPymesId(),"POR DIRECCION");
							PymeConfiguracionCoberturaDAO pymeConfiguracionCoberturaDAO = new PymeConfiguracionCoberturaDAO();
							PymeConfiguracionCobertura  pymeConfiguracionCobertura = null;
							for(PymeObjetoCotizacionCobertura coberturaActual:listado){
								coberturaJSONObject.put("configuracionCoberturaId", coberturaActual.getObjetoOrigenId());
								pymeConfiguracionCobertura = pymeConfiguracionCoberturaDAO.buscarPorId(coberturaActual.getObjetoOrigenId());
								if(pymeConfiguracionCobertura.getTipoDeclaracion() == 3 || pymeConfiguracionCobertura.getDependeValor() == 2 )
									coberturaJSONObject.put("direccion","direccionEstructuras");
								else if(pymeConfiguracionCobertura.getTipoDeclaracion() == 4 || pymeConfiguracionCobertura.getDependeValor() == 8 ) 
									coberturaJSONObject.put("direccion","direccionContenidos");
								coberturaJSONObject.put("tasaSugerida",coberturaActual.getTasaSugerida());
								coberturaJSONObject.put("tasaIngresada",coberturaActual.getTasaIngresada());
								coberturaJSONObject.put("valorIngresado",coberturaActual.getValorIngresado());
								coberturaJSONObject.put("primaCalculada",coberturaActual.getPrimaCalculada());
								coberturaJSONObject.put("tipoOrigen",coberturaActual.getTipoOrigen());
								coberturaJSONObject.put("textoDeducible",coberturaActual.getTextoCompletoDeducible());
								coberturaJSONObject.put("idsDeducible",coberturaActual.getIdsDeducible());
								coberturaJSONObject.put("valoresDeducible",coberturaActual.getValoresDeducible());
								coberturaJSONObject.put("textosDeducible",coberturaActual.getTextosDeducible());
								coberturaJSONObject.put("valorAdicional",coberturaActual.getValorIngresado2());
								coberturasJSONArray.add(coberturaJSONObject);
							}
							result.put("coberturas", coberturasJSONArray);
							List<PymeObjetoCotizacionCobertura> listado2=objetoCCDAO.buscarPorCotizacionId(nuevoObjetoPyme.getObjetoPymesId(),"DEDUCIBLE DIRECCION");
							for(PymeObjetoCotizacionCobertura coberturaActual:listado2){
								coberturaDeduciblesJSONObject.put("configuracionCoberturaId", coberturaActual.getObjetoOrigenId());
								coberturaDeduciblesJSONObject.put("direccion","direccionDeducibles");
								coberturaDeduciblesJSONObject.put("tasaSugerida",coberturaActual.getTasaSugerida());
								coberturaDeduciblesJSONObject.put("tasaIngresada",coberturaActual.getTasaIngresada());
								coberturaDeduciblesJSONObject.put("valorIngresado",coberturaActual.getValorIngresado());
								coberturaDeduciblesJSONObject.put("primaCalculada",coberturaActual.getPrimaCalculada());
								coberturaDeduciblesJSONObject.put("tipoOrigen",coberturaActual.getTipoOrigen());
								coberturaDeduciblesJSONObject.put("textoDeducible",coberturaActual.getTextoCompletoDeducible());
								coberturaDeduciblesJSONObject.put("idsDeducible",coberturaActual.getIdsDeducible());
								coberturaDeduciblesJSONObject.put("valoresDeducible",coberturaActual.getValoresDeducible());
								coberturaDeduciblesJSONObject.put("textosDeducible",coberturaActual.getTextosDeducible());
								coberturaDeduciblesJSONObject.put("valorAdicional",coberturaActual.getValorIngresado2());
								coberturasDeduciblesJSONArray.add(coberturaDeduciblesJSONObject);
							}
							result.put("coberturasDeducibles", coberturasDeduciblesJSONArray);
						}
					}
				}
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
			result.put("error", " "+e.getMessage()+" || Error código: "+CodError);
			result.put("ex", e.getMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			e.printStackTrace();
		}			
	}
    
	private String GenerarContenido(String html, java.util.Hashtable<String, String> ParamValues){
		List<String> detectedParams = new ArrayList<String>();
		Pattern params=Pattern.compile("\\[[a-zA-Z0-9\\.]*\\]");
		Matcher mat=params.matcher(html);
		while(mat.find()) {
			detectedParams.add(mat.group());
		}
		
		for(String detectedParam:detectedParams)
		{
			String valor=ParamValues.get(detectedParam.replace("#", "").replace("#", ""));
			html=html.replace(detectedParam,valor);
		}
		return html;
	}

}
