package com.BananaTechies.resources;


import java.io.IOException;
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
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.BananaTechies.db.DAOFactory;
import com.BananaTechies.db.ProyectoDAO;
import com.BananaTechies.db.TareaDAO;
import com.BananaTechies.db.UsuarioDAO;
import com.BananaTechies.models.Mensaje;
import com.BananaTechies.models.Proyecto;
import com.BananaTechies.models.Tarea;
import com.BananaTechies.models.Usuario;
import com.BananaTechies.resources.ProyectosAPI;

@Path("/Proyectos")
public class ProyectosREST implements ProyectosAPI {
	private static List<Proyecto> misProyectos;
	
	static {
		ProyectosREST.misProyectos = new ArrayList<Proyecto>();
	}
	
	@Override
	@GET
	@Path("/") 
	@Produces(MediaType.APPLICATION_JSON)
	//public List<Proyecto> listaProyectoResponsables (@PathParam("uid") int uid){
	public Object listaProyectoResponsables ()  throws JSONException, JsonMappingException, IOException {
		int uid =1; //mock de token
		Mensaje resp = new Mensaje(0,"");
		try {
			//obtener el objeto usuario completo
			Usuario elUsuario= new Usuario();
			UsuarioDAO userDAO = (UsuarioDAO)  DAOFactory.getInstance().getDAO(elUsuario);
			elUsuario = userDAO.getUsuario(uid);		
			//existe usuario uid
			if (elUsuario!=null) {
				//Otener la lista de los proyectos de ese usuario
				Proyecto elProyecto= new Proyecto();
				ProyectoDAO ProyDAO = (ProyectoDAO)  DAOFactory.getInstance().getDAO(elProyecto);
				ProyectosREST.misProyectos = ProyDAO.getUserProyecto(elUsuario);
			}else {
				throw new RuntimeException("- El usuario ("+ uid +") es desconocido.");
			}
			return ProyectosREST.misProyectos;
		} catch (Exception e) {
			resp.setCuerpo(e.getMessage() +"\n- Formato erroneo en el cuerpo del objeto USUARIO.\nLease API");
			return resp;			
		}
	}

	@Override
	@POST
	@Path("/") 
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Mensaje addProyectoLista (Proyecto newProyecto)  throws JSONException, JsonMappingException, IOException{

		int uid =1; //mock de token
		Mensaje resp = new Mensaje(0,"");
/*		try {
			//obtener el objeto usuario completo
			Usuario elUsuario= new Usuario();
			// Verificar el nuevo Proyecto a introducir
			
			ProyectoDAO ProyDAO = (ProyectoDAO)  DAOFactory.getInstance().getDAO(elProyecto);

			
			//existe usuario uid
			if (elUsuario!=null) {
				System.out.println("*** < 2 > ***");
				elProyecto = ProyDAO.getProyecto(elProyecto.getIdp(), elUsuario);
				System.out.println("*** < 3 > ***");
				//existe proyecto pid
				if (elProyecto==null) {
					resp.setCuerpo(ProyDAO.insertProyecto(elProyecto)?"":"");
				}else {
					throw new RuntimeException("- El proyecto ("+ elProyecto.getIdp() +") ya exite, imposible añadirlo.");
				}				
			}else {
				throw new RuntimeException("- El proyecto ("+ uid +") es desconocido.");
			}
			return resp;
		} catch (Exception e) {
			resp.setCuerpo(e.getMessage() +"\n- Formato erroneo en el cuerpo del objeto Proyecto.\nLease API");
			return resp;		
		}*/	
		
		return new Mensaje(0, "Añadido un nuevo Proyecto de la lista.");
	}

	@Override
	@GET
	@Path("/{pid}") //Identificador Proyecto
	@Produces(MediaType.APPLICATION_JSON)
	public Response ProyectoID (@PathParam("pid") int pid)  throws JSONException, JsonMappingException, IOException{
		Tarea unaTarea = null;
		Mensaje resp= null;
		int uid =1; //mock de token
		try {
			//Existe proyecto que se pide?
			Proyecto elProyecto= new Proyecto();
			ProyectoDAO ProyectoDAO = (ProyectoDAO)  DAOFactory.getInstance().getDAO(elProyecto);
			elProyecto = ProyectoDAO.getProyecto(pid, null);		
			//existe Proyecto
			if (elProyecto!=null) {
				return Response.status(200).entity(elProyecto).build();
			}else {
				throw new RuntimeException("- El Proyecto ("+ pid +") es desconocido.");
			}
		} catch (Exception e) {
			resp.setCuerpo(e.getMessage() +"\n- Formato erroneo en el cuerpo del objeto Tarea.\nLease API");
			return Response.status(499).entity(resp).build();			
		}
	}

	
	@Override
	@PUT
	@Path("/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Mensaje upDateProyectoLista (@PathParam("pid") int pid, JSONObject UpdateProyecto)  throws JSONException, JsonMappingException, IOException{
		// TODO Auto-generated method stub
		return new Mensaje(0, "Actualizar/Modificar los detalles Proyecto de liata");
	}

	@Override
	@POST
	@Path("/{pid}") 
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Mensaje addTareaListaP (@PathParam("pid") int pid, JSONObject nuevaTarea)  throws JSONException, JsonMappingException, IOException{
		// TODO Auto-generated method stub
		return new Mensaje(0, "Añadido la tarea al Proyecto");
	}

	
	@Override
	@DELETE
	@Path("/{pid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Mensaje borrarProyectoLista (@PathParam("pid") int pid)  throws JSONException, JsonMappingException, IOException{
		// TODO Auto-generated method stub
		return new Mensaje(0, "Borrado un proyecto de lista ");
	}

	@Override
	@GET
	@Path("/{pid}/tareas") //Identificador Tarea
	@Produces(MediaType.APPLICATION_JSON)
	public Response listaTareasProyecto (@PathParam("pid") int pid) throws JSONException, JsonMappingException, IOException{
		Tarea unaTarea = null;
		Mensaje resp= null;
		int uid =1; //mock de token
		try {
			//Existe proyecto que se pide?
			Proyecto elProyecto= new Proyecto();
			ProyectoDAO ProyectoDAO = (ProyectoDAO)  DAOFactory.getInstance().getDAO(elProyecto);
			elProyecto = ProyectoDAO.getProyecto(pid, null);		
			//existe Proyecto
			if (elProyecto!=null) {
				//Otener la lista de los proyectos de ese usuario
				Tarea laTarea = new Tarea();
				TareaDAO ProyDAO = (TareaDAO)  DAOFactory.getInstance().getDAO(laTarea);
				return Response.status(200).entity(ProyDAO.getTareasList(elProyecto)).build();
			}else {
				throw new RuntimeException("- El Proyecto ("+ pid +") es desconocido.");
			}
		} catch (Exception e) {
			resp.setCuerpo(e.getMessage() +"\n- Formato erroneo en el cuerpo del objeto Tarea.\nLease API");
			return Response.status(499).entity(resp).build();			
		}
	}
	
	
	
	/*
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Mensaje insertUsuarioLIsta(JSONObject nuevoProyecto) throws JSONException  {
		Mensaje resp = new Mensaje("");
		try {				
			//Para obtener Atribitos de la clase UProyecto para separaor capa API de la Modelo
			Field[] fieldsProyecto = Proyecto.class.getFields();
			
			//Dar formato de JSON a usuario sin usar JErsey para Verificar el objeto entrante
			Proyecto elProyecto = new Proyecto(nuevoProyecto.getInt(fieldsProyecto[0].getName()), nuevoProyecto.getString(fieldsProyecto[1].getName()), nuevoProyecto.getString(fieldsProyecto[2].getName()), nuevoProyecto.getInt(fieldsProyecto[3].getName()));

			//Si exite el Usuario No lo devuelve
			if (findProyecto(nuevoProyecto.getInt(fieldsProyecto[0].getName()))!=null){
				throw new RuntimeException("- El usuario Existe. "+ fieldsProyecto[0].getName() +" == "+nuevoProyecto.getInt(fieldsProyecto[0].getName()));
			}else {
				ProyectosREST.misUsuarios.add(elProyecto);
				resp.setCuerpo("- El usuario Insertado.");
			}
			return resp;
		} catch (Exception e) {
			resp.setCuerpo(e.getMensaje() +"\n- Formato erroneo en el cuerpo del objeto USUARIO.\nLease API");
			return resp;
			
		}
	} throws JSONException, JsonMappingException, IOException*/
	
	

}


