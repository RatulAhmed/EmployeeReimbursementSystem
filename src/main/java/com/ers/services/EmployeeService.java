package com.ers.services;


import com.ers.dao.EmployeeDAO;
import com.ers.models.Employee;

import java.util.List;

/**
 * Singleton Service Class
 */
public class EmployeeService {

    /**
     * Create a singleton and get instance when needed
     */
    private static EmployeeService employeeService = new EmployeeService();

    private EmployeeService() {
    }

    public static EmployeeService getInstance() {
        if(employeeService == null) {
            employeeService = new EmployeeService();
        }
        return employeeService;
    }


    /**
     * EmployeeService Functionality -- optional come back to it later
     */
    public void createOrUpdateEmployee(Employee employee) {
         EmployeeDAO.getInstance().save(employee);
    }

    /**
     * Used by manager
     * @return A list of all Employees
     */
    public List<Employee> getAllEmployees() {
        return (List<Employee>) EmployeeDAO.getInstance().getAll();
    }

    /**
     *
     * @param email
     * @param password
     * @return
     */
    public Employee authenticate(String email, String password) {

        if(email == null || password == null) {
            System.out.println("The email and/or password is blank");
            return null;
        }
        return EmployeeDAO.getInstance().getByEmailAndPassword(email, password);
    }

    /**
     *
     * @param employee
     * @return
     */
    public void updateEmployeeInformation(Employee employee) {
        EmployeeDAO.getInstance().update(employee);
    }


    /**
     *
     * @param employeeId
     * @return
     */
    public Employee getEmployee(Integer employeeId) {
        return EmployeeDAO.getInstance().getById(employeeId);
    }


    public Employee getEmployeeByEmail(String email) {
        return EmployeeDAO.getInstance().getByEmail(email);
    }
}
