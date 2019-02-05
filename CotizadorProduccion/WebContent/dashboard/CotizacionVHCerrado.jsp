<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es" >
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="../static/css/jquery-steps/normalize.css">
    <link rel="stylesheet" href="../static/css/jquery-steps/main.css">
    <link rel="stylesheet" href="../static/css/jquery-steps/jquery.steps.css">
    <link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link rel="stylesheet" href="../static/css/select2/select2.css">
    <script src="../static/js/jquery-steps/modernizr-2.6.2.min.js"></script>
    <script src="../static/js/jquery-steps/jquery.cookie-1.3.1.js"></script>
    <script src="../static/js/jquery-steps/jquery.steps.min.js"></script>
    <script src="../static/js/jquery.validate.js"></script>
    <script src="../static/js/producto_cerrado_vh/carga.inicial.cotizador.js"></script>
    <script src="../static/js/producto_cerrado_vh/metodos.cotizador.js"></script>
    <script src="../static/js/producto_cerrado_vh/emisionVH.js"></script>
    <script src="../static/js/util.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="../static/js/select2.js"></script>
	<!--  	para el tooltipster -->
	<script src="../static/js/jquery-ui/jquery-ui.js"></script>
	<link rel="stylesheet" type="text/css" href="../static/js/jquery-ui/jquery-ui.theme.css" />
	<link rel="stylesheet" type="text/css" href="../static/css/tooltipster.css" />
	<script type="text/javascript" src="../static/js/jquery.tooltipster.js"></script>
    <script type="text/javascript" src="../static/js/jquery.tooltipster.min.js"></script>	
    <!-- Para el Datepicker-->
    <link href="../static/css/datepicker.css" rel="stylesheet">
    <script src="../static/js/bootstrap-datepicker.js"></script>
    <style type="text/css">


        .pillbox {
            border: 1px solid #bbb;
           /* margin-bottom: 10px;*/
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            border-radius: 4px;
            width: 251px;
        }

        .container > div, .container > table {
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
            -webkit-box-shadow:  0px 0px 0px 0px #ddd;
            box-shadow:  0px 0px 0px 0px #ddd;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
        }

        legend.border {
            width:inherit; /* Or auto */
            padding:0 10px; /* To give a bit of padding on the left and right */
            border-bottom:none;
            font-size: medium;
        }

		.demo-wrap{
               /* margin: 40px auto;*/
                display:block;
                position:relative;
               /* max-width:500px;*/
            }
			
		a {
			text-decoration:underline;
			color:#00F;
			cursor:pointer;
		}

	
	.seleccionado{
		background-color: #E0E0E0;
		color:black;
		
	}
	
	table {
    	width:100%;
	}	
	
	select{
    	width:90%;
	}
	
	input[type="text"]{
		width:90%;
	}
	.marca_modelo {
		width:90%;
	}
	
	.no-close  {display: none }
	
	.ui-dialog-titlebar{width:0%;}
	
	.ui-dialog-titlebar-close {
  visibility: hidden;
}
	
	a{
	text-decoration: none !important; 
	}
	
	
	.col-md-3, .col-sm-3{
		padding-left:0px;
		padding-right:0px;
	}
	
	#tablaFinalVehiculos tr td{
    width:10%;
    white-space:nowrap;
	}
	

    </style>
    <script>
  //eventos de objetos
  
	
  	var editoVehiculo=false;
  	var tieneDescuento=false;
	
	$(document).ready(function (){
		
		activarMenu("CotizacionVHCerrado");
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
            <!--  
            <h1>Basic Demo</h1>
			-->
            <form id="wizard" action="" method="post">
                <h2>Producto / Cliente</h2>
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
							    <td style="width: 35%"><select name="grupo_productos" id="grupo_productos" onchange="obtenerProductosGrupo();" required="required"></select></td>
							    <td style="width: 15%"><label><b>Producto:*</b></label></td>
							    <td style="width: 35%"><select name="productos" id="productos" required="required" onchange="obtenerTasaPorProducto();cargarPuntosVenta();"></select></td>							    
							  </tr>
							  <tr>
							  	<td style="width: 15%"><label><b>Tasa:*</b></label></td>
							    <td style="width: 35%">
							    	<input type="text" name="tasa" id="tasa" disabled="disabled">							    	
							    	<select name="tasas" id="tasas" style="display: none;"></select>							    	
							    </td>
							    <td style="width: 15%"></td>
							    <td style="width: 35%"></td>							    
							  </tr>							   
							</table>
							</div> 
							</div>
		                    
							 <!-- Inicio datos poliza - 4 columnas -->
							<div class="panel panel-primary">
						     <div class="panel-heading">Datos de la P&oacute;liza</div>
								<div class="panel-body">		
							<table>
							  <tr>
							    <td style="width: 15%"><label><b>Punto Venta:*</b></label></td>
							    <td style="width: 35%"><select name="punto_venta" id="punto_venta" required="required"></select></td>
							    <td style="width: 15%"><label><b>Vigencia de la p&oacute;liza:*</b></label></td>
							    <td style="width: 35%"><select name="vigencia" id="vigencia" required="required"></select></td>
							  </tr>
							  <tr>
							    <td style="width: 15%" colspan="1"><label><b>Agente de Seguros:*</b></label></td>
							    <td style="width: 85%" colspan="3"><select name="agentes" id="agentes"  required="required"></select></td>
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
								    <td style="width: 35%"><select class="tipo_identificacion" id="tipo_identificacion_principal" onchange="mostrarTipoIdentificacion(); mostrarAdicionales();" required="required"></select></td>
								    <td style="width: 15%"><label><b>Identificaci&oacute;n:*</b></label></td>
								    <td style="width: 35%"><input type="text" id="identificacion" name="identificacion" required="required" maxlength="20" onchange="cargarPorIdentificacion('datosClienteInicio', this.value)">
								    <div id="loading_identificacion" class="loading_identificacion" hidden="" ><span id="loading-msg">Cargando...</span><img  src="../static/images/ajax-loader.gif" /></div></td>								    
								    <td style="width: 35%"> <input type="hidden" id="codigoClienteEnsurance" name="codigoClienteEnsurance"> </td>
								    <td style="width: 35%"> <input type="hidden" id="codigoEntidadEnsurance" name="codigoEntidadEnsurance"> </td>
								  </tr>	
								   <tr id="filaNatural">
								    <td style="width: 15%"><label><b>Nombres:*</b></label></td>
								    <td style="width: 35%"><input type="text" id="nombres" name="nombres" required="required"></td>
								    <td style="width: 15%"><label><b>Apellidos:*</b></label></td>
								    <td style="width: 35%"><input type="text" id="apellidos" name="apellidos" required="required"></td>
								  </tr>
								  <tr id ="filaJuridica" hidden="true">
								    <td  style="width: 15%" colspan="1"><label><b>Nombres Empresa:*</b></label></td>
								    <td  style="width: 35%" colspan="3"><input type="text" id="nombre_completo" name="nombre_completo" required="required"></td>								    
								  </tr>							  
								</table>	
								</div> 
							</div>	
							 <!-- Fin datos cliente -->
		            </fieldset>		
			</section>

			<h2 >Productos</h2>
			<section>
            <!--***********************************************************************************************
                             G E N E R A C I O N   D I N A M I C A   V E H I C U L O S
            ************************************************************************************************-->

			<table>
				<tr>
					<td><b>Cotizaci&oacute;n # <span id="cotizacion_id"></span></b></td>
					<td><input type="hidden" id="numero_vehiculos" name="numero_vehiculos"></td>
					<td style="text-align: right;"><a id="agregarVehiculonumero"  class="btn btn-primary glyphicon glyphicon-plus" onclick="agregarVehiculo();">Agregar un nuevo vehículo</a></td>					
					<td style="text-align: right;"><select- name="vehiculos_num" id="vehiculos_num"></select></td>
				</tr>						  
			</table>	
 
            <br>
            <!--Listado de variables a utilizar -->
		    <div id="lista_coberturas" style="display:none;" ></div>
			<input type="hidden" id="anio_max_seguro" name="anio_max_seguro">
			<input type="hidden" id="suma_asegurada_valor_tr" name="suma_asegurada_valor_tr">
			<input type="hidden" id="monto_fijo_valor_tr" name="monto_fijo_valor_tr">
			<input type="hidden" id="siniestro_valor_tr" name="siniestro_valor_tr">
			
		    <!--Listado de los vehiculos-->
		    <div id="lista_vehiculos"></div>
		    
			</section>

			<h2>Pago</h2>
            <section>


				<div class="panel panel-primary">
					<div class="panel-heading">
						Informaci&oacute;n de la Prima
						<button hidden="hidden" type='button'
							class='btn btn-success btn-xs' id='descargar_certificado' onclick="abirCertificado();">
							<span class='glyphicon glyphicon glyphicon-download'></span>Descargar Cotización
						</button>
						<button hidden="hidden" type='button'
							class='btn btn-success btn-xs' id='enviar_certificado'
							onclick="$('#correos_certificado').dialog( 'open' );">
							<span class='glyphicon glyphicon glyphicon-send'></span>Enviar Cotización
						</button>
						<div class="panel panel-primary ui-dialog ui-dialog-content ui-dialog-titlebar" id="correos_certificado" title="Correos">
							<div class="panel panel-primary">
								<table class="">
									<tr class="ui-dialog-titlebar" id="correosForm" value="">
										<td><input type="email" id="correoCertificado" class=""></td>
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
								<img src="../static/images/ajax-loader.gif" /><br />
								<br /> <span id="loading-msg">Cargando...</span>
							</div>
						</div>
						<div id="tabbable" class="tabbable" style="display: none;">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#primero" data-toggle="tab">Coberturas
										y Valor a Pagar</a></li>
								<li><a href="#segundo" data-toggle="tab">Solicitar
										Inspección</a></li>
								<li><a href="#tercero" data-toggle="tab">Solicitud de
										Descuentos</a></li>
								<li><a href="#cuarto" data-toggle="tab" id="tabFormasPago">Formas
										de Pago</a></li>
								<li><a href="#quinto" data-toggle="tab">Textos Legales
										y Condiciones Particulares</a></li>
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
														<td colspan="2"><center>Detalle Valor a
																Pagar</center></td>
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
													<!--  
													<tr>
														<td style="text-align: right">Recargo Seguro
															Campesino</td>
														<td><input id="recargo_seguro_campesino_vh"
															disabled="disabled" type="text"></input></td>
													</tr>
													-->
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
														<td style="text-align: right">IVA 12.00%</td>
														<td><input id="iva_vh" disabled="disabled"
															type="text"></input></td>
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
											<!-- div class="thumbnail" style="padding: 20px;">
							  		
							    </div-->
										</div>
									</div>


								</div>
								<!--  F i n   P r i m e r   t a b -->

								<!-- I n i c i o   S e g u n d o   t a b -->
								<div class="tab-pane" id="segundo">
									<!--  I N S P E C C I O N   -->
									<br>
									<!-- ---------------------------------------------------------------------------------------------------------------------- -->
									<div class="alert alert-danger" id="msgPopupInspeccionError"
										style="display: none;">
										<button type="button" class="close" data-dismiss="alert">
											<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
										</button>
										Por favor seleccione su forma de pago.
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
												value=""><label><b>Tipo de inspección:</b></label></td>
											<td><select id="tipoInspeccion" name="tipoInspeccion"
												onchange="cambioTipoInspeccion(this.value);"
												style="width: 150px">
													<option value="interna">Interna</option>
													<option value="externa">Externa</option>
											</select></td>
										</tr>
										<!-- tr style="height: 15px;"></tr-->
									</table>

									<table class='table table-bordered table-hover'
										style="display: none;" id="forma_inspeccion_externa">
										<tr>
											<td style="text-align: left;" colspan="2">Telefono de
												Contacto:*</td>
											<td colspan="3"><input id="numContactoInspeccion"
												type="text" onkeypress="return justNumbers(event);"
												required="required" name="numContactoInspeccion"
												class="requeridoInspeccion" maxlength="10"></td>
										</tr>
										<tr>
											<td style="text-align: left;" colspan="2">Zona Inspección:*</td>
											<td colspan="3"><select id="zonaInspeccion"
												class="requeridoInspeccion" style="width: 150px">
												<option value="">Seleccione una zona</option>
												<option value="1">Urbana</option>
												<option value="2">Rural</option>
												</select></td>
										</tr>
										<tr>
											<td style="text-align: left;" colspan="2">Lugar
												Inspección:*</td>
											<td colspan="3"><select id="origenInspeccion"
												name="origenInspeccion"
												onchange="cargarDestinoInspeccion(this.value)"
												class="requeridoInspeccion" style="width: 150px"></select></td>
										</tr>
										<tr>
											<td style="text-align: left;" colspan="2">Destino
												Inpección:*</td>
											<td colspan="3"><select id="destinoInspeccion"
												name="destinoInspeccion"
												onchange="cargarProveedoresDisponibles(this.value)"
												class="requeridoInspeccion" style="width: 150px"></select></td>
										</tr>
										<tbody id="detalleInspectoresDisponibles">

										</tbody>
										<tr>
											<td colspan="5" align="center"><button type="button"
													class="btn btn-primary save-inspeccion"
													id="save-inspeccion" onclick='guardarSolicitudInspeccion()'>Guardar</button></td>
										</tr>
									</table>
									<!-- ---------------------------------------------------------------------------------------------------------------------- -->
								</div>

								<!-- Fin Segundo tab-->
								<!-- inicio Tercero tab-->
								<div class="tab-pane" id="tercero">
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
												<td><select id="codigo_descuento"
													onChange="cambiaDescuento()"></select></td>
											</tr>
											<tr>
												<td style="text-align: right">Motivo Descuento:*</td>
												<td><select id="motivo_descuento"></select></td>
											</tr>
											<tr>
												<td style="text-align: right">Porcentaje:*</td>
												<td><input id="porcentaje_solicitud_descuento"
													step='0.1' type="number"></input></td>
											</tr>
											<tr>
												<td style="text-align: right">Descripci&oacute;n:*</td>
												<td><textarea id="descripcion_motivo_descuento"
														style="width: 90%" rows="10"></textarea></td>
											</tr>
											<tr id='estado_solicitud_descuento'>
												<td style="text-align: right">Estado</td>
												<td>Pendiente</td>
											</tr>
											<tr>
												<td style="text-align: right"></td>
												<td><button type='button'
														class='btn btn-success btn-xs'
														id='enviar_solicitud_descuento'
														onclick='confirmarDescuento(event)'>
														<span class='glyphicon glyphicon glyphicon-edit'></span>Enviar
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
									<div class="alert alert-danger" id="msgPopupPagoError"
										style="display: none;">
										<button type="button" class="close" data-dismiss="alert">
											<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
										</button>
										Por favor seleccione su forma de pago.
									</div>

									<div class="alert alert-success" id="msgPopupPagoGrabo"
										style="display: none;">
										<button type="button" class="close" data-dismiss="alert">
											<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
										</button>
										La forma de pago se ha registrado correctamente.
									</div>

									<br>
									<!--  F O R M A S   D E   P A G O   -->
									<table style="width: 300px;" align="center">
										<tr>
											<td><label><b>Forma de Pago:*</b></label></td>
											<td><select id="cboFpFormaPago" name="cboFpFormaPago"
												onchange="cambioFormaPago(this.value);" style="width: 150px">
											</select></td>
										</tr>
										<tr style="height: 20px;"></tr>
										<tr>
											<td colspan="2" align="center"><button type="button"
													class="btn btn-primary save-pago" id="save-pagoContado"
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
											<td style="text-align: left;">Banco</td>
											<td><select id="bancoId" name="cboFpBanco"></select></td>
										</tr>
										<tr>
											<td>Tipo Cuenta</td>
											<td><select id="bancoTipoCuenta" name="cboFpTipo"
												required="required">
													<option value="A">Ahorro</option>
													<option value="C">Corriente</option>
											</select></td>
										</tr>
										<tr>
											<td>Número Cuenta</td>
											<td><input id="bancoNumeroCuenta" type="text"
												class="camporequerido validate-not-first"
												onkeypress="return justNumbers(event);"
												onchange="validacionBanco(); validaCuenta();"
												required="required" name="txtFpNumero"></td>
										</tr>
										<tr>
											<td>Titular Cuenta</td>
											<td><input id="bancoTitular" type="text" placeholder=""
												required="required"></td>
										</tr>
										<tr>
											<td>Tipo de identificaci&oacute;n del titular Cuenta</td>
											<td><select id="tipo_identificacion_banco"
												class="tipo_identificacion"
												name="cboFpTipoIdentificacionBanco" required="required"></select></td>
										</tr>
										<tr>
											<td>Cédula/Ruc del titular Cuenta</td>
											<td><input id="bancoIdentificacion" type="text"
												required="required" onkeypress="return justNumbers(event);"
												onchange="cargarPorIdentificacion('formasPagoDebitoBancario', this.value);"
												name="txtIdentificacionBanco">
												<div class="loading_identificacion" hidden="">
													<span id="loading-msg">Cargando...</span><img
														src="../static/images/ajax-loader.gif" />
												</div></td>
											</td>
										</tr>
										<!--tr>
													<td>Fecha inicio de pagos</td>
													<td><input id="txtfechaInicialpago"
														name="txtfechaInicialpago" type="date"></td>
												</tr-->
												<tr>
											<td>Cuota Inicial</td>
											<td><input id="txtcuotaInicialbancoPlazo" type="number" value="0" required="required" onkeypress="return justNumbers(event);" name="txtcuotaInicial" onchange="validarCuotaInicial(event);" aria-required="true"></td>
										</tr>
										<tr>
											<td>Plazo</td>
											<td><select id="bancoPlazo" name="cboFpPlazo" onchange="validarCuotaMinima(event);"
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
													class="btn btn-primary save-pago" id="save-pagoDebitoBanco"
													onclick='guardarPago("banco")'>Guardar</button></td>
										</tr>
									</table>


									<!-- *********************************************************************************************************************
													     F O R M U L A R I O   P A R A   E L   I N G R E S O   D E   D E B I T O S   C O N   T A R J E T A S
											     ******************************************************************************************************************-->
									<table class='table table-bordered table-hover'
										style="display: none;" id="forma_tarjeta">
										<div class="alert alert-info" id="msgPopupPagoTarjeta"
											style="display: none;">Grabado con exito.</div>
										<tr>
											<td>Número Tarjeta</td>
											<td><input id="tarjetaNumero" type="text"
												required="required" onpaste="return false"
												placeholder="Ingrese el número de tarjeta"
												onkeypress="return validarEmisorTarjeta(event, this.value);"
												onchange="comprobarTarjetaValida(this.value)"
												name="txtFpNumero" maxlength="16"> <br> <span></span>
											</td>
										</tr>
										<tr>
											<td>Tarjeta</td>
											<td><select id="tarjetaId" name="cboFpBanco"
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
											<td>Cédula/Ruc del titular Tarjeta</td>
											<td><input id="tarjetaIdentificacion" type="text"
												required="required" onkeypress="return justNumbers(event);"
												title="Ingrese la Cédula/RUC del titular"
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
											<td>Titular Tarjeta</td>
											<td><input id="tarjetaTitular" type="text"
												required="required"
												class="camporequerido validate-not-first"
												title="Ingrese el titular de la Tarjeta" value=""
												name="txtTitularTarjeta"></td>
										</tr>
										<tr>
											<td>Tipo de identificaci&oacute;n del titular Tarjeta</td>
											<td><select id="tipo_identificacion_tarjeta"
												class="tipo_identificacion"
												name="cboFpTipoIdentificacionTarjeta" required="required"></select>
											</td>
										</tr>
										<tr>
											<td>Mes Expiraci&oacute;n</td>
											<td><select id="tarjetaMesExp" name="cboFpMes"
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
											<td>A&ntilde;o Expiraci&oacute;n</td>
											<td><select id="tarjetaAnioExp" name="cboFpAnio"
												required="required"></select></td>
										</tr>

										<!--tr>
													<td>Fecha inicio de pagos</td>
													<td><input id="txtfechaInicialpago" name="txtfechaInicialpago"  type="date"></td>
												</tr-->
												<td>Cuota Inicial</td>
											<td><input id="txtcuotaInicialtarjetaPlazo" type="number" min="0" value="0" required="required" onkeypress="return justNumbers(event);" name="txtcuotaInicial" onchange="validarCuotaInicial(event);" aria-required="true"></td>
										<tr>
											<td>Plazo</td>
											<td><select id="tarjetaPlazo" name="cboFpPlazo"
												required="required" onchange=" validarCuotaMinima(event); cambioPlazoPago(this.value);">
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
													<optgroup label="Credito con Interes">
														<option value="12" class="plazoCreditoTarjeta">12
															meses</option>
													</optgroup>

											</select></td>
										</tr>
										<tr>
											<td colspan="2" align="center"><button type="button"
													class="btn btn-primary save-pago"
													id="save-pagoDebitoTarjeta"
													onclick='guardarPago("tarjeta")'>Guardar</button></td>
										</tr>
									</table>

									<!-- *********************************************************************************************************************
													     		F O R M U L A R I O   P A R A   E L   I N G R E S O   D E   C U O T A S
											     ******************************************************************************************************************-->
									<table id='forma_cuotas' hidden=''
										class='table table-bordered table-hover'>
										<!--tr>
															<td>Fecha inicio de pagos</td>
															<td>
																<input type="date" name="txtfechaInicialpago" id="txtfechaInicialpago" >
															</td>
														</tr-->
										<tr></tr>
										<tr>
											<td>Cuota Inicial</td>
											<td><input id="txtcuotaInicial" type="number"
												required="required" onkeypress="return justNumbers(event);"
												name="txtcuotaInicial" min="0" value="0"
												onchange="validarCuotaInicial(event);"></td>
										</tr>
										<tr>
											<td>Plazo</td>
											<td><select id="cboFpPlazo" name="cboFpPlazo"
												onchange="javascript:calculaCuotas(this.value);"
												required="required">
													<option value="0">Seleccione una opción</option>
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
														<td align="center"><b>Número de Cheque</b></td>
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
													class="btn btn-primary save-pago" id="save-pagoCuotas"
													onclick='guardarPago("cuota")'>Guardar</button></td>
										</tr>
									</table>
									<!-- F O R M A S   D E   P A G O  -->
								</div>
								<!-- F i n   T e r c e r   t a b  -->
								<!-- I n i c i o   Q u i n t o   t a b  -->
								<div class="tab-pane" id="quinto">
									<div class="thumbnail" style="padding: 20px;">
										<table id="tabla_textos_coberturas"
											class="table table-bordered table-hover dataTable"></table>
									</div>
								</div>
								<!-- F i n   Q u i n t o   t a b  -->
							</div>
						</div>



					</div>
				</div>
			</section>

             <h2>Upla</h2>
                <section>
                  <!-- *********************************************************************************************************************
									F O R M U L A R I O   P A R A   E L   I N G R E S O   D E   D A T O S   D E   U P L A
				******************************************************************************************************************-->
											
		             <fieldset id ="datosAdicionalesNatural"class="border">
		                <legend class="border">Datos Adicionales</legend>
		                    <div class="alert alert-danger alert-error" id="mensaje_exito_input_vehiculo" style="display: none;">Por favor ingrese los datos faltantes.</div>
							 <!-- Inicio datos poliza - 4 columnas -->
							<div class="panel panel-primary">
						     <div class="panel-heading">Ubicacion Persona Natural</div>
								<div class="panel-body">		
							<table>
							  <tr>
							    <td colspan="1"><label><b>Zona:*</b></label></td>
							    <td colspan="1"><select id="zona_direccion_natural" onChange="cambiaZonaDireccion(event,'N');"></select></td>
							    <td colspan="1"><label><b>Provincia:*</b></label></td>
							    <td colspan="1"><select id="provincia_direccion_cliente_natural" class="provincia" required="required"></select></td>
							    </tr>
							    <tr>
							    <td id="canton_cliente_label" colspan="1" hidden=""><label><b>Canton:*</b></label></td>
							    <td id="canton_cliente_select" colspan="1" hidden=""><select id="canton_direccion_cliente_natural" class="canton"></select></td>
							    <td id="parroquia_cliente_label" colspan="1" hidden=""><label><b>Parroquia:*</b></label></td>
							    <td id="parroquia_cliente_select" colspan="1" hidden=""><select id="parroquia_direccion_cliente_natural" class="parroquia"></select></td>
							    <td id="ciudad_cliente_label" colspan="1" hidden=""><label><b>Ciudad:*</b></label></td>
							    <td id="ciudad_cliente_select" colspan="1" hidden=""><select id="ciudad_direccion_cliente_natural" class="ciudad"></select></td>
							    
							    </tr>
							    <tr>
							    <td colspan="1"><label><b>Calle Principal:*</b></label></td>
							    <td colspan="1"><input type="text" id="calle_principal_direccion_natural" required="required"></td>
							    <td colspan="1"><label><b>Número:*</b></label></td>
							    <td colspan="1"><input type="text" id="numero_direccion_natural" required="required"></td>
							     </tr>
							    <tr>
							    <td colspan="1"><label><b>Calle Secundaria:*</b></label></td>
							    <td colspan="1"><input type="text" id="calle_secundaria_direccion_natural" required="required"></td>
							    <td colspan="1"><label><b>Datos Referencia:*</b></label></td>
							    <td colspan="1"><input type="text" id="referencia_direccion_natural" required="required"></td>
							  </tr>
							  <tr>
							    <td><label><b>Lugar Nacimiento:*</b></label></td>
							    <td><input type="text" id="lugar_nacimiento_natural" name="lugarNacimiento" ></td>
							    <td><label><b>Fecha de Nacimiento:*</b></label></td>
							    <td><input type="date" id="fecha_nacimiento_natural" name="fechaNacimiento" required="required"></td>
							  </tr>
							   <tr>
							    <td><label><b>Telefono:</b></label></td>
							    <td><input type="text" id="telefono_cliente_natural" maxlength="10" class="telefono" onkeypress="validarKeyPress(event,/[0-9]/)" required="required"></td>
							    
							    <td><label><b>Celular:</b></label></td>
							    <td><input type="text" id="celular_cliente_natural" maxlength="10" name="celular" onkeypress="validarKeyPress(event,/[0-9]/)"  required="required"></td>
							    
							  </tr>
							  <tr>
							    <td><label><b>Email:</b></label></td>
							    <td><input type="text" id="mail_cliente_natural" class="mail"></td>
							    
							    <td><label><b>Genero:*</b></label></td>
							    <td><select id="genero_cliente_natural"  required="required"></select></td>
							    
							  </tr>
						</table>																	
								</div> 
							</div> 
							 <!-- Fin datos ubicacion-->
							 <!-- Inicio datos cliente - 4 columnas -->
							<div class="panel panel-primary">
						     <div class="panel-heading">Actividad Persona Natural</div>
								<div class="panel-body">
								<table>
								  <tr>
								  <td><label><b>Actividad del Cliente:</b></label></td>
								    <td><input style="width:90%" type="select"  id="actividad_economica_cliente_natural" class="actividad_economica"></input>
								    </tr>
								   <tr>
								    <td><label><b>Tipo de Actividad del Cliente:</b></label></td>
								    <td><select name="tipo_actividad_cliente_natural" id="tipo_actividad_cliente_natural"></select></td>
								     </tr>
								   <tr>
								    <td><label><b>Cargo que ocupa:</b></label></td>
								    <td><input type="text" id="cargo_ocupa_cliente_natural" name="cargoOcupa">								    
								  </tr>	
								   <tr>
								    <td><label><b>Proposito de la relacion comercial (Ramo que contrata): </b></label></td>
								    <td><select name="ramo_contrata_cliente_natural" class='ramo' id="ramo_contrata_cliente_natural"></select></td>								    
								  </tr>								  						  
								</table>	
								</div> 
							</div>	
							 <!-- Fin datos actividad -->
							 
							 <!-- Inicio datos politica - 4 columnas -->
							<div class="panel panel-primary">
						     <div class="panel-heading">Actividad Politica</div>
								<div class="panel-body">
								<table>
								  <tr>
								  <td colspan='2'><label><b>Usted es una Persona Expuesta Politicamente :</b></label>
								  </td>
								    </tr>
								    <tr>
								    <td colspan='2'>
								    <select id="expuesto_cliente_natural"></select>
  									</td>
								    </tr>
								    <tr>
								    <td colspan><label><b>Cargo que desempeña:</b></label></td>
								    <td><input type="text" id="cargo_expuesto_cliente_natural" name="cargoDesempena">
								    </tr>
								    <tr>
								  <td colspan='2'><label><b>Tiene algun familiar que sea una Persona Expuesta Politicamente:</b></label>
								  </td>
								    </tr>
								    <tr>
								    <td colspan='2'>
								    <select id="expuesto_familiar_natural" placeholder="Escoja una opción"></select>
  									</td>
  									 </tr>
								    <tr>
								    <td><label><b>Parentesco:</b></label></td>
								    <td><input type="text" id="parentesco_expuesto_familiar_natural" name="parentesco" >
								    </td>
  									 </tr>
								    <tr>
								    <td><label><b>Cargo que desempe&ntilde;a:</b></label></td>
								    <td><input type="text" id="cargo_expuesto_familiar_natural" name="cargoDesempenaFamiliar" >
								    </tr>
								    <div class="well"><b>Persona Expuesta Politicamente:</b> Es la persona que desempe&ntilde;a o ha desempe&ntilde;ado funciones publicas destacadas en el pais o 
								    en el exterior, que por su perfil pueda exponer en mayor grado a la entidad al riesgo de lavado de activos y financiamiento de delitos, por ejemplo,
								    Jefe de Estado o de un gobierno, politico de alta jerarquia, funcionario importante de partidos politicos. Las relaciones comerciales con los parientes
								    dentro del segundo grado de consaguinidad o primero de afinidad y los colaboradores cercanos de una persona politicamente expuesta, implica que las instituciones
								    del sistema financiero apliquen procedimientos de debida diligencia ampliados.</div>
								</table>	
								</div> 
							</div>	
							 <!-- Fin datos actividad -->
							 <!-- Inicio datos Conyuge - 4 columnas -->
							<div class="panel panel-primary">
						     <div class="panel-heading">Datos del Conyuge o Conviviente (De ser aplicable)</div>
								<div class="panel-body">
								<table>
								  <tr>
								  <td><label><b>Apellido Paterno:</b></label></td>
								    <td><input type="text" id="apellido_paterno_conyuge_natural"  >
    							    <td><label><b>Apellido Materno:</b></label></td>
								    <td><input type="text" id="apellido_materno_conyuge_natural" >
								    <td><label><b>Nombres:</b></label></td>
								    <td><input type="text" id="nombre_conyuge_natural"  >
								    </tr>
								    <tr>
								    <td><label><b>Tipo Identificaci&oacute;n:</b></label></td>
								    <td><select class="tipo_identificacion" id="tipo_identificacion_conyuge_natural" onchange="" ></select></td>
								    <td><label><b>Identificaci&oacute;n:</b></label></td>
								    <td><input type="text" id="identificacion_conyuge_natural">
								    </tr>								   						  						  
								</table>	
								</div> 
							</div>	
							 <!-- Fin datos actividad -->
							 
							 <!-- Inicio datos Situacion Financiera -->
							<div class="panel panel-primary">
						     <div class="panel-heading">Situacion Financiera: Total de activos y pasivos</div>
								<div class="panel-body">
								<table>
								  <tr>
								  <td><label><b>Salario Mensual:</b></label></td>
								    <td><input type="number" id="salario_mensual_natural" name="" ></td>
    							    <td><label><b>Activos:</b></label></td>
								    <td><input type="number" id="activos_natural" name="">
								    </tr>
								    <tr>
								    <td><label><b>Otros Ingresos:</b></label></td>
								    <td><input type="number" id="otros_ingresos_natural" name=""></td>
    							    <td><label><b>Pasivos:</b></label></td>
								    <td><input type="number" id="pasivos_natural" name=""></td>
								    </tr>
								     <tr>
								    <td><label><b>Egresos:</b></label></td>
								    <td><input type="number" id="egresos_natural" name="egresos"></td>
    							    <td><label><b>A-P= Patrimonio:</b></label></td>
								    <td><input type="number" id="patrimonio_natural" name="patrimonio"></td>
								    </tr>
								    <tr>
								    <td><label><b>V/Neto Ingreso - Egreso:</b></label></td>
								    <td><input type="number" id="ingresos_egresos_natural" name=""></td>
								    </tr>
								</table>	
								</div> 
							</div>	
							 <!-- Fin datos actividad -->
							
							 <!-- Inicio datos Vinculos Existentes - 4 columnas -->
							<div class="panel panel-primary">
						     <div class="panel-heading">Vinculos Existentes entre el solicitante, asegurado, beneficiario</div>
								<div class="panel-body">
								<table>
								    <tr>
								    <td><label><b>Es Usted el asegurado de la poliza?:</b></label>
								    <select id="es_asegurado_natural" placeholder="Escoja una opción"></select>
  									</td>								    
								    </tr>
								    <tr>
								    <td><label><b>Es Usted el beneficiario de la poliza?:</b></label>
								    <select id="es_beneficiario_natural" placeholder="Escoja una opción"></select>
  									 </td>								    
								    </tr>								    							    
								</table>
								<br>
								
							<div class="panel panel-primary">
						     <div class="panel-heading">Vinculos Existentes entre el Asegurado y el Solicitante</div>
								<div class="panel-body">
								<table>
								  <tr>
								    <td><label><b>Tipo Identificaci&oacute;n:</b></label></td>
								    <td><select class="tipo_identificacion" id="tipo_identificacion_asegurado_natural" onchange=""></select></td>
								    <td><label><b>Identificaci&oacute;n:</b></label></td>
								    <td><input type="text" id="identificacion_asegurado_natural">
								    <td><label><b>Nombre Completo:</b></label></td>
								    <td><input type="text" id="nombre_asegurado_natural" name="nombreCompleto">
								    </tr>
								    <tr>
								    <td ><label><b>Direccion Domicilio:</b></label></td>
								    <td colspan='3'><input type="text" id="direccion_asegurado_natural" name="domicilio">							  
								    <td><label><b>Telefono Domicilio:</b></label></td>
								    <td><input type="text" maxlength="10" id="telefono_asegurado_natural" onkeypress="validarKeyPress(event,/[0-9]/)" name="telefonoDomicilio">
								    </tr>
								    <tr>
								    <td><label><b>Celular:</b></label></td>
								    <td><input type="text" maxlength="10" id="celular_asegurado_natural" onkeypress="validarKeyPress(event,/[0-9]/)" name="celularAsegurado" onkeypress="validarKeyPress(event,/[0-9]/)">
								    <td><label><b>Relacion:</b></label></td>
								    <td><input type="text" id="relacion_asegurado_natural" name="relacionAsegurado">
								    </tr>
								</table>	
								</div>							
								</div>
								
										<div class="panel panel-primary">
						     <div class="panel-heading">Vinculos Existentes entre el Solicitante y el Beneficiario</div>
								<div class="panel-body">
								<table>
								  <tr>
								    <td><label><b>Tipo Identificaci&oacute;n:</b></label></td>
								    <td><select class="tipo_identificacion" id="tipo_identificacion_beneficiario_natural" onchange=""></select></td>
								    <td><label><b>Identificaci&oacute;n:</b></label></td>
								    <td><input type="text" id="identificacion_beneficiario_natural">
								   <td><label><b>Nombre Completo:</b></label></td>
								    <td><input type="text" id="nombre_beneficiario_natural" name="nombreCompleto" >
								    </tr>
								    <tr>
								    <td ><label><b>Direccion Domicilio:</b></label></td>
								    <td colspan='3' >
								    <input type="text" id="direccion_beneficiario_natural" name="domicilio">							  
								    <td><label><b>Telefono Domicilio:</b></label></td>
								    <td><input type="text" maxlength="10" id="telefono_beneficiario_natural" name="telefonoDomicilio" onkeypress="validarKeyPress(event,/[0-9]/)">
								    </tr>
								    <tr>
								    <td><label><b>Celular:</b></label></td>
								    <td><input type="text" maxlength="10" id="celular_beneficiario_natural" name="celularAsegurado" onkeypress="validarKeyPress(event,/[0-9]/)">
								    <td><label><b>Relacion:</b></label></td>
								    <td><input type="text" id="relacion_beneficiario_natural" name="relacionAsegurado">
								    </tr>
								</table>	
								</div>							
								</div>
								
									
								</div> 
							</div>	
							 <!-- Fin datos actividad -->
					
		            </fieldset>		       
<!--  Fin Persona Natural -->       
<!-- 		          Persona Juridica -->
<!-- Persona Natural -->
		             <fieldset id ="datosAdicionalesJuridica"class="border">
		                <legend class="border">Datos Adicionales</legend>
		                    <div class="alert alert-danger alert-error" id="mensaje_exito_input_vehiculo" style="display: none;">Por favor ingrese los datos faltantes.</div>
							 <!-- Inicio datos poliza - 4 columnas -->
							<div class="panel panel-primary">
						     <div class="panel-heading">Ubicacion Persona Juridica</div>
								<div class="panel-body">		
							<table>
							  <tr>
							    <td><label><b>Objeto Social:</b></label></td>
							    <td><input type="text" id="objeto_social_juridica" name=""></td>
							    <td><label><b>Ciudad del domicilio </br> de la Persona Juridica:</b></label></td>
							    <td><input type="text" id="ciudad_juridica" name=""></td>
							  </tr>
							  <tr>
							   <td colspan="5"><label><b>Dirección de la Matriz</b></label></td>
							  </tr>
							  <tr>
							    <td colspan="1"><label><b>Zona:</b></label></td>
							    <td colspan="1"><select id="zona_direccion_matriz_juridica" onChange="cambiaZonaDireccion(event,'J');" required="required"></select></td>
							    <td colspan="1"><label><b>Provincia:</b></label></td>
							    <td colspan="1"><select id="provincia_direccion_matriz_juridica" class="provincia" required="required"></select></td>
							    
							    <td id="canton_matriz_label" colspan="1" hidden=""><label><b>Canton:</b></label></td>
							    <td id="canton_matriz_select" colspan="1" hidden=""><select id="canton_direccion_matriz_juridica" class="canton"></select></td>
							    <td id="parroquia_matriz_label" colspan="1" hidden=""><label><b>Parroquia:</b></label></td>
							    <td id="parroquia_matriz_select" colspan="1" hidden=""><select id="parroquia_direccion_matriz_juridica" class="parroquia"></select></td>
							    <td id="ciudad_matriz_label" colspan="1" hidden=""><label><b>Ciudad:</b></label></td>
							    <td id="ciudad_matriz_select" colspan="1" hidden=""><select id="ciudad_direccion_matriz_juridica" class="ciudad"></select></td>
							    
							    </tr>
							  <tr>
							    <td ><label><b>Calle Principal :</b></label></td>
							    <td colspan='3' ><input type="text" id="calle_principal_direccion_juridica" required="required"></td>
							    </tr>
							  <tr><td ><label><b>Número:</b></label></td>
							    <td ><input type="text" id="numero_direccion_juridica" required="required"></td>
							    <td ><label><b>Calle Secundaria:</b></label></td>
							    <td ><input type="text" id="calle_secundaria_direccion_juridica" required="required"></td>
							     </tr>
							  <tr>
							    <td colspan='3' ><label><b>Datos Referencia:</b></label></td>
							    <td ><input type="text" id="referencia_direccion_juridica" ></td>
							  </tr>
							  <tr>
							    <td><label><b>Telefono:</b></label></td>
							    <td><input maxlength="10" type="text" id="telefono_juridica" name="telefonoJuridico" onkeypress="validarKeyPress(event,/[0-9]/)" required="required"></td>
							    <td><label><b>Fax:</b></label></td>
							    <td><input maxlength="10" type="text" id="fax_juridica" name="fax" onkeypress="validarKeyPress(event,/[0-9]/)"></td>
							    
							  </tr>
							  <tr>
							   <td ><label><b>Dirección de la Sucursal</b></label></td>
							  </tr>
							  <tr>
							    <td ><label><b>Sucursal Direccion:</b></label></td>
							    <td><input type="text" id="direccion_sucursal_juridica" name="matrizDireccion"></td>
							    <td ><label><b>Ciudad:</b></label></td>
							    <td ><input type="text" id="ciudad_sucursal_juridica" name="ciudadSucursal"></td>
							  </tr>
							   
						</table>																	
								</div> 
							</div> 
							 <!-- Fin datos ubicacion-->
							 <!-- Inicio datos cliente - 4 columnas -->
							<div class="panel panel-primary">
						     <div class="panel-heading">Actividad Persona Juridica</div>
								<div class="panel-body">
								<table>
								  <tr>
								  <td><label><b>Actividad Economica:</b></label></td>
								    <td><input style="width:90%" type="select" id="actividad_economica_juridica">
								    </tr>
								    <tr>
								
								 <tr>
								 <tr >
								    <td><label><b>Nombres Representante Legal:</b></label></td>
								    <td><input type="text" id="nombres_representante_juridica" name="nombres" ></td>
								    <td><label><b>Apellidos Representante Legal:</b></label></td>
								    <td><input type="text" id="apellidos_representante_juridica" name="apellidos" ></td>
								  </tr>
								  <tr>
								    <td><label><b>Tipo Identificaci&oacute;n:</b></label></td>
								    <td><select class="tipo_identificacion" id="tipo_identificacion_representante_juridica" onchange="" ></select></td>
								    <td><label><b>Identificaci&oacute;n:</b></label></td>
								    <td><input type="text" id="identificacion_representante_juridica" name="identificacion_representante_juridica" number="number" maxlength="20" minlength="10" >						    								    
								  </tr>									   
								 <tr>
								    <td><label><b>Lugar Nacimiento:</b></label></td>
								    <td><input type="text" id="lugar_nacimiento_representante_juridica" name="lugarNacimiento"></td>
								    <td><label><b>Fecha de Nacimiento:</b></label></td>
								    <td><input type="date" id="fecha_nacimiento_representante_juridica" name="fechaNacimiento"></td>
							  	</tr>
							  <tr>
							    <td colspan="1"><label><b>Direcccion de Residencia:</b></label></td>
							    <td colspan="1"><input type="text" id="residencia_representante_juridica" name="direccionResidencia"></td>
							    </tr>
							  <tr>
							   <!--  <td colspan="1"><label><b>Pais:</b></label></td>
							    <td colspan="1"><input type="text" id="pais_representante_juridica" name="pais"></td> -->
							    <td colspan="1"><label><b>Provincia:</b></label></td>
							    <td colspan="1"><select id="provincia_representante_juridica" class="provincia"></select></td>
							    <td colspan="1"><label><b>Ciudad:</b></label></td>
							    <td colspan="1"><select id="ciudad_representante_juridica" name="ciudad"></select></td>
							  </tr>
							   <tr>
							    <td><label><b>Telefono:</b></label></td>
							    <td><input type="text" maxlength="10" id="telefono_representante_juridica" name="telefono" onkeypress="validarKeyPress(event,/[0-9]/)"></td>
							    
							    <td><label><b>Celular:</b></label></td>
							    <td><input type="text" maxlength="10" id="celular_representante_juridica" name="celular" onkeypress="validarKeyPress(event,/[0-9]/)"></td>
							    <td colspan="2"></td>
							  </tr>
								    
								   					  						  
								</table>	
								</div> 
							</div>	
							 <!-- Fin datos actividad -->
							 
							 <!-- Inicio datos politica - 4 columnas -->
							<div class="panel panel-primary">
						     <div class="panel-heading">Actividad Politica</div>
								<div class="panel-body">
								<table>
								  <tr>
								  <td colspan='4'><label><b>Usted es una Persona Expuesta Politicamente :</b></label>
								    </td>
  									</tr>
								    <tr>
								    <td colspan='4'>
								    <select id="expuesto_representante_juridica" placeholder="Escoja una opción">
  									</td>
  									</tr>
								    <tr>
								    <td><label><b>Cargo que desempe&ntilde;a:</b></label></td>
								    <td><input type="text" id="cargo_expuesta_representante_juridica" name="cargoDesempena">
								    </tr>
								    <tr>
								  <td colspan='4'><label><b>Tiene algun familiar que sea una Persona Expuesta Politicamente:</b></label>
								 	 </tr>
								    <tr>
								  	<td colspan='4'>
								    <select id="expuesto_familiar_juridica" placeholder="Escoja una opción"></select>
  									</td>
  									</tr>
								    <tr>
								    <td><label><b>Parentesco:</b></label></td>
								    <td><input type="text" id="parentesco_expuesto_familiar_juridico" name="parentesco">
								    <td><label><b>Cargo que desempe&ntilde;a:</b></label></td>
								    <td><input type="text" id="cargo_expuesto_familiar_juridica" name="cargoDesempenaFamiliar">
								    </tr>
								    <div class="well"><b>Persona Expuesta Politicamente:</b> Es la persona que desempe&ntilde;a o ha desempe&ntilde;ado funciones publicas destacadas en el pais o 
								    en el exterior, que por su perfil pueda exponer en mayor grado a la entidad al riesgo de lavado de activos y financiamiento de delitos, por ejemplo,
								    Jefe de Estado o de un gobierno, politico de alta jerarquia, funcionario importante de partidos politicos. Las relaciones comerciales con los parientes
								    dentro del segundo grado de consaguinidad o primero de afinidad y los colaboradores cercanos de una persona politicamente expuesta, implica que las instituciones
								    del sistema financiero apliquen procedimientos de debida diligencia ampliados.</div>
								</table>	
								</div> 
							</div>	
							 <!-- Fin datos actividad -->
							 <!-- Inicio datos Conyugue - 4 columnas -->
							<div class="panel panel-primary">
						     <div class="panel-heading">Datos del Conyugue o Conviviente (De ser aplicable)</div>
								<div class="panel-body">
								<table>
								  <tr>
								  <td><label><b>Apellido Paterno:</b></label></td>
								    <td><input type="text" id="apellido_paterno_conyuge_juridica" name="apellidoPaternoConyugue">
    							    <td><label><b>Apellido Materno:</b></label></td>
								    <td><input type="text" id="apellido_materno_conyuge_juridica" name="apellidoMaternoConyugue">
								    <td><label><b>Nombres:</b></label></td>
								    <td><input type="text" id="nombre_conyuge_juridica" name="NombresConyugue">
								    </tr>
								    <tr>
								    <td><label><b>Tipo Identificaci&oacute;n:</b></label></td>
								    <td><select class="tipo_identificacion" id="tipo_identificacion_conyugue_juridica" onchange=""></select></td>
								    <td><label><b>Identificaci&oacute;n:</b></label></td>
								    <td><input type="text" id="identificacion_conyuge_juridica" name="identificacionidentificacionConyugue" number="number" maxlength="20" minlength="10">
								    </tr>								   						  						  
								</table>	
								</div> 
							</div>	
							 <!-- Fin datos actividad -->
							 
							 <!-- Inicio datos Situacion Financiera -->
							<div class="panel panel-primary">
						     <div class="panel-heading">Situacion Financiera: Total de activos y pasivos</div>
								<div class="panel-body">
								<table>
								  <tr>
								  <td><label><b>Salario Mensual:</b></label></td>
								    <td><input type="number" id="salario_mensual_juridica" name="salarioMensual"></td>
    							    <td><label><b>Activos:</b></label></td>
								    <td><input type="number" id="activos_juridica" name="activosFinanciero">
								    </tr>
								    <tr>
								    <td><label><b>Otros Ingresos:</b></label></td>
								    <td><input type="number" id="otros_ingresos_juridica" name="otrosIngresos"></td>
    							    <td><label><b>Pasivos:</b></label></td>
								    <td><input type="number" id="pasivos_juridica" name="pasivosFinanciero"></td>
								    </tr>
								     <tr>
								    <td><label><b>Egresos:</b></label></td>
								    <td><input type="number" id="egresos_juridica" name="egresos"></td>
    							    <td><label><b>A-P= Patrimonio:</b></label></td>
								    <td><input type="number" id="patrimonio_juridica" name="patrimonio"></td>
								    </tr>
								    <tr>
								    <td><label><b>V/Neto Ingreso - Egreso:</b></label></td>
								    <td><input type="number" id="ingresos_egresos_juridica" name="vNeto"></td>
								    </tr>
								</table>	
								</div> 
							</div>	
							 <!-- Fin datos actividad -->
							
							 <!-- Inicio datos Vinculos Existentes - 4 columnas -->
							<div class="panel panel-primary">
						     <div class="panel-heading">Vinculos Existentes entre el solicitante, asegurado, beneficiario</div>
								<div class="panel-body">
								<table>
								  <tr>
								    </tr>
								    <tr>
								    <td><label><b>Es Usted el asegurado de la poliza?:</b></label>
								    <select id="es_asegurado_juridica" ></select>
  									</td>								    
								    </tr>
								    <tr>
								    <td><label><b>Es Usted el beneficiario de la poliza?:</b></label>
								    <select  id="es_beneficiario_juridica"> </select>
  									</td>								    
								    </tr>								    							    
								</table>
								<br>
								
							<div class="panel panel-primary">
						     <div class="panel-heading">Vinculos Existentes entre el Solicitante y el Asegurado</div>
								<div class="panel-body">
								<table>
								  <tr>
								    <td><label><b>Tipo Identificaci&oacute;n:</b></label></td>
								    <td><select class="tipo_identificacion" id="tipo_identificacion_asegurado_juridica" onchange=""></select></td>
								    <td><label><b>Identificaci&oacute;n:</b></label></td>
								    <td><input type="text" id="identificacion_asegurado_juridica" name="identificacion" maxlength="30">
								    <td><label><b>Nombre Completo:</b></label></td>
								    <td><input type="text" id="nombre_asegurado_juridica" name="nombreCompleto">
								    </tr>
								    <tr>
								    <td><label><b>Direccion Domicilio:</b></label></td>
								    <td colspan='2'><input type="text" id="direccion_asegurado_juridica" name="domicilio">							  
								    <td><label><b>Telefono Domicilio:</b></label></td>
								    <td><input type="text" maxlength="10" id="telefono_asegurado_juridica" name="telefonoDomicilio" onkeypress="validarKeyPress(event,/[0-9]/)">
								    </tr>
								    <tr>
								    <td><label><b>Celular:</b></label></td>
								    <td><input type="text" maxlength="10" id="celular_asegurado_juridica" name="celularAsegurado" onkeypress="validarKeyPress(event,/[0-9]/)">
								    <td><label><b>Relacion:</b></label></td>
								    <td><input type="text" id="relacion_asegurado_juridica" name="relacionAsegurado">
								    </tr>
								</table>	
								</div>							
								</div>
								
										<div class="panel panel-primary">
						     <div class="panel-heading">Vinculos Existentes entre el Asegurado y el Beneficiario</div>
								<div class="panel-body">
								<table>
								  <tr>
								    <td><label><b>Tipo Identificaci&oacute;n:</b></label></td>
								    <td><select class="tipo_identificacion" id="tipo_identificacion_beneficiario_juridica" onchange=""></select></td>
								    <td><label><b>Identificaci&oacute;n:</b></label></td>
								    <td><input type="text" id="identificacion_beneficiario_juridica" name="identificacion" maxlength="30">
								    <td><label><b>Nombre Completo:</b></label></td>
								    <td><input type="text" id="nombre_beneficiario_juridica" name="nombreCompleto">
								    </tr>
								    <tr>								    							    
								    
								    <td><label><b>Direccion Domicilio:</b></label></td>
								    <td  colspan='2'><input type="text" id="direccion_beneficiario_juridica" name="domicilio">							  
								    <td><label><b>Telefono Domicilio:</b></label></td>
								    <td><input type="text" maxlength="10" id="telefono_beneficiario_juridica" name="telefonoDomicilio" onkeypress="validarKeyPress(event,/[0-9]/)">
								    </tr>
								    <tr>
								    <td><label><b>Celular:</b></label></td>
								    <td><input type="text" maxlength="10" id="celular_beneficiario_juridica" name="celularAsegurado" onkeypress="validarKeyPress(event,/[0-9]/)">
								    <td><label><b>Relacion:</b></label></td>
								    <td><input type="text" id="relacion_beneficiario_juridica" name="relacionAsegurado">
								    </tr>
								</table>	
								</div>							
								</div>
								
									
								</div> 
							</div>	
							 <!-- Fin datos actividad -->
					
		            </fieldset>		       
<!--  Fin Persona Juridica -->    
                   
             </section>
             
             <h2>Emision</h2>
			<section>

				<div class="panel panel-primary">
					<div class="panel-heading">Vehiculos</div>
					<div class="panel-body">
						<table id="tablaFinalVehiculos">


						</table>
					</div>
				</div>

				<!--  <div class="panel panel-primary">
					<div class="panel-heading">Asegurado</div>
					<div class="panel-body">
						<table id="tablaFinalVehiculos">
						
						  <tr>
								    <td><label><b>Tipo Identificaci&oacute;n:*</b></label></td>
								    <td><select class="tipo_identificacion_asegurado" id="tipo_identificacion_principal" onchange="mostrarTipoIdentificacion(); mostrarAdicionales();" required="required"></select></td>
								    <td><label><b>Identificaci&oacute;n:*</b></label></td>
								    <td><input type="text" id="identificacion" name="identificacion" required="required" maxlength="20" onchange="cargarPorIdentificacion('datosClienteInicio', this.value)">
								    <div id="loading_identificacion_asegurado" class="loading_identificacion" hidden="" ><span id="loading-msg">Cargando...</span><img  src="../static/images/ajax-loader.gif" /></div></td>								    
								    <td> <input type="hidden" id="codigo_cliente_ensurance_asegurado" name="codigoClienteEnsurance"> </td>
								    <td> <input type="hidden" id="codigo_entidad_ensurance_asegurado" name="codigoEntidadEnsurance"> </td>
								  </tr>	
								   <tr id="filaNatural">
								    <td><label><b>Nombres:*</b></label></td>
								    <td><input type="text" id="nombres_asegurado" name="nombres" required="required"></td>
								    <td><label><b>Apellidos:*</b></label></td>
								    <td><input type="text" id="apellidos_asegurado" name="apellidos" required="required"></td>
								  </tr>
								  <tr id ="filaJuridica" hidden="true">
								    <td  colspan="1"><label><b>Nombres Empresa:*</b></label></td>
								    <td  colspan="3"><input type="text" id="nombre_completo_aseguado" name="nombre_completo" required="required"></td>								    
								  </tr>	

						</table>
					</div>
				</div>

				<div class="panel panel-primary">
					<div class="panel-heading">Vehiculos</div>
					<div class="panel-body">
						<table id="tablaFinalVehiculos">


						</table>
					</div>
				</div> -->
				<button type='button' class='btn btn-success btn-xs' onclick="confirmarDatosVehiculos()">
										<span class='glyphicon glyphicon glyphicon-check'></span>Confirmar</button>
			<div id="loading_tablaFinal" class="loading_identificacion" hidden="" ><span id="loading-msgTablaFinal"></span><img  src="../static/images/ajax-loader.gif" /></div>
				
			<button type='button' class='btn btn-success btn-xs' id='emision_poliza' onclick="emisionVH()" disabled="disabled">
										<span class='glyphicon glyphicon glyphicon-edit'></span>Emision
										Poliza
									</button>
					    
			
								</section>
        </form>
        
        
    </body>
</html> 