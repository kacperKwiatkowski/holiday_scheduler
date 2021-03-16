package com.github.kacperkwiatkowski.holidayscheduler_backend.team;

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

}
