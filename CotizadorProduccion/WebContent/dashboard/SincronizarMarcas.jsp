<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Sincronizar Marcas, Modelos y Colores</title>
	<script id="tipoObjetoMetodos" src="../static/js/vehiculos/carga.archivo.js" tipoObjetoValor="Ninguno"></script>
	<script src="../static/js/vehiculos/formulados.js"></script>
	<script src="../static/js/cotizador/comun.js"></script>
	<!-- Core CSS - Include with every page -->
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="../static/js/util.js"></script>
	
	<!-- Table Tools -->
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.tableTools.js"></script>
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.tableTools.css" rel="stylesheet">
	
	
	<style type="text/css">
	
		.pillbox {
			border: 1px solid #bbb;
			/* margin-bottom: 10px;*/
			-webkit-border-radius: 4px;
			-moz-border-radius: 4px;
			border-radius: 4px;
			width: 251px;
		}

		.container>div,.container>table {
			margin: 20px;
		}

		.tree {
			width: 430px;
			height: 350px;
		}

		.static-loader {
			margin-left: 30px;
		}

		.step-content {
			border: 1px solid #D4D4D4;
			border-top: 0;
			border-radius: 0 0 4px 4px;
			padding: 10px;
			margin-bottom: 10px;
		}

		fieldset.border {
			border: 1px solid #ddd !important;
			padding: 0 1.4em 1.4em 1.4em !important;
			margin: 0 0 1.5em 0 !important;
			-webkit-box-shadow: 0px 0px 0px 0px #ddd;
			box-shadow: 0px 0px 0px 0px #ddd;
			-webkit-border-radius: 5px;
			-moz-border-radius: 5px;
			border-radius: 5px;
		}

		legend.border {
			width: inherit; /* Or auto */
			padding: 0 10px; /* To give a bit of padding on the left and right */
			border-bottom: none;
			font-size: medium;
		}

		.demo-wrap {
			/* margin: 40px auto;*/
			display: block;
			position: relative;
			/* max-width:500px;*/
		}

		a {
			text-decoration: underline;
			color: #00F;
			cursor: pointer;
		}

		.seleccionado {
			background-color: #E0E0E0;
			color: black;
		}

		table {
			width: 100%;
		}

		select {
			width: 90%;
		}

		input[type="text"] {
			width: 90%;
		}

		.marca_modelo {
			width: 90%;
		}

		.no-close {
			display: none
		}

		.ui-dialog-titlebar {
			width: 0%;
		}

		.ui-dialog-titlebar-close {
			visibility: hidden;
		}

		a {
			text-decoration: none !important;
		}

		.col-md-3,.col-sm-3 {
			padding-left: 0px;
			padding-right: 0px;
		}

		.obligatoriosTarifacion {
			border-width: 1px;
			border-style: solid;
			border-color: #46b8da;
			background: #c3e4ff;
		}

		#tablaFinalVehiculos tr td {
			width: 10%;
			white-space: nowrap;
		}
		
		.fondo_botones {
			font-family:Helvetica Neue, Helvetica, Arial, sans-serif;
			font-weight:bold;
		}
		</style>
<script>
	var casoEspecial=false;
	var validar = 0;
  
	$(document).ready(function (){			  	
		<!--activarMenu("Cotizacion");--->
		
	});
	   
    
    function actualizarMarcas(){
		
    	tipoConsulta = "actualizarMarcasModelos";
    	$.ajax({
    		url : '../MantenimientoController',
    		data:{
    			"tipoConsulta" : tipoConsulta
    		},
			type : 'POST',
			datatype : 'json',
			success : function(data) {
				if(data.success)
				{
					var existenRegistro = false;
					$('#marcasCreadas').show();
					$('#dataTableMarcas').show();
					$('#dataTable_wrapperMarcas').show();	 					
					$('#dataTableContentMarcas').html('');
					$("#datos_temporal").val("");
					var listadoMarcas = 0;
					listadoMarcas = data.listadoMarcas; 
					var auxiliar = "";
					if(listadoMarcas.length > 1){
						$.each(listadoMarcas, function(index){
							
							var aux="	<tr class='odd gradeX'>" +
							" <td relation='marca'>"+ listadoMarcas[index].marca +"</td>" +
							" <td relation='modelo'>"+ listadoMarcas[index].modelo +"</td>" +
							" <td relation='clase'>"+ listadoMarcas[index].clase +"</td>" +
						"</tr>";
								auxiliar +=	aux;
							
						//$("#buscando").fadeOut("slow");	 					
						});	
						
						// Evitar Reinicializacion datatable
						var oTable = $('#dataTableMarcas').dataTable();
						oTable.fnDestroy();							
						$('#dataTableMarcas tbody').html(auxiliar);
						existenRegistro = true;
				}
				if(!existenRegistro){
						var oTable = $('#dataTableMarcas').dataTable();
						oTable.fnDestroy();							
						$('#dataTableMarcas tbody').html("<tr><td colspan='12'>No existen Registros</td></tr>");
						//$("#buscando").fadeOut("slow");
				}
				}	
				else{
					alert("Existen errores en la actualizacion")
				}
			}
    	});
    	
    }
    
function actualizarColores(){
		
    	tipoConsulta = "actualizarColores";
    	$.ajax({
    		url : '../MantenimientoController',
    		data:{
    			"tipoConsulta" : tipoConsulta
    		},
			type : 'POST',
			datatype : 'json',
			success : function(data) {
				if(data.success)
				{
					var existenRegistro = false;
					$('#coloresCreadas').show();
					$('#dataTableColores').show();
					$('#dataTable_wrapperColores').show();	 					
					$('#dataTableContentColores').html('');
					$("#datos_temporal").val("");
					var listadoColores = 0;
					listadoColores = data.listadoColores; 
					var auxiliar = "";
					if(listadoColores.length > 1){
						$.each(listadoColores, function(index){
							
							var aux="	<tr class='odd gradeX'>" +
							" <td relation='color'>"+ listadoColores[index].color +"</td>" +
						"</tr>";
								auxiliar +=	aux;
							
						//$("#buscando").fadeOut("slow");	 					
						});	
						
						// Evitar Reinicializacion datatable
						var oTable = $('#dataTableColores').dataTable();
						oTable.fnDestroy();							
						$('#dataTableColores tbody').html(auxiliar);
						existenRegistro = true;
				}
				if(!existenRegistro){
						var oTable = $('#dataTableColores').dataTable();
						oTable.fnDestroy();							
						$('#dataTableColores tbody').html("<tr><td colspan='12'>No existen Registros</td></tr>");
						//$("#buscando").fadeOut("slow");
				}
				}	
				else{
					alert("Existen errores en la actualizacion")
				}
			}
    	});
    	
    }
  
     </script>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">
			<b>Actualizar Marcas, Modelos</b>
		</div>
	<div  id = "marcasModelos"class="panel-body">
			<table>
				<tbody id="ActualizarMarcas">
					<tr>
						<td>
							<form id="formActualizarMarcasModelos">
								<button class='btn btn-primary btn-xs ' id="crearBtn" type="button" value="" onclick= "actualizarMarcas();">
								<span></span>Actualizar Marcas y Modelos &nbsp;
								</button>
							</form>
						</td>
					</tr>
				</tbody>
			</table>
			<br>
		<!-- Datatable -->
		<div id = "marcasCreadas" style="display: none;" class="row">
			<div class="col-lg-6">
				<div class="table-responsive">	
					<table class="table table-striped table-bordered table-hover" id="dataTableMarcas" style="font-size: x-small;">
						<thead>
							<tr>
								<th>Marca</th>
								<th>Modelo</th>
								<th>Clase Vehículo</th>
							</tr>
						</thead>
						<tbody id="dataTableContentMarcas" class="searchable" style="font-size: x-small;">
												
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- Datatable -->
	</div>
</div>
<!-- Actulializar Colores -->
	<div class="panel panel-default">
		<div class="panel-heading">
			<b>Actualizar Colores</b>
		</div>
	<div  id = "colores"class="panel-body">
			<table>
				<tbody id="ActualizarColores">
					<tr>
						<td>
							<form id="formActualizarColores">
								<button class='btn btn-primary btn-xs ' id="crearBtn" type="button" value="" onclick= "actualizarColores();">
								<span></span>Actualizar Colores &nbsp;
								</button>
							</form>
						</td>
					</tr>
				</tbody>
			</table>
			<br>
			<div id = "coloresCreadas" style="display: none;" class="row">
				<div class="col-lg-6">
					<div class="table-responsive">	
						<table class="table table-striped table-bordered table-hover" id="dataTableColores" style="font-size: x-small;">
							<thead>
								<tr>
									<th>Color</th>
								</tr>
							</thead>
							<tbody id="dataTableContentColores" class="searchable" style="font-size: x-small;">
							</tbody>
						</table>
					</div>
				</div>
		  </div>
	</div>
</div>
</body>
</html>