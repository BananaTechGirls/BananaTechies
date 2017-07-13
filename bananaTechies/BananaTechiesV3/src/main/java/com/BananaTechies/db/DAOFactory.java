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
	

	public static final DAO getDAO(String tipo) {
		switch(tipo){
		case "usuario":UsuarioDAO uDAO=UsuarioDAOImpl.getInstance();return uDAO; 
		case "proyecto":ProyectoDAO pDAO=ProyectoDAOImpl.getInstance();return pDAO; 
		case "tarea":TareaDAO tDAO=TareaDAOImpl.getInstance();return tDAO; 
		default : return null;
		}
	}
}
