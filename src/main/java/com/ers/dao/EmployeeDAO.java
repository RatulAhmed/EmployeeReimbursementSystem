package com.ers.dao;

import com.ers.models.Employee;
import com.ers.models.Reimbursement;
import com.ers.util.HibernateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.*;

import java.util.Collection;
import java.util.List;

public class EmployeeDAO implements Repository<Employee, Integer> {

    /**
     * Singleton definition
     */
    private static EmployeeDAO employeeDAO = new EmployeeDAO();
    private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    private static final Logger logger = LogManager.getLogger(EmployeeDAO.class);


    private EmployeeDAO() {}

    public static EmployeeDAO getInstance() {
        if(employeeDAO == null) {
            employeeDAO = new EmployeeDAO();
        }
        return employeeDAO;
    }

    /**
     * Return a collection of all employees
     * @return
     */
    @Override
    public Collection<Employee> getAll() {
        Session session = sessionFactory.openSession();
        String hql ="from Employee";
        Query query = session.createQuery(hql);
        List<Employee> employeeList =  query.list();
        session.close();
        return employeeList;
    }

    /**
     * Get a specific employee by the id
     * @param employeeId
     * @return
     */
    @Override
    public Employee getById(Integer employeeId) {
        Employee employee = null;
        Session session = sessionFactory.openSession();
        String hql = "from Employee where id=:id";
        Query query = session.createQuery(hql);
        query.setInteger("id", employeeId);
        return (employee = (Employee) query.uniqueResult());
    }

    /**
     * Persist a new Employee object
     * @param obj
     * @return
     */
    @Override
    public Integer save(Employee obj) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(obj);
        tx.commit();
        session.close();
        return 1;
    }

    @Override
    public void delete(Employee obj) {
    }

    /**
     * Retrieve a employee by email and password - used for auth
     * @param email
     * @param password
     * @return
     */
    public Employee getByEmailAndPassword(String email, String password) {
        Employee employee = null;
        Session session = sessionFactory.openSession();

        String hql = "from Employee where email=:e and password=:p";
        Query query = session.createQuery(hql);
        query.setString("e", email);
        query.setString("p", password);

        List employeeList = query.list();

        employee = (Employee) employeeList.get(0);
        session.close();
        return employee;
    }

    /**
     * Update an employee's information
     * @param employee
     */
    public void update(Employee employee) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(employee);
        tx.commit();
        session.refresh(employee);
        session.close();
    }

    /**
     * Get user details from email
     * @param email
     * @return
     */
    public Employee getByEmail(String email) {
        Employee employee;
        Session session = sessionFactory.openSession();
        String hql = "from Employee where email=:e";
        Query query = session.createQuery(hql);
        query.setString("e", email);


        List employeeList = query.list();

        return employee = (Employee) employeeList.get(0);
    }
}
