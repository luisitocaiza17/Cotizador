<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Cotizaciones Pendientes - CotizadorQBE</title>
	
	<!-- Core CSS - Include with every page -->
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	<link href="../static/css/loading.css" rel="stylesheet">
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="../static/js/util.js"></script>
    <!-- Para el Datepicker-->
    <link href="../static/css/datepicker.css" rel="stylesheet">
    <script src="../static/js/bootstrap-datepicker.js"></script>
	<script>
		var codigo = "";
		var producto_grupo  = "";
		var nombre = "";
		
		var nombre_comercial = "";
		var activo = "0";
		var plan_ensurance = "";
		var pro_ensurance = "";
		var nemotecnico = "";
		var tipoConsulta = "";
		var arrProductoGrupo = new Array();
		var arrCodigoProductoGrupo = new Object();
		
		$(document).ready(function() {
			activarMenu("CotizacionesPendientesGanadero");
		 	 $('#alert').hide();  
		
	       	 var f = new Date();
	    	 var inicioMes;
	    	 var finMes;
	    	 var mesFechaFinal;
	    	 var anioFechaFinal;
	    	 var diaFechaFinal;
	    	 
	    	 anioFechaFinal = f.getFullYear();
	    	 mesFechaFinal = f.getMonth()+1;
	    	 diaFechaFinal = f.getDate();

	    	 if (mesFechaFinal < 10) mesFechaFinal = "0"+ mesFechaFinal;
	    	 if (diaFechaFinal < 10) diaFechaFinal = "0"+ diaFechaFinal;
	    	 inicioMes = SumaRestaDiasFecha("restar", 15, f);
	    	 finMes = diaFechaFinal + "-"+mesFechaFinal+"-"+anioFechaFinal;	    	 
	    	 
	    	 $("#dp1").attr("value",inicioMes);
	    	 $("#dp2").attr("value",finMes);   			
			
			$.ajax({
				url : '../CotizacionController',
				data : {
					"fInicio" : inicioMes,
					"fFinal" : finMes,
					"codigoTipoObjeto": "7",
					"tipoConsulta" : "encontrarTipoObjetoPorAprobarCanal",
					"estadoConsulta" : estadoConsulta
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {					
					$("#loading").fadeOut();
					var numeroListas = data.numeroListas;
					var existenRegistro = false;
for (i=0; i<parseInt(numeroListas); i++){					
					var listadoCotizacion = ""
					if(i == 0)
						listadoCotizacion = data.listadoCotizacion0;

					if(i == 1)
						listadoCotizacion = data.listadoCotizacion1;

					if(i == 2)
						listadoCotizacion = data.listadoCotizacion2;

					if(i == 3)
						listadoCotizacion = data.listadoCotizacion3;
					
					if(listadoCotizacion.length > 0){
						$.each(listadoCotizacion, function(index){
							var prima_neta_total = 0;
							if(parseFloat(listadoCotizacion[index].prima_neta_total).toFixed(2) != "NaN")
								prima_neta_total = parseFloat(listadoCotizacion[index].prima_neta_total).toFixed(2);
							else
								prima_neta_total = "0.00";
							
							var aux="	<tr class='odd gradeX'>" +
							" <td relation='id'>"+ listadoCotizacion[index].codigo +"</td>" +
							" <td relation='punto_venta'>"+ listadoCotizacion[index].punto_venta +"</td>" +
							" <td relation='vigencia_poliza'>"+ listadoCotizacion[index].vigencia_poliza +"</td>" +
							" <td relation='cliente'>"+ listadoCotizacion[index].cliente +"</td>" +
							" <td relation='vendedor'>"+ listadoCotizacion[index].vendedor +"</td>" +
							" <td relation='agente'>"+listadoCotizacion[index].agente+"</td>" +
							" <td relation='producto'>"+ listadoCotizacion[index].producto +"</td>" +
							" <td relation='estado'>"+ listadoCotizacion[index].estado +"</td>" +
							" <td relation='tipo_objeto'>"+ listadoCotizacion[index].tipo_objeto +"</td>" +
							" <td relation='fecha_elaboracion'>"+ listadoCotizacion[index].fecha_elaboracion +"</td>" +
							" <td relation='por_comision'>"+ listadoCotizacion[index].por_comision +"</td>" +
							" <td relation='suma_total'>"+ parseFloat(listadoCotizacion[index].suma_total).toFixed(2) +"</td>" +
							" <td relation='prima_neta_total'>"+ prima_neta_total +"</td>" +
							" <td width='80px' align='center'>" +
								" <input type='hidden'  value='"+ listadoCotizacion[index].codigo +"'/>" +
								" <button type='button' class='btn btn-success btn-xs actualizar-btn'>" +
  									" <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
								" </button>" +
								" <button type='button' class='btn btn-danger btn-xs eliminar-btn'>" +
								  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span>&nbsp; Eliminar &nbsp;" +
								" </button>" +
							"</td>" +
						"</tr>";
								
							$("#dataTableContent").append(aux);
					
						});
						
						$(".actualizar-btn").bind({click: function(event) {
							var id=$(this).parent().parent().children().first().text();
							if($(this).parent().prev().prev().prev().prev().prev().text() == "Ganadero")
								window.location.href='CotizacionGanadero.jsp?id='+id;
						}
					});
					
						$(".eliminar-btn").bind({click: function() {
								var r = confirm("Seguro que desea eliminar la Cotización " + $(this).parent().parent().children().first().text());
								if (r == true){
									codigo = $(this).parent().children().first().val();
									tipoConsulta = "eliminar";
									enviarDatos(codigo, tipoConsulta);
							    	$(this).parent().parent().remove();
								}
							}
						});	
						/* Fin Controles Elminar Registro */
						existenRegistro = true;
					}
}
if(!existenRegistro)
	$("#dataTableContent").append("<tr><td colspan='12'>No existen Registros</td></tr>");
				}
						
			});
			
			function enviarDatos(codigoEnsurance, tipoConsulta){				
				$.ajax({
					url : '../CotizacionController',
					data : {
						"codigo" : codigo,
						"tipoConsulta" : tipoConsulta
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


//Para que funcione el Datepicker
		    		$(function(){

		    			var startDate = new Date();
		    			var endDate = new Date();
		    			$('#dp1').datepicker()
		    				.on('changeDate', function(ev){
		    					if (ev.date.valueOf() > endDate.valueOf()){
		    						$('#alert').show().find('strong').text('La Fecha Inicial no puede ser mayor que la Fecha Final');
		    						$("#ConsultaBtn").hide();
		    					} else {
		    						$('#alert').hide();
		    						$("#ConsultaBtn").show();
		    						startDate = new Date(ev.date);
		    						$('#startDate').text($('#dp1').data('date'));
		    					}
		    					$('#dp1').datepicker('hide');
		    				});
		    			$('#dp2').datepicker()
		    				.on('changeDate', function(ev){
		    					if (ev.date.valueOf() < startDate.valueOf()){
		    						$('#alert').show().find('strong').text('La Fecha Final no puede ser menor que la Fecha Inicial');
		    						$("#ConsultaBtn").hide();
		    					} else {
		    						$('#alert').hide();
		    						$("#ConsultaBtn").show();
		    						endDate = new Date(ev.date);
		    						$('#endDate').text($('#dp2').data('date'));
		    					}
		    					$('#dp2').datepicker('hide');
		    				});
		    		});
//Boton Consulta	
		    		function Consulta(){
		    			var fInicio = $("#dp1").val();
		    			var fFinal = $("#dp2").val();
		    			$("#dataTableContent").empty();
		    			$('#loading').show();
		    			enviarDatosFecha(fInicio, fFinal);
		    		}
					function enviarDatosFecha(fInicio, fFinal){				
						$.ajax({
							url : '../CotizacionController',
							data : {
								"fInicio" : fInicio,
								"fFinal" : fFinal,
								"codigoTipoObjeto": "2,4,5,6",
								"tipoConsulta" : "encontrarTipoObjeto"
							},
							type : 'POST',
							datatype : 'json',
							success : function(data) {					
								$("#loading").fadeOut();
								var numeroListas = data.numeroListas;
								var existenRegistro = false;
			for (i=0; i<parseInt(numeroListas); i++){					
								var listadoCotizacion = ""
								if(i == 0)
									listadoCotizacion = data.listadoCotizacion0;

								if(i == 1)
									listadoCotizacion = data.listadoCotizacion1;

								if(i == 2)
									listadoCotizacion = data.listadoCotizacion2;

								if(i == 3)
									listadoCotizacion = data.listadoCotizacion3;
								
								if(listadoCotizacion.length > 0){
									$.each(listadoCotizacion, function(index){
										var prima_neta_total = 0;
										if(parseFloat(listadoCotizacion[index].prima_neta_total).toFixed(2) != "NaN")
											prima_neta_total = parseFloat(listadoCotizacion[index].prima_neta_total).toFixed(2);
										else
											prima_neta_total = "0.00";
										
										var aux="	<tr class='odd gradeX'>" +
										" <td relation='id'>"+ listadoCotizacion[index].codigo +"</td>" +
										" <td relation='punto_venta'>"+ listadoCotizacion[index].punto_venta +"</td>" +
										" <td relation='vigencia_poliza'>"+ listadoCotizacion[index].vigencia_poliza +"</td>" +
										" <td relation='cliente'>"+ listadoCotizacion[index].cliente +"</td>" +
										" <td relation='vendedor'>"+ listadoCotizacion[index].vendedor +"</td>" +
										" <td relation='agente'>"+listadoCotizacion[index].agente+"</td>" +
										" <td relation='producto'>"+ listadoCotizacion[index].producto +"</td>" +
										" <td relation='estado'>"+ listadoCotizacion[index].estado +"</td>" +
										" <td relation='tipo_objeto'>"+ listadoCotizacion[index].tipo_objeto +"</td>" +
										" <td relation='fecha_elaboracion'>"+ listadoCotizacion[index].fecha_elaboracion +"</td>" +
										" <td relation='por_comision'>"+ listadoCotizacion[index].por_comision +"</td>" +
										" <td relation='suma_total'>"+ parseFloat(listadoCotizacion[index].suma_total).toFixed(2) +"</td>" +
										" <td relation='prima_neta_total'>"+ prima_neta_total +"</td>" +
										" <td width='80px' align='center'>" +
											" <input type='hidden'  value='"+ listadoCotizacion[index].codigo +"'/>" +
											" <button type='button' class='btn btn-success btn-xs actualizar-btn'>" +
			  									" <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
											" </button>" +
											" <button type='button' class='btn btn-danger btn-xs eliminar-btn'>" +
											  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span>&nbsp; Eliminar &nbsp;" +
											" </button>" +
										"</td>" +
									"</tr>";
											
										$("#dataTableContent").append(aux);
								
									});
									
									$(".actualizar-btn").bind({click: function(event) {
										var id=$(this).parent().parent().children().first().text();
										if($(this).parent().prev().prev().prev().prev().prev().text() == "Ganadero")
											window.location.href='CotizacionGanadero.jsp?id='+id;
									}
								});
								
									$(".eliminar-btn").bind({click: function() {
											var r = confirm("Seguro que desea eliminar la Cotización " + $(this).parent().parent().children().first().text());
											if (r == true){
												codigo = $(this).parent().children().first().val();
												tipoConsulta = "eliminar";
												enviarDatos(codigo, tipoConsulta);
										    	$(this).parent().parent().remove();
											}
										}
									});	
									/* Fin Controles Elminar Registro */
									existenRegistro = true;
								}
			}
			if(!existenRegistro)
				$("#dataTableContent").append("<tr><td colspan='12'>No existen Registros</td></tr>");
							}
									
						});
						
						function enviarDatos(codigoEnsurance, tipoConsulta){				
							$.ajax({
								url : '../CotizacionController',
								data : {
									"codigo" : codigo,
									"tipoConsulta" : tipoConsulta
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
		<div id="loading">
		<div class="loading-indicator">
			<img src="../static/images/ajax-loader.gif"/><br /><br />
			<span id="loading-msg">Cargando...</span>
		</div>					
	</div>
	<div class="well">
			<div class="alert alert-error" id="alert" style="display: block;">
				<strong>La Fecha Inicial no puede ser mayor que la Fecha Final</strong>
			  </div>
			<table class="table">
				<thead>
					<tr>
					<th>Fecha Inicial: <input type="text" data-date-format="dd-mm-yyyy" id="dp1"></th>
					<th>Fecha Final:   <input type="text" data-date-format="dd-mm-yyyy" id="dp2"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
				    <th><button class="btn btn-primary" id="ConsultaBtn" onclick = "Consulta();"> 
			         <span class="glyphicon glyphicon-search"></span> &nbsp; Consulta
	     			</button><th>
					</tr>
				</tbody>
			</table>
          </div>
	
		<!-- Button trigger modal -->

	
	
	<!-- Datatable -->
	<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">
				<div class="input-group"> <span class="input-group-addon">Filter</span>
				    <input id="filter" type="text" class="form-control" placeholder="Escriba la palabra a buscar...">
				</div>			
				<table class="table table-striped table-bordered table-hover"
					id="dataTable" style="font-size: x-small;">
					<thead>
						<tr>
							<th># Cot.</th>
							<th>Pto. Venta</th>
							<th>Vigencia</th>
							<th>Cliente</th>
							<th>Vendido por</th>
							<th>Agente</th>
							<th>Producto</th>
							<th>Estado</th>
							<th>Tipo Objeto</th>
							<th>Fecha Elaboracion</th>
							<th>% Comision</th>
							<th>Suma Asegurada Total</th>
							<th>Prima Neta Total</th>
							<th></th>
						</tr>
					</thead>
					<tbody id="dataTableContent" class="searchable" style="font-size: x-small;">
											
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- Datatable -->	
</body>
</html>