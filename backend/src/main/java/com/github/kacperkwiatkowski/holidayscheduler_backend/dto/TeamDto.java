package com.github.kacperkwiatkowski.holidayscheduler_backend.dto;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Team;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;

import java.util.List;

public class TeamDto {

    private int id;
    private String name;
    private List<String> userEmail;
    private User teamLeader;

    public TeamDto(int id, String name, List<String> userEmail, User teamLeader) {
        this.id = id;
        this.name = name;
        this.userEmail = userEmail;
        this.teamLeader = teamLeader;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(List<String> userEmail) {
        this.userEmail = userEmail;
    }

    public User getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(User teamLeader) {
        this.teamLeader = teamLeader;
    }

    @Override
    public String toString() {
        return "TeamDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userEmail=" + userEmail +
                ", teamLeader=" + teamLeader +
                '}';
    }

    public static TeamDto parseTeamToDto(Team team){
        return new TeamDto(
                team.getId(),
                team.getName(),
                team.getUserEmail(),
                team.getTeamLeader()
        );
    }
}
