<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cotizador QBE - Actualizacion Datos </title>
    <!-- Core CSS - Include with every page -->
    <link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="../static/css/loading.css" rel="stylesheet">
	<link rel="stylesheet" href="../static/css/select2/select2.css">
    
    <script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="../static/js/util.js"></script>
    <script src="../static/js/select2.js"></script>
    <script src="../static/js/jquery.validate.js"></script>

<script>	

	$( document ).ready(function() {
		cargarDatos();
  	});
	
	
	function enviarDatos(){
		$.ajax({
			url : '../EntidadController',
			data : {
				"telefono" : $('#telefono').val(),
				"extension" : $('#extension').val(),
				"celular" : $('#celular').val(),
				"tipoConsulta" : "actualizarDatosUsuario"
			},
			type : 'POST',
			datatype : 'json',
			success : function(data) {
				if(data.success){					
					$("#success_texto").text("Se actualizaron los datos ");
					$("#exito").show();					
				}else{
					$("#alerta").text("No se actualizaron los datos");
					$("#alerta").slideDown();
				}					
			}
		});
	}
	
	function cargarDatos(){
		$('#login').val("");
		$('#email').val("");
		$('#telefono').val("");
		$('#extension').val("");
		$('#celular').val("");
		
		$.ajax({
			url : '../EntidadController',
			data : {				
				"tipoConsulta" : "obtenerDatosActualizarUsuario"
			},
			type : 'POST',
			datatype : 'json',
			success : function(data) {							
				$('#login').val(data.login);
				$('#email').val(data.email);
				$('#telefono').val(data.telefono);
				$('#extension').val(data.extension);
				$('#celular').val(data.celular);										
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

<body class="header"">
	<div class="mainContent">
	<h1></h1>
	</nav>
	<div id="gwtContent">
	<div class="container" id="login-block">
	<div class="">
	<div class="col-sm-6 col-sm-offset-3 ">
	<div class="signup-box clearfix animated flipInY">
		<div hidden='' class="alert alert-warning" id="alerta">
			<button type="button" class="close"  id="alertax" data-dismiss="" onclick="$(this).parent().fadeOut()">×</button>
			<h4 id='error_texto'></h4>
		</div>
		<div hidden='' class="alert alert-success" id="exito">
			<button type="button" class="close" id="exitox" data-dismiss="" onclick="$(this).parent().fadeOut()">×</button>
			<h4 id='success_texto'></h4>
		</div>
	
			<div class="panel panel-primary">
			<div class="panel-heading"><b>Actualizaci&oacute;n de Datos</b></div>
			<div class="panel-body">							
				<div class="form-group">					
					<label>Login :</label>
					<input type="text" class="form-control" id="login"  onkeypress="validarKeyPress(event,/[a-z\s]/)" disabled="disabled">																		
					<label>e-mail :</label>
					<input type="text" class="form-control" id="email" disabled="disabled">
					<label>Tel&eacute;fono Fijo :</label>
					<input type="text" class="form-control" id="telefono" onkeypress="validarKeyPress(event,/[0-9\s]/)" required maxlength="10" >
					<label>Extensi&oacute;n Tel&eacute;fono :</label>
					<input type="text" class="form-control required" id="extension" onkeypress="validarKeyPress(event,/[0-9\s]/)" required maxlength="10" >
					<label>Tel&eacute;fono Celular :</label>
					<input type="text" class="form-control required" id="celular" onkeypress="validarKeyPress(event,/[0-9\s]/)" required maxlength="10" >
					</br>														
					<button type='button'  onclick="enviarDatos()" class='btn btn-success btn-s actualizar-btn'>
                      <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar
                    </button>
					
				</div>
			</div> 
			</div>
	 </div>
	 </div>
	 </div>
	 </div>
	 </div>
	</div>
</body>
</html>