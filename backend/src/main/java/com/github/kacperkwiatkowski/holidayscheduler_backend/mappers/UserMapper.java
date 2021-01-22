package com.github.kacperkwiatkowski.holidayscheduler_backend.mappers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.TeamDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.UserDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.Team;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.TeamRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Mapper
public class UserMapper implements ObjectMapper<UserDto, User> {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto mapToDto(User user) {
        UserDto userDto = UserDto
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .daysOffLeft(user.getDaysOffLeft())
                .roleType(user.getRoleType())
                .build();

        return userDto;
    }

    @Override
    public User mapToEntity(UserDto userDto) {

        User user;
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findById(userDto.getId()));

        if(optionalUser.isEmpty()){
            user = new User();
        } else {
            user = optionalUser.get();
        }

        if(!user.getEmail().equals(userDto.getEmail())){
            user.setEmail(userDto.getEmail());
        }

        if(!user.getFirstName().equals(userDto.getFirstName())){
            user.setFirstName(userDto.getFirstName());
        }

        if(!user.getLastName().equals(userDto.getLastName())){
            user.setLastName(userDto.getLastName());
        }

        if(user.getDaysOffLeft()!=userDto.getDaysOffLeft()){
            user.setDaysOffLeft(userDto.getDaysOffLeft());
        }

        if(!user.getRoleType().equals(userDto.getRoleType())){
            user.setRoleType(userDto.getRoleType());
        }

        return user;
    }
}
