package com.BananaTechies.resources;

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

import com.BananaTechies.models.StatusMensaje;
import com.BananaTechies.models.Usuario;

public interface UsuarioAPI  {
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserList(@HeaderParam("token") String token);
	
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertUsuario(Usuario nuevoUser,@HeaderParam("token") String token);
	
	@Path("/{uid}")//ruta
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("uid") int uid,@HeaderParam("token") String token); 
							
	
	@Path("/{uid}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUsuario(@PathParam("uid") int uid,Usuario aUsuario,@HeaderParam("token") String token);
	
	
	@Path("/{uid}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUsuario(@PathParam("uid") int uid,@HeaderParam("token") String token);
}
