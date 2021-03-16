package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.NationalHolidayDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.service.NationalHolidayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/nationalholiday")
class NationalHolidaysController {

    private final NationalHolidayService nationalHolidayService;

    NationalHolidaysController(NationalHolidayService nationalHolidayService) {
        this.nationalHolidayService = nationalHolidayService;
    }

    @GetMapping(value = "/read")
    @PreAuthorize("hasAuthority('nationalHolidays:management')")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<NationalHolidayDto>> downloadNationalHolidays(){
        return ResponseEntity.ok(nationalHolidayService.readNationalHolidays());
    }

    @PostMapping(value = "/download/{year}/{key}")
    @ResponseStatus(HttpStatus.OK)
    void uploadNationalHolidaysToDatabase(
            @PathVariable String year,
            @PathVariable String key
    ){
        log.info("Controller 'uploadNationalHolidaysToDatabase' initiated.");
        nationalHolidayService.uploadNationalHolidaysToDatabase(year, key);
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('nationalHolidays:management')")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<NationalHolidayDto> deleteNationalHolidays(@PathVariable("id") int id){
        return ResponseEntity.ok(nationalHolidayService.deleteNationalHoliday(id));
    }
}
