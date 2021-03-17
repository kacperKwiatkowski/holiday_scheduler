package com.github.kacperkwiatkowski.nationalHoliday;

import com.github.kacperkwiatkowski.exceptions.ObjectNotFoundException;
import com.github.kacperkwiatkowski.nationalHoliday.nationalHolidayApi.Holidays;
import com.github.kacperkwiatkowski.nationalHoliday.nationalHolidayApi.HolidaysJsonData;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NationalHolidayFacade {

    private final NationalHolidayRepository nationalHolidayRepository;

    NationalHolidayFacade(NationalHolidayRepository nationalHolidayRepository) {
        this.nationalHolidayRepository = nationalHolidayRepository;
    }

    public List<NationalHolidayDto> readNationalHolidays(){
        List<NationalHoliday> nationalHolidaysList = nationalHolidayRepository.findAll();
        if(nationalHolidaysList.size()!=0){
            return nationalHolidaysList.stream().map(NationalHolidayDto::mapToDto).collect(Collectors.toList());
        } else {
            throw ObjectNotFoundException.createWith("No national holiday is found.");
        }
    }

    public NationalHolidayDto deleteNationalHoliday(int id){
        Optional<NationalHoliday> nationalHolidayToDelete = Optional.ofNullable(nationalHolidayRepository.findById(id));
        if(nationalHolidayToDelete.isPresent()){
            nationalHolidayRepository.deleteById(id);
            return  NationalHolidayDto.mapToDto(nationalHolidayToDelete.get());
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

    public Queue<NationalHolidayDto> findHolidaysWithinGivenTimeFrame(LocalDate firstDay, LocalDate lastDay){
        return nationalHolidayRepository
                .fetchHolidaysWithinGivenTimeFrameFromRepository(firstDay, lastDay)
                .stream()
                .map(NationalHolidayDto::mapToDto)
                .collect(Collectors.toCollection(LinkedList::new));
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
