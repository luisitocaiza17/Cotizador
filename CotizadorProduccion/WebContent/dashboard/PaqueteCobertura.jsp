<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	<link href="../static/css/loading.css" rel="stylesheet">
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="../static/js/util.js"></script>
<title>Paquete Cobertura - Cotizador QBE</title>
	<script>
		var codigo = "";
		var grupoPorProducto = "";
		var cobertura = "";
		var paquete = "";
		var tipoConsulta = "";
		
		$(document).ready(function() {
			activarMenu("PaqueteCobertura");
			
			$.ajax({
				url : '../PaqueteCoberturaController',
				data : {
					"tipoConsulta" : "encontrarTodos"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					$("#loading").remove();
					if(data.numRegistros > 0){
						var listadoPaqueteCobertura = data.listadoPaqueteCobertura;
						$.each(listadoPaqueteCobertura, function(index){
							$("#dataTableContent").append("	<tr class='odd gradeX'>" +
									" <td relation='grupoPorProducto'>"+ listadoPaqueteCobertura[index].grupoPorProducto +"</td>" +									
									" <td relation='cobertura'>"+ listadoPaqueteCobertura[index].cobertura +"</td>" +
									" <td relation='paquete'>"+ listadoPaqueteCobertura[index].paquete +"</td>" +
									" <td width='175px'>" +
										" <input type='hidden' value='"+ listadoPaqueteCobertura[index].codigo +"'/>" +
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
								var r = confirm("Seguro que desea eliminar" + $(this).parent().parent().children().first().text());
								if (r == true){
									codigo = $(this).parent().children().first().val();
									grupoPorProducto = ""; cobertura = ""; paquete = "";
									tipoConsulta = "eliminar";
									enviarDatos(codigo, grupoPorProducto, cobertura, paquete, tipoConsulta);
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
		
					
					cobertura = $("#cobertura").val();
					codigo = $("#codigo").val();
					grupoPorProducto = $("#grupoPorProducto").val();
					paquete = $("#paquete").val(); 
					if (codigo == ""){
						tipoConsulta = "crear";}
					else{
						tipoConsulta = "actualizar";
					}
					enviarDatos(codigo, grupoPorProducto, cobertura, paquete, tipoConsulta);
				});
			/* Fin Controles Grabar Resgistro*/
			
			function enviarDatos(codigo, grupoPorProducto, cobertura, paquete, tipoConsulta){
				$.ajax({
					url : '../PaqueteCoberturaController',
					data : {						
						"codigo" : codigo,
						"grupoPorProducto" : grupoPorProducto,
						"cobertura" : cobertura,
						"paquete" : paquete,
						"tipoConsulta" : tipoConsulta
					},
					type : 'POST',
					datatype : 'json',
					success : function(data) {
						$("#msgPopup").show();
					}
				});
			}
			
			
			/* CONSULTA LAS COBERTURAS DISPONIBLES*/
			$.ajax({
				url : '../CoberturaController',
				data : {
					"tipoConsulta" : "encontrarTodos"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					var listadoCobertura = data.listadoCobertura;
					$.each(listadoCobertura, function(index){
						$("#cobertura").append("<option value='"+ listadoCobertura[index].nombre +"'>" + listadoCobertura[index].nombre + "</option>");	
					});
					
					$("#msgPopup").show();
				}
			});
			
			/* CONSULTA LOS PAQUETES DISPONIBLES*/
			$.ajax({
				url : '../PaqueteCoberturaController',
				data : {
					"tipoConsulta" : "encontrarPaquetes"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					var listadoPaquete = data.listadoPaquete;
					$.each(listadoPaquete, function(index){
						$("#paquete").append("<option value='"+ listadoPaquete[index].nombre +"'>" + listadoPaquete[index].nombre + "</option>");	
					});
					
					$("#msgPopup").show();
				}
			});
			
			
			
			/* CONSULTA LOS GRUPO POR PRODUCTO*/
			$.ajax({
				url : '../GrupoPorProductoController',
				data : {
					"tipoConsulta" : "encontrarTodos"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					var listadoGrupoPorProducto = data.listadoGrupoPorProducto;
					$.each(listadoGrupoPorProducto, function(index){
						$("#grupoPorProducto").append("<option value='"+ listadoGrupoPorProducto[index].nombreComercialProducto +"'>" + listadoGrupoPorProducto[index].nombreComercialProducto + "</option>");	
					});
					
					$("#msgPopup").show();
				}
			});				
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
							"PaqueteCobertura")) {
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
							<h4 class="modal-title" id="myModalLabel">Nuevo Paquete Cobertura</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">El paquete cobertura se grabo con exito.</div>
							<div class="form-group">
								<input type="hidden"class="form-control" id="codigo">
								<label>Grupo por Producto</label> 
								<select type="select" class="form-control required" id="grupoPorProducto">
                                	<option>Seleccione una opcion</option>
                            	</select>
								<label>Cobertura</label> 
								<select type="select" class="form-control required" id="cobertura">
                                	<option>Seleccione una opcion</option>
                            	</select>                            	
                            	<label>Paquete</label> 
								<select type="select" class="form-control required" id="paquete">
                                	<option>Seleccione una opcion</option>
                            	</select>
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
			<div class="table-responsive">
				<div class="input-group"> <span class="input-group-addon">Filter</span>
				    <input id="filter" type="text" class="form-control" placeholder="Escriba la palabra a buscar...">
				</div>			
				<table class="table table-striped table-bordered table-hover"
					id="dataTable">
					<thead>
						<tr>
							<th>Grupo por Producto</th>						
							<th>Cobertura</th>
							<th>Paquete</th>
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