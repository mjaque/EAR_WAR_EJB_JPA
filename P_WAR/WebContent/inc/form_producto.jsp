<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="dominio.Producto"%>
<%@ page import="java.math.BigDecimal"%>
<%
	String titulo = "";
	String descripcion = "";
	String urlFoto = "";
	BigDecimal precio = null;
	Producto.Categoria categoria;

	if (request.getSession().getAttribute("producto") != null) {
		//Completar los datos del producto.
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
		value="null">
</div>
<br />
<div class="input-group dropdown">
	<span class="input-group-addon" id="basic-addon1"><span
		class="glyphicon glyphicon-tasks" aria-hidden="true"></span></span>
	<span>Categoría: </span>
	<select name="categoria">
		<option value="Vehiculos">Vehículos</option>
		<option value="Moda">Moda</option>
		<option value="Electrodomesticos">Electrodomésticos</option>
		<option value="Libros">Libros</option>
	</select>
</div>
<br />
<div class="input-group">
	<span class="input-group-addon" id="basic-addon1"><span
		class="glyphicon glyphicon-pencil" aria-hidden="true"></span> </span>
	<textarea name="descripcion"></textarea>

</div>
<br />
<div class="input-group">
	<span class="input-group-addon" id="basic-addon1">Foto:</span><input
		type="file" id="foto" name="foto" aria-describedby="basic-addon1" value="null">
</div>