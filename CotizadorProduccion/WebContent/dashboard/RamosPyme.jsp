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

<!-- KENDO -->
<link rel="stylesheet" href="../static/css/Kendo/kendo.common.min.css" />
<link rel="stylesheet" href="../static/css/Kendo/kendo.blueopal.min.css" />
<script src="../static/js/Kendo/kendo.all.min.js"></script>
<script src="../static/js/Kendo/kendo.web.min.js"></script>

<title>Ramos PYMEs - Cotizador QBE</title>

<script>
	$(function() {
		$(".wrapper1").scroll(function() {
			$(".wrapper2").scrollLeft($(".wrapper1").scrollLeft());
		});
		$(".wrapper2").scroll(function() {
			$(".wrapper1").scrollLeft($(".wrapper2").scrollLeft());
		});
	});

	var ramoId = "";
	var tipoItemId = "";
	var tipoRiesgoId = "";
	var claseRiesgoId = "";
	var tipoConsulta = "";
	var notification;
	var ramoList = "";
	var grupoCoberturaList = "";

	$(document)
			.ready(
					function() {
						activarMenu("RamosPyme");

						$("#ramoId").kendoMultiSelect({
							dataTextField : "ramoNombre",
							dataValueField : "ramoId",
							animation : false,
							maxSelectedItems : 1
						});

						ramoList = $("#ramoId").data("kendoMultiSelect");

						$("#tipoItemId").kendoNumericTextBox({
							format : "#"
						});

						$("#tipoRiesgoId").kendoNumericTextBox({
							format : "#"
						});

						$("#claseRiesgoId").kendoNumericTextBox({
							format : "#"
						});
						/*Mensaje de error*/
						notification = $("#notification").kendoNotification({
                        position: {
                            pinned: true,
                            top: 90,
                            right: 35
                        },
                        autoHideAfter: 10000,
                        hideOnClick: false,
                        stacking: "down",
                        templates: [ {
                            type: "error",
                            template: $("#errorTemplate").html()
                    		}]
                        }).data("kendoNotification");
						 $(document).one("kendo:pageUnload", function(){ });
						
						cargar();

						$("#save-record")
								.bind(
										{
											click : function() {

												ramoId = $(
														"#ramoId option:selected")
														.val();
												tipoItemId = ($("#tipoItemId")
														.data("kendoNumericTextBox"))
														.value();
												tipoRiesgoId = ($("#tipoRiesgoId")
														.data("kendoNumericTextBox"))
														.value();
												claseRiesgoId = ($("#claseRiesgoId")
														.data("kendoNumericTextBox"))
														.value();

												if (typeof ramoId === 'undefined'
														|| typeof tipoItemId === 'undefined'
														|| typeof tipoRiesgoId === 'undefined'
														|| typeof claseRiesgoId === 'undefined') {
													$("#ramoId").css("border",
															"1px solid red");
													$("#tipoItemId").css(
															"border",
															"1px solid red");
													$("#tipoRiesgoId").css(
															"border",
															"1px solid red");
													$("#claseRiesgoId").css(
															"border",
															"1px solid red");
													alert("Por favor ingrese el campo requerido");
													return false;
												}

												tipoConsulta = "crear";
												enviarDatos(ramoId, tipoItemId,
														tipoRiesgoId,
														claseRiesgoId,
														tipoConsulta);
											}

										});

						$("#ramoId").change(function() {
							actualizar($("#ramoId option:selected").val());
						});
					});

	/*function cargarCiudad(){
		ramoList.d
	}
	 */
	function cargar() {
		$("#dataTableContent").children().remove();
		$
				.ajax({
					url : '../PymeExtRamoController',
					data : {
						"tipoConsulta" : "encontrarTodos"
					},
					type : 'POST',
					datatype : 'json',
					success : function(data) {
						if (data.success == false){
							 notification.show({
		                           title: "ERROR",
		                           message: data.error
		                       }, "error");							
							return;
						}
						var listadoPymeExtRamo = data.listadoPymeExtRamo;
						$
								.each(
										listadoPymeExtRamo,
										function(index) {
											$("#dataTableContent")
													.append(
															"<tr class='odd gradeX'>"
																	+ "<td relation='ramo'>"
																	+ listadoPymeExtRamo[index].ramoNombre
																	+ "</td>"
																	+ "<td relation='ramo'>"
																	+ listadoPymeExtRamo[index].tipoItemId
																	+ "</td>"
																	+ "<td relation='ramo'>"
																	+ listadoPymeExtRamo[index].tipoRiesgoId
																	+ "</td>"
																	+ "<td relation='ramo'>"
																	+ listadoPymeExtRamo[index].claseRiesgoId
																	+ "</td>"
																	+ "<td width='175px'>"
																	+ " <button type='button'  name='actualizar-btn' data-toggle='modal' data-target='#add' class='btn btn-success btn-xs actualizar-btn' onclick='actualizar("
																	+ listadoPymeExtRamo[index].ramoId
																	+ ")'>"
																	+ " <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar"
																	+ " </button>"
																	+ " <button type='button' class='btn btn-danger btn-xs eliminar-btn' onclick='eliminar("
																	+ listadoPymeExtRamo[index].ramoId
																	+ ")'>"
																	+ "<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar"
																	+ " </button>"
																	+ "</td>"
																	+ "</tr>");
										});
						$("#loading").remove();

						/*Cargo el select de Ramo*/
						ramoList.dataSource.filter({});
						ramoList.setDataSource(data.ramoArr);
						/*var ramoArr = data.ramoArr;
						$.each(ramoArr, function (index){
							$("#ramoId").append("<option value='"+ ramoArr[index].ramoId +"'>"+ ramoArr[index].ramoNombre +"</option>");
						});*/
					}
				});
	}

	function enviarDatos(ramoId, tipoItemId, tipoRiesgoId, claseRiesgoId,
			tipoConsulta) {
		$.ajax({
			url : '../PymeExtRamoController',
			async : false,
			data : {
				"ramoId" : ramoId,
				"tipoItemId" : tipoItemId,
				"tipoRiesgoId" : tipoRiesgoId,
				"claseRiesgoId" : claseRiesgoId,
				"tipoConsulta" : tipoConsulta
			},
			type : 'POST',
			datatype : 'json',
			success : function(data) {
				if (data.success == false){
					 notification.show({
                          title: "ERROR",
                          message: data.error
                      }, "error");							
					return;
				}
				$("#msgPopup").show();
			}
		});
	}

	function eliminar(id) {
		var r = confirm("Seguro que desea eliminar");
		if (r == true) {
			ramoId = id;
			tipoItemId = "";
			tipoRiesgoId = "";
			tipoItemId = "";
			claseRiesgoId = "";
			tipoConsulta = "eliminar";
			enviarDatos(ramoId, tipoItemId, tipoRiesgoId, claseRiesgoId,
					tipoConsulta);
		}
		cargar();
	}

	function actualizar(id) {

		($("#tipoItemId").data("kendoNumericTextBox")).value("");
		($("#tipoRiesgoId").data("kendoNumericTextBox")).value("");
		($("#claseRiesgoId").data("kendoNumericTextBox")).value("");

		if (typeof id === 'undefined')
			return false;

		$.ajax({
			url : '../PymeExtRamoController',
			async : false,
			data : {
				"ramoId" : id,
				"tipoConsulta" : "obtenerPorId"
			},
			type : 'POST',
			datatype : 'json',
			success : function(data) {
				if (data.success == false){
					 notification.show({
                          title: "ERROR",
                          message: data.error
                      }, "error");							
					return;
				}
				$("#ramoId").data("kendoMultiSelect").value(data.ramoId);
				($("#tipoItemId").data("kendoNumericTextBox"))
						.value(data.tipoItemId);
				($("#tipoRiesgoId").data("kendoNumericTextBox"))
						.value(data.tipoRiesgoId);
				($("#claseRiesgoId").data("kendoNumericTextBox"))
						.value(data.claseRiesgoId);
			}
		});
	}
</script>
<script id="errorTemplate" type="text/x-kendo-template">
  <div class="wrong-pass">
      <img src="../static/images/error-icon.png" width="60" height="60"/>
         <h3>#= title #</h3>
      <p>#= message #</p>
  </div>
</script>
<style>
                .demo-section p {
                    margin: 3px 0 3px;
                    line-height: 50px;
                }
                .demo-section .k-button {
                    width: 200px;
                }

                .k-notification {
                    border: 0;
                }
                /* Error template */
                .k-notification-error.k-group {
                    background: rgba(100%,0%,0%,.7);
                    color: #ffffff;
                }
                .wrong-pass {
                    width: 400px;
                    height: 50px;
                }
                .wrong-pass h3 {
                    font-size: 1em;
                    padding: 36px 14px 9px;
                }
                .wrong-pass img {
                    float: left;
                    margin: 30px 15px 30px 30px;
                }
                
            </style>
</head>
<body>
	<%
		// Permitimos el acceso si la session existe		
		if (session.getAttribute("login") == null) {
			response.sendRedirect("/CotizadorWeb");
		}
	%>
	<span id="notification" style="display:none;"></span>
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
							<h4 class="modal-title" id="myModalLabel">Configuraci&oacute;n
								Ramos.</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">El texto
								cobertura se grabo con exito.</div>
							<div class="form-group">
								<table style="width: 100%">
									<tr>
										<td><label>Ramo: </label> <select id="ramoId"
											class="required" data-placeholder="Seleccione una opción..."></select>
										</td>
									</tr>
									<tr>
										<td><label>Tipo Item:</label> <input type="text"
											id="tipoItemId" style="width: 100%"></td>
									</tr>
									<tr>
										<td><label>Tipo Riesgo:</label> <input type="text"
											id="tipoRiesgoId" style="width: 100%"></td>
									</tr>
									<tr>
										<td><label>Clase Riesgo:</label> <input type="text"
											id="claseRiesgoId" style="width: 100%"></td>
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
								<th>Ramo</th>
								<th>Tipo Item</th>
								<th>Tipo Riesgo</th>
								<th>Clase Riesgo</th>
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
