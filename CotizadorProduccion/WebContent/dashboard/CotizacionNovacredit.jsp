<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.qbe.cotizador.model.UsuarioRol"%>
<%@page import="com.qbe.cotizador.model.Rol"%>
<%@page import="com.qbe.cotizador.dao.seguridad.UsuarioRolDAO"%>
<%@page import="com.qbe.cotizador.dao.seguridad.VariableSistemaDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../static/css/jquery-steps/normalize.css">
<link rel="stylesheet" href="../static/css/jquery-steps/main.css">
<link rel="stylesheet"
	href="../static/css/jquery-steps/jquery.steps.css">
<link
	href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">
<link rel="stylesheet" href="../static/css/select2/select2.css">
<link rel="stylesheet"
	href="../static/css/bootstrap2-toggle/bootstrap2-toggle.min.css">

<script src="../static/js/jquery-steps/modernizr-2.6.2.min.js"></script>
<script src="../static/js/jquery-steps/jquery.cookie-1.3.1.js"></script>
<script src="../static/js/jquery-steps/jquery.steps.min.js"></script>
<script src="../static/js/jquery.validate.js"></script>
<script src="../static/js/bootstrap2-toggle/bootstrap2-toggle.min.js"></script>

<script src="../static/js/util.js"></script>
<script
	src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
<script
	src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="../static/js/jquery-dynamic-url/jquery.dynamic-url.js"></script>
<script src="../static/js/select2.js"></script>
<!--  	para el tooltipster -->
<script src="../static/js/jquery-ui/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css"
	href="../static/js/jquery-ui/jquery-ui.theme.css" />
<link rel="stylesheet" type="text/css"
	href="../static/css/tooltipster.css" />
<script type="text/javascript" src="../static/js/jquery.tooltipster.js"></script>
<script type="text/javascript"
	src="../static/js/jquery.tooltipster.min.js"></script>
<!-- Para el Datepicker-->
<link href="../static/css/datepicker.css" rel="stylesheet">
<script src="../static/js/bootstrap-datepicker.js"></script>
<!-- cdn for modernizr, if you haven't included it already  -->
<script src="../static/js/modernizr-custom.js"></script>
<!-- polyfiller file to detect and load polyfills -->
<script src="../static/js/polyfiller.js"></script>

<style type="text/css">
.pillbox {
	border: 1px solid #bbb;
	/* margin-bottom: 10px;*/
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	width: 251px;
}

.container>div, .container>table {
	margin: 20px;
}

.tree {
	width: 430px;
	height: 350px;
}

.static-loader {
	margin-left: 30px;
}

.step-content {
	border: 1px solid #D4D4D4;
	border-top: 0;
	border-radius: 0 0 4px 4px;
	padding: 10px;
	margin-bottom: 10px;
}

fieldset.border {
	border: 1px solid #ddd !important;
	padding: 0 1.4em 1.4em 1.4em !important;
	margin: 0 0 1.5em 0 !important;
	-webkit-box-shadow: 0px 0px 0px 0px #ddd;
	box-shadow: 0px 0px 0px 0px #ddd;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
}

legend.border {
	width: inherit; /* Or auto */
	padding: 0 10px; /* To give a bit of padding on the left and right */
	border-bottom: none;
	font-size: medium;
}

.demo-wrap {
	/* margin: 40px auto;*/
	display: block;
	position: relative;
	/* max-width:500px;*/
}

a {
	text-decoration: underline;
	color: #00F;
	cursor: pointer;
}

.seleccionado {
	background-color: #E0E0E0;
	color: black;
}

table {
	width: 100%;
}

select {
	width: 90%;
}

input[type="text"] {
	width: 90%;
}

input[type="date"] {
	width: 90%;
}

.marca_modelo {
	width: 90%;
}

.no-close {
	display: none
}

.ui-dialog-titlebar {
	width: 0%;
}

.ui-dialog-titlebar-close {
	visibility: hidden;
}

a {
	text-decoration: none !important;
}

.col-md-3, .col-sm-3 {
	padding-left: 0px;
	padding-right: 0px;
}

.obligatoriosTarifacion {
	border-width: 1px;
	border-style: solid;
	border-color: #46b8da;
	background: #c3e4ff;
}

#tablaFinalVehiculos tr td {
	width: 10%;
	white-space: nowrap;
}

.fondo_botones {
	font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
	font-weight: bold;
}

.plomo {
	background-color: #f0f8ff;
}
</style>
<script>
	//eventos de objetos

	var editoVehiculo = false;
	var tieneDescuento = false;
	var cargadoDatosUPLA = false;
	var solicitarInspeccionArr = [];
	var casoEspecial = false;

	var cargandoPorId = false;

	$(document).ready(
			function() {
				$("#loading").fadeIn();
				var date = new Date();
				var mes = (Number(date.getMonth()) + 1) < 10 ? "0"
						+ (Number(date.getMonth()) + 1) : (Number(date
						.getMonth()) + 1);
				var dia = (Number(date.getDate())) < 10 ? "0"
						+ (Number(date.getDate()))
						: (Number(date.getDate()));
				$('#fecha').val(
						date.getFullYear() + "-" + mes + "-" + dia);
				activarMenu("CotizacionNovacredit");

				$('#marca').on('change', function(e) {
					//cargarModelos("", $('#marca').select2('val'));
				});

				cargaInicial();

				if (QueryString.id == null) {

					$("#descargarCertificado").fadeOut();
					cargarMarcas();
					cargarColores();
				} else {
					$("#descargarCertificado").fadeIn();
				}
			});

	function cargarMarcas(id) {
		$.ajax({
			url : '../MarcaController',
			data : {
				"tipoConsulta" : "cargaSelect2"
			},
			type : 'post',
			datatype : 'json',
			success : function(data) {

				var marcas2 = data.listadoMarca;
				var marcas = [];

				$.each(marcas2, function(index) {
					marcas[index] = {
						"id" : marcas2[index].text,
						"text" : marcas2[index].text
					};
				});

				$('#marca').select2({
					data : marcas2,
					placeholder : 'Seleccione una marca'
				});

				$('#marca').select2("val", id).attr('required', 'required');
				$("#loading").fadeOut();
			}
		});
	}

	function cargarModelos(id, marca) {

		$("#loading").fadeIn();
		$.ajax({
			url : '../ModeloController',
			data : {
				"tipoConsulta" : "cargaPorMarcaSelect2",
				"marca" : marca
			},
			type : 'post',
			datatype : 'json',
			success : function(data) {
				//$("#modelo_" + numero).find('option').remove().end();

				/*$('#modelo').select2({
					data : data.listadoModelo,
					placeholder : "Escoja un Modelo"

				});*/
				$("#modelo").val(id);
				$("#loading").fadeOut();
			}
		});
	}

	function cargarPorIdentificacion() {

		$("#loading").fadeIn();
		var identificacion = $("#cedula").val();
		if (identificacion.length >= 10) {
			var entidad = "";

			$.ajax({
				url : '../EntidadController',
				data : {
					"identificacion" : identificacion,
					"tipoConsulta" : "cargarPorIdentificacionEnsurance"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					if (data.success) {
						var entidad = data.entidad;
						if (entidad != undefined) {

							$("#nombres").val(entidad.nombre);
							$("#apellidos").val(entidad.apellido);

						}
						validarEntidadJr(identificacion);

						$("#loading").fadeOut();
					} else {
						alert(data.error);
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {

				}
			});
		}
	}

	function validarEntidadJr(identificacion) {
		$
				.ajax({
					url : "../EntidadJrController",
					data : {
						"tipoConsulta" : "encontrarPorIdentificacion",
						"identificacion" : identificacion
					},
					type : 'post',
					datatype : 'json',
					success : function(data) {
						if (data.success) {
							if (data.esRiesgosa) {
								alert("El cliente necesita actualizar sus datos. Por favor contactarse con las siguientes personas:\n\n      Sofia Almeida\t Ext. 2021\n\n      Christian Arzani\t Ext. 2018\n\n      Andrea Donoso\t Ext. 2032");
								$("#identificacion").val("");
								$("#nombres").val("");
								$("#apellidos").val("");
							}
						}

						$("#loading").fadeOut();
					}
				});
	}

	function cargaInicial() {
		$
				.ajax({
					url : "../VhNovacreditCotizacionController",
					data : {
						"tipoConsulta" : "cargaInicial",
						"codigo" : QueryString.id
					},
					type : 'post',
					datatype : 'json',
					success : function(data) {
						if (data.success) {
							if (QueryString.id == null) {
								var tiposTasa = data.tiposTasa;
								var tiposCoberturas = data.tiposCoberturas;
								var periodos = data.periodos;
								var aux = "";
								for (var i = 0; i < tiposTasa.length; i++) {
									var tipoTasa = tiposTasa[i];
									aux += "<option value='"+tipoTasa.id+"' >"
											+ tipoTasa.nombre + "</option>";
								}
								$("#tipoTasa").html(aux);
								aux = "";

								for (var i = 0; i < tiposCoberturas.length; i++) {
									var tipoCobertura = tiposCoberturas[i];
									aux += "<option value='"+tipoCobertura.id+"' >"
											+ tipoCobertura.nombre
											+ "</option>";
								}
								$("#tipoCobertura").html(aux);
								aux = "";

								for (var i = 0; i < periodos.length; i++) {
									var periodo = periodos[i];
									aux += "<option value='"+periodo.id+"' >"
											+ periodo.anios + " años </option>";
								}
								$("#periodos").html(aux);

								$("#loading").fadeOut();
							} else {
								$("#endoso").val(data.numeroEndoso);
								$("#lugar").val(data.lugar);
								$("#fecha").val((data.fecha));
								$("#cedula").val(data.cedula);
								$("#nombres").val(data.nombres);
								$("#apellidos").val(data.apellidos);
								$("#fechaNacimiento").val(
										(data.fechaNacimiento));
								$("#telefono").val(data.telefono);
								$("#celular").val(data.celular);
								$("#correo").val(data.correo);
								$("#callePrincipal").val(data.callePrincipal);
								$("#numeroDireccion").val(data.numeroDireccion);
								$("#calleSecundaria").val(data.calleSecundaria);
								$("#vigenciaDesde").val(data.vigenciaDesde);
								$("#modelo").val(data.modeloTexto);
								//cargarModelos(data.modeloId, data.marcaId);
								cargarMarcas(data.marcaId);
								cargarColores(data.colorId);
								//$("#periodos").val();
								//$("#colores").select2("val");
								//var marca = $("#marca").select2("val");
								//var modelo = $("#modelo").select2("val");

								var tiposTasa = data.tiposTasa;
								var tiposCoberturas = data.tiposCoberturas;
								var periodos = data.periodos;
								var aux = "";
								for (var i = 0; i < tiposTasa.length; i++) {
									var tipoTasa = tiposTasa[i];
									aux += "<option value='"+tipoTasa.id+"' >"
											+ tipoTasa.nombre + "</option>";
								}
								$($("#tipoTasa").html(aux))
										.val(data.tipoTasaId);
								aux = "";

								for (var i = 0; i < tiposCoberturas.length; i++) {
									var tipoCobertura = tiposCoberturas[i];
									aux += "<option value='"+tipoCobertura.id+"' >"
											+ tipoCobertura.nombre
											+ "</option>";
								}
								$($("#tipoCobertura").html(aux)).val(
										data.tipoCoberturaId);
								aux = "";

								for (var i = 0; i < periodos.length; i++) {
									var periodo = periodos[i];
									aux += "<option value='"+periodo.id+"' >"
											+ periodo.anios + " años </option>";
								}
								$($("#periodos").html(aux)).val(data.periodoId);

								$("#loading").fadeOut();

								$("#anio").val(data.anio);
								$("#motor").val(data.motor);
								$("#chasis").val(data.chasis);
								$("#valorCasco").val(data.valorCasco);
								$("#valorExtras").val(data.valorExtras);

								var valores = data.cotizacionXPeriodo;

								var aux = "";
								var filaPeriodo = "<tr class='plomo'><td><b>Período</b></td>";
								var filaValor = "<tr><td ><b>Valor asegurado</b></td>";
								var filaPrima = "<tr class='plomo'><td><b>Prima</b></td>";
								var filaBancos = "<tr><td><b>SCVS</b></td>";
								var filaCampesino = "<tr class='plomo'><td><b>S. Campesino</b></td>";
								var filaEmision = "<tr><td><b>Emisión</b></td>";
								var filaIva = "<tr class='plomo'><td><b>IVA</b></td>";
								var filaTotal = "<tr><td><b>Total</b></td>";

								for (var i = 0; i < valores.length; i++) {
									var aux = valores[i];
									filaPeriodo += "<td><b>AÑO "
											+ aux.periodoAnios + "</b></td>";
									filaValor += "<td>$ " + aux.valorAsegurado
											+ "</td>";
									filaPrima += "<td>$ " + aux.valorPrima
											+ "</td>";
									filaBancos += "<td>$ " + aux.valorSCVS
											+ "</td>";
									filaCampesino += "<td>$ "
											+ aux.valorSeguroCampesino
											+ "</td>";
									filaEmision += "<td>$ "
											+ aux.derechosEmision + "</td>";
									filaIva += "<td>$ " + aux.iva + "</td>";
									filaTotal += "<td>$ " + aux.total + "</td>";
								}

								filaPeriodo += "<td></td></tr>";
								filaValor += "<td></td></tr>";//"<td><b>$ "+datos.valorAseguradoTotalAcumulado+"</b></td>";
								filaPrima += "<td><b>$ " + data.primaTotal
										+ "</b></td></tr>";
								filaBancos += "<td><b>$ " + data.valorSCVSTotal
										+ "</b></td></tr>";
								filaCampesino += "<td><b>$ "
										+ data.valorSeguroCampesinoTotal
										+ "</b></td></tr>";
								filaEmision += "<td><b>$ "
										+ data.valorDerechosEmisionTotal
										+ "</b></td></tr>";
								filaIva += "<td><b>$ " + data.valorIVATotal
										+ "</b></td></tr>";
								filaTotal += "<td><b>$ " + data.valorTotal
										+ "</b></td></tr>";

								$("#tablaValores tbody").html(
										filaPeriodo + filaValor + filaPrima
												+ filaBancos + filaCampesino
												+ filaEmision + filaIva
												+ filaTotal);

								$("#descargarCertificado").fadeIn();

							}
						} else {
							alert(data.error);
						}
					}
				});
	}

	var QueryString = function() {
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
				var arr = [ query_string[pair[0]], pair[1] ];
				query_string[pair[0]] = arr;
				// If third or later entry with this name
			} else {
				query_string[pair[0]].push(pair[1]);
			}
		}
		return query_string;
	}();

	function formarQueryString() {
		QueryString = function() {
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
					var arr = [ query_string[pair[0]], pair[1] ];
					query_string[pair[0]] = arr;
					// If third or later entry with this name
				} else {
					query_string[pair[0]].push(pair[1]);
				}
			}
			return query_string;
		}();
	}

	function guardar() {
		var nroEndoso = $("#endoso").val();
		var lugar = $("#lugar").val();
		var fecha = $("#fecha").val();
		var cedula = $("#cedula").val();
		var nombres = $("#nombres").val();
		var apellidos = $("#apellidos").val();
		var fechaNacimiento = $("#fechaNacimiento").val();
		var telefono = $("#telefono").val();
		var celular = $("#celular").val();
		var correo = $("#correo").val();
		var callePrincipal = $("#callePrincipal").val();
		var numeroDireccion = $("#numeroDireccion").val();
		var calleSecundaria = $("#calleSecundaria").val();
		var vigenciaDesde = $("#vigenciaDesde").val();
		var periodos = $("#periodos").val();
		var colores = $("#colores").select2("val");
		var marca = $("#marca").select2("val");
		var modelo = $("#modelo").val();
		var anio = $("#anio").val();
		var motor = $("#motor").val();
		var chasis = $("#chasis").val();
		var tipoCobertura = $("#tipoCobertura").val();
		var tipoTasa = $("#tipoTasa").val();
		var valorCasco = $("#valorCasco").val();
		var valorExtras = $("#valorExtras").val();
		var codigo = "";
		if (QueryString.id != null)
			codigo = QueryString.id;

		if (nroEndoso == "") {
			alert("Ingrese un numero de endoso!");
			return;
		}
		if (lugar == "") {
			alert("Ingrese un lugar valido");
			return;
		}
		if (fecha == "") {
			alert("Ingrese una fecha valida");
			return;
		}
		if (cedula == "" || !validaCedula(cedula)) {
			alert("Ingrese una cedula valida");
			return;
		}
		if (nombres == "") {
			alert("Ingrese un nombre");
			return;
		}
		if (apellidos == "") {
			alert("Ingrese un apellido");
			return;
		}
		if (fechaNacimiento == "") {
			alert("Ingrese una fecha de nacimiento valida");
			return;
		}
		if (celular == "" || Number(celular) == 'NaN') {
			alert("Ingrese un celular valido");
			return;
		}
		if (periodos == "") {
			alert("Ingrese un periodo del credito");
			return;
		}
		if (colores == "") {
			alert("Ingrese un color");
			return;
		}
		if (modelo == "") {
			alert("Ingrese un modelo");
			return;
		}
		if (marca == "") {
			alert("Ingrese un marca");
			return;
		}
		
		if (anio == "" && Number(anio) == 'NaN') {
			alert("Ingrese un año");
			return;
		}
		if (motor == "") {
			alert("Ingrese un motor");
			return;
		}
		if (chasis == "") {
			alert("Ingrese un chasis");
			return;
		}
		if (tipoCobertura == "") {
			alert("Ingrese un tipo de cobertura");
			return;
		}
		if (tipoTasa == "") {
			alert("Ingrese un tipo de tasa");
			return;
		}
		if (valorCasco == "") {
			alert("Ingrese el valor del casco");
			return;
		}
		if (valorExtras == "") {
			valorExtras = "0";
		}
		$("#loading").fadeIn();
		$("#guardar").attr("disabled", "disabled");
		$
				.ajax({
					url : "../VhNovacreditCotizacionController",
					data : {
						"tipoConsulta" : "guardar",
						"codigo" : codigo,
						"numeroEndoso" : nroEndoso,
						"lugar" : lugar,
						"fecha" : fecha,
						"identificacion" : cedula,
						"nombres" : nombres,
						"apellidos" : apellidos,
						"fechaNacimiento" : fechaNacimiento,
						"telefono" : telefono,
						"celular" : celular,
						"correo" : correo,
						"callePrincipal" : callePrincipal,
						"numeroDireccion" : numeroDireccion,
						"calleSecundaria" : calleSecundaria,
						"vigenciaDesde" : vigenciaDesde,
						"periodo" : periodos,
						"color" : colores,
						"marca" : marca,
						"modeloTexto" : modelo,
						"anio" : anio,
						"motor" : motor,
						"chasis" : chasis,
						"tipoCobertura" : tipoCobertura,
						"tipoTasa" : tipoTasa,
						"valorCasco" : valorCasco,
						"valorExtras" : valorExtras
					},
					type : 'post',
					datatype : 'json',
					success : function(data) {

						$("#guardar").removeAttr("disabled");
						if (data.success) {
							var valores = data.datos.cotizacionXPeriodo;
							var datos = data.datos;
							var aux = "";
							var filaPeriodo = "<tr class='plomo'><td><b>Período</b></td>";
							var filaValor = "<tr><td ><b>Valor asegurado</b></td>";
							var filaPrima = "<tr class='plomo'><td><b>Prima</b></td>";
							var filaBancos = "<tr><td><b>SCVS</b></td>";
							var filaCampesino = "<tr class='plomo'><td><b>S. Campesino</b></td>";
							var filaEmision = "<tr><td><b>Emisión</b></td>";
							var filaIva = "<tr class='plomo'><td><b>IVA</b></td>";
							var filaTotal = "<tr><td><b>Total</b></td>";

							for (var i = 0; i < valores.length; i++) {
								var aux = valores[i];
								filaPeriodo += "<td><b>AÑO " + aux.periodoAnios
										+ "</b></td>";
								filaValor += "<td>$ " + aux.valorAsegurado
										+ "</td>";
								filaPrima += "<td>$ " + aux.valorPrima
										+ "</td>";
								filaBancos += "<td>$ " + aux.valorSCVS
										+ "</td>";
								filaCampesino += "<td>$ "
										+ aux.valorSeguroCampesino + "</td>";
								filaEmision += "<td>$ " + aux.derechosEmision
										+ "</td>";
								filaIva += "<td>$ " + aux.iva + "</td>";
								filaTotal += "<td>$ " + aux.total + "</td>";
							}

							filaPeriodo += "<td></td></tr>";
							filaValor += "<td></td></tr>";//"<td><b>$ "+datos.valorAseguradoTotalAcumulado+"</b></td>";
							filaPrima += "<td><b>$ " + datos.primaTotal
									+ "</b></td></tr>";
							filaBancos += "<td><b>$ " + datos.valorSCVSTotal
									+ "</b></td></tr>";
							filaCampesino += "<td><b>$ "
									+ datos.valorSeguroCampesinoTotal
									+ "</b></td></tr>";
							filaEmision += "<td><b>$ "
									+ datos.valorDerechosEmisionTotal
									+ "</b></td></tr>";
							filaIva += "<td><b>$ " + datos.valorIVATotal
									+ "</b></td></tr>";
							filaTotal += "<td><b>$ " + datos.valorTotal
									+ "</b></td></tr>";

							$("#tablaValores tbody")
									.html(
											filaPeriodo + filaValor + filaPrima
													+ filaBancos
													+ filaCampesino
													+ filaEmision + filaIva
													+ filaTotal);

							$("#descargarCertificado").fadeIn();
							$.pushVar("id", datos.id, "", window.location.href);
							formarQueryString();
						} else {
							alert(data.error);
						}

						$("#loading").fadeOut();
					}
				});
	}

	function cargarColores(id) {
		$.ajax({
			url : '../ColorController',
			data : {
				"tipoConsulta" : "cargaSelect2"
			},
			type : 'post',
			datatype : 'json',
			success : function(data) {

				var colores = data.listadoColor;

				$('#colores').select2({
					data : colores,
					placeholder : 'Seleccione un color'

				});

				$('#colores').select2("val", id).attr('required', 'required');
			}
		});
	}

	function abrirCertificado() {
		var cotizacion = QueryString.id;
		var path = "/static/reportes/CertificadosVehiculos/";
		path += "Novacredit/ReporteNovacredit.jasper";
		var parametros = {
			"parametros" : {
				"COTIZACION_ID" : cotizacion
			},
			"pathReporte" : path
		};
		abrirReporte('POST', '../ReportesController', parametros, "_blank");
	}
</script>

</head>
<body>
	<%
		// Permitimos el acceso si la session existe		
		if (session.getAttribute("login") == null) {
			response.sendRedirect("/CotizadorWeb");
		}
	%>
	<!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

	<div class="content">

		<button type='button' class='btn btn-success ' id='guardar'
			onclick="guardar();">
			<span class="glyphicons glyphicons-floppy-save"></span>Guardar
		</button>
		<button type='button' class='btn btn-success '
			id='descargarCertificado' onclick="abrirCertificado();">
			<span class="glyphicons glyphicons-floppy-save"></span>Descargar
			Certificado
		</button>

		<br>
		<div id="loading" class="loading">
			<span id="loading-msg">Cargando...</span><img
				src="../static/images/ajax-loader.gif" />
		</div>
		<br>
		<div class="alert alert-danger alert-error"
			id="mensaje_exito_input_vehiculo" style="display: none;">Por
			favor ingrese los datos faltantes.</div>
		<!-- Inicio datos del producto cerrado -->
		<div class="panel panel-primary">
			<div class="panel-heading">Cliente</div>
			<div class="panel-body">
				<table>
					<tr>
						<td style="width: 10%"><label><b>Endoso Nro.</b></label></td>
						<td style="width: 25%"><input type="number" id="endoso"
							name=""></td>
						<td style="width: 10%"><label><b>Lugar</b></label></td>
						<td style="width: 20%"><input type="text" id="lugar"
							value="CUENCA"></td>
						<td style="width: 10%"><label><b>Fecha</b></label></td>
						<td style="width: 25%"><input type="date" id="fecha" name=""></td>
					</tr>
					<tr>
						<td style="width: 10%"><label><b>Cedula</b></label></td>
						<td style="width: 25%"><input type="text" id="cedula" name=""
							onchange="cargarPorIdentificacion()" maxlength="13"></td>
						<td style="width: 10%"><label><b>Nombres</b></label></td>
						<td style="width: 20%"><input type="text" id="nombres"
							name="" maxlength="60"></td>
						<td style="width: 10%"><label><b>Apellidos</b></label></td>
						<td style="width: 25%"><input type="text" id="apellidos"
							maxlength="45" name=""></td>
					</tr>
					<tr>
						<td style="width: 10%"><label><b>Fecha de
									Nacimineto</b></label></td>
						<td style="width: 25%"><input type="date"
							id="fechaNacimiento" name=""></td>
						<td style="width: 10%"><label><b>Telefono</b></label></td>
						<td style="width: 20%"><input type="number" id="telefono"
							maxlength="15" name=""></td>
						<td style="width: 10%"><label><b>Celular</b></label></td>
						<td style="width: 25%"><input type="number" id="celular"
							maxlength="15" name=""></td>
					</tr>
					<tr>

						<td style="width: 10%"><label><b>Correo</b></label></td>
						<td style="width: 25%"><input type="text" id="correo" name=""
							maxlength="64"></td>
					</tr>
					<tr>
						<td style="width: 10%"><label><b>Calle Principal</b></label></td>
						<td style="width: 25%"><input type="text" id="callePrincipal"
							maxlength="160" name=""></td>
						<td style="width: 10%"><label><b>Numero</b></label></td>
						<td style="width: 20%"><input type="text" maxlength="50"
							id="numeroDireccion" name=""></td>
						<td style="width: 10%"><label><b>Calle Secundaria</b></label></td>
						<td style="width: 25%"><input type="text" maxlength="100"
							id="calleSecundaria" name=""></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading">Datos de la P&oacute;liza</div>
			<div class="panel-body">
				<table>
					<tr>
						<td style="width: 10%"><label><b>Vigencia Desde</b></label></td>
						<td style="width: 25%"><input type="date" id="vigenciaDesde"
							name=""></td>
						<td style="width: 10%"><label><b>Tiempo de
									Credito</b></label></td>
						<td style="width: 25%"><select id="periodos"></select></td>
						<td style="width: 10%"><label><b>Color</b></label></td>
						<td style="width: 25%"><input type="select"
							style="width: 90%" name="colores" id="colores" class=""
							onchange="" required="required"></td>
					</tr>
					<tr>
						<td style="width: 10%"><label><b>Marca</b></label></td>
						<td style="width: 25%"><input type="select"
							style="width: 90%" name="marca" id="marca" class="" onchange=""
							required="required"></td>
						<td style="width: 10%"><label><b>Modelo</b></label></td>
						<td style="width: 20%"><input type="text" name="modelo"
							id="modelo" class="" onchange="" required="required"></td>
						<td style="width: 10%"><label><b>Año</b></label></td>
						<td style="width: 25%"><input type="number" name="anio"
							maxlength="4" id="anio" class="" onchange="" required="required"></td>
					</tr>
					<tr>
						<td style="width: 10%"><label><b>Motor</b></label></td>
						<td style="width: 25%"><input type="text" id="motor"
							maxlength="20"></td>
						<td style="width: 10%"><label><b>Chasis</b></label></td>
						<td style="width: 20%"><input type="text" id="chasis"
							maxlength="20"></td>
					</tr>
					<tr>
						<td style="width: 10%"><label><b>Tipo de
									Cobertura</b></label></td>
						<td style="width: 25%"><select id="tipoCobertura"></select></td>
						<td style="width: 10%"><label><b>Tipo de Tasa</b></label></td>
						<td style="width: 25%"><select id="tipoTasa"></select></td>
					</tr>
					<tr>
						<td style="width: 10%"><label><b>Valor Casco</b></label></td>
						<td style="width: 25%"><input id="valorCasco" type="number"></td>
						<td style="width: 10%"><label><b>Valor Extras</b></label></td>
						<td style="width: 25%"><input id="valorExtras" type="number"></td>
					</tr>
				</table>
			</div>
		</div>
		<div class="panel panel-primary">
			<div class="panel-heading">Valores</div>
			<div class="panel-body">
				<table id="tablaValores">
					<thead>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
</body>
</html>
