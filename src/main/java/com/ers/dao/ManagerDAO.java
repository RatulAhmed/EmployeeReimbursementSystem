package com.ers.dao;

import com.ers.models.Employee;
import com.ers.models.Manager;
import com.ers.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Collection;
import java.util.List;

public class ManagerDAO implements Repository<Manager, Integer>{

    /**
     *  Singleton class declaration
     */
    private static ManagerDAO managerDAO = new ManagerDAO();
    private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    private ManagerDAO() {

    }

    public static ManagerDAO getInstance() {
        if(managerDAO == null) {
            return new ManagerDAO();
        }
        return managerDAO;
    }

    /**
     *
     * @return A collection of All managers
     * not used
     */
    @Override
    public Collection<Manager> getAll() {
        return null;
    }

    /**
     * Not used
     * @param integer
     * @return
     */
    @Override
    public Manager getById(Integer integer) {
        return null;
    }

    /**
     * Not used
     * @param obj
     * @return
     */
    @Override
    public Integer save(Manager obj) {
        return null;
    }

    /**
     * not used
     * @param obj
     */
    @Override
    public void delete(Manager obj) {

    }

    /**
     * Retrieve a Manager but email and password, used for auth
     * @param email
     * @param password
     * @return
     */
    public Manager getByEmailAndPassword(String email, String password) {
        Manager manager = null;
        Session session = sessionFactory.openSession();

        String hql = "from Manager where email=:e and password=:p";
        Query query = session.createQuery(hql);
        query.setString("e", email);
        query.setString("p", password);

        List managerList = query.list();

        manager = (Manager) managerList.get(0);
        session.close();
        return manager;

    }
}
