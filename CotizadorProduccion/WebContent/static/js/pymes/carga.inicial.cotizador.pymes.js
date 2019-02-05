/**
 * 
 */
var tipoObjeto = document.getElementById("tipoObjetoCargaInicial").getAttribute("tipoObjetoValor");

function cargar(){
	//Se calcula la fecha de pago inicial
	var today = new Date();
	//riesgo
	
	$('#tieneRiesgo').val(0);
	$('#riesgoDanioColumn').hide();
	$('#riesgoVolcanColumn').hide();
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

	if (QueryString.GrupoProductoID != null && QueryString != '')
		$("#GrupoProductoID").val(QueryString.GrupoProductoID);
	
	if (QueryString.ProductoID != null && QueryString != ''){
		$("#ProductoID").val(QueryString.ProductoID);	
		if(QueryString.ProductoID=="190"){ //Quemado que sea solo para edificios
			$("#fila_constitucion").hide();
		}
	}
		

	$("#wizard").steps({
		headerTag : "h2",
		bodyTag : "section",
		onFinished: function (event, currentIndex) {
			emitirPoliza();
		},
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
						cargarDatosEnUPLAJuridica({"":""});
					else
						cargarDatosEnUPLANatural({"":""});
					mostrarTipoIdentificacionAsegurado();
				}
			}
			// Eventos al cambiar a la pestana Productos
			if (newIndex === 2) {
				if ($("#estadoCotizacion").val() == "borrador" || $("#estadoCotizacion").val() == "Borrador"){
					var resultado=guardarProductoPymes();
					if(resultado)
						cargarResumenValores();
					return resultado;
					
				}
			}

			if (newIndex === 3) {
				var res = verificarEstadoInspeccion();
				return res;
			}
			
			if (newIndex === 4) {
				// Verificamos si el producto seleccionado tiene inspeccion o no.
				//$(".loading-indicator").show();
				$("#tabbable").show();
				var res = false;

				if( parseInt($("#codigoPagoRegistrado").val()) == -1){
					$("#tabFormasPago").trigger("click");
					$("#msgPopupPagoError").show();
					res = false;
				}else{
					res = verificarEstadoPago();
					$("#msgPopupPagoGrabo").hide();
				}
				return res;
				//Se pone el metodo "cargarTablaVehiculosFinal()" en este paso porque se desactivo el formulario de la UPLA
				
			}

			if (newIndex === 5) 
			{
				var opcion = $('#tipo_identificacion_asegurado').val();
				if (opcion == 1 || opcion == 2 || opcion == 3) 
					guardarDatosUPLANaturalEnGanadero();
				else if (opcion == 4  || opcion == 5) 
					guardarDatosUPLAJuridicaEnGanadero();
			}
			return $("#wizard").valid();
		},			
	});

	//Llamo al metodo que obtiene el rol del usuario
	obtenerRol();
	
	//Llama al metodo que inicializa los componentes a Kendo
	inicializarAKendo();
	
	//carga de cotizaciones nuevas
	if(QueryString.id==null||QueryString==''){
		cargaInicialPymes();
		//Consultar provincias
	}
	else{
		cargaInicialPymes();
		//Consultar provincias
		cargarPorCotizacionId(QueryString.id);
	}
}

function obtenerRol()
{
	$.ajax({
		url : '../UsuarioController',
		data : {
			"tipoConsulta" : "cargarActualRol",
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			if(data.success){
				if(data.usuario.rolPermitido){
					$("#usuarioRol").val("1");
				}
				else{
					$("#usuarioRol").val("0");
				}
			}
			else
				$("#usuarioRol").val("0");
		}
	});
}

function inicializarAKendo(){
	$("#tabstrip").kendoTabStrip({
		select: onSelect,
        animation:  {
            open: {
                effects: "fadeIn"
            }
        }
    });
	var tabStrip = $("#tabstrip").kendoTabStrip().data("kendoTabStrip");
	//tabStrip.enable(tabStrip.tabGroup.children().eq(2), false);
	
	//creo control de distancia como numerico
	$("#distanciaRiesgo").kendoNumericTextBox({
		format: "#.00",
        decimals: 2			
	});	
	
	$("#ingresarNuevoValores").kendoButton();

	$("#fecha_constitucion").kendoDatePicker();
	
	$("#fecha_inicio_vigencia").kendoDatePicker();
	
	$("#anio_construccion").kendoNumericTextBox({
		format: "#",
        decimals: 0
	});
	
	$("#fecha_inicio_vigencia").kendoDatePicker({
		format: "yyyy-MM-dd"
	});
	
	$("#numero_total_pisos").kendoNumericTextBox({
		format: "#",
        decimals: 0			
	});
	
	$("#valor_estructuras").kendoNumericTextBox({
		format: "c2" 			
	});
	
	$("#valor_muebles_enseres").kendoNumericTextBox({
		format: "c2" 			
	});
	
	$("#valor_maquinarias").kendoNumericTextBox({
		format: "c2" 			
	});
	
	$("#valor_mercaderia").kendoNumericTextBox({
		format: "c2" 			
	});
	
	$("#valor_herramientas").kendoNumericTextBox({
		format: "c2" 			
	});
	
	$("#valor_insumos_medicos").kendoNumericTextBox({
		format: "c2" 			
	});
	
	$("#valor_contenidos").kendoNumericTextBox({
		format: "c2" 			
	});
	
	$("#total_valor_asegurado").kendoNumericTextBox({
		format: "c2" 			
	});
	
	$("#todo_riesgo_prima_neta").kendoNumericTextBox({
		format: "c2" 			
	});
	
	$("#todo_riesgo_monto_asegurado").kendoNumericTextBox({
		format: "c2" 			
	});
	
	$("#todo_riesgo_tasa_sugerida").kendoNumericTextBox({			
	});
	
	$("#todo_riesgo_tasa_ingresada").kendoNumericTextBox({		
	});
	
	$("#rotura_vidrios_tasa_sugerida").kendoNumericTextBox({			
	});
	
	$("#rotura_vidrios_tasa_ingresada").kendoNumericTextBox({		
	});
	
	$("#robo_robo_tasa_sugerida").kendoNumericTextBox({			
	});
	
	$("#robo_robo_tasa_ingresada").kendoNumericTextBox({		
	});
	
	$("#rotura_vidrios_monto_asegurado").kendoNumericTextBox({
		format: "c2" 			
	});
	
	$("#rotura_vidrios_prima_neta").kendoNumericTextBox({
		format: "c2" 			
	});
	
	$("#robo_robo_monto_asegurado").kendoNumericTextBox({
		format: "c2" 			
	});
	
	$("#robo_robo_prima_neta").kendoNumericTextBox({
		format: "c2" 			
	});
	
	$("#robo_hurto_tasa_sugerida").kendoNumericTextBox({			
	});
	
	$("#robo_hurto_tasa_ingresada").kendoNumericTextBox({		
	});
	
	$("#robo_hurto_monto_asegurado").kendoNumericTextBox({
		format: "c2" 			
	});
	
	$("#robo_hurto_prima_neta").kendoNumericTextBox({
		format: "c2" 			
	});
}

/*
 * METODO QUE CARGA COTIZACIONES NUEVAS
 */    
function cargaInicialPymes(){
	
	// Cargar Grupos de Productos
	cargarGruposProductosPymes();
	
	//Cargar las sucursales, en base a esto se carga los puntos de venta
	cargarSucursalesPymes();
	
	//Consultar las vigencia de las polizas
	cargarVigenciasPolizasGanadero();

	// Consultar los agentes  
	cargarAgentes();
	
	//Cargar materiales de construcción
	cargarMaterialesConstruccion();
	
	//
	cargarBeneficiarios();
	
	//Consultar provincias
	cargarProvinciasPNatural();
	
	//Consultar provincias
	cargarProvinciasPJuridica();
	
	//Tipo de predios Giros de negocio
	//cargarTiposPredios();
	
	//obtenerTipoPredioPorActividad('');

	// Consultar los tipos de identificacion  
	cargarTiposIdentificacionPymes("","",false);

	// Consultar las formas de pago
	cargarFormasPago("", "formasPago");
	cargarFormasPago("", "intitucionesFinancieras");
	cargarFormasPago("", "aniosVigencia");
	
	//cargar las actividades económicas
	$("#actividad_economica").kendoMultiSelect({
		dataTextField : "nombre",
		dataValueField : "codigo",
		filter: "contains",
		animation : false,
		maxSelectedItems : 1
	});
	actividadEconomicaList = $("#actividad_economica").data("kendoMultiSelect");
	
	$("#giro_negocio").kendoMultiSelect({
		dataTextField : "nombre",
		dataValueField : "codigo",
		filter: "contains",
		animation : false,
		maxSelectedItems : 1
	});
	giroNegociosList = $("#giro_negocio").data("kendoMultiSelect");
	
	$(".ui-dialog-titlebar").attr("style", $(".panel-heading").attr("style"))
	.addClass($(".panel-heading").attr("class"));
	
	$("#seguridad_adecuada_si").click(function(){
		$('.cobertura').each(function () { 
			  var coberturaId = ($(this).val());
			  $("#deducible_cobertura_"+coberturaId).remove();
			  var planId = $('#planId_'+$(this).val()).val();
			  var coberturaNombre = $('#nombreCobertura_'+$(this).val()).val();
			  cargarDeducibles(coberturaId, "true", coberturaNombre);
		});
	});
	
	
	$("#seguridad_adecuada_no").click(function(){
		$('.cobertura').each(function () { 
			  var coberturaId = ($(this).val());
			  $("#deducible_cobertura_"+coberturaId).remove();
			  var planId = $('#planId_'+$(this).val()).val();
			  var coberturaNombre = $('#nombreCobertura_'+$(this).val()).val();
			  cargarDeducibles(coberturaId, "false", coberturaNombre);
		});
	});
	
	//riesgo
	$("#riesgoDanio_si").click(function(){
		calcularTotalValorAsegurado();
	});
	
	
	$("#riesgoDanio_no").click(function(){
		calcularTotalValorAsegurado();
	});
	
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
	$("#ubicacion_provincia_1").change(function (e) {
		obtenerCantonPorProvincia('',"1");
	});
	
	$("#actividad_economica").change(function (e) {
		obtenerTipoPredioPorActividad('');
	});
	
	$("#grupo_productos").change(function (e) {
		obtenerProductosPorGrupoProducto('');
	});
	
	$("#productos").change(function (e) {
		obtenerPuntosVentaPorProducto('','');
		//Llena el combo de actividades economica
		cargarActividadesEconomicas();
	});
	
	$("#punto_venta").change(function (e) {
		verificarPuntosVenta('');
	});
	
	$("#tipo_identificacion_principal").change(function (e) {
		var tipo_identificacion = $(this).val();
		if(tipo_identificacion == "4"){			
			$("#es_contribuyente").show();
			$("#lbles_contribuyente").show();
		}
		else{
			$("#es_contribuyente").hide();
			$("#lbles_contribuyente").hide();
		}
		mostrarTipoIdentificacionSolicitante();
		mostrarAdicionalesSolicitante();
	});
	
	$("#tipo_identificacion_asegurado").change(function (e) {
		mostrarTipoIdentificacionAsegurado();
		mostrarAdicionalesAsegurado();
	});
	
	$("#btnAgregarDireccion").click(function (e) {
		agregarNuevaDireccion();
	});
	
	$("#registrarParaInspeccion").click(function (e) {
		cambiarEstadoCotizacion("Pendiente Asignar Inspector");
	});
	
	$("#procesarReporte").click(function (e) {
		procesarReporte();
	});
	
	$("#procesarReporteBorrador").click(function (e) {
		imprimirPolizaBorrador();
	});

	$("#solicitarAutorizacion").click(function (e) {
		solicitarAutorizacion();
	});

	$("#enviarReporte").click(function (e) {
		enviarReporte();
	});
	
	$("#imprimirPoliza").click(function (e) {
		imprimirPoliza();
	});
	
	$("#enviarPoliza").click(function (e) {
		enviarPoliza();
	});
	
	$("#imprimirAutorizacionDebito").click(function (e) {
		abrirCertificadoDebito();
	});
	
	$("#imprimirEndosoBeneficiario").click(function (e) {
		abrirEndosoBeneficiario();
	});
	
	$("#imprimirVinculacionCliente").click(function (e) {
		abrirVinculacionCliente();
	});
	
	$("#ingresarNuevoValores").click(function (e) {
		limpiarCoberturas();
	});
	
	
	
	/*$(".actualizar-btn").bind({click: function() {
		$("#addButton").trigger("click");		
	  	} 
	});*/
	
	$('#cobertura_robo_robo').click(function() {
        if ($(this).is(':checked')) {
        	$("#fila_cobertura_robo_robo").show();
        }
        else{
        	if(confirm("Esta seguro que desea retirar esta cobertura?")){
        		$("#fila_cobertura_robo_robo").hide();
        		return true;
        	}
        	else{
        		return false;
        	}
        }
        	
    });
	
	$('#cobertura_robo_hurto').click(function() {
        if ($(this).is(':checked')) {
        	$("#fila_cobertura_robo_hurto").show();
        }
        else{
        	if(confirm("Esta seguro que desea retirar esta cobertura?")){
        		$("#fila_cobertura_robo_hurto").hide();
        		return true;
        	}
        	else{
        		return false;
        	}
        }
        	
    });
	
	$('#cobertura_rotura_vidrios').click(function() {
        if ($(this).is(':checked')) {
        	$("#fila_cobertura_rotura_vidrios").show();
        }
        else{
        	if(confirm("Esta seguro que desea retirar esta cobertura?")){
        		$("#fila_cobertura_rotura_vidrios").hide();
        		return true;
        	}
        	else{
        		return false;
        	}
        }
        	
    });
	
	$('#clausula_electrica').click(function() {
        if ($(this).is(':checked')) {
        	$("#fila_clausula_electrica").show();
        }
        else{
        	if(confirm("Esta seguro que desea retirar esta clausula?")){
        		$("#fila_clausula_electrica").hide();
        		return true;
        	}
        	else{
        		return false;
        	}
        }
        	
    });
	
	$('#clausula_vidrios').click(function() {
        if ($(this).is(':checked')) {
        	$("#fila_clausula_vidrios").show();
        }
        else{
        	if(confirm("Esta seguro que desea retirar esta clausula?")){
        		$("#fila_clausula_vidrios").hide();
        		return true;
        	}
        	else{
        		return false;
        	}
        }
        	
    });
	
	/*Cargo los datos del grupo de producto y producto*/
	if($("#GrupoProductoID").val()!=null && $("#GrupoProductoID").val()!=""){
		$("#grupo_productos").val($("#GrupoProductoID").val());
		$('#grupo_productos').trigger('change');
		obtenerProductosPorGrupoProducto($("#ProductoID").val());
		cargarProvincias("1");
	}
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
			/*$('#beneficiario').select2({
				data : beneficiarios,
				placeholder : 'Seleccione un beneficiario'
			});*/
			$("#beneficiario_1").append("<option value=''>Seleccione una opcion</option>");
			$.each(beneficiarios, function (index) {
				$("#beneficiario_1").append("<option value='" + beneficiarios[index].id + "'>" + beneficiarios[index].text + "</option>");
			});
		}
	});
}

function cargarTiposPredios(){
	giroNegociosList.value([]);
	//Consultar la provincia
	$.ajax({
		url : '../TipoPredioController',
		data : {
			"tipoConsulta" : "ObtenerTodos"
		},
		async: false,
		type : 'post',
		datatype : 'json',
		success : function (data) {
			giroNegociosList.dataSource.filter({});
			giroNegociosList.setDataSource(data.tiposPredio);
		}
	});
}

function cargarGruposProductosPymes() {
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
function cargarSucursalesPymes(seleccionada) {
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
			for (var i = 0; i < data.vigencias_poliza.length; i++) {
				$("#vigencia").append("<option value='" + data.vigencias_poliza[i].id + "'>" + data.vigencias_poliza[i].nombre + "</option>");
			}
			$("#vigencia").val("1");
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

function cargarPuntosVentaSessionPymes(){
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

/*
 * METODO QUE RECIBE EL ID DE LA PROVINCIA Y SETEALA PROVINCIA CORRESPONDIENTE AL ID
 * QUE RECIBE.
 */	
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
				/*if($("#productos").val()=="190"){
					if(listadoProvincias[index].nombre != 'MANABI' && listadoProvincias[index].nombre != 'ESMERALDAS' && listadoProvincias[index].nombre != 'SANTA ELENA' && listadoProvincias[index].nombre != 'GUAYAS'){
						$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
					}
				}
				else if($("#productos").val()=="213"){
					if(listadoProvincias[index].nombre != 'MANABI' && listadoProvincias[index].nombre != 'ESMERALDAS' && listadoProvincias[index].nombre != 'SANTA ELENA' && listadoProvincias[index].nombre != 'GUAYAS'){
						$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
					}
				}
				else if($("#productos").val()=="218"){
					if(listadoProvincias[index].nombre != 'MANABI' && listadoProvincias[index].nombre != 'ESMERALDAS' && listadoProvincias[index].nombre != 'SANTA ELENA'){
						$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
					}
				}
				else if($("#productos").val()=="214"){
					if(listadoProvincias[index].nombre == 'MANABI' || listadoProvincias[index].nombre == 'ESMERALDAS' || listadoProvincias[index].nombre == 'SANTA ELENA' || listadoProvincias[index].nombre == 'GUAYAS'){
						$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
					}
				}
				else if($("#productos").val()=="221"){
					if(listadoProvincias[index].nombre == 'MANABI' || listadoProvincias[index].nombre == 'ESMERALDAS' || listadoProvincias[index].nombre == 'SANTA ELENA' || listadoProvincias[index].nombre == 'GUAYAS'){
						$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
					}
				}
				else if($("#productos").val()=="215"){
					if(listadoProvincias[index].nombre == 'GUAYAS'){
						$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
					}
				}*/				
				/**PRODUCTO EDIFICIOS**/
				/*if($("#productos").val()=="190"){
					if(listadoProvincias[index].nombre != 'MANABI' && listadoProvincias[index].nombre != 'ESMERALDAS' && listadoProvincias[index].nombre != 'SANTA ELENA' && listadoProvincias[index].nombre != 'GUAYAS'){
						$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
					}
				}
				/**QBE GAMA ALTA**/	
				/*else*/if($("#productos").val()=="213"){
					if(listadoProvincias[index].nombre != 'MANABI' && listadoProvincias[index].nombre != 'ESMERALDAS' && listadoProvincias[index].nombre != 'SANTA ELENA' && listadoProvincias[index].nombre != 'GUAYAS'){
						$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
					}
				}
				/**EDIFICIOS COSTA**/				
				else if($("#productos").val()=="214"){
					if(listadoProvincias[index].nombre == 'MANABI' || listadoProvincias[index].nombre == 'ESMERALDAS' || listadoProvincias[index].nombre == 'SANTA ELENA' || listadoProvincias[index].nombre == 'GUAYAS'){
						$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
					}
				}				
				/**EDIFICIOS GUAYAS**/				
				else if($("#productos").val()=="215"){
					if(listadoProvincias[index].nombre == 'GUAYAS'){
						$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
					}
				}
				/**PRODUCTO Edificios / Protecseguros**/
				
				/*else if($("#productos").val()=="217"){
					if(listadoProvincias[index].nombre != 'MANABI' && listadoProvincias[index].nombre != 'ESMERALDAS' && listadoProvincias[index].nombre != 'SANTA ELENA'){
						$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
					}
				}*/
				/**Casa Habitación Cerrados**/
				else if($("#productos").val()=="212"){
					if(listadoProvincias[index].nombre != 'MANABI' && listadoProvincias[index].nombre != 'ESMERALDAS' && listadoProvincias[index].nombre != 'SANTA ELENA'){
						$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
					}
				}
				/**Casa Habitación Costa**/
				else if($("#productos").val()=="216"){
					if(listadoProvincias[index].nombre != 'MANABI' && listadoProvincias[index].nombre != 'ESMERALDAS' && listadoProvincias[index].nombre != 'SANTA ELENA'){
						$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
					}
				}
				/**Casa Habitación Dinámico**/
				/*else if($("#productos").val()=="218"){
					if(listadoProvincias[index].nombre != 'MANABI' && listadoProvincias[index].nombre != 'ESMERALDAS' && listadoProvincias[index].nombre != 'SANTA ELENA'){
						$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
					}
				}*/
				/**Casa Habitación**/
				/*else if($("#productos").val()=="219"){
					if(listadoProvincias[index].nombre != 'MANABI' && listadoProvincias[index].nombre != 'ESMERALDAS' && listadoProvincias[index].nombre != 'SANTA ELENA'){
						$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
					}
				}*/
				/**PRODUCTO COMERCIAL**/
				/*else if($("#productos").val()=="186"){
					if(listadoProvincias[index].nombre != 'MANABI' && listadoProvincias[index].nombre != 'ESMERALDAS' && listadoProvincias[index].nombre != 'SANTA ELENA'){
						$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
					}
				}*/
				/**PRODUCTO COLEGIOS Y UNIVERSIDADES**/
				else if($("#productos").val()=="187"){
					if(listadoProvincias[index].nombre != 'MANABI' && listadoProvincias[index].nombre != 'ESMERALDAS' && listadoProvincias[index].nombre != 'SANTA ELENA'){
						$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
					}
				}
				/**INDUSTRIAL**/
				else if($("#productos").val()=="188"){
					if(listadoProvincias[index].nombre != 'MANABI' && listadoProvincias[index].nombre != 'ESMERALDAS' && listadoProvincias[index].nombre != 'SANTA ELENA'){
						$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
					}
				}
				/**OFICINAS Y CONSULTORIOS**/
				/*else if($("#productos").val()=="189"){
					if(listadoProvincias[index].nombre != 'MANABI' && listadoProvincias[index].nombre != 'ESMERALDAS' && listadoProvincias[index].nombre != 'SANTA ELENA'){
						$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
					}
				}*/
				/**Pymes - Mall del Sol**/
				else if($("#productos").val()=="210"){
					if(listadoProvincias[index].nombre != 'MANABI' && listadoProvincias[index].nombre != 'ESMERALDAS' && listadoProvincias[index].nombre != 'SANTA ELENA'){
						$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
					}
				}
				/**Pymes - Banco del Pacifico**/
				else if($("#productos").val()=="211"){
					if(listadoProvincias[index].nombre != 'MANABI' && listadoProvincias[index].nombre != 'ESMERALDAS' && listadoProvincias[index].nombre != 'SANTA ELENA'){
						$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
					}
				}
				else{
					$("#ubicacion_provincia_"+identificador).append("<option value='" + listadoProvincias[index].codigo + "'>" + listadoProvincias[index].nombre + "</option>");
				}
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
function cargarTiposIdentificacionPymes(seleccionada,tipo,noChange) {
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
					cargarDatosEnUPLAJuridica(data.datosUPLA);
				else
					cargarDatosEnUPLANatural(data.datosUPLA);
			}

		}
	});
}

function cargarPuntosVentaSessionGanadero(){
	$.ajax({
		url : '../PuntoVentaController',
		data : {
			"tipoConsulta" : "puntosVentaXProductoSession",
			"tipoObjeto":tipoObjeto
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