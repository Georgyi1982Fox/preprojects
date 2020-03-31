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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String uri = request.getRequestURI();
        String userLogin =  request.getParameter("login");

        if(userLogin == null && uri.endsWith("/admin")){
            response.sendRedirect("/");
        }else {
            chain.doFilter(req, res);
        }


/*
        HttpSession session = request.getSession(false);
        String uri = ((HttpServletRequest) req).getRequestURI();
        this.context.log("Requested Resource::"+uri);

        if (session == null || session.getAttribute("userLogin") == null  && !uri.endsWith("/admin")) {
            this.context.log("Unauthorized access request");
            response.sendRedirect("login.html");
        } else {
            chain.doFilter(req, res); // Logged-in user found, so just continue request.
        }

 */


    }

    @Override
    public void destroy() {

    }
}









