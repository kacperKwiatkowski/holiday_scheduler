package com.github.kacperkwiatkowski.holidayscheduler_backend.mappers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.UserDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.enums.RoleType;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserMapperTest {

    UserMapper userMapper = new UserMapper();

    @Test
    @DisplayName("User map to DTO with each field")
    public void mapUserToDto_checkIfFieldsMappedCorrectlyGivenAllFields(){
        //given
        User user = User
                .builder()
                .id(2)
                .email("test@gmail.com")
                .firstName("TestFirstName")
                .lastName("testLastName")
                .daysOffLeft(26)
                .roleType(RoleType.ADMIN)
                .build();

        //when
        UserDto userDto = userMapper.mapToDto(user);

        //then
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getDaysOffLeft(), userDto.getDaysOffLeft());
        assertEquals(user.getRoleType(), userDto.getRoleType());
    }

    @Test
    @DisplayName("User map to DTO with no id")
    public void mapUserToDto_checkIfFieldsMappedCorrectlyGivenIdIsNull(){
        //given
        User user = User
                .builder()
                .email("test@gmail.com")
                .firstName("TestFirstName")
                .lastName("testLastName")
                .daysOffLeft(26)
                .roleType(RoleType.ADMIN)
                .build();

        //when
        UserDto userDto = userMapper.mapToDto(user);

        //then
        assertEquals(0, userDto.getId());
    }

    @DisplayName("User map to entity with each field")
    @Test
    public void mapUserToEntity_checkIfFieldsMappedCorrectlyGivenAllFields(){
        //given
        UserDto userDto = UserDto
                .builder()
                .id(1)
                .email("test@gmail.com")
                .firstName("TestFirstName")
                .lastName("testLastName")
                .daysOffLeft(26)
                .roleType(RoleType.ADMIN)
                .build();

        //when
        User user = userMapper.mapToEntity(userDto);

        //then
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getDaysOffLeft(), userDto.getDaysOffLeft());
        assertEquals(user.getRoleType(), userDto.getRoleType());
    }

    @DisplayName("User map to entity with no id")
    @Test
    public void mapUserToEntity_checkIfFieldsMappedCorrectlyGivenIdIsNull(){
        //given
        User user = User
                .builder()
                .email("test@gmail.com")
                .firstName("TestFirstName")
                .lastName("testLastName")
                .daysOffLeft(26)
                .roleType(RoleType.ADMIN)
                .build();

        //when
        UserDto userDto = userMapper.mapToDto(user);

        //then
        assertEquals(0, userDto.getId());
    }

}