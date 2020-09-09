package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static SessionFactory sessionFactory;

    static {
        try {
            Properties prop = new Properties();
            prop.put(Environment.URL, "jdbc:mysql://localhost:3306/testbd" +
                    "?useSSL=false&useUnicode=true&serverTimezone=UTC");
            prop.put(Environment.USER, "root");
            prop.put(Environment.PASS, "root");
            prop.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
            prop.put(Environment.SHOW_SQL, true);
            prop.put(Environment.HBM2DDL_AUTO, "update");
            prop.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

            Configuration configuration = new Configuration().setProperties(prop);
            System.out.println(configuration.getProperties());
            configuration.addAnnotatedClass(User.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Exception e) {
            System.out.println("Исключение!" + e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Connection getMySQLConnection() throws SQLException {
        String hostName = "localhost";
        String dbName = "testbd";
        String userName = "root";
        String password = "root";
        return getMySQLConnection(hostName, dbName, userName, password);
    }
    private static Connection getMySQLConnection(String hostName, String dbName, String userName, String password)
            throws SQLException {
        String conUrl = "jdbc:mysql://" + hostName + ":3306/" + dbName +
                "?verifyServerCertificate=false&useSSL=false&requireSSL=false&serverTimezone=UTC";
        return DriverManager.getConnection(conUrl, userName, password);
    }

}
