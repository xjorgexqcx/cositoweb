<%@ include file="home.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link href='http://fonts.googleapis.com/css?family=Dancing+Script:700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="css/publicaciones.css">

<section>
	<div onmouseover="moverScroll('1');" onmouseleave="stopScroll();"
		id="scrolling" class="scrollingUp"></div>
	<div onmouseover="moverScroll('0');" onmouseleave="stopScroll();"
		id="scrolling" class="scrolling"></div>
	<div id="back" class="back">
		<a href="index"><img alt="Atras" title="Atras"
			src="images/back.png" /></a>
	</div>
	<div class="publicaciones">
		<h1>Cosito Dice</h1>
		<c:forEach items="${ltaArticulos}" var="articulo">
			<div class="subPublicaciones" style="${articulo.estilo}">
				<h2>${articulo.nombre}</h2>
				<p>${articulo.descripcion}</p>
			</div>
		</c:forEach>
	</div>
	<audio>
		<source src="audios/click.mp3"></source>
		<source src="audios/click.ogg"></source>
	</audio>
	<div id="sounddiv">
		<bgsound id="sound">
	</div>
</section>
</body>
</html>