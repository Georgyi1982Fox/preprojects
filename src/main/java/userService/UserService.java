package userService;

import dao.UserDAO;
import dao.UserHibernateDAO;
import dao.UserJdbcDAO;
import model.User;
import java.sql.SQLException;
import java.util.List;

import static util.DBHelper.*;

public class UserService {
    private static UserService userService;
    private static UserDAO userDAO;

    public static UserDAO getUserDao(){
        if(userDAO == null){
            userDAO = new UserHibernateDAO();
        }
        return userDAO;
    }

    private UserService() {
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public void addUser(User user) throws SQLException {
        getUserDao().addUser(user);
    }

    public List<User> listAllUsers() throws SQLException {
        return getUserDao().listAllUsers();
    }

    public boolean validateClient(String name) throws SQLException {
        return getUserDao().validateClient(name);
    }

    public User getUserById(Long id) throws SQLException {
        return getUserDao().getUserById(id);
    }

    public void updateUser(User user) throws SQLException {
        getUserDao().updateUser(user);
    }

    public boolean deleteUser(Long id) throws SQLException {
        return getUserDao().deleteUser(id);
    }
}