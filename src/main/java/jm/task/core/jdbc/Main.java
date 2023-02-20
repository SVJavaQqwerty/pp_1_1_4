package jm.task.core.jdbc;

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

//import static jm.task.core.jdbc.util.Util.getMySQLConnection;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserService u = new UserServiceImpl();

        String name = "Vladimir";
        String lastName = "Srdyukov";
        byte age = 33;

        //UserDaoJDBCImpl us = new UserDaoJDBCImpl();
        // создаем таблицу
        u.createUsersTable();
        // Добавляем пользователя
        for (byte i = 0; i < 4; i++) {
            u.saveUser(name, lastName, age++);
        }
        // Чтение всех пользователей
        List<User> g = u.getAllUsers();
        for(User i : g) {
            System.out.println(i.toString());
        }
        // удаляем одного юзера
        u.removeUserById(3);
        //Удаляем таблицу Users
        //us.dropUsersTable();
        u.createUsersTable();

    }
}
