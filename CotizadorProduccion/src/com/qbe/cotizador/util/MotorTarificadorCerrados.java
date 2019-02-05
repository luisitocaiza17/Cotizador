package com.qbe.cotizador.util;

import java.util.HashMap;
import java.util.Map;

import com.qbe.cotizador.model.TasaProducto;



public class MotorTarificadorCerrados {
	
	/**
	 * Metodo para obtener la prima de un vehiculo cerrado 
	 * @param tasaProducto
	 * @param antiguedadVehiculo
	 * @param sumaAsegurada
	 * @param dispositivoRastreo
	 * @param valorExtras
	 * @return
	 */
	public static Map<String, String> calcularPrimaCerrados(TasaProducto tasaProducto,Integer antiguedadVehiculo,Double sumaAsegurada,Boolean dispositivoRastreo, Double valorExtras,String tipoVehiculo,Double tonelaje,String region,
			Double deducible_porcentaje_siniestro,Double deducible_porcentaje_valor_asegurado,Double deducible_minimo,Double deducible_perdida_total_siniestro,int ano_fabricacion, int edad_conductor,String marcas){
	
		final Map<String, String> tarificadorResultados = new HashMap<String, String>();
		
// 		if(tasaProducto.getTieneAntiguedadVh()){
//			// Verificamos que la antiguedad del vehiculo este dentro del rango
//			if(antiguedadVehiculo < Integer.parseInt(String.valueOf(tasaProducto.getAntiguedadInicio())) || antiguedadVehiculo > Integer.parseInt(String.valueOf(tasaProducto.getAntiguedadFin()))){
//				tarificadorResultados.put("Error", "antiguedad del vehiculo");
//			}			
//		}
//		if(tasaProducto.getTieneSumaAsegurada()){
//			// Verificamos que la suma asegurada del vehiculo este dentro del rango
//			if(sumaAsegurada < Integer.parseInt(String.valueOf(tasaProducto.getSumaAseguradaInicio())) || sumaAsegurada >Integer.parseInt(String.valueOf(tasaProducto.getSumaAseguradaFin()))){
//				tarificadorResultados.put("Error", "suma asegurada");
//			}
//		}		
//		if(tasaProducto.getTieneDispositivoRastreo()){
//			// Verificamos que el vehiculo tenga la validacion del dispositivo de rastreo
//			if(dispositivoRastreo != tasaProducto.getTieneDispositivoRastreo()){
//				tarificadorResultados.put("Error", "dispositivo de rastreo");
//			}
//		}			
//		if(tasaProducto.getTieneTipoVehiculo()){
//			// Verificamos que el vehiculo tenga tipo de vehiculo			
//			String[] tipos_vehiculos = tasaProducto.getTipoVehiculoNombre().split(";");
//			int contador = 0;
//			for(String tipo: tipos_vehiculos){
//				if(!tipo.equalsIgnoreCase(tipoVehiculo)){
//					contador++;
//				}
//			}			
//			if(contador >0){
//				tarificadorResultados.put("Error", "tipo de vehiculo");
//			}
//		}
//		
//		if(tasaProducto.getTieneTonelaje()){
//			// Verificamos que el tonelaje del vehiculo ingresado en el producto
//			if(tonelaje < tasaProducto.getValorTonelajeInicio() || tonelaje > tasaProducto.getValorTonelajeFin()){			
//				tarificadorResultados.put("Error", "tonelaje");
//			}
//		}				
//		
//		if(tasaProducto.getTieneRegion()){
//			// Verificamos que la region del vehiculo ingresado en el producto
//			String[] tiene_region = tasaProducto.getValorRegion().split(";");
//			int contador = 0;
//			for(String region_valor: tiene_region){
//				if(!region_valor.equalsIgnoreCase(region)){
//					contador++;
//				}
//			}			
//			if(contador >0){
//				tarificadorResultados.put("Error", "region");
//			}			
//		}
//		
//		if(tasaProducto.getTieneDeducible()){
//			// Verificamos el porcentaje de siniestro del deducible ingresado este dentro del rango del producto configurado
//			if(deducible_porcentaje_siniestro < tasaProducto.getDeduciblePorcentajeSiniestro()){
//				tarificadorResultados.put("Error", "porcentaje del deducible del siniestro ");
//			}
//			// Verificamos el valor asegurado del deducible ingresado este dentro del rango del producto configurado
//			if(deducible_porcentaje_valor_asegurado < tasaProducto.getDeduciblePorcentajeValorAsegurado()){
//				tarificadorResultados.put("Error", "porcentaje del valor asegurado");
//			}
//			
//			// Verificamos el deducible minimo  ingresado este dentro del rango del producto configurado
//			if(deducible_minimo < tasaProducto.getDeducibleMinimo()){
//				tarificadorResultados.put("Error", "deducible minimo ");
//			}
//			
//			// Verificamos el deducible minimo  ingresado este dentro del rango del producto configurado
//			if(deducible_minimo < tasaProducto.getDeducibleMinimo()){
//				tarificadorResultados.put("Error", "deducible minimo ");
//			}
//			
//			// Verificamos el deducible del siniestro perdida total ingresado este dentro del rango del producto configurado
//			if(tasaProducto.getTieneDeduciblePerdidaTotalSiniestro()){
//				if(deducible_perdida_total_siniestro < tasaProducto.getDeduciblePerdidaTotalSiniestro()){
//					tarificadorResultados.put("Error", "deducible de perdida total del siniestro");
//				}
//			}
//		}
//		
//		if(tasaProducto.getTieneAnoFabricacion()){
//			// Verificamos que el anio de fabricacion del vehiculo este dentro del rango
//			if(ano_fabricacion < tasaProducto.getAnoFabricacionInicio() || ano_fabricacion >tasaProducto.getAnoFabricacionFin()){
//				tarificadorResultados.put("Error", "ano de fabricacion");
//			}
//		}
//		
//		if(tasaProducto.getTieneEdadConductor()){
//			// Verificamos que la edad del conductor este dentro del rango
//			if(edad_conductor < tasaProducto.getEdadConductorInicio() || ano_fabricacion >tasaProducto.getAnoFabricacionFin()){
//				tarificadorResultados.put("Error", "edad del conductor");
//			}
//		}
//		
//		
		
		if(tarificadorResultados.size()==0){									
			tarificadorResultados.put("porcentaje_casco", String.valueOf(tasaProducto.getPorcentajeCasco()));
			tarificadorResultados.put("porcentaje_extras", String.valueOf(tasaProducto.getPorcentajeExtras()));
		}
		return tarificadorResultados;
	}

}
