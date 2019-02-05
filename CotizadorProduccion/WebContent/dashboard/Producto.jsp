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
		var codigo = "";
		var producto_grupo  = "";
		var nombre = "";
		var nombre_comercial = "";
		var activo = "0";
		var plan_ensurance = "";
		var pro_ensurance = "";
		var nemotecnico = "";
		var tipoConsulta = "";
		var arrProductoGrupo = new Array();
		var arrCodigoProductoGrupo = new Object();
		
		$(document).ready(function() {
			activarMenu("Producto");
			$('#LimpiarBtn').click(function(){	 
				$("#estadoFiltro").val("");
				$('#dataTableContent').html('');
			});
			
			 $('#ConsultaBtn').click(function(){
				 var estadoFiltro = $("#estadoFiltro").val();
					var requerido = true;
					if(estadoFiltro == "")
					{
						alert("Seleccione el estado");
						requerido = false;
					}
						
					
					if(requerido)
					$.ajax({
						url : '../ProductoController',
						data : {
							"estadoFiltro" : estadoFiltro,
							"tipoConsulta" : "encontrarTodos"
						},
						type : 'POST',
						datatype : 'json',
						success : function(data) {
							var productoGrupos=data.listadoProductoGrupo;
							$("#loading").fadeOut();
							$('#dataTableContent').html('');
							if(data.numRegistros > 0){
								var listadoProducto = data.listadoProducto;
								$.each(listadoProducto, function(index){
									var aux="	<tr class='odd gradeX'>" +
//		 							" <td relation='producto_grupo'>"+ listadoProducto[index].producto_grupo +"</td>" +
									" <td relation='nombre'>"+ listadoProducto[index].nombre +"</td>" +
									" <td relation='ramo' >"+ listadoProducto[index].ramo +"</td>" +
									" <td relation='pro_ensurance' style='display: none'>"+ listadoProducto[index].pro_ensurance +"</td>" ;
									aux+=" <td relation='activo'>"+listadoProducto[index].activo+"</td>" ;
									aux+=" <td relation='plan_ensurance' style='display: none'>"+ listadoProducto[index].plan_ensurance +"</td>" +
									" <td relation='nemotecnico' style='display: none'>"+ listadoProducto[index].nemotecnico +"</td>" +
									" <td width='175px'>" +
										" <input type='hidden'  value='"+ listadoProducto[index].codigo +"'/>" +
//		 								" <button type='button' class='btn btn-success btn-xs actualizar-btn'>" +
//		   									" <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
//		 								" </button>" +
										" <button type='button' class='btn btn-info btn-xs cambioEstado-btn'>" +
		    						  	"<span class='glyphicon glyphicon glyphicon-refresh' id='cambioEstado-btn'>Activar/Desactivar</span>" +
		    							" </button>" +
//		 								" <button type='button' class='btn btn-danger btn-xs eliminar-btn'>" +
//		 								  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar" +
//		 								" </button>" +
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
								
								  $(".cambioEstado-btn").bind({click: function() {		
		    								codigo = $(this).parent().children().first().val();
		    								var activoaux = $(this).parent().prev().prev().prev().text();
		    								if (activoaux == "Si"){
		    	  						    		activo = 0;
		    	  						    		
		    	  						    	}else {
		    	  						    		activo = 1;
		    	  						    	}
		    	                                producto_grupo = "";
		    	                                nombre="";
		    	                                nombre_comercial="";
		    	                                pro_ensurance="";
		    	                                plan_ensurance = "";
		    	                                nemotecnico = "";
		    	                                tipoConsulta = "cambioEstado";
		    	                                enviarDatos(codigo,producto_grupo, nombre, nombre_comercial,pro_ensurance, activo,plan_ensurance,nemotecnico, tipoConsulta)
		     								alert("Estado Cambiado");
		     								location.reload();
		     						}
		    					}); 
								
								
								/* Inicio Controles Elminar Registro */
								$(".eliminar-btn").bind({click: function() {
										var r = confirm("Seguro que desea eliminar el Producto " + $(this).parent().parent().children().first().text());
										if (r == true){
											codigo = $(this).parent().children().first().val();
											producto_grupo  = ""; nombre = ""; nombre_comercial = ""; activo = ""; plan_ensurance = ""; nemotecnico = "";tipoConsulta = "eliminar";pro_ensurance="";
											enviarDatos(codigo,producto_grupo, nombre, nombre_comercial,pro_ensurance, activo,plan_ensurance,nemotecnico, tipoConsulta);
									    	$(this).parent().parent().remove();
										}
									}
								});	
								/* Fin Controles Elminar Registro */
							}else{
								$("#dataTableContent").append("<tr><td colspan='4'>No existen Registros</td></tr>");
							}
						//Se agregan los PRoductos Grupo al select
						
							$.each(productoGrupos, function(index){
								arrProductoGrupo.push( productoGrupos[index].nombre );
					    		//console.log(arrProductoGrupo);
				                arrCodigoProductoGrupo[productoGrupos[index].nombre] = productoGrupos[index].codigo;
				                $("#producto_grupo").append("<option value='"+productoGrupos[index].nombre+"'>"+productoGrupos[index].nombre+"</option>");
							    });
						
						}
					});	
				 
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
					producto_grupo  = arrCodigoProductoGrupo[$("#producto_grupo").val()];
					nombre = $("#nombre").val();
					nombre_comercial = $("#nombre_comercial").val();
					plan_ensurance = $("#plan_ensurance").val();
					pro_ensurance = $("#pro_ensurance").val();
					nemotecnico = $("#nemotecnico").val();
					if (codigo == "")
						tipoConsulta = "crear";
					else
						tipoConsulta = "actualizar";
					if(retorno)
					enviarDatos(codigo,producto_grupo, nombre, nombre_comercial,pro_ensurance, activo,plan_ensurance,nemotecnico, tipoConsulta);
				});
			/* Fin Controles Grabar Resgistro*/
			
			function enviarDatos(codigo,producto_grupo, nombre, nombre_comercial,pro_ensurance, activo,plan_ensurance,nemotecnico, tipoConsulta){
				$.ajax({
					url : '../ProductoController',
					data : {
						"codigo" : codigo,
						"producto_grupo" : producto_grupo,
						"nombre" : nombre,
						"nombre_comercial" : nombre_comercial,
						"pro_ensurance" : pro_ensurance,
						"activo": activo,
						"plan_ensurance" : plan_ensurance,
						"nemotecnico" : nemotecnico,
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
							"Producto")) {
						response.sendRedirect("/CotizadorWeb/dashboard/AccesoDenegado.jsp");
					}
				}
%>

	<div class="row crud-nav-bar">
	<div class="well">
			<table class="table">
				<thead>
					<tr>
						<td colspan="3" style="font-weight: bold;"><center>Buscador
								Producto</center></td>

					</tr>
					<tr>
						<th>Estado:</th>
						<th><select type="select"
							class="form-control " id="estadoFiltro">
								<option value="">Seleccione una opcion</option>
								<option value="1">Activo</option>
								<option value="0">Innactivo</option>
						</select></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th style="padding-left: 35%;">
							<button class="btn btn-primary" id="ConsultaBtn">
								<span class="glyphicon glyphicon-search"></span> &nbsp; Consulta
							</button>							
						</th>
						<th style="padding-left: 15%;">
							<button class="btn btn-primary" id="LimpiarBtn">
								<span class="glyphicon glyphicon-trash"></span> &nbsp; Limpiar
							</button>
						</th>
						
					</tr>
				</tbody>
			</table>
		</div>
		<!-- Button trigger modal -->
<!-- 		<button class="btn btn-primary" data-toggle="modal" data-target="#add" id="addButton"> -->
<!-- 			<span class="glyphicon glyphicon-plus"></span> &nbsp; Nuevo -->
<!-- 		</button> -->

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
							<h4 class="modal-title" id="myModalLabel">Nuevo Producto</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">El Producto se grabo con exito.</div>
							<div class="form-group">
								<input type="hidden"class="form-control" id="codigo">
								<label>Producto Grupo</label> 
								<select type="select" class="form-control required" id="producto_grupo">
								  <option value=''>Seleccione una opcion</option>
								</select>
								<label>Nombre</label> <br>
								<input type="text" class="form-control required" id="nombre" ><br>
								<label>Nombre Comercial</label> 
								<input type="text"class="form-control required" id="nombre_comercial">
								<div class="checkbox">
									<label> <input type="checkbox" value="activo" id="activo">Activo</label>
								</div>
								<label>Pro Ensurance</label> 
								<input type="text"class="form-control required" id="pro_ensurance">
								<label>Plan Ensurance</label> 
								<input type="text"class="form-control required" id="plan_ensurance">
								<label>Nombre Nemotecnico</label> 
								<input type="text"class="form-control required" id="nemotecnico">
								
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
<!-- 							<th>Producto Grupo</th> -->
							<th>Nombre</th>
							<th>Ramo</th>
<!-- 							<th>Pro Ensurance</th> -->
							<th>Activo</th>
<!-- 							<th>Plan Ensurance</th> -->
<!-- 							<th>Nemotecnico</th> -->
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