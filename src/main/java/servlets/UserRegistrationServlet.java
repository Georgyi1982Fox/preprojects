package servlets;

import dbexception.DBException;
import model.User;
import musicianService.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class UserRegistrationServlet extends HttpServlet {

    UserService userService = new UserService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("userPage.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("name");
        String userPassword = req.getParameter("password");
        String userEmail = req.getParameter("email");

        User user = new User(userName, userPassword, userEmail);

        try {
            userService.addUser(user);
        } catch (SQLException | DBException e) {

        }
        resp.sendRedirect("list");
    }
}