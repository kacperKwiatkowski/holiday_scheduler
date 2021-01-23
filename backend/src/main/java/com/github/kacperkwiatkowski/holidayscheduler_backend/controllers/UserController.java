package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.dto.UserDto;
import com.github.kacperkwiatkowski.holidayscheduler_backend.exceptions.ObjectNotFoundException;
import com.github.kacperkwiatkowski.holidayscheduler_backend.mappers.UserMapper;
import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @PostMapping(path = "/create")
    @ResponseBody
    ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        log.info("User created");
        userService.createUser(userDto);
        return ResponseEntity.ok().build();
    }


    @GetMapping(path = "/read/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable int id) throws ObjectNotFoundException {
        return new ResponseEntity<UserDto>(userService.readUser(id), HttpStatus.OK);
    }

    @GetMapping(path = "/read/all")
    public ResponseEntity<List<UserDto>> getAllUser() throws ObjectNotFoundException {
        return new ResponseEntity<List<UserDto>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PatchMapping(path = "/update")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ResponseEntity<UserDto> updateUser(@RequestBody UserDto userToUpdate) throws ObjectNotFoundException {
            userService.updateUser(userToUpdate);
            return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PatchMapping(path = "/update/password")
    ResponseEntity updatePassword(@RequestBody String password, String passwordMatch){
        if(password.equals(passwordMatch)){
            log.info("Password changed successfully");
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            log.info("Password changed unsuccessfully");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity deleteUser(@PathVariable("id") int id) throws ObjectNotFoundException {
        userService.deleteUser(id);
        log.info("User: " + id + " deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @GetMapping(path = "/page")
    public ResponseEntity<List<User>> getAllUsers(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortOrder)
    {
        List<User> list = userService.listAll(pageNo, pageSize, sortBy, sortOrder);
        log.info("Pagination successful");
        return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
