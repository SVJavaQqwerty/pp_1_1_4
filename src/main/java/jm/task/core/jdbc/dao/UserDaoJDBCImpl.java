package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.text.html.parser.Entity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable() {
        try (Connection connection = Util.getMySQLConnection();
              Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (\n" +
                    "id BIGINT auto_increment NOT null,\n" +
                    "name varchar(100) NULL,\n" +
                    "lastName varchar(100) NULL,\n" +
                    "age DECIMAL null,\n" +
                    "primary key (id)\n" +
                    ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Connection connection = Util.getMySQLConnection();
             Statement statement = connection.createStatement()){
            statement.executeUpdate("DROP TABLE IF EXISTS users;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getMySQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "insert into users (name, lastName, age) values (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "delete from users where id = " + id + ";";
        try (Connection connection = Util.getMySQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getMySQLConnection();
             Statement statement = connection.createStatement()) {
            ResultSet allUsers = statement.executeQuery("select * from users;");
            while (allUsers.next()) {
                User n = new User(allUsers.getString("name"), allUsers.getString("lastName"),
                        allUsers.getByte("age"));
                n.setId((long) allUsers.getInt("id"));
                users.add(n);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getMySQLConnection();
               Statement statement = connection.createStatement()) {
                statement.executeUpdate("delete from users;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
