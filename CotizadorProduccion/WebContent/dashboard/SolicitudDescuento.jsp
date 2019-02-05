<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Solicitud Descuento - CotizadorQBE</title>

<!-- Core CSS - Include with every page -->
<link
	href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="../static/css/loading.css" rel="stylesheet">

<script
	src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
<script
	src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="../static/js/util.js"></script>
<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
	var codigo = "";
	var nombre = "";
	var descripcion = "";
	var porcentaje = "";

	$(document).ready(function() {
		activarMenu("SolicitudDescuento");
						$("#addButton").hide();

						function cargar() {$.ajax({
										url : '../SolicitudDescuentoController',
										data : {
											"tipoConsulta" : "encontrarDescuentosUsuario"
										},
										type : 'POST',
										datatype : 'json',
										success : function(data) {
											$(".loading").fadeOut();
											if(data.success){
											if (data.numRegistros > 0) {
												var listadoSolicitudDescuento = data.listadoSolicitudDescuento;
												$.each(listadoSolicitudDescuento,
																function(index) {

																		var aux = "	<tr class='odd gradeX'>"
																			+ " <td relation='cotizacionId'>"
																			+ listadoSolicitudDescuento[index].cotizacionId
																			+ "</td>"
																			+ " <td relation='nombre'>"
																			+ listadoSolicitudDescuento[index].nombre
																			+ "</td>"
																			+ " <td relation='descripcion'>"
																			+ listadoSolicitudDescuento[index].descripcion
																			+ "</td>"
																			+ " <td relation='porcentaje'>"
																			+ listadoSolicitudDescuento[index].porcentaje
																			+ "</td>"
																			+ " <td'>"
																			+ " <input id='codigo' type='hidden' value='"+ listadoSolicitudDescuento[index].codigo +"'/>"
																			+ " <td relation='estado'>"
																			+ listadoSolicitudDescuento[index].estado
																			+ "</td>"
																			+ "";

																		if (listadoSolicitudDescuento[index].estado == 'Pendiente') {
																			aux += " <td width='175px'>"
																			+ " <button type='button' class='btn btn-success btn-xs ' onclick='window.open(\""+listadoSolicitudDescuento[index].urlAutorizar+"\")'>"
																			+ " <span class='glyphicon glyphicon glyphicon-edit'></span> Procesar"
																			+ " </button>"
																			+ "</td></tr>" ;
																		} else {
																			aux += " <td relation='usuario'>"
																					+ listadoSolicitudDescuento[index].usuario
																					+ "</td></tr>";
																		}

																		$("#dataTableContent").append(aux);

																	});

													/* Inicio Controles Actualizar Registro*/
													$(".actualizar-btn")
															.bind(
																	{
																		click : function() {
																			//$("#addButton").trigger("click");
																			var r = confirm("Seguro que desea aceptar el Descuento "
																					+ $(
																							this)
																							.parent()
																							.parent()
																							.children()
																							.first()
																							.text());
																			if (r == true) {
																				$(
																						"#dataTableContent > tr")
																						.remove();
																				$(
																						"#dataTableContent")
																						.append(
																								"<div id='loading' class='loading'><div class='loading-indicator'><img src='../static/images/ajax-loader.gif'/><br /><br /><span id='loading-msg'>Cargando...</span></div></div>");
																				$(
																						"#codigo")
																						.val(
																								$(
																										this)
																										.parent()
																										.children()
																										.first()
																										.val());
																				codigo = $(
																						this)
																						.parent()
																						.children()
																						.first()
																						.val();
																				var elem = $(
																						this)
																						.parent();
																				var estado = "Aprobado";
																				var tipoConsulta = "ActualizarEstado";
																				enviarDatos(
																						codigo,
																						estado,
																						tipoConsulta);
																			}
																		}
																	});

													$(".eliminar-btn")
															.bind(
																	{
																		click : function() {
																			var r = confirm("Seguro que desea rechazar el Descuento "
																					+ $(
																							this)
																							.parent()
																							.parent()
																							.children()
																							.first()
																							.text());
																			if (r == true) {
																				$(
																						"#dataTableContent > tr")
																						.remove();
																				$(
																						"#dataTableContent")
																						.append(
																								"<div id='loading' class='loading'><div class='loading-indicator'><img src='../static/images/ajax-loader.gif'/><br /><br /><span id='loading-msg'>Cargando...</span></div></div>");
																				codigo = $(
																						this)
																						.parent()
																						.children()
																						.first()
																						.val();
																				var tipoConsulta = "ActualizarEstado";
																				var estado = "Rechazado";
																				enviarDatos(
																						codigo,
																						estado,
																						tipoConsulta);
																			}
																		}
																	});
													/* Fin Controles Elminar Registro */
												} else {
													$("#dataTableContent")
															.append(
																	"<tr><td colspan='7'>No existen Registros</td></tr>");
												}
											} else {
												alert(data.error);
											}
										}
									});
						}

						var urlParams;
						(window.onpopstate = function() {
							var match, pl = /\+/g, // Regex for replacing addition symbol with a space
							search = /([^&=]+)=?([^&]*)/g, decode = function(s) {
								return decodeURIComponent(s.replace(pl, " "));
							}, query = window.location.search.substring(1);

							urlParams = {};
							while (match = search.exec(query))
								urlParams[decode(match[1])] = decode(match[2]);
						})();
						if (urlParams.codigo == null || urlParams.codigo == "")
							cargar();
						else
							cargarPorId(urlParams.codigo);

						function cargarPorId(codigo) {
							$
									.ajax({
										url : '../SolicitudDescuentoController',
										data : {
											"tipoConsulta" : "encontrarPorId",
											"codigo" : codigo
										},
										type : 'POST',
										datatype : 'json',
										success : function(data) {
											$(".loading").fadeOut();
											if (data.numRegistros > 0) {
												var listadoSolicitudDescuento = data.listadoSolicitudDescuento;
												$
														.each(
																listadoSolicitudDescuento,
																function(index) {
																	if (listadoSolicitudDescuento[index].estado == 'Pendiente')//estado pendiente
																	{
																		$(
																				"#dataTableContent")
																				.append(
																						"	<tr class='odd gradeX'>"
																								+ " <td relation='cotizacionId'>"
																								+ listadoSolicitudDescuento[index].cotizacionId
																								+ "</td>"
																								+ " <td relation='nombre'>"
																								+ listadoSolicitudDescuento[index].nombre
																								+ "</td>"
																								+ " <td relation='descripcion'>"
																								+ listadoSolicitudDescuento[index].descripcion
																								+ "</td>"
																								+ " <td relation='porcentaje'>"
																								+ listadoSolicitudDescuento[index].porcentaje
																								+ "</td>"
																								+ " <td width='175px'>"
																								+ " <input id='codigo' type='hidden' value='"+ listadoSolicitudDescuento[index].codigo +"'/>"
																								+ " <button type='button' class='btn btn-success btn-xs actualizar-btn'>"
																								+ " <span class='glyphicon glyphicon glyphicon-edit'></span> Aprobar"
																								+ " </button>"
																								+ " <button type='button' class='btn btn-danger btn-xs eliminar-btn'>"
																								+ "<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Rechazar"
																								+ " </button>"
																								+ "</td>"
																								+ "</tr>");
																	} else {
																		var aux = "	<tr class='odd gradeX'>"
																				+ " <td relation='nombre'>"
																				+ listadoSolicitudDescuento[index].nombre
																				+ "</td>"
																				+ " <td relation='descripcion'>"
																				+ listadoSolicitudDescuento[index].descripcion
																				+ "</td>"
																				+ " <td relation='porcentaje'>"
																				+ listadoSolicitudDescuento[index].porcentaje
																				+ "</td>";
																		if (listadoSolicitudDescuento[index].estado == 'Aprobado')//estado aprobado
																			aux += " <td > Aprobado </td>";
																		if (listadoSolicitudDescuento[index].estado == 'Rechazado')//estado aprobado
																			aux += " <td > Rechazado </td>";

																		$(
																				"#dataTableContent")
																				.append(
																						aux
																								+ "</tr>");
																	}

																});

												/* Inicio Controles Actualizar Registro*/
												$(".actualizar-btn")
														.bind(
																{
																	click : function() {
																		//$("#addButton").trigger("click");
																		var r = confirm("Seguro que desea aceptar el Descuento "
																				+ $(
																						this)
																						.parent()
																						.parent()
																						.children()
																						.first()
																						.text());
																		if (r == true) {
																			$(
																					"#dataTableContent > tr")
																					.remove();
																			$(
																					"#dataTableContent")
																					.append(
																							"<div id='loading' class='loading'><div class='loading-indicator'><img src='../static/images/ajax-loader.gif'/><br /><br /><span id='loading-msg'>Cargando...</span></div></div>");
																			$(
																					"#codigo")
																					.val(
																							$(
																									this)
																									.parent()
																									.children()
																									.first()
																									.val());
																			codigo = $(
																					this)
																					.parent()
																					.children()
																					.first()
																					.val();
																			var elem = $(
																					this)
																					.parent();
																			var estado = "Aprobado";
																			var tipoConsulta = "ActualizarEstado";
																			enviarDatos(
																					codigo,
																					estado,
																					tipoConsulta);
																		}
																	}
																});

												$(".eliminar-btn")
														.bind(
																{
																	click : function() {
																		var r = confirm("Seguro que desea rechazar el Descuento "
																				+ $(
																						this)
																						.parent()
																						.parent()
																						.children()
																						.first()
																						.text());
																		if (r == true) {
																			$(
																					"#dataTableContent > tr")
																					.remove();
																			$(
																					"#dataTableContent")
																					.append(
																							"<div id='loading' class='loading'><div class='loading-indicator'><img src='../static/images/ajax-loader.gif'/><br /><br /><span id='loading-msg'>Cargando...</span></div></div>");
																			codigo = $(
																					this)
																					.parent()
																					.children()
																					.first()
																					.val();
																			var tipoConsulta = "ActualizarEstado";
																			var estado = "Rechazado";
																			enviarDatos(
																					codigo,
																					estado,
																					tipoConsulta);
																		}
																	}
																});
												/* Fin Controles Elminar Registro */
											} else {
												$("#dataTableContent")
														.append(
																"<tr><td colspan='7'>No existen Registros</td></tr>");
											}
										}
									});
						}

						function getParameterByName(name) {
							name = name.replace(/[\[]/, "\\[").replace(/[\]]/,
									"\\]");
							var regex = new RegExp("[\\?&]" + name
									+ "=([^&#]*)"), results = regex
									.exec(location.search);
							return results == null ? ""
									: decodeURIComponent(results[1].replace(
											/\+/g, " "));
						}

						function enviarDatos(codigo, estado, tipoConsulta) {
							$.ajax({
								url : '../SolicitudDescuentoController',
								data : {
									"codigo" : codigo,
									"estadoId" : estado,
									"tipoConsulta" : tipoConsulta
								},
								type : 'POST',
								datatype : 'json',
								success : function(data) {
									$("#msgPopup").show();
									if (tipoConsulta == 'ActualizarEstado')
										cargar();
								}
							});

						}
					});
</script>
</head>
<body>
<%
			// Permitimos el acceso si la session existe		
				if(session.getAttribute("login") == null){
				    response.sendRedirect("/CotizadorWeb");
				}
				else {
					com.qbe.cotizador.model.Usuario usuario = (com.qbe.cotizador.model.Usuario) session
							.getAttribute("usuario");

					// Obtenemos el rol asociado al usuario
					com.qbe.cotizador.dao.seguridad.UsuarioRolDAO usuarioRolDAO = new com.qbe.cotizador.dao.seguridad.UsuarioRolDAO();
					com.qbe.cotizador.model.UsuarioRol usuarioRol = usuarioRolDAO
							.buscarPorUsuario(usuario);
					com.qbe.cotizador.model.Rol rol = usuarioRol.getRol();
					com.qbe.cotizador.dao.seguridad.OpcionMenuRolDAO opcionMenuRolDAO = new com.qbe.cotizador.dao.seguridad.OpcionMenuRolDAO();
					if (!opcionMenuRolDAO.verificarPermiso(rol.getId(),
							"Solicitudes de Descuento")) {
						response.sendRedirect("/CotizadorWeb/dashboard/AccesoDenegado.jsp");
					}
				}
%>
	<div class="row crud-nav-bar">
		<!-- Button trigger modal -->
		<!--  <button class="btn btn-primary" data-toggle="modal" data-target="#add" id="addButton">
			<span class="glyphicon glyphicon-plus"></span> &nbsp; Nuevo
		</button> -->

		<!-- Modal -->
		<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form id="formCrud">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Nuevo Descuento</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">El Descuento se grabo con exito.</div>
							<div class="form-group">
								<input type="hidden" class="form-control"> <label>Nombre</label>
								<input type="text" class="form-control" id="nombre"> <label>Descripcion</label>
								<input type="text" class="form-control" id="descripcion">
								<label>Porcentaje</label> <input type="number"
									class="form-control" id="porcentaje">
							</div>
						</div>
					</form>	
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" id="close-popup"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="save-record">Save
						changes</button>
				</div>
				
			</div>
		</div>
	</div>	
	<!-- Modal -->


	<!-- Datatable -->
	<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">
				<div class="input-group">
					<span class="input-group-addon">Filtro</span> <input id="filter"
						type="text" class="form-control"
						placeholder="Escriba la palabra a buscar...">
				</div>
				<br>
				<table class="table table-striped table-bordered table-hover"
					id="dataTable">
					<thead>
						<tr>
							<th>Cotización</th>
							<th>Nombre</th>
							<th>Descripción</th>
							<th>Porcentaje</th>
							<th>Estado</th>
							<th>Respuesta por</th>							
						</tr>
					</thead>
					<tbody id="dataTableContent" class="searchable">

						<div id="loading" class="loading" style="position: fixed;left: 50%;">
							<div class="loading-indicator">
								<img src="../static/images/ajax-loader.gif" /><br /> <br /> <span
									id="loading-msg">Buscando Solicitudes de Descuento ...</span>
							</div>
						</div>

					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- Datatable -->
</body>
</html>