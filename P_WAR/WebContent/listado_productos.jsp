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
				<%@ page import="dominio.Usuario"%>
				<%@ page import="dominio.Producto"%>
				<%@ page import="java.util.Set"%>
				<%
					Set<Producto> listadoProductos = (Set<Producto>) (request.getAttribute("listadoProductos"));
					int prodCont = 0;
					if (listadoProductos != null) {
						for (Producto prod : listadoProductos) {
							if (prodCont % 4 == 0) {
				%>
				<div class="row">
					<%
						}
							prodCont++;
					%>
					<div class="col-md-3 producto">
						<a class="producto"
							href="?accion=ir_producto&id=<%=prod.getId()%>"> <img
							class="producto" src="<%=prod.getUrlFoto()%>"
							alt="<%=prod.getDescripcion()%>" title="<%=prod.getDescripcion()%>" />
						</a>
						<div class="datosproducto">
							<p class="producto titulo"><%=prod.getTitulo()%></p>
							<p>
								<span class="producto categoria"><%=prod.getCategoria()%></span>
								<span class="producto precio"><%=prod.getPrecio()%>€</span>
							</p>
						</div>
					</div>
					<%
						if (prodCont % 4 == 0) { //Cierre de <div class="row">
					%>
				</div>
				<%
							}//if(prodCont%...
						} //for (Producto...
					} //if(listadoProductos...
				%>
			</div>
			<%@include file="inc/footer.html"%>
		</div>
		<!-- /container -->

		<%@include file="inc/javascript.html"%>
</body>
</html>