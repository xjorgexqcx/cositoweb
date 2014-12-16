<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Bienvenido | MedicFarma</title>
<script src="js/inicio.js"></script>
<script src="js/alertify.js"></script>

<link rel="stylesheet" href="css/alertify.core.css">
<link rel="stylesheet" href="css/alertify.default.css">
<link rel="stylesheet" type="text/css" href="css/inicio.css">

<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">

<script>
	window.fbAsyncInit = function() {
		FB.init({
			appId : '1530567763850296',
			xfbml : true,
			version : 'v2.1'
		});
	};
	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id)) {
			return;
		}
		js = d.createElement(s);
		js.id = id;
		js.src = "//connect.facebook.net/en_US/sdk.js";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));
</script>
</head>
<body>
<%@include file="plantilla.jsp"%>
<br />
<div class="formulario">
	<form method="post" id="formulario"
		onsubmit="javascript:valida();return false;">
		<input type="text" id="usuario" name="usuario"
			placeholder="Ingrese su Usuario" required class="usuario"><br />
		<input type="password" id="password" name="password"
			placeholder="Ingrese Contraseña" class="usuario" required><br />
		<input type="submit" id="ingreso" value="Ingresar" class="boton">
	</form>
	<br/>
	<div style="color:${color}"><strong>${respuesta}</strong></div>
</div>
<br />
<br />

<div id="loader" Style="display: none">
	<img alt="Verificando,espere!" src="images/loader.gif">
</div>
<br />
<div id="socialConnect">
	<a href="http://youtu.be/9iShXAG-Nzo" target="_blank"> <img
		src="images/youtube.jpg" /></a> <a href="http://fb.com/medicfarma"
		target="_blank"> <img src="images/facebook.jpg" /></a> <a
		href="http://twitter.com/medicfarma" target="_blank"> <img
		src="images/twitter.jpg" /></a>
</div>
<br />
<div id="facebookLikeDafak" class="fb-like" data-share="true" data-width="450"
	data-show-faces="true"></div>
</body>
</html>