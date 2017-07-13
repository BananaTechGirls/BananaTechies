package com.BananaTechies.resources;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.JsonWebKeySet;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;

import com.BananaTechies.db.DAOFactory;
import com.BananaTechies.db.UsuarioDAO;
import com.BananaTechies.models.StatusMensaje;
import com.BananaTechies.models.Usuario;
import com.sun.jersey.api.client.ClientResponse.Status;

@Path("/json")
public class JSONService {
	private static Logger logger = Logger.getLogger("JSONService");
	static List<JsonWebKey> jwkList = null;

	static {
		logger.info("Inside static initializer...");
		jwkList = new LinkedList<>();
		// Creating three keys, will use one now
		for (int kid = 1; kid <= 3; kid++) {
			JsonWebKey jwk = null;
			try {
				jwk = RsaJwkGenerator.generateJwk(2048);
				logger.info("PUBLIC KEY (" + kid + "): " + jwk.toJson(JsonWebKey.OutputControlLevel.PUBLIC_ONLY));
			} catch (JoseException e) {
				e.printStackTrace();
			}
			jwk.setKeyId(String.valueOf(kid));
			jwkList.add(jwk);
		}

	}

	
    // No se deve hacer y no se necesita
	@GET
	@Path("/owndata")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOwnData(@HeaderParam("token") String token) {
		logger.log(Level.INFO, "token:" + token);
		String userEmail = "";

		userEmail = this.getUserEmailFromToken(token);

		if (userEmail == null) {
			StatusMensaje statusMensaje = new StatusMensaje();
			statusMensaje.setStatus(Status.FORBIDDEN.getStatusCode());
			statusMensaje.setCuerpo("Access Denied for this functionality !!!");
			return Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMensaje).build();
		}

		Usuario user = null;
		UsuarioDAO userDAO;
		int uid = 0;

		/*try {
			userDAO = (UsuarioDAO) DAOFactory.getDAO("usuario");
			user = userDAO.getUsuarioByMail(userEmail);
			uid = user.getUid();
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		return Response.status(200).entity(user).build();
	}

	/* AUX */
	protected String getUserEmailFromToken(String token) {
		if (token == null)
			return null;

		String userEmail = null;

		try {

			JsonWebKeySet jwks = new JsonWebKeySet(jwkList);
			JsonWebKey jwk = jwks.findJsonWebKey("1", null, null, null);
			logger.log(Level.INFO, "JWK (1) ===> " + jwk.toJson());

			// Validate Token's authenticity and check claims
			JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireExpirationTime()
					.setAllowedClockSkewInSeconds(30).setRequireSubject().setExpectedIssuer("Techies.com")
					.setVerificationKey(jwk.getKey()).build();

			// Validate the JWT and process it to the Claims
			JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
			logger.log(Level.INFO, "JWT validation succeeded! " + jwtClaims.getSubject().toString());
			userEmail = jwtClaims.getSubject().toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userEmail;
	}
	
	// Crea el token en Jws...
	protected JsonWebSignature crearJWT( RsaJsonWebKey senderJwk, String email) {
		//RsaJsonWebKey senderJwk = (RsaJsonWebKey) jwkList.get(0);

		senderJwk.setKeyId("1");
		logger.info("JWK (1) ===> " + senderJwk.toJson());

		// Create the Claims, which will be the content of the JWT
		JwtClaims claims = new JwtClaims();
		claims.setIssuer("Techies.com"); // who creates the token and signs it
		claims.setExpirationTimeMinutesInTheFuture(10); // token will expire (10 minutes from now)
		claims.setGeneratedJwtId(); // a unique identifier for the token
		claims.setIssuedAtToNow(); // when the token was issued/created (now)
		claims.setNotBeforeMinutesInThePast(2); // time before which the token is not yet valid (2 minutes ago)
		claims.setSubject(email); // the subject/principal is whom the token is about
		claims.setStringListClaim("roles", "Admin"); // multi-valued claims for roles
		JsonWebSignature jws = new JsonWebSignature();

		jws.setPayload(claims.toJson());

		jws.setKeyIdHeaderValue(senderJwk.getKeyId());
		jws.setKey(senderJwk.getPrivateKey());

		jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
		return jws;
	}


}