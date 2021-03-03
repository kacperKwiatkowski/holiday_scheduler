package com.github.kacperkwiatkowski.holidayscheduler_backend.service;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.TeamDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.UserDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.ObjectNotFoundException;
import com.github.kacperkwiatkowski.holidayscheduler_backend.mappers.TeamMapper;
import com.github.kacperkwiatkowski.holidayscheduler_backend.mappers.UserMapper;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Team;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.TeamRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final TeamMapper teamMapper;
    private final UserMapper userMapper;

    public TeamService(TeamRepository teamRepository, UserRepository userRepository, TeamMapper teamMapper, UserMapper userMapper) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.teamMapper = teamMapper;
        this.userMapper = userMapper;
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

    public List<TeamDto> readEachTeam(){
        List<Team> foundTeams = teamRepository.findAll();
        if(foundTeams.size()!=0){
            return foundTeams.stream().map(teamMapper::mapToDto).collect(Collectors.toList());
        } else {
            throw ObjectNotFoundException.createWith("Team with such ID does not exist.");
        }
    }

    public List<UserDto> readTeamMembers(int id){
        Optional<Team> foundTeam = Optional.ofNullable(teamRepository.findById(id));
        if(foundTeam.isPresent()){
            List<Integer> teamSquadIds = foundTeam.get().getTeamSquad();
            List<User> foundUsers = userRepository.findUsersWithIds(teamSquadIds);
            return foundUsers.stream().map(userMapper::mapToDto).collect(Collectors.toList());
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

    public List<TeamDto> listAll(Integer pageNum, Integer pageSize, String sortBy, String sortOrder, String filter) {

        Pageable paging;

        if(sortOrder.equals("ASC")){
            paging = PageRequest.of(pageNum, pageSize, Sort.Direction.ASC, sortBy);
        } else {
            paging = PageRequest.of(pageNum, pageSize, Sort.Direction.DESC, sortBy);
        }

        Page<Team> pagedResult;

        if(filter.length()<3){
            pagedResult = teamRepository.findAll(paging);
        } else {
            pagedResult = teamRepository.findWithFilter(filter, paging);
        }

        if(pagedResult.hasContent()) {
            return pagedResult.stream().map(teamMapper::mapToDto).collect(Collectors.toList());
        } else {
            throw new ObjectNotFoundException("Pagination impossible");
        }
    }

    @Transactional
    public UserDto removeFromTeam(int id){
        //TODO apply bussiness logic
        return null;
    }
}
