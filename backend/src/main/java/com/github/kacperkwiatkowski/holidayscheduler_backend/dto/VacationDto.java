package com.github.kacperkwiatkowski.holidayscheduler_backend.dto;

import lombok.*;


@Builder
@Getter
@Setter
public class VacationDto {

    private int id;
    private String firstDay;
    private String lastDay;
    private int userID;
    private boolean isAccepted;
    private String leaveType;

}
