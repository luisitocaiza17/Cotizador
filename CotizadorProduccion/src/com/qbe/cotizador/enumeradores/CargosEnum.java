package com.qbe.cotizador.enumeradores;

public enum CargosEnum {

	PRESIDENTE("PRESIDENTE"), VICEPRESIDENTE("VICEPRESIDENTE"), GERENTE(
			"GERENTE"), DIRECTOR("DIRECTOR");

	private String nombre;

	private CargosEnum(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

}
