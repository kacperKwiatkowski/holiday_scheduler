package com.github.kacperkwiatkowski.holidayscheduler_backend.dto;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Team;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class TeamDto {

    private int id;
    private String name;
    private List<Integer> userIds;
    private int teamLeaderId;

}
