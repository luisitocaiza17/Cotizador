package com.qbe.cotizador.servlets.producto.agricola;

import java.util.ArrayList;
import java.util.List;

import com.qbe.cotizador.dao.producto.agricola.User;

public class TransportDataUser {
	private List<User> Users = new ArrayList<User>();

	public List<User> getUsers() {
		return Users;
	}

	public void setUsers(List<User> users) {
		Users = users;
	}
		
}
