/**
 * BasicHttpBinding_EngineStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class BasicHttpBinding_EngineStub extends org.apache.axis.client.Stub implements org.tempuri.Engine_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[20];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SearchData");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "Parametro"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "TipoBusqueda"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ramo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfENTIDAD"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchDataResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDAD"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SaveData");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "RowID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/", "guid"), java.lang.String.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "rq"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.Extensible.Objects", "RequestTicket"), org.datacontract.schemas._2004._07.SmartWork_BPM_Extensible_Objects.RequestTicket.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "obj"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "EnsuranceEntity"), org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.EnsuranceEntity.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SaveDataResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SearchBeneficiario");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ramo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "parametro"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDAD"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchBeneficiarioResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SearchDress");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "EntidadId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfDIRECCION"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.DIRECCION[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchDressResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DIRECCION"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SearchEntityDress");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "EntidadId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "DireccionId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDADDIRECCION"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDADDIRECCION.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchEntityDressResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SearchPoliza");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "ramo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "entidad"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfPOLIZA"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchPolizaResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "POLIZA"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SearchContenedor");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "PadreId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "POLIZA"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchContenedorResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SearchBroker");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "poliza"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "cliente"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDAD"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchBrokerResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SearchRamo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "NombreRamo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "RAMO"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.RAMO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchRamoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SearchPais");
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfPAIS"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PAIS[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchPaisResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PAIS"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SearchProvincia");
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfPROVINCIA"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PROVINCIA[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchProvinciaResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PROVINCIA"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SearchCiudad");
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfCIUDAD"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CIUDAD[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchCiudadResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CIUDAD"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SearchCanton");
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfCANTON"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CANTON[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchCantonResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CANTON"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("LastId");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "entidad"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "tipodireccion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "LastIdResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SearchNacionalidad");
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfNACIONALIDAD"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.NACIONALIDAD[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchNacionalidadResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NACIONALIDAD"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("searchActivity");
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfACTIVIDADECONOMICA"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ACTIVIDADECONOMICA[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "searchActivityResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ACTIVIDADECONOMICA"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("SincronizarBrokers");
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("searchOrdenPago");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "NumeroOP"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ORDENPAGO"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ORDENPAGO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "searchOrdenPagoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("searchStateOrdenPago");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "NumeroOP"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), java.lang.Integer.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESTADO"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ESTADO.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "searchStateOrdenPagoResult"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("searchAcuerdos");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://tempuri.org/", "Identificacion"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setOmittable(true);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfACUERDO"));
        oper.setReturnClass(org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ACUERDO[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://tempuri.org/", "searchAcuerdosResult"));
        param = oper.getReturnParamDesc();
        param.setItemQName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ACUERDO"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[19] = oper;

    }

    public BasicHttpBinding_EngineStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public BasicHttpBinding_EngineStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public BasicHttpBinding_EngineStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
        addBindings0();
        addBindings1();
    }

    private void addBindings0() {
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
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ACTIVIDADECONOMICA");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ACTIVIDADECONOMICA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ACUERDO");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ACUERDO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AGENTE");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.AGENTE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfACTIVIDADECONOMICA");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ACTIVIDADECONOMICA[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ACTIVIDADECONOMICA");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ACTIVIDADECONOMICA");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfACUERDO");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ACUERDO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ACUERDO");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ACUERDO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfAGENTE");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.AGENTE[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AGENTE");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "AGENTE");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfBENEFICIARIO");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.BENEFICIARIO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "BENEFICIARIO");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "BENEFICIARIO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfCANTON");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CANTON[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CANTON");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CANTON");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfCIUDAD");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CIUDAD[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CIUDAD");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CIUDAD");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfCLIENTE");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CLIENTE[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CLIENTE");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CLIENTE");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfDIRECCION");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.DIRECCION[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DIRECCION");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DIRECCION");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfDOCUMENTO");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.DOCUMENTO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DOCUMENTO");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DOCUMENTO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfENDOSO");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSO");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfENDOSOAGENTE");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSOAGENTE[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSOAGENTE");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSOAGENTE");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfENDOSOITEM");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSOITEM[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSOITEM");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSOITEM");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfENTIDAD");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDAD");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDAD");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfNACIONALIDAD");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.NACIONALIDAD[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NACIONALIDAD");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NACIONALIDAD");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfORDENPAGO");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ORDENPAGO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ORDENPAGO");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ORDENPAGO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfPAIS");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PAIS[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PAIS");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PAIS");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfPOLIZA");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "POLIZA");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "POLIZA");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfPROVINCIA");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PROVINCIA[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PROVINCIA");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PROVINCIA");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfROLENTIDAD");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ROLENTIDAD[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ROLENTIDAD");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ROLENTIDAD");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ArrayOfROLENTIDADACUERDO");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ROLENTIDADACUERDO[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ROLENTIDADACUERDO");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ROLENTIDADACUERDO");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "BENEFICIARIO");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.BENEFICIARIO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CANTON");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CANTON.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CIUDAD");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CIUDAD.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "CLIENTE");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CLIENTE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DIRECCION");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.DIRECCION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "DOCUMENTO");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.DOCUMENTO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "EMPRESA");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.EMPRESA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSO");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSOAGENTE");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSOAGENTE.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENDOSOITEM");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENDOSOITEM.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "EnsuranceEntity");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.EnsuranceEntity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDAD");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ENTIDADDIRECCION");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDADDIRECCION.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ESTADO");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ESTADO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "NACIONALIDAD");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.NACIONALIDAD.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ORDENPAGO");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ORDENPAGO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PAIS");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PAIS.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PERSONA");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PERSONA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "POLIZA");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "PROVINCIA");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PROVINCIA.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "RAMO");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.RAMO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ROLENTIDAD");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ROLENTIDAD.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "ROLENTIDADACUERDO");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ROLENTIDADACUERDO.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.BPMLogicEnsurance", "TIPOROL");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.TIPOROL.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/SmartWork.BPM.Extensible.Objects", "RequestTicket");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.SmartWork_BPM_Extensible_Objects.RequestTicket.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityObject");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityObject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReference");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReference.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfACUERDOFG7c7FF7");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfACUERDOFG7C7FF7.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfAGENTEFG7c7FF7");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfAGENTEFG7C7FF7.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfCLIENTEFG7c7FF7");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfCLIENTEFG7C7FF7.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfEMPRESAFG7c7FF7");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfEMPRESAFG7C7FF7.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfENDOSOFG7c7FF7");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENDOSOFG7C7FF7.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfENDOSOITEMFG7c7FF7");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENDOSOITEMFG7C7FF7.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfENTIDADFG7c7FF7");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfENTIDADFG7C7FF7.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfESTADOFG7c7FF7");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfESTADOFG7C7FF7.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfPAISFG7c7FF7");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfPAISFG7C7FF7.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfPERSONAFG7c7FF7");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfPERSONAFG7C7FF7.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfPOLIZAFG7c7FF7");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfPOLIZAFG7C7FF7.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfPROVINCIAFG7c7FF7");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfPROVINCIAFG7C7FF7.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfRAMOFG7c7FF7");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfRAMOFG7C7FF7.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfROLENTIDADFG7c7FF7");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfROLENTIDADFG7C7FF7.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "EntityReferenceOfTIPOROLFG7c7FF7");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.EntityReferenceOfTIPOROLFG7C7FF7.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "RelatedEnd");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.RelatedEnd.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data.Objects.DataClasses", "StructuralObject");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_Data_Objects_DataClasses.StructuralObject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data", "ArrayOfEntityKeyMember");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_Data.EntityKeyMember[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data", "EntityKeyMember");
            qName2 = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data", "EntityKeyMember");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data", "EntityKey");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_Data.EntityKey.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/System.Data", "EntityKeyMember");
            cachedSerQNames.add(qName);
            cls = org.datacontract.schemas._2004._07.System_Data.EntityKeyMember.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/", "char");
            cachedSerQNames.add(qName);
            cls = int.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/", "duration");
            cachedSerQNames.add(qName);
            cls = org.apache.axis.types.Duration.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/", "guid");
            cachedSerQNames.add(qName);
            cls = java.lang.String.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">LastId");
            cachedSerQNames.add(qName);
            cls = org.tempuri.LastId.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">LastIdResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.LastIdResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SaveData");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SaveData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SaveDataResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SaveDataResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">searchActivity");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchActivity.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">searchActivityResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchActivityResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">searchAcuerdos");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchAcuerdos.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">searchAcuerdosResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchAcuerdosResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchBeneficiario");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchBeneficiario.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchBeneficiarioResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchBeneficiarioResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchBroker");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchBroker.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchBrokerResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchBrokerResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchCanton");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchCanton.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchCantonResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchCantonResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchCiudad");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchCiudad.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchCiudadResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchCiudadResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchContenedor");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchContenedor.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchContenedorResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchContenedorResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchData");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchDataResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchDataResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchDress");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchDress.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchDressResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchDressResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchEntityDress");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchEntityDress.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchEntityDressResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchEntityDressResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchNacionalidad");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchNacionalidad.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchNacionalidadResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchNacionalidadResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">searchOrdenPago");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchOrdenPago.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }
    private void addBindings1() {
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
            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">searchOrdenPagoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchOrdenPagoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchPais");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchPais.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchPaisResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchPaisResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchPoliza");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchPoliza.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchPolizaResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchPolizaResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchProvincia");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchProvincia.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchProvinciaResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchProvinciaResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchRamo");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchRamo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SearchRamoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchRamoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">searchStateOrdenPago");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchStateOrdenPago.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">searchStateOrdenPagoResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SearchStateOrdenPagoResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SincronizarBrokers");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SincronizarBrokers.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://tempuri.org/", ">SincronizarBrokersResponse");
            cachedSerQNames.add(qName);
            cls = org.tempuri.SincronizarBrokersResponse.class;
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
                    _call.setEncodingStyle(null);
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

    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD[] searchData(java.lang.String parametro, java.lang.Integer tipoBusqueda, java.lang.String ramo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/Engine/SearchData");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchData"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parametro, tipoBusqueda, ramo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String saveData(java.lang.String rowID, org.datacontract.schemas._2004._07.SmartWork_BPM_Extensible_Objects.RequestTicket rq, org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.EnsuranceEntity obj) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/Engine/SaveData");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SaveData"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {rowID, rq, obj});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD searchBeneficiario(java.lang.String ramo, java.lang.String parametro) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/Engine/SearchBeneficiario");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchBeneficiario"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {ramo, parametro});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.DIRECCION[] searchDress(java.lang.String entidadId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/Engine/SearchDress");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchDress"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entidadId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.DIRECCION[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.DIRECCION[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.DIRECCION[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDADDIRECCION searchEntityDress(java.lang.String entidadId, java.lang.String direccionId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/Engine/SearchEntityDress");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchEntityDress"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entidadId, direccionId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDADDIRECCION) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDADDIRECCION) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDADDIRECCION.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA[] searchPoliza(java.lang.String ramo, java.lang.String entidad) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/Engine/SearchPoliza");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchPoliza"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {ramo, entidad});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA searchContenedor(java.lang.String padreId) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/Engine/SearchContenedor");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchContenedor"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {padreId});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.POLIZA.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD searchBroker(java.lang.String poliza, java.lang.String cliente) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/Engine/SearchBroker");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchBroker"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {poliza, cliente});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ENTIDAD.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.RAMO searchRamo(java.lang.String nombreRamo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/Engine/SearchRamo");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchRamo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {nombreRamo});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.RAMO) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.RAMO) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.RAMO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PAIS[] searchPais() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/Engine/SearchPais");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchPais"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PAIS[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PAIS[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PAIS[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PROVINCIA[] searchProvincia() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/Engine/SearchProvincia");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchProvincia"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PROVINCIA[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PROVINCIA[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.PROVINCIA[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CIUDAD[] searchCiudad() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/Engine/SearchCiudad");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchCiudad"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CIUDAD[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CIUDAD[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CIUDAD[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CANTON[] searchCanton() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/Engine/SearchCanton");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchCanton"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CANTON[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CANTON[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.CANTON[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public java.lang.String lastId(java.lang.String entidad, java.lang.String tipodireccion) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/Engine/LastId");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "LastId"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {entidad, tipodireccion});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.NACIONALIDAD[] searchNacionalidad() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/Engine/SearchNacionalidad");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SearchNacionalidad"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.NACIONALIDAD[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.NACIONALIDAD[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.NACIONALIDAD[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ACTIVIDADECONOMICA[] searchActivity() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/Engine/searchActivity");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "searchActivity"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ACTIVIDADECONOMICA[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ACTIVIDADECONOMICA[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ACTIVIDADECONOMICA[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void sincronizarBrokers() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/Engine/SincronizarBrokers");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "SincronizarBrokers"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        extractAttachments(_call);
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ORDENPAGO searchOrdenPago(java.lang.Integer numeroOP) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/Engine/searchOrdenPago");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "searchOrdenPago"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {numeroOP});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ORDENPAGO) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ORDENPAGO) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ORDENPAGO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ESTADO searchStateOrdenPago(java.lang.Integer numeroOP) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/Engine/searchStateOrdenPago");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "searchStateOrdenPago"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {numeroOP});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ESTADO) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ESTADO) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ESTADO.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ACUERDO[] searchAcuerdos(java.lang.String identificacion) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("http://tempuri.org/Engine/searchAcuerdos");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://tempuri.org/", "searchAcuerdos"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {identificacion});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ACUERDO[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ACUERDO[]) org.apache.axis.utils.JavaUtils.convert(_resp, org.datacontract.schemas._2004._07.SmartWork_BPM_BPMLogicEnsurance.ACUERDO[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
