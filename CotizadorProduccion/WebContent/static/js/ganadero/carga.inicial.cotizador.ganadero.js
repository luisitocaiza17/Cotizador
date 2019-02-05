/**
 * 
 */
var tipoObjeto = document.getElementById("tipoObjetoCargaInicial").getAttribute("tipoObjetoValor");

function cargar(){

	//Se calcula la fecha de pago inicial

	var today = new Date();

	$('#datosAdicionalesJuridica').hide();
	$('#datosAdicionalesNatural').hide();
	$('#fechaNacimientoPN').datepicker();
	$('#fechaNacimientoPJ').datepicker();
	//Seccion Actividad Politica PN
	$('#lblCargoDesempena').hide();
	$('#cargoDesempena').hide();
	$('#lblParentesco').hide();
	$('#parentesco').hide();
	$('#lblCargoDesempenaFamiliar').hide();
	$('#cargoDesempenaFamiliar').hide();
	//Seccion Actividad Financiera PN
	$('#salarioMensual').val(0);
	$('#activosFinanciero').val(0);
	$('#pasivosFinanciero').val(0);
	$('#otrosIngresos').val(0);
	$('#egresos').val(0);
	$('#vNeto').val(0);
	$('#patrimonio').val(0);
	$('#estadoCotizacion').val('borrador');
	//Seccion Actividad Politica PJ
	$('#lblCargoDesempenaPJ').hide();
	$('#cargoDesempenaPJ').hide();
	$('#lblParentescoPJ').hide();
	$('#parentescoPJ').hide();
	$('#lblCargoDesempenaFamiliarPJ').hide();
	$('#cargoDesempenaFamiliarPJ').hide();
	//Seccion Actividad Financiera PJ
	$('#salarioMensualPJ').val(0);
	$('#activosFinancieroPJ').val(0);
	$('#pasivosFinancieroPJ').val(0);
	$('#otrosIngresosPJ').val(0);
	$('#egresosPJ').val(0);
	$('#vNetoPJ').val(0);
	$('#patrimonioPJ').val(0);
	//Seccion Vinculos Existentes PJ
	$("#vinculo3").hide();
	$("#vinculo4").hide();
	$("#estado_solicitud_descuento").hide();

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

	

	$("#wizard").steps({
		headerTag : "h2",
		bodyTag : "section",
		labels: {
			finish: "Emitir",
			next: "Siguiente",
			previous: "Anterior",
		},
		onStepChanging : function (event, currentIndex, newIndex) {
			//Se desahibilita el control de los campos requeridos cuando se va hacia atras
			if (currentIndex > newIndex){
				return true;
			}
			// Eventos al cambiar a la pestana Cliente
			if (newIndex === 1) {
				if (QueryString.id != null && QueryString != '')
					$("#cotizacion_id").text(QueryString.id);
				// Validacion para que solo se cree una cotizacion
				if ($("#estadoCotizacion").val() == "borrador" || $("#estadoCotizacion").val() == "Borrador"){										
					guardarCotizacion();
					
					if($("#tipo_identificacion_asegurado").val()=="")
						$("#tipo_identificacion_asegurado").val($("#tipo_identificacion_principal").val());
					if($("#identificacion_asegurado").val()=="")
						$("#identificacion_asegurado").val($("#identificacion").val());
					if($("#nombres_asegurado").val()=="")
						$("#nombres_asegurado").val($("#nombres").val());
					if($("#apellidos_asegurado").val()=="")
						$("#apellidos_asegurado").val($("#apellidos").val());
					if($("#tipo_identificacion_asegurado").val()==4)
						cargarDatosEnGanaderoUPLAJuridica({"":""});
					else
						cargarDatosEnGanaderoUPLANatural({"":""});
					mostrarTipoIdentificacionAsegurado();
				}
			}
			// Eventos al cambiar a la pestana Productos
			if (newIndex === 2) {
				if ($("#estadoCotizacion").val() == "borrador" || $("#estadoCotizacion").val() == "Borrador"){
					guardarProductoGanadero();
				}
				cargarAnimalesCotizacion();
			}

			if (newIndex === 3) {
				// Verificamos si el producto seleccionado tiene inspeccion o no.
				$(".loading-indicator").show();
				$("#tabbable").show();


				if( parseInt($("#codigoPagoRegistrado").val()) == -1){
					$("#tabFormasPago").trigger("click");
					$("#msgPopupPagoError").show();
					return false;
				}else{
					$("#msgPopupPagoGrabo").hide();
				}
				//Se pone el metodo "cargarTablaVehiculosFinal()" en este paso porque se desactivo el formulario de la UPLA
				
			}

			if (newIndex === 4) {
				var opcion = $('#tipo_identificacion_asegurado').val();
				if (opcion == 1 || opcion == 2 || opcion == 3) {
					if ($("#estadoCotizacion").val() == "borrador" || $("#estadoCotizacion").val() == "Borrador"){
						guardarDatosUPLANaturalEnGanadero();
					}
				} else if (opcion == 4  || opcion == 5) {
					if ($("#estadoCotizacion").val() == "borrador" || $("#estadoCotizacion").val() == "Borrador"){
						guardarDatosUPLAJuridicaEnGanadero();
					}
				}
			}
			return $("#wizard").valid();
		},			
	});

	//carga de cotizaciones nuevas
	if(QueryString.id==null||QueryString==''){
		cargaInicialGanadero();
	}
	else{
		cargaInicialGanadero();
		cargarPorGanaderoId(QueryString.id);
	}
}


/*
 * METODO QUE CARGA COTIZACIONES NUEVAS
 */    
function cargaInicialGanadero(){
	
	// Cargar Grupos de Productos
	cargarGruposProductosGanadero();
	
	//Cargar las sucursales, en base a esto se carga los puntos de venta
	cargarSucursalesGanadero();
	
	// Consultar las vigencia de las polizas
	//cargarVigenciasPolizasGanadero();

	// Consultar los agentes  
	cargarAgentes();

	// llena el combo de raza
	cargarRazas();
	
	//Llena el combo de tipos de ganado
	cargarTipoGanado();

	//Consultar provincias
	cargarProvincias();
	
	//
	cargarBeneficiarios();
	
	//Consultar provincias
	cargarProvinciasPNatural();
	
	//Consultar provincias
	cargarProvinciasPJuridica();
	
	// Consultar los tipos de identificacion  
	cargarTiposIdentificacionGanadero("","",false);

	// Consultar las formas de pago
	//cargarFormasPago("", true);
	
	$(".ui-dialog-titlebar").attr("style", $(".panel-heading").attr("style"))
	.addClass($(".panel-heading").attr("class"));
	
	
	//eventos ULAP
	$("#provincia_direccion_cliente_natural").change(function (e) {
		obtenerCantonPorProvinciaPNatural('');
		obtenerCiudadesPorProvinciaPNatural('');
	});
	
	$("#provincia_direccion_matriz_juridica").change(function (e) {
		obtenerCiudadesPorProvinciaPJuridica('');
		obtenerCantonPorProvinciaPJuridica('');
	});
	
	$("#canton_direccion_cliente_natural").change(function (e) {
		obtenerParroquiaPorCantonPNatural('');
	});
	
	$("#canton_direccion_matriz_juridica").change(function (e) {
		obtenerParroquiaPorCantonPJuridica('');
	});
	
	// eventos
	$("#ubicacion_provincia").change(function (e) {
		obtenerCantonPorProvincia('');
	});

	$("#ubicacion_Canton").change(function (e) {
		obtenerParroquiaPorCanton('');
	});
	
	$("#grupo_productos").change(function (e) {
		obtenerProductosPorGrupoProducto('');
	});
	
	$("#productos").change(function (e) {
		obtenerPuntosVentaPorProducto('','');
	});
	
	$("#punto_venta").change(function (e) {
		verificarPuntosVenta('');
		obtenerFormaPagoXPV('');
		obtenerVigenciaPolizaXPV('');
	});
	
	$("#tipo_identificacion_principal").change(function (e) {
		var tipo_identificacion = $(this).val();
		if(tipo_identificacion == "4")			
			$("#es_contribuyente").show();
		else
			$("#es_contribuyente").hide();
		
		mostrarTipoIdentificacionSolicitante();
		mostrarAdicionalesSolicitante();
	});
	
	$("#tipo_identificacion_asegurado").change(function (e) {
		mostrarTipoIdentificacionAsegurado();
		mostrarAdicionalesAsegurado();
	});
	
	$("#finalizar_Cotizacion").click(function (e) {
		cambiarEstadoGanadero("Pendiente de Revisar");
	});
	
	$("#Aceptar_Cotizacion").click(function (e) {
		/*Cambia el estado a revision aprobada*/
		cambiarEstadoGanadero("Revision Aprobada");
	});
	
	$("#Denegar_Cotizacion").click(function (e) {
		cambiarEstadoGanadero("Revision Negada");
	});
	
	$("#Aprobada_Cliente_Cotizacion").click(function (e) {
		cambiarEstadoGanadero("Pendiente de Emitir");
	});
	
	$("#Negada_Cliente_Cotizacion").click(function (e) {
		cambiarEstadoGanadero("Denegado Cliente");
	});
	
	$("#Aprobar_Cotizacion").click(function (e) {
		emitirPolizaGanadero();
	});
	
	$("#Negar_Cotizacion").click(function (e) {
		cambiarEstadoGanadero("Denegado");
	});
}

function cargarBeneficiarios(){
	$.ajax({
		url : '../BeneficiarioController',
		data : {
			"tipoConsulta" : "cargarSelect2"
		},
		async: false,
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var beneficiarios = data.listadoBeneficiarios;
			$('#beneficiario').select2({
				data : beneficiarios,
				placeholder : 'Seleccione un beneficiario'
			});
		}
	});
}
function cargarGruposProductosGanadero() {
	// Consultar listado de grupos productos
	$.ajax({
		url : '../GrupoPorProductoController',
		data : {
			"tipoConsulta" : "encontrarTodosGrupoProducto",
			"tipoObjeto" : tipoObjeto,
		},	
		async: false,
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var listadoGrupos = data.listadoGruposProducto;	
			var ListadoGruposUnico = arregloUnicoJSON(listadoGrupos); 
						
			$("#grupo_productos").append("<option value=''>Seleccione una opcion</option>");
			$.each(ListadoGruposUnico, function (index) {
				$("#grupo_productos").append("<option value='" + ListadoGruposUnico[index].id + "'>" + ListadoGruposUnico[index].nombre + "</option>");
			});		
		}
	});
}

/*
 * METODO QUE RECIBE EL ID DE LA SUCURSAL Y EL NUMERO DEL VEHICULO
 * DENTRO DE LA COTIZACION. SETEA LA SUCURSAL CORRESPONDIENTE AL ID
 * QUE RECIBE. 
 */
function cargarSucursalesGanadero(seleccionada) {
	$.ajax({
		url : '../SucursalController',
		data : {
			"tipoConsulta" : "encontrarSucursalesActivas",
		},
		async: false,
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var options = '';
			options = '<option value="">&nbsp;&nbsp;Seleccione</option>';

			for (var i = 0; i < data.sucursales.length; i++) {
				options += '<option value="' + data.sucursales[i].id + '">' + data.sucursales[i].nombre + '</option>';
			}
			$("#sucursales").html(options).val(seleccionada).attr('required','required');
		}
	});
}


/*
 * METODO QUE CARGA EL NUMERO DE AÃ‘OS CON EL QUE SE VA A EMITIR LA POLIZA.
 * Y SETEA EL VALOR CORRESPONDIENTE AL ID RECIBIDO
 */	
function cargarVigenciasPolizasGanadero() {
	$.ajax({
		url : '../VigenciaPolizaController',
		data : {
			"tipoConsulta" : "encontrarTodosActivos",
		},
		async: false,
		type : 'post',
		datatype : 'json',
		success : function (data) {
			$("#vigencia").append("<option value=''>Seleccione una vigencia</option>");
			for (var i = 0; i < data.vigencias_poliza.length; i++) {
				$("#vigencia").append("<option value='" + data.vigencias_poliza[i].id + "'>" + data.vigencias_poliza[i].nombre + "</option>");
			}			
		}
	});
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
		async: false,
		type : 'post',
		datatype : 'json',
		success : function (data) {
			$("#agentes").append("<option value=''>Seleccione un Agente</option>");
			for (var i = 0; i < data.agentes.length; i++) {
				$("#agentes").append("<option value='" + data.agentes[i].id + "'>" + data.agentes[i].nombre + "</option>");
			}
		}
	});
}

/*
 * METODO QUE RECIBE EL ID DE LA RAZAS Y SETEALA RAZA CORRESPONDIENTE AL ID
 * QUE RECIBE.
 */	
function cargarRazas() {
	$("#razaAnimalOrigin").empty();
	//Consultar la provincia
	$.ajax({
		url : '../RazaController',
		data : {
			"tipoConsulta" : "encontrarTodos"
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var listadoRazas = data.listadoRazas;			
			$("#razaAnimalOrigin").append("<option value=''>Seleccione una raza</option>");
			$.each(listadoRazas, function (index) {
				$("#razaAnimalOrigin").append("<option value='" + listadoRazas[index].id + "'>" + listadoRazas[index].nombre + "</option>");
			});
		}
	});
}

/*
 * METODO QUE RECIBE EL ID DE LA RAZAS Y SETEALA RAZA CORRESPONDIENTE AL ID
 * QUE RECIBE.
 */	
function cargarTipoGanado() {
	$("#tipoAnimalOrigin").empty();
	//Consultar la provincia
	$.ajax({
		url : '../TipoGanadoController',
		data : {
			"tipoConsulta" : "encontrarTodos"
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var listadoTipos = data.listadoTiposGanado;			
			$("#tipoAnimalOrigin").append("<option value=''>Seleccione un Tipo de Ganado</option>");
			$.each(listadoTipos, function (index) {
				$("#tipoAnimalOrigin").append("<option value='" + listadoTipos[index].id + "'>" + listadoTipos[index].nombre + "</option>");
			});
		}
	});
}

/*
 * METODO QUE RECIBE EL ID DE LA PROVINCIA Y SETEALA PROVINCIA CORRESPONDIENTE AL ID
 * QUE RECIBE.
 */	
function cargarProvincias() {
	$("#ubicacion_provincia").empty();
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
			$("#ubicacion_provincia").append("<option value=''>Seleccione una provincia</option>");
			$.each(listadoProvincias, function (index) {
				$("#ubicacion_provincia").append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
			});
		}
	});
}

///Cargar provincias para los datos de la facturación
function cargarProvinciasPNatural() {
	$("#provincia_direccion_cliente_natural").empty();
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
			$("#provincia_direccion_cliente_natural").append("<option value=''>Seleccione una provincia</option>");
			$.each(listadoProvincias, function (index) {
				$("#provincia_direccion_cliente_natural").append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
			});
		}
	});
}

///Cargar provincias para los datos de la facturación
function cargarProvinciasPJuridica() {
	$("#provincia_direccion_matriz_juridica").empty();
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
			$("#provincia_direccion_matriz_juridica").append("<option value=''>Seleccione una provincia</option>");
			$.each(listadoProvincias, function (index) {
				$("#provincia_direccion_matriz_juridica").append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
			});
		}
	});
}
/*
 * METODO QUE RECIBE EL ID Y SETEA EL TIPO DE IDENTIFICACION CORRESPONDIENTE AL ID
 * QUE RECIBE.
 */	
function cargarTiposIdentificacionGanadero(seleccionada,tipo,noChange) {
	$.ajax({
		url : '../TipoIdentificacionController',
		data : {
			"tipoConsulta" : "ObtenerTodos",
		},
		async: false,
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var options = '';
			options = '<option value="">Seleccione un tipo de identificacion </option>';
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
				$(".tipo_identificacion").html(options).val(seleccionada);
		}
	});
}


function cargarTablaPorIdentificacion(identificacion){
	$.ajax({
		url : '../UPLAController',
		data : {
			"tipoConsulta" : "cargarDatosPorIdentificacion",
			"identificacion" : identificacion
		},
		type : 'POST',
		datatype : 'json',
		success : function(data) {
			if(data.tieneDatosUPLA){
				if($("#tipo_identificacion_asegurado").val()==4)
					cargarDatosEnGanaderoUPLAJuridica(data.datosUPLA);
				else
					cargarDatosEnGanaderoUPLANatural(data.datosUPLA);
			}

		}
	});
}

function cargarPuntosVentaSessionGanadero(){
	$.ajax({
		url : '../PuntoVentaController',
		data : {
			"tipoConsulta" : "puntosVentaXProductoSession",
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var sucursales = data.sucursales;
			var options = '';
			options = '<option value="">Seleccione un punto de venta</option>';
			var puntosVenta =arregloUnicoJSON(data.puntosVenta);
			var contador;
			for (var j = 0; j < sucursales.length; j++) {				
				contador =0;
				for (var i = 0; i < puntosVenta.length; i++) {
					if (puntosVenta[i].sucursal == sucursales[j].id) {
						contador++;
						if(contador ==1){
							options += '<option value="" disabled="disabled" class="seleccionado" ">' + sucursales[j].nombre + '</option>';
						}
						options += '<option value="'+ puntosVenta[i].id + '" >&nbsp;&nbsp;&nbsp;&nbsp;' + puntosVenta[i].nombre + '</option>';
					}
				}
			}
			
			$("#punto_venta_session").html(options);
		}
	});
}