package com.github.kacperkwiatkowski.holidayscheduler_backend.repository;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Team;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

interface TeamRepository extends JpaRepository<Team, Integer> {

    Team findById(int id);
}
