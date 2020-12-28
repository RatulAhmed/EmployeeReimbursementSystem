package com.ers.dao;

import com.ers.models.Reimbursement;
import com.ers.util.HibernateUtil;
import org.hibernate.*;

import java.util.Collection;
import java.util.List;

public class ReimbursementDAO implements Repository<Reimbursement, Integer> {

    private static ReimbursementDAO reimbursementDAO = new ReimbursementDAO();
    private SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    private ReimbursementDAO() {

    }

    public static ReimbursementDAO getInstance() {
        if(reimbursementDAO == null) {
            reimbursementDAO = new ReimbursementDAO();
        }
        return reimbursementDAO;
    }

    /**
     *
     * @return A collection of Reimbursement objects
     */
    @Override
    public Collection<Reimbursement> getAll() {

        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Reimbursement.class);
        return criteria.list();
    }

    /**
     * Did not use this function
     * @param integer
     * @return
     */
    @Override
    public Reimbursement getById(Integer integer) {
        return null;
    }


    /**
     * Will save a new user
     * @param obj
     * @return Integer
     */
    @Override
    public Integer save(Reimbursement obj) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Integer result = (Integer) session.save(obj);
        transaction.commit();
        session.close();
        return result;
    }

    @Override
    public void delete(Reimbursement obj) {

    }

    /**
     *
     * @param employeeId - a foreign key that maps to a employee
     * @param status - a String that represents the status of a request
     *               either "PENDING" "APPROVED" or "DENIED"
     * @return A list of Reimbursements with the status of String status and id of Integer employeeId
     */
    public List<Reimbursement> getAllForEmployeeByStatus(Integer employeeId, String status) {
        status = status.toUpperCase();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from Reimbursement where employee_id=:id and status=:status";
        Query query = session.createQuery(hql);

        query.setInteger("id", employeeId);
        query.setString("status", status);

        List<Reimbursement> reimbursements = query.list();
        session.close();
        return reimbursements;
    }

    /**
     * Will retrieve all reimbursments for a specific employee
     * @param employeeId
     * @return
     */
    public List<Reimbursement> getAllForEmployee(Integer employeeId) {
        Session session = sessionFactory.openSession();
        String hql = "from Reimbursement where employee_id=:id";
        Query query = session.createQuery(hql);
        query.setInteger("id", employeeId);
        List<Reimbursement> reimbursements = query.list();
        session.close();
        return reimbursements;
    }

    /**
     * Will return all reimbursements with the same status
     * @param status
     * @return
     */
    public List<Reimbursement> getAllByStatus(String status) {
        status = status.toUpperCase();
        Session session = sessionFactory.openSession();
        String hql = "from Reimbursement where status=:status";
        Query query = session.createQuery(hql);
        query.setString("status", status);
        List<Reimbursement> reimbursements = query.list();
        session.close();
        return reimbursements;
    }

    /**
     * Will update a reimbursement
     * @param reimbursement
     * @return
     */
    public Reimbursement update(Reimbursement reimbursement) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(reimbursement);
        tx.commit();
        return reimbursement;
    }
}
