package com.github.kacperkwiatkowski.holidayscheduler_backend;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Team;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Vacation;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.VacationRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.TeamRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.LeaveType;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.RoleType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication/*
@EnableJpaRepositories("com.github.kacperkwiatkowski.holidayscheduler_backend.repository")
@EntityScan("com.github.kacperkwiatkowski.holidayscheduler_backend.model")*/
public class HolidaySchedulerBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HolidaySchedulerBackendApplication.class, args);
    }

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

/*
            Team team = teamRepository.findById(at);
            team.setTeamLeader(userRepository.findById(3));
            teamRepository.save(team);
*/

            Vacation vacation = new Vacation();
            vacation.setFirstDay(LocalDate.now());
            vacation.setLastDay(LocalDate.now());
            vacation.setAccepted(false);
            vacation.setLeaveType(LeaveType.PAYED);

            User user = userRepository.findById(3);
            user.setVacations(Arrays.asList(vacation));

            //CREATE LEAVES
            userRepository.save(user);


        };
    }
}
