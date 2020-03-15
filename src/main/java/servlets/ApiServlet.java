package servlets;

import dbexception.DBException;
import musicianService.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApiServlet extends HomeServlet {
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService musicianService = new UserService();
        try {
            musicianService.createTable();
            resp.setStatus(200);
        } catch (DBException e) {
            resp.setStatus(400);
        }
    }
}
