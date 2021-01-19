package com.github.kacperkwiatkowski.holidayscheduler_backend.dto;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Leave;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Team;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;

import java.util.Set;

public class UserDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int daysOffLeft;
    private int levelOfAccess;
    private boolean isTeamLeader;
    private int team;
    private Set<Leave> dayOffSet;

    public UserDto(int id, String firstName, String lastName, String email, int daysOffLeft, int levelOfAccess, boolean isTeamLeader, int team, Set<Leave> dayOffSet) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.daysOffLeft = daysOffLeft;
        this.levelOfAccess = levelOfAccess;
        this.isTeamLeader = isTeamLeader;
        this.team = team;
        this.dayOffSet = dayOffSet;
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

    public int getTeamId() {
        return team;
    }

    public void setTeamId(Team team) {
        this.team = team.getId();
    }

    public Set<Leave> getDayOffSet() {
        return dayOffSet;
    }

    public void setDayOffSet(Set<Leave> dayOffSet) {
        this.dayOffSet = dayOffSet;
    }

    public static UserDto parseUserToDto(User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getFirstName(),
                user.getEmail(),
                user.getDaysOffLeft(),
                user.getLevelOfAccess(),
                user.isTeamLeader(),
                user.getTeam().getTeamLeader().getTeam().getId(),
                user.getDaysOff()
        );
    }
}
