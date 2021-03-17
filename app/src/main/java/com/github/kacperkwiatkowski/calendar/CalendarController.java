package com.github.kacperkwiatkowski.calendar;

import com.github.kacperkwiatkowski.user.UserDto;
import com.github.kacperkwiatkowski.user.UserFacade;
import com.github.kacperkwiatkowski.vacation.VacationDto;
import com.github.kacperkwiatkowski.vacation.VacationFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/calendar")
class CalendarController {

    private final CalendarService calendarService;
    private final UserFacade userFacade;
    private final VacationFacade vacationFacade;

    CalendarController(CalendarService calendarService, UserFacade userFacade, VacationFacade vacationFacade) {
        this.calendarService = calendarService;
        this.userFacade = userFacade;
        this.vacationFacade = vacationFacade;
    }

    @PreAuthorize("hasAuthority('employee:read')")
    @GetMapping(path = "/page")
    ResponseEntity<List<CalendarDto>> getCalendar(
            @RequestParam(defaultValue = "") String filter,
            @RequestParam(defaultValue = "0") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortOrder,
            @RequestParam String month,
            @RequestParam String year)
    {
        log.info("Controller 'getCalendar' initiated.");
        List<UserDto> foundUsers = userFacade.listAll(pageNum, pageSize, sortBy, sortOrder, filter);
        List<VacationDto> foundVacations = vacationFacade.readRequiredVacations(foundUsers, Integer.valueOf(month), Integer.valueOf(year));
        List<CalendarDto> generatedCalendar = calendarService.createCalendar(foundUsers, foundVacations, Integer.valueOf(month), Integer.valueOf(year));
        return ResponseEntity.ok(generatedCalendar);
    }

}
