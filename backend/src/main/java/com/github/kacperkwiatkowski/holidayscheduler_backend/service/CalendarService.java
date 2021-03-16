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

import static java.lang.Integer.parseInt;

@Slf4j
@Service
public class CalendarService {

    private final UserRepository userRepository;
    private final VacationRepository vacationRepository;
    private final NationalHolidayRepository nationalHolidayRepository;

    CalendarService(UserRepository userRepository, VacationRepository vacationRepository, NationalHolidayRepository nationalHolidayRepository) {
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

        calendar = insertIndividualHolidays(vacations, days, calendar, month);

        calendar = insertNationalHolidays(month, year, days, calendar);

        return calendar;
    }

    private  List<CalendarDto>  insertIndividualHolidays(List<VacationDto> vacations, int days, List<CalendarDto> calendar, int month) {
        for(VacationDto v : vacations){
            int vacationsUserId = v.getUserID();

            CalendarDto foundEntry = calendar.stream().filter(u -> vacationsUserId == u.getUserDto().getId()).findFirst().get();

            int index = calendar.indexOf(foundEntry);

            foundEntry.setHolidayStatus(fillMonth(foundEntry.getHolidayStatus(), v, days, month));

            calendar.set(index, foundEntry);
        }

        return calendar;
    }

    private List<CalendarDto>  insertNationalHolidays(int month, int year, int days, List<CalendarDto> calendar) {
        Queue<NationalHoliday> nationalHolidaysInThisMonth =
                nationalHolidayRepository.findHolidaysWithinGivenTimeFrame(LocalDate.of(year, month, 1), LocalDate.of(year, month, days));
        while(!nationalHolidaysInThisMonth.isEmpty()){

            calendar
                    .forEach(
                            user -> user
                                    .getHolidayStatus()
                                    .set(nationalHolidaysInThisMonth
                                            .peek()
                                            .getHolidayDate()
                                            .getDayOfMonth()-1,
                                        nationalHolidaysInThisMonth.peek().getName().toUpperCase()));

            nationalHolidaysInThisMonth.poll();
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

    private List<String> fillMonth (List<String> month, VacationDto vacationDto, int daysInMonth, int currentMonth){

        int index = generateIndex(vacationDto, currentMonth);
        int lastDay = calculateLastDay(vacationDto, daysInMonth, currentMonth);

        do{
            if(index>=month.size()) break;
            month.set(index, vacationDto.getLeaveType());
            index++;
        } while (lastDay>=index);

        return month;
    }

    private int generateIndex(VacationDto vacationDto, int currentMonth) {

        if(currentMonth > parseInt(vacationDto.getFirstDay().substring(3,5))){
            return 0;
        } else {
            return parseInt(vacationDto.getFirstDay().substring(0,2))-1;
        }
    }

    private int calculateLastDay(VacationDto vacationDto, int daysInMonth, int currentMonth) {

        if(parseInt(vacationDto.getLastDay().substring(3,5)) != currentMonth && parseInt(vacationDto.getLastDay().substring(3,5)) > parseInt(vacationDto.getFirstDay().substring(3,5))){
            return daysInMonth;
        } else {
            return parseInt(vacationDto.getLastDay().substring(0,2))-1;
        }
    }
}
