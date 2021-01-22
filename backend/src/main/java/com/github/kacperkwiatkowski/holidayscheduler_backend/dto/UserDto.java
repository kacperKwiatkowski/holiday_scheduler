package com.github.kacperkwiatkowski.holidayscheduler_backend.dto;

import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.RoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int daysOffLeft;
    private RoleType roleType;
}


