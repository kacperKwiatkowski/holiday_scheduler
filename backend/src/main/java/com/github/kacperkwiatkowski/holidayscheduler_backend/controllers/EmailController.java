package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Vacation;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.TeamRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.VacationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/email")
public class EmailController {

    private final UserRepository userRepository;

    public EmailController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/send/{id}")
    @PreAuthorize("hasAuthority('self:edit')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity createVacation(
            @PathVariable("id") int id,
            @RequestParam("address") String address,
            @RequestParam("title") String title,
            @RequestParam("content") String content) {

        //TODO Apply logic
        Vacation vacation = new Vacation();
        log.info("Controller 'createVacation' initiated.");
        return null;
    }
}
