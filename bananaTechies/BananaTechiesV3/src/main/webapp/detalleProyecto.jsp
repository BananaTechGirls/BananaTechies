<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.BananaTechies.models.Proyecto"%>

<!DOCTYPE>
<html lang="es">
<jsp:include page="head.jsp"></jsp:include>
<body>
	<div class="container" id="containerId">
		<jsp:include page="header.jsp"></jsp:include>
		<section class="container-fluid">
			<ol class="breadcrumb">
				<li><a href="home.html">Home</a></li>
				<li class="active">Proyecto</li>
			</ol>

			<article class="row">
				<div class="buttons pull-xs-right">
					<a href="home.html" class="btn"><span
						class="glyphicon glyphicon-trash"></span> Borrar Proyecto</a> <a
						href="editP.html" class="btn">Editar Proyecto</a> <a
						href="createT.html" class="btn">Crear Tarea</a>
				</div>
			</article>

			<aside class="col-xs-12 col-sm-12">
				<div class="col-xs-12">

					<ul class="pInfo">

						<li class="pageTitle "><h1>Titulo:
								${DetalleProyecto.titulo}</h1></li>

						<li>Fecha de inicio: ${DetalleProyecto.fechaInicio}</li>

						<li>Fecha de Fin:${DetalleProyecto.fechaFinal}</li>

						<li>Asignado a: ${idUsuario.nombre}</li>

						<li>Status: ${DetalleProyecto.status}</li>

						<li>Progreso: ${DetalleProyecto.progreso}</li>


					</ul>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Descripción</h3>
					</div>
					<div class="panel-body">
						<div class="box1 textBox">
							<p>${DetalleProyecto.descripcion}</p>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Notas y comentarios</h3>
					</div>
					<div class="panel-body">
						<div class="box1 textBox">
							<p>${DetalleProyecto.notas}</p>
						</div>
					</div>
				</div>


				<div class="panel-heading">
					<h3 class="panel-title">Tareas</h3>
				</div>
				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Ver - Borrar</th>
								<th>Titulo</th>
								<th>Responsable</th>
								<th>Progreso</th>

							</tr>
						</thead>
							<c:forEach var="tar" items="${listaTareaAMostrar}"
								varStatus="counter">
							<tbody>
								<tr>
									<td class="buttons"><a
										href="${tar.idt}"
										class="btn glyphicon glyphicon-eye-open" aria-label="Tareas"></a>
										<a href="#"
										class="btn_del_Task btn glyphicon glyphicon-trash"
										data_Id="${tar.idt}" aria-label="botonBorrarP"></a> 						</td>
									<td>${tar.titulo}</td>
									<td>${idUsuario.nombre}</td>
									<td>${tar.progreso}</td>

								</tr>
							</tbody>

						</c:forEach>
					</table>
				</div>


				<div class="panel-body">
					<div class="container-fluid">
						<ul class="taskList">

							<c:forEach var="tar" items="${listaTareaAMostrar}"
								varStatus="counter">
								<li class="row" id="${tar.idt}">
									<div class="col-xs-6">
										<a href="task.html">${tar.titulo}</a>
									</div>
									<div class="col-xs-6">

										<button aria-label="deleteTask"
											class="btn_del_Task glyphicon glyphicon-trash"
											data_Id="${tar.idt}"></button>
									</div>
								</li>
							</c:forEach>

						</ul>
					</div>
				</div>

			</aside>

		</section>
	</div>
</body>
</html>