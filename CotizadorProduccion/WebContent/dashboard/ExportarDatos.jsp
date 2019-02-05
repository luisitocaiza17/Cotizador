<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exportaci&oacute;n de datos - CotizadorQBE</title>

<!-- Core CSS - Include with every page -->
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	
	<!-- Table Tools -->
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.tableTools.js"></script>
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.tableTools.css" rel="stylesheet">
	
	<script src="../static/js/cotizador/comun.js"></script>	
	<script src="../static/js/util.js"></script>
    <!-- Para el Datepicker-->
    <link href="../static/css/datepicker.css" rel="stylesheet">
    <script src="../static/js/bootstrap-datepicker.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		activarMenu("ExportarDatos");	
	});
	function exportar(){	
		$("#buscando").fadeIn("slow");
		window.open('../AgriExportarDatos');
		$("#buscando").fadeOut("slow");
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
<table>
<tr align="center">
<td align="center"><strong>EXPORTACIÓN DE DATOS</strong></td>
</tr>
<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>Está opción permite exportar los datos para poder usarlos en el sistema Off-Line.</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
	<td><div id="buscando" hidden="hidden">
								<div style="margin-top: 10px;">
									<span id="loading-msg"></span><img
										src="../static/images/ajax-loader.gif" /> Procesando la
									exportación, por favor espere...
								</div></td>
	</tr>
	<tr>
		<td><button id="btnExportar" class="btn btn-primary"  onclick="exportar()">Exportar Datos Ahora</button></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
</table>
</body>
</html>