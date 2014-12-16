<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">

<title>MedicFarma | Registro</title>
<!-- Javascript -->
<script src="js/registro.js"></script>
<script src="js/alertify.js"></script>
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<!-- Css -->
<link rel="stylesheet" href="css/plantilla.css">
<link rel="stylesheet" href="css/registro.css">
<link rel="stylesheet" href="css/alertify.core.css">
<link rel="stylesheet" href="css/alertify.default.css">
<link rel="stylesheet" href="css/bootstrap.min.css">

</head>
<body onload="defaultRadio();">
<br />
	<%@include file="plantilla.jsp"%>
	<article>
	<h1>
		<u>Registro</u> <u>de</u> <u>Clientes</u>
	</h1>
	<div class="registro">
		<div>
			<input type="text" id="usuario" placeholder="Ingrese usuario"
				name="usuario" required /> <input type="password" id="password"
				placeholder="Ingrese password" name="password" required />
		</div>
		<div>
			<input type="firstname" id="nombres" placeholder="Ingrese nombre(s)"
				name="nombres" required /> <input type="lastname" id="apellidos"
				placeholder="Ingrese apellidos" name="apellidos" required />
		</div>
		<div>
			<input type="date" id="fechaNac" placeholder="Seleccione fecha"
				name="fechaNac" required /> <input type="text"
				placeholder="Ingrese su dni" name="dni" id="dni" />
		</div>
		<div id="boton" data-toggle="modal" data-target="#myModal">
			<button class="tamanio" type="button" id="save" name="save">Guardar</button>
		</div>
		<div style="color:${color};"><strong>${respuesta}</strong></div>
	</div>
	<div>
		<img alt="Fondo para registro" src="images/productos/pildora.gif" />
	</div>

	<!-- Modal de los productos para ver sus presentaciones -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Lista de Sedes</h4>
				</div>
				<br />

				<div id="modal-content">
					<!-- *****************Lista de Sedes****************** -->
					<div id="list5">
						<ol>
							<button Style="margin-left: 33% !important;" type="button"
								class="btn btn-success" onclick="addUser();">Finalizar Registro</button>
							<hr />
							<br />
							<form id="formSede">
								<c:forEach items="${ciudades}" var="ciudades">
									<li>${ciudades.nombre}
										<ol>
											<c:forEach items="${sedes}" var="sedes">
												<c:if test="${sedes.idCiudad == ciudades.id}">
													<li><input type="radio" name="sede" id="${sedes.id}"
														value="${sedes.id}">${sedes.direccion}<br></li>
												</c:if>
											</c:forEach>
										</ol>
									</li>
								</c:forEach>
							</form>
						</ol>
					</div>
					<!-- ************************************* -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Cerrar!</button>
				</div>
			</div>
		</div>
	</div>
	</article>
</body>
</html>