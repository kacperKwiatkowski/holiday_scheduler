package com.github.kacperkwiatkowski.holidayscheduler_backend.dto;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
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

    public UserDto() {
    }

    public UserDto(String firstName, String lastName, String email, int daysOffLeft, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.daysOffLeft = daysOffLeft;
        this.role = role;
    }

    public UserDto(int id, String firstName, String lastName, String email, int daysOffLeft, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.daysOffLeft = daysOffLeft;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDaysOffLeft() {
        return daysOffLeft;
    }

    public void setDaysOffLeft(int daysOffLeft) {
        this.daysOffLeft = daysOffLeft;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static UserDto parseUserToDto(User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getDaysOffLeft(),
                user.getRole()
        );
    }

    public static User parseUserFromDto(UserDto userDto, User user){

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setDaysOffLeft(userDto.getDaysOffLeft());
        user.setRole(userDto.getRole());

        return user;
    }
}


