package com.qbe.cotizador.servicios.QBE.ganadero;

//import sun.security.util.BigInt;

public class CotizacionGanderoQBE {
	
	//Metodo que genera una sola cotizacion
	public ResultadoOBJ  generarCotizacion(CotizacionOBJ nuevaCotizacion)
	{
		return new ResultadoOBJ();
	}
	
	//Metodo que genera una o varias cotizaciones
	public ResultadoOBJ[]  generarVariasCotizacion(CotizacionOBJ[] nuevasCotizaciones)
	{
		ResultadoOBJ[] result=new ResultadoOBJ[1];
		result[0]=new ResultadoOBJ();
		return result;
	}
	
	public ConsultaOBJ consultarEstadoCotizacion(int NumeroCotizacion, String numeroCotizacionOrigen)
	{
		return new ConsultaOBJ();
	}
	
	public ConsultaOBJ[] consultarEstadoCotizacion2(int[] NumeroCotizacion)
	{
		ConsultaOBJ[] result=new ConsultaOBJ[1];
		result[0]=new ConsultaOBJ();
		return result;
	}
	
	//Metodo para obtener el listado de Tipos de Ganado Vacuno
	public TipoGanadoVacuno[] obtenerTiposGanadoVacuno()
	{
		TipoGanadoVacuno[] result=new TipoGanadoVacuno[1];
		result[0]=new TipoGanadoVacuno();
		return result;
	}
	
	//Metodo para obtener el listado de Razas
	public Razas[] obtenerRazas()
	{
		Razas[] result=new Razas[1];
		result[0]=new Razas();
		return result;
	}
	
	//Metodo para obtener el listado de Provincias
	public Provincias[] obtenerProvincias()
	{
		Provincias[] result=new Provincias[1];
		result[0]=new Provincias();
		return result;
	}
	
	//Metodo para obtener el listado de ciudades de una provincia
	public Ciudades[] obtenerCiudadesPorProvincia(String provinciaId)
	{
		Ciudades[] result=new Ciudades[1];
		result[0]=new Ciudades();
		return result;
	}
	
	//Metodo para obtener el listado de parroquias de una ciudad
	public Parroquias[] obtenerParroquiasPorCiudad(String ciudadId)
	{
		Parroquias[] result=new Parroquias[1];
		result[0]=new Parroquias();
		return result;
	}
}
