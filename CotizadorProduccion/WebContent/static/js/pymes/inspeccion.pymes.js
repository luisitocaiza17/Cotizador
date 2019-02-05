var nombreArchivo = "";
var idCotizacion="";

function cargarInicalInspeccion(){
	
	$("#tabstrip").kendoTabStrip({
	        select: onSelect
	});
	
	$("#fecha_inspeccion").kendoDatePicker({
		format: "yyyy-MM-dd"
	});
	
	$("#numero_total_pisos").kendoNumericTextBox({
		 format: "#",
	     decimals: 0
	});
	 
	$("#anio_construccion").kendoNumericTextBox({
	     format: "#",
	     decimals: 0
	});

	
 
	var QueryString = function () {
		// This function is anonymous, is executed immediately and
		// the return value is assigned to QueryString!
		var query_string = {};
		var query = window.location.search.substring(1);
		var vars = query.split("&");
		for (var i = 0; i < vars.length; i++) {
			var pair = vars[i].split("=");
			// If first entry with this name
			if (typeof query_string[pair[0]] === "undefined") {
				query_string[pair[0]] = pair[1];
				// If second entry with this name
			} else if (typeof query_string[pair[0]] === "string") {
				var arr = [query_string[pair[0]], pair[1]];
				query_string[pair[0]] = arr;
				// If third or later entry with this name
			} else {
				query_string[pair[0]].push(pair[1]);
			}
		}
		return query_string;
	}
	();
	
	
	var idCotizacion2="../PymesObjetoCotizacionController?valor="+QueryString.id;
	$("#files").kendoUpload({
		    
			async : {
				saveUrl : idCotizacion2,
				removeUrl: idCotizacion2
			},
			multiple : false,
			upload : onUpload,
			success : onSuccess
		    //cancel: onCancel
	});
	
	
	if (QueryString.id != null && QueryString != ''){
		idCotizacion="../PymesObjetoCotizacionController?valor="+QueryString.id;
		$("#cotizacion_id").text(QueryString.id);
	}
	
	//Consultar provincias
	cargarProvincias("1");

	cargarMaterialesConstruccion();
	
	cargarTiposOcupacion();
	
	// eventos
	$("#ubicacion_provincia_1").change(function (e) {
		obtenerCantonPorProvincia('',"1");
	});
	
	cargarPorCotizacionId();
}

function onUpload(e) {
	// Array with information about the uploaded files
	var files = e.files;
	// Check the extension of each file and abort the upload if it is not .jpg
	/*$.each(files, function() {
		if (this.extension.toLowerCase() != ".html") {
			alert("Por favor seleccionar el archivo correcto.");
			e.preventDefault();
		}
	});*/
}

//Carga de archivos masivo
function onSuccess(e) {
	if (e.operation == "upload") {
		nombreArchivo = e.files[0].name;
		$('#btnImportar').show();
	} else {
		nombreArchivo = "";
		$('#btnImportar').hide();
	}
}

function cargarInicalInspeccionRealizadas(){
	
	$("#tabstrip").kendoTabStrip({
	        select: onSelect
	});
	 
	 $("#numero_total_pisos").kendoNumericTextBox({
		 format: "#",
	     decimals: 0
	 });
	 
	 $("#anio_construccion").kendoNumericTextBox({
	     format: "#",
	     decimals: 0
	 });
	 
	 $("#fecha_inspeccion").kendoDatePicker({
			format: "yyyy-MM-dd"
	 });
 
	var QueryString = function () {
		// This function is anonymous, is executed immediately and
		// the return value is assigned to QueryString!
		var query_string = {};
		var query = window.location.search.substring(1);
		var vars = query.split("&");
		for (var i = 0; i < vars.length; i++) {
			var pair = vars[i].split("=");
			// If first entry with this name
			if (typeof query_string[pair[0]] === "undefined") {
				query_string[pair[0]] = pair[1];
				// If second entry with this name
			} else if (typeof query_string[pair[0]] === "string") {
				var arr = [query_string[pair[0]], pair[1]];
				query_string[pair[0]] = arr;
				// If third or later entry with this name
			} else {
				query_string[pair[0]].push(pair[1]);
			}
		}
		return query_string;
	}
	();
	
	if (QueryString.id != null && QueryString != '')
		$("#cotizacion_id").text(QueryString.id);
	
	//Consultar provincias
	cargarProvincias("1");

	cargarMaterialesConstruccion();
	
	cargarTiposOcupacion();
	
	// eventos
	$("#ubicacion_provincia_1").change(function (e) {
		obtenerCantonPorProvincia('',"1");
	});
	
	cargarInspecciones();
}

function onSelect(){
	initialize();
}

function cargarProvincias(identificador) {
	$("#ubicacion_provincia_"+identificador).empty();
	//Consultar la provincia
	$.ajax({
		url : '../ProvinciaController',
		data : {
			"tipoConsulta" : "encontrarTodos"
		},
		async: false,
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var listadoProvincias = data.listadoProvincia;			
			$("#ubicacion_provincia_"+identificador).append("<option value=''>Seleccione una provincia</option>");
			$.each(listadoProvincias, function (index) {
				$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
			});
		}
	});
}

function cargarMaterialesConstruccion() {
	$("#tipo_construccion").empty();
	//Consultar la provincia
	$.ajax({
		url : '../MaterialConstruccionController',
		data : {
			"tipoConsulta" : "encontrarTodos"
		},
		async: false,
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var listadoTipos = data.listadoTipoConstruccion;			
			$("#tipo_construccion").append("<option value=''>Seleccione un tipo de construcci&oacute;n</option>");
			$.each(listadoTipos, function (index) {
				$("#tipo_construccion").append("<option value='" + listadoTipos[index].codigo + "'>" + listadoTipos[index].nombre + "</option>");
			});
		}
	});
}

function cargarTiposOcupacion() {
	$("#tipo_ocupacion").empty();
	//Consultar la provincia
	$.ajax({
		url : '../TipoOcupacionController',
		data : {
			"tipoConsulta" : "encontrarTodos"
		},
		async: false,
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var listadoTipos = data.listadoTipoOcupacion;			
			$("#tipo_ocupacion").append("<option value=''>Seleccione un tipo de ocupaci&oacute;n</option>");
			$.each(listadoTipos, function (index) {
				$("#tipo_ocupacion").append("<option value='" + listadoTipos[index].codigo + "'>" + listadoTipos[index].nombre + "</option>");
			});
		}
	});
}

function obtenerCantonPorProvincia(seleccionada, codigoDireccion) {
	$("#ubicacion_canton_"+codigoDireccion).empty();
	// Consultar la provincia
	$.ajax({
		url : '../CantonController',
		data : {
			"tipoConsulta" : "encontrarPorProvincia",
			"provincia" : $("#ubicacion_provincia_"+codigoDireccion).val()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			var listadoCantones = data.cantones;
			$("#ubicacion_canton_"+codigoDireccion).append("<option value=''>Seleccione un Canton</option>");
			$.each(listadoCantones, function(index) {
				var id = undefined;
				if (listadoCantones[index].codigo == undefined) {
					id = listadoCantones[index].id;
				} else {
					id = listadoCantones[index].codigo;
				}
				$("#ubicacion_canton_"+codigoDireccion).append("<option value='" + id + "'>" + listadoCantones[index].nombre + "</option>");
			});
			$("#ubicacion_canton_"+codigoDireccion).val(seleccionada);
		}
	});
}

function limpiarControles(){
	$("#tiene_extintores").prop("checked", false);
	$("#tiene_detector_humo").prop("checked", false);
	$("#tiene_spinklers").prop("checked", false);
	$("#tiene_alarma_monitoreada").prop("checked", false);
	$("#tiene_guardiania").prop("checked", false);
	$("#tiene_otros").val("");
	$("#txt_Latitud").val("");
	$("#txt_Longitud").val("");
	$("#anio_construccion").val("");
	$("#fecha_inspeccion").val("");
}

function obtenerConfiguracionDireccion(numeroDireccion) {
	limpiarControles();
	$("#cotizacionDetalleId").val(numeroDireccion);
	$.ajax({
		url : '../PymesObjetoCotizacionController',
		data : {
			"tipoConsulta" : "obtenerConfiguracionPorDetalleId",
			"cotizacionDetalleId" : $("#cotizacionDetalleId").val(),
			"cotizacionId" : $("#cotizacion_id").text()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			$("#tipo_construccion").val(data.tipoConstruccionId);
			$("#tipo_ocupacion").val(data.tipoOcupacionId);
			
			var numerictextbox1 = $("#numero_total_pisos").data("kendoNumericTextBox");
			numerictextbox1.value(data.numeroTotalPisos);
			
			var datepicker = $("#fecha_inspeccion").data("kendoDatePicker");
			datepicker.value(data.fechaInspecion);
			
			$("#tiene_extintores").prop("checked", data.extintores);
			$("#tiene_detector_humo").prop("checked", data.detectorHumo);
			$("#tiene_spinklers").prop("checked", data.sprinklers);
			$("#tiene_alarma_monitoreada").prop("checked", data.alarmaMonitoreada);
			$("#tiene_guardiania").prop("checked", data.guardiania);
			$("#tiene_otros").val(data.otros);
			if(data.latitud=="0")
				$("#txt_Latitud").val("");
			else
				$("#txt_Latitud").val(data.latitud);
			if(data.longuitud=="0")
				$("#txt_Longitud").val("");
			else
				$("#txt_Longitud").val(data.longuitud);
			
			var numerictextbox2 = $("#anio_construccion").data("kendoNumericTextBox");
			numerictextbox2.value(data.anioConstruccion);
			
			if(data.EstadoInspeccion=="1" || data.EstadoInspeccion=="2"){
				$("#tipo_construccion").attr('disabled','disabled');
				$("#tipo_ocupacion").attr('disabled','disabled');
				$("#tiene_extintores").attr('disabled','disabled');
				$("#tiene_detector_humo").attr('disabled','disabled');
				$("#tiene_spinklers").attr('disabled','disabled');
				$("#tiene_alarma_monitoreada").attr('disabled','disabled');
				$("#tiene_guardiania").attr('disabled','disabled');
				$("#tiene_otros").attr('disabled','disabled');
				$("#txt_Latitud").attr('disabled','disabled');
				$("#txt_Longitud").attr('disabled','disabled');
				$("#anio_construccion").attr('disabled','disabled');
				numerictextbox1.enable(false);
				numerictextbox2.enable(false);
				//datepicker.enable(false);
				$("#guardarNoAprobado").hide();
				$("#guardarAprobado").hide();
				$("#btnExportar").show();
				
			}
			else{
				$("#tipo_construccion").removeAttr('disabled');
				$("#tipo_ocupacion").removeAttr('disabled','disabled');
				$("#tiene_extintores").removeAttr('disabled','disabled');
				$("#tiene_detector_humo").removeAttr('disabled','disabled');
				$("#tiene_spinklers").removeAttr('disabled','disabled');
				$("#tiene_alarma_monitoreada").removeAttr('disabled','disabled');
				$("#tiene_guardiania").removeAttr('disabled','disabled');
				$("#tiene_otros").removeAttr('disabled','disabled');
				$("#txt_Latitud").removeAttr('disabled','disabled');
				$("#txt_Longitud").removeAttr('disabled','disabled');
				$("#anio_construccion").removeAttr('disabled','disabled');
				numerictextbox1.enable(true);
				numerictextbox2.enable(true);
				//datepicker.enable(true);
				$("#guardarNoAprobado").show();
				$("#guardarAprobado").show();
				$("#btnExportar").hide();
			}
		}
	});
}

function cargarPorCotizacionId(){
	$.ajax({
				url : '../PymesObjetoCotizacionController',
		data : {
			"tipoConsulta" : "obtenerDireccionesInspeccion",
			"cotizacionId" : $("#cotizacion_id").text()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			//Creo las direcciones configuradas
			var listadoDirecciones = data.direcciones;
			$.each(listadoDirecciones, function(index){
				if(index==0){
					$("#ubicacion_provincia_1").val(listadoDirecciones[index].provinciaId);
					obtenerCantonPorProvincia(listadoDirecciones[index].cantonId, "1");
					$("#cotizacionDetalleId_1").val(listadoDirecciones[index].cotizacionDetalleId);
					$("#ubicacion_calle_principal_1").val(listadoDirecciones[index].callePrincipal);
					$("#ubicacion_numero_1").val(listadoDirecciones[index].numeroDireccion);
					$("#ubicacion_calle_secundaria_1").val(listadoDirecciones[index].calleSecundaria);
					$("#btnInspeccionDir1").click(function (e) {
						obtenerConfiguracionDireccion(listadoDirecciones[index].cotizacionDetalleId);
					});
				}
				else{
					$("#direcciones").append(
							'<tr>'
							+ '<td>'
							+ '<div class="panel panel-primary">'
							+ '<div class="panel-body">'
							+ '<table>'
							+ '<tr>'
							+ '<td style="width: 10%"><label><b>Provincia:*</b></label></td>'
							+ '<td style="width: 25%"><select id="ubicacion_provincia_'
							+ listadoDirecciones[index].cotizacionDetalleId
							+ '" required="required" class="datosGanadero ubicacionProvincia"></select></td>'
							+ '<td style="width: 5%"><label><b>Cant&oacuten:*</b></label></td>'
							+ '<td style="width: 15%"><select id="ubicacion_canton_'
							+ listadoDirecciones[index].cotizacionDetalleId
							+ '" required="required" class="datosGanadero"></select></td><td></td><td></td>'
							+ '<td style="width: 20%">'
							+ '<button type="button" class="btn btn-success btn-xs actualizar-btn" data-toggle="modal" data-target="#add" onclick="obtenerConfiguracionDireccion('
							+ listadoDirecciones[index].cotizacionDetalleId
							+ ')">'
							+ '<span class="glyphicon glyphicon glyphicon-edit"></span> Ingresar datos de inspecci&oacute;n</button>'
							+ '</td>'
							+ '</tr>'
							+ '<tr>'
							+ '<td><label><b>C. Principal:*</b></label></td>'
							+ '<td><input type="text" id="ubicacion_calle_principal_'
							+ listadoDirecciones[index].cotizacionDetalleId
							+ '" required="required" class="datosGanadero"></input></td>'
							+ '<td><label><b>N&uacutemero:*</b></label></td>'
							+ '<td><input type="text" id="ubicacion_numero_'
							+ listadoDirecciones[index].cotizacionDetalleId
							+ '" required="required" class="datosGanadero"></input></td>'
							+ '<td><label><b>C. Secundaria:*</b></label></td>'
							+ '<td><input type="text" id="ubicacion_calle_secundaria_'
							+ listadoDirecciones[index].cotizacionDetalleId
							+ '" required="required" class="datosGanadero"></input></td>'
							+ '</tr>'
							+ '</table>'
							+ '</div>' + '</div>' + '</td>' + '</td>' + '</tr>');
					$("#contadorDirecciones").val(listadoDirecciones[index].cotizacionDetalleId);
					
					// Consultar provincias
					cargarProvincias(listadoDirecciones[index].cotizacionDetalleId);

					$("#ubicacion_provincia_"+listadoDirecciones[index].cotizacionDetalleId).change(function (e) {
						obtenerCantonPorProvincia('',listadoDirecciones[index].cotizacionDetalleId);
					});
					$("#ubicacion_provincia_"+listadoDirecciones[index].cotizacionDetalleId).val(listadoDirecciones[index].provinciaId);
					obtenerCantonPorProvincia(listadoDirecciones[index].cantonId, listadoDirecciones[index].cotizacionDetalleId);
					$("#cotizacionDetalleId_"+listadoDirecciones[index].cotizacionDetalleId).val(listadoDirecciones[index].cotizacionDetalleId);
					$("#ubicacion_calle_principal_"+listadoDirecciones[index].cotizacionDetalleId).val(listadoDirecciones[index].callePrincipal);
					$("#ubicacion_numero_"+listadoDirecciones[index].cotizacionDetalleId).val(listadoDirecciones[index].numeroDireccion);
					$("#ubicacion_calle_secundaria_"+listadoDirecciones[index].cotizacionDetalleId).val(listadoDirecciones[index].calleSecundaria);
					
				}
			});	
		}
	});
}


function cargarInspecciones(){
	$.ajax({
				url : '../PymesObjetoCotizacionController',
		data : {
			"tipoConsulta" : "obtenerInspecciones",
			"cotizacionId" : $("#cotizacion_id").text()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			//Creo las direcciones configuradas
			var listadoDirecciones = data.direcciones;
			$.each(listadoDirecciones, function(index){
				if(index==0){
					$("#ubicacion_provincia_1").val(listadoDirecciones[index].provinciaId);
					obtenerCantonPorProvincia(listadoDirecciones[index].cantonId, "1");
					$("#cotizacionDetalleId_1").val(listadoDirecciones[index].cotizacionDetalleId);
					$("#ubicacion_calle_principal_1").val(listadoDirecciones[index].callePrincipal);
					$("#ubicacion_numero_1").val(listadoDirecciones[index].numeroDireccion);
					$("#ubicacion_calle_secundaria_1").val(listadoDirecciones[index].calleSecundaria);
					$("#btnInspeccionDir1").click(function (e) {
						obtenerConfiguracionDireccion(listadoDirecciones[index].cotizacionDetalleId);
					});
				}
				else{
					$("#direcciones").append(
							'<tr>'
							+ '<td>'
							+ '<div class="panel panel-primary">'
							+ '<div class="panel-body">'
							+ '<table>'
							+ '<tr>'
							+ '<td style="width: 10%"><label><b>Provincia:*</b></label></td>'
							+ '<td style="width: 25%"><select id="ubicacion_provincia_'
							+ listadoDirecciones[index].cotizacionDetalleId
							+ '" required="required" class="datosGanadero ubicacionProvincia"></select></td>'
							+ '<td style="width: 5%"><label><b>Cant&oacuten:*</b></label></td>'
							+ '<td style="width: 15%"><select id="ubicacion_canton_'
							+ listadoDirecciones[index].cotizacionDetalleId
							+ '" required="required" class="datosGanadero"></select></td><td></td><td></td>'
							+ '<td style="width: 20%">'
							+ '<button type="button" class="btn btn-success btn-xs actualizar-btn" data-toggle="modal" data-target="#add" onclick="obtenerConfiguracionDireccion('
							+ listadoDirecciones[index].cotizacionDetalleId
							+ ')">'
							+ '<span class="glyphicon glyphicon glyphicon-edit"></span> Ingresar datos de inspecci&oacute;n</button>'
							+ '</td>'
							+ '</tr>'
							+ '<tr>'
							+ '<td><label><b>C. Principal:*</b></label></td>'
							+ '<td><input type="text" id="ubicacion_calle_principal_'
							+ listadoDirecciones[index].cotizacionDetalleId
							+ '" required="required" class="datosGanadero"></input></td>'
							+ '<td><label><b>N&uacutemero:*</b></label></td>'
							+ '<td><input type="text" id="ubicacion_numero_'
							+ listadoDirecciones[index].cotizacionDetalleId
							+ '" required="required" class="datosGanadero"></input></td>'
							+ '<td><label><b>C. Secundaria:*</b></label></td>'
							+ '<td><input type="text" id="ubicacion_calle_secundaria_'
							+ listadoDirecciones[index].cotizacionDetalleId
							+ '" required="required" class="datosGanadero"></input></td>'
							+ '</tr>'
							+ '</table>'
							+ '</div>' + '</div>' + '</td>' + '</td>' + '</tr>');
					$("#contadorDirecciones").val(listadoDirecciones[index].cotizacionDetalleId);
					
					// Consultar provincias
					cargarProvincias(listadoDirecciones[index].cotizacionDetalleId);

					$("#ubicacion_provincia_"+listadoDirecciones[index].cotizacionDetalleId).change(function (e) {
						obtenerCantonPorProvincia('',listadoDirecciones[index].cotizacionDetalleId);
					});
					$("#ubicacion_provincia_"+listadoDirecciones[index].cotizacionDetalleId).val(listadoDirecciones[index].provinciaId);
					obtenerCantonPorProvincia(listadoDirecciones[index].cantonId, listadoDirecciones[index].cotizacionDetalleId);
					$("#cotizacionDetalleId_"+listadoDirecciones[index].cotizacionDetalleId).val(listadoDirecciones[index].cotizacionDetalleId);
					$("#ubicacion_calle_principal_"+listadoDirecciones[index].cotizacionDetalleId).val(listadoDirecciones[index].callePrincipal);
					$("#ubicacion_numero_"+listadoDirecciones[index].cotizacionDetalleId).val(listadoDirecciones[index].numeroDireccion);
					$("#ubicacion_calle_secundaria_"+listadoDirecciones[index].cotizacionDetalleId).val(listadoDirecciones[index].calleSecundaria);
					
				}
			});	
		}
	});
}

function grabarInspeccion(estado){
	if($("#tipo_construccion").val()==""){
		alert("Debe seleccionar un material de construcci\u00f3n para poder grabar la inspecci\u00f3n");
		return;
	}
	if($("#tipo_ocupacion").val()==""){
		alert("Debe seleccionar un tipo de ocupaci\u00f3n para poder grabar la inspecci\u00f3n");
		return;
	}
	if($("#anio_construccion").val()==""){
		alert("Debe ingresar el año de construcci\u00f3n para poder grabar la inspecci\u00f3n");
		return;
	}
	if($("#numero_total_pisos").val()==""){
		alert("Debe ingresar el numero de piso para poder grabar la inspecci\u00f3n");
		return;
	}
	if($("#fecha_inspeccion").val()==""){
		alert("Debe ingresar la fecha para poder grabar la inspecci\u00f3n");
		return;
	}
	if($("#numero_total_pisos").val()=="0"){
		alert("El numero de pisos no debe ser igual a 0.");
		return;
	}
	if($("#txt_Latitud").val()==""){
		alert("Debe ingresar la latutid para poder grabar la inspecci\u00f3n");
		return;
	}
	if($("#txt_Longitud").val()==""){
		alert("Debe ingresar la longitud para poder grabar la inspecci\u00f3n");
		return;
	}
	if(nombreArchivo==""){
		alert("Debe seleccionar un archivo del informe para poder grabar la inspecci\u00f3n");
		return;
	}
	$.ajax({
		url : '../PymesObjetoCotizacionController',
		data : {
			"tipoConsulta" : "registrarInspeccion",
			"cotizacionId" : $("#cotizacion_id").text(),
			"cotizacionDetalleId" : $("#cotizacionDetalleId").val(),
			"tipoConstruccionId" : $("#tipo_construccion").val(),
			"tipoOcupacionId" : $("#tipo_ocupacion").val(),
			"numeroTotalPisos" : $("#numero_total_pisos").val(),
			"extintores" : $("#tiene_extintores").is(":checked") ? 1 : 0,
			"detectorHumo" : $("#tiene_extintores").is(":checked") ? 1 : 0,
			"sprinklers" : $("#tiene_spinklers").is(":checked") ? 1 : 0,
			"alarmaMonitoreada" : $("#tiene_alarma_monitoreada").is(":checked") ? 1 : 0,
			"guardiania" : $("#tiene_guardiania").is(":checked") ? 1 : 0,
			"otros" : $("#tiene_otros").val(),
			"anioConstruccion" : $("#anio_construccion").val(),
			"latitud" : $("#txt_Latitud").val(),
			"longuitud" : $("#txt_Longitud").val(),
			"estadoInspeccion" : estado,
			"FileName" : nombreArchivo,
			"fechaInspeccion" : $("#fecha_inspeccion").val(),
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			if(data.success){
				alert("La inspecci\u00f3n fue grabada con \u00e9xito.");
				$('#add').modal('hide');
				window.location.href = "../dashboard/CotizacionesSinInspeccionPymes.jsp?State=SPINS";
			}
			else{
				alert("La inspecci\u00f3n no pudo ser registrada, existio un error: "+ data.mensaje);
			}
		}
	});
}

function exportarInforme() {
	window.open('../PymeCotizacionController?tipoConsulta=descargarInformeInspeccion&cotizacionId=' + $("#cotizacion_id").text()+'&cotizacionDetalleId=' + $("#cotizacionDetalleId").val());
}