/**
 * RequestTicket.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.datacontract.schemas._2004._07.SmartWork_BPM_Extensible_Objects;

public class RequestTicket  implements java.io.Serializable {
    private java.lang.String applicationID;

    private java.lang.String IP;

    private java.util.Calendar loginDate;

    private java.lang.String machineName;

    private java.lang.String sessionToken;

    private java.lang.String userID;

    public RequestTicket() {
    }

    public RequestTicket(
           java.lang.String applicationID,
           java.lang.String IP,
           java.util.Calendar loginDate,
           java.lang.String machineName,
           java.lang.String sessionToken,
           java.lang.String userID) {
           this.applicationID = applicationID;
           this.IP = IP;
           this.loginDate = loginDate;
           this.machineName = machineName;
           this.sessionToken = sessionToken;
           this.userID = userID;
    }


    /**
     * Gets the applicationID value for this RequestTicket.
     * 
     * @return applicationID
     */
    public java.lang.String getApplicationID() {
        return applicationID;
    }


    /**
     * Sets the applicationID value for this RequestTicket.
     * 
     * @param applicationID
     */
    public void setApplicationID(java.lang.String applicationID) {
        this.applicationID = applicationID;
    }


    /**
     * Gets the IP value for this RequestTicket.
     * 
     * @return IP
     */
    public java.lang.String getIP() {
        return IP;
    }


    /**
     * Sets the IP value for this RequestTicket.
     * 
     * @param IP
     */
    public void setIP(java.lang.String IP) {
        this.IP = IP;
    }


    /**
     * Gets the loginDate value for this RequestTicket.
     * 
     * @return loginDate
     */
    public java.util.Calendar getLoginDate() {
        return loginDate;
    }


    /**
     * Sets the loginDate value for this RequestTicket.
     * 
     * @param loginDate
     */
    public void setLoginDate(java.util.Calendar loginDate) {
        this.loginDate = loginDate;
    }


    /**
     * Gets the machineName value for this RequestTicket.
     * 
     * @return machineName
     */
    public java.lang.String getMachineName() {
        return machineName;
    }


    /**
     * Sets the machineName value for this RequestTicket.
     * 
     * @param machineName
     */
    public void setMachineName(java.lang.String machineName) {
        this.machineName = machineName;
    }


    /**
     * Gets the sessionToken value for this RequestTicket.
     * 
     * @return sessionToken
     */
    public java.lang.String getSessionToken() {
        return sessionToken;
    }


    /**
     * Sets the sessionToken value for this RequestTicket.
     * 
     * @param sessionToken
     */
    public void setSessionToken(java.lang.String sessionToken) {
        this.sessionToken = sessionToken;
    }


    /**
     * Gets the userID value for this RequestTicket.
     * 
     * @return userID
     */
    public java.lang.String getUserID() {
        return userID;
    }


    /**
     * Sets the userID value for this RequestTicket.
     * 
     * @param userID
     */
    public void setUserID(java.lang.String userID) {
        this.userID = userID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RequestTicket)) return false;
        RequestTicket other = (RequestTicket) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.applicationID==null && other.getApplicationID()==null) || 
             (this.applicationID!=null &&
              this.applicationID.equals(other.getApplicationID()))) &&
            ((this.IP==null && other.getIP()==null) || 
             (this.IP!=null &&
              this.IP.equals(other.getIP()))) &&
            ((this.loginDate==null && other.getLoginDate()==null) || 
             (this.loginDate!=null &&
              this.loginDate.equals(other.getLoginDate()))) &&
            ((this.machineName==null && other.getMachineName()==null) || 
             (this.machineName!=null &&
              this.machineName.equals(other.getMachineName()))) &&
            ((this.sessionToken==null && other.getSessionToken()==null) || 
             (this.sessionToken!=null &&
              this.sessionToken.equals(other.getSessionToken()))) &&
            ((this.userID==null && other.getUserID()==null) || 
             (this.userID!=null &&
              this.userID.equals(other.getUserID())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getApplicationID() != null) {
            _hashCode += getApplicationID().hashCode();
        }
        if (getIP() != null) {
            _hashCode += getIP().hashCode();
        }
        if (getLoginDate() != null) {
            _hashCode += getLoginDate().hashCode();
        }
        if (getMachineName() != null) {
            _hashCode += getMachineName().hashCode();
        }
        if (getSessionToken() != null) {
            _hashCode += getSessionToken().hashCode();
        }
        if (getUserID() != null) {
            _hashCode += getUserID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RequestTicket.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.Extensible.Objects", "RequestTicket"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applicationID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.Extensible.Objects", "ApplicationID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IP");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.Extensible.Objects", "IP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.Extensible.Objects", "LoginDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("machineName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.Extensible.Objects", "MachineName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sessionToken");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.Extensible.Objects", "SessionToken"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.Extensible.Objects", "UserID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
