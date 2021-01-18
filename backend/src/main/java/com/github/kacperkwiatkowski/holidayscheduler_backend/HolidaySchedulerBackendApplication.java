package com.github.kacperkwiatkowski.holidayscheduler_backend;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
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
    public CommandLineRunner init(UserRepository repository){
        return args -> {
            repository.save(new User("adam@gmail.com", "1234", "Adam", "Adamski", 1, 26));
            repository.save(new User("magda@gmail.com", "1234", "Magda", "Magdanska", 1, 26));
            repository.save(new User("maciej@gmail.com", "1234", "Maciej", "Mackowski", 1, 26));
            repository.save(new User("edyta@gmail.com", "1234", "Edyta", "Edycka", 1, 26));
            repository.save(new User("ewa@gmail.com", "1234", "Ewa", "Ewska", 1, 26));
            repository.save(new User("mariusz@gmail.com", "1234", "Mariusz", "Marski", 1, 26));
            repository.save(new User("maria@gmail.com", "1234", "Maria", "Marecka", 2, 26));
            repository.save(new User("wojciech@gmail.com", "1234", "Wojciech", "Wojski", 2, 26));
            repository.save(new User("ola@gmail.com", "1234", "Ola", "Olska", 3, 26));
            repository.save(new User("ida@gmail.com", "1234", "Ida", "Idowska", 3, 26));
        };
    }
}
