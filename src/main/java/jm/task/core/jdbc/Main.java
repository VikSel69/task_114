package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        String testName = "Ivan";
        String testLastName = "Ivanov";
        byte testAge = 5;

        UserServiceImpl table = new UserServiceImpl();

        table.dropUsersTable();
        table.createUsersTable();
        table.saveUser(testName, testLastName, testAge);

        List<User> userList = table.getAllUsers();
        System.out.println(userList.get(0).getName());
        System.out.println(userList.get(0).getLastName());
        System.out.println(userList.get(0).getAge());
    }
}
