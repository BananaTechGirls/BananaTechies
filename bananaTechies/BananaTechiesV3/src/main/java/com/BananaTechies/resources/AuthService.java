package com.BananaTechies.resources;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

import com.BananaTechies.models.StatusMensaje;
import com.BananaTechies.db.DAOFactory;
import com.BananaTechies.db.UsuarioDAO;
import com.BananaTechies.models.StatusMensaje;
import com.BananaTechies.models.Usuario;
import com.sun.jersey.api.client.ClientResponse.Status;

@Path("/authenticate")
public class AuthService extends JSONService {

	private static Logger logger = Logger.getLogger("JSONService");

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateCredentials(@HeaderParam("username") String username, @HeaderParam("password") String password)	throws JsonGenerationException, JsonMappingException, IOException {
		logger.info("Authenticating User Credentials...");

		if (username == null) {
			StatusMensaje StatusMensaje = new StatusMensaje();
			StatusMensaje.setStatus(Status.PRECONDITION_FAILED.getStatusCode());
			StatusMensaje.setCuerpo("Username value is missing!!!");
			return Response.status(Status.PRECONDITION_FAILED.getStatusCode()).entity(StatusMensaje).build();
		}

		if (password == null) {
			StatusMensaje StatusMensaje = new StatusMensaje();
			StatusMensaje.setStatus(Status.PRECONDITION_FAILED.getStatusCode());
			StatusMensaje.setCuerpo("Password value is missing!!!");
			return Response.status(Status.PRECONDITION_FAILED.getStatusCode()).entity(StatusMensaje).build();
		}

		Usuario user = new Usuario();
		//UsuarioDAO usuarioDAO;
		try {			
			UsuarioDAO userDAO = (UsuarioDAO)  DAOFactory.getInstance().getDAO(user);	
			user = userDAO.getUsuario(username, password);
			logger.log(Level.INFO, "user:" + user);
		} catch (Exception e) {
			e.printStackTrace();
		}

			if (user == null) {
				StatusMensaje statusMensaje = new StatusMensaje();
				statusMensaje.setStatus(Status.FORBIDDEN.getStatusCode());
				statusMensaje.setCuerpo("Access Denied for this functionality !!!");
				return Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
		}
			JsonWebSignature jws = crearJWT((RsaJsonWebKey) jwkList.get(0), user.getEmail());
	
			String jwt = null;
		
			try {
				jwt = jws.getCompactSerialization();
			} catch (JoseException e) {
				e.printStackTrace();
			}

		return Response.status(200).entity(jwt).build();
	
	}
	



}
