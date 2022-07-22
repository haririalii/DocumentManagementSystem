package com.docuservice.api.controller.dto;

import com.docuservice.security.model.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Set;

@Data
public class UserResponseDto {

    private Long id;

    private String name;

    private String username;

    private String email;

    private Set<Role> roles;

    @JsonProperty("creation_date")
    private String createdAt;

    @JsonProperty("last_modification_date")
    private String updatedAt;


}
