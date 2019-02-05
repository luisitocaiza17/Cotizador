<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link
	href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="../static/css/loading.css" rel="stylesheet">

<script
	src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
<script
	src="../static/js/sb-admin/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="../static/js/util.js"></script>

<!-- KENDO -->
<link rel="stylesheet" href="../static/css/Kendo/kendo.common.min.css" />
<link rel="stylesheet" href="../static/css/Kendo/kendo.blueopal.min.css" />
<script src="../static/js/Kendo/kendo.all.min.js"></script>
<script src="../static/js/Kendo/kendo.web.min.js"></script>

<title>Cobertura Textos PYMEs - Cotizador QBE</title>

<style>
.modal .modal-dialog {
	width: 80%;
}
.modal-body {
    height:80%; 
}
</style>

<script>
    $(function(){
  	  $(".wrapper1").scroll(function(){
  	    $(".wrapper2").scrollLeft($(".wrapper1").scrollLeft());
  	  });
  	  $(".wrapper2").scroll(function(){
  	    $(".wrapper1").scrollLeft($(".wrapper2").scrollLeft());
  	  });
  	});
    var notification;
    var tipoConsulta = "";
    var textoGrupoCobertura = "";
    var grupoCoberturaId = "";
    var ramoId = "";
    var texto = "";
    var grupoporproductoid= "";
	var productoEnsId= "";
    
    var ramoList = "";
    var grupoCoberturaList = "";
    
    $(document).ready(function(){
    	activarMenu("CoberturaTextosPyme");
    	/*Mensaje de error*/
		notification = $("#notification").kendoNotification({
        position: {
            pinned: true,
            top: 90,
            right: 35
        },
        autoHideAfter: 10000,
        hideOnClick: false,
        stacking: "down",
        templates: [ {
            type: "error",
            template: $("#errorTemplate").html()
    		}]
        }).data("kendoNotification");
	
	 $(document).one("kendo:pageUnload", function(){ });
    	cargarProductosPorGrupoProducto();
    	
    	$("#texto").kendoEditor({
            tools: [
                "bold",
                "italic",
                "underline",
                "strikethrough",
                "justifyLeft",
                "justifyCenter",
                "justifyRight",
                "justifyFull",
                "insertUnorderedList",
                "insertOrderedList",
                "indent",
                "outdent",
                "createLink",
                "unlink",
                "insertImage",
                "insertFile",
                "subscript",
                "superscript",
                "createTable",
                "addRowAbove",
                "addRowBelow",
                "addColumnLeft",
                "addColumnRight",
                "deleteRow",
                "deleteColumn",
                "viewHtml",
                "formatting",
                "cleanFormatting",
                "fontName",
                "fontSize",
                "foreColor",
                "backColor",
                "print"
            ]
        });
    	
    	$("#ramoId").kendoMultiSelect({
			dataTextField: "ramoNombre",
            dataValueField: "ramoId",
            animation: false,
            maxSelectedItems: 1
		});
    	ramoList = $("#ramoId").data("kendoMultiSelect");
    	
    	
    	
    	$("#grupoCoberturaId").kendoMultiSelect({
			dataTextField: "gCoberturaNombre",
            dataValueField: "gCoberturaId",
            animation: false,
            maxSelectedItems: 1
		});
    	grupoCoberturaList = $("#grupoCoberturaId").data("kendoMultiSelect");
    	
    	//texto = ($("#text").data("kendoEditor")).value();
    	
    	cargar();
    	
    	$("#save-record").bind({click: function(){				
			
			/*$(".required").css("border", "1px solid #ccc");
			$(".required").each(function(index) {
				var cadena = ($(this).data("kendoEditor")).value();
				if (cadena .length <= 0) {
					$(this).css("border", "1px solid red");
					alert("Por favor ingrese el campo requerido");
					$(this).focus();
					return false;
				}		
			});	*/
			
			
							
			textoGrupoCobertura = $("#textoGrupoCobertura").val();
			grupoporproductoid= $("#productos").val();
			productoEnsId= $("#productos").val();
		 	grupoCoberturaId = $("#grupoCoberturaId option:selected").val(); 
		 	ramoId = $("#ramoId option:selected").val();
		 	texto = ($("#texto").data("kendoEditor")).value();
    		
		 	if( typeof grupoCoberturaId === 'undefined' || typeof ramoId === 'undefined' || typeof texto === 'undefined' || texto === ""){
		 		$("#grupoCoberturaId").css("border", "1px solid red");
 				$("#ramoId").css("border", "1px solid red");
		 		$("#texto").css("border", "1px solid red");
		 		alert("Por favor ingrese el campo requerido.");
		 		return false;
		 	}
		 	
			if(textoGrupoCobertura=="" || textoGrupoCobertura== 0)
				tipoConsulta = "crear";
			else
				tipoConsulta = "actualizar";
			
			enviarDatos(textoGrupoCobertura,grupoCoberturaId,texto,ramoId,tipoConsulta,grupoporproductoid,productoEnsId);
			}			
			
		});
    	
    	$("#ramoId").change(function(){				
    		//$("select option:selected").each(function(){
    			ramoId = $("#ramoId option:selected").val();
    			$.ajax({
    				url : '../PymeTextoGrupoCoberturaController',
    				data : {
    					"tipoConsulta":"cargarGruposCobertura",
    					"ramoId" : ramoId							
    				},
    				type : 'POST',
    				datatype : 'json',
    				success : function(data){
    					if (data.success == false){
    						 notification.show({
    	                           title: "ERROR",
    	                           message: data.error
    	                       }, "error");							
    						return;
    					}
    					grupoCoberturaList.dataSource.filter({});
    					grupoCoberturaList.setDataSource(data.grupoCoberturaArr);
    					
    				}
    			});
    		//});
    	});
    	$("#addButton").click(function(){
    		/*Activo los select*/
    		ramoList.enable(true);
			grupoCoberturaList.enable(true);
			/*Limpio los controles*/
    		$("#textoGrupoCobertura").val("");
    		($("#texto").data("kendoEditor")).value("");
    		grupoCoberturaList.dataSource.filter({});
    	});
    });
    
    
    function cargarProductosPorGrupoProducto() {
		// Consultar listado de productos dentro de un grupos de productos
		$("#productos").empty();

		$
		.ajax({
			url : '../GrupoPorProductoController',

			data : {
				"tipoConsulta" : "encontrarTodosPorGrupo",
				"tipoObjeto" : "PYMES",
				"grupoProductoId" : 94
			},
			async : false,
			type : 'post',
			datatype : 'json',
			success : function(data) {
				if (data.success == false){
					 notification.show({
                          title: "ERROR",
                          message: data.error
                      }, "error");							
					return;
				}
				var listadoGrupos = data.listadoGruposPorProducto;
				$("#productos").append(
				"<option value=''>Seleccione una opcion</option>");
				$.each(
					listadoGrupos,
					function(index) {
						$("#productos")
						.append(
								"<option value='"
								+ listadoGrupos[index].id
								+ "'>"
								+ listadoGrupos[index].nombre
								+ "</option>");
					});
			}
		});
	}
    
    /*function cargarCiudad(){
    	ramoList.d
    }
    */
    function cargar(){
		$("#dataTableContent").children().remove();
		$.ajax({
			url : '../PymeTextoGrupoCoberturaController',
			data : {
				"tipoConsulta" : "encontrarTodos"
			},
			type : 'POST',
			datatype : 'json',
			success : function (data) {
				if (data.success == false){
					 notification.show({
                          title: "ERROR",
                          message: data.error
                      }, "error");							
					return;
				}
				var listadoCoberturaPymes = data.listadoCoberturaPymes;
				$.each(listadoCoberturaPymes, function (index){
					$("#dataTableContent").append("<tr class='odd gradeX'>"+
							"<td relation='grupoPorProducto'>"+listadoCoberturaPymes[index].grupoPorProductoId+"</td>"+
							"<td relation='ramo'>"+listadoCoberturaPymes[index].ramoId+"</td>"+
							"<td relation='grupoCobertura' >" + listadoCoberturaPymes[index].grupoCoberturaId + "</td>"+																	
							"<td width='175px'>"+									
								"<input type='hidden' id='textoGrupoCobertura' value='" + listadoCoberturaPymes[index].textoGrupoCobertura + "'/>"+	
								" <button type='button'  name='actualizar-btn' data-toggle='modal' data-target='#add' class='btn btn-success btn-xs actualizar-btn' onclick='actualizar("+ listadoCoberturaPymes[index].textoGrupoCobertura +")'>" +
									" <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
								" </button>" +									
								" <button type='button' class='btn btn-danger btn-xs eliminar-btn' onclick='eliminar("+ listadoCoberturaPymes[index].textoGrupoCobertura +")'>" +
								  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar" +
								" </button>" +									
							"</td>" +
						"</tr>");		
				});				
				$("#loading").remove();	
				
				/*Activo los select*/
				ramoList.enable(true);
				grupoCoberturaList.enable(true);			
				
				/*Cargo el select de Ramo*/
				ramoList.dataSource.filter({});
				ramoList.setDataSource(data.ramoArr);
				/*var ramoArr = data.ramoArr;
				$.each(ramoArr, function (index){
					$("#ramoId").append("<option value='"+ ramoArr[index].ramoId +"'>"+ ramoArr[index].ramoNombre +"</option>");
				});*/
			}
		});
	}
    
    function enviarDatos(textoGrupoCobertura,grupoCoberturaId,texto,ramoId,tipoConsulta,grupoPorProductoId,productoEnsId){
		$.ajax({
			url : '../PymeTextoGrupoCoberturaController',
			async : false,
			data : {
				"textoGrupoCobertura" : textoGrupoCobertura,
			 	"grupoCoberturaId" : grupoCoberturaId,	 
			 	"texto" : texto,
			 	"ramoId" : ramoId,			 	
			 	"tipoConsulta" : tipoConsulta,
			 	"grupoPorProductoId" : grupoPorProductoId,
			 	"productoEnsId" : productoEnsId,
			},
			type : 'POST',
			datatype : 'json',
			success : function(data){
				if (data.success == false){
					 notification.show({
                          title: "ERROR",
                          message: data.error
                      }, "error");							
					return;
				}
				$("#msgPopup").show();
			}
		});
	}
    
    function eliminar(id){
		var r = confirm("Seguro que desea eliminar");
		if(r == true){
			textoGrupoCobertura = id;
		 	grupoCoberturaId = "";	 
		 	texto = "";
		 	ramoId = "";
		 	tipoConsulta = "eliminar";
		 	enviarDatos(textoGrupoCobertura,grupoCoberturaId,texto,ramoId,tipoConsulta,"","");			 	
		}
		cargar();
	}
    
    function actualizar(id){			
		$.ajax({
			url : '../PymeTextoGrupoCoberturaController',
			async : false,
			data : {
				"textoGrupoCobertura" : id,
			 	"tipoConsulta" : "obtenerPorId"
			},
			type : 'POST',
			datatype : 'json',
			success : function(data){
				if (data.success == false){
					 notification.show({
                          title: "ERROR",
                          message: data.error
                      }, "error");							
					return;
				}
				$("#productos").val(data.gGrupoPorProductoId);
				$("#productoEnsID").val(data.gProductoEnsID);
				$("#ramoId").data("kendoMultiSelect").value(data.ramoId);
				//$("#ramoId").val(data.ramoId);
				$("#textoGrupoCobertura").val(data.textoGrupoCobertura);
				grupoCoberturaList.dataSource.filter({});
				grupoCoberturaList.setDataSource(data.grupoCoberturaArr);
				/*Desactivo los select*/
				ramoList.enable(false);
				grupoCoberturaList.enable(false);
				
				$("#grupoCoberturaId").data("kendoMultiSelect").value(data.gCoberturaId);				
				
				($("#texto").data("kendoEditor")).value(data.texto);
			}
		});			
	}
    </script>
    <script id="errorTemplate" type="text/x-kendo-template">
  <div class="wrong-pass">
      <img src="../static/images/error-icon.png" width="60" height="60"/>
         <h3>#= title #</h3>
      <p>#= message #</p>
  </div>
</script>
<style>
                .demo-section p {
                    margin: 3px 0 3px;
                    line-height: 50px;
                }
                .demo-section .k-button {
                    width: 200px;
                }

                .k-notification {
                    border: 0;
                }
                /* Error template */
                .k-notification-error.k-group {
                    background: rgba(100%,0%,0%,.7);
                    color: #ffffff;
                }
                .wrong-pass {
                    width: 400px;
                    height: 50px;
                }
                .wrong-pass h3 {
                    font-size: 1em;
                    padding: 36px 14px 9px;
                }
                .wrong-pass img {
                    float: left;
                    margin: 30px 15px 30px 30px;
                }
                
            </style>

</head>
<body>
	<%
			// Permitimos el acceso si la session existe		
				if(session.getAttribute("login") == null){
				    response.sendRedirect("/CotizadorWeb");
				}
	%>
	<span id="notification" style="display:none;"></span>
	<div class="row crud-nav-bar">
		<!-- Button trigger modal -->
		<button class="btn btn-primary" data-toggle="modal" data-target="#add"
			id="addButton">
			<span class="glyphicon glyphicon-plus"></span> &nbsp; Nuevo
		</button>

		<!-- Modal -->
		<div class="modal fade" id="add" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<form id="formCrud">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Texto Cobertura
								PYMES</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">El texto
								cobertura se grabo con exito.</div>
							<div class="form-group">
								<table>
									<tr>
										<td>Producto:</td>
										<td style="width: 35%"><select name="productos"
											id="productos" class="datosPymes"></select></td>
										<td>Ramo:</td>
										<td><select id="ramoId"
											data-placeholder="Seleccione una opción...">
										</select></td>
									</tr>
									<tr>
										<td>Grupo Cobertura:</td>
										<td><select id="grupoCoberturaId" class="datosPymes"
											data-placeholder="Seleccione una opción...">
										</select></td>
										<td>Producto Ens. ID:</td>
										<td><input type="text" id="productoEnsID"></td>
									</tr>
									<tr>
										<td colspan="4">Texto:</td>
									</tr>
									<tr>
										<td colspan="4"><textarea id="texto" rows="30" cols="30"
												style="height: 500px; width: 100%; max-width: 600px;"
												class="form-control"></textarea></td>
									</tr>
								</table>
								<input type="hidden" class="form-control"
									id="textoGrupoCobertura">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" id="close-popup"
								data-dismiss="modal">Cerrar</button>
							<button type="button" class="btn btn-primary" id="save-record">Guardar
								Cambios</button>
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
			<div class="panel panel-primary">
				<div class="panel-body">
					<div class="input-group">
						<span class="input-group-addon">Filtro</span> <input id="filter"
							type="text" class="form-control"
							placeholder="Escriba la palabra a buscar...">
					</div>
					<table class="table table-striped table-bordered table-hover"
						id="dataTable">
						<thead>
							<tr>
								<th>Producto</th>
								<th>Ramo</th>
								<th>Grupo Cobertura</th>
								<th></th>
							</tr>
						</thead>
						<tbody id="dataTableContent" class="searchable">
							<div id="loading">
								<div class="loading-indicator">
									<img src="../static/images/ajax-loader.gif" /><br /> <br />
									<span id="loading-msg">Cargando...</span>
								</div>
							</div>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- Datatable -->

</body>
</html>
