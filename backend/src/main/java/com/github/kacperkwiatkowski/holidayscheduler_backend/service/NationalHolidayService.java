package com.github.kacperkwiatkowski.holidayscheduler_backend.service;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.NationalHolidayDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.ObjectNotFoundException;
import com.github.kacperkwiatkowski.holidayscheduler_backend.mappers.NationalHolidayMapper;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.NationalHoliday;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.NationalHolidayRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.nationalHolidayApi.Holidays;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.nationalHolidayApi.HolidaysJsonData;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NationalHolidayService {

    private final NationalHolidayRepository nationalHolidayRepository;
    private final NationalHolidayMapper nationalHolidayMapper;

    public NationalHolidayService(NationalHolidayRepository nationalHolidayRepository, NationalHolidayMapper nationalHolidayMapper) {
        this.nationalHolidayRepository = nationalHolidayRepository;
        this.nationalHolidayMapper = nationalHolidayMapper;
    }

    public List<NationalHolidayDto> readNationalHolidays(){
        List<NationalHoliday> nationalHolidaysList = nationalHolidayRepository.findAll();
        if(nationalHolidaysList.size()!=0){
            return nationalHolidaysList.stream().map(nationalHolidayMapper::mapToDto).collect(Collectors.toList());
        } else {
            throw ObjectNotFoundException.createWith("No national holiday is found.");
        }
    }

    public NationalHolidayDto deleteNationalHoliday(int id){
        Optional<NationalHoliday> nationalHolidayToDelete = Optional.ofNullable(nationalHolidayRepository.findById(id));
        if(nationalHolidayToDelete.isPresent()){
            nationalHolidayRepository.deleteById(id);
            return  nationalHolidayMapper.mapToDto(nationalHolidayToDelete.get());
        } else {
            throw new ObjectNotFoundException("Deletion unsuccessful. Id does not exist.");
        }
    }

    public void uploadNationalHolidaysToDatabase(String year, String key){
        try{
            String apiURL = generateApiFromUrl(year, key);
            StringBuffer content = fetchAndProcessResponseFromUrl(apiURL);
            List<NationalHoliday> nationalHolidays = parseNationalHolidays(content);
            validateAndInsertNationalHolidaysIntoDatabase(nationalHolidays);
            log.info("National holidays upload successful");
        } catch (Exception e){
            log.info("National holidays upload unsuccessful");
        }
    }

    private void validateAndInsertNationalHolidaysIntoDatabase(List<NationalHoliday> nationalHolidays) {
        for(NationalHoliday nationalHoliday : nationalHolidays){
            if(!nationalHolidayRepository.existsByHolidayDate(nationalHoliday.getHolidayDate())){
                nationalHolidayRepository.save(nationalHoliday);
            }
        }
    }

    private List<NationalHoliday> parseNationalHolidays(StringBuffer content) {
        Gson gson = new Gson();
        List<Holidays> list = gson.fromJson(content.toString(), HolidaysJsonData.class).getServerResponse().getHolidays();

        List<NationalHoliday> listNH = new ArrayList<>();
        for(Holidays h : list){
            NationalHoliday nh = new NationalHoliday();
            nh.setName(h.getName());
            nh.setDescription(h.getDescription());
            nh.setHolidayDate(h.getHolidayDateInLocalDateFormat());
            listNH.add(nh);
        }
        return listNH;
    }

    private StringBuffer fetchAndProcessResponseFromUrl(String apiURL) throws IOException {
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content;
    }

    private String generateApiFromUrl(String requestedYear, String apiKey) {
        return "https://calendarific.com/api/v2/holidays?api_key=" + apiKey + "&country=PL&year=" + requestedYear + "&type=national";
    }

}
