/**
 *
 */

/*
 * METODO QUE CARGA LA INFORMACION DE LA FORMA DE PAGO QUE CORRESPONDE AL ID
 * RECIBIDO
 */
function cargarFormasPago(seleccionada, tipoConsulta) {
	$("#rowDetallePagos").hide();

	if (tipoConsulta == "formasPago") {
		$.ajax({
			url : '../FormaPagoController',
			data : {
				"tipoConsulta" : "encontrarTodos",
			},
			type : 'post',
			datatype : 'json',
			success : function (data) {
				var listadoFormaPago = data.listadoFormaPago;
				var options = '';
				$.each(listadoFormaPago, function (index) {
					options += '<option value="' + listadoFormaPago[index].codigo + '">' + listadoFormaPago[index].nombre + '</option>';
				});
				$("#cboFpFormaPago").empty().append(options);

				if (seleccionada > 0) {
					cambioFormaPago(seleccionada, false);
					$("#cboFpFormaPago").val(seleccionada);
				}
			}
		});
	}

	if (tipoConsulta == "intitucionesFinancieras") {
		$.ajax({
			url : '../InstitucionFinancieraController',
			data : {
				"tipoConsulta" : "encontrarActivos",
			},
			type : 'post',
			datatype : 'json',
			success : function (datos) {
				var listadoInstitucionFinanciera = datos.listadoInstitucionFinanciera;

				$("#banco_forma_pago").empty();
				$("#cboFpBanco").empty();
				$.each(listadoInstitucionFinanciera, function (index) {
					if (listadoInstitucionFinanciera[index].tarjeta == '0' && listadoInstitucionFinanciera[index].debito == '1') {
						$("#bancoId").append("<option value='" + listadoInstitucionFinanciera[index].codigo + "' nemotecnico='" + listadoInstitucionFinanciera[index].nemotecnico + "'>" + listadoInstitucionFinanciera[index].nombre + "</option>");
					}

					if (listadoInstitucionFinanciera[index].tarjeta == '1' && listadoInstitucionFinanciera[index].debito == '1') {
						$("#tarjetaId").append("<option value='" + listadoInstitucionFinanciera[index].codigo + "' nemotecnico='" + listadoInstitucionFinanciera[index].nemotecnico + "'>" + listadoInstitucionFinanciera[index].nombre + "</option>");
					}
				});

				if (seleccionada > 0) {
					$("#tarjetaId").val(seleccionada);
					$("#bancoId").val(seleccionada);
				}
			}
		});
	}

	if (tipoConsulta == "aniosVigencia") {

		var currentTime = new Date();
			var year = currentTime.getFullYear();

			for (var i = 2014; i <= parseInt(year + 10); i++)
				$("#tarjetaAnioExp").append('<option value="' + i + '">' + i + '</option>');

			if (seleccionada > 0) {
				$("#tarjetaAnioExp").val(seleccionada);
			}						
	}
}

/*
 * METODO QUE COMPRUEBA QUE EL USUARIO DIGITE SOLO NUMERO
 */
function justNumbers(e) {
	var keynum = window.event ? window.event.keyCode : e.which;

	if ((keynum == 8) || (keynum == 46))
		return true;
	return /\d/.test(String.fromCharCode(keynum));
}

function limpiaForm(miForm) {
	// recorremos todos los campos que tiene el formulario
	$(':input', miForm).each(function () {
		var type = this.type;
		var tag = this.tagName.toLowerCase();
		//limpiamos los valores de los camposÃ¢Â€Â¦
		if (type == 'text' || type == 'password' || tag == 'textarea')
			this.value = "";
		// excepto de los checkboxes y radios, le quitamos el checked
		// pero su valor no debe ser cambiado
		else if (type == 'checkbox' || type == 'radio')
			this.checked = false;
		// los selects le ponemos el indice a -
		else if (tag == 'select')
			this.selectedIndex = -1;
	});
}

function digitoVerificadorTarjeta(digit) {
	if (digit > 9) {
		var tmp = digit.toString();
		var d1 = parseInt(tmp.charAt(0));
		var d2 = parseInt(tmp.charAt(1));
		return (d1 + d2);
	} else {
		return digit;
	}
}

function comprobarTarjetaValida(value) {
	$("#tarjetaNumero").next().next().empty();

	if (/[^0-9-\s]+/.test(value))
		return false;

	// The Luhn Algorithm. It's so pretty.
	var nCheck = 0,
	nDigit = 0,
	bEven = false;
	value = value.replace(/\D/g, "");

	for (var n = value.length - 1; n >= 0; n--) {
		var cDigit = value.charAt(n),
		nDigit = parseInt(cDigit, 10);

		if (bEven) {
			if ((nDigit *= 2) > 9)
				nDigit -= 9;
		}

		nCheck += nDigit;
		bEven = !bEven;
	}

	if ((nCheck % 10) == 0) {
		$("#tarjetaNumero").next().next().attr("style", "color:green;").empty().append("<b>Tarjeta Verificada</b>");
	} else {
		$("#tarjetaNumero").next().next().attr("style", "color:#8a1f11;").empty().append("<b>Tarjeta Incorrecta</b>");
	}
}

function validarEmisorTarjeta(e, num) {
	$("#tarjetaNumero").parent().children().last().empty();
	if (e.keyCode >= 48 && e.keyCode <= 57) {
		var charCount = num.length;
		/* VALIDACION DE TIPO */
		if (charCount == 1) {
			if (num == "4") {
				$(".tarjetasIconos").each(function () {
					$(this).attr("style", "opacity: 0.2; filter: alpha(opacity=20);")
				});
				$("#tarjetaId [nemotecnico='" + $("#visaIcon").attr('nemotecnico') + "']").attr("selected", "selected");
				$("#visaIcon").removeAttr("style");
			}
		}
		if (charCount == 2) {
			if (num == "34" || num == "37") {
				$(".tarjetasIconos").each(function () {
					$(this).attr("style", "opacity: 0.2; filter: alpha(opacity=20);")
				});
				$("#tarjetaId [nemotecnico='" + $("#amexIcon").attr('nemotecnico') + "']").attr("selected", "selected");
				$("#amexIcon").removeAttr("style");
			} else if (num == "50" || num == "51" || num == "52" || num == "53" || num == "54" || num == "55") {
				$(".tarjetasIconos").each(function () {
					$(this).attr("style", "opacity: 0.2; filter: alpha(opacity=20);")
				});
				$("#tarjetaId [nemotecnico='" + $("#mastercardIcon").attr('nemotecnico') + "']").attr("selected", "selected");
				$("#mastercardIcon").removeAttr("style");
			} else if (num == "65") {
				$(".tarjetasIconos").each(function () {
					$(this).attr("style", "opacity: 0.2; filter: alpha(opacity=20);")
				});
				$("#tarjetaId [nemotecnico='" + $("#discoverIcon").attr('nemotecnico') + "']").attr("selected", "selected");
				$("#discoverIcon").removeAttr("style");
			} else if (num == "36" || num == "38" || num == "39") {
				$(".tarjetasIconos").each(function () {
					$(this).attr("style", "opacity: 0.2; filter: alpha(opacity=20);")
				});
				$("#tarjetaId [nemotecnico='" + $("#dinersIcon").attr('nemotecnico') + "']").attr("selected", "selected");
				$("#dinersIcon").removeAttr("style");
			}
		}
		if (charCount == 3) {
			if (num == "644" || num == "649") {
				$(".tarjetasIconos").each(function () {
					$(this).attr("style", "opacity: 0.2; filter: alpha(opacity=20);")
				});
				$("#tarjetaId [nemotecnico='" + $("#discoverIcon").attr('nemotecnico') + "']").attr("selected", "selected");
				$("#discoverIcon").removeAttr("style");
			} else if (num == "300" || num == "305" || num == "309") {
				$(".tarjetasIconos").each(function () {
					$(this).attr("style", "opacity: 0.2; filter: alpha(opacity=20);")
				});
				$("#tarjetaId [nemotecnico='" + $("#dinersIcon").attr('nemotecnico') + "']").attr("selected", "selected");
				$("#dinersIcon").removeAttr("style");
			}
		}
		if (charCount == 4) {
			if (num == "6011") {
				$(".tarjetasIconos").each(function () {
					$(this).attr("style", "opacity: 0.2; filter: alpha(opacity=20);")
				});
				$("#tarjetaId [nemotecnico='" + $("#discoverIcon").attr('nemotecnico') + "']").attr("selected", "selected");
				$("#discoverIcon").removeAttr("style");
			}
		}
		if (charCount == 6) {
			if (num == "622126" || num == "622925") {
				$(".tarjetasIconos").each(function () {
					$(this).attr("style", "opacity: 0.2; filter: alpha(opacity=20);")
				});
				$("#tarjetaId [nemotecnico='" + $("#discoverIcon").attr('nemotecnico') + "']").attr("selected", "selected");
				$("#discoverIcon").removeAttr("style");
			}
		}
		/* !VALIDACION DE TIPO */
		return true;
	} else {
		event.returnValue = false;
	}
}
/*
function  validarEmisorTarjeta(e, number) {
$("#tarjetaNumero").parent().children().last().empty();
var re = {
electron : /^(4026|417500|4405|4508|4844|4913|4917)\d+$/,
maestro : /^(5018|5020|5038|5612|5893|6304|6759|6761|6762|6763|0604|6390)\d+$/,
dankort : /^(5019)\d+$/,
interpayment : /^(636)\d+$/,
unionpay : /^(62|88)\d+$/,
visa : /^4[0-9]{12}(?:[0-9]{3})?$/,
mastercard : /^5[1-5][0-9]{14}$/,
amex : /^3[47][0-9]{13}$/,
diners : /^3(?:0[0-5]|[68][0-9])[0-9]{11}$/,
discover : /^6(?:011|5[0-9]{2})[0-9]{12}$/,
jcb : /^(?:2131|1800|35\d{3})\d{11}$/
};
if (re.electron.test(number)) {
alert("Tarjeta no permitida");
$("#tarjetaNumero").focus();
return false;
} else if (re.maestro.test(number)) {
alert("Tarjeta no permitida");
$("#tarjetaNumero").focus();
return false;
} else if (re.dankort.test(number)) {
alert("Tarjeta no permitida");
$("#tarjetaNumero").focus();
return false;
} else if (re.interpayment.test(number)) {
alert("Tarjeta no permitida");
$("#tarjetaNumero").focus();
return false;
} else if (re.unionpay.test(number)) {
alert("Tarjeta no permitida");
$("#tarjetaNumero").focus();
return false;
} else if (re.visa.test(number)) {
$(".tarjetasIconos").each(function () {
$(this).attr("style", "opacity: 0.2; filter: alpha(opacity=20);");
});
$("#tarjetaId [nemotecnico='" + $("#visaIcon").attr('nemotecnico') + "']").attr("selected", "selected");
$("#visaIcon").removeAttr("style");
} else if (re.mastercard.test(number)) {
$(".tarjetasIconos").each(function () {
$(this).attr("style", "opacity: 0.2; filter: alpha(opacity=20);");
});
$("#tarjetaId [nemotecnico='" + $("#mastercardIcon").attr('nemotecnico') + "']").attr("selected", "selected");
$("#mastercardIcon").removeAttr("style");
} else if (re.amex.test(number)) {
$(".tarjetasIconos").each(function () {
$(this).attr("style", "opacity: 0.2; filter: alpha(opacity=20);");
});
$("#tarjetaId [nemotecnico='" + $("#amexIcon").attr('nemotecnico') + "']").attr("selected", "selected");
$("#amexIcon").removeAttr("style");
} else if (re.diners.test(number)) {
$(".tarjetasIconos").each(function () {
$(this).attr("style", "opacity: 0.2; filter: alpha(opacity=20);");
});
$("#tarjetaId [nemotecnico='" + $("#dinersIcon").attr('nemotecnico') + "']").attr("selected", "selected");
$("#dinersIcon").removeAttr("style");
} else if (re.discover.test(number)) {
$(".tarjetasIconos").each(function () {
$(this).attr("style", "opacity: 0.2; filter: alpha(opacity=20);");
});
$("#tarjetaId [nemotecnico='" + $("#discoverIcon").attr('nemotecnico') + "']").attr("selected", "selected");
$("#discoverIcon").removeAttr("style");
} else if (re.jcb.test(number)) {
alert("Tarjeta no permitida");
$("#tarjetaNumero").focus();
return false;
} else {
alert("Tarjeta no permitida");
$("#tarjetaNumero").val("");
return false;
}
}
 */
function cambioPlazoPago(numCuotas) {
	var valorTotal = parseFloat($("#total_vh_origin").val());
	var cuotaInicial = $("#txtcuotaInicialtarjetaPlazo").val();

	$("#msgPopupPagoTarjeta").empty().delay(800);
	if (numCuotas == 12) {
		valorTotal = valorTotal * parseFloat(1.0842);
		cuotas = (valorTotal - cuotaInicial) / numCuotas;
		$("#msgPopupPagoTarjeta").empty().append("El valor total a pagar es $" + parseFloat(valorTotal).toFixed(2) + " con cuotas de $" + parseFloat(cuotas).toFixed(2)).show();
		$(".total_vh").val(parseFloat(valorTotal).toFixed(2));
	} else if (numCuotas < 12) {
		cuotas = (valorTotal - cuotaInicial) / numCuotas;
		if (cuotaInicial > 0)
			$("#msgPopupPagoTarjeta").empty().append("El valor total a pagar es $" + parseFloat(valorTotal).toFixed(2) + " con cuotas de $" + parseFloat(cuotas).toFixed(2) + " y cuota inicial de $" + parseFloat(cuotaInicial).toFixed(2)).show();
		else
			$("#msgPopupPagoTarjeta").empty().append("El valor total a pagar es $" + parseFloat(valorTotal).toFixed(2) + " con cuotas de $" + parseFloat(cuotas).toFixed(2)).show();
		$(".total_vh").val(parseFloat(valorTotal).toFixed(2));
	}
	$("#resumenTotalPago").children().first().empty().append("$" + parseFloat(valorTotal).toFixed(2));
}

function calculaDiasPago(fecha) {
	arrFecha = fecha.split("/");
	var newDay = parseInt(arrFecha[0]);
	var newMonth = parseInt(arrFecha[1]);
	var newYear = parseInt(arrFecha[2]);
	if (newMonth == 12) {
		newMonth = 1;
		newYear += 1;
	} else {
		newMonth += 1;
	}

	switch (newMonth) {
	case 1:
	case 3:
	case 5:
	case 7:
	case 8:
	case 10:
	case 12:
		if (newDay == 30)
			newDay = 31;
		break;
	case 4:
	case 6:
	case 9:
	case 11:
		if (newDay == 31)
			newDay = 30;
		break;
	case 2:
		if (newDay > 28) {
			if (newYear % 4 == 0) {
				if ((newYear % 100 != 0) || (newYear % 400 == 0)) {
					newDay = 29;
				} else {
					newDay = 28;
				}
			} else {
				newDay = 28;
			}
		}
		break;
	}

	if (newMonth < 10) {
		newMonth = '0' + newMonth;
	}

	if (newDay < 10) {
		newDay = '0' + newDay;
	}

	var fechaNueva = newDay + '/' + newMonth + '/' + newYear;
	return fechaNueva;
}

function verificarValorCuotaInicial(valor) {
	$("#cboFpPlazo").val("0");
	$("#rowDetallePagos").hide();
	if (valor < 50) {
		alert("Las cuotas a pagar no pueden ser menor a 50 dolares.");
		$("#txtcuotaInicial").val($("#total_vh").val());
		$("#cboFpPlazo").val("0");
	}
}

function calculaCuotas(numCuotas) {
	if (parseFloat($("#txtcuotaInicial").val()) >= parseFloat(50)) {
		var valorDiferencia = parseFloat($("#total_vh").val()) - parseFloat($("#txtcuotaInicial").val());
		var valorCuotas = parseFloat(valorDiferencia).toFixed(2) / (parseInt(numCuotas) - 1);
		var fechaCuotaInicial = $("#fechaInicialPagos").val();
		if (valorCuotas < 50) {
			numCuotas = Math.floor(valorDiferencia / 50);
			valorCuotas = parseFloat(valorDiferencia).toFixed(2) / parseInt(numCuotas);
			$("#cboFpPlazo").val(numCuotas).trigger("change");
			alert("Las cuotas mensuales no pueden ser menores a $50. Usted puede pagar la diferencia en maximo " + (numCuotas) + " cuotas");
			numCuotas++;
		}

		$("#detallePagoCuotas").empty().append("<tr>" +
			"<td align='center'><b>1</b></td>" +
			"<td align='center'><input type='text' class='detalleChequesPagos' id='cuotaInicial'  size='12' style='margin: 5px; padding: 5px;' value='Cuota Inicial' disabled='disabled'></td>" +
			"<td align='center'>" + parseFloat($("#txtcuotaInicial").val()).toFixed(2) + "</td>" +
			"<td align='center'>" + fechaCuotaInicial + "</td>" +
			"</tr>");

		var fechaCuota = calculaDiasPago(fechaCuotaInicial);
		var limiteCuotas = parseInt(numCuotas);

		for (var i = 1; i < limiteCuotas; i++) {
			$("#detallePagoCuotas").append("<tr>" +
				"<td align='center'><b>" + (i + 1) + "</b></td>" +
				"<td align='center'><input type='text' onkeypress='return justNumbers(event);' class='detalleChequesPagos' size='12' style='margin: 5px; padding: 5px;' disabled='disabled'></td>" +
				"<td align='center'>" + parseFloat(valorCuotas).toFixed(2) + "</td>" +
				"<td align='center'>" + fechaCuota + "</td>" +
				"</tr>");
			fechaCuotaSiguiente = fechaCuota;
			fechaCuota = calculaDiasPago(fechaCuotaSiguiente);
		}
		$("#rowDetallePagos").show();
	} else {
		alert("Su cuota inicial debes ser minimo de $50.");
	}
}

function validacionBanco() {
	//los numeros de cuenta pertenecientes de qbe
	var cuentas = [];
	cuentas[0] = '3039409904';
	cuentas[1] = '3083207804';
	cuentas[2] = '3162187904';
	cuentas[3] = '3356072304';
	cuentas[4] = '3387015504';
	cuentas[5] = '02005008022';
	cuentas[6] = '02005065840';
	cuentas[7] = '02005072219';
	cuentas[8] = '02005152484';
	cuentas[9] = '02080003881';
	cuentas[10] = '5005008235';
	cuentas[11] = '0014001603';
	cuentas[12] = '3506371';
	cuentas[13] = '5926500007778';
	cuentas[14] = '10086041184';
	cuentas[15] = '1001504013';
	cuentas[16] = '10154681';
	cuentas[17] = '10260231';
	cuentas[18] = '17060000443';
	cuentas[19] = '0000632192';
	cuentas[20] = '0082542035';
	cuentas[21] = '36295998';
	cuentas[22] = '052255667';
	cuentas[23] = '1901010755933';
	cuentas[24] = '2901040558';
	cuentas[25] = '1009901985';

	for (var i = 0; i < cuentas.length; i++) {
		if (i < 5) { //pichincha ecuador
			if ($("#bancoNumeroCuenta").val() == cuentas[i] && $("#bancoId").val() == '254') {
				alert('El numero de cuenta escojido pertenece a QBE!');
				$("#bancoNumeroCuenta").val("");
			}
		}

		if (i < 10 && i > 4) { //produbanco
			if ($("#bancoNumeroCuenta").val() == cuentas[i] && $("#bancoId").val() == '259') {
				alert('El numero de cuenta escojido pertenece a QBE!');
				$("#bancoNumeroCuenta").val('');
			}
		}

		if (i == 10) { //bolivariano
			if ($("#bancoNumeroCuenta").val() == cuentas[i] && $("#bancoId").val() == '32') {
				alert('El numero de cuenta escojido pertenece a QBE!');
				$("#bancoNumeroCuenta").val('');
			}
		}

		if (i == 11) { //guayaquil
			if ($("#bancoNumeroCuenta").val() == cuentas[i] && $("#bancoId").val() == '226') {
				alert('El numero de cuenta escojido pertenece a QBE!');
				$("#bancoNumeroCuenta").val('');
			}
		}
		if (i == 12) { //pacifico
			if ($("#bancoNumeroCuenta").val() == cuentas[i] && $("#bancoId").val() == '252') {
				alert('El numero de cuenta escojido pertenece a QBE!');
				$("#bancoNumeroCuenta").val('');
			}
		}

		if (i == 13 || i == 14) { //solidario
			if ($("#bancoNumeroCuenta").val() == cuentas[i] && $("#bancoId").val() == '266') {
				alert('El numero de cuenta escojido pertenece a QBE!');
				$("#bancoNumeroCuenta").val('');
			}
		}

		if (i == 15) { //promerica
			if ($("#bancoNumeroCuenta").val() == cuentas[i] && $("#bancoId").val() == '9') {
				alert('El numero de cuenta escojido pertenece a QBE!');
				$("#bancoNumeroCuenta").val('');
			}
		}

		if (i == 16 || i == 17) { //fomento
			if ($("#bancoNumeroCuenta").val() == cuentas[i] && $("#bancoId").val() == '222') {
				alert('El numero de cuenta escojido pertenece a QBE!');
				$("#bancoNumeroCuenta").val('');
			}
		}

		if (i == 18) { //capital
			if ($("#bancoNumeroCuenta").val() == cuentas[i] && $("#bancoId").val() == '11') {
				alert('El numero de cuenta escojido pertenece a QBE!');
				$("#bancoNumeroCuenta").val('');
			}
		}

		if (i == 19) { //internacional
			if ($("#bancoNumeroCuenta").val() == cuentas[i] && $("#bancoId").val() == '232') {
				alert('El numero de cuenta escojido pertenece a QBE!');
				$("#bancoNumeroCuenta").val('');
			}
		}

		if (i == 20) { //citibank
			if ($("#bancoNumeroCuenta").val() == cuentas[i] && $("#bancoId").val() == '44') {
				alert('El numero de cuenta escojido pertenece a QBE!');
				$("#bancoNumeroCuenta").val('');
			}
		}

		if (i == 21) { //citibank ny
			if ($("#bancoNumeroCuenta").val() == cuentas[i] && $("#bancoId").val() == '42') {
				alert('El numero de cuenta escojido pertenece a QBE!');
				$("#bancoNumeroCuenta").val('');
			}
		}

		if (i == 22) { //mutualista pichincha
			if ($("#bancoNumeroCuenta").val() == cuentas[i] && $("#bancoId").val() == '247') {
				alert('El numero de cuenta escojido pertenece a QBE!');
				$("#bancoNumeroCuenta").val('');
			}
		}

		if (i == 23) { //procredit
			if ($("#bancoNumeroCuenta").val() == cuentas[i] && $("#bancoId").val() == '258') {
				alert('El numero de cuenta escojido pertenece a QBE!');
				$("#bancoNumeroCuenta").val('');
			}
		}

		if (i == 24) { //banco de loja
			if ($("#bancoNumeroCuenta").val() == cuentas[i] && $("#bancoId").val() == '15') {
				alert('El numero de cuenta escojido pertenece a QBE!');
				$("#bancoNumeroCuenta").val('');
			}
		}

		if (i == 25) { //banco pichincha panama
			if ($("#bancoNumeroCuenta").val() == cuentas[i] && $("#bancoId").val() == '20') {
				alert('El numero de cuenta escojido pertenece a QBE!');
				$("#bancoNumeroCuenta").val('');
			}
		}
	}

	if ($("#bancoNumeroCuenta").val().length > 10 && $("#bancoId").val() == '254') {
		alert('El la cuenta debe tener mÃ¡ximo 10 dÃ­gitos');
		$("#bancoNumeroCuenta").focus();
	}
}

function validaCuenta() {
	var retorno = true;
	var numero = $("#bancoNumeroCuenta").val();
	var tipo = $("#bancoTipoCuenta").val();
	if ($("#bancoId").val() == '254') //pichincha
		if (!validaCuentasPichincha(numero, tipo)) {
			alert('Cuenta no valida para el tipo de cuenta seleccionado');
			retorno = false;
		}
	if ($("#bancoId").val() == '259') //produ
		if (!validaCuentasProdubanco(numero, tipo)) {
			alert('Cuenta no valida para el tipo de cuenta seleccionado');
			retonro = false;
		}

	return retorno;
}

function validaCuentasPichincha(numero, tipo) {
	var retorno = false;
	if (tipo == 'Ahorro')
		retorno = true;
	if (tipo == 'Corriente')
		retorno = true;
	return retorno;
}

function validaCuentasProdubanco(numero, tipo) {
	var retorno = false;
	if (tipo == 'Ahorro')
		retorno = true;
	if (tipo == 'Corriente')
		retorno = true;
	return retorno;
}

// Validacion para ingreso de identificaciones del cliente
function validadorIdentificaciones() {
	var tipoIdentificacion = $('#tipo_identificacion_principal').val();
	var identificacion = $('#identificacion').val();
	// Validacion de la cedula
	if (tipoIdentificacion == '1') {
		check_cedula(identificacion);
	}

}

// Validador de cedula ecuatoriana
function check_cedula(form) {
	var cedula = form;
	array = cedula.split("");
	num = array.length;
	if (num == 10) {
		total = 0;
		digito = (array[9] * 1);
		for (i = 0; i < (num - 1); i++) {
			mult = 0;
			if ((i % 2) != 0) {
				total = total + (array[i] * 1);
			} else {
				mult = array[i] * 2;
				if (mult > 9)
					total = total + (mult - 9);
				else
					total = total + mult;
			}
		}
		decena = total / 10;
		decena = Math.floor(decena);
		decena = (decena + 1) * 10;
		final = (decena - total);
		if ((final == 10 && digito == 0) || (final == digito)) {
			console.log("La c\xe9dula ES v\xe1lida!!!");
		} else {
			alert("La c\xe9dula NO es v\xe1lida!!!");
			return false;
		}
	} else {
		alert("La c\xe9dula no puede tener menos de 10 d\xedgitos");
		return false;
	}
}


//Metodo para expandir la pantalla o reducir la pantalla
function expandirPantalla (chequeado){	
if($(chequeado).prop('checked')){
	  $(".navbar-static-side").css("display","none");
	  $("#page-wrapper").css("margin","0 0 0 0");
}else{
	  $(".navbar-static-side").css("display","inline");
	  $("#page-wrapper").css("margin","0 0 0 250px");
}
	  
}