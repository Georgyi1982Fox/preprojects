package servlets;

import userService.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession(false);
        String loginUrl = request.getContextPath() + "/";

        boolean logged = session != null && session.getAttribute("userLogin") != null && session.getAttribute("userRole") != null;
        boolean loginRequest = request.getRequestURI().equals(loginUrl);
        if (logged || loginRequest) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(loginUrl);
        }
    }

    @Override
    public void destroy() {

    }
}


