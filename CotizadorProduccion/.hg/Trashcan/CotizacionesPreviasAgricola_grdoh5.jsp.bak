<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<title>Cotizaciones Agricolas Carga Previa - CotizadorQBE</title>

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
		
		
		
		</style>
		<script>

	//eventos de objetos
	var editoVehiculo = false;
	var tieneDescuento = false;
	var cargadoDatosUPLA = false;
	var solicitarInspeccionArr = [];	
	$(document).ready(function() {
		cargarAgricola();
				
		//$('#celular').prop('disabled', true);
		//$('#email').prop('disabled', true);
		$('#ubicacion_provincia').prop('disabled', true);
		$("#ubicacion_Canton").prop('disabled', true);
		//$("#ubicacion_Parroquia").prop('disabled', true);
		$("#agricultor_tecnificado_si").prop('disabled', true);
		$("#agricultor_tecnificado_no").prop('disabled', true);
		$("#tiene_riego_si").prop('disabled', true);
		$("#tiene_riego_no").prop('disabled', true);
		$("#tiene_asistencia_si").prop('disabled', true);
		$("#tiene_asistencia_no").prop('disabled', true);
		//$("#ubicacion_altitud").prop('disabled', true);
		
		$("#identificacion").change(function(){
			if($("#identificacion").val().length >= 10)
				cargarTodas();
		});
		
	});
	
</script>

</head>
	<body>
    		<%
			// Permitimos el acceso si la session existe		
				if(session.getAttribute("login") == null){
				    response.sendRedirect("/CotizadorWeb");
				}
			%>
        <!--[if lt IE 7]>
            <p class="chromeframe">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> or <a href="http://www.google.com/chromeframe/?redirect=true">activate Google Chrome Frame</a> to improve your experience.</p>
        <![endif]-->

	<div class="content">
		<div
			style="position: absolute; margin-top: 15px; margin-left: 10px; font-size: larger; color: #003da5;"><b>Cot. # <span id="cotizacion_id"></span></b>
		</div>
		<form id="wizard" action="" method="post">
			<input type="hidden" id="cargaPreviaId">
			<h2>Cliente <i class="fa fa-child fa-2x"></i></h2>
			<section>
				<!-- **************************************************************************************************************************
		        *                   F O R M U L A R I O   P A R A   E L   I N G R E S O  D A T O S   G E N E R A L E S
		        ****************************************************************************************************************************-->
				<fieldset class="border">
					<legend class="border">Datos sobre Producto, P&oacute;liza y Cliente</legend>
					<div class="alert alert-danger alert-error" id="mensaje_exito_input_vehiculo" style="display: none;">Por favor ingrese los datos faltantes.</div>
					<!-- Inicio datos del producto cerrado -->
					<div class="panel panel-primary" style="display: none">
						<div class="panel-heading">Seleccione el producto</div>
						<div class="panel-body">
							<table>
								<tr>
									<td style="width: 15%"><label><b>Grupo de Productos:*</b></label></td>
									<td style="width: 35%"><select name="grupo_productos" id="grupo_productos" required="required" class="datosGanadero"></select></td>
									<td style="width: 15%"><label><b>Producto:*</b></label></td>
									<td style="width: 35%"><select name="productos" id="productos" required="required" class="datosGanadero"></select></td>
									<td style="width: 35%"><input type="hidden" id="codigoProductos" name=""></td>
									
								</tr>
								<!--  <tr>
									<td style="width: 15%"><label><b>Tasa:*</b></label></td>
									<td style="width: 35%"><input type="text" name="tasa" id="tasa" disabled="disabled"><select name="tasas" id="tasas" style="display: none;"></select></td>
									<td style="width: 15%"></td>
									<td style="width: 35%"></td>
									
								</tr> -->
							</table>
						</div>
					</div>

					<!-- Inicio datos poliza - 4 columnas -->
					<div class="panel panel-primary" style="display: none">
						<div class="panel-heading">Datos de la P&oacute;liza</div> 
						<div class="panel-body">
							<table>
								<tr>
									<td style="width: 15%"><label><b>Canal:*</b></label></td>
									<td style="width: 35%"><select name="canal" id="canal" required="required" class="datosGanadero obligatoriosTarifacion"></select></td>
									<!-- <td style="width: 15%"><label><b>Vigencia de la p&oacute;liza:*</b></label></td>
									<td style="width: 35%"><select name="vigencia" id="vigencia" required="required" class="datosGanadero"></select></td> -->
									<input type="hidden" id="canalSeleccionado">
								</tr>
								<tr>
									<td style="width: 15%"><label><b>Punto de Venta:*</b></label></td>
									<td style="width: 35%"><select name="punto_venta" id="punto_venta" required="required" class="datosGanadero obligatoriosTarifacion"></select></td>
									<!-- <td style="width: 15%"><label><b>Vigencia de la p&oacute;liza:*</b></label></td>
									<td style="width: 35%"><select name="vigencia" id="vigencia" required="required" class="datosGanadero"></select></td> -->
									<input type="hidden" id="puntoVentaSeleccionado">
								</tr>
								<tr>
									<td style="width: 15%" colspan="1"><label><b>Agente de Seguros:*</b></label></td>
									<td style="width: 85%" colspan="3"><select name="agentes" id="agentes" class="datosGanadero" required="required"></select></td>
								</tr>
							</table>
						</div>
					</div>
					<!-- Fin datos poliza-->
					<!-- Inicio datos cliente - 4 columnas -->
					<div class="panel panel-primary">
						<div class="panel-heading">Datos del Solicitante</div>
						<div class="panel-body">
							<table>
								<tr>
									<td style="width: 15%"><label><b>Tipo Identificaci&oacute;n:*</b></label></td>
									<td style="width: 35%"><select class="tipo_identificacion datosGanadero" id="tipo_identificacion_principal" required="required"></select>
									<br/>
									<label><b>Contribuyente Especial:*</b></label>
									<input type="checkbox" id="es_contribuyente" style="display:none;"/>									
									</td>
									<td style="width: 15%"><label><b>Identificaci&oacute;n:*</b></label></td>
									<td style="width: 35%"><input type="text" id="identificacion" class="datosGanadero" name="identificacion" required="required" maxlength="20" onchange="cargarPorIdentificacion('datosClienteInicio', this.value)"> <div id="loading_identificacion" class="loading_identificacion" hidden="">
											<span id="loading-msg">Cargando...</span><img src="../static/images/ajax-loader.gif" />
										</div></td>
									<td style="width: 35%"><input type="hidden" id="codigoClienteEnsurance" name="codigoClienteEnsurance"></td>
									<td style="width: 35%"><input type="hidden" id="codigoEntidadEnsurance" name="codigoEntidadEnsurance"></td>
								</tr>
								<tr id="filaNatural">
									<td style="width: 15%"><label><b>Nombres:*</b></label></td>
									<td style="width: 35%"><input type="text" id="nombres" class="datosGanadero" name="nombres" required="required"></td>
									<td style="width: 15%"><label><b>Apellidos:*</b></label></td>
									<td style="width: 35%"><input type="text" id="apellidos" class="datosGanadero" name="apellidos" required="required"></td>
								</tr>
								<tr id="filaJuridica" hidden="true">
									<td style="width: 15%" colspan="1"><label><b>Nombres Empresa:*</b></label></td>
									<td style="width: 35%" colspan="3"><input type="text" id="nombre_completo" class="datosGanadero" name="nombre_completo" required="required"></td>
								</tr>								
								<tr>
									<td style="width: 15%"><label><b>E-mail:*</b></label></td>									
									<td style="width: 35%"><input type="email" id="email" class="datosGanadero" name="email" ></td>
									<td style="width: 15%"><label><b>No. Teléfono:</b></label></td>
									
									<td style="width: 35%"><input type="text" id="telefono" class="datosGanadero" name="telefono" onkeypress="return justNumbers(event);"></td>
								</tr>
								<tr>
									<td style="width: 15%"><label><b>No. Celular:*</b></label></td>
									<td style="width: 35%"><input type="text" id="celular" class="datosGanadero" name="celular" required="required" onkeypress="return justNumbers(event);"></td>
								</tr>
							</table>
							
						</div>
					</div>
					<!-- Fin datos cliente -->		
					
					<!-- Inicio pre cotizaciones -->
					<div class="panel panel-primary" style="display: none" id="preCotizaciones">
						<div class="panel-heading">Datos de la P&oacute;liza</div> 
						<div class="panel-body">
							<table class="table table-striped table-bordered table-hover"
								id="dataTable" style="font-size: x-small; max-width: 800px;">
								<thead>
									<tr>
										<th>NOMBRES</th>
										<th>IDENTIFICACI&Oacute;N</th>
										<th>MONTO ASEGURADO</th>
										<th>FECHA SOLICITUD</th>
										<th>FECHA SIEMIEMBRA </th>
										<th>CULTIVO</th>
										<th>HECTAREAS ASEGURABLES</th>
										<th>HECTAREAS LOTE</th>							
										<th>PROVINCIA</th>
										<th>CANT&Oacute;N</th>				
										<th></th>
									</tr>
								</thead >
								<tbody>							
								</tbody>
							</table>
						</div>
					</div>
					<!-- Fin pre cotizaciones-->			
					
				</fieldset>
			</section>
			<h2> Producto <i class="fa fa-picture-o fa-2x"></i></h2>			
			<section>
			<!--***********************************************************************************************
                             G E N E R A C I O N   D E   L A  F I C H A   D E  A G R I C O L A
            ************************************************************************************************-->
			<fieldset class="border">
				<legend>Datos para seguro Agricola</legend>
				<div class="alert alert-danger" id="msgPopupFichaAgricolaError"
					style="display: none;">
					<button type="button" class="close" data-dismiss="alert">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					
				</div>
				
				<div class="panel panel-primary">
					<div class="panel-heading">1 - Ubicación del Cultivo</div>
					<div class="panel-body">
						<table>
							<tr>
								<td><label><b>Provincia:*</b></label></td>
								<td style="width: 35%"><select id="ubicacion_provincia"  required="required" class="datosGanadero" readonly></select></td>
								<td><label><b>Cantón:*</b></label></td>
								<td style="width: 35%"><select id="ubicacion_Canton" required="required" class="datosGanadero"></select></td>
								<td><label><b>Parroquia:*</b></label></td>
								<td style="width: 30%"><select id="ubicacion_Parroquia" class="datosGanadero"></select></td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td><label><b>Sitio/Referencia:*</b></label></td>
								<td colspan="5"><input type="text" id="ubicacion_direccion" class="datosGanadero" required="required"></td>
								<input type="hidden" id="previaId" class="datosGanadero">
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td><label><b>Latitud:</b></label></td>
								<td><input type="number" value='0' id="ubicacion_latitud" class="datosGanadero"></td>
								<td><label><b>Longitud:</b></label></td>
								<td><input type="number" value='0' id="ubicacion_longitud" class="datosGanadero"></td>
							</tr>
						</table>
					</div>
				</div>
				<div class="panel panel-primary">
					<div class="panel-heading">2 - Datos del Crédito</div>
					<div class="panel-body">
						<table>
							<tr>
								<td>
									<b>Fecha del crédito:*</b>
								</td>
								<td>
									<input type="date" id="fecha_credito" class="datosGanadero" required="required" readonly>
								</td>
								<td>
									<b>Monto del crédito:*</b>
								</td>
								<td>
									<input type="number" id="monto_credito" value='0' min='0' class="datosGanadero" style="width: 150px" required="required" readonly onkeypress="return justNumbers(event);">
								</td>
							</tr>
							
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<!-- <tr>
								<td>
									<b>Sucursal:*</b>
								</td>
								<td>
									<select id="sucursal_canal"  required="required" class="datosGanadero" style="width: 400px;"></select>
								</td>
							</tr> -->
						</table>
					</div>
				</div>
				<div class="panel panel-primary">
					<div class="panel-heading">3 - Datos del Cultivo</div>
					<div class="panel-body">
						<table style="width: 100%">
							<tr>
								<td style="width: 20%"><label><b>Tipo Cultivo:*</b></label></td>
								<td style="width: 80%" colspan="3"><select id="tipo_cultivo"  required="required" class="datosGanadero" style="width: 400px;" readonly></select></td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<!-- <tr>
								<td style="width: 20%"><label><b>Variedad:</b></label></td>
								<td style="width: 80%" colspan="3"><input type="text" id="variedad_cultivo" class="datosGanadero" style="width: 400px;" readonly></td>
							</tr> -->
							<!--<tr id="cultivoPerenne" style="visibility: hidden;">
								<td><b>Tipo de Seguro:</b></td>
								<td><select id="tipo_poliza"  required="required" class="datosGanadero">
									<option value="1">Asegurar Cultivo Completo</option>
									<option value="2">Asegurar Mantenimiento Anual</option>
								</select></td>
								<td>Años del Cultivo:</td>
								<td>
									<input type="number" value='0' min='0' id="anios_cultivo" style="width: 100px" class="datosGanadero" required="required" onkeypress="return justNumbers(event);">
								</td>
							</tr>
							<tr id="cultivoPerenne_blank" style="visibility: hidden;">
								<td>
									&nbsp;
								</td>
							</tr>-->
							<tr>
								<td>
									<b>Total hectáreas del lote:*</b>
								</td>
								<td>
									<input type="number" id="hectareas_lote" min='1' style="width: 150px" class="datosGanadero" required="required" readonly onkeypress="return justNumbers(event);">
								</td>
								<td>
									<b>Hectáreas asegurables:*</b>
								</td>
								<td>
									<input type="number" min='1' id="hectareas_asegurables" style="width: 150px" class="datosGanadero" required="required" onkeypress="return justNumbers(event);">
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td>
									<b>Fecha prevista de la siembra:*</b>
								</td>
								<td>
									<input type="date" id="fecha_siembra" class="datosGanadero" required="required">
								</td>
								<!-- <td><label><b>Altitud(m.s.n.m.):*</b></label></td>
								<td><input type="number" min='0' max='6000' id="ubicacion_altitud" class="datosGanadero form-control bfh-number" required="required" value="0" onkeypress="return justNumbers(event);" readonly="readonly"></td>
								 -->
							</tr>							
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td>
									<b>Agricultor tecnificado:</b>
								</td>
								<td>
									<table style="width: 50%">
										<tr>
											<td>Si</td>
											<td><input type="radio" id="agricultor_tecnificado_si" name="agricultor_tecnificado" class="datosGanadero" readonly></td>
											<td>No</td>
											<td><input type="radio" id="agricultor_tecnificado_no" name="agricultor_tecnificado" class="datosGanadero" readonly></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td>
									<b>Tiene Riego:</b>
								</td>
								<td>
									<table style="width: 50%">
										<tr>
											<td>Si</td>
											<td><input type="radio" id="tiene_riego_si" name="tiene_riego" class="datosGanadero" readonly></td>
											<td>No</td>
											<td><input type="radio" id="tiene_riego_no" name="tiene_riego" class="datosGanadero" readonly></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
								
							</tr>							
							<tr>
								<td>
									<b>Dispone asistencia:</b>									
								</td>								
								<td>
									<table style="width: 50%">
										<tr>
											<td>Si</td>
											<td><input type="radio" id="tiene_asistencia_si" name="tiene_asistencia" class="datosGanadero" readonly></td>
											<td>No</td>
											<td><input type="radio" id="tiene_asistencia_no" name="tiene_asistencia" class="datosGanadero" readonly></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td>
									<b>Observaciones:</b>
								</td>
								<td colspan="3">
									<textarea id="observacionCotizacion" rows="4" cols="1000" style="width: 100%; max-height: 100px" maxlength="200"> 
									</textarea>
								</td>								
							</tr>							
						</table>						
					</div>
				</div>
			</fieldset> 	

			</section>

			<h2> Pago <i class="fa fa-credit-card fa-2x"></i></h2>
			<section>

				<div class="panel panel-primary">
					<div class="panel-heading">
						Informaci&oacute;n de la Prima
						<span id="resumenTotalPago">Total a Pagar: <b></b></span>
					</div>
					<div class="panel-body">
						<input type="hidden" id="fechaInicialPagos" readonly="readonly" />
						<input type="hidden" id="codigoPagoRegistrado" value="-1"
							readonly="readonly" />
						<input type="hidden" id="estadoCotizacion" value=""
							readonly="readonly" />						
						
						<table>
							<tbody><tr>
								<!-- <td colspan="2"><select id="selectDescargas" onchange="cambioDescargaCertificados();">
									<option value='3'> El formato de débito bancario. </option>
									<option value='4'> El formato del formulario de la UPLA </option>
									<option value='5'> Formato de la Póliza </option>
								</select>
								</td> -->
								<td colspan="2"> 
								<br>
								<b> <font color="red">DEBE DESCARGAR LA PREPOLIZA PARA PODER TERMINAR LA COTIZACION
								</font></b>
								<br>
								
								<br>
								<button type="button" class="btn btn-success btn-xs descargaCertificado" id="descargar_FichaCotizacion" onclick="generarReporteCotizacion();"> <span class="glyphicon glyphicon glyphicon-download"></span> Descargar </button>
								<button type="button" class="btn btn-success btn-xs descargaCertificado" id="descargar_certificadoCotizacion" onclick="abrirCertificadoCotizacion();" hidden="hidden"> <span class="glyphicon glyphicon glyphicon-download"></span>Descargar </button>
								<button type="button" class="btn btn-success btn-xs descargaCertificado" id="descargar_certificadoNormasParticulares" onclick="abrirCertificadoNormaParticulares();"  hidden="hidden"> <span class="glyphicon glyphicon glyphicon-download"></span>Descargar </button>
								<!-- <button hidden="hidden" type='button' class='btn btn-success btn-xs descargaCertificado' id='descargar_certificadoDebito' onclick="abrirCertificadoDebito();"> <span class='glyphicon glyphicon glyphicon-download'></span>Descargar </button>
								
								</td>
								<!-- <td colspan="2">
								
								<button type='button' class='btn btn-success btn-xs descargaCertificado' id='enviar_certificadoCotizacion' onclick="$('#correos_certificado').dialog( 'open' );"> <span class='glyphicon glyphicon glyphicon-send'></span>Enviar Cotización </button></td>
								 -->
								</tr>							
							<tr height="30px"></tr>
							<tr></tr></tbody></table>
						
						
							<div id="tabbable" class="tabbable">
								<ul class="nav nav-tabs">								
								<li class="active"><a href="#primero" data-toggle="tab">Resumen de Valores</a></li>								
								</ul>								
								<!-- Inicio tab valor a pagar -->
								<div id="primero">
									<br>
									<div class="row">
										<div class="col-md-12">
											<div class="thumbnail" style="padding: 20px;">
												<table id="tabla_detalle_pagos" class="table table-bordered table-hover">
													<tr>
														<td colspan="2" style="text-align: center">Detalle Valor a Pagar</td>
													</tr>
													<tr>
														<td style="text-align: right">Total Suma Asegurada :</td>
														<td><input id="total_suma_asegurada" disabled="disabled" type="text"></input></td>
													</tr>
													<tr style="display: none;" id="filaDescuento">
														<td style="text-align: right">Descuento % :</td>
														<td><input id="porcentaje_descuento"
															disabled="disabled" type="text"></input></td>
													</tr>
													<tr>
														<td style="text-align: right">Prima sin Impuestos :</td>
														<td><input id="prima_sin_impuestos"
															disabled="disabled" type="text"></input></td>
													</tr>
													<tr>
														<td style="text-align: right">Superintendencia Bancos
															3.50% :</td>
														<td><input id="super_bancos" disabled="disabled"
															type="text"></input></td>
													</tr>
													<tr>
														<td style="text-align: right">Seguro Campesino 0.50% :</td>
														<td><input id="seguro_campesino"
															disabled="disabled" type="text"></input></td>
													</tr>
													<!--tr>
														<td style="text-align: right">Recargo Seguro
															Campesino</td>
														<td><input id="recargo_seguro_campesino_vh"
															disabled="disabled" type="text"></input></td>
													</tr-->
													<tr>
														<td style="text-align: right">Derechos de
															Emisi&oacute;n :</td>
														<td><input id="derecho_emision"
															disabled="disabled" type="text"></input></td>
													</tr>
													<tr>
														<td style="text-align: right">Subtotal :</td>
														<td><input id="subtotal" disabled="disabled"
															type="text"></input></td>
													</tr>
													<tr>
														<td style="text-align: right">IVA 14.00% (Excepto M y E 12%):</td>
														<td><input id="iva" disabled="disabled"
															type="text"></input></td>
													</tr>
													<tr>
														<td style="text-align: right">TOTAL :</td>
														<td><input id="total" class="total_vh"
															disabled="disabled" type="text"><input
															id="total" disabled="disabled" type="hidden"></td>
													</tr>
													<tr>
														<p>El valor cotizado puede ser sujeto a variaci&oacute;n al momento de la facturaci&oacute;n de acuerdo a la DISPOSICI&Oacute;N TRANSITORIA PRIMERA DE LA LEY ORG&Aacute;NICA DE SOLIDARIDAD Y DE CORRESPONSABILIDAD CIUDADANA PARA LA RECONSTRUCCI&Oacute;N Y REACTIVACI&Oacute;N DE LAS ZONAS AFECTADAS POR EL TERREMOTO DE 16 DE ABRIL DE 2016 publicada en el registro oficial Suplemento 759 del &iacute;a 20 de mayo del 2016.</p> 
													</tr>

												</table>
											</div>
										</div>
									</div>
								</div>
								<!--  Fin tab valor a pagar -->								
							</div>
					</div>
				</div>
			</section>					
		</form>					
		<!-- Inicio tabla de tabla previa de cotizaciones -->	
	</div>
</body>
</html>
