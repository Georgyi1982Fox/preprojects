package dao;

import org.hibernate.mapping.Property;
import util.DBHelper;

import java.io.*;
import java.sql.Connection;
import java.util.Properties;

public class UserDaoFactory {


    public UserDAO getDaoType(String daoType) throws IOException{
        InputStream inputStream = new FileInputStream("config.properties");

        Properties pr = new Properties();
        pr.load(inputStream);
        String type = pr.getProperty("userDAO");

        if(daoType == null){
            return null;
        }
        else if (daoType == type) {
            return new UserHibernateDAO();
        }
        return null;
    }
}
