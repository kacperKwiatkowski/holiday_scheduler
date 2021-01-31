package com.github.kacperkwiatkowski.holidayscheduler_backend.utils.calendar;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.VacationDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Vacation;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Calendar {

    public static Map<Integer, List<String>> createCalendar(List<Integer> users, List<VacationDto> vacations, int month, int year){

        Map<Integer, List<String>> calendar = new HashMap<>();

        int days = LocalDate.of(year, month, 1).lengthOfMonth();

        fillMapWithUsers(users, calendar, days);

        for(VacationDto v : vacations){
            calendar.replace(v.getUserID(), fillMonth(calendar.get(v.getUserID()), v));
        }

        return calendar;
    }

    private static void fillMapWithUsers(List<Integer> users, Map<Integer, List<String>> calendar, int days) {
        for(Integer i : users) {
            if (!calendar.containsKey(i)) {
                calendar.put(i, generateEmptyMonth(days));
            }
        }
    }

    private static List<String> generateEmptyMonth(int days){
        List<String> emptyMonth = new LinkedList<>();
        for(int i = 0; i < days; i++){
            emptyMonth.add("NONE");
        }
        return emptyMonth;
    }

    private static List<String> fillMonth (List<String> month, VacationDto vacationDto){

        int index = Integer.parseInt(vacationDto.getFirstDay().substring(0,2))-1;
        int lastDay = Integer.parseInt(vacationDto.getLastDay().substring(0,2))-1;
        do{
            month.set(index, vacationDto.getLeaveType().toString());
            index++;
        } while (lastDay>=index);

        return month;
    }
}