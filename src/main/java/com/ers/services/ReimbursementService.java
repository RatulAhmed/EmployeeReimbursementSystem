package com.ers.services;

import com.ers.dao.ReimbursementDAO;
import com.ers.models.Reimbursement;

import java.util.List;

public class ReimbursementService {

    private static ReimbursementService reimbursementService = new ReimbursementService();

    private ReimbursementService() {
    }

    public static ReimbursementService getInstance() {
        if(reimbursementService == null) {
            reimbursementService = new ReimbursementService();
        }
        return reimbursementService;
    }

    /**
     *
     * @param reimbursement
     * @return
     */
    public Integer submitReimbursement(Reimbursement reimbursement) {
        return ReimbursementDAO.getInstance().save(reimbursement);
    }

    /**
     *
     * @param employeeId
     * @return
     */
    public List<Reimbursement> getAllPendingReimbursementsForEmployee(Integer employeeId) {
        String status = "PENDING";
        return ReimbursementDAO.getInstance().getAllForEmployeeByStatus(employeeId, status);
    }

    /**
     *
     * @param employeeId
     * @return
     */
    public List<Reimbursement> getAllResolvedReimbursementsForEmployee(Integer employeeId) {
        String status = "RESOLVED";
        return ReimbursementDAO.getInstance().getAllForEmployeeByStatus(employeeId, status);
    }


    /**
     *
     * @param
     * @return
     */
    public List<Reimbursement> getAllPendingReimbursements() {
        String status = "PENDING";
        return ReimbursementDAO.getInstance().getAllByStatus(status);
    }

    /**
     *
     * @param
     * @return
     */
    public List<Reimbursement> getAllResolvedReimubrsements() {
        String status = "RESOLVED";
        return ReimbursementDAO.getInstance().getAllByStatus(status);
    }


    /**
     *
     * @param employeeId
     * @return
     */
    public List<Reimbursement> getAllReimbursementsForEmployee(Integer employeeId) {
        return ReimbursementDAO.getInstance().getAllForEmployee(employeeId);
    }


    /**
     *
     * @return
     */
    public List<Reimbursement> getAllReimbursements() {
        return (List<Reimbursement>) ReimbursementDAO.getInstance().getAll();
    }

    /**
     *
     * @param reimbursement
     * @return
     */
    public Reimbursement updateReimbursement(Reimbursement reimbursement) {
        return ReimbursementDAO.getInstance().update(reimbursement);
    }


}
