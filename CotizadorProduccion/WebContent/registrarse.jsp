<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cotizador-QBE - Registro de Usuarios</title>
<link rel="stylesheet" href="static/css/loginForm.css">
<link rel="stylesheet" href="static/css/main.css">
<script src="static/js/jquery.min.js"></script>
<script src="static/js/jquery-ui/jquery-ui.js"></script>
<script src="static/js/sb-admin/bootstrap.js"></script>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

<script>
	$(document).ready(function() {
						var arrTipoIdentificacion = new Array();
						var arrCodigoTipoIdentificacion = new Object();
						$.ajax({
							url : '../CotizadorWeb/EntidadController',
							data : {
								"tipoConsulta" : "encontrarTodos",
								"tipoEntidad" : "clientes"
									},
									type : 'POST',
									datatype : 'json',
									success : function(data) {
										var listadoTipoIdentificacion = data.listadoTipoIdentificacion;

										$
												.each(
														listadoTipoIdentificacion,
														function(index) {
															arrTipoIdentificacion
																	.push(listadoTipoIdentificacion[index].nombre);
															arrCodigoTipoIdentificacion[listadoTipoIdentificacion[index].nombre] = listadoTipoIdentificacion[index].codigo;
															$(
																	"#tipo_identificacion")
																	.append(
																			"<option value='"+listadoTipoIdentificacion[index].nombre+"'>"
																					+ listadoTipoIdentificacion[index].nombre
																					+ "</option>");
														});

									}
								});

						$("#identificacion").change(function(){
							$("#loading_identificacion").fadeIn();
							var identificacion=$("#identificacion").val();
							
							$.ajax({
								url : '../CotizadorWeb/EntidadController',
								data : {
									"identificacion" : identificacion,
									"tipoConsulta" : "cargarPorIdentificacion"
								},
								type : 'POST',
								datatype : 'json',
								success : function(data) {
									var entidad=data.entidad;
									if(entidad.codigo!=null ){
//										$("#codigoEnsurance").val(entidad.codigoEnsurance);
										$("#codigo").val(entidad.codigo);
										$("#identificacion").val();
										$("#nombre").val(entidad.nombre);
										$("#apellido").val(entidad.apellido);
										$("#email").val(entidad.mail);
										$("#tipo_identificacion").val(entidad.tipoIdentificacion); 
									}
									$("#loading_identificacion").fadeOut();
								},
								error:function(){
									$("#loading_identificacion").fadeOut();
								}
							});
							
						});						
						
						$("#signupButton").click(function() {
							var login=$("#login").val().trim();
							var tipo_identificacion=$("#tipo_identificacion").val().trim();
							var tipo_identificacionEnv= arrCodigoTipoIdentificacion[$("#tipo_identificacion").val()];
							var identificacion=$("#identificacion").val().trim();
							var nombre=$("#nombre").val().trim();
							var apellido=$("#apellido").val().trim();
							var email=$("#email").val().trim();
							var password1=$("#password1").val();
							var password2=$("#password2").val();
							
							if(!valido()){
								$("#error_texto").text('Por Favor llenar todos los campos');
								$("#alerta").slideDown();	
							} else
							if(login.length<5){
								$("#error_texto").text('Por favor ingrese un login de al menos 5 caracteres');
								$("#alerta").slideDown();		
							} else 
							if(password1!=password2){
								$("#error_texto").text('Las contraseñas no coinciden');
								$("#alerta").slideDown();		
							} else 
							if(!validaClave(password1)){
									$("#error_texto").text('La contraseña debe tener al menos un numero, una letra mayuscula y una letra minuscula');
									$("#alerta").slideDown();	
							}else
							if(!validaClave(password1)){
								$("#error_texto").text('La contraseña debe tener al menos un numero, una letra mayuscula y una letra minuscula');
								$("#alerta").slideDown();	
							} else
							if(password1<5){
									$("#error_texto").text('Por favor ingrese una contraseña de al menos 5 caracteres');
								$("#alerta").slideDown();		
							}else
							if(!IsEmail(email)){
								$("#error_texto").text('Por favor ingrese un correo válido');
								$("#alerta").slideDown();		
							}  else
							if((tipo_identificacion=='RUC Persona Natural'||tipo_identificacion=='RUC Persona Jurídica')&&!validaRuc(identificacion)){
								$("#error_texto").text('Por favor ingrese un RUC valido');
								$("#alerta").slideDown();	
							} else
							if((tipo_identificacion=='Cédula')&&!validaCedula(identificacion)){
								$("#error_texto").text('Por favor ingrese una Cédula valida');
								$("#alerta").slideDown();	
							} else
							if(password1!=password2){
								$("#error_texto").text('Las contraseñas no coinciden');
								$("#alerta").slideDown();		
							} else 
							if(!validaClave(password1)){
									$("#error_texto").text('La contraseña debe tener al menos un numero, una letra mayuscula y una letra minuscula');
									$("#alerta").slideDown();	
							}else{							
								enviarDatos(nombre, apellido, identificacion, login, password1, tipo_identificacionEnv, email);
							}					
						});
						
						$(".alert").click(function() {
							$(this).slideUp();	
						});
						
						$("#exitox").click(function() {
							$("#exito").slideUp();	
						});
					

					});
	
	function valido(){
		var retorno=true;
		var requeridos=$(".required");
		for(var i=0;i<requeridos.length;i++){
			if(requeridos[i].value.trim().length<=0){
				retorno=false;
			}
		}
		return retorno;
	}
	
	
	function enviarDatos(nombre, apellido, identificacion, login, password, tipoIdentificacion, mail){
		$.ajax({
			url : '../CotizadorWeb/EntidadController',
			data : {
				//"codigoEnsurance" : codigoEnsurance,
				"codigo" : $("#codigo").val(),
				"nombre" : nombre,
				"apellido" : apellido,
				"login" : login,
				"password" : password,
				"mail" : mail,
				"identificacion" : identificacion,
				"tipoIdentificacion" : tipoIdentificacion,
				"tipoEntidad" : "usuarios",
				"tipoConsulta" : "crear"
			},
			type : 'POST',
			datatype : 'json',
			success : function(data) {
				if(data.success){					
					$("#success_texto").text(data.mensajeActivacion);
					$("#exito").show();					
				}else{
					$("#alerta").text(data.error);
					$("#alerta").slideDown();
				}
					
			}
		});
	}

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


							<div class="login-logo" style="color: #0092D4;">
								Registrarse</div>
							<!--  <div hidden='' class="alert alert-warning" id="alerta"><button type="button" class="" data-dismiss="alert">×</button></div> -->
							<div hidden='' class="alert alert-warning" id="alerta">
									<button type="button" class="close"  id="alertax" data-dismiss="">×</button>
									<h4 id='error_texto'></h4>
								</div>
							<div hidden='' class="alert alert-success" id="exito">
									<button type="button" class="close" id="exitox" data-dismiss="">×</button>
									<h4 id='success_texto'></h4>
								</div>
								
								<div class="form-group">
									<input type="hidden"class="form-control" id="codigo">
									<label>Tipo Identificación</label> 
									<select type="select" class="form-control required" id="tipo_identificacion">
										<option>Seleccione una opcion</option>
									</select> 
									<label>Identificación</label>
									 <input type="text" class="form-control required" id="identificacion" onkeypress="validarKeyPress(event,/[0-9\s]/)"> 
									 <div id="loading_identificacion" hidden="" ><span id="loading-msg">Cargando...</span><img  src="static/images/ajax-loader.gif" /></div>  
									 <label>Nombres</label>
									<input type="text" class="form-control required" id="nombre"  onkeypress="validarKeyPress(event,/[a-zA-Z\s]/)"> 
									<label>Apellidos</label>
									<input type="text" class="form-control required" id="apellido" onkeypress="validarKeyPress(event,/[a-zA-Z\s]/)">
									<label>e-mail</label> <input type="text" class="form-control required" id="email" >
									<label>Login</label> 
									<input type="text" class="form-control" id="login"  onkeypress="validarKeyPress(event,/[a-z\s]/)">
									<label>Password</label>
									<input type="PASSWORD" class="form-control required" id="password1">
									<label>Repetir Password</label>
									<input type="PASSWORD" class="form-control required" id="password2">
									<button type="button" class="btn btn-login" id="signupButton">Registrarse</button>
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