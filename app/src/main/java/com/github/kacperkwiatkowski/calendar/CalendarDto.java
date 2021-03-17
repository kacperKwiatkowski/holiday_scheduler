package com.github.kacperkwiatkowski.calendar;

import com.github.kacperkwiatkowski.user.UserDto;
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