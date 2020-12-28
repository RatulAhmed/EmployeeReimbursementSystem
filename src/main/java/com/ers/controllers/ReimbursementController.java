package com.ers.controllers;

import com.ers.models.Reimbursement;
import com.ers.services.ReimbursementService;
import io.javalin.http.Handler;

import java.util.List;

public class ReimbursementController {


    /**
     * Will get all reimbursements with status of "PENDING" for a specific employee
     */
    public static Handler viewPendingReimbursement = ctx -> {

        try {
            int employeeId = Integer.parseInt(ctx.pathParam("id"));
            List<Reimbursement> reimbursements = ReimbursementService.getInstance().getAllPendingReimbursementsForEmployee(employeeId);
            ctx.status(200);
            ctx.json(reimbursements);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    };

    /**
     * Will get all reimbursements with the status of "RESOLVED"
     * For a particular employee
     */
    public static Handler viewResolvedReimbursement = ctx -> {
        try {
            int employeeId = Integer.parseInt(ctx.pathParam("id"));
            List<Reimbursement> reimbursements = ReimbursementService.getInstance().getAllResolvedReimbursementsForEmployee(employeeId);
            ctx.status(200);
            ctx.json(reimbursements);
        } catch (NumberFormatException e) {
            ctx.status(400);
            e.printStackTrace();
        }
    };

    /**
     * will get all pending requests
     */
    public static Handler getAllPending = ctx -> {
        List<Reimbursement> reimbursementList = ReimbursementService.getInstance().getAllPendingReimbursements();
        ctx.json(reimbursementList);
    };

    /**
     * will get all resolved requests
     */
    public static Handler getAllResolved = ctx -> {
        List<Reimbursement> reimbursementList = ReimbursementService.getInstance().getAllResolvedReimubrsements();
        ctx.json(reimbursementList);
    };

    /**
     * Get ALL reimbursements for an employee
     */
    public static Handler getAllReimbursementsForEmployee = ctx -> {
        int customerId = Integer.parseInt(ctx.pathParam("customerId"));
        List<Reimbursement> reimbursementList = ReimbursementService.getInstance().getAllReimbursementsForEmployee(customerId);
        ctx.json(reimbursementList);
    };
}
