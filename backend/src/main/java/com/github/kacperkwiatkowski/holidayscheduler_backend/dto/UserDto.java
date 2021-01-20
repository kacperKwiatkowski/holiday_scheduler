package com.github.kacperkwiatkowski.holidayscheduler_backend.dto;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Leave;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Team;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.sun.istack.NotNull;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class UserDto {

    private int id;
    @NotBlank(message = "Task description must not be blank!")
    private String firstName;
    @NotBlank(message = "Task description must not be blank!")
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
    public static UserDto parseUserFromDto(User user){
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
