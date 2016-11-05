<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<%@include file="inc/header.html"%>
	<title>Práctica 1 TIW - Login</title>
</head>

<body>

	<div class="container">
		<div class="header clearfix">
			<%@include file="inc/nav_nologin.html" %>
			<h3 class="text-muted">Práctica 1 TIW</h3>
		</div>

		<div class="jumbotron">
			<h1>Login</h1>
			<%@include file="inc/mensajes.html"%>

			<form>
				<input type="hidden" name="accion" value="login" />
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1">@</span> <input
						type="email" class="form-control" placeholder="Email"
						name="email" aria-describedby="basic-addon1">
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
		<%@include file="inc/footer.html"%>
	</div> <!-- /container -->

	<%@include file="inc/javascript.html"%>
</body>
</html>