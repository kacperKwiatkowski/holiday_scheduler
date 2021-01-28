package com.github.kacperkwiatkowski.holidayscheduler_backend.repository;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Integer> {

    Vacation findById(int id);

    /*@Query("FROM Vacation  where user.email like :email and (firstDay between :firstDay and :lastDay or lastDay between :firstDay and :lastDay")*/
    List<Vacation> findHolidaysInGivenMonthAndYear(int id, LocalDate firstDay, LocalDate LastDate);

}
