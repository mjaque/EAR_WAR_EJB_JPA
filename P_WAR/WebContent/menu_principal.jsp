<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="es">
<head>
	<%@include file="html/header.html"%>
	<title>Práctica 1 TIW - Menú Principal</title>
</head>

<body>

	<div class="container">
		<div class="header clearfix">
			<nav>
				<ul class="nav nav-pills pull-right">
					<li role="presentation" class="active"><a href="#">Home</a></li>
					<li role="presentation"><a href="#">About</a></li>
					<li role="presentation"><a href="#">Contact</a></li>
				</ul>
			</nav>
			<h3 class="text-muted">Práctica 1 TIW</h3>
		</div>

		<div>
			<h1>Menú Principal</h1>
		</div>
		<%@include file="html/footer.html"%>
	</div> <!-- /container -->

	<%@include file="html/javascript.html"%>
</body>
</html>