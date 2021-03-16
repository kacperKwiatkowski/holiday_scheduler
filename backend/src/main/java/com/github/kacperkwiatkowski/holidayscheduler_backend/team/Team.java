package com.github.kacperkwiatkowski.holidayscheduler_backend.team;

import com.github.kacperkwiatkowski.holidayscheduler_backend.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column(nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "TeamSquad", joinColumns = @JoinColumn(name = "teamId"))
    private List<Integer> teamSquad;

    @OneToOne(fetch = FetchType.EAGER)
    private User teamLeader;

//    public Team mapToEntity(TeamDto teamDto) {
//        Team team;
//        Optional<Team> optionalTeam = Optional.ofNullable(teamRepository.findById(teamDto.getId()));
//        if(optionalTeam.isPresent()){
//            team = optionalTeam.get();
//        } else {
//            team = new Team();
//        }
//        team.setName(teamDto.getName());
//        team.setTeamSquad(teamDto.getUserIds());
//        team.setTeamLeader(userRepository.findById(teamDto.getTeamLeaderId()));
//
//        return team;
//    }

    TeamDto mapToDto() {

        return TeamDto.builder()
                .id(id)
                .name(name)
                .userIds(teamSquad)
                .teamLeaderId(teamLeader.getId())
                .teamLeaderFirstName(teamLeader.getFirstName())
                .teamLeaderLastName(teamLeader.getLastName())
                .teamLeaderEmail(teamLeader.getEmail())
                .build();
    }
}
