<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<link rel="stylesheet" href="../static/css/jquery-steps/normalize.css">
		<link rel="stylesheet" href="../static/css/jquery-steps/main.css">
		<link rel="stylesheet" href="../static/css/jquery-steps/jquery.steps.css">
		<link rel="stylesheet" href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css">
		<link rel="stylesheet" href="../static/css/select2/select2.css">
		
		<script src="../static/js/jquery-steps/modernizr-2.6.2.min.js"></script>
		<script src="../static/js/jquery-steps/jquery.cookie-1.3.1.js"></script>
		<script src="../static/js/jquery-steps/jquery.steps.min.js"></script>
		<script src="../static/js/jquery.validate.js"></script>
		<script src="../static/js/bootstrap.min.js"></script>
		<script src="../static/js/util.js"></script>
		<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
		<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
		<script src="../static/js/jquery-dynamic-url/jquery.dynamic-url.js"></script>
		<script src="../static/js/select2.js"></script>
		
		<!--  	para el tooltipster -->
		<script src="../static/js/jquery-ui/jquery-ui.js"></script>
		<link href="../static/js/jquery-ui/jquery-ui.theme.css" rel="stylesheet" type="text/css" />
		<link href="../static/css/tooltipster.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../static/js/jquery.tooltipster.js"></script>
		<script type="text/javascript" src="../static/js/jquery.tooltipster.min.js"></script>



<!-- KENDO -->
<link rel="stylesheet" href="../static/css/Kendo/kendo.common.min.css" />
<link rel="stylesheet" href="../static/css/Kendo/kendo.blueopal.min.css" />
<script src="../static/js/Kendo/kendo.all.min.js"></script>
<script src="../static/js/Kendo/kendo.web.min.js"></script>
	
	<script>
		var tipoDeducibleId = "";
		//var matriz = 0;
		//var activo = 0;
		var tipoConsulta = "";
		//var arrTipoIdentificacion = new Array();
		//var arrCodigoTipoIdentificacion = new Object();
		var a = "";
		var b = "";
		var ArrayInspectorDetalle = new Array();
		var auxTipoDeducibleId = "";
		var auxValor = 0;
		
		$(document).ready(function() {
			
			cargarProductosPorGrupoProducto();
			cargarRamos();
			cargarTipoDeducible();
			
			cargar();
			
			$("#save-record").click(function(){				
				tipoConsulta = "crear";
				var contador = 0;
                 $("#dataTableInspector2 tr").each(function (index) {
                	 auxTipoDeducibleId = $(this).find("td").eq(0).html();
                	 auxValor = $(this).find("td").eq(2).html();
                     var datalleInspector = {
                    	tipoDeducibleId: auxTipoDeducibleId,
                        valor : auxValor
                     };
                     ArrayInspectorDetalle[contador] = datalleInspector;
                     contador++;
                  });
              	if(contador <= 0){
              		alert("Ingrese el detalle de la configuaración");
              	}else{
              		enviarDatos($("#listaRamo").val(),$("#listaGrupoPorProducto").val(), ArrayInspectorDetalle, tipoConsulta);
              		ArrayInspectorDetalle = [];
              	}
			});
			
			
			$("#agregar").click(function(){
				
				var bandera = false;
				var auxTipoDedId2 = $("#listaTipoDeducible").val();
				var auxTipoDedNombre = $("#listaTipoDeducible option:selected").text();;
				var auxValor = $("#valor").val();
				
				$("#dataTableInspector2 tr").each(function (index) {                    
					auxTipoDeducibleId = $(this).find("td").eq(0).html();
                    if(auxTipoDedId2 == auxTipoDeducibleId){
                    	bandera = true;
                    	return false;
                    }else{
                    	bandera = false;
                    }
                });
				if(bandera){
					alert("El tipo de deducible ya esta en la lista.");
				}else{
					if((auxTipoDedId2 > 0)){
						$("#dataTableInspector2").append("<tr class='odd gradeX'>" +
								" <td relation='tipoDeducibleId' style='display:none;' >"+ auxTipoDedId2 +"</td>" +
								" <td relation='tipoDeducibleNombre' style='width: 40%'>"+ auxTipoDedNombre+"</td>" +
								" <td relation='valor'>"+ auxValor +"</td>" +
								" <td style='width: 20%'>" +	
									" <button type='button' class='btn btn-danger btn-xs eliminar-btn eliminar'>" +
										  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar" +
									" </button>" +
										
								"</td>" +
							"</tr>");	
					}else{
						alert("Debe seleccionar un tipo de deducible.");
					}		
				}				
				$(".eliminar").click(function(){
					$(this).parent().parent().remove();
				});
				$("#valor").val()
			});
			
			
			$("#addButton").click(function(){
				$("#dataTableInspector2").children().remove();
				//$("#listaEntidad").data("kendoMultiSelect").readonly(false);	
			});
			
			
		});	
			
		function cargar(){
			$("#dataTableContent").children().remove();
			$.ajax({
				url : '../PymeTipoDeducibleGeneralController',
				data : {
					"tipoConsulta" : "buscarTodos"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					$("#loading").hide();
					var listaDeducibles = data.tipoDeduciblesResumen;	
					
					$.each(listaDeducibles, function(index){
						$("#dataTableContent").append("	<tr class='odd gradeX'>" +
								" <td relation='ramoNombre'>"+ listaDeducibles[index].ramoNombre+"</td>" +
								" <td relation='grupoNombre'>"+ listaDeducibles[index].grupoNombre +"</td>" +
								" <td width='175px'>" +
								" <button type='button' class='btn btn-success btn-xs actualizar-btn' onClick='actualizar("+listaDeducibles[index].ramoId+","+listaDeducibles[index].grupoPorProductoId+");' data-toggle='modal' data-target='#add'>" +
  									" <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
								" </button>" +
									" <button type='button' class='btn btn-danger btn-xs eliminar-btn' onClick='eliminar("+listaDeducibles[index].ramoId+","+listaDeducibles[index].grupoPorProductoId+");'>" +
									  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar" +
								" </button>" +
										
								"</td>" +
							"</tr>");					
					});		
				}
			});	
		}
		
		function actualizar(ramoIdArg,grupoProductoIdArg){
			$("#dataTableInspector2").children().remove();
			$.ajax({
				url : '../PymeTipoDeducibleGeneralController',
				data : {
					"tipoConsulta" : "buscarPorId",
					"ramoId" : ramoIdArg,
					"grupoPorProductoId":grupoProductoIdArg
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					$("#listaRamo").val(ramoIdArg);
					$("#listaGrupoPorProducto").val(grupoProductoIdArg);
					
					$("#listaRamo").prop( "disabled", true );
					$("#listaGrupoPorProducto").prop( "disabled", true );
					
					var detallDeducibles = data.detallDeducibles;
					/*if(detalleInspector.length > 0){
						$("#listaEntidad").data("kendoMultiSelect").readonly(true);
					}else{
						$("#listaEntidad").data("kendoMultiSelect").readonly(false);
					}*/
					
					$.each(detallDeducibles, function(index){						
						$("#dataTableInspector2").append("	<tr class='odd gradeX'>" +
								" <td relation='tipoDeducibleId' style='display:none;' >"+ detallDeducibles[index].tipoDeducibleId +"</td>" +
								" <td relation='tipoDeducibleNombre' style='width: 40%'>"+ detallDeducibles[index].tipoDeducibleNombre+"</td>" +
								" <td relation='valor'>"+ detallDeducibles[index].valor +"</td>" +
								" <td style='width: 20%'>" +	
									" <button type='button' class='btn btn-danger btn-xs eliminar-btn eliminar'>" +
										  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar" +
									" </button>" +
										
								"</td>" +
							"</tr>");
						
						$(".eliminar").click(function(){
							$(this).parent().parent().remove();
						});
					});		
				}
			});	
		}
		
		function eliminar(ramoIdArg,grupoProductoIdArg){
			var r = confirm("Esta seguro que desea eliminar la configuración");
			if(r){
				$.ajax({
					url : '../PymeTipoDeducibleGeneralController',
					data : {
						"tipoConsulta" : "eliminar",
						"ramoId" :ramoIdArg,
						"grupoPorProductoId" : grupoProductoIdArg,
					},
					type : 'POST',
					datatype : 'json',
					success : function(data) {	
						$("#loading").show();
					}
				});	
				cargar();
			}
			
		}
		
		function enviarDatos(ramoId,grupoProductoId,configuracionArray,tipoConsulta){
			$.ajax({
				url:'../PymeTipoDeducibleGeneralController',
				data:{
					"ramoId" :ramoId,
					"grupoPorProductoId" : grupoProductoId,
					"configuracionArray": JSON.stringify(configuracionArray),
					"tipoConsulta":tipoConsulta
				},
				type:'POST',
				datatype : 'json',
				success : function(data){
					if(data.success)
						$("#msgPopup").show();
					else
						alert(data.error);
				}
			});
		}
		
		function cargarProductosPorGrupoProducto() {
			// Consultar listado de productos dentro de un grupos de productos
			$("#listaGrupoPorProducto").empty();

			$
			.ajax({
				url : '../GrupoPorProductoController',

				data : {
					"tipoConsulta" : "encontrarTodosPorGrupo",
					"tipoObjeto" : "PYMES",
					"grupoProductoId" : 82
				},
				async : false,
				type : 'post',
				datatype : 'json',
				success : function(data) {
					var listadoGrupos = data.listadoGruposPorProducto;
					$("#listaGrupoPorProducto").append("<option value=''>Seleccione una opcion</option>");
					$.each(listadoGrupos,function(index) {
							$("#listaGrupoPorProducto").append("<option value='" + listadoGrupos[index].id + "'>" + listadoGrupos[index].nombre + "</option>");
						});
				}
			});
		}
		
		function cargarRamos() {
			// Consultar listado de productos dentro de un grupos de productos
			$("#listaRamo").empty();

			$
			.ajax({
				url : '../RamoController',
				data : {
					"tipoConsulta" : "encontrarTodos"
				},
				async : false,
				type : 'post',
				datatype : 'json',
				success : function(data) {
					var listadoRamos = data.listadoRamo;
					$("#listaRamo").append("<option value=''>Seleccione una opcion</option>");
					$.each(listadoRamos,function(index) {
						$("#listaRamo").append("<option value='" + listadoRamos[index].codigo + "'>" + listadoRamos[index].nombre + "</option>");
					});
				}
			});
		}
		
		function cargarTipoDeducible() {
			// Consultar listado de productos dentro de un grupos de productos
			$("#listaTipoDeducible").empty();

			$.ajax({
				url : '../TipoDeducibleController',
				data : {
					"tipoConsulta" : "encontrarTodos"
				},
				async : false,
				type : 'post',
				datatype : 'json',
				success : function(data) {
					var listadoTipoDeducibles = data.listadoTipoDeducible;
					$("#listaTipoDeducible").append("<option value=''>Seleccione una opcion</option>");
					$.each(listadoTipoDeducibles,function(index) {
						$("#listaTipoDeducible").append("<option value='" + listadoTipoDeducibles[index].id + "'>" + listadoTipoDeducibles[index].texto_descripcion + "</option>");
					});
				}
			});
		}
	</script>
</head>
<body>
<%
	// Permitimos el acceso si la session existe		
	if(session.getAttribute("login") == null){
	    response.sendRedirect("/CotizadorWeb");
	}
%>
	<div class="row crud-nav-bar">
		<!-- Button trigger modal -->
		<button class="btn btn-primary" data-toggle="modal" data-target="#add" id="addButton">
			<span class="glyphicon glyphicon-plus"></span> &nbsp; Nuevo
		</button>

		<!-- Modal -->
		<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form id="formCrud">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">DEDUCIBLES GENERALES</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">Grabado con exito.</div>
							<div class="form-group">
									<label>Ramo</label> 
									<select id="listaRamo" data-placeholder="Seleccione una opción..." class="required">								  
									</select>	
									<br/>
									<br/>
									<label>Grupo por Producto</label> 
									<select id="listaGrupoPorProducto" data-placeholder="Seleccione una opción..." class="required">								  
									</select>	
									<br/>
									<br/>
									<table>
										<tr>
											<td style="width: 40%">
												<label>Tipo Deducible</label>
											</td>
											<td>
												<label>Valor</label>
											</td>
											<td style="width: 20%"></td>
										</tr>
										<tr>
											<td style="width: 40%">
												<select id="listaTipoDeducible" data-placeholder="Seleccione una opción...">								  							  
												</select>
											</td>
											<td style="width: 40%">
												<input type="text"  id="valor">								  								  
											</td>
											<td style="width: 20%">
												<button type="button" class="btn btn-primary" id="agregar">Agregar</button>
											</td>
										</tr>
									</table>					
									
									<br>
									<br>
									<table class="table table-striped table-bordered table-hover"
										id="dataTableInspector1" style="font-size: x-small;">
										<thead>
											<tr>												
												<th style="display: none;">TipoDeducibleId</th>
												<th style='width: 40%'>Tipo Deducible</th>
												<th style='width: 40%'>Valor</th>																		
												<th style='width: 20%'></th>
											</tr>
										</thead>
										<tbody id="dataTableInspector2" style="font-size: x-small;">	
										</tbody>
									</table>					
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
			<br>
				<div class="input-group"> <span class="input-group-addon">Filter</span>
				    <input id="filter" type="text" class="form-control" placeholder="Escriba la palabra a buscar...">
				</div>
			<table class="table table-striped table-bordered table-hover id="dataTable">
					<thead>
						<tr>
							<th>Ramo</th>
							<th>Grupo por Producto</th>						
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
