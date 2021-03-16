package com.github.kacperkwiatkowski.holidayscheduler_backend.vacation;

import com.github.kacperkwiatkowski.holidayscheduler_backend.convertors.VacationTypeConvertor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
class VacationFactory {

    private final VacationRepository vacationRepository;

    public VacationFactory(VacationRepository vacationRepository) {
        this.vacationRepository = vacationRepository;
    }

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
        vacation.setUser(vacationRepository.findById(vacationDto.getId()).getUser());
        vacation.setAccepted(vacationDto.isAccepted());
        vacation.setVacationType(VacationTypeConvertor.convertToEnum(vacationDto.getLeaveType()));

        return vacation;
    }
}
