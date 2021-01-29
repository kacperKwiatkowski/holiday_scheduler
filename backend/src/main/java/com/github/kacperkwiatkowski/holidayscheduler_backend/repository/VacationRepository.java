package com.github.kacperkwiatkowski.holidayscheduler_backend.repository;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Integer> {

    Vacation findById(int id);

    List<Vacation> findByIdAndFirstDayAfterAndLastDayBefore(int id, LocalDate firstDay, LocalDate LastDate);

//
//    @Query(
//            value = "SELECT FROM vacation WHERE firstDay IS AFTER :firstDay /*where id like :id and (firstDay between :firstDay and :lastDay or lastDay between :firstDay and :lastDay*/",
//            nativeQuery = true
//    )
//    List<Vacation> justFind(@Param("id") int id, @Param("firstDay") LocalDate firstDay, @Param("lastDay") LocalDate LastDate);

}
