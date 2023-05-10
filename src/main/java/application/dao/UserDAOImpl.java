package application.dao;

import application.models.User;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import application.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    public UserDAOImpl() {

    }

    @Override
    public void add(User user) throws Exception {
        try (Session session = HibernateSessionFactoryUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            System.out.println("User was add: " + user + '\n');
        }
    }

    @Override
    public List<User> getAll() throws Exception {
        try (Session session = HibernateSessionFactoryUtil.getSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            System.out.println("ALL users: \n" + query.list() + '\n');
            return query.list();
        }
    }

    @Override
    public User getById(Integer userId) throws Exception {
        try (Session session = HibernateSessionFactoryUtil.getSession()) {
            User user = session.get(User.class, userId);
            System.out.println(user != null ?
                    ("User by id: " + userId + ": " + user + '\n') : ("There's NO user with id: " + userId + '\n'));
            return user;
        }
    }

    @Override
    public User update(User user) throws Exception {
        try (Session session = HibernateSessionFactoryUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            User updUser = session.merge(user);
            transaction.commit();
            System.out.println("Updated user: " + user.getUserId() + ": " + updUser + '\n');
            return updUser;
        }
    }

    @Override
    public void deleteAll() throws Exception {
        try (Session session = HibernateSessionFactoryUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            Query<User> query = session.createNativeQuery("DELETE FROM Users", User.class); //added Generic <User>
            query.executeUpdate();
            transaction.commit();
            System.out.println("ALL users was DELETE \n");
        }
    }

    @Override
    public void deleteById(Integer userId) throws Exception {
        try (Session session = HibernateSessionFactoryUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            User userForRemove = getById(userId);
            System.out.println("USER FOR REMOVE: " + userForRemove);
            session.remove(userForRemove);
            transaction.commit();
            System.out.println("User with id: " + userId + " was DELETED \n");
        }
    }

    public User getFirstUser() throws Exception {
        try (Session session = HibernateSessionFactoryUtil.getSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            ScrollableResults<User> scroll = query.scroll();
            scroll.first();
            User firstUser = scroll.get();
            System.out.println("FIRST user is: " + firstUser + '\n');
            return firstUser;
        }
    }

    public User getLastUser() throws Exception {
        try (Session session = HibernateSessionFactoryUtil.getSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            ScrollableResults<User> scroll = query.scroll();
            scroll.last();
            User lastUser = scroll.get();
            System.out.println("LAST user is: " + lastUser + '\n');
            return lastUser;
        }
    }

}
