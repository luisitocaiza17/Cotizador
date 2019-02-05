package com.qbe.cotizador.servicios.QBE.tagh;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.qbe.cotizador.dao.entidad.CantonDAO;
import com.qbe.cotizador.dao.entidad.ParroquiaDAO;
import com.qbe.cotizador.dao.entidad.ProvinciaDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriCotizacionesDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriObjetoCotizacionDAO;
import com.qbe.cotizador.dao.producto.agricola.AgriTipoCultivoDAO;
import com.qbe.cotizador.model.AgriCotizaciones;
import com.qbe.cotizador.model.AgriTipoCultivo;
import com.qbe.cotizador.model.Canton;
import com.qbe.cotizador.model.Parroquia;
import com.qbe.cotizador.model.Provincia;

public class MidpointTagh {
	
	/*Función para obtener la información de las pólizas agrícolas vigentes, dado un número de identificación.*/
	public DatosTagh[] consultarPolizas(String token, String identificacion){
		String tokenLocal ="J1LbTEde/zE6nLGqZKfwZVqclsz1zxh07MCZeH2+v3QxmislX+xbEvOXnJ9HKF4pO3yQohmZdnn6RjA9RvUcgg";
		DatosTagh respuestaFinal = new DatosTagh();
		
		/*Procesos de instancia de objetos a utlizar*/
		AgriCotizaciones agriCotizaciones = new AgriCotizaciones();
		AgriCotizacionesDAO agriCotizacionesDAO = new AgriCotizacionesDAO();
		AgriTipoCultivo tipoCultivo= new AgriTipoCultivo();
		AgriTipoCultivoDAO tipoCultivoDAO = new AgriTipoCultivoDAO();
		Provincia provincia = new Provincia();
		ProvinciaDAO provinciaProcesos = new ProvinciaDAO();
		Canton canton = new Canton();
		CantonDAO cantonProcesos = new CantonDAO();
		Parroquia parroquia= new Parroquia();
		ParroquiaDAO parroquiaProcesos= new ParroquiaDAO();
		
		try{
			if(tokenLocal.equals(token)){
				List<AgriCotizaciones> lasCotizaciones = agriCotizacionesDAO.BuscarPorClienteIdentificacion(identificacion);
				int tamañoArray=lasCotizaciones.size();
				int tamaño=tamañoArray;
				if(tamañoArray==0)
					tamañoArray=1;
				DatosTagh[] arrayRespuesta= new DatosTagh[tamañoArray];
				int contador=0;
				if(tamaño==0){
					DatosTagh respuesta = new DatosTagh();
					respuesta.setObservaciones("No existen pólizas vigentes con esta identificación: "+identificacion);
					arrayRespuesta[0]=respuesta;
				}
				for(AgriCotizaciones rs : lasCotizaciones){
					DatosTagh respuesta = new DatosTagh();
					respuesta.setPolizaId(rs.getTramite().toString());
					respuesta.setTarifaId(null);
					/*Calculo de fechas*/
					Date fechaInicio=rs.getVigenciaDesde();
					tipoCultivo=tipoCultivoDAO.BuscarPorId(rs.getTipoCultivoId());
					if(tipoCultivo.getTipoCultivoId()==null)
						throw new Exception("Error El cultivo no existe");
					int dias= tipoCultivo.getVigenciaDias();
					Date fechaFin=fechaInicio;
					try{
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(fechaInicio);
					calendar.add(Calendar.DAY_OF_YEAR, dias);
					fechaFin=calendar.getTime();
					}catch(Exception e){
						e.printStackTrace();
					}
					//Proceso de cambio de formato solicitado yyyy/MM/dd hh:mm:ss GMT0
					SimpleDateFormat sm = new SimpleDateFormat("yyyy/MM/dd");
					try {
						respuesta.setVigenciaInicio(""+sm.format(fechaInicio)+" 00:00:00");
					}catch(Exception e){
						e.printStackTrace();
					}
					try {
						respuesta.setVigenciaFin(""+sm.format(fechaFin)+" 00:00:00");
					}catch(Exception e){
						e.printStackTrace();
					}
					try{
						respuesta.setFechaSiembra(""+sm.format(rs.getFechaSiembra())+" 00:00:00");
					}catch(Exception e){
						e.printStackTrace();
					}
					
					/*fin proceso de fechas*/
					respuesta.setDiasVigencia(""+dias);
					respuesta.setTramite(""+rs.getTramite());
					respuesta.setSucursal(rs.getSucursal());
					respuesta.setCanal(rs.getCanal());
					respuesta.setCultivoNombre(rs.getTipoCultivoNombre());
					respuesta.setNombres(rs.getNombres());
					respuesta.setApellidos(rs.getApellidos());
					String codigoProvincia=rs.getProvinciaId().toString();
					provincia= provinciaProcesos.buscarPorId(codigoProvincia);
					respuesta.setProvinciaCodigo(provincia.getCodigoSbs());
					respuesta.setProvinciaNombre(rs.getProvinciaNombre());
					String codigoCanton = rs.getCantonId().toString();
					canton= cantonProcesos.buscarPorId(codigoCanton);
					respuesta.setCantonCodigo(""+provincia.getCodigoSbs()+""+canton.getCodigoSbs());
					respuesta.setCantonNombre(rs.getCantonNombre());
					String codigoParroquia=rs.getParroquiaId().toString();
					parroquia= parroquiaProcesos.buscarPorId(codigoParroquia);
					respuesta.setParroquiaCodigo(parroquia.getCodigoSbs());
					respuesta.setParroquiaNombre(rs.getParroquiaNombre());
					respuesta.setDireccion(rs.getDireccionSiembra());
					respuesta.setHectareasAsegurables(""+rs.getHectareasAsegurables());
					
					respuesta.setObservaciones("Todo Correcto");
					arrayRespuesta[contador]=respuesta;
					contador++;
				}
							
				return arrayRespuesta;
			}else {
				respuestaFinal.setObservaciones("Error Token incorrecto: acceso denegado");
				DatosTagh[] arrayRespuesta= new DatosTagh[1];
				arrayRespuesta[0]=respuestaFinal;
				return arrayRespuesta;
			}	
		}catch(Exception e){
			e.printStackTrace();
			respuestaFinal=null;
			respuestaFinal.setObservaciones("Error Inesperado : "+e);
			DatosTagh[] arrayRespuesta= new DatosTagh[1];
			arrayRespuesta[0]=respuestaFinal;
			return arrayRespuesta;
		}
	}
}