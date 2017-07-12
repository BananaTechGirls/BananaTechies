package com.BananaTechies.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
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
			String sql = "SELECT p.idp, p.titulo, p.status, p.Progreso, DATE_FORMAT(p.fechaInicio, '%m/%d/%Y') as fechaInicio, DATE_FORMAT(p.fechaFinal, '%m/%d/%Y') as fechaFinal, p.descripcion, p.nota FROM bananatechies.proyecto p WHERE p.idp=? LIMIT 1";
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
						rs.getString("nota")
						);
			}

			pstm.close();
			conn.close();

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
	public boolean insertProyecto(Proyecto proyecto) throws Exception {
		boolean estaInsertado = false;
		PreparedStatement pstm = null;
		Connection conn = null;
		try {
			conn = this.datasource.getConnection();

			//String query = " insert into users (first_name, last_name, date_created, is_admin, num_points) values (?, ?, ?, ?, ?)";
			String query = "INSERT INTO `bananatechies`.`proyecto` (`titulo`, `responsable`, `fechaInicio`, `descripcion`, `nota`, `status`, `progreso`) VALUES ('GBProyecto', '1', '2017-07-10 10:39:04', 'Ex partem placerat sea, porro commodo ex eam. Por Gabriel', '***', '0', '1')";

			pstm = conn.prepareStatement(query);
			/*pstm.setString(1, "Barney");
			pstm.setString(2, "Rubble");
			pstm.setDate(3, startDate);
			pstm.setBoolean(4, false);
			pstm.setInt(5, 5000);*/

			// execute the preparedstatement
			pstm.executeUpdate();
			if (pstm.getUpdateCount() == 0) {
				throw new Exception(MessageFormat.format("Nigun Objeto insertado \"{0}\"", query));
			} else {
				estaInsertado = true;
				logger.info("Conexi�n exitosa insertProyecto");
			}
			pstm.close();
			conn.close();	
		} catch (Exception e) {
			logger.severe("Error en la conexi�n de BBDD:" + e);
		} 
		return estaInsertado;
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
			String sql = "SELECT p.idp, p.titulo, DATE_FORMAT(p.fechaInicio, '%m/%d/%Y') as fechaInicio, DATE_FORMAT(p.fechaFinal, '%m/%d/%Y') as fechaFinal, p.descripcion, p.nota, p.status, o.estado As progreso FROM bananatechies.proyecto p left join bananatechies.progreso o on p.progreso=o.idpro where p.responsable=? order by p.status desc;";
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


			logger.info("Conexión exitosa getUserProyecto");


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


			logger.info("Conexión exitosa <getProyectosList>");


		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			listADevolver = null;
		}

		return listADevolver;
	}

}
