<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylesheet.css" />
  <title>Chollo Informacion</title>

</head>
<body>
  <h1 id="maintitle">Chollo</h1>
  <h2>${messages.error}</h2>

  <form method="post" action="?">
  <input type="hidden" name="id" value="${chollo.id }">
    <div>
  
      <label for="name">Titulo:</label> 
      <input class="personaldata" type="text" name="title" id="name" placeholder="Nombre del chollo" value="${chollo.title}" required/>
    </div>
    <div>
      <label for="desc">Descripcion:</label> 
      <input class="personaldata" type="text" name="description" id="desc" placeholder="Descripcion del chollo ..." value="${chollo.description}" required/>
    </div>
    <div>
      <label for="link">Link Tienda:</label>
      <input class="personaldata" type="url" name="link" id="link" placeholder="www.tuTienda.com" required value="${chollo.link}">  
    </div>
    
    <div>
      <label for="name">Tienda:</label> 
      <input class="personaldata" type="text" name="tienda" id="name" placeholder="Tienda del chollo" value="${tienda.name}" required/>
    </div>
    
    
    <div>
      <label for="price">Precio:</label>
      <input class="personaldata" type="number" name="price" id="price" placeholder="0" required value="${chollo.price}">  
    </div>
    <div>
      <label for="soldout">Soldout:</label>
      <input class="personaldata" type="number" name="soldout" id="soldout" placeholder="0" required value="${chollo.soldout}">  
    </div>
    
    <div>
      <label for="categoria">Categoria:</label>
      <input class="personaldata" type="text" name="categoria" id="categoria" placeholder="Categoria del producto ..." required value="${categoria.name}">  
    </div>  
      <input id="button" type="submit"  
      			value=<c:choose>
      					<c:when test="${not empty chollo.id}"> "Confirmar edicion chollo"</c:when>
      					<c:otherwise>
    						"Añadir chollo"
    					</c:otherwise>
					  </c:choose>/>
    </form>
</body>
</html>

