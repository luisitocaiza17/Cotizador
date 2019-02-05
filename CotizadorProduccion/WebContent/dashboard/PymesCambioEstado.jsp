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
	
	<script id="tipoObjetoCargaInicial" src="../static/js/pymes/carga.inicial.cotizador.pymes.js" tipoObjetoValor="PYMES"></script>
	<script id="tipoObjetoMetodos" src="../static/js/pymes/metodos.pymes.js" tipoObjetoValor="PYMES"></script>
	<script src="../static/js/cotizador/comun.js"></script>
	
	<script src="../static/js/util.js"></script>
    <!-- Para el Datepicker-->
    <link href="../static/css/datepicker.css" rel="stylesheet">
    <script src="../static/js/bootstrap-datepicker.js"></script>
    
    <!-- KENDO -->
	<link rel="stylesheet" href="../static/css/Kendo/kendo.common.min.css" />
	<link rel="stylesheet" href="../static/css/Kendo/kendo.blueopal.min.css" />
	<script src="../static/js/Kendo/kendo.all.min.js"></script>
	<script src="../static/js/Kendo/kendo.web.min.js"></script>
	<script src="../static/js/Kendo/jszip.min.js"></script>

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
		var notification;
		var estadoList = "";
		
		var nombre_comercial = "";
		var activo = "0";
		var plan_ensurance = "";
		var pro_ensurance = "";
		var nemotecnico = "";
		var tipoConsulta = "";
		var arrProductoGrupo = new Array();
		var arrCodigoProductoGrupo = new Object();
		
		$(document).ready(function() {
			activarMenu("PymesCambioEstado");		 	
			
			$("#estado").kendoMultiSelect({
 				dataTextField : "nombre",
 				dataValueField : "estadoId",
 				animation : false,
 				maxSelectedItems : 1
 			});	    	 
 	    	 
    	 	estadoList = $("#estado").data("kendoMultiSelect");
    	 	
    	 	/*Mensaje de error*/
			notification = $("#notification").kendoNotification({
            position: {
                pinned: true,
                top: 90,
                right: 35
            },
            autoHideAfter: 10000,
            hideOnClick: false,
            stacking: "down",
            templates: [ {
                type: "error",
                template: $("#errorTemplate").html()
        		}]
            }).data("kendoNotification");
		
		 $(document).one("kendo:pageUnload", function(){ });
    	 	
	    	 CargarComboEstado();
	    	 
	    	 $('#LimpiarBtn').click(function(){	    	 	
	    	 	$("#cotizacion_id").val("");
	    	 	var multi = $("#estado").data("kendoMultiSelect");
	    	 	multi.value("");
	    	 	multi.input.blur();
	    	 });
	    	 
	    	 $('#ConsultaBtn').click(function(){	
	    		 $("#buscando").fadeIn("slow");

	    		 // Validaciones de las busquedas	    		 
	    		 var cotizacionId = $("#cotizacion_id").val();
	    		 
	    		 if(cotizacionId==""){
	    		 	alert("Ingrese el numero de cotizacion");
	    		 	$("#buscando").fadeOut("slow");
	    		 	return false;
	    		 }
	    		 var estadoConsulta = $("#estado option:selected").val();
	    		 
	    		 if(estadoConsulta=="" || estadoConsulta==undefined){
	    		 	alert("Debe seleccionar el nuevo estado");
	    		 	$("#buscando").fadeOut("slow");
	    		 	return false;
		    	}
	    		 
	    		 $.ajax({
	 				url : '../PymeCotizacionController',
	 				data : {
	 					"cotizacionId" : cotizacionId ,
	 					"tipoConsulta" : "procesarCambioEstado",
	 					"estadoFinalId" : estadoConsulta
	 				},
	 				type : 'POST',
	 				datatype : 'json',
	 				success : function(data) {
	 					if(data.success){
	 						alert("El cambio de estado fue realizado con éxito");
	 						$("#cotizacion_id").val("");
	 						var multi = $("#estado").data("kendoMultiSelect");
	 			    	 	multi.value("");
	 			    	 	multi.input.blur();
	 					}
	 					else{
	 						 notification.show({
	 	                           title: "ERROR",
	 	                           message: data.error
	 	                       }, "error");							
	 						return;
	 							 					}
	 					$("#buscando").fadeOut("slow");
	 				}
	 			});
	    	 });
		});

		
		function CargarComboEstado(){
			$.ajax({
				url : '../PymeCotizacionReporteController',
				data : {
					"tipoConsulta" : "cargarCombos",
				},
				type : 'post',
				datatype : 'json',
				success : function (data) {
					if (data.success == false){
						 notification.show({
	                           title: "ERROR",
	                           message: data.error
	                       }, "error");							
						return;
					}
					estadoList.dataSource.filter({});
					estadoList.setDataSource(data.listEstado);					
				}
			});
		}
	</script>
	<script id="errorTemplate" type="text/x-kendo-template">
  <div class="wrong-pass">
      <img src="../static/images/error-icon.png" width="60" height="60"/>
         <h3>#= title #</h3>
      <p>#= message #</p>
  </div>
</script>
<style>
                .demo-section p {
                    margin: 3px 0 3px;
                    line-height: 50px;
                }
                .demo-section .k-button {
                    width: 200px;
                }

                .k-notification {
                    border: 0;
                }
                /* Error template */
                .k-notification-error.k-group {
                    background: rgba(100%,0%,0%,.7);
                    color: #ffffff;
                }
                .wrong-pass {
                    width: 400px;
                    height: 50px;
                }
                .wrong-pass h3 {
                    font-size: 1em;
                    padding: 36px 14px 9px;
                }
                .wrong-pass img {
                    float: left;
                    margin: 30px 15px 30px 30px;
                }
                
            </style>
</head>
<body>
<%
			// Permitimos el acceso si la session existe		
				if(session.getAttribute("login") == null){
				    response.sendRedirect("/CotizadorWeb");
				}
%>
	<span id="notification" style="display:none;"></span>
	<div class="row crud-nav-bar">	
	<div class="well">
			<table class="table">
				<thead>
					<tr>
						<td colspan="3" style="font-weight: bold;"><center>Cambiar de Estado 
								Cotizaciones Pymes</center></td>

					</tr>
					<tr>
						<td style="color: blue;">Cotizacion:</td>
						<td><input type="text" id="cotizacion_id"
							onkeypress="return justNumbers(event);"></td>
					</tr>
					<tr>
						<td style="color: blue;">Nuevo Estado:</td>
						<td >
							<select id="estado" data-placeholder="Seleccione una opción..."></select>
						</td>	
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							<button class="btn btn-primary" id="ConsultaBtn">
								<span class="glyphicon glyphicon-search"></span> &nbsp; Cambiar ahora
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
										src="../static/images/ajax-loader.gif" /> Cambiando de estado, por favor espere...
								</div>
							</div></th>
						<th>&nbsp;</th>
					</tr>
				</tbody>
			</table>
		</div>
	
		<!-- Button trigger modal -->
	</div>
</body>
</html>