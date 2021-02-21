package es.unex.pi.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.unex.pi.dao.CategoryDAO;
import es.unex.pi.dao.CholloDAO;
import es.unex.pi.dao.ChollosCategoryDAO;
import es.unex.pi.dao.JDBCCategoryDAOImpl;
import es.unex.pi.dao.JDBCCholloDAOImpl;
import es.unex.pi.dao.JDBCChollosCategoryDAOImpl;
import es.unex.pi.dao.JDBCShopDAOImpl;
import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.ShopDAO;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.Chollo;
import es.unex.pi.model.User;

/**
 * Servlet implementation class mostrarChollosUsuarioServlet
 */
@WebServlet("/mostrarChollosUsuarioServlet.do")
public class mostrarChollosUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public mostrarChollosUsuarioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info("Atendiendo GET");

		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("usuario");
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);

		CholloDAO cholloDAO = new JDBCCholloDAOImpl();
		cholloDAO.setConnection(conn);

		ShopDAO shopDAO = new JDBCShopDAOImpl();
		shopDAO.setConnection(conn);

		CategoryDAO categoryDAO = new JDBCCategoryDAOImpl();
		categoryDAO.setConnection(conn);

		ChollosCategoryDAO chollosCategoryDAO = new JDBCChollosCategoryDAOImpl();
		chollosCategoryDAO.setConnection(conn);

		List<Chollo> cholloList = cholloDAO.getAllByUser(user.getId());

		cholloList = cholloDAO.getAllByUser(user.getId());

		request.setAttribute("chollosList", cholloList);

		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/misChollos.jsp");
		view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
