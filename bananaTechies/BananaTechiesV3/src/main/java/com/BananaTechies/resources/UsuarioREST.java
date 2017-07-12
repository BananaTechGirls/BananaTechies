package com.BananaTechies.resources;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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




@Path("/usuarios")
public class UsuarioREST implements UsuarioAPI {
	private static List<Usuario> misUsuarios;//actua como un singleton todas las llamadas responden a este atributo
	
	static{misUsuarios=new ArrayList<Usuario>();}
	
	
	@Override
	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getUserList() {
		
		return this.misUsuarios;
	}
	

	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje insertUsuario(Usuario nuevoUser){

		this.misUsuarios.add(nuevoUser);
		return new Mensaje(1, "Usuario añadido");

	}

	@Path("/{uid}")//ruta
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUser(@PathParam("uid") int uid,String email,String nombre,String apellido) {//implementacion puedo poner parametro

		Usuario unUser=new Usuario();
		unUser.setUid(uid);
		unUser.setEmail(email);
		unUser.setNombre(nombre);
		unUser.setApellido(apellido);
		
		return unUser;
	}
	
	
	
	
	@Path("{uid}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void removeUser(@PathParam("uid") int uid){
		for (Usuario u : misUsuarios) {
			if(u.getUid()==uid){
				misUsuarios.remove(u);
			};
		}
	   
	}
	
	


	@Override
	public void updateUsuario(int uid) {
		// TODO Auto-generated method stub
		
	}
}
