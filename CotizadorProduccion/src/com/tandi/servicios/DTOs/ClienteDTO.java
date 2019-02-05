/**
 * ClienteDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tandi.servicios.DTOs;

public class ClienteDTO  implements java.io.Serializable {
    private java.lang.String actividadEconomica;

    private java.lang.String actividadEconomicaId;

    private java.lang.String apellidos;

    private java.lang.String apellidosConyuge;

    private java.lang.String birthPlace;

    private java.lang.String canton;

    private java.lang.String ciudad;

    private java.lang.String correoElectronico;

    private java.lang.String direccion;

    private com.tandi.servicios.DTOs.DireccionDTO[] direcciones;

    private java.lang.String employeeType;

    private java.lang.String entidadId;

    private boolean esEmpresa;

    private boolean esPrimaExcenta;

    private java.lang.String faxEmpresa;

    private java.util.Calendar fechaNacimiento;

    private java.lang.String fuente;

    private java.lang.String genero;

    private java.lang.String grupoEconomicoId;

    private java.lang.String id;

    private java.lang.String identificacion;

    private double income;

    private double limiteCredito;

    private java.lang.String nacionalidad;

    private java.lang.String nombreCorto;

    private java.lang.String nombreEmpresa;

    private java.lang.String nombres;

    private java.lang.String nombresConyuge;

    private double otrosIngresos;

    private java.lang.String pais;

    private java.lang.String parroquia;

    private int porcentajeBeneficio;

    private java.lang.String provincia;

    private com.tandi.servicios.DTOs.ClienteDTO representanteLegal;

    private java.lang.String sectorEmpresa;

    private java.lang.String sectorEmpresaId;

    private java.lang.String socialObject;

    private java.lang.String telefonoCasa;

    private java.lang.String telefonoCelular;

    private java.lang.String telefonoEmpresa;

    private java.lang.String telefonoOficina;

    private java.lang.String tipoEmpleado;

    private java.lang.String tipoEmpresaId;

    private java.lang.String tipoEntidad;

    private java.lang.String tipoIdentificacion;

    private java.lang.String tituloAcademico;

    private double totalEgresos;

    private java.lang.String webSite;

    public ClienteDTO() {
    }

    public ClienteDTO(
           java.lang.String actividadEconomica,
           java.lang.String actividadEconomicaId,
           java.lang.String apellidos,
           java.lang.String apellidosConyuge,
           java.lang.String birthPlace,
           java.lang.String canton,
           java.lang.String ciudad,
           java.lang.String correoElectronico,
           java.lang.String direccion,
           com.tandi.servicios.DTOs.DireccionDTO[] direcciones,
           java.lang.String employeeType,
           java.lang.String entidadId,
           boolean esEmpresa,
           boolean esPrimaExcenta,
           java.lang.String faxEmpresa,
           java.util.Calendar fechaNacimiento,
           java.lang.String fuente,
           java.lang.String genero,
           java.lang.String grupoEconomicoId,
           java.lang.String id,
           java.lang.String identificacion,
           double income,
           double limiteCredito,
           java.lang.String nacionalidad,
           java.lang.String nombreCorto,
           java.lang.String nombreEmpresa,
           java.lang.String nombres,
           java.lang.String nombresConyuge,
           double otrosIngresos,
           java.lang.String pais,
           java.lang.String parroquia,
           int porcentajeBeneficio,
           java.lang.String provincia,
           com.tandi.servicios.DTOs.ClienteDTO representanteLegal,
           java.lang.String sectorEmpresa,
           java.lang.String sectorEmpresaId,
           java.lang.String socialObject,
           java.lang.String telefonoCasa,
           java.lang.String telefonoCelular,
           java.lang.String telefonoEmpresa,
           java.lang.String telefonoOficina,
           java.lang.String tipoEmpleado,
           java.lang.String tipoEmpresaId,
           java.lang.String tipoEntidad,
           java.lang.String tipoIdentificacion,
           java.lang.String tituloAcademico,
           double totalEgresos,
           java.lang.String webSite) {
           this.actividadEconomica = actividadEconomica;
           this.actividadEconomicaId = actividadEconomicaId;
           this.apellidos = apellidos;
           this.apellidosConyuge = apellidosConyuge;
           this.birthPlace = birthPlace;
           this.canton = canton;
           this.ciudad = ciudad;
           this.correoElectronico = correoElectronico;
           this.direccion = direccion;
           this.direcciones = direcciones;
           this.employeeType = employeeType;
           this.entidadId = entidadId;
           this.esEmpresa = esEmpresa;
           this.esPrimaExcenta = esPrimaExcenta;
           this.faxEmpresa = faxEmpresa;
           this.fechaNacimiento = fechaNacimiento;
           this.fuente = fuente;
           this.genero = genero;
           this.grupoEconomicoId = grupoEconomicoId;
           this.id = id;
           this.identificacion = identificacion;
           this.income = income;
           this.limiteCredito = limiteCredito;
           this.nacionalidad = nacionalidad;
           this.nombreCorto = nombreCorto;
           this.nombreEmpresa = nombreEmpresa;
           this.nombres = nombres;
           this.nombresConyuge = nombresConyuge;
           this.otrosIngresos = otrosIngresos;
           this.pais = pais;
           this.parroquia = parroquia;
           this.porcentajeBeneficio = porcentajeBeneficio;
           this.provincia = provincia;
           this.representanteLegal = representanteLegal;
           this.sectorEmpresa = sectorEmpresa;
           this.sectorEmpresaId = sectorEmpresaId;
           this.socialObject = socialObject;
           this.telefonoCasa = telefonoCasa;
           this.telefonoCelular = telefonoCelular;
           this.telefonoEmpresa = telefonoEmpresa;
           this.telefonoOficina = telefonoOficina;
           this.tipoEmpleado = tipoEmpleado;
           this.tipoEmpresaId = tipoEmpresaId;
           this.tipoEntidad = tipoEntidad;
           this.tipoIdentificacion = tipoIdentificacion;
           this.tituloAcademico = tituloAcademico;
           this.totalEgresos = totalEgresos;
           this.webSite = webSite;
    }


    /**
     * Gets the actividadEconomica value for this ClienteDTO.
     * 
     * @return actividadEconomica
     */
    public java.lang.String getActividadEconomica() {
        return actividadEconomica;
    }


    /**
     * Sets the actividadEconomica value for this ClienteDTO.
     * 
     * @param actividadEconomica
     */
    public void setActividadEconomica(java.lang.String actividadEconomica) {
        this.actividadEconomica = actividadEconomica;
    }


    /**
     * Gets the actividadEconomicaId value for this ClienteDTO.
     * 
     * @return actividadEconomicaId
     */
    public java.lang.String getActividadEconomicaId() {
        return actividadEconomicaId;
    }


    /**
     * Sets the actividadEconomicaId value for this ClienteDTO.
     * 
     * @param actividadEconomicaId
     */
    public void setActividadEconomicaId(java.lang.String actividadEconomicaId) {
        this.actividadEconomicaId = actividadEconomicaId;
    }


    /**
     * Gets the apellidos value for this ClienteDTO.
     * 
     * @return apellidos
     */
    public java.lang.String getApellidos() {
        return apellidos;
    }


    /**
     * Sets the apellidos value for this ClienteDTO.
     * 
     * @param apellidos
     */
    public void setApellidos(java.lang.String apellidos) {
        this.apellidos = apellidos;
    }


    /**
     * Gets the apellidosConyuge value for this ClienteDTO.
     * 
     * @return apellidosConyuge
     */
    public java.lang.String getApellidosConyuge() {
        return apellidosConyuge;
    }


    /**
     * Sets the apellidosConyuge value for this ClienteDTO.
     * 
     * @param apellidosConyuge
     */
    public void setApellidosConyuge(java.lang.String apellidosConyuge) {
        this.apellidosConyuge = apellidosConyuge;
    }


    /**
     * Gets the birthPlace value for this ClienteDTO.
     * 
     * @return birthPlace
     */
    public java.lang.String getBirthPlace() {
        return birthPlace;
    }


    /**
     * Sets the birthPlace value for this ClienteDTO.
     * 
     * @param birthPlace
     */
    public void setBirthPlace(java.lang.String birthPlace) {
        this.birthPlace = birthPlace;
    }


    /**
     * Gets the canton value for this ClienteDTO.
     * 
     * @return canton
     */
    public java.lang.String getCanton() {
        return canton;
    }


    /**
     * Sets the canton value for this ClienteDTO.
     * 
     * @param canton
     */
    public void setCanton(java.lang.String canton) {
        this.canton = canton;
    }


    /**
     * Gets the ciudad value for this ClienteDTO.
     * 
     * @return ciudad
     */
    public java.lang.String getCiudad() {
        return ciudad;
    }


    /**
     * Sets the ciudad value for this ClienteDTO.
     * 
     * @param ciudad
     */
    public void setCiudad(java.lang.String ciudad) {
        this.ciudad = ciudad;
    }


    /**
     * Gets the correoElectronico value for this ClienteDTO.
     * 
     * @return correoElectronico
     */
    public java.lang.String getCorreoElectronico() {
        return correoElectronico;
    }


    /**
     * Sets the correoElectronico value for this ClienteDTO.
     * 
     * @param correoElectronico
     */
    public void setCorreoElectronico(java.lang.String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }


    /**
     * Gets the direccion value for this ClienteDTO.
     * 
     * @return direccion
     */
    public java.lang.String getDireccion() {
        return direccion;
    }


    /**
     * Sets the direccion value for this ClienteDTO.
     * 
     * @param direccion
     */
    public void setDireccion(java.lang.String direccion) {
        this.direccion = direccion;
    }


    /**
     * Gets the direcciones value for this ClienteDTO.
     * 
     * @return direcciones
     */
    public com.tandi.servicios.DTOs.DireccionDTO[] getDirecciones() {
        return direcciones;
    }


    /**
     * Sets the direcciones value for this ClienteDTO.
     * 
     * @param direcciones
     */
    public void setDirecciones(com.tandi.servicios.DTOs.DireccionDTO[] direcciones) {
        this.direcciones = direcciones;
    }


    /**
     * Gets the employeeType value for this ClienteDTO.
     * 
     * @return employeeType
     */
    public java.lang.String getEmployeeType() {
        return employeeType;
    }


    /**
     * Sets the employeeType value for this ClienteDTO.
     * 
     * @param employeeType
     */
    public void setEmployeeType(java.lang.String employeeType) {
        this.employeeType = employeeType;
    }


    /**
     * Gets the entidadId value for this ClienteDTO.
     * 
     * @return entidadId
     */
    public java.lang.String getEntidadId() {
        return entidadId;
    }


    /**
     * Sets the entidadId value for this ClienteDTO.
     * 
     * @param entidadId
     */
    public void setEntidadId(java.lang.String entidadId) {
        this.entidadId = entidadId;
    }


    /**
     * Gets the esEmpresa value for this ClienteDTO.
     * 
     * @return esEmpresa
     */
    public boolean isEsEmpresa() {
        return esEmpresa;
    }


    /**
     * Sets the esEmpresa value for this ClienteDTO.
     * 
     * @param esEmpresa
     */
    public void setEsEmpresa(boolean esEmpresa) {
        this.esEmpresa = esEmpresa;
    }


    /**
     * Gets the esPrimaExcenta value for this ClienteDTO.
     * 
     * @return esPrimaExcenta
     */
    public boolean isEsPrimaExcenta() {
        return esPrimaExcenta;
    }


    /**
     * Sets the esPrimaExcenta value for this ClienteDTO.
     * 
     * @param esPrimaExcenta
     */
    public void setEsPrimaExcenta(boolean esPrimaExcenta) {
        this.esPrimaExcenta = esPrimaExcenta;
    }


    /**
     * Gets the faxEmpresa value for this ClienteDTO.
     * 
     * @return faxEmpresa
     */
    public java.lang.String getFaxEmpresa() {
        return faxEmpresa;
    }


    /**
     * Sets the faxEmpresa value for this ClienteDTO.
     * 
     * @param faxEmpresa
     */
    public void setFaxEmpresa(java.lang.String faxEmpresa) {
        this.faxEmpresa = faxEmpresa;
    }


    /**
     * Gets the fechaNacimiento value for this ClienteDTO.
     * 
     * @return fechaNacimiento
     */
    public java.util.Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }


    /**
     * Sets the fechaNacimiento value for this ClienteDTO.
     * 
     * @param fechaNacimiento
     */
    public void setFechaNacimiento(java.util.Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    /**
     * Gets the fuente value for this ClienteDTO.
     * 
     * @return fuente
     */
    public java.lang.String getFuente() {
        return fuente;
    }


    /**
     * Sets the fuente value for this ClienteDTO.
     * 
     * @param fuente
     */
    public void setFuente(java.lang.String fuente) {
        this.fuente = fuente;
    }


    /**
     * Gets the genero value for this ClienteDTO.
     * 
     * @return genero
     */
    public java.lang.String getGenero() {
        return genero;
    }


    /**
     * Sets the genero value for this ClienteDTO.
     * 
     * @param genero
     */
    public void setGenero(java.lang.String genero) {
        this.genero = genero;
    }


    /**
     * Gets the grupoEconomicoId value for this ClienteDTO.
     * 
     * @return grupoEconomicoId
     */
    public java.lang.String getGrupoEconomicoId() {
        return grupoEconomicoId;
    }


    /**
     * Sets the grupoEconomicoId value for this ClienteDTO.
     * 
     * @param grupoEconomicoId
     */
    public void setGrupoEconomicoId(java.lang.String grupoEconomicoId) {
        this.grupoEconomicoId = grupoEconomicoId;
    }


    /**
     * Gets the id value for this ClienteDTO.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this ClienteDTO.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the identificacion value for this ClienteDTO.
     * 
     * @return identificacion
     */
    public java.lang.String getIdentificacion() {
        return identificacion;
    }


    /**
     * Sets the identificacion value for this ClienteDTO.
     * 
     * @param identificacion
     */
    public void setIdentificacion(java.lang.String identificacion) {
        this.identificacion = identificacion;
    }


    /**
     * Gets the income value for this ClienteDTO.
     * 
     * @return income
     */
    public double getIncome() {
        return income;
    }


    /**
     * Sets the income value for this ClienteDTO.
     * 
     * @param income
     */
    public void setIncome(double income) {
        this.income = income;
    }


    /**
     * Gets the limiteCredito value for this ClienteDTO.
     * 
     * @return limiteCredito
     */
    public double getLimiteCredito() {
        return limiteCredito;
    }


    /**
     * Sets the limiteCredito value for this ClienteDTO.
     * 
     * @param limiteCredito
     */
    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }


    /**
     * Gets the nacionalidad value for this ClienteDTO.
     * 
     * @return nacionalidad
     */
    public java.lang.String getNacionalidad() {
        return nacionalidad;
    }


    /**
     * Sets the nacionalidad value for this ClienteDTO.
     * 
     * @param nacionalidad
     */
    public void setNacionalidad(java.lang.String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }


    /**
     * Gets the nombreCorto value for this ClienteDTO.
     * 
     * @return nombreCorto
     */
    public java.lang.String getNombreCorto() {
        return nombreCorto;
    }


    /**
     * Sets the nombreCorto value for this ClienteDTO.
     * 
     * @param nombreCorto
     */
    public void setNombreCorto(java.lang.String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }


    /**
     * Gets the nombreEmpresa value for this ClienteDTO.
     * 
     * @return nombreEmpresa
     */
    public java.lang.String getNombreEmpresa() {
        return nombreEmpresa;
    }


    /**
     * Sets the nombreEmpresa value for this ClienteDTO.
     * 
     * @param nombreEmpresa
     */
    public void setNombreEmpresa(java.lang.String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }


    /**
     * Gets the nombres value for this ClienteDTO.
     * 
     * @return nombres
     */
    public java.lang.String getNombres() {
        return nombres;
    }


    /**
     * Sets the nombres value for this ClienteDTO.
     * 
     * @param nombres
     */
    public void setNombres(java.lang.String nombres) {
        this.nombres = nombres;
    }


    /**
     * Gets the nombresConyuge value for this ClienteDTO.
     * 
     * @return nombresConyuge
     */
    public java.lang.String getNombresConyuge() {
        return nombresConyuge;
    }


    /**
     * Sets the nombresConyuge value for this ClienteDTO.
     * 
     * @param nombresConyuge
     */
    public void setNombresConyuge(java.lang.String nombresConyuge) {
        this.nombresConyuge = nombresConyuge;
    }


    /**
     * Gets the otrosIngresos value for this ClienteDTO.
     * 
     * @return otrosIngresos
     */
    public double getOtrosIngresos() {
        return otrosIngresos;
    }


    /**
     * Sets the otrosIngresos value for this ClienteDTO.
     * 
     * @param otrosIngresos
     */
    public void setOtrosIngresos(double otrosIngresos) {
        this.otrosIngresos = otrosIngresos;
    }


    /**
     * Gets the pais value for this ClienteDTO.
     * 
     * @return pais
     */
    public java.lang.String getPais() {
        return pais;
    }


    /**
     * Sets the pais value for this ClienteDTO.
     * 
     * @param pais
     */
    public void setPais(java.lang.String pais) {
        this.pais = pais;
    }


    /**
     * Gets the parroquia value for this ClienteDTO.
     * 
     * @return parroquia
     */
    public java.lang.String getParroquia() {
        return parroquia;
    }


    /**
     * Sets the parroquia value for this ClienteDTO.
     * 
     * @param parroquia
     */
    public void setParroquia(java.lang.String parroquia) {
        this.parroquia = parroquia;
    }


    /**
     * Gets the porcentajeBeneficio value for this ClienteDTO.
     * 
     * @return porcentajeBeneficio
     */
    public int getPorcentajeBeneficio() {
        return porcentajeBeneficio;
    }


    /**
     * Sets the porcentajeBeneficio value for this ClienteDTO.
     * 
     * @param porcentajeBeneficio
     */
    public void setPorcentajeBeneficio(int porcentajeBeneficio) {
        this.porcentajeBeneficio = porcentajeBeneficio;
    }


    /**
     * Gets the provincia value for this ClienteDTO.
     * 
     * @return provincia
     */
    public java.lang.String getProvincia() {
        return provincia;
    }


    /**
     * Sets the provincia value for this ClienteDTO.
     * 
     * @param provincia
     */
    public void setProvincia(java.lang.String provincia) {
        this.provincia = provincia;
    }


    /**
     * Gets the representanteLegal value for this ClienteDTO.
     * 
     * @return representanteLegal
     */
    public com.tandi.servicios.DTOs.ClienteDTO getRepresentanteLegal() {
        return representanteLegal;
    }


    /**
     * Sets the representanteLegal value for this ClienteDTO.
     * 
     * @param representanteLegal
     */
    public void setRepresentanteLegal(com.tandi.servicios.DTOs.ClienteDTO representanteLegal) {
        this.representanteLegal = representanteLegal;
    }


    /**
     * Gets the sectorEmpresa value for this ClienteDTO.
     * 
     * @return sectorEmpresa
     */
    public java.lang.String getSectorEmpresa() {
        return sectorEmpresa;
    }


    /**
     * Sets the sectorEmpresa value for this ClienteDTO.
     * 
     * @param sectorEmpresa
     */
    public void setSectorEmpresa(java.lang.String sectorEmpresa) {
        this.sectorEmpresa = sectorEmpresa;
    }


    /**
     * Gets the sectorEmpresaId value for this ClienteDTO.
     * 
     * @return sectorEmpresaId
     */
    public java.lang.String getSectorEmpresaId() {
        return sectorEmpresaId;
    }


    /**
     * Sets the sectorEmpresaId value for this ClienteDTO.
     * 
     * @param sectorEmpresaId
     */
    public void setSectorEmpresaId(java.lang.String sectorEmpresaId) {
        this.sectorEmpresaId = sectorEmpresaId;
    }


    /**
     * Gets the socialObject value for this ClienteDTO.
     * 
     * @return socialObject
     */
    public java.lang.String getSocialObject() {
        return socialObject;
    }


    /**
     * Sets the socialObject value for this ClienteDTO.
     * 
     * @param socialObject
     */
    public void setSocialObject(java.lang.String socialObject) {
        this.socialObject = socialObject;
    }


    /**
     * Gets the telefonoCasa value for this ClienteDTO.
     * 
     * @return telefonoCasa
     */
    public java.lang.String getTelefonoCasa() {
        return telefonoCasa;
    }


    /**
     * Sets the telefonoCasa value for this ClienteDTO.
     * 
     * @param telefonoCasa
     */
    public void setTelefonoCasa(java.lang.String telefonoCasa) {
        this.telefonoCasa = telefonoCasa;
    }


    /**
     * Gets the telefonoCelular value for this ClienteDTO.
     * 
     * @return telefonoCelular
     */
    public java.lang.String getTelefonoCelular() {
        return telefonoCelular;
    }


    /**
     * Sets the telefonoCelular value for this ClienteDTO.
     * 
     * @param telefonoCelular
     */
    public void setTelefonoCelular(java.lang.String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }


    /**
     * Gets the telefonoEmpresa value for this ClienteDTO.
     * 
     * @return telefonoEmpresa
     */
    public java.lang.String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }


    /**
     * Sets the telefonoEmpresa value for this ClienteDTO.
     * 
     * @param telefonoEmpresa
     */
    public void setTelefonoEmpresa(java.lang.String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }


    /**
     * Gets the telefonoOficina value for this ClienteDTO.
     * 
     * @return telefonoOficina
     */
    public java.lang.String getTelefonoOficina() {
        return telefonoOficina;
    }


    /**
     * Sets the telefonoOficina value for this ClienteDTO.
     * 
     * @param telefonoOficina
     */
    public void setTelefonoOficina(java.lang.String telefonoOficina) {
        this.telefonoOficina = telefonoOficina;
    }


    /**
     * Gets the tipoEmpleado value for this ClienteDTO.
     * 
     * @return tipoEmpleado
     */
    public java.lang.String getTipoEmpleado() {
        return tipoEmpleado;
    }


    /**
     * Sets the tipoEmpleado value for this ClienteDTO.
     * 
     * @param tipoEmpleado
     */
    public void setTipoEmpleado(java.lang.String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }


    /**
     * Gets the tipoEmpresaId value for this ClienteDTO.
     * 
     * @return tipoEmpresaId
     */
    public java.lang.String getTipoEmpresaId() {
        return tipoEmpresaId;
    }


    /**
     * Sets the tipoEmpresaId value for this ClienteDTO.
     * 
     * @param tipoEmpresaId
     */
    public void setTipoEmpresaId(java.lang.String tipoEmpresaId) {
        this.tipoEmpresaId = tipoEmpresaId;
    }


    /**
     * Gets the tipoEntidad value for this ClienteDTO.
     * 
     * @return tipoEntidad
     */
    public java.lang.String getTipoEntidad() {
        return tipoEntidad;
    }


    /**
     * Sets the tipoEntidad value for this ClienteDTO.
     * 
     * @param tipoEntidad
     */
    public void setTipoEntidad(java.lang.String tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }


    /**
     * Gets the tipoIdentificacion value for this ClienteDTO.
     * 
     * @return tipoIdentificacion
     */
    public java.lang.String getTipoIdentificacion() {
        return tipoIdentificacion;
    }


    /**
     * Sets the tipoIdentificacion value for this ClienteDTO.
     * 
     * @param tipoIdentificacion
     */
    public void setTipoIdentificacion(java.lang.String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }


    /**
     * Gets the tituloAcademico value for this ClienteDTO.
     * 
     * @return tituloAcademico
     */
    public java.lang.String getTituloAcademico() {
        return tituloAcademico;
    }


    /**
     * Sets the tituloAcademico value for this ClienteDTO.
     * 
     * @param tituloAcademico
     */
    public void setTituloAcademico(java.lang.String tituloAcademico) {
        this.tituloAcademico = tituloAcademico;
    }


    /**
     * Gets the totalEgresos value for this ClienteDTO.
     * 
     * @return totalEgresos
     */
    public double getTotalEgresos() {
        return totalEgresos;
    }


    /**
     * Sets the totalEgresos value for this ClienteDTO.
     * 
     * @param totalEgresos
     */
    public void setTotalEgresos(double totalEgresos) {
        this.totalEgresos = totalEgresos;
    }


    /**
     * Gets the webSite value for this ClienteDTO.
     * 
     * @return webSite
     */
    public java.lang.String getWebSite() {
        return webSite;
    }


    /**
     * Sets the webSite value for this ClienteDTO.
     * 
     * @param webSite
     */
    public void setWebSite(java.lang.String webSite) {
        this.webSite = webSite;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ClienteDTO)) return false;
        ClienteDTO other = (ClienteDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.actividadEconomica==null && other.getActividadEconomica()==null) || 
             (this.actividadEconomica!=null &&
              this.actividadEconomica.equals(other.getActividadEconomica()))) &&
            ((this.actividadEconomicaId==null && other.getActividadEconomicaId()==null) || 
             (this.actividadEconomicaId!=null &&
              this.actividadEconomicaId.equals(other.getActividadEconomicaId()))) &&
            ((this.apellidos==null && other.getApellidos()==null) || 
             (this.apellidos!=null &&
              this.apellidos.equals(other.getApellidos()))) &&
            ((this.apellidosConyuge==null && other.getApellidosConyuge()==null) || 
             (this.apellidosConyuge!=null &&
              this.apellidosConyuge.equals(other.getApellidosConyuge()))) &&
            ((this.birthPlace==null && other.getBirthPlace()==null) || 
             (this.birthPlace!=null &&
              this.birthPlace.equals(other.getBirthPlace()))) &&
            ((this.canton==null && other.getCanton()==null) || 
             (this.canton!=null &&
              this.canton.equals(other.getCanton()))) &&
            ((this.ciudad==null && other.getCiudad()==null) || 
             (this.ciudad!=null &&
              this.ciudad.equals(other.getCiudad()))) &&
            ((this.correoElectronico==null && other.getCorreoElectronico()==null) || 
             (this.correoElectronico!=null &&
              this.correoElectronico.equals(other.getCorreoElectronico()))) &&
            ((this.direccion==null && other.getDireccion()==null) || 
             (this.direccion!=null &&
              this.direccion.equals(other.getDireccion()))) &&
            ((this.direcciones==null && other.getDirecciones()==null) || 
             (this.direcciones!=null &&
              java.util.Arrays.equals(this.direcciones, other.getDirecciones()))) &&
            ((this.employeeType==null && other.getEmployeeType()==null) || 
             (this.employeeType!=null &&
              this.employeeType.equals(other.getEmployeeType()))) &&
            ((this.entidadId==null && other.getEntidadId()==null) || 
             (this.entidadId!=null &&
              this.entidadId.equals(other.getEntidadId()))) &&
            this.esEmpresa == other.isEsEmpresa() &&
            this.esPrimaExcenta == other.isEsPrimaExcenta() &&
            ((this.faxEmpresa==null && other.getFaxEmpresa()==null) || 
             (this.faxEmpresa!=null &&
              this.faxEmpresa.equals(other.getFaxEmpresa()))) &&
            ((this.fechaNacimiento==null && other.getFechaNacimiento()==null) || 
             (this.fechaNacimiento!=null &&
              this.fechaNacimiento.equals(other.getFechaNacimiento()))) &&
            ((this.fuente==null && other.getFuente()==null) || 
             (this.fuente!=null &&
              this.fuente.equals(other.getFuente()))) &&
            ((this.genero==null && other.getGenero()==null) || 
             (this.genero!=null &&
              this.genero.equals(other.getGenero()))) &&
            ((this.grupoEconomicoId==null && other.getGrupoEconomicoId()==null) || 
             (this.grupoEconomicoId!=null &&
              this.grupoEconomicoId.equals(other.getGrupoEconomicoId()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.identificacion==null && other.getIdentificacion()==null) || 
             (this.identificacion!=null &&
              this.identificacion.equals(other.getIdentificacion()))) &&
            this.income == other.getIncome() &&
            this.limiteCredito == other.getLimiteCredito() &&
            ((this.nacionalidad==null && other.getNacionalidad()==null) || 
             (this.nacionalidad!=null &&
              this.nacionalidad.equals(other.getNacionalidad()))) &&
            ((this.nombreCorto==null && other.getNombreCorto()==null) || 
             (this.nombreCorto!=null &&
              this.nombreCorto.equals(other.getNombreCorto()))) &&
            ((this.nombreEmpresa==null && other.getNombreEmpresa()==null) || 
             (this.nombreEmpresa!=null &&
              this.nombreEmpresa.equals(other.getNombreEmpresa()))) &&
            ((this.nombres==null && other.getNombres()==null) || 
             (this.nombres!=null &&
              this.nombres.equals(other.getNombres()))) &&
            ((this.nombresConyuge==null && other.getNombresConyuge()==null) || 
             (this.nombresConyuge!=null &&
              this.nombresConyuge.equals(other.getNombresConyuge()))) &&
            this.otrosIngresos == other.getOtrosIngresos() &&
            ((this.pais==null && other.getPais()==null) || 
             (this.pais!=null &&
              this.pais.equals(other.getPais()))) &&
            ((this.parroquia==null && other.getParroquia()==null) || 
             (this.parroquia!=null &&
              this.parroquia.equals(other.getParroquia()))) &&
            this.porcentajeBeneficio == other.getPorcentajeBeneficio() &&
            ((this.provincia==null && other.getProvincia()==null) || 
             (this.provincia!=null &&
              this.provincia.equals(other.getProvincia()))) &&
            ((this.representanteLegal==null && other.getRepresentanteLegal()==null) || 
             (this.representanteLegal!=null &&
              this.representanteLegal.equals(other.getRepresentanteLegal()))) &&
            ((this.sectorEmpresa==null && other.getSectorEmpresa()==null) || 
             (this.sectorEmpresa!=null &&
              this.sectorEmpresa.equals(other.getSectorEmpresa()))) &&
            ((this.sectorEmpresaId==null && other.getSectorEmpresaId()==null) || 
             (this.sectorEmpresaId!=null &&
              this.sectorEmpresaId.equals(other.getSectorEmpresaId()))) &&
            ((this.socialObject==null && other.getSocialObject()==null) || 
             (this.socialObject!=null &&
              this.socialObject.equals(other.getSocialObject()))) &&
            ((this.telefonoCasa==null && other.getTelefonoCasa()==null) || 
             (this.telefonoCasa!=null &&
              this.telefonoCasa.equals(other.getTelefonoCasa()))) &&
            ((this.telefonoCelular==null && other.getTelefonoCelular()==null) || 
             (this.telefonoCelular!=null &&
              this.telefonoCelular.equals(other.getTelefonoCelular()))) &&
            ((this.telefonoEmpresa==null && other.getTelefonoEmpresa()==null) || 
             (this.telefonoEmpresa!=null &&
              this.telefonoEmpresa.equals(other.getTelefonoEmpresa()))) &&
            ((this.telefonoOficina==null && other.getTelefonoOficina()==null) || 
             (this.telefonoOficina!=null &&
              this.telefonoOficina.equals(other.getTelefonoOficina()))) &&
            ((this.tipoEmpleado==null && other.getTipoEmpleado()==null) || 
             (this.tipoEmpleado!=null &&
              this.tipoEmpleado.equals(other.getTipoEmpleado()))) &&
            ((this.tipoEmpresaId==null && other.getTipoEmpresaId()==null) || 
             (this.tipoEmpresaId!=null &&
              this.tipoEmpresaId.equals(other.getTipoEmpresaId()))) &&
            ((this.tipoEntidad==null && other.getTipoEntidad()==null) || 
             (this.tipoEntidad!=null &&
              this.tipoEntidad.equals(other.getTipoEntidad()))) &&
            ((this.tipoIdentificacion==null && other.getTipoIdentificacion()==null) || 
             (this.tipoIdentificacion!=null &&
              this.tipoIdentificacion.equals(other.getTipoIdentificacion()))) &&
            ((this.tituloAcademico==null && other.getTituloAcademico()==null) || 
             (this.tituloAcademico!=null &&
              this.tituloAcademico.equals(other.getTituloAcademico()))) &&
            this.totalEgresos == other.getTotalEgresos() &&
            ((this.webSite==null && other.getWebSite()==null) || 
             (this.webSite!=null &&
              this.webSite.equals(other.getWebSite())));
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
        if (getActividadEconomica() != null) {
            _hashCode += getActividadEconomica().hashCode();
        }
        if (getActividadEconomicaId() != null) {
            _hashCode += getActividadEconomicaId().hashCode();
        }
        if (getApellidos() != null) {
            _hashCode += getApellidos().hashCode();
        }
        if (getApellidosConyuge() != null) {
            _hashCode += getApellidosConyuge().hashCode();
        }
        if (getBirthPlace() != null) {
            _hashCode += getBirthPlace().hashCode();
        }
        if (getCanton() != null) {
            _hashCode += getCanton().hashCode();
        }
        if (getCiudad() != null) {
            _hashCode += getCiudad().hashCode();
        }
        if (getCorreoElectronico() != null) {
            _hashCode += getCorreoElectronico().hashCode();
        }
        if (getDireccion() != null) {
            _hashCode += getDireccion().hashCode();
        }
        if (getDirecciones() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDirecciones());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDirecciones(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getEmployeeType() != null) {
            _hashCode += getEmployeeType().hashCode();
        }
        if (getEntidadId() != null) {
            _hashCode += getEntidadId().hashCode();
        }
        _hashCode += (isEsEmpresa() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isEsPrimaExcenta() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getFaxEmpresa() != null) {
            _hashCode += getFaxEmpresa().hashCode();
        }
        if (getFechaNacimiento() != null) {
            _hashCode += getFechaNacimiento().hashCode();
        }
        if (getFuente() != null) {
            _hashCode += getFuente().hashCode();
        }
        if (getGenero() != null) {
            _hashCode += getGenero().hashCode();
        }
        if (getGrupoEconomicoId() != null) {
            _hashCode += getGrupoEconomicoId().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getIdentificacion() != null) {
            _hashCode += getIdentificacion().hashCode();
        }
        _hashCode += new Double(getIncome()).hashCode();
        _hashCode += new Double(getLimiteCredito()).hashCode();
        if (getNacionalidad() != null) {
            _hashCode += getNacionalidad().hashCode();
        }
        if (getNombreCorto() != null) {
            _hashCode += getNombreCorto().hashCode();
        }
        if (getNombreEmpresa() != null) {
            _hashCode += getNombreEmpresa().hashCode();
        }
        if (getNombres() != null) {
            _hashCode += getNombres().hashCode();
        }
        if (getNombresConyuge() != null) {
            _hashCode += getNombresConyuge().hashCode();
        }
        _hashCode += new Double(getOtrosIngresos()).hashCode();
        if (getPais() != null) {
            _hashCode += getPais().hashCode();
        }
        if (getParroquia() != null) {
            _hashCode += getParroquia().hashCode();
        }
        _hashCode += getPorcentajeBeneficio();
        if (getProvincia() != null) {
            _hashCode += getProvincia().hashCode();
        }
        if (getRepresentanteLegal() != null) {
            _hashCode += getRepresentanteLegal().hashCode();
        }
        if (getSectorEmpresa() != null) {
            _hashCode += getSectorEmpresa().hashCode();
        }
        if (getSectorEmpresaId() != null) {
            _hashCode += getSectorEmpresaId().hashCode();
        }
        if (getSocialObject() != null) {
            _hashCode += getSocialObject().hashCode();
        }
        if (getTelefonoCasa() != null) {
            _hashCode += getTelefonoCasa().hashCode();
        }
        if (getTelefonoCelular() != null) {
            _hashCode += getTelefonoCelular().hashCode();
        }
        if (getTelefonoEmpresa() != null) {
            _hashCode += getTelefonoEmpresa().hashCode();
        }
        if (getTelefonoOficina() != null) {
            _hashCode += getTelefonoOficina().hashCode();
        }
        if (getTipoEmpleado() != null) {
            _hashCode += getTipoEmpleado().hashCode();
        }
        if (getTipoEmpresaId() != null) {
            _hashCode += getTipoEmpresaId().hashCode();
        }
        if (getTipoEntidad() != null) {
            _hashCode += getTipoEntidad().hashCode();
        }
        if (getTipoIdentificacion() != null) {
            _hashCode += getTipoIdentificacion().hashCode();
        }
        if (getTituloAcademico() != null) {
            _hashCode += getTituloAcademico().hashCode();
        }
        _hashCode += new Double(getTotalEgresos()).hashCode();
        if (getWebSite() != null) {
            _hashCode += getWebSite().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ClienteDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ClienteDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actividadEconomica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "actividadEconomica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actividadEconomicaId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "actividadEconomicaId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apellidos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "apellidos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apellidosConyuge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "apellidosConyuge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birthPlace");
        elemField.setXmlName(new javax.xml.namespace.QName("", "birthPlace"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("canton");
        elemField.setXmlName(new javax.xml.namespace.QName("", "canton"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ciudad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ciudad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("correoElectronico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "correoElectronico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("direccion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "direccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("direcciones");
        elemField.setXmlName(new javax.xml.namespace.QName("", "direcciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "DireccionDTO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("employeeType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "employeeType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entidadId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "entidadId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esEmpresa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "esEmpresa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esPrimaExcenta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "esPrimaExcenta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("faxEmpresa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "faxEmpresa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaNacimiento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaNacimiento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fuente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fuente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("genero");
        elemField.setXmlName(new javax.xml.namespace.QName("", "genero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grupoEconomicoId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "grupoEconomicoId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("income");
        elemField.setXmlName(new javax.xml.namespace.QName("", "income"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limiteCredito");
        elemField.setXmlName(new javax.xml.namespace.QName("", "limiteCredito"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nacionalidad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nacionalidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreCorto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombreCorto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreEmpresa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombreEmpresa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombres");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombres"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombresConyuge");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombresConyuge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("otrosIngresos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "otrosIngresos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pais");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pais"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parroquia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "parroquia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("porcentajeBeneficio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "porcentajeBeneficio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("provincia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "provincia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("representanteLegal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "representanteLegal"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:DTOs.servicios.tandi.com", "ClienteDTO"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sectorEmpresa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sectorEmpresa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sectorEmpresaId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sectorEmpresaId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("socialObject");
        elemField.setXmlName(new javax.xml.namespace.QName("", "socialObject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefonoCasa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefonoCasa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefonoCelular");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefonoCelular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefonoEmpresa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefonoEmpresa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("telefonoOficina");
        elemField.setXmlName(new javax.xml.namespace.QName("", "telefonoOficina"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoEmpleado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoEmpleado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoEmpresaId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoEmpresaId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoEntidad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoEntidad"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoIdentificacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoIdentificacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tituloAcademico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tituloAcademico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalEgresos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "totalEgresos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("webSite");
        elemField.setXmlName(new javax.xml.namespace.QName("", "webSite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
