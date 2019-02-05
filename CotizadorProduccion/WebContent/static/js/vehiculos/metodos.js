/**
 *
 */
var tipoObjeto = document.getElementById("tipoObjetoMetodos").getAttribute("tipoObjetoValor");
var tarificador = getParameterByName("v"); // Version Tarificador

function verificarPuntosVenta() {
	var puntoVenta = $("#punto_venta").val();
	if(puntoVenta.length >0){
	$.ajax({
		url : '../PuntoVentaController',
		data : {
			"tipoConsulta" : "verificacionPuntoVenta",
			"tipoObjeto" : tipoObjeto,
			"puntoVentaId" : puntoVenta,

		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			if (data.punto_venta.agente_id == null || data.punto_venta.agente_id == "") {
				$('#agentes').prop("disabled", false);
				if($("#agentes").attr("type")!="select")
					$("#agentes option:first").attr('selected', 'selected');
			} else {
				if($("#agentes").attr("type")=="select")
					$('#agentes').select2('val', data.punto_venta.agente_id);
				else
					$('#agentes option[value=' + data.punto_venta.agente_id + ']').attr('selected', 'selected');
				$('#agentes').prop("disabled", true);
				obtenerAgenteComision("","");
			}									
		}
	});
	}
}

/*
 * METODO QUE OBTIENE LA COMISION DEL AGENTE QUE
 * ESTA REGISTRADA EN EL CONTRATO DE AGENCIAMIENTO
 */
function obtenerAgenteComision(valorComision,seleccionadaAgenteAcuerdo) {
	var agenteId = $("#agentes").val();
	var Comision = "";
	if (valorComision != undefined)
		Comision = valorComision;

	if (agenteId == '') {
		$("#porc_comision").val("");
	} else {
		// Consultar la comision del agente
		$.ajax({
			url : '../AgenteController',
			data : {
				"tipoConsulta" : "ObtenerAgenteId",
				"agenteId" : agenteId
			},
			type : 'post',
			datatype : 'json',
			success : function (data) {
				if (data.comisionVariable) {
					$("#porc_comision").fadeOut();
					$("#porc_comision_cb").fadeIn();
					$("#porc_comision_cb").html('');
					var options = '<option value="">Seleccione una comision</option>';
					if (data.comision1 != 0 && data.comision1 != "0")
						options += '<option value=' + data.comision1 + '>' + data.comision1 + '</option>';
					if (data.comision2 != 0 && data.comision2 != "0")
						options += '<option value=' + data.comision2 + '>' + data.comision2 + '</option>';
					if (data.comision3 != 0 && data.comision3 != "0")
						options += '<option value=' + data.comision3 + '>' + data.comision3 + '</option>';
					$("#porc_comision_cb").html(options);
					if (Comision.length != 0)
						$("#porc_comision_cb").val(Comision);

				} else {
					if (Comision.length != 0)
						$("#porc_comision").val(Comision);
					else
						$("#porc_comision").val(data.comision);

					$("#porc_comision").prop('readonly', true);
					$("#porc_comision_cb").fadeOut();
					$("#porc_comision").fadeIn();
				}
				
				// ocultar por defecto los acuerdos
				$(".acuerdos" ).each(function() {
					  $(this).hide();
				});
				
				// Consultar acuerdos agentes							
				$.ajax({
					url : '../AgenteController',
					data : {
						"tipoConsulta" : "ObtenerAgenteAcuerdo",
						"agenteId" : agenteId
					},
					type : 'post',
					datatype : 'json',
					success : function (datos) {
						$("#acuerdosListado").html('');
						if(datos.numAcuerdoAgentes >0 )
						{
							var options = '<option value="">Seleccione un acuerdo</option>';
							for (var i = 0; i < datos.acuerdoAgentes.length; i++)
								options += '<option value="' + datos.acuerdoAgentes[i].id + '">' + datos.acuerdoAgentes[i].nombre + '</option>';							
							
							$(".acuerdos" ).each(function() {
								  $(this).show();
							});
							
							if(seleccionadaAgenteAcuerdo!='')
								$("#acuerdosListado").html(options).val(seleccionadaAgenteAcuerdo).trigger('change');
							else
								$("#acuerdosListado").html(options);														
						}
					}
				});								
			}
		});
	}
}

/*
 * METODO QUE MUESTRA O ESCONDE LOS CAMPOS DEL FORMULARIO DE LA UPLA
 * DEPENDIENDO DEL TIPO DE IDENTIFICACION QUE SELECCIONE EL USUARIO
 */
function mostrarTipoIdentificacion() {
	var tipoIdentificacionId = $("#tipo_identificacion_principal").val();
	if (tipoIdentificacionId == '' || tipoIdentificacionId == '1' || tipoIdentificacionId == '2' || tipoIdentificacionId == '3') {
		$("#nombre_completo").val("");
	} else {	
		$("#nombres").val("");
		$("#apellidos").val("");
	}

	if (tipoIdentificacionId == '' || tipoIdentificacionId == '1' || tipoIdentificacionId == '3' || tipoIdentificacionId == '4') {
		$("#identificacion").attr("onkeypress", "validarKeyPress(event,/[0-9]/);");

	} else {
		$("#identificacion").attr("onkeypress", "validarSoloLetrasNumeros(event);");
	}
}

/*
 * MUESTRA LOS CAMPOS EN EL FORMULARIO DE LA UPLA
 */
function mostrarAdicionales() {
	var opcion = $('#tipo_identificacion_principal').val();

	if (opcion == 1 || opcion == 2 || opcion == 3) {
		$("#filaNatural").show();
		$("#filaJuridica").hide();
		$('#datosAdicionalesNatural').show();
		$('#datosAdicionalesJuridica').hide();
		if (!cargadoDatosUPLA)
			cargarDatosUPLANatural({
				"" : ""
			});
	} else if (opcion == 4) {
		$("#filaNatural").hide();
		$("#filaJuridica").show();
		$('#datosAdicionalesJuridica').show();
		$('#datosAdicionalesNatural').hide();
		if (!cargadoDatosUPLA)
			cargarDatosUPLAJuridica({
				"" : ""
			});
	} else {
		$('#datosAdicionalesNatural').hide();
		$('#datosAdicionalesJuridica').hide();
	}
}

function cargarDatosUPLANatural(datosNatural) {

	var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	options += '<option value="0">&nbsp;&nbsp;NO </option>';
	options += '<option value="1">&nbsp;&nbsp;SI </option>';

	var optionsZona = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	optionsZona += '<option value="U">&nbsp;&nbsp;Urbana </option>';
	optionsZona += '<option value="R">&nbsp;&nbsp;Rural </option>';

	var tipoActividad = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	tipoActividad += '<option value="Privado">Empleado Privado</option>';
	tipoActividad += '<option value="Publico">Empleado PÃƒÂºblico</option>';
	tipoActividad += '<option value="Independiente">Independiente</option>';
	tipoActividad += '<option value="Jubilado">Jubilado</option>';

	var genero = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	genero += '<option value="M">Masculino</option>';
	genero += '<option value="F">Femenino</option>';

	if (datosNatural.lugarNacimiento != null)
		$("#lugar_nacimiento_natural").val(datosNatural.lugarNacimiento);
	else
		$("#lugar_nacimiento_natural").val("");

	if (datosNatural.fechaNacimiento != null) {
		var dia = datosNatural.fechaNacimiento.date < 10 ? '0' + datosNatural.fechaNacimiento.date : datosNatural.fechaNacimiento.date;
		var mes = datosNatural.fechaNacimiento.month < 9 ? "0" + (datosNatural.fechaNacimiento.month + 1) : (datosNatural.fechaNacimiento.month + 1);
		var anio = (1900 + datosNatural.fechaNacimiento.year);
		var aux = '' + anio + '-' + mes + '-' + dia;

		$("#fecha_nacimiento_natural").val(aux);
	} else
		$("#fecha_nacimiento_natural").val("");

	if (datosNatural.zonaDireccionCliente != null)
		$("#zona_direccion_natural").html(optionsZona).val(datosNatural.zonaDireccionCliente).attr('required', 'required').trigger('change');
	else
		$("#zona_direccion_natural").html(optionsZona).val("").attr('required', 'required');

	if (datosNatural.provinciaDireccionCliente != null)
		cargarProvincias(datosNatural.provinciaDireccionCliente, "direccion_cliente_natural");
	else
		cargarProvincias("", "direccion_cliente_natural");

	if (datosNatural.zonaDireccionCliente == "R") {
		if (datosNatural.cantonDireccionCliente != null) {
			if (datosNatural.provinciaDireccionCliente != null)
				cargarCantones(datosNatural.cantonDireccionCliente, datosNatural.provinciaDireccionCliente, "direccion_cliente_natural");
		} else {
			if (datosNatural.provinciaDireccionCliente != null)
				cargarCantones("", datosNatural.provinciaDireccionCliente, "direccion_cliente_natural");
		}

		if (datosNatural.parroquiaDireccionCliente != null) {
			if (datosNatural.cantonDireccionCliente != null)
				cargarParroquias(datosNatural.parroquiaDireccionCliente, datosNatural.cantonDireccionCliente, "direccion_cliente_natural");
		} else {
			if (datosNatural.cantonDireccionCliente != null)
				cargarParroquias("", datosNatural.cantonDireccionCliente, "direccion_cliente_natural");
		}

	} else {
		if (datosNatural.ciudadDireccionCliente != null) {
			if (datosNatural.provinciaDireccionCliente != null)
				cargarCiudades(datosNatural.ciudadDireccionCliente, datosNatural.provinciaDireccionCliente, "direccion_cliente_natural");
		} else {
			if (datosNatural.provinciaDireccionCliente != null)
				cargarCiudades("", datosNatural.provinciaDireccionCliente, "direccion_cliente_natural");
		}
	}

	if (datosNatural.callePrincipalCliente != null)
		$("#calle_principal_direccion_natural").val(datosNatural.callePrincipalCliente);
	else
		$("#calle_principal_direccion_natural").val("");

	if (datosNatural.numeroDireccionCliente != null)
		$("#numero_direccion_natural").val(datosNatural.numeroDireccionCliente);
	else
		$("#numero_direccion_natural").val("");

	if (datosNatural.calleSecundariaCliente != null)
		$("#calle_secundaria_direccion_natural").val(datosNatural.calleSecundariaCliente);
	else
		$("#calle_secundaria_direccion_natural").val("");

	if (datosNatural.referenciaDireccionCliente != null)
		$("#referencia_direccion_natural").val(datosNatural.referenciaDireccionCliente);
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

	if (datosNatural.generoCliente != null)
		$("#genero_cliente_natural").html(genero).val(datosNatural.generoCliente);
	else
		$("#genero_cliente_natural").html(genero).val("");

	if (datosNatural.mail != null)
		$("#mail_cliente_natural").val(datosNatural.mail);
	else
		$("#mail_cliente_natural").val("");

	//actividad persona natural

	if (datosNatural.actividadCliente != null)
		cargarActividades(datosNatural.actividadCliente, "cliente_natural");
	else
		cargarActividades("", "cliente_natural");

	if (datosNatural.tipoActividadCliente != null)
		$("#tipo_actividad_cliente_natural").html(tipoActividad).val(datosNatural.tipoActividadCliente);
	else
		$("#tipo_actividad_cliente_natural").html(tipoActividad).val("");

	if (datosNatural.cargoOcupaCliente != null)
		$("#cargo_ocupa_cliente_natural").val(datosNatural.cargoOcupaCliente);
	else
		$("#cargo_ocupa_cliente_natural").val("");

	if (datosNatural.tipoRamoContrata != null)
		cargarRamos(datosNatural.tipoRamoContrata, "contrata_cliente_natural");
	else
		cargarRamos("", "contrata_cliente_natural");

	if (datosNatural.expuestoCliente != null)
		$("#expuesto_cliente_natural").html(options).val(datosNatural.expuesto ? 1 : 0);
	else
		$("#expuesto_cliente_natural").html(options).val("");

	if (datosNatural.cargoExpuestoCliente != null)
		$("#cargo_expuesto_cliente_natural").val(datosNatural.cargoExpuesta);
	else
		$("#cargo_expuesto_cliente_natural").val("");

	if (datosNatural.expuestoFamiliar != null)
		$("#expuesto_familiar_natural").html(options).val(datosNatural.expuestoFamiliar ? 1 : 0);
	else
		$("#expuesto_familiar_natural").html(options).val("");

	if (datosNatural.parentescoExpuestoFamiliar != null)
		$("#parentesco_expuesto_familiar_natural").val(datosNatural.parentescoExpuestoFamiliar);
	else
		$("#parentesco_expuesto_familiar_natural").val("");

	if (datosNatural.cargoExpuestoFamiliar != null)
		$("#cargo_expuesto_familiar_natural").val(datosNatural.cargoExpuestoFamiliar);
	else
		$("#cargo_expuesto_familiar_natural").val("");

	if (datosNatural.apellidoPaternoConyuge != null)
		$("#apellido_paterno_conyuge_natural").val(datosNatural.apellidoPaternoConyuge);
	else
		$("#apellido_paterno_conyuge_natural").val("");

	if (datosNatural.apellidoMaternoConyuge != null)
		$("#apellido_materno_conyuge_natural").val(datosNatural.apellidoMaternoConyuge);
	else
		$("#apellido_materno_conyuge_natural").val("");

	if (datosNatural.nombreConyuge != null)
		$("#nombre_conyuge_natural").val(datosNatural.nombreConyuge);
	else
		$("#nombre_conyuge_natural").val("");

	if (datosNatural.tipoIdentificacionConyuge != null)
		cargarTiposIdentificacion(datosNatural.tipoIdentificacionConyuge, "conyuge_natural", true);
	else
		cargarTiposIdentificacion("", "conyuge_natural", false);

	if (datosNatural.identificacionConyuge != null)
		$("#identificacion_conyuge_natural").val(datosNatural.identificacionConyuge);
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

	if (datosNatural.esAsegurado != null)
		$("#es_asegurado_natural").html(options).val(datosNatural.esAsegurado ? 1 : 0);
	else
		$("#es_asegurado_natural").html(options).val("");

	if (datosNatural.esBeneficiario != null)
		$("#es_beneficiario_natural").html(options).val(datosNatural.esBeneficiario ? 1 : 0);
	else
		$("#es_beneficiario_natural").html(options).val("");

	if (datosNatural.tipoIdentificacionAsegurado != null)
		cargarTiposIdentificacion(datosNatural.tipoIdentificacionAsegurado, "asegurado_natural", true);
	else
		cargarTiposIdentificacion("", "asegurado_natural", false);

	if (datosNatural.identificacionAsegurado != null)
		$("#identificacion_asegurado_natural").val(datosNatural.identificacionAsegurado);
	else
		$("#identificacion_asegurado_natural").val("");

	if (datosNatural.nombreCompletoAsegurado != null)
		$("#nombre_asegurado_natural").val(datosNatural.nombreCompletoAsegurado);
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

	//beneficiario
	if (datosNatural.tipoIdentificacionBeneficiario != null)
		cargarTiposIdentificacion(datosNatural.tipoIdentificacionBeneficiario, "beneficiario_natural", true);
	else
		cargarTiposIdentificacion("", "beneficiario_natural", false);

	if (datosNatural.identificacionBeneficiario != null)
		$("#identificacion_beneficiario_natural").val(datosNatural.identificacionBeneficiario);
	else
		$("#identificacion_beneficiario_natural").val("");

	if (datosNatural.nombreCompletoBeneficiario != null)
		$("#nombre_beneficiario_natural").val(datosNatural.nombreCompletoBeneficiario);
	else
		$("#nombre_beneficiario_natural").val("");

	if (datosNatural.direccionBeneficiario != null)
		$("#direccion_beneficiario_natural").val(datosNatural.direccionBeneficiario);
	else
		$("#direccion_beneficiario_natural").val("");

	if (datosNatural.telefonoBeneficiario != null)
		$("#telefono_beneficiario_natural").val(datosNatural.telefonoBeneficiario);
	else
		$("#telefono_beneficiario_natural").val("");

	if (datosNatural.celularBeneficiario != null)
		$("#celular_beneficiario_natural").val(datosNatural.celularBeneficiario);
	else
		$("#celular_beneficiario_natural").val("");

	if (datosNatural.relacionBeneficiario != null)
		$("#relacion_beneficiario_natural").val(datosNatural.relacionBeneficiario);
	else
		$("#relacion_beneficiario_natural").val("");

	cargadoDatosUPLA = true;

}

/*
 * METODO QUE CONSULTA LOS DATOS DE LA ENTIDAD EN BASE AL DOCUMENTO DE
 * IDENTIFICACIÃƒâ€œN Y MUESTRA LOS DATOS EN EL FORMULARIO CORRESPONDIENTE
 */
function cargarPorIdentificacion(formulario, valor) {
	var identificacion = valor;
	if ((identificacion.length >= 10 && $("#tipo_identificacion_principal").val() != 2) || (identificacion.length >= 5 && ($("#tipo_identificacion_principal").val() == 2 || $("#tipo_identificacion_principal").val() == ""))) {
		$(".loading_identificacion").fadeIn();
		var entidad = "";

		if (formulario == "datosClienteInicio")
			cargarTablaPorIdentificacion(identificacion);

		$.ajax({
			url : '../EntidadController',
			data : {
				"identificacion" : identificacion,
				"tipoConsulta" : "cargarPorIdentificacionEnsurance"
			},
			type : 'POST',
			datatype : 'json',
			success : function (data) {
				if(data.success){
				var entidad = data.entidad;
				if(entidad != undefined){
					if (entidad.entidadIdEnsurance != "") {
						if (formulario == "datosClienteInicio") {
							$("#codigoEntidadEnsurance").val(entidad.entidadIdEnsurance);
							$("#codigoClienteEnsurance").val(entidad.clienteIdEnsurance);
							$("#nombres").val(entidad.nombre);
							$("#apellidos").val(entidad.apellido);
							if(entidad.tipoIdentificacion=="4"){
								$("#nombre_completo").val(entidad.nombreCompleto);
							}else{
								$("#nombre_completo").val(entidad.apellido + entidad.nombre);	
							}							
							$("#tipo_identificacion_principal").val(entidad.tipoIdentificacion).trigger("change");
							if (data.datosFactura != null) {
								cargarDireccionFactura("solicitante", data.datosFactura);
							}
						}
	
						if (formulario == "formasPagoDebitoBancario") {
							if(entidad.tipoIdentificacion != "4"){								
								$("#filaNaturalDebitos").show();
								$("#filaJuridicaDebitos").hide();
								$("#nombresTitularDebitos").val(entidad.nombre);
								$("#apellidosTitularDebitos").val(entidad.apellido);
								$("#nombreCompletoTitularDebitos").val("");
							}
							else{
								$("#filaNaturalDebitos").hide();
								$("#filaJuridicaDebitos").show();
								$("#nombresTitularDebitos").val("");
								$("#apellidosTitularDebitos").val("");
								$("#nombreCompletoTitularDebitos").val(entidad.nombre + " " + entidad.apellido);
							}							
							$("#bancoTipoIdentificacion").val(entidad.tipoIdentificacion);
						}
	
						if (formulario == "formasPagoDebitoTarjetas") {						
							
							if(entidad.tipoIdentificacion != "4"){								
								$("#filaNaturalTarjetas").show();
								$("#filaJuridicaTarjetas").hide();
								$("#nombresTitularTarjetas").val(entidad.nombre);
								$("#apellidosTitularTarjetas").val(entidad.apellido);
								$("#nombreCompletoTitularTarjetas").val("");
							}
							else{
								$("#filaNaturalTarjetas").hide();
								$("#filaJuridicaTarjetas").show();
								$("#nombresTitularTarjetas").val("");
								$("#apellidosTitularTarjetas").val("");
								$("#nombreCompletoTitularTarjetas").val(entidad.nombreCompleto);
							}														
							$("#tarjetaTipoIdentificacion").val(entidad.tipoIdentificacion);
						}
	
						if (formulario == "datosAsegurado") {
							$("#nombres_asegurado").val(entidad.nombre);
							$("#apellidos_asegurado").val(entidad.apellido);
							$("#nombre_completo_asegurado").val(entidad.nombreCompleto);
							cargarTiposIdentificacion(entidad.tipoIdentificacion, "asegurado", true);
							if (data.datosFactura != null) {
								cargarDireccionFactura("asegurado", data.datosFactura);
							}
						}
					} else {
						if (data.datosFactura == null || data.datosFactura.callePrincipal == null) {
							if (formulario == "datosClienteInicio") {
								$("#nombres").val("");
								$("#apellidos").val("");
								$("#nombre_completo").val("");
							}
							if (formulario == "datosAsegurado") {
								$("#nombres_asegurado").val("");
								$("#apellidos_asegurado").val("");
								$("#nombre_completo_asegurado").val();
								cargarTiposIdentificacion("asegurado", true);
								cargarDireccionFactura("asegurado");
							}
						}
					}
			}
				validarEntidadJr(identificacion, formulario);
				$(".loading_identificacion").fadeOut();
			}
				else{
					alert(data.error);
					$(".loading_identificacion").fadeOut();
					if (formulario == "datosClienteInicio")
						$("#identificacion").val("");
					if (formulario == "datosAsegurado") 
						$("#identificacion_asegurado").val("");
					
				}
			},
			error : function (xhr, ajaxOptions, thrownError) {
				$(".loading_identificacion").fadeOut();
			}
		});
	}
}

//Metodo para crear una cotizacion cerrados livianos
function crearCotizacion() {

	var pppv = $('#punto_venta').find(":selected").attr('pxpv');
	//alert(pppv);
	$.ajax({
		url : "../CotizacionController",
		data : {
			"tipoConsulta" : "crear",
			"producto" : "productoVehiculo",
			"grupoProductoId" : $("#grupo_productos").val(),
			"puntoVentaId" : $("#punto_venta").val(),
			"vigenciaPoliza" : $("#vigencia").val(),
			"tipoIdentificacion" : $("#tipo_identificacion_principal").val(),
			"productoXPuntoDeVenta" : pppv,
			"identificacion" : $("#identificacion").val(),
			"nombres" : $("#nombres").val(),
			"apellidos" : $("#apellidos").val(),
			"nombreCompleto" : $("#nombre_completo").val(),
			"agenteId" : $("#agentes").val(),
			"codigoEntidadEnsurance" : $("#codigoEntidadEnsurance").val(),
			"codigoClienteEnsurance" : $("#codigoClienteEnsurance").val(),
			"cotizacionId" : $("#cotizacion_id").text(),
			"porcentajeComisionAgente" : $("#porc_comision").val(),
			"tipoObjeto" : tipoObjeto,
			"grupoPorProductoId" : $("#productos").val(),
			"tasaProductoId" : $("#tasas").val(),
			"tasaProductoValor" : $("#tasa").val(),
			"fechaCompra" : $("#txtfechaCompra").val(),
			"diasExtras" : $("#txtDiasExtras").val(),
			"mesPago" : $("#mes_pago").val(),
			"acuerdoId" : $("#acuerdosListado").val()			
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var cotizacionId = data.cotizacionId;
			if (cotizacionId != undefined) {
				$("#cotizacion_id").text(cotizacionId);
				$("#punto_venta").attr("unidadNegocio", data.unidadNegocioId);
				// Validacion poner en la URL el id de la cotizacion
				var valorId = getParameterByName("id");
				if (valorId != null) {
					$.pushVar("id", data.cotizacionId, "", window.location.href);
					//etapa 6
					var urlDescarga = "../CotizacionController?tipoConsulta=descargaPolizaFirmada&id=" + cotizacionId;
					$("#linkDescargaPolizaFirmada").attr("href", urlDescarga);
					$("#subirPolizaFirmada").show();
					urlDescarga = "../CotizacionController?tipoConsulta=descargaAutorizacionDebito&id=" + cotizacionId;
					$("#linkDescargaAutorizacionDebito").attr("href", urlDescarga);
					$("#subirAutorizacionDebito").show();
				}
			} else {
				alert(data.error);
				return false;
			}
		}
	});
}

//Agregar dinamicamente uno o varios vehiculos - mediante el boton agregar vehiculo cerrados livianos
function agregarVehiculo() {
	
	if(QueryString.carga != null && QueryString.carga2 != null){
		$("#agregarVehiculonumero").hide();
		$("#lista_vehiculos").text("En carga masiva no se tiene acceso a esta opcion");
		return;
	}
	
	var mostrarCoberturas = "";
	var mostrarCerrados = "";
	var mostrarTarificador1 = "";
	var mostrarTarificador2 = "";
	var deduciblePerdidaTotalRC ="";
	var tipoVehiculos ="";
	
	if (tipoObjeto != "VHDinamico") {
		mostrarCoberturas = "display:none;";
	}

	if (tipoObjeto == "Pesados") {
		mostrarCerrados = '<tr>' +
			'<td><label><b>Tonelaje: </b></label></td>' +
			'<td><input type="text" name="tonelajenumero" id="tonelajenumero" class="tonelaje datosVehiculo cambioVehiculo"  maxlength="4" onkeypress="return justNumbers(event);"></td>' +
			'<td><label><b>Tipo adquisicion: </b></label></td>' +
			'<td><select name="tipo_adquisicionnumero" id="tipo_adquisicionnumero" class="tipo_adquisicion datosVehiculo cambioVehiculo" ></select></td>' +
			'</tr>';
	}

	if (tipoObjeto == "Livianos" || tipoObjeto == "Publicos" || tipoObjeto == "Motos") {
		mostrarCerrados = '<tr>' +
			'<td><label><b>Tipo adquisicion: </b></label></td>' +
			'<td><select name="tipo_adquisicionnumero" id="tipo_adquisicionnumero" class="tipo_adquisicion datosVehiculo cambioVehiculo" ></select></td>' +
			'<td></td>' +
			'<td></td>' +
			'</tr>';
	}

	if(tarificador=="V1"){
		mostrarTarificador1='<tr>' +
			'<td><label><b>Tipo Veh&iacute;culo:*</b></label></td>' +
			'<td><select name="tipo_vehiculonumero" id="tipo_vehiculonumero" class="tipo_vehiculo datosVehiculo cambioVehiculo" required="required"></select>' +
			'<td><label><b>Sucursal QBE:*</b></label></td>' +
			'<td><select name="sucursalesnumero" id="sucursalesnumero" class="sucursales datosVehiculo cambioVehiculo" required="required"></select></td>' +						
		'</tr>' +
		'<tr>' +
			'<td><label><b>Conductor Edad: </b></label></td>' +
			'<td><select name="conductor_edadnumero" id="conductor_edadnumero" class="edad_conductor datosVehiculo cambioVehiculo"></select></td>' +
			'<td><label><b>Color: </b></label></td>' +
			'<td><input style="width:90%" type="select" id="color_numero" name="color_numero" class="color datosVehiculo cambioVehiculo" required="required" >' +
		'</tr>'+
		'<tr>' +
			'<td><label><b>Tipo Uso:*</b></label></td>' +
			'<td><select name="tipo_usonumero" id="tipo_usonumero" class="tipo_uso datosVehiculo cambioVehiculo" required="required"></select></td>' +
			'<td><label><b>Antiguedad Veh&iacute;culo: </b></label></td>' +
			'<td><select name="antiguedad_vhnumero" id="antiguedad_vhnumero" class="antiguedadvh datosVehiculo cambioVehiculo" ></select></td>' +			
		'</tr>'+
		'<tr>' +
			'<td><label><b>Conductor G&eacute;nero: </b></label></td>' +
			'<td><select name="conductor_generonumero" id="conductor_generonumero" class="genero datosVehiculo cambioVehiculo" ></select></td>' +
			'<td><label><b>Conductor Estado Civil: </b></label></td>' +
			'<td><select name="conductor_estado_civilnumero" id="conductor_estado_civilnumero" class="estado_civil datosVehiculo cambioVehiculo" ></select></td>' +
		'</tr>' +
		'<tr>' +
			'<td><label><b>N&uacute;mero de hijos: </b></label></td>' +
			'<td><select name="valor_hijosnumero" id="valor_hijosnumero" class="valor_hijo datosVehiculo cambioVehiculo" ></select></td>' +
			'<td><label><b>Cero Kil&oacute;metros?: </b></label></td>' +
			'<td><select name="cero_kilometrosnumero" id="cero_kilometrosnumero" class="cerokilometros datosVehiculo cambioVehiculo" onchange="anioKilometraje(numero); ceroKilometros(numero);" ></select></td>' +
		'</tr>' +
		'<tr>' +
			'<td><label><b>Zona: </b></label></td>' +
			'<td><select name="zonanumero" id="zonanumero" class="zona datosVehiculo cambioVehiculo" ></select></td>' +
			'<td><label><b>Guarda en garage: </b></label></td>' +
			'<td><select name="guarda_garagenumero" id="guarda_garagenumero" class="garage datosVehiculo cambioVehiculo" ></select></td>' +
		'</tr>' +
		'<tr>' +
			'<td><label><b>Kil&oacute;metros recorridos por a&ntilde;o: </b></label></td>' +
			'<td><input type="text" id="km_recorridosnumero" onkeypress="return justNumbers(event);" name="km_recorridosnumero" class="km_recorridos datosVehiculo cambioVehiculo" disabled="disabled" maxlength="6"></td>' +
			'<td><label><b>Combustible utilizado: </b></label></td>' +
			'<td><select name="combustiblenumero" id="combustiblenumero" class="combustible datosVehiculo cambioVehiculo" ></select></td>' +
		'</tr>';
		
		deduciblePerdidaTotalRC= '<td class="danoTotal"></td><td class="danoTotal"><b>% Suma Asegurada: </b> <input type="number" onkeypress="return justNumbers(event);" name="porcentaje_suma_aseguradaDT" id="porcentaje_suma_aseguradaDT" style="width:70px;" class="datosVehiculo cambioVehiculo"></td>' +
		'<td class="danoTotal"></td>';
		
	}
	if(tarificador=="V2"){
		mostrarTarificador2='<tr>' +
			'<td><label><b>Tipo Veh&iacute;culo:*</b></label></td>' +
			'<td><select name="tipo_vehiculonumero" id="tipo_vehiculonumero" class="tipo_vehiculo datosVehiculo cambioVehiculo" required="required"></select>' +
			'<td><label><b>Zona Transito:*</b></label></td>' +
			'<td><select name="zona_transitonumero" id="zona_transitonumero" class="zona_transito datosVehiculo cambioVehiculo" required="required"></select></td>' +
	'</tr>' +
	'<tr>' +
		'<td><label><b>Kilometraje Anual: </b></label></td>' +
		'<td><select name="kilometraje_anualnumero" id="kilometraje_anualnumero" class="kilometraje_anual datosVehiculo cambioVehiculo" required="required"></select></td>' +		
		'<td><label><b>Color: </b></label></td>' +
		'<td><input style="width:90%" type="select" id="color_numero" name="color_numero" class="color datosVehiculo cambioVehiculo" required="required" >' +		
	'</tr>'+
	'<tr><td colspan="4" style="text-align:center;background-color:#003da5;color:#fff;border-radius: 25px;"><b>Informaci&oacute;n del Conductor</b></td></tr>'+
	'<tr><td colspan="4" style="height:10px;" ></td></tr>'+
	'<tr class="datosConductornumero">' +
		'<td><label><b>Conductor Identificaci&oacute;n: </b></label><input type="hidden" id="codigo_conductor_ensurancenumero" class="datosVehiculo cambioVehiculo" name="codigo_conductor_ensurancenumero"  /></td>' +
		'<td><input type="text" id="identificacion_conductornumero" class="datosVehiculo cambioVehiculo" name="identificacion_conductornumero" maxlength="10" onchange="cargarIdentificacionConductor(this.value,numero);" onblur="validadorIdentificaciones();" onkeypress="validarKeyPress(event,/[0-9]/);" />'+
		'<img id="cargar_conductornumero" name="cargar_conductornumero" style="display:none;" src="../static/images/ajax-loader.gif" />'+		
		'</td>' +
		'<td><label><b>Conductor Nombres: </b></label></td>' +
		'<td><input type="text" id="nombres_conductornumero" class="datosVehiculo cambioVehiculo" name="nombres_conductornumero" maxlength="20" /></td>' +
	'</tr>' +
	'<tr class="datosConductornumero">' +
		'<td><label><b>Conductor Apellidos: </b></label></td>' +
		'<td><input type="text" id="apellidos_conductornumero" class="datosVehiculo cambioVehiculo" name="apellidos_conductornumero" maxlength="20" /></td>' +		
		'<td></td>' +
		'<td></td>' +
	'</tr>' +
	'<tr>' +
		'<td><label><b>Conductor Edad: </b></label></td>' +
		'<td><select name="conductor_edadnumero" id="conductor_edadnumero" class="edad_conductor datosVehiculo cambioVehiculo" required="required"></select></td>' +
		'<td><label><b>Conductor Estado Civil: </b></label></td>' +
		'<td><select name="conductor_estado_civilnumero" id="conductor_estado_civilnumero" class="estado_civil datosVehiculo cambioVehiculo" required="required"></select></td>' +	
	'</tr>' +
	'<tr>' +		
		'<td><label><b>Conductor G&eacute;nero: </b></label></td>' +
		'<td><select name="conductor_generonumero" id="conductor_generonumero" class="genero datosVehiculo cambioVehiculo" required="required"></select></td>' +
		'<td><label><b>Hijos hasta 25 conduzcan: </b></label></td>' +
		'<td><select name="hijos_conduzcannumero" id="hijos_conduzcannumero" class="hijos_conduzcan datosVehiculo cambioVehiculo"  required="required"></select></td>' +		
	'</tr>';
							
	deduciblePerdidaTotalRC= '<td><b> Deducibles de la Cobertura: </b>&nbsp;&nbsp;&nbsp;&nbsp;<b class="danoTotal">% Suma Asegurada: </b> <input type="number" onkeypress="return justNumbers(event);" name="porcentaje_suma_aseguradaDT" id="porcentaje_suma_aseguradaDT" style="width:70px;border:1px solid #003da5;display:inline;" class="datosVehiculo cambioVehiculo danoTotal" onBlur="validarDanoTotal();">&nbsp;&nbsp;&nbsp;' +		
		'  <b class="deducibleRC">Deducible RC: </b> <input type="number" onkeypress="return justNumbers(event);" name="deducible_rc" id="deducible_rc" style="width:100px;border:1px solid #003da5;display:inline;" class="datosVehiculo cambioVehiculo deducibleRC" onBlur="validarDeducibleRC();"></td>';
	
	tipoVehiculos = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><input type="checkbox" name="vh_comercial_checknumero" id="vh_comercial_checknumero" checked onchange="cambioTipoVehiculo(numero,event,\'' + 'comercial' + '\');" />&nbsp;&nbsp;Veh&iacute;culo Comercial</label>&nbsp;&nbsp;'+
	                '<label><input type="checkbox" name="vh_empresa_checknumero" id="vh_empresa_checknumero"  onchange="cambioTipoVehiculo(numero,event,\'' + 'empresa' + '\');" />&nbsp;&nbsp;Veh&iacute;culo Empresa</label>&nbsp;&nbsp;';
	}
		
	var vehiculo_clone = '<div id="clonar_vehiculonumero" class="row clearfix">' +
		'<div class="col-md-9 column div_vehiculos">' +
		'<div class="panel panel-primary">' +
		'<div class="panel-heading">' +
		'<b>Datos del Veh&iacute;culo ( numero ) a cotizar</b>' +		
		tipoVehiculos+
		'<div class="btn-group pull-right">' +			
			'<a class="btn btn-default glyphicon glyphicon-euro" id="precioReferencialbtnnumero"  onclick = precioReferencial(numero); disabled = "true" style="margin-left:5px;margin-right:5px;" >Precios Referenciales</a>'+
			'<a id="eliminarVehiculonumero" class="btn btn-default btn-sm glyphicon glyphicon-remove datosVehiculo cambioVehiculo  bloquearEmitido" data-toggle="modal" data-target="#myModalnumero" style="margin-left:5px;margin-right:5px;"><b>Eliminar Veh&iacute;culo</b></a>'+
			'<a id="extrasVehiculonumero" class="btn btn-default btn-sm glyphicon glyphicon-wrench datosVehiculo cambioVehiculo bloquearEmitido" data-toggle="modal" data-target="#modalExtrasnumero" style="margin-left:5px;margin-right:5px;"><b>Extras Veh&iacute;culo</b></a>' +
		'</div>' +
		'<div class="clearfix"></div>' +
		'</div>' +
		'<div class="panel-body">' +
			'<input type="hidden" id="vehiculoIdnumero" class="vehiculoCotizador" numVehiculo="numero" name="vehiculoIdnumero" value="">' +
			'<input type="hidden" id="codigoVehiculoEnsurancenumero" name="codigoVehiculoEnsurancenumero">' +
			'<input type="hidden" id="tasaVehiculonumero" class="tasaVehiculo">' +
			'<input type="hidden" id="sumaAcumuladaVehiculonumero" class="sumaAcumulada">' +
			'<input type="hidden" id="sumaAcumuladaExtrasnumero" class="sumaAcumuladaExtras">' +
		'<table>' +
		'<tr>' +
			'<tr><td colspan="4"><div class="alert" id="mensajeVehiculonumero" hidden="hidden"></div></td></tr>'+
			'<td><label><b>Placa:</b></label></td>' +
			'<td><input type="text" onkeypress="return validarSoloLetrasNumeros(event);" style="text-transform:uppercase" class="placa datosVehiculo cambioVehiculo " id="num_placanumero" name="num_placanumero" maxlength="15" onChange="consultarDatosVehiculo(this.value,\'placaDatosVehiculo\',numero);"><div id="loading_numero" hidden="" ><span id="loading-msg">Cargando...</span><img  src="../static/images/ajax-loader.gif" /></div></td>' +
			'<td><label><b>N&uacute;mero de Motor:</b></label></td>' +
			'<td><input type="text" onkeypress="return validarSoloLetrasNumeros(event);" style="text-transform:uppercase" class="motor datosVehiculo cambioVehiculo " id="num_motornumero" name="num_motornumero" maxlength="20" onChange="consultarVigencia(this.value,\'motor\',numero);"></td>' +
		'</tr>' +
		'<tr>' +
			'<td><label><b>N&uacute;mero de Chasis:</b></label></td>' +
			'<td><input type="text" onkeypress="return validarSoloLetrasNumeros(event);" style="text-transform:uppercase" class="chasis datosVehiculo parametroCargarEnsurance cambioVehiculo " id="num_chasisnumero" maxlength="20" name="num_chasisnumero" onChange="consultarDatosVehiculo(this.value,\'chasisDatosVehiculo\',numero);"></td>' +
			'<td><label><b>A&ntilde;o de Fabricaci&oacute;n:*</b></label></td>' +
			'<td><select name="anio_fabricacionnumero" id="anio_fabricacionnumero" class="fabricacion datosVehiculo cambioVehiculo obligatoriosTarifacion" required="required" onchange = "habilitarBoton(numero);calcularAntiguedad(numero);"></select></td>' +
		'</tr>' +
		'<tr>' +
			'<td><label><b>Marca:*</b></label></td>' +
			'<td><input style="width:90%" type="select" id="marca_numero" name="marca_numero" class="marca datosVehiculo cambioVehiculo " required="required" onClick="cargarModelos(\'\',$(\'#marca_\'+numero).select2(\'val\'),numero);" ></td>' +
			'<td><label><b>Modelo:*</b></label></td>' +
			'<td><input style="width:90%" type="select" id="modelo_numero" name="modelo_numero" required="required" class="modelo datosVehiculo cambioVehiculo obligatoriosTarifacion" onchange = "habilitarBoton(numero)";></td>' +
		'</tr>' +		
		'<tr>' +
			'<td><label><b>Dispositivo rastreo:*</b></label></td>' +
			'<td><select name="disposito_rastreonumero" id="disposito_rastreonumero" class="rastreo datosVehiculo cambioVehiculo obligatoriosTarifacion" required="required" onChange="cambiaDispositivoRastreo(numero);"></select></td>' +


			'<td><label><b>Suma Asegurada:*</b></label></td>' +
			'<td><input type="text" id="suma_asegurada_numero" onkeypress="return justNumbers(event);" name="suma_asegurada_numero" class="suma_asegurada datosVehiculo cambioVehiculo obligatoriosTarifacion" required="required" number="number" onchange="activarBotonExtras(numero);activarBotonPaquetes(numero);calcularValorAcumuladoVehiculo(numero);"></td>' +									
		'</tr>' +
		mostrarTarificador1+
		mostrarTarificador2+
		mostrarCerrados +
		'</table>' +
		'<br>' +
		'<center>' +
		'<a id="guardarVehiculonumero" class="btn btn-primary glyphicon glyphicon-plus guardarVehiculo" onclick="guardarVehiculo(numero);" style="display:none;">Guardar Veh&iacute;culo</a></center>' +
		'</div>' +
		'</div>' +

		'<div class="modal" id="myModalnumero" tabindex="-1" role="dialog" aria-hidden="true">' +
		'<div class="modal-dialog">' +
		'<div class="modal-content">' +
		'<div class="modal-header">' +
		'<h4 class="modal-title">Eliminar Veh&iacute;culo ( numero ) de la cotizaci&oacute;n</h4>' +
		'</div>' +
		'<div class="modal-body">' +
		'<a class="btn btn-default" data-dismiss="modal">Cerrar</a>  &nbsp;&nbsp;' +
		'<a class="btn btn-primary" onclick="eliminarVehiculo(numero);">Eliminar</a>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +

		'<div class="modal" id="modalExtrasnumero" tabindex="-1" role="dialog" aria-hidden="true">' +
		'<div class="modal-dialog" style="width:800px;">' +
		'<div class="modal-content">' +
		'<div class="modal-header">' +
		'<h4 class="modal-title">Extras del Veh&iacute;culo ( numero ) de la cotizaci&oacute;n</h4>' +
		'</div>' +
		'<div class="modal-body">' +
		'<div class="alert alert-success" id="msgPopupnumero" style="display:none;">Grabado con exito.</div>' +
		'<label>Llene los datos del Tipo de Extra y despues presione el boton azul</label>' +
		'<input type="hidden" value="0" id="contadorExtrasnumero">' +
		'<input type="hidden" value="0" id="listaExtrasIdsnumero">' +
		'<input type="hidden" value="0" id="listaExtrasDetallesnumero">' +
		'<input type="hidden" value="0" id="listaExtrasValoresnumero">' +
		'<table>' +
		'<tr>' +
			'<th class="sorting"><center>Extras</center></th>' +
			'<th class="sorting"><center>Detalle</center></th>' +
			'<th class="sorting"><center>Valor Extra</center></th>' +
			'<th class="sorting"></th>' +
		'</tr>' +		
		'<tr id="extraFormnumero" value="">' +
		'	<td>' +
		'		<select id="tipoExtraOriginnumero" class="requirednumero">' +
		'			<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>' +
		'		</select>' +
		'	</td>' +
		'	<td><input type="text" id="detalleExtraOriginnumero" class="requirednumero datosVehiculo cambioVehiculo"></td>' +
		'	<td><input type="text" id="valorExtraOriginnumero" class="requirednumero datosVehiculo cambioVehiculo" onkeypress="return justNumbers(event);"></td>' +
		'	<td><button type="button" class="btn btn-primary btn-xs actualizar-btn" id="btnAnadirExtra" onclick="agregarExtras(numero);"> ' +
		'			 <span class="glyphicon glyphicon glyphicon-plus"></span> A&ntilde;adir Extra ' +
		'		</button> ' +
		'	</td>' +
		'</tr>' +
		'</table>' +
		'<table id="extras_numero" style="margin-top:20px;">' +
		'<tr>' +
		'<th class="sorting"><center>Extras</center></th>' +
		'<th class="sorting"><center>Detalle</center></th>' +
		'<th class="sorting"><center>Valor Extra</center></th>' +
		'<th class="sorting"></th>' +
		'</tr>' +
		'</table>' +
		'</div>' +
		'<div class="modal-footer">' +
		'<a class="btn btn-default" data-dismiss="modal">Cerrar</a>  &nbsp;&nbsp;' +
		'<button type="button" class="btn btn-primary  bloquearEmitido" data-dismiss="modal" id="save-record-numero" onclick="grabarExtras(numero);">Guardar cambios</button>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'<div class="col-md-3 column div_coberturas">' +
		'<div class="panel panel-primary">' +
		'<div class="panel-body">' +
		'<div class="row">' +
		'<div class="col-sm-12">' +
		'<label><input type="checkbox" name="coberturaTR_checknumero" id="coberturaTR_checknumero" onclick="habilitarDeshabilitarCobertura(numero,event);" class="coberturanumero datosVehiculo cambioVehiculoClick" required="required" value="Todo Riesgo">&nbsp;&nbsp;Todo Riesgo</label>' +
		'<table id="tabla_deduciblesnumero" class="table-striped table-bordered table-hover" style="border: 1px solid rgb(66, 139, 202); border-radius:5px;" >' +
		'<tbody>' +
		'<tr>' +
		'<td><center><label>% Suma Asegurada:</label></center></td>' +
		'<td><center><input type="text" onkeypress="return justNumbers(event);" name="porcentaje_suma_aseguradanumero" id="porcentaje_suma_aseguradanumero" style="width:100px;" class="datosVehiculo cambioVehiculo obligatoriosTarifacion"  required="required"  onBlur="validarSumaAsegurada();"></center></td>' +
		'</tr>' +
		'<tr>' +
		'<td><center><label>Monto Fijo:</label></center></td>' +
		'<td><center><input type="text" onkeypress="return justNumbers(event);" name="monto_fijonumero" id="monto_fijonumero" style="width:100px;" class="datosVehiculo cambioVehiculo obligatoriosTarifacion" required="required" onBlur="validarMontoFijo();" ></center></td>' +
		'</tr>' +
		'<tr>' +
		'<td><center><label>% Valor Siniestro55:</label></center></td>' +
		'<td><center><input type="text" onkeypress="return justNumbers(event);" name="valor_siniestronumero" id="valor_siniestronumero" style="width:100px;" onBlur="validarPorcentajeSiniestro();" class="datosVehiculo cambioVehiculo obligatoriosTarifacion" required="required"></center></td>' +
		'</tr>' +
		'</tbody>' +
		'</table>' +
		'</div>' +
				
		'<div class="col-sm-12">' +
			'<label style="' + mostrarCoberturas + '"><input type="checkbox" name="coberturaDT_checknumero" id="coberturaDT_checknumero" onclick="habilitarDeshabilitarCobertura(numero,event); cambiaDispositivoRastreo(numero);" class="coberturanumero datosVehiculo cambioVehiculoClick" value="Perdida Total" style="' + mostrarCoberturas + '">&nbsp;&nbsp;Cobertura Perdida Total</label>' +
			'<table id="tabla_deduciblesDTnumero" class="table-striped table-bordered table-hover" style="display:none">' +
			'<tbody>' +
			'<tr>' +
				'<td><center><label>% Suma Asegurada:</label></center></td>' +
				'<td><center><input type="number" onkeypress="return justNumbers(event);" name="porcentaje_suma_aseguradaDTnumero" id="porcentaje_suma_aseguradaDTnumero" style="width:100px;" class="datosVehiculo cambioVehiculo"></center></td>' +
			'</tr>' +		
			'<tr>' +
				'<td><center><label>Deducible RC:</label></center></td>' +
			'<td><center><input type="number" onkeypress="return justNumbers(event);" name="deducible_rcnumero" id="deducible_rcnumero" style="width:100px;" class="datosVehiculo cambioVehiculo"></center></td>' +
			'</tr>' +					
			'</tbody>' +
			'</table>' +
		'</div>' +
		'<div class="col-sm-12">' +
			'<label style="' + mostrarCoberturas + '"><input type="checkbox" name="coberturaRC_checknumero" id="coberturaRC_checknumero" onclick="habilitarDeshabilitarCobertura(numero,event);" class="coberturanumero datosVehiculo cambioVehiculoClick" value="Resp. Civil" style="' + mostrarCoberturas + '">&nbsp;&nbsp;Cobertura Responsabilidad Civil</label>' +			
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'<div class="panel panel-primary">' +
		'<div class="panel-heading fondo_botones"><center>Beneficios - (Paquetes)<center></div>' +
		'<div class="panel-body">' +
		'<div class="row">' +
		'<div class="col-sm-3" id="div_paquete_black_numero">' +
		'<center><label>Black</label></center>' +
		'<center><input type="checkbox" disabled="disabled" name="paquete1_checknumero" id="paquete1_checknumero" class="paquetenumero cambioVehiculoClick" onclick="habilitarDeshabilitarCheck(numero,event);"></center>' +
		'<center><input type="button" disabled="disabled" name="paquete1_buttonnumero" id="paquete1_buttonnumero" value="Detalle" class="btn btn-primary" data-toggle="modal"  onclick="mostrarPaqueteCobertura(numero,\'' + 'black' + '\'); calcularValoresCoberturas(event,numero);" data-target="#modalPaquete1_numero"></center>' +
		'<div class="modal" id="modalPaquete1_numero" tabindex="-1" role="dialog" aria-hidden="true">' +
		'<div class="modal-dialog">' +
		'<div class="modal-content">' +
		'<div class="modal-header">' +
		'<h4 class="modal-title">Paquete Black - Veh&iacute;culo ( numero )</h4>' +
		'</div>' +
		'<div class="modal-body">' +
		'<div id="paquete1_tabla_numero"></div>' +
		'<div class="modal-footer">' +
		'<div id="valor_total_paquete_Black_numero"></div>' +
		'<a class="btn btn-default" data-dismiss="modal">Cerrar</a>  &nbsp;&nbsp;' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'<div class="col-sm-3" id="div_paquete_blue_numero">' +
		'<center><label>Blue</label></center>' +
		'<center><input type="checkbox" disabled="disabled" name="paquete2_checknumero" id="paquete2_checknumero" class="paquetenumero cambioVehiculoClick" onclick="habilitarDeshabilitarCheck(numero,event);"></center>' +
		'<center><input type="button" disabled="disabled" name="paquete2_buttonnumero" id="paquete2_buttonnumero" value="Detalle" class="btn btn-primary" data-toggle="modal" onclick="mostrarPaqueteCobertura(numero,\'' + 'blue' + '\');  calcularValoresCoberturas(event,numero);" data-target="#modalPaquete2_numero"></center>' +
		'<div class="modal" id="modalPaquete2_numero" tabindex="-1" role="dialog" aria-hidden="true">' +
		'<div class="modal-dialog">' +
		'<div class="modal-content">' +
		'<div class="modal-header">' +
		'<h4 class="modal-title">Paquete Blue - Veh&iacute;culo ( numero )</h4>' +
		'</div>' +
		'<div class="modal-body">' +
		'<div id="paquete2_tabla_numero"></div>' +
		'<div class="modal-footer">' +
		'<div id="valor_total_paquete_Blue_numero"></div>' +
		'<a class="btn btn-default" data-dismiss="modal">Cerrar</a>  &nbsp;&nbsp;' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'<div class="col-sm-3" id="div_paquete_gold_numero">' +
		'<center><label>Gold</label></center>' +
		'<center><input type="checkbox" disabled="disabled" name="paquete3_checknumero" id="paquete3_checknumero" class="paquetenumero cambioVehiculoClick" onclick="habilitarDeshabilitarCheck(numero,event);"></center>' +
		'<center><input type="button" disabled="disabled" name="paquete3_buttonnumero" id="paquete3_buttonnumero" value="Detalle" class="btn btn-primary" data-toggle="modal" onclick="mostrarPaqueteCobertura(numero,\'' + 'gold' + '\');  calcularValoresCoberturas(event,numero);" data-target="#modalPaquete3_numero"></center>' +
		'<div class="modal" id="modalPaquete3_numero" tabindex="-1" role="dialog" aria-hidden="true">' +
		'<div class="modal-dialog">' +
		'<div class="modal-content">' +
		'<div class="modal-header">' +
		'<h4 class="modal-title">Paquete Gold - Veh&iacute;culo ( numero )</h4>' +
		'</div>' +
		'<div class="modal-body">' +
		'<div id="paquete3_tabla_numero"></div>' +
		'<div class="modal-footer">' +
		'<div id="valor_total_paquete_Gold_numero"></div>' +
		'<a class="btn btn-default" data-dismiss="modal">Cerrar</a>  &nbsp;&nbsp;' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'<div class="row">' +
		'<div class="col-md-12">' +
		'<center><label>Sin Paquete</label></center>' +
		'<center><input type="checkbox" disabled="disabled" name="paquete5_checknumero" id="paquete5_checknumero" class="paquetenumero cambioVehiculoClick" onclick="habilitarDeshabilitarCheck(numero,event);"></center>' +
		'<center><input type="button" disabled="disabled" name="paquete5_buttonnumero" id="paquete5_buttonnumero" value="Detalle" class="btn btn-primary" data-toggle="modal" onclick="mostrarPaqueteCobertura(numero, \'' + 'sin' + '\');  calcularValoresCoberturas(event,numero);" data-target="#modalPaquete5_numero"></center>' +
		'<div class="modal" id="modalPaquete5_numero" tabindex="-1" role="dialog" aria-hidden="true">' +
		'<div class="modal-dialog">' +
		'<div class="modal-content">' +
		'<div class="modal-header">' +
		'<h4 class="modal-title">Sin Paquete - Veh&iacute;culo ( numero )</h4>' +
		'</div>' +
		'<div class="modal-body">' +
		'<div id="paquete5_tabla_numero"></div>' +
		'<div class="modal-footer">' +
		'<div id="valor_total_paquete_Sin_numero"></div>' +
		'<div id="valor_total_paquete_otros_Sin_numero"></div>' +
		'<a class="btn btn-default" data-dismiss="modal">Cerrar</a>  &nbsp;&nbsp;' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>';

	var unicoCoberturas =
		'<div class="panel-body col-sm-12" style="padding: 15px; border: 0px solid rgb(66, 139, 202);">' +
		'<table style="border: 1px solid rgb(66, 139, 202); border-radius:5px; border-bottom-color: transparent;  text-align:center; " id="" >' +
		'<tbody>' +
		'<tr >' +
			'<td rowspan="2" width="10%" style="color: rgb(255, 255, 255); background: rgb(0, 61, 165);"><center><label>Coberturas</label></center></td>' +
			'<td style="background: rgb(255, 255, 255);"><center><label><input type="checkbox" name="coberturaTR_check" id="coberturaTR_check" onclick="habilitarDeshabilitarCobertura(\'\',event);" class="cobertura datosVehiculo cambioVehiculoClick" value="Todo Riesgo">&nbsp;&nbsp;Todo Riesgo</label></td></center></td>' +


			'<td  style="background: rgb(255, 255, 255);" width="20%"><center><label style="' + mostrarCoberturas + '"><input type="checkbox" name="coberturaDT_check" id="coberturaDT_check" onclick="habilitarDeshabilitarCobertura(\'\',event); cambiaDispositivoRastreo();" class="cobertura datosVehiculo cambioVehiculoClick" value="Perdida Total" style="' + mostrarCoberturas + '">&nbsp;&nbsp; Perdida Total</label></center></td>' +
			'<td style="background: rgb(255, 255, 255);"><center><label style="' + mostrarCoberturas + '"><input type="checkbox" name="coberturaRC_check" id="coberturaRC_check" onclick="habilitarDeshabilitarCobertura(\'\',event);" class="cobertura datosVehiculo cambioVehiculoClick" value="Resp. Civil" style="' + mostrarCoberturas + '">&nbsp;&nbsp; Responsabilidad Civil</label></center></td>' +

		'</tr>' +
		'<tr>' +
			'<td  id="tabla_deducibles" colspan=3 style="background: rgb(255, 255, 255);border: 1px solid rgb(66, 139, 202); border-radius:5px;"><table >' +

			'<td><b> Deducibles de la Cobertura: </b>&nbsp;&nbsp;&nbsp;&nbsp;<b>% Suma Asegurada: </b> <input type="number" onkeypress="return justNumbers(event);" name="porcentaje_suma_asegurada" id="porcentaje_suma_asegurada" style="width:70px;border:1px solid #003da5;display:inline;" class="datosVehiculo cambioVehiculo"  onBlur="validarSumaAsegurada();">&nbsp;&nbsp;&nbsp;&nbsp;' +			

			'	 <b>Monto Fijo: </b> <input type="number" onkeypress="return justNumbers(event);" name="monto_fijo" id="monto_fijo" style="width:70px;border:1px solid #003da5;display:inline;" class="datosVehiculo cambioVehiculo" onBlur="validarMontoFijo();">&nbsp;&nbsp;&nbsp;&nbsp;' +			

			'    <b>% Valor Siniestro: </b> <input type="number" onkeypress="return justNumbers(event);" name="valor_siniestro" id="valor_siniestro" style="width:70px;border:1px solid #003da5;display:inline;" onBlur="validarPorcentajeSiniestro();" class="datosVehiculo cambioVehiculo"></td>' +			
		'</table></td>' +
		'<td id="tabla_deduciblesDT" style="display:none;background: rgb(255, 255, 255);border: 1px solid rgb(66, 139, 202); border-radius:5px;" colspan=3><table>' +
		'<tbody>' +
		'<tr>' +								
			deduciblePerdidaTotalRC+
		'</tr>' +	


		'</tbody>' +
		'</table></td>' +
		'</tr>' +

		'<table id="" style="border: 1px solid rgb(66, 139, 202); border-radius:5px; text-align:center;" ><tbody><tr>' +
		'<td width="10%" style="color: rgb(255, 255, 255); background: rgb(0, 61, 165);"><center><label>Paquetes</label></center></td>' +
		'<td width="18%"  style="background: rgb(255, 255, 255);">' +
		'<div id="div_paquete_black_">' +
		'<label><input type="checkbox" name="paquete1_check" id="paquete1_check" class="paquete cambioVehiculoClick" onclick="habilitarDeshabilitarCheck(\'\',event);">&nbsp;&nbsp; Black</label>' +
		'<input type="button" style="display:none" name="paquete1_button" id="paquete1_button_unico" value="Detalle" class="btn btn-primary" data-toggle="modal"  onclick="mostrarPaqueteCobertura(\'\',\'black\'); calcularValoresCoberturas(event,\'\');" data-target="#modalPaquete1_">' +
		'<div class="modal" id="modalPaquete1_" tabindex="-1" role="dialog" aria-hidden="true" style="text-align:left;">' +
		'<div class="modal-dialog">' +
		'<div class="modal-content">' +
		'<div class="modal-header">' +
		'<h4 class="modal-title">Paquete Black</h4>' +
		'</div>' +
		'<div class="modal-body">' +
		'<div id="paquete1_tabla_"></div>' +
		'<div class="modal-footer">' +
		'<div id="valor_total_paquete_Black_"></div>' +
		'<a class="btn btn-default" data-dismiss="modal">Cerrar</a>  &nbsp;&nbsp;' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</td>' +
		'<td width="18%"  style="background: rgb(255, 255, 255);">' +
		'<div id="div_paquete_blue_">' +
		'<label><input type="checkbox" name="paquete2_check" id="paquete2_check" class="paquete cambioVehiculoClick" onclick="habilitarDeshabilitarCheck(\'\',event);">&nbsp;&nbsp; Blue</label>' +
		'<input type="button" style="display:none" name="paquete2_button" id="paquete2_button_unico" value="Detalle" class="btn btn-primary" data-toggle="modal" onclick="mostrarPaqueteCobertura(\'\',\'blue\');  calcularValoresCoberturas(event,\'\');" data-target="#modalPaquete2_">' +
		'<div class="modal" id="modalPaquete2_" tabindex="-1" role="dialog" aria-hidden="true" style="text-align:left;">' +
		'<div class="modal-dialog">' +
		'<div class="modal-content">' +
		'<div class="modal-header">' +
		'<h4 class="modal-title">Paquete Blue</h4>' +
		'</div>' +
		'<div class="modal-body">' +
		'<div id="paquete2_tabla_"></div>' +
		'<div class="modal-footer">' +
		'<div id="valor_total_paquete_Blue_"></div>' +
		'<a class="btn btn-default" data-dismiss="modal">Cerrar</a>  &nbsp;&nbsp;' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</td>' +
		'<td width="18%" style="background: rgb(255, 255, 255);">' +
		'<div id="div_paquete_gold_">' +
		'<label><input type="checkbox" name="paquete3_check" id="paquete3_check" class="paquete cambioVehiculoClick" onclick="habilitarDeshabilitarCheck(\'\',event);">&nbsp;&nbsp; Gold</label>' +
		'<input type="button" style="display:none" name="paquete3_button" id="paquete3_button_unico" value="Detalle" class="btn btn-primary" data-toggle="modal" onclick="mostrarPaqueteCobertura(\'\',\'gold\');  calcularValoresCoberturas(event,\'\');" data-target="#modalPaquete3_">' +
		'<div class="modal" id="modalPaquete3_" tabindex="-1" role="dialog" aria-hidden="true" style="text-align:left;">' +
		'<div class="modal-dialog">' +
		'<div class="modal-content">' +
		'<div class="modal-header">' +
		'<h4 class="modal-title">Paquete Gold</h4>' +
		'</div>' +
		'<div class="modal-body">' +
		'<div id="paquete3_tabla_"></div>' +
		'<div class="modal-footer">' +
		'<div id="valor_total_paquete_Gold_"></div>' +
		'<a class="btn btn-default" data-dismiss="modal">Cerrar</a>  &nbsp;&nbsp;' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</td>' +
		'<td width="18%" style="background: rgb(255, 255, 255);">' +
		'<div>' +
		'<label><input type="checkbox" name="paquete5_check" id="paquete5_check" class="paquete cambioVehiculoClick" onclick="habilitarDeshabilitarCheck(\'\',event);">&nbsp;&nbsp; Sin Paquete</label>' +
		'<input type="button"style="display:none" name="paquete5_button" id="paquete5_button_unico" value="Detalle" class="btn btn-primary" data-toggle="modal" onclick="mostrarPaqueteCobertura(\'\',\'sin\');  calcularValoresCoberturas(event,\'\');" data-target="#modalPaquete5_">' +
		'<div class="modal" id="modalPaquete5_" tabindex="-1" role="dialog" aria-hidden="true" style="text-align:left;">' +
		'<div class="modal-dialog">' +
		'<div class="modal-content">' +
		'<div class="modal-header">' +
		'<h4 class="modal-title">Sin Paquete</h4>' +
		'</div>' +
		'<div class="modal-body">' +
		'<div id="paquete5_tabla_"></div>' +
		'<div class="modal-footer">' +
		'<div id="valor_total_paquete_Sin_"></div>' +
		'<div id="valor_total_paquete_otros_Sin_"></div>' +
		'<a class="btn btn-default" data-dismiss="modal">Cerrar</a>  &nbsp;&nbsp;' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</div>' +
		'</td>' +
		'</tr></tbody></table>';

	unicoCoberturas = unicoCoberturas.replace(/numero/g, 'unico');

	//se desabilita el campo de km recorridos
	var num_vehiculos = $('#numero_vehiculos').val();
	var numero;
	var grupoPorProductoId = 0;

	if (num_vehiculos == 0) {
		if (tipoObjeto != "VHDinamico")
			grupoPorProductoId = Number($('#codigoProductos').val());
		else
			grupoPorProductoId = 148;

		vehiculo_clone = vehiculo_clone.replace(/numero/g, '1');
		$('#lista_vehiculos').html(vehiculo_clone);
		$("#numero_vehiculos").val("1");
		numero = 1;
		cargaTipoExtra(1);

		if (!casoEspecial) {

			var clase = $(".div_vehiculos").attr("class");
			$(".div_coberturas").attr("style", $(".div_coberturas").attr("style") + ";display:none");
			$(".div_vehiculos").attr("class", (clase.replace("9", "12")));
			if (numero == 1)
				$("#coberturas_unico").html(unicoCoberturas);
		}

		if (casoEspecial)
			cargarTablaCoberturas("", numero, "", "", grupoPorProductoId);

		$("#extrasVehiculo" + numero).attr("disabled", "true");
		$("#km_recorridos" + numero).attr("disabled", "true");

	} else {
		var nuevo_num_vehiculos = parseInt(num_vehiculos) + 1;
		var num_anterior = nuevo_num_vehiculos - 1;
		var marcaModelo = $('#marca_modelo' + num_anterior).val();
		var sucursal = $('#sucursales' + num_anterior).val();
		var anoFabricacion = $('#anio_fabricacion' + num_anterior).val();
		var sumaAsegurada = $('#suma_asegurada_' + num_anterior).val();
		var dispositivoRastreo = $('#disposito_rastreo' + num_anterior).val();
		var ceroKilometros = $('#cero_kilometros' + num_anterior).val();
		if (marcaModelo == "" || sucursal == "" || anoFabricacion == "" || sumaAsegurada == "" || dispositivoRastreo == "") {
			return $("#wizard").valid();
		} else {
			vehiculo_clone = vehiculo_clone.replace(/numero/g, nuevo_num_vehiculos);
			$('#clonar_vehiculo' + num_anterior).before(vehiculo_clone);
			$("#extrasVehiculo" + numero).attr("disabled", "true");
			$("#km_recorridos" + numero).attr("disabled", "true");

			// agregamos los valores de los combos dinamicamente
			if (nuevo_num_vehiculos > 1) {
				cargaInicial(nuevo_num_vehiculos);
				cargarDatosVehiculo(nuevo_num_vehiculos);
			}
			$("#numero_vehiculos").val(nuevo_num_vehiculos);
			numero = nuevo_num_vehiculos;
			cargaTipoExtra(nuevo_num_vehiculos);
			cargarTipoVehiculo("", nuevo_num_vehiculos);
			cargarTipoUso("", nuevo_num_vehiculos);
			if (casoEspecial)
				cargarTablaCoberturas("", nuevo_num_vehiculos, "", "", grupoPorProductoId);
		}

		if (!casoEspecial) {
			var clase = $(".div_vehiculos").attr("class");
			$(".div_coberturas").hide();
			$(".div_vehiculos").attr("class", (clase.replace("9", "12")));
		}

	}
	$(".cambioVehiculo").change(function () {
		editoVehiculo = true;
	});

	$(".cambioVehiculoClick").click(function () {
		editoVehiculo = true;
	});

	$(".cambioVehiculo").on('select2-blur', function (e) {
		editoVehiculo = true;

	});

	if ($("#estado_cotizacion").val() == "Emitido")
		bloquearEmitido();
}


/*
 * CALCULA LA ANTIGÃƒÅ“EDAD DEL VEHICULO DE ACUERDO AL AÃƒâ€˜O DE FABRICACION
 */
function calcularAntiguedad(numero) {
	var anio = $("#anio_fabricacion" + numero).val();
	var anioActual = new Date().getFullYear();
	if (anio != "" && anio != null) {

		var valor = anioActual - anio;
		if (valor == -1) {
			$("#antiguedad_vh" + numero).val(0).attr('disabled', 'disabled');
		} else
			$("#antiguedad_vh" + numero).val(valor).attr('disabled', 'disabled');
		if (valor > 12 && tipoObjeto == "VHDinamico") {
			$("#coberturaTR_check").prop("checked", false).attr('disabled', 'disabled');
			if (!$("#coberturaDT_check").is(':checked'))
				$("#coberturaDT_check").trigger('click');
			$("#coberturaDT_check").attr('disabled', 'disabled');
			$('#tabla_deducibles').fadeOut();
		}
		if (valor <= 12 && tipoObjeto == "VHDinamico") {
			$("#coberturaTR_check").removeAttr('disabled');
			$("#coberturaDT_check").removeAttr('disabled');
		}
	}

	if ($("#estado_cotizacion").val() == "Emitido")
		bloquearEmitido();
}

/*
 * DESABILITA EL CAMPO DE KILOMETROS RECORRIDOS CUANDO UN AUTO ES CERO KILOMETROS
 */
function ceroKilometros(numero) {
	var esCeroKilometros = $("#cero_kilometros" + numero).val();
	if (esCeroKilometros == 1) {
		$("#km_recorridos" + numero).attr('disabled', 'disabled').removeAttr('required');
	}
	if (esCeroKilometros == 0) {
		$("#km_recorridos" + numero).removeAttr('disabled'); //.attr('required','required');
	}

}

/*
 * METODO QUE HABILITA Y DESHABILITA LAS COBERTURAS
 */
function habilitarDeshabilitarCobertura(valor, event) {
		var target = event.target || event.srcElement;
		var text = $(target).attr("name").split('_')[0].replace('cobertura', '');
		var checked = $(target).is(':checked');

		if (tipoObjeto != "VHDinamico") {
			$("#coberturaTR_check").prop("checked", true);
		}

		if (text == 'TR' && checked) {
			$('#coberturaDT_check').prop('checked', false);
			$('#coberturaRC_check').prop('checked', false);
			if (tipoObjeto == "VHDinamico") {
				$('#tabla_deducibles').fadeIn();
				$(".paquete").removeAttr("disabled");		
			}
		}

		if ((text == 'RC' || text == 'DT') && checked) {
			$('#coberturaTR_check').prop('checked', false);
			if (tipoObjeto == "VHDinamico"){
				$('#tabla_deducibles').fadeOut();
			}				
		}

		// Opciones al seleccionar coberturas
		if ($('#coberturaDT_check').is(':checked') && $('#coberturaRC_check').is(':checked')) {
			$('#tabla_deduciblesDT').fadeIn();
			$('.danoTotal').fadeIn();
			$('.deducibleRC').fadeIn();
		}
		if (!$('#coberturaDT_check').is(':checked') && $('#coberturaRC_check').is(':checked')) {
			$('#tabla_deduciblesDT').fadeIn();
			$('.danoTotal').fadeOut();
			$('.deducibleRC').fadeIn();
			$(".paquete").attr("disabled", "disabled").prop('checked', false);
		}
		if ($('#coberturaDT_check').is(':checked') && !$('#coberturaRC_check').is(':checked')) {
			$('#tabla_deduciblesDT').fadeIn();
			$('.danoTotal').fadeIn();
			$('.deducibleRC').fadeOut();
			$("#paquete1_check").attr("disabled", "disabled").prop('checked', false);
			$("#paquete2_check").attr("disabled", "disabled").prop('checked', false);
			$("#paquete3_check").attr("disabled", "disabled").prop('checked', false);
			$("#paquete5_check").removeAttr("disabled");
		}
		if (!$('#coberturaDT_check').is(':checked') && !$('#coberturaRC_check').is(':checked')) {
			$('#tabla_deduciblesDT').fadeOut();			
		}		
}

/*
 * METODO QUE HABILITA EL BOTON DE PRECIOS REFERENCIALES CUANDO SE HAN LLENADO
 * LOS CAMPOS REQUERIDOS.
 */
function habilitarBoton(numero) {
	var aniofab = $("#anio_fabricacion" + numero).val();
	var f = new Date();
	var anioActual = parseInt(f.getFullYear());
	if (aniofab < anioActual) {
		$("#cero_kilometros" + numero).val("0");
	}

	if ($("#modelo_" + numero).select2('val') != "")
		var modelo = $("#modelo_" + numero).select2('data').text;

	if (modelo != '-1' && aniofab != '-1' && $('#precioReferencialbtn' + numero).attr('disabled') == 'disabled') {
		$('#precioReferencialbtn' + numero).removeAttr("disabled").trigger('click').trigger('click');
	} else if (modelo == '-1' && aniofab == '-1' && $('#precioReferencialbtn' + numero).attr('disabled') != 'disabled') {
		$('#precioReferencialbtn' + numero).attr("disabled", "true");
	}
}

/*
 * METODO PARA ACTIVAR EL BOTON DE EXTRAS CUANDO YA SE HAYA INGRESADO LA
 * SUMA ASEGURADA.
 */
function activarBotonExtras(numero) {
	if ($("#suma_asegurada_" + numero).val() != "") {
		$("#extrasVehiculo" + numero).removeAttr("disabled");
	} else {
		$("#extrasVehiculo" + numero).attr("disabled", "true");
	}
}

/*
 * METODO QUE COMPRUEBA SI UN VEHICULO TIENE DISPOSITIVO DE RASTERO
 */
function cambiaDispositivoRastreo(numero) {
	if (casoEspecial) {
		var dispositivo = $("#disposito_rastreo" + numero).val();
		if (dispositivo == '1') {
			$("#porcentaje_suma_aseguradaDT" + numero).val(5);
			$("#porcentaje_suma_aseguradaDT" + numero).attr('min', 5);
		}
		if (dispositivo == '0') {
			$("#porcentaje_suma_aseguradaDT" + numero).val(15);
			$("#porcentaje_suma_aseguradaDT" + numero).attr('min', 15);
		}
	} else {
		var rastreos = $(".rastreo ");
		var bandera = true;
		$.each(rastreos, function (index, element) {
			var dispositivo = $(element).val();
			if (dispositivo == '1' && bandera) {
				$("#porcentaje_suma_aseguradaDT").val(5);
				$("#porcentaje_suma_aseguradaDT").attr('min', 5);
			}
			if (dispositivo == '0') {
				$("#porcentaje_suma_aseguradaDT").val(15);
				$("#porcentaje_suma_aseguradaDT").attr('min', 15);
				bandera = false;
			}
		});
	}
}

/*
 * METODO DE QUE ACTIVA LOS BOTONES DE LOS PAQUETES EN LA COTIZACION DE VEHICULOS
 */
function activarBotonPaquetes(numero) {
	//evaldez
	//var target = event.target || event.srcElement;
	var valor = $("#suma_asegurada_" + numero).val();
	if (valor > 0) {
		$("#paquete1_button" + numero).removeAttr("disabled");
		$("#paquete2_button" + numero).removeAttr("disabled");
		$("#paquete3_button" + numero).removeAttr("disabled");
		$("#paquete4_button" + numero).removeAttr("disabled");
		$("#paquete5_button" + numero).removeAttr("disabled");
		$("#paquete1_check" + numero).removeAttr("disabled");
		$("#paquete2_check" + numero).removeAttr("disabled");
		$("#paquete3_check" + numero).removeAttr("disabled");
		$("#paquete4_check" + numero).removeAttr("disabled");
		$("#paquete5_check" + numero).removeAttr("disabled");

	} else {
		$("#paquete1_button" + numero).attr("disabled", "disabled");
		$("#paquete2_button" + numero).attr("disabled", "disabled");
		$("#paquete3_button" + numero).attr("disabled", "disabled");
		$("#paquete4_button" + numero).attr("disabled", "disabled");
		$("#paquete5_button" + numero).attr("disabled", "disabled");
		$("#paquete1_check" + numero).attr("disabled", "disabled");
		$("#paquete2_check" + numero).attr("disabled", "disabled");
		$("#paquete3_check" + numero).attr("disabled", "disabled");
		$("#paquete4_check" + numero).attr("disabled", "disabled");
		$("#paquete5_check" + numero).attr("disabled", "disabled");
	}
}

/*
 * METODO PARA AGREGAR EXTRAS A LOS VEHICULOS A COTIZAR
 */
function agregarExtras(numero) {
	$("#errorMsg").remove();
	$("#msgPopup" + numero).hide();
	$(".required" + numero).css("border", "1px solid black");
	var numeroVeces = Number(numero);
	var bandera = "0";
	$.each($(".required" + numero), function (index) {
		if ($(this).val() == "") {
			$(this).css("border", "1px solid red");
			$(this).focus();
			$("#extraForm" + numero).parent().append("<tr id='errorMsg'><td colspan='3' style='color:red;'>Por favor ingrese el campo requerido<td></tr>");
			bandera = "1";
			return false;
		}
	});

	$.each($(".valorExtra" + numero), function () {
		if ($(this).val() == $("#tipoExtraOrigin" + numero).val()) {
			alert("Extra " + $(this).val() + " ya esta seleccionado para su vehiculo");
			bandera = "1";
			return false;
		}
	});

	// Validacion porcentaje extras segun tipo de vehiculo
	var valorExtras = 0;
	if (tipoObjeto == "Publicos" || tipoObjeto == "Livianos" || tipoObjeto == "Motos") {
		valorExtras = 20;
	}
	if (tipoObjeto == "Pesados") {
		valorExtras = 50;
	}
	if (tipoObjeto == "VHDinamico") {
		valorExtras = 15;
	}

	var sumaMaximaExtras = parseFloat($("#suma_asegurada_" + numero).val()) * (valorExtras / 100);
	var valorTotalExtras = $("#valorExtraOrigin" + numero).val();
	$.each($(".valorExtra" + numero), function () {
		if ($(this).val() != "") {
			valorTotalExtras = parseFloat(valorTotalExtras) + parseFloat($(this).parent().next().next().children().val());
		}
	});
	if (parseFloat(valorTotalExtras) > parseFloat(sumaMaximaExtras)) {
		alert("El valor de los extras supera el " + valorExtras + "% del valor de la prima total. Usted puede registrar maximo $" + sumaMaximaExtras + " para este auto.");
		return;
	}

	var numeroExtasAuto = parseInt($("#contadorExtras" + numero).val()) + parseInt(1);

	if (bandera == 0) {
		$("#extras_" + numero).append('<tr>' +
			'<td class="sorting"><input type="text" id="valorTipo_' + numero + '_' + numeroExtasAuto + '" readonly class="valorExtra' + numero + '" value="' + $("#tipoExtraOrigin" + numero).val() + '" source="' + $("#tipoExtraOrigin" + numero + " option:selected").attr("source") + '"></td>' +
			'<td class="sorting"><input type="text" id="valorDetalle_' + numero + '_' + numeroExtasAuto + '" readonly value="' + $("#detalleExtraOrigin" + numero).val() + '"></td>' +
			'<td class="sorting"><input type="text" class="valorExtras_' + numero + '" id="valorValor_' + numero + '_' + numeroExtasAuto + '" readonly value="' + $("#valorExtraOrigin" + numero).val() + '"></td>' +
			'<td class="sorting">' +
			'<button type="button" class="btn btn-danger btn-xs eliminar-extra-btn">' +
			' <span class="glyphicon glyphicon glyphicon-remove"></span> Eliminar' +
			' </button></td>' +
			'</tr>');
		$("#contadorExtras" + numero).val(numeroExtasAuto);
		$("#tipoExtraOrigin" + numero).val('-1');
		$("#detalleExtraOrigin" + numero).val('');
		$("#valorExtraOrigin" + numero).val('');
	}

	$(".eliminar-extra-btn").bind({
		click : function () {
			$(this).parent().parent().remove();
			numeroExtasAuto = parseInt($("#contadorExtras" + numero).val()) - parseInt(1);
			$("#contadorExtras" + numero).val(numeroExtasAuto);
		}
	});

	$("#tipoExtraOrigin" + numero).val('');
	$("#detalleExtraOrigin" + numero).val('');
	$("#valorExtraOrigin" + numero).val('');
}

/*
 * METODO QUE CONSULTA LOS PRECIOS REFERENCIALES Y MUESTRA EN EL COMPONENTE
 * TOOLSTISTER
 */
function precioReferencial(numero) {
	$('#precioReferencialbtn' + numero).tooltipster({
		onlyOne : 'true',
		animation : 'fall',
		delay : 50,
		position : 'bottom',
		theme : 'tooltipster-default',
		trigger : 'click',
		contentAsHTML : 'true',
		multiple : 'true',
		functionBefore : function (origin, continueTooltip) {
			continueTooltip();

			//origin.tooltipster('content', "No hay precios referenciales.")
			if (origin.data('ajax') !== 'cached') {
				$.ajax({
					url : '../CotizacionController',
					data : {
						"modelo" : $("#modelo_" + numero).select2('data') ? $("#modelo_" + numero).select2('data').text : "",
						"marca" : $("#marca_" + numero).select2('data') ? $("#marca_" + numero).select2('data').text : "",
						"aniofab" : $("#anio_fabricacion" + numero).val(),
						"tipoConsulta" : "consultaPrecioReferencial"
					},
					type : 'post',
					datatype : 'json',
					success : function (data) {
						$(".tooltipster-content").empty();
						var listaPrecios = data.listadoPrecioReferencial;
						//Para imprimir el tooltip usando el tooltipster
						arrPrecios = "";
						if (listaPrecios.length > 0) {
							arrPrecios = "<table><tr><th>Marca</th>" +
								"<th>Modelo</th>" +
								"<th>A&ntilde;o</th>" +
								"<th>Precio</th></tr>";
							$.each(listaPrecios, function (index) {
								arrPrecios = arrPrecios + "<tr><td>" + listaPrecios[index].marca + "</td>";
								arrPrecios = arrPrecios + "<td>" + listaPrecios[index].modelo + "</td>";
								arrPrecios = arrPrecios + "<td>" + listaPrecios[index].aniofab + "</td>";
								arrPrecios = arrPrecios + "<td>" + listaPrecios[index].precio + "</td></tr>";
							});
							arrPrecios = arrPrecios + "</table>";
						} else {
							arrPrecios = "No hay precios referenciales.";
						}
						origin.tooltipster('content', arrPrecios);
					}
				});
			}
		},
	});
}

/*
 * METODO QUE HABILITA Y DESHABILITA LOS PAQUETES
 */
function habilitarDeshabilitarCheck(valor, event) {
	var target = event.target || event.srcElement;

	if (valor != "") {
		var numeroVeces = Number(valor);
		if ($(target).is(":checked"))
			$(target).parent().next().children().first().click();

		$('.paquete' + numeroVeces.toString()).not(target).prop('checked', false);
	} else {
		var idBoton = $(target).attr("id").replace("check", "button_unico");
		if ($(target).is(":checked"))
			$("#" + idBoton).click();
		$('.paquete').not(target).prop('checked', false);
	}
}

/*
 * METODO QUE MUESTRA LAS COBERTURAS AGRUPADAS POR PAQUETE
 */
function mostrarPaqueteCobertura(numero, paquete) {
	var numeroVeces = Number(numero);
	var paqueteValor = paquete.toString();

	var coberturaTablaPaqueteBlack = $("#coberturaTablaPaqueteBlack").html().replace(/numero/g, numero);
	var coberturaTablaPaqueteBlue = $("#coberturaTablaPaqueteBlue").html().replace(/numero/g, numero);
	var coberturaTablaPaqueteGold = $("#coberturaTablaPaqueteGold").html().replace(/numero/g, numero);
	var coberturaTablaSinPaquete = $("#coberturaTablaSinPaquete").html().replace(/numero/g, numero);

	if (paqueteValor == "black" && $('#paquete1_tabla_' + numero).html().trim() == "") {
		$('#paquete1_tabla_' + numero).empty().html(coberturaTablaPaqueteBlack);
	}

	if (paqueteValor == "blue" && $('#paquete2_tabla_' + numero).html().trim() == "") {
		$('#paquete2_tabla_' + numero).empty().html(coberturaTablaPaqueteBlue);
	}

	if (paqueteValor == "gold" && $('#paquete3_tabla_' + numero).html().trim() == "") {
		$('#paquete3_tabla_' + numero).empty().html(coberturaTablaPaqueteGold);
	}

	if (paqueteValor == "sin" && $('#paquete5_tabla_' + numero).html().trim() == "") {
		$('#paquete5_tabla_' + numero).empty().html(coberturaTablaSinPaquete);
	}
}

/*
 * METODO QUE CALCULA LA ANTIGUEDAD EN AÃƒâ€˜OS DEL VEHICULO
 * EN BASE AL AÃƒâ€˜O DE FABRICACION
 */
function anioKilometraje(numero) {
	var f = new Date();
	var anioActual = parseInt(f.getFullYear());

	if (parseInt($("#anio_fabricacion" + numero).val()) < anioActual && $("#cero_kilometros" + numero).val() == "1") {
		alert("Los vehiculos del ano " + $("#anio_fabricacion" + numero).val() + " ya no pueden registrarse con kilometraje cero.");
		$("#cero_kilometros" + numero).val("0");
	}
}

/*
 * METODO PARA CALCULAR LOS VALORES DE LAS COBERTURAS
 */
function calcularValoresCoberturas(event, numero) {
	var target = event.target || event.srcElement;
	var tiempoVigencia = Number($("#vigencia").val());
	if (numero != "") {
		var paquete = $(target).parent().parent().children().first().children().first().html();
		if (paquete == "Sin Paquete")
			paquete = "Sin";

		var coberturas = $(".valor_" + paquete + "_Cobertura_tabla_" + Number(numero));
		var suma = 0;
		var sumas = $(".suma_asegurada");
		var totalPaquete = 0;
		var extras = $("#sumaAcumuladaExtras" + numero);
		var valorExtras = 0;

		$(extras).each(function (index, element) {
			valorExtras = Number(valorExtras) + Number($(element).val());
		});

		$(sumas).each(function (index, element) {
			suma = Number(suma) + Number($(element).val());
		});
		suma = Number(suma) + Number(valorExtras);

		var idsAgregadas = [];
		for (var index = coberturas.length / 2; index < coberturas.length; index++) {
			var element = coberturas[index];

			var tasa = $(element).next();
			var tipoTasa = $(tasa).attr("class");
			var valorTasa = $(tasa).val();
			var idCobertura = $(element).attr('id').split('_')[3];

			if (idCobertura == '6348540415022' || idCobertura == '6349173767914') {
				var valorExcesoRC = Number($(element).next().next().children().first().val()) * sumas.length * tiempoVigencia;
				totalPaquete += Number(Math.round(valorExcesoRC * Number(valorTasa))) * sumas.length * tiempoVigencia;
			} else {
				if (tipoTasa == 'porcentual') {
					$(element).html("$" + (tiempoVigencia * Math.round((valorTasa * suma / 100) * 100) / 100));
					totalPaquete += Number(tiempoVigencia * Math.round((valorTasa * suma / 100) * 100) / 100);
				} else {
					$(element).html("$" + (tiempoVigencia * Math.round((valorTasa) * 100) / 100) * sumas.length);
					totalPaquete += Number(tiempoVigencia * Math.round((valorTasa) * 100) / 100) * sumas.length;
				}
			}

		}
		if (paquete != "Sin")
			$("#valor_total_paquete_" + paquete + "_" + numero).html('Valor del Paquete: $' + totalPaquete);
		calcularTotalSinPaquete(numero);
	} else {
		var paquete = $(target).prev().html().split("&nbsp;&nbsp;")[1].trim();
		if (paquete == "Sin Paquete")
			paquete = "Sin";

		var coberturas = $(".valor_" + paquete + "_Cobertura_tabla_");
		var suma = 0;
		var sumas = $(".suma_asegurada");
		var totalPaquete = 0;
		var extras = $(".sumaAcumuladaExtras");
		var valorExtras = 0;

		$(extras).each(function (index, element) {
			valorExtras = Number(valorExtras) + Number($(element).val());
		});

		$(sumas).each(function (index, element) {
			suma = Number(suma) + Number($(element).val());
		});

		suma = Number(suma) + Number(valorExtras);
		var idsAgregadas = [];

		for (var index = coberturas.length / 2; index < coberturas.length; index++) {
			var element = coberturas[index];
			var tasa = $(element).next();
			var tipoTasa = $(tasa).attr("class");
			var valorTasa = $(tasa).val();
			var idCobertura = $(element).attr('id').split('_')[3];

			if (idCobertura == '6348540415022' || idCobertura == '6349173767914') {
				var valorExcesoRC = tiempoVigencia * Number($(element).next().next().children().first().val()) * sumas.length;
				totalPaquete += tiempoVigencia * Number(Math.round(valorExcesoRC * Number(valorTasa))) * sumas.length;
			} else {
				if (tipoTasa == 'porcentual') {
					$(element).html("$" + tiempoVigencia * Math.round((valorTasa * suma / 100) * 100) / 100);
					totalPaquete += tiempoVigencia * Number(Math.round((valorTasa * suma / 100) * 100) / 100);
				} else {
					$(element).html("$" + (tiempoVigencia * Math.round((valorTasa) * 100) / 100) * sumas.length);
					totalPaquete += tiempoVigencia * Number(Math.round((valorTasa) * 100) / 100) * sumas.length;
				}
			}
			
		}
		if (paquete != "Sin")
			$("#valor_total_paquete_" + paquete + "_" + numero).html('Valor del Paquete: $' + totalPaquete);
		calcularTotalSinPaquete(numero);
	}
}

/*
 * CONSULTA LA VIGENCIA DE UN VEHICULO
 */
function consultarVigencia(valor, parametro, numero) {
	$('#num_motor' + numero).removeAttr("disabled");
	$('#num_chasis' + numero).removeAttr("disabled");
	$('#anio_fabricacion' + numero).removeAttr("disabled");
	if (valor.length >= 5) {
		var numeroVeces = Number(numero);
		
		$('#loading_' + numeroVeces.toString()).fadeIn();
		var numeroVeces = Number(numero);
		$.ajax({
			url : '../CotizacionController',
			data : {
				"tipoConsulta" : "consultarVigencia",
				"parametro" : parametro,
				"valor" : valor
			},
			type : 'POST',
			datatype : 'json',
			success : function (data) {
				if (data.success) {
					var today = new Date();
					var vigencia = new Date(data.vigenciaEnsurance);
					var diferencia = (Date.parse(vigencia) - Date.parse(today)) / (1000 * 60 * 60 * 24);
					var dd = vigencia.getDate();
					var mm = vigencia.getMonth() + 1; // January
					// is
					// 0!
					var yyyy = vigencia.getFullYear();

					if (dd < 10) {
						dd = '0' + dd;
					}

					if (mm < 10) {
						mm = '0' + mm;
					}

					/**/
					var hoy = new Date();
					var dia = hoy.getDate();
					var mes = hoy.getMonth() + 1; // January
					// is
					// 0!
					var anio = hoy.getFullYear();

					if (dia < 10) {
						dia = '0' + dia;
					}

					if (mes < 10) {
						mes = '0' + mes;
					}

					var aux = anio + '-' + mes + '-' + dia;

					var fechaescogidaactual = $("#fecha_inicio_vigencia").val();

					if (fechaescogidaactual == ""){
						$("#fecha_inicio_vigencia").val(aux);
						fechaescogidaactual=aux;
					}
					/**/

					var fechaVigenciaPoliza = dd + "/" + mm + "/" + yyyy;
					var fechaSet = yyyy + '-' + mm + '-' + dd;

					solicitarInspeccionArr[numeroVeces] = true;

					if (data.cotizacionId != null && data.cotizacionId != "" && data.cotizacionId != $("#cotizacion_id").text()) {
						alert("El vehiculo se encuentra en la cotizacion #" + data.cotizacionId + ". No se puede cotizar!");
						$("#num_chasis" + numero).val("");
						$("#num_motor" + numero).val("");
						$("#num_placa" + numero).val("");
					}

					if (diferencia <= 90 && diferencia >= 0)
						if (yyyy > Number(fechaescogidaactual.split('-')[0])) {
							alert("El vehiculo ingresado tiene una poliza vigente hasta el " + fechaVigenciaPoliza + " (dd/mm/YYYY).  La vigencia inicial de la nueva poliza sera la fecha de vencimiento de la anterior.");
							$("#fecha_inicio_vigencia").val(fechaSet).attr("disabled", "disabled");
							solicitarInspeccionArr[numeroVeces] = false;
							$("#marca_" + numeroVeces).select2("val", data.marcaEnsurance);
							cargarModelos(data.modeloEnsurance, data.marcaEnsurance, numeroVeces);
							$("#color_" + numeroVeces).select2("val", data.colorEnsurance);
							$("#anio_fabricacion" + numeroVeces).val(data.anioEnsurance);
							if(data.placasEnsurance.trim()!='')
								$("#num_placa" + numeroVeces).val(data.placasEnsurance);
							if(data.motorEnsurance.trim()!='')
								$("#num_motor" + numeroVeces).val(data.motorEnsurance);
							if(data.chasisEnsurance.trim()!='')
								$("#num_chasis" + numeroVeces).val(data.chasisEnsurance);
							actualizarVigenciaDesde();
						} else {
							if (mm > Number(fechaescogidaactual.split('-')[1]) && Number(fechaescogidaactual.split('-')[0]) == yyyy) {
								alert("El vehiculo ingresado tiene una poliza vigente hasta el " + fechaVigenciaPoliza + " (dd/mm/YYYY).  La vigencia inicial de la nueva poliza sera la fecha de vencimiento de la anterior.");
								$("#fecha_inicio_vigencia").val(fechaSet).attr("disabled", "disabled");
								solicitarInspeccionArr[numeroVeces] = false;
								$("#marca_" + numeroVeces).select2("val", data.marcaEnsurance);
								cargarModelos(data.modeloEnsurance, data.marcaEnsurance, numeroVeces);
								$("#color_" + numeroVeces).select2("val", data.colorEnsurance);
								$("#anio_fabricacion" + numeroVeces).val(data.anioEnsurance);
								if(data.placasEnsurance.trim()!='')
									$("#num_placa" + numeroVeces).val(data.placasEnsurance);
								if(data.motorEnsurance.trim()!='')
									$("#num_motor" + numeroVeces).val(data.motorEnsurance);
								if(data.chasisEnsurance.trim()!='')
									$("#num_chasis" + numeroVeces).val(data.chasisEnsurance);
								actualizarVigenciaDesde();
							} else
								if (dd >= Number(fechaescogidaactual.split('-')[2]) && Number(fechaescogidaactual.split('-')[1]) == mm && Number(fechaescogidaactual.split('-')[0]) == yyyy) {
									alert("El vehiculo ingresado tiene una poliza vigente hasta el " + fechaVigenciaPoliza + " (dd/mm/YYYY).  La vigencia inicial de la nueva poliza sera la fecha de vencimiento de la anterior.");
									$("#fecha_inicio_vigencia").val(fechaSet).attr("disabled", "disabled");
									solicitarInspeccionArr[numeroVeces] = false;
									$("#marca_" + numeroVeces).select2("val", data.marcaEnsurance);
									cargarModelos(data.modeloEnsurance, data.marcaEnsurance, numeroVeces);
									$("#color_" + numeroVeces).select2("val", data.colorEnsurance);
									$("#anio_fabricacion" + numeroVeces).val(data.anioEnsurance);
									if(data.placasEnsurance.trim()!='')
										$("#num_placa" + numeroVeces).val(data.placasEnsurance);
									if(data.motorEnsurance.trim()!='')
										$("#num_motor" + numeroVeces).val(data.motorEnsurance);
									if(data.chasisEnsurance.trim()!='')
										$("#num_chasis" + numeroVeces).val(data.chasisEnsurance);
									actualizarVigenciaDesde();

								}
						}

					if ((Date.parse(vigencia)) >= (Date.parse(today)) && diferencia > 90) {

						alert("El vehiculo ingresado tiene una poliza vigente hasta el " + fechaVigenciaPoliza + " (dd/mm/YYYY).  No puede cotizar este vehiculo.");
						$("#num_chasis" + numero).val("");
						$("#num_motor" + numero).val("");
						$("#num_placa" + numero).val("");
						return false;
					} else {
						if(parametro=='placa')
							validarPlaca(valor, numero);
						//if(parametro=='chasis')
							//consultarChasisSRI(event);
					}

				} else {
					alert(data.error);
				}
			}
		});
		$('#loading_' + numeroVeces.toString()).fadeOut(6000);

	}
}

/*
 * METODO QUE GRABA LOS EXTRAS QUE SE AÃƒâ€˜ADEN A LOS VEHICULOS
 */
function grabarExtras(numero) {
	var listaExtrasIds = "";
	var listaExtrasDetalles = "";
	var listaExtrasValores = "";
	var valorTotalExtras = 0;

	$(".valorExtra" + numero).each(function (index, value) {
		if ($(this).val() != "") {
			listaExtrasIds = listaExtrasIds + "," + $(this).attr('source').replace(',',' ');
			listaExtrasDetalles = listaExtrasDetalles + "," + $(this).parent().next().children().val().replace(',',' ');
			listaExtrasValores = listaExtrasValores + "," + $(this).parent().next().next().children().val().replace(',',' ');
			valorTotalExtras = parseFloat(valorTotalExtras) + parseFloat($(this).parent().next().next().children().val());
		}
	});
	$("#listaExtrasIds" + numero).val(listaExtrasIds);
	$("#listaExtrasDetalles" + numero).val(listaExtrasDetalles);
	$("#listaExtrasValores" + numero).val(listaExtrasValores);
	$("#msgPopup" + numero).show();
	calcularValorAcumuladoVehiculo(numero);
}

function calcularTotalSinPaquete(numero) {

	if (numero == null)
		numero = "";
	var coberturas = $(".valor_Sin_Cobertura_tabla_" + numero);
	var valor = 0;
	var valorOtros = 0;
	var coberturaOtros = false;
	for (var i = 0; i < coberturas.length; i++) {
		if ($($(".check_Sin_Cobertura_" + numero)[i]).is(":checked"))
			{
				var str = $(coberturas[i]).attr("id");
				str = str.replace("valor_Sin_Cobertura_","");
			    str = str.replace("_tabla_","");
			    var valorTemp =  Number($(coberturas[i]).html().replace('$', ''));
			    
			    $.ajax({
			    	async:false, 
					url : '../CoberturaController',
					data : {
						"tipoConsulta" : "verificarCoberturaOtros",
						"coberturaId" : str
					},
					type : 'post',
					datatype : 'json',
					success : function (data) {
						coberturaOtros = data.coberturaOtros;
					    if(!coberturaOtros)
					    {
					    	valor += valorTemp;
					    }
					    else
					    {
					    	valorOtros += valorTemp;
					    }
					}
				});
			    
			    
			}
	}
		$("#valor_total_paquete_Sin_" + numero).html("Valor del Paquete: $" + Math.round(valor * 100) / 100);
		$("#valor_total_paquete_otros_Sin_" + numero).html("Valor del Paquete Otros: $"+ Math.round(valorOtros * 100) / 100);
}

/*
 * METODO QUE VALIDA LOS DATOS DEL VEHICULO CON LA INFORMACION DE LA ANT
 * EN BASE A LA PLACA DEL VEHICULO INGRESADA POR EL USUARIO.
 */
function validarPlaca(valor, numero,event) {
	$('#num_motor' + numero).removeAttr("disabled");
	$('#num_chasis' + numero).removeAttr("disabled");
	$('#anio_fabricacion' + numero).removeAttr("disabled");
	if (valor.length >= 5 && valor.length <= 7) {
		var numeroVeces = Number(numero);
		//$('#num_motor' + numeroVeces.toString()).val("");
		//$('#num_chasis' + numeroVeces.toString()).val("");
		//$('#anio_fabricacion' + numeroVeces.toString()).val("");
		$('#loading_' + numeroVeces.toString()).fadeIn();
		var numeroVeces = Number(numero);
		$.ajax({
			url : '../ObjetoVehiculoController',
			data : {
				"tipoConsulta" : "consultarPlaca",
				"placa" : valor,
			},
			type : 'POST',
			datatype : 'json',
			success : function (data) {
				if (data.success) {
					if (data.cotizacionId != null && data.cotizacionId != "" && data.cotizacionId != $("#cotizacion_id").text()) {
						alert("El vehiculo se encuentra en la cotizacion #" + data.cotizacionId + ". No se puede volver a cotizar!");
						$("#num_placa" + numero).val("");
						$("#num_motor" + numero).val("");
						$("#num_chasis" + numero).val("");
						$("#anio_fabricacion" + numero).val("").removeAttr('disabled');
						$('#marca_' + numeroVeces.toString()).select2("val", "");
						$('#color_' + numeroVeces.toString()).select2("val", "");
					}

					if (data.anioFabricacion.length > 2 && data.cotizacionId == "") {

						if (parseInt(data.anioFabricacion) >= parseInt($("#anio_max_seguro").val()) && parseInt(data.anioFabricacion) <= parseInt($('#anio_fabricacion1').children().first().next().val())) {
							$('#codigoVehiculoEnsurance' + numeroVeces.toString()).val(data.codigoEnsurance);
							$('#marca_' + numeroVeces.toString()).select2("val", "");
							$('#color_' + numeroVeces.toString()).select2("val", "");
							if (data.motor != "") {
								$('#num_motor' + numeroVeces.toString()).val($.trim(data.motor)).attr('disabled', 'disabled');
								$('#num_chasis' + numeroVeces.toString()).val($.trim(data.chasis)).attr('disabled', 'disabled');
								$('#anio_fabricacion' + numeroVeces.toString()).find("option[value=" + data.anioFabricacion + "]").attr('selected', 'selected').trigger('change');
								$('#anio_fabricacion' + numeroVeces.toString()).attr('disabled', 'disabled');
								$('#suma_asegurada_' + numeroVeces.toString()).val(data.valorEnsurance);
								$('#marca_' + numeroVeces.toString()).select2("val", data.marcaEnsurance);
								$('#color_' + numeroVeces.toString()).select2("val", data.color).change();
							}
							cargarModelos(data.modeloEnsurance, data.marcaEnsurance, numeroVeces.toString());
							if (data.dispositivoEnsurance == "SI")
								$('#disposito_rastreo' + numeroVeces.toString()).val(1);
							else
								$('#disposito_rastreo' + numeroVeces.toString()).val(0);

							if (parseInt(data.valorEnsurance) > 0) {
								$('#suma_asegurada_' + numeroVeces.toString()).val(data.valorEnsurance).trigger('change');
								activarBotonExtras(1);
							}
						} else {
							if (data.cotizacionId == "")
								alert("No se puede cotizar un automovil de " + data.anioFabricacion);
							$('#num_motor' + numeroVeces.toString()).val("");
							$('#num_chasis' + numeroVeces.toString()).val("");
							$('#anio_fabricacion' + numeroVeces.toString()).val("");
							$('#marca_' + numeroVeces.toString()).select2("val", "");
							$('#modelo_' + numeroVeces.toString()).select2("val", "");
							$('#color_' + numeroVeces.toString()).select2("val", "");
							$('#disposito_rastreo' + numeroVeces.toString()).select2("val", "");
							$('#suma_asegurada_' + numeroVeces.toString()).select2("val", "");
							$("#antiguedad_vh" + numeroVeces.toString()).val("");
						}
					}
				} else {
					alert(data.error);
				}
			}
		});
		$('#loading_' + numeroVeces.toString()).fadeOut(6000);
	}
	else
		consultarSRIVehiculos(valor, numero,event);
}

/*
 * METODO PARA OBTENER UN ELEMENTO DE VISTA ATRAVEZ DEL NOMBRE
 */
function getParameterByName(name) {
	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	results = regex.exec(location.search);
	return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

/*
 * METODO PARA ELIMINAR UN VEHICULO DE LA COTIZACION
 */
function eliminarVehiculo(numero) {
	var num = numero;
	$.ajax({
		url : '../CotizacionController',
		data : {
			"tipoConsulta" : "elminarObjetoDetalle",
			"producto" : "productoVehiculo",
			"vehiculoId" : $("#vehiculoId" + num).val(),
			"cotizacionId" : $("#cotizacion_id").text()
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			if (data.msgEliminarObjeto != "") {
				$('#myModal' + num).modal('hide');
				var vehiculos = $("#numero_vehiculos").val();
				$('#clonar_vehiculo' + num).remove();
				var restarVehiculo = parseInt(vehiculos) - 1;
				$('#numero_vehiculos').val(restarVehiculo);
			}
		}
	});
}

/*
 * METODO PARA CARGAR UNA COTIZACION EXISTENTE
 */
function cargarPorId(id) {
	
	if(QueryString.carga != null && QueryString.carga2 != null){
		$("#agregarVehiculonumero").hide();
		$("#lista_vehiculos").text("En carga masiva no se tiene acceso a esta opcion");
		//return;
	}
	
	var etapa;
	cargandoPorId=true;
		$.ajax({
			url : '../CotizacionController',
			data : {
				"tipoConsulta" : "encontrarPorId",
				"id" : id
			},
			type : 'post',
			datatype : 'json',
			success : function (data) {
				if (data.success) {
					var datosCotizacion = data.datosCotizacion;
					
					if (datosCotizacion != null)

						$("#estado_cotizacion").val(datosCotizacion.estadoCotizacion);

					if (datosCotizacion.etapa1 != null) {
						$("#grupo_productos").removeAttr('required');
						$("#productos").removeAttr('required');
						$("#tipo_identificacion_principal").removeAttr('required');
						$("#porc_comision").removeAttr('required');
						$("#identificacion").removeAttr('required');
						$("#nombres").removeAttr('required');
						$("#apellidos").removeAttr('required');
						$("#tipo_identificacion").removeAttr('required');
						$("#agentes").removeAttr('required');
						$("#vigencia").removeAttr('required');
						$("#punto_venta").removeAttr('required');
						$("#acuerdosListado").removeAttr('required');
						
						var etapa1 = datosCotizacion.etapa1;

						if (tipoObjeto != "VHDinamico") {
							gruposProductos(etapa1.grupoProductos, etapa1.productos, etapa1.tasa, etapa1.puntoVenta);
							cargarTablaCoberturas("", 1, "", "", etapa1.productos);
							metodoObtenerProductoFormulados(etapa1.productos); // Validacion Productos formulados
							metodoValidacionTasas(etapa1.tasa); // Validacion de Vehiculos Cerrados
							if(etapa1.productos =='24' || etapa1.productos =='25' || etapa1.productos =='26' || grupoPorProductoId=='155'){
								$("#txtfechaCompra").val(etapa1.fechaCompra);
								$("#mes_pago").val(etapa1.diaMesPago);
								$("#txtDiasExtras").val(etapa1.diasExtras);								
							}
						}

						cargarPuntosVenta(etapa1.puntoVenta);

						cargarDescuentosPorUnidadNegocio(etapa1.descuentoId, etapa1.unidadNegocioId);

						cargarVigenciasPolizas(etapa1.vigenciaPoliza);

						if(etapa1.acuerdoId !=""){ // Agente Acuerdo
							$(".acuerdos" ).each(function() {
								  $(this).show();
							});								
							cargarAgentesSelect2(etapa1.agente, etapa1.porComisionAgente,etapa1.agenteAcuerdoId);
						}else{
							cargarAgentesSelect2(etapa1.agente, etapa1.porComisionAgente,'');
							$(".acuerdos" ).each(function() {
								  $(this).hide();
							});
							
						}
						

						$("#identificacion").val(etapa1.identificacion);

						$("#nombres").val(etapa1.nombres);

						$("#nombre_completo").val(etapa1.nombreCompleto);

						$("#apellidos").val(etapa1.apellidos);
											
						
						cargarTiposIdentificacion(etapa1.tipoIdentificacion, 'principal', true);
						cargarFormasPago("", "formasPago");
						cargarFormasPago("", "intitucionesFinancieras");
						cargarFormasPago("", "aniosVigencia");
						etapa = 1;

					}
					if (datosCotizacion != null)
						var grupoPorProductoId = "";

					if (tipoObjeto == "VHDinamico")
						grupoPorProductoId = "148";
					else
						grupoPorProductoId = datosCotizacion.etapa1.productos;
					$("#codigoProductos").val(grupoPorProductoId);

					if(QueryString.carga2 == null){
						if (datosCotizacion.etapa2 != null) {
						$("a[href='#next']").click();
						var etapa2 = datosCotizacion.etapa2;
						var objetos = etapa2.objetos;

						if (objetos != null) {
							for (var i = 0; i < objetos.length; i++) {

								var objeto = objetos[i];

								agregarVehiculo();
								cargarDatosVehiculo(objeto.numero, objeto.anio, objeto.antiguedadVehiculo, objeto.conductorEdad, objeto.conductorNumeroHijos, objeto.zona, objeto.dispositivoRastreo, objeto.ceroKilometros, objeto.conductorGenero, objeto.conductorEstadoCivil, objeto.guardaGaraje, objeto.combustible, objeto.sucursal, objeto.marca, objeto.modelo, objeto.color, objeto.tipoUso, objeto.tipoVehiculo, objeto.tipoAdquisicion, data.datosCotizacion.etapa1.tasa,
										objeto.hijosConduzcan,objeto.kilometrajeAnual,objeto.zonaTransito);
								cargarExtrasVehiculos(objeto.numero, objeto.extras);
								$("#anio_fabricacion" + objeto.numero).removeAttr('required');
								$("#marca_" + objeto.numero).removeAttr('required');
								$("#modelo_" + objeto.numero).removeAttr('required');
								$("#color_" + objeto.numero).removeAttr('required');
								$("#disposito_rastreo" + objeto.numero).removeAttr('required');
								$("#cero_kilometros" + objeto.numero).removeAttr('required');
								$("#antiguedad_vh" + objeto.numero).removeAttr('required');
								$("#conductor_edad" + objeto.numero).removeAttr('required');
								$("#conductor_genero" + objeto.numero).removeAttr('required');
								$("#conductor_estado_civil" + objeto.numero).removeAttr('required');
								$("#valor_hijos" + objeto.numero).removeAttr('required');
								$("#sucursales" + objeto.numero).removeAttr('required');
								$("#zona" + objeto.numero).removeAttr('required');
								$("#guarda_garage" + objeto.numero).removeAttr('required');
								$("#combustible" + objeto.numero).removeAttr('required');
								$("#tipo_vehiculo" + objeto.numero).removeAttr('required');
								$("#tipo_uso" + objeto.numero).removeAttr('required');								
								$("#zona_transito" + objeto.numero).removeAttr('required');
								$("#hijos_conduzcan" + objeto.numero).removeAttr('required');							
								$("#conductor_estado_civil" + objeto.numero).removeAttr('required');
								$("#kilometraje_anual" + objeto.numero).removeAttr('required');									
								$("#vehiculoId" + objeto.numero).val(objeto.id); 
								$("#num_placa" + objeto.numero).val(objeto.placa); 
								$("#num_motor" + objeto.numero).val(objeto.motor); 
								$("#num_chasis" + objeto.numero).val(objeto.chasis); 
								$("#suma_asegurada_" + objeto.numero).val(objeto.sumaAsegurada).attr('required', 'required');
								activarBotonExtras(objeto.numero);
								activarBotonPaquetes(objeto.numero);
								$("#km_recorridos" + objeto.numero).val(objeto.kilometrosRecorridos); 
								$("#tasaVehiculo" + objeto.numero).val(objeto.tasa);
								$("#tonelaje" + objeto.numero).val(objeto.tonelaje);
								$("#tipo_adquisicion" + objeto.numero).val(objeto.tipoAdquisicion);		
								$("#hijos_conduzcan" + objeto.numero).val(objeto.hijosConduzcan);								
								$("#kilometraje_anual" + objeto.numero).val(objeto.kilometrajeAnual);								
								$("#zona_transito" + objeto.numero).val(objeto.zonaTransito);
								
								if(objeto.identificacionConductor!=""){																		
									$(".datosConductor"+ objeto.numero).fadeIn();
									$("#vh_comercial_check"+ objeto.numero).prop("checked",true);
									$("#vh_empresa_check"+ objeto.numero).prop("checked",false);
									$("#identificacion_conductor" + objeto.numero).val(objeto.identificacionConductor);
									$("#nombres_conductor" + objeto.numero).val(objeto.nombresConductor);
									$("#apellidos_conductor" + objeto.numero).val(objeto.apellidosConductor);
									$("#codigo_conductor_ensurance" + objeto.numero).val(objeto.codigoConductorEnsurance);	
								}else{
									$(".datosConductor"+ objeto.numero).fadeOut();
									$("#vh_comercial_check"+ objeto.numero).prop("checked",false);
									$("#vh_empresa_check"+ objeto.numero).prop("checked",true);									
								}		
								solicitarInspeccionArr[Number(objeto.numero)] = objeto.necesitaInspeccion;

								var tieneTodoRiesgo = false;

								if (i == 0) {
									for (var w = 0; w < objeto.coberturasPrincipales.length&&i <= objetos.length; w++) {

										var coberturaPrincipal = objeto.coberturasPrincipales[w];
										if (coberturaPrincipal.id == '30075' || coberturaPrincipal.id == '40010') { //todo riesgo
											$("#coberturaTR_check").prop("checked",false);
											$("#coberturaTR_check").click();
											$("#porcentaje_suma_asegurada").val(coberturaPrincipal.porSumaAsegurada);
											$("#monto_fijo").val(coberturaPrincipal.montoFijo);
											$("#valor_siniestro").val(coberturaPrincipal.porValorSiniestro);
											tieneTodoRiesgo = true;
										}
										if (coberturaPrincipal.id == '40037' && !tieneTodoRiesgo) {
											$("#coberturaDT_check").prop("checked",false);
											$("#coberturaDT_check").click();
											$("#porcentaje_suma_aseguradaDT").val(coberturaPrincipal.porSumaAsegurada);
										}
										if (coberturaPrincipal.id == '4540881508289' && !tieneTodoRiesgo) {
											$("#coberturaRC_check").prop("checked",false);
											$("#coberturaRC_check").click();
											$("#deducible_rc").val(coberturaPrincipal.montoFijo);
											
										}
									}
									cargarTablaCoberturas("", objeto.numero, objeto.coberturas, objeto.paquete, grupoPorProductoId);
								}
								$("#valorUnicoResponsabilidadCivil").val(objeto.valorExcesoRC);
								if (objeto.paquete != null && objeto.paquete != '' && objeto.paquete != 0)
									$("#paquete" + objeto.paquete + "_check").prop('checked', true);

							}
							editoVehiculo = false;
							habilitarDesabilitarDescuentos();
							$("a[href='#next']").click();
							if (objetos == null || objetos.length == 0) {
								agregarVehiculo();
								cargarDatosVehiculo(1, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "","","","");
								cargarTablaCoberturas("", 1, "", "", grupoPorProductoId);
							}
							etapa = 2;

							//agregar atributos requeridos de la primera pantalla
							$("#tipo_identificacion_principal").attr('required', 'required');
							$("#porc_comision").attr('required', 'required');
							$("#identificacion").attr('required', 'required');
							$("#nombres").attr('required', 'required');
							$("#apellidos").attr('required', 'required');
							$("#tipo_identificacion").attr('required', 'required');
							$("#agentes").attr('required', 'required');
							$("#vigencia").attr('required', 'required');
							$("#punto_venta").attr('required', 'required');

							if (etapa2.vigenciaDesde) {
								var dia = etapa2.vigenciaDesde.date < 10 ? '0' + etapa2.vigenciaDesde.date : etapa2.vigenciaDesde.date;
								var mes = etapa2.vigenciaDesde.month < 9 ? "0" + (etapa2.vigenciaDesde.month + 1) : (etapa2.vigenciaDesde.month + 1);
								var anio = (1900 + etapa2.vigenciaDesde.year);
								var aux = '' + anio + '-' + mes + '-' + dia;
								$("#fecha_inicio_vigencia").val(aux);
							}
						} else {
							cargarTablaCoberturas("", objeto.numero, objeto.coberturas, objeto.paquete, grupoPorProductoId);
						}
					} else {
						agregarVehiculo();
						cargarDatosVehiculo(1, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
						cargarTablaCoberturas("", 1, "", "", grupoPorProductoId);
					}
					}	
					if(QueryString.carga2 != null || QueryString.carga != null){
						solicitarInspeccionArr[0]= false;
					}										
					
					if (datosCotizacion != null)
						if (datosCotizacion.etapa3 != null) {
							cargarOrigenInspeccion("");
							var etapa3 = datosCotizacion.etapa3;
							cargarSolicitudInspeccionPorId(id);

							if (etapa3.solicitudDescuento != null && etapa3.solicitudDescuento.descuentoId != null) {
								tieneDescuento = true;
								
								
								$('#porcentaje_solicitud_descuento').val(etapa3.solicitudDescuento.porcentaje).attr("disabled", "disabled");
								cargarMotivosDescuento(etapa3.solicitudDescuento.motivo);
								$('#descripcion_motivo_descuento').text(etapa3.solicitudDescuento.descripcion).attr("disabled", "disabled");
								var aux = $("#estado_solicitud_descuento").attr("disabled", "disabled");
								if (etapa3.solicitudDescuento.estado.trim() != 'Pendiente')
									$(aux).children().next().html(etapa3.solicitudDescuento.estado + ' por ' + etapa3.solicitudDescuento.usuarioActualiza);
								else
									$(aux).children().next().html(etapa3.solicitudDescuento.estado);

								$(aux).show();
								$("#enviar_solicitud_descuento").fadeOut("slow"); //.attr('disabled','disabled');
								$("#boton_actualizar_solicitud_descuento").show();
								$('#codigo_descuento').attr("disabled", "disabled");
								$('#motivo_descuento').attr("disabled", "disabled");

							} else {
								//cargarDescuentos("");
								cargarMotivosDescuento("");

							}

							if (etapa3.endosoBeneficiario != null) {
								if (etapa3.endosoBeneficiario.beneficiarioId != null || etapa3.endosoBeneficiario.beneficiarioId != "")
									cargarPestanaEndosoBeneficiario(etapa3.endosoBeneficiario.identificacion, etapa3.endosoBeneficiario.monto, etapa3.endosoBeneficiario.beneficiarioId);
								$("#asegurado_id").val(etapa3.endosoBeneficiario.entidadId);
								$("#identificacion_asegurado").val(etapa3.endosoBeneficiario.identificacion);
								$("#nombres_asegurado").val(etapa3.endosoBeneficiario.nombres);
								$("#apellidos_asegurado").val(etapa3.endosoBeneficiario.apellidos);
								$("#nombre_completo_asegurado").val(etapa3.endosoBeneficiario.nombreCompleto);

								cargarTiposIdentificacion(etapa3.endosoBeneficiario.tipoIdentificacion, 'asegurado', true);
								$("#endoso_beneficiario_id").val(etapa3.endosoBeneficiario.endosoBeneficiarioId);
								$("a[href='#next']").click();

							} else {
								cargarPestanaEndosoBeneficiario(datosCotizacion.etapa1.identificacion, 0, "");
							}

							if (etapa3.formaPago != null && etapa3.formaPago.pagoId != null) {

								$("#descargar_certificado").fadeIn("slow").removeAttr("disabled");
								$("#enviar_certificado").fadeIn("slow").removeAttr("disabled");
								$("#codigoPagoRegistrado").val(etapa3.formaPago.pagoId);

								$("a[href='#next']").click();
								cargarTiposIdentificacion("", "banco", false);
								cargarTiposIdentificacion("", "tarjeta", false);

								cargarFormasPago(etapa3.formaPago.formaPagoId, "formasPago");
								if (etapa3.formaPago.formaPagoNombre.trim().toString() == "DEBITO BANCARIO") {
									cargarTiposIdentificacion("", "tarjeta", false);
									cargarTiposIdentificacion(etapa3.formaPago.tipoIdentificacion, "banco", true);
									$("#bancoNumeroCuenta").val(etapa3.formaPago.numCuentaTarjeta);
									
									// Nueva Validacion para nombre y apellido del titular de una cuenta
									var nombreTitular = etapa3.formaPago.nombreTitular;
									var nombresApellidos = nombreTitular.split("-");
									
									if(tipoIdentificacionId != "4"){
										if(nombresApellidos.length > 0){
											$("#nombresTitularDebitos").val(nombresApellidos[0]); 
											$("#apellidosTitularDebitos").val(nombresApellidos[1]);
										}
										if(nombresApellidos.length == 0){
											$("#nombresTitularDebitos").val(nombreTitular);
										}
									}else{
										$("#nombreCompletoTitularDebitos").val(nombreTitular);
									}

									$("#bancoIdentificacion").val(etapa3.formaPago.identificacionTitular);
									$("#txtcuotaInicialbancoPlazo").val(etapa3.formaPago.cuotaInicial);
									//$("#bancoId").val(etapa3.formaPago.institucionFinancieraId).attr("selected", "selected");
									cargarFormasPago(etapa3.formaPago.institucionFinancieraId, "intitucionesFinancieras");
									$("#bancoTipoCuenta").val(etapa3.formaPago.tipoCuenta);
									//$("#bancoTipoIdentificacion option[value='"+ etapa3.formaPago.tipoIdentificacion +"']").attr("selected", "selected");
									$("#txtfechaInicialpago").val(etapa3.formaPago.fechaDebito);
									$("#bancoPlazo").val(etapa3.formaPago.plazo);
									if (etapa3.formaPago.cuotaInicial > 0)
										$("#bancoPlazo option[value='10']").remove();
									//validarValoresPagos("DEBITO BANCARIO");

								}

								if (etapa3.formaPago.formaPagoNombre.trim().toString() == "DEBITO TARJETA") {
									cargarTiposIdentificacion(etapa3.formaPago.tipoIdentificacion, "tarjeta", true);
									cargarTiposIdentificacion("", "banco", false);
									cargarFormasPago(etapa3.formaPago.institucionFinancieraId, "intitucionesFinancieras");
									cargarFormasPago(etapa3.formaPago.anioExpTarjeta, "aniosVigencia");
									//$("#tarjetaId option[value='"+ etapa3.formaPago.institucionFinancieraId +"']").attr("selected", "selected");
									$("#tarjetaTipoCuenta").val(etapa3.formaPago.tipoCuenta);
									$("#tarjetaNumero").val(etapa3.formaPago.numCuentaTarjeta);									
									
									var tipoIdentificacionId = etapa3.formaPago.tipoIdentificacion;
									
									// Nueva Validacion para nombre y apellido del titular de una cuenta
									var nombreTitular = etapa3.formaPago.nombreTitular;
									var nombresApellidos = nombreTitular.split("-");
									
									if(tipoIdentificacionId != "4"){
										if(nombresApellidos.length > 0){
											$("#nombresTitularTarjetas").val(nombresApellidos[0]); 
											$("#apellidosTitularTarjetas").val(nombresApellidos[1]);
										}
										if(nombresApellidos.length == 0){
											$("#nombresTitularTarjetas").val(nombreTitular);
										}
									}else{
										$("#nombreCompletoTitularTarjetas").val(nombreTitular);
									}
									
									$("#tarjetaMesExp").val(etapa3.formaPago.mesExpTarjeta);
									//$("#tarjetaAnioExp").val(etapa3.formaPago.anioExpTarjeta);
									$("#txtcuotaInicialtarjetaPlazo").val(etapa3.formaPago.cuotaInicial);
									$("#tarjetaIdentificacion").val(etapa3.formaPago.identificacionTitular);
									$("#tarjetaPlazo").val(etapa3.formaPago.plazo);
									$("#txtfechaInicialpagoTarjeta").val(etapa3.formaPago.fechaDebito);
									if (etapa3.formaPago.cuotaInicial > 0)
										$("#tarjetaPlazo option[value='10']").remove();
									//validarValoresPagos("DEBITO TARJETA");
								}

								if (etapa3.formaPago.formaPagoNombre.trim().toString() == "CREDITO CUOTAS") {
									var listadoCuotas = etapa3.listadoCuotas;
									var filasCuotas = "";
									$("#detallePagoCuotas").empty();
									if (etapa3.formaPago.cuotaInicial > 0)
										$("#cboFpPlazo option[value='10']").remove();
									//
									$.each(listadoCuotas, function (index) {
										filasCuotas = filasCuotas + "<tr>" +
											"<td align='center'><b>" + listadoCuotas[index].cuotaOrden + "</b></td>";
										if (index == 0) {
											$("#txtcuotaInicial").val(etapa3.formaPago.cuotaInicial);
											$("#cboFpPlazo").val(etapa3.formaPago.plazo);
											filasCuotas = filasCuotas + "<td align='center'><input type='text' class='detalleChequesPagos' id='cuotaInicial'  size='12' style='margin: 5px; padding: 5px;' value='" + listadoCuotas[index].cuotaNumCheque + "' disabled='disabled'></td>";
										} else {
											filasCuotas = filasCuotas + "<td align='center'><input type='text' class='detalleChequesPagos' id='cuotaInicial'  size='12' style='margin: 5px; padding: 5px;' value='" + listadoCuotas[index].cuotaNumCheque + "' disabled='disabled'></td>";
										}

										filasCuotas = filasCuotas + "<td align='center'>" + parseFloat(listadoCuotas[index].cuotaValor).toFixed(2) + "</td>" +
											"<td align='center'>" + listadoCuotas[index].cuotaFechaPago + "</td>";
										
									});
									$("#detallePagoCuotas").append(filasCuotas);
									$("#rowDetallePagos").show();
									validarValoresPagos("CREDITO CUOTAS");
								}

							} else {
								cargarOrigenInspeccion("");
								//cargarDescuentos("");
								cargarMotivosDescuento("");
								cargarTiposIdentificacion("", "banco", false);
								cargarTiposIdentificacion("", "tarjeta", false);
							}

							vehiculosCotizacion();
							//$("#loading").fadeIn();
							$(".loading-indicator").delay((1000 * parseInt($('#numero_vehiculos').val()))).fadeOut();
							$("#tabbable").delay(1000).show();
						} else {
							cargarOrigenInspeccion("");
							//cargarDescuentos("");
							cargarMotivosDescuento("");
							cargarTiposIdentificacion("", "banco", false);
							cargarTiposIdentificacion("", "tarjeta", false);
							cargarPestanaEndosoBeneficiario(datosCotizacion.etapa1.identificacion, 0, "");
							$("a[href='#next']").click();

						}
					var tipoIdentificacionId = data.datosCotizacion.etapa1.tipoIdentificacion;
					if (tipoIdentificacionId == '' || tipoIdentificacionId == '1' || tipoIdentificacionId == '2' || tipoIdentificacionId == '3') {
						//natural
						if (data.datosCotizacion == null || data.datosCotizacion.datosFacturaCliente == null) {
							$("#nombre_direccion_solicitante").val(datosCotizacion.etapa1.nombreCompleto);
							cargarDireccionFactura();
						} else {
							if (data.datosCotizacion.datosFacturaCliente != null)
								cargarDireccionFactura("solicitante", data.datosCotizacion.datosFacturaCliente);
							else
								cargarDireccionFactura("solicitante");

							if (data.datosCotizacion.datosFacturaAsegurado != null)
								cargarDireccionFactura("asegurado", data.datosCotizacion.datosFacturaAsegurado);
							else
								cargarDireccionFactura("asegurado");
							$("a[href='#next']").click();
						}
					} else {
						if (data.datosCotizacion == null || data.datosCotizacion.datosFacturaCliente == null) {
							$("#nombre_direccion_solicitante").val(datosCotizacion.etapa1.nombreCompleto);
							cargarDireccionFactura();
						} else {
							if (data.datosCotizacion.datosFacturaCliente != null)
								cargarDireccionFactura("solicitante", data.datosCotizacion.datosFacturaCliente);
							else
								cargarDireccionFactura("solicitante");

							if (data.datosCotizacion.datosFacturaAsegurado != null)
								cargarDireccionFactura("asegurado", data.datosCotizacion.datosFacturaAsegurado);
							else
								cargarDireccionFactura("asegurado");
							$("a[href='#next']").click();
						} 
					}

					if (datosCotizacion.estadoCotizacion == "emitido") {
						$(".datosVehiculo").each(function () {
							$(this).attr("disabled", "disabled");
						});

						$(":button").each(function () {
							$(this).attr("disabled", "disabled");
						});

						$(":checkbox").each(function () {
							$(this).attr("disabled", "disabled");
						});					

					}
					//etapa 6
					var etapa6 = datosCotizacion.etapa6;
					
					tieneDocumento("PolizaFirmada",etapa6.tieneArchivoPolizaFirmada);
					
					tieneDocumento("AutorizacionDebito",etapa6.tieneArchivoAutorizacionDebito);
					
					tieneDocumento("FormularioUPLA",etapa6.tieneArchivoFormularioUPLA);
					
					tieneDocumento("CaratulaCotizacion",etapa6.tieneArchivoCaratulaCotizacion);
					
					tieneDocumento("Factura",etapa6.tieneArchivoFactura);
					
					if (etapa6.numeroTramite !=null) {
						$("#mensajeNumeroTramite").attr("class","alert alert-success").html("Numero de tramite "+etapa6.numeroTramite).attr("style",$("#mensajeNumeroTramite").attr("style")+" ; width:50%;").fadeIn("slow");
						$("#crearTramiteWF").fadeOut();
					}
					
					if (etapa6.tieneArchivoPolizaFirmada + "" == "1") {
						$("#tienePolizaFirmada").val("1");
						$("a[href='#next']").click();
					} else {
						$("#tienePolizaFirmada").val("0");
					}

					if (etapa6.tieneArchivoAutorizacionDebito + "" == "1") {
						$("#tieneAutorizacionDebito").val("1");
						$("a[href='#next']").click();
					} else {
						$("#tieneAutorizacionDebito").val("0");
					} 
					if (etapa6.tieneArchivoFormularioUPLA == "1") {
						$("#tieneFormularioUPLA").val("1");
						$("a[href='#next']").click();
					} else {
						$("#tieneFormularioUPLA").val("0");
					}
					if (etapa6.tieneArchivoCaratulaCotizacion == "1") {
						$("#tieneCaratulaCotizacion").val("1");
						$("a[href='#next']").click();
					} else {
						$("#tieneCaratulaCotizacion").val("0");
					}

					if (etapa6.tieneArchivoFactura == "1") {
						$("#tieneFactura").val("1");
						$("a[href='#next']").click();
					} else {
						$("#tieneFactura").val("0");
					}
					cargandoPorId=false;
				} else {
					alert(data.error);
				}
			}
		});
	return etapa;
}

/*
 * METODO QUE CAPTURA TODOS LOS DATOS DEL VEHICULO PARA CREAR LA COTIZACION.
 */
function vehiculosCotizacion() {
	var listadoVehiculos = "";

	$.ajax({
		url : '../ObjetoVehiculoController',
		data : {
			"tipoConsulta" : "obtenerVehiculos",
			"producto" : "productoVehiculo",
			"cotizacionId" : $("#cotizacion_id").text(),
			"tipoObjeto" : tipoObjeto,
		},
		type : 'POST',
		datatype : 'json',
		success : function (data) {
			var totalVehiculos = Number(data.numeroVehiculos);
			$("#total_suma_asegurada_vh").val(data.valorAsegurado);
			$("#valor_endoso_beneficiario").attr('max', data.valorAsegurado);
			$("#prima_sin_impuestos_vh").val(parseFloat(data.valorPrima).toFixed(2));
			$("#prima_otros_iva").val(parseFloat(data.valorPrimaOtrosIva).toFixed(2));
			$("#prima_otros_sin_iva").val(parseFloat(data.valorPrimaOtros).toFixed(2));
			$("#super_bancos_vh").val(parseFloat(data.valorSuperBancos).toFixed(2));
			if (parseFloat(data.porcentajeDescuento) > 0)
				$("#filaDescuento").show();
			$("#porcentaje_descuento_vh").val(parseFloat(data.porcentajeDescuento).toFixed(2));
			$("#seguro_campesino_vh").val(parseFloat(data.valorSeguroCampesino).toFixed(2));
			$("#recargo_seguro_campesino_vh").val(data.valorRecargoCampesino);
			$("#derecho_emision_vh").val(data.valorDerechosEmision);
			$("#subtotal_vh").val(parseFloat(data.valorSubTotal).toFixed(2));
			//$("#iva_vh").val(data.valorIva);
			// Calculo del IVA  
			calculoIVAPuntoVentaCotizacion();			
			$(".total_vh").val( parseFloat(data.valorTotal).toFixed(2));
			$("#total_vh_origin").val( parseFloat(data.valorTotal).toFixed(2));
			$("#resumenTotalPago").children().first().empty().append("$" + parseFloat(data.valorTotal).toFixed(2));
			listadoVehiculos += '<thead>' +
			'<tr style="font-size:x-small;">' +
			'<th class="sorting">Marca - Modelo</th>' +
			'<th class="sorting">A&ntilde;o</th>' +
			'<th class="sorting">Disp. Rastreo</th>' +
			'<th class="sorting">Sucursal QBE</th>' +
			'<th class="sorting">Suma asegurada</th>' +
			'<th class="sorting">Prima</th>' +
			'<th class="sorting">Coberturas Principales</th>' +
			'<th>Tasa</th>' +
			'<th class="sorting">Extras</th>' +
			'</tr>' +
			'</thead>' +
			'<tbody style="font-size:x-small;">';
			var primaTotalSinImpuesto = Number(0.0);
			var listadoTasas = new Array();
			var valorTasaTotal = 0;
			var tasaObtenida = 0;
			for (var i = 0; i < totalVehiculos; i++) {
				if (QueryString.carga2 != null || QueryString.carga != null){
					solicitarInspeccionArr[i]= false;
					$("#necesitaInspeccion").val(false);
				}
				listadoVehiculos += '<tr class="gradeA odd">' +
				'<td>' + data.vehiculosCotizacion[i].marca_modelo + '</td>' +
				'<td>' + data.vehiculosCotizacion[i].ano_fabricacion + '</td>' +
				'<td>' + data.vehiculosCotizacion[i].dispositivo_rastreo + '</td>' +
				'<td class="center">' + data.vehiculosCotizacion[i].sucursal + '</td>' +
				'<td class="center">' + parseFloat(data.vehiculosCotizacion[i].suma_asegurada).toFixed(2) + '</td>' +
				'<td class="center">' + parseFloat(data.vehiculosCotizacion[i].prima_vehiculo).toFixed(2) + '</td>' +
				'<td class="center">' +
				"<ul>";
				//var listaCoberturasPrincipales = "";
				if (casoEspecial)
					$(".cobertura" + (i + 1)).each(function () {
						if ($(this).is(":checked"))
							listadoVehiculos += "<li>" + $(this).attr("value") + " </li>";
					});
				else
					$(".cobertura").each(function () {
						if ($(this).is(":checked"))
							listadoVehiculos += "<li>" + $(this).attr("value") + " </li>";
					});

				listadoVehiculos += '</ul></td>';
				var vigencia = Number($("#vigencia").val());
				// Validacion 3 decimales Plan V - Autoconsa
				var grupoProductos = $("#grupo_productos").val();				
				if(grupoProductos == "11")
					tasaObtenida = parseFloat(data.vehiculosCotizacion[i].tasa_vehiculo * 100).toFixed(3);
				else
					tasaObtenida = parseFloat(data.vehiculosCotizacion[i].tasa_vehiculo * 100).toFixed(2);
				
				listadoVehiculos += '<td class="center" >' + tasaObtenida + '%</td>';
				// Agregamos listado de tasas solo para vehiculos dinamicos
				if (tipoObjeto == "VHDinamico") {
					listadoTasas.push(tasaObtenida);
					valorTasaTotal += parseFloat(tasaObtenida);
				}

				listadoVehiculos += '<td class="center"><a href="#" id="' + i + '" class="resumenExtras" vh="' + data.vehiculosCotizacion[i].codigo_vehiculo + '">Ver detalle</a></td>' +
				'</tr>' +
				'<tr class="detalleResumenExtras' + i + '">' +
				'<td colspan="8"></td>' +
				'</tr>';

				primaTotalSinImpuesto += Number(data.vehiculosCotizacion[i].prima_vehiculo);
			}
			listadoVehiculos += '</tbody>';

			// Validacion Tasa Minima y Promedio de la tasa
			if (tipoObjeto == "VHDinamico") {
				// Ordenamos las tasas de los vehiculos
				listadoTasas = listadoTasas.sort();
				// Obtenemos la tasa primera ordenada.
				$("#tasa_minima_valor").val(listadoTasas[0]);
				$("#tasa_minima").val(listadoTasas[0]);
				$("#tasa_minima").attr("min", listadoTasas[0]);
				// Agregamos la tasa promedio
				var tasaPromedio = valorTasaTotal / totalVehiculos;
				$("#tasa_promedio").val(tasaPromedio.toFixed(2));
				cargarDescuentos("", tasaPromedio);
				/*if (tasaPromedio.toFixed(2) <= 3.3) {
					//$("#codigo_descuento").html('<option value="" selected="selected">Seleccione un tipo de descuento</option><option value="15" minimo="10.1" maximo="15">Descuento de Vicepresidente Masivos del (10.1% al 15.0%)</option><option value="18" minimo="30" maximo="30">Funcionarios QBE</option>');
					cargarDescuentos(seleccionada, tasa)
				}*/
			}

			$("#prima_sin_impuestos_vh").val(parseFloat(primaTotalSinImpuesto).toFixed(2));

			$(".detalleResumenExtras").hide();

			// Mostrar los textos de las coberturas
			var listadoCoberturas = '<thead>' +
				'<tr>' +
				'<th class="sorting">Tipo de Cobertura</th>' +
				'<th class="sorting">Detalle de la cobertura</th>' +
				'</tr>' +
				'</thead>' +
				'<tbody>';
			for (var i = 0; i < totalVehiculos; i++) {
				listadoCoberturas += '<tr class="gradeA odd" colspan="2"> Veh&iacute;culo : ' + data.vehiculosCotizacion[i].marca_modelo + ' - ' + data.vehiculosCotizacion[i].ano_fabricacion + '</tr>';

				for (var j = 0; j < data.textosCoberturas.length; j++) {
					listadoCoberturas += '<tr class="gradeA odd">' +
					'<td>' + data.textosCoberturas[j].tipo_cobertura_nombre + '</td>' +
					'<td>' + data.textosCoberturas[j].texto + '</td></tr>';
				}
			}
			listadoCoberturas += '</tbody>';
			$("#tabla_vehiculos_cotizacion").empty().html(listadoVehiculos);
			$("#tabla_textos_coberturas").empty().html(listadoCoberturas);
			$("#tabbable").delay(1000 * parseInt($('#numero_vehiculos').val())).fadeIn(1);
			$(".loading-indicator").delay((800 * parseInt($('#numero_vehiculos').val()))).fadeOut(1);
			$(".resumenExtras").click(function () {
				var vehiculoCotizadorId = $(this).attr("vh");
				var elem = $(this);
				if (elem.parent().parent().next().children().children().first().attr("style") == undefined) {
					$.each($(".vehiculoCotizador"), function () {
						if ($(this).val() == vehiculoCotizadorId) {
							var copiaTablaExtras = $("#extras_" + $(this).attr('numVehiculo'));
							elem.parent().parent().next().children().empty();
							copiaTablaExtras.clone().appendTo(elem.parent().parent().next().children());
							$(".detalleResumenExtras" + elem.attr("id") + " .eliminar-extra-btn").remove();
						}
					});
					elem.parent().parent().next().show("slow");
				} else {
					elem.parent().parent().next().hide("fast");
					elem.parent().parent().next().children().children().first().remove();
				}
			});
			$("#loading_tablaFinal").fadeOut();
		}
	});

	var vigenciaDesde = $("#fecha_inicio_vigencia").val();	

	if($("#confirmarDatos").is(':disabled')==false){				
		if (vigenciaDesde != null) { //consultar a rchicaiza condiciones para la vigencia
	
			if (!vigenciaDesde == "") {
				$.ajax({
					url : '../CotizacionController',
					data : {
						"tipoConsulta" : "actualizarFechaInicioVigencia",
						"vigenciaDesde" : vigenciaDesde,
						"cotizacionId" : $("#cotizacion_id").html().trim()
	
					},
					type : 'POST',
					datatype : 'json',
					success : function (data) {}
				});
			}
		}
	}
}

//Metodo para agregar un vehiculo
function guardarVehiculo(numero) {
	var numeroVeces = Number(numero);
	var modelo = "";

	
	if ($("#modelo_" + numeroVeces).select2('val') != "")
		modelo = $('#modelo_' + numeroVeces.toString()).select2('val');

	var color = "";

	if ($("#color_" + numeroVeces).select2('val') != "")
		color = $('#color_' + numeroVeces.toString()).select2('val');

	var sucursal = $('#sucursales' + numeroVeces.toString()).val();
	var anoFabricacion = $('#anio_fabricacion' + numeroVeces.toString()).val();
	var sumaAsegurada = $('#suma_asegurada_' + numeroVeces.toString()).val();
	var dispositivoRastreo = $('#disposito_rastreo' + numeroVeces.toString()).val();
	var ceroKilometros = $('#cero_kilometros' + numeroVeces.toString()).val();
	var vehiculoId = $("#vehiculoId" + numeroVeces.toString()).val();
	var inicioVigencia = $("#fecha_inicio_vigencia").val();
	if (tipoObjeto == "VHDinamico") {
		if (!($("#coberturaTR_check").is(":checked") || $("#coberturaDT_check").is(":checked") || $("#coberturaRC_check").is(":checked"))) {
			$("#coberturaTR_check").prop("checked", false);
			$("#coberturaTR_check").click();
		}
	} else {
		$("#coberturaTR_check").prop("checked", false);
		$("#coberturaTR_check").click();
	}
	if (modelo == "" || sucursal == "" || anoFabricacion == "" || sumaAsegurada == "" || dispositivoRastreo == "") {
		return $("#wizard").valid();
	} else if ($("#wizard").valid()) {

	var tasaVehiculosCerrados = "";
	if (tipoObjeto != "VHDinamico") {
		var grupoPorProductoId = $("#productos").val();
		// Validacion de Productos Formulados
		// Grupo Por Producto: 100 QBE Box
		if (grupoPorProductoId == "100") {
			tasaVehiculosCerrados = obtenerInformacionProducto(numero);
			// Error provocado para que no pase al siguiente paso
			if (tasaVehiculosCerrados == 0)
				$("#wizard").previous();
		}
		// Grupo Por Producto: Grupo Cerrado Automotores Continental - Plan V
		if (grupoPorProductoId == "24" || grupoPorProductoId == "25" || grupoPorProductoId == "26" || grupoPorProductoId=="155") {
			tasaVehiculosCerrados = obtenerInformacionProducto(numero);
		}
		var tasaVariable = $("#tasas").val();
		// Verificacion de tasa variable
		if (tasaVariable != "") {
			var retornoValidacion = validacionInformacionTasaProducto(numero);
			if (retornoValidacion[0] == "ERROR") {
				alert(retornoValidacion[1]);
				$("#wizard").previous(); // Error provocado para que no se pase al siguiente paso del wizard
			}
		}
	}			
	// Grabamos el vehiculo
	var porcentaje_suma_asegurada = 0;
	var monto_fijo = 0;
	var valor_siniestro = 0;
	var coberturas = "";
	var valorExcesoRC = 0;
	// Guardamos los valores de los deducibles de las coberturas principales
	if ($('#coberturaTR_check').is(':checked')) {
		if(tipoObjeto == "VHDinamico"){
			porcentaje_suma_asegurada = $('#porcentaje_suma_asegurada').val();
			monto_fijo = $('#monto_fijo').val();
			valor_siniestro = $('#valor_siniestro').val();}
	}

	if ($('#coberturaDT_check').is(':checked')) {
		if(tipoObjeto == "VHDinamico")
				porcentaje_suma_asegurada = $('#porcentaje_suma_aseguradaDT').val();
	}
	
	if ($('#coberturaRC_check').is(':checked')) {
		if(tipoObjeto == "VHDinamico")
			monto_fijo = $('#deducible_rc').val();
	}


		if ($('#paquete1_check').is(':checked')) {
		var cxp = $(".check_Black_Cobertura_");
		coberturas = "";
			for (var i = 0; i < cxp.length; i++) {
				if ($(cxp[i]).is(":checked") && (coberturas.indexOf($(cxp[i]).attr("id").split("_")[3] + ",") == -1)) {
					coberturas += $(cxp[i]).attr("id").split("_")[3] + ",";
				}
			}
		}

		if ($('#paquete2_check').is(':checked')) {
			var cxp = $(".check_Blue_Cobertura_");
			coberturas = "";
			for (var i = 0; i < cxp.length; i++) {
				if ($(cxp[i]).is(":checked") && coberturas.indexOf($(cxp[i]).attr("id").split("_")[3] + ",") == -1) {
					coberturas += $(cxp[i]).attr("id").split("_")[3] + ",";
				}
			}
		}

		if ($('#paquete3_check').is(':checked')) {
			var cxp = $(".check_Gold_Cobertura_");
			coberturas = "";
			for (var i = 0; i < cxp.length; i++) {
				if ($(cxp[i]).is(":checked") && coberturas.indexOf($(cxp[i]).attr("id").split("_")[3] + ",") == -1) {
					coberturas += $(cxp[i]).attr("id").split("_")[3] + ",";
				}
			}
		}

		if ($('#paquete5_check').is(':checked')) {
			var cxp = $(".check_Sin_Cobertura_");
			coberturas = "";
			for (var i = 0; i < cxp.length; i++) {
				if ($(cxp[i]).is(":checked") && coberturas.indexOf($(cxp[i]).attr("id").split("_")[3] + ",") == -1) {
					coberturas += $(cxp[i]).attr("id").split("_")[3] + ",";
				}
				if ($("#excesoResponsabilidadCivil_" + ($(cxp[i]).attr("id").split("_")[3]) + "_").html() != null) {
					valorExcesoRC = $("#valorUnicoResponsabilidadCivil").val();
				}
			}		
		}

		/*I N I C I O   D E P R E S I A C I O N   P A R A   P O L I Z A S   C O N   V I G E N C I A   D E   M A S   D E   D O S   A Ã¯Â¿Â½ O S*/
		var tiempoVigencia = $("#vigencia").val();
		var extras = $(".valorExtras_" + numeroVeces);
		var valorAseguradoTiempoVigencia = "";
		var valorAseguradoDepreciado = sumaAsegurada;
		for (i = 1; i <= tiempoVigencia; i++) {
			if (i == 1) {
				valorAseguradoTiempoVigencia += valorAseguradoDepreciado + "|";
			} else if (i > 1) {
				if (i == 2){
					if(tarificador=="V1")
						valorAseguradoDepreciado = parseFloat(valorAseguradoDepreciado).toFixed(2) - parseFloat(valorAseguradoDepreciado * 0.15).toFixed(2);
					if(tarificador=="V2")
						valorAseguradoDepreciado = parseFloat(valorAseguradoDepreciado).toFixed(2) - parseFloat(valorAseguradoDepreciado * 0.10).toFixed(2);						
				}
				else
					valorAseguradoDepreciado = parseFloat(valorAseguradoDepreciado).toFixed(2) - parseFloat(valorAseguradoDepreciado * 0.10).toFixed(2);
				
				valorAseguradoTiempoVigencia += valorAseguradoDepreciado + "|";
			}
		}
		valorAseguradoTiempoVigencia = valorAseguradoTiempoVigencia.substring(0, valorAseguradoTiempoVigencia.length - 1);

		var valoresExtras = "";
		for (var j = 0; j < extras.length; j++) {
		var valorExtraDepreciado = $(extras[j]).val();
		var valorExtraTiempoVigencia = "";
			for (i = 1; i <= tiempoVigencia; i++) {
				if (i == 1) {
					valorExtraTiempoVigencia += valorExtraDepreciado + ",";
				} else if (i > 1) {
					if (i == 2){
						if(tarificador=="V1")
							valorExtraDepreciado = parseFloat(valorExtraDepreciado).toFixed(2) - parseFloat(valorExtraDepreciado * 0.15).toFixed(2);								
						if(tarificador=="V2")
							valorExtraDepreciado = parseFloat(valorExtraDepreciado).toFixed(2) - parseFloat(valorExtraDepreciado * 0.10).toFixed(2);																					
					}else
						valorExtraDepreciado = parseFloat(valorExtraDepreciado).toFixed(2) - parseFloat(valorExtraDepreciado * 0.10).toFixed(2);

					valorExtraTiempoVigencia += valorExtraDepreciado + ",";
				}
			}
				valoresExtras += valorExtraTiempoVigencia + ";";
		}

			// Validacion - QBE Motos VA no puede ser menor $ 10 000 ni superior a $ 30.000
			if (tipoObjeto == "Motos") {
				var valorSumaAsegurada = Number(sumaAsegurada);
				var valorMinimo = Number("8000");
				var valorMaximo = Number("35000");

				if (valorSumaAsegurada < valorMinimo || valorSumaAsegurada > valorMaximo) {
					alert("Valor Asegurado del Vehiculo " + numero + ",no puede ser menor a $ " + valorMinimo + " ni mayor a $" + valorMaximo);
					// Error provocado para que no pase al siguiente paso
					$("#wizard").previous();
					return false;

				}
			}

			/*F I N   D E P R E S I A C I O N   P A R A   P O L I Z A S   C O N   V I G E N C I A   D E   M A S   D E   D O S   A Ã¯Â¿Â½ O S*/
			$("#loading_tablaFinal").fadeIn();

			$.ajax({
				url : "../ObjetoVehiculoController",
				data : {
					"tipoConsulta" : "guardarVehiculo",
					"vehiculoId" : vehiculoId,
					"producto" : "productoVehiculo",
					"codigoVehiculoEnsurance" : $("#codigoVehiculoEnsurance" + numeroVeces.toString()).val(),
					"cotizacionId" : $("#cotizacion_id").text(),
					"modelo" : modelo,
					"sucursal_id" : sucursal,
					"agenteId" : $("#agentes").val(),
					"motor" : $('#num_motor' + numeroVeces.toString()).val(),
					"chasis" : $('#num_chasis' + numeroVeces.toString()).val(),
					"placa" : $('#num_placa' + numeroVeces.toString()).val(),
					"anio_fabricacion" : anoFabricacion,
					"suma_asegurada_valor" : sumaAsegurada,
					"suma_asegurada_valor_arr" : valorAseguradoTiempoVigencia,
					"disposito_rastreo" : dispositivoRastreo,
					"cero_kilometros" : ceroKilometros,
					"antiguedad_vh" : $('#antiguedad_vh' + numeroVeces.toString()).val(),
					"conductor_edad" : $('#conductor_edad' + numeroVeces.toString()).val(),
					"conductor_genero" : $('#conductor_genero' + numeroVeces.toString()).val(),
					"conductor_estado_civil" : $('#conductor_estado_civil' + numeroVeces.toString()).val(),
					"numero_hijos" : $('#valor_hijos' + numeroVeces.toString()).val(),
					"zona" : $('#zona' + numeroVeces.toString()).val(),
					"guarda_garage" : $('#guarda_garage' + numeroVeces.toString()).val(),
					"km_recorridos" : $('#km_recorridos' + numeroVeces.toString()).val(),
					"combustible" : $('#combustible' + numeroVeces.toString()).val(),
					"porcentaje_suma_asegurada" : porcentaje_suma_asegurada,
					"monto_fijo" : monto_fijo,
					"valor_siniestro" : valor_siniestro,
					"color" : color,
					"tipoObjeto" : tipoObjeto,
					"necesitaInspeccion" : solicitarInspeccionArr[numeroVeces],
					"valorExcesoRC" : valorExcesoRC,
					"tasaVehiculosCerrados" : tasaVehiculosCerrados, // Tasa vehiculos cerrados					
					"kilometraje_anual" : $('#kilometraje_anual' + numeroVeces.toString()).val(),
					"hijos_conduzcan" : $('#hijos_conduzcan' + numeroVeces.toString()).val(),
					"zona_transito" : $('#zona_transito' + numeroVeces.toString()).val(),										
					"identificacion_conductor" : $('#identificacion_conductor' + numeroVeces.toString()).val(),
					"nombres_conductor" : $('#nombres_conductor' + numeroVeces.toString()).val(),
					"apellidos_conductor" : $('#apellidos_conductor' + numeroVeces.toString()).val(),
					"codigo_conductor_ensurance" : $('#codigo_conductor_ensurance' + numeroVeces.toString()).val(),
					
					
					// Coberturas
					"coberturaTR_check" : $('#coberturaTR_check').is(':checked'),
					"coberturaDT_check" : $('#coberturaDT_check').is(':checked'),
					"coberturaRC_check" : $('#coberturaRC_check').is(':checked'),

					// Planes
					"paquete1_check" : $('#paquete1_check').is(':checked'),
					"paquete2_check" : $('#paquete2_check').is(':checked'),
					"paquete3_check" : $('#paquete3_check').is(':checked'),
					"paquete4_check" : $('#paquete4_check').is(':checked'),
					"paquete5_check" : $('#paquete5_check').is(':checked'),
					"coberturasAdicionales" : coberturas,
					"listaExtrasIds" : $('#listaExtrasIds' + numeroVeces.toString()).val(),
					"listaExtrasDetalles" : $('#listaExtrasDetalles' + numeroVeces.toString()).val(),
					"listaExtrasValores" : $('#listaExtrasValores' + numeroVeces.toString()).val(),
					"codigoTipoVehiculo" : $("#tipo_vehiculo" + numeroVeces.toString()).val(),
					"codigoTipoUso" : $("#tipo_uso" + numeroVeces.toString()).val(),
					"codigoTipoAdquisicion" : $("#tipo_adquisicion" + numeroVeces.toString()).val(),
					"tonelaje" : $("#tonelaje" + numeroVeces.toString()).val(),
					"valoresExtras" : valoresExtras
				},
				type : 'post',
				datatype : 'json',
				success : function (data) {

					$("#vehiculoId" + numeroVeces.toString()).val(data.vehiculoId);
					$('#extrasVehiculo' + numeroVeces.toString()).show();
					$('#eliminarVehiculo' + numeroVeces.toString()).show();
					$('#tasaVehiculo' + numeroVeces.toString()).val(data.tasaVehiculo);
					if ($('#numero_vehiculos').val() + "" == numeroVeces + "")
						vehiculosCotizacion();
					habilitarDesabilitarDescuentos();
				}
			});
		}	
}

/*
 * HABILITA O DESHABILITA LA PESTANA DE DESCUENTOS EN BASE A LA
 * TASA DEVUELTA POR EL MOTOR DE TARIFICACION
 */
function habilitarDesabilitarDescuentos() {

	$.ajax({
		url : '../DescuentoController',
		data : {		
			"tipoConsulta" : "mostrarDescuentoMinimoVH"
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
		
			var tasas = $(".tasaVehiculo");
			var total = 0;

			for (var i = 0; i < tasas.length; i++) {
				total += Number($(tasas).val());
			}
			var descuentoMinimo = datos.valor_minimo_mostrar_descuentos;
			if (Number(total / tasas.length) < Number(descuentoMinimo))
				$("#linkDescuentos").hide();
			else
				$("#linkDescuentos").show();
		}
	});

}

function abrirCertificado() {
	var cotizacion = $("#cotizacion_id").html().trim();
	var path = "/static/reportes/CertificadosVehiculos/";
	if (casoEspecial)
		path += "CertificadoCotizacionCasosEspeciales/certificadoVhc.jasper";
	else
		path += "nuevaCotizacion/certificadoVhc.jasper";
	var parametros = {
		"parametros" : {
			"COTIZACION" : cotizacion
		},
		"pathReporte" : path
	};
	abrirReporte('POST', '../ReportesController', parametros, "_blank");
}

function abrirCertificadoBorrador() {
	var cotizacion = $("#cotizacion_id").html().trim();
	var path = "/static/reportes/CertificadosVehiculos/CertificadoCotizacion/certificadoVhc.jasper";			
	var parametros = {
		"parametros" : {
			"COTIZACION" : cotizacion
		},
		"pathReporte" : path
	};
	abrirReporte('POST', '../ReportesController', parametros, "_blank");
}

function agregarCorreoCertificado() {
	var correo = $('#correoCertificado').val().trim();
	if (IsEmail(correo)) {
		$('#correosCertificado tr:last').after('<tr><td class="correoAEnviarCertificado">' + correo + '</td><td><button type="button" class="btn btn-alert eliminarCorreoEnvioCertificados" >Eliminar</button></td></tr>');
		$('#correoCertificado').val('');
	} else
		alert('Ingrese un correo Valido');
	$(".eliminarCorreoEnvioCertificados").click(function () {
		$(this).parent().parent().remove();
	});

}

function enviarCertificados() {
	$("#loading_envio_cotizacion").fadeIn('slow');
	var correos = $(".correoAEnviarCertificado");
	var correosEnviar = "";
	var cotizacion = $("#cotizacion_id").html().trim();
	var bandera = true;

	for (var index = 0; index < correos.length; index++) {
		if (IsEmail($(correos[index]).html().trim()))
			correosEnviar += $(correos[index]).html().trim() + ",";
		else {
			alert('Ingrese correos validos!');
			bandera = false;
			break;
		}
	}

	if (correosEnviar.trim().length == 0) {
		bandera = false;
		alert('Ingrese un correo!');
		$("#loading_envio_cotizacion").fadeOut('slow');
	}
	if (bandera)
		$.ajax({
			url : '../CotizacionController',
			data : {
				"correos" : correosEnviar,
				"id" : cotizacion,
				"tipoConsulta" : "enviarCertificado",
				"casoEspecial" : casoEspecial
			},
			type : 'post',
			datatype : 'json',
			success : function (datos) {
				alert('Se enviaron los certificados');
				$("#correos_certificado").dialog('close');
				$("#loading_envio_cotizacion").fadeOut('slow');
			}
		});
}

function cambioDescargaCertificados() {

	var valor = $("#selectDescargas").val();
	if (valor == 1) {
		$(".descargaCertificado").fadeOut("slow");
		$("#descargar_condicionesParticulares").fadeIn("slow");

	}
	if (valor == 2) {
		$(".descargaCertificado").fadeOut("slow");
		$("#descargar_certificadoDebito").fadeIn("slow");

	}
	if (valor == 3) {
		$(".descargaCertificado").fadeOut("slow");
		$("#descargar_certificadoUPLA").fadeIn("slow");

	}
}

/*
 * ACTIVA LOS FORMULARIOS DE LAS INSPECCIONES DEPENDIENDO DE LO QUE ELIJA EL USUARIO
 */
function cambioTipoInspeccion(tipoInspeccion) {
	$("#forma_inspeccion_externa").hide();
	$("#forma_inspeccion_interna").hide();
	if (tipoInspeccion == "externa") {
		$("#forma_inspeccion_externa").show();
	}
	if (tipoInspeccion == "interna") {
		if ($("#inspectorInterno").val() == null || $("#inspectorInterno").val() == "")
			cargarInspectoresInternas();
		$("#forma_inspeccion_interna").show();
	}
}

function cambioFormaPago(valor, limpiar) {
	$("#tarjetaNumero").next().next().hide();
	$("#msgPopupPago").fadeOut("slow");
	var formaPago = valor;

	if (limpiar) {
		limpiaForm(forma_debitos);
		limpiaForm(forma_tarjeta);
		limpiaForm(forma_cuotas);
	}

	if (formaPago == '1') {
		$('#forma_tarjeta').fadeOut("slow");
		$('#forma_cuotas').fadeOut("slow");
		$('#forma_debitos').fadeOut("slow");
		$("#save-pagoContado").show();
	}

	if (formaPago == '2') { //Corresponde a Debitos Bancos
		$("#detallePagoCuotas").empty();
		$("#rowDetallePagos").hide();
		$("#save-pagoContado").hide();
		$('#forma_tarjeta').fadeOut("slow");
		$('#forma_cuotas').fadeOut("slow");
		$('#forma_debitos').fadeIn("slow");
	}

	if (formaPago == '3') { //Corresponde a Debitos Tarjetas
		$("#detallePagoCuotas").empty();
		$("#rowDetallePagos").hide();
		$("#save-pagoContado").hide();
		$('#forma_cuotas').fadeOut("slow");
		$('#forma_debitos').fadeOut("slow");
		$('#forma_tarjeta').fadeIn("slow");
	}

	if (formaPago == '4') { //Corresponde a Cuotas
		$("#save-pagoContado").hide();
		$('#forma_tarjeta').fadeOut("slow");
		$('#forma_debitos').fadeOut("slow");
		$('#forma_cuotas').fadeIn("slow");
	}
}

/**
 * @param valor
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
		var validacion = false;
		
		$("#save-pagoDebitoBanco").parent().parent().parent().find(':input').each(function () {
			var elemento = this;
			var tipoIdentificacion = $("#tipo_identificacion_banco").val(); 
			
			if(elemento.name="nombreCompletoTitularDebitos" && elemento.value == "" && tipoIdentificacion !="4")
				validacion = true;
			if(elemento.name="nombresTitularDebitos" && elemento.value == "" && tipoIdentificacion =="4")
				validacion = true;
			if(elemento.name="apellidosTitularDebitos" && elemento.value == "" && tipoIdentificacion =="4")
				validacion = true;
			
			if (elemento.value == "" && elemento.type != "button" && validacion == false) {
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
					"nombresTitularDebitos": $("#nombresTitularDebitos").val(),
					"apellidosTitularDebitos":$("#apellidosTitularDebitos").val(),
					"nombreCompletoTitularDebitos":$("#nombreCompletoTitularDebitos").val(),					
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
		var validacion = false;
		$("#save-pagoDebitoTarjeta").parent().parent().parent().find(':input').each(function () {
			var elemento = this;
			var tipoIdentificacion = $("#tipo_identificacion_tarjeta").val();
			
			if(elemento.name="nombreCompletoTitularTarjetas" && elemento.value == "" && tipoIdentificacion !="4")
				validacion = true;
			if(elemento.name="nombresTitularTarjetas" && elemento.value == "" && tipoIdentificacion =="4")
				validacion = true;
			if(elemento.name="apellidosTitularTarjetas" && elemento.value == "" && tipoIdentificacion =="4")
				validacion = true;
			
			if (elemento.value == "" && elemento.type != "button" && validacion == false) {
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
					"nombresTitularTarjetas": $("#nombresTitularTarjetas").val(),
					"apellidosTitularTarjetas":$("#apellidosTitularTarjetas").val(),
					"nombreCompletoTitularTarjetas":$("#nombreCompletoTitularTarjetas").val(),					
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
/*
 * CARGA EXTRAS QUE SE PUEDEN AÃƒâ€˜ADIR AL VEHICULO
 */
function cargarExtrasVehiculos(numero, extras) {
	var listaExtrasIds = "";
	var listaExtrasDetalles = "";
	var listaExtrasValores = "";
	var valorTotalExtras = 0;

	$.each(extras, function (index) {
		$("#extras_" + numero).append('<tr>' +
			'<td class="sorting"><input type="text" id="valorTipo_' + numero + '_' + extras[index].id + '" readonly class="valorExtra' + numero + '" value="' + extras[index].nombreTipoExtra + '" source="' + extras[index].tipoExtraId + '"></td>' +
			'<td class="sorting"><input type="text" id="valorDetalle_' + numero + '_' + extras[index].id + '" readonly value=' + extras[index].descripcion + '></td>' +
			'<td class="sorting"><input type="text" class="valorExtras_' + numero + ' valor_extras"  id="valorValor_' + numero + '_' + extras[index].id + '" readonly value=' + extras[index].valor + '></td>' +
			'<td class="sorting">' +
			'<button type="button" class="btn btn-danger btn-xs eliminar-extra-btn">' +
			' <span class="glyphicon glyphicon glyphicon-remove"></span> Eliminar' +
			' </button></td>' +
			'</tr>');

		listaExtrasIds = listaExtrasIds + "," + extras[index].tipoExtraId;
		listaExtrasDetalles = listaExtrasDetalles + "," + extras[index].descripcion;
		listaExtrasValores = listaExtrasValores + "," + extras[index].valor;
		valorTotalExtras = parseFloat(valorTotalExtras) + parseFloat(extras[index].valor);
	});

	$("#listaExtrasIds" + numero).val(listaExtrasIds);
	$("#listaExtrasDetalles" + numero).val(listaExtrasDetalles);
	$("#listaExtrasValores" + numero).val(listaExtrasValores);

	$(".eliminar-extra-btn").bind({
		click : function () {
			$(this).parent().parent().remove();
			numeroExtasAuto = parseInt($("#contadorExtras" + numero).val()) - parseInt(1);
			$("#contadorExtras" + numero).val(numeroExtasAuto);
		}
	});
}

function abrirCertificadoDebito() {
	var cotizacion = $("#cotizacion_id").html().trim();
	var pathReporte = "/static/reportes/CertificadosVehiculos/AutorizacionDebito/autorizacionDebito.jasper";

	var parametros = {
		"parametros" : {
			"COTIZACION" : cotizacion
		},
		"pathReporte" : pathReporte
	};
	abrirReporte('POST', '../ReportesController', parametros, "_blank");
}

function abrirCertificadoTodos() {
	var cotizacion = $("#cotizacion_id").html().trim();
	var pathReporte = "/static/reportes/CertificadosVehiculos/todoCertificado.jasper";

	var parametros = {
		"parametros" : {
			"COTIZACION" : cotizacion
		},
		"pathReporte" : pathReporte
	};
	abrirReporte('POST', '../ReportesController', parametros, "_blank");
}

function abrirCertificadoUPLA() {
	var cotizacion = $("#cotizacion_id").html().trim();
	var pathReporte = "/static/reportes/CertificadosVehiculos/UPLA/conozcaASuClienteNaturalVacio.jasper";

	if ($("#tipo_identificacion_principal").val() == '4')
		pathReporte = "/static/reportes/CertificadosVehiculos/UPLA/conozcaASuClienteJuridicaVacio.jasper";

	var parametros = {
		"parametros" : {
			"COTIZACION_ID" : cotizacion
		},
		"pathReporte" : pathReporte
	};
	abrirReporte('POST', '../ReportesController', parametros, "_blank");
}

function abrirCertificadoPoliza() {
	var parametros = {
		"parametros" : {
			"tipoConsulta" : "reporteModificatoriosEnsurance"
		}
	};
	abrirReporte('POST', '../ReportesController', parametros, "_blank");
}

function abrirCertificadoCondicionesParticulares() {
	var cotizacion = $("#cotizacion_id").html().trim();
	var pathReporte = "/static/reportes/CertificadosVehiculos/CertificadoCondicionesParticulares/certificadoCondicionesParticulares.jasper";

	var parametros = {
		"parametros" : {
			"COTIZACION" : cotizacion
		},
		"pathReporte" : pathReporte
	};
	abrirReporte('POST', '../ReportesController', parametros, "_blank");
}

/*
 * CARGA LOS LUGARES DE DESTINO DE LA INSPECCCION
 */
function cargarDestinoInspeccion(origen, seleccionado) {
	$("#detalleInspectoresDisponibles").html("");
	$.ajax({
		url : '../DistanciaInspectorController',
		data : {
			"tipoConsulta" : "encontrarDestinoByOrigen",
			"origen" : origen
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			var listadoDestinoInspector = datos.listadoDestinoInspector;
			$("#destinoInspeccion").empty().append('<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>');
			$.each(listadoDestinoInspector, function (index) {
				if ($("#destinoInspeccion").html().indexOf(listadoDestinoInspector[index].destino) == -1)
					$("#destinoInspeccion").append('<option value="' + listadoDestinoInspector[index].destino + '">' + listadoDestinoInspector[index].destino + '</option>');
			});
			if (seleccionado != null && seleccionado != '')
				$("#destinoInspeccion").val(seleccionado).attr("disabled", "disabled");
		}
	});
}

/*
 * CARGA EL LISTADO DE VEHICULOS EN LA PANTALLA DE EMISION
 */
function cargarTablaVehiculosFinal() {
	var cotizacion = $("#cotizacion_id").html().trim();

	$.ajax({
		url : '../CotizacionController',
		data : {
			"id" : cotizacion,
			"tipoConsulta" : "cargarTablaVehiculos"
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			if (datos.success) {
				$('#tablaFinalSoloVehiculos').html('');
				$('#tablaFinalSoloVehiculos').append('<tr><th>Placa</th><th>Motor</th><th></th><th>Chasis</th><th></th><th>Marca</th><th>Modelo</th><th>Valor</th></tr>');
				var vehiculos = datos.listadoVehiculos;
				for (var i = 0; i < vehiculos.length; i++) {
					cambioTablaFinal[i] = false;
					var placa = '<td ><input type="hidden" value="' + vehiculos[i].id + '" id="idTablaFinal' + i + '" class="idTablaFinal"><input onkeypress="return validarSoloLetrasNumeros(event);" style="text-transform:uppercase" type="text" id="placaTablaFinal' + i + '" class="placaTablaFinal" maxlength="15" onChange=" cambioTablaFinal[' + i + ']=true; $(\'#emision_poliza\').attr(\'disabled\',\'disabled\');  consultarDatosVehiculo($(this).val(),\'tablaFinalVehiculos\','+ i +');" value="' + vehiculos[i].placa + '"></td>';
					var motor = '<td colspan="2"><input style="text-transform:uppercase" type="text" onkeypress="return validarSoloLetrasNumeros(event);" id="motorTablaFinal' + i + '" class="motorTablaFinal" value="' + vehiculos[i].motor + '" onChange=" cambioTablaFinal[' + i + ']=true; $(\'#emision_poliza\').attr(\'disabled\',\'disabled\');" ></td>';
					var chasis='<td colspan="2"><input style="text-transform:uppercase" onkeypress="return validarSoloLetrasNumeros(event);" type="text" id="chasisTablaFinal'+i+'" class="chasisTablaFinal" value="'+vehiculos[i].chasis+'" onChange=" cambioTablaFinal['+i+']=true; $(\'#emision_poliza\').attr(\'disabled\',\'disabled\'); consultarDatosVehiculo($(this).val(),\'tablaFinalVehiculos\','+ i +'); "></td>';
					var marca = '<td><input type="text" class="marcaTablaFinal" disabled="disabled" id="marcaTablaFinal' + i + '" value="' + vehiculos[i].marca + '"></td>';
					var modelo = '<td><input type="text" class="modeloTablaFinal" disabled="disabled" id="modeloTablaFinal' + i + '" value="' + vehiculos[i].modelo + '"></td>';
					var valor = '<td><input type="text" class="valorTablaFinal" disabled="disabled" value="' + vehiculos[i].valor + '"></td>';
					$('#tablaFinalSoloVehiculos tr:last').after('<tr>' + placa + motor + chasis + marca + modelo + valor + '></tr>');
				}
			}
		}
	});
}

/*
 * CARGA LA SOLICITU DE INSPECCION CON EL ID RECIBIDO
 */
function cargarSolicitudInspeccionPorId(id) {
	$.ajax({
		url : '../SolicitudInspeccionController',
		data : {
			"tipoConsulta" : "encontrarPorCotizacionId",
			"codigoCotizacion" : id
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			var solicitudInspeccion = datos.solicitudInspeccion;
			if (solicitudInspeccion != null) {
				if (solicitudInspeccion.inspectorId == null) { //interna
					$("#tipoInspeccion").val('interna').trigger('change');
					//$("#solicitudInspeccionId").val(solicitudInspeccion.id);
					//$("#inspectorInterno").val(solicitudInspeccion.inspectorId);
				} else {
					if (solicitudInspeccion.tipoInspector == '1') {
						$("#tipoInspeccion").val('interna').trigger('change').attr("disabled", "disabled");
						$("#solicitudInspeccionId").val(solicitudInspeccion.id).attr("disabled", "disabled");
						//$("#inspectorInterno").val(solicitudInspeccion.inspectorId).attr("disabled", "disabled");
						$("#boton-actualizar-inspeccion").show();
						cargarInspectoresInternas(solicitudInspeccion.inspectorId);
						//$("#estadoActualSolicitudInspeccionInterna").fadeIn('slow');
						if (solicitudInspeccion.estado) {
							$("#textoEstadoInspeccionInterna").html(solicitudInspeccion.estado);
							$("#estadoActualSolicitudInspeccionInterna").fadeIn("slow");
							$("#save-inspeccion-interna").fadeOut('slow');
							$("#actualizar-estado-solicitud-inspeccion-interna").fadeIn('slow');
							$("a[href='#next']").click();
						}
					} else {
						//externa
						$("#tipoInspeccion").val('externa').trigger('change').attr("disabled", "disabled");
						if (solicitudInspeccion.telefonoContacto)
							$("#numContactoInspeccion").val(solicitudInspeccion.telefonoContacto).attr("disabled", "disabled");
						if (solicitudInspeccion.origen)
							cargarOrigenInspeccion(solicitudInspeccion.origen);
						if (solicitudInspeccion.origen)
							cargarDestinoInspeccion(solicitudInspeccion.origen, solicitudInspeccion.destino);
						if (solicitudInspeccion.zona)
							$("#zonaInspeccion").val(solicitudInspeccion.zona).attr("disabled", "disabled");
						if (solicitudInspeccion.estado) {
							$("#textoEstadoInspeccion").html(solicitudInspeccion.estado);
							$("#estadoActualSolicitudInspeccion").fadeIn("slow");
							$("#save-inspeccion").fadeOut('slow');
							$("#boton-actualizar-inspeccion").fadeIn('slow');
						}
						cargarProveedoresDisponibles(solicitudInspeccion.destino, solicitudInspeccion.inspectorId, solicitudInspeccion.origen, 'Externo');
						$("#solicitudInspeccionId").val(solicitudInspeccion.id);
						$("#save-inspeccion").hide().attr("disabled", "disabled");
						$("#boton-actualizar-inspeccion").show();
						$("#actualizar-estado-solicitud-descuento").show();
						$("a[href='#next']").click();

					}
				}
			}
		}
	});
}

/*
 * CARGA DATOS DE INSPECTORES INTERNOS
 */
function cargarInspectoresInternas(seleccionada) {
	$.ajax({
		url : '../CotizacionController',
		data : {
			"tipoConsulta" : "cargarInspectoresInternos"
		},
		type : 'POST',
		datatype : 'json',
		success : function (data) {
			var inspectores = data.listadoInspectoresInternos;
			var options = "";
			for (var i = 0; i < inspectores.length; i++) {
				options += "<option value='" + inspectores[i].id + "'>" + "Interna " + inspectores[i].nombre + "</option>";
			}
			if (seleccionada == null)
				$("#inspectorInterno").html(options);
			else
				$("#inspectorInterno").html(options).val(seleccionada);
		}
	});
}

/* evaldez
 *
 */
var cambioTablaFinal = [];

function confirmarDatosVehiculos() {

	$("#confirmarDatos").attr("disabled", "disabled");

	cargarEstadoInspeccion();

	$("#loading_tablaFinal").fadeIn('slow');
	var placas = $(".placaTablaFinal");
	var chasiss = $(".chasisTablaFinal");
	var motors = $(".motorTablaFinal");
	var marcas = $('.marcaTablaFinal');
	var modelos = $('.modeloTablaFinal');
	var ids = $('.idTablaFinal');
	var vigenciaDesde = $("#fecha_inicio_vigencia").val();
	var mostrarEndosoBeneficiario = $("#beneficiario").select2("val") != "";
	var bandera=true;
	if (vigenciaDesde != null) {

		if (vigenciaDesde == "") {
			alert('Escoja una vigencia de la poliza!');
			$("#confirmarDatos").removeAttr("disabled");
			$("#fecha_inicio_vigencia").focus();
			bandera = false;
		}

	}
	
	if (bandera) {

		$.ajax({
			url : '../CotizacionController',
			data : {
				"tipoConsulta" : "actualizarFechaInicioVigencia",
				"vigenciaDesde" : vigenciaDesde,
				"cotizacionId" : $("#cotizacion_id").html().trim()

			},
			type : 'POST',
			datatype : 'json',
			success : function (data) {
				if (!data.success) {
					alert(data.error);
					$("#confirmarDatos").removeAttr("disabled");
					bandera = false;
					$("#loading_tablaFinal").fadeOut('slow');

					} else {
						var placasStr="";
						var chasisStr="";
						var motorStr="";
						var modeloStr="";
						var marcaStr="";
						var idStr="";
						
						for (var i = 0; i < placas.length && bandera; i++) {
							if ($(chasiss[i]).val().trim().length == 0) {
								alert('Ingrese un chasis valido!');
								$("#confirmarDatos").removeAttr("disabled");
								$(chasiss[i]).focus();
								bandera = false;
								break;
							}

							/*Comentar para consulta a corpaire*/
							if ($(motors[i]).val().trim().length == 0) {
							alert('Ingrese un motor valido!');
							$("#confirmarDatos").removeAttr("disabled");
							$(motors[i]).focus();
							bandera = false;
							break;
						}
						if ($(placas[i]).val().trim().length == 0 || $(placas[i]).val().trim().length < 5 ) {
							alert('Ingrese una placa valida!');
							$("#confirmarDatos").removeAttr("disabled");
							$(placas[i]).focus();
							bandera = false;
							break;
						}
						
						placasStr+=$(placas[i]).val().trim()+";";
						chasisStr+=$(chasiss[i]).val().trim()+";";
						motorStr+=$(motors[i]).val().trim()+";";
						modeloStr+=$(modelos[i]).val().trim()+";";
						marcaStr+=$(marcas[i]).val().trim()+";";
						idStr+=$(ids[i]).val().trim()+";";

					}
						if(bandera)
						$.ajax({
							url : "../ObjetoVehiculoController",
							data : {
								"placas" : placasStr,
								"chasiss" : chasisStr,
								"motors" : motorStr,
								"modelos" : modeloStr,
								"marcas" : marcaStr,
								"ids" : idStr,
								"cotizacionId" : $("#cotizacion_id").html().trim(),
								"tipoConsulta" : "validarDatosVehiculos"
							},
							type : 'post',
							datatype : 'json',
							success : function (data) {
								if (!data.success){
									alert(data.error);
									$("#loading_tablaFinal").fadeOut("slow");
									$("#confirmarDatos").removeAttr("disabled");	
								}
								else {
									if(data.fechaInicioVigencia==null||data.fechaInicioVigencia=="")
										emisionVH();
									else{
										var r = confirm(data.mensaje);
										if (r == true) {
											emisionVH();
										}
									}
								}



							}
						});	
						
				}
			}
			});
		}
	}


function actualizarVigenciaDesde(){
	var vigenciaDesde = $("#fecha_inicio_vigencia").val();
	$.ajax({
		url : '../CotizacionController',
		data : {
			"tipoConsulta" : "actualizarFechaInicioVigencia",
			"vigenciaDesde" : vigenciaDesde,
			"cotizacionId" : $("#cotizacion_id").html().trim()

		},
		type : 'POST',
		datatype : 'json',
		success : function (data) {
			
		}
	});
}


function cambiaZonaDireccion(event, tipo) {
	var target = event.target || event.srcElement;
	var seleccionada = $(target).val();
	if (seleccionada == "U") {
		if (tipo == 'solicitante') {
			$("#fila_ciudad_direccion_solicitante").fadeIn("slow");
			$("#fila_canton_direccion_solicitante").fadeOut("slow");
			$("#fila_parroquia_direccion_solicitante").fadeOut("slow");
		}
		if (tipo == 'asegurado') {
			$("#fila_ciudad_direccion_asegurado").fadeIn("slow");
			$("#fila_canton_direccion_asegurado").fadeOut("slow");
			$("#fila_parroquia_direccion_asegurado").fadeOut("slow");
		}
	}
	if (seleccionada == "R") {
		if (tipo == 'solicitante') {
			$("#fila_ciudad_direccion_solicitante").fadeOut("slow");
			$("#fila_canton_direccion_solicitante").fadeIn("slow");
			$("#fila_parroquia_direccion_solicitante").fadeIn("slow");
		}
		if (tipo == 'asegurado') {
			$("#fila_ciudad_direccion_asegurado").fadeOut("slow");
			$("#fila_canton_direccion_asegurado").fadeIn("slow");
			$("#fila_parroquia_direccion_asegurado").fadeIn("slow");
		}
	}
}

function guardarDatosUPLANatural() {
	
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
	var identificacion = $("#identificacion").val();
	var genero = $("#genero_cliente_natural").val();
	var mail = $("#mail_cliente_natural").val();
	var cotizacion = $("#cotizacion_id").text();

	$.ajax({
		url : '../UPLAController',
		data : {
			"identificacion" : identificacion,
			"tipoConsulta" : "guardarDatosNatural",
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
			"genero" : genero,
			"mail" : mail,
			"cotizacion" : cotizacion
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {}
	});
}

function actualizarProgramaSeguros() {
	var tiene_programa_seguros = $('#emision_programa_seguros').is(':checked');

	$.ajax({
		url : '../CotizacionController',
		data : {
			"cotizacionId" : $("#cotizacion_id").text(),
			"tipoConsulta" : "emisionProgramaSeguros",
			"tienePrograma" : tiene_programa_seguros,
		},
		type : 'POST',
		datatype : 'json',
		success : function (data) {
			if (data.resultado_programa == "1")
				console.log("Tiene Programa de Seguros");
			else
				console.log("No Tiene Programa de Seguros");
		}
	});
}

/*
 * METODO QUE VALIDA EL POCENTAJE DE LA SUMA ASEGURADA
 */
function validarSumaAsegurada(valor) {
	if (valor == null)
		valor = "";
	var sumaAsegurada = Number($('#porcentaje_suma_asegurada' + valor).val());
	var valorDefectoSumaAsegurada = Number($('#suma_asegurada_valor_tr').val());
	if (sumaAsegurada < (valorDefectoSumaAsegurada - Number('0.5'))) {
		alert('La Suma Asegurada debe ser mayor o igual a: ' + (valorDefectoSumaAsegurada - Number('0.5')));
	}
}

/*
 * METODO QUE VALIDA EL PORCENTAJE DEL SINIESTRO
 */
function validarPorcentajeSiniestro() {
	var valorSiniestro = Number($('#valor_siniestro').val());
	var valorDefectoValorSiniestro = Number($('#siniestro_valor_tr').val());
	if (valorSiniestro < valorDefectoValorSiniestro) {
		alert('El Porcentaje del siniestro debe ser mayor o igual a: ' + valorDefectoValorSiniestro);
	}
}

/*
 * METODO QUE VALIDA EL MONTO FIJO
 */
function validarMontoFijo() {	
	var valorMontoFijo = Number($('#monto_fijo').val());
	var valorMontoFijoDefecto = Number($('#monto_fijo_valor_tr').val());
	if (valorMontoFijo < valorMontoFijoDefecto) {
		alert('El Porcentaje del Monto Fijo debe ser mayor o igual a: ' + valorMontoFijoDefecto);
	}
}


/*
 * METODO QUE VALIDA EL VALOR DEL DEDUCIBLE RC
 */
function validarDeducibleRC() {	
	var valorDeducibleRC = Number($('#deducible_rc').val());
	var valorDeducibleRCDefecto = Number($('#deducible_rc_defecto').val());
	if (valorDeducibleRC < valorDeducibleRCDefecto) {
		alert('El Porcentaje del deducible de RC debe ser mayor o igual a: ' + valorDeducibleRCDefecto);
	}
}

/*
 * METODO QUE VALIDA EL VALOR DEL DEDUCIBLE DANO TOTAL
 */
function validarDanoTotal() {	
	var valorDanoTotal = Number($('#porcentaje_suma_aseguradaDT').val());
	var valorDanoTotalDefecto = Number($('#deducible_perdida_total_defecto').val());
	if (valorDanoTotal < valorDanoTotalDefecto) {
		alert('El Porcentaje del deducible de Dano Total debe ser mayor o igual a: ' + valorDanoTotalDefecto);
		return false;
	}
}

/*
 * METODO QUE ACEPTA O RECHAZA UNA SOLICITU DE DESCUENTO
 */
function confirmarDescuento(event) {

	var descuentoId = $("#codigo_descuento").val().trim();
	var porcentaje = Number($("#porcentaje_solicitud_descuento").val().trim());
	var min = Number($("#porcentaje_solicitud_descuento").attr("min"));
	var max = Number($("#porcentaje_solicitud_descuento").attr("max"));
	var motivoId = $("#motivo_descuento").val().trim();
	var descripcion = $("#descripcion_motivo_descuento").val().trim();
	var punto_venta = $('#punto_venta').val().trim();
	var cotizacion = $("#cotizacion_id").text().trim();

	$(".msgerr").remove();

	if (descuentoId.length <= 0) {
		$("#codigo_descuento").css({
			"background" : "rgb(251, 227, 228)",
			"border" : "1px solid #fbc2c4",
			"color" : "#8a1f11"
		}).after("<span class='msgerr'><br><b style='color:#8a1f11;'>Campo Requerido</b></span>");
	}

	if ((porcentaje + '').length <= 0) {
		$("#porcentaje_solicitud_descuento").css({
			"background" : "rgb(251, 227, 228)",
			"border" : "1px solid #fbc2c4",
			"color" : "#8a1f11"
		}).after("<span class='msgerr'><br><b style='color:#8a1f11;'>Campo Requerido</b></span>");
	}
	if (porcentaje > max) {
		$("#porcentaje_solicitud_descuento").css({
			"background" : "rgb(251, 227, 228)",
			"border" : "1px solid #fbc2c4",
			"color" : "#8a1f11"
		}).after("<span class='msgerr'><br><b style='color:#8a1f11;'>El valor es superior al mÃƒÂ¡ximo</b></span>");
	}
	if (porcentaje < min) {
		$("#porcentaje_solicitud_descuento").css({
			"background" : "rgb(251, 227, 228)",
			"border" : "1px solid #fbc2c4",
			"color" : "#8a1f11"
		}).after("<span class='msgerr'><br><b style='color:#8a1f11;'>El valor es menor al m&iacute;nimo</b></span>");
	}
	if (motivoId.length <= 0) {
		$("#motivo_descuento").css({
			"background" : "rgb(251, 227, 228)",
			"border" : "1px solid #fbc2c4",
			"color" : "#8a1f11"
		}).after("<span class='msgerr'><br><b style='color:#8a1f11;'>Campo Requerido</b></span>");
	}

	if (descripcion.length <= 0) {
		$("#descripcion_motivo_descuento").css({
			"background" : "rgb(251, 227, 228)",
			"border" : "1px solid #fbc2c4",
			"color" : "#8a1f11"
		}).after("<span class='msgerr'><br><b style='color:#8a1f11;'>Campo Requerido</b></span>");
	}

	if (punto_venta.length <= 0) {
		$("#punto_venta").css({
			"background" : "rgb(251, 227, 228)",
			"border" : "1px solid #fbc2c4",
			"color" : "#8a1f11"
		}).after("<span class='msgerr'><br><b style='color:#8a1f11;'>Campo Requerido</b></span>");
	}

	if (cotizacion.length <= 0) {
		$("#cotizacion_id").css({
			"background" : "rgb(251, 227, 228)",
			"border" : "1px solid #fbc2c4",
			"color" : "#8a1f11"
		}).after("<span class='msgerr'><br><b style='color:#8a1f11;'>Campo Requerido</b></span>");
	}
	if (descuentoId.length > 0 && (porcentaje + '').length > 0 && motivoId.length > 0 && descripcion.length > 0 && punto_venta.length > 0 && descuentoId.length > 0 && cotizacion.length > 0 && porcentaje <= max && porcentaje >= min) {
		$("#codigo_descuento").attr("disabled", "disabled");
		$("#porcentaje_solicitud_descuento").attr("disabled", "disabled");
		$("#motivo_descuento").attr("disabled", "disabled");
		$("#descripcion_motivo_descuento").attr("disabled", "disabled");
		$("#punto_venta").attr("disabled", "disabled");
		$("#cotizacion_id").attr("disabled", "disabled");
		$("#loading_solicitud_descuento").fadeIn('slow');
		
		
		if ($("#codigoPagoRegistrado").val() != null && $("#codigoPagoRegistrado").val() != ""&& $("#codigoPagoRegistrado").val() != "-1") {
			var r = confirm("Si solicita un descuento se eliminara la forma de pago! Desea continuar?");
			if (r == true) {
				eliminarPago();
				agregarSolicitudDescuento(descripcion, porcentaje, descuentoId, punto_venta, cotizacion, motivoId);
			}
			else{
				$("#codigo_descuento").removeAttr("disabled");
				$("#porcentaje_solicitud_descuento").removeAttr("disabled");
				$("#motivo_descuento").removeAttr("disabled");
				$("#descripcion_motivo_descuento").removeAttr("disabled");
				$("#punto_venta").removeAttr("disabled");
				$("#cotizacion_id").removeAttr("disabled");
				
				$("#loading_solicitud_descuento").fadeOut('slow');
			}
		}
		else
			{
			agregarSolicitudDescuento(descripcion, porcentaje, descuentoId, punto_venta, cotizacion, motivoId);
			}
	}
}

/*
 * METODO PARA CANCELAR UNA SOLICITUD DE DESCUENTO
 */
function cancelarDescuentoClick(event) {
	$("#dialog-descuento").dialog("close");
	$("#porcentaje_solicitud_descuento").val('');
	$("#motivo_solicitud_descuento").val('');
	$("#descripcion_motivo_descuento").val('');
}

/*
 * METODO QUE CREA UNA SOLICITU DE DESCUENTO
 */
function agregarSolicitudDescuento(descripcion, porcentaje, descuento, punto_venta, cotizacion, motivoId) {
	var target = $("#enviar_solicitud_descuento");

	target.attr('disabled', 'disabled');
	$.ajax({
		url : '../CotizacionController',
		data : {
			//"nombre":nombre,
			"descripcion" : descripcion,
			"porcentaje" : porcentaje,
			"descuento" : descuento,
			"cotizacion" : cotizacion,
			"punto_venta" : punto_venta,
			"motivoId" : motivoId,
			"tipoConsulta" : "agregarSolicitudDescuento"
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			if (data.success) {
				tieneDescuento = true;
				$("#loading_solicitud_descuento").fadeOut('slow');
				$(target).fadeOut("slow"); //attr('disabled','disabled');
				$("#estado_solicitud_descuento").fadeIn("slow");
				$("#boton_actualizar_solicitud_descuento").fadeIn("slow");
				alert('Se envio la solicitud de descuento!');
			} else {
				alert('No se envio la solicitud de descuento!');
				$("#loading_solicitud_descuento").fadeOut('slow');
				$(target).fadeIn("slow");
			}
		}
	});
}

/*
 * METODO QUE VALIDA EL RANGO DE DECUENTO EN BASE AL DESCUENTO
 * SELECCIONADO
 */
function cambiaDescuento() {
	var tipo = $("#codigo_descuento").val();
	var aux = $("#codigo_descuento option:selected").text();
	//aux = aux.split('(')[1].replace(')', '').replace('%', '');
	var min = Number($("#codigo_descuento option:selected").attr('minimo'));
	var max = Number($("#codigo_descuento option:selected").attr('maximo'));
	$("#porcentaje_solicitud_descuento").val(min).attr('min', min).attr('max', max);

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
 * CARGA LOS INSPECTORES EN LA PANTALLA DE ACUERDO A LOS PARAMETROS RECIBIDOS
 */
function cargarProveedoresDisponibles(destino, seleccionado, origen, tipo) {
	var origenEnviar = $("#origenInspeccion").val();
	var zona = $("#zonaInspeccion").val();
	var tipoConsulta = 'buscarInspectoresDisponibles';
	if (origen != null || origen == "")
		origenEnviar = origen;
	if (tipo == 'Externo')
		tipoConsulta = 'buscarInspectoresExternosDisponibles';
	else
		tipoConsulta = 'buscarInspectoresInternosDisponibles';
	$.ajax({
		url : '../DistanciaInspectorController',
		data : {
			"tipoConsulta" : "buscarInspectoresDisponibles",
			"origen" : origenEnviar,
			"destino" : destino,
			"zona" : zona,
			"cotizacionId" : $("#cotizacion_id").text().trim()
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {
			var listadoInspectoresDisponibles = datos.listadoInspectoresDisponibles;
			var aux = '';

			if (datos.mostrarCostos) {
				aux += '<tr style="background-color:#428bca; color:#ffffff;">' +
				'<td>PROVEEDOR</td>' +
				'<td>HONORARIOS</td>' +
				'<td>GASTOS</td>' +
				'<td>TOTAL</td>' +
				'<td>SELECCION</td>' +
				'</tr>';
			} else {
				aux += '<tr style="background-color:#428bca; color:#ffffff;">' +
				'<td colspan="2">PROVEEDOR</td>' +
				'<td colspan="2">SELECCION</td>' +
				'</tr>';
			}

			$("#detalleInspectoresDisponibles").empty().append(aux);
			$.each(listadoInspectoresDisponibles, function (index) {
				if (listadoInspectoresDisponibles[index].honorarios != "-1") {
					var aux2 = "";
					if (datos.mostrarCostos) {
						aux2 += '<tr>' +
						'<td>' + listadoInspectoresDisponibles[index].nombreInspector + '</td>';

						aux2 += '<td>' + listadoInspectoresDisponibles[index].honorarios.toFixed(2) + '</td>' +
						'<td>' + listadoInspectoresDisponibles[index].gastos.toFixed(2) + '</td>' +
						'<td>' + (parseFloat(listadoInspectoresDisponibles[index].gastos) + parseFloat(listadoInspectoresDisponibles[index].honorarios)).toFixed(2) + '</td>';
						aux2 += '<td ><input type="radio" class="proveedorSeleccionado" name="proveedorSeleccionado" value="' + listadoInspectoresDisponibles[index].codigoInspector + '"></td>' +
						'</tr>';
					} else {
						aux2 += '<tr>' +
						'<td>' + listadoInspectoresDisponibles[index].nombreInspector + '</td>';
						aux2 += '<td hidden="hidden">' + listadoInspectoresDisponibles[index].honorarios.toFixed(2) + '</td>' +
						'<td hidden="hidden">' + listadoInspectoresDisponibles[index].gastos.toFixed(2) + '</td>' +
						'<td hidden="hidden">' + (parseFloat(listadoInspectoresDisponibles[index].gastos) + parseFloat(listadoInspectoresDisponibles[index].honorarios)).toFixed(2) + '</td>';
						aux2 += '<td colspan="4"><input type="radio" class="proveedorSeleccionado" name="proveedorSeleccionado" value="' + listadoInspectoresDisponibles[index].codigoInspector + '"></td>' +
						'</tr>';
					}

					$("#detalleInspectoresDisponibles").append(aux2);
				} else {
					$("#detalleInspectoresDisponibles").append('<tr>' +
						'<td>' + listadoInspectoresDisponibles[index].nombreInspector + '</td>' +
						'<td colspan="4" style="font-size:small;">ESTE PROVEEDOR NO TIENE COBERTURA EN EL SECTOR</td>' +
						'</tr>');
				}

			});
			if (seleccionado != null && seleccionado != '') {
				$.each($(".proveedorSeleccionado"), function (index) {
					if ($($(".proveedorSeleccionado")[index]).val() == seleccionado)
						$($(".proveedorSeleccionado")[index]).prop('checked', true);
				});
			}
		}
	});
}

/*
 * CREA UNA SOLICITUD DE INSPECCION EXTERNA
 * RELACIONADA A LA COTIZACION
 */
function guardarSolicitudInspeccion() {
	var bandera = true;
	//$("#save-inspeccion").hide();
	//$("#estadoActualSolicitudInspeccion").show();
	$(".requeridoInspeccion").css("background-color", "#ffffff");
	if ($("#tipoInspeccion").val().trim() == "externa" && bandera) {
		$(".requeridoInspeccion").each(function () {
			if ($(this).val() == "" && bandera) {
				alert("Por favor ingrese los campos requeridos");
				$(this).css("background-color", "#fbc2c4");
				$(this).focus();
				bandera = false;
			}
		});
	}

	if ($("#numContactoInspeccion").val().trim().substr(0, 2) == "09" && bandera) {
		if (parseInt($("#numContactoInspeccion").val().trim().length) != 10) {
			alert("Un numero celular debe contener 10 digitos. Por favor ingrese un numero de celular valido");
			$("#numContactoInspeccion").val("");
			$("#numContactoInspeccion").css("background-color", "#fbc2c4");
			$("#numContactoInspeccion").focus();
			bandera = false;
		}
	} else {
		if (parseInt($("#numContactoInspeccion").val().trim().length) != 9 && bandera) {
			alert("Un numero convencional debe contener 9 digitos. Por favor ingrese un numero convencional valido");
			$("#numContactoInspeccion").val("");
			$("#numContactoInspeccion").css("background-color", "#fbc2c4");
			$("#numContactoInspeccion").focus();
			bandera = false;
		}
		if ($("#numContactoInspeccion").val().trim().substr(0, 2) == "00" && bandera) {
			alert("El numero no puede empezar con 00");
			$("#numContactoInspeccion").val("");
			$("#numContactoInspeccion").css("background-color", "#fbc2c4");
			$("#numContactoInspeccion").focus();
			bandera = false;
		}
	}

	var proveedorSeleccionado = 0;
	var valorInspeccion = "";
	$(".proveedorSeleccionado").each(function () {
		if ($(this).is(':checked')) {
			proveedorSeleccionado = $(this).val();
			valorInspeccion = $(this).parent().prev().text()
		}
	});

	if (proveedorSeleccionado == 0 && bandera) {
		alert("Seleccione un proveedor para que realice la inspeccion");
		bandera = false;
	}

	if ($("#zonaInspeccion").val() == "" && bandera) {
		alert("Seleccione la zona de la inspeccion");
		bandera = false;
	}

	var tipoConsulta = "crear";
	if ($("#solicitudInspeccionId").val() != "")
		tipoConsulta = "actualizar";

	if (bandera) {
		$("#save-inspeccion").fadeOut("slow");
		$("#loading_solicitud_inspeccion").fadeIn("slow");
		$.ajax({
			url : '../SolicitudInspeccionController',
			data : {
				"tipoConsulta" : tipoConsulta,
				"codigoInspector" : proveedorSeleccionado,
				"telfContacto" : $("#numContactoInspeccion").val(),
				"origenInspeccion" : $("#origenInspeccion").val(),
				"destinoInspeccion" : $("#destinoInspeccion").val(),
				"valorInspeccion" : valorInspeccion,
				"codigoCotizacion" : $("#cotizacion_id").text(),
				"zona" : $("#zonaInspeccion").val(),
				"enviarCorreo" : "si",
				"codigo" : $("#solicitudInspeccionId").val()
			},
			type : 'post',
			datatype : 'json',
			success : function (datos) {
				if (datos.success) {
					$("#solicitudInspeccionId").val(datos.codigoSolicitudInspeccion);
					$("#msgPopupInspeccionGrabo").show();
					$("#estadoActualSolicitudInspeccion").fadeIn("slow");
					$("#textoEstadoInspeccion").html("Pendiente");
					$("#boton-actualizar-inspeccion").fadeIn("slow");
					$("#loading_solicitud_inspeccion").fadeOut("slow");
				} else {
					alert(datos.error);
					$("#loading_solicitud_inspeccion").fadeOut("slow");
					$("#save-inspeccion").fadeIn("slow");
				}
			}
		});
	}
}

/*
 * CREA UNA SOLICITUD DE INSPECCION INTERNA
 * RELACIONADA A LA COTIZACION
 */
function guardarSolicitudInspeccionInterna() {
	var bandera = true;
	if ($("#tipoInspeccion").val().trim() == "interna" && bandera) {
		$(".requeridoInspeccionInterna").each(function () {
			if ($(this).val() == "" && bandera) {
				alert("Por favor ingrese los campos requeridos");
				$(this).css("background-color", "#fbc2c4");
				$(this).focus();
				bandera = false;
			}
		});
	}
	var tipoConsulta = "crear";
	var proveedorSeleccionado = $("#inspectorInterno").val();
	if ($("#solicitudInspeccionId").val() != "")
		tipoConsulta = "actualizar";
	$("#save-inspeccion-interna").fadeOut("slow");
	$("#loading_solicitud_inspeccionInterna").fadeIn("slow");
	if (bandera)
		$.ajax({
			url : '../SolicitudInspeccionController',
			data : {
				"tipoConsulta" : tipoConsulta,
				"codigoInspector" : proveedorSeleccionado,
				"telfContacto" : "",
				"origenInspeccion" : "",
				"destinoInspeccion" : "",
				"valorInspeccion" : "",
				"codigoCotizacion" : $("#cotizacion_id").text(),				
				"codigo" : $("#solicitudInspeccionId").val()
			},
			type : 'post',
			datatype : 'json',
			success : function (datos) {
				if (datos.success) {
					$("#solicitudInspeccionId").val(datos.codigoSolicitudInspeccion);
					$("#textoEstadoInspeccionInterna").html("Pendiente");
					$("#estadoActualSolicitudInspeccionInterna").fadeIn("slow");
					$("#actualizar-estado-solicitud-descuento").fadeIn("slow");
					$("#loading_solicitud_inspeccionInterna").fadeOut("slow");
					$("#actualizar-estado-solicitud-inspeccion-interna").fadeIn('slow');
					$("#msgPopupInspeccionGrabo").show();
				} else {

					$("#save-inspeccion-interna").fadeIn("slow");
					$("#loading_solicitud_inspeccionInterna").fadeOut("slow");
				}
			}
		});
}

/*
 * VALIDA LOS DATOS DEL VEHICULO QUE INGRESO EL USUARIO CONTRA LOS DATOS
 * DE LA ANT
 */
function validarPlacaFinal(valor, numero) {
	var retorno = true;
	$("#loading_tablaFinal").fadeIn('slow');

	valor = valor.trim();
	if (valor.length >= 5 && valor.length <= 7) {
		$("#emision_poliza").attr('disabled', 'disabled');
		var motor = $('#motorTablaFinal' + numero).val();
		var chasis = $('#chasisTablaFinal' + numero).val();
		var marca = $('#marcaTablaFinal' + numero).val();
		var modelo = $('#modeloTablaFinal' + numero).val();
		$("#loading_tablaFinal").fadeIn('slow');
		$.ajax({
			url : '../CotizacionController',
			data : {
				"tipoConsulta" : "consultarGeneral",
				"placa" : valor
			},
			type : 'POST',
			datatype : 'json',
			success : function (data) {
				var today = new Date();
				var vigencia = new Date(data.vigenciaEnsurance);

				if ((Date.parse(vigencia)) >= (Date.parse(today))) {
					var dd = vigencia.getDate();
					var mm = vigencia.getMonth() + 1; //January is 0!
					var yyyy = vigencia.getFullYear();

					if (dd < 10) {
						dd = '0' + dd;
					}

					if (mm < 10) {
						mm = '0' + mm;
					}

					var fechaVigenciaPoliza = dd + "/" + mm + "/" + yyyy;

					alert("El vehiculo ingresado tiene una poliza vigente hasta el " + fechaVigenciaPoliza + " (dd/mm/YYYY).  No puede cotizar este vehiculo.");
					retorno = false;
				}
				if (motor != data.motor.trim() && retorno) {
					alert('El campo motor del vehiculo de placas ' + valor + ' no coincide con los datos de la ANT. Por favor revisar');
					$("#emision_poliza").attr('disabled', 'disabled');
					retorno = false;
				}
				if (marca.indexOf(data.marca.trim().split(' ')[0]) == -1 && retorno) {
					alert('El campo marca del vehiculo de placas ' + valor + ' no coincide con los datos de la ANT. Por favor revisar');
					$("#emision_poliza").attr('disabled', 'disabled');
					retorno = false;
				}
				if (modelo.indexOf(data.modelo.trim().split(' ')[0]) == -1 && retorno) {
					alert('El campo modelo del vehiculo de placas ' + valor + ' no coincide con los datos de la ANT. Por favor revisar');
					$("#emision_poliza").attr('disabled', 'disabled');
					retorno = false;
				}
				if (chasis != data.chasis.trim() && retorno) {
					alert('El campo chasis del vehiculo de placas ' + valor + ' no coincide con los datos de la ANT. Por favor revisar');
					$("#emision_poliza").attr('disabled', 'disabled');
					retorno = false;
				}
				$("#loading_tablaFinal").fadeOut('slow');

				//return retorno;
				if (retorno) {
					$("#emision_poliza").removeAttr('disabled');
					//$("#loading_tablaFinal").fadeOut('slow');
				}
			}
		});
	}
}

/* evaldez
 * CONSULTAR CON ESTEFANO PARA QUE SIRVE ESTE METODO
 */
function solicitarInspeccion(event) {
	var target = event.target || event.srcElement;
	var valor = $(target).val();

	if (valor == '1')
		solicitarInspeccion = true;
}

/*
 * Metodo para realizar la emision de una poliza
 */
function emisionVH() {

	$("#loading_tablaFinal").fadeIn('slow');
	$("#emision_poliza").attr('disabled', 'disabled');
	var opcion = $('#tipo_identificacion_principal').val();
	var tipoIdentificacionConyugue;
	var identificacionConyugue;
	var nombreConyugue;
	var apellidoPConyugue;
	var apellidoMConyugue;
	var tipoIdentificacionConyugue;

	var tipoIdentificacionAsegurado;
	var identificacionAsegurado;
	var nombreAsegurado;
	var apellidoPAsegurado;
	var apellidoMAsegurado;
	var tipoIdentificacionAsegurado;

	var tipoIdentificacionBeneficiario;
	var identificacionBeneficiario;
	var nombreBeneficiario;
	var apellidoPBeneficiario;
	var apellidoMBeneficiario;
	var tipoIdentificacionBeneficiario;
	var fechaEnviar = $("#fecha_inicio_vigencia").val().split("-")[2] + "/" + $("#fecha_inicio_vigencia").val().split("-")[1] + "/" + $("#fecha_inicio_vigencia").val().split("-")[0];

	if (opcion == 1 || opcion == 2 || opcion == 3) {
		//natural

	} else if (opcion == 4) {
		//juridica
	}

	$.ajax({
		url : '../EmisionVHController',
		data : {
			"tipoConsulta" : "emisionPoliza",
			"producto" : tipoObjeto,
			"cotizacionId" : $("#cotizacion_id").text(),
			"agenteId" : $("#agentes").val(),
			"ramoNemotecnico" : "VP",
			"fechaInicioVigencia" : fechaEnviar
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			$("#loading_tablaFinal").fadeOut('slow');
			if (data.success) {
				//alert(data.respuesta + "Mensaje, " + data.respuesta.split('::')[0].length);
				if (data.numeroFactura == null) {
					alert(data.respuesta);
				} else {
					if (data.numeroFactura.trim() != "" && !$("#emision_programa_seguros").is(':checked')) {
						alert('La poliza fue Emitida, el numero de factura es ' + data.numeroFactura);
						bloquearEmitido();
					} else {
						alert('No se pudo emitir: ' + data.respuesta);
						$("#confirmarDatos").removeAttr("disabled");
					}
				}
			}
			else
				alert(data.error);
		}
	});


}

/*
 * OBTIENE TASA POR PRODUCTO
 */
function obtenerTasaPorProducto(tasa) {
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

			if (tiene_tasa == 1 && es_formulada == 0) {
				$("#tasa").val(data.tasa_fija_valor);
				$("#tasas").hide();
				$("#tasa").show();
			}
			if (tiene_tasa == 0 && es_formulada == 0) {
				$("#tasa").val("");
				$("#tasa").hide();
				$("#tasas").show();

				var listadoTasas = data.tasas_listado;
				$("#tasas").empty();
				$("#tasas").append("<option value=''>Seleccione una opcion</option>");
				if (listadoTasas != undefined) {
					$.each(listadoTasas, function (index) {
						$("#tasas").append("<option value='" + listadoTasas[index].id + "'>" + listadoTasas[index].nombre + "</option>");
					});
				}
				$("#tasas").val(tasa);
			}
			if (tiene_tasa == 0 && es_formulada == 1) {
				$("#tasa").val("Formulada");
				$("#tasas").hide();
				$("#tasa").show();
			}
		}
	});
}

function cargarDatosUPLAJuridica(datosJuridica) {

	var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	options += '<option value="0">&nbsp;&nbsp;NO </option>';
	options += '<option value="1">&nbsp;&nbsp;SI </option>';

	var optionsZona = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	optionsZona += '<option value="U">&nbsp;&nbsp;Urbana </option>';
	optionsZona += '<option value="R">&nbsp;&nbsp;Rural </option>';

	var tipoActividad = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
	tipoActividad += '<option value="Privado">Empleado Privado</option>';
	tipoActividad += '<option value="Publico">Empleado PÃƒÂºblico</option>';
	tipoActividad += '<option value="Independiente">Independiente</option>';
	tipoActividad += '<option value="Jubilado">Jubilado</option>';

	if (datosJuridica.objetoSocial != null)
		$("#objeto_social_juridica").val(datosJuridica.objetoSocial);
	else
		$("#objeto_social_juridica").val("");

	if (datosJuridica.ciudadJuridica != null)
		$("#ciudad_juridica").val(datosJuridica.ciudadJuridica);
	else
		$("#ciudad_juridica").val("");

	if (datosJuridica.zonaDireccionMatriz != null)
		$("#zona_direccion_matriz_juridica").html(optionsZona).val(datosJuridica.zonaDireccionMatriz).attr('required', 'required').trigger('change');
	else
		$("#zona_direccion_matriz_juridica").html(optionsZona).val("").attr('required', 'required');

	if (datosJuridica.provinciaDireccionMatriz != null)
		cargarProvincias(datosJuridica.provinciaDireccionMatriz, "direccion_matriz_juridica");
	else
		cargarProvincias("", "direccion_matriz_juridica");

	if (datosJuridica.zonaDireccionMatriz == "R") {
		if (datosJuridica.cantonDireccionMatriz != null) {
			if (datosJuridica.provinciaDireccionMatriz != null)
				cargarCantones(datosJuridica.cantonDireccionMatriz, datosJuridica.provinciaDireccionMatriz, "direccion_matriz_juridica");
		} else {
			if (datosJuridica.provinciaDireccionMatriz != null)
				cargarCantones("", datosJuridica.provinciaDireccionMatriz, "direccion_matriz_juridica");
		}

		if (datosJuridica.parroquiaDireccionMatriz != null) {
			if (datosJuridica.cantonDireccionMatriz != null)
				cargarParroquias(datosJuridica.parroquiaDireccionMatriz, datosJuridica.cantonDireccionMatriz, "direccion_matriz_juridica");
		} else {
			if (datosJuridica.cantonDireccionMatriz != null)
				cargarParroquias("", datosJuridica.cantonDireccionMatriz, "direccion_matriz_juridica");
		}

	} else {
		if (datosJuridica.ciudadDireccionMatriz != null) {
			if (datosJuridica.provinciaDireccionMatriz != null)
				cargarCiudades(datosJuridica.ciudadDireccionMatriz, datosJuridica.provinciaDireccionMatriz, "direccion_matriz_juridica");
		} else {
			if (datosJuridica.provinciaDireccionMatriz != null)
				cargarCiudades("", datosJuridica.provinciaDireccionMatriz, "direccion_matriz_juridica");
		}
	}

	if (datosJuridica.callePrincipalMatriz != null)
		$("#calle_principal_direccion_juridica").val(datosJuridica.callePrincipalMatriz);
	else
		$("#calle_principal_direccion_juridica").val("");

	if (datosJuridica.numeroDireccionMatriz != null)
		$("#numero_direccion_juridica").val(datosJuridica.numeroDireccionMatriz);
	else
		$("#numero_direccion_juridica").val("");

	if (datosJuridica.calleSecundariaMatriz != null)
		$("#calle_secundaria_direccion_juridica").val(datosJuridica.calleSecundariaMatriz);
	else
		$("#calle_secundaria_direccion_juridica").val("");

	if (datosJuridica.referenciaDireccionMatriz != null)
		$("#referencia_direccion_juridica").val(datosJuridica.referenciaDireccionMatriz);
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

	//actividad persona natural

	if (datosJuridica.actividadJuridica != null)
		cargarActividades(datosJuridica.actividadJuridica, "juridica");
	else
		cargarActividades("", "juridica");

	if (datosJuridica.nombresRepresentanteLegal != null)
		$("#nombres_representante_juridica").val(datosJuridica.nombresRepresentanteLegal);
	else
		$("#nombres_representante_juridica").val("");

	if (datosJuridica.apellidosRepresentanteLegal != null)
		$("#apellidos_representante_juridica").val(datosJuridica.apellidosRepresentanteLegal);
	else
		$("#apellidos_representante_juridica").val("");

	if (datosJuridica.tipoIdentificacionRepresentante != null)
		cargarTiposIdentificacion(datosJuridica.tipoIdentificacionRepresentante, "representante_juridica", true);
	else
		cargarTiposIdentificacion("", "representante_juridica", false);

	if (datosJuridica.identificacionRepresentante != null)
		$("#identificacion_representante_juridica").val(datosJuridica.identificacionRepresentante);
	else
		$("#identificacion_representante_juridica").val("");

	if (datosJuridica.lugarNacimientoRepresentante != null)
		$("#lugar_nacimiento_representante_juridica").val(datosJuridica.lugarNacimientoRepresentante);
	else
		$("#lugar_nacimiento_representante_juridica").val("");

	if (datosJuridica.fechaNacimiento != null) {
		var dia = datosJuridica.fechaNacimiento.date < 10 ? '0' + datosJuridica.fechaNacimiento.date : datosJuridica.fechaNacimiento.date;
		var mes = datosJuridica.fechaNacimiento.month < 9 ? "0" + (datosJuridica.fechaNacimiento.month + 1) : (datosJuridica.fechaNacimiento.month + 1);
		var anio = (1900 + datosJuridica.fechaNacimiento.year);
		var aux = '' + anio + '-' + mes + '-' + dia;

		$("#fecha_nacimiento_representante_juridica").val(aux);
	} else
		$("#fecha_nacimiento_representante_juridica").val("");

	if (datosJuridica.residenciaRepresentante != null)
		$("#residencia_representante_juridica").val(datosJuridica.residenciaRepresentante);
	else
		$("#residencia_representante_juridica").val("");

	if (datosJuridica.provinciaRepresentante != null)
		cargarProvincias(datosJuridica.provinciaRepresentante, "representante_juridica");
	else
		cargarProvincias("", "representante_juridica");	

	if (datosJuridica.ciudadRepresentante != null)
		cargarCiudades(datosJuridica.ciudadRepresentante, datosJuridica.provinciaRepresentante, "representante_juridica");
	else		
		cargarCiudades('', datosJuridica.provinciaRepresentante, "representante_juridica");

	if (datosJuridica.telefonoRepresentante != null)
		$("#telefono_representante_juridica").val(datosJuridica.telefonoRepresentante);
	else
		$("#telefono_representante_juridica").val("");

	if (datosJuridica.celularRepresentante != null)
		$("#celular_representante_juridica").val(datosJuridica.celularRepresentante);
	else
		$("#celular_representante_juridica").val("");

	if (datosJuridica.expuestoRepresentante != null)
		$("#expuesto_representante_juridica").html(options).val(datosJuridica.expuestoRepresentante ? 1 : 0);
	else
		$("#expuesto_representante_juridica").html(options).val("");

	if (datosJuridica.cargoExpuesta != null)
		$("#cargo_expuesta_representante_juridica").val(datosJuridica.cargoExpuesta);
	else
		$("#cargo_expuesta_representante_juridica").val("");

	if (datosJuridica.expuestoFamiliar != null)
		$("#expuesto_familiar_juridica").html(options).val(datosJuridica.expuestoFamiliar ? 1 : 0);
	else
		$("#expuesto_familiar_juridica").html(options).val("");

	if (datosJuridica.parentescoExpuestoFamiliar != null)
		$("#parentesco_expuesto_familiar_juridico").val(datosJuridica.parentescoExpuestoFamiliar);
	else
		$("#parentesco_expuesto_familiar_juridico").val("");

	if (datosJuridica.cargoExpuestoFamiliar != null)
		$("#cargo_expuesto_familiar_juridica").val(datosJuridica.cargoExpuestoFamiliar);
	else
		$("#cargo_expuesto_familiar_juridica").val("");

	if (datosJuridica.apellidoPaternoConyuge != null)
		$("#apellido_paterno_conyuge_juridica").val(datosJuridica.apellidoPaternoConyuge);
	else
		$("#apellido_paterno_conyuge_juridica").val("");

	if (datosJuridica.apellidoMaternoConyuge != null)
		$("#apellido_materno_conyuge_juridica").val(datosJuridica.apellidoMaternoConyuge);
	else
		$("#apellido_materno_conyuge_juridica").val("");

	if (datosJuridica.nombreConyuge != null)
		$("#nombre_conyuge_juridica").val(datosJuridica.nombreConyuge);
	else
		$("#nombre_conyuge_juridica").val("");

	if (datosJuridica.tipoIdentificacionConyuge != null)
		cargarTiposIdentificacion(datosJuridica.tipoIdentificacionConyuge, "conyugue_juridica", true);
	else
		cargarTiposIdentificacion("", "conyugue_juridica", false);

	if (datosJuridica.identificacionConyuge != null)
		$("#identificacion_conyuge_juridica").val(datosJuridica.identificacionConyuge);
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
		$("#es_asegurado_juridica").html(options).val(datosJuridica.esAsegurado ? 1 : 0);
	else
		$("#es_asegurado_juridica").html(options).val("");

	if (datosJuridica.esBeneficiario != null)
		$("#es_beneficiario_juridica").html(options).val(datosJuridica.esBeneficiario ? 1 : 0);
	else
		$("#es_beneficiario_juridica").html(options).val("");

	if (datosJuridica.tipoIdentificacionAsegurado != null)
		cargarTiposIdentificacion(datosJuridica.tipoIdentificacionAsegurado, "asegurado_juridica", true);
	else
		cargarTiposIdentificacion("", "asegurado_juridica", false);

	if (datosJuridica.identificacionAsegurado != null)
		$("#identificacion_asegurado_juridica").val(datosJuridica.identificacionAsegurado);
	else
		$("#identificacion_asegurado_juridica").val("");

	if (datosJuridica.nombreCompletoAsegurado != null)
		$("#nombre_asegurado_juridica").val(datosJuridica.nombreCompletoAsegurado);
	else
		$("#nombre_asegurado_juridica").val("");

	if (datosJuridica.direccionAsegurado != null)
		$("#direccion_asegurado_juridica").val(datosJuridica.direccionAsegurado);
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

	//beneficiario
	if (datosJuridica.tipoIdentificacionBeneficiario != null)
		cargarTiposIdentificacion(datosJuridica.tipoIdentificacionBeneficiario, "beneficiario_juridica", true);
	else
		cargarTiposIdentificacion("", "beneficiario_juridica", false);

	if (datosJuridica.identificacionBeneficiario != null)
		$("#identificacion_beneficiario_juridica").val(datosJuridica.identificacionBeneficiario);
	else
		$("#identificacion_beneficiario_juridica").val("");

	if (datosJuridica.nombreCompletoBeneficiario != null)
		$("#nombre_beneficiario_juridica").val(datosJuridica.nombreCompletoBeneficiario);
	else
		$("#nombre_beneficiario_juridica").val("");

	if (datosJuridica.direccionBeneficiario != null)
		$("#direccion_beneficiario_juridica").val(datosJuridica.direccionBeneficiario);
	else
		$("#direccion_beneficiario_juridica").val("");

	if (datosJuridica.telefonoBeneficiario != null)
		$("#telefono_beneficiario_juridica").val(datosJuridica.telefonoBeneficiario);
	else
		$("#telefono_beneficiario_juridica").val("");

	if (datosJuridica.celularBeneficiario != null)
		$("#celular_beneficiario_juridica").val(datosJuridica.celularBeneficiario);
	else
		$("#celular_beneficiario_juridica").val("");

	if (datosJuridica.relacionBeneficiario != null)
		$("#relacion_beneficiario_juridica").val(datosJuridica.relacionBeneficiario);
	else
		$("#relacion_beneficiario_juridica").val("");

	cargadoDatosUPLA = true;

}

function guardarDatosUPLAJuridica() {

	var objetoSocial = $("#objeto_social_juridica").val();
	//var ciudad = $("#ciudad_direccion_matriz_juridica").val();
	var zonaDireccionMatriz = $("#zona_direccion_matriz_juridica").val();
	var provinciaDireccionMatriz = $("#provincia_direccion_matriz_juridica").val();
	var cantonDireccionMatriz = $("#canton_direccion_matriz_juridica").val();
	var parroquiaDireccionMatriz = $("#parroquia_direccion_matriz_juridica").val();
	var ciudadDireccionMatriz = $("#ciudad_direccion_matriz_juridica").val();
	var callePrincipalDireccion = $("#calle_principal_direccion_juridica").val();
	var numeroDireccion = $("#numero_direccion_juridica").val();
	var calleSecundariaDireccion = $("#calle_secundaria_direccion_juridica").val();
	var referenciaDireccion = $("#referencia_direccion_juridica").val();	
	var telefono = $("#telefono_juridica").val();
	var fax = $("#fax_juridica").val();
	var identificacion = $("#identificacion").val();
	var genero = $("#genero_representante_juridico").val();
	var mail = $("#mail_representante_juridico").val();
	var cotizacion = $("#cotizacion_id").text();	

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
			"telefono" : telefono,
			"fax" : fax,
			"genero" : genero,
			"mail" : mail,		
			"cotizacion" : cotizacion
		},
		type : 'post',
		datatype : 'json',
		success : function (datos) {}
	});
}

function eliminarDescuento() {
	//se cambiara el estado a anulado, ya no se elimina
	var cotizacion = $("#cotizacion_id").html().trim();
	$("#codigo_descuento").val('').removeAttr('disabled');
	$("#motivo_descuento").val('').removeAttr('disabled');
	$("#porcentaje_solicitud_descuento").val('').removeAttr('disabled');
	$("#descripcion_motivo_descuento").val('').removeAttr('disabled');
	$("#estado_solicitud_descuento").fadeOut('slow').removeAttr('disabled');
	$("#enviar_solicitud_descuento").fadeIn('slow').removeAttr('disabled');
	$("#boton_actualizar_solicitud_descuento").fadeOut('slow').removeAttr('disabled');
	$.ajax({
		url : '../SolicitudDescuentoController',
		data : {
			"tipoConsulta" : "anularPorCotizacion",
			"cotizacionId" : cotizacion
		},
		type : 'POST',
		datatype : 'json',
		success : function (data) {
			tieneDescuento=false;
		}
	});

}

function eliminarPago() {
	$.ajax({
		url : '../PagoController',
		data : {
			"tipoConsulta" : "eliminar",
			"codigoPago" : $("#codigoPagoRegistrado").val()
		},
		type : 'POST',
		datatype : 'json',
		success : function (data) {
			$("#codigoPagoRegistrado").val(-1);
			validarValoresPagos();
		}
	});

}

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

function gruposProductos(grupoSeleccionado, producto, tasa, puntoVenta) {
	// Consultar listado de grupos productos
	$.ajax({
		url : '../GrupoPorProductoController',
		data : {
			"tipoConsulta" : "encontrarTodosGrupoProducto",
			"tipoObjeto" : tipoObjeto,
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var listadoGrupos = data.listadoGruposProducto;
			var ListadoGruposUnico = arregloUnicoJSON(listadoGrupos);
			
			if(listadoGrupos.length!=0){
				$("#grupo_productos").append("<option value=''>Seleccione una opcion</option>");
				$.each(ListadoGruposUnico, function (index) {
					$("#grupo_productos").append("<option value='" + ListadoGruposUnico[index].id + "'>" + ListadoGruposUnico[index].nombre + "</option>");
				});
				$("#grupo_productos").val(grupoSeleccionado);
				if (producto != null) {
					obtenerProductosGrupo(producto, tasa, puntoVenta);
				}	
			}
			
			if(listadoGrupos.length==0){
				$(".content").hide();
				$(".mensaje_no_producto").show();				
			}
			
		}
	});
}

function obtenerProductosGrupo(producto, tasa, puntoVenta) {
	// Consultar listado de productos dentro de un grupos de productos
	$("#productos").empty();

	$.ajax({
		url : '../GrupoPorProductoController',

		data : {
			"tipoConsulta" : "encontrarTodosPorGrupo",
			"tipoObjeto" : tipoObjeto,
			"grupoProductoId" : $("#grupo_productos").val()
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var grupos = data.listadoGruposPorProducto;
			var listadoGrupos = arregloUnicoJSON(grupos);
						
			$("#productos").append("<option value=''>Seleccione una opcion</option>");
			$.each(listadoGrupos, function (index) {
				var inspeccionRequerida = listadoGrupos[index].inspeccionRequerida;
				$("#productos").append("<option value='" + listadoGrupos[index].id + "' inspeccionRequerida='" + inspeccionRequerida + "' >" + listadoGrupos[index].nombre + "</option>");
			});
			$("#productos").val(producto);
			$("#codigoProductos").val(producto);
			if (producto != null) {
				cargarPuntosVenta(puntoVenta);
				obtenerTasaPorProducto(tasa);

			}
		}
	});
}

//Arreglo de elementos unicos JSON
function arregloUnicoJSON(obj) {
	var uniques = [];
	var stringify = {};
	for (var i = 0; i < obj.length; i++) {
		var keys = Object.keys(obj[i]);
		keys.sort(function (a, b) {
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

function limpiarTasaProducto() {
	$("#tasa").val('');
	$("#tasas").hide();
	$("#tasa").show();
}

function esconderFilaAsegurado() {
	var val = $("#tipo_identificacion_asegurado").val();
	if (val == '4') {
		$("#filaNaturalAsegurado").fadeOut('slow');
		$("#filaJuridicaAsegurado").fadeIn('slow');
	} else {
		$("#filaNaturalAsegurado").fadeIn('slow');
		$("#filaJuridicaAsegurado").fadeOut('slow');
	}
}

/*	GUARDAR EL ENDOSO DEL BENEFICIARIO  */

function guardarBeneficiario(tipoConsulta) {

	var beneficiario = $("#beneficiario").select2('val');
	var monto = $("#valor_endoso_beneficiario").val();
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
		if (Number(monto) <= 0 || Number(monto) > Number($("#total_suma_asegurada_vh").val())) {
			$("#mensajeErrorEndosoBeneficiario").html("El monto debe ser menor al valor asegurado y mayor a 0");
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
		$.ajax({
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
			success : function (data) {
				if (data.success) {
					$("#endoso_beneficiario_id").val(data.endosoBeneficiarioId);
					$("#mensajeGraboEndosoBeneficiario").html("Se guard&oacute; el beneficiario exitosamente");
					$("#msgPopupEndosoBeneficiarioGrabo").fadeIn("slow");
					$("#msgPopupEndosoBeneficiarioError").fadeOut("slow");
					if (tipoConsulta == 'eliminar') {
						$("#mensajeGraboEndosoBeneficiario").html("Se elimin&oacute; el beneficiario exitosamente");
						$("#endoso_beneficiario_id").val('');
						$("#valor_endoso_beneficiario").val('');
						$("#beneficiario").val('');
					}

				} else {
					$("#mensajeErrorEndosoBeneficiario").html("No se pudo guardar el beneficiario");
					$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
					$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
					if (tipoConsulta == 'eliminar')
						$("#mensajeGraboEndosoBeneficiario").html("No se pudo eliminar el beneficiario");

					alert(data.error);
				}
			}
		});
}

/*	GUARDAR EL ASEGURADO  */

function guardarAsegurado(tipoConsulta) {

	var tipoIdentificacionAsegurado = $("#tipo_identificacion_asegurado").val();
	var identificacionAsegurado = $("#identificacion_asegurado").val();
	var nombreAsegurado = $("#nombres_asegurado").val();
	var apellidoAsegurado = $("#apellidos_asegurado").val();
	var nombreCompletoAsegurado = $("#nombre_completo_asegurado").val();
	var cotizacionId = $("#cotizacion_id").text();
	var aseguradoId = $("#asegurado_id").val();
	var valido = true;

	if (tipoIdentificacionAsegurado == "" || tipoIdentificacionAsegurado == null) {
		$("#mensajeErrorEndosoBeneficiario").html("Seleccione un tipo de identificaci&oacute;n");
		$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
		$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
		$("#tipo_identificacion_asegurado").focus();
		valido = false;
	} else {
		if (tipoIdentificacionAsegurado != '4') {
			nombreCompletoAsegurado = apellidoAsegurado + ' ' + nombreAsegurado;
			if (nombreAsegurado == "" || nombreAsegurado == null) {
				$("#mensajeErrorEndosoBeneficiario").html("Ingrese el nombre del asegurado");
				$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
				$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
				$("#nombres_asegurado").focus();
				valido = false;
			}
			if (apellidoAsegurado == "" || apellidoAsegurado == null) {
				$("#mensajeErrorEndosoBeneficiario").html("Ingrese el apellido del asegurado");
				$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
				$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
				$("#apellidos_asegurado").focus();
				valido = false;
			}
		}
	}

	if (identificacionAsegurado == "" || identificacionAsegurado == null) {
		$("#mensajeErrorEndosoBeneficiario").html("Ingrese una identificaci&oacute;n");
		$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
		$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
		$("#identificacion_asegurado").focus();
		valido = false;
	}

	if (nombreCompletoAsegurado == "" || nombreCompletoAsegurado == null) {
		$("#mensajeErrorEndosoBeneficiario").html("Ingrese el nombre completo del asegurado");
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
	if (valido && tipoIdentificacionAsegurado != "2") {
		if (tipoIdentificacionAsegurado == "1")
			valido = validaCedula(identificacionAsegurado);
		if (tipoIdentificacionAsegurado == "3" || tipoIdentificacionAsegurado == "4")
			valido = validaRuc(identificacionAsegurado);
		$("#mensajeErrorEndosoBeneficiario").html("Ingrese una identificaci&oacute;n valida");
		$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
		$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
		$("#nombre_completo_asegurado").focus();

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
			success : function (data) {
				if (data.success) {
					$("#asegurado_id").val(data.aseguradoId);
					$("#mensajeGraboEndosoBeneficiario").html("Se guard&oacute; el asegurado exitosamente");
					$("#msgPopupEndosoBeneficiarioGrabo").fadeIn("slow");
					$("#msgPopupEndosoBeneficiarioError").fadeOut("slow");
					if (tipoConsulta == 'eliminar') {
						$("#mensajeGraboEndosoBeneficiario").html("Se elimin&oacute; el asegurado exitosamente");
						$("#asegurado_id").val('');
					}

				} else {
					$("#mensajeErrorEndosoBeneficiario").html("No se pudo guardar el asegurado");
					if (tipoConsulta == 'eliminar')
						$("#mensajeGraboEndosoBeneficiario").html("No se pudo eliminar el asegurado");
					$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
					$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
					alert(data.error);
				}
			}
		});
}

function abrirEndosoBeneficiario() {
	var cotizacion = $("#cotizacion_id").html().trim();

	var parametros = {
		"parametros" : {
			"cotizacionId" : cotizacion
		},
		"pathReporte" : "/static/reportes/CertificadosVehiculos/EndosoBeneficiario.jasper"
	};
	abrirReporte('POST', '../ReportesController', parametros, "_blank");
}

function abrirCoberturaProvisional() {
	var cotizacion = $("#cotizacion_id").html().trim();

	var parametros = {
		"parametros" : {
			"cotizacionId" : cotizacion
		},
		"pathReporte" : "/static/reportes/CertificadosVehiculos/CoberturaProvisional.jasper"
	};
	abrirReporte('POST', '../ReportesController', parametros, "_blank");
}

function cambioExcesoRC(id, n, event) {
	var t = event.target || event.srcElement;
	var tiempoVigencia = $("#vigencia").val();
	if (n == "")
		$("#valorUnicoResponsabilidadCivil").val($(t).val());
	var target = t;
	var check = $(target).attr('check');
	var valor = $(target).parent().prev().prev();
	var sumas = $(".suma_asegurada");
	var porcentaje = $(target).parent().prev();
	var valorExcesoRC = Number($(target).val());
	var valorMax = Number($(target).attr('max'));
	if (valorExcesoRC > valorMax) {
		alert("El valor no puede ser mayor a $" + valorMax);
		$(target).val(valorMax);
		$("#valorUnicoResponsabilidadCivil").val(valorMax);
	}
	//$(t).parent().prev().prev().prev().prev().prev().children().first()
	if (n != "") {
		if ($("#" + check).is(":checked")) {
			$(valor).html('$' + (tiempoVigencia * (Number($(porcentaje).val()) * valorExcesoRC / 100)) * sumas.length);
			calcularTotalSinPaquete(n);

		}
	} else {
		if ($(t).parent().prev().prev().prev().prev().prev().children().first().is(":checked")) {
			$(valor).html('$' + (tiempoVigencia * Number($(porcentaje).val()) * valorExcesoRC / 100) * sumas.length);
			calcularTotalSinPaquete(n);

		}
	}
}

function cargarDireccionFactura(formulario, datos) {
	if (datos != null) {
		if (formulario == "solicitante") {
			if ($("#tipo_identificacion_principal").val() == "4") {
				$("#nombre_direccion_solicitante").val($("#nombre_completo").val());
			} else {
				$("#nombre_direccion_solicitante").val($("#nombres").val() + " " + $("#apellidos").val());
			}
			$("#cedula_direccion_solicitante").val($("#identificacion").val());
			$("#telefono_direccion_solicitante").val(datos.telefono);
			$("#celular_direccion_solicitante").val(datos.celular);
			$("#mail_direccion_solicitante").val(datos.email);
			options = '';
			// Agregamos zonas
			options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
			options += '<option value="U">&nbsp;&nbsp;Urbana </option>';
			options += '<option value="R">&nbsp;&nbsp;Rural </option>';
			$("#zona_direccion_solicitante").html(options);
			if (datos.zona == null) {
				cargarProvincias(datos.provincia, "direccion_solicitante");
			} else {
				options = '';
				// Agregamos zonas
				options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
				options += '<option value="U">&nbsp;&nbsp;Urbana </option>';
				options += '<option value="R">&nbsp;&nbsp;Rural </option>';
				$("#zona_direccion_solicitante").html(options);

				if(datos.provincia==null)
					cargarProvincias("", "direccion_solicitante");
				else
					cargarProvincias(datos.provincia, "direccion_solicitante");
			}
			if (datos.zona == "1") {
				$("#zona_direccion_solicitante").val("U");
				cargarProvincias(datos.provincia, "direccion_solicitante");
				cargarCiudades(datos.ciudad, datos.provincia, "direccion_solicitante");
				$("#fila_ciudad_direccion_solicitante").fadeIn("slow");
				$("#fila_canton_direccion_solicitante").fadeOut("slow");
				$("#fila_parroquia_direccion_solicitante").fadeOut("slow");
				$("#principal_direccion_solicitante").val(datos.callePrincipal);
				$("#secundaria_direccion_solicitante").val(datos.calleSecundaria);
				$("#numero_direccion_solicitante").val(datos.numero);
				$("#referencia_direccion_solicitante").val(datos.datosReferencia);
			}
			if (datos.zona == "2") {
				$("#zona_direccion_solicitante").val("R");
				cargarProvincias(datos.provincia, "direccion_solicitante");
				cargarCantones(datos.canton, datos.provincia, "direccion_solicitante");
				$("#fila_ciudad_direccion_solicitante").fadeOut("slow");
				$("#fila_canton_direccion_solicitante").fadeIn("slow");
				cargarParroquias(datos.parroquia, datos.canton, "direccion_solicitante");
				$("#fila_parroquia_direccion_solicitante").fadeIn("slow");
				$("#principal_direccion_solicitante").val(datos.callePrincipal);
				$("#secundaria_direccion_solicitante").val(datos.calleSecundaria);
				$("#numero_direccion_solicitante").val(datos.numero);
				$("#referencia_direccion_solicitante").val(datos.datosReferencia);
			}
		}
		if (formulario == "asegurado") {
			if(datos.nombre!=null)
				$("#nombres_asegurado").val(datos.nombre);
			if(datos.apellido!=null)
				$("#apellidos_asegurado").val(datos.apellido);
			if(datos.identificacion!=null)
				$("#identificacion_asegurado").val(datos.identificacion);
			if(datos.tipoIdentifiacion!=null)
				$("#tipo_identificacion_asegurado").val(datos.tipoIdentifiacion);
			if (datos.tipoIdentifiacion == "4") {
				$("#nombre_direccion_asegurado").val($("#nombre_completo").val());
			} else {
				$("#nombre_direccion_asegurado").val($("#nombres").val() + " " + $("#apellidos").val());
			}
			$("#cedula_direccion_asegurado").val($("#identificacion").val());
			$("#telefono_direccion_asegurado").val(datos.telefono);
			$("#celular_direccion_asegurado").val(datos.celular);
			$("#mail_direccion_asegurado").val(datos.email);
			options = '';
			// Agregamos zonas
			options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
			options += '<option value="U">&nbsp;&nbsp;Urbana </option>';
			options += '<option value="R">&nbsp;&nbsp;Rural </option>';
			$("#zona_direccion_asegurado").html(options);
			if (datos.zona == null) {
				cargarProvincias(datos.provincia, "direccion_asegurado");
			}
			if (datos.zona == "1") {
				$("#zona_direccion_asegurado").val("U");
				cargarProvincias(datos.provincia, "direccion_asegurado");
				cargarCiudades(datos.ciudad, datos.provincia, "direccion_asegurado");
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
				cargarProvincias(datos.provincia, "direccion_asegurado");
				cargarCantones(datos.canton, datos.provincia, "direccion_asegurado");
				$("#fila_ciudad_direccion_asegurado").fadeOut("slow");
				$("#fila_canton_direccion_asegurado").fadeIn("slow");
				cargarParroquias(datos.parroquia, datos.canton, "direccion_asegurado");
				$("#fila_parroquia_direccion_asegurado").fadeIn("slow");
				$("#principal_direccion_asegurado").val(datos.callePrincipal);
				$("#secundaria_direccion_asegurado").val(datos.calleSecundaria);
				$("#numero_direccion_asegurado").val(datos.numero);
				$("#referencia_direccion_asegurado").val(datos.datosReferencia);
			}
		}
	}
	if ((datos == null || datos == "") && (formulario == "" || formulario == null)) {
		var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
		options += '<option value="U">&nbsp;&nbsp;Urbana </option>';
		options += '<option value="R">&nbsp;&nbsp;Rural </option>';
		$("#zona_direccion_asegurado").html(options);
		$("#zona_direccion_solicitante").html(options);

		cargarProvincias("", "direccion_asegurado");
		cargarProvincias("", "direccion_solicitante");
		if ($("#tipo_identificacion_principal").val() == "4") {
			$("#nombre_direccion_solicitante").val($("#nombre_completo").val());
		} else {
			$("#nombre_direccion_solicitante").val($("#nombres").val() + " " + $("#apellidos").val());
		}
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

function guardarDatosFactura() {

	if ($("#identificacion_asegurado").val().trim() != $("#identificacion").val().trim()) {

		var valido = true;

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
		if (zonaSolicitante == null || zonaSolicitante == "") {
			$(mostrarError).fadeIn("slow");
			$(mostrarExito).fadeOut("slow");
			$(mensajeError).html("Seleccione una zona de la direcci&oacute;n");
			$("#zona_direccion_solicitante").focus();
			valido = false;
		}

		if (valido)
			if (provinciaSolicitante == null || provinciaSolicitante == "") {
				$(mostrarError).fadeIn("slow");
				$(mostrarExito).fadeOut("slow");
				$(mensajeError).html("Seleccione una provincia de la direcci&oacute;n");
				$("#provincia_direccion_solicitante").focus();
				valido = false;
			}

		if (valido)
			if (zonaSolicitante == "R") {
				if (cantonSolicitante == null || cantonSolicitante == "") {
					$(mostrarError).fadeIn("slow");
					$(mostrarExito).fadeOut("slow");
					$(mensajeError).html("Seleccione un canton de la direcci&oacute;n");
					$("#canton_direccion_solicitante").focus();
				}
				if (parroquiaSolicitante == null || parroquiaSolicitante == "") {
					$(mostrarError).fadeIn("slow");
					$(mostrarExito).fadeOut("slow");
					$(mensajeError).html("Seleccione una provincia de la direcci&oacute;n");
					$("#parroquia_direccion_solicitante").focus();
				}
			}

		if (valido)
			if (zonaSolicitante == "U") {
				if (ciudadSolicitante == null || ciudadSolicitante == "") {
					$(mostrarError).fadeIn("slow");
					$(mostrarExito).fadeOut("slow");
					$(mensajeError).html("Seleccione una ciudad de la direcci&oacute;n");
					$("#ciudad_direccion_solicitante").focus();
				}
			}

		if (valido)
			if (principalSolicitante == null || principalSolicitante == "") {
				$(mostrarError).fadeIn("slow");
				$(mostrarExito).fadeOut("slow");
				$(mensajeError).html("Ingrese una calle principal");
				$("#principal_direccion_solicitante").focus();
				valido = false;
			}

		if (valido)
			if (secundariaSolicitante == null || secundariaSolicitante == "") {
				$(mostrarError).fadeIn("slow");
				$(mostrarExito).fadeOut("slow");
				$(mensajeError).html("Ingrese una calle secundaria");
				$("#secundaria_direccion_solicitante").focus();
				valido = false;
			}

		if (valido)
			if (numeroSolicitante == null || numeroSolicitante == "") {
				$(mostrarError).fadeIn("slow");
				$(mostrarExito).fadeOut("slow");
				$(mensajeError).html("Ingrese un n&uacute;mero de la direcci&oacute;n");
				$("#numero_direccion_solicitante").focus();
				valido = false;
			}

		if (valido)
			if (telefonoSolicitante == null || telefonoSolicitante == "") {
				$(mostrarError).fadeIn("slow");
				$(mostrarExito).fadeOut("slow");
				$(mensajeError).html("Ingrese un telefono");
				$("#telefono_direccion_solicitante").focus();
				valido = false;
			}

		if (valido)
			if (celularSolicitante == null || celularSolicitante == "") {
				$(mostrarError).fadeIn("slow");
				$(mostrarExito).fadeOut("slow");
				$(mensajeError).html("Ingrese un telefono del solicitante");
				$("#celular_direccion_solicitante").focus();
				valido = false;
			}

		if (valido)
			if (emailSolicitante == null || emailSolicitante == "") {
				$(mostrarError).fadeIn("slow");
				$(mostrarExito).fadeOut("slow");
				$(mensajeError).html("Ingrese un email del solicitante");
				$("#provincia_direccion_solicitante").focus();
				valido = false;
			}
		//fin validaciones solicitante

		// validaciones Asegurado
		if (zonaAsegurado == null || zonaAsegurado == "") {
			$(mostrarError).fadeIn("slow");
			$(mostrarExito).fadeOut("slow");
			$(mensajeError).html("Seleccione una zona de la direcci&oacute;n");
			$("#zona_direccion_asegurado").focus();
			valido = false;
		}

		if (valido)
			if (provinciaAsegurado == null || provinciaAsegurado == "") {
				$(mostrarError).fadeIn("slow");
				$(mostrarExito).fadeOut("slow");
				$(mensajeError).html("Seleccione una provincia de la direcci&oacute;n");
				$("#provincia_direccion_asegurado").focus();
				valido = false;
			}

		if (valido)
			if (zonaAsegurado == "R") {
				if (cantonAsegurado == null || cantonAsegurado == "") {
					$(mostrarError).fadeIn("slow");
					$(mostrarExito).fadeOut("slow");
					$(mensajeError).html("Seleccione un canton de la direcci&oacute;n");
					$("#canton_direccion_asegurado").focus();
				}
				if (parroquiaAsegurado == null || parroquiaAsegurado == "") {
					$(mostrarError).fadeIn("slow");
					$(mostrarExito).fadeOut("slow");
					$(mensajeError).html("Seleccione una provincia de la direcci&oacute;n");
					$("#parroquia_direccion_asegurado").focus();
				}
			}

		if (valido)
			if (zonaAsegurado == "U") {
				if (ciudadAsegurado == null || ciudadAsegurado == "") {
					$(mostrarError).fadeIn("slow");
					$(mostrarExito).fadeOut("slow");
					$(mensajeError).html("Seleccione una ciudad de la direcci&oacute;n");
					$("#ciudad_direccion_asegurado").focus();
				}
			}

		if (valido)
			if (principalAsegurado == null || principalAsegurado == "") {
				$(mostrarError).fadeIn("slow");
				$(mostrarExito).fadeOut("slow");
				$(mensajeError).html("Ingrese una calle principal");
				$("#principal_direccion_asegurado").focus();
				valido = false;
			}

		if (valido)
			if (secundariaAsegurado == null || secundariaAsegurado == "") {
				$(mostrarError).fadeIn("slow");
				$(mostrarExito).fadeOut("slow");
				$(mensajeError).html("Ingrese una calle secundaria");
				$("#secundaria_direccion_asegurado").focus();
				valido = false;
			}

		if (valido)
			if (numeroAsegurado == null || numeroAsegurado == "") {
				$(mostrarError).fadeIn("slow");
				$(mostrarExito).fadeOut("slow");
				$(mensajeError).html("Ingrese un n&uacute;mero de la direcci&oacute;n");
				$("#numero_direccion_asegurado").focus();
				valido = false;
			}

		if (valido)
			if (telefonoAsegurado == null || telefonoAsegurado == "") {
				$(mostrarError).fadeIn("slow");
				$(mostrarExito).fadeOut("slow");
				$(mensajeError).html("Ingrese un telefono");
				$("#telefono_direccion_asegurado").focus();
				valido = false;
			}

		if (valido)
			if (celularAsegurado == null || celularAsegurado == "") {
				$(mostrarError).fadeIn("slow");
				$(mostrarExito).fadeOut("slow");
				$(mensajeError).html("Ingrese un telefono del Asegurado");
				$("#celular_direccion_asegurado").focus();
				valido = false;
			}

		if (valido)
			if (emailAsegurado == null || emailAsegurado == "") {
				$(mostrarError).fadeIn("slow");
				$(mostrarExito).fadeOut("slow");
				$(mensajeError).html("Ingrese un email del Asegurado");
				$("#provincia_direccion_asegurado").focus();
				valido = false;
			}
		//fin validaciones Asegurado

		if (valido) {
			$.ajax({
				url : "../DireccionController",

				data : {
					"tipoConsulta" : "guardarDatosFacturaSolicitanteBeneficiario",
					"zonaSolicitante" : zonaSolicitante,
					"provinciaSolicitante" : provinciaSolicitante,
					"cantonSolicitante" : cantonSolicitante,
					"parroquiaSolicitante" : parroquiaSolicitante,
					"ciudadSolicitante" : ciudadSolicitante,
					"principalSolicitante" : principalSolicitante,
					"numeroSolicitante" : numeroSolicitante,
					"secundariaSolicitante" : secundariaSolicitante,
					"referenciaSolicitante" : referenciaSolicitante,
					"telefonoSolicitante" : telefonoSolicitante,
					"celularSolicitante" : celularSolicitante,
					"emailSolicitante" : emailSolicitante,
					"identificacionSolicitante" : identificacionSolicitante,
					"zonaAsegurado" : zonaAsegurado,
					"provinciaAsegurado" : provinciaAsegurado,
					"cantonAsegurado" : cantonAsegurado,
					"parroquiaAsegurado" : parroquiaAsegurado,
					"ciudadAsegurado" : ciudadAsegurado,
					"principalAsegurado" : principalAsegurado,
					"numeroAsegurado" : numeroAsegurado,
					"secundariaAsegurado" : secundariaAsegurado,
					"referenciaAsegurado" : referenciaAsegurado,
					"telefonoAsegurado" : telefonoAsegurado,
					"celularAsegurado" : celularAsegurado,
					"emailAsegurado" : emailAsegurado,
					"identificacionAsegurado" : identificacionAsegurado
				},
				type : 'post',
				datatype : 'json',
				success : function (data) {
					if (data.success) {
						$(mostrarExito).fadeIn("slow");
						$(mostrarError).fadeOut("slow");
						$(mensajeExito).html("Se guardaron las direcciones con exito");

					} else {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("No se pudieron guardar las direcciones, mensaje: " + data.error);

					}
				}
			});
		}
	} else {

		var valido = true;

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
		if (zonaSolicitante == null || zonaSolicitante == "") {
			$(mostrarError).fadeIn("slow");
			$(mostrarExito).fadeOut("slow");
			$(mensajeError).html("Seleccione una zona de la direcci&oacute;n");
			$("#zona_direccion_solicitante").focus();
			valido = false;
		}

		if (valido)
			if (provinciaSolicitante == null || provinciaSolicitante == "") {
				$(mostrarError).fadeIn("slow");
				$(mostrarExito).fadeOut("slow");
				$(mensajeError).html("Seleccione una provincia de la direcci&oacute;n");
				$("#provincia_direccion_solicitante").focus();
				valido = false;
			}

		if (valido)
			if (zonaSolicitante == "R") {
				if (cantonSolicitante == null || cantonSolicitante == "") {
					$(mostrarError).fadeIn("slow");
					$(mostrarExito).fadeOut("slow");
					$(mensajeError).html("Seleccione un canton de la direcci&oacute;n");
					$("#canton_direccion_solicitante").focus();
				}
				if (parroquiaSolicitante == null || parroquiaSolicitante == "") {
					$(mostrarError).fadeIn("slow");
					$(mostrarExito).fadeOut("slow");
					$(mensajeError).html("Seleccione una provincia de la direcci&oacute;n");
					$("#parroquia_direccion_solicitante").focus();
				}
			}

		if (valido)
			if (zonaSolicitante == "U") {
				if (ciudadSolicitante == null || ciudadSolicitante == "") {
					$(mostrarError).fadeIn("slow");
					$(mostrarExito).fadeOut("slow");
					$(mensajeError).html("Seleccione una ciudad de la direcci&oacute;n");
					$("#ciudad_direccion_solicitante").focus();
				}
			}

		if (valido)
			if (principalSolicitante == null || principalSolicitante == "") {
				$(mostrarError).fadeIn("slow");
				$(mostrarExito).fadeOut("slow");
				$(mensajeError).html("Ingrese una calle principal");
				$("#principal_direccion_solicitante").focus();
				valido = false;
			}

		if (valido)
			if (secundariaSolicitante == null || secundariaSolicitante == "") {
				$(mostrarError).fadeIn("slow");
				$(mostrarExito).fadeOut("slow");
				$(mensajeError).html("Ingrese una calle secundaria");
				$("#secundaria_direccion_solicitante").focus();
				valido = false;
			}

		if (valido)
			if (numeroSolicitante == null || numeroSolicitante == "") {
				$(mostrarError).fadeIn("slow");
				$(mostrarExito).fadeOut("slow");
				$(mensajeError).html("Ingrese un n&uacute;mero de la direcci&oacute;n");
				$("#numero_direccion_solicitante").focus();
				valido = false;
			}

		if (valido)
			if (telefonoSolicitante == null || telefonoSolicitante == "") {
				$(mostrarError).fadeIn("slow");
				$(mostrarExito).fadeOut("slow");
				$(mensajeError).html("Ingrese un telefono");
				$("#telefono_direccion_solicitante").focus();
				valido = false;
			}

		if (valido)
			if (celularSolicitante == null || celularSolicitante == "") {
				$(mostrarError).fadeIn("slow");
				$(mostrarExito).fadeOut("slow");
				$(mensajeError).html("Ingrese un telefono del solicitante");
				$("#celular_direccion_solicitante").focus();
				valido = false;
			}

		if (valido)
			if (emailSolicitante == null || emailSolicitante == "") {
				$(mostrarError).fadeIn("slow");
				$(mostrarExito).fadeOut("slow");
				$(mensajeError).html("Ingrese un email del solicitante");
				$("#provincia_direccion_solicitante").focus();
				valido = false;
			}
		//fin validaciones solicitante

		if (valido) {
			$.ajax({
				url : "../DireccionController",

				data : {
					"tipoConsulta" : "guardarDatosFacturaSolicitante",
					"zonaSolicitante" : zonaSolicitante,
					"provinciaSolicitante" : provinciaSolicitante,
					"cantonSolicitante" : cantonSolicitante,
					"parroquiaSolicitante" : parroquiaSolicitante,
					"ciudadSolicitante" : ciudadSolicitante,
					"principalSolicitante" : principalSolicitante,
					"numeroSolicitante" : numeroSolicitante,
					"secundariaSolicitante" : secundariaSolicitante,
					"referenciaSolicitante" : referenciaSolicitante,
					"telefonoSolicitante" : telefonoSolicitante,
					"celularSolicitante" : celularSolicitante,
					"emailSolicitante" : emailSolicitante,
					"identificacionSolicitante" : identificacionSolicitante
				},
				type : 'post',
				datatype : 'json',
				success : function (data) {
					if (data.success) {
						$(mostrarExito).fadeIn("slow");
						$(mostrarError).fadeOut("slow");
						$(mensajeExito).html("Se guardaron la direcci&oacute;n con exito");

					} else {
						$(mostrarError).fadeIn("slow");
						$(mostrarExito).fadeOut("slow");
						$(mensajeError).html("No se pudieron guardar las direcciones, mensaje: " + data.error);

					}
				}
			});
		}
	}

}

function validarEntidadJr(identificacion, formulario) {

	$.ajax({
		url : "../EntidadJrController",

		data : {
			"tipoConsulta" : "encontrarPorIdentificacion",
			"identificacion" : identificacion
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			if (data.success) {
				if (data.esRiesgosa) {
					alert("El cliente necesita actualizar sus datos. Por favor contactarse con las siguientes personas:\n\n      Sofia Almeida\t Ext. 2021\n\n      Christian Arzani\t Ext. 2018\n\n      Andrea Donoso\t Ext. 2032");
					if (formulario == "datosClienteInicio") {
						$("#codigoClienteEnsurance").val("");
						$("#identificacion").val("");
						$("#nombres").val("");
						$("#apellidos").val("");
						$("#nombre_completo").val("");
					}
					if (formulario == "formasPagoDebitoBancario") {
						$("#codigoClienteEnsurance").val("");
						$("#bancoIdentificacion").val("");
						$("#nombresTitularDebitos").val("");
						$("#apellidosTitularDebitos").val("");
						$("#nombreCompletoTitularDebitos").val("");
					}
					if (formulario == "formasPagoDebitoTarjetas") {						
						$("#tarjetaIdentificacion").val("");
						$("#nombresTitularTarjetas").val("");
						$("#apellidosTitularTarjetas").val("");
						$("#nombreCompletoTitularTarjetas").val("");
					}
					if (formulario == "datosAsegurado") {
						$("#identificacion_asegurado").val("");
						$("#nombres_asegurado").val("");
						$("#apellidos_asegurado").val("");
						$("#nombre_completo_asegurado").val("");
						$("#asegurado_id").val("");
					}
				}
			} else {}
		}
	});

}

//Metodo para actualizar la tasa solo en vehÃ¯Â¿Â½culos dinamicos
function actualizarTasaMinima() {

	// Validacion tasa minima rango
	var tasaMinimaIngresadaValor = parseFloat($("#tasa_minima").val()) || parseFloat("0");
	var tasaMinimaObtenidaValor = parseFloat($("#tasa_minima_valor").val());

	if (tasaMinimaIngresadaValor == 0) {
		$("#tasa_minima").val(tasaMinimaObtenidaValor);
		return false;
	}

	if (tasaMinimaIngresadaValor < tasaMinimaObtenidaValor || tasaMinimaIngresadaValor > parseFloat("7")) {
		return false;
	}

	var tienePagoL=false;
	var alerta="";
	if ($("#codigoPagoRegistrado").val() != null && $("#codigoPagoRegistrado").val() != "" && $("#codigoPagoRegistrado").val() != "-1") {
		tienePagoL=true;
	}
	
	if(tienePagoL&&!tieneDescuento)
		alerta="Si recalcula la tasa se eliminara la forma de pago! Desea continuar?";
		
	if(!tienePagoL&&tieneDescuento)
		alerta="Si recalcula la tasa se eliminara la solicitud de descuento. Desea continuar?";
	
	if(tienePagoL&&tieneDescuento)
		alerta="Si recalcula la tasa se eliminara la forma de pago y la solicitud de descuento. Desea continuar?";
	
	if(tienePagoL||tieneDescuento){
		var r = confirm(alerta);
		if (r == true) {
			if(tienePagoL)
				eliminarPago();
			if(tieneDescuento)
				eliminarDescuento();
			
			$.ajax({
				url : "../CotizacionController",
				data : {
					"tipoConsulta" : "actualizarTasa",
					"cotizacionId" : $("#cotizacion_id").text(),
					"tasaValor" : tasaMinimaIngresadaValor,

				},
				type : 'post',
				datatype : 'json',
				success : function (data) {
					// Recargar listado de vehiculos
					vehiculosCotizacion();

				}
			});
		}
	}
	else{
		$.ajax({
			url : "../CotizacionController",
			data : {
				"tipoConsulta" : "actualizarTasa",
				"cotizacionId" : $("#cotizacion_id").text(),
				"tasaValor" : tasaMinimaIngresadaValor,

			},
			type : 'post',
			datatype : 'json',
			success : function (data) {
				// Recargar listado de vehiculos
				vehiculosCotizacion();

			}
		});
	}
}
//Metodo validaciÃ¯Â¿Â½n para el ingreso de la identificacion del beneficiario
function validarIdentificacionAsegurado() {

	var tipoIdentificacion = $("#tipo_identificacion_asegurado").val();
	var txtIdentificacion = $("#identificacion_asegurado");
	if (tipoIdentificacion != "2") {
		$(txtIdentificacion).attr("onkeypress", "validarKeyPress(event,/[0-9]/);");
		if (tipoIdentificacion == "1") {
			$(txtIdentificacion).attr("maxlength", "10");
		}
		if (tipoIdentificacion == "3" || tipoIdentificacion == "4") {
			$(txtIdentificacion).attr("maxlength", "13");
		}
	} else {
		$(txtIdentificacion).attr("onkeypress", "validarSoloLetrasNumeros(event);");
		$(txtIdentificacion).removeAttr("maxlength");
	}

}

//Metodo validaciÃ¯Â¿Â½n de ingreso de la cedula del beneficiario
function validarRUCCedulaAsegurado() {

	var tipoIdentificacion = $("#tipo_identificacion_asegurado").val();
	var txtIdentificacion = $("#identificacion_asegurado");
	var valido = true;

	if (tipoIdentificacion != "2") {

		if (tipoIdentificacion == "1") {
			valido = validaCedula($(txtIdentificacion).val());
		}
		if (tipoIdentificacion == "3" || tipoIdentificacion == "4") {
			valido = validaRuc($(txtIdentificacion).val());
		}
	}
	if (!valido) {
		$("#mensajeErrorEndosoBeneficiario").html("Ingrese una identificaci&oacute;n valida");
		$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
		$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
		$(txtIdentificacion).focus();
	} else
		cargarPorIdentificacion('datosAsegurado', $(txtIdentificacion).val());
}
function reestablecerTasaMinima() {
	
	var tienePagoL=false;
	var alerta="";
	if ($("#codigoPagoRegistrado").val() != null && $("#codigoPagoRegistrado").val() != "" && $("#codigoPagoRegistrado").val() != "-1") {
		tienePagoL=true;
	}
	
	if(tienePagoL&&!tieneDescuento)
		alerta="Si reestablece la tasa se eliminara la forma de pago! Desea continuar?";
		
	if(!tienePagoL&&tieneDescuento)
		alerta="Si reestablece la tasa se eliminara la solicitud de descuento. Desea continuar?";
	
	if(tienePagoL&&tieneDescuento)
		alerta="Si reestablece la tasa se eliminara la forma de pago y la solicitud de descuento. Desea continuar?";
	
	if(tienePagoL||tieneDescuento){
		var r = confirm(alerta);
		if (r == true) {
			if(tienePagoL)
				eliminarPago();
			if(tieneDescuento)
				eliminarDescuento();
			
			$.ajax({
				url : "../CotizacionController",
				data : {
					"tipoConsulta" : "reestrablecerTasa",
					"cotizacionId" : $("#cotizacion_id").text(),
				},
				type : 'post',
				datatype : 'json',
				success : function (data) {
					// Recorremos los vehiculos y calculamos nuevamente los valores
					$.each($(".guardarVehiculo"), function (index, item) {
						$(item).click();
						if(index-1==$(".guardarVehiculo").length)
							vehiculosCotizacion();
					});
					// Recargar listado de vehiculos
					
				}
			});
		}
	}
	else{
		$.ajax({
			url : "../CotizacionController",
			data : {
				"tipoConsulta" : "reestrablecerTasa",
				"cotizacionId" : $("#cotizacion_id").text(),
			},
			type : 'post',
			datatype : 'json',
			success : function (data) {
				// Recorremos los vehiculos y calculamos nuevamente los valores
				$.each($(".guardarVehiculo"), function (index) {
					$(this).trigger("click");
				});
				// Recargar listado de vehiculos
				vehiculosCotizacion();
			}
		});
	}

}

function calcularValorAcumuladoVehiculo(numero) {
	var tiempoVigencia = $("#vigencia").val();
	var sumaAsegurada = Number($("#suma_asegurada_" + numero).val());
	var valorAseguradoDepreciado = sumaAsegurada;
	var valorAseguradoAcumulado = 0;
	for (var i = 1; i <= tiempoVigencia; i++) {
		if (i == 1) {
			valorAseguradoAcumulado += Number(valorAseguradoDepreciado);
		} else if (i > 1) {
			if (i == 2)
				valorAseguradoDepreciado = parseFloat(valorAseguradoDepreciado).toFixed(2) - parseFloat(valorAseguradoDepreciado * 0.15).toFixed(2);
			else
				valorAseguradoDepreciado = parseFloat(valorAseguradoDepreciado).toFixed(2) - parseFloat(valorAseguradoDepreciado * 0.10).toFixed(2);

			valorAseguradoAcumulado += Number(valorAseguradoDepreciado);
		}
	}
	$("#sumaAcumuladaVehiculo" + numero).val(valorAseguradoAcumulado);

	var extras = $(".valorExtras_" + numero);
	var totalExtras = 0;
	for (var j = 0; j < extras.length; j++) {
		var valorExtraDepreciado = $(extras[j]).val();
		for (var i = 1; i <= tiempoVigencia; i++) {
			if (i == 1) {
				totalExtras += Number(valorExtraDepreciado);
			} else if (i > 1) {
				if (i == 2)
					valorExtraDepreciado = parseFloat(valorExtraDepreciado).toFixed(2) - parseFloat(valorExtraDepreciado * 0.15).toFixed(2);
				else
					valorExtraDepreciado = parseFloat(valorExtraDepreciado).toFixed(2) - parseFloat(valorExtraDepreciado * 0.10).toFixed(2);
			}
		}
		totalExtras += Number(valorExtraDepreciado);
	}
	$("#sumaAcumuladaExtras" + numero).val(totalExtras);

}

function cambioVigencia() {

	var vehiculos = Number($("#numero_vehiculos").val());
	for (var i = 1; i <= vehiculos; i++) {
		calcularValorAcumuladoVehiculo(i);
	}
}

function habilitarSubidaArchivo(event) {
	$("#loading_cargaDocumentos").fadeIn('slow');
	var target = event.target || event.srcElement;
	var id = $(target).attr("id").replace("archivo", "upload");
	if (document.getElementById($(target).attr("id")).files[0] != null);
	$("#" + id).fadeIn("slow");

}

function subirArchivo(event) {
	var target = event.target || event.srcElement;

	var nombre = $(target).attr("name");
	if (nombre == null)
		nombre = $(target).parent().attr("name");
	$("#loading_cargaDocumentos").fadeIn('slow');
	var archivo = document.getElementById("archivo" + nombre).files[0];

	var formdata = new FormData();

	formdata.append("archivo" + nombre + $("#cotizacion_id").text(), archivo);

	var xhr = new XMLHttpRequest();
	xhr.overrideMimeType('text/plain;');

	xhr.open("POST", "../DocumentoVisadoController", true);

	xhr.send(formdata);

	xhr.onload = function (e) {

		if (this.status == 200) {
			var data = JSON.parse(this.responseText.replace(/'/g, '"'));
			if (data.success) {
				$("#msgPopupArchivosHabilitantes").attr("class", "alert alert-success").text("Se guardo el archivo con exito").fadeIn('slow');
				$("#subir" + nombre).fadeOut('slow');
				$("#upload" + nombre).fadeOut('slow');
				$("#descargar" + nombre).fadeIn('slow');
				$("#eliminar" + nombre).fadeIn('slow');
				descargarArchivo(event);
			} else {
				$("#msgPopupArchivosHabilitantes").attr("class", "alert alert-danger").text(data.error).fadeIn('slow');
				$("#subir" + nombre).fadeIn('slow');
				$("#descargar" + nombre).fadeOut('slow');
				$("#eliminar" + nombre).fadeOut('slow');
			}
		}
		$("#loading_cargaDocumentos").fadeOut('slow');
	};

}

function descargarArchivo(event) {
	var target = event.target || event.srcElement;
	var nombre = $(target).attr("name");
	if (nombre == null)
		nombre = $(target).parent().attr("name");

	var urlDescarga = "../DocumentoVisadoController?tipoConsulta=descarga" + nombre + "&id=" + $("#cotizacion_id").text();
	//$("#frameDescargaPolizaFirmada").attr("src",urlDescarga);linkDescargaPolizaFirmada
	$("#linkDescarga" + nombre).attr("href", urlDescarga);
}

function eliminarArchivo(event) {
	var target = event.target || event.srcElement;
	var nombre = $(target).attr("name");
	if (nombre == null)
		nombre = $(target).parent().attr("name");
	$(target).attr("disabled", "disabled");
	$("#loading_cargaDocumentos").fadeIn('slow');

	$.ajax({
		url : "../DocumentoVisadoController",
		data : {
			"cotizacionId" : $("#cotizacion_id").text(),
			"tipoConsulta" : "eliminarArchivo" + nombre
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			if (!data.success)
				alert(data.error);
			else {
				$("#descargar" + nombre).fadeOut('slow');
				$("#eliminar" + nombre).fadeOut('slow');
				$("#subir" + nombre).fadeIn('slow');
			}
			$("#loading_cargaDocumentos").fadeOut('slow');
			$(target).removeAttr("disabled");

		}
	});
}

function calcularValoresCuotas() {
	var formulario = $("#cboFpFormaPago").val();
	var cuotaInicial = "";
	var numeroCuotas = "";
	var valorTotal = $("#total_vh").val();
	var diferencia;
	var valorCuotas;

	if (formulario != "1") {
		if (formulario == "4") {
			cuotaInicial = $("#txtcuotaInicial").val();
			numeroCuotas = $("#cboFpPlazo").val();
		}
		if (formulario == "2") {
			cuotaInicial = $("#txtcuotaInicialbancoPlazo").val();
			numeroCuotas = $("#bancoPlazo").val();
		}
		if (formulario == "3") {
			cuotaInicial = $("#txtcuotaInicialtarjetaPlazo").val();
			numeroCuotas = $("#tarjetaPlazo").val();
		}
		cuotaInicial = Number(cuotaInicial);
		numeroCuotas = Number(numeroCuotas);
		if (numeroCuotas == 12) {
			valorTotal = valorTotal * parseFloat(1.0842);
		}
		diferencia = Number(valorTotal - cuotaInicial);
		valorCuotas = parseFloat(diferencia / numeroCuotas).toFixed(2);

		var mensaje = "El valor total a pagar es $" + parseFloat(valorTotal).toFixed(2);
		mensaje += " en " + numeroCuotas + " cuotas de $" + valorCuotas;
		if (cuotaInicial > 0)
			mensaje += ", cuota inicial de $" + cuotaInicial;

		if (numeroCuotas == 1)
			mensaje = mensaje.replace("cuotas", "cuota");
		if (numeroCuotas != 0)
			$("#msgPopupPago").attr("class", "alert alert-info").html(mensaje).fadeIn();
	}
}

function validarCuotaMinima(event) {
	var target = event.target || event.srcElement;
	var aux = $(target).attr('id');
	var cuotaInicial = $("#txtcuotaInicial" + aux.replace("txtcuotaInicial", "")).val();
	var numCuotas = $(target).val();
	var valor = $("#total_vh").val();
	var valorRestante = valor - cuotaInicial;
	var valorCuotas = parseFloat(valorRestante).toFixed(2) / parseInt(numCuotas);

	if (valorCuotas < 50) {
		numCuotas = Math.floor(valorRestante / 50) == 0 ? 1 : Math.floor(valorRestante / 50);
		valorCuotas = parseFloat(valorRestante).toFixed(2) / parseInt(numCuotas);
		$(target).val(numCuotas); //.trigger("change");
		alert("Las cuotas mensuales no pueden ser menores a $50. Usted puede pagar la diferencia en maximo " + numCuotas + " cuotas");
		$("#" + aux + " option[value='10']").remove();
		var opcion = $("#" + id + " option[value='9']");
		var clase = $(opcion).attr("class");
		$(opcion).after("<option value='10' class='" + clase + "'>10 meses</option>");
		calcularValoresCuotas();
	} else
		$(target).parent().parent().prev().children().last().children().first().trigger("change");
}

function validarCuotaInicial(event) {
	$("#detallePagoCuotas").empty();
	var target = event.target || event.srcElement;
	var id = $(target).attr("id");
	var slctCts = id.replace("txtcuotaInicial", "");
	if ($(target).val() == "")
		$(target).val(0);
	if ($(target).val() < 50 && $(target).val() != 0) {
		alert("El valor minimo de la cuota inicial es $50");
		$(target).val(0);

		var opcion = $("#" + slctCts + " option[value='9']");
		var clase = $(opcion).attr("class");
		if ($("#" + id + " option[value='10']").length == 0)
			$(opcion).after("<option value='10' class='" + clase + "'>10 meses</option>");
	} else {
		if ($(target).val() != 0)
			$("#" + slctCts + " option[value='10']").remove();
		else {
			var opcion = $("#" + slctCts + " option[value='9']");
			var clase = $(opcion).attr("class");
			if ($("#" + id + " option[value='10']").length == 0)
				$(opcion).after("<option value='10' class='" + clase + "'>10 meses</option>");
		}
		$(target).parent().parent().next().children().last().children().first().trigger("change");
		calcularValoresCuotas();

	}
}

function validarValoresPagos(tipo) {
	var formulario = $("#cboFpFormaPago").val();
	var cuotaInicial;
	var numCuotas;

	if (formulario != "1" || tipo != null) {
		if (formulario == "4" || tipo == "CREDITO CUOTAS") {
			cuotaInicial = $("#txtcuotaInicial");
			numCuotas = $("#cboFpPlazo");
		}
		if (formulario == "2" || tipo == "DEBITO BANCARIO") {
			cuotaInicial = $("#txtcuotaInicialbancoPlazo");
			numCuotas = $("#bancoPlazo");
		}
		if (formulario == "3" || tipo == "DEBITO TARJETA") {
			cuotaInicial = $("#txtcuotaInicialtarjetaPlazo");
			numCuotas = $("#tarjetaPlazo");
		}

		var valorCuotaInicial = Number($(cuotaInicial).val());
		var valorNumCuotas = Number($(numCuotas).val());
		var valor = $("#total_vh").val();
		if (valorNumCuotas == 12) {
			valor = valor * parseFloat(1.0842);
		}
		var valorRestante = valor - valorCuotaInicial;
		var valorCuotas = parseFloat(valorRestante).toFixed(2) / parseInt(valorNumCuotas);

		if (valorCuotaInicial > valor && valor!=0) {
			alert("El valor de la cuota inicial no puede ser mayor al valor del pago!");
			$(cuotaInicial).val(0);

		}
		if (valorCuotaInicial > 0 && valorCuotaInicial < 50) {
			alert("El valor minimo de la cuota inicial es $50");
			$(cuotaInicial).val(0);

		}
		if (valorCuotaInicial == 0) {
			var slctCts = $(numCuotas).attr("id");
			var opcion = $("#" + slctCts + " option[value='9']");
			var clase = $(opcion).attr("class");
			if ($("#" + slctCts + " option[value='10']").length == 0)
				$(opcion).after("<option value='10' class='" + clase + "'>10 meses</option>");
			if (valorCuotas < 50) {
				valorNumCuotas = Math.floor(valorRestante / 50) == 0 ? 1 : Math.floor(valorRestante / 50);
				valorCuotas = parseFloat(valorRestante).toFixed(2) / parseInt(valorNumCuotas);
				$(numCuotas).val(valorNumCuotas); //.trigger("change");
				alert("Las cuotas mensuales no pueden ser menores a $50. Usted puede pagar la diferencia en m&aacute;ximo " + valorNumCuotas + " cuotas");
			}
		}
		if (valorCuotaInicial >= 50 && valorCuotaInicial < valor) {
			var slctCts = $(numCuotas).attr("id");
			$("#" + slctCts + " option[value='10']").remove();
			if (valorCuotas < 50) {
				valorNumCuotas = Math.floor(valorRestante / 50) == 0 ? 1 : Math.floor(valorRestante / 50);
				valorNumCuotas = valorNumCuotas == 10 ? 9 : valorNumCuotas;
				$(numCuotas).val(valorNumCuotas); //.trigger("change");
				alert("Las cuotas mensuales no pueden ser menores a $50. Usted puede pagar la diferencia en m&aacute;ximo " + valorNumCuotas + " cuotas");
			}
			valorNumCuotas = valorNumCuotas == 10 ? 9 : valorNumCuotas;
			$(numCuotas).val(valorNumCuotas); //.trigger("change");
		}
	}
	calcularValoresCuotas();
}

function bloquearEmitido() {
	$("input[type*='date']").attr("disabled", "disabled");
	$("textarea").attr("disabled", "disabled");
	$("input[type*='number']").attr("disabled", "disabled");
	$("input[type*='select']").attr("disabled", "disabled");
	$("select").attr("disabled", "disabled");
	$(":text").attr("disabled", "disabled");
	$(":checkbox").attr("disabled", "disabled");
	$(".bloquearEmitido").attr("disabled", "disabled");
	editoVehiculo = false;
}

function cambiarEstadoCotizacion(estado){
	var cotizacion = $("#cotizacion_id").html().trim();
	$.ajax({
		url : "../CotizacionController",
		data : {
			"tipoConsulta" : "actualizarEstado",
			"estado" : estado,
			"cotizacionId" : cotizacion
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			if (!data.success)
				alert(data.error);
			else {
				alert("La cotizacion fue actualizada, para emitirla se encontrara en la opcion de Cotizaciones por Emitir");
			}

		}
	});
	
}


function consultarDatosVehiculo(valor, formulario, numero) {
	var target = event.target || event.srcElement;
	var tipoConsulta = "consultaGeneral";
	var cotizacionId = $("#cotizacion_id").text();
	var anioProximo=new Date().getFullYear()+1;
	if (formulario.indexOf("chasis") != -1)
		tipoConsulta = "consultarChasisSRI";

	if (valor.length > 5) {
		if (formulario == "placaDatosVehiculo" || formulario == "chasisDatosVehiculo")
			$("#loading_" + numero).fadeIn("slow");
		if (formulario == "tablaFinalVehiculos" || formulario == "validarTablaFinalVehiculos")
			$("#loading_TablaFinal").fadeIn("slow");

		$.ajax({
			url : "../ObjetoVehiculoController",
			data : {
				"valor" : valor,
				"tipoConsulta" : tipoConsulta,
				"formulario" : formulario,
				"numero" : numero
			},
			type : 'post',
			datatype : 'json',
			success : function (data) {
				if (!data.success)
					alert(data.error);
				else {
					if (data.datosVehiculo != null && data.datosVehiculo.anioFabricacion != null) {
						data = data.datosVehiculo;
						//if (validarVigencia(data, numero))
							if (formulario == "tablaFinalVehiculos") {
								if (data.placa != "")
									$("#placaTablaFinal" + numero).val(data.placa);
								else
									$("#placaTablaFinal" + numero).val(data.cpn);
								$("#motorTablaFinal" + numero).val(data.motor);
								$("#chasisTablaFinal" + numero).val(data.chasis);
							}
						if (formulario == "placaDatosVehiculo") {
							if (data.cotizacionId != null && data.cotizacionId != "" && data.cotizacionId != $("#cotizacion_id").text()) {
								alert("El vehiculo se encuentra en la cotizacion #" + data.cotizacionId + ". No se puede volver a cotizar!");
								$("#num_placa" + numero).val("").removeAttr('disabled');
								$("#num_motor" + numero).val("").removeAttr('disabled');
								$("#num_chasis" + numero).val("").removeAttr('disabled');
								$("#anio_fabricacion" + numero).val("").removeAttr('disabled');
								$('#marca_' + numero).select2("val", "");
								$('#color_' + numero).select2("val", "");
							}

							if (data.anioFabricacion.length > 2 && data.cotizacionId == "") {

								if(parseInt(data.anioFabricacion)<=anioProximo || (parseInt(data.anioFabricacion) >= parseInt($("#anio_max_seguro").val()) && parseInt(data.anioFabricacion) <= parseInt($('#anio_fabricacion1').children().first().next().val()))) {
									$('#codigoVehiculoEnsurance' + numero).val(data.codigoEnsurance);
									$('#marca_' + numero).select2("val", "");
									$('#color_' + numero).select2("val", "");
									if (data.motor != "") {
										$('#num_motor' + numero).val($.trim(data.motor));
									}
									$('#num_chasis' + numero).val(
											$.trim(data.chasis));
									$('#anio_fabricacion' + numero).find("option[value=" + data.anioFabricacion + "]").attr('selected', 'selected').trigger('change');
									$('#anio_fabricacion' + numero).attr('disabled', 'disabled');
									$('#suma_asegurada_' + numero).val(data.valorEnsurance);
									$('#marca_' + numero).select2("val", data.marcaEnsurance);
									$('#color_' + numero).select2("val", data.color).change();

									cargarModelos(data.modeloEnsurance, data.marcaEnsurance, numero);
									if (data.dispositivoEnsurance == "SI")
										$('#disposito_rastreo' + numero).val(1);
									else
										$('#disposito_rastreo' + numero).val(0);

									if (parseInt(data.valorEnsurance) > 0) {
										$('#suma_asegurada_' + numero).val(data.valorEnsurance).trigger('change');
										activarBotonExtras(1);
									}
								} else {
									if (data.cotizacionId == "") {
										alert("No se puede cotizar un automovil de " + data.anioFabricacion);
										$("#loading_" + numero).fadeOut("slow");
									}
									$('#num_motor' + numero).val("");
									$('#num_chasis' + numero).val("");
									$('#anio_fabricacion' + numero).val("");
									$('#marca_' + numero).select2("val", "");
									$('#modelo_' + numero).select2("val", "");
									$('#color_' + numero).select2("val", "");
									$('#disposito_rastreo' + numero).select2("val", "");
									$('#suma_asegurada_' + numero).select2("val", "");
									$("#antiguedad_vh" + numero).val("");
								}
							} else {
								$("#num_placa" + numero).removeAttr('disabled');
								$("#num_motor" + numero).removeAttr('disabled');
								$("#num_chasis" + numero).removeAttr('disabled');
								$("#anio_fabricacion" + numero).removeAttr('disabled');

							}
						}
						if (formulario == "chasisDatosVehiculo") {
							if (data.cotizacionId != null && data.cotizacionId != "" && data.cotizacionId != $("#cotizacion_id").text()) {
								alert("El vehiculo se encuentra en la cotizacion #" + data.cotizacionId + ". No se puede volver a cotizar!");
								$("#num_placa" + numero).val("");
								$("#num_motor" + numero).val("");
								$("#num_chasis" + numero).val("");
								$("#anio_fabricacion" + numero).val("").removeAttr('disabled');
								$('#marca_' + numero).select2("val", "");
								$('#color_' + numero).select2("val", "");
								$("#loading_" + numero).fadeOut("slow");
							}

							if (data.anioFabricacion.length > 2 && data.cotizacionId == "") {

								if (parseInt(data.anioFabricacion) >= parseInt($("#anio_max_seguro").val()) && parseInt(data.anioFabricacion) <= parseInt($('#anio_fabricacion1').children().first().next().val())) {
									$('#codigoVehiculoEnsurance' + numero).val(data.codigoEnsurance);
									$('#marca_' + numero).select2("val", "");
									$('#color_' + numero).select2("val", "");
									$('#num_placa' + numero).val($.trim(data.placa));
									$('#num_chasis' + numero).val($.trim(data.chasis));
									$('#anio_fabricacion' + numero).find("option[value=" + data.anioFabricacion + "]").attr('selected', 'selected').trigger('change');
									$('#anio_fabricacion' + numero).attr('disabled', 'disabled');
									$('#suma_asegurada_' + numero).val(data.valorEnsurance);
									$('#marca_' + numero).select2("val", data.marcaEnsurance);
									$('#color_' + numero).select2("val", data.color).change();
									cargarModelos(data.modeloEnsurance, data.marcaEnsurance, numero);
									if (data.dispositivoEnsurance == "SI")
										$('#disposito_rastreo' + numero).val(1);
									else
										$('#disposito_rastreo' + numero).val(0);

									if (parseInt(data.valorEnsurance) > 0) {
										$('#suma_asegurada_' + numero).val(data.valorEnsurance).trigger('change');
										activarBotonExtras(1);
									}
								} else {
									if (data.cotizacionId == "")
										alert("No se puede cotizar un automovil de " + data.anioFabricacion);
									$('#num_motor' + numero).val("");
									$('#num_chasis' + numero).val("");
									$('#anio_fabricacion' + numero).val("");
									$('#marca_' + numero).select2("val", "");
									$('#modelo_' + numero).select2("val", "");
									$('#color_' + numero).select2("val", "");
									$('#disposito_rastreo' + numero).select2("val", "");
									$('#suma_asegurada_' + numero).select2("val", "");
									$("#antiguedad_vh" + numero).val("");
								}
							} else {
								$("#num_placa" + numero).removeAttr('disabled');
								$("#num_motor" + numero).removeAttr('disabled');
								$("#num_chasis" + numero).removeAttr('disabled');
								$("#anio_fabricacion" + numero).removeAttr('disabled');
							}
						}
						if (formulario == "tablaFinalVehiculos") {
							if (data.placa.trim() != "")
								$("#placaTablaFinal" + numero).val(data.placa.trim());
							else
								$("#placaTablaFinal" + numero).val(data.cpn.trim());
							if (data.chasis.trim())
								$("#chasisTablaFinal" + numero).val(data.chasis.trim());
							if (data.motor.trim())
								$("#motorTablaFinal" + numero).val(data.motor.trim());
						}
					} else {
						$("#num_placa" + numero).removeAttr('disabled');
						$("#num_motor" + numero).removeAttr('disabled');
						$("#num_chasis" + numero).removeAttr('disabled');
						$("#anio_fabricacion" + numero).removeAttr('disabled');
					}
					if (data.vigenciaEnsurance != null) {
						validarVigencia(data, numero);
					}
					else{
						solicitarInspeccionArr[numero] = true;
						$("#mensajeVehiculo"+numero).fadeOut("slow");
					}
					
				}
				$("#loading_" + numero).fadeOut("slow");
				$(target).removeAttr("disabled");

			}
		});

	}
	else{
		solicitarInspeccionArr[numero] = true;
	}
}

function validarVigencia(data, numero){
	
	var today = new Date();
	var vigencia = new Date(data.vigenciaEnsurance);
	var diferencia = (Date.parse(vigencia) - Date.parse(today)) / (1000 * 60 * 60 * 24);
	var dd = vigencia.getDate();
	var mm = vigencia.getMonth() + 1; // January
	// is
	// 0!
	var yyyy = vigencia.getFullYear();

	if (dd < 10) {
		dd = '0' + dd;
	}

	if (mm < 10) {
		mm = '0' + mm;
	}

	/**/
	var hoy = new Date();
	var dia = hoy.getDate();
	var mes = hoy.getMonth() + 1; // January
	// is
	// 0!
	var anio = hoy.getFullYear();

	if (dia < 10) {
		dia = '0' + dia;
	}

	if (mes < 10) {
		mes = '0' + mes;
	}

	var aux = anio + '-' + mes + '-' + dia;

	var fechaescogidaactual = $("#fecha_inicio_vigencia").val();

	if (fechaescogidaactual == ""){
		$("#fecha_inicio_vigencia").val(aux);
		fechaescogidaactual=aux;
	}
	/**/

	var fechaVigenciaPoliza = dd + "/" + mm + "/" + yyyy;
	var fechaSet = yyyy + '-' + mm + '-' + dd;

	solicitarInspeccionArr[numero] = true;

	if (data.cotizacionId != null && data.cotizacionId != "" && data.cotizacionId != $("#cotizacion_id").text()) {
		alert("El vehiculo se encuentra en la cotizacion #" + data.cotizacionId + ". No se puede cotizar!");
		$("#mensajeVehiculo"+numero).fadeOut("slow");
		$("#num_chasis" + numero).val("");
		$("#num_motor" + numero).val("");
		$("#num_placa" + numero).val("");
		return false;
	}

	if (diferencia <= 90 && diferencia >= 0)
		if (yyyy > Number(fechaescogidaactual.split('-')[0])) {
			$("#mensajeVehiculo"+numero).attr("class","alert alert-info").html("El vehiculo ingresado tiene una poliza vigente hasta el " + fechaVigenciaPoliza + " (dd/mm/YYYY).  La vigencia inicial de la nueva poliza sera la fecha de vencimiento de la anterior.").fadeIn("slow");
			alert("El vehiculo ingresado tiene una poliza vigente hasta el " + fechaVigenciaPoliza + " (dd/mm/YYYY).  La vigencia inicial de la nueva poliza sera la fecha de vencimiento de la anterior.");
			$("#fecha_inicio_vigencia").val(fechaSet).attr("disabled", "disabled");
			solicitarInspeccionArr[numero] = false; 
			$("#marca_" + numero).select2("val", data.marcaEnsurance);
			cargarModelos(data.modeloEnsurance, data.marcaEnsurance, numero);
			$("#color_" + numero).select2("val", data.colorEnsurance);
			//$("#anio_fabricacion" + numero).val(data.anioEnsurance);
			$("#num_placa" + numero).val(data.placa);
			$("#num_motor" + numero).val(data.motor);
			
			$("#num_chasis" + numero).val(data.chasis);
			actualizarVigenciaDesde();
			return true;
		} else {
			if (mm > Number(fechaescogidaactual.split('-')[1]) && Number(fechaescogidaactual.split('-')[0]) == yyyy) {
				$("#mensajeVehiculo"+numero).attr("class","alert alert-info").html("El vehiculo ingresado tiene una poliza vigente hasta el " + fechaVigenciaPoliza + " (dd/mm/YYYY).  La vigencia inicial de la nueva poliza sera la fecha de vencimiento de la anterior.").fadeIn("slow");
				alert("El vehiculo ingresado tiene una poliza vigente hasta el " + fechaVigenciaPoliza + " (dd/mm/YYYY).  La vigencia inicial de la nueva poliza sera la fecha de vencimiento de la anterior.");
				$("#fecha_inicio_vigencia").val(fechaSet).attr("disabled", "disabled");
				solicitarInspeccionArr[numero] = false;
				$("#marca_" + numero).select2("val", data.marcaEnsurance);
				cargarModelos(data.modeloEnsurance, data.marcaEnsurance, numero);
				$("#color_" + numero).select2("val", data.colorEnsurance);
				//$("#anio_fabricacion" + numeroVeces).val(data.anioEnsurance);
				$("#num_placa" + numero).val(data.placa);
				$("#num_motor" + numero).val(data.motor);
				$("#num_chasis" + numero).val(data.chasis);
				actualizarVigenciaDesde();
				return true;
			} else
				if (dd >= Number(fechaescogidaactual.split('-')[2]) && Number(fechaescogidaactual.split('-')[1]) == mm && Number(fechaescogidaactual.split('-')[0]) == yyyy) {
					$("#mensajeVehiculo"+numero).attr("class","alert alert-info").html("El vehiculo ingresado tiene una poliza vigente hasta el " + fechaVigenciaPoliza + " (dd/mm/YYYY).  La vigencia inicial de la nueva poliza sera la fecha de vencimiento de la anterior.").fadeIn("slow");
					alert("El vehiculo ingresado tiene una poliza vigente hasta el " + fechaVigenciaPoliza + " (dd/mm/YYYY).  La vigencia inicial de la nueva poliza sera la fecha de vencimiento de la anterior.");
					$("#fecha_inicio_vigencia").val(fechaSet).attr("disabled", "disabled");
					solicitarInspeccionArr[numero] = false;
					$("#marca_" + numero).select2("val", data.marcaEnsurance);
					cargarModelos(data.modeloEnsurance, data.marcaEnsurance, numero);
					$("#color_" + numero).select2("val", data.colorEnsurance);
					//$("#anio_fabricacion" + numero).val(data.anioEnsurance);
					$("#num_placa" + numero).val(data.placa);
					$("#num_motor" + numero).val(data.motor);
					$("#num_chasis" + numero).val(data.chasis);
					actualizarVigenciaDesde();
					return true;
				}
		}

	if ((Date.parse(vigencia)) >= (Date.parse(today)) && diferencia > 90) {
		$("#mensajeVehiculo"+numero).fadeOut("slow");
		
		alert("El vehiculo ingresado tiene una poliza vigente hasta el " + fechaVigenciaPoliza + " (dd/mm/YYYY).  No puede cotizar este vehiculo.");
		$("#num_chasis" + numero).val("");
		$("#num_motor" + numero).val("");
		$("#num_placa" + numero).val("");
		return false;
	}	
}


function abrirTramiteWF(){
	var cotizacion = $("#cotizacion_id").html().trim();
	$("#loading_cargaDocumentos").fadeIn("slow");
	$.ajax({
		url : "../WorkFlowSmartworkController",
		data : {
			"cotizacionId":cotizacion,
			"tipoConsulta":"crearTramiteWF"
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			$("#loading_cargaDocumentos").fadeOut("slow");
			if(data.success){
			/*var str="ErrorMensaje "+data.resultado.ErrorMensaje;
			str+=" InstanceNumber "+data.resultado.InstanceNumber;
			str+=" WorkFlowId "+data.resultado.WorkFlowId;
			alert(str);*/
				if(data.resultado.InstanceNumber!=""){
					$("#msgPopupArchivosHabilitantes").attr("class","alert alert-success").html("El tramite fue creado").fadeIn("slow");
					$("#mensajeNumeroTramite").attr("class","alert alert-success").html("Numero de tramite "+data.resultado.InstanceNumber).attr("style",$("#mensajeNumeroTramite").attr("style")+" ; width:50%;").fadeIn("slow");
					$("#crearTramiteWF").fadeOut("slow");
					$("#descargarGuiaRemision").fadeIn("slow");
					}
				if(data.resultado.ErrorMensaje!="")
					$("#msgPopupArchivosHabilitantes").attr("class","alert alert-danger").html("El tramite no se pudo crear: "+data.resultado.ErrorMensaje).fadeIn("slow");
			}
			else
				alert(data.error);
		}
	});
}

function tieneDocumento(documento,tiene){
	if (tiene + "" == "1") {
		var urlDescarga = "../DocumentoVisadoController?tipoConsulta=descarga"+documento+"&id=" + $("#cotizacion_id").text();
		$("#linkDescarga"+documento).attr("href", urlDescarga);
		$("#subir"+documento).fadeOut('slow');
		$("#upload"+documento).fadeOut('slow');
		$("#descargar"+documento).fadeIn('slow');
		$("#eliminar"+documento).fadeIn('slow');
	} 
	if (tiene + "" == "0") {
		$("#descargar"+documento).fadeOut('slow');
		$("#eliminar"+documento).fadeOut('slow');
		$("#subir"+documento).fadeIn('slow');
	}
}


function abrirGuiaRemision() {
	var cotizacion = $("#cotizacion_id").html().trim();
	var pathReporte = "/static/reportes/CertificadosVehiculos/GuiaRemision/guiaRemision.jasper";

	var parametros = {
		"parametros" : {
			"COTIZACION" : cotizacion
		},
		"pathReporte" : pathReporte
	};
	abrirReporte('POST', '../ReportesController', parametros, "_blank");
}

// Habilitamos los botones para la creacion del trï¿½mite en smartwork
function habilitarCreacionTramite(){
	
	var tienePolizaFirmada = $("#tienePolizaFirmada").val();
	var tieneAutorizacionDebito = $("#tieneAutorizacionDebito").val();
	var tieneFormularioUPLA = $("#tieneFormularioUPLA").val();
	var tieneCaratulaCotizacion = $("#tieneCaratulaCotizacion").val();
	var tieneFactura = $("#tieneFactura").val();
	
	
	if(tienePolizaFirmada=="1" && tieneAutorizacionDebito =="1" && 
		tieneFormularioUPLA =="1" && tieneCaratulaCotizacion =="1" && tieneFactura=="1") {
		$("#descargarGuiaRemision").show();
		$("#crearTramiteWF").show();
	}
	
	if($("#descargarPolizaFirmada").val() && $('#descargarAutorizacionDebito').is(':visible') &&
			$('#descargarFormularioUPLA').is(':visible') && $('#descargarCaratulaCotizacion').is(':visible') &&
			$('#descargarFactura').is(':visible')) {
		$("#descargarGuiaRemision").show();
		$("#crearTramiteWF").show();
		
	}
}

// Fecha de compra Autoconsa - Plan V
function validacionFechaCompraAutoconsa(){
	var fechaCompra = $("#txtfechaCompra").val();
	var fechaValida = validarSiEsFecha(fechaCompra);
	if(fechaValida == false){
		alert("Ingrese una Fecha de Compra Correcta");
		return false;
	}else{
		var fechaCompraVerificador = new Date(fechaCompra);
		var fechaActual = new Date();
		var diasExtras = Number(15);
		var fechaDesde = fechaActual.getTime() - (86400000 * diasExtras);
		var fechaHasta = fechaActual.getTime() + (86400000 * diasExtras);
		var fechaDesdeValidador = new Date(fechaDesde);
		var fechaHastaValidador = new Date(fechaHasta);
		
		if(fechaCompraVerificador<fechaDesdeValidador || fechaCompraVerificador>fechaHastaValidador){
			alert("La fecha de compra debe ser 15 dias antes y 15 depues de la fecha actual");
			return false;
		}else{
			validacionDiaMesAutoconsa();
		}
	}
}

//Calculo del dia de mes de pago - Plan V
function validacionDiaMesAutoconsa(){
	var mesPago = Number($("#mes_pago").val());
	var valorFecha = $("#txtfechaCompra").val();
	var valorFechaSplit = valorFecha.split("-");		
	var fechaCompra = new Date(valorFechaSplit[0], valorFechaSplit[1] - 1, valorFechaSplit[2]);
			
	if(mesPago == 1){
		var diaCompra = fechaCompra.getDate();
		if(diaCompra == 1)
			$("#txtDiasExtras").val(0);		
		if(diaCompra >=2 && diaCompra <=6 ){
			alert("Dias Extras superan el limite, seleccione otra forma de pago.");
			return false;
		}
		if(diaCompra >=7 && diaCompra <=31 ){
			var mesCompra = fechaCompra.getMonth()+1;			
			// Fecha limite primero de cada mes (el primer dia del segundo mes)			
			var fechaLimite = new Date(fechaCompra.getFullYear(), mesCompra+1, 1);
			// 1 dia en milisegundos
			var diaMilisegundos=1000*60*60*24;
			// Convertimos las fechas en milisegundos para comparar
			var fechaCompraMS = fechaCompra.getTime();
			var fechaLimiteMS = fechaLimite.getTime();  		
			// Calculala diferencia en milisegundos
			  var diferenciaMS = fechaLimiteMS - fechaCompraMS;			  
			// Convertimos los en dias de diferencia
			var diasExtrasObtenidos = Math.round(diferenciaMS/diaMilisegundos); 
			
			// Verificamos el ano bisisto y agregamos los valores		
			var vigenciaPoliza = $("#vigencia").val();
			// Verificar mï¿½ximo de dias de ano bisiesto
			var fechaActual = new Date();
			var anoActual = Number(fechaActual.getFullYear());

			$("#txtDiasExtras").val(diasExtrasObtenidos-31); 			
		}
	}
	if(mesPago == 16){
		var diaCompra = fechaCompra.getDate();		
		if(diaCompra >=17 && diaCompra <=21 ){
			alert("Dias Extras superan el limite, seleccione otra forma de pago.");
			return false;
		}
		if(diaCompra >=1 && diaCompra <=16 ){
			var mesCompra = fechaCompra.getMonth()+1;			
			// Fecha limite primero de cada mes (el primer dia del primer mes)			
			var fechaLimite = new Date(fechaCompra.getFullYear(), mesCompra, 1);
			// 1 dia en milisegundos
			var diaMilisegundos=1000*60*60*24;
			// Convertimos las fechas en milisegundos para comparar
			var fechaCompraMS = fechaCompra.getTime();
			var fechaLimiteMS = fechaLimite.getTime();  		
			// Calculala diferencia en milisegundos
			  var diferenciaMS = fechaLimiteMS - fechaCompraMS;			  
			// Convertimos los en dias de diferencia
			var diasExtrasObtenidos = Math.round(diferenciaMS/diaMilisegundos); 
			
			// Verificamos el ano bisiesto y agregamos los valores		
			var vigenciaPoliza = $("#vigencia").val();
			// Verificar maximo de dias de ano bisiesto
			var fechaActual = new Date();
			var anoActual = Number(fechaActual.getFullYear());

			$("#txtDiasExtras").val(diasExtrasObtenidos-31);
		}
		if(diaCompra >=22 && diaCompra <=31 ){
			var mesCompra = fechaCompra.getMonth()+1;			
			// Fecha limite primero de cada mes (el primer dia del segundo mes)			
			var fechaLimite = new Date(fechaCompra.getFullYear(), mesCompra+1, 1);
			// 1 dia en milisegundos
			var diaMilisegundos=1000*60*60*24;
			// Convertimos las fechas en milisegundos para comparar
			var fechaCompraMS = fechaCompra.getTime();
			var fechaLimiteMS = fechaLimite.getTime();  		
			// Calculala diferencia en milisegundos
			  var diferenciaMS = fechaLimiteMS - fechaCompraMS;			  
			// Convertimos los en dias de diferencia
			var diasExtrasObtenidos = Math.round(diferenciaMS/diaMilisegundos); 
			
			// Verificamos el ano bisisto y agregamos los valores		
			var vigenciaPoliza = $("#vigencia").val();
			// Verificar mï¿½ximo de dias de ano bisiesto
			var fechaActual = new Date();
			var anoActual = Number(fechaActual.getFullYear());
			$("#txtDiasExtras").val(diasExtrasObtenidos-31);
		}		
	}
}

function validarSiEsFecha(fechaCompra) {
	return ((new Date(fechaCompra)).toString() !== "Invalid Date") ? true : false;
}

// Metodo para al hacer clic subir el foco
function ponerFocoAlHacerClic() {
	$("html, body, div").animate({ scrollTop: 0 }, "slow");
}

function cambiarIdentificacion(tipo,valor){
	
	if(tipo=='debito' && valor!='4'){
		$("#filaNaturalDebitos").show();
		$("#filaJuridicaDebitos").hide();
		if(valor =='1')
			$('#bancoIdentificacion').attr('maxlength',10);
		else
			$('#bancoIdentificacion').attr('maxlength',20);
	}
	if(tipo=='debito' && valor=='4'){
		$("#filaNaturalDebitos").hide();
		$("#filaJuridicaDebitos").show();
		$('#bancoIdentificacion').attr('maxlength',20);
	}
	
	if(tipo=='tarjeta' && valor!='4'){
		$("#filaNaturalTarjetas").show();
		$("#filaJuridicaTarjetas").hide();
		if(valor =='1')
			$('#tarjetaIdentificacion').attr('maxlength',10);
		else
			$('#tarjetaIdentificacion').attr('maxlength',20);
	}
	if(tipo=='tarjeta' && valor=='4'){
		$("#filaNaturalTarjetas").hide();
		$("#filaJuridicaTarjetas").show();
		$('#tarjetasIdentificacion').attr('maxlength',20);
	}
}

// Mensaje al emitir programa de seguros
function mensajeProgramaSeguros(){
    var respuesta = confirm(" Clic en Aceptar \n - La Poliza quedara en estado emitido en Ensurance. No se facturara\n\n Clic en Cancelar\n - Si se facturara en Ensurance");
    if (respuesta == true) {
    	$("#emision_programa_seguros").attr('checked', true);
    	actualizarProgramaSeguros();
    } else {
    	$("#emision_programa_seguros").attr('checked', false);
    	actualizarProgramaSeguros();
    }
}

function activarOpcionesPantallaVH(){
	
	$.ajax({
		url : "../OpcionPantallaController",
		data : {			
			"tipoConsulta":"activarOpcionesPantallaVH"
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			if(data.success){
				var opciones =data.Opciones;
				
				if(opciones.indexOf("seccion_coberturas_vh")!= -1)
					$("#coberturas_unico").hide();
				else
					$("#coberturas_unico").show();
				
				if(opciones.indexOf("seccion_descuentos_vh")!= -1)																	
					$(".descuentos_ocultar").hide();
					
				if(opciones.indexOf("seccion_inspecciones_vh")!= -1)											
					$(".inspeccion_ocultar").hide();			
			}
			else
				alert(data.error);
		}
	});	
}

//Metodo para realizar el cambio de vehÃƒÂ­culo dependiendo de lo que elija el usuario
function cambioTipoVehiculo(numero,event,texto){
	var target = event.target || event.srcElement;	
	var checked = $(target).is(':checked');
	if (texto == 'comercial' && checked) {	
		$(".datosConductor"+ numero.toString()).fadeIn();
		$("#vh_empresa_check"+ numero.toString()).removeAttr('checked');		
		$("#conductor_genero" + numero.toString()).val("");
		$("#conductor_edad" + numero.toString()).val("");
		$("#conductor_estado_civil" + numero.toString()).val("");
		$("#hijos_conduzcan"+ numero.toString()).val("");
		$("#identificacion_conductor"+ numero.toString()).fadeIn();
		$("#nombres_conductor"+ numero.toString()).fadeIn();
		$("#apellidos_conductor"+ numero.toString()).fadeIn();
	}
	if (texto == 'empresa' && checked) {
		$("#vh_comercial_check"+ numero.toString()).removeAttr('checked');
		$(".datosConductor"+ numero.toString()).fadeOut();				
		$("#conductor_genero" + numero.toString()).val("J");
		$("#conductor_edad" + numero.toString()).val("J");
		$("#conductor_estado_civil" + numero.toString()).val("J");
		$("#hijos_conduzcan" + numero.toString()).val("NO");
		$("#identificacion_conductor"+ numero.toString()).fadeOut();
		$("#nombres_conductor"+ numero.toString()).fadeOut();
		$("#apellidos_conductor"+ numero.toString()).fadeOut();
		$("#identificacion_conductor"+ numero.toString()).val("");
		$("#nombres_conductor"+ numero.toString()).val("");
		$("#apellidos_conductor"+ numero.toString()).val("");
	}
}
// Metodo para obtener los datos del conductor 
function cargarIdentificacionConductor(identificacion,numero){
	$("#cargar_conductor"+numero.toString()).fadeIn();

	$.ajax({
		url : '../EntidadController',

		data : {
			"identificacion" : identificacion,
			"tipoConsulta" : "cargarPorIdentificacionConductorEnsurance"
		},
		type : 'POST',
		datatype : 'json',
		success : function (data) {
		if(data.success){									
				var entidad = data.entidad;
				if(entidad != undefined){
					if (entidad.entidadIdEnsurance != "") {						
							$("#codigo_conductor_ensurance"+numero.toString()).val(entidad.entidadIdEnsurance);						
							$("#nombres_conductor"+numero.toString()).val(entidad.nombre);
							$("#apellidos_conductor"+numero.toString()).val(entidad.apellido);													
					} else {						
							$("#codigo_conductor_ensurance"+numero.toString()).val("");						
							$("#nombres_conductor"+numero.toString()).val("");
							$("#apellidos_conductor"+numero.toString()).val("");									
					}
				}
			}
			$("#cargar_conductor"+numero.toString()).fadeOut();
			},
			error : function (xhr, ajaxOptions, thrownError) {		
				$("#cargar_conductor"+numero.toString()).fadeOut();
			}
		});

}

// Metodo para el calculo del IVA segun los Puntos de Ventas
function calculoIVAPuntoVentaCotizacion() {
	var cotizacion = $("#cotizacion_id").text();
	$.ajax({
		url : '../CotizacionController',
		data : {
			"tipoConsulta" : "ivaSegunPuntoVenta",
			"cotizacionId" : cotizacion 
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			$(".valor_iva").text(parseFloat(data.iva).toFixed(2));	
			$("#iva_vh").val(data.valor_iva);			
			$(".valor_compensacion").text(data.compensacion);
			$("#valor_compensacion_vh").val(parseFloat(data.valor_compensacion).toFixed(2));
		}
	});
}
