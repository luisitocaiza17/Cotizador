<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Detalle Producto - CotizadorQBE</title>
	
	<!-- Core CSS - Include with every page -->
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	<link href="../static/css/loading.css" rel="stylesheet">	
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="../static/js/util.js"></script>	
	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>
		<!-- Inicio Variables Globales -->
		var tipoConsulta = "";
		var codigo = "";
		var nombreProducto= "";
		var plan= "";
		var coberturaConjunto= "";
		var texto= "";
		
    	var arrProducto = new Array();
    	var arrCodigoProducto = new Object();
		
		$(document).ready(function() {
			activarMenu("DetalleProducto");
			
			$.ajax({
				url : '../DetalleProductoController',
				data : {
					"tipoConsulta" : "cargarCombos"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					//Para Cargar Combos
                    var listadoProducto = data.listadoProducto;
					$.each(listadoProducto,function(index) {
										arrProducto.push(listadoProducto[index].nombre);
										arrCodigoProducto[listadoProducto[index].nombre] = listadoProducto[index].codigo;
										$("#productoBusqueda")
												.append(
														"<option value='"+listadoProducto[index].nombre+"'>"
																+ listadoProducto[index].nombre
																+ "</option>");
									});
					$("#loading").fadeOut();

				}
			});			
			
			/* Resetea el formulario cada vez que presiona el boton nuevo */
				$("#addButton").click(function(){
					$("#msgPopup").hide();
					$("#formCrud").get(0).reset();
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
					
					codigo = $("#codigo").val();
					nombreProducto = $("#nombreProducto").val();
					plan = $("#plan").val();
					coberturaConjunto = $("#coberturaConjunto").val();					
					texto = $("#texto").val();
					
					if (codigo == ""){
						tipoConsulta = "crear";
					}else{
						tipoConsulta = "actualizar";
					}
					codigoEnsurance = $("#codigoEnsurance").val();
					enviarDatos(codigo, nombreProducto, plan, coberturaConjunto, texto, tipoConsulta);
				});
			/* Fin Controles Grabar Resgistro*/
			
			function enviarDatos(codigo, nombreProducto, plan, coberturaConjunto, texto, tipoConsulta){
				$.ajax({
					url : '../DetalleProductoController',
					data : {
						"codigo" : codigo,
						"nombreProducto" : nombreProducto,
						"plan" : plan,
						"coberturaConjunto" : coberturaConjunto,
						"texto" : texto,
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
		
		function buscar() {
			// Consultar por Producto
			var productoBusqueda = $("#productoBusqueda").val();
			$.ajax({
				url : '../DetalleProductoController',
				data : {
					"tipoConsulta" : "encontrarPorProducto",
					"productoBusqueda" : productoBusqueda,
				},
				type : 'post',
				datatype : 'json',
				success : function (data) {
					$("#loading").remove();
					//if(data.numRegistros > 0){
						var listadoDetalleProducto = data.listadoDetalleProducto;
						$.each(listadoDetalleProducto, function(index){
							$("#dataTableContent").append("	<tr class='odd gradeX'>" +
 									" <td relation='codigo'>"+ listadoDetalleProducto[index].codigo+"</td>" +									
									" <td relation='nombreProducto'>"+ listadoDetalleProducto[index].nombreProducto +"</td>" +
									" <td relation='plan'>"+ listadoDetalleProducto[index].plan +"</td>" +
									" <td relation='coberturaConjunto'>"+ listadoDetalleProducto[index].coberturaConjunto +"</td>" +
 									" <td relation='texto'>"+ listadoDetalleProducto[index].texto +"</td>" +									
									" <td width='175px'>" +
										" <input type='hidden' value='"+ listadoDetalleProducto[index].codigo +"'/>" +
										" <button type='button' class='btn btn-success btn-xs actualizar-btn'>" +
		  									" <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
										" </button>" +
// 										" <button type='button' class='btn btn-danger btn-xs eliminar-btn'>" +
// 										  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar" +
// 										" </button>" +
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
// 						$(".eliminar-btn").bind({click: function() {
// 								var r = confirm("Seguro que desea eliminar el Punto de Venta " + $(this).parent().parent().children().first().text());
// 								if (r == true){
// 									codigo = $(this).parent().children().first().val();
// 									nombreProducto = ""; plan = ""; coberturaConjunto = ""; texto="";									
// 									tipoConsulta = "eliminar";
// 									enviarDatos(codigo, nombreProducto, plan, coberturaConjunto, texto, tipoConsulta);
// 							    	$(this).parent().parent().remove();
// 								}
// 							}
// 						});	
						/* Fin Controles Elminar Registro */
				//	}else{
			//			$("#dataTableContent").append("<tr><td colspan='4'>No existen Registros</td></tr>");
				//	}
					
				}
			});
			
			/* Resetea el formulario cada vez que presiona el boton nuevo */
			$("#addButton").click(function(){
				$("#msgPopup").hide();
				$("#formCrud").get(0).reset();
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
				
				codigo = $("#codigo").val();
				nombreProducto = $("#nombreProducto").val();
				plan = $("#plan").val();
				coberturaConjunto = $("#coberturaConjunto").val();					
				texto = $("#texto").val();
				
				if (codigo == ""){
					tipoConsulta = "crear";
				}else{
					tipoConsulta = "actualizar";
				}
				codigoEnsurance = $("#codigoEnsurance").val();
				enviarDatos(codigo, nombreProducto, plan, coberturaConjunto, texto, tipoConsulta);
			});
		/* Fin Controles Grabar Resgistro*/
		
		function enviarDatos(codigo, nombreProducto, plan, coberturaConjunto, texto, tipoConsulta){
			$.ajax({
				url : '../DetalleProductoController',
				data : {
					"codigo" : codigo,
					"nombreProducto" : nombreProducto,
					"plan" : plan,
					"coberturaConjunto" : coberturaConjunto,
					"texto" : texto,
					"tipoConsulta" : tipoConsulta
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					$("#msgPopup").show();
				}
			});
		}
}//Fin Buscar
	</script>
</head>
<body>
<%
			// Permitimos el acceso si la session existe		
				if(session.getAttribute("login") == null){
				    response.sendRedirect("/CotizadorWeb");
				}
%>
	<div class="well">			
			<label>Producto</label>
			<select type="select" class="form-control" id="productoBusqueda">
		    <option value='0'>Seleccione una opcion</option>
		    </select>
		    <br>
		    <a class="btn btn-primary" id ="buscar" onclick="buscar();"><span class="glyphicon glyphicon-search"></span> Buscar</a>
		    <br>
		</div>
		
	<div class="row crud-nav-bar">
<!-- 		Button trigger modal -->
		<button class="btn btn-primary hidden" data-toggle="modal" data-target="#add" id="addButton">
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
							<h4 class="modal-title" id="myModalLabel">Detalle Producto</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">Se grabo con exito.</div>
							<div class="form-group">								
								<label>ID Detalle</label>
								<input type="text" class="form-control" id="codigo" disabled>								
								<label>Nombre Producto</label> 
								<input type="text"class="form-control" id="nombreProducto" disabled>
								<label>Plan</label> 
								<input type="text"class="form-control" id="plan" disabled>	
								<label>Cobertura Conjunto</label>
								<input type="text" class="form-control" id="coberturaConjunto" disabled>
								<label>Texto</label> 
								<textarea type="text" class="form-control" rows="10" id="texto"></textarea>																
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
<!-- 				<div class="input-group"> <span class="input-group-addon">Filter</span> -->
<!-- 				    <input id="filter" type="text" class="form-control" placeholder="Escriba la palabra a buscar..."> -->
<!-- 				</div>			 -->
				<table class="table table-striped table-bordered table-hover"
					id="dataTable">
					<thead>
						<tr>
							<th>ID Detalle</th>
							<th>Nombre Producto</th>
							<th>Plan</th>
							<th>Cobertura Conjunto</th>
							<th>Texto</th>							
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