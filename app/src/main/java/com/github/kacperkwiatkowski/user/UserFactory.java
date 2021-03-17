package com.github.kacperkwiatkowski.user;

import com.github.kacperkwiatkowski.convertors.RoleTypeConvertor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class UserFactory {

    private final UserRepository userRepository;

    UserFactory(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    User mapToEntity(UserDto userDto) {

        User user;
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findById(userDto.getId()));

        if(optionalUser.isEmpty()){
            user = new User();
        } else {
            user = optionalUser.get();
        }
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setDaysOffLeft(userDto.getDaysOffLeft());
        user.setRoleType(RoleTypeConvertor.convertToEnum(userDto.getRoleType()));

        return user;
    }


    User mapToEntity(UserSecurityDto userSecurityDto) {

        User user = new User();
        user.setEmail(userSecurityDto.getEmail());
        user.setPassword(userSecurityDto.getPassword());
        user.setFirstName(userSecurityDto.getFirstName());
        user.setLastName(userSecurityDto.getLastName());
        user.setDaysOffLeft(userSecurityDto.getDaysOffLeft());
        user.setRoleType(userSecurityDto.getRoleType());
        user.setImageUrl(userSecurityDto.getImageUrl());

        return user;
    }
}
