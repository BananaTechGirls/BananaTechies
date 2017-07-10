package com.BananaTechies.resources;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.BananaTechies.db.DAOFactory;
import com.BananaTechies.db.ProyectoDAO;
import com.BananaTechies.db.UsuarioDAO;
import com.BananaTechies.models.Mensaje;
import com.BananaTechies.models.Proyecto;
import com.BananaTechies.models.Usuario;
import com.BananaTechies.resources.ProyectosAPI;

@Path("/Proyectos")
public class ProyectosREST implements ProyectosAPI {
	private static List<Proyecto> misProyectos;
	
	static {
		ProyectosREST.misProyectos = new ArrayList<Proyecto>();
		//MOCKEAR LOS DATOS ENTRADA EN VEZ DE LA BBDD < MYSQL >
		/*for (int i=0; i < 10; i++){
			Proyecto nuevo= new Proyecto (i+1);
			nuevo.setEdad(nuevo.getEdad()-(i*3));
			nuevo.setName(nuevo.getName()+(char)(65+i));
			ProyectosREST.misUsuarios.add(nuevo);
		}*/
		
	}
	
	@Override
	@GET
	@Path("/") 
	@Produces(MediaType.APPLICATION_JSON)
	//public List<Proyecto> listaProyectoResponsables (@PathParam("uid") int uid){
	public Object listaProyectoResponsables (){
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
	public Mensaje addProyectoLista(JSONObject newProyecto) throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Proyecto ProyectoID(int pid) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Mensaje upDateProyectoLista(int uid, JSONObject modificadoProyecto) throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mensaje addTareaListaP(int pid, JSONObject nuevaTarea) throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Mensaje borrarProyectoLista(int uid) throws JSONException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object listaTareasProyecto(int pid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	/*@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Proyecto> listaUsuarios (){
		//Obtener DAO FACTORY La lista TODOS los USUARIOS
		return ProyectosREST.misUsuarios;
	}

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
	}


	
	public Proyecto findProyecto(int uid){
		Proyecto ProyectoIterado = null; 
		ListIterator<Proyecto> it = ProyectosREST.misUsuarios.listIterator();
		while(it.hasNext()) {		 
			ProyectoIterado = it.next();
			if (ProyectoIterado.getUid()==uid) {break;}	
		}
		return ProyectoIterado.getUid()!=uid?null:ProyectoIterado;
	}
	
	
	@POST
	@Path("/EKO/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Object ecoUsuario(JSONObject nuevoProyecto) throws JSONException  {
		try {	
			//Para obtener Atribitos de la clase UProyecto para separaor capa API de la Modelo
			Field[] fieldsProyecto = Proyecto.class.getFields();
			
			//Dar formato de JSON a usuario sin usar JErsey para Verificar el objeto entrante
			Proyecto elProyecto = new Proyecto(nuevoProyecto.getInt(fieldsProyecto[0].getName()), 
											   nuevoProyecto.getString(fieldsProyecto[1].getName()), 
											   nuevoProyecto.getString(fieldsProyecto[2].getName()),
											   nuevoProyecto.getInt(fieldsProyecto[3].getName()));

			return elProyecto;
		} catch (Exception e) {
			Mensaje resp = new Mensaje(e.getMensaje() +"\n- Formato erroneo en el cuerpo del objeto USUARIO.\nLease API");
			return resp;
		}
	}

	
	
	//---------------------------------------------------------
	
	
	
	
	@POST
	@Path("/login")
	public boolean esProyecto(){
		return true;
	}
	
	//curl -X GET http://localhost:8080/BananaApp/rest/usuario/pepe/ruiz/33
	@GET
	@Path("/{nom}/{ape}/{edad}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean addu (@PathParam("nom") String nom, @PathParam("ape") String ape, @PathParam("edad") int edad){
		Proyecto nuevo= new Proyecto (ProyectosREST.misUsuarios.get(ProyectosREST.misUsuarios.size() - 1).getUid()+1, nom, ape, edad);
		ProyectosREST.misUsuarios.add(nuevo);		
		return true;
	}
	
	public boolean addProyecto (String nom, String ape, int edad){
		Proyecto nuevo= new Proyecto (ProyectosREST.misUsuarios.get(ProyectosREST.misUsuarios.size() - 1).getUid()+1, nom, ape, edad);
		ProyectosREST.misUsuarios.add(nuevo);		
		return true;
	}*/
}


