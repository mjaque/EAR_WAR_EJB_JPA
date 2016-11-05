<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<html lang="es">
<head>
<%@include file="inc/header.html"%>
<link rel="stylesheet" href="css/listado_productos.css" media="screen">
<title>Práctica 1 TIW - Listado de Productos</title>
</head>

<body>

	<div class="container">
		<div class="header clearfix">
			<%@include file="inc/nav_usuario.html"%>
			<h3 class="text-muted">Práctica 1 TIW</h3>
		</div>

		<div>
			<h1>Listado de Productos</h1>
			<%@include file="inc/mensajes.html"%>
			<div class="container page-header">
				<jsp:include page="inc/listar_productos.jsp" />
			</div>
			<%@include file="inc/footer.html"%>
		</div>
		<!-- /container -->

		<%@include file="inc/javascript.html"%>
</body>
</html>