package com.BananaTechies.controllers;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.BananaTechies.db.DAOFactory;
import com.BananaTechies.db.UsuarioDAO;
import com.BananaTechies.models.Usuario;



@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger=Logger.getLogger("LoginServlet");
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession misession= (HttpSession)request.getSession();
		
		if( misession.getAttribute("idUsuario")!=null ){
			request.getRequestDispatcher("/lista_proyectos").forward(request, response);
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("Email");
		String contrasena = request.getParameter("Password");
		Usuario elUsuario = null;

		//DBConnector dbc= DBConnector.getInstance();
		
		UsuarioDAO userDAO = (UsuarioDAO)  DAOFactory.getInstance().getDAO("usuario");	
		
		elUsuario = userDAO.getUsuario(email, contrasena);//null point exeption
		
		if( elUsuario!=null ){
			HttpSession misession= (HttpSession)request.getSession();
			misession.setAttribute("idUsuario", elUsuario);
			
			request.getRequestDispatcher("/lista_proyectos").forward(request, response);
		}else{
			request.setAttribute("mierror", "Email y contraseña erroneos");
			doGet(request, response);
		}
		
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		LoginServlet.logger = logger;
	}

}
