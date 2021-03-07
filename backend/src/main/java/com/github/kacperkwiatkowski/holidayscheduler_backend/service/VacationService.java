package com.github.kacperkwiatkowski.holidayscheduler_backend.service;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.UserDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.VacationDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.ObjectNotFoundException;
import com.github.kacperkwiatkowski.holidayscheduler_backend.mappers.VacationMapper;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Vacation;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.NationalHolidayRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.VacationRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.VacationType;
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
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Slf4j
@Service
public class VacationService {

    private final VacationRepository vacationRepository;
    private final VacationMapper vacationMapper;
    private final UserRepository userRepository;
    private final NationalHolidayRepository nationalHolidayRepository;

    public VacationService(VacationRepository vacationRepository, VacationMapper vacationMapper, UserRepository userRepository, NationalHolidayRepository nationalHolidayRepository) {
        this.vacationRepository = vacationRepository;
        this.vacationMapper = vacationMapper;
        this.userRepository = userRepository;
        this.nationalHolidayRepository = nationalHolidayRepository;
    }

    @Transactional
    public VacationDto createVacation(VacationDto vacationToCreate){
        Vacation vacation = vacationMapper.mapToEntity(vacationToCreate);
        User user = userRepository.findById(vacation.getUser().getId());

        int daysBetween = getDaysBetween(vacation);


        validateHolidayRequest(vacation, user, daysBetween);

        userRepository.subtractDaysOffFromUser(user.getId(), daysBetween);
        vacationRepository.save(vacation);

        return vacationToCreate;
    }

    public List<VacationDto> readRequiredVacations(List<UserDto> users, int month, int year) {

        List<Integer> usersIdsToFetchVacations = users.stream().map(UserDto::getId).collect(Collectors.toList());

        //FIXME Write this function as a query
        List<List<VacationDto>> foundVacations = usersIdsToFetchVacations
                .stream()
                .map(id ->
                                vacationRepository.findAcceptedHolidaysWithinGivenTimeFrame(
                            id,
                            LocalDate.of(year, month, 1),
                            LocalDate.of(year, month, YearMonth.of(year, month).lengthOfMonth()))
                                .stream().map(vacationMapper::mapToDto).collect(Collectors.toList())
                        )
                .collect(Collectors.toList());

        return foundVacations.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    public VacationDto updateVacation(VacationDto vacationToUpdate){
        Optional<Vacation> foundVacation = Optional.ofNullable(vacationRepository.findById(vacationToUpdate.getId()));
        if(foundVacation.isPresent()){

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

            if(foundVacation.get().getVacationType()==VacationType.PAYED){

                userRepository.addDaysOffFromUser(
                        foundVacation.get().getUser().getId(),
                        getDaysBetween(foundVacation.get())
                );
            }
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

        if(sortOrder.equals("ASC")){
            paging = PageRequest.of(pageNum, pageSize, Sort.Direction.ASC, sortBy);
        } else {
            paging = PageRequest.of(pageNum, pageSize, Sort.Direction.DESC, sortBy);
        }

        Page<Vacation> pagedResult;

        if(filter.length()<3){
            pagedResult = vacationRepository.findAll(paging);
        } else {
            pagedResult = vacationRepository.findWithFilter(filter, paging);
        }

        if(pagedResult.hasContent()) {
            return pagedResult.stream().map(vacationMapper::mapToDto).collect(Collectors.toList());
        } else {
            throw new ObjectNotFoundException("Pagination impossible");
        }
    }

    private void validateHolidayRequest(Vacation vacation, User user, int daysBetween) {
        if(vacation.getVacationType()== VacationType.PAYED && daysBetween > user.getDaysOffLeft()){
            //TODO Create another controller helper class for this exception
            throw new ObjectNotFoundException("Not enough days off left to place vacation request");
        }
        if (!vacationRepository.findHolidaysWithinGivenTimeFrame(user.getId(), vacation.getFirstDay(), vacation.getLastDay()).isEmpty()){
            throw new ObjectNotFoundException("Holiday already placed within requested days");
        }
    }

    private int getDaysBetween(Vacation vacation) {
        return (int)
                (DAYS.between(vacation.getFirstDay(), vacation.getLastDay()) + 1) -
                nationalHolidayRepository.findHolidaysWithinGivenTimeFrame(vacation.getFirstDay(), vacation.getLastDay()).size();
    }
}
