package utils;

import models.Lot;
import models.LotProperty;
import models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration()
                        .configure() // use hibernate.cfg.xml
                        .addAnnotatedClass(models.User.class)
                        .addAnnotatedClass(models.Lot.class)
                        .addAnnotatedClass(models.LotProperty.class)
                        .buildSessionFactory();
            } catch (Exception e) {
                System.out.println("EXCEPTION! ");
                e.printStackTrace();
            }
        }

        return sessionFactory;
    }
    public static void close() {
        getSessionFactory().close();
    }

}
