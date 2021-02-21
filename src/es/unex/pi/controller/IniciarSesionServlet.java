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
 * Servlet implementation class IniciarSesion
 */
@WebServlet("/IniciarSesion.do")
public class IniciarSesionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IniciarSesionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/IniciarSesion.jsp");
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
		String password = request.getParameter("password");
		
		user=usuarioDAO.get(username);

		if (user != null  && user.getPassword().equals(password)) {
			HttpSession sesion = request.getSession();
		      sesion.setAttribute("usuario", user);
			response.sendRedirect("ListChollosUsuarioServlet.do");
		} else {
			
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/IniciarSesion.jsp");
			view.forward(request, response);
		}

	}

}
