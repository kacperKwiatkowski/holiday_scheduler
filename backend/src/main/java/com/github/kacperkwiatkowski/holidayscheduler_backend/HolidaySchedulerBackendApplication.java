package com.github.kacperkwiatkowski.holidayscheduler_backend;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Team;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Vacation;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.VacationRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.TeamRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.VacationType;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.RoleType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HolidaySchedulerBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HolidaySchedulerBackendApplication.class, args);
    }

    @Profile("!test")
    @Bean
    public CommandLineRunner init(
            UserRepository userRepository,
            VacationRepository vacationRepository,
            TeamRepository teamRepository
    ){
        return args -> {
            //CREATE ADMINS
            userRepository.save(new User("ola@gmail.com", "1234", "Ola", "Olska", RoleType.ADMIN, 26));
            userRepository.save(new User("ida@gmail.com", "1234", "Ida", "Idowska", RoleType.ADMIN, 26));

            //CREATE TEAM LEADERS
            userRepository.save(new User("maria@gmail.com", "1234", "Maria", "Marecka", RoleType.TEAM_LEADER, 26));
            userRepository.save(new User("wojciech@gmail.com", "1234", "Wojciech", "Wojski", RoleType.TEAM_LEADER, 26));
            userRepository.save(new User("natalia@gmail.com", "1234", "Natalia", "Natalska", RoleType.TEAM_LEADER, 26));

            //CREATE USERS
            userRepository.save(new User("adam@gmail.com", "1234", "Adam", "Adamski", RoleType.EMPLOYEE, 26));
            userRepository.save(new User("magda@gmail.com", "1234", "Magda", "Magdanska", RoleType.EMPLOYEE, 26));
            userRepository.save(new User("maciej@gmail.com", "1234", "Maciej", "Mackowski", RoleType.EMPLOYEE, 26));
            userRepository.save(new User("edyta@gmail.com", "1234", "Edyta", "Edycka", RoleType.EMPLOYEE, 26));
            userRepository.save(new User("ewa@gmail.com", "1234", "Ewa", "Ewska", RoleType.EMPLOYEE, 26));
            userRepository.save(new User("mariusz@gmail.com", "1234", "Mariusz", "Marski", RoleType.EMPLOYEE, 26));

            //CREATE TEAMS
            List<Integer> avocado = new ArrayList<>();
            List<Integer> guacamole = new ArrayList<>();
            List<Integer> chorizo = new ArrayList<>();

            Team avocadoTeam = new Team();
            avocadoTeam.setName("avocado");
            avocadoTeam.setTeamSquad(new ArrayList<>());

            Team guacamoleTeam = new Team();
            guacamoleTeam.setName("guacamole");
            guacamoleTeam.setTeamSquad(new ArrayList<>());

            Team chorizoTeam = new Team();
            chorizoTeam.setName("chorizo");
            chorizoTeam.setTeamSquad(new ArrayList<>());

            int at = teamRepository.save(avocadoTeam).getId();
            int gt = teamRepository.save(guacamoleTeam).getId();
            int ct = teamRepository.save(chorizoTeam).getId();

            //FILL TEAMS
            avocado.add(3);
            avocado.add(10);
            avocado.add(9);
            avocado.add(8);
            guacamole.add(4);
            guacamole.add(7);
            chorizo.add(5);
            chorizo.add(6);

            for(Integer a : avocado){
                User user = userRepository.findById(a).get();
                Team team = teamRepository.findById(at);
                user.setTeam(team);
                List<Integer> teamMembers = team.getTeamSquad();
                teamMembers.add(a);
                team.setTeamSquad(teamMembers);
                userRepository.save(user);
            }

            User userA = userRepository.findById(3);
            Team teamA = teamRepository.findById(1);
            teamA.setTeamLeader(userA);
            userA.setTeam(teamA);
            userRepository.save(userA);

            for(Integer g : guacamole){
                User user = userRepository.findById(g).get();
                Team team = teamRepository.findById(gt);
                user.setTeam(team);
                List<Integer> teamMembers = team.getTeamSquad();
                teamMembers.add(g);
                team.setTeamSquad(teamMembers);
                userRepository.save(user);
            }

            User userG = userRepository.findById(4);
            Team teamG = teamRepository.findById(2);
            teamG.setTeamLeader(userG);
            userG.setTeam(teamG);
            userRepository.save(userG);

            for(Integer c : chorizo){
                User user = userRepository.findById(c).get();
                Team team = teamRepository.findById(ct);
                user.setTeam(team);
                List<Integer> teamMembers = team.getTeamSquad();
                teamMembers.add(c);
                team.setTeamSquad(teamMembers);
                userRepository.save(user);
            }

            User userC = userRepository.findById(5);
            Team teamC = teamRepository.findById(3);
            teamC.setTeamLeader(userC);
            userC.setTeam(teamC);
            userRepository.save(userC);

            // CREATE VACATION
            Vacation vacation1 = new Vacation();
            vacation1.setFirstDay(LocalDate.now().plusDays(1));
            vacation1.setLastDay(LocalDate.now().plusDays(3));
            vacation1.setAccepted(false);
            vacation1.setVacationType(VacationType.PAYED);
            User user1 = userRepository.findById(3);
            vacation1.setUser(user1);

            Vacation vacation2 = new Vacation();
            vacation2.setFirstDay(LocalDate.now().plusDays(7));
            vacation2.setLastDay(LocalDate.now().plusDays(9));
            vacation2.setAccepted(false);
            vacation2.setVacationType(VacationType.PAYED);
            User user2 = userRepository.findById(5);
            vacation2.setUser(user2);

            Vacation vacation3 = new Vacation();
            vacation3.setFirstDay(LocalDate.now().plusDays(11));
            vacation3.setLastDay(LocalDate.now().plusDays(21));
            vacation3.setAccepted(false);
            vacation3.setVacationType(VacationType.PAYED);
            User user3 = userRepository.findById(8);
            vacation3.setUser(user3);


            Vacation vacation4 = new Vacation();
            vacation4.setFirstDay(LocalDate.now().plusDays(15));
            vacation4.setLastDay(LocalDate.now().plusDays(21));
            vacation4.setAccepted(false);
            vacation4.setVacationType(VacationType.SICK);
            User user4 = userRepository.findById(1);
            vacation4.setUser(user4);


            Vacation vacation5 = new Vacation();
            vacation5.setFirstDay(LocalDate.now().plusDays(4));
            vacation5.setLastDay(LocalDate.now().plusDays(8));
            vacation5.setAccepted(false);
            vacation5.setVacationType(VacationType.PAYED);
            User user5 = userRepository.findById(4);
            vacation5.setUser(user5);


            Vacation vacation6 = new Vacation();
            vacation6.setFirstDay(LocalDate.now().plusDays(0));
            vacation6.setLastDay(LocalDate.now().plusDays(270));
            vacation6.setAccepted(false);
            vacation6.setVacationType(VacationType.MATERNITY);
            User user6 = userRepository.findById(2);
            vacation6.setUser(user6);

            //CREATE LEAVES
            vacationRepository.save(vacation1);
            vacationRepository.save(vacation2);
            vacationRepository.save(vacation3);
            vacationRepository.save(vacation4);
            vacationRepository.save(vacation5);
            vacationRepository.save(vacation6);

        };
    }
}
