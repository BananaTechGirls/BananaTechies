package com.BananaTechies.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.BananaTechies.models.Proyecto;
import com.BananaTechies.models.Usuario;
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
	public Proyecto getProyecto(int idp, Usuario elUsuario) {
		Proyecto ProyectoADevolver = null;

		try {
			Connection conn = this.datasource.getConnection();
			// ordenes sql
			String sql = "SELECT p.* FROM bananatechies.proyecto p WHERE p.idp=? LIMIT 1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idp);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {

				ProyectoADevolver = new Proyecto(
						rs.getInt("idp"), 
						rs.getString("titulo"),
						elUsuario,											
						rs.getBoolean("status"),
						rs.getString("Progreso"),
						rs.getString("fechaInicio"),
						rs.getString("fechaFinal"),
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
	public List<Proyecto> getUserProyecto(Usuario unUsuario) {
		List<Proyecto> listADevolver = new ArrayList<Proyecto>();

		try {
			Connection conn = this.datasource.getConnection();

			// ordenes sql
			String sql = "SELECT p.idp, p.titulo, DATE_FORMAT(p.fechaInicio, '%m/%d/%Y') as fechaInicio, DATE_FORMAT(p.fechaFinal, '%m/%d/%Y') as fechaFinal, p.descripcion, p.nota, p.status, o.estado As progreso FROM bananatechies.proyecto p left join bananatechies.progreso o on p.progreso=o.idpro where p.responsable=? order by p.status;";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, unUsuario.getUid());
			
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				listADevolver.add(new Proyecto(					
						rs.getInt("idp"), 
						rs.getString("titulo"),
						unUsuario,											
						rs.getBoolean("status"),
						rs.getString("Progreso"),
						rs.getString("fechaInicio"),
						rs.getString("fechaFinal"),
						rs.getString("descripcion"),
						rs.getString("nota")					
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
			String sql = "SELECT p.* FROM bananatechies.proyecto p WHERE 1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			UsuarioDAO uDAO=(UsuarioDAO)UsuarioDAOImpl.getInstance();

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				listADevolver.add(new Proyecto(rs.getInt("idp"), 
						rs.getString("titulo"),
						uDAO.getUsuario(rs.getInt("usuario")),											
						rs.getBoolean("status"),
						rs.getString("Progreso"),
						rs.getString("fechaInicio"),
						rs.getString("fechaFinal"),
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
