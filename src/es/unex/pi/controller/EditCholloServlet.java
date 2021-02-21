package es.unex.pi.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
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
import es.unex.pi.dao.ShopDAO;
import es.unex.pi.model.Category;
import es.unex.pi.model.Chollo;
import es.unex.pi.model.ChollosCategory;
import es.unex.pi.model.Shop;
import es.unex.pi.model.User;


/**
 * Servlet implementation class DetailOrderServlet
 */
@WebServlet("/EditCholloServlet.do")
public class EditCholloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCholloServlet() {
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
				request.setAttribute("chollo",chollo);
				//////////////////////////////////////
				CategoryDAO categoryDAO = new JDBCCategoryDAOImpl();
				categoryDAO.setConnection(conn);
				  
				ChollosCategoryDAO chollosCategoryDAO = new JDBCChollosCategoryDAOImpl();
				chollosCategoryDAO.setConnection(conn);
				
				List<ChollosCategory> cholloCategory = chollosCategoryDAO.getAllByChollo(chollo.getId());
				//Categorias de todos los chollos
				Iterator<ChollosCategory> itCholloList = cholloCategory.iterator();
				while(itCholloList.hasNext()) {
					ChollosCategory x = itCholloList.next();
					long y = x.getIdct();
					Category z = categoryDAO.get(y);
					request.setAttribute("categoria", z);
					//en el caso de que tuviera más de una, se quedaria la ultima pero ya lo arreglaré
				}
				
				ShopDAO shopDAO = new JDBCShopDAOImpl();
				shopDAO.setConnection(conn);
				
				Shop tienda = shopDAO.get(chollo.getIds());
				//System.out.println("Carloooooooos");
				//chollo.getLink();
				//System.out.println(x.getName());
				request.setAttribute("tienda", tienda);
				//request.setAttribute("categoria", );
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/EditChollo.jsp");
				view.forward(request,response);
			}
			else response.sendRedirect("mostrarChollosUsuarioServlet.do");
		}
			catch (NumberFormatException e) {
				logger.info("parameter id is not a number");
				response.sendRedirect("mostrarChollosUsuarioServlet.do");
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		CholloDAO cholloDao = new JDBCCholloDAOImpl();
		cholloDao.setConnection(conn);
		
		ShopDAO shopDAO = new JDBCShopDAOImpl();
		shopDAO.setConnection(conn);
		
		//obtengo el chollo de la tabla
		Long id = Long.parseLong(request.getParameter("id"));
		Chollo chollo = cholloDao.get(id);
		
		//El id no se edita
		//chollo.setId(Long.parseLong(request.getParameter("id")));
		//Long id = Long.parseLong(request.getParameter("id"));
		//System.out.println("Id="+id);
		
		
		//Modifico los atributos
		chollo.setTitle(request.getParameter("title"));
		String title = request.getParameter("title");
		//System.out.println("Title ="+title);
		
		chollo.setDescription(request.getParameter("description"));
		String description = request.getParameter("description");
		//System.out.println("Descripcion="+description);
		
		chollo.setLink(request.getParameter("link"));
		String link = request.getParameter("link");
		//System.out.println("Link="+link);
		
		chollo.setPrice(Float.parseFloat(request.getParameter("price")));
		float price = Float.parseFloat(request.getParameter("price"));
		//System.out.println("Precio="+price);
		
		HttpSession session = request.getSession();
		User userActual = (User) session.getAttribute("usuario");
		chollo.setIdu(userActual.getId());
		Long idu = userActual.getId();
		//System.out.println("Idu="+idu);
		
		
		//aaaaaaaaaaa
		//Shop x = shopDAO.getTiendaPorLink(link);
		
		//chollo.setIds(x.getId());
		//Long ids = Long.parseLong(request.getParameter("ids"));
		//System.out.println("Ids="+ids);
		
//		chollo.setLikes(Integer.parseInt(request.getParameter("likes")));
//		int likes = Integer.parseInt(request.getParameter("likes"));
//		System.out.println("Likes="+likes);
		
		chollo.setSoldout(Integer.parseInt(request.getParameter("soldout")));
		int soldout = Integer.parseInt(request.getParameter("soldout"));
		//System.out.println("Soldout="+soldout);
		
		//Faltaria cambiar la categoria si se modifica ...
		//OPCIONAL
		
		
		
		//Map<String, String> messages = new HashMap<String, String>();
		//&& chollo.validate(messages)
		if (id!=null  && title!=null && description!=null && idu!=null && link!=null && price>=0 && (soldout==0 || soldout==1)) {
			//Compruebo si existe la tienda
			//Si existe no la creo
			Shop x = null;
			x=shopDAO.get(request.getParameter("tienda"));
			if(x==null) {
				//Si no existe la tienda la creo
				Shop nueva = new Shop();
				nueva.setName(request.getParameter("tienda"));
				nueva.setUrl(request.getParameter("link"));
				shopDAO.add(nueva);
				chollo.setIds(shopDAO.get(request.getParameter("tienda")).getId());
			}else {
				chollo.setIds(x.getId());
			}
			//Los likes no se modifican porque hemos recuperado la tupla de la tabla con los que tuviese
			
			cholloDao.save(chollo);
			chollo=null;
			response.sendRedirect("ListChollosUsuarioServlet.do");
		} 
		else {
			//request.setAttribute("messages",messages);
			request.setAttribute("chollo",chollo);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/EditChollo.jsp");
			view.forward(request,response);
		}	
		
		
		
	}

}