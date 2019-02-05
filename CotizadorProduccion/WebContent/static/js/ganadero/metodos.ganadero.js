var tipoObjeto = document.getElementById("tipoObjetoMetodos").getAttribute(
		"tipoObjetoValor");

function verificarPuntosVenta(puntoVentaId) {

	var puntoId = "";
	if (puntoVentaId != null && puntoVentaId != '')
		puntoId = puntoVentaId;
	else
		puntoId = $('#punto_venta').val();
	$.ajax({
		url : '../PuntoVentaController',
		data : {
			"tipoConsulta" : "verificacionPuntoVenta",
			"tipoObjeto" : tipoObjeto,
			"puntoVentaId" : puntoId,

		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			if (data.punto_venta.agente_id == null
					|| data.punto_venta.agente_id == "") {
				$('#agentes').prop("disabled", false);
				$("#agentes option:first").attr('selected', 'selected');
			} else {

				$('#agentes option[value=' + data.punto_venta.agente_id + ']')
						.attr('selected', 'selected');
				// $('#agentes').val(data.punto_venta.agente_id);
				$('#agentes').prop("disabled", true);
			}
		}
	});

}

function obtenerFormaPagoXPV(puntoVentaId) {

	var puntoId = "";
	if (puntoVentaId != null && puntoVentaId != '')
		puntoId = puntoVentaId;
	else
		puntoId = $('#punto_venta').val();
	$.ajax({
		url : '../FormaPagoXPuntoVentaController',
		data : {
			"tipoConsulta" : "encontrarTodos",
			"puntoVentaId" : puntoId,

		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			var listadoGrupos = data.listadoFormaPagoXPV;
			$("#cboFpFormaPago").append(
					"<option value=''>Seleccione una opcion</option>");
			$.each(listadoGrupos, function(index) {
				$("#cboFpFormaPago").append(
						"<option value='" + listadoGrupos[index].id + "' >"
								+ listadoGrupos[index].nombre + "</option>");
			});
		}
	});

}

function obtenerVigenciaPolizaXPV(puntoVentaId) {

	var puntoId = "";
	if (puntoVentaId != null && puntoVentaId != '')
		puntoId = puntoVentaId;
	else
		puntoId = $('#punto_venta').val();
	$.ajax({
		url : '../VigenciaPolizaXPuntoVentaController',
		data : {
			"tipoConsulta" : "encontrarTodos",
			"puntoVentaId" : puntoId,

		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			var listadoGrupos = data.listadoVigenciaPolizaXPV;
			$("#vigencia").append(
					"<option value=''>Seleccione una opcion</option>");
			$.each(listadoGrupos, function(index) {
				$("#vigencia").append(
						"<option value='" + listadoGrupos[index].id + "' >"
								+ listadoGrupos[index].nombre + "</option>");
			});
		}
	});

}
/* METODOS DE CALCULOS */
function calcularIngresosEgresosNatural() {

	var salario = Number($("#salario_mensual_natural").val());
	var otrosIngresos = Number($("#otros_ingresos_natural").val());
	var egresos = Number($("#egresos_natural").val());

	$("#ingresos_egresos_natural").val((salario + otrosIngresos) - egresos);

}

function calcularPatrimonioNatural() {

	var activos = Number($("#activos_natural").val());
	var pasivos = Number($("#pasivos_natural").val());

	$("#patrimonio_natural").val(activos - pasivos);

}

function calcularIngresosEgresosJuridica() {

	var salario = Number($("#salario_mensual_juridica").val());
	var otrosIngresos = Number($("#otros_ingresos_juridica").val());
	var egresos = Number($("#egresos_juridica").val());

	$("#ingresos_egresos_juridica").val((salario + otrosIngresos) - egresos);

}

function calcularPatrimonioJuridica() {

	var activos = Number($("#activos_juridica").val());
	var pasivos = Number($("#pasivos_juridica").val());

	$("#patrimonio_juridica").val(activos - pasivos);

}

function calcularTotalHectareas() {

	var p1 = Number($("#topografia_pendiente1").val());
	var p2 = Number($("#topografia_pendiente2").val());
	var p3 = Number($("#topografia_pendiente3").val());

	$("#topografia_total").val(p1 + p2 + p3);

}

/** METODOS PARA LA DESCARGA DE LOS REPORTES* */
function cambioDescargaCertificados() {

	var valor = $("#selectDescargas").val();
	if (valor == 1) {
		$(".descargaCertificado").fadeOut("slow");
		$("#descargar_FichaCotizacion").fadeIn("slow");
	}
	if (valor == 2) {
		$(".descargaCertificado").fadeOut("slow");
		$("#descargar_certificadoCotizacion").fadeIn("slow");
	}
	if (valor == 3) {
		$(".descargaCertificado").fadeOut("slow");
		$("#descargar_certificadoNormasParticulares").fadeIn("slow");

	}
	/*
	 * if (valor == 4) { $(".descargaCertificado").fadeOut("slow");
	 * $("#descargar_certificadoUPLA").fadeIn("slow");
	 *  } if (valor == 5) { $(".descargaCertificado").fadeOut("slow");
	 * $("#descargar_certificadoPoliza").fadeIn("slow"); }
	 */

}

function abrirFichaCotizacion() {
	var cotizacion = $("#cotizacion_id").html().trim();

	var parametros = {
		"parametros" : {
			"cot_Id" : cotizacion
		},
		"pathReporte" : "/static/reportes/Ganadero/cabecera.jasper"
	};
	abrirReporte('POST', '../ReportesController', parametros, "_blank");
}

function abrirCertificadoCotizacion() {
	var cotizacion = $("#cotizacion_id").html().trim();

	var parametros = {
		"parametros" : {
			"Cot_Id" : cotizacion
		},
		"pathReporte" : "/static/reportes/Ganadero/CotiVacas2KR2012.jasper"
	};
	abrirReporte('POST', '../ReportesController', parametros, "_blank");
}

function abrirCertificadoNormaParticulares() {
	var cotizacion = $("#cotizacion_id").html().trim();

	var parametros = {
		"parametros" : {
			"cot_Id" : cotizacion
		},
		"pathReporte" : "/static/reportes/Ganadero/CondicionesParticulares.jasper"
	};
	abrirReporte('POST', '../ReportesController', parametros, "_blank");
}
/*
 * METODO QUE MUESTRA O ESCONDE LOS CAMPOS DEL FORMULARIO DE LA UPLA DEPENDIENDO
 * DEL TIPO DE IDENTIFICACION QUE SELECCIONE EL USUARIO
 */
function mostrarTipoIdentificacionSolicitante() {
	var tipoIdentificacionId = $("#tipo_identificacion_principal").val();
	if (tipoIdentificacionId == '' || tipoIdentificacionId == '1'
			|| tipoIdentificacionId == '2' || tipoIdentificacionId == '3') {
		$("#nombre_completo").val("");
	} else {
		$("#nombres").val("");
		$("#apellidos").val("");
	}

	if (tipoIdentificacionId == '' || tipoIdentificacionId == '1'
			|| tipoIdentificacionId == '3' || tipoIdentificacionId == '4') {
		$("#identificacion").attr("onkeypress",
				"validarKeyPress(event,/[0-9]/);");

	} else {
		$("#identificacion").attr("onkeypress",
				"validarSoloLetrasNumeros(event);");
	}
}

function mostrarTipoIdentificacionAsegurado() {
	var tipoIdentificacionId = $("#tipo_identificacion_asegurado").val();
	if (tipoIdentificacionId == '' || tipoIdentificacionId == '1'
			|| tipoIdentificacionId == '2' || tipoIdentificacionId == '3') {
		$("#nombre_completo_asegurado").val("");
		$("#ubicacionPersonaNatural").show();
		$("#datosPoliticaNatural").show();
		$("#situacionFinancieraNatural").show();
		$("#ubicacionPersonaJuridica").hide();
		$("#datosPoliticaJuridica").hide();
		$("#situacionFinancieraJuridica").hide();
	} else {
		$("#nombres_asegurado").val("");
		$("#apellidos_asegurado").val("");
		$("#ubicacionPersonaNatural").hide();
		$("#datosPoliticaNatural").hide();
		$("#situacionFinancieraNatural").hide();
		$("#ubicacionPersonaJuridica").show();
		$("#datosPoliticaJuridica").show();
		$("#situacionFinancieraJuridica").show();
	}

	if (tipoIdentificacionId == '' || tipoIdentificacionId == '1'
			|| tipoIdentificacionId == '3' || tipoIdentificacionId == '4') {
		$("#identificacion_asegurado").attr("onkeypress",
				"validarKeyPress(event,/[0-9]/);");

	} else {
		$("#identificacion_asegurado").attr("onkeypress",
				"validarSoloLetrasNumeros(event);");
	}
}

function cargarPestanaEndosoBeneficiario(identificacion, monto, beneficiario) {
	cargarTiposIdentificacionGanadero("", "asegurado", false);
	if (identificacion == null || identificacion == "") {
		$("#identificacion_asegurado").val($("#identificacion").val()).trigger(
				'change');
	} else {
		$("#identificacion_asegurado").val(identificacion);
		cargarPorIdentificacion("datosAsegurado", identificacion);
	}

	if (monto == null || monto == "") {
		$("#valor_endoso_beneficiario").val(0);
	} else {
		$("#valor_endoso_beneficiario").val(monto);
	}
	if (beneficiario != null && beneficiario != '') {
		$('#beneficiario').select2('val', beneficiario);
	}
}

/*
 * METODO QUE RECIBE EL ID DEL PUNTO DE VENTA Y SETEA EL CORRESPONDIENTE AL ID
 * QUE RECIBE.
 */
function obtenerPuntosVentaPorProducto(seleccionada, productoActualID) {
	var productoid = "";
	if (productoActualID != null && productoActualID != '')
		productoid = productoActualID;
	else
		productoid = $('#productos').val();
	$
			.ajax({
				url : '../PuntoVentaController',
				data : {
					"tipoConsulta" : "puntosVentaXProducto",
					"tipoObjeto" : tipoObjeto,
					"grupoPorProductoId" : productoid,

				},
				async : false,
				type : 'post',
				datatype : 'json',
				success : function(data) {
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
									$("#productos")
											.append(
													"<option value=''>Seleccione una opcion</option>");
									options += '<option value="" disabled="disabled" class="seleccionado" ">'
											+ sucursales[j].nombre
											+ '</option>';
								}
								options += '<option value="'
										+ data.puntosVenta[i].id
										+ '" pxpv="'
										+ data.puntosVenta[i].productoPorPuntoDeVenta
										+ '"  >&nbsp;&nbsp;&nbsp;&nbsp;'
										+ data.puntosVenta[i].nombre
										+ '</option>';
							}
						}
					}
					$("#punto_venta").html(options);
					if (seleccionada != '') {
						$("#punto_venta").val(seleccionada);
						verificarPuntosVenta(seleccionada);
					}
				}
			});
}

function obtenerProductosPorGrupoProducto(producto) {
	// Consultar listado de productos dentro de un grupos de productos
	$("#productos").empty();

	$
			.ajax({
				url : '../GrupoPorProductoController',

				data : {
					"tipoConsulta" : "encontrarTodosPorGrupo",
					"tipoObjeto" : tipoObjeto,
					"grupoProductoId" : $("#grupo_productos").val()
				},
				async : false,
				type : 'post',
				datatype : 'json',
				success : function(data) {
					var listadoGrupos = data.listadoGruposPorProducto;
					$("#productos").append(
							"<option value=''>Seleccione una opcion</option>");
					$
							.each(
									listadoGrupos,
									function(index) {
										var inspeccionRequerida = listadoGrupos[index].inspeccionRequerida;
										$("#productos")
												.append(
														"<option value='"
																+ listadoGrupos[index].id
																+ "' inspeccionRequerida='"
																+ inspeccionRequerida
																+ "' >"
																+ listadoGrupos[index].nombre
																+ "</option>");
									});
					if (producto != '') {
						$("#productos").val(producto);
						obtenerPuntosVentaPorProducto($(
								"#puntoVentaSeleccionado").val(), producto);
					}
				}
			});
}

function obtenerCantonPorProvincia(seleccionada) {
	$("#ubicacion_Canton").empty();
	// Consultar la provincia
	$.ajax({
		url : '../CantonController',
		data : {
			"tipoConsulta" : "encontrarPorProvincia",
			"provincia" : $("#ubicacion_provincia").val()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			var listadoCantones = data.cantones;
			$("#ubicacion_Canton").append(
					"<option value=''>Seleccione un Canton</option>");
			$.each(listadoCantones, function(index) {
				var id = undefined;
				if (listadoCantones[index].codigo == undefined) {
					id = listadoCantones[index].id;
				} else {
					id = listadoCantones[index].codigo;
				}

				$("#ubicacion_Canton").append(
						"<option value='" + id + "'>"
								+ listadoCantones[index].nombre + "</option>");
			});
			$("#ubicacion_Canton").val(seleccionada);
		}
	});
}

function obtenerParroquiaPorCanton(seleccionada) {
	$("#ubicacion_Parroquia").empty();
	$.ajax({
		url : '../ParroquiaController',
		data : {
			"tipoConsulta" : "encontrarPorCanton",
			"canton" : $("#ubicacion_Canton").val()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			var listadoParroquias = data.listadoParroquia;
			$("#ubicacion_Parroquia").append(
					"<option value=''>Seleccione una Parroquia</option>");
			$.each(listadoParroquias, function(index) {
				var id = undefined;
				if (listadoParroquias[index].codigo == undefined) {
					id = listadoParroquias[index].id;
				} else {
					id = listadoParroquias[index].codigo;
				}

				$("#ubicacion_Parroquia")
						.append(
								"<option value='" + id + "'>"
										+ listadoParroquias[index].nombre
										+ "</option>");
			});
			$("#ubicacion_Parroquia").val(seleccionada);
		}
	});
}

function obtenerCiudadesPorProvinciaPNatural(seleccionada) {
	$("#ciudad_direccion_cliente_natural").empty();
	$.ajax({
		url : '../CiudadController',
		data : {
			"tipoConsulta" : "encontrarPorProvincia",
			"provincia" : $("#provincia_direccion_cliente_natural").val()
		},
		type : 'post',
		datatype : 'json',
		success : function(datos) {

			var listadoCiudades = datos.ciudades;
			$("#ciudad_direccion_cliente_natural").append(
					"<option value=''>Seleccione una Ciudad</option>");
			$.each(listadoCiudades, function(index) {
				var id = undefined;
				if (listadoCiudades[index].codigo == undefined) {
					id = listadoCiudades[index].id;
				} else {
					id = listadoCiudades[index].codigo;
				}

				$("#ciudad_direccion_cliente_natural").append(
						"<option value='" + id + "'>"
								+ listadoCiudades[index].nombre + "</option>");
			});
			$("#ciudad_direccion_cliente_natural").val(seleccionada);
		}
	});
}

function obtenerCiudadesPorProvinciaPJuridica(seleccionada) {
	$("#ciudad_direccion_matriz_juridica").empty();
	$.ajax({
		url : '../CiudadController',
		data : {
			"tipoConsulta" : "encontrarPorProvincia",
			"provincia" : $("#provincia_direccion_matriz_juridica").val()
		},
		type : 'post',
		datatype : 'json',
		success : function(datos) {

			var listadoCiudades = datos.ciudades;
			$("#ciudad_direccion_matriz_juridica").append(
					"<option value=''>Seleccione una Ciudad</option>");
			$.each(listadoCiudades, function(index) {
				var id = undefined;
				if (listadoCiudades[index].codigo == undefined) {
					id = listadoCiudades[index].id;
				} else {
					id = listadoCiudades[index].codigo;
				}

				$("#ciudad_direccion_matriz_juridica").append(
						"<option value='" + id + "'>"
								+ listadoCiudades[index].nombre + "</option>");
			});
			$("#ciudad_direccion_matriz_juridica").val(seleccionada);
		}
	});
}

function obtenerCantonPorProvinciaPNatural(seleccionada) {
	$("#canton_direccion_cliente_natural").empty();
	// Consultar la provincia
	$.ajax({
		url : '../CantonController',
		data : {
			"tipoConsulta" : "encontrarPorProvincia",
			"provincia" : $("#provincia_direccion_cliente_natural").val()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			var listadoCantones = data.cantones;
			$("#canton_direccion_cliente_natural").append(
					"<option value=''>Seleccione un Canton</option>");
			$.each(listadoCantones, function(index) {
				var id = undefined;
				if (listadoCantones[index].codigo == undefined) {
					id = listadoCantones[index].id;
				} else {
					id = listadoCantones[index].codigo;
				}

				$("#canton_direccion_cliente_natural").append(
						"<option value='" + id + "'>"
								+ listadoCantones[index].nombre + "</option>");
			});
			$("#canton_direccion_cliente_natural").val(seleccionada);
		}
	});
}

function obtenerCantonPorProvinciaPJuridica(seleccionada) {
	$("#canton_direccion_matriz_juridica").empty();
	// Consultar la provincia
	$.ajax({
		url : '../CantonController',
		data : {
			"tipoConsulta" : "encontrarPorProvincia",
			"provincia" : $("#provincia_direccion_matriz_juridica").val()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			var listadoCantones = data.cantones;
			$("#canton_direccion_matriz_juridica").append(
					"<option value=''>Seleccione un Canton</option>");
			$.each(listadoCantones, function(index) {
				var id = undefined;
				if (listadoCantones[index].codigo == undefined) {
					id = listadoCantones[index].id;
				} else {
					id = listadoCantones[index].codigo;
				}

				$("#canton_direccion_matriz_juridica").append(
						"<option value='" + id + "'>"
								+ listadoCantones[index].nombre + "</option>");
			});
			$("#canton_direccion_matriz_juridica").val(seleccionada);
		}
	});
}

function obtenerParroquiaPorCantonPNatural(seleccionada) {
	$("#parroquia_direccion_cliente_natural").empty();
	$.ajax({
		url : '../ParroquiaController',
		data : {
			"tipoConsulta" : "encontrarPorCanton",
			"canton" : $("#canton_direccion_cliente_natural").val()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			var listadoParroquias = data.listadoParroquia;
			$("#parroquia_direccion_cliente_natural").append(
					"<option value=''>Seleccione una Parroquia</option>");
			$.each(listadoParroquias, function(index) {
				var id = undefined;
				if (listadoParroquias[index].codigo == undefined) {
					id = listadoParroquias[index].id;
				} else {
					id = listadoParroquias[index].codigo;
				}

				$("#parroquia_direccion_cliente_natural")
						.append(
								"<option value='" + id + "'>"
										+ listadoParroquias[index].nombre
										+ "</option>");
			});
			$("#parroquia_direccion_cliente_natural").val(seleccionada);
		}
	});
}

function obtenerParroquiaPorCantonPJuridica(seleccionada) {
	$("#parroquia_direccion_matriz_juridica").empty();
	$.ajax({
		url : '../ParroquiaController',
		data : {
			"tipoConsulta" : "encontrarPorCanton",
			"canton" : $("#canton_direccion_matriz_juridica").val()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			var listadoParroquias = data.listadoParroquia;
			$("#parroquia_direccion_matriz_juridica").append(
					"<option value=''>Seleccione una Parroquia</option>");
			$.each(listadoParroquias, function(index) {
				var id = undefined;
				if (listadoParroquias[index].codigo == undefined) {
					id = listadoParroquias[index].id;
				} else {
					id = listadoParroquias[index].codigo;
				}

				$("#parroquia_direccion_matriz_juridica")
						.append(
								"<option value='" + id + "'>"
										+ listadoParroquias[index].nombre
										+ "</option>");
			});
			$("#parroquia_direccion_matriz_juridica").val(seleccionada);
		}
	});
}

function cambiaZonaDireccion(event, tipo) {
	var target = event.target || event.srcElement;
	var seleccionada = $(target).val();
	if (seleccionada == "U") {
		if (tipo == 'N') {
			$("#ciudad_cliente_label").fadeIn();
			$("#ciudad_cliente_select").fadeIn();
			$("#canton_cliente_label").fadeOut();
			$("#canton_cliente_select").fadeOut();
			$("#parroquia_cliente_label").fadeOut();
			$("#parroquia_cliente_select").fadeOut();
		}
		if (tipo == 'J') {
			$("#ciudad_matriz_label").fadeIn();
			$("#ciudad_matriz_select").fadeIn();
			$("#canton_matriz_label").fadeOut();
			$("#canton_matriz_select").fadeOut();
			$("#parroquia_matriz_label").fadeOut();
			$("#parroquia_matriz_select").fadeOut();
		}
	}
	if (seleccionada == "R") {
		if (tipo == 'N') {
			$("#canton_cliente_label").fadeIn();
			$("#canton_cliente_select").fadeIn();
			$("#parroquia_cliente_label").fadeIn();
			$("#parroquia_cliente_select").fadeIn();
			$("#ciudad_cliente_label").fadeOut();
			$("#ciudad_cliente_select").fadeOut();
		}
		if (tipo == 'J') {
			$("#canton_matriz_label").fadeIn();
			$("#canton_matriz_select").fadeIn();
			$("#parroquia_matriz_label").fadeIn();
			$("#parroquia_matriz_select").fadeIn();
			$("#ciudad_matriz_label").fadeOut();
			$("#ciudad_matriz_select").fadeOut();
		}
	}
}

function agregarNuevoAnimal() {
	$("#msgPopup").hide();
	$(".required").css("border", "1px solid black");
	var bandera = "0";
	$
			.each(
					$(".required"),
					function(index) {
						if ($(this).val() == "") {
							$(this).css("border", "1px solid red");
							$(this).focus();
							$("#AnimalForm")
									.parent()
									.append(
											"<tr id='errorMsg'><td colspan='3' style='color:red;'>Por favor ingrese el campo requerido<td></tr>");
							bandera = "1";
							return false;
						}
					});

	var region = "";
	if ($("#region_costa").is(":checked"))
		region = "COSTA";
	else if ($("#region_sierra").is(":checked"))
		region = "SIERRA";
	else
		region = "ORIENTE";

	var produccion = "";
	if ($("#produccion_carne").is(":checked"))
		produccion = "CARNE";
	else if ($("#produccion_leche").is(":checked"))
		produccion = "LECHE";
	else
		produccion = "DOBLE PROPOSITO";

	if ($("#origenAnimalOrigin").val() == "NACIONAL") {
		$
				.ajax({
					url : '../PrecioReferencialGanaderoController',
					data : {
						"tipoConsulta" : "encontrarPorParametros",
						"tipoGanadoId" : $("#tipoAnimalOrigin").val(),
						"tipoProduccion" : produccion,
						"region" : region
					},
					async : false,
					type : 'post',
					datatype : 'json',
					success : function(data) {
						var precioMinimo = Number(data.precioMinimo);
						var precioMaximo = Number(data.precioMaximo);
						var valor = Number($("#valorAseguradoOrigin").val());
						if (valor < precioMinimo || valor > precioMaximo)
							alert("El precio indicado esta fuera de rango.");
						else {
							var valorTotalExtras = $("#valorAseguradoOrigin")
									.val();
							$
									.each(
											$(".valorExtra"),
											function() {
												if ($(this).val() != "") {
													valorTotalExtras = parseFloat(valorTotalExtras)
															+ parseFloat($(this)
																	.parent()
																	.next()
																	.next()
																	.children()
																	.val());
												}
											});

							var numeroAnimales = parseInt($("#contadorAnimales")
									.val())
									+ parseInt(1);

							if (bandera == 0) {
								$("#animales")
										.append(
												'<tr>'
														+ '<td style="width: 18%" class="sorting"><input type="text" class="datosGanadero" id="tipoAnimal_'
														+ numeroAnimales
														+ '" readonly value="'
														+ $(
																"#tipoAnimalOrigin option:selected")
																.text()
														+ '" source="'
														+ $("#tipoAnimalOrigin")
																.val()
														+ '"></td>'
														+ '<td style="width: 10%" class="sorting"><input type="text" class="datosGanadero" id="numeroArete_'
														+ numeroAnimales
														+ '" value='
														+ $(
																"#numeroAreteOrigin")
																.val()
														+ '></td>'
														+ '<td style="width: 7%" class="sorting"><input type="text" class="datosGanadero" id="numeroChip_'
														+ numeroAnimales
														+ '" value='
														+ $("#numeroChipOrigin")
																.val()
														+ '></td>'
														+ '<td style="width: 10%" class="sorting"><input type="text" class="datosGanadero" id="razaAnimal_'
														+ numeroAnimales
														+ '" readonly value="'
														+ $(
																"#razaAnimalOrigin option:selected")
																.text()
														+ '" source="'
														+ $("#razaAnimalOrigin")
																.val()
														+ '"></td>'
														+ '<td style="width: 10%" class="sorting"><input type="text" class="datosGanadero" id="origenAnimal_'
														+ numeroAnimales
														+ '" value='
														+ $(
																"#origenAnimalOrigin")
																.val()
														+ '></td>'
														+ '<td style="width: 10%" class="sorting"><input type="text" class="datosGanadero" id="procedenciaAnimal_'
														+ numeroAnimales
														+ '" value='
														+ $(
																"#procedenciaAnimalOrigin")
																.val()
														+ '></td>'
														+ '<td style="width: 10%" class="sorting"><input type="text" class="datosGanadero" id="colorAnimal_'
														+ numeroAnimales
														+ '" value='
														+ $(
																"#colorAnimalOrigin")
																.val()
														+ '></td>'
														+ '<td style="width: 7%" class="sorting"><input type="text" class="datosGanadero" id="edadAnimal_'
														+ numeroAnimales
														+ '" value='
														+ $("#edadAnimalOrigin")
																.val()
														+ '></td>'
														+ '<td style="width: 10%" class="sorting"><input type="text" class="datosGanadero" id="valorAsegurado_'
														+ numeroAnimales
														+ '" value='
														+ $(
																"#valorAseguradoOrigin")
																.val()
														+ '></td>'
														+ '<td style="width: 20%" class="sorting">'
														+ '<button type="button" class="btn btn-danger btn-xs eliminar-extra-btn">'
														+ ' <span class="glyphicon glyphicon glyphicon-remove"></span> Eliminar'
														+ ' </button></td>'
														+ '</tr>');
							}
							$("#contadorAnimales").val(numeroAnimales);
							$("#tipoAnimalOrigin").val('-1');
							$("#origenAnimalOrigin").val('-1');
							$("#numeroAreteOrigin").val('');
							$("#numeroChipOrigin").val('');
							$("#razaAnimalOrigin").val('-1');
							$("#edadAnimalOrigin").val('');
							$("#procedenciaAnimalOrigin").val('');
							$("#colorAnimalOrigin").val('');
							$("#valorAseguradoOrigin").val('');

							$(".eliminar-extra-btn").bind(
									{
										click : function() {
											$(this).parent().parent().remove();
											numeroAnimales = parseInt($(
													"#contadorAnimales").val())
													- parseInt(1);
											$("#contadorAnimales").val(
													numeroAnimales);
										}
									});
						}
					}
				});
	} else {
		var valorTotalExtras = $("#valorAseguradoOrigin").val();
		$.each($(".valorExtra"), function() {
			if ($(this).val() != "") {
				valorTotalExtras = parseFloat(valorTotalExtras)
						+ parseFloat($(this).parent().next().next().children()
								.val());
			}
		});

		var numeroAnimales = parseInt($("#contadorAnimales").val())
				+ parseInt(1);

		if (bandera == 0) {
			$("#animales")
					.append(
							'<tr>'
									+ '<td style="width: 18%" class="sorting"><input type="text" class="datosGanadero" id="tipoAnimal_'
									+ numeroAnimales
									+ '" readonly value="'
									+ $("#tipoAnimalOrigin option:selected")
											.text()
									+ '" source="'
									+ $("#tipoAnimalOrigin").val()
									+ '"></td>'
									+ '<td style="width: 10%" class="sorting"><input type="text" class="datosGanadero" id="numeroArete_'
									+ numeroAnimales
									+ '" value='
									+ $("#numeroAreteOrigin").val()
									+ '></td>'
									+ '<td style="width: 7%" class="sorting"><input type="text" class="datosGanadero" id="numeroChip_'
									+ numeroAnimales
									+ '" value='
									+ $("#numeroChipOrigin").val()
									+ '></td>'
									+ '<td style="width: 10%" class="sorting"><input type="text" class="datosGanadero" id="razaAnimal_'
									+ numeroAnimales
									+ '" readonly value="'
									+ $("#razaAnimalOrigin option:selected")
											.text()
									+ '" source="'
									+ $("#razaAnimalOrigin").val()
									+ '"></td>'
									+ '<td style="width: 10%" class="sorting"><input type="text" class="datosGanadero" id="origenAnimal_'
									+ numeroAnimales
									+ '" value='
									+ $("#origenAnimalOrigin").val()
									+ '></td>'
									+ '<td style="width: 10%" class="sorting"><input type="text" class="datosGanadero" id="procedenciaAnimal_'
									+ numeroAnimales
									+ '" value='
									+ $("#procedenciaAnimalOrigin").val()
									+ '></td>'
									+ '<td style="width: 10%" class="sorting"><input type="text" class="datosGanadero" id="colorAnimal_'
									+ numeroAnimales
									+ '" value='
									+ $("#colorAnimalOrigin").val()
									+ '></td>'
									+ '<td style="width: 7%" class="sorting"><input type="text" class="datosGanadero" id="edadAnimal_'
									+ numeroAnimales
									+ '" value='
									+ $("#edadAnimalOrigin").val()
									+ '></td>'
									+ '<td style="width: 10%" class="sorting"><input type="text" class="datosGanadero" id="valorAsegurado_'
									+ numeroAnimales
									+ '" value='
									+ $("#valorAseguradoOrigin").val()
									+ '></td>'
									+ '<td style="width: 20%" class="sorting">'
									+ '<button type="button" class="btn btn-danger btn-xs eliminar-extra-btn">'
									+ ' <span class="glyphicon glyphicon glyphicon-remove"></span> Eliminar'
									+ ' </button></td>' + '</tr>');
		}
		$("#contadorAnimales").val(numeroAnimales);
		$("#tipoAnimalOrigin").val('-1');
		$("#origenAnimalOrigin").val('-1');
		$("#numeroAreteOrigin").val('');
		$("#numeroChipOrigin").val('');
		$("#razaAnimalOrigin").val('-1');
		$("#edadAnimalOrigin").val('');
		$("#procedenciaAnimalOrigin").val('');
		$("#valorAseguradoOrigin").val('');

		$(".eliminar-extra-btn").bind(
				{
					click : function() {
						$(this).parent().parent().remove();
						numeroAnimales = parseInt($("#contadorAnimales").val())
								- parseInt(1);
						$("#contadorAnimales").val(numeroAnimales);
					}
				});
	}
}

function cambiarEstadoGanadero(estado) {
	$.ajax({
		url : '../CotizacionController',
		data : {
			"tipoConsulta" : "cambiarEstado",
			"cotizacionId" : $("#cotizacion_id").text(),
			"estadoNombre" : estado,
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			$("#cotizacion_id").text(data.cotizacionId);
			// Validacion poner en la URL el id de la cotizacion
			var valorId = getParameterByName("id");
			if (valorId != null) {
				$.pushVar("id", data.cotizacionId, "", window.location.href);
				if (estado == "Denegado")
					$("#msgPopupEmitidoRechazado").show();
				else {
					$("#msgPopupFinalizadoCorrectamente").show();
					$(".datosGanadero").each(function() {
						$(this).attr("disabled", "disabled");
					});

					$(":button").each(function() {
						$(this).attr("disabled", "disabled");
					});

					$(":checkbox").each(function() {
						$(this).attr("disabled", "disabled");
					});
					$("#filaEmitirCotizacion").hide();
					$("#datosFinales").hide();
					$("#filaEnviarCotizacion").hide();
				}
			}
		}
	});
}

function emitirPolizaGanadero() {
	$("#msgPopupEmitidoCorrectamente").hide();
	if ($("#fecha_inicio_vigencia").val() == "") {
		alert("Debe ingresar la fecha en la que desea que se emita la poliza.");
	} else {
		$.ajax({
			url : '../ObjetoGanaderoController',
			data : {
				"tipoConsulta" : "emitirPolizaGanadero",
				"cotizacionId" : $("#cotizacion_id").text(),
				"fechaInicioVigencia" : $("#fecha_inicio_vigencia").val(),
			},
			async : false,
			type : 'post',
			datatype : 'json',
			success : function(data) {
				var proceso=data.success;
				
				$("#cotizacion_id").text(data.cotizacionId);
				// Validacion poner en la URL el id de la cotizacion
				var valorId = getParameterByName("id");
				if (valorId != null) {
					$
							.pushVar("id", data.cotizacionId, "",
									window.location.href);
					
					if(proceso==="TRUE" ||proceso===true){
						$("#msgPopupEmitidoCorrectamente").show();
						$("#filaEmitirCotizacion").hide();
					}else{
						$("#msgPopupEmitidoFalso").show();
					}
					
					
				}
			}
		});
	}
}

function cargarDireccionFactura(formulario, datos) {
	if (datos != null) {
		if (formulario == "solicitante") {
			if ($("#tipo_identificacion_principal").val() == "4") {
				$("#nombre_direccion_solicitante").val(
						$("#nombre_completo").val());
			} else {
				$("#nombre_direccion_solicitante").val(
						$("#nombres").val() + " " + $("#apellidos").val());
			}
			$("#cedula_direccion_solicitante").val($("#identificacion").val());
			$("#telefono_direccion_solicitante").val(datos.telefono);
			$("#celular_direccion_solicitante").val(datos.celular);
			$("#mail_direccion_solicitante").val(datos.email);
			options = '';
		}
		if (formulario == "asegurado") {
			$("#tipo_identificacion_asegurado").val(datos.tipoIdentificacion);
			$("#nombres_asegurado").val(datos.nombre);
			$("#apellidos_asegurado").val(datos.apellido);
			$("#identificacion_asegurado").val(datos.identificacion);
			if (datos.tipoIdentificacion == "4") {
				$("#nombre_direccion_asegurado").val(datos.nombreCompleto);
				$("#filaJuridicaAsegurado").show();
				$("#filaNaturalAsegurado").hide();

			} else {
				$("#nombre_direccion_asegurado").val(
						datos.nombre + " " + datos.apellido);
				$("#filaNaturalAsegurado").show();
				$("#filaJuridicaAsegurado").hide();
			}

			if (datos.zona == "1") {
				$("#zona_direccion_asegurado").val("U");
				if (datos.tipoIdentificacion == "4") {
					$("#provincia_direccion_matriz_juridica").val(
							datos.provincia);
					obtenerCiudadesPorProvinciaPJuridica(datos.ciudad);
				} else {
					$("#provincia_direccion_cliente_natural").val(
							datos.provincia);
					obtenerCiudadesPorProvinciaPNatural(datos.ciudad);
				}
				$("#fila_ciudad_direccion_asegurado").fadeIn("slow");
				$("#fila_canton_direccion_asegurado").fadeOut("slow");
				$("#fila_parroquia_direccion_asegurado").fadeOut("slow");
				$("#principal_direccion_asegurado").val(datos.callePrincipal);
				$("#secundaria_direccion_asegurado").val(datos.calleSecundaria);
				$("#numero_direccion_asegurado").val(datos.numero);
				$("#referencia_direccion_asegurado").val(datos.datosReferencia);
			}
			if (datos.zona == "2") {
				$("#zona_direccion_asegurado").val("R");
				if (datos.tipoIdentificacion == "4") {
					// cargarProvinciasPJuridica(datos.provincia,
					// "direccion_asegurado");
					$("#provincia_direccion_matriz_juridica").val(
							datos.provincia);
					obtenerCantonPorProvinciaPJuridica(datos.canton);
					obtenerParroquiaPorCantonPJuridica(datos.parroquia);
				} else {
					$("#provincia_direccion_cliente_natural").val(
							datos.provincia);
					obtenerCantonPorProvinciaPNatural(datos.canton);
					obtenerParroquiaPorCantonPNatural(datos.parroquia);
				}
				// cargarProvincias(datos.provincia, "direccion_asegurado");
				// cargarCantones(datos.canton, datos.provincia,
				// "direccion_asegurado");
				$("#fila_ciudad_direccion_asegurado").fadeOut("slow");
				$("#fila_canton_direccion_asegurado").fadeIn("slow");
				cargarParroquias(datos.parroquia, datos.canton,
						"direccion_asegurado");
				$("#fila_parroquia_direccion_asegurado").fadeIn("slow");
				$("#principal_direccion_asegurado").val(datos.callePrincipal);
				$("#secundaria_direccion_asegurado").val(datos.calleSecundaria);
				$("#numero_direccion_asegurado").val(datos.numero);
				$("#referencia_direccion_asegurado").val(datos.datosReferencia);
			}
		}
	}
	if ((datos == null || datos == "")
			&& (formulario == "" || formulario == null)) {
		var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
		options += '<option value="U">&nbsp;&nbsp;Urbana </option>';
		options += '<option value="R">&nbsp;&nbsp;Rural </option>';
		$("#zona_direccion_asegurado").html(options);
		$("#zona_direccion_solicitante").html(options);

		cargarProvincias("", "direccion_asegurado");
		cargarProvincias("", "direccion_solicitante");
	}
	if ((datos == null || datos == "") && (formulario == "solicitante")) {
		var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
		options += '<option value="U">&nbsp;&nbsp;Urbana </option>';
		options += '<option value="R">&nbsp;&nbsp;Rural </option>';
		$("#zona_direccion_solicitante").html(options);

		cargarProvincias("", "direccion_solicitante");
	}
	if ((datos == null || datos == "") && (formulario == "asegurado")) {
		var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
		options += '<option value="U">&nbsp;&nbsp;Urbana </option>';
		options += '<option value="R">&nbsp;&nbsp;Rural </option>';
		$("#zona_direccion_asegurado").html(options);

		cargarProvincias("", "direccion_asegurado");
	}
}

function cargarFormasPagoGanadero(seleccionada) {
	$("#rowDetallePagos").hide();
	/*
	 * $.ajax({ url : '../FormaPagoController', data : { "tipoConsulta" :
	 * "encontrarTodos", }, type : 'post', datatype : 'json', success : function
	 * (data) { var listadoFormaPago = data.listadoFormaPago; var options = '';
	 * $.each(listadoFormaPago, function(index){ options += '<option value="' +
	 * listadoFormaPago[index].codigo + '">' + listadoFormaPago[index].nombre + '</option>';
	 * }); $("#cboFpFormaPago").empty().append(options);
	 * 
	 * if (seleccionada > 0){ cambioFormaPago(seleccionada, false);
	 * $("#cboFpFormaPago").val(seleccionada); }
	 * 
	 * for(i=2014; i<=2019; i++) $("#tarjetaAnioExp").append('<option value="' +
	 * i + '">' + i + '</option>'); } });
	 */

	$
			.ajax({
				url : '../InstitucionFinancieraController',
				data : {
					"tipoConsulta" : "encontrarTodos",
				},
				type : 'post',
				datatype : 'json',
				success : function(datos) {
					var listadoInstitucionFinanciera = datos.listadoInstitucionFinanciera;

					$("#banco_forma_pago").empty();
					$("#cboFpBanco").empty();
					$
							.each(
									listadoInstitucionFinanciera,
									function(index) {
										if (listadoInstitucionFinanciera[index].tarjeta == '0'
												&& listadoInstitucionFinanciera[index].debito == '1') {
											$("#bancoId")
													.append(
															"<option value='"
																	+ listadoInstitucionFinanciera[index].codigo
																	+ "' nemotecnico='"
																	+ listadoInstitucionFinanciera[index].nemotecnico
																	+ "'>"
																	+ listadoInstitucionFinanciera[index].nombre
																	+ "</option>");
										}

										if (listadoInstitucionFinanciera[index].tarjeta == '1'
												&& listadoInstitucionFinanciera[index].debito == '1') {
											$("#tarjetaId")
													.append(
															"<option value='"
																	+ listadoInstitucionFinanciera[index].codigo
																	+ "' nemotecnico='"
																	+ listadoInstitucionFinanciera[index].nemotecnico
																	+ "'>"
																	+ listadoInstitucionFinanciera[index].nombre
																	+ "</option>");
										}
									});

					var fecha = new Date();
					var anio = fecha.getFullYear();
					var anioLimite = parseInt(anio) + 10;

					for (i = parseInt(anio); i <= anioLimite; i++) {
						$("#cboFpAnio").append(
								"<option value='" + i + "'>" + i + "</option>");
					}
				}
			});
}

function cargarPorGanaderoId(id) {
	var etapa;

	$
			.ajax({
				url : '../CotizacionController',
				data : {
					"tipoConsulta" : "encontrarPorId",
					"id" : id
				},
				async : false,
				type : 'post',
				datatype : 'json',
				success : function(data) {
					if (data.success) {
						var datosCotizacion = data.datosCotizacion;
						$("#estadoCotizacion").val(
								datosCotizacion.estadoCotizacion);
						if (datosCotizacion != null)
							$("#grupo_productos").val(
									datosCotizacion.etapa1.productos);

						if (datosCotizacion.etapa1 != null) {

							$("#grupo_productos").removeAttr('required');
							$("#productos").removeAttr('required');
							$("#tipo_identificacion_principal").removeAttr(
									'required');
							$("#identificacion").removeAttr('required');
							$("#nombres").removeAttr('required');
							$("#email").removeAttr('required');
							$("#apellidos").removeAttr('required');
							$("#tipo_identificacion").removeAttr('required');
							$("#agentes").removeAttr('required');
							$("#vigencia").removeAttr('required');
							$("#punto_venta").removeAttr('required');

							var etapa1 = datosCotizacion.etapa1;

							// Lleno los combos que son base de otros combos
							$("#grupo_productos").val(etapa1.grupoProductos);
							$("#puntoVentaSeleccionado").val(etapa1.puntoVenta);

							// Lleno los combos dependientes
							obtenerProductosPorGrupoProducto(etapa1.productos);
							cargarTiposIdentificacionGanadero(
									etapa1.tipoIdentificacion, 'principal',
									true);
							obtenerVigenciaPolizaXPV(etapa1.puntoVenta);
							obtenerFormaPagoXPV(etapa1.puntoVenta);

							$("#vigencia").val(etapa1.vigenciaPoliza);
							$("#identificacion").val(etapa1.identificacion);
							$("#nombres").val(etapa1.nombres);
							$("#nombre_completo").val(etapa1.nombreCompleto);
							$("#apellidos").val(etapa1.apellidos);
							$("#email").val(etapa1.mail);
							$("#celular").val(etapa1.celular);
							$("#telefono").val(etapa1.telefono);

							etapa = 1;
						}

						if (datosCotizacion.etapa2 != null) {
							$("a[href='#next']").click();
							var etapa2 = datosCotizacion.etapa2;
							var objetos = etapa2.objetos;

							if (objetos != null) {
								var objetoActual = objetos[0];

								$("#topografia_pendiente1").removeAttr(
										'required');

								$("#ubicacion_provincia").val(
										objetoActual.provinciaId);

								obtenerCantonPorProvincia(objetoActual.cantonId);
								obtenerParroquiaPorCanton(objetoActual.parroquiaId);

								if (objetoActual.region == "COSTA")
									$("#region_costa").prop("checked", true);
								else if (objetoActual.region == "SIERRA")
									$("#region_sierra").prop("checked", true);
								else
									$("#region_oriente").prop("checked", true);

								if (objetoActual.tipoProduccion == "CARNE")
									$("#produccion_carne")
											.prop("checked", true);
								else if (objetoActual.tipoProduccion == "LECHE")
									$("#produccion_leche")
											.prop("checked", true);
								else
									$("#produccion_doble")
											.prop("checked", true);

								if (objetoActual.espricipalIngreso = "S")
									$("#esprincipal_ingreso_si").prop(
											"checked", true);
								else
									$("#esprincipal_ingreso_no").prop(
											"checked", true);

								if (objetoActual.asistenciaVeterinaria = "S")
									$("#asistencia_veterinaria_si").prop(
											"checked", true);
								else
									$("#asistencia_veterinaria_no").prop(
											"checked", true);

								// "region":
								// "tipoProduccion": produccion,
								// "fincaAltitud": produccion,
								$("#ubicacion").val(objetoActual.ubicacion);
								$("#recinto").val(objetoActual.recinto);
								$("#topografia_pendiente1").val(
										objetoActual.fincaTopografia1);
								$("#topografia_pendiente2").val(
										objetoActual.fincaTopografia2);
								$("#topografia_pendiente3").val(
										objetoActual.fincaTopografia3);
								var totalTopografia = 0;
								if (objetoActual.fincaTopografia1 != "")
									totalTopografia = totalTopografia
											+ parseFloat(objetoActual.fincaTopografia1);
								if (objetoActual.fincaTopografia2 != "")
									totalTopografia = totalTopografia
											+ parseFloat(objetoActual.fincaTopografia2);
								if (objetoActual.fincaTopografia3 != "")
									totalTopografia = totalTopografia
											+ parseFloat(objetoActual.fincaTopografia3);
								$("#topografia_total").val(totalTopografia);
								$("#tipo_pasto").val(objetoActual.pastoTipo);
								$("#hectareas_pasto").val(
										objetoActual.pastoHectareas);
								$("#volumen_pasto").val(
										objetoActual.pastoVolumen);
								$("#observaciones_pasto").val(
										objetoActual.pastoObservaciones);
								$("#animales_vacunos").val(
										objetoActual.animalesVacunos);
								$("#mortalidad_vacas").val(
										objetoActual.mortalidadVacas);
								$("#mortalidad_vacas_causa").val(
										objetoActual.mortalidadVacasCausa);
								$("#mortalidad_vaconasv").val(
										objetoActual.mortalidadVaconasV);
								$("#mortalidad_vaconasv_causa").val(
										objetoActual.mortalidadVaconasVCausa);
								$("#mortalidad_vaconasf").val(
										objetoActual.mortalidadVaconasF);
								$("#mortalidad_vaconasf_causa").val(
										objetoActual.mortalidadVaconasFCausa);
								$("#mortalidad_vaconasm").val(
										objetoActual.mortalidadVaconasM);
								$("#mortalidad_vaconasm_causa").val(
										objetoActual.mortalidadVaconasMCausa);
								$("#mortalidad_toros").val(
										objetoActual.mortalidadToros);
								$("#mortalidad_toros_causa").val(
										objetoActual.mortalidadTorosCausa);
								$("#mortalidad_toretes").val(
										objetoActual.mortalidadToretes);
								$("#mortalidad_toretes_causa").val(
										objetoActual.mortalidadToretesCausa);
								$("#mortalidad_terneros").val(
										objetoActual.mortalidadTeneros);
								$("#mortalidad_terneros_causa").val(
										objetoActual.mortalidadTenerosCausa);
								$("#mortalidad_terneras").val(
										objetoActual.mortalidadTerneras);
								$("#mortalidad_terneras_causa").val(
										objetoActual.mortalidadTernerasCausa);

								$("#alimentacion_pastoreo")
										.prop(
												"checked",
												objetoActual.alimentacionPastoreo == "1" ? true
														: false);
								$("#alimentacion_corte")
										.prop(
												"checked",
												objetoActual.alimentacionCorte == "1" ? true
														: false);
								$("#alimentacion_sogueo")
										.prop(
												"checked",
												objetoActual.alimentacionSogueo == "1" ? true
														: false);
								$("#alimentacion_otros")
										.prop(
												"checked",
												objetoActual.alimentacionOtros == "1" ? true
														: false);

								$("#acceso_al_agua").val(
										objetoActual.accesoAlAgua);

								$("#vacunaciones_aftosa")
										.prop(
												"checked",
												objetoActual.vacunacionesAftosa == "1" ? true
														: false);
								$("#vacunaciones_brucelosis")
										.prop(
												"checked",
												objetoActual.vacunacionesBrucelosis == "1" ? true
														: false);
								$("#vacunaciones_triple")
										.prop(
												"checked",
												objetoActual.vacunacionesTriple == "1" ? true
														: false);
								$("#vacunaciones_leptospirosis")
										.prop(
												"checked",
												objetoActual.vacunacionesLeptospirosis == "1" ? true
														: false);
								$("#vacunaciones_ibrbvd")
										.prop(
												"checked",
												objetoActual.vacunacionesIbrBvd == "1" ? true
														: false);

								$("#vacunaciones_otras").val(
										objetoActual.vacunacionesOtras);
								$("#enfermedad_mastitis").val(
										objetoActual.enfermedadMastitis);
								$("#enfermedad_panadizo").val(
										objetoActual.enfermedadPanadizo);
								$("#enfermedad_fiebreleche").val(
										objetoActual.enfermedadFiebreleche);
								$("#enfermedad_lesionubres").val(
										objetoActual.enfermedadLesionubres);
								$("#enfermedad_neumonias").val(
										objetoActual.enfermedadNeumonias);
								$("#enfermedad_otras").val(
										objetoActual.enfermedadOtras);
								$("#enfermedad_cual").val(
										objetoActual.enfermedadCual);

								$("#parasitos_internos")
										.prop(
												"checked",
												objetoActual.parasitosInternos == "1" ? true
														: false);
								$("#parasitos_internos_trata").val(
										objetoActual.parasitosInternosTrata);
								$("#parasitos_internos_frecu").val(
										objetoActual.parasitosInternosFrecu);
								$("#parasitos_externos")
										.prop(
												"checked",
												objetoActual.parasitosExternos == "1" ? true
														: false);
								$("#parasitos_externos_trata").val(
										objetoActual.parasitosExternosTrata);
								$("#parasitos_externos_frecu").val(
										objetoActual.parasitosExternosFrecu);
								// "asistenciaVeterinaria": asistencia,
								$("#asistencia_veterinaria_frecu")
										.val(
												objetoActual.asistenciaVeterinariaFrecu);
								$("#asistencia_veterinaria_prof").val(
										objetoActual.asistenciaVeterinariaProf);
								$("#asistencia_veterinaria_tele").val(
										objetoActual.asistenciaVeterinariaTele);
								$("#experiencia_ganadero_anios").val(
										objetoActual.experienciaGanaderoAnios);

								var animales = objetoActual.animales;

								// Cargo el listado de animales
								$
										.each(
												animales,
												function(index) {
													$("#animales")
															.append(
																	'<tr>'
																			+ '<td style="width: 18%"><input type="text" class="datosGanadero" id="tipoAnimal_'
																			+ animales[index].id
																			+ '" readonly value="'
																			+ animales[index].tipoNombre
																			+ '" source="'
																			+ animales[index].tipoId
																			+ '"></td>'
																			+ '<td style="width: 10%"><input type="text" class="datosGanadero" id="numeroArete_'
																			+ animales[index].id
																			+ '" value='
																			+ animales[index].numeroArete
																			+ '></td>'
																			+ '<td style="width: 7%"><input type="text" class="datosGanadero" id="numeroChip_'
																			+ animales[index].id
																			+ '" value='
																			+ animales[index].numeroChip
																			+ '></td>'
																			+ '<td style="width: 18%"><input type="text" class="datosGanadero" id="razaAnimal_'
																			+ animales[index].id
																			+ '" readonly value="'
																			+ animales[index].razaNombre
																			+ '" source="'
																			+ animales[index].razaId
																			+ '"></td>'
																			+ '<td style="width: 10%"><input type="text" class="datosGanadero" id="origen_'
																			+ animales[index].id
																			+ '" value='
																			+ animales[index].origen
																			+ '></td>'
																			+ '<td style="width: 10%"><input type="text" class="datosGanadero" id="procedencia_'
																			+ animales[index].id
																			+ '" value='
																			+ animales[index].procedencia
																			+ '></td>'
																			+ '<td style="width: 10%"><input type="text" class="datosGanadero" id="color_'
																			+ animales[index].id
																			+ '" value='
																			+ animales[index].color
																			+ '></td>'
																			+ '<td style="width: 10%"><input type="text" class="datosGanadero" id="edadAnimal_'
																			+ animales[index].id
																			+ '" value='
																			+ animales[index].edad
																			+ '></td>'
																			+ '<td style="width: 10%"><input type="text" class="datosGanadero" id="valorAsegurado_'
																			+ animales[index].id
																			+ '" value='
																			+ animales[index].valorAsegurar
																			+ '></td>'
																			+ '<td style="width: 20%">'
																			+ '<button type="button" class="btn btn-danger btn-xs eliminar-extra-btn">'
																			+ ' <span class="glyphicon glyphicon glyphicon-remove"></span> Eliminar'
																			+ ' </button></td>'
																			+ '</tr>');
												});
								etapa = 2;
							}
						}

						if (datosCotizacion.etapa3 != null) {
							$("a[href='#next']").click();
							var etapa3 = datosCotizacion.etapa3;

							if (etapa3.endosoBeneficiario != null) {
								if (etapa3.endosoBeneficiario.beneficiarioId != null
										|| etapa3.endosoBeneficiario.beneficiarioId != "")
									cargarPestanaEndosoBeneficiario(
											etapa3.endosoBeneficiario.identificacion,
											etapa3.endosoBeneficiario.monto,
											etapa3.endosoBeneficiario.beneficiarioId);
								$("#asegurado_id").val(
										etapa3.endosoBeneficiario.entidadId);
								$("#identificacion_asegurado")
										.val(
												etapa3.endosoBeneficiario.identificacion);
								$("#nombres_asegurado").val(
										etapa3.endosoBeneficiario.nombres);
								$("#apellidos_asegurado").val(
										etapa3.endosoBeneficiario.apellidos);
								$("#nombre_completo_asegurado")
										.val(
												etapa3.endosoBeneficiario.nombreCompleto);

								cargarTiposIdentificacionGanadero(
										etapa3.endosoBeneficiario.tipoIdentificacion,
										'asegurado', true);
								$("#endoso_beneficiario_id")
										.val(
												etapa3.endosoBeneficiario.endosoBeneficiarioId);
							} else {
								cargarPestanaEndosoBeneficiario(
										datosCotizacion.etapa1.identificacion,
										0, "");
							}

							if (etapa3.formaPago != null
									&& etapa3.formaPago.pagoId != null) {

								$("#descargar_certificado").fadeIn("slow")
										.removeAttr("disabled");
								$("#enviar_certificado").fadeIn("slow")
										.removeAttr("disabled");
								$("#codigoPagoRegistrado").val(
										etapa3.formaPago.pagoId);

								if (etapa3.formaPago.formaPagoNombre.trim()
										.toString() == "CONTADO") {
									cargarTiposIdentificacionGanadero("",
											"banco", false);
									cargarTiposIdentificacionGanadero("",
											"tarjeta", false);
								}

								cargarFormasPagoGanadero(etapa3.formaPago.formaPagoId);

								//cambioFormaPago(etapa3.formaPago.formaPagoId, false);
								$("#cboFpFormaPago").val(
										etapa3.formaPago.formaPagoId);

								if (etapa3.formaPago.formaPagoNombre.trim()
										.toString() == "DEBITO BANCARIO") {
									cargarTiposIdentificacionGanadero("",
											"tarjeta", false);
									cargarTiposIdentificacionGanadero(
											etapa3.formaPago.tipoIdentificacion,
											"banco", true);
									$("#bancoNumeroCuenta").val(
											etapa3.formaPago.numCuentaTarjeta);
									$("#bancoTitular").val(
											etapa3.formaPago.nombreTitular);
									$("#bancoIdentificacion")
											.val(
													etapa3.formaPago.identificacionTitular);
									$("#txtcuotaInicialbancoPlazo").val(
											etapa3.formaPago.cuotaInicial);
									$(
											"#bancoId option[value='"
													+ etapa3.formaPago.institucionFinancieraId
													+ "']").attr("selected",
											"selected");
									$(
											"#bancoTipoCuenta option[value='"
													+ etapa3.formaPago.tipoCuenta
													+ "']").attr("selected",
											"selected");
									// $("#bancoTipoIdentificacion
									// option[value='"+
									// etapa3.formaPago.tipoIdentificacion
									// +"']").attr("selected", "selected");
									$("#txtfechaInicialpago").val(
											etapa3.formaPago.fechaDebito);
									$(
											"#bancoPlazo option[value='"
													+ etapa3.formaPago.plazo
													+ "']").attr("selected",
											"selected");
									$("#descargar_certificado").fadeIn("slow")
											.removeAttr("disabled");
									$("#enviar_certificado").fadeIn("slow")
											.removeAttr("disabled");

								}

								if (etapa3.formaPago.formaPagoNombre.trim()
										.toString() == "DEBITO TARJETA") {
									cargarTiposIdentificacionGanadero(
											etapa3.formaPago.tipoIdentificacion,
											"tarjeta", true);
									cargarTiposIdentificacionGanadero("",
											"banco", false);
									$(
											"#tarjetaId option[value='"
													+ etapa3.formaPago.institucionFinancieraId
													+ "']").attr("selected",
											"selected");
									$(
											"#tarjetaTipoCuenta option[value='"
													+ etapa3.formaPago.tipoCuenta
													+ "']").attr("selected",
											"selected");
									$("#tarjetaNumero").val(
											etapa3.formaPago.numCuentaTarjeta);
									$("#tarjetaTitular").val(
											etapa3.formaPago.nombreTitular);
									$("#tarjetaMesExp").val(
											etapa3.formaPago.mesExpTarjeta);
									$("#tarjetaAnioExp").val(
											etapa3.formaPago.anioExpTarjeta);
									$("#txtcuotaInicialtarjetaPlazo").val(
											etapa3.formaPago.cuotaInicial);
									$("#tarjetaIdentificacion")
											.val(
													etapa3.formaPago.identificacionTitular);
									$(
											"#tarjetaPlazo option[value='"
													+ etapa3.formaPago.plazo
													+ "']").attr("selected",
											"selected");
									$("#descargar_certificado").fadeIn("slow")
											.removeAttr("disabled");
									$("#enviar_certificado").fadeIn("slow")
											.removeAttr("disabled");
								}

								if (etapa3.formaPago.formaPagoNombre.trim()
										.toString() == "CREDITO CUOTAS") {
									var listadoCuotas = etapa3.listadoCuotas;
									var filasCuotas = "";
									$("#detallePagoCuotas").empty();
									$
											.each(
													listadoCuotas,
													function(index) {
														filasCuotas = filasCuotas
																+ "<tr>"
																+ "<td align='center'><b>"
																+ listadoCuotas[index].cuotaOrden
																+ "</b></td>";
														if (index == 0) {
															$(
																	"#txtcuotaInicial")
																	.val(
																			etapa3.formaPago.cuotaInicial);
															$("#cboFpPlazo")
																	.val(
																			etapa3.formaPago.plazo);
															filasCuotas = filasCuotas
																	+ "<td align='center'><input type='text' class='detalleChequesPagos' id='cuotaInicial'  size='12' style='margin: 5px; padding: 5px;' value='"
																	+ listadoCuotas[index].cuotaNumCheque
																	+ "' disabled='disabled'></td>";
														} else {
															filasCuotas = filasCuotas
																	+ "<td align='center'><input type='text' class='detalleChequesPagos' id='cuotaInicial'  size='12' style='margin: 5px; padding: 5px;' value='"
																	+ listadoCuotas[index].cuotaNumCheque
																	+ "'></td>";
														}

														filasCuotas = filasCuotas
																+ "<td align='center'>"
																+ parseFloat(
																		listadoCuotas[index].cuotaValor)
																		.toFixed(
																				2)
																+ "</td>"
																+ "<td align='center'>"
																+ listadoCuotas[index].cuotaFechaPago
																+ "</td>";
													});
									$("#detallePagoCuotas").append(filasCuotas);
									$("#rowDetallePagos").show();
									$("#descargar_certificado").fadeIn("slow")
											.removeAttr("disabled");
									$("#enviar_certificado").fadeIn("slow")
											.removeAttr("disabled");
								}

							} else {
								cargarTiposIdentificacionGanadero("", "banco",
										false);
								cargarTiposIdentificacionGanadero("",
										"tarjeta", false);
							}

							cargarAnimalesCotizacion();
							$("#loading").fadeIn();
							$(".loading-indicator").delay(
									(1000 * parseInt($('#numero_vehiculos')
											.val()))).fadeOut();
							$("#tabbable").delay(1000).show();

							etapa = 3;

						} else {
							cargarTiposIdentificacionGanadero("", "banco",
									false);
							cargarTiposIdentificacionGanadero("", "tarjeta",
									false);
							// $("a[href='#next']").click();
							etapa = 3;
						}

						var tipoIdentificacionId = data.datosCotizacion.etapa1.tipoIdentificacion;
						if (tipoIdentificacionId == ''
								|| tipoIdentificacionId == '1'
								|| tipoIdentificacionId == '2'
								|| tipoIdentificacionId == '3') {
							// natural
							if (data.datosCotizacion == null
									|| data.datosCotizacion.datosFacturaCliente == null) {
								$("#nombre_direccion_solicitante").val(
										datosCotizacion.etapa1.nombreCompleto);
								$("#nombre_direccion_solicitante").val(
										datosCotizacion.etapa1.nombreCompleto);
								cargarDireccionFactura();
							} else {
								// $("a[href='#next']").click();
								// cargarDireccionFactura("solicitante",data.datosCotizacion.datosFacturaCliente);
								if (data.datosCotizacion.datosFacturaAsegurado != null)
									cargarDireccionFactura(
											"asegurado",
											data.datosCotizacion.datosFacturaAsegurado);
								else
									cargarDireccionFactura("asegurado");

							}
						} else {
							cargarDireccionFactura();

						}

						if (datosCotizacion.estadoCotizacion == "Borrador") {
							$("#filaAprobarCotizacion").hide();
							$("#filaAprobadaClienteCotizacion").hide();
							$("#filaEmitirCotizacion").hide();
							$("#datosFinales").hide();
							$("#filaEnviarCotizacion").show();
							$("#tipoAnimalOrigin").empty();
							$("#selectDescargas")
									.append(
											"<option value='1'>Ficha de la Cotizaci&oacute;n</option>");
						} else if (datosCotizacion.estadoCotizacion == "Pendiente de Revisar") {
							$("#filaAprobarCotizacion").show();
							$("#filaAprobadaClienteCotizacion").hide();
							$("#filaEmitirCotizacion").hide();
							$("#datosFinales").hide();
							$("#filaEnviarCotizacion").hide();
							$("#tipoAnimalOrigin").empty();
							$("#selectDescargas")
									.append(
											"<option value='1'>Ficha de la Cotizaci&oacute;n</option>");
						} else if (datosCotizacion.estadoCotizacion == "Revision Aprobada") {
							$(".datosGanadero").each(function() {
								$(this).attr("disabled", "disabled");
							});

							$(":button").each(function() {
								$(this).attr("disabled", "disabled");
							});

							$(":checkbox").each(function() {
								$(this).attr("disabled", "disabled");
							});
							$("#Aprobada_Cliente_Cotizacion").removeAttr(
									'disabled');
							$("#Negada_Cliente_Cotizacion").removeAttr(
									'disabled');
							$("#filaAprobarCotizacion").hide();
							$("#filaAprobadaClienteCotizacion").show();
							$("#filaEmitirCotizacion").hide();
							$("#datosFinales").hide();
							$("#filaEnviarCotizacion").hide();
							$("#tipoAnimalOrigin").empty();
							$("#selectDescargas")
									.append(
											"<option value='1'>Ficha de la Cotizaci&oacute;n</option>");
							$("#selectDescargas")
									.append(
											"<option value='2'>Certificado de la Cotizaci&oacute;n</option>");
						} else if (datosCotizacion.estadoCotizacion == "Revision Negada") {
							$(".datosGanadero").each(function() {
								$(this).attr("disabled", "disabled");
							});

							$(":button").each(function() {
								$(this).attr("disabled", "disabled");
							});

							$(":checkbox").each(function() {
								$(this).attr("disabled", "disabled");
							});
							$("#filaAprobarCotizacion").hide();
							$("#filaAprobadaClienteCotizacion").hide();
							$("#filaEmitirCotizacion").hide();
							$("#datosFinales").hide();
							$("#filaEnviarCotizacion").hide();
							$("#tipoAnimalOrigin").empty();
							$("#selectDescargas")
									.append(
											"<option value='1'>Ficha de la Cotizaci&oacute;n</option>");
							$("#selectDescargas")
									.append(
											"<option value='2'>Certificado de la Cotizaci&oacute;n</option>");
						} else if (datosCotizacion.estadoCotizacion == "Pendiente de Emitir") {
							$(".datosGanadero").each(function() {
								$(this).attr("disabled", "disabled");
							});

							$(":button").each(function() {
								$(this).attr("disabled", "disabled");
							});

							$(":checkbox").each(function() {
								$(this).attr("disabled", "disabled");
							});
							$("#Aprobar_Cotizacion").removeAttr('disabled');
							$("#Negar_Cotizacion").removeAttr('disabled');
							$("#filaEmitirCotizacion").show();
							$("#datosFinales").show();
							$("#filaEnviarCotizacion").hide();
							$("#finish").hide();

							$("#tipoAnimalOrigin").empty();
							$("#selectDescargas")
									.append(
											"<option value='1'>Ficha de la Cotizaci&oacute;n</option>");
							$("#selectDescargas")
									.append(
											"<option value='2'>Certificado de la Cotizaci&oacute;n</option>");
						} else if (datosCotizacion.estadoCotizacion == "Emitido") {
							$(".datosGanadero").each(function() {
								$(this).attr("disabled", "disabled");
							});

							$(":button").each(function() {
								$(this).attr("disabled", "disabled");
							});

							$(":checkbox").each(function() {
								$(this).attr("disabled", "disabled");
							});

							$("#tipoAnimalOrigin").empty();
							$("#selectDescargas")
									.append(
											"<option value='1'>Ficha de la Cotizaci&oacute;n</option>");
							$("#selectDescargas")
									.append(
											"<option value='2'>Certificado de la Cotizaci&oacute;n</option>");
						}
						$("#descargar_FichaCotizacion").removeAttr('disabled');
						$("#descargar_certificadoCotizacion").removeAttr(
								'disabled');
						$("#descargar_certificadoNormasParticulares")
								.removeAttr('disabled');
					} else {
						alert(data.error);
					}
				}
			});

	return etapa;
}

function cargarAnimalesCotizacion() {
	var listadoVehiculos = "";

	$.ajax({
		url : '../ObjetoGanaderoController',
		data : {
			"tipoConsulta" : "obtenerAnimales",
			"cotizacionId" : $("#cotizacion_id").text(),
		},
		type : 'POST',
		datatype : 'json',
		success : function(data) {
			$("#total_suma_asegurada_vh").val(data.valorAsegurado);
			$("#valor_endoso_beneficiario").attr('max', data.valorAsegurado);
			$("#prima_sin_impuestos_vh").val(data.valorPrima);
			$("#super_bancos_vh").val(data.valorSuperBancos);
			if (parseFloat(data.porcentajeDescuento) > 0)
				$("#filaDescuento").show();
			$("#porcentaje_descuento_vh").val(data.porcentajeDescuento);
			$("#seguro_campesino_vh").val(data.valorSeguroCampesino);
			$("#recargo_seguro_campesino_vh").val(data.valorRecargoCampesino);
			$("#derecho_emision_vh").val(data.valorDerechosEmision);
			$("#subtotal_vh").val(data.valorSubTotal);
			$("#iva_vh").val(data.valorIva);
			$(".total_vh").val(data.valorTotal);
			$("#total_vh_origin").val(data.valorTotal);
			$("#resumenTotalPago").children().first().empty().append(
					"$" + data.valorTotal);
			listadoVehiculos += '<thead>' + '<tr style="font-size:x-small;">'
					+ '<th class="sorting">Tipo</th>'
					+ '<th class="sorting">#Arete/Nombre</th>'
					+ '<th class="sorting">No. Chip</th>'
					+ '<th class="sorting">Raza</th>'
					+ '<th class="sorting">Edad(Meses)</th>'
					+ '<th class="sorting">Val. Asegurar $</th>'
					+ '<th>Tasa</th>' + '</tr>' + '</thead>'
					+ '<tbody style="font-size:x-small;">';

			var primaTotalSinImpuesto = Number(0.0);
			var animalesCotizacion = data.animalesCotizacion;

			$.each(animalesCotizacion, function(index) {
				listadoVehiculos += '<tr class="gradeA odd">'
						+ '<td>'
						+ animalesCotizacion[index].tipoNombre
						+ '</td>'
						+ '<td>'
						+ animalesCotizacion[index].numeroArete
						+ '</td>'
						+ '<td>'
						+ animalesCotizacion[index].numeroChip
						+ '</td>'
						+ '<td class="center">'
						+ animalesCotizacion[index].razaNombre
						+ '</td>'
						+ '<td class="center">'
						+ animalesCotizacion[index].edad
						+ '</td>'
						+ '<td class="center">'
						+ parseFloat(animalesCotizacion[index].valorAsegurar)
								.toFixed(2) + '</td>' + '<td class="center">'
						+ animalesCotizacion[index].tasa + '</td>' + "<ul>";
				'</tr>';
			});
			listadoVehiculos += '</tbody>';
			$("#tabla_animales_cotizacion").empty().html(listadoVehiculos);

			$("#tabbable").delay(1000 * parseInt($('#numero_vehiculos').val()))
					.fadeIn(1);
			$(".loading-indicator").delay(
					(800 * parseInt($('#numero_vehiculos').val()))).fadeOut(1);
		}
	});
}
/*
 * METODO QUE CONSULTA LOS DATOS DE LA ENTIDAD EN BASE AL DOCUMENTO DE
 * IDENTIFICACIÃ“N Y MUESTRA LOS DATOS EN EL FORMULARIO CORRESPONDIENTE
 */
function cargarPorIdentificacion(formulario, valor) {
	var identificacion = valor;
	if ((identificacion.length >= 10 && $("#tipo_identificacion_principal")
			.val() != 2)
			|| (identificacion.length >= 5 && ($(
					"#tipo_identificacion_principal").val() == 2 || $(
					"#tipo_identificacion_principal").val() == ""))) {
		$(".loading_identificacion").fadeIn();
		var entidad = "";

		if (formulario == "datosClienteInicio")
			cargarTablaPorIdentificacion(identificacion);
		else if (formulario == "datosAsegurado")
			cargarTablaPorIdentificacion(identificacion);

		$.ajax({
			url : '../EntidadController',
			data : {
				"identificacion" : identificacion,
				"tipoConsulta" : "cargarPorIdentificacionEnsurance"
			},
			Async : false,
			type : 'POST',
			datatype : 'json',
			success : function(data) {
				var entidad = data.entidad;
				if(entidad === undefined){
					$("#nombres").val("");
					$("#apellidos").val("");
					$("#nombre_completo").val("");
					$("#email").val(data.email);
					$("#telefono").val(data.telefono);
					$("#celular").val(data.celular);
				}
				else if (entidad.clienteIdEnsurance=="") {
					/*$("#nombres").val(data.datosFactura.nombres);
					$("#apellidos").val(datosFactura.apellidos);
					$("#nombre_completo").val(datosFactura.nombre_completo);
					$("#email").val(datosFactura.email);
					$("#telefono").val(datosFactura.telefono);
					$("#celular").val(datosFactura.celular);*/
					$(".loading_identificacion").fadeOut();
				} else {
					if (entidad.clienteIdEnsurance != ""
							&& entidad.entidadIdEnsurance != "") {
						if (formulario == "datosClienteInicio") {
							$("#codigoEntidadEnsurance").val(
									entidad.entidadIdEnsurance);
							$("#codigoClienteEnsurance").val(
									entidad.clienteIdEnsurance);
							$("#nombres").val(entidad.nombre);
							$("#apellidos").val(entidad.apellido);
							$("#nombre_completo").val(
									entidad.apellido + entidad.nombre);
							$("#email").val(entidad.mail);
							$("#tipo_identificacion_principal").val(
									entidad.tipoIdentificacion).trigger(
									"change");
						}

						if (formulario == "formasPagoDebitoBancario") {
							$("#bancoTitular").val(
									entidad.nombre + " " + entidad.apellido);
							$("#bancoTipoIdentificacion").val(
									entidad.tipoIdentificacion);
						}

						if (formulario == "formasPagoDebitoTarjetas") {
							$("#tarjetaTitular").val(
									entidad.nombre + " " + entidad.apellido);
							$("#tarjetaTipoIdentificacion").val(
									entidad.tipoIdentificacion);
						}

						if (formulario == "datosAsegurado") {
							$("#nombres_asegurado").val(entidad.nombre);
							$("#apellidos_asegurado").val(entidad.apellido);
							$("#nombre_completo_asegurado").val(
									entidad.nombre + " " + entidad.apellido);
							cargarTiposIdentificacionGanadero(
									entidad.tipoIdentificacion, "asegurado",
									true);
							// if(data.datosFactura!=null){
							// cargarDireccionFactura("asegurado",
							// data.datosFactura);
							// }
						}
					} else {
						$("#nombres").val("");
						$("#apellidos").val("");
						$("#nombre_completo").val("");
						$("#email").val("");
						$("#telefono").val("");
						$("#celular").val("");
					}
				}
				$(".loading_identificacion").fadeOut();
			},
			error : function(xhr, ajaxOptions, thrownError) {
				$(".loading_identificacion").fadeOut();
			}
		});
	}
}

function mostrarAdicionalesSolicitante() {
	var opcion = $('#tipo_identificacion_principal').val();

	if (opcion == 1 || opcion == 2 || opcion == 3) {
		$("#filaNatural").show();
		$("#filaJuridica").hide();
	} else if (opcion == 4 || opcion == 5) {
		$("#filaNatural").hide();
		$("#filaJuridica").show();
	}
}

function mostrarAdicionalesAsegurado() {
	var opcion = $('#tipo_identificacion_asegurado').val();
	if (opcion == 1 || opcion == 2 || opcion == 3) {
		$("#filaNaturalAsegurado").show();
		$("#filaJuridicaAsegurado").hide();

		$("#ubicacionPersonaNatural").show();
		$("#datosPoliticaNatural").show();
		$("#situacionFinancieraNatural").show();
		$("#ubicacionPersonaJuridica").hide();
		$("#datosPoliticaJuridica").hide();
		$("#situacionFinancieraJuridica").hide();
		if (!cargadoDatosUPLA)
			cargarDatosEnGanaderoUPLANatural({
				"" : ""
			});
	} else if (opcion == 4 || opcion == 5) {
		$("#filaNaturalAsegurado").hide();
		$("#filaJuridicaAsegurado").show();
		$("#ubicacionPersonaNatural").hide();
		$("#datosPoliticaNatural").hide();
		$("#situacionFinancieraNatural").hide();
		$("#ubicacionPersonaJuridica").show();
		$("#datosPoliticaJuridica").show();
		$("#situacionFinancieraJuridica").show();
		if (!cargadoDatosUPLA)
			cargarDatosEnGanaderoUPLAJuridica({
				"" : ""
			});
	}
}

function cargarDatosEnGanaderoUPLAJuridica(datosJuridica) {
	if (datosJuridica.objetoSocial != null)
		$("#objeto_social_juridica").val(datosJuridica.objetoSocial);
	else
		$("#objeto_social_juridica").val("");

	if (datosJuridica.ciudadJuridica != null)
		$("#ciudad_juridica").val(datosJuridica.ciudadJuridica);
	else
		$("#ciudad_juridica").val("");

	if (datosJuridica.zonaDireccionMatriz != null)
		$("#zona_direccion_matriz_juridica").val(
				datosJuridica.zonaDireccionMatriz).attr('required', 'required')
				.trigger('change');
	else
		$("#zona_direccion_matriz_juridica").val("").attr('required',
				'required');

	if (datosJuridica.provinciaDireccionMatriz != null)
		cargarProvincias(datosJuridica.provinciaDireccionMatriz,
				"direccion_matriz_juridica");
	else
		cargarProvincias("", "direccion_matriz_juridica");

	if (datosJuridica.zonaDireccionMatriz == "R") {
		if (datosJuridica.cantonDireccionMatriz != null) {
			if (datosJuridica.provinciaDireccionMatriz != null)
				cargarCantones(datosJuridica.cantonDireccionMatriz,
						datosJuridica.provinciaDireccionMatriz,
						"direccion_matriz_juridica");
		} else {
			if (datosJuridica.provinciaDireccionMatriz != null)
				cargarCantones("", datosJuridica.provinciaDireccionMatriz,
						"direccion_matriz_juridica");
		}

		if (datosJuridica.parroquiaDireccionMatriz != null) {
			if (datosJuridica.cantonDireccionMatriz != null)
				cargarParroquias(datosJuridica.parroquiaDireccionMatriz,
						datosJuridica.cantonDireccionMatriz,
						"direccion_matriz_juridica");
		} else {
			if (datosJuridica.cantonDireccionMatriz != null)
				cargarParroquias("", datosJuridica.cantonDireccionMatriz,
						"direccion_matriz_juridica");
		}

	} else {
		if (datosJuridica.ciudadDireccionMatriz != null) {
			if (datosJuridica.provinciaDireccionMatriz != null)
				obtenerCiudadesPorProvinciaPJuridica(
						datosJuridica.ciudadDireccionMatriz,
						datosJuridica.provinciaDireccionMatriz,
						"direccion_matriz_juridica");
		} else {
			if (datosJuridica.provinciaDireccionMatriz != null)
				obtenerCiudadesPorProvinciaPJuridica("",
						datosJuridica.provinciaDireccionMatriz,
						"direccion_matriz_juridica");
		}
	}

	if (datosJuridica.callePrincipalMatriz != null)
		$("#calle_principal_direccion_juridica").val(
				datosJuridica.callePrincipalMatriz);
	else
		$("#calle_principal_direccion_juridica").val("");

	if (datosJuridica.numeroDireccionMatriz != null)
		$("#numero_direccion_juridica")
				.val(datosJuridica.numeroDireccionMatriz);
	else
		$("#numero_direccion_juridica").val("");

	if (datosJuridica.calleSecundariaMatriz != null)
		$("#calle_secundaria_direccion_juridica").val(
				datosJuridica.calleSecundariaMatriz);
	else
		$("#calle_secundaria_direccion_juridica").val("");

	if (datosJuridica.referenciaDireccionMatriz != null)
		$("#referencia_direccion_juridica").val(
				datosJuridica.referenciaDireccionMatriz);
	else
		$("#referencia_direccion_juridica").val("");

	if (datosJuridica.sucursalDireccion != null)
		$("#direccion_sucursal_juridica").val(datosJuridica.sucursalDireccion);
	else
		$("#direccion_sucursal_juridica").val("");

	if (datosJuridica.sucursalCiudad != null)
		$("#ciudad_sucursal_juridica").val(datosJuridica.sucursalCiudad);
	else
		$("#ciudad_sucursal_juridica").val("");

	if (datosJuridica.telefono != null)
		$("#telefono_juridica").val(datosJuridica.telefono);
	else
		$("#telefono_juridica").val("");

	if (datosJuridica.fax != null)
		$("#fax_juridica").val(datosJuridica.fax);
	else
		$("#fax_juridica").val("");

	// actividad persona natural

	if (datosJuridica.nombresRepresentanteLegal != null)
		$("#nombres_representante_juridica").val(
				datosJuridica.nombresRepresentanteLegal);
	else
		$("#nombres_representante_juridica").val("");

	if (datosJuridica.apellidosRepresentanteLegal != null)
		$("#apellidos_representante_juridica").val(
				datosJuridica.apellidosRepresentanteLegal);
	else
		$("#apellidos_representante_juridica").val("");

	if (datosJuridica.tipoIdentificacionRepresentante != null)
		cargarTiposIdentificacionGanadero(
				datosJuridica.tipoIdentificacionRepresentante,
				"representante_juridica", true);
	else
		cargarTiposIdentificacionGanadero("", "representante_juridica", false);

	if (datosJuridica.identificacionRepresentante != null)
		$("#identificacion_representante_juridica").val(
				datosJuridica.identificacionRepresentante);
	else
		$("#identificacion_representante_juridica").val("");

	if (datosJuridica.lugarNacimientoRepresentante != null)
		$("#lugar_nacimiento_representante_juridica").val(
				datosJuridica.lugarNacimientoRepresentante);
	else
		$("#lugar_nacimiento_representante_juridica").val("");

	if (datosJuridica.fechaNacimiento != null) {
		var dia = datosJuridica.fechaNacimiento.date < 10 ? '0'
				+ datosJuridica.fechaNacimiento.date
				: datosJuridica.fechaNacimiento.date;
		var mes = datosJuridica.fechaNacimiento.month < 9 ? "0"
				+ (datosJuridica.fechaNacimiento.month + 1)
				: (datosJuridica.fechaNacimiento.month + 1);
		var anio = (1900 + datosJuridica.fechaNacimiento.year);
		var aux = '' + anio + '-' + mes + '-' + dia;

		$("#fecha_nacimiento_representante_juridica").val(aux);
	} else
		$("#fecha_nacimiento_representante_juridica").val("");

	if (datosJuridica.residenciaRepresentante != null)
		$("#residencia_representante_juridica").val(
				datosJuridica.residenciaRepresentante);
	else
		$("#residencia_representante_juridica").val("");

	/*
	 * if (datosJuridica.paisRepresentante != null)
	 * $("#pais_representante_juridica").val(datosJuridica.paisRepresentante);
	 * else $("#pais_representante_juridica").val("");
	 */

	if (datosJuridica.provinciaRepresentante != null)
		cargarProvincias(datosJuridica.provinciaRepresentante,
				"representante_juridica");
	// $("#provincia_representante_juridica").val(datosJuridica.provinciaRepresentante);
	else
		cargarProvincias("", "representante_juridica");
	// $("#provincia_representante_juridica").val("");

	if (datosJuridica.ciudadRepresentante != null)
		obtenerCiudadesPorProvinciaPJuridica(datosJuridica.ciudadRepresentante,
				datosJuridica.provinciaRepresentante, "representante_juridica");
	// $("#ciudad_representante_juridica").val(datosJuridica.ciudadRepresentante);
	else
		// $("#ciudad_representante_juridica").val("");
		obtenerCiudadesPorProvinciaPJuridica('',
				datosJuridica.provinciaRepresentante, "representante_juridica");

	if (datosJuridica.telefonoRepresentante != null)
		$("#telefono_representante_juridica").val(
				datosJuridica.telefonoRepresentante);
	else
		$("#telefono_representante_juridica").val("");

	if (datosJuridica.celularRepresentante != null)
		$("#celular_representante_legal").val(
				datosJuridica.celularRepresentante);
	else
		$("#celular_representante_legal").val("");

	if (datosJuridica.expuestoRepresentante != null)
		$("#expuesto_representante_juridica").val(
				datosJuridica.expuestoRepresentante ? 1 : 0);
	else
		$("#expuesto_representante_juridica").val("");

	if (datosJuridica.cargoExpuesta != null)
		$("#cargo_expuesta_representante_juridica").val(
				datosJuridica.cargoExpuesta);
	else
		$("#cargo_expuesta_representante_juridica").val("");

	if (datosJuridica.expuestoFamiliar != null)
		$("#expuesto_familiar_juridica").val(
				datosJuridica.expuestoFamiliar ? 1 : 0);
	else
		$("#expuesto_familiar_juridica").val("");

	if (datosJuridica.parentescoExpuestoFamiliar != null)
		$("#parentesco_expuesto_familiar_juridico").val(
				datosJuridica.parentescoExpuestoFamiliar);
	else
		$("#parentesco_expuesto_familiar_juridico").val("");

	if (datosJuridica.cargoExpuestoFamiliar != null)
		$("#cargo_expuesto_familiar_juridica").val(
				datosJuridica.cargoExpuestoFamiliar);
	else
		$("#cargo_expuesto_familiar_juridica").val("");

	if (datosJuridica.apellidoPaternoConyuge != null)
		$("#apellido_paterno_conyuge_juridica").val(
				datosJuridica.apellidoPaternoConyuge);
	else
		$("#apellido_paterno_conyuge_juridica").val("");

	if (datosJuridica.apellidoMaternoConyuge != null)
		$("#apellido_materno_conyuge_juridica").val(
				datosJuridica.apellidoMaternoConyuge);
	else
		$("#apellido_materno_conyuge_juridica").val("");

	if (datosJuridica.nombreConyuge != null)
		$("#nombre_conyuge_juridica").val(datosJuridica.nombreConyuge);
	else
		$("#nombre_conyuge_juridica").val("");

	if (datosJuridica.tipoIdentificacionConyuge != null)
		cargarTiposIdentificacionGanadero(
				datosJuridica.tipoIdentificacionConyuge, "conyugue_juridica",
				true);
	else
		cargarTiposIdentificacionGanadero("", "conyugue_juridica", false);

	if (datosJuridica.identificacionConyuge != null)
		$("#identificacion_conyuge_juridica").val(
				datosJuridica.identificacionConyuge);
	else
		$("#identificacion_conyuge_juridica").val("");

	if (datosJuridica.salarioMensual != null)
		$("#salario_mensual_juridica").val(datosJuridica.salarioMensual);
	else
		$("#salario_mensual_juridica").val("");

	if (datosJuridica.activos != null)
		$("#activos_juridica").val(datosJuridica.activos);
	else
		$("#activos_juridica").val("");

	if (datosJuridica.otrosIngresos != null)
		$("#otros_ingresos_juridica").val(datosJuridica.otrosIngresos);
	else
		$("#otros_ingresos_juridica").val("");

	if (datosJuridica.pasivos != null)
		$("#pasivos_juridica").val(datosJuridica.pasivos);
	else
		$("#pasivos_juridica").val("");

	if (datosJuridica.egresos != null)
		$("#egresos_juridica").val(datosJuridica.egresos);
	else
		$("#egresos_juridica").val("");

	if (datosJuridica.patrimonio != null)
		$("#patrimonio_juridica").val(datosJuridica.patrimonio);
	else
		$("#patrimonio_juridica").val("");

	if (datosJuridica.ingresoEgreso != null)
		$("#ingresos_egresos_juridica").val(datosJuridica.ingresoEgreso);
	else
		$("#ingresos_egresos_juridica").val("");

	if (datosJuridica.esAsegurado != null)
		$("#es_asegurado_juridica").val(datosJuridica.esAsegurado ? 1 : 0);
	else
		$("#es_asegurado_juridica").val("");

	if (datosJuridica.esBeneficiario != null)
		$("#es_beneficiario_juridica")
				.val(datosJuridica.esBeneficiario ? 1 : 0);
	else
		$("#es_beneficiario_juridica").val("");

	if (datosJuridica.tipoIdentificacionAsegurado != null)
		cargarTiposIdentificacionGanadero(
				datosJuridica.tipoIdentificacionAsegurado,
				"asegurado_juridica", true);
	else
		cargarTiposIdentificacionGanadero("", "asegurado_juridica", false);

	if (datosJuridica.identificacionAsegurado != null)
		$("#identificacion_asegurado_juridica").val(
				datosJuridica.identificacionAsegurado);
	else
		$("#identificacion_asegurado_juridica").val("");

	if (datosJuridica.nombreCompletoAsegurado != null)
		$("#nombre_asegurado_juridica").val(
				datosJuridica.nombreCompletoAsegurado);
	else
		$("#nombre_asegurado_juridica").val("");

	if (datosJuridica.direccionAsegurado != null)
		$("#direccion_asegurado_juridica")
				.val(datosJuridica.direccionAsegurado);
	else
		$("#direccion_asegurado_juridica").val("");

	if (datosJuridica.telefonoAsegurado != null)
		$("#telefono_asegurado_juridica").val(datosJuridica.telefonoAsegurado);
	else
		$("#telefono_asegurado_juridica").val("");

	if (datosJuridica.celularAsegurado != null)
		$("#celular_asegurado_juridica").val(datosJuridica.celularAsegurado);
	else
		$("#celular_asegurado_juridica").val("");

	if (datosJuridica.relacionAsegurado != null)
		$("#relacion_asegurado_juridica").val(datosJuridica.relacionAsegurado);
	else
		$("#relacion_asegurado_juridica").val("");

	// beneficiario
	if (datosJuridica.tipoIdentificacionBeneficiario != null)
		cargarTiposIdentificacionGanadero(
				datosJuridica.tipoIdentificacionBeneficiario,
				"beneficiario_juridica", true);
	else
		cargarTiposIdentificacionGanadero("", "beneficiario_juridica", false);

	if (datosJuridica.identificacionBeneficiario != null)
		$("#identificacion_beneficiario_juridica").val(
				datosJuridica.identificacionBeneficiario);
	else
		$("#identificacion_beneficiario_juridica").val("");

	if (datosJuridica.nombreCompletoBeneficiario != null)
		$("#nombre_beneficiario_juridica").val(
				datosJuridica.nombreCompletoBeneficiario);
	else
		$("#nombre_beneficiario_juridica").val("");

	if (datosJuridica.direccionBeneficiario != null)
		$("#direccion_beneficiario_juridica").val(
				datosJuridica.direccionBeneficiario);
	else
		$("#direccion_beneficiario_juridica").val("");

	if (datosJuridica.telefonoBeneficiario != null)
		$("#telefono_beneficiario_juridica").val(
				datosJuridica.telefonoBeneficiario);
	else
		$("#telefono_beneficiario_juridica").val("");

	if (datosJuridica.celularBeneficiario != null)
		$("#celular_beneficiario_juridica").val(
				datosJuridica.celularBeneficiario);
	else
		$("#celular_beneficiario_juridica").val("");

	if (datosJuridica.relacionBeneficiario != null)
		$("#relacion_beneficiario_juridica").val(
				datosJuridica.relacionBeneficiario);
	else
		$("#relacion_beneficiario_juridica").val("");

	cargadoDatosUPLA = true;

}

function cargarDatosEnGanaderoUPLANatural(datosNatural) {

	if (datosNatural.lugarNacimiento != null)
		$("#lugar_nacimiento_natural").val(datosNatural.lugarNacimiento);
	else
		$("#lugar_nacimiento_natural").val("");

	if (datosNatural.fechaNacimiento != null) {
		var dia = datosNatural.fechaNacimiento.date < 10 ? '0'
				+ datosNatural.fechaNacimiento.date
				: datosNatural.fechaNacimiento.date;
		var mes = datosNatural.fechaNacimiento.month < 9 ? "0"
				+ (datosNatural.fechaNacimiento.month + 1)
				: (datosNatural.fechaNacimiento.month + 1);
		var anio = (1900 + datosNatural.fechaNacimiento.year);
		var aux = '' + anio + '-' + mes + '-' + dia;

		$("#fecha_nacimiento_natural").val(aux);
	} else
		$("#fecha_nacimiento_natural").val("");

	if (datosNatural.zonaDireccionCliente != null)
		$("#zona_direccion_natural").val(datosNatural.zonaDireccionCliente)
				.attr('required', 'required').trigger('change');
	else
		$("#zona_direccion_natural").val("").attr('required', 'required');

	if (datosNatural.provinciaDireccionCliente != null)
		cargarProvinciasPNatural(datosNatural.provinciaDireccionCliente,
				"direccion_cliente_natural");
	else
		cargarProvinciasPNatural("", "direccion_cliente_natural");

	if (datosNatural.zonaDireccionCliente == "R") {
		if (datosNatural.cantonDireccionCliente != null) {
			if (datosNatural.provinciaDireccionCliente != null)
				cargarCantones(datosNatural.cantonDireccionCliente,
						datosNatural.provinciaDireccionCliente,
						"direccion_cliente_natural");
		} else {
			if (datosNatural.provinciaDireccionCliente != null)
				cargarCantones("", datosNatural.provinciaDireccionCliente,
						"direccion_cliente_natural");
		}

		if (datosNatural.parroquiaDireccionCliente != null) {
			if (datosNatural.cantonDireccionCliente != null)
				cargarParroquias(datosNatural.parroquiaDireccionCliente,
						datosNatural.cantonDireccionCliente,
						"direccion_cliente_natural");
		} else {
			if (datosNatural.cantonDireccionCliente != null)
				cargarParroquias("", datosNatural.cantonDireccionCliente,
						"direccion_cliente_natural");
		}

	} else {
		if (datosNatural.ciudadDireccionCliente != null) {
			if (datosNatural.provinciaDireccionCliente != null)
				obtenerCiudadesPorProvinciaPNatural(
						datosNatural.ciudadDireccionCliente,
						datosNatural.provinciaDireccionCliente,
						"direccion_cliente_natural");
		} else {
			if (datosNatural.provinciaDireccionCliente != null)
				obtenerCiudadesPorProvinciaPNatural("",
						datosNatural.provinciaDireccionCliente,
						"direccion_cliente_natural");
		}
	}

	if (datosNatural.callePrincipalCliente != null)
		$("#calle_principal_direccion_natural").val(
				datosNatural.callePrincipalCliente);
	else
		$("#calle_principal_direccion_natural").val("");

	if (datosNatural.numeroDireccionCliente != null)
		$("#numero_direccion_natural").val(datosNatural.numeroDireccionCliente);
	else
		$("#numero_direccion_natural").val("");

	if (datosNatural.calleSecundariaCliente != null)
		$("#calle_secundaria_direccion_natural").val(
				datosNatural.calleSecundariaCliente);
	else
		$("#calle_secundaria_direccion_natural").val("");

	if (datosNatural.referenciaDireccionCliente != null)
		$("#referencia_direccion_natural").val(
				datosNatural.referenciaDireccionCliente);
	else
		$("#referencia_direccion_natural").val("");

	if (datosNatural.telefonoCliente != null)
		$("#telefono_cliente_natural").val(datosNatural.telefonoCliente);
	else
		$("#telefono_cliente_natural").val("");

	if (datosNatural.celularCliente != null)
		$("#celular_cliente_natural").val(datosNatural.celularCliente);
	else
		$("#celular_cliente_natural").val("");

	/*
	 * if (datosNatural.generoCliente != null)
	 * $("#genero_cliente_natural").html(genero).val(datosNatural.generoCliente);
	 * else $("#genero_cliente_natural").html(genero).val("");
	 */

	if (datosNatural.mail != null)
		$("#mail_cliente_natural").val(datosNatural.mail);
	else
		$("#mail_cliente_natural").val("");

	/*
	 * if (datosNatural.tipoActividadCliente != null)
	 * $("#tipo_actividad_cliente_natural").val(datosNatural.tipoActividadCliente);
	 * else $("#tipo_actividad_cliente_natural").val("");
	 */

	if (datosNatural.cargoOcupaCliente != null)
		$("#cargo_ocupa_cliente_natural").val(datosNatural.cargoOcupaCliente);
	else
		$("#cargo_ocupa_cliente_natural").val("");

	if (datosNatural.expuestoCliente != null)
		$("#expuesto_cliente_natural").val(datosNatural.expuesto ? 1 : 0);
	else
		$("#expuesto_cliente_natural").val("");

	if (datosNatural.cargoExpuestoCliente != null)
		$("#cargo_expuesto_cliente_natural").val(datosNatural.cargoExpuesta);
	else
		$("#cargo_expuesto_cliente_natural").val("");

	if (datosNatural.expuestoFamiliar != null)
		$("#expuesto_familiar_natural").val(
				datosNatural.expuestoFamiliar ? 1 : 0);
	else
		$("#expuesto_familiar_natural").val("");

	if (datosNatural.parentescoExpuestoFamiliar != null)
		$("#parentesco_expuesto_familiar_natural").val(
				datosNatural.parentescoExpuestoFamiliar);
	else
		$("#parentesco_expuesto_familiar_natural").val("");

	if (datosNatural.cargoExpuestoFamiliar != null)
		$("#cargo_expuesto_familiar_natural").val(
				datosNatural.cargoExpuestoFamiliar);
	else
		$("#cargo_expuesto_familiar_natural").val("");

	if (datosNatural.apellidoPaternoConyuge != null)
		$("#apellido_paterno_conyuge_natural").val(
				datosNatural.apellidoPaternoConyuge);
	else
		$("#apellido_paterno_conyuge_natural").val("");

	if (datosNatural.apellidoMaternoConyuge != null)
		$("#apellido_materno_conyuge_natural").val(
				datosNatural.apellidoMaternoConyuge);
	else
		$("#apellido_materno_conyuge_natural").val("");

	if (datosNatural.nombreConyuge != null)
		$("#nombre_conyuge_natural").val(datosNatural.nombreConyuge);
	else
		$("#nombre_conyuge_natural").val("");

	if (datosNatural.tipoIdentificacionConyuge != null)
		cargarTiposIdentificacionGanadero(
				datosNatural.tipoIdentificacionConyuge, "conyuge_natural", true);
	else
		cargarTiposIdentificacionGanadero("", "conyuge_natural", false);

	if (datosNatural.identificacionConyuge != null)
		$("#identificacion_conyuge_natural").val(
				datosNatural.identificacionConyuge);
	else
		$("#identificacion_conyuge_natural").val("");

	if (datosNatural.salarioMensual != null)
		$("#salario_mensual_natural").val(datosNatural.salarioMensual);
	else
		$("#salario_mensual_natural").val("");

	if (datosNatural.activos != null)
		$("#activos_natural").val(datosNatural.activos);
	else
		$("#activos_natural").val("");

	if (datosNatural.otrosIngresos != null)
		$("#otros_ingresos_natural").val(datosNatural.otrosIngresos);
	else
		$("#otros_ingresos_natural").val("");

	if (datosNatural.pasivos != null)
		$("#pasivos_natural").val(datosNatural.pasivos);
	else
		$("#pasivos_natural").val("");

	if (datosNatural.egresos != null)
		$("#egresos_natural").val(datosNatural.egresos);
	else
		$("#egresos_natural").val("");

	if (datosNatural.patrimonio != null)
		$("#patrimonio_natural").val(datosNatural.patrimonio);
	else
		$("#patrimonio_natural").val("");

	if (datosNatural.ingresoEgreso != null)
		$("#ingresos_egresos_natural").val(datosNatural.ingresoEgreso);
	else
		$("#ingresos_egresos_natural").val("");

	/*
	 * if (datosNatural.esAsegurado != null)
	 * $("#es_asegurado_natural").val(datosNatural.esAsegurado?1:0); else
	 * $("#es_asegurado_natural").val("");
	 * 
	 * if (datosNatural.esBeneficiario != null)
	 * $("#es_beneficiario_natural").val(datosNatural.esBeneficiario?1:0); else
	 * $("#es_beneficiario_natural").val("");
	 */

	if (datosNatural.tipoIdentificacionAsegurado != null)
		cargarTiposIdentificacionGanadero(
				datosNatural.tipoIdentificacionAsegurado, "asegurado_natural",
				true);
	else
		cargarTiposIdentificacionGanadero("", "asegurado_natural", false);

	if (datosNatural.identificacionAsegurado != null)
		$("#identificacion_asegurado_natural").val(
				datosNatural.identificacionAsegurado);
	else
		$("#identificacion_asegurado_natural").val("");

	if (datosNatural.nombreCompletoAsegurado != null)
		$("#nombre_asegurado_natural")
				.val(datosNatural.nombreCompletoAsegurado);
	else
		$("#nombre_asegurado_natural").val("");

	if (datosNatural.direccionAsegurado != null)
		$("#direccion_asegurado_natural").val(datosNatural.direccionAsegurado);
	else
		$("#direccion_asegurado_natural").val("");

	if (datosNatural.telefonoAsegurado != null)
		$("#telefono_asegurado_natural").val(datosNatural.telefonoAsegurado);
	else
		$("#telefono_asegurado_natural").val("");

	if (datosNatural.celularAsegurado != null)
		$("#celular_asegurado_natural").val(datosNatural.celularAsegurado);
	else
		$("#celular_asegurado_natural").val("");

	if (datosNatural.relacionAsegurado != null)
		$("#relacion_asegurado_natural").val(datosNatural.relacionAsegurado);
	else
		$("#relacion_asegurado_natural").val("");

	// beneficiario
	if (datosNatural.tipoIdentificacionBeneficiario != null)
		cargarTiposIdentificacionGanadero(
				datosNatural.tipoIdentificacionBeneficiario,
				"beneficiario_natural", true);
	else
		cargarTiposIdentificacionGanadero("", "beneficiario_natural", false);

	if (datosNatural.identificacionBeneficiario != null)
		$("#identificacion_beneficiario_natural").val(
				datosNatural.identificacionBeneficiario);
	else
		$("#identificacion_beneficiario_natural").val("");

	if (datosNatural.nombreCompletoBeneficiario != null)
		$("#nombre_beneficiario_natural").val(
				datosNatural.nombreCompletoBeneficiario);
	else
		$("#nombre_beneficiario_natural").val("");

	if (datosNatural.direccionBeneficiario != null)
		$("#direccion_beneficiario_natural").val(
				datosNatural.direccionBeneficiario);
	else
		$("#direccion_beneficiario_natural").val("");

	if (datosNatural.telefonoBeneficiario != null)
		$("#telefono_beneficiario_natural").val(
				datosNatural.telefonoBeneficiario);
	else
		$("#telefono_beneficiario_natural").val("");

	if (datosNatural.celularBeneficiario != null)
		$("#celular_beneficiario_natural")
				.val(datosNatural.celularBeneficiario);
	else
		$("#celular_beneficiario_natural").val("");

	if (datosNatural.relacionBeneficiario != null)
		$("#relacion_beneficiario_natural").val(
				datosNatural.relacionBeneficiario);
	else
		$("#relacion_beneficiario_natural").val("");

	cargadoDatosUPLA = true;

}

// Arreglo de elementos unicos JSON
function arregloUnicoJSON(obj) {
	var uniques = [];
	var stringify = {};
	for (var i = 0; i < obj.length; i++) {
		var keys = Object.keys(obj[i]);
		keys.sort(function(a, b) {
			return a - b
		});
		var str = '';
		for (var j = 0; j < keys.length; j++) {
			str += JSON.stringify(keys[j]);
			str += JSON.stringify(obj[i][keys[j]]);
		}
		if (!stringify.hasOwnProperty(str)) {
			uniques.push(obj[i]);
			stringify[str] = true;
		}
	}
	return uniques;
}

function getParameterByName(name) {
	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), results = regex
			.exec(location.search);
	return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g,
			" "));
}

// ************
// ***GRABAR COTIZACION Y OBJETO-GANADERO***
// ************
function guardarCotizacion() {

	var pppv = $('#punto_venta').find(":selected").attr('pxpv');
	var esContribuyente = "0";
	if($("#es_contribuyente").is(":checked"))
		esContribuyente = "1";
	
	// alert(pppv);
	$.ajax({
		url : "../CotizacionController",
		data : {
			"tipoConsulta" : "crear",
			"producto" : "ganadero",
			"grupoProductoId" : $("#grupo_productos").val(),
			"puntoVentaId" : $("#punto_venta").val(),
			"vigenciaPoliza" : $("#vigencia").val(),
			"tipoIdentificacion" : $("#tipo_identificacion_principal").val(),
			"productoXPuntoDeVenta" : pppv,
			"identificacion" : $("#identificacion").val(),
			"nombres" : $("#nombres").val(),
			"apellidos" : $("#apellidos").val(),
			"nombreCompleto" : $("#nombre_completo").val(),
			"email" : $("#email").val(),
			"telefono" : $("#telefono").val(),
			"celular" : $("#celular").val(),
			"agenteId" : $("#agentes").val(),
			"codigoEntidadEnsurance" : $("#codigoEntidadEnsurance").val(),
			"codigoClienteEnsurance" : $("#codigoClienteEnsurance").val(),
			"cotizacionId" : $("#cotizacion_id").text(),
			"tipoObjeto" : tipoObjeto,
			"grupoPorProductoId" : $("#productos").val(),
			"tasaProductoId" : $("#tasas").val(),
			"tasaProductoValor" : $("#tasa").val(),
			"esContribuyente" : esContribuyente,

		},
		type : 'post',
		datatype : 'json',
		success : function(data) {
			$("#cotizacion_id").text(data.cotizacionId);
			$("#punto_venta").attr("unidadNegocio", data.unidadNegocioId);
			// Validacion poner en la URL el id de la cotizacion
			var valorId = getParameterByName("id");
			if (valorId != null) {
				$.pushVar("id", data.cotizacionId, "", window.location.href);
			}
		}
	});
}

function guardarProductoGanadero() {
	var listaAnimalesRazaId = "";
	var listaAnimalTipo = "";
	var listaAnimalArete = "";
	var listaAnimalChips = "";
	var listaAnimalEdad = "";
	var listaAnimalValor = "";
	var listaAnimalOrigen = "";
	var listaAnimalProcedencia = "";
	var listaAnimalColor = "";
	var valorTotalAsegurar = 0;

	$("#animales tbody tr")
			.each(
					function(index) {
						$(this)
								.children("td")
								.each(
										function(index2) {
											switch (index2) {
											case 0:
												listaAnimalTipo = listaAnimalTipo
														+ ","
														+ $(this).children()
																.attr('source');
												break;
											case 1:
												if ($(this).children().val())
													listaAnimalArete = listaAnimalArete
															+ ","
															+ $(this)
																	.children()
																	.val();
												else
													listaAnimalArete = listaAnimalArete
															+ "," + " ";
												break;
											case 2:
												if ($(this).children().val())
													listaAnimalChips = listaAnimalChips
															+ ","
															+ $(this)
																	.children()
																	.val();
												else
													listaAnimalChips = listaAnimalChips
															+ "," + " ";
												break;
											case 3:
												listaAnimalesRazaId = listaAnimalesRazaId
														+ ","
														+ $(this).children()
																.attr('source');
												break;
											case 4:
												listaAnimalOrigen = listaAnimalOrigen
														+ ","
														+ $(this).children()
																.val();
												break;
											case 5:
												if ($(this).children().val())
													listaAnimalProcedencia = listaAnimalProcedencia
															+ ","
															+ $(this)
																	.children()
																	.val();
												else
													listaAnimalProcedencia = listaAnimalProcedencia
															+ "," + " ";
												break;
											case 6:
												if ($(this).children().val())
													listaAnimalColor = listaAnimalColor
															+ ","
															+ $(this)
																	.children()
																	.val();
												else
													listaAnimalColor = listaAnimalColor
															+ "," + " ";
												break;
											case 7:
												if ($(this).children().val())
													listaAnimalEdad = listaAnimalEdad
															+ ","
															+ $(this)
																	.children()
																	.val();
												else
													listaAnimalEdad = listaAnimalEdad
															+ "," + "0";
												break;
											case 8:
												if ($(this).children().val())
													listaAnimalValor = listaAnimalValor
															+ ","
															+ $(this)
																	.children()
																	.val();
												else
													listaAnimalValor = listaAnimalValor
															+ "," + "0";
												break;
											}
										});
					});

	var region = "";
	if ($("#region_costa").is(":checked"))
		region = "COSTA";
	else if ($("#region_sierra").is(":checked"))
		region = "SIERRA";
	else
		region = "ORIENTE";

	var produccion = "";
	if ($("#produccion_carne").is(":checked"))
		produccion = "CARNE";
	else if ($("#produccion_leche").is(":checked"))
		produccion = "LECHE";
	else
		produccion = "DOBLE PROPOSITO";

	var esprincipal = "";
	if ($("#esprincipal_ingreso_si").is(":checked"))
		esprincipal = "S";
	else
		esprincipal = "N";

	var asistencia = "";
	if ($("#asistencia_veterinaria_si").is(":checked"))
		asistencia = "S";
	else
		asistencia = "N";

	$
			.ajax({
				url : "../ObjetoGanaderoController",
				data : {
					"cotizacionId" : $("#cotizacion_id").text(),
					"tipoConsulta" : "crear",
					"producto" : "ganadero",
					"provinciaId" : $("#ubicacion_provincia").val(),
					"cantonId" : $("#ubicacion_Canton").val(),
					"parroquiaId" : $("#ubicacion_Parroquia").val(),
					"ubicacion" : $("#ubicacion").val(),
					"recinto" : $("#recinto").val(),
					"region" : region,
					"tipoProduccion" : produccion,
					"fincaAltitud" : produccion,
					"fincaTopografia1" : $("#topografia_pendiente1").val(),
					"fincaTopografia2" : $("#topografia_pendiente2").val(),
					"fincaTopografia3" : $("#topografia_pendiente3").val(),
					"pastoTipo" : $("#tipo_pasto").val(),
					"pastoHectareas" : $("#hectareas_pasto").val(),
					"pastoVolumen" : $("#volumen_pasto").val(),
					"pastoObservaciones" : $("#observaciones_pasto").val(),
					"animalesVacunos" : $("#animales_vacunos").val(),
					"mortalidadVacas" : $("#mortalidad_vacas").val(),
					"mortalidadVacasCausa" : $("#mortalidad_vacas_causa").val(),
					"mortalidadVaconasV" : $("#mortalidad_vaconasv").val(),
					"mortalidadVaconasVCausa" : $("#mortalidad_vaconasv_causa")
							.val(),
					"mortalidadVaconasF" : $("#mortalidad_vaconasf").val(),
					"mortalidadVaconasFCausa" : $("#mortalidad_vaconasf_causa")
							.val(),
					"mortalidadVaconasM" : $("#mortalidad_vaconasm").val(),
					"mortalidadVaconasMCausa" : $("#mortalidad_vaconasm_causa")
							.val(),
					"mortalidadToros" : $("#mortalidad_toros").val(),
					"mortalidadTorosCausa" : $("#mortalidad_toros_causa").val(),
					"mortalidadToretes" : $("#mortalidad_toretes").val(),
					"mortalidadToretesCausa" : $("#mortalidad_toretes_causa")
							.val(),
					"mortalidadTeneros" : $("#mortalidad_terneros").val(),
					"mortalidadTenerosCausa" : $("#mortalidad_terneros_causa")
							.val(),
					"mortalidadTerneras" : $("#mortalidad_terneras").val(),
					"mortalidadTernerasCausa" : $("#mortalidad_terneras_causa")
							.val(),
					"alimentacionPastoreo" : $("#alimentacion_pastoreo").is(
							':checked') ? 1 : 0,
					"alimentacionCorte" : $("#alimentacion_corte").is(
							':checked') ? 1 : 0,
					"alimentacionSogueo" : $("#alimentacion_sogueo").is(
							':checked') ? 1 : 0,
					"alimentacionOtros" : $("#alimentacion_otros").is(
							':checked') ? 1 : 0,
					"accesoAlAgua" : $("#acceso_al_agua").val(),
					"vacunacionesAftosa" : $("#vacunaciones_aftosa").is(
							':checked') ? 1 : 0,
					"vacunacionesBrucelosis" : $("#vacunaciones_brucelosis")
							.is(':checked') ? 1 : 0,
					"vacunacionesTriple" : $("#vacunaciones_triple").is(
							':checked') ? 1 : 0,
					"vacunacionesLeptospirosis" : $(
							"#vacunaciones_leptospirosis").is(':checked') ? 1
							: 0,
					"vacunacionesIbrBvd" : $("#vacunaciones_ibrbvd").is(
							':checked') ? 1 : 0,
					"vacunacionesOtras" : $("#vacunaciones_otras").is(
							':checked') ? 1 : 0,
					"enfermedadMastitis" : $("#enfermedad_mastitis").val(),
					"enfermedadPanadizo" : $("#enfermedad_panadizo").val(),
					"enfermedadFiebreleche" : $("#enfermedad_fiebreleche")
							.val(),
					"enfermedadLesionubres" : $("#enfermedad_lesionubres")
							.val(),
					"enfermedadNeumonias" : $("#enfermedad_neumonias").val(),
					"enfermedadOtras" : $("#enfermedad_otras").val(),
					"enfermedadCual" : $("#enfermedad_cual").val(),
					"parasitosInternos" : $("#parasitos_internos").is(
							':checked') ? 1 : 0,
					"parasitosInternosTrata" : $("#parasitos_internos_trata")
							.val(),
					"parasitosInternosFrecu" : $("#parasitos_internos_frecu")
							.val(),
					"parasitosExternos" : $("#parasitos_externos").is(
							':checked') ? 1 : 0,
					"parasitosExternosTrata" : $("#parasitos_externos_trata")
							.val(),
					"parasitosExternosFrecu" : $("#parasitos_externos_frecu")
							.val(),
					"asistenciaVeterinaria" : asistencia,
					"asistenciaVeterinariaFrecu" : $(
							"#asistencia_veterinaria_frecu").val(),
					"asistenciaVeterinariaProf" : $(
							"#asistencia_veterinaria_prof").val(),
					"asistenciaVeterinariaTele" : $(
							"#asistencia_veterinaria_tele").val(),
					"experienciaGanaderoAnios" : $(
							"#experiencia_ganadero_anios").val(),
					"espricipalIngreso" : esprincipal,
					"listaAnimalesRazaId" : listaAnimalesRazaId,
					"listaAnimalTipo" : listaAnimalTipo,
					"listaAnimalArete" : listaAnimalArete,
					"listaAnimalEdad" : listaAnimalEdad,
					"listaAnimalValor" : listaAnimalValor,
					"listaAnimalChips" : listaAnimalChips,
					"listaAnimalOrigen" : listaAnimalOrigen,
					"listaAnimalProcedencia" : listaAnimalProcedencia,
					"listaAnimalColor" : listaAnimalColor,
					"valorTotalAsegurar" : valorTotalAsegurar,
				},
				type : 'post',
				async : false,
				datatype : 'json',
				success : function(data) {
					$("#cotizacion_id").text(data.cotizacionId);
					$("#objetoGanaderoId").attr("unidadNegocio",
							data.unidadNegocioId);
					// Validacion poner en la URL el id de la cotizacion
					/*
					 * var valorId = getParameterByName("id"); if(valorId !=
					 * null){ $.pushVar("id", data.cotizacionId, "",
					 * window.location.href); }
					 */
				}
			});
}

/* GUARDAR EL ASEGURADO */

function guardarAsegurado(tipoConsulta) {

	var tipoIdentificacionAsegurado = $("#tipo_identificacion_asegurado").val();
	var identificacionAsegurado = $("#identificacion_asegurado").val();
	var nombreAsegurado = $("#nombres_asegurado").val();
	var apellidoAsegurado = $("#apellidos_asegurado").val();
	var nombreCompletoAsegurado = $("#nombre_completo_asegurado").val();
	var cotizacionId = $("#cotizacion_id").text();
	var aseguradoId = $("#asegurado_id").val();
	var valido = true;

	if (tipoIdentificacionAsegurado == ""
			|| tipoIdentificacionAsegurado == null) {
		$("#mensajeErrorEndosoBeneficiario").html(
				"Seleccione un tipo de identificación");
		$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
		$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
		$("#tipo_identificacion_asegurado").focus();
		valido = false;
	} else {
		if (tipoIdentificacionAsegurado != '4') {
			nombreCompletoAsegurado = apellidoAsegurado + ' ' + nombreAsegurado;
			if (nombreAsegurado == "" || nombreAsegurado == null) {
				$("#mensajeErrorEndosoBeneficiario").html(
						"Ingrese el nombre del asegurado");
				$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
				$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
				$("#nombres_asegurado").focus();
				valido = false;
			}
			if (apellidoAsegurado == "" || apellidoAsegurado == null) {
				$("#mensajeErrorEndosoBeneficiario").html(
						"Ingrese el apellido del asegurado");
				$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
				$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
				$("#apellidos_asegurado").focus();
				valido = false;
			}
		}
	}

	if (identificacionAsegurado == "" || identificacionAsegurado == null) {
		$("#mensajeErrorEndosoBeneficiario").html("Ingrese una identificación");
		$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
		$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
		$("#identificacion_asegurado").focus();
		valido = false;
	}

	if (nombreCompletoAsegurado == "" || nombreCompletoAsegurado == null) {
		$("#mensajeErrorEndosoBeneficiario").html(
				"Ingrese el nombre completo del asegurado");
		$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
		$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
		$("#nombre_completo_asegurado").focus();
		valido = false;
	}

	if (tipoConsulta == "guardar") {
		if (aseguradoId == "" || aseguradoId == null) {
			tipoConsulta = "crear";
		} else {
			tipoConsulta = "actualizar";
		}
	}
	if (valido)
		$.ajax({
			url : "../EndosoBeneficiarioController",

			data : {
				"tipoConsulta" : tipoConsulta,
				"tipoIdentificacionAsegurado" : tipoIdentificacionAsegurado,
				"identificacionAsegurado" : identificacionAsegurado,
				"nombreAsegurado" : nombreAsegurado,
				"apellidoAsegurado" : apellidoAsegurado,
				"nombreCompletoAsegurado" : nombreCompletoAsegurado,
				"aseguradoId" : aseguradoId,
				"cotizacionId" : cotizacionId,
				"guardarAsegurado" : "1"
			},
			type : 'post',
			datatype : 'json',
			success : function(data) {
				if (data.success) {
					$("#asegurado_id").val(data.aseguradoId);
					$("#mensajeGraboEndosoBeneficiario").html(
							"Se guard&oacute; el asegurado exitosamente");
					$("#msgPopupEndosoBeneficiarioGrabo").fadeIn("slow");
					$("#msgPopupEndosoBeneficiarioError").fadeOut("slow");
					if (tipoConsulta == 'eliminar') {
						$("#mensajeGraboEndosoBeneficiario").html(
								"Se elimin&oacute; el asegurado exitosamente");
						$("#asegurado_id").val('');
					}

				} else {
					$("#mensajeErrorEndosoBeneficiario").html(
							"No se pudo guardar el asegurado");
					if (tipoConsulta == 'eliminar')
						$("#mensajeGraboEndosoBeneficiario").html(
								"No se pudo eliminar el asegurado");
					$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
					$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
					alert(data.error);
				}
			}
		});
}

function guardarBeneficiario(tipoConsulta) {

	var beneficiario = $("#beneficiario").select2('val');
	var monto = $("#total_suma_asegurada_vh").val();
	// var monto = $("#valor_endoso_beneficiario").val();
	var cotizacionId = $("#cotizacion_id").text();
	var endosoBeneficiarioId = $("#endoso_beneficiario_id").val();
	var valido = true;

	if (beneficiario == "" || beneficiario == null) {
		$("#mensajeErrorEndosoBeneficiario").html("Seleccione un beneficiario");
		$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
		$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
		$("#beneficiario").focus();
		valido = false;
	}

	if (monto == "" || monto == null) {
		$("#mensajeErrorEndosoBeneficiario").html("Ingrese un monto");
		$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
		$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
		$("#valor_endoso_beneficiario").focus();
		valido = false;
	} else {
		if (Number(monto) <= 0
				|| Number(monto) > Number($("#total_suma_asegurada_vh").val())) {
			$("#mensajeErrorEndosoBeneficiario").html(
					"El monto debe ser menor al valor asegurado y mayor a 0");
			$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
			$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
			$("#valor_endoso_beneficiario").focus();
			valido = false;
		}
	}

	if (tipoConsulta == "guardar") {
		if (endosoBeneficiarioId == "" || endosoBeneficiarioId == null) {
			tipoConsulta = "crear";
		} else {
			tipoConsulta = "actualizar";
		}
	}
	if (valido)
		$
				.ajax({
					url : "../EndosoBeneficiarioController",

					data : {
						"tipoConsulta" : tipoConsulta,
						"beneficiarioId" : beneficiario,
						"monto" : monto,
						"codigo" : endosoBeneficiarioId,
						"cotizacionId" : cotizacionId,
						"guardarAsegurado" : "0"
					},
					type : 'post',
					datatype : 'json',
					success : function(data) {
						if (data.success) {
							$("#endoso_beneficiario_id").val(
									data.endosoBeneficiarioId);
							$("#mensajeGraboEndosoBeneficiario")
									.html(
											"Se guard&oacute; el beneficiario exitosamente");
							$("#msgPopupEndosoBeneficiarioGrabo")
									.fadeIn("slow");
							$("#msgPopupEndosoBeneficiarioError").fadeOut(
									"slow");
							if (tipoConsulta == 'eliminar') {
								$("#mensajeGraboEndosoBeneficiario")
										.html(
												"Se elimin&oacute; el beneficiario exitosamente");
								$("#endoso_beneficiario_id").val('');
							}

						} else {
							$("#mensajeErrorEndosoBeneficiario").html(
									"No se pudo guardar el beneficiario");
							$("#msgPopupEndosoBeneficiarioGrabo").fadeOut(
									"slow");
							$("#msgPopupEndosoBeneficiarioError")
									.fadeIn("slow");
							if (tipoConsulta == 'eliminar')
								$("#mensajeGraboEndosoBeneficiario").html(
										"No se pudo eliminar el beneficiario");

							alert(data.error);
						}
					}
				});
}

function guardarDatosUPLAJuridicaEnGanadero() {

	var objetoSocial = $("#objeto_social_juridica").val();
	// var ciudad = $("#ciudad_direccion_matriz_juridica").val();
	var zonaDireccionMatriz = $("#zona_direccion_matriz_juridica").val();
	var provinciaDireccionMatriz = $("#provincia_direccion_matriz_juridica")
			.val();
	var cantonDireccionMatriz = $("#canton_direccion_matriz_juridica").val();
	var parroquiaDireccionMatriz = $("#parroquia_direccion_matriz_juridica")
			.val();
	var ciudadDireccionMatriz = $("#ciudad_direccion_matriz_juridica").val();
	var callePrincipalDireccion = $("#calle_principal_direccion_juridica")
			.val();
	var numeroDireccion = $("#numero_direccion_juridica").val();
	var calleSecundariaDireccion = $("#calle_secundaria_direccion_juridica")
			.val();
	var referenciaDireccion = $("#referencia_direccion_juridica").val();
	/*
	 * var direccionSucursal = $("#direccion_sucursal_juridica").val(); var
	 * ciudadSucursal = $("#ciudad_sucursal_juridica").val();
	 */
	var telefono = $("#telefono_juridica").val();
	var fax = $("#fax_juridica").val();
	var salarioMensual = $("#salario_mensual_juridica").val();
	var activos = $("#activos_juridica").val();
	var otrosIngresos = $("#otros_ingresos_juridica").val();
	var pasivos = $("#pasivos_juridica").val();
	var egresos = $("#egresos_juridica").val();
	var patrimonio = $("#patrimonio_juridica").val();
	var ingresosEgresos = $("#ingresos_egresos_juridica").val();
	/*
	 * var actividad = $("#actividad_economica_juridica").select2("val"); var
	 * nombresRepresentante = $("#nombres_representante_juridica").val(); var
	 * apellidosRepresentante = $("#apellidos_representante_juridica").val();
	 * var tipoIdentificacionRepresentante =
	 * $("#tipo_identificacion_representante_juridica").val(); var
	 * identificacionRepresentante =
	 * $("#identificacion_representante_juridica").val(); var
	 * lugarNacimientoRepresentante =
	 * $("#lugar_nacimiento_representante_juridica").val(); var
	 * fechaNacimientoRepresentante =
	 * $("#fecha_nacimiento_representante_juridica").val(); var
	 * residenciaRepresentante = $("#residencia_representante_juridica").val();
	 * //var paisRepresentante = $("#pais_representante_juridica").val(); var
	 * provinciaRepresentante = $("#provincia_representante_juridica").val();
	 * var ciudadRepresentante = $("#ciudad_representante_juridica").val(); var
	 * telefonoRepresentante = $("#telefono_representante_juridica").val();
	 * 
	 * var expuestoRepresentante = $("#expuesto_representante_juridica").val();
	 * var cargoExpuestaRepresentante =
	 * $("#cargo_expuesta_representante_juridica").val(); var expuestoFamiliar =
	 * $("#expuesto_familiar_juridica").val(); var parentescoExpuestoFamiliar =
	 * $("#parentesco_expuesto_familiar_juridico").val(); var
	 * cargoExpuestoFamiliar = $("#cargo_expuesto_familiar_juridica").val(); var
	 * apellidoPaternoConyuge = $("#apellido_paterno_conyuge_juridica").val();
	 * var apellidoMaternoConyuge =
	 * $("#apellido_materno_conyuge_juridica").val(); var nombreConyuge =
	 * $("#nombre_conyuge_juridica").val(); var tipoIdentificacionConyugue =
	 * $("#tipo_identificacion_conyugue_juridica").val(); var
	 * identificacionConyuge = $("#identificacion_conyuge_juridica").val(); var
	 * esAsegurado = $("#es_asegurado_juridica").val(); var esBeneficiario =
	 * $("#es_beneficiario_juridica").val(); var tipoIdentificacionAsegurado =
	 * $("#tipo_identificacion_asegurado_juridica").val(); var
	 * identificacionAsegurado = $("#identificacion_asegurado_juridica").val();
	 * var nombreAsegurado = $("#nombre_asegurado_juridica").val(); var
	 * direccionAsegurado = $("#direccion_asegurado_juridica").val(); var
	 * telefonoAsegurado = $("#telefono_asegurado_juridica").val(); var
	 * celularAsegurado = $("#celular_asegurado_juridica").val(); var
	 * relacionAsegurado = $("#relacion_asegurado_juridica").val(); var
	 * tipoIdentificacionBeneficiario =
	 * $("#tipo_identificacion_beneficiario_juridica").val(); var
	 * identificacionBeneficiario =
	 * $("#identificacion_beneficiario_juridica").val(); var nombreBeneficiario =
	 * $("#nombre_beneficiario_juridica").val(); var direccionBeneficiario =
	 * $("#direccion_beneficiario_juridica").val(); var telefonoBeneficiario =
	 * $("#telefono_beneficiario_juridica").val(); var celularBeneficiario =
	 * $("#celular_beneficiario_juridica").val(); var relacionBeneficiario =
	 * $("#telefono_beneficiario_juridica").val();
	 */
	var identificacion = $("#identificacion_asegurado").val();
	var genero = $("#genero_representante_juridico").val();
	var mail = $("#mail_representante_juridico").val();
	var celularRepresentante = $("#celular_representante_legal").val();
	var cotizacion = $("#cotizacion_id").text();
	// var ciudadJuridica = $("#ciudad_juridica").val();

	$.ajax({
		url : '../UPLAController',
		data : {
			"identificacion" : identificacion,
			"tipoConsulta" : "guardarDatosJuridica",
			"objetoSocial" : objetoSocial,
			"zonaDireccionMatriz" : zonaDireccionMatriz,
			"provinciaDireccionMatriz" : provinciaDireccionMatriz,
			"cantonDireccionMatriz" : cantonDireccionMatriz,
			"parroquiaDireccionMatriz" : parroquiaDireccionMatriz,
			"ciudadDireccionMatriz" : ciudadDireccionMatriz,
			"callePrincipalDireccion" : callePrincipalDireccion,
			"numeroDireccion" : numeroDireccion,
			"calleSecundariaDireccion" : calleSecundariaDireccion,
			"referenciaDireccion" : referenciaDireccion,
			"salarioMensual" : salarioMensual,
			"activos" : activos,
			"otrosIngresos" : otrosIngresos,
			"pasivos" : pasivos,
			"egresos" : egresos,
			"patrimonio" : patrimonio,
			"ingresosEgresos" : ingresosEgresos,
			"celularRepresentante" : celularRepresentante,
			/*
			 * "direccionSucursal":direccionSucursal,
			 * "ciudadSucursal":ciudadSucursal,
			 */
			"telefono" : telefono,
			"fax" : fax,
			/*
			 * "actividad":actividad,
			 * "nombresRepresentante":nombresRepresentante,
			 * "apellidosRepresentante":apellidosRepresentante,
			 * "tipoIdentificacionRepresentante":tipoIdentificacionRepresentante,
			 * "identificacionRepresentante":identificacionRepresentante,
			 * "lugarNacimientoRepresentante":lugarNacimientoRepresentante,
			 * "fechaNacimientoRepresentante":fechaNacimientoRepresentante,
			 * "residenciaRepresentante":residenciaRepresentante,
			 * //"paisRepresentante":paisRepresentante,
			 * "provinciaRepresentante":provinciaRepresentante,
			 * "ciudadRepresentante":ciudadRepresentante,
			 * "telefonoRepresentante":telefonoRepresentante,
			 * 
			 * "expuestoRepresentante":expuestoRepresentante,
			 * "cargoExpuestaRepresentante":cargoExpuestaRepresentante,
			 * "expuestoFamiliar":expuestoFamiliar,
			 * "parentescoExpuestoFamiliar":parentescoExpuestoFamiliar,
			 * "cargoExpuestoFamiliar":cargoExpuestoFamiliar,
			 * "apellidoPaternoConyuge":apellidoPaternoConyuge,
			 * "apellidoMaternoConyuge":apellidoMaternoConyuge,
			 * "nombreConyuge":nombreConyuge,
			 * "tipoIdentificacionConyugue":tipoIdentificacionConyugue,
			 * "identificacionConyuge":identificacionConyuge,
			 * "esAsegurado":esAsegurado, "esBeneficiario":esBeneficiario,
			 * "tipoIdentificacionAsegurado":tipoIdentificacionAsegurado,
			 * "identificacionAsegurado":identificacionAsegurado,
			 * "nombreAsegurado":nombreAsegurado,
			 * "direccionAsegurado":direccionAsegurado,
			 * "telefonoAsegurado":telefonoAsegurado,
			 * "celularAsegurado":celularAsegurado,
			 * "relacionAsegurado":relacionAsegurado,
			 * "tipoIdentificacionBeneficiario":tipoIdentificacionBeneficiario,
			 * "identificacionBeneficiario":identificacionBeneficiario,
			 * "nombreBeneficiario":nombreBeneficiario,
			 * "direccionBeneficiario":direccionBeneficiario,
			 * "telefonoBeneficiario":telefonoBeneficiario,
			 * "celularBeneficiario":celularBeneficiario,
			 * "relacionBeneficiario":relacionBeneficiario,
			 */
			"genero" : genero,
			"mail" : mail,
			// "ciudad":ciudadJuridica,
			"cotizacion" : cotizacion
		},
		type : 'post',
		datatype : 'json',
		success : function(datos) {

		}
	});
}

function guardarDatosUPLANaturalEnGanadero() {
	var lugarNacimiento = $("#lugar_nacimiento_natural").val();
	var fechaNacimiento = $("#fecha_nacimiento_natural").val();
	var zonaDireccionCliente = $("#zona_direccion_natural").val();
	var callePrincipalCliente = $("#calle_principal_direccion_natural").val();
	var calleSecundariaCliente = $("#calle_secundaria_direccion_natural").val();
	var numeroDireccionCliente = $("#numero_direccion_natural").val();
	var refenciaDireccionCliente = $("#referencia_direccion_natural").val();
	var provincia = $("#provincia_direccion_cliente_natural").val();
	var canton = $("#canton_direccion_cliente_natural").val();
	var parroquia = $("#parroquia_direccion_cliente_natural").val();
	var ciudad = $("#ciudad_direccion_cliente_natural").val();
	var telefono = $("#telefono_cliente_natural").val();
	var celular = $("#celular_cliente_natural").val();
	var mail = $("#mail_cliente_natural").val();
	var tipoIdentificacionAsegurado = $("#tipo_identificacion_asegurado").val();
	var identificacionAsegurado = $("#identificacion_asegurado").val();
	var nombreAsegurado = $("#nombres_asegurado").val();
	var apellidosAsegurado = $("#apellidos_asegurado").val();
	var domicilioAsegurado = $("#direccion_asegurado_natural").val();
	var salarioMensual = $("#salario_mensual_natural").val();
	var activo = $("#activos_natural").val();
	var otrosIngresos = $("#otros_ingresos_natural").val();
	var pasivos = $("#pasivos_natural").val();
	var egresos = $("#egresos_natural").val();
	var patrimonio = $("#patrimonio_natural").val();
	var ingresosEgresos = $("#ingresos_egresos_natural").val();
	var cotizacion = $("#cotizacion_id").text();
	var cargoOcupa = $("#cargo_ocupa_cliente_natural").val();
	/*
	 * var actividad = $("#actividad_economica_cliente_natural").select2("val");
	 * var tipoActividad = $("#tipo_actividad_cliente_natural").val(); var
	 * tipoRamoContrata = $("#ramo_contrata_cliente_natural").val(); var
	 * expuesto = $("#expuesto_cliente_natural").val(); var cargoExpuesta =
	 * $("#cargo_expuesto_cliente_natural").val(); var expuestoFamiliar =
	 * $("#expuesto_familiar_natural").val(); var parentescoFamiliar =
	 * $("#parentesco_expuesto_familiar_natural").val(); var cargoFamiliar =
	 * $("#cargo_expuesto_familiar_natural").val(); var apellidoPaternoConyuge =
	 * $("#apellido_paterno_conyuge_natural").val(); var apellidoMaternoConyuge =
	 * $("#apellido_materno_conyuge_natural").val(); var nombresConyuge =
	 * $("#nombre_conyuge_natural").val(); var tipoIdentificacionConyuge =
	 * $("#tipo_identificacion_conyuge_natural").val(); var
	 * identificacionConyuge = $("#identificacion_conyuge_natural").val();
	 * 
	 * var esAsegurado = $("#es_asegurado_natural").val(); var esBeneficiario =
	 * $("#es_beneficiario_natural").val();
	 * 
	 * var relacionAsegurado = $("#relacion_asegurado_natural").val(); var
	 * tipoIdentificacionBeneficiario =
	 * $("#tipo_identificacion_beneficiario_natural").val(); var
	 * identificacionBeneficiario =
	 * $("#identificacion_beneficiario_natural").val(); var nombreBeneficiario =
	 * $("#nombre_beneficiario_natural").val(); var domicilioBeneficiario =
	 * $("#direccion_beneficiario_natural").val(); var telefonoBeneficiario =
	 * $("#telefono_beneficiario_natural").val(); var celularBeneficiario =
	 * $("#celular_beneficiario_natural").val(); var relacionBeneficiario =
	 * $("#relacion_beneficiario_natural").val();
	 */

	$.ajax({
		url : '../UPLAController',
		data : {
			"identificacion" : identificacionAsegurado,
			"tipoConsulta" : "guardarDatosNatural",
			"lugarNacimiento" : lugarNacimiento,
			"fechaNacimiento" : fechaNacimiento,
			"zonaDireccionCliente" : zonaDireccionCliente,
			"callePrincipalCliente" : callePrincipalCliente,
			"calleSecundariaCliente" : calleSecundariaCliente,
			"numeroDireccionCliente" : numeroDireccionCliente,
			"refenciaDireccionCliente" : refenciaDireccionCliente,
			"provincia" : provincia,
			"canton" : canton,
			"parroquia" : parroquia,
			"ciudad" : ciudad,
			"telefono" : telefono,
			"celular" : celular,
			"tipoIdentificacionAsegurado" : tipoIdentificacionAsegurado,
			"identificacionAsegurado" : identificacionAsegurado,
			"nombreAsegurado" : nombreAsegurado,
			"apellidosAsegurado" : apellidosAsegurado,
			"domicilioAsegurado" : domicilioAsegurado,
			"salarioMensual" : salarioMensual,
			"activo" : activo,
			"otrosIngresos" : otrosIngresos,
			"pasivos" : pasivos,
			"egresos" : egresos,
			"patrimonio" : patrimonio,
			"ingresosEgresos" : ingresosEgresos,
			"cargoOcupa" : cargoOcupa,
			/*
			 * "actividad" : actividad, "tipoActividad" : tipoActividad,
			 * "tipoRamoContrata" : tipoRamoContrata, "expuesto" : expuesto,
			 * "cargoExpuesta" : cargoExpuesta, "expuestoFamiliar" :
			 * expuestoFamiliar, "parentescoFamiliar" : parentescoFamiliar,
			 * "cargoFamiliar" : cargoFamiliar, "apellidoPaternoConyuge" :
			 * apellidoPaternoConyuge, "apellidoMaternoConyuge" :
			 * apellidoMaternoConyuge, "nombresConyuge" : nombresConyuge,
			 * "tipoIdentificacionConyuge" : tipoIdentificacionConyuge,
			 * "identificacionConyuge" : identificacionConyuge, "esBeneficiario" :
			 * esBeneficiario,
			 * 
			 * "relacionAsegurado" : relacionAsegurado,
			 * "tipoIdentificacionBeneficiario" :
			 * tipoIdentificacionBeneficiario, "identificacionBeneficiario" :
			 * identificacionBeneficiario, "nombreBeneficiario" :
			 * nombreBeneficiario, "domicilioBeneficiario" :
			 * domicilioBeneficiario, "telefonoBeneficiario" :
			 * telefonoBeneficiario, "celularBeneficiario" :
			 * celularBeneficiario, "relacionBeneficiario" :
			 * relacionBeneficiario,
			 */
			"mail" : mail,
			"cotizacion" : cotizacion
		},
		type : 'post',
		datatype : 'json',
		success : function(datos) {

		}
	});
}

function guardarAsegurado(tipoConsulta) {
	var tipoIdentificacionAsegurado = $("#tipo_identificacion_asegurado").val();
	var identificacionAsegurado = $("#identificacion_asegurado").val();
	var nombreAsegurado = $("#nombres_asegurado").val();
	var apellidoAsegurado = $("#apellidos_asegurado").val();
	var nombreCompletoAsegurado = $("#nombre_completo_asegurado").val();
	var cotizacionId = $("#cotizacion_id").text();
	var aseguradoId = $("#asegurado_id").val();
	var valido = true;

	if (tipoIdentificacionAsegurado == ""
			|| tipoIdentificacionAsegurado == null) {
		$("#mensajeErrorEndosoBeneficiario").html(
				"Seleccione un tipo de identificación");
		$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
		$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
		$("#tipo_identificacion_asegurado").focus();
		valido = false;
	} else {
		if (tipoIdentificacionAsegurado != '4') {
			nombreCompletoAsegurado = apellidoAsegurado + ' ' + nombreAsegurado;
			if (nombreAsegurado == "" || nombreAsegurado == null) {
				$("#mensajeErrorEndosoBeneficiario").html(
						"Ingrese el nombre del asegurado");
				$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
				$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
				$("#nombres_asegurado").focus();
				valido = false;
			}
			if (apellidoAsegurado == "" || apellidoAsegurado == null) {
				$("#mensajeErrorEndosoBeneficiario").html(
						"Ingrese el apellido del asegurado");
				$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
				$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
				$("#apellidos_asegurado").focus();
				valido = false;
			}
		}
	}

	if (identificacionAsegurado == "" || identificacionAsegurado == null) {
		$("#mensajeErrorEndosoBeneficiario").html("Ingrese una identificación");
		$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
		$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
		$("#identificacion_asegurado").focus();
		valido = false;
	}

	if (nombreCompletoAsegurado == "" || nombreCompletoAsegurado == null) {
		$("#mensajeErrorEndosoBeneficiario").html(
				"Ingrese el nombre completo del asegurado");
		$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
		$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
		$("#nombre_completo_asegurado").focus();
		valido = false;
	}

	if (tipoConsulta == "guardar") {
		if (aseguradoId == "" || aseguradoId == null) {
			tipoConsulta = "crear";
		} else {
			tipoConsulta = "actualizar";
		}
	}
	if (valido)
		$.ajax({
			url : "../EndosoBeneficiarioController",

			data : {
				"tipoConsulta" : tipoConsulta,
				"tipoIdentificacionAsegurado" : tipoIdentificacionAsegurado,
				"identificacionAsegurado" : identificacionAsegurado,
				"nombreAsegurado" : nombreAsegurado,
				"apellidoAsegurado" : apellidoAsegurado,
				"nombreCompletoAsegurado" : nombreCompletoAsegurado,
				"aseguradoId" : aseguradoId,
				"cotizacionId" : cotizacionId,
				"guardarAsegurado" : "1"
			},
			type : 'post',
			datatype : 'json',
			success : function(data) {
				if (data.success) {
					$("#asegurado_id").val(data.aseguradoId);
					$("#mensajeGraboEndosoBeneficiario").html(
							"Se guard&oacute; el asegurado exitosamente");
					$("#msgPopupEndosoBeneficiarioGrabo").fadeIn("slow");
					$("#msgPopupEndosoBeneficiarioError").fadeOut("slow");
					if (tipoConsulta == 'eliminar') {
						$("#mensajeGraboEndosoBeneficiario").html(
								"Se elimin&oacute; el asegurado exitosamente");
						$("#asegurado_id").val('');
					}

				} else {
					$("#mensajeErrorEndosoBeneficiario").html(
							"No se pudo guardar el asegurado");
					if (tipoConsulta == 'eliminar')
						$("#mensajeGraboEndosoBeneficiario").html(
								"No se pudo eliminar el asegurado");
					$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
					$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
					alert(data.error);
				}
			}
		});
}
/*
function guardarPago(valor) {
	var tipoConsulta = "guardarPorCotizacion";

	$("#msgPopupPagoGrabo").hide();
	$("#msgPopupPagoError").hide();
	if (parseInt($("#codigoPagoRegistrado").val()) > 0)
		tipoConsulta = "actualizar";

	if (valor == 'contado') {
		$.ajax({
			url : '../PagoController',
			data : {
				"codigoPago" : $("#codigoPagoRegistrado").val(),
				"codigoCotizacion" : $("#cotizacion_id").text(),
				"codigoFormaPago" : $("#cboFpFormaPago").val(),
				"codigoInstFinanciera" : $("#banco_forma_pago").val(),
				"valor" : $("#total_vh").val(),
				"plazo" : "1",
				"fechaPago" : $("#fechaInicialPagos").val().replace("/", "-")
						.replace("/", "-"),
				"formaPagoSeleccionada" : "contado",
				"cuotaInicial" : $("#total_vh").val(),
				"tipoConsulta" : tipoConsulta
			},
			type : 'post',
			datatype : 'json',
			success : function(datos) {
				$("#codigoPagoRegistrado").val(datos.codigoPagoRegistrado);
				$("#msgPopupPagoGrabo").show();
				// $("#descargar_certificado").fadeIn('slow');
				// $("#enviar_certificado").fadeIn('slow');
			}
		});
	}

	if (valor == 'banco') {
		var bandera = true;
		$("#save-pagoDebitoBanco")
				.parent()
				.parent()
				.parent()
				.find(':input')
				.each(
						function() {
							var elemento = this;
							if (elemento.value == ""
									&& elemento.type != "button") {
								alert("Todos los campos del formulario son requerido. Por favor verifique que esten llenos.");
								$("#" + elemento.id).css('border-color', 'red');
								bandera = false;
								return false;
							}
						});

		if ((Date.parse($("#txtfechaInicialpago").val())) <= (Date
				.parse(new Date()))) {
			alert("La fecha de inicio de pagos no puede ser menor a la fecha actual.");
			$("#txtfechaInicialpago").css('border-color', 'red');
			bandera = false;
			return false;
		}

		if (bandera) {
			$.ajax({
				url : '../PagoController',
				data : {
					"codigoPago" : $("#codigoPagoRegistrado").val(),
					"codigoCotizacion" : $("#cotizacion_id").text(),
					"codigoFormaPago" : $("#cboFpFormaPago").val(),
					"codigoInstFinanciera" : $("#bancoId").val(),
					"tipoCuenta" : $("#bancoTipoCuenta").val(),
					"numCuenta" : $("#bancoNumeroCuenta").val(),
					"titular" : $("#bancoTitular").val(),
					"tipoIdentificacionId" : $("#tipo_identificacion_banco")
							.val(),
					"identificacion" : $("#bancoIdentificacion").val(),
					"plazo" : $("#bancoPlazo").val(),
					"fechaPago" : $("#txtfechaInicialpago").val(),
					"valor" : $("#total_vh").val(),
					"formaPagoSeleccionada" : "debitoBanco",
					"valor" : $("#total_vh").val(),
					"cuotaInicial" : $("#txtcuotaInicialbancoPlazo").val(),
					"fechaDebito" : $("#txtfechaInicialpago").val(),
					"tipoConsulta" : tipoConsulta
				},
				type : 'post',
				datatype : 'json',
				success : function(datos) {
					$("#codigoPagoRegistrado").val(datos.codigoPagoRegistrado);
					$("#msgPopupPagoGrabo").show();
					// $("#descargar_certificado").fadeIn('slow');
					// $("#enviar_certificado").fadeIn('slow');
				}
			});
		}
	}

	if (valor == 'tarjeta') {
		var bandera = true;
		$("#save-pagoDebitoTarjeta")
				.parent()
				.parent()
				.parent()
				.find(':input')
				.each(
						function() {
							var elemento = this;
							if (elemento.value == ""
									&& elemento.type != "button") {
								alert("Todos los campos del formulario son requerido. Por favor verifique que esten llenos.");
								$("#" + elemento.id).css('border-color', 'red');
								bandera = false;
								return false;
							}
						});

		if (bandera) {
			$.ajax({
				url : '../PagoController',
				data : {
					"codigoPago" : $("#codigoPagoRegistrado").val(),
					"codigoCotizacion" : $("#cotizacion_id").text(),
					"codigoFormaPago" : $("#cboFpFormaPago").val(),
					"codigoInstFinanciera" : $("#tarjetaId").val(),
					"numCuenta" : $("#tarjetaNumero").val(),
					"titular" : $("#tarjetaTitular").val(),
					"tipoCuenta" : 'T',
					"tipoIdentificacionId" : $("#tipo_identificacion_tarjeta")
							.val(),
					"identificacion" : $("#tarjetaIdentificacion").val(),
					"tarjetaAnioExp" : $("#tarjetaAnioExp").val(),
					"tarjetaMesExp" : $("#tarjetaMesExp").val(),
					"plazo" : $("#tarjetaPlazo").val(),
					"fechaPago" : $("#fechaInicialPagos").val().replace("/",
							"-").replace("/", "-"),
					"valor" : $("#total_vh").val(),
					"formaPagoSeleccionada" : "debitoTarjeta",
					"cuotaInicial" : $("#txtcuotaInicialtarjetaPlazo").val(),
					"tipoConsulta" : tipoConsulta
				},
				type : 'post',
				datatype : 'json',
				success : function(datos) {
					$("#codigoPagoRegistrado").val(datos.codigoPagoRegistrado);
					$("#msgPopupPagoGrabo").show();
					// $("#descargar_certificado").fadeIn('slow');
					// $("#enviar_certificado").fadeIn('slow');
				}
			});
		}
	}

	if (valor == 'cuota') {
		var bandera = true;
		$("#save-pagoCuotas")
				.parent()
				.parent()
				.parent()
				.find(':input')
				.each(
						function() {
							var elemento = this;
							if (elemento.value == "0"
									&& elemento.type != "button") {
								alert("Todos los campos del formulario son requerido. Por favor verifique que esten llenos.");
								$("#" + elemento.id).css('border-color', 'red');
								bandera = false;
								return false;
							}
						});

		if (bandera && $("#cboFpPlazo").val() != "0") {
			var listadoCheques = "";

			$(".detalleChequesPagos").each(function() {
				if ($(this).val() != "")
					listadoCheques = listadoCheques + $(this).val() + ",";
				else
					listadoCheques = listadoCheques + " ,";
			});

			$.ajax({
				url : '../PagoController',
				data : {
					"codigoPago" : $("#codigoPagoRegistrado").val(),
					"codigoCotizacion" : $("#cotizacion_id").text(),
					"codigoFormaPago" : $("#cboFpFormaPago").val(),
					"codigoInstFinanciera" : $("#tarjetaId").val(),
					"numCuenta" : $("#tarjetaNumero").val(),
					"titular" : $("#tarjetaTitular").val(),
					"tipoCuenta" : 'T',
					"tipoIdentificacionId" : $("#tarjetaTipoIdentificacion")
							.val(),
					"identificacion" : $("#tarjetaIdentificacion").val(),
					"tarjetaAnioExp" : $("#tarjetaAnioExp").val(),
					"tarjetaMesExp" : $("#tarjetaMesExp").val(),
					"plazo" : $("#cboFpPlazo").val(),
					"fechaPago" : $("#fechaInicialPagos").val().replace("/",
							"-").replace("/", "-"),
					"valor" : $("#total_vh").val(),
					"formaPagoSeleccionada" : "cuotas",
					"tipoConsulta" : tipoConsulta,
					"cuotaInicial" : $("#txtcuotaInicial").val(),
					"listadoCheques" : listadoCheques
				},
				type : 'post',
				datatype : 'json',
				success : function(datos) {
					$("#codigoPagoRegistrado").val(datos.codigoPagoRegistrado);
					$("#msgPopupPagoGrabo").show();
					// $("#descargar_certificado").removeAttr('disabled');
				}
			});
		}
	}
}
*/


function guardarPago(valor) {
	var tipoConsulta = "crear";

	$("#save-pagoContado").attr("disabled","disabled");
	$("#save-pagoDebitoBanco").attr("disabled","disabled");
	$("#save-pagoDebitoTarjeta").attr("disabled","disabled");
	$("#save-pagoCuotas").attr("disabled","disabled");
	$("#msgPopupPagoGrabo").hide();
	$("#msgPopupPagoError").hide();
	if (parseInt($("#codigoPagoRegistrado").val()) > 0)
		tipoConsulta = "actualizar";

	if (valor == 'contado') {
		$.ajax({
			url : '../PagoController',
			data : {
				"codigoCotizacion" : $("#cotizacion_id").text(),
				"codigoFormaPago" : $("#cboFpFormaPago").val(),
				"codigoInstFinanciera" : "",
				"plazo" : "1",
				"fechaPago" : "",
				"formaPagoSeleccionada" : "1",
				"tipoConsulta" : "guardarPorCotizacion"
				},
			type : 'post',
			datatype : 'json',
			success : function (datos) {
				if (datos.success) {
					$("#codigoPagoRegistrado").val(datos.pagoId);
					$("#msgPopupPagoGrabo").show();
					$("#msgPopupPago").attr("class", "alert alert-success").html("La forma de pago se ha registrado correctamente.").fadeIn("slow");
				} else {
					$("#codigoPagoRegistrado").val("");
					$("#msgPopupPago").attr("class", "alert alert-danger").html(datos.error).fadeIn("slow");
				}
				$("#save-pagoContado").removeAttr("disabled");
				$("#save-pagoDebitoBanco").removeAttr("disabled");
				$("#save-pagoDebitoTarjeta").removeAttr("disabled");
				$("#save-pagoCuotas").removeAttr("disabled");
				
			}
		});
	}

	if (valor == 'banco') {
		var bandera = true;
		$("#save-pagoDebitoBanco").parent().parent().parent().find(':input').each(function () {
			var elemento = this;
			if (elemento.value == "" && elemento.type != "button") {
				alert("Todos los campos del formulario son requerido. Por favor verifique que esten llenos.");
				$("#" + elemento.id).css('border-color', 'red');
				bandera = false;
				$("#save-pagoContado").removeAttr("disabled");
				$("#save-pagoDebitoBanco").removeAttr("disabled");
				$("#save-pagoDebitoTarjeta").removeAttr("disabled");
				$("#save-pagoCuotas").removeAttr("disabled");
				return false;
			}
		});

		if ((Date.parse($("#txtfechaInicialpago").val())) < (Date.parse(new Date().setHours(0, 0, 0, 0)))) {
			alert("La fecha de inicio de pagos no puede ser menor a la fecha actual.");
			$("#txtfechaInicialpago").css('border-color', 'red');
			bandera = false;
			$("#save-pagoContado").removeAttr("disabled");
			$("#save-pagoDebitoBanco").removeAttr("disabled");
			$("#save-pagoDebitoTarjeta").removeAttr("disabled");
			$("#save-pagoCuotas").removeAttr("disabled");
			return false;
		}

		if (bandera) {
			var plazo=Number($("#bancoPlazo").val());
			//if($("#txtcuotaInicialbancoPlazo").val()!=""&&$("#txtcuotaInicialbancoPlazo").val()!="0")
			//	plazo++;
			
			$.ajax({
				url : '../PagoController',
				data : {
					"codigoCotizacion" : $("#cotizacion_id").text(),
					"formaPagoSeleccionada" : "2",
					"codigoInstFinanciera" : $("#bancoId").val(),
					"tipoCuenta" : $("#bancoTipoCuenta").val(),
					"numCuenta" : $("#bancoNumeroCuenta").val(),
					"titular" : $("#bancoTitular").val(),
					"tipoIdentificacionId" : $("#tipo_identificacion_banco").val(),
					"identificacion" : $("#bancoIdentificacion").val(),
					"plazo" : plazo,
					"fechaPago" : $("#txtfechaInicialpago").val(),
					"cuotaInicial" : $("#txtcuotaInicialbancoPlazo").val(),
					"fechaPago" : $("#txtfechaInicialpago").val(),
					"tipoConsulta" : "guardarPorCotizacion"
				},
				type : 'post',
				datatype : 'json',
				success : function (datos) {
					if (datos.success) {
						$("#codigoPagoRegistrado").val(datos.pagoId);
						$("#msgPopupPago").attr("class", "alert alert-success").html("La forma de pago se ha registrado correctamente.").fadeIn("slow");
					} else {
						$("#codigoPagoRegistrado").val("");
						$("#msgPopupPago").attr("class", "alert alert-danger").html(datos.error).fadeIn("slow");
					}
					$("#save-pagoContado").removeAttr("disabled");
					$("#save-pagoDebitoBanco").removeAttr("disabled");
					$("#save-pagoDebitoTarjeta").removeAttr("disabled");
					$("#save-pagoCuotas").removeAttr("disabled");
					
				}
			});

		}
	}

	if (valor == 'tarjeta') {
		
		var plazo=Number($("#tarjetaPlazo").val());
		//if($("#txtcuotaInicialbancoPlazo").val()!=""&&$("#txtcuotaInicialbancoPlazo").val()!="0")
		//	plazo++;
		
		var bandera = true;
		$("#save-pagoDebitoTarjeta").parent().parent().parent().find(':input').each(function () {
			var elemento = this;
			if (elemento.value == "" && elemento.type != "button") {
				alert("Todos los campos del formulario son requerido. Por favor verifique que esten llenos.");
				$("#" + elemento.id).css('border-color', 'red');
				bandera = false;
				$("#save-pagoContado").removeAttr("disabled");
				$("#save-pagoDebitoBanco").removeAttr("disabled");
				$("#save-pagoDebitoTarjeta").removeAttr("disabled");
				$("#save-pagoCuotas").removeAttr("disabled");
				
				return false;
			}
		});

		if (bandera) {
			$.ajax({
				url : '../PagoController',
				data : {
					"codigoCotizacion" : $("#cotizacion_id").text(),
					"formaPagoSeleccionada" : "3",
					"codigoInstFinanciera" : $("#tarjetaId").val(),
					"numCuenta" : $("#tarjetaNumero").val(),
					"titular" : $("#tarjetaTitular").val(),
					"tipoCuenta" : 'T',
					"tipoIdentificacionId" : $("#tipo_identificacion_tarjeta").val(),
					"identificacion" : $("#tarjetaIdentificacion").val(),
					"tarjetaAnioExp" : $("#tarjetaAnioExp").val(),
					"tarjetaMesExp" : $("#tarjetaMesExp").val(),
					"plazo" : plazo,
					"fechaPago" : $("#txtfechaInicialpagoTarjeta").val(),
					"cuotaInicial" : $("#txtcuotaInicialtarjetaPlazo").val(),
					"tipoConsulta" : "guardarPorCotizacion"
				},
				type : 'post',
				datatype : 'json',
				success : function (datos) {
					if (datos.success) {
						$("#codigoPagoRegistrado").val(datos.pagoId);
						$("#msgPopupPago").attr("class", "alert alert-success").html("La forma de pago se ha registrado correctamente.").fadeIn("slow");
					} else {
						$("#codigoPagoRegistrado").val("");
						$("#msgPopupPago").attr("class", "alert alert-danger").html(datos.error).fadeIn("slow");
					}
					$("#save-pagoContado").removeAttr("disabled");
					$("#save-pagoDebitoBanco").removeAttr("disabled");
					$("#save-pagoDebitoTarjeta").removeAttr("disabled");
					$("#save-pagoCuotas").removeAttr("disabled");
					
				}
			});
		}
	}

	if (valor == 'cuota') {
		var plazo=Number($("#cboFpPlazo").val());
		//if($("#txtcuotaInicialbancoPlazo").val()!=""&&$("#txtcuotaInicialbancoPlazo").val()!="0")
		//	plazo++;
		
		var bandera = true;
		$("#save-pagoCuotas").parent().parent().parent().find(':input').each(function () {
			var elemento = this;
			if (elemento.value == "0" && elemento.type != "button") {
				alert("Todos los campos del formulario son requerido. Por favor verifique que esten llenos.");
				$("#" + elemento.id).css('border-color', 'red');
				bandera = false;
				$("#save-pagoContado").removeAttr("disabled");
				$("#save-pagoDebitoBanco").removeAttr("disabled");
				$("#save-pagoDebitoTarjeta").removeAttr("disabled");
				$("#save-pagoCuotas").removeAttr("disabled");
				
				return false;
			}
		});

		if (bandera && $("#cboFpPlazo").val() != "0") {
			var listadoCheques = "";

			$(".detalleChequesPagos").each(function () {
				if ($(this).val() != "")
					listadoCheques = listadoCheques + $(this).val() + ",";
				else
					listadoCheques = listadoCheques + " ,";
			});

			$.ajax({
				url : '../PagoController',
				data : {
					"codigoCotizacion" : $("#cotizacion_id").text(),
					"tipoIdentificacionId" : $("#tarjetaTipoIdentificacion").val(),
					"identificacion" : $("#tarjetaIdentificacion").val(),
					"plazo" : plazo,
					"formaPagoSeleccionada" : "4",
					"tipoConsulta" : "guardarPorCotizacion",
					"cuotaInicial" : $("#txtcuotaInicial").val()
				},
				type : 'post',
				datatype : 'json',
				success : function (datos) {
					if (datos.success) {
						$("#codigoPagoRegistrado").val(datos.pagoId);
						$("#msgPopupPago").attr("class", "alert alert-success").html("La forma de pago se ha registrado correctamente.").fadeIn("slow");
					} else {
						$("#codigoPagoRegistrado").val("");
						$("#msgPopupPago").attr("class", "alert alert-danger").html(datos.error).fadeIn("slow");
					}
					$("#save-pagoContado").removeAttr("disabled");
					$("#save-pagoDebitoBanco").removeAttr("disabled");
					$("#save-pagoDebitoTarjeta").removeAttr("disabled");
					$("#save-pagoCuotas").removeAttr("disabled");
					
				}
			});
		}
	}
}
