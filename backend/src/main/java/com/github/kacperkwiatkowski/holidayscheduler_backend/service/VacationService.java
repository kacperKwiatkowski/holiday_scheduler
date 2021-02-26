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
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.VacationSqlRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.CalendarDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VacationService {

    private final VacationRepository vacationRepository;
    private final VacationSqlRepository vacationSqlRepository;
    private final VacationMapper vacationMapper;

    public VacationService(VacationRepository vacationRepository, VacationSqlRepository vacationSqlRepository, VacationMapper vacationMapper) {
        this.vacationRepository = vacationRepository;
        this.vacationSqlRepository = vacationSqlRepository;
        this.vacationMapper = vacationMapper;
    }

    public VacationDto createVacation(VacationDto vacationToCreate){
        //TODO check if leave qualifies.

        vacationRepository.save(vacationMapper.mapToEntity(vacationToCreate));
        return vacationToCreate;
    }

    public List<VacationDto> readRequiredVacations(List<UserDto> users, int month, int year) {

        List<Integer> usersIdsToFetchVacations = users.stream().map(UserDto::getId).collect(Collectors.toList());

        List<List<VacationDto>> foundVacations = usersIdsToFetchVacations
                .stream()
                .map(id ->
                        vacationSqlRepository.justFind(
                            id,
                            LocalDate.of(year, month, 1),
                            LocalDate.of(year, month, YearMonth.of(year, month).lengthOfMonth()))
                                .stream().map(vacationMapper::mapToDto).collect(Collectors.toList())
                        )
                .collect(Collectors.toList());

        return foundVacations.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    public VacationDto updateVacation(VacationDto vacationToUpdate){
        Optional<Vacation> foundUser = Optional.ofNullable(vacationRepository.findById(vacationToUpdate.getId()));
        if(foundUser.isPresent()){
            Vacation vacation = vacationMapper.mapToEntity(vacationToUpdate);
            vacationRepository.save(vacation);
            log.info("Vacation: " + vacationToUpdate.getId() + "updated successfully");
            return vacationToUpdate;
        } else {
            log.info("Vacation: " + vacationToUpdate.getId()  + "updated unsuccessfully");
            throw ObjectNotFoundException.createWith("PATCH impossible, user with such id doesnt exists.");
        }
    }

    @Transactional
    public VacationDto deleteVacation(int id){
        Optional<Vacation> foundVacation = Optional.ofNullable(vacationRepository.findById(id));
        if(foundVacation.isPresent()){
            vacationRepository.delete(foundVacation.get());
            log.info("DELETION successful.");
            return vacationMapper.mapToDto(foundVacation.get());
        } else {
            log.info("DELETION unsuccessful.");
            throw new ObjectNotFoundException("DELETION unsuccessful, object not found");
        }
    }

    public List<VacationDto> listAll(Integer pageNum, Integer pageSize, String sortBy, String sortOrder, String filter) {

        Pageable paging;

        //FIXME Sorting causes problems

        if(sortOrder.equals("ASC")){
            paging = PageRequest.of(pageNum, pageSize, Sort.Direction.ASC, sortBy);
        } else {
            paging = PageRequest.of(pageNum, pageSize, Sort.Direction.DESC, sortBy);
        }

        Page<Vacation> pagedResult;

        if(filter.length()<3){
            pagedResult = vacationRepository.findAll(paging);
        } else {
            pagedResult = vacationRepository.findById(filter, paging);
        }

        if(pagedResult.hasContent()) {
            return pagedResult.stream().map(vacationMapper::mapToDto).collect(Collectors.toList());
        } else {
            throw new ObjectNotFoundException("Pagination impossible");
        }
    }
}
