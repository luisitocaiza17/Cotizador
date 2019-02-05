<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-control" content="no-cache">
<title>Cotizaciones Agr&iacute;cola Log - CotizadorQBE</title>
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
		var estadoConsulta="";//GetQueryStringByParameter('State');
		var codigo = "";
		var tipoConsulta = "";
		var Reglas = "";
		
		var estadoList = "";
		var canalList = "";
		var puntoVentaList = "";
		var agenteList = "";
		
		$(document).ready(function() {
				activarMenu(this);
				
				$("#loading").hide();
				
	    	 	$(".text_Currency").kendoNumericTextBox({
			        format: "c",
			        min: 0,
			        decimals: 2
			    });
				
				$(".text_Decimal").kendoNumericTextBox({
					format : '#.00',
			        min: 0,
			        decimals: 2
			    });		 
				
	    	 	$("#ConsultaBtn").click(function(){
	    	 		Cargar();
	    	 	});		
	    	 	
	    	 	$('#content-Upload').hide();
	    		$('#content-cargaMasiva').hide();
	    		$('#btnImportar').hide();
							
				$('#LimpiarBtn').click(function(){	    	 	
		    	 	$("#dp1").val("");
		    	 	$("#dp2").val("");
					$("#identificacion").val("");
		    	 	$("#Apellidos_Cliente").val("");
		    	 	if ($('#grid').data().kendoGrid){
		    			$('#grid').data().kendoGrid.destroy();
		    			$('#grid').empty();
		    		}
		 			$('#grid').hide();
		 			$('#content-Upload').hide();
		    		$('#content-cargaMasiva').hide();
		    		$('#btnImportar').hide();
		    	 });
				
				$('#btnAprobacionMasiva').click(function(){
					$('#dataTableMasiva').hide();
		 			$('#datatableError').html('');
		 			$("#content-cargaMasiva").hide();
					$("#content-Upload").show();
		    	 });
		});
		
		function Cargar() {	
			$('#dataTable_wrapper').hide();
			$('#grid').show();
			// Validaciones de las busquedas	
			var nro_tramite = $("#nro_tramite").val();
			var fechaInicio = $("#dp1").val();
			var fechaFin = $("#dp2").val();
			
			if ($('#grid').data().kendoGrid){
				$('#grid').data().kendoGrid.destroy();
				$('#grid').empty();
			}
			
			$("#grid").kendoGrid({
				toolbar: ["excel"],
	            excel: {
	                fileName: "QBE_Cotizaciones.xlsx",
	                filterable: true,
	                allPages: true
	            },
				dataSource: {
	                type: "json",
	                serverPaging: true,
	                serverSorting: true,
	                pageSize: 50000,
	                transport:{
	                	read: {
	                		url: "../AgriCruceEstadoSucreController",
	                		data: {
	                			"fInicio" : fechaInicio,
	    						"fFinal" : fechaFin,
	    						"nro_tramite":nro_tramite,
	    						"tipoConsulta":"encontrarPorParametros"
	    					}
	                	}
	                },
	                schema: {
	                	data: "Data",
	                	total: "Total",
	                }
	            },
	            columns: [
	      				{ field: "idCotizacion", title: "ID Cotizacion.", width: "50px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "tramite", title: "Trámite.", width: "100px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "estadoQbe", title: "Estado QBE.", width: "100px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "estadoSucre", title: "Estado Sucre.", width: "100px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "observacion", title: "Resultado.", width: "100px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "idEstado", title: "Resultado.", width: "100px",headerAttributes: { style: "white-space: normal"}},
	      				{ command: { text: "Ver Detalle", click: fnEventoClick },id:"btn", title: " ", width: "100px"}
	      				],
	      				height: 500,
	                selectable: true,
	                resizable: true,
	                pageable: {
	                    info: true,
	                    numeric: false,
	                    previousNext: false
	                },
	                scrollable: {
	                    virtual: true
	                },
	                
	                rowTemplate: '<tr class="#:observacion==\"DIFERENCIAS\"? \"red\" : \"white\"#" data-uid="#= uid #"><td>#: idCotizacion #</td><td>#:tramite #</td><td>#:estadoQbe #</td><td>#:estadoSucre #</td><td>#:observacion #</td><td><button id=i#:idCotizacion# class="btn btn-info" onclick=cambio("#:idCotizacion#","#:observacion#","#:estadoQbe#"); >IGUALAR ESTADOS</button></td></tr>'
	            }); 
			
			
			var exportFlag=false;
			$("#grid").data("kendoGrid").bind("excelExport", function (e) {
				var columns = e.sender.columns;
				if (!exportFlag) {
		            jQuery.each(columns, function (index) {
		                if (this.exportar) {
		                	e.sender.showColumn(this.field);
		                }
		            });
		            
		            //e.sender.showColumn("AgenteId");
		            e.preventDefault();
		            exportFlag = true;
		            setTimeout(function () {
		                e.sender.saveAsExcel();
		            }, 1000);
		        } else {
		        	jQuery.each(columns, function (index) {
		                if (this.exportar) {
		                	e.sender.hideColumn(this.field);
		                }
		            });
		            exportFlag = false;
		        }
			});
		}
		

		//para el datepicker 
		//Metodo validacion de fechas buscador
		$(function() {
			var startDate = new Date();
			var endDate = new Date();

			$('#dp1')
					.datepicker()
					.on(
							'changeDate',
							function(ev) {
								if (ev.date.valueOf() > endDate.valueOf()) {
									alert("La Fecha Inicial no puede ser mayor que la Fecha Actual");
									return false;
								} else {
									startDate = new Date(ev.date);
									$('#startDate').text($('#dp1').data('date'));
								}
								$('#dp1').datepicker('hide');
							});
			$('#dp2')
					.datepicker()
					.on(
							'changeDate',
							function(ev) {
									endDate = new Date(ev.date);
									$('#endDate').text($('#dp2').data('date'));
								$('#dp2').datepicker('hide');
							});
		});
		
		function fnEventoClick(e) {
	        e.preventDefault();
	        var dataItem = this.dataItem($(e.currentTarget).closest("tr"));     
	        alert("Id : "+dataItem.idCotizacion);	       
	    }
		function cambio(id,observacion,estado) {
			if(observacion==='DIFERENCIAS'){
				var confirmacion=confirm("DESEA IGUALAR EL ESTADO DE LA COTIZACIÓN QBE AL ESTADOS DE SUCRE?");
				if(confirmacion){					
					$('#i'+id).prop("disabled", true);
					$('#i'+id).text('ACTUALIZADO');	
					$.ajax({
						url:'../AgriCruceEstadoSucreController',
						data:{
							"cotizacion" : id,
							"estadoSucre":estado,
							"tipoConsulta":"procesoCambioEstado"
						},
						async: false,
						type:'post',
						datatype:'json',
						success: function(data){
							if(data.success==true){
								alert("Proceso Correcto la cotizacion fue actualizada con exito");
							}else{
								alert("Se produjo un error: "+data.error);
							}	
						}
					});
				}	
			}else{
				alert("El estado de la cotizacion está sincronizado y correcto");
				$('#i'+id).prop("disabled", true);				
			}
		}
		
				
	</script>
	<style type="text/css">
		.red{    
    		background-color:#EEAC27;
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

	<!-- ventana consulta -->
	<div class="row crud-nav-bar">
		<div class="well">
			<table class="table" style="width: 100%">
				<thead>
					<tr>
						<td colspan="6" style="font-weight: bold;" align="center">Buscador Log de Cotizaciones.</td>
					</tr>
					<tr>
						<th style="width: 180px">Busqueda por c&oacute;digos:</th>
						<th style="width: 100px">Nro. Tr&aacute;mite:</th>
						<th style="width: 300px"><input type="text" id="nro_tramite" style="width: 100%"></th>
					</tr>
					<tr>
						<th style="width: 180px">Busqueda por Fechas:</th>
						<th style="width: 100px">Fecha Creaci&oacute;n Desde:</th>
						<th style="width: 300px"><input type="text" data-date-format="dd-mm-yyyy" id="dp1" style="width: 100%"></th>
						<th style="width: 100px">Fecha Creaci&oacute;n Hasta:</th>
						<th style="width: 300px"><input type="text" data-date-format="dd-mm-yyyy" id="dp2" style="width: 100%"></th>
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
							<button  class="btn btn-primary" id="btnAprobacionMasiva" >Consulta masivas</button>
						</th>						
					</tr>					
				</tbody>
			</table>
		</div>
		<!-- Button trigger modal -->
	</div>
	
	<div class="row">
		<div class="col-md-1">
		</div>
		<div class="col-md-8">
			<div class="row" style="display: inline;" id="content-Upload">
				<table class="table table-bordered" style="border: 1px solid: #ddd !important;">
			        <thead>
			        <tr >
			        <th colspan="4">
			        	<b>FORMATO CRUCE MASIVOS</b>
			        </th>
			        </tr>
			        <tr>
			        	<th>Id Cotización/Tramite</th>			        				        	
			        </tr>
			        </thead>
			        <tbody> 
			        	<tr  class="success">
			        		<td>####</td>		
			        	</tr>			        	
			        </tbody>
		    	</table>
				<div class="col-lg-12">
					<div class="table-responsive">
					<table width="80%">
							<tr >
									<td colspan="2" >CRUCE DE ESTADO MASIVO</td>
								</tr>
							<tr >
								<td  colspan="2"><input name="files" id="files" type="file" /></td>
								
								</tr>
								<td><br /> <br /></td>
								<tr align="right">
									<td colspan="2"><button id="btnImportar" class="btn btn-primary" 
										onclick="importar()">Subir Archivo</button></td>			
								</tr>
								
								</table>
					</div>
				</div>
			</div>
			
			<!-- Tabla con respuesta de aprovacion masiva -->
			<div class="row" style="display: none;" id="content-cargaMasiva">
				<div class="col-lg-12">
					<div class="table-responsive">
					<h3>Detalle de Cotizaciones <strong>procesadas</strong>.</h3>
						<table class="table table-striped table-bordered table-hover"
							id="dataTableMasiva">
							<thead>
								<tr>
									<th># COTIZACIÓN</th>
									<th>COMENTARIO</th>
								</tr>
							</thead>
							<tbody id="datatableError" class="searchable">
							</tbody>
						</table>
					</div>
				</div>
			</div>
			</div>
			<div class="col-md-1">
			</div>
		</div>	
	
	<!-- Table -->
	<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">				
				<div id="grid"></div>
			</div>
		</div>
	</div>
</body>
</html>