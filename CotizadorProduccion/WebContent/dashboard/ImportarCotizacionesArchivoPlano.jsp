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
		var oTable = $('#dataTable').dataTable();
		oTable.fnDestroy();

		if (nombreArchivo !== "") {
			$("#loading").show();
			$("#datatableError").children().remove();

			tipoConsulta = "importar";
			$
					.ajax({
						url : '../ImportarCAPController',
						data : {
							"tipoConsulta" : "importar",
							"nombreArchivo" : nombreArchivo
						},
						type : 'POST',
						datatype : 'json',
						success : function(data) {
							$("#loading").hide();							
							alert(data.mensaje);
							var listCotError = data.listCotError;
							if( typeof listCotError != 'undefined'){
								$("#content").show();								
								$.each(
												listCotError,
												function(index) {
													$("#datatableError")
															.append(
																	"<tr class='odd gradeX'>"
																			+ "<td>"
																			+ listCotError[index].canal
																			+ "</td>"
																			+ "<td>"
																			+ listCotError[index].cliente
																			+ "</td>"
																			+ "<td>"
																			+ listCotError[index].identificacion
																			+ "</td>"
																			+ "<td>"
																			+ listCotError[index].montoAsegurado
																			+ "</td>"
																			+ "<td>"
																			+ listCotError[index].fechaSolicitud
																			+ "</td>"
																			+ "<td>"
																			+ listCotError[index].fechaSiembra
																			+ "</td>"
																			+ "<td>"
																			+ listCotError[index].tipoCultivo
																			+ "</td>"
																			+ "<td>"
																			+ listCotError[index].hasAseguradas
																			+ "</td>"
																			+ "<td>"
																			+ listCotError[index].hasLote
																			+ "</td>"
																			+ "<td>"
																			+ listCotError[index].esTecnificado
																			+ "</td>"
																			+ "<td>"
																			+ listCotError[index].provincia
																			+ "</td>"
																			+ "<td>"
																			+ listCotError[index].canton
																			+ "</td>"
																			+ "<td>"
																			+ listCotError[index].parroquia
																			+ "</td>"
																			+ "<td>"
																			+ listCotError[index].ubicacion
																			+ "</td>"
																			+ "<td>"
																			+ listCotError[index].telefono
																			+ "</td>"
																			+ "<td>"
																			+ listCotError[index].gpsX
																			+ "</td>"
																			+ "<td>"
																			+ listCotError[index].gpsY
																			+ "</td>"
																			+ "<td>"
																			+ listCotError[index].detalle
																			+ "</td>"
																			+ "</tr>");
												});
								

								$('#dataTable')
										.dataTable(
												{
													"pagingType" : "full",
													"bFilter" : false,
													"bLengthChange" : false,
													"bSort" : false,
													"iDisplayLength" : 10, // Limitamos el numero de filas en la paginacion
													"scrollX" : true,
													"dom" : 'T<"clear">lfrtip',
													"tableTools" : {
														"sSwfPath" : "/CotizadorWeb/static/js/sb-admin/plugins/dataTables/swf/copy_csv_xls.swf",
														"aButtons" : [ "copy",
																"xls", "print" ]
													}
												});
							}
							
							
							

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
				alert("Por favor seleccioanar el achivo correcto.");
				e.preventDefault();
			}
		});
	}

	function onSuccess(e) {
		nombreArchivo = e.files[0].name;
	}

	$(document).ready(function() {

		activarMenu("ImportarCotizacionesArchivoPlano");

		$("#loading").hide();
		$("#files").kendoUpload({
			async : {
				saveUrl : "../ImportarCAPController"
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
				realizadas en <strong>Archivos Planos</strong>.
			</td>
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
							<th>NUMID</th>
							<th>MONTO</th>
							<th>FECSOL</th>
							<th>FECSIEM</th>
							<th>NOMBRE DEL CULTIVO</th>
							<th>NUMHAS</th>
							<th>NUMHASLOTE</th>
							<th>TECNIF</th>
							<th>PROV</th>
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

</body>
</html>