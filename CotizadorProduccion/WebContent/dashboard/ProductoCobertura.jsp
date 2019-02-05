<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
         <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	 <!-- Core CSS - Include with every page -->
    <link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
    <link href="../static/css/loading.css" rel="stylesheet">
	<link rel="stylesheet" href="../static/css/select2/select2.css">
    
    <!-- Table Tools -->
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.tableTools.js"></script>
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.tableTools.css" rel="stylesheet">
    
    <script src="../static/js/util.js"></script>
    <script src="../static/js/select2.js"></script>
<title>Producto Cobertura - Cotizador QBE</title>
	<script>
		var codigo = "";
		var grupoPorProducto = "";
		var cobertura = "";		
		var tipoConsulta = "";
		var grupoProductoFiltro = "";
		var arrCobertura = new Array();
        var arrCodigoCobertura = new Object();
        var opcionesSelectCobertura = new Array();
        var arrGrupoProducto = new Array();
        var arrCodigoGrupoProducto = new Object();
        var opcionesSelectGrupoProducto = new Array();
        var requerido = true;
		
		$(document).ready(function() {
			activarMenu("ProductoCobertura");
			
			 $('#LimpiarBtn').click(function(){	    	 	
				$("#grupoPorProductoFilter").val("");
		 		$('#dataTableContent').html('');
		 			
		      });
			
			$('#ConsultaBtn').click(function(){
				$('#dataTableContent').html('');
				grupoProductoFiltro = $("#grupoPorProductoFilter").val();
				if(grupoProductoFiltro == "")
				{
					alert("Seleccione el grupo por producto");
				}
				else
				{
					$.ajax({
						url : '../ProductoCoberturaController',
						data : {
							"grupoPorProductoFiltro" : grupoProductoFiltro,
							"tipoConsulta" : "encontrarTodos"
						},
						type : 'POST',
						datatype : 'json',
						success : function(data) {
							$("#loading").remove();
							if(data.numRegistros > 0){
								var listadoProductoCobertura = data.listadoProductoCobertura;
								$.each(listadoProductoCobertura, function(index){
									$("#dataTableContent").append("	<tr class='odd gradeX'>" +
											" <td relation='grupoPorProducto'>"+ listadoProductoCobertura[index].grupoPorProducto +"</td>" +									
											" <td relation='cobertura'>"+ listadoProductoCobertura[index].cobertura +"</td>" +
											" <td width='175px'>" +
												" <input type='hidden' value='"+ listadoProductoCobertura[index].codigo +"'/>" +
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
											grupoPorProducto = ""; cobertura = "";
											tipoConsulta = "eliminar";
											enviarDatos(codigo, grupoPorProducto, cobertura, tipoConsulta);
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
				}
				
			})
			
					
			
			/* Inicio Controles Grabar Resgistro*/
				$("#save-record").click(function() {
					requerido = true;
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
		
					grupoPorProducto = $("#grupoPorProducto").select2("val");
					if(grupoPorProducto == "" && requerido)
					{
						alert("Por favor seleccione un grupo por producto");
	                    requerido = false;
	                    return false;
					}
					cobertura = $("#cobertura").select2("val");
					if(cobertura == "" && requerido)
					{
						alert("Por favor seleccione una cobertura");
	                    requerido = false;
	                    return false;	
					}
					codigo = $("#codigo").val();
					if(requerido)
					{
						if (codigo == ""){
							tipoConsulta = "crear";}
						else{
							tipoConsulta = "actualizar";
						}
						enviarDatos(codigo, grupoPorProducto, cobertura, tipoConsulta);
					}
					
				});
			/* Fin Controles Grabar Resgistro*/
			
			function enviarDatos(codigo, grupoPorProducto, cobertura, tipoConsulta){
				$.ajax({
					url : '../ProductoCoberturaController',
					data : {						
						"codigo" : codigo,
						"grupoPorProducto" : grupoPorProducto,
						"cobertura" : cobertura,						
						"tipoConsulta" : tipoConsulta
					},
					type : 'POST',
					datatype : 'json',
					success : function(data) {
						if(data.exitoCrear)
						{
							$("#msgPopup").show();
						}
						else
						{
							alert("La cobertura ya existe en el grupo por producto");	
						}
						
					}
				});
			}
			
			
			$('#addButton').click(function(){
				/* CONSULTA LAS COBERTURAS DISPONIBLES*/
				$.ajax({
					url : '../CoberturaController',
					data : {
						"tipoObjeto" : "vehiculos",
						"tipoConsulta" : "encontrarTodos"
					},
					type : 'POST',
					datatype : 'json',
					success : function(data) {
						
						var listadoCobertura = data.listadoCobertura;
                        $.each(listadoCobertura, function (index) {
                        	arrCobertura.push(listadoCobertura[index].nombre);
                        	arrCodigoCobertura[listadoCobertura[index].nombre] = listadoCobertura[index].codigo;
                        	opcionesSelectCobertura[index]={"id":listadoCobertura[index].codigo,"text":listadoCobertura[index].nombre};
                        });
                        
                        $("#cobertura").select2({
            				data : opcionesSelectCobertura,
            				placeholder : "Escoja una Cobertura"        				
            			});
						
						
						
						
						
// 						var listadoCobertura = data.listadoCobertura;
// 						$.each(listadoCobertura, function(index){
// 							$("#cobertura").append("<option value='"+ listadoCobertura[index].nombre +"'>" + listadoCobertura[index].nombre + "</option>");	
// 						});
						
						
					}
				});
				
				/* CONSULTA LOS GRUPO POR PRODUCTO*/
			});
			
			
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
						arrGrupoProducto.push(listadoGrupoPorProducto[index].nombreComercialProducto);
                    	arrCodigoGrupoProducto[listadoGrupoPorProducto[index].nombreComercialProducto] = listadoGrupoPorProducto[index].codigo;
                    	opcionesSelectGrupoProducto[index]={"id":listadoGrupoPorProducto[index].codigo,"text":listadoGrupoPorProducto[index].nombreComercialProducto};
                    });
                    
                    $("#grupoPorProducto").select2({
        				data : opcionesSelectGrupoProducto,
        				placeholder : "Escoja un Grupo Producto"        				
        			});
                    

                    $("#grupoPorProductoFilter").select2({
        				data : opcionesSelectGrupoProducto,
        				placeholder : "Escoja un Grupo Producto"        				
        			});
					
				}
			});
			
			
			/* CONSULTA LOS GRUPO POR PRODUCTO FILTRO*/
			/*$.ajax({
				url : '../GrupoPorProductoController',
				data : {
					"tipoConsulta" : "encontrarTodos"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					var listadoGrupoPorProducto = data.listadoGrupoPorProducto;
					$.each(listadoGrupoPorProducto, function(index){
						$("#grupoPorProductoFilter").append("<option value='"+ listadoGrupoPorProducto[index].codigo +"'>" + listadoGrupoPorProducto[index].nombreComercialProducto + "</option>");	
					});
					
					
				}
			});*/
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
							"ProductoCobertura")) {
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
								Producto Cobertura</center></td>

					</tr>
					<tr>
						<th>Grupo por Producto:</th>
						<th><input type="select2"
							class=" " id="grupoPorProductoFilter">
								</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							<button class="btn btn-primary" id="ConsultaBtn">
								<span class="glyphicon glyphicon-search"></span> &nbsp; Consulta
							</button>							
						</th>
						<th>
							<button class="btn btn-primary" id="LimpiarBtn">
								<span class="glyphicon glyphicon-trash"></span> &nbsp; Limpiar
							</button>
						</th>
						<th>
							<button class="btn btn-primary" data-toggle="modal"
								data-target="#add" id="addButton">
								<span class="glyphicon glyphicon-plus"></span> &nbsp; Nuevo
							</button>
						</th>
						<th>&nbsp;</th>
					</tr>
					<tr>
						<th>&nbsp;</th>
						<th><div id="buscando" hidden="hidden">
								<div style="margin-top: 10px;">
									<span id="loading-msg"></span><img
										src="../static/images/ajax-loader.gif" /> Buscando la
									informacion, por favor espere...
								</div>
							</div></th>
						<th>&nbsp;</th>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
		<!-- Button trigger modal -->
		
<div class="row crud-nav-bar">	
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
							<h4 class="modal-title" id="myModalLabel">Nuevo Producto Cobertura</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">El producto cobertura se grabo con exito.</div>
							<div class="form-group">
								<input type="hidden"class="form-control" id="codigo">
								<label>Grupo por Producto</label>
								<input style="width:100%"  type="select2" class="required_select2" id="grupoPorProducto">
								<label>Cobertura</label> 
								<input style="width:100%"  type="select2" class="required_select2" id="cobertura">                           	
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