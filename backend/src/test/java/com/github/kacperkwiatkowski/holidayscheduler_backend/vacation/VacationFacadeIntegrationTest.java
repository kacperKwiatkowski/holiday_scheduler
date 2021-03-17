package com.github.kacperkwiatkowski.holidayscheduler_backend.vacation;

import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.VacationType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertThrows;
@RunWith(SpringRunner.class)
@SpringBootTest()
class VacationFacadeIntegrationTest {

    @Autowired
    private VacationFacade vacationFacade;

    @Autowired
    private VacationRepository vacationRepository;


    @Autowired
    private VacationFactory vacationFactory;

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
        vacationDto = vacationFacade.createVacation(vacationDto);

        //then
        vacationRepository.findById(vacationDto.getId());
    }

//    @Test
//    void deleteVacation_assertDeletionWasSuccessful() {
//        //given
//
//        User user = userRepository.save(new User("test1@gmail.com", "1234", "Test", "Test", RoleType.ADMIN, 26, "Test"));
//
//        VacationDto vacationDto = VacationDto
//                .builder()
//                .firstDay("02/04/2021")
//                .lastDay("02/04/2021")
//                .isAccepted(false)
//                .userID(user.getId())
//                .leaveType(VacationType.MATERNITY.toString())
//                .build();
//
//        //when
//        Vacation vacation = vacationRepository.save(vacationFactory.mapToEntity(vacationDto));
//
//        user = userRepository.findFirstByEmail("test1@gmail.com");
//        user.setVacations(List.of());
//        userRepository.save(user);
//
//        //then
//        assertNull(vacationRepository.findById(vacation.getId()));
//
//    }
//
//
//    @Test
//    void deleteVacation_assertDeletionWasUnsuccessful() {
//        //given
//
//        User user = userRepository.save(new User("test2@gmail.com", "1234", "Test", "Test", RoleType.ADMIN, 26, "Test"));
//
//        VacationDto vacationDto = VacationDto
//                .builder()
//                .firstDay("02/04/2021")
//                .lastDay("02/04/2021")
//                .isAccepted(false)
//                .userID(user.getId())
//                .leaveType(VacationType.MATERNITY.toString())
//                .build();
//
//        //when
//        Vacation vacation = vacationRepository.save(vacationFactory.mapToEntity(vacationDto));
//
//        //then
//
//        assertThrows(ObjectNotFoundException.class, () -> {
//            vacationService.deleteVacation(Integer.MAX_VALUE);
//        });
//    }
}