package com.qbe.cotizador.servlets.producto.agricola;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.common.io.Files;
import com.google.common.base.Charsets;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.persistence.internal.expressions.OuterJoinExpressionHolder;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.qbe.cotizador.dao.cotizacion.ProductoDAO;
import com.qbe.cotizador.dao.cotizacion.ProductoXPuntoVentaDAO;
import com.qbe.cotizador.dao.entidad.CantonDAO;
import com.qbe.cotizador.dao.entidad.ParroquiaDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.entidad.PuntoVentaDAO;
import com.qbe.cotizador.dao.entidad.TipoIdentificacionDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriAgenciaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCicloDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCultivoCanalDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParametroXCanalDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParametroXPuntoVentaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriParroquiaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriReglaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCalculoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCultivoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCultivoXTipoCalculoDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriVariedadDAO;
import com.qbe.cotizador.dao.producto.vehiculocerrado.GrupoPorProductoDAO;
import com.qbe.cotizador.dao.seguridad.TipoVariableDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.AgriAgencia;
import com.qbe.cotizador.model.AgriCiclo;
import com.qbe.cotizador.model.AgriCultivoCanal;
import com.qbe.cotizador.model.AgriParametroXCanal;
import com.qbe.cotizador.model.AgriParametroXPuntoVenta;
import com.qbe.cotizador.model.AgriParroquia;
import com.qbe.cotizador.model.AgriRegla;
import com.qbe.cotizador.model.AgriTipoCalculo;
import com.qbe.cotizador.model.AgriTipoCultivo;
import com.qbe.cotizador.model.AgriTipoCultivoXTipoCalculo;
import com.qbe.cotizador.model.AgriVariedad;
import com.qbe.cotizador.model.Canton;
import com.qbe.cotizador.model.GrupoPorProducto;
import com.qbe.cotizador.model.ProductoXPuntoVenta;
import com.qbe.cotizador.model.Provincia;
import com.qbe.cotizador.model.Parroquia;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.TipoIdentificacion;
import com.qbe.cotizador.model.VariableSistema;
import com.sun.faces.taglib.html_basic.OutputTextTag;

/**
 * Servlet implementation class AgriExportarDatos
 */
@WebServlet("/AgriExportarDatos")
public class AgriExportarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AgriExportarDatos() {
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
		
		//Cotizador 1.- Credife 2.- Cooprogreso 3.- Agripac
		String Cotizador = request.getParameter("cotizador") == null ? "" : request.getParameter("cotizador");
		
		//JSONObject retorno=new JSONObject();
		String result=generarData(Cotizador);

//		response.setContentType("text/plain; charset=ISO-8859-1"); 
//		response.getWriter().write(result);
		
		response.setHeader("Content-Transfer-Encoding", "binary"); 
		response.setContentLength(result.getBytes("UTF-8").length);
		response.setHeader("Content-Encoding", "none");
		response.setContentType("application/force-download");
		response.setHeader("Content-Disposition","attachment; filename=" + "DataCotizadorOffline.config");//fileName);

		
		ServletOutputStream  o = response.getOutputStream();
		o.write(result.getBytes("UTF-8")); 
		o.flush(); 
		o.close(); 
		return;
	}

	private String generarData(String Cotizador){
		TransporteData nuevaData=new TransporteData();

		//Exportar los datos de las reglas
		AgriReglaDAO agriReglaDAO = new AgriReglaDAO();
		
		PuntoVentaDAO puntoVentaRefDAO = new PuntoVentaDAO();
		PuntoVenta puntoVentaRef= new PuntoVenta();
		
		String NombreCotizado="";
		switch (Cotizador) {
		case "1":
			NombreCotizado="CREDIFE";			
			break;
		case "2":
			NombreCotizado="COOPROGRESO";
			break;
		case "3":
			NombreCotizado="AGRIPAC";
			break;
		default:
			break;
		}
		
		puntoVentaRef=puntoVentaRefDAO.buscarPorNombre(NombreCotizado);
		
		AgriParametroXPuntoVentaDAO agriParametroXPuntoVentaDAO = new AgriParametroXPuntoVentaDAO();
		AgriParametroXPuntoVenta agriParametroXPuntoVenta =agriParametroXPuntoVentaDAO.buscarPorPuntoVentaId(new BigInteger(puntoVentaRef.getId()));
		AgriTipoCalculoDAO agriTipoCalculoDAO = new AgriTipoCalculoDAO();
		AgriTipoCalculo agriTipoCalculo=agriTipoCalculoDAO.BuscarPorId(agriParametroXPuntoVenta.getTipoCalculoId());
		
		
		AgriCultivoCanalDAO agriCultivoCanalDAO= new AgriCultivoCanalDAO();
		List<TipoCultivo> listadoTipoCultivo=new ArrayList<TipoCultivo>();
		switch (Cotizador) {
		case "1":
			List<AgriCultivoCanal> agriCultivoCanal= agriCultivoCanalDAO.buscarTipoCalculo(agriTipoCalculo.getTipoCalculoId());
			
			for (AgriCultivoCanal tipoCultivo: agriCultivoCanal)
			{
				TipoCultivo nuevoTipo=new TipoCultivo();
				nuevoTipo.setTipoCultivoId(tipoCultivo.getTipoCultivoId());
				nuevoTipo.setNombre(tipoCultivo.getNombre());
				nuevoTipo.setTipo(tipoCultivo.getTipo());
				nuevoTipo.setVigenciaDias(tipoCultivo.getVigenciaDias());
				//TODO: se agrega el precio de ajuste y nombre de plantilla 
				nuevoTipo.setPrecioAjuste(tipoCultivo.getPrecioAjuste());
				nuevoTipo.setPrecioAjuste2(tipoCultivo.getPrecioAjuste2());
				nuevoTipo.setUnidadMedida(tipoCultivo.getUnidadMedida());
				nuevoTipo.setUnidadMedida2(tipoCultivo.getUnidadMedida2());
				listadoTipoCultivo.add(nuevoTipo);
			}
			break;
		case "2":
			
			List<AgriCultivoCanal> agriCultivoCanalCooprogreso= agriCultivoCanalDAO.buscarTipoCalculo(agriTipoCalculo.getTipoCalculoId());
			for (AgriCultivoCanal tipoCultivo: agriCultivoCanalCooprogreso)
			{
				TipoCultivo nuevoTipo=new TipoCultivo();
				nuevoTipo.setTipoCultivoId(tipoCultivo.getTipoCultivoId());
				nuevoTipo.setNombre(tipoCultivo.getNombre());
				nuevoTipo.setTipo(tipoCultivo.getTipo());
				nuevoTipo.setVigenciaDias(tipoCultivo.getVigenciaDias());
				//TODO: se agrega el precio de ajuste y nombre de plantilla 
				nuevoTipo.setPrecioAjuste(tipoCultivo.getPrecioAjuste());
				nuevoTipo.setPrecioAjuste2(tipoCultivo.getPrecioAjuste2());
				nuevoTipo.setUnidadMedida(tipoCultivo.getUnidadMedida());
				nuevoTipo.setUnidadMedida2(tipoCultivo.getUnidadMedida2());
				listadoTipoCultivo.add(nuevoTipo);
			}
			break;
		case "3":
			
			List<AgriCultivoCanal> agriCultivoCanalAgripac= agriCultivoCanalDAO.buscarTipoCalculo(agriTipoCalculo.getTipoCalculoId());
			for (AgriCultivoCanal tipoCultivo: agriCultivoCanalAgripac)
			{
				TipoCultivo nuevoTipo=new TipoCultivo();
				nuevoTipo.setTipoCultivoId(tipoCultivo.getTipoCultivoId());
				nuevoTipo.setNombre(tipoCultivo.getNombre());
				nuevoTipo.setTipo(tipoCultivo.getTipo());
				nuevoTipo.setVigenciaDias(tipoCultivo.getVigenciaDias());
				//TODO: se agrega el precio de ajuste y nombre de plantilla 
				nuevoTipo.setPrecioAjuste(tipoCultivo.getPrecioAjuste());
				nuevoTipo.setPrecioAjuste2(tipoCultivo.getPrecioAjuste2());
				nuevoTipo.setUnidadMedida(tipoCultivo.getUnidadMedida());
				nuevoTipo.setUnidadMedida2(tipoCultivo.getUnidadMedida2());
				listadoTipoCultivo.add(nuevoTipo);
			}
			break;
		default:
			break;
		}
		
		
		List<AgriRegla> listadoAgriReglas = agriReglaDAO.BuscarPorTipoCalculoNombre(agriTipoCalculo.getNombre());
		///TODO: Se obtiene unicamante los que son para el offline 
		//List<AgriRegla> listadoAgriReglas = agriReglaDAO.BuscarPorTipoCalculoNombre("GENERAL");
		List<Regla> listadoReglas=new ArrayList<Regla>();
		for (AgriRegla reglaActual: listadoAgriReglas)
		{
			Regla nuevaRegla=new Regla();
			nuevaRegla.setReglaId(reglaActual.getReglaId());
			nuevaRegla.setTipoCultivoId(reglaActual.getTipoCultivoId());	
			nuevaRegla.setTipoCalculoId(reglaActual.getTipoCalculoId());
			nuevaRegla.setCicloId(reglaActual.getClicloId());
			nuevaRegla.setProvinciaId(reglaActual.getProvinciaId());
			nuevaRegla.setCantonId(reglaActual.getCantonId());
			nuevaRegla.setCostoProduccion(reglaActual.getCostoProduccion());
			nuevaRegla.setTasa(reglaActual.getTasa());
			nuevaRegla.setCostoMantenimiento(reglaActual.getCostoMantenimiento());
			nuevaRegla.setAceptabilidadDesde(reglaActual.getAceptabilidadDesde());
			nuevaRegla.setAceptabilidadHasta(reglaActual.getAceptabilidadHasta());
			nuevaRegla.setObservaciones(reglaActual.getObservaciones());
			listadoReglas.add(nuevaRegla);
		}

		//Exportar los datos de los ciclos
		AgriCicloDAO agriCicloDAO = new AgriCicloDAO();
		List<AgriCiclo> listadoCiclos = agriCicloDAO.BuscarTodos();
		List<Ciclo> listaCiclos=new ArrayList<Ciclo>();
		for (AgriCiclo Ciclo: listadoCiclos)
		{
			Ciclo nuevoCiclo=new Ciclo();
			nuevoCiclo.setCicloId(Ciclo.getClicloId());
			nuevoCiclo.setNombre(Ciclo.getNombre());
			nuevoCiclo.setFechaInicio(Ciclo.getFechaInicio());
			nuevoCiclo.setFechaFin(Ciclo.getFechaFin());
			listaCiclos.add(nuevoCiclo);
		}

		//Exportar datos de las provincias
		ProvinciaDAO provinciaDAO=new ProvinciaDAO();
		List<Provincia> listadoProvincias = provinciaDAO.buscarTodos();
		List<com.qbe.cotizador.servlets.producto.agricola.Provincia> listaProvincias=new ArrayList<com.qbe.cotizador.servlets.producto.agricola.Provincia>();
		for(Provincia provinciaActual:listadoProvincias){
			com.qbe.cotizador.servlets.producto.agricola.Provincia nuevaProvincia=new com.qbe.cotizador.servlets.producto.agricola.Provincia();
			nuevaProvincia.setProvinciaId(provinciaActual.getId());
			nuevaProvincia.setNombre(provinciaActual.getNombre());
			listaProvincias.add(nuevaProvincia);
		}

		//Exportar datos de los cantones
		CantonDAO cantonDAO=new CantonDAO();
		List<Canton> listadoCantones = cantonDAO.buscarTodos();
		List<com.qbe.cotizador.servlets.producto.agricola.Canton> listaCantones=new ArrayList<com.qbe.cotizador.servlets.producto.agricola.Canton>();
		for(Canton cantonActual:listadoCantones){
			com.qbe.cotizador.servlets.producto.agricola.Canton nuevoCanton=new com.qbe.cotizador.servlets.producto.agricola.Canton();
			nuevoCanton.setCantonId(cantonActual.getId());
			nuevoCanton.setProvinciaId(cantonActual.getProvincia().getId());
			nuevoCanton.setNombre(cantonActual.getNombre());
			listaCantones.add(nuevoCanton);
		}
		
		//Exportar datos de las variedades
		AgriVariedadDAO variedadDAO=new AgriVariedadDAO();
		List<AgriVariedad> listadoVariedades = variedadDAO.BuscarTodos();
		List<com.qbe.cotizador.servlets.producto.agricola.Variedad> listaVariedades=new ArrayList<com.qbe.cotizador.servlets.producto.agricola.Variedad>();
		for(AgriVariedad variedadActual:listadoVariedades){
			com.qbe.cotizador.servlets.producto.agricola.Variedad nuevaVariedad = new com.qbe.cotizador.servlets.producto.agricola.Variedad();
			nuevaVariedad.setVariedadId(variedadActual.getVariedadId());
			nuevaVariedad.setTipoCultivoId(variedadActual.getTipoCultivoId());
			nuevaVariedad.setNombre(variedadActual.getNombre());
			listaVariedades.add(nuevaVariedad);
		}

		//Exportar datos de los parametros
		AgriParametroXPuntoVentaDAO parametroPuntoVentaDAO=new AgriParametroXPuntoVentaDAO();
		List<AgriParametroXPuntoVenta> listadoParametros = parametroPuntoVentaDAO.buscarTodos();
		List<com.qbe.cotizador.servlets.producto.agricola.ParametrosXPuntoVenta> listaParametros=new ArrayList<com.qbe.cotizador.servlets.producto.agricola.ParametrosXPuntoVenta>();
		for(AgriParametroXPuntoVenta parametroActual:listadoParametros){
			com.qbe.cotizador.servlets.producto.agricola.ParametrosXPuntoVenta nuevoParametro=new com.qbe.cotizador.servlets.producto.agricola.ParametrosXPuntoVenta();
			nuevoParametro.setTipoCalculoId(parametroActual.getTipoCalculoId());
			nuevoParametro.setPuntoVentaID(parametroActual.getPuntoVentaId());
			listaParametros.add(nuevoParametro);
		}

		AgriParroquiaDAO parroquiaDAO=new AgriParroquiaDAO();
		List<AgriParroquia> listadoParroquias = parroquiaDAO.buscarTodos();
		List<com.qbe.cotizador.servlets.producto.agricola.Parroquia> listaParroquias=new ArrayList<com.qbe.cotizador.servlets.producto.agricola.Parroquia>();
		for(AgriParroquia parroquiaActual:listadoParroquias){
			com.qbe.cotizador.servlets.producto.agricola.Parroquia nuevoParroquia=new com.qbe.cotizador.servlets.producto.agricola.Parroquia();
			nuevoParroquia.setCantonId(parroquiaActual.getCantonId());
			nuevoParroquia.setParroquiaId(""+parroquiaActual.getId());
			nuevoParroquia.setNombre(parroquiaActual.getParroquiaNombre());
			listaParroquias.add(nuevoParroquia);
		}
		
		//Exporto los datos de los puntos de venta
		GrupoPorProductoDAO grupoPorProducto=new GrupoPorProductoDAO();
		ProductoXPuntoVentaDAO productoXPuntoVentaDAO=new ProductoXPuntoVentaDAO();
		PuntoVentaDAO puntoVentaDAO=new PuntoVentaDAO();
		GrupoPorProducto grupoProducto = grupoPorProducto.buscarPorNombre("Agricola");
		List<com.qbe.cotizador.servlets.producto.agricola.PuntoVenta> listaPuntosVenta=new ArrayList<com.qbe.cotizador.servlets.producto.agricola.PuntoVenta>();
		if(grupoProducto!=null){
			List<ProductoXPuntoVenta> listadoProductoXPuntoVenta= productoXPuntoVentaDAO.buscarPorProductoPuntoVentaListado(grupoProducto);
			for(ProductoXPuntoVenta productoPuntoVenta:listadoProductoXPuntoVenta){
				PuntoVenta puntoVenta=puntoVentaDAO.buscarPorId(productoPuntoVenta.getPuntoVenta().getId());
				com.qbe.cotizador.servlets.producto.agricola.PuntoVenta nuevoPuntoVenta=new com.qbe.cotizador.servlets.producto.agricola.PuntoVenta();
				nuevoPuntoVenta.setNombre(puntoVenta.getNombre());
				nuevoPuntoVenta.setPuntoVentaID(puntoVenta.getId());
				listaPuntosVenta.add(nuevoPuntoVenta);
			}
		}


		//Exportar datos de los tipos de identificacion
		TipoIdentificacionDAO tipoIdentificacionDAO=new TipoIdentificacionDAO();
		List<TipoIdentificacion> listadoTipos=tipoIdentificacionDAO.buscarTodos();
		List<com.qbe.cotizador.servlets.producto.agricola.TipoIdentificacion> listaTipos=new ArrayList<com.qbe.cotizador.servlets.producto.agricola.TipoIdentificacion>();
		for(TipoIdentificacion tipoIdentificacionActual:listadoTipos){
			com.qbe.cotizador.servlets.producto.agricola.TipoIdentificacion nuevoTipoIdentificacion=new com.qbe.cotizador.servlets.producto.agricola.TipoIdentificacion();
			nuevoTipoIdentificacion.setId(tipoIdentificacionActual.getId());
			nuevoTipoIdentificacion.setNombre(tipoIdentificacionActual.getNombre());
			listaTipos.add(nuevoTipoIdentificacion);
		}

		//Exportar datos de tipos de cultivo por tipo de calcul
		AgriTipoCultivoXTipoCalculoDAO tipoCultivoTipoCalculoDAO=new AgriTipoCultivoXTipoCalculoDAO();
		List<AgriTipoCultivoXTipoCalculo> listadoTCXTC = tipoCultivoTipoCalculoDAO.BuscarTodos();
		List<com.qbe.cotizador.servlets.producto.agricola.TipoCultivoXTipoCalculo> listaTiposConfigurados=new ArrayList<com.qbe.cotizador.servlets.producto.agricola.TipoCultivoXTipoCalculo>();
		for(AgriTipoCultivoXTipoCalculo tipoActual:listadoTCXTC){
			com.qbe.cotizador.servlets.producto.agricola.TipoCultivoXTipoCalculo nuevoTipo=new com.qbe.cotizador.servlets.producto.agricola.TipoCultivoXTipoCalculo();
			nuevoTipo.setTipoCalculoId(tipoActual.getTipoCalculoId());
			nuevoTipo.setTipoCultivoId(tipoActual.getTipoCalculoId());
			nuevoTipo.setCoberturaTexto(tipoActual.getCoberturaText());
			nuevoTipo.setDeducibleTexto(tipoActual.getDeducibleTexto());
			nuevoTipo.setMetodoIndemnizacionTexto(tipoActual.getMetodoIndemnizacionTexto());
			listaTiposConfigurados.add(nuevoTipo);
		}
		//TODO:Exportar datos de la agencia
		AgriAgenciaDAO agenciaDAO = new AgriAgenciaDAO();
		List<AgriAgencia>listadoAgencias = agenciaDAO.buscarTodos();
		List<com.qbe.cotizador.servlets.producto.agricola.Agencia> listaAgenciaConfigurados=new ArrayList<com.qbe.cotizador.servlets.producto.agricola.Agencia>();
		for (AgriAgencia agencia: listadoAgencias){
			com.qbe.cotizador.servlets.producto.agricola.Agencia nuevoAgencia=new com.qbe.cotizador.servlets.producto.agricola.Agencia();
			nuevoAgencia.setAgenciaId(agencia.getId());
			nuevoAgencia.setPuntoVentaId(agencia.getpuntoVentaId());
			nuevoAgencia.setNombreAgencia(agencia.getAgenciaNombre());
			nuevoAgencia.setCodigo(agencia.getCodigo());
			listaAgenciaConfigurados.add(nuevoAgencia);
		}
		//TODO: parametros generales 
		VariableSistemaDAO variablesDAO = new VariableSistemaDAO();
		String MailFacturacionElectronica="";
		String DiasValidacionCultivo="";
		VariableSistema variablesSistema=variablesDAO.buscarPorNombre("MAIL_FACTURACION_ELECTRONICA");
		MailFacturacionElectronica = variablesSistema.getValor();
		variablesSistema=variablesDAO.buscarPorNombre("DIAS_VALIDACION_CULTIVO");
		DiasValidacionCultivo = variablesSistema.getValor();
		ParametroGeneral nuevoParametroGeneral=new ParametroGeneral();
		nuevoParametroGeneral.setDiasValidacionCultivo(Integer.parseInt(DiasValidacionCultivo));
		nuevoParametroGeneral.setMailFactuacionElectronica(MailFacturacionElectronica);
		
		//Exportar los datos de los parametros por canal para el nombre de la plantilla
		
		AgriParametroXCanalDAO agriParametroXCanalDAO = new AgriParametroXCanalDAO();
		List<AgriParametroXCanal> resultsPxC = agriParametroXCanalDAO.ObtenerPorPuntoVenta(new BigInteger(puntoVentaRef.getId()));
		List<ParametrosXCanal> listadoParametrosXCanal=new ArrayList<ParametrosXCanal>(); 
		for (AgriParametroXCanal parametroXCanal: resultsPxC)
			{
				ParametrosXCanal nuevoParametroCanal=new ParametrosXCanal();
				nuevoParametroCanal.setTipoCultivoId(parametroXCanal.getTipoCultivoId());
				nuevoParametroCanal.setNombrePlantilla(parametroXCanal.getPlantillaNombre());
				nuevoParametroCanal.setCanalId(parametroXCanal.getPuntoVentaId());
				nuevoParametroCanal.setPlantillaHtml(parametroXCanal.getPlantillaHtml());
				nuevoParametroCanal.setContenedorNumero(parametroXCanal.getContenedorNumero());
				listadoParametrosXCanal.add(nuevoParametroCanal);
			}
		
				
		nuevaData.setParametrosGenerales(nuevoParametroGeneral);
		nuevaData.setCantones(listaCantones);
		nuevaData.setCiclos(listaCiclos);
		nuevaData.setParroquias(listaParroquias);
		nuevaData.setProvincias(listaProvincias);
		nuevaData.setReglas(listadoReglas);
		nuevaData.setTiposCultivos(listadoTipoCultivo);
		nuevaData.setTiposIdentificacion(listaTipos);
		nuevaData.setPuntosVentaAgricola(listaPuntosVenta);
		nuevaData.setParametrosPuntoVenta(listaParametros);
		nuevaData.setVariedades(listaVariedades);
		nuevaData.setTiposCultivoXTiposCalculos(listaTiposConfigurados);
		nuevaData.setAgencias(listaAgenciaConfigurados);
		nuevaData.setParametrosXCanal(listadoParametrosXCanal);
		/*nuevaData.setPlantillas(listaPlantillaReporte);*/

		try{
			Gson g=new Gson();
			String data=g.toJson(nuevaData);
			String dataPreparada=com.qbe.cotizador.util.AES_Helper.padString(data);
			String dataSerializada= com.qbe.cotizador.util.AES_Helper.encrypt(dataPreparada);
			return dataSerializada;
		}
		catch(Exception ex){
			return "";	
		}

	}

}