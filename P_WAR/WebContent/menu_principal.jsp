<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<html lang="es">
<head>
	<%@include file="inc/header.html"%>
	<title>Práctica 1 TIW - Menú Principal</title>
</head>

<body>

	<div class="container">
		<div class="header clearfix">
			<%@include file="inc/nav_usuario.html"%>
			<h3 class="text-muted">Práctica 1 TIW</h3>
		</div>

		<div>
			<h1>Menú Principal</h1>
			<%@include file="inc/mensajes.html"%>
		</div>
		<%@include file="inc/footer.html"%>
	</div> <!-- /container -->

	<%@include file="inc/javascript.html"%>
</body>
</html>