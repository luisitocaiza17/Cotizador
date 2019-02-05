package com.qbe.cotizador.servlets.producto.ganadero;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Convert;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoXPuntoVentaDAO;
import com.qbe.cotizador.dao.cotizacion.TipoObjetoDAO;
import com.qbe.cotizador.dao.cotizacion.UplaDAO;
import com.qbe.cotizador.dao.entidad.AgenteDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.FirmasDigitalesDAO;
import com.qbe.cotizador.dao.entidad.RamoDAO;
import com.qbe.cotizador.dao.entidad.UnidadNegocioDAO;
import com.qbe.cotizador.dao.entidad.UnidadProduccionDAO;
import com.qbe.cotizador.dao.producto.ganadero.DeducibleGanaderoDAO;
import com.qbe.cotizador.dao.producto.ganadero.ObjetoGanaderoDAO;
import com.qbe.cotizador.dao.producto.ganadero.ObjetoGanaderoDeducibleDAO;
import com.qbe.cotizador.dao.producto.ganadero.ObjetoGanaderoDetalleDAO;
import com.qbe.cotizador.dao.producto.ganadero.ParametroXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.ganadero.RazaDAO;
import com.qbe.cotizador.dao.producto.ganadero.TipoGanadoDAO;
import com.qbe.cotizador.dao.seguridad.TipoVariableDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.Agente;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.DeducibleGanadero;
import com.qbe.cotizador.model.Direccion;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.FirmasDigitales;
import com.qbe.cotizador.model.FormaPago;
import com.qbe.cotizador.model.GrupoUsuarioAutorizacion;
import com.qbe.cotizador.model.ObjetoGanadero;
import com.qbe.cotizador.model.ObjetoGanaderoDeducible;
import com.qbe.cotizador.model.ObjetoGanaderoDetalle;
import com.qbe.cotizador.model.Pago;
import com.qbe.cotizador.model.ParametroXPuntoVenta;
import com.qbe.cotizador.model.ProductoXPuntoVenta;
import com.qbe.cotizador.model.Ramo;
import com.qbe.cotizador.model.Raza;
import com.qbe.cotizador.model.TipoGanado;
import com.qbe.cotizador.model.TipoVariable;
import com.qbe.cotizador.model.UnidadNegocio;
import com.qbe.cotizador.model.UnidadProduccion;
import com.qbe.cotizador.model.Upla;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.servicios.QBE.emisionGanaderoWS.WS_EMISIONProxy;
import com.qbe.cotizador.transaction.cotizacion.CotizacionDetalleTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.producto.ganadero.ObjetoGanaderoDeducibleTransaction;
import com.qbe.cotizador.transaction.producto.ganadero.ObjetoGanaderoDetalleTransaction;
import com.qbe.cotizador.transaction.producto.ganadero.ObjetoGanaderoTransaction;
//Import del servicio
import com.tandi.servicios.DTOs.ClienteDTO;
import com.tandi.servicios.DTOs.ConfiguracionPagoDTO;
import com.tandi.servicios.DTOs.DireccionDTO;
import com.tandi.servicios.DTOs.ErrorDTO;
import com.tandi.servicios.DTOs.GanadoDTO;
import com.tandi.servicios.DTOs.PagoDTO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ObjetoGanaderoController
 */
@WebServlet("/ObjetoGanaderoController")
public class ObjetoGanaderoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObjetoGanaderoController() {
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
		try{
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			
			CotizacionTransaction cotizacionTransaction= new CotizacionTransaction();
			ObjetoGanaderoDeducibleTransaction objetoGanaderoDeducibleTransaction = new ObjetoGanaderoDeducibleTransaction();
			ObjetoGanaderoTransaction objetoGanaderoTransaction = new ObjetoGanaderoTransaction();
			ObjetoGanaderoDetalleTransaction objetoGanaderoDetalleTransaction = new ObjetoGanaderoDetalleTransaction();
			CotizacionDetalleTransaction cotizacionDetalleTransaction= new CotizacionDetalleTransaction();			
			
			if(tipoConsulta.equalsIgnoreCase("obtenerAnimales"))
			{
				CotizacionDAO cotizacionDAO = new CotizacionDAO();
				CotizacionDetalleDAO cotizacionDetalleDAO= new CotizacionDetalleDAO();
				ObjetoGanaderoDAO objetoGanaderoDAO=new ObjetoGanaderoDAO();
				ObjetoGanaderoDetalleDAO objetoGanaderoDetalleDAO=new ObjetoGanaderoDetalleDAO();
				VariableSistemaDAO variableDAO = new VariableSistemaDAO();
				
				Cotizacion cotizacion = cotizacionDAO.buscarPorId(request.getParameter("cotizacionId"));
				List<CotizacionDetalle> cotizacionesDetalle = cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);								
				
				double valorPrima = 0;
				double valorAsegurado = 0;
				double valorPrimaDescuento = 0;
				double valorFinalPrima = 0;
				if(cotizacionesDetalle.size()!=0)
				{
					ObjetoGanadero objetoGanaderoActual=objetoGanaderoDAO.buscarObjetoGanaderoPorCotizacionDetalle(cotizacionesDetalle.get(0).getObjetoId());
					JSONArray animalesJSONArray= new JSONArray(); 
					ObjetoGanaderoDetalleDAO ganaderoDetalleDAO=new ObjetoGanaderoDetalleDAO();
					List<ObjetoGanaderoDetalle> detallesGanadero=ganaderoDetalleDAO.buscarPorObjetoGanadero(new BigInteger(objetoGanaderoActual.getId()));
					Raza raza;
					TipoGanado tipoGanado;
					RazaDAO razaDAO=new RazaDAO();
					TipoGanadoDAO tipoGanadoDAO=new TipoGanadoDAO(); 
					for(ObjetoGanaderoDetalle detActual: detallesGanadero)
					{
						JSONObject animalJSON = new JSONObject();
						animalJSON.put("id", detActual.getId());
						animalJSON.put("objetoGanaderoId", detActual.getObjetoGanaderoId());
						animalJSON.put("tipoId", detActual.getTipoId());
						tipoGanado=tipoGanadoDAO.buscarPorId(detActual.getTipoId());
						if(tipoGanado!=null)
							animalJSON.put("tipoNombre", tipoGanado.getNombre());
						else
							animalJSON.put("tipoNombre", "");
						animalJSON.put("numeroArete", detActual.getNumeroArete());
						animalJSON.put("numeroChip", detActual.getNumeroChip());
						raza=razaDAO.buscarPorId(detActual.getRazaId());
						if(raza!=null)
							animalJSON.put("razaNombre", raza.getNombre());
						else
							animalJSON.put("razaNombre", "");
						animalJSON.put("razaId", detActual.getRazaId());
						animalJSON.put("edad", detActual.getEdad());
						animalJSON.put("origen", detActual.getOrigen());
						animalJSON.put("procedencia", detActual.getProcedencia());
						animalJSON.put("valorAsegurar", detActual.getValorAsegurar());
						animalJSON.put("tasa", detActual.getTasa());
						animalesJSONArray.add(animalJSON);
					}
					result.put("animalesCotizacion", animalesJSONArray);
					
					//Calculo los valores
					for(CotizacionDetalle cotizacionDetalle : cotizacionesDetalle)
					{			
						valorPrima = valorPrima + cotizacionDetalle.getPrimaNetaItem();
						valorAsegurado = valorAsegurado+ cotizacionDetalle.getSumaAseguradaItem();
						valorPrima = Math.rint(valorPrima*100)/100;
						valorAsegurado = Math.rint(valorAsegurado*100)/100;
					}
					valorFinalPrima = valorPrima;
					result.put("porcentajeDescuento", 0.0);  
					TipoVariableDAO tipoVariableDao = new TipoVariableDAO();
					TipoVariable tipoVariable = tipoVariableDao.buscarPorId("3");
					List<VariableSistema> variablesistema = variableDAO.buscarTipoVariable(tipoVariable);
					double valorBase = 0;
					double valorDerechosEmision = 0;
					double valorSeguroCampesino = 0;
					double valorRecargoCampesino = 0;
					double valorSuperBancos = 0;
					double valorIva= 0;
					double valorSubTotal = 0;
					double valorTotal = 0;
					double valorPorcentajeSubsidio=0;
					double valorTopeSubsidio=0;
					result.put("valorPrima", valorFinalPrima);
					result.put("valorAsegurado", valorAsegurado);
					if(variablesistema.size() > 0) {
						//OBTENGO EL VALOR DEL SUBSIDIO Y DEL TOPE DEL SUBSIDIO
						for(VariableSistema variable : variablesistema) {
							if(variable.getNombre().equals("SUBSIDIO_GANADERO")){
								valorPorcentajeSubsidio=Double.parseDouble(variable.getValor());
							}
							if(variable.getNombre().equals("TOPE_SUBSIDIO_GANADERO")){
								valorTopeSubsidio=Double.parseDouble(variable.getValor());
							}
						}
						//SI EL VALOR FINAL DE LA PRIMA ES MENOR AL SUBSIDIO APLICO EL % DE SUBSIDIO
						if(valorFinalPrima<=valorTopeSubsidio){
							valorFinalPrima=valorFinalPrima-(valorFinalPrima*valorPorcentajeSubsidio/100);
						}
						for(VariableSistema variable : variablesistema) {
							if(variable.getNombre().equals("DERECHOS_EMISION_GANADERO")){
							   valorBase = Double.parseDouble(variable.getValor())+ valorFinalPrima;
							   valorDerechosEmision = Double.parseDouble(variable.getValor());
							   cotizacion.setImpDerechoEmision(valorDerechosEmision);
							   result.put("valorDerechosEmision", valorDerechosEmision);
							   
							}
							else if(variable.getNombre().equals("SEGURO_CAMPESINO_GANADERO")){
								valorSeguroCampesino = Math.rint(Double.parseDouble(variable.getValor())*valorFinalPrima/100*100)/100;
								valorBase = valorBase + valorSeguroCampesino;
								cotizacion.setImpSeguroCampesino(valorSeguroCampesino);
								result.put("valorSeguroCampesino", valorSeguroCampesino);
							}
							/*else if(variable.getNombre().equals("RECARGO_SEGURO_CAMPESINO_GANADERO")){
								InquiredServiceInterfaceService servicio = new InquiredServiceInterfaceService();
								valorRecargoCampesino = servicio.getInquiredServiceInterfacePort().consultarRecargoSeguroAgricola(cliente.getEntidad().getIdentificacion(), valorFinalPrima);
								cotizacion.setImpDerechoEmision(valorRecargoCampesino);
								result.put("valorRecargoCampesino", valorRecargoCampesino);
								valorBase = valorBase + valorRecargoCampesino;
								
							}*/
							else if(variable.getNombre().equals("SUPER_DE_BANCOS_GANADERO")){
								valorSuperBancos = Math.rint(Double.parseDouble(variable.getValor())*valorFinalPrima/100*100)/100;
								result.put("valorSuperBancos", valorSuperBancos);
								cotizacion.setImpSuperBancos(valorSuperBancos);
								valorBase = valorBase + valorSuperBancos;
								
							}
							
							else if(variable.getNombre().equals("SUBTOTAL_GANADERO")){
								valorSubTotal = Math.rint(valorBase*100)/100;
								result.put("valorSubTotal", valorSubTotal);
							}
							else if(variable.getNombre().equals("IVA_GANADERO")){
								valorIva = Math.rint(Double.parseDouble(variable.getValor())*valorSubTotal/100*100)/100;
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
				}
				//
			}
			if(tipoConsulta.equalsIgnoreCase("crear"))
			{
			
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId");
				String provinciaId = request.getParameter("provinciaId") == null ? "" : request.getParameter("provinciaId");
				String cantonId = request.getParameter("cantonId") == null ? "" : request.getParameter("cantonId");
				String parroquiaId = request.getParameter("parroquiaId") == null ? "" : request.getParameter("parroquiaId");
				String ubicacion = request.getParameter("ubicacion") == null ? "" : request.getParameter("ubicacion");
				String recinto = request.getParameter("recinto") == null ? "" : request.getParameter("recinto");
				String region = request.getParameter("region") == null ? "" : request.getParameter("region");
				String tipoProduccion = request.getParameter("tipoProduccion") == null ? "" : request.getParameter("tipoProduccion");
				String fincaAltitud = request.getParameter("fincaAltitud") == null ? "" : request.getParameter("fincaAltitud");
				String fincaTopografia1 = request.getParameter("fincaTopografia1") == null ? "" : request.getParameter("fincaTopografia1");
				String fincaTopografia2 = request.getParameter("fincaTopografia2") == null ? "" : request.getParameter("fincaTopografia2");
				String pastoTipo = request.getParameter("pastoTipo") == null ? "" : request.getParameter("pastoTipo");
				String fincaTopografia3 = request.getParameter("fincaTopografia3") == null ? "" : request.getParameter("fincaTopografia3");
				String pastoHectareas = request.getParameter("pastoHectareas") == null ? "" : request.getParameter("pastoHectareas");
				String pastoVolumen = request.getParameter("pastoVolumen") == null ? "" : request.getParameter("pastoVolumen");
				String pastoObservaciones = request.getParameter("pastoObservaciones") == null ? "" : request.getParameter("pastoObservaciones");
				String animalesVacunos = request.getParameter("animalesVacunos") == null ? "" : request.getParameter("animalesVacunos");
				String mortalidadVacas = request.getParameter("mortalidadVacas") == null ? "" : request.getParameter("mortalidadVacas");
				String mortalidadVacasCausa = request.getParameter("mortalidadVacasCausa") == null ? "" : request.getParameter("mortalidadVacasCausa");
				String mortalidadVaconasV = request.getParameter("mortalidadVaconasV") == null ? "" : request.getParameter("mortalidadVaconasV");
				String mortalidadVaconasVCausa = request.getParameter("mortalidadVaconasVCausa") == null ? "" : request.getParameter("mortalidadVaconasVCausa");
				String mortalidadVaconasF = request.getParameter("mortalidadVaconasF") == null ? "" : request.getParameter("mortalidadVaconasF");
				String mortalidadVaconasFCausa = request.getParameter("mortalidadVaconasFCausa") == null ? "" : request.getParameter("mortalidadVaconasFCausa");
				String mortalidadVaconasM = request.getParameter("mortalidadVaconasM") == null ? "" : request.getParameter("mortalidadVaconasM");
				String mortalidadVaconasMCausa = request.getParameter("mortalidadVaconasMCausa") == null ? "" : request.getParameter("mortalidadVaconasMCausa");
				String mortalidadToros = request.getParameter("mortalidadToros") == null ? "" : request.getParameter("mortalidadToros");
				String mortalidadTorosCausa = request.getParameter("mortalidadTorosCausa") == null ? "" : request.getParameter("mortalidadTorosCausa");
				String mortalidadToretes = request.getParameter("mortalidadToretes") == null ? "" : request.getParameter("mortalidadToretes");
				String mortalidadToretesCausa = request.getParameter("mortalidadToretesCausa") == null ? "" : request.getParameter("mortalidadToretesCausa");
				String mortalidadTeneros = request.getParameter("mortalidadTeneros") == null ? "" : request.getParameter("mortalidadTeneros");
				String mortalidadTenerosCausa = request.getParameter("mortalidadTenerosCausa") == null ? "" : request.getParameter("mortalidadTenerosCausa");
				String mortalidadTerneras = request.getParameter("mortalidadTerneras") == null ? "" : request.getParameter("mortalidadTerneras");
				String mortalidadTernerasCausa = request.getParameter("mortalidadTernerasCausa") == null ? "" : request.getParameter("mortalidadTernerasCausa");
				String alimentacionPastoreo = request.getParameter("alimentacionPastoreo") == null ? "" : request.getParameter("alimentacionPastoreo");
				String alimentacionCorte = request.getParameter("alimentacionCorte") == null ? "" : request.getParameter("alimentacionCorte");
				String alimentacionSogueo = request.getParameter("alimentacionSogueo") == null ? "" : request.getParameter("alimentacionSogueo");
				String alimentacionOtros = request.getParameter("alimentacionOtros") == null ? "" : request.getParameter("alimentacionOtros");
				String accesoAlAgua = request.getParameter("accesoAlAgua") == null ? "" : request.getParameter("accesoAlAgua");
				String vacunacionesAftosa = request.getParameter("vacunacionesAftosa") == null ? "" : request.getParameter("vacunacionesAftosa");
				String vacunacionesBrucelosis = request.getParameter("vacunacionesBrucelosis") == null ? "" : request.getParameter("vacunacionesBrucelosis");
				String vacunacionesTriple = request.getParameter("vacunacionesTriple") == null ? "" : request.getParameter("vacunacionesTriple");
				String vacunacionesLeptospirosis = request.getParameter("vacunacionesLeptospirosis") == null ? "" : request.getParameter("vacunacionesLeptospirosis");
				String vacunacionesIbrBvd = request.getParameter("vacunacionesIbrBvd") == null ? "" : request.getParameter("vacunacionesIbrBvd");
				String vacunacionesOtras = request.getParameter("vacunacionesOtras") == null ? "" : request.getParameter("vacunacionesOtras");
				String enfermedadMastitis = request.getParameter("enfermedadMastitis") == null ? "" : request.getParameter("enfermedadMastitis");
				String enfermedadPanadizo = request.getParameter("enfermedadPanadizo") == null ? "" : request.getParameter("enfermedadPanadizo");
				String enfermedadFiebreleche = request.getParameter("enfermedadFiebreleche") == null ? "" : request.getParameter("enfermedadFiebreleche");
				String enfermedadLesionubres = request.getParameter("enfermedadLesionubres") == null ? "" : request.getParameter("enfermedadLesionubres");
				String enfermedadNeumonias = request.getParameter("enfermedadNeumonias") == null ? "" : request.getParameter("enfermedadNeumonias");
				String enfermedadOtras = request.getParameter("enfermedadOtras") == null ? "" : request.getParameter("enfermedadOtras");
				String enfermedadCual = request.getParameter("enfermedadCual") == null ? "" : request.getParameter("enfermedadCual");
				String parasitosInternos = request.getParameter("parasitosInternos") == null ? "" : request.getParameter("parasitosInternos");
				String parasitosInternosTrata = request.getParameter("parasitosInternosTrata") == null ? "" : request.getParameter("parasitosInternosTrata");
				String parasitosInternosFrecu = request.getParameter("parasitosInternosFrecu") == null ? "" : request.getParameter("parasitosInternosFrecu");
				String parasitosExternos = request.getParameter("parasitosExternos") == null ? "" : request.getParameter("parasitosExternos");
				String parasitosExternosTrata = request.getParameter("parasitosExternosTrata") == null ? "" : request.getParameter("parasitosExternosTrata");
				String parasitosExternosFrecu = request.getParameter("parasitosExternosFrecu") == null ? "" : request.getParameter("parasitosExternosFrecu");
				String asistenciaVeterinaria = request.getParameter("asistenciaVeterinaria") == null ? "" : request.getParameter("asistenciaVeterinaria");
				String asistenciaVeterinariaFrecu = request.getParameter("asistenciaVeterinariaFrecu") == null ? "" : request.getParameter("asistenciaVeterinariaFrecu");
				String asistenciaVeterinariaProf = request.getParameter("asistenciaVeterinariaProf") == null ? "" : request.getParameter("asistenciaVeterinariaProf");
				String asistenciaVeterinariaTele = request.getParameter("asistenciaVeterinariaTele") == null ? "" : request.getParameter("asistenciaVeterinariaTele");
				String experienciaGanaderoAnios = request.getParameter("experienciaGanaderoAnios") == null ? "" : request.getParameter("experienciaGanaderoAnios");
				String espricipalIngreso = request.getParameter("espricipalIngreso") == null ? "" : request.getParameter("espricipalIngreso");
				//
				String listaAnimalesRazaId = request.getParameter("listaAnimalesRazaId") == null ? "" : request.getParameter("listaAnimalesRazaId");
				String listaAnimalTipo = request.getParameter("listaAnimalTipo") == null ? "" : request.getParameter("listaAnimalTipo");
				String listaAnimalArete = request.getParameter("listaAnimalArete") == null ? "" : request.getParameter("listaAnimalArete");
				String listaAnimalValor = request.getParameter("listaAnimalValor") == null ? "" : request.getParameter("listaAnimalValor");
				String listaAnimalEdad = request.getParameter("listaAnimalEdad") == null ? "" : request.getParameter("listaAnimalEdad");
				String listaAnimalChips = request.getParameter("listaAnimalChips") == null ? "" : request.getParameter("listaAnimalChips");
				String listaAnimalOrigen = request.getParameter("listaAnimalOrigen") == null ? "" : request.getParameter("listaAnimalOrigen");
				String listaAnimalProcedencia = request.getParameter("listaAnimalProcedencia") == null ? "" : request.getParameter("listaAnimalProcedencia");
				String listaAnimalColor = request.getParameter("listaAnimalColor") == null ? "" : request.getParameter("listaAnimalColor");
				
				TipoObjetoDAO tipoObjetoDAO=new TipoObjetoDAO();
				CotizacionDAO cotizacionDAO=new CotizacionDAO();
				CotizacionDetalleDAO cotizacionDetalleDAO=new CotizacionDetalleDAO();
				ObjetoGanaderoDAO objetoDAO=new ObjetoGanaderoDAO();
				ObjetoGanaderoDetalleDAO detalleDAO=new ObjetoGanaderoDetalleDAO();
				DeducibleGanaderoDAO deducibleGanaderoDAO=new DeducibleGanaderoDAO();
				ObjetoGanaderoDeducibleDAO objetoDeducibleDAO=new ObjetoGanaderoDeducibleDAO();
				
				//Valido si existe la cotizacion
				Cotizacion objetoCotizacion=cotizacionDAO.buscarPorId(cotizacionId);
				if(objetoCotizacion!=null)
				{
					//
					Double sumaAseguradaTotal=0.0;
					Double primaNetaTotal=0.0;
					
					//Cuento si ya hay al menos un detalle para la cotizacion
					List<CotizacionDetalle> listadoDetalles=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(objetoCotizacion);
					if(listadoDetalles.size()==0)
					{
						//Inserta el detalle de la cotización
						ObjetoGanadero objetoganadero=new ObjetoGanadero();
						objetoganadero.setProvinciaid(new BigInteger(provinciaId));
						objetoganadero.setCantonid(new BigInteger(cantonId));
						objetoganadero.setParroquiaid(new BigInteger(parroquiaId));
						objetoganadero.setUbicacion(ubicacion);
						objetoganadero.setRecinto(recinto);
						objetoganadero.setRegion(region);
						objetoganadero.setTipoProduccion(tipoProduccion);
						objetoganadero.setFincaAltitud(0);
						if(!fincaTopografia1.equals(""))
							objetoganadero.setFincaTopografia1(Float.parseFloat(fincaTopografia1));
						if(!fincaTopografia2.equals(""))
							objetoganadero.setFincaTopografia2(Float.parseFloat(fincaTopografia2));
						if(!fincaTopografia3.equals(""))
							objetoganadero.setFincaTopografia3(Float.parseFloat(fincaTopografia3));
						objetoganadero.setPastoTipoid(pastoTipo);
						if(!pastoHectareas.equals(""))
							objetoganadero.setPastoHectareas(Float.parseFloat(pastoHectareas));
						if(!pastoVolumen.equals(""))
							objetoganadero.setPastoVolumneanio(Float.parseFloat(pastoVolumen));
						objetoganadero.setPastoObservaciones(pastoObservaciones);
						if(!animalesVacunos.equals(""))
						objetoganadero.setAnimalesVacunos(Integer.parseInt(animalesVacunos));
						if(!mortalidadVacas.equals(""))
							objetoganadero.setMortalidadVacas(Integer.parseInt(mortalidadVacas));
						objetoganadero.setMortalidadVacasCausa(mortalidadVacasCausa);
						if(!mortalidadVaconasV.equals(""))
							objetoganadero.setMortalidadVaconasv(Integer.parseInt(mortalidadVaconasV));
						objetoganadero.setMortalidadVaconasvCausa(mortalidadVaconasVCausa);
						if(!mortalidadVaconasF.equals(""))
							objetoganadero.setMortalidadVaconasf(Integer.parseInt(mortalidadVaconasF));
						objetoganadero.setMortalidadVaconasfCausa(mortalidadVaconasFCausa);
						if(!mortalidadVaconasM.equals(""))
							objetoganadero.setMortalidadVaconasm(Integer.parseInt(mortalidadVaconasM));
						objetoganadero.setMortalidadVaconasmCausa(mortalidadVaconasMCausa);
						if(!mortalidadToros.equals(""))
							objetoganadero.setMortalidadToros(Integer.parseInt(mortalidadToros));
						objetoganadero.setMortalidadTorosCausa(mortalidadTorosCausa);
						if(!mortalidadToretes.equals(""))
							objetoganadero.setMortalidadToretes(Integer.parseInt(mortalidadToretes));
						objetoganadero.setMortalidadToretesCausa(mortalidadToretesCausa);
						if(!mortalidadTeneros.equals(""))
							objetoganadero.setMortalidadTerneros(Integer.parseInt(mortalidadTeneros));
						objetoganadero.setMortalidadTernerosCausa(mortalidadTenerosCausa);
						if(!mortalidadTerneras.equals(""))
							objetoganadero.setMortalidadTerneras(Integer.parseInt(mortalidadTerneras));
						objetoganadero.setMortalidadTernerasCausa(mortalidadTernerasCausa);
						objetoganadero.setAlimentacionPastoreo(alimentacionPastoreo);
						objetoganadero.setAlimentacionCorte(alimentacionCorte);
						objetoganadero.setAlimentacionSogueo(alimentacionSogueo);
						objetoganadero.setAlimentacionOtros(alimentacionOtros);
						objetoganadero.setAccesoAlAgua(accesoAlAgua);
						objetoganadero.setVacunacionesAftosa(vacunacionesAftosa);
						objetoganadero.setVacunacionesBrucelosis(vacunacionesBrucelosis);
						objetoganadero.setVacunacionesTriple(vacunacionesTriple);
						objetoganadero.setVacunacionesLeptospirosis(vacunacionesLeptospirosis);
						objetoganadero.setVacunacionesIbrbvd(vacunacionesIbrBvd);
						objetoganadero.setVacunacionesOtras(vacunacionesOtras);
						if(!enfermedadMastitis.equals(""))
							objetoganadero.setEnfermedadMastisis(Integer.parseInt(enfermedadMastitis));
						if(!enfermedadPanadizo.equals(""))
							objetoganadero.setEnfermedadPanadizo(Integer.parseInt(enfermedadPanadizo));
						if(!enfermedadFiebreleche.equals(""))
							objetoganadero.setEnfermedadFiebreleche(Integer.parseInt(enfermedadFiebreleche));
						if(!enfermedadLesionubres.equals(""))
							objetoganadero.setEnfermedadLesionubres(Integer.parseInt(enfermedadLesionubres));
						if(!enfermedadNeumonias.equals(""))
							objetoganadero.setEnfermedadNeumonias(Integer.parseInt(enfermedadNeumonias));
						if(!enfermedadOtras.equals(""))
							objetoganadero.setEnfermedadOtras(Integer.parseInt(enfermedadOtras));
						objetoganadero.setEnfermedadCual(enfermedadCual);
						objetoganadero.setParasitosInternos(parasitosInternos);
						objetoganadero.setParasitosInternosTrata(parasitosInternosTrata);
						objetoganadero.setParasitosInternosFrecu(parasitosInternosFrecu);
						objetoganadero.setParasitosExternos(parasitosExternos);
						objetoganadero.setParasitosExternosTrata(parasitosExternosTrata);
						objetoganadero.setParasitosExternosFrecu(parasitosExternosFrecu);
						objetoganadero.setAsistenciaVeterinaria(asistenciaVeterinaria);
						objetoganadero.setAsistenciaVeterinariaFrec(asistenciaVeterinariaFrecu);
						objetoganadero.setAsistenciaVeterinariaProf(asistenciaVeterinariaProf);
						objetoganadero.setAsistenciaVeterinariaTele(asistenciaVeterinariaTele);
						objetoganadero.setExperienciaGanaderoAnios(Integer.parseInt(experienciaGanaderoAnios));
						objetoganadero.setEsprincipalIngreso(espricipalIngreso);
						Date vigencia=new Date();
						objetoganadero.setFechaRegistro(vigencia);
						objetoganadero.setOrigen(1);
						
						objetoganadero=objetoGanaderoTransaction.crear(objetoganadero);
						
						//Grabo el listado de animales
						String[] arrlistaAnimalesRazaId = listaAnimalesRazaId.split(",");
						String[] arrlistaAnimalTipo = listaAnimalTipo.split(",");
						String[] arrlistaAnimalArete = listaAnimalArete.split(",");
						String[] arrlistaAnimalValor = listaAnimalValor.split(",");
						String[] arrlistaAnimalEdad = listaAnimalEdad.split(",");
						String[] arrlistaAnimalChips = listaAnimalChips.split(",");
						String[] arrlistaAnimalOrigen = listaAnimalOrigen.split(",");
						String[] arrlistaAnimalProcedencia = listaAnimalProcedencia.split(",");
						String[] arrlistaAnimalColor = listaAnimalColor.split(",");
						
						
						int contadorAnimales=0;
						for(int i=1; i< arrlistaAnimalesRazaId.length; i++){
							contadorAnimales++;
							sumaAseguradaTotal+=Double.valueOf(arrlistaAnimalValor[i]);
						}
						List<DeducibleGanadero> deduciblesganadero= deducibleGanaderoDAO.buscarPorNumeroAnimale(contadorAnimales, tipoProduccion);
						String categoriaDeducible=""; 
						double porcentajePrima=0;
						ObjetoGanaderoDeducible objetoDeducible;
						for (DeducibleGanadero deducibleg : deduciblesganadero)
						{
							if(contadorAnimales>=deducibleg.getRangoInicial() && contadorAnimales<=deducibleg.getRangoFinal())
							{
								//Insertamos el deducible que se aplico en la cotización
								objetoDeducible=new ObjetoGanaderoDeducible();
								objetoDeducible.setDeducibleCategoria(deducibleg.getCategoria());
								objetoDeducible.setNumeroAnimales(contadorAnimales);
								objetoDeducible.setObjetoGanaderoId(new BigInteger(objetoganadero.getId()));
								objetoDeducible.setDeduciblePrimaAplicada((int)deducibleg.getValorPrima());
								objetoDeducible.setDeducibleRangoInicial(deducibleg.getRangoInicial());
								objetoDeducible.setDeducibleRangoFinal(deducibleg.getRangoFinal());
								objetoDeducible.setDeducibleNumeroSiniestro(deducibleg.getNumeroSiniestro());
								objetoDeducible.setDeduciblePagoSiniestro(deducibleg.getPagoSiniestro());
								objetoDeducible.setDeducibleDeducible(deducibleg.getDeducible());
								primaNetaTotal=sumaAseguradaTotal*deducibleg.getValorPrima()/100;
								porcentajePrima=deducibleg.getValorPrima();
								objetoGanaderoDeducibleTransaction.crear(objetoDeducible);
							}
						}
						
						//Iserto los detalles del objeto ganadero
						ObjetoGanaderoDetalle detalle=new ObjetoGanaderoDetalle();
						for(int i=1; i< arrlistaAnimalesRazaId.length; i++){
							if(arrlistaAnimalTipo.length>0)
								detalle.setTipoId(arrlistaAnimalTipo[i]);
							detalle.setObjetoGanaderoId(new BigInteger(objetoganadero.getId()));
							if(arrlistaAnimalArete.length>0)
								detalle.setNumeroArete(arrlistaAnimalArete[i]);
							if(arrlistaAnimalChips.length>0)
								detalle.setNumeroChip(arrlistaAnimalChips[i]);
							if(arrlistaAnimalesRazaId.length>0)
								detalle.setRazaId(arrlistaAnimalesRazaId[i]);
							if(arrlistaAnimalOrigen.length>0)
								detalle.setOrigen(arrlistaAnimalOrigen[i]);
							if(arrlistaAnimalProcedencia.length>0)
								detalle.setProcedencia(arrlistaAnimalProcedencia[i]);
							if(arrlistaAnimalColor.length>0)
								detalle.setColor(arrlistaAnimalColor[i]);
							detalle.setTasa(porcentajePrima);
							if(arrlistaAnimalEdad.length>0)
								detalle.setEdad(Integer.parseInt(arrlistaAnimalEdad[i]));
							if(arrlistaAnimalValor.length>0)
								detalle.setValorAsegurar(Float.valueOf(arrlistaAnimalValor[i]));
							Float primaNetaPorAnimal= Float.valueOf(arrlistaAnimalValor[i])*(float)porcentajePrima/100;
							detalle.setValorPrima(primaNetaPorAnimal);
							objetoGanaderoDetalleTransaction.crear(detalle);
						}
						
						//Creo la cotización detalle
						CotizacionDetalle nuevoCotizacionDetalle=new CotizacionDetalle();
						nuevoCotizacionDetalle.setCotizacion(objetoCotizacion);
						nuevoCotizacionDetalle.setNecesitaInspeccion(false);
						nuevoCotizacionDetalle.setTipoObjetoId(tipoObjetoDAO.buscarPorNombre("Ganadero").getId());
						nuevoCotizacionDetalle.setObjetoId(objetoganadero.getId());
						nuevoCotizacionDetalle.setSumaAseguradaItem(sumaAseguradaTotal);
						nuevoCotizacionDetalle.setPrimaNetaItem(primaNetaTotal);
						cotizacionDetalleTransaction.crear(nuevoCotizacionDetalle);
						
						
						result.put("cotizacionId",objetoCotizacion.getId());
						result.put("objetoGanaderoId",objetoganadero.getId());
					}
					else
					{
						//Recupero el objeto detalle para actualizar
						CotizacionDetalle nuevoCotizacionDetalle=listadoDetalles.get(0);
						
						ObjetoGanadero objetoganadero;
						
						//Valido si existe el objeto ganadero para ese datalle, si existe lo recupero 
						//caso contrario me toca crearlo
						
						if(nuevoCotizacionDetalle.getObjetoId().equals(""))
							objetoganadero=new ObjetoGanadero();
						else
							objetoganadero=objetoDAO.buscarPorId(nuevoCotizacionDetalle.getObjetoId());
						
						objetoganadero.setProvinciaid(new BigInteger(provinciaId));
						objetoganadero.setCantonid(new BigInteger(cantonId));
						objetoganadero.setParroquiaid(new BigInteger(parroquiaId));
						objetoganadero.setUbicacion(ubicacion);
						objetoganadero.setRecinto(recinto);
						objetoganadero.setRegion(region);
						objetoganadero.setTipoProduccion(tipoProduccion);
						objetoganadero.setFincaAltitud(0);
						if(!fincaTopografia1.equals(""))
							objetoganadero.setFincaTopografia1(Float.parseFloat(fincaTopografia1));
						if(!fincaTopografia2.equals(""))
							objetoganadero.setFincaTopografia2(Float.parseFloat(fincaTopografia2));
						if(!fincaTopografia3.equals(""))
							objetoganadero.setFincaTopografia3(Float.parseFloat(fincaTopografia3));
						objetoganadero.setPastoTipoid(pastoTipo);
						if(!pastoHectareas.equals(""))
							objetoganadero.setPastoHectareas(Float.parseFloat(pastoHectareas));
						if(!pastoVolumen.equals(""))
							objetoganadero.setPastoVolumneanio(Float.parseFloat(pastoVolumen));
						objetoganadero.setPastoObservaciones(pastoObservaciones);
						if(!animalesVacunos.equals(""))
						objetoganadero.setAnimalesVacunos(Integer.parseInt(animalesVacunos));
						if(!mortalidadVacas.equals(""))
							objetoganadero.setMortalidadVacas(Integer.parseInt(mortalidadVacas));
						objetoganadero.setMortalidadVacasCausa(mortalidadVacasCausa);
						if(!mortalidadVaconasV.equals(""))
							objetoganadero.setMortalidadVaconasv(Integer.parseInt(mortalidadVaconasV));
						objetoganadero.setMortalidadVaconasvCausa(mortalidadVaconasVCausa);
						if(!mortalidadVaconasF.equals(""))
							objetoganadero.setMortalidadVaconasf(Integer.parseInt(mortalidadVaconasF));
						objetoganadero.setMortalidadVaconasfCausa(mortalidadVaconasFCausa);
						if(!mortalidadVaconasM.equals(""))
							objetoganadero.setMortalidadVaconasm(Integer.parseInt(mortalidadVaconasM));
						objetoganadero.setMortalidadVaconasmCausa(mortalidadVaconasMCausa);
						if(!mortalidadToros.equals(""))
							objetoganadero.setMortalidadToros(Integer.parseInt(mortalidadToros));
						objetoganadero.setMortalidadTorosCausa(mortalidadTorosCausa);
						if(!mortalidadToretes.equals(""))
							objetoganadero.setMortalidadToretes(Integer.parseInt(mortalidadToretes));
						objetoganadero.setMortalidadToretesCausa(mortalidadToretesCausa);
						if(!mortalidadTeneros.equals(""))
							objetoganadero.setMortalidadTerneros(Integer.parseInt(mortalidadTeneros));
						objetoganadero.setMortalidadTernerosCausa(mortalidadTenerosCausa);
						if(!mortalidadTerneras.equals(""))
							objetoganadero.setMortalidadTerneras(Integer.parseInt(mortalidadTerneras));
						objetoganadero.setMortalidadTernerasCausa(mortalidadTernerasCausa);
						objetoganadero.setAlimentacionPastoreo(alimentacionPastoreo);
						objetoganadero.setAlimentacionCorte(alimentacionCorte);
						objetoganadero.setAlimentacionSogueo(alimentacionSogueo);
						objetoganadero.setAlimentacionOtros(alimentacionOtros);
						objetoganadero.setAccesoAlAgua(accesoAlAgua);
						objetoganadero.setVacunacionesAftosa(vacunacionesAftosa);
						objetoganadero.setVacunacionesBrucelosis(vacunacionesBrucelosis);
						objetoganadero.setVacunacionesTriple(vacunacionesTriple);
						objetoganadero.setVacunacionesLeptospirosis(vacunacionesLeptospirosis);
						objetoganadero.setVacunacionesIbrbvd(vacunacionesIbrBvd);
						objetoganadero.setVacunacionesOtras(vacunacionesOtras);
						if(!enfermedadMastitis.equals(""))
							objetoganadero.setEnfermedadMastisis(Integer.parseInt(enfermedadMastitis));
						if(!enfermedadPanadizo.equals(""))
							objetoganadero.setEnfermedadPanadizo(Integer.parseInt(enfermedadPanadizo));
						if(!enfermedadFiebreleche.equals(""))
							objetoganadero.setEnfermedadFiebreleche(Integer.parseInt(enfermedadFiebreleche));
						if(!enfermedadLesionubres.equals(""))
							objetoganadero.setEnfermedadLesionubres(Integer.parseInt(enfermedadLesionubres));
						if(!enfermedadNeumonias.equals(""))
							objetoganadero.setEnfermedadNeumonias(Integer.parseInt(enfermedadNeumonias));
						if(!enfermedadOtras.equals(""))
							objetoganadero.setEnfermedadOtras(Integer.parseInt(enfermedadOtras));
						objetoganadero.setEnfermedadCual(enfermedadCual);
						objetoganadero.setParasitosInternos(parasitosInternos);
						objetoganadero.setParasitosInternosTrata(parasitosInternosTrata);
						objetoganadero.setParasitosInternosFrecu(parasitosInternosFrecu);
						objetoganadero.setParasitosExternos(parasitosExternos);
						objetoganadero.setParasitosExternosTrata(parasitosExternosTrata);
						objetoganadero.setParasitosExternosFrecu(parasitosExternosFrecu);
						objetoganadero.setAsistenciaVeterinaria(asistenciaVeterinaria);
						objetoganadero.setAsistenciaVeterinariaFrec(asistenciaVeterinariaFrecu);
						objetoganadero.setAsistenciaVeterinariaProf(asistenciaVeterinariaProf);
						objetoganadero.setAsistenciaVeterinariaTele(asistenciaVeterinariaTele);
						objetoganadero.setExperienciaGanaderoAnios(Integer.parseInt(experienciaGanaderoAnios));
						objetoganadero.setEsprincipalIngreso(espricipalIngreso);
						
						objetoganadero=objetoGanaderoTransaction.editar(objetoganadero);
						
						//Grabo el listado de animales
						String[] arrlistaAnimalesRazaId = listaAnimalesRazaId.split(",");
						String[] arrlistaAnimalTipo = listaAnimalTipo.split(",");
						String[] arrlistaAnimalArete = listaAnimalArete.split(",");
						String[] arrlistaAnimalValor = listaAnimalValor.split(",");
						String[] arrlistaAnimalEdad = listaAnimalEdad.split(",");
						String[] arrlistaAnimalChips = listaAnimalChips.split(",");
						String[] arrlistaAnimalOrigen = listaAnimalOrigen.split(",");
						String[] arrlistaAnimalProcedencia = listaAnimalProcedencia.split(",");
						String[] arrlistaAnimalColor = listaAnimalColor.split(",");
						 
						//Elimino el listado anterior de las vacas
						List<ObjetoGanaderoDetalle> listaAnteriorDetalles=detalleDAO.buscarPorObjetoGanadero(new BigInteger(objetoganadero.getId()));
						for(ObjetoGanaderoDetalle detActual: listaAnteriorDetalles)
							objetoGanaderoDetalleTransaction.eliminar(detActual);
						
						//Elimino los deducibles anteriore
						List<ObjetoGanaderoDeducible> listaAnteriorDeducibles=objetoDeducibleDAO.buscarPorObjetoGanadero(new BigInteger(objetoganadero.getId()));
						for(ObjetoGanaderoDeducible detActual: listaAnteriorDeducibles)
							objetoGanaderoDeducibleTransaction.eliminar(detActual);
						
						int contadorAnimales=0;
						for(int i=1; i< arrlistaAnimalesRazaId.length; i++){
							contadorAnimales++;
							sumaAseguradaTotal+=Double.valueOf(arrlistaAnimalValor[i]);
						}
						
						List<DeducibleGanadero> deduciblesganadero= deducibleGanaderoDAO.buscarPorNumeroAnimale(contadorAnimales, tipoProduccion);
						String categoriaDeducible=""; 
						double porcentajePrima=0;
						ObjetoGanaderoDeducible objetoDeducible;
						for (DeducibleGanadero deducibleg : deduciblesganadero)
						{
							if(contadorAnimales>=deducibleg.getRangoInicial() && contadorAnimales<=deducibleg.getRangoFinal())
							{
								//Insertamos el deducible que se aplico en la cotización
								objetoDeducible=new ObjetoGanaderoDeducible();
								objetoDeducible.setDeducibleCategoria(deducibleg.getCategoria());
								objetoDeducible.setNumeroAnimales(contadorAnimales);
								objetoDeducible.setObjetoGanaderoId(new BigInteger(objetoganadero.getId()));
								objetoDeducible.setDeduciblePrimaAplicada((int)deducibleg.getValorPrima());
								objetoDeducible.setDeducibleRangoInicial(deducibleg.getRangoInicial());
								objetoDeducible.setDeducibleRangoFinal(deducibleg.getRangoFinal());
								objetoDeducible.setDeducibleNumeroSiniestro(deducibleg.getNumeroSiniestro());
								objetoDeducible.setDeduciblePagoSiniestro(deducibleg.getPagoSiniestro());
								objetoDeducible.setDeducibleDeducible(deducibleg.getDeducible());
								primaNetaTotal=sumaAseguradaTotal*deducibleg.getValorPrima()/100;
								porcentajePrima=deducibleg.getValorPrima();
								objetoGanaderoDeducibleTransaction.crear(objetoDeducible);
							}
						}
						
						//Iserto los detalles del objeto ganadero
						ObjetoGanaderoDetalle detalle=new ObjetoGanaderoDetalle();
						for(int i=1; i< arrlistaAnimalesRazaId.length; i++){
							if(arrlistaAnimalTipo.length>0)
								detalle.setTipoId(arrlistaAnimalTipo[i]);
							detalle.setObjetoGanaderoId(new BigInteger(objetoganadero.getId()));
							if(arrlistaAnimalArete.length>0)
								detalle.setNumeroArete(arrlistaAnimalArete[i]);
							if(arrlistaAnimalChips.length>0)
								detalle.setNumeroChip(arrlistaAnimalChips[i]);
							if(arrlistaAnimalesRazaId.length>0)
								detalle.setRazaId(arrlistaAnimalesRazaId[i]);
							if(arrlistaAnimalOrigen.length>0)
								detalle.setOrigen(arrlistaAnimalOrigen[i]);
							if(arrlistaAnimalProcedencia.length>0)
								detalle.setProcedencia(arrlistaAnimalProcedencia[i]);
							if(arrlistaAnimalColor.length>0)
								detalle.setColor(arrlistaAnimalColor[i]);
							detalle.setTasa(porcentajePrima);
							if(arrlistaAnimalEdad.length>0)
								detalle.setEdad(Integer.parseInt(arrlistaAnimalEdad[i]));
							if(arrlistaAnimalValor.length>0)
								detalle.setValorAsegurar(Float.valueOf(arrlistaAnimalValor[i]));
							Float primaNetaPorAnimal= Float.valueOf(arrlistaAnimalValor[i])*(float)porcentajePrima/100;
							detalle.setValorPrima(primaNetaPorAnimal);
							objetoGanaderoDetalleTransaction.crear(detalle);
						}
						
						//Cambio los valores de la poliza
						nuevoCotizacionDetalle.setSumaAseguradaItem(sumaAseguradaTotal);
						nuevoCotizacionDetalle.setPrimaNetaItem(primaNetaTotal);
						cotizacionDetalleTransaction.editar(nuevoCotizacionDetalle);
						
						
						result.put("cotizacionId",objetoCotizacion.getId());
						result.put("objetoGanaderoId",objetoganadero.getId());
					}
					objetoCotizacion.setSumaAseguradaTotal(sumaAseguradaTotal);
					objetoCotizacion.setPrimaNetaTotal(primaNetaTotal.toString());
					objetoCotizacion.setEtapaWizard(2);
					cotizacionTransaction.editar(objetoCotizacion);
				}
			}
			
			if(tipoConsulta.equalsIgnoreCase("emitirPolizaGanadero"))
			{
				String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();
				String fechaInicioVigencia = request.getParameter("fechaInicioVigencia") == null ? "" : request.getParameter("fechaInicioVigencia").trim();
				
				CotizacionDAO cotizacionDAO=new CotizacionDAO();
				ObjetoGanaderoDAO objetoGanadeoDAO=new ObjetoGanaderoDAO();
				ObjetoGanaderoDetalleDAO objetoGanaderoDetalleDAO=new ObjetoGanaderoDetalleDAO();
				ClienteDAO clienteDAO=new ClienteDAO();
				
				
				Cotizacion cotizacion=cotizacionDAO.buscarPorId(cotizacionId);
				
				Cliente cliente= clienteDAO.buscarPorId(cotizacion.getClienteId().toString());
				
				//Obtengo el detalle de animales de la cotización
				GanadoDTO[] ganadosDTO=null;
				if(cotizacion.getCotizacionDetalles()!=null)
				{
					ObjetoGanadero objetoGanadero=objetoGanadeoDAO.buscarObjetoGanaderoPorCotizacionDetalle(cotizacion.getCotizacionDetalles().get(0).getObjetoId());
					List<ObjetoGanaderoDetalle> listadoAnimales=objetoGanaderoDetalleDAO.buscarPorObjetoGanadero(new BigInteger(objetoGanadero.getId()));
					ganadosDTO=new GanadoDTO[listadoAnimales.size()];
					ganadosDTO=generarObjetoGanadoDTO(listadoAnimales, objetoGanadero.getRegion());
				}
				
				//Obtengo las configuraciones del canal
				String ContenedorID="";
				String PlantillaID="";
				ParametroXPuntoVentaDAO parametroDAO=new ParametroXPuntoVentaDAO();
				ParametroXPuntoVenta parametroCanal=parametroDAO.obtenerPorPuntoVentaId(new BigInteger(cotizacion.getPuntoVenta().getId()));
				ContenedorID=parametroCanal.getContenedorEnsuranceid();
				PlantillaID=parametroCanal.getPlantillaEnsurancid();
				
				//Formo los objetos cliente DTO
				ClienteDTO clienteDTO=new ClienteDTO();
				if(cliente.getEntidad().getIdentificacion().equals(cotizacion.getAsegurado().getIdentificacion()))
					clienteDTO=generarObjetoAseguradoDTO(cotizacion.getAsegurado());
				else
					clienteDTO=generarObjetoClienteDTO(cliente.getEntidad());
				
				//Formo el objeto ClienteDTO pero es el asegurado)
				ClienteDTO aseguradoDTO=generarObjetoAseguradoDTO(cotizacion.getAsegurado());
				

			    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			    Date dateDesde = formatter.parse(fechaInicioVigencia);
			    long vigenciaDesdeLong = dateDesde.getTime()/1000;

			    Calendar cal = Calendar.getInstance();
			    cal.setTime(dateDesde);
			    cal.add(Calendar.YEAR, 1);
			    Date dateHasta = cal.getTime();
			    long vigenciaHastaLong = dateHasta.getTime()/1000;
		        
				//Date vigenciaDesde=new Date();
				//Calendar cal=Calendar.getInstance();
				//long vigenciaDesdeLong=vigenciaDesde.getTime();
				//cal.setTime(vigenciaDesde);
				//int anios=cotizacion.getVigenciaPoliza().getValor().intValue();
				//cal.add(Calendar.YEAR, anios);
				//long vigenciaHastaLong=cal.getTime().getTime();
				
				BigDecimal valorComision=new BigDecimal(0);
				BigDecimal porcentajeSubsidio=new BigDecimal(0);
				BigDecimal valorMaxSubsidio=new BigDecimal(0);
				
				AgenteDAO agenteDAO=new AgenteDAO();
				
				//Ramo ramo=ramoDAO.buscarPorId(cotizacion.getProducto().getRamoId().toString());
				Agente agente=agenteDAO.buscarPorId(cotizacion.getAgenteId().toString());
				
				//FirmasDigitales firma= firmaDAO.buscarPorNegocioProduccionRamoSucursal(ppv.getUnidadNegocio(), ppv.getUnidadProduccion(), ramo, cotizacion.getPuntoVenta().getSucursal());
				
				//UNIDAD DE NEGOCIO
				ProductoXPuntoVentaDAO dao = new ProductoXPuntoVentaDAO();
				ProductoXPuntoVenta productoXPuntoVenta=dao.buscarPorPuntoVenta(cotizacion.getPuntoVenta()).get(0);
			    UnidadNegocioDAO negocioDAO = new UnidadNegocioDAO();
			    UnidadNegocio unidadNegocio= negocioDAO.buscarPorId(productoXPuntoVenta.getUnidadNegocio().toString());
				UnidadProduccionDAO unidadProduccionDAO = new UnidadProduccionDAO();
				UnidadProduccion unidadProduccion= unidadProduccionDAO.buscarPorId(productoXPuntoVenta.getUnidadProduccion().toString());
				
				//Uso el servicio firma.getId()=21507997696
				WS_EMISIONProxy client=new WS_EMISIONProxy();
				ErrorDTO respuesta= client.emitir_SGQBE("8084", "cotizadorGA", "cotizadorGA", agente.getAgeEnsurance() , "8554838230195", 
														null, //configuracion de pago
														clienteDTO, //Cliente 
														aseguradoDTO, //Asegurado 
														clienteDTO, //propietario
														ganadosDTO, //Listado de animales
														"7208962",  //Ramo id
														"SG",  //ramo
														unidadNegocio.getUnEnsurance(), //unidad de negocio id
														unidadProduccion.getUpEnsurance(),  //unidad de producción id
														cotizacion.getPuntoVenta().getSucursal().getSucEnsurance(), vigenciaDesdeLong, vigenciaHastaLong, null, 
														valorComision, new BigDecimal(cotizacion.getSumaAseguradaTotal()), new BigDecimal(cotizacion.getPrimaNetaTotal()), 
														new BigDecimal(cotizacion.getTotalFactura()), vigenciaDesdeLong, 
														"1", //tipo documento 
														"0", //numero factura 
														"0",  //numero autorización SRI
														null, //cotizacion.getProducto().getId() 
														null, //ppv.getPlan().getId() 
														ContenedorID, //contenedor id 
														cotizacion.getId(), //numero poliza 
														null, //polizaid
														cotizacion.getPuntoVenta().getPtoEnsurance(), 
														"8126481", //tipo de riesgo
														"8060944", //Clase de riesgo
														"0", //Cobrador id 
														"101", //Tipo item id 
														"11141120", //Moneda id 
														PlantillaID, //ID de la plantilla 
														"1", //Firma sucursal
														porcentajeSubsidio, 
														valorMaxSubsidio, 
														"", //Lote de impresión 
														cotizacion.getId(), 
														"Agricola");
				String valor=respuesta.getTexto();
				try{
					if(valor!="-1")
					{
						EstadoDAO estadoDAO=new EstadoDAO();
						String[] resultados=valor.split("::");
						Timestamp ts=Timestamp.valueOf(resultados[1]);
						cotizacion.setNumeroFactura(resultados[0]);
						cotizacion.setFechaEmision(ts);
						cotizacion.setEstado(estadoDAO.buscarPorNombre("Emitido","Cotizacion"));
						cotizacionTransaction.editar(cotizacion);
						result.put("id", cotizacion.getId());
						result.put("success", Boolean.TRUE);
					}
					else{
						result.put("id", cotizacion.getId());
						result.put("success", Boolean.FALSE);
					}
				}
				catch (Exception e) {
					result.put("id", cotizacion.getId());
					result.put("success", Boolean.FALSE);
				}
			}
			
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

	
	private ClienteDTO generarObjetoClienteDTO(Entidad entidad)
	{
		ClienteDTO nuevoCliente=new ClienteDTO();
		nuevoCliente.setApellidos(entidad.getApellidos());
		nuevoCliente.setIdentificacion(entidad.getIdentificacion());
		nuevoCliente.setCorreoElectronico(entidad.getMail());
		nuevoCliente.setNombres(entidad.getNombreCompleto());
		nuevoCliente.setEntidadId(entidad.getEntEnsurance());
		nuevoCliente.setNombreCorto(entidad.getNombres());
		return nuevoCliente;
	}
	
	private ClienteDTO generarObjetoAseguradoDTO(Entidad entidad)
	{
		ClienteDTO nuevoCliente=new ClienteDTO();
		nuevoCliente.setApellidos(entidad.getApellidos());
		nuevoCliente.setIdentificacion(entidad.getIdentificacion());
		if(entidad.getTipoIdentificacion().getNombre().equals("Cédula"))
		{
			nuevoCliente.setTipoIdentificacion("c");
			nuevoCliente.setTipoEntidad("1");
		}
		else if(entidad.getTipoIdentificacion().getNombre().equals("Pasaporte"))
		{
			nuevoCliente.setTipoIdentificacion("p");
			nuevoCliente.setTipoEntidad("1");
		}
		else if(entidad.getTipoIdentificacion().getNombre().equals("RUC Persona Natural"))
		{
			nuevoCliente.setTipoIdentificacion("r");
			nuevoCliente.setTipoEntidad("1");
		}
		else if(entidad.getTipoIdentificacion().getNombre().equals("RUC Persona Jurídica"))
		{
			nuevoCliente.setTipoIdentificacion("r");
			nuevoCliente.setTipoEntidad("2");
		}
		nuevoCliente.setGenero("f");
		nuevoCliente.setCorreoElectronico(entidad.getMail());
		nuevoCliente.setNombres(entidad.getNombreCompleto());
		nuevoCliente.setEntidadId(entidad.getEntEnsurance());
		nuevoCliente.setNombreCorto(entidad.getNombres());
		
		//Obtener el cliente
		if (entidad.getClientes().size()!=0)
		{
			UplaDAO uplaDAO=new UplaDAO();
			Upla entidadUpla=uplaDAO.buscarPorCliente(entidad.getClientes().get(0));
			Direccion direccionPersona=entidadUpla.getDireccion();
			DireccionDTO[] direcciones=new DireccionDTO[1];
			DireccionDTO nuevaDireccion=new DireccionDTO();
			nuevaDireccion.setProvinciaId(direccionPersona.getCiudad().getProvincia().getCodigoSbs());
			nuevaDireccion.setCallePrincipal(direccionPersona.getCallePrincipal());
			nuevaDireccion.setCalleSecundaria(direccionPersona.getCalleSecundaria());
			nuevaDireccion.setNumero(direccionPersona.getNumero());
			nuevaDireccion.setTipoDireccion("1");
			nuevaDireccion.setPaisId("6815744");
			nuevaDireccion.setCantonId(direccionPersona.getCiudad().getId());
			if(direccionPersona.getParroquia()!=null)
				nuevaDireccion.setParroquiaId(direccionPersona.getParroquia().getId());
			nuevaDireccion.setDireccion(direccionPersona.getCallePrincipal() + " "+direccionPersona.getNumero()+" "+direccionPersona.getCalleSecundaria());
			direcciones[0]=nuevaDireccion;
			nuevoCliente.setDirecciones(direcciones);
		}
		return nuevoCliente;
	}
	
	private ConfiguracionPagoDTO generarObjetoPagoDTO(Pago pago)
	{
		ConfiguracionPagoDTO nuevoConfigPago=new ConfiguracionPagoDTO();
		if(pago.getAnioExpiracionTarjeta()!=null && pago.getMesExpiracionTarjeta()!=null)
		{
			Calendar fechaVencimiento=GregorianCalendar.getInstance();
			fechaVencimiento.set(Integer.parseInt(pago.getAnioExpiracionTarjeta()), Integer.parseInt(pago.getMesExpiracionTarjeta()), 1);
			nuevoConfigPago.setFechaVencimientoTarjeta(fechaVencimiento);
		}
		if(pago.getInstitucionFinanciera()!=null)
			nuevoConfigPago.setInstitucionaDebitar(pago.getInstitucionFinanciera().getNombre());
		if(pago.getNumeroCuentaTarjeta()!=null)
			nuevoConfigPago.setNumeroCuenta(pago.getNumeroCuentaTarjeta());
		nuevoConfigPago.setTipoCuenta(pago.getTipoCuenta());
		
		PagoDTO[] nuevosPagos=new PagoDTO[1];
		PagoDTO nuevoPago=new PagoDTO();
		nuevoPago.setEsCuota(false);
		Calendar fechaPago=GregorianCalendar.getInstance();
		Date fechaActual=new Date();
		fechaPago.set(fechaActual.getYear(), fechaActual.getMonth(), fechaActual.getDay());
		nuevoPago.setFechaPago(fechaPago);
		nuevoPago.setOrden(1);
		nuevoPago.setValorPago(new BigDecimal(pago.getValorTotal()));
		nuevosPagos[0]=nuevoPago;
		
		nuevoConfigPago.setPagos(nuevosPagos);
		
		return nuevoConfigPago;
	}
	
	private GanadoDTO[] generarObjetoGanadoDTO(List<ObjetoGanaderoDetalle> lstGanadoDetalles, String region)
	{
		int numero=lstGanadoDetalles.size();
		GanadoDTO[] ganadosDTO=new GanadoDTO[numero];
		GanadoDTO nuevoDetalle;
		int index=0;
		for(ObjetoGanaderoDetalle ganado:lstGanadoDetalles)
		{
			nuevoDetalle=new GanadoDTO();
			nuevoDetalle.setAreteoNombre(ganado.getNumeroArete());
			nuevoDetalle.setClaseRiesgoId("8060944");
			nuevoDetalle.setColor("");
			nuevoDetalle.setEdad(ganado.getEdad());
			nuevoDetalle.setGanadoId("");
			nuevoDetalle.setPropiedadId("");
			nuevoDetalle.setRazaId(ganado.getRazaId());
			nuevoDetalle.setSierraoCosta(region);
			nuevoDetalle.setTexto(ganado.getNumeroChip());
			nuevoDetalle.setTipoGanadoId(ganado.getTipoId());
			nuevoDetalle.setValorAsegurado(new BigDecimal(ganado.getValorAsegurar()));
			nuevoDetalle.setValorAseguradoGanado(new BigDecimal(ganado.getValorAsegurar()));
			nuevoDetalle.setTipoItemId("101");
			nuevoDetalle.setTipoRiesgoId("8126481");
			nuevoDetalle.setValorPrimaGanado(new BigDecimal(ganado.getValorPrima()));
			ganadosDTO[index]=nuevoDetalle;
			index++;
		}
		return ganadosDTO;
	}
}

