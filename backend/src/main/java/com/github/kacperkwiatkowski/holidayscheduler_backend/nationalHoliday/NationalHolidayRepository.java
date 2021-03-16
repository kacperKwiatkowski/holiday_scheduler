package com.github.kacperkwiatkowski.holidayscheduler_backend.nationalHoliday;

import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.nationalHolidayApi.Holidays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.LinkedList;

@Repository
public interface NationalHolidayRepository extends JpaRepository<NationalHoliday, Integer> {

    NationalHoliday findById(int id);

    void save(Holidays holidays);

    boolean existsByHolidayDate(LocalDate date);

    @Query(
            value = "SELECT * FROM NationalHoliday n WHERE n.holidayDate BETWEEN :firstDay AND :lastDay",
            nativeQuery = true
    )
    LinkedList<NationalHoliday> findHolidaysWithinGivenTimeFrame(@Param("firstDay")LocalDate firstDay, @Param("lastDay")LocalDate LastDate);
}
