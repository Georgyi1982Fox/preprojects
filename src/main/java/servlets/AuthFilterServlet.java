package servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/admin/*, /user/*")//Защищаемае часть
public class AuthFilterServlet implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);

        boolean logged = session != null && session.getAttribute("userLogin") != null && session.getAttribute("userRole") != null;
        if (logged) {
            String userRole = session.getAttribute("userRole").toString();
            if (userRole.equals("user")) {
                response.sendRedirect(request.getContextPath() + "/user");
            } else if (userRole.equals("admin")) {
                response.sendRedirect(request.getContextPath() + "/admin");
            }
        }

    }

    @Override
    public void destroy() {

    }
}


