<%@ include file="home.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section>
	<div onmouseover="moverScroll('1');" onmouseleave="stopScroll();"
		id="scrolling" class="scrollingUp"></div>
	<div onmouseover="moverScroll('0');" onmouseleave="stopScroll();"
		id="scrolling" class="scrolling"></div>

	<div class="agregarSeccion">
				<img alt="Agregar sección" src="images/add.png" />
	</div>
	<c:forEach items="${ltaSecciones}" var="ltaS">
		<div Style="background-image: url('${ltaS.idImagen.url}') !important;"
			id="item" onclick="redi('publicaciones.html');" class="item">
			<div class="eliminarSeccion">
				<img alt="eliminar Seccion" src="images/delete.png" />
			</div>
		</div>
	</c:forEach>
	<audio>
		<source src="audios/click.mp3"></source>
		<source src="audios/click.ogg"></source>
	</audio>
	<div id="sounddiv">
		<bgsound id="sound">
	</div>
</section>
<footer>
	<!--
        <div class="menu">
            <img onclick="redi('index.html');" alt="home" src="images/1016.png">
            <img alt="registro" src="images/register.png">
        </div>
        -->
</footer>
</body>
</html>