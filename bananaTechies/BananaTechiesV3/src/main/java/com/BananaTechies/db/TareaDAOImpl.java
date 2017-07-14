package com.BananaTechies.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.BananaTechies.models.Proyecto;
import com.BananaTechies.models.Tarea;
import com.BananaTechies.models.Usuario;

public final class TareaDAOImpl extends TareaDAO {
	private static Logger logger = Logger.getLogger("TareaDaoImpl");

	private static TareaDAOImpl instance = null;

	public static TareaDAOImpl getInstance() {
		if (instance == null) {
			instance = new TareaDAOImpl();
		}
		return instance;
	}

	@Override
	public Tarea getTarea(int idt) {
		Tarea TareaADevolver = null;

		try {
			Connection conn = this.datasource.getConnection();
			// ordenes sql
			String sql = "SELECT t.idt, t.titulo, t.proyecto, t.responsable, t.status, o.estado As Progreso, DATE_FORMAT(t.fechaInicio, '%m/%d/%Y') as fechaInicio, DATE_FORMAT(t.fechaFinal, '%m/%d/%Y') as fechaFinal FROM bananatechies.tarea t left join bananatechies.progreso o on t.progreso= o.idpro WHERE t.idt=1 LIMIT 1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idt);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {

				TareaADevolver = new Tarea(rs.getInt("idt"), rs.getString("titulo"), null, rs.getString("Responsable"),
						rs.getInt("status"), rs.getString("Progreso"), rs.getString("fechaInicio"),
						rs.getString("fechaFinal"));
			}

			pstm.close();
			conn.close();

			logger.info("Conexi�n exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexi�n de BBDD:" + e);
			TareaADevolver = null;
		}

		return TareaADevolver;
	}

	@Override
	public List<Tarea> getTareasList(Proyecto elProyecto) {
		List<Tarea> listADevolver = new ArrayList<Tarea>();

		try {
			Connection conn = this.datasource.getConnection();

			// ordenes sql
			String sql = "SELECT t.idt, t.titulo, t.proyecto, concat(u.nombre, ' ' , u.apellido) As Responsable, t.status, o.estado As Progreso, DATE_FORMAT(t.fechaInicio, '%m/%d/%Y') as fechaInicio, DATE_FORMAT(t.fechaFinal, '%m/%d/%Y') as fechaFinal FROM bananatechies.tarea t left join bananatechies.progreso o on t.progreso= o.idpro left join bananatechies.usuario u on t.responsable=u.idu WHERE t.proyecto=? order by t.status desc;";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, elProyecto.getIdp());

			ResultSet rs = pstm.executeQuery();

			/*
			 * //Obtenet lineas RS int rowcount = 0; if (rs.last()) { rowcount =
			 * rs.getRow(); rs.beforeFirst(); // not rs.first() because the
			 * rs.next() below will move on, missing the first element }
			 * 
			 * logger.
			 * info("!---------------getTareasList >>>>> executeQuery >>>> numero de lineas: "
			 * + rowcount);
			 */

			while (rs.next()) {
				listADevolver.add(new Tarea(rs.getInt("idt"), rs.getString("titulo"), null, rs.getString("Responsable"),
						rs.getInt("status"), rs.getString("Progreso"), rs.getString("fechaInicio"),
						rs.getString("fechaFinal")));
			}

			pstm.close();

			conn.close();

			logger.info("Conexi�n exitosa <getTareasList>");

		} catch (Exception e) {
			logger.severe("Error en la conexi�n de BBDD:" + e);
			listADevolver = null;
		}

		return listADevolver;
	}

	@Override
	public boolean delTarea(int idt) throws Exception {
		boolean estaBorrado = false;
		PreparedStatement pstm = null;
		Connection conn = null;

		try {
			conn = this.datasource.getConnection();
			String sql = "DELETE FROM tarea WHERE idt=?;";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idt);

			pstm.executeUpdate();
			if (pstm.getUpdateCount() == 0) {
				throw new Exception(MessageFormat.format("Objeto sin borrar", sql));
			} else {
				estaBorrado = true;
				logger.info("conexion exitosa borrar proyecto");
			}

		} catch (Exception e) {
			logger.severe("Error en la conexion" + e);
		} finally {
			pstm.close();

			conn.close();
		}
		;
		return estaBorrado;
	}

	@Override
	public boolean insertTarea(Tarea tarea) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTarea(int idt, Tarea tarea) throws Exception {
		boolean estaActualizado = false;
		PreparedStatement pstm = null;
		Connection conn = null;

		conn = this.datasource.getConnection();
		String sql = "UPDATE bananatechies.tarea SET " + "status=?, " + "progreso=?, " + "responsable=?, "
				+ "proyecto=? " + "WHERE idt=?";

		pstm = conn.prepareStatement(sql);

		pstm = conn.prepareStatement(sql);
		pstm.setString(1, tarea.getTitulo());
		pstm.setInt(2, tarea.getStatus());
		pstm.setInt(3, tarea.getProgreso);
		pstm.setUsuario(4, tarea.getResponsable);
		pstm.setsetInt(5, tarea.getProyecto);

		pstm.executeUpdate();
		try {
			if (pstm.getUpdateCount() == 0) {
				throw new Exception(MessageFormat.format("Nigun Objeto esta actualizado \"{0}\"", sql));
			} else {
				estaActualizado = true;
				logger.info("Conexi�n exitosa updateTarea");
			}
			pstm.close();
			conn.close();
		} catch (

		Exception e) {
			logger.severe("Error en la conexi�n de BBDD:" + e);
		}

		return estaActualizado;
	}

	@Override
	public List<Tarea> getUserTarea(Usuario user) {
		List<Tarea> listADevolver = new ArrayList<Tarea>();
		try {
			Connection conn = this.datasource.getConnection();

			// ordenes sql
			String sql = "UPDATE bananatechies.tarea SET " + "titulo=?, " + "status=?, " + "progreso=?, "
					+ "responsable=?, " + "proyecto=? " + "WHERE idt=?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, user.getUid());

			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				listADevolver.add(new Tarea(rs.getInt("idt"), rs.getString("titulo"), null, rs.getString("Responsable"),
						rs.getInt("status"), rs.getString("Progreso"), rs.getString("fechaInicio"),
						rs.getString("fechaFinal")));
			}
			pstm.close();

			conn.close();

			logger.info("Conexión exitosa getUserTarea");
		} catch (Exception e) {
			logger.severe("Error en la conexión de BBDD:" + e);
			listADevolver = null;
		}

		return listADevolver;
	}
}
