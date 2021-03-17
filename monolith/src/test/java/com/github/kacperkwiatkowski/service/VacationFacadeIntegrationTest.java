//package com.github.kacperkwiatkowski.service;
//
//import com.github.kacperkwiatkowski.utils.leaveConfig.VacationType;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//@RunWith(SpringRunner.class)
//@SpringBootTest()
//class VacationFacadeIntegrationTest {
//
//    @Autowired
//    private VacationFacade vacationFacade;
//
//    @Autowired
//    private VacationRepository vacationRepository;
//
//
//    @Autowired
//    private VacationFactory vacationFactory;
//
//    //given
//    //when
//    //then
//
//    @Test
//    void createVacation() {
//        //given
//        VacationDto vacationDto = VacationDto
//                .builder()
//                .firstDay(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
//                .lastDay(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
//                .isAccepted(false)
//                .userID(1)
//                .leaveType(VacationType.MATERNITY.toString())
//                .build();
//
//        //when
//        vacationDto = vacationFacade.createVacation(vacationDto);
//
//        //then
//        vacationRepository.findById(vacationDto.getId());
//    }
//
////    @Test
////    void deleteVacation_assertDeletionWasSuccessful() {
////        //given
////
////        User com.github.kacperkwiatkowski.user = userRepository.save(new User("test1@gmail.com", "1234", "Test", "Test", RoleType.ADMIN, 26, "Test"));
////
////        VacationDto vacationDto = VacationDto
////                .builder()
////                .firstDay("02/04/2021")
////                .lastDay("02/04/2021")
////                .isAccepted(false)
////                .userID(com.github.kacperkwiatkowski.user.getId())
////                .leaveType(VacationType.MATERNITY.toString())
////                .build();
////
////        //when
////        Vacation com.github.kacperkwiatkowski.vacation = vacationRepository.save(vacationFactory.mapToEntity(vacationDto));
////
////        com.github.kacperkwiatkowski.user = userRepository.findFirstByEmail("test1@gmail.com");
////        com.github.kacperkwiatkowski.user.setVacations(List.of());
////        userRepository.save(com.github.kacperkwiatkowski.user);
////
////        //then
////        assertNull(vacationRepository.findById(com.github.kacperkwiatkowski.vacation.getId()));
////
////    }
////
////
////    @Test
////    void deleteVacation_assertDeletionWasUnsuccessful() {
////        //given
////
////        User com.github.kacperkwiatkowski.user = userRepository.save(new User("test2@gmail.com", "1234", "Test", "Test", RoleType.ADMIN, 26, "Test"));
////
////        VacationDto vacationDto = VacationDto
////                .builder()
////                .firstDay("02/04/2021")
////                .lastDay("02/04/2021")
////                .isAccepted(false)
////                .userID(com.github.kacperkwiatkowski.user.getId())
////                .leaveType(VacationType.MATERNITY.toString())
////                .build();
////
////        //when
////        Vacation com.github.kacperkwiatkowski.vacation = vacationRepository.save(vacationFactory.mapToEntity(vacationDto));
////
////        //then
////
////        assertThrows(ObjectNotFoundException.class, () -> {
////            vacationService.deleteVacation(Integer.MAX_VALUE);
////        });
////    }
//}