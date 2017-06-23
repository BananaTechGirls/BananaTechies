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

import com.BananaTechies.models.Proyecto;
import com.BananaTechies.models.Usuario;



@WebServlet("/lista_proyectos")
public class ListaProyectosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger=Logger.getLogger("ListaMaquillajesServlet");
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession misession= (HttpSession)request.getSession();
		
		if( misession.getAttribute("usuario")!=null ){
			Usuario elUsuario=(Usuario) misession.getAttribute("usuario");
/*			ProyectoDAO pDAO=(ProyectoDAO)ProyectoDAOImpl.getInstance();
			
			List<Proyecto> listaMaquillajes = pDAO.getUserMaquillajes(elUsuario.getUid() );
			request.setAttribute("listaProyectosAMostrar", listaProyectos);*/
			
			request.getRequestDispatcher("plantilla_listaProyectos.jsp").forward(request, response);
		}else{
			misession.invalidate();
			response.sendRedirect("login");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ESTOY EN POST!!");
		doGet(request, response);
	}

}
