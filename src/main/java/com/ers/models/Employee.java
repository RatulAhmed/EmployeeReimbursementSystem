package com.ers.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="employee")
public class Employee {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_reimbursement",
    joinColumns = @JoinColumn(name = "employee_id"),
    inverseJoinColumns = @JoinColumn(name = "reimbursement_id"))
    @JsonManagedReference
    private List<Reimbursement> employeeReimbursement;

    public Employee() {
    }

    public Employee(Integer employee_id) {
        this.id = employee_id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Reimbursement> getEmployeeReimbursement() {
        return employeeReimbursement;
    }

    public void setEmployeeReimbursement(List<Reimbursement> employeeReimbursement) {
        this.employeeReimbursement = employeeReimbursement;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
