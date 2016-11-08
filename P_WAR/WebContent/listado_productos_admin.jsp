<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
	<%@ page import="dominio.Producto"%>
	<%@ page import="dominio.Usuario"%>
	<%@ page import="java.util.List"%>
	
<html lang="es">
<head>
<%@include file="inc/header.html"%>
<link rel="stylesheet" href="css/listado_productos.css" media="screen">
<title>Práctica 1 TIW - Listado de Productos</title>
</head>

<body style="background-color: #F78181">

	<div class="container">
		<div class="header clearfix">
			<%@include file="inc/nav_admin.html"%>
			<h3 class="text-muted">Práctica 1 TIW</h3>
		</div>

		<div class="jumbotron">
			<h1>Listado de Productos</h1>
			<%@include file="inc/mensajes.html"%>
			<div class="container page-header">
				<%
					List<Producto> listadoProductos = (List<Producto>) (request.getAttribute("listadoProductos"));
					if (listadoProductos != null) {
				%>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Id</th>
							<th>Título</th>
							<th>Descripción</th>
							<th>UrlFoto</th>
							<th>Precio</th>
							<th>Id Usuario</th>
						</tr>
					</thead>
					<tbody>
				<%							
						for (Producto prod : listadoProductos) {
				%>
						<tr>
							<td><%=prod.getId()%></td>
							<td><%=prod.getTitulo()%></td>
							<td><%=prod.getDescripcion()%></td>
							<td><%=prod.getUrlFoto()%></td>
							<td><%=prod.getPrecio()%></td>
							<td><%=prod.getUsuario().getId()%></td>
							<td>
							<a href="?accion=ir_modificar_producto&idProducto=<%=prod.getId()%>"><span class="glyphicon glyphicon-edit" aria-hidden="true" title="editar"></span></a>
							&nbsp;
							<a href="?accion=baja_producto&idProducto=<%=prod.getId()%>"><span class="glyphicon glyphicon-trash" aria-hidden="true" title="eliminar"></span></a>
							</td>
						</tr>
				<%
						} //for
				%>
					</tbody>
				</table>
				<%
				} //if(listadoProductos...
				%>
			</div>
			<%@include file="inc/footer.html"%>
		</div>
	</div>
		<!-- /container -->

		<%@include file="inc/javascript.html"%>
</body>
</html>