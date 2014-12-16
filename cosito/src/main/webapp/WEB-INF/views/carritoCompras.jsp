<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MedicFarma | Carrito</title>
<link rel="stylesheet" href="css/plantilla.css">
<link rel="stylesheet" href="css/carrito.css">
</head>
<body>
	<br />
	<%@include file="plantilla.jsp"%>
	<article>
	<h1>
		<u>Carrito</u> <u>de</u> <u>Compras</u>
	</h1>
	<div class="logoSale" style="display: none;">
		<img alt="Fondo para carrito" src="images/carrito/Sale.png" />
	</div>
	<c:if test="${detalles !=null}">
		<div Style="margin-bottom: 8px;">
			<a href="cancelaPedido" Style="color: red;">Eliminar Todo</a>
		</div>
	</c:if>
	<div id="list5">
		<div class="columnas">
			<div>Producto</div>
			<div>Presentacion</div>
			<div>Precio</div>
			<div>Cantidad</div>
			<div>Fecha de Pedido</div>
			<div>Cancela Pedido</div>
			<div>Editar Pedido</div>
			<div>Confirmar Pedido</div>
		</div>
		<div class="data">
			<c:forEach items="${detalles}" var="detalle">
				<c:forEach items="${ltaProd}" var="productos">
					<c:if test="${productos.id == detalle.idProducto}">
						<div>${productos.nombre}</div>
					</c:if>
				</c:forEach>
				<c:forEach items="${ltaPre}" var="presentaciones">
					<c:if test="${presentaciones.id == detalle.idPresentacion}">
						<div>${presentaciones.descripcion}</div>
					</c:if>
				</c:forEach>
				<div>S/. ${detalle.precio}</div>
				<div>${detalle.cantidad}</div>
				<div>${pedidos.fechaPedido}</div>
				<div>
					<button>Cancelar</button>
				</div>
				<div>
					<button>Editar</button>
				</div>
				<div>
					<button>Confirmar</button>
				</div>
				<br />
			</c:forEach>
		</div>
		<div class="data" Style="color:${color}">${carro}</div>
	</div>
	</article>
</body>
</html>