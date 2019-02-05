/**
 * WebServiceSeguridadImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.qbe.servicios.implementacion.seguridad;

public class WebServiceSeguridadImplServiceLocator extends org.apache.axis.client.Service implements com.qbe.servicios.implementacion.seguridad.WebServiceSeguridadImplService {

    public WebServiceSeguridadImplServiceLocator() {
    }


    public WebServiceSeguridadImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WebServiceSeguridadImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WebServiceSeguridadImplPort
    private java.lang.String WebServiceSeguridadImplPort_address = "http://10.10.21.115:80/SeguridadServicios/seguridad";

    public java.lang.String getWebServiceSeguridadImplPortAddress() {
        return WebServiceSeguridadImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WebServiceSeguridadImplPortWSDDServiceName = "WebServiceSeguridadImplPort";

    public java.lang.String getWebServiceSeguridadImplPortWSDDServiceName() {
        return WebServiceSeguridadImplPortWSDDServiceName;
    }

    public void setWebServiceSeguridadImplPortWSDDServiceName(java.lang.String name) {
        WebServiceSeguridadImplPortWSDDServiceName = name;
    }

    public com.qbe.servicios.interfaces.seguridad.WebServiceSeguridadInterface getWebServiceSeguridadImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WebServiceSeguridadImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWebServiceSeguridadImplPort(endpoint);
    }

    public com.qbe.servicios.interfaces.seguridad.WebServiceSeguridadInterface getWebServiceSeguridadImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.qbe.servicios.implementacion.seguridad.WebServiceSeguridadImplPortBindingStub _stub = new com.qbe.servicios.implementacion.seguridad.WebServiceSeguridadImplPortBindingStub(portAddress, this);
            _stub.setPortName(getWebServiceSeguridadImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWebServiceSeguridadImplPortEndpointAddress(java.lang.String address) {
        WebServiceSeguridadImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.qbe.servicios.interfaces.seguridad.WebServiceSeguridadInterface.class.isAssignableFrom(serviceEndpointInterface)) {
                com.qbe.servicios.implementacion.seguridad.WebServiceSeguridadImplPortBindingStub _stub = new com.qbe.servicios.implementacion.seguridad.WebServiceSeguridadImplPortBindingStub(new java.net.URL(WebServiceSeguridadImplPort_address), this);
                _stub.setPortName(getWebServiceSeguridadImplPortWSDDServiceName());
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
        if ("WebServiceSeguridadImplPort".equals(inputPortName)) {
            return getWebServiceSeguridadImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://seguridad.implementacion.servicios.qbe.com/", "WebServiceSeguridadImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://seguridad.implementacion.servicios.qbe.com/", "WebServiceSeguridadImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WebServiceSeguridadImplPort".equals(portName)) {
            setWebServiceSeguridadImplPortEndpointAddress(address);
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
