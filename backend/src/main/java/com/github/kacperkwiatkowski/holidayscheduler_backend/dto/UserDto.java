package com.github.kacperkwiatkowski.holidayscheduler_backend.dto;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Leave;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Team;

import java.util.Set;

public class UserDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int daysOffLeft;
    private int levelOfAccess;
    private boolean isTeamLeader;
    private Team team;
    private Set<Leave> dayOffSet;

    public UserDto(int id, String firstName, String lastName, String email, String password, int daysOffLeft, int levelOfAccess, boolean isTeamLeader, Team team) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.daysOffLeft = daysOffLeft;
        this.levelOfAccess = levelOfAccess;
        this.isTeamLeader = isTeamLeader;
        this.team = team;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDaysOffLeft() {
        return daysOffLeft;
    }

    public void setDaysOffLeft(int daysOffLeft) {
        this.daysOffLeft = daysOffLeft;
    }

    public int getLevelOfAccess() {
        return levelOfAccess;
    }

    public void setLevelOfAccess(int levelOfAccess) {
        this.levelOfAccess = levelOfAccess;
    }

    public boolean isTeamLeader() {
        return isTeamLeader;
    }

    public void setTeamLeader(boolean teamLeader) {
        isTeamLeader = teamLeader;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", daysOffLeft=" + daysOffLeft +
                ", levelOfAccess=" + levelOfAccess +
                ", isTeamLeader=" + isTeamLeader +
                ", team=" + team +
                '}';
    }
}
