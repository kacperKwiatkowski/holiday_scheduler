package com.github.kacperkwiatkowski.holidayscheduler_backend.dto;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Team;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;

import java.util.List;

public class TeamDto {

    private int id;
    private String name;
    private List<Integer> userIds;
    private int teamLeaderId;

    public TeamDto() {
    }

    public TeamDto(String name, List<Integer> userIds, int teamLeaderId) {
        this.name = name;
        this.userIds = userIds;
        this.teamLeaderId = teamLeaderId;
    }

    public TeamDto(int id, String name, List<Integer> userIds, int teamLeaderId) {
        this.id = id;
        this.name = name;
        this.userIds = userIds;
        this.teamLeaderId = teamLeaderId;
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

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    public int getTeamLeaderId() {
        return teamLeaderId;
    }

    public void setTeamLeaderId(int teamLeaderId) {
        this.teamLeaderId = teamLeaderId;
    }
}
