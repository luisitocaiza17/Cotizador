<%@page import="com.qbe.cotizador.model.PuntoVenta"%>
<%@page import="com.qbe.cotizador.model.OpcionMenuPantallaRol"%>
<%@page import="com.qbe.cotizador.model.Rol"%>
<%@page import="com.qbe.cotizador.model.UsuarioRol"%>
<%@page import="com.qbe.cotizador.model.UsuarioXPuntoVenta"%>
<%@page import="com.qbe.cotizador.model.TipoRolModulo"%>
<%@page import="com.qbe.cotizador.model.ProductoXPuntoVenta"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<script src="../static/js/util.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Grupos Productos Pymes</title>
<script>
	$(document).ready(function() {
		$(".panel").hover(function() {
			$(this).removeClass("panel-default").addClass("panel-primary");
		}, function() {
			$(this).removeClass("panel-primary").addClass("panel-default");
		});
	});
</script>
</head>
<body>
	<div class="row">
		<%
			// Permitimos el acceso si la session existe		
				if(session.getAttribute("login") == null){
				    response.sendRedirect("/CotizadorWeb");
				}else {
			com.qbe.cotizador.model.Usuario usuario = (com.qbe.cotizador.model.Usuario)session.getAttribute("usuario");
			List<UsuarioXPuntoVenta> listadoUsuariosPV=usuario.getUsuarioXPuntoVentas();
			List<UsuarioRol> listadoUsuarioRol = usuario.getUsuarioRols();			
			if(listadoUsuarioRol.size() > 0){
				UsuarioRol usuarioRol = listadoUsuarioRol.get(0);
				Rol rol = usuarioRol.getRol();
				
				// Verificamos que el rol asignado al usuario posea opciones de menu caso contrario redireccionamos a una pagina de mensaje
				List<TipoRolModulo> tiposRolModulo =  new ArrayList<TipoRolModulo>();
				tiposRolModulo = rol.getTipoRolModulos();			
				List<ProductoXPuntoVenta> listadoProductosPV=null;
				if(listadoUsuariosPV.size()>0){
					UsuarioXPuntoVenta usuarioPV=listadoUsuariosPV.get(0);
					PuntoVenta puntoV=usuarioPV.getPuntoVenta();
					listadoProductosPV=puntoV.getProductoXPuntoVentas();
				}
				
				if(tiposRolModulo.size() == 0){
				String mensajeSistema= "Rol no tiene asignado opciones de  menu";								
		%>		
		<div class="row">
			<div class="col-lg-3 col-md-6" style="float: none; margin: 0 auto;">
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="row">
							<div class="col-md-2">
								<i class="fa fa-info-circle fa-5x"></i>
							</div>
							<div class="col-md-10">
								<span class="pull-left"><span style="font-weight: bold;">Mensaje del Sistema:</span><br>
									<%=mensajeSistema%> 
								</span>
							</div>
						</div>
					</div>					
				</div>
			</div>
		</div>		
		<%
			}else{
				List<OpcionMenuPantallaRol> listaOpcionMenuRol = rol.getOpcionMenuPantallaRols();
				if(listaOpcionMenuRol.size() > 0){
					for(int i=0; i<listaOpcionMenuRol.size();i++){
						if(listaOpcionMenuRol.get(i).getOpcionPantalla().getIdentificador().equalsIgnoreCase("icono_pymes_c")){
							if(listadoProductosPV==null)
							{
		%>
			<div class="col-lg-3 col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i
								class="<%=listaOpcionMenuRol.get(i).getOpcionPantalla().getIcono().toString()%>"></i>
						</div>
					</div>
				</div>
				<a
					href="<%=listaOpcionMenuRol.get(i).getOpcionPantalla().getUrl().toString()%>">
					<div class="panel-footer">
						<span class="pull-left"><%=listaOpcionMenuRol.get(i).getOpcionPantalla().getNombre().toString()%></span>
						<span class="pull-right"><i
							class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
		<%						
								
							}
							else{	
								int contador=0;
								for(ProductoXPuntoVenta productoPV:listadoProductosPV){
									if(productoPV.getGrupoPorProducto().getNombreComercialProducto().toUpperCase().equals(listaOpcionMenuRol.get(i).getOpcionPantalla().getNombre().toUpperCase()))
										contador++;
								}
								if(contador>0){
		%>
		<div class="col-lg-3 col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i
								class="<%=listaOpcionMenuRol.get(i).getOpcionPantalla().getIcono().toString()%>"></i>
						</div>
					</div>
				</div>
				<a
					href="<%=listaOpcionMenuRol.get(i).getOpcionPantalla().getUrl().toString()%>">
					<div class="panel-footer">
						<span class="pull-left"><%=listaOpcionMenuRol.get(i).getOpcionPantalla().getNombre().toString()%></span>
						<span class="pull-right"><i
							class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
		<%
						}
					}
				}
			}
				}
			}
				  }	else{
					  String mensajeSistema= "El usuario no esta parametrizado en la tabla usuario_rol";
					  %>		
						<div class="row">
							<div class="col-lg-3 col-md-6" style="float: none; margin: 0 auto;">
								<div class="panel panel-default">
									<div class="panel-heading">
										<div class="row">
											<div class="col-md-2">
												<i class="fa fa-info-circle fa-5x"></i>
											</div>
											<div class="col-md-10">
												<span class="pull-left"><span style="font-weight: bold;">Mensaje del Sistema:</span><br>
													<%=mensajeSistema%> 
												</span>
											</div>
										</div>
									</div>					
								</div>
							</div>
						</div>		
						<%				  
				  }
				}
		%>
	</div>
</body>
</html>