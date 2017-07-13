package com.BananaTechies.resources;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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

import com.BananaTechies.db.DAOFactory;
import com.BananaTechies.db.ProyectoDAO;
import com.BananaTechies.db.TareaDAO;
import com.BananaTechies.db.UsuarioDAO;
import com.BananaTechies.models.StatusMensaje;
import com.BananaTechies.models.Proyecto;
import com.BananaTechies.models.Tarea;
import com.BananaTechies.models.Usuario;
import com.BananaTechies.resources.ProyectosAPI;
import com.sun.jersey.api.client.ClientResponse.Status;

@Path("/Proyectos")
public class ProyectosREST extends JSONService implements ProyectosAPI {
	private static List<Proyecto> misProyectos;

	static {
		ProyectosREST.misProyectos = new ArrayList<Proyecto>();
	}

	@Override
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaProyectoResponsables(@HeaderParam("token") String token) throws JSONException, JsonMappingException, IOException {

		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse = null;
		StatusMensaje statusMensaje = null;

		if (userEmail == null) {
			statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),
					"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
			return mResponse;
		}

		try {
			// obtener el objeto usuario completo
			Usuario elUsuario = new Usuario();
			UsuarioDAO userDAO = (UsuarioDAO) DAOFactory.getInstance().getDAO(elUsuario);
			elUsuario = userDAO.getUsuario(userEmail, null);
			// existe usuario uid
			if (elUsuario != null) {
				// Otener la lista de los proyectos de ese usuario
				Proyecto elProyecto = new Proyecto();
				ProyectoDAO ProyDAO = (ProyectoDAO) DAOFactory.getInstance().getDAO(elProyecto);
				ProyectosREST.misProyectos = ProyDAO.getUserProyecto(elUsuario);
			} else {
				throw new RuntimeException("- El usuario (" + userEmail + ") es desconocido.");
			}
			mResponse = Response.status(200).entity(ProyectosREST.misProyectos).build();
			return mResponse;
		} catch (Exception e) {
			mResponse = Response.status(200)
					.entity(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto USUARIO.\nLease API").build();
			return mResponse;
		}
	}

	@Override
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProyectoLista(@HeaderParam("token") String token, Proyecto newProyecto) throws JSONException, JsonMappingException, IOException {
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse = null;
		StatusMensaje statusMensaje = null;

		if (userEmail == null) {
			statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),
					"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
			return mResponse;
		}

		/*
		 * try { //obtener el objeto usuario completo Usuario elUsuario= new
		 * Usuario(); // Verificar el nuevo Proyecto a introducir
		 * 
		 * ProyectoDAO ProyDAO = (ProyectoDAO)
		 * DAOFactory.getInstance().getDAO(elProyecto);
		 * 
		 * 
		 * //existe usuario uid if (elUsuario!=null) {
		 * System.out.println("*** < 2 > ***"); elProyecto =
		 * ProyDAO.getProyecto(elProyecto.getIdp(), elUsuario);
		 * System.out.println("*** < 3 > ***"); //existe proyecto pid if
		 * (elProyecto==null) {
		 * resp.setCuerpo(ProyDAO.insertProyecto(elProyecto)?"":""); }else {
		 * throw new RuntimeException("- El proyecto ("+ elProyecto.getIdp()
		 * +") ya exite, imposible a�adirlo."); } }else { throw new
		 * RuntimeException("- El proyecto ("+ uid +") es desconocido."); }
		 * return resp; } catch (Exception e) { resp.setCuerpo(e.getMessage()
		 * +"\n- Formato erroneo en el cuerpo del objeto Proyecto.\nLease API");
		 * return resp; }
		 */
		try {
		} catch (Exception e) {
			mResponse = Response.status(499).entity(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto USUARIO.\nLease API").build();
			return mResponse;
		}
		mResponse = Response.status(200).entity("\n- A�adido un nuevo Proyecto de la lista.").build();
		return mResponse;
	}

	@Override
	@GET
	@Path("/{pid}") // Identificador Proyecto
	@Produces(MediaType.APPLICATION_JSON)
	public Response ProyectoID(@HeaderParam("token") String token, @PathParam("pid") int pid) throws JSONException, JsonMappingException, IOException {

		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse = null;
		StatusMensaje statusMensaje = null;

		if (userEmail == null) {
			statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),
					"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
			return mResponse;
		}
		try {
			// Existe proyecto que se pide?
			Proyecto elProyecto = new Proyecto();
			ProyectoDAO ProyectoDAO = (ProyectoDAO) DAOFactory.getInstance().getDAO(elProyecto);
			elProyecto = ProyectoDAO.getProyecto(pid, null);
			// existe Proyecto
			if (elProyecto != null) {
				return Response.status(200).entity(elProyecto).build();
			} else {
				throw new RuntimeException("- El Proyecto (" + pid + ") es desconocido.");
			}
		} catch (Exception e) {
			statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),
					"\n" + e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto Tarea.\nLease API");
			mResponse = Response.status(499).entity(statusMensaje).build();
		}
		return mResponse;
	}

	@Override
	@PUT
	@Path("/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response upDateProyectoLista(@HeaderParam("token") String token, @PathParam("pid") int pid, JSONObject UpdateProyecto) throws JSONException, JsonMappingException, IOException {
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse = null;
		StatusMensaje statusMensaje = null;

		if (userEmail == null) {
			statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),
					"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
			return mResponse;
		}

		// TODO Auto-generated method stub
		try {
		} catch (Exception e) {
			mResponse = Response.status(499).entity(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto USUARIO.\nLease API").build();
			return mResponse;
		}
		mResponse = Response.status(200).entity("\n- A�adido un nuevo Proyecto de la lista.").build();
		return mResponse;
	}

	@Override
	@POST
	@Path("/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTareaListaP(@HeaderParam("token") String token, @PathParam("pid") int pid, JSONObject nuevaTarea) throws JSONException, JsonMappingException, IOException {
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse = null;
		StatusMensaje statusMensaje = null;

		if (userEmail == null) {
			statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),
					"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
			return mResponse;
		}
		// TODO Auto-generated method stub
		try {
		} catch (Exception e) {
			mResponse = Response.status(499).entity(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto USUARIO.\nLease API").build();
			return mResponse;
		}
		mResponse = Response.status(200).entity("\n- A�adido un nuevo Proyecto de la lista.").build();
		return mResponse;
	}

	@Override
	@DELETE
	@Path("/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response borrarProyectoLista(@HeaderParam("token") String token, @PathParam("pid") int pid) throws JSONException, JsonMappingException, IOException {
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse = null;
		StatusMensaje statusMensaje = null;

		if (userEmail == null) {
			statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),
					"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
			return mResponse;
		}
		// TODO Auto-generated method stub
		try {
		} catch (Exception e) {
			mResponse = Response.status(499).entity(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto USUARIO.\nLease API").build();
			return mResponse;
		}
		mResponse = Response.status(200).entity("\n- A�adido un nuevo Proyecto de la lista.").build();
		return mResponse;
	}

	@Override
	@GET
	@Path("/{pid}/tareas") // Identificador Tarea
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaTareasProyecto(@HeaderParam("token") String token, @PathParam("pid") int pid) throws JSONException, JsonMappingException, IOException {
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse = null;
		StatusMensaje statusMensaje = null;

		if (userEmail == null) {
			statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),
					"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
			return mResponse;
		}
		try {
			// Existe proyecto que se pide?
			Proyecto elProyecto = new Proyecto();
			ProyectoDAO ProyectoDAO = (ProyectoDAO) DAOFactory.getInstance().getDAO(elProyecto);
			elProyecto = ProyectoDAO.getProyecto(pid, null);
			// existe Proyecto
			if (elProyecto != null) {
				// Otener la lista de los proyectos de ese usuario
				Tarea laTarea = new Tarea();
				TareaDAO ProyDAO = (TareaDAO) DAOFactory.getInstance().getDAO(laTarea);
				return Response.status(200).entity(ProyDAO.getTareasList(elProyecto)).build();
			} else {
				throw new RuntimeException("- El Proyecto (" + pid + ") es desconocido.");
			}
		} catch (Exception e) {
			mResponse = Response.status(499).entity(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto USUARIO.\nLease API").build();
			return mResponse;
		}
	}
}
