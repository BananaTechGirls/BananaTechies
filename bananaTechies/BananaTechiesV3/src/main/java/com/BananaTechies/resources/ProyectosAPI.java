package com.BananaTechies.resources;

import java.io.IOException;
import java.util.List;

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

import com.BananaTechies.models.StatusMensaje;
import com.BananaTechies.models.Proyecto;


public interface ProyectosAPI {
	

	@GET
	@Path("/") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaProyectoResponsables(@HeaderParam("token") String token) throws JsonMappingException, IOException;
	//Mostrar lista de Proyectos de un Responsable por Token
	
	@POST
	@Path("/") 
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public StatusMensaje addProyectoLista (Proyecto newProyecto)  throws JSONException, JsonMappingException, IOException; 
	//Añadir un nuevo Proyecto de la lista de un Responsable por Token

	@GET
	@Path("/{pid}") //Identificador Proyecto
	@Produces(MediaType.APPLICATION_JSON)
	public Response ProyectoID(@HeaderParam("token") String token, @PathParam("pid") int pid)
			throws JSONException, JsonMappingException, IOException;
	//Mostrar Detalles de Proyecto por el identificador de la lista proyectos

	@PUT
	@Path("/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public StatusMensaje upDateProyectoLista (@PathParam("pid") int pid, JSONObject UpdateProyecto) throws JSONException, JsonMappingException, IOException;
	//Actualizar/Modificar un Proyecto en liata de un Responsable por Token

	@POST
	@Path("/{pid}") 
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public StatusMensaje addTareaListaP (@PathParam("pid") int pid, JSONObject nuevaTarea) throws JSONException, JsonMappingException, IOException;
	//Añadir una tarea aun Proyecto de la lista de un Responsable por Token

	@DELETE
	@Path("/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public StatusMensaje borrarProyectoLista (@PathParam("pid") int pid) throws JSONException, JsonMappingException, IOException;
	//Borrar un proyecto de lista de un Responsable por Token
	
	@GET
	@Path("/{pid}/tareas") //Identificador Tarea
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaTareasProyecto (@PathParam("pid") int pid) throws JSONException, JsonMappingException, IOException;

	//Mostrar lista de Tareas de un proyecto
	
}
