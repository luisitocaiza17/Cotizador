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

	var grupoProductoId = "";
	var codigo = "";
	var nombre = "";
	var codigoEnsurance = "";
	var tipoConsulta = "";


	var grupoProductoList = "";

	$(document)
			.ready(
					function() {
						activarMenu("ActividadEconomica");

						$("#grupoProductoId").kendoMultiSelect({
							dataTextField : "nombreGrupoCobertura",
							dataValueField : "grupoProductoId",
							animation : false,
							maxSelectedItems : 1
						});

						grupoProductoList = $("#grupoProductoId").data("kendoMultiSelect");

						$("#codigoEnsurance").kendoNumericTextBox({
							format : "#"
						});

						

						cargar();

						$("#save-record")
								.bind(
										{
											click : function() {
                                                
												grupoProductoId = $(
														"#grupoProductoId option:selected")
														.val();
												nombre = $("#nombre").val();
														
												codigoEnsurance = ($("#codigoEnsurance")
														.data("kendoNumericTextBox"))
														.value();
												

												if (typeof grupoProductoId === 'undefined'
														|| typeof codigoEnsurance === 'undefined'
														|| typeof nombre === 'undefined'
														) {
													$("#grupoProductoId").css("border",
															"1px solid red");
													$("#nombre").css(
															"border",
															"1px solid red");
													$("#codigoEnsurance").css(
															"border",
															"1px solid red");
													
													alert("Por favor ingrese el campo requerido");
													return false;
												}

												tipoConsulta = "crear";
												enviarDatos(grupoProductoId, nombre,
														codigoEnsurance,codigo,
														tipoConsulta);
											}

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
					url : '../ActividadEconomicaController',
					data : {
						"tipoConsulta" : "encontrarTodos"
					},
					type : 'POST',
					datatype : 'json',
					success : function(data) {
						var listadoActividadesEconomicas = data.listadoActividadesEconomicas;
						$
								.each(
										listadoActividadesEconomicas,
										function(index) {
											$("#dataTableContent")
													.append(
															"<tr class='odd gradeX'>"
																	+ "<td relation='ramoNombre'>"
																	+ listadoActividadesEconomicas[index].nombre
																	+ "</td>"
																	+ "<td relation='nombreGrupo'>"
																	+ listadoActividadesEconomicas[index].nombreGrupo
																	+ "</td>"
																	+ "<td relation='codigoEnsurance'>"
																	+ listadoActividadesEconomicas[index].codigoEnsurance
																	+ "</td>"
																	+ "<td width='175px'>"
																	+ " <button type='button'  name='actualizar-btn' data-toggle='modal' data-target='#add' class='btn btn-success btn-xs actualizar-btn' onclick='actualizar("
																	+ listadoActividadesEconomicas[index].codigo
																	+ ")'>"
																	+ " <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar"
																	+ " </button>"
																	+ " <button type='button' class='btn btn-danger btn-xs eliminar-btn' onclick='eliminar("
																	+ listadoActividadesEconomicas[index].codigo
																	+ ")'>"
																	+ "<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar"
																	+ " </button>"
																	+ "</td>"
																	+ "</tr>");
										});
						$("#loading").remove();

						/*Cargo el select de GrupoProducto*/
						grupoProductoList.dataSource.filter({});
						grupoProductoList.setDataSource(data.grupoProductoArr);
						
					}
				});
	}

	function enviarDatos(grupoProductoId, nombre, codigoEnsurance, codigo,
			tipoConsulta) {
		$.ajax({
			url : '../ActividadEconomicaController',
			async : false,
			data : {
				"grupoProductoId" : grupoProductoId,
				"nombre" : nombre,
				"codigo" : codigo,
				"codigoEnsurance" : codigoEnsurance,
				"tipoConsulta" : tipoConsulta
			},
			type : 'POST',
			datatype : 'json',
			success : function(data) {
				$("#msgPopup").show();
			}
		});
	}

	function eliminar(id) {
		var r = confirm("Seguro que desea eliminar");
		if (r == true) {
			codigo = id;
			grupoProductoId = "";
			nombre = "";
			codigoEnsurance = "";
			tipoConsulta = "eliminar";
			enviarDatos(grupoProductoId, nombre, codigoEnsurance,codigo, 
					tipoConsulta);
		}
		cargar();
	}

	function actualizar(id) {
        
		 $("#nombre").val("");
		 ($("#codigoEnsurance").data("kendoNumericTextBox")).value("");
		

		if (typeof id === 'undefined')
			return false;

		$.ajax({
			url : '../ActividadEconomicaController',
			async : false,
			data : {
				"codigo" : id,
				"tipoConsulta" : "obtenerPorId"
			},
			type : 'POST',
			datatype : 'json',
			success : function(data) {
				codigo = data.codigo;
				$("#grupoProductoId").data("kendoMultiSelect").value(data.grupoProductoId);
				$("#nombre").val(data.nombre);
				($("#codigoEnsurance").data("kendoNumericTextBox")).value(data.codigoEnsurance);
				
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
							<h4 class="modal-title" id="myModalLabel">Actividades Economicas</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">El texto
								 se grabo con exito.</div>
							<div class="form-group">
								<table style="width: 100%">
									<tr>
										<td><label>Grupo Producto: </label> <select id="grupoProductoId"
											class="required" data-placeholder="Seleccione una opción..."></select>
										</td>
									</tr>
									<tr>
										<td><label>Actividad Economica:</label> 
											<textarea class="form-control" id="nombre"></textarea>
										</td>
									</tr>
									<tr>
										<td><label>Codigo Ensurance:</label> <input type="text"
											id="codigoEnsurance" style="width: 100%"></td>
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
								<th>Actividad</th>
								<th>Grupo Producto</th>
								<th>Codigo Ensurance</th>
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
