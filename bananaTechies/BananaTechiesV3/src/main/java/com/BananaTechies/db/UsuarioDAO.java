package com.BananaTechies.db;

import com.BananaTechies.models.Usuario;

public abstract class UsuarioDAO extends DAO {
	public abstract Usuario getUsuario(String email, String password);
	public abstract Usuario getUsuario(int uid);
	public abstract boolean delUsuario(int uid);
	public abstract boolean insertUsuario(Usuario usuario);
	public abstract boolean updateUsuario(Usuario usuario);
}
