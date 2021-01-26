package com.github.kacperkwiatkowski.holidayscheduler_backend.auth;

import org.springframework.context.annotation.Bean;

import java.util.Optional;

public interface ApplicationUserDao {

    Optional<ApplicationUser> selectApplicationUserByUsername(String username);

}