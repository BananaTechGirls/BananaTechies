package com.BananaTechies.resources;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.BananaTechies.db.DAOFactory;
import com.BananaTechies.db.ProyectoDAO;
import com.BananaTechies.db.TareaDAO;
import com.BananaTechies.models.Mensaje;
import com.BananaTechies.models.Proyecto;
import com.BananaTechies.models.Tarea;
import com.BananaTechies.models.Tarea;
import com.BananaTechies.resources.ProyectosAPI;
import com.netmind.models.User;

@Path("/Tarea")
public class TareaREST implements TareaAPI {
	private static Tarea miTarea;

	static {
		TareaREST.miTarea = new Tarea();
		// MOCKEAR LOS DATOS ENTRADA EN VEZ DE LA BBDD < MYSQL >

	}

	@Override
	@GET
	@Path("/{tid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object TareaID(int tid) {
		Mensaje resp = new Mensaje(0, "");
		try {
			// obtener el objeto Tarea completo
			Tarea laTarea = new Tarea();
			TareaDAO tareaDAO = (TareaDAO) DAOFactory.getInstance().getDAO(laTarea);
			laTarea = tareaDAO.getTarea(tid);
			// existe tarea tid
			if (laTarea != null) {
				TareaREST.miTarea = laTarea;
				return TareaREST.miTarea;
			} else {
				throw new RuntimeException("- La tarea (" + tid + ") es desconocida.");
			}
		} catch (Exception e) {
			resp.setCuerpo(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto TAREA.\nLease API");
			return resp;
		}
	}

	
	@Override
	@PUT
	@Path("/{tid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Mensaje upDateTarea (@PathParam("tid") int tid, Tarea UpdateTarea) throws JSONException {
		Mensaje respuesta=null;
		try {			
			TareaDAO tDAO=(TareaDAO) DAOFactory.getInstance().getDAO(UpdateTarea);
			if (tDAO.updateTarea(tid, UpdateTarea)) {
				respuesta= new Mensaje(0,"Tarea actualizada");
			} else {
				respuesta= new Mensaje(0,"Operacion sin actualizar");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}				
		return respuesta;
	}


	

	
	
	@Override
	public Mensaje borrarTarea(int tid) throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}

}
