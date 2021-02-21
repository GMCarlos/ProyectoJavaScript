<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html id="list">
	<head>
		<title>PROYECTO DE PROGRAMACION EN INTERNET</title>
		<meta charset="UTF-8">
		<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/stylesheet.css"   />
        <link rel="stylesheet" href="css/fontello.css">
        <link rel="stylesheet" href="css/estilos.css">
        <link rel="stylesheet" href="css/menu.css">
        <link rel="stylesheet" href="css/banner.css">
        <link rel="stylesheet" href="css/chollos.css">
        <link rel="stylesheet" href="css/footer.css">
	</head>
	<body>
	
		
        <header>
            <div class="contenedor">
                <h1 class="icon-dollar"><a href="ListChollosUsuarioServlet.do">PIRECIAZOS</a></h1>
                <input type="checkbox" id="menu-bar">
                <label class="icon-menu-1" for="menu-bar"></label>
                         
                <ul class="menu">
				<li><a href="">Mi cuenta</a>
					<ul>
						<li><a href="EditProfileServlet.do">Editar perfil</a></li>
						<li><a href="EliminarUsuarioServlet.do">Eliminar usuario</a></li>
						<li><a href="LogoutServlet.do">Cerrar Sesion</a></li>		
					</ul>
				</li>
				<li><a href="mostrarChollosUsuarioServlet.do">Mis chollos</a>
				</li>
				<li><a href="AnadirChollo.do">Añadir chollo</a>
				</li>
                </ul>
                <form method="get" action="Buscar.do">      
                <input type="text" placeholder="¿Que estas buscando?" id="buscador" name="buscar">
                <button type="submit" id="boton" value="Buscar">Buscar</button>
                </form>
            </div>
            
        </header>
        
            <section id="banner">
                <img src="img/banner.jpg" alt="fondo azul">
            </section>
        
             
         <h1> Mis chollos:</h1> 
		<br>
		<br>
		
		
			<c:forEach var="chollo" items="${chollosList}">
				<div>
					<h4>${chollo.title}</h4>
		    		<p>${chollo.description}</p> 
		    		<p><a href="${chollo.link}">${chollo.link}</a></p> 
		    		<p>${chollo.price}</p> 
		    		<p>${chollo.likes}</p>
		    	<div class="icono">
		    		<a href="<c:url value='EditCholloServlet.do?id=${chollo.id }'/>" ><img src="${pageContext.request.contextPath}/img/edit.png" width="80px" alt="edit ${chollo.id }" /></a>
					<a href="<c:url value='DeleteCholloServlet.do?id=${chollo.id }'/>" ><img src="${pageContext.request.contextPath}/img/delete.png" width="80px" alt="delete ${chollo.id }" /></a>
	
		    	</div>	
				
		    		<p>
		    			<c:choose>
		    				<c:when test="${chollo.soldout=='1'}">
		    					Agotado
		    				</c:when>
		    				<c:otherwise>
		    					Disponible
		    				</c:otherwise>	
		    			</c:choose>
					</p> 
				</div>	
		    </c:forEach>
        
	</body>
	        <footer>
            <div class="contenedor">
                <p class="copy">PIRECIAZOS &copy; 2018/19</p>
                <div class="redesSociales">
                    <a class="icon-twitter" href="https://twitter.com/GMCarlos_8"></a>
                    <a class="icon-instagram" href="https://www.instagram.com/GMCarlos_8/?hl=es"></a>
                    <a class="icon-facebook-squared" href="https://www.facebook.com/CarlosGuillenMorenoo"></a>
                    <a class="icon-gmail" href="mailto:cguilleny@alumnos.unex.es"></a>
                </div>
            </div>
        </footer>
</html>