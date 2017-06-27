<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<jsp:include page="head.jsp"></jsp:include>
<body>
	<div class="container" id="containerId">
		<jsp:include page="header.jsp"></jsp:include>
		<section class="container-fluid">
			<ol class="breadcrumb">
				<li><a href="home.html">Home</a></li>
				<li class="active">Lista de Tareas</li>
			</ol>
			<h1>
				Listado de Tareas 
			</h1>
			<div class="row">
				<form method="post" id="searchTForm" novalidate>
					<div class="row">
						<div class="col-xs-12 col-sm-4 col-sm-offset-8 searchBox">
							<div class="input-group">
								<span class="input-group-btn buttons">
									<button id="searchTask" class="btn btn-default"
										aria-label="searchBtn">
										<span class="glyphicon glyphicon-search"></span>
									</button>
								</span> <input type="search" class="form-control"
									placeholder="Buscar Tarea..." id="searchBoxT"
									aria-label="searchBox" required>
							</div>
							<div class="mensajes_error_searchBoxT">
								<div id="searchBoxT[valueMissing]" class="errorP">El campo
									Buscar Tarea debe tener valor.</div>
							</div>
							<!-- /input-group -->
						</div>
					</div>

				</form>
				<div class="buttons">
					<a href="createT.html" class="btn glyphicon glyphicon-plus"><span
						class="iconText"> Crear Tarea</span></a>
				</div>
			</div>
			<div class="container-fluid">


				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Ver - Borrar</th>
								<th>ID</th>
								<th>Titulo</th>
								<th>Proyecto</th>
								<th>Responsable</th>
								<th>Fecha inicio</th>
								<th>Status</th>
								<th>Progreso</th>
							</tr>
						</thead>
						<c:forEach var="t" items="${listaTareaAMostrar}"
							varStatus="counter">
							<tbody>
								<tr>
									<td class="buttons">
										<a href="/DetalleTareaServlet?idp=${t.idt}"
										class="btn glyphicon glyphicon-eye-open"
										aria-label="viewTaskBtn" data_Id="${t.idt}"></a> 
										<a href="#"
										class="btn btn_del_Task glyphicon glyphicon-trash"
										aria-label="deleteTaskBtn" data_Id="${t.idt}"></a>
									</td>
									<td>${t.idt}</td>
									<td>${t.titulo}</td>
									<td>${t.proyecto}</td>
									<td>${idUsuario.nombre}</td>
									<td>${t.fechaInicio}</td>
									<td>${t.status}</td>
									<td>${t.progreso}</td>
								</tr>
							</tbody>
						</c:forEach>
					</table>
				</div>

			</div>
		</section>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
<script src="./js/home.js"></script>
<script src="./js/searchP.js "></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</html>