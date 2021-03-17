/*
package com.github.kacperkwiatkowski.com.github.kacperkwiatkowski.user;

import com.github.kacperkwiatkowski.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
//@SpringBootTest()
//@ContextConfiguration
@WebMvcTest(controllers = UserController.class)
@ActiveProfiles(value = "test")
class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserFacade userFacade;




    @Test()
    @WithUserDetails(value="customUsername", userDetailsServiceBeanName="myUserDetailsService")
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
        when(userFacade.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        //then
//        this.mockMvc
//                .perform(get("/com.github.kacperkwiatkowski.user/read/all"))
//                .andExpect(status().isOk());
    }

}*/
