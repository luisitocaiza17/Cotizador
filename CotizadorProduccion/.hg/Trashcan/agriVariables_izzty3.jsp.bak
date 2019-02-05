<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-control" content="no-cache">
<title>Variables - CotizadorQBE</title>
<!-- <script src="../static/js/jquery.min.js"></script> -->
<script src="../static/js/cotizador/comun.js"></script>
<link href="../static/css/loading.css" rel="stylesheet">


<!-- Core CSS - Include with every page -->
<link
	href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">

<script
	src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
<script
	src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>

<script src="../static/js/sb-admin/plugins/dataTables/jquery.numeric.js"></script>
<script src="../static/js/util.js"></script>

<!-- KENDO -->
<link rel="stylesheet" href="../static/css/Kendo/kendo.common.min.css" />
<link rel="stylesheet" href="../static/css/Kendo/kendo.blueopal.min.css" />
<script src="../static/js/Kendo/kendo.all.min.js"></script>
<script src="../static/js/Kendo/kendo.web.min.js"></script>
<script src="../static/js/Kendo/jszip.min.js"></script>

<!-- Table Tools -->
<script
	src="../static/js/sb-admin/plugins/dataTables/dataTables.tableTools.js"></script>
<link
	href="../static/css/sb-admin/plugins/dataTables/dataTables.tableTools.css"
	rel="stylesheet">

<!-- Para el Datepicker-->
<link href="../static/css/datepicker.css" rel="stylesheet">
<script src="../static/js/bootstrap-datepicker.js"></script>

<link rel="stylesheet" href="../static/css/select2/select2.css">

<!--<script id="tipoObjetoCargaInicial" src="../static/js/pymes/carga.inicial.cotizador.pymes.js" tipoObjetoValor="Agricola"></script>-->
<!-- <script id="tipoObjetoMetodos" src="../static/js/pymes/metodos.pymes.js" tipoObjetoValor="Agricola"></script>
	<script src="../static/js/cotizador/comun.js"></script>
	 -->
<script>
	function GetQueryStringByParameter(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	    results = regex.exec(location.search);
	    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
		}
		var estadoConsulta="";//GetQueryStringByParameter('State');
		var codigo = "";
		var tipoConsulta = "";
		var Reglas = "";
		
		var estadoList = "";
		var canalList = "";
		var puntoVentaList = "";
		var agenteList = "";
		
		$(document).ready(function() {
				activarMenu("agriVariables");
				
				$("#loading").hide();
				
	    	 	$(".text_Currency").kendoNumericTextBox({
			        format: "c",
			        min: 0,
			        decimals: 2
			    });
				
				$(".text_Decimal").kendoNumericTextBox({
					format : '#.00',
			        min: 0,
			        decimals: 2
			    });		 
				
	    	 	$("#ConsultaBtn").click(function(){
	    	 		Cargar();
	    	 		$('#grid').show();
	    	 	});			
							
				$('#LimpiarBtn').click(function(){	    	 	
		    	 	$("#nombre").val("");
		    	 	if ($('#grid').data().kendoGrid){
		    			$('#grid').data().kendoGrid.destroy();
		    			$('#grid').empty();
		    		}
		 			$('#grid').hide();
		    	 });
				
				$("#save-record").bind({click: function(){
					$("#msgPopup").hide();
					var validator = $("#formCrud").kendoValidator().data("kendoValidator"); 
					$(".required").css("border", "1px solid #ccc");
					 if (validator.validate() === false) {     
						$(".required").each(function(index) {
								var cadena = $(this).val();
								if (cadena.length <= 0) {
									$(this).css("border", "1px solid red");
									alert("Por favor ingrese el campo requerido");
									$(this).focus();
									return false;
								}		
							});
				      }else{
				    	  	identificadorCot = $("#variableId").val();
							tipoConsulta = "editar";
							
							enviarDatos(tipoConsulta);
				      }
					 
					 Cargar();
					}
				});
				
				
		});
		
		
		function enviarDatos(tipoConsulta){
			
			 var nombreV=$("#nombreV").val();
			 var valor=$("#valor").val();
			 var variableId=$("#variableId").val();
			 
			$.ajax({
				url:'../AgriVariableController',
				data : {
					"nombreV" : nombreV,
					"valor" : valor,
					"variableId" : variableId,
					"tipoConsulta" : tipoConsulta
				},
				type : 'POST',
				async : false,
				datatype : 'json',
				success : function(data){
					var exito= data.success;
					if(exito=="true" || exito ===true){
						$("#msgPopup").show();
					}else{
						alert("Se produjo un error al guardar el usuario");
					}
				}
			});
		}
		
		function Cargar() {	
			// Validaciones de las busquedas	
			var nombre = $("#nombre").val();
			
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
	                		url: "../AgriVariableController",
	                		data: {
	                			"nombre":nombre,
	    						"tipoConsulta":"encontrarPorParametros"
	    					}
	                	}
	                },
	                schema: {
	                	data: "Data",
	                	total: "Total",
	                }
	            },
	            columns: [
	      				{ field: "id", title: "ID.", width: "30px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "nombre", title: "Nombre.", width: "120px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "valor", title: "Valor.", width: "120px",headerAttributes: { style: "white-space: normal"}},
	      				{ field: "Tipo", title: "Tipo de Variable.", width: "120px",headerAttributes: { style: "white-space: normal"}},
	      				{ command: { text: "Ver Detalle", click: fnEventoClick }, title: " Detalle ", width: "110px"},
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
		
		
		function fnEventoClick(e) {
	        e.preventDefault();
	        var dataItem = this.dataItem($(e.currentTarget).closest("tr"));   
	        $('#add').modal('show');
	        actualizar(dataItem.id);
	    }
		
		function actualizar(id){
			$.ajax({
				url:'../AgriVariableController',
				data:{
					"id" : id,
					"tipoConsulta" : "obtenerPorId"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data){
					$("#variableId").val(data.id);
					$("#nombreV").val(data.nombre);
					$("#valor").val(data.valor);					
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

	<!-- ventana consulta -->
	<div class="row crud-nav-bar">
		<div class="well">
			<table class="table" style="width: 100%">
				<thead>
					<tr>
						<td colspan="6" style="font-weight: bold;" align="center">Buscador Log de Cotizaciones.</td>
					</tr>
					<tr>
						<th style="width: 180px">Busqueda Espec&iacute;fica:</th>
						<th style="width: 100px">Nombre:</th>
						<th style="width: 200px"><input type="text" id="nombre" style="width: 100%"></th>
						<th style="width: 100px"></th>
						<th style="width: 100px"></th>
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
					</tr>					
				</tbody>
			</table>
		</div>
		<!-- Button trigger modal -->
	</div>
	
	
	
	
	
	<div id="grid"></div>
	
	<!-- Modal -->
		<div class="modal fade" id="add" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form id="formCrud">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Variables del Sistema</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">El la variable se actualizo con exito.</div>
							<div class="status"> </div>	
							<div class="form-group">
								<input type="hidden"class="form-control" id="variableId">										
								<label>Variable</label>
									<input type="text" class="form-control required" id="nombreV" validationMessage="Campo requerido!!!" required>
								
								<label>Nombre</label>
									<input type="text" class="form-control required" id="valor" validationMessage="Campo requerido!!!" required>
								<br />																						
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" id="close-popup"
								data-dismiss="modal">Cerrar</button>
							<button type="button" class="btn btn-primary" id="save-record">Guardar Cambios</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	
</body>
</html>