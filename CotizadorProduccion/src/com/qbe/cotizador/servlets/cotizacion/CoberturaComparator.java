package com.qbe.cotizador.servlets.cotizacion;

import java.util.Comparator;
import org.apache.commons.lang3.builder.CompareToBuilder;
import com.qbe.cotizador.model.Cobertura;

public class CoberturaComparator implements Comparator<Cobertura>{

	@Override
	public int compare(Cobertura arg0, Cobertura arg1) {		
		return new CompareToBuilder().append(arg0.getSeccion(), arg1.getSeccion())
				.append(arg0.getOrden(), arg1.getOrden()).toComparison();
	}

}
