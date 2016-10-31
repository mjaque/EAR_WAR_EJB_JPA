<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<%@include file="inc/header.html"%>
	<title>Pr�ctica 1 TIW - Perfil de Usuario</title>
</head>

<body>

	<div class="container">
		<div class="header clearfix">
			<%@include file="inc/nav_usuario.html"%>
			<h3 class="text-muted">Pr�ctica 1 TIW</h3>
		</div>

		<div class="jumbotron">
			<h1>Perfil de Usuario</h1>
			<%@include file="inc/mensajes.html"%>
			<p>Aqu� puedes modificar tu informaci�n de perfil</p>
			<form>
				<input type="hidden" name="accion" value="perfil" />
				<%@include file="inc/form_usuario.jsp"%>
				<br />
				<button type="submit" class="btn btn-primary btn-lg">Modificar</button>
			</form>
		</div>
		<%@include file="inc/footer.html"%>
	</div> <!-- /container -->

	<%@include file="inc/javascript.html"%>
</body>
</html>