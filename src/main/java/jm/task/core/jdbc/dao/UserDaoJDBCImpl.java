package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    Util util = new Util();

    public void createUsersTable() {
        try (Statement statement = util.getConnection().createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS USERS (" + "ID INTEGER, NAME VARCHAR,LASTNAME VARCHAR," +
                    "AGE INTEGER)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (Statement statement = util.getConnection().createStatement()) {
            statement.execute("drop table if exists Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement st = util.getConnection().prepareStatement("insert into Users (Name, LastName, Age) values (?, ?, ?)")) {
            st.setString(1, name);
            st.setString(2, lastName);
            st.setByte(3, age);
            st.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        String sql = "delete from Users where ID = ?";
        try (PreparedStatement st = util.getConnection().prepareStatement(sql)) {
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement st = util.getConnection().createStatement()) {
            ResultSet resultSet = st.executeQuery("select * from Users");
            while (resultSet.next()) {
                User tUser = new User();
                tUser.setId(resultSet.getLong("ID"));
                tUser.setName(resultSet.getString("Name"));
                tUser.setLastName(resultSet.getString("LastName"));
                tUser.setAge(resultSet.getByte("Age"));
                list.add(tUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement st = util.getConnection().createStatement()) {
            st.execute("truncate table Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
