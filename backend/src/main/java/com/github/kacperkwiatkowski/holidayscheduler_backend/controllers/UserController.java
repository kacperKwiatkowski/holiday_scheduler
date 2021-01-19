package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.UserNotFoundException;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.service.UserService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping(path = "/create")
    ResponseEntity createUser(@RequestBody String userDetails){

        Gson g = new Gson();
        User user = g.fromJson(userDetails, User.class);
        userRepository.save(user);

        logger.info("User: " + user.getId() + "added successfully");

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/delete/{id}")
    ResponseEntity deleteUser(@PathVariable("id") int id){

        if(userRepository.findById(id)==null) throw new UserNotFoundException("Error1233");

        userRepository.deleteById(id);

        logger.info("User: " + id + " deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping(path = "/update/{id}/{firstname}/{lastname}/{email}/{daysOff}")
    ResponseEntity updateUser(@PathVariable int id, @PathVariable String firstname, @PathVariable String lastname, @PathVariable String email, @PathVariable int daysOff){
        Optional<User> foundUser = Optional.ofNullable(userRepository.findById(id));
        if(foundUser.isPresent()){
            User user = foundUser.get();
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setEmail(email);
            user.setDaysOffLeft(daysOff);
            userRepository.save(user);

            logger.info("User: " + id + "updated successfully");

            return ResponseEntity.noContent().build();
        } else {
            logger.info("User: " + id + "updated unsuccessfully");

            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(path = "/update/password")
    ResponseEntity updatePassword(@RequestBody String password, String passwordMatch){
        if(password.equals(passwordMatch)){
            logger.info("Password changed successfully");
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            logger.info("Password changed unsuccessfully");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping(path = "/page")
    public ResponseEntity<List<User>> getAllUsers(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortOrder)
    {
        List<User> list = userService.listAll(pageNo, pageSize, sortBy, sortOrder);

        logger.info("Pagination successful");

        return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
    }

}
