package com.ers.dto;


/**
 * DTO class so that we don't have to send sensitive Employee info to server
 */
public class EmployeeDTO {

    private int id;
    private String email;


    public EmployeeDTO(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public EmployeeDTO() {


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
