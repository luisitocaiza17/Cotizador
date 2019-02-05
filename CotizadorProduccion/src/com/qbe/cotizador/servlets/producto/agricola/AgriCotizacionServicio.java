package com.qbe.cotizador.servlets.producto.agricola;

import java.math.BigInteger;
import java.util.List;

import com.qbe.cotizador.dao.producto.agricola.AgriCotizacionesDAO;
import com.qbe.cotizador.model.AgriCotizaciones;

public class AgriCotizacionServicio {
	public AgriCotizaciones[] busquedaCotizacionesTodos(){
		AgriCotizacionesDAO cotizaciones = new AgriCotizacionesDAO();
		List<AgriCotizaciones> lasCotizaciones=cotizaciones.BuscarTodos();
		int tamaño=lasCotizaciones.size();
		AgriCotizaciones[] CotizacionesSalida= new AgriCotizaciones[tamaño];
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
		int tamaño=lasCotizaciones.size();
		AgriCotizaciones[] CotizacionesSalida= new AgriCotizaciones[tamaño];
		int contadorObjetos=0;
		for(AgriCotizaciones CotizacionesEntrada : lasCotizaciones){
			CotizacionesSalida[contadorObjetos]=CotizacionesEntrada;
			contadorObjetos++;
		}
		return CotizacionesSalida;
	}
}
