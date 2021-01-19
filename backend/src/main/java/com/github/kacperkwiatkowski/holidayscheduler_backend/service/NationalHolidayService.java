package com.github.kacperkwiatkowski.holidayscheduler_backend.service;

import com.github.kacperkwiatkowski.holidayscheduler_backend.controllers.LoginController;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.nationalHolidayApi.Holidays;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.nationalHolidayApi.HolidaysJsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationalHolidayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NationalHolidayService.class);

    public String generateApiFromUrl(String requestedYear, String apiKey) {
        return "https://calendarific.com/api/v2/holidays?api_key=" + apiKey + "&country=PL&year=" + requestedYear + "&type=national";
    }

}
