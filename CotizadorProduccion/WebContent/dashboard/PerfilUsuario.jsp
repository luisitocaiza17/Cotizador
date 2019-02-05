<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
         <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Usuario - CotizadorQBE</title>

    <!-- Core CSS - Include with every page -->
    <link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="../static/css/loading.css" rel="stylesheet">
	<link rel="stylesheet" href="../static/css/select2/select2.css">
    
    <script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="../static/js/util.js"></script>
    <script src="../static/js/select2.js"></script>
	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
        
        $(document).ready(function () {
           
            $.ajax({
                url: '../UsuarioController',
                data: {
                    "tipoConsulta": "cargarActual"
//                     "tipoEntidad": "usuarios"
                },
                type: 'POST',
                datatype: 'json',
                success: function (data) {
                	var usuario=data.usuario;
                	$("#codigo").val(usuario.codigo);
                	$("#nombre").val(usuario.nombreCompleto);
                	$("#email").val(usuario.mail);
                	$("#login").val(usuario.login);
                	$("#identificacion").val(usuario.identificacion);
                }
                });
        });
        
        function cambiarClave(){
        	$.ajax({
                url: '../UsuarioController',
                data: {
                    "tipoConsulta": "cambiarClave",
                    "password":$("#passwordAnterior").val(),
                    "password1":$("#password1").val(),
                    "password2":$("#password2").val(),
                    "codigo":$("#codigo").val()
                },
                type: 'POST',
                datatype: 'json',
                success: function (data) {
                	if(data.success){
                		$("#success_texto").html(data.resultado);
                		$("#exito").fadeIn();
                	}
                	else{
                		$("#error_texto").html(data.error);
                		$("#alerta").fadeIn();
                	}
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
<body class="header">
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
						     <div class="panel-heading"><b>Cambio de Clave</b></div>
								<div class="panel-body">		
									
								<div class="form-group">
									<input type="hidden"class="form-control" id="codigo">
									<label>Identificación</label>
									 <input type="text" class="form-control " id="identificacion" disabled="disabled"> 
									 <label>Nombre</label>
									<input type="text" class="form-control " id="nombre"  disabled="disabled"> 
									<label>e-mail</label> 
									<input type="text" class="form-control" id="email" disabled="disabled">
									<label>Login</label> 
									<input type="text" class="form-control" id="login"  disabled="disabled">
									<label>Password Anterior</label>
									<input type="PASSWORD" class="form-control required" id="passwordAnterior">
									<label>Password</label>
									<input type="PASSWORD" class="form-control required" id="password1">
									<label>Repetir Password</label>
									<input type="PASSWORD" class="form-control required" id="password2"></br>
									<button type='button' id="cambiarClave" onclick="cambiarClave()" class='btn btn-success btn-s actualizar-btn'>
                                    <span class='glyphicon glyphicon glyphicon-edit'></span> Cambiar Clave
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
	</div>
</body>
</html>