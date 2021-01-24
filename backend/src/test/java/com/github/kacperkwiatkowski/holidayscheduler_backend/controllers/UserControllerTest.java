package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.UserDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.ApplicationExceptionHandler;
import com.github.kacperkwiatkowski.holidayscheduler_backend.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getAllUsers_responseShouldReturnStatusCode200andAllUser() throws Exception {
        //given
        UserDto user1 = UserDto
                .builder()
                .id(1)
                .firstName("Name1")
                .lastName("LastName1")
                .email("email1@gmial.com")
                .roleType("ADMIN")
                .daysOffLeft(26)
                .build();

        UserDto user2 = UserDto
                .builder()
                .id(1)
                .firstName("Name2")
                .lastName("LastName2")
                .email("email2@gmial.com")
                .roleType("ADMIN")
                .daysOffLeft(26)
                .build();

        //when
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        //then
        this.mockMvc
                .perform(get("/user/read/all"))
                .andExpect(status().isOk());
    }

}