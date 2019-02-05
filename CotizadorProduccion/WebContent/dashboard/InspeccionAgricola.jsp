<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../static/js/jquery.min.js"></script>

<link rel="stylesheet" href="../static/css/jquery-steps/normalize.css">
<link rel="stylesheet" href="../static/css/jquery-steps/main.css">
<link rel="stylesheet" href="../static/css/jquery-steps/jquery.steps.css">
<link rel="stylesheet" href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css">

<link rel="stylesheet" href="../static/css/select2/select2.css">
<script src="../static/js/jquery-steps/modernizr-2.6.2.min.js"></script>
<script src="../static/js/jquery-steps/jquery.cookie-1.3.1.js"></script>
<script src="../static/js/jquery-steps/jquery.steps.min.js"></script>
<script src="../static/js/jquery.validate.js"></script>
<script src="../static/js/bootstrap.min.js"></script>

<script src="../static/js/util.js"></script>
<script src="../static/js/jquery-dynamic-url/jquery.dynamic-url.js"></script>
<script src="../static/js/select2.js"></script>
<!--  	para el tooltipster -->
<script src="../static/js/jquery-ui/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css"
	href="../static/js/jquery-ui/jquery-ui.theme.css" />
<link rel="stylesheet" type="text/css"
	href="../static/css/tooltipster.css" />
<script type="text/javascript" src="../static/js/jquery.tooltipster.js"></script>
<script type="text/javascript"
	src="../static/js/jquery.tooltipster.min.js"></script>
<!-- Para el Datepicker-->
<link href="../static/css/datepicker.css" rel="stylesheet"/>
<script src="../static/js/bootstrap-datepicker.js"></script>

<!-- KENDO -->
<link rel="stylesheet" href="../static/css/Kendo/kendo.common.min.css" />
<link rel="stylesheet" href="../static/css/Kendo/kendo.blueopal.min.css" />
<script src="../static/js/Kendo/kendo.all.min.js"></script>
<script src="../static/js/Kendo/kendo.web.min.js"></script>

<script id="tipoObjetoMetodos" src="../static/js/agricola/inspeccion.agricola.js"></script>
<script src="../static/js/cotizador/comun.js"></script>

<script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>

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
	font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
	font-weight: bold;
}

.tab_strip_direccion {
	height: 300px;
}

#map-canvas {
    width:100%;
    height:500px;
}
</style>

<script>
	//eventos de objetos
	var editoVehiculo = false;
	var tieneDescuento = false;
	var cargadoDatosUPLA = false;
	var solicitarInspeccionArr = [];
	$(document).ready(function() {
		cargarInicalInspeccion();
		/* $('#add').on('shown', function() {
			$('.modal-body', this).css({
				width : 'auto',
				height : 'auto',
				'max-height' : '100%'
			});
			initialize();
		}); */
		
		$("#add").on("shown.bs.modal", function () {
			initialize();
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
	<!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

	<div class="content">
		<div
			style="position: absolute; margin-top: 15px; margin-left: 10px; font-size: larger; color: #003da5;">
			<b>Cotizaci&oacute;n # <span id="cotizacion_id"></span></b> 
			<input type="hidden" value="" id="cotizacionDetalleId"><br> 
			<input type="hidden" value="" id="numeroDireccion"><br>
		</div>
		<br /> <br /> <br />
		<div align="center">
		<button type="button" class="btn btn-primary" id="guardarAprobado"
							onclick='grabarInspeccion("9")' data-dismiss="modal">Riesgo sugerido</button>
			<button type="button" class="btn btn-primary" id="guardarNoAprobado"
							onclick='grabarInspeccion("8")' data-dismiss="modal">Riesgo NO sugerido</button>
						
		</div>
	</div>
</body>
</html>
