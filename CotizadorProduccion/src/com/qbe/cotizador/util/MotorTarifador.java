package com.qbe.cotizador.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import com.qbe.cotizador.dao.cotizacion.CoberturaDAO;
import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.dao.producto.vehiculo.tarifador.AntiguedadVehiculoDAO;
import com.qbe.cotizador.dao.producto.vehiculo.tarifador.ModeloClasificacionRiesgoDAO;
import com.qbe.cotizador.dao.producto.vehiculo.tarifador.SumaAseguradaDAO;

import com.qbe.cotizador.model.AntiguedadVehiculo;
import com.qbe.cotizador.model.Cobertura;
import com.qbe.cotizador.model.Modelo;
import com.qbe.cotizador.model.ModeloClasificaRiesgo;
import com.qbe.cotizador.model.Sucursal;
import com.qbe.cotizador.model.SumaAsegurada;

public class MotorTarifador{
	
	/**
	 * Método para obtener la Prima de la cobertura de Robo Total
	 * @param sumaAseguradaValor
	 * @param frecuenciaBaseRoboTotal
	 * @param sucursal
	 * @param anioFabricacion
	 * @param dispositivo
	 * @return
	 */
	public static Double calcularPrimaRoboTotal(Double sumaAseguradaValor, Sucursal sucursal, int anioFabricacion, int dispositivo, Modelo modelo){
		
		// Obtenemos la lista de los rangos de la suma asegurada y verificamos el indice de robo total al que pertenece la suma aseguradora
		Double tipoRiegoRoboTotal = 0.0;
		SumaAseguradaDAO sumaAseguradaDAO = new SumaAseguradaDAO();
		List<SumaAsegurada>sumaAseguradaLista = sumaAseguradaDAO.buscarTodos();
		
		//Verificamos si el vehiculo tiene riesgo, sino tiene se pone el valor del rango de la suma asegurada
		ModeloClasificacionRiesgoDAO modeloClasificaRiesgoDAO = new ModeloClasificacionRiesgoDAO();
		CoberturaDAO coberturaDAO = new CoberturaDAO();
		Cobertura cobertura = coberturaDAO.buscarPorNemotecnico("ROTO");		
		ModeloClasificaRiesgo modeloClasificaRiesgo =  modeloClasificaRiesgoDAO.buscarPorClasificacionRiesgoPorModelo(modelo, cobertura);
		
		if(modeloClasificaRiesgo.getId() == null){
			for(int i=0;i<sumaAseguradaLista.size();i++){
				SumaAsegurada sumaAsegurada = sumaAseguradaLista.get(i);
				Double indiceSumaAseguradaDesdeTemporal = Double.parseDouble(sumaAseguradaLista.get(i).getDesde());
				Double indiceSumaAseguradaHastaTemporal = Double.parseDouble(sumaAseguradaLista.get(i).getHasta());
				if(sumaAseguradaValor >= indiceSumaAseguradaDesdeTemporal  && sumaAseguradaValor <= indiceSumaAseguradaHastaTemporal){
					tipoRiegoRoboTotal = Double.parseDouble(sumaAsegurada.getIndiceRoboTotal());
				}			
			}
		}else{
			tipoRiegoRoboTotal = Double.parseDouble(modeloClasificaRiesgo.getClasificacionRiesgo().getIndiceAsociado());
		}
		
		// Obtenemos la lista de los rangos de la antiguedad del vehiculo y verificamos el indice de robo total al que pertenece la antiguedad
		AntiguedadVehiculoDAO antiguedadVehiculoDAO = new AntiguedadVehiculoDAO();
		List<AntiguedadVehiculo> antiguedadVHLista = antiguedadVehiculoDAO.buscarTodos();		
		Double indiceAntiguedadVehiculoRiegoRoboTotal = 0.0;		
		int anioActual = Calendar.getInstance().get(Calendar.YEAR);
		int aniosVehiculo = anioActual - anioFabricacion;
		if (aniosVehiculo < 0) aniosVehiculo = 0;
		for(int i=0;i<antiguedadVHLista.size();i++){
			AntiguedadVehiculo antiguedadVH = antiguedadVHLista.get(i);
			int indiceAntiguedadVHDesdeTemporal = Integer.parseInt(antiguedadVHLista.get(i).getDesde());
			int indiceAntiguedadVHHastaTemporal = Integer.parseInt(antiguedadVHLista.get(i).getHasta());
			if(aniosVehiculo >= indiceAntiguedadVHDesdeTemporal  && aniosVehiculo <= indiceAntiguedadVHHastaTemporal){
				indiceAntiguedadVehiculoRiegoRoboTotal = Double.parseDouble(antiguedadVH.getIndiceRoboTotal());
			}			
		}
		List<String> listadoVariables = new ArrayList<String>();
		listadoVariables.add("DEDUCIBLE_ROBO_TOTAL_CON_DISPOSITIVO");
		listadoVariables.add("DEDUCIBLE_ROBO_TOTAL_SIN_DISPOSITIVO");
		listadoVariables.add("ROBO_TOTAL_FRECUENCIA_BASE");
		
		VariableSistemaDAO variableDAO = new VariableSistemaDAO();
		List<String> listadoValores = variableDAO.buscarPorNombres(listadoVariables); 
		
		Double frecuenciaBaseRoboTotal = Double.parseDouble(listadoValores.get(2).toString()) * 100;
		// Frecuencia Robo Total = Frecuencia base RT * Tipo de Riego Robo Total * Antiguedad Vehiculo RiegoRoboTotal * Valor indice Robo Total Sucursal
		Double frecuenciaRoboTotal = Math.rint((frecuenciaBaseRoboTotal * tipoRiegoRoboTotal * indiceAntiguedadVehiculoRiegoRoboTotal* Double.parseDouble(sucursal.getIndiceRoboTotal()))*100)/100;
		
		// Deducible RT: Valor del VH tiene dispositivo o no. Se toman los valores de las variables del sistema
		Double deducibleRoboTotal = 0.0;							
		if(dispositivo == 0){
			deducibleRoboTotal = Double.parseDouble(listadoValores.get(1).toString());
		}else{
			deducibleRoboTotal = Double.parseDouble(listadoValores.get(0).toString());
		}			
		
		// Severidad Neta Robo Total = Suma Asegurada * (1-Deducible Robo Total)*0,9
		Double SeveridadNetaRoboTotal = Math.rint((sumaAseguradaValor * (1-deducibleRoboTotal)*0.9)*100)/100;
		
		// Prima Pura Robo Total = Severidad Neta Robo Total * Frecuencia Robo Total		
		Double primaPuraRoboTotal = Math.rint((SeveridadNetaRoboTotal * (frecuenciaRoboTotal/100))*100)/100;
		
		return primaPuraRoboTotal;
		
	}
	
	public static Double calcularPrimaChoqueTotal(Double sumaAseguradaValor, Sucursal sucursal, Modelo modelo){
		
		// Obtenemos la lista de los rangos de la suma asegurada y verificamos el indice de choque total al que pertenece la suma aseguradora
		Double tipoRiegoChoqueTotal = 0.0;
		SumaAseguradaDAO sumaAseguradaDAO = new SumaAseguradaDAO();
		List<SumaAsegurada>sumaAseguradaLista = sumaAseguradaDAO.buscarTodos();
		
		//Verificamos si el vehiculo tiene riesgo, sino tiene se pone el valor del rango de la suma asegurada
		ModeloClasificacionRiesgoDAO modeloClasificaRiesgoDAO = new ModeloClasificacionRiesgoDAO();
		CoberturaDAO coberturaDAO = new CoberturaDAO();
		Cobertura cobertura = coberturaDAO.buscarPorNemotecnico("CHTO");		
		ModeloClasificaRiesgo modeloClasificaRiesgo =  modeloClasificaRiesgoDAO.buscarPorClasificacionRiesgoPorModelo(modelo, cobertura);
		
		if(modeloClasificaRiesgo.getId() == null){
			for(int i=0;i<sumaAseguradaLista.size();i++){
				SumaAsegurada sumaAsegurada = sumaAseguradaLista.get(i);
				Double indiceSumaAseguradaDesdeTemporal = Double.parseDouble(sumaAseguradaLista.get(i).getDesde());
				Double indiceSumaAseguradaHastaTemporal = Double.parseDouble(sumaAseguradaLista.get(i).getHasta());
				if(sumaAseguradaValor >= indiceSumaAseguradaDesdeTemporal  && sumaAseguradaValor <= indiceSumaAseguradaHastaTemporal){
					tipoRiegoChoqueTotal = Double.parseDouble(sumaAsegurada.getIndiceChoqueTotal());
				}			
			}
		}else{
			tipoRiegoChoqueTotal = Double.parseDouble(modeloClasificaRiesgo.getClasificacionRiesgo().getIndiceAsociado());
		}			
		
		List<String> listadoVariables = new ArrayList<String>();		
		listadoVariables.add("CHOQUE_TOTAL_FRECUENCIA_BASE");
		
		VariableSistemaDAO variableDAO = new VariableSistemaDAO();
		List<String> listadoValores = variableDAO.buscarPorNombres(listadoVariables); 
		
		Double frecuenciaBaseChoqueTotal = Double.parseDouble(listadoValores.get(0).toString()) * 100;
		
		// Frecuencia Choque Total = Frecuencia base CHT * Tipo de Riego Choque Total *  Valor indice Choque Total Sucursal
		Double frecuenciaChoqueTotal = Math.rint((frecuenciaBaseChoqueTotal * tipoRiegoChoqueTotal * Double.parseDouble(sucursal.getIndiceChoqueTotal()))*100)/100 ;
		
		//Severidad Neta Choque Total = Suma Asegurada * 0,595
		Double severidadNetaChoqueTotal = sumaAseguradaValor * 0.595 ;
		
		//Prima Pura Choque Total = Severidad Neta Choque Total * Frecuencia Choque Total
		Double primaPuraChoqueTotal = Math.rint((severidadNetaChoqueTotal * (frecuenciaChoqueTotal/100))*100)/100 ;
		
		return primaPuraChoqueTotal;
	}
	
public static Double calcularPrimaResponsabilidadCivil(Double sumaAseguradaValor, Sucursal sucursal, int anioFabricacion, Modelo modelo){
		
		// Obtenemos la lista de los rangos de la suma asegurada y verificamos el indice de responsabilidad civil al que pertenece la suma aseguradora
		Double tipoRiegoFrecuenciaRC = 0.0;
		Double tipoRiegoSeveridadRC = 0.0;
		
		SumaAseguradaDAO sumaAseguradaDAO = new SumaAseguradaDAO();
		List<SumaAsegurada>sumaAseguradaLista = sumaAseguradaDAO.buscarTodos();
					
		for(int i=0;i<sumaAseguradaLista.size();i++){
				SumaAsegurada sumaAsegurada = sumaAseguradaLista.get(i);
				Double indiceSumaAseguradaDesdeTemporal = Double.parseDouble(sumaAseguradaLista.get(i).getDesde());
				Double indiceSumaAseguradaHastaTemporal = Double.parseDouble(sumaAseguradaLista.get(i).getHasta());
			if(sumaAseguradaValor >= indiceSumaAseguradaDesdeTemporal  && sumaAseguradaValor <= indiceSumaAseguradaHastaTemporal){
				tipoRiegoFrecuenciaRC = Double.parseDouble(sumaAsegurada.getIndiceResponsabilidadCivilFrecuencia());
				tipoRiegoSeveridadRC = Double.parseDouble(sumaAsegurada.getIndiceResponsabilidadCivilSeveridad());
			}			
		}
		
		// Obtenemos la lista de los rangos de la antiguedad del vehiculo y verificamos el indice de robo total al que pertenece la antiguedad
		AntiguedadVehiculoDAO antiguedadVehiculoDAO = new AntiguedadVehiculoDAO();
		List<AntiguedadVehiculo> antiguedadVHLista = antiguedadVehiculoDAO.buscarTodos();		
		Double indiceAntiguedadVehiculoRiegoRC = 0.0;		
		int anioActual = Calendar.getInstance().get(Calendar.YEAR);
		int aniosVehiculo = anioActual - anioFabricacion;
		if (aniosVehiculo < 0) aniosVehiculo = 0;
		for(int i=0;i<antiguedadVHLista.size();i++){
			AntiguedadVehiculo antiguedadVH = antiguedadVHLista.get(i);
			int indiceAntiguedadVHDesdeTemporal = Integer.parseInt(antiguedadVHLista.get(i).getDesde());
			int indiceAntiguedadVHHastaTemporal = Integer.parseInt(antiguedadVHLista.get(i).getHasta());
			if(aniosVehiculo >= indiceAntiguedadVHDesdeTemporal  && aniosVehiculo <= indiceAntiguedadVHHastaTemporal){
				indiceAntiguedadVehiculoRiegoRC = Double.parseDouble(antiguedadVH.getIndiceResponsabilidadCivilFrecuencia());
			}			
		}
		
		List<String> listadoVariables = new ArrayList<String>();		
		listadoVariables.add("RC_FRECUENCIA_BASE");
		listadoVariables.add("RC_SEVERIDAD_BASE");
		
		VariableSistemaDAO variableDAO = new VariableSistemaDAO();
		List<String> listadoValores = variableDAO.buscarPorNombres(listadoVariables); 
				
		// Frecuencia Responsabilidad Civil = Frecuencia base RC * Tipo de Riego Responsabilidad Civil (suma asegurada) * Antiguedad Vehiculo RiegoResponsabilidadCivil * Valor indice Responsabilidad Civil Sucursal
		Double frecuenciaBaseRC = Math.rint((Double.parseDouble(listadoValores.get(0).toString()) * 100)*100)/100;
		Double frecuenciaResponsabilidadCivil = Math.rint((frecuenciaBaseRC * tipoRiegoFrecuenciaRC * indiceAntiguedadVehiculoRiegoRC * Double.parseDouble(sucursal.getIndiceResponsabilidadCivilFrecuencia()))*100)/100;
		
		// Severidad Responsabilidad Civil = Severidad base RC * Tipo de Riesgo RC (Suma Asegurada) * Valor indice RC Sucursal
		Double severidadBaseRC = Math.rint((Double.parseDouble(listadoValores.get(1).toString()))*100)/100;
		Double severidadResponsabilidadCivil = Math.rint((severidadBaseRC * tipoRiegoSeveridadRC * Double.parseDouble(sucursal.getIndiceResponsabilidadCivilSeveridad()))*100)/100 ;
		
		// Prima Pura Responsabilidad Civil = Severidad Responsabilidad Civil * Frecuencia Responsabilidad Civil
		Double  primaPuraRC = Math.rint((severidadResponsabilidadCivil * (frecuenciaResponsabilidadCivil/100))*100)/100;
		return primaPuraRC;
	}
	
	public static Double calcularPrimaDanoParcial(Double sumaAseguradaValor, Sucursal sucursal, int anioFabricacion, Modelo modelo,Double montoFijo,Double porcentajeSiniestro,Double porcentajeSumaAsegurada){
	
	SumaAseguradaDAO sumaAseguradaDAO = new SumaAseguradaDAO();
	List<SumaAsegurada>sumaAseguradaLista = sumaAseguradaDAO.buscarTodos();				
	
	List<String> listadoVariables = new ArrayList<String>();		
	listadoVariables.add("DANO_PARCIAL_FRECUENCIA_BASE");
	listadoVariables.add("DANO_PARCIAL_SEVERIDAD_BASE");
	
	VariableSistemaDAO variableDAO = new VariableSistemaDAO();
	List<String> listadoValores = variableDAO.buscarPorNombres(listadoVariables); 
		
	Double frecuenciaBaseDanoParcial = Double.parseDouble(listadoValores.get(0).toString()) * 100;
	Double severidadBaseDanoParcial = Double.parseDouble(listadoValores.get(1).toString());
	
	// Obtenemos la lista de los rangos de la antiguedad del vehiculo y verificamos el indice de dano parcial al que pertenece la antiguedad
    AntiguedadVehiculoDAO antiguedadVehiculoDAO = new AntiguedadVehiculoDAO();
	List<AntiguedadVehiculo> antiguedadVHLista = antiguedadVehiculoDAO.buscarTodos();
	
	Double indiceAntiguedadVehiculoRiegoDanoParcialFrecuencia = 0.0;
	Double indiceAntiguedadVehiculoRiegoDanoParcialSeveridad = 0.0;
	
	int anioActual = Calendar.getInstance().get(Calendar.YEAR);
	int aniosVehiculo = anioActual - anioFabricacion;
	if (aniosVehiculo < 0) aniosVehiculo = 0;
	for(int i=0;i<antiguedadVHLista.size();i++){
		AntiguedadVehiculo antiguedadVH = antiguedadVHLista.get(i);
		int indiceAntiguedadVHDesdeTemporal = Integer.parseInt(antiguedadVHLista.get(i).getDesde());
		int indiceAntiguedadVHHastaTemporal = Integer.parseInt(antiguedadVHLista.get(i).getHasta());
		if(aniosVehiculo >= indiceAntiguedadVHDesdeTemporal  && aniosVehiculo <= indiceAntiguedadVHHastaTemporal){
			indiceAntiguedadVehiculoRiegoDanoParcialFrecuencia = Double.parseDouble(antiguedadVH.getIndiceDanoParcialFrecuencia());
			indiceAntiguedadVehiculoRiegoDanoParcialSeveridad = Double.parseDouble(antiguedadVH.getIndiceDanoParcialSeveridad());
		}			
	}
	
	// Frecuencia Daño Parcial = Frecuencia base DP * tipo riesgo frecuencia DP (suma asegurada) * Valor indice DP Frecuencia Sucursal * Indide Dano Parcial Frecuencia del modelo
	Double frecuenciaDanoParcial = frecuenciaBaseDanoParcial * indiceAntiguedadVehiculoRiegoDanoParcialFrecuencia * Double.parseDouble(sucursal.getIndiceDanoParcialFrecuencia())* Double.parseDouble(modelo.getIndiceDanoParcialFrecuencia()); 
	ModeloClasificacionRiesgoDAO modeloClasificaRiesgoDAO = new ModeloClasificacionRiesgoDAO();
	CoberturaDAO coberturaDAO = new CoberturaDAO();
	Cobertura cobertura = coberturaDAO.buscarPorNemotecnico("DAPA");		
	ModeloClasificaRiesgo modeloClasificaRiesgo =  modeloClasificaRiesgoDAO.buscarPorClasificacionRiesgoPorModelo(modelo, cobertura);
	
	// Obtenemos la lista de los rangos de la suma asegurada y verificamos el indice de dano parcial al que pertenece la suma aseguradora
	Double tipoRiegoSeveridadDP = 0.0;
		
	if(modeloClasificaRiesgo.getId() == null){
		for(int i=0;i<sumaAseguradaLista.size();i++){
			SumaAsegurada sumaAsegurada = sumaAseguradaLista.get(i);
			Double indiceSumaAseguradaDesdeTemporal = Double.parseDouble(sumaAseguradaLista.get(i).getDesde());
			Double indiceSumaAseguradaHastaTemporal = Double.parseDouble(sumaAseguradaLista.get(i).getHasta());
			if(sumaAseguradaValor >= indiceSumaAseguradaDesdeTemporal  && sumaAseguradaValor <= indiceSumaAseguradaHastaTemporal){
				tipoRiegoSeveridadDP = Double.parseDouble(sumaAsegurada.getIndiceDanoParcialSeveridad());
				// Si el vehículo no tiene Severidad DP (SEVERIDAD LEVE, SEVERIDAD MEDIA, SEVERIDAD ALTA) la severidad base se pone el valor de la suma asugurada 
				severidadBaseDanoParcial = sumaAseguradaValor; 
			}			
		}
	}else{
		tipoRiegoSeveridadDP = Double.parseDouble(modeloClasificaRiesgo.getClasificacionRiesgo().getIndiceAsociado());
	}		

	// Severidad Daño Parcial = Severidad base DP * tipo riesgo severidad DP (suma asegurada) * V2 * Sucursal	
	Double severidadDanoParcial = severidadBaseDanoParcial * indiceAntiguedadVehiculoRiegoDanoParcialSeveridad * Double.parseDouble(sucursal.getIndiceDanoParcialSeveridad())* tipoRiegoSeveridadDP;
		
	// Deducible Daño Parcial = Máximo valor entre (Deducible Daños Parciales - Monto Fijo ; Deducible Daños Parciales - % Siniestro * Severidad Daño Parcial ; Deducible Daños Parciales - % Suma Asegurada * Suma Asegurada)
	Double deducibleDanoParcialMontoFijo =  montoFijo;
	Double deducibleDanoParcialPorcentajeSiniestro =  (porcentajeSiniestro/100) * severidadDanoParcial;
	Double deducibleDanoParcialPorcentajeSumaAsegurada = (porcentajeSumaAsegurada/100) * sumaAseguradaValor;
	
	List<Double> deducibles = new ArrayList<Double>();
	deducibles.add(deducibleDanoParcialMontoFijo);
	deducibles.add(deducibleDanoParcialPorcentajeSiniestro);
	deducibles.add(deducibleDanoParcialPorcentajeSumaAsegurada);

    Double deducibleDanoParcial = Collections.max(deducibles);
    
    Double severidadNetaDanoParcial = (severidadDanoParcial - deducibleDanoParcial)* 0.9;
    
    //Prima Pura Daño Parcial = Frecuencia Daño Parcial * Severidad Neta daño Parcial
    //Double primaPuraDanoParcial = (frecuenciaDanoParcial/100) * severidadNetaDanoParcial;
    Double  primaPuraDanoParcial = Math.rint((frecuenciaDanoParcial/100) * severidadNetaDanoParcial*100)/100;
	//Prima Pura Daño Parcial con RASA = Prima Pura Daño Parcial / ( 1 + Frecuencia Daño Parcial * Severidad Neta daño Parcial / Suma Asegurada)
	//Double primaPuraDanoParcialConRASA = primaPuraDanoParcial / (1+frecuenciaDanoParcial*severidadNetaDanoParcial/sumaAseguradaValor); 
	
	return primaPuraDanoParcial;
	}
	
	/**
	 * Método para obtener la prima y la tasa de dano total
	 * @param primaPuraRoboTotal
	 * @param primaPuraChoqueTotal
	 * @param comisionAgente
	 * @param sumaAsegurada
	 * @return
	 */ 
	public static Double calcularPrimaTasaDanoTotal(Double primaPuraRoboTotal, Double primaPuraChoqueTotal, Double comisionAgente, Double sumaAsegurada){
		Double primaTarifaSinImpuestos = (primaPuraRoboTotal + primaPuraChoqueTotal) / (0.8 - (comisionAgente/100)); 		
		primaTarifaSinImpuestos = primaTarifaSinImpuestos - (primaTarifaSinImpuestos * 0.0117);
		return primaTarifaSinImpuestos;		
	}

	/**
	 * Método para obtener la prima y la tasa de responsabilidad civil
	 * @param primaPuraRC
	 * @param comisionAgente
	 * @param sumaAsegurada
	 * @return
	 */
	public static Double calcularPrimaTasaResponsabilidadCivil(Double primaPuraRC, Double comisionAgente, Double sumaAsegurada){
		Double primaTarifaSinImpuestos = primaPuraRC / (0.8 - (comisionAgente/100));
		primaTarifaSinImpuestos = primaTarifaSinImpuestos - (primaTarifaSinImpuestos * 0.0117);
		return primaTarifaSinImpuestos;		
	}
	
	/**
	 * Método para obtener la prima y la tasa de todo riesgo
	 * @param primaPuraRoboTotal
	 * @param primaPuraChoqueTotal
	 * @param primaPuraRC
	 * @param primaPuraDanoParcialConRasa
	 * @param comisionAgente
	 * @param sumaAsegurada
	 * @return
	 */
	public static Double calcularPrimaTasaTodoRiesgo(Double primaPuraRoboTotal, Double primaPuraChoqueTotal, Double primaPuraRC, Double primaPuraDanoParcialConRasa, Double comisionAgente, Double sumaAsegurada){
		Double primaTarifaSinImpuestos = (primaPuraRoboTotal+primaPuraChoqueTotal+primaPuraRC+primaPuraDanoParcialConRasa) / (0.8 - (comisionAgente/100));
		primaTarifaSinImpuestos = primaTarifaSinImpuestos - (primaTarifaSinImpuestos * 0.0117);  
		return primaTarifaSinImpuestos;		
	}

}

