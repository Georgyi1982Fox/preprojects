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

@WebServlet("/update")
public class UpdateUserServlet extends HttpServlet {
    UserService userService = new UserService();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userById = req.getParameter("id");
        Long userId = Long.parseLong(userById);

        User userOne= null;
        try {
            userOne = userService.getUserById(userId);
        } catch (DBException e) {
            e.printStackTrace();
        }
        req.getServletContext().setAttribute("userOne", userOne);

        req.getRequestDispatcher("update.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("name");
        String userPassword = req.getParameter("password");
        String userEmail = req.getParameter("email");
        User user = new User(userName, userPassword, userEmail);

        if (userPassword == null || userPassword.isEmpty()) {
            resp.sendRedirect("/list");
        } else {
            try {
                userService.updateUser(user);
            } catch (DBException e) {
                e.printStackTrace();
            }
            resp.sendRedirect("/list");
        }
    }

}

