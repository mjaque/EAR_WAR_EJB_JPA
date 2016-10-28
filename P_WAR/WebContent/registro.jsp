<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<%@include file="html/header.html"%>
	<title>ModeloWebApp - Registro de Usuario</title>
</head>

<body>

	<div class="container">
		<div class="header clearfix">
			<h3 class="text-muted">Project name</h3>
		</div>

		<div class="jumbotron">
			<h1>Registro de Usuario</h1>
			<%
				String error = (String) request.getAttribute("error");
				if (error != null) {
			%>
			<div class="alert alert-danger alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>Error:</strong> <%= error %>
			</div>
			<%
				}
			%>
			<form>
				<input type="hidden" name="accion" value="registro" />
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
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><span
						class="glyphicon glyphicon-eye-close" aria-hidden="true"></span></span> <input
						type="password" class="form-control" placeholder="Repite tu clave"
						name="claveRepetida" aria-describedby="basic-addon1">
				</div>
				<br />
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1">@</span> <input
						type="email" class="form-control" placeholder="Email"
						name="email" aria-describedby="basic-addon1">
				</div>
				<br />
				<p>Al pulsar el botón aceptas las condiciones criminales que te imponemos.</p>
				<button type="submit" class="btn btn-primary btn-lg">Alta</button>
			</form>
		</div>
		<footer class="footer">
			<p>&copy; 2016 Academia 19e37.com</p>
		</footer>
	</div> <!-- /container -->

	<%@include file="html/javascript.html"%>
</body>
</html>