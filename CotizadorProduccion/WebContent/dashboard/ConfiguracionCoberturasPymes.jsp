<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"><link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
	<link href="../static/css/loading.css" rel="stylesheet">
	
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="../static/js/sb-admin/plugins/dataTables/jquery.numeric.js"></script>
	<script src="../static/js/util.js"></script>
	
<title>Cobertura PYMEs - Cotizador QBE</title>

<script>
	
	 $(function(){
	  	  $(".wrapper1").scroll(function(){
	  	    $(".wrapper2").scrollLeft($(".wrapper1").scrollLeft());
	  	  });
	  	  $(".wrapper2").scroll(function(){
	  	    $(".wrapper1").scrollLeft($(".wrapper2").scrollLeft());
	  	  });
	  	});
	 
	 	var tipoConsulta = "";
	 	var configuracionCoberturaId = "";	 	
	 	var coberturaPymesId = "";
	 	var esSinValor = "";	 
	 	var porcentajeAplicado = "";
	 	var tasaPredeterminada = "";
	 	var texto = "";
	 	var tipoVariable = "";
	 	var valorMaximo = "";
	 	var variableOrigenPorcentaje = "";
	 	var tipoDeclaracion = "";
		var arrCoberturaPymes = new Array();
	    
		
		$(document).ready(function(){
			activarMenu("ConfiguracionCoberturasPymes");
			
			$(':input[type="number"]').numeric("");
			
			
			$.ajax({
				url : '../PymeCoberturaController',
				data : {
					"tipoConsulta" : "encontrarTodos"
				},
				type : 'POST',
				datatype : 'json',
				success : function (data) {
					var listadoCoberturaPymes = data.listadoCoberturaPymes;
					$.each(listadoCoberturaPymes, function (index){
						$("#configuracionCanal").append("<tr class='odd gradeX'>"+
								"<td relation='coberturaPymesId' style='width: 20%; text-align: left;' class='sorting' >" + listadoCoberturaPymes[index].coberturaPymesId + "</td>"+
								"<td relation='grupoCoberturaId' style='width: 20%; text-align: left;' class='sorting' >" + listadoCoberturaPymes[index].grupoCoberturaId + "</td>"+
								"<td relation='nombre' style='width: 20%; text-align: left;' class='sorting' >" + listadoCoberturaPymes[index].nombre + "</td>"+
								"<td relation='tipoCoberturaId' style='width: 20%; text-align: left;' class='sorting' >" + listadoCoberturaPymes[index].tipoCoberturaId + "</td>"+
								"<td relation='tipoDefinicion' style='width: 20%; text-align: left;' class='sorting' >" + listadoCoberturaPymes[index].tipoDefinicion + "</td>"+
								"<td style='width: 20%; text-align: center;' class='sorting' >"+
									"<button type='button' class='btn btn-primary btn-xs actualizar-btn' onclick='cargarConfiguracion("+listadoCoberturaPymes[index].coberturaPymesId+");' id='addButton'>"+
							        	"<span hidden='hidden' class='glyphicon glyphicon-plus' ></span> &nbsp; Configurar"+
								    "</button>"+									
								"</td>" +
							"</tr>");		
					});	
					$("#loading").remove();	
					
					$("#addConfiguracionCP").bind({click: function(){
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
						
					 	configuracionCoberturaId = $("#configuracionCoberturaId").val();
					 	coberturaPymesId = $("#coberturaPymesId").val();
					 	if($("#esSinValor").is(":checked")){
					 		esSinValor = 1;
					 	}else{
					 		esSinValor = 0;
					 	}					 		
						porcentajeAplicado = $("#porcentajeAplicado").val();
						tasaPredeterminada = $("#tasaPredeterminada").val();
						texto = $("#texto").val();
						tipoVariable = $("#tipoVariable").val();
						valorMaximo = $("#valorMaximo").val();
						variableOrigenPorcentaje = $("#variableOrigenPorcentaje").val();
						tipoDeclaracion = $("#tipoDeclaracion").val();	
						if(configuracionCoberturaId=="" || configuracionCoberturaId=="0")
							tipoConsulta = "crear";   
						else
							tipoConsulta = "editar";
						
						enviarDatos(configuracionCoberturaId,coberturaPymesId,esSinValor,porcentajeAplicado,tasaPredeterminada,texto,tipoVariable,valorMaximo,variableOrigenPorcentaje,tipoDeclaracion,tipoConsulta);	
						
						}
					});					
				}
			});						
		});	
		
		function cargarConfiguracion(coberturaPymesId){	
			$('#panel_1').hide();
			$('#panel_2').show();
			$.ajax({
				url : '../PymeConfiguracionCoberturaController',
				data : {
					"tipoConsulta" : "encontrarPorId",
					"coberturaPymesId" : coberturaPymesId					
				},
				type : 'POST',
				datatype : 'json',
				success : function(data){
					$("#configuracionCoberturaId").val(data.configuracionCoberturaId);
					$("#coberturaPymesId").val(coberturaPymesId);
					if(data.esSinValor > 0){
						$("#esSinValor").attr('checked', true);
					}else{
						$("#esSinValor").attr('checked', false);
					}					
					
					$("#porcentajeAplicado").val(data.porcentajeAplicado);
					$("#tasaPredeterminada").val(data.tasaPredeterminada);
					$("#texto").val(data.texto);
					$("#tipoVariable").val(data.tipoVariable);
					$("#valorMaximo").val(data.valorMaximo);
					$("#variableOrigenPorcentaje").val(data.variableOrigenPorcentaje);
					$("#tipoDeclaracion").val(data.tipoDeclaracion);
				}
			});
		}
		
		function enviarDatos(configuracionCoberturaId,coberturaPymesId,esSinValor,porcentajeAplicado,tasaPredeterminada,texto,tipoVariable,valorMaximo,variableOrigenPorcentaje,tipoDeclaracion,tipoConsulta){
			$.ajax({
				url : "../PymeConfiguracionCoberturaController",
				data : {
					"tipoConsulta" : tipoConsulta,
				 	"configuracionCoberturaId" : configuracionCoberturaId,	 	
				 	"coberturaPymesId" : coberturaPymesId,
				 	"esSinValor" : esSinValor,
				 	"porcentajeAplicado":porcentajeAplicado,
				 	"tasaPredeterminada":tasaPredeterminada,
				 	"texto":texto,
				 	"tipoVariable":tipoVariable,
				 	"valorMaximo":valorMaximo,
				 	"variableOrigenPorcentaje":variableOrigenPorcentaje,
				 	"tipoDeclaracion":tipoDeclaracion
				 	},
				 type : 'POST',
				 datatype : 'json',
				 success : function(data){
					 $("#msgPopup").show();
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

<div class="content">
		<form id="wizard" action="" method="post">			
			<section id = 'panel_1' style='display:block;'>
				<h2>Configuración Cobertura PYMES </h2>		
				<fieldset class="border">
					<legend class="border">Datos sobre la cobertura PYMES</legend>	
					<div class="panel panel-primary">
						<div class="panel-body">
						<div class="input-group"> <span class="input-group-addon">Filtro</span>
						    <input id="filter" type="text" class="form-control" placeholder="Escriba la palabra a buscar...">
						</div>
						 <table class="table table-striped table-bordered table-hover"
							id="dataTable">
								<thead>
									<tr>							
										<th>Id</th>
										<th>Grupo cobertura</th>
										<th>Nombre</th>
										<th>Tipo cobertura</th>
										<th>Tipo definición</th>							
										<th> </th>
									</tr>	
								</thead>
								<tbody id="configuracionCanal" class="searchable">
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
				</fieldset>
			</section>
			
			<section id ='panel_2' style='display:none;'>
				<h2>Configuración Cobertura PYMES</h2>				
				<fieldset class="border">
					<legend class="border">Datos de la Configuración PYMES</legend>									
						<div class="panel-body">
						<div class="alert alert-success" id="msgPopup">La configuración se grabo con exito.</div>							
					<div class="form-group">
						<div class="panel panel-primary">
						<div class="panel-heading">Parametros de configuración cobertura.</div>
						<div class="panel-body">
								<table class="table table-striped table-bordered table-hover" id="tbl_ConfiguracionCobertura">
									<tr>
									<td style='width: 25%' colspan='2'>
									<input type='hidden' id='configuracionCoberturaId' >
									<input type='hidden' id='coberturaPymesId' >
									<label>Sin valor. </label>									
									<input type='checkbox'  id='esSinValor' />	
									</td>
									</tr>
									<tr>									
									<td style='width: 25%'>
										<label>Tipo de declaracion. </label>										
										<input type="text" class="form-control required" id="tipoDeclaracion"  required>																				
									</td>
									<td>
									<label>Tipo de variable. </label>
										<select type='select' class='form-control required' id='tipoVariable' required>
										<option>Seleccione una opción</option>
										<option value='1'>GENERAL</option>
										<option value='2'>DIRECCIÓN</option>
										<option value='3'>COBERTURA PADRE</option>
										</select>
									</td></tr>
									<tr><td style='width: 25%'>
									<label>Valor Máximo</label>									
									<input type="number" class="form-control required" id="valorMaximo"  required>
									</td>
									<td style="width: 25%">
											<label>Porcentaje aplicado. </label>											
											<input type="number" class="form-control required" id="porcentajeAplicado"  required>
									</td></tr>
									<tr><td style='width: 25%'>
									<label>Variable origen porcentaje. </label>
									<select type='select' class='form-control required' id='variableOrigenPorcentaje' required>
										<option>Seleccione una opción</option>
										<option value='1'>SUMA VALORES</option>
										<option value='2'>SÓLO ESTRUCTURA</option>
										<option value='3'>SÓLO CONTENIDO</option>
										<option value='4'>SÓLO MAQUINARIA</option>
										<option value='5'>SÓLO MERCADERIA</option>
										<option value='6'>SÓLO MÉDICO</option>
										<option value='7'>SÓLO INSUMOS - NO ELÉCTRICOS</option>
										<option value='8'>TOTAL DE INCENDIO</option>
										<option value='9'>TOTAL DE ROBO</option>
										<option value='10'>NO APLICA</option>
										<option value='11'>VALOR</option>
										<option value='12'>VALOR PLO(RESPONSABILIDAD CIVIL)</option>
										</select>
									</td>
									<td style='width: 25%'>
									<label>Tasa. </label>									
									<input type="number" class="form-control required" id="tasaPredeterminada"  required>
									</td>
									</tr>									
									<tr>
									<td style='width: 25%' colspan="2">
									<label>Texto </label>									
									<textarea name="textarea" style="width:100%;height:80%;" id="texto"></textarea>	
									</td>
									</tr>
								<tr>								
								<td style='width: 25%' style = 'alingn: right' colspan='2'>
								<button type="button" class="btn btn-default" id="close-popup"
								data-dismiss="modal">Cerrar</button>								
									<button type="button" class="btn btn-primary" id="addConfiguracionCP">Guardar
											Cambios</button>
								</td>
								</tr>							
								</table>	
								</div>
								</div>
							</div>		
							<div>																				
						</div>					
						</div>
				</fieldset>
			</section>			
		</form>
	</div>
</body>
</html>