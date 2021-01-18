package com.github.kacperkwiatkowski.holidayscheduler_backend.repository;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);
}
