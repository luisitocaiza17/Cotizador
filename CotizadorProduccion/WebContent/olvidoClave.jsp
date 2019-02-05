<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cotizador-QBE</title>
<link rel="stylesheet" href="static/css/loginForm.css">
<link rel="stylesheet" href="static/css/main.css">
<link rel="shortcut icon" href="static/images/favicon.ico">
<script src="static/js/jquery.min.js"></script>
<script src="static/js/jquery-ui/jquery-ui.js"></script>
<script src="static/js/sb-admin/bootstrap.js"></script>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

<!--  <link rel="stylesheet" href="static/css/sb-admin/bootstrap.css">-->
<script>

$(document).ready(function (){	
	$("#regresarButton").hide();
	$("#loginButton").click(function(){
		$("#loading").fadeIn();	
		var mail=$("#mail").val().trim();
		if(mail.length>0&&IsEmail(mail)){
			$.ajax({
				url : '../CotizadorWeb/UsuarioController',
				data : {
					"mail" : mail,
					"tipoConsulta" : "olvidoClaveMail"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					if(data.success){
						alert("Se envio un correo con su nueva clave");
						$("#loading").fadeOut();
						$("#loginButton").fadeOut();
						$("#regresarButton").fadeIn();
					}else{
						alert(data.error);
						$("#loading").fadeOut();
						}
				}
			});
		}
		else{
			if(!IsEmail(mail)){
				alert("Ingrese un correo válido");
			}
			
		}
		
	});
	
	$("#regresarButton").click(function(){
		window.location = "index.jsp";
	});
	
});

function IsEmail(email) {
	  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  return regex.test(email);
	}

</script>
</head>
<body class="header">
	<div class="mainContent">						
			<div class="container" id="login-block">
			<div class="row">
			<div class="col-md-12">
					<div class="col-md-4">
						<div class="login-box">
							<div class="login-logo" style="color: #0092D4;">
								Olvido de clave							
							</div>
							<div class="login-form">
								<div class="alert alert-error hide">
									<button type="button" class="close" data-dismiss="alert">×</button>
									<h4>Error!</h4>
							</div>
							
								<p align="center" style="color: red"></p>
								<input type="text" name="mail" placeholder="Ingrese su correo"
									class="input-field" required="" id="mail" autofocus>
								
								<button type="button" class="btn btn-login" id="loginButton">Enviar</button>
								<button type="button" class="btn btn-login" id="regresarButton">Regresar</button>
							</div>
							<div align="center" id="loading" hidden>
							<div class="loading-indicator">
								<img src="static/images/ajax-loader.gif"/><br /><br />
								<span id="loading-msg">Cargando...</span>
							</div>					
						</div>
						</div>
					</div>
					<div class="col-md-8">
					 <div class="img-container">
					 	<img src="static/images/login.png" width="413" height="121">
					 </div>
					</div>
					
			</div>
			</div>	
			</div>
	</div>
</body>
</html>