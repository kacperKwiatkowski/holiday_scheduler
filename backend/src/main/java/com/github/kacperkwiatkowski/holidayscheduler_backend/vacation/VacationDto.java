package com.github.kacperkwiatkowski.holidayscheduler_backend.vacation;

import lombok.*;


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
