var myVar;
var inicio = 0;
var peticion;
var seccionEliminada;
function inicializa_xhr() {
	if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
}
function redi(uri) {
	window.location.href = uri;
}
function moverScroll(direccion) {
	var posicion = document.getElementById("posicionScroll");
	inicio = posicion.scrollTop;
	if (direccion == "0") {
		myVar = setInterval(baja, 40);
	}
	if (direccion == "1") {
		myVar = setInterval(sube, 40);
	}
}
function baja() {
	inicio += 0.1;
	window.scrollBy(0, inicio);
}
function sube() {
	inicio -= 0.1;
	window.scrollBy(0, inicio);
}
function stopScroll() {
	clearInterval(myVar);
}

function eliminar(codigo) {
	if (confirm("¿Desea cancelar esta Seccion?")) {
		var data = "seccion=" + encodeURIComponent(codigo);
		seccionEliminada=codigo;
		peticion = inicializa_xhr();
		peticion.onreadystatechange = eliminaSeccion;
		peticion.open("POST", "eliminaSeccion", true);

		peticion.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		peticion.send(data);
	} else {
		return false;
	}
}
function eliminaSeccion() {
	var rpta = peticion.responseText;	
	if (rpta == "1") {
		var div = document.getElementById("codigo"+seccionEliminada);
		div.style.display="none"
		toastr.info("Se ha desactivado la seccion seleccionada");
	}
	if (rpta == "0") {
		toastr.error("Lo sentimos, Tuvimos problemas para desactivar la seccion");
	}
}
function generaSesion(idSeccion){
	var data = "idSeccion=" + encodeURIComponent(idSeccion);	
	peticion = inicializa_xhr();
	peticion.onreadystatechange = rptaGeneraSesion;
	peticion.open("POST", "generaSesion", true);

	peticion.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	peticion.send(data);
}
function rptaGeneraSesion(){
	var rpta = peticion.responseText;	
	if (rpta == "1") {
		location.href="publicaciones";
	}
	if (rpta == "0") {
		toastr.error("Lo sentimos, Tuvimos problemas para acceder a la seccion");
	}
}
function obtieneInformaciones(id){
	
}