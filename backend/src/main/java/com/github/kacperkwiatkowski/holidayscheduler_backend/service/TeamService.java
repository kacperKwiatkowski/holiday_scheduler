package com.github.kacperkwiatkowski.holidayscheduler_backend.service;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.TeamDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.UserDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.ObjectNotFoundException;
import com.github.kacperkwiatkowski.holidayscheduler_backend.mappers.TeamMapper;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Team;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.TeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    public TeamService(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    public TeamDto creatTeam(TeamDto teamToCreate){
        teamRepository.save(teamMapper.mapToEntity(teamToCreate));
        return teamToCreate;
    }

    public TeamDto readTeam(int teamId){
        Optional<Team> foundTeam = Optional.ofNullable(teamRepository.findById(teamId));
        if(foundTeam.isPresent()){
            return teamMapper.mapToDto(foundTeam.get());
        } else {
            throw ObjectNotFoundException.createWith("Team with such ID does not exist.");
        }
    }

    public TeamDto updateTeam(TeamDto teamToUpdate) throws ObjectNotFoundException {

        Optional<Team> foundTeam = Optional.ofNullable(teamRepository.findById(teamToUpdate.getId()));
        if(foundTeam.isPresent()){
            teamRepository.save(teamMapper.mapToEntity(teamToUpdate));
            return teamToUpdate;
        } else {
            throw ObjectNotFoundException.createWith("Team with such ID does not exist.");
        }
    }

    @Transactional
    public TeamDto deleteTeam(int id){
        Optional<Team> foundTeam = Optional.ofNullable(teamRepository.findById(id));
        //FIXME Resolve DB alteration problem
        if (foundTeam.isPresent()){
            teamRepository.deleteById(id);
            log.info("Deletion successful.");
            return teamMapper.mapToDto(foundTeam.get());
        } else {
            throw new ObjectNotFoundException("DELETION impossible, object not found.");
        }
    }

    public List<TeamDto> listAll(Integer pageNo, Integer pageSize, String sortBy, String sortOrder) {

        Pageable paging;

        if(sortOrder.equals("ASC")){
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        } else {
            paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        }

        Page<Team> pagedResult = teamRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.stream().map(teamMapper::mapToDto).collect(Collectors.toList());
        } else {
            throw new ObjectNotFoundException("SEARCH impossible, no object were found.");
        }
    }
}
