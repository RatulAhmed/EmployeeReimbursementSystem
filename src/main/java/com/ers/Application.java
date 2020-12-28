package com.ers;

import com.ers.controllers.*;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Application {

    public static void main(String[] args) {

        // creating the Javalin app
        Javalin app = Javalin
                            .create(config -> {
                                config.addStaticFiles("/public");
                                config.contextPath = "/";

                                // Employee static files
                                config.addStaticFiles("/employeeLogin", "public/employee/employeeLogin.html",Location.CLASSPATH);
                                config.addStaticFiles("/employeeHome", "public/employee/employeeHome.html",Location.CLASSPATH);
                                config.addStaticFiles("/newReimbursement", "public/employee/reimbursementForm.html", Location.CLASSPATH);
                                config.addStaticFiles("/pendingReimbursements", "public/employee/pendingRequests.html", Location.CLASSPATH);
                                config.addStaticFiles("/resolvedReimbursements", "public/employee/resolvedRequests.html", Location.CLASSPATH);
                                config.addStaticFiles("/updateEmployee", "public/employee/employeeUpdateForm.html", Location.CLASSPATH);


                                // Manager static files
                                config.addStaticFiles("/managerLogin", "public/manager/managerLogin.html",Location.CLASSPATH);
                                config.addStaticFiles("/managerHome", "public/manager/managerHome.html", Location.CLASSPATH);
                                config.addStaticFiles("/pendingReimbursementsManager", "public/manager/pendingRequestsManager.html", Location.CLASSPATH);
                                config.addStaticFiles("/resolvedReimbursementsManager", "public/manager/resolvedRequestsManager.html", Location.CLASSPATH);
                                config.addStaticFiles("/allEmployees", "public/manager/allEmployees.html", Location.CLASSPATH);
                                config.addStaticFiles("/viewReimbursements", "public/manager/viewReimbursements.html", Location.CLASSPATH);
                            })
                .start(8080);

        /** Before to check we are still authenticated **/
        app.before("/employeeHome", EmployeeController.checkIfAuthenticated);


        /************************** API routes **************************/

        /**
         *  employees/*
         */
        app.post("api/employees/login", EmployeeController.login);
         app.get("api/employees/logout", EmployeeController.logout);
        app.post("api/employees/:id/reimbursements", EmployeeController.submitReimbursement);
        app.get("api/employees/:id/reimbursements/pending", ReimbursementController.viewPendingReimbursement);
        app.get("api/employees/:id/reimbursements/resolved", ReimbursementController.viewResolvedReimbursement);
        app.get("api/employees/:id", EmployeeController.getEmployeeInformation);
        app.get("api/employees/employee/:email", EmployeeController.getAllEmployeesByEmail);
        app.get("api/employees", EmployeeController.getAllEmployees);
        app.post("api/employees/:id", EmployeeController.updateEmployee);


        /**
         *  reimbursements/*
         */

//        app.put("/api/reimbursements/:id", ReimbursementController.updateReimbursement);
        // deleteable
//        app.get("/api/reimbursements", ReimbursementController.getAllReimbursements);
        app.get("api/reimbursements/pending", ReimbursementController.getAllPending);
        app.get("api/reimbursements/resolved", ReimbursementController.getAllResolved);
        app.get("api/reimbursements/:customerId", ReimbursementController.getAllReimbursementsForEmployee);
        /**
         * managers/*
         */
        app.post("api/managers/login", ManagerController.login);
        app.post("api/managers/logout", ManagerController.logout);


        /**
         *  Error mappings
         */
//        app.error(404);

    }
}
