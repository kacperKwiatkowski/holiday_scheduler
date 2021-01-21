package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Vacation;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.VacationRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leave")
public class VacationController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final VacationRepository vacationRepository;

    public VacationController(VacationRepository vacationRepository) {
        this.vacationRepository = vacationRepository;
    }

    @PostMapping(path = "/create")
    ResponseEntity<Vacation> createLeave(@RequestBody String leaveDetails){

        //TODO check if leave qualifies.

        Gson g = new Gson();
        Vacation vacation = g.fromJson(leaveDetails, Vacation.class);
        vacationRepository.save(vacation);

        logger.info("User: " + vacation.getId() + "added successfully");

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping(path = "/delete")
    ResponseEntity<Vacation> createLeave(@RequestParam int id){
        vacationRepository.deleteById(id);

        logger.info("User: " + id + "deleted successfully");

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
