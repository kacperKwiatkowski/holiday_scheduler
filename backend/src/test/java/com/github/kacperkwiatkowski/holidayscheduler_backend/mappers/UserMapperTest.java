package com.github.kacperkwiatkowski.holidayscheduler_backend.mappers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.convertors.RoleTypeConvertor;
import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.UserDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.utils.roleConfig.RoleType;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)*/
class UserMapperTest {

    private UserRepository userRepository = mock(UserRepository.class);

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("User map to DTO with each field")
    public void mapUserToDto_checkIfFieldsMappedCorrectlyGivenAllFields(){
        //given
        UserMapper userMapper = new UserMapper(userRepository);
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
        assertEquals(user.getRoleType(), RoleTypeConvertor.convertToEnum(userDto.getRoleType()));
    }

    @Test
    @DisplayName("User map to DTO with no id")
    public void mapUserToDto_checkIfFieldsMappedCorrectlyGivenIdIsNull(){
        //given
        UserMapper userMapper = new UserMapper(userRepository);
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
        UserMapper userMapper = new UserMapper(userRepository);
        UserDto userDto = UserDto
                .builder()
                .id(1)
                .email("test@gmail.com")
                .firstName("TestFirstName")
                .lastName("testLastName")
                .daysOffLeft(26)
                .roleType("ADMIN")
                .build();

        //when

        when(userRepository.findById(anyInt())).thenReturn(User.builder().id(1).email("wrong").build());

        User user = userMapper.mapToEntity(userDto);

        //then
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
        assertEquals(user.getDaysOffLeft(), userDto.getDaysOffLeft());
        assertEquals(user.getRoleType().toString(), userDto.getRoleType());
    }

    @DisplayName("User map to entity with no id")
    @Test
    public void mapUserToEntity_checkIfFieldsMappedCorrectlyGivenIdIsNull(){
        //given
        UserMapper userMapper = new UserMapper(userRepository);
        UserDto userDto = UserDto
                .builder()
                .email("test@gmail.com")
                .firstName("TestFirstName")
                .lastName("testLastName")
                .daysOffLeft(26)
                .roleType(RoleTypeConvertor.convertToString(RoleType.ADMIN))
                .build();

        //when
        User user = userMapper.mapToEntity(userDto);

        //then
        assertEquals(0, userDto.getId());
    }


}