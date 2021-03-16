package com.github.kacperkwiatkowski.holidayscheduler_backend.mappers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.nationalHoliday.NationalHolidayDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.nationalHoliday.NationalHoliday;
import org.springframework.stereotype.Component;

@Component
public class NationalHolidayMapper implements ObjectMapper<NationalHolidayDto, NationalHoliday> {

    @Override
    public NationalHoliday mapToEntity(NationalHolidayDto nationalHolidayDto) {
        return NationalHoliday.builder()
                .id(nationalHolidayDto.getId())
                .name(nationalHolidayDto.getName())
                .description(nationalHolidayDto.getDescription())
                .holidayDate(nationalHolidayDto.getHolidayDate())
                .build();
    }

    @Override
    public NationalHolidayDto mapToDto(NationalHoliday nationalHoliday) {
        return NationalHolidayDto.builder()
                .id(nationalHoliday.getId())
                .name(nationalHoliday.getName())
                .description(nationalHoliday.getDescription())
                .holidayDate(nationalHoliday.getHolidayDate())
                .build();
    }
}
