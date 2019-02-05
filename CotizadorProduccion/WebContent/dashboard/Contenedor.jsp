<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Cache-control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Contenedor - CotizadorQBE</title>
	
	<!-- Core CSS - Include with every page -->
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	
	<!-- Table Tools -->
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.tableTools.js"></script>
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.tableTools.css" rel="stylesheet">
	
	<script id="tipoObjetoCargaInicial" src="../static/js/vehiculos/carga.inicial.js" tipoObjetoValor="Ninguno"></script>	
	<script id="tipoObjetoMetodos" src="../static/js/vehiculos/metodos.js" tipoObjetoValor="Ninguno"></script>
	<script src="../static/js/cotizador/comun.js"></script>
	
	<script src="../static/js/util.js"></script>
    <!-- Para el Datepicker-->
    <link href="../static/css/datepicker.css" rel="stylesheet">
    <script src="../static/js/bootstrap-datepicker.js"></script>
	<script>
		var codigoEnsurance = "";
		var numeroContenedor = "";
		var activo = 0;
		var vigenciaDesde = "";
		var vigenciaHasta = "";
		var descripcion = "";
		var requerido = true;
		
		$(document).ready(function() {
			activarMenu("CotizacionesPendientesCerrados");		 	
			 $('#dataTable').hide();
	    	 $('#msgError').hide();
	    	 
	    	 $('#LimpiarBtn').click(function(){	    	 	
	    	 	$("#contenedor_numero").val("");
	    	 	$("#dp1").val("");
	    	 	$("#dp2").val("");
	    	 	$("#contenedor_ensurance").val("");
	    	 	$('#dataTable').hide();
	 			$('#dataTableContent').html('');
	 			$('#dataTable_wrapper').hide();	
	    	 });
	    	 
	    	 $('#ConsultaBtn').click(function(){
	    		 $('#dataTable').hide();
	 			$('#dataTable_wrapper').hide();	 			
	 				 			
	    		 $("#buscando").fadeIn("slow");

	    		 // Validaciones de las busquedas
	    		 var numeroCon = $("#contenedor_numero").val();
	    		 var fechaInicio = $("#dp1").val();
	    		 var fechaFin = $("#dp2").val();
	    		 var ensurance = $("#contenedor_ensurance").val();
	    		 if(numeroCon=="" && fechaInicio == "" && fechaFin == "" && ensurance == ""){
	    		 	alert("Ingrese al menos un campo de busqueda");
	    		 	$("#buscando").fadeOut("slow");
	    		 	return false;
	    		 }

				if(fechaInicio != "" && fechaFin=="" ){
	    		 	alert("Ingrese una fecha Fin de Busqueda");
	    		 	$("#buscando").fadeOut("slow");
	    		 	return false;
	    		 }
				if(fechaInicio == "" && fechaFin !="" ){
	    		 	alert("Ingrese una fecha Inicio de Busqueda");
	    		 	$("#buscando").fadeOut("slow");
	    		 	return false;
	    		 }
				
	    		 $.ajax({
	    			 url : '../ContenedorController',
	 				data : {
	 					"fInicio" : fechaInicio,
	 					"fFinal" : fechaFin,
	 					"numeroCon" : numeroCon,
	 					"ensurance" : ensurance,
	 					"tipoConsulta" : "buscar"
	 				},
	 				type : 'POST',
	 				datatype : 'json',
	 				success : function(data) {					
	 					var existenRegistro = false;
	 					$('#dataTable').show();
	 					$('#dataTable_wrapper').show();	 					
	 					$('#dataTableContent').html('');
	 					$("#datos_temporal").val("");
	 					var listadoContenedor = 0;
	 					listadoContenedor = data.listadoContenedor;
	 					var auxiliar = "";
	 					if(listadoContenedor.length > 0){
	 						$.each(listadoContenedor, function(index){
	 							var aux="	<tr class='odd gradeX'>" +
	 							" <td relation='ensurance'>"+ listadoContenedor[index].ensurance +"</td>" +
	 							" <td relation='numero'>"+ listadoContenedor[index].numero +"</td>" +
	 							" <td relation='descripcion'>"+ listadoContenedor[index].descripcion +"</td>" +
	 							" <td relation='vigenciaDesde'>"+ listadoContenedor[index].vigenciaDesde +"</td>" +
	 							" <td relation='vigenciaHasta'>"+ listadoContenedor[index].vigenciaHasta +"</td>" +
	 						"</tr>";
	 								auxiliar +=	aux;
	 							
	 						$("#buscando").fadeOut("slow");	 					
	 						});	
							
							// Evitar Reinicializacion datatable
	 						var oTable = $('#dataTable').dataTable();
							oTable.fnDestroy();							
							$('#dataTable tbody').html(auxiliar);
							
	 						$('#dataTable').dataTable( {	 							
        						"pagingType": "full",
        						"bFilter": true,
        						"bLengthChange": false,
        						"bSort" : false,
        						"iDisplayLength": 10, // Limitamos el numero de filas en la paginacion
        						"dom": 'T<"clear">lfrtip',
        						"tableTools": {
            						"sSwfPath": "/CotizadorWeb/static/js/sb-admin/plugins/dataTables/swf/copy_csv_xls.swf",
            					}		        						
    						});
					
	 						existenRegistro = true;
	 					}
	 				if(!existenRegistro){
	 						var oTable = $('#dataTable').dataTable();
							oTable.fnDestroy();							
							$('#dataTable tbody').html("<tr><td colspan='12'>No existen Registros</td></tr>");
	 						$("#buscando").fadeOut("slow");
	 				}
	 				}
	 			});				
	    	 });
	    	 
	    	 /* Inicio Controles Grabar Registro*/
	    	 $("#save-record").click(function() {
	    		 $("#msgPopup").hide();
	    		 $('#msgError').hide();
	    		 requerido = true;
					$(".required").css("border", "1px solid #ccc");
					$(".required").each(function(index) {
						var cadena = $(this).val();
						if (cadena.length <= 0) {
							$(this).css("border", "1px solid red");
							alert("Por favor ingrese el campo requerido");
							$(this).focus();
							requerido = false;
							return false;
						}
					});
					if(requerido)
					{
						if ($("#activo").is(':checked')) activo = 1;
						
						codigoEnsurance = $("#codigoEnsurance").val();
						numeroContenedor = $("#numeroContenedor").val();
						vigenciaDesde = $("#vigenciaDesde").val();
						vigenciaHasta = $("#vigenciaHasta").val();
						descripcion = $("#descripcionContenedor").val();
						enviarDatos(codigoEnsurance, numeroContenedor, vigenciaDesde, vigenciaHasta, activo, descripcion);
					}
					
				});
	    	 
	    	 function enviarDatos(codigoEnsurance, numeroContenedor, vigenciaDesde, vigenciaHasta, activo, descripcion){				
					$.ajax({
						url : '../ContenedorController',
						data : {
							"codigoEnsurance" : codigoEnsurance,
							"numeroContenedor" : numeroContenedor,
							"vigenciaDesde" : vigenciaDesde,
							"vigenciaHasta" : vigenciaHasta,
							"descripcion" : descripcion,
							"activo" : activo,
							"tipoConsulta" : "crear"
						},
						type : 'POST',
						datatype : 'json',
						success : function(data) {
							if(data.success)
								$("#msgPopup").show();
							else
							{
								$('#msgError').show();
							}
								
						}
					});
				}
	    	 
		});

				
					
				//Metodo validacion de fechas buscador
		    		$(function(){
		    			var startDate = new Date();
		    			var endDate = new Date();
		    			var startDateNuevo = new Date();
		    			var endDateNuevo = new Date();
		    			
		    			$('#dp1').datepicker().on('changeDate', function(ev){
		    					if (ev.date.valueOf() > endDate.valueOf()){
		    						alert("La Fecha Inicial no puede ser mayor que la Fecha Actual");
		    						$("#dp1").val("");
		    						return false;
		    					} else {		    					
		    						startDate = new Date(ev.date);
		    						$('#startDate').text($('#dp1').data('date'));
		    					}
		    					$('#dp1').datepicker('hide');
		    				});
		    			
		    			$('#dp2').datepicker().on('changeDate', function(ev){
	    					if (ev.date.valueOf() < startDate.valueOf()){
	    						alert('La Fecha Final no puede ser menor que la Fecha Inicial');
	    						$("#dp2").val("");
	    						return false;
	    					} else {
	    						
	    						endDate = new Date(ev.date);
	    						$('#endDate').text($('#dp2').data('date'));
	    					}
	    					$('#dp2').datepicker('hide');
	    				});
		    			
		    			$('#vigenciaDesde').datepicker({
		    				format: "dd/mm/yyyy",
		    				language: "es",
		    				autoclose: true,
		    				todayBtn: true
		    			}).on(
		    			  'show', function(ev) {			
		    				// Obtener valores actuales z-index de cada elemento
		    				var zIndexModal = $('#add').css('z-index');
		    				var zIndexFecha = $('.datepicker').css('z-index');
		    			        $('.datepicker').css('z-index',zIndexModal+1);
		    			        
		    			});
		    			
		    			$('#vigenciaDesde').datepicker().on('changeDate', function(ev){
		    				if (ev.date.valueOf() > endDateNuevo.valueOf()){
	    						alert("La Fecha Inicial no puede ser mayor que la Fecha Actual");
	    						$("#vigenciaDesde").val("");
	    						return false;
	    					} else {		    					
	    						startDateNuevo = new Date(ev.date);
	    						$('#startDateNuevo').text($('#vigenciaDesde').data('date'));
	    					}
		    				$('#vigenciaDesde').datepicker('hide');
	    				})
		    			
		    			$('#vigenciaHasta').datepicker({
		    				format: "dd/mm/yyyy",
		    				language: "es",
		    				autoclose: true,
		    				todayBtn: true
		    			}).on(
		    			  'changeDate', function(ev) {			
		    				
		    				var zIndexModal = $('#add').css('z-index');
		    				var zIndexFecha = $('.datepicker').css('z-index');
		    			 
		    			        $('.datepicker').css('z-index',zIndexModal+1);
		    			        
		    			      
		    			});
		    			
		    			$('#vigenciaHasta').datepicker().on('changeDate', function(ev){
		    				if (ev.date.valueOf() < startDateNuevo.valueOf()){
	    						alert('La Fecha Final no puede ser menor que la Fecha Inicial');
	    						$("#vigenciaHasta").val("");
	    						return false;
	    					} else {
	    						
	    						endDateNuevo = new Date(ev.date);
	    						$('#endDateNuevo').text($('#vigenciaHasta').data('date'));
	    					}
		    				$('#vigenciaHasta').datepicker('hide');
	    				})
		    			
		    			
		    		});
				
		    		
					
							
				 			
				 			
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
	<div class="well">
			<table class="table">
				<thead>
					<tr>
						<td colspan="3" style="font-weight: bold;"><center>Buscador
								Contenedor</center></td>

					</tr>
					<tr>
						<th>Busqueda por Contenedor:</th>
						<th>Numero: <input type="text" id="contenedor_numero"
							onkeypress="return justNumbers(event);"></th>
						<th>Ensurance: <input type="text" id="contenedor_ensurance"
							onkeypress="return justNumbers(event);"></th>
					</tr>
					<tr>
						<th>Busqueda por Fechas:</th>
						<th>Fecha Inicial: <input type="text"
							data-date-format="dd/mm/yyyy" id="dp1"></th>
						<th>Fecha Final: <input type="text"
							data-date-format="dd/mm/yyyy" id="dp2"></th>
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
	
		<!-- Button trigger modal -->

	</div>
	<!-- Datatable -->
	<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">	
				<table class="table table-striped table-bordered table-hover"
					id="dataTable" style="font-size: x-small;">
					<thead>
						<tr>
							<th>Ensurance</th>
							<th>Numero</th>
							<th>Descripcion</th>
							<th>Vigencia Desde</th>
							<th>Vigencia Hasta</th>
						</tr>
					</thead>
					<tbody id="dataTableContent" class="searchable" style="font-size: x-small;">
											
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- Datatable -->	
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
							<h4 class="modal-title" id="myModalLabel">Contenedor</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">El contenedor se grabo con exito.</div>
							<div class="alert alert-warning" id="msgError">El contenedor ya existe.</div>
							<div class="form-group">
								<label>Codigo Ensurance</label> 
								<input type="text"class="form-control required" id="codigoEnsurance" onkeypress="return justNumbers(event);">
								<label>Numero del Contenedor</label> 
								<input type="text"class="form-control required" id="numeroContenedor" onkeypress="return justNumbers(event);">
								<label>Descripcion</label> 
								<input type="text"class="form-control required" id="descripcionContenedor">
								<label>Vigencia Desde</label> 
								<input type="text" data-date-format="dd/mm/yyyy" class="form-control required" id="vigenciaDesde">
								<label>Vigencia Hasta</label> 
								<input type="text" data-date-format="dd/mm/yyyy" class="form-control required" id="vigenciaHasta">
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
	<!-- Modal -->
	
</body>
</html>