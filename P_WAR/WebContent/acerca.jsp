<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dominio.Usuario"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<%@include file="inc/header.html"%>
	<title>Práctica 1 TIW - Acerca De</title>
</head>

<body>

	<div class="container">
		<div class="header clearfix">
		<% if (request.getSession().getAttribute("usuario") != null) {
			if (((Usuario)request.getSession().getAttribute("usuario")).isAdmin()){ %>
				<%@include file="inc/nav_admin.html" %>
			<%}else{ %>
				<%@include file="inc/nav_usuario.html" %>
		<%	}
		}else{ %>
			<%@include file="inc/nav_nologin.html" %>
		<%} %>
			<h3 class="text-muted">Práctica 1 TIW</h3>
		</div>

		<div class="jumbotron">
			<h1>Acerca De:</h1>
			<p>Bla, bla, bla...</p>
		</div>
		<%@include file="inc/footer.html"%>
		<p>áéíóú ñÑ üÜ çÇ</p>
	</div> <!-- /container -->

	<%@include file="inc/javascript.html"%>
</body>
</html>