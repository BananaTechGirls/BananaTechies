package com.BananaTechies.controllers;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BananaTechies.db.DAOFactory;
import com.BananaTechies.db.ProyectoDAO;
import com.BananaTechies.models.Proyecto;
import com.BananaTechies.models.Usuario;


@WebServlet("/lista_tareas")
public class ListaTareaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger=Logger.getLogger("ListaTareasServlet");
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession misession= (HttpSession)request.getSession();
		Proyecto elProyecto = new Proyecto();
		
		if( misession.getAttribute("idUsuario")!=null ){
			Usuario elUsuario=(Usuario) misession.getAttribute("idUsuario");
			
			ProyectoDAO pDAO=(ProyectoDAO) DAOFactory.getInstance().getDAO(elProyecto);
						
			List<Proyecto> listaProyectos = pDAO.getUserProyecto(elUsuario);
			request.setAttribute("listaTareasAMostrar", listaProyectos);
			
			request.getRequestDispatcher("listaTareas.jsp").forward(request, response);
		}else{
			misession.invalidate();
			response.sendRedirect("login");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		ListaProyectosServlet.setLogger(logger);
	}

}
