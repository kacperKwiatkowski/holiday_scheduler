package com.github.kacperkwiatkowski.vacation;

import com.github.kacperkwiatkowski.exceptions.ObjectNotFoundException;
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
class VacationController {

    private static final Logger logger = LoggerFactory.getLogger(VacationController.class);

    private final VacationFacade vacationFacade;
    private final VacationRepository vacationRepository;

    VacationController(VacationFacade vacationFacade, VacationRepository vacationRepository) {
        this.vacationFacade = vacationFacade;
        this.vacationRepository = vacationRepository;
    }

    @PostMapping(path = "/request")
    @PreAuthorize("hasAuthority('self:edit')")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity createVacation(
            @RequestBody VacationDto vacationDto) {
        vacationFacade.createVacation(vacationDto);
        log.info("Controller 'createVacation' initiated.");
        return null;
    }

    @PatchMapping(path = "/update/{id}")
    @PreAuthorize("hasAuthority('vacation:notAcceptedEdit')")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ResponseEntity<VacationDto> updateVacation(@RequestBody VacationDto vacationToUpdate) throws ObjectNotFoundException {
        log.info("Controller 'updateVacation' initiated.");
        //TODO Return entity
        return ResponseEntity.ok(vacationFacade.updateVacation(vacationToUpdate));

    }

    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("hasAuthority('vacation:delete')")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<VacationDto> deleteVacation(@PathVariable("id") int id){
        logger.info("Controller 'deleteVacation' initiated.");
        return ResponseEntity.ok(vacationFacade.deleteVacation(Integer.valueOf(id)));
    }

    @GetMapping(path = "/page")
    ResponseEntity<List<VacationDto>> getAllVacations(
            @RequestParam(defaultValue = "0") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "firstDay") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortOrder,
            @RequestParam(defaultValue = "") String filter)
    {
        log.info("Controller 'getAllVacations' initiated.");
        return new ResponseEntity<List<VacationDto>>(vacationFacade.listAll(pageNum, pageSize, sortBy, sortOrder, filter), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Long> vacationCount(){
        log.info("Controller 'vacationCount' initiated.");
        return ResponseEntity.ok(vacationRepository.count());
    }
}
