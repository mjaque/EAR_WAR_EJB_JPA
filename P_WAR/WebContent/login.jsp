<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<%@include file="html/header.html"%>
	<title>ModeloApp</title>
</head>

<body>

	<div class="container">
		<div class="header clearfix">
			<nav>
				<ul class="nav nav-pills pull-right">
					<li role="presentation" class="active"><a href="#">Home</a></li>
					<li role="presentation"><a href="#">About</a></li>
					<li role="presentation"><a href="#">Contact</a></li>
				</ul>
			</nav>
			<h3 class="text-muted">Project name</h3>
		</div>

		<div class="jumbotron">
			<h1>Login</h1>
			<%
				String error = (String) request.getAttribute("error");
				if (error != null) {
			%>
			<div class="alert alert-danger alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>Error de Login:</strong> El usuario y la clave no coinciden.
			</div>
			<%
				}
			%>

			<form>
				<input type="hidden" name="accion" value="login" />
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><span
						class="glyphicon glyphicon-user" aria-hidden="true"></span></span> <input
						type="text" class="form-control" placeholder="Usuario"
						name="usuario" aria-describedby="basic-addon1">
				</div>
				<br />
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><span
						class="glyphicon glyphicon-eye-close" aria-hidden="true"></span></span> <input
						type="password" class="form-control" placeholder="Clave"
						name="clave" aria-describedby="basic-addon1">
				</div>
				<br />
				<button type="submit" class="btn btn-primary btn-lg">Login</button>
				<p style="text-align:right"><a href="?accion=ir_registro">Registro de nuevo usuario</a></p>
			</form>
		</div>
		<footer class="footer">
			<p>&copy; 2016 Academia 19e37.com</p>
			<p>Versión de compilación: 1</p>
		</footer>
	</div> <!-- /container -->

	<%@include file="html/javascript.html"%>
</body>
</html>