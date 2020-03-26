package servlets;

import model.User;
import userService.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet("/")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        String userLogin = req.getParameter("login");
        String userPassword = req.getParameter("password");
        String role = "";
        HttpSession session = req.getSession();

        try {
            if (UserService.getInstance().validateClient(userLogin, userPassword)) {
                role = UserService.getInstance().getRoleByLoginPassword(userLogin, userPassword);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (role.equals("user")) {
                    session.setAttribute("userLogin", userLogin);
                    session.setAttribute("userPassword", userPassword);
                    session.setAttribute("userRole", role);
                    resp.sendRedirect("/user/*");
                }else
                if (role.equals("admin")) {
                    resp.sendRedirect("admin/admin.jsp");
                }
    }
}


