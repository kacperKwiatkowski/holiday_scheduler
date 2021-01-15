package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping(path = "/credentials")
    ResponseEntity<User> signInRequestVerifier(
            //FIXME check if param if ok
            @RequestParam("details") String loginDetails) {
        Gson g = new Gson();
        User user = g.fromJson(loginDetails, User.class);

        if(user.getEmail().equals("loginDefault") & user.getPassword().equals("1234")){
            logger.info("User signed in.");
            return ResponseEntity.status(200).build();
        } else {
            logger.info("User not signed in.");
            return ResponseEntity.notFound().build();
        }

    }
}
