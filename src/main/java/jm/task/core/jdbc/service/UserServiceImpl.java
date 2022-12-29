package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
//  UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
  private UserDaoHibernateImpl userDAoImpl =new UserDaoHibernateImpl();

    public void createUsersTable() {

        userDAoImpl.createUsersTable();
    }

    public void dropUsersTable() {
        userDAoImpl.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {
        userDAoImpl.saveUser(name, lastName, age);

    }

    public void removeUserById(long id) {

        userDAoImpl.removeUserById(id);
    }

    public List<User> getAllUsers() {

        return userDAoImpl.getAllUsers();
    }

    public void cleanUsersTable() {

        userDAoImpl.cleanUsersTable();
    }
}
