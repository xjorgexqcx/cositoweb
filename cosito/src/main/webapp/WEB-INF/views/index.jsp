<%@ include file="home.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/toastr.js"></script>
<link href="css/toastr.css" rel="stylesheet" />
<link rel="stylesheet" href="css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="js/bootstrap.min.js"></script>

<script type="text/javascript">
toastr.options = {
	"closeButton" : true,
	"debug" : false,
	"progressBar" : true,
	"positionClass" : "toast-top-center",
	"preventDuplicates" : false,
	"onclick" : null,
	"showDuration" : "300",
	"hideDuration" : "1000",
	"timeOut" : "5000",
	"extendedTimeOut" : "1000",
	"showEasing" : "swing",
	"hideEasing" : "linear",
	"showMethod" : "fadeIn",
	"hideMethod" : "fadeOut"
}
</script>
<section>
	<div onmouseover="moverScroll('1');" onmouseleave="stopScroll();"
		id="scrolling" class="scrollingUp"></div>
	<div onmouseover="moverScroll('0');" onmouseleave="stopScroll();"
		id="scrolling" class="scrolling"></div>

	<div class="agregarSeccion" data-toggle="modal" data-target="#myModal">
		<img alt="Agregar sección" src="images/add.png" />
	</div>
	<c:forEach items="${ltaSecciones}" var="ltaS">
		<c:if test="${ltaS.estado == 1}">
			<div id="codigo${ltaS.id}" Style="display: inline-block;">
				<div
					Style="background-image: url('${ltaS.idImagen.url}') !important;"
					id="item" onclick="generaSesion('${ltaS.id}');" class="item"
					title="${ltaS.nombre}"></div>
				<div class="eliminarSeccion" onclick="eliminar(${ltaS.id});">
					<img alt="eliminar Seccion" src="images/delete.png" />
				</div>
			</div>
		</c:if>
	</c:forEach>

	<!-- Small modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Nueva Sección</h4>
				</div>
				<div class="modal-body">
					<fieldset>
						<form action="uploadFile" enctype="multipart/form-data"
							method="post">
							<input type="text" id="nombre" name="nombre"
								placeholder="Nombre de la Seccion" required /><br /> <label>Subir
								Banner</label> <input name="userPhoto" type="file" required> </br> <input
								class="btn btn-primary" type="submit" name="SUBMIT"
								value="Guardar">
						</form>
					</fieldset>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
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