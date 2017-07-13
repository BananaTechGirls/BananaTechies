package com.BananaTechies.resources;



import java.util.ArrayList;
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
import com.sun.jersey.api.client.ClientResponse.Status;

@Path("/usuarios")
public class UsuarioREST extends JSONService implements UsuarioAPI {
	private static List<Usuario> misUsuarios;//actua como un singleton todas las llamadas responden a este atributo
	static{misUsuarios=new ArrayList<Usuario>();}

	@Override
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserList(@HeaderParam("token") String token) {
		
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse=null;

		if (userEmail == null) {
			StatusMensaje statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),"Access Denied for this functionality !!!");
			mResponse=Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
		}else  mResponse=Response.status(200).entity(misUsuarios).build();
		
		return mResponse;
	}
	
	@Override
	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertUsuario(Usuario nuevoUser,@HeaderParam("token") String token){
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse=null;
		if (userEmail == null) {
			StatusMensaje statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),"Access Denied for this functionality !!!");
			mResponse=Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
		}else  {
			this.misUsuarios.add(nuevoUser);
			StatusMensaje statusMensaje = new StatusMensaje(Status.CREATED.getStatusCode(),"Usuario Añadido!!!");
			mResponse=Response.status(200).entity(statusMensaje).build();
		}
		return mResponse;

	}
	
	@Override
	@Path("/{uid}")//ruta
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(@PathParam("uid") int uid,@HeaderParam("token") String token) {//implementacion puedo poner parametro
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse=null;

		if (userEmail == null) {
			StatusMensaje statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),"Access Denied for this functionality !!!");
			mResponse=Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
		}else  {
		Usuario unUsuario = new Usuario();
		for (Usuario user : misUsuarios) {
			if(user.getUid()==uid){
				unUsuario=user;
				break;
			}
		}
		mResponse=Response.status(200).entity(unUsuario).build();
	}
	
	return mResponse;
	}
	
	
	
	@Override
	@Path("/{uid}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUsuario(@PathParam("uid") int uid,@HeaderParam("token") String token) {
		
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse=null;

		if (userEmail == null) {
			StatusMensaje statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),"Access Denied for this functionality !!!");
			mResponse=Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
		}else  {
			for (Usuario user : misUsuarios) {
				if(user.getUid()==uid){
					misUsuarios.remove(user);
					break;
				}
			}
			StatusMensaje statusMensaje = new StatusMensaje(Status.CREATED.getStatusCode(),"Usuario borrado!!!");
			mResponse=Response.status(200).entity(statusMensaje).build();
		}
		
		return mResponse;
		
	}
	


	@Override
	@Path("/{uid}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUsuario(@PathParam("uid") int uid,Usuario aUsuario,@HeaderParam("token") String token) {
		
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse=null;

		if (userEmail == null) {
			StatusMensaje statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),"Access Denied for this functionality !!!");
			mResponse=Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
		}else  {
			for (Usuario user : misUsuarios) {
				if(user.getUid()==uid){
					misUsuarios.remove(user);
					misUsuarios.add(aUsuario);
					break;
				}
			}
			StatusMensaje statusMensaje = new StatusMensaje(Status.CREATED.getStatusCode(),"Usuario modificado!!!");
			mResponse=Response.status(200).entity(statusMensaje).build();
		}
		
		return mResponse;
	}
}
