/**
 * WS_EMISIONServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.qbe.cotizador.servicios.QBE.emisionGanaderoWS;

import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.VariableSistema;

public class WS_EMISIONServiceLocator extends org.apache.axis.client.Service implements com.qbe.cotizador.servicios.QBE.emisionGanaderoWS.WS_EMISIONService {

    public WS_EMISIONServiceLocator() {
    }


    public WS_EMISIONServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WS_EMISIONServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WS_EMISION
    VariableSistemaDAO vsDAO=new VariableSistemaDAO();
    VariableSistema  vs=vsDAO.buscarPorNombre("URL_EMISION_GANADERO5");
    
    
    private java.lang.String WS_EMISION_address = vs.getValor();
    
    public java.lang.String getWS_EMISIONAddress() {
        return WS_EMISION_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WS_EMISIONWSDDServiceName = "WS_EMISION";

    public java.lang.String getWS_EMISIONWSDDServiceName() {
        return WS_EMISIONWSDDServiceName;
    }

    public void setWS_EMISIONWSDDServiceName(java.lang.String name) {
        WS_EMISIONWSDDServiceName = name;
    }

    public com.qbe.cotizador.servicios.QBE.emisionGanaderoWS.WS_EMISION getWS_EMISION() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WS_EMISION_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWS_EMISION(endpoint);
    }

    public com.qbe.cotizador.servicios.QBE.emisionGanaderoWS.WS_EMISION getWS_EMISION(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.qbe.cotizador.servicios.QBE.emisionGanaderoWS.WS_EMISIONSoapBindingStub _stub = new com.qbe.cotizador.servicios.QBE.emisionGanaderoWS.WS_EMISIONSoapBindingStub(portAddress, this);
            _stub.setPortName(getWS_EMISIONWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWS_EMISIONEndpointAddress(java.lang.String address) {
        WS_EMISION_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.qbe.cotizador.servicios.QBE.emisionGanaderoWS.WS_EMISION.class.isAssignableFrom(serviceEndpointInterface)) {
                com.qbe.cotizador.servicios.QBE.emisionGanaderoWS.WS_EMISIONSoapBindingStub _stub = new com.qbe.cotizador.servicios.QBE.emisionGanaderoWS.WS_EMISIONSoapBindingStub(new java.net.URL(WS_EMISION_address), this);
                _stub.setPortName(getWS_EMISIONWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WS_EMISION".equals(inputPortName)) {
            return getWS_EMISION();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://WS_EMISION.services.ensurance.localhost/", "WS_EMISIONService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://WS_EMISION.services.ensurance.localhost/", "WS_EMISION"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WS_EMISION".equals(portName)) {
            setWS_EMISIONEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
