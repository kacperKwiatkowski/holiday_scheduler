package com.github.kacperkwiatkowski.holidayscheduler_backend.nationalHoliday;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NationalHolidayDto {

    private int id;
    private String name;
    private String description;
    private LocalDate holidayDate;

}
