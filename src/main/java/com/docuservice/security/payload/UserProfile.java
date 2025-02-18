package com.docuservice.security.payload;


import com.docuservice.security.model.Role;

import java.util.Date;
import java.util.Set;

public class UserProfile {
    private Long id;
    private String username;
    private String name;
    private Date joinedAt;
    private Set<Role> roles;


    public UserProfile(Long id, String username, String name, Date joinedAt, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.joinedAt = joinedAt;
        this.roles = roles;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Date joinedAt) {
        this.joinedAt = joinedAt;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
