package com.BananaTechies.db;

public class DAOFactory {
	private static DAOFactory instance = null;

	public static DAOFactory getInstance() {
		if (instance == null) {
			instance = new DAOFactory();
		}
		return instance;
	}
	
	
	
	public Object getDAO(String daoType) {
		switch(daoType){
		case "usuario":return(Object)UsuarioDAOImpl.getInstance();
		case "proyecto":return(Object)ProyectoDAOImpl.getInstance();
		case "tarea":return(Object)TareaDAOImpl.getInstance();
		default : return null;
		}
	}
}
