<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="css/estilos1.css">
    
    
</head>
<body>
    <div class="contenedor-form">
        <div class="toggle">
            <a href="RegistrarServlet.do">Crear Cuenta</a>
            
        </div>
        
        <div class="formulario">
            <h2>Iniciar Sesion</h2>
            <form method="post" action="?">
                <input type="text" placeholder="Usuario" name="usuario" required>
                <input type="password" placeholder="Contraseña" name="password" required>
                <input type="submit" value="Iniciar Sesion">
            </form>
        </div>
        
        
        <div class="reset-password">
            <a href="ListChollosServlet.do">Inicio</a>
        
        </div>
        
        
    </div>
</body>
</html>