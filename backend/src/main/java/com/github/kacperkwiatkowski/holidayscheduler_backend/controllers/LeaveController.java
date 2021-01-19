package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Leave;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.LeaveRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final LeaveRepository leaveRepository;

    public LeaveController(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    @PostMapping(path = "/create")
    ResponseEntity<Leave> createLeave(@RequestBody String leaveDetails){

        //TODO check if leave qualifies.

        Gson g = new Gson();
        Leave leave = g.fromJson(leaveDetails, Leave.class);
        leaveRepository.save(leave);

        logger.info("User: " + leave.getId() + "added successfully");

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(path = "/delete")
    ResponseEntity<Leave> createLeave(@RequestParam int id){
        leaveRepository.deleteById(id);

        logger.info("User: " + id + "deleted successfully");

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
