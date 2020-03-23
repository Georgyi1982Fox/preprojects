package dao;

import org.hibernate.mapping.Property;
import util.DBHelper;

import java.io.*;
import java.sql.Connection;
import java.util.Properties;

public class UserDaoFactory {

    public UserDAO getDaoType() throws IOException {
        try (InputStream inputStream = new FileInputStream("config.properties")) {
            Properties pr = new Properties();
            pr.load(inputStream);

            String userDaoType = pr.getProperty("typeDao", "userHibernateDAO");
            if ("userHibernateDAO".equals(userDaoType)) {
                return new UserHibernateDAO();
            }
            if ("userJdbcDAO".equals(userDaoType)) {
                return new UserJdbcDAO(DBHelper.getConnection());
            } else
                return null;
        }
    }
}
