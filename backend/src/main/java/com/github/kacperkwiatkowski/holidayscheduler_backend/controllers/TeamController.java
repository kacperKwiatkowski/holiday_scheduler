package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.amazonaws.services.workmail.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.TeamDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.UserDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.ObjectNotFoundException;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Team;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.TeamRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.service.TeamService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@CrossOrigin
@RequestMapping("/api/team")
public class TeamController {

    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping (path = "/create")
    @PreAuthorize("hasAuthority('team:create')")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<TeamDto> createTeam (@RequestBody TeamDto teamToCreate){
        log.info("Controller 'createTeam' initiated.");
        return ResponseEntity.ok(teamService.createTeam(teamToCreate));
    }

    @PostMapping (path = "/create/member/{id}")
    @PreAuthorize("hasAuthority('team:create')")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<TeamDto> addMemberToTeam (
            @PathVariable int id,
            @RequestBody TeamDto teamDto){
        log.info("Controller 'addMemberToTeam' initiated.");
        return ResponseEntity.ok(teamService.addMemberToTeam(id, teamDto));
    }

    @GetMapping (path = "/read/{id}")
    @PreAuthorize("hasAuthority('team:read')")
    @ResponseStatus (HttpStatus.OK)
    ResponseEntity<TeamDto> readTeam (@PathVariable int id) {
        log.info("Controller 'createTeam' initiated.");
        return ResponseEntity.ok(teamService.readTeam(id));
    }

    @GetMapping (path = "/read/squad/{id}")
    @PreAuthorize("hasAuthority('team:read')")
    @ResponseStatus (HttpStatus.OK)
    ResponseEntity<List<UserDto>> readTeamSquad (@PathVariable int id) {
        log.info("Controller 'createTeam' initiated.");
        return ResponseEntity.ok(teamService.readTeamMembers(id));
    }


    @GetMapping (path = "/read/leaders/available")
    @PreAuthorize("hasAuthority('team:read')")
    @ResponseStatus (HttpStatus.OK)
    ResponseEntity<List<UserDto>> readAvailableTeamLeaders () {
        log.info("Controller 'readAvailableTeamLeaders' initiated.");
        return ResponseEntity.ok(teamService.readAvailableTeamLeaders());
    }


    @GetMapping (path = "/read")
    @PreAuthorize("hasAuthority('team:read')")
    @ResponseStatus (HttpStatus.OK)
    ResponseEntity<List<TeamDto>> readEachTeam () {
        log.info("Controller 'readEachTeam' initiated.");
        return ResponseEntity.ok(teamService.readEachTeam());
    }


    @PatchMapping(path = "/update/{id}")
    @PreAuthorize("hasAuthority('team:update')")
    @ResponseBody
    ResponseEntity<TeamDto> updateTeam (@RequestBody TeamDto teamToUpdate) {
        log.info("Controller 'updateTeam' initiated.");
        return ResponseEntity.ok(teamService.updateTeam(teamToUpdate));
    }

    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("hasAuthority('team:delete')")
    ResponseEntity<TeamDto> deleteUser(@PathVariable("id") int id){
        logger.info("Controller 'deleteUser' initiated.");
        return ResponseEntity.ok(teamService.deleteTeam(id));
    }

    @GetMapping(path = "/page")
    @PreAuthorize("hasAuthority('team:read')")
    public ResponseEntity<List<TeamDto>> getAllTeams(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortOrder,
            @RequestParam(defaultValue = "") String filter)
    {
        logger.info("Pagination successful");
        return new ResponseEntity<List<TeamDto>>(teamService.listAll(pageNo, pageSize, sortBy, sortOrder, filter), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(path = "/member/remove/{id}")
    @PreAuthorize("hasAuthority('team:update')")
    public ResponseEntity<UserDto> removeFromTeam(
            @PathVariable Integer id
    ){
        return ResponseEntity.ok(teamService.removeFromTeam(id));
    }
}
