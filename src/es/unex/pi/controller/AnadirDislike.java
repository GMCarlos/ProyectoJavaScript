package es.unex.pi.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.unex.pi.dao.CholloDAO;
import es.unex.pi.dao.JDBCCholloDAOImpl;
import es.unex.pi.dao.JDBCShopDAOImpl;
import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.ShopDAO;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.Chollo;
import es.unex.pi.model.Shop;
import es.unex.pi.model.User;
import es.unex.pi.util.Triplet;

/**
 * Servlet implementation class AñadirDislike
 */
@WebServlet("/AnadirDislike.do")
public class AnadirDislike extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnadirDislike() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		
		
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);
		
		CholloDAO cholloDAO = new JDBCCholloDAOImpl();
		cholloDAO.setConnection(conn);
		
		ShopDAO shopDAO = new JDBCShopDAOImpl();
		shopDAO.setConnection(conn);
		
		request.getParameter("id");
		Chollo aux =cholloDAO.get(Long.parseLong(request.getParameter("id")));
		
		if(aux!=null) {
			
			aux.setLikes(aux.getLikes()-1);
			cholloDAO.save(aux);
			aux=null;
					
		}
		
		List<Chollo> cholloList = cholloDAO.getAll();
		  
		  Iterator<Chollo> itCholloList = cholloList.iterator();

		  List<Triplet<Chollo, User, Shop>> chollosUserShopList = new ArrayList<Triplet<Chollo, User, Shop>>();

		  while(itCholloList.hasNext()) {
		   Chollo chollo = (Chollo) itCholloList.next();
		   User user = userDAO.get(chollo.getIdu());
		   Shop shop = shopDAO.get(chollo.getIds());

		   chollosUserShopList.add(new Triplet<Chollo, User, Shop>(chollo,user,shop));
		  }


		request.setAttribute("chollosList",chollosUserShopList);
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/cuandoInicioSesion.jsp");
		view.forward(request,response);
	}

}
