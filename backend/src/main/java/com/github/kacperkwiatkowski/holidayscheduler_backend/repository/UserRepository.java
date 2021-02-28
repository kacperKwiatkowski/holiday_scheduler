package com.github.kacperkwiatkowski.holidayscheduler_backend.repository;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);

    User findByEmail(String email);

    void deleteById(int id);

    User findFirstByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.firstName LIKE %?1%"
            + " OR u.lastName LIKE %?1%")
    Page<User> findWithFilter(String filter, Pageable paging);
}
