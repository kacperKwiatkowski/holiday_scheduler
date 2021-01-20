package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.EntityNotFoundException;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.google.gson.Gson;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    @Autowired
    UserController userController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(userController).isNotNull();
    }

    @Test
    void getAllUsers_ReturnsCorrectPagination() {
        //given
        List<User> listOfUsers = new ArrayList<>();

        //when
        listOfUsers = userController.getAllUsers(0, 10, "FirstName", "ASC").getBody();

        //then
        assertThat(listOfUsers.size()).isEqualTo(10);
    }

    @Test
    void getUser_ThrowsEntityFoundExceptionError() {
        assertThrows(EntityNotFoundException.class, () -> {
            userController.getUser(12);
        });
    }

}