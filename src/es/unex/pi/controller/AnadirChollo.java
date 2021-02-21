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
import es.unex.pi.model.Category;
import es.unex.pi.model.Chollo;
import es.unex.pi.model.ChollosCategory;
import es.unex.pi.model.Shop;
import es.unex.pi.model.User;

/**
 * Servlet implementation class AñadirChollo
 */
@WebServlet("/AnadirChollo.do")
public class AnadirChollo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnadirChollo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/EditChollo.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		
		UserDAO usuarioDAO = new JDBCUserDAOImpl();
		usuarioDAO.setConnection(conn);
		
		ShopDAO shopDAO = new JDBCShopDAOImpl();
		shopDAO.setConnection(conn);
		
		CholloDAO cholloDAO = new JDBCCholloDAOImpl();
		cholloDAO.setConnection(conn);

		Chollo chollo = new Chollo();
		
		//Obtengo la sesion
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("usuario");
		//Esto se cambia dependiendo del user que hay logeado
		chollo.setIdu(user.getId());
		Long idu = user.getId();

		chollo.setTitle(request.getParameter("title"));
		String title = request.getParameter("title");

		chollo.setDescription(request.getParameter("description"));
		String description = request.getParameter("description");
		
		chollo.setLink(request.getParameter("link"));
		String link = request.getParameter("link");

		chollo.setPrice(Float.parseFloat(request.getParameter("price")));
		float price = Float.parseFloat(request.getParameter("price"));
		
		
		//Compruebo que si el link existia en la tienda
		//Si existe recupero el ids que le corresponde
		//Si no existe, creo nueva tienda
		//chollo.setIds(Long.parseLong(request.getParameter("ids")));
		//Long ids = Long.parseLong(request.getParameter("ids"));

		chollo.setLikes(0);

		chollo.setSoldout(Integer.parseInt(request.getParameter("soldout")));
		int soldout = Integer.parseInt(request.getParameter("soldout"));
		
		
		String categoria = request.getParameter("categoria");
		
		CategoryDAO categoryDAO = new JDBCCategoryDAOImpl();
		categoryDAO.setConnection(conn);
		  
		ChollosCategoryDAO chollosCategoryDAO = new JDBCChollosCategoryDAOImpl();
		chollosCategoryDAO.setConnection(conn);
		
		if (title!=null && description!=null && link!=null && price>=0 && idu!=null  && (soldout==0||soldout==1)) {
			//Si la tienda existe con ese link, te deja insertarlo sin insertar
			Shop aux = shopDAO.get(request.getParameter("tienda"));
			if(aux!=null) {
				//Si existe la tienda, obtengo el id de la tienda y lo guardo en ids
				chollo.setIds(aux.getId());
					
			}else {
				//Hay que insertar en la tienda una nueva tupla con nuevo link
				Shop aux2 = new Shop();
				aux2.setName(request.getParameter("tienda"));
				aux2.setUrl(chollo.getLink());
				shopDAO.add(aux2);
				chollo.setIds(shopDAO.get(request.getParameter("tienda")).getId());
			}
			Category categoriaAux = categoryDAO.get(categoria);
			long x;
			if(categoriaAux==null) {
				//Inserto la nueva categoria porque no existe
				categoriaAux=new Category();
				categoriaAux.setName(categoria);
				x = categoryDAO.add(categoriaAux);
			}else {
				//Si existe la categoria, obtengo su id
				x = categoriaAux.getId();
			}
			//Añado el chollo en la tabla chollosCategory
			ChollosCategory nuevaTupla = new ChollosCategory();
			//Inserto el chollo en la tabla de chollos
			long y = cholloDAO.add(chollo);
			nuevaTupla.setIdc(y);
			nuevaTupla.setIdct(x);
			chollosCategoryDAO.add(nuevaTupla);
			response.sendRedirect("ListChollosUsuarioServlet.do");
		} else {
			//request.setAttribute("mensaje", "Ha introducido mal algún campo");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/EditChollo.jsp"); // PONER MENSAJES DE ERROR
			view.forward(request, response);
		}
	}

}
