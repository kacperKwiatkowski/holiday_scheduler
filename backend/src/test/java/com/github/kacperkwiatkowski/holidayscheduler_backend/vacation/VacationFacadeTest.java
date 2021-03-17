package com.github.kacperkwiatkowski.holidayscheduler_backend.vacation;

import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.ObjectNotFoundException;
import com.github.kacperkwiatkowski.holidayscheduler_backend.nationalHoliday.NationalHolidayFacade;
import com.github.kacperkwiatkowski.holidayscheduler_backend.user.dto.UserDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.user.UserFacade;
import com.github.kacperkwiatkowski.holidayscheduler_backend.user.query.SimpleUserQueryDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.VacationType;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class VacationFacadeTest {

    private VacationRepository vacationRepository = mock(VacationRepository.class);
    private VacationFactory vacationFactory = mock(VacationFactory.class);
    private NationalHolidayFacade nationalHolidayFacade = mock(NationalHolidayFacade.class);
    private UserFacade userFacade = mock(UserFacade.class);

    VacationFacade vacationFacade;

    @Before
    void setUp(){
        vacationFacade = new VacationFacade(vacationRepository, vacationFactory, nationalHolidayFacade, userFacade);
    }

    @Test
    void createVacation() {
        //given
        vacationFacade = new VacationFacade(vacationRepository, vacationFactory, nationalHolidayFacade, userFacade);
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
        assertEquals(vacationDto, vacationFacade.createVacation(vacationDto));
    }

    @Test
    void readRequiredVacations() {
    }

    @Test
    void deleteVacation_methodReturnsObjectIfIdIsFoundInRepository() {
        //given
        vacationFacade = new VacationFacade(vacationRepository, vacationFactory, nationalHolidayFacade, userFacade);
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
        vacation.setUser(new SimpleUserQueryDto());

        //when
        when(vacationRepository.findById(anyInt())).thenReturn(vacation);
        when(userFacade.findById(anyInt())).thenReturn(new UserDto());
        when(vacation.mapToDto()).thenReturn(vacationDto);

        //then
        assertTrue(vacationFacade.deleteVacation(anyInt()) instanceof VacationDto);
    }

    @Test
    void deleteVacation_methodThrowsExceptionWhenIdIsNotFoundInRepository() {
        //given
        vacationFacade = new VacationFacade(vacationRepository, vacationFactory, nationalHolidayFacade, userFacade);
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
        vacation.setUser(new SimpleUserQueryDto());

        //when
        when(vacationRepository.findById(anyInt())).thenReturn(null);
        when(userFacade.findById(anyInt())).thenReturn(new UserDto());

        //then

        assertThrows(ObjectNotFoundException.class, () -> {
            vacationFacade.deleteVacation(anyInt());
        });
    }
}
