package models;

import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class UserManager {
    private SessionFactory sessionFactory;

    public void init() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Lot.class);
                configuration.addAnnotatedClass(LotProperty.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
                e.printStackTrace();
            }
        }

        // if (this.sessionFactory == null) {
        //     try {
        //         this.sessionFactory = new Configuration()
        //                 .configure() // use hibernate.cfg.xml
        //                 .addAnnotatedClass(models.User.class)
        //                 .addAnnotatedClass(models.Lot.class)
        //                 .addAnnotatedClass(models.LotProperty.class)
        //                 .buildSessionFactory();
        //     } catch (Exception e) {
        //         System.out.println("EXCEPTION! ");
        //         e.printStackTrace();
        //     }
        // }

    }

    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User", User.class);
            return query.list();
        }
    }

    public void addUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }

    public User getUserById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            return user;
        }
    }

    public boolean saveUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            // session.flush();
            transaction.commit();
            // session.evict(user);

            return true;

        } catch(Throwable t) {
            t.printStackTrace();
            return false;
        }
    }

    public boolean removeUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    public User getLastUser() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery("from User where id > 100", User.class);
            ScrollableResults<User> scroll = query.scroll();
            scroll.last();
            User lastUser = scroll.get();
            return lastUser;
        }
    }

    public User updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User updUser = session.merge(user);
            transaction.commit();
            return updUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateUser1(User user) {
        try (Session session = sessionFactory.openSession()) {
            // User updUser = session.merge(user);
            session.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
