package com.github.kacperkwiatkowski.holidayscheduler_backend.auth;

import com.github.kacperkwiatkowski.holidayscheduler_backend.user.UserFacade;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {

    private final UserFacade userFacade;

    public UserAuthService(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        try{
            return org.springframework.security.core.userdetails
                    .User.builder()
                    .username(userFacade.fetchEmail(email))
                    .password(userFacade.fetchPassword(email))
                    .authorities(userFacade.fetchRoleType(email).getGrantedAuthorities())
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
