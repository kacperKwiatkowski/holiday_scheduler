package com.github.kacperkwiatkowski.holidayscheduler_backend.repository;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Vacation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface VacationSqlRepository extends Repository<Vacation, Integer> {

    @Query(
            value = "SELECT * FROM Vacation v WHERE v.users_id = ?1 AND v.firstDay BETWEEN ?2 AND ?3",
            nativeQuery = true
    )
    List<Vacation> justFind(int id, LocalDate firstDay, LocalDate LastDate);
}
