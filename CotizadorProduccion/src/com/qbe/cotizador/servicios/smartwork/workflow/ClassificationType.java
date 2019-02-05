
package com.qbe.cotizador.servicios.smartwork.workflow;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ClassificationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ClassificationType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BROKER"/>
 *     &lt;enumeration value="DETALLEGARANTIA"/>
 *     &lt;enumeration value="DOCUMENTOS"/>
 *     &lt;enumeration value="FORMADEPAGO"/>
 *     &lt;enumeration value="INSTITUCIONBANCARIA"/>
 *     &lt;enumeration value="OBJETOASEGURADO"/>
 *     &lt;enumeration value="RAMOS"/>
 *     &lt;enumeration value="RAZONCANCELACION"/>
 *     &lt;enumeration value="RAZONURGENTE"/>
 *     &lt;enumeration value="REMISIONDOCUMENTOS"/>
 *     &lt;enumeration value="SOLICITANTE"/>
 *     &lt;enumeration value="SUCURSALES"/>
 *     &lt;enumeration value="TAMAÑOPROGRAMA"/>
 *     &lt;enumeration value="TIPOCUENTATARJETA"/>
 *     &lt;enumeration value="TIPODEENDOSO"/>
 *     &lt;enumeration value="TIPODEPOLIZA"/>
 *     &lt;enumeration value="TIPOREASEGURO"/>
 *     &lt;enumeration value="UNIDADCOMERCIAL"/>
 *     &lt;enumeration value="DOCUMENTOSVISADO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ClassificationType")
@XmlEnum
public enum ClassificationType {

    BROKER,
    DETALLEGARANTIA,
    DOCUMENTOS,
    FORMADEPAGO,
    INSTITUCIONBANCARIA,
    OBJETOASEGURADO,
    RAMOS,
    RAZONCANCELACION,
    RAZONURGENTE,
    REMISIONDOCUMENTOS,
    SOLICITANTE,
    SUCURSALES,
    TAMAÑOPROGRAMA,
    TIPOCUENTATARJETA,
    TIPODEENDOSO,
    TIPODEPOLIZA,
    TIPOREASEGURO,
    UNIDADCOMERCIAL,
    DOCUMENTOSVISADO;

    public String value() {
        return name();
    }

    public static ClassificationType fromValue(String v) {
        return valueOf(v);
    }

}
