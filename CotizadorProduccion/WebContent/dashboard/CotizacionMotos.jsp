<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.qbe.cotizador.model.UsuarioRol"%>
<%@page import="com.qbe.cotizador.model.Rol"%>
<%@page import="com.qbe.cotizador.dao.seguridad.UsuarioRolDAO"%>	
<%@page import="com.qbe.cotizador.dao.seguridad.VariableSistemaDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="../static/css/jquery-steps/normalize.css">
	<link rel="stylesheet" href="../static/css/jquery-steps/main.css">
	<link rel="stylesheet" href="../static/css/jquery-steps/jquery.steps.css">
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" href="../static/css/select2/select2.css">
	<link rel="stylesheet" href="../static/css/bootstrap2-toggle/bootstrap2-toggle.min.css">
	
	<script src="../static/js/jquery-steps/modernizr-2.6.2.min.js"></script>
	<script src="../static/js/jquery-steps/jquery.cookie-1.3.1.js"></script>
	<script src="../static/js/jquery-steps/jquery.steps.min.js"></script>
	<script src="../static/js/jquery.validate.js"></script>
	<script src="../static/js/bootstrap2-toggle/bootstrap2-toggle.min.js"></script>

	<script id="tipoObjetoCargaInicial" src="../static/js/vehiculos/carga.inicial.js" tipoObjetoValor="Motos"></script>
	<script id="tipoObjetoMetodos" src="../static/js/vehiculos/metodos.js" tipoObjetoValor="Motos"></script>
	<script src="../static/js/vehiculos/formulados.js"></script>
	<script src="../static/js/cotizador/comun.js"></script>
	<script src="../static/js/vehiculos/tasa.variable.js"></script>
	
	<script src="../static/js/util.js"></script> 
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
    <!-- cdn for modernizr, if you haven't included it already  -->
	<script src="../static/js/modernizr-custom.js"></script>
	<!-- polyfiller file to detect and load polyfills -->
	<script src="../static/js/polyfiller.js"></script>
    
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
		
		input[type="date"]{
			width:90%;
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
		</style>
		<script>

	//eventos de objetos

			var editoVehiculo = false;
			var tieneDescuento = false;
			var cargadoDatosUPLA = false;
			var solicitarInspeccionArr = [];
			var casoEspecial = false;

			var cargandoPorId = false;
			$(document).ready(function() {
				activarMenu("CotizacionVHCerrado");
				activarOpcionesPantallaVH();
				cargar();
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
	<input type="checkbox" id="expandir_pantalla" data-toggle="toggle" data-on="Contraer" data-off="Expandir" data-onstyle="danger" data-offstyle="success" data-size="normal" data-width="110" data-height="30" onchange="expandirPantalla(this);">
		<div
			style="position: absolute; margin-top: 15px; margin-left: 10px; font-size: larger; color: #003da5;"><b>Cotizaci&oacute;n # <span id="cotizacion_id"></span></b>
		</div>
		<form id="wizard" action="" method="post">
			<h2>Cliente <i class="fa fa-child fa-2x"></i></h2>
			<section>
				<!-- **************************************************************************************************************************
		        *                   F O R M U L A R I O   P A R A   E L   I N G R E S O  D A T O S   G E N E R A L E S
		        ****************************************************************************************************************************-->
				<fieldset class="border">
					<legend class="border">Datos sobre Producto, P&oacute;liza y Cliente</legend>
					<div class="alert alert-danger alert-error" id="mensaje_exito_input_vehiculo" style="display: none;">Por favor ingrese los datos faltantes.</div>
					<!-- Inicio datos del producto cerrado -->
					<div class="panel panel-primary">
						<div class="panel-heading">Seleccione el producto</div>
						<div class="panel-body">
							<table>
								<tr>
									<td style="width: 15%"><label><b>Grupo de Productos:*</b></label></td>
									<td style="width: 35%"><select name="grupo_productos" id="grupo_productos" onchange="obtenerProductosGrupo();limpiarTasaProducto();" required="required" class="datosVehiculo"></select></td>
									<td style="width: 15%"><label><b>Producto:*</b></label></td>
									<td style="width: 35%"><select name="productos" id="productos" required="required" onchange="obtenerTasaPorProducto();cargarPuntosVenta();metodoObtenerProductoFormulados(this.value); cargarTablaCoberturas('','','','',$(this).val());" class="datosVehiculo"></select></td>
									<td style="width: 35%"><input type="hidden" id="codigoProductos" name=""></td>
								</tr>
								<tr>
									<td style="width: 15%"><label><b>Tasa:*</b></label></td>
									<td style="width: 35%"><input type="text" name="tasa" id="tasa" disabled="disabled"><select name="tasas" id="tasas" style="display: none;" onchange="metodoValidacionTasas(this.value);"></select></td>
									<td style="width: 15%"></td>
									<td style="width: 35%"></td>
								</tr>
							</table>
							<input type="hidden" id="necesitaInspeccion" name="necesitaInspeccion">
						</div>
					</div>

					<!-- Inicio datos poliza - 4 columnas -->
					<div class="panel panel-primary">
						<div class="panel-heading">Datos de la P&oacute;liza</div>
						<div class="panel-body">
							<table>
								<tr>
									<td style="width: 15%"><label><b>Punto Venta:*</b></label></td>
									<td style="width: 35%"><select name="punto_venta" id="punto_venta" required="required" class="datosVehiculo obligatoriosTarifacion" onchange="verificarPuntosVenta();"></select></td>
									<td style="width: 15%"><label><b>Vigencia de la p&oacute;liza:*</b></label></td>
									<td style="width: 35%"><select name="vigencia" id="vigencia" required="required" class="datosVehiculo"  onchange="cambioVigencia()"></select></td>
								</tr>
								<tr>
									<td style="width: 15%" colspan="1"><label><b>Agente de Seguros:*</b></label></td>
									<td style="width: 85%" colspan="3"><input type="select"  style="width:90%" name="agentes" id="agentes" class="datosVehiculo" onchange="obtenerAgenteComision();" required="required"></td>
								</tr>
								<tr >
							    <td ><label><b>Comisi&oacute;n del agente (%):*</b></label></td>
							    <td><input type="text" id="porc_comision" name="porc_comision" readonly onkeypress="return justNumbers(event);">
							    <select hidden="hidden" id="porc_comision_cb" name="porc_comision_cb" onchange="$('#porc_comision').val($('#porc_comision_cb').val());" required="required" readonly></select></td>
							    <!-- Acuerdos -->
							    <td class="acuerdos" style="display: none;"><label><b>Acuerdo:*</b></label></td>
							    <td class="acuerdos" style="display: none;"><select name="acuerdosListado" id="acuerdosListado" required="required" class="datosVehiculo" ></select></td>
							  </tr>
							</table>
						</div>
					</div>
					<!-- Fin datos poliza-->
					<!-- Inicio datos cliente - 4 columnas -->
					<div class="panel panel-primary">
						<div class="panel-heading">Datos del Cliente</div>
						<div class="panel-body">
							<table>
								<tr>
									<td style="width: 15%"><label><b>Tipo Identificaci&oacute;n:*</b></label></td>
									<td style="width: 35%"><select class="tipo_identificacion datosVehiculo" id="tipo_identificacion_principal" onchange="mostrarTipoIdentificacion(); mostrarAdicionales();" required="required"></select></td>
									<td style="width: 15%"><label><b>Identificaci&oacute;n:*</b></label></td>
									<td style="width: 35%"><input type="text" id="identificacion" class="datosVehiculo" name="identificacion" required="required" maxlength="20" onchange="cargarPorIdentificacion('datosClienteInicio', this.value)" onblur="validadorIdentificaciones();"> <div id="loading_identificacion" class="loading_identificacion" hidden="">
											<span id="loading-msg">Cargando...</span><img src="../static/images/ajax-loader.gif" />
										</div></td>
									<td style="width: 35%"><input type="hidden" id="codigoClienteEnsurance" name="codigoClienteEnsurance"></td>
									<td style="width: 35%"><input type="hidden" id="codigoEntidadEnsurance" name="codigoEntidadEnsurance"></td>
								</tr>
								<tr id="filaNatural">
									<td style="width: 15%"><label><b>Nombres:*</b></label></td>
									<td style="width: 35%"><input type="text" id="nombres" class="datosVehiculo" name="nombres" required="required"></td>
									<td style="width: 15%"><label><b>Apellidos:*</b></label></td>
									<td style="width: 35%"><input type="text" id="apellidos" class="datosVehiculo" name="apellidos" required="required"></td>
								</tr>
								<tr id="filaJuridica" hidden="true">
									<td style="width: 15%" colspan="1"><label><b>Nombres Empresa:*</b></label></td>
									<td style="width: 35%" colspan="3"><input type="text" id="nombre_completo" class="datosVehiculo" name="nombre_completo" required="required"></td>
								</tr>
							</table>
						</div>
					</div>
					<!-- Fin datos cliente -->
				</fieldset>
			</section>
			<h2> Productos <i class="fa fa-bicycle fa-2x"></i></h2>
			<section>
			<!--***********************************************************************************************
                             G E N E R A C I O N   D I N A M I C A   V E H I C U L O S
            ************************************************************************************************-->
			<input type="hidden" id="inspeccionado" name="inspeccionado">
			<input type="hidden" id="numero_vehiculos" name="numero_vehiculos">
			<a id="agregarVehiculonumero"  class="btn btn-primary glyphicon glyphicon-plus datosVehiculo fondo_botones" onclick="agregarVehiculo(); $('#guardarVehiculo'+(Number($('#numero_vehiculos').val())-1)).click()">Agregar un nuevo vehÃ­culo</a> 
			
			<br>
			<br>

			<!--Listado de variables a utilizar -->
			<div id="lista_coberturas" style="display: none;"></div>
			<input type="hidden" id="anio_max_seguro" name="anio_max_seguro">
			<input type="hidden" id="suma_asegurada_valor_tr" name="suma_asegurada_valor_tr"> 
			<input type="hidden" id="monto_fijo_valor_tr" name="monto_fijo_valor_tr">
			<input type="hidden" id="siniestro_valor_tr" name="siniestro_valor_tr">
			<input type="hidden" id="valor_exceso_rc" name="valor_exceso_rc">
			<input type="hidden" id="estado_cotizacion" name="estado_cotizacion">

			<!--Listado variables productos formulados -->
			<div id="listado_formulado" style="display: none;"></div>
			
			<!--Listado de los vehiculos-->

			<fieldset class="border">
			<input type="hidden" id="valorUnicoResponsabilidadCivil">
		    	<div id="coberturas_unico" class="row clearfix" ></div>
		     	<br>
		    	<div id="lista_vehiculos" style="margin-left:10px;margin-right: 20px;"></div>
		    	 <br>
		     </fieldset>
			</section>

			<h2> Pago <i class="fa fa-credit-card fa-2x"></i></h2>
			<section>

				<div class="panel panel-primary">
					<div class="panel-heading">
						Informaci&oacute;n de la Prima
						<div class="panel panel-primary ui-dialog ui-dialog-content ui-dialog-titlebar" id="correos_certificado" title="Correos">
							<div class="panel panel-primary">
								<table class="">
									<tr class="ui-dialog-titlebar" id="correosForm" value="">
										<td><input type="email" id="correoCertificado" class="datosVehiculo"></td>
										<td><button type="button" class="btn btn-primary btn-xs actualizar-btn" id="btnAnadirCorreoCertificado" onclick="agregarCorreoCertificado();">
												<span class="glyphicon glyphicon glyphicon-plus"></span>
												A&ntilde;adir Correo
											</button></td>
									</tr>
								</table>
								<table id="correosCertificado" >
									<tr>
										<th><center>Correos</center></th>
										<th></th>
									</tr>
								</table>
								<button type="button" class="btn btn-success btn-xs " onclick="enviarCertificados();">
												<span class="glyphicon glyphicon glyphicon-send"></span>
												Enviar
											</button><div id="loading_envio_cotizacion" hidden="">
													<span id="loading-msg">Enviando...</span><img
														src="../static/images/ajax-loader.gif" />
												</div>
								<button type="button" class="btn btn-danger btn-xs "  onclick="$('#correos_certificado').dialog('close');">
												<span class="glyphicon glyphicon glyphicon-remove"></span>
												Cerrar
											</button>
							</div>

						</div>

						<span id="resumenTotalPago">Total a Pagar: <b></b></span>
					</div>
					<div class="panel-body">
						<input type="hidden" id="fechaInicialPagos" readonly="readonly" />
						<input type="hidden" id="codigoPagoRegistrado" value="-1"
							readonly="readonly" />
						<div id="loading">
							<div class="loading-indicator">
								<img src="../static/images/ajax-loader.gif" /><br /> <br /> <span
									id="loading-msg">Cargando...</span>
							</div>
						</div>
						
						
						<table >
							<tbody><tr>
								<td > 
								Certificado de la Cotizaci&oacute;n
								</td>
								<td > 								
									<button type='button' class='btn btn-success btn-s ' id='descargar_certificadoBorrador' onclick="abrirCertificadoBorrador();"> <span class='glyphicon glyphicon glyphicon-pencil'></span>P&oacute;liza Borrador </button>
								</td>
								<td > 								
									<button type='button' class='btn btn-success btn-s ' id='descargar_certificadoCotizacion' onclick="abrirCertificado();"> <span class='glyphicon glyphicon glyphicon-download'></span>Descargar Cotizaci&oacute;n </button>
								</td>
								<td >								
									<button type='button' class='btn btn-success btn-s ' id='enviar_certificadoCotizacion' onclick="$('#correos_certificado').dialog( 'open' );"> <span class='glyphicon glyphicon glyphicon-send'></span>Enviar Cotizaci&oacute;n </button></td>								
								</tr>							
							<tr height="30px"></tr>
							<tr></tr></tbody></table>
						
							<div id="tabbable" class="tabbable" style="display: none;">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#primero" data-toggle="tab">Coberturas
										y Valor a Pagar</a></li>
								<li class="inspeccion_ocultar"><a href="#segundo" data-toggle="tab" id="tabInspecciones" class="inspeccion_ocultar">Solicitar
										Inspecci&oacute;n</a></li>
								<li  id="linkDescuentos" class="descuentos_ocultar"><a href="#tercero" data-toggle="tab" class="descuentos_ocultar">Solicitud de
										Descuentos</a></li>
								<li><a href="#cuarto" data-toggle="tab" id="tabFormasPago">Formas
										de Pago</a></li>
								<li><a href="#quinto" data-toggle="tab">Beneficiario y Asegurado</a></li>  
							</ul>
							<div class="tab-content">
								<!-- I n i c  i o   P r i m e r   t a b -->
								<div class="tab-pane active" id="primero">
									<br>
									<div class="row">
										<div class="col-md-4">
											<div class="thumbnail" style="padding: 20px;">
												<table id="tabla_detalle_pagos"
													class="table table-bordered table-hover">
													<tr>
														<td colspan="2"><center>Detalle Valor a Pagar</center></td>
													</tr>
													<tr>
														<td style="text-align: right">Total Suma Asegurada $</td>
														<td><input id="total_suma_asegurada_vh"
															disabled="disabled" type="text"></input></td>
													</tr>
													<tr style="display: none;" id="filaDescuento">
														<td style="text-align: right">Descuento %</td>
														<td><input id="porcentaje_descuento_vh"
															disabled="disabled" type="text"></input></td>
													</tr>
													<tr>
														<td style="text-align: right">Prima sin Impuestos $</td>
														<td><input id="prima_sin_impuestos_vh"
															disabled="disabled" type="text"></input></td>
													</tr>
													<tr>
														<td style="text-align: right">Otros $</td>
														<td><input id="prima_otros_iva"
															disabled="disabled" type="text"></input></td>
													</tr>
													<tr>
														<td style="text-align: right">Otros IVA 0% $</td>
														<td><input id="prima_otros_sin_iva"
															disabled="disabled" type="text"></input></td>
													</tr>
													<tr>
														<td style="text-align: right">Superintendencia Bancos
															3.50%</td>
														<td><input id="super_bancos_vh" disabled="disabled"
															type="text"></input></td>
													</tr>
													<tr>
														<td style="text-align: right">Seguro Campesino 0.50%</td>
														<td><input id="seguro_campesino_vh"
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
															Emisi&oacute;n $</td>
														<td><input id="derecho_emision_vh"
															disabled="disabled" type="text"></input></td>
													</tr>
													<tr>
														<td style="text-align: right">Subtotal $</td>
														<td><input id="subtotal_vh" disabled="disabled"
															type="text"></input></td>
													</tr>
													<tr>
														<td style="text-align: right">IVA <span class="valor_iva"></span>%</td>
														<td><input id="iva_vh" disabled="disabled" type="text"></input></td>
													</tr>
													<tr>
														<td style="text-align: right">Compensaci&oacute;n del IVA <span class="valor_compensacion"></span>%</td>
														<td><input id="valor_compensacion_vh" disabled="disabled" type="text"></input></td>
													</tr>
													<tr>
														<td style="text-align: right">TOTAL $</td>
														<td><input id="total_vh" class="total_vh"
															disabled="disabled" type="text"><input
															id="total_vh_origin" disabled="disabled" type="hidden"></td>
													</tr>

												</table>
											</div>
										</div>
										<div class="col-md-8">
											<div class="thumbnail" style="padding: 20px;">
												<table id="tabla_vehiculos_cotizacion"
													class="table table-bordered table-hover dataTable"></table>												
											</div>
										</div>
									</div>


								</div>
								<!--  F i n   P r i m e r   t a b -->

								<!-- I n i c i o   S e g u n d o   t a b -->
								<div class="tab-pane inspeccion_ocultar" id="segundo">
									<!--  I N S P E C C I O N   -->
									<br>
									<!-- ---------------------------------------------------------------------------------------------------------------------- -->
									<div class="alert alert-danger" id="msgPopupInspeccionError"
										style="display: none;">
										<button type="button" class="close" data-dismiss="alert">
											<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
										</button>
										Por favor solicite una inspecci&oacute;n!.
									</div>
									<div class="alert alert-danger" id="msgPopupPlacaError"
										style="display: none;">
										<button type="button" class="close" data-dismiss="alert">
											<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
										</button>
										Por favor ingrese una placa!
									</div>

									<div class="alert alert-success" id="msgPopupInspeccionGrabo"
										style="display: none;">
										<button type="button" class="close" data-dismiss="alert">
											<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
										</button>
										Su solicitud de inspeccion se ha procesado correctamente.
									</div>
									<table style="width: 300px;" align="center">
										<tr>
											<td><input type="hidden" id="solicitudInspeccionId"
												value=""><label><b>Tipo de inspecciÃ³n:</b></label></td>
											<td><select id="tipoInspeccion" name="tipoInspeccion"
												onchange="cambioTipoInspeccion(this.value);"
												style="width: 150px" class="datosVehiculo">
													<option value="">Seleccione una opcion</option>
													<option value="interna">Interna</option>
													<option value="externa">Externa</option>
											</select></td>
										</tr>
									</table>

									<table class='table table-bordered table-hover'
										style="display: none;" id="forma_inspeccion_interna">
										<tr>
											<td style="text-align: left;" colspan="2">Seleccione el Inspector:*</td>
											<td colspan="3"><select id="inspectorInterno"
												class="requeridoInspeccion datosVehiculo" style="width: 150px"></select>
											</td>
												
										</tr>
										<tr id="estadoActualSolicitudInspeccionInterna" hidden="hidden">
											<td colspan="2" >Estado:</td>
											<td colspan="3" id="textoEstadoInspeccionInterna"></td>
										</tr>										
										<tr>
											<td colspan="5" align="center"><button type="button"
													class="btn btn-primary "
													id="save-inspeccion-interna bloquearEmitido"
													onclick='guardarSolicitudInspeccionInterna()'>Guardar</button>
												<button type="button"
													class="btn btn-primary " hidden=""
													id="actualizar-estado-solicitud-inspeccion-interna"
													onclick='cargarEstadoInspeccion()'>Actualizar
													Estado</button>
													<div id="loading_solicitud_inspeccionInterna" hidden="">
													<span id="loading-msg">Solicitando...</span><img
														src="../static/images/ajax-loader.gif" /></td>
										</tr>
									</table>

									<table class='table table-bordered table-hover'
										style="display: none;" id="forma_inspeccion_externa">
										<tr>
											<td style="text-align: left;" colspan="2">Telefono de
												Contacto:*</td>
											<td colspan="3"><input id="numContactoInspeccion"
												type="text" onkeypress="return justNumbers(event);"
												required="required" name="numContactoInspeccion"
												class="requeridoInspeccion datosVehiculo" maxlength="10"></td>
										</tr>
										<tr>
											<td style="text-align: left;" colspan="2">Zona InspecciÃ³n:*</td>
											<td colspan="3"><select id="zonaInspeccion"
												class="requeridoInspeccion datosVehiculo" style="width: 150px">
												<option value="">Seleccione una zona</option>
												<option value="1">Urbana</option>
												<option value="2">Rural</option>
												</select></td>
										</tr>
										<tr>
											<td style="text-align: left;" colspan="2">Lugar
												InspecciÃ³n:*</td>
											<td colspan="3"><select id="origenInspeccion"
												name="origenInspeccion"
												onchange="cargarDestinoInspeccion(this.value)"
												class="requeridoInspeccion datosVehiculo" style="width: 150px"></select></td>
										</tr>
										<tr>
											<td style="text-align: left;" colspan="2">Destino
												InpecciÃ³n:*</td>
											<td colspan="3"><select id="destinoInspeccion"
												name="destinoInspeccion"
												onchange="cargarProveedoresDisponibles(this.value)"
												class="requeridoInspeccion datosVehiculo" style="width: 150px"></select></td>
										</tr>
										<tr id="estadoActualSolicitudInspeccion" hidden="hidden">
											<td colspan="2" >Estado:</td>
											<td colspan="3" id="textoEstadoInspeccion"></td>
										</tr>
										<tbody id="detalleInspectoresDisponibles">

										</tbody>
										<tr>
											<td colspan="5" align="center"><button type="button"
													class="btn btn-primary save-inspeccion bloquearEmitido"
													id="save-inspeccion" onclick='guardarSolicitudInspeccion()'>Guardar</button>
													<button type="button"
													class="btn btn-primary save-inspeccion bloquearEmitido" hidden="hidden"
													id="boton-actualizar-inspeccion bloquearEmitido" onclick='cargarEstadoInspeccion()'>Actualizar Estado</button>
													<div id="loading_solicitud_inspeccion" hidden="">
													<span id="loading-msg">Solicitando...</span><img
														src="../static/images/ajax-loader.gif" /></td>
										</tr>
										
									</table>
									<!-- ---------------------------------------------------------------------------------------------------------------------- -->
									
											<table class='table table-bordered table-hover'
										style="display: none;" id="forma_inspeccion_interna">										
										<tr id="estadoActualSolicitudInspeccionInterna" hidden="hidden">
											<td colspan="2" >Estado:</td>
											<td colspan="3" id="textoEstadoInspeccionInterna"></td>
										</tr>

										<tr>
											<td colspan="5" align="center"><button type="button"
													class="btn btn-primary  bloquearEmitido"
													id="save-inspeccion-interna"
													onclick='guardarSolicitudInspeccionInterna()'>Guardar</button>
												<button type="button"
													class="btn btn-primary " hidden=""
													id="actualizar-estado-solicitud-inspeccion-interna"
													onclick='cargarEstadoInspeccion()'>Actualizar
													Estado</button>
													<div id="loading_solicitud_inspeccionInterna" hidden="">
													<span id="loading-msg">Solicitando...</span><img
														src="../static/images/ajax-loader.gif" /></td>
										</tr>

									</table>
									<!-- ---------------------------------------------------------------------------------------------------------------------- -->
									
									
								</div>

								<!-- Fin Segundo tab-->
								<!-- inicio Tercero tab-->
								<div class="tab-pane descuentos_ocultar" id="tercero">
									<br>
									<table class="table table-striped table-bordered table-hover"
										id="dataTable">
										<thead>
											<tr>
												<th>Descuento</th>
												<th></th>
											</tr>
										</thead>
										<tbody id="descuentos">
											<tr>
												<td style="text-align: right">Tipo de Descuento:*</td>
												<td><select id="codigo_descuento" class="datosVehiculo"
													onChange="cambiaDescuento()"></select></td>
											</tr>
											<tr>
												<td style="text-align: right">Motivo Descuento:*</td>
												<td><select id="motivo_descuento" class="datosVehiculo"></select></td>
											</tr>
											<tr>
												<td style="text-align: right">Porcentaje:*</td>
												<td><input id="porcentaje_solicitud_descuento"
													step='0.1' type="number" class="datosVehiculo"></input></td>
											</tr>
											<tr>
												<td style="text-align: right">Descripci&oacute;n:*</td>
												<td><textarea id="descripcion_motivo_descuento"
														style="width: 90%" rows="10" class="datosVehiculo" maxlength="500"></textarea></td>
											</tr>
											<tr id='estado_solicitud_descuento'>
												<td style="text-align: right">Estado</td>
												<td id="texto_estado_descuento">Pendiente</td>
											</tr>
											<tr>
												<td style="text-align: right"></td>
												<td><button type='button'
														class='btn btn-success btn-xs bloquearEmitido'
														id='enviar_solicitud_descuento'
														onclick='confirmarDescuento(event)'>
														<span class='glyphicon glyphicon glyphicon-edit'></span>Enviar
													</button><button type='button'
														class='btn btn-success btn-xs bloquearEmitido'
														id='boton_actualizar_solicitud_descuento' hidden=""
														onclick='cargarEstadoDescuento(event)'>
														<span class='glyphicon glyphicon glyphicon-refresh'></span>Actualizar Estado
													</button><div id="loading_solicitud_descuento" hidden="">
													<span id="loading-msg">Enviando...</span><img
														src="../static/images/ajax-loader.gif" />
												</div></td>
											</tr>
										</tbody>
									</table>
									<!-- F i n   T e r c e r o   t a b -->

									<!-- I n i c i o   C u a r t o   t a b -->
								</div>
								<!-- I n i c i o   C u a r t o   t a b -->
								<div class="tab-pane" id="cuarto">
									<br>
									<!--  F O R M A S   D E   P A G O   -->
									<table style="width: 300px;" align="center">
										<tr>
											<td><label><b>Forma de Pago:*</b></label></td>
											<td><select id="cboFpFormaPago" name="cboFpFormaPago" class="datosVehiculo"
												onchange="cambioFormaPago(this.value);" style="width: 150px">
											</select></td>
										</tr>
										<tr>
										<div class="alert" id="msgPopupPago" hidden="hidden">
											<div id='mensajeErrorEndosoBeneficiario'></div>
										</div>
										</tr>
										<tr style="height: 20px;"></tr>
										<tr>
											<td colspan="2" align="center"><button type="button"
													class="btn btn-primary save-pago bloquearEmitido" id="save-pagoContado"
													onclick='guardarPago("contado")'>Guardar</button></td>
										</tr>
									</table>
									<br>

									<!-- *********************************************************************************************************************
														F O R M U L A R I O   P A R A   E L   I N G R E S O   D E   D E B I T O S   B A N C A R I O S 
											     ******************************************************************************************************************-->
									<table class='table table-bordered table-hover'
										style="display: none;" id="forma_debitos">
										<tr>
											<td>Tipo de identificaci&oacute;n del titular Cuenta<b>*</b></td>
											<td><select id="tipo_identificacion_banco"
												class="tipo_identificacion datosVehiculo"
												name="cboFpTipoIdentificacionBanco" required="required" onchange="cambiarIdentificacion('debito',this.value);"></select></td>
										</tr>
										
										<tr>
											<td>CÃ©dula/Ruc del titular Cuenta<b>*</b></td>
											<td><input id="bancoIdentificacion" type="text" class="datosVehiculo"
												required="required" onkeypress=""
												onchange="cargarPorIdentificacion('formasPagoDebitoBancario', this.value);"
												name="txtIdentificacionBanco">
												<div class="loading_identificacion" hidden="">
													<span id="loading-msg">Cargando...</span><img
														src="../static/images/ajax-loader.gif" />
												</div></td>
											</td>
										</tr>
										<tr>
											<td>Titular Cuenta<b>*</b></td>
											<td id="filaNaturalDebitos">																					
												Nombres *:&nbsp<input id="nombresTitularDebitos" type="text" placeholder="" style="text-transform: uppercase;display: inline;width:35%;" class="datosVehiculo" required="required" maxlength="400">
												Apellidos *:&nbsp<input id="apellidosTitularDebitos" type="text" placeholder="" style="text-transform: uppercase;display: inline;width:35%;" class="datosVehiculo" required="required" maxlength="45">
											</td>
											<td id="filaJuridicaDebitos" hidden="true">																					
												Nombre Completo *:&nbsp<input id="nombreCompletoTitularDebitos" type="text" placeholder="" style="text-transform: uppercase;display: inline;width:70%;" class="datosVehiculo" required="required" maxlength="400">
											</td>
										</tr>
										<tr>
											<td style="text-align: left;">Banco<b>*</b></td>
											<td><select id="bancoId" name="cboFpBanco" class="datosVehiculo"></select></td>
										</tr>
										<tr>
											<td>Tipo Cuenta<b>*</b></td>
											<td><select id="bancoTipoCuenta" name="cboFpTipo"
												required="required" class="datosVehiculo">
													<option value="A">Ahorro</option>
													<option value="C">Corriente</option>
											</select></td>
										</tr>
										<tr>
											<td>NÃºmero Cuenta<b>*</b></td>
											<td><input id="bancoNumeroCuenta" type="text"
												class="camporequerido validate-not-first datosVehiculo"
												onkeypress="return justNumbers(event);"
												onchange="validacionBanco(); validaCuenta();"
												required="required" name="txtFpNumero"></td>
										</tr>
										<tr>
											<td>Fecha inicio de pagos<b>*</b></td>
											<td><input id="txtfechaInicialpago"
														name="txtfechaInicialpago" type="date"></td>
										</tr>
										<tr>
											<td>Cuota Inicial<b>*</b></td>
											<td><input id="txtcuotaInicialbancoPlazo" class="datosVehiculo" type="number" value="0" required="required" onkeypress="return justNumbers(event);" name="txtcuotaInicial" onchange="validarValoresPagos()" aria-required="true"></td>
										</tr>
										<tr>
											<td>Plazo<b>*</b></td>
											<td><select id="bancoPlazo" name="cboFpPlazo" onchange="validarValoresPagos()" class="datosVehiculo"
												required="required">
													<option value="1">1 mes</option>
													<option value="2">2 meses</option>
													<option value="3">3 meses</option>
													<option value="4">4 meses</option>
													<option value="5">5 meses</option>
													<option value="6">6 meses</option>
													<option value="7">7 meses</option>
													<option value="8">8 meses</option>
													<option value="9">9 meses</option>
													<option value="10">10 meses</option>
											</select></td>
										</tr>
										<tr>
											<td colspan="2" align="center"><button type="button"
													class="btn btn-primary save-pago bloquearEmitido" id="save-pagoDebitoBanco"
													onclick='guardarPago("banco");ponerFocoAlHacerClic();'>Guardar</button></td>
										</tr>
									</table>


									<!-- *********************************************************************************************************************
													     F O R M U L A R I O   P A R A   E L   I N G R E S O   D E   D E B I T O S   C O N   T A R J E T A S
											     ******************************************************************************************************************-->
									<table class='table table-bordered table-hover'
										style="display: none;" id="forma_tarjeta">
										<tr>
											<td>Tipo de identificaci&oacute;n del titular Tarjeta<b>*</b></td>
											<td><select id="tipo_identificacion_tarjeta"
												class="tipo_identificacion datosVehiculo"
												name="cboFpTipoIdentificacionTarjeta" required="required" onchange="cambiarIdentificacion('tarjeta',this.value);"></select>
											</td>
										</tr>
										<tr>
											<td>CÃ©dula/Ruc del titular Tarjeta<b>*</b></td>
											<td><input id="tarjetaIdentificacion" type="text" class="datosVehiculo"
												required="required" onkeypress="return justNumbers(event);"
												title="Ingrese la CÃ©dula/RUC del titular"
												onchange="cargarPorIdentificacion('formasPagoDebitoTarjetas', this.value);"
												name="txtIdentificacionTarjeta">
												<div id="loading_identificacion"
													class="loading_identificacion" hidden="">
													<span id="loading-msg">Cargando...</span><img
														src="../static/images/ajax-loader.gif" />
												</div></td>
											</td>
										</tr>
										<tr>
											<td>Titular Tarjeta<b>*</b></td>
											<td id="filaNaturalTarjetas">																					
												Nombres *:&nbsp<input id="nombresTitularTarjetas" type="text" placeholder="" style="text-transform: uppercase;display: inline;width:35%;" class="datosVehiculo" required="required" maxlength="400">
												Apellidos *:&nbsp<input id="apellidosTitularTarjetas" type="text" placeholder="" style="text-transform: uppercase;display: inline;width:35%;" class="datosVehiculo" required="required" maxlength="45">
											</td>
											<td id="filaJuridicaTarjetas" hidden="true">																					
												Nombre Completo *:&nbsp<input id="nombreCompletoTitularTarjetas" type="text" placeholder="" style="text-transform: uppercase;display: inline;width:70%;" class="datosVehiculo" required="required" maxlength="400">
											</td>
										</tr>
										<tr>
											<td>NÃºmero Tarjeta<b>*</b></td>
											<td><input id="tarjetaNumero" type="text" class="datosVehiculo"
												required="required" onpaste="return false"
												placeholder="Ingrese el nÃºmero de tarjeta"
												onkeypress="return validarEmisorTarjeta(event, this.value);"
												onchange="comprobarTarjetaValida(this.value)"
												name="txtFpNumero" maxlength="16"> <br> <span></span>
											</td>
										</tr>										
										<tr>
											<td>Tarjeta<b>*</b></td>
											<td><select id="tarjetaId" name="cboFpBanco" class="datosVehiculo"
												required="required" disabled></select> <br> <img
												id="dinersIcon" class="tarjetasIconos" nemotecnico="diners"
												src="../static/images/diners_club_32.png"
												style="opacity: 0.2; filter: alpha(opacity = 20);" /> <img
												id="discoverIcon" class="tarjetasIconos"
												nemotecnico="discover"
												src="../static/images/discover_32.png"
												style="opacity: 0.2; filter: alpha(opacity = 20);" /> <img
												id="visaIcon" class="tarjetasIconos" nemotecnico="visa"
												src="../static/images/visa_32.png"
												style="opacity: 0.2; filter: alpha(opacity = 20);" /> <img
												id="mastercardIcon" class="tarjetasIconos"
												nemotecnico="mastercard"
												src="../static/images/mastercard_32.png"
												style="opacity: 0.2; filter: alpha(opacity = 20);" /> <img
												id="amexIcon" class="tarjetasIconos" nemotecnico="amex"
												src="../static/images/american_express_32.png"
												style="opacity: 0.2; filter: alpha(opacity = 20);" /></td>
										</tr>
										<tr>
											<td>Mes Expiraci&oacute;n<b>*</b></td>
											<td><select id="tarjetaMesExp" name="cboFpMes" class="datosVehiculo"
												required="required">
													<option value="1">1</option>
													<option value="2">2</option>
													<option value="3">3</option>
													<option value="4">4</option>
													<option value="5">5</option>
													<option value="6">6</option>
													<option value="7">7</option>
													<option value="8">8</option>
													<option value="9">9</option>
													<option value="10">10</option>
													<option value="11">11</option>
													<option value="12">12</option>
											</select></td>
										</tr>
										<tr>
											<td>A&ntilde;o Expiraci&oacute;n<b>*</b></td>
											<td><select id="tarjetaAnioExp" name="cboFpAnio" class="datosVehiculo"
												required="required"></select></td>
										</tr>
										<tr>
											<td>Fecha inicio de pagos<b>*</b></td>
											<td><input id="txtfechaInicialpagoTarjeta"
												name="txtfechaInicialpagoTarjeta" type="date"></td>
										</tr>
										<tr>
										<td>Cuota Inicial<b>*</b></td>
											<td><input id="txtcuotaInicialtarjetaPlazo" class="datosVehiculo" type="number" min="0" value="0" required="required" onkeypress="return justNumbers(event);" name="txtcuotaInicial" onchange="validarValoresPagos() " aria-required="true"></td>
										</tr>
										<tr>
											<td>Plazo<b>*</b></td>
											<td><select id="tarjetaPlazo" name="cboFpPlazo" class="datosVehiculo"
												required="required" onchange=" validarValoresPagos()">
													<optgroup label="Debito sin Interes">
														<option value="1" class="plazoDebitoTarjeta">1
															mes</option>
														<option value="2" class="plazoDebitoTarjeta">2
															meses</option>
														<option value="3" class="plazoDebitoTarjeta">3
															meses</option>
														<option value="4" class="plazoDebitoTarjeta">4
															meses</option>
														<option value="5" class="plazoDebitoTarjeta">5
															meses</option>
														<option value="6" class="plazoDebitoTarjeta">6
															meses</option>
														<option value="7" class="plazoDebitoTarjeta">7
															meses</option>
														<option value="8" class="plazoDebitoTarjeta">8
															meses</option>
														<option value="9" class="plazoDebitoTarjeta">9
															meses</option>
														<option value="10" class="plazoDebitoTarjeta">10
															meses</option>
													</optgroup>
											</select></td>
										</tr>
										<tr>
											<td colspan="2" align="center"><button type="button"
													class="btn btn-primary save-pago bloquearEmitido"
													id="save-pagoDebitoTarjeta"
													onclick='guardarPago("tarjeta");ponerFocoAlHacerClic();'>Guardar</button></td>
										</tr>
									</table>

									<!-- *********************************************************************************************************************
													     		F O R M U L A R I O   P A R A   E L   I N G R E S O   D E   C U O T A S
											     ******************************************************************************************************************-->
									<table id='forma_cuotas' hidden=''
										class='table table-bordered table-hover'>
										<tr></tr>
										<tr>
											<td>Cuota Inicial<b>*</b></td>
											<td><input id="txtcuotaInicial" type="number" class="datosVehiculo"
												required="required" onkeypress="return justNumbers(event);"
												name="txtcuotaInicial" min="0" value="0"
												onchange="validarValoresPagos()"></td>
										</tr>
										<tr>
											<td>Plazo<b>*</b></td>
											<td><select id="cboFpPlazo" name="cboFpPlazo" class="datosVehiculo"
												onchange="validarValoresPagos()"
												required="required">
													<option value="1">1 mes</option>
													<option value="2">2 meses</option>
													<option value="3">3 meses</option>
													<option value="4">4 meses</option>
													<option value="5">5 meses</option>
													<option value="6">6 meses</option>
													<option value="7">7 meses</option>
													<option value="8">8 meses</option>
													<option value="9">9 meses</option>
													<option value="10">10 meses</option>
											</select></td>
										</tr>
										<tr id="rowDetallePagos">
											<td colspan="2" align='center'>
												<table border="1"
													style="width: 500px; margin: 10px; padding: 5px;">
													<tr>
														<td align="center"><b>Nro.</b></td>
														<td align="center"><b>NÃºmero de Cheque</b></td>
														<td align="center"><b>Valor</b></td>
														<td align="center"><b>Fecha Cobro</b></td>
													</tr>
													<tbody id="detallePagoCuotas">

													</tbody>
												</table>
											</td>
										</tr>
										<tr>
											<td colspan="2" align="center"><button type="button"
													class="btn btn-primary save-pago bloquearEmitido" id="save-pagoCuotas"
													onclick='guardarPago("cuota")'>Guardar</button></td>
										</tr>
									</table>
									<!-- F O R M A S   D E   P A G O  -->
								</div>
								<!-- F i n   T e r c e r   t a b  -->
								<!-- I n i c i o   Q u i n t o   t a b  -->
								<div class="tab-pane" id="quinto">
									<div class="alert alert-danger" id="msgPopupEndosoBeneficiarioError" hidden="hidden">
										<div id='mensajeErrorEndosoBeneficiario'></div>
									</div>
									<div class="alert alert-success" id="msgPopupEndosoBeneficiarioGrabo" hidden="hidden">
										<div id='mensajeGraboEndosoBeneficiario'></div>
									</div>
							<div class="panel panel-primary">
						     <div class="panel-heading">Datos del Asegurado</div>
								<div class="panel-body">
									<table class='table table-bordered table-hover' id="formaAsegurado">
										<input id="asegurado_id" type="hidden">
										<tr>
											<td>Tipo de identificaci&oacute;n del Asegurado</td>
											<td><select id="tipo_identificacion_asegurado"
												class="tipo_identificacion datosVehiculo" onchange="esconderFilaAsegurado(); validarIdentificacionAsegurado()"
												name="tipo_identificacion_asegurado" required="required"></select>
											</td>
											
											<td>Identificaci&oacute;n del Asegurado</td>
											<td><input id="identificacion_asegurado" onkeypress="validarKeyPress(event,/[0-9]/);"
												class="identificacion datosVehiculo" onChange="validarRUCCedulaAsegurado();"
												name="identificacion_asegurado" required="required">
											</td>
										</tr>
										<tr id='filaNaturalAsegurado'>
											<td>Nombres:</td>
											<td><input id="nombres_asegurado"
												class=" datosVehiculo"
												name="nombres_asegurado" required="required">
											</td>
											
											<td>Apellidos:</td>
											<td><input id="apellidos_asegurado"
												class=" datosVehiculo"
												name="apellidos_asegurado" required="required">
											</td>
										</tr>
										<tr id='filaJuridicaAsegurado' hidden='hidden'>
											<td>Nombre Completo:</td>
											<td><input id="nombre_completo_asegurado"
												class="datosVehiculo"
												name="nombre_completo_asegurado" required="required">
											</td>
										</tr>
												<tr>
													<td colspan='4'>
														<div align="center">
															<button type="button" class="btn btn-primary bloquearEmitido" id="guardar_asegurado" onClick="guardarAsegurado('guardar');ponerFocoAlHacerClic();">Guardar</button>
														</div>
													</td>
												</tr>
											</table>
									
								</div>
									</div>
							 <div class="panel panel-primary">
						     <div class="panel-heading">Datos del Beneficiario</div>
								<div class="panel-body">
									<table class='table table-bordered table-hover' id="formaBeneficiario">
										<input id="endoso_beneficiario_id" type="hidden">
										<tr>
											<td>Beneficiario</td>
											<td>
											<input style="width:90%" type="select" id="beneficiario" name="beneficiario" class="">
											</td>
											</tr>
											<tr>
											<td>Monto</td>
											<td><input type="number" value='0' min='0' id="valor_endoso_beneficiario"
												class="datosVehiculo"
												name="valor_endoso_beneficiario">
											</td>
										</tr>
										<tr>
													<td>
														<div align="center">
															<button type="button" class="btn btn-primary bloquearEmitido" id="guardar_beneficiario" onClick="guardarBeneficiario('guardar');ponerFocoAlHacerClic();">Guardar</button>
														</div>
													</td>
													<td>
														<div align="center">
															<button type="button" class="btn btn-danger bloquearEmitido" id="eliminar_beneficiario" onClick="guardarBeneficiario('eliminar');ponerFocoAlHacerClic();">Eliminar</button>
														</div>
													</td>
												</tr>
									</table>
									
								</div>
								</div>
									
								</div>
								
								<!-- F i n   Q u i n t o   t a b  -->
							</div>
						</div>



					</div>
				</div>
			</section>

             <h2>Factura<i class="fa fa-edit fa-2x"></i></h2>
                <section>
                
                  <!-- *********************************************************************************************************************
									F O R M U L A R I O   P A R A   E L   I N G R E S O   D E   D A T O S   D E   U P L A
				******************************************************************************************************************-->
											
		              <fieldset id ="datosFactura"class="border">
		                <legend class="border">Factura</legend>
		                <div class="alert alert-danger" id="msgPopupDatosFacturaError" hidden="hidden">
							<div id='mensajeErrorDatosFactura'></div>
						</div>
							<div class="alert alert-success" id="msgPopupDatosFacturaExito" hidden="hidden">
							<div id='mensajeExitoDatosFactura'></div>
						</div> 
							<div class="panel panel-primary">
						     <div class="panel-heading">Direcci&oacute;n del Solicitante</div>
								<div class="panel-body">		
							<table>
							 <tr>
							    <td colspan="1"><label><b>Nombre:</b></label></td>
							    <td colspan="3" ><input type="text" id="nombre_direccion_solicitante" disabled="disabled">
							    	<input hidden="hidden" type="hidden" id="cedula_direccion_solicitante"></td>		
							    </tr>
							  <tr>
							    <td colspan="1"><label><b>Zona:*</b></label></td>
							    <td colspan="1"><select id="zona_direccion_solicitante" class="datosVehiculo" onChange="cambiaZonaDireccion(event,'solicitante');"></select></td>
							    <td colspan="1"><label><b>Provincia:*</b></label></td>
							    <td colspan="1"><select id="provincia_direccion_solicitante" class="provincia datosVehiculo" onchange="cargarCiudades('',$(this).val(),'direccion_solicitante'); cargarCantones('',$(this).val(),'direccion_solicitante')" required="required"></select></td>
							    </tr>
							    <tr id="fila_canton_direccion_solicitante" hidden="">
							    <td  colspan="1" ><label><b>Canton:*</b></label></td>
							    <td colspan="3"><select id="canton_direccion_solicitante"  onchange="cargarParroquias('',$(this).val(),'direccion_solicitante');" name="canton_direccion_solicitante" class="canton datosVehiculo"></select></td>
							    </tr>
							     <tr id="fila_parroquia_direccion_solicitante" hidden="">
							    <td colspan="1" ><label><b>Parroquia:*</b></label></td>
							    <td colspan="3" ><select id="parroquia_direccion_solicitante" name="parroquia_direccion_solicitante" class="parroquia datosVehiculo"></select></td>
							    </tr>
							     <tr id="fila_ciudad_direccion_solicitante" hidden="">
							    <td colspan="1" ><label><b>Ciudad:*</b></label></td>
							    <td colspan="3" ><select id="ciudad_direccion_solicitante" name="ciudad_direccion_solicitante" class="ciudad datosVehiculo"></select></td>
							    </tr>
							    <tr>
							    <td colspan="1"><label><b>Calle Principal:*</b></label></td>
							    <td colspan="3"><input type="text" maxlength="170" name="principal_direccion_solicitante" id="principal_direccion_solicitante" class="datosVehiculo" required="required"></td>
							    </tr>
							    <tr>
							    <td colspan="1"><label><b>NÃºmero:*</b></label></td>
							    <td colspan="3"><input type="text" maxlength="30" id="numero_direccion_solicitante" class="datosVehiculo" name="numero_direccion_solicitante" required="required"></td>
							    </tr>
							    <tr>
							    <td colspan="1"><label><b>Calle Secundaria:*</b></label></td>
							    <td colspan="3"><input type="text" maxlength="100" id="secundaria_direccion_solicitante" class="datosVehiculo" name="secundaria_direccion_solicitante" required="required"></td>
							    </tr>
							    <tr>
							    <td colspan="1"><label><b>Datos Referencia:*</b></label></td>
							    <td colspan="3"><input type="text" maxlength="170" id="referencia_direccion_solicitante" class="datosVehiculo" name="referencia_direccion_solicitante" ></td>
							  </tr>
   								<tr>
							    <td colspan="1"><label><b>Telefono:*</b></label></td>
							    <td colspan="1"><input type="text" id="telefono_direccion_solicitante"  name="telefono_direccion_solicitante" maxlength="10" class="telefono datosVehiculo" onkeypress="validarKeyPress(event,/[0-9]/)" required="required"></td>
							    <td colspan="1"><label><b>Celular:*</b></label></td>
							    <td colspan="1"><input type="text" id="celular_direccion_solicitante" class="datosVehiculo" maxlength="10" name="celular_direccion_solicitante" onkeypress="validarKeyPress(event,/[0-9]/)"  required="required"></td>
							    
							  </tr>
							  <tr>
							    <td><label><b>Email:</b></label></td>
							    <td><input type="text" id="mail_direccion_solicitante" name="mail_direccion_cliente" class="mail datosVehiculo" onchange="ValidarCorreoCampo(this)"></td>
							  </tr>
						</table>																	
								</div>
								 
							</div> 
							
							<div id="direccion_asegurado" hidden="" class="panel panel-primary">
						     <div class="panel-heading">Direcci&oacute;n del Asegurado</div>
								<div class="panel-body">		
							<table>
							 <tr>
							    <td colspan="1"><label><b>Nombre:</b></label></td>
							    <td colspan="3" ><input type="text" id="nombre_direccion_asegurado" disabled="disabled">
							    <input hidden="hidden" type="hidden" id="cedula_direccion_asegurado" ></td>							    
							 </tr>
							  <tr>
							    <td colspan="1"><label><b>Zona:*</b></label></td>
							    <td colspan="1"><select id="zona_direccion_asegurado" class="datosVehiculo" onChange="cambiaZonaDireccion(event,'asegurado');"></select></td>
							    <td colspan="1"><label><b>Provincia:*</b></label></td>
							    <td colspan="1"><select id="provincia_direccion_asegurado" class="provincia datosVehiculo" onchange="cargarCiudades('',$(this).val(),'direccion_asegurado'); cargarCantones('',$(this).val(),'direccion_asegurado')" required="required"></select></td>
							    </tr>
							    <tr id="fila_canton_direccion_asegurado" hidden="">
							    <td  colspan="1" ><label><b>Canton:*</b></label></td>
							    <td colspan="3"><select id="canton_direccion_asegurado"  onchange="cargarParroquias('',$(this).val(),'direccion_asegurado');" name="canton_direccion_cliente_natural" class="canton datosVehiculo"></select></td>
							    </tr>
							     <tr id="fila_parroquia_direccion_asegurado" hidden="">
							    <td colspan="1" ><label><b>Parroquia:*</b></label></td>
							    <td  colspan="3" ><select id="parroquia_direccion_asegurado" name="parroquia_direccion_cliente_natural" class="parroquia datosVehiculo"></select></td>
							    </tr>
							     <tr id="fila_ciudad_direccion_asegurado" hidden="">
							    <td colspan="1" ><label><b>Ciudad:*</b></label></td>
							    <td colspan="3" ><select id="ciudad_direccion_asegurado" name="ciudad_direccion_asegurado" class="ciudad datosVehiculo"></select></td>
							    </tr>
							    <tr>
							    <td colspan="1"><label><b>Calle Principal:*</b></label></td>
							    <td colspan="3"><input type="text" maxlength="170" name="principal_direccion_asegurado" id="principal_direccion_asegurado" class="datosVehiculo" required="required"></td>
							    </tr>
							    <tr>
							    <td colspan="1"><label><b>NÃºmero:*</b></label></td>
							    <td colspan="3"><input type="text" maxlength="30" id="numero_direccion_asegurado" class="datosVehiculo" name="numero_direccion_asegurado" required="required"></td>
							    </tr>
							    <tr>
							    <td colspan="1"><label><b>Calle Secundaria:*</b></label></td>
							    <td colspan="3"><input type="text" maxlength="100" id="secundaria_direccion_asegurado" class="datosVehiculo" name="secundaria_direccion_asegurado" required="required"></td>
							    </tr>
							    <tr>
							    <td colspan="1"><label><b>Datos Referencia:*</b></label></td>
							    <td colspan="3"><input type="text" maxlength="170" id="referencia_direccion_asegurado" class="datosVehiculo" name="referencia_direccion_asegurado" ></td>
							  </tr>
   								<tr>
							    <td colspan="1"><label><b>Telefono:*</b></label></td>
							    <td colspan="1"><input type="text" id="telefono_direccion_asegurado"  name="telefono_direccion_asegurado" maxlength="10" class="telefono datosVehiculo" onkeypress="validarKeyPress(event,/[0-9]/)" required="required"></td>
							    
							    <td colspan="1"><label><b>Celular:*</b></label></td>
							    <td colspan="1"><input type="text" id="celular_direccion_asegurado" class="datosVehiculo" maxlength="10" name="celular_direccion_asegurado" onkeypress="validarKeyPress(event,/[0-9]/)"  required="required"></td>
							    
							  </tr>
							  <tr>
							    <td><label><b>Email:</b></label></td>
							    <td><input type="text" id="mail_direccion_asegurado" name="mail_direccion_asegurado" class="mail datosVehiculo" onchange="ValidarCorreoCampo(this)"></td>
							  </tr>
						</table>																	
								</div>
								 
							</div> 
						<div align="center">
							<button type="button" class="btn btn-primary bloquearEmitido" id="guardar_direcciones" onClick="guardarDatosFactura();">Guardar</button>
						</div>
							 <!-- Fin datos ubicacion-->

		            </fieldset>		       
 
             </section>

			</section>
			<h2>Emision <i class="fa fa-check-circle-o fa-2x"></i></h2>
			<section>

				<div class="panel panel-primary">
					<div class="panel-heading">Vehiculos</div>
					<div class="panel-body">
						<table id="tablaFinalVehiculos">
							<tr>
								<td colspan="4"><label>Seleccione la fecha con la que desea que se emita la poliza de esta cotizaci&oacute;n: </label></td>
								<td colspan="3"><input type="date" id="fecha_inicio_vigencia" name="fecha_inicio_vigencia" required="required"></tr>
							</tr>
							<tr height="30px"></tr>
							<tr><table id="tablaFinalSoloVehiculos"></table></tr>
						</table>
						<div id="loading_tablaFinal" class="loading_identificacion" hidden="" ><span id="loading-msgTablaFinal"></span><img  src="../static/images/ajax-loader.gif" /></div>
					</div>
				</div>	
				<table>
					<tr>
						<td style="width: 10%">
							<button type='button' id='confirmarDatos' class='btn btn-success btn-xs bloquearEmitido' onclick="confirmarDatosVehiculos()"> <span class='glyphicon glyphicon glyphicon-check'></span>Solicitar Emision
						</button>
						</td>
						
						<td style="width: 10%">
							<button hidden='hidden' type='button' class='btn btn-success btn-xs' id='emision_poliza' onclick="emisionVH()" disabled="disabled"> <span class='glyphicon glyphicon glyphicon-edit'></span>Emision Poliza </button>
						</td>
						<td style="width: 80%">
							&nbsp;&nbsp;<label><input type="checkbox" id="emision_programa_seguros" onchange="mensajeProgramaSeguros();">&nbsp;&nbsp;EMITIR COMO PROGRAMA DE SEGURO</label>
						</td>	
					</tr>
					<tr>
						<td colspan="10">
							<label>* Para confirmar la compra, indique si se emitirÃ¡ o no como programa de seguros.</label>
						</td>
					</tr>
					<tr>
						<td style="width: 10%">
							<button type='button' id='pendientePorEmitir' class='btn btn-success btn-xs bloquearEmitido' onclick="confirmarDatosVehiculos()">
								<span class='glyphicon glyphicon glyphicon-check'></span>Pasar a Pendiente por Emitir
							</button>
						</td>
					</tr>
					<tr>
						<td colspan="10">
							<button id='descargarEndosoBeneficiario'  hidden='hidden' type='button' class='btn btn-success btn-xs' onclick="abrirEndosoBeneficiario()"> <span class='glyphicon glyphicon glyphicon-download'></span>Endoso Beneficiario </button>
						</td>
					</tr>
					<tr>
						<td colspan="10">
							<button id='descargarCoberturaProvisional' hidden='hidden' type='button' class='btn btn-success btn-xs' onclick="abrirCoberturaProvisional()"> <span class='glyphicon glyphicon glyphicon-download'></span>Cobertura Provisional</button>
						</td>
					</tr>
				</table>
							
			</section>
			
			<!-- ---------------------------------------------------------------------------------------- -->
			<h2>Documentos <i class="fa fa-archive fa-2x"></i></h2>
			<section>
		                <div class="alert " id="msgPopupArchivosHabilitantes" hidden="hidden">
							<div id='mensajeErrorArchivosHabilitantes'></div>
						</div>
							
				<div class="panel panel-primary">
					<div class="panel-heading">Certificados</div>
					<div class="panel-body">

						<table>
							<tbody>
								<tr>
									<td>
										<div class="alert alert-info" id="mensajeTramite"
											hidden="hidden" style="display: block;"></div>
									</td>
								</tr>
								<tr>									
									<td colspan="2">
										<button type='button'
											class='btn btn-success btn-s descargaCertificado'
											id='descargar_condicionesParticulares'
											onclick="abrirCertificadoTodos();"> 
											<span class='glyphicon glyphicon glyphicon-download'></span>
											Descargar
										</button>
									</td>
									<td colspan="2">
										<button type='button'
											class='btn btn-success btn-s crearTramiteWF'
											id='crearTramiteWF'
											onclick="abrirTramiteWF();"> 
											<span class='glyphicon glyphicon glyphicon-tag'></span> 
											Crear Tramite
										</button>
										<button type='button'
											class='btn btn-success btn-s descargarGuiaRemision'
											id='descargarGuiaRemision' hidden='hidden'
											onclick="abrirGuiaRemision();"> 
											<span class='glyphicon glyphicon glyphicon-download'></span> 
											Descargar Guia Remision
										</button>
										</td>
									<td colspan="1">
										<div class="alert alert-success" id="mensajeNumeroTramite" hidden="hidden">
										</div>
									</td>
								<tr height="30px"></tr>
								<tr></tr>
							</tbody>
						</table>

						<div id="loading_cargaDocumentos" class="loading_cargaDocumentos" hidden="" ><span id="loading-msgTablaFinal"></span><img  src="../static/images/ajax-loader.gif" /></div>
					</div>
				</div>

				<div class="panel panel-primary">
					<div class="panel-heading">Documentos Habilitantes</div>
					<div class="panel-body">
						<table id="tablaFinalVehiculos" class="table">
							<tr>
								<td width="20%">Poliza Firmada</td>
								<td id="subirPolizaFirmada" colspan="">
								<input type="hidden" id="tienePolizaFirmada">
								<input id="archivoPolizaFirmada" name="PolizaFirmada" type="file" onchange="habilitarSubidaArchivo(event);"/>
								</td>
								<td  id="uploadPolizaFirmada"  style="display:none;">
									<button class='btn btn-primary btn-s '
										type="button"  name="PolizaFirmada"
										onClick="subirArchivo(event);">
										<span class="glyphicon glyphicon-upload"></span>Subir
									</button></td>
								<td id='descargarPolizaFirmada'  style="display:none;">
								<a rel="nofollow" href='' id='linkDescargaPolizaFirmada' class='btn btn-success btn-s ' name="PolizaFirmada">
										<span class='glyphicon glyphicon glyphicon-download'></span>
										Descargar
								</a></td>
								<td id="eliminarPolizaFirmada"  style="display:none;"><button type='button'
										class='btn btn-danger btn-s eliminar-btn' name="PolizaFirmada"
										onClick='eliminarArchivo(event)'> 
										<span class='glyphicon glyphicon glyphicon-remove'
											id='delete-record'></span> Eliminar
									</button></td>
							</tr>
							<tr>
								<td width="20%">Autorizacion Debito</td>
								<td id="subirAutorizacionDebito" colspan="">
								<input type="hidden" id="tieneAutorizacionDebito">
								<input id="archivoAutorizacionDebito" name="AutorizacionDebito" type="file" onchange="habilitarSubidaArchivo(event);"/>
								</td>
								<td  id="uploadAutorizacionDebito"  style="display:none;">
									<button class='btn btn-primary btn-s '
										type="button"  name="AutorizacionDebito"
										onClick="subirArchivo(event);">
										<span class="glyphicon glyphicon-upload"></span>Subir
									</button></td>
								<td id='descargarAutorizacionDebito'  style="display:none;">
								<a rel="nofollow" href='' id='linkDescargaAutorizacionDebito' class='btn btn-success btn-s '  name="AutorizacionDebito">
										<span class='glyphicon glyphicon glyphicon-download'></span>
										Descargar
								</a></td>
								<td id="eliminarAutorizacionDebito" style="display:none;"><button type='button'
										class='btn btn-danger btn-s eliminar-btn' name="AutorizacionDebito"
										onClick='eliminarArchivo(event)'>
										<span class='glyphicon glyphicon glyphicon-remove'
											id='delete-record'></span> Eliminar
									</button></td>
							</tr>
							<tr>
								<td width="20%">Formulario UPLA</td>
								<td id="subirFormularioUPLA" colspan="">
								<input type="hidden" id="tieneFormularioUPLA">
								<input id="archivoFormularioUPLA" name="FormularioUPLA" type="file" onchange="habilitarSubidaArchivo(event);"/>
								</td>
								<td  id="uploadFormularioUPLA"  style="display:none;">
									<button class='btn btn-primary btn-s '
										type="button"  name="FormularioUPLA"
										onClick="subirArchivo(event);">
										<span class="glyphicon glyphicon-upload"></span>Subir
									</button></td>
								<td id='descargarFormularioUPLA'  style="display:none;">
								<a rel="nofollow" href='' id='linkDescargaFormularioUPLA' class='btn btn-success btn-s '  name="FormularioUPLA">
										<span class='glyphicon glyphicon glyphicon-download'></span>
										Descargar
								</a></td>
								<td id="eliminarFormularioUPLA" style="display:none;"><button type='button'
										class='btn btn-danger btn-s eliminar-btn'  name="FormularioUPLA"
										onClick='eliminarArchivo(event)'>
										<span class='glyphicon glyphicon glyphicon-remove'
											id='delete-record'></span> Eliminar
									</button></td>
							</tr>
							<tr>
								<td width="20%">Caratula de la Cotizaci&oacute;n</td>
								<td id="subirCaratulaCotizacion" colspan="">
								<input type="hidden" id="tieneCaratulaCotizacion">
								<input id="archivoCaratulaCotizacion" name="CaratulaCotizacion" type="file" onchange="habilitarSubidaArchivo(event);"/>
								</td>
								<td  id="uploadCaratulaCotizacion"  style="display:none;">
									<button class='btn btn-primary btn-s '
										type="button"  name="CaratulaCotizacion"
										onClick="subirArchivo(event);">
										<span class="glyphicon glyphicon-upload"></span>Subir
									</button></td>
								<td id='descargarCaratulaCotizacion'  style="display:none;">
								<a rel="nofollow" href='' id='linkDescargaCaratulaCotizacion' class='btn btn-success btn-s '  name="CaratulaCotizacion">
										<span class='glyphicon glyphicon glyphicon-download'></span>
										Descargar
								</a></td>
								<td id="eliminarCaratulaCotizacion" style="display:none;"><button type='button'
										class='btn btn-danger btn-s eliminar-btn'  name="CaratulaCotizacion"
										onClick='eliminarArchivo(event)'>
										<span class='glyphicon glyphicon glyphicon-remove'
											id='delete-record'></span> Eliminar
									</button></td>
							</tr>
							<tr>
								<td width="20%">Factura</td>
								<td id="subirFactura" colspan="">
								<input type="hidden" id="tieneFactura">
								<input id="archivoFactura" name="Factura" type="file" onchange="habilitarSubidaArchivo(event);"/>
								</td>
								<td  id="uploadFactura"  style="display:none;">
									<button class='btn btn-primary btn-s '
										type="button"  name="Factura"
										onClick="subirArchivo(event);">
										<span class="glyphicon glyphicon-upload"></span>Subir
									</button></td>
								<td id='descargarFactura'  style="display:none;">
								<a rel="nofollow" href='' id='linkDescargaFactura' class='btn btn-success btn-s '  name="Factura">
										<span class='glyphicon glyphicon glyphicon-download'></span>
										Descargar
								</a></td>
								<td id="eliminarFactura" style="display:none;"><button type='button'
										class='btn btn-danger btn-s eliminar-btn'  name="Factura"
										onClick='eliminarArchivo(event)'>
										<span class='glyphicon glyphicon glyphicon-remove'
											id='delete-record'></span> Eliminar
									</button></td>
							</tr>
							</table>
					<div id="loading_cargaDocumentos" class="loading_cargaDocumentos" hidden="" ><span id="loading-msgTablaFinal"></span><img  src="../static/images/ajax-loader.gif" /></div>
					</div>
				</div>
											
			</section>
			
			<!-- ---------------------------------------------------------------------------------------- -->
        </form>
        </div>
    
    <div class="mensaje_no_producto" style="display: none;height:400px;text-align: center"><center><div class="alert alert-info" style="background: #003da5;border-color:#003da5;color: white;margin-left: 20%;margin-right: 20%;"><b>No puede realizar cotizaciones desde esta opcion.  !!!!</b></div></center></div>    
    </body>
</html>
 