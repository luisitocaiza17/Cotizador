<%@page import="com.qbe.cotizador.dao.seguridad.ItemMenuDAO"%>
<%@page import="com.qbe.cotizador.model.UsuarioRol"%>
<%@page import="com.qbe.cotizador.model.Rol"%>
<%@page import="com.qbe.cotizador.model.ItemMenu"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.qbe.cotizador.model.TipoRolModulo"%>
<%@page import="com.qbe.cotizador.model.OpcionMenuRol"%>
<%@page import="com.qbe.cotizador.dao.seguridad.UsuarioRolDAO"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="refresh" content="<%=session.getMaxInactiveInterval()%>;url=/CotizadorWeb/index.jsp?redir=expirada" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

	<link rel="shortcut icon" href="../static/images/favicon.ico">
	
    <title><decorator:title/></title>

    <!-- Core CSS - Include with every page -->
    <link href="/CotizadorWeb/static/css/sb-admin/bootstrap.min.css" rel="stylesheet">
    <link href="/CotizadorWeb/static/font-awesome/css/font-awesome.css" rel="stylesheet">
    <!-- Page-Level Plugin CSS - Blank -->

    <!-- SB Admin CSS - Include with every page -->
    <link href="/CotizadorWeb/static/css/sb-admin/sb-admin.css" rel="stylesheet">
    <link href="/CotizadorWeb/static/css/typeahead.css" rel="stylesheet">
    
        <!-- Core Scripts - Include with every page -->
    <script src="/CotizadorWeb/static/js/jquery.min.js"></script>
    <script src="/CotizadorWeb/static/js/bootstrap.min.js"></script>
    <script src="/CotizadorWeb/static/js/sb-admin/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="/CotizadorWeb/static/js/typeahead.bundle.js"></script>
   
    <!-- Page-Level Plugin Scripts - Blank -->

    <!-- SB Admin Scripts - Include with every page -->
    <script src="/CotizadorWeb/static/js/sb-admin/sb-admin.js"></script>

    <!-- Page-Level Demo Scripts - Blank - Use for reference -->
    
    <decorator:head />
 		<script>

 		$(document).ready(function () {
 			
 			$.ajax({
  				url : '../VariableSistemaController',      				
  				type : 'POST',
  				datatype : 'json',
  				data : {
  					"tipoConsulta":"encontrarValorPorNombre"
  				},
  				success : function(data) {   
  					$("#mensaje").html(data.valor);
  				}
  			});	
 			
 		});
 		
		// Funcion para cerrar sesion
        function cerrarSesion(){
        	$.ajax({
      				url : '../SalirSistema',      				
      				type : 'POST',
      				datatype : 'json',
      				success : function(data) {   
      					if (data.success == true){
      						window.location= "../index.jsp";
      					}
      				}
      			});	   
        	
         }
        </script>
</head>
<body>
		<%
		// Permitimos el acceso si la session existe
		String nombreUsuario = null;
		String rolUsuario = null;
		List<OpcionMenuRol> opcionesMenuRolLista = new ArrayList<OpcionMenuRol>();
		List<OpcionMenuRol> opcionesMenuRolListaPrimerNivel = new ArrayList<OpcionMenuRol>();
		List<OpcionMenuRol> opcionesMenuRolListaSegundoNivel = new ArrayList<OpcionMenuRol>();
		
		if(session.getAttribute("login") == null){
			 response.sendRedirect("/CotizadorWeb");
		}else {
			
			com.qbe.cotizador.model.Usuario usuario = (com.qbe.cotizador.model.Usuario) session.getAttribute("usuario");
			nombreUsuario = usuario.getEntidad().getNombreCompleto();
			
			// Obtenemos el rol asociado al usuario
			UsuarioRolDAO usuarioRolDAO = new UsuarioRolDAO();
			UsuarioRol usuarioRol = usuarioRolDAO.buscarPorUsuario(usuario);
			Rol rol = usuarioRol.getRol();
			
			if(rol != null){
			rolUsuario = rol.getDescripcion();
			
			// Verificamos que el rol asignado al usuario posea opciones de menu caso contrario redireccionamos a una pagina de mensaje
			List<TipoRolModulo> tiposRolModulo =  new ArrayList<TipoRolModulo>();
			tiposRolModulo = rol.getTipoRolModulos();		
			opcionesMenuRolLista = rol.getOpcionMenuRols();
			for(OpcionMenuRol opcionMenuRol: opcionesMenuRolLista){				
					if(opcionMenuRol.getOpcionMenu().getPadreId()!= null){
						opcionesMenuRolListaSegundoNivel.add(opcionMenuRol);
					}else{
						opcionesMenuRolListaPrimerNivel.add(opcionMenuRol);
					}
				}
			
// 			if(tiposRolModulo.size() > 0){								
// 				TipoRolModulo tiposRolModuloEncontrado = tiposRolModulo.get(0);												
// 				opcionesMenuRolLista = tiposRolModuloEncontrado.getOpcionMenuRols();
// 				// Obtenemos la opciones menu principales y las secundarias
// 				for(OpcionMenuRol opcionMenuRol: opcionesMenuRolLista){				
// 					if(opcionMenuRol.getOpcionMenu().getPadreId()!= null){
// 						opcionesMenuRolListaSegundoNivel.add(opcionMenuRol);
// 					}else{
// 						opcionesMenuRolListaPrimerNivel.add(opcionMenuRol);
// 					}
// 				}
// 			}
			
			}
		}
		%>
    <div id="wrapper">

        <nav class="navbar navbar-default navbar-fixed-top nav_theme" role="navigation" style="margin-bottom: 0;background-color:#003da5;">
            <div class="navbar-header">
                 <div class="img-container">
                <a href="Dashboard.jsp">                
					<img src="/CotizadorWeb/static/images/logo_MC.png" style="padding-left: 15px;">				 					
				</a>
				</div>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="color:#fff;">
                        <i class="fa fa-user fa-fw"></i> <span style="font-weight: bold;">Bienvenido:</span> <span style="font-size: small"><%=nombreUsuario %></span> -  <span style="font-weight: bold;">Rol:</span> <span style="font-size: small;"><%=rolUsuario %>  </span>
                        <i class="fa fa-caret-down"></i>                        
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="PerfilUsuario.jsp"><i class="fa fa-user fa-fw"></i> Cambio de Clave</a>
                        </li>
                        <li><a href="ActualizacionUsuario.jsp"><i class="fa fa-gear fa-fw"></i> Actualizar Datos</a>
                        </li>
                        <li class="divider"></li>
                        <li><a onclick="cerrarSesion();" ><i class="fa fa-sign-out fa-fw"></i> Salir</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>                
                <!-- /.dropdown -->
                <li>
	                <div class="img-container">
	                	<img src="/CotizadorWeb/static/images/logo_QBE.png">
	                </div>
                </li>
                </ul>
                </br>
                </br>
                </br>
                <ul class="nav navbar-botton-links navbar-center">
                <li>
                <div style="color:#fff; font-weight: bold; font-size: small" id="mensaje">
	                
	            </div>
                </li>
             </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default navbar-static-side" role="navigation" style="top:33px;">
                <div class="sidebar-collapse">
                    <ul class="nav" id="side-menu">
                        <!-- 
                         <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            
                        </li>
                        -->
                        <%  for(OpcionMenuRol opcionMenu: opcionesMenuRolListaPrimerNivel) {%>
                        <li><a nivel="1" href="#"><i class="<%=opcionMenu.getOpcionMenu().getIcono()%>"></i><%=opcionMenu.getOpcionMenu().getNombre()%><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                        <li>
                        <%  for(OpcionMenuRol opcionMenuSegundoNivel: opcionesMenuRolListaSegundoNivel) {%>
                        <%  if(opcionMenuSegundoNivel.getOpcionMenu().getPadreId().equalsIgnoreCase(opcionMenu.getOpcionMenu().getId())) {%>
                        <a href="#" nivel="2" ><%=opcionMenuSegundoNivel.getOpcionMenu().getNombre()%><span class="fa arrow"></span></a>
                        <ul class="nav nav-third-level" id="test">
                        <%  ItemMenuDAO itemMenuDAO = new ItemMenuDAO(); 
                        List<ItemMenu> listadoItems = itemMenuDAO.buscarPorOpcionMenu(opcionMenuSegundoNivel.getOpcionMenu());
                        for(ItemMenu itemMenu: listadoItems){%>
                        <li><a nivel="3" href="<%=itemMenu.getUrl()%>"><%=itemMenu.getNombre()%></a></li>
                        <% }%>
                        </ul>
                        <% } %>
                        <% } %>
                        </li>
                        </ul>
                        </li>
                        <% }%>
                    </ul>
                    <!-- /#side-menu -->
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                	<br/><br/>
                    
                    <!-- **************************************************************** -->
                    
                    					<decorator:body></decorator:body>
                    
                    <!-- **************************************************************** -->
                    
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
</body>

</html>
