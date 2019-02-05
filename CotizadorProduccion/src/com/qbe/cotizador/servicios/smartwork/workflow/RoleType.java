
package com.qbe.cotizador.servicios.smartwork.workflow;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RoleType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RoleType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AprobadoresdeComisiones"/>
 *     &lt;enumeration value="AprobadoresTasasEspeciales"/>
 *     &lt;enumeration value="AprobadoresTasasEspecialesFianzas"/>
 *     &lt;enumeration value="VisadorQuito"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RoleType")
@XmlEnum
public enum RoleType {

    @XmlEnumValue("AprobadoresdeComisiones")
    APROBADORESDE_COMISIONES("AprobadoresdeComisiones"),
    @XmlEnumValue("AprobadoresTasasEspeciales")
    APROBADORES_TASAS_ESPECIALES("AprobadoresTasasEspeciales"),
    @XmlEnumValue("AprobadoresTasasEspecialesFianzas")
    APROBADORES_TASAS_ESPECIALES_FIANZAS("AprobadoresTasasEspecialesFianzas"),
    @XmlEnumValue("VisadorQuito")
    VISADOR_QUITO("VisadorQuito");
    private final String value;

    RoleType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RoleType fromValue(String v) {
        for (RoleType c: RoleType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
