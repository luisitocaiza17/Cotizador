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

<title>Archivos Planos- QBE</title>
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
		$("#content").hide();	
		// Evitar Reinicializacion datatable

		
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
	                		url: "../AgriCargaPreviaArchivoPlanoController",
	                		data: {
	                			"tipoConsulta" : "importarKendo",
								"nombreArchivo" : nombreArchivo
	    					}
	                	}
	                	
	                },
	                schema: {
	                	data: "Data",
	                	total: "Total",
	                }
	            },
	            columns: [
		      				{ field: "canal", title: "Canal.", width: "80px",headerAttributes: { style: "white-space: normal"}},
		      				{ field: "nombres", title: "Nombres", width: "100px", hidden: true, exportar: true},
		      				{ field: "apellidos", title: "Apellidos", width: "100px", hidden: true, exportar: true},
		      				{ field: "cliente", title: "Cliente.", width: "100px",hidden: false,exportar: false},
		      				{ field: "identificacion", title: "Identificacion.", width: "120px",headerAttributes: { style: "white-space: normal"}},
		      				{ field: "montoAsegurado", title: "Monto.", width: "60px",headerAttributes: { style: "white-space: normal"}},
		      				{ field: "fechaSolicitud", title: "Fech.Solicitud", width: "100px",headerAttributes: { style: "white-space: normal"}},
		      				{ field: "fechaSiembra", title: "Fech.Siembra.", width: "100px",headerAttributes: { style: "white-space: normal"}},
		      				{ field: "tipoCultivo", title: "Cultivo.", width: "120px",headerAttributes: { style: "white-space: normal"}},
		      				{ field: "hasAseguradas", title: "Hec.Aseguradas.", width: "120px",headerAttributes: { style: "white-space: normal"}},
		      				{ field: "hasLote", title: "Hec.Lote.", width: "80px",headerAttributes: { style: "white-space: normal"}},
		      				{ field: "esTecnificado", title: "Tecnif.", width: "80px",headerAttributes: { style: "white-space: normal"}},
		      				{ field: "provincia", title: "Provincia.", width: "80px",headerAttributes: { style: "white-space: normal"}},
		      				{ field: "canton", title: "Canton.", width: "80px",headerAttributes: { style: "white-space: normal"}},
		      				{ field: "parroquia", title: "Parroquia.", width: "80px",headerAttributes: { style: "white-space: normal"}},
		      				{ field: "ubicacion", title: "Ubicacion.", width: "80px",headerAttributes: { style: "white-space: normal"}},
		      				{ field: "telefono", title: "Telefono.", width: "120px",headerAttributes: { style: "white-space: normal"}},
		      				{ field: "gpsX", title: "GpsX.", width: "80px",headerAttributes: { style: "white-space: normal"}},
		      				{ field: "gpsY", title: "GpsY.", width: "80px",headerAttributes: { style: "white-space: normal"}},
		      				{ field: "detalle", title: "Observacion del error.", width: "140px",headerAttributes: { style: "white-space: normal"}},
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
		
		alert("Proceso terminado.");
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

		activarMenu(this);

		$("#loading").hide();
		$("#files").kendoUpload({
			async : {
				saveUrl : "../AgriCargaPreviaArchivoPlanoController"
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
				realizadas en <strong>Archivos Planos Carga Previa</strong>.
			</td>
		</tr>
		<tr>
			<table class="table table-bordered" style="border: 1px solid: #ddd !important;">
			        <thead>
			        <tr >
			        <th colspan="4">
			        	<b>FORMATO ARCHIVO EXCEL</b>
			        </th>
			        </tr>
			        <tr>
			        	<th>CANAL</th>
			        	<th>APELLIDOS</th>
			        	<th>NOMBRES</th>
			        	<th>IDENTIFICACION</th>
			        	<th>MONTO DEL CREDITO</th>
			        	<th>FECHA DE SOLICITUD</th>
			        	<th>FECHA DE SIEMBRA</th>
			        	<th>NOMBRE DEL CULTIVO</th>
			        	<th>HECT. ASEGUR</th>
			        	<th>HECT. LOTE</th>
			        	<th>TECNIFICADO</th>
			        	<th>PROVINCIA</th>
			        	<th>CANTÓN</th>
			        	<th>PARROQUIA</th>
			        	<th>REFERENCIA</th>
			        	<th>TELF</th>
			        	<th>GPS X</th>
			        	<th>GPS Y</th>
			        </tr>
			        </thead>
			        <tbody> 
			        	<tr  class="success">
			        		<td>PRONOCA</td>
			        		<td>####</td>
			        		<td>####</td>
			        		<td>####</td>
			        		<td>##,##</td>
			        		<td>dd/MM/yyyy</td>
			        		<td>dd/MM/yyyy</td>
			        		<td>####</td>
			        		<td>##,##</td>
			        		<td>##,##</td>
			        		<td>####</td>
			        		<td>####</td>
			        		<td>####</td>
			        		<td>####</td>
			        		<td>####</td>
			        		<td>####</td>
			        		<td>##,##</td>
			        		<td>##,##</td>
			        	</tr>
			        </tbody>
		    	</table>
		   
		</tr>
		<tr>
			<td><br /> <br /></td>
		</tr>
		<tr>
			<td colspan="2">Archivos planos:</td>
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
	<!-- Datatable -->
	<div class="row" style="display: none;" id="content">
		<div class="col-lg-12">
			<div class="table-responsive">
			<h3>Cotizaciones con <strong>errores</strong>.</h3>
				<table class="table table-striped table-bordered table-hover"
					id="dataTable">
					<thead>
						<tr>
							<th>CANAL</th>
							<th>NOMBRES</th>
							<th>IDENTIFICACION</th>
							<th>MONTO</th>
							<th>FECH.SOLICITUD</th>
							<th>FECH.SIEMBRA</th>
							<th>CULTIVO</th>
							<th>HEC.ASEGURADAS</th>
							<th>HEC.LOTE</th>
							<th>TECNIFICADO</th>
							<th>PROVINCIA</th>
							<th>CANTON</th>
							<th>PARROQUIA</th>
							<th>REFERENCIA</th>
							<th>TEL&Eacute;FONO</th>
							<th>GPS X</th>
							<th>GPS Y</th>
							<th>Error</th>
						</tr>
					</thead>
					<tbody id="datatableError" class="searchable">
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<div id="grid"></div>

</body>
</html>