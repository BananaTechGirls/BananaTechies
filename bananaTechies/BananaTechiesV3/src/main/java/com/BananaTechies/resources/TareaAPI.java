package com.BananaTechies.resources;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.BananaTechies.models.StatusMensaje;
import com.BananaTechies.models.Tarea;


public interface TareaAPI {
	

	@GET
	@Path("/{tid}") 
	@Produces(MediaType.APPLICATION_JSON)
	public Object TareaID (@PathParam("tid") int tid);
	//Mostrar Detalles de la Tarea


	@PUT
	@Path("/{tid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)

	public StatusMensaje upDateTarea (@PathParam("tid") int tid, Tarea UpdateTarea) throws JSONException;

	//Actualizar/Modificar una tarea

	@DELETE
	@Path("/{tid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response borrarTarea (@PathParam("tid") int tid) throws JSONException;
	//Borrar un tarea 
	

	
}
