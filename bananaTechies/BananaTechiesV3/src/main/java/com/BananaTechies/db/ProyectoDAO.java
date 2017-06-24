package com.BananaTechies.db;

import java.util.List;

import com.BananaTechies.models.Proyecto;

public abstract class ProyectoDAO extends DAO {
	public abstract Proyecto getProyecto(int idp);
	public abstract List<Proyecto> getUserProyecto(int uid);
	public abstract List<Proyecto> getProyectosList();
	public abstract boolean delProyecto(int idp);
	public abstract boolean insertProyecto(Proyecto proyecto);
	public abstract boolean updateProyecto(Proyecto proyecto);
}
