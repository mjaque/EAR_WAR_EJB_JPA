<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<html lang="es">
<head>
	<%@include file="inc/header.html"%>
	<title>Práctica 1 TIW - Perfil de Usuario</title>
</head>

<body>

	<div class="container">
		<div class="header clearfix">
			<%@include file="inc/nav_usuario.html"%>
			<h3 class="text-muted">Práctica 1 TIW</h3>
		</div>

		<div class="jumbotron">
			<h1>Perfil de Usuario</h1>
			<%@include file="inc/mensajes.html"%>
			<p>Aquí puedes modificar tu información de perfil</p>
			<form>
				<input type="hidden" name="accion" value="perfil" />
				<%@include file="inc/form_usuario.jsp"%>
				<br />
				<button type="submit" class="btn btn-primary btn-lg">Modificar</button>
			</form>
			<p style="text-align:right"><button id="btnBaja" type="button" class="btn-xs btn-danger">Baja</button></p>
		</div>
		<%@include file="inc/footer.html"%>
	</div> <!-- /container -->

	<%@include file="inc/javascript.html"%>
	<script src="js/perfil.js"></script>
</body>
</html>