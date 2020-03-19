package userService;




import dao.UserHibernateDAO;
import model.User;
import org.hibernate.SessionFactory;

import java.sql.SQLException;
import java.util.List;

import static util.DBHelper.*;

public class UserService {

    private static UserService userHibernateService;
    private SessionFactory sessionFactory;

    private UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserService getInstance() {
        if (userHibernateService == null) {
            userHibernateService = new UserService(getSessionFactory());
        }
        return userHibernateService;
    }

    public void addUser(User user){
        new UserHibernateDAO(sessionFactory.openSession()).addUser(user);
    }

    public List<User> listAllUsers() throws SQLException {
        return new UserHibernateDAO(sessionFactory.openSession()).listAllUsers();
    }

    public boolean validateClient(String name) throws SQLException{
        return new UserHibernateDAO(sessionFactory.openSession()).validateClient(name);
    }

    public User getUserById(Long id)throws SQLException{
        return new UserHibernateDAO(sessionFactory.openSession()).getUserById(id);
    }

    public void updateUser(User user) throws SQLException{
        new UserHibernateDAO(sessionFactory.openSession()).updateUser(user);
    }

    public boolean deleteUser(Long id) throws SQLException{
        return new UserHibernateDAO(sessionFactory.openSession()).deleteUser(id);
    }
}
