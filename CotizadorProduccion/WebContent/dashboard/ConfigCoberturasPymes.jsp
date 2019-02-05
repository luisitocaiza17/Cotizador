<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="../static/css/sb-admin/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
<link href="../static/css/loading.css" rel="stylesheet">

<script src="../static/js/sb-admin/plugins/dataTables/jquery.dataTables.js"></script>
<script src="../static/js/util.js"></script>

<!-- script src="../static/js/bootstrap.min.js"></script> -->

<!-- KENDO -->
<link rel="stylesheet" href="../static/css/Kendo/kendo.common.min.css" />
<link rel="stylesheet" href="../static/css/Kendo/kendo.blueopal.min.css" />
<script src="../static/js/Kendo/kendo.all.min.js"></script>
<script src="../static/js/Kendo/kendo.web.min.js"></script>

<style type="text/css">
.tab_strip {
	height: 400px;
}
</style>

<title>Matriz de Coberturas PYMEs - Cotizador QBE</title>

<script>
	
	 $(function(){		 		 
	  	  $(".wrapper1").scroll(function(){
	  	    $(".wrapper2").scrollLeft($(".wrapper1").scrollLeft());
	  	  });
	  	  $(".wrapper2").scroll(function(){
	  	    $(".wrapper1").scrollLeft($(".wrapper2").scrollLeft());
	  	  });
	  	});
	 
	 	var tipoConsulta = "";
	 	var listaValorMinimo = "";
		var listaValorMaximo = "";
		var listaTasa = "";
		var configuracionCoberturaId = "";
		var coberturaPymesId = "";
		var grupoPorProductoId = "";
		var tipoDeclaracion = "";
		var origenValorLimiteAsegurado = "";
		var porcentajeLimiteAsegurado = "";
		var valorMaximoLimiteAsegurado = "";
		var origenValorLimiteCobertura = "";
		var porcentajeLimiteCobertura = "";
		var valorMaximoLimiteCobertura = "";
		var coberturaEnsMultiId = "";
		var coberturaEnsProgrId = "";
		var textoDeducible = "";
		var tipoTasa = "";
		var tipoConfiguracion = "";
		var tasa = "";
		var tasaEstructuras = "";
		var coberturaCopiaId = "";
		var incluyeEnProducto = "";
		var valorMaximo2 = "";
		var usaValorAdicional = "";
		var ordenPresentacion = "";
		var dependeValor = "";
		var arrCoberturaPymes = new Array();
		var arrPlanes = new Array();
		var listaTasasRiesgosDesde;
		var listaTasasRiesgosHasta;
		var listaTasasRiesgosTasas;
		var listaTasasReclamos;
		var listaTasasReclamosTasas;
		/*Texto deducible*/
		var arrTextoTipoDeducible = new Array();
		var auxTextoRepetido = "";
		var notification;
		
		function cargarCoberturas() {
			// Consultar listado de grupos productos
			$.ajax({
				url : '../PymeCoberturaController',
				data : {
					"tipoConsulta" : "encontrarTodos"
				},	
				async: false,
				type : 'post',
				datatype : 'json',
				success : function (data) {
					if (data.success == false){
						 notification.show({
	                           title: "ERROR",
	                           message: data.error
	                       }, "error");							
						return;
					}
					var listadoCoberturas = data.listadoCoberturaPymes;	 
					$("#coberturas").append("<option value=''>Seleccione una opcion</option>");
					$.each(listadoCoberturas, function (index) {
						$("#coberturas").append("<option value='" + listadoCoberturas[index].coberturaPymesId + "'>" + listadoCoberturas[index].nombre + "</option>");
					});		
					$("#cobertura_copiada").append("<option value=''>Seleccione una opcion</option>");
					$.each(listadoCoberturas, function (index) {
						$("#cobertura_copiada").append("<option value='" + listadoCoberturas[index].coberturaPymesId + "'>" + listadoCoberturas[index].nombre + "</option>");
					});		
				}
			});
		}
		
		
		function obtenerProductosPorGrupoProducto() {
			// Consultar listado de productos dentro de un grupos de productos
			$("#productos").empty();

			$.ajax({
					url : '../GrupoPorProductoController',

					data : {
						"tipoConsulta" : "encontrarTodosPorGrupo",
						"tipoObjeto" : "PYMES",
						"grupoProductoId" : "94"
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
								var inspeccionRequerida = listadoGrupos[index].inspeccionRequerida;
								$("#productos")
										.append(
												"<option value='"
														+ listadoGrupos[index].id
														+ "' inspeccionRequerida='"
														+ inspeccionRequerida
														+ "' >"
														+ listadoGrupos[index].nombre
														+ "</option>");
							});
					}
				});
		}
		
		
		
		$(document).ready(function(){	
			activarMenu("ConfigCoberturasPymes");
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
			cargar();
			
			$("#valor_riesgo_inicial").kendoNumericTextBox({
				 format : '#.000000',
				 decimals: 6
            });
			 $("#valor_riesgo_final").kendoNumericTextBox({
				 format : '#.000000',
				 decimals: 6
            });
			 $("#valor_riesgo_tasa").kendoNumericTextBox({
				 format : '#.000000',
				 decimals: 6
            });
			
			$("#orden_presentacion").kendoNumericTextBox({		    
				format: "#"
            });
			
			$("#valor_danio_tasa").kendoNumericTextBox({
				 format : '#.000000',
				 decimals: 6
            });
			 
			 $("#tieneDanio").kendoMultiSelect({
				dataTextField : "nombre",
				dataValueField : "id",
				animation : false,
				maxSelectedItems : 1
			});
			
			$("#porcentaje_limite_asegurado").kendoNumericTextBox({
                format: "#",
		        decimals: 0,
		        min: 0,
		        max: 100
            });			
			
			 $("#valor_maximo_limite_asegurado").kendoNumericTextBox({
                 format: "c",
                 min: 0,
                 decimals: 2
             });
			 
			 $("#tasa").kendoNumericTextBox({				 
				 format : '#.000000',
				 decimals: 6
             });
			 
			 $("#tasaEstructuras").kendoNumericTextBox({				 
				 format : '#.000000',
				 decimals: 6
             });
			 
			 $("#valor_cobertura_inicial").kendoNumericTextBox({
                 format: "c",
                 min: 0,
                 decimals: 2
             });
			 
			 $("#valor_cobertura_final").kendoNumericTextBox({
                 format: "c",
                 min: 0,
                 decimals: 2
             });
			 
			 $("#valor_maximo_limite_embarque").kendoNumericTextBox({
                 format: "c",
                 min: 0,
                 decimals: 2
             });
			 
			 $("#valorDeducible").kendoNumericTextBox({
                 format: "c",
                 min: 0,
                 decimals: 2
             });
			 
			 $("#valorPlan").kendoNumericTextBox({
                 format: "c",
                 min: 0,
                 decimals: 2
             });
			 
			 $("#valor_cobertura_tasa").kendoNumericTextBox({		 
	             format : '#.000000',
	             decimals: 6
             });	 
			 
			$("#tabstrip").kendoTabStrip({
		        animation:  {
		            open: {
		                effects: "fadeIn"
		            }
		        }
		    });
			
			/**para los combos de coberturas**/
			$("#cobertura_ens_multi_id2").kendoMultiSelect({
 				dataTextField : "nombre",
 				dataValueField : "id",
 				animation : false,
 				maxSelectedItems : 1
 			});	    	 
 	    	 
			listCoberturaMulti = $("#cobertura_ens_multi_id2").data("kendoMultiSelect");
    	 	
    	 	$("#cobertura_ens_progr_id2").kendoMultiSelect({
 				dataTextField : "nombre",
 				dataValueField : "id",
 				animation : false,
 				maxSelectedItems : 1
 			});	    	 
 	    	 
    	 	listCoberturaProgra = $("#cobertura_ens_progr_id2").data("kendoMultiSelect");
			
			var tabStrip = $("#tabstrip").kendoTabStrip().data("kendoTabStrip");
			tabStrip.enable(tabStrip.tabGroup.children().eq(0), false);
			tabStrip.enable(tabStrip.tabGroup.children().eq(1), false);
	        
			$("#configDinamico").change(function () {
				if ($("#configDinamico").is(":checked")) {
					var tabstrip = $("#tabstrip").kendoTabStrip().data("kendoTabStrip");
					tabstrip.enable(tabstrip.tabGroup.children().eq(0), true);
					tabstrip.enable(tabstrip.tabGroup.children().eq(1), false);
					tabstrip.select(0);
		        }
			});
			
			//para cambiar los id de ensurance al seleccionar el producto
			
			$("#coberturas").change(function () {
				var productoId = $("#productos option:selected").val() ? $(
				"#productos option:selected").val() : "";
				var coberturasId = $("#coberturas option:selected").val() ? $(
				"#coberturas option:selected").val() : "";
				//LLAMAMOS AL SERVLET PARA CARGAR LOS COMBOS DE IDS ENSURANCE coberturas productos
				listCoberturaMulti.dataSource.data([]);
				listCoberturaProgra.dataSource.data([]);
				cargarCombosEnsurane(productoId,coberturasId);				
			});
			
			$("#configCerrado").change(function () {
				if ($("#configCerrado").is(":checked")) {
					var tabstrip = $("#tabstrip").kendoTabStrip().data("kendoTabStrip");
					tabstrip.enable(tabstrip.tabGroup.children().eq(0), false);
					tabstrip.enable(tabstrip.tabGroup.children().eq(1), true);
					tabstrip.select(1);
		        }
			});
			
			$("#tasas_compuesta tr").remove();
			
			obtenerProductosPorGrupoProducto();
			
			cargarCoberturas();
			
			$("#loading").remove();	
			
			$('#tipo_tasa_simple').click(function() {
				$("#fila_tasa_simple").hide();
				$("#fila_tasa_compuesta").hide();
				$("#fila_tasa_copiada").hide();
				//limpiar los cuadros de texto
				$("#tasas_compuesta tr").remove();
				$("#cobertura_copiada").val("");
				
				if ($(this).is(':checked')) {
		        	$("#fila_tasa_simple").show();
		        }
		    });
			
			$('#tipo_tasa_compuesta').click(function() {
				$("#fila_tasa_simple").hide();
				$("#fila_tasa_compuesta").hide();
				$("#fila_tasa_copiada").hide();
				//limpiar los cuadros de texto
				$("#tasa").val("");
				$("#cobertura_copiada").val("");
				
		        if ($(this).is(':checked')) {
		        	$("#fila_tasa_compuesta").show();
		        }
		    });
			
			$('#tipo_tasa_sin_valor').click(function() {
				$("#fila_tasa_simple").hide();
				$("#fila_tasa_compuesta").hide();
				$("#fila_tasa_copiada").hide();
				//limpiar los cuadros de texto
				$("#tasa").val("");
				$("#tasas_compuesta tr").remove();
				$("#cobertura_copiada").val("");
		    });
			
			$('#usa_valor_adicional').click(function() {
				if ($(this).is(':checked')) {
		        	$("#filaValorAdicional").css("visibility", "visible");
		        }
				else{
					$("#filaValorAdicional").css("visibility", "hidden");
					($("#valor_maximo_limite_embarque").data("kendoNumericTextBox")).value(0);
				}
			});
			
			$('#tipo_tasa_copiada').click(function() {
				$("#fila_tasa_simple").hide();
				$("#fila_tasa_compuesta").hide();
				$("#fila_tasa_copiada").hide();
				//limpiar los cuadros de texto
				$("#tasa").val("");
				$("#tasas_compuesta tr").remove();
		        if ($(this).is(':checked')) {
		        	$("#fila_tasa_copiada").show();
		        }
		    });			
			 
			
			$("#save-record").bind({click: function(){				
				//bandera para verificar que un radioButton este seleccionado
				var bandera = 0;
				
				if($("#tipo_tasa_simple").is(":checked")){		
					$("#tasa").addClass("required");
					$("#tasa").attr("required", true);
					bandera = 1;
				}else if($('#tipo_tasa_compuesta').is(':checked')){
					$("#tasa").removeClass("required");
					$("#tasa").attr("required", false);
					bandera = 1;
				}else if($('#tipo_tasa_sin_valor').is(':checked')){
					$("#tasa").removeClass("required");
					$("#tasa").attr("required", false);
					bandera = 1;
				}else if($('#tipo_tasa_copiada').is(':checked')) {
					$("#tasa").removeClass("required");
					$("#tasa").attr("required", false);
					bandera = 1;
			    }
				
				var tipoCofiguracion=0;
				if ($("#configDinamico").is(":checked"))
					tipoCofiguracion=1;
				else if ($("#configCerrado").is(":checked"))
					tipoCofiguracion=2;
				
				var validator = $("#formCrud").kendoValidator().data("kendoValidator");
				$(".required").css("border", "1px solid #ccc");
				 if (validator.validate() === false) {
					 alert("Por favor ingrese el campo requerido");
				}else if(tipoCofiguracion === 0){
			    	  alert("Debe seleccionar el tipo de configuración");
			    }else if(bandera === 0){
			    	  alert("Por favor ingrese la tasa");
			    }else{					
						listaValorMinimo = "";
						listaValorMaximo = "";
						listaTasa = "";
						listaTasasRiesgosDesde="";
						listaTasasRiesgosHasta="";
						listaTasasRiesgosTasas="";
						listaTasasReclamos="";
						listaTasasReclamosTasas="";
						
						$("#tablaRiesgoVolcan  tbody tr").each(function(index) {
							$(this).children("td").each(
								function(index2) {
									switch (index2) {
									case 0:
										listaTasasRiesgosDesde = listaTasasRiesgosDesde + "," + $(this).children().val();
										break;
									case 1:
										listaTasasRiesgosHasta = listaTasasRiesgosHasta + "," + $(this).children().val();
										break;
									case 2:
										listaTasasRiesgosTasas = listaTasasRiesgosTasas + "," + $(this).children().val();
									}
								});
						});
						
						$("#tablaRiesgoReclamos tbody tr").each(function(index) {
							$(this).children("td").each(
								function(index2) {
									switch (index2) {
									case 0:
										listaTasasReclamos = listaTasasReclamos + "," + $(this).children().val();
										break;
									case 1:
										listaTasasReclamosTasas = listaTasasReclamosTasas + "," + $(this).children().val();
										break;									
									}
								});
						});
						
						$("#tasas_compuesta tbody tr").each(function(index) {
							$(this).children("td").each(
								function(index2) {
									switch (index2) {
									case 0:
										listaValorMinimo = listaValorMinimo + "," + $(this).children().val();
										break;
									case 1:
										if ($(this).children().val())
											listaValorMaximo = listaValorMaximo + "," + $(this).children().val();
										else
											listaValorMaximo = listaValorMaximo + "," + " ";
										break;
									case 2:
										if ($(this).children().val())
											listaTasa = listaTasa + "," + $(this).children().val();
										else
											listaTasa = listaTasa + "," + " ";
										break;
									}
								});
						});
						
						var contador = 0;
						$("#dataTablePlanes tbody tr").each(function(index) {
							var auxPlanId = "";
							var auxValor = "";	
							$(this).children("td").each(
								function(index2) {
									switch (index2) {
									case 0:																				
										auxPlanId = $(this).html();									
										break;
									case 2:										
										auxValor = $(this).html();									
										break;
									}
								});
							var detallePlan = {
									planId: auxPlanId,
									valor: auxValor,
                                };
							arrPlanes[contador] = detallePlan;
							contador++;
						});
						
						contador = 0;
						$("#dataTableTexto tr").each(function(index) {
							var auxTipoDeducibleCoberturaId = "";
							var auxTipoDeducibleId = "";
							var auxValor = "";	
							var auxTexto = "";
							var auxSeguridadAdecuada ="";
							$(this).children("td").each(
								function(index2) {
									switch (index2) {
									case 0:
										auxTipoDeducibleCoberturaId = $(this).html();
										break;
									case 1:																				
										auxTipoDeducibleId = $(this).html();									
										break;
									case 3:										
										auxValor = $(this).html();									
										break;
									case 5:										
										auxTexto = $(this).children().val();									
										break;
									case 4:										
										auxSeguridadAdecuada = $(this).html();									
										break;	
									}
								});
							var detalleTexto = {
									tipoDeducibleCoberturaId: auxTipoDeducibleCoberturaId,
									tipoDeducibleId: auxTipoDeducibleId,
                                    valor: auxValor,
                                    texto: auxTexto,
                                    seguridadAdecuada: auxSeguridadAdecuada
                                };
							
							arrTextoTipoDeducible[contador] = detalleTexto;
							contador++;
						});
						
						
						configuracionCoberturaId = $("#configuracionCoberturaId").val();
						
						if(configuracionCoberturaId=="" || configuracionCoberturaId== 0)
							tipoConsulta = "crear";
						else
							tipoConsulta = "editar";
						
						coberturaPymesId = $("#coberturas").val();
						grupoPorProductoId = $("#productos").val();
						tipoDeclaracion = $("#tipo_declaracion").val();
						origenValorLimiteAsegurado = $("#origen_valor_limite_asegurado").val();
						porcentajeLimiteAsegurado = $("#porcentaje_limite_asegurado").val();
						valorMaximoLimiteAsegurado = $("#valor_maximo_limite_asegurado").val();					
						/*coberturaEnsMultiId = $("#cobertura_ens_multi_id").val();
						coberturaEnsProgrId = $("#cobertura_ens_progr_id").val();*/
						
						coberturaEnsMultiId =$("#cobertura_ens_multi_id2 option:selected").val();
						coberturaEnsProgrId =$("#cobertura_ens_progr_id2 option:selected").val();
						origenValorLimiteCobertura = "9";
						porcentajeLimiteCobertura = "0";
						valorMaximoLimiteCobertura = "0";
						textoDeducible = $("#texto_deducible").val();
						
							
						if ($("#tipo_tasa_simple").is(":checked"))
							tipoTasa = 1;
						else if ($("#tipo_tasa_compuesta").is(":checked"))
							tipoTasa = 2;
						else if ($("#tipo_tasa_sin_valor").is(":checked"))
							tipoTasa = 3;
						else
							tipoTasa = 4;
						tasa = $("#tasa").val();
						tasaEstructuras = $("#tasaEstructuras").val();
						coberturaCopiaId = $("#cobertura_copiada").val();
						if($("#incluye_en_producto").is(":checked")){
							incluyeEnProducto = 1;
						}else{
							incluyeEnProducto = 0;
						}
						
						if($("#usa_valor_adicional").is(":checked")){
							usaValorAdicional = 1;
						}else{
							usaValorAdicional = 0;
						}
						valorMaximo2 = $("#valor_maximo_limite_embarque").val();
						
						dependeValor = $("#depende_valor").val();
						ordenPresentacion = $("#orden_presentacion").val();
												
						enviarDatos(tipoConsulta, configuracionCoberturaId, coberturaPymesId, grupoPorProductoId, tipoDeclaracion, origenValorLimiteAsegurado,
									porcentajeLimiteAsegurado, valorMaximoLimiteAsegurado, origenValorLimiteCobertura, porcentajeLimiteCobertura, valorMaximoLimiteCobertura,
									arrTextoTipoDeducible, tipoTasa, tasa, coberturaCopiaId, incluyeEnProducto, dependeValor, ordenPresentacion, 
									listaValorMinimo, listaValorMaximo, listaTasa, coberturaEnsMultiId, coberturaEnsProgrId, usaValorAdicional, 
									valorMaximo2, arrPlanes, tipoCofiguracion,tasaEstructuras,listaTasasRiesgosDesde,listaTasasRiesgosHasta
									,listaTasasRiesgosTasas,listaTasasReclamos,listaTasasReclamosTasas);
					}
				}
			
				
			});	
			
			$("#addButton").click(function(){
				$("#tasas_compuesta").children().remove();
				$("#dataTableTexto").children().remove();
				$("#dataTablePlanes").children().remove();
				arrTextoTipoDeducible = [];
				arrPlanes = [];
			});
		});
		
		function cargarCombosEnsurane(grupo, cobertura){
			
			$.ajax({
				url : '../PymeCoberturaConfiguradaController',
				data : {
					"tipoConsulta" : "cargarCoberturasTodas",
					"grupoProductoId":grupo,
					"coberturaId":cobertura
				},
				type : 'post',
				datatype : 'json',
				success : function (data) {
					if(data.success){						
						var ListaCoberturasPrograma = data.ListaCoberturasPrograma;
						listCoberturaProgra.dataSource.filter({});
						listCoberturaProgra.setDataSource(ListaCoberturasPrograma);
						
						var ListaCoberturasMulti = data.ListaCoberturasMulti;
						listCoberturaMulti.dataSource.filter({});
						listCoberturaMulti.setDataSource(ListaCoberturasMulti);
						if(data.observacion!='')						
							alert(data.observacion);						
					}else{
						alert(data.error);
					}
				}
			});
			
		}
		
		function agregarNuevoTexto(){
			
			
			var auxTipoDeducible = $("#tipoDeducible option:selected").val();
			var auxSeguridadAdecuada =  $("#seguridadAdecuada option:selected").val();
			var auxValor = $("#valorDeducible").val();
			var auxText = $("#tipoDeducible option:selected").text().replace("'VAL'", auxValor);
			var auxSeguridadAdecuadaText = $("#seguridadAdecuada option:selected").text();
			
			
			var aux= true;
			
			/* $("#dataTableTexto tr").each(function(index) {								
				
				$(this).children("td").each(
					function(index2) {
						switch (index2) {						
						case 1:																				
							if(auxTipoDeducible === $(this).html()){
								alert("No se puede repetir el deducible.");
								aux = false;
							}		
							break;
						}
					});				
			}); */
			
			
			if(aux){
				$("#dataTableTexto").append(
						'<tr>'+
						'<td style="display: none">'+
						'</td>'+
						'<td style="display: none">'+auxTipoDeducible+
						'</td >'+
						'<td style="display: none">'+
						'</td>'+
						'<td style="display: none">'+auxValor+
						'</td><td style="display: none">'+auxSeguridadAdecuada+'</td>'+
						'<td style="width: 50%"><input type="text" value="'+auxText+
						'"></input><label style="color: green; font-size: x-small;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+auxSeguridadAdecuadaText+'</label></td>'+
						'<td style="width: 20%">'+
						'<button type="button" class="btn btn-danger btn-xs eliminar-extra-btn">'+
						' <span class="glyphicon glyphicon glyphicon-remove"></span> Eliminar'+
						' </button></td>'+
						'</tr>');		
				$("#tipoDeducible").val("");
				($("#valorDeducible").data("kendoNumericTextBox")).value("");
			}
			
			
			$(".eliminar-extra-btn").bind(
					{
						click : function() {
							$(this).parent().parent().remove();							
					}
			});
		}
		
		function agregarNuevoPlan(){
			var auxPlan = $("#tipoPlan option:selected").val();
			var auxValor = $("#valorPlan").val();
			var auxText = $("#tipoPlan option:selected").text();
			
			
			var aux= true;
			
			$("#dataTablePlanes tr").each(function(index) {								
				
				$(this).children("td").each(
					function(index2) {
						switch (index2) {						
						case 1:																				
							if(auxPlan === $(this).html()){
								alert("No se puede repetir el plan.");
								aux = false;
							}		
							break;
						}
					});				
			});
			
			if(aux){
				$("#dataTablePlanes").append(
						'<tr>'+
						'<td style="display: none">'+auxPlan+'</td>'+
						'<td>'+auxText+'</td>'+
						'<td>'+auxValor+'</td>'+
						'<td style="width: 20%">'+
						'<button type="button" class="btn btn-danger btn-xs eliminar-extra-btn">'+
						' <span class="glyphicon glyphicon glyphicon-remove"></span> Eliminar'+
						' </button></td>'+
						'</tr>');		
				$("#tipoPlan").val("");
				($("#valorPlan").data("kendoNumericTextBox")).value("");
			}
			
			
			$(".eliminar-extra-btn").bind(
					{
						click : function() {
							$(this).parent().parent().remove();							
					}
			});
		}
		
		function cargar(){
			$("#configuracionCanal").children().remove();
			$.ajax({
				url : '../PymeConfiguracionCoberturaController',
				data : {
					"tipoConsulta" : "encontrarTodosVista"
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
					var listadoConfiguraciones = data.listadoConfiguraciones;
					$.each(listadoConfiguraciones, function (index){
						$("#configuracionCanal").append("<tr class='odd gradeX'>"+								
								"<td relation='coberturaNombre' >" + listadoConfiguraciones[index].coberturaNombre + "</td>"+								
								"<td relation='grupoNombre' >" + listadoConfiguraciones[index].grupoNombre + "</td>"+
								"<td relation='tipoDeclaracionNombre' >" + listadoConfiguraciones[index].tipoDeclaracionNombre + "</td>"+
								"<td relation='multi' >" + listadoConfiguraciones[index].idMulti + "</td>"+
								"<td relation='progra' >" + listadoConfiguraciones[index].idPrograma + "</td>"+
								"<td width='175px'>"+									
									" <button type='button'  name='actualizar-btn' data-toggle='modal' data-target='#add' class='btn btn-success btn-xs actualizar-btn' onclick='actualizar("+ listadoConfiguraciones[index].configuracionCoberturaId +")'>" +
  									" <span class='glyphicon glyphicon glyphicon-edit'></span> Actualizar" +
									" </button>" +									
									" <button type='button' class='btn btn-danger btn-xs eliminar-btn' onclick='eliminar("+ listadoConfiguraciones[index].configuracionCoberturaId +")'>" +
									  	"<span class='glyphicon glyphicon glyphicon-remove' id='delete-record'></span> Eliminar" +
									" </button>" +									
								"</td>" +
							"</tr>");		
					});				
					$("#loading").remove();	
					/*var grupoCoberturaArr = data.grupoCoberturaArr;
					$.each(grupoCoberturaArr, function (index){
						$("#configuracionCoberturaId").append("<option value='"+ grupoCoberturaArr[index].gCoberturaId +"'>"+ grupoCoberturaArr[index].gCoberturaNombre +"</option>");
					});
					var tipoCoberturaArr = data.tipoCoberturaArr;
					$.each(tipoCoberturaArr, function (index){
						$("#configuracionCoberturaId").append("<option value='"+ tipoCoberturaArr[index].tCoberturaId +"'>"+ tipoCoberturaArr[index].tCoberturaNombre +"</option>");
					});	*/
					
					var tipoDeducibleArr = data.tipoDeducibleArr;
					$("#tipoDeducible").append("<option value=''>Seleccione una opci&oacute;n</option>");
					$.each(tipoDeducibleArr, function (index) {
						$("#tipoDeducible").append("<option value='" + tipoDeducibleArr[index].tDeducibleId + "'>" + tipoDeducibleArr[index].tDeducibleTexto + "</option>");
					});
					
					var planArr = data.planArr;
					$("#tipoPlan").append("<option value=''>Seleccione una opci&oacute;n</option>");
					$.each(planArr, function (index) {
						$("#tipoPlan").append("<option value='" + planArr[index].tPlanId + "'>" + planArr[index].tPlanNombre + "</option>");
					});
				}
			});
		}
		
		function actualizar(configId){	
			listCoberturaMulti.dataSource.data([]);
			listCoberturaProgra.dataSource.data([]);
			
			$.ajax({
				url : '../PymeConfiguracionCoberturaController',
				async : false,
				data : {
					"configuracionCoberturaId" : configId,
				 	"tipoConsulta" : "encontrarPorId"
				},
				async : false,
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
					$("#configuracionCoberturaId").val(data.configuracionCoberturaId);
					$("#coberturas").val(data.coberturaPymesId);
					$("#productos").val(data.grupoPorProductoId);
					$("#tipo_declaracion").val(data.tipoDeclaracion);
					$("#origen_valor_limite_asegurado").val(data.origenValorLimiteAsegurado);
					($("#porcentaje_limite_asegurado").data("kendoNumericTextBox")).value(data.porcentajeLimiteAsegurado);					
					($("#valor_maximo_limite_asegurado").data("kendoNumericTextBox")).value(data.valorMaximoLimiteAsegurado);					
					$("#texto_deducible").val(data.textoDeducible);
					
					$("#cobertura_ens_multi_id").val(data.coberturaEnsMultiId);
					$("#cobertura_ens_progr_id").val(data.coberturaEnsProgrId);
					
					$("#fila_tasa_simple").hide();
					$("#fila_tasa_compuesta").hide();
					$("#fila_tasa_copiada").hide();
					
					if(data.tipoTasa == "1"){
						$("#tipo_tasa_simple").prop("checked", true);
						$("#fila_tasa_simple").show();
					}
					else
						$("#tipo_tasa_simple").prop("checked", false);

					if(data.tipoTasa == "2"){
						$("#tipo_tasa_compuesta").prop("checked", true);
						$("#fila_tasa_compuesta").show();
					}
					else
						$("#tipo_tasa_compuesta").prop("checked", false);
					
					if(data.tipoTasa == "3")
						$("#tipo_tasa_sin_valor").prop("checked", true);
					else
						$("#tipo_tasa_sin_valor").prop("checked", false);
					
					if(data.tipoConfiguracion == "1"){
						$("#configDinamico").prop("checked", true);
						$("#configCerrado").prop("checked", false);
						var tabstrip = $("#tabstrip").kendoTabStrip().data("kendoTabStrip");
						tabstrip.enable(tabstrip.tabGroup.children().eq(0), true);
						tabstrip.enable(tabstrip.tabGroup.children().eq(1), false);
						tabstrip.select(0);
					}
					else{
						$("#configCerrado").prop("checked", true);
						$("#configDinamico").prop("checked", false);
						var tabstrip = $("#tabstrip").kendoTabStrip().data("kendoTabStrip");
						tabstrip.enable(tabstrip.tabGroup.children().eq(0), false);
						tabstrip.enable(tabstrip.tabGroup.children().eq(1), true);
						tabstrip.select(1);
					}
					
					if(data.tipoTasa == "4"){
						$("#tipo_tasa_copiada").prop("checked", true);
						$("#fila_tasa_copiada").show();
					}
					else
						$("#tipo_tasa_copiada").prop("checked", false);
					
					($("#tasa").data("kendoNumericTextBox")).value(data.tasa);					
					($("#tasaEstructuras").data("kendoNumericTextBox")).value(data.tasaEstructuras);
					$("#cobertura_copiada").val(data.coberturaCopiaId);
					if(data.incluyeEnProducto == "1"){
						$("#incluye_en_producto").prop("checked", true);
					}else{
						$("#incluye_en_producto").prop("checked", false);
					}
					
					if(data.usaValorAdicional == "1"){
						$("#filaValorAdicional").css("visibility", "visible");
						($("#valor_maximo_limite_embarque").data("kendoNumericTextBox")).value(data.valorMaximo2);
						$("#usa_valor_adicional").prop("checked", true);
					}else{
						$("#usa_valor_adicional").prop("checked", false);
						($("#valor_maximo_limite_embarque").data("kendoNumericTextBox")).value(0);
					}
					
					
					($("#orden_presentacion").data("kendoNumericTextBox")).value(data.ordenPresentacion);
					$("#depende_valor").val(data.dependeValor);		
					
					$("#tasas_compuesta").children().remove();
										
					var tasas = data.tasas;
					
					$.each(data.tasas,function(index) {
						$("#tasas_compuesta").append(
										'<tr>'+
										'<td>'+
										'<input style="width: 100px" type="text"'+
										' id="valor_cobertura_inicial_'+tasas[index].coberturaTasaId+
										'" value='+tasas[index].valorCoberturaInicial+
										'></td>'+
										'<td>'+
										'<input style="width: 100px" type="text"'+
										'id="valor_cobertura_final_'+tasas[index].coberturaTasaId+
										'" value='+tasas[index].valorCoberturaFinal+
										'></td>'+
										'<td>'+
										'<input style="width: 100px" type="text"'+
										'id="valor_cobertura_tasa_'+tasas[index].coberturaTasaId+
										'" value='+tasas[index].tasa.toFixed(4)+
										'></td>'+
										'<td>'+
										'<button type="button" class="btn btn-danger btn-xs eliminar-extra-btn">'+
										' <span class="glyphicon glyphicon glyphicon-remove"></span> Eliminar'+
										' </button></td>'+
										'</tr>');
					});
					
					$("#tablaRiesgoVolcan").children().remove();
					var tasasVolcan = data.tasasRiesgoVolcanJSONArray;
					$.each(data.tasasRiesgoVolcanJSONArray,function(index) {
						$("#tablaRiesgoVolcan").append(
										'<tr>'+
										'<td>'+
										'<input style="width: 100px" type="text"'+
										' id="valor_riesgo_inicial_'+index+
										'" value='+tasasVolcan[index].valorInicial+
										'></td>'+
										'<td>'+
										'<input style="width: 100px" type="text"'+
										'id="valor_cobertura_final_'+index+
										'" value='+tasasVolcan[index].valorFinal+
										'></td>'+
										'<td>'+
										'<input style="width: 100px" type="text"'+
										'id="valor_cobertura_tasa_'+index+
										'" value='+tasasVolcan[index].tasa.toFixed(6)+
										'></td>'+
										'<td>'+
										'<button type="button" class="btn btn-danger btn-xs eliminar-extra-btn">'+
										' <span class="glyphicon glyphicon glyphicon-remove"></span> Eliminar'+
										' </button></td>'+
										'</tr>');
					});
					
					$("#tablaRiesgoReclamos").children().remove();
					var tasasReclamo = data.tasasRiesgoCostaJSONArray;
					$.each(data.tasasRiesgoCostaJSONArray,function(index) {
						$("#tablaRiesgoReclamos").append(
										'<tr>'+
										'<td>'+
										'<input style="width: 100px" type="text"'+
										' id="tieneDanio_'+index+
										'" value='+tasasReclamo[index].reclamo+
										'></td>'+
										'<td>'+
										'<input style="width: 100px" type="text"'+
										'id="valor_danio_tasa_'+index+
										'" value='+tasasReclamo[index].tasa.toFixed(6)+
										'></td>'+
										'<td>'+
										'<button type="button" class="btn btn-danger btn-xs eliminar-extra-btn">'+
										' <span class="glyphicon glyphicon glyphicon-remove"></span> Eliminar'+
										' </button></td>'+
										'</tr>');
					});
					
					
					$("#dataTableTexto").children().remove();
					/*Carga los textos del deducible*/
					var pymeTipoDeducibleArr = data.pymeTipoDeducibleArr;
					$.each(pymeTipoDeducibleArr,function(index) {
						$("#dataTableTexto").append(
										'<tr>'+
										'<td style="display: none" >'+pymeTipoDeducibleArr[index].pymeTipoDeducibleid+
										'</td>'+
										'<td style="display: none">'+pymeTipoDeducibleArr[index].tipoDeducibleId+
										'</td >'+
										'<td style="display: none">'+pymeTipoDeducibleArr[index].configuracionCoberturaId+
										'</td>'+
										'<td style="display: none">'+pymeTipoDeducibleArr[index].valorDeducible+
										'</td>'+
										'<td style="display: none">'+pymeTipoDeducibleArr[index].seguridadAdecuada+
										'</td>'+
										'<td style="width: 50%"><input type="text" value="'+pymeTipoDeducibleArr[index].texto+
										'"></input><label style="color: green; font-size: x-small;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+pymeTipoDeducibleArr[index].auxSeguridadAdecuadaText+'</label></td>'+										
										'<td style="width: 20%">'+
										'<button type="button" class="btn btn-danger btn-xs eliminar-extra-btn">'+
										' <span class="glyphicon glyphicon glyphicon-remove"></span> Eliminar'+
										' </button></td>'+
										'</tr>');
					});
					
					/*Carga los textos de los planes*/
					$("#dataTablePlanes").children().remove();
					var pymePlanesArr = data.pymePlanArr;
					$.each(pymePlanesArr,function(index) {
						$("#dataTablePlanes").append(
										'<tr>'+
										'<td style="display: none">'+pymePlanesArr[index].planId+
										'</td >'+
										'<td>'+pymePlanesArr[index].texto+
										'</td>'+
										'<td>'+pymePlanesArr[index].valorMaximo+
										'</td>'+
										'<td style="width: 20%">'+
										'<button type="button" class="btn btn-danger btn-xs eliminar-extra-btn">'+
										' <span class="glyphicon glyphicon glyphicon-remove"></span> Eliminar'+
										' </button></td>'+
										'</tr>');
					});
					
					/**CARGA LA LISTA DE COBERTURAS**/
					var ListaCoberturasPrograma = data.ListaCoberturasPrograma;
					listCoberturaProgra.dataSource.filter({});
					listCoberturaProgra.setDataSource(ListaCoberturasPrograma);
					
					var ListaCoberturasMulti = data.ListaCoberturasMulti;
					listCoberturaMulti.dataSource.filter({});
					listCoberturaMulti.setDataSource(ListaCoberturasMulti);
					
					$("#cobertura_ens_multi_id2").data("kendoMultiSelect").value(data.coberturaEnsMultiId);
					$("#cobertura_ens_progr_id2").data("kendoMultiSelect").value(data.coberturaEnsProgrId);
					
					if(data.observacion!='')						
						alert(data.observacion);
					
					$(".eliminar-extra-btn").bind(
							{
								click : function() {
									$(this).parent().parent().remove();
							}
					});
				}
			});
			
		}
		
		function eliminar(configId){			
			var r = confirm("Seguro que desea eliminar");
			if(r == true){
				var p = confirm("Por favor tomar en cuenta que todas las cotizaciones atadas a la cobertura se dañaran, aun asi desea continuar?");
				if(r == true){
					configuracionCoberturaId = configId;
				 	tipoConsulta = "eliminar";
				 	enviarDatos(tipoConsulta, configId, "", "", "", "",
							"", "", "", "", "", "", "",
							"", "", "", "", "", "", "", "", "", "","",""
							,"","","","","");	
				}
			}
			cargar();
		}
		
		function agregarNuevoTasa() {
			$("#valor_cobertura_inicial").css("border", "1px solid #ccc");
			$("#valor_cobertura_final").css("border", "1px solid #ccc");
			$("#valor_cobertura_tasa").css("border", "1px solid #ccc");			
			if($("#valor_cobertura_inicial").val() == "" || $("#valor_cobertura_final").val() == "" || $("#valor_cobertura_tasa").val() == "" ){
				$("#valor_cobertura_inicial").css("border", "1px solid red");
				$("#valor_cobertura_final").css("border", "1px solid red");
				$("#valor_cobertura_tasa").css("border", "1px solid red");
				alert("Por favor ingrese el campo requerido");
				$("#valor_cobertura_inicial").focus();
				return false;
			}
			
			var numeroTasa = parseInt($("#contadorTasas").val()) + parseInt(1);

			$("#tasas_compuesta")
					.append(
							'<tr>'+
							'<td>'+
							'<input style="width: 100px" type="text"'+
							' id="valor_cobertura_inicial_'+numeroTasa+
							'" value='+$("#valor_cobertura_inicial").val()+
							'></td>'+
							'<td>'+
							'<input style="width: 100px" type="text"'+
							'id="valor_cobertura_final_'+numeroTasa+
							'" value='+$("#valor_cobertura_final").val()+
							'></td>'+
							'<td>'+
							'<input style="width: 100px" type="text"'+
							'id="valor_cobertura_tasa_'+numeroTasa+
							'" value='+$("#valor_cobertura_tasa").val()+
							'></td>'+
							'<td>'+
							'<button type="button" class="btn btn-danger btn-xs eliminar-extra-btn">'+
							' <span class="glyphicon glyphicon glyphicon-remove"></span> Eliminar'+
							' </button></td>'+
							'</tr>');
			$("#contadorTasas").val(numeroTasa);
			($("#valor_cobertura_inicial").data("kendoNumericTextBox")).value("");
			($("#valor_cobertura_final").data("kendoNumericTextBox")).value("");
			($("#valor_cobertura_tasa").data("kendoNumericTextBox")).value("");
			
			$(".eliminar-extra-btn").bind(
				{
					click : function() {
						$(this).parent().parent().remove();
						numeroTasa = parseInt($("#contadorTasas").val()) - parseInt(1);
						$("#contadorTasas").val(numeroTasa);
				}
			});
		}
		
		function agregarNuevoTasaRiesgo() {
			$("#valor_riesgo_inicial").css("border", "1px solid #ccc");
			$("#valor_riesgo_final").css("border", "1px solid #ccc");
			$("#valor_riesgo_tasa").css("border", "1px solid #ccc");			
			if($("#valor_riesgo_inicial").val() == "" || $("#valor_riesgo_final").val() == "" || $("#valor_riesgo_tasa").val() == "" ){
				$("#valor_riesgo_inicial").css("border", "1px solid red");
				$("#valor_riesgo_final").css("border", "1px solid red");
				$("#valor_riesgo_tasa").css("border", "1px solid red");
				alert("Por favor ingrese el campo requerido");
				$("#valor_riesgo_inicial").focus();
				return false;
			}
			
			var numeroTasa = parseInt($("#contadorTasasRiesgo").val()) + parseInt(1);

			$("#tablaRiesgoVolcan")
					.append(
							'<tr>'+
							'<td>'+
							'<input style="width: 100px" type="text"'+
							' id="valor_riesgo_inicial_'+numeroTasa+
							'" value='+$("#valor_riesgo_inicial").val()+
							'></td>'+
							'<td>'+
							'<input style="width: 100px" type="text"'+
							'id="valor_riesgo_final_'+numeroTasa+
							'" value='+$("#valor_riesgo_final").val()+
							'></td>'+
							'<td>'+
							'<input style="width: 100px" type="text"'+
							'id="valor_riesgo_tasa_'+numeroTasa+
							'" value='+$("#valor_riesgo_tasa").val()+
							'></td>'+
							'<td>'+
							'<button type="button" class="btn btn-danger btn-xs eliminar-extra-btn">'+
							' <span class="glyphicon glyphicon glyphicon-remove"></span> Eliminar'+
							' </button></td>'+
							'</tr>');
			$("#contadorTasasRiesgo").val(numeroTasa);
			($("#valor_riesgo_inicial").data("kendoNumericTextBox")).value("");
			($("#valor_riesgo_final").data("kendoNumericTextBox")).value("");
			($("#valor_riesgo_tasa").data("kendoNumericTextBox")).value("");
			
			$(".eliminar-extra-btn").bind(
				{
					click : function() {
						$(this).parent().parent().remove();
						numeroTasa = parseInt($("#contadorTasasRiesgo").val()) - parseInt(1);
						$("#contadorTasasRiesgo").val(numeroTasa);
				}
			});
		}
		
		function agregarNuevoTasaDanio() {
			$("#tieneDanio").css("border", "1px solid #ccc");
			$("#valor_danio_tasa").css("border", "1px solid #ccc");
			if($("#tieneDanio").val() == "" || $("#valor_danio_tasa").val() == ""){
				$("#valor_danio_tasa").css("border", "1px solid red");
				$("#tieneDanio").css("border", "1px solid red");
				alert("Por favor ingrese el campo requerido");
				$("#valor_danio_tasa").focus();
				return false;
			}
			
			var numeroTasa = parseInt($("#contadorTasasReclamo").val()) + parseInt(1);

			$("#tablaRiesgoReclamos")
					.append(
							'<tr>'+
							'<td>'+
							'<input style="width: 100px" type="text"'+
							' id="tieneDanio_'+numeroTasa+
							'" value='+$("#tieneDanio option:selected").text()+
							'></td>'+
							'<td>'+
							'<input style="width: 100px" type="text"'+
							'id="valor_danio_tasa_'+numeroTasa+
							'" value='+$("#valor_danio_tasa").val()+
							'></td>'+
							'<td>'+
							'<button type="button" class="btn btn-danger btn-xs eliminar-extra-btn">'+
							' <span class="glyphicon glyphicon glyphicon-remove"></span> Eliminar'+
							' </button></td>'+
							'</tr>');
			$("#contadorTasasReclamo").val(numeroTasa);
			($("#valor_danio_tasa").data("kendoNumericTextBox")).value("");
			
			$(".eliminar-extra-btn").bind(
				{
					click : function() {
						$(this).parent().parent().remove();
						numeroTasa = parseInt($("#contadorTasasReclamo").val()) - parseInt(1);
						$("#contadorTasasReclamo").val(numeroTasa);
				}
			});
		}
		
		function enviarDatos(tipoConsulta, configuracionCoberturaId, coberturaPymesId, grupoPorProductoId, tipoDeclaracion, origenValorLimiteAsegurado,
				porcentajeLimiteAsegurado, valorMaximoLimiteAsegurado, origenValorLimiteCobertura, porcentajeLimiteCobertura, valorMaximoLimiteCobertura,
				textoDeducible, tipoTasa, tasa, coberturaCopiaId, incluyeEnProducto, dependeValor, ordenPresentacion, listaValorMinimo, listaValorMaximo, 
				listaTasa, coberturaEnsMultiId, coberturaEnsProgrId, usaValorAdicional, valorMaximo2, arrPlanes,tipoCofiguracion,tasaEstructura
				,listaTasasRiesgosDesde,listaTasasRiesgosHasta,listaTasasRiesgosTasas,listaTasasReclamos,listaTasasReclamosTasas){

			$.ajax({
				url : '../PymeConfiguracionCoberturaController',				
				data : {
					"tipoConsulta": tipoConsulta,
					"configuracionCoberturaId": configuracionCoberturaId,
					"coberturaPymesId": coberturaPymesId,
					"grupoPorProductoId": grupoPorProductoId,
					"tipoDeclaracion": tipoDeclaracion,
					"origenValorLimiteAsegurado": origenValorLimiteAsegurado,
					"porcentajeLimiteAsegurado": porcentajeLimiteAsegurado,
					"valorMaximoLimiteAsegurado": valorMaximoLimiteAsegurado,
					"origenValorLimiteCobertura": origenValorLimiteCobertura,
					"porcentajeLimiteCobertura": porcentajeLimiteCobertura,
					"valorMaximoLimiteCobertura": valorMaximoLimiteCobertura,					
					"tipoTasa": tipoTasa,
					"tasa": tasa,
					"coberturaCopiaId": coberturaCopiaId,
					"incluyeEnProducto": incluyeEnProducto,
					"dependeValor": dependeValor, 
					"ordenPresentacion":ordenPresentacion,
					"listaValorMinimo": listaValorMinimo,
					"listaValorMaximo": listaValorMaximo,
					"listaTasa": listaTasa,
					"arrTextoDeducible": JSON.stringify(textoDeducible),
					"coberturaEnsMultiId": coberturaEnsMultiId,
					"coberturaEnsProgrId": coberturaEnsProgrId,
					"usaValorAdicional" : usaValorAdicional,
					"valorMaximo2" : valorMaximo2,
					"arrPlanes" : JSON.stringify(arrPlanes),
					"tipoCofiguracion" : tipoCofiguracion,
					"tasaEstructuras" : tasaEstructuras,
					"listaTasasRiesgosDesde":listaTasasRiesgosDesde,
					"listaTasasRiesgosHasta":listaTasasRiesgosHasta,
					"listaTasasRiesgosTasas":listaTasasRiesgosTasas,
					"listaTasasReclamos":listaTasasReclamos,
					"listaTasasReclamosTasas":listaTasasReclamosTasas
				},
				async : false,
				type : 'POST',
				datatype : 'json',
				success : function(data) {
					if (data.success == false){
						 notification.show({
	                           title: "ERROR",
	                           message: data.error
	                       }, "error");							
						return;
					}
					$("#msgPopup").show();
					arrTextoTipoDeducible = [];
					arrPlanes = [];
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
		if (session.getAttribute("login") == null) {
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
			aria-labelledby="myModalLabel" aria-hidden="true" data-keyboard="false" data-backdrop="static">
			<div class="modal-dialog">
				<div class="modal-content" style="width: 800px;">
					<form id="formCrud">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">Matriz de
								Coberturas</h4>
						</div>
						<div class="modal-body">
							<div class="alert alert-success" id="msgPopup">La
								parametrización de la cobertura se grabo con éxito.</div>
							<input type="hidden" class="form-control"
								id="configuracionCoberturaId">
							<div class="panel panel-primary">
								<div class="panel-heading">Datos de la Cobertura</div>
								<div class="panel-body">
									<table>
										<tr>
											<td colspan="4" align="center">
												Configurar Producto Dinámico: <input type="radio" id="configDinamico" name="tipoConfiguracion">
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												Configurar Producto Cerrado: <input type="radio" id="configCerrado"  name="tipoConfiguracion">
											</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td>Producto:</td>
											<td><select name="productos" id="productos"
												class="datosGanadero" validationMessage="Campo requerido!!!"
												required></select></td>
											<td>Cobertura:</td>
											<td><select style="width: 180px" name="coberturas"
												id="coberturas" class="datosGanadero"
												validationMessage="Campo requerido!!!" required></select></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td>Tipo de Declaración:</td>
											<td><select name="tipo_declaracion"
												id="tipo_declaracion" class="datosGanadero"
												validationMessage="Campo requerido!!!" required>
													<option value=''>Seleccione una opción</option>
													<option value='1'>GENERAL</option>
													<option value='2'>POR DIRECCIÓN</option>
													<option value='3'>PRINCIPAL DE ESTRUCTURAS</option>
													<option value='4'>PRINCIPAL DE CONTENIDOS</option>
											</select></td>
											<td>Depende del Valor:</td>
											<td><select name="depende_valor" id="depende_valor"
												class="datosGanadero" validationMessage="Campo requerido!!!"
												required>
													<option value=''>Seleccione una opción</option>
													<option value='1'>De Ninguno</option>
													<option value='2'>De Estructuras</option>
													<option value='3'>De Muebles y Enseres</option>
													<option value='4'>De Maquinaria</option>
													<option value='5'>De Mercadería</option>
													<option value='6'>De Equipos y Herramientas</option>
													<option value='7'>De Insumos Médicos</option>
													<option value='8'>De Contenidos</option>
													<option value='9'>De P.L.O. de R.C.</option>
													<option value='10'>De Valor Asegurado</option>
											</select></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td>Orden de Presentación:</td>
											<td><input type="text" name="orden_presentacion"
												id="orden_presentacion" class="datosGanadero"></input></td>
											<td>Incluido en el Producto:</td>
											<td><input type="checkbox" id="incluye_en_producto"
												class="datosGanadero" name="incluye_en_producto"></td>
										</tr>
									</table>
								</div>
							</div>

							<div class="form-group">
								<div id="tabstrip">
									<ul>
										<li class="k-state-active">Límite Asegurado por Cobertura</li>
										<li>Planes</li>
										<li>Deducibles</li>
										<li>Tasas</li>
										<li>Tasas por Riesgos</li>
										<li>Datos Integración</li>
									</ul>
									<div class="tab_strip" style="height: 100%">
										<br />
										<div class="panel panel-primary">
											<div class="panel-body">
												<table style="width: 100%">
													<tr>
														<td>Origen del Valor:</td>
														<td colspan="3"><select
															name="origen_valor_limite_asegurado"
															id="origen_valor_limite_asegurado" required="required"
															validationMessage="Campo requerido!!!"
															class="datosGanadero">
																<option>Seleccione una opción</option>
																<option value='1'>SUMA VALORES</option>
																<option value='2'>SOLO ESTRUCTURA</option>
																<option value='3'>SOLO CONTENIDO</option>
																<option value='4'>SOLO MAQUINARIA</option>
																<option value='5'>SOLO MERCADERIA</option>
																<option value='6'>SOLO INSUMOS MÉDICOS-NO ELÉCTRICOS</option>
																<option value='7'>VALOR ASEGURADO DE INCENDIO</option>
																<option value='8'>VALOR ASEGURADO DE ROBO</option>
																<option value='9'>NO APLICA</option>
																<option value='10'>VALOR</option>
																<option value='11'>VALOR PLO(RESPONSABILIDAD
																	CIVIL)</option>
														</select></td>

													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td>Porcentaje del Origen:</td>
														<td style="width: 30%"><input type="text"
															name="porcentaje_limite_asegurado"
															id="porcentaje_limite_asegurado" class="datosGanadero"></input></td>
														<td>Valor Máximo:</td>
														<td style="width: 30%"><input type="text"
															name="valor_maximo_limite_asegurado"
															id="valor_maximo_limite_asegurado" class="datosGanadero"></input></td>
													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td>Es Transporte:</td>
														<td><input type="checkbox" id="usa_valor_adicional"
															name="usa_valor_adicional"></td>
													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr id="filaValorAdicional" style="visibility: hidden;">
														<td>Límite por embarque:</td>
														<td style="width: 30%;"><input type="text"
															name="valor_maximo_limite_embarque"
															id="valor_maximo_limite_embarque" class="datosGanadero"></input></td>
													</tr>
												</table>
											</div>
										</div>
									</div>
									<div class="tab_strip" style="height: 100%">
										<br />
										<div class="panel panel-primary">
											<div class="panel-body">
												<div style="height: 150px; overflow:scroll; !important; ">
													<table>
														<tr>
															<td style='width: 50%'>Plan:</td>
															<td style='width: 20%'>Monto Aseg.:</td>
															<td style='width: 20%'></td>
														</tr>
														<tr>
															<td style='width: 50%'><select name="tipoPlan"
																id="tipoPlan">
															</select></td>
															<td style='width: 20%'>
																<input style="width: 100px"	type="text" id="valorPlan">
															</td>
															<td style='width: 20%'>
																<button type="button"
																	class="btn btn-primary btn-xs actualizar-btn"
																	id="btnAnadirPlan" onclick="agregarNuevoPlan();">
																	<span class="glyphicon glyphicon glyphicon-plus"></span>A&ntilde;adir
																	Plan
																</button>
															</td>														
														</tr>
													</table>
													<table
														class="table table-striped table-bordered table-hover"
														style="font-size: x-small;" id="dataTablePlanes">
													</table>
												</div>
											</div>
										</div>
									</div>
									<div class="tab_strip" style="height: 100%">
										<br />
										<div class="panel panel-primary">
											<div class="panel-body">
												<table>
													<tr>
														<td style='width: 50%'>Deducibles:</td>
														<td style='width: 20%'>Tipo Variable:</td>
														<td style='width: 20%'>Valor:</td>
														<td style='width: 20%'></td>
													</tr>
													
													
													<tr>
														<td style='width: 50%'><select name="tipoDeducible"
															id="tipoDeducible">
														</select></td>
														
														<td style='width: 50%'><select name="seguridadAdecuada"
															id="seguridadAdecuada">
															<option value='-1'>Ninguno</option>
															<option value='1'>Si Seguridad Adecuada</option>
															<option value='0'>No Seguridad Adecuada</option>
														</select></td>
														<td style='width: 20%'>
															<input style="width: 100px"	type="text" id="valorDeducible">
														</td>
														<td style='width: 20%'>
															<button type="button"
																class="btn btn-primary btn-xs actualizar-btn"
																id="btnAnadirTexto" onclick="agregarNuevoTexto();">
																<span class="glyphicon glyphicon glyphicon-plus"></span>A&ntilde;adir
																Texto
															</button>
														</td>														
													</tr>
												</table>
												<table
													class="table table-striped table-bordered table-hover"
													style="font-size: x-small;" id="dataTableTexto">
												</table>
											</div>
										</div>
									</div>
									<div class="tab_strip" style="height: 100%">
										<br />
										<div class="panel panel-primary">
											<div class="panel-body">
												<div class="status"></div>
												<table style="width: 100%">
													<tr>
														<td>Tasa Simple&nbsp;<input type="radio"
															name="tipo_tasa" id="tipo_tasa_simple"
															class="datosGanadero" required="required" required></input></td>
														<td>Tasa Compuesta&nbsp;<input type="radio"
															name="tipo_tasa" id="tipo_tasa_compuesta"
															class="datosGanadero" required="required" required></input></td>
														<td>Sin Costo&nbsp;<input type="radio"
															name="tipo_tasa" id="tipo_tasa_sin_valor"
															class="datosGanadero" required="required" required></input></td>
														<td>Tasa Copiada&nbsp;<input type="radio"
															name="tipo_tasa" id="tipo_tasa_copiada"
															class="datosGanadero" required="required" required></input></td>
													</tr>
													<tr id="fila_tasa_simple" style="display: none">
														<td>Tasa:</td>
														<td colspan="7"><input type="text" name="tasa"
															id="tasa" validationMessage="Campo requerido!!!"></input></td>
													</tr>
													<tr>
														<td>Tasa solo Estructuras:</td>
														<td colspan="7"><input type="text" name="tasaEstructuras"
															id="tasaEstructuras"></input></td>
													</tr>
													<tr id="fila_tasa_compuesta" style="display: none">
														<td colspan="8"><input type="hidden" value="0"
															id="contadorTasas"><br>
															<table width="100%">
																<tr>
																	<td style="width: 15%; text-align: center;">
																		Desde:</td>
																	<td style="width: 15%; text-align: center;">
																		Hasta:</td>
																	<td style="width: 20%; text-align: center;">Tasa:
																	</td>
																	<td style="width: 50%; text-align: center;"></td>
																</tr>
																<tr>
																	<td><input style="width: 100px" type="text"
																		name="valor_cobertura_inicial"
																		id="valor_cobertura_inicial"></td>
																	<td><input style="width: 100px" type="text"
																		name="valor_cobertura_final"
																		id="valor_cobertura_final"></td>
																	<td><input style="width: 100px" type="text"
																		name="valor_cobertura_tasa" id="valor_cobertura_tasa">
																	</td>
																	<td>
																		<button type="button"
																			class="btn btn-primary btn-xs actualizar-btn"
																			id="btnAnadirTasa" onclick="agregarNuevoTasa();">
																			<span class="glyphicon glyphicon glyphicon-plus"></span>A&ntilde;adir
																			Tasa
																		</button>
																	</td>
																</tr>
															</table>
															<table id="tasas_compuesta">

															</table></td>
													</tr>
													<tr id="fila_tasa_copiada" style="display: none">
														<td>Copiar de:</td>
														<td colspan="3"><select name="cobertura_copiada"
															id="cobertura_copiada">
														</select></td>
													</tr>
												</table>
											</div>
										</div>
									</div>
									
									<!-- Tab para tasas por riesgo -->
									<div class="tab_strip" style="height: 100%">
										<div class="panel panel-primary">
											<div class="panel-body">
												<div class="row">
													 <div class="col-md-11">
													 	<div class="panel panel-primary">
															<div class="panel-body">
																<h5>Riesgo Volcan</h5>
																<div style="height: 150px; overflow:scroll; !important; ">
																	<input type="hidden" value="0"	id="contadorTasasRiesgo"><br>
																	<table width="100%">
																		<tr>
																			<td style="width: 25%; text-align: center;">
																				Desde:</td>
																			<td style="width: 25%; text-align: center;">
																				Hasta:</td>
																			<td style="width: 25%; text-align: center;">Tasa:
																			</td>
																			<td style="width: 25%; text-align: center;"></td>
																		</tr>
																		<tr>
																			<td><input style="width: 150px" type="text"
																				name="valor_riesgo_inicial"
																				id="valor_riesgo_inicial"></td>
																			<td><input style="width: 150px" type="text"
																				name="valor_riesgo_final"
																				id="valor_riesgo_final"></td>
																			<td><input style="width: 150px" type="text"
																				name="valor_riesgo_tasa" id="valor_riesgo_tasa">
																			</td>
																			<td>
																				<button type="button" style="width: 100px"
																					class="btn btn-primary btn-xs actualizar-btn"
																					id="btnAnadirTasaRiesgo" onclick="agregarNuevoTasaRiesgo();">
																					<span class="glyphicon glyphicon glyphicon-plus"></span>A&ntilde;adir
																					Tasa
																				</button>
																			</td>
																		</tr>
																	</table>
																	<table width="100%" class="table table-striped" id="tablaRiesgoVolcan">
																																				
																	</table>
																</div>
															</div>
														</div>
													 </div>
													 <div class="col-md-11">
													 	<div class="panel panel-primary">
															<div class="panel-body">
															<input type="hidden" value="0"
																id="contadorTasasReclamo">
																<h5>Riesgo Costa</h5>
																<div style="height: 150px; overflow:scroll; !important; ">
																	<table width="100%" >
																		<tr>
																			<td style="width: 25%; text-align: center;">
																				Tiene Reclamo:</td>
																			<td style="width: 25%; text-align: center;">Tasa:
																			</td>
																			<td style="width: 25%; text-align: center;"></td>
																			<td style="width: 25%; text-align: center;"></td>
																		</tr>
																		<tr>
																			<td>
																				<select name="tieneDanio" style="width: 200px"
																					id="tieneDanio" class="datosGanadero"
																					validationMessage="Campo requerido!!!" required>
																						<option value=''>Seleccione una opción</option>
																						<option value='1'>SI</option>
																						<option value='2'>NO</option>
																				</select>
																			</td>
																			<td><input style="width: 150px" type="text"
																				name="valor_danio_tasa" id="valor_danio_tasa">
																			</td>
																			<td>
																				<button type="button" style="width: 150px"
																					class="btn btn-primary btn-xs actualizar-btn"
																					id="btnAnadirTasaDanio" onclick="agregarNuevoTasaDanio();">
																					<span class="glyphicon glyphicon glyphicon-plus"></span>A&ntilde;adir
																					Tasa
																				</button>
																			</td>
																		</tr>
																	</table>
																	<table width="100%" class="table table-striped" id="tablaRiesgoReclamos">
																																			
																	</table>
																</div>
															</div>
														</div>
													 </div>
												</div>											
											</div>
										</div>
									</div>
									<!-- fin tab de riesgos -->
									<div class="tab_strip" style="height: 100%">
										<br />
										<div class="panel panel-primary">
											<div class="panel-body">
												<div class="status"></div>
												<table style="width: 100%">
													<tr>
														<td>Cobertura Id para Multiriesgo:</td>
														<td><input style="width: 200px" type="hidden"
																		name="cobertura_ens_multi_id" id="cobertura_ens_multi_id"></td>
														<td>
															<select id="cobertura_ens_multi_id2" style="width: 450px" data-placeholder="Seleccione una opción..."></select>
														</td>
													</tr>
													
													<tr>
														<td>
														<br/>
														</td>
													</tr>
													<tr>
														<td>Cobertura Id para Programa:</td>
														<td><input style="width: 200px" type="hidden"
																		name="cobertura_ens_progr_id" id="cobertura_ens_progr_id"></td>
														<td>
															<select id="cobertura_ens_progr_id2" style="width: 450px" data-placeholder="Seleccione una opción..."></select>
														</td>
													</tr>
												</table>
											</div>
										</div>
									</div>
								</div>
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
								<th>Cobertura</th>
								<th>Producto</th>
								<th>Tipo</th>
								<th>Id Multiriesgo</th>
								<th>Id Programa</th>
								<th></th>
							</tr>
						</thead>
						<tbody id="configuracionCanal" class="searchable">
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
	</div>
	<!-- Datatable -->

</body>
</html>