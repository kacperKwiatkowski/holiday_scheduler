package com.github.kacperkwiatkowski.auth;

import com.github.kacperkwiatkowski.enums.RoleType;
import com.github.kacperkwiatkowski.user.UserFacade;
import com.github.kacperkwiatkowski.user.UserSecurityDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;

    public UserAuthService(UserFacade userFacade, PasswordEncoder passwordEncoder) {
        this.userFacade = userFacade;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if(userFacade.getAllUsers().isEmpty()){
            userFacade.save(new UserSecurityDto("ola@gmail.com", passwordEncoder.encode("1234"), "Ola", "Olska", RoleType.ADMIN, 26, "profile-image"));
            userFacade.save(new UserSecurityDto("ida@gmail.com", passwordEncoder.encode("1234"), "Ida", "Idowska", RoleType.ADMIN, 26, "profile-image"));
        }


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
