package es.unex.giiis.pi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//TODO Comlete the urlpattern for this Filter

@WebFilter(urlPatterns = { "/AnadirLike.do", "/AnadirDislike.do", "/AnadirChollo.do",
		"/DeleteCholloServlet.do", "/EditCholloServlet.do","/EditProfileServlet.do","/EliminarUsuarioServlet.do","/ListChollosUsuarioServlet.do", "/mostrarChollosUsuarioServlet.do" })
public class LoginFilter implements Filter {

	private FilterConfig fc;

	public void init(FilterConfig fc) {

		this.fc=fc;

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = ((HttpServletRequest) request).getSession(true);

// If there is not a session established you must redirect to LoginServlet.do otherwise, follow the usual process. 

		if (session.getAttribute("usuario") == null) {
			res.sendRedirect(req.getContextPath() + "/IniciarSesion.do");
		} else
			chain.doFilter(request, response);
	}

	public void destroy() {
		// do the cleanup staff
	}

}
