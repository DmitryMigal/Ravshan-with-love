package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import static java.awt.Event.DELETE;
import static org.hibernate.hql.internal.antlr.HqlTokenTypes.FROM;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final static SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS USERS1(ID SERIAL NOT NULL,NAME VARCHAR,LASTNAME VARCHAR," +
                "AGE INTEGER, UNIQUE(id));").executeUpdate();
        session.getTransaction().commit();

    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS USERS1;").executeUpdate();
        session.getTransaction().commit();
        session.close();




    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = (User) session.get(User.class,id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();


    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users;
        users = session.createQuery("FROM User").list();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("TRUNCATE TABLE USERS1;").executeUpdate();
        session.getTransaction().commit();
        session.close();

    }
}
