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
	
	//curl -X GET http://localhost:8080/BananaTechiesV3/rest/Proyectos/2
	@GET
	@Path("/{uid}") //Identificador Usuario
	@Produces(MediaType.APPLICATION_JSON)
	public Object listaProyectoResponsables (@PathParam("uid") int uid);
	//Mostrar lista de Proyectos de un Responsable

	@POST
	@Path("/{uid}") 
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Mensaje upDateProyectoLista (@PathParam("uid") int uid, JSONObject modificadoProyecto) throws JSONException;
	//Actualizar un Proyecto de la lista   (NO LO USAREMOS)

	@PUT
	@Path("/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Mensaje addProyectoLista (@PathParam("uid") int uid, JSONObject nuevoProyecto) throws JSONException;
	//Añadir un Proyecto en liata          (NO LO USAREMOS)

	@DELETE
	@Path("/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Mensaje borrarProyectoLista (@PathParam("uid") int uid) throws JSONException;
	//Borrar un proyecto de lista
	
}
