package servlets;

import dbexception.DBException;
import userService.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete")
public class DeleteUserServlet extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        UserService userService = new UserService();

        Long id = Long.parseLong(req.getParameter("id"));
            try {
                userService.deleteUser(id);
            } catch (DBException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            res.sendRedirect(req.getContextPath()+"/");
        }
    }

