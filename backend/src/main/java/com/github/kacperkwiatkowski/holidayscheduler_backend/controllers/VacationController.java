package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.UserDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.VacationDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.ObjectNotFoundException;
import com.github.kacperkwiatkowski.holidayscheduler_backend.service.VacationService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/vacation")
public class VacationController {

    private static final Logger logger = LoggerFactory.getLogger(VacationController.class);

    private final VacationService vacationService;

    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @PostMapping(path = "/create")
    @PreAuthorize("hasAuthority('vacation:create')")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<VacationDto> createVacation(@RequestBody VacationDto vacationToCreate){
        log.info("Controller 'createUser' initiated.");
        return ResponseEntity.ok(vacationService.createVacation(vacationToCreate));
    }

    @PatchMapping(path = "/update")
    @PreAuthorize("hasAuthority('vacation:notAcceptedEdit')")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ResponseEntity<VacationDto> updateVacation(@RequestBody VacationDto vacationToUpdate) throws ObjectNotFoundException {
        log.info("Controller 'updateVacation' initiated.");
        //TODO Return entity
        return ResponseEntity.ok(vacationService.updateVacation(vacationToUpdate));

    }

    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("hasAuthority('vacation:delete')")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<VacationDto> deleteVacation(@PathVariable("id") int id){
        logger.info("Controller 'deleteVacation' initiated.");
        return ResponseEntity.ok(vacationService.deleteVacation(Integer.valueOf(id)));
    }

    @GetMapping(path = "/page")
    public ResponseEntity<List<VacationDto>> getAllVacations(
            @RequestParam(defaultValue = "0") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "firstDay") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortOrder,
            @RequestParam(defaultValue = "") String filter)
    {
        log.info("Controller 'getAllVacations' initiated.");
        return new ResponseEntity<List<VacationDto>>(vacationService.listAll(pageNum, pageSize, sortBy, sortOrder, filter), new HttpHeaders(), HttpStatus.OK);
    }
}
