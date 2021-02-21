<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
  <link rel="stylesheet" href="css/estilos1.css">
</head>
<body>
 <div class="contenedor-form">
        
        
        <div class="formulario">
            <h2>Crea tu cuenta</h2>
            <form method="post" action="?">
                Usuario<input type="text" placeholder="Usuario" name="usuario" required>
                Email<input type="email" placeholder="Correo Electronico" name="email" required>
                Contrasena<input type="password" placeholder="Contraseña" name="contrasena" required>  
                <input type="submit" value="Registrarse">
            </form>
        </div>
        
        
        
        
    </div>
</body>
</html>