var codigoProducto = 0;
var READY_STATE_COMPLETE = 4;
var peticion_http = null;
var nuevaPeticion = null;
var numPaginacion = 1;
var AJAX = inicializa_xhr();
var productoo=null;
function inicializa_xhr() {
	if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
}

function createXMLHttpRequest() {
	// See http://en.wikipedia.org/wiki/XMLHttpRequest
	// Provide the XMLHttpRequest class for IE 5.x-6.x:
	if (typeof XMLHttpRequest == "undefined")
		XMLHttpRequest = function() {
			try {
				return new ActiveXObject("Msxml2.XMLHTTP.6.0")
			} catch (e) {
			}
			try {
				return new ActiveXObject("Msxml2.XMLHTTP.3.0")
			} catch (e) {
			}
			try {
				return new ActiveXObject("Msxml2.XMLHTTP")
			} catch (e) {
			}
			try {
				return new ActiveXObject("Microsoft.XMLHTTP")
			} catch (e) {
			}
			throw new Error("This browser does not support XMLHttpRequest.")
		};
	return new XMLHttpRequest();
}

function crea_query_string() {
	var usuario = document.getElementById("usuario");
	var password = document.getElementById("password");

	return "usuario=" + encodeURIComponent(usuario.value) + "&password="
			+ encodeURIComponent(password.value);
}

function valida() {
	var pass = document.getElementById("password").value;
	if (pass.length > 7) {
		document.getElementById("loader").style.display = 'block';
		peticion_http = inicializa_xhr();
		setTimeout(function() {
			if (peticion_http) {
				peticion_http.onreadystatechange = procesaRespuesta;
				peticion_http.open("POST", "validaLogin", true);

				peticion_http.setRequestHeader("Content-Type",
						"application/x-www-form-urlencoded");
				var query_string = crea_query_string();
				peticion_http.send(query_string);
			}
		}, 1000);

	} else {
		alertify.log('Su clave debe contener almenos 8 caracteres');
	}
}
function validaCarrito() {
	peticion_http = inicializa_xhr();
	if (peticion_http) {
		peticion_http.onreadystatechange = procesaRespuestaCarrito;
		peticion_http.open("POST", "mostrarPresentaciones", true);

		peticion_http.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		var query_string2 = query_string("idProducto", codigoProducto);
		peticion_http.send(query_string2);
	}
}
function procesaRespuestaCarrito() {
	if (peticion_http.readyState == READY_STATE_COMPLETE) {
		if (peticion_http.status == 200) {
			var presentacion = eval('(' + peticion_http.responseText + ')');
			document.getElementById("modal-content").innerHTML = "";
			for (index = 0; index < presentacion.length; ++index) {
				var idProducto = codigoProducto;
				var presentacionId = presentacion[index].id;
				//console.log("presentacion: "+presentacionId);
				document.getElementById("modal-content").innerHTML += ""
						+ "<div class='modal-body' id='modal-body'>"
						+ "<div> <br/><p>Codigo "
						+ presentacion[index].id
						+ ": "
						+ presentacion[index].descripcion
						+ "</p>"
						+ "</div>"
						+ "<div Style='position:absolute;'><input id=cantidad"+presentacion[index].id+" style='color: black; width: 72px; margin-left: 194px; margin-top: 5px;' type='number' name='cantidad' min='1' value='1' max='10' /></div>"
						+ "<div id='imagenModal' onclick=sesionSave("
						+ idProducto
						+ ","
						+ presentacionId
						+ ");>"
						+ "<img alt='Carrito de Compras' src='images/carritocompras.png'>"
						+ "</div>" + "</div><br/>";
			}
		} else {
			alertify
					.error('Obtuvimos un Error en el sistema, Intentelo mas tarde!<br/> <p>Gracias</p>');
		}
	}
}

function sesionSave(idProducto, idPresentacion) {
	var cantidad ="cantidad"+idProducto;
	cantidad = document.getElementById(cantidad);
	peticion_http = inicializa_xhr();
	if (peticion_http) {
		peticion_http.onreadystatechange = procesaRespuestaSesionSave;
		peticion_http.open("POST", "agregaCarrito", true);

		peticion_http.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		var productoID = query_string("idProducto", idProducto);
		var presentacionID = query_string("idPresentacion", idPresentacion);
		var cantidadID=query_string("cantidad",cantidad.value);
		peticion_http.send(productoID + "&" + presentacionID+"&"+cantidadID);
	}
}

function procesaRespuestaSesionSave() {
	// Ocultar loader
	if (peticion_http.readyState == READY_STATE_COMPLETE) {
		if (peticion_http.status == 200) {
			alertify.success(peticion_http.responseText);
		} else {
			alertify.error('Obtuvimos un error en el sistema, Intentelo mas tarde!<br/> <p>Gracias</p>');
		}
	}
}

function procesaRespuesta() {
	// Ocultar loader
	document.getElementById("loader").style.display = 'none';
	if (peticion_http.readyState == READY_STATE_COMPLETE) {
		if (peticion_http.status == 200) {
			if (peticion_http.responseText == "verdadero") {
				window.location.replace("productos");
			} else {
				alertify
						.error('<p>Verifique que los datos ingresados sean correctos!.</p>');
			}
		} else {
			alertify
					.error('Obtuvimos un error en el sistema, Intentelo mas tarde!<br/> <p>Gracias</p>');
		}
	}
}

// agrega producto al detalle
function carrito(idProducto) {
	codigoProducto = idProducto;
	validaCarrito();
}

function query_string(nombre, variable) {
	return nombre + "=" + encodeURIComponent(variable);
}

// Carga productos segun paginacion
var tipoPaginacion = "next";
function paginacion(tipo) {
	tipoPaginacion = tipo;
	show();
}

// Obtiene datos de productos mediante ajax
function handler() {
	if (AJAX.readyState == 4 && AJAX.status == 200) {
		if(productoo===null){
			productoo = eval('(' + AJAX.responseText + ')');
		}
		var cantidad = productoo.length;
		var paginas = Math.ceil(cantidad / 20);
		if (tipoPaginacion === 'next') {
			if (paginas > numPaginacion) {
				numPaginacion++;
			} else {
				alertify.error("No hay mas productos en la base de datos");
			}
		}
		if (tipoPaginacion === 'back') {
			if (numPaginacion > 1) {
				numPaginacion--;
			}
		}
		var inicio = numPaginacion * 20 - 20;
		var fin = numPaginacion * 20;
		if (numPaginacion <= paginas && numPaginacion > 0) {
			alertify.log("Pagina: " + numPaginacion);
			document.getElementById("listaProductos").innerHTML = "";
			for (inicio; inicio < fin; ++inicio) {
				document.getElementById("listaProductos").innerHTML += '<div data-toggle="modal" data-target="#myModal" id='
						+ productoo[inicio].id
						+ ' onclick="carrito('
						+ productoo[inicio].id
						+ ');">'
						+ '<img alt="MedicFarma" src="images/productos/pildora.png"'
						+ 'width="65px" height="70px">'
						+ '<p>'
						+ '<b>Codigo:</b> '
						+ productoo[inicio].id
						+ '<br /> <b> Nombre:</b>'
						+ productoo[inicio].nombre
						+ '<br />'
						+ '<b>Categoria</b>: '
						+ productoo[inicio].idCategoria.descripcion
						+ '<br /> <b>Laboratorio:</b>'
						+ productoo[inicio].idLaboratorio.descripcion
						+ '</p>'
						+ '</div>';
			}
		} else {
			alertify.error("No hay mas productos en la base de datos");
		}
	} else if (AJAX.readyState == 4 && AJAX.status != 200) {
		alertify.error('Something went wrong...');
	}
}

function show() {
	AJAX.onreadystatechange = handler;
	AJAX.open("GET", "ajaxProductos");
	AJAX.send("");
}

function Busqueda() {
	var busquedas = document.getElementById("listarBusquedaxCategoria");
	var busquedas2 = document.getElementById("listarBusquedaxProducto");
	var categorias = document.getElementById("categoria");
	var indexCategoria = categorias.options.selectedIndex;
	var categoria = categorias.options[indexCategoria].text;
	if (categoria == "Categoria") {
		busquedas.style.display = 'block';
		busquedas2.style.display = 'none';
	}
	if (categoria == "Producto") {
		busquedas.style.display = 'none';
		busquedas2.style.display = 'block';
	}
}
function getURL(tipo,tipoBusqueda) {
	var nombre=document.getElementById(tipoBusqueda);
	return "nombre=" + encodeURIComponent(nombre.value) + "&tipo="
	+ encodeURIComponent(tipo);
}
function buscar(e, tipoBusqueda) {
	if (e.keyCode == 13) {
		nuevaPeticion = inicializa_xhr();
		var data = document.getElementById(tipoBusqueda).value;
		var tipo = "producto";
		if (data.length > 0) {
			if (tipoBusqueda == "buscaCategoria") {
				tipo = "categoria";
			}
			nuevaPeticion.onreadystatechange = respuestaBusqueda;
			nuevaPeticion.open("POST", "busqueda", true);
			tipo = getURL(tipo,tipoBusqueda);
			nuevaPeticion.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
			nuevaPeticion.send(tipo);
		} else {
			alert("Debe ingresar algo en el campo de busqueda");
		}
	}
}
function respuestaBusqueda() {
	if (nuevaPeticion.readyState == 4 && nuevaPeticion.status == 200) {
		productoo = eval('(' + nuevaPeticion.responseText + ')');
		var cantidad = productoo.length;
		var paginas = Math.ceil(cantidad / 20);
		if (tipoPaginacion === 'next') {
			if (paginas > numPaginacion) {
				numPaginacion++;
			} else {
			}
		}
		if (tipoPaginacion === 'back') {
			if (numPaginacion > 1) {
				numPaginacion--;
			}
		}
		var inicio = numPaginacion * 20 - 20;
		var fin = numPaginacion * 20;
		if (numPaginacion <= paginas && numPaginacion > 0) {
			alertify.log("Se obtuvieron: " + productoo.length+" productos");
			document.getElementById("listaProductos").innerHTML = "";
			for (inicio; inicio < fin; ++inicio) {
				document.getElementById("listaProductos").innerHTML += '<div data-toggle="modal" data-target="#myModal" id='
						+ productoo[inicio].id
						+ ' onclick="carrito('
						+ productoo[inicio].id
						+ ');">'
						+ '<img alt="MedicFarma" src="images/productos/pildora.png"'
						+ 'width="65px" height="70px">'
						+ '<p>'
						+ '<b>Codigo:</b> '
						+ productoo[inicio].id
						+ '<br /> <b> Nombre:</b>'
						+ productoo[inicio].nombre
						+ '<br />'
						+ '<b>Categoria</b>: '
						+ productoo[inicio].idCategoria.descripcion
						+ '<br /> <b>Laboratorio:</b>'
						+ productoo[inicio].idLaboratorio.descripcion
						+ '</p>'
						+ '</div>';
			}
		} else {
			alertify.error("No hay mas productos en la base de datos");
		}
	} else if (nuevaPeticion.readyState == 4 && nuevaPeticion.status != 200) {
		alertify.error('Something went wrong...');
	}
}