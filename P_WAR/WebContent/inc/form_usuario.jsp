<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dominio.Usuario"%>
<%
	Integer idUsuario = null;
	String email = "";
	String nombre = "";
	String apellidos = "";
	String clave = "****";
	String ciudad = "";
	Usuario usuario;
	if (request.getAttribute("usuario") != null) {
		usuario = (Usuario) (request.getAttribute("usuario"));
		idUsuario = usuario.getId();
		email = usuario.getEmail();
		nombre = usuario.getNombre();
		apellidos = usuario.getApellidos();
		ciudad = usuario.getCiudad();
	} else
		usuario = new Usuario();
	// 				if (request.getSession().getAttribute("usuario") != null){
	// 					Usuario usuario = (Usuario)(request.getSession().getAttribute("usuario"));
	
	// 				} 
	if (usuario.getId() != null) {
%>
<input type="hidden" name="idUsuario" value="<%=idUsuario%>" />
<%
	}
%>
<div class="input-group">
	<span class="input-group-addon" id="basic-addon1">@</span> <input
		type="email" class="form-control" placeholder="Email" name="email"
		aria-describedby="basic-addon1" value="<%=email%>">
</div>

<br />
<div class="input-group">
	<span class="input-group-addon" id="basic-addon1"><span
		class="glyphicon glyphicon-eye-close" aria-hidden="true"></span></span> <input
		type="password" class="form-control" placeholder="Clave" name="clave"
		aria-describedby="basic-addon1" value="<%=clave%>"> <span
		class="input-group-addon" id="basic-addon1"><span
		class="glyphicon glyphicon-eye-close" aria-hidden="true"></span></span> <input
		type="password" class="form-control" placeholder="Repite tu clave"
		name="claveRepetida" aria-describedby="basic-addon1"
		value="<%=clave%>">
</div>
<br />
<div class="input-group">
	<span class="input-group-addon" id="basic-addon1"><span
		class="glyphicon glyphicon-user" aria-hidden="true"></span></span> <input
		type="texto" class="form-control" placeholder="Nombre" name="nombre"
		aria-describedby="basic-addon1" value="<%=nombre%>"> <span
		class="input-group-addon" id="basic-addon1"><span
		class="glyphicon glyphicon-user" aria-hidden="true"></span></span> <input
		type="texto" class="form-control" placeholder="Apellidos"
		name="apellidos" aria-describedby="basic-addon1"
		value="<%=apellidos%>">
</div>
<br />
<div class="input-group">
	<span class="input-group-addon" id="basic-addon1"><span
		class="glyphicon glyphicon-home" aria-hidden="true"></span></span> <input
		type="text" class="form-control" placeholder="Ciudad de Residencia"
		name="ciudad" aria-describedby="basic-addon1" value="<%=ciudad%>">
</div>