<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Cache-control" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Cotizaciones Pendientes - CotizadorQBE</title>
	
	<!-- Core CSS - Include with every page -->
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	
	<!-- Table Tools -->
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.tableTools.js"></script>
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.tableTools.css" rel="stylesheet">
	
	<script id="tipoObjetoCargaInicial" src="../static/js/ganadero/carga.inicial.cotizador.ganadero.js" tipoObjetoValor="Ganadero"></script>
	<script id="tipoObjetoMetodos" src="../static/js/ganadero/metodos.ganadero.js" tipoObjetoValor="Ganadero"></script>
	<script src="../static/js/cotizador/comun.js"></script>
	
	<script src="../static/js/util.js"></script>
    <!-- Para el Datepicker-->
    <link href="../static/css/datepicker.css" rel="stylesheet">
    <script src="../static/js/bootstrap-datepicker.js"></script>
	<script>
		function GetQueryStringByParameter(name) {
	       name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	       var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	       results = regex.exec(location.search);
	       return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	   	}
	
		var estadoConsulta=GetQueryStringByParameter('State');
		
		var codigo = "";
		var producto_grupo  = "";
		var nombre = "";
		
		var nombre_comercial = "";
		var activo = "0";
		var plan_ensurance = "";
		var pro_ensurance = "";
		var nemotecnico = "";
		var tipoConsulta = "";
		var arrProductoGrupo = new Array();
		var arrCodigoProductoGrupo = new Object();
		
		$(document).ready(function() {
			activarMenu("CotizacionesPendientesCerrados");		 	
			 $('#dataTable').hide();
	    	 // Carga de agentes y puntos de venta	    	 
	    	 cargarAgentes("","");
	    	 cargarPuntosVentaSessionGanadero();
	    	 
	    	 
	    	 $('#LimpiarBtn').click(function(){	    	 	
	    	 	$("#cotizacion_id").val("");
	    	 	$("#tipo_objeto_id").val("0");
	    	 	$("#dp1").val("");
	    	 	$("#dp2").val("");
	    	 	$("#punto_venta_session").val("");	    	 	
	    	 	$("#agentes").val("");
	    	 	$("#identificacion").val("");
	    	 	$("#mis_cotizaciones").prop( "checked", false );
	    	 	$('#dataTable').hide();
	 			$('#dataTableContent').html('');
	 			$('#dataTable_wrapper').hide();
	 			$("#datos_temporal").val("");	 	
	    	 });
	    	 
	    	 $('#ConsultaBtn').click(function(){
	    		 $('#dataTable').hide();
	 			$('#dataTable_wrapper').hide();	 			
	 				 			
	    		 $("#buscando").fadeIn("slow");

	    		 // Validaciones de las busquedas	    		 
	    		 var cotizacionId = $("#cotizacion_id").val();
	    		 var fechaInicio = $("#dp1").val();
	    		 var fechaFin = $("#dp2").val();
	    		 var tipoObjeto = "7";
	    		 var puntoVenta = $("#punto_venta_session").val();
	    		 var agente = $("#agentes").val();
	    		 var identificacion = $("#identificacion").val();
	    		 var misCotizaciones = $("#mis_cotizaciones").is(":checked");
	    		 
	    		 if(tipoObjeto == "0" && cotizacionId=="" && fechaInicio == "" && fechaFin == "" && puntoVenta == "" && agente == "" && identificacion =="" && misCotizaciones ==false){
	    		 	alert("Ingrese al menos un campo de busqueda");
	    		 	$("#buscando").fadeOut("slow");
	    		 	return false;
	    		 }

				if(fechaInicio != "" && fechaFin=="" ){
	    		 	alert("Ingrese una fecha Fin de Busqueda");
	    		 	$("#buscando").fadeOut("slow");
	    		 	return false;
	    		 }
	    		 $.ajax({
	 				url : '../CotizacionController',
	 				data : {
	 					"fInicio" : fechaInicio,
	 					"fFinal" : fechaFin,
	 					"numeroCotizacion" : cotizacionId ,
	 					"codigoTipoObjeto": tipoObjeto,
	 					"puntoVenta" : puntoVenta,
	 					"agente":agente,
	 					"identificacion": identificacion,
	 					"misCotizaciones":misCotizaciones,	 					
	 					"tipoConsulta" : "encontrarTipoObjetoPorAprobarCanal",
	 					"estadoConsulta" : estadoConsulta
	 				},
	 				type : 'POST',
	 				datatype : 'json',
	 				success : function(data) {					
	 					var existenRegistro = false;
	 					$('#dataTable').show();
	 					$('#dataTable_wrapper').show();	 					
	 					$('#dataTableContent').html('');
	 					$("#datos_temporal").val("");
	 					var listadoCotizacion = 0;
	 					listadoCotizacion = data.listadoCotizacion;
	 					var auxiliar = "";
	 					if(listadoCotizacion.length > 0){
	 						$.each(listadoCotizacion, function(index){
	 							var prima_neta_total = 0;
	 							if(parseFloat(listadoCotizacion[index].prima_neta_total).toFixed(2) != "NaN")
	 								prima_neta_total = parseFloat(listadoCotizacion[index].prima_neta_total).toFixed(2);
	 							else
	 								prima_neta_total = "0.00";
	 							
	 							var aux="	<tr class='odd gradeX'>" +
	 							" <td relation='id'>"+ listadoCotizacion[index].codigo +"</td>" +
	 							" <td relation='punto_venta'>"+ listadoCotizacion[index].punto_venta +"</td>" +
	 							" <td relation='vigencia_poliza'>"+ listadoCotizacion[index].vigencia_poliza +"</td>" +
	 							" <td relation='cliente'>"+ listadoCotizacion[index].cliente +"</td>" +
	 							" <td relation='vendedor'>"+ listadoCotizacion[index].vendedor +"</td>" +
	 							" <td relation='agente'>"+listadoCotizacion[index].agente+"</td>" +
	 							" <td relation='producto'>"+ listadoCotizacion[index].producto +"</td>" +
	 							" <td relation='estado'>"+ listadoCotizacion[index].estado +"</td>" +
	 							" <td relation='tipo_objeto'>"+ listadoCotizacion[index].tipo_objeto +"</td>" +
	 							" <td relation='fecha_elaboracion'>"+ listadoCotizacion[index].fecha_elaboracion +"</td>" +
	 							" <td relation='por_comision'>"+ listadoCotizacion[index].por_comision +"</td>" +
	 							" <td relation='suma_total'>"+ parseFloat(listadoCotizacion[index].suma_total).toFixed(2) +"</td>" +
	 							" <td relation='prima_neta_total'>"+ prima_neta_total +"</td>" +
	 							" <td width='80px' align='center'>" +
	 								" <input type='hidden'  value='"+ listadoCotizacion[index].codigo +"'/>" +
	 								" <button type='button' class='btn btn-success btn-xs actualizar-btn'>" +
	   									" <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
	 								" </button>" +
	 								//" <button type='button' class='btn btn-danger btn-xs eliminar-btn'>" +
	 								//  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span>&nbsp; Eliminar &nbsp;" +
	 								//" </button>" +
	 							"</td>" +
	 						"</tr>";
	 								auxiliar +=	aux;
	 							
	 						$("#buscando").fadeOut("slow");	 					
	 						});	
							
							// Evitar Reinicializacion datatable
	 						var oTable = $('#dataTable').dataTable();
							oTable.fnDestroy();							
							$('#dataTable tbody').html(auxiliar);
							
	 						$('#dataTable').dataTable( {	 							
        						"pagingType": "full",
        						"bFilter": true,
        						"bLengthChange": false,
        						"bSort" : false,
        						"iDisplayLength": 10, // Limitamos el numero de filas en la paginacion
        						"dom": 'T<"clear">lfrtip',
        						"tableTools": {
            						"sSwfPath": "/CotizadorWeb/static/js/sb-admin/plugins/dataTables/swf/copy_csv_xls.swf",
            					},
        						"fnDrawCallback": function( oSettings ) {
        							$(".actualizar-btn").bind({click: function(event) {
        								var id=$(this).parent().parent().children().first().text();
        								window.location.href='CotizacionGanadero.jsp?id='+id;					
        								}
        								});
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
	 			
	 			function enviarDatos(codigoEnsurance, tipoConsulta){				
					$.ajax({
						url : '../CotizacionController',
						data : {
							"codigo" : codigo,
							"tipoConsulta" : tipoConsulta
						},
						type : 'POST',
						datatype : 'json',
						success : function(data) {
							if(data.success)
								$("#msgPopup").show();
							else
								alert(data.error);
						}
					});
				}
					
	    	 });
		});

				
					
				//Metodo validacion de fechas buscador
		    		$(function(){
		    			var startDate = new Date();
		    			var endDate = new Date();
		    			
		    			$('#dp1').datepicker().on('changeDate', function(ev){
		    					if (ev.date.valueOf() > endDate.valueOf()){
		    						alert("La Fecha Inicial no puede ser mayor que la Fecha Actual");
		    						return false;
		    					} else {		    					
		    						startDate = new Date(ev.date);
		    						$('#startDate').text($('#dp1').data('date'));
		    					}
		    					$('#dp1').datepicker('hide');
		    				});
		    			$('#dp2').datepicker().on('changeDate', function(ev){
		    					if (ev.date.valueOf() < startDate.valueOf()){
		    						alert('La Fecha Final no puede ser menor que la Fecha Inicial');
		    						return false;
		    					} else {
		    						
		    						endDate = new Date(ev.date);
		    						$('#endDate').text($('#dp2').data('date'));
		    					}
		    					$('#dp2').datepicker('hide');
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
				else {
					com.qbe.cotizador.model.Usuario usuario = (com.qbe.cotizador.model.Usuario) session
							.getAttribute("usuario");

					// Obtenemos el rol asociado al usuario
					com.qbe.cotizador.dao.seguridad.UsuarioRolDAO usuarioRolDAO = new com.qbe.cotizador.dao.seguridad.UsuarioRolDAO();
					com.qbe.cotizador.model.UsuarioRol usuarioRol = usuarioRolDAO
							.buscarPorUsuario(usuario);
					com.qbe.cotizador.model.Rol rol = usuarioRol.getRol();
					com.qbe.cotizador.dao.seguridad.OpcionMenuRolDAO opcionMenuRolDAO = new com.qbe.cotizador.dao.seguridad.OpcionMenuRolDAO();
					if (!opcionMenuRolDAO.verificarPermiso(rol.getId(),
							"Cotizaciones en Borrador")) {
						response.sendRedirect("/CotizadorWeb/dashboard/AccesoDenegado.jsp");
					}
				}
%>
	<div class="row crud-nav-bar">	
	<div class="well">
			<table class="table">
				<thead>
					<tr>
						<td colspan="3" style="font-weight: bold;"><center>Buscador
								Cotizaciones Ganadero</center></td>

					</tr>
					<tr>
						<th>Busqueda por Cotizacion:</th>
						<th>Cotizacion: <input type="text" id="cotizacion_id"
							onkeypress="return justNumbers(event);"></th>
					</tr>
					<tr>
						<th>Busqueda por Fechas:</th>
						<th>Fecha Inicial: <input type="text"
							data-date-format="dd-mm-yyyy" id="dp1"></th>
						<th>Fecha Final: <input type="text"
							data-date-format="dd-mm-yyyy" id="dp2"></th>
					</tr>
					<tr>
						<th>Busqueda Varios:</th>
						<th>Punto Venta: <select id="punto_venta_session"></select></th>
						<th>Mis Cotizaciones:<input type="checkbox"
							id="mis_cotizaciones"></th>
					</tr>
					<tr>
						<th>&nbsp;</th>
						<th>Agente: <select id="agentes"></select></th>
						<th>&nbsp;</th>
					</tr>
					<tr>
						<th>&nbsp;</th>
						<th>Identificacion Cliente: <input type="text"
							id="identificacion"></th>
						<th>&nbsp;</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							<button class="btn btn-primary" id="ConsultaBtn">
								<span class="glyphicon glyphicon-search"></span> &nbsp; Consulta
							</button>							
						</th>
						<th>
							<button class="btn btn-primary" id="LimpiarBtn">
								<span class="glyphicon glyphicon-trash"></span> &nbsp; Limpiar
							</button>
						</th>
						<th>&nbsp;</th>
					</tr>
					<tr>
						<th>&nbsp;</th>
						<th><div id="buscando" hidden="hidden">
								<div style="margin-top: 10px;">
									<span id="loading-msg"></span><img
										src="../static/images/ajax-loader.gif" /> Buscando la
									informacion, por favor espere...
								</div>
							</div></th>
						<th>&nbsp;</th>
					</tr>
				</tbody>
			</table>
		</div>
	
		<!-- Button trigger modal -->

	</div>
	<!-- Datatable -->
	<div class="row">
		<div class="col-lg-12">
			<div class="table-responsive">	
				<table class="table table-striped table-bordered table-hover"
					id="dataTable" style="font-size: x-small;">
					<thead>
						<tr>
							<th># Cot.</th>
							<th>Pto. Venta</th>
							<th>Vigencia</th>
							<th>Cliente</th>
							<th>Vendido por</th>
							<th>Agente</th>
							<th>Producto</th>
							<th>Estado</th>
							<th>Tipo Objeto</th>
							<th>Fecha Elaboracion</th>
							<th>% Comision</th>
							<th>Suma Asegurada Total</th>
							<th>Prima Neta Total</th>
							<th></th>
						</tr>
					</thead>
					<tbody id="dataTableContent" class="searchable" style="font-size: x-small;">
											
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- Datatable -->	
</body>
</html>