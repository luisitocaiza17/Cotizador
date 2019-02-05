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
    
    <style type="text/css">
    	.tab_strip
		{
		    height: 200px;
		}
    </style>
<title>Periodos Agricolas</title>
 <script>
 var CicloId = "";
 var Nombre = "";
 var FechaInicio = "";
 var FechaFin = "";
 var Estado = "";
            
            $(document).ready(function(){
            	activarMenu("CiclosAgricola");
            	// create DatePicker from input HTML element
            	 $("#FechaInicio").kendoDatePicker({format:"yyyy-MM-dd",change: checkDates});
                 $("#FechaFin").kendoDatePicker({format:"yyyy-MM-dd",change: checkDates });
            	Cargar();
            	$("#save-record").bind({click: function(){
            		//validate_fechaMayorQue();
            		var validator = $("#formCrud").kendoValidator().data("kendoValidator"); 
            		
            		$(".required").css("border", "1px solid #ccc");
            		 if (validator.validate() === false || validate_fechaMayorQue()===false) {
            			 if(validate_fechaMayorQue()===false){
            				 alert("La fecha de inicio no puede ser mayor que la fecha de fin");
            			 }
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
            	    	  	if(!checkDates())
            	    	  		return false
            	    	  	CicloId = $("#CicloId").val();
            				Nombre = $("#Nombre").val();
            				FechaInicio = $("#FechaInicio").val();
            				FechaFin = $("#FechaFin").val();
            				if(CicloId == "")
            					tipoConsulta = "crear";
            				else
            					tipoConsulta = "editar";
            				
            				enviarDatos(CicloId,Nombre,FechaInicio,FechaFin,Estado,tipoConsulta);
            	      }
            		}
            	});
            	
            	
            	function checkDates() {
            		var validar = true;
            		var startDate = $("#FechaInicio").data("kendoDatePicker");
            		var endDate = $("#FechaFin").data("kendoDatePicker");
                    if ( startDate.value() != '' && endDate.value() != '') {
                        if (Date.parse(startDate.value()) > Date.parse(endDate.value())) {
                            alert('La fecha Final no puede ser menor a la fecha Inicial.');
                            //endDate.value(startDate.value());
                            validar = false;
                        }
                    }
                    return validar;
                }
           	});
            	
            function actualizar(Ciclo){
            	$.ajax({
            		url:'../AgriCicloController',
            		data:{
            			"CicloId" : Ciclo,
            			"tipoConsulta" : "obtenerPorId"
            		},
            		type : 'POST',
            		datatype : 'json',
            		success : function(data){
            			$("#CicloId").val(data.CicloId);
            			$("#Nombre").val(data.Nombre);
            			$("#FechaInicio").val(data.FechaInicio);
            			$("#FechaFin").val(data.FechaFin);
            		}
            	});
            }

            function eliminar(Ciclo){
            	var r = confirm("Seguro que desea eliminar");
            	if(r == true){			
            		CicloId = Ciclo;
            		tipoConsulta="eliminar";			
            		enviarDatos(CicloId,"","","","",tipoConsulta);
            	}
            	Cargar();
            	$("#msgPopup").hide();
            }
            	
            function enviarDatos(CicloId,Nombre,FechaInicio,FechaFin,Estado,tipoConsulta){
            	
            	$.ajax({
            		url:'../AgriCicloController',
            		data : {
            			"CicloId" : CicloId,
            			"Nombre" : Nombre,
            			"FechaInicio" : FechaInicio,
            			"FechaFin" : FechaFin,
            			"Estado" : Estado,
            			"tipoConsulta" : tipoConsulta
            		},
            		type : 'POST',
            		async : false,
            		datatype : 'json',
            		success : function(data){
            			$("#msgPopup").show();
            		}
            	});
            }
            
            function validate_fechaMayorQue()
			{
            	var fechaInicial=$("#FechaInicio").val();
            	var fechaFinal=$("#FechaFin").val();
            	valuesStart=fechaInicial.split("-");
				valuesEnd=fechaFinal.split("-");
				var dateStart=new Date(valuesStart[2],(valuesStart[1]-1),valuesStart[0]);
				var dateEnd=new Date(valuesEnd[2],(valuesEnd[1]-1),valuesEnd[0]);
				if(dateStart>=dateEnd)
				{
				    return false;	
				}
				else{
					return true;
				}
			}
            function validate_fechaMayorQue2(fechaInicial,fechaFinal)
			{
				valuesStart=fechaInicial.split("-");
				valuesEnd=fechaFinal.split("-");
				var dateStart=new Date( valuesStart[0],(valuesStart[1]-1),valuesStart[2]);
				var dateEnd=new Date(valuesEnd[0],(valuesEnd[1]-1),valuesEnd[2]);
				if(dateStart>=dateEnd)
				{
				   return 0;
				}
				return 1;
			}
           
            function Cargar(){
            	$('#configuracionCiclo').children().remove();
            	$.ajax({
            		url:'../AgriCicloController',
            		data:{'tipoConsulta':'encontrarTodos'},
            		type:'post',
            		datatype:'json',
            		async: false,
            		success: function(data){
            			var CicloJSONArray = data.CicloJSONArray;
            			$.each(CicloJSONArray, function(index){
            				$('#configuracionCiclo').append("<tr class='odd gradeX'>"+
            						"<td relation='Nombre'>"+CicloJSONArray[index].Nombre+"</td>"+
            						"<td relation='FechaInicio'>"+CicloJSONArray[index].FechaInicio+"</td>"+
            						"<td relation='FechaFin'>"+CicloJSONArray[index].FechaFin+"</td>"+
            						"<td relation='Estado'>"+CicloJSONArray[index].Estado+"</td>"+
            						
            						"<td width='175px'>"+									
            						"<input type='hidden' id='CicloId' value='" + CicloJSONArray[index].CicloId + "'/>"+	
            						" <button type='button'  name='actualizar-btn' data-toggle='modal' data-target='#add' class='btn btn-success btn-xs actualizar-btn' onclick='actualizar("+CicloJSONArray[index].CicloId+");'>" +
            							" <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
            						" </button>" +									
            						" <button type='button' class='btn btn-danger btn-xs eliminar-btn' onclick='eliminar("+CicloJSONArray[index].CicloId+");'>" +
            						  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record' ></span> Eliminar" +
            						" </button>" +									
            					"</td>" +
            				"</tr>");
            			});
            			$("#loading").remove();	
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
		<!-- Button trigger modal -->
		<button class="btn btn-primary" data-toggle="modal" data-target="#add" id="addButton">
			<span class="glyphicon glyphicon-plus"></span> &nbsp; Nuevo
		</button>

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
							<h4 class="modal-title" id="myModalLabel">Periodo Agrícolas</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">El Periodo se grabo con exito.</div>
							<div class="status"> </div>	
							<div class="form-group">
								<input type="hidden" class="form-control" id="CicloId">										
								<label>Nombre</label>
								<input type="text" id="Nombre" name="Nombre" class="form-control required"  validationMessage="Campo requerido!!!" required>
								<br>
								<br>
								<table width="100%">
								<tr>
								<td width="100px">
								<label>Fecha Inicio</label>	
								</td>
								<td>
								<input id="FechaInicio" style="width: 200px" name="FechaInicio" class="form-control required"  validationMessage="Campo requerido!!!" required="required">
								</td>
								</tr>
								<tr><td>&nbsp;</td></tr>
								<tr>
								<td width="100px">
								<label>Fecha Fin </label>
								</td>
								<td>
								<input id="FechaFin" style="width: 200px" name="FechaFin" class="form-control required"  validationMessage="Campo requerido!!!" required="required">
								</td>
								</tr>
								</table>
								<!--
								<table width="100%" id="EstadoTable">
								<tr>
								<td>
								<label>Estado</label>
								<select   name="Estado"  id="Estado" class="form-control required" validationMessage="Campo requerido!!!" required="required">	
								<option value='' >Selecione una opción</option>	
								<option value='1'>Activo</option>	
								<option value='0'>Inactivo</option>						
								</select>
								</td>
								</tr>
								</table>-->																					
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
	
	<!-- Datatable -->
	<div class="row">
		<div class="col-lg-12">					
				<div class="panel panel-primary">
						<div class="panel-body">
						<div class="input-group"> <span class="input-group-addon">Filtro</span>
						    <input id="filter" type="text" class="form-control" placeholder="Escriba la palabra a buscar...">
						</div>
						 <table class="table table-striped table-bordered table-hover"
							id="dataTable">
								<thead>
									<tr>
										<th>Nombre</th>	
										<th>Fecha Inicio</th>	
										<th>Fecha Fin</th>	
										<th>Estado</th>																
										<th> </th>
									</tr>	
								</thead>
								<tbody id="configuracionCiclo" class="searchable">
					                 		<div id="loading">
										<div class="loading-indicator">
											<img src="../static/images/ajax-loader.gif"/><br /><br />
											<span id="loading-msg">Cargando...</span>
										</div>					
									</div> 
								</tbody>                     
			                </table>
						</div>
					</div>	
			</div>
		</div>
	</div>
	<!-- Datatable -->	
</body>
</html>