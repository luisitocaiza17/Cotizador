/**
 * WS_INTEGRACIONSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package _177._12._168._192.ensurance.services.WS_INTEGRACION;

public class WS_INTEGRACIONSoapBindingStub extends org.apache.axis.client.Stub implements _177._12._168._192.ensurance.services.WS_INTEGRACION.ServicioIntegracion {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[2];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("dummy");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "p1"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ClienteDTO"), com.tandi.servicios.DTOs.ClienteDTO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "p2"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ComponenteXDocDTO"), com.tandi.servicios.DTOs.ComponenteXDocDTO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "p3"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ConfiguracionPagoDTO"), com.tandi.servicios.DTOs.ConfiguracionPagoDTO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "p4"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ProductoDTO"), com.tandi.servicios.DTOs.ProductoDTO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "p5"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "PolizaDTO"), com.tandi.servicios.DTOs.PolizaDTO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "p6"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "EndosoDTO"), com.tandi.servicios.DTOs.EndosoDTO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "p7"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "DeducibleDTO"), com.tandi.servicios.DTOs.DeducibleDTO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "p8"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ErrorDTO"), com.tandi.servicios.DTOs.ErrorDTO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "p9"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "FacturaDTO"), com.tandi.servicios.DTOs.FacturaDTO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "p10"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ItemDTO"), com.tandi.servicios.DTOs.ItemDTO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "p11"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "PagoDTO"), com.tandi.servicios.DTOs.PagoDTO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "p12"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "RequestDTO"), com.tandi.servicios.DTOs.RequestDTO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "p13"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "RespuestaDTO"), com.tandi.servicios.DTOs.RespuestaDTO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "p14"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "PredioDTO"), com.tandi.servicios.DTOs.PredioDTO.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "p15"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "CoberturaDTO"), com.tandi.servicios.DTOs.CoberturaDTO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("integrar");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "request"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "RequestDTO"), com.tandi.servicios.DTOs.RequestDTO.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "RespuestaDTO"));
        oper.setReturnClass(com.tandi.servicios.DTOs.RespuestaDTO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "integrarReturn"));
        oper.setStyle(org.apache.axis.constants.Style.RPC);
        oper.setUse(org.apache.axis.constants.Use.ENCODED);
        _operations[1] = oper;

    }

    public WS_INTEGRACIONSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public WS_INTEGRACIONSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public WS_INTEGRACIONSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://192.168.12.177:8084/ensurance/services/WS_INTEGRACION", "ArrayOf_tns1_BlanketDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.BlanketDTO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "BlanketDTO");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://192.168.12.177:8084/ensurance/services/WS_INTEGRACION", "ArrayOf_tns1_ClienteDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.ClienteDTO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ClienteDTO");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://192.168.12.177:8084/ensurance/services/WS_INTEGRACION", "ArrayOf_tns1_CoberturaDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.CoberturaDTO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "CoberturaDTO");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://192.168.12.177:8084/ensurance/services/WS_INTEGRACION", "ArrayOf_tns1_DeducibleDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.DeducibleDTO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "DeducibleDTO");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://192.168.12.177:8084/ensurance/services/WS_INTEGRACION", "ArrayOf_tns1_DireccionDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.DireccionDTO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "DireccionDTO");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://192.168.12.177:8084/ensurance/services/WS_INTEGRACION", "ArrayOf_tns1_ExtraVehiculoDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.ExtraVehiculoDTO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ExtraVehiculoDTO");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://192.168.12.177:8084/ensurance/services/WS_INTEGRACION", "ArrayOf_tns1_PagoDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.PagoDTO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "PagoDTO");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://192.168.12.177:8084/ensurance/services/WS_INTEGRACION", "ArrayOf_tns1_PredioDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.PredioDTO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "PredioDTO");
            qName2 = null;
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "BlanketDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.BlanketDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ClienteDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.ClienteDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "CoberturaDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.CoberturaDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ComponenteXDocDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.ComponenteXDocDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ConfiguracionPagoDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.ConfiguracionPagoDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "DeducibleDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.DeducibleDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "DireccionDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.DireccionDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "EndosoDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.EndosoDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ErrorDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.ErrorDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ExtraVehiculoDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.ExtraVehiculoDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "FacturaDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.FacturaDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ItemDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.ItemDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "LoteCultivoDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.LoteCultivoDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "PagoDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.PagoDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "PolizaDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.PolizaDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "PredioDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.PredioDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ProductoDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.ProductoDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "RequestDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.RequestDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "RespuestaDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.RespuestaDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "TransporteDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.TransporteDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "VehiculoDTO");
            cachedSerQNames.add(qName);
            cls = com.tandi.servicios.DTOs.VehiculoDTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
                    _call.setEncodingStyle(org.apache.axis.Constants.URI_SOAP11_ENC);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public void dummy(com.tandi.servicios.DTOs.ClienteDTO p1, com.tandi.servicios.DTOs.ComponenteXDocDTO p2, com.tandi.servicios.DTOs.ConfiguracionPagoDTO p3, com.tandi.servicios.DTOs.ProductoDTO p4, com.tandi.servicios.DTOs.PolizaDTO p5, com.tandi.servicios.DTOs.EndosoDTO p6, com.tandi.servicios.DTOs.DeducibleDTO p7, com.tandi.servicios.DTOs.ErrorDTO p8, com.tandi.servicios.DTOs.FacturaDTO p9, com.tandi.servicios.DTOs.ItemDTO p10, com.tandi.servicios.DTOs.PagoDTO p11, com.tandi.servicios.DTOs.RequestDTO p12, com.tandi.servicios.DTOs.RespuestaDTO p13, com.tandi.servicios.DTOs.PredioDTO p14, com.tandi.servicios.DTOs.CoberturaDTO p15) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://integracion.servicios.tandi.com", "dummy"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.tandi.servicios.DTOs.RespuestaDTO integrar(com.tandi.servicios.DTOs.RequestDTO request) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://integracion.servicios.tandi.com", "integrar"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {request});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.tandi.servicios.DTOs.RespuestaDTO) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.tandi.servicios.DTOs.RespuestaDTO) org.apache.axis.utils.JavaUtils.convert(_resp, com.tandi.servicios.DTOs.RespuestaDTO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
