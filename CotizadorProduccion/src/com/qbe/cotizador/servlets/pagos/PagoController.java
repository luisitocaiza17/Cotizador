package com.qbe.cotizador.servlets.pagos;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.joda.time.Days;

import net.sf.json.JSONObject;

import com.qbe.cotizador.dao.cotizacion.CotizacionDAO;
import com.qbe.cotizador.dao.entidad.TipoIdentificacionDAO;
import com.qbe.cotizador.dao.pagos.CuotaDAO;
import com.qbe.cotizador.dao.pagos.FormaPagoDAO;
import com.qbe.cotizador.dao.pagos.InstitucionFinancieraDAO;
import com.qbe.cotizador.dao.pagos.PagoDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.Cuota;
import com.qbe.cotizador.model.FormaPago;
import com.qbe.cotizador.model.InstitucionFinanciera;
import com.qbe.cotizador.model.Pago;
import com.qbe.cotizador.model.SolicitudDescuento;
import com.qbe.cotizador.model.TipoIdentificacion;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.transaction.cotizacion.CotizacionTransaction;
import com.qbe.cotizador.transaction.pagos.CuotaTransaction;
import com.qbe.cotizador.transaction.pagos.PagoTransaction;

/**
 * Servlet implementation class PagoController
 */
@WebServlet("/PagoController")
public class PagoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagoController() {
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
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject result = new JSONObject();
		try{
			String codigoPago = request.getParameter("codigoPago") == null ? "" : request.getParameter("codigoPago");
			String codigoCotizacion = request.getParameter("codigoCotizacion") == null ? "" : request.getParameter("codigoCotizacion");
			String codigoFormaPago = request.getParameter("codigoFormaPago") == null ? "" : request.getParameter("codigoFormaPago");
			String codigoInstFinanciera = request.getParameter("codigoInstFinanciera") == null ? "" : request.getParameter("codigoInstFinanciera");
			String formaPagoSeleccionada = request.getParameter("formaPagoSeleccionada") == null ? "" : request.getParameter("formaPagoSeleccionada");
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? "" : request.getParameter("tipoConsulta");
			String tipoCuenta = request.getParameter("tipoCuenta") == null ? "" : request.getParameter("tipoCuenta");
			String numCuenta = request.getParameter("numCuenta") == null ? "" : request.getParameter("numCuenta");
			String titular = request.getParameter("titular") == null ? "" : request.getParameter("titular");
			String tipoIdentificacionId = request.getParameter("tipoIdentificacionId") == null ? "" : request.getParameter("tipoIdentificacionId");
			String identificacion = request.getParameter("identificacion") == null ? "" : request.getParameter("identificacion");
			String plazo = request.getParameter("plazo") == null ? "" : request.getParameter("plazo");
			String tarjetaAnioExp = request.getParameter("tarjetaAnioExp") == null ? "" : request.getParameter("tarjetaAnioExp");
			String tarjetaMesExp = request.getParameter("tarjetaMesExp") == null ? "" : request.getParameter("tarjetaMesExp");
			String numCheque = request.getParameter("listadoCheques") == null ? "" : request.getParameter("listadoCheques");
			String fechaPago = request.getParameter("fechaPago") == null ? "" : request.getParameter("fechaPago");
			String cuotaInicial = request.getParameter("cuotaInicial") == null ? "" : request.getParameter("cuotaInicial");
			String fechaDebito = request.getParameter("fechaDebito") == null ? "" : request.getParameter("fechaDebito");
			


			PagoTransaction pagoTransaction = new PagoTransaction();
			CuotaTransaction cuotaTransaction = new CuotaTransaction();
			CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
			
			if(!fechaPago.equals("") && fechaDebito.equals("")){
				String [] fechaPagoConv = fechaPago.split("-");
				fechaPago = fechaPagoConv[2] + "/" + fechaPagoConv[1] + "/" + fechaPagoConv[0];				
			}else
				fechaPago = fechaDebito.replace("-", "/");
			
			InstitucionFinancieraDAO institucionFinancieraDAO = new InstitucionFinancieraDAO();
			
			FormaPago formaPago = new FormaPago();
			FormaPagoDAO formaPagoDAO = new FormaPagoDAO();
			
			Cuota cuota = new Cuota();
			CuotaDAO cuotaDAO = new CuotaDAO();
			
			Pago pago = new Pago();
			PagoDAO pagoDAO = new PagoDAO();

			TipoIdentificacion tipoIdentificacion = new TipoIdentificacion();
			TipoIdentificacionDAO tipoIdentificacionDAO = new TipoIdentificacionDAO();
			
			if (!tipoIdentificacionId.equals("")){
				tipoIdentificacion = tipoIdentificacionDAO.buscarPorId(tipoIdentificacionId);
				pago.setTipoIdentificacionId(tipoIdentificacion);
			}
				
			if (!codigoPago.equals("")&&!codigoPago.equals("-1")){
				pago=pagoDAO.buscarPorId(codigoPago);
			}
			
			if (!codigoFormaPago.equals("")){
				formaPago=formaPagoDAO.buscarPorId(codigoFormaPago);
				pago.setFormaPago(formaPago);
			}

			if (!codigoInstFinanciera.equals("")){
				pago.setInstitucionFinanciera(institucionFinancieraDAO.buscarPorId(codigoInstFinanciera));
			}

			/*if (!valor.equals(""))
				pago.setValorTotal(new Float(valor));
			else
				valor = "0";*/
			
			if (!identificacion.equals(""))
				pago.setIdentificacionTitular(identificacion);

			if (!titular.equals(""))
				pago.setNombreTitular(titular);

			if (!numCuenta.equals(""))
				pago.setNumeroCuentaTarjeta(numCuenta);

			if (!tarjetaAnioExp.equals(""))
				pago.setAnioExpiracionTarjeta(tarjetaAnioExp);

			if (!tarjetaMesExp.equals(""))
				pago.setMesExpiracionTarjeta(tarjetaMesExp);

			if (!plazo.equals(""))
				pago.setPlazonEnMes(plazo);
			
			if(!tipoCuenta.equals(""))
				pago.setTipoCuenta(tipoCuenta);
			
			if(!cuotaInicial.equals("")){
				pago.setCuotaInicial(Float.valueOf(cuotaInicial));
				//pago.setPlazonEnMes((Integer.parseInt(plazo)+1)+"");
			}
			
			if(tipoConsulta.equals("guardarPorCotizacion")){
				result.put("pagoId", guardarPorCotizacion(request));
				
			}
			
			if(tipoConsulta.equals("crear")){
				
				Cotizacion cotizacion = new Cotizacion();
				CotizacionDAO cotizacionDAO = new CotizacionDAO();
				if(!codigoCotizacion.equals("")) 
					cotizacion = cotizacionDAO.buscarPorId(codigoCotizacion);
				//cotizacion.setPago(pago);
				cotizacion.setEtapaWizard(3);
				
				if(cotizacion.getSolicitudDescuentos()!=null&&cotizacion.getSolicitudDescuentos().size()>0){
					SolicitudDescuento solicitudDescuento=cotizacion.getSolicitudDescuentos().get(0); 
					if(solicitudDescuento.getEstado().getNombre().equals("Pendiente"))
						throw new Exception("Tiene una solicitud de descuento pendiente! No puede guardar el pago!");
				}
				
				
				//cotizacionDAO.editar(cotizacion);
				
				Double valorTotal = cotizacion.getTotalFactura();
				Integer numeroCuotas = new Integer(plazo);
				
				String arrListadoCheques[] = new String[numeroCuotas];
				arrListadoCheques = numCheque.split(",");
				//SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd"); 
				SimpleDateFormat curFormater2 = new SimpleDateFormat("yyyy/MM/dd"); 
				Date dateObj;
				dateObj = curFormater2.parse(fechaPago);
				Date fechaActual =new Date();
				
				int diferencia=Days.daysBetween(new DateTime(fechaActual), new DateTime(dateObj)).getDays();
				
				if(fechaActual.after(dateObj))
					throw new Exception("El pago de la primera cuota no puede ser antes de la fecha actual");
				if(fechaActual.before(dateObj)&& diferencia>15)
					throw new Exception("El pago de la primera cuota no puede ser despues de 15 días de la fecha actual");
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dateObj);
				
				//si tiene fecha de debito
				//if(!fechaDebito.equals(""))
					
					//calendar.setTime(new Date(Integer.parseInt(fechaDebito.substring(0, 4))-1900,Integer.parseInt(fechaDebito.substring(5, 7))-1,Integer.parseInt(fechaDebito.substring(8))));
				pago.setValorTotal(valorTotal.floatValue());
				pago = pagoTransaction.crear(pago);
				cotizacion.setPago(pago);
				cotizacion=cotizacionTransaction.editar(cotizacion);
				
				int indiceCheques = 0;
				if ((formaPagoSeleccionada.equals("cuotas")||formaPagoSeleccionada.equals("debitoBanco")||formaPagoSeleccionada.equals("debitoTarjeta")) && (new Double(cuotaInicial) > new Double("0"))){
					cuota.setValor(new BigDecimal(cuotaInicial).setScale(2, RoundingMode.HALF_UP).doubleValue());
					cuota.setFechaPago(new Date(curFormater2.format(dateObj)));
					//cuota.setNumeroCheque(arrListadoCheques[0]);
					cuota.setNumeroCheque("0");
					cuota.setPago(pago);
					cuota.setOrden(0);
					cuotaTransaction.crear(cuota);
					valorTotal = valorTotal - new Double(cuotaInicial);
					indiceCheques++;
					calendar.add(Calendar.MONTH, 1);
					dateObj =  calendar.getTime();
				}
				
				if (formaPagoSeleccionada.equals("contado")){
					pago.setValorTotal(valorTotal.floatValue());
					pago.setCuotaInicial(Float.parseFloat(cotizacion.getTotalFactura()+""));	
				}
				
				for (int i=0; i < numeroCuotas; i++){
					cuota=new Cuota();
					/*if(new Double(cuotaInicial)>0&&i==0)
					{
						cuota.setOrden(0);
						cuota.setValor(new Double(cuotaInicial));
						cuota.setPago(pago);
						cuota.setFechaPago(dateObj);
						cuotaDAO.crear(cuota);
						cuota=new Cuota();
						calendar.add(Calendar.MONTH, 1);
						dateObj =  calendar.getTime();
						numeroCuotas++;
					}*/
					Double valorCuotas = (valorTotal / numeroCuotas);
					int orden = i + 1;
					cuota.setOrden(orden);
					cuota.setValor(new BigDecimal(valorCuotas).setScale(2, RoundingMode.HALF_UP).doubleValue());
					if (numCheque  == "Cuota Inicial")
						cuota.setNumeroCheque(arrListadoCheques[indiceCheques + i]);
					else
						cuota.setNumeroCheque("0");
					
					cuota.setFechaPago(new Date(curFormater2.format(dateObj)));
					cuota.setPago(pago);
					cuotaTransaction.crear(cuota);
					
					calendar.add(Calendar.MONTH, 1);
					dateObj =  calendar.getTime();
				}
			}

			if(tipoConsulta.equals("actualizar")){
				cuotaDAO.eliminarPorPago(pago);
				
				Cotizacion cotizacion = new Cotizacion();
				CotizacionDAO cotizacionDAO = new CotizacionDAO();
				if(!codigoCotizacion.equals("")) 
					cotizacion = cotizacionDAO.buscarPorId(codigoCotizacion);
				//cotizacion.setPago(pago);
				cotizacion.setEtapaWizard(3);
				
				if(cotizacion.getSolicitudDescuentos()!=null&&cotizacion.getSolicitudDescuentos().size()>0){
					SolicitudDescuento solicitudDescuento=cotizacion.getSolicitudDescuentos().get(0); 
					if(solicitudDescuento.getEstado().getNombre().equals("Pendiente"))
						throw new Exception("Tiene una solicitud de descuento pendiente! No puede guardar el pago!");
				}
				
				/**/
				Double valorTotal = cotizacion.getTotalFactura();
				Integer numeroCuotas = new Integer(plazo);
				
				String arrListadoCheques[] = new String[numeroCuotas];
				arrListadoCheques = numCheque.split(",");
				SimpleDateFormat curFormater2 = new SimpleDateFormat("yyyy/MM/dd"); 
				Date dateObj;
				dateObj = curFormater2.parse(fechaPago);
				Date fechaActual =new Date();
				
				int diferencia=Days.daysBetween(new DateTime(fechaActual), new DateTime(dateObj)).getDays();
				
				if(fechaActual.after(dateObj))
					throw new Exception("El pago de la primera cuota no puede ser antes de la fecha actual");
				if(fechaActual.before(dateObj)&& diferencia>15)
					throw new Exception("El pago de la primera cuota no puede ser despues de 15 días de la fecha actual");
				
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dateObj);
				
				pago.setValorTotal(valorTotal.floatValue());
				pago=cotizacion.getPago();
				int indiceCheques = 0;
				pago = pagoTransaction.editar(pago);
				cotizacion.setPago(pago);
				cotizacion.setAgenteId(cotizacion.getAgenteId());
				cotizacion=cotizacionTransaction.editar(cotizacion);
				
				if ((formaPagoSeleccionada.equals("cuotas")||formaPagoSeleccionada.equals("debitoBanco")||formaPagoSeleccionada.equals("debitoTarjeta")) && (new Double(cuotaInicial) > new Double("0"))){
					cuota.setValor(new BigDecimal(cuotaInicial).setScale(2, RoundingMode.HALF_UP).doubleValue());
					cuota.setFechaPago(new Date(curFormater2.format(dateObj)));
					//cuota.setNumeroCheque(arrListadoCheques[0]);
					cuota.setNumeroCheque("0");
					cuota.setPago(pago);
					cuota.setOrden(0);
					cuotaTransaction.crear(cuota);
					valorTotal = valorTotal - new Double(cuotaInicial);
					//numeroCuotas --;
					indiceCheques++;
					calendar.add(Calendar.MONTH, 1);
					dateObj =  calendar.getTime();
				} 
				
				if (formaPagoSeleccionada.equals("contado")){
					pago.setValorTotal(valorTotal.floatValue());
					pago.setCuotaInicial(Float.parseFloat(cotizacion.getTotalFactura()+""));	
				}
				
			for (int i=0; i < numeroCuotas; i++){
				cuota=new Cuota();
				/*if(new Double(cuotaInicial)>0&&i==0)
				{
					cuota.setOrden(0);
					cuota.setValor(new Double(cuotaInicial));
					cuota.setPago(pago);
					cuota.setFechaPago(dateObj);
					cuotaDAO.crear(cuota);
					calendar.add(Calendar.MONTH, 1);
					dateObj =  calendar.getTime();
					numeroCuotas++;
				}*/
				Double valorCuotas = (valorTotal / numeroCuotas);
				int orden = i + 1;
				cuota.setOrden(orden);
				cuota.setValor(new BigDecimal(valorCuotas).setScale(2, RoundingMode.HALF_UP).doubleValue());
				if (numCheque  == "Cuota Inicial")
					cuota.setNumeroCheque(arrListadoCheques[indiceCheques + i]);
				else
					cuota.setNumeroCheque("0");
				
				cuota.setFechaPago(new Date(curFormater2.format(dateObj)));
				cuota.setPago(pago);
				cuotaTransaction.crear(cuota);
				
				calendar.add(Calendar.MONTH, 1);
				dateObj =  calendar.getTime();
			}
		}

				
			if(tipoConsulta.equals("eliminar")){
				pago=pagoDAO.buscarPorId(pago.getId());
				CotizacionDAO cotizacionDAO=new CotizacionDAO();
				Cotizacion cotizacion=cotizacionDAO.buscarPorPago(pago);
				cotizacion.setPago(null);
				cotizacion=cotizacionTransaction.editar(cotizacion);
				pagoTransaction.eliminar(pago);
			}
				
			
			result.put("codigoPagoRegistrado", pago.getId());
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
	
	public String guardarPorCotizacion(HttpServletRequest request) throws Exception{


		PagoTransaction pagoTransaction = new PagoTransaction();
		CuotaTransaction cuotaTransaction = new CuotaTransaction();
		CotizacionTransaction cotizacionTransaction = new CotizacionTransaction();
		
		String codigoCotizacion = request.getParameter("codigoCotizacion") == null ? "" : request.getParameter("codigoCotizacion");
		String codigoInstFinanciera = request.getParameter("codigoInstFinanciera") == null ? "" : request.getParameter("codigoInstFinanciera");
		String formaPagoSeleccionada = request.getParameter("formaPagoSeleccionada") == null ? "" : request.getParameter("formaPagoSeleccionada");
		String tipoCuenta = request.getParameter("tipoCuenta") == null ? "" : request.getParameter("tipoCuenta");
		String numCuenta = request.getParameter("numCuenta") == null ? "" : request.getParameter("numCuenta");
		String nombresTitularDebitos = request.getParameter("nombresTitularDebitos") == null ? "" : request.getParameter("nombresTitularDebitos");
		String apellidosTitularDebitos = request.getParameter("apellidosTitularDebitos") == null ? "" : request.getParameter("apellidosTitularDebitos");
		String nombreCompletoTitularDebitos = request.getParameter("nombreCompletoTitularDebitos") == null ? "" : request.getParameter("nombreCompletoTitularDebitos");
		String nombresTitularTarjetas = request.getParameter("nombresTitularTarjetas") == null ? "" : request.getParameter("nombresTitularTarjetas");
		String apellidosTitularTarjetas = request.getParameter("apellidosTitularTarjetas") == null ? "" : request.getParameter("apellidosTitularTarjetas");
		String nombreCompletoTitularTarjetas = request.getParameter("nombreCompletoTitularTarjetas") == null ? "" : request.getParameter("nombreCompletoTitularTarjetas");
		String tipoIdentificacionId = request.getParameter("tipoIdentificacionId") == null ? "" : request.getParameter("tipoIdentificacionId");
		String identificacion = request.getParameter("identificacion") == null ? "" : request.getParameter("identificacion");
		String plazo = request.getParameter("plazo") == null ? "" : request.getParameter("plazo");
		String tarjetaAnioExp = request.getParameter("tarjetaAnioExp") == null ? "" : request.getParameter("tarjetaAnioExp");
		String tarjetaMesExp = request.getParameter("tarjetaMesExp") == null ? "" : request.getParameter("tarjetaMesExp");
		String fechaPago = request.getParameter("fechaPago") == null ? "" : request.getParameter("fechaPago");
		String cuotaInicial = request.getParameter("cuotaInicial") == null ? "" : request.getParameter("cuotaInicial");
		
		Cotizacion cotizacion = new Cotizacion();
		CotizacionDAO cotizacionDAO = new CotizacionDAO();
		
		if(!codigoCotizacion.equals("")) 
			cotizacion = cotizacionDAO.buscarPorId(codigoCotizacion);
		//cotizacion.setPago(pago);
		cotizacion.setEtapaWizard(3);
		
		if(cotizacion.getSolicitudDescuentos()!=null&&cotizacion.getSolicitudDescuentos().size()>0){
			SolicitudDescuento solicitudDescuento=cotizacion.getSolicitudDescuentos().get(0); 
			if(solicitudDescuento.getEstado().getNombre().equals("Pendiente"))
				throw new Exception("Tiene una solicitud de descuento pendiente! No puede guardar el pago!");
		}

		Pago pago=new Pago();
		if(cotizacion.getPago()!=null)
			pago=cotizacion.getPago();
		
		FormaPagoDAO fpDAO=new FormaPagoDAO();
		FormaPago fp=fpDAO.buscarPorId(formaPagoSeleccionada);
		
		Double plazoNum= Double.parseDouble(plazo);
				
		pago.setAnioExpiracionTarjeta(tarjetaAnioExp);
		pago.setFormaPago(fp);
		pago.setIdentificacionTitular(identificacion);
		pago.setMesExpiracionTarjeta(tarjetaMesExp);
		// Guardamos el nombre de titular de la cuenta 
		if(!tipoIdentificacionId.equalsIgnoreCase("4")){					
			if(formaPagoSeleccionada.equalsIgnoreCase("2"))
				pago.setNombreTitular(nombresTitularDebitos.toUpperCase()+"-"+apellidosTitularDebitos.toUpperCase());
			if(formaPagoSeleccionada.equalsIgnoreCase("3"))
				pago.setNombreTitular(nombresTitularTarjetas.toUpperCase()+"-"+apellidosTitularTarjetas.toUpperCase());
		}else{
			if(formaPagoSeleccionada.equalsIgnoreCase("2"))
				pago.setNombreTitular(nombreCompletoTitularDebitos.toUpperCase());
			if(formaPagoSeleccionada.equalsIgnoreCase("3"))
				pago.setNombreTitular(nombreCompletoTitularTarjetas.toUpperCase());
		}
		pago.setNumeroCuentaTarjeta(numCuenta);	
		pago.setTipoCuenta(tipoCuenta);
		pago.setValorTotal((float) cotizacion.getTotalFactura());
		pago.setTipoCuenta(tipoCuenta);
		pago.setPlazonEnMes(""+plazoNum.intValue());
		pago.setCuotaInicial(0);
		
		TipoIdentificacionDAO tiDAO=new TipoIdentificacionDAO();
		TipoIdentificacion ti=tiDAO.buscarPorId(tipoIdentificacionId);
		
		if(ti!=null&&ti.getId()!=null)
			pago.setTipoIdentificacionId(ti);
		else
			pago.setTipoIdentificacionId(null);
		
		InstitucionFinancieraDAO ifDAO=new InstitucionFinancieraDAO();
		InstitucionFinanciera insFin=ifDAO.buscarPorId(codigoInstFinanciera);
		
		if(insFin!=null&&insFin.getId()!=null)
			pago.setInstitucionFinanciera(insFin);
		else
			pago.setInstitucionFinanciera(null);
		
		CuotaDAO cDAO=new CuotaDAO();
		cDAO.eliminarPorPago(pago);

		Cuota inicial=new Cuota();
		
		if(pago.getId()==null)
			pago=pagoTransaction.crear(pago);
		else
			pago=pagoTransaction.editar(pago);
				
		if(fp.getNombre().trim().toUpperCase().equals("CONTADO")){
			Date fechaActual=new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(fechaActual);
			calendar.add(Calendar.DATE, 15);
			
			pago.setPlazonEnMes("1");
			pago.setCuotaInicial(pago.getValorTotal());
			
			inicial.setValor(pago.getValorTotal());
			inicial.setFechaPago(calendar.getTime());
			inicial.setOrden(0);
			inicial.setNumeroCheque("");
			inicial.setPago(pago);
			cuotaTransaction.crear(inicial);		
		}
		
		if(fp.getNombre().trim().toUpperCase().equals("DEBITO BANCARIO")||fp.getNombre().trim().toUpperCase().equals("DEBITO TARJETA")||fp.getNombre().trim().toUpperCase().equals("CREDITO CUOTAS")){
			
			SimpleDateFormat curFormater2 = new SimpleDateFormat("yyyy-MM-dd"); 
			Date fechaPagoDate=new Date();
			Calendar calendar = Calendar.getInstance();
			
			if(!fechaPago.equals(""))
				fechaPagoDate = curFormater2.parse(fechaPago);
			else{
				calendar.setTime(fechaPagoDate);
				calendar.add(Calendar.DATE, 15);
				fechaPagoDate=calendar.getTime();
			}
			Date fechaActual =new Date();
			Date inicioVigencia=cotizacion.getVigenciaDesde();
			int diferenciaActualPago=Days.daysBetween(new DateTime(fechaActual), new DateTime(fechaPagoDate)).getDays();
			int diferenciaVigenciaPago=Days.daysBetween(new DateTime(inicioVigencia), new DateTime(fechaPagoDate)).getDays();
			
			
			VariableSistemaDAO vsDAO=new VariableSistemaDAO();
			VariableSistema vs= vsDAO.buscarPorNombre("MAXIMO_DIAS_PAGO");
			
			int numMaximoDiasPago = new Integer(vs.getValor());	
			
			if(inicioVigencia.after(fechaActual)){
				if(fechaPagoDate.after(inicioVigencia)&&diferenciaVigenciaPago>numMaximoDiasPago)
					throw new Exception ("La fecha de pago debe ser hasta "+numMaximoDiasPago+" dias despues del inicio de vigencia");
				if(fechaPagoDate.before(fechaActual))
					throw new Exception ("La fecha de pago no puede ser anterior a la fecha actual");
			}
			else{
				if(fechaPagoDate.before(fechaActual))
					throw new Exception ("La fecha de pago no puede ser anterior a la fecha actual");
				if(fechaPagoDate.after(fechaActual)&&diferenciaActualPago>numMaximoDiasPago)
					throw new Exception ("La fecha de pago debe ser hasta "+numMaximoDiasPago+" dias despues de la fecha actual");
			}

			calendar.setTime(fechaPagoDate);
			Double valorCuotas=cotizacion.getTotalFactura()/plazoNum;
			
			if(!cuotaInicial.equals("")&&!cuotaInicial.equals("0")){
				Double cuotaInicialNum=Double.parseDouble(cuotaInicial);
				pago.setCuotaInicial(cuotaInicialNum.floatValue());
				pago.setPlazonEnMes(""+new Double((plazoNum+1)+"").intValue());
				
				inicial.setValor(cuotaInicialNum.floatValue());
				inicial.setFechaPago(calendar.getTime());
				inicial.setOrden(0);
				inicial.setNumeroCheque("");
				inicial.setPago(pago);
				cuotaTransaction.crear(inicial);	
				
				valorCuotas=(cotizacion.getTotalFactura()-inicial.getValor())/plazoNum;
				
				calendar.add(Calendar.MONTH, 1);
			}
					
			for(int i=0;i<plazoNum;i++){
				Cuota cuota = new Cuota();
				cuota.setValor(valorCuotas);
				cuota.setOrden(i+1);
				cuota.setNumeroCheque("");
				cuota.setFechaPago(calendar.getTime());
				cuota.setPago(pago);
				
				cuotaTransaction.crear(cuota);	
				
				calendar.add(Calendar.MONTH, 1);
			}
			
		}
		
		pago=pagoTransaction.editar(pago);
		cotizacion.setPago(pago);
		cotizacion=cotizacionTransaction.editar(cotizacion);

		return pago.getId();
	}

}
