package com.BananaTechies.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.BananaTechies.models.Usuario;

public final class UsuarioDAOImpl extends UsuarioDAO {
	private static Logger logger = Logger.getLogger("UsuarioDAOImpl");
	
	private static UsuarioDAOImpl instance = null;

	public static UsuarioDAOImpl getInstance() {
		if (instance == null) {
			instance = new UsuarioDAOImpl();
		}
		return instance;
	}
	
	@Override
	public Usuario getUsuario(String email, String password) {
		Usuario usuarioADevolver = null;
		logger.info("!--------------->>>>> getUsuario");
		try {
			
			Connection conn = datasource.getConnection();
			// ordenes sql
			String sql = "SELECT u.* FROM bananatechies.usuario u WHERE u.email=? AND password=? LIMIT 1;";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, email);
			pstm.setString(2, password);	
			ResultSet rs = pstm.executeQuery();

			int rowcount = 0;
			if (rs.last()) {
			  rowcount = rs.getRow();
			  rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
			}
			
			logger.info("!--------------->>>>> executeQuery >>>> numero de lineas: "+ rowcount);
			
			if (rs.next()) {
				usuarioADevolver = new Usuario(
						rs.getInt("idu"),
						rs.getString("nombre"),
						rs.getString("apellido"),
						rs.getString("email"), 
						rs.getString("password")
						//rs.getString("foto"),
						//rs.getString("video")
						);
			}

			pstm.close();
			conn.close();

			logger.info("Conexión exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			usuarioADevolver = null;
		}

		return usuarioADevolver;
	}

	@Override
	public boolean delUsuario(int uid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertUsuario(Usuario user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUsuario(Usuario user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Usuario getUsuario(int uid) {
		Usuario usuarioADevolver = null;

		try {
			Connection conn = datasource.getConnection();
			// ordenes sql
			String sql = "SELECT u.* FROM bananatechies.usuario u WHERE u.uid=?LIMIT 1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, uid);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {

				usuarioADevolver = new Usuario(
						rs.getInt("idu"),
						rs.getString("nombre"),
						rs.getString("apellido"),
						rs.getString("email"),
						rs.getString("password")
						//rs.getString("foto"),
						//rs.getString("video")
						);
			}

			pstm.close();
			conn.close();

			logger.info("Conexión exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			usuarioADevolver = null;
		}

		return usuarioADevolver;
	}

}
