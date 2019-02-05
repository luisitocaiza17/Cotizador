package com.qbe.cotizador.servlets.producto.pymes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.allcolor.yahp.converter.CYaHPConverter;
import org.allcolor.yahp.converter.IHtmlToPdfTransformer;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.cotizacion.TipoObjetoDAO;
import com.qbe.cotizador.dao.entidad.ActividadEconomicaDAO;
import com.qbe.cotizador.dao.entidad.AgenteDAO;
import com.qbe.cotizador.dao.entidad.ClienteDAO;
import com.qbe.cotizador.dao.pagos.CuotaDAO;
import com.qbe.cotizador.dao.pagos.PagoDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeAsistenciaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeCoberturaCotizacionValorDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeDeducibleCotizacionValorDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeObjetoCotizacionCoberturaDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeObjetoCotizacionDAO;
import com.qbe.cotizador.dao.producto.pymes.PymeTextoCoberturaCotizacionDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.ActividadEconomica;
import com.qbe.cotizador.model.Agente;
import com.qbe.cotizador.model.Cliente;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.Cuota;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.PymeAsistencia;
import com.qbe.cotizador.model.PymeAuditoriaGeneral;
import com.qbe.cotizador.model.PymeCoberturaCotizacionValor;
import com.qbe.cotizador.model.PymeDeducibleCotizacionValor;
import com.qbe.cotizador.model.PymeObjetoCotizacion;
import com.qbe.cotizador.model.PymeObjetoCotizacionCobertura;
import com.qbe.cotizador.model.PymeTextoGrupoCoberturaCotizacion;
import com.qbe.cotizador.model.TipoObjeto;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.model.Pago;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.pagos.CuotaTransaction;
import com.qbe.cotizador.transaction.pagos.PagoTransaction;
import com.qbe.cotizador.transaction.producto.pymes.PymeAuditoriaGeneralTransaction;

/**
 * Servlet implementation class PymeRecalculoFacturas
 */
@WebServlet("/PymeRecalculoFacturas")
public class PymeRecalculoFacturas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PymeRecalculoFacturas() {
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
		JSONObject result = new JSONObject();
		try
		{
			CotizacionDAO cotizacionDAO=new CotizacionDAO();
			ClienteDAO clienteDAO=new ClienteDAO();
			PagoDAO pagoDAO=new PagoDAO();
			CuotaDAO cuotaDAO=new CuotaDAO();
			TipoObjetoDAO tipoObjetoDAO=new TipoObjetoDAO(); 
			PagoTransaction pagoTransaction=new PagoTransaction();
			CuotaTransaction cuotaTransaction=new CuotaTransaction();
			CotizacionTransaction cotizacionTransaction=new CotizacionTransaction();
			VariableSistemaDAO variableSistemaDAO=new VariableSistemaDAO();
			TipoObjeto tipoObjeto=tipoObjetoDAO.buscarPorNombre("PYMES");
			List<Cotizacion> cotizaciones= cotizacionDAO.buscarPorTipoObjeto(tipoObjeto);
			VariableSistema variableSistemaIVA=variableSistemaDAO.buscarPorNombre("IVA");
			Double porcentajeIVA=Double.parseDouble(variableSistemaIVA.getValor());
			double valorIva= 0;
			for(Cotizacion cotizacionActual:cotizaciones){
				if(!cotizacionActual.getEstado().getId().equals("2"))
				{
					if(cotizacionActual.getTotalFactura()>0)
					{
						double valor=cotizacionActual.getTotalFactura()/1.12;
						double valorTotalCon12=cotizacionActual.getTotalFactura();
						if(Math.rint((cotizacionActual.getTotalFactura()-valor)*100)/100==cotizacionActual.getImpIva()){
							Double subtotal=Double.parseDouble(cotizacionActual.getPrimaNetaTotal())+cotizacionActual.getImpSuperBancos()+cotizacionActual.getImpSeguroCampesino()+cotizacionActual.getImpDerechoEmision();
							valorIva = Math.rint(porcentajeIVA*subtotal/100*100)/100;
							cotizacionActual.setImpIva(valorIva);
							double valorTotal = Math.rint((subtotal+valorIva)*100)/100;
							cotizacionActual.setTotalFactura(valorTotal);
							cotizacionTransaction.editar(cotizacionActual);

							//Obtener el valor de diferencia
							double valorDiferencia=valorTotalCon12-valorTotal;
							//Actualizo la forma de pago
							if(cotizacionActual.getPago()!=null){
								Pago pago= pagoDAO.buscarPorId(cotizacionActual.getPago().getId());
								if(pago.getId()!=null){
									if(pago.getFormaPago().getId()!=null){
										if(pago.getFormaPago().getId().equals("1")){
											double valorPago=pago.getValorTotal();
											pago.setValorTotal((float)(valorPago+valorDiferencia));
											pago.setCuotaInicial((float)(valorPago+valorDiferencia));
											pagoTransaction.editar(pago);
										}
										else{
											double valorPago=pago.getValorTotal();
											double valorCuotaInicial=pago.getCuotaInicial();
											pago.setValorTotal((float)(valorPago+valorDiferencia));
											List<Cuota> cuotas= cuotaDAO.buscarPorPago(pago);		
											double valorCuotaDiferencia=valorDiferencia/cuotas.size();
											pago.setCuotaInicial((float)(valorCuotaInicial+valorCuotaDiferencia));
											pagoTransaction.editar(pago);
											for(Cuota cuotaActual:cuotas){
												double valorActualCuota=cuotaActual.getValor();
												cuotaActual.setValor(valorActualCuota+valorCuotaDiferencia);
												cuotaTransaction.editar(cuotaActual);
											}
										}
									}
								}
							}
						}

						//Envió del correo
						Cliente cliente=clienteDAO.buscarPorId(cotizacionActual.getClienteId().toString());
						Entidad entidadUsuario=cotizacionActual.getUsuario().getEntidad();
						String html= generarHtmlCotizacion(cotizacionActual.getId(), "/static/plantillas/pymes/solicitudCotizacion.htm");
						byte[] data = GenerarPDF(html, cotizacionActual.getId());

						VariableSistema variableSistema=variableSistemaDAO.buscarPorNombre("RUTA_IMAGENES_EMAILS");

						java.util.Hashtable<String, String> parametersHeader = new java.util.Hashtable<String, String>();
						parametersHeader.put("urlImagenes", variableSistema.getValor());
						parametersHeader.put("nombreCliente", cliente.getEntidad().getNombreCompleto());

						parametersHeader.put("CotizacionId", cotizacionActual.getId());
						parametersHeader.put("UsuarioNombre", entidadUsuario.getNombreCompleto());
						if(entidadUsuario.getTelefono()==null)
							parametersHeader.put("UsuarioTelefono", " ");
						else
							parametersHeader.put("UsuarioTelefono", entidadUsuario.getTelefono());
						parametersHeader.put("UsuarioExtension", " ");
						parametersHeader.put("UsuarioSucursal", " ");

						if(cliente.getEntidad().getMail()!=null){
							MailGenericoPlantillas.EnvioPlantillaGenerica(cliente.getEntidad().getMail(), parametersHeader, "/static/plantillas/pymes/notificacionCotizacionIVA.html", 
									request, data);
						}
					}
				}
			}
			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
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
			pymeAuditoriaGeneral.setObjeto(ex.getMessage()+"||"+ex.getCause());
			try {
				pymeAuditoriaGeneralTransaction.crear(pymeAuditoriaGeneral);
			} catch (Exception e1) {				
				e1.printStackTrace();
			}
			/***RESPUESTA A INTERFAZ***/
			result.put("success", Boolean.FALSE);
			result.put("error", "Error en Servidor, refiérase para soporte con el siguiente código: "+CodError);
			result.put("ex", ex.getMessage());
			response.setContentType("application/json; charset=ISO-8859-1"); 
			result.write(response.getWriter());
			ex.printStackTrace();
		}
	}

	public String generarHtmlCotizacion(String cotizacionID, String plantilla){

		PymeObjetoCotizacionDAO pymeObjetoCotizacionDAO=new PymeObjetoCotizacionDAO();
		PymeTextoCoberturaCotizacionDAO textoCoberturaDAO=new PymeTextoCoberturaCotizacionDAO();
		PymeCoberturaCotizacionValorDAO coberturaValorDAO=new PymeCoberturaCotizacionValorDAO();
		CotizacionDAO cotizacionDAO=new CotizacionDAO();
		CotizacionDetalleDAO cotizacionDetalleDAO=new CotizacionDetalleDAO();
		ClienteDAO clienteDAO=new ClienteDAO();
		AgenteDAO agenteDAO=new AgenteDAO();
		ActividadEconomicaDAO actividadDAO=new ActividadEconomicaDAO();

		Cotizacion cotizacion=cotizacionDAO.buscarPorId(cotizacionID);

		//Obtengo el cliente que cotizo
		Cliente cliente=clienteDAO.buscarPorId(String.valueOf(cotizacion.getClienteId()));

		//Obtengo los datos del agente
		Agente agente=agenteDAO.buscarPorId(String.valueOf(cotizacion.getAgenteId()));

		String html="";
		try{
			String rutaPlantilla = this.getServletContext().getRealPath("") + plantilla;
			html = Files.toString(new File(rutaPlantilla), Charsets.UTF_8);
		}
		catch(IOException ex){
		}

		double subtotal=Double.parseDouble(cotizacion.getPrimaNetaTotal())+cotizacion.getImpSuperBancos()+cotizacion.getImpSeguroCampesino()+cotizacion.getImpRecargoSeguroCampesino()+cotizacion.getImpDerechoEmision();
		java.util.Hashtable<String, String> parametersHeader = new java.util.Hashtable<String, String>();
		parametersHeader.put("CotizacionId", cotizacionID);
		parametersHeader.put("PuntoVenta", cotizacion.getPuntoVenta().getNombre());
		String fechaFormat = new SimpleDateFormat("dd-MM-yyyy").format(cotizacion.getFechaElaboracion());
		parametersHeader.put("FechaCotizacion", fechaFormat);
		parametersHeader.put("ClienteNombre", cliente.getEntidad().getNombreCompleto());
		if(cliente.getEntidad().getTelefono()!=null)
			parametersHeader.put("TelefonoCliente", cliente.getEntidad().getTelefono());
		else
			parametersHeader.put("TelefonoCliente", "");
		parametersHeader.put("BrokerNombre", agente.getEntidad().getNombreCompleto());

		//Aquí poner las direcciones
		String HtmlDirecciones="<table style='width: 100%'>";
		int ContadorDirecciones=1;
		PymeObjetoCotizacion objetoCotizacion=new PymeObjetoCotizacion();
		List<CotizacionDetalle> detallesCotizacion=cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion);
		for(CotizacionDetalle cotizaDetalle: detallesCotizacion){
			objetoCotizacion=pymeObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizaDetalle.getObjetoId()));
			String  actividadEconomicaNombre="";
			if(objetoCotizacion.getActividadEconomicaId()!=null){
				ActividadEconomica actividadEconomica=actividadDAO.buscarPorId(objetoCotizacion.getActividadEconomicaId().toString());
				actividadEconomicaNombre=actividadEconomica.getNombre();
			}
			String DireccionCompleta=objetoCotizacion.getCallePrincipal()+" "+objetoCotizacion.getNumeroDireccion()+" "+objetoCotizacion.getCalleSecundaria();
			HtmlDirecciones+="<tr><td colspan='2' style='font-family: Verdana; font-size: small; font-weight: bold'>RIESGO "+
					+ContadorDirecciones+":</td></tr>";

			HtmlDirecciones+="<tr><td style='font-family: Verdana; font-size: small; width:60%'>"
					+DireccionCompleta
					+"</td><td style='font-family: Verdana; font-size: small; width:40%'>"
					+actividadEconomicaNombre
					+"</td></tr>";
			ContadorDirecciones++;
		}
		HtmlDirecciones+="</table>";
		parametersHeader.put("DireccionesAseguradas", HtmlDirecciones);



		NumberFormat formatter = NumberFormat.getCurrencyInstance();

		parametersHeader.put("ValorPrimaNeta", formatter.format(Double.parseDouble(cotizacion.getPrimaNetaTotal())));
		parametersHeader.put("ValorSuperintendenciaBancos", formatter.format(cotizacion.getImpSuperBancos()).toString());
		parametersHeader.put("ValorSeguroCampesino", formatter.format(cotizacion.getImpSeguroCampesino()).toString());
		parametersHeader.put("ValorDerechos", formatter.format(cotizacion.getImpDerechoEmision()).toString());
		parametersHeader.put("ValorSubtotal", formatter.format(subtotal).toString());
		parametersHeader.put("ValorIva", formatter.format(cotizacion.getImpIva()).toString());
		parametersHeader.put("ValorTotal", formatter.format(cotizacion.getTotalFactura()).toString());


		//Obtener la coberturas de seleccionada en la cotizacion
		List<PymeTextoGrupoCoberturaCotizacion> listadoCoberturas=textoCoberturaDAO.buscarTextoCoberturaCotizacionPorCotizacionId(new BigInteger(cotizacion.getId()), cotizacion.getGrupoPorProductoId());
		String HtmlValoresCoberturas="";
		String HtmlTextoCoberturasAdicionales="<table>";
		for(PymeTextoGrupoCoberturaCotizacion coberturaTexto:listadoCoberturas){
			//Recupero las coberturas del ramo y la cotización
			List<PymeCoberturaCotizacionValor> coberturasValor=coberturaValorDAO.buscarPorCotizacionRamoId(new BigInteger(cotizacion.getId()), coberturaTexto.getRamoId());
			if(coberturaTexto.getRamoId().equals(new BigInteger("7208961"))){ //Determino si es el ramo incendio ya que el tratamiento es diferente
				double valorPrimaCobertura=0;
				double valorAseguradoCobertura=0;
				
				HtmlTextoCoberturasAdicionales+="<tr><td><strong>"+coberturaTexto.getNombre()+"</strong></td></tr>";
				for(PymeCoberturaCotizacionValor coberturaDetalleActual: coberturasValor){
					//if(coberturaDetalleActual.getTipoCoberturaId().toString().equals("3"))
					//{
						HtmlTextoCoberturasAdicionales+="<tr><td>"+coberturaDetalleActual.getNombre()+"  ";
						if(coberturaDetalleActual.getValorIngresado()==0)
							if(coberturaDetalleActual.getValorMaximoLimiteAsegurado()==0)
								HtmlTextoCoberturasAdicionales+=" - Al "+coberturaDetalleActual.getPorcentajeLimiteAsegurado()+"% de Incendio";
							else
								HtmlTextoCoberturasAdicionales+=formatter.format(coberturaDetalleActual.getValorMaximoLimiteAsegurado());
						else
							HtmlTextoCoberturasAdicionales+=formatter.format(coberturaDetalleActual.getValorIngresado());
						HtmlTextoCoberturasAdicionales+="</td></tr>";
					//}
					valorPrimaCobertura+=coberturaDetalleActual.getPrimaCalculada();					
				}
				//Recorro las direcciones y voy poniendo los valores de cada dirección y voy armando los textos de las coberturas
				ContadorDirecciones=1;
				HtmlValoresCoberturas+="<table width='100%'><tr><td colspan='4' style='text-align: center; background-color: #00ACEF; font-family: Verdana;"
						+"font-size: small; font-weight: bold'>"
						+coberturaTexto.getNombre()
						+"</td></tr>";
				
				for(CotizacionDetalle cotizaDetalle: detallesCotizacion){
					objetoCotizacion=pymeObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizaDetalle.getObjetoId()));
					
					HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:40%; font-weight: bold'>"
							+"BIENES ASEGURADOS</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:20%; font-weight: bold'>"
							+"RIESGO " +ContadorDirecciones
							+"</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:20%; font-weight: bold'>"
							+"</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:20%; font-weight: bold'>PRIMA NETA"
							+"</td></tr>";
					if(objetoCotizacion.getValorEstructuras()>0){
						HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>Estructuras:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(objetoCotizacion.getValorEstructuras())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
						valorAseguradoCobertura+=objetoCotizacion.getValorEstructuras();
					}
					if(objetoCotizacion.getValorContenidos()>0){
						HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>Contenidos:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(objetoCotizacion.getValorContenidos())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
						valorAseguradoCobertura+=objetoCotizacion.getValorContenidos();
					}
					if(objetoCotizacion.getValorMueblesEnseres()>0){
						HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>Muebles, enseres y equipos de oficina:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(objetoCotizacion.getValorMueblesEnseres())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
						valorAseguradoCobertura+=objetoCotizacion.getValorMueblesEnseres();
					}
					if(objetoCotizacion.getValorMaquinaria()>0){
						HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>Maquinaria:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(objetoCotizacion.getValorMaquinaria())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
						valorAseguradoCobertura+=objetoCotizacion.getValorMaquinaria();
					}
					if(objetoCotizacion.getValorMercaderia()>0){
						HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>Mercadería:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(objetoCotizacion.getValorMercaderia())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
						valorAseguradoCobertura+=objetoCotizacion.getValorMercaderia();
					}
					if(objetoCotizacion.getValorInsumosNoelectronicos()>0){
						HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>Insumos Médicos y/o de Laboratorio (no electrónicos):</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(objetoCotizacion.getValorInsumosNoelectronicos())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
						valorAseguradoCobertura+=objetoCotizacion.getValorInsumosNoelectronicos();
					}
					if(objetoCotizacion.getValorEquipoHerramienta()>0){
						HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>Equipos y herramientas:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(objetoCotizacion.getValorEquipoHerramienta())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
						valorAseguradoCobertura+=objetoCotizacion.getValorEquipoHerramienta();
					}
					//RECORRO LAS COBERTURAS DEL RAMO INCENDIO PARA OBTENER LAS COBERTURAS POR DIRECCION
					/*List<PymeCoberturaCotizacionValor> coberturasValorDireccion=coberturaValorDAO.buscarPorCotizacionDetalleIdRamoId(new BigInteger(cotizaDetalle.getId()), coberturaTexto.getRamoId());
					for(PymeCoberturaCotizacionValor coberturaDireccion: coberturasValorDireccion){
						if(coberturaDireccion.getNombre().trim().equals("Todo Riesgo Incendio (Edificio + Contenidos)")==false && coberturaDireccion.getValorIngresado()>0)
						{
							HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+coberturaDireccion.getNombre();
							HtmlValoresCoberturas+="</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(coberturaDireccion.getValorIngresado())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
						}
					}*/
					ContadorDirecciones++;
				}
				//RECORRO LAS COBERTURAS DEL RAMO INCENDIO PARA OBTENER LAS COBERTURAS POR DIRECCION
				/*List<PymeCoberturaCotizacionValor> coberturasValorDireccion=coberturaValorDAO.buscarPorCotizacionRamoId(new BigInteger(cotizacion.getId()), coberturaTexto.getRamoId());
				for(PymeCoberturaCotizacionValor coberturaDireccion: coberturasValorDireccion){
					if(coberturaDireccion.getTipoOrigen().equals("GENERAL") || coberturaDireccion.getTipoOrigen().equals("ADICIONALES")){
						if(coberturaDireccion.getNombre().trim().equals("Todo Riesgo Incendio (Edificio + Contenidos)")==false && coberturaDireccion.getValorIngresado()>0)
						{
							HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+coberturaDireccion.getNombre();
							HtmlValoresCoberturas+="</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(coberturaDireccion.getValorIngresado())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
						}
					}
				}*/
				HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2; text-align: right; font-weight: bolder'>Total:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(valorAseguradoCobertura)+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(valorPrimaCobertura)+"</td></tr>";
				HtmlValoresCoberturas+= "</table>";
			}
			else{ //Si no es incendio
				double valorPrimaCobertura=0;
				double valorAseguradoCobertura=0;
				ContadorDirecciones=1;
				HtmlValoresCoberturas+="<table width='100%'><tr><td colspan='4' style='text-align: center; background-color: #00ACEF; font-family: Verdana;"
						+"font-size: small; fot-wenight: bold'>"
						+coberturaTexto.getNombre()
						+"</td></tr>";
				for(CotizacionDetalle cotizaDetalle: detallesCotizacion){
					int contadorDirecciones = 0;
					for(PymeCoberturaCotizacionValor coberturaValor: coberturasValor){
						if(coberturaValor.getCotizacionDetalleId().equals(new BigInteger(cotizaDetalle.getId())))
							contadorDirecciones++;
					}
					if(contadorDirecciones>0){
						
						HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:40%; font-weight: bold'>"
								+"BIENES ASEGURADOS</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:20%; font-weight: bold'>RIESGO " + ContadorDirecciones
								+"</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:20%; font-weight: bold'>"
								+"</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:20%; font-weight: bold'>PRIMA NETA"
								+"</td></tr>";
						HtmlTextoCoberturasAdicionales+="<tr><td><strong>"+coberturaTexto.getNombre()+"</strong></td></tr>";
						HtmlTextoCoberturasAdicionales+="<tr><td colspan='2'>RIESGO " + ContadorDirecciones+"</td></tr>";
						for(PymeCoberturaCotizacionValor coberturaValor: coberturasValor){
							if(coberturaValor.getTipoOrigen().equals("POR DIRECCION")==false)
							{
								HtmlTextoCoberturasAdicionales+="<tr><td>"+coberturaValor.getNombre()+"  ";
								if(coberturaValor.getValorIngresado()==0)
									HtmlTextoCoberturasAdicionales+=formatter.format(coberturaValor.getValorMaximoLimiteAsegurado());
								else
									HtmlTextoCoberturasAdicionales+=formatter.format(coberturaValor.getValorIngresado());
								HtmlTextoCoberturasAdicionales+="</td></tr>";
							}
							if(coberturaValor.getCotizacionDetalleId().equals(new BigInteger(cotizaDetalle.getId())))
							{
								HtmlTextoCoberturasAdicionales+="<tr><td>"+coberturaValor.getNombre()+"</td><td>"+formatter.format(coberturaValor.getValorIngresado())+"</td></tr>";
								HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+coberturaValor.getNombre()+":</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(coberturaValor.getValorIngresado())+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td></tr>";
								valorPrimaCobertura+=coberturaValor.getPrimaCalculada();
								valorAseguradoCobertura+=coberturaValor.getValorIngresado();
							}
						}
					}
					ContadorDirecciones++;
				}

				//Hacemos la tabla de las coberturas generales
				int contadorCoberturasGenerales=0;
				for(PymeCoberturaCotizacionValor coberturaValor: coberturasValor){
					if(coberturaValor.getCotizacionDetalleId().equals(new BigInteger("0")))
						contadorCoberturasGenerales++;
				}
				if(contadorCoberturasGenerales>0){
					HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:40%; font-weight: bold'>"
							+"BIENES ASEGURADOS</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:20%; font-weight: bold'>RIESGO"
							+"</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:20%; font-weight: bold'>"
							+"</td><td style='border-style: none none solid none; border-width: medium; border-color: #00ACEF; width:20%; font-weight: bold'>PRIMA NETA"
							+"</td></tr>";
					HtmlTextoCoberturasAdicionales+="<tr><td><strong>"+coberturaTexto.getNombre()+"</strong></td></tr>";
					HtmlTextoCoberturasAdicionales+="<tr><td colspan='2'>RIESGO</td></tr>";
					for(PymeCoberturaCotizacionValor coberturaValor: coberturasValor){
						HtmlTextoCoberturasAdicionales+="<tr><td>"+coberturaValor.getNombre()+"  ";
						if(coberturaValor.getValorIngresado()==0)
							HtmlTextoCoberturasAdicionales+=formatter.format(coberturaValor.getValorMaximoLimiteAsegurado());
						else
							HtmlTextoCoberturasAdicionales+=formatter.format(coberturaValor.getValorIngresado());
						HtmlTextoCoberturasAdicionales+="</td></tr>";
						valorPrimaCobertura+=coberturaValor.getPrimaCalculada();
						valorAseguradoCobertura+=coberturaValor.getValorIngresado();
					}
				}
				if(valorPrimaCobertura>0 || valorAseguradoCobertura>0)
					HtmlValoresCoberturas+="<tr><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2; text-align: right; font-weight: bolder'>Total:</td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(valorAseguradoCobertura)+"</td><td style='border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'></td><td style='text-align: right; border-style: none none solid none; border-width: thin; border-color: #D2D2D2;'>"+formatter.format(valorPrimaCobertura)+"</td></tr>";

				HtmlValoresCoberturas+="</table>";
			}

		}
		HtmlTextoCoberturasAdicionales+="</table>";
		
		
		PymeObjetoCotizacionCoberturaDAO objetoCCDAO=new PymeObjetoCotizacionCoberturaDAO();
		PymeAsistenciaDAO asistenciaDAO=new PymeAsistenciaDAO();
		List<PymeObjetoCotizacionCobertura> asistenciasCotizacion=objetoCCDAO.buscarPorCotizacionId(new BigInteger(cotizacion.getId()), "ASISTENCIA");
		String HtmlAsistencias="<table width='100%'>";
		for(PymeObjetoCotizacionCobertura asistenciaCotizacion:asistenciasCotizacion){
			PymeAsistencia asistenciaActual=asistenciaDAO.buscarPorId(asistenciaCotizacion.getObjetoOrigenId());
			if(asistenciaActual!=null){
				HtmlAsistencias+="<tr><td style='font-weight: bold'>";
				HtmlAsistencias+=asistenciaActual.getNombre();
				HtmlAsistencias+="</td></tr>";
				if(asistenciaActual.getTexto()!=null){
					String cadena=new String(asistenciaActual.getTexto());
					HtmlAsistencias+="<tr><td>";
					HtmlAsistencias+=cadena;
					HtmlAsistencias+="</td></tr>";
				}
			}
		}
		HtmlAsistencias+="</table>";
		
		//Armo la tablas para los deducibles
		PymeDeducibleCotizacionValorDAO deducibleDAO=new PymeDeducibleCotizacionValorDAO();
		List<PymeDeducibleCotizacionValor> deduciblesCotizacion=deducibleDAO.buscarPorCotizacionId(new BigInteger(cotizacion.getId()));
		String HtmlDeducibles="<table width='100%'>";
		for(PymeDeducibleCotizacionValor deducibleCotizacion:deduciblesCotizacion){
			if(!deducibleCotizacion.getTextoCompletoDeducible().equals("")){
				HtmlDeducibles+="<tr><td style='font-weight: bold'>";
				HtmlDeducibles+=deducibleCotizacion.getNombre();
				HtmlDeducibles+="</td></tr>";
				HtmlDeducibles+="<tr><td>";
				HtmlDeducibles+=deducibleCotizacion.getTextoCompletoDeducible();
				HtmlDeducibles+="</td></tr>";
			}
		}
		HtmlDeducibles+="</table>";
		
		parametersHeader.put("ValoresCoberturas", HtmlValoresCoberturas);

		parametersHeader.put("HtmlAsistencias", HtmlAsistencias);
		
		parametersHeader.put("HtmlDeducibles", HtmlDeducibles);
		
		parametersHeader.put("HtmlCoberturas", HtmlTextoCoberturasAdicionales);

		parametersHeader.put("NombreUsuario", cotizacion.getUsuario().getEntidad().getNombreCompleto());
		if(cotizacion.getUsuario().getEntidad().getMail()!=null)
			parametersHeader.put("EmailUsuario", cotizacion.getUsuario().getEntidad().getMail());
		else
			parametersHeader.put("EmailUsuario", "");
		
		//Poner la tabla de pagos
		double valor=cotizacion.getTotalFactura();
		String htmTablaPagos="<table width='100%' style='border-style: solid'><tr><td style='font-weight: bold'>Cuotas.</td><td style='font-weight: bold'>Valor</td></tr>";
		for(int i=1;i<=10;i++){
			double cuota=valor/i;
			if(i==1 || i==3 || i==5 || i==7 || i==9)
				htmTablaPagos+="<tr><td style='background-color: #CCCCCC;'>"+i+"</td><td style='background-color: #CCCCCC;'>"+formatter.format(cuota)+"</td></tr>";
			else
				htmTablaPagos+="<tr><td>"+i+"</td><td>"+formatter.format(cuota)+"</td></tr>";
		}
		htmTablaPagos+="</table>";
		parametersHeader.put("TablaDePagos", htmTablaPagos);
		
		
		String htmlGenerado=GenerarContenido(html, parametersHeader);

		return htmlGenerado;
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
			String valor=ParamValues.get(detectedParam.replace("[", "").replace("]", ""));
			html=html.replace(detectedParam,  valor);
		}
		return html;
	}
	
	private byte[] GenerarPDF(String html, String CotizacionId){
		java.io.ByteArrayOutputStream out = null;

		//FileOutputStream out = null;
		try {
			CYaHPConverter converter = new CYaHPConverter(false);

			List headerFooterList = new ArrayList();

			// cabecera y pie de página
			//headerFooterList.add(new IHtmlToPdfTransformer.CHeaderFooter("", IHtmlToPdfTransformer.CHeaderFooter.HEADER));

			headerFooterList.add(new IHtmlToPdfTransformer.CHeaderFooter("Página: <pagenumber>/<pagecount><hr />", IHtmlToPdfTransformer.CHeaderFooter.FOOTER));


			Map properties = new HashMap();

			properties.put(IHtmlToPdfTransformer.PDF_RENDERER_CLASS, IHtmlToPdfTransformer.FLYINGSAUCER_PDF_RENDERER);

			// Soporte para fuentes
			properties.put(IHtmlToPdfTransformer.FOP_TTF_FONT_PATH, "c:\\Windows\\Fonts");

			//File fout = new File("D:\\Archivos\\Escritorio\\YaHP-Converter-master\\YaHP-Converter-master\\YaHPConverter\\out\\artifacts\\YaHPConverter\\cosa4.pdf");
			//out = new FileOutputStream(fout);
			out = new ByteArrayOutputStream();

			// si no se pone la etiqueta head, no valen los saltos de línea
			converter.convertToPdf(html,
					IHtmlToPdfTransformer.A4P,
					headerFooterList,
					null,
					out,
					properties);

			out.flush();
			out.close();

			// PDF renderizado en Byte Array
			byte[] result = out.toByteArray();
			return result;

		}catch (Exception ex)
		{
			try {

				out.flush();
				out.close();
			}catch (Exception ignore){}
		}
		return null;
	}

}
