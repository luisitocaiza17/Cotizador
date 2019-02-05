<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Actualizacion Catálogos Pymes - CotizadorQBE</title>
	
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
			activarMenu("ActualizacionCatalogosPymes");
		});
			
		
		function actualizarMantenimientoPymes(){
			limpiarResultados();
			$("#actualizando").fadeIn("slow");
			$.ajax({
				url : '../MantenimientoController',
				data : {					
					"tipoConsulta" : "actualizarCatalogosPYMES"
				},
				type : 'POST',
				datatype : 'json',
				success : function (data) {					
					if(data.success)
					{
						var existenRegistro = false;						
						$('#dataTableContentActualizacionPymes').html('');						
						var resultado = "";
						resultado = data.resultadoActualizacion;						
						var auxiliar ="<tr class='odd gradeX'><td relation='resultadoActualizacion'>"+ resultado +"</td></tr>";
						// Evitar Reinicializacion datatable
						var oTable = $('#dataTableActualizacionPymes').dataTable();
						oTable.fnDestroy();							
						$('#dataTableActualizacionPymes tbody').html(auxiliar);
						existenRegistro = true;
						$('#actualizacionPymesCreadas').show();
						$('#dataTableActualizacionPymes').show();															
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
			$('#actualizacionPymesCreadas').hide();
			// Limpiamos las tablas			
			$('#dataTableContentActualizacionPymes').html('');
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
                    <h3 class="panel-title">Catálogos Pymes</h3>
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
		                    <td>Productos, ConfiguracionProducto, DetalleProducto, Planes, Coberturas, Grupocoberturas, ConjuntoCoberturas, Deducibles</td>
		                    <td><button type="button" class="btn btn-primary" id="actualizar_mantenimientoPymes" onclick='actualizarMantenimientoPymes()'>Actualizar</button></td>
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
                		  		
		  		<!-- Datatable - ActualizacionVH -->
				<div id = "actualizacionPymesCreadas" style="display: none;" class="row">
					<div class="col-lg-6">
						<div class="table-responsive">	
							<table class="table table-striped table-bordered table-hover" id="dataTableActualizacionPymes" style="font-size: x-small;">
								<thead><tr><th>Resultado</th></tr></thead>
								<tbody id="dataTableContentActualizacionPymes" class="searchable" style="font-size: x-small;"></tbody>
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