package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private Session session;

    public UserHibernateDAO(Session session) {

    }

    @Override
    public void addUser(User user) {
        try {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }

        }

    }

    @Override
    public List<User> listAllUsers() throws SQLException {
        List<User> allUsers;
        try {
            Transaction transaction = session.beginTransaction();
            allUsers = session.createQuery("FROM users").list();
            transaction.commit();
        } finally {
            session.close();
        }
        return allUsers;
    }

    @Override
    public boolean validateClient(String name) throws SQLException {
        Transaction transaction = session.beginTransaction();
        User user = (User)session.createQuery("FROM users WHERE name:=name")
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
        Transaction transaction = session.beginTransaction();
        User userById = (User) session.createQuery("FROM users WHERE id:=id")
                .setParameter("id", id)
                .uniqueResult();
        transaction.commit();
        session.close();
    return userById;

    }

    @Override
    public void updateUser(User user) throws SQLException {
        Transaction transaction = session.beginTransaction();
        User updateUser = (User) session.createQuery("FROM users");
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public boolean deleteUser(Long id) throws SQLException {
        Transaction transaction = session.beginTransaction();
        User deleteUser = (User) session.createQuery("FROM users WHERE id:=id")
                .setParameter("id", id)
                .uniqueResult();
        session.delete(deleteUser);
        transaction.commit();
        session.close();
        return true;
    }
}















