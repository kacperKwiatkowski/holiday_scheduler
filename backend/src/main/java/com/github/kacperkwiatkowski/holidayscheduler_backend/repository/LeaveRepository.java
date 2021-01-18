package com.github.kacperkwiatkowski.holidayscheduler_backend.repository;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Leave;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

interface LeaveRepository extends JpaRepository<Leave, Integer> {

    Leave findById(int id);
}
