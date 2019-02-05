/**
 * Engine_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.VariableSistema;

public class Engine_ServiceLocator extends org.apache.axis.client.Service implements org.tempuri.Engine_Service {

    public Engine_ServiceLocator() {
    }


    public Engine_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Engine_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WS_INTEGRACION
    VariableSistemaDAO vsDAO=new VariableSistemaDAO();
    VariableSistema  vs=vsDAO.buscarPorNombre("URL_SERVICIO_ENTIDADES");
    // Use to get a proxy class for BasicHttpBinding_Engine
    private java.lang.String BasicHttpBinding_Engine_address = vs.getValor();
    

    public java.lang.String getBasicHttpBinding_EngineAddress() {
        return BasicHttpBinding_Engine_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BasicHttpBinding_EngineWSDDServiceName = "BasicHttpBinding_Engine";

    public java.lang.String getBasicHttpBinding_EngineWSDDServiceName() {
        return BasicHttpBinding_EngineWSDDServiceName;
    }

    public void setBasicHttpBinding_EngineWSDDServiceName(java.lang.String name) {
        BasicHttpBinding_EngineWSDDServiceName = name;
    }

    public org.tempuri.Engine_PortType getBasicHttpBinding_Engine() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpBinding_Engine_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpBinding_Engine(endpoint);
    }

    public org.tempuri.Engine_PortType getBasicHttpBinding_Engine(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.BasicHttpBinding_EngineStub _stub = new org.tempuri.BasicHttpBinding_EngineStub(portAddress, this);
            _stub.setPortName(getBasicHttpBinding_EngineWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpBinding_EngineEndpointAddress(java.lang.String address) {
        BasicHttpBinding_Engine_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.Engine_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.BasicHttpBinding_EngineStub _stub = new org.tempuri.BasicHttpBinding_EngineStub(new java.net.URL(BasicHttpBinding_Engine_address), this);
                _stub.setPortName(getBasicHttpBinding_EngineWSDDServiceName());
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
        if ("BasicHttpBinding_Engine".equals(inputPortName)) {
            return getBasicHttpBinding_Engine();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "Engine");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "BasicHttpBinding_Engine"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BasicHttpBinding_Engine".equals(portName)) {
            setBasicHttpBinding_EngineEndpointAddress(address);
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
