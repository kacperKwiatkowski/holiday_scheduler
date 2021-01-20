package com.github.kacperkwiatkowski.holidayscheduler_backend.dto;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Leave;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.dateAndTime.DateTimeProcessor;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.LeaveType;

import java.time.LocalDate;
import java.util.List;

public class LeaveDto {

    private int id;
    private LocalDate firstDay;
    private LocalDate lastDay;
    private int userID;
    private boolean isAccepted;
    private LeaveType leaveType;
    private List<LocalDate> listOfDays;

    public LeaveDto(int id, LocalDate firstDay, LocalDate lastDay, int userID, boolean isAccepted, LeaveType leaveType, List<LocalDate> listOfDays) {
        this.id = id;
        this.firstDay = firstDay;
        this.lastDay = lastDay;
        this.userID = userID;
        this.isAccepted = isAccepted;
        this.leaveType = leaveType;
        this.listOfDays = listOfDays;
    }

}
