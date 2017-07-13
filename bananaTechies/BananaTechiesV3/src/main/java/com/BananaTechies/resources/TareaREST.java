package com.BananaTechies.resources;

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

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.BananaTechies.db.DAOFactory;
import com.BananaTechies.db.ProyectoDAO;
import com.BananaTechies.db.TareaDAO;
import com.BananaTechies.models.StatusMensaje;
import com.BananaTechies.models.Proyecto;
import com.BananaTechies.models.Tarea;
import com.BananaTechies.resources.ProyectosAPI;
//import com.sun.jersey.api.client.ClientResponse.Status;

//import com.sun.jersey.api.client.ClientResponse.Status;

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
	public StatusMensaje upDateTarea(@PathParam("tid") int tid, Tarea UpdateTarea) throws JSONException {
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
		return respuesta;
	}

	@Override
	@Path("/{tid}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response borrarTarea(@PathParam("tid")int tid//,@HeaderParam("token") String token
			) throws JSONException {
		//String userEmail = this.getUserEmailFromToken(token);
		Response mResponse=null;
		
		/*try {	
		  	
			TareaDAO tDAO=(TareaDAO) DAOFactory.getInstance().getDAO(delTarea);
			if (tDAO.delTarea(tid)=null) {
				 StatusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),"Access Denied for this functionality !!!");
				mResponse=Response.status(Status.FORBIDDEN.getStatusCode()).entity(StatusMensaje).build();
			}
			}catch(Exception e){
			StatusMensaje StatusMensaje = new StatusMensaje(Status.CREATED.getStatusCode(),"Tarea borrada!!!");
			mResponse=Response.status(200).entity(StatusMensaje).build();
			}
		}
		
		return mResponse;*/
		return null;
	}
	
	/*@Path("/{uid}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUsuario(@PathParam("uid") int uid,@HeaderParam("token") String token) {
		
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse=null;

		if (userEmail == null) {
			StatusMessage statusMessage = new StatusMessage(Status.FORBIDDEN.getStatusCode(),"Access Denied for this functionality !!!");
			mResponse=Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		}else  {
			for (Usuario user : misUsuarios) {
				if(user.getUid()==uid){
					misUsuarios.remove(user);
					break;
				}
			}
			StatusMessage statusMessage = new StatusMessage(Status.CREATED.getStatusCode(),"Usuario borrado!!!");
			mResponse=Response.status(200).entity(statusMessage).build();
		}
		
		return mResponse;
		
	}*/

}
