package dao;


import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    void addUser(User user) throws SQLException;
    List<User>  listAllUsers() throws SQLException;
    boolean validateClient(String name) throws SQLException;
    void updateUser(User user) throws SQLException;
    boolean deleteUser(Long id) throws SQLException;
}


