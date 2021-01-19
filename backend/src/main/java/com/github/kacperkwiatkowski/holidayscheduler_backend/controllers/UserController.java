package com.github.kacperkwiatkowski.holidayscheduler_backend.controllers;

import com.github.kacperkwiatkowski.holidayscheduler_backend.model.User;
import com.github.kacperkwiatkowski.holidayscheduler_backend.repository.UserRepository;
import com.github.kacperkwiatkowski.holidayscheduler_backend.service.UserService;
import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

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
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/delete/{id}")
    void deleteUser(@PathVariable("id") int id){
        userRepository.deleteById(id);
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
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(path = "/update/password")
    ResponseEntity updatePassword(@RequestBody String password, String passwordMatch){
        if(password.equals(passwordMatch)){
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
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

        return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
    }

}
