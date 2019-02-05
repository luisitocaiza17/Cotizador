/**
 * 
 */
function verificarProducto(valor) {
	if(valor != 'VHDinamico'){
		$("#datos_cerrados").show();
		$("#datos_dinamico").show();
		gruposProductos();
	}
	else{
		$("#datos_cerrados").hide();
		$("#datos_dinamico").show();	
		verificarTipoProducto("VHDinamico");
	}
	cargarPuntosVenta("");
	cargarAgentes();
	cargarVigenciasPolizas("");
	cargarTiposIdentificacion("","",false);
	mostrarTipoIdentificacion();
}

function verificarTipoCarga(valor) {
	if(valor != '1'){
		$("#datos_clientes").show();
	}
	else{
		$("#datos_clientes").hide();
	}
}


function verificarTipoProducto(valor){
	agregarVehiculo();
	cargarDatosVehiculo(1, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
	cargarTablaCoberturas("",1,"","", valor);
}

/*
 * METODO QUE RECIBE EL ID Y SETEA EL TIPO DE IDENTIFICACION CORRESPONDIENTE AL ID
 * QUE RECIBE.
 */	
function cargarTiposIdentificacion(seleccionada,tipo,noChange) {
	$.ajax({
		url : '../TipoIdentificacionController',
		data : {
			"tipoConsulta" : "ObtenerTodos",
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var options = '';			
			for (var i = 0; i < data.tipoIdentificacion.length; i++) {
				options += '<option value="' + data.tipoIdentificacion[i].id + '">' + data.tipoIdentificacion[i].nombre + '</option>';
			}

			if(tipo!=''){
				if(noChange){
					$("#tipo_identificacion_"+tipo).html(options).val(seleccionada).trigger('change');
				}else{
					$("#tipo_identificacion_"+tipo).html(options).val(seleccionada);
				}
			}
			else
				$(".tipo_identificacion").html(options).val('1');
		}
	});
}

/*
 * METODO QUE MUESTRA O ESCONDE LOS CAMPOS DEL FORMULARIO DE LA UPLA 
 * DEPENDIENDO DEL TIPO DE IDENTIFICACION QUE SELECCIONE EL USUARIO
 */
function mostrarTipoIdentificacion() {
	var tipoIdentificacionId = $("#tipo_identificacion_principal").val();
	if (tipoIdentificacionId == '' || tipoIdentificacionId == '1' || tipoIdentificacionId == '2' || tipoIdentificacionId == '3') {
		$("#datosAdicionalesNatural").show();
		$("#datosAdicionalesJuridica").hide();
		$("#nombre_completo").val("");
	} else {
		$("#datosAdicionalesNatural").hide();
		$("#datosAdicionalesJuridica").show();
		$("#nombres").val("");
		$("#apellidos").val("");
	}

	if (tipoIdentificacionId == '' || tipoIdentificacionId == '1' || tipoIdentificacionId == '3' || tipoIdentificacionId == '4'){
		$("#identificacion").attr("onkeypress","validarKeyPress(event,/[0-9]/);");

	}
	else{
		$("#identificacion").attr("onkeypress","validarSoloLetrasNumeros(event);");
	}
}

function cargarPorIdentificacion(formulario, valor) {
	var identificacion = valor;
	if ((identificacion.length >= 10 && $("#tipo_identificacion_principal").val()!=2)||(identificacion.length >= 5 && ($("#tipo_identificacion_principal").val()==2||$("#tipo_identificacion_principal").val()==""))) {
		$(".loading_identificacion").fadeIn();
		var entidad = "";
		
		$.ajax({
			url : '../EntidadController',
			data : {
				"identificacion" : identificacion,
				"tipoConsulta" : "cargarPorIdentificacionEnsurance"
			},
			type : 'POST',
			datatype : 'json',
			success : function (data) {
				var entidad = data.entidad;
				if (entidad.clienteIdEnsurance != null && entidad.entidadIdEnsurance != "") {
					if (formulario == "datosClienteInicio") {
						$("#codigoEntidadEnsurance").val(entidad.entidadIdEnsurance);
						$("#codigoClienteEnsurance").val(entidad.clienteIdEnsurance);
						$("#nombres").val(entidad.nombre);
						$("#apellidos").val(entidad.apellido);
						$("#nombre_completo").val(entidad.apellido + entidad.nombre);
						$("#tipo_identificacion_principal").val(entidad.tipoIdentificacion).trigger("change");
					}
					
					if(formulario == "formasPagoDebitoBancario"){
						$("#bancoTitular").val(entidad.nombre + " " + entidad.apellido);
						$("#bancoTipoIdentificacion").val(entidad.tipoIdentificacion);
					}

					if(formulario == "formasPagoDebitoTarjetas"){
						$("#tarjetaTitular").val(entidad.nombre + " " + entidad.apellido);
						$("#tarjetaTipoIdentificacion").val(entidad.tipoIdentificacion);
					}
				}else{
					$("#nombres").val("");
					$("#apellidos").val("");
				}
				$(".loading_identificacion").fadeOut();
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$(".loading_identificacion").fadeOut();
			}
		});			
	}
}

function gruposProductos() {
	// Consultar listado de grupos productos
	$.ajax({
		url : '../GrupoPorProductoController',
		data : {
			"tipoConsulta" : "encontrarTodosGrupoProducto",
			"tipoObjeto" : $("#tipo_objeto").val(),
		},	
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var listadoGrupos = data.listadoGruposProducto;	
			var ListadoGruposUnico = arregloUnicoJSON(listadoGrupos); 
			$("#grupo_productos").val("");			
			$("#grupo_productos").append("<option value=''>Seleccione una opcion</option>");
			$.each(ListadoGruposUnico, function (index) {
				$("#grupo_productos").append("<option value='" + ListadoGruposUnico[index].id + "'>" + ListadoGruposUnico[index].nombre + "</option>");
			});	
		}
	});
}

//Arreglo de elementos unicos JSON
function arregloUnicoJSON(obj){
    var uniques=[];
    var stringify={};
    for(var i=0;i<obj.length;i++){
       var keys=Object.keys(obj[i]);
       keys.sort(function(a,b) {return a-b});
       var str='';
        for(var j=0;j<keys.length;j++){
           str+= JSON.stringify(keys[j]);
           str+= JSON.stringify(obj[i][keys[j]]);
        }
        if(!stringify.hasOwnProperty(str)){
            uniques.push(obj[i]);
            stringify[str]=true;
        }
    }
    return uniques;
}

function obtenerProductosGrupo(){
	// Consultar listado de productos dentro de un grupos de productos
	$("#productos").empty();	

	$.ajax({
		url : '../GrupoPorProductoController',

		data : {
			"tipoConsulta" : "encontrarTodosPorGrupo",
			"tipoObjeto": $("#tipo_objeto").val(),
			"grupoProductoId" : $("#grupo_productos").val()
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {			
			var listadoGrupos = data.listadoGruposPorProducto;			
			$("#productos").append("<option value=''>Seleccione una opcion</option>");
			$.each(listadoGrupos, function (index) {
				var inspeccionRequerida=listadoGrupos[index].inspeccionRequerida;
				$("#productos").append("<option value='" + listadoGrupos[index].id + "' inspeccionRequerida='"+inspeccionRequerida+"' >" + listadoGrupos[index].nombre + "</option>");
			});
		}
	});
}

function limpiarTasaProducto(){
	$("#tasa").val('');
	$("#tasas").hide();
	$("#tasa").show();
}

/*
 * OBTIENE TASA POR PRODUCTO
 */
function obtenerTasaPorProducto(){
	// Consultar el valor de las tasas del producto
	$.ajax({
		url : '../GrupoPorProductoController',

		data : {
			"tipoConsulta" : "encontrarTasaProducto",			
			"grupoProductoId" : $("#productos").val()
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var inspeccionRequerida = $('#productos').find(":selected").attr("inspeccionRequerida");
			$("#necesitaInspeccion").val(inspeccionRequerida);
			var tiene_tasa = data.tiene_tasa_fija;
			var es_formulada = data.es_formulado;
			var tiene_inspeccion = data.tiene_inspeccion;
			$("#tiene_inspeccion").val();

			if(tiene_tasa == 1 && es_formulada == 0){
				$("#tasa").val(data.tasa_fija_valor);
				$("#tasas").hide();
				$("#tasa").show();
			}
			if(tiene_tasa == 0 && es_formulada == 0){
				$("#tasa").val("");
				$("#tasa").hide();
				$("#tasas").show();

				var listadoTasas = data.tasas_listado;
				$("#tasas").empty();
				$("#tasas").append("<option value=''>Seleccione una opcion</option>");
				if(listadoTasas != undefined){
				$.each(listadoTasas, function (index) {
					$("#tasas").append("<option value='" + listadoTasas[index].id + "'>" + listadoTasas[index].nombre + "</option>");
				});
				}
			}
			if(tiene_tasa == 0 && es_formulada == 1){
				$("#tasa").val("Formulada");
				$("#tasas").hide();
				$("#tasa").show();
			}
			
		}
	});
}

/*
 * METODO QUE RECIBE EL ID DEL PUNTO DE VENTA Y SETEA EL CORRESPONDIENTE AL ID
 * QUE RECIBE. 
 */
function cargarPuntosVenta(seleccionada){
	var producto;
	if($("#tipo_objeto").val()!= 'VHDinamico'){
		producto = $('#productos').val();
	}
	else{
		producto = '148';
	}
	$.ajax({
		url : '../PuntoVentaController',
		data : {
			"tipoConsulta" : "puntosVentaXProducto",
			"tipoObjeto" :  $("#tipo_objeto").val(),
			"grupoPorProductoId":producto,

		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var sucursales = data.sucursales;
			var options = '';
			options = '<option value="">Seleccione un punto de venta</option>';
			var contador;
			for (var j = 0; j < sucursales.length; j++) {				
				contador =0;
				for (var i = 0; i < data.puntosVenta.length; i++) {
					if (data.puntosVenta[i].sucursal == sucursales[j].id) {
						contador++;
						if(contador ==1){
							options += '<option value="" disabled="disabled" class="seleccionado" ">' + sucursales[j].nombre + '</option>';
						}
						options += '<option value="' + data.puntosVenta[i].id + '" pxpv="'+data.puntosVenta[i].productoPorPuntoDeVenta+'"  >&nbsp;&nbsp;&nbsp;&nbsp;' + data.puntosVenta[i].nombre + '</option>';
					}
				}
			}
			$("#punto_venta").html(options);
		}
	});
}

function verificarPuntosVenta() {

	$.ajax({
		url : '../PuntoVentaController',
		data : {
			"tipoConsulta" : "verificacionPuntoVenta",
			"tipoObjeto" :$("#tipo_objeto").val(),
			"puntoVentaId" : $("#punto_venta").val(),

		},
		type : 'post',
		datatype : 'json',
		success : function (data) {		
			if(data.punto_venta.agente_id == null || data.punto_venta.agente_id =="" ){				
				$('#agentes').prop( "disabled", false );
				$("#agentes option:first").attr('selected','selected');				
			}else{
				$('#agentes option[value='+data.punto_venta.agente_id+']').attr('selected','selected');
				$('#agentes').prop( "disabled", true );
				obtenerAgenteComision("");
			}
		}
	});

}

function metodoObtenerProductoFormulados(grupoPorProductoId){
	
	var listadoVariables = "";
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
	}else{
		eliminarInformacionFormulados();
	}
}

/*
 * METODO QUE RECIBE EL ID DEL AGENTE Y SETEA EL AGENTE CORRESPONDIENTE AL ID
 * QUE RECIBE.
 */	
function cargarAgentes() {
	$.ajax({
		url : '../AgenteController',
		data : {
			"tipoConsulta" : "consultarAgentes",
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var options = '';
			options = '<option value="">Seleccione un agente</option>';
			for (var i = 0; i < data.agentes.length; i++) {
				options += '<option value="' + data.agentes[i].id + '">' + data.agentes[i].nombre + '</option>';
			}
			$("#agentes").html(options);
		}
	});
}

/*
 * METODO QUE VALIDA EL RANGO DE LA COMISION MLDEALER
 */	
function validarRangoComisionMLDealer(){	
	var agenteId = $("#agentes").val();
	if(agenteId == "451"){
		var valorComision = Number($('#porc_comision').val());
		if(valorComision < Number('15') || valorComision > Number('25') ){
			alert('La comision ingresada debe ser mayor al 15% y menor que 25% ');
			$('#porc_comision').val('15');
			return false;
		}
	}
}

/*
 * METODO QUE OBTIENE LA COMISION DEL AGENTE QUE
 * ESTA REGISTRADA EN EL CONTRATO DE AGENCIAMIENTO
 */
function obtenerAgenteComision(valorComision) {
	var agenteId = $("#agentes").val();
	var Comision = "";
	if(valorComision != undefined)		
		Comision = valorComision;
			
	if (agenteId == '') {
		$("#porc_comision").val("");			
	} else {
		// Consultar la comision del agente ayanez
		$.ajax({
			url : '../AgenteController',
			data : {
				"tipoConsulta" : "ObtenerAgenteId",
				"agenteId" : agenteId
			},
			type : 'post',
			datatype : 'json',
			success : function (data) {
				if(data.comisionVariable)
				{
					$("#porc_comision").fadeOut();
					$("#porc_comision_cb").fadeIn();
					$("#porc_comision_cb").html('');
					var options='<option value="">Seleccione una comisión</option>';
					if(data.comision1!=0&&data.comision1!="0")
						options+='<option value='+data.comision1+'>'+data.comision1+'</option>';
					if(data.comision2!=0&&data.comision2!="0")
						options+='<option value='+data.comision2+'>'+data.comision2+'</option>';
					if(data.comision3!=0&&data.comision3!="0")
						options+='<option value='+data.comision3+'>'+data.comision3+'</option>';
					$("#porc_comision_cb").html(options);
					if(Comision.length != 0)
						$("#porc_comision_cb").val(Comision);	
					
				}
				else
				{
					// Validacion MLDealears para que puedan agregar valores numericos
					if(agenteId == "451"){
						// Validacion de que solo personal de qbe pueda modificar la comisi�n
						if(data.rol == '1' || data.rol == '2' || data.rol == '7' || data.rol == '9' )
							$("#porc_comision").prop('readonly', false);						 
						 $("#porc_comision").prop('maxlength', '4');						 						 
						 if(Comision.length != 0)
							 $("#porc_comision").val(Comision);
						 else
							 $("#porc_comision").val(data.comision);
						 
					}else{						 
						if(Comision.length != 0)
							$("#porc_comision").val(Comision);
						else
							$("#porc_comision").val(data.comision);
						
						$("#porc_comision").prop('readonly', true);
					}

					$("#porc_comision_cb").fadeOut();
					$("#porc_comision").fadeIn();
				}
			}
		});
	}
}

/*
 * METODO QUE CARGA EL NUMERO DE AÑOS CON EL QUE SE VA A EMITIR LA POLIZA.
 * Y SETEA EL VALOR CORRESPONDIENTE AL ID RECIBIDO
 */	
function cargarVigenciasPolizas(seleccionada) {

	$.ajax({
		url : '../VigenciaPolizaController',
		data : {
			"tipoConsulta" : "encontrarTodosActivos",
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var options = '';
			options = '<option value="">Seleccione una vigencia</option>';
			for (var i = 0; i < data.vigencias_poliza.length; i++) {
				options += '<option value="' + data.vigencias_poliza[i].id + '">' + data.vigencias_poliza[i].nombre + '</option>';
			}
			$("#vigencia").html(options).val(seleccionada);
		}
	});
}

function cargarTablaCoberturas(seleccionada,numero,escogidas,paquete,grupoPorProductoId) {
	var coberturasPT=['1528073305754',
	                  '1528079531039',
	                  '1539870032603',
	                  '1822474698752',
	                  '7086801709200'];
			$.ajax({
				url : '../CoberturaController',
				data : {
					"tipoConsulta" : "encontrarTodos",
					"tipoObjeto" : $("#tipo_objeto").val(),
					"grupoPorProductoId": grupoPorProductoId,
				},
				type : 'post',
				datatype : 'json',
				success : function (data) {
					var tablaBlackCobertura = '<table id="coberturaTablaPaqueteBlack">';
					var tablaBlueCobertura = '<table id="coberturaTablaPaqueteBlue">';
					var tablaGoldCobertura = '<table id="coberturaTablaPaqueteGold">';
					var tablaPlatinumCobertura = '<table id="coberturaTablaPaquetePlatinum">';
					var tablaSinCobertura = '<table id="coberturaTablaSinPaquete">';
					var listaCoberturas=data.listadoCobertura;
					var coberturasPorPaquetes=data.coberturasPorPaquetes;

					// Verificacion si existen planes seleccionados - sino no se muestra
					var coberturas_paquete_black = 0;
					var coberturas_paquete_blue = 0;
					var coberturas_paquete_gold = 0;
					var coberturas_paquete_platinum = 0;
					
					//var tablaexcesoRC = '<input type="text" id="'++'" value="'+listaCoberturas[i].tasa+'">'; 

					$("#tabla_deducibles").hide();
					for(var i=0;i<listaCoberturas.length;i++){
						if(listaCoberturas[i].mostrar_cotizador == '1'){
							for(var k=0;k<coberturasPorPaquetes.length;k++){

								if(coberturasPorPaquetes[k].paquete_id==1&&listaCoberturas[i].codigo==coberturasPorPaquetes[k].cobertura_id){
									
									tablaBlackCobertura += '<tr><td colspan="5" style="padding-right:10px"><input type="checkbox" checked="checked" disabled="disabled" class="check_Black_Cobertura_ datosCobertura datosVehiculoClick bloquearEmitido" id="check_Black_Cobertura_' + listaCoberturas[i].codigo + '_tabla_" />' + listaCoberturas[i].nombre + '</td>' +
									'<td></td><td colspan="5" style="padding-right:10px">' + listaCoberturas[i].descripcion + '</td><td hidden="hidden" style="padding-left:10px" colspan="5" align="right" class="valor_Black_Cobertura_tabla_" id="valor_Black_Cobertura_' + listaCoberturas[i].codigo + '_tabla_"></td><input type="hidden" class="'+ listaCoberturas[i].tipoTasa+'" value="'+listaCoberturas[i].tasa+'"></td></tr>';
									coberturas_paquete_black++;
								}
								if(coberturasPorPaquetes[k].paquete_id==2&&listaCoberturas[i].codigo==coberturasPorPaquetes[k].cobertura_id){

									tablaBlueCobertura += '<tr><td colspan="5" style="padding-right:10px"><input type="checkbox" checked="checked" disabled="disabled" class="check_Blue_Cobertura_ datosCobertura datosVehiculoClick bloquearEmitido" id="check_Blue_Cobertura_' + listaCoberturas[i].codigo + '_tabla_" />' + listaCoberturas[i].nombre + '</td>' +
									'<td></td><td colspan="5" style="padding-right:10px">' + listaCoberturas[i].descripcion + '</td><td hidden="hidden" style="padding-left:10px" colspan="5" align="right" class="valor_Blue_Cobertura_tabla_" id="valor_Blue_Cobertura_' + listaCoberturas[i].codigo + '_tabla_"></td><input type="hidden" class="'+ listaCoberturas[i].tipoTasa+'" value="'+listaCoberturas[i].tasa+'"></td></tr>';
									coberturas_paquete_blue++;
								}
								if(coberturasPorPaquetes[k].paquete_id==3&&listaCoberturas[i].codigo==coberturasPorPaquetes[k].cobertura_id){

									tablaGoldCobertura += '<tr><td colspan="5" style="padding-right:10px"><input type="checkbox" checked="checked" disabled="disabled" class="check_Gold_Cobertura_ datosCobertura datosVehiculoClick bloquearEmitido" id="check_Gold_Cobertura_' + listaCoberturas[i].codigo + '_tabla_" />' + listaCoberturas[i].nombre + '</td>' +
									'<td></td><td colspan="5" style="padding-right:10px">' + listaCoberturas[i].descripcion + '</td><td hidden="hidden" style="padding-left:10px" colspan="5" align="right" class="valor_Gold_Cobertura_tabla_" id="valor_Gold_Cobertura_' + listaCoberturas[i].codigo + '_tabla_"></td><input type="hidden" class="'+ listaCoberturas[i].tipoTasa+'" value="'+listaCoberturas[i].tasa+'"></td></tr>';
									coberturas_paquete_gold++;
								}
								if(coberturasPorPaquetes[k].paquete_id==4&&listaCoberturas[i].codigo==coberturasPorPaquetes[k].cobertura_id){

									tablaPlatinumCobertura += '<tr><td colspan="5" style="padding-right:10px"><input type="checkbox" checked="checked" disabled="disabled" class="check_Platinum_Cobertura_ datosCobertura datosVehiculoClick bloquearEmitido" id="check_Platinum_Cobertura_' + listaCoberturas[i].codigo + '_tabla_" />' + listaCoberturas[i].nombre + '</td>' +
									'<td></td><td colspan="5" style="padding-right:10px">' + listaCoberturas[i].descripcion + '</td><td hidden="hidden" style="padding-left:10px" colspan="5" align="right" class="valor_Platinum_Cobertura_tabla_" id="valor_Platinum_Cobertura_' + listaCoberturas[i].codigo + '_tabla_"></td><input type="hidden" class="'+ listaCoberturas[i].tipoTasa+'" value="'+listaCoberturas[i].tasa+'"></td></tr>';
									coberturas_paquete_platinum++;
								}

							}

							if ((listaCoberturas[i].codigo == '6348540415022' || listaCoberturas[i].codigo == '6349173767914') && $.inArray(listaCoberturas[i].codigo,coberturasPT)==-1) {
										tablaSinCobertura += '<tr class="esconderPT"><td colspan="5" style="padding-right:10px"><input type="checkbox" class="check_Sin_Cobertura_ datosCobertura datosVehiculoClick bloquearEmitido" onClick="calcularTotalSinPaquete(\'\'); $(this).parent().next().next().next().next().next().children().first().trigger(\'change\')" id="check_Sin_Cobertura_'
												+ listaCoberturas[i].codigo
												+ '_tabla_" />'
												+ listaCoberturas[i].nombre
												+ '</td><td></td><td colspan="5" style="padding-right:10px">'
												+ listaCoberturas[i].descripcion
												+ '</td><td hidden="hidden" style="padding-left:10px" colspan="5" align="right" class="valor_Sin_Cobertura_tabla_" id="valor_Sin_Cobertura_'
												+ listaCoberturas[i].codigo
												+ '_tabla_"></td><input type="hidden" class="'
												+ listaCoberturas[i].tipoTasa
												+ '" value="'
												+ listaCoberturas[i].tasa
												+ '"></td>'
												+ '<td><input type="number" id="excesoResponsabilidadCivil_'
												+ listaCoberturas[i].codigo
												+ '_" min="0" class="valorExcesoRC  bloquearEmitido" onchange="cambioExcesoRC(\'excesoResponsabilidadCivil_'
												+ listaCoberturas[i].codigo
												+ '_\',\'\',event);" max="'
												+ data.limiteExcesoRC
												+ '" value="'
												+$("#valorUnicoResponsabilidadCivil").val()
												+'" check="check_Sin_Cobertura_'
												+ listaCoberturas[i].codigo
												+ '_tabla_numero"></td>' + '</tr>';
										
									} else {
										if ($.inArray(listaCoberturas[i].codigo,coberturasPT)==-1) {
										tablaSinCobertura += '<tr class="esconderPT"><td colspan="5" style="padding-right:10px"><input type="checkbox" class="check_Sin_Cobertura_ datosCobertura datosVehiculoClick bloquearEmitido" onClick="calcularTotalSinPaquete(\'\')" id="check_Sin_Cobertura_'
												+ listaCoberturas[i].codigo
												+ '_tabla_" />'
												+ listaCoberturas[i].nombre
												+ '</td>'
												+ '<td></td><td colspan="5" style="padding-right:10px">'
												+ listaCoberturas[i].descripcion
												+ '</td><td hidden="hidden" style="padding-left:10px" colspan="5" align="right" class="valor_Sin_Cobertura_tabla_" id="valor_Sin_Cobertura_'
												+ listaCoberturas[i].codigo
												+ '_tabla_"></td><input type="hidden" class="'
												+ listaCoberturas[i].tipoTasa
												+ '" value="'
												+ listaCoberturas[i].tasa
												+ '"></td></tr>';
									}
										else{
											if ($.inArray(listaCoberturas[i].codigo,coberturasPT)!=-1) {
												tablaSinCobertura += '<tr><td colspan="5" style="padding-right:10px"><input type="checkbox" class="check_Sin_Cobertura_ datosCobertura datosVehiculoClick bloquearEmitido" onClick="calcularTotalSinPaquete(\'\')" id="check_Sin_Cobertura_'
													+ listaCoberturas[i].codigo
													+ '_tabla_" />'
													+ listaCoberturas[i].nombre
													+ '</td>'
													+ '<td></td><td colspan="5" style="padding-right:10px">'
													+ listaCoberturas[i].descripcion
													+ '</td><td hidden="hidden" style="padding-left:10px" colspan="5" align="right" class="valor_Sin_Cobertura_tabla_" id="valor_Sin_Cobertura_'
													+ listaCoberturas[i].codigo
													+ '_tabla_"></td><input type="hidden" class="'
													+ listaCoberturas[i].tipoTasa
													+ '" value="'
													+ listaCoberturas[i].tasa
													+ '"></td></tr>';
											}
										}
									}
									
									
						}
					}

					tablaBlackCobertura += '</table>';
					tablaBlueCobertura += '</table>';
					tablaGoldCobertura += '</table>';
					tablaPlatinumCobertura += '</table>';
					tablaSinCobertura += '</table>';
					
					$("#lista_coberturas").html(tablaBlackCobertura + tablaBlueCobertura + tablaGoldCobertura + tablaPlatinumCobertura + tablaSinCobertura);
					//falta escoger la seleccionada
						
					var sumaCoberturas=0;

					var coberturasBlack=$(".check_Black_Cobertura_");
					var coberturasBlue=$(".check_Blue_Cobertura_");
					var coberturasGold=$(".check_Gold_Cobertura_");
					var coberturasPlatinum=$(".check_Platinum_Cobertura_");		


					var numPaquetes=0;
					
					if(coberturas_paquete_black >0){
						$("#div_paquete_black_").show();	
						numPaquetes++;
					}
					if(coberturas_paquete_black == 0){
						$("#div_paquete_black_").parent().hide().attr("width","0%");				
					}			
					if(coberturas_paquete_blue > 0){
						$("#div_paquete_blue_").show();	
						numPaquetes++;			
					}
					if(coberturas_paquete_blue == 0){
						$("#div_paquete_blue_").parent().hide().attr("width","0%");				
					}
					if(coberturas_paquete_gold > 0){
						$("#div_paquete_gold_").show();	
						numPaquetes++;			
					}	
					if(coberturas_paquete_gold == 0){
						$("#div_paquete_gold_").parent().hide().attr("width","0%");			
					}				
					if(coberturas_paquete_platinum > 0){
						$("#div_paquete_platinum_").show();	
						numPaquetes++;			
					}			
					if(coberturas_paquete_platinum == 0){
						$("#div_paquete_platinum_").parent().hide().attr("width","0%");				
					}
					
					

					if (numPaquetes != 0) {
							if (coberturas_paquete_black > 0) {
								$("#div_paquete_black_").parent().attr("width",72 / numPaquetes+"%");
							}
							if (coberturas_paquete_blue > 0) {
								$("#div_paquete_blue_").parent().attr("width",72 / numPaquetes+"%");
							}
							if (coberturas_paquete_gold > 0) {
								$("#div_paquete_gold_").parent().attr("width",72 / numPaquetes+"%");
							}
							if (coberturas_paquete_platinum > 0) {
								$("#div_paquete_platinum_").parent().attr("width", 72 / numPaquetes+"%");
							}
						}
					else{
						$("#div_paquete_platinum_").parent().next().attr("width","90%");
					}
					
					var bandera = false;
					if(escogidas != null || paquete != null){
						var aux;
						var ids=[];
						$.each(escogidas,function(index){
							if(ids.indexOf(escogidas[index].coberturaId) == -1){
								ids[index]=escogidas[index].coberturaId;
								if(escogidas[index].nemotecnico == "TORI"){
									$("#coberturaTR_check").trigger("click").prop("checked",true);
									editoVehiculo=false;
									bandera = true;
								}
							}
						});

						//if(!bandera&&!$("#coberturaTR_check"  + numero).is(":checked"))

						//	$("#coberturaTR_check"  + numero).trigger("click").attr('checked','checked');
						if ($("#tipo_objeto").val() == "VHDinamico") { 	
							if (!($("#coberturaTR_check").is(":checked") || $("#coberturaDT_check").is(":checked") || $("#coberturaRC_check").is(":checked"))) {
								$("#coberturaTR_check").trigger("click").prop("checked",true);;
							}
						} else {
							$("#coberturaTR_check").trigger("click").prop("checked",true);;
						}

						if(paquete==1)
							aux="check_Black_Cobertura_";
						if(paquete==2)
							aux="check_Blue_Cobertura_";
						if(paquete==3)
							aux="check_Gold_Cobertura_";
						if(paquete==4)
							aux="check_Platinum_Cobertura_";
						if(paquete==5)
							aux="check_Sin_Cobertura_";

						var coberturasPorPaquete=$("."+aux);

						$.each(coberturasPorPaquete,function(index){
							var chk=coberturasPorPaquete[index];
							if(ids.indexOf($(chk).attr('id').replace(aux,'').replace('_tabla_',''))!=-1){
								$(chk).prop("checked",true);
								$(chk).attr("checked","checked");
							}
						});
					}
					$(".check_Black_Cobertura_1 .datosCobertura").bind({click: function(){					
						editoVehiculo=true;
					}
					});
				}
			});
		
		
		$(".datosVehiculoClick").click(function(){
			editoVehiculo=true;
		});
	}

//Agregar dinamicamente uno o varios vehiculos - mediante el boton agregar vehiculo cerrados livianos
function agregarVehiculo(){

	var mostrarCoberturas = "";
	var mostrarCerrados = "";
	
	
	if($("#tipo_objeto").val() !="VHDinamico")
	{
		mostrarCoberturas = "display:none;";
	}
	
	if($("#tipo_objeto").val() =="Pesados")
	{
		mostrarCerrados = '<tr>'+
				'<td><label><b>Tonelaje: </b></label></td>'+
				'<td><input type="text" name="tonelajenumero" id="tonelajenumero" class="tonelaje datosVehiculo cambioVehiculo"  maxlength="4" onkeypress="return justNumbers(event);"></td>'+
				'<td><label><b>Tipo adquisicion: </b></label></td>'+
				'<td><select name="tipo_adquisicionnumero" id="tipo_adquisicionnumero" class="tipo_adquisicion datosVehiculo cambioVehiculo" ></select></td>'+      	
				'</tr>';
	}
	
	if($("#tipo_objeto").val() =="Livianos" || $("#tipo_objeto").val() =="Publicos" || $("#tipo_objeto").val() =="Motos")
	{
		mostrarCerrados = '<tr>'+
				'<td><label><b>Tipo adquisicion: </b></label></td>'+
				'<td><select name="tipo_adquisicionnumero" id="tipo_adquisicionnumero" class="tipo_adquisicion datosVehiculo cambioVehiculo" ></select></td>'+
				'<td></td>'+
				'<td></td>'+      	
				'</tr>';
	}     		    
	
	var unicoCoberturas=		
	'<div class="panel-body col-sm-12" style="padding: 15px; border: 0px solid rgb(66, 139, 202);">'+
	'<table style="border: 1px solid rgb(66, 139, 202); border-radius:5px; border-bottom-color: transparent;  text-align:center; " id="" >'+
	'<tbody>'+
	'<tr >'+
	'<td rowspan="2" width="10%" style="color: rgb(255, 255, 255); background: rgb(0, 61, 165);"><center><label>Coberturas</label></center></td>'+
	'<td style="background: rgb(255, 255, 255);"><center><label><input type="checkbox" name="coberturaTR_check" id="coberturaTR_check" onclick="habilitarDeshabilitarCobertura(\'\',event);" class="cobertura datosVehiculo cambioVehiculoClick" value="Todo Riesgo">&nbsp;&nbsp;Todo Riesgo</label></td></center>'+
	'</td>'+ 
	
	'<td  style="background: rgb(255, 255, 255);" width="20%"><center><label style="'+mostrarCoberturas+'"><input type="checkbox" name="coberturaDT_check" id="coberturaDT_check" onclick="habilitarDeshabilitarCobertura(\'\',event); cambiaDispositivoRastreo();" class="cobertura datosVehiculo cambioVehiculoClick" value="Perdida Total" style="'+mostrarCoberturas+'">&nbsp;&nbsp; Perdida Total</label></center></td>'+
	'<td style="background: rgb(255, 255, 255);"><center><label style="'+mostrarCoberturas+'"><input type="checkbox" name="coberturaRC_check" id="coberturaRC_check" onclick="habilitarDeshabilitarCobertura(\'\',event);" class="cobertura datosVehiculo cambioVehiculoClick" value="Resp. Civil" style="'+mostrarCoberturas+'">&nbsp;&nbsp; Responsabilidad Civil</label></center></td>'+
	
	'</tr>'+
	'<tr>'+
	'<td  id="tabla_deducibles" colspan=3 style="background: rgb(255, 255, 255);"><table  style="background: rgb(255, 255, 255); ">'+
	'<td><center><label style=" font-weight: lighter;">% Suma Asegurada:</label></center></td>'+
	'<td><center><input type="number" onkeypress="return justNumbers(event);" name="porcentaje_suma_asegurada" id="porcentaje_suma_asegurada" style="width:70px;" class="datosVehiculo cambioVehiculo"  onBlur="validarSumaAsegurada();"></center></td>'+
	'<td><center><label style=" font-weight: lighter;">Monto Fijo:</label></center></td>'+
	'<td><center><input type="number" onkeypress="return justNumbers(event);" name="monto_fijo" id="monto_fijo" style="width:70px;" class="datosVehiculo cambioVehiculo"></center></td>'+
	'<td><center><label style=" font-weight: lighter;">% Valor Siniestro:</label></center></td>'+
	'<td><center><input type="number" onkeypress="return justNumbers(event);" name="valor_siniestro" id="valor_siniestro" style="width:70px;" onBlur="validarPorcentajeSiniestro();" class="datosVehiculo cambioVehiculo"></center></td>'+
	'</table></td>'+
	'<td id="tabla_deduciblesDT" style="display:none;" colspan=3><table style="background: rgb(255, 255, 255);">'+
	'<tbody>'+
	'<td><label style=" font-weight: lighter;">% Suma Asegurada:</label></td>'+
	'<td><input type="number" onkeypress="return justNumbers(event);" name="porcentaje_suma_aseguradaDT" id="porcentaje_suma_aseguradaDT" style="width:70px;" class="datosVehiculo cambioVehiculo"></td>'+
	'</tbody>'+
	'</table></td>'+
	'</tr>'+
	
	
	'<table id="" style="border: 1px solid rgb(66, 139, 202); border-radius:5px; text-align:center;" ><tbody><tr>'+
	'<td width="10%" style="color: rgb(255, 255, 255); background: rgb(0, 61, 165);"><center><label>Paquetes</label></center></td>'+
	'<td width="18%"  style="background: rgb(255, 255, 255);">'+
	'<div id="div_paquete_black_">'+
	'<label><input type="checkbox" name="paquete1_check" id="paquete1_check" class="paquete cambioVehiculoClick" onclick="habilitarDeshabilitarCheck(\'\',event);">&nbsp;&nbsp; Black</label>'+
	'<input type="button" style="display:none" name="paquete1_button" id="paquete1_button_unico" value="Detalle" class="btn btn-primary" data-toggle="modal"  onclick="mostrarPaqueteCobertura(\'\',\'black\'); calcularValoresCoberturas(event,\'\');" data-target="#modalPaquete1_">'+
	'<div class="modal" id="modalPaquete1_" tabindex="-1" role="dialog" aria-hidden="true" style="text-align:left;">'+
	'<div class="modal-dialog">'+
	'<div class="modal-content">'+
	'<div class="modal-header">'+                  	        
	'<h4 class="modal-title">Paquete Black</h4>'+
	'</div>'+
	'<div class="modal-body">'+
	'<div id="paquete1_tabla_"></div>'+     
	'<div class="modal-footer">'+
	'<div id="valor_total_paquete_Black_"></div>'+
	'<a class="btn btn-default" data-dismiss="modal">Cerrar</a>  &nbsp;&nbsp;'+
	'</div>'+	
	'</div>'+                  	      
	'</div>'+
	'</div>'+
	'</div>'+ 
	'</td>'+
	'<td width="18%"  style="background: rgb(255, 255, 255);">'+
	'<div id="div_paquete_blue_">'+		
	'<label><input type="checkbox" name="paquete2_check" id="paquete2_check" class="paquete cambioVehiculoClick" onclick="habilitarDeshabilitarCheck(\'\',event);">&nbsp;&nbsp; Blue</label>'+
	'<input type="button" style="display:none" name="paquete2_button" id="paquete2_button_unico" value="Detalle" class="btn btn-primary" data-toggle="modal" onclick="mostrarPaqueteCobertura(\'\',\'blue\');  calcularValoresCoberturas(event,\'\');" data-target="#modalPaquete2_">'+
	'<div class="modal" id="modalPaquete2_" tabindex="-1" role="dialog" aria-hidden="true" style="text-align:left;">'+
	'<div class="modal-dialog">'+
	'<div class="modal-content">'+
	'<div class="modal-header">'+                  	        
	'<h4 class="modal-title">Paquete Blue</h4>'+
	'</div>'+
	'<div class="modal-body">'+
	'<div id="paquete2_tabla_"></div>'+    
	'<div class="modal-footer">'+		
	'<div id="valor_total_paquete_Blue_"></div>'+                  	         
	'<a class="btn btn-default" data-dismiss="modal">Cerrar</a>  &nbsp;&nbsp;'+
	'</div>'+			                  	  		         	    	
	'</div>'+                  	      
	'</div>'+
	'</div>'+
	'</div>'+       
	'</div>'+
	'</td>'+
	'<td width="18%" style="background: rgb(255, 255, 255);">'+
	'<div id="div_paquete_gold_">'+
	'<label><input type="checkbox" name="paquete3_check" id="paquete3_check" class="paquete cambioVehiculoClick" onclick="habilitarDeshabilitarCheck(\'\',event);">&nbsp;&nbsp; Gold</label>'+
	'<input type="button" style="display:none" name="paquete3_button" id="paquete3_button_unico" value="Detalle" class="btn btn-primary" data-toggle="modal" onclick="mostrarPaqueteCobertura(\'\',\'gold\');  calcularValoresCoberturas(event,\'\');" data-target="#modalPaquete3_">'+
	'<div class="modal" id="modalPaquete3_" tabindex="-1" role="dialog" aria-hidden="true" style="text-align:left;">'+
	'<div class="modal-dialog">'+
	'<div class="modal-content">'+
	'<div class="modal-header">'+                  	        
	'<h4 class="modal-title">Paquete Gold</h4>'+
	'</div>'+
	'<div class="modal-body">'+
	'<div id="paquete3_tabla_"></div>'+  
	'<div class="modal-footer">'+		    
	'<div id="valor_total_paquete_Gold_"></div>'+              	         
	'<a class="btn btn-default" data-dismiss="modal">Cerrar</a>  &nbsp;&nbsp;'+
	'</div>'+		                  	  		         	    	
	'</div>'+                  	      
	'</div>'+
	'</div>'+
	'</div>'+
	'</div>'+
	'</td>'+
	'<td width="18%" style="background: rgb(255, 255, 255);">'+
	'<div id="div_paquete_platinum_">'+		
	'<label><input type="checkbox" name="paquete4_check" id="paquete4_check" class="paquete cambioVehiculoClick" onclick="habilitarDeshabilitarCheck(\'\',event);">&nbsp;&nbsp; Platinum</label>'+
	'<input type="button" style="display:none" name="paquete4_button" id="paquete4_button_unico" value="Detalle" class="btn btn-primary" data-toggle="modal" onclick="mostrarPaqueteCobertura(\'\',\'platinum\');  calcularValoresCoberturas(event,\'\');" data-target="#modalPaquete4_">'+
	'<div class="modal" id="modalPaquete4_" tabindex="-1" role="dialog" aria-hidden="true" style="text-align:left;">'+
	'<div class="modal-dialog">'+
	'<div class="modal-content">'+
	'<div class="modal-header">'+                  	        
	'<h4 class="modal-title">Paquete Platinum</h4>'+
	'</div>'+
	'<div class="modal-body">'+
	'<div id="paquete4_tabla_"></div>'+  
	'<div class="modal-footer">'+	
	'<div id="valor_total_paquete_Platinum_"></div>'+	                  	         
	'<a class="btn btn-default" data-dismiss="modal">Cerrar</a>  &nbsp;&nbsp;'+
	'</div>'+		                  	  		         	    	
	'</div>'+                  	      
	'</div>'+
	'</div>'+
	'</div>'+ 
	'</div>'+
	'</td>'+
	'<td width="18%" style="background: rgb(255, 255, 255);">'+
	'<div>'+
	'<label><input type="checkbox" name="paquete5_check" id="paquete5_check" class="paquete cambioVehiculoClick" onclick="habilitarDeshabilitarCheck(\'\',event);">&nbsp;&nbsp; Sin Paquete</label>'+
	'<input type="button"style="display:none" name="paquete5_button" id="paquete5_button_unico" value="Detalle" class="btn btn-primary" data-toggle="modal" onclick="mostrarPaqueteCobertura(\'\',\'sin\');  calcularValoresCoberturas(event,\'\');" data-target="#modalPaquete5_">'+
	'<div class="modal" id="modalPaquete5_" tabindex="-1" role="dialog" aria-hidden="true" style="text-align:left;">'+
	'<div class="modal-dialog">'+
	'<div class="modal-content">'+
	'<div class="modal-header">'+                  	        
	'<h4 class="modal-title">Sin Paquete</h4>'+
	'</div>'+
	'<div class="modal-body">'+
	'<div id="paquete5_tabla_"></div>'+  
	'<div class="modal-footer">'+	
	'<div id="valor_total_paquete_Sin_"></div>'+                  	         
	'<a class="btn btn-default" data-dismiss="modal">Cerrar</a>  &nbsp;&nbsp;'+
	'</div>'+		                  	  		         	    	
	'</div>'+                  	      
	'</div>'+
	'</div>'+
	'</div>'+ 
	'</div>'+
	'</td>'+	
	'</tr></tbody></table>';
	
	unicoCoberturas=unicoCoberturas.replace(/numero/g, 'unico');
	
	//se desabilita el campo de km recorridos
	//var num_vehiculos = $('#numero_vehiculos').val();
	var numero;
	var grupoPorProductoId = 0;
	
	//if(num_vehiculos == 0){
		if($("#tipo_objeto").val() !="VHDinamico")
			grupoPorProductoId=Number($('#codigoProductos').val());
		else
			grupoPorProductoId=148;
		
		//vehiculo_clone = vehiculo_clone.replace(/numero/g, '1'); 
		//$('#lista_vehiculos').html(vehiculo_clone);
		//$("#numero_vehiculos").val("1");
		//numero = 1;
		//cargaTipoExtra(1);
		
		if(!casoEspecial){
			var clase=$(".div_vehiculos").attr("class");
			$(".div_coberturas").attr("style",$(".div_coberturas").attr("style")+";display:none");
			//$(".div_vehiculos").attr("class",(clase.replace("9","12")));
			//if(numero==1)
			$("#coberturas_unico").html(unicoCoberturas);
		}
		//patoargu      		    	cargarTipoVehiculo("", numero);
		//patoargu      		    	cargarTipoUso("", numero);
		//patoargu      		    	cargarTablaCoberturas("",numero,"","");
		if(casoEspecial)
		cargarTablaCoberturas("",numero,"","",grupoPorProductoId);
		if($("#tipo_objeto").val()!="VHDinamico")
			$("#coberturaTR_check"+ numero).prop("checked",true);
		//$("#extrasVehiculo"+numero).attr("disabled","true");
		//$("#km_recorridos"+numero).attr("disabled","true");		

	//}else{
		//var nuevo_num_vehiculos = parseInt(num_vehiculos) +1;
		//var num_anterior = nuevo_num_vehiculos -1;
		//var marcaModelo = $('#marca_modelo'+num_anterior).val();
		//var sucursal = $('#sucursales'+num_anterior).val();
		//var anoFabricacion = $('#anio_fabricacion'+num_anterior).val();
		//var sumaAsegurada = $('#suma_asegurada_'+num_anterior).val();
		//var dispositivoRastreo = $('#disposito_rastreo'+num_anterior).val();
		//var ceroKilometros =	$('#cero_kilometros'+num_anterior).val();
		/*if (marcaModelo =="" || sucursal =="" || anoFabricacion =="" || sumaAsegurada =="" || dispositivoRastreo ==""  )
		{
			return $("#wizard").valid();
		}
		else{ 

			//vehiculo_clone = vehiculo_clone.replace(/numero/g, nuevo_num_vehiculos);     
			//$('#clonar_vehiculo'+num_anterior).before(vehiculo_clone); 
			//$("#extrasVehiculo"+numero).attr("disabled","true");
			//$("#km_recorridos"+numero).attr("disabled","true");

			// agregamos los valores de los combos dinamicamente 
			if(nuevo_num_vehiculos>1){
				cargaInicial(nuevo_num_vehiculos);
				cargarDatosVehiculo(nuevo_num_vehiculos);
			}      			 	      			   
			//$("#numero_vehiculos").val(nuevo_num_vehiculos);
			numero = nuevo_num_vehiculos;
			cargaTipoExtra(nuevo_num_vehiculos);
			cargarTipoVehiculo("",nuevo_num_vehiculos);
			cargarTipoUso("",nuevo_num_vehiculos);
			if(casoEspecial)
			cargarTablaCoberturas("",nuevo_num_vehiculos,"","",grupoPorProductoId);      	      		      	      		
		}*/
		
		if(!casoEspecial){
			//var clase=$(".div_vehiculos").attr("class");
			$(".div_coberturas").hide();
			//$(".div_vehiculos").attr("class",(clase.replace("9","12")));
		}
		
	//}     
	/*$(".cambioVehiculo").change(function(){
		editoVehiculo=true;

	});*/

	/*$(".cambioVehiculoClick").click(function(){
		editoVehiculo=true;
	});*/

	/*$(".cambioVehiculo").on('select2-blur', function(e) {
		editoVehiculo=true;

	});*/
	
	if($("#estado_cotizacion").val()=="Emitido")
		bloquearEmitido();
}

/*
 * METODO QUE HABILITA Y DESHABILITA LOS PAQUETES
 */	
function habilitarDeshabilitarCheck(valor,event){
	var target = event.target || event.srcElement;
	
	if(valor!=""){
		var numeroVeces = Number(valor);
		if($(target).is(":checked"))
			$(target).parent().next().children().first().click();


		/* $('.paquete'+numeroVeces.toString()).on('change', function() {
  		    	$('.paquete'+numeroVeces.toString()).not(this).prop('checked', false);  
  			});*/
		$('.paquete'+numeroVeces.toString()).not(target).prop('checked', false);
	}else{
		var idBoton=$(target).attr("id").replace("check","button_unico");
		if($(target).is(":checked"))
					$("#"+idBoton).click();
		$('.paquete').not(target).prop('checked', false);
	}
}

/*
 * METODO QUE MUESTRA LAS COBERTURAS AGRUPADAS POR PAQUETE
 */    
function mostrarPaqueteCobertura(numero,paquete){   
	var numeroVeces = Number(numero);
	var paqueteValor = paquete.toString();
	//cargarTablaCoberturas("",numero,"",paquete);

	var coberturaTablaPaqueteBlack =  $("#coberturaTablaPaqueteBlack").html().replace(/numero/g,numero);
	var coberturaTablaPaqueteBlue =  $("#coberturaTablaPaqueteBlue").html().replace(/numero/g,numero);
	var coberturaTablaPaqueteGold =  $("#coberturaTablaPaqueteGold").html().replace(/numero/g,numero);
	var coberturaTablaPaquetePlatinum =  $("#coberturaTablaPaquetePlatinum").html().replace(/numero/g,numero);
	var coberturaTablaSinPaquete =  $("#coberturaTablaSinPaquete").html().replace(/numero/g,numero);

	if(paqueteValor == "black"&&$('#paquete1_tabla_'+numero).html().trim()==""){
		$('#paquete1_tabla_'+numero).empty().html(coberturaTablaPaqueteBlack);
	}

	if(paqueteValor == "blue"&&$('#paquete2_tabla_'+numero).html().trim()==""){
		$('#paquete2_tabla_'+numero).empty().html(coberturaTablaPaqueteBlue);
	}

	if(paqueteValor == "gold"&&$('#paquete3_tabla_'+numero).html().trim()==""){
		$('#paquete3_tabla_'+numero).empty().html(coberturaTablaPaqueteGold);
	}

	if(paqueteValor == "platinum"&&$('#paquete4_tabla_'+numero).html().trim()==""){
		$('#paquete4_tabla_'+numero).empty().html(coberturaTablaPaquetePlatinum);      
	}

	if(paqueteValor == "sin"&&$('#paquete5_tabla_'+numero).html().trim()==""){
		$('#paquete5_tabla_'+numero).empty().html(coberturaTablaSinPaquete);      
	}
}

/*
 * METODO PARA CALCULAR LOS VALORES DE LAS COBERTURAS
 */
function calcularValoresCoberturas(event,numero){
	var target = event.target || event.srcElement;
	var tiempoVigencia = Number($("#vigencia").val());
	if(numero!=""){
	var paquete=$(target).parent().parent().children().first().children().first().html();
	if(paquete=="Sin Paquete")
		paquete="Sin";

	var coberturas=$(".valor_"+paquete+"_Cobertura_tabla_"+Number(numero));
	var suma=0;
	var sumas=$(".sumaAcumulada");
	var totalPaquete=0;
	var extras=$("#sumaAcumuladaExtras"+numero);
	var valorExtras=0;
	
	
	$(extras).each(function(index,element){
		valorExtras=Number(valorExtras)+Number($(element).val());
	});
	
	$(sumas).each(function(index,element){
		suma=Number(suma)+Number($(element).val());
	});
	suma=Number(suma)+Number(valorExtras);
	
	var idsAgregadas=[];
	for(var index=coberturas.length/2;index<coberturas.length;index++){
		var element=coberturas[index];
		
		var tasa=$(element).next();
		var tipoTasa=$(tasa).attr("class");
		var valorTasa=$(tasa).val();
		var idCobertura=$(element).attr('id').split('_')[3];
			
		if(idCobertura== '6348540415022' || idCobertura == '6349173767914'){
			var valorExcesoRC=Number($(element).next().next().children().first().val())*sumas.length*tiempoVigencia;
			totalPaquete+=Number(valorTasa);
		}
		else{
			if(tipoTasa=='porcentual'){
				$(element).html("$"+(tiempoVigencia*Math.round((valorTasa*suma/100)* 100) / 100));
				totalPaquete+=Number(valorTasa);
			}
			else{
				$(element).html("$"+(tiempoVigencia*Math.round((valorTasa)* 100) / 100)*sumas.length);
				totalPaquete+=Number(valorTasa);
			}
		}
		
	}
	if(paquete!="Sin")
		$("#valor_total_paquete_"+paquete+"_"+numero).html('Valor del Paquete: $'+totalPaquete);
	calcularTotalSinPaquete(numero);
	}
	else{
		var paquete=$(target).prev().html().split("&nbsp;&nbsp;")[1].trim();
		if(paquete=="Sin Paquete")
			paquete="Sin";

		var coberturas=$(".valor_"+paquete+"_Cobertura_tabla_");
		var suma=0;
		var sumas=$(".sumaAcumulada");
		var totalPaquete=0;
		var extras=$(".sumaAcumuladaExtras");
		var valorExtras=0;
		
		$(extras).each(function(index,element){
			valorExtras=Number(valorExtras)+Number($(element).val());
		});
		
		$(sumas).each(function(index,element){
			suma=Number(suma)+Number($(element).val());
		});
		
		suma=Number(suma)+Number(valorExtras);
		var idsAgregadas=[];
		
		for(var index=coberturas.length/2;index<coberturas.length;index++){
			var element=coberturas[index];
			var tasa=$(element).next();
			var tipoTasa=$(tasa).attr("class");
			var valorTasa=$(tasa).val();
			var idCobertura=$(element).attr('id').split('_')[3];
			
		//	if($.inArray(idCobertura,idsAgregadas)==-1){
			//	idsAgregadas[index]=idCobertura;
			
			if(idCobertura== '6348540415022' || idCobertura == '6349173767914'){
				var valorExcesoRC=tiempoVigencia*Number($(element).next().next().children().first().val())*sumas.length;
				totalPaquete+=Number(valorTasa);
			}
			else{
				if(tipoTasa=='porcentual'){
					$(element).html("$"+tiempoVigencia*Math.round((valorTasa*suma/100)* 100) / 100);
					totalPaquete+=Number(valorTasa);
				}
				else{
					$(element).html("$"+(tiempoVigencia*Math.round((valorTasa)* 100) / 100)*sumas.length);
					totalPaquete+=Number(valorTasa);
				}
			}
			/*}else{
				if(idCobertura== '6348540415022' || idCobertura == '6349173767914'){
					var valorExcesoRC=0;
					totalPaquete+=0;
				}
				else{
					if(tipoTasa=='porcentual'){
						$(element).html("$"+0);
						totalPaquete+=0;
					}
					else{
						$(element).html("$"+0);
						totalPaquete+=0;
					}
				}
			}*/
		}
		if(paquete!="Sin")
			$("#valor_total_paquete_"+paquete+"_"+numero).html('Valor del Paquete: $'+totalPaquete);
		calcularTotalSinPaquete(numero);
	}
}

function calcularTotalSinPaquete(numero){
	
	if(numero==null)
		numero="";
	var coberturas=$(".valor_Sin_Cobertura_tabla_"+numero);
	var valor=0;
	for(var i=0;i<coberturas.length;i++){
		if($($(".check_Sin_Cobertura_"+numero)[i]).is(":checked"))
			valor+=Number($(coberturas[i]).html().replace('$',''));
	}
	$("#valor_total_paquete_Sin_"+numero).html("Valor del Paquete: $"+Math.round(valor* 100) / 100);	
}

function cambioExcesoRC(id,n,event){
	var t = event.target || event.srcElement;
	var tiempoVigencia = $("#vigencia").val();
	if(n=="")
		$("#valorUnicoResponsabilidadCivil").val($(t).val());
	var target = t;
	var check=$(target).attr('check');
	var valor=$(target).parent().prev().prev();
	var sumas=$(".suma_asegurada");
	var porcentaje=$(target).parent().prev();
	var valorExcesoRC=Number($(target).val());
	var valorMax=Number($(target).attr('max'));
	if(valorExcesoRC>valorMax){
		alert("El valor no puede ser mayor a $"+valorMax);
		$(target).val(valorMax);
		$("#valorUnicoResponsabilidadCivil").val(valorMax);
	}
	//$(t).parent().prev().prev().prev().prev().prev().children().first()
	if(n!=""){
	if($("#"+check).is(":checked"))
		{
			$(valor).html('$'+(tiempoVigencia*(Number($(porcentaje).val())*valorExcesoRC/100))*sumas.length);
			calcularTotalSinPaquete(n);
		
		}	}
	else{
		if($(t).parent().prev().prev().prev().prev().prev().children().first().is(":checked"))
		{
			$(valor).html('$'+(tiempoVigencia*Number($(porcentaje).val())*valorExcesoRC/100)*sumas.length);
			calcularTotalSinPaquete(n);
		
		}
	}
}

/*
 * METODO QUE HABILITA Y DESHABILITA LAS COBERTURAS
 */
function habilitarDeshabilitarCobertura(valor, event) {
	if (valor != "") {
		var numeroVeces = Number(valor);
		var target = event.target || event.srcElement;
		var text = $(target).attr("name").split('_')[0].replace('cobertura', '');
		var checked = $(target).is(':checked');

		if ($("#tipo_objeto").val() != "VHDinamico") {
			$("#coberturaTR_check" + numeroVeces.toString()).prop("checked", true);
		}

		if (text == 'TR' && checked) {
			$('#coberturaDT_check' + numeroVeces.toString()).prop('checked', false);
			$('#coberturaRC_check' + numeroVeces.toString()).prop('checked', false);
		}

		if ((text == 'RC' || text == 'DT') && checked) {
			$('#coberturaTR_check' + numeroVeces.toString()).prop('checked', false);
		}

		if ($('#coberturaDT_check' + numeroVeces.toString()).is(':checked')) {
			$('#tabla_deduciblesDT' + numeroVeces.toString()).fadeIn();
			
		} else {
			$('#tabla_deduciblesDT' + numeroVeces.toString()).fadeOut();
			
		}

		if ($('#coberturaTR_check' + numeroVeces.toString()).is(':checked')) {
			if ($("#tipo_objeto").val() == "VHDinamico") {
				$('#tabla_deducibles' + numeroVeces.toString()).fadeIn();
			}
		} else {
			if ($("#tipo_objeto").val() == "VHDinamico")
				$('#tabla_deducibles' + numeroVeces.toString()).fadeOut();
		}
		if(!$('#coberturaDT_check'+ numeroVeces.toString()).is(':checked')&&$('#coberturaRC_check'+ numeroVeces.toString()).is(':checked')){
			$(".paquete"+ numeroVeces.toString()).attr("disabled","disabled").prop('checked', false);
		}
		else{
			$(".paquete"+ numeroVeces.toString()).removeAttr("disabled");
		}
	} else {
		var target = event.target || event.srcElement;
		var text = $(target).attr("name").split('_')[0].replace('cobertura', '');
		var checked = $(target).is(':checked');

		if ($("#tipo_objeto").val() != "VHDinamico") {
			$("#coberturaTR_check").prop("checked", true);
		}

		if (text == 'TR' && checked) {
			$('#coberturaDT_check').prop('checked', false);
			$('#coberturaRC_check').prop('checked', false);
		}

		if ((text == 'RC' || text == 'DT') && checked) {
			$('#coberturaTR_check').prop('checked', false);
		}

		if ($('#coberturaDT_check').is(':checked')) {
			$('#tabla_deduciblesDT').fadeIn();
			$('.esconderPT').fadeOut();
			var esconder=$('.esconderPT');
			for(var i=0;i<esconder.length;i++){
				$(esconder[i]).children().first().children().first().prop('checked', false);
			}
			
			$("#paquete1_check").attr("disabled","disabled").prop('checked', false);
			$("#paquete2_check").attr("disabled","disabled").prop('checked', false);
			$("#paquete3_check").attr("disabled","disabled").prop('checked', false);
			$("#paquete4_check").attr("disabled","disabled").prop('checked', false);
			$("#paquete5_check").removeAttr("disabled");
						
		} else {
			$('#tabla_deduciblesDT').fadeOut();
			$('.esconderPT').fadeIn();
			$(".paquete").removeAttr("disabled");
		}

		if ($('#coberturaTR_check').is(':checked')) {
			if ($("#tipo_objeto").val() == "VHDinamico") {
				$('#tabla_deducibles').fadeIn();
				$(".paquete").removeAttr("disabled");
				//$('#tabla_deducibles').parent().attr("colspan","3");
				//$('#tabla_deducibles').parent().removeAttr("width");
			}
		} else {
			if ($("#tipo_objeto").val() == "VHDinamico")
				$('#tabla_deducibles').fadeOut();
				//$('#tabla_deducibles').parent().attr("colspan","0");
		}
		if(!$('#coberturaDT_check').is(':checked')&&$('#coberturaRC_check').is(':checked')){
			$(".paquete").attr("disabled","disabled").prop('checked', false);
		}
		{
			
		}
	}
}

/*
 * METODO QUE COMPRUEBA SI UN VEHICULO TIENE DISPOSITIVO DE RASTERO
 */
function cambiaDispositivoRastreo(numero){
	if(casoEspecial){
	var dispositivo=$("#disposito_rastreo"+numero).val();
	if(dispositivo=='1'){
		$("#porcentaje_suma_aseguradaDT"+numero).val(5);
		$("#porcentaje_suma_aseguradaDT"+numero).attr('min',5);
	}
	if(dispositivo=='0'){
		$("#porcentaje_suma_aseguradaDT"+numero).val(15);
		$("#porcentaje_suma_aseguradaDT"+numero).attr('min',15);
	}
	}
	else{
		var rastreos = $(".rastreo ");
		var bandera=true;
		$.each(rastreos,function(index,element){
		var dispositivo=$(element).val();
		if(dispositivo=='1' && bandera){
			$("#porcentaje_suma_aseguradaDT").val(5);
			$("#porcentaje_suma_aseguradaDT").attr('min',5);
		}
		if(dispositivo=='0'){
			$("#porcentaje_suma_aseguradaDT").val(15);
			$("#porcentaje_suma_aseguradaDT").attr('min',15);
			bandera=false;
		}
		});
	}
}

/*
 * METODO QUE VALIDA EL POCENTAJE DE LA SUMA ASEGURADA
 */	
function validarSumaAsegurada(valor){
	if(valor==null)
		valor="";
	var sumaAsegurada = Number($('#porcentaje_suma_asegurada'+valor).val());
	var valorDefectoSumaAsegurada = Number($('#suma_asegurada_valor_tr').val());
	if(sumaAsegurada < (valorDefectoSumaAsegurada -Number('0.5'))){
		alert('La Suma Asegurada debe ser mayor o igual a: '+ (valorDefectoSumaAsegurada -Number('0.5')));
	}
}	

/*
 * METODO QUE VALIDA EL PORCENTAJE DEL SINIESTRO
 */    
function validarPorcentajeSiniestro(valor){
	var numeroVeces = Number(valor);
	var valorSiniestro= Number($('#valor_siniestro'+numeroVeces.toString()).val());
	var valorDefectoValorSiniestro = Number($('#siniestro_valor_tr').val());
	if(valorSiniestro < valorDefectoValorSiniestro){
		alert('El Porcentaje del siniestro debe ser mayor o igual a: '+valorDefectoValorSiniestro);
	}
}

function mostrarAdicionales() {
	var opcion = $('#tipo_identificacion_principal').val();

	if (opcion == 1 || opcion == 2 || opcion == 3) {
		$("#filaNatural").show();
		$("#filaJuridica").hide();
		$('#datosAdicionalesNatural').show();
		$('#datosAdicionalesJuridica').hide();

	} else if (opcion == 4) {
		$("#filaNatural").hide();
		$("#filaJuridica").show();
		$('#datosAdicionalesJuridica').show();
		$('#datosAdicionalesNatural').hide();

	} else {
		$('#datosAdicionalesNatural').hide();
		$('#datosAdicionalesJuridica').hide();
	}
}

function cargarDatosVehiculo(valor, anio, antiguedad, edad, hijos, zona, dispositivo, cero_kilometros, genero, estado_civil, guarda_garage, combustible, sucursal, marca, modelo, color, tipoUso, tipoVehiculo, tipoAdquisicion, tasaProductoId) {
	var numeroVeces = Number(valor);
	var options = '';
	var anoFabricacion = '';
	var anoActual = '';
	var fechaActual = new Date();

	// Consultar las variables del sistema
	$.ajax({
		url : '../VariableSistemaController',
		data : {
			"tipoConsulta" : "encontrarTodosVariable",
			"producto" : "productoVehiculo",
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			//COTROL EMISION
			bandera = false;
			if (data.permite_emision == "0") {
				$("#confirmarDatos").parent().parent().html("<td colspan='2'><b>LA EMISION NO ESTA DISPONIBLE POR PROCESOS DE CIERRE DE MES<b></td>").next().remove();
				$("#pendientePorEmitir").show();
				$("#confirmarDatos").remove();
				$("#emision_poliza").remove();
				bandera = true;
			}
			else{
				$("#pendientePorEmitir").hide();
			}
			
			if(!bandera && !data.usario_emite){
				$("#confirmarDatos").parent().parent().next().remove();
				$("#confirmarDatos").remove();
				$("#emision_poliza").remove();
				$("#pendientePorEmitir").remove();
			}
			
			anoFabricacion = Number(data.ano_fabricacion);
			$("#anio_max_seguro").val(anoFabricacion);
			if (fechaActual.getMonth() >= 2)
				anoActual = Number(fechaActual.getFullYear() + 1);
			else
				anoActual = Number(fechaActual.getFullYear());

			options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
			//for (var i = anoFabricacion; i < anoActual + 1; i++) {
			for (var i = anoActual; i >= anoFabricacion + 1; i--) {
				options += '<option value="' + i + '">' + i + '</option>';
			}
			$("#anio_fabricacion" + numeroVeces.toString()).html(options).val(anio).attr('required', 'required');
			options = '';
			var antiguedadVh = '';
			antiguedadVh = Number(data.antiguedad_vh);
			// Antiguedad Vehiculo
			options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
			for (var i = 0; i <= antiguedadVh; i++) {
				if (i == 0)
					options += '<option value="' + i + '"> Menor de 1 a&ntilde;o</option>';
				else if (i == 1)
					options += '<option value="' + i + '">' + i + ' a&ntilde;o</option>';
				else
					options += '<option value="' + i + '">' + i + ' a&ntilde;os</option>';
			}
			$("#antiguedad_vh" + numeroVeces.toString()).html(options).val(antiguedad);
			//calcularAntiguedad(numeroVeces);

			// Edad del conductor
			options = '';
			var edadMaxima = '';
			var edadMinima = '';
			edadMinima = Number(data.edad_minima);
			edadMaxima = Number(data.edad_maxima);

			options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
			for (var i = edadMinima; i < edadMaxima + 1; i++) {
				options += '<option value="' + i + '">' + i + '</option>';
			}
			$("#conductor_edad" + numeroVeces.toString()).html(options).val(edad);

			// Numero de hijos
			options = '';
			var numeroHijos = Number(data.maximo_numero_hijos);
			options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
			for (var i = 0; i < numeroHijos; i++) {
				if (i == 0) {
					options += '<option value="' + i + '">Ninguno</option>';
				} else {
					options += '<option value="' + i + '">' + i + '</option>';
				}
			}

			$("#valor_hijos" + numeroVeces.toString()).html(options).val(hijos);

			options = '';

			// Agregamos las coberturas por defecto
			if (casoEspecial) {
				if ($("#porcentaje_suma_asegurada" + numeroVeces.toString()).val() == "")
					$("#porcentaje_suma_asegurada" + numeroVeces.toString()).val(Number(data.porcentaje_suma_asegurada) * Number('100'));

				if ($("#porcentaje_suma_aseguradaDT" + numeroVeces.toString()).val() == "")
					$("#porcentaje_suma_aseguradaDT" + numeroVeces.toString()).val(Number(data.porcentaje_suma_asegurada) * Number('100'));

				if ($("#monto_fijo" + numeroVeces.toString()).val() == "")
					$("#monto_fijo" + numeroVeces.toString()).val(data.monto_fijo);

				if ($("#valor_siniestro" + numeroVeces.toString()).val() == "")
					$("#valor_siniestro" + numeroVeces.toString()).val(Number(data.porcentaje_siniestro) * Number('100'));
			} else {
				if ($("#porcentaje_suma_asegurada").val() == "")
					$("#porcentaje_suma_asegurada").val(Number(data.porcentaje_suma_asegurada) * Number('100'));

				if ($("#porcentaje_suma_aseguradaDT").val() == "")
					$("#porcentaje_suma_aseguradaDT").val(Number(data.porcentaje_suma_asegurada) * Number('100'));

				if ($("#monto_fijo").val() == "")
					$("#monto_fijo").val(data.monto_fijo);

				if ($("#valor_siniestro").val() == "")
					$("#valor_siniestro").val(Number(data.porcentaje_siniestro) * Number('100'));
			}

			// Agregamos los valores por defecto de las variables (una sola vez)
			if (numeroVeces == 1) {
				$("#suma_asegurada_valor_tr").val(Number(data.porcentaje_suma_asegurada) * Number('100'));
				$("#monto_fijo_valor_tr").val(data.monto_fijo);
				$("#siniestro_valor_tr").val(Number(data.porcentaje_siniestro) * Number('100'));
			}

		}

	});
	options = '';
	// Agregamos zonas
	options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	options += '<option value="U">&nbsp;&nbsp;Urbana </option>';
	options += '<option value="R">&nbsp;&nbsp;Rural </option>';
	$("#zona" + numeroVeces.toString()).html(options).val(zona);
	options = '';
	// Agregamos el rastreo
	options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	options += '<option value="0">&nbsp;&nbsp;NO </option>';
	options += '<option value="1">&nbsp;&nbsp;SI </option>';
	$("#disposito_rastreo" + numeroVeces.toString()).html(options).val(dispositivo);
	options = '';
	// Agregamos cero kilometros
	options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	options += '<option value="0">&nbsp;&nbsp;NO </option>';
	options += '<option value="1">&nbsp;&nbsp;SI </option>';
	$("#cero_kilometros" + numeroVeces.toString()).html(options).val(cero_kilometros);
	//ceroKilometros(numeroVeces);
	options = '';
	// Agregamos genero
	options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	options += '<option value="M">&nbsp;&nbsp;Masculino </option>';
	options += '<option value="F">&nbsp;&nbsp;Femenino </option>';
	$("#conductor_genero" + numeroVeces.toString()).html(options).val(genero);
	options = '';
	// Agregamos estado civil
	options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	options += '<option value="S">&nbsp;&nbsp;Soltero </option>';
	options += '<option value="C">&nbsp;&nbsp;Casado </option>';
	options += '<option value="D">&nbsp;&nbsp;Divorciado </option>';
	options += '<option value="U">&nbsp;&nbsp;Uni&oacute;n Libre </option>';
	options += '<option value="V">&nbsp;&nbsp;Viudo </option>';
	$("#conductor_estado_civil" + numeroVeces.toString()).html(options).val(estado_civil);
	options = '';
	// Agregamos guarda en garage
	options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	options += '<option value="0">&nbsp;&nbsp;NO </option>';
	options += '<option value="1">&nbsp;&nbsp;SI </option>';
	$("#guarda_garage" + numeroVeces.toString()).html(options).val(guarda_garage);
	options = '';
	// Agregamos cobustible
	options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	options += '<option value="G">&nbsp;&nbsp;Gasolina </option>';
	options += '<option value="D">&nbsp;&nbsp;Diesel </option>';
	options += '<option value="E">&nbsp;&nbsp;Gasolina/Electricidad</option>';
	$("#combustible" + numeroVeces.toString()).html(options).val(combustible);
	options = '';

	// Agregamos Tipo de Adquisicion para algunos tipos de vehiculos cerrados
	options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	options += '<option value="Contado">&nbsp;&nbsp;Contado </option>';
	options += '<option value="Financiado">&nbsp;&nbsp;Financiado </option>';
	options += '<option value="Credito">&nbsp;&nbsp;Cr&eacute;dito</option>';
	$("#tipo_adquisicion" + numeroVeces.toString()).html(options).val(tipoAdquisicion);
	options = '';

	//cargarSucursales(sucursal, numeroVeces.toString());
	//cargarMarcas(marca, numeroVeces.toString());
	//cargarColores(color, numeroVeces.toString());
	//cargarModelos(modelo, marca, numeroVeces.toString());
	//cargarTipoUso(tipoUso, numeroVeces.toString());
	//cargarTipoVehiculo(tipoVehiculo, numeroVeces.toString(), tasaProductoId);
}

//Metodo para crear una cotizacion cerrados livianos                    
function crearCotizacion()
{

	var pppv = $('#punto_venta').find(":selected").attr('pxpv');
	//alert(pppv);
	$.ajax({
		url: "../CotizacionController",
		data: { 
			"tipoConsulta": "crear",
			"producto": "productoVehiculo",
			"grupoProductoId" : $("#grupo_productos").val(),
			"puntoVentaId": $("#punto_venta").val(),
			"vigenciaPoliza": $("#vigencia").val(),
			"tipoIdentificacion": $("#tipo_identificacion_principal").val(),
			"productoXPuntoDeVenta": pppv,
			"identificacion": $("#identificacion").val(),
			"nombres": $("#nombres").val(),
			"apellidos": $("#apellidos").val(),
			"nombreCompleto": $("#nombre_completo").val(),
			"agenteId": $("#agentes").val(),
			"codigoEntidadEnsurance": $("#codigoEntidadEnsurance").val(),
			"codigoClienteEnsurance": $("#codigoClienteEnsurance").val(),
			"cotizacionId": $("#cotizacion_id").text(),
			"porcentajeComisionAgente": $("#porc_comision").val(),			            	            					
			"tipoObjeto" : tipoObjeto,
			"grupoPorProductoId" : $("#productos").val(),
			"tasaProductoId" : $("#tasas").val(),
			"tasaProductoValor" : $("#tasa").val(),
			
		},
		type: 'post',          
		datatype:'json',
		success: function(data) {
			var cotizacionId = data.cotizacionId;
			if(cotizacionId !=undefined){
			$("#cotizacion_id").text(cotizacionId);
			$("#punto_venta").attr("unidadNegocio", data.unidadNegocioId);			
			// Validacion poner en la URL el id de la cotizacion
			var valorId = getParameterByName("id");
			if(valorId != null){
				$.pushVar("id", data.cotizacionId, "", window.location.href);
			}
			}else{
				alert(data.error);
				return false;
			}
		}
	});
}