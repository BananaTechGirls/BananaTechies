package com.BananaTechies.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.juanma.models.Proyectos;
import com.juanma.models.Tarea;

@WebServlet("/detalle_proyecto")
public class DetalleProyectosServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession misession = (HttpSession) request.getSession();

		ArrayList<Tarea> lisTareasUser = new ArrayList<Tarea>();		

		String idProyecto = request.getParameter("idProyecto");

		if (idProyecto != null) {

			Tarea[] listaTarea = null;

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

			try {
				listaTarea = new Tarea[] { 
						new Tarea("Proyecto de Gestion Ambiental 01", "Tarea 01", "Ricardo", "01"),
						new Tarea("Proyecto de Gestion Ambiental 01", "Tarea 02", "Ricardo", "02"),
						new Tarea("Proyecto de Gestion Ambiental 01", "Tarea 03", "Ricardo", "03"),
						new Tarea("Proyecto de Gestion Ambiental 01", "Tarea 04", "Ricardo", "04"),
						new Tarea("Proyecto de Gestion Ambiental 02", "Tarea 05", "Juana", "05")};

			} catch (Exception e) {
				// TODO: handle exception
			}

			/*for (Tarea tarea : listaTarea) {
				if (tarea.getTarea().toLowerCase().equals(idProyecto.toLowerCase())) {
					lisTareasUser.add(tarea);
				}
			}*/


			request.setAttribute("proyectoUserAMostrar",  getProyecto(idProyecto.toLowerCase()));

			request.setAttribute("listaTareasMostrar", listaTarea);

			request.getRequestDispatcher("plantilla_proyectos_info.jsp").forward(request, response);

		} else {

			misession.invalidate();
			response.sendRedirect("login");

		}
	}

	private Proyecto getProyecto(String idProyecto) {
		Proyecto proyectoUserAMostrar = null;

		Proyecto[] listaProyectos = null;

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		try {
			listaProyectos = new Proyecto[] {
					new Proyecto("ricardo@r.es", "Proyecto de Gestion Ambiental 01", "Ricardo",
							formatter.parse("01-01-2017"), formatter.parse("2018-12-01"), true),
					new Proyecto("ricardo@r.es", "Proyecto de Gestion Documental 01", "Ricardo",
							formatter.parse("01-01-2017"), formatter.parse("2018-12-01"), false),
					new Proyecto("juana@j.es", "Proyecto de Gestion Historica 01", "Juana",
							formatter.parse("01-01-2017"), formatter.parse("2018-12-01"), true),
					new Proyecto("juana@j.es", "Proyecto de Gestion Ambiental 02", "Juana",
							formatter.parse("01-01-2017"), formatter.parse("2018-12-01"), true),
					new Proyecto("luis@l.es", "Proyecto de Gestion Documental 02", "Luis",
							formatter.parse("01-01-2017"), formatter.parse("2018-12-01"), false),
					new Proyecto("luis@l.es", "Proyecto de Gestion Historica 02", "Luis",
							formatter.parse("01-01-2017"), formatter.parse("2018-12-01"), true) };

		} catch (Exception e) {
			// TODO: handle exception
		}

		for (Proyecto maq : listaProyectos) {
			if (maq.getTitulo().toLowerCase().equals(idProyecto.toLowerCase())) {
				proyectoUserAMostrar=maq;
			}
		}
		
		
		return proyectoUserAMostrar;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
