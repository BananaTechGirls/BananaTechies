package com.BananaTechies.resources;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.BananaTechies.db.DAOFactory;
import com.BananaTechies.db.ProyectoDAO;
import com.BananaTechies.db.TareaDAO;
import com.BananaTechies.db.UsuarioDAO;
import com.BananaTechies.models.StatusMensaje;
import com.BananaTechies.models.Proyecto;
import com.BananaTechies.models.Tarea;
import com.BananaTechies.models.Usuario;
import com.BananaTechies.resources.ProyectosAPI;
//import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.client.ClientResponse.Status;

//import com.sun.jersey.api.client.ClientResponse.Status;

@Path("/Tarea")
public class TareaREST extends JSONService implements TareaAPI {
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
		StatusMensaje resp = new StatusMensaje(0, "");
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
	public Response upDateTarea(@PathParam("tid") int tid, Tarea UpdateTarea, @HeaderParam("token") String token) throws JsonMappingException, IOException {
		StatusMensaje respuesta = null;
		try {
			TareaDAO tDAO = (TareaDAO) DAOFactory.getInstance().getDAO(UpdateTarea);
			if (tDAO.updateTarea(tid, UpdateTarea)) {
				respuesta = new StatusMensaje(0, "Tarea actualizada");
			} else {
				respuesta = new StatusMensaje(0, "Operacion sin actualizar");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return Response.status(200).entity(respuesta).build();
	}

	@Override
	@Path("/{tid}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response borrarTarea(@PathParam("tid") int tid, @HeaderParam("token") String token)
			throws JsonMappingException, IOException {
		String userEmail = this.getUserEmailFromToken(token);
		StatusMensaje statusMensaje = null;
		Response mResponse = null;
		if (userEmail == null) {
			statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),
					"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
			return mResponse;
		}

		try {
			// Borara tarea que nos pid?
			Tarea laTarea = new Tarea();
			TareaDAO TareaDAO = (TareaDAO) DAOFactory.getInstance().getDAO(laTarea);
			if (!(TareaDAO.delTarea(tid))) {
				statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),	"Access Denied for this functionality !!!");
				mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
				return mResponse;
			}
		} catch (Exception e) {
			StatusMensaje StatusMensaje = new StatusMensaje(Status.CREATED.getStatusCode(), "Tarea borrada!!!");
			mResponse = Response.status(200).entity(StatusMensaje).build();
		}

		return mResponse;
	}
}
