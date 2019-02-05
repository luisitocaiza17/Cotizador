<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	<link href="../static/css/loading.css" rel="stylesheet">

	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="../static/js/util.js"></script>
	
	<!-- KENDO -->
	<link rel="stylesheet" href="../static/css/Kendo/kendo.common.min.css" />
	<link rel="stylesheet" href="../static/css/Kendo/kendo.blueopal.min.css" />
    <script src="../static/js/Kendo/kendo.all.min.js"></script>
    <script src="../static/js/Kendo/kendo.web.min.js"></script>
    <script src="../static/js/Kendo/jszip.min.js"></script>
    <style type="text/css">
    	.tab_strip
		{
		    height: 200px;
		}
    </style>
<title>Auditoria Cobertura Pymes</title>
<script>

$(function(){
	  $(".wrapper1").scroll(function(){
	    $(".wrapper2").scrollLeft($(".wrapper1").scrollLeft());
	  });
	  $(".wrapper2").scroll(function(){
	    $(".wrapper1").scrollLeft($(".wrapper2").scrollLeft());
	  });
	});	


	$(document).ready(function(){
		activarMenu("AuditoriraCoberturas");	
		Cargar();
	
	});
	
	$("formCrud").submit(function(event) {
	    event.preventDefault();
	    if (validator.validate()) {
	        status.text("Hooray! Your tickets has been booked!")
	            .removeClass("invalid")
	            .addClass("valid");
	    } else {
	        status.text("Oops! There is invalid data in the form.")
	            .removeClass("valid")
	            .addClass("invalid");
	    }
	});
	

	
	function Cargar() {	
		$('#dataTable_wrapper').hide();
	
		if ($('#grid').data().kendoGrid){
			$('#grid').data().kendoGrid.destroy();
			$('#grid').empty();
		}
		
		$("#grid").kendoGrid({
			toolbar: ["excel"],
	        excel: {
	            fileName: "QBE_Cotizaciones.xlsx",
	            filterable: true,
	            allPages: true
	        },
			dataSource: {
	            type: "json",
	            serverPaging: true,
	            serverSorting: true,
	            pageSize: 20,
	            transport:{
	            	read: {
	            		url: "../AuditoriaCoberturasController",
	            		data: {
	            			"tipoConsulta":"buscarTodos"
						}
	            	}
	            },
	            schema: {
	            	data: "Data",
	            	total: "Total",
	            }
	        },
	        columns:[
	             	{ field: "id", title: "id.", width: "60px",headerAttributes: { style: "white-space: normal"}},
	            	{ field: "fechaCambio", title: "Fecha de Cambio", headerAttributes: { style: "white-space: normal"}, width: "160px"},
	            	{ field: "nombre", title: "Nombre Cobertura", width: "200px",headerAttributes: { style: "white-space: normal"}},
	            	{ field: "nombreComercialProducto", title: "Nombre Comercial Producto.", width: "200px",headerAttributes: { style: "white-space: normal"}},
	            	{ field: "usuario", title: "Usuario", width: "150px",headerAttributes: { style: "white-space: normal"}},
	            	{ field: "valorAnterior", title: "Valor Anterior ", headerAttributes: { style: "white-space: normal"}, width: "150px"},
	            	{ field: "valorNuevo", title: "Valor Nuevo ", headerAttributes: { style: "white-space: normal"}, width: "150px"},
	            	{ field: "detalle", title: " Detalle ", headerAttributes: { style: "white-space: normal"}, width: "150px"}
	            ],
	  				height: 500,
	            selectable: true,
	            resizable: true,
	            pageable: {
	                info: true,
	                numeric: false,
	                previousNext: false
	            },
	            scrollable: {
	                virtual: true
	            },
	        }); 
		
		
		var exportFlag=false;
		$("#grid").data("kendoGrid").bind("excelExport", function (e) {
			var columns = e.sender.columns;
			if (!exportFlag) {
	            jQuery.each(columns, function (index) {
	                if (this.exportar) {
	                	e.sender.showColumn(this.field);
	                }
	            });
	            
	            //e.sender.showColumn("AgenteId");
	            e.preventDefault();
	            exportFlag = true;
	            setTimeout(function () {
	                e.sender.saveAsExcel();
	            }, 1000);
	        } else {
	        	jQuery.each(columns, function (index) {
	                if (this.exportar) {
	                	e.sender.hideColumn(this.field);
	                }
	            });
	            exportFlag = false;
	        }
		});
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
	<div class="row crud-nav-bar">
			
		
		<!-- Datatable -->
		
		<h3>AUDITORIA DE COBERTURAS PYMES</h3>
		<br>
		<div id="grid">
			
		</div>
		
	</div>
	<!-- Datatable -->
	
	<style>	
	 .confirm {
                    padding-top: 1em;
                }

                .valid {
                    color: green;
                }

                .invalid {
                    color: red;
                }
	</style>
</body>
</html>