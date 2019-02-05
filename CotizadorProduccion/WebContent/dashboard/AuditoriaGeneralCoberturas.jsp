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
				activarMenu("AuditoriaGeneralCoberturas");
				
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
		    	 });
				
		});
		
		function Cargar() {	
			$('#dataTable_wrapper').hide();

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
	                pageSize: 20,
	                transport:{
	                	read: {
	                		url: "../AuditoriaGeneralCoberturasController",
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
	      				{ field: "id", title: "ID.", width: "30px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "estado", title: "Estado.", width: "40px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "tramite", title: "Tramite.", width: "60px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "objeto", title: "Observacion.", width: "400px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "proceso", title: "Canal.", width: "60px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "fecha", title: "Fech.CargaArchivo",format: "{0:MM/dd/yyyy}", width: "100px",headerAttributes: { style: "white-space: normal"}}
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
	        window.location.href='CotizacionesPreviasAgricola.jsp?previaId='+dataItem.id;
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
					</tr>					
				</tbody>
			</table>
		</div>
		<!-- Button trigger modal -->
	</div>
	<div id="grid"></div>
	
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