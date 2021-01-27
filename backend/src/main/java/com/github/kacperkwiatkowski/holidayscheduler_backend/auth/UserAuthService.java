package com.github.kacperkwiatkowski.holidayscheduler_backend.auth;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        try{
            com.github.kacperkwiatkowski.holidayscheduler_backend.model.User user = userRepository.findFirstByEmail(email);
            //TODO Improve code's layout
            return org.springframework.security.core.userdetails
                    .User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .authorities(user.getRoleType().getGrantedAuthorities())
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();
        } catch (Exception e){
            throw new UsernameNotFoundException(String.format("Username %s not found", email));
        }
    }
}
