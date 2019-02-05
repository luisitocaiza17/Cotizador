package com.qbe.cotizador.servlets.producto.agricola;

import java.math.BigInteger;
import java.util.List;

import com.qbe.cotizador.dao.producto.agricola.AgriCotizacionesDAO;
import com.qbe.cotizador.model.AgriCotizaciones;

public class AgriCotizacionServicio {
	public AgriCotizaciones[] busquedaCotizacionesTodos(){
		AgriCotizacionesDAO cotizaciones = new AgriCotizacionesDAO();
		List<AgriCotizaciones> lasCotizaciones=cotizaciones.BuscarTodos();
		int tama�o=lasCotizaciones.size();
		AgriCotizaciones[] CotizacionesSalida= new AgriCotizaciones[tama�o];
		int contadorObjetos=0;
		for(AgriCotizaciones CotizacionesEntrada : lasCotizaciones){
			CotizacionesSalida[contadorObjetos]=CotizacionesEntrada;
			contadorObjetos++;
		}
		return CotizacionesSalida;
	}
	
	public AgriCotizaciones buscarPorCotizacionId(BigInteger Id){
		AgriCotizacionesDAO cotizaciones = new AgriCotizacionesDAO();
		AgriCotizaciones cotizacion= cotizaciones.BuscarPorCotizacionId(Id);
		return cotizacion;
	}
	
	public AgriCotizaciones[] buscarPorClienteIdentificacion(String identificacion ){
		AgriCotizacionesDAO cotizaciones = new AgriCotizacionesDAO();
		List<AgriCotizaciones> lasCotizaciones=cotizaciones.BuscarPorClienteIdentificacion(identificacion);
		int tama�o=lasCotizaciones.size();
		AgriCotizaciones[] CotizacionesSalida= new AgriCotizaciones[tama�o];
		int contadorObjetos=0;
		for(AgriCotizaciones CotizacionesEntrada : lasCotizaciones){
			CotizacionesSalida[contadorObjetos]=CotizacionesEntrada;
			contadorObjetos++;
		}
		return CotizacionesSalida;
	}
}
