package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.UserDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.VacationDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Vacation;
import com.github.kacperkwiatkowski.holidayscheduler_backend.service.VacationService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.lang.reflect.Type;
import java.util.List;


@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/vacation")
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

    @PostMapping(path = "/read/required")
    @ResponseStatus(HttpStatus.OK)
    String readRequiredVacations(
            @RequestParam String month,
            @RequestParam  String year,
            @RequestParam("details") String users
    ) //throws JsonProcessingException
    {
        //vacationService.readRequiredVacations(users, Integer.valueOf(month), Integer.valueOf(year));
        log.info("Controller 'readRequiredVacations' initiated.");
        return "SUCCESS";
    }

    @DeleteMapping(path = "/delete")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<VacationDto> deleteVacation(@RequestParam String id){
        logger.info("Controller 'deleteVacation' initiated.");
        return ResponseEntity.ok(vacationService.deleteVacation(Integer.valueOf(id)));
    }
}
