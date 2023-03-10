package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String HOST = "jdbc:mysql://localhost:3306/pp_java";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "1111";
    private static final String DIALECT = "org.hibernate.dialect.MySQLDialect";
    private static SessionFactory sessionFactory = null;

    private static final Configuration configuration = new Configuration()
            .setProperty("hibernate.connection.driver_class", DRIVER)
            .setProperty("hibernate.connection.url", HOST)
            .setProperty("hibernate.connection.username", LOGIN)
            .setProperty("hibernate.connection.password", PASSWORD)
            .setProperty("hibernate.dialect", DIALECT)
            .addAnnotatedClass(User.class);

    public static SessionFactory getConnection(){
        try {
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    public static Connection getMySQLConnection() {
        try {
            return DriverManager.getConnection(HOST, LOGIN, PASSWORD);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}