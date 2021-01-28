package com.github.kacperkwiatkowski.holidayscheduler_backend.repository;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.NationalHoliday;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.nationalHolidayApi.Holidays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalHolidayRepository extends JpaRepository<NationalHoliday, Integer> {

    NationalHoliday findById(int id);

    void save(Holidays holidays);

/*    public List<DayOff> getOverlappingHolidays(DayOff dayOff){
        return (List<DayOff>) entityManager.createQuery("FROM DayOff  where user.email like :email and (firstDay between :firstDay and :lastDay or lastDay between :firstDay and :lastDay)")
                .setParameter("email", dayOff.getUser().getEmail())
                .setParameter("firstDay", dayOff.getFirstDay())
                .setParameter("lastDay", dayOff.getLastDay())
                .getResultList();
    }*/

}
