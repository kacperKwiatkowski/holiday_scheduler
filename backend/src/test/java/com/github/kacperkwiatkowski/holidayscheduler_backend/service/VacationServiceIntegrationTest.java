package com.github.kacperkwiatkowski.holidayscheduler_backend.service;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.VacationDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.ObjectNotFoundException;
import com.github.kacperkwiatkowski.holidayscheduler_backend.mappers.VacationMapper;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Vacation;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.VacationRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.VacationType;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.roleConfig.RoleType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
@RunWith(SpringRunner.class)
@SpringBootTest()
class VacationServiceIntegrationTest {

    @Autowired
    private VacationService vacationService;

    @Autowired
    private VacationRepository vacationRepository;

    @Autowired
    private UserRepository userRepository;

    VacationMapper vacationMapper;



    //given
    //when
    //then

    @Test
    void createVacation() {
        //given
        VacationDto vacationDto = VacationDto
                .builder()
                .firstDay(LocalDate.now().toString())
                .lastDay(LocalDate.now().toString())
                .isAccepted(false)
                .userID(1)
                .leaveType(VacationType.MATERNITY.toString())
                .build();

        //when
        vacationDto = vacationService.createVacation(vacationDto);

        //then
        vacationRepository.findById(vacationDto.getId());
    }

    @Test
    void deleteVacation_assertDeletionWasSuccessful() {
        //given
        vacationMapper = new VacationMapper(vacationRepository, userRepository);

        User user = userRepository.save(new User("test@gmail.com", "1234", "Test", "Test", RoleType.ADMIN, 26));

        VacationDto vacationDto = VacationDto
                .builder()
                .firstDay("02/04/2021")
                .lastDay("02/04/2021")
                .isAccepted(false)
                .userID(user.getId())
                .leaveType(VacationType.MATERNITY.toString())
                .build();

        //when
        Vacation vacation = vacationRepository.save(vacationMapper.mapToEntity(vacationDto));
        VacationDto responseVacationDto = vacationService.deleteVacation(vacation.getId());

        //then
        assertNull(vacationRepository.findById(vacation.getId()));
        assertEquals(vacationDto.getFirstDay(), responseVacationDto.getFirstDay());
        assertEquals(vacationDto.getLeaveType(), responseVacationDto.getLeaveType());
        assertEquals(vacationDto.getUserID(), responseVacationDto.getUserID());
    }


    @Test
    void deleteVacation_assertDeletionWasUnsuccessful() {
        //given
        vacationMapper = new VacationMapper(vacationRepository, userRepository);

        User user = userRepository.save(new User("test@gmail.com", "1234", "Test", "Test", RoleType.ADMIN, 26));

        VacationDto vacationDto = VacationDto
                .builder()
                .firstDay("02/04/2021")
                .lastDay("02/04/2021")
                .isAccepted(false)
                .userID(user.getId())
                .leaveType(VacationType.MATERNITY.toString())
                .build();

        //when
        Vacation vacation = vacationRepository.save(vacationMapper.mapToEntity(vacationDto));

        //then

        assertThrows(ObjectNotFoundException.class, () -> {
            vacationService.deleteVacation(Integer.MAX_VALUE);
        });
    }
}