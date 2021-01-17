package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.google.gson.Gson;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping(path = "/credentials")
    ResponseEntity<User> signInRequestVerifier(
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

    // TODO Read more about it! https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(){
        logger.error("Handling not found exception");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        return modelAndView;
    }
}
