package com.github.kacperkwiatkowski.holidayscheduler_backend.repository;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Vacation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Integer> {

    Vacation findById(int id);

    Page<Vacation> findById(String filter, Pageable paging);

    @Query(
            value = "SELECT * FROM Vacation v WHERE v.users_id = ?1 AND v.firstDay BETWEEN ?2 AND ?3",
            nativeQuery = true
    )
    List<Vacation> findHolidaysWithinGivenTimeFrame(int id, LocalDate firstDay, LocalDate LastDate);

    @Query("SELECT v FROM Vacation v WHERE v.user.firstName LIKE %?1%"
            + " OR v.user.lastName LIKE %?1%")
    Page<Vacation> findWithFilter(String filter, Pageable paging);

}
