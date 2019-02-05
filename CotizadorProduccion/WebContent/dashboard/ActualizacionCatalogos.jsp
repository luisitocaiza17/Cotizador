<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Actualizacion Catálogos - CotizadorQBE</title>
	
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
			activarMenu("ActualizarCatalogo");
		});

		
		function actualizarMantenimientoCore(){
			var opcionListado = $('#listadoMantenimientoCore').val(); 
			if(opcionListado==''){
				alert('Seleccione una opcion para sincronizar');
				return false;
			}else{
				var tipoActualizacion ='';
				if(opcionListado=='1'){
					tipoActualizacion = "actualizarFirmasDigitales";
				}
				if(opcionListado=='2'){
					tipoActualizacion = "actualizarEntidadJr";
				}
				if(opcionListado=='3'){
					tipoActualizacion = "actualizarAutorizacionSri";
				}
				
				$("#actualizando").fadeIn("slow");
				$.ajax({
					url : '../MantenimientoController',
					data : {					
						"tipoConsulta" : tipoActualizacion
					},
					type : 'POST',
					datatype : 'json',
					success : function (data) {
					if(data.success)
					{	
						var existenRegistro = false;						
						$('#dataTableContentActualizacion').html('');						
						var resultado = "";
						resultado = data.resultadoActualizacion;						
						var auxiliar ="<tr class='odd gradeX'><td relation='resultadoActualizacion'>"+ resultado +"</td></tr>";
						// Evitar Reinicializacion datatable
						var oTable = $('#dataTableActualizacion').dataTable();
						oTable.fnDestroy();							
						$('#dataTableActualizacion tbody').html(auxiliar);
						existenRegistro = true;
						$('#actualizacionCatalogosTabla').show();
						$('#dataTableActualizacion').show();															
					}	
					else{
						alert("Existen errores en la actualizacion");
					}
					$("#actualizando").fadeOut("slow");
					}				
				});
			}
		}
		
		function actualizarMantenimientoVH(){
			var opcionListado = $('#listadoMantenimientoProductosVH').val(); 
			if(opcionListado==''){
				alert('Seleccione una opcion para sincronizar');
				return false;
			}else{
				var tipoActualizacion ='';
				if(opcionListado=='VH' || opcionListado=='VHM' || opcionListado=='VL' || opcionListado=='VP'){
					tipoActualizacion = "actualizarCatalogosVH";					
				}			
				$("#actualizando").fadeIn("slow");
				$.ajax({
					url : '../MantenimientoController',
					data : {					
						"tipoConsulta" : tipoActualizacion,
						"ramo" : opcionListado
					},
					type : 'POST',
					datatype : 'json',
					success : function (data) {
					if(data.success)
					{	
						var existenRegistro = false;						
						$('#dataTableContentActualizacion').html('');						
						var resultado = "";
						resultado = data.resultadoActualizacion;						
						var auxiliar ="<tr class='odd gradeX'><td relation='resultadoActualizacion'>"+ resultado +"</td></tr>";
						// Evitar Reinicializacion datatable
						var oTable = $('#dataTableActualizacion').dataTable();
						oTable.fnDestroy();							
						$('#dataTableActualizacion tbody').html(auxiliar);
						existenRegistro = true;
						$('#actualizacionCatalogosTabla').show();
						$('#dataTableActualizacion').show();															
					}	
					else{
						alert("Existen errores en la actualizacion");
					}
					$("#actualizando").fadeOut("slow");
					}				
				});
			}
		}
		
		
		function actualizarMantenimientoPymes(){
			var opcionListado = $('#listadoMantenimientoProductosPymes').val(); 
			if(opcionListado==''){
				alert('Seleccione una opcion para sincronizar');
				return false;
			}else{
				var tipoActualizacion ='';
				if(opcionListado=='EE' || opcionListado=='IN' || opcionListado=='MIC' || opcionListado=='RB' || opcionListado=='RC' || opcionListado=='RD' || opcionListado=='RM'){
					tipoActualizacion = "actualizarCatalogosPYMES";					
				}			
				$("#actualizando").fadeIn("slow");
				$.ajax({
					url : '../MantenimientoController',
					data : {					
						"tipoConsulta" : tipoActualizacion,
						"ramo" : opcionListado
					},
					type : 'POST',
					datatype : 'json',
					success : function (data) {
					if(data.success)
					{	
						var existenRegistro = false;						
						$('#dataTableContentActualizacion').html('');						
						var resultado = "";
						resultado = data.resultadoActualizacion;						
						var auxiliar ="<tr class='odd gradeX'><td relation='resultadoActualizacion'>"+ resultado +"</td></tr>";
						// Evitar Reinicializacion datatable
						var oTable = $('#dataTableActualizacion').dataTable();
						oTable.fnDestroy();							
						$('#dataTableActualizacion tbody').html(auxiliar);
						existenRegistro = true;
						$('#actualizacionCatalogosTabla').show();
						$('#dataTableActualizacion').show();															
					}	
					else{
						alert("Existen errores en la actualizacion");
					}
					$("#actualizando").fadeOut("slow");
					}				
				});
			}
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
	<br>
	<div class="row">
        <div class="col-md-6">
            <div class="panel panel-primary">
                <div class="panel-heading clickable">
                    <h3 class="panel-title">Sincronizacion de Catalogos Core (Ensurance - Cotizador)</h3>
                </div>
                <div class="panel-body">                   
                
                <table class="table table-condensed">
					<thead>
						<tr>
							<th>Descripcion</th>
							<th>Listado</th>
							<th>Accion</th>
					    </tr>
				    </thead>
	        		<tbody>		                
		                <tr>
		                    <td>Catalogos Core</td>
		                    <td>
		                    	<select id="listadoMantenimientoCore">
									<option value="">Seleccione una opcion</option>
									<option value="1">Firmas Digitales</option>
									<option value="2">Entidades Listas Negras </option>
									<option value="3">Autorizaciones SRI </option>
								</select>
							</td>				
		                    <td><button type="button" class="btn btn-primary"  onclick='actualizarMantenimientoCore()'>Actualizar</button></td>
		                </tr>		                		                
		                <tr id="actualizando" hidden="hidden" >
		                    <td><div ><span id="loading-msg"></span><img  src="../static/images/ajax-loader.gif" /></div></td>
		                    <td>Actualizando los datos, por favor espere...</td>
		                </tr>
	        		</tbody>
    			</table>
                </div>
        	</div>
        </div>
        <div class="col-md-6">
            <div class="panel panel-primary">
                <div class="panel-heading clickable">
                    <h3 class="panel-title">Sincronizacion de Catalogos Productos (Ensurance - Cotizador)</h3>
                </div>
                <div class="panel-body">
                
                
                <table class="table table-condensed">
					<thead>
						<tr>
							<th>Descripcion</th>
							<th>Listado</th>
							<th>Accion</th>
					    </tr>
				    </thead>
	        		<tbody>		                
		                <tr>
		                    <td>Catalogos Vehiculos</td>
		                    <td>
		                    	<select id="listadoMantenimientoProductosVH">
									<option value="">Seleccione una opcion</option>
									<!--  <option value="1">Marcas</option>-->
									<!--  <option value="2">Modelos </option>-->
									<option value="VH">Veh&iacute;culo - Sincronizaci&oacute;n   </option>
									<option value="VHM">Veh&iacute;culos Mas&iacute;vos  - Sincronizaci&oacute;n   </option>
									<option value="VL">Veh&iacute;culos Livianos Mas&iacute;vos  - Sincronizaci&oacute;n   </option>
									<option value="VP">Veh&iacute;culos Pesados Mas&iacute;vos  - Sincronizaci&oacute;n   </option>
								</select>
							</td>				
		                    <td><button type="button" class="btn btn-primary"  onclick='actualizarMantenimientoVH()'>Actualizar</button></td>
		                </tr>
		                <tr>
		                    <td>Catalogos PYMES</td>
		                    <td>
		                    	<select id="listadoMantenimientoProductosPymes">
									<option value="">Seleccione una opcion</option>		
									<option value="EE">Equipo Electr&oacute;nico - Sincronizaci&oacute;n  </option>
									<option value="IN">Incendio - Sincronizaci&oacute;n  </option>
									<option value="MIC">MultiRiesgo Industrial Comercial - Sincronizaci&oacute;n  </option>
									<option value="RB">Robo y Asalto - Sincronizaci&oacute;n  </option>
									<option value="RC">Responsabilidad Civil - Sincronizaci&oacute;n  </option>
									<option value="RD">Dinero y Valores - Sincronizaci&oacute;n  </option>
									<option value="RM">Rotura Maquinaria - Sincronizaci&oacute;n  </option>
								</select>
							</td>				
		                    <td><button type="button" class="btn btn-primary"  onclick='actualizarMantenimientoPymes()'>Actualizar</button></td>
		                </tr>
		                		                		                
		                <tr id="actualizando" hidden="hidden" >
		                    <td><div ><span id="loading-msg"></span><img  src="../static/images/ajax-loader.gif" /></div></td>
		                    <td>Actualizando los datos, por favor espere...</td>
		                </tr>
	        		</tbody>
    			</table>                                
                </div>
            </div>
        </div>
    </div>
    <!-- Datatable - Actualizacion Catalogos -->
	<div id = "actualizacionCatalogosTabla" style="display: none;" class="row">
		<div class="col-md-12">
			<div class="table-responsive">	
			<table class="table table-striped table-bordered table-hover" id="dataTableActualizacion" style="font-size: x-small;">
				<thead><tr><th>Resultado</th></tr></thead>
				<tbody id="dataTableContentActualizacion" class="searchable" style="font-size: x-small;"></tbody>
			</table>
			</div>
		</div>
	</div>

</body>
</html>