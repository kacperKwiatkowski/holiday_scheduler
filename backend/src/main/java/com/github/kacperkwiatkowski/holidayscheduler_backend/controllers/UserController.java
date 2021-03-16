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
class UserController {

    UserController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @PostMapping(path = "/create")
    @PreAuthorize("hasAuthority('employee:create')")
    @ResponseBody
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        log.info("Controller 'createUser' initiated.");
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @GetMapping(path = "/read/{id}")
    @PreAuthorize("hasAuthority('employee:read')")
    ResponseEntity<UserDto> getUser(@PathVariable int id) throws ObjectNotFoundException {
        log.info("Controller 'getUser' initiated.");
        return new ResponseEntity<UserDto>(userService.readUser(id), HttpStatus.OK);
    }

    @GetMapping(path = "/read/logged")
    @PreAuthorize("hasAuthority('self:edit')")
    ResponseEntity<UserDto> getUser(@RequestParam String email) throws ObjectNotFoundException {
        log.info("Controller 'getUser' initiated.");
        return new ResponseEntity<UserDto>(userService.readUser(email), HttpStatus.OK);
    }


    @GetMapping(path = "/read/all")
    @PreAuthorize("hasAuthority('employee:read')")
    ResponseEntity<List<UserDto>> getAllUser() throws ObjectNotFoundException {
        log.info("Controller 'getAllUser' initiated.");
        return new ResponseEntity<List<UserDto>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PatchMapping(path = "/update/{id}")
    @PreAuthorize("hasAuthority('employee:update')")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    ResponseEntity<UserDto> updateUser(@RequestBody UserDto userToUpdate) throws ObjectNotFoundException {
            log.info("Controller 'updateUser' initiated.");
            return ResponseEntity.ok(userService.updateUser(userToUpdate));

    }

    @DeleteMapping(path = "/delete/{id}")
    @PreAuthorize("hasAuthority('employee:delete')")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserDto> deleteUser(
            @PathVariable("id") int id,String token
    ) throws ObjectNotFoundException {

        log.info("Controller 'deleteUser' initiated.");
        return ResponseEntity.ok(userService.deleteUser(id));
    }


    @GetMapping(path = "/page")
    ResponseEntity<List<UserDto>> getAllUsers(
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
