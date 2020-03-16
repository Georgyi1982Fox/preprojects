package servlets;

import dbexception.DBException;
import model.User;
import userService.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    UserService userService = new UserService();
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<User> users = userService.listAllUsers();
            req.setAttribute("users", users);
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
