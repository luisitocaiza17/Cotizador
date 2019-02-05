<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Respuesta Procesada</title>
<link rel="stylesheet" href="static/css/loginForm.css">
<link rel="stylesheet" href="static/css/main.css">

<script src="static/js/util.js"></script>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/jquery-ui/jquery-ui.js"></script>
<script src="static/js/sb-admin/bootstrap.js"></script>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

<script>
	$(document).ready(function() {
		
		var QueryString = function () {
			  // This function is anonymous, is executed immediately and 
			  // the return value is assigned to QueryString!
			  var query_string = {};
			  var query = window.location.search.substring(1);
			  var vars = query.split("&");
			  for (var i=0;i<vars.length;i++) {
			    var pair = vars[i].split("=");
			    	// If first entry with this name
			    if (typeof query_string[pair[0]] === "undefined") {
			      query_string[pair[0]] = pair[1];
			    	// If second entry with this name
			    } else if (typeof query_string[pair[0]] === "string") {
			      var arr = [ query_string[pair[0]], pair[1] ];
			      query_string[pair[0]] = arr;
			    	// If third or later entry with this name
			    } else {
			      query_string[pair[0]].push(pair[1]);
			    }
			  } 
			    return query_string;
			} ();
			
			
		if(QueryString.tipoRespuesta=='SolicitudDescuento'){
			if(QueryString.a=='1')
				$("#success_texto").text("La Solicitud de Descuento fue autorizada");
			else
				$("#success_texto").text("La Solicitud de Descuento fue rechazada");
		}
		

		});
	
</script>

</head>
<body class="header">
	<div class="mainContent">
		<img src="static/images/logoMiqbe.png" width="86" height="69"> <img
			src="static/images/colonial.png">
		<nav id="webMenu">
		<h1></h1>
		</nav>
		<div id="gwtContent">
			<div class="container" id="login-block">
				<div class="">
					<div class="col-sm-6 col-sm-offset-3 ">

						<div class="signup-box clearfix animated flipInY">

							<div class="login-logo" style="color: #0092D4;"></div>
							
							<div class="alert alert-success" id="exito">
									<h4 id='success_texto'></h4>
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