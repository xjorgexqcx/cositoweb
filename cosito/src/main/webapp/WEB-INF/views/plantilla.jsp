<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="shortcut icon" href="images/icono/health.ico" />
<div class="logo">
	<img src="images/logofinal.png" alt="MediFarma a tu Servicio!" />
</div>
<br />
<div class="linux" Style="cursor: pointer;"
	onclick="window.location.href='http://www.ubuntu.com/'">
	<img alt="IloveUbuntu" src="images/ubuntuWallpaper.jpg">
</div>
<c:if test="${usuario !=null}">
	<div class="infoUsuario">
		<p Style="font-weight: bold;">
			Bienvenido: <a data-toggle="modal" data-target="#myLinkModal" href="">${usuario}</a>
		</p>
		<p Style="font-weight: bold;">
			<a href=""> ${tipoUsuario}</a>
		</p>
	</div>
</c:if>
<div class="menu">
	<div onclick="javascript:window.location.href='productos'">
		<p>Productos</p>
	</div>
	<div onclick="javascript:window.location.href='carritoCompras'">
		<p>Carrito</p>
	</div>
	<c:if test="${usuario == null}">
		<div onclick="javascript:window.location.href='login'">
			<p>Login</p>
		</div>
		<div onclick="javascript:window.location.href='register'">
			<p>Registrarse</p>
		</div>
	</c:if>
	<c:if test="${usuario !=null}">
		<div onclick="javascript:window.location.href='cerrarSesion'">
			<p>Salir</p>
		</div>
	</c:if>
</div>