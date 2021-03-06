package com.github.kacperkwiatkowski.holidayscheduler_backend.service;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.CalendarDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.UserDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.VacationDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.NationalHoliday;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.NationalHolidayRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.VacationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Slf4j
@Service
public class CalendarService {

    private final UserRepository userRepository;
    private final VacationRepository vacationRepository;
    private final NationalHolidayRepository nationalHolidayRepository;

    public CalendarService(UserRepository userRepository, VacationRepository vacationRepository, NationalHolidayRepository nationalHolidayRepository) {
        this.userRepository = userRepository;
        this.vacationRepository = vacationRepository;
        this.nationalHolidayRepository = nationalHolidayRepository;
    }

    public Queue<NationalHoliday> nationalHolidaysInThisMonth(int year, int month) {
        return nationalHolidayRepository
                .findHolidaysWithinGivenTimeFrame(
                        LocalDate.of(year, month, 1),
                        LocalDate.of(year, month, LocalDate.of(year, month, 1).lengthOfMonth())
                );
    }

    public List<CalendarDto> createCalendar(List<UserDto> users, List<VacationDto> vacations, int month, int year){

        int days = LocalDate.of(year, month, 1).lengthOfMonth();

        List<CalendarDto> calendar = fillCalendar(users, days);

        for(VacationDto v : vacations){
            int vacationsUserId = v.getUserID();

            CalendarDto foundEntry = calendar.stream().filter(u -> vacationsUserId == u.getUserDto().getId()).findFirst().get();

            int index = calendar.indexOf(foundEntry);

            foundEntry.setHolidayStatus(fillMonth(foundEntry.getHolidayStatus(), v, days));

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

    private List<String> fillMonth (List<String> month, VacationDto vacationDto, int daysInMonth){

        int index = Integer.parseInt(vacationDto.getFirstDay().substring(0,2))-1;
        int lastDay = Integer.parseInt(vacationDto.getLastDay().substring(0,2))-1;

        boolean b = !vacationDto.getLastDay().substring(3,5).equals(vacationDto.getFirstDay().substring(3,5));
        String s1 = vacationDto.getLastDay().substring(3,5);
        String s2 = vacationDto.getFirstDay().substring(3,5);

        if(!vacationDto.getLastDay().substring(3,5).equals(vacationDto.getFirstDay().substring(3,5))){
            lastDay = daysInMonth;
        }

        do{
            if(index>=month.size()) break;
            month.set(index, vacationDto.getLeaveType());
            index++;
        } while (lastDay>=index);

        return month;
    }
}
