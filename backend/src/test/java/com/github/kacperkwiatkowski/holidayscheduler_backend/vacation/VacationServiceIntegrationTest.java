package com.github.kacperkwiatkowski.holidayscheduler_backend.vacation;

import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.ObjectNotFoundException;
import com.github.kacperkwiatkowski.holidayscheduler_backend.mappers.VacationMapper;
import com.github.kacperkwiatkowski.holidayscheduler_backend.user.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.user.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.VacationType;
import com.github.kacperkwiatkowski.holidayscheduler_backend.security.RoleType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
                .firstDay(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .lastDay(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
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

        User user = userRepository.save(new User("test1@gmail.com", "1234", "Test", "Test", RoleType.ADMIN, 26, "Test"));

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

        user = userRepository.findFirstByEmail("test1@gmail.com");
        user.setVacations(List.of());
        userRepository.save(user);

        //then
        assertNull(vacationRepository.findById(vacation.getId()));

    }


    @Test
    void deleteVacation_assertDeletionWasUnsuccessful() {
        //given
        vacationMapper = new VacationMapper(vacationRepository, userRepository);

        User user = userRepository.save(new User("test2@gmail.com", "1234", "Test", "Test", RoleType.ADMIN, 26, "Test"));

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