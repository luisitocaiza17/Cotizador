<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-control" content="no-cache">
<title>Cotizaciones Agr&iacute;cola Resumen- CotizadorQBE</title>
<!-- <script src="../static/js/jquery.min.js"></script> -->
<script src="../static/js/cotizador/comun.js"></script>
<link href="../static/css/loading.css" rel="stylesheet">


<!-- Core CSS - Include with every page -->
<link
	href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">

<script
	src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
<script
	src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>

<script src="../static/js/sb-admin/plugins/dataTables/jquery.numeric.js"></script>
<script src="../static/js/util.js"></script>

<!-- KENDO -->
<link rel="stylesheet" href="../static/css/Kendo/kendo.common.min.css" />
<link rel="stylesheet" href="../static/css/Kendo/kendo.blueopal.min.css" />
<script src="../static/js/Kendo/kendo.all.min.js"></script>
<script src="../static/js/Kendo/kendo.web.min.js"></script>
<script src="../static/js/Kendo/jszip.min.js"></script>

<!-- Table Tools -->
<script
	src="../static/js/sb-admin/plugins/dataTables/dataTables.tableTools.js"></script>
<link
	href="../static/css/sb-admin/plugins/dataTables/dataTables.tableTools.css"
	rel="stylesheet">

<!-- Para el Datepicker-->
<link href="../static/css/datepicker.css" rel="stylesheet">
<script src="../static/js/bootstrap-datepicker.js"></script>

<link rel="stylesheet" href="../static/css/select2/select2.css">

<!--<script id="tipoObjetoCargaInicial" src="../static/js/pymes/carga.inicial.cotizador.pymes.js" tipoObjetoValor="Agricola"></script>-->
<!-- <script id="tipoObjetoMetodos" src="../static/js/pymes/metodos.pymes.js" tipoObjetoValor="Agricola"></script>
	<script src="../static/js/cotizador/comun.js"></script>
	 -->

<script>
	function GetQueryStringByParameter(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	    results = regex.exec(location.search);
	    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	var canalId=GetQueryStringByParameter('Canal');
	var codigo = "";
	var tipoConsulta = "";
	var Reglas = "";
	var facturaId="";
	var estadoList = "";
	var canalList = "";
	var puntoVentaList = "";
	var agenteList = "";
	
	$(document).ready(function() {
			activarMenu("AgriCotizacionMR");
			
			$("#loading").hide();
			
			
    	 	$("#sucursal_canal").kendoMultiSelect({
				dataTextField : "nombre",
				dataValueField : "id",
				animation : false,
				maxSelectedItems : 1
			});
    	 	
    	 	canalList = $("#sucursal_canal").data("kendoMultiSelect");
    	 	    	 				
			$("#punto_venta_session").kendoMultiSelect({
				dataTextField : "nombre",
				dataValueField : "puntoVentaId",
				animation : false,
				maxSelectedItems : 1
			});
			
			puntoVentaList = $("#punto_venta_session").data("kendoMultiSelect");
			
			  	 
			
    	 	$("#ConsultaBtn").click(function(){
    	 		$('#ConsultaBtn').prop('disabled', true);
    	 		$("#buscando").fadeIn("slow");
    	 		var reporteEndoso=$("#R_E_si").is(":checked") ? "SI" : "NO";
				if(reporteEndoso==="SI"){
					EliminarTramitesDuplicados();
				}else{
					EliminarPolizasDuplicados();
				}
    	 		
    	 	});
    	 	
			obtenerCanal();
			
			$("#sucursal_canal").change(function(){
				if($("#sucursal_canal option:selected").val()!="" && typeof $("#sucursal_canal option:selected").val() != "undefined")
					CargarCombo($("#sucursal_canal option:selected").val());
			});		
						
			$('#LimpiarBtn').click(function(){	    	 	
	    	 	$("#nro_tramite").val("");
	    	 	puntoVentaList.value("");
	    	 	canalList.value("");	    	 	
	    	 });
			
	});
			
	function CargarCombo(sucursal_canal) {
		var sucursal_canal_Id = "";
		if (sucursal_canal != null && sucursal_canal != '')
			sucursal_canal_Id = sucursal_canal;
		else
			sucursal_canal_Id = $('#sucursal_canal').val();
		$.ajax({
			url : '../AgriCotizacionAprobacionController',
			data : {
				"tipoConsulta" : "cargarCombos",
				"canalId" : sucursal_canal_Id,
			},
			async : false,
			type : 'post',
			datatype : 'json',
			success : function(data) {
				puntoVentaList.dataSource.filter({});
				puntoVentaList.setDataSource(data.listPuntoVenta);				
			}
		});	
	}
	
		
	function obtenerCanal() {		
		$.ajax({
			url : '../AgriCanalController',
			data : {
				"tipoConsulta" : "encontrarPorCanalId",
				"canalId" : "0"
			},
			async : false,
			type : 'post',
			datatype : 'json',
			success : function(data) {
				//var listadoCanal = data.canalesJSONArray;				
				canalList.dataSource.filter({});
				canalList.setDataSource(data.canalesJSONArray);
			}
		});
	}	
	function EliminarTramitesDuplicados() {	
		var canal = $('#sucursal_canal option:selected').val();
		var puntoVenta = $('#punto_venta_session option:selected').val();
		if (canal == null || canal == ''){
			alert("se debe seleccionar al menos un canal");
			$('#ConsultaBtn').prop('disabled', false);
			$("#buscando").fadeOut("slow");
			return false;
		}
			
		$.ajax({
			url : '../AgriCotizacionMRController',
			data : {
				"tipoConsulta" : "EliminarDuplicadosTramites",
				"canalId" : canal,
				"puntoVenta":puntoVenta
			},
			async : false,
			type : 'post',
			datatype : 'json',
			success : function(data) {
				if(data.success==true){
					alert("El proceso termino con éxito para evitar sobrecargar se procesaron "+ data.actual+ "de "+data.total+" faltan por procesar "+data.faltante);
					$('#ConsultaBtn').prop('disabled', false);
					$("#buscando").fadeOut("slow");
				}
				else{
					alert("El problema detecto un problema, el proceso no pudo culminar");
					$('#ConsultaBtn').prop('disabled', false);
					$("#buscando").fadeOut("slow");
				}
			}
		});

	}
	function EliminarPolizasDuplicados() {	
			
		$.ajax({
			url : '../AgriCotizacionMRController',
			data : {
				"tipoConsulta" : "EliminarDuplicadosPolizas",
				"canalId" : "",
				"puntoVenta":""
			},
			async : false,
			type : 'post',
			datatype : 'json',
			success : function(data) {
				if(data.success==true){
					alert("El proceso termino con éxito");
					$('#ConsultaBtn').prop('disabled', false);
					$("#buscando").fadeOut("slow");
				}
				else{
					alert("El problema detecto un problema, el proceso no pudo culminar");
					$('#ConsultaBtn').prop('disabled', false);
					$("#buscando").fadeOut("slow");
				}
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
	<!-- ventana consulta -->
	<div class="row crud-nav-bar">
		<div class="well">
			<table class="table" style="width: 100%">
				<thead>
					<tr>
						<td colspan="6" style="font-weight: bold;" align="center">Buscador Resumen de Cotizaciones.</td>
					</tr>
					
					<tr>
						<th style="width: 180px">Busqueda por Canal:</th>						
						<th style="width: 100px">Sponsor: *</th>
						<th style="width: 300px"><select id="sucursal_canal" data-placeholder="Seleccione una opción..."></select></th>
						<th style="width: 100px">Canal:</th>
						<th style="width: 300px"><select id="punto_venta_session" data-placeholder="Seleccione una opción..." ></select></th>
					</tr>
					<tr>
						<th style="width: 180px">Proceso:</th>						
						<th style="width: 100px">Tipo de Acción: *</th>
						<th style="width: 300px">
							<input type="radio" name="reporte" value="1" id="R_E_si" checked> Eliminar Tramites Repetidos<br>
							<input type="radio" name="reporte" value="2" id="R_E_no">Eliminar polizas repetidas<br>
						</th>
						<th style="width: 100px"></th>
						<th style="width: 300px"></th>
					</tr>										
				</thead>
				<tbody>
					<tr>
						<th>
						</th>
						<th>

							<button class="btn btn-primary" id="ConsultaBtn">
								<span class="glyphicon glyphicon-search"></span> &nbsp; Quitar tramites Duplicados
							</button>
						</th>
						<th>
							<button class="btn btn-primary" id="LimpiarBtn">
								<span class="glyphicon glyphicon-trash"></span> &nbsp; Limpiar
							</button>
						</th>						
					</tr>					
				</tbody>
			</table>
		</div>
		<!-- Button trigger modal -->
	</div>
	<div id="buscando" hidden="hidden">
		<div style="margin-top: 10px;">
			<span id="loading-msg"></span><img
				src="../static/images/ajax-loader.gif" /> Realizando Proceso, por favor espere...
		</div>
	</div>
	
	
</body>
</html>