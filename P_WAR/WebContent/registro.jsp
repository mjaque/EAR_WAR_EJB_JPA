<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<%@include file="inc/header.html"%>
	<title>Práctica 1 TIW - Registro de Usuario</title>
</head>

<body>

	<div class="container">
		<div class="header clearfix">
			<%@include file="inc/nav_nologin.html" %>
			<h3 class="text-muted">Práctica 1 TIW</h3>
		</div>

		<div class="jumbotron">
			<h1>Registro de Usuario</h1>
			<%@include file="inc/mensajes.html"%>
			<form>
				<input type="hidden" name="accion" value="registro" />
				<jsp:include page="inc/form_usuario.jsp" />
				<br />
				<p>Al pulsar el botón aceptas las condiciones criminales que te
					imponemos.</p>
				<button type="submit" class="btn btn-primary btn-lg">Alta</button>
			</form>
		</div>
		<%@include file="inc/footer.html"%>
	</div>
	<!-- /container -->

	<%@include file="inc/javascript.html"%>
</body>
</html>