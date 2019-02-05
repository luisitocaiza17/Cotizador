<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Descuento - CotizadorQBE</title>
	
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
		var descripcion = "";
		var rangoInicial = "";
		var rangoFinal = "";
		var activo = "0";
		var aplicaTasaMinima = "0";
		var tipoConsulta = "";
		var arrCargo = new Array();
		var arrCodigoCargo = new Object(); 
		var arrGrupoAutorizacion = new Array();
		var arrCodigoGrupoAutorizacion = new Object(); 
		
		$(document).ready(function() {
			activarMenu("Descuento");
			
			/*$.ajax({
				url : '../GrupoAutorizacionController',
				data : {
					"tipoConsulta" : "encontrarTodos"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					var listadoGrupoAutorizacion = data.listadoGrupoAutorizacion;
					var opciones = "<option value=''>Seleccion una opcion</option>";
					$.each(listadoGrupoAutorizacion, function(index){
						opciones += "<option value='"+ listadoGrupoAutorizacion[index].unidadNegocioNombre +"' codigoGrupoAutorizacion='" + listadoGrupoAutorizacion[index].codigo + "' codigoUnidadNegocio='"+ listadoGrupoAutorizacion[index].unidadNegocioId +"'>" + listadoGrupoAutorizacion[index].unidadNegocioNombre +"</option>";
					});
					$("#grupo").html(opciones);
					$("#loading").hide();
				}					
			});		*/			

			
			function empleadoGrupoAutorizacion(codigoLider){
				$.ajax({
					url : '../GrupoAutorizacionController',
					data : {
						"codigoLider": codigoLider,
						"tipoConsulta" : "encontrarPorLider"
					},
					type : 'POST',
					datatype : 'json',
					success : function(data) {
						var listaEmpleadosAutorizacion = data.listadoGrupoUsuarioAutorizacion;
						var tablaEmpleados1 = "<table class='tablaPersonasAutorizan'>";
						var tablaEmpleados2 = "<table class='tablaPersonasAutorizan'>";
						$("#panelGerentes").children().last().empty();
						$("#panelDirectores").children().last().empty();
						$.each(listaEmpleadosAutorizacion, function(index){
							if(listaEmpleadosAutorizacion[index].nombreGenerico == "gerente"){
								tablaEmpleados1 = tablaEmpleados1 + "<tr>"+
																	"<td><input type='checkbox' name='gerente' value='"+listaEmpleadosAutorizacion[index].codigoEmpleado+"' class='radioGerente'></td>"+
																	"<td><td>"+
																	"<td>"+listaEmpleadosAutorizacion[index].nombre+"</td>"+
																	"<td></td>"+
																	"<td><input type='mail' id='mailGerente"+index+"' placeholder='Ingrese el mail' size='35' value='"+listaEmpleadosAutorizacion[index].mail+"'></td>"+
																  "</tr>";
							}
							
							if(listaEmpleadosAutorizacion[index].nombreGenerico == "director"){
								tablaEmpleados2 = tablaEmpleados2 + "<tr>"+
																	"<td><input type='checkbox' name='director' value='"+listaEmpleadosAutorizacion[index].codigoEmpleado+"' class='radioDirector'></td>"+
																	"<td><td>"+
																	"<td>"+listaEmpleadosAutorizacion[index].nombre+"</td>"+
																	"<td></td>"+
																	"<td><input type='mail' id='mailDirector"+index+"' placeholder='Ingrese el mail' size='35' value='"+listaEmpleadosAutorizacion[index].mail+"'></td>"+
																  "</tr>";
							}							
						});
						tablaEmpleados1 = tablaEmpleados1 + "</table>";
						$("#panelGerentes").children().last().append(tablaEmpleados1);
						tablaEmpleados2 = tablaEmpleados2 + "</table>";
						$("#panelDirectores").children().last().append(tablaEmpleados2);
					}					
				});				
			}
			
			function empleadoPorCargo(cargoGenerico, codigoLider){
				$.ajax({
					url : '../EmpleadoController',
					data : {
						"cargoGenerico": cargoGenerico,
						"codigoLider": codigoLider,
						"tipoConsulta" : "empleadoPorCargo"
					},
					type : 'POST',
					datatype : 'json',
					success : function(data) {
						var listaEmpleadosCargo = data.listadoEmpleadosCargo;
						if(cargoGenerico == "VICEPRESIDENTE"){
							$("#panelVicepresidentes").children().last().empty();
							var tablaEmpleados = "<table class='tablaPersonasAutorizan'>";
							$.each(listaEmpleadosCargo, function(index){
								tablaEmpleados = tablaEmpleados + "<tr>"+
																	"<td><input type='radio' name='vicepresidente' value='"+listaEmpleadosCargo[index].codigoEmpleado+"' class='radioVicepresidente'></td>"+
																	"<td><td>"+
																	"<td>"+listaEmpleadosCargo[index].nombre+"</td>"+
																	"<td></td>"+
																	"<td><input type='mail' id='mailVicepresidente"+index+"' placeholder='Ingrese el mail' size='35' value='"+listaEmpleadosCargo[index].mail+"'></td>"+
																  "</tr>";
							});
							tablaEmpleados = tablaEmpleados + "</table>";
							$("#panelVicepresidentes").children().last().append(tablaEmpleados);
							
							$(".radioVicepresidente").click(function(){
								if($("#panelGerentes").is(':visible')){
									empleadoGrupoAutorizacion($(this).val());
								}
							});
						}		
					}					
				});
			}			
			
			empleadoPorCargo("VICEPRESIDENTE");
			$("#cargo").change(function(){
				if($(this).find(":selected").attr("class") == "VICEPRESIDENTE"){
					$("#panelGerentes").children().last().empty().append("Para mostrar los gerentes disponibles seleccione al vicepresidente del que dependen.");
					$("#panelDirectores").hide();
					$("#panelGerentes").hide();
					
					
				}				
				
				if($(this).find(":selected").attr("class") == "GERENTE"){
					$("#panelDirectores").children().last().empty().append("Para mostrar los gerentes disponibles seleccione al vicepresidente del que dependen.");
					$("#panelDirectores").hide();
					$("#panelGerentes").show();
					//empleadoPorCargo($(this).find(":selected").attr("class"));
				}

				if($(this).find(":selected").attr("class") == "DIRECTOR"){
					$("#panelGerentes").show();
					$("#panelDirectores").show();
				}
			});
			
			$.ajax({
				url : '../DescuentoController',
				data : {
					"tipoConsulta" : "encontrarTodos"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					$("#loading").remove();
					if(data.numRegistros > 0){
						var listadoDescuento = data.listadoDescuento;
						var listadoCargo = data.listadoCargo;
						var listadoGrupoAutorizacion = data.listadoGrupoAutorizacion;
						$.each(listadoDescuento, function(index){
							$("#dataTableContent").append("	<tr class='odd gradeX'>" +
									" <td relation=''>"+ listadoDescuento[index].grupoAutoriza +"</td>" +
									" <td relation='grupo' hidden='hidden'>"+ listadoDescuento[index].grupoAutoriza +"</td>" +
									" <td relation='nombre'>"+ listadoDescuento[index].nombre +"</td>" +
									//" <td relation='descripcion'>"+ listadoDescuento[index].descripcion +"</td>" +
									" <td relation='rangoInicial'>"+ listadoDescuento[index].rangoInicial +"</td>" +
									" <td relation='rangoFinal'>"+ listadoDescuento[index].rangoFinal +"</td>" +
									" <td relation='cargo' >"+ listadoDescuento[index].cargoAutoriza +"</td>" +
									" <td relation='activo'>"+ listadoDescuento[index].activo +"</td>" +
									" <td relation='aplicaTasaMinima'>"+ listadoDescuento[index].aplicaTasaMinima +"</td>" +
									" <td width='175px'>" +
										" <input type='hidden' value='"+ listadoDescuento[index].codigo +"'/>" +
										" <button type='button' class='btn btn-success btn-xs actualizar-btn'>" +
		  									" <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
										" </button>" +
										" <button type='button' class='btn btn-danger btn-xs eliminar-btn'>" +
										  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar" +
										" </button>" +
									"</td>" +
								"</tr>");
					
						});
						
						$.each(listadoCargo, function(index){
				    		arrCargo.push( listadoCargo[index].nombre );
			                arrCodigoCargo[listadoCargo[index].nombre] = listadoCargo[index].nombre;
			                
			                $("#cargo").append("<option value='"+listadoCargo[index].nombre+"' class='"+listadoCargo[index].nombre+"'>"+listadoCargo[index].nombre+"</option>");
				    	});

						$.each(listadoGrupoAutorizacion, function(index){
							arrGrupoAutorizacion.push( listadoGrupoAutorizacion[index].nombre );
			                arrCodigoGrupoAutorizacion[listadoGrupoAutorizacion[index].nombre] = listadoGrupoAutorizacion[index].codigo;
			                
			                $("#grupo").append("<option value='"+listadoGrupoAutorizacion[index].nombre+"'>"+listadoGrupoAutorizacion[index].nombre+"</option>");
				    	});

						// Inicio Controles Actualizar Registro
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
						// Fin Controles Actualizar Registro
						
						// Inicio Controles Elminar Registro
						$(".eliminar-btn").bind({click: function() {
								var r = confirm("Seguro que desea eliminar la Descuento " + $(this).parent().parent().children().first().text());
								if (r == true){
									codigo = $(this).parent().children().first().val();
									nombre = ""; descripcion = "";rangoInicial="";rangoFinal=""; activo = ""; grupoAutorizacion=""; cargo = ""; aplicaTasaMinima=""; tipoConsulta = "eliminar";
									enviarDatos(codigo, grupoAutorizacion, nombre, rangoInicial, rangoFinal, cargo, activo, aplicaTasaMinima, tipoConsulta);
							    	$(this).parent().parent().remove();
								}
							}
						});	
						// Fin Controles Elminar Registro
					}else{
						$("#dataTableContent").append("<tr><td colspan='5'>No existen Registros</td></tr>");
					}
				}
			});			
			
			// Inicio Controles Grabar Resgistro
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
					if ($("#aplicaTasaMinima").is(':checked')) aplicaTasaMinima = 1;
					codigo = $("#codigo").val();
					nombre = $("#nombre").val();
					//descripcion = $("#descripcion").val();
					rangoInicial = $("#rangoInicial").val();
					rangoFinal = $("#rangoFinal").val();
					grupoAutorizacion = arrCodigoGrupoAutorizacion[$("#grupo").val()];
					cargo = $("#cargo").val();
					if (codigo == "" || codigo  == undefined)
						tipoConsulta = "crear";
					else
						tipoConsulta = "actualizar";
					
					if(retorno)
						enviarDatos(codigo, grupoAutorizacion, nombre, rangoInicial, rangoFinal, cargo, activo, aplicaTasaMinima, tipoConsulta);
				});
			// Fin Controles Grabar Resgistro
			
			function enviarDatos(codigo, grupoAutorizacion, nombre, rangoInicial, rangoFinal, cargo, activo, aplicaTasaMinima, tipoConsulta){
				$.ajax({
					url : '../DescuentoController',
					data : {
						"codigo" : codigo,
						"grupoAutorizacion": grupoAutorizacion,
						"cargo": cargo,
						"nombre" : nombre,
						//"descripcion" : descripcion,
						"rangoInicial" : rangoInicial,
						"rangoFinal" : rangoFinal,
						"activo" : activo,
						"aplicaTasaMinima":aplicaTasaMinima,
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
							"Descuentos")) {
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
							<h4 class="modal-title" id="myModalLabel">Nuevo Descuento</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">El Descuento se grabo con exito.</div>
							<div class="form-group">
								<table style="min-width: 100%;">
									<tr>
										<td valign="top" style="width: 50%;">
											<div class="panel panel-default">
											  <div class="panel-heading">Datos Generales</div>
											  <div class="panel-body">
											  	<input type="hidden"class="form-control" id="codigo">
												<table style="min-width: 100%;">
													<tr>
														<td colspan="3">
															<label>Grupo Autorización</label> 
															<select type="select" class="form-control required" id="grupo">
															  <option>Seleccione una opcion</option>
															</select>
														</td>
													</tr>
													<tr>
														<td colspan="3">
															<label>Nombre</label> 
															<input type="text"class="form-control required" id="nombre">
														</td>
													</tr>
													<!-- tr>
														<td colspan="3">
															<label>Descripcion</label> 
															<textarea class="form-control required" rows="3" id="descripcion"></textarea>
														</td>
													</tr-->
													<tr>
														<td>
															<label>Rango Inicial</label> 
															<input type="number" class="form-control required" id="rangoInicial">
														</td>
														<td width="10px"></td>
														<td>
															<label>Rango Final</label> 
															<input type="number" class="form-control required" id="rangoFinal">
														</td>
													</tr>
													<tr>
														<td colspan="3">
															<label>Cargo que Autoriza</label> 
															<select type="select" class="form-control required" id="cargo">
															  <option>Seleccione una opcion</option>
															</select>
														</td>
													</tr>
													<tr>
														<td colspan="1">
															<div class="checkbox">
																<label> <input type="checkbox" value="activo" id="activo">Activo</label>
															</div>
														</td>
													
														<td colspan="2">
															<div class="checkbox">
																<label> <input type="checkbox" value="aplicaTasaMinima" id="aplicaTasaMinima">Aplica Tasa Minima</label>
															</div>
														</td>
													</tr>
												</table>
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
							<th>Grupo</th>
							<th>Nombre</th>
							<!-- th>Descripción</th-->
							<th>Rango Inicial</th>
							<th>Rango Final</th>
							<th>Cargo Autoriza</th>
							<th>Activo</th>
							<th>Aplica Tasa Minima</th>
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