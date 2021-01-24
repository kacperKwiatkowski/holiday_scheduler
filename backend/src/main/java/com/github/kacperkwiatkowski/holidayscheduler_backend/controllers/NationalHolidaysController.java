package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.NationalHolidayDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.service.NationalHolidayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/nationalholiday")
public class NationalHolidaysController {

    private final NationalHolidayService nationalHolidayService;

    public NationalHolidaysController(NationalHolidayService nationalHolidayService) {
        this.nationalHolidayService = nationalHolidayService;
    }

    @PostMapping(value = "/download/{year}/{key}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<NationalHolidayDto>> uploadNationalHolidaysToDatabase(
            @PathVariable String year,
            @PathVariable String key
    ){
        log.info("Controller 'uploadNationalHolidaysToDatabase' initiated.");
        return ResponseEntity.ok(nationalHolidayService.uploadNationalHolidaysToDatabase(year, key));
    }
}
