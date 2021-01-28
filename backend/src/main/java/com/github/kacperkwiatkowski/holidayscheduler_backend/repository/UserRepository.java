package com.github.kacperkwiatkowski.holidayscheduler_backend.repository;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);

    void deleteById(int id);
}
