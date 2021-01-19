package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Team;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.TeamRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.service.TeamService;
import com.google.gson.Gson;
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

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final TeamService teamService;

    public TeamController(TeamRepository teamRepository, UserRepository userRepository, TeamService teamService) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.teamService = teamService;
    }

    @PostMapping (path = "/create")
    ResponseEntity<Team> createTeam (@RequestBody String teamDetails){
        Gson gson = new Gson();
        teamRepository.save(gson.fromJson(teamDetails, Team.class));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping(path = "/update/{teamId}/{teamName}/{teamLeaderId}")
    ResponseEntity<Team> updateTeam (
            @PathVariable int teamId,
            @PathVariable String teamName,
            @PathVariable int teamLeaderId){
        Optional<Team> foundTeam = Optional.ofNullable(teamRepository.findById(teamId));
        if(foundTeam.isPresent()){
            Team team = foundTeam.get();
            team.setName(teamName);
            team.setTeamLeader(userRepository.findById(teamLeaderId)); //TODO Check if id exists
            teamRepository.save(team);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.unprocessableEntity().build();
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

        return new ResponseEntity<List<Team>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    void deleteUser(@PathVariable("id") int id){
        teamRepository.deleteById(id);
    }
}
