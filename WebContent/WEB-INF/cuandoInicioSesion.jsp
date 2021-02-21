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
        
        <ul>					
		<li><a href="BuscarPorLikesDescendente.do">Filtrar por likes descendente</a></li>
		</ul>
		
		<div class="y">
		<ul>
		<li><a>Buscar chollos con un número mínimo de likes</a></li>
		</ul>
		<form method="get" action="FiltrarPorLikes.do">      
                <input type="number" placeholder="Likes" id="buscador" name="buscarLikes">
                <button type="submit" id="boton" value="BuscarConLikes">Buscar</button>
        </form>
        </div>
        <ul>
		<li><a href="MostrarChollosDisponibles.do">Mostrar chollos disponibles</a></li>
        </ul>
          <br>
          <br>
        
		<h2> List of chollos:</h2>
		<table>
			<thead>
				<tr>
					<th>Title</th>
					<th>Link</th>
					<th>Price</th>
					<th>Likes</th>
					<th>Soldout</th>
					<th>User</th>
					<th>Shop</th>
					<th>Like</th>
					<th>Dislike</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="chollo" items="${chollosList}">
					<tr> 
						<td>${chollo.first.title}</td>
						<td><a href="${chollo.first.link}">${chollo.first.link}</a></td>
						<td>${chollo.first.price}</td>
						<td>${chollo.first.likes}</td>
						<td>
						<c:choose>
		    				<c:when test="${chollo.first.soldout=='1'}">
		    					Agotado
		    				</c:when>
		    				<c:otherwise>
		    					Disponible
		    				</c:otherwise>	
		    			</c:choose>
						</td>
						<td>${chollo.second.username}</td>
						<td>${chollo.third.name}</td>
						<td><a href="<c:url value='AnadirLike.do?id=${chollo.first.id }'/>"><img src="img/Like.PNG" alt="Mano arriba" width="40px"></a></td>
						<td><a href="<c:url value='AnadirDislike.do?id=${chollo.first.id }'/>"><img src="img/Dislike.PNG" alt="Mano abajo" width="40px" ></a></td>
					</tr>	
		    	</c:forEach>
		    	</tbody>	
			</table>	       
        
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