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
            <span>Crear Cuenta</span>
        </div>
        
        <div class="formulario">
            <h2>Iniciar SesiÃ³n</h2>
            <form action="#">
                <input type="text" placeholder="Usuario" required>
                <input type="password" placeholder="ContraseÃ±a" required>
                <input type="submit" value="Iniciar Sesion">
            </form>
        </div>
        
        
        <div class="formulario">
            <h2>Crea tu cuenta</h2>
            <form action="#">
                <input type="text" placeholder="Usuario" required>
                <input type="password" placeholder="ContraseÃ±a" required>
                <input type="email" placeholder="Correo ElectrÃ³nico" required>
                
                
                <input type="submit" value="Registrarse">
            </form>
        </div>
        
        
        <div class="reset-password">
            <a href="#">Olvide mi ContraseÃ±a</a>
        
        </div>
        
        
    </div>
</body>
</html>