$(document).ready(iniciar);

function iniciar() {
	$("#btnBaja").click(baja)
};

function baja(){
	if (confirm("¿Realmente desea darse de baja de la aplicación?")){
		window.open("?accion=baja");
	}
}