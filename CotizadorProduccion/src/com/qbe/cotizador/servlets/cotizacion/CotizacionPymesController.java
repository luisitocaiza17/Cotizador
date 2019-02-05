package com.qbe.cotizador.servlets.cotizacion;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.cotizacion.CoberturaDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionCoberturaDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoDAO;
import com.qbe.cotizador.dao.cotizacion.TipoObjetoDAO;
import com.qbe.cotizador.dao.cotizacion.VigenciaPolizaDAO;
import com.qbe.cotizador.dao.entidad.ActividadEconomicaDAO;
import com.qbe.cotizador.dao.entidad.AgenteDAO;
import com.qbe.cotizador.dao.entidad.CiudadDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.DetalleProductoDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.entidad.TipoIdentificacionDAO;
import com.qbe.cotizador.dao.producto.pymes.ObjetoPymesDAO;
import com.qbe.cotizador.dao.producto.pymes.TipoConstruccionDAO;
import com.qbe.cotizador.dao.producto.pymes.TipoOcupacionDAO;
import com.qbe.cotizador.model.ActividadEconomica;
import com.qbe.cotizador.model.Agente;
import com.qbe.cotizador.model.Ciudad;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cobertura;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionCobertura;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.ObjetoPyme;
import com.qbe.cotizador.model.Producto;
import com.qbe.cotizador.model.Provincia;
import com.qbe.cotizador.model.TipoConstruccion;
import com.qbe.cotizador.model.TipoObjeto;
import com.qbe.cotizador.model.TipoOcupacion;
import com.qbe.cotizador.model.Upla;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.transaction.cotizacion.CotizacionDetalleTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.entidad.ClienteTransaction;
import com.qbe.cotizador.transaction.entidad.EntidadTransaction;
import com.qbe.cotizador.transaction.producto.pymes.ObjetoPymesTransaction;

/**
 * Servlet implementation class CotizacionPymesController
 */
@WebServlet("/CotizacionPymesController")
public class CotizacionPymesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    static final String grupoCoberturaIncendio = "1528073304335";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CotizacionPymesController() {
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
		JSONObject result = new JSONObject();
		try{
			String codigoEntidadEnsurance = request.getParameter("codigoEntidadEnsurance") == null ? "" : request.getParameter("codigoEntidadEnsurance").trim();
			String productoId = request.getParameter("productoId") == null ? "" : request.getParameter("productoId").trim();
			String puntoVentaId = request.getParameter("puntoVentaId") == null ? "" : request.getParameter("puntoVentaId").trim();
			String vigenciaPoliza = request.getParameter("vigenciaPoliza") == null ? "" : request.getParameter("vigenciaPoliza").trim();
			String tipoIdentificacion = request.getParameter("tipoIdentificacion") == null ? "" : request.getParameter("tipoIdentificacion").trim();
			String identificacion = request.getParameter("identificacion") == null ? "" : request.getParameter("identificacion").trim();
			String nombres = request.getParameter("nombres") == null ? "" : request.getParameter("nombres").trim();
			String apellidos = request.getParameter("apellidos") == null ? "" : request.getParameter("apellidos").trim();
			String nombreCompleto = request.getParameter("nombreCompleto") == null ? "" : request.getParameter("nombreCompleto").trim();
			String agenteId = request.getParameter("agenteId") == null ? "" : request.getParameter("agenteId").trim();
			String mail = request.getParameter("mail") == null ? "" : request.getParameter("mail").trim();
			String cotizacionId = request.getParameter("cotizacionId") == null ? "" : request.getParameter("cotizacionId").trim();
			String porcentajeComision = request.getParameter("porcentajeComisionAgente") == null ? "" : request.getParameter("porcentajeComisionAgente").trim();		
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String ubicacionId = request.getParameter("ubicacionId") == null ? "" : request.getParameter("ubicacionId").trim();
			String callePrincipal = request.getParameter("callePrincipal") == null ? "" : request.getParameter("callePrincipal").trim();
			String calleSecundaria = request.getParameter("calleSecundaria") == null ? "" : request.getParameter("calleSecundaria").trim();
			String nombreEdificioConjunto = request.getParameter("nombrePredio") == null ? "" : request.getParameter("nombrePredio").trim();
			String numeroOficinaPiso = request.getParameter("numPredio") == null ? "" : request.getParameter("numPredio").trim();
			String sector = request.getParameter("sector") == null ? "" : request.getParameter("sector").trim();
			String provinciaId = request.getParameter("provinciaUbicacion") == null ? "" : request.getParameter("provinciaUbicacion").trim();
			String ciudadId = request.getParameter("ciudadUbicacion") == null ? "" : request.getParameter("ciudadUbicacion").trim();
			String numeroDireccion = request.getParameter("numDireccion") == null ? "" : request.getParameter("numDireccion").trim();
			
			// Informacion enviada de datos adicionales
			String actividadEconomicaId = request.getParameter("actividadEconomicaUbicacion") == null ? "" : request.getParameter("actividadEconomicaUbicacion").trim();
			String descripcionSiniestro = request.getParameter("descripcionSiniestro") == null ? "" : request.getParameter("descripcionSiniestro").trim();
			String valorSiniestro = request.getParameter("valorSiniestro") == null ? "" : request.getParameter("valorSiniestro").trim();
			String contabilidad = request.getParameter("contabilidad") == null ? "" : request.getParameter("contabilidad").trim();
			String constituida = request.getParameter("constituida") == null ? "" : request.getParameter("constituida").trim();
			String tipoConstruccionId = request.getParameter("tipo_construccion") == null ? "" : request.getParameter("tipo_construccion").trim();
			String tipoOcupacionId = request.getParameter("tipo_ocupacion") == null ? "" : request.getParameter("tipo_ocupacion").trim();
			String anioConstruccion = request.getParameter("anioConstruccion") == null ? "" : request.getParameter("anioConstruccion").trim();
			String numPisos = request.getParameter("numPiso") == null ? "" : request.getParameter("numPiso").trim();
			String extintor = request.getParameter("extintor") == null ? "" : request.getParameter("extintor").trim();
			String detectorHumo = request.getParameter("detectorHumo") == null ? "" : request.getParameter("detectorHumo").trim();
			String sprinklers = request.getParameter("sprinklers") == null ? "" : request.getParameter("sprinklers").trim();
			String alarma = request.getParameter("alarma") == null ? "" : request.getParameter("alarma").trim();
			String guardiania = request.getParameter("guardiania") == null ? "" : request.getParameter("guardiania").trim();
			String txtOtros = request.getParameter("txtOtros") == null ? "" : request.getParameter("txtOtros").trim();			
			
			EntidadTransaction entidadTransaction= new EntidadTransaction();
			ClienteTransaction clienteTransaction= new ClienteTransaction();
			CotizacionTransaction cotizacionTransaction= new CotizacionTransaction();
			ObjetoPymesTransaction objetoPymesTransaction = new ObjetoPymesTransaction();
			CotizacionDetalleTransaction cotizacionDetalleTransaction= new CotizacionDetalleTransaction();
			
			HttpSession session = request.getSession(true);
			Usuario usuario = (Usuario) session.getAttribute("usuario");
			
			Cotizacion cotizacion = new Cotizacion();
			CotizacionDAO cotizacionDAO = new CotizacionDAO();

			CotizacionDetalleDAO cotizacionDetalleDAO= new CotizacionDetalleDAO();
			CotizacionDetalle cotizacionDetalle = new CotizacionDetalle();

			if(tipoConsulta.equalsIgnoreCase("crear")){
				EntidadDAO entidadDAO = new EntidadDAO();
				Entidad entidad = new Entidad();
				
				ClienteDAO clienteDAO = new ClienteDAO();
				Cliente cliente = new Cliente();
				
				TipoIdentificacionDAO tipoDAO = new TipoIdentificacionDAO();
				
				entidad = entidadDAO.buscarEntidadPorIdentificacion(request.getParameter("identificacion"));

				
				//si no existe la entidad en cotizador
				//if(entidad.getId()==null){
					if(!identificacion.equals(""))
						entidad.setIdentificacion(identificacion);
					
					if(!codigoEntidadEnsurance.equals(""))
						entidad.setEntEnsurance(codigoEntidadEnsurance);
					
					if(!tipoIdentificacion.equals(""))
						entidad.setTipoIdentificacion(tipoDAO.buscarPorId(tipoIdentificacion));
					
					entidad.setMail(mail);
					
					if(tipoDAO.buscarPorId(entidad.getTipoIdentificacion().getId()).getId().equalsIgnoreCase("4")){
						entidad.setNombres("");
						entidad.setApellidos("");
						entidad.setNombreCompleto(nombreCompleto.toUpperCase());
					}else{
						entidad.setNombres(nombres.toUpperCase());
						entidad.setApellidos(apellidos.toUpperCase());
						entidad.setNombreCompleto(nombres.toUpperCase() + " " + apellidos.toUpperCase());
					}
					
					if(entidad.getId()==null)
						entidad=entidadTransaction.crear(entidad);
					else
						entidad=entidadTransaction.editar(entidad);
				
				cliente=clienteDAO.buscarPorEntidadId(entidad);
				
				if(cliente.getId()==null){
					ActividadEconomica actividad = new ActividadEconomica();
					ActividadEconomicaDAO actividadDAO = new ActividadEconomicaDAO();
					actividad = actividadDAO.buscarPorNombre("Ninguno");
					
					cliente.setEntidad(entidad);
					cliente.setActividadEconomica(actividad);
					cliente.setActivo(true);
					cliente=clienteTransaction.crear(cliente);
					//falta en codigo del ensurance
				}
				
				ProductoDAO productoDAO = new ProductoDAO();
					
				EstadoDAO estadoDAO = new EstadoDAO();
					
				PuntoVentaDAO pvDAO = new PuntoVentaDAO();
				
				VigenciaPolizaDAO vpDAO= new  VigenciaPolizaDAO();
				
				TipoObjetoDAO toDAO = new TipoObjetoDAO();
				
				if(cotizacionId!=null&&!cotizacionId.equals(""))
					cotizacion=cotizacionDAO.buscarPorId(cotizacionId);
				
				if(!puntoVentaId.equals(""))
					cotizacion.setPuntoVenta(pvDAO.buscarPorId(puntoVentaId));
				
				if(!vigenciaPoliza.equals(""))
					cotizacion.setVigenciaPoliza(vpDAO.buscarPorId(vigenciaPoliza));
				else
					cotizacion.setVigenciaPoliza(vpDAO.buscarPorId("1"));
				
				if(!agenteId.equals("")){
					cotizacion.setAgenteId(BigInteger.valueOf(Long.valueOf(agenteId)));				
				}
				
				if(!porcentajeComision.equals("")){
		 	        cotizacion.setPorcentajeComision(Double.parseDouble(porcentajeComision)); // Porcentaje comision agente
				}
				cotizacion.setClienteId(BigInteger.valueOf(Long.valueOf(cliente.getId())));
				
				if(!productoId.equals("")){
					Producto productoDefectoVH = productoDAO.buscarPorId(productoId);		
					cotizacion.setProducto(productoDefectoVH);
				}
				
				cotizacion.setEstado(estadoDAO.buscarPorNombre("Borrador","Cotizacion"));
				cotizacion.setTipoObjeto(toDAO.buscarPorNombre("PYMES"));
				cotizacion.setUsuario(usuario);
				Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
				
				if(!fechaActual.equals(""))
					cotizacion.setFechaElaboracion(fechaActual);
				
				if(cotizacion.getEtapaWizard()<1){
					cotizacion.setEtapaWizard(1);
				}
				
				if(cotizacion.getId()!=null)
					cotizacion = cotizacionTransaction.editar(cotizacion);	
				else
					cotizacion = cotizacionTransaction.crear(cotizacion);
				result.put("cotizacionId",cotizacion.getId());
			}
			
			// Metodo agregar un vehiculo a la cotizacionayanez
			if(tipoConsulta.equalsIgnoreCase("agregarUbicacionCotizacion")){								
				ObjetoPymesDAO objetoPymesDAO = new ObjetoPymesDAO();
				ObjetoPyme objetoPymes = new ObjetoPyme();
				
				if(!cotizacionId.equals("")){
					cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
					//evaldez cambiamos la etapa de la cotizacion
					if(cotizacion.getEtapaWizard()<2){
						cotizacion.setEtapaWizard(2);
						cotizacion=cotizacionTransaction.editar(cotizacion);
					}
				}
				
				TipoObjetoDAO tipoObjetoDAO = new TipoObjetoDAO();				
				TipoObjeto tipoObjeto = tipoObjetoDAO.buscarPorNombre("PYMES");
				
				ProvinciaDAO provinciaDAO = new ProvinciaDAO();				
				Provincia provincia =  provinciaDAO.buscarPorId(provinciaId);
				
				CiudadDAO ciudadDAO = new CiudadDAO();
				Ciudad ciudad = ciudadDAO.buscarPorId(ciudadId);

				ActividadEconomicaDAO actividadEconomicaDAO = new ActividadEconomicaDAO();
				ActividadEconomica actividadEconomica = actividadEconomicaDAO.buscarPorId(actividadEconomicaId);
				
				TipoConstruccionDAO tipoConstruccionDAO = new TipoConstruccionDAO();
				TipoConstruccion tipoConstruccion = tipoConstruccionDAO.buscarPorId(tipoConstruccionId);

				TipoOcupacionDAO tipoOcupacionDAO = new TipoOcupacionDAO();
				TipoOcupacion tipoOcupacion = tipoOcupacionDAO.buscarPorId(tipoOcupacionId);
				
				CotizacionDetalle cotizacionDetalleExiste = new CotizacionDetalle();
				
				if(!ubicacionId.equals("")){
					objetoPymes = objetoPymesDAO.buscarPorId(ubicacionId);
					cotizacionDetalleExiste = cotizacionDetalleDAO.buscarCotizacionDetalleIdYObjetoId(ubicacionId, cotizacion);
					if(cotizacionDetalleExiste.getId() != null)
						cotizacionDetalle = cotizacionDetalleExiste;
				}
				
				if(!callePrincipal.equals(""))
					objetoPymes.setCallePrincipal(callePrincipal);

				if(!calleSecundaria.equals(""))
					objetoPymes.setCalleSecundaria(calleSecundaria);
					
				if(!numeroDireccion.equals(""))
					objetoPymes.setNumeroDireccion(numeroDireccion);
				
				if(!sector.equals(""))
					objetoPymes.setSector(sector);
				
				if(!nombreEdificioConjunto.equals(""))
					objetoPymes.setNombreEdificioConjunto(nombreEdificioConjunto);
				
				if(!numeroOficinaPiso.equals(""))
					objetoPymes.setNumeroOficinaPiso(numeroOficinaPiso);
				
				if(!provincia.equals(""))
					objetoPymes.setProvincia(provincia);
				
				if(!ciudad.equals(""))
					objetoPymes.setCiudad(ciudad);
				
				if(!actividadEconomica.equals(""))
					objetoPymes.setActividadEconomica(actividadEconomica);
				
				if(!descripcionSiniestro.equals(""))
					objetoPymes.setDescripcionSiniestroAnterior(descripcionSiniestro);
				
				if(!valorSiniestro.equals(""))
					objetoPymes.setValorSiniestroAnterior(new Double(valorSiniestro));
				
				if(contabilidad.equals("1"))
					objetoPymes.setContabilidadFormal(true);

				if(contabilidad.equals("0"))
					objetoPymes.setContabilidadFormal(false);
				
				if(constituida.equals("1"))
					objetoPymes.setContituidaHaceDosAnios(true);

				if(constituida.equals("0"))
					objetoPymes.setContituidaHaceDosAnios(false);

				if(tipoConstruccion.getId() != null)
					objetoPymes.setTipoConstruccion(tipoConstruccion);
				
				if(tipoOcupacion.getId() != null)
					objetoPymes.setTipoOcupacion(tipoOcupacion);
				
				if(!anioConstruccion.equals(""))
					objetoPymes.setAnioConstruccion(new Integer(anioConstruccion));
				
				if(!numPisos.equals(""))
					objetoPymes.setNumeroPisos(new Integer(numPisos));
				
				if(extintor.equals("1"))
					objetoPymes.setExtintores(true);
				
				if(extintor.equals("0"))
					objetoPymes.setExtintores(false);
				
				if(detectorHumo.equals("1"))
					objetoPymes.setDetectorHumo(true);

				if(detectorHumo.equals("0"))
					objetoPymes.setDetectorHumo(false);
				
				if(sprinklers.equals("1"))
					objetoPymes.setSprinklers(true);

				if(sprinklers.equals("0"))
					objetoPymes.setSprinklers(false);

				if(alarma.equals("1"))
					objetoPymes.setAlarmaMonitoreada(true);

				if(alarma.equals("0"))
					objetoPymes.setAlarmaMonitoreada(false);

				if(guardiania.equals("1"))
					objetoPymes.setGuardiania(true);
				
				if(guardiania.equals("0"))
					objetoPymes.setGuardiania(false);
				
				if(!txtOtros.equals(""))
					objetoPymes.setOtros(txtOtros);
				
				if(!ubicacionId.equals("")){
					objetoPymes = objetoPymesTransaction.editar(objetoPymes);
				}else{					
	
					objetoPymes = objetoPymesTransaction.crear(objetoPymes);
				}

				
				cotizacionDetalle.setTipoObjetoId(tipoObjeto.getId());
				cotizacionDetalle.setObjetoId(objetoPymes.getId());
				cotizacionDetalle.setPrimaNetaItem(0);
				cotizacionDetalle.setSumaAseguradaItem(0);
				cotizacionDetalle.setTasa(0);
				cotizacionDetalle.setValorExtras(0);
				cotizacionDetalle.setPaqueteId(null);
				
				if (cotizacionDetalleExiste.getId() != null ){
					cotizacionDetalle = cotizacionDetalleTransaction.editar(cotizacionDetalle);
				}else{
					cotizacionDetalle.setCotizacion(cotizacion);
					cotizacionDetalle = cotizacionDetalleTransaction.crear(cotizacionDetalle);
				}
					
				result.put("ubicacionId", objetoPymes.getId());
			}			
			
			if(tipoConsulta.equalsIgnoreCase("grabarCoberturasUbicaciones")){
				String listaCoberturaId = request.getParameter("listaCoberturaId") == null ? "" : request.getParameter("listaCoberturaId").trim();
				String listaValor = request.getParameter("listaValor") == null ? "" : request.getParameter("listaValor").trim();
				String listaCotizacionId = request.getParameter("listaCotizacionId") == null ? "" : request.getParameter("listaCotizacionId").trim();
				String listaObjetoId = request.getParameter("listaObjetoId") == null ? "" : request.getParameter("listaObjetoId").trim();
				
				String [] arrCoberturaId = listaCoberturaId.split(",");
				String [] arrValor = listaValor.split(",");
				String [] arrCotizacionId = listaCotizacionId.split(",");
				String [] arrObjetoId = listaObjetoId.split(",");

				for(int i=0; i<arrCoberturaId.length; i++){
					String coberturaId = arrCoberturaId[i];
					String valor = arrValor[i];
					cotizacionId = arrCotizacionId[i];
					String objetoId = arrObjetoId[i];
					
					if(!cotizacionId.equals(cotizacion.getId()))
							cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
			
					cotizacionDetalle = cotizacionDetalleDAO.buscarCotizacionDetalleIdYObjetoId(objetoId, cotizacion);

					CoberturaDAO coberturaDAO = new CoberturaDAO();
					Cobertura cobertura = coberturaDAO.buscarPorId(coberturaId);
					
					CotizacionCoberturaDAO cotizacionCoberturaDAO = new CotizacionCoberturaDAO();
					CotizacionCobertura cotizacionCobertura = new CotizacionCobertura();
									
//					DetalleProductoDAO detalleProductoDAO = new DetalleProductoDAO();
//					Object [] detalleProducto=null;
//					List<Object> datos = detalleProductoDAO.buscarPorProductoTasaPymes(productoId, coberturaId);
//					
//		             if(datos.size() > 0){
//		            	detalleProducto = (Object[]) datos.get(0);
//					    Double tasa = new Double(detalleProducto[6].toString());
//						Double montoFijo = new Double(valor);
//					    cotizacionCobertura.setCotizacionDetalle(cotizacionDetalle);
//						cotizacionCobertura.setCobertura(cobertura);
//						cotizacionCobertura.setMontoFijo(montoFijo);
//							
//						if(grupoCoberturaIncendio.equals(detalleProducto[4].toString()))
//							cotizacionCobertura.setValorPrima((tasa * montoFijo)/1000);
//						else
//							cotizacionCobertura.setValorPrima((tasa * montoFijo)/100);
//						
//						cotizacionCoberturaDAO.crear(cotizacionCobertura);
//		             }
				}
			}
			
			if(tipoConsulta.equalsIgnoreCase("eliminar"))
			{		
				String cotizacion_id = request.getParameter("codigo") == null ? "" : request.getParameter("codigo");
				cotizacion = cotizacionDAO.buscarPorId(cotizacion_id);
			
				if(cotizacion.getId()!=null)
					cotizacionTransaction.eliminar(cotizacion);
			}
			
			if(tipoConsulta.equalsIgnoreCase("elminarObjetoDetalle")){
				cotizacion = cotizacionDAO.buscarPorId(cotizacionId);
				result.put("msgEliminarObjeto",  eliminarUbicacionCotizacionDetalle(cotizacion, ubicacionId));
			}
			
			if(tipoConsulta.equalsIgnoreCase("encontrarPorId")){
				result.put("datosCotizacion",  encontrarPorId(cotizacionId));
			}
		
			
			result.put("success", Boolean.TRUE);
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

	public JSONObject encontrarPorId(String id){
		
		CotizacionDAO cotizacionDAO=new CotizacionDAO();
		Cotizacion cotizacion = cotizacionDAO.buscarPorId(id);
		JSONObject retorno= new JSONObject();
		ClienteDAO clienteDAO = new ClienteDAO();
		Cliente cliente= clienteDAO.buscarPorId(cotizacion.getClienteId().toString());
		
		AgenteDAO agenteDAO = new AgenteDAO();
		Agente agente= agenteDAO.buscarPorId(cotizacion.getAgenteId().toString());

		JSONObject etapa1= new JSONObject();
		if(cotizacion.getEtapaWizard()>=1){
			etapa1.put("puntoVenta", cotizacion.getPuntoVenta().getId());
			etapa1.put("vigenciaPoliza", cotizacion.getVigenciaPoliza().getId());
			etapa1.put("agente", agente.getId());
			etapa1.put("porComisionAgente", cotizacion.getPorcentajeComision());
			etapa1.put("tipoIdentificacion", cliente.getEntidad().getTipoIdentificacion().getId() );
			etapa1.put("identificacion", cliente.getEntidad().getIdentificacion());
			etapa1.put("nombres", cliente.getEntidad().getNombres());
			etapa1.put("apellidos", cliente.getEntidad().getApellidos());
			etapa1.put("productoId", cotizacion.getProducto().getId());
			retorno.put("etapa1", etapa1);
		}
		
		
		//fin etapa1
		
		//etapa2
		
		JSONObject etapa2= new JSONObject();
		if(cotizacion.getEtapaWizard()>=2){
			JSONObject objeto= new JSONObject();
			List<CotizacionDetalle> detalles= cotizacion.getCotizacionDetalles();		
			JSONArray objetos= new JSONArray(); 
			for(int i=0;i<detalles.size();i++){
				if(cotizacion.getTipoObjeto().getNombre().equals("PYMES")){
					ObjetoPymesDAO ovDAO = new ObjetoPymesDAO();
					ObjetoPyme ov = ovDAO.buscarPorId(detalles.get(i).getObjetoId());
					if(ov.getId() != null){
						objeto.put("numero", (i+1));
						objeto.put("id", ov.getId());
						objeto.put("callePrincipal", ov.getCallePrincipal());
						objeto.put("numeroDireccion", ov.getNumeroDireccion());
						objeto.put("calleSecundaria", ov.getCalleSecundaria());
						objeto.put("nombreEdificioConjunto", ov.getNombreEdificioConjunto());
						objeto.put("numeroOficinaPiso", ov.getNumeroOficinaPiso());
						objeto.put("sector", ov.getSector());
						objeto.put("provinciaId", ov.getProvincia().getId());
						objeto.put("ciudadId", ov.getCiudad().getId());
						
						objeto.put("actividadEconomicaId", ov.getActividadEconomica().getId());
						objeto.put("descripcion", ov.getDescripcionSiniestroAnterior());
						objeto.put("valorSiniestro", ov.getValorSiniestroAnterior());
						objeto.put("contabilidad", ov.getContabilidadFormal());
						objeto.put("constituida", ov.getContituidaHaceDosAnios());
						objeto.put("tipoConstruccionId", ov.getTipoConstruccion().getId());
						objeto.put("tipoOcupacionId", ov.getTipoOcupacion().getId());
						objeto.put("anioConstruccion", ov.getAnioConstruccion());
						objeto.put("numeroPisos", ov.getNumeroPisos());
						objeto.put("extintores", ov.getExtintores());
						objeto.put("detectorHumo", ov.getDetectorHumo());
						objeto.put("sprinklers", ov.getSprinklers());
						objeto.put("alarmaMonitoreada", ov.getAlarmaMonitoreada());
						objeto.put("guardiana", ov.getGuardiania());
						objeto.put("otros", ov.getOtros());
						
						objetos.add(objeto);						
					}
				}
			}
			etapa2.put("objetos", objetos);
			retorno.put("etapa2", etapa2);
		}
		//fin etapa2

		//etapa 3
		if(cotizacion.getEtapaWizard()>=3){
			JSONObject etapa3= new JSONObject();
			retorno.put("etapa3", etapa3);
		}		
/*		
		//etapa 3
		if(cotizacion.getEtapaWizard()>=3){
			JSONObject etapa3= new JSONObject();
			
			JSONObject solicitudDescuento = new JSONObject();
			List<SolicitudDescuento> solicitudesDescuento = cotizacion.getSolicitudDescuentos();
			for(int i=0;i<solicitudesDescuento.size();i++){
				
				solicitudDescuento.put("descuentoId",solicitudesDescuento.get(i).getDescuento().getId());
				solicitudDescuento.put("porcentaje", solicitudesDescuento.get(i).getPorcentaje());
				solicitudDescuento.put("motivo", solicitudesDescuento.get(i).getMotivoDescuento().getId());
				solicitudDescuento.put("descripcion", solicitudesDescuento.get(i).getDescripcion());
				solicitudDescuento.put("estado", solicitudesDescuento.get(i).getEstado().getNombre());
				solicitudDescuento.put("usuarioActualiza", solicitudesDescuento.get(i).getUsuarioId());
				
			}
			JSONArray cuotasJSONArray = new JSONArray();
			JSONObject cuotasJSONObject = new JSONObject();
			JSONObject formaPago = new JSONObject();
			if(cotizacion.getPago() != null){
				formaPago.put("pagoId", cotizacion.getPago().getId());
				formaPago.put("formaPagoId", cotizacion.getPago().getFormaPago().getId());
				formaPago.put("valorTotal", cotizacion.getPago().getValorTotal());
				formaPago.put("plazo", cotizacion.getPago().getPlazonEnMes());
		
				if(cotizacion.getPago().getFormaPago().getNombre().trim().toUpperCase().equals("DEBITO BANCARIO")){
					formaPago.put("formaPagoNombre", cotizacion.getPago().getFormaPago().getNombre());
					formaPago.put("institucionFinancieraId", cotizacion.getPago().getInstitucionFinanciera().getId());
					formaPago.put("nombreTitular", cotizacion.getPago().getNombreTitular());
					formaPago.put("identificacionTitular", cotizacion.getPago().getIdentificacionTitular());
					formaPago.put("numCuentaTarjeta", cotizacion.getPago().getNumeroCuentaTarjeta());
					formaPago.put("tipoIdentificacion", cotizacion.getPago().getTipoIdentificacionId().getId());
					formaPago.put("tipoCuenta", cotizacion.getPago().getTipoCuenta());
				}
		
				if(cotizacion.getPago().getFormaPago().getNombre().trim().toUpperCase().equals("DEBITO TARJETA")){
					formaPago.put("formaPagoNombre", cotizacion.getPago().getFormaPago().getNombre());
					formaPago.put("institucionFinancieraId", cotizacion.getPago().getInstitucionFinanciera().getId());
					formaPago.put("nombreTitular", cotizacion.getPago().getNombreTitular());
					formaPago.put("identificacionTitular", cotizacion.getPago().getIdentificacionTitular());
					formaPago.put("numCuentaTarjeta", cotizacion.getPago().getNumeroCuentaTarjeta());
					formaPago.put("tipoIdentificacion", cotizacion.getPago().getTipoIdentificacionId().getId());
					formaPago.put("tipoCuenta", cotizacion.getPago().getTipoCuenta());
					formaPago.put("anioExpTarjeta", cotizacion.getPago().getAnioExpiracionTarjeta());
					formaPago.put("mesExpTarjeta", cotizacion.getPago().getMesExpiracionTarjeta());			
				}
		
				if(cotizacion.getPago().getFormaPago().getNombre().trim().toUpperCase().equals("CREDITO CUOTAS")){
					formaPago.put("formaPagoNombre", cotizacion.getPago().getFormaPago().getNombre());
					
					Cuota cuota = new Cuota();
					CuotaDAO cuotaDAO = new CuotaDAO();
					List <Cuota> listaCuotas= cuotaDAO.buscarPorPago(cotizacion.getPago());
					SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
					for (int i=0; i<listaCuotas.size(); i++){
						cuota = listaCuotas.get(i);
						cuotasJSONObject.put("cuotaValor", cuota.getValor());
						cuotasJSONObject.put("cuotaFechaPago", dt.format(cuota.getFechaPago().getTime()));
						cuotasJSONObject.put("cuotaNumCheque", cuota.getNumeroCheque());
						cuotasJSONObject.put("cuotaOrden", cuota.getOrden());
						cuotasJSONArray.add(cuotasJSONObject);
					}
					
				}
			
			if(cotizacion.getPago().getFormaPago().getNombre().trim().toUpperCase().equals("CONTADO")){
				formaPago.put("formaPagoNombre", cotizacion.getPago().getFormaPago().getNombre());
				//formaPago.put("institucionFinancieraId", cotizacion.getPago().getInstitucionFinanciera().getId());
				//formaPago.put("nombreTitular", cotizacion.getPago().getNombreTitular());
				//formaPago.put("identificacionTitular", cotizacion.getPago().getIdentificacionTitular());
				//formaPago.put("numCuentaTarjeta", cotizacion.getPago().getNumeroCuentaTarjeta());
				//formaPago.put("tipoIdentificacion", cotizacion.getPago().getTipoIdentificacionId().getId());
				//formaPago.put("tipoCuenta", cotizacion.getPago().getTipoCuenta());
			}
			}
			etapa3.put("listadoCuotas", cuotasJSONArray);
			etapa3.put("formaPago", formaPago);
			etapa3.put("solicitudDescuento", solicitudDescuento);
			retorno.put("etapa3", etapa3);
		}
		
		
		//datos UPLA
		//etapa 4
		//JSONObject etapa4= new JSONObject();
		JSONObject datosUPLA= new JSONObject();
				
		UplaDAO uplaDAO=new UplaDAO();
		Upla upla = uplaDAO.buscarPorCliente(cliente);
		if(upla!=null&&upla.getId()!=null){
			datosUPLA.put("datosUPLA", getDatosUpla(upla));			
		}
		retorno.put("datosUPLA",datosUPLA );
		
		JSONObject detalleValorPagar= new JSONObject();
		//detalleValorPagar.put("TotalSumaAsegurada", cotizacion.);
*/		
		return retorno;
	}
	
	public JSONObject getDatosUpla(Upla upla){
		JSONObject datosUPLA= new JSONObject();
		
		if(upla.getTipoCliente().equals("N")){
			if(upla.getLugarNacimientoNatural()!=null)
			datosUPLA.put("lugarNacimiento", upla.getLugarNacimientoNatural());
			if(upla.getFechaNacimientoNatural()!=null)
				datosUPLA.put("fechaNacimiento", upla.getFechaNacimientoNatural());
			if(upla.getDireccion()!=null)
				datosUPLA.put("zonaDireccionCliente",upla.getDireccion().getZona().getNombre().charAt(0));
			if(upla.getDireccion()!=null)
				if(upla.getDireccion().getZona().getNombre().charAt(0)=='R'){
					if(upla.getDireccion().getParroquia().getCanton().getProvincia()!=null)
						datosUPLA.put("provinciaDireccionCliente", upla.getDireccion().getParroquia().getCanton().getProvincia().getId());
					if(upla.getDireccion().getParroquia().getCanton()!=null)
						datosUPLA.put("cantonDireccionCliente", upla.getDireccion().getParroquia().getCanton().getId());
					if(upla.getDireccion().getParroquia()!=null)
						datosUPLA.put("parroquiaDireccionCliente", upla.getDireccion().getParroquia().getId());		
			}
			
			if(upla.getDireccion() != null)
				if(upla.getDireccion().getZona()!=null)
					if(upla.getDireccion().getZona().getNombre().charAt(0)=='U'){
						if(upla.getDireccion().getCiudad()!=null){
							datosUPLA.put("ciudadDireccionCliente", upla.getDireccion().getCiudad().getId());
							if(upla.getDireccion().getCiudad().getProvincia()!=null)
								datosUPLA.put("provinciaDireccionCliente", upla.getDireccion().getCiudad().getProvincia().getId());
						}
						if(upla.getDireccion().getCallePrincipal()!=null)
							datosUPLA.put("callePrincipalCliente",upla.getDireccion().getCallePrincipal());
						if(upla.getDireccion().getNumero()!=null)
							datosUPLA.put("numeroDireccionCliente",upla.getDireccion().getNumero());
						if(upla.getDireccion().getCalleSecundaria()!=null)
							datosUPLA.put("calleSecundariaCliente",upla.getDireccion().getCalleSecundaria());
						if(upla.getDireccion().getDatosDeReferencia()!=null)
							datosUPLA.put("referenciaDireccionCliente",upla.getDireccion().getDatosDeReferencia());
					}
			
			if(upla.getTelefonoNatural()!=null)
				datosUPLA.put("telefonoCliente",upla.getTelefonoNatural());
			if(upla.getCelularNatural()!=null)
				datosUPLA.put("celularCliente",upla.getCelularNatural());
			if(upla.getGeneroNatural()!=null)
				datosUPLA.put("generoCliente",upla.getGeneroNatural());
			if(upla.getEmailNatural()!=null)
				datosUPLA.put("mail",upla.getEmailNatural());
			if(upla.getCliente().getActividadEconomica()!=null)
				datosUPLA.put("actividadCliente",upla.getCliente().getActividadEconomica().getId());
			if(upla.getTipoActividadNatural()!=null)
				datosUPLA.put("tipoActividadCliente",upla.getTipoActividadNatural());
			if(upla.getCargoOcupaNatural()!=null)
				datosUPLA.put("cargoOcupaCliente",upla.getCargoOcupaNatural());
			if(upla.getRamo()!=null)
				datosUPLA.put("tipoRamoContrata",upla.getRamo().getId());
			datosUPLA.put("expuestoCliente",upla.getExpuestaPoliticamenteNatural());
			if(upla.getCargoDesempenaNatural()!=null)
				datosUPLA.put("cargoExpuestoCliente",upla.getCargoDesempenaNatural());
			datosUPLA.put("expuestoFamiliar",upla.getFamiliarExpuestoPoliticamente());
			if(upla.getParentescoFamiliarExpuesto()!=null)
				datosUPLA.put("parentescoExpuestoFamiliar",upla.getParentescoFamiliarExpuesto());
			if(upla.getCargoFamiliarExpuesto()!=null)
				datosUPLA.put("cargoExpuestoFamiliar",upla.getCargoFamiliarExpuesto());
			if(upla.getApellidoPaternoConyuge()!=null)
				datosUPLA.put("apellidoPaternoConyuge",upla.getApellidoPaternoConyuge());
			if(upla.getApellidoMaternoConyuge()!=null)
				datosUPLA.put("apellidoMaternoConyuge",upla.getApellidoMaternoConyuge());
			if(upla.getNombreConyuge()!=null)
				datosUPLA.put("nombreConyuge",upla.getNombreConyuge());
			if(upla.getTipoIdentificacionIdConyuge()!=null)
				datosUPLA.put("tipoIdentificacionConyuge",upla.getTipoIdentificacionIdConyuge());
			if(upla.getIdentificacionConyuge()!=null)
				datosUPLA.put("identificacionConyuge",upla.getIdentificacionConyuge());
			datosUPLA.put("salarioMensual",upla.getSalarioMensual());
			datosUPLA.put("activos",upla.getActivos());
			datosUPLA.put("otrosIngresos",upla.getOtrosIngresos());
			datosUPLA.put("pasivos",upla.getPasivos());
			datosUPLA.put("egresos",upla.getEgresos());
			datosUPLA.put("patrimonio",upla.getPatrimonio());
			datosUPLA.put("ingresoEgreso",upla.getIngresosEgresos());
			datosUPLA.put("esAsegurado",upla.getEsAsegurado());
			datosUPLA.put("esBeneficiario",upla.getEsBeneficiario());
			if(upla.getTipoIdentificacionIdAsegurado()!=null)
				datosUPLA.put("tipoIdentificacionAsegurado",upla.getTipoIdentificacionIdAsegurado());
			if(upla.getIdentificacionAsegurado()!=null)
				datosUPLA.put("identificacionAsegurado",upla.getIdentificacionAsegurado());
			if(upla.getNombreCompletoAsegurado()!=null)
				datosUPLA.put("nombreCompletoAsegurado",upla.getNombreCompletoAsegurado());
			if(upla.getDireccionAsegurado()!=null)
				datosUPLA.put("direccionAsegurado",upla.getDireccionAsegurado());
			if(upla.getTelefonoAsegurado()!=null)
				datosUPLA.put("telefonoAsegurado",upla.getTelefonoAsegurado());
			if(upla.getCelularAsegurado()!=null)
				datosUPLA.put("celularAsegurado",upla.getCelularAsegurado());
			if(upla.getRelacionAsegurado()!=null)
				datosUPLA.put("relacionAsegurado",upla.getRelacionAsegurado());
			if(upla.getTipoIdentificacionIdBeneficiario()!=null)
				datosUPLA.put("tipoIdentificacionBeneficiario",upla.getTipoIdentificacionIdBeneficiario());
			if(upla.getIdentificacionBeneficiario()!=null)
				datosUPLA.put("identificacionBeneficiario",upla.getIdentificacionBeneficiario());
			if(upla.getNombreBeneficiario()!=null)
				datosUPLA.put("nombreCompletoBeneficiario",upla.getNombreBeneficiario());
			if(upla.getDireccionBeneficiario()!=null)
				datosUPLA.put("direccionBeneficiario",upla.getDireccionBeneficiario());
			if(upla.getTelefonoBeneficiario()!=null)
				datosUPLA.put("telefonoBeneficiario",upla.getTelefonoBeneficiario());
			if(upla.getCelularBeneficiario()!=null)
				datosUPLA.put("celularBeneficiario",upla.getCelularBeneficiario());
			if(upla.getRelacionBeneficiario()!=null)
				datosUPLA.put("relacionBeneficiario",upla.getRelacionBeneficiario());
			
			}
			
			if(upla.getTipoCliente().equals("J")){
				if(upla.getObjetoSocialJuridica()!=null)
					datosUPLA.put("objetoSocial", upla.getObjetoSocialJuridica());
				if(upla.getCiudadPaisJuridica()!=null)
					datosUPLA.put("ciudadJuridica", upla.getCiudadPaisJuridica());
				if(upla.getFechaNacimientoNatural()!=null)
					datosUPLA.put("fechaNacimiento", upla.getFechaNacimientoRepresentanteLegal());
				if(upla.getDireccion().getZona()!=null)
					datosUPLA.put("zonaDireccionMatriz",upla.getDireccion().getZona().getNombre().charAt(0));
				if(upla.getDireccion().getZona()!=null)
					if(upla.getDireccion().getZona().getNombre().charAt(0)=='R'){
						if(upla.getDireccion().getParroquia().getCanton().getProvincia()!=null)
							datosUPLA.put("provinciaDireccionMatriz", upla.getDireccion().getParroquia().getCanton().getProvincia().getId());
						if(upla.getDireccion().getParroquia().getCanton()!=null)
							datosUPLA.put("cantonDireccionMatriz", upla.getDireccion().getParroquia().getCanton().getId());
						if(upla.getDireccion().getParroquia()!=null)
							datosUPLA.put("parroquiaDireccionMatriz", upla.getDireccion().getParroquia().getId());		
			}
				if(upla.getDireccion().getZona().getNombre()!=null)
					if(upla.getDireccion().getZona().getNombre().charAt(0)=='U'){
						if(upla.getDireccion().getCiudad().getProvincia()!=null)
							datosUPLA.put("provinciaDireccionMatriz", upla.getDireccion().getCiudad().getProvincia().getId());
						if(upla.getDireccion().getCiudad()!=null)
							datosUPLA.put("ciudadDireccionMatriz", upla.getDireccion().getCiudad().getId());		
			}
				if(upla.getDireccion().getCallePrincipal()!=null)
					datosUPLA.put("callePrincipalMatriz",upla.getDireccion().getCallePrincipal());
				if(upla.getDireccion().getNumero()!=null)
					datosUPLA.put("numeroDireccionMatriz",upla.getDireccion().getNumero());
				if(upla.getDireccion().getCalleSecundaria()!=null)
					datosUPLA.put("calleSecundariaMatriz",upla.getDireccion().getCalleSecundaria());
				if(upla.getDireccion().getDatosDeReferencia()!=null)
					datosUPLA.put("referenciaDireccionMatriz",upla.getDireccion().getDatosDeReferencia());
				if(upla.getSucursalDireccionJuridica()!=null)
					datosUPLA.put("sucursalDireccion",upla.getSucursalDireccionJuridica());
				if(upla.getSucursalCiudadJuridica()!=null)
					datosUPLA.put("sucursalCiudad",upla.getSucursalCiudadJuridica());
				if(upla.getTelefonoEmpresa()!=null)
					datosUPLA.put("telefono",upla.getTelefonoEmpresa());
				if(upla.getFaxEmpresa()!=null)
					datosUPLA.put("fax",upla.getFaxEmpresa());
				if(upla.getCliente().getActividadEconomica()!=null)
					datosUPLA.put("actividadJuridica",upla.getCliente().getActividadEconomica().getId());
				if(upla.getNombresRepresentanteLegal()!=null)
					datosUPLA.put("nombresRepresentanteLegal",upla.getNombresRepresentanteLegal());
				if(upla.getApellidosRepresentanteLegal()!=null)
					datosUPLA.put("apellidosRepresentanteLegal",upla.getApellidosRepresentanteLegal());
				if(upla.getTipoIdentificacionIdRepresentanteLegal()!=null)
					datosUPLA.put("tipoIdentificacionRepresentante",upla.getTipoIdentificacionIdRepresentanteLegal());
				if(upla.getIdentificacionRepresentanteLegal()!=null)
					datosUPLA.put("identificacionRepresentante",upla.getIdentificacionRepresentanteLegal());
				if(upla.getLugarNacimientoRepresentanteLegal()!=null)
					datosUPLA.put("lugarNacimientoRepresentante",upla.getLugarNacimientoRepresentanteLegal());
				if(upla.getFechaNacimientoRepresentanteLegal()!=null)
					datosUPLA.put("fechaNacimientoRepresentante",upla.getFechaNacimientoRepresentanteLegal());
				if(upla.getDireccionRepresentanteLegal()!=null)
					datosUPLA.put("residenciaRepresentante",upla.getDireccionRepresentanteLegal());
				datosUPLA.put("paisRepresentante","");
				if(upla.getProvinciaIdRepresentanteLegal()!=null)
					datosUPLA.put("provinciaRepresentante",upla.getProvinciaIdRepresentanteLegal());
				if(upla.getCiudadIdRepresentanteLegal()!=null)
					datosUPLA.put("ciudadRepresentante",upla.getCiudadIdRepresentanteLegal());
				if(upla.getTelefonoRepresentanteLegal()!=null)
					datosUPLA.put("telefonoRepresentante",upla.getTelefonoRepresentanteLegal());
				if(upla.getCelularRepresentanteLegal()!=null)
					datosUPLA.put("celularRepresentante",upla.getCelularRepresentanteLegal());
				datosUPLA.put("expuestoRepresentante",upla.getExpuestaPoliticamenteNatural());
				if(upla.getCargoDesempenaNatural()!=null)
					datosUPLA.put("cargoExpuesta",upla.getCargoDesempenaNatural());
				datosUPLA.put("expuestoFamiliar",upla.getFamiliarExpuestoPoliticamente());
				if(upla.getParentescoFamiliarExpuesto()!=null)
					datosUPLA.put("parentescoExpuestoFamiliar",upla.getParentescoFamiliarExpuesto());
				if(upla.getCargoFamiliarExpuesto()!=null)
					datosUPLA.put("cargoExpuestoFamiliar",upla.getCargoFamiliarExpuesto());
				if(upla.getApellidoPaternoConyuge()!=null)
					datosUPLA.put("apellidoPaternoConyuge",upla.getApellidoPaternoConyuge());
				if(upla.getApellidoMaternoConyuge()!=null)
					datosUPLA.put("apellidoMaternoConyuge",upla.getApellidoMaternoConyuge());
				if(upla.getNombreConyuge()!=null)
					datosUPLA.put("nombreConyuge",upla.getNombreConyuge());
				if(upla.getTipoIdentificacionIdConyuge()!=null)
					datosUPLA.put("tipoIdentificacionConyuge",upla.getTipoIdentificacionIdConyuge());
				if(upla.getIdentificacionConyuge()!=null)
					datosUPLA.put("identificacionConyuge",upla.getIdentificacionConyuge());
				datosUPLA.put("salarioMensual",upla.getSalarioMensual());
				datosUPLA.put("activos",upla.getActivos());
				datosUPLA.put("otrosIngresos",upla.getOtrosIngresos());
				datosUPLA.put("pasivos",upla.getPasivos());
				datosUPLA.put("egresos",upla.getEgresos());
				datosUPLA.put("patrimonio",upla.getPatrimonio());
				datosUPLA.put("ingresoEgreso",upla.getIngresosEgresos());
				datosUPLA.put("esAsegurado",upla.getEsAsegurado());
				datosUPLA.put("esBeneficiario",upla.getEsBeneficiario());
				if(upla.getTipoIdentificacionIdAsegurado()!=null)
					datosUPLA.put("tipoIdentificacionAsegurado",upla.getTipoIdentificacionIdAsegurado());
				if(upla.getIdentificacionAsegurado()!=null)
					datosUPLA.put("identificacionAsegurado",upla.getIdentificacionAsegurado());
				if(upla.getNombreCompletoAsegurado()!=null)
					datosUPLA.put("nombreCompletoAsegurado",upla.getNombreCompletoAsegurado());
				if(upla.getDireccionAsegurado()!=null)
					datosUPLA.put("direccionAsegurado",upla.getDireccionAsegurado());
				if(upla.getTelefonoAsegurado()!=null)
					datosUPLA.put("telefonoAsegurado",upla.getTelefonoAsegurado());
				if(upla.getCelularAsegurado()!=null)
					datosUPLA.put("celularAsegurado",upla.getCelularAsegurado());
				if(upla.getRelacionAsegurado()!=null)
					datosUPLA.put("relacionAsegurado",upla.getRelacionAsegurado());
				if(upla.getTipoIdentificacionIdBeneficiario()!=null)
					datosUPLA.put("tipoIdentificacionBeneficiario",upla.getTipoIdentificacionIdBeneficiario());
				if(upla.getIdentificacionBeneficiario()!=null)
					datosUPLA.put("identificacionBeneficiario",upla.getIdentificacionBeneficiario());
				if(upla.getNombreBeneficiario()!=null)
					datosUPLA.put("nombreCompletoBeneficiario",upla.getNombreBeneficiario());
				if(upla.getDireccionBeneficiario()!=null)
					datosUPLA.put("direccionBeneficiario",upla.getDireccionBeneficiario());
				if(upla.getTelefonoBeneficiario()!=null)
					datosUPLA.put("telefonoBeneficiario",upla.getTelefonoBeneficiario());
				if(upla.getCelularBeneficiario()!=null)
					datosUPLA.put("celularBeneficiario",upla.getCelularBeneficiario());
				if(upla.getRelacionBeneficiario()!=null)
					datosUPLA.put("relacionBeneficiario",upla.getRelacionBeneficiario());
			}
			return datosUPLA;
	}
	
	public String eliminarUbicacionCotizacionDetalle(Cotizacion cotizacion, String ubicacionId){
		String result = "";
		CotizacionDetalle cotizacionDetalle = new CotizacionDetalle();
		ObjetoPymesTransaction objetoPymesTransaction = new ObjetoPymesTransaction();
		CotizacionDetalleTransaction cotizacionDetalleTransaction= new CotizacionDetalleTransaction();
		
		CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();
		cotizacionDetalle = cotizacionDetalleDAO.buscarCotizacionDetalleIdYObjetoId(ubicacionId, cotizacion);
		cotizacionDetalleTransaction.eliminar(cotizacionDetalle);
		
		ObjetoPymesDAO objetoPymeDAO = new ObjetoPymesDAO();
		try {
			objetoPymesTransaction.eliminar(objetoPymeDAO.buscarPorId(ubicacionId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result = "Se ha eliminado correctamente";
		return result;
	}	

}
