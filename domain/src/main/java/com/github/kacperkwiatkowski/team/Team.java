package com.github.kacperkwiatkowski.team;

import com.github.kacperkwiatkowski.user.query.SimpleUserQueryDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
class Team {
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
    private SimpleUserQueryDto teamLeader;

//    public Team mapToEntity(TeamDto teamDto) {
//        Team com.github.kacperkwiatkowski.team;
//        Optional<Team> optionalTeam = Optional.ofNullable(teamRepository.findById(teamDto.getId()));
//        if(optionalTeam.isPresent()){
//            com.github.kacperkwiatkowski.team = optionalTeam.get();
//        } else {
//            com.github.kacperkwiatkowski.team = new Team();
//        }
//        com.github.kacperkwiatkowski.team.setName(teamDto.getName());
//        com.github.kacperkwiatkowski.team.setTeamSquad(teamDto.getUserIds());
//        com.github.kacperkwiatkowski.team.setTeamLeader(userRepository.findById(teamDto.getTeamLeaderId()));
//
//        return com.github.kacperkwiatkowski.team;
//    }

    com.github.kacperkwiatkowski.team.TeamDto mapToDto() {

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
