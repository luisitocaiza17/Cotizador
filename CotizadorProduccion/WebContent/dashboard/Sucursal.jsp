<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Sucursal - CotizadorQBE</title>
	
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
		var matriz = 0;
		var activo = 0;
		var tipoConsulta = "";
		
		var indicechoquetotal = ""; 
		var indicedanoparcialfrecuencia = "";
		var indicedanoparcialseveridad = "";
		var indiceresponsabilidadcivilfrecuencia = "";
		var indiceresponsabilidadcivilseveridad = "";
		var indicerobototal= "";
		var region= "";
		
		var arrRegion= new Array();
	    var arrCodigoRegion = new Object();
		
		
		$(document).ready(function() {
			activarMenu("Sucursal");
			$.ajax({
				url : '../SucursalController',
				data : {
					"tipoConsulta" : "encontrarTodos"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					$("#loading").remove();
					
                    var listadoRegion = data.listadoRegion;
                    $.each(listadoRegion, function (index) {
                    	arrRegion.push(listadoRegion[index].nombre);
                    	arrCodigoRegion[listadoRegion[index].nombre] = listadoRegion[index].codigo;
                        $("#region").append("<option value='" + listadoRegion[index].nombre + "'>" + listadoRegion[index].nombre + "</option>");
                    });
					
					
					
					if(data.numRegistros > 0){
						var listadoSucursal = data.listadoSucursal;
						$.each(listadoSucursal, function(index){
							$("#dataTableContent").append("	<tr class='odd gradeX'>" +
									" <td relation='codigoEnsurance'>"+ listadoSucursal[index].codigoEnsurance +"</td>" +
									" <td relation='nombre'>"+ listadoSucursal[index].nombre +"</td>" +
									" <td relation='indicechoquetotal'>"+ listadoSucursal[index].indicechoquetotal +"</td>" +
									" <td relation='indicedanoparcialfrecuencia'>"+ listadoSucursal[index].indicedanoparcialfrecuencia +"</td>" +
									" <td relation='indicedanoparcialseveridad'>"+ listadoSucursal[index].indicedanoparcialseveridad +"</td>" +
									" <td relation='indiceresponsabilidadcivilfrecuencia'>"+ listadoSucursal[index].indiceresponsabilidadcivilfrecuencia +"</td>" +
									" <td relation='indiceresponsabilidadcivilseveridad'>"+ listadoSucursal[index].indiceresponsabilidadcivilseveridad +"</td>" +
									" <td relation='indicerobototal'>"+ listadoSucursal[index].indicerobototal +"</td>" +
									" <td relation='region'>"+ listadoSucursal[index].region +"</td>" +
									" <td relation='matriz'>"+ listadoSucursal[index].matriz +"</td>" +
									" <td relation='activo'>"+ listadoSucursal[index].activo +"</td>" +
									" <td width='175px'>" +
										" <input type='hidden' value='"+ listadoSucursal[index].codigo +"'/>" +
										" <button type='button' class='btn btn-success btn-xs actualizar-btn'>" +
		  									" <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
										" </button>" +
										" <button type='button' class='btn btn-danger btn-xs eliminar-btn'>" +
										  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar" +
										" </button>" +
									"</td>" +
								"</tr>");
					
						});
						
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
							  }
						});
						/* Fin Controles Actualizar Registro*/
						
						/* Inicio Controles Elminar Registro */
						$(".eliminar-btn").bind({click: function() {
								var r = confirm("Seguro que desea eliminar la sucursal " + $(this).parent().parent().children().first().text());
								if (r == true){
									codigo = $(this).parent().children().first().val();
									nombre = ""; matriz = ""; activo = ""; codigoEnsurance="";
									indicechoquetotal = ""; indicedanoparcialfrecuencia = "";
									 indicedanoparcialseveridad = "";
									 indiceresponsabilidadcivilfrecuencia = "";
									 indiceresponsabilidadcivilseveridad = "";
									 indicerobototal= "";
									 region= "";
									tipoConsulta = "eliminar";
									enviarDatos(codigoEnsurance, codigo, nombre, indicechoquetotal, indicedanoparcialfrecuencia,indicedanoparcialseveridad,
											indiceresponsabilidadcivilfrecuencia,indiceresponsabilidadcivilseveridad,indicerobototal,region,matriz, activo, tipoConsulta);
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
					$(".required").css("border", "1px solid #ccc");
					$(".required").each(function(index) {
						var cadena = $(this).val();
						if (cadena.length <= 0) {
							$(this).css("border", "1px solid red");
							alert("Por favor ingrese el campo requerido");
							$(this).focus();
							return false;
						}
					});
		
					if ($("#matriz").is(':checked')) matriz = 1;
					if ($("#activo").is(':checked')) activo = 1;
					codigo = $("#codigo").val();
					nombre = $("#nombre").val();
					indicechoquetotal= $("#indicechoquetotal").val();
					indicedanoparcialfrecuencia= $("#indicedanoparcialfrecuencia").val();
					indicedanoparcialseveridad = $("#indicedanoparcialseveridad").val();
					indiceresponsabilidadcivilfrecuencia= $("#indiceresponsabilidadcivilfrecuencia").val();
					indiceresponsabilidadcivilseveridad= $("#indiceresponsabilidadcivilseveridad").val();
					indicerobototal= $("#indicerobototal").val();
					region= $("#region").val();
					
					if (codigo == "")
						tipoConsulta = "crear";
					else
						tipoConsulta = "actualizar";
					
					if(nombre=="" && codigo=="" && indicechoquetotal=="" && indicedanoparcialfrecuencia=="" && indicedanoparcialseveridad=="" && indiceresponsabilidadcivilfrecuencia=="" && indiceresponsabilidadcivilseveridad=="" && indicerobototal=="" )
					{
					alert("Ingrese al menos un valor ");
					}
				else
					{
					codigoEnsurance = $("#codigoEnsurance").val();
					enviarDatos(codigoEnsurance, codigo, nombre, indicechoquetotal, indicedanoparcialfrecuencia,indicedanoparcialseveridad,
					indiceresponsabilidadcivilfrecuencia,indiceresponsabilidadcivilseveridad,indicerobototal,region,matriz, activo, tipoConsulta);
				}
				});
			/* Fin Controles Grabar Resgistro*/
			
			function enviarDatos(codigoEnsurance, codigo, nombre, indicechoquetotal, indicedanoparcialfrecuencia,indicedanoparcialseveridad,
					indiceresponsabilidadcivilfrecuencia,indiceresponsabilidadcivilseveridad,indicerobototal,region,matriz, activo, tipoConsulta){
				$.ajax({
					url : '../SucursalController',
					data : {
						"codigoEnsurance" : codigoEnsurance,
						"codigo" : codigo,
						"nombre" : nombre,
						"indicechoquetotal":indicechoquetotal, 
						"indicedanoparcialfrecuencia":indicedanoparcialfrecuencia,
						"indicedanoparcialseveridad":indicedanoparcialseveridad,
						"indiceresponsabilidadcivilfrecuencia":indiceresponsabilidadcivilfrecuencia,
						"indiceresponsabilidadcivilseveridad":indiceresponsabilidadcivilseveridad,
						"indicerobototal":indicerobototal,
						"region":region,
						"matriz" : matriz,
						"activo" : activo,
						"tipoConsulta" : tipoConsulta
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
							"Sucursales")) {
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
			<div class="modal-dialog">
				<div class="modal-content">
					<form id="formCrud">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Nueva Sucursal</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">La Sucursal se grabo con exito.</div>
							<div class="form-group">
								<input type="hidden"class="form-control" id="codigo">
								<label>Codigo Ensurance</label> 
								<input type="text"class="form-control required" id="codigoEnsurance">
								<label>Nombre de la Sucursal</label> 
								<input type="text"class="form-control required" id="nombre">
								<br>
								<div class="well">
								<label>Indice Choque Total</label> 
								<input type="text"class="form-control required" id="indicechoquetotal">
								<label>Indice Daño Parcial Frecuencia</label> 
								<input type="text"class="form-control required" id="indicedanoparcialfrecuencia">
								<label>Indice Daño Parcial Severidad</label> 
								<input type="text"class="form-control required" id="indicedanoparcialseveridad">
								<label>Indice Responsabilidad Civil Frecuencia</label> 
								<input type="text"class="form-control required" id="indiceresponsabilidadcivilfrecuencia">
								<label>Indice Responsabilidad Civil Severidad</label> 
								<input type="text"class="form-control required" id="indiceresponsabilidadcivilseveridad">
								<label>Indice Robo Total</label> 
								<input type="text"class="form-control required" id="indicerobototal">
								<label>Region</label> 
								<select type="select" class="form-control required" id="region"></select>								
								</div>
								<div class="checkbox">
									<label> <input type="checkbox" value="matriz" id="matriz">Es matriz</label>
								</div>
								<div class="checkbox">
									<label> <input type="checkbox" value="activo"id="activo">Activo</label>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" id="close-popup"
								data-dismiss="modal">Cerrar</button>
							<button type="button" class="btn btn-primary" id="save-record">Guardar Cambios</button>
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
							<th>Codigo Ensunrace</th>
							<th>Nombre</th>
							<th>Indice Choque Total</th>
							<th>Indice Daño Parcial Frecuencia</th>
							<th>Indice Daño Parcial Severidad</th>
							<th>Indice Responsabilidad Civil Frecuencia</th>
							<th>Indice Responsabilidad Civil Severidad</th>
							<th>Indice Robo Total</th>
							<th>Region</th>
							<th>Matriz</th>
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