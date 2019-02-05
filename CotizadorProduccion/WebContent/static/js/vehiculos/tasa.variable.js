/* Archivo para realizar las validaciones de los productos cerrados que tiene varias tasas */
function metodoValidacionTasas(tasaVariableId,numeroVehiculos){
	
	var listadoVariables = "";
	// Verificacion de tasa variable 
	if(tasaVariableId!=""){		
						
		$.ajax({
			url : '../GrupoPorProductoController',
			data : {
				"tipoConsulta" : "encontrarInformacionTasaProducto",
				"tasaProducto" : tasaVariableId,
			},
			type : 'post',
			datatype : 'json',
			success : function (data) {				
				
				listadoVariables+='<input type="text" id="porcentajeCasco" name="porcentajeCasco">';
				listadoVariables+='<input type="text" id="porcentajeExtras" name="porcentajeExtras">';
				
				listadoVariables+='<input type="text" id="tieneSumaAsegurada" name="tieneSumaAsegurada">';
				listadoVariables+='<input type="text" id="sumaAseguradaInicio" name="sumaAseguradaInicio" >';
				listadoVariables+='<input type="text" id="sumaAseguradaFin" name="sumaAseguradaFin">';
				
				listadoVariables+='<input type="text" id="tieneAntiguedad" name="tieneAntiguedad">';
				listadoVariables+='<input type="text" id="antiguedadInicio" name="antiguedadInicio" >';
				listadoVariables+='<input type="text" id="antiguedadFin" name="antiguedadFin">';
												
				listadoVariables+='<input type="text" id="tieneCargaPasajeros" name="tieneCargaPasajeros">';
				listadoVariables+='<input type="text" id="cargaPasajerosValor" name="cargaPasajerosValor" >';
								
				$("#listado_formulado").html(listadoVariables);
				
				$("#tieneSumaAsegurada").val(data.tasaProducto.tieneSumaAsegurada);
				$("#sumaAseguradaInicio").val(data.tasaProducto.sumaAseguradaInicio);
				$("#sumaAseguradaFin").val(data.tasaProducto.sumaAseguradaFin);
				
				$("#tieneAntiguedad").val(data.tasaProducto.tieneAntiguedadVh);
				$("#antiguedadInicio").val(data.tasaProducto.antiguedadInicio);
				$("#antiguedadFin").val(data.tasaProducto.antiguedadFin);				
				
				$("#tieneCargaPasajeros").val(data.tasaProducto.tieneCargaPasajeros);
				$("#cargaPasajerosValor").val(data.tasaProducto.cargaPasajerosValor);
				
				// Validacion que actualiza el combo de tipo vehiculo (carga y pasajeros)
				if(data.tasaProducto.tieneCargaPasajeros =="SI"){				
					$.each($("#tipo_vehiculo"+numeroVehiculos),function(index){
							var tipoVehiculo = $(this).val();
							cargarTipoVehiculo(tipoVehiculo, index,tasaVariableId);						
					});
				}
				
			}
		});
	}
}

function validacionInformacionTasaProducto(numero){
			
	var tieneAntiguedad = $("#tieneAntiguedad").val();
	var antiguedadInicio = $("#antiguedadInicio").val();
	var antiguedadFin = $("#antiguedadFin").val();
	var anoFabricacion = $('#anio_fabricacion'+numero).val();		
	var fechaActual = new Date();
	var anoActual = fechaActual.getFullYear();
	var antiguedadvalor = Number(anoActual)-Number(anoFabricacion);
		
	var tieneSumaAsegurada = $("#tieneSumaAsegurada").val();
	var sumaAseguradaInicio = $("#sumaAseguradaInicio").val();
	var sumaAseguradaFin =$("#sumaAseguradaFin").val();
	var sumaAseguradaValor = $('#suma_asegurada_'+numero).val();
	
	var retornoInformacion = new Array();
			
	var errores ="El veh\xedculo ("+numero+") tiene los siguientes problemas: \n";
						
	var tieneError = "0";
					
	if(tieneAntiguedad =="Si" && antiguedadvalor > 0){
		if(antiguedadvalor< Number(antiguedadInicio) ||antiguedadvalor > Number(antiguedadFin)){
			errores+=" - Antiguedad del veh\xedculo debe ser hasta "+antiguedadFin +" a\xF1os. \n";
			tieneError = "1";
		}	
	}
	if(tieneSumaAsegurada =="Si"){
		if(sumaAseguradaValor< Number(sumaAseguradaInicio) ||sumaAseguradaValor > Number(sumaAseguradaFin)){
			errores+=" - Suma Asegurada debe estar entre "+sumaAseguradaInicio +" hasta "+sumaAseguradaFin+" \n";
			tieneError = "1";
		}	
	}
		
	if(tieneError == "1"){
		retornoInformacion.push("ERROR");
		retornoInformacion.push(errores);				
	}else{
		retornoInformacion.push("OK");	
	}
	return retornoInformacion; 
}