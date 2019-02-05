/**
 * PQBE_wsLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sucreagrop;

public class PQBE_wsLocator extends org.apache.axis.client.Service implements sucreagrop.PQBE_ws {

    public PQBE_wsLocator() {
    }


    public PQBE_wsLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PQBE_wsLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for pQBE_wsSoapPort
    private java.lang.String pQBE_wsSoapPort_address = "http://181.198.93.59:8080/sucreagrogt/servlet/apqbe_ws";

    public java.lang.String getpQBE_wsSoapPortAddress() {
        return pQBE_wsSoapPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String pQBE_wsSoapPortWSDDServiceName = "pQBE_wsSoapPort";

    public java.lang.String getpQBE_wsSoapPortWSDDServiceName() {
        return pQBE_wsSoapPortWSDDServiceName;
    }

    public void setpQBE_wsSoapPortWSDDServiceName(java.lang.String name) {
        pQBE_wsSoapPortWSDDServiceName = name;
    }

    public sucreagrop.PQBE_wsSoapPort getpQBE_wsSoapPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(pQBE_wsSoapPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getpQBE_wsSoapPort(endpoint);
    }

    public sucreagrop.PQBE_wsSoapPort getpQBE_wsSoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            sucreagrop.PQBE_wsSoapBindingStub _stub = new sucreagrop.PQBE_wsSoapBindingStub(portAddress, this);
            _stub.setPortName(getpQBE_wsSoapPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setpQBE_wsSoapPortEndpointAddress(java.lang.String address) {
        pQBE_wsSoapPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (sucreagrop.PQBE_wsSoapPort.class.isAssignableFrom(serviceEndpointInterface)) {
                sucreagrop.PQBE_wsSoapBindingStub _stub = new sucreagrop.PQBE_wsSoapBindingStub(new java.net.URL(pQBE_wsSoapPort_address), this);
                _stub.setPortName(getpQBE_wsSoapPortWSDDServiceName());
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
        if ("pQBE_wsSoapPort".equals(inputPortName)) {
            return getpQBE_wsSoapPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("sucreagrop", "pQBE_ws");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("sucreagrop", "pQBE_wsSoapPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("pQBE_wsSoapPort".equals(portName)) {
            setpQBE_wsSoapPortEndpointAddress(address);
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
