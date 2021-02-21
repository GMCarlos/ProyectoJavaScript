package es.unex.pi.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.unex.pi.dao.CholloDAO;
import es.unex.pi.dao.JDBCCholloDAOImpl;
import es.unex.pi.model.Chollo;
import es.unex.pi.model.User;


/**
 * Servlet implementation class DeleteOrderServlet
 */
@WebServlet("/DeleteCholloServlet.do")
public class DeleteCholloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteCholloServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	Connection conn = (Connection) getServletContext().getAttribute("dbConn");
	CholloDAO cholloDao = new JDBCCholloDAOImpl();
	cholloDao.setConnection(conn);
	
	try{
		String id = request.getParameter("id");
		logger.info("get parameter id ("+id+")");
		long oid = 0;
		oid = Long.parseLong(id); 
		logger.info("get parameter id ("+id+") and casting "+oid);
		Chollo chollo = cholloDao.get(oid);
		//Obtengo la sesion
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("usuario");
		if (chollo != null && chollo.getIdu()==user.getId()){
			//HttpSession session = request.getSession();
			session.setAttribute("chollo",chollo);
			request.setAttribute("CheckType", "Delete");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/eliminarChollo.jsp");
			view.forward(request,response);
		}
		else response.sendRedirect("mostrarChollosUsuario.do");
	}
		catch (Exception e) {
			logger.info("parameter id is not a number");
			response.sendRedirect("ListOrderServlet.do");
	}
	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("Handling POST");
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		CholloDAO cholloDao = new JDBCCholloDAOImpl();
		cholloDao.setConnection(conn);
		
		HttpSession session = request.getSession();
		logger.info("Confirmed order to delete with session id: "+session.getId());
		Chollo chollo = (Chollo) session.getAttribute("chollo");
		
		
		if (chollo!=null) {
			cholloDao.delete(chollo.getId());
			session.removeAttribute("chollo");
			chollo=null;
		}
			
		response.sendRedirect("ListChollosUsuarioServlet.do");
	}

}
