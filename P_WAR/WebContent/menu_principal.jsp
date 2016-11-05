<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<html lang="es">
<head>
<%@include file="inc/header.html"%>
<link rel="stylesheet" href="css/listado_productos.css" media="screen">
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
			<form>
				<input type="hidden" name="accion" value="buscar" />
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><span
						class="glyphicon glyphicon-search" aria-hidden="true"></span></span> <input
						type="text" class="form-control" placeholder="Buscar..." name="criterio"
						aria-describedby="basic-addon1">
				</div>
			</form>
			<div class="container page-header">
				<jsp:include page="inc/listar_productos.jsp" />
			</div>
			<%@include file="inc/footer.html"%>
		</div>
		<!-- /container -->

		<%@include file="inc/javascript.html"%>
</body>
</html>