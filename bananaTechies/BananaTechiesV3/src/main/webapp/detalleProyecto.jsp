<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


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
			<div class="pageTitle ">
				<h1>${Proyecto.titulo}</h1>
			</div>
			<article class="row">
				<div class="buttons pull-xs-right">
					<a href="home.html" class="btn"><span
						class="glyphicon glyphicon-trash"></span> Borrar Proyecto</a> <a
						href="editP.html" class="btn">Editar Proyecto</a> <a
						href="createT.html" class="btn">Crear Tarea</a>
				</div>
			</article>
			<aside class="col-xs-12 col-sm-3">
				<div class="col-xs-12">
					<ul class="pInfo">
						<li>Fecha de inicio:${Proyecto.getFechaInicio()}</li>
						<li>Fecha de Fin:${Proyecto.getFechaFin()}</li>
						
						<li>Asignado a: ${idUsuario.nombre}</li>
						<li>Status: ${Proyecto.getStatus()}</li>
					</ul>
				</div>
				<div class="panel panel-default col-xs-12">
					<div class="panel-heading">
						<h3 class="panel-title">Tareas</h3>
					</div>
					<div class="panel-body">
						<ul class="taskList">
							<c:forEach var="t" items="${listaTareasAMostrar}" varStatus="counter">
								<li class="row" id="${t.idt}">
									<div class="col-xs-6">
										<a href="task.html">${t.titulo}</a>
									</div>
									<div class="col-xs-6">
										<button aria-label="deleteTask"
											class="btn_del_Task glyphicon glyphicon-trash"
											data_Id="${t.idt}"></button>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</aside>
			<article class="col-xs-12 col-sm-9">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Descripción</h3>
					</div>
					<div class="panel-body">
						<div class="box1 textBox">
							<p>${Proyecto.getDescripcion()}</p>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Notas y comentarios</h3>
					</div>
					<div class="panel-body">
						<div class="box1 textBox">
							<p>${Proyecto.getNotas()}</p>
						</div>
					</div>
				</div>
			</article>
		</section>
	</div>
</body>
</html>