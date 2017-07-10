package com.BananaTechies.resources;
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


public interface ProyectoAPI {
	
	@GET
	@Path("/{pid}") //Identificador Proyecto
	@Produces(MediaType.APPLICATION_JSON)
	public Proyecto listaProyectoResponsables (@PathParam("pid") int pid);
	//Mostrar Detalles de Proyecto por el identificador

	@POST
	@Path("/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Mensaje upDateProyectoLista (@PathParam("pid") int pid, JSONObject modificadoProyecto) throws JSONException;
	//Actualizar un Proyecto

	@PUT
	@Path("/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Mensaje addProyectoLista (@PathParam("pid") int pid, JSONObject nuevoProyecto) throws JSONException;
	//Añadir un Proyecto

	@DELETE
	@Path("/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Mensaje borrarProyectoLista (@PathParam("pid") int pid) throws JSONException;
	//Borrar un proyecto de lista          (NO LO USAREMOS)
	
}
