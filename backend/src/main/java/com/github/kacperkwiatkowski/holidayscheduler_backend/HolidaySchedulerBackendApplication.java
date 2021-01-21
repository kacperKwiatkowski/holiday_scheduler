package com.github.kacperkwiatkowski.holidayscheduler_backend;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.LeaveRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.TeamRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.RoleType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HolidaySchedulerBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HolidaySchedulerBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(
            UserRepository userRepository,
            LeaveRepository leaveRepository,
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

/*            //FILL TEAMS
            List<String> avocado = new ArrayList<>();
            List<String> guacamole = new ArrayList<>();
            List<String> chorizo = new ArrayList<>();

            avocado.add("adam@gmail.com");
            avocado.add("magda@gmail.com");
            avocado.add("maciej@gmail.com");

            guacamole.add("edyta@gmail.com");

            chorizo.add("ewa@gmail.com");

            Leave leave = new Leave();
            leave.setLeaveType(LeaveType.PAYED);
            leave.setFirstDay(LocalDate.now());
            leave.setLastDay(LocalDate.now());
            leave.setUser(userRepository.findById(3));
            leave.setAccepted(false);

            //CREATE LEAVES
            leaveRepository.save(new Leave());

            //CREATE TEAMS
            Team team = new Team();
            team.setName("Panchieta");
            team.setUserEmail(new ArrayList<>());
            team.setTeamLeader(userRepository.findById(3));

            teamRepository.save(team);

            teamRepository.save(new Team());
            teamRepository.save(new Team());
            teamRepository.save(new Team());

            teamRepository.save(new Team("Avocado", avocado, userRepository.findById(3)));
            teamRepository.save(new Team("Guacamole", guacamole, userRepository.findById(4)));
            teamRepository.save(new Team("Chorizo", chorizo, userRepository.findById(5)));
        */};
    }
}
