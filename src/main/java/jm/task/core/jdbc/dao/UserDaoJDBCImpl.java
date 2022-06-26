package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    //language=SQL
    private final String SQL_CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, first_name VARCHAR(50) NOT NULL, last_name VARCHAR(50) NOT NULL, age TINYINT NOT NULL)";
    //language=SQL
    private final String SQL_DROP_USERS_TABLE = "DROP TABLE IF EXISTS users";
    //language=SQL
    private final String SQL_SAVE_USER = "INSERT INTO users (first_name, last_name, age) VALUES (?,?,?)";
    //language=SQL
    private final String SQL_REM0VE_USER_BY_ID = "DELETE FROM users WHERE id = ?";
    //language=SQL
    private final String SQL_GET_ALL_USERS = "SELECT id, first_name, last_name, age from users";
    //language=SQL
    private final String SQL_CLEAN_USERS_TABLE = "TRUNCATE TABLE users";

    private Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USERS_TABLE)){
            statement.executeUpdate();
            System.out.println("Table was successfully created!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {

        try (PreparedStatement statement = connection.prepareStatement(SQL_DROP_USERS_TABLE)){
            statement.executeUpdate();
            System.out.println("Table was successfully deleted!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement statement = connection.prepareStatement(SQL_SAVE_USER)){
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User " + name + " " + lastName + " was added");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {

        try (PreparedStatement statement = connection.prepareStatement(SQL_REM0VE_USER_BY_ID)){
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("User successfully removed!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_USERS)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                User user = new User(resultSet.getLong("id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getByte("age"));
                users.add(user);
                System.out.println(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        return users;
    }

    public void cleanUsersTable() {

        try (PreparedStatement statement = connection.prepareStatement(SQL_CLEAN_USERS_TABLE)){
            statement.executeUpdate();
            System.out.println("Table was cleared!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
