package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.TeamDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.ObjectNotFoundException;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Team;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.TeamRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.service.TeamService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/team")
public class TeamController {

    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final TeamService teamService;

    public TeamController(TeamRepository teamRepository, UserRepository userRepository, TeamService teamService) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.teamService = teamService;
    }

    @PostMapping (path = "/create")
    ResponseEntity<Team> createTeam (@RequestBody TeamDto teamToCreate){
        ModelMapper modelMapper = new ModelMapper();
        Team team = modelMapper.map(teamToCreate, Team.class);
        teamRepository.save(team);
        logger.info("User: " + team.getId() + "added successfully");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping(path = "/update/{teamId}/{teamName}/{teamLeaderId}")
    ResponseEntity<Team> updateTeam (
            @PathVariable int teamId,
            @PathVariable String teamName,
            @PathVariable int teamLeaderId) throws ObjectNotFoundException {
        Optional<Team> foundTeam = Optional.ofNullable(teamRepository.findById(teamId));

        if(foundTeam.isPresent()){
            if(foundTeam.isPresent()){
                Team team = foundTeam.get();
                team.setName(teamName);
                team.setTeamLeader(userRepository.findById(teamLeaderId)); //TODO Check if id exists
                teamRepository.save(team);
                logger.info("Team: " + teamId + "updated successfully");
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                logger.info("Team: " + teamId + "updated unsuccessfully");
                return ResponseEntity.unprocessableEntity().build();
            }
        } else {
            throw ObjectNotFoundException.createWith("Team with such ID does not exist.");
        }


    }

    @GetMapping(path = "/page")
    public ResponseEntity<List<Team>> getAllTeams(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortOrder)
    {
        List<Team> list = teamService.listAll(pageNo, pageSize, sortBy, sortOrder);

        logger.info("Pagination successful");

        return new ResponseEntity<List<Team>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    void deleteUser(@PathVariable("id") int id){
        teamRepository.deleteById(id);
        logger.info("Team: " + id + "deleted successfully");
    }
}
