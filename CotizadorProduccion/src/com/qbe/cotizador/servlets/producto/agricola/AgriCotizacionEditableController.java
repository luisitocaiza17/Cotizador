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

import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.entidad.CantonDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.ParroquiaDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCicloDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriObjetoCotizacionDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParametroXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParroquiaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriReglaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCalculoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCultivoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriVariedadDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.AgriCiclo;
import com.qbe.cotizador.model.AgriObjetoCotizacion;
import com.qbe.cotizador.model.AgriParametroXPuntoVenta;
import com.qbe.cotizador.model.AgriParroquia;
import com.qbe.cotizador.model.AgriRegla;
import com.qbe.cotizador.model.AgriTipoCalculo;
import com.qbe.cotizador.model.AgriTipoCultivo;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.transaction.cotizacion.CotizacionDetalleTransaction;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.producto.agricola.AgriObjetoCotizacionTransaction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class AgriCotizacionEditable
 */
@WebServlet("/AgriCotizacionEditableController")
public class AgriCotizacionEditableController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgriCotizacionEditableController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		String tipoConsulta = request.getParameter("tipoConsulta") == null ? "":request.getParameter("tipoConsulta");
		
		try{
			
			if(tipoConsulta.equals("EditarCotizacion")){
				String idCotizacion = request.getParameter("idCotizacion") == null ? "":request.getParameter("idCotizacion");
				String puntoVentaNuevo = request.getParameter("puntoVenta") == null ? "":request.getParameter("puntoVenta");
				String idEmisionNuevo = request.getParameter("idEmision") == null ? "":request.getParameter("idEmision");
				String NumtramiteNuevo = request.getParameter("Numtramite") == null ? "":request.getParameter("Numtramite");
				String identificacionNuevo = request.getParameter("identificacion") == null ? "":request.getParameter("identificacion");
				String provinciaNuevo = request.getParameter("provincia") == null ? "":request.getParameter("provincia");
				String cantonNuevo = request.getParameter("canton") == null ? "":request.getParameter("canton");
				String parroquiaNuevo = request.getParameter("parroquia") == null ? "":request.getParameter("parroquia");
				String cultivoNuevo = request.getParameter("cultivo") == null ? "":request.getParameter("cultivo");
				String hectareasAsegurablesNuevo = request.getParameter("hectareasAsegurables") == null ? "":request.getParameter("hectareasAsegurables");
				String hectareasTotalesNuevo = request.getParameter("hectareasTotales") == null ? "":request.getParameter("hectareasTotales");
				String lugarSiembraNuevo = request.getParameter("lugarSiembra") == null ? "":request.getParameter("lugarSiembra");
				String fechaSiembraNuevo = request.getParameter("fechaSiembra") == null ? "":request.getParameter("fechaSiembra");
				String costoProduccionNuevo = request.getParameter("costoProduccion") == null ? "":request.getParameter("costoProduccion");
				String tasa = request.getParameter("tasa") == null ? "":request.getParameter("tasa");				
				String sumaAseguradaNuevo = request.getParameter("sumaAsegurada") == null ? "":request.getParameter("sumaAsegurada");
				String primaNetaNuevo = request.getParameter("primaNeta") == null ? "":request.getParameter("primaNeta");
				String seguroCampesinoNuevo = request.getParameter("seguroCampesino") == null ? "":request.getParameter("seguroCampesino");
				String superBancosNuevo = request.getParameter("superBancos") == null ? "":request.getParameter("superBancos");
				String derechoEmisionNuevo = request.getParameter("derechoEmision") == null ? "":request.getParameter("derechoEmision");
				String ivaNuevo = request.getParameter("iva") == null ? "":request.getParameter("iva");
				String impuestosNuevo = request.getParameter("impuestos") == null ? "":request.getParameter("impuestos");
				String primaTotalNuevo = request.getParameter("primaTotal") == null ? "":request.getParameter("primaTotal");
				
				//buscamos la cotizacion
				
				CotizacionDAO cotizacionDAO= new CotizacionDAO();
				Cotizacion cotizacion=cotizacionDAO.buscarPorId(idCotizacion);
				CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();
				CotizacionDetalle cotizacionDetalle = cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).get(0);
				AgriObjetoCotizacionDAO agriObjetoCotizacionDAO= new AgriObjetoCotizacionDAO();
				AgriObjetoCotizacion agriObjetoCotizacion=agriObjetoCotizacionDAO.buscarPorId(new BigInteger( cotizacionDetalle.getObjetoId()));
				
				//verificamos si hubo modificaicon en el punto de venta.
				String idPuntoVenta=cotizacion.getPuntoVenta().getId();
				
				//Actualizo el punto de Venta
				if(!idPuntoVenta.equals(puntoVentaNuevo)){
					PuntoVentaDAO puntoVentaDAO = new PuntoVentaDAO();
					com.qbe.cotizador.model.PuntoVenta puntoVenta= puntoVentaDAO.buscarPorId(puntoVentaNuevo);
					cotizacion.setPuntoVenta(puntoVenta);
					AgriParametroXPuntoVentaDAO agriParametroXPuntoVentaDAO = new AgriParametroXPuntoVentaDAO();
					AgriParametroXPuntoVenta agriParametroXPuntoVenta=agriParametroXPuntoVentaDAO.buscarPorPuntoVentaId(new BigInteger(puntoVenta.getId()));
					//actualizo el porcentaje de comision y el agente
					cotizacion.setPorcentajeComision(Double.parseDouble(agriParametroXPuntoVenta.getPorcentajeComision()));
					cotizacion.setAgenteId(new BigInteger(agriParametroXPuntoVenta.getAgenteId()));
				}
				//Actualizo el id de emision
				if(!agriObjetoCotizacion.getIdCotizacion2().toString().equals(idEmisionNuevo.trim()))
					agriObjetoCotizacion.setIdCotizacion2( new BigInteger(idEmisionNuevo.trim()));
				//Actualizo el numero de tramite
				cotizacion.setNumeroTramite(NumtramiteNuevo.trim());
				//Actualizo el cliente
				if(!cotizacion.getAsegurado().getIdentificacion().equals(identificacionNuevo.trim())){
					EntidadDAO entidadDAO= new EntidadDAO();
					Entidad entidad= entidadDAO.buscarEntidadPorIdentificacion(identificacionNuevo.trim());
					cotizacion.setAsegurado(entidad);
					ClienteDAO clienteDAO= new ClienteDAO();
					Cliente cliente = clienteDAO.buscarPorEntidadId(entidad);
					cotizacion.setClienteId(new BigInteger(cliente.getId()));
				}
				//actualizo la provincia
				if(!agriObjetoCotizacion.getProvinciaId().toString().equals(provinciaNuevo))
					agriObjetoCotizacion.setProvinciaId(new BigInteger(provinciaNuevo));
				//actualizo el canton
				if(!agriObjetoCotizacion.getCantonId().toString().equals(cantonNuevo))
					agriObjetoCotizacion.setCantonId(new BigInteger(cantonNuevo));
				//actualizo la parroquia
				if(!agriObjetoCotizacion.getAgriParroquiaId().toString().equals(parroquiaNuevo)){
					agriObjetoCotizacion.setParroquiaId(null);
					agriObjetoCotizacion.setAgriParroquiaId(parroquiaNuevo);
				}
				//actualizo el tipo de cultivo
				if(!agriObjetoCotizacion.getTipoCultivoId().toString().equals(cultivoNuevo.trim()))
					agriObjetoCotizacion.setTipoCultivoId(new BigInteger(cultivoNuevo.trim()));
				//actualizo el numero de hectareas asegurables
				agriObjetoCotizacion.setHectareasAsegurables(Float.parseFloat(hectareasAsegurablesNuevo));
				agriObjetoCotizacion.setHectareasLote(Float.parseFloat(hectareasTotalesNuevo));
				//actualizo el lugar de la siembra
				agriObjetoCotizacion.setDireccionSiembra(lugarSiembraNuevo);
				
				//actualizar la fecha de siembra
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
				Date fechaSiembra = formatoDelTexto.parse(fechaSiembraNuevo);
				agriObjetoCotizacion.setFechaSiembra(fechaSiembra);
				
				//redondeo
				BigDecimal a = new BigDecimal(""+costoProduccionNuevo);
				BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
				
				
				/***PROCESOS DE RECALCULO DE VALORES DE LAS COTIZACIONES***/
				agriObjetoCotizacion.setCostoProduccion(Float.parseFloat(""+roundOff));
				
				a = new BigDecimal(""+tasa);
				roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
				cotizacion.setTasaProductoValor(Double.parseDouble(""+roundOff));
				cotizacionDetalle.setTasa(Double.parseDouble(""+roundOff));
				cotizacionDetalle.setTasaOrigen(Double.parseDouble(""+roundOff)); 
				
				a = new BigDecimal(""+sumaAseguradaNuevo);
				roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
				cotizacion.setSumaAseguradaTotal(Double.parseDouble(""+roundOff));
				
				a = new BigDecimal(""+primaNetaNuevo);
				roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
				cotizacion.setPrimaNetaTotal(""+roundOff);
				cotizacion.setPrimaOrigen(Double.parseDouble(""+roundOff));
				
				a = new BigDecimal(""+seguroCampesinoNuevo);
				roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
				cotizacion.setImpSeguroCampesino(Double.parseDouble(""+roundOff));
				
				a = new BigDecimal(""+superBancosNuevo);
				roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
				cotizacion.setImpSuperBancos(Double.parseDouble(""+roundOff));
				
				a = new BigDecimal(""+derechoEmisionNuevo);
				roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);				
				cotizacion.setImpDerechoEmision(Double.parseDouble(""+roundOff));
				
				a = new BigDecimal(""+ivaNuevo);
				roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
				cotizacion.setImpIva(Double.parseDouble(""+roundOff));
				
				a = new BigDecimal(""+primaTotalNuevo);
				roundOff = a.setScale(2, BigDecimal.ROUND_HALF_UP);
				cotizacion.setTotalFactura(Double.parseDouble(""+roundOff));
				
				//Si todo esta correcto actualizo la cotizacion
				CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
				cotizacionTransaction.editar(cotizacion);
				CotizacionDetalleTransaction cotizacionDetalleTransaction = new CotizacionDetalleTransaction();
				cotizacionDetalleTransaction.editar(cotizacionDetalle);
				AgriObjetoCotizacionTransaction agriObjetoCotizacionTransaction= new AgriObjetoCotizacionTransaction();
				agriObjetoCotizacionTransaction.editar(agriObjetoCotizacion);
				
			}
			
			if(tipoConsulta.equals("recalculoTotal")){
				String hectareasAsegurables = request.getParameter("hectareasAsegurables") == null ? "":request.getParameter("hectareasAsegurables");
				String puntoVentaId = request.getParameter("puntoVentaId") == null ? "":request.getParameter("puntoVentaId");
				String provinciaId = request.getParameter("provinciaId") == null ? "":request.getParameter("provinciaId");
				String cantonId = request.getParameter("cantonId") == null ? "":request.getParameter("cantonId");
				String cultivoId = request.getParameter("cultivoId") == null ? "":request.getParameter("cultivoId");
				String fechaSiembra = request.getParameter("fechaSiembra") == null ? "":request.getParameter("fechaSiembra");
				
				System.out.println("provinciaId "+provinciaId+" cantonId "+cantonId+" cultivoId "+cultivoId+" hectareasAsegurables "+ hectareasAsegurables+" puntoVentaId "+puntoVentaId+ "fechaSiembra "+fechaSiembra);
				
				//Recalculo de Valores
				AgriParametroXPuntoVentaDAO agriParametroXPuntoVentaDAO = new AgriParametroXPuntoVentaDAO();
				AgriParametroXPuntoVenta agriParametroXPuntoVenta=agriParametroXPuntoVentaDAO.buscarPorPuntoVentaId(new BigInteger(puntoVentaId));
				
				ProvinciaDAO provinciaDAO= new ProvinciaDAO();
				com.qbe.cotizador.model.Provincia provincia=provinciaDAO.buscarPorId(provinciaId);
				
				CantonDAO cantonDAO= new CantonDAO();
				com.qbe.cotizador.model.Canton canton=cantonDAO.buscarPorId(cantonId);
				
				AgriTipoCultivoDAO agriTipoCultivoDAO = new AgriTipoCultivoDAO();
				AgriTipoCultivo agriTipoCultivo = agriTipoCultivoDAO.BuscarPorId(new BigInteger(cultivoId));
								
				//Buscamos las reglas
				
				AgriReglaDAO agriReglaDAO = new AgriReglaDAO();
				List<AgriRegla> agriRegla=agriReglaDAO.BuscarPorParametros(new BigInteger(provincia.getId()), new BigInteger(canton.getId()), agriTipoCultivo.getTipoCultivoId(), agriParametroXPuntoVenta.getTipoCalculoId());
				
				//ciclos
				AgriCicloDAO cicloDAO= new AgriCicloDAO();
				
				//Variables de calculo
				double costoProduccion=0;
				double tasa=0;
				
				for(AgriRegla rs :  agriRegla ){
					AgriCiclo ciclo = cicloDAO.BuscarPorId(rs.getClicloId());//tomamos el ciclo para verificar a cual pertenece
					tasa=Double.parseDouble(""+rs.getTasa());
					if(rs.getCostoProduccion()!=0){
						costoProduccion=Double.parseDouble(""+rs.getCostoProduccion());							
					}else{
						costoProduccion=Double.parseDouble(""+rs.getCostoMantenimiento());							
					}
				
				}
				
				//En caso de no tener tasa la buscamos directamente del cultivo
				if(tasa==0)
					tasa=agriTipoCultivo.getTasa();
				
				//buscamos variables de calculo
				VariableSistemaDAO  variableSistemaDAO = new VariableSistemaDAO();
				VariableSistema variableSistema=variableSistemaDAO.buscarPorNombre("DERECHOS_EMISION_AGRICOLA");
				
				//verificamos por el canal en caso de ser sucre va 0
				
				double derechoEmision = 0;
				
				if(agriParametroXPuntoVenta.getCanalId().toString().equals("1"))
					derechoEmision=0;
				else
					derechoEmision=Double.parseDouble(variableSistema.getValor());
				
				variableSistema=variableSistemaDAO.buscarPorNombre("SEGURO_CAMPESINO");
				
				double seguroCampesino = Double.parseDouble(variableSistema.getValor());
				
				variableSistema=variableSistemaDAO.buscarPorNombre("SUPER_DE_BANCOS");
				
				double superBancos = Double.parseDouble(variableSistema.getValor());
				
				variableSistema=variableSistemaDAO.buscarPorNombre("IVA");
				
				double PorcentajeIva=14;
				
				if(provincia.getNombre().equals("MANABI")||provincia.getNombre().equals("ESMERALDAS")){
					 PorcentajeIva=12;
	           	}else{
	           		 PorcentajeIva=  Double.parseDouble(variableSistema.getValor());
	           	}
				
				//Calculos
				double NumHectareas=Double.parseDouble(hectareasAsegurables);
				double SumaAsegurada=costoProduccion*NumHectareas;
				double primaNeta=SumaAsegurada*tasa/100;
				double ImpSeguroCampesino=(primaNeta*seguroCampesino/100*100)/100;
				double ImpSuperBancos=(primaNeta*superBancos/100*100)/100;
				double ImpIva=((primaNeta+ImpSeguroCampesino+ImpSuperBancos+derechoEmision)* PorcentajeIva / 100 * 100) / 100;
				double ImpTotal=ImpSeguroCampesino+ImpSuperBancos+ImpIva+derechoEmision;
				double PrimaTotal=primaNeta+ImpSeguroCampesino+ImpSuperBancos+ImpIva+derechoEmision;
				
				
				result.put("costoProduccion", costoProduccion);
				result.put("sumaAsegurada", SumaAsegurada);
				result.put("primaNeta", primaNeta);
				result.put("impSeguroCampesino",ImpSeguroCampesino);
				result.put("derechoEmision",derechoEmision);
				result.put("impSuperBancos",ImpSuperBancos);
				result.put("impIva",ImpIva);
				result.put("impTotal",ImpTotal);
				result.put("primaTotal",PrimaTotal);
				result.put("tasa",tasa);
				result.put("PorcentajeIva",PorcentajeIva);
				result.put("gderechoEmision", derechoEmision);
				
				if(costoProduccion==0)
					throw new Exception("no Existe regla para este cultivo");
			}
			
			if(tipoConsulta.equals("cliente")){
				String identificacion = request.getParameter("identificacion") == null ? "":request.getParameter("identificacion");
				
				Entidad entidad = new Entidad();
				EntidadDAO entidadDAO = new EntidadDAO();
				entidad=entidadDAO.buscarEntidadPorIdentificacion(identificacion);
				
				Cliente cliente = new Cliente();
				ClienteDAO clienteDAO = new ClienteDAO();
				cliente=clienteDAO.buscarPorEntidadId(entidad);
				
				result.put("nombres", entidad.getNombreCompleto());
				result.put("idCliente",cliente.getId());
				result.put("idEntidad",entidad.getId());
				
			}
			
			if(tipoConsulta.equals("cargarCotizacion")){
				String idCotizacion = request.getParameter("idCotizacion") == null ? "":request.getParameter("idCotizacion");
				CotizacionDAO cotizacionDAO= new CotizacionDAO();
				Cotizacion cotizacion=cotizacionDAO.buscarPorId(idCotizacion);
				CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();
				CotizacionDetalle cotizacionDetalle = cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).get(0);
				AgriObjetoCotizacionDAO agriObjetoCotizacionDAO= new AgriObjetoCotizacionDAO();
				AgriObjetoCotizacion agriObjetoCotizacion=agriObjetoCotizacionDAO.buscarPorId(new BigInteger( cotizacionDetalle.getObjetoId()));
				
				/**PROCESO DE LLENADO DE INFORMACION**/
				AgriParametroXPuntoVentaDAO agriParametroXPuntoVentaDAO = new AgriParametroXPuntoVentaDAO();
				AgriParametroXPuntoVenta agriParametroXPuntoVenta=agriParametroXPuntoVentaDAO.buscarPorPuntoVentaId(new BigInteger(cotizacion.getPuntoVenta().getId()));
				
				SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
				
				//calculamos el iva dependiendo del lugar de siembra
				ProvinciaDAO provinciaDAO= new ProvinciaDAO();
				com.qbe.cotizador.model.Provincia provincia=provinciaDAO.buscarPorId(agriObjetoCotizacion.getProvinciaId().toString());
				
				VariableSistemaDAO  variableSistemaDAO = new VariableSistemaDAO();
				VariableSistema variableSistema=variableSistemaDAO.buscarPorNombre("DERECHOS_EMISION_AGRICOLA");
								
				variableSistema=variableSistemaDAO.buscarPorNombre("IVA");
				
				double PorcentajeIva=14;
				
				if(provincia.getNombre().equals("MANABI")||provincia.getNombre().equals("ESMERALDAS")){
					 PorcentajeIva=12;
	           	}else{
	           		 PorcentajeIva=  Double.parseDouble(variableSistema.getValor());
	           	}
				
				String DerechoEmisionGeneral="";
				if(agriParametroXPuntoVenta.getCanalId().toString().equals("1")){
					DerechoEmisionGeneral="0";
				}else{
					variableSistema=variableSistemaDAO.buscarPorNombre("DERECHOS_EMISION_AGRICOLA");
					DerechoEmisionGeneral=variableSistema.getValor();					
				}
				/**VALORES**/
				result.put("sponsorId", agriParametroXPuntoVenta.getCanalId().toString());
				result.put("canalId", agriParametroXPuntoVenta.getPuntoVentaId().toString());
				result.put("idEmision", agriObjetoCotizacion.getIdCotizacion2());
				result.put("tramite", cotizacion.getNumeroTramite());
				result.put("Numtramite", cotizacion.getNumeroTramite());
				result.put("identificacion", cotizacion.getAsegurado().getIdentificacion());
				result.put("clienteId", cotizacion.getAsegurado().getId());
				result.put("entidad", cotizacion.getAsegurado().getId());
				result.put("nombre", cotizacion.getAsegurado().getNombreCompleto());
				result.put("provinciaId", agriObjetoCotizacion.getProvinciaId());
				result.put("cantonId", agriObjetoCotizacion.getCantonId());
				result.put("parroquiaId", agriObjetoCotizacion.getAgriParroquiaId());
				result.put("cultivoId", agriObjetoCotizacion.getTipoCultivoId());
				result.put("hecAseguradas", agriObjetoCotizacion.getHectareasAsegurables());
				result.put("hecTotal", agriObjetoCotizacion.getHectareasLote());
				result.put("lugarSiembra", agriObjetoCotizacion.getDireccionSiembra());
				result.put("fechaSiembra", dt.format(agriObjetoCotizacion.getFechaSiembra()));
				result.put("costoProduccion", agriObjetoCotizacion.getCostoProduccion());
				result.put("sumaAsegurada", cotizacion.getSumaAseguradaTotal());
				result.put("primaNeta", cotizacion.getPrimaOrigen());
				result.put("iva", cotizacion.getImpIva());
				result.put("seguroCampesino", cotizacion.getImpSeguroCampesino());
				result.put("derechoEmision", cotizacion.getImpDerechoEmision());
				result.put("superBancos", cotizacion.getImpSuperBancos());
				result.put("impuestos", cotizacion.getImpSuperBancos()+cotizacion.getImpDerechoEmision()+cotizacion.getImpSeguroCampesino()+cotizacion.getImpIva());
				result.put("primaTotal", cotizacion.getTotalFactura());
				result.put("tasa", cotizacion.getTasaProductoValor());
				result.put("porcentajeIva", PorcentajeIva);
				result.put("porcentajeDerecho", Double.parseDouble(DerechoEmisionGeneral));
			}
			
			if(tipoConsulta.equals("valoresGenerales")){
				//buscamos variables de calculo
				VariableSistemaDAO  variableSistemaDAO = new VariableSistemaDAO();
				VariableSistema variableSistema=variableSistemaDAO.buscarPorNombre("DERECHOS_EMISION_AGRICOLA");
				
				double derechoEmision = Double.parseDouble(variableSistema.getValor());
				variableSistema=variableSistemaDAO.buscarPorNombre("SEGURO_CAMPESINO");
				double seguroCampesino = Double.parseDouble(variableSistema.getValor());
				variableSistema=variableSistemaDAO.buscarPorNombre("SUPER_DE_BANCOS");
				double superBancos = Double.parseDouble(variableSistema.getValor());
				variableSistema=variableSistemaDAO.buscarPorNombre("IVA");
				double PorcentajeIva=Double.parseDouble(variableSistema.getValor());;
				
				result.put("derechoEmision", derechoEmision);
				result.put("seguroCampesino", seguroCampesino);
				result.put("superBancos", superBancos);
				result.put("PorcentajeIva", PorcentajeIva);
				
			}
			
			if(tipoConsulta.equals("encontrarPorProvincia")){
				String idCotizacion = request.getParameter("idCotizacion") == null ? "":request.getParameter("idCotizacion");
				CotizacionDAO cotizacionDAO= new CotizacionDAO();
				Cotizacion cotizacion=cotizacionDAO.buscarPorId(idCotizacion);
				CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();
				CotizacionDetalle cotizacionDetalle = cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).get(0);
				AgriObjetoCotizacionDAO agriObjetoCotizacionDAO= new AgriObjetoCotizacionDAO();
				AgriObjetoCotizacion agriObjetoCotizacion=agriObjetoCotizacionDAO.buscarPorId(new BigInteger( cotizacionDetalle.getObjetoId()));
				
				ProvinciaDAO provinciaDAO= new ProvinciaDAO();
				com.qbe.cotizador.model.Provincia provincia=provinciaDAO.buscarPorId(agriObjetoCotizacion.getProvinciaId().toString());
				
				CantonDAO cantonDAO= new CantonDAO();
				List<com.qbe.cotizador.model.Canton> cantones=cantonDAO.buscarPorProvincia(provincia);
				
				JSONObject cantonObjeto = new JSONObject();
				JSONArray  cantonArray = new JSONArray();
				
				
				for(com.qbe.cotizador.model.Canton rs:cantones){
					cantonObjeto.put("id", rs.getId());
					cantonObjeto.put("nombre", rs.getNombre());
					cantonArray.add(cantonObjeto);
				}
				result.put("cantonArray", cantonArray);				
			}
			
			if(tipoConsulta.equals("encontrarPorProvinciaCanton")){
				String provinciaId = request.getParameter("provinciaId") == null ? "":request.getParameter("provinciaId");
				
				ProvinciaDAO provinciaDAO= new ProvinciaDAO();
				com.qbe.cotizador.model.Provincia provincia=provinciaDAO.buscarPorId(provinciaId);
				
				CantonDAO cantonDAO= new CantonDAO();
				List<com.qbe.cotizador.model.Canton> cantones=cantonDAO.buscarPorProvincia(provincia);
				
				JSONObject cantonObjeto = new JSONObject();
				JSONArray  cantonArray = new JSONArray();
				
				
				for(com.qbe.cotizador.model.Canton rs:cantones){
					cantonObjeto.put("id", rs.getId());
					cantonObjeto.put("nombre", rs.getNombre());
					cantonArray.add(cantonObjeto);
				}
				result.put("cantonArray", cantonArray);				
			}
			
			if(tipoConsulta.equals("encontrarTipoCultivo")){
				AgriTipoCultivoDAO agriTipoCultivoDAO = new AgriTipoCultivoDAO();
				List<AgriTipoCultivo> agriTipoCultivo=agriTipoCultivoDAO.BuscarTodos();
				
				JSONObject cultivoObjeto = new JSONObject();
				JSONArray  cultivoArray = new JSONArray();
				
				
				for(AgriTipoCultivo rs:agriTipoCultivo){
					cultivoObjeto.put("id", rs.getTipoCultivoId());
					cultivoObjeto.put("nombre", rs.getNombre());
					cultivoArray.add(cultivoObjeto);
				}
				result.put("cultivoArray", cultivoArray);				
			}
			
			if(tipoConsulta.equals("encontrarPorCanton")){
				String idCotizacion = request.getParameter("idCotizacion") == null ? "":request.getParameter("idCotizacion");
				CotizacionDAO cotizacionDAO= new CotizacionDAO();
				Cotizacion cotizacion=cotizacionDAO.buscarPorId(idCotizacion);
				CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();
				CotizacionDetalle cotizacionDetalle = cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).get(0);
				AgriObjetoCotizacionDAO agriObjetoCotizacionDAO= new AgriObjetoCotizacionDAO();
				AgriObjetoCotizacion agriObjetoCotizacion=agriObjetoCotizacionDAO.buscarPorId(new BigInteger( cotizacionDetalle.getObjetoId()));
				
				
				CantonDAO cantonDAO= new CantonDAO();
				com.qbe.cotizador.model.Canton cantones=cantonDAO.buscarPorId(agriObjetoCotizacion.getCantonId().toString());
				
				AgriParroquiaDAO agriParroquiaDAO= new AgriParroquiaDAO();
				List<AgriParroquia> agriParroquia=agriParroquiaDAO.BuscarPorCanton(cantones.getId());
				JSONObject parroquiaObjeto = new JSONObject();
				JSONArray  parroquiaArray = new JSONArray();
				
				
				for(AgriParroquia rs:agriParroquia){
					parroquiaObjeto.put("id", rs.getId());
					parroquiaObjeto.put("nombre", rs.getParroquiaNombre());
					parroquiaArray.add(parroquiaObjeto);
				}
				result.put("parroquiaArray", parroquiaArray);				
			}
			
			if(tipoConsulta.equals("encontrarPorParroquiaCanton")){
				String cantonId = request.getParameter("cantonId") == null ? "":request.getParameter("cantonId");
								
				AgriParroquiaDAO agriParroquiaDAO= new AgriParroquiaDAO();
				List<AgriParroquia> agriParroquia=agriParroquiaDAO.BuscarPorCanton(cantonId);
				JSONObject parroquiaObjeto = new JSONObject();
				JSONArray  parroquiaArray = new JSONArray();
				
				
				for(AgriParroquia rs:agriParroquia){
					parroquiaObjeto.put("id", rs.getId());
					parroquiaObjeto.put("nombre", rs.getParroquiaNombre());
					parroquiaArray.add(parroquiaObjeto);
				}
				result.put("parroquiaArray", parroquiaArray);				
			}
			
			if(tipoConsulta.equals("encontrarPorCanal")){
				String idCotizacion = request.getParameter("idCotizacion") == null ? "":request.getParameter("idCotizacion");
				CotizacionDAO cotizacionDAO= new CotizacionDAO();
				Cotizacion cotizacion=cotizacionDAO.buscarPorId(idCotizacion);
				
				AgriParametroXPuntoVentaDAO agriParametroXPuntoVentaDAO = new AgriParametroXPuntoVentaDAO();
				AgriParametroXPuntoVenta agriParametroXPuntoVenta=agriParametroXPuntoVentaDAO.buscarPorPuntoVentaId(new BigInteger(cotizacion.getPuntoVenta().getId()));
				
				List<AgriParametroXPuntoVenta> listAgriParametroXPuntoVenta=agriParametroXPuntoVentaDAO.buscarTodosCanal(agriParametroXPuntoVenta.getCanalId());
				
				JSONObject puntoObjeto = new JSONObject();
				JSONArray  puntoArray = new JSONArray();
				
				
				for(AgriParametroXPuntoVenta rs:listAgriParametroXPuntoVenta){
					puntoObjeto.put("puntoVentaId", rs.getPuntoVentaId());
					PuntoVentaDAO puntoVentaDAO= new PuntoVentaDAO();
					com.qbe.cotizador.model.PuntoVenta puntoVenta=puntoVentaDAO.buscarPorId(rs.getPuntoVentaId().toString());
					puntoObjeto.put("nombre", puntoVenta.getNombre());
					puntoArray.add(puntoObjeto);
				}
				result.put("puntoArray", puntoArray);				
			}
			
			
			
			if(tipoConsulta.equals("encontrarPorCanalId")){
				String idCotizacion = request.getParameter("idCotizacion") == null ? "":request.getParameter("idCotizacion");
				String canalId = request.getParameter("canalId") == null ? "":request.getParameter("canalId");
				CotizacionDAO cotizacionDAO= new CotizacionDAO();
				Cotizacion cotizacion=cotizacionDAO.buscarPorId(idCotizacion);
				
				AgriParametroXPuntoVentaDAO agriParametroXPuntoVentaDAO = new AgriParametroXPuntoVentaDAO();
				List<AgriParametroXPuntoVenta> listAgriParametroXPuntoVenta=agriParametroXPuntoVentaDAO.buscarTodosCanal(new BigInteger(canalId));
				
				JSONObject puntoObjeto = new JSONObject();
				JSONArray  puntoArray = new JSONArray();
				
				
				for(AgriParametroXPuntoVenta rs:listAgriParametroXPuntoVenta){
					puntoObjeto.put("puntoVentaId", rs.getPuntoVentaId());
					PuntoVentaDAO puntoVentaDAO= new PuntoVentaDAO();
					com.qbe.cotizador.model.PuntoVenta puntoVenta=puntoVentaDAO.buscarPorId(rs.getPuntoVentaId().toString());
					puntoObjeto.put("nombre", puntoVenta.getNombre());
					puntoArray.add(puntoObjeto);
				}
				result.put("puntoArray", puntoArray);				
			}
			
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
		}catch(Exception e){
			e.printStackTrace();
			result.put("success", Boolean.FALSE);
			result.put("error", e.getMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
		}
	}

}
