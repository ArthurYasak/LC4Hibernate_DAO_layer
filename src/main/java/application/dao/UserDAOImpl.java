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
    public boolean add(User user) {
        // try (Session session = sessionFactory.openSession()) {
        try (Session session = HibernateSessionFactoryUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            return true;
        } catch(Throwable t) {
            t.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> getAll() {
        // try (Session session = sessionFactory.openSession()) {
        try (Session session = HibernateSessionFactoryUtil.getSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            return query.list();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getById(Integer userId) {
        // try (Session session = sessionFactory.openSession()) {
        try (Session session = HibernateSessionFactoryUtil.getSession()) {
            User user = session.get(User.class, userId);
            return user;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User update(User user) {
        // try (Session session = sessionFactory.openSession()) {
        try (Session session = HibernateSessionFactoryUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            User updUser = session.merge(user);
            transaction.commit();
            return updUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteAll() {
        // try (Session session = sessionFactory.openSession()){
        try (Session session = HibernateSessionFactoryUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            Query<User> query = session.createNativeQuery("DELETE FROM Users", User.class); //added Generic <User>
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Integer userId) {
        // try (Session session = sessionFactory.openSession()) {
        try (Session session = HibernateSessionFactoryUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            User userForRemove = getById(userId);
            session.remove(userForRemove);
            transaction.commit();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getFirstUser() {
        // try (Session session = sessionFactory.openSession()) {
        try (Session session = HibernateSessionFactoryUtil.getSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            ScrollableResults<User> scroll = query.scroll();
            scroll.first();
            User lastUser = scroll.get();
            return lastUser;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getLastUser() {
        // try (Session session = sessionFactory.openSession()) {
        try (Session session = HibernateSessionFactoryUtil.getSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            ScrollableResults<User> scroll = query.scroll();
            scroll.last();
            User lastUser = scroll.get();
            return lastUser;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
