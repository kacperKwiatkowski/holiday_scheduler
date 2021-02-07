package com.github.kacperkwiatkowski.holidayscheduler_backend.service;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.CalendarDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.UserDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.VacationDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.VacationRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.VacationSqlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
public class CalendarService {

    private final UserRepository userRepository;
    private final VacationRepository vacationRepository;
    private final VacationSqlRepository vacationSqlRepository;

    public CalendarService(UserRepository userRepository, VacationRepository vacationRepository, VacationSqlRepository vacationSqlRepository) {
        this.userRepository = userRepository;
        this.vacationRepository = vacationRepository;
        this.vacationSqlRepository = vacationSqlRepository;
    }

    public List<CalendarDto> createCalendar(List<UserDto> users, List<VacationDto> vacations, int month, int year){

        int days = LocalDate.of(year, month, 1).lengthOfMonth();

        List<CalendarDto> calendar = fillCalendar(users, days);

        for(VacationDto v : vacations){
            int vacationsUserId = v.getUserID();

            CalendarDto foundEntry = calendar.stream().filter(u -> vacationsUserId == u.getUserDto().getId()).findFirst().get();

            int index = calendar.indexOf(foundEntry);

            foundEntry.setHolidayStatus(fillMonth(foundEntry.getHolidayStatus(), v));

            calendar.set(index, foundEntry);
        }

        return calendar;
    }

    private List<CalendarDto>  fillCalendar(List<UserDto> users, int days) {

        List<CalendarDto> calendar = new ArrayList<>();
        users.forEach(u -> calendar.add(CalendarDto.builder().userDto(u).holidayStatus(generateEmptyMonth(days)).build()));
        return calendar;
    }

    private List<String> generateEmptyMonth(int days){
        List<String> emptyMonth = new LinkedList<>();
        for(int i = 0; i < days; i++){
            emptyMonth.add("NONE");
        }
        return emptyMonth;
    }

    private List<String> fillMonth (List<String> month, VacationDto vacationDto){

        int index = Integer.parseInt(vacationDto.getFirstDay().substring(0,2))-1;
        //FIXME The last day var does not take the month into account
        int lastDay = Integer.parseInt(vacationDto.getLastDay().substring(0,2))-1;
        do{
            if(index>=month.size()) break;
            month.set(index, vacationDto.getLeaveType().toString());
            index++;
        } while (lastDay>=index);

        return month;
    }
}
