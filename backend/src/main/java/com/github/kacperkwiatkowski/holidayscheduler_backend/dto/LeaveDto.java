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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(LocalDate firstDay) {
        this.firstDay = firstDay;
    }

    public LocalDate getLastDay() {
        return lastDay;
    }

    public void setLastDay(LocalDate lastDay) {
        this.lastDay = lastDay;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public List<LocalDate> getListOfDays() {
        return listOfDays;
    }

    public void setListOfDays(List<LocalDate> listOfDays) {
        this.listOfDays = listOfDays;
    }

    @Override
    public String toString() {
        return "DayOffDto{" +
                "firstDay=" + firstDay +
                ", lastDay=" + lastDay +
                ", user=" + userID +
                ", isAccepted=" + isAccepted +
                ", listOfDays=" + listOfDays +
                '}';
    }

    public static LeaveDto parseLeaveToDto(Leave leave){
        return new LeaveDto(
                leave.getId(),
                leave.getFirstDay(),
                leave.getLastDay(),
                leave.getUser().getId(),
                leave.isAccepted(),
                leave.getLeaveType(),
                DateTimeProcessor.generateListOfDates(leave.getFirstDay(), leave.getLastDay())
        );

    }


}
