package com.github.kacperkwiatkowski.holidayscheduler_backend.repository;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.NationalHoliday;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

interface NationalHolidayRepository extends JpaRepository<NationalHoliday, Integer> {

    NationalHoliday findById(int id);
}
