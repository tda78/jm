package dao;

import util.PropertyReader;

import java.io.IOException;
import java.util.Properties;

public class UserDaoFactory {
    public static UserDAO getDao() throws Exception {
        Properties properties = null;
        try {
            properties = PropertyReader.readDaoTypeProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DaoType type = DaoType.valueOf(properties.getProperty("daotype"));
        if (type.equals(DaoType.HIBERNATE))return new UserHibernateDAO();
        if (type.equals(DaoType.JDBC))return new UserJdbcDAO();
        throw new IllegalArgumentException("this DAO type is not declared");
    }
}
