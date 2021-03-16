package com.github.kacperkwiatkowski.holidayscheduler_backend.vacation;

import com.github.kacperkwiatkowski.holidayscheduler_backend.nationalHoliday.NationalHolidayService;
import com.github.kacperkwiatkowski.holidayscheduler_backend.user.UserDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.ObjectNotFoundException;
import com.github.kacperkwiatkowski.holidayscheduler_backend.user.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.user.UserService;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.VacationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VacationService {

    private final VacationRepository vacationRepository;
    private final VacationFactory vacationFactory;
    private final NationalHolidayService nationalHolidayService;
    private final UserService userService;

    VacationService(VacationRepository vacationRepository, VacationFactory vacationFactory, NationalHolidayService nationalHolidayService, UserService userService) {
        this.vacationRepository = vacationRepository;
        this.vacationFactory = vacationFactory;
        this.nationalHolidayService = nationalHolidayService;
        this.userService = userService;
    }

    @Transactional
    public VacationDto createVacation(VacationDto vacationToCreate){
        Vacation vacation = vacationFactory.mapToEntity(vacationToCreate);


        int daysBetween = getDaysBetween(vacation);
        UserDto user = userService.findById(vacation.getUser().getId());
        validateHolidayRequest(vacation, user, daysBetween);
        vacationRepository.save(vacation);

        userService.subtractDaysOffFromUser(user.getId(), daysBetween);

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
                                .stream().map(Vacation::mapToDto).collect(Collectors.toList())
                        )
                .collect(Collectors.toList());

        return foundVacations.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    public VacationDto updateVacation(VacationDto vacationToUpdate){
        Optional<Vacation> foundVacation = Optional.ofNullable(vacationRepository.findById(vacationToUpdate.getId()));
        if(foundVacation.isPresent()){

            Vacation vacation = vacationFactory.mapToEntity(vacationToUpdate);

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

                userService.addDaysOffFromUser(
                        foundVacation.get().getUser().getId(),
                        getDaysBetween(foundVacation.get())
                );
            }
            vacationRepository.delete(foundVacation.get());
            log.info("DELETION successful.");
            return (foundVacation.get().mapToDto());
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
            return pagedResult.stream().map(Vacation::mapToDto).collect(Collectors.toList());
        } else {
            throw new ObjectNotFoundException("Pagination impossible");
        }
    }

    private void validateHolidayRequest(Vacation vacation, UserDto userDto, int daysBetween) {
        if(vacation.getVacationType()== VacationType.PAYED && daysBetween > userDto.getDaysOffLeft()){
            //TODO Create another controller helper class for this exception
            throw new ObjectNotFoundException("Not enough days off left to place vacation request");
        }
        if (!vacationRepository.findHolidaysWithinGivenTimeFrame(userDto.getId(), vacation.getFirstDay(), vacation.getLastDay()).isEmpty()){
            throw new ObjectNotFoundException("Holiday already placed within requested days");
        }
        if(vacation.getFirstDay().isAfter(vacation.getLastDay())){
            throw new ObjectNotFoundException("Holiday's first dat cannot be after its last day");
        }
    }

    private int getDaysBetween(Vacation vacation) {
        LocalDate currentDay = vacation.getFirstDay();
        int numOfFreeDays = 0;
        while (vacation.getLastDay().isAfter(currentDay)){
            DayOfWeek dow = currentDay.getDayOfWeek();
            if(!(currentDay.getDayOfWeek()==DayOfWeek.SATURDAY || currentDay.getDayOfWeek()==DayOfWeek.SUNDAY)){
                numOfFreeDays++;
            }
            currentDay = currentDay.plusDays((long) 1);
        }

        return numOfFreeDays - nationalHolidayService.findHolidaysWithinGivenTimeFrame(vacation.getFirstDay(), vacation.getLastDay()).size();
    }
}
