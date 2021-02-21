package es.unex.pi.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.User;

/**
 * Servlet implementation class RegistrarServlet
 */
@WebServlet("/RegistrarServlet.do")
public class RegistrarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/CrearUsuario.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		UserDAO usuarioDAO = new JDBCUserDAOImpl();
		usuarioDAO.setConnection(conn);

		User user = new User();

		String username = request.getParameter("usuario");
		user.setUsername(username);
		String password = request.getParameter("contrasena");
		user.setPassword(password);
		String email = request.getParameter("email");
		user.setEmail(email);

		if (user.getUsername() != null && user.getEmail()!=null && user.getPassword()!=null && usuarioDAO.get(username)==null) {
			usuarioDAO.add(user);
			response.sendRedirect("ListChollosServlet.do");
		} else {
			//request.setAttribute("mensaje", "Ha introducido mal algún campo");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/CrearUsuario.jsp"); // PONER MENSAJES DE ERROR
			view.forward(request, response);
		}
	}
		
	}


