var tipoObjeto = document.getElementById("tipoObjetoMetodos").getAttribute(
"tipoObjetoValor");

var configCobertasPorProducto;
var configAsistenciasPorProducto;
var parametroGrupoProducto;
var usuarioRol;
var cotizacionEstado="";
//riesgo
var existeRiesgoCosta;
var existeRiesgoVolcan;

function formatDollar(num) {
	var numero=parseFloat(num);
	var p = numero.toFixed(2).split(".");
	return "$" + p[0].split("").reverse().reduce(function(acc, numero, i, orig) {
		return numero + (i && !(i % 3) ? "," : "") + acc;
	}, "") + "." + p[1];
}

function onSelect(e) {
	if ($(e.item).find("> .k-link").text().trim() == "Coberturas") {
		if ($("#total_valor_asegurado").val() != "" && $("#total_valor_asegurado").val() > 0) {
			var numerictextbox1 = $("#valor_estructuras").data("kendoNumericTextBox");
			numerictextbox1.enable(false);
			
			var numerictextbox2 = $("#valor_muebles_enseres").data("kendoNumericTextBox");
			numerictextbox2.enable(false);
			
			var numerictextbox3 = $("#valor_maquinarias").data("kendoNumericTextBox");
			numerictextbox3.enable(false);
			
			var numerictextbox4 = $("#valor_mercaderia").data("kendoNumericTextBox");
			numerictextbox4.enable(false);
			
			var numerictextbox5 = $("#valor_herramientas").data("kendoNumericTextBox");
			numerictextbox5.enable(false);
			
			var numerictextbox6 = $("#valor_insumos_medicos").data("kendoNumericTextBox");
			numerictextbox6.enable(false);
			
			var numerictextbox7 = $("#valor_contenidos").data("kendoNumericTextBox");
			numerictextbox7.enable(false);
			
			if (cotizacionEstado == "Pendiente Asignar Inspector" || cotizacionEstado == "Pendiente de Inspeccion" || cotizacionEstado == "Pendiente" || cotizacionEstado == "Emitido")
				$("#ingresarNuevoValores").css("visibility", "hidden");
			else
				$("#ingresarNuevoValores").css("visibility", "visible");
		}
	}
}

function limpiarCoberturas() {
	if (confirm("El cambio de valores afecta a las coberturas ya establecidas. Desea continuar?")) {
		var tabStrip = $("#tabstrip").kendoTabStrip().data("kendoTabStrip");
		tabStrip.enable(tabStrip.tabGroup.children().eq(2), false);
		$("#detalle_cobertura_direccion tr").remove();
		$("#detalle_cobertura_general tr").remove();
		$("#detalle_cobertura_adicionales tr").remove();
		$("#deducibles_general tr").remove();
		
		//limpio los checks de las coberturas por dirección
		$(".coberturaPorDireccion").each(function() {
			$(this).prop( "checked", false );
		});
		
		//limpio los checks de las coberturas generales
		$(".coberturaGenerales").each(function() {
			$(this).prop( "checked", false );
		});
		
		//limpio los checks de las coberturas adicionales
		$(".coberturaAdicionales").each(function() {
			$(this).prop( "checked", false );
		});
		
		//riesgo
		var riesgoSierra=$("#distanciaRiesgo").data("kendoNumericTextBox");
		riesgoSierra.enable(true);
		var riesgoCostaSi=$("#riesgoDanio_si");
		riesgoCostaSi.removeAttr('disabled');
		var riesgoCostaNo=$("#riesgoDanio_no");
		riesgoCostaNo.removeAttr('disabled');
		
		
		var numerictextbox1 = $("#valor_estructuras").data("kendoNumericTextBox");
		numerictextbox1.enable(true);
		var numerictextbox2 = $("#valor_muebles_enseres").data("kendoNumericTextBox");
		numerictextbox2.enable(true);
		var numerictextbox3 = $("#valor_maquinarias").data("kendoNumericTextBox");
		numerictextbox3.enable(true);
		var numerictextbox4 = $("#valor_mercaderia").data("kendoNumericTextBox");
		numerictextbox4.enable(true);
		var numerictextbox5 = $("#valor_herramientas").data("kendoNumericTextBox");
		numerictextbox5.enable(true);
		var numerictextbox6 = $("#valor_insumos_medicos").data("kendoNumericTextBox");
		numerictextbox6.enable(true);
		var numerictextbox7 = $("#valor_contenidos").data("kendoNumericTextBox");
		numerictextbox7.enable(true);
		$("#ingresarNuevoValores").css("visibility", "hidden");
	}
}

function obtenerConfiguracionesCoberturaProducto(productoID) {
	$("#coberturas_por_direccion tr").remove();
	$("#coberturas_generales tr").remove();
	$("#coberturas_adicionales tr").remove();
	$("#coberturas_por_direccion")
	.append('<tr><th>Cobertura</th><th>Ramo</th></tr>');
	$("#coberturas_generales")
	.append('<tr><th>Cobertura</th><th>Ramo</th></tr>');
	$("#coberturas_adicionales")
	.append('<tr><th>Cobertura</th><th>Ramo</th></tr>');
	
	$.ajax({
		url : '../PymeConfiguracionCoberturaController',
		data : {
			"tipoConsulta" : "encontrarConfiguracionesPorProductoId",
			"grupoPorProductoId" : productoID,
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			configCobertasPorProducto = data.listadoConfiguraciones;
			$.each(configCobertasPorProducto, function(index) {
				//Si son coberturas por dirección
				if (configCobertasPorProducto[index].tipoDeclaracion == "2") {
					
					if (configCobertasPorProducto[index].incluyeEnProducto == 1) {
						$("#coberturas_por_direccion")
						.append(
								'<tr><td style="color: orange;"><input type="checkbox"  id="cobertura_'
								+ configCobertasPorProducto[index].configuracionCoberturaId
								+ '" class="datosPymes dependeDe_'
								+ configCobertasPorProducto[index].dependeValor
								+ '" checked disabled>'
								+ configCobertasPorProducto[index].coberturaNombre
								+ '</td><td style="color: orange;">('+configCobertasPorProducto[index].ramoNombre+')</td></tr>');
					} else {
						$("#coberturas_por_direccion")
						.append(
								'<tr><td style="color: green;"><input type="checkbox"  id="cobertura_'
								+ configCobertasPorProducto[index].configuracionCoberturaId
								+ '" class="datosPymes coberturaPorDireccion" onclick=crearCobertura('
								+ configCobertasPorProducto[index].configuracionCoberturaId
								+ ',"direccion"'
								+ ')>'
								+ configCobertasPorProducto[index].coberturaNombre
								+ '</td><td style="color: green;">('+configCobertasPorProducto[index].ramoNombre+')</td></tr>');
						}

				} else {
					if (configCobertasPorProducto[index].tipoCoberturaId == 1) {
						
						if (configCobertasPorProducto[index].incluyeEnProducto == 1) {
							$("#coberturas_generales")
							.append(
									'<tr><td style="color: orange;"><input type="checkbox"  id="cobertura_'
									+ configCobertasPorProducto[index].configuracionCoberturaId
									+ '" class="datosPymes dependeDe_'
									+ configCobertasPorProducto[index].dependeValor
									+ '" checked disabled>'
									+ configCobertasPorProducto[index].coberturaNombre
									+ '</td><td style="color: orange;">('+configCobertasPorProducto[index].ramoNombre+')</td></tr>');
						} else {
							$("#coberturas_generales")
							.append(
									'<tr><td style="color: green;"><input type="checkbox"  id="cobertura_'
									+ configCobertasPorProducto[index].configuracionCoberturaId
									+ '" class="datosPymes coberturaGenerales dependeDe_'
									+ configCobertasPorProducto[index].dependeValor
									+ '" onclick=crearCoberturaGeneral('
									+ configCobertasPorProducto[index].configuracionCoberturaId
									+ ',"general"'
									+ ')>'
									+ configCobertasPorProducto[index].coberturaNombre
									+ '</td><td style="color: green;">('+configCobertasPorProducto[index].ramoNombre+')</td></tr>');
						}
					} else {
						
						if (configCobertasPorProducto[index].tipoTasa == 3) {
							
							if (configCobertasPorProducto[index].incluyeEnProducto == 1) {
								$("#coberturas_adicionales")
								.append(
										'<tr><td style="color: blue;"><input type="checkbox"  id="cobertura_'
										+ configCobertasPorProducto[index].configuracionCoberturaId
										+ '" class="datosPymes dependeDe_'
										+ configCobertasPorProducto[index].dependeValor
										+ '" checked disabled>'
										+ configCobertasPorProducto[index].coberturaNombre
										+ '</td><td style="color: blue;">('+configCobertasPorProducto[index].ramoNombre+')</td></tr>');
							} else {
								$("#coberturas_adicionales")
								.append(
										'<tr><td style="color: green;"><input type="checkbox"  id="cobertura_'
										+ configCobertasPorProducto[index].configuracionCoberturaId
										+ '" class="datosPymes coberturaAdicionales dependeDe_'
										+ configCobertasPorProducto[index].dependeValor
										+ '" onclick=crearCobertura('
										+ configCobertasPorProducto[index].configuracionCoberturaId
										+ ',"adicionales"'
										+ ')>'
										+ configCobertasPorProducto[index].coberturaNombre
										+ '</td><td style="color: green;">('+configCobertasPorProducto[index].ramoNombre+')</td></tr>');
							}
						}
						else{
							$("#coberturas_adicionales")
							.append(
									'<tr><td style="color: green;"><input type="checkbox"  id="cobertura_'
									+ configCobertasPorProducto[index].configuracionCoberturaId
									+ '" class="datosPymes coberturaAdicionales dependeDe_'
									+ configCobertasPorProducto[index].dependeValor
									+ '" onclick=crearCobertura('
									+ configCobertasPorProducto[index].configuracionCoberturaId
									+ ',"adicionales"'
									+ ')>'
									+ configCobertasPorProducto[index].coberturaNombre
									+ '</td><td style="color: green;">('+configCobertasPorProducto[index].ramoNombre+')</td></tr>');
						}							
					}

				}
			});
			//Desactivo los check de PLO RC
			$(".dependeDe_9").each(function() {
				$(this).prop("disabled", true);
			});
		}
	});
}
function crearCobertura(configuracionCoberturaId, tipo) {
	// Filtro en el array de cobertura el id seleccionado
	var filterResult = $(configCobertasPorProducto).filter(function(idx) {
		return configCobertasPorProducto[idx].configuracionCoberturaId === configuracionCoberturaId;
	});
	if (filterResult.length > 0) {
		if ($("#cobertura_" + configuracionCoberturaId).is(':checked')) {
			var valorMaximo = determinarValorMaximo(configuracionCoberturaId);
			var tasa = determinarTasa(configuracionCoberturaId);
			var clase = filterResult[0].incluyeEnProducto == "1" ? "predeterminado" : "normal";
			var formacionFila='<tr class="'
								+ clase
								+ '" id="fila_config_cobertura_'
								+ configuracionCoberturaId
								+ '">'
								+ '<td style="width: 50%">'
								+ '<b>'
								+ filterResult[0].coberturaNombre
								+ '</b>'
								+ '<div class="panel panel-primary">'
								+ '<table style="width: 100%" cellpadding="3">';
			
			if(filterResult[0].usaValorAdicional == "0"){ //Esto quiere decir que no es transporte
				formacionFila=formacionFila+'<tr>'
				+ '<td><label class="porcentajeTasasPymes" id="lbl_tasa_sugerida_'
				+ configuracionCoberturaId
				+ '">Tasa Sugerida:</label></td>'
				+ '<td><input type="text" style="width: 70px" disabled id="tasa_sugerida_'
				+ configuracionCoberturaId
				+ '" class="datosPymes porcentajeTasasPymes"></td>'
				+ '<td><label class="porcentajeTasasPymes" id="lbl_tasa_ingresada_'
				+ configuracionCoberturaId
				+ '">Tasa Ingresada:</label></td>'
				+ '<td><input type="text" style="width: 70px" id="tasa_ingresada_'
				+ configuracionCoberturaId
				+ '" class="datosPymes porcentajeTasasPymes" onchange="calcularPrimaNeta('
				+ configuracionCoberturaId
				+ ',' + valorMaximo + ')"></td>'
				+ '<td>Monto Asegurado:</td>'
				+ '<td><input type="text" style="width: 100px" id="monto_asegurado_'
				+ configuracionCoberturaId;
				if(tipo=="direccion")
					formacionFila=formacionFila + '" class="datosPymes datosMontoAseguradoDir" onchange="calcularPrimaNeta(';
				else
					formacionFila=formacionFila + '" class="datosPymes datosMontoAseguradoGen" onchange="calcularPrimaNeta(';
						
				formacionFila=formacionFila + configuracionCoberturaId
				+ ',' + valorMaximo + ')"></td>'
				+ '<td>Prima Neta:</td>'
				+ '<input type="hidden" class="cobertura" id="codigoCobertura_'+configuracionCoberturaId+'" value="'+configuracionCoberturaId+'" >'
				+ '<input type="hidden" id="nombreCobertura_'+configuracionCoberturaId+'" value="'+filterResult[0].coberturaNombre+'">'
				+ '<td><input type="text" style="width: 100px" id="prima_neta_'
				+ configuracionCoberturaId;
				if(tipo=="direccion")
					formacionFila=formacionFila + '" class="datosPymes primaNetaCobertura" disabled></td>';
				else
					formacionFila=formacionFila + '" class="datosPymes" disabled></td>';	
				formacionFila=formacionFila + '</tr>';
			}
			else{
				formacionFila=formacionFila+'<tr>'
				+ '<td><label class="porcentajeTasasPymes" id="lbl_tasa_sugerida_'
				+ configuracionCoberturaId
				+ '">Tasa Sugerida:</label></td>'
				+ '<td><input type="text" style="width: 70px" disabled id="tasa_sugerida_'
				+ configuracionCoberturaId
				+ '" class="datosPymes porcentajeTasasPymes"></td>'
				+ '<td><label class="porcentajeTasasPymes" id="lbl_tasa_ingresada_'
				+ configuracionCoberturaId
				+ '">Tasa Ingresada:</label></td>'
				+ '<td><input type="text" style="width: 70px" id="tasa_ingresada_'
				+ configuracionCoberturaId
				+ '" class="datosPymes porcentajeTasasPymes" onchange="calcularPrimaNeta('
				+ configuracionCoberturaId
				+ ',' + valorMaximo + ')"></td>'
				+ '<td></td>'
				+ '<td></td>'
				+ '<td></td>'
				+ '<td></td>'
				+ '</tr>'
				+ '<tr>'
				+ '<td>L&iacute;mite por Embarque:</td>'
				+ '<td><input type="text" style="width: 70px" id="valor_adicional_'
				+ configuracionCoberturaId
				+ '" class="datosPymes porcentajeTasasPymes" onchange="validarMaximoEmbarque('
				+ configuracionCoberturaId
				+ ',' + filterResult[0].valorMaximo2 + ')"></td>'
				+ '<td>Movilizaci&oacute;n Anual:</td>'
				+ '<td><input type="text" style="width: 100px" id="monto_asegurado_'
				+ configuracionCoberturaId;
				if(tipo=="direccion")
					formacionFila=formacionFila + '" class="datosPymes datosMontoAseguradoDir" onchange="calcularPrimaNeta(';
				else
					formacionFila=formacionFila + '" class="datosPymes datosMontoAseguradoGen" onchange="calcularPrimaNeta(';
				
				formacionFila=formacionFila + configuracionCoberturaId
				+ ',' + valorMaximo + ')"></td>'
				+ '<td>Prima Neta:</td>'
				+ '<input type="hidden" class="cobertura" id="codigoCobertura_'+configuracionCoberturaId+'" value="'+configuracionCoberturaId+'" >'
				+ '<input type="hidden" id="nombreCobertura_'+configuracionCoberturaId+'" value="'+filterResult[0].coberturaNombre+'">'
				+ '<td><input type="text" style="width: 100px" id="prima_neta_'
				+ configuracionCoberturaId;
				if(tipo=="direccion")
					formacionFila=formacionFila + '" class="datosPymes primaNetaCobertura" disabled></td>';
				else
					formacionFila=formacionFila + '" class="datosPymes" disabled></td>';
				
				formacionFila=formacionFila + '<td></td>'
				+ '<td></td>'
				+ '</tr>';
			}
			formacionFila = formacionFila+'<tr>'
			+ '<td colspan="8" style="color: #FF0000; font-size: x-small;">'
			+ '<label id="advertencia_'
			+ configuracionCoberturaId + '"></label>'
			+ '</td>' + '</tr>' + '</table>' + '</div>'
			+ '</td>' + '</tr>';	
			
			$("#detalle_cobertura_" + tipo).append(formacionFila);
			
			//Aqui se cargan los deducibles.
			var tipoVariable = $("#seguridad_adecuada_si").is(":checked") ? "true" : "false";
			
			cargarDeducibles(configuracionCoberturaId, tipoVariable, filterResult[0].coberturaNombre);
			
			
			if (typeof filterResult[0].textoDeducible !== "undefined") {
				var contadorDeducibles=0;
				$(".deducibleIDs").each(function() {
					var deducibleId = $(this).val();
					if(deducibleId==configuracionCoberturaId && filterResult[0].tipoDeclaracion != "2")
						contadorDeducibles++;
				});
				
				if(contadorDeducibles==0 && filterResult[0].tipoDeclaracion != "2"){
					$("#deducibles_general").append(
							'<tr id="deducible_cobertura_General_'
							+ configuracionCoberturaId
							+ '">'
							+ '<td><table><tr><td style="font-weight:bold">Deducible:&nbsp;'
							+ filterResult[0].coberturaNombre
							+ '<input type="hidden" class="deducibleIDs" id="deducible_cobertura_General_'+configuracionCoberturaId
							+ '" value="'+configuracionCoberturaId
							+ '"></td></tr><tr>'
							+ '<td><textarea disabled id="text_area_deducible_cobertura_'
							+ configuracionCoberturaId
							+ '" rows="2" cols="100">'
							+ filterResult[0].textoDeducible
							+ '</textarea><input type="hidden" id="ids_deducible_cobertura_'
							+ configuracionCoberturaId
							+ '" value="'+filterResult[0].idsDeducible
							+ '"><input type="hidden" id="valores_deducible_cobertura_'
							+ configuracionCoberturaId
							+ '" value="'+filterResult[0].valoresDeducible
							+ '"><input type="hidden" id="textos_deducible_cobertura_'
							+ configuracionCoberturaId
							+ '" value="'+filterResult[0].textosDeducible
							+ '"></td></tr></table></td></tr>');
				}
			}
			$("#tasa_sugerida_" + configuracionCoberturaId).val(tasa);
			$("#tasa_ingresada_" + configuracionCoberturaId).val(tasa);

			if (valorMaximo != -1 && valorMaximo != 0) {
				
				$("#advertencia_" + configuracionCoberturaId).html(
						"Monto asegurado m&aacute;ximo para esta cobertura: "
						+ formatDollar(valorMaximo));
			}
			//Desactivo los check que dependen de PLO RC
			if(filterResult[0].coberturaNombre=="Responsabilidad Civil P.L.O"){
				$(".dependeDe_9").each(function() {
					$(this).prop("disabled", false);
				});
			}
		} else {
			$("#fila_config_cobertura_" + configuracionCoberturaId).remove();
			$("#deducible_cobertura_" + configuracionCoberturaId).remove();
			if(filterResult[0].coberturaNombre=="Responsabilidad Civil P.L.O"){
				$(".dependeDe_9").each(function() {
					$(this).prop("disabled", true);
				});
			}
			
			// Calculo total de la prima de esta direccion
			var valorTotal = 0;
			$(".primaNetaCobertura").each(function() {
				valorTotal = valorTotal + Number($(this).val());
			});
			$("#prima_neta_por_direccion").text(formatDollar(valorTotal));
			
		}
		if($("#usuarioRol").val()=="0"){
			$(".porcentajeTasasPymes").each(function() {
				//$(this).prop('hidden', 'hidden');
				$(this).css('display','none');
			});
		}		
	}
}

function cargarDeducibles(configuracionCoberturaId, tipoVariable, coberturaNombre) {
	$.ajax({
		url : '../PymeConfiguracionCoberturaController',
		data : {
			"tipoConsulta" : "traeDeducible",
			"configuracionCoberturaId" : configuracionCoberturaId,
			"tipoVariable" : tipoVariable,
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var listadoTipos = data.pymeTipoDeducibleArr;			
			$.each(listadoTipos, function (index) {
				if (typeof listadoTipos[0].textosDeducible !== "undefined") {
					var contadorDeducibles=0;
					$(".deducibleIDsDir").each(function() {
						var deducibleId = $(this).val();
						if(deducibleId==configuracionCoberturaId)
							contadorDeducibles++;
					});
					
					if(contadorDeducibles==0){
						$("#deducibles_por_direccion").append(
									'<tr id="deducible_cobertura_'
									+ configuracionCoberturaId
									+ '">'
									+ '<td><table><tr><td style="font-weight:bold">Deducible:&nbsp;'
									+ coberturaNombre
									+ '<input type="hidden" class="deducibleIDsDir" id="deducible_cobertura_'+configuracionCoberturaId
									+ '" value="'+configuracionCoberturaId
									+ '"></td></tr><tr>'
									+ '<td><textarea disabled id="text_area_deducible_cobertura_'
									+ configuracionCoberturaId
									+ '" rows="2" cols="100">'
									+ listadoTipos[index].texto
									+ '</textarea><input type="hidden" id="ids_deducible_cobertura_'
									+ configuracionCoberturaId
									+ '" value="'+listadoTipos[index].idsDeducible
									+ '"><input type="hidden" id="valores_deducible_cobertura_'
									+ configuracionCoberturaId
									+ '" value="'+listadoTipos[index].valoresDeducible
									+ '"><input type="hidden" id="textos_deducible_cobertura_'
									+ configuracionCoberturaId
									+ '" value="'+listadoTipos[index].textosDeducible
									+ '"></td></tr></table></td></tr>');
						}
					}
			});
		}
	});
}

function cargarDeduciblesGenerales(configuracionCoberturaId, tipoVariable, coberturaNombre) {
	$.ajax({
		url : '../PymeConfiguracionCoberturaController',
		data : {
			"tipoConsulta" : "traeDeducibleGeneral",
			"configuracionCoberturaId" : configuracionCoberturaId,
			"tipoVariable" : tipoVariable,
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var listadoTipos = data.pymeTipoDeducibleArr;			
			$.each(listadoTipos, function (index) {
				if (typeof listadoTipos[0].textosDeducible !== "undefined") {
					var contadorDeducibles=0;
					$(".deducibleIDs").each(function() {
						var deducibleId = $(this).val();
						if(deducibleId==configuracionCoberturaId)
							contadorDeducibles++;
					});
					
					if(contadorDeducibles==0){
						$("#deducibles_general").append(
									'<tr id="deducible_cobertura_General_'
									+ configuracionCoberturaId
									+ '">'
									+ '<td><table><tr><td style="font-weight:bold">Deducible:&nbsp;'
									+ coberturaNombre
									+ '<input type="hidden" class="deducibleIDs" id="deducible_cobertura_General_'+configuracionCoberturaId
									+ '" value="'+configuracionCoberturaId
									+ '"></td></tr><tr>'
									+ '<td><textarea disabled id="text_area_deducible_cobertura_'
									+ configuracionCoberturaId
									+ '" rows="2" cols="100">'
									+ listadoTipos[index].texto
									+ '</textarea><input type="hidden" id="ids_deducible_cobertura_'
									+ configuracionCoberturaId
									+ '" value="'+listadoTipos[index].idsDeducible
									+ '"><input type="hidden" id="valores_deducible_cobertura_'
									+ configuracionCoberturaId
									+ '" value="'+listadoTipos[index].valoresDeducible
									+ '"><input type="hidden" id="textos_deducible_cobertura_'
									+ configuracionCoberturaId
									+ '" value="'+listadoTipos[index].textosDeducible
									+ '"></td></tr></table></td></tr>');
						}
					}
			});
		}
	});
}


function cargarDeducible(configuracionCoberturaId, textDeducible, ids, valores, textosDeducible) {
	// Filtro en el array de cobertura el id seleccionado
	var filterResult = $(configCobertasPorProducto)
	.filter(
			function(idx) {
				return configCobertasPorProducto[idx].configuracionCoberturaId === configuracionCoberturaId;
			});
	if (filterResult.length > 0) {
		$("#deducibles_por_direccion").append(
				'<tr id="deducible_cobertura_'
				+ configuracionCoberturaId
				+ '">'
				+ '<td><table><tr><td style="font-weight:bold">Deducible:&nbsp;'
				+ filterResult[0].coberturaNombre
				+ '<input type="hidden" class="deducibleIDsDir" id="deducible_cobertura_'+configuracionCoberturaId
				+ '" value="'+configuracionCoberturaId
				+ '"></td></tr><tr>'
				+ '<td><textarea disabled id="text_area_deducible_cobertura_'
				+ configuracionCoberturaId
				+ '" rows="2" cols="100">'
				+ textDeducible
				+ '</textarea><input type="hidden" id="ids_deducible_cobertura_'
				+ configuracionCoberturaId
				+ '" value="' + ids
				+ '"><input type="hidden" id="valores_deducible_cobertura_'
				+ configuracionCoberturaId
				+ '" value="' + valores 
				+ '"><input type="hidden" id="textos_deducible_cobertura_'
				+ configuracionCoberturaId
				+ '" value="'+textosDeducible
				+ '"></td></tr></table></td></tr>');
	}
}

function crearCoberturaGeneral(configuracionCoberturaId, tipo,plan) {
	// Filtro en el array de cobertura el id seleccionado
	var filterResult = $(configCobertasPorProducto).filter(function(idx) {
		return configCobertasPorProducto[idx].configuracionCoberturaId === configuracionCoberturaId;
	});
	if (filterResult.length > 0) {
		if ($("#cobertura_" + configuracionCoberturaId).is(':checked')) {
			var valorMaximo = determinarValorMaximo(configuracionCoberturaId);
			var tasa = determinarTasa(configuracionCoberturaId);
			var clase = filterResult[0].incluyeEnProducto == "1" ? "predeterminado" : "normal";
			var formacionFila='<tr class="'
								+ clase
								+ '" id="fila_config_cobertura_'
								+ configuracionCoberturaId
								+ '">'
								+ '<td style="width: 50%">'
								+ '<b>'
								+ filterResult[0].coberturaNombre
								+ '</b>'
								+ '<div class="panel panel-primary">'
								+ '<table style="width: 100%" cellpadding="3">';
			
			if(filterResult[0].usaValorAdicional == "0"){ //Esto quiere decir que no es transporte
				formacionFila=formacionFila+'<tr>'
				+ '<td><label class="porcentajeTasasPymes" id="lbl_tasa_sugerida_'
				+ configuracionCoberturaId
				+ '">Tasa Sugerida:</label></td>'
				+ '<td><input type="text" style="width: 70px" disabled id="tasa_sugerida_'
				+ configuracionCoberturaId
				+ '" class="datosPymes porcentajeTasasPymes"></td>'
				+ '<td><label class="porcentajeTasasPymes" id="lbl_tasa_ingresada_'
				+ configuracionCoberturaId
				+ '">Tasa Ingresada:</label></td>'
				+ '<td><input type="text" style="width: 70px" id="tasa_ingresada_'
				+ configuracionCoberturaId
				+ '" class="datosPymes porcentajeTasasPymes" onchange="calcularPrimaNeta('
				+ configuracionCoberturaId
				+ ',' + valorMaximo + ')"></td>'
				+ '<td>Monto Asegurado:</td>'
				+ '<td><input type="text" style="width: 100px" id="monto_asegurado_'
				+ configuracionCoberturaId;
				if(tipo=="direccion")
					formacionFila=formacionFila + '" class="datosPymes datosMontoAseguradoDir" onchange="calcularPrimaNeta(';
				else
					formacionFila=formacionFila + '" class="datosPymes datosMontoAseguradoGen" onchange="calcularPrimaNeta(';
						
				formacionFila=formacionFila + configuracionCoberturaId
				+ ',' + valorMaximo + ')"></td>'
				+ '<td>Prima Neta:</td>'
				+ '<input type="hidden" class="coberturaGeneral" id="codigoCobertura_'+configuracionCoberturaId+'" value="'+configuracionCoberturaId+'" >'
				+ '<input type="hidden" id="nombreCobertura_'+configuracionCoberturaId+'" value="'+filterResult[0].coberturaNombre+'">'
				+ '<td><input type="text" style="width: 100px" id="prima_neta_'
				+ configuracionCoberturaId;
				if(tipo=="direccion")
					formacionFila=formacionFila + '" class="datosPymes primaNetaCobertura" disabled></td>';
				else
					formacionFila=formacionFila + '" class="datosPymes" disabled></td>';	
				formacionFila=formacionFila + '</tr>';
			}
			else{
				formacionFila=formacionFila+'<tr>'
				+ '<td><label class="porcentajeTasasPymes" id="lbl_tasa_sugerida_'
				+ configuracionCoberturaId
				+ '">Tasa Sugerida:</label></td>'
				+ '<td><input type="text" style="width: 70px" disabled id="tasa_sugerida_'
				+ configuracionCoberturaId
				+ '" class="datosPymes porcentajeTasasPymes"></td>'
				+ '<td><label class="porcentajeTasasPymes" id="lbl_tasa_ingresada_'
				+ configuracionCoberturaId
				+ '">Tasa Ingresada:</label></td>'
				+ '<td><input type="text" style="width: 70px" id="tasa_ingresada_'
				+ configuracionCoberturaId
				+ '" class="datosPymes porcentajeTasasPymes" onchange="calcularPrimaNeta('
				+ configuracionCoberturaId
				+ ',' + valorMaximo + ')"></td>'
				+ '<td></td>'
				+ '<td></td>'
				+ '<td></td>'
				+ '<td></td>'
				+ '</tr>'
				+ '<tr>'
				+ '<td>L&iacute;mite por Embarque:</td>'
				+ '<td><input type="text" style="width: 70px" id="valor_adicional_'
				+ configuracionCoberturaId
				+ '" class="datosPymes porcentajeTasasPymes" onchange="validarMaximoEmbarque('
				+ configuracionCoberturaId
				+ ',' + filterResult[0].valorMaximo2 + ')"></td>'
				+ '<td>Movilizaci&oacute;n Anual:</td>'
				+ '<td><input type="text" style="width: 100px" id="monto_asegurado_'
				+ configuracionCoberturaId;
				if(tipo=="direccion")
					formacionFila=formacionFila + '" class="datosPymes datosMontoAseguradoDir" onchange="calcularPrimaNeta(';
				else
					formacionFila=formacionFila + '" class="datosPymes datosMontoAseguradoGen" onchange="calcularPrimaNeta(';
				
				formacionFila=formacionFila + configuracionCoberturaId
				+ ',' + valorMaximo + ')"></td>'
				+ '<td>Prima Neta:</td>'
				+ '<input type="hidden" class="coberturaGeneral" id="codigoCobertura_'+configuracionCoberturaId+'" value="'+configuracionCoberturaId+'" >'
				+ '<input type="hidden" id="nombreCobertura_'+configuracionCoberturaId+'" value="'+filterResult[0].coberturaNombre+'">'
				+ '<td><input type="text" style="width: 100px" id="prima_neta_'
				+ configuracionCoberturaId;
				if(tipo=="direccion")
					formacionFila=formacionFila + '" class="datosPymes primaNetaCobertura" disabled></td>';
				else
					formacionFila=formacionFila + '" class="datosPymes" disabled></td>';
				
				formacionFila=formacionFila + '<td></td>'
				+ '<td></td>'
				+ '</tr>';
			}
			formacionFila = formacionFila+'<tr>'
			+ '<td colspan="8" style="color: #FF0000; font-size: x-small;">'
			+ '<label id="advertencia_'
			+ configuracionCoberturaId + '"></label>'
			+ '</td>' + '</tr>' + '</table>' + '</div>'
			+ '</td>' + '</tr>';	
			
			$("#detalle_cobertura_" + tipo).append(formacionFila);
			
			//Aqui se cargan los deducibles.
			var tipoVariable = $("#seguridad_adecuada_si").is(":checked") ? "true" : "false";
			
			cargarDeduciblesGenerales(configuracionCoberturaId, tipoVariable, filterResult[0].coberturaNombre);
			
			
			if (typeof filterResult[0].textoDeducible !== "undefined") {
				var contadorDeducibles=0;
				$(".deducibleIDs").each(function() {
					var deducibleId = $(this).val();
					if(deducibleId==configuracionCoberturaId && filterResult[0].tipoDeclaracion != "2")
						contadorDeducibles++;
				});
				
				if(contadorDeducibles==0 && filterResult[0].tipoDeclaracion != "2"){
					$("#deducibles_general").append(
							'<tr id="deducible_cobertura_General_'
							+ configuracionCoberturaId
							+ '">'
							+ '<td><table><tr><td style="font-weight:bold">Deducible:&nbsp;'
							+ filterResult[0].coberturaNombre
							+ '<input type="hidden" class="deducibleIDs" id="deducible_cobertura_General_'+configuracionCoberturaId
							+ '" value="'+configuracionCoberturaId
							+ '"></td></tr><tr>'
							+ '<td><textarea disabled id="text_area_deducible_cobertura_'
							+ configuracionCoberturaId
							+ '" rows="2" cols="100">'
							+ filterResult[0].textoDeducible
							+ '</textarea><input type="hidden" id="ids_deducible_cobertura_'
							+ configuracionCoberturaId
							+ '" value="'+filterResult[0].idsDeducible
							+ '"><input type="hidden" id="valores_deducible_cobertura_'
							+ configuracionCoberturaId
							+ '" value="'+filterResult[0].valoresDeducible
							+ '"><input type="hidden" id="textos_deducible_cobertura_'
							+ configuracionCoberturaId
							+ '" value="'+filterResult[0].textosDeducible
							+ '"></td></tr></table></td></tr>');
				}
			}
			$("#tasa_sugerida_" + configuracionCoberturaId).val(tasa);
			$("#tasa_ingresada_" + configuracionCoberturaId).val(tasa);

			if (valorMaximo != -1 && valorMaximo != 0) {
				
				$("#advertencia_" + configuracionCoberturaId).html(
						"Monto asegurado m&aacute;ximo para esta cobertura: "
						+ formatDollar(valorMaximo));
			}
			//Desactivo los check que dependen de PLO RC
			if(filterResult[0].coberturaNombre=="Responsabilidad Civil P.L.O"){
				$(".dependeDe_9").each(function() {
					$(this).prop("disabled", false);
				});
			}
		} else {
			$("#fila_config_cobertura_" + configuracionCoberturaId).remove();
			$("#deducible_cobertura_General_" + configuracionCoberturaId).remove();
			if(filterResult[0].coberturaNombre=="Responsabilidad Civil P.L.O"){
				$(".dependeDe_9").each(function() {
					$(this).prop("disabled", true);
				});
			}
			
			// Calculo total de la prima de esta direccion
			var valorTotal = 0;
			$(".primaNetaCobertura").each(function() {
				valorTotal = valorTotal + Number($(this).val());
			});
			$("#prima_neta_por_direccion").text(formatDollar(valorTotal));
			
		}
		if($("#usuarioRol").val()=="0"){
			$(".porcentajeTasasPymes").each(function() {
				//$(this).prop('hidden', 'hidden');
				$(this).css('display','none');
			});
		}		
	}
}

function cargarCobertura(configuracionCoberturaId, tipo, tasaSugerida, tasaIngresada, montoAsegurado, valorPrima, valorAdicional) {
	// Filtro en el array de cobertura el id seleccionado
	var filterResult = $(configCobertasPorProducto)
	.filter(
			function(idx) {
				return configCobertasPorProducto[idx].configuracionCoberturaId === configuracionCoberturaId;
			});
	if (filterResult.length > 0) {
		$("#cobertura_" + configuracionCoberturaId).prop( "checked", true );
		if(Number(montoAsegurado)>0){
			var valorMaximo = determinarValorMaximo(configuracionCoberturaId);
			var clase = filterResult[0].incluyeEnProducto == "1" ? "predeterminado" : "normal";
			var FormacionFila='<tr class="'
				+ clase
				+ '" id="fila_config_cobertura_'
				+ configuracionCoberturaId
				+ '">'
				+ '<td style="width: 50%">'
				+ '<b>'
				+ filterResult[0].coberturaNombre
				+ '</b>'
				+ '<div class="panel panel-primary">'
				+ '<table style="width: 100%" cellpadding="3">';
			if(filterResult[0].usaValorAdicional == "0"){ //Esto quier decir que no es transporte
				FormacionFila = FormacionFila + '<tr>'
				+ '<td><label class="porcentajeTasasPymes" id="lbl_tasa_sugerida_'
				+ configuracionCoberturaId
				+ '">Tasa Sugerida:</label></td>'
				+ '<td><input type="text" style="width: 70px" disabled id="tasa_sugerida_'
				+ configuracionCoberturaId
				+ '" class="datosPymes porcentajeTasasPymes"></td>'
				+ '<td><label class="porcentajeTasasPymes" id="lbl_tasa_ingresada_'
				+ configuracionCoberturaId
				+'">Tasa Ingresada:</label></td>'	
				+ '<td><input type="text" style="width: 70px" id="tasa_ingresada_'
				+ configuracionCoberturaId
				+ '" class="datosPymes porcentajeTasasPymes" onchange="calcularPrimaNeta('
				+ configuracionCoberturaId
				+ ',' + valorMaximo + ')"></td>'
				+ '<td>Monto Asegurado:</td>'
				+ '<td><input type="text" style="width: 100px" id="monto_asegurado_'
				+ configuracionCoberturaId
				+ '" class="datosPymes" onchange="calcularPrimaNeta('
				+ configuracionCoberturaId
				+ ',' + valorMaximo + ')"></td>'
				+ '<td>Prima Neta:</td>'
				+ '<input type="hidden" class="cobertura" id="codigoCobertura_'+configuracionCoberturaId+'" value="'+configuracionCoberturaId+'" >'
				+ '<input type="hidden" id="nombreCobertura_'+configuracionCoberturaId+'" value="'+filterResult[0].coberturaNombre+'">'
				+ '<td><input type="text" style="width: 100px" id="prima_neta_'
				+ configuracionCoberturaId;
				if(tipo=="direccion")
					FormacionFila = FormacionFila + '" class="datosPymes primaNetaCobertura" disabled></td>';
				else
					FormacionFila = FormacionFila + '" class="datosPymes" disabled></td>';
				FormacionFila = FormacionFila + '</tr>';
				
			}
			else{
				FormacionFila = FormacionFila + '<tr>'
				+ '<td><label class="porcentajeTasasPymes" id="lbl_tasa_sugerida_'
				+ configuracionCoberturaId
				+ '">Tasa Sugerida:</label></td>'
				+ '<td><input type="text" style="width: 70px" disabled id="tasa_sugerida_'
				+ configuracionCoberturaId
				+ '" class="datosPymes porcentajeTasasPymes"></td>'
				+ '<td><label class="porcentajeTasasPymes" id="lbl_tasa_ingresada_'
				+ configuracionCoberturaId
				+'">Tasa Ingresada:</label></td>'	
				+ '<td><input type="text" style="width: 70px" id="tasa_ingresada_'
				+ configuracionCoberturaId
				+ '" class="datosPymes porcentajeTasasPymes" onchange="calcularPrimaNeta('
				+ configuracionCoberturaId
				+ ',' + valorMaximo + ')"></td>'
				+ '<td></td>'
				+ '<td></td>'
				+ '<td></td>'
				+ '<td></td>'
				+ '</tr>'
				+ '<tr>'
				+ '<td>L&iacute;mite por Embarque:</td>'
				+ '<td><input type="text" style="width: 70px" id="valor_adicional_'
				+ configuracionCoberturaId
				+ '" class="datosPymes"></td>'
				+ '<td>Movilizaci&oacute;n Anual:</td>'
				+ '<td><input type="text" style="width: 100px" id="monto_asegurado_'
				+ configuracionCoberturaId
				+ '" class="datosPymes" onchange="calcularPrimaNeta('
				+ configuracionCoberturaId
				+ ',' + valorMaximo + ')"></td>'
				+ '<td>Prima Neta:</td>'
				+ '<input type="hidden" class="cobertura" id="codigoCobertura_'+configuracionCoberturaId+'" value="'+configuracionCoberturaId+'" >'
				+ '<input type="hidden" id="nombreCobertura_'+configuracionCoberturaId+'" value="'+filterResult[0].coberturaNombre+'">'
				+ '<td><input type="text" style="width: 100px" id="prima_neta_'
				+ configuracionCoberturaId;
				if(tipo=="direccion")
					FormacionFila = FormacionFila + '" class="datosPymes primaNetaCobertura" disabled></td>';
				else
					FormacionFila = FormacionFila + '" class="datosPymes" disabled></td>';
				FormacionFila = FormacionFila + '<td></td>'
				+ '<td></td>'
				+ '</tr>';
			}
			FormacionFila = FormacionFila + '<tr>'
			+ '<td colspan="6" style="color: #FF0000; font-size: x-small;">'
			+ '<label id="advertencia_'
			+ configuracionCoberturaId + '"></label>'
			+ '</td>' + '</tr>' + '</table>' + '</div>'
			+ '</td>' + '</tr>';
			
			$("#detalle_cobertura_" + tipo).append(FormacionFila);
			
			if (valorMaximo != -1 && valorMaximo != 0) {
				$("#advertencia_" + configuracionCoberturaId).html(
						"Monto asegurado m&aacute;ximo para esta cobertura: "
						+ formatDollar(valorMaximo));
			}
			//Seteo los valores guardados en base
			$("#tasa_sugerida_" + configuracionCoberturaId).val(tasaSugerida);
			$("#tasa_ingresada_" + configuracionCoberturaId).val(tasaIngresada);
			$("#monto_asegurado_" + configuracionCoberturaId).val(montoAsegurado);
			$("#prima_neta_" + configuracionCoberturaId).val(valorPrima);
			
			if(filterResult[0].usaValorAdicional == "1")
				$("#valor_adicional_" + configuracionCoberturaId).val(valorAdicional);
			
			if(filterResult[0].incluyeEnProducto == "1")
				$("#monto_asegurado_"+ configuracionCoberturaId).attr("disabled", "disabled");
			else
				$("#monto_asegurado_"+ configuracionCoberturaId).removeAttr("disabled");
			
			if (cotizacionEstado == "Pendiente Asignar Inspector" || cotizacionEstado == "Pendiente de Inspeccion" || cotizacionEstado == "Pendiente" || cotizacionEstado == "Emitido"){
				$(".datosPymes").each(function() {
					$(this).attr("disabled", "disabled");
				});
				$("#ingresarNuevoValores").css("visibility", "hidden");
			}
			

			//Oculto las tasas si es necesario
			if($("#usuarioRol").val()=="0"){
				$(".porcentajeTasasPymes").each(function() {
					$(this).css('display','none');
				});
			}
		}
	}
}

function cerrarAlertFichaPymeError(){
	$("#msgPopupFichaPymeError").hide();
}

function cerrarAlertFichaInspeccionError(){
	$("#msgPopupFichaInspeccionError").hide();
}

function mostrarResumen(){
	$.ajax({
		url : '../PymesObjetoCotizacionController',
		data : {
			"tipoConsulta" : "obtenerResumenValores",
			"cotizacionId" : $("#cotizacion_id").text(),
		},
		type : 'POST',
		datatype : 'json',
		success : function(data) {
			$("#resumen_inspeccion_total_suma_asegurada").val(formatDollar(data.valorAsegurado));
			$("#resumen_inspeccion_prima_sin_impuestos").val(formatDollar(data.valorPrima));
			$("#resumen_inspeccion_super_bancos").val(formatDollar(data.valorSuperBancos));
			if (parseFloat(data.porcentajeDescuento) > 0)
				$("#resumen_inspeccion_filaDescuento").show();
			$("#resumen_inspeccion_porcentaje_descuento").val(formatDollar(data.porcentajeDescuento));
			$("#resumen_inspeccion_seguro_campesino").val(formatDollar(data.valorSeguroCampesino));
			$("#resumen_inspeccion_derecho_emision").val(formatDollar(data.valorDerechosEmision));
			$("#resumen_inspeccion_subtotal").val(formatDollar(data.valorSubTotal));
			$("#resumen_inspeccion_iva").val(formatDollar(data.valorIva));
			$("#resumen_inspeccion_total").val(formatDollar(data.valorTotal));

			//Valores para el cuadro de resumen.
			$("#resumen_total_suma_asegurada").val(formatDollar(data.valorAsegurado));
			$("#resumen_prima_sin_impuestos").val(formatDollar(data.valorPrima));
			$("#resumen_super_bancos").val(formatDollar(data.valorSuperBancos));
			if (parseFloat(data.porcentajeDescuento) > 0)
				$("#resumen_filaDescuento").show();
			$("#resumen_porcentaje_descuento").val(formatDollar(data.porcentajeDescuento));
			$("#resumen_derecho_emision").val(formatDollar(data.valorDerechosEmision));
			$("#resumen_subtotal").val(formatDollar(data.valorSubTotal));
			$("#resumen_iva").val(formatDollar(data.valorIva));
			$("#resumen_total").val(formatDollar(data.valorTotal));
			
			calculoIVAPuntoVentaCotizacion();
		}
	});
}

/*function cargarDeducible(configuracionCoberturaId, textDeducible, ids, valores, textosDeducible) {
	// Filtro en el array de cobertura el id seleccionado
	var filterResult = $(configCobertasPorProducto)
	.filter(
			function(idx) {
				return configCobertasPorProducto[idx].configuracionCoberturaId === configuracionCoberturaId;
			});
	if (filterResult.length > 0) {
		$("#deducibles_general").append(
				'<tr id="deducible_cobertura_'
				+ configuracionCoberturaId
				+ '">'
				+ '<td><table><tr><td style="font-weight:bold">Deducible:&nbsp;'
				+ filterResult[0].coberturaNombre
				+ '<input type="hidden" class="deducibleIDs" id="deducible_cobertura_'+configuracionCoberturaId
				+ '" value="'+configuracionCoberturaId
				+ '"></td></tr><tr>'
				+ '<td><textarea disabled id="text_area_deducible_cobertura_'
				+ configuracionCoberturaId
				+ '" rows="2" cols="100">'
				+ textDeducible
				+ '</textarea><input type="hidden" id="ids_deducible_cobertura_'
				+ configuracionCoberturaId
				+ '" value="' + ids
				+ '"><input type="hidden" id="valores_deducible_cobertura_'
				+ configuracionCoberturaId
				+ '" value="' + valores 
				+ '"><input type="hidden" id="textos_deducible_cobertura_'
				+ configuracionCoberturaId
				+ '" value="'+textosDeducible
				+ '"></td></tr></table></td></tr>');
	}
}*/

function cargarAsistencia(asistenciaId, valor) {
	// Filtro en el array de cobertura el id seleccionado
	var filterResult = $(configAsistenciasPorProducto).filter(
			function(idx) {
				return configAsistenciasPorProducto[idx].asistenciaId === asistenciaId;
			});
	if (filterResult.length > 0) {
		$("#asistencia_" + asistenciaId).attr('checked', true);
		$("#valor_asistencia_" + asistenciaId).val(valor);
		if (filterResult[0].esPredeterminada == 1)
			$("#valor_asistencia_" + asistenciaId).hide();
		else
			$("#valor_asistencia_" + asistenciaId).show();

	}
}

function obtenerAsistenciasPorProducto(productoID) {
	$("#asistencias_generales tr").remove();
	$.ajax({
		url : '../PymeAsistenciaController',
		data : {
			"tipoConsulta" : "buscarPorProductoId",
			"grupoPorProductoId" : productoID,
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			configAsistenciasPorProducto = data.asistenciaJSONArray;
			$.each(configAsistenciasPorProducto,
					function(index) {
				if (configAsistenciasPorProducto[index].esPredeterminada == 1) {
					$("#asistencias_generales")
					.append(
							'<tr><td style="color: orange;"><input type="checkbox"  id="asistencia_'
							+ configAsistenciasPorProducto[index].asistenciaId
							+ '" class="datosPymes" checked disabled>'
							+ configAsistenciasPorProducto[index].nombre
							+ '</td>'
							+ '<td><input type="text" id="valor_asistencia_'
							+ configAsistenciasPorProducto[index].asistenciaId
							+ '" disabled class="datosPymes" style="width: 80px; display: none;" value="'
							+ configAsistenciasPorProducto[index].valor
							+ '">'
							+ '</td></tr>');
				} else {
					$("#asistencias_generales")	.append(
							'<tr><td style="color: green;"><input type="checkbox"  id="asistencia_'
							+ configAsistenciasPorProducto[index].asistenciaId
							+ '" class="datosPymes" onclick=crearAsistencias('
							+ configAsistenciasPorProducto[index].asistenciaId
							+ ')>'
							+ configAsistenciasPorProducto[index].nombre
							+ '</td>'
							+ '<td><input type="text" id="valor_asistencia_'
							+ configAsistenciasPorProducto[index].asistenciaId
							+ '" disabled class="datosPymes" style="width: 80px; display: none;" value="'
							+ configAsistenciasPorProducto[index].valor
							+ '">'
							+ '</td></tr>');
				}
			});
		}
	});
}

function crearAsistencias(asistenciaId) {
	if ($("#asistencia_" + asistenciaId).is(':checked'))
		$("#valor_asistencia_" + asistenciaId).show();
	else
		$("#valor_asistencia_" + asistenciaId).hide();
}

function determinarTasa(configuracionCoberturaId) {
	var filterResult = $(configCobertasPorProducto).filter(function(idx) {
		return configCobertasPorProducto[idx].configuracionCoberturaId === configuracionCoberturaId;
	});
	var tasa;
	if(filterResult[0].tipoDeclaracionNombre=="GENERAL"){
		//verifico que tenga un item caso contrario dejo seguir con normalidad
		var cotizacionId=$("#cotizacion_id").text();
		var tieneRiesgoP;
		var valorRiesgo;
		var tipoRiesgo;
		var zonaRiesgo;
		var contadorDirecciones;
		$.ajax({
			url : '../PymeRiesgoController',
			data : {
				"tipoConsulta" : "hallarPrimerRiesgo",
				"cotizacionId" : cotizacionId
			},
			type : 'POST',
			datatype : 'json',
			async: false,
			success : function(data) {
				contadorDirecciones=data.contadorDirecciones;
				if(data.zonaRiesgo=="SI"){
					zonaRiesgo="SI";
					if(data.tipoRiesgo=="1"){
						tipoRiesgo="1";
						valorRiesgo=data.valorRiesgo;
					}else{
						tipoRiesgo="2";
						valorRiesgo=data.valorRiesgo;
					}
				}else{
					zonaRiesgo="NO";
					tipoRiesgo="0";
					valorRiesgo="";
				}
			}
		});
		if(contadorDirecciones!="0"){
			if(zonaRiesgo=="SI"){
			//VOLVEMOS A CALCULAR LAS TASAS PERO EN BASE AL PRIMER ITEM
			if(tipoRiesgo=="1"){
				var distanciaRiesgo=valorRiesgo;
				$.ajax({
					url : '../PymeRiesgoController',
					data : {
						"tipoConsulta" : "traerTasaVolcan",
						"distancia" : distanciaRiesgo,
						"configuracionCoberturaId" : configuracionCoberturaId
					},
					async : false,
					type : 'post',
					datatype : 'json',
					success : function(data) {
						tasa = data.tasa;				
					}
				});	
			}		
			/**para tomar tasas en caso de que el riesgo pertenezca a una zona con riesgo**/
			if(tipoRiesgo=="2"){
					//llamo al servlet y traigo la tasa para la configuracion costa tiene danio
					$.ajax({
						url : '../PymeRiesgoController',
						data : {
							"tipoConsulta" : "traerTasaCosta",
							"tieneRiesgo" : valorRiesgo,
							"configuracionCoberturaId" : configuracionCoberturaId
						},
						async : false,
						type : 'post',
						datatype : 'json',
						success : function(data) {
							tasa = data.tasa;
							
						}
					});	
				}
			if(tasa==0)
				tasa=determinarTasaNormal(configuracionCoberturaId);
			}else{
				tasa=determinarTasaNormal(configuracionCoberturaId);
			}
		}else{
			tasa=determinarTasaNormal(configuracionCoberturaId);
		}
				
		
	}else{
		tasa=determinarTasaNormal(configuracionCoberturaId);
		/**TASA APLICABLE POR TIPO DE RIESGO**/
		//verifico si no estan activos los campos de tasas por riesgos caso contrario queda tal cual.
		if(existeRiesgoVolcan=="SI"){
			var distanciaRiesgo=$("#distanciaRiesgo").val();
			$.ajax({
				url : '../PymeRiesgoController',
				data : {
					"tipoConsulta" : "traerTasaVolcan",
					"distancia" : distanciaRiesgo,
					"configuracionCoberturaId" : configuracionCoberturaId
				},
				async : false,
				type : 'post',
				datatype : 'json',
				success : function(data) {
					tasa = data.tasa;				
				}
			});	
		}
		
		/**para tomar tasas en caso de que el riesgo pertenezca a una zona con riesgo**/
		if(existeRiesgoCosta=="SI"){
			
			var tieneRiesgoC=$("#riesgoDanio_si").is(":checked") ? "SI" : "NO";//verifico si esta selecionada tiene danio o no
			//llamo al servlet y traigo la tasa para la configuracion costa tiene danio
			$.ajax({
				url : '../PymeRiesgoController',
				data : {
					"tipoConsulta" : "traerTasaCosta",
					"tieneRiesgo" : tieneRiesgoC,
					"configuracionCoberturaId" : configuracionCoberturaId
				},
				async : false,
				type : 'post',
				datatype : 'json',
				success : function(data) {
					tasa = data.tasa;
					
				}
			});	
		}
		if(tasa==0)
			tasa=determinarTasaNormal(configuracionCoberturaId);
	}		
	return tasa;
}


//funcion para calculo de tasa normal

function determinarTasaNormal(configuracionCoberturaId){
	var tasa = 0;
	var filterResult = $(configCobertasPorProducto).filter(
			function(idx) {
				return configCobertasPorProducto[idx].configuracionCoberturaId === configuracionCoberturaId;
			});
	var total = $("#total_valor_asegurado").val();
	
	//Determino el valor de contenidos
	/*var valMue = Number($("#valor_muebles_enseres").val());
	var valMaq = Number($("#valor_maquinarias").val());
	var valMer = Number($("#valor_mercaderia").val());
	var valHer = Number($("#valor_herramientas").val());
	var valIns = Number($("#valor_insumos_medicos").val());
	var totalContenidos=valMue+valMaq+valMer+valHer+valIns;*/
	
	var valCon = Number($("#valor_contenidos").val());
	
	if (filterResult.length > 0) {
		if (filterResult[0].tipoTasa == "1") // Tipo de tasa SIMPLE
		{
			if($("#productos").val()=="221" || $("#productos").val()=="218"){
				if(filterResult[0].coberturaNombre=="Todo Riesgo Incendio (Edificio + Contenidos)"){
					if(valCon==0)
						tasa = Number(filterResult[0].tasaEstructuras);
					else
						tasa = Number(filterResult[0].tasa);
				}
				else
					tasa = Number(filterResult[0].tasa);
			}
			else
				tasa = Number(filterResult[0].tasa);
		} 
		else if (filterResult[0].tipoTasa == "2") // Tipo de tasa COMPUESTA
		{
			//$("#asistencias_generales tr").remove();
			$.ajax({
				url : '../PymeConfiguracionCoberturaController',
				data : {
					"tipoConsulta" : "encontrarTasasPorConfiguracionID",
					"configuracionCoberturaId" : configuracionCoberturaId,
				},
				async : false,
				type : 'post',
				datatype : 'json',
				success : function(data) {
					var listadoTasas = data.listadoTasas;
					$.each(listadoTasas, function(index) {
						if (Number(total) >= Number(listadoTasas[index].valorCoberturaInicial) && Number(total) <= Number(listadoTasas[index].valorCoberturaFinal)) {
							tasa = Number(listadoTasas[index].tasa);
						}
					});
				}
			});
		} else if (filterResult[0].tipoTasa == "3") // Tipo de tasa SIN COSTO
		{
			tasa = Number(0);
		} else // Tipo de tasa COPIADA
		{
			$.ajax({
				url : '../PymeConfiguracionCoberturaController',
				data : {
					"tipoConsulta" : "encontrarTasasPorCotizacionCoberturaID",
					"configuracionCoberturaId" : configuracionCoberturaId,
					"cotizacionId" : $("#cotizacion_id").html().trim(),
				},
				async : false,
				type : 'post',
				datatype : 'json',
				success : function(data) {
					if(data.tasa){
						tasa = Number(data.tasa);
					}
					else{
						var filterResult2 = $(configCobertasPorProducto).filter(
							function(idx) {
								return configCobertasPorProducto[idx].coberturaId === filterResult[0].coberturaCopiaId;
							});
						if (filterResult2.length > 0){
							tasa = Number($("#tasa_ingresada_" + filterResult2[0].configuracionCoberturaId).val());
						}
					}
				}
			});
		}
	}
	return tasa;
}

function determinarValorMaximo(configuracionCoberturaId, valorMaximo) {
	// Filtro en el array de cobertura el id seleccionado
	var filterResult = $(configCobertasPorProducto)
	.filter(
			function(idx) {
				return configCobertasPorProducto[idx].configuracionCoberturaId === configuracionCoberturaId;
			});
	if (filterResult.length > 0) {
		switch(filterResult[0].origenValorLimiteAsegurado)
		{
			case 1: // SUMA VALORES
				if (filterResult[0].valorMaximoLimiteAsegurado != "")
					return Number(filterResult[0].valorMaximoLimiteAsegurado);
				break;
			case 2: // SOLO ESTRUCTURA
				var valorCalculado = 0;
				var valorBase = Number($("#valor_estructuras").val());
				if (filterResult[0].porcentajeLimiteAsegurado != "") {
					var porcentaje = Number(filterResult[0].porcentajeLimiteAsegurado);
					valorCalculado = valorBase * porcentaje / 100;
				}
				var limite = 0;
				if (filterResult[0].valorMaximoLimiteAsegurado != "")
					limite = Number(filterResult[0].valorMaximoLimiteAsegurado);
				if(valorCalculado == 0)
					return limite;
				else if (valorCalculado > limite)
					return limite;
				else
					return valorCalculado;
				break;
			case 3: // SOLO CONTENIDO
				var valorCalculado = 0;
				var valorBase = Number($("#valor_muebles_enseres").val())
				+ Number($("#valor_maquinarias").val())
				+ Number($("#valor_mercaderia").val())
				+ Number($("#valor_herramientas").val())
				+ Number($("#valor_insumos_medicos").val())
				+ Number($("#valor_contenidos").val());
				if (filterResult[0].porcentajeLimiteAsegurado != "") {
					var porcentaje = Number(filterResult[0].porcentajeLimiteAsegurado);
					valorCalculado = valorBase * porcentaje / 100;
				}
				var limite = 0;
				if (filterResult[0].valorMaximoLimiteAsegurado != "")
					limite = Number(filterResult[0].valorMaximoLimiteAsegurado);
				//Determino si existe límite
				if(limite>0)
				{
					if(valorCalculado == 0)
						return limite;
					else if (valorCalculado > limite)
						return limite;
					else
						return valorCalculado;
				}
				else{
					return valorCalculado;
				}
				break;
			case 4: // SOLO MAQUINARIA
				var valorCalculado = 0;
				var valorBase = Number($("#valor_maquinarias").val());
				if (filterResult[0].porcentajeLimiteAsegurado != "") {
					var porcentaje = Number(filterResult[0].porcentajeLimiteAsegurado);
					valorCalculado = valorBase * porcentaje / 100;
				}
				var limite = 0;
				if (filterResult[0].valorMaximoLimiteAsegurado != "")
					limite = Number(filterResult[0].valorMaximoLimiteAsegurado);
				if(valorCalculado == 0)
					return limite;
				else if (valorCalculado > limite)
					return limite;
				else
					return valorCalculado;
				break;
			case 5: // SOLO MERCADERIA
				var valorCalculado = 0;
				var valorBase = Number($("#valor_mercaderia").val());
				if (filterResult[0].porcentajeLimiteAsegurado != "") {
					var porcentaje = Number(filterResult[0].porcentajeLimiteAsegurado);
					valorCalculado = valorBase * porcentaje / 100;
				}
				var limite = 0;
				if (filterResult[0].valorMaximoLimiteAsegurado != "")
					limite = Number(filterResult[0].valorMaximoLimiteAsegurado);
				if(valorCalculado == 0)
					return limite;
				else if (valorCalculado > limite)
					return limite;
				else
					return valorCalculado;
				break;
			case 6: // SOLO INSUMOS MÉDICOS-NO ELÉCTRICOS
				var valorCalculado = 0;
				var valorBase = Number($("#valor_insumos_medicos").val());
				if (filterResult[0].porcentajeLimiteAsegurado != "") {
					var porcentaje = Number(filterResult[0].porcentajeLimiteAsegurado);
					valorCalculado = valorBase * porcentaje / 100;
				}
				var limite = 0;
				if (filterResult[0].valorMaximoLimiteAsegurado != "")
					limite = Number(filterResult[0].valorMaximoLimiteAsegurado);
				if(valorCalculado == 0)
					return limite;
				if (valorCalculado > limite)
					return limite;
				else
					return valorCalculado;
				break;
			case 7: // TOTAL DE INCENDIO
				var valorCalculado = 0;
				var valorBase = Number($("#total_valor_asegurado").val());
				if (filterResult[0].porcentajeLimiteAsegurado != "") {
					var porcentaje = Number(filterResult[0].porcentajeLimiteAsegurado);
					valorCalculado = valorBase * porcentaje / 100;
				}
				var limite = 0;
				if (filterResult[0].valorMaximoLimiteAsegurado != "")
					limite = Number(filterResult[0].valorMaximoLimiteAsegurado);
				if(valorCalculado == 0)
					return limite;
				else if (valorCalculado > limite)
					return limite;
				else
					return valorCalculado;
				break;
			case 8: // TOTAL DE ROBO
				var valorCalculado = 0;
				if ($("#monto_asegurado_8")) {
					var valorBase = Number($("#total_valor_asegurado").val());
					if (filterResult[0].porcentajeLimiteAsegurado != "") {
						var porcentaje = Number(filterResult[0].porcentajeLimiteAsegurado);
						valorCalculado = valorBase * porcentaje / 100;
					}
					var limite = 0;
					if (filterResult[0].valorMaximoLimiteAsegurado != "")
						limite = Number(filterResult[0].valorMaximoLimiteAsegurado);
					if(valorCalculado == 0)
						return limite;
					else if (valorCalculado > limite)
						return limite;
					else
						return valorCalculado;
				}
				break;
			case 9: // NO APLICA
				return -1;
				break;
			case 10: //VALOR
				if (filterResult[0].valorMaximoLimiteAsegurado != "")
					return Number(filterResult[0].valorMaximoLimiteAsegurado);
				break;
			case 11:
				var valorCalculado = 0;
				var valorBase = Number($("#total_valor_asegurado").val());
				if (filterResult[0].porcentajeLimiteAsegurado != "") {
					var porcentaje = Number(filterResult[0].porcentajeLimiteAsegurado);
					valorCalculado = valorBase * porcentaje / 100;
				}
				var limite = 0;
				if (filterResult[0].valorMaximoLimiteAsegurado != "")
					limite = Number(filterResult[0].valorMaximoLimiteAsegurado);
				if(valorCalculado == 0)
					return limite;
				else if (valorCalculado > limite)
					return limite;
				else
					return valorCalculado;
				break;
		}
	}
}

function calcularPrimaNeta(configuracionCoberturaId, montomaximo) {
	var p2 = Number($("#monto_asegurado_" + configuracionCoberturaId).val());
	var ts = Number($("#tasa_sugerida_" + configuracionCoberturaId).val());
	var ti = Number($("#tasa_ingresada_" + configuracionCoberturaId).val());
	
	if(ti<ts){
		alert("La tasa ingresada no puede ser menor a la tasa sugerida");
		$("#tasa_ingresada_" + configuracionCoberturaId).val(ts);
	}
	if (!p2){
		alert("Por favor primero ingrese los valores del riesgo para poder calcular la PRIMA, el valor debe ser mayor que 0");
		return;
	}
	
	if (p2<0){
		alert("Por favor primero ingrese los valores del riesgo para poder calcular la PRIMA, el valor debe ser mayor que 0");
		return;
	}
	
	if (montomaximo){
		if (montomaximo != -1 && montomaximo != 0){
			if (p2 > montomaximo){
				alert('Valor ingresado es mayor al Monto Máximo permitido');
				$("#monto_asegurado_" + configuracionCoberturaId).val(montomaximo);
				p2 = montomaximo;
			}
		}
	}
	
	// Filtro en el array de cobertura el id seleccionado
	var filterResult = $(configCobertasPorProducto)
	.filter(
			function(idx) {
				return configCobertasPorProducto[idx].configuracionCoberturaId === configuracionCoberturaId;
			});
	// Calculo el total neto de la prima en la cobertura seleccionada
	var p1 = Number($("#tasa_ingresada_" + configuracionCoberturaId).val());
	//var p2 = Number($("#monto_asegurado_" + configuracionCoberturaId).val());
	$("#prima_neta_" + configuracionCoberturaId).val(p1 * p2);

	// Calculo total de la prima de esta direccion
	var valorTotal = 0;
	$(".primaNetaCobertura").each(function() {
		valorTotal = valorTotal + Number($(this).val());
	});
	$("#prima_neta_por_direccion").text(formatDollar(valorTotal));
}

function calcularTotalValorAsegurado() {
	//riesgo
	if(existeRiesgoCosta=="SI"){
		var tieneRiesgoC=$("#riesgoDanio_si").is(":checked") ? "SI" : "NO";//verifico si esta selecionada tiene danio o no
		var tieneRiesgoL=$("#riesgoDanio_no").is(":checked") ? "SI" : "NO";
		
		if(tieneRiesgoC=="NO" && tieneRiesgoL=="NO" ){
			alert("Se encuentra en zona de riesgo, por favor selecciones si sufrio daño o no.");
			return;
		}
	}
	
	if(existeRiesgoVolcan=="SI"){
		var distanciaRiesgo=$("#distanciaRiesgo").val();
		if(distanciaRiesgo==""){
			alert("Se encuentra en zona de riesgo, por favor selecciones a que distancia se encuentra del riesgo.");
			return;
		}
	}
	
	var valEst = Number($("#valor_estructuras").val());
	var valMue = Number($("#valor_muebles_enseres").val());
	var valMaq = Number($("#valor_maquinarias").val());
	var valMer = Number($("#valor_mercaderia").val());
	var valHer = Number($("#valor_herramientas").val());
	var valIns = Number($("#valor_insumos_medicos").val());
	var valCon = Number($("#valor_contenidos").val());

	var numerictextbox = $("#total_valor_asegurado")
	.data("kendoNumericTextBox");
	numerictextbox.value(valEst + valMue + valMaq + valMer + valHer + valIns + valCon);

	var tabStrip = $("#tabstrip").kendoTabStrip().data("kendoTabStrip");
	if(Number($("#total_valor_asegurado").val())>Number(parametroGrupoProducto.limiteAsegurado)){
		$("#advertencia_general").html("El l&iacute;mite asegurado de incendio por cotizaci&oacute;n no puede ser mayor a: " + formatDollar(parametroGrupoProducto.limiteAsegurado));
		tabStrip.enable(tabStrip.tabGroup.children().eq(2), false);
	}
	else{
		$("#advertencia_general").html("");
		tabStrip.enable(tabStrip.tabGroup.children().eq(2), true);
		$(".predeterminado").each(function() {
			$(this).remove();
		});
	
		//Busco todas las coberturas que dependen de un valor
		var filterCoberturasDependen = $(configCobertasPorProducto).filter(function(idx) {
			return configCobertasPorProducto[idx].dependeValor != "1";
		});
		$.each(filterCoberturasDependen, function(index) {
			switch(filterCoberturasDependen[index].dependeValor){
				case 2: //De Estructuras
					if(valEst>0)
						$("#cobertura_"+ filterCoberturasDependen[index].configuracionCoberturaId).prop("disabled", false);
					else
						$("#cobertura_"+ filterCoberturasDependen[index].configuracionCoberturaId).prop("disabled", true);
					break;
				case 3: //De Muebles y Enseres
					if(valMue>0)
						$("#cobertura_"+ filterCoberturasDependen[index].configuracionCoberturaId).prop("disabled", false);
					else
						$("#cobertura_"+ filterCoberturasDependen[index].configuracionCoberturaId).prop("disabled", true);
					break;
				case 4: //De Maquinaria
					if(valMaq>0)
						$("#cobertura_"+ filterCoberturasDependen[index].configuracionCoberturaId).prop("disabled", false);
					else
						$("#cobertura_"+ filterCoberturasDependen[index].configuracionCoberturaId).prop("disabled", true);
					break;
				case 5: //De Mercadería
					if(valMer>0)
						$("#cobertura_"+ filterCoberturasDependen[index].configuracionCoberturaId).prop("disabled", false);
					else
						$("#cobertura_"+ filterCoberturasDependen[index].configuracionCoberturaId).prop("disabled", true);
					break;
				case 6: //De Equipos y Herramientas
					if(valHer>0)
						$("#cobertura_"+ filterCoberturasDependen[index].configuracionCoberturaId).prop("disabled", false);
					else
						$("#cobertura_"+ filterCoberturasDependen[index].configuracionCoberturaId).prop("disabled", true);
					break;
				case 7: //De Insumos Médicos
					if(valIns>0)
						$("#cobertura_"+ filterCoberturasDependen[index].configuracionCoberturaId).prop("disabled", false);
					else
						$("#cobertura_"+ filterCoberturasDependen[index].configuracionCoberturaId).prop("disabled", true);
					break;
				case 8: //De Contenidos
					if(valMue + valMaq + valMer + valHer + valIns>0)
						$("#cobertura_"+ filterCoberturasDependen[index].configuracionCoberturaId).prop("disabled", false);
					else{
						if(valCon>0)
							$("#cobertura_"+ filterCoberturasDependen[index].configuracionCoberturaId).prop("disabled", false);
						else
							$("#cobertura_"+ filterCoberturasDependen[index].configuracionCoberturaId).prop("disabled", true);
					}				
					
					break;
			}
		});
		
		// Busco todas las coberturas que son predeterminadas para generarlas automáticamente
		var filterResult = $(configCobertasPorProducto).filter(function(idx) {
			return configCobertasPorProducto[idx].incluyeEnProducto == "1";
		});
		$.each(filterResult, function(index) {
			$("#deducible_cobertura_"+ filterResult[index].configuracionCoberturaId).remove();
			if (filterResult[index].tipoDeclaracion == "2") {
				//determina la tasa de acuerdo al id de la configuración
				var tasa = determinarTasa(filterResult[index].configuracionCoberturaId);
				if (tasa > 0) {
					var valorMaximo = determinarValorMaximo(filterResult[index].configuracionCoberturaId);
					crearCobertura(filterResult[index].configuracionCoberturaId, "direccion");
					if(Number($("#total_valor_asegurado").val())>Number(valorMaximo))
						$("#monto_asegurado_"+ filterResult[index].configuracionCoberturaId).val(Number(valorMaximo));
					else
						$("#monto_asegurado_"+ filterResult[index].configuracionCoberturaId).val(Number($("#total_valor_asegurado").val()));
					$("#monto_asegurado_"+ filterResult[index].configuracionCoberturaId).prop('disabled', true);
					calcularPrimaNeta(filterResult[index].configuracionCoberturaId);
				} else {
					if (typeof filterResult[index].textoDeducible !== "undefined") {
						var contadorDeducibles=0;
						$(".deducibleIDsDir").each(function() {
							var deducibleId = $(this).val();
							if(deducibleId==filterResult[index].configuracionCoberturaId)
								contadorDeducibles++;
						});
						
						if(contadorDeducibles==0){
							$("#deducibles_por_direccion").append(
									'<tr id="deducible_cobertura_'
									+ filterResult[index].configuracionCoberturaId
									+ '">'
									+ '<td><table><tr><td style="font-weight:bold">Deducible:&nbsp;'
									+ filterResult[index].coberturaNombre
									+ '<input type="hidden" class="deducibleIDsDir" id="deducible_cobertura_'+filterResult[index].configuracionCoberturaId
									+ '" value="'+filterResult[index].configuracionCoberturaId
									+ '"></td></tr><tr>'
									+ '<td><textarea disabled id="text_area_deducible_cobertura_'
									+ filterResult[index].configuracionCoberturaId
									+ '" rows="2" cols="100">'
									+ filterResult[index].textoDeducible
									+ '</textarea><input type="hidden" id="ids_deducible_cobertura_'
									+ filterResult[index].configuracionCoberturaId
									+ '" value="' + filterResult[index].idsDeducible
									+ '"><input type="hidden" id="valores_deducible_cobertura_'
									+ filterResult[index].configuracionCoberturaId
									+ '" value="' + filterResult[index].valoresDeducible
									+ '"><input type="hidden" id="textos_deducible_cobertura_'
									+ filterResult[index].configuracionCoberturaId
									+ '" value="'+filterResult[index].textosDeducible
									+ '"></td></tr></table></td></tr>');
						}
					}
				}
			} else {
				if (Number(filterResult[index].tasa) > 0) {
					crearCoberturaGeneral(
							filterResult[index].configuracionCoberturaId,
							"general");
					$("#monto_asegurado_" + filterResult[index].configuracionCoberturaId).val(Number($("#total_valor_asegurado").val()));
					$("#monto_asegurado_" + filterResult[index].configuracionCoberturaId).prop('disabled', true);
					calcularPrimaNeta(filterResult[index].configuracionCoberturaId);
				} else {
					if (typeof filterResult[index].textoDeducible !== "undefined") {
						var contadorDeducibles=0;
						$(".deducibleIDs").each(function() {
							var deducibleId = $(this).val();
							if(deducibleId==filterResult[index].configuracionCoberturaId)
								contadorDeducibles++;
						});
						
						if(contadorDeducibles==0){
							$("#deducibles_general")
							.append('<tr id="deducible_cobertura_General_'
									+ filterResult[index].configuracionCoberturaId
									+ '">'
									+ '<td><table><tr><td style="font-weight:bold">Deducible:&nbsp;'
									+ filterResult[index].coberturaNombre
									+ '<input type="hidden" class="deducibleIDs" id="deducible_cobertura_General_'+filterResult[index].configuracionCoberturaId
									+ '" value="'+filterResult[index].configuracionCoberturaId
									+ '"></td></tr><tr>'
									+ '<td><textarea disabled id="text_area_deducible_cobertura_'
									+ filterResult[index].configuracionCoberturaId
									+ '" rows="2" cols="100">'
									+ filterResult[index].textoDeducible
									+ '</textarea><input type="hidden" id="ids_deducible_cobertura_'
									+ filterResult[index].configuracionCoberturaId
									+ '" value="' + filterResult[index].idsDeducible
									+ '"><input type="hidden" id="valores_deducible_cobertura_'
									+ filterResult[index].configuracionCoberturaId
									+ '" value="' + filterResult[index].valoresDeducible 
									+ '"><input type="hidden" id="textos_deducible_cobertura_'
									+ filterResult[index].configuracionCoberturaId
									+ '" value="'+filterResult[index].textosDeducible
									+ '"></td></tr></table></td></tr>');
						}
					}
				}
			}
		});
	}
}

function validarMaximoEmbarque(configuracionCoberturaId, montomaximo) {
	var p2 = Number($("#valor_adicional_" + configuracionCoberturaId).val());
	if (!p2){
		alert("No es un valor válido");
		return;
	}
	
	if (p2<0){
		alert("No es un valor válido");
		return;
	}
	
	if (montomaximo){
		if (montomaximo != -1 && montomaximo != 0){
			if (p2 > montomaximo){
				alert('Valor ingresado es mayor al Monto Máximo permitido');
				$("#valor_adicional_" + configuracionCoberturaId).val(montomaximo);
			}
		}
	}
}

function grabarDireccion() {
	if($("#actividad_economica").val()==""){
		alert("Debe seleccionar una actividad económica.");
		return;
	}
	if($("#giro_negocio").val()==""){
		alert("Debe seleccionar un giro de negocio.");
		return;
	}
	if($("#ubicacion_sector").val()==""){
		alert("Debe ingresar un sector.");
		return;
	}
	if($("#total_valor_asegurado").val()==""){
		alert("El total del valor asegurado debe ser mayor a 0.");
		return;
	}
	//Valido en relación a si el campo esta activo o no
	if($("#fila_contabilidad").css("display")=="table-row"){
		if($("#contabilidad_formal_si").is(':checked')==false){
			alert("El cliente debe tener contabilidad formal, no es posible seguir cotizando");
			return;
		}
	}
	if($("#fila_constitucion").css("display")=="table-row"){
		if($("#tiene_mas_dos_anios_si").is(':checked')==false){
			alert("El cliente debe tener más de dos años, no es posible seguir cotizando");
			return;
		}
	}
	if($("#fila_seguridad").css("display")=="table-row"){
		if($("#seguridad_adecuada_si").is(':checked')==false && $("#seguridad_adecuada_no").is(':checked')==false){
			alert("Debe indicar si tiene o no seguridad adecuada.");
			return;
		}
	}
	
	if(Number($("#total_valor_asegurado").val())>Number(parametroGrupoProducto.limiteAsegurado)){
		alert("El total de valores asegurados no deben ser mayores al límite de asegurado de incendio. Límite actual: "+formatDollar(parametroGrupoProducto.limiteAsegurado));
		return;
	}
	
	var contadorCoberturaConError=0;
	$(".datosMontoAseguradoDir").each(function() {
		if($(this).val()=="" || $(this).val()=="0"){
			contadorCoberturaConError++;
		}else{
			var p2 = Number($(this).val());
			if (!p2)
				contadorCoberturaConError++;
		}
	});
	if(contadorCoberturaConError>0){
		alert("No se puede grabar la configuración de la dirección, porque existe coberturas sin monto asegurado, monto no válido o con monto asegurado igual a 0");
		return;
	}
	
	
	var coberturas = new Array();
	var contador = 0;
	var cotizacionId = $("#cotizacion_id").html().trim();
	$.each(configCobertasPorProducto,
			function(index) {
		if(configCobertasPorProducto[index].tipoDeclaracion=="2"){
			if ($("#cobertura_"+ configCobertasPorProducto[index].configuracionCoberturaId).is(':checked')) {
				var tasaSugerida = 0;
				var tasaIngresada = 0;
				var montoAsegurado = 0;
				var primaCalculada = 0;
				var valorAdicional = 0;
				var planGeneral = 0;
				if ($("#tasa_sugerida_"+ configCobertasPorProducto[index].configuracionCoberturaId).length)
					tasaSugerida = Number($("#tasa_sugerida_"+ configCobertasPorProducto[index].configuracionCoberturaId).val());
				if ($("#tasa_ingresada_"+ configCobertasPorProducto[index].configuracionCoberturaId).length)
					tasaIngresada = Number($("#tasa_ingresada_"+ configCobertasPorProducto[index].configuracionCoberturaId).val());
				if ($("#monto_asegurado_"+ configCobertasPorProducto[index].configuracionCoberturaId).length)
					montoAsegurado = Number($("#monto_asegurado_"+ configCobertasPorProducto[index].configuracionCoberturaId).val());
				if ($("#prima_neta_"+ configCobertasPorProducto[index].configuracionCoberturaId).length)
					primaCalculada = Number($("#prima_neta_"+ configCobertasPorProducto[index].configuracionCoberturaId).val());
				
				if($("#valor_adicional_"+ configCobertasPorProducto[index].configuracionCoberturaId))
					if ($("#valor_adicional_"+ configCobertasPorProducto[index].configuracionCoberturaId).length)
						valorAdicional = Number($("#valor_adicional_"+ configCobertasPorProducto[index].configuracionCoberturaId).val());
				var cobertura = {
						configuracionCoberturaId : configCobertasPorProducto[index].configuracionCoberturaId,
						tasaSugerida : tasaSugerida,
						tasaIngresada : tasaIngresada,
						valorIngresado : montoAsegurado,
						primaCalculada : primaCalculada,
						tipoOrigen : "POR DIRECCION",
						textoDeducible : "",
						idsDeducible : "",
						valoresDeducible : "",
						textosDeducible : "",
						planGeneral :"",
						valorAdicional : valorAdicional
				};
				coberturas[contador] = cobertura;
				contador++;
			}
		}
	});
	
	
	//Lleno el objeto de los deducibles
	$(".deducibleIDsDir").each(function() {
		var deducibleId = $(this).val();
		var textoDeducible = $("#text_area_deducible_cobertura_"+deducibleId).val();
		var idsDeducible = $("#ids_deducible_cobertura_"+deducibleId).val();
		var valoresDeducible = $("#valores_deducible_cobertura_"+deducibleId).val();
		var textosDeducible = $("#textos_deducible_cobertura_"+deducibleId).val();
		var cobertura = {
				configuracionCoberturaId : deducibleId,
				tasaSugerida : 0,
				tasaIngresada : 0,
				valorIngresado : 0,
				primaCalculada : 0,
				tipoOrigen : "DEDUCIBLE DIRECCION",
				textoDeducible : textoDeducible,
				idsDeducible : idsDeducible,
				valoresDeducible : valoresDeducible,
				textosDeducible : textosDeducible,
				planGeneral : "",
				valorAdicional : 0	
		};
		coberturas[contador] = cobertura;
		contador++;
	});
	//riesgo
	var zonaRiego="NO";
	var tipoRiesgo="0";
	var valorRiesgo="0";
	if(existeRiesgoVolcan=="SI"){
		zonaRiego="SI";
		tipoRiesgo="1";
		valorRiesgo=$("#distanciaRiesgo").val(); 
	}
	if(existeRiesgoCosta=="SI"){
		zonaRiego="SI";
		tipoRiesgo="2";
		valorRiesgo=$("#riesgoDanio_si").is(":checked") ? "SI" : "NO";
	}
		
	var direccionActualId = $("#numeroDireccion").val();
	$.ajax({
		url : '../PymesObjetoCotizacionController',

		data : {
			"tipoConsulta" : "crearDireccion",
			"cotizacionId" : cotizacionId,
			"cotizacionDetalleId" : $("#cotizacionDetalleId").val(),
			"provinciaId" : $("#ubicacion_provincia_" + direccionActualId).val(),
			"cantonId" : $("#ubicacion_canton_" + direccionActualId).val(),
			"callePrincipal" : $("#ubicacion_calle_principal_" + direccionActualId).val(),
			"numeroDireccion" : $("#ubicacion_numero_" + direccionActualId).val(),
			"calleSecundaria" : $("#ubicacion_calle_secundaria_" + direccionActualId).val(),
			"actividadEconomicaId" : $("#actividad_economica option:selected").val(),
			"tipoPredioId" : $("#giro_negocio option:selected").val(),
			"tieneMasDosAnios" : $("#tiene_mas_dos_anios_si").is(":checked") ? "true" : "false",
			"contabilidadFormal" : $("#contabilidad_formal_si").is(":checked") ? "true" : "false",
			"requiereInspeccion" : $("#requiere_inspeccion_si").is(":checked") ? "true" : "false",
			"sector" : $("#ubicacion_sector").val(),
			"nombreEdificio" : $("#ubicacion_nombre_edificio").val(),
			"numeroOficina" : $("#ubicacion_numero_oficina").val(),
			"valorEstructuras" : $("#valor_estructuras").val(),
			"valorMueblesEnseres" : $("#valor_muebles_enseres").val(),
			"valorMaquinaria" : $("#valor_maquinarias").val(),
			"valorMercaderia" : $("#valor_mercaderia").val(),
			"valorInsumosNoelectronicos" : $("#valor_insumos_medicos").val(),
			"valorEquipoHerramienta" : $("#valor_herramientas").val(),
			"valorContenidos" : $("#valor_contenidos").val(),
			"seguridadAdecuada" : $("#seguridad_adecuada_si").is(":checked") ? "true" : "false",
			"coberturas" : JSON.stringify(coberturas),
			"anioConstruccion" : $("#anio_construccion").val(),
			"numeroTotalPisos" : $("#numero_total_pisos").val(),
			"tipoConstruccionId" : $("#tipo_construccion").val(),
			"zonaRiego" : zonaRiego,
			"tipoRiesgo" :tipoRiesgo,
			"valorRiesgo" :valorRiesgo,
			//riesgo
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			if(data.success){
				$("#cotizacionDetalleId_" + direccionActualId).val(data.cotizacionDetalleId);
				$('#add').modal('hide');
			}
			else{
				alert("Se produjo un error al grabar la configuración de la dirección");
			}
		}
	});
}

function verificarPuntosVenta(puntoVentaId) {

	var puntoId = "";
	if (puntoVentaId != null && puntoVentaId != '')
		puntoId = puntoVentaId;
	else
		puntoId = $('#punto_venta').val();
	$.ajax({
		url : '../PuntoVentaController',
		data : {
			"tipoConsulta" : "verificacionPuntoVenta",
			"tipoObjeto" : tipoObjeto,
			"puntoVentaId" : puntoId,

		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			if (data.punto_venta.agente_id == null
					|| data.punto_venta.agente_id == "") {
				$('#agentes').prop("disabled", false);
				$("#agentes option:first").attr('selected', 'selected');
			} else {

				$('#agentes option[value=' + data.punto_venta.agente_id + ']')
				.attr('selected', 'selected');
				// $('#agentes').val(data.punto_venta.agente_id);
				$('#agentes').prop("disabled", true);
			}
		}
	});
}



/* METODOS DE CALCULOS */
function calcularIngresosEgresosNatural() {

	var salario = Number($("#salario_mensual_natural").val());
	var otrosIngresos = Number($("#otros_ingresos_natural").val());
	var egresos = Number($("#egresos_natural").val());

	$("#ingresos_egresos_natural").val((salario + otrosIngresos) - egresos);

}

function calcularPatrimonioNatural() {

	var activos = Number($("#activos_natural").val());
	var pasivos = Number($("#pasivos_natural").val());

	$("#patrimonio_natural").val(activos - pasivos);

}

function calcularIngresosEgresosJuridica() {

	var salario = Number($("#salario_mensual_juridica").val());
	var otrosIngresos = Number($("#otros_ingresos_juridica").val());
	var egresos = Number($("#egresos_juridica").val());

	$("#ingresos_egresos_juridica").val((salario + otrosIngresos) - egresos);

}

function calcularPatrimonioJuridica() {

	var activos = Number($("#activos_juridica").val());
	var pasivos = Number($("#pasivos_juridica").val());

	$("#patrimonio_juridica").val(activos - pasivos);

}

/** METODOS PARA LA DESCARGA DE LOS REPORTES* */
function cambioDescargaCertificados() {

	var valor = $("#selectDescargas").val();
	if (valor == 1) {
		$(".descargaCertificado").fadeOut("slow");
		$("#descargar_FichaCotizacion").fadeIn("slow");
	}
	if (valor == 2) {
		$(".descargaCertificado").fadeOut("slow");
		$("#descargar_certificadoCotizacion").fadeIn("slow");
	}
	if (valor == 3) {
		$(".descargaCertificado").fadeOut("slow");
		$("#descargar_certificadoNormasParticulares").fadeIn("slow");

	}
	/*
	 * if (valor == 4) { $(".descargaCertificado").fadeOut("slow");
	 * $("#descargar_certificadoUPLA").fadeIn("slow"); } if (valor == 5) {
	 * $(".descargaCertificado").fadeOut("slow");
	 * $("#descargar_certificadoPoliza").fadeIn("slow"); }
	 */

}

function abrirFichaCotizacion() {
	var cotizacion = $("#cotizacion_id").html().trim();

	var parametros = {
			"parametros" : {
				"cot_Id" : cotizacion
			},
			"pathReporte" : "/static/reportes/Ganadero/cabecera.jasper"
	};
	abrirReporte('POST', '../ReportesController', parametros, "_blank");
}

function abrirCertificadoCotizacion() {
	var cotizacion = $("#cotizacion_id").html().trim();

	var parametros = {
			"parametros" : {
				"Cot_Id" : cotizacion
			},
			"pathReporte" : "/static/reportes/Ganadero/CotiVacas2KR2012.jasper"
	};
	abrirReporte('POST', '../ReportesController', parametros, "_blank");
}

function abrirCertificadoNormaParticulares() {
	var cotizacion = $("#cotizacion_id").html().trim();

	var parametros = {
			"parametros" : {
				"cot_Id" : cotizacion
			},
			"pathReporte" : "/static/reportes/Ganadero/CondicionesParticulares.jasper"
	};
	abrirReporte('POST', '../ReportesController', parametros, "_blank");
}
/*
 * METODO QUE MUESTRA O ESCONDE LOS CAMPOS DEL FORMULARIO DE LA UPLA DEPENDIENDO
 * DEL TIPO DE IDENTIFICACION QUE SELECCIONE EL USUARIO
 */
function mostrarTipoIdentificacionSolicitante() {
	var tipoIdentificacionId = $("#tipo_identificacion_principal").val();
	if (tipoIdentificacionId == '' || tipoIdentificacionId == '1'
		|| tipoIdentificacionId == '2' || tipoIdentificacionId == '3') {
		$("#nombre_completo").val("");
	} else {
		$("#nombres").val("");
		$("#apellidos").val("");
	}

	if (tipoIdentificacionId == '' || tipoIdentificacionId == '1'
		|| tipoIdentificacionId == '3' || tipoIdentificacionId == '4') {
		$("#identificacion").attr("onkeypress",
		"validarKeyPress(event,/[0-9]/);");

	} else {
		$("#identificacion").attr("onkeypress",
		"validarSoloLetrasNumeros(event);");
	}
}

function mostrarTipoIdentificacionAsegurado() {
	var tipoIdentificacionId = $("#tipo_identificacion_asegurado").val();
	if (tipoIdentificacionId == '' || tipoIdentificacionId == '1'
		|| tipoIdentificacionId == '2' || tipoIdentificacionId == '3') {
		$("#nombre_completo_asegurado").val("");
		$("#ubicacionPersonaNatural").show();
		$("#datosPoliticaNatural").show();
		$("#situacionFinancieraNatural").show();
		$("#ubicacionPersonaJuridica").hide();
		$("#datosPoliticaJuridica").hide();
		$("#situacionFinancieraJuridica").hide();
	} else {
		$("#nombres_asegurado").val("");
		$("#apellidos_asegurado").val("");
		$("#ubicacionPersonaNatural").hide();
		$("#datosPoliticaNatural").hide();
		$("#situacionFinancieraNatural").hide();
		$("#ubicacionPersonaJuridica").show();
		$("#datosPoliticaJuridica").show();
		$("#situacionFinancieraJuridica").show();
	}

	if (tipoIdentificacionId == '' || tipoIdentificacionId == '1'
		|| tipoIdentificacionId == '3' || tipoIdentificacionId == '4') {
		$("#identificacion_asegurado").attr("onkeypress",
		"validarKeyPress(event,/[0-9]/);");

	} else {
		$("#identificacion_asegurado").attr("onkeypress",
		"validarSoloLetrasNumeros(event);");
	}
}

function cargarPestanaEndosoAsegurado(identificacion){
	cargarTiposIdentificacionPymes("", "asegurado", false);
	if (identificacion == null || identificacion == "") {
		$("#identificacion_asegurado").val($("#identificacion").val()).trigger(
		'change');
	} else {
		$("#identificacion_asegurado").val(identificacion);
		cargarPorIdentificacion("datosAsegurado", identificacion);
	}
}

function cargarPestanaEndosoBeneficiario(monto, beneficiario, rubro, endosoBeneficiarioId) {
	nuevoBeneficiario(endosoBeneficiarioId);
	if (monto == null || monto == "") {
		$("#valor_endoso_beneficiario_"+endosoBeneficiarioId).val(0);
	} else {
		$("#valor_endoso_beneficiario_"+endosoBeneficiarioId).val(monto);
	}
	if (beneficiario != null && beneficiario != '') {
		$('#beneficiario_'+endosoBeneficiarioId).val(beneficiario);
	}
	if (rubro != null && rubro != '') {
		$('#beneficiario_rubro_'+endosoBeneficiarioId).val(rubro);
	}
	$('#endoso_beneficiario_id_'+endosoBeneficiarioId).val(endosoBeneficiarioId);
}

/*
 * METODO QUE RECIBE EL ID DEL PUNTO DE VENTA Y SETEA EL CORRESPONDIENTE AL ID
 * QUE RECIBE.
 */
function obtenerPuntosVentaPorProducto(seleccionada, productoActualID) {
	var productoid = "";
	if (productoActualID != null && productoActualID != '')
		productoid = productoActualID;
	else
		productoid = $('#productos').val();
	$
	.ajax({
		url : '../PuntoVentaController',
		data : {
			"tipoConsulta" : "puntosVentaXProducto",
			"tipoObjeto" : tipoObjeto,
			"grupoPorProductoId" : productoid,

		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			var sucursales = data.sucursales;
			var options = '';
			options = '<option value="">Seleccione un punto de venta</option>';
			var contador;
			for (var j = 0; j < sucursales.length; j++) {
				contador = 0;
				for (var i = 0; i < data.puntosVenta.length; i++) {
					if (data.puntosVenta[i].sucursal == sucursales[j].id) {
						contador++;
						if (contador == 1) {
							$("#productos")
							.append(
							"<option value=''>Seleccione una opcion</option>");
							options += '<option value="" disabled="disabled" class="seleccionado" ">'
								+ sucursales[j].nombre
								+ '</option>';
						}
						//if(data.puntosVenta[i].nombre!="ESMERALDAS" && data.puntosVenta[i].nombre!="MANTA OFICINA" && data.puntosVenta[i].nombre!="PORTOVIEJO" ){
							options += '<option value="'
								+ data.puntosVenta[i].id
								+ '" pxpv="'
								+ data.puntosVenta[i].productoPorPuntoDeVenta
								+ '"  >&nbsp;&nbsp;&nbsp;&nbsp;'
								+ data.puntosVenta[i].nombre
								+ '</option>';
						//}
					}
				}
			}
			$("#punto_venta").html(options);
			if (seleccionada != '') {
				$("#punto_venta").val(seleccionada);
				verificarPuntosVenta(seleccionada);
			}
		}
	});
}


function cargarActividadesEconomicas(producto) {
	actividadEconomicaList.value([]);
	//Consultar la provincia
	$.ajax({
		url : '../ActividadEconomicaController',
		data : {
			"tipoConsulta" : "encontrarPorGrupoProductoId",
			"grupoProductoId" : producto,
		},
		async: false,
		type : 'post',
		datatype : 'json',
		success : function (data) {
			actividadEconomicaList.dataSource.filter({});
			actividadEconomicaList.setDataSource(data.listadoActividadesEconomicas);
		}
	});
	
}

function obtenerProductosPorGrupoProducto(producto) {
	// Consultar listado de productos dentro de un grupos de productos
	$("#productos").empty();

	$
	.ajax({
		url : '../GrupoPorProductoController',

		data : {
			"tipoConsulta" : "encontrarTodosPorGrupo",
			"tipoObjeto" : tipoObjeto,
			"grupoProductoId" : $("#grupo_productos").val()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			var listadoGrupos = data.listadoGruposPorProducto;
			$("#productos").append(
			"<option value=''>Seleccione una opcion</option>");
			$
			.each(
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
			if (producto != '') {
				$("#productos").val(producto);
				obtenerConfiguracionesCoberturaProducto(producto);
				obtenerAsistenciasPorProducto(producto);
				cargarActividadesEconomicas(producto);
				obtenerPuntosVentaPorProducto($("#puntoVentaSeleccionado").val(), producto);
				configurarValoresMostrarPorProducto(producto);
			}
		}
	});
}

function configurarValoresMostrarPorProducto(productoId) {
	$.ajax({
		url : '../PymeParametroXGrupoPorProductoController',
		data : {
			"tipoConsulta" : "obtenerPorProductoId",
			"parametroGrupoProductoId" : productoId
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			parametroGrupoProducto=data;
			if (data.mostrarValorEstructuras == "1")
				$("#fila_estructuras").show();
			if (data.mostrarValorMueblesEnseres == "1")
				$("#fila_muebles_enseres").show();
			if (data.mostrarMaquinaria == "1")
				$("#fila_maquinaria").show();
			if (data.mostrarValorMercaderia == "1")
				$("#fila_mercaderia").show();
			if (data.mostrarValorInsumos == "1")
				$("#fila_insumos_medicos").show();
			if (data.mostrarValorEquipoHerramienta == "1")
				$("#fila_equipos_herramienta").show();
			if (data.mostrarValorContenidos == "1")
				$("#fila_contenidos").show();

		}
	});
}

function obtenerCantonPorProvincia(seleccionada, codigoDireccion) {
	$("#ubicacion_canton_"+codigoDireccion).empty();
	// Consultar la provincia
	$.ajax({
		url : '../CantonController',
		data : {
			"tipoConsulta" : "encontrarPorProvincia",
			"provincia" : $("#ubicacion_provincia_"+codigoDireccion).val()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			var listadoCantones = data.cantones;
			$("#ubicacion_canton_"+codigoDireccion).append("<option value=''>Seleccione un Canton</option>");
			$.each(listadoCantones, function(index) {
				var id = undefined;
				if (listadoCantones[index].codigo == undefined) {
					id = listadoCantones[index].id;
				} else {
					id = listadoCantones[index].codigo;
				}
				//TODO: No mostrar provincias
				if($("#productos").val()=="214" || $("#productos").val()=="221" || $("#productos").val()=="190"){
					if($("#ubicacion_provincia_"+codigoDireccion).val()=='9'){
						if(listadoCantones[index].nombre == 'PLAYAS'){
							$("#ubicacion_canton_"+codigoDireccion).append("<option value='" + id + "'>" + listadoCantones[index].nombre + "</option>");
						}
					}
					else{
						$("#ubicacion_canton_"+codigoDireccion).append("<option value='" + id + "'>" + listadoCantones[index].nombre + "</option>");
					}
				}
				else if($("#productos").val()=="215" || $("#productos").val()=="218"){
					if(listadoCantones[index].nombre != 'PLAYAS'){
						$("#ubicacion_canton_"+codigoDireccion).append("<option value='" + id + "'>" + listadoCantones[index].nombre + "</option>");
					}
				}
				else {
					$("#ubicacion_canton_"+codigoDireccion).append("<option value='" + id + "'>" + listadoCantones[index].nombre + "</option>");
				}	
			});
			$("#ubicacion_canton_"+codigoDireccion).val(seleccionada);
		}
	});
}

function obtenerParroquiaPorCanton(seleccionada) {
	$("#ubicacion_parroquia1").empty();
	$.ajax({
		url : '../ParroquiaController',
		data : {
			"tipoConsulta" : "encontrarPorCanton",
			"canton" : $("#ubicacion_canton1").val()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			var listadoParroquias = data.listadoParroquia;
			$("#ubicacion_parroquia1").append(
			"<option value=''>Seleccione una Parroquia</option>");
			$.each(listadoParroquias, function(index) {
				var id = undefined;
				if (listadoParroquias[index].codigo == undefined) {
					id = listadoParroquias[index].id;
				} else {
					id = listadoParroquias[index].codigo;
				}

				$("#ubicacion_parroquia1")
				.append(
						"<option value='" + id + "'>"
						+ listadoParroquias[index].nombre
						+ "</option>");
			});
			$("#ubicacion_parroquia1").val(seleccionada);
		}
	});
}

function obtenerCiudadesPorProvinciaPNatural(seleccionada) {
	$("#ciudad_direccion_cliente_natural").empty();
	$.ajax({
		url : '../CiudadController',
		data : {
			"tipoConsulta" : "encontrarPorProvincia",
			"provincia" : $("#provincia_direccion_cliente_natural").val()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(datos) {
			var listadoCiudades = datos.ciudades;
			$("#ciudad_direccion_cliente_natural").append(
			"<option value=''>Seleccione una Ciudad</option>");
			$.each(listadoCiudades, function(index) {
				var id = undefined;
				if (listadoCiudades[index].codigo == undefined) {
					id = listadoCiudades[index].id;
				} else {
					id = listadoCiudades[index].codigo;
				}
				if(listadoCiudades[index].nombre!='SANTA ELENA'){
					$("#ciudad_direccion_cliente_natural").append(
						"<option value='" + id + "'>"
						+ listadoCiudades[index].nombre + "</option>");
				}
			});
			$("#ciudad_direccion_cliente_natural").val(seleccionada);
		}
	});
}

function obtenerCiudadesPorProvinciaPJuridica(seleccionada) {
	$("#ciudad_direccion_matriz_juridica").empty();
	$.ajax({
		url : '../CiudadController',
		data : {
			"tipoConsulta" : "encontrarPorProvincia",
			"provincia" : $("#provincia_direccion_matriz_juridica").val()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(datos) {

			var listadoCiudades = datos.ciudades;
			$("#ciudad_direccion_matriz_juridica").append("<option value=''>Seleccione una Ciudad</option>");
			$.each(listadoCiudades, function(index) {
				$("#ciudad_direccion_matriz_juridica").append("<option value='" + listadoCiudades[index].id + "'>" + listadoCiudades[index].nombre + "</option>");
			});
			$("#ciudad_direccion_matriz_juridica").val(seleccionada);
		}
	});
}

function obtenerCantonPorProvinciaPNatural(seleccionada) {
	$("#canton_direccion_cliente_natural").empty();
	// Consultar la provincia
	$.ajax({
		url : '../CantonController',
		data : {
			"tipoConsulta" : "encontrarPorProvincia",
			"provincia" : $("#provincia_direccion_cliente_natural").val()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			var listadoCantones = data.cantones;
			$("#canton_direccion_cliente_natural").append(
			"<option value=''>Seleccione un Canton</option>");
			$.each(listadoCantones, function(index) {
				var id = undefined;
				if (listadoCantones[index].codigo == undefined) {
					id = listadoCantones[index].id;
				} else {
					id = listadoCantones[index].codigo;
				}

				$("#canton_direccion_cliente_natural").append(
						"<option value='" + id + "'>"
						+ listadoCantones[index].nombre + "</option>");
			});
			$("#canton_direccion_cliente_natural").val(seleccionada);
		}
	});
}

function obtenerCantonPorProvinciaPJuridica(seleccionada) {
	$("#canton_direccion_matriz_juridica").empty();
	// Consultar la provincia
	$.ajax({
		url : '../CantonController',
		data : {
			"tipoConsulta" : "encontrarPorProvincia",
			"provincia" : $("#provincia_direccion_matriz_juridica").val()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			var listadoCantones = data.cantones;
			$("#canton_direccion_matriz_juridica").append(
			"<option value=''>Seleccione un Canton</option>");
			$.each(listadoCantones, function(index) {
				var id = undefined;
				if (listadoCantones[index].codigo == undefined) {
					id = listadoCantones[index].id;
				} else {
					id = listadoCantones[index].codigo;
				}

				$("#canton_direccion_matriz_juridica").append(
						"<option value='" + id + "'>"
						+ listadoCantones[index].nombre + "</option>");
			});
			$("#canton_direccion_matriz_juridica").val(seleccionada);
		}
	});
}

function obtenerParroquiaPorCantonPNatural(seleccionada) {
	$("#parroquia_direccion_cliente_natural").empty();
	$.ajax({
		url : '../ParroquiaController',
		data : {
			"tipoConsulta" : "encontrarPorCanton",
			"canton" : $("#canton_direccion_cliente_natural").val()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			var listadoParroquias = data.listadoParroquia;
			$("#parroquia_direccion_cliente_natural").append(
			"<option value=''>Seleccione una Parroquia</option>");
			$.each(listadoParroquias, function(index) {
				var id = undefined;
				if (listadoParroquias[index].codigo == undefined) {
					id = listadoParroquias[index].id;
				} else {
					id = listadoParroquias[index].codigo;
				}

				$("#parroquia_direccion_cliente_natural")
				.append(
						"<option value='" + id + "'>"
						+ listadoParroquias[index].nombre
						+ "</option>");
			});
			$("#parroquia_direccion_cliente_natural").val(seleccionada);
		}
	});
}

function obtenerParroquiaPorCantonPJuridica(seleccionada) {
	$("#parroquia_direccion_matriz_juridica").empty();
	$.ajax({
		url : '../ParroquiaController',
		data : {
			"tipoConsulta" : "encontrarPorCanton",
			"canton" : $("#canton_direccion_matriz_juridica").val()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			var listadoParroquias = data.listadoParroquia;
			$("#parroquia_direccion_matriz_juridica").append(
			"<option value=''>Seleccione una Parroquia</option>");
			$.each(listadoParroquias, function(index) {
				var id = undefined;
				if (listadoParroquias[index].codigo == undefined) {
					id = listadoParroquias[index].id;
				} else {
					id = listadoParroquias[index].codigo;
				}

				$("#parroquia_direccion_matriz_juridica")
				.append(
						"<option value='" + id + "'>"
						+ listadoParroquias[index].nombre
						+ "</option>");
			});
			$("#parroquia_direccion_matriz_juridica").val(seleccionada);
		}
	});
}

function cambiaZonaDireccion(event, tipo) {
	var target = event.target || event.srcElement;
	var seleccionada = $(target).val();
	if (seleccionada == "U") {
		if (tipo == 'N') {
			$("#ciudad_cliente_label").fadeIn();
			$("#ciudad_cliente_select").fadeIn();
			$("#canton_cliente_label").fadeOut();
			$("#canton_cliente_select").fadeOut();
			$("#parroquia_cliente_label").fadeOut();
			$("#parroquia_cliente_select").fadeOut();
		}
		if (tipo == 'J') {
			$("#ciudad_matriz_label").fadeIn();
			$("#ciudad_matriz_select").fadeIn();
			$("#canton_matriz_label").fadeOut();
			$("#canton_matriz_select").fadeOut();
			$("#parroquia_matriz_label").fadeOut();
			$("#parroquia_matriz_select").fadeOut();
		}
	}
	if (seleccionada == "R") {
		if (tipo == 'N') {
			$("#canton_cliente_label").fadeIn();
			$("#canton_cliente_select").fadeIn();
			$("#parroquia_cliente_label").fadeIn();
			$("#parroquia_cliente_select").fadeIn();
			$("#ciudad_cliente_label").fadeOut();
			$("#ciudad_cliente_select").fadeOut();
		}
		if (tipo == 'J') {
			$("#canton_matriz_label").fadeIn();
			$("#canton_matriz_select").fadeIn();
			$("#parroquia_matriz_label").fadeIn();
			$("#parroquia_matriz_select").fadeIn();
			$("#ciudad_matriz_label").fadeOut();
			$("#ciudad_matriz_select").fadeOut();
		}
	}
}

function limpiarControles(){

	var tabStrip = $("#tabstrip").kendoTabStrip().data("kendoTabStrip");
	tabStrip.enable(tabStrip.tabGroup.children().eq(2), false);
	
	$("#detalle_cobertura_direccion tr").remove();
	$("#actividad_economica").data("kendoMultiSelect").value(null);
	$("#ubicacion_sector").val("");
	$("#ubicacion_nombre_edificio").val("");
	$("#ubicacion_numero_oficina").val("");
	$("#giro_negocio").data("kendoMultiSelect").value(null);
	
	$("#numero_total_pisos").val("");
	$("#anio_construccion").val("");
	
	$("#tiene_mas_dos_anios_si").prop("checked",false);
	$("#tiene_mas_dos_anios_no").prop("checked",false);
	$("#contabilidad_formal_si").prop("checked",false);
	$("#contabilidad_formal_no").prop("checked",false);
	$("#requiere_inspeccion_si").prop("checked",false);
	$("#requiere_inspeccion_no").prop("checked",false);

	//riesgo
	$("#riesgoDanio_si").prop("checked",false);
	$("#riesgoDanio_no").prop("checked",false);
	$("#distanciaRiesgo").val("");
	var riesgoSierra=$("#distanciaRiesgo").data("kendoNumericTextBox");
	riesgoSierra.enable(true);
	var riesgoCostaSi=$("#riesgoDanio_si");
	riesgoCostaSi.removeAttr('disabled');;
	var riesgoCostaNo=$("#riesgoDanio_no");
	riesgoCostaNo.removeAttr('disabled');
	//inicializo los riesgo
	$("#riesgoDanio_si").prop( "checked", true );
	$("#distanciaRiesgo").val(0);
	
	
	$("#riesgoDanioColumn").hide();
	$("#riesgoVolcanColumn").hide();
	
	$("#prima_neta_por_direccion").text("");

	var numerictextbox1 = $("#valor_estructuras").data("kendoNumericTextBox");
	numerictextbox1.value(null);
	numerictextbox1.enable(true);

	var numerictextbox2 = $("#valor_muebles_enseres").data("kendoNumericTextBox");
	numerictextbox2.value(null);
	numerictextbox2.enable(true);

	var numerictextbox3 = $("#valor_maquinarias").data("kendoNumericTextBox");
	numerictextbox3.value(null);
	numerictextbox3.enable(true);

	var numerictextbox4 = $("#valor_mercaderia").data("kendoNumericTextBox");
	numerictextbox4.value(null);
	numerictextbox4.enable(true);

	var numerictextbox5 = $("#valor_insumos_medicos").data("kendoNumericTextBox");
	numerictextbox5.value(null);
	numerictextbox5.enable(true);

	var numerictextbox6 = $("#valor_herramientas").data("kendoNumericTextBox");
	numerictextbox6.value(null);
	numerictextbox6.enable(true);
	
	var numerictextbox7 = $("#valor_contenidos").data("kendoNumericTextBox");
	numerictextbox7.value(null);
	numerictextbox7.enable(true);

	var numerictextbox = $("#total_valor_asegurado").data("kendoNumericTextBox");
	numerictextbox.value(null);

	$("#ingresarNuevoValores").css("visibility", "hidden");


	$(".coberturaPorDireccion").each(function() {
		$(this).prop("checked",false);
	});
}
//riesgo
function verificarRiesgo(ciudad){
	$.ajax({
		url:'../PymeRiesgoController',
		data:{
			"tipoConsulta" : "verificarRiesgo",
			"ciudad" : ciudad
		},
		type : 'POST',
		datatype : 'json',
		success : function(data){
			if(data.riesgos=="SI"){
				if(data.tipoRiesgoErupcion=="SI"){
					$("#riesgoVolcanColumn").show();
					existeRiesgoVolcan="SI";
					existeRiesgoCosta="NO";
				}
				if(data.tipoRiesgoCosta=="SI"){
					$("#riesgoDanioColumn").show();
					existeRiesgoCosta="SI";
					existeRiesgoVolcan="NO";
				}
			}else{
				$("#riesgoDanioColumn").hide();
				$("#riesgoVolcanColumn").hide();
				existeRiesgoCosta="NO";
				existeRiesgoVolcan="NO";
			}			
		}
	});
}


function obtenerConfiguracionDireccion(numeroDireccion) {
	if($("#ubicacion_provincia_"+numeroDireccion).val()==undefined || $("#ubicacion_provincia_"+numeroDireccion).val()==""){
		alert("Debe seleccionar una provincia.");
		return;
	}
	if($("#ubicacion_canton_"+numeroDireccion).val()==undefined || $("#ubicacion_canton_"+numeroDireccion).val()==""){
		alert("Debe seleccionar un cantón.");
		return;
	}else{
		//riesgo
		verificarRiesgo($("#ubicacion_canton_"+numeroDireccion).val());
	}
	if($("#ubicacion_calle_principal_"+numeroDireccion).val()==undefined || $("#ubicacion_calle_principal_"+numeroDireccion).val()==""){
		alert("Debe ingresar la calle principal.");
		return;
	}
	if($("#ubicacion_numero_"+numeroDireccion).val()==undefined || $("#ubicacion_numero_"+numeroDireccion).val()==""){
		alert("Debe ingresar el numero.");
		return;
	}
	if($("#ubicacion_calle_secundaria_"+numeroDireccion).val()==undefined || $("#ubicacion_calle_secundaria_"+numeroDireccion).val()==""){
		alert("Debe ingresar la calle secundaria.");
		return;
	}
	
	
	limpiarControles();	
	$("#numeroDireccion").val(numeroDireccion);
	$("#cotizacionDetalleId").val($("#cotizacionDetalleId_"+numeroDireccion).val());
	if($("#cotizacionDetalleId").val()!=""){
		$.ajax({
			url : '../PymesObjetoCotizacionController',
			data : {
				"tipoConsulta" : "obtenerConfiguracionPorDetalleId",
				"cotizacionDetalleId" : $("#cotizacionDetalleId").val()
			},
			async : false,
			type : 'post',
			datatype : 'json',
			success : function(data) {
				var codigoActividad = data.actividadEconomicaId;
				var tabStrip = $("#tabstrip").kendoTabStrip().data("kendoTabStrip");
				tabStrip.enable(tabStrip.tabGroup.children().eq(2), true);
				
				$("#actividad_economica").data("kendoMultiSelect").value(data.actividadEconomicaId);
				
				$("#actividad_economica").change(function (e) {
					obtenerTipoPredioPorActividad('');
				});
				obtenerTipoPredioPorActividad(data.tipoPredioId);
				//$("#giro_negocio").val(data.tipoPredioId);
				if(data.tieneMasDosAnios)
					$("#tiene_mas_dos_anios_si").prop( "checked", true );
				else
					$("#tiene_mas_dos_anios_no").prop( "checked", true );
				if(data.contabilidadFormal)
					$("#contabilidad_formal_si").prop( "checked", true );
				else
					$("#contabilidad_formal_no").prop( "checked", true );
				if(data.requiereInspeccion)
					$("#requiere_inspeccion_si").prop( "checked", true );
				else
					$("#requiere_inspeccion_no").prop( "checked", true );
				
				//campo seguridad adecuada
				if(data.seguridadAdecuada)
					$("#seguridad_adecuada_si").prop( "checked", true );
				else
					$("#seguridad_adecuada_no").prop( "checked", true );
				
				//riesgo
				if(data.tipoRiesgo=="1"){
					var distanciaRiesgoV = $("#distanciaRiesgo").data("kendoNumericTextBox");
					distanciaRiesgoV.value(data.valorRiesgo);					
				}
				if(data.tipoRiesgo=="2"){
					if(data.valorRiesgo=="SI")
						$("#riesgoDanio_si").prop( "checked", true );
					else
						$("#riesgoDanio_no").prop( "checked", true );						
				}
				
				//Quemado que sea solo para edificios
				$("#fila_seguridad").hide();
				$("#fila_constitucion").hide();
				$("#fila_contabilidad").hide();
				if($("#ProductoID").val()=="186" || $("#ProductoID").val()=="187"  || $("#ProductoID").val()=="188" || $("#ProductoID").val()=="189"|| $("#ProductoID").val()=="231"|| $("#ProductoID").val()=="230"|| $("#ProductoID").val()=="235"|| $("#ProductoID").val()=="233"){
					$("#fila_constitucion").show();
					$("#fila_contabilidad").show();
				}
				if($("#ProductoID").val()=="190" || $("#ProductoID").val()=="211"  || $("#ProductoID").val()=="214" || $("#ProductoID").val()=="215" || $("#ProductoID").val()=="234"){ 
					$("#fila_contabilidad").show();
				}
				if($("#ProductoID").val()=="211"){ 
					$("#fila_constitucion").show();
				}
				if($("#ProductoID").val()=="218" || $("#ProductoID").val()=="221"){ 
					$("#fila_seguridad").show();
				}
				
				$("#ubicacion_sector").val(data.sector);
				$("#ubicacion_nombre_edificio").val(data.nombreEdificio);
				$("#ubicacion_numero_oficina").val(data.numeroOficina);

				var numerictextbox1 = $("#numero_total_pisos").data("kendoNumericTextBox");
				numerictextbox1.value(data.numeroTotalPisos);
				
				var numerictextbox2 = $("#anio_construccion").data("kendoNumericTextBox");
				numerictextbox2.value(data.anioConstruccion);
				
				$("#anio_construccion").val(data.anioConstruccion);
				$("#tipo_construccion").val(data.tipoConstruccionId);
				
				//riesgo
				var riesgoSierra=$("#distanciaRiesgo").data("kendoNumericTextBox");
				riesgoSierra.enable(false);
				var riesgoCostaSi=$("#riesgoDanio_si");
				riesgoCostaSi.attr('disabled','disabled');
				var riesgoCostaNo=$("#riesgoDanio_no");
				riesgoCostaNo.attr('disabled','disabled');
				
				var numerictextbox1 = $("#valor_estructuras").data("kendoNumericTextBox");
				numerictextbox1.value(data.valorEstructuras);
				numerictextbox1.enable(false);

				var numerictextbox2 = $("#valor_muebles_enseres").data("kendoNumericTextBox");
				numerictextbox2.value(data.valorMueblesEnseres);
				numerictextbox2.enable(false);

				var numerictextbox3 = $("#valor_maquinarias").data("kendoNumericTextBox");
				numerictextbox3.value(data.valorMaquinaria);
				numerictextbox3.enable(false);

				var numerictextbox4 = $("#valor_mercaderia").data("kendoNumericTextBox");
				numerictextbox4.value(data.valorMercaderia);
				numerictextbox4.enable(false);

				var numerictextbox5 = $("#valor_insumos_medicos").data("kendoNumericTextBox");
				numerictextbox5.value(data.valorInsumosNoelectronicos);
				numerictextbox5.enable(false);

				var numerictextbox6 = $("#valor_herramientas").data("kendoNumericTextBox");
				numerictextbox6.value(data.valorEquipoHerramienta);
				numerictextbox6.enable(false);
				
				var numerictextbox7 = $("#valor_contenidos").data("kendoNumericTextBox");
				numerictextbox7.value(data.valorContenidos);
				numerictextbox7.enable(false);

				$("#ingresarNuevoValores").css("visibility", "visible");

				var p1 = Number($("#valor_estructuras").val());
				var p2 = Number($("#valor_muebles_enseres").val());
				var p3 = Number($("#valor_maquinarias").val());
				var p4 = Number($("#valor_mercaderia").val());
				var p5 = Number($("#valor_herramientas").val());
				var p6 = Number($("#valor_insumos_medicos").val());
				
				//limpiamos los deducibles
				$("#deducibles_por_direccion").empty();

				var p7 = Number($("#valor_contenidos").val());

				var numerictextbox = $("#total_valor_asegurado").data("kendoNumericTextBox");
				numerictextbox.value(p1 + p2 + p3 + p4 + p5 + p6 + p7);

				var coberturasConfiguradas= data.coberturas;
				//tomamos las configuraciones de los deducibles
				var coberturasDeducibles = data.coberturasDeducibles;
				
				$.each(coberturasConfiguradas, function(index) {
					cargarCobertura(coberturasConfiguradas[index].configuracionCoberturaId, 
							"direccion", 
							coberturasConfiguradas[index].tasaSugerida, 
							coberturasConfiguradas[index].tasaIngresada, 
							coberturasConfiguradas[index].valorIngresado, 
							coberturasConfiguradas[index].primaCalculada,
							coberturasConfiguradas[index].valorAdicional);
				});
				
				//Cargamos los deducibles
				
				$.each(coberturasDeducibles, function(index) {
					cargarDeducible(coberturasDeducibles[index].configuracionCoberturaId, 
							coberturasDeducibles[index].textoDeducible,
							coberturasDeducibles[index].idsDeducible,
							coberturasDeducibles[index].valoresDeducible,
							coberturasDeducibles[index].textosDeducible);
				});
				
				// Calculo total de la prima de esta direccion
				var valorTotal = 0;
				$(".primaNetaCobertura").each(function() {
					valorTotal = valorTotal + Number($(this).val());
				});
				$("#prima_neta_por_direccion").text(formatDollar(valorTotal));
				
				$('#add').modal('show');
			}
		});
	}
	else{
		$('#add').modal('show');
		$("#seguridad_adecuada_si").prop( "checked", false );
		$("#seguridad_adecuada_no").prop( "checked", false );
		$("#deducibles_por_direccion").empty();
		//Quemado que sea solo para edificios
		$("#fila_seguridad").hide();
		$("#fila_constitucion").hide();
		$("#fila_contabilidad").hide();
		if($("#ProductoID").val()=="186" || $("#ProductoID").val()=="187"  || $("#ProductoID").val()=="188" || $("#ProductoID").val()=="189"|| $("#ProductoID").val()=="231"|| $("#ProductoID").val()=="230"|| $("#ProductoID").val()=="235"|| $("#ProductoID").val()=="233"){
			$("#fila_constitucion").show();
			$("#fila_contabilidad").show();
		}
		if($("#ProductoID").val()=="190" || $("#ProductoID").val()=="211"  || $("#ProductoID").val()=="214" || $("#ProductoID").val()=="215" || $("#ProductoID").val()=="234"){ 
			$("#fila_contabilidad").show();
		}
		//Productos Casa Habitación Dinámicos
		if($("#ProductoID").val()=="218" || $("#ProductoID").val()=="221"){ 
			$("#fila_seguridad").show();
		}
	}
}

function agregarNuevaDireccion() {

	var numeroDirecciones = parseInt($("#contadorDirecciones").val()) + parseInt(1);

	$("#direcciones")
	.append(
			'<tr>'
			+ '<td>'
			+ '<div class="panel panel-primary">'
			+ '<div class="panel-body">'
			+ '<table>'
			+ '<tr>'
			+ '<td style="width: 10%"><label><b>Provincia:*</b></label></td>'
			+ '<td style="width: 25%"><select id="ubicacion_provincia_'
			+ numeroDirecciones
			+ '" required="required" class="datosPymes ubicacionProvincia"></select></td>'
			+ '<td style="width: 5%"><label><b>Cant&oacuten:*</b></label></td>'
			+ '<td style="width: 15%"><select id="ubicacion_canton_'
			+ numeroDirecciones
			+ '" required="required" class="datosPymes"></select></td><td></td><td></td>'
			+ '<td style="width: 20%">'
			+ '<button type="button" class="btn btn-success btn-xs actualizar-btn" onclick="obtenerConfiguracionDireccion('
			+ numeroDirecciones
			+ ')">'
			+ '<span class="glyphicon glyphicon glyphicon-edit"></span> Informaci&oacute;n para cotizar</button>'
			+ '</td>'
			+ '</tr>'
			+ '<tr>'
			+ '<td><label><b>C. Principal:*</b></label></td>'
			+ '<td><input type="text" id="ubicacion_calle_principal_'
			+ numeroDirecciones
			+ '" required="required" class="datosPymes"></input></td>'
			+ '<td><label><b>N&uacutemero:*</b></label></td>'
			+ '<td><input type="text" id="ubicacion_numero_'
			+ numeroDirecciones
			+ '" required="required" class="datosPymes"></input></td>'
			+ '<td><label><b>C. Secundaria:*</b></label></td>'
			+ '<td><input type="text" id="ubicacion_calle_secundaria_'
			+ numeroDirecciones
			+ '" required="required" class="datosPymes"></input></td>'
			+ '<td style="width: 20%" class="sorting">'
			+ '<input type="hidden" id="cotizacionDetalleId_'+numeroDirecciones+'"/>'
			+ '<button type="button" class="btn btn-danger btn-xs eliminar-extra-btn">'
			+ ' <span class="glyphicon glyphicon glyphicon-remove"></span> Eliminar'
			+ ' </button></td>'
			+ '</tr>'
			+ '</table>'
			+ '</div>' + '</div>' + '</td>' + '</td>' + '</tr>');
	$("#contadorDirecciones").val(numeroDirecciones);

	$(".eliminar-extra-btn").bind({
		click : function() {
			$(this).parent().parent().parent().parent().parent().parent().parent().remove();
			var numeroDirecciones = parseInt($("#contadorDirecciones").val()) - parseInt(1);
			$("#contadorDirecciones").val(numeroDirecciones);
		}
	});

	// Consultar provincias
	cargarProvincias(numeroDirecciones);

	$("#ubicacion_provincia_"+numeroDirecciones).change(function (e) {
		obtenerCantonPorProvincia('',numeroDirecciones);
	});
}

function verificarEstadoInspeccion(){
	var resultado=false;
	$.ajax({
		url : '../PymeCotizacionController',
		data : {
			"tipoConsulta" : "encontrarPorId",
			"id" : $("#cotizacion_id").text()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			if (data.success) {
				var datosCotizacion = data.datosCotizacion;
				if(datosCotizacion.estadoCotizacion=="Borrador"){
					$("#lblContenidoMensajeInspeccion").html("No puede continuar, porque debe aprobar la cotizaci&oacute;n.");
					$("#msgPopupFichaInspeccionError").show();
					resultado = false;
				}
				else if(datosCotizacion.estadoCotizacion=="Pendiente Asignar Inspector"){
					$("#lblContenidoMensajeInspeccion").html("No puede continuar, porque debe realizar la asignaci&oacute;n de los inspectores.");
					$("#msgPopupFichaInspeccionError").show();
					resultado = false;
				}
				else if(datosCotizacion.estadoCotizacion=="Pendiente de Inspeccion"){
					$("#lblContenidoMensajeInspeccion").html("No puede continuar, porque primero debe realizar la inspecci&oacute;n.");
					$("#msgPopupFichaInspeccionError").show();
					resultado = false;
				}
				else
					resultado = true;
			}
		}
	});
	return resultado;
}


function verificarEstadoPago(){
	var resultado=false;
	$.ajax({
		url : '../PymeCotizacionController',
		data : {
			"tipoConsulta" : "verificarEstadoPago",
			"codigoPagoRegistrado" : $("#codigoPagoRegistrado").val(),
			"cotizacionId" : $("#cotizacion_id").text()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			if (data.success) {
				$("#msgPopupFichaPagoError").hide();
				resultado = true;
			}
			else{
				$("#lblContenidoMensajePago").html("No puede continuar. " + data.error);
				$("#msgPopupFichaPagoError").show();
				resultado = false;
			}
				
		}
	});
	return resultado;
}

function cambiarEstadoCotizacion(estado) {
	$.ajax({
		url : '../PymeCotizacionController',
		data : {
			"tipoConsulta" : "cambiarEstado",
			"cotizacionId" : $("#cotizacion_id").text(),
			"estadoNombre" : estado,
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			$("#cotizacion_id").text(data.cotizacionId);
			// Validacion poner en la URL el id de la cotizacion
			var valorId = getParameterByName("id");
			if (valorId != null) {
				$.pushVar("id", data.cotizacionId, "", window.location.href);
				if (estado == "Denegado")
					$("#msgPopupEmitidoRechazado").show();
				else if(estado == "Pendiente Asignar Inspector"){
					$(".datosPymes").each(function() {
						$(this).attr("disabled", "disabled");
					});

					$(":button").each(function() {
						$(this).attr("disabled", "disabled");
					});

					$(":checkbox").each(function() {
						$(this).attr("disabled", "disabled");
					});
					$("#msgInspeccionAprobada").show();
				}
				else 
				{
					$("#msgPopupFinalizadoCorrectamente").show();
					$(".datosPymes").each(function() {
						$(this).attr("disabled", "disabled");
					});

					$(":button").each(function() {
						$(this).attr("disabled", "disabled");
					});

					$(":checkbox").each(function() {
						$(this).attr("disabled", "disabled");
					});
					$("#filaEmitirCotizacion").hide();
					$("#datosFinales").hide();
					$("#filaEnviarCotizacion").hide();
				}
				window.location.href='AsignarInspectorPymes.jsp?id='+data.cotizacionId;
			}
			
		}
	});
}

function procesarReporte() {
	window.open('../PymeCotizacionController?tipoConsulta=generarReporte&cotizacionId=' + $("#cotizacion_id").text());
}

function enviarReporte() {
	$.ajax({
		url : '../PymeCotizacionController',
		data : {
			"tipoConsulta" : "enviarReporte",
			"cotizacionId" : $("#cotizacion_id").text(),
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			
		}
	});
}

function enviarPoliza() {
	$("#msgPopupEmisionConError").hide();
	$.ajax({
		url : '../PymeCotizacionController',
		data : {
			"tipoConsulta" : "enviarPoliza",
			"cotizacionId" : $("#cotizacion_id").text(),
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			if(data.success){
				$("#lbl_ErrorEmisionPoliza").html("Póliza enviada correctamente");
				$("#msgPopupEmisionConError").show();
			}
			else{
				$("#lbl_ErrorEmisionPoliza").html("No se pudo enviar la póliza");
				$("#msgPopupEmisionConError").show();
			}
				
		}
	});
}

function nuevoBeneficiario(endosoBeneficiarioId){
	var contador = Number($("#contador_endosos_beneficiario").val());
	if(endosoBeneficiarioId)
		contador = endosoBeneficiarioId;
	else
		contador = contador + 1;
	
	$("#contador_endosos_beneficiario").val(contador);
	
	var eliminar=2;
	var guardar=1;
	
	$("#divEndosoBeneficiario").append('<table class="table table-bordered table-hover" id="fila_endoso_benficiario_'+
				+ contador
				+ '">'
				+ '<tr><td>Beneficiario</td><td><select style="width: 90%" id="beneficiario_'
				+ contador
				+ '" class="datosPymes datosPymesPosterior"></select><input id="endoso_beneficiario_id_'
				+ contador
				+ '" type="hidden"></td></tr><tr><td>Rubro</td>'
				+ '<td><select id="beneficiario_rubro_'+
				+ contador
				+ '" class="datosPymes datosPymesPosterior" style="width: 250px">'
				+ '<option>Seleccione el rubro</option>'
				+ '<option value="1">Valores de Estructuras</option>'
				+ '<option value="2">Valores de Muebles y Enseres</option>'
				+ '<option value="3">Valores de Maquinaria</option>'
				+ '<option value="4">Valores de Mercaderia</option>'
				+ '<option value="5">Valores de Insumos(No electrónicos)</option>'
				+ '<option value="6">Valores de Equipos y Herramientas</option>'
				+ '</select></td></tr><tr><td>Monto</td>'
				+ '<td><input type="number" value="0" min="0" id="valor_endoso_beneficiario_'
				+ contador
				+ '" class="datosPymes datosPymesPosterior"></td>'
				+ '</tr><tr><td><div align="center"><button type="button" class="btn btn-primary"'
				+ 'id="guardar_beneficiario_'
				+ contador
				+ '" onClick="guardarBeneficiario(' + guardar + ','
				+ contador
				+ ');">Guardar</button>'
				+ '</div></td><td><div align="center"><button type="button" class="btn btn-danger"'
				+ 'id="eliminar_beneficiario_'
				+ contador
				+ '" onClick="guardarBeneficiario(' + eliminar + ','+
				+ contador
				+ ');">Eliminar</button>'
				+ '</div></td></tr></table>');

	//LLenar el combo
	$.ajax({
		url : '../BeneficiarioController',
		data : {
			"tipoConsulta" : "cargarSelect2"
		},
		async: false,
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var beneficiarios = data.listadoBeneficiarios;
			/*$('#beneficiario').select2({
				data : beneficiarios,
				placeholder : 'Seleccione un beneficiario'
			});*/
			$("#beneficiario_"+contador).append("<option value=''>Seleccione una opcion</option>");
			$.each(beneficiarios, function (index) {
				$("#beneficiario_"+contador).append("<option value='" + beneficiarios[index].id + "'>" + beneficiarios[index].text + "</option>");
			});
		}
	});
}

function solicitarAutorizacion(){
	$.ajax({
		url : '../PymeCotizacionController',
		data : {
			"tipoConsulta" : "solicitarAutorizacion",
			"cotizacionId" : $("#cotizacion_id").text(),
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			if(data.success){
				$("#wizard .actions a[href='#finish']").hide();
				$("#solicitarAutorizacion").hide();
				window.location.href='CotizacionesPendientesPymes.jsp?State=SPE';
			}
			else{
				if(data.autorizacion){
					$("#solicitarAutorizacion").show();
				}
				$("#lbl_ErrorEmisionPoliza").html("No se pudo solicitar autorización de la poliza: " + data.errorMesaje);
				$("#msgPopupEmisionConError").show();
				$("#imprimirPoliza").hide();
				$("#enviarPoliza").hide();
				$("#imprimirAutorizacionDebito").hide();
				$("#imprimirVinculacionCliente").hide();
				$("#imprimirEndosoBeneficiario").hide();
				$("#wizard .actions a[href='#finish']").show();
				//Muestro los objetos de la fecha emisión
				$("#fecha_inicio_vigencia").show();
				$("#lbl_FechaVigencia").show();
			}
				
		}
	});
}


function emitirPoliza() {
	if($("#fecha_inicio_vigencia").val()!=""){
		$("#msgPopupEmitidoCorrectamente").hide();
		$("#msgPopupEmisionConError").hide();
		$("#wizard .actions a[href='#finish']").hide();
		$.ajax({
			url : '../PymeCotizacionController',
			data : {
				"tipoConsulta" : "emitirPoliza",
				"cotizacionId" : $("#cotizacion_id").text(),
				"fechaInicioVigencia" : $("#fecha_inicio_vigencia").val(),
			},
			async : false,
			type : 'post',
			datatype : 'json',
			success : function(data) {
				if(data.success){
					$("#cotizacion_id").text(data.cotizacionId);
					$("#imprimirPoliza").show();
					$("#enviarPoliza").show();
					$("#imprimirAutorizacionDebito").show();
					$("#imprimirVinculacionCliente").show();
					$("#imprimirEndosoBeneficiario").show();
					$("#msgPopupEmitidoCorrectamente").show();
					//Oculto los objetos de la fecha emisión
					$("#fecha_inicio_vigencia").hide();
					$("#lbl_FechaVigencia").hide();
				}
				else{
					if(data.autorizacion){
						$("#solicitarAutorizacion").show();
					}
					$("#lbl_ErrorEmisionPoliza").html("No se pudo emitir la poliza: " + data.errorMesaje);
					$("#msgPopupEmisionConError").show();
					$("#imprimirPoliza").hide();
					$("#enviarPoliza").hide();
					$("#imprimirAutorizacionDebito").hide();
					$("#imprimirVinculacionCliente").hide();
					$("#imprimirEndosoBeneficiario").hide();
					$("#wizard .actions a[href='#finish']").show();
					//Muestro los objetos de la fecha emisión
					$("#fecha_inicio_vigencia").show();
					$("#lbl_FechaVigencia").show();
				}
					
			}
		});
	}
	else{
		alert("Debe ingresar la fecha de inicio de vigencia de la póliza");
		return false;
	}
		
}

function cargarDireccionFactura(formulario, datos) {
	if (datos != null) {
		if (formulario == "solicitante") {
			if ($("#tipo_identificacion_principal").val() == "4") {
				$("#nombre_direccion_solicitante").val(
						$("#nombre_completo").val());
			} else {
				$("#nombre_direccion_solicitante").val(
						$("#nombres").val() + " " + $("#apellidos").val());
			}
			$("#cedula_direccion_solicitante").val($("#identificacion").val());
			$("#telefono_direccion_solicitante").val(datos.telefono);
			$("#celular_direccion_solicitante").val(datos.celular);
			$("#mail_direccion_solicitante").val(datos.email);
			options = '';
		}
		if (formulario == "asegurado") {
			$("#tipo_identificacion_asegurado").val(datos.tipoIdentificacion);
			$("#nombres_asegurado").val(datos.nombre);
			$("#apellidos_asegurado").val(datos.apellido);
			$("#identificacion_asegurado").val(datos.identificacion);
			if (datos.tipoIdentificacion == "4") {
				$("#nombre_direccion_asegurado").val(datos.nombreCompleto);
				$("#filaJuridicaAsegurado").show();
				$("#filaNaturalAsegurado").hide();

			} else {
				$("#nombre_direccion_asegurado").val(
						datos.nombre + " " + datos.apellido);
				$("#filaNaturalAsegurado").show();
				$("#filaJuridicaAsegurado").hide();
			}

			if (datos.zona == "1") {
				$("#zona_direccion_asegurado").val("U");
				if (datos.tipoIdentificacion == "4") {
					$("#provincia_direccion_matriz_juridica").val(datos.provincia);
					obtenerCiudadesPorProvinciaPJuridica(datos.ciudad);
					
				} else {
					$("#provincia_direccion_cliente_natural").val(datos.provincia);
					obtenerCiudadesPorProvinciaPNatural(datos.ciudad);
				}
				$("#fila_ciudad_direccion_asegurado").fadeIn("slow");
				$("#fila_canton_direccion_asegurado").fadeOut("slow");
				$("#fila_parroquia_direccion_asegurado").fadeOut("slow");
				$("#principal_direccion_asegurado").val(datos.callePrincipal);
				$("#secundaria_direccion_asegurado").val(datos.calleSecundaria);
				$("#numero_direccion_asegurado").val(datos.numero);
				$("#referencia_direccion_asegurado").val(datos.datosReferencia);
			}
			if (datos.zona == "2") {
				$("#zona_direccion_asegurado").val("R");
				if (datos.tipoIdentificacion == "4") {
					// cargarProvinciasPJuridica(datos.provincia,
					// "direccion_asegurado");
					$("#provincia_direccion_matriz_juridica").val(
							datos.provincia);
					obtenerCantonPorProvinciaPJuridica(datos.canton);
					obtenerParroquiaPorCantonPJuridica(datos.parroquia);
				} else {
					$("#provincia_direccion_cliente_natural").val(
							datos.provincia);
					obtenerCantonPorProvinciaPNatural(datos.canton);
					obtenerParroquiaPorCantonPNatural(datos.parroquia);
				}
				// cargarProvincias(datos.provincia, "direccion_asegurado");
				// cargarCantones(datos.canton, datos.provincia,
				// "direccion_asegurado");
				$("#fila_ciudad_direccion_asegurado").fadeOut("slow");
				$("#fila_canton_direccion_asegurado").fadeIn("slow");
				//cargarParroquias(datos.parroquia, datos.canton,"direccion_asegurado");
				$("#fila_parroquia_direccion_asegurado").fadeIn("slow");
				$("#principal_direccion_asegurado").val(datos.callePrincipal);
				$("#secundaria_direccion_asegurado").val(datos.calleSecundaria);
				$("#numero_direccion_asegurado").val(datos.numero);
				$("#referencia_direccion_asegurado").val(datos.datosReferencia);
			}
		}
	}
	if ((datos == null || datos == "")
			&& (formulario == "" || formulario == null)) {
		var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
		options += '<option value="U">&nbsp;&nbsp;Urbana </option>';
		options += '<option value="R">&nbsp;&nbsp;Rural </option>';
		$("#zona_direccion_asegurado").html(options);
		$("#zona_direccion_solicitante").html(options);

		cargarProvincias("", "direccion_asegurado");
		cargarProvincias("", "direccion_solicitante");
	}
	if ((datos == null || datos == "") && (formulario == "solicitante")) {
		var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
		options += '<option value="U">&nbsp;&nbsp;Urbana </option>';
		options += '<option value="R">&nbsp;&nbsp;Rural </option>';
		$("#zona_direccion_solicitante").html(options);

		cargarProvincias("", "direccion_solicitante");
	}
	if ((datos == null || datos == "") && (formulario == "asegurado")) {
		var options = '<option value="">&nbsp;&nbsp;&nbsp;&nbsp;Seleccione&nbsp;&nbsp;&nbsp;&nbsp;</option>';
		options += '<option value="U">&nbsp;&nbsp;Urbana </option>';
		options += '<option value="R">&nbsp;&nbsp;Rural </option>';
		$("#zona_direccion_asegurado").html(options);

		cargarProvincias("", "direccion_asegurado");
	}
}

function cambioFormaPago(valor, limpiar) {
	$("#tarjetaNumero").next().next().hide();
	$("#msgPopupPago").fadeOut("slow");
	var formaPago = valor;

	if (limpiar) {
		limpiaForm(forma_debitos);
		limpiaForm(forma_tarjeta);
		limpiaForm(forma_cuotas);
	}

	if (formaPago == '1') {
		$('#forma_tarjeta').fadeOut("slow");
		$('#forma_cuotas').fadeOut("slow");
		$('#forma_debitos').fadeOut("slow");
		$("#save-pagoContado").show();
	}

	if (formaPago == '2') { //Corresponde a Debitos Bancos
		$("#detallePagoCuotas").empty();
		$("#rowDetallePagos").hide();
		$("#save-pagoContado").hide();
		$('#forma_tarjeta').fadeOut("slow");
		$('#forma_cuotas').fadeOut("slow");
		$('#forma_debitos').fadeIn("slow");
		//var fecha=new Date();
		//$("#txtfechaInicialpago").attr('');

	}

	if (formaPago == '3') { //Corresponde a Debitos Tarjetas
		$("#detallePagoCuotas").empty();
		$("#rowDetallePagos").hide();
		$("#save-pagoContado").hide();
		$('#forma_cuotas').fadeOut("slow");
		$('#forma_debitos').fadeOut("slow");
		$('#forma_tarjeta').fadeIn("slow");
	}

	if (formaPago == '4') { //Corresponde a Cuotas
		$("#save-pagoContado").hide();
		$('#forma_tarjeta').fadeOut("slow");
		$('#forma_debitos').fadeOut("slow");
		$('#forma_cuotas').fadeIn("slow");
	}
}

function validarValoresPagos(tipo) {
	var formulario = $("#cboFpFormaPago").val();
	var cuotaInicial;
	var numCuotas;

	if (formulario != "1" || tipo != null) {
		if (formulario == "4" || tipo == "CREDITO CUOTAS") {
			cuotaInicial = $("#txtcuotaInicial");
			numCuotas = $("#cboFpPlazo");
		}
		if (formulario == "2" || tipo == "DEBITO BANCARIO") {
			cuotaInicial = $("#txtcuotaInicialbancoPlazo");
			numCuotas = $("#bancoPlazo");
		}
		if (formulario == "3" || tipo == "DEBITO TARJETA") {
			cuotaInicial = $("#txtcuotaInicialtarjetaPlazo");
			numCuotas = $("#tarjetaPlazo");
		}

		var valorCuotaInicial = Number($(cuotaInicial).val());
		var valorNumCuotas = Number($(numCuotas).val());
		var valor = $("#total").val().replace("$","").replace(',', '');
		valor=parseFloat(valor);
		if (valorNumCuotas == 12) {
			valor = valor * parseFloat(1.0842);
		}
		var valorRestante = valor - valorCuotaInicial;
		var valorCuotas = parseFloat(valorRestante).toFixed(2) / parseInt(valorNumCuotas);

		if (valorCuotaInicial > valor && valor!=0) {
			alert("El valor de la cuota inicial no puede ser mayor al valor del pago!");
			$(cuotaInicial).val(0);

		}
		if (valorCuotaInicial > 0 && valorCuotaInicial < 50) {
			alert("El valor minimo de la cuota inicial es $50");
			$(cuotaInicial).val(0);

		}
		if (valorCuotaInicial == 0) {
			var slctCts = $(numCuotas).attr("id");
			var opcion = $("#" + slctCts + " option[value='9']");
			var clase = $(opcion).attr("class");
			if ($("#" + slctCts + " option[value='10']").length == 0)
				$(opcion).after("<option value='10' class='" + clase + "'>10 meses</option>");
			if (valorCuotas < 50) {
				valorNumCuotas = Math.floor(valorRestante / 50) == 0 ? 1 : Math.floor(valorRestante / 50);
				valorCuotas = parseFloat(valorRestante).toFixed(2) / parseInt(valorNumCuotas);
				$(numCuotas).val(valorNumCuotas); //.trigger("change");
				alert("Las cuotas mensuales no pueden ser menores a $50. Usted puede pagar la diferencia en m&aacute;ximo " + valorNumCuotas + " cuotas");
			}
		}
		if (valorCuotaInicial >= 50 && valorCuotaInicial < valor) {
			var slctCts = $(numCuotas).attr("id");
			$("#" + slctCts + " option[value='10']").remove();
			if (valorCuotas < 50) {
				valorNumCuotas = Math.floor(valorRestante / 50) == 0 ? 1 : Math.floor(valorRestante / 50);
				valorNumCuotas = valorNumCuotas == 10 ? 9 : valorNumCuotas;
				$(numCuotas).val(valorNumCuotas); //.trigger("change");
				alert("Las cuotas mensuales no pueden ser menores a $50. Usted puede pagar la diferencia en m&aacute;ximo " + valorNumCuotas + " cuotas");
			}
			valorNumCuotas = valorNumCuotas == 10 ? 9 : valorNumCuotas;
			$(numCuotas).val(valorNumCuotas); //.trigger("change");
		}
	}
	calcularValoresCuotas();
}

function validarCuotaMinima(event) {
	var target = event.target || event.srcElement;
	var aux = $(target).attr('id');
	var cuotaInicial = $("#txtcuotaInicial" + aux.replace("txtcuotaInicial", "")).val();
	var numCuotas = $(target).val();
	var valor = $("#total").val().replace("$","");
	var valorRestante = valor - cuotaInicial;
	var valorCuotas = parseFloat(valorRestante).toFixed(2) / parseInt(numCuotas);

	if (valorCuotas < 50) {
		numCuotas = Math.floor(valorRestante / 50) == 0 ? 1 : Math.floor(valorRestante / 50);
		valorCuotas = parseFloat(valorRestante).toFixed(2) / parseInt(numCuotas);
		$(target).val(numCuotas); //.trigger("change");
		alert("Las cuotas mensuales no pueden ser menores a $50. Usted puede pagar la diferencia en maximo " + numCuotas + " cuotas");
		$("#" + aux + " option[value='10']").remove();
		var opcion = $("#" + id + " option[value='9']");
		var clase = $(opcion).attr("class");
		$(opcion).after("<option value='10' class='" + clase + "'>10 meses</option>");
		calcularValoresCuotas();
	} else
		$(target).parent().parent().prev().children().last().children().first().trigger("change");
}

function validarCuotaInicial(event) {
	$("#detallePagoCuotas").empty();
	var target = event.target || event.srcElement;
	var id = $(target).attr("id");
	var slctCts = id.replace("txtcuotaInicial", "");
	if ($(target).val() == "")
		$(target).val(0);
	if ($(target).val() < 50 && $(target).val() != 0) {
		alert("El valor minimo de la cuota inicial es $50");
		$(target).val(0);

		var opcion = $("#" + slctCts + " option[value='9']");
		var clase = $(opcion).attr("class");
		if ($("#" + id + " option[value='10']").length == 0)
			$(opcion).after("<option value='10' class='" + clase + "'>10 meses</option>");
	} else {
		if ($(target).val() != 0)
			$("#" + slctCts + " option[value='10']").remove();
		else {
			var opcion = $("#" + slctCts + " option[value='9']");
			var clase = $(opcion).attr("class");
			if ($("#" + id + " option[value='10']").length == 0)
				$(opcion).after("<option value='10' class='" + clase + "'>10 meses</option>");
		}
		$(target).parent().parent().next().children().last().children().first().trigger("change");
		calcularValoresCuotas();

	}
}

function calcularValoresCuotas() {
	var formulario = $("#cboFpFormaPago").val();
	var cuotaInicial = "";
	var numeroCuotas = "";
	var valorTotal = $("#total").val().replace("$","").replace(",","");
	valorTotal=parseFloat(valorTotal);
	var diferencia;
	var valorCuotas;

	if (formulario != "1") {
		if (formulario == "4") {
			cuotaInicial = $("#txtcuotaInicial").val();
			numeroCuotas = $("#cboFpPlazo").val();
		}
		if (formulario == "2") {
			cuotaInicial = $("#txtcuotaInicialbancoPlazo").val();
			numeroCuotas = $("#bancoPlazo").val();
		}
		if (formulario == "3") {
			cuotaInicial = $("#txtcuotaInicialtarjetaPlazo").val();
			numeroCuotas = $("#tarjetaPlazo").val();
		}
		cuotaInicial = Number(cuotaInicial);
		numeroCuotas = Number(numeroCuotas);
		if (numeroCuotas == 12) {
			valorTotal = valorTotal * parseFloat(1.0842);
		}
		diferencia = Number(valorTotal - cuotaInicial);
		valorCuotas = parseFloat(diferencia / numeroCuotas).toFixed(2);

		var mensaje = "El valor total a pagar es $" + parseFloat(valorTotal).toFixed(2);
		mensaje += " en " + numeroCuotas + " cuotas de $" + valorCuotas;
		if (cuotaInicial > 0)
			mensaje += ", cuota inicial de $" + cuotaInicial;

		if (numeroCuotas == 1)
			mensaje = mensaje.replace("cuotas", "cuota");
		if (numeroCuotas != 0)
			$("#msgPopupPago").attr("class", "alert alert-info").html(mensaje).fadeIn();
	}
}

function cargarFormasPagoGanadero(seleccionada) {
	$("#rowDetallePagos").hide();
	/*
	 * $.ajax({ url : '../FormaPagoController', data : { "tipoConsulta" :
	 * "encontrarTodos", }, type : 'post', datatype : 'json', success : function
	 * (data) { var listadoFormaPago = data.listadoFormaPago; var options = '';
	 * $.each(listadoFormaPago, function(index){ options += '<option value="' +
	 * listadoFormaPago[index].codigo + '">' + listadoFormaPago[index].nombre + '</option>';
	 * }); $("#cboFpFormaPago").empty().append(options);
	 * 
	 * if (seleccionada > 0){ cambioFormaPago(seleccionada, false);
	 * $("#cboFpFormaPago").val(seleccionada); }
	 * 
	 * for(i=2014; i<=2019; i++) $("#tarjetaAnioExp").append('<option value="' +
	 * i + '">' + i + '</option>'); } });
	 */

	$
	.ajax({
		url : '../InstitucionFinancieraController',
		data : {
			"tipoConsulta" : "encontrarTodos",
		},
		type : 'post',
		datatype : 'json',
		success : function(datos) {
			var listadoInstitucionFinanciera = datos.listadoInstitucionFinanciera;

			$("#banco_forma_pago").empty();
			$("#cboFpBanco").empty();
			$
			.each(
					listadoInstitucionFinanciera,
					function(index) {
						if (listadoInstitucionFinanciera[index].tarjeta == '0'
							&& listadoInstitucionFinanciera[index].debito == '1') {
							$("#bancoId")
							.append(
									"<option value='"
									+ listadoInstitucionFinanciera[index].codigo
									+ "' nemotecnico='"
									+ listadoInstitucionFinanciera[index].nemotecnico
									+ "'>"
									+ listadoInstitucionFinanciera[index].nombre
									+ "</option>");
						}

						if (listadoInstitucionFinanciera[index].tarjeta == '1'
							&& listadoInstitucionFinanciera[index].debito == '1') {
							$("#tarjetaId")
							.append(
									"<option value='"
									+ listadoInstitucionFinanciera[index].codigo
									+ "' nemotecnico='"
									+ listadoInstitucionFinanciera[index].nemotecnico
									+ "'>"
									+ listadoInstitucionFinanciera[index].nombre
									+ "</option>");
						}
					});

			var fecha = new Date();
			var anio = fecha.getFullYear();
			var anioLimite = parseInt(anio) + 10;

			for (i = parseInt(anio); i <= anioLimite; i++) {
				$("#cboFpAnio").append(
						"<option value='" + i + "'>" + i + "</option>");
			}
		}
	});
}

function cargarPorCotizacionId(id) {
	var etapa;

	$.ajax({
		url : '../PymeCotizacionController',
		data : {
			"tipoConsulta" : "encontrarPorId",
			"id" : id
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			if (data.success) {
				var datosCotizacion = data.datosCotizacion;
				$("#estadoCotizacion").val(
						datosCotizacion.estadoCotizacion);
				if (datosCotizacion != null)
					$("#grupo_productos").val(
							datosCotizacion.etapa1.productos);

				if (datosCotizacion.etapa1 != null) {

					$("#grupo_productos").removeAttr('required');
					$("#productos").removeAttr('required');
					$("#tipo_identificacion_principal").removeAttr('required');
					$("#identificacion").removeAttr('required');
					$("#nombres").removeAttr('required');
					$("#email").removeAttr('required');
					$("#apellidos").removeAttr('required');
					$("#tipo_identificacion").removeAttr('required');
					$("#agentes").removeAttr('required');
					$("#vigencia").removeAttr('required');
					$("#punto_venta").removeAttr('required');
					$("#agentes").removeAttr('selected');

					var etapa1 = datosCotizacion.etapa1;

					// Lleno los combos que son base de otros combos
					$("#grupo_productos").val(etapa1.grupoProductos);
					$("#productos").val(etapa1.productos);
					$("#ProductoID").val(etapa1.productos);
					$("#puntoVentaSeleccionado").val(etapa1.puntoVenta);
					$("#agentes").val(etapa1.agente);
					$("#agentes option[value='"+etapa1.agente+"']").attr("selected","selected");

					// Lleno los combos dependientes
					obtenerProductosPorGrupoProducto(etapa1.productos);
					cargarTiposIdentificacionPymes(etapa1.tipoIdentificacion, 'principal', true);
					$("#vigencia").val(etapa1.vigenciaPoliza);
					$("#identificacion").val(etapa1.identificacion);
					$("#nombres").val(etapa1.nombres);
					$("#nombre_completo").val(etapa1.nombreCompleto);
					$("#apellidos").val(etapa1.apellidos);
					$("#email").val(etapa1.mail);
					$("#celular").val(etapa1.celular);
					$("#telefono").val(etapa1.telefono);
					cotizacionEstado=etapa1.estado;

					if($("#identificacion_asegurado").val()==""){
						cargarTiposIdentificacionPymes(etapa1.tipoIdentificacion, 'asegurado', true);
						$("#identificacion_asegurado").val(etapa1.identificacion);
						$("#nombres_asegurado").val(etapa1.nombres);
						$("#nombre_completo_asegurado").val(etapa1.nombreCompleto);
						$("#apellidos_asegurado").val(etapa1.apellidos);
					}
					etapa = 1;
					cargarProvincias("1");
				}

				if (datosCotizacion.etapa2 != null) {
					$("a[href='#next']").click();
					var etapa2 = datosCotizacion.etapa2;

					//Creo las direcciones configuradas
					var listadoDirecciones = etapa2.direcciones;
					$.each(listadoDirecciones, function(index){
						if(index==0){
							$("#ubicacion_provincia_1").val(listadoDirecciones[index].provinciaId);
							obtenerCantonPorProvincia(listadoDirecciones[index].cantonId, "1");
							$("#cotizacionDetalleId_1").val(listadoDirecciones[index].cotizacionDetalleId);
							$("#ubicacion_calle_principal_1").val(listadoDirecciones[index].callePrincipal);
							$("#ubicacion_numero_1").val(listadoDirecciones[index].numeroDireccion);
							$("#ubicacion_calle_secundaria_1").val(listadoDirecciones[index].calleSecundaria);
							$("#contadorDirecciones").val(listadoDirecciones[index].cotizacionDetalleId);
						}
						else{
							$("#direcciones").append(
									'<tr>'
									+ '<td>'
									+ '<div class="panel panel-primary">'
									+ '<div class="panel-body">'
									+ '<table>'
									+ '<tr>'
									+ '<td style="width: 10%"><label><b>Provincia:*</b></label></td>'
									+ '<td style="width: 25%"><select id="ubicacion_provincia_'
									+ listadoDirecciones[index].cotizacionDetalleId
									+ '" required="required" class="datosPymes ubicacionProvincia"></select></td>'
									+ '<td style="width: 5%"><label><b>Cant&oacuten:*</b></label></td>'
									+ '<td style="width: 15%"><select id="ubicacion_canton_'
									+ listadoDirecciones[index].cotizacionDetalleId
									+ '" required="required" class="datosPymes"></select></td><td></td><td></td>'
									+ '<td style="width: 20%">'
									+ '<button type="button" class="btn btn-success btn-xs actualizar-btn" onclick="obtenerConfiguracionDireccion('
									+ listadoDirecciones[index].cotizacionDetalleId
									+ ')">'
									+ '<span class="glyphicon glyphicon glyphicon-edit"></span> Informaci&oacute;n para cotizar</button>'
									+ '</td>'
									+ '</tr>'
									+ '<tr>'
									+ '<td><label><b>C. Principal:*</b></label></td>'
									+ '<td><input type="text" id="ubicacion_calle_principal_'
									+ listadoDirecciones[index].cotizacionDetalleId
									+ '" required="required" class="datosPymes"></input></td>'
									+ '<td><label><b>N&uacutemero:*</b></label></td>'
									+ '<td><input type="text" id="ubicacion_numero_'
									+ listadoDirecciones[index].cotizacionDetalleId
									+ '" required="required" class="datosPymes"></input></td>'
									+ '<td><label><b>C. Secundaria:*</b></label></td>'
									+ '<td><input type="text" id="ubicacion_calle_secundaria_'
									+ listadoDirecciones[index].cotizacionDetalleId
									+ '" required="required" class="datosPymes"></input></td>'
									+ '<td style="width: 20%" class="sorting">'
									+ '<input type="hidden"  id="cotizacionDetalleId_' + listadoDirecciones[index].cotizacionDetalleId  
									+ '" value="'+ listadoDirecciones[index].cotizacionDetalleId +'"/>'
									+ '<button type="button" class="btn btn-danger btn-xs eliminar-extra-btn">'
									+ ' <span class="glyphicon glyphicon glyphicon-remove"></span> Eliminar'
									+ ' </button>' 
									+ '</td>'
									+ '</tr>'
									+ '</table>'
									+ '</div>' + '</div>' + '</td>' + '</td>' + '</tr>');
						
							$(".eliminar-extra-btn").bind({
								click : function() {
									var id=$(this).parent().children().first().val();
									var tableR=$(this).parent().parent().parent().parent().parent().parent().parent();
									
									$.ajax({
										url : '../PymeCotizacionController',
										data : {
											"tipoConsulta" : "eliminarObjetoDetalle",
											"cotizacionId" : $("#cotizacion_id").text(),
											"detalleId" : id
										},
										async : false,
										type : 'post',
										datatype : 'json',
										success : function(data) {
											if (data.success) {
												tableR.remove();
												var numeroDirecciones = parseInt($("#contadorDirecciones").val()) - parseInt(1);
												$("#contadorDirecciones").val(numeroDirecciones);
											}
										}
									});
								}
							});
							
							$("#contadorDirecciones").val(listadoDirecciones[index].cotizacionDetalleId);

							// Consultar provincias
							cargarProvincias(listadoDirecciones[index].cotizacionDetalleId);

							$("#ubicacion_provincia_"+listadoDirecciones[index].cotizacionDetalleId).change(function (e) {
								obtenerCantonPorProvincia('',listadoDirecciones[index].cotizacionDetalleId);
							});
							$("#ubicacion_provincia_"+listadoDirecciones[index].cotizacionDetalleId).val(listadoDirecciones[index].provinciaId);
							obtenerCantonPorProvincia(listadoDirecciones[index].cantonId, listadoDirecciones[index].cotizacionDetalleId);
							$("#cotizacionDetalleId_"+listadoDirecciones[index].cotizacionDetalleId).val(listadoDirecciones[index].cotizacionDetalleId);
							$("#ubicacion_calle_principal_"+listadoDirecciones[index].cotizacionDetalleId).val(listadoDirecciones[index].callePrincipal);
							$("#ubicacion_numero_"+listadoDirecciones[index].cotizacionDetalleId).val(listadoDirecciones[index].numeroDireccion);
							$("#ubicacion_calle_secundaria_"+listadoDirecciones[index].cotizacionDetalleId).val(listadoDirecciones[index].calleSecundaria);

						}
					});	

					//Creo las coberturas generales configuradas
					var coberturasConfiguradas= etapa2.coberturas;
					$.each(coberturasConfiguradas, function(index) {
						if(coberturasConfiguradas[index].tipoOrigen=="ADICIONALES"){
							cargarCobertura(coberturasConfiguradas[index].configuracionCoberturaId, 
									"adicionales", 
									coberturasConfiguradas[index].tasaSugerida, 
									coberturasConfiguradas[index].tasaIngresada, 
									coberturasConfiguradas[index].valorIngresado, 
									coberturasConfiguradas[index].primaCalculada);
						}
						else if(coberturasConfiguradas[index].tipoOrigen=="GENERAL"){
							cargarCobertura(coberturasConfiguradas[index].configuracionCoberturaId, 
									"general", 
									coberturasConfiguradas[index].tasaSugerida, 
									coberturasConfiguradas[index].tasaIngresada, 
									coberturasConfiguradas[index].valorIngresado, 
									coberturasConfiguradas[index].primaCalculada);
						}
						else if(coberturasConfiguradas[index].tipoOrigen=="ASISTENCIA"){
							cargarAsistencia(coberturasConfiguradas[index].configuracionCoberturaId, coberturasConfiguradas[index].primaCalculada);
						}
						else if(coberturasConfiguradas[index].tipoOrigen=="DEDUCIBLE"){
							
								cargarDeduciblesGenerales(coberturasConfiguradas[index].configuracionCoberturaId);
							
						}
					});

					etapa = 2;
				}

				if (datosCotizacion.etapa3 != null) {
					$("a[href='#next']").click();
					var etapa3=datosCotizacion.etapa3;
					if(etapa3.valoresCalculados != null){
						$("#inspeccion_prima_sin_impuestos").val(formatDollar(etapa3.valoresCalculados.valorPrima));
						$("#inspeccion_super_bancos").val(formatDollar(etapa3.valoresCalculados.valorSuperBancos));
						$("#inspeccion_seguro_campesino").val(formatDollar(etapa3.valoresCalculados.valorSeguroCampesino));
						$("#inspeccion_derecho_emision").val(formatDollar(etapa3.valoresCalculados.valorDerechosEmision));
						$("#inspeccion_subtotal").val(formatDollar(etapa3.valoresCalculados.valorSubTotal));
						$("#inspeccion_iva").val(formatDollar(etapa3.valoresCalculados.valorIva));
						$("#inspeccion_total").val(formatDollar(etapa3.valoresCalculados.valorTotal));
						
						$("#prima_sin_impuestos").val(formatDollar(etapa3.valoresCalculados.valorPrima));
						$("#super_bancos").val(formatDollar(etapa3.valoresCalculados.valorSuperBancos));
						$("#seguro_campesino").val(formatDollar(etapa3.valoresCalculados.valorSeguroCampesino));
						$("#derecho_emision").val(formatDollar(etapa3.valoresCalculados.valorDerechosEmision));
						$("#subtotal").val(formatDollar(etapa3.valoresCalculados.valorSubTotal));
						$("#iva").val(formatDollar(etapa3.valoresCalculados.valorIva));
						calculoIVAPuntoVentaCotizacion();
						$("#total").val(formatDollar(etapa3.valoresCalculados.valorTotal));
						$("#resumenTotalPago").children().first().empty().append("$" + etapa3.valoresCalculados.valorTotal);
					}
					if(datosCotizacion.estadoCotizacion=="Pendiente" || datosCotizacion.estadoCotizacion=="Pendiente Asignar Inspector" || datosCotizacion.estadoCotizacion=="Pendiente de Inspeccion")
						$("#registrarParaInspeccion").hide();
					else
						$("#registrarParaInspeccion").show();
					etapa = 3;
				}

				if (datosCotizacion.etapa4 != null) {
					$("a[href='#next']").click();
					var etapa4 = datosCotizacion.etapa4;
					$("#contador_endosos_beneficiario").val("1");
					if (etapa4.endosoBeneficiario != null) {
						if (etapa4.endosoBeneficiario.endososBeneficiario != null || etapa4.endosoBeneficiario.endososBeneficiario != ""){
							$("#divEndosoBeneficiario table").remove();
							var listadoEndosos=etapa4.endosoBeneficiario.endososBeneficiario;
							$.each(listadoEndosos, function (index) {
								cargarPestanaEndosoBeneficiario(
										listadoEndosos[index].monto,
										listadoEndosos[index].beneficiarioId,
										listadoEndosos[index].rubro,
										listadoEndosos[index].endosoBeneficiarioId);
								$("#contador_endosos_beneficiario").val(listadoEndosos[index].endosoBeneficiarioId);
							});
						}
						cargarPestanaEndosoAsegurado(etapa4.endosoBeneficiario.identificacion);
						$("#asegurado_id").val(etapa4.endosoBeneficiario.entidadId);
						$("#identificacion_asegurado").val(etapa4.endosoBeneficiario.identificacion);
						$("#nombres_asegurado").val(etapa4.endosoBeneficiario.nombres);
						$("#apellidos_asegurado").val(etapa4.endosoBeneficiario.apellidos);
						$("#nombre_completo_asegurado").val(etapa4.endosoBeneficiario.nombreCompleto);

						cargarTiposIdentificacionPymes(etapa4.endosoBeneficiario.tipoIdentificacion, 'asegurado', true);
						$("#endoso_beneficiario_id").val(etapa4.endosoBeneficiario.endosoBeneficiarioId);
					} else {
						cargarPestanaEndosoAsegurado(datosCotizacion.etapa1.identificacion);
					}

					if (etapa4.formaPago != null && etapa4.formaPago.pagoId != null) {

						$("#descargar_certificado").fadeIn("slow").removeAttr("disabled");
						$("#enviar_certificado").fadeIn("slow").removeAttr("disabled");
						$("#codigoPagoRegistrado").val(etapa4.formaPago.pagoId);

						cargarTiposIdentificacionPymes("", "banco", false);
						cargarTiposIdentificacionPymes("", "tarjeta", false);

						cargarFormasPago(etapa4.formaPago.formaPagoId, "formasPago");

						if (etapa4.formaPago.formaPagoNombre.trim().toString() == "DEBITO BANCARIO") {
							cargarTiposIdentificacionPymes("", "tarjeta", false);
							cargarTiposIdentificacionPymes(etapa4.formaPago.tipoIdentificacion, "banco", true);
							$("#bancoNumeroCuenta").val(etapa4.formaPago.numCuentaTarjeta);
							$("#bancoTitular").val(etapa4.formaPago.nombreTitular);
							$("#bancoIdentificacion").val(etapa4.formaPago.identificacionTitular);
							$("#txtcuotaInicialbancoPlazo").val(etapa4.formaPago.cuotaInicial);
							cargarFormasPago(etapa4.formaPago.institucionFinancieraId, "intitucionesFinancieras");
							$("#bancoTipoCuenta").val(etapa4.formaPago.tipoCuenta);
							//$("#bancoTipoIdentificacion option[value='"+ etapa3.formaPago.tipoIdentificacion +"']").attr("selected", "selected");
							$("#txtfechaInicialpago").val(etapa4.formaPago.fechaDebito);
							$("#bancoPlazo").val(etapa4.formaPago.plazo);
							if (etapa4.formaPago.cuotaInicial > 0)
								$("#bancoPlazo option[value='10']").remove();
						}

						if (etapa4.formaPago.formaPagoNombre.trim().toString() == "DEBITO TARJETA") {
							cargarTiposIdentificacionPymes(etapa4.formaPago.tipoIdentificacion, "tarjeta", true);
							cargarTiposIdentificacionPymes("", "banco", false);
							cargarFormasPago(etapa4.formaPago.institucionFinancieraId, "intitucionesFinancieras");
							cargarFormasPago(etapa4.formaPago.anioExpTarjeta, "aniosVigencia");
							$("#tarjetaTipoCuenta").val(etapa4.formaPago.tipoCuenta);
							$("#tarjetaNumero").val(etapa4.formaPago.numCuentaTarjeta);
							$("#tarjetaTitular").val(etapa4.formaPago.nombreTitular);
							$("#tarjetaMesExp").val(etapa4.formaPago.mesExpTarjeta);
							$("#txtcuotaInicialtarjetaPlazo").val(etapa4.formaPago.cuotaInicial);
							$("#tarjetaIdentificacion").val(etapa4.formaPago.identificacionTitular);
							$("#tarjetaPlazo").val(etapa4.formaPago.plazo);
							$("#txtfechaInicialpagoTarjeta").val(etapa4.formaPago.fechaDebito);
							if (etapa4.formaPago.cuotaInicial > 0)
								$("#tarjetaPlazo option[value='10']").remove();
						}

						if (etapa4.formaPago.formaPagoNombre.trim().toString() == "CREDITO CUOTAS") {
							var listadoCuotas = etapa4.listadoCuotas;
							var filasCuotas = "";
							$("#detallePagoCuotas").empty();
							if (etapa4.formaPago.cuotaInicial > 0)
								$("#cboFpPlazo option[value='10']").remove();
							$.each(listadoCuotas, function (index) {
								filasCuotas = filasCuotas + "<tr>" +
									"<td align='center'><b>" + listadoCuotas[index].cuotaOrden + "</b></td>";
								if (index == 0) { 
									$("#txtcuotaInicial").val(parseFloat(etapa4.formaPago.cuotaInicial).toFixed(2));
									$("#cboFpPlazo").val(etapa4.formaPago.plazo);
									filasCuotas = filasCuotas + "<td align='center'><input type='text' class='detalleChequesPagos' id='cuotaInicial'  size='12' style='margin: 5px; padding: 5px;' value='" + listadoCuotas[index].cuotaNumCheque + "' disabled='disabled'></td>";
								} else {
									filasCuotas = filasCuotas + "<td align='center'><input type='text' class='detalleChequesPagos' id='cuotaInicial'  size='12' style='margin: 5px; padding: 5px;' value='" + listadoCuotas[index].cuotaNumCheque + "' disabled='disabled'></td>";
								}

								filasCuotas = filasCuotas + "<td align='center'>" + parseFloat(listadoCuotas[index].cuotaValor).toFixed(2) + "</td>" +
									"<td align='center'>" + listadoCuotas[index].cuotaFechaPago + "</td>";
							});
							$("#detallePagoCuotas").append(filasCuotas);
							$("#rowDetallePagos").show();
							
							validarValoresPagos("CREDITO CUOTAS");
						}

					} else {
						cargarTiposIdentificacionPymes("", "banco",
								false);
						cargarTiposIdentificacionPymes("", "tarjeta",
								false);
					}

					$("#loading").fadeIn();
					$(".loading-indicator").delay(
							(1000 * parseInt($('#numero_vehiculos')
									.val()))).fadeOut();
					$("#tabbable").delay(1000).show();

					etapa = 4;

				} else {
					cargarTiposIdentificacionPymes("", "banco", false);
					cargarTiposIdentificacionPymes("", "tarjeta", false);
					// $("a[href='#next']").click();
					etapa = 4;
				}

				var tipoIdentificacionId = data.datosCotizacion.etapa1.tipoIdentificacion;
				if (tipoIdentificacionId == ''
					|| tipoIdentificacionId == '1'
						|| tipoIdentificacionId == '2'
							|| tipoIdentificacionId == '3') {
					// natural
					if (data.datosCotizacion == null
							|| data.datosCotizacion.datosFacturaCliente == null) {
						$("#nombre_direccion_solicitante").val(
								datosCotizacion.etapa1.nombreCompleto);
						$("#nombre_direccion_solicitante").val(
								datosCotizacion.etapa1.nombreCompleto);
						cargarDireccionFactura();
					} else {
						// $("a[href='#next']").click();
						// cargarDireccionFactura("solicitante",data.datosCotizacion.datosFacturaCliente);
						if (data.datosCotizacion.datosFacturaAsegurado != null)
							cargarDireccionFactura(
									"asegurado",
									data.datosCotizacion.datosFacturaAsegurado);
						else
							cargarDireccionFactura("asegurado");

					}
				} else {
					cargarDireccionFactura();

				}

				if (datosCotizacion.estadoCotizacion == "Borrador") {
					$("#filaAprobarCotizacion").hide();
					$("#filaAprobadaClienteCotizacion").hide();
					$("#filaEmitirCotizacion").hide();
					$("#datosFinales").hide();
					$("#filaEnviarCotizacion").show();
				} else if (datosCotizacion.estadoCotizacion == "Pendiente de Inspeccion"){
					$(".datosPymes").each(function() {
						$(this).attr("disabled", "disabled");
					});
				} else if (datosCotizacion.estadoCotizacion == "Pendiente Asignar Inspector"){
					$(".datosPymes").each(function() {
						$(this).attr("disabled", "disabled");
					});
				} 
				else if (datosCotizacion.estadoCotizacion == "Pendiente"){
					$(".datosPymes").each(function() {
						$(this).attr("disabled", "disabled");
					});
					$(".datosPymesPosterior").each(function() {
						$(this).removeAttr("disabled");
					});
				} else if (datosCotizacion.estadoCotizacion == "Emitido") {
					$('a[href="#finish"]').css('display', 'none');
					
					$(".datosPymes").each(function() {
						$(this).attr("disabled", "disabled");
					});

					$(":button").each(function() {
						$(this).attr("disabled", "disabled");
					});

					$(":checkbox").each(function() {
						$(this).attr("disabled", "disabled");
					});
					//Oculto los objetos de la fecha emisión
					$("#fecha_inicio_vigencia").hide();
					$("#lbl_FechaVigencia").hide();
					//
					$("#registrarParaInspeccion").hide();
					$("#enviarReporte").hide();
					$("#procesarReporte").removeAttr("disabled");
					$("#imprimirPoliza").show();
					$("#enviarPoliza").show();
					$("#imprimirPoliza").removeAttr("disabled");
					$("#enviarPoliza").removeAttr("disabled");
					if (etapa4.formaPago.formaPagoNombre.trim().toString() == "CONTADO"){
						$("#imprimirAutorizacionDebito").hide();
					}
					else{
						$("#imprimirAutorizacionDebito").show();
						$("#imprimirAutorizacionDebito").removeAttr("disabled");
					}
					if (etapa4.endosoBeneficiario.endososBeneficiario.length != 0){
						$("#imprimirEndosoBeneficiario").show();
						$("#imprimirEndosoBeneficiario").removeAttr("disabled");
					}
					else{
						$("#imprimirEndosoBeneficiario").hide();
					}
						
					$("#imprimirVinculacionCliente").show();
					$("#imprimirVinculacionCliente").removeAttr("disabled");
					$("a[href='#next']").click();
					$("a[href='#next']").click();
				}
			} else {
				alert(data.error);
			}
		}
	});
	return etapa;
}


function cargarDeduciblesGenerales(configuracionCoberturaId) {
	$.ajax({
		url : '../PymeConfiguracionCoberturaController',
		data : {
			"tipoConsulta" : "traeDeducibleGeneralEspecifico",
			"configuracionCoberturaId" : configuracionCoberturaId
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			var listadoTipos = data.pymeTipoDeducibleArr;			
			$.each(listadoTipos, function (index) {
				if (typeof listadoTipos[0].textosDeducible !== "undefined") {
					var contadorDeducibles=0;
					$(".deducibleIDs").each(function() {
						var deducibleId = $(this).val();
						if(deducibleId==configuracionCoberturaId)
							contadorDeducibles++;
					});
					
					if(contadorDeducibles==0){
						$("#deducibles_general").append(
									'<tr id="deducible_cobertura_General_'
									+ configuracionCoberturaId
									+ '">'
									+ '<td><table><tr><td style="font-weight:bold">Deducible:&nbsp;'
									+ listadoTipos[index].nombreDeducible
									+ '<input type="hidden" class="deducibleIDs" id="deducible_cobertura_General_'+configuracionCoberturaId
									+ '" value="'+configuracionCoberturaId
									+ '"></td></tr><tr>'
									+ '<td><textarea disabled id="text_area_deducible_cobertura_'
									+ configuracionCoberturaId
									+ '" rows="2" cols="100">'
									+ listadoTipos[index].texto
									+ '</textarea><input type="hidden" id="ids_deducible_cobertura_'
									+ configuracionCoberturaId
									+ '" value="'+listadoTipos[index].idsDeducible
									+ '"><input type="hidden" id="valores_deducible_cobertura_'
									+ configuracionCoberturaId
									+ '" value="'+listadoTipos[index].valoresDeducible
									+ '"><input type="hidden" id="textos_deducible_cobertura_'
									+ configuracionCoberturaId
									+ '" value="'+listadoTipos[index].textosDeducible
									+ '"></td></tr></table></td></tr>');
						}
					}
			});
		}
	});
}


function cargarResumenValores() {
	$.ajax({
		url : '../PymesObjetoCotizacionController',
		data : {
			"tipoConsulta" : "obtenerResumenValores",
			"cotizacionId" : $("#cotizacion_id").text(),
		},
		type : 'POST',
		datatype : 'json',
		success : function(data) {
			$("#inspeccion_total_suma_asegurada").val(formatDollar(data.valorAsegurado));
			$("#inspeccion_prima_sin_impuestos").val(formatDollar(data.valorPrima));
			$("#inspeccion_super_bancos").val(formatDollar(data.valorSuperBancos));
			if (parseFloat(data.porcentajeDescuento) > 0)
				$("#inspeccion_filaDescuento").show();
			$("#inspeccion_porcentaje_descuento").val(formatDollar(data.porcentajeDescuento));
			$("#inspeccion_seguro_campesino").val(formatDollar(data.valorSeguroCampesino));
			$("#inspeccion_derecho_emision").val(formatDollar(data.valorDerechosEmision));
			$("#inspeccion_subtotal").val(formatDollar(data.valorSubTotal));
			$("#inspeccion_iva").val(formatDollar(data.valorIva));
			$("#inspeccion_total").val(formatDollar(data.valorTotal));

			//Cargo los valores del paso 4 forma de pago
			$("#total_suma_asegurada").val(formatDollar(data.valorAsegurado));
			$("#prima_sin_impuestos").val(formatDollar(data.valorPrima));
			$("#super_bancos").val(formatDollar(data.valorSuperBancos));
			if (parseFloat(data.porcentajeDescuento) > 0)
				$("#filaDescuento").show();
			$("#porcentaje_descuento").val(formatDollar(data.porcentajeDescuento));
			$("#seguro_campesino").val(formatDollar(data.valorSeguroCampesino));
			$("#derecho_emision").val(formatDollar(data.valorDerechosEmision));
			$("#subtotal").val(formatDollar(data.valorSubTotal));
			$("#iva").val(formatDollar(data.valorIva));
			$("#total").val(formatDollar(data.valorTotal));
			$("#resumenTotalPago").children().first().empty().append("$" + data.valorTotal);
			
			calculoIVAPuntoVentaCotizacion();
		}
	});
}
/*
 * METODO QUE CONSULTA LOS DATOS DE LA ENTIDAD EN BASE AL DOCUMENTO DE
 * IDENTIFICACIÃ“N Y MUESTRA LOS DATOS EN EL FORMULARIO CORRESPONDIENTE
 */
function cargarPorIdentificacion(formulario, valor) {
	var identificacion = valor;
	if ((identificacion.length >= 10 && $("#tipo_identificacion_principal")
			.val() != 2)
			|| (identificacion.length >= 5 && ($(
			"#tipo_identificacion_principal").val() == 2 || $(
			"#tipo_identificacion_principal").val() == ""))) {
		$(".loading_identificacion").fadeIn();
		var entidad = "";

		if (formulario == "datosClienteInicio")
			cargarTablaPorIdentificacion(identificacion);
		else if (formulario == "datosAsegurado")
			cargarTablaPorIdentificacion(identificacion);

		$.ajax({
			url : '../EntidadController',
			data : {
				"identificacion" : identificacion,
				"tipoConsulta" : "cargarPorIdentificacionEnsurance"
			},
			Async : false,
			type : 'POST',
			datatype : 'json',
			success : function(data) {
				if(!data.success){
					alert(data.mensajeValidacion);
					$(".loading_identificacion").fadeOut();
				}
				else {
					var entidad = data.entidad;
					if (entidad === undefined) {
						$("#nombres").val("");
						$("#apellidos").val("");
						$("#nombre_completo").val("");
						$("#email").val(data.email);
						$("#telefono").val(data.telefono);
						$("#celular").val(data.celular);
					} else if (entidad.clienteIdEnsurance == "") {
						/*
						 * $("#nombres").val(data.datosFactura.nombres);
						 * $("#apellidos").val(datosFactura.apellidos);
						 * $("#nombre_completo").val(datosFactura.nombre_completo);
						 * $("#email").val(datosFactura.email);
						 * $("#telefono").val(datosFactura.telefono);
						 * $("#celular").val(datosFactura.celular);
						 */
						$(".loading_identificacion").fadeOut();
					} else {
						if (entidad.clienteIdEnsurance != ""
							&& entidad.entidadIdEnsurance != "") {
							if (formulario == "datosClienteInicio") {
								$("#codigoEntidadEnsurance").val(
										entidad.entidadIdEnsurance);
								$("#codigoClienteEnsurance").val(
										entidad.clienteIdEnsurance);
								$("#nombres").val(entidad.nombre);
								$("#apellidos").val(entidad.apellido);
								$("#nombre_completo").val(
										entidad.apellido + entidad.nombre);
								$("#email").val(entidad.mail);
								$("#tipo_identificacion_principal").val(
										entidad.tipoIdentificacion).trigger(
										"change");
							}
	
							if (formulario == "formasPagoDebitoBancario") {
								$("#bancoTitular").val(
										entidad.nombre + " " 
										+ entidad.apellido);
								$("#bancoTipoIdentificacion").val(
										entidad.tipoIdentificacion);
							}
	
							if (formulario == "formasPagoDebitoTarjetas") {
								$("#tarjetaTitular").val(
										entidad.nombre + " " + entidad.apellido);
								$("#tarjetaTipoIdentificacion").val(
										entidad.tipoIdentificacion);
							}
	
							if (formulario == "datosAsegurado") {
								$("#nombres_asegurado").val(entidad.nombre);
								$("#apellidos_asegurado").val(entidad.apellido);
								$("#nombre_completo_asegurado").val(
										entidad.nombre + " " + entidad.apellido);
								cargarTiposIdentificacionPymes(
										entidad.tipoIdentificacion, "asegurado",
										true);
								// if(data.datosFactura!=null){
								// cargarDireccionFactura("asegurado",
								// data.datosFactura);
								// }
							}
						} else {
							$("#nombres").val("");
							$("#apellidos").val("");
							$("#nombre_completo").val("");
							$("#email").val("");
							$("#telefono").val("");
							$("#celular").val("");
						}
					}
					$(".loading_identificacion").fadeOut();
				}},
				error : function(xhr, ajaxOptions, thrownError) {
					$(".loading_identificacion").fadeOut();
				}
				
		});
	}
}

function mostrarAdicionalesSolicitante() {
	var opcion = $('#tipo_identificacion_principal').val();

	if (opcion == 1 || opcion == 2 || opcion == 3) {
		$("#filaNatural").show();
		$("#filaJuridica").hide();
	} else if (opcion == 4 || opcion == 5) {
		$("#filaNatural").hide();
		$("#filaJuridica").show();
	}
}

function mostrarAdicionalesAsegurado() {
	var opcion = $('#tipo_identificacion_asegurado').val();
	if (opcion == 1 || opcion == 2 || opcion == 3) {
		$("#filaNaturalAsegurado").show();
		$("#filaJuridicaAsegurado").hide();

		$("#ubicacionPersonaNatural").show();
		$("#datosPoliticaNatural").show();
		$("#situacionFinancieraNatural").show();
		$("#ubicacionPersonaJuridica").hide();
		$("#datosPoliticaJuridica").hide();
		$("#situacionFinancieraJuridica").hide();
		if (!cargadoDatosUPLA)
			cargarDatosEnUPLANatural({"" : ""});
		} else if (opcion == 4 || opcion == 5) {
		$("#filaNaturalAsegurado").hide();
		$("#filaJuridicaAsegurado").show();
		$("#ubicacionPersonaNatural").hide();
		$("#datosPoliticaNatural").hide();
		$("#situacionFinancieraNatural").hide();
		$("#ubicacionPersonaJuridica").show();
		$("#datosPoliticaJuridica").show();
		$("#situacionFinancieraJuridica").show();
		if (!cargadoDatosUPLA)
			cargarDatosEnUPLAJuridica({
				"" : ""
			});
	}
}

function cargarDatosEnUPLAJuridica(datosJuridica) {
	if (datosJuridica.ciudadJuridica != null)
		$("#ciudad_juridica").val(datosJuridica.ciudadJuridica);
	else
		$("#ciudad_juridica").val("");

	if (datosJuridica.zonaDireccionMatriz != null)
		$("#zona_direccion_matriz_juridica").val(
				datosJuridica.zonaDireccionMatriz).attr('required', 'required')
				.trigger('change');
	else
		$("#zona_direccion_matriz_juridica").val("").attr('required','required');

	if (datosJuridica.provinciaDireccionMatriz != null){
		cargarProvinciasPJuridica();
		$("#provincia_direccion_matriz_juridica").val(datosJuridica.provinciaDireccionMatriz);
	}
	else
		cargarProvinciasPJuridica();

	if (datosJuridica.zonaDireccionMatriz == "R") {
		if (datosJuridica.cantonDireccionMatriz != null) {
			if (datosJuridica.provinciaDireccionMatriz != null)
				obtenerCantonPorProvinciaPJuridica(datosJuridica.cantonDireccionMatriz);
		} else {
			if (datosJuridica.provinciaDireccionMatriz != null)
				obtenerCantonPorProvinciaPJuridica("");
		}

		if (datosJuridica.parroquiaDireccionMatriz != null) {
			if (datosJuridica.cantonDireccionMatriz != null)
				cargarParroquias(datosJuridica.parroquiaDireccionMatriz,
						datosJuridica.cantonDireccionMatriz,
				"direccion_matriz_juridica");
		} else {
			if (datosJuridica.cantonDireccionMatriz != null)
				cargarParroquias("", datosJuridica.cantonDireccionMatriz,
				"direccion_matriz_juridica");
		}

	} else {
		if (datosJuridica.ciudadDireccionMatriz != null) {
				obtenerCiudadesPorProvinciaPJuridica(datosJuridica.ciudadDireccionMatriz);
				$("#ciudad_direccion_matriz_juridica").val(datosJuridica.ciudadDireccionMatriz);
		} else {
			if (datosJuridica.provinciaDireccionMatriz != null){
				obtenerCiudadesPorProvinciaPJuridica("");
				$("#ciudad_direccion_matriz_juridica").val("");
			}
		}
	}

	if (datosJuridica.callePrincipalMatriz != null)
		$("#calle_principal_direccion_juridica").val(
				datosJuridica.callePrincipalMatriz);
	else
		$("#calle_principal_direccion_juridica").val("");

	if (datosJuridica.numeroDireccionMatriz != null)
		$("#numero_direccion_juridica")
		.val(datosJuridica.numeroDireccionMatriz);
	else
		$("#numero_direccion_juridica").val("");

	if (datosJuridica.calleSecundariaMatriz != null)
		$("#calle_secundaria_direccion_juridica").val(
				datosJuridica.calleSecundariaMatriz);
	else
		$("#calle_secundaria_direccion_juridica").val("");

	if (datosJuridica.referenciaDireccionMatriz != null)
		$("#referencia_direccion_juridica").val(
				datosJuridica.referenciaDireccionMatriz);
	else
		$("#referencia_direccion_juridica").val("");

	if (datosJuridica.sucursalDireccion != null)
		$("#direccion_sucursal_juridica").val(datosJuridica.sucursalDireccion);
	else
		$("#direccion_sucursal_juridica").val("");

	if (datosJuridica.sucursalCiudad != null)
		$("#ciudad_sucursal_juridica").val(datosJuridica.sucursalCiudad);
	else
		$("#ciudad_sucursal_juridica").val("");

	if (datosJuridica.telefono != null)
		$("#telefono_juridica").val(datosJuridica.telefono);
	else
		$("#telefono_juridica").val("");

	if (datosJuridica.fax != null)
		$("#fax_juridica").val(datosJuridica.fax);
	else
		$("#fax_juridica").val("");

	// actividad persona natural

	if (datosJuridica.nombresRepresentanteLegal != null)
		$("#nombres_representante_juridica").val(
				datosJuridica.nombresRepresentanteLegal);
	else
		$("#nombres_representante_juridica").val("");

	if (datosJuridica.apellidosRepresentanteLegal != null)
		$("#apellidos_representante_juridica").val(
				datosJuridica.apellidosRepresentanteLegal);
	else
		$("#apellidos_representante_juridica").val("");

	if (datosJuridica.tipoIdentificacionRepresentante != null)
		cargarTiposIdentificacionPymes(
				datosJuridica.tipoIdentificacionRepresentante,
				"representante_juridica", true);
	else
		cargarTiposIdentificacionPymes("", "representante_juridica", false);

	if (datosJuridica.identificacionRepresentante != null)
		$("#identificacion_representante_juridica").val(
				datosJuridica.identificacionRepresentante);
	else
		$("#identificacion_representante_juridica").val("");

	if (datosJuridica.lugarNacimientoRepresentante != null)
		$("#lugar_nacimiento_representante_juridica").val(
				datosJuridica.lugarNacimientoRepresentante);
	else
		$("#lugar_nacimiento_representante_juridica").val("");

	if (datosJuridica.fechaNacimiento != null) {
		var dia = datosJuridica.fechaNacimiento.date < 10 ? '0'
				+ datosJuridica.fechaNacimiento.date
				: datosJuridica.fechaNacimiento.date;
		var mes = datosJuridica.fechaNacimiento.month < 9 ? "0"
				+ (datosJuridica.fechaNacimiento.month + 1)
				: (datosJuridica.fechaNacimiento.month + 1);
				var anio = (1900 + datosJuridica.fechaNacimiento.year);
				var aux = '' + anio + '-' + mes + '-' + dia;

				$("#fecha_nacimiento_representante_juridica").val(aux);
	} else
		$("#fecha_nacimiento_representante_juridica").val("");

	if (datosJuridica.residenciaRepresentante != null)
		$("#residencia_representante_juridica").val(
				datosJuridica.residenciaRepresentante);
	else
		$("#residencia_representante_juridica").val("");


	if (datosJuridica.cargoExpuesta != null)
		$("#cargo_expuesta_representante_juridica").val(
				datosJuridica.cargoExpuesta);
	else
		$("#cargo_expuesta_representante_juridica").val("");

	if (datosJuridica.expuestoFamiliar != null)
		$("#expuesto_familiar_juridica").val(
				datosJuridica.expuestoFamiliar ? 1 : 0);
	else
		$("#expuesto_familiar_juridica").val("");

	if (datosJuridica.parentescoExpuestoFamiliar != null)
		$("#parentesco_expuesto_familiar_juridico").val(
				datosJuridica.parentescoExpuestoFamiliar);
	else
		$("#parentesco_expuesto_familiar_juridico").val("");

	if (datosJuridica.cargoExpuestoFamiliar != null)
		$("#cargo_expuesto_familiar_juridica").val(
				datosJuridica.cargoExpuestoFamiliar);
	else
		$("#cargo_expuesto_familiar_juridica").val("");

	if (datosJuridica.apellidoPaternoConyuge != null)
		$("#apellido_paterno_conyuge_juridica").val(
				datosJuridica.apellidoPaternoConyuge);
	else
		$("#apellido_paterno_conyuge_juridica").val("");

	if (datosJuridica.apellidoMaternoConyuge != null)
		$("#apellido_materno_conyuge_juridica").val(
				datosJuridica.apellidoMaternoConyuge);
	else
		$("#apellido_materno_conyuge_juridica").val("");

	if (datosJuridica.nombreConyuge != null)
		$("#nombre_conyuge_juridica").val(datosJuridica.nombreConyuge);
	else
		$("#nombre_conyuge_juridica").val("");

	if (datosJuridica.tipoIdentificacionConyuge != null)
		cargarTiposIdentificacionPymes(datosJuridica.tipoIdentificacionConyuge,
				"conyugue_juridica", true);
	else
		cargarTiposIdentificacionPymes("", "conyugue_juridica", false);

	if (datosJuridica.identificacionConyuge != null)
		$("#identificacion_conyuge_juridica").val(
				datosJuridica.identificacionConyuge);
	else
		$("#identificacion_conyuge_juridica").val("");

	if (datosJuridica.salarioMensual != null)
		$("#salario_mensual_juridica").val(datosJuridica.salarioMensual);
	else
		$("#salario_mensual_juridica").val("");

	if (datosJuridica.activos != null)
		$("#activos_juridica").val(datosJuridica.activos);
	else
		$("#activos_juridica").val("");

	if (datosJuridica.otrosIngresos != null)
		$("#otros_ingresos_juridica").val(datosJuridica.otrosIngresos);
	else
		$("#otros_ingresos_juridica").val("");

	if (datosJuridica.pasivos != null)
		$("#pasivos_juridica").val(datosJuridica.pasivos);
	else
		$("#pasivos_juridica").val("");

	if (datosJuridica.egresos != null)
		$("#egresos_juridica").val(datosJuridica.egresos);
	else
		$("#egresos_juridica").val("");

	if (datosJuridica.patrimonio != null)
		$("#patrimonio_juridica").val(datosJuridica.patrimonio);
	else
		$("#patrimonio_juridica").val("");

	if (datosJuridica.ingresoEgreso != null)
		$("#ingresos_egresos_juridica").val(datosJuridica.ingresoEgreso);
	else
		$("#ingresos_egresos_juridica").val("");

	if (datosJuridica.esAsegurado != null)
		$("#es_asegurado_juridica").val(datosJuridica.esAsegurado ? 1 : 0);
	else
		$("#es_asegurado_juridica").val("");

	if (datosJuridica.esBeneficiario != null)
		$("#es_beneficiario_juridica")
		.val(datosJuridica.esBeneficiario ? 1 : 0);
	else
		$("#es_beneficiario_juridica").val("");

	if (datosJuridica.tipoIdentificacionAsegurado != null)
		cargarTiposIdentificacionPymes(
				datosJuridica.tipoIdentificacionAsegurado,
				"asegurado_juridica", true);
	else
		cargarTiposIdentificacionPymes("", "asegurado_juridica", false);

	if (datosJuridica.identificacionAsegurado != null)
		$("#identificacion_asegurado_juridica").val(
				datosJuridica.identificacionAsegurado);
	else
		$("#identificacion_asegurado_juridica").val("");

	if (datosJuridica.nombreCompletoAsegurado != null)
		$("#nombre_asegurado_juridica").val(
				datosJuridica.nombreCompletoAsegurado);
	else
		$("#nombre_asegurado_juridica").val("");

	if (datosJuridica.direccionAsegurado != null)
		$("#direccion_asegurado_juridica")
		.val(datosJuridica.direccionAsegurado);
	else
		$("#direccion_asegurado_juridica").val("");

	if (datosJuridica.telefonoAsegurado != null)
		$("#telefono_asegurado_juridica").val(datosJuridica.telefonoAsegurado);
	else
		$("#telefono_asegurado_juridica").val("");

	if (datosJuridica.celularAsegurado != null)
		$("#celular_asegurado_juridica").val(datosJuridica.celularAsegurado);
	else
		$("#celular_asegurado_juridica").val("");

	if (datosJuridica.relacionAsegurado != null)
		$("#relacion_asegurado_juridica").val(datosJuridica.relacionAsegurado);
	else
		$("#relacion_asegurado_juridica").val("");

	// beneficiario
	if (datosJuridica.tipoIdentificacionBeneficiario != null)
		cargarTiposIdentificacionPymes(
				datosJuridica.tipoIdentificacionBeneficiario,
				"beneficiario_juridica", true);
	else
		cargarTiposIdentificacionPymes("", "beneficiario_juridica", false);

	if (datosJuridica.identificacionBeneficiario != null)
		$("#identificacion_beneficiario_juridica").val(
				datosJuridica.identificacionBeneficiario);
	else
		$("#identificacion_beneficiario_juridica").val("");

	if (datosJuridica.nombreCompletoBeneficiario != null)
		$("#nombre_beneficiario_juridica").val(
				datosJuridica.nombreCompletoBeneficiario);
	else
		$("#nombre_beneficiario_juridica").val("");

	if (datosJuridica.direccionBeneficiario != null)
		$("#direccion_beneficiario_juridica").val(
				datosJuridica.direccionBeneficiario);
	else
		$("#direccion_beneficiario_juridica").val("");

	if (datosJuridica.telefonoBeneficiario != null)
		$("#telefono_beneficiario_juridica").val(
				datosJuridica.telefonoBeneficiario);
	else
		$("#telefono_beneficiario_juridica").val("");

	if (datosJuridica.celularBeneficiario != null)
		$("#celular_beneficiario_juridica").val(
				datosJuridica.celularBeneficiario);
	else
		$("#celular_beneficiario_juridica").val("");

	if (datosJuridica.relacionBeneficiario != null)
		$("#relacion_beneficiario_juridica").val(
				datosJuridica.relacionBeneficiario);
	else
		$("#relacion_beneficiario_juridica").val("");

	cargadoDatosUPLA = true;

}

function cargarDatosEnUPLANatural(datosNatural) {
	if (datosNatural.lugarNacimiento != null)
		$("#lugar_nacimiento_natural").val(datosNatural.lugarNacimiento);
	else
		$("#lugar_nacimiento_natural").val("");

	if (datosNatural.fechaNacimiento != null) {
		var dia = datosNatural.fechaNacimiento.date < 10 ? '0'
				+ datosNatural.fechaNacimiento.date
				: datosNatural.fechaNacimiento.date;
		var mes = datosNatural.fechaNacimiento.month < 9 ? "0"
				+ (datosNatural.fechaNacimiento.month + 1)
				: (datosNatural.fechaNacimiento.month + 1);
				var anio = (1900 + datosNatural.fechaNacimiento.year);
				var aux = '' + anio + '-' + mes + '-' + dia;

				$("#fecha_nacimiento_natural").val(aux);
	} else
		$("#fecha_nacimiento_natural").val("");

	if (datosNatural.zonaDireccionCliente != null)
		$("#zona_direccion_natural").val(datosNatural.zonaDireccionCliente)
		.attr('required', 'required').trigger('change');
	else
		$("#zona_direccion_natural").val("").attr('required', 'required');

	if (datosNatural.provinciaDireccionCliente != null){
		cargarProvinciasPNatural();
		$("#provincia_direccion_cliente_natural").val(datosNatural.provinciaDireccionCliente);
	}
	else
		cargarProvinciasPNatural();

	if (datosNatural.zonaDireccionCliente == "R") {
		if (datosNatural.cantonDireccionCliente != null) {
			if (datosNatural.provinciaDireccionCliente != null)
				cargarCantones(datosNatural.cantonDireccionCliente,
						datosNatural.provinciaDireccionCliente,
				"direccion_cliente_natural");
		} else {
			if (datosNatural.provinciaDireccionCliente != null)
				cargarCantones("", datosNatural.provinciaDireccionCliente,
				"direccion_cliente_natural");
		}

		if (datosNatural.parroquiaDireccionCliente != null) {
			if (datosNatural.cantonDireccionCliente != null)
				cargarParroquias(datosNatural.parroquiaDireccionCliente,
						datosNatural.cantonDireccionCliente,
				"direccion_cliente_natural");
		} else {
			if (datosNatural.cantonDireccionCliente != null)
				cargarParroquias("", datosNatural.cantonDireccionCliente,
				"direccion_cliente_natural");
		}

	} else {
		if (datosNatural.ciudadDireccionCliente != null) {
			if (datosNatural.provinciaDireccionCliente != null)
				obtenerCiudadesPorProvinciaPNatural(
						datosNatural.ciudadDireccionCliente,
						datosNatural.provinciaDireccionCliente,
				"direccion_cliente_natural");
		} else {
			if (datosNatural.provinciaDireccionCliente != null)
				obtenerCiudadesPorProvinciaPNatural("",
						datosNatural.provinciaDireccionCliente,
				"direccion_cliente_natural");
		}
	}

	if (datosNatural.callePrincipalCliente != null)
		$("#calle_principal_direccion_natural").val(
				datosNatural.callePrincipalCliente);
	else
		$("#calle_principal_direccion_natural").val("");

	if (datosNatural.numeroDireccionCliente != null)
		$("#numero_direccion_natural").val(datosNatural.numeroDireccionCliente);
	else
		$("#numero_direccion_natural").val("");

	if (datosNatural.calleSecundariaCliente != null)
		$("#calle_secundaria_direccion_natural").val(
				datosNatural.calleSecundariaCliente);
	else
		$("#calle_secundaria_direccion_natural").val("");

	if (datosNatural.referenciaDireccionCliente != null)
		$("#referencia_direccion_natural").val(
				datosNatural.referenciaDireccionCliente);
	else
		$("#referencia_direccion_natural").val("");

	if (datosNatural.telefonoCliente != null)
		$("#telefono_cliente_natural").val(datosNatural.telefonoCliente);
	else
		$("#telefono_cliente_natural").val("");

	if (datosNatural.celularCliente != null)
		$("#celular_cliente_natural").val(datosNatural.celularCliente);
	else
		$("#celular_cliente_natural").val("");

	/*
	 * if (datosNatural.generoCliente != null)
	 * $("#genero_cliente_natural").html(genero).val(datosNatural.generoCliente);
	 * else $("#genero_cliente_natural").html(genero).val("");
	 */

	if (datosNatural.mail != null)
		$("#mail_cliente_natural").val(datosNatural.mail);
	else
		$("#mail_cliente_natural").val("");

	/*
	 * if (datosNatural.tipoActividadCliente != null)
	 * $("#tipo_actividad_cliente_natural").val(datosNatural.tipoActividadCliente);
	 * else $("#tipo_actividad_cliente_natural").val("");
	 */

	if (datosNatural.cargoOcupaCliente != null)
		$("#cargo_ocupa_cliente_natural").val(datosNatural.cargoOcupaCliente);
	else
		$("#cargo_ocupa_cliente_natural").val("");

	if (datosNatural.expuestoCliente != null)
		$("#expuesto_cliente_natural").val(datosNatural.expuesto ? 1 : 0);
	else
		$("#expuesto_cliente_natural").val("");

	if (datosNatural.cargoExpuestoCliente != null)
		$("#cargo_expuesto_cliente_natural").val(datosNatural.cargoExpuesta);
	else
		$("#cargo_expuesto_cliente_natural").val("");

	if (datosNatural.expuestoFamiliar != null)
		$("#expuesto_familiar_natural").val(
				datosNatural.expuestoFamiliar ? 1 : 0);
	else
		$("#expuesto_familiar_natural").val("");

	if (datosNatural.parentescoExpuestoFamiliar != null)
		$("#parentesco_expuesto_familiar_natural").val(
				datosNatural.parentescoExpuestoFamiliar);
	else
		$("#parentesco_expuesto_familiar_natural").val("");

	if (datosNatural.cargoExpuestoFamiliar != null)
		$("#cargo_expuesto_familiar_natural").val(
				datosNatural.cargoExpuestoFamiliar);
	else
		$("#cargo_expuesto_familiar_natural").val("");

	if (datosNatural.apellidoPaternoConyuge != null)
		$("#apellido_paterno_conyuge_natural").val(
				datosNatural.apellidoPaternoConyuge);
	else
		$("#apellido_paterno_conyuge_natural").val("");

	if (datosNatural.apellidoMaternoConyuge != null)
		$("#apellido_materno_conyuge_natural").val(
				datosNatural.apellidoMaternoConyuge);
	else
		$("#apellido_materno_conyuge_natural").val("");

	if (datosNatural.nombreConyuge != null)
		$("#nombre_conyuge_natural").val(datosNatural.nombreConyuge);
	else
		$("#nombre_conyuge_natural").val("");

	if (datosNatural.tipoIdentificacionConyuge != null)
		cargarTiposIdentificacionPymes(datosNatural.tipoIdentificacionConyuge,
				"conyuge_natural", true);
	else
		cargarTiposIdentificacionPymes("", "conyuge_natural", false);

	if (datosNatural.identificacionConyuge != null)
		$("#identificacion_conyuge_natural").val(
				datosNatural.identificacionConyuge);
	else
		$("#identificacion_conyuge_natural").val("");

	if (datosNatural.salarioMensual != null)
		$("#salario_mensual_natural").val(datosNatural.salarioMensual);
	else
		$("#salario_mensual_natural").val("");

	if (datosNatural.activos != null)
		$("#activos_natural").val(datosNatural.activos);
	else
		$("#activos_natural").val("");

	if (datosNatural.otrosIngresos != null)
		$("#otros_ingresos_natural").val(datosNatural.otrosIngresos);
	else
		$("#otros_ingresos_natural").val("");

	if (datosNatural.pasivos != null)
		$("#pasivos_natural").val(datosNatural.pasivos);
	else
		$("#pasivos_natural").val("");

	if (datosNatural.egresos != null)
		$("#egresos_natural").val(datosNatural.egresos);
	else
		$("#egresos_natural").val("");

	if (datosNatural.patrimonio != null)
		$("#patrimonio_natural").val(datosNatural.patrimonio);
	else
		$("#patrimonio_natural").val("");

	if (datosNatural.ingresoEgreso != null)
		$("#ingresos_egresos_natural").val(datosNatural.ingresoEgreso);
	else
		$("#ingresos_egresos_natural").val("");

	/*
	 * if (datosNatural.esAsegurado != null)
	 * $("#es_asegurado_natural").val(datosNatural.esAsegurado?1:0); else
	 * $("#es_asegurado_natural").val("");
	 * 
	 * if (datosNatural.esBeneficiario != null)
	 * $("#es_beneficiario_natural").val(datosNatural.esBeneficiario?1:0); else
	 * $("#es_beneficiario_natural").val("");
	 */

	if (datosNatural.tipoIdentificacionAsegurado != null)
		cargarTiposIdentificacionPymes(
				datosNatural.tipoIdentificacionAsegurado, "asegurado_natural",
				true);
	else
		cargarTiposIdentificacionPymes("", "asegurado_natural", false);

	if (datosNatural.identificacionAsegurado != null)
		$("#identificacion_asegurado_natural").val(
				datosNatural.identificacionAsegurado);
	else
		$("#identificacion_asegurado_natural").val("");

	if (datosNatural.nombreCompletoAsegurado != null)
		$("#nombre_asegurado_natural")
		.val(datosNatural.nombreCompletoAsegurado);
	else
		$("#nombre_asegurado_natural").val("");

	if (datosNatural.direccionAsegurado != null)
		$("#direccion_asegurado_natural").val(datosNatural.direccionAsegurado);
	else
		$("#direccion_asegurado_natural").val("");

	if (datosNatural.telefonoAsegurado != null)
		$("#telefono_asegurado_natural").val(datosNatural.telefonoAsegurado);
	else
		$("#telefono_asegurado_natural").val("");

	if (datosNatural.celularAsegurado != null)
		$("#celular_asegurado_natural").val(datosNatural.celularAsegurado);
	else
		$("#celular_asegurado_natural").val("");

	if (datosNatural.relacionAsegurado != null)
		$("#relacion_asegurado_natural").val(datosNatural.relacionAsegurado);
	else
		$("#relacion_asegurado_natural").val("");

	// beneficiario
	if (datosNatural.tipoIdentificacionBeneficiario != null)
		cargarTiposIdentificacionPymes(
				datosNatural.tipoIdentificacionBeneficiario,
				"beneficiario_natural", true);
	else
		cargarTiposIdentificacionPymes("", "beneficiario_natural", false);

	if (datosNatural.identificacionBeneficiario != null)
		$("#identificacion_beneficiario_natural").val(
				datosNatural.identificacionBeneficiario);
	else
		$("#identificacion_beneficiario_natural").val("");

	if (datosNatural.nombreCompletoBeneficiario != null)
		$("#nombre_beneficiario_natural").val(
				datosNatural.nombreCompletoBeneficiario);
	else
		$("#nombre_beneficiario_natural").val("");

	if (datosNatural.direccionBeneficiario != null)
		$("#direccion_beneficiario_natural").val(
				datosNatural.direccionBeneficiario);
	else
		$("#direccion_beneficiario_natural").val("");

	if (datosNatural.telefonoBeneficiario != null)
		$("#telefono_beneficiario_natural").val(
				datosNatural.telefonoBeneficiario);
	else
		$("#telefono_beneficiario_natural").val("");

	if (datosNatural.celularBeneficiario != null)
		$("#celular_beneficiario_natural")
		.val(datosNatural.celularBeneficiario);
	else
		$("#celular_beneficiario_natural").val("");

	if (datosNatural.relacionBeneficiario != null)
		$("#relacion_beneficiario_natural").val(
				datosNatural.relacionBeneficiario);
	else
		$("#relacion_beneficiario_natural").val("");

	cargadoDatosUPLA = true;

}

//Arreglo de elementos unicos JSON
function arregloUnicoJSON(obj) {
	var uniques = [];
	var stringify = {};
	for (var i = 0; i < obj.length; i++) {
		var keys = Object.keys(obj[i]);
		keys.sort(function(a, b) {
			return a - b;
		});
		var str = '';
		for (var j = 0; j < keys.length; j++) {
			str += JSON.stringify(keys[j]);
			str += JSON.stringify(obj[i][keys[j]]);
		}
		if (!stringify.hasOwnProperty(str)) {
			uniques.push(obj[i]);
			stringify[str] = true;
		}
	}
	return uniques;
}

function getParameterByName(name) {
	name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), results = regex
	.exec(location.search);
	return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g,
	" "));
}

//************
//***GRABAR COTIZACION Y OBJETO-GANADERO***
//************
function guardarCotizacion() {

	var pppv = $('#punto_venta').find(":selected").attr('pxpv');
	var esContribuyente = "0";
	if($("#es_contribuyente").is(":checked"))
		esContribuyente = "1";
	// alert(pppv);
	$.ajax({
		url : "../PymeCotizacionController",
		data : {
			"tipoConsulta" : "crear",
			"producto" : "PYMES",
			"grupoProductoId" : $("#grupo_productos").val(),
			"puntoVentaId" : $("#punto_venta").val(),
			"vigenciaPoliza" : $("#vigencia").val(),
			"tipoIdentificacion" : $("#tipo_identificacion_principal").val(),
			"productoXPuntoDeVenta" : pppv,
			"identificacion" : $("#identificacion").val(),
			"nombres" : $("#nombres").val(),
			"apellidos" : $("#apellidos").val(),
			"nombreCompleto" : $("#nombre_completo").val(),
			"email" : $("#email").val(),
			"telefono" : $("#telefono").val(),
			"celular" : $("#celular").val(),
			"agenteId" : $("#agentes").val(),
			"codigoEntidadEnsurance" : $("#codigoEntidadEnsurance").val(),
			"codigoClienteEnsurance" : $("#codigoClienteEnsurance").val(),
			"cotizacionId" : $("#cotizacion_id").text(),
			"tipoObjeto" : tipoObjeto,
			"grupoPorProductoId" : $("#productos").val(),
			"tasaProductoId" : $("#tasas").val(),
			"tasaProductoValor" : $("#tasa").val(),
			"esContribuyente" : esContribuyente,
		},
		type : 'post',
		datatype : 'json',
		success : function(data) {
			$("#cotizacion_id").text(data.cotizacionId);
			$("#punto_venta").attr("unidadNegocio", data.unidadNegocioId);
			// Validacion poner en la URL el id de la cotizacion
			var valorId = getParameterByName("id");
			if (valorId != null) {
				$.pushVar("id", data.cotizacionId, "", window.location.href);
			}
		}
	});
}

function guardarProductoPymes() {
	var resVal = false;
	
	var contadorCoberturaConError=0;
	$(".datosMontoAseguradoGen").each(function() {
		if($(this).val()=="" || $(this).val()=="0"){
			contadorCoberturaConError++;
		}else{
			var p2 = Number($(this).val());
			if (!p2)
				contadorCoberturaConError++;
		}
	});
	if(contadorCoberturaConError>0){
		alert("No se puede grabar la cotización, porque existe coberturas sin monto asegurado, monto no válido o con monto asegurado igual a 0");
		resVal=false;
		return resVal;
	}
	
	
	
	var coberturasGenerales = new Array();
	var contador = 0;
	//Lleno el objeto de las coberturas generales
	$.each(configCobertasPorProducto,
			function(index) {
		if(configCobertasPorProducto[index].tipoDeclaracion=="1"){
			if ($("#cobertura_"+ configCobertasPorProducto[index].configuracionCoberturaId).is(':checked')) {
				var tasaSugerida = 0;
				var tasaIngresada = 0;
				var montoAsegurado = 0;
				var primaCalculada = 0;
				var planGeneral = 0;
				if ($("#tasa_sugerida_"+ configCobertasPorProducto[index].configuracionCoberturaId).length)
					tasaSugerida = Number($("#tasa_sugerida_"+ configCobertasPorProducto[index].configuracionCoberturaId).val());
				if ($("#tasa_ingresada_"+ configCobertasPorProducto[index].configuracionCoberturaId).length)
					tasaIngresada = Number($("#tasa_ingresada_"+ configCobertasPorProducto[index].configuracionCoberturaId).val());
				if ($("#monto_asegurado_"+ configCobertasPorProducto[index].configuracionCoberturaId).length)
					montoAsegurado = Number($("#monto_asegurado_"+ configCobertasPorProducto[index].configuracionCoberturaId).val());
				if ($("#prima_neta_"+ configCobertasPorProducto[index].configuracionCoberturaId).length)
					primaCalculada = Number($("#prima_neta_"+ configCobertasPorProducto[index].configuracionCoberturaId).val());
				var cobertura = {
						configuracionCoberturaId : configCobertasPorProducto[index].configuracionCoberturaId,
						tasaSugerida : tasaSugerida,
						tasaIngresada : tasaIngresada,
						valorIngresado : montoAsegurado,
						primaCalculada : primaCalculada,
						tipoOrigen : configCobertasPorProducto[index].tipoCoberturaId==1?"GENERAL":"ADICIONALES",
						textoDeducible : "",
						idsDeducible : "",
						valoresDeducible : "",
						planGeneral :"",
						textosDeducible : ""
				};
				coberturasGenerales[contador] = cobertura;
				contador++;
			}
		}
	});

	//Lleno el objeto de las asistencias
	$.each(configAsistenciasPorProducto, function(index) {
		if ($("#asistencia_"+ configAsistenciasPorProducto[index].asistenciaId).is(':checked')) {
			var valorAsistencia = 0;
			if ($("#valor_asistencia_"+ configAsistenciasPorProducto[index].asistenciaId).length)
				valorAsistencia = Number($("#valor_asistencia_"+ configAsistenciasPorProducto[index].asistenciaId).val());
			var cobertura = {
					configuracionCoberturaId : configAsistenciasPorProducto[index].asistenciaId,
					tasaSugerida : 0,
					tasaIngresada : 0,
					valorIngresado : 0,
					primaCalculada : valorAsistencia,
					tipoOrigen : "ASISTENCIA",
					textoDeducible : "",
					idsDeducible : "",
					planGeneral :"",
					valoresDeducible : "",
					textosDeducible : ""
			};
			coberturasGenerales[contador] = cobertura;
			contador++;
		}
	});

	//Lleno el objeto de los deducibles
	$(".deducibleIDs").each(function() {
		var deducibleId = $(this).val();
		var textoDeducible = $("#text_area_deducible_cobertura_"+deducibleId).val();
		var idsDeducible = $("#ids_deducible_cobertura_"+deducibleId).val();
		var valoresDeducible = $("#valores_deducible_cobertura_"+deducibleId).val();
		var textosDeducible = $("#textos_deducible_cobertura_"+deducibleId).val();
		var planGeneral = 0;
		var cobertura = {
				configuracionCoberturaId : deducibleId,
				tasaSugerida : 0,
				tasaIngresada : 0,
				valorIngresado : 0,
				primaCalculada : 0,
				tipoOrigen : "DEDUCIBLE",
				textoDeducible : textoDeducible,
				idsDeducible : idsDeducible,
				planGeneral : "",
				valoresDeducible : valoresDeducible,
				textosDeducible : textosDeducible
		};
		coberturasGenerales[contador] = cobertura;
		contador++;
	});

	$.ajax({url : "../PymesObjetoCotizacionController",
		data : {
			"cotizacionId" : $("#cotizacion_id").text(),
			"tipoConsulta" : "crear",
			"coberturas" : JSON.stringify(coberturasGenerales)
		},
		type : 'post',
		async : false,
		datatype : 'json',
		success : function(data) {
			$("#msgPopupFichaPymeError").hide();
			if(data.success){
				$("#cotizacion_id").text(data.cotizacionId);
				resVal=true;
			}
			else{
				$("#lblContenidoMensaje").html(data.error);
				$("#msgPopupFichaPymeError").show();
				resVal=false;
			}
		}
	});
	return resVal;
}

/* GUARDAR EL ASEGURADO */
function guardarAsegurado(tipoConsulta) {

	var tipoIdentificacionAsegurado = $("#tipo_identificacion_asegurado").val();
	var identificacionAsegurado = $("#identificacion_asegurado").val();
	var nombreAsegurado = $("#nombres_asegurado").val();
	var apellidoAsegurado = $("#apellidos_asegurado").val();
	var nombreCompletoAsegurado = $("#nombre_completo_asegurado").val();
	var cotizacionId = $("#cotizacion_id").text();
	var aseguradoId = $("#asegurado_id").val();
	var valido = true;

	if (tipoIdentificacionAsegurado == ""
		|| tipoIdentificacionAsegurado == null) {
		$("#mensajeErrorEndosoBeneficiario").html(
		"Seleccione un tipo de identificación");
		$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
		$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
		$("#tipo_identificacion_asegurado").focus();
		valido = false;
	} else {
		if (tipoIdentificacionAsegurado != '4') {
			nombreCompletoAsegurado = apellidoAsegurado + ' ' + nombreAsegurado;
			if (nombreAsegurado == "" || nombreAsegurado == null) {
				$("#mensajeErrorEndosoBeneficiario").html(
				"Ingrese el nombre del asegurado");
				$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
				$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
				$("#nombres_asegurado").focus();
				valido = false;
			}
			if (apellidoAsegurado == "" || apellidoAsegurado == null) {
				$("#mensajeErrorEndosoBeneficiario").html(
				"Ingrese el apellido del asegurado");
				$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
				$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
				$("#apellidos_asegurado").focus();
				valido = false;
			}
		}
	}

	if (identificacionAsegurado == "" || identificacionAsegurado == null) {
		$("#mensajeErrorEndosoBeneficiario").html("Ingrese una identificación");
		$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
		$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
		$("#identificacion_asegurado").focus();
		valido = false;
	}

	if (nombreCompletoAsegurado == "" || nombreCompletoAsegurado == null) {
		$("#mensajeErrorEndosoBeneficiario").html(
		"Ingrese el nombre completo del asegurado");
		$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
		$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
		$("#nombre_completo_asegurado").focus();
		valido = false;
	}

	if (tipoConsulta == "guardar") {
		if (aseguradoId == "" || aseguradoId == null) {
			tipoConsulta = "crear";
		} else {
			tipoConsulta = "actualizar";
		}
	}
	if (valido)
		$.ajax({
					url : "../PymeEndosoBeneficiarioController",

			data : {
				"tipoConsulta" : tipoConsulta,
				"tipoIdentificacionAsegurado" : tipoIdentificacionAsegurado,
				"identificacionAsegurado" : identificacionAsegurado,
				"nombreAsegurado" : nombreAsegurado,
				"apellidoAsegurado" : apellidoAsegurado,
				"nombreCompletoAsegurado" : nombreCompletoAsegurado,
				"aseguradoId" : aseguradoId,
				"cotizacionId" : cotizacionId,
				"guardarAsegurado" : "1"
			},
			asyn : false,
			type : 'post',
			datatype : 'json',
			success : function(data) {
				if (data.success) {
					$.ajax({
						url : '../PymeCotizacionController',
						data : {
							"cotizacionId" : $("#cotizacion_id").text(),
							"etapaNumero" : "4",
							"tipoConsulta" : "cambiarEtapa"
						},
						asyn : false,
						type : 'post',
						datatype : 'json',
						success : function(datos) {
							$("#asegurado_id").val(data.aseguradoId);
							$("#mensajeGraboEndosoBeneficiario").html(
							"Se guard&oacute; el asegurado exitosamente");
							$("#msgPopupEndosoBeneficiarioGrabo").fadeIn("slow");
							$("#msgPopupEndosoBeneficiarioError").fadeOut("slow");
							if (tipoConsulta == 'eliminar') {
								$("#mensajeGraboEndosoBeneficiario").html(
								"Se elimin&oacute; el asegurado exitosamente");
								$("#asegurado_id").val('');
							}
						}
					});


				} else {
					$("#mensajeErrorEndosoBeneficiario").html(
					"No se pudo guardar el asegurado");
					if (tipoConsulta == 'eliminar')
						$("#mensajeGraboEndosoBeneficiario").html(
						"No se pudo eliminar el asegurado");
					$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
					$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
					alert(data.error);
				}
			}
		});
}

function guardarBeneficiario(tipoConsulta, itemId) {

	var beneficiario = $("#beneficiario_" + itemId).val();
	var monto = $("#valor_endoso_beneficiario_" + itemId).val();
	var cotizacionId = $("#cotizacion_id").text();
	var endosoBeneficiarioId = $("#endoso_beneficiario_id_"+itemId).val();
	var beneficiario_rubro=$("#beneficiario_rubro_"+itemId).val();
	var valido = true;

	if (beneficiario == "" || beneficiario == null) {
		$("#mensajeErrorEndosoBeneficiario").html("Seleccione un beneficiario");
		$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
		$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
		$("#beneficiario").focus();
		valido = false;
	}

	if (monto == "" || monto == null) {
		$("#mensajeErrorEndosoBeneficiario").html("Ingrese un monto");
		$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
		$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
		$("#valor_endoso_beneficiario").focus();
		valido = false;
	} 

	//reemplazo 1 por guardar
	if (tipoConsulta == 1) {
		if (endosoBeneficiarioId == "" || endosoBeneficiarioId == null) {
			tipoConsulta = "crear";
		} else {
			tipoConsulta = "actualizar";
		}
	}
	else{
		tipoConsulta = "eliminar";
	}
	if (valido)
		$.ajax({
			url : "../PymeEndosoBeneficiarioController",

			data : {
				"tipoConsulta" : tipoConsulta,
				"beneficiarioId" : beneficiario,
				"monto" : monto,
				"codigo" : endosoBeneficiarioId,
				"cotizacionId" : cotizacionId,
				"guardarAsegurado" : "0",
				"beneficiario_rubro" : beneficiario_rubro
			},
			type : 'post',
			datatype : 'json',
			success : function (data) {
				if (data.success) {
					$("#endoso_beneficiario_id_"+itemId).val(data.endosoBeneficiarioId);
					$("#mensajeGraboEndosoBeneficiario").html("Se guard&oacute; el beneficiario exitosamente");
					$("#msgPopupEndosoBeneficiarioGrabo").fadeIn("slow");
					$("#msgPopupEndosoBeneficiarioError").fadeOut("slow");
					//reemplazo 2 por eliminar
					if (tipoConsulta == "eliminar") {
						$("#fila_endoso_benficiario_" + itemId).remove();
						$("#mensajeGraboEndosoBeneficiario").html("Se elimin&oacute; el beneficiario exitosamente");
						$("#endoso_beneficiario_id_"+itemId).val('');
						$("#valor_endoso_beneficiario_"+itemId).val('');
						$("#beneficiario_"+itemId).val('');
						$("#beneficiario_rubro_"+itemId).val('');
					}

				} else {
					$("#mensajeErrorEndosoBeneficiario").html("No se pudo guardar el beneficiario");
					$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
					$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
					if (tipoConsulta == 'eliminar')
						$("#mensajeGraboEndosoBeneficiario").html("No se pudo eliminar el beneficiario");

					alert(data.error);
				}
			}
		});
}

function guardarDatosUPLAJuridicaEnGanadero() {

	var objetoSocial = $("#objeto_social_juridica").val();
	// var ciudad = $("#ciudad_direccion_matriz_juridica").val();
	var zonaDireccionMatriz = $("#zona_direccion_matriz_juridica").val();
	var provinciaDireccionMatriz = $("#provincia_direccion_matriz_juridica")
	.val();
	var cantonDireccionMatriz = $("#canton_direccion_matriz_juridica").val();
	var parroquiaDireccionMatriz = $("#parroquia_direccion_matriz_juridica")
	.val();
	var ciudadDireccionMatriz = $("#ciudad_direccion_matriz_juridica").val();
	var callePrincipalDireccion = $("#calle_principal_direccion_juridica")
	.val();
	var numeroDireccion = $("#numero_direccion_juridica").val();
	var calleSecundariaDireccion = $("#calle_secundaria_direccion_juridica")
	.val();
	var referenciaDireccion = $("#referencia_direccion_juridica").val();
	/*
	 * var direccionSucursal = $("#direccion_sucursal_juridica").val(); var
	 * ciudadSucursal = $("#ciudad_sucursal_juridica").val();
	 */
	var telefono = $("#telefono_juridica").val();
	var fax = $("#fax_juridica").val();
	var salarioMensual = $("#salario_mensual_juridica").val();
	var activos = $("#activos_juridica").val();
	var otrosIngresos = $("#otros_ingresos_juridica").val();
	var pasivos = $("#pasivos_juridica").val();
	var egresos = $("#egresos_juridica").val();
	var patrimonio = $("#patrimonio_juridica").val();
	var ingresosEgresos = $("#ingresos_egresos_juridica").val();
	
	var identificacion = $("#identificacion_asegurado").val();
	var genero = $("#genero_representante_juridico").val();
	var mail = $("#mail_representante_juridico").val();
	var celularRepresentante = $("#celular_representante_legal").val();
	var cotizacion = $("#cotizacion_id").text();
	// var ciudadJuridica = $("#ciudad_juridica").val();

	$.ajax({
		url : '../UPLAController',
		data : {
			"identificacion" : identificacion,
			"tipoConsulta" : "guardarDatosJuridica",
			"objetoSocial" : objetoSocial,
			"zonaDireccionMatriz" : zonaDireccionMatriz,
			"provinciaDireccionMatriz" : provinciaDireccionMatriz,
			"cantonDireccionMatriz" : cantonDireccionMatriz,
			"parroquiaDireccionMatriz" : parroquiaDireccionMatriz,
			"ciudadDireccionMatriz" : ciudadDireccionMatriz,
			"callePrincipalDireccion" : callePrincipalDireccion,
			"numeroDireccion" : numeroDireccion,
			"calleSecundariaDireccion" : calleSecundariaDireccion,
			"referenciaDireccion" : referenciaDireccion,
			"salarioMensual" : salarioMensual,
			"activos" : activos,
			"otrosIngresos" : otrosIngresos,
			"pasivos" : pasivos,
			"egresos" : egresos,
			"patrimonio" : patrimonio,
			"ingresosEgresos" : ingresosEgresos,
			"celularRepresentante" : celularRepresentante,
			/*
			 * "direccionSucursal":direccionSucursal,
			 * "ciudadSucursal":ciudadSucursal,
			 */
			"telefono" : telefono,
			"fax" : fax,
			/*
			 * "actividad":actividad,
			 * "nombresRepresentante":nombresRepresentante,
			 * "apellidosRepresentante":apellidosRepresentante,
			 * "tipoIdentificacionRepresentante":tipoIdentificacionRepresentante,
			 * "identificacionRepresentante":identificacionRepresentante,
			 * "lugarNacimientoRepresentante":lugarNacimientoRepresentante,
			 * "fechaNacimientoRepresentante":fechaNacimientoRepresentante,
			 * "residenciaRepresentante":residenciaRepresentante,
			 * //"paisRepresentante":paisRepresentante,
			 * "provinciaRepresentante":provinciaRepresentante,
			 * "ciudadRepresentante":ciudadRepresentante,
			 * "telefonoRepresentante":telefonoRepresentante,
			 * 
			 * "expuestoRepresentante":expuestoRepresentante,
			 * "cargoExpuestaRepresentante":cargoExpuestaRepresentante,
			 * "expuestoFamiliar":expuestoFamiliar,
			 * "parentescoExpuestoFamiliar":parentescoExpuestoFamiliar,
			 * "cargoExpuestoFamiliar":cargoExpuestoFamiliar,
			 * "apellidoPaternoConyuge":apellidoPaternoConyuge,
			 * "apellidoMaternoConyuge":apellidoMaternoConyuge,
			 * "nombreConyuge":nombreConyuge,
			 * "tipoIdentificacionConyugue":tipoIdentificacionConyugue,
			 * "identificacionConyuge":identificacionConyuge,
			 * "esAsegurado":esAsegurado, "esBeneficiario":esBeneficiario,
			 * "tipoIdentificacionAsegurado":tipoIdentificacionAsegurado,
			 * "identificacionAsegurado":identificacionAsegurado,
			 * "nombreAsegurado":nombreAsegurado,
			 * "direccionAsegurado":direccionAsegurado,
			 * "telefonoAsegurado":telefonoAsegurado,
			 * "celularAsegurado":celularAsegurado,
			 * "relacionAsegurado":relacionAsegurado,
			 * "tipoIdentificacionBeneficiario":tipoIdentificacionBeneficiario,
			 * "identificacionBeneficiario":identificacionBeneficiario,
			 * "nombreBeneficiario":nombreBeneficiario,
			 * "direccionBeneficiario":direccionBeneficiario,
			 * "telefonoBeneficiario":telefonoBeneficiario,
			 * "celularBeneficiario":celularBeneficiario,
			 * "relacionBeneficiario":relacionBeneficiario,
			 */
			"genero" : genero,
			"mail" : mail,
			// "ciudad":ciudadJuridica,
			"cotizacion" : cotizacion
		},
		type : 'post',
		datatype : 'json',
		success : function(datos) {

		}
	});
}

function guardarDatosUPLANaturalEnGanadero() {
	var lugarNacimiento = $("#lugar_nacimiento_natural").val();
	var fechaNacimiento = $("#fecha_nacimiento_natural").val();
	var zonaDireccionCliente = $("#zona_direccion_natural").val();
	var callePrincipalCliente = $("#calle_principal_direccion_natural").val();
	var calleSecundariaCliente = $("#calle_secundaria_direccion_natural").val();
	var numeroDireccionCliente = $("#numero_direccion_natural").val();
	var refenciaDireccionCliente = $("#referencia_direccion_natural").val();
	var provincia = $("#provincia_direccion_cliente_natural").val();
	var canton = $("#canton_direccion_cliente_natural").val();
	var parroquia = $("#parroquia_direccion_cliente_natural").val();
	var ciudad = $("#ciudad_direccion_cliente_natural").val();
	var telefono = $("#telefono_cliente_natural").val();
	var celular = $("#celular_cliente_natural").val();
	var mail = $("#mail_cliente_natural").val();
	var tipoIdentificacionAsegurado = $("#tipo_identificacion_asegurado").val();
	var identificacionAsegurado = $("#identificacion_asegurado").val();
	var nombreAsegurado = $("#nombres_asegurado").val();
	var apellidosAsegurado = $("#apellidos_asegurado").val();
	var domicilioAsegurado = $("#direccion_asegurado_natural").val();
	var salarioMensual = $("#salario_mensual_natural").val();
	var activo = $("#activos_natural").val();
	var otrosIngresos = $("#otros_ingresos_natural").val();
	var pasivos = $("#pasivos_natural").val();
	var egresos = $("#egresos_natural").val();
	var patrimonio = $("#patrimonio_natural").val();
	var ingresosEgresos = $("#ingresos_egresos_natural").val();
	var cotizacion = $("#cotizacion_id").text();
	var cargoOcupa = $("#cargo_ocupa_cliente_natural").val();
	/*
	 * var actividad = $("#actividad_economica_cliente_natural").select2("val");
	 * var tipoActividad = $("#tipo_actividad_cliente_natural").val(); var
	 * tipoRamoContrata = $("#ramo_contrata_cliente_natural").val(); var
	 * expuesto = $("#expuesto_cliente_natural").val(); var cargoExpuesta =
	 * $("#cargo_expuesto_cliente_natural").val(); var expuestoFamiliar =
	 * $("#expuesto_familiar_natural").val(); var parentescoFamiliar =
	 * $("#parentesco_expuesto_familiar_natural").val(); var cargoFamiliar =
	 * $("#cargo_expuesto_familiar_natural").val(); var apellidoPaternoConyuge =
	 * $("#apellido_paterno_conyuge_natural").val(); var apellidoMaternoConyuge =
	 * $("#apellido_materno_conyuge_natural").val(); var nombresConyuge =
	 * $("#nombre_conyuge_natural").val(); var tipoIdentificacionConyuge =
	 * $("#tipo_identificacion_conyuge_natural").val(); var
	 * identificacionConyuge = $("#identificacion_conyuge_natural").val();
	 * 
	 * var esAsegurado = $("#es_asegurado_natural").val(); var esBeneficiario =
	 * $("#es_beneficiario_natural").val();
	 * 
	 * var relacionAsegurado = $("#relacion_asegurado_natural").val(); var
	 * tipoIdentificacionBeneficiario =
	 * $("#tipo_identificacion_beneficiario_natural").val(); var
	 * identificacionBeneficiario =
	 * $("#identificacion_beneficiario_natural").val(); var nombreBeneficiario =
	 * $("#nombre_beneficiario_natural").val(); var domicilioBeneficiario =
	 * $("#direccion_beneficiario_natural").val(); var telefonoBeneficiario =
	 * $("#telefono_beneficiario_natural").val(); var celularBeneficiario =
	 * $("#celular_beneficiario_natural").val(); var relacionBeneficiario =
	 * $("#relacion_beneficiario_natural").val();
	 */

	$.ajax({
		url : '../UPLAController',
		data : {
			"identificacion" : identificacionAsegurado,
			"tipoConsulta" : "guardarDatosNatural",
			"lugarNacimiento" : lugarNacimiento,
			"fechaNacimiento" : fechaNacimiento,
			"zonaDireccionCliente" : zonaDireccionCliente,
			"callePrincipalCliente" : callePrincipalCliente,
			"calleSecundariaCliente" : calleSecundariaCliente,
			"numeroDireccionCliente" : numeroDireccionCliente,
			"refenciaDireccionCliente" : refenciaDireccionCliente,
			"provincia" : provincia,
			"canton" : canton,
			"parroquia" : parroquia,
			"ciudad" : ciudad,
			"telefono" : telefono,
			"celular" : celular,
			"tipoIdentificacionAsegurado" : tipoIdentificacionAsegurado,
			"identificacionAsegurado" : identificacionAsegurado,
			"nombreAsegurado" : nombreAsegurado,
			"apellidosAsegurado" : apellidosAsegurado,
			"domicilioAsegurado" : domicilioAsegurado,
			"salarioMensual" : salarioMensual,
			"activo" : activo,
			"otrosIngresos" : otrosIngresos,
			"pasivos" : pasivos,
			"egresos" : egresos,
			"patrimonio" : patrimonio,
			"ingresosEgresos" : ingresosEgresos,
			"cargoOcupa" : cargoOcupa,
			/*
			 * "actividad" : actividad, "tipoActividad" : tipoActividad,
			 * "tipoRamoContrata" : tipoRamoContrata, "expuesto" : expuesto,
			 * "cargoExpuesta" : cargoExpuesta, "expuestoFamiliar" :
			 * expuestoFamiliar, "parentescoFamiliar" : parentescoFamiliar,
			 * "cargoFamiliar" : cargoFamiliar, "apellidoPaternoConyuge" :
			 * apellidoPaternoConyuge, "apellidoMaternoConyuge" :
			 * apellidoMaternoConyuge, "nombresConyuge" : nombresConyuge,
			 * "tipoIdentificacionConyuge" : tipoIdentificacionConyuge,
			 * "identificacionConyuge" : identificacionConyuge, "esBeneficiario" :
			 * esBeneficiario,
			 * 
			 * "relacionAsegurado" : relacionAsegurado,
			 * "tipoIdentificacionBeneficiario" :
			 * tipoIdentificacionBeneficiario, "identificacionBeneficiario" :
			 * identificacionBeneficiario, "nombreBeneficiario" :
			 * nombreBeneficiario, "domicilioBeneficiario" :
			 * domicilioBeneficiario, "telefonoBeneficiario" :
			 * telefonoBeneficiario, "celularBeneficiario" :
			 * celularBeneficiario, "relacionBeneficiario" :
			 * relacionBeneficiario,
			 */
			"mail" : mail,
			"cotizacion" : cotizacion
		},
		type : 'post',
		datatype : 'json',
		success : function(datos) {

		}
	});
}

function guardarAsegurado(tipoConsulta) {
	var tipoIdentificacionAsegurado = $("#tipo_identificacion_asegurado").val();
	var identificacionAsegurado = $("#identificacion_asegurado").val();
	var nombreAsegurado = $("#nombres_asegurado").val();
	var apellidoAsegurado = $("#apellidos_asegurado").val();
	var nombreCompletoAsegurado = $("#nombre_completo_asegurado").val();
	var cotizacionId = $("#cotizacion_id").text();
	var aseguradoId = $("#asegurado_id").val();
	var valido = true;

	if (tipoIdentificacionAsegurado == ""
		|| tipoIdentificacionAsegurado == null) {
		$("#mensajeErrorEndosoBeneficiario").html(
		"Seleccione un tipo de identificación");
		$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
		$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
		$("#tipo_identificacion_asegurado").focus();
		valido = false;
	} else {
		if (tipoIdentificacionAsegurado != '4') {
			nombreCompletoAsegurado = apellidoAsegurado + ' ' + nombreAsegurado;
			if (nombreAsegurado == "" || nombreAsegurado == null) {
				$("#mensajeErrorEndosoBeneficiario").html(
				"Ingrese el nombre del asegurado");
				$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
				$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
				$("#nombres_asegurado").focus();
				valido = false;
			}
			if (apellidoAsegurado == "" || apellidoAsegurado == null) {
				$("#mensajeErrorEndosoBeneficiario").html(
				"Ingrese el apellido del asegurado");
				$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
				$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
				$("#apellidos_asegurado").focus();
				valido = false;
			}
		}
	}

	if (identificacionAsegurado == "" || identificacionAsegurado == null) {
		$("#mensajeErrorEndosoBeneficiario").html("Ingrese una identificación");
		$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
		$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
		$("#identificacion_asegurado").focus();
		valido = false;
	}

	if (nombreCompletoAsegurado == "" || nombreCompletoAsegurado == null) {
		$("#mensajeErrorEndosoBeneficiario").html(
		"Ingrese el nombre completo del asegurado");
		$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
		$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
		$("#nombre_completo_asegurado").focus();
		valido = false;
	}

	if (tipoConsulta == "guardar") {
		if (aseguradoId == "" || aseguradoId == null) {
			tipoConsulta = "crear";
		} else {
			tipoConsulta = "actualizar";
		}
	}
	if (valido)
		$.ajax({
			url : "../PymeEndosoBeneficiarioController",

			data : {
				"tipoConsulta" : tipoConsulta,
				"tipoIdentificacionAsegurado" : tipoIdentificacionAsegurado,
				"identificacionAsegurado" : identificacionAsegurado,
				"nombreAsegurado" : nombreAsegurado,
				"apellidoAsegurado" : apellidoAsegurado,
				"nombreCompletoAsegurado" : nombreCompletoAsegurado,
				"aseguradoId" : aseguradoId,
				"cotizacionId" : cotizacionId,
				"guardarAsegurado" : "1"
			},
			type : 'post',
			datatype : 'json',
			success : function(data) {
				if (data.success) {
					$.ajax({
						url : '../PymeCotizacionController',
						data : {
							"cotizacionId" : $("#cotizacion_id").text(),
							"etapaNumero" : "4",
							"tipoConsulta" : "cambiarEtapa"
						},
						asyn : false,
						type : 'post',
						datatype : 'json',
						success : function(datos) {
							$("#asegurado_id").val(data.aseguradoId);
							$("#mensajeGraboEndosoBeneficiario").html(
							"Se guard&oacute; el asegurado exitosamente");
							$("#msgPopupEndosoBeneficiarioGrabo").fadeIn("slow");
							$("#msgPopupEndosoBeneficiarioError").fadeOut("slow");
							if (tipoConsulta == 'eliminar') {
								$("#mensajeGraboEndosoBeneficiario").html(
								"Se elimin&oacute; el asegurado exitosamente");
								$("#asegurado_id").val('');
							}
						}
					});
				} else {
					$("#mensajeErrorEndosoBeneficiario").html(
					"No se pudo guardar el asegurado");
					if (tipoConsulta == 'eliminar')
						$("#mensajeGraboEndosoBeneficiario").html(
						"No se pudo eliminar el asegurado");
					$("#msgPopupEndosoBeneficiarioGrabo").fadeOut("slow");
					$("#msgPopupEndosoBeneficiarioError").fadeIn("slow");
					alert(data.error);
				}
			}
		});
}

function guardarPago(valor) {
	var tipoConsulta = "crear";

	$("#save-pagoContado").attr("disabled", "disabled");
	$("#save-pagoDebitoBanco").attr("disabled", "disabled");
	$("#save-pagoDebitoTarjeta").attr("disabled", "disabled");
	$("#save-pagoCuotas").attr("disabled", "disabled");
	$("#msgPopupPagoGrabo").hide();
	$("#msgPopupPagoError").hide();
	if (parseInt($("#codigoPagoRegistrado").val()) > 0)
		tipoConsulta = "actualizar";

	if (valor == 'contado') {
		$.ajax({
			url : '../PagoController',
			data : {
				"codigoCotizacion" : $("#cotizacion_id").text(),
				"codigoFormaPago" : $("#cboFpFormaPago").val(),
				"codigoInstFinanciera" : "",
				"plazo" : "1",
				"fechaPago" : "",
				"formaPagoSeleccionada" : "1",
				"tipoConsulta" : "guardarPorCotizacion"
			},
			asyn : false,
			type : 'post',
			datatype : 'json',
			success : function(datos) {
				if (datos.success) {
					$("#codigoPagoRegistrado").val(datos.pagoId);
					
					$.ajax({
						url : '../PymeCotizacionController',
						data : {
							"cotizacionId" : $("#cotizacion_id").text(),
							"etapaNumero" : "4",
							"tipoConsulta" : "cambiarEtapa"
						},
						asyn : false,
						type : 'post',
						datatype : 'json',
						success : function(datos) {
							if (datos.success) {
								$("#msgPopupPagoGrabo").show();
								$("#msgPopupPago").attr("class", "alert alert-success").html("La forma de pago se ha registrado correctamente.").fadeIn("slow");
							}
						}
					});
				} else {
					$("#codigoPagoRegistrado").val("");
					$("#msgPopupPago").attr("class",
					"alert alert-danger").html(datos.error)
					.fadeIn("slow");
				}
				$("#save-pagoContado").removeAttr("disabled");
				$("#save-pagoDebitoBanco").removeAttr("disabled");
				$("#save-pagoDebitoTarjeta").removeAttr("disabled");
				$("#save-pagoCuotas").removeAttr("disabled");

			}
		});
	}

	if (valor == 'banco') {
		var bandera = true;
		$("#save-pagoDebitoBanco")
		.parent()
		.parent()
		.parent()
		.find(':input')
		.each(
				function() {
					var elemento = this;
					if (elemento.value == ""
						&& elemento.type != "button") {
						alert("Todos los campos del formulario son requerido. Por favor verifique que esten llenos.");
						$("#" + elemento.id).css('border-color', 'red');
						bandera = false;
						$("#save-pagoContado").removeAttr("disabled");
						$("#save-pagoDebitoBanco").removeAttr(
						"disabled");
						$("#save-pagoDebitoTarjeta").removeAttr(
						"disabled");
						$("#save-pagoCuotas").removeAttr("disabled");
						return false;
					}
				});

		if ((Date.parse($("#txtfechaInicialpago").val())) < (Date
				.parse(new Date().setHours(0, 0, 0, 0)))) {
			alert("La fecha de inicio de pagos no puede ser menor a la fecha actual.");
			$("#txtfechaInicialpago").css('border-color', 'red');
			bandera = false;
			$("#save-pagoContado").removeAttr("disabled");
			$("#save-pagoDebitoBanco").removeAttr("disabled");
			$("#save-pagoDebitoTarjeta").removeAttr("disabled");
			$("#save-pagoCuotas").removeAttr("disabled");
			return false;
		}

		if (bandera) {
			var plazo = Number($("#bancoPlazo").val());
			// if($("#txtcuotaInicialbancoPlazo").val()!=""&&$("#txtcuotaInicialbancoPlazo").val()!="0")
			// plazo++;

			$.ajax({
				url : '../PagoController',
				data : {
					"codigoCotizacion" : $("#cotizacion_id").text(),
					"formaPagoSeleccionada" : "2",
					"codigoInstFinanciera" : $("#bancoId").val(),
					"tipoCuenta" : $("#bancoTipoCuenta").val(),
					"numCuenta" : $("#bancoNumeroCuenta").val(),
					"titular" : $("#bancoTitular").val(),
					"tipoIdentificacionId" : $(
					"#tipo_identificacion_banco").val(),
					"identificacion" : $("#bancoIdentificacion").val(),
					"plazo" : plazo,
					"fechaPago" : $("#txtfechaInicialpago").val(),
					"cuotaInicial" : $("#txtcuotaInicialbancoPlazo")
					.val(),
					"fechaPago" : $("#txtfechaInicialpago").val(),
					"tipoConsulta" : "guardarPorCotizacion"
				},
				type : 'post',
				datatype : 'json',
				success : function(datos) {
					if (datos.success) {
						$("#codigoPagoRegistrado").val(datos.pagoId);
						$.ajax({
							url : '../PymeCotizacionController',
							data : {
								"cotizacionId" : $("#cotizacion_id").text(),
								"etapaNumero" : "4",
								"tipoConsulta" : "cambiarEtapa"
							},
							asyn : false,
							type : 'post',
							datatype : 'json',
							success : function(datos) {
								$("#msgPopupPagoGrabo").show();
								$("#msgPopupPago").attr("class", "alert alert-success").html("La forma de pago se ha registrado correctamente.").fadeIn("slow");
							}
						});
					} else {
						$("#codigoPagoRegistrado").val("");
						$("#msgPopupPago").attr("class",
						"alert alert-danger").html(datos.error)
						.fadeIn("slow");
					}
					$("#save-pagoContado").removeAttr("disabled");
					$("#save-pagoDebitoBanco").removeAttr("disabled");
					$("#save-pagoDebitoTarjeta").removeAttr("disabled");
					$("#save-pagoCuotas").removeAttr("disabled");
				}
			});

		}
	}

	if (valor == 'tarjeta') {

		var plazo = Number($("#tarjetaPlazo").val());
		// if($("#txtcuotaInicialbancoPlazo").val()!=""&&$("#txtcuotaInicialbancoPlazo").val()!="0")
		// plazo++;

		var bandera = true;
		$("#save-pagoDebitoTarjeta")
		.parent()
		.parent()
		.parent()
		.find(':input')
		.each(
				function() {
					var elemento = this;
					if (elemento.value == ""
						&& elemento.type != "button") {
						alert("Todos los campos del formulario son requerido. Por favor verifique que esten llenos.");
						$("#" + elemento.id).css('border-color', 'red');
						bandera = false;
						$("#save-pagoContado").removeAttr("disabled");
						$("#save-pagoDebitoBanco").removeAttr(
						"disabled");
						$("#save-pagoDebitoTarjeta").removeAttr(
						"disabled");
						$("#save-pagoCuotas").removeAttr("disabled");

						return false;
					}
				});

		if (bandera) {
			$
			.ajax({
				url : '../PagoController',
				data : {
					"codigoCotizacion" : $("#cotizacion_id").text(),
					"formaPagoSeleccionada" : "3",
					"codigoInstFinanciera" : $("#tarjetaId").val(),
					"numCuenta" : $("#tarjetaNumero").val(),
					"titular" : $("#tarjetaTitular").val(),
					"tipoCuenta" : 'T',
					"tipoIdentificacionId" : $(
					"#tipo_identificacion_tarjeta").val(),
					"identificacion" : $("#tarjetaIdentificacion")
					.val(),
					"tarjetaAnioExp" : $("#tarjetaAnioExp").val(),
					"tarjetaMesExp" : $("#tarjetaMesExp").val(),
					"plazo" : plazo,
					"fechaPago" : $("#txtfechaInicialpagoTarjeta")
					.val(),
					"cuotaInicial" : $("#txtcuotaInicialtarjetaPlazo")
					.val(),
					"tipoConsulta" : "guardarPorCotizacion"
				},
				type : 'post',
				datatype : 'json',
				success : function(datos) {
					if (datos.success) {
						$("#codigoPagoRegistrado").val(datos.pagoId);
						$.ajax({
							url : '../PymeCotizacionController',
							data : {
								"cotizacionId" : $("#cotizacion_id").text(),
								"etapaNumero" : "4",
								"tipoConsulta" : "cambiarEtapa"
							},
							asyn : false,
							type : 'post',
							datatype : 'json',
							success : function(datos) {
								$("#msgPopupPagoGrabo").show();
								$("#msgPopupPago").attr("class", "alert alert-success").html("La forma de pago se ha registrado correctamente.").fadeIn("slow");
							}
						});
					} else {
						$("#codigoPagoRegistrado").val("");
						$("#msgPopupPago").attr("class",
						"alert alert-danger").html(datos.error)
						.fadeIn("slow");
					}
					$("#save-pagoContado").removeAttr("disabled");
					$("#save-pagoDebitoBanco").removeAttr("disabled");
					$("#save-pagoDebitoTarjeta").removeAttr("disabled");
					$("#save-pagoCuotas").removeAttr("disabled");

				}
			});
		}
	}

	if (valor == 'cuota') {
		var plazo = Number($("#cboFpPlazo").val());
		// if($("#txtcuotaInicialbancoPlazo").val()!=""&&$("#txtcuotaInicialbancoPlazo").val()!="0")
		// plazo++;

		var bandera = true;
		$("#save-pagoCuotas")
		.parent()
		.parent()
		.parent()
		.find(':input')
		.each(
				function() {
					var elemento = this;
					if (elemento.value == "0"
						&& elemento.type != "button") {
						alert("Todos los campos del formulario son requerido. Por favor verifique que esten llenos.");
						$("#" + elemento.id).css('border-color', 'red');
						bandera = false;
						$("#save-pagoContado").removeAttr("disabled");
						$("#save-pagoDebitoBanco").removeAttr(
						"disabled");
						$("#save-pagoDebitoTarjeta").removeAttr(
						"disabled");
						$("#save-pagoCuotas").removeAttr("disabled");

						return false;
					}
				});

		if (bandera && $("#cboFpPlazo").val() != "0") {
			var listadoCheques = "";

			$(".detalleChequesPagos").each(function() {
				if ($(this).val() != "")
					listadoCheques = listadoCheques + $(this).val() + ",";
				else
					listadoCheques = listadoCheques + " ,";
			});

			$.ajax({
				url : '../PagoController',
				data : {
					"codigoCotizacion" : $("#cotizacion_id").text(),
					"tipoIdentificacionId" : $(
					"#tarjetaTipoIdentificacion").val(),
					"identificacion" : $("#tarjetaIdentificacion")
					.val(),
					"plazo" : plazo,
					"formaPagoSeleccionada" : "4",
					"tipoConsulta" : "guardarPorCotizacion",
					"cuotaInicial" : $("#txtcuotaInicial").val()
				},
				type : 'post',
				datatype : 'json',
				success : function(datos) {
					if (datos.success) {
						$("#codigoPagoRegistrado").val(datos.pagoId);
						$.ajax({
							url : '../PymeCotizacionController',
							data : {
								"cotizacionId" : $("#cotizacion_id").text(),
								"etapaNumero" : "4",
								"tipoConsulta" : "cambiarEtapa"
							},
							asyn : false,
							type : 'post',
							datatype : 'json',
							success : function(datos) {
								$("#msgPopupPagoGrabo").show();
								$("#msgPopupPago").attr("class", "alert alert-success").html("La forma de pago se ha registrado correctamente.").fadeIn("slow");
							}
						});
					} else {
						$("#codigoPagoRegistrado").val("");
						$("#msgPopupPago").attr("class",
						"alert alert-danger").html(datos.error)
						.fadeIn("slow");
					}
					$("#save-pagoContado").removeAttr("disabled");
					$("#save-pagoDebitoBanco").removeAttr("disabled");
					$("#save-pagoDebitoTarjeta").removeAttr("disabled");
					$("#save-pagoCuotas").removeAttr("disabled");
				}
			});
		}
	}
}

function obtenerTipoPredioPorActividad(seleccionada) {
	giroNegociosList.value([]);
	// Consultar la ACTIVIDAD
	$.ajax({
		url : '../TipoPredioController',
		data : {
			"tipoConsulta" : "encontrarPorActividad",
			"actividadEconomicaId" : $("#actividad_economica option:selected").val()
		},
		async : false,
		type : 'post',
		datatype : 'json',
		success : function(data) {
			giroNegociosList.dataSource.filter({});
			giroNegociosList.setDataSource(data.tiposPredio);
			$("#giro_negocio").data("kendoMultiSelect").value(seleccionada);
		}
	});
}

//Metodo para el calculo del IVA segun los Puntos de Ventas
function calculoIVAPuntoVentaCotizacion() {
	var cotizacion = $("#cotizacion_id").text();
	$.ajax({
		url : '../PymeCotizacionController',
		data : {
			"tipoConsulta" : "ivaSegunPuntoVenta",
			"cotizacionId" : cotizacion 
		},
		type : 'post',
		datatype : 'json',
		success : function (data) {
			$(".valor_iva").text(data.iva);	
			$(".valor_compensacion").text(data.compensacion);
			
			//$("#resumen_inspeccion_iva").val(data.valor_iva);
			$("#resumen_valor_compensacion").val(data.valor_compensacion);
			
			//$("#inspeccion_iva").val(data.valor_iva);
			$("#inspeccion_valor_compensacion").val(data.valor_compensacion);
			
			//$("#iva").val(data.valor_iva);
			$("#valor_compensacion").val(data.valor_compensacion);
		}
	});
}

/*REPORTE*/
function imprimirPoliza() {
	window.open('../PymeCotizacionController?tipoConsulta=imprimirPoliza&cotizacionId=' + $("#cotizacion_id").text());
}

function imprimirPolizaBorrador() {
	window.open('../PymeCotizacionController?tipoConsulta=imprimirPolizaBorrador&cotizacionId=' + $("#cotizacion_id").text());
}


function abrirCertificadoDebito() {
	window.open('../PymeCotizacionController?tipoConsulta=imprimirAutorizacionDebito&cotizacionId=' + $("#cotizacion_id").text());
}

function abrirVinculacionCliente() {
	window.open('../PymeCotizacionController?tipoConsulta=imprimirVinculacionCliente&cotizacionId=' + $("#cotizacion_id").text());
}

function abrirEndosoBeneficiario() {
	window.open('../PymeCotizacionController?tipoConsulta=imprimirEndosoBeneficiario&cotizacionId=' + $("#cotizacion_id").text());
}