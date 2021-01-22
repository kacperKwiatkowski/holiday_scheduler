package com.github.kacperkwiatkowski.holidayscheduler_backend.mappers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.TeamDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Team;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.TeamRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Mapper
public class TeamMapper implements ObjectMapper<TeamDto, Team> {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Team mapToDto(TeamDto teamDto) {

        Team team = teamRepository.findById(teamDto.getId());

                if(!teamDto.getName().equals(teamDto.getName())){
                    team.setName(teamDto.getName());
                }

                if(teamDto.getUserIds().equals(team.getTeamSquad())){
                    team.setTeamSquad(teamDto.getUserIds());
                }

                if(teamDto.getId()!=team.getId()){
                    team.setTeamLeader(userRepository.findById(teamDto.getId()));
                }

        return team;
    }

    @Override
    public TeamDto mapToEntity(Team team) {
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
