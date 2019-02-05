/**
 * WSActualizarCrearEntidadesONBASEServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _174._13._39._181.ensurance.services.WSActualizarCrearEntidadesONBASE;

import com.qbe.cotizador.dao.seguridad.VariableSistemaDAO;
import com.qbe.cotizador.model.VariableSistema;

public class WSActualizarCrearEntidadesONBASEServiceLocator extends org.apache.axis.client.Service implements _174._13._39._181.ensurance.services.WSActualizarCrearEntidadesONBASE.WSActualizarCrearEntidadesONBASEService {

    public WSActualizarCrearEntidadesONBASEServiceLocator() {
    }


    public WSActualizarCrearEntidadesONBASEServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WSActualizarCrearEntidadesONBASEServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

 // Use to get a proxy class 
    VariableSistemaDAO vsDAO=new VariableSistemaDAO();
    VariableSistema  vs=vsDAO.buscarPorNombre("URL_CREACION_USUARIO");
    
    // Use to get a proxy class for WSActualizarCrearEntidadesONBASE
    private java.lang.String WSActualizarCrearEntidadesONBASE_address =  vs.getValor();
    public java.lang.String getWSActualizarCrearEntidadesONBASEAddress() {
        return WSActualizarCrearEntidadesONBASE_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSActualizarCrearEntidadesONBASEWSDDServiceName = "WSActualizarCrearEntidadesONBASE";

    public java.lang.String getWSActualizarCrearEntidadesONBASEWSDDServiceName() {
        return WSActualizarCrearEntidadesONBASEWSDDServiceName;
    }

    public void setWSActualizarCrearEntidadesONBASEWSDDServiceName(java.lang.String name) {
        WSActualizarCrearEntidadesONBASEWSDDServiceName = name;
    }

    public _174._13._39._181.ensurance.services.WSActualizarCrearEntidadesONBASE.WSActualizarCrearEntidadesONBASE getWSActualizarCrearEntidadesONBASE() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSActualizarCrearEntidadesONBASE_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSActualizarCrearEntidadesONBASE(endpoint);
    }

    public _174._13._39._181.ensurance.services.WSActualizarCrearEntidadesONBASE.WSActualizarCrearEntidadesONBASE getWSActualizarCrearEntidadesONBASE(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            _174._13._39._181.ensurance.services.WSActualizarCrearEntidadesONBASE.WSActualizarCrearEntidadesONBASESoapBindingStub _stub = new _174._13._39._181.ensurance.services.WSActualizarCrearEntidadesONBASE.WSActualizarCrearEntidadesONBASESoapBindingStub(portAddress, this);
            _stub.setPortName(getWSActualizarCrearEntidadesONBASEWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSActualizarCrearEntidadesONBASEEndpointAddress(java.lang.String address) {
        WSActualizarCrearEntidadesONBASE_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (_174._13._39._181.ensurance.services.WSActualizarCrearEntidadesONBASE.WSActualizarCrearEntidadesONBASE.class.isAssignableFrom(serviceEndpointInterface)) {
                _174._13._39._181.ensurance.services.WSActualizarCrearEntidadesONBASE.WSActualizarCrearEntidadesONBASESoapBindingStub _stub = new _174._13._39._181.ensurance.services.WSActualizarCrearEntidadesONBASE.WSActualizarCrearEntidadesONBASESoapBindingStub(new java.net.URL(WSActualizarCrearEntidadesONBASE_address), this);
                _stub.setPortName(getWSActualizarCrearEntidadesONBASEWSDDServiceName());
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
        if ("WSActualizarCrearEntidadesONBASE".equals(inputPortName)) {
            return getWSActualizarCrearEntidadesONBASE();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://181.39.13.174:8085/ensurance/services/WSActualizarCrearEntidadesONBASE", "WSActualizarCrearEntidadesONBASEService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://181.39.13.174:8085/ensurance/services/WSActualizarCrearEntidadesONBASE", "WSActualizarCrearEntidadesONBASE"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSActualizarCrearEntidadesONBASE".equals(portName)) {
            setWSActualizarCrearEntidadesONBASEEndpointAddress(address);
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
