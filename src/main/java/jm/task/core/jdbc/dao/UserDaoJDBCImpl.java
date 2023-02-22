package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getMySQLConnection;

public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n" +
                "id INT auto_increment NOT null,\n" +
                "name varchar(100) NULL,\n" +
                "lastName varchar(100) NULL,\n" +
                "age DECIMAL null,\n" +
                "primary key (id)\n" +
                ");";
        try (PreparedStatement preparedStatement = Util.getMySQLConnection().prepareStatement(sql)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE users;";
        try(Connection connect = Util.getMySQLConnection();
            PreparedStatement preparedStatement = connect.prepareStatement(sql)){
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = Util.getMySQLConnection().createStatement()) {
            statement.executeUpdate("insert into users set name='" + name + "', lastName='"
                    + lastName + "', age=" + age + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = Util.getMySQLConnection().createStatement()) {
            statement.executeUpdate("delete from users where id = " + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = Util.getMySQLConnection().createStatement()) {
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

    public void cleanUsersTable() throws SQLException, ClassNotFoundException {
        try(Statement statement = Util.getMySQLConnection().createStatement()) {
            statement.executeUpdate("delete from users;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
