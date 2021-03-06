package com.github.kacperkwiatkowski.holidayscheduler_backend.service;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.VacationDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.ObjectNotFoundException;
import com.github.kacperkwiatkowski.holidayscheduler_backend.mappers.VacationMapper;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Vacation;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.VacationRepository;
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
    private UserRepository userRepository = mock(UserRepository.class);
    private VacationMapper vacationMapperMock = mock(VacationMapper.class);

    VacationMapper vacationMapper;
    VacationService vacationService;

    @Before
    void setUp(){
        vacationMapper = new VacationMapper(vacationRepository, userRepository);
        vacationService = new VacationService(vacationRepository, vacationMapper, userRepository);
    }

    @Test
    void createVacation() {
        //given
        vacationMapper = new VacationMapper(vacationRepository, userRepository);
        vacationService = new VacationService(vacationRepository, vacationMapper, userRepository);
        VacationDto vacationDto = VacationDto
                        .builder()
                        .id(1)
                        .firstDay(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                        .lastDay(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                        .isAccepted(false)
                        .userID(1)
                        .leaveType(VacationType.MATERNITY.toString())
                        .build();

        Vacation vacation = vacationMapper.mapToEntity(vacationDto);

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
        vacationMapper = new VacationMapper(vacationRepository, userRepository);
        vacationService = new VacationService(vacationRepository, vacationMapper, userRepository);
        VacationDto vacationDto = VacationDto
                .builder()
                .id(1)
                .firstDay(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .lastDay(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .isAccepted(false)
                .userID(1)
                .leaveType(VacationType.MATERNITY.toString())
                .build();

        Vacation vacation = vacationMapper.mapToEntity(vacationDto);
        vacation.setUser(new User());

        //when
        when(vacationRepository.findById(anyInt())).thenReturn(vacation);
        when(userRepository.findById(anyInt())).thenReturn(new User());
        when(vacationMapperMock.mapToDto(vacation)).thenReturn(vacationDto);

        //then
        assertTrue(vacationService.deleteVacation(anyInt()) instanceof VacationDto);
    }

    @Test
    void deleteVacation_methodThrowsExceptionWhenIdIsNotFoundInRepository() {
        //given
        vacationMapper = new VacationMapper(vacationRepository, userRepository);
        vacationService = new VacationService(vacationRepository, vacationMapper, userRepository);
        VacationDto vacationDto = VacationDto
                .builder()
                .id(1)
                .firstDay(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .lastDay(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .isAccepted(false)
                .userID(1)
                .leaveType(VacationType.MATERNITY.toString())
                .build();

        Vacation vacation = vacationMapper.mapToEntity(vacationDto);
        vacation.setId(1);
        vacation.setUser(new User());

        //when
        when(vacationRepository.findById(anyInt())).thenReturn(null);
        when(userRepository.findById(anyInt())).thenReturn(new User());

        //then

        assertThrows(ObjectNotFoundException.class, () -> {
            vacationService.deleteVacation(anyInt());
        });
    }
}
