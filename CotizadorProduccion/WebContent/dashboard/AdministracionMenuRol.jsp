<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Producto - CotizadorQBE</title>
	
	<!-- Core CSS - Include with every page -->
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	<link href="../static/css/loading.css" rel="stylesheet">
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="../static/js/util.js"></script>
	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>
		var rol = "";
		var tipoConsulta = "";
		var arrRol = new Array();
		var arrCodigoRol = new Object();
		var listaOpcion = new Array();
		var listaTodosHijos = new Array();
		
		$(document).ready(function() {
			activarMenu("AdministracionMenuRol");
			$.ajax({
				url : '../AdministracionMenuRolController',
				data : {
					"tipoConsulta" : "encontrarRol"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					var listadoRol=data.listadoRol;
					$("#loading").fadeOut();
					$('#content').html('');
					$("#save-record").hide();	
						
						$.each(listadoRol, function(index){
							arrRol.push( listadoRol[index].nombre );
			                arrCodigoRol[listadoRol[index].nombre] = listadoRol[index].codigo;
			                $("#rolListado").append("<option value='"+listadoRol[index].codigo+"'>"+listadoRol[index].nombre+"</option>");
						    })
						
				}
			});	
			
			$(function() {
				$('#rolListado').change(function() {
					$('#content').html('');
					$("#msgPopup").hide();	
					$("#save-record").hide();	
					rol = $("#rolListado").val();
					if(rol != "")
					$.ajax({
						url : '../AdministracionMenuRolController',
						data : {
							"rol" : rol,
							"tipoConsulta" : "encontrarOpcionMenu"
						},
						type : 'POST',
						datatype : 'json',
						success : function(data) {
							listaOpcion=data.listadoOpcion;
							listaTodosHijos = data.listadoTodosHijos;
							$("#save-record").show();	
							$("#loading").fadeOut();
							$('#content').html('');
							

								
		 						$.each(listaOpcion, function(index){
		 							$("#content").append("<tr class='odd gradeX' align = 'center'><td>"+listaOpcion[index].nombre+"</td>"
		 							+"<td>"
		 							+"<input type='checkbox' id='"+listaOpcion[index].nombre+"' name='"+listaOpcion[index].nombre+"' value='"+listaOpcion[index].codigo+"' onclick ='validarHijos(this)'/>"
		 							+"</td>"
		 							+"<td>"
		 							+"<table class='table'>"
		 							+"<tbody id='"+listaOpcion[index].nombre+"_hijos'>"
		 							+"</tbody>"
		 							+"</table>"
		 							+"</td>"
		 							+"</tr>");
		 							
		 							if(listaOpcion[index].check)
		 							{
		 								$("#"+listaOpcion[index].nombre).prop('checked', true);	
		 								var padre = listaOpcion[index].codigo;
		 								var nombre = listaOpcion[index].nombre;
		 								$.ajax({
		 									url : '../AdministracionMenuRolController',
		 									data : {
		 										"padre" : padre,
		 										"rol" : rol,
		 										"tipoConsulta" : "encontrarHijos"
		 									},
		 									type : 'POST',
		 									datatype : 'json',
		 									success : function(data) {
		 										listadoHijos=data.listadoHijos;
		 										$.each(listadoHijos, function(index){
		 											$("#"+nombre+"_hijos").append("<tr class='odd gradeX' align = 'center'><td>"+listadoHijos[index].nombre+"</td>"
		 													+"<td>"
		 													+"<input type='checkbox' id='"+listadoHijos[index].codigo+"' name='"+listadoHijos[index].nombre+"' value='"+listadoHijos[index].codigo+"'/>"
		 													+"</td>"
		 													+"</tr>");
		 											
		 											if(listadoHijos[index].check)
		 				 							{
		 												$("#"+listadoHijos[index].codigo).prop('checked', true);	
		 				 							}
		 									
		 											});
		 											
		 										
		 									
		 									}
		 								});
		 								
		 						
		 								
		 							}
							
		 						});
								
							
						
						}
					});	
				});
				
				/* Inicio Controles Grabar Registro*/
				$("#save-record").click(function() {
					$("#loading").fadeIn("slow"); 
					var listaCheck = new Array();
					var listaNoCheck = new Array();
					var rol = $("#rolListado").val();
					var i = 0;
					var j = 0;
					$.each(listaOpcion, function(index){
						var nombre = listaOpcion[index].nombre;
						if ($("#"+nombre).is(':checked')){
							
							listaCheck[i] = listaOpcion[index].codigo;
							i = i+1;
						}else{
							listaNoCheck[j] = listaOpcion[index].codigo;
							j = j+1;
						}
				
						});
					$.each(listaTodosHijos, function(index){
						var codigo = listaTodosHijos[index].codigo;
						if ($("#"+codigo).is(':checked')){
							
							
							listaCheck[i] = listaTodosHijos[index].codigo;
							i = i+1;
						}else{
							listaNoCheck[j] = listaTodosHijos[index].codigo;
							j = j+1;
						}
				
						});
					$.ajax({
						url : '../AdministracionMenuRolController',
						data : {
							listaCheck : listaCheck,
							listaNoCheck : listaNoCheck,
							"rol" : rol,
							"tipoConsulta" : "guardar"
						},
						type : 'POST',
						datatype : 'json',
						success : function(data) {
							$("#loading").fadeOut("slow");  
							$("#msgPopup").show();		
						}
					});	
				});
				
			});
			
			
	
					
					
			
	
		});
		
		function validarHijos(field) {
			var nombre = field.id;
			var padre = field.value;
			$("#"+nombre+"_hijos").html('');
			$("#msgPopup").hide();
			if ($("#"+nombre).is(':checked')){
				
				$.ajax({
					url : '../AdministracionMenuRolController',
					data : {
						"padre" : padre,
						"tipoConsulta" : "encontrarHijos"
					},
					type : 'POST',
					datatype : 'json',
					success : function(data) {
						listadoHijos=data.listadoHijos;
						$.each(listadoHijos, function(index){
							$("#"+nombre+"_hijos").append("<tr class='odd gradeX' align = 'center'><td>"+listadoHijos[index].nombre+"</td>"
									+"<td>"
									+"<input type='checkbox' id='"+listadoHijos[index].codigo+"' name='"+listadoHijos[index].nombre+"' value='"+listadoHijos[index].codigo+"'/>"
									+"</td>"
									+"</tr>");
					
							});

					}
				});	

			}
			
			
		}
		
	</script>
</head>
<body>
	<%
		// Permitimos el acceso si la session existe		
		if (session.getAttribute("login") == null) {
			response.sendRedirect("/CotizadorWeb");
		} else {
			com.qbe.cotizador.model.Usuario usuario = (com.qbe.cotizador.model.Usuario) session
					.getAttribute("usuario");

			// Obtenemos el rol asociado al usuario
			com.qbe.cotizador.dao.seguridad.UsuarioRolDAO usuarioRolDAO = new com.qbe.cotizador.dao.seguridad.UsuarioRolDAO();
			com.qbe.cotizador.model.UsuarioRol usuarioRol = usuarioRolDAO
					.buscarPorUsuario(usuario);
			com.qbe.cotizador.model.Rol rol = usuarioRol.getRol();
			com.qbe.cotizador.dao.seguridad.OpcionMenuRolDAO opcionMenuRolDAO = new com.qbe.cotizador.dao.seguridad.OpcionMenuRolDAO();
			if (!opcionMenuRolDAO.verificarPermiso(rol.getId(),
					"Administracion de Permisos")) {
				response.sendRedirect("/CotizadorWeb/dashboard/AccesoDenegado.jsp");
			}
		}
	%>

	<div class="row crud-nav-bar">
	<div class="well">
			<div class="alert alert-success" id="msgPopup">Grabado con
				exito.</div>
			<table class="table">
				<thead>
					<tr>
						<td colspan="3" style="font-weight: bold;"><center>Administracion
								Menu - Rol</center></td>

					</tr>
					<tr>
						
						<td>ROL:</td>
						<td><select type="select" class="form-control"
							id="rolListado">
								<option value=''>Seleccione una opcion</option>
						</select></td>
						
					</tr>
				</thead>
			</table>
			<table class="table">
<!-- 				<thead> -->
<!-- 					<tr> -->
<%-- 						<td colspan="3" style="font-weight: bold;"><center>Administracion --%>
<%-- 								Menu - Rol</center></td> --%>

<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td style="width : 25%"></td> -->
<!-- 						<td style="width : 10%">ROL:</td> -->
<!-- 						<td style="width : 25%"><select type="select" class="form-control" -->
<!-- 							id="rolListado"> -->
<!-- 								<option value=''>Seleccione una opcion</option> -->
<!-- 						</select></td> -->
						
<!-- 					</tr> -->
<!-- 				</thead> -->
				<tbody id="content">
					<div id="loading">
						<div class="loading-indicator">
							<img src="../static/images/ajax-loader.gif" /><br /> <br /> <span
								id="loading-msg">Cargando...</span>
						</div>
					</div>
				</tbody>
				<tfoot>
					<tr>
						<td><button type="button" class="btn btn-primary" id="save-record">Guardar</button></td>
					</tr>
				</tfoot>
			</table>
		</div>
		
	</div>
	
	
</body>
</html>