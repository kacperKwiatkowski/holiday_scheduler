package com.github.kacperkwiatkowski.holidayscheduler_backend.service;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.VacationDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.ObjectNotFoundException;
import com.github.kacperkwiatkowski.holidayscheduler_backend.mappers.VacationMapper;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Vacation;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.VacationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class VacationService {

    private final VacationRepository vacationRepository;
    private final VacationMapper vacationMapper;

    public VacationService(VacationRepository vacationRepository, VacationMapper vacationMapper) {
        this.vacationRepository = vacationRepository;
        this.vacationMapper = vacationMapper;
    }

    public VacationDto createVacation(VacationDto vacationToCreate){
        //TODO check if leave qualifies.

        vacationRepository.save(vacationMapper.mapToEntity(vacationToCreate));
        return vacationToCreate;
    }

    public VacationDto deleteVacation(int id){
        Optional<Vacation> foundVacation = Optional.ofNullable(vacationRepository.findById(id));
        if(foundVacation.isPresent()){
            return vacationMapper.mapToDto(foundVacation.get());
        } else {
            throw new ObjectNotFoundException("DELETION unsuccessful, object not found");
        }
    }
}
