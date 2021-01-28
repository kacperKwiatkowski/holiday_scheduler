package com.github.kacperkwiatkowski.holidayscheduler_backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.UserDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.VacationDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.ObjectNotFoundException;
import com.github.kacperkwiatkowski.holidayscheduler_backend.mappers.UserMapper;
import com.github.kacperkwiatkowski.holidayscheduler_backend.mappers.VacationMapper;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Vacation;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.VacationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VacationService {

    private final VacationRepository vacationRepository;
    private final VacationMapper vacationMapper;
    //private final UserMapper userMapper;

    public VacationService(VacationRepository vacationRepository, VacationMapper vacationMapper/*, UserMapper userMapper*/) {
        this.vacationRepository = vacationRepository;
        this.vacationMapper = vacationMapper;
        //this.userMapper = userMapper;
    }

    public VacationDto createVacation(VacationDto vacationToCreate){
        //TODO check if leave qualifies.

        vacationRepository.save(vacationMapper.mapToEntity(vacationToCreate));
        return vacationToCreate;
    }

/*    public List<VacationDto> readRequiredVacations(String usersJson, int month, int year) throws JsonProcessingException {


        List<User> usersForWhichToFetchVacations =
                new ObjectMapper().readValue(usersJson, new TypeReference<List<UserDto>>() {})
                .stream().map(userMapper::mapToEntity)
                .collect(Collectors.toList());

        List<Vacation> foundVacations =new ArrayList<>();

        usersForWhichToFetchVacations
                        .stream()
                        .map(user -> vacationRepository.findHolidaysInGivenMonthAndYear(
                                user.getId(),
                                LocalDate.of(year, month, 1),
                                LocalDate.of(year, month, YearMonth.of(year, month).lengthOfMonth())))
                        .forEach(vacation -> foundVacations.add((Vacation) vacation));

        //TODO Apply solution
        return null;
    }*/

    public VacationDto deleteVacation(int id){
        Optional<Vacation> foundVacation = Optional.ofNullable(vacationRepository.findById(id));
        if(foundVacation.isPresent()){
            return vacationMapper.mapToDto(foundVacation.get());
        } else {
            throw new ObjectNotFoundException("DELETION unsuccessful, object not found");
        }
    }
}
