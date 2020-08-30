package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDaoJDBCImpl useTable = null;

    public void createUsersTable() throws SQLException {
        useTable = new UserDaoJDBCImpl();
        useTable.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        useTable = new UserDaoJDBCImpl();
        useTable.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        useTable = new UserDaoJDBCImpl();
        useTable.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        useTable = new UserDaoJDBCImpl();
        useTable.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        useTable = new UserDaoJDBCImpl();
        return useTable.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        useTable = new UserDaoJDBCImpl();
        useTable.cleanUsersTable();
    }
}
