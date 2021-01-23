package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.VacationDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Vacation;
import com.github.kacperkwiatkowski.holidayscheduler_backend.service.VacationService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/leave")
public class VacationController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final VacationService vacationService;

    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @PostMapping(path = "/create")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<VacationDto> createVacation(@RequestBody VacationDto vacationToCreate){
        log.info("Controller 'createUser' initiated.");
        return ResponseEntity.ok(vacationService.createVacation(vacationToCreate));
    }

    @DeleteMapping(path = "/delete")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<VacationDto> deleteVacation(@RequestParam int id){
        logger.info("Controller 'deleteVacation' initiated.");
        return ResponseEntity.ok(vacationService.deleteVacation(id));
    }
}
