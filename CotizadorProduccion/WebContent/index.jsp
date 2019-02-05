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
<script>

$(document).ready(function (){
	var QueryString = function () {				
		// This function is anonymous, is executed immediately and
		// the return value is assigned to QueryString!
		var query_string = {};
		var query = window.location.search.substring(1);
		// Validacion de sesion expirada
		var session_expirada = "NO";
		if(query.indexOf("expirada") != -1){
			query = "redir="+document.referrer;
			$("#mensaje_session").text("Sesión expirada. Ingrese");
			session_expirada = "SI";
		}else{
			query = window.location.search.substring(1);
			session_expirada = "NO";
		}				 		
		var vars = query.split("&");
					
		for (var i = 0; i < vars.length; i++) {
			var pair = vars[i].split("=");
			// If first entry with this name
			if (typeof query_string[pair[0]] === "undefined") {
				if(pair.length ==3 && session_expirada == "SI"){
					query_string[pair[0]] = pair[1]+"="+ pair[2];
				}else
					query_string[pair[0]] = pair[1];
				// If second entry with this name
			} else if (typeof query_string[pair[0]] === "string") {
				if(pair.length ==3 && session_expirada == "SI"){
					pair[1] = pair[1] +"="+ pair[2];
				}
				var arr = [query_string[pair[0]], pair[1]];
				query_string[pair[0]] = arr;
				// If third or later entry with this name
				} else {				
					query_string[pair[0]].push(pair[1]);
				}
			}
		return query_string;
	}
	();
	
	if(QueryString.redir!=null&&QueryString.redir!=""){
		var aux=$("#loginForm").attr('action');		
		$("#loginForm").attr('action',aux+'?redir='+QueryString.redir);
	}
	
	
	if(QueryString.error!=null&&QueryString.error!=""){
		
		if(QueryString.error=="contrasena"){
			alert("Contraseña Incorrecta");
			//$("#mensajeError").html("Contraseña Incorrecta");
			//$("#mensaje_session").text("Contraseña Incorrecta");
		}
		
		if(QueryString.error=="usuario"){
			alert("Usuario no existe");
			//$("#mensajeError").html("Usuario no existe");
			//$("#mensaje_session").text("Usuario no existe");
		}
		
		
		
		//$(".alert-error").fadeIn("slow");
		
	}
	
	

	
});

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
								Iniciar Sesión							
							</div>
							<div class="login-form">
								<div class="alert alert-error hide">
									<button type="button" class="close" data-dismiss="alert">×</button>
									<h4 id="mensajeError" style=" font-color: red ">Error!</h4>
							</div>
							<center><c:out value="${mensaje_validacion_cuenta }"></c:out>
							<p id="mensaje_session"  style=" font-color: red "></p>
							
							</center>	
							<form action="../CotizadorWeb/AccesoSistemaController" method="post" id="loginForm">
								<p align="center" style="color: red"></p>
								<input type="text" name="username" placeholder="Usuario"
									class="input-field" required="" id="username" autofocus>
								<input type="password" name="password" placeholder="Clave"
										class="input-field" required="" id="password">
								<button type="submit" class="btn btn-login" id="loginButton">Acceder</button>
							</form>
							<div class="login-links">
								<a href="olvidoClave.jsp" id="olvidoClave"> Olvid&oacute; su clave? </a> <br>								
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