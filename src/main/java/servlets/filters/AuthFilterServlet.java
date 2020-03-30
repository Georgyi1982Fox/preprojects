package servlets.filters;

import model.User;
import userService.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(urlPatterns = "/admin/*, /user/*")//Защищаемае часть
public class AuthFilterServlet implements Filter {
    private HttpServletRequest httpRequest;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String userLogin = request.getParameter("login");
        String userPassword = request.getParameter("password");
        String role = " ";
        boolean url = request.getRequestURI().startsWith("/admin");

        session.setAttribute("userLogin", userLogin);
        session.setAttribute("userPassword", userPassword);
        session.setAttribute("role", role);
        session.setAttribute("url", url);

        try {
            role = UserService.getInstance().getRoleByLoginPassword(userLogin, userPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        session.setAttribute("userLogin", userLogin);
        session.setAttribute("userPassword", userPassword);
        session.setAttribute("role", role);

        boolean logged = session != null && session.getAttribute("userLogin")!= null && session.getAttribute("role").equals("admin");
        boolean notLogged = session == null && session.getAttribute("userLogin") == null;
        if (logged && url) {
            response.sendRedirect("/admin");
            filterChain.doFilter(request, response);
        }
        if (notLogged) {
            response.sendRedirect("/");
        }
    }

    @Override
    public void destroy() {

    }
}












