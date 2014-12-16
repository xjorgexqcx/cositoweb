var peticion = inicializa_xhr();

function inicializa_xhr() {
	if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
}

function addUser() {
	var sedeForm=document.getElementById("formSede").sede;
	
	var usuario = document.getElementById("usuario").value;
	var password = document.getElementById("password").value;
	var nombre = document.getElementById("nombres").value;
	var apellidos = document.getElementById("apellidos").value;
	var fechaNac = document.getElementById("fechaNac").value;
	var dni = document.getElementById("dni").value;
	var sede = null;
	var tipoUsuario = 3;

	for (i = 0; i < sedeForm.length; i++)
		if (sedeForm[i].checked)
			sede = sedeForm[i].value;

	if (usuario != '' && password != '' && nombre != '' && apellidos != ''
			&& fechaNac != '' && dni != '' && sede != null) {
		if (password.length >= 8) {
			var data = crea_query_string(usuario, password, nombre, apellidos,
					fechaNac, dni, sede, tipoUsuario);
			ajaxSend(data);
		}else
			{
				alert("La contraseña debe contener almenos 8 caracteres");
			}
	} else {
		alert("Error faltan datos");
	}
}

function crea_query_string(usuario, password, nombre, apellidos, fechaNac, dni,
		sede, tipoUsuario) {
	return "usuario=" + encodeURIComponent(usuario) + "&password="
			+ encodeURIComponent(password) + "&nombre="
			+ encodeURIComponent(nombre) + "&apellidos="
			+ encodeURIComponent(apellidos) + "&fechaNac="
			+ encodeURIComponent(fechaNac) + "&dni=" + encodeURIComponent(dni)
			+ "&sede=" + encodeURIComponent(sede) + "&tipoUsuario="
			+ encodeURIComponent(tipoUsuario);
}

function ajaxSend(data) {
	peticion = inicializa_xhr();
	peticion.onreadystatechange = registraUsuario;
	peticion.open("POST", "registraUsuario", true);

	peticion.setRequestHeader("Content-Type",
			"application/x-www-form-urlencoded");
	peticion.send(data);
}

function registraUsuario() {
	parent.location.reload();
}
function defaultRadio() {
	document.getElementById("1").checked = true;
}