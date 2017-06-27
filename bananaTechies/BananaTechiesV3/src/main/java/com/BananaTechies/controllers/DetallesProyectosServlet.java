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
import com.BananaTechies.db.ProyectoDAOImpl;
import com.BananaTechies.db.UsuarioDAO;
import com.BananaTechies.models.Proyecto;
import com.BananaTechies.models.Tarea;
import com.BananaTechies.models.Usuario;



@WebServlet("/DetalleProyectoServlet")
public class DetallesProyectosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger=Logger.getLogger("DetalleProyectoServlet");
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession misession= (HttpSession)request.getSession();
		Proyecto elProyecto = new Proyecto();
		
		Usuario elUsuario=(Usuario) misession.getAttribute("idUsuario");
				
		if( elUsuario!=null ){				
			ProyectoDAO pDAO=(ProyectoDAO) DAOFactory.getInstance().getDAO(elProyecto);

			elProyecto= pDAO.getProyecto(Integer.parseInt(request.getParameter("idp")));
						
			//List<Tarea> listaTareas = pDAO.getProyectoTarea(elProyecto);
			
			request.setAttribute("DetalleProyecto", elProyecto);
			//request.setAttribute("listaProyectosAMostrar", listaTareas);
			
			request.getRequestDispatcher("detalleProyecto.jsp").forward(request, response);
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
		DetallesProyectosServlet.logger = logger;
	}

}