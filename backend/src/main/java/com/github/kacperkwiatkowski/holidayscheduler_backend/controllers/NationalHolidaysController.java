package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.NationalHoliday;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.NationalHolidayRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.service.NationalHolidayService;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.nationalHolidayApi.Holidays;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.nationalHolidayApi.HolidaysJsonData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nationalholiday")
public class NationalHolidaysController {

    private final NationalHolidayRepository nationalHolidayRepository;
    private final NationalHolidayService nationalHolidayService;

    public NationalHolidaysController(NationalHolidayRepository nationalHolidayRepository, NationalHolidayService nationalHolidayService) {
        this.nationalHolidayRepository = nationalHolidayRepository;
        this.nationalHolidayService = nationalHolidayService;
    }

    @PostMapping(value = "/download/{year}/{key}")
    ResponseEntity<NationalHoliday> uploadNationalHolidaysToDatabase(
            @PathVariable String year,
            @PathVariable String key
    ){
            String apiURL = nationalHolidayService.generateApiFromUrl(year, key);
            List<Holidays> jsonHolidays = HolidaysJsonData.readNationalHolidaysFromApiUrl(apiURL);
            if (!jsonHolidays.isEmpty()) {
                jsonHolidays.forEach(nationalHolidayRepository::save);
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }
}
