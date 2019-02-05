<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Variables de Sistema - CotizadorQBE</title>
	
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
		var activo = 0;
		var tipoConsulta = "";
		var arrCodigoModulo = new Array();
		var arrModulo = new Array();
		var arrTipoVariable = new Array();
		var arrCodigoTipoVariable = new Array();
		
		
		$(document).ready(function() {
			activarMenu("VariableSistema");
			$.ajax({
				url : '../VariableSistemaController',
				data : {
					"tipoConsulta" : "encontrarTodos"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					$("#loading").remove();
					var listadoModulo = data.listadoModulo;
			    	var listadoTipoVariable = data.listadoTipoVariable;

					if(data.numRegistros > 0){
						var listadoVariableSistema = data.listadoVariableSistema;
				    	
				    	$.each(listadoVariableSistema, function(index){
							$("#dataTableContent").append("	<tr class='odd gradeX'>" +
									" <td relation='nombre'>"+ listadoVariableSistema[index].nombre +"</td>" +
									" <td relation='valor'>"+ listadoVariableSistema[index].valor +"</td>" +
									" <td relation='tipoVariable'>"+ listadoVariableSistema[index].tipoVariable +"</td>" +
									" <td relation='modulo'>"+ listadoVariableSistema[index].modulo +"</td>" +
									" <td relation='activo'>"+ listadoVariableSistema[index].activo +"</td>" +
									" <td width='175px'>" +
										" <input type='hidden' value='"+ listadoVariableSistema[index].codigo +"'/>" +
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
						
						/* Inicio Controles Eliminar Registro */
						$(".eliminar-btn").bind({click: function() {
								var r = confirm("Seguro que desea eliminar la Variable de Sistema? " + $(this).parent().parent().children().first().text());
								if (r == true){
 									codigo = $(this).parent().children().first().val();
									nombre = ""; valor =""; activo =""; tipoVariable =""; modulo = "";
									tipoConsulta = "eliminar"; 
									enviarDatos(codigo, nombre, valor, tipoVariable, activo, modulo,tipoConsulta);
							    	$(this).parent().parent().remove();
								}
							}
						});	
						/* Fin Controles Eliminar Registro */
					}else{
						$("#dataTableContent").append("<tr><td colspan='6'>No existen Registros</td></tr>");
					}
					
					$.each(listadoModulo, function(index){
						arrModulo.push(listadoModulo[index].nombre);
			    		arrCodigoModulo[listadoModulo[index].nombre] = listadoModulo[index].codigo;
		                $("#modulo").append("<option value='"+listadoModulo[index].nombre+"'>"+listadoModulo[index].nombre+"</option>");
			    	});
			    	
			    	
			    	$.each(listadoTipoVariable, function(index){
			    		arrTipoVariable.push(listadoTipoVariable[index].nombre);
		                arrCodigoTipoVariable[listadoTipoVariable[index].nombre] = listadoTipoVariable[index].codigo;
		                $("#tipoVariable").append("<option value='"+listadoTipoVariable[index].nombre+"'>"+listadoTipoVariable[index].nombre+"</option>");
			    	});	
					
					$(".hideColumn").hide();
				}//finfunctiondata
			});//finajax			
			

			/* Inicio Controles Grabar Registro*/
				$("#save-record").click(function() {
					var retorno=true;
					$(".required").css("border", "1px solid #ccc");
					$(".required").each(function(index) {
						var cadena = $(this).val();
						if (cadena.length <= 0) {
							$(this).css("border", "1px solid red");
							alert("Por favor ingrese el campo requerido");
							$(this).focus();
							retorno= false;
						}
					});
		
					if ($("#activo").is(':checked')) activo = 1;
					codigo = $("#codigo").val();
					nombre = $("#nombre").val();
					valor = $("#valor").val();
					modulo = arrCodigoModulo[$("#modulo").val()]; //$("#modulo").val();
					tipoVariable = arrCodigoTipoVariable[$("#tipoVariable").val()]; 
					if ($("#activo").is(':checked')) activo = 1;
					
					if (codigo == "")
						tipoConsulta = "crear";
					else
						tipoConsulta = "actualizar";
					
					enviarDatos(codigo, nombre, valor, tipoVariable, activo, modulo,tipoConsulta);
				});
			/* Fin Controles Grabar Registro*/
			
			function enviarDatos(codigo, nombre, valor, tipoVariable, activo, modulo,tipoConsulta){
				$.ajax({
					url : '../VariableSistemaController',
					data : {
						"codigo" : codigo,
						"tipoVariable": tipoVariable,
						"nombre" : nombre,
						"valor": valor,
						"modulo": modulo,
						"activo" : activo,
						"tipoConsulta" : tipoConsulta						
					},
					type : 'POST',
					datatype : 'json',
					success : function(data) {
						if(data.success)
							$("#msgPopup").show();
						else
							alert(data.error);
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
							"Variables de Sistema")) {
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
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Variables del Sistema</h4>
						</div>
						<div class="modal-body">
						<div class="alert alert-success" id="msgPopup">Grabado con exito.</div>
							<div class="form-group">
								<input type="hidden"class="form-control" id="codigo">
								<label>Tipo de Variable</label> 
								<select type="select" class="form-control required" id="tipoVariable">
								  <option>Seleccione una opcion</option>
								</select>
								<label>Nombre de la Variable de Sistema</label> 
								<input type="text"class="form-control required" id="nombre">
								<label>Valor</label> 
								<input type="text"class="form-control required" id="valor">
								<label>Modulo</label> 
								<select type="select" class="form-control required" id="modulo">
								  <option>Seleccione una opcion</option>
								</select>
								<div class="checkbox">
									<label> <input type="checkbox" value="activo"id="activo">Activo</label>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" id="close-popup"
								data-dismiss="modal">Cerrar</button>
							<button type="button" class="btn btn-primary" id="save-record">Guardar</button>
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
							<th>Nombre</th>
							<th>Valor</th>
							<th>Tipo Variable</th>
							<th>Tipo Modulo</th>
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