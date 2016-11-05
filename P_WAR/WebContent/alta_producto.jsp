<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<html lang="es">
<head>
	<%@include file="inc/header.html"%>
	<title>Práctica 1 TIW - Alta de Producto</title>
</head>

<body>

	<div class="container">
		<div class="header clearfix">
			<%@include file="inc/nav_usuario.html" %>
			<h3 class="text-muted">Práctica 1 TIW</h3>
		</div>

		<div class="jumbotron">
			<h1>Alta de Producto</h1>
			<%@include file="inc/mensajes.html"%>
<!-- 			<form method="post" action="?accion=alta_producto" enctype="multipart/form-data"> -->
				<form method="post" action="?accion=alta_producto" enctype="multipart/form-data">
				<input type="hidden" name="accion" value="alta_producto" />
				<jsp:include page="inc/form_producto.jsp" />
				<div id="gallery"></div>
				<br />
				<button type="submit" class="btn btn-primary btn-lg">Alta</button>
			</form>
		</div>
		<%@include file="inc/footer.html"%>
	</div>
	<!-- /container -->

	<%@include file="inc/javascript.html"%>
</body>
</html>