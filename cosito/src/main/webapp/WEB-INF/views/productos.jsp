<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>MedicFarma | Productos</title>
<script src="js/inicio.js"></script>
<script src="js/alertify.js"></script>
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css" href="css/inicio.css">
<link rel="stylesheet" href="css/alertify.core.css">
<link rel="stylesheet" href="css/alertify.default.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- Latest compiled and minified CSS -->

<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">

<!-- Encriptar y desencriptar claves en javascript
<script
	src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/rabbit.js"></script>
 -->
<script src="js/rabbit.js"></script>
<script>
//var encrypted = CryptoJS.Rabbit.encrypt("solocon22","1234");
//alert("encrypted : "+encrypted);
//var decrypted =CryptoJS.Rabbit.decrypt(encrypted, "1234");
//alert("decrypted : "+decrypted);
</script>
</head>
<body>
<br />
	<%@include file="plantilla.jsp"%>
	<br />
	<div class="paginacion">
		<img alt="Back" src="images/back.png" Style="cursor: pointer;"
			onclick="paginacion('back')"> &nbsp; <img alt="Next"
			src="images/next.png" Style="cursor: pointer;"
			onclick="paginacion('next')">
	</div>
	<div id="loader" Style="display: none">
		<img alt="Verificando,espere!" src="images/loader.gif">
	</div>
	<br />
	<br />
	<h2>Productos</h2>
	<div id="listarBusquedaxCategoria" class="fondoinput">
		<input type="text" id="buscaCategoria"
			placeholder="Busqueda por categoria" list="lista"
			onkeyup="javascript:buscar(event,'buscaCategoria');" />
		<datalist id="lista"> <c:forEach items="${categoria}"
			var="categoria">
			<option value="${categoria.descripcion}">
		</c:forEach> </datalist>
	</div>
	<div id="listarBusquedaxProducto" class="fondoinput"
		Style="display: none;">
		<input type="text" id="buscaProducto"
			placeholder="Busqueda por producto" list="listaP"
			onkeyup="javascript:buscar(event,'buscaProducto');" />
		<datalist id="listaP"> <c:forEach items="${Lista}"
			var="categoria">
			<option value="${categoria.nombre}">
		</c:forEach> </datalist>
	</div>
	<select name="categoria" id="categoria"
		onchange="javascript:Busqueda()">
		<option>Categoria</option>
		<option>Producto</option>
	</select>

	<div class="listaProductos" id="listaProductos">
		<c:forEach items="${Lista}" var="bean">
			<div data-toggle="modal" data-target="#myModal" id="${bean.id}"
				onclick="carrito(${bean.id});";>
				<img alt="MedicFarma" src="images/productos/pildora.png"
					width="65px" height="70px">
				<p>
					<b>Codigo:</b> ${bean.id}<br /> <b> Nombre:</b> ${bean.nombre}<br />
					<b>Categoria</b>: ${bean.idCategoria.descripcion}<br /> <b>Laboratorio:</b>
					${bean.idLaboratorio.descripcion}
				</p>
			</div>
		</c:forEach>
	</div>
	<br />

	<!-- Modal de los productos para ver sus presentaciones -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">PRESENTACIONES DEL
						PRODUCTO</h4>
				</div>
				<br />
				<div id="modal-content"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar!</button>
				</div>
			</div>
		</div>
	</div>

	<!-- ***************Modal para informacion personal*************** -->
	<div class="modal fade" id="myLinkModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Mi Informacion
						Personal</h4>
				</div>
				<br />
				<div id="modal-content2">
					<div id="list5">
						<ol>
							<li>Nombres
								<ol>
									<li>${user.descripcion}</li>
								</ol>
							</li>
							<li>Apellidos
								<ol>
									<li>${user.apellido}</li>
								</ol>
							</li>
							<li>Dni
								<ol>
									<li>${user.dni}</li>
								</ol>
							</li>
							<li>Usuario
								<ol>
									<li>${user.usuario}</li>
								</ol>
							</li>
							<li>Fecha de Nacimiento
								<ol>
									<li>${user.fechaNac}</li>
								</ol>
							</li>
						</ol>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar!</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>