package com.github.kacperkwiatkowski.holidayscheduler_backend.dto;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.NationalHoliday;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Team;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class NationalHolidayDto {

    private int id;
    private String name;
    private String description;
    private LocalDate holidayDate;

}
