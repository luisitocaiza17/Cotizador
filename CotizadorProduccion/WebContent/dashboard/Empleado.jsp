<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>         
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <title>Empleado - CotizadorQBE</title>

    <!-- Core CSS - Include with every page -->
    <link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
    <link href="../static/css/loading.css" rel="stylesheet">

    <script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="../static/js/util.js"></script>
    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
        var codigo = "";
        var nombre = "";
        var apellido = "";
        var identificacion = "";
        var activo = 0;
        var mail = "";
        var firma = "";
        var tipoConsulta = "";
        var cargo="";
        var sucursal="";
        var arrTipoIdentificacion = new Array();
        var arrCodigoTipoIdentificacion = new Object();
        var arrCargo = new Array();
        var arrCodigoCargo = new Object();
        var arrSucursal = new Array();
        var arrCodigoSucursal = new Object();
        
        $(document).ready(function () {
            activarMenu("Empleado");
            
          //Inicio de carga de combos 
			$.ajax({
				url : '../EmpleadoController',
				data : {
					"tipoConsulta" : "buscarFiltro"
				},
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					var listadoTipoIdentificacion = data.listadoTipoIdentificacion;
					var listadoCargo = data.listadoCargo;
                    var listadoSucursal = data.listadoSucursal;
						
						$.each(listadoTipoIdentificacion, function(index){
				    		arrTipoIdentificacion.push( listadoTipoIdentificacion[index].nombre );
			                arrCodigoTipoIdentificacion[listadoTipoIdentificacion[index].nombre] = listadoTipoIdentificacion[index].codigo;
			                $("#filtro_tipoIdentificacion").append("<option value='"+listadoTipoIdentificacion[index].codigo+"'>"+listadoTipoIdentificacion[index].nombre+"</option>");
			                $("#tipoIdentificacion").append("<option value='"+listadoTipoIdentificacion[index].nombre+"'>"+listadoTipoIdentificacion[index].nombre+"</option>");
						});	
						
						
						$.each(listadoCargo, function (index) {
	                        arrCargo.push(listadoCargo[index].nombre);
	                        arrCodigoCargo[listadoCargo[index].nombre] = listadoCargo[index].codigo;
	                        $("#cargo").append("<option value='" + listadoCargo[index].nombre + "' cargoId='" + listadoCargo[index].codigo + "'>" + listadoCargo[index].nombre + "</option>");
	                        $("#filtro_cargo").append("<option value='" + listadoCargo[index].codigo + "'>" + listadoCargo[index].nombre + "</option>");
	                        
						});
	                    
	                    $.each(listadoSucursal, function (index) {
	                        arrSucursal.push(listadoSucursal[index].nombre);
	                        arrCodigoSucursal[listadoSucursal[index].nombre] = listadoSucursal[index].codigo;
	                        $("#sucursal").append("<option value='" + listadoSucursal[index].nombre + "' sucursalId='" + listadoSucursal[index].codigo + "'>" + listadoSucursal[index].nombre + "</option>");
	                        $("#filtro_sucursal").append("<option value='" + listadoSucursal[index].codigo + "'>" + listadoSucursal[index].nombre + "</option>");
	                        
	                    });
				}
			});
          
          
			$('#LimpiarBtn').click(function(){	 
	    	 	$("#filtro_nombre").val("");
	    	 	$("#filtro_identificacion").val("");
	    	 	$("#filtro_tipoIdentificacion").val("-1");
	    	 	$("#filtro_apellido").val("");
	    	 	$("#filtro_ensurance").val("");
	    	 	$("#filtro_cargo").val("-1");
	    	 	$("#filtro_sucursal").val("-1");
	    	 	$("#filtro_activo").prop('checked', true);
	 			$('#dataTableContent').html('');
	 			
	    	 });
          
			$("#loading").fadeOut("slow"); 
			$("#cargando").fadeOut("slow");
			$('#ConsultaBtn').click(function(){
				$("#loading").fadeIn("slow");
				var nombreFiltro = $("#filtro_nombre").val();
	    		 var identificacionFiltro = $("#filtro_identificacion").val();
	    		 var tipoIdentificacionFiltro = $("#filtro_tipoIdentificacion").val();
	    		 var apellidoFiltro = $("#filtro_apellido").val();
	    		 var ensuranceFiltro = $("#filtro_ensurance").val();
	    		 var cargoFiltro = $("#filtro_cargo").val();
	    		 var sucursalFiltro = $("#filtro_sucursal").val();
	    		 var activoFiltro = 0;
	    		 if ($("#filtro_activo").is(':checked')) activoFiltro = 1;
	    		 if(tipoIdentificacionFiltro == "-1")
	    			 tipoIdentificacionFiltro = "";
	    		 if(cargoFiltro == "-1")
	    			 cargoFiltro = "";
	    		 if(sucursalFiltro == "-1")
	    			 sucursalFiltro = "";
	    		 if(nombreFiltro == "" && identificacionFiltro == "" && tipoIdentificacionFiltro == "" && apellidoFiltro == "" && ensuranceFiltro == "" && cargoFiltro == "" && sucursalFiltro == ""){
			     	alert("Ingrese al menos un campo de busqueda");
			     	$("#loading").fadeOut("slow");
			     	return false;
			   	}
	    		 
				$.ajax({
	                url: '../EmpleadoController',
	                data: {
	                    "tipoConsulta": "encontrarTodos",
	                    "nombreFiltro" : nombreFiltro,
	                    "identificacionFiltro" : identificacionFiltro,
	                    "tipoIdentificacionFiltro" : tipoIdentificacionFiltro,
	                    "apellidoFiltro" : apellidoFiltro,
	                    "ensuranceFiltro" : ensuranceFiltro,
	                    "cargoFiltro" : cargoFiltro,
	                    "sucursalFiltro" : sucursalFiltro,
	                    "activoFiltro" : activoFiltro
	                },
	                type: 'POST',
	                datatype: 'json',
	                success: function (data) {
	                	$("#loading").fadeOut("slow");
						$('#dataTableContent').html('');
	                	if (data.numRegistros > 0) {
	                   
	                    
	                    	var listadoEmpleado = data.listadoEmpleado;                    	
	                    	$.each(listadoEmpleado, function(index){
								$("#dataTableContent").append("	<tr class='odd gradeX'>" +
										" <td relation='nombre'>" + listadoEmpleado[index].nombre + "</td>" +
			                            " <td relation='apellido'>" + listadoEmpleado[index].apellido + "</td>"+
			                            " <td relation='codigoEnsurance'>" + listadoEmpleado[index].codigoEnsurance + "</td>"+
			                            " <td relation='tipoIdentificacion'>" + listadoEmpleado[index].tipoIdentificacion + "</td>" +
			                            " <td relation='identificacion'>" + listadoEmpleado[index].identificacion + "</td>" +
			                            " <td relation='mail'>" + listadoEmpleado[index].mail + "</td>" +
			                            //" <td relation='firma'>" + listadoEmpleado[index].firma + "</td>" +
			                            " <td relation='cargo'>" + listadoEmpleado[index].cargo + "</td>" +
			                            " <td relation='sucursal'>" + listadoEmpleado[index].sucursal + "</td>" +
				                            " <td relation='activo'><center>" + listadoEmpleado[index].activo + "</center></td>" +
			                             " <td width='175px'>" +                             
			                            " <input type='hidden' value='" + listadoEmpleado[index].codigo + "'/>" +                            
			                            " <button type='button' class='btn btn-success btn-xs actualizar-btn'>" +
			                            " <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
			                            " </button>" +
//			                             " <button type='button' class='btn btn-danger btn-xs eliminar-btn'>" +
//			                             "<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar" +
//			                             " </button>" +
										" <button type='button' class='btn btn-info btn-xs cambioEstado-btn'>" +
									  	"<span class='glyphicon glyphicon glyphicon-refresh' id='cambioEstado-btn'>Activar/Desactivar</span>" +
										" </button>" +
			                            "</td>" +
			                            "</tr>");
							});
	                    	listadoEmpleado = null;
	                        //Boton Cambio de Estado
	                        $(".cambioEstado-btn").bind({click: function() {		
									codigo = $(this).parent().children().first().val();
									var activoaux = $(this).parent().prev().text();
									 if (activoaux == "Si"){
		  						    		activo = 1;
		  						    		
		  						    	}else {
		  						    		activo = 0;
		  						    	}
		                                nombre = "";
		                                apellido = "";
		                                cargo="";
		                                mail="";
		                                firma="";
		                                tipoIdentificacion = "";
		                                codigoEnsurance = "";
		                                identificacion = "";
		                                sucursal = "";
										tipoConsulta = "cambioEstado";
		                                enviarDatos(codigoEnsurance, codigo, nombre, apellido, identificacion, activo, cargo, firma, tipoIdentificacion, mail, sucursal, tipoConsulta);
	 								alert("Estado Cambiado");
	 								location.reload();
	 						}
						});                       
	                        
	                        /* Inicio Controles Actualizar Registro*/
	                        $(".actualizar-btn").bind({click: function () {
	                            $("#addButton").trigger("click");
	                            $("#codigo").val($(this).parent().children().first().val());
	                            var elem = $(this).parent();
	                            var bandera = 1;
	                            do {
	                                elem = elem.prev();
	                                if (elem.is("td")) {
	                                    var elemCode = elem.attr("relation");
	                                    elementType(elem.text(), elemCode, $("#" + elemCode).attr("type"));
	                                } else {
	                                    bandera = 0;
	                                }
	                            } while (bandera == 1);                            
	                        }
	                        });
	                        /* Fin Controles Actualizar Registro*/

	                        /* Inicio Controles Eliminar Registro */
	                        $(".eliminar-btn").bind({click: function () {
	                            var r = confirm("Seguro que desea eliminar el Empleado " + $(this).parent().parent().children().first().text());
	                            if (r == true) {
	                                codigo = $(this).parent().parent().children().first().text();
	                                nombre = "";
	                                apellido = "";
	                                activo = "";
	                                cargo="";
	                                mail="";
	                                firma="";
	                                tipoIdentificacion = "";
	                                codigoEnsurance = "";
	                                identificacion = "";
	                                sucursal = "";
	                                tipoConsulta = "eliminar";
	                                enviarDatos(codigoEnsurance, codigo, nombre, apellido, identificacion, activo, cargo, firma, tipoIdentificacion, mail, sucursal, tipoConsulta);
	                                $(this).parent().parent().remove();
	                            }
	                        }
	                        });
	                        /* Fin Controles Elminar Registro */
	                    } else {
	                        $("#dataTableContent").append("<tr><td colspan='4'>No existen Registros</td></tr>");
	                    }
	                }
	            });	
			});
            

            /* Inicio Controles Grabar Resgistro*/
            $("#save-record").click(function (event) {
            	var requerido = true;
                 tipoIdentificacion = arrCodigoTipoIdentificacion[$("#tipoIdentificacion").val()];
                 if(tipoIdentificacion == undefined && requerido)
				 {
                	requerido = false;
					alert("Por favor seleccione un tipo de identificacion");	
				 }
                 cargo = arrCodigoCargo[$("#cargo").val()];
                 if(cargo == undefined && requerido)
				 {
                	requerido = false;
					alert("Por favor seleccione un cargo");	
				 }
                 sucursal = arrCodigoSucursal[$("#sucursal").val()];
                 if(sucursal == undefined && requerido)
				 {
                	requerido = false;
					alert("Por favor seleccione una sucursal");	
				 }
                 
                $(".required").css("border", "1px solid #ccc");
                $(".required").each(function (index) {
                    var cadena = $(this).val();
                    if (cadena.length <= 0 && requerido) {
                        $(this).css("border", "1px solid red");
                        alert("Por favor ingrese el campo requerido");
                        $(this).focus();
                        requerido = false;
                        return false;
                    }
                });


                if ($("#activo").is(':checked')) activo = 1;
                codigo = $("#codigo").val();
                nombre = $("#nombre").val();
                apellido = $("#apellido").val();
                identificacion = $("#identificacion").val();
                mail = $("#mail").val();
               

                if (codigo == "")
                    tipoConsulta = "crear";
                else
                    tipoConsulta = "actualizar";
                codigoEnsurance = $("#codigoEnsurance").val();
                if(requerido)
                 	enviarDatos(codigoEnsurance, codigo, nombre, apellido, identificacion, activo, cargo, firma, tipoIdentificacion, mail, sucursal, tipoConsulta);
               
            });
            /* Fin Controles Grabar Resgistro*/

            function enviarDatos(codigoEnsurance, codigo, nombre, apellido, identificacion, activo, cargo, firma, tipoIdentificacion, mail, sucursal, tipoConsulta) {
                $.ajax({
                    url: '../EmpleadoController',
                    data: {
                        "codigoEnsurance": codigoEnsurance,
                        "codigo": codigo,
                        "nombre": nombre,
                        "apellido": apellido,
                        "activo": activo,
                        "sucursalId": sucursal,
                        "cargo": cargo,
                        //"firma": firma,
                        "mail": mail,
                        "identificacion": identificacion,
                        "tipoIdentificacion": tipoIdentificacion,
                        "tipoEntidad": "empleados",
                        "tipoConsulta": tipoConsulta
                    },
                    type: 'POST',
                    datatype: 'json',
                    success: function (data) { 
                        $("#msgPopup").show();
                        //$("#close-popup").trigger("click");
                    }
                });
            }
            
            $("#identificacion").change(function(){
				$("#loading_identificacion").fadeIn();
				var identificacion=$("#identificacion").val();
				var tipo_identificacion=$("#tipoIdentificacion").val();
				var validacion = true;
				if(tipo_identificacion == 'Seleccione una opcion')
				{
					$("#identificacion").val('');
					alert("Por favor seleccione un tipo de identificacion");	
					validacion = false;
					$("#loading_identificacion").fadeOut();
				}
				if((tipo_identificacion=='RUC Persona Natural'||tipo_identificacion=='RUC Persona Jurídica')&&!validaRuc(identificacion)){
					alert('Por favor ingrese un RUC valido');
					$("#identificacion").val('');
					validacion = false;
					$("#loading_identificacion").fadeOut();
				} else
				if((tipo_identificacion=='Cédula')&&!validaCedula(identificacion)){
					alert('Por favor ingrese una Cédula valida');
					$("#identificacion").val('');
					validacion = false;
					$("#loading_identificacion").fadeOut();
				}
				if(validacion)
					$.ajax({
						url : '../EntidadController',
						data : {
							"identificacion" : identificacion,
							"tipoConsulta" : "cargarPorIdentificacionEnsurance"
						},
						type : 'POST',
						datatype : 'json',
						success : function(data) {
							var entidad=data.entidad;
							//alert(entidad.codigo);
							if(entidad!=null ){
							$("#codigoEnsurance").val(entidad.entidadIdEnsurance);
							//$("#codigo").val(entidad.codigo);
							//$("#identificacion").val();
							$("#nombre").val(entidad.nombre);
							$("#apellido").val(entidad.apellido);
							$("#mail").val(entidad.mail);
							$("#tipoIdentificacion").val(entidad.tipoIdentificacionNombre); }
							$("#loading_identificacion").fadeOut();
							validarEntidadJr(identificacion);
						},
						error:function(){
							$("#loading_identificacion").fadeOut();
						}
					});
				
			});
            
    		function validarEntidadJr(identificacion){
    			
    			$.ajax({
    				url : "../EntidadJrController",

    				data : {
    					"tipoConsulta" : "encontrarPorIdentificacion",
    					"identificacion" : identificacion
    				},
    				type : 'post',
    				datatype : 'json',
    				success : function (data) {
    					if(data.success){
    							if(data.esRiesgosa){
    								alert("La persona se encuentra bloqueda, no puede realizar ningun tramite");
    								$("#identificacion").val("");
    								$("#nombre").val("");
    								$("#mail").val("");
    								$("#tipoIdentificacion").val("");
    								$("#codigoEnsurance").val("");    								
    							}
    						}
    					else{

    					}
    				}
    			});
    		}
    		
    		$("#mail").change(function(){
				var mail=$("#mail").val();
				if(!IsEmail(mail))
					{
					alert("Ingrese un correo Valido!");
					$("#mail").val('');
					}
								
			});
        });
    </script>
</head>
<body>
<%
			// Permitimos el acceso si la session existe		
				if(session.getAttribute("login") == null){
				    response.sendRedirect("/CotizadorWeb");
				}
				else {
					com.qbe.cotizador.model.Usuario usuario = (com.qbe.cotizador.model.Usuario) session
							.getAttribute("usuario");

					// Obtenemos el rol asociado al usuario
					com.qbe.cotizador.dao.seguridad.UsuarioRolDAO usuarioRolDAO = new com.qbe.cotizador.dao.seguridad.UsuarioRolDAO();
					com.qbe.cotizador.model.UsuarioRol usuarioRol = usuarioRolDAO
							.buscarPorUsuario(usuario);
					com.qbe.cotizador.model.Rol rol = usuarioRol.getRol();
					com.qbe.cotizador.dao.seguridad.OpcionMenuRolDAO opcionMenuRolDAO = new com.qbe.cotizador.dao.seguridad.OpcionMenuRolDAO();
					if (!opcionMenuRolDAO.verificarPermiso(rol.getId(),
							"Empleados")) {
						response.sendRedirect("/CotizadorWeb/dashboard/AccesoDenegado.jsp");
					}
				}
%>
<div class="row crud-nav-bar">

<div class="well">
			<table class="table">
				<thead>
					<tr>
						<td colspan="3" style="font-weight: bold;"><center>Buscador
								Cliente</center></td>

					</tr>
					<tr>
						<th style="text-align: center;">Tipo Identificacion: <select
							type="select" class="form-control"
							id="filtro_tipoIdentificacion">
								<option value="-1">Seleccione una opcion</option>
						</select></th>
						<th style="text-align: center;">Cargo: <select
							type="select" class="form-control"
							id="filtro_cargo">
								<option value="-1">Seleccione una opcion</option>
						</select></th>
						<th style="text-align: center;">Sucursal: <select
							type="select" class="form-control"
							id="filtro_sucursal">
								<option value="-1">Seleccione una opcion</option>
						</select></th>
						

					</tr>

					<tr>
					<th style="text-align: center;">Identificacion: <input type="text"
							id="filtro_identificacion"></th>
						<th style="text-align: center;">Nombres: <input type="text" id="filtro_nombre"></th>
						<th style="text-align: center;">Apellido: <input type="text" id="filtro_apellido"></th>
						

					</tr>
					<tr>
						<th style="text-align: center;">Ensurance: <input type="text" id="filtro_ensurance"></th>
						<th style="text-align: center;">Activo: <input type="checkbox" value="activo"
							id="filtro_activo" checked></th>
					</tr>

				</thead>
				<tbody>
					<tr>
						<th style="text-align: center;">
							<button class="btn btn-primary" id="ConsultaBtn">
								<span class="glyphicon glyphicon-search"></span> &nbsp; Consulta
							</button>							
						</th>
						<th style="text-align: center;">
							<button class="btn btn-primary" id="LimpiarBtn">
								<span class="glyphicon glyphicon-trash"></span> &nbsp; Limpiar
							</button>
						</th>
						<th style="text-align: center;">
							<button class="btn btn-primary" data-toggle="modal"
								data-target="#add" id="addButton">
								<span class="glyphicon glyphicon-plus"></span> &nbsp; Nuevo
							</button>
						</th>
						<th>&nbsp;</th>
					</tr>
					<tr>
						<th>&nbsp;</th>
						<th><div id="cargando" style="position: fixed; left: 50%;">
								<div class="loading-indicator">
									<img src="../static/images/ajax-loader.gif" /><br />
									<br /> <span id="loading-msg">Cargando Espere...</span>
								</div>
							</div></th>
						<th>&nbsp;</th>
					</tr>

				</tbody>
			</table>
		</div>

    
    

    <!-- Modal -->
    <div class="modal fade" id="add" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form id="formCrud">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" onclick="window.location.reload();">
                            <span aria-hidden="true">&times;</span><span class="sr-only">Cerrar</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel">Nuevo Empleado</h4>
                    </div>
                    <div class="modal-body">
                        <div class="alert alert-success" id="msgPopup">El Empleado se grabo con exito.</div>
                        <div class="form-group">
                            <input type="hidden" class="form-control" id="codigo">
                            <label>Tipo Identificación</label>
                            <select type="select" class="form-control " id="tipoIdentificacion">
                                <option>Seleccione una opcion</option>
                            </select>
                            <label>Identificación</label>
                            <input type="text" class="form-control required" id="identificacion">
                            <div id="loading_identificacion" hidden="" ><span id="loading-msg">Cargando...</span><img  src="../static/images/ajax-loader.gif" /></div>
							<label>Nombre del Empleado</label>
                            <input type="text" class="form-control required" id="nombre" onkeypress="validarKeyPress(event,/[a-zA-Z\s]/)">
                            <label>Apellido del Empleado</label>
                            <input type="text" class="form-control required" id="apellido" onkeypress="validarKeyPress(event,/[a-zA-Z\s]/)">
                            <label>Codigo Ensurance</label>
                            <input type="text" class="form-control required" id="codigoEnsurance" onkeypress="validarKeyPress(event,/[0-9]/)">
							<label>Cargo</label>
                            <select type="select" class="form-control " id="cargo">
                                <option>Seleccione una opcion</option>
                            </select>
							<label>Sucursal</label>
                            <select type="select" class="form-control " id="sucursal">
                                <option>Seleccione una opcion</option>
                            </select>

<!--                             <label>Firma</label> -->
<!--                             <input type="text" class="form-control" id="firma"> -->
                            <label>Email</label>
                            <input type="text" class="form-control required" id="mail">
                            

                            <div class="checkbox">
                                <label> <input type="checkbox" value="activo" id="activo">Activo</label>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" id="close-popup"
                                data-dismiss="modal">Cerrar
                        </button>
                        <button type="button" class="btn btn-primary" id="save-record" >Guardar
                        </button>
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
        <div class="table-responsive">
            <div class="input-group"><span class="input-group-addon">Filtro</span>
                <input id="filter" type="text" class="form-control" placeholder="Escriba la palabra a buscar...">
            </div>
            <table class="table table-striped table-bordered table-hover"
                   id="dataTable">
                <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th>Código Ensurance</th>
                    <th>Tipo Identificación</th>
                    <th>Identificación</th>
                    <th>Mail</th>
<!--                     <th>Firma</th> -->
                    <th>Cargo</th>
                    <th>Sucursal</th>
                    <th>Activo</th>
                </tr>
                </thead>
                <tbody id="dataTableContent" class="searchable">

                <div id="loading">
                    <div class="loading-indicator">
                        <img src="../static/images/ajax-loader.gif"/><br/><br/>
                        <span id="loading-msg">Cargando...</span>
                    </div>
                </div>

                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- Datatable -->
</body>
</html>