/**
 * ServicioIntegracionServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _177._12._168._192.ensurance.services.WS_INTEGRACION;

public class ServicioIntegracionServiceLocator extends org.apache.axis.client.Service implements _177._12._168._192.ensurance.services.WS_INTEGRACION.ServicioIntegracionService {

    public ServicioIntegracionServiceLocator() {
    }


    public ServicioIntegracionServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServicioIntegracionServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WS_INTEGRACION
    private java.lang.String WS_INTEGRACION_address = "http://192.168.12.177:8084/ensurance/services/WS_INTEGRACION";

    public java.lang.String getWS_INTEGRACIONAddress() {
        return WS_INTEGRACION_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WS_INTEGRACIONWSDDServiceName = "WS_INTEGRACION";

    public java.lang.String getWS_INTEGRACIONWSDDServiceName() {
        return WS_INTEGRACIONWSDDServiceName;
    }

    public void setWS_INTEGRACIONWSDDServiceName(java.lang.String name) {
        WS_INTEGRACIONWSDDServiceName = name;
    }

    public _177._12._168._192.ensurance.services.WS_INTEGRACION.ServicioIntegracion getWS_INTEGRACION() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WS_INTEGRACION_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWS_INTEGRACION(endpoint);
    }

    public _177._12._168._192.ensurance.services.WS_INTEGRACION.ServicioIntegracion getWS_INTEGRACION(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            _177._12._168._192.ensurance.services.WS_INTEGRACION.WS_INTEGRACIONSoapBindingStub _stub = new _177._12._168._192.ensurance.services.WS_INTEGRACION.WS_INTEGRACIONSoapBindingStub(portAddress, this);
            _stub.setPortName(getWS_INTEGRACIONWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWS_INTEGRACIONEndpointAddress(java.lang.String address) {
        WS_INTEGRACION_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (_177._12._168._192.ensurance.services.WS_INTEGRACION.ServicioIntegracion.class.isAssignableFrom(serviceEndpointInterface)) {
                _177._12._168._192.ensurance.services.WS_INTEGRACION.WS_INTEGRACIONSoapBindingStub _stub = new _177._12._168._192.ensurance.services.WS_INTEGRACION.WS_INTEGRACIONSoapBindingStub(new java.net.URL(WS_INTEGRACION_address), this);
                _stub.setPortName(getWS_INTEGRACIONWSDDServiceName());
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
        if ("WS_INTEGRACION".equals(inputPortName)) {
            return getWS_INTEGRACION();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://192.168.12.177:8084/ensurance/services/WS_INTEGRACION", "ServicioIntegracionService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://192.168.12.177:8084/ensurance/services/WS_INTEGRACION", "WS_INTEGRACION"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WS_INTEGRACION".equals(portName)) {
            setWS_INTEGRACIONEndpointAddress(address);
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
