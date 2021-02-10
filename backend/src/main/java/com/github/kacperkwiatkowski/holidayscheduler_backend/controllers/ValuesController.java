package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/values")
public class ValuesController {

    private final UserRepository userRepository;

    public ValuesController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/count")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Long> userCount(){
        return ResponseEntity.ok(userRepository.count());
    }
}
