package com.github.kacperkwiatkowski.holidayscheduler_backend.vacation;

import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.ObjectNotFoundException;
import com.github.kacperkwiatkowski.holidayscheduler_backend.nationalHoliday.NationalHolidayService;
import com.github.kacperkwiatkowski.holidayscheduler_backend.user.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.user.UserDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.user.UserService;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.VacationType;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class VacationServiceTest {

    private VacationRepository vacationRepository = mock(VacationRepository.class);
    private VacationFactory vacationFactory = mock(VacationFactory.class);
    private NationalHolidayService nationalHolidayService = mock(NationalHolidayService.class);
    private UserService userService = mock(UserService.class);

    VacationService vacationService;

    @Before
    void setUp(){
        vacationService  = new VacationService(vacationRepository, vacationFactory, nationalHolidayService, userService);
    }

    @Test
    void createVacation() {
        //given
        vacationService  = new VacationService(vacationRepository, vacationFactory, nationalHolidayService, userService);
        VacationDto vacationDto = VacationDto
                        .builder()
                        .id(1)
                        .firstDay(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                        .lastDay(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                        .isAccepted(false)
                        .userID(1)
                        .leaveType(VacationType.MATERNITY.toString())
                        .build();

        Vacation vacation = vacationFactory.mapToEntity(vacationDto);

        //when
        when(vacationRepository.save(vacation)).thenReturn(vacation);

        //then
        assertEquals(vacationDto, vacationService.createVacation(vacationDto));
    }

    @Test
    void readRequiredVacations() {
    }

    @Test
    void deleteVacation_methodReturnsObjectIfIdIsFoundInRepository() {
        //given
        vacationService  = new VacationService(vacationRepository, vacationFactory, nationalHolidayService, userService);
        VacationDto vacationDto = VacationDto
                .builder()
                .id(1)
                .firstDay(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .lastDay(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .isAccepted(false)
                .userID(1)
                .leaveType(VacationType.MATERNITY.toString())
                .build();

        Vacation vacation = vacationFactory.mapToEntity(vacationDto);
        vacation.setUser(new User());

        //when
        when(vacationRepository.findById(anyInt())).thenReturn(vacation);
        when(userService.findById(anyInt())).thenReturn(new UserDto());
        when(vacation.mapToDto()).thenReturn(vacationDto);

        //then
        assertTrue(vacationService.deleteVacation(anyInt()) instanceof VacationDto);
    }

    @Test
    void deleteVacation_methodThrowsExceptionWhenIdIsNotFoundInRepository() {
        //given
        vacationService  = new VacationService(vacationRepository, vacationFactory, nationalHolidayService, userService);
        VacationDto vacationDto = VacationDto
                .builder()
                .id(1)
                .firstDay(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .lastDay(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .isAccepted(false)
                .userID(1)
                .leaveType(VacationType.MATERNITY.toString())
                .build();

        Vacation vacation = vacationFactory.mapToEntity(vacationDto);
        vacation.setId(1);
        vacation.setUser(new User());

        //when
        when(vacationRepository.findById(anyInt())).thenReturn(null);
        when(userService.findById(anyInt())).thenReturn(new UserDto());

        //then

        assertThrows(ObjectNotFoundException.class, () -> {
            vacationService.deleteVacation(anyInt());
        });
    }
}
