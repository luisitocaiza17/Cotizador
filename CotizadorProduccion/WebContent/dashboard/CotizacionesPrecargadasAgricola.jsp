<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cotizaciones Precargadas - CotizadorQBE </title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="../static/css/jquery-steps/normalize.css">
	<link rel="stylesheet" href="../static/css/jquery-steps/main.css">
	<link rel="stylesheet" href="../static/css/jquery-steps/jquery.steps.css">
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" href="../static/css/select2/select2.css">
	
	<!-- <script src="../static/js/jquery-steps/modernizr-2.6.2.min.js"></script>
	<script src="../static/js/jquery-steps/jquery.cookie-1.3.1.js"></script> -->
	<script src="../static/js/jquery-steps/jquery.steps.min.js"></script>
	<script src="../static/js/jquery.validate.js"></script>

	<!-- <script id="tipoObjetoCargaInicial" src="../static/js/agricola/carga.inicial.cotizador.agricola.js" tipoObjetoValor="Agricola"></script> -->
	<script id="tipoObjetoCargaInicial" src="../static/js/agricola/carga.inicial.cotizador.agricolaArchivoPlano.js" tipoObjetoValor="Agricola"></script>
	<!-- <script id="tipoObjetoMetodos" src="../static/js/agricola/metodos.agricola.js" tipoObjetoValor="Agricola"></script> -->
	<script id="tipoObjetoMetodos" src="../static/js/agricola/metodos.agricolaArchivoPlano.js" tipoObjetoValor="Agricola"></script>
	
	<script src="../static/js/cotizador/comun.js"></script>

	<script src="../static/js/util.js"></script> 
	<link
	href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">
	<script	src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script	src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="../static/js/jquery-dynamic-url/jquery.dynamic-url.js"></script>
	<script src="../static/js/select2.js"></script>
	<!--  	para el tooltipster -->
	<script src="../static/js/jquery-ui/jquery-ui.js"></script>
	<link rel="stylesheet" type="text/css"	href="../static/js/jquery-ui/jquery-ui.theme.css" />
	<link rel="stylesheet" type="text/css"	href="../static/css/tooltipster.css" />
	<script type="text/javascript" src="../static/js/jquery.tooltipster.js"></script>
	<script type="text/javascript"	src="../static/js/jquery.tooltipster.min.js"></script>
	<!-- Para el Datepicker-->
	<link href="../static/css/datepicker.css" rel="stylesheet">
	<script src="../static/js/bootstrap-datepicker.js"></script>
	
	<!-- KENDO -->
	<link rel="stylesheet" href="../static/css/Kendo/kendo.common.min.css" />
	<link rel="stylesheet" href="../static/css/Kendo/kendo.blueopal.min.css" />
	<script src="../static/js/Kendo/kendo.all.min.js"></script>
	<script src="../static/js/Kendo/kendo.web.min.js"></script>

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
		
		.wizard > .content .k-datepicker .k-input {
    		display: inline-block;
    		border: none;
		}
		
		.wizard > .content .k-combobox .k-input {
    		display: inline-block;
    		border: none;
		}
		
		.k-numerictextbox .k-state-default .k-formatted-value.k-input{
		    display: inline-block !important;
		}
		   
		.k-numerictextbox .k-state-focused .k-input {
		    display: none !important;
		}
		.red{
        color:red;
	    }
	
	    .green {
	        color:green;
	    }
		</style>
	</script>
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
				activarMenu("ResumenCotizaciones");
				
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
			var identificacion = $("#identificacion").val();
			var NombresCliente = $("#nombres_Cliente").val();
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
	                		url: "../CotizacionesPrecargadasAgricolaController",
	                		data: {
	                			"fInicio" : fechaInicio,
	    						"fFinal" : fechaFin,
	    						"NombresCliente":NombresCliente,
	    						"identificacion":identificacion,
	    						"tipoConsulta":"Consulta"
	    					}
	                	}
	                },
	                schema: {
	                	data: "Data",
	                	total: "Total",
	                }
	            },
	            columns: [
	      				{ field: "canalNombre", title: "Canal.", width: "80px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "clienteNombre", title: "Cliente.", width: "100px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "clienteIdentificacion", title: "Identificacion.", width: "120px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "montoAsegurado", title: "Monto.", width: "60px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "cargaFecha", title: "Fech.CargaArchivo",format: "{0:MM/dd/yyyy}", width: "100px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "solicitudFecha", title: "Fech.Solicitud",format: "{0:MM/dd/yyyy}", width: "100px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "siembraFecha", title: "Fech.Siembra.", width: "100px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "tipoCultivoNombre", title: "Cultivo.", width: "120px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "numerHasAseguradas", title: "Hec.Aseguradas.", width: "120px",headerAttributes: { style: "white-space: normal"},attributes: {
	      	                class: "#=numerHasAseguradas <= 10000 ? 'red' : 'green' # #console.log(data)#" 
	                    }},
	      				{ field: "numeroHasLote", title: "Hec.Lote.", width: "80px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "esTecnificado", title: "Tecnif.", width: "80px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "provinciaNombre", title: "Provincia.", width: "80px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "cantonNombre", title: "Canton.", width: "80px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "parroquiaNombre", title: "Parroquia.", width: "80px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "ubicacionCultivo", title: "Ubicacion.", width: "80px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "telefono", title: "Telefono.", width: "120px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "gpsX", title: "GpsX.", width: "80px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "gpsY", title: "GpsY.", width: "80px",headerAttributes: { style: "white-space: normal"}},
	      				{ command: { text: "Cotizar", click: fnEventoClick }, title: " ", width: "100px"}],
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
		
		// actualizar valores 
		function actualizar(Cotizacion) {
			$("#CotizacionId").val("");
			($("#SumaAsegurada").data("kendoNumericTextBox")).value(0);
			($("#PrimaNetaTotal").data("kendoNumericTextBox")).value(0);
			($("#Tasa").data("kendoNumericTextBox")).value(0);
			($("#Iva").data("kendoNumericTextBox")).value(0);
			($("#TotalFactura").data("kendoNumericTextBox")).value(0);
			$("#TipoSeguroId").val("");
			$("#Observacion").val("");
			
			//Reglas = "";
			VariablesCalculo = "";
			
			RecalSeguroCampesino=0;
			RecalSuperBancos=0;
			RecalDerechoEmision = 0;
			$
					.ajax({
						url : '../AgriCotizacionAprobacionController',
						data : {
							"cotizacionId" : Cotizacion,
							"tipoConsulta" : "buscarCotizacionId"
						},
						type : 'POST',
						datatype : 'json',
						success : function(data) {						
							$("#cotId").html(data.codigo);
							$("#noTramite").html(data.NumeroTramite);
							$("#canal").html(data.CanalNombre);
							$("#puntoVenta").html(data.PuntoVenta);
							$("#estadoCotizacion").html(data.EstadoCotizacion);						
							$("#CotizacionId").val(data.codigo);
							$("#TipoSeguroId").val(data.tipo_seguro);
							($("#SumaAsegurada").data("kendoNumericTextBox"))
									.value(data.suma_asegurada_total);
							($("#PrimaNetaTotal").data("kendoNumericTextBox"))
									.value(data.prima_neta_total);
							($("#Iva").data("kendoNumericTextBox")).value(data.iva);
							($("#TotalFactura").data("kendoNumericTextBox"))
									.value(data.totalFactura);
							///TODO:Nuevos campos a presentar en la pantalla de actualizacion 						
							$('#HectareasAsegurables').html(data.hectareas_asegurables);
							$('#CostoProduccion').html('$ '+data.costo_produccion);
							$('#MontoCredito').html('$ '+data.monto_credito);
							$('#FechaSiembra').html(data.fecha_siembra);
							$('#Parroquia').html(data.parroquia_nombre+" / "+data.sitio);
							$('#Provincia').html(data.provincia_nombre + " / "+data.canton_nombre);
							$('#TipoCultivo').html(data.tipo_cultivo_nombre);
							$('#Variedad').html(data.variedad);
							$('#ClienteDatos').html(data.nombres_cliente);
							$('#AgenteDatos').html(data.nombresAgente);
							$('#UsuarioDatos').html(data.nombresUsuario);
							$('#Longitud').html(data.longitud);
							$('#Latitud').html(data.latitud);
							$('#Observacion').html(data.observacion);
							$('#ClienteIdentificacion').html(data.ClienteIdentificacion);
							$('#DisponeRiego').html(data.DisponeRiego);
							$('#DisponeAsistencia').html(data.DisponeAsistencia);
							$('#AgricultorTecnificado').html(data.AgricultorTecnificado);
							//Reglas = data.Regla;
							
							/*if (data.tasa == 0) {
								if (typeof Reglas != "undefined") {							
								($("#Tasa").data("kendoNumericTextBox")).value(Reglas.Tasa);							
								}
							}else {*/
								($("#Tasa").data("kendoNumericTextBox")).value(data.tasa);
							//}
					//Variables de calculo 
					VariablesCalculo = data.VariablesCalculo;
					//deshabilitar controles 
					($("#PrimaNetaTotal").data("kendoNumericTextBox")).enable(false);
					
					($("#Iva").data("kendoNumericTextBox")).enable(false);
					($("#TotalFactura").data("kendoNumericTextBox")).enable(false);
					($("#Tasa").data("kendoNumericTextBox")).enable(false);
					($("#SumaAsegurada").data("kendoNumericTextBox")).enable(false);
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
						<td colspan="6" style="font-weight: bold;" align="center">Buscador Cotizaciones Precargadas Pronaca.</td>
					</tr>
					<tr>
						<th style="width: 180px">Busqueda por Fechas:</th>
						<th style="width: 100px">Fecha Creaci&oacute;n Desde:</th>
						<th style="width: 100px"><input type="text" data-date-format="dd-mm-yyyy" id="dp1" style="width: 100%"></th>
						<th style="width: 100px">Fecha Creaci&oacute;n Hasta:</th>
						<th style="width: 100px"><input type="text" data-date-format="dd-mm-yyyy" id="dp2" style="width: 100%"></th>
					</tr>
					<tr>
						<th style="width: 180px">Busqueda Varios:</th>	
						<th style="width: 100px">Cliente Identificación:</th>
						<th style="width: 100px"><input type="text" id="identificacion" onkeypress="return justNumbers(event);" style="width: 100%"></th>
						<th style="width: 100px">Cliente Nombres:</th>
						<th style="width: 100px"><input type="text" id="nombres_Cliente" style="width: 100%"></th>
					</tr>
									
				</thead>
				<tbody>
					<tr>
						<th></th>
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