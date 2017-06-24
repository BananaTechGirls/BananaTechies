package com.BananaTechies.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.BananaTechies.models.Proyecto;
import com.BananaTechies.db.UsuarioDAO;
import com.BananaTechies.db.UsuarioDAOImpl;

public final class ProyectoDAOImpl extends ProyectoDAO {
	private static Logger logger = Logger.getLogger("ProyectoDaoImpl");
	
	private static ProyectoDAOImpl instance = null;

	public static ProyectoDAOImpl getInstance() {
		if (instance == null) {
			instance = new ProyectoDAOImpl();
		}
		return instance;
	}
	
	@Override
	public Proyecto getProyecto(int idp) {
		Proyecto ProyectoADevolver = null;

		try {
			Connection conn = this.datasource.getConnection();
			// ordenes sql
			String sql = "SELECT p.* FROM proyecto p WHERE p.idp=? LIMIT 1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idp);
			
			
			UsuarioDAO uDAO=(UsuarioDAO)UsuarioDAOImpl.getInstance();
			//TareaDAO=(TareaDAO)TareaDAOImpl.getInstance();
			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {

				ProyectoADevolver = new Proyecto(
						rs.getInt("idp"), 
						rs.getString("titulo"),
						uDAO.getUsuario(rs.getInt("usuario")),											
						rs.getBoolean("status"),
						rs.getString("Progreso"),
						rs.getDate("fechaInicio"),
						rs.getDate("fechaFinal"),
						rs.getString("descripcion"),
						rs.getString("notas")
						);
			}

			pstm.close();
			conn.close();

			logger.info("Conexión exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			ProyectoADevolver = null;
		}

		return ProyectoADevolver;
	}

	@Override
	public boolean delProyecto(int idp) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertProyecto(Proyecto proyecto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProyecto(Proyecto proyecto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Proyecto> getUserProyecto(int uid) {
		List<Proyecto> listADevolver = new ArrayList<Proyecto>();

		try {
			Connection conn = this.datasource.getConnection();

			// ordenes sql
			String sql = "SELECT m.* FROM maquillaje m INNER JOIN compra c ON c.cosmetico=m.mid WHERE c.usuario=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, uid);

			UsuarioDAO uDAO=(UsuarioDAO)UsuarioDAOImpl.getInstance();
			
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				listADevolver.add(new Proyecto(
						
						rs.getInt("idp"), 
						rs.getString("titulo"),
						uDAO.getUsuario(rs.getInt("usuario")),											
						rs.getBoolean("status"),
						rs.getString("Progreso"),
						rs.getDate("fechaInicio"),
						rs.getDate("fechaFinal"),
						rs.getString("descripcion"),
						rs.getString("notas")
						
						
						));
			}

			pstm.close();

			conn.close();

			logger.info("Conexión exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			listADevolver = null;
		}

		return listADevolver;
	}

	@Override
	public List<Proyecto> getProyectosList() {
		List<Proyecto> listADevolver = new ArrayList<Proyecto>();

		try {
			Connection conn = this.datasource.getConnection();

			// ordenes sql
			String sql = "SELECT m.* FROM maquillaje m WHERE 1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			UsuarioDAO uDAO=(UsuarioDAO)UsuarioDAOImpl.getInstance();

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				listADevolver.add(new Proyecto(rs.getInt("idp"), 
						rs.getString("titulo"),
						uDAO.getUsuario(rs.getInt("usuario")),											
						rs.getBoolean("status"),
						rs.getString("Progreso"),
						rs.getDate("fechaInicio"),
						rs.getDate("fechaFinal"),
						rs.getString("descripcion"),
						rs.getString("notas")
						
						
						));
			}

			pstm.close();

			conn.close();

			logger.info("Conexión exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			listADevolver = null;
		}

		return listADevolver;
	}

}
