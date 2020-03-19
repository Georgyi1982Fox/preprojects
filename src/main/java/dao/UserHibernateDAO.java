package dao;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
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
            allUsers = session.createQuery("FROM User").list();
            transaction.commit();
        } finally {
            session.close();
        }
        return allUsers;
    }

    @Override
    public boolean validateClient(String name) throws SQLException {
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
        Transaction transaction = session.beginTransaction();
        User userById = (User) session.createQuery("FROM User WHERE id=:id")
                .setParameter("id", id)
                .setMaxResults(1)
                .uniqueResult();
        User user = new User(userById.getId(), userById.getName(),userById.getEmail());
        transaction.commit();
        session.close();
    return user;
    }

    @Override
    public void updateUser(User user) throws SQLException {
        //Transaction transaction = session.beginTransaction();
        Query query  = session.createQuery("update User u set name =:name, password=:password, email=:email WHERE name=:name");
                query.setParameter("name", user.getName());
                query.setParameter("password", user.getPassword());
                query.setParameter("email", user.getEmail());
                query.setParameter("name", user.getName());
                int result = query.executeUpdate();
                session.getTransaction().commit();
                session.close();
    }

    @Override
    public boolean deleteUser(Long id) throws SQLException {
        Transaction transaction = session.beginTransaction();
        User deleteUser = (User) session.createQuery("FROM User WHERE id=:id")
                .setParameter("id", id)
                .uniqueResult();
        session.delete(deleteUser);
        transaction.commit();
        session.close();
        return true;
    }
}















