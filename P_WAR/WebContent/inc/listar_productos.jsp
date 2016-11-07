<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>				
				<%@ page import="dominio.Usuario"%>
				<%@ page import="dominio.Producto"%>
				<%@ page import="java.util.List"%>
				<%
					List<Producto> listadoProductos = (List<Producto>) (request.getAttribute("listadoProductos"));
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
							href="?accion=ir_producto&idProducto=<%=prod.getId()%>"> <img
							class="producto" src="<%=prod.getUrlFoto()%>"
							alt="<%=prod.getDescripcion()%>" title="<%=prod.getDescripcion()%>" />
						</a>
						<div class="datosproducto">
							<p class="producto titulo"><%=prod.getTitulo()%></p>
							<p>
								<span class="producto categoria"><%=prod.getCategoria()%></span>
								<span class="producto estado"><%=prod.getEstado()%></span>
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