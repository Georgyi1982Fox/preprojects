package musicianService;

//import dao.MusicianDao;
import dao.UserDao;
import dbexception.DBException;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    public UserService() {
    }

    private static UserDao getUserDao() {
        return new UserDao(getMysqlConnection());
    }

    public List<User> listAllUsers() throws DBException {
        try {
            return getUserDao().listAllUsers();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }


    public void addUser(User user) throws DBException, SQLException {
            if(!getUserDao().validateClient(user.getName())) {
                try {
                    getUserDao().addUser(user);
                } catch (SQLException e) {
                    throw new DBException(e);
                }
            }
    }

    public boolean deleteUser(Long id) throws DBException, SQLException {
            {
                return getUserDao().deleteUser(id);
            }
        }


    public void updateUser(User user) throws DBException {

        try {
            getUserDao().updateUser(user);
        } catch (SQLException e) {
            throw new DBException (e);
        }
    }


    public boolean validateClient (String name) throws DBException {
        try {
            return   getUserDao().validateClient(name);
        } catch (SQLException e){
        throw new DBException(e);
       }
    }

    public User getUserById(Long id)throws DBException{
        try {
            return getUserDao ( ).getUserById(id);
        }catch (SQLException e){
            throw new DBException(e);
        }
    }
    public void cleanUp()throws DBException{
        UserDao userDao = getUserDao ();
        try {
            userDao.deleteTable ();
        }catch (SQLException e){
            throw new DBException(e);
        }
    }
    public void createTable() throws DBException{
        UserDao userDao = getUserDao ();
        try {
            userDao.createTable();
        }catch (SQLException e){
            throw new DBException(e);
        }
    }

    private static Connection getMysqlConnection(){
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();
            url.
                    append ("jdbc:mysql://").        //db type
                    append ("localhost:").           //host name
                    append ("3306/").                //port
                    append ("db_example?").          //db name
                    append ("user=root&").          //login
                    append ("password=hjk_Esk1982!").   //password
                    append("&serverTimezone=UTC");//timezone

            System.out.println("URL: " + url + "\n");
            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        }catch(SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e){
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}
