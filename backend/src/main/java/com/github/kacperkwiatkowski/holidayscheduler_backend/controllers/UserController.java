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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @PostMapping(path = "/create")
    @PreAuthorize("hasAuthority('employee:create')")
    @ResponseBody
    ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        log.info("Controller 'createUser' initiated.");
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @GetMapping(path = "/read/{id}")
    @PreAuthorize("hasAuthority('employee:read')")
    public ResponseEntity<UserDto> getUser(@PathVariable int id) throws ObjectNotFoundException {
        log.info("Controller 'getUser' initiated.");
        return new ResponseEntity<UserDto>(userService.readUser(id), HttpStatus.OK);
    }

    @GetMapping(path = "/read/logged")
    @PreAuthorize("hasAuthority('self:edit')")
    public ResponseEntity<UserDto> getUser(@RequestParam String email) throws ObjectNotFoundException {
        log.info("Controller 'getUser' initiated.");
        return new ResponseEntity<UserDto>(userService.readUser(email), HttpStatus.OK);
    }


    @GetMapping(path = "/read/all")
    @PreAuthorize("hasAuthority('employee:read')")
    public ResponseEntity<List<UserDto>> getAllUser() throws ObjectNotFoundException {
        log.info("Controller 'getAllUser' initiated.");
        return new ResponseEntity<List<UserDto>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PatchMapping(path = "/update")
    @PreAuthorize("hasAuthority('employee:update')")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ResponseEntity<UserDto> updateUser(@RequestBody UserDto userToUpdate) throws ObjectNotFoundException {
            log.info("Controller 'updateUser' initiated.");
            //TODO Return entity
            return ResponseEntity.ok(userService.updateUser(userToUpdate));

    }

    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("hasAuthority('employee:delete')")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity deleteUser(@PathVariable("id") int id) throws ObjectNotFoundException {
        userService.deleteUser(id);
        log.info("Controller 'deleteUser' initiated.");
        //TODO Controller doesn't return the deleted entity
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @GetMapping(path = "/page")
    public ResponseEntity<List<UserDto>> getAllUsers(
            @RequestParam(defaultValue = "0") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "lastName") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortOrder,
            @RequestParam(defaultValue = "") String filter)
    {
        log.info("Controller 'getAllUsers' initiated.");
        return new ResponseEntity<List<UserDto>>(userService.listAll(pageNum, pageSize, sortBy, sortOrder, filter), new HttpHeaders(), HttpStatus.OK);
    }


}
