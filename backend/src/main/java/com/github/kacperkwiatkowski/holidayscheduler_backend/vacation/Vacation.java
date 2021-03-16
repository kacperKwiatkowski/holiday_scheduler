package com.github.kacperkwiatkowski.holidayscheduler_backend.vacation;

import com.github.kacperkwiatkowski.holidayscheduler_backend.convertors.VacationTypeConvertor;
import com.github.kacperkwiatkowski.holidayscheduler_backend.user.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.VacationType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vacation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column(nullable = false)
    private LocalDate firstDay;

    @Column(nullable = false)
    private LocalDate lastDay;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean isAccepted;

    @Enumerated(EnumType.ORDINAL)
    private VacationType vacationType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_Id")
    private User user;

    public VacationDto mapToDto() {
        VacationDto vacationDto = VacationDto
                .builder()
                .id(id)
                .firstDay(firstDay.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .lastDay(lastDay.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .userID(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .isAccepted(isAccepted)
                .leaveType(VacationTypeConvertor.convertToString(vacationType))
                .build();
        return vacationDto;
    }
}
