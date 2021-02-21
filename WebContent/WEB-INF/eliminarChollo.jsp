<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylesheet.css" />
<title>Eliminar Chollo</title>
</head>
<body>
<h1> ${requestScope.CheckType} Chollo </h1>
		<ul>
		<li>Id chollo: ${chollo.id}</li>
		<li>Titulo chollo: ${chollo.title}</li>
		<li>Descripcion chollo: ${chollo.description}</li>
		<li>Link chollo: ${chollo.link}</li>
		<li>Precio Chollo: ${chollo.price}</li>
		<li>Id usuario chollo: ${chollo.idu}</li>
		<li>Id tienda: ${chollo.ids}</li>
		<li>Likes chollo: ${chollo.likes}</li>
		<li>Soldout chollo: ${chollo.soldout}</li>		
		</ul>
		<form method="POST" action="?">
		<input id="button" type="submit" value="${requestScope.CheckType} Order"/>
		</form>
</body>
</html>