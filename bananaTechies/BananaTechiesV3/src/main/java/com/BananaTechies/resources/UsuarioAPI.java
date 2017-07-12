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

import com.BananaTechies.models.Mensaje;
import com.BananaTechies.models.Usuario;

public interface UsuarioAPI {
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getUserList();
	
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje insertUsuario(Usuario nuevoUser);
	
	@Path("/{uid}")//ruta
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUser(@PathParam("uid") 
							int uid,
							String email,
							String nombre,
							String apellido);
	
	@Path("{uid}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateUsuario(@PathParam("uid") int uid);
	
	@Path("{uid}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void removeUser(@PathParam("uid") int uid);
}
