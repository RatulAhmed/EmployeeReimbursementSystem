package com.ers.services;

import com.ers.dao.ManagerDAO;
import com.ers.models.Manager;

public class ManagerService {

    private static ManagerService managerService = new ManagerService();

    private ManagerService() { }


    public static ManagerService getInstance() {
        if (managerService == null) {
            return new ManagerService();
        }
        return managerService;
    }



    public Manager authenticate(String email, String password) {
        if(email == null || password == null) {
            return null;
        }
        return ManagerDAO.getInstance().getByEmailAndPassword(email, password);
    }



}
