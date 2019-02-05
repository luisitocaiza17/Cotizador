/**
 * Engine_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public interface Engine_Service extends javax.xml.rpc.Service {
    public java.lang.String getBasicHttpBinding_EngineAddress();

    public org.tempuri.Engine_PortType getBasicHttpBinding_Engine() throws javax.xml.rpc.ServiceException;

    public org.tempuri.Engine_PortType getBasicHttpBinding_Engine(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
