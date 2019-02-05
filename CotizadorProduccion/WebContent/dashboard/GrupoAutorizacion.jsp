<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Grupo Autorización - CotizadorQBE</title>
	
	<!-- Core CSS - Include with every page -->
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	<link href="../static/css/loading.css" rel="stylesheet">
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="../static/js/util.js"></script>
	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>
		var codigo = "";
		var nombre = "";
		var lider = "";
		var activo = 0;
		var tipoConsulta = "";
		var arrlistadoEmpleadosV = new Array();
		var arrCodigolistadoEmpleadosV = new Object();
		var arrlistadoEmpleadosG = new Array();
		var arrCodigolistadoEmpleadosG = new Object();
		var arrlistadoEmpleadosD = new Array();
		var arrCodigolistadoEmpleadosD = new Object();
		var tablaEmpleados = "";
		
		$(document).ready(function() {
			activarMenu("GrupoAutorizacion");
			
			$.ajax({
				url : '../UnidadNegocioController',
				data : {
					"tipoConsulta" : "encontrarTodos"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					if(data.numRegistros > 0){
						var listadoUN = data.listadoUN;
						var options = "";
						$.each(listadoUN, function(index){
							options = options + "<option value='"+ listadoUN[index].nombre +"' codigoUnidadNegocio='"+listadoUN[index].codigo+"' codigoUnidadNegocioEnsurance='"+ listadoUN[index].un_ensurance +"'>" + listadoUN[index].nombre + "</option>";					
						});
						
					}
					$("#unidad").append(options);
				}
			});					
			
			$.ajax({
				url : '../GrupoAutorizacionController',
				data : {
					"tipoConsulta" : "encontrarTodos"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					$("#loading").remove();
					if(data.numRegistros > 0){
						var listadoGrupoAutorizacion = data.listadoGrupoAutorizacion;
						var listadoEmpleadosV = data.listadoEmpleadosV;
						var listadoEmpleadosG = data.listadoEmpleadosG;
						var listadoEmpleadosD = data.listadoEmpleadosD;
						$.each(listadoGrupoAutorizacion, function(index){
							$("#dataTableContent").append("	<tr class='odd gradeX'>" +
									" <td relation='unidad'>"+ listadoGrupoAutorizacion[index].unidadNegocioNombre +"</td>" +
									" <td relation='lider'>"+ listadoGrupoAutorizacion[index].lider +"</td>" +
									" <td relation='activo'>"+ listadoGrupoAutorizacion[index].activo +"</td>" +
									" <td width='175px'>" +
										" <input type='hidden' value='"+ listadoGrupoAutorizacion[index].codigo +"'/>" +
										" <button type='button' class='btn btn-success btn-xs actualizar-btn'>" +
		  									" <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
										" </button>" +
										" <button type='button' class='btn btn-danger btn-xs eliminar-btn'>" +
										  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar" +
										" </button>" +
									"</td>" +
								"</tr>");
					
						});
						
						$.each(listadoEmpleadosV, function(index){
				    		arrlistadoEmpleadosV.push(listadoEmpleadosV[index].nombre);
			                arrCodigolistadoEmpleadosV[listadoEmpleadosV[index].nombre] = listadoEmpleadosV[index].codigoEmpleado;
			                $("#lider").append("<option value='"+listadoEmpleadosV[index].nombre+"'>"+listadoEmpleadosV[index].nombre+"</option>");
						});
						
						tablaEmpleados = "<table class='tablaPersonasAutorizan'>";
						$.each(listadoEmpleadosG, function(index){
				    		//arrlistadoEmpleadosG.push(listadoEmpleadosG[index].nombre);
			                //arrCodigolistadoEmpleadosG[listadoEmpleadosG[index].nombre] = listadoEmpleadosG[index].codigoEmpleado;
			                //$("#panelGerentes"),children().last().append("<option value='"+listadoEmpleadosG[index].nombre+"'>"+listadoEmpleadosG[index].nombre+"</option>");
			                tablaEmpleados = tablaEmpleados + "<tr>"+
																"<td><input type='checkbox' name='gerente' value='"+listadoEmpleadosG[index].codigoEmpleado+"' class='radioGerente'></td>"+
																"<td><td>"+
																"<td>"+listadoEmpleadosG[index].nombre+"</td>"+
																"<td></td>"+
																"<td><input type='mail' id='mailGerente"+index+"' placeholder='Ingrese el mail' size='35' value='"+listadoEmpleadosG[index].mail+"' readonly></td>"+
															  "</tr>"; 
						});
						tablaEmpleados = tablaEmpleados + "</table>";
						$("#panelGerentes").children().last().append(tablaEmpleados);


						tablaEmpleados = "<table class='tablaPersonasAutorizan'>";
						$.each(listadoEmpleadosD, function(index){
				    		//arrlistadoEmpleadosG.push(listadoEmpleadosG[index].nombre);
			                //arrCodigolistadoEmpleadosG[listadoEmpleadosG[index].nombre] = listadoEmpleadosG[index].codigoEmpleado;
			                //$("#panelGerentes"),children().last().append("<option value='"+listadoEmpleadosG[index].nombre+"'>"+listadoEmpleadosG[index].nombre+"</option>");
			                tablaEmpleados = tablaEmpleados + "<tr>"+
																"<td><input type='checkbox' name='director' value='"+listadoEmpleadosD[index].codigoEmpleado+"' class='radioDirector'></td>"+
																"<td><td>"+
																"<td>"+listadoEmpleadosD[index].nombre+"</td>"+
																"<td></td>"+
																"<td><input type='mail' id='mailGerente"+index+"' placeholder='Ingrese el mail' size='35' value='"+listadoEmpleadosD[index].mail+"' readonly></td>"+
															  "</tr>"; 
						});
						tablaEmpleados = tablaEmpleados + "</table>";
						$("#panelDirectores").children().last().append(tablaEmpleados);
						
						/* Inicio Controles Actualizar Registro*/
						$(".actualizar-btn").bind({click: function() {
								$("#addButton").trigger("click");
								$("#codigo").val($(this).parent().children().first().val());
								var elem = $(this).parent();
								var bandera = 1;
								do {
									elem = elem.prev();
									if (elem.is("td")){
										var elemCode = elem.attr("relation");
										elementType(elem.text(), elemCode, $("#"+elemCode).attr("type"));
									}else {
										bandera = 0;
									}
								} while (bandera == 1);
								
								
								$.ajax({
									url : '../GrupoAutorizacionController',
									data : {
										"codigo" : $(this).parent().children().first().val(),
										"tipoConsulta" : "usuariosActivosGrupo",
									},
									type : 'POST',
									datatype : 'json',
									success : function(data) {
										var listadoUsuarioAutorizacion = data.listadoGrupoUsuarioAutorizacion;
										if(listadoUsuarioAutorizacion.length){
											$("#panelGerentes").hide();
											$("#panelDirectores").hide();
											$.each(listadoUsuarioAutorizacion, function(index){
												$('input[type="checkbox"]').each(function(){
								                    if($(this).attr("name") == listadoUsuarioAutorizacion[index].nombreGenerico && $(this).val() == listadoUsuarioAutorizacion[index].codigoEmpleado){
								                    	$(this).prop("checked",true);
								                    }
								                });
											});
											$("#panelGerentes").show("slow");
											$("#panelDirectores").show("slow");											
										}
									}
								});
							}
						});
						/* Fin Controles Actualizar Registro*/
						
						/* Inicio Controles Elminar Registro */
						$(".eliminar-btn").bind({click: function() {
								var r = confirm("Seguro que desea eliminar la sucursal " + $(this).parent().parent().children().first().text());
								if (r == true){
									codigo = $(this).parent().children().first().val();
									nombre = ""; lider = ""; activo = "";
									tipoConsulta = "eliminar";
									var listaGerentesSeleccionados = "";
									var listaDirectoresSeleccionados = "";
									enviarDatos(codigo, nombre, lider, activo, tipoConsulta, listaGerentesSeleccionados, listaDirectoresSeleccionados);
							    	$(this).parent().parent().remove();
								}
							}
						});	
						/* Fin Controles Elminar Registro */
					}else{
						$("#dataTableContent").append("<tr><td colspan='4'>No existen Registros</td></tr>");
					}
				}
			});			
			
			/* Inicio Controles Grabar Resgistro*/
				$("#save-record").click(function() {
					var retorno=true;
					$(".required").css("border", "1px solid #ccc");
					$(".required").each(function(index) {
						var cadena = $(this).val();
						if (cadena.length <= 0) {
							retorno = false;
							$(this).css("border", "1px solid red");
							alert("Por favor ingrese el campo requerido");
							$(this).focus();
							return false;
						}
					});
					
					if ($("#activo").is(':checked')) activo = 1;
					codigo = $("#codigo").val();
					unidadNegocioId = $("#unidad option:selected").attr("codigounidadnegocio");
					if (codigo == "")
						tipoConsulta = "crear";
					else
						tipoConsulta = "actualizar";
					lider = arrCodigolistadoEmpleadosV[$("#lider").val()];
					
					var listaGerentesSeleccionados = "";
					$("input:checkbox[name=gerente]:checked").each(function() {
						listaGerentesSeleccionados = listaGerentesSeleccionados + $(this).val()+",";
					});

					var listaDirectoresSeleccionados = ""
					$("input:checkbox[name=director]:checked").each(function() {
						listaDirectoresSeleccionados = listaDirectoresSeleccionados + $(this).val()+",";
					});
					if(retorno)
						enviarDatos(codigo, unidadNegocioId, lider, activo, tipoConsulta, listaGerentesSeleccionados, listaDirectoresSeleccionados);
				});
			/* Fin Controles Grabar Resgistro*/
			
			$("#close-popup").click(function(){
				$(".required").css("border", "1px solid #ccc");
				$("#unidad").val("");
			});
			
			function enviarDatos(codigo, unidadNegocioId, lider, activo, tipoConsulta, listaGerentesSeleccionados, listaDirectoresSeleccionados){
				$.ajax({
					url : '../GrupoAutorizacionController',
					data : {
						"codigo" : codigo,
						"unidadNegocioId" : unidadNegocioId,
						"lider" : lider,
						"activo" : activo,
						"tipoConsulta" : tipoConsulta,
						"listaGerentesSeleccionados":listaGerentesSeleccionados, 
						"listaDirectoresSeleccionados":listaDirectoresSeleccionados
					},
					type : 'POST',
					datatype : 'json',
					success : function(data) {
						$("#msgPopup").show();
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
							"Grupos Autorizaciones")) {
						response.sendRedirect("/CotizadorWeb/dashboard/AccesoDenegado.jsp");
					}
				}
%>

	<div class="row crud-nav-bar">
		<!-- Button trigger modal -->
		<button class="btn btn-primary" data-toggle="modal" data-target="#add" id="addButton">
			<span class="glyphicon glyphicon-plus"></span> &nbsp; Nuevo
		</button>

		<!-- Modal -->
		<div class="modal fade" id="add" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 900px;">
				<div class="modal-content">
					<form id="formCrud">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Nuevo Grupo de Autorizaciones</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">El grupo se  con exito.</div>
							<div class="form-group">
								<input type="hidden"class="form-control" id="codigo">
								<label>Unidad Negocio</label> 
								<select type="select" class="form-control required" id="unidad" placeholder="Seleccione una opcion">
									<option value="">Seleccione una opcion</option>
								</select>								
								<tr>
									<td colspan="3">
										<label>Lider del grupo</label> 
										<select type="select" class="form-control required" id="lider" placeholder="Seleccione una opcion">
											<option value="">Seleccione una opcion</option>
										</select>
									</td>
								</tr>
								<div class="checkbox">
									<label> <input type="checkbox" value="activo"id="activo">Activo</label>
								</div>
								
								<br>
								
								<table width="100%">
									<tr height-max=300px">
										<td>
											<div class="panel panel-default" id="panelGerentes">
											  <div class="panel-heading"><b>Listado de Gerentes</b></div>
											  <div class="panel-body">

											  </div>
											</div>
										</td>
										<td width="5px"></td>
										<td>
											<div class="panel panel-default" id="panelDirectores">
											  <div class="panel-heading"><b>Listado de Directores</b></div>
											  <div class="panel-body">

											  </div>
											</div>
										</td>
									</tr>
								</table>
								
								
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" id="close-popup"
								data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary" id="save-record">Save
								changes</button>
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
			<div class="table-responsive">
				<div class="input-group"> <span class="input-group-addon">Filter</span>
				    <input id="filter" type="text" class="form-control" placeholder="Escriba la palabra a buscar...">
				</div>			
				<table class="table table-striped table-bordered table-hover"
					id="dataTable">
					<thead>
						<tr>
							<th>Unidad Negocio</th>
							<th>Lider</th>
							<th>Activo</th>
							<th></th>
						</tr>
					</thead>
					<tbody id="dataTableContent" class="searchable">
					
						<div id="loading">
							<div class="loading-indicator">
								<img src="../static/images/ajax-loader.gif"/><br /><br />
								<span id="loading-msg">Cargando...</span>
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