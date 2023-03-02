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
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Name", "Lastname", (byte)23);
        List<User> users = userService.getAllUsers();
        for (User user: users) {
            System.out.println(user.toString());
        }
        userService.removeUserById(1L);
        userService.createUsersTable();
        userService.dropUsersTable();
    }
}
