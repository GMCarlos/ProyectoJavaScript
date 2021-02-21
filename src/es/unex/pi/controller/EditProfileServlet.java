package es.unex.pi.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.UserDAO;

import es.unex.pi.model.User;

/**
 * Servlet implementation class EditProfileServlet
 */
@WebServlet("/EditProfileServlet.do")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("usuario");
		
		if(user!=null) {
			request.setAttribute("usuario", user);
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/editarUsuario.jsp");
			view.forward(request, response);
		}else {
			response.sendRedirect("ListChollosUsuarioUsuario.do");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		UserDAO usuarioDAO = new JDBCUserDAOImpl();
		usuarioDAO.setConnection(conn);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("usuario");
		String nombreAnterior= user.getUsername();
		System.out.println("Nombre Antiguo");
		System.out.println(nombreAnterior);

		String username = request.getParameter("usuario");
		user.setUsername(username);
		System.out.println("Nombre nuevo");
		System.out.println(username);
		String password = request.getParameter("contrasena");
		user.setPassword(password);
		System.out.println("Nombre nuevo");
		System.out.println(password);
		String email = request.getParameter("email");
		user.setEmail(email);
		System.out.println("Nombre nuevo");
		System.out.println(email);

		//Compruebo que los campos no estén vacíos
		if (user.getUsername() != null && user.getEmail()!=null && user.getPassword()!=null) {
			//si mismo nombre, actualizo
			System.out.println("Username: "+username);
			System.out.println("NombreAnterior: "+nombreAnterior);
			if (username.equals(nombreAnterior)) {
				
				usuarioDAO.save(user);
				response.sendRedirect("ListChollosUsuarioServlet.do");
			}else {
				//Si no tiene el mismo nombre y el nuevo nombre no existe
				if(usuarioDAO.get(username)==null) {
					usuarioDAO.save(user);
					response.sendRedirect("ListChollosUsuarioServlet.do");
				}else {
					
					//Si existe el usuario redirijo a editra Usuario
					RequestDispatcher view = request.getRequestDispatcher("WEB-INF/editarUsuario.jsp"); // PONER MENSAJES DE ERROR
					view.forward(request, response);
				}
			}
		} else {
			//request.setAttribute("mensaje", "Ha introducido mal algún campo");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/editarUsuario.jsp"); // PONER MENSAJES DE ERROR
			view.forward(request, response);
		}
	}

}
