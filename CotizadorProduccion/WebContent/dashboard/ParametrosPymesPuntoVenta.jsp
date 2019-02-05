<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link
	href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="../static/css/loading.css" rel="stylesheet">

<script
	src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
<script
	src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="../static/js/util.js"></script>
<!-- script src="../static/js/bootstrap.min.js"></script> -->

<!-- KENDO -->
<link rel="stylesheet" href="../static/css/Kendo/kendo.common.min.css" />
<link rel="stylesheet" href="../static/css/Kendo/kendo.blueopal.min.css" />
<script src="../static/js/Kendo/kendo.all.min.js"></script>
<script src="../static/js/Kendo/kendo.web.min.js"></script>

<style type="text/css">
.tab_strip {
	height: 200px;
}
</style>

<title>Configuracion Canal - Cotizador QBE</title>

<script>
	$(function() {
		$(".wrapper1").scroll(function() {
			$(".wrapper2").scrollLeft($(".wrapper1").scrollLeft());
		});
		$(".wrapper2").scroll(function() {
			$(".wrapper1").scrollLeft($(".wrapper2").scrollLeft());
		});
	});

	var tipoConsulta = "";
	var parametroPPVId = "";
	var puntoVentaId = "";
	var esMultiriesgo = "";

	var puntoVentaList = "";

	$(document)
			.ready(
					function() {
						activarMenu("ParametrosPymesPuntoVenta");

						$("#puntoVentaId").kendoMultiSelect({
							dataTextField : "nombre",
							dataValueField : "id",
							animation : false,
							maxSelectedItems : 1
						});
						puntoVentaList = $("#puntoVentaId").data("kendoMultiSelect");

						cargar();

						/*Inicio controloes grabar*/

						$("#save-record")
								.bind(
										{
											click : function() {
												parametroPPVId = $(
														"#parametroPPVId")
														.val();
												puntoVentaId = $(
														"#puntoVentaId option:selected")
														.val();
												if (typeof puntoVentaId === 'undefined') {
													$("#puntoVentaId").css(
															"border",
															"1px solid red");
													alert("Por favor ingrese el campo requerido.");
													return false;
												}

												if ($('#esMultiRiesgo').is(
														':checked'))
													esMultiriesgo = true;
												else
													esMultiriesgo = false;

												if (parametroPPVId == "") {
													tipoConsulta = "crear";
												} else {
													tipoConsulta = "actualizar";
												}

												enviarDatos(parametroPPVId,
														puntoVentaId,
														esMultiriesgo,
														tipoConsulta);
											}

										});
						/*Fin controloes grabar*/

						$("#puntoVentaId").change(
								function() {
									actualizar($(
											"#puntoVentaId option:selected")
											.val());
								});

					});

	function cargar() {

		$("#dataTableContent").children().remove();
		$.ajax({
			url : '../AgenteController',
			data : {
				"tipoConsulta" : "consultarAgentes"
			},
			async : false,
			type : 'POST',
			datatype : 'json',
			success : function(data) {
				/*Activo los select*/
				puntoVentaList.enable(true);

				/*Cargo el select puntoVenta*/
				puntoVentaList.dataSource.filter({});
				puntoVentaList.setDataSource(data.agentes);

			}
		});

		$
				.ajax({
					url : '../PymeParametroXPVController',
					data : {
						"tipoConsulta" : "buscarTodos"
					},
					async : false,
					type : 'POST',
					datatype : 'json',
					success : function(data) {
						var parametroArr = data.parametroArr;
						$
								.each(
										parametroArr,
										function(index) {
											$("#dataTableContent")
													.append(
															"<tr class='odd gradeX'>"
																	+ "<td relation='puntoVentaNombre' style='width: 60%; text-align: left;' class='sorting' >"
																	+ parametroArr[index].puntoVentaNombre
																	+ "</td>"
																	+ "<td relation='esMultirriesgo' style='width: 20%; text-align: left;' class='sorting' >"
																	+ parametroArr[index].esMultiriesgo
																	+ "</td>"
																	+ "<td width='175px'>"
																	+ " <button type='button'  name='actualizar-btn' data-toggle='modal' data-target='#add' class='btn btn-success btn-xs actualizar-btn' onclick='actualizar("
																	+ parametroArr[index].parametroPPVId
																	+ ")'>"
																	+ " <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar"
																	+ " </button>"
																	+ " <button type='button' class='btn btn-danger btn-xs eliminar-btn' onclick='eliminar("
																	+ parametroArr[index].parametroPPVId
																	+ ")'>"
																	+ "<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar"
																	+ " </button>"
																	+ "</td>"
																	+ "</tr>");
										});

						$("#loading").remove();

					}
				});
	}

	function actualizar(puntoVenta) {

		$("#parametroPPVId").val("");
		$("#esMultiRiesgo").prop('checked', false);

		if (typeof puntoVenta === 'undefined')
			return false;

		$.ajax({
			url : '../PymeParametroXPVController',
			data : {
				"tipoConsulta" : "buscarPorId",
				"parametroPPVId" : puntoVenta
			},
			type : 'POST',
			datatype : 'json',
			success : function(data) {
				$("#parametroPPVId").val(data.parametroPPVId);
				$("#puntoVentaId").data("kendoMultiSelect").value(
						data.puntoVentaId);
				if (data.esMultiriesgo) {
					$("#esMultiRiesgo").prop('checked', true);
				} else {
					$("#esMultiRiesgo").prop('checked', false);
				}

			}
		});

	}

	function eliminar(parametroId) {
		var r = confirm("Seguro que desea eliminar");
		if (r) {
			parametroPPVId = parametroId;
			puntoVentaId = "";
			esMultiriesgo = "";
			tipoConsulta = "eliminar";

			enviarDatos(parametroPPVId, puntoVentaId, esMultiriesgo,
					tipoConsulta);
			cargar();

		}
	}

	function enviarDatos(parametroPPVId, contenedorEnsuranceId, esMultiriesgo, tipoConsulta) {
		$.ajax({
			url : '../PymeParametroXPVController',
			async : false,
			data : {
				"tipoConsulta" : tipoConsulta,
				"parametroPPVId" : parametroPPVId,
				"puntoVentaId" : puntoVentaId,
				"esMultiriesgo" : esMultiriesgo

			},
			type : 'POST',
			datatype : 'json',
			success : function(data) {
				$("#msgPopup").show();
			}
		});
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

	<div class="row crud-nav-bar">
		<!-- Button trigger modal -->
		<button class="btn btn-primary" data-toggle="modal" data-target="#add"
			id="addButton">
			<span class="glyphicon glyphicon-plus"></span> &nbsp; Nuevo
		</button>

		<!-- Modal -->
		<div class="modal fade" id="add" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form id="formCrud">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Parametros Punto
								de Venta.</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">La
								configuración se grabo con exito.</div>
							<div class="form-group">
								<input type="hidden" class="form-control" id="parametroPPVId">
								<table style="width: 100%">
									<tr>
										<td colspan="2"><label style="width: 100%">Agente:</label></td>
									</tr>
									<tr>
										<td colspan="2"><select id="puntoVentaId"
											data-placeholder="Seleccione una opción...">
										</select></td>
									</tr>
									<tr>
										<td><br /></td>
									</tr>

									<tr>
										<td><label style="width: 100%">Puede Emitir
												Multiriesgo:</label></td>
										<td><input type="checkbox" id="esMultiRiesgo"></td>
									</tr>
								</table>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" id="close-popup"
								data-dismiss="modal">Cerrar</button>
							<button type="button" class="btn btn-primary" id="save-record">Guardar
								Cambios</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->

	<!-- Datatable -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-primary">
				<div class="panel-body">
					<div class="input-group">
						<span class="input-group-addon">Filtro</span> <input id="filter"
							type="text" class="form-control"
							placeholder="Escriba la palabra a buscar...">
					</div>
					<table class="table table-striped table-bordered table-hover"
						id="dataTable">
						<thead>
							<tr>
								<th>Agente</th>
								<th>Tiene Multiriesgo</th>
								<th></th>
							</tr>
						</thead>
						<tbody id="dataTableContent" class="searchable">
							<div id="loading">
								<div class="loading-indicator">
									<img src="../static/images/ajax-loader.gif" /><br /> <br />
									<span id="loading-msg">Cargando...</span>
								</div>
							</div>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- Datatable -->

</body>
</html>
