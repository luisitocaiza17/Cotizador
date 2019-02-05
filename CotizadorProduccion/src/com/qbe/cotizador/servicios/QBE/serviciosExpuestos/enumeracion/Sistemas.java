package com.qbe.cotizador.servicios.QBE.serviciosExpuestos.enumeracion;

import java.util.HashMap;
import java.util.Map;


public enum Sistemas {
	QBE("1"), SEGUROS123("2"),;

    private final String id;

    // Mapa de busqueda inversa para conseguir un sistema al ingresar el id
    private static final Map<String, Sistemas> lookup = new HashMap<String, Sistemas>();

    static {
        for (Sistemas d : Sistemas.values()) {
            lookup.put(d.getId(), d);
        }
    }

    private Sistemas(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static Sistemas get(String id) {
        return lookup.get(id);
    }
}