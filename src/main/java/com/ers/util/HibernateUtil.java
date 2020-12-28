package com.ers.util;

import com.ers.models.Employee;
import com.ers.models.Manager;
import com.ers.models.Reimbursement;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try{
            buildSessionFactory();
        }catch(Exception e){
            System.err.println("Exception while initializing hibernate util.. ");
            e.printStackTrace();
        }
    }

    public static SessionFactory buildSessionFactory() {

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(Manager.class);
        configuration.addAnnotatedClass(Reimbursement.class);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());

        return sessionFactory;
    }

    public static Session getSession() throws HibernateException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
        } catch (HibernateException exception) {
            System.out.println("error while getting session");
        }
        return session;
    }
}
