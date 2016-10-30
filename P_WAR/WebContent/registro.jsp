<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<%@include file="html/header.html"%>
	<title>Práctica 1 TIW - Registro de Usuario</title>
</head>

<body>

	<div class="container">
		<div class="header clearfix">
			<h3 class="text-muted">Práctica 1 TIW</h3>
		</div>

		<div class="jumbotron">
			<h1>Registro de Usuario</h1>
			<%@include file="html/error.html"%>
			<form>
				<input type="hidden" name="accion" value="registro" />
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1">@</span> <input
						type="email" class="form-control" placeholder="Email"
						name="email" aria-describedby="basic-addon1">
				</div>
				<br />
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><span
						class="glyphicon glyphicon-eye-close" aria-hidden="true"></span></span> <input
						type="password" class="form-control" placeholder="Clave"
						name="clave" aria-describedby="basic-addon1">
					<span class="input-group-addon" id="basic-addon1"><span
						class="glyphicon glyphicon-eye-close" aria-hidden="true"></span></span> <input
						type="password" class="form-control" placeholder="Repite tu clave"
						name="claveRepetida" aria-describedby="basic-addon1">
				</div>
				<br />
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><span
						class="glyphicon glyphicon-eye-close" aria-hidden="true"></span></span> <input
						type="texto" class="form-control" placeholder="Nombre"
						name="nombre" aria-describedby="basic-addon1">
					<span class="input-group-addon" id="basic-addon1"><span
						class="glyphicon glyphicon-eye-close" aria-hidden="true"></span></span> <input
						type="texto" class="form-control" placeholder="Apellidos"
						name="apellidos" aria-describedby="basic-addon1">					
				</div>
				<br />
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1">@</span> <input
						type="text" class="form-control" placeholder="Ciudad de Residencia"
						name="ciudad" aria-describedby="basic-addon1">
				</div>
				<br />
				<p>Al pulsar el botón aceptas las condiciones criminales que te imponemos.</p>
				<button type="submit" class="btn btn-primary btn-lg">Alta</button>
			</form>
		</div>
		<%@include file="html/footer.html"%>
	</div> <!-- /container -->

	<%@include file="html/javascript.html"%>
</body>
</html>