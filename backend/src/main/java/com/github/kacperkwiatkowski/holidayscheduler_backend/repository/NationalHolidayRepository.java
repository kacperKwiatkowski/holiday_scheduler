package com.github.kacperkwiatkowski.holidayscheduler_backend.repository;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.NationalHoliday;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.nationalHolidayApi.Holidays;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NationalHolidayRepository extends JpaRepository<NationalHoliday, Integer> {

    NationalHoliday findById(int id);

    void save(Holidays holidays);
}
