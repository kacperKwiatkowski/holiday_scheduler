package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.UserDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.ObjectNotFoundException;
import com.github.kacperkwiatkowski.holidayscheduler_backend.mappers.UserMapper;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    UserService userService;

    @PostMapping(path = "/create")
    @ResponseBody
    ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        logger.info("User created");
        userService.createUser(userDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/delete/{id}")
    ResponseEntity deleteUser(@PathVariable("id") int id) throws ObjectNotFoundException {
        Optional<User> userToDelete = Optional.ofNullable(userRepository.findById(id));
        if(userToDelete.isPresent()){
            userRepository.deleteById(id);
            logger.info("User: " + id + " deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            throw ObjectNotFoundException.createWith("Deletion unsuccessful. Id does not exist.");
        }
    }

    @PatchMapping(path = "/update")
    @ResponseBody
    ResponseEntity<UserDto> updateUser(@RequestBody UserDto userToPatch) throws ObjectNotFoundException {
        Optional<User> foundUser = Optional.ofNullable(userRepository.findById(userToPatch.getId()));
        UserMapper userMapper = new UserMapper(userRepository);
        if(foundUser.isPresent()){
            User user = userMapper.mapToEntity(userToPatch);
            userRepository.save(user);
            logger.info("User: " + userToPatch.getId() + "updated successfully");
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            logger.info("User: " + userToPatch.getId()  + "updated unsuccessfully");
            throw ObjectNotFoundException.createWith("PATCH impossible, user with such id doesnt exists.");
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

    @GetMapping(path = "/read/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) throws ObjectNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findById(id));
        if(user.isPresent()){
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        } else {
            throw ObjectNotFoundException.createWith("Id does not exist.");
        }
    }

    @GetMapping(path = "/read/all")
    public ResponseEntity<List<User>> getAllUser() throws ObjectNotFoundException {
        Optional<List<User>> users = Optional.ofNullable(userRepository.findAll());
        if(users.isPresent()){
            return new ResponseEntity<List<User>>(users.get(), HttpStatus.OK);
        } else {
            throw ObjectNotFoundException.createWith("The list is empty.");
        }
    }


}
