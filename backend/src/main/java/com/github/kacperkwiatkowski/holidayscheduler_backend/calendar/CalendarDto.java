package com.github.kacperkwiatkowski.holidayscheduler_backend.calendar;

import com.github.kacperkwiatkowski.holidayscheduler_backend.user.dto.UserDto;
import lombok.*;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalendarDto {

    private UserDto userDto;
    private List<String> holidayStatus;

}