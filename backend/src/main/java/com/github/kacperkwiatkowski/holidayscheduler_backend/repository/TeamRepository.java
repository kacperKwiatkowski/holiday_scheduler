package com.github.kacperkwiatkowski.holidayscheduler_backend.repository;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Team;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {

    Team findById(int id);

/*    @Query(
            value = "SELECT user.email FROM USERS u WHERE user.id LIKE id",
            nativeQuery = true)
    List<String> findEmailById(int id);*/
}
