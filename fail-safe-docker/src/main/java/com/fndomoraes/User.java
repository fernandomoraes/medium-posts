package com.fndomoraes;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    protected User() {
    }

    User(final String name, final String email) {
        this.name = name;
        this.email = email;
    }

    Long getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }

    void setName(final String name) {
        this.name = name;
    }
}
