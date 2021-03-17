package com.github.kacperkwiatkowski.team;

import org.springframework.stereotype.Service;

@Service
class TeamFactory {

    private final TeamRepository teamRepository;

    TeamFactory(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    Team mapToEntity(final TeamDto teamDto){
        return new Team(
                teamDto.getId(),
                teamDto.getName(),
                teamDto.getUserIds(),
                teamRepository.findById(teamDto.getTeamLeaderId()).getTeamLeader());
    }
}
