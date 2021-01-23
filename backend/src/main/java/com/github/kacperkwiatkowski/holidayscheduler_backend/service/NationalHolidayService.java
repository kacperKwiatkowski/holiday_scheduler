package com.github.kacperkwiatkowski.holidayscheduler_backend.service;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.NationalHolidayDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.ObjectNotFoundException;
import com.github.kacperkwiatkowski.holidayscheduler_backend.mappers.NationalHolidayMapper;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.NationalHoliday;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.NationalHolidayRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.nationalHolidayApi.Holidays;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.nationalHolidayApi.HolidaysJsonData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NationalHolidayService {

    private final NationalHolidayRepository nationalHolidayRepository;
    private final NationalHolidayMapper nationalHolidayMapper;

    public NationalHolidayService(NationalHolidayRepository nationalHolidayRepository, NationalHolidayMapper nationalHolidayMapper) {
        this.nationalHolidayRepository = nationalHolidayRepository;
        this.nationalHolidayMapper = nationalHolidayMapper;
    }

    public List<NationalHolidayDto> uploadNationalHolidaysToDatabase(String year, String key){
        String apiURL = generateApiFromUrl(year, key);
        List<Holidays> jsonHolidays = HolidaysJsonData.readNationalHolidaysFromApiUrl(apiURL);
        if (!jsonHolidays.isEmpty()) {
            jsonHolidays.forEach(nationalHolidayRepository::save);
            return formatNationalHolidays(jsonHolidays).stream().map(nationalHolidayMapper::mapToDto).collect(Collectors.toList());
        } else {
            throw new ObjectNotFoundException("UPLOAD impossible.");
            //TODO Create exception error for this case
        }
    }

    private String generateApiFromUrl(String requestedYear, String apiKey) {
        return "https://calendarific.com/api/v2/holidays?api_key=" + apiKey + "&country=PL&year=" + requestedYear + "&type=national";
    }

    private List<NationalHoliday> formatNationalHolidays(List<Holidays> listToParse){
        List<NationalHoliday> parsedList = new ArrayList<>();

        for(Holidays holiday : listToParse){
            NationalHoliday.builder().name(holiday.getName()).holidayDate(holiday.getHolidayDateInLocalDateFormat()).description(holiday.getDescription()).build();
        }

        return parsedList;
    }
}
