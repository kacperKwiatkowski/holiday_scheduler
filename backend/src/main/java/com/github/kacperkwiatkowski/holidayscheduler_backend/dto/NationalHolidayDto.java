package com.github.kacperkwiatkowski.holidayscheduler_backend.dto;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.NationalHoliday;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Team;

import java.time.LocalDate;

public class NationalHolidayDto {

    private int id;
    private String name;
    private String description;
    private LocalDate holidayDate;

    public NationalHolidayDto(int id, String name, String description, LocalDate holidayDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.holidayDate = holidayDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(LocalDate holidayDate) {
        this.holidayDate = holidayDate;
    }

    @Override
    public String toString() {
        return "NationalHolidayDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", holidayDate=" + holidayDate +
                '}';
    }

    public static NationalHolidayDto parseNationalHolidayToDto(NationalHoliday nationalHoliday){
        return new NationalHolidayDto(
                nationalHoliday.getId(),
                nationalHoliday.getName(),
                nationalHoliday.getDescription(),
                nationalHoliday.getHolidayDate()
        );
    }
}
