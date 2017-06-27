package com.BananaTechies.controllers;

import java.io.IOException;
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


@WebServlet("/DetalleProyectoServlet")
public class DetalleProyectoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger=Logger.getLogger("DetalleProyectoServlet");
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession misession= (HttpSession)request.getSession();
		Proyecto elProyecto = new Proyecto();
		System.out.println("*************************************************************");
		Usuario elUsuario=(Usuario) misession.getAttribute("idUsuario");
		System.out.println(elUsuario.getNombre());
				
		if( elUsuario!=null ){				
			ProyectoDAO pDAO=(ProyectoDAO) DAOFactory.getInstance().getDAO(elProyecto);

			//System.out.println(elUsuario.getNombre() +"IdP ->"+request.getParameter("idp"));
			
			elProyecto= pDAO.getProyecto(Integer.parseInt(request.getParameter("idp")), elUsuario);
						
			//List<Tarea> listaTareas = pDAO.getProyectoTarea(elProyecto);
			
			request.setAttribute("DetalleProyecto", elProyecto);
			logger.info("+++++++++++++++ >> titulo");
			
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
		DetalleProyectoServlet.logger = logger;
	}

}
