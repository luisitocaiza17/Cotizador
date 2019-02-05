function cargarInicalInspeccion(){
	
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
	
	// eventos
	$("#ubicacion_provincia_1").change(function (e) {
		obtenerCantonPorProvincia('',"1");
	});
	
	cargarPorCotizacionId();
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
	$("#inspector").val("");
}

function obtenerListadoInspectores(numeroDireccion){
	$("#inspector").empty();
	// Consultar la provincia
	$.ajax({
		url : '../PymeInspectorProvinciaAsignadoController',
		data : {
			"tipoConsulta" : "obtenerUsuariosPorProvincia",
			"cotizacionDetalleId" : numeroDireccion
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			var listaUsuariosAsignado = data.listaUsuariosAsignado;
			$("#inspector").append("<option value=''>Seleccione un Inspector</option>");
			$.each(listaUsuariosAsignado, function(index) {
				$("#inspector").append("<option value='" + listaUsuariosAsignado[index].usuarioId + "'>" + listaUsuariosAsignado[index].nombre_completo + "</option>");
			});
		}
	});
}

function obtenerConfiguracionDireccion(numeroDireccion) {
	limpiarControles();
	obtenerListadoInspectores(numeroDireccion);
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
			$("#inspector").val(data.inspectorID);
		}
	});
}

function cargarPorCotizacionId(){
	$.ajax({
				url : '../PymesObjetoCotizacionController',
		data : {
			"tipoConsulta" : "obtenerDireccionesSinInspector",
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
							+ '<span class="glyphicon glyphicon glyphicon-edit"></span> Asignar Inspector</button>'
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

function grabarInspeccion(){
	if($("#inspector").val()==""){
		alert("Debe seleccionar un inspector");
		return;
	}
	$.ajax({
		url : '../PymesObjetoCotizacionController',
		data : {
			"tipoConsulta" : "registrarAsignacionInspector",
			"cotizacionId" : $("#cotizacion_id").text(),
			"cotizacionDetalleId" : $("#cotizacionDetalleId").val(),
			"inspectorId" : $("#inspector").val()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			if(data.success){
				alert("La asignaci\u00f3n fue grabada con \u00e9xito.");
				$('#add').modal('hide');
			}
		}
	});
}

function finalizarInspeccion(){
	if($("#inspector").val()==""){
		alert("Debe seleccionar un inspector");
		return;
	}
	$.ajax({
		url : '../PymesObjetoCotizacionController',
		data : {
			"tipoConsulta" : "finalizarAsignacionSinInspector",
			"cotizacionId" : $("#cotizacion_id").text()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			if(data.success){
				alert("La asignaci\u00f3n fue finalizada con \u00e9xito.");
				window.location.href = "../dashboard/CotizacionPymesCerrados.jsp?id="+$("#cotizacion_id").text();
			}
		}
	});
}