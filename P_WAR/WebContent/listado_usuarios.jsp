<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
	<%@ page import="dominio.Usuario"%>
	<%@ page import="java.util.List"%>
	
<html lang="es">
<head>
<%@include file="inc/header.html"%>
<link rel="stylesheet" href="css/listado_productos.css" media="screen">
<title>Práctica 1 TIW - Listado de Usuarios</title>
</head>

<body style="background-color: #F78181">

	<div class="container">
		<div class="header clearfix">
			<%@include file="inc/nav_admin.html"%>
			<h3 class="text-muted">Práctica 1 TIW</h3>
		</div>

		<div class="jumbotron">
			<h1>Listado de Usuarios</h1>
			<%@include file="inc/mensajes.html"%>
			<div class="container page-header">
				<%
					List<Usuario> listadoUsuarios = (List<Usuario>) (request.getAttribute("listadoUsuarios"));
					if (listadoUsuarios != null) {
				%>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Id</th>
							<th>Nombre</th>
							<th>Apellidos</th>
							<th>Email</th>
							<th>Ciudad</th>
							<th>Acciones</th>
						</tr>
					</thead>
					<tbody>
				<%							
						for (Usuario usu : listadoUsuarios) {
				%>
						<tr>
							<td><%=usu.getId()%></td>
							<td><%=usu.getNombre()%></td>
							<td><%=usu.getApellidos()%></td>
							<td><%=usu.getEmail()%></td>
							<td><%=usu.getCiudad()%></td>
							<td>
							<a href="?accion=ir_modificar_usuario&idUsuario=<%=usu.getId()%>"><span class="glyphicon glyphicon-edit" aria-hidden="true" title="editar"></span></a>
							&nbsp;
							<a href="?accion=baja_usuario&idUsuario=<%=usu.getId()%>"><span class="glyphicon glyphicon-trash" aria-hidden="true" title="eliminar"></span></a>
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