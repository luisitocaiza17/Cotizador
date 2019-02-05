package com.qbe.cotizador.servlets.producto.agricola;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.qbe.cotizador.dao.cotizacion.CotizacionDetalleDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriAuditoriaCotizacionDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriObjetoCotizacionDAO;
import com.qbe.cotizador.dao.producto.agricola.CotizacionAprobacionDAO;
import com.qbe.cotizador.dao.seguridad.TipoVariableDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.AgriAuditoriaCotizacion;
import com.qbe.cotizador.model.AgriCotizacionAprobacion;
import com.qbe.cotizador.model.AgriObjetoCotizacion;
import com.qbe.cotizador.model.AgriObjetoDetalle;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.CotizacionDetalle;
import com.qbe.cotizador.model.TipoVariable;
import com.qbe.cotizador.model.VariableSistema;
import com.qbe.cotizador.transaction.producto.agricola.AgriObjetoDetalleTransaction;

public class AgriCotizacionDetalleProcesos {

	public void creardetalleCotizacion(Cotizacion cotizacion,String proceso) throws Exception{
		/**PROCESO DE CREACION DEL DETALLE DE LA COTIZACION ENDOSO***/
		AgriCotizacionAprobacion agriCotizacionAprobacion = new AgriCotizacionAprobacion();
		CotizacionAprobacionDAO cotizacionAprobacionDAO = new CotizacionAprobacionDAO();
		agriCotizacionAprobacion=cotizacionAprobacionDAO.buscarPorId(cotizacion.getId());
		//llenamos el objeto
		AgriObjetoDetalle agriObjetoDetalle = new AgriObjetoDetalle();
		agriObjetoDetalle.setCotizacionId(agriCotizacionAprobacion.getId());
		agriObjetoDetalle.setAgricolaId(agriCotizacionAprobacion.getIdCotizacion2());
		try{
			agriObjetoDetalle.setPeriodoId(new BigInteger(agriCotizacionAprobacion.getCicloId()));
		}catch(Exception e){
			agriObjetoDetalle.setPeriodoId(new BigInteger("1"));
			e.printStackTrace();
		}
		agriObjetoDetalle.setCanalId(agriCotizacionAprobacion.getCanalId());
		agriObjetoDetalle.setPuntoVentaId(agriCotizacionAprobacion.getPuntoVentaId());
		agriObjetoDetalle.setNumeroTramite(agriCotizacionAprobacion.getNumeroTramite());
		
		try{
			agriObjetoDetalle.setVigenciaDias(Double.parseDouble(agriCotizacionAprobacion.getVigenciaDias().toString()));
		}catch(Exception e){
			System.out.println("no se guardo los dias de vigencia");
		}
		
		if(cotizacion.getEstado().getNombre().equals(("Pendiente de Emitir"))){
			
			CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();
			CotizacionDetalle cotizacionDetalle= cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).get(0);
			
			AgriObjetoCotizacionDAO agriObjetoCotizacionDAO = new AgriObjetoCotizacionDAO();
			AgriObjetoCotizacion agriObjetoCotizacion = agriObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizacionDetalle.getObjetoId())); 
			
			agriObjetoDetalle.setVigenciaDesde(agriObjetoCotizacion.getFechaSiembra());
			
			Date fechaFin=new Date();
			try{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(agriObjetoCotizacion.getFechaSiembra());
			calendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(agriCotizacionAprobacion.getVigenciaDias().toString()));
			fechaFin=calendar.getTime();
			}catch(Exception e){
				e.printStackTrace();
			}					
			agriObjetoDetalle.setVigenciaHasta(fechaFin);
		}
		agriObjetoDetalle.setCantonId(agriCotizacionAprobacion.getCantonId());
		agriObjetoDetalle.setClienteId(new BigInteger(agriCotizacionAprobacion.getClienteId()));
		agriObjetoDetalle.setCultivoId(agriCotizacionAprobacion.getTipoCultivoId());
		agriObjetoDetalle.setProvinciaId(agriCotizacionAprobacion.getProvinciaId());		
		try{
			agriObjetoDetalle.setParroquiaId(agriCotizacionAprobacion.getParroquiaId());
		}catch (Exception e) {
			e.printStackTrace();
		}
		agriObjetoDetalle.setSitioInversion(agriCotizacionAprobacion.getDireccionSiembra());
		agriObjetoDetalle.setTelefono(agriCotizacionAprobacion.getTelefono());
		agriObjetoDetalle.setHectareasAseguradas(Double.parseDouble(""+agriCotizacionAprobacion.getHectareasAsegurables()));
		agriObjetoDetalle.setHectareasLote(Double.parseDouble(""+agriCotizacionAprobacion.getHectareasLote()));
		
		Date date = new Date(agriCotizacionAprobacion.getFechaSiembra().getTime());
		
		agriObjetoDetalle.setFechaSiembra(date);
		agriObjetoDetalle.setMontoAsegurado(Double.parseDouble(""+agriCotizacionAprobacion.getSumaAseguradaTotal()));
		agriObjetoDetalle.setCostoProduccion(Double.parseDouble(""+agriCotizacionAprobacion.getCostoProduccion()));
		agriObjetoDetalle.setTasa(Double.parseDouble(""+agriCotizacionAprobacion.getTasa()));
		agriObjetoDetalle.setSuperBancos(Double.parseDouble(""+agriCotizacionAprobacion.getSeguroBancos()));
		agriObjetoDetalle.setDerechoEmision(Double.parseDouble(""+agriCotizacionAprobacion.getDerechoEmision()));
		agriObjetoDetalle.setIva(Double.parseDouble(""+agriCotizacionAprobacion.getIva()));
		agriObjetoDetalle.setPrimaTotal(Double.parseDouble(""+agriCotizacionAprobacion.getTotalFactura()));
		agriObjetoDetalle.setPrimaNeta(Double.parseDouble(""+agriCotizacionAprobacion.getPrimaNetaTotal()));
		try{
			agriObjetoDetalle.setLongitud(""+agriCotizacionAprobacion.getLongitud());
			agriObjetoDetalle.setLatitud(""+agriCotizacionAprobacion.getLatitud());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		agriObjetoDetalle.setDisponeAsistencia(agriCotizacionAprobacion.getDisponeAsistencia());
		agriObjetoDetalle.setDisponeRiego(agriCotizacionAprobacion.getDisponeRiego());
		agriObjetoDetalle.setAgricultorTecnificado(agriCotizacionAprobacion.getAgricultorTecnificado());
		agriObjetoDetalle.setObservacion(proceso);
		agriObjetoDetalle.setObservacionQbe(agriCotizacionAprobacion.getObservacionQBE());
		agriObjetoDetalle.setEstado(agriCotizacionAprobacion.getEstadoCotizacion());
		try{
			agriObjetoDetalle.setUsuarioOffline(agriCotizacionAprobacion.getUsuarioOffline());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		AgriObjetoDetalleTransaction agriObjetoDetalleTransaction = new AgriObjetoDetalleTransaction();
		agriObjetoDetalleTransaction.crear(agriObjetoDetalle);
		
		/**FIN DE REGISTRO DE ENDOSO**/
	}
	
	public Cotizacion DetallesCalculo(Cotizacion cotizacion,double primaNeta){
		
		double valorPrimaCalculada=primaNeta;
		TipoVariableDAO tipoVariableDao = new TipoVariableDAO();
		TipoVariable tipoVariable = tipoVariableDao.buscarPorId("3");
		VariableSistemaDAO variableDAO = new VariableSistemaDAO();
		List<VariableSistema> variablesistema = variableDAO
				.buscarTipoVariable(tipoVariable);
		double valorBase = 0;
		double valorDerechosEmision = 0;
		double valorSeguroCampesino = 0;
		double valorSuperBancos = 0;
		double valorIva = 0;
		double valorSubTotal = 0;
		double valorTotal = 0;
		if (variablesistema.size() > 0) {
			for (VariableSistema variable : variablesistema) {
				
				if (variable.getNombre().equals("DERECHOS_EMISION_AGRICOLA")) {
					valorBase = valorDerechosEmision+valorBase;
							
					// valorDerechosEmision =
					// Double.parseDouble(variable.getValor());
					cotizacion.setImpDerechoEmision(valorDerechosEmision);
				} else if (variable.getNombre().equals(
						"SEGURO_CAMPESINO")) {
					valorSeguroCampesino = Math.rint(Double
							.parseDouble(variable.getValor())
							* valorPrimaCalculada / 100 * 100) / 100;
					valorBase = valorBase + valorSeguroCampesino;
					cotizacion.setImpSeguroCampesino(valorSeguroCampesino);
				} else if (variable.getNombre().equals(
						"SUPER_DE_BANCOS")) {
					valorSuperBancos = Math.rint(Double
							.parseDouble(variable.getValor())
							* valorPrimaCalculada / 100 * 100) / 100;
					cotizacion.setImpSuperBancos(valorSuperBancos);
					valorBase = valorBase + valorSuperBancos;
				}
				
			}
							
			VariableSistema variableIva= variableDAO.buscarPorNombre("IVA");					
			valorSubTotal=valorBase+valorPrimaCalculada;
			//calculamos el Iva
			String PorcentajeIva=variableIva.getValor();
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
		
		return cotizacion;
	}
	
	
	public void creardetalleCotizacionTemporal(Cotizacion cotizacion,String proceso){
		/**PROCESO DE CREACION DEL DETALLE DE LA COTIZACION ENDOSO***/
		AgriCotizacionAprobacion agriCotizacionAprobacion = new AgriCotizacionAprobacion();
		CotizacionAprobacionDAO cotizacionAprobacionDAO = new CotizacionAprobacionDAO();
		agriCotizacionAprobacion=cotizacionAprobacionDAO.buscarPorId(cotizacion.getId());
		//llenamos el objeto
		AgriObjetoDetalle agriObjetoDetalle = new AgriObjetoDetalle();
		agriObjetoDetalle.setCotizacionId(agriCotizacionAprobacion.getId());
		agriObjetoDetalle.setAgricolaId(agriCotizacionAprobacion.getIdCotizacion2());
		agriObjetoDetalle.setPeriodoId(new BigInteger(agriCotizacionAprobacion.getCicloId()));
		agriObjetoDetalle.setCanalId(agriCotizacionAprobacion.getCanalId());
		agriObjetoDetalle.setPuntoVentaId(agriCotizacionAprobacion.getPuntoVentaId());
		agriObjetoDetalle.setNumeroTramite(agriCotizacionAprobacion.getNumeroTramite());
		
		try{
			agriObjetoDetalle.setVigenciaDias(Double.parseDouble(agriCotizacionAprobacion.getVigenciaDias().toString()));
		}catch(Exception e){
			System.out.println("no se guardo los dias de vigencia");
		}
		
		if(cotizacion.getEstado().getNombre().equals(("Pendiente de Emitir"))){
			
			CotizacionDetalleDAO cotizacionDetalleDAO = new CotizacionDetalleDAO();
			CotizacionDetalle cotizacionDetalle= cotizacionDetalleDAO.buscarCotizacionDetallePorCotizacion(cotizacion).get(0);
			
			AgriObjetoCotizacionDAO agriObjetoCotizacionDAO = new AgriObjetoCotizacionDAO();
			AgriObjetoCotizacion agriObjetoCotizacion = agriObjetoCotizacionDAO.buscarPorId(new BigInteger(cotizacionDetalle.getObjetoId())); 
			
			agriObjetoDetalle.setVigenciaDesde(agriObjetoCotizacion.getFechaSiembra());
			
			Date fechaFin=new Date();
			try{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(agriObjetoCotizacion.getFechaSiembra());
			calendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(agriCotizacionAprobacion.getVigenciaDias().toString()));
			fechaFin=calendar.getTime();
			}catch(Exception e){
				e.printStackTrace();
			}					
			agriObjetoDetalle.setVigenciaHasta(fechaFin);
		}
		agriObjetoDetalle.setCantonId(agriCotizacionAprobacion.getCantonId());
		agriObjetoDetalle.setClienteId(new BigInteger(agriCotizacionAprobacion.getClienteId()));
		agriObjetoDetalle.setCultivoId(agriCotizacionAprobacion.getTipoCultivoId());
		agriObjetoDetalle.setProvinciaId(agriCotizacionAprobacion.getProvinciaId());
		agriObjetoDetalle.setParroquiaId(agriCotizacionAprobacion.getParroquiaId());
		agriObjetoDetalle.setSitioInversion(agriCotizacionAprobacion.getDireccionSiembra());
		agriObjetoDetalle.setTelefono(agriCotizacionAprobacion.getTelefono());
		agriObjetoDetalle.setHectareasAseguradas(Double.parseDouble(""+agriCotizacionAprobacion.getHectareasAsegurables()));
		agriObjetoDetalle.setHectareasLote(Double.parseDouble(""+agriCotizacionAprobacion.getHectareasLote()));
		
		Date date = new Date(agriCotizacionAprobacion.getFechaSiembra().getTime());
		
		agriObjetoDetalle.setFechaSiembra(date);
		agriObjetoDetalle.setMontoAsegurado(Double.parseDouble(""+agriCotizacionAprobacion.getSumaAseguradaTotal()));
		agriObjetoDetalle.setCostoProduccion(Double.parseDouble(""+agriCotizacionAprobacion.getCostoProduccion()));
		agriObjetoDetalle.setTasa(Double.parseDouble(""+agriCotizacionAprobacion.getTasa()));
		agriObjetoDetalle.setSuperBancos(Double.parseDouble(""+agriCotizacionAprobacion.getSeguroBancos()));
		agriObjetoDetalle.setDerechoEmision(Double.parseDouble(""+agriCotizacionAprobacion.getDerechoEmision()));
		agriObjetoDetalle.setIva(Double.parseDouble(""+agriCotizacionAprobacion.getIva()));
		agriObjetoDetalle.setPrimaTotal(Double.parseDouble(""+agriCotizacionAprobacion.getTotalFactura()));
		agriObjetoDetalle.setPrimaNeta(Double.parseDouble(""+agriCotizacionAprobacion.getPrimaNetaTotal()));
		agriObjetoDetalle.setLongitud(""+agriCotizacionAprobacion.getLongitud());
		agriObjetoDetalle.setLatitud(""+agriCotizacionAprobacion.getLatitud());
		agriObjetoDetalle.setDisponeAsistencia(agriCotizacionAprobacion.getDisponeAsistencia());
		agriObjetoDetalle.setDisponeRiego(agriCotizacionAprobacion.getDisponeRiego());
		agriObjetoDetalle.setAgricultorTecnificado(agriCotizacionAprobacion.getAgricultorTecnificado());
		agriObjetoDetalle.setObservacion(proceso);
		agriObjetoDetalle.setObservacionQbe(agriCotizacionAprobacion.getObservacionQBE());
		agriObjetoDetalle.setEstado(agriCotizacionAprobacion.getEstadoCotizacion());
		agriObjetoDetalle.setUsuarioOffline(agriCotizacionAprobacion.getUsuarioOffline());
		
		AgriObjetoDetalleTransaction agriObjetoDetalleTransaction = new AgriObjetoDetalleTransaction();
		agriObjetoDetalleTransaction.crear(agriObjetoDetalle);
		
		/**FIN DE REGISTRO DE ENDOSO**/
	}
	
}