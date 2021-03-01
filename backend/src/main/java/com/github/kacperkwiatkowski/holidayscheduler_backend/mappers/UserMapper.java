package com.github.kacperkwiatkowski.holidayscheduler_backend.mappers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.convertors.RoleTypeConvertor;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.UserDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper implements ObjectMapper<UserDto, User> {

    private final UserRepository userRepository;

    public UserMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto mapToDto(User user) {
        UserDto userDto = UserDto
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .daysOffLeft(user.getDaysOffLeft())
                .roleType(RoleTypeConvertor.convertToString(user.getRoleType()))
                .imageUrl(user.getImageUrl())
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
            user.setEmail(userDto.getEmail());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setDaysOffLeft(userDto.getDaysOffLeft());
            user.setRoleType(RoleTypeConvertor.convertToEnum(userDto.getRoleType()));

        return user;
    }
}
