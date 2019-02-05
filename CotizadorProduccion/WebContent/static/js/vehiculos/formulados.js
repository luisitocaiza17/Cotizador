/* Archivo para poner la codificacion de los productos formulados. Configurados en GrupoPorProductos */
function metodoObtenerProductoFormulados(grupoPorProductoId){
	
	var listadoVariables = "";
	// Productos Formulados
	if(grupoPorProductoId=="100"){		
						
		$.ajax({
			url : '../GrupoPorProductoController',
			data : {
				"tipoConsulta" : "encontrarPorId",
				"grupoPorProductoId" : grupoPorProductoId,
			},
			type : 'post',
			datatype : 'json',
			success : function (data) {
				listadoVariables+='<input type="text" id="productoFormulado" name="productoFormulado">';
				listadoVariables+='<input type="text" id="tieneAntiguedad" name="tieneAntiguedad">';
				listadoVariables+='<input type="text" id="antiguedadInicio" name="antiguedadInicio" >';
				listadoVariables+='<input type="text" id="antiguedadFin" name="antiguedadFin">';
				
				listadoVariables+='<input type="text" id="tieneSumaAsegurada" name="tieneSumaAsegurada">';
				listadoVariables+='<input type="text" id="sumaAseguradaInicio" name="sumaAseguradaInicio" >';
				listadoVariables+='<input type="text" id="sumaAseguradaFin" name="sumaAseguradaFin">';
				
				listadoVariables+='<input type="text" id="tieneModelo" name="tieneModelo">';
				listadoVariables+='<input type="text" id="modeloListado" name="modeloListado" >';
				
				$("#listado_formulado").html(listadoVariables);
				
				$("#productoFormulado").val(data.grupoPorProducto[0].nombreComercialProducto);
				
				$("#tieneAntiguedad").val(data.grupoPorProducto[0].tieneAntiguedadVh);
				$("#antiguedadInicio").val(data.grupoPorProducto[0].antiguedadInicio);
				$("#antiguedadFin").val(data.grupoPorProducto[0].antiguedadFin);
				
				$("#tieneSumaAsegurada").val(data.grupoPorProducto[0].tieneSumaAsegurada);
				$("#sumaAseguradaInicio").val(data.grupoPorProducto[0].sumaAseguradaInicio);
				$("#sumaAseguradaFin").val(data.grupoPorProducto[0].sumaAseguradaFin);

				$("#tieneModelo").val(data.grupoPorProducto[0].tieneModelo);
				$("#modeloListado").val(data.grupoPorProducto[0].modeloListado);
				
			}
		});		
	}// Producto Autoconsa - Plan V
	if(grupoPorProductoId=="24" || grupoPorProductoId=="25" || grupoPorProductoId=="26"){
		$("#datosAdicionales").show();		
		$(".mostrarFuncionalidad").each(function() {
		   $(this).hide();
		});
		
	}else{	
		eliminarInformacionFormulados();
		$(".mostrarFuncionalidad").each(function() {
			   $(this).show();
		});
		$("#datosAdicionales").hide();
	}
}

function eliminarInformacionFormulados(){
	$("#listado_formulado").html("");
}

function obtenerInformacionProducto(numero){
	
	var tasaVehiculo = 0;
	var nombreProducto = $("#productoFormulado").val();
	
	var tieneAntiguedad = $("#tieneAntiguedad").val();
	var antiguedadInicio = $("#antiguedadInicio").val();
	var antiguedadFin = $("#antiguedadFin").val();
	var anoFabricacion = $('#anio_fabricacion'+numero).val();		
	var fechaActual = new Date();
	var anoActual = fechaActual.getFullYear();
	var antiguedadvalor = Number(anoActual)-Number(anoFabricacion);
	if(Number(anoActual)<Number(anoFabricacion))
		antiguedadvalor=0;
		
	var tieneSumaAsegurada = $("#tieneSumaAsegurada").val();
	var sumaAseguradaInicio = $("#sumaAseguradaInicio").val();
	var sumaAseguradaFin =$("#sumaAseguradaFin").val();
	var sumaAseguradaValor = $('#suma_asegurada_'+numero).val();
	
	var tieneModelo = $("#tieneModelo").val();
	var modeloListado =  $("#modeloListado").val();
	var modeloValor = $("#modelo_"+numero).select2('data').text;
	
	var errores ="El veh\xedculo ("+numero+") tiene los siguientes problemas: \n";
					
	var grupoPorProductoId = $("#productos").val();
	var tieneError = "0";
	// Producto QBE BOX
	if(grupoPorProductoId=="100"){
					
			if(tieneAntiguedad =="Si"){
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
			if(tieneModelo =="Si"){
				if(tieneModelo =="Si"){
					// Obtenemos todas las tasas y verificamos a que modelo pertenece					
					var listadoModelos = modeloListado.split("|");
					
					for (var i = 0;i<listadoModelos.length-1;i++){
						var modelo = listadoModelos[i].split(";");
						var modeloNombre = modelo[0];
						var tasaModelo = modelo[1];													
						// Verificamos si existe una cadena dentro de otra
						if (modeloValor.indexOf(modeloNombre) != -1) {
							tasaVehiculo = Number(tasaModelo); 
						}						
					}
					// Tasa para vehiculos - diferentes modelos
					if(tasaVehiculo == 0){
						tasaVehiculo = 4;
					}
				}
			}
			
			if(tieneError == "1"){
				alert(errores);
				return false;
			}else{
				return tasaVehiculo;
			}
	}
	
	// Producto Autoconsa - Plan V
	if(grupoPorProductoId=="24" || grupoPorProductoId=="25" || grupoPorProductoId=="26" || grupoPorProductoId=="155"){
		var mesPago = Number($("#mes_pago").val());
		var valorFecha = $("#txtfechaCompra").val();
		var valorFechaSplit = valorFecha.split("-");		
		var fechaCompra = new Date(valorFechaSplit[0], valorFechaSplit[1] - 1, valorFechaSplit[2]);
		var diaCompra = fechaCompra.getDate();
		var mesCompra = fechaCompra.getMonth()+1;
		var numeroDiasExtras = 0;
		var numeroDiasBisiesto = 0;
		
		// Verificamos el ano bisiesto y agregamos los valores		
		var vigenciaPoliza = $("#vigencia").val();
		var fechaActual = new Date();
		var anoActual = Number(fechaActual.getFullYear());
		
		for(var i =1;i<=Number(vigenciaPoliza);i++){
			// Funcion para verificar anio bisiesto
			var anoBisiesto = anoActual+i;
			var esBisiesto = esAnoBisiesto(anoBisiesto);
			if(esBisiesto == true)
				numeroDiasBisiesto++;						
		}
		
		if(mesPago == 1){
			if(diaCompra == 1){
				numeroDiasExtras = 0;				
				// Fecha de Primer Pago
				fechaPrimerPago = new Date(fechaCompra.getFullYear(), mesCompra, 1);
			}						
			if(diaCompra >=7 && diaCompra <=31 ){						
				// Fecha limite primero de cada mes (el primer dia del segundo mes)			
				var fechaPrimerPago = new Date(fechaCompra.getFullYear(), mesCompra+1, 1);
				// 1 dia en milisegundos
				var diaMilisegundos=1000*60*60*24;
				// Convertimos las fechas en milisegundos para comparar
				var fechaCompraMS = fechaCompra.getTime();
				var fechaLimiteMS = fechaPrimerPago.getTime();  		
				// Calculala diferencia en milisegundos
				  var diferenciaMS = fechaLimiteMS - fechaCompraMS;			  
				// Convertimos los en dias de diferencia
				var diasExtrasObtenidos = Math.round(diferenciaMS/diaMilisegundos); 															
				numeroDiasExtras = diasExtrasObtenidos-31;
			}
		}
		if(mesPago == 16){				
			if(diaCompra >=1 && diaCompra <=16 ){
				// Fecha limite primero de cada mes (el primer dia del primer mes)			
				var fechaPrimerPago = new Date(fechaCompra.getFullYear(), mesCompra, 1);
				// 1 dia en milisegundos
				var diaMilisegundos=1000*60*60*24;
				// Convertimos las fechas en milisegundos para comparar
				var fechaCompraMS = fechaCompra.getTime();
				var fechaLimiteMS = fechaPrimerPago.getTime();  		
				// Calculala diferencia en milisegundos
				  var diferenciaMS = fechaLimiteMS - fechaCompraMS;			  
				// Convertimos los en dias de diferencia
				var diasExtrasObtenidos = Math.round(diferenciaMS/diaMilisegundos); 
				numeroDiasExtras = diasExtrasObtenidos-31;												
			}
			if(diaCompra >=22 && diaCompra <=31 ){			
				// Fecha limite primero de cada mes (el primer dia del segundo mes)			
				var fechaPrimerPago = new Date(fechaCompra.getFullYear(), mesCompra+1, 1);
				// 1 dia en milisegundos
				var diaMilisegundos=1000*60*60*24;
				// Convertimos las fechas en milisegundos para comparar
				var fechaCompraMS = fechaCompra.getTime();
				var fechaLimiteMS = fechaPrimerPago.getTime();  		
				// Calculala diferencia en milisegundos
				var diferenciaMS = fechaLimiteMS - fechaCompraMS;			  
				numeroDiasExtras = diasExtrasObtenidos-31;		
			}					
		}
		var tasaVehiculoSinBisiesto = 0;
		
		if(grupoPorProductoId=="24" || grupoPorProductoId=="155"){
			tasaVehiculoSinBisiesto = Number(4.0);
			if(numeroDiasBisiesto >0){
				numeroDiasBisiesto = 1;				
				var tasaVehiculoPrimerAno = ((365+numeroDiasBisiesto+numeroDiasExtras)*tasaVehiculoSinBisiesto)/365;
				var tasaVehiculoVariosAnos = tasaVehiculoSinBisiesto * (vigenciaPoliza -1);				
				tasaVehiculo = (tasaVehiculoPrimerAno.toFixed(3)+tasaVehiculoVariosAnos)/vigenciaPoliza;				
			}else{
				tasaVehiculo = tasaVehiculoSinBisiesto;
			}			
		}
		if(grupoPorProductoId=="25"){
			tasaVehiculoSinBisiesto = Number(3.5);
			if(numeroDiasBisiesto >0){
				numeroDiasBisiesto = 1;
				var tasaVehiculoPrimerAno = ((365+numeroDiasBisiesto+numeroDiasExtras)*tasaVehiculoSinBisiesto)/365;
				tasaVehiculo = (tasaVehiculoPrimerAno+(vigenciaPoliza -1)*tasaVehiculoSinBisiesto)/vigenciaPoliza;
			}else{
				tasaVehiculo = tasaVehiculoSinBisiesto;
			}			
		}
					
		if(grupoPorProductoId=="26"){
			tasaVehiculoSinBisiesto = Number(4.75);
			if(numeroDiasBisiesto >0){
				numeroDiasBisiesto = 1;
				var tasaVehiculoPrimerAno = ((365+numeroDiasBisiesto+numeroDiasExtras)*tasaVehiculoSinBisiesto)/365;
				tasaVehiculo = (tasaVehiculoPrimerAno+(vigenciaPoliza -1)*tasaVehiculoSinBisiesto)/vigenciaPoliza;
			}else{
				tasaVehiculo = tasaVehiculoSinBisiesto;
			}
		}		
		return tasaVehiculo.toFixed(3);			
	}	
}

function esAnoBisiesto(anoBisiesto) { 
	return (anoBisiesto%400)?((anoBisiesto%100)?((anoBisiesto%4)?false:true):false):true; 
}