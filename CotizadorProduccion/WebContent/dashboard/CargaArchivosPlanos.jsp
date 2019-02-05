<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Carga Archivos Planos</title>
	<script id="tipoObjetoMetodos" src="../static/js/vehiculos/carga.archivo.js" tipoObjetoValor="Ninguno"></script>
	<script src="../static/js/vehiculos/formulados.js"></script>
	<script src="../static/js/cotizador/comun.js"></script>
	<!-- Core CSS - Include with every page -->
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="../static/js/util.js"></script>
	
	<!-- Table Tools -->
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.tableTools.js"></script>
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.tableTools.css" rel="stylesheet">
	
	
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
		</style>
<script>
	var casoEspecial=false;
	var validar = 0;
	var cargadoDatosUPLA=false;
	var xhr = new JSONHttpRequest();
	$(document).ready(function (){			  	
		<!--activarMenu("Cotizacion");--->
		
	});
	
    function performAjaxSubmit() {    	
    	var tipo_carga = $("#tipo_carga").val();
    	var archivo = document.getElementById("archivoCarga").files[0];
    	
    	if(archivo.name.indexOf(".csv")!=archivo.name.length-4){
    		alert("Formato del archivo incorrecto!");
    		return;
    	}
    	
        var formdata = new FormData();

       formdata.append( $("#tipo_carga").val()+",cargar", archivo);
       
       //var xhr = new XMLHttpRequest();
		$("#buscando").fadeIn("slow");
        xhr.open("POST","../CargaArchivosPlanosController", true);
        xhr.send(formdata);  
        xhr.onload = function(e) {
        	var respuesta=xhr.responseJSON;
            if (respuesta.success) {            	
            	cargarTabla();
            }
            else{
            	alert(respuesta.error);
            }
        };     
    } 
    
    function JSONHttpRequest() {
    	var _xmlHttpRequest = new XMLHttpRequest();
    	var _responseJSON = null;
    	var _userContentType = false;
    	// INFO Defining 'this' as '_self' improves compression, since keywords won't be shortened,
    	//      but variables will, and it delivers us from the need to reset the scope of the anonymous
    	//      function in the for-loop via call() or apply().
    	var _self = this;

    	var property = {
    		get: function() {
    			try {
    				_responseJSON = _xmlHttpRequest.responseText ? (!_responseJSON ? JSON.parse(_xmlHttpRequest.responseText) : _responseJSON) : null;
    			}
    			catch (e) {
    				if (_self.strictJSON)
    					throw e;
    			}
    			return _responseJSON;
    		},
    		enumerable: true,
    		configurable: true
    	}
    	
    	_self.strictJSON = true;
    	Object.defineProperty(_self, 'responseJSON', property);
    	
    	_self.sendJSON = function(data) {
        	try {
        		data = JSON.stringify(data);
        		_responseJSON = null;
        		if (!_userContentType)
        			_xmlHttpRequest.setRequestHeader('Content-Type', 'application/json;charset=ISO-8859-1');    		
        		_userContentType = false;
    		}
    		catch (e) {
    			if (_self.strictJSON)
    				throw e;
    		}
    		_xmlHttpRequest.send(data);
        }
    	
    	// INFO proxy setup
    	
    	function proxy(name) {
    		try {
    			if ((typeof _xmlHttpRequest[name]) == 'function') {
    				_self[name] = function() {
    					if (name == 'setRequestHeader')
    						_userContentType = arguments[0].toLowerCase() == 'content-type';
    					return _xmlHttpRequest[name].apply(_xmlHttpRequest, Array.prototype.slice.apply(arguments));
    				};
    			}
    			else {
    				property.get = function() { return _xmlHttpRequest[name]; }
    				property.set = function(value) { _xmlHttpRequest[name] = value; }
    				Object.defineProperty(_self, name, property);	
    			}
    		}
    		catch (e) {
    			// NOTE Swallow any exceptions, which may rise here.
    		}
    	}
    	
    	// FIX onreadystatechange is not enumerable [Opera]
    	proxy('onreadystatechange');
    	
    	for (n in _xmlHttpRequest)
    		proxy(n);
    }
    
    function cargarTabla(){
    	var archivo = $("#archivoCarga").val();
    	 $.ajax({
    		 	url :'../CargaArchivosPlanosController',
    		 	data :{
    		 		"tipoConsulta":"consultaArchivo",
    		 		"archivo":archivo
    		 	},
    		 	type : 'POST',
    		 	datatype : 'json',
    		 	success : function(data) {
					var existenRegistro = false;
					$('#dataTable').show();
					$('#dataTable_wrapper').show();	 					
					$('#dataTableContent').html('');
					$("#datos_temporal").val("");
					var listadoRegistros = 0;
					listadoRegistros = data.listadoRegistros;
					var auxiliar = "";
					if(listadoRegistros.length > 0){
						$.each(listadoRegistros, function(index){
							
							var aux="	<tr class='odd gradeX'>" +
							" <td relation='id'>"+ listadoRegistros[index].id +"</td>" +
							" <td relation='identificacionI'>"+ listadoRegistros[index].identificacion +"</td>" +
							" <td relation='nombresI'>"+ listadoRegistros[index].nombresI +"</td>" +
							" <td relation='apellidosI'>"+ listadoRegistros[index].apellidosI +"</td>" +
							" <td relation='placa'>"+ listadoRegistros[index].placa +"</td>" +
							" <td relation='marca'>"+ listadoRegistros[index].marca +"</td>" +
							" <td relation='modelo'>"+ listadoRegistros[index].modelo +"</td>" +
							" <td relation='tipo'>"+ listadoRegistros[index].tipo +"</td>" +
							" <td relation='anioFabricacion'>"+ listadoRegistros[index].anioFabricacion +"</td>" +
							" <td relation='dispositivoSeguridad'>"+ listadoRegistros[index].dispositivoSeguridad +"</td>" +
							" <td relation='valorAsegurado'>"+listadoRegistros[index].valorAsegurado+"</td>" +
							" <td relation='sucursal'>"+ listadoRegistros[index].sucursal +"</td>" +
							" <td relation='error'>"+ listadoRegistros[index].error +"</td>" +
							" <td width='80px' align='center'>" +
								" <input type='hidden'  value='"+ listadoRegistros[index].id +"'/>" +
								" <button type='button' class='btn btn-success btn-xs actualizar-btn' data-toggle='modal' data-target='#add'>" +
									" <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
								" </button>" +
								" <button type='button' class='btn btn-danger btn-xs eliminar-btn'>" +
								  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span>&nbsp; Eliminar &nbsp;" +
								" </button>" +
							"</td>" +
						"</tr>";
								auxiliar +=	aux;
							
						
						$("#buscando").fadeOut("slow");	 					
						});	
						
						// Evitar Reinicializacion datatable
						var oTable = $('#dataTable').dataTable();
						oTable.fnDestroy();							
						$('#dataTable tbody').html(auxiliar);
						
						/* Inicio Controles Actualizar Registro*/
						$(".actualizar-btn").bind({click: function() {
								$("#id").val($(this).parent().children().first().val());
								var elem = $(this).parent();
								var bandera = 1;
								do {
									elem = elem.prev();
									if (elem.is("td")){
										var elemCode = elem.attr("relation");
										elementType(elem.text(), elemCode, $("#"+elemCode).attr("type"));
									}else {
										bandera = 0;
									}
								} while (bandera == 1);
							  }
						});
						/* Fin Controles Actualizar Registro*/
							
							$(".eliminar-btn").bind({click: function(event) {
								var r = window.confirm("Seguro que desea eliminar el registro " + $(this).parent().parent().children().first().text());
								if (r == true){
									codigo = $(this).parent().children().first().val();
									tipoConsulta = "eliminar";
									enviarDatos(codigo, identificacionI,placa, marca, modelo,tipo,anioFabricacion,dispositivoSeguridad,valorAsegurado,sucursal, tipoConsulta,nombresI,apellidosI);
							    	$(this).parent().parent().remove();
								}
							}
							});	
				
						existenRegistro = true;
					}
				if(!existenRegistro){
						var oTable = $('#dataTable').dataTable();
						oTable.fnDestroy();							
						$('#dataTable tbody').html("<tr><td colspan='12'>No existen Registros</td></tr>");
						$("#buscando").fadeOut("slow");
				}
				}
			});
    	 
    	 
    	 /* Inicio Controles Grabar Registro*/
			$(".cerrarModal").click(function() {
				cargarTabla();
			});
			
    	 
    	 /* Inicio Controles Grabar Registro*/
			$("#save-record").click(function() {
				$(".required").css("border", "1px solid #ccc");
				$(".required").each(function(index) {
					var cadena = $(this).val();
					if (cadena.length <= 0) {
						$(this).css("border", "1px solid red");
						alert("Por favor ingrese el campo requerido");
						$(this).focus();
						return false;
					}
				});
	
				id = $("#id").val();
				identificacionI = $("#identificacionI").val();
				nombresI = $("#nombresI").val();
				apellidosI = $("#apellidosI").val();
				placa = $("#placa").val();
				marca = $("#marca").val();
				modelo = $("#modelo").val();
				tipo = $("#tipo").val();
				anioFabricacion = $("#anioFabricacion").val();
				dispositivoSeguridad = $("#dispositivoSeguridad").val();
				valorAsegurado = $("#valorAsegurado").val();
				sucursal = $("#sucursal").val();
				tipoConsulta = "actualizar";
				enviarDatos(id, identificacionI,placa, marca, modelo,tipo,anioFabricacion,dispositivoSeguridad,valorAsegurado,sucursal, tipoConsulta,nombresI,apellidosI);
				cargarTabla();
			});
		/* Fin Controles Grabar Registro*/
    	 
    	 function enviarDatos(id,identificacionI,placa,marca,modelo,tipo,anioFabricacion,dispositivoSeguridad,valorAsegurado,sucursal, tipoConsulta,nombresI,apellidosI){				
				$.ajax({
					url : '../CargaArchivosPlanosController',
					data : {
						"id" : id,
						"identificacionI" : identificacionI,
						"nombresI":nombresI,
						"apellidosI":apellidosI,
						"placa" : placa,
						"marca" : marca,
						"modelo" : modelo,
						"tipo": tipo,
						"anioFabricacion" : anioFabricacion,
						"dispositivoSeguridad" : dispositivoSeguridad,
						"valorAsegurado" : valorAsegurado,
						"sucursal" : sucursal,
						"tipoConsulta" : tipoConsulta,
						"tipo_carga": $("#tipo_carga").val()
					},
					type : 'POST',
					datatype : 'json',
					success : function(data) {
						if(data.success){
							$("#msgPopup").show();
							cargarTabla();
						}
						else
							alert(data.error);
					}
				});
			}
    }    
    
    function cotizar(){
    	$("#creando").fadeIn("slow");
    	var tipo_carga = $("#tipo_carga").val();
    	var archivo = $("#archivoCarga").val();
    	var tipoObjeto = $("#tipo_objeto").val();
    	var punto_venta = $("#punto_venta").val();
    	var codigoEntidadEnsurance = $("#codigoEntidadEnsurance").val();
    	var tipoIdentificacion = $("#tipo_identificacion_principal").val();
    	var identificacion = $("#identificacion").val();
    	var nombres = $("#nombres").val();
    	var apellidos = $("#apellidos").val();
    	var nombre_completo = $("#nombre_completo").val();
    	var vigencia = $("#vigencia").val();
    	var agentes = $("#agentes").val();
    	var porc_comision = $("#porc_comision").val();
    	var productos = $("#productos").val();
    	var grupo_productos = $("#grupo_productos").val();
    	var tasas = $("#tasas").val();
    	var tipoObjeto = $("#tipo_objeto").val();
    	var tasasValor = $("#tasasValor").val();
    	var coberturaTR = $('#coberturaTR_check').is(':checked');
	    var coberturaDT = $('#coberturaDT_check').is(':checked');
	    var coberturaRC = $('#coberturaRC_check').is(':checked');
    	 var tasaVehiculosCerrados = "";
			/*if (tipoObjeto != "VHDinamico") {
				//var grupoPorProductoId = $("#productos").val();
				// Validacion de Productos Formulados
				// Grupo Por Producto: 100 QBE Box
				if(productos=="100"){
				tasaVehiculosCerrados = obtenerInformacionProducto(numero);
				// Error provocado para que no pase al siguiente paso
				//if (tasaVehiculosCerrados == 0)
					//$("#wizard").previous();
				//}
				
				var tasaVariable = $("#tasas").val();
				// Verificacion de tasa variable 
				if(tasaVariable!=""){
				var retornoValidacion = validacionInformacionTasaProducto(numero);
					if(retornoValidacion[0]=="ERROR"){
						alert(retornoValidacion[1]);	
						$("#wizard").previous(); // Error provocado para que no se pase al siguiente paso del wizard
					}
				}
			}*/

    	 var porcentaje_suma_asegurada = "";
			var monto_fijo = "";
			var valor_siniestro = "";
			var coberturas = "";
			var valorExcesoRC = 0;
    	 var paquete1_check = $('#paquete1_check').is(':checked');
    	 var paquete2_check = $('#paquete2_check').is(':checked');
    	 var paquete3_check = $('#paquete3_check').is(':checked');
    	 var paquete4_check = $('#paquete4_check').is(':checked');
    	 var paquete5_check = $('#paquete5_check').is(':checked');
    	 if ($('#coberturaTR_check').is(':checked')) {
				porcentaje_suma_asegurada = $('#porcentaje_suma_asegurada').val();
				monto_fijo = $('#monto_fijo').val();
				valor_siniestro = $('#valor_siniestro').val();
			}

		if ($('#coberturaDT_check').is(':checked')) {
				porcentaje_suma_asegurada = $('#porcentaje_suma_aseguradaDT').val();
			}

		if ($('#paquete1_check').is(':checked')) {
				var cxp = $(".check_Black_Cobertura_");
				coberturas = "";
				for (var i = 0; i < cxp.length; i++) {
					if ($(cxp[i]).is(":checked") && (coberturas.indexOf($(cxp[i]).attr("id").split("_")[3] + ",") == -1)) {
						coberturas += $(cxp[i]).attr("id").split("_")[3] + ",";
					}
				}
			}

		if ($('#paquete2_check').is(':checked')) {
				var cxp = $(".check_Blue_Cobertura_");
				coberturas = "";
				for (var i = 0; i < cxp.length; i++) {
					if ($(cxp[i]).is(":checked") && coberturas.indexOf($(cxp[i]).attr("id").split("_")[3] + ",") == -1) {
						coberturas += $(cxp[i]).attr("id").split("_")[3] + ",";
					}
				}
			}

		if ($('#paquete3_check').is(':checked')) {
				var cxp = $(".check_Gold_Cobertura_");
				coberturas = "";
				for (var i = 0; i < cxp.length; i++) {
					if ($(cxp[i]).is(":checked") && coberturas.indexOf($(cxp[i]).attr("id").split("_")[3] + ",") == -1) {
						coberturas += $(cxp[i]).attr("id").split("_")[3] + ",";
					}
				}
			}

		if ($('#paquete4_check').is(':checked')) {
				var cxp = $(".check_Platinum_Cobertura_");
				coberturas = "";
				for (var i = 0; i < cxp.length; i++) {
					if ($(cxp[i]).is(":checked") && coberturas.indexOf($(cxp[i]).attr("id").split("_")[3] + ",") == -1) {
						coberturas += $(cxp[i]).attr("id").split("_")[3] + ",";
					}
				}
			}

		if ($('#paquete5_check').is(':checked')) {
				var cxp = $(".check_Sin_Cobertura_");
				coberturas = "";
				for (var i = 0; i < cxp.length; i++) {
					if ($(cxp[i]).is(":checked") && coberturas.indexOf($(cxp[i]).attr("id").split("_")[3] + ",") == -1) {
						coberturas += $(cxp[i]).attr("id").split("_")[3] + ",";
					}
					if ($("#excesoResponsabilidadCivil_" + ($(cxp[i]).attr("id").split("_")[3]) + "_").html() != null) {
						valorExcesoRC = $("#valorUnicoResponsabilidadCivil").val();
					}
				}
			}

		
    	tipoConsulta = "crear";
    	$.ajax({
    		url : '../CargaArchivosPlanosController',
    		data:{
    			"tipo_carga":tipo_carga,
    			"archivo":archivo,
    			"tipoobjeto":tipoObjeto,
    			"punto_venta":punto_venta,
    			"codigoEntidadEnsurance":codigoEntidadEnsurance,
    			"tipoIdentificacion":tipoIdentificacion,
    			"identificacion":identificacion,
    			"nombres":nombres,
    			"apellidos":apellidos,
    			"nombre_completo":nombre_completo,
    			"vigencia":vigencia,
    			"agentes":agentes,
    			"porc_comision":porc_comision,
    			"productos":productos,
    			"grupo_productos":grupo_productos,
    			"tasas":tasas,
    			"tasasValor":tasasValor,
    			"coberturaTR":coberturaTR,
    			"coberturaDT":coberturaDT,
    			"coberturaRC":coberturaRC,
    			"porcentajeSumaAsegurada":porcentaje_suma_asegurada,
    			"montoFijo":monto_fijo,
    			"valorSiniestro":valor_siniestro,
    			"tasaVehiculosCerrados":tasaVehiculosCerrados,
    			"coberturasAdicionales" : coberturas,
    			"valorExcesoRC":valorExcesoRC,
    			"paquete1_check":paquete1_check,
    			"paquete2_check":paquete2_check,
    			"paquete3_check":paquete3_check,
    			"paquete4_check":paquete4_check,
    			"paquete5_check":paquete5_check,
    			"tipoConsulta" : tipoConsulta
    		},
			type : 'POST',
			datatype : 'json',
			success : function(data) {
				$("#creando").fadeOut("slow");
				if(data.success)
				{
					var existenRegistro = false;
					$('#cotizacionesCreadas').show();
					$('#dataTableCotizaciones').show();
					$('#dataTable_wrapperCotizaciones').show();	 					
					$('#dataTableContentCotizaciones').html('');
					$("#datos_temporal").val("");
					var listadoCotizaciones = 0;
					listadoCotizaciones = data.listadoCotizaciones;
					var auxiliar = "";
					if(listadoCotizaciones.length > 0){
						$.each(listadoCotizaciones, function(index){
							
							var aux="	<tr class='odd gradeX'>" +
							" <td relation='cotizacion'>"+
							" <a class='btn btn-success btn-xs link' href='../dashboard/Cotizacion.jsp?id="+ listadoCotizaciones[index].cotizacion +"&carga= masivos'>" +
								" <span>"+ listadoCotizaciones[index].cotizacion +"</span>" +
							" </a></td>" +
							" <td relation='identificacionCliente'>"+ listadoCotizaciones[index].identificacionCliente +"</td>" +
							" <td relation='nombreCliente'>"+ listadoCotizaciones[index].nombreCliente +"</td>" +
						"</tr>";
								auxiliar +=	aux;
							
						//$("#buscando").fadeOut("slow");	 					
						});	
						
						// Evitar Reinicializacion datatable
						var oTable = $('#dataTableCotizaciones').dataTable();
						oTable.fnDestroy();							
						$('#dataTableCotizaciones tbody').html(auxiliar);
						existenRegistro = true;
						
						if ($("#tipo_carga").val() == 2){
							window.location= "../dashboard/Cotizacion.jsp?id="+ listadoCotizaciones[0].cotizacion +"&carga= masivos&carga2= grupal";
							$('#cotizacionesCreadas').hide();
						}
						$("a[href='#next']").click();
				}
				if(!existenRegistro){
						var oTable = $('#dataTableCotizaciones').dataTable();
						oTable.fnDestroy();							
						$('#dataTableCotizaciones tbody').html("<tr><td colspan='12'>No existen Registros</td></tr>");
						//$("#buscando").fadeOut("slow");
				}
				}	
				else{
					alert(data.error)
				}
			}
    	});
    	
    }
  
     </script>
</head>
<body>
	</br><div style="font-size: medium; font-weight:bold; color:red;">Por favor realizar cargas masivas a partir de las 5pm</div></br>
	<div class="panel panel-default">
		<div class="panel-heading">
			<b>Datos necesarios carga masiva</b>
		</div>
		<div class="panel-body">
			<table>
				<tr>
					<td style="width: 25%"><label><b>Tipo de Carga:</b></label></td>
					<td style="width: 25%"><select name="tipo_carga" id="tipo_carga" required="required" onchange = "verificarTipoCarga(this.value)">
						<option value= "">Seleccione una opción</option>
						<option value= "1">Individual</option>
						<option value= "2">Grupal</option>
						</select>
					</td>
					<td style="width: 25%"><label><b>Tipo de Objeto:</b></label></td>
					<td style="width: 25%"><select name="tipo_objeto" id="tipo_objeto" required="required" onchange = "verificarProducto(this.value)">
								<option value="0">Seleccione una opcion</option>
								<option value="VHDinamico">Vehiculos Dinamicos</option>
								<option value="Pesados">Vehiculos Cerrados Pesados</option>
								<option value="Livianos">Vehiculos Cerrados Livianos</option>
								<option value="Motos">Vehiculos Cerrados Motos</option>
								<option value="Publicos">Vehiculos Cerrados Publicos</option>
						</select>
					</td>
				</tr>
			</table>
			<br>
		<div id ="datos_clientes" style = "display:none;">	
			<table>
				<tr>
					<td colspan="4">
					<br>
					 <!-- Inicio datos cliente - 4 columnas -->
							<div class="panel panel-primary">
						     <div class="panel-heading">Datos del Cliente</div>
								<div class="panel-body">
								<table>
								  <tr>
								    <td><label><b>Tipo Identificaci&oacute;n:*</b></label></td>
								    <td><select class="tipo_identificacion datosVehiculo" id="tipo_identificacion_principal" onchange="mostrarTipoIdentificacion(); mostrarAdicionales();" required="required"></select></td>
								    <td><label><b>Identificaci&oacute;n:*</b></label></td>
								    <td><input type="text" id="identificacion" class="datosVehiculo" name="identificacion" required="required" maxlength="20" onchange="cargarPorIdentificacion('datosClienteInicio', this.value)" onblur="validadorIdentificaciones();">
								    <div id="loading_identificacion" class="loading_identificacion" hidden="" ><span id="loading-msg">Cargando...</span><img  src="../static/images/ajax-loader.gif" /></div></td>								    
								    <td> <input type="hidden" id="codigoClienteEnsurance" name="codigoClienteEnsurance"> </td>
								    <td> <input type="hidden" id="codigoEntidadEnsurance" name="codigoEntidadEnsurance"> </td>
								  </tr>	
								   <tr id="filaNatural">
								    <td><label><b>Nombres:*</b></label></td>
								    <td><input type="text" id="nombres" class="datosVehiculo" name="nombres" required="required"></td>
								    <td><label><b>Apellidos:*</b></label></td>
								    <td><input type="text" id="apellidos" class="datosVehiculo" name="apellidos" required="required"></td>
								  </tr>
								  <tr id ="filaJuridica" hidden="true">
								    <td  colspan="1"><label><b>Nombres Empresa:*</b></label></td>
								    <td  colspan="3"><input type="text" id="nombre_completo" class="datosVehiculo" name="nombre_completo" required="required"></td>								    
								  </tr>							  
								</table>	
								</div> 
							</div>	
							 <!-- Fin datos cliente -->
							 </td>
					</tr>
				</table>
			</div>
			<br>
			<div id ="datos_cerrados" style = "display:none;">
				<table>
					<tr>
						<td style="width: 15%"><label><b>Grupo de Productos:*</b></label></td>
						<td style="width: 35%"><select name="grupo_productos" id="grupo_productos" onchange="obtenerProductosGrupo();limpiarTasaProducto();" required="required" class="datosVehiculo"></select></td>
						<td style="width: 15%"><label><b>Producto:*</b></label></td>
						<td style="width: 35%"><select name="productos" id="productos" required="required" onchange="obtenerTasaPorProducto();cargarPuntosVenta();metodoObtenerProductoFormulados(this.value);verificarTipoProducto(this.value);" class="datosVehiculo"></select></td>
						<td style="width: 35%"><input type="hidden" id="codigoProductos" name=""></td>
									
					</tr>
					<tr>
						<td style="width: 15%"><label><b>Tasa:*</b></label></td>
						<td style="width: 35%"><input type="text" name="tasa" id="tasa" disabled="disabled"><select name="tasas" id="tasas" style="display: none;"></select></td>
						<td style="width: 15%"></td>
						<td style="width: 35%"></td>
					</tr>
				</table>
			</div>
			<div id ="datos_dinamico" style = "display:none;">
			<table>
					<tr>
						<td style="width: 15%"><label><b>Punto Venta:*</b></label></td>
						<td style="width: 35%"><select name="punto_venta" id="punto_venta" required="required" class="datosVehiculo obligatoriosTarifacion" onchange="verificarPuntosVenta();"></select></td>
						<td style="width: 15%"><label><b>Vigencia de la p&oacute;liza:*</b></label></td>
						<td style="width: 35%"><select name="vigencia" id="vigencia" required="required" class="datosVehiculo" onchange=""></select></td>
					</tr>
					<tr>
						<td style="width: 15%" colspan="1"><label><b>Agente de Seguros:*</b></label></td>
						<td style="width: 85%" colspan="3"><select name="agentes" id="agentes" class="datosVehiculo" required="required" onChange="obtenerAgenteComision()"></select></td>
					</tr>
						<tr >
					    <td ><label><b>Comisi&oacute;n del agente (%):*</b></label></td>
					    <td><input type="text" id="porc_comision" name="porc_comision" readonly onkeypress="return justNumbers(event);" onblur="validarRangoComisionMLDealer();">
					    <select hidden="hidden" id="porc_comision_cb" name="porc_comision_cb" onchange="$('#porc_comision').val($('#porc_comision_cb').val());" readonly></select></td>
					    <td colspan="2"></td>
					 </tr>
			</table>
			</div>
		</div>
	</div>

	<div id="lista_coberturas" style="display: none;"></div>
	<input type="hidden" id="anio_max_seguro" name="anio_max_seguro">
	<input type="hidden" id="suma_asegurada_valor_tr" name="suma_asegurada_valor_tr">
	<input type="hidden" id="monto_fijo_valor_tr" name="monto_fijo_valor_tr">
	<input type="hidden" id="siniestro_valor_tr" name="siniestro_valor_tr">
	<input type="hidden" id="valor_exceso_rc" name="valor_exceso_rc">
	<input type="hidden" id="estado_cotizacion" name="estado_cotizacion">
	<fieldset class="border">
	<input type="hidden" id="valorUnicoResponsabilidadCivil">
	<div id="coberturas_unico" class="row clearfix" ></div>
	<br>
	<div id="lista_vehiculos" style="margin-left:10px;margin-right: 20px;"></div>
	<br>
	</fieldset>
	<div class="panel panel-default" id="panelNuevoArchivo">
		<div class="panel-heading">
			<b>Carga Masiva</b>
		</div>
		<div class="panel-body">
			<table>
				<tbody id="archivoNuevo">
				
					<tr>
						<td>
							<form id="form1">
								<input id="archivoCarga" name="archivoCarga" value="" type="file" /></input>
								<button class='btn btn-primary btn-xs ' id="uploadBtn" type="button" value="" onclick= "performAjaxSubmit();">
								<span class="glyphicon glyphicon-upload"></span>Subir &nbsp;
								</button>
							</form>
						</td>
					</tr>
					<tr>
						<th><div id="buscando" hidden="hidden">
								<div style="margin-top: 10px;">
									<span id="loading-msg"></span><img src="../static/images/ajax-loader.gif" /> Buscando la informacion, por favor espere...
								</div>
							</div></th>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
	<!-- Datatable -->
	<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">	
				<table class="table table-striped table-bordered table-hover"
					id="dataTable" style="font-size: x-small;">
					<thead>
						<tr>
							<th>Id</th>
							<th>Identificación</th>
							<th>Nombres</th>
							<th>Apellidos</th>
							<th>Placa</th>
							<th>Marca</th>
							<th>Modelo</th>
							<th>Tipo</th>
							<th>Año Fabricación</th>
							<th>Dispositivo Seguridad</th>
							<th>Valor Asegurado</th>
							<th>Sucursal</th>
							<th>Error</th>
						</tr>
					</thead>
					<tbody id="dataTableContent" class="searchable" style="font-size: x-small;">
											
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- Datatable -->
	
	<!-- Modal -->
		<div class="modal fade" id="add" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form id="formCrud">
						<div class="modal-header">
							<button type="button" class="close cerrarModal" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Registro Archivo</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup" style="display: none;">El registro se grabo con exito.</div>
							<div class="form-group">
								<input type="hidden"class="form-control" id="id">
								<label>Identificación</label> 
								<input type="text"class="form-control" id="identificacionI">
								<label>Nombres</label> 
								<input type="text" class="form-control" id="nombresI">
								<label>Apellidos</label> 
								<input type="text" class="form-control" id="apellidosI">
								<label>Placa</label> 
								<input type="text"class="form-control" id="placa">
								<label>Marca</label> 
								<input type="text"class="form-control required" id="marca">
								<label>Modelo</label> 
								<input type="text"class="form-control required" id="modelo">
								<label>Tipo</label> 
								<input type="text"class="form-control required" id="tipo">
								<label>Año fabricación</label> 
								<input type="text"class="form-control required" id="anioFabricacion">
								<label>Dispositivo de Seguridad</label> 
								<input type="text"class="form-control required" id="dispositivoSeguridad">
								<label>Valor Asegurado</label> 
								<input type="text"class="form-control required" id="valorAsegurado">
								<label>Sucursal</label> 
								<input type="text"class="form-control required" id="sucursal">
							</div>
						</div>
						<div class="modal-footer">
							
							<button type="button" class="btn btn-primary" data-dismiss="modal" id="save-record">Guardar Cambios</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	<!-- Modal -->
	
	<div  id = "crearCotizacion"class="panel-body">
			<table>
				<tbody id="crearCotizacion">
					<tr>
						<td>
							<form id="formCotizacion">
								<button class='btn btn-primary btn-xs ' id="crearBtn" type="button" value="" onclick= "cotizar();">
									<span></span>Crear Cotización &nbsp;
								</button>
								<div id="creando" hidden="hidden">
								<div style="margin-top: 10px;">
									<span id="loading-msg"></span><img src="../static/images/ajax-loader.gif" /> Creando la cotizacion, por favor espere...
								</div>
							</div>
							</form>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
				<!-- Datatable -->
	<div id = "cotizacionesCreadas" style="display: none;" class="row">
		<div class="col-lg-6">
			<div class="table-responsive">	
				<table class="table table-striped table-bordered table-hover" id="dataTableCotizaciones" style="font-size: x-small;">
					<thead>
						<tr>
							<th>Cotización</th>
							<th>Identificación</th>
							<th>Cliente</th>
						</tr>
					</thead>
					<tbody id="dataTableContentCotizaciones" class="searchable" style="font-size: x-small;">
											
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- Datatable -->

</body>
</html>