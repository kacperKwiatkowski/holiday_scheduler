package com.github.kacperkwiatkowski.holidayscheduler_backend.mappers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.TeamDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Team;
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

        Team team = new Team();
        team.setId(teamDto.getId());


        return null;
    }

    @Override
    public TeamDto mapToEntity(Team team) {
        return null;
    }
}
