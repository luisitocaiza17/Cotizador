<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="../static/css/loading.css" rel="stylesheet">

<script
	src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
<script
	src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="../static/js/util.js"></script>

<!-- KENDO -->
<link rel="stylesheet" href="../static/css/Kendo/kendo.common.min.css" />
<link rel="stylesheet" href="../static/css/Kendo/kendo.blueopal.min.css" />
<script src="../static/js/Kendo/kendo.all.min.js"></script>
<script src="../static/js/Kendo/kendo.web.min.js"></script>
<script src="../static/js/Kendo/jszip.min.js"></script>

<title>Recalculo Masivo - QBE</title>
<script>
	var tipoConsulta = "";
	var nombreArchivo = "";

	$(function() {
		$(".wrapper1").scroll(function() {
			$(".wrapper2").scrollLeft($(".wrapper1").scrollLeft());
		});
		$(".wrapper2").scroll(function() {
			$(".wrapper1").scrollLeft($(".wrapper2").scrollLeft());
		});
	});

	function importar() {
		var operacion=$("#R_E_M").is(":checked");
		var proceso="Ninguno";
		if(operacion){
			proceso="RecalculoMasivo";
		}else{
			operacion=$("#R_E_D").is(":checked");
			if(operacion){
				proceso="Lugares";	
			}else{
				proceso="Fechas";
			}
		}
				
		$("#content").hide();	
		// Evitar Reinicializacion datatable
		$("#titulo").show();
		
		if ($('#grid').data().kendoGrid){
			$('#grid').data().kendoGrid.destroy();
			$('#grid').empty();
		}

		if (nombreArchivo !== "") {
			$("#grid").kendoGrid({
				toolbar: ["excel"],
	            excel: {
	                fileName: "Listado.xlsx",
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
	                		url: "../AgriRecalculoMasivoController",
	                		data: {
	                			"tipoConsulta" : "importarKendo",
								"nombreArchivo" : nombreArchivo,
								"proceso":proceso
	    					}
	                	}
	                	
	                },
	                schema: {
	                	data: "Data",
	                	total: "Total",
	                	
	                }
	            },
	            columns: [
							{ field: "id", title: "Id De Cotizacion.", width: "50px",headerAttributes: { style: "white-space: normal"}},
		      				{ field: "observacion", title: "Estado del Proceso.", width: "180px",headerAttributes: { style: "white-space: normal"}},
		      				],
		      	            height: 500,
		                selectable: true,
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
		} else {
			alert("Seleccione un archivo.");
		}
		
		
	}

	function onUpload(e) {
		// Array with information about the uploaded files
		var files = e.files;

		// Check the extension of each file and abort the upload if it is not .jpg
		$.each(files, function() {
			if (this.extension.toLowerCase() != ".xls"
					&& this.extension.toLowerCase() != ".xlsx") {
				alert("Por favor seleccionar el achivo correcto.");
				e.preventDefault();
			}
		});
	}

	function onSuccess(e) {
		nombreArchivo = e.files[0].name;
	}

	$(document).ready(function() {

		activarMenu("AgriRecalculoMasivo");
		
		$("#titulo").hide();
		$("#loading").hide();
		$("#files").kendoUpload({
			async : {
				saveUrl : "../AgriRecalculoMasivoController"
			},
			multiple : false,
			upload : onUpload,
			success : onSuccess
		//cancel: onCancel
		});
	});
</script>
</head>
<body>
	<%
		// Permitimos el acceso si la session existe		
		if (session.getAttribute("login") == null) {
			response.sendRedirect("/CotizadorWeb");
		}
	%>

	<table>
		<tr>
			<td colspan="2">Está opción le permite importar las cotizaciones
				en el formato establecido <strong>para el recalculo masivo de los valores</strong>.
			</td>
		</tr>		
		<tr>
			<table class="table table-bordered" style="border: 1px solid: #ddd !important;">
			        <thead>
			        <tr >
			        <th colspan="4">
			        	<b>TABLA DE RECALCULO MASIVO</b>
			        </th>
			        </tr>
			        <tr>
			        	<th>Id Cotización</th>
			        	<th>Costo Producción</th>
			        	<th>Num. Hectareas</th>
			        	<th>Tasa </th>
			        </tr>
			        </thead>
			        <tbody> 
			        	<tr  class="success">
			        		<td>####</td>
			        		<td>##.##</td>
			        		<td>##.##</td>
			        		<td>##.##</td>
			        	</tr>
			        </tbody>
		    	</table>
		   
		</tr>
		
		<tr>
			<table class="table table-bordered" style="border: 1px solid: #ddd !important;">
		        <thead>
		        <tr >
		        <th colspan="4">
		        	<b>TABLA DE CAMBIO DE SITIOS DE SIEMBRA</b>
		        </th>
		        </tr>
		        <tr>
		        	<th WIDTH="250">Id Cotización</th>
		        	<th>Sitio de Siembra</th>		        	
		        </tr>
		        </thead>
		        <tbody> 
		        	<tr  class="info">
		        		<td>####</td>
		        		<td>##.##</td>
		           	</tr>
		        </tbody>
		    </table>	
		</tr>
		
		<tr>		
			<table class="table table-bordered" style="border: 1px solid: #ddd !important;">
		        <thead>
		        <tr >
		        <th colspan="4">
		        	<b>TABLA DE CAMBIO DE FECHAS DE SIEMBRA</b>
		        </th>
		        </tr>
		        <tr>
		        	<th WIDTH="250">Id Cotización</th>
		        	<th>Fecha Siembra dd/MM/yyyy</th>		        	
		        </tr>
		        </thead>
		        <tbody> 
		        	<tr  class="danger">
		        		<td>####</td>
		        		<td>##.##</td>
		           	</tr>
		        </tbody>
		    </table>
		</tr>		
	</table>
	
		
	<table>
		<tr>
			<b> OPCIONES MASIVAS </b>
		</tr>
		<tr>
			<br>
			<br>
			<input type="radio" name="reporte" value="1" id="R_E_M" checked> Recalculo de valores Masivos<br>
			<input type="radio" name="reporte" value="2" id="R_E_D"> Cambio Masivo de Lugares<br>
			<input type="radio" name="reporte" value="3" id="R_E_F"> Cambio Masivo de Fechas<br>
			<br>
		</tr>
		<tr>
			<td colspan="2">Archivos Excel de cotizaciones:</td>
		</tr>
		<tr>
			<td colspan="2"><input name="files" id="files" type="file" /></td>
		</tr>
		<tr>
			<td><br /> <br /></td>
		</tr>
		<tr>
			<td colspan="2"><button id="btnImportar" class="btn btn-primary"
					onclick="importar()">Importar Ahora</button></td>			
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<div id="loading">
			<div class="loading-indicator">
				<img src="../static/images/ajax-loader.gif" /><br /> <br /> <span
					id="loading-msg">Procesando, espere por favor...</span>
			</div>
		</div>
	</table>
	<br>
	<div id="gridCorrectas">
	
	</div>
	
	<br>
	<!-- Datatable -->
	<h3 id="titulo">ESTADO DE COTIZACIONES</h3>	
	<div id="grid">
	
	</div>

</body>
</html>