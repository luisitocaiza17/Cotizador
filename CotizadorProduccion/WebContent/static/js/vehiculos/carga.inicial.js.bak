/**
 *
 */
var tipoObjeto = document.getElementById("tipoObjetoCargaInicial").getAttribute("tipoObjetoValor");

function cargar() {
	//temporalmente se esconde el boton de crear tramite
	$("#crearTramiteWF").hide();
	//Se calcula la fecha de pago inicial

	var today = new Date();

	fechaPrimeraCuota = new Date(today.getTime() + (15 * 24 * 3600 * 1000));

	var dd = fechaPrimeraCuota.getDate();
	var mm = fechaPrimeraCuota.getMonth() + 1; //January is 0!
	var yyyy = fechaPrimeraCuota.getFullYear();

	if (dd < 10) {
		dd = '0' + dd;
	}

	if (mm < 10) {
		mm = '0' + mm;
	}

	var fechaCuotaInicial = dd + "/" + mm + "/" + yyyy;
	$("#fechaInicialPagos").val(fechaCuotaInicial);

	$(".datosVehiculo").blur(function () {
		editoVehiculo = true;
	});

	$(".datosVehiculo").on('select2-blur', function (e) {
		editoVehiculo = true;
	});

	$('.marca').on('select2-close', function (e) {
		var theEvent = e || window.event;
		var target = $(theEvent.target);
		var numero = target.attr("id").substr(6);
		var marca = $('#marca_' + numero).select2("val");
		cargarModelos('-1', marca, numero);

	});

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
	//Seccion Vinculos Existentes PN
	$("#vinculo1").hide();
	$("#vinculo2").hide();
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

	$(".parametroCargarEnsurance").change(function (e) {
		var theEvent = e || window.event;
		var target = $(theEvent.target);
		var aux = target.parent().prev().first().text().replace(':', '');
		var nombreParametro = "";
		var parametro = target.val();
		var numero = 0;

		if (aux == 'Placa') {
			nombreParametro = 'placa';
			numero = target.attr("id").substr(9);
		}
		if (aux == 'N&uacute;mero de Chasis') {
			nombreParametro = 'chasis';
			numero = target.attr("id").substr(10);
		}
		if (aux == 'N&uacute;mero de Motor') {
			nombreParametro = 'motor';
			numero = target.attr("id").substr(9);
		}

		$.ajax({
			url : '../ObjetoVehiculoController',
			data : {
				"tipoConsulta" : "cargarPorParametro",
				"parametro" : parametro,
				"nombreParametro" : nombreParametro
			},
			type : 'post',
			datatype : 'json',
			success : function (data) {
				var vehiculo = data.vehiculo;
				if (vehiculo.codigo != null && vehiculo.codigo != "") {
					$("#color_" + numero).select2("val", vehiculo.color);
					$("#marca_" + numero).select2("val", vehiculo.marca);
					// $(".modelo").select2("val",vehiculo.modelo);

					cargarModelos(vehiculo.modelo, vehiculo.marca, numero);

				}
			}
		});

	});

 QueryString = function () {
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

	$("#codigo_descuento").change(function () {

		var tipo = $("#codigo_descuento").val();
		var aux = $("#codigo_descuento option:selected").text();
		aux = aux.split('(')[1].replace(')', '').replace('%', '');
		var min = Number(aux.split('al')[0].trim());
		var max = Number(aux.split(' al ')[1].trim());

		$("#porcentaje_solicitud_descuento").val(min).attr('min', min).attr('max', max);

	});

	$("#wizard").steps({
		headerTag : "h2",
		bodyTag : "section",
		enableFinishButton : false,
		labels : {
			next : "Siguiente",
			previous : "Anterior",
		},
		onStepChanging : function (event, currentIndex, newIndex) {
			//Se desahibilita el control de los campos requeridos cuando se va hacia atras
			if (currentIndex > newIndex) {
				return true;
			}
			// Eventos al cambiar a la pestana Cliente
			if (newIndex === 1) {
				if (QueryString.id != null && QueryString != '')
					$("#cotizacion_id").text(QueryString.id);
				var cotizacionId = $("#cotizacion_id").text();
				// Validacion para que solo se cree una cotizacion
				if (currentIndex == 0) {
					crearCotizacion();
					var grupoPorProductoId = "";
					if (tipoObjeto != "VHDinamico")
						grupoPorProductoId = $('#productos').val();
					else
						grupoPorProductoId = "148";

					if ((QueryString.id == null || QueryString == '') && (cotizacionId == null || cotizacionId == '')) {
						agregarVehiculo();
						if (tipoObjeto != "VHDinamico") {
							var numvehiculos = $("#numero_vehiculos").val();
							for (var i = 0; i < numvehiculos; i++) {
								$("coberturaTR_check" + i + 1).prop('checked', true);
							}
						}
						cargarTablaCoberturas("", '1', "", "", grupoPorProductoId);
						cargarDatosVehiculo(1, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
					}
				}
			}
			// Eventos al cambiar a la pestana Productos
			if (newIndex === 2) {
				$(".loading-indicator").show();
				$("#tabbable").hide();
				var pago = $("#codigoPagoRegistrado").val();
				if (tieneDescuento && editoVehiculo && pago == '-1') {
					if (confirm("Si cambia los datos se eliminara la solicitud de descuento! Desea continuar?")) {
						eliminarDescuento();
						tieneDescuento=false;
					} else {
						editoVehiculo = false;
					}
				}
				if (!tieneDescuento && editoVehiculo && pago != '-1') {
					if (confirm("Si cambia los datos se eliminara la forma de pago! Desea continuar?")) {
						eliminarPago();
					} else {
						editoVehiculo = false;
					}
				}
				if (tieneDescuento && editoVehiculo && pago != '-1') {
					if (confirm("Si cambia los datos se eliminara la solicitud de descuento y la forma de pago! Desea continuar?")) {
						eliminarDescuento();
						eliminarPago();
						tieneDescuento=false;
					} else {
						editoVehiculo = false;
					}
				}

				// Validacion Flota de Vehiculos
				if (tipoObjeto != "VHDinamico") {
					var textoTasas = $("#tasas option:selected").text();
					if (textoTasas.toLowerCase().indexOf("flota") != -1) {
						var validacionFlota = Number($("#numero_vehiculos").val());
						if (validacionFlota < 4) {
							alert("Para cotizar una flota de vehiculos se deben ingresar al menos 4 vehiculos!!");
							// Error provocado para que no pase al siguiente paso
							$("#wizard").previous();
						}
					}
				}

				$.each($(".guardarVehiculo"), function (index) {
					if (editoVehiculo && !tieneDescuento) {
						$(this).trigger("click");
					}
				});
				
				// Guardamos el pago productos autoconsa
				var grupoPorProductoId = $('#productos').val();
				if(grupoPorProductoId == '24' ||grupoPorProductoId == '25' || grupoPorProductoId == '26')
					guardarPago("contado");
				
				if (currentIndex === 1)
					vehiculosCotizacion();
				if (QueryString.id == null) {
					cargarDescuentosPorUnidadNegocio("", $("#punto_venta").attr('unidadNegocio'));
					cargarPestanaEndosoBeneficiario($("#identificacion").val(), 0, "");
				}
			}

			if (newIndex === 3) {
				// Verificamos si el producto seleccionado tiene inspeccion o no.
				//var solicitudInspecci�nId = $("#tiene_inspeccion").val();
				var solicitudInspeccionId = $("#solicitudInspeccionId").val();
				var necesitaInspeccion = $("#necesitaInspeccion").val();

				if (necesitaInspeccion == "true" && (solicitudInspeccionId == "" || solicitudInspeccionId == null)&&!cargandoPorId) {
					$("#tabInspecciones").trigger("click");
					$("#msgPopupInspeccionError").show();
					$("#tipoInspeccion").trigger("change");

					return false;
				} else {
					if(solicitarInspeccionArr.length==0)// no ingreso ninguna placa
						{
							$("#tabInspecciones").trigger("click");
							$("#msgPopupInspeccionError").show();
							$("#tipoInspeccion").trigger("change");
							return false;
						}
					for (var i = 0; i < solicitarInspeccionArr.length; i++) {
						if (solicitarInspeccionArr[i] && (solicitudInspeccionId == "" || solicitudInspeccionId == null)&&!cargandoPorId) {
							$("#tabInspecciones").trigger("click");
							$("#msgPopupInspeccionError").show();
							$("#tipoInspeccion").trigger("change");
							return false;
							break;
						}
					}
					$("#msgPopupInspeccionError").hide();
				}

				if (parseInt($("#codigoPagoRegistrado").val()) == -1) {
					if (QueryString.carga == null || QueryString.carga2 == null){
						$("#tabFormasPago").trigger("click");
					}
					$("#msgPopupPago").attr("class", "alert alert-danger").html("Por favor seleccione su forma de pago.").fadeIn("slow");
					return false;
				} else {
					$("#msgPopupPagoGrabo").hide();
				}
				//Se pone el metodo "cargarTablaVehiculosFinal()" en este paso porque se desactivo el formulario de la UPLA
				cargarTablaVehiculosFinal();
				//verificamos que la cedula del asegurado sea igual a la del cliente
				if ($("#identificacion_asegurado").val().trim() != $("#identificacion").val().trim()) {
					$("#direccion_asegurado").fadeIn("slow");
					$("#nombre_direccion_asegurado").val($("#nombres_asegurado").val() + " " + $("#apellidos_asegurado").val());
				} else {
					$("#direccion_asegurado").fadeOut("slow");
				}
				if ($("#tipo_identificacion_principal").val() == "4") {
					$("#nombre_direccion_solicitante").val($("#nombre_completo").val());
				} else {
					$("#nombre_direccion_solicitante").val($("#nombres").val() + " " + $("#apellidos").val());
				}
				
				if($("#zona_direccion_solicitante").val()==null||$("#zona_direccion_solicitante").val()==""){
					cargarDireccionFactura();
				}
			}

			if (newIndex === 4) {

				if ($("#identificacion_asegurado").val().trim() != $("#identificacion").val().trim()) {

					var zonaSolicitante = $("#zona_direccion_solicitante").val();
					var provinciaSolicitante = $("#provincia_direccion_solicitante").val();
					var cantonSolicitante = $("#canton_direccion_solicitante").val();
					var parroquiaSolicitante = $("#parroquia_direccion_solicitante").val();
					var ciudadSolicitante = $("#ciudad_direccion_solicitante").val();
					var principalSolicitante = $("#principal_direccion_solicitante").val();
					var numeroSolicitante = $("#numero_direccion_solicitante").val();
					var secundariaSolicitante = $("#secundaria_direccion_solicitante").val();
					var referenciaSolicitante = $("#referencia_direccion_solicitante").val();
					var telefonoSolicitante = $("#telefono_direccion_solicitante").val();
					var celularSolicitante = $("#celular_direccion_solicitante").val();
					var emailSolicitante = $("#mail_direccion_solicitante").val();
					var identificacionSolicitante = $("#identificacion").val();

					var zonaAsegurado = $("#zona_direccion_asegurado").val();
					var provinciaAsegurado = $("#provincia_direccion_asegurado").val();
					var cantonAsegurado = $("#canton_direccion_asegurado").val();
					var parroquiaAsegurado = $("#parroquia_direccion_asegurado").val();
					var ciudadAsegurado = $("#ciudad_direccion_asegurado").val();
					var principalAsegurado = $("#principal_direccion_asegurado").val();
					var numeroAsegurado = $("#numero_direccion_asegurado").val();
					var secundariaAsegurado = $("#secundaria_direccion_asegurado").val();
					var referenciaAsegurado = $("#referencia_direccion_asegurado").val();
					var telefonoAsegurado = $("#telefono_direccion_asegurado").val();
					var celularAsegurado = $("#celular_direccion_asegurado").val();
					var emailAsegurado = $("#mail_direccion_asegurado").val();
					var identificacionAsegurado = $("#identificacion_asegurado").val();

					var mostrarError = $("#msgPopupDatosFacturaError");
					var mensajeError = $("#mensajeErrorDatosFactura");
					var mostrarExito = $("#msgPopupDatosFacturaExito");
					var mensajeExito = $("#mensajeExitoDatosFactura");

					// validaciones solicitante
					if ((zonaSolicitante == null || zonaSolicitante == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Seleccione una zona de la direcci&oacute;n");
						$("#zona_direccion_solicitante").focus();
						return false;
					}

					if ((provinciaSolicitante == null || provinciaSolicitante == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Seleccione una provincia de la direcci&oacute;n");
						$("#provincia_direccion_solicitante").focus();
						return false;
					}

					if (zonaSolicitante == "R") {
						if ((cantonSolicitante == null || cantonSolicitante == "") &&!cargandoPorId) {
							$(mostrarError).fadeIn("slow");
							$(mostrarExito).fadeOut("slow");
							$(mensajeError).html("Seleccione un canton de la direcci&oacute;n");
							$("#canton_direccion_solicitante").focus();
						}
						if ((parroquiaSolicitante == null || parroquiaSolicitante == "") &&!cargandoPorId) {
							$(mostrarError).fadeIn("slow");
							$(mostrarExito).fadeOut("slow");
							$(mensajeError).html("Seleccione una provincia de la direcci&oacute;n");
							$("#parroquia_direccion_solicitante").focus();
						}
					}

					if (zonaSolicitante == "U") {
						if ((ciudadSolicitante == null || ciudadSolicitante == "") &&!cargandoPorId) {
							$(mostrarError).fadeIn("slow");
							$(mostrarExito).fadeOut("slow");
							$(mensajeError).html("Seleccione una ciudad de la direcci&oacute;n");
							$("#ciudad_direccion_solicitante").focus();
						}
					}

					if ((principalSolicitante == null || principalSolicitante == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Ingrese una calle principal");
						$("#principal_direccion_solicitante").focus();
						return false;
					}

					if ((secundariaSolicitante == null || secundariaSolicitante == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Ingrese una calle secundaria");
						$("#secundaria_direccion_solicitante").focus();
						return false;
					}

					if ((numeroSolicitante == null || numeroSolicitante == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Ingrese un n&uacute;mero de la direcci&oacute;n");
						$("#numero_direccion_solicitante").focus();
						return false;
					}

					if ((telefonoSolicitante == null || telefonoSolicitante == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Ingrese un telefono");
						$("#telefono_direccion_solicitante").focus();
						return false;
					}

					if ((celularSolicitante == null || celularSolicitante == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Ingrese un telefono del solicitante");
						$("#celular_direccion_solicitante").focus();
						return false;
					}

					if ((emailSolicitante == null || emailSolicitante == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Ingrese un email del solicitante");
						$("#provincia_direccion_solicitante").focus();
						return false;
					}
					//fin validaciones solicitante

					// validaciones Asegurado
					if ((zonaAsegurado == null || zonaAsegurado == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Seleccione una zona de la direcci&oacute;n");
						$("#zona_direccion_asegurado").focus();
						return false;
					}

					if ((provinciaAsegurado == null || provinciaAsegurado == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Seleccione una provincia de la direcci&oacute;n");
						$("#provincia_direccion_asegurado").focus();
						return false;
					}

					if (zonaAsegurado == "R") {
						if ((cantonAsegurado == null || cantonAsegurado == "") &&!cargandoPorId) {
							$(mostrarError).fadeIn("slow");
							$(mostrarExito).fadeOut("slow");
							$(mensajeError).html("Seleccione un canton de la direcci&oacute;n");
							$("#canton_direccion_asegurado").focus();
						}
						if ((parroquiaAsegurado == null || parroquiaAsegurado == "") &&!cargandoPorId) {
							$(mostrarError).fadeIn("slow");
							$(mostrarExito).fadeOut("slow");
							$(mensajeError).html("Seleccione una provincia de la direcci&oacute;n");
							$("#parroquia_direccion_asegurado").focus();
						}
					}

					if (zonaAsegurado == "U") {
						if ((ciudadAsegurado == null || ciudadAsegurado == "") &&!cargandoPorId) {
							$(mostrarError).fadeIn("slow");
							$(mostrarExito).fadeOut("slow");
							$(mensajeError).html("Seleccione una ciudad de la direcci&oacute;n");
							$("#ciudad_direccion_asegurado").focus();
						}
					}

					if ((principalAsegurado == null || principalAsegurado == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Ingrese una calle principal");
						$("#principal_direccion_asegurado").focus();
						return false;
					}

					if ((secundariaAsegurado == null || secundariaAsegurado == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Ingrese una calle secundaria");
						$("#secundaria_direccion_asegurado").focus();
						return false;
					}

					if ((numeroAsegurado == null || numeroAsegurado == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Ingrese un n&uacute;mero de la direcci&oacute;n");
						$("#numero_direccion_asegurado").focus();
						return false;
					}

					if ((telefonoAsegurado == null || telefonoAsegurado == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Ingrese un telefono");
						$("#telefono_direccion_asegurado").focus();
						return false;
					}

					if ((celularAsegurado == null || celularAsegurado == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Ingrese un telefono del Asegurado");
						$("#celular_direccion_asegurado").focus();
						return false;
					}

					if ((emailAsegurado == null || emailAsegurado == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Ingrese un email del Asegurado");
						$("#provincia_direccion_asegurado").focus();
						return false;
					}

				} else {

					var zonaSolicitante = $("#zona_direccion_solicitante").val();
					var provinciaSolicitante = $("#provincia_direccion_solicitante").val();
					var cantonSolicitante = $("#canton_direccion_solicitante").val();
					var parroquiaSolicitante = $("#parroquia_direccion_solicitante").val();
					var ciudadSolicitante = $("#ciudad_direccion_solicitante").val();
					var principalSolicitante = $("#principal_direccion_solicitante").val();
					var numeroSolicitante = $("#numero_direccion_solicitante").val();
					var secundariaSolicitante = $("#secundaria_direccion_solicitante").val();
					var referenciaSolicitante = $("#referencia_direccion_solicitante").val();
					var telefonoSolicitante = $("#telefono_direccion_solicitante").val();
					var celularSolicitante = $("#celular_direccion_solicitante").val();
					var emailSolicitante = $("#mail_direccion_solicitante").val();
					var identificacionSolicitante = $("#identificacion").val();

					var mostrarError = $("#msgPopupDatosFacturaError");
					var mensajeError = $("#mensajeErrorDatosFactura");
					var mostrarExito = $("#msgPopupDatosFacturaExito");
					var mensajeExito = $("#mensajeExitoDatosFactura");

					// validaciones solicitante
					if ((zonaSolicitante == null || zonaSolicitante == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Seleccione una zona de la direcci&oacute;n");
						$("#zona_direccion_solicitante").focus();
						return false;
					}

					if ((provinciaSolicitante == null || provinciaSolicitante == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Seleccione una provincia de la direcci&oacute;n");
						$("#provincia_direccion_solicitante").focus();
						return false;
					}

					if (zonaSolicitante == "R") {
						if ((cantonSolicitante == null || cantonSolicitante == "") &&!cargandoPorId) {
							$(mostrarError).fadeIn("slow");
							$(mostrarExito).fadeOut("slow");
							$(mensajeError).html("Seleccione un canton de la direcci&oacute;n");
							$("#canton_direccion_solicitante").focus();
						}
						if ((parroquiaSolicitante == null || parroquiaSolicitante == "") &&!cargandoPorId) {
							$(mostrarError).fadeIn("slow");
							$(mostrarExito).fadeOut("slow");
							$(mensajeError).html("Seleccione una provincia de la direcci&oacute;n");
							$("#parroquia_direccion_solicitante").focus();
						}
					}

					if (zonaSolicitante == "U") {
						if ((ciudadSolicitante == null || ciudadSolicitante == "") &&!cargandoPorId) {
							$(mostrarError).fadeIn("slow");
							$(mostrarExito).fadeOut("slow");
							$(mensajeError).html("Seleccione una ciudad de la direcci&oacute;n");
							$("#ciudad_direccion_solicitante").focus();
						}
					}

					if ((principalSolicitante == null || principalSolicitante == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Ingrese una calle principal");
						$("#principal_direccion_solicitante").focus();
						return false;
					}

					if ((secundariaSolicitante == null || secundariaSolicitante == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Ingrese una calle secundaria");
						$("#secundaria_direccion_solicitante").focus();
						return false;
					}

					if ((numeroSolicitante == null || numeroSolicitante == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Ingrese un n&uacute;mero de la direcci&oacute;n");
						$("#numero_direccion_solicitante").focus();
						return false;
					}

					if ((telefonoSolicitante == null || telefonoSolicitante == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Ingrese un telefono");
						$("#telefono_direccion_solicitante").focus();
						return false;
					}

					if ((celularSolicitante == null || celularSolicitante == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Ingrese un telefono del solicitante");
						$("#celular_direccion_solicitante").focus();
						return false;
					}

					if ((emailSolicitante == null || emailSolicitante == "") &&!cargandoPorId) {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("Ingrese un email del solicitante");
						$("#provincia_direccion_solicitante").focus();
						return false;
					}
				}

				cargarTablaVehiculosFinal();

			}

			if (newIndex === 5) {
			/*	var tieneArchivoPolizaFirmada = $("#tienePolizaFirmada").val();
				tieneDocumento("PolizaFirmada",tieneArchivoPolizaFirmada);
				
				var tieneArchivoAutorizacionDebito = $("#tieneAutorizacionDebito").val();
				tieneDocumento("AutorizacionDebito",tieneArchivoAutorizacionDebito);
				
				var tieneArchivoFormularioUPLA = $("#tieneFormularioUPLA").val();
				tieneDocumento("FormularioUPLA",tieneArchivoFormularioUPLA);
				
				var tieneArchivoCaratulaCotizacion = $("#tieneCaratulaCotizacion").val();
				tieneDocumento("CaratulaCotizacion",tieneArchivoCaratulaCotizacion);
				
				var tieneArchivoFactura = $("#tieneFactura").val();
				tieneDocumento("Factura",tieneArchivoFactura);*/
			}

			return $("#wizard").valid();
		},
	});

	//carga de cotizaciones nuevas
	if (QueryString.id == null || QueryString == '') {
		cargaInicial('1');
		cargarTiposIdentificacion('', '');
	} else {
		cargarPorId(QueryString.id);
		vehiculosCotizacion();
	}

	$(".provincia").change(function (event) {
		var theEvent = event || window.event;
		var target = $(theEvent.target);
		var tipo = target.attr("id").replace("provincia_", "");
		cargarCiudades("", $(target).val(), tipo);
		cargarCantones("", $(target).val(), tipo);

	});

	$(".canton").change(function (event) {
		var theEvent = event || window.event;
		var target = $(theEvent.target);
		var tipo = target.attr("id").replace("canton_", "");
		cargarParroquias("", $(target).val(), tipo);

	});

	var dialogCorreos = $("#correos_certificado").dialog({
			autoOpen : false,
			closeOnEscape : false,
			modal : true,
			height : 'auto',
			width : 350,
			draggable : false,
			resizable : false
		});

}

/*
 * METODO QUE CARGA COTIZACIONES NUEVAS
 */
function cargaInicial(valor) {
	var numeroVeces = Number(valor);

	// Consultar las sucursales
	var sucursales = cargarSucursales("", numeroVeces);

	if (numeroVeces == 1) {

		// Cargar Grupos de Productos
		if (tipoObjeto != "VHDinamico")
			gruposProductos("");

		// Consultar Puntos Venta x Producto
		cargarPuntosVenta("");

		// Consultar las vigencia de las polizas
		cargarVigenciasPolizas("");

		// Consultar los agentes
		cargarAgentes("", "");

		// Consultar los tipos de identificacion
		cargarTiposIdentificacion("", "", false);

		// Consultar los datos de las inspeccion
		cargarOrigenInspeccion("");

		// Consultar las formas de pago
		cargarFormasPago("", "formasPago");
		cargarFormasPago("", "intitucionesFinancieras");
		cargarFormasPago("", "aniosVigencia");

		// Consultar las coberturas del producto vehiculo
		var grupoPorProductoId = "";
		if (tipoObjeto != "VHDinamico")
			grupoPorProductoId = $('#productos').val();
		else
			grupoPorProductoId = "148";
		cargarTablaCoberturas("", numeroVeces, "", "", grupoPorProductoId);

		// Consultar los Motivos de Descuento
		cargarMotivosDescuento("");

		// Cargar descuentos
		cargarDescuentos("");

		cargarMarcas("-1", numeroVeces);
		cargarColores("-1", numeroVeces);

		$(".ui-dialog-titlebar").attr("style", $(".panel-heading").attr("style"))
		.addClass($(".panel-heading").attr("class"));
	}
}

/*
 * METODO QUE RECIBE EL ID DE LA SUCURSAL Y EL NUMERO DEL VEHICULO
 * DENTRO DE LA COTIZACION. SETEA LA SUCURSAL CORRESPONDIENTE AL ID
 * QUE RECIBE.
 */
function cargarSucursales(seleccionada, numero) {
	$.ajax({
		url : '../SucursalController',
		data : {
			"tipoConsulta" : "encontrarSucursalesActivas",
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var options = '';
			options = '<option value="">&nbsp;&nbsp;Seleccione</option>';

			for (var i = 0; i < data.sucursales.length; i++) {
				options += '<option value="' + data.sucursales[i].id + '">' + data.sucursales[i].nombre + '</option>';
			}
			$("#sucursales" + numero.toString()).html(options).val(seleccionada).attr('required', 'required');

		}

	});
}

/*
 * METODO QUE RECIBE EL ID DEL PUNTO DE VENTA Y SETEA EL CORRESPONDIENTE AL ID
 * QUE RECIBE.
 */
function cargarPuntosVenta(seleccionada) {
	$.ajax({
		url : '../PuntoVentaController',
		data : {
			"tipoConsulta" : "puntosVentaXProducto",
			"tipoObjeto" : tipoObjeto,
			"grupoPorProductoId" : $('#productos').val(),

		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var sucursales = data.sucursales;
			var options = '';
			options = '<option value="">Seleccione un punto de venta</option>';
			var contador;
			for (var j = 0; j < sucursales.length; j++) {
				contador = 0;
				for (var i = 0; i < data.puntosVenta.length; i++) {
					if (data.puntosVenta[i].sucursal == sucursales[j].id) {
						contador++;
						if (contador == 1) {
							options += '<option value="" disabled="disabled" class="seleccionado" ">' + sucursales[j].nombre + '</option>';
						}
						options += '<option value="' + data.puntosVenta[i].id + '" pxpv="' + data.puntosVenta[i].productoPorPuntoDeVenta + '"  >&nbsp;&nbsp;&nbsp;&nbsp;' + data.puntosVenta[i].nombre + '</option>';
					}
				}
			}
			$("#punto_venta").html(options).val(seleccionada);
		}
	});
}

/*
 * Cargar Puntos Venta por session (Buscador)
 *
 */
function cargarPuntosVentaSession() {
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
			var puntosVenta = arregloUnicoJSON(data.puntosVenta);
			var contador;
			for (var j = 0; j < sucursales.length; j++) {
				contador = 0;
				for (var i = 0; i < puntosVenta.length; i++) {
					if (puntosVenta[i].sucursal == sucursales[j].id) {
						contador++;
						if (contador == 1) {
							options += '<option value="" disabled="disabled" class="seleccionado" ">' + sucursales[j].nombre + '</option>';
						}
						options += '<option value="' + puntosVenta[i].id + '" >&nbsp;&nbsp;&nbsp;&nbsp;' + puntosVenta[i].nombre + '</option>';
					}
				}
			}

			$("#punto_venta_session").html(options);
		}
	});
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

/*
 * METODO QUE RECIBE EL ID DEL AGENTE Y SETEA EL AGENTE CORRESPONDIENTE AL ID
 * QUE RECIBE.
 */
function cargarAgentes(seleccionada, valorComision) {
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
			$("#agentes").html(options).val(seleccionada);
			if (seleccionada != "") {
				obtenerAgenteComision(valorComision);
			}
		}
	});
}

/*
 * METODO QUE RECIBE EL ID Y SETEA EL TIPO DE IDENTIFICACION CORRESPONDIENTE AL ID
 * QUE RECIBE.
 */
function cargarTiposIdentificacion(seleccionada, tipo, noChange) {
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

			if (tipo != '') {
				if (noChange) {
					$("#tipo_identificacion_" + tipo).html(options).val(seleccionada).trigger('change');
				} else {
					$("#tipo_identificacion_" + tipo).html(options).val(seleccionada);
				}
			} else
				$(".tipo_identificacion").html(options).val('1');
		}
	});
}

/*
 * CARGA LOS LUGARES DE ORIGEN DE LA INSPECCCION
 */
function cargarOrigenInspeccion(seleccionado) {
	$.ajax({
		url : '../DistanciaInspectorController',
		data : {
			"tipoConsulta" : "encontrarOrigen"
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			var listadoOrigenInspector = datos.listadoOrigenInspector;
			$("#origenInspeccion").append('<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>');
			$.each(listadoOrigenInspector, function (index) {
				$("#origenInspeccion").append('<option value="' + listadoOrigenInspector[index].origen + '">' + listadoOrigenInspector[index].origen + '</option>');
			});
			if (seleccionado != null && seleccionado != '')
				$("#origenInspeccion").val(seleccionado).attr("disabled", "disabled");
		}
	});
}

/*
 * METODO QUE CARGA LAS COBERTURAS PARA VEHICULOS DINAMICOS
 */
function cargarTablaCoberturas(seleccionada, numero, escogidas, paquete, grupoPorProductoId) {
	var coberturasPT = ['1528073305754',
		'1528079531039',
		'1539870032603',
		'1822474698752',
		'7086801709200'];
	if (casoEspecial) {
		$.ajax({
			url : '../CoberturaController',
			data : {
				"tipoConsulta" : "encontrarTodos",
				"tipoObjeto" : tipoObjeto,
				"grupoPorProductoId" : grupoPorProductoId,
			},
			type : 'post',
			datatype : 'json',
			success : function (data) {
				var tablaBlackCobertura = '<table id="coberturaTablaPaqueteBlack">';
				var tablaBlueCobertura = '<table id="coberturaTablaPaqueteBlue">';
				var tablaGoldCobertura = '<table id="coberturaTablaPaqueteGold">';
				var tablaSinCobertura = '<table id="coberturaTablaSinPaquete">';
				var listaCoberturas = data.listadoCobertura;
				var coberturasPorPaquetes = data.coberturasPorPaquetes;

				// Verificacion si existen planes seleccionados - sino no se muestra
				var coberturas_paquete_black = 0;
				var coberturas_paquete_blue = 0;
				var coberturas_paquete_gold = 0;
			
				//var tablaexcesoRC = '<input type="text" id="'++'" value="'+listaCoberturas[i].tasa+'">';

				$("#tabla_deducibles" + numero).hide();
				for (var i = 0; i < listaCoberturas.length; i++) {
					if (listaCoberturas[i].mostrar_cotizador == '1') {
						for (var k = 0; k < coberturasPorPaquetes.length; k++) {

							if (coberturasPorPaquetes[k].paquete_id == 1 && listaCoberturas[i].codigo == coberturasPorPaquetes[k].cobertura_id) {

								tablaBlackCobertura += '<tr><td colspan="5" style="padding-right:10px"><input type="checkbox" checked="checked" disabled="disabled" class="check_Black_Cobertura_numero datosCobertura datosVehiculoClick bloquearEmitido" id="check_Black_Cobertura_' + listaCoberturas[i].codigo + '_tabla_numero" />' + listaCoberturas[i].nombre + '</td>' +
								'<td></td><td colspan="5" style="padding-right:10px">' + listaCoberturas[i].descripcion + '</td><td hidden="hidden" style="padding-left:10px" colspan="5" align="right" class="valor_Black_Cobertura_tabla_numero" id="valor_Black_Cobertura_' + listaCoberturas[i].codigo + '_tabla_numero"></td><input type="hidden" class="' + listaCoberturas[i].tipoTasa + '" value="' + listaCoberturas[i].tasa + '"></td></tr>';
								coberturas_paquete_black++;
							}
							if (coberturasPorPaquetes[k].paquete_id == 2 && listaCoberturas[i].codigo == coberturasPorPaquetes[k].cobertura_id) {

								tablaBlueCobertura += '<tr><td colspan="5" style="padding-right:10px"><input type="checkbox" checked="checked" disabled="disabled" class="check_Blue_Cobertura_numero datosCobertura datosVehiculoClick bloquearEmitido" id="check_Blue_Cobertura_' + listaCoberturas[i].codigo + '_tabla_numero" />' + listaCoberturas[i].nombre + '</td>' +
								'<td></td><td colspan="5" style="padding-right:10px">' + listaCoberturas[i].descripcion + '</td><td hidden="hidden" style="padding-left:10px" colspan="5" align="right" class="valor_Blue_Cobertura_tabla_numero" id="valor_Blue_Cobertura_' + listaCoberturas[i].codigo + '_tabla_numero"></td><input type="hidden" class="' + listaCoberturas[i].tipoTasa + '" value="' + listaCoberturas[i].tasa + '"></td></tr>';
								coberturas_paquete_blue++;
							}
							if (coberturasPorPaquetes[k].paquete_id == 3 && listaCoberturas[i].codigo == coberturasPorPaquetes[k].cobertura_id) {

								tablaGoldCobertura += '<tr><td colspan="5" style="padding-right:10px"><input type="checkbox" checked="checked" disabled="disabled" class="check_Gold_Cobertura_numero datosCobertura datosVehiculoClick bloquearEmitido" id="check_Gold_Cobertura_' + listaCoberturas[i].codigo + '_tabla_numero" />' + listaCoberturas[i].nombre + '</td>' +
								'<td></td><td colspan="5" style="padding-right:10px">' + listaCoberturas[i].descripcion + '</td><td hidden="hidden" style="padding-left:10px" colspan="5" align="right" class="valor_Gold_Cobertura_tabla_numero" id="valor_Gold_Cobertura_' + listaCoberturas[i].codigo + '_tabla_numero"></td><input type="hidden" class="' + listaCoberturas[i].tipoTasa + '" value="' + listaCoberturas[i].tasa + '"></td></tr>';
								coberturas_paquete_gold++;
							}

						}

						if ((listaCoberturas[i].codigo == '6348540415022' || listaCoberturas[i].codigo == '6349173767914') && $.inArray(listaCoberturas[i].codigo, coberturasPT) == -1) {
							tablaSinCobertura += '<tr class="esconderPT"><td colspan="5" style="padding-right:10px"><input type="checkbox" class="check_Sin_Cobertura_numero datosCobertura datosVehiculoClick bloquearEmitido" onClick="calcularTotalSinPaquete(numero); $(\'#excesoResponsabilidadCivil_'
							 + listaCoberturas[i].codigo
							 + '_numero\').trigger(\'change\')" id="check_Sin_Cobertura_'
							 + listaCoberturas[i].codigo
							 + '_tabla_numero" />'
							 + listaCoberturas[i].nombre
							 + '</td><td></td><td colspan="5" style="padding-right:10px">'
							 + listaCoberturas[i].descripcion
							 + '</td><td hidden="hidden" style="padding-left:10px" colspan="5" align="right" class="valor_Sin_Cobertura_tabla_numero" id="valor_Sin_Cobertura_'
							 + listaCoberturas[i].codigo
							 + '_tabla_numero"></td><input type="hidden" class="'
							 + listaCoberturas[i].tipoTasa
							 + '" value="'
							 + listaCoberturas[i].tasa
							 + '"></td>'
							 + '<td><input type="number" id="excesoResponsabilidadCivil_'
							 + listaCoberturas[i].codigo
							 + '_numero" min="0" onchange="cambioExcesoRC(\'excesoResponsabilidadCivil_'
							 + listaCoberturas[i].codigo
							 + '_numero\',numero,event);" class="valorExcesoRC bloquearEmitido" max="'
							 + data.limiteExcesoRC
							 + '" value="'
							 + listaCoberturas[i].valorMonto
							 + '" check="check_Sin_Cobertura_'
							 + listaCoberturas[i].codigo
							 + '_tabla_numero"></td>' + '</tr>';

						} else {
							if ($.inArray(listaCoberturas[i].codigo, coberturasPT) == -1) {
								tablaSinCobertura += '<tr class="esconderPT"><td colspan="5" style="padding-right:10px"><input type="checkbox" class="check_Sin_Cobertura_numero datosCobertura datosVehiculoClick bloquearEmitido" onClick="calcularTotalSinPaquete(numero)" id="check_Sin_Cobertura_'
								 + listaCoberturas[i].codigo
								 + '_tabla_numero" />'
								 + listaCoberturas[i].nombre
								 + '</td>'
								 + '<td></td><td colspan="5" style="padding-right:10px">'
								 + listaCoberturas[i].descripcion
								 + '</td><td hidden="hidden" style="padding-left:10px" colspan="5" align="right" class="valor_Sin_Cobertura_tabla_numero" id="valor_Sin_Cobertura_'
								 + listaCoberturas[i].codigo
								 + '_tabla_numero"></td><input type="hidden" class="'
								 + listaCoberturas[i].tipoTasa
								 + '" value="'
								 + listaCoberturas[i].tasa
								 + '"></td></tr>';
							} else {
								if ($.inArray(listaCoberturas[i].codigo, coberturasPT) != -1) {
									tablaSinCobertura += '<tr><td colspan="5" style="padding-right:10px"><input type="checkbox" class="check_Sin_Cobertura_numero datosCobertura datosVehiculoClick bloquearEmitido" onClick="calcularTotalSinPaquete(numero)" id="check_Sin_Cobertura_'
									 + listaCoberturas[i].codigo
									 + '_tabla_numero" />'
									 + listaCoberturas[i].nombre
									 + '</td>'
									 + '<td></td><td colspan="5" style="padding-right:10px">'
									 + listaCoberturas[i].descripcion
									 + '</td><td hidden="hidden" style="padding-left:10px" colspan="5" align="right" class="valor_Sin_Cobertura_tabla_numero" id="valor_Sin_Cobertura_'
									 + listaCoberturas[i].codigo
									 + '_tabla_numero"></td><input type="hidden" class="'
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
				tablaSinCobertura += '</table>';

				$("#lista_coberturas").html(tablaBlackCobertura + tablaBlueCobertura + tablaGoldCobertura + tablaSinCobertura);
				//falta escoger la seleccionada

				var sumaCoberturas = 0;

				var coberturasBlack = $(".check_Black_Cobertura_" + numero);
				var coberturasBlue = $(".check_Blue_Cobertura_" + numero);
				var coberturasGold = $(".check_Gold_Cobertura_" + numero);
				
				if (coberturas_paquete_black > 0) {
					$("#div_paquete_black_" + numero).show();
				}
				if (coberturas_paquete_black == 0) {
					$("#div_paquete_black_" + numero).hide();
				}
				if (coberturas_paquete_blue > 0) {
					$("#div_paquete_blue_" + numero).show();
				}
				if (coberturas_paquete_blue == 0) {
					$("#div_paquete_blue_" + numero).hide();
				}
				if (coberturas_paquete_gold > 0) {
					$("#div_paquete_gold_" + numero).show();
				}
				if (coberturas_paquete_gold == 0) {
					$("#div_paquete_gold_" + numero).hide();
				}
				var bandera = false;
				if (escogidas != null || paquete != null) {
					var aux;
					var ids = [];
					$.each(escogidas, function (index) {
						if (ids.indexOf(escogidas[index].coberturaId) == -1) {
							ids[index] = escogidas[index].coberturaId;
							if (escogidas[index].nemotecnico == "TORI") {
								$("#coberturaTR_check" + numero).prop("checked", false);
								$("#coberturaTR_check" + numero).click();
								editoVehiculo = false;
								bandera = true;
							}
						}
					});

					//if(!bandera&&!$("#coberturaTR_check"  + numero).is(":checked"))

					//	$("#coberturaTR_check"  + numero).trigger("click").attr('checked','checked');

					if (paquete == 1)
						aux = "check_Black_Cobertura_";
					if (paquete == 2)
						aux = "check_Blue_Cobertura_";
					if (paquete == 3)
						aux = "check_Gold_Cobertura_";
					if (paquete == 5)
						aux = "check_Sin_Cobertura_";

					var coberturasPorPaquete = $("." + aux + numero);

					$.each(coberturasPorPaquete, function (index) {
						var chk = coberturasPorPaquete[index];
						if (ids.indexOf($(chk).attr('id').replace(aux, '').replace('_tabla_' + numero, '')) != -1) {
							$(chk).prop("checked", true);
							$(chk).attr("checked", "checked");
						}
					});
				}
				$(".check_Black_Cobertura_1 .datosCobertura").bind({
					click : function () {
						editoVehiculo = true;
					}
				});
			}
		});
	} else {

		$.ajax({
			url : '../CoberturaController',
			data : {
				"tipoConsulta" : "encontrarTodos",
				"tipoObjeto" : tipoObjeto,
				"grupoPorProductoId" : grupoPorProductoId,
			},
			type : 'post',
			datatype : 'json',
			success : function (data) {
				var tablaBlackCobertura = '<table id="coberturaTablaPaqueteBlack">';
				var tablaBlueCobertura = '<table id="coberturaTablaPaqueteBlue">';
				var tablaGoldCobertura = '<table id="coberturaTablaPaqueteGold">';
				var tablaSinCobertura = '<table id="coberturaTablaSinPaquete">';
				var listaCoberturas = data.listadoCobertura;
				var coberturasPorPaquetes = data.coberturasPorPaquetes;

				// Verificacion si existen planes seleccionados - sino no se muestra
				var coberturas_paquete_black = 0;
				var coberturas_paquete_blue = 0;
				var coberturas_paquete_gold = 0;
				
				//var tablaexcesoRC = '<input type="text" id="'++'" value="'+listaCoberturas[i].tasa+'">';

				$("#tabla_deducibles").hide();
				for (var i = 0; i < listaCoberturas.length; i++) {
					if (listaCoberturas[i].mostrar_cotizador == '1') {
						for (var k = 0; k < coberturasPorPaquetes.length; k++) {

							if (coberturasPorPaquetes[k].paquete_id == 1 && listaCoberturas[i].codigo == coberturasPorPaquetes[k].cobertura_id) {

								tablaBlackCobertura += '<tr><td colspan="5" style="padding-right:10px"><input type="checkbox" checked="checked" disabled="disabled" class="check_Black_Cobertura_ datosCobertura datosVehiculoClick bloquearEmitido" id="check_Black_Cobertura_' + listaCoberturas[i].codigo + '_tabla_" />' + listaCoberturas[i].nombre + '</td>' +
								'<td></td><td colspan="5" style="padding-right:10px">' + listaCoberturas[i].descripcion + '</td><td hidden="hidden" style="padding-left:10px" colspan="5" align="right" class="valor_Black_Cobertura_tabla_" id="valor_Black_Cobertura_' + listaCoberturas[i].codigo + '_tabla_"></td><input type="hidden" class="' + listaCoberturas[i].tipoTasa + '" value="' + listaCoberturas[i].tasa + '"></td></tr>';
								coberturas_paquete_black++;
							}
							if (coberturasPorPaquetes[k].paquete_id == 2 && listaCoberturas[i].codigo == coberturasPorPaquetes[k].cobertura_id) {

								tablaBlueCobertura += '<tr><td colspan="5" style="padding-right:10px"><input type="checkbox" checked="checked" disabled="disabled" class="check_Blue_Cobertura_ datosCobertura datosVehiculoClick bloquearEmitido" id="check_Blue_Cobertura_' + listaCoberturas[i].codigo + '_tabla_" />' + listaCoberturas[i].nombre + '</td>' +
								'<td></td><td colspan="5" style="padding-right:10px">' + listaCoberturas[i].descripcion + '</td><td hidden="hidden" style="padding-left:10px" colspan="5" align="right" class="valor_Blue_Cobertura_tabla_" id="valor_Blue_Cobertura_' + listaCoberturas[i].codigo + '_tabla_"></td><input type="hidden" class="' + listaCoberturas[i].tipoTasa + '" value="' + listaCoberturas[i].tasa + '"></td></tr>';
								coberturas_paquete_blue++;
							}
							if (coberturasPorPaquetes[k].paquete_id == 3 && listaCoberturas[i].codigo == coberturasPorPaquetes[k].cobertura_id) {

								tablaGoldCobertura += '<tr><td colspan="5" style="padding-right:10px"><input type="checkbox" checked="checked" disabled="disabled" class="check_Gold_Cobertura_ datosCobertura datosVehiculoClick bloquearEmitido" id="check_Gold_Cobertura_' + listaCoberturas[i].codigo + '_tabla_" />' + listaCoberturas[i].nombre + '</td>' +
								'<td></td><td colspan="5" style="padding-right:10px">' + listaCoberturas[i].descripcion + '</td><td hidden="hidden" style="padding-left:10px" colspan="5" align="right" class="valor_Gold_Cobertura_tabla_" id="valor_Gold_Cobertura_' + listaCoberturas[i].codigo + '_tabla_"></td><input type="hidden" class="' + listaCoberturas[i].tipoTasa + '" value="' + listaCoberturas[i].tasa + '"></td></tr>';
								coberturas_paquete_gold++;
							}
							
						}

						if ((listaCoberturas[i].codigo == '6348540415022' || listaCoberturas[i].codigo == '6349173767914') && $.inArray(listaCoberturas[i].codigo, coberturasPT) == -1) {
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
							 + '" check="check_Sin_Cobertura_'
							 + listaCoberturas[i].codigo
							 + '_tabla_numero"></td>' + '</tr>';

						} else {
							if ($.inArray(listaCoberturas[i].codigo, coberturasPT) == -1) {
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
							} else {
								if ($.inArray(listaCoberturas[i].codigo, coberturasPT) != -1) {
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
				tablaSinCobertura += '</table>';

				$("#lista_coberturas").html(tablaBlackCobertura + tablaBlueCobertura + tablaGoldCobertura + tablaSinCobertura);
				//falta escoger la seleccionada

				var sumaCoberturas = 0;

				var coberturasBlack = $(".check_Black_Cobertura_");
				var coberturasBlue = $(".check_Blue_Cobertura_");
				var coberturasGold = $(".check_Gold_Cobertura_");
				
				var numPaquetes = 0;

				if (coberturas_paquete_black > 0) {
					$("#div_paquete_black_").show();
					numPaquetes++;
				}
				if (coberturas_paquete_black == 0) {
					$("#div_paquete_black_").parent().hide().attr("width", "0%");
				}
				if (coberturas_paquete_blue > 0) {
					$("#div_paquete_blue_").show();
					numPaquetes++;
				}
				if (coberturas_paquete_blue == 0) {
					$("#div_paquete_blue_").parent().hide().attr("width", "0%");
				}
				if (coberturas_paquete_gold > 0) {
					$("#div_paquete_gold_").show();
					numPaquetes++;
				}
				if (coberturas_paquete_gold == 0) {
					$("#div_paquete_gold_").parent().hide().attr("width", "0%");
				}

				if (numPaquetes != 0) {
					if (coberturas_paquete_black > 0) {
						$("#div_paquete_black_").parent().attr("width", 72 / numPaquetes + "%");
					}
					if (coberturas_paquete_blue > 0) {
						$("#div_paquete_blue_").parent().attr("width", 72 / numPaquetes + "%");
					}
					if (coberturas_paquete_gold > 0) {
						$("#div_paquete_gold_").parent().attr("width", 72 / numPaquetes + "%");
					}
				} else {
					$("#div_paquete_gold_").parent().next().attr("width", "90%");
				}

				var bandera = false;
				if (escogidas != null || paquete != null) {
					var aux;
					var ids = [];
					$.each(escogidas, function (index) {
						if (ids.indexOf(escogidas[index].coberturaId) == -1) {
							ids[index] = escogidas[index].coberturaId;
							if (escogidas[index].nemotecnico == "TORI") {
								$("#coberturaTR_check").prop("checked", false);
								$("#coberturaTR_check").click();
								editoVehiculo = false;
								bandera = true;
							}
						}
					});

					//if(!bandera&&!$("#coberturaTR_check"  + numero).is(":checked"))

					//	$("#coberturaTR_check"  + numero).trigger("click").attr('checked','checked');

					if (paquete == 1)
						aux = "check_Black_Cobertura_";
					if (paquete == 2)
						aux = "check_Blue_Cobertura_";
					if (paquete == 3)
						aux = "check_Gold_Cobertura_";
					if (paquete == 5)
						aux = "check_Sin_Cobertura_";

					var coberturasPorPaquete = $("." + aux);

					$.each(coberturasPorPaquete, function (index) {
						var chk = coberturasPorPaquete[index];
						if (ids.indexOf($(chk).attr('id').replace(aux, '').replace('_tabla_', '')) != -1) {
							$(chk).prop("checked", true);
							$(chk).attr("checked", "checked");
						}
					});
				}
				$(".check_Black_Cobertura_1 .datosCobertura").bind({
					click : function () {
						editoVehiculo = true;
					}
				});
			}
		});
	}

	$(".datosVehiculoClick").click(function () {
		editoVehiculo = true;
	});
}

/*
 * METODO QUE CARGA LOS MOTIVOS DE DESCUENTO Y CUANDO RECIBE EL MOTIVO SELECCIONADO
 * SETEA EL VALOR QUE CORRESPONDE.
 */
function cargarMotivosDescuento(seleccionada) {

	$.ajax({
		url : '../MotivoDescuentoController',
		data : {
			"tipoConsulta" : "encontrarTodosActivos"
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var options = '';
			options = '<option value="">Seleccione un motivo del descuento </option>';
			for (var i = 0; i < data.listadoMotivoDescuento.length; i++) {
				options += '<option value="' + data.listadoMotivoDescuento[i].codigo + '">' + data.listadoMotivoDescuento[i].nombre + '</option>';
			}
			$("#motivo_descuento").html(options).val(seleccionada);

		}
	});
}

/*
 * METODO QUE CARGA LOS DESCUENTOS Y CUANDO RECIBE EL DESCUENTO SELECCIONADO
 * SETEA EL VALOR QUE CORRESPONDE.
 */
function cargarDescuentos(seleccionada) {
	$.ajax({
		url : '../DescuentoController',
		data : {
			"tipoConsulta" : "encontrarTodos"
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var listadoDescuento = data.listadoDescuento;
			var options = '<option value="">Seleccione un tipo de descuento descuento </option>';
			$.each(listadoDescuento, function (index) {
				options += "<option value=" + listadoDescuento[index].codigo + ">" + listadoDescuento[index].nombre + "</option>";
			});
			$("#codigo_descuento").html(options).val(seleccionada);
		}
	});
}

/*
 * METODO QUE CARGA LOS DESCUENTOS EN BASE A LA UNIDAD DE NEGOCIO
 * SETEA EL VALOR QUE CORRESPONDE.
 */
function cargarDescuentosPorUnidadNegocio(seleccionada, unidadNegocio) {
	$.ajax({
		url : '../DescuentoController',
		data : {
			"tipoConsulta" : "encontrarPorUnidadNegocio",
			"unidadNegocioId" : unidadNegocio
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var listadoDescuento = data.listadoDescuento;
			var options = '<option value="" selected="selected">Seleccione un tipo de descuento</option>';
			$.each(listadoDescuento, function (index) {
				options += "<option value=" + listadoDescuento[index].codigo + " minimo='" + listadoDescuento[index].rangoInicial + "' maximo='" + listadoDescuento[index].rangoFinal + "'>" + listadoDescuento[index].nombre + "</option>";
			});
			$("#codigo_descuento").html(options).val(seleccionada);
		}
	});
}

/*
 * METODO QUE RECIBE EL ID DE LA MARCA Y EL NUMERO DEL VEHICULO
 * DENTRO DE LA COTIZACION. SETEA LA MARCA CORRESPONDIENTE AL ID
 * QUE RECIBE.
 */
function cargarMarcas(id, numero) {
	$.ajax({
		url : '../MarcaController',
		data : {
			"tipoConsulta" : "cargaSelect2"
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {

			var marcas2 = data.listadoMarca;

			$('#marca_' + numero).select2({
				data : marcas2,
				placeholder : 'Seleccione una marca'
			});
			if (numero != null)
				$('#marca_' + numero).select2("val", id).attr('required', 'required');
		}
	});
}

/*
 * METODO QUE RECIBE EL ID DEL COLOR Y EL NUMERO DEL VEHICULO
 * DENTRO DE LA COTIZACION. SETEA EL COLOR CORRESPONDIENTE AL ID
 * QUE RECIBE.
 */
function cargarColores(id, numero) {
	$.ajax({
		url : '../ColorController',
		data : {
			"tipoConsulta" : "cargaSelect2"
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {

			var colores = data.listadoColor;

			$('#color_' + numero).select2({
				data : colores,
				placeholder : 'Seleccione un color'

			});

			if (numero != null)
				$('#color_' + numero).select2("val", id).attr('required', 'required');
		}
	});
}

/*
 * METOO PARA CARGAR LAS PROVINCIAS DISPONIBLES
 */
function cargarProvincias(seleccionada, tipo) {
	var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';

	$.ajax({
		url : '../ProvinciaController',
		data : {
			"tipoConsulta" : "encontrarTodos"
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			for (var i = 0; i < datos.listadoProvincia.length; i++)
				options += '<option value="' + datos.listadoProvincia[i].codigo + '">' + datos.listadoProvincia[i].nombre + '</option>';

			if (tipo == "")
				$(".provincia").html(options).val(seleccionada);
			else
				$("#provincia_" + tipo).html(options).val(seleccionada);
		}
	});

}

/*
 * METODO PARA CARGAR LAS ACTIVIDADES DISPONIBLES
 */
function cargarActividades(seleccionada, tipo) {
	//var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	$.ajax({
		url : '../ActividadEconomicaController',
		data : {
			"tipoConsulta" : "cargarSelect2"
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {

			if (tipo == "")
				$('.actividad_economica').select2({
					data : datos.listadoActividadesEconomicas,
					placeholder : 'Seleccione una actividad econimica'
				}).select2("val", seleccionada);
			else
				$("#actividad_economica_" + tipo).select2({
					data : datos.listadoActividadesEconomicas,
					placeholder : 'Seleccione una actividad econimica'
				}).select2("val", seleccionada);
		}
	});
}

function cargarRamos(seleccionada, tipo) {
	var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';

	$.ajax({
		url : '../RamoController',
		data : {
			"tipoConsulta" : "encontrarTodos"
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			for (var i = 0; i < datos.listadoRamo.length; i++)
				options += '<option value="' + datos.listadoRamo[i].codigo + '">' + datos.listadoRamo[i].nombre + '</option>';

			if (tipo == "")
				$(".ramo").html(options).val(seleccionada);
			else
				$("#ramo_" + tipo).html(options).val(seleccionada);
		}
	});
}

/*
 * CARGA LOS TIPOS DE EXTRAS
 */
function cargaTipoExtra(numero) {
	// Consultar listado de extras definido
	$.ajax({
		url : '../TipoExtraController',
		data : {
			"tipoConsulta" : "encontrarTodos"
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var listadoExtras = data.listadoTipoExtra;
			$.each(listadoExtras, function (index) {
				$("#tipoExtraOrigin" + numero).append("<option value='" + listadoExtras[index].nombre + "' source='" + listadoExtras[index].codigo + "'>" + listadoExtras[index].nombre + "</option>");
			});
		}
	});
}

/*
 * METODO PARA CARGAR LAS CIUDADES DISPONIBLES
 */
function cargarCiudades(seleccionada, provincia, tipo) {
	var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';

	$.ajax({
		url : '../CiudadController',
		data : {
			"tipoConsulta" : "encontrarPorProvincia",
			"provincia" : provincia
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			for (var i = 0; i < datos.ciudades.length; i++)
				options += '<option value="' + datos.ciudades[i].id + '">' + datos.ciudades[i].nombre + '</option>';

			if (tipo == "")
				$(".ciudad").html(options).val(seleccionada);
			else
				$("#ciudad_" + tipo).html(options).val(seleccionada);
		}
	});

}

/*
 * METODO PARA CARGAR LAS CANTONES DISPONIBLES
 */
function cargarCantones(seleccionada, provincia, tipo) {
	var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';

	$.ajax({
		url : '../CantonController',
		data : {
			"tipoConsulta" : "encontrarPorProvincia",
			"provincia" : provincia
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			for (var i = 0; i < datos.cantones.length; i++)
				options += '<option value="' + datos.cantones[i].id + '">' + datos.cantones[i].nombre + '</option>';

			if (tipo == "")
				$(".canton").html(options).val(seleccionada);
			else
				$("#canton_" + tipo).html(options).val(seleccionada);

		}
	});

}

/*
 * METODO PARA CARGAR LAS PARROQUIAS DISPONIBLES
 */
function cargarParroquias(seleccionada, canton, tipo) {
	var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	$.ajax({
		url : '../ParroquiaController',
		data : {
			"tipoConsulta" : "encontrarPorCanton",
			"canton" : canton
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			for (var i = 0; i < datos.listadoParroquia.length; i++)
				options += '<option value="' + datos.listadoParroquia[i].id + '">' + datos.listadoParroquia[i].nombre + '</option>';

			if (tipo == "")
				$(".parroquia").html(options).val(seleccionada);
			else
				$("#parroquia_" + tipo).html(options).val(seleccionada);
		}
	});
}

/*
 * METODO PARA CARGAR LAS ACTIVIDADES DISPONIBLES
 */
function cargarActividades(seleccionada, tipo) {
	//var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	$.ajax({
		url : '../ActividadEconomicaController',
		data : {
			"tipoConsulta" : "cargarSelect2"
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {

			if (tipo == "")
				$('.actividad_economica').select2({
					data : datos.listadoActividadesEconomicas,
					placeholder : 'Seleccione una actividad econimica'
				}).select2("val", seleccionada);
			else
				$("#actividad_economica_" + tipo).select2({
					data : datos.listadoActividadesEconomicas,
					placeholder : 'Seleccione una actividad econimica'
				}).select2("val", seleccionada);
		}
	});
}

/*
 * CARGA MODELOS EN BASE A LA MARCA
 */
function cargarModelos(id, marca, numero) {

	$.ajax({
		url : '../ModeloController',
		data : {
			"tipoConsulta" : "cargaPorMarcaSelect2",
			"marca" : marca,
			"claseVehiculo" : "LIVIANOS"
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			$("#modelo_" + numero).find('option').remove().end();
			/*.append(
			'<option value="">Seleccione una modelo</option>')
			.val('-1');*/

			$('#modelo_' + numero).select2({
				data : data.listadoModelo,
				placeholder : "Escoja un Modelo",
				containerCssClass : "obligatoriosTarifacion"

			});

			$('#modelo_' + numero).select2("val", id).attr('required', 'required');
		}
	});
}

/*
 * CARGA TIPOS DE USO
 */
function cargarTipoUso(id, numero) {
	// Consultar listado de extras definido
	$.ajax({
		url : '../TipoUsoController',
		data : {
			"tipoConsulta" : "encontrarTodos",
			"tipoObjeto" : tipoObjeto
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var listadoTipoUso = data.listadoTipoUso;
			$("#tipo_uso" + numero).html("<option value=''>Seleccione una opcion</option>");
			$.each(listadoTipoUso, function (index) {
				$("#tipo_uso" + numero).append("<option value='" + listadoTipoUso[index].codigo + "' source='" + listadoTipoUso[index].codigo + "'>" + listadoTipoUso[index].nombre + "</option>");
			});
			$("#tipo_uso" + numero.toString()).val(id).attr('required', 'required');
		}
	});
}

/*
 * CARGA TIPOS DE VEHICULOS
 */
function cargarTipoVehiculo(id, numero, tasaProductoId) {

	// Consultar listado de tipos de Vehiculo
	$.ajax({
		url : '../TipoVehiculoController',
		data : {
			"tipoConsulta" : tipoObjeto,
			"tasaProductoId" : tasaProductoId,
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var listadoTipoVehiculo = data.listadoTipoVehiculo;
			$("#tipo_vehiculo" + numero).html("");
			$("#tipo_vehiculo" + numero).append("<option value=''>Seleccione una opcion</option>");
			$.each(listadoTipoVehiculo, function (index) {
				$("#tipo_vehiculo" + numero).append("<option value='" + listadoTipoVehiculo[index].codigo + "' source='" + listadoTipoVehiculo[index].codigo + "'>" + listadoTipoVehiculo[index].nombre + "</option>");
			});
			$("#tipo_vehiculo" + numero.toString()).val(id).attr('required', 'required');
		}
	});
}

//cargar datos de cada vehiculo valor es el numero de vehiculo, si es carga nueva se envia ""
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
			calcularAntiguedad(numeroVeces);

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
	ceroKilometros(numeroVeces);
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

	cargarSucursales(sucursal, numeroVeces.toString());
	cargarMarcas(marca, numeroVeces.toString());
	cargarColores(color, numeroVeces.toString());
	cargarModelos(modelo, marca, numeroVeces.toString());
	cargarTipoUso(tipoUso, numeroVeces.toString());
	cargarTipoVehiculo(tipoVehiculo, numeroVeces.toString(), tasaProductoId);
}

function cargarTablaPorIdentificacion(identificacion) {
	$.ajax({
		url : '../UPLAController',
		data : {
			"tipoConsulta" : "cargarDatosPorIdentificacion",
			"identificacion" : identificacion
		},
		type : 'POST',
		datatype : 'json',
		success : function (data) {
			if (data.tieneDatosUPLA) {
				if ($("#tipo_identificacion_principal").val() == 4)
					cargarDatosUPLAJuridica(data.datosUPLA);
				else
					cargarDatosUPLANatural(data.datosUPLA);
			}

		}
	});
}

function cargarEstadoDescuento() {
	$(".loading-indicator").fadeIn();
	$.ajax({
		url : '../SolicitudDescuentoController',
		data : {
			"tipoConsulta" : "encontrarPorCotizacion",
			"cotizacionId" : $("#cotizacion_id").text().trim()
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			$(".loading-indicator").fadeOut();
			if (datos.listadoSolicitudDescuento.length > 0 && datos.listadoSolicitudDescuento[0].estado!="Eliminada") {
				if (datos.listadoSolicitudDescuento[0].usuario != null && datos.listadoSolicitudDescuento[0].usuario != "") {
					$("#texto_estado_descuento").html(datos.listadoSolicitudDescuento[0].estado + ' por ' + datos.listadoSolicitudDescuento[0].usuario);
					vehiculosCotizacion();
				}
			} else {
				$("#codigo_descuento").val('').removeAttr('disabled');
				$("#motivo_descuento").val('').removeAttr('disabled');
				$("#porcentaje_solicitud_descuento").val('').removeAttr('disabled');
				$("#descripcion_motivo_descuento").val('').removeAttr('disabled');
				$("#estado_solicitud_descuento").fadeOut('slow').removeAttr('disabled');
				$("#enviar_solicitud_descuento").fadeIn('slow').removeAttr('disabled');
				$("#boton_actualizar_solicitud_descuento").fadeOut('slow').removeAttr('disabled');
				
			}
		}
	});
}

function cargarEstadoInspeccion() {
	$(".loading-indicator").fadeIn();
	$.ajax({
		url : '../SolicitudInspeccionController',
		data : {
			"tipoConsulta" : "encontrarPorCotizacionId",
			"codigoCotizacion" : $("#cotizacion_id").text().trim()
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			$(".loading-indicator").fadeOut();
			$("#textoEstadoInspeccion").html(datos.solicitudInspeccion.estado);
			$("#textoEstadoInspeccionInterna").html(datos.solicitudInspeccion.estado);

		}
	});
}

function cargarBeneficiario(seleccionado) {
	$(".loading-indicator").fadeIn();
	$.ajax({
		url : '../SolicitudInspeccionController',
		data : {
			"tipoConsulta" : "encontrarPorCotizacionId",
			"codigoCotizacion" : $("#cotizacion_id").text().trim()
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			$(".loading-indicator").fadeOut();
			$("#textoEstadoInspeccion").html(datos.solicitudInspeccion.estado);
			$("#textoEstadoInspeccionInterna").html(datos.solicitudInspeccion.estado);

		}
	});
}

function cargarPestanaEndosoBeneficiario(identificacion, monto, beneficiario) {
	cargarTiposIdentificacion("", "asegurado", false);
	if (identificacion == null || identificacion == "") {
		$("#identificacion_asegurado").val($("#identificacion").val()).trigger('change');
	} else {
		$("#identificacion_asegurado").val(identificacion);
		cargarPorIdentificacion("datosAsegurado", identificacion);
	}

	if (monto == null || monto == "") {
		$("#valor_endoso_beneficiario").val(0);
	} else {
		$("#valor_endoso_beneficiario").val(monto);
	}

	$.ajax({
		url : '../BeneficiarioController',
		data : {
			"tipoConsulta" : "cargarSelect2"
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var beneficiarios = data.listadoBeneficiarios;
			$('#beneficiario').select2({
				data : beneficiarios,
				placeholder : 'Seleccione un beneficiario'
			});
			if (beneficiario != null && beneficiario != '') {
				$('#beneficiario').select2('val', beneficiario);
			}
		}
	});
}