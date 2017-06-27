package com.BananaTechies.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			String sql = "SELECT t.* FROM bananatechies.tarea t WHERE t.idt=? LIMIT 1";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, idt);	
			
			
			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {

/*				TareaADevolver = new Tarea(
						rs.getInt("idt"), 
						rs.getString("titulo"),
						pDAO.getProyecto(rs.getInt("proyecto ")),
						rs.getString("responsable"),											
						rs.getBoolean("status"),
						rs.getString("Progreso"),
						rs.getString("fechaInicio"),
						rs.getString("fechaFinal")
						);*/
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
			String sql = "SELECT t.idt,t.proyecto,u.nombre,t.status,o.estado As progreso,DATE_FORMAT(p.fechaInicio, '%m/%d/%Y') as fechaInicio, DATE_FORMAT(p.fechaFinal, '%m/%d/%Y') as fechaFinal,FROM bananatechies.tarea t left join bananatechies.progreso o on t.progreso= o.idpro, left join bananatechies.usuario u on t.responsable=u.idu WHERE t.responsable=?,order by p.status desc;";  
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, elProyecto.getIdp());
			
			
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				listADevolver.add(new Tarea(
						rs.getInt("idt"), 
						rs.getString("titulo"),
						elProyecto,
						rs.getString("responsable"),										
						rs.getBoolean("status"),
						rs.getString("Progreso"),
						rs.getString("fechaInicio"),
						rs.getString("fechaFinal")
						));
			}

			pstm.close();

			conn.close();

			logger.info("Conexi�n exitosa");

		} catch (Exception e) {
			logger.severe("Error en la conexi�n de BBDD:" + e);
			listADevolver = null;
		}

		return listADevolver;
	}
		

	@Override
	public boolean delaTarea(int idt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertTarea(Tarea tarea) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTarea(Tarea tarea) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Tarea> getUserTarea(Usuario user) {
		// TODO Auto-generated method stub
		return null;
	}

}
