package com.BananaTechies.db;

public class DAOFactory {
	private static DAOFactory instance = null;

	public static DAOFactory getInstance() {
		if (instance == null) {
			instance = new DAOFactory();
		}
		return instance;
	}
	
	
	
	public Object getDAO(Object daoType) {
		switch(daoType.getClass().getSimpleName()){
		case "Usuario":return(Object)UsuarioDAOImpl.getInstance();
		case "Proyecto":return(Object)ProyectoDAOImpl.getInstance();
		case "Tarea":return(Object)TareaDAOImpl.getInstance();
		default : return null;
		}
	}
}
