package com.github.kacperkwiatkowski.holidayscheduler_backend.mappers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.convertors.VacationTypeConvertor;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.VacationDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Vacation;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.VacationRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class VacationMapper implements ObjectMapper<VacationDto, Vacation> {

    private final VacationRepository vacationRepository;
    private final UserRepository userRepository;

    public VacationMapper(VacationRepository vacationRepository, UserRepository userRepository) {
        this.vacationRepository = vacationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Vacation mapToEntity(VacationDto vacationDto) {
        Vacation vacation;
        Optional<Vacation> optionalVacation = Optional.ofNullable(vacationRepository.findById(vacationDto.getId()));

        if(optionalVacation.isEmpty()){
            vacation = new Vacation();
        } else {
            vacation = optionalVacation.get();
        }
            vacation.setFirstDay(
                    LocalDate
                            .parse(vacationDto.getFirstDay(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            vacation.setLastDay(LocalDate.parse(vacationDto.getLastDay(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            vacation.setUser(userRepository.findById(vacationDto.getUserID()));
            vacation.setAccepted(vacationDto.isAccepted());
            vacation.setVacationType(VacationTypeConvertor.convertToEnum(vacationDto.getLeaveType()));

        return vacation;
    }

    @Override
    public VacationDto mapToDto(Vacation vacation) {
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
