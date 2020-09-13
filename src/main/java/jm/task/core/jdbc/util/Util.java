package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static SessionFactory sessionFactory;

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

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/testbd" +
                        "?useSSL=false&useUnicode=true&serverTimezone=UTC");
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "root");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}
