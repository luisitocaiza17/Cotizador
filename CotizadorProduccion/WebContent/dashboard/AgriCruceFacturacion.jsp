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
		var proceso="CruceFacturacion";
		
		$("#loading").show();
				
		$("#content").hide();	
		// Evitar Reinicializacion datatable
		$("#titulo").show();
		$("#titulo2").show();
		$("#dataTableErrores").show();
		if ($('#grid').data().kendoGrid){
			$('#grid').data().kendoGrid.destroy();
			$('#grid').empty();
		}
		
		Cargar();
		
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
	                		url: "../AgriCruceFacturacionController",
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
						{ field: "numeroTramite", title: "Nro. Trámite", width: "100px",headerAttributes: { style: "white-space: normal"}},
						{ field: "idCotizacion2", title: "Póliza.", width: "60px",headerAttributes: { style: "white-space: normal"}},
						{ field: "nombresCliente", title: "Asegurado", width: "120px",headerAttributes: { style: "white-space: normal"}},
						{ field: "identificacionCliente", title: "Cédula", width: "90px",headerAttributes: { style: "white-space: normal"}},
						{ field: "tipoCultivoNombre", title: "Cultivo", width: "140px",headerAttributes: { style: "white-space: normal"}},
						{ field: "provinciaNombre", title: "Provincia", width: "80px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "cantonNombre", title: "Cantón", width: "100px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "parroquiaNombre", title: "Parroquia", width: "120px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "direccionSiembra", title: "Dirección", width: "200px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "vigenciaDias", title: "Dias de Vigencia", width: "80px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "fechaVigenciaDesde", type:"date", format:"{0:dd/MM/yyyy}", title: "Desde", width: "80px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "fechaVigenciaHasta", type:"date", format:"{0:dd/MM/yyyy}", title: "Hasta", width: "80px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "hectareasAsegurables", title: "No. HA", width: "80px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "sumaAseguradaPorCiento", title: "V.Asegurado al 90%", width: "80px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "tasa", title: "Tasa QBE" , width: "80px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "primaNetaPorCiento", title: "P.Neta 90%", width: "80px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "comision", title: "Comision 6%", width: "80px",headerAttributes: { style: "white-space: normal"}},
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
	
	function Cargar(){
		$('#tramitesProblemas').children().remove();
		$.ajax({
			url:'../AgriCruceFacturacionController',
			data:{
				'tipoConsulta':'importarErrores',
				"nombreArchivo" : nombreArchivo	
			},
			type:'post',
			datatype:'json',
			async: false,
			success: function(data){
				var errores = data.listCotError;
				$.each(errores, function(index){	
					$('#tramitesProblemas').append("<tr class='odd gradeX'>"+
							"<td relation='tramite'>"+errores[index].tramite+"</td>"+
							"<td relation='cliente'>"+errores[index].cliente+"</td>"+	
							"<td relation='sumaAsegurada'>"+errores[index].sumaAsegurada+"</td>"+
							"<td relation='sumaAseguradaDes'>"+errores[index].sumaAseguradaDes+"</td>"+	
							"<td relation='primaAsegurada'>"+errores[index].primaAsegurada+"</td>"+
							"<td relation='primaAseguradaDes'>"+errores[index].primaAseguradaDes+"</td>"+	
							"<td relation='observacion'>"+errores[index].observacion+"</td>"+	
					"</tr>");
				});
				var cambioEstado=confirm(data.mensaje);
				if(cambioEstado)
					cambioEstadoFacturado();
				
				
				$("#loading").remove();	
			}
		});
	}

	function cambioEstadoFacturado(){
		$.ajax({
			url:'../AgriCruceFacturacionController',
			data:{
				'tipoConsulta':'cambiosEstados'
			},
			type:'post',
			datatype:'json',
			async: false,
			success: function(data){
				if(data.correcto)
					alert("cambio de estado a facturado CORRECTO")
				else
					alert("Sucedio un problema: "+data.mensaje)
			}
		});
	}
	
	$(document).ready(function() {
		activarMenu(this);		
		$("#titulo").hide();
		$("#titulo2").hide();
		$("#dataTableErrores").hide();
		
		$("#loading").hide();
		$("#files").kendoUpload({
			async : {
				saveUrl : "../AgriCruceFacturacionController"
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
		<tr>&nbsp;</tr>	
		<tr>
			<td colspan="2">Está opción le permite Subir un archivo en el formato Excel para cruzar la informacion existente en el cotizador y la información proveniente
			de reseguros.</strong>.
			</td>
		</tr>
		<tr>&nbsp;</tr>
		<br>		
		<tr>
			<table class="table table-bordered" style="border: 1px solid: #ddd !important;">
			        <thead>
			        <tr >
			        <th colspan="4">
			        	<b>FORMATO DE SUBIDA DE COTIZACIONES DE REASEGURO.</b>
			        </th>
			        </tr>
			        <tr>
			        	<th># Número de Trámite</th>
			        	<th>Cliente</th>
			        	<th>Suma Asegurada</th>
			        	<th>Suma Asegurada al 90%</th>
			        	<th>Prima Neta</th>
			        	<th>Prima Neta al 90%</th>
			        </tr>
			        </thead>
			        <tbody> 
			        	<tr  class="info">
			        		<td>####-##</td>
			        		<td>####</td>
			        		<td>##.##</td>
			        		<td>##.##</td>
			        		<td>##.##</td>
			        		<td>##.##</td>
			        	</tr>
			        </tbody>
		    	</table>		   
		</tr>
	</table>
	
		
	<table>		
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
	</table>
	<br>
	
	<br>
	<!-- Datatable -->
	<div class="alert alert-success" role="alert" id="titulo">
	  <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
	  <span class="sr-only">Busqueda:</span>
	  Cotizaciones Correctas
	</div>
	
	<p class="bg-primary" ></p>	
	<div id="grid">
	</div>

	<br>
	<div class="alert alert-danger" role="alert" id="titulo2">
	  <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
	  <span class="sr-only">Busqueda:</span>
	  Cotizaciones Incorrectas
	</div>
	<!-- tabla de tramites no encontrados -->
	<div id="loading">
		<div class="loading-indicator">
			<img src="../static/images/ajax-loader.gif"/><br /><br />
			<span id="loading-msg">Cargando...</span>
		</div>					
	</div>
	
	<table class="table table-striped table-bordered table-hover"
		id="dataTableErrores">
			<thead>
				<tr>
					<th>Trámite</th>	
					<th>Cliente</th>
					<th>Suma Asegurada</th>
					<th>Suma Asegurada(90%)</th>
					<th>Prima Neta</th>
					<th>Prima Neta (90%)</th>	
					<th>Observación</th>
				</tr>	
			</thead>
			<tbody id="tramitesProblemas" class="searchable">
		               		 
			</tbody>                     
       </table>

</body>
</html>