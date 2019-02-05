package com.qbe.cotizador.servlets.producto.agricola;

import java.util.List;

/**
 * Created by andresn on 24/07/2015.
 */
public class TransporteCotizaciones {

	
	
    public List<CotizacionAgricola> getCotizacionAgricola() {
        return CotizacionAgricola;
    }

    public void setCotizacionAgricola(List<CotizacionAgricola> cotizacionAgricola) {
        CotizacionAgricola = cotizacionAgricola;
    }
    private List<CotizacionAgricola> CotizacionAgricola;

    

}
