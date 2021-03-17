package com.github.kacperkwiatkowski.team;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {

    private int id;
    private String name;
    private List<Integer> userIds;
    @NotNull
    private int teamLeaderId;
    private String teamLeaderFirstName;
    private String teamLeaderLastName;
    private String teamLeaderEmail;

    public static TeamDto mapToDto(Team team) {

        return TeamDto.builder()
                .id(team.getId())
                .name(team.getName())
                .userIds(team.getTeamSquad())
                .teamLeaderId(team.getTeamLeader().getId())
                .teamLeaderFirstName(team.getTeamLeader().getFirstName())
                .teamLeaderLastName(team.getTeamLeader().getLastName())
                .teamLeaderEmail(team.getTeamLeader().getEmail())
                .build();
    }

}
