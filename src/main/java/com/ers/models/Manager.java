package com.ers.models;

import javax.persistence.*;

@Entity
@Table(name="manager")
public class Manager {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;
}
