package com.github.kacperkwiatkowski.holidayscheduler_backend.dto;

import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.Role;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

public class UserDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int daysOffLeft;
    private Role role;

    public UserDto(int id, String firstName, String lastName, String email, int daysOffLeft, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.daysOffLeft = daysOffLeft;
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }
}


