<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<%@include file="inc/header.html"%>
	<title>Pr�ctica 1 TIW - Men� Principal</title>
</head>

<body>

	<div class="container">
		<div class="header clearfix">
			<%@include file="inc/nav_usuario.html"%>
			<h3 class="text-muted">Pr�ctica 1 TIW</h3>
		</div>

		<div>
			<h1>Men� Principal</h1>
			<%@include file="inc/mensajes.html"%>
		</div>
		<%@include file="inc/footer.html"%>
	</div> <!-- /container -->

	<%@include file="inc/javascript.html"%>
</body>
</html>