<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dominio.Producto"%>
<%@ page import="dominio.Usuario"%>
<%@ page import="dao.DAOProducto"%>
<%@ page import="java.math.BigDecimal"%>
<%
	Integer id = null;
	String titulo = "";
	String descripcion = "";
	String urlFoto = "";
	BigDecimal precio = new BigDecimal(0.0);
	Producto.Categoria categoria = Producto.Categoria.Vehiculos;
	Producto.Estado estado = Producto.Estado.Disponible;
	Integer idUsuario = null;

	System.out.println("TRON: form_producto.jsp. idProducto = " + request.getParameter("idProducto"));
	if (request.getParameter("idProducto") != null) {
		DAOProducto dao = new DAOProducto();
		Producto producto = dao.getProducto(Integer.valueOf(request.getParameter("idProducto")));
		id = producto.getId();
		titulo = producto.getTitulo();
		descripcion = producto.getDescripcion();
		urlFoto = producto.getUrlFoto();
		precio = producto.getPrecio();
		categoria = producto.getCategoria();
		estado = producto.getEstado();
		idUsuario = producto.getUsuario().getId();
		%>
		<input type="hidden" id="idProducto" name="idProducto" value="<%=id%>"/>
		<%
	}
	if (((Usuario)request.getSession().getAttribute("usuario")).isAdmin()) {
		%>
		<div class="input-group">
			<span class="input-group-addon" id="basic-addon1"><span
			class="glyphicon glyphicon-user" aria-hidden="true"></span> </span> <input
			type="text" class="form-control" placeholder="idUsuario"
			name="idUsuario" aria-describedby="basic-addon1" value="<%=idUsuario%>">
		</div>
		<br/>
		<%
	}
%>
<div class="input-group">
	<span class="input-group-addon" id="basic-addon1"><span
		class="glyphicon glyphicon-bullhorn" aria-hidden="true"></span> </span> <input
		type="text" class="form-control" placeholder="Título del Producto"
		name="titulo" aria-describedby="basic-addon1" value="<%=titulo%>">
</div>
<br />
<div class="input-group">
	<span class="input-group-addon" id="basic-addon1"><span
		class="glyphicon glyphicon-euro" aria-hidden="true"></span></span> <input
		type="number" min="0" max="999999" step="0.01" class="form-control"
		placeholder="Precio" name="precio" aria-describedby="basic-addon1"
		value="<%=precio%>">
</div>
<br />
<div class="input-group dropdown">
	<span class="input-group-addon" id="basic-addon1"><span
		class="glyphicon glyphicon-tasks" aria-hidden="true"></span></span>
	<span>Categoría: </span>
	<select name="categoria">
		<option value="Vehiculos"
		<%
		if (categoria.equals(Producto.Categoria.Vehiculos)){%>
			selected 
		<%} %>
		>Vehículos</option>
		<option value="Moda"
		<%
		if (categoria.equals(Producto.Categoria.Moda)){%>
			selected
		<%} %>
		>Moda</option>
		<option value="Electrodomesticos"
		<%
		if (categoria.equals(Producto.Categoria.Electrodomesticos)){%>
			selected
		<%} %>
		>Electrodomésticos</option>
		<option value="Libros"
		<%
		if (categoria.equals(Producto.Categoria.Libros)){%>
			selected
		<%} %>
		>Libros</option>
	</select>
</div>
<br />
<div class="input-group dropdown">
	<span class="input-group-addon" id="basic-addon1"><span
		class="glyphicon glyphicon-tags" aria-hidden="true"></span></span>
	<span>Estado: </span>
	<select name="estado">
		<option value="Disponible"
		<%
		if (estado.equals(Producto.Estado.Disponible)){%>
			selected 
		<%} %>
		>Disponible</option>
		<option value="Reservado"
		<%
		if (estado.equals(Producto.Estado.Reservado)){%>
			selected
		<%} %>
		>Reservado</option>
		<option value="Vendido"
		<%
		if (estado.equals(Producto.Estado.Vendido)){%>
			selected
		<%} %>
		>Vendido</option>
	</select>
</div>
<br />
<div class="input-group">
	<span class="input-group-addon" id="basic-addon1"><span
		class="glyphicon glyphicon-pencil" aria-hidden="true"></span> </span>
	<textarea name="descripcion"><%=descripcion%></textarea>

</div>
<br />
<div class="input-group">
	<span class="input-group-addon" id="basic-addon1">Foto:</span><input
		type="file" id="foto" name="foto" aria-describedby="basic-addon1" value="<%=urlFoto%>">
</div>