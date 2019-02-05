<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Cache-control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Cotizaciones Pendientes - CotizadorQBE</title>
	
	<!-- Core CSS - Include with every page -->
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	
	<!-- Table Tools -->
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.tableTools.js"></script>
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.tableTools.css" rel="stylesheet">
	
	<script id="tipoObjetoCargaInicial" src="../static/js/pymes/carga.inicial.cotizador.pymes.js" tipoObjetoValor="PYMES"></script>
	<script id="tipoObjetoMetodos" src="../static/js/pymes/metodos.pymes.js" tipoObjetoValor="PYMES"></script>
	<script src="../static/js/cotizador/comun.js"></script>
	
	<script src="../static/js/util.js"></script>
    <!-- Para el Datepicker-->
    <link href="../static/css/datepicker.css" rel="stylesheet">
    <script src="../static/js/bootstrap-datepicker.js"></script>
    
    <!-- KENDO -->
	<link rel="stylesheet" href="../static/css/Kendo/kendo.common.min.css" />
	<link rel="stylesheet" href="../static/css/Kendo/kendo.blueopal.min.css" />
	<script src="../static/js/Kendo/kendo.all.min.js"></script>
	<script src="../static/js/Kendo/kendo.web.min.js"></script>
    
	<script>
		function GetQueryStringByParameter(name) {
	       name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	       var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	       results = regex.exec(location.search);
	       return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	   	}
	
		var estadoConsulta=GetQueryStringByParameter('State');
		var notification;
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
			activarMenu("PymesLogCotizaciones");		 	
			 $('#dataTable').hide();
			 $('#dataTableRamos').hide();
	    	 // Carga de agentes y puntos de venta
	    	 /*Mensaje de error*/
						notification = $("#notification").kendoNotification({
                        position: {
                            pinned: true,
                            top: 90,
                            right: 35
                        },
                        autoHideAfter: 10000,
                        hideOnClick: false,
                        stacking: "down",
                        templates: [ {
                            type: "error",
                            template: $("#errorTemplate").html()
                    		}]
                        }).data("kendoNotification");
					
					 $(document).one("kendo:pageUnload", function(){ });
	    	 cargarAgentes("","");
	    	 cargarPuntosVentaSessionPymes();
	    	 
	    	 
	    	 $('#LimpiarBtn').click(function(){	    	 	
	    	 	$("#cotizacion_id").val("");
	    	 });
	    	 
	    	 $('#ConsultaBtn').click(function(){
	    		$('#dataTable').hide();
	    		$('#dataTableRamos').hide();
	 			$('#dataTable_wrapper').hide();	 			
	 				 			
	    		 $("#buscando").fadeIn("slow");

	    		 // Validaciones de las busquedas	    		 
	    		 var cotizacionId = $("#cotizacion_id").val();
	    		 
	    		 if(cotizacionId==""){
	    		 	alert("Ingrese el numero de cotizacion");
	    		 	$("#buscando").fadeOut("slow");
	    		 	return false;
	    		 }
	    		 
	    		 $.ajax({
	 				url : '../PymeCotizacionController',
	 				data : {
	 					"cotizacionId" : cotizacionId ,
	 					"tipoConsulta" : "encontrarLogCotizacion"
	 				},
	 				type : 'POST',
	 				datatype : 'json',
	 				success : function(data) {
	 					if (data.success == false){
	 						 notification.show({
	 	                           title: "ERROR",
	 	                           message: data.error
	 	                       }, "error");							
	 						return;
	 					}
	 					var existenRegistro = false;
	 					$('#dataTable').show();
	 					$('#dataTableRamos').show();
	 					$('#dataTableDetalles').show();
	 					$('#dataTable_wrapper').show();	 					
	 					$('#dataTableContent').html('');
	 					$('#dataTableContentRamos').html('');
	 					$('#dataTableContentDetalles').html('');
	 					$("#datos_temporal").val("");
	 					
	 					var listadoCotizacion = 0;
	 					listadoCotizacion = data.listadoCotizacionCoberturaValor;
	 					var auxiliar = "";
	 					if(listadoCotizacion.length > 0){
	 						$.each(listadoCotizacion, function(index){
	 							var aux="	<tr class='odd gradeX'>" +
	 							" <td relation='GrupoProductoId'>"+ listadoCotizacion[index].GrupoProductoId +"</td>" +
	 							" <td relation='RamoId'>"+ listadoCotizacion[index].RamoId +"</td>" +
	 							" <td relation='CoberturaEnsMultiId'>"+ listadoCotizacion[index].CoberturaEnsMultiId +"</td>" +
	 							" <td relation='CoberturaEnsProgrId'>"+ listadoCotizacion[index].CoberturaEnsProgrId +"</td>" +
	 							" <td relation='Nombre'>"+ listadoCotizacion[index].Nombre +"</td>" +
	 							" <td relation='TasaSugerida'>"+listadoCotizacion[index].TasaSugerida+"</td>" +
	 							" <td relation='TasaIngresada'>"+ listadoCotizacion[index].TasaIngresada +"</td>" +
	 							" <td relation='ValorIngresado'>"+ listadoCotizacion[index].ValorIngresado +"</td>" +
	 							" <td relation='PrimaCalculada'>"+ listadoCotizacion[index].PrimaCalculada +"</td>" +
	 							" <td relation='CotizacionId'>"+ listadoCotizacion[index].CotizacionId +"</td>" +
	 							" <td relation='ObjetoPymesCoberturaId'>"+ listadoCotizacion[index].ObjetoPymesCoberturaId +"</td>" +
	 							" <td relation='CotizacionDetalleId'>"+ listadoCotizacion[index].CotizacionDetalleId +"</td>" +
	 							" <td relation='CoberturaPymesId'>"+ listadoCotizacion[index].CoberturaPymesId +"</td>" +
	 							" <td relation='TipoOrigen'>"+ listadoCotizacion[index].TipoOrigen +"</td>" +
	 							" <td relation='ValorMaximoLimiteAsegurado'>"+ listadoCotizacion[index].ValorMaximoLimiteAsegurado +"</td>" +
	 							" <td relation='PorcentajeLimiteAsegurado'>"+ listadoCotizacion[index].PorcentajeLimiteAsegurado +"</td>" +
	 							" <td relation='TipoCoberturaId'>"+ listadoCotizacion[index].TipoCoberturaId +"</td>" +
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
            					},
    						});
	 						existenRegistro = true;
	 					}
	 					
	 					//RAMOS
	 					var listadoRamo = 0;
	 					listadoRamo = data.listadoRamosCotizacion;
	 					var auxiliarRamo = "";
	 					if(listadoRamo.length > 0){
	 						$.each(listadoRamo, function(index){
	 							var aux="	<tr class='odd gradeX'>" +
	 							" <td relation='RamoId'>"+ listadoRamo[index].RamoId +"</td>" +
	 							" <td relation='CotizacionId'>"+ listadoRamo[index].CotizacionId +"</td>" +
	 							" <td relation='PrimaCalculada'>"+ listadoRamo[index].PrimaCalculada +"</td>" +
	 							" <td relation='SumaAsegurada'>"+ listadoRamo[index].SumaAsegurada +"</td>" +
	 							" <td relation='NombreRamo'>"+ listadoRamo[index].NombreRamo +"</td>" +
	 							"</tr>";
	 							auxiliarRamo +=	aux;
	 							$("#buscando").fadeOut("slow");	 					
	 						});	
							// Evitar Reinicializacion datatable
	 						var oTable = $('#dataTableRamos').dataTable();
							oTable.fnDestroy();							
							$('#dataTableRamos tbody').html(auxiliarRamo);
							
	 						$('#dataTableRamos').dataTable( {	 							
        						"pagingType": "full",
        						"bFilter": true,
        						"bLengthChange": false,
        						"bSort" : false,
        						"iDisplayLength": 10, // Limitamos el numero de filas en la paginacion
        						"dom": 'T<"clear">lfrtip',
        						"tableTools": {
            						"sSwfPath": "/CotizadorWeb/static/js/sb-admin/plugins/dataTables/swf/copy_csv_xls.swf",
            					},
    						});
	 						existenRegistro = true;
	 					}
	 					if(!existenRegistro){
	 						var oTable = $('#dataTableRamos').dataTable();
							oTable.fnDestroy();							
							$('#dataTableRamos tbody').html("<tr><td colspan='12'>No existen Registros</td></tr>");
	 						$("#buscando").fadeOut("slow");
	 					}
	 					
	 					//GRID DETALLES
	 					var listadoCotizacionDetalles = 0;
	 					listadoCotizacionDetalles = data.listadoCotizacionDetalle;
	 					var auxiliarDetalles = "";
	 					if(listadoCotizacionDetalles.length > 0){
	 						$.each(listadoCotizacionDetalles, function(index){
	 							var aux="	<tr class='odd gradeX'>" +
	 							" <td relation='ObjetoPymesId'>"+ listadoCotizacionDetalles[index].ObjetoPymesId +"</td>" +
	 							" <td relation='CallePrincipal'>"+ listadoCotizacionDetalles[index].CallePrincipal +"</td>" +
	 							" <td relation='NumeroDireccion'>"+ listadoCotizacionDetalles[index].NumeroDireccion +"</td>" +
	 							" <td relation='CalleSecundaria'>"+ listadoCotizacionDetalles[index].CalleSecundaria +"</td>" +
	 							" <td relation='NombreEdificio'>"+ listadoCotizacionDetalles[index].NombreEdificio +"</td>" +
	 							" <td relation='NumeroPiso'>"+listadoCotizacionDetalles[index].NumeroPiso+"</td>" +
	 							" <td relation='NumeroOficina'>"+ listadoCotizacionDetalles[index].NumeroOficina +"</td>" +
	 							" <td relation='Sector'>"+ listadoCotizacionDetalles[index].Sector +"</td>" +
	 							" <td relation='ProvinciaId'>"+ listadoCotizacionDetalles[index].ProvinciaId +"</td>" +
	 							" <td relation='CiudadId'>"+ listadoCotizacionDetalles[index].CiudadId +"</td>" +
	 							" <td relation='ActividadEconomicaId'>"+ listadoCotizacionDetalles[index].ActividadEconomicaId +"</td>" +
	 							" <td relation='ContabilidadFormal'>"+ listadoCotizacionDetalles[index].ContabilidadFormal +"</td>" +
	 							" <td relation='TieneMasDosAnio'>"+ listadoCotizacionDetalles[index].TieneMasDosAnio +"</td>" +
	 							" <td relation='TipoConstruccionId'>"+ listadoCotizacionDetalles[index].TipoConstruccionId +"</td>" +
	 							" <td relation='TipoOcupacionId'>"+ listadoCotizacionDetalles[index].TipoOcupacionId +"</td>" +
	 							" <td relation='AnioConstruccion'>"+ listadoCotizacionDetalles[index].AnioConstruccion +"</td>" +
	 							" <td relation='NumeroTotalPisos'>"+ listadoCotizacionDetalles[index].NumeroTotalPisos +"</td>" +
	 							" <td relation='Extintores'>"+ listadoCotizacionDetalles[index].Extintores +"</td>" +
	 							" <td relation='DetectorHumo'>"+ listadoCotizacionDetalles[index].DetectorHumo +"</td>" +
	 							" <td relation='Sprinklers'>"+ listadoCotizacionDetalles[index].Sprinklers +"</td>" +
	 							" <td relation='AlarmaMonitoreada'>"+ listadoCotizacionDetalles[index].AlarmaMonitoreada +"</td>" +
	 							" <td relation='Guardiania'>"+ listadoCotizacionDetalles[index].Guardiania +"</td>" +
	 							" <td relation='Otros'>"+ listadoCotizacionDetalles[index].Otros +"</td>" +
	 							" <td relation='ValorEstructuras'>"+ listadoCotizacionDetalles[index].ValorEstructuras +"</td>" +
	 							" <td relation='ValorMueblesEnseres'>"+ listadoCotizacionDetalles[index].ValorMueblesEnseres +"</td>" +
	 							" <td relation='ValorMaquinaria'>"+ listadoCotizacionDetalles[index].ValorMaquinaria +"</td>" +
	 							" <td relation='ValorMercaderia'>"+ listadoCotizacionDetalles[index].ValorMercaderia +"</td>" +
	 							" <td relation='ValorInsumosNoelectronicos'>"+ listadoCotizacionDetalles[index].ValorInsumosNoelectronicos +"</td>" +
	 							" <td relation='ValorEquipoHerramienta'>"+ listadoCotizacionDetalles[index].ValorEquipoHerramienta +"</td>" +
	 							" <td relation='Latitud'>"+ listadoCotizacionDetalles[index].Latitud +"</td>" +
	 							" <td relation='Longuitud'>"+ listadoCotizacionDetalles[index].Longuitud +"</td>" +
	 							" <td relation='Informe'>"+ listadoCotizacionDetalles[index].Informe +"</td>" +
	 							" <td relation='EstadoInspeccion'>"+ listadoCotizacionDetalles[index].EstadoInspeccion +"</td>" +
	 							" <td relation='RequiereInspeccion'>"+ listadoCotizacionDetalles[index].RequiereInspeccion +"</td>" +
	 							" <td relation='TipoPredioId'>"+ listadoCotizacionDetalles[index].TipoPredioId +"</td>" +
	 							" <td relation='InspectorId'>"+ listadoCotizacionDetalles[index].InspectorId +"</td>" +
	 							" <td relation='PlanContenidosId'>"+ listadoCotizacionDetalles[index].PlanContenidosId +"</td>" +
	 							" <td relation='PlanEstructuraId'>"+ listadoCotizacionDetalles[index].PlanEstructuraId +"</td>" +
	 							" <td relation='ValorContenidos'>"+ listadoCotizacionDetalles[index].ValorContenidos +"</td>" +
	 							" <td relation='SeguridadAdecuada'>"+ listadoCotizacionDetalles[index].SeguridadAdecuada +"</td>" +
	 							" <td relation='FechaInspeccion'>"+ listadoCotizacionDetalles[index].FechaInspeccion +"</td>" +
	 							" <td relation='FechaSolicitud'>"+ listadoCotizacionDetalles[index].FechaSolicitud +"</td>" +
	 							"</tr>";
	 							auxiliarDetalles +=	aux;
	 							$("#buscando").fadeOut("slow");	 					
	 						});	
							// Evitar Reinicializacion datatable
	 						var oTable = $('#dataTableDetalles').dataTable();
							oTable.fnDestroy();							
							$('#dataTableDetalles tbody').html(auxiliarDetalles);
							
	 						$('#dataTableDetalles').dataTable( {	 							
        						"pagingType": "full",
        						"bFilter": true,
        						"bLengthChange": false,
        						"bSort" : false,
        						"iDisplayLength": 10, // Limitamos el numero de filas en la paginacion
        						"dom": 'T<"clear">lfrtip',
        						"tableTools": {
            						"sSwfPath": "/CotizadorWeb/static/js/sb-admin/plugins/dataTables/swf/copy_csv_xls.swf",
            					},
    						});
	 						existenRegistro = true;
	 					}
	 					
		 				if(!existenRegistro){
	 						var oTable = $('#dataTableDetalles').dataTable();
							oTable.fnDestroy();							
							$('#dataTableDetalles tbody').html("<tr><td colspan='12'>No existen Registros</td></tr>");
	 						$("#buscando").fadeOut("slow");
		 				}
	 				}
	 			});
	    	 });
		});

				
					
				//Metodo validacion de fechas buscador
		    		$(function(){
		    			var startDate = new Date();
		    			var endDate = new Date();
		    			
		    			$('#dp1').datepicker().on('changeDate', function(ev){
		    					if (ev.date.valueOf() > endDate.valueOf()){
		    						alert("La Fecha Inicial no puede ser mayor que la Fecha Actual");
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
		    						return false;
		    					} else {
		    						
		    						endDate = new Date(ev.date);
		    						$('#endDate').text($('#dp2').data('date'));
		    					}
		    					$('#dp2').datepicker('hide');
		    				});
		    		});
	</script>
	<script id="errorTemplate" type="text/x-kendo-template">
  <div class="wrong-pass">
      <img src="../static/images/error-icon.png" width="60" height="60"/>
         <h3>#= title #</h3>
      <p>#= message #</p>
  </div>
</script>
<style>
                .demo-section p {
                    margin: 3px 0 3px;
                    line-height: 50px;
                }
                .demo-section .k-button {
                    width: 200px;
                }

                .k-notification {
                    border: 0;
                }
                /* Error template */
                .k-notification-error.k-group {
                    background: rgba(100%,0%,0%,.7);
                    color: #ffffff;
                }
                .wrong-pass {
                    width: 400px;
                    height: 50px;
                }
                .wrong-pass h3 {
                    font-size: 1em;
                    padding: 36px 14px 9px;
                }
                .wrong-pass img {
                    float: left;
                    margin: 30px 15px 30px 30px;
                }
                
            </style>
</head>
<body>
<%
			// Permitimos el acceso si la session existe		
				if(session.getAttribute("login") == null){
				    response.sendRedirect("/CotizadorWeb");
				}
%>
	<span id="notification" style="display:none;"></span>
	
	<div class="row crud-nav-bar">	
	<div class="well">
			<table class="table">
				<thead>
					<tr>
						<td colspan="3" style="font-weight: bold;"><center>Buscador
								Cotizaciones Pymes</center></td>

					</tr>
					<tr>
						<th>Busqueda por Cotizacion:</th>
						<th>Cotizacion: <input type="text" id="cotizacion_id"
							onkeypress="return justNumbers(event);"></th>
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
							<th>GrupoProductoId</th>
							<th>RamoId</th>
							<th>CoberturaEnsMultiId</th>
							<th>CoberturaEnsProgrId</th>
							<th>Nombre</th>
							<th>TasaSugerida</th>
							<th>TasaIngresada</th>
							<th>ValorIngresado</th>
							<th>PrimaCalculada</th>
							<th>CotizacionId</th>
							<th>ObjetoPymesCoberturaId</th>
							<th>CotizacionDetalleId</th>
							<th>CoberturaPymesId</th>
							<th>TipoOrigen</th>
							<th>ValorMaximoLimiteAsegurado</th>
							<th>PorcentajeLimiteAsegurado</th>
							<th>TipoCoberturaId</th>
						</tr>
					</thead>
					<tbody id="dataTableContent" class="searchable" style="font-size: x-small;">
											
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- Datatable -->
	
	<!-- Datatable -->
	<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">	
				<table class="table table-striped table-bordered table-hover"
					id="dataTableRamos" style="font-size: x-small;">
					<thead>
						<tr>
							<th>RamoId</th>
							<th>CotizacionId</th>
							<th>PrimaCalculada</th>
							<th>SumaAsegurada</th>
							<th>NombreRamo</th>
						</tr>
					</thead>
					<tbody id="dataTableContentRamos" class="searchable" style="font-size: x-small;">
											
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- Datatable -->	
	
	<!-- Datatable -->
	<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">	
				<table class="table table-striped table-bordered table-hover"
					id="dataTableDetalles" style="font-size: x-small;">
					<thead>
						<tr>
							<th>ObjetoPymesId</th>
							<th>CallePrincipal</th>
							<th>NumeroDireccion</th>
							<th>CalleSecundaria</th>
							<th>NombreEdificio</th>
							<th>NumeroPiso</th>
							<th>NumeroOficina</th>
							<th>Sector</th>
							<th>ProvinciaId</th>
							<th>CiudadId</th>
							<th>ActividadEconomicaId</th>
							<th>ContabilidadFormal</th>
							<th>TieneMasDosAnio</th>
							<th>TipoConstruccionId</th>
							<th>TipoOcupacionId</th>
							<th>AnioConstruccion</th>
							<th>NumeroTotalPisos</th>
							<th>Extintores</th>
							<th>DetectorHumo</th>
							<th>Sprinklers</th>
							<th>AlarmaMonitoreada</th>
							<th>Guardiania</th>
							<th>Otros</th>
							<th>ValorEstructuras</th>
							<th>ValorMueblesEnseres</th>
							<th>ValorMaquinaria</th>
							<th>ValorMercaderia</th>
							<th>ValorInsumosNoelectronicos</th>
							<th>ValorEquipoHerramienta</th>
							<th>Latitud</th>
							<th>Longuitud</th>
							<th>Informe</th>
							<th>EstadoInspeccion</th>
							<th>RequiereInspeccion</th>
							<th>TipoPredioId</th>
							<th>InspectorId</th>
							<th>PlanContenidosId</th>
							<th>PlanEstructuraId</th>
							<th>ValorContenidos</th>
							<th>SeguridadAdecuada</th>
							<th>FechaInspeccion</th>
							<th>FechaSolicitud</th>
						</tr>
					</thead>
					<tbody id="dataTableContentDetalles" class="searchable" style="font-size: x-small;">
											
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- Datatable -->	
</body>
</html>