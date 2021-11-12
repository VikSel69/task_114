package jm.task.core.jdbc.dao;

import org.hibernate.Session;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE if NOT EXISTS users ( id BIGINT NOT NULL AUTO_INCREMENT , " +
                "name VARCHAR(50) NOT NULL , last_name VARCHAR(50) NOT NULL , " +
                "age TINYINT NOT NULL , PRIMARY KEY (id)) ENGINE = InnoDB";
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE if EXISTS bdusers . users  CASCADE ;";
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        List <User> users = (List<User>) session.createQuery("FROM User ").list();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        String sql;
        sql = "truncate table  bdusers . users;";
        session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
