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
		var nombre_comercial = "";
		var activo = "0";
		var plan_ensurance = "";
		var pro_ensurance = "";
		var nemotecnico = "";
		var tipoConsulta = "";
		var arrProductoGrupo = new Array();
		var arrCodigoProductoGrupo = new Object();
		
		$(document).ready(function() {
			activarMenu("PymesRecalculoCotizaciones");		 	
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
	    	 $('#ConsultaBtn').click(function(){
	    		 $.ajax({
	 				url : '../PymeRecalculoFacturas',
	 				type : 'POST',
	 				datatype : 'json',
	 				success : function(data) {
	 					if (data.success == false){
	 						 notification.show({
	 	                           title: "ERROR",
	 	                           message: data.error
	 	                       }, "error");							
	 						return;
	 					}	
	 					else{
	 						alert("El reproceso se realizo de manera correcta");
	 					}
	 					
	 				}
	 			});
	    	 });
		});

				
					
				
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
						<td colspan="3" style="font-weight: bold;"><center>Re-Calculo de Cotizaciones Pymes</center></td>

					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							<button class="btn btn-primary" id="ConsultaBtn">
								<span class="glyphicon glyphicon-search"></span> &nbsp; Procesar
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
</body>
</html>