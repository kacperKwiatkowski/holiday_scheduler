package com.github.kacperkwiatkowski.vacation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class VacationDto {

    private int id;
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String firstDay;
    private String lastDay;
    private boolean isAccepted;
    private String leaveType;

}
