package com.BananaTechies.controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession misession= (HttpSession)request.getSession();
		
		if( misession.getAttribute("idUsuario")!=null ){
			request.getRequestDispatcher("/lista_proyectos").forward(request, response);
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String contrasena = request.getParameter("password");
		
		System.out.println("***"+email+":"+contrasena+":"+ email.equals("ricardo@r.es") +":"+ contrasena.equals("qwerty") );
				
		if( email.equals("ricardo@r.es") && contrasena.equals("qwerty") ){
			
			HttpSession misession= (HttpSession)request.getSession();
			misession.setAttribute("idUsuario", "ricardo@r.es");
			
			request.getRequestDispatcher("/lista_proyectos").forward(request, response);
			
		 }else if ( email.equals("juana@j.es") && contrasena.equals("qwerty") ){
				HttpSession misession= (HttpSession)request.getSession();
				misession.setAttribute("idUsuario", "juana@j.es");
				
				request.getRequestDispatcher("/lista_proyectos").forward(request, response);
		 }else if ( email.equals("luis@l.es") && contrasena.equals("qwerty") ){
				HttpSession misession= (HttpSession)request.getSession();
				misession.setAttribute("idUsuario", "luis@l.es");
				
				request.getRequestDispatcher("/lista_proyectos").forward(request, response);
		}else{
			request.setAttribute("mierror", "Email y contraseña erroneos");
			doGet(request, response);
		}
		
	}

}
