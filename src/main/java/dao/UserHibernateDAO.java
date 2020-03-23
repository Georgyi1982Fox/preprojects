package dao;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import util.DBHelper;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAO {
   private Session session;

    public UserHibernateDAO() {
    }

    @Override
    public void addUser(User user) {
        session = DBHelper.getConfiguration().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    @Override
    public List<User> listAllUsers() throws SQLException {
        session = DBHelper.getConfiguration().openSession();
        List<User> allUsers;
         //session = DBHelper.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            allUsers = session.createQuery("FROM User").list();
            transaction.commit();
        return allUsers;
    }

    @Override
    public boolean validateClient(String name) throws SQLException {
        session = DBHelper.getConfiguration().openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User)session.createQuery("FROM User WHERE name:=name")
                .setParameter("name", name)
                .setMaxResults(1)
                .uniqueResult();
        transaction.commit();
        if(user.getName().equals(name)){
            return true;
        }
        return false;
    }

    public User getUserById(Long id)throws SQLException {
        session = DBHelper.getConfiguration().openSession();
        Transaction transaction = session.beginTransaction();
        User userById = (User) session.createQuery("FROM User WHERE id=:id")
                .setParameter("id", id)
                .setMaxResults(1)
                .uniqueResult();
        User user = new User(userById.getId(), userById.getName(),userById.getEmail());
        transaction.commit();
    return user;
    }

    @Override
    public void updateUser(User user) throws SQLException {
        session = DBHelper.getConfiguration().openSession();
        Transaction transaction = session.beginTransaction();
        Query query  = session.createQuery("update User u SET u.name=:name, u.password=:password, u.email=:email WHERE u.name=:name");
                query.setParameter("name", user.getName());
                query.setParameter("password", user.getPassword());
                query.setParameter("email", user.getEmail());
                query.setParameter("name", user.getName());
                int result = query.executeUpdate();
                transaction.commit();
                session.close();

    }

    @Override
    public boolean deleteUser(Long id) throws SQLException {
        session = DBHelper.getConfiguration().openSession();
        Transaction transaction = session.beginTransaction();
        User deleteUser = (User) session.createQuery("FROM User WHERE id=:id")
                .setParameter("id", id)
                .uniqueResult();
        session.delete(deleteUser);
        transaction.commit();
        return true;
    }
}















