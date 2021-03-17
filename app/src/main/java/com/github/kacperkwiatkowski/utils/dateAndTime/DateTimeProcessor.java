package com.github.kacperkwiatkowski.utils.dateAndTime;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateTimeProcessor {

    public static List<LocalDate> generateListOfDates(LocalDate firstDate, LocalDate lastDate){

        List<LocalDate> listOfLeavesDays = new ArrayList<>();

        while(!firstDate.isAfter(lastDate)){
            listOfLeavesDays.add(firstDate);
            firstDate.plusDays(1);
        }

        return listOfLeavesDays;
    }
}
