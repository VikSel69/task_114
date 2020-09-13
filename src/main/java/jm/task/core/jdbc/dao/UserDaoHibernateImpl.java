package jm.task.core.jdbc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        SessionFactory sessionFactory =  Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            String sql;
            sql = "CREATE TABLE IF NOT EXISTS users ( `id` BIGINT NOT NULL AUTO_INCREMENT , " +
                    "`name` VARCHAR(50) NOT NULL , " +
                    "`last_name` VARCHAR(50) NOT NULL , " +
                    "`age` TINYINT NOT NULL , " +
                    "PRIMARY KEY (`id`)) ENGINE = InnoDB";
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        SessionFactory sessionFactory =  Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            String sql;
            sql = "drop table if exists `testbd` . users  cascade;";
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        SessionFactory sessionFactory =  Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        SessionFactory sessionFactory =  Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        SessionFactory sessionFactory =  Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        List <User> users = new ArrayList<>();
        try {
            session.beginTransaction();
            users = session.createQuery("FROM User ").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        SessionFactory sessionFactory =  Util.getSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            String sql;
            sql = "truncate table  `testbd` . users;";
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
