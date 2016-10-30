<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<%@include file="html/header.html"%>
	<title>Pr·ctica 1 TIW - Login</title>
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
			<h3 class="text-muted">Pr·ctica 1 TIW</h3>
		</div>

		<div class="jumbotron">
			<h1>Login</h1>
			<%@include file="html/error.html"%>

			<form>
				<input type="hidden" name="accion" value="login" />
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><span
						class="glyphicon glyphicon-user" aria-hidden="true"></span></span> <input
						type="text" class="form-control" placeholder="Email"
						name="email" aria-describedby="basic-addon1">
				</div>
				<br />
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><span
						class="glyphicon glyphicon-eye-close" aria-hidden="true"></span></span> <input
						type="password" class="form-control" placeholder="Clave"
						name="clave" aria-describedby="basic-addon1">
				</div>
				<br />
				<button type="submit" class="btn btn-primary btn-lg">Login</button>
				<p style="text-align:right"><a href="?accion=ir_registro">Registro de nuevo usuario</a></p>
			</form>
		</div>
		<%@include file="html/footer.html"%>
		<p>·ÈÌÛ˙ Ò— ¸‹ Á«</p>
	</div> <!-- /container -->

	<%@include file="html/javascript.html"%>
</body>
</html>