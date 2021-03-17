package com.github.kacperkwiatkowski;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class HolidaySchedulerBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HolidaySchedulerBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(
    ){
        return args -> {
//
//            if(userFacade.getAllUsers().size()==0) {
//                //CREATE ADMINS
//                userFacade.save(new UserSecurityDto("ola@gmail.com", passwordEncoder.encode("1234"), "Ola", "Olska", RoleType.ADMIN, 26, "profile-image"));
//                userFacade.save(new UserSecurityDto("ida@gmail.com", passwordEncoder.encode("1234"), "Ida", "Idowska", RoleType.ADMIN, 26, "profile-image"));
//
//                //CREATE TEAM LEADERS
//                userFacade.save(new UserSecurityDto("maria@gmail.com", passwordEncoder.encode("1234"), "Maria", "Marecka", RoleType.TEAM_LEADER, 26, "profile-image"));
//                userFacade.save(new UserSecurityDto("wojciech@gmail.com", passwordEncoder.encode("1234"), "Wojciech", "Wojski", RoleType.TEAM_LEADER, 26, "profile-image"));
//                userFacade.save(new UserSecurityDto("natalia@gmail.com", passwordEncoder.encode("1234"), "Natalia", "Natalska", RoleType.TEAM_LEADER, 26, "profile-image"));
//                userFacade.save(new UserSecurityDto("grzegorz@gmail.com", passwordEncoder.encode("1234"), "Grzegorz", "Gregorowicz", RoleType.TEAM_LEADER, 26, "profile-image"));
//
//
//                //CREATE USERS
//                userFacade.save(new UserSecurityDto("adam@gmail.com", passwordEncoder.encode("1234"), "Adam", "Adamski", RoleType.EMPLOYEE, 26, "profile-image"));
//                userFacade.save(new UserSecurityDto("magda@gmail.com", passwordEncoder.encode("1234"), "Magda", "Magdanska", RoleType.EMPLOYEE, 26, "profile-image"));
//                userFacade.save(new UserSecurityDto("maciej@gmail.com", passwordEncoder.encode("1234"), "Maciej", "Mackowski", RoleType.EMPLOYEE, 26, "profile-image"));
//                userFacade.save(new UserSecurityDto("edyta@gmail.com", passwordEncoder.encode("1234"), "Edyta", "Edycka", RoleType.EMPLOYEE, 26, "profile-image"));
//                userFacade.save(new UserSecurityDto("ewa@gmail.com", passwordEncoder.encode("1234"), "Ewa", "Ewska", RoleType.EMPLOYEE, 26, "profile-image"));
//                userFacade.save(new UserSecurityDto("mariusz@gmail.com", passwordEncoder.encode("1234"), "Mariusz", "Marski", RoleType.EMPLOYEE, 26, "profile-image"));
//            }

                //
//                //FILL TEAMS
//                List<Integer> avocado = new ArrayList<>();
//                List<Integer> guacamole = new ArrayList<>();
//                List<Integer> chorizo = new ArrayList<>();
//
//                Team avocadoTeam = new Team();
//                avocadoTeam.setName("Avocado");
//                avocadoTeam.setTeamSquad(new ArrayList<>());
//
//                Team guacamoleTeam = new Team();
//                guacamoleTeam.setName("Guacamole");
//                guacamoleTeam.setTeamSquad(new ArrayList<>());
//
//                Team chorizoTeam = new Team();
//                chorizoTeam.setName("Chorizo");
//                chorizoTeam.setTeamSquad(new ArrayList<>());
//
//                int at = teamRepository.save(avocadoTeam).getId();
//                int gt = teamRepository.save(guacamoleTeam).getId();
//                int ct = teamRepository.save(chorizoTeam).getId();
//
//                //FILL TEAMS
//                avocado.add(3);
//                avocado.add(11);
//                avocado.add(10);
//                avocado.add(9);
//                guacamole.add(4);
//                guacamole.add(8);
//                chorizo.add(5);
//                chorizo.add(7);
//
//                for(Integer a : avocado){
//                    User com.github.kacperkwiatkowski.user = userRepository.findById(a).get();
//                    Team com.github.kacperkwiatkowski.team = teamRepository.findById(at);
//                    com.github.kacperkwiatkowski.user.setTeam(com.github.kacperkwiatkowski.team);
//                    List<Integer> teamMembers = com.github.kacperkwiatkowski.team.getTeamSquad();
//                    teamMembers.add(a);
//                    com.github.kacperkwiatkowski.team.setTeamSquad(teamMembers);
//                    userRepository.save(com.github.kacperkwiatkowski.user);
//                }
//
//                User userA = userRepository.findById(3);
//                Team teamA = teamRepository.findById(1);
//                teamA.setTeamLeader(userA);
//                userA.setTeam(teamA);
//                userRepository.save(userA);
//
//                for(Integer g : guacamole){
//                    User com.github.kacperkwiatkowski.user = userRepository.findById(g).get();
//                    Team com.github.kacperkwiatkowski.team = teamRepository.findById(gt);
//                    com.github.kacperkwiatkowski.user.setTeam(com.github.kacperkwiatkowski.team);
//                    List<Integer> teamMembers = com.github.kacperkwiatkowski.team.getTeamSquad();
//                    teamMembers.add(g);
//                    com.github.kacperkwiatkowski.team.setTeamSquad(teamMembers);
//                    userRepository.save(com.github.kacperkwiatkowski.user);
//                }
//
//                User userG = userRepository.findById(4);
//                Team teamG = teamRepository.findById(2);
//                teamG.setTeamLeader(userG);
//                userG.setTeam(teamG);
//                userRepository.save(userG);
//
//                for(Integer c : chorizo){
//                    User com.github.kacperkwiatkowski.user = userRepository.findById(c).get();
//                    Team com.github.kacperkwiatkowski.team = teamRepository.findById(ct);
//                    com.github.kacperkwiatkowski.user.setTeam(com.github.kacperkwiatkowski.team);
//                    List<Integer> teamMembers = com.github.kacperkwiatkowski.team.getTeamSquad();
//                    teamMembers.add(c);
//                    com.github.kacperkwiatkowski.team.setTeamSquad(teamMembers);
//                    userRepository.save(com.github.kacperkwiatkowski.user);
//                }
//
//                User userC = userRepository.findById(5);
//                Team teamC = teamRepository.findById(3);
//                teamC.setTeamLeader(userC);
//                userC.setTeam(teamC);
//                userRepository.save(userC);
//
//                // CREATE VACATION
//                Vacation vacation1 = new Vacation();
//                vacation1.setFirstDay(LocalDate.now().plusDays(1));
//                vacation1.setLastDay(LocalDate.now().plusDays(3));
//                vacation1.setAccepted(true);
//                vacation1.setVacationType(VacationType.SICK);
//                User user1 = userRepository.findById(9);
//                vacation1.setUser(user1);
//
//                Vacation vacation2 = new Vacation();
//                vacation2.setFirstDay(LocalDate.now().plusDays(7));
//                vacation2.setLastDay(LocalDate.now().plusDays(9));
//                vacation2.setAccepted(true);
//                vacation2.setVacationType(VacationType.PAYED);
//                User user2 = userRepository.findById(8);
//                vacation2.setUser(user2);
//
//                Vacation vacation3 = new Vacation();
//                vacation3.setFirstDay(LocalDate.now().plusDays(11));
//                vacation3.setLastDay(LocalDate.now().plusDays(21));
//                vacation3.setAccepted(true);
//                vacation3.setVacationType(VacationType.PAYED);
//                User user3 = userRepository.findById(7);
//                vacation3.setUser(user3);
//
//
//                Vacation vacation4 = new Vacation();
//                vacation4.setFirstDay(LocalDate.now().plusDays(15));
//                vacation4.setLastDay(LocalDate.now().plusDays(21));
//                vacation4.setAccepted(true);
//                vacation4.setVacationType(VacationType.SICK);
//                User user4 = userRepository.findById(6);
//                vacation4.setUser(user4);
//
//
//                Vacation vacation5 = new Vacation();
//                vacation5.setFirstDay(LocalDate.now().plusDays(4));
//                vacation5.setLastDay(LocalDate.now().plusDays(8));
//                vacation5.setAccepted(true);
//                vacation5.setVacationType(VacationType.PAYED);
//                User user5 = userRepository.findById(5);
//                vacation5.setUser(user5);
//
//
//                Vacation vacation6 = new Vacation();
//                vacation6.setFirstDay(LocalDate.now().plusDays(0));
//                vacation6.setLastDay(LocalDate.now().plusDays(270));
//                vacation6.setAccepted(true);
//                vacation6.setVacationType(VacationType.MATERNITY);
//                User user6 = userRepository.findById(4);
//                vacation6.setUser(user6);
//
//
//                Vacation vacation7 = new Vacation();
//                vacation7.setFirstDay(LocalDate.now().minusDays(10));
//                vacation7.setLastDay(LocalDate.now().plusDays(10));
//                vacation7.setAccepted(true);
//                vacation7.setVacationType(VacationType.SICK);
//                User user7 = userRepository.findById(3);
//                vacation7.setUser(user7);
//
//
//                Vacation vacation8 = new Vacation();
//                vacation8.setFirstDay(LocalDate.of(2021, LocalDate.now().getMonth(), 1));
//                vacation8.setLastDay(LocalDate.of(2021, LocalDate.now().getMonth(), 28));
//                vacation8.setAccepted(true);
//                vacation8.setVacationType(VacationType.SICK);
//                User user8 = userRepository.findById(2);
//                vacation8.setUser(user8);
//
//
//                Vacation vacation9 = new Vacation();
//                vacation9.setFirstDay(LocalDate.of(2021, LocalDate.now().getMonth(), 03));
//                vacation9.setLastDay(LocalDate.of(2021, LocalDate.now().getMonth().getValue()+1, 02));
//                vacation9.setAccepted(true);
//                vacation9.setVacationType(VacationType.SICK);
//                User user9 = userRepository.findById(1);
//                vacation9.setUser(user9);
//
//                //CREATE LEAVES
//                vacationRepository.save(vacation1);
//                vacationRepository.save(vacation2);
//                vacationRepository.save(vacation3);
//                vacationRepository.save(vacation4);
//                vacationRepository.save(vacation5);
//                vacationRepository.save(vacation6);
//                vacationRepository.save(vacation7);
//                vacationRepository.save(vacation8);
//
//            }

        };
    }
}
