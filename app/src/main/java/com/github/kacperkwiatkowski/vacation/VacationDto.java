package com.github.kacperkwiatkowski.vacation;

import com.github.kacperkwiatkowski.convertors.VacationTypeConvertor;
import lombok.*;

import java.time.format.DateTimeFormatter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VacationDto {

    private int id;
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String firstDay;
    private String lastDay;
    private boolean isAccepted;
    private String leaveType;

    public static VacationDto mapToDto(Vacation vacation) {
        VacationDto vacationDto = VacationDto
                .builder()
                .id(vacation.getId())
                .firstDay(vacation.getFirstDay().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .lastDay(vacation.getLastDay().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .userID(vacation.getUser().getId())
                .email(vacation.getUser().getEmail())
                .firstName(vacation.getUser().getFirstName())
                .lastName(vacation.getUser().getLastName())
                .isAccepted(vacation.isAccepted())
                .leaveType(VacationTypeConvertor.convertToString(vacation.getVacationType()))
                .build();
        return vacationDto;
    }
}
