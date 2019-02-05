
package com.qbe.cotizador.servicios.smartwork.workflow;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WorkFlowType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="WorkFlowType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="AdjuntaralFile"/>
 *     &lt;enumeration value="Fianzas"/>
 *     &lt;enumeration value="FianzasEsmeraldas"/>
 *     &lt;enumeration value="FianzasGuayaquil"/>
 *     &lt;enumeration value="FianzasMachala"/>
 *     &lt;enumeration value="FianzasManta"/>
 *     &lt;enumeration value="FianzasPortoviejo"/>
 *     &lt;enumeration value="ImpresiónAmbato"/>
 *     &lt;enumeration value="ImpresiónCuenca"/>
 *     &lt;enumeration value="ImpresiónEsmeraldas"/>
 *     &lt;enumeration value="ImpresiónGuayaquil"/>
 *     &lt;enumeration value="ImpresiónIbarra"/>
 *     &lt;enumeration value="ImpresiónLoja"/>
 *     &lt;enumeration value="ImpresiónMachala"/>
 *     &lt;enumeration value="ImpresiónManta"/>
 *     &lt;enumeration value="ImpresiónPortoviejo"/>
 *     &lt;enumeration value="ImpresiónPuyo"/>
 *     &lt;enumeration value="ImpresiónRiobamba"/>
 *     &lt;enumeration value="ImpresiónSantoDomingo"/>
 *     &lt;enumeration value="ImpresiónSolicituddeEmisión"/>
 *     &lt;enumeration value="SolicituddeEmisión"/>
 *     &lt;enumeration value="SolicituddeEmisiónAgenciaSur"/>
 *     &lt;enumeration value="SolicituddeEmisiónContactCenter"/>
 *     &lt;enumeration value="SolicituddeEmisiónAmbato"/>
 *     &lt;enumeration value="SolicituddeEmisiónCuenca"/>
 *     &lt;enumeration value="SolicituddeEmisiónEsmeraldas"/>
 *     &lt;enumeration value="SolicituddeEmisiónGuayaquil"/>
 *     &lt;enumeration value="SolicituddeEmisiónIbarra"/>
 *     &lt;enumeration value="SolicituddeEmisiónLoja"/>
 *     &lt;enumeration value="SolicituddeEmisiónMachala"/>
 *     &lt;enumeration value="SolicituddeEmisiónManta"/>
 *     &lt;enumeration value="SolicituddeEmisiónPortoviejo"/>
 *     &lt;enumeration value="SolicituddeEmisiónPuyo"/>
 *     &lt;enumeration value="SolicituddeEmisiónRiobamba"/>
 *     &lt;enumeration value="SolicituddeEmisiónSantoDomingo"/>
 *     &lt;enumeration value="Visado"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "WorkFlowType")
@XmlEnum
public enum WorkFlowType {

    @XmlEnumValue("AdjuntaralFile")
    ADJUNTARAL_FILE("AdjuntaralFile"),
    @XmlEnumValue("Fianzas")
    FIANZAS("Fianzas"),
    @XmlEnumValue("FianzasEsmeraldas")
    FIANZAS_ESMERALDAS("FianzasEsmeraldas"),
    @XmlEnumValue("FianzasGuayaquil")
    FIANZAS_GUAYAQUIL("FianzasGuayaquil"),
    @XmlEnumValue("FianzasMachala")
    FIANZAS_MACHALA("FianzasMachala"),
    @XmlEnumValue("FianzasManta")
    FIANZAS_MANTA("FianzasManta"),
    @XmlEnumValue("FianzasPortoviejo")
    FIANZAS_PORTOVIEJO("FianzasPortoviejo"),
    @XmlEnumValue("Impresi\u00f3nAmbato")
    IMPRESIÓN_AMBATO("Impresi\u00f3nAmbato"),
    @XmlEnumValue("Impresi\u00f3nCuenca")
    IMPRESIÓN_CUENCA("Impresi\u00f3nCuenca"),
    @XmlEnumValue("Impresi\u00f3nEsmeraldas")
    IMPRESIÓN_ESMERALDAS("Impresi\u00f3nEsmeraldas"),
    @XmlEnumValue("Impresi\u00f3nGuayaquil")
    IMPRESIÓN_GUAYAQUIL("Impresi\u00f3nGuayaquil"),
    @XmlEnumValue("Impresi\u00f3nIbarra")
    IMPRESIÓN_IBARRA("Impresi\u00f3nIbarra"),
    @XmlEnumValue("Impresi\u00f3nLoja")
    IMPRESIÓN_LOJA("Impresi\u00f3nLoja"),
    @XmlEnumValue("Impresi\u00f3nMachala")
    IMPRESIÓN_MACHALA("Impresi\u00f3nMachala"),
    @XmlEnumValue("Impresi\u00f3nManta")
    IMPRESIÓN_MANTA("Impresi\u00f3nManta"),
    @XmlEnumValue("Impresi\u00f3nPortoviejo")
    IMPRESIÓN_PORTOVIEJO("Impresi\u00f3nPortoviejo"),
    @XmlEnumValue("Impresi\u00f3nPuyo")
    IMPRESIÓN_PUYO("Impresi\u00f3nPuyo"),
    @XmlEnumValue("Impresi\u00f3nRiobamba")
    IMPRESIÓN_RIOBAMBA("Impresi\u00f3nRiobamba"),
    @XmlEnumValue("Impresi\u00f3nSantoDomingo")
    IMPRESIÓN_SANTO_DOMINGO("Impresi\u00f3nSantoDomingo"),
    @XmlEnumValue("Impresi\u00f3nSolicituddeEmisi\u00f3n")
    IMPRESIÓN_SOLICITUDDE_EMISIÓN("Impresi\u00f3nSolicituddeEmisi\u00f3n"),
    @XmlEnumValue("SolicituddeEmisi\u00f3n")
    SOLICITUDDE_EMISIÓN("SolicituddeEmisi\u00f3n"),
    @XmlEnumValue("SolicituddeEmisi\u00f3nAgenciaSur")
    SOLICITUDDE_EMISIÓN_AGENCIA_SUR("SolicituddeEmisi\u00f3nAgenciaSur"),
    @XmlEnumValue("SolicituddeEmisi\u00f3nContactCenter")
    SOLICITUDDE_EMISIÓN_CONTACT_CENTER("SolicituddeEmisi\u00f3nContactCenter"),
    @XmlEnumValue("SolicituddeEmisi\u00f3nAmbato")
    SOLICITUDDE_EMISIÓN_AMBATO("SolicituddeEmisi\u00f3nAmbato"),
    @XmlEnumValue("SolicituddeEmisi\u00f3nCuenca")
    SOLICITUDDE_EMISIÓN_CUENCA("SolicituddeEmisi\u00f3nCuenca"),
    @XmlEnumValue("SolicituddeEmisi\u00f3nEsmeraldas")
    SOLICITUDDE_EMISIÓN_ESMERALDAS("SolicituddeEmisi\u00f3nEsmeraldas"),
    @XmlEnumValue("SolicituddeEmisi\u00f3nGuayaquil")
    SOLICITUDDE_EMISIÓN_GUAYAQUIL("SolicituddeEmisi\u00f3nGuayaquil"),
    @XmlEnumValue("SolicituddeEmisi\u00f3nIbarra")
    SOLICITUDDE_EMISIÓN_IBARRA("SolicituddeEmisi\u00f3nIbarra"),
    @XmlEnumValue("SolicituddeEmisi\u00f3nLoja")
    SOLICITUDDE_EMISIÓN_LOJA("SolicituddeEmisi\u00f3nLoja"),
    @XmlEnumValue("SolicituddeEmisi\u00f3nMachala")
    SOLICITUDDE_EMISIÓN_MACHALA("SolicituddeEmisi\u00f3nMachala"),
    @XmlEnumValue("SolicituddeEmisi\u00f3nManta")
    SOLICITUDDE_EMISIÓN_MANTA("SolicituddeEmisi\u00f3nManta"),
    @XmlEnumValue("SolicituddeEmisi\u00f3nPortoviejo")
    SOLICITUDDE_EMISIÓN_PORTOVIEJO("SolicituddeEmisi\u00f3nPortoviejo"),
    @XmlEnumValue("SolicituddeEmisi\u00f3nPuyo")
    SOLICITUDDE_EMISIÓN_PUYO("SolicituddeEmisi\u00f3nPuyo"),
    @XmlEnumValue("SolicituddeEmisi\u00f3nRiobamba")
    SOLICITUDDE_EMISIÓN_RIOBAMBA("SolicituddeEmisi\u00f3nRiobamba"),
    @XmlEnumValue("SolicituddeEmisi\u00f3nSantoDomingo")
    SOLICITUDDE_EMISIÓN_SANTO_DOMINGO("SolicituddeEmisi\u00f3nSantoDomingo"),
    @XmlEnumValue("Visado")
    VISADO("Visado");
    private final String value;

    WorkFlowType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static WorkFlowType fromValue(String v) {
        for (WorkFlowType c: WorkFlowType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
