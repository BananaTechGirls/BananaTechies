package com.BananaTechies.resources;
import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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
import com.fasterxml.jackson.databind.JsonMappingException;


public interface TareaAPI {
	

	@GET
	@Path("/{tid}") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response TareaID( @PathParam("tid")int tid ,@HeaderParam("token") String token) throws JSONException, JsonMappingException, IOException;
	//Mostrar Detalles de la Tarea


	@PUT
	@Path("/{tid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response upDateTarea(@PathParam("tid") int tid, Tarea UpdateTarea, @HeaderParam("token") String token) throws JsonMappingException, IOException;
	//Actualizar/Modificar una tarea

	@DELETE
	@Path("/{tid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response borrarTarea (@PathParam("tid")int tid, @HeaderParam("token") String token) throws JsonMappingException, IOException;
	//Borrar un tarea 
	

	
}
