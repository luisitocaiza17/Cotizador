<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Unidad de Producción - CotizadorQBE</title>
	
	<!-- Core CSS - Include with every page -->
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	<link href="../static/css/loading.css" rel="stylesheet">
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="../static/js/util.js"></script>
	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>
		var codigo = "";
		var unidad_negocio  = "";
		var nombre = "";
		var up_ensurance = "";
		var activo = "0";
		var tipoConsulta = "";
		var arrUnidadNegocio = new Array();
		var arrCodigoUnidadNegocio = new Object();
		
		$(document).ready(function() {
			
			activarMenu("UnidadProduccion");
			$.ajax({
				url : '../UnidadProduccionController',
				data : {
					"tipoConsulta" : "encontrarTodos"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					var unidadNegocio=data.listadoUN;
					$("#loading").fadeOut();
					if(data.numRegistros > 0){
						var listadoUnidadesProduccion = data.listadoUP;
						$.each(listadoUnidadesProduccion, function(index){
							var aux="	<tr class='odd gradeX'>" +
							" <td relation='unidad_negocio'>"+ listadoUnidadesProduccion[index].unidad_negocio +"</td>" +
							" <td relation='nombre'>"+ listadoUnidadesProduccion[index].nombre +"</td>" +
							" <td relation='up_ensurance'>"+ listadoUnidadesProduccion[index].up_ensurance +"</td>" ;
							aux+=" <td relation='activo'>"+listadoUnidadesProduccion[index].activo+"</td>" ;
							aux+=" <td width='175px'>" +
								" <input type='hidden'  value='"+ listadoUnidadesProduccion[index].codigo +"'/>" +
								" <button type='button' class='btn btn-success btn-xs actualizar-btn'>" +
  									" <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
								" </button>" +
								" <button type='button' class='btn btn-danger btn-xs eliminar-btn'>" +
								  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar" +
								" </button>" +
							"</td>" +
						"</tr>";
								
							$("#dataTableContent").append(aux);
					
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
								var r = confirm("Seguro que desea eliminar la Unidad de Producción " + $(this).parent().parent().children().first().next().text());
								if (r == true){
									codigo = $(this).parent().children().first().val();
									unidad_negocio  = ""; nombre = ""; up_ensurance = ""; activo = ""; tipoConsulta = "eliminar";
									enviarDatos(codigo,unidad_negocio, nombre, up_ensurance, activo, tipoConsulta);
							    	$(this).parent().parent().remove();
								}
							}
						});	
						/* Fin Controles Elminar Registro */
					}else{
						$("#dataTableContent").append("<tr><td colspan='4'>No existen Registros</td></tr>");
					}
				//Se agregan los PRoductos Grupo al select
				
					$.each(unidadNegocio, function(index){
						arrUnidadNegocio.push( unidadNegocio[index].nombre );
			    		//console.log(arrProductoGrupo);
		                arrCodigoUnidadNegocio[unidadNegocio[index].nombre] = unidadNegocio[index].codigo;
		                $("#unidad_negocio").append("<option value='"+unidadNegocio[index].nombre+"'>"+unidadNegocio[index].nombre+"</option>");
					    });
				
				}
			});			
					
			/* Inicio Controles Grabar Resgistro*/
				$("#save-record").click(function() {
					var retorno=true;
					$(".required").css("border", "1px solid #ccc");
					$(".required").each(function(index) {
						var cadena = $(this).val();
						if (cadena.trim().length <= 0 && retorno) {
							$(this).css("border", "1px solid red");
							alert("Por favor ingrese el campo requerido");
							$(this).focus();
							retorno= false;
							
						}
					});
		
					if ($("#activo").is(':checked')) activo = 1;
					codigo = $("#codigo").val();
					unidad_negocio  = arrCodigoUnidadNegocio[$("#unidad_negocio").val()];
					nombre = $("#nombre").val();
					up_ensurance = $("#up_ensurance").val();
					if (codigo == "")
						tipoConsulta = "crear";
					else
						tipoConsulta = "actualizar";
					if(retorno)
						enviarDatos(codigo,unidad_negocio, nombre, up_ensurance, activo, tipoConsulta);
				});
			/* Fin Controles Grabar Resgistro*/
			
			function enviarDatos(codigo,unidad_negocio, nombre, up_ensurance, activo, tipoConsulta){
				$.ajax({
					url : '../UnidadProduccionController',
					data : {
						"codigo" : codigo,
						"unidad_negocio" : unidad_negocio,
						"nombre" : nombre,
						"up_ensurance" : up_ensurance,
						"activo": activo,
						"tipoConsulta": tipoConsulta
						
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
							"Unidad de Producción")) {
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
							<h4 class="modal-title" id="myModalLabel">Nueva Unidad de Produccion</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">La Unidad se grabo con exito.</div>
							<div class="form-group">
								<input type="hidden"class="form-control" id="codigo">
								<label>Unidad de Negocio</label> 
								<select type="select" class="form-control required" id="unidad_negocio">
								  <option value=''>Seleccione una opcion</option>
								</select>
								<label>Nombre</label> <br>
								<input type="text" class="form-control required" id="nombre" ><br>
								<label>Codigo Ensurance</label> 
								<input type="text"class="form-control required" id="up_ensurance">
								<div class="checkbox">
									<label> <input type="checkbox" value="activo" id="activo">Activo</label>
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
				<div class="input-group"> <span class="input-group-addon">Filtro</span>
				    <input id="filter" type="text" class="form-control" placeholder="Escriba la palabra a buscar...">
				</div>			
				<table class="table table-striped table-bordered table-hover"
					id="dataTable">
					<thead>
						<tr>
							<th>Unidad Negocio</th>
							<th>Nombre</th>
							<th>Codigo Ensurance</th>
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