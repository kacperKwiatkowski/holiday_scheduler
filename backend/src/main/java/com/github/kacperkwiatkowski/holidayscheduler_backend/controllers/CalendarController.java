package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.CalendarDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.UserDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.VacationDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.NationalHoliday;
import com.github.kacperkwiatkowski.holidayscheduler_backend.service.CalendarService;
import com.github.kacperkwiatkowski.holidayscheduler_backend.service.UserService;
import com.github.kacperkwiatkowski.holidayscheduler_backend.service.VacationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Queue;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/calendar")
public class CalendarController {

    private final CalendarService calendarService;
    private final UserService userService;
    private final VacationService vacationService;

    public CalendarController(CalendarService calendarService, UserService userService, VacationService vacationService) {
        this.calendarService = calendarService;
        this.userService = userService;
        this.vacationService = vacationService;
    }

    @PreAuthorize("hasAuthority('employee:read')")
    @GetMapping(path = "/page")
    public ResponseEntity<List<CalendarDto>> getCalendar(
            @RequestParam(defaultValue = "") String filter,
            @RequestParam(defaultValue = "0") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortOrder,
            @RequestParam String month,
            @RequestParam String year)
    {
        log.info("Controller 'getCalendar' initiated.");
        List<UserDto> foundUsers = userService.listAll(pageNum, pageSize, sortBy, sortOrder, filter);
        List<VacationDto> foundVacations = vacationService.readRequiredVacations(foundUsers, Integer.valueOf(month), Integer.valueOf(year));
        List<CalendarDto> generatedCalendar = calendarService.createCalendar(foundUsers, foundVacations, Integer.valueOf(month), Integer.valueOf(year));
        return ResponseEntity.ok(generatedCalendar);
    }

    @PreAuthorize("hasAuthority('employee:read')")
    @GetMapping(path = "/nationalHolidays")
    public ResponseEntity<Queue<NationalHoliday>> getNationalHolidays(
            @RequestParam int month,
            @RequestParam int year)
    {
        return ResponseEntity.ok(calendarService.nationalHolidaysInThisMonth(year, month));
    }

}
