package com.github.kacperkwiatkowski.holidayscheduler_backend.user.dto;

import com.github.kacperkwiatkowski.holidayscheduler_backend.security.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSecurityDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private RoleType roleType;
    private int daysOffLeft;
    private String imageUrl;
}
