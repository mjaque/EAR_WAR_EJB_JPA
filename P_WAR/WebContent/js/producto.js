$(document).ready(iniciar);

function iniciar() {
	$("#btnBaja").click(baja)
};

function baja(){
	if (confirm("¿Realmente desea dar de baja este producto?")){
		window.open("?accion=baja_producto&id=" + document.getElementById("idProducto").value);
	}
}