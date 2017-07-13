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
			statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),	"Access Denied for this functionality !!!");
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
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto USUARIO.\nLease API").build();
			return mResponse;
		}
	}

	@Override
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProyectoLista(@HeaderParam("token") String token, Proyecto newProyecto)
			throws JSONException, JsonMappingException, IOException {
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse = null;
		StatusMensaje statusMensaje = null;

		if (userEmail == null) {
			statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),	"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
			return mResponse;
		}

		try {
			// obtener el objeto usuario completo
			Usuario elUsuario = new Usuario();
			UsuarioDAO userDAO = (UsuarioDAO) DAOFactory.getInstance().getDAO(elUsuario);
			elUsuario = userDAO.getUsuario(userEmail, null);
			if (elUsuario != null) {
				Proyecto elProyecto = new Proyecto();
				ProyectoDAO ProyDAO = (ProyectoDAO) DAOFactory.getInstance().getDAO(elProyecto);
				if (ProyDAO.insertProyecto(newProyecto)) {
					mResponse = Response.status(200).entity("El poryecto esta añadido").build();
				} else {
					mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity("Operacion sin actualizar").build();
				}
			} else {
				mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity("El responsable no existe").build();
			}

		} catch (Exception e) {
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto Proyecto.\nLease API")
					.build();
			return mResponse;
		}
		return mResponse;
	}

	@Override
	@GET
	@Path("/{pid}") // Identificador Proyecto
	@Produces(MediaType.APPLICATION_JSON)
	public Response ProyectoID(@HeaderParam("token") String token, @PathParam("pid") int pid)
			throws JSONException, JsonMappingException, IOException {

		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse = null;
		StatusMensaje statusMensaje = null;

		if (userEmail == null) {
			statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),	"Access Denied for this functionality !!!");
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
			statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),	"\n" + e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto Tarea.\nLease API");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
		}
		return mResponse;
	}

	@Override
	@PUT
	@Path("/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response upDateProyectoLista(@HeaderParam("token") String token, @PathParam("pid") int pid,
			JSONObject UpdateProyecto) throws JSONException, JsonMappingException, IOException {String userEmail = this.getUserEmailFromToken(token);
		Response mResponse = null;
		StatusMensaje statusMensaje = null;

		if (userEmail == null) {
			statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),	"Access Denied for this functionality !!!");
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
				if (ProyectoDAO.updateProyecto(elProyecto)) {
					mResponse = Response.status(200).entity("\n- Modificado Proyecto de la lista.").build();
				} else {
					mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity("\n- La modificado del Proyecto ha sido cancelada.").build();
				}
			} else {
				mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity("\n- El proyecto (" + pid + ") es inecsitente").build();
			}
		} catch (Exception e) {
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto USUARIO.\nLease API").build();
			return mResponse;
		}
		return mResponse;
	}

	@Override
	@POST
	@Path("/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTareaListaP(@HeaderParam("token") String token, @PathParam("pid") int pid, Tarea nuevaTarea) throws JSONException, JsonMappingException, IOException {
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse = null;
		StatusMensaje statusMensaje = null;

		if (userEmail == null) {
			statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),	"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
			return mResponse;
		}
		try {
			// obtener el objeto usuario completo
			Usuario elUsuario = new Usuario();
			UsuarioDAO userDAO = (UsuarioDAO) DAOFactory.getInstance().getDAO(elUsuario);
			elUsuario = userDAO.getUsuario(userEmail, null);
			if (elUsuario != null) {
				// Existe proyecto que se pide?
				Proyecto elProyecto = new Proyecto();
				ProyectoDAO ProyectoDAO = (ProyectoDAO) DAOFactory.getInstance().getDAO(elProyecto);
				elProyecto = ProyectoDAO.getProyecto(pid, elUsuario);
				// existe Proyecto
				if (elProyecto != null) {
					Tarea laTarea = new Tarea();
					TareaDAO tareaDAO = (TareaDAO) DAOFactory.getInstance().getDAO(laTarea);		
					if (tareaDAO.insertTarea(nuevaTarea)) {
						mResponse = Response.status(200).entity("\n- Modificado Proyecto de la lista.").build();
					} else {
						mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity("\n- La modificado del Proyecto ha sido cancelada.").build();
					}
				} else {
					mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity("\n- El proyecto (" + pid + ") es inecsitente").build();
				}
			}
			} catch (Exception e) {
				mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto USUARIO.\nLease API").build();
				return mResponse;
			}
		return mResponse;
	}

	@Override
	@DELETE
	@Path("/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response borrarProyectoLista(@HeaderParam("token") String token, @PathParam("pid") int pid)
			throws JSONException, JsonMappingException, IOException {
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse = null;
		StatusMensaje statusMensaje = null;

		if (userEmail == null) {
			statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),	"Access Denied for this functionality !!!");
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
			return mResponse;
		}
		try {
			// Borara tarea que nos pid?
			Proyecto elProyecto = new Proyecto();
			ProyectoDAO ProyectoDAO = (ProyectoDAO) DAOFactory.getInstance().getDAO(elProyecto);
			if (!(ProyectoDAO.delProyecto(pid))) {
				statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),	"Borrado el proyecto");
				mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
				return mResponse;
			}			
		} catch (Exception e) {
			mResponse = Response.status(Status.FORBIDDEN.getStatusCode()).entity(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto USUARIO.\nLease API").build();
			return mResponse;
		}
		mResponse = Response.status(200).entity("\n- Añadido un nuevo Proyecto de la lista.").build();
		return mResponse;
	}

	@Override
	@GET
	@Path("/{pid}/tareas") // Identificador Tarea
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaTareasProyecto(@HeaderParam("token") String token, @PathParam("pid") int pid)
			throws JSONException, JsonMappingException, IOException {
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse = null;
		StatusMensaje statusMensaje = null;

		if (userEmail == null) {
			statusMensaje = new StatusMensaje(Status.FORBIDDEN.getStatusCode(),	"Access Denied for this functionality !!!");
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
			mResponse = Response.status(499)
					.entity(e.getMessage() + "\n- Formato erroneo en el cuerpo del objeto USUARIO.\nLease API").build();
			return mResponse;
		}
	}
}
