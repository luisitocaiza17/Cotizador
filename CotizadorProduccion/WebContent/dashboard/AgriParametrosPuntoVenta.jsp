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
	var puntoVentaId = "";
	var tipoCalculoId = "";
	var agenteId="";
	var contenedorEnsuranceId = "";
	var plantillaEnsuranceId = "";
	var codigoIntegracion = "";	
	var tieneEmisionDirecta = "";
	var emailPuntoVenta="";
	var formaNotificacion="";
	var beneficiarioId="";
	var tieneEmisionObligatoria="";
    var canalIdList="";
	var puntoVentaList = "";
	var tipoCalculoList = "";
	var agenteList="";

	$(document)
			.ready(
					function() {
						activarMenu("AgriParametrosPuntoVenta");

						$("#puntoVentaId").kendoMultiSelect({
							dataTextField : "nombre",
							dataValueField : "codigo",
							animation : false,
							maxSelectedItems : 1
						});
						
						$("#canalId").kendoMultiSelect({
							dataTextField : "nombre",
							dataValueField : "id",
							animation : false,
							maxSelectedItems : 1
						});
						
						puntoVentaList = $("#puntoVentaId").data(
						"kendoMultiSelect");
						
						canalIdList= $("#canalId").data(
						"kendoMultiSelect");
						
						$("#tipoCalculoId").kendoMultiSelect({
							dataTextField : "tipoCalculoNombre",
							dataValueField : "tipoCalculoId",
							animation : false,
							maxSelectedItems : 1
						});
						
						tipoCalculoList = $("#tipoCalculoId").data(
						"kendoMultiSelect");
						
						$("#agenteId").kendoMultiSelect({
							dataTextField : "nombre",
							dataValueField : "id",
							animation : false,
							maxSelectedItems : 1
						});
						
						agenteList = $("#agenteId").data("kendoMultiSelect");

						cargar();

						/*Inicio controloes grabar*/

						$("#save-record").bind({click : function() {	
							var validator = $("#formCrud").kendoValidator().data("kendoValidator");
							$(".required").css("border", "1px solid #ccc");
							if (validator.validate() === false) {
								$(".required").each(
									function(index) {
									var cadena = $(this).val();
									if (cadena.length <= 0) {
										$(this).css("border","1px solid red");
										alert("Por favor ingrese el campo requerido");
										$(this).focus();
										return false;
									}
								});
							}else{			
										puntoVentaId = $("#puntoVentaId option:selected").val();
										canalId=$("#canalId option:selected").val();
										tipoCalculoId = $("#tipoCalculoId option:selected").val();
										contenedorEnsuranceId = $("#contenedorEnsuranceId").val();
										plantillaEnsuranceId = $("#plantillaEnsuranceId").val();
										codigoIntegracion = $("#codigoIntegracion").val();
										
										emailPuntoVenta=$("#emailPuntoVenta").val();
										formaNotificacion=$("#formaNotificacion").val();
										beneficiarioId=$("#beneficiarioId").val();
										excepcionesCultivos=$("#tiposCultivosExcepxion").val();
										
										tieneEmisionDirecta = $('#tieneEmisionDirecta').is(':checked');
										tieneEmisionObligatoria = $('#tieneEmisionObligatoria').is(':checked');
										
										tipoConsulta = "crear";									

										enviarDatos(puntoVentaId,tipoCalculoId,codigoIntegracion,tieneEmisionDirecta,tipoConsulta,
												emailPuntoVenta,formaNotificacion,beneficiarioId,tieneEmisionObligatoria,excepcionesCultivos);
									}
							}

						});
						
						///TODO: al seleccionar el combo canal
						$("#canalId").change(function(){	
							$("#puntoVentaId").children().remove();
							$("select option:selected").each(function(){
								CargarCombo();
							});
						});
				});
	
	
	function CargarCombo() {
		var listPuntoVenta="";
		var puntoId = $("#canalId option:selected").val() ? $(
		"#canalId option:selected").val() : "";
		if (puntoId==""){
			$("#puntoVentaId").children().remove();
		}
		else {
			$.ajax({
				url : '../AgriCotizacionAprobacionController',
				data : {
					"tipoConsulta" : "cargarCombosPuntoVenta",
					"canalId" : puntoId,
				},
				async : false,
				type : 'post',
				datatype : 'json',
				success : function(data) {
					
					$("#puntoVentaId").children().remove();
					$("#puntoVentaId").append("<option value=''>Seleccione una opci�n</option>");
					listPuntoVenta = data.listPuntoVenta;
					$.each(listPuntoVenta, function (index){								
						$("#puntoVentaId").append("<option value='"+ listPuntoVenta[index].puntoVentaId +"'>"+ listPuntoVenta[index].nombre +"</option>");
					});
					puntoVentaList.dataSource.filter({});
					puntoVentaList.setDataSource(listPuntoVenta);
				}
			});
		}
	}

	function cargar() {

		$("#dataTableContent").children().remove();
		$.ajax({
			url : '../AgriCanalController',
			data : {
				"tipoConsulta" : "encontrarTodos"
			},
			async : false,
			type : 'POST',
			datatype : 'json',
			success : function(data) {
				/*Activo los select*/
				canalIdList.enable(true);

				/*Cargo el select puntoVenta*/
				canalIdList.dataSource.filter({});
				canalIdList.setDataSource(data.canalesJSONArray);

			}
		});
		
		
		//Carga los beneficiarios
		$.ajax({
		url : '../BeneficiarioController',
		data : {
			"tipoConsulta" : "cargarSelect3"
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
			$("#beneficiarioId").append("<option value=''>Seleccione una opcion</option>");
			$.each(beneficiarios, function (index) {
				$("#beneficiarioId").append("<option value='" + beneficiarios[index].id + "'>" + beneficiarios[index].text + "</option>");
			});
			}
		});
		
		/**Cargamos los agentes**/
		$.ajax({
				url : '../AgriParametrosPuntoVentaController',
				data : {
					"tipoConsulta" : "buscarAgente"
				},
				async : false,
				type : 'post',
				datatype : 'json',
				success : function(data) {					
					$("#agenteId").children().remove();
					agenteList.dataSource.filter({});
					agenteList.setDataSource(data.listaAgentes);
				}
			});
		
		$.ajax({
					url : '../AgriParametrosPuntoVentaController',
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
																	+ "<td relation='puntoVentaNombre' style='width: 25%; text-align: left;' class='sorting' >"
																	+ parametroArr[index].puntoVentaNombre
																	+ "</td>"
																	+ "<td relation='tipoCalculo' style='width: 15%; text-align: left;' class='sorting' >"
																	+ parametroArr[index].tipoCalculoNombre
																	+ "</td>"																	
																	+ "<td relation='codigoIntegracion' style='width: 15%; text-align: left;' class='sorting' >"
																	+ parametroArr[index].codigoIntegracion
																	+ "</td>"
																	+ "<td relation='tieneEmisionDirecta' style='width: 5%; text-align: left;' class='sorting' >"
																	+ parametroArr[index].tieneEmisionDirecta
																	+ "</td>"
																	+ "<td style='width: 20%;'>"
																	+ " <button type='button'  name='actualizar-btn' data-toggle='modal' data-target='#add' class='btn btn-success btn-xs actualizar-btn' onclick='actualizar("
																	+ parametroArr[index].puntoVentaId
																	+ ")'>"
																	+ " <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar"
																	+ " </button>"
																	+ " <button type='button' class='btn btn-danger btn-xs eliminar-btn' onclick='eliminar("
																	+ parametroArr[index].puntoVentaId
																	+ ")'>"
																	+ "<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar"
																	+ " </button>"
																	+ "</td>"
																	+ "</tr>");
										});
						
						tipoCalculoList.dataSource.filter({});
						tipoCalculoList.setDataSource(data.tipoCalculoArr);

						$("#loading").remove();

					}
				});
	}

	function actualizar(puntoVenta) {
		
		/**Cargamos los puntos de venta**/
		$.ajax({
			url : '../AgriParametrosPuntoVentaController',
			data : {
				"tipoConsulta" : "encontrarTodosPuntoVenta"
			},
			async : false,
			type : 'POST',
			datatype : 'json',
			success : function(data) {
				/*Activo los select*/
				puntoVentaList.enable(true);

				/*Cargo el select puntoVenta*/
				puntoVentaList.dataSource.filter({});
				puntoVentaList.setDataSource(data.listadoPuntoVenta);

			}
		});
		
		
		$("#tipoCalculoId").val("");
		$("#emailPuntoVenta").val("");
		$("#formaNotificacion").val("");
		$("#beneficiarioId").val("");
		$("#tiposCultivosExcepxion").val("");
		$("#codigoIntegracion").val("");
		$("#tieneEmisionDirecta").prop('checked', false);
		$("#tieneEmisionObligatoria").prop('checked', false);

		if (typeof puntoVenta === 'undefined')
			return false;

		$.ajax({
			url : '../AgriParametrosPuntoVentaController',
			data : {
				"tipoConsulta" : "buscarPorId",
				"puntoVentaId" : puntoVenta
			},
			type : 'POST',
			datatype : 'json',
			success : function(data) {				
				$("#puntoVentaId").data("kendoMultiSelect").value(data.puntoVentaId);
				$("#agenteId").data("kendoMultiSelect").value(data.agenteId);
				$("#canalId").data("kendoMultiSelect").value(data.canalId);
				$("#tipoCalculoId").data("kendoMultiSelect").value(data.tipoCalculoId);
				$("#codigoIntegracion").val(data.codigoIntegracion);
				$("#canalId").data("kendoMultiSelect").value(data.canalId);
				$("#emailPuntoVenta").val(data.emailPuntoVenta);
				$("#formaNotificacion").val(data.formaNotificacion);
				$("#beneficiarioId").val(data.beneficiarioId);
				$("#comision").val(data.comision);
				$("#tiposCultivosExcepxion").val(data.excepcionesCultivos);
				
				if (data.tieneEmisionDirecta) {
					$("#tieneEmisionDirecta").prop('checked', true);
				} else {
					$("#tieneEmisionDirecta").prop('checked', false);
				}
				if (data.tieneEmisionObligatoria) {
					$("#tieneEmisionObligatoria").prop('checked', true);
				} else {
					$("#tieneEmisionObligatoria").prop('checked', false);
				}
			}
		});

	}

	function eliminar(puntoVenta) {
		var r = confirm("Seguro que desea eliminar");
		if (r) {
			puntoVentaId = puntoVenta;
			tipoCalculoId = "";
			plantillaEnsuranceId = "";
			contenedorEnsuranceId = "";
			codigoIntegracion = "";
			tieneEmisionDirecta = "";
			emailPuntoVenta="";
			formaNotificacion="";
			beneficiarioId="";
			tieneEmisionObligatoria="";
			excepcionesCultivos="";
			tipoConsulta = "eliminar";

			enviarDatos(puntoVentaId,tipoCalculoId,codigoIntegracion,tieneEmisionDirecta,tipoConsulta,emailPuntoVenta,formaNotificacion,beneficiarioId,tieneEmisionObligatoria,excepcionesCultivos);
			cargar();
		}
	}

	function enviarDatos(puntoVentaId,tipoCalculoId,codigoIntegracion,tieneEmisionDirecta,tipoConsulta,
			emailPuntoVenta,formaNotificacion,beneficiarioId,tieneEmisionObligatoria,excepcionesCultivos) {
		
		canalId=$("#canalId option:selected").val();
		var agenteSeleccionado= $("#agenteId option:selected").val();
		var comisionSeleccionada=$("#comision").val();
		$.ajax({
			url : '../AgriParametrosPuntoVentaController',
			async : false,
			data : {
				"canalId":canalId,
				"tipoConsulta" : tipoConsulta,
				"agenteId":agenteSeleccionado,
				"comision":comisionSeleccionada,
				"puntoVentaId" : puntoVentaId,
				"tipoCalculoId" : tipoCalculoId,
				"emailPuntoVenta" :emailPuntoVenta,
				"formaNotificacion" : formaNotificacion,
				"beneficiarioId" : beneficiarioId,
				"codigoIntegracion" : codigoIntegracion,
				"excepcionesCultivos" : excepcionesCultivos,
				"tieneEmisionDirecta" : tieneEmisionDirecta,
				"tieneEmisionObligatoria" : tieneEmisionObligatoria
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
								configuraci�n se grabo con exito.</div>
							<div class="form-group">
								<input type="hidden" class="form-control" id="parametroPPVId">
								<input type="hidden" id="idCotizacion">
								<table class="table table-striped" style="width: 100%">
									<tr>
										<td colspan="2"><label style="width: 100%">Sponsor: *</label></td>
									</tr>
									<tr>
										<td colspan="2"><select id="canalId" name="canalId" style ="width: 450px"
											data-placeholder="Seleccione una opci�n..." validationMessage="Campo requerido!!!" required>											
										</select></td>
									</tr>
									<tr>
										<br>&nbsp;
									</tr>
									<tr>
										<td colspan="2"><label style="width: 100%">Punto de Venta: *</label></td>
									</tr>
									<tr>
										<td colspan="2"><select id="puntoVentaId" name="puntoVentaId" style ="width: 450px"
											data-placeholder="Seleccione una opci�n..." validationMessage="Campo requerido!!!" required>											
										</select></td>
									</tr>
									<tr>
										<td colspan="2"><label style="width: 100%">Tipo de Calculo: *</label></td>
									</tr>									
									<tr>
										<td colspan="2"><select id="tipoCalculoId" name="tipoCalculoId"  style ="width: 450px"
											data-placeholder="Seleccione una opci�n..." validationMessage="Campo requerido!!!" required>
										</select></td>
									</tr>
									<tr>
										<td colspan="2"><label style="width: 100%">Beneficiario para el Endoso: *</label></td>
									</tr>
									<tr>
										<td colspan="2"><select id="beneficiarioId" name="beneficiarioId"  style ="width: 450px"
											data-placeholder="Seleccione una opci�n si aplica...">
										</select></td>
									</tr>
									<tr>
										<td colspan="2"><label style="width: 100%">Agente para la Emision: *</label></td>
									</tr>
									<tr>
										<td colspan="2"><select id="agenteId" name="agenteId"  style ="width: 450px"
											data-placeholder="Seleccione una opci�n si aplica...">
										</select></td>
									</tr>
									<tr><br>
										<td><label style="width: 100%">Email para facturacion Electronica: *</label></td>
										<td><input type="email" id="emailPuntoVenta"></td>

									</tr>								
									<tr>
										<td><label style="width: 100%">Forma de Notificaci�n: *</label></td>
										<td><select id="formaNotificacion">
												<option value="1">General</option>
												<option value="2">Web Service(Sucre)</option>
												<option value="3">Por email</option>
											</select>
										</td>


									</tr>
									<tr>
										<td><label style="width: 100%">C�digo Integraci�n: *</label></td>
										<td><input type="text" id="codigoIntegracion"></td>
									</tr>
									<tr>
										<td><label style="width: 100%">Porcentaje de comision para Emision: *</label></td>
										<td><input type="text" id="comision"></td>
									</tr>
									<tr>
										<td><label style="width: 100%">Emite Directamente : *</label></td>
										<td><input type="checkbox" id="tieneEmisionDirecta"></td>
									</tr>
									<tr>
										<td><label style="width: 100%">Tipos de Cultivo Excepciones:</label></td>
										<td><input type="text" id="tiposCultivosExcepxion"></td>
									</tr>
									
									<tr>
										<td><label style="width: 100%">Emisi�n Obligatoria ?:</label></td>
										<td><input type="checkbox" id="tieneEmisionObligatoria"></td>
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
								<th>Punto de Venta</th>
								<th>Tipo de Calculo</th>	
								<th>C�digo Integraci�n</th>						
								<th>Emision Directa</th>
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
