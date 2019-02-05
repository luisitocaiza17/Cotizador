
package com.qbe.cotizador.servicios.recargoSC.cliente;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.tandicorp.tandiesb.webservice.interfaces package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ConsultarRecargoSeguroAgricolaResponse_QNAME = new QName("http://interfaces.webservice.tandiesb.tandicorp.com/", "consultarRecargoSeguroAgricolaResponse1");
    private final static QName _ConsultarRecargoSeguroAgricola_QNAME = new QName("http://interfaces.webservice.tandiesb.tandicorp.com/", "consultarRecargoSeguroAgricola1");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.tandicorp.tandiesb.webservice.interfaces
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultarRecargoSeguroAgricolaResponse }
     * 
     */
    public ConsultarRecargoSeguroAgricolaResponse createConsultarRecargoSeguroAgricolaResponse() {
        return new ConsultarRecargoSeguroAgricolaResponse();
    }

    /**
     * Create an instance of {@link ConsultarRecargoSeguroAgricola }
     * 
     */
    public ConsultarRecargoSeguroAgricola createConsultarRecargoSeguroAgricola() {
        return new ConsultarRecargoSeguroAgricola();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarRecargoSeguroAgricolaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.webservice.tandiesb.tandicorp.com/", name = "consultarRecargoSeguroAgricolaResponse1")
    public JAXBElement<ConsultarRecargoSeguroAgricolaResponse> createConsultarRecargoSeguroAgricolaResponse(ConsultarRecargoSeguroAgricolaResponse value) {
        return new JAXBElement<ConsultarRecargoSeguroAgricolaResponse>(_ConsultarRecargoSeguroAgricolaResponse_QNAME, ConsultarRecargoSeguroAgricolaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarRecargoSeguroAgricola }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.webservice.tandiesb.tandicorp.com/", name = "consultarRecargoSeguroAgricola1")
    public JAXBElement<ConsultarRecargoSeguroAgricola> createConsultarRecargoSeguroAgricola(ConsultarRecargoSeguroAgricola value) {
        return new JAXBElement<ConsultarRecargoSeguroAgricola>(_ConsultarRecargoSeguroAgricola_QNAME, ConsultarRecargoSeguroAgricola.class, null, value);
    }

}
