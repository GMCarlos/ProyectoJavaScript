package es.unex.pi.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import es.unex.pi.model.Shop;
import es.unex.pi.model.User;
import es.unex.pi.util.Triplet;

/**
 * Servlet implementation class ListChollosUsuarioServlet
 */
@WebServlet("/ListChollosUsuarioServlet.do")
public class ListChollosUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListChollosUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		  logger.info("Atendiendo GET");  
		
		  Connection conn = (Connection) getServletContext().getAttribute("dbConn");
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
		  
		  
		  List<Chollo> cholloList = cholloDAO.getAll();
		  
		  Iterator<Chollo> itCholloList = cholloList.iterator();

		  List<Triplet<Chollo, User, Shop>> chollosUserShopList = new ArrayList<Triplet<Chollo, User, Shop>>();

		  while(itCholloList.hasNext()) {
		   Chollo chollo = (Chollo) itCholloList.next();
		   User user = userDAO.get(chollo.getIdu());
		   logger.info("Usuario " + user.getUsername());
		   Shop shop = shopDAO.get(chollo.getIds());

		   chollosUserShopList.add(new Triplet<Chollo, User, Shop>(chollo,user,shop));
		  }
		  
//		  List<User> listUser = new ArrayList<User>();
//			listUser = userDAO.getAll();
//			Iterator<User> itUser = listUser.iterator();
//			Map<User,List<Chollo>> userChollosMap = new HashMap<User,List<Chollo>>();
//			
//			while(itUser.hasNext()) {
//				User user = itUser.next();
//				cholloList = cholloDAO.getAllByUser(user.getId());
//				userChollosMap.put(user, cholloList);
//			}
			
			request.setAttribute("chollosList",chollosUserShopList);
			//request.setAttribute("usersMap", userChollosMap);
			
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/cuandoInicioSesion.jsp");
			view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
