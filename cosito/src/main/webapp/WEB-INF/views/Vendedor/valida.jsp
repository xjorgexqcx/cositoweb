<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	HttpSession hs = request.getSession();
	if (hs.getAttribute("tipoUsuario") == null) {
		response.sendRedirect("../productos");
	} else {
		String tipo = hs.getAttribute("tipoUsuario").toString();
		if (tipo.equals("Cliente")) {
			response.sendRedirect("../productos");
		}
	}
%>