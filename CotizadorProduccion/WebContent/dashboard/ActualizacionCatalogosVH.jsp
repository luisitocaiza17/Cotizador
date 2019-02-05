<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Actualizacion Catálogos Vehículos - CotizadorQBE</title>
	
	<!-- Core CSS - Include with every page -->
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	<link href="../static/css/loading.css" rel="stylesheet">
	<style type="text/css">
	
		.clickable
		{
		    cursor: pointer;
		}
		
		.clickable .glyphicon
		{
		    background: rgba(0, 0, 0, 0.15);
		    display: inline-block;
		    padding: 6px 12px;
		    border-radius: 4px
		}
		
		.panel-heading span
		{
		    margin-top: -23px;
		    font-size: 15px;
		    margin-right: -9px;
		}
		a.clickable { color: inherit; }
		a.clickable:hover { text-decoration:none; }
	</style>

	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="../static/js/jquery-ui/jquery-ui.js"></script>
	<link rel="stylesheet" type="text/css" href="../static/css/jquery-ui.theme.css" />
	<link href="../static/css/loading.css" rel="stylesheet">
	<script src="../static/js/util.js"></script>
	<script>

		$(document).ready(function() {
			activarMenu("ActualizacionCatalogosVH");
		});
		
		
		function actualizarMarcas(){
			limpiarResultados();
			$("#actualizando").fadeIn("slow");				    	
	    	$.ajax({
	    		url : '../MantenimientoController',
	    		data:{
	    			"tipoConsulta" : "actualizarMarcasModelos"
	    		},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					if(data.success)
					{
						var existenRegistro = false;											
						var listadoMarcas = 0;
						listadoMarcas = data.listadoMarcas; 
						var auxiliar = "";
						if(listadoMarcas.length >= 1){
							$.each(listadoMarcas, function(index){								
								var aux="<tr class='odd gradeX'>" +
								" <td relation='marca'>"+ listadoMarcas[index].marca +"</td>" +
								" <td relation='modelo'>"+ listadoMarcas[index].modelo +"</td>" +
								" <td relation='clase'>"+ listadoMarcas[index].clase +"</td>" +
								"</tr>";
									auxiliar +=	aux;																 				
							});	
							
							// Evitar Reinicializacion datatable
							var oTable = $('#dataTableMarcas').dataTable();
							oTable.fnDestroy();							
							$('#dataTableMarcas tbody').html(auxiliar);
							existenRegistro = true;
							$('#marcasCreadas').show();
							$('#dataTableMarcas').show();							 											
					}
					if(!existenRegistro){
							var oTable = $('#dataTableMarcas').dataTable();
							oTable.fnDestroy();							
							$('#dataTableMarcas tbody').html("<tr><td colspan='12'>No existen Registros</td></tr>");
							$('#marcasCreadas').show();
							$('#dataTableMarcas').show();
					}
					}	
					else{
						alert("Existen errores en la actualizacion")
					}
					$("#actualizando").fadeOut("slow");
				}
	    	});
	    	
	    }
		
		function actualizarColores(){
			limpiarResultados();
			$("#actualizando").fadeIn("slow");	    	
	    	$.ajax({
	    		url : '../MantenimientoController',
	    		data:{
	    			"tipoConsulta" : "actualizarColores"
	    		},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					if(data.success)
					{
						var existenRegistro = false;											
						var listadoColores = 0;
						listadoColores = data.listadoColor; 
						var auxiliar = "";
						if(listadoColores.length >= 1){
							$.each(listadoColores, function(index){								
								var aux="<tr class='odd gradeX'><td relation='color'>"+ listadoColores[index].color +"</td></tr>";
									auxiliar +=	aux;														
							});								
							// Evitar Reinicializacion datatable
							var oTable = $('#dataTableColores').dataTable();
							oTable.fnDestroy();							
							$('#dataTableColores tbody').html(auxiliar);
							existenRegistro = true;
							$('#coloresCreadas').show();
							$('#dataTableColores').show();																	
					}
					if(!existenRegistro){
							var oTable = $('#dataTableColores').dataTable();
							oTable.fnDestroy();											
							$('#dataTableColores tbody').html("<tr><td colspan='12'>No existen Registros</td></tr>");
							$('#coloresCreadas').show();
							$('#dataTableColores').show();
					}
					}	
					else{
						alert("Existen errores en la actualizacion")
					}
					$("#actualizando").fadeOut("slow");
				}
	    	});
	    	
	    }
		
		function actualizarTipoExtras(){
			limpiarResultados();						
			$("#actualizando").fadeIn("slow");
			$.ajax({
				url : '../MantenimientoController',
				data : {					
					"tipoConsulta" : "actualizarTipoExtras"
				},
				type : 'POST',
				datatype : 'json',
				success : function (data) {
					if(data.success)
					{
						var existenRegistro = false;						
						$('#dataTableContentExtras').html('');						
						var listadoExtras = 0;
						listadoExtras = data.listadoExtras; 
						var auxiliar = "";
						if(listadoExtras.length >= 1){
							$.each(listadoExtras, function(index){								
								var aux="<tr class='odd gradeX'><td relation='extra'>"+ listadoExtras[index].extra +"</td></tr>";
									auxiliar +=	aux;																 				
							});	
							
							// Evitar Reinicializacion datatable
							var oTable = $('#dataTableExtras').dataTable();
							oTable.fnDestroy();							
							$('#dataTableExtras tbody').html(auxiliar);
							existenRegistro = true;
							$('#extrasCreadas').show();
							$('#dataTableExtras').show();							
					}
					if(!existenRegistro){							
							$('#extrasCreadas').show();
							$('#dataTableExtras').show();									
							$('#dataTableContentExtras').html('');
							var oTable = $('#dataTableExtras').dataTable();
							oTable.fnDestroy();							
							$('#dataTableExtras tbody').html("<tr><td colspan='12'>No existen Registros</td></tr>");							
					}					
					}	
					else{
						alert("Existen errores en la actualizacion")
					}
					$("#actualizando").fadeOut("slow");
				}				
			});
		}		
		
		function actualizarMantenimientoVH(){
			limpiarResultados();
			$("#actualizando").fadeIn("slow");
			$.ajax({
				url : '../MantenimientoController',
				data : {					
					"tipoConsulta" : "actualizarCatalogosVH"
				},
				type : 'POST',
				datatype : 'json',
				success : function (data) {					
					if(data.success)
					{
						var existenRegistro = false;						
						$('#dataTableContentActualizacionVH').html('');						
						var resultado = "";
						resultado = data.resultadoActualizacion;						
						var auxiliar ="<tr class='odd gradeX'><td relation='resultadoActualizacion'>"+ resultado +"</td></tr>";
						// Evitar Reinicializacion datatable
						var oTable = $('#dataTableActualizacionVH').dataTable();
						oTable.fnDestroy();							
						$('#dataTableActualizacionVH tbody').html(auxiliar);
						existenRegistro = true;
						$('#actualizacionVHCreadas').show();
						$('#dataTableActualizacionVH').show();															
					}	
					else{
						alert("Existen errores en la actualizacion")
					}
					$("#actualizando").fadeOut("slow");
				}				
			});
		}

		function limpiarResultados(){
			// ocultamos las tablas
			$('#extrasCreadas').hide();
			$('#coloresCreadas').hide();
			$('#marcasCreadas').hide();
			$('#actualizacionVHCreadas').hide();
			// Limpiamos las tablas
			$('#dataTableContentExtras').html('');
			$('#dataTableContentMarcas').html('');
			$('#dataTableContentColores').html('');
			$('#dataTableContentActualizacionVH').html('');
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

	<div class="row">
        <div class="col-md-6">
            <div class="panel panel-primary">
                <div class="panel-heading clickable">
                    <h3 class="panel-title">Catálogos VH</h3>
                </div>
                <div class="panel-body">                   
                
                <table class="table table-condensed">
					<thead>
						<tr>
							<th>Descripción</th>
							<th>Acción</th>
					    </tr>
				    </thead>
	        		<tbody>
		                <tr>
		                    <td>Marcas y Modelos(Solo del día en que se ejecuta)</td>
		                    <td><button type="button" class="btn btn-primary" id="actualizar_marcas_modelos" onclick='actualizarMarcas()'>Actualizar</button></td>
		                </tr>
		                <tr>
		                    <td>Colores(Solo del día en que se ejecuta)</td>
		                    <td><button type="button" class="btn btn-primary" id="actualizar_colores" onclick='actualizarColores()'>Actualizar</button></td>
		                </tr>
		                <tr>
		                    <td>Tipos de Extras</td>
		                    <td><button type="button" class="btn btn-primary" id="actualizar_tipoExtras" onclick='actualizarTipoExtras()'>Actualizar</button></td>
		                </tr>
		                <tr>
		                    <td>Productos, ConfiguracionProducto, DetalleProducto, Planes, Coberturas, Grupocoberturas, ConjuntoCoberturas, Deducibles</td>
		                    <td><button type="button" class="btn btn-primary" id="actualizar_mantenimientoVH" onclick='actualizarMantenimientoVH()'>Actualizar</button></td>
		                </tr>		                		                
	        		</tbody>
    			</table>
                </div>
        	</div>
        </div>        
    </div>
	<div class="row">
	<div class="col-md-12">
            <div class="panel panel-primary">
                <div class="panel-heading clickable">
                    <h3 class="panel-title">Resultados</h3>
                </div>
                <div class="panel-body">
                <div id="actualizando" hidden="hidden" >
		                    <div ><span id="loading-msg"></span><img  src="../static/images/ajax-loader.gif" /></div>
		                    <div>Actualizando los datos, por favor espere...</div>
		        </div>
		        <br>		                 
                <!-- Datatable - Marcas y Modelos -->
				<div id = "marcasCreadas" style="display: none;" class="row">
					<div class="col-lg-6">
						<div class="table-responsive">	
							<table class="table table-striped table-bordered table-hover" id="dataTableMarcas" style="font-size: x-small;">
								<thead><tr><th>Marca</th><th>Modelo</th><th>Clase Vehículo</th></tr></thead>
								<tbody id="dataTableContentMarcas" class="searchable" style="font-size: x-small;"></tbody>						
							</table>
						</div>
					</div>
				</div>
				<!-- Datatable - Colores -->
				<div id = "coloresCreadas" style="display: none;" class="row">
					<div class="col-lg-6">
						<div class="table-responsive">	
							<table class="table table-striped table-bordered table-hover" id="dataTableColores" style="font-size: x-small;">
								<thead><tr><th>Color</th></tr></thead>
								<tbody id="dataTableContentColores" class="searchable" style="font-size: x-small;"></tbody>
							</table>
						</div>
					</div>
		  		</div>
		  		<!-- Datatable - Tipo Extras -->
				<div id = "extrasCreadas" style="display: none;" class="row">
					<div class="col-lg-6">
						<div class="table-responsive">	
							<table class="table table-striped table-bordered table-hover" id="dataTableExtras" style="font-size: x-small;">
								<thead><tr><th>Tipo Extra</th></tr></thead>
								<tbody id="dataTableContentExtras" class="searchable" style="font-size: x-small;"></tbody>
							</table>
						</div>
					</div>
		  		</div>		  		
		  		<!-- Datatable - ActualizacionVH -->
				<div id = "actualizacionVHCreadas" style="display: none;" class="row">
					<div class="col-lg-6">
						<div class="table-responsive">	
							<table class="table table-striped table-bordered table-hover" id="dataTableActualizacionVH" style="font-size: x-small;">
								<thead><tr><th>Resultado</th></tr></thead>
								<tbody id="dataTableContentActualizacionVH" class="searchable" style="font-size: x-small;"></tbody>
							</table>
						</div>
					</div>
		  		</div>
		  						
                </div>
            </div>
     </div>           
	</div>
</body>
</html>