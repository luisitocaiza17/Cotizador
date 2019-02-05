<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cotizaciones Pendientes - CotizadorQBE</title>

<!-- Core CSS - Include with every page -->
<link
	href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">

<script
	src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
<script
	src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>

<!-- Table Tools -->
<script
	src="../static/js/sb-admin/plugins/dataTables/dataTables.tableTools.js"></script>
<link
	href="../static/css/sb-admin/plugins/dataTables/dataTables.tableTools.css"
	rel="stylesheet">

<script id="tipoObjetoCargaInicial"
	src="../static/js/vehiculos/carga.inicial.js" tipoObjetoValor="Ninguno"></script>
<script id="tipoObjetoMetodos" src="../static/js/vehiculos/metodos.js"
	tipoObjetoValor="Ninguno"></script>
<script src="../static/js/cotizador/comun.js"></script>

<script src="../static/js/util.js"></script>
<!-- Para el Datepicker-->
<link href="../static/css/datepicker.css" rel="stylesheet">
<script src="../static/js/bootstrap-datepicker.js"></script>
<script>
	$(document)
			.ready(
					function() {
						activarMenu("CotizacionesPendientesNovacredit");
						$('#dataTable').hide();

						$('#LimpiarBtn').click(function() {
							$("#cotizacion_id").val("");
							$("#dp1").val("");
							$("#dp2").val("");
							$("#punto_venta_session").val("");
							$("#agentes").val("");
							$("#identificacion").val("");
							$("#mis_cotizaciones").prop("checked", false);
							$('#dataTable').hide();
							$('#dataTableContent').html('');
							$('#dataTable_wrapper').hide();
							$("#datos_temporal").val("");
						});

						$('#ConsultaBtn')
								.click(
										function() {
											$('#dataTable').hide();
											$('#dataTable_wrapper').hide();

											$("#buscando").fadeIn("slow");

											// Validaciones de las busquedas	    		 
											var numeroEndoso = $(
													"#numeroEndoso").val();
											var fechaInicio = $("#dp1").val();
											var fechaFin = $("#dp2").val();
											var identificacion = $(
													"#identificacion").val();

											if (numeroEndoso == ""
													&& fechaInicio == ""
													&& fechaFin == ""
													&& identificacion == "") {
												alert("Ingrese al menos un campo de busqueda");
												$("#buscando").fadeOut("slow");
												return false;
											}

											if (fechaInicio != ""
													&& fechaFin == "") {
												alert("Ingrese una fecha Fin de Busqueda");
												$("#buscando").fadeOut("slow");
												return false;
											}
											$
													.ajax({
														url : '../VhNovacreditCotizacionController',
														data : {
															"fInicio" : fechaInicio,
															"fFinal" : fechaFin,
															"numeroEndoso" : numeroEndoso,
															"identificacion" : identificacion,
															"tipoConsulta" : "consultarPorParametros"
														},
														type : 'POST',
														datatype : 'json',
														success : function(data) {
															var existenRegistro = false;
															$('#dataTable')
																	.show();
															$(
																	'#dataTable_wrapper')
																	.show();
															$(
																	'#dataTableContent')
																	.html('');
															$("#datos_temporal")
																	.val("");
															var listadoCotizacion = 0;
															listadoCotizacion = data.listadoCotizacion;
															var auxiliar = "";
															if (listadoCotizacion.length > 0) {
																$
																		.each(
																				listadoCotizacion,
																				function(
																						index) {

																					var aux = "	<tr class='odd gradeX'>"
																							+ " <td >"
																							+ listadoCotizacion[index].contenedor
																							+ "</td>"
																							+ " <td >"
																							+ listadoCotizacion[index].numeroEndoso
																							+ "</td>"
																							+ " <td >"
																							+ listadoCotizacion[index].nombreCompleto
																							+ "</td>"
																							+ " <td >"
																							+ listadoCotizacion[index].identificacion
																							+ "</td>"
																							+ " <td >"
																							+ listadoCotizacion[index].vigenciaDesde
																							+ "</td>"
																							+ " <td >"
																							+ listadoCotizacion[index].vigenciaHasta
																							+ "</td>"
																							+ " <td >"
																							+ listadoCotizacion[index].marca
																							+ "</td>"
																							+ " <td >"
																							+ listadoCotizacion[index].modelo
																							+ "</td>"
																							+ " <td >"
																							+ listadoCotizacion[index].motor
																							+ "</td>"
																							+ " <td >"
																							+ listadoCotizacion[index].chasis
																							+ "</td>"
																							+ " <td >"
																							+ listadoCotizacion[index].anio
																							+ "</td>"
																							+ " <td >"
																							+ listadoCotizacion[index].estado
																							+ "</td>";
																					if (listadoCotizacion[index].estado == "Emitido" &&!listadoCotizacion[index].cargaInicial)
																						aux += " <td width='80px' align='center'>"
																								+ " <button type='button' class='btn btn-success btn-xs actualizar-btn' id='"+listadoCotizacion[index].id+"'>"
																								+ " <span class='glyphicon glyphicon glyphicon-edit' id='"+listadoCotizacion[index].id+"'></span> Descgar Reporte"
																								+ " </button>";
																					else
																						aux += "<td>";

																					aux += " <button type='button' class='btn btn-danger btn-xs eliminar-btn' id='"+listadoCotizacion[index].id+"'>"
																							+ "<span class='glyphicon glyphicon glyphicon-remove'  id='"+listadoCotizacion[index].id+"'></span>&nbsp; Cancelar &nbsp;"
																							+ " </button>"
																							+ "</td>"
																							+ "</tr>";
																					auxiliar += aux;

																					$("#buscando").fadeOut("slow");
																				});
																$("#dataTableContent").html(auxiliar);

																$(
																		".actualizar-btn")
																		.bind(
																				{
																					click : function(
																							event) {
																						abrirCertificadoCotizacion(this);
																					}
																				});

																$(
																		".eliminar-btn")
																		.bind(
																				{
																					click : function(
																							event) {
																						var r = window
																								.confirm("Seguro que desea cancelar la Cotizacion");
																						if (r == true) {
																							codigo = $(
																									this)
																									.attr(
																											"id");
																							tipoConsulta = "cancelar";
																							enviarDatos(
																									codigo,
																									tipoConsulta);
																							$(
																									this)
																									.fadeOut();
																							$(
																									'#ConsultaBtn')
																									.click();
																						}
																					}
																				});

																existenRegistro = true;
															}
															if (!existenRegistro) {
																var oTable = $(
																		'#dataTable')
																		.dataTable();
																oTable
																		.fnDestroy();
																$(
																		'#dataTable tbody')
																		.html(
																				"<tr><td colspan='13'>No existen Registros</td></tr>");
																$("#buscando")
																		.fadeOut(
																				"slow");
															}
														}
													});


											function abrirCertificadoCotizacion(target) {
												var cotizacion = $(target).attr("id");
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
											
											function enviarDatos(
													codigoEnsurance,
													tipoConsulta) {
												$
														.ajax({
															url : '../VhNovacreditCotizacionController',
															data : {
																"codigo" : codigo,
																"tipoConsulta" : tipoConsulta
															},
															type : 'POST',
															datatype : 'json',
															success : function(
																	data) {
																if (data.success)
																	$(
																			"#msgPopup")
																			.show();
																else
																	alert(data.error);
															}
														});
											}
										});
					});

	//Metodo validacion de fechas buscador
	$(function() {
		var startDate = new Date();
		var endDate = new Date();

		$('#dp1')
				.datepicker()
				.on(
						'changeDate',
						function(ev) {
							if (ev.date.valueOf() > endDate.valueOf()) {
								alert("La Fecha Inicial no puede ser mayor que la Fecha Actual");
								return false;
							} else {
								startDate = new Date(ev.date);
								$('#startDate').text($('#dp1').data('date'));
							}
							$('#dp1').datepicker('hide');
						});
		$('#dp2')
				.datepicker()
				.on(
						'changeDate',
						function(ev) {
							if (ev.date.valueOf() < startDate.valueOf()) {
								alert('La Fecha Final no puede ser menor que la Fecha Inicial');
								return false;
							} else {

								endDate = new Date(ev.date);
								$('#endDate').text($('#dp2').data('date'));
							}
							$('#dp2').datepicker('hide');
						});
	});
	
	function abrirCertificado() {
		var path = "/static/reportes/CertificadosVehiculos/";
		path += "Novacredit/HistoricoNovacredit.jasper";
		var parametros = {
			"pathReporte" : path,
			"formato" : "excel"
		};
		abrirReporte('POST', '../ReportesController', parametros, "_blank");
	}
	function abrirRenovaciones() {
		var path = "/static/reportes/CertificadosVehiculos/";
		path += "Novacredit/RenovacionesNovacredit.jasper";
		var parametros = {
			"pathReporte" : path,
			"formato" : "excel"
		};
		abrirReporte('POST', '../ReportesController', parametros, "_blank");
	}
</script>
</head>
<body>
	<div class="row crud-nav-bar">
		<div class="well">
			<table class="table">
				<thead>
					<tr>
						<td colspan="3" style="font-weight: bold;"><center>Buscador
								Cotizaciones Novacredit</center></td>

					</tr>
					<tr>
						<th>Busqueda por Cotizacion:</th>
						<th>Nro. Endoso: <input type="text" id="numeroEndoso"
							onkeypress="return justNumbers(event);"></th>
						</th>
						<th>Identificacion Cliente: <input type="text"
							id="identificacion"></th>
						<th>&nbsp;</th>
					</tr>
					<tr>
						<th>Busqueda por Fechas:</th>
						<th>Fecha Inicial: <input type="text"
							data-date-format="dd-mm-yyyy" id="dp1"></th>
						<th>Fecha Final: <input type="text"
							data-date-format="dd-mm-yyyy" id="dp2"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							<button class="btn btn-primary" id="ConsultaBtn">
								<span class="glyphicon glyphicon-search"></span> &nbsp; Consulta
							</button>
						</th>
						<th>
							<button class="btn btn-primary" id="LimpiarBtn">
								<span class="glyphicon glyphicon-trash"></span> &nbsp; Limpiar
							</button>
						</th>
						<th>

							<button type='button' class='btn btn-success '
								id='descargarCertificado' onclick="abrirCertificado();">
								<span class="glyphicons glyphicons-floppy-save"></span>
								Historico
							</button>
						</th>
						<th>

							<button type='button' class='btn btn-success '
								id='descargarCertificado' onclick="abrirRenovaciones();">
								<span class="glyphicons glyphicons-floppy-save"></span>Renovaciones
								del Mes
							</button>
						</th>
						<th>&nbsp;</th>
					</tr>
					<tr>
						<th><div id="buscando" hidden="hidden">
								<div style="margin-top: 10px;">
									<span id="loading-msg"></span><img
										src="../static/images/ajax-loader.gif" /> Buscando la
									informacion, por favor espere...
								</div>
							</div></th>
					</tr>
				</tbody>
			</table>
		</div>

		<!-- Button trigger modal -->

	</div>
	<!-- Datatable -->
	<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover"
					style="font-size: x-small;">
					<thead>
						<tr>
							<th>Contenedor</th>
							<th>Nro Endoso</th>
							<th>Cliente</th>
							<th>CI/RUC</th>
							<th>Inicio</th>
							<th>Fin</th>
							<th>Marca</th>
							<th>Modelo</th>
							<th>Motor</th>
							<th>Chasis</th>
							<th>Año</th>
							<th>Observación</th>
							<th></th>
						</tr>
					</thead>
					<tbody id="dataTableContent" class="searchable"
						style="font-size: x-small;">

					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- Datatable -->
</body>
</html>