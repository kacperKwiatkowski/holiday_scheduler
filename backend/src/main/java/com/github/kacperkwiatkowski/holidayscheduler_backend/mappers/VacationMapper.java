package com.github.kacperkwiatkowski.holidayscheduler_backend.mappers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.UserDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.VacationDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Vacation;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.TeamRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.VacationRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.LeaveType;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Mapper
public class VacationMapper implements ObjectMapper<VacationDto, Vacation> {

    @Autowired
    VacationRepository vacationRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Vacation mapToDto(VacationDto vacationDto) {
        Vacation vacation;
        Optional<Vacation> optionalVacation = Optional.ofNullable(vacationRepository.findById(vacationDto.getId()));

        if(optionalVacation.isEmpty()){
            vacation = new Vacation();
        } else {
            vacation = optionalVacation.get();
        }

        if(!vacationDto.getFirstDay().equals(vacation.getFirstDay())){
            vacation.setFirstDay(vacationDto.getFirstDay());
        }

        if(!vacationDto.getLastDay().equals(vacation.getLastDay())){
            vacation.setLastDay(vacationDto.getFirstDay());
        }

        if(vacationDto.getUserID()!=(vacation.getUser().getId())){
            vacation.setUser(userRepository.findById(vacationDto.getUserID()));
        }

        if(!vacationDto.isAccepted() == vacation.isAccepted()){
            vacation.setAccepted(vacationDto.isAccepted());
        }

        if(!vacationDto.getLeaveType().equals(vacation.getLeaveType())){
            vacation.setLeaveType(vacationDto.getLeaveType());
        }

        return vacation;
    }

    @Override
    public VacationDto mapToEntity(Vacation vacation) {
        VacationDto vacationDto = VacationDto
                .builder()
                .id(vacation.getId())
                .firstDay(vacation.getFirstDay())
                .lastDay(vacation.getLastDay())
                .userID(vacation.getUser().getId())
                .isAccepted(vacation.isAccepted())
                .leaveType(vacation.getLeaveType())
                .build();
        return vacationDto;
    }
}
