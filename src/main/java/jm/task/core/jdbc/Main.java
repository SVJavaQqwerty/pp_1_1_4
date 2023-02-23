package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

        userDaoJDBC.createUsersTable(); // работает
        userDaoJDBC.saveUser("Name", "LastName", (byte) 65); // works
        userDaoJDBC.removeUserById(6); // works

        List<User> users = userDaoJDBC.getAllUsers();
        for (User user: users) {
            System.out.println(user.toString());
        }

        userDaoJDBC.cleanUsersTable(); // Работает
        userDaoJDBC.dropUsersTable(); // Работает
    }
}
