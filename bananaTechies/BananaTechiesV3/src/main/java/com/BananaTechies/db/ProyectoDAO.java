package com.BananaTechies.db;

import java.util.List;

import com.BananaTechies.models.Proyecto;
import com.BananaTechies.models.Usuario;

public abstract class ProyectoDAO extends DAO {
	public abstract Proyecto getProyecto(int idp, Usuario user);
	public abstract List<Proyecto> getUserProyecto(Usuario user);
	public abstract List<Proyecto> getProyectosList();
	public abstract boolean delProyecto(int idp);
	public abstract boolean insertProyecto(Proyecto proyecto) throws Exception;
	public abstract boolean updateProyecto(Proyecto proyecto);
}
