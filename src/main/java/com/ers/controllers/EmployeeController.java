package com.ers.controllers;

import com.ers.dto.EmployeeDTO;
import com.ers.models.Employee;
import com.ers.models.Reimbursement;
import com.ers.services.EmployeeService;
import com.ers.services.ReimbursementService;
import io.javalin.http.Handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class EmployeeController {

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);


    /**
     * Going to take in form data to create a new Reimbursement object
     * Temp Employee object with EmployeeId that was stored server side
     * Context Params -- FormPararms{email: , password:}
     */
    public static Handler submitReimbursement = ctx -> {
        Reimbursement newReimbursement = new Reimbursement();
//        System.out.println(ctx.formParamMap());

        try {
            logger.debug("ctx.formParamMap()");
            Employee employee = new Employee(Integer.parseInt(ctx.pathParam("id")));
           logger.debug("Amount from form param is " + ctx.formParam("amount"));
            newReimbursement.setAmount(Double.parseDouble(ctx.formParam("amount")));
            newReimbursement.setDescription(ctx.formParam("description"));
            newReimbursement.setStatus("PENDING");
            newReimbursement.setEmployee(employee);
            newReimbursement.setEmployee_id(employee.getId());
            ReimbursementService.getInstance().submitReimbursement(newReimbursement);
            ctx.status(201);
            ctx.redirect("/employeeHome");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            ctx.status(500);
            ctx.result("Error submitting your request");
        }
    };


    /**
     * @param will be in the path and is the EmployeeId
     *             For now we are just sending username and password
     *             TODO add other fields like phone number address etc
     */
    public static Handler getEmployeeInformation = ctx -> {
        try {

            EmployeeDTO response = new EmployeeDTO();
            logger.debug(ctx.pathParam("id"));
            Employee employee = EmployeeService.getInstance().getEmployee(Integer.parseInt(ctx.pathParam("id")));
            response.setEmail(employee.getEmail());
            response.setId(employee.getId());
            ctx.status(200);
            ctx.json(response);

        } catch (NumberFormatException e ) {
            e.printStackTrace();
        }
    };

    /**
     * Will retrieve all employees in database and return as json
     */
    public static Handler getAllEmployees = ctx -> {

        List<Employee> employees = EmployeeService.getInstance().getAllEmployees();
        ctx.json(employees);

    };

    /**
     * Will check for which values are changed in employee and send them for updating
     */
    public static Handler updateEmployee = ctx -> {
        Employee employee = ctx.sessionAttribute("loggedInEmployee");
        System.out.println("Inside of EmployeeController.updateEmployee >>>The employee is " + employee);
        for(Map.Entry<String, List<String>> entry : ctx.formParamMap().entrySet()) {
            if(entry.getValue().get(0).equals("")) {
                System.out.println("this is a null value");

            } else {
                if(entry.getKey().equals("email")) {
                    employee.setEmail(entry.getValue().get(0));

                }
                if(entry.getKey().equals("password")) {
                    employee.setPassword((entry.getValue().get(0)));
                }
            }
            logger.debug("We are in update Employee: The value is \" + entry.getValue().get(0)");
//                System.out.println("The value is " + entry.getValue().get(0));
//                System.out.println("The key is " + entry.getKey());
        }
        EmployeeService.getInstance().createOrUpdateEmployee(employee);
        logger.info("Inside of updateEmployee: Redirecting to employeeHome");
        ctx.redirect("/employeeHome");
    };

    /**
     * Will retrieve a single employee by email address
     */
    public static Handler getAllEmployeesByEmail = ctx -> {
        String email = ctx.pathParam("email");
        logger.debug("We are in getAllEmployeeByEmail: The email is " + email);
        Employee employee = EmployeeService.getInstance().getEmployeeByEmail(email);
        ctx.json(employee);
    };

    /*** Employee Auth Controller Handlers **/

    public static Handler login = ctx -> {

        Employee employee = null;
        try {
            if (ctx.formParamMap().containsKey("email") && ctx.formParamMap().containsKey("password")) {
                employee = EmployeeService.getInstance().authenticate(ctx.formParam("email"), ctx.formParam("password"));

                // 403 forbidden - incorrect credentials
                if(employee == null) {
                    logger.info("Incorrect credentials should return 403");
                    ctx.status(403);
                    ctx.redirect("/employeeLogin");
                }
                else {
                    // now we have to build the correct response and set Session info
                    ctx.status(200);
                    logger.info("Correct credentials should return 200");
                    ctx.contentType("application/json");
                    ctx.sessionAttribute("loggedInEmployee", employee);
                    ctx.redirect("/employeeHome");
                }
            }
        } catch (Exception exception) {
            System.out.println("Error");
            exception.printStackTrace();
        }
    };

    public static Handler logout = ctx -> {
        ctx.req.getSession().invalidate();
        ctx.sessionAttribute("loggedInEmployee", null);
        ctx.redirect("/index.html");
    };


    //TODO fix this
    public static Handler checkIfAuthenticated = ctx -> {

        if(ctx.sessionAttribute("loggedInEmployee") != null) {
            logger.info("The session is authenticated");
        }
        else {
            logger.info("Redirecting to login page");
            ctx.redirect("/employeeLogin");
        }
    };
}
