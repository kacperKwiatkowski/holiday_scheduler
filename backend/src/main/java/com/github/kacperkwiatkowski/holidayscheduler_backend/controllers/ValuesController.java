package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.TeamRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.VacationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/values")
public class ValuesController {

    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final VacationRepository vacationRepository;

    public ValuesController(UserRepository userRepository, TeamRepository teamRepository, VacationRepository vacationRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.vacationRepository = vacationRepository;
    }

    @GetMapping("/user/count")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Long> userCount(){
        log.info("Controller 'userCount' initiated.");
        return ResponseEntity.ok(userRepository.count());
    }

    @GetMapping("/teams/count")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Long> teamsCount(){
        log.info("Controller 'teamsCount' initiated.");
        return ResponseEntity.ok(teamRepository.count());
    }

    @GetMapping("/vacation/count")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Long> vacationCount(){
        log.info("Controller 'vacationCount' initiated.");
        return ResponseEntity.ok(vacationRepository.count());
    }
}
