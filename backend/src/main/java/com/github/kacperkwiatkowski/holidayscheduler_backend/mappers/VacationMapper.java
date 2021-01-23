package com.github.kacperkwiatkowski.holidayscheduler_backend.mappers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.convertors.VacationTypeConvertor;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.VacationDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Vacation;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.VacationRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
            vacation.setFirstDay(LocalDate.parse(vacationDto.getFirstDay()));
            vacation.setLastDay(LocalDate.parse(vacationDto.getFirstDay()));
            vacation.setUser(userRepository.findById(vacationDto.getUserID()));
            vacation.setAccepted(vacationDto.isAccepted());
            vacation.setLeaveType(VacationTypeConvertor.convertToEnum(vacationDto.getLeaveType()));

        return vacation;
    }

    @Override
    public VacationDto mapToDto(Vacation vacation) {
        VacationDto vacationDto = VacationDto
                .builder()
                .id(vacation.getId())
                .firstDay(vacation.getFirstDay().toString())
                .lastDay(vacation.getLastDay().toString())
                .userID(vacation.getUser().getId())
                .isAccepted(vacation.isAccepted())
                .leaveType(VacationTypeConvertor.convertToString(vacation.getLeaveType()))
                .build();
        return vacationDto;
    }
}
