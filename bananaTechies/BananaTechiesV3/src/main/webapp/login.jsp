<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="es">
<c:set var="errorclass" value='${mierror?"errorP":""}' />

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Banana GEST Login</title>
<link href="https://fonts.googleapis.com/css?family=Rock+Salt"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700"
	rel="stylesheet">

<link rel="stylesheet" href="./style/login.css">


</head>
<body>
	<div class="box1">
		<header>
			<h1>Banana GEST</h1>
			<h2>Log In</h2>
		</header>

		<section>
			<c:if test="${not empty mierror}">
				<div class="alert alert-danger">${mierror}</div>
			</c:if>

			<form action="login" method="post" id="loginForm" novalidate>
				<div>

					<label for="email">Email: </label> <input type="email" name="Email"
						id="email" placeholder="email@ejemplo.com" required>
					<div class="mensajes_error">
						<div id="email[valueMissing]" class="${errorclass}">Introduzca sus datos.</div>
						<div id="email[typeMismatch]" class="${errorclass}">El formato no se corresponde con un email.</div>
					</div>
				</div>
				<div>
					<label for="password">Password: </label> 
					<input type="password" name="Password" id="password" placeholder="password" minlength="6"
						pattern="[a-z 0-9]*" required>
					<div class="mensajes_error">
						<div id="pass[valueMissing]" class="${errorclass}">Introduzca sus datos.</div>
						<div id="pass[tooShort]" class="${errorclass}">El password debe contener como minimo 6 caracteres.</div>
					</div>
				</div>
				<div>
					<input class="btnLogin" type="submit" form="loginForm" value="Login" id="btnLoginId"/>
				</div>
			</form>
			<a href="forgotPass.html">Has olvidado tu Password?</a>
		</section>
	</div>
</body>
<!-- <script src="./js/login.js"></script> -->
<script src="js/login01.js"></script>

</html>