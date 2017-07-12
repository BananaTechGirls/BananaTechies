package com.BananaTechies.db;

import java.util.List;

import com.BananaTechies.models.Proyecto;
import com.BananaTechies.models.Tarea;
import com.BananaTechies.models.Usuario;

public abstract class TareaDAO extends DAO {
	public abstract Tarea getTarea(int idt);
	public abstract List<Tarea> getUserTarea(Usuario user);
	public abstract List<Tarea> getTareasList(Proyecto proy);
	public abstract boolean delTarea(int idt) throws Exception;
	public abstract boolean insertTarea(Tarea tarea);
	public abstract boolean updateTarea(Tarea tarea);
}
	
	


