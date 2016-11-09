<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<html lang="es">
<head>
<%@include file="inc/header.html"%>
<title>Práctica 1 TIW - Búsqueda Avanzada de Producto</title>
</head>

<body>

	<div class="container">
		<div class="header clearfix">
			<%@include file="inc/nav_usuario.html"%>
			<h3 class="text-muted">Práctica 1 TIW</h3>
		</div>

		<div class="jumbotron">
			<h1>Búsqueda Avanzada</h1>
			<%@include file="inc/mensajes.html"%>
			<form method="post" action="?accion=buscar_productos">
				<input type="hidden" name="accion" value="buscar_productos" />

				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><span
						class="glyphicon glyphicon-bullhorn" aria-hidden="true"></span> </span> <input
						type="text" class="form-control" placeholder="Título del Producto"
						name="titulo" aria-describedby="basic-addon1" value="">
				</div>
				<br />
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><span
						class="glyphicon glyphicon-euro" aria-hidden="true"></span></span> <input
						type="number" min="0" max="999999" step="0.01"
						class="form-control" placeholder="Precio" name="precio"
						aria-describedby="basic-addon1" value="">
				</div>
				<br />
				<div class="input-group dropdown">
					<span class="input-group-addon" id="basic-addon1"><span
						class="glyphicon glyphicon-tasks" aria-hidden="true"></span></span> <span>Categoría:
					</span> <select name="categoria">
						<option>Cualquiera</option>
						<option value="Vehiculos">Vehículos</option>
						<option value="Moda">Moda</option>
						<option value="Electrodomesticos">Electrodomésticos</option>
						<option value="Libros">Libros</option>
					</select>
				</div>
				<br />
				<div class="input-group dropdown">
					<span class="input-group-addon" id="basic-addon1"><span
						class="glyphicon glyphicon-tags" aria-hidden="true"></span></span> <span>Estado:
					</span> <select name="estado">
						<option>Cualquiera</option>
						<option value="Disponible">Disponible</option>
						<option value="Reservado">Reservado</option>
						<option value="Vendido">Vendido</option>
					</select>
				</div>
				<br />
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><span
						class="glyphicon glyphicon-pencil" aria-hidden="true"></span> </span>
					<textarea name="descripcion"></textarea>

				</div>
				<br />
				<button type="submit" class="btn btn-primary btn-lg">Buscar</button>
			</form>
		</div>
		<%@include file="inc/footer.html"%>
	</div>
	<!-- /container -->

	<%@include file="inc/javascript.html"%>
</body>
</html>