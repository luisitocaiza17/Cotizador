package com.qbe.cotizador.dao.producto.agricola;

public class User {
    private int UserID;
    private String Name;
    private String UserName;
    private String Password;
    private String CIUser;
    private String LastName;
    private String PuntoVentaId;
    private boolean admin;
    private String Agencia;

    public int getUserID() {
        return this.UserID;
    }

    public void setUserID(int userID) {
        this.UserID = userID;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getUserName() {
        return this.UserName;
    }

    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public String getPassword() {
        return this.Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getCIUser() {
        return this.CIUser;
    }

    public void setCIUser(String cIUser) {
        this.CIUser = cIUser;
    }

    public String getLastName() {
        return this.LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public String getPuntoVentaId() {
        return this.PuntoVentaId;
    }

    public void setPuntoVentaId(String puntoVentaId) {
        this.PuntoVentaId = puntoVentaId;
    }

    public boolean isAdmin() {
        return this.admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getAgencia() {
        return this.Agencia;
    }

    public void setAgencia(String agencia) {
        this.Agencia = agencia;
    }
}