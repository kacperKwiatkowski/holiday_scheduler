package com.github.kacperkwiatkowski.holidayscheduler_backend.mappers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.TeamDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Team;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.TeamRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TeamMapper implements ObjectMapper<TeamDto, Team> {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public TeamMapper(TeamRepository teamRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Team mapToEntity(TeamDto teamDto) {
        Team team;
        Optional<Team> optionalTeam = Optional.ofNullable(teamRepository.findById(teamDto.getId()));
        if(optionalTeam.isPresent()){
            team = optionalTeam.get();
        } else {
            team = new Team();
        }
            team.setName(teamDto.getName());
            team.setTeamSquad(teamDto.getUserIds());
            team.setTeamLeader(userRepository.findById(teamDto.getId()));

        return team;
    }

    @Override
    public TeamDto mapToDto(Team team) {
        TeamDto teamDto = TeamDto
                .builder()
                .id(team.getId())
                .name(team.getName())
                .userIds(team.getTeamSquad())
                .teamLeaderId(team.getTeamLeader().getId())
                .build();

        return teamDto;
    }


}
