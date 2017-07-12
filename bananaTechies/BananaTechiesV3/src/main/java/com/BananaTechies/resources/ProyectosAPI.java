package com.BananaTechies.resources;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.BananaTechies.models.Mensaje;
import com.BananaTechies.models.Proyecto;


public interface ProyectosAPI {
	

	@GET
	@Path("/") 
	@Produces(MediaType.APPLICATION_JSON)
	public Object listaProyectoResponsables ();
	//Mostrar lista de Proyectos de un Responsable por Token
	
	@POST
	@Path("/") 
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Mensaje addProyectoLista (JSONObject newProyecto) throws JSONException;
	//Añadir un nuevo Proyecto de la lista de un Responsable por Token

	@GET
	@Path("/{pid}") //Identificador Proyecto
	@Produces(MediaType.APPLICATION_JSON)
	public Proyecto ProyectoID (@PathParam("pid") int pid);
	//Mostrar Detalles de Proyecto por el identificador de la lista proyectos

	@PUT
	@Path("/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Mensaje upDateProyectoLista (@PathParam("pid") int pid, JSONObject UpdateProyecto) throws JSONException;
	//Actualizar/Modificar un Proyecto en liata de un Responsable por Token

	@POST
	@Path("/{pid}") 
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Mensaje addTareaListaP (@PathParam("pid") int pid, JSONObject nuevaTarea) throws JSONException;
	//Añadir una tarea aun Proyecto de la lista de un Responsable por Token

	@DELETE
	@Path("/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Mensaje borrarProyectoLista (@PathParam("pid") int pid) throws JSONException;
	//Borrar un proyecto de lista de un Responsable por Token
	
	@GET
	@Path("/{pid}/tareas") //Identificador Tarea
	@Produces(MediaType.APPLICATION_JSON)
	public Object listaTareasProyecto (@PathParam("pid") int pid);
	//Mostrar lista de Tareas de un proyecto
	
}
