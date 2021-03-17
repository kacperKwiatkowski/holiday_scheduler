package com.github.kacperkwiatkowski.holidayscheduler_backend.user.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int daysOffLeft;
    private String roleType;
    private int teamId;

}


