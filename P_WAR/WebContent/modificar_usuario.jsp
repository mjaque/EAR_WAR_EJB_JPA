<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<html lang="es">
<head>
	<%@include file="inc/header.html"%>
	<title>Práctica 1 TIW - Modificar Usuario</title>
</head>

<body style="background-color: #F78181">

	<div class="container">
		<div class="header clearfix">
			<%@include file="inc/nav_admin.html"%>
			<h3 class="text-muted">Práctica 1 TIW</h3>
		</div>

		<div class="jumbotron">
			<h1>Modificar Usuario</h1>
			<%@include file="inc/mensajes.html"%>
			<form>
				<input type="hidden" name="accion" value="modificar_usuario" />
				<jsp:include page="inc/form_usuario.jsp" />
				<br />
				<button type="submit" class="btn btn-primary btn-lg">Modificar</button>
			</form>
		</div>
		<%@include file="inc/footer.html"%>
	</div>
	<!-- /container -->

	<%@include file="inc/javascript.html"%>
</body>
</html>